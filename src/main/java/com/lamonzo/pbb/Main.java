package com.lamonzo.pbb;


import com.lamonzo.pbb.domain.Settings;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.service.PlayerService;
import com.lamonzo.pbb.service.SettingsService;
import com.lamonzo.pbb.tasks.UpdatePlayerDataService;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;

//import org.scenicview.ScenicView;

@SpringBootApplication
@Slf4j
public class Main extends Application {

    //== FIELDS ==
    private PlayerService playerService = null;
    private SettingsService settingsService = null;
    private UpdatePlayerDataService updatePlayerDataService = null;
    private DataModel dataModel = null;

    private ConfigurableApplicationContext context;
    private Parent rootNode;
    private Parent splashNode;

    private double xOffset = 0;
    private double yOffset = 0;

    //== PUBLIC METHODS ==
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));

        //Grab Spring Beans that Are needed Here
        playerService = context.getBean(PlayerService.class);
        settingsService = context.getBean(SettingsService.class);
        updatePlayerDataService = context.getBean(UpdatePlayerDataService.class);
        dataModel = context.getBean(DataModel.class);

        FXMLLoader splashLoader = new FXMLLoader(getClass().getResource("/fxml/splash/SplashScreen.fxml"));
        splashNode = splashLoader.load();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/containers/MainContainer.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }

    @Override
    public void start(Stage initStage) throws Exception {
        showSplash(initStage);

        Task task = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                Thread.sleep(5000);
                return null;
            }
        };

        task.setOnSucceeded(e -> fetchData(initStage));
        //fetchData(initStage);
        Thread t = new Thread(task);
        t.start();


        //ScenicView.show(splashNode);
    }

    private void showSplash(Stage initStage){
        initStage.setScene(new Scene(splashNode, 570.0, 325.0));
        initStage.centerOnScreen();
        initStage.setResizable(false);
        initStage.initStyle(StageStyle.UNDECORATED);
        initStage.show();
    }

    private void showMain(){
        Stage primaryStage = new Stage(StageStyle.DECORATED.UNDECORATED);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        rootNode.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        rootNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(new Scene(rootNode, 1500, 1000, Color.TRANSPARENT));
        primaryStage.show();
    }

    private void fetchData(Stage initStage){
        dataModel.getIsFetchDataRunning().set(true);
        dataModel.getIsFetchDataRunning().addListener((observable, oldValue, newValue) -> {
                if(oldValue == true && newValue == false){
                    System.out.println("Showing Main Screen Now");
                    initStage.hide();
                    showMain();
                }
        });

        if(playerService.getPlayerCount() != 0) {
            //GET USER SETTINGS FROM THE DB OR CREATE DEFAULT SETTINGS IF NONE EXIST
            Settings settings = settingsService.getSettings();
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

            dataModel.setSettings(settings);
            dataModel.createObservableSettings();
            dataModel.refreshTableData(true);
            dataModel.getIsFetchDataRunning().set(false);
        }
        else{
            updatePlayerDataService.start();
        }
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}