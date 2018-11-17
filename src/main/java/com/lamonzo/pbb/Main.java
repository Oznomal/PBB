package com.lamonzo.pbb;

import com.jauntium.Browser;
import com.jauntium.Elements;
import com.jauntium.Element;
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

        //CHROME
        //System.setProperty("webdriver.chrome.driver", "E:/Programming/WebDrivers/Chrome/chromedriver.exe");
        //Browser browser = new Browser(new ChromeDriver());

        //FIREFOX
        //System.setProperty("webdriver.gecko.driver", "E:/Programming/WebDrivers/Firefox/geckodriver.exe");

        //IE 11 (HAS BUGS)
        //System.setProperty("webdriver.ie.driver", "E:/Programming/WebDrivers/IE/IEDriverServer.exe");

        //EDGE
        //System.setProperty("webdriver.edge.driver", "E:/Programming/WebDrivers/Edge/MicrosoftWebDriver.exe");

        //OPERA (OPTIONS MUST BE SET FIRST)
        //OperaOptions operaOptions = new OperaOptions();
        //operaOptions.setBinary("C:\\Program Files\\Opera\\54.0.2952.60\\opera.exe");
        //System.setProperty("webdriver.opera.driver", "E:/Programming/WebDrivers/Opera/operadriver.exe");
        //Browser browser = new Browser(new OperaDriver(operaOptions));

        System.setProperty("webdriver.chrome.driver", "E:/Programming/WebDrivers/Chrome/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        Random random = new Random();


        int length = UserAgentConstants.VERY_COMMON_USER_AGENTS.size();
        String userAgent = UserAgentConstants.CHROME_USER_AGENT_OPTION_PREFIX +
                UserAgentConstants.VERY_COMMON_USER_AGENTS.get(random.nextInt(length));
        System.out.println("User Agent String: " + userAgent);
        options.addArguments(userAgent);
        Browser browser = new Browser(new ChromeDriver(options));
        browser.visit("https://www.whatismybrowser.com/detect/what-is-my-user-agent");
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
