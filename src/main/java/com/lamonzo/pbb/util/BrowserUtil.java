package com.lamonzo.pbb.util;

import com.jauntium.Browser;
import com.lamonzo.pbb.constants.ProxyConstants;
import com.lamonzo.pbb.constants.UserAgentConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Random;

@Slf4j
public class BrowserUtil {

    private static final String CHROME_DRIVER_PATH = "E:/Programming/WebDrivers/Chrome/chromedriver.exe";
    private static final String CHROME_DRIVER_SYSTEM_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_HEADLESS_OPTION = "--headless";

    /**
     * Generates a browser with a random User Agent
     * @return
     */
    public static Browser getBrowser(){

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
        options.addArguments(CHROME_HEADLESS_OPTION);

        //TODO: Uncomment this line to begin using the proxy service, but avoid using to avoid charges
        //options.addArguments(ProxyConstants.CHROME_PROXY_OPTION_PREFIX + ProxyConstants.PROXY_PORT_URL);

        //Create and return the browser
        Browser browser = new Browser(new ChromeDriver(options));
        log.info("New Browser Created | User Agent={}", agent);

        return browser;
    }
}
