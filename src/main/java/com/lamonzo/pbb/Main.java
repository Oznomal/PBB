package com.lamonzo.pbb;

import com.lamonzo.pbb.tasks.UpdatePlayerData;
import com.lamonzo.pbb.constants.ScrapingConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

//////////////////////////////////////////////////////////////////////////////////
//        ExecutorService es = Executors.newFixedThreadPool(3);
//        for(int i = 0; i < 4; i++){
//            es.submit(new SubmitBallot());
//        }
//        es.shutdown();
//////////////////////////////////////////////////////////////////////////////////

        ExecutorService es = Executors.newFixedThreadPool(3);
        Iterator<String> iterator = ScrapingConstants.ALL_POSITION_TAB_LINKS.iterator();
        while(iterator.hasNext()){
            es.submit(new UpdatePlayerData(iterator.next()));
        }
        es.shutdown();
        while(!es.isTerminated()){}
        System.out.println("Done");
    }
}
