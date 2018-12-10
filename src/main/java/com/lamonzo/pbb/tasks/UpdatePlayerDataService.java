package com.lamonzo.pbb.tasks;

import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.service.PlayerService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UpdatePlayerDataService extends Service<Void> {

    //== FIELDS ==
    private final ThreadPoolTaskExecutor taskExecutor;
    private final DataModel dataModel;
    private final PlayerService playerService;

    private int successCount;
    private int finishCount;

    //== CONSTRUCTORS ==
    @Autowired
    public UpdatePlayerDataService(DataModel dataModel, PlayerService playerService,
            @Qualifier(SpringConstants.VARIABLE_TASK_EXECUTOR) ThreadPoolTaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
        this.dataModel = dataModel;
        this.playerService = playerService;
    }


    //== PROTECTED FIELDS ==
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                //User to bind these inner tasks to buttons
                dataModel.getIsUpdatePlayerDataRunning().set(true);

                //Going to force this process to run on half of the available processors, regardless
                //of the users settings because it is time consuming ... but using all of the processors
                //is known to throw timeout errors at times, which is fine when submitting ballots but not ok here
                //where we want to ensure ALL of the data is captured.
                int maxThreads = Runtime.getRuntime().availableProcessors() / 2;
                int poolCap = maxThreads < 4 ? maxThreads : 4;

                taskExecutor.setMaxPoolSize(poolCap);
                taskExecutor.setCorePoolSize(poolCap);

                //Each item in the list is the list of position tabs a thread will be responsible for processing
                //this algorithm divides the positions tabs up for the threads
                int idx = 0;
                List<List<String>> threadResponsibilitiesList = new ArrayList<>(poolCap);
                for (String tabHtml : ScrapingConstants.ALL_POSITION_TAB_LINKS) {
                    if (idx >= poolCap)
                        idx = 0;

                    if (threadResponsibilitiesList.size() < poolCap) {
                        List<String> listToAdd = new ArrayList<>();
                        listToAdd.add(tabHtml);
                        threadResponsibilitiesList.add(listToAdd);
                    } else {
                        threadResponsibilitiesList.get(idx++).add(tabHtml);
                    }
                }

                //Empty the players from the DB for the new players
                playerService.deleteAllPlayers();

                //Process the lists to be updated
                successCount = 0;
                finishCount = threadResponsibilitiesList.size();
                for (List<String> responsibilities : threadResponsibilitiesList) {
                    UpdatePlayerData task = getUpdatePlayerData(responsibilities);

                    task.setOnSucceeded(e -> {
                        if(task.getValue()){
                            log.info("Update Player Data Thread Completed | Count: " + (++successCount));
                        }else{
                            log.info("Update Player Data Thread Failed | Finish Count: " + (--finishCount));
                        }

                        if (successCount == finishCount) {
                            dataModel.refreshTableData(true);
                            dataModel.getIsUpdatePlayerDataRunning().set(false);

                        }
                    });


                    task.setOnFailed(e -> {
                        log.error("Something went wrong with the task submission | Finish Count: " +  --finishCount);

                        if (successCount == finishCount) {
                            dataModel.refreshTableData(true);
                            dataModel.getIsUpdatePlayerDataRunning().set(false);
                        }
                    });

                    taskExecutor.submit(task);
                }
                return null;
            }
        };
    }

    //== SPRING LOOKUPS ==
    @Lookup
    UpdatePlayerData getUpdatePlayerData(List<String> responsibilities){
        return null;
    }
}
