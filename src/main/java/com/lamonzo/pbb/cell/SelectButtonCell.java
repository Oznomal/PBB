package com.lamonzo.pbb.cell;

import com.jfoenix.controls.JFXButton;
import com.lamonzo.pbb.constants.CssConstants;
import com.lamonzo.pbb.domain.PlayerTreeObject;
import com.lamonzo.pbb.model.DataModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SelectButtonCell extends TreeTableCell<PlayerTreeObject, Boolean> {

    //================================================================================================================//
    //== FIELDS ==
    private final JFXButton selectBtn;
    private final DataModel dataModel;

    //================================================================================================================//
    //== CONSTRUCTOR
    @Autowired
    public SelectButtonCell(DataModel dataModel){
        this.dataModel = dataModel;
        this.selectBtn = new JFXButton(CssConstants.UNSELECTED_BTN_TEXT);
    }

    //================================================================================================================//
    //== PROTECTED METHODS
    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);

        if (empty) {
            setGraphic(null);
        }
        else if(getTreeTableRow().getTreeItem() != null){
            PlayerTreeObject pto = getTreeTableRow().getTreeItem().getValue();

            //HANDLE BUTTON CLICKS
            selectBtn.setOnAction(event -> handleSelectPlayerButtonClick(pto));

            //DISABLE SELECT PLAYER BUTTONS UNDER 3 CONDITIONS
            //1. THE MAX VOTE IS REACHED FOR POSITION (DISABLES ALL UNSELECTED PLAYERS BUTTONS)
            //2. WHEN UPDATE PLAYER DATA IS RUNNING (DISABLES ALL BUTTONS)
            //3. WHEN SUBMIT BALLOT IS RUNNING (DISABLES ALL BUTTONS)
            SimpleStringProperty currentVoteCount = dataModel.getPositionVoteMap().get(pto.getPosition().get());
            String mv = Integer.toString(pto.getPlayer().getPosition().getMaxVotes());
            selectBtn.disableProperty().bind(Bindings.and(
                    Bindings.greaterThanOrEqual(currentVoteCount, mv),
                    Bindings.not(pto.getIsSelected()))
                    .or(dataModel.getIsUpdatePlayerDataRunning()).or(dataModel.getIsSubmitBallotRunning()));

            //SET BUTTON TEXT
            selectBtn.setText(pto.getIsSelected().get() ? CssConstants.SELECTED_BTN_TEXT
                    : CssConstants.UNSELECTED_BTN_TEXT);

            //SET THE GRAPHIC
            setGraphic(selectBtn);
        }
    }

    //================================================================================================================//
    //== PRIVATE METHODS ==
    private void handleSelectPlayerButtonClick(PlayerTreeObject pto){
        String pos = pto.getPosition().get();
        BooleanProperty newValue = new SimpleBooleanProperty(false);

        int currVoteCount = getCurrentVoteCount(pos);

        if(pto.getIsSelected().getValue()) {
            newValue = new SimpleBooleanProperty(false);
            dataModel.getBallotList().remove(pto.getPlayer());
            selectBtn.setText(CssConstants.UNSELECTED_BTN_TEXT);
            currVoteCount = currVoteCount > 0 ? --currVoteCount : 0;
        }
        else if(currVoteCount < dataModel.getPositionByName(pos).getMaxVotes()){
            newValue = new SimpleBooleanProperty(true);
            dataModel.getBallotList().add(pto.getPlayer());
            selectBtn.setText(CssConstants.SELECTED_BTN_TEXT);
            currVoteCount++;
        }

        updateCurrentVoteCount(pos, currVoteCount);
        pto.setIsSelected(newValue);
        updateItem(true, false);
    }

    private int getCurrentVoteCount(String position){
        String voteCountString = dataModel.getPositionVoteMap().get(position).get();
        return Integer.parseInt(voteCountString);
    }

    private void updateCurrentVoteCount(String position, int value){
        String newValue = Integer.toString(value);
        dataModel.getPositionVoteMap().get(position).setValue(newValue);
    }
}
