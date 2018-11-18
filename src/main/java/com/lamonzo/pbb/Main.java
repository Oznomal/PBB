package com.lamonzo.pbb;

import com.jauntium.Browser;
import com.jauntium.Elements;
import com.jauntium.Element;
import com.lamonzo.pbb.util.BrowserUtil;
import com.lamonzo.pbb.util.constants.UserAgentConstants;
import main.java.com.lamonzo.pbb.util.constants.ScrapingConstants;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Browser browser = BrowserUtil.getBrowser();
        browser.visit("https://www.whoishostingthis.com/tools/user-agent/");
        try {
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


            Iterator<String> iterator = ScrapingConstants.ALL_POSITION_TAB_LINKS.iterator();
            while(iterator.hasNext()){
                Thread.sleep(2000);
                Element element = browser.doc.findFirst(iterator.next());
                element.click();
            }

        }catch(Exception e){
            System.out.println("Something went wrong in example");
        }
    }
}
