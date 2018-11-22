package com.lamonzo.pbb;

import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.tasks.UpdatePlayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * This class is used as the entry point for the application
 * and the run method is immediately invoked after Spring
 * starts up.
 */
@Component
public class ApplicationEntryPoint implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("singleCoreTaskExecutor")
    private TaskExecutor taskExecutor;

    @Override
    public void run(String... args) throws Exception {
//        Iterator<String> iterator = ScrapingConstants.ALL_POSITION_TAB_LINKS.iterator();
//        while(iterator.hasNext()){
//            taskExecutor.execute(applicationContext.getBean(UpdatePlayerData.class, iterator.next()));
//        }

        //taskExecutor.execute(applicationContext.getBean(UpdatePlayerData.class, ScrapingConstants.QUARTERBACK_TAB_LINK));
    }
}
