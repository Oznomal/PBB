package com.lamonzo.pbb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuration class used to handle the configuration
 * of classes and beans related to multi-threading within
 * the application
 */

@Configuration
public class ThreadConfig {

    @Bean(name = "singleCoreTaskExecutor")
    public TaskExecutor singleThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setThreadNamePrefix("Single_Thread_");
        executor.initialize();

        return executor;
    }

    @Bean(name = "multiCoreTaskExecutor")
    public TaskExecutor multiThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("Multi_Thread_");
        executor.initialize();

        return executor;
    }
}
