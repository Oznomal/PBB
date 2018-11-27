package com.lamonzo.pbb.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.lamonzo.pbb.cell.UserBallotCell;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.tasks.SubmitBallot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class UserBallotController implements Initializable {

    //== FIELDS ==
    private final DataModel dataModel;

    @Autowired
    @Qualifier(SpringConstants.SINGLE_TASK_EXECUTOR)
    private TaskExecutor taskExecutor;

    @FXML
    private JFXListView<Player> userBallotListView;

    @FXML
    private JFXButton submitButton;


    //== CONSTRUCTOR ==
    @Autowired
    public UserBallotController(DataModel dataModel){
        this.dataModel = dataModel;
    }

    //== PUBLIC METHODS ==
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userBallotListView.setItems(dataModel.getBallotList());
        userBallotListView.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>(){
            @Override
            public ListCell<Player> call(ListView<Player> param) {
                return getUserBallotCell();
            }
        });

        submitButton.setOnAction(event -> handleSubmitButtonClick());
    }

    //== PRIVATE METHODS ==
    public void handleSubmitButtonClick(){
        if(!dataModel.getBallotList().isEmpty()) {
            taskExecutor.execute(getSubmitBallot());
        }
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
}
