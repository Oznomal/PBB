package com.lamonzo.pbb;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

//import org.scenicview.ScenicView;

@SpringBootApplication
@Slf4j
public class Main extends Application {

    //== FIELDS ==
    private ConfigurableApplicationContext context;
    private Parent rootNode;

    //== PUBLIC METHODS ==
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/containers/MainContainer.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(rootNode, 1500, 1000));
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
        //ScenicView.show(rootNode);
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}