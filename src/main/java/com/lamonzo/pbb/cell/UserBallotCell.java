package com.lamonzo.pbb.cell;

import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.util.LogoUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class UserBallotCell extends ListCell<Player> {

    //================================================================================================================//
    //== FIELDS ==
    private final LogoUtil logoUtil;
    private HBox content;
    private VBox nameContent;
    private Label playerName;
    private Label playerPosition;
    private Region region;
    private ImageView teamLogo;

    //================================================================================================================//
    //== CONSTRUCTORS ==
    @Autowired
    public UserBallotCell(LogoUtil logoUtil){
        this.logoUtil = logoUtil;
    }

    @PostConstruct
    private void setupCellLayout(){
        //INIT COMPONENTS
        playerName = new Label();
        playerPosition = new Label();
        region = new Region();
        teamLogo = new ImageView();

        //CONFIGURE CONTAINERS
        nameContent = new VBox(playerName, playerPosition);
        nameContent.setAlignment(Pos.CENTER_LEFT);

        content = new HBox(nameContent, region, teamLogo);
        content.setPrefHeight(50);
        content.setAlignment(Pos.CENTER_LEFT);

        //REGION SEPARATING LEFT AND RIGHT CONTENT
        HBox.setHgrow(region, Priority.ALWAYS);

        //TEAM LOGO SETTINGS
        teamLogo.setPreserveRatio(true);
        teamLogo.setFitWidth(40);
        teamLogo.setFitHeight(40);

        //ADD STYLES
        playerPosition.getStyleClass().add("position-label");
        playerName.getStyleClass().add("player-name-label");
    }

    //================================================================================================================//
    //== PROTECTED METHODS ==
    @Override
    protected void updateItem(Player item, boolean empty) {
        super.updateItem(item, empty);
        setPrefHeight(50);
        if(item != null && !empty){
            playerName.setText(item.getName());
            playerPosition.setText(item.getPosition().getPositionName());
            teamLogo.setImage(logoUtil.getTeamLogo(item.getTeam()));
            setGraphic(content);
        }else{
            setGraphic(null);
        }
    }
}
