package com.lamonzo.pbb;

import com.jauntium.Browser;
import com.jauntium.Element;
import com.lamonzo.pbb.tasks.SubmitBallot;
import com.lamonzo.pbb.util.BrowserUtil;
import com.lamonzo.pbb.constants.ScrapingConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.Executor;


import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Main {
    public static void main(String[] args) {


        ExecutorService es = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 4; i++){
            es.submit(new SubmitBallot());
        }
        es.shutdown();


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
