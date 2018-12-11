package com.lamonzo.pbb.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.lamonzo.pbb.cell.UserBallotCell;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.controller.dialog.SettingsController;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.tasks.SubmitBallot;
import com.lamonzo.pbb.tasks.SubmitLightningBallot;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Slf4j
public class UserBallotController implements Initializable {

    //== FIELDS ==
    private final DataModel dataModel;
    private final ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private MainController mainController;

    @Autowired
    private SettingsController settingsController;

    @FXML
    private JFXListView<Player> userBallotListView;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private Label ballotCountLabel;

    //Used to implement custom countdown latch
    private int current;
    private int finished;


    //== CONSTRUCTOR ==
    @Autowired
    public UserBallotController(@Qualifier(SpringConstants.VARIABLE_TASK_EXECUTOR) ThreadPoolTaskExecutor taskExecutor,
                                    DataModel dataModel){
        this.dataModel = dataModel;
        this.taskExecutor = taskExecutor;
    }

    //== PUBLIC METHODS ==
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userBallotListView.setItems(dataModel.getBallotList());
        userBallotListView.setCellFactory(column -> getUserBallotCell());

        //Makes submit button disabled under the following conditions
        //1. Players Ballot is empty
        //2. Update Player Data is running
        //3. Submit Ballot is running
        submitButton.disableProperty().bind((Bindings.isEmpty(userBallotListView.getItems()))
                .or(dataModel.getIsUpdatePlayerDataRunning()).or(dataModel.getIsSubmitBallotRunning()));

        //Hides the submit button when Submit Ballot is running so it can be replaced with Cancel button
        submitButton.visibleProperty().bind(dataModel.getIsSubmitBallotRunning().not());
        submitButton.managedProperty().bind(dataModel.getIsSubmitBallotRunning().not());

        submitButton.setOnAction(event -> handleSubmitButtonClick());

        //Makes cancel button visible when Submit Ballot is running
        cancelButton.visibleProperty().bind(dataModel.getIsSubmitBallotRunning());
        cancelButton.managedProperty().bind(dataModel.getIsSubmitBallotRunning());

        //Disable cancel button after task is already in the process of being canceled
        cancelButton.disableProperty().bind(dataModel.getCancellingTask());

        cancelButton.setOnAction(event -> handleCancelButtonClick());

        //Makes settings button disabled when submitting ballots
        settingsButton.disableProperty().bind(dataModel.getIsSubmitBallotRunning());
        settingsButton.setOnAction(event -> handleSettingsButtonClick());

        //Binds the counter and formats the number so it is always 6 long
        ballotCountLabel.textProperty().bind(dataModel.getSuccessCount().asString("%06d"));
    }

    //== PRIVATE METHODS ==
    private void handleSettingsButtonClick(){
        settingsController.getSettingsModal().setDialogContainer(mainController.getMainContainerStackPane());
        settingsController.getSettingsModal().show();
    }

    private void handleSubmitButtonClick(){
        if(!dataModel.getBallotList().isEmpty()) {

            //Disable Buttons
            dataModel.getIsSubmitBallotRunning().set(true);

            //Reset the counter
            dataModel.getSuccessCount().set(0);

            //Setup executor and number of parallel threads based on user settings
            taskExecutor.setCorePoolSize(dataModel.getNumberOfBrowsers().get());
            taskExecutor.setMaxPoolSize(dataModel.getNumberOfBrowsers().get());

            //Submit Tasks
            current = 0;
            finished = dataModel.getNumberOfBrowsers().get();
            for (int i = 0; i < dataModel.getNumberOfBrowsers().get(); i++) {
                Task<Boolean> task = getTask();

                task.setOnSucceeded(event -> {
                    log.info("Submit Ballot Task Completed Success");
                    if(++current >= finished){
                        dataModel.getCancellingTask().set(false);
                        dataModel.getIsSubmitBallotRunning().set(false);
                        log.info("Successfully terminated tasks");
                    }
                });

                task.setOnFailed(event -> {
                    log.warn("Submit Ballot Task Failed");
                    if(current >= --finished){
                        dataModel.getCancellingTask().set(false);
                        dataModel.getIsSubmitBallotRunning().set(false);
                        log.info("Successfully terminated tasks");
                    }
                });

                taskExecutor.submit(task);
            }
        }
    }

    private Task<Boolean> getTask(){
        if(dataModel.getLightningMode().get())
            return getSubmitLightningBallot();
        else
            return getSubmitBallot();
    }

    private void handleCancelButtonClick(){
        dataModel.getCancellingTask().set(true);
    }

    //== SPRING LOOKUPS ==
    //Gets a new instance of UserBallotCell which is prototype scoped, ignore the null, this works with spring magic!
    @Lookup
    UserBallotCell getUserBallotCell(){
        return null;
    }

    @Lookup
    SubmitBallot getSubmitBallot(){
        return null;
    }

    @Lookup
    SubmitLightningBallot getSubmitLightningBallot(){ return null; }
}
