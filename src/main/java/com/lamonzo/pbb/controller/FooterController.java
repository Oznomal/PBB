package com.lamonzo.pbb.controller;

import com.lamonzo.pbb.model.DataModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Getter
public class FooterController implements Initializable {

    //================================================================================================================//
    //== FIELDS ==
    private final DataModel dataModel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label updateLabel;

    @FXML
    private Label cancelLabel;

    @FXML
    private Label buildingBallotLabel;

    //================================================================================================================//
    //== CONSTRUCTORS ==
    @Autowired
    public FooterController(DataModel dataModel){
        this.dataModel = dataModel;
    }

    //================================================================================================================//
    //== PUBLIC METHODS ==
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Display the progress information when cancelling a task or updating player data
        progressBar.visibleProperty().bind(Bindings.or(dataModel.getCancellingTask(),
                dataModel.getIsUpdatePlayerDataRunning()).or(dataModel.getIsInitialSubmitBallotLoading()));
        cancelLabel.visibleProperty().bind(dataModel.getCancellingTask());
        cancelLabel.managedProperty().bind(dataModel.getCancellingTask());
        updateLabel.visibleProperty().bind(dataModel.getIsUpdatePlayerDataRunning());
        updateLabel.managedProperty().bind(dataModel.getIsUpdatePlayerDataRunning());
        buildingBallotLabel.visibleProperty().bind(dataModel.getIsInitialSubmitBallotLoading());
        buildingBallotLabel.managedProperty().bind(dataModel.getIsInitialSubmitBallotLoading());
    }
}
