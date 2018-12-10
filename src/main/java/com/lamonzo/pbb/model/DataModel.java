package com.lamonzo.pbb.model;

import com.lamonzo.pbb.controller.tab.BaseTabController;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.PlayerTreeObject;
import com.lamonzo.pbb.domain.Position;
import com.lamonzo.pbb.domain.Settings;
import com.lamonzo.pbb.service.PlayerService;
import com.lamonzo.pbb.service.PositionService;
import com.lamonzo.pbb.service.SettingsService;
import com.lamonzo.pbb.tasks.UpdatePlayerDataService;
import com.lamonzo.pbb.util.ControllerUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.*;

/**
 * The purpose of the data model layer is to act as the primary cache for the application.
 * Since the amount of data within the DB is relatively small I would like to store most
 * of it in memory when possible to limit the amount of calls to the DB. The service layers
 * will still be used to communicate directly with the repos.
 */
@Component
public class DataModel implements Initializable {

    //== FIELDS ==
    @Autowired
    private PositionService positionService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private ControllerUtil controllerUtil;

    @Autowired
    private UpdatePlayerDataService updatePlayerDataService;

    @Getter
    private Map<String, SimpleStringProperty> positionVoteMap;

    @Getter
    private ObservableList<Player> ballotList = FXCollections.observableArrayList();

    private Map<String, ObservableList<PlayerTreeObject>> playerTreeObjectData;
    private Map<String, List<Player>> playerData;
    private Map<String, Position> positionMap;

    @Getter
    private SimpleIntegerProperty successCount = new SimpleIntegerProperty(0);

    private Settings settings;

    @Getter
    private SimpleIntegerProperty votingGoals = new SimpleIntegerProperty();

    @Getter
    private SimpleIntegerProperty numberOfBrowsers = new SimpleIntegerProperty();

    @Getter
    private SimpleBooleanProperty isAutoFill = new SimpleBooleanProperty();

    @Getter
    private SimpleBooleanProperty showBrowser = new SimpleBooleanProperty();

    @Getter
    private SimpleBooleanProperty rotateProxies = new SimpleBooleanProperty();

    @Getter
    private SimpleBooleanProperty lightningMode = new SimpleBooleanProperty();

    @Getter
    private SimpleBooleanProperty isUpdatePlayerDataRunning = new SimpleBooleanProperty();

    //== PUBLIC METHODS ==
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Gets the data form the DB if it exists or scrapes the data form the website
    @PostConstruct
    private void fetchData(){
        if(playerService.getPlayerCount() != 0) {
            //GET USER SETTINGS FROM THE DB OR CREATE DEFAULT SETTINGS IF NONE EXIST
            settings = settingsService.getSettings();
            if (settings == null) {
                System.out.println("Settings is Null, Adding them");
                settings = new Settings();
                settings.setNumberOfBrowsers(Runtime.getRuntime().availableProcessors() / 2);
                settings.setVotingGoals(1);
                settings.setLightningMode(false);
                settings.setShowBrowser(false);
                settings.setAutoFill(true);
                settings.setRotateProxies(false);
                settingsService.saveSettings(settings);
            }

            createObservableSettings();
            refreshTableData(false);
        }
        else{
            positionMap = new HashMap<>();
            playerTreeObjectData = new HashMap<>();
            positionVoteMap = new HashMap<>();
            playerData = new HashMap<>();
            updatePlayerDataService.start();
        }
    }

    public void refreshTableData(boolean rebuildTableViews){
        //GET PLAYER DATA FROM THE DATABASE AND CACHE IT
        Iterable<Position> positions;

        //Only make the call to the DB for positions if we don't already have them
        if(positionMap == null || positionMap.isEmpty()) {
            positionMap = new HashMap<>();
            positions = positionService.getAllPositions();
        }
        else
            positions = positionMap.values();

        playerTreeObjectData = new HashMap<>();
        positionVoteMap = new HashMap<>();
        playerData = new HashMap<>();
        for(Position position : positions){
            ObservableList<PlayerTreeObject> ptoList = FXCollections.observableArrayList();
            List<Player> playerList = new ArrayList<>();
            for(Player player : playerService.getPlayersByPosition(position)){
                playerList.add(player);

                PlayerTreeObject pto = new PlayerTreeObject(player);
                ptoList.add(pto);
            }
            playerTreeObjectData.put(position.getPositionName(), ptoList);
            positionVoteMap.put(position.getPositionName(), new SimpleStringProperty("0"));
            positionMap.put(position.getPositionName(), position);
            playerData.put(position.getPositionName(), playerList);
        }

        if(rebuildTableViews){
            //Remove players from ballot list in case they no longer exist
            ballotList.clear();

            for(BaseTabController controller : controllerUtil.getControllers())
                controller.buildTreeTable();
        }
    }

    public ObservableList<PlayerTreeObject> getPlayerDataByPosition(String position){
        return playerTreeObjectData.get(position);
    }

    public List<Player> getPlayersByPosition(String position){
        return playerData.get(position);
    }

    public Position getPositionByName(String positionName){
        return positionMap.get(positionName);
    }

    //Using Linked List here because at the time, this method is mostly used by
    //the SubmitBallot task when the remainingPositions variable uses this method
    //to initialize itself, after that, items are removed from the list multiple
    //times and the get by index is only accessed once ... thus LinkedList should
    //offer better performance for the multiple O(1) removals vs the few O(n) retrievals
    public List<Position> getAllPositions(){
        return new LinkedList<>(positionMap.values());
    }


    //In order to ensure that the settings object only has one instance (1 row in table),
    //I am forcing all settings transactions to go through the data model and choosing
    //not to expose the actual Settings Object, this will make sure that the data model is
    //always up to date with the settings and eliminate the need to constantly go back
    //and forth to the DB
    public void createObservableSettings(){
        votingGoals.set(settings.getVotingGoals());
        numberOfBrowsers.set(settings.getNumberOfBrowsers());
        lightningMode.set(settings.isLightningMode());
        isAutoFill.set(settings.isAutoFill());
        showBrowser.set(settings.isShowBrowser());
        rotateProxies.set(settings.isRotateProxies());
    }

    public void updateSettings(){
        settings.setNumberOfBrowsers(numberOfBrowsers.get());
        settings.setVotingGoals(votingGoals.get());
        settings.setLightningMode(lightningMode.get());
        settings.setShowBrowser(showBrowser.get());
        settings.setAutoFill(isAutoFill.get());
        settings.setRotateProxies(rotateProxies.get());
        settingsService.saveSettings(settings);
    }

    //Thread safe way to update the counter which is used by multiple threads
    public synchronized int incrementSuccessCount(){
        int newValue = successCount.get() + 1;
        Platform.runLater(() -> successCount.set(newValue));
        return newValue;
    }
}
