package com.lamonzo.pbb.domain;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerTreeObject extends RecursiveTreeObject<PlayerTreeObject> {

    //================================================================================================================//
    //== FIELDS ==
    private final Player player;
    private final StringProperty name;
    private final StringProperty position;
    private final StringProperty team;
    private BooleanProperty isSelected;

    //================================================================================================================//
    //== CONSTRUCTOR ==
    public PlayerTreeObject(Player player){
        this.player = player;
        this.name = new SimpleStringProperty(player.getName());
        this.position = new SimpleStringProperty(player.getPosition().getPositionName());
        this.team = new SimpleStringProperty(player.getTeam());
        this.isSelected = new SimpleBooleanProperty(false);
    }
}