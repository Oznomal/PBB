package com.lamonzo.pbb.controller;

import com.jfoenix.controls.JFXButton;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.model.DataModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Slf4j
public class HeaderController implements Initializable {

    //================================================================================================================//
    //== FIELDS ==
    private final ThreadPoolTaskExecutor taskExecutor;
    private final DataModel dataModel;

    @FXML
    private JFXButton minButton;

    @FXML
    private JFXButton closeButton;

    //================================================================================================================//
    //== CONSTRUCTORS ==
    @Autowired
    public HeaderController(@Qualifier(SpringConstants.VARIABLE_TASK_EXECUTOR) ThreadPoolTaskExecutor taskExecutor,
                            DataModel dataModel){
        this.taskExecutor = taskExecutor;
        this.dataModel = dataModel;
    }

    //================================================================================================================//
    //== INIT ==
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        minButton.setOnAction(e -> handleMinimizeButtonClick(e));
        closeButton.setOnAction(e -> handleCloseButtonClick(e));
    }

    //================================================================================================================//
    //== PRIVATE METHODS ==
    private void handleMinimizeButtonClick(Event event){
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    private void handleCloseButtonClick(Event event){
        dataModel.getCancellingTask().set(true); //Cancels never ending loop to allow threads to exit

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();

        taskExecutor.shutdown();
        System.exit(0);
    }
}
