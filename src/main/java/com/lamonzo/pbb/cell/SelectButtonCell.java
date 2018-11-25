package com.lamonzo.pbb.cell;

import com.jfoenix.controls.JFXButton;
import com.lamonzo.pbb.constants.CssConstants;
import com.lamonzo.pbb.domain.PlayerTreeObject;
import com.lamonzo.pbb.model.DataModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TreeTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SelectButtonCell extends TreeTableCell<PlayerTreeObject, Boolean> {

    //== FIELDS ==
    private final JFXButton selectBtn;
    private final DataModel dataModel;

    //== CONSTRUCTOR
    @Autowired
    public SelectButtonCell(DataModel dataModel){
        this.dataModel = dataModel;
        this.selectBtn = new JFXButton(CssConstants.UNSELECTED_BTN_TEXT);
    }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);

        if (empty) {
            setGraphic(null);
        }
        else if(getTreeTableRow().getTreeItem() != null) {
            PlayerTreeObject pto = getTreeTableRow().getTreeItem().getValue();
            selectBtn.setOnAction(event -> handleSelectPlayerButtonClick(pto));
            selectBtn.setText(pto.getIsSelected().getValue() ? CssConstants.SELECTED_BTN_TEXT
                    : CssConstants.UNSELECTED_BTN_TEXT);
            setGraphic(selectBtn);
        }
    }

    private void handleSelectPlayerButtonClick(PlayerTreeObject pto){
        BooleanProperty newValue;

        if(pto.getIsSelected().getValue()) {
            newValue = new SimpleBooleanProperty(false);
            dataModel.getBallotList().remove(pto.getPlayer());
            selectBtn.setText(CssConstants.UNSELECTED_BTN_TEXT);
        }
        else {
            newValue = new SimpleBooleanProperty(true);
            dataModel.getBallotList().add(pto.getPlayer());
            selectBtn.setText(CssConstants.SELECTED_BTN_TEXT);
        }

        pto.setIsSelected(newValue);
    }
}
