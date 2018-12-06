package com.lamonzo.pbb.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MainController {

    @FXML
    private StackPane mainContainerStackPane;
}
