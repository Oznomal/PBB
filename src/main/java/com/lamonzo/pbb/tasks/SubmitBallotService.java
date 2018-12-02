package com.lamonzo.pbb.tasks;

import com.jfoenix.controls.JFXComboBox;
import com.lamonzo.pbb.constants.SpringConstants;
import com.lamonzo.pbb.controller.UserBallotController;
import com.lamonzo.pbb.model.DataModel;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SubmitBallotService extends Service<Void> {

    //== FIELDS ==
    private final DataModel dataModel;

    @Autowired
    @Lazy
    private UserBallotController userBallotController;

    @Autowired
    @Qualifier(SpringConstants.MULTI_TASK_EXECUTOR)
    private ThreadPoolTaskExecutor taskExecutor;

    private int successCount;

    //== CONSTRUCTORS ==
    @Autowired
    public SubmitBallotService(DataModel dataModel){
        this.dataModel = dataModel;
    }

    //== PUBLIC METHODS ==
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if(!dataModel.getBallotList().isEmpty()) {
                    final JFXComboBox<String> countSelector = userBallotController.getCountSelector();
                    final boolean unlimited;
                    final int votesToDo;

                    if(countSelector.getValue().equalsIgnoreCase("Unlimited")) {
                        unlimited = true;
                        votesToDo = taskExecutor.getMaxPoolSize();
                    }
                    else {
                        unlimited = false;
                        votesToDo = Integer.parseInt(countSelector.getValue());
                    }

                    for(int i = 0; i < votesToDo; i++){
                        scheduleTask(unlimited);
                    }
                }

                return null;
            }

            private void scheduleTask(final boolean unlimited){
                SubmitBallot task = getSubmitBallot();
                task.setOnSucceeded(event -> {
                    dataModel.getSuccessCount().set(++successCount);
                    log.info("Successfully Completed Task | Total Count: " + successCount);
                    if(unlimited)
                        scheduleTask(unlimited);
                });

                task.setOnFailed(event -> scheduleTask(unlimited));
                taskExecutor.submit(task);
            }
        };
    }

    //== SPRING LOOKUPS ==
    @Lookup
    SubmitBallot getSubmitBallot(){
        return null;
    }
}
