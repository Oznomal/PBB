package com.lamonzo.pbb;


import com.lamonzo.pbb.controller.SplashScreenController;
import com.lamonzo.pbb.domain.Settings;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.service.PlayerService;
import com.lamonzo.pbb.service.SettingsService;
import com.lamonzo.pbb.tasks.UpdatePlayerDataService;
import javafx.application.Application;
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

//import org.scenicview.ScenicView;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Random;

@SpringBootApplication
@Slf4j
public class Main extends Application {

    //================================================================================================================//
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

    private FXMLLoader splashLoader = null;
    private SplashScreenController splashController = null;

    private double progress = 0.0;
    private double maxProgress = 11.0;


    //================================================================================================================//
    //== PUBLIC METHODS ==
    public static void main(String [] Args){
        Application.launch(Args);
    }

    //================================================================================================================//
    //== INIT ==
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));

        //Grab Spring Beans that Are needed Here
        playerService = context.getBean(PlayerService.class);
        settingsService = context.getBean(SettingsService.class);
        updatePlayerDataService = context.getBean(UpdatePlayerDataService.class);
        dataModel = context.getBean(DataModel.class);

        splashLoader = new FXMLLoader(getClass().getResource("/fxml/splash/SplashScreen.fxml"));
        splashNode = splashLoader.load();

        splashController = splashLoader.getController();
        splashController.getSplashProgressBar().progressProperty().bind(dataModel.getLoadingProgress());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/containers/MainContainer.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }

    //================================================================================================================//
    //== PUBLIC METHODS ==
    @Override
    public void start(Stage initStage) throws Exception {

        showSplash(initStage);

        Task task = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                Random random = new Random();
                for(int i = 0; i < maxProgress+1; i++){
                    Thread.sleep(random.nextInt(250));
                        double value = progress++ / maxProgress;
                        dataModel.getLoadingProgress().set(value);
                }
                return null;
            }
        };

        task.setOnSucceeded(e -> fetchData(initStage));
        Thread t = new Thread(task);
        t.start();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }

    //================================================================================================================//
    //== PRIVATE METHODS ==
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
        //ScenicView.show(rootNode);

    }

    private void fetchData(Stage initStage){
        if(playerService.getPlayerCount() != 0) {
            //GET USER SETTINGS FROM THE DB OR CREATE DEFAULT SETTINGS IF NONE EXIST
            Settings settings = settingsService.getSettings();
            if (settings == null) {
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
        }
        else{
            updatePlayerDataService.start();
        }

        dataModel.getLoadingProgress().set(1.0);
        showMain();
        initStage.hide();
    }
}