package com.lamonzo.pbb.controller.tab;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.lamonzo.pbb.cell.SelectButtonCell;
import com.lamonzo.pbb.constants.CssConstants;
import com.lamonzo.pbb.constants.StatConstants;
import com.lamonzo.pbb.domain.PlayerTreeObject;
import com.lamonzo.pbb.domain.Stat;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.service.PositionService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
public abstract class BaseTabController implements Initializable {

    //== FIELDS ==
    @Autowired
    protected DataModel dataModel;

    @Autowired
    protected PositionService positionService;

    @FXML
    protected JFXTreeTableView<PlayerTreeObject> playerTableView;

    @FXML
    protected JFXTreeTableColumn<PlayerTreeObject, String> nameColumn;

    @FXML
    protected JFXTreeTableColumn<PlayerTreeObject, String> positionColumn;

    @FXML
    protected JFXTreeTableColumn<PlayerTreeObject, String> teamColumn;

    @FXML
    protected JFXTreeTableColumn<PlayerTreeObject, Boolean> selectColumn;

    @FXML
    protected Label voteCountLabel;

    @FXML
    protected Label maxVotesLabel;

    @FXML
    protected JFXTextField positionFilter;

    @Getter
    protected String positionName;

    protected int maxVotes;

    //== PUBLIC METHODS ==
    //Sets default fields that are shared amongst all tabs
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SET CELL VALUE FACTORY
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PlayerTreeObject, String> param)
                -> param.getValue().getValue().getName());
        positionColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PlayerTreeObject, String> param)
                -> param.getValue().getValue().getPosition());
        teamColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PlayerTreeObject, String> param)
                -> param.getValue().getValue().getTeam());
        selectColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PlayerTreeObject, Boolean> param)
                -> param.getValue().getValue().getIsSelected());

        //SETTING CELL FACTORY
        setPseudoClassForStringColumn(nameColumn, CssConstants.PSEUDO_PADDED);
        setPseudoClassForStringColumn(positionColumn, CssConstants.PSEUDO_CENTERED);
        setPseudoClassForStringColumn(teamColumn, CssConstants.PSEUDO_CENTERED);
        selectColumn.setCellFactory(column -> {
            TreeTableCell<PlayerTreeObject, Boolean> cell = getSelectButtonCell();
            cell.pseudoClassStateChanged(PseudoClass.getPseudoClass(CssConstants.PSEUDO_CENTERED), true);
            return cell;
        });

        //NON SORTABLE COLUMNS
        positionColumn.setSortable(false);
        selectColumn.setSortable(false);

        //DEFAULT COLUMN WIDTH (TOTAL SPACE ALLOCATION 1100px)
        nameColumn.setPrefWidth(330);
        positionColumn.setPrefWidth(300);
        teamColumn.setPrefWidth(100);
        selectColumn.setPrefWidth(200);

        //SETUP POSITION FILTER
        positionFilter.setPromptText("Search ... ");
        positionFilter.textProperty().addListener((o, oldVal, newVal) -> {
            playerTableView.setPredicate(pto ->
                    pto.getValue().getName().get().toLowerCase().contains(newVal.toLowerCase()) ||
                            pto.getValue().getTeam().get().toLowerCase().contains(newVal.toLowerCase())
            );
        });
    }

    //== PUBLIC METHODS ==
    public void buildTreeTable(){
        ObservableList<PlayerTreeObject> players = dataModel.getPlayerDataByPosition(positionName);
        if(players != null && !players.isEmpty()) {

            final TreeItem<PlayerTreeObject> root = new RecursiveTreeItem<>(players, RecursiveTreeObject::getChildren);

            //The order that we add the columns determines in what order they are displayed in
            playerTableView.getColumns().setAll(nameColumn, positionColumn , teamColumn);
            List<Stat> randomStats = players.get(0).getPlayer().getStats();
            if(randomStats != null && !randomStats.isEmpty()){
                for(Stat stat : randomStats) {
                    playerTableView.getColumns().add(buildStatColumn(stat.getType().getStatType()));
                }
                setupColumnSizes(randomStats.size());
            }
            playerTableView.getColumns().add(selectColumn);

            playerTableView.setRoot(root);
            playerTableView.setShowRoot(false);
            playerTableView.setEditable(false);

            //SETUP THE VOTING COUNT INFORMATION
            maxVotes = dataModel.getPositionByName(positionName).getMaxVotes();
            maxVotesLabel.setText("OF " + maxVotes + " VOTES ");
            voteCountLabel.textProperty().bind(dataModel.getPositionVoteMap().get(positionName));
        }else{
            log.warn("Player List is Empty for: " + positionName);
        }
    }

    //== PRIVATE METHODS ==
    //Adds a CSS Pseudo class to a String column and creates the cell factory
    private void setPseudoClassForStringColumn(JFXTreeTableColumn<PlayerTreeObject, String> column, String pseudoClass){
        column.setCellFactory(col -> {
            TreeTableCell<PlayerTreeObject, String> cell = new TreeTableCell<>(){
                @Override
                protected void updateItem(String value, boolean empty) {
                    super.updateItem(value, empty);
                    setText(empty ? null : value);
                }
            };
            cell.pseudoClassStateChanged(PseudoClass.getPseudoClass(pseudoClass), true);
            return cell;
        });
    }

    //Builds and returns a stat column
    private JFXTreeTableColumn<PlayerTreeObject, Number> buildStatColumn(String statType){
        JFXTreeTableColumn<PlayerTreeObject, Number> statColumn = new JFXTreeTableColumn<>(statType);

        statColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PlayerTreeObject, Number> param) -> {
            List<Stat> stats = param.getValue().getValue().getPlayer().getStats();
            for(Stat stat : stats){
                if(stat.getType().getStatType().equals(statType)) {
                    if(StatConstants.STAT_VALUE_TYPE_MAP.get(statType))
                        return new SimpleDoubleProperty(stat.getValue());

                    //Valid stats that aren't yet received for a player are represented by Double.MIN_VALUE
                    //In the DB. However, if we make it to this ELSE IF then it means we want to return an
                    //Integer because that particular stat column shouldn't show decimal places, so we
                    //need to convert the Double.MIN_VALUE to Integer.MIN_VALUE to avoid errors and then
                    //check for both in the CellFactory
                    else if(stat.getValue() == Double.MIN_VALUE)
                        return new SimpleIntegerProperty(Integer.MIN_VALUE);

                    else
                        return new SimpleIntegerProperty((int) stat.getValue());
                }
            }
            return null;
        });

        statColumn.setCellFactory(column -> {
            TreeTableCell<PlayerTreeObject, Number> cell = new TreeTableCell<>(){
                @Override
                protected void updateItem(Number item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty){
                        setText(null);
                    }else if(item.intValue() == Integer.MIN_VALUE || item.doubleValue() == Double.MIN_VALUE){
                        setText("--");
                    }else{
                        setText(item.toString());
                    }
                }
            };
            cell.pseudoClassStateChanged(PseudoClass.getPseudoClass(CssConstants.PSEUDO_CENTERED), true);
            return cell;
        });

        statColumn.setPrefWidth(75);

        return statColumn;
    }

    private void setupColumnSizes(int numberOfStatColumns){
        if(numberOfStatColumns == 3){
            nameColumn.setPrefWidth(275);
            positionColumn.setPrefWidth(225);
            teamColumn.setPrefWidth(100);
            selectColumn.setPrefWidth(175);
        }
        else if(numberOfStatColumns == 4){
            nameColumn.setPrefWidth(250);
            positionColumn.setPrefWidth(200);
            teamColumn.setPrefWidth(100);
            selectColumn.setPrefWidth(150);
        }
    }

    //== SPRING PROTOTYPE LOOKUPS ==
    @Lookup
    SelectButtonCell getSelectButtonCell(){
        return null;
    }
}
