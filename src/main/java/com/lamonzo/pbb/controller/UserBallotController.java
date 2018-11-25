package com.lamonzo.pbb.controller;

import com.jfoenix.controls.JFXListView;
import com.lamonzo.pbb.cell.UserBallotCell;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.Position;
import com.lamonzo.pbb.model.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class UserBallotController implements Initializable {

    //== FIELDS ==
    private DataModel dataModel;

    @FXML
    private JFXListView<Player> userBallotListView;


    //== CONSTRUCTOR ==
    @Autowired
    public UserBallotController(DataModel dataModel){
        this.dataModel = dataModel;
    }

    //== PUBLIC METHODS ==
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //userBallotListView.setItems(dataModel.getBallotList());
        Player test = new Player();
        test.setName("Lamonzo Arroyo");
        test.setPosition(new Position("Developer", 1));
        test.setTeam("ATL");

        ObservableList<Player> testList = FXCollections.observableArrayList();
        testList.add(test);

        userBallotListView.setItems(testList);

        userBallotListView.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>(){
            @Override
            public ListCell<Player> call(ListView<Player> param) {
                return getUserBallotCell();
            }
        });
    }

    //== PRIVATE METHODS ==
    //Gets a new instance of UserBallotCell which is prototype scoped, ignore the null, this works with spring magic!
    @Lookup
    UserBallotCell getUserBallotCell(){
        return null;
    }
}
