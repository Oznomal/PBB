package com.lamonzo.pbb;

import com.jauntium.Browser;
import com.jauntium.Element;
import com.jauntium.Elements;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.tasks.SubmitBallot;
import com.lamonzo.pbb.util.BrowserUtil;
import com.lamonzo.pbb.constants.ScrapingConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.Executor;
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

          Browser browser = BrowserUtil.getBrowser();
          browser.visit(ScrapingConstants.PRO_BOWL_VOTING_URL);

          try {
              Iterator<String> iterator = ScrapingConstants.ALL_POSITION_TAB_LINKS.iterator();
              while (iterator.hasNext()) {
                  Element tab = browser.doc.findFirst(iterator.next());
                  tab.click();

                  //COLLECT POSITION INFO
                  List<Element> positionInfoList = browser.doc.findFirst("<ul id=ballot-pos-nav-info-nfl>")
                          .getChildElements();

                  String sectionPosition = positionInfoList.get(0).getTextContent();
                  String [] votingInfoArr = positionInfoList.get(2).getTextContent().split(" ");
                  int maxVote = Integer.parseInt(votingInfoArr[2]);

                  System.out.println("_________________________________________________________");
                  System.out.println(sectionPosition + "\t|\tMax Votes: " + maxVote + "\n");

                  //COLLECT STAT CRITERIA FOR POSITION
                  Map<String, String> statMap = new HashMap<>();
                  Element statParent = browser.doc.findFirst("<ul id=ballot-sort-nav-nfc>");

                  for(int i = 1; i <= 4; i++){
                      String statId = "stat" + i;
                      String statType = statParent.findFirst("<li class=" + statId + ">").getTextContent().trim();
                      if (!statType.isEmpty())
                          statMap.put(statId, statType);
                      else
                          break;
                  }

                  List<Element> players = browser.doc.findEvery("<div class=name-pos>").toList();

                  for(Element playerElement : players){
                      Player player = new Player();

                      //TODO: This can be abstracted to a method
                      //GET PLAYER NAME INFO
                      String [] arr = playerElement.getAttribute("data-sort-name").split(" ");
                      StringBuilder sb = new StringBuilder();
                      for(int i = arr.length - 1; i >= 0; i--)
                          sb.append((i != 0) ? arr[i] + " " : arr[i]);
                      player.setName(sb.toString());

                      player.setPosition(playerElement.findFirst("<span>").getTextContent());
                      player.setTeam(playerElement.getParent().findFirst("<img class=team>")
                              .getAttribute("data-sort-team"));
                      player.setHtmlIdentifier(playerElement.getParent().getParent().getAttribute("id"));

                      //TODO: This can be abstracted to a method
                      //GET PLAYER STAT INFO IF APPLICABLE
                      if(!statMap.isEmpty()){
                          Element statsParent = playerElement.getParent().getFirst("<ul class=stats>");
                          Map<String, String> playerStats = new HashMap<>();
                          for(String statId : statMap.keySet()){
                              String statValue = statsParent.getFirst("<li class=" + statId +">")
                                      .getTextContent().trim();
                              playerStats.put(statId, statMap.get(statId) + " = " + statValue);
                          }

                          player.setStats(playerStats);
                      }

                      System.out.println(player);
                  }

                  System.out.println("\n\n");
              }
          }catch(Exception e){
              System.out.println("Something went wrong");
          }


//        Browser browser = BrowserUtil.getBrowser();
        //browser.visit("https://www.whoishostingthis.com/tools/user-agent/");

//        browser.visit(ScrapingConstants.PRO_BOWL_VOTING_URL);
//        try {
//            Element li = browser.doc.findFirst("<li id=button-KR>");
//            List<Element> children = li.getChildElements();
//            Element link = children.get(0);
//
//            Thread.sleep(random.nextInt(5000));
//
//            link.click();
//
//            Thread.sleep(random.nextInt(5000));
//
//            Element blake = browser.doc.findFirst("<li id=983040_09_1_06>");
//            blake.click();

            //This is the code that will run in the background regardless of what else is running


//            Iterator<String> iterator = ScrapingConstants.ALL_POSITION_TAB_LINKS.iterator();
//            while(iterator.hasNext()){
//                Thread.sleep(2000);
//                Element element = browser.doc.findFirst(iterator.next());
//                element.click();
//            }
//
//        }catch(Exception e){
//            System.out.println("Something went wrong in example");
//        }
    }
}
