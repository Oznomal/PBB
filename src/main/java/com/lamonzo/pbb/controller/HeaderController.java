package com.lamonzo.pbb.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Slf4j
public class HeaderController implements Initializable {

    //== FIELDS ==
    @FXML
    private JFXButton minButton;

    @FXML
    private JFXButton closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Image minButtonImg = new Image(new FileInputStream("src\\main\\resources\\img\\icons\\Minimize_Icon1.png"));
            Image closeButtonImg = new Image(new FileInputStream("src\\main\\resources\\img\\icons\\Close_Icon1.png"));
            minButton.setGraphic(new ImageView(minButtonImg));
            closeButton.setGraphic(new ImageView(closeButtonImg));

            minButton.setOnAction(e -> handleMinimizeButtonClick(e));
            closeButton.setOnAction(e -> handleCloseButtonClick(e));

        }catch(FileNotFoundException e) {
            log.error("Unable to find decorator tray image | " + e.getMessage());
        }
    }

    private void handleMinimizeButtonClick(Event event){
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    private void handleCloseButtonClick(Event event){
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
