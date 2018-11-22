package com.lamonzo.pbb;

import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.tasks.UpdatePlayerData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;

import java.util.Iterator;

@SpringBootApplication
@Slf4j
public class Main extends Application {

    //== FIELDS ==
    private TaskExecutor taskExecutor;

    private ConfigurableApplicationContext context;
    private Parent rootNode;

    //== PUBLIC METHODS ==
    //APPLICATION ENTRY POINT
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //importPlayerData();

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double width = visualBounds.getWidth();
        double height = visualBounds.getHeight();

        primaryStage.setScene(new Scene(rootNode, width, height));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }

    //== PRIVATE METHODS ==
    //Scrapes the Data used in the application from NFL.com
    public void importPlayerData(){
        taskExecutor = context.getBean(SpringConstants.SINGLE_TASK_EXECUTOR, TaskExecutor.class);
        Iterator<String> iterator = ScrapingConstants.ALL_POSITION_TAB_LINKS.iterator();
        while(iterator.hasNext()){
            taskExecutor.execute(context.getBean(UpdatePlayerData.class, iterator.next()));
        }
    }
}