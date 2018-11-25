package com.lamonzo.pbb.model;

import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.PlayerTreeObject;
import com.lamonzo.pbb.domain.Position;
import com.lamonzo.pbb.service.PlayerService;
import com.lamonzo.pbb.service.PositionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String, ObservableList<PlayerTreeObject>> playerData;

    @Getter
    private ObservableList<Label> ballotList = FXCollections.observableArrayList();

    //== PUBLIC METHODS ==
    @PostConstruct
    private void fetchData(){
        //TODO: Add an if check here to make sure that the Data is in the DB, if not then we must scrape site

        playerData = new HashMap<>();
        for(Position position : positionService.getAllPositions()){
            ObservableList<PlayerTreeObject> players = FXCollections.observableArrayList();
            for(Player player : playerService.getPlayersByPosition(position)){
                PlayerTreeObject pto = new PlayerTreeObject(player);
                players.add(pto);
            }
            if(!playerData.containsKey(position.getPositionName()))
                playerData.put(position.getPositionName(), players);
        }
    }

    public ObservableList<PlayerTreeObject> getPlayerDataByPosition(String position){
        return playerData.get(position);
    }
}
