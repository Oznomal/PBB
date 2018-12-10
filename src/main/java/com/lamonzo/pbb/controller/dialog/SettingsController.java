package com.lamonzo.pbb.controller.dialog;

import com.jfoenix.controls.*;
import com.lamonzo.pbb.cell.UserBallotCell;
import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.service.PlayerService;
import com.lamonzo.pbb.tasks.UpdatePlayerData;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@Getter
@Slf4j
public class SettingsController implements Initializable {

    private final ThreadPoolTaskExecutor taskExecutor;
    private final DataModel dataModel;
    private final PlayerService playerService;
    private final String ON = "ON";
    private final String OFF = "OFF";

    private final String TEN = "10";
    private final String ONE_HUNDRED = "100";
    private final String FIVE_HUNDRED = "500";
    private final String ONE_THOUSAND = "1000";
    private final String UNLIMITED = "\u221e";

    @FXML
    private JFXDialog settingsModal;

    @FXML
    private JFXDialogLayout dialogLayout;

    @FXML
    private JFXSlider threadSlider;

    @FXML
    private JFXSlider voteGoalSlider;

    @FXML
    private JFXToggleButton lightningModeToggle;

    @FXML
    private JFXToggleButton autoFillToggle;

    @FXML
    private JFXToggleButton showVotingToggle;

    @FXML
    private JFXToggleButton rotateProxiesToggle;

    @FXML
    private JFXButton updatePlayerDataButton;

    private int successCount;

    //== CONSTRUCTORS ==
    @Autowired
    public SettingsController(DataModel dataModel,PlayerService playerService,
            @Qualifier(SpringConstants.VARIABLE_TASK_EXECUTOR) ThreadPoolTaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
        this.dataModel = dataModel;
        this.playerService = playerService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Number of Threads Slider
        threadSlider.setMin(1);
        threadSlider.setMax(Runtime.getRuntime().availableProcessors());

        //Number of Votes Slider
        voteGoalSlider.setValueFactory(event ->
                Bindings.createStringBinding(() -> {
                        double value = voteGoalSlider.getValue();
                        if(value < 1.50)
                            return(TEN);
                        else if(value < 2.50)
                            return (ONE_HUNDRED);
                        else if(value < 3.50)
                            return FIVE_HUNDRED;
                        else if(value < 4.50)
                            return ONE_THOUSAND;
                        else
                            return UNLIMITED;

                }, voteGoalSlider.valueProperty())
        );

        voteGoalSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 1.5) return TEN;
                if (n < 2.5) return ONE_HUNDRED;
                if (n < 3.5) return FIVE_HUNDRED;
                if (n < 4.5) return ONE_THOUSAND;

                return UNLIMITED;
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case TEN:
                        return 1d;
                    case ONE_HUNDRED:
                        return 2d;
                    case FIVE_HUNDRED:
                        return 3d;
                    case ONE_THOUSAND:
                        return 4d;
                    case UNLIMITED:
                        return 5d;

                    default:
                        return 1d;
                }
            }
        });

        //Lightning Mode Toggle Button
        lightningModeToggle.textProperty()
                .bind(Bindings.when(lightningModeToggle.selectedProperty()).then(ON).otherwise(OFF));

        //Auto-Fill Toggle Button
        autoFillToggle.textProperty()
                .bind(Bindings.when(autoFillToggle.selectedProperty()).then(ON).otherwise(OFF));

        //Show Voting Toggle Button
        showVotingToggle.textProperty()
                .bind(Bindings.when(showVotingToggle.selectedProperty()).then(ON).otherwise(OFF));

        //Rotate Proxies Toggle Button
        rotateProxiesToggle.textProperty()
                .bind(Bindings.when(rotateProxiesToggle.selectedProperty()).then(ON).otherwise(OFF));


        //Binding Values to DataModel
        threadSlider.valueProperty().bindBidirectional(dataModel.getNumberOfBrowsers());
        voteGoalSlider.valueProperty().bindBidirectional(dataModel.getVotingGoals());
        lightningModeToggle.selectedProperty().bindBidirectional(dataModel.getLightningMode());
        autoFillToggle.selectedProperty().bindBidirectional(dataModel.getIsAutoFill());
        showVotingToggle.selectedProperty().bindBidirectional(dataModel.getShowBrowser());
        rotateProxiesToggle.selectedProperty().bindBidirectional(dataModel.getRotateProxies());

    }

    //PUBLIC METHODS
    public void handleUpdatePlayerDataButtonClick(){

        //Going to force this process to run on half of the available processors, regardless
        //of the users settings because it is time consuming ... but using all of the processors
        //is known to throw timeout errors at times, which is fine when submitting ballots but not ok here
        //where we want to ensure ALL of the data is captured.
        int maxThreads = Runtime.getRuntime().availableProcessors() / 2;
        int poolCap = maxThreads < 4 ? maxThreads : 4;

        taskExecutor.setMaxPoolSize(poolCap);
        taskExecutor.setCorePoolSize(poolCap);

        //Each item in the list is the list of position tabs a thread will be responsible for processing
        //this algorithm divides the positions tabs up for the threads
        int idx = 0;
        List<List<String>> threadResponsibilitiesList = new ArrayList<>(poolCap);
        for(String tabHtml : ScrapingConstants.ALL_POSITION_TAB_LINKS){
            if(idx >= poolCap)
                idx = 0;

            if(threadResponsibilitiesList.size() < poolCap) {
                List<String> listToAdd = new ArrayList<>();
                listToAdd.add(tabHtml);
                threadResponsibilitiesList.add(listToAdd);
            }else{
                threadResponsibilitiesList.get(idx++).add(tabHtml);
            }
        }

        //Empty the players from the DB for the new players
        playerService.deleteAllPlayers();

        //Process the lists to be updated
        successCount = 0;
        for(List<String> responsibilities : threadResponsibilitiesList){
            UpdatePlayerData task = getUpdatePlayerData(responsibilities);
            task.setOnSucceeded(event -> {
                log.info("Thread Completed | Count: " + (++successCount));

                if(successCount == threadResponsibilitiesList.size()){
                    dataModel.refreshTableData(true);
                }
            });

            task.setOnFailed(event -> {
                log.error("Something went wrong with the task submission");
            });

            taskExecutor.submit(task);
        }
    }

    public void handleCancelButtonClick(){
        settingsModal.close();
        dataModel.createObservableSettings();
    }

    public void handleSaveButtonClick(){
        settingsModal.close();
        dataModel.updateSettings();
    }

    //== SPRING LOOKUPS ==
    @Lookup
    UpdatePlayerData getUpdatePlayerData(List<String> responsibilities){
        return null;
    }
}
