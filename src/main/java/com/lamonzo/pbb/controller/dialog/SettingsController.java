package com.lamonzo.pbb.controller.dialog;

import com.jfoenix.controls.*;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.model.DataModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Toggle;
import javafx.util.StringConverter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Getter
public class SettingsController implements Initializable {

    private final ThreadPoolTaskExecutor taskExecutor;
    private final DataModel dataModel;
    private final String ON = "ON";
    private final String OFF = "OFF";

    @FXML
    private JFXDialog settingsModal;

    @FXML
    private JFXDialogLayout dialogLayout;

    @FXML
    private JFXSlider threadSlider;

    @FXML
    private JFXSlider voteGoalSlider;

    @FXML
    private JFXToggleButton autoFillToggle;

    @FXML
    private JFXToggleButton showVotingToggle;

    @FXML
    private JFXToggleButton rotateProxiesToggle;

    @FXML
    private JFXButton updatePlayerDataButton;

    //== CONSTRUCTORS ==
    @Autowired
    public SettingsController(DataModel dataModel,
            @Qualifier(SpringConstants.MULTI_TASK_EXECUTOR) ThreadPoolTaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
        this.dataModel = dataModel;
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
                            return("10");
                        else if(value < 2.50)
                            return ("100");
                        else if(value < 3.50)
                            return "500";
                        else if(value < 4.50)
                            return "1000";
                        else
                            return "\u221e";

                }, voteGoalSlider.valueProperty())
        );

        voteGoalSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 1.5) return "10";
                if (n < 2.5) return "100";
                if (n < 3.5) return "500";
                if (n < 4.5) return "1000";

                return "\u221e";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "10":
                        return 1d;
                    case "100":
                        return 2d;
                    case "500":
                        return 3d;
                    case "1000":
                        return 4d;
                    case "\u221e":
                        return 5d;

                    default:
                        return 1d;
                }
            }
        });

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
        autoFillToggle.selectedProperty().bindBidirectional(dataModel.getIsAutoFill());
        showVotingToggle.selectedProperty().bindBidirectional(dataModel.getShowBrowser());
        rotateProxiesToggle.selectedProperty().bindBidirectional(dataModel.getRotateProxies());
    }

    //PUBLIC METHODS
    public void handleCancelButtonClick(){
        settingsModal.close();
        dataModel.createObservableSettings();
    }

    public void handleSaveButtonClick(){
        settingsModal.close();
        dataModel.updateSettings();
    }
}
