package com.lamonzo.pbb.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.lamonzo.pbb.cell.UserBallotCell;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.tasks.SubmitBallotService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Slf4j
public class UserBallotController implements Initializable {

    //== FIELDS ==
    private final DataModel dataModel;
    private final SubmitBallotService submitBallotService;

    @FXML
    private JFXListView<Player> userBallotListView;

    @FXML
    private JFXButton submitButton;

    @FXML
    private Label ballotCountLabel;

    @FXML
    @Getter
    private JFXComboBox<String> countSelector;


    //== CONSTRUCTOR ==
    @Autowired
    public UserBallotController(DataModel dataModel, SubmitBallotService submitBallotService){
        this.dataModel = dataModel;
        this.submitBallotService = submitBallotService;
    }

    //== PUBLIC METHODS ==
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userBallotListView.setItems(dataModel.getBallotList());
        userBallotListView.setCellFactory(column -> getUserBallotCell());

        //Makes submit only clickable when count is selected and at least 1 player is added
        submitButton.disableProperty().bind(countSelector.valueProperty().isNull()
                .or(Bindings.isEmpty(userBallotListView.getItems()))
                .or(submitBallotService.runningProperty()));
        submitButton.setOnAction(event -> handleSubmitButtonClick());

        ballotCountLabel.textProperty().bind(dataModel.getSuccessCount().asString());
    }

    //== PRIVATE METHODS ==
    public void handleSubmitButtonClick(){
        submitBallotService.start();
    }

    //== SPRING LOOKUPS ==
    //Gets a new instance of UserBallotCell which is prototype scoped, ignore the null, this works with spring magic!
    @Lookup
    UserBallotCell getUserBallotCell(){
        return null;
    }
}
