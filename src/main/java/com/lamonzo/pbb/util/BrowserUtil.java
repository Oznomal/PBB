package com.lamonzo.pbb.util;

import com.jauntium.Browser;
import com.lamonzo.pbb.constants.ProxyConstants;
import com.lamonzo.pbb.constants.UserAgentConstants;
import com.lamonzo.pbb.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class BrowserUtil {

    //================================================================================================================//
    //== FIELDS ==
    private static final String CHROME_DRIVER_PATH = "E:/Programming/WebDrivers/Chrome/chromedriver.exe";
    private static final String CHROME_DRIVER_SYSTEM_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_HEADLESS_OPTION = "--headless";
    private static final String CHROME_WINDOW_SIZE_OPTION = "window-size=2000,4500";

    private final DataModel dataModel;

    //================================================================================================================//
    //== CONSTRUCTORS ==
    @Autowired
    public BrowserUtil(DataModel dataModel){
        this.dataModel = dataModel;
    }

    //================================================================================================================//
    //== PUBLIC METHODS ==
    //Generates a browser based on the users settings with a random user agent
    public Browser getBrowser(){

        List<String> userAgentList;
        Random random = new Random();

        //Determine if the UA will be mobile or computer based
        //TODO: Switch the number back to 2 to start using mobile user-agents again (Requires Updates)
        if(random.nextInt(1) == 0){
            userAgentList = UserAgentConstants.VERY_COMMON_COMPUTER_USER_AGENTS;
        }else{
            userAgentList = UserAgentConstants.VERY_COMMON_MOBILE_USER_AGENTS;
        }

        //Set the system property to use the Chrome Driver
        System.setProperty(CHROME_DRIVER_SYSTEM_PROPERTY, CHROME_DRIVER_PATH);

        //Configure browser options by adding user agent and proxy info
        ChromeOptions options = new ChromeOptions();
        String agent = userAgentList.get(random.nextInt(userAgentList.size()));
        options.addArguments(UserAgentConstants.CHROME_USER_AGENT_OPTION_PREFIX + agent);

        //Add headless mode if selected
        if(!dataModel.getShowBrowser().get()) {
            options.addArguments(CHROME_HEADLESS_OPTION);
            options.addArguments(CHROME_WINDOW_SIZE_OPTION);
        }

        //Rotate proxies if selected
        if(dataModel.getRotateProxies().get()){
            options.addArguments(ProxyConstants.CHROME_PROXY_OPTION_PREFIX + ProxyConstants.PROXY_PORT_URL);
        }

        //Create and return the browser
        Browser browser = new Browser(new ChromeDriver(options));
        log.info("New Browser Created | User Agent={}", agent);

        return browser;
    }

    //Generates a standard headless browser regardless of the user settings
    //useful for tasks other than submitting the ballots
    public Browser getHeadlessBrowser(){
        System.setProperty(CHROME_DRIVER_SYSTEM_PROPERTY, CHROME_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(CHROME_HEADLESS_OPTION);
        options.addArguments(CHROME_WINDOW_SIZE_OPTION);
        Browser browser = new Browser(new ChromeDriver(options));
        log.info("New Generic Headless Browser Created");

        return browser;
    }
}
