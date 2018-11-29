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
        executor.setThreadNamePrefix("Single_Thread_");
        executor.initialize();

        return executor;
    }

    @Bean(name = SpringConstants.MULTI_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor multiThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("Multi_Thread_");
        executor.initialize();

        return executor;
    }
}
