package com.lamonzo.pbb.controller.tab;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.lamonzo.pbb.cell.SelectButtonCell;
import com.lamonzo.pbb.constants.CssConstants;
import com.lamonzo.pbb.domain.PlayerTreeObject;
import com.lamonzo.pbb.model.DataModel;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public abstract class BaseTabController implements Initializable {

    //== FIELDS ==
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
    protected JFXTreeTableColumn<PlayerTreeObject, Boolean> selectColumn;


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
        nameColumn.setPrefWidth(400);
        positionColumn.setPrefWidth(300);
        teamColumn.setPrefWidth(100);
        selectColumn.setPrefWidth(200);
    }

    //== SPRING PROTOTYPE LOOKUPS ==
    @Lookup
    SelectButtonCell getSelectButtonCell(){
        return null;
    }

    //== PROTECTED METHODS ==
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
