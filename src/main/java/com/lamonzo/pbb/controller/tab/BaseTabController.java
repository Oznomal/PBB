package com.lamonzo.pbb.controller.tab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.lamonzo.pbb.constants.CssConstants;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.PlayerTreeObject;
import com.lamonzo.pbb.model.DataModel;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public abstract class BaseTabController implements Initializable {

    //== FIELDS ==
    protected static final String UNSELECTED_BTN_TEXT = "Select Player";
    protected static final String SELECTED_BTN_TEXT = "Selected";

    @Autowired
    protected DataModel dataModel;

    @FXML
    protected JFXTreeTableView<PlayerTreeObject> playerTableView;

    @FXML
    protected JFXTreeTableColumn<PlayerTreeObject, String> nameColumn;

    @FXML
    protected JFXTreeTableColumn<PlayerTreeObject, String> positionColumn;

    @FXML
    protected JFXTreeTableColumn<PlayerTreeObject, String> teamColumn;

    @FXML
    protected JFXTreeTableColumn selectColumn;


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
        selectColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(null));

        //SETTING CELL FACTORY
        setPseudoClassForStringColumn(nameColumn, CssConstants.PSEUDO_PADDED);
        setPseudoClassForStringColumn(positionColumn, CssConstants.PSEUDO_CENTERED);
        setPseudoClassForStringColumn(teamColumn, CssConstants.PSEUDO_CENTERED);
        selectColumn.setCellFactory(column -> {
            TreeTableCell<PlayerTreeObject, String> cell = new TreeTableCell<>(){
                JFXButton selectBtn = new JFXButton(UNSELECTED_BTN_TEXT);

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(null);
                    if(empty){
                        setGraphic(null);
                    }else{
                        //selectBtn.setPrefSize();
                        selectBtn.setOnAction(event -> {
                            PlayerTreeObject player = getTreeTableRow().getTreeItem().getValue();
                            System.out.println("Selected Button For: " + player.getName().getValue());
//                            selectBtn.setText(selectBtn.getText().equals(UNSELECTED_BTN_TEXT)
//                                    ? SELECTED_BTN_TEXT : UNSELECTED_BTN_TEXT);
                            //TODO: HANDLE BUTTON CLICKS
                        });
                        setGraphic(selectBtn);
                    }
                }
            };

            cell.pseudoClassStateChanged(PseudoClass.getPseudoClass(CssConstants.PSEUDO_CENTERED), true);
            return cell;
        });

        //NON SORTABLE COLUMNS
        positionColumn.setSortable(false);
        selectColumn.setSortable(false);

        //DEFAULT COLUMN WIDTH (TOTAL SPACE ALLOCATION 1100px)
        nameColumn.setPrefWidth(400);
        positionColumn.setPrefWidth(300);
        teamColumn.setPrefWidth(100);
        selectColumn.setPrefWidth(200);
    }

    /**
     * Adds a CSS Pseudo class to a String column
     * @param column the JFXTreeTableColumn
     * @param pseudoClass the name of the CSS class to be added
     */
    protected void setPseudoClassForStringColumn(JFXTreeTableColumn<PlayerTreeObject, String> column, String pseudoClass){
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

    protected void buildTreeTable(String position){
        ObservableList<PlayerTreeObject> players = dataModel.getPlayerDataByPosition(position);
        if(players != null && !players.isEmpty()) {

            final TreeItem<PlayerTreeObject> root = new RecursiveTreeItem<PlayerTreeObject>
                    (players, RecursiveTreeObject::getChildren);

            playerTableView.getColumns().setAll(nameColumn, positionColumn, teamColumn, selectColumn);
            playerTableView.setRoot(root);
            playerTableView.setShowRoot(false);
            playerTableView.setEditable(false);
        }else{
            log.warn("Player List is Empty for: " + position);
        }
    }
}
