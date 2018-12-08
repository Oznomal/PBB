package com.lamonzo.pbb.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.lamonzo.pbb.cell.UserBallotCell;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.controller.dialog.SettingsController;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.tasks.SubmitBallot;
import com.lamonzo.pbb.tasks.SubmitBallotService;
import com.lamonzo.pbb.tasks.SubmitLightningBallot;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Slf4j
public class UserBallotController implements Initializable {

    //== FIELDS ==
    private final DataModel dataModel;
    private final SubmitBallotService submitBallotService;
    private final ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private MainController mainController;

    @Autowired
    @Lazy
    private UserBallotController userBallotController;

    @Autowired
    private SettingsController settingsController;

    @FXML
    private JFXListView<Player> userBallotListView;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private Label ballotCountLabel;

    @FXML
    @Getter
    private JFXComboBox<String> countSelector;

    private int successCount;


    //== CONSTRUCTOR ==
    @Autowired
    public UserBallotController(DataModel dataModel, SubmitBallotService submitBallotService,
                                @Qualifier(SpringConstants.VARIABLE_TASK_EXECUTOR) ThreadPoolTaskExecutor taskExecutor){
        this.dataModel = dataModel;
        this.submitBallotService = submitBallotService;
        this.taskExecutor = taskExecutor;
    }

    //== PUBLIC METHODS ==
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userBallotListView.setItems(dataModel.getBallotList());
        userBallotListView.setCellFactory(column -> getUserBallotCell());

        //Makes submit only clickable when count is selected and at least 1 player is added
        submitButton.disableProperty().bind((Bindings.isEmpty(userBallotListView.getItems()))
                .or(submitBallotService.runningProperty()));
        submitButton.setOnAction(event -> handleSubmitButtonClick());

        //Binds the counter and formats the number so it is always 6 long
        ballotCountLabel.textProperty().bind(dataModel.getSuccessCount().asString("%06d"));
    }

    //== PRIVATE METHODS ==
    public void handleSettingsButtonClick(){
        settingsController.getSettingsModal().setDialogContainer(mainController.getMainContainerStackPane());
        settingsController.getSettingsModal().show();
    }

    public void handleSubmitButtonClick(){
        if(!dataModel.getBallotList().isEmpty()) {

            final boolean unlimited;
            final int votesToDo;

            //Determine the number of votes to do based on user settings
            double value = dataModel.getVotingGoals().get();
            String voteSliderString = settingsController.getVoteGoalSlider().getLabelFormatter().toString(value).trim();

            if (voteSliderString.equalsIgnoreCase(settingsController.getUNLIMITED())) {
                System.out.println("Set to Unlimited Lets GO");
                unlimited = true;
                votesToDo = dataModel.getNumberOfBrowsers().get();
            } else {
                System.out.println("Set to " + voteSliderString);
                unlimited = false;
                votesToDo = Integer.parseInt(voteSliderString);
            }

            //Setup executor and number of parallel threads based on user settings
            taskExecutor.setCorePoolSize(dataModel.getNumberOfBrowsers().get());
            taskExecutor.setMaxPoolSize(dataModel.getNumberOfBrowsers().get());

            //Submit Tasks
            for (int i = 0; i < dataModel.getNumberOfBrowsers().get(); i++) {
                scheduleTask(unlimited);
            }
        }
    }

    private void scheduleTask(final boolean unlimited){
        Task<Boolean> task;

        if(dataModel.getLightningMode().get())
            task = getSubmitLightningBallot();
        else
            task = getSubmitBallot();


        //SubmitBallot task = getSubmitBallot();
        task.setOnSucceeded(event -> {
            //dataModel.getSuccessCount().set(++successCount);
            log.info("Successfully Completed Task | Total Count: " + successCount);

//            if(unlimited) {
//                scheduleTask(unlimited);
//            }
        });

        task.setOnFailed(event -> {
            log.info("Task Failed");
            //scheduleTask(unlimited);
        });
        taskExecutor.submit(task);
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
