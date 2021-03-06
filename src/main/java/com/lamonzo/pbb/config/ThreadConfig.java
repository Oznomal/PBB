package com.lamonzo.pbb.config;

import com.lamonzo.pbb.constants.SpringConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuration class used to handle the configuration
 * of classes and beans related to multi-threading within
 * the application
 */

@Configuration
public class ThreadConfig {

    @Bean(name = SpringConstants.SINGLE_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor singleThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setDaemon(true);
        executor.setThreadNamePrefix("Single_Thread_");
        executor.initialize();

        return executor;
    }

    @Bean(name = SpringConstants.VARIABLE_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor multiThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setDaemon(true);
        executor.setThreadNamePrefix("Variable_Thread_");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();

        return executor;
    }
}
