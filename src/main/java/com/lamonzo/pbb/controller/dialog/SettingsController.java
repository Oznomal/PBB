package com.lamonzo.pbb.controller.dialog;

import com.jfoenix.controls.*;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.model.DataModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
            @Qualifier(SpringConstants.VARIABLE_TASK_EXECUTOR) ThreadPoolTaskExecutor taskExecutor){
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
