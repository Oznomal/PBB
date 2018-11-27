package com.lamonzo.pbb.model;

import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.PlayerTreeObject;
import com.lamonzo.pbb.domain.Position;
import com.lamonzo.pbb.service.PlayerService;
import com.lamonzo.pbb.service.PositionService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * The purpose of the data model layer is to act as the primary cache for the application.
 * Since the amount of data within the DB is relatively small I would like to store most
 * of it in memory when possible to limit the amount of calls to the DB. The service layers
 * will still be used to communicate directly with the repos.
 */
@Component
public class DataModel {

    //== FIELDS ==
    @Autowired
    private PositionService positionService;

    @Autowired
    private PlayerService playerService;

    @Getter
    private Map<String, SimpleStringProperty> positionVoteMap;

    @Getter
    private ObservableList<Player> ballotList = FXCollections.observableArrayList();

    private Map<String, ObservableList<PlayerTreeObject>> playerTreeObjectData;
    private Map<String, List<Player>> playerData;
    private Map<String, Position> positionMap;



    //== PUBLIC METHODS ==
    @PostConstruct
    private void fetchData(){
        //TODO: Add an if check here to make sure that the Data is in the DB, if not then we must scrape site

        System.out.println("Fetch Data is Called");
        playerTreeObjectData = new HashMap<>();
        positionVoteMap = new HashMap<>();
        positionMap = new HashMap<>();
        playerData = new HashMap<>();
        for(Position position : positionService.getAllPositions()){
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
}
