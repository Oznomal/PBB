package com.lamonzo.pbb.util;

import com.jauntium.Browser;
import com.lamonzo.pbb.util.constants.UserAgentConstants;
import lombok.extern.slf4j.Slf4j;
import main.java.com.lamonzo.pbb.util.ProxyUtil;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Random;

@Slf4j
public class BrowserUtil {

    /**
     * Generates a browser with a random User Agent
     * @return
     */
    public static Browser getBrowser(){

        List<String> userAgentList;
        Random random = new Random();

        //Determine if the UA will be mobile or computer based
        if(random.nextInt(2) == 0){
            userAgentList = UserAgentConstants.VERY_COMMON_COMPUTER_USER_AGENTS;
        }else{
            userAgentList = UserAgentConstants.VERY_COMMON_MOBILE_USER_AGENTS;
        }

        //Get random user agent
        String agent = userAgentList.get(random.nextInt(userAgentList.size()));
        log.info("Creating Browser | User Agent={}", agent);

        //Add user agents to the options and create the browser
        System.setProperty(UserAgentConstants.CHROME_DRIVER_SYSTEM_PROPERTY, UserAgentConstants.CHROME_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(UserAgentConstants.CHROME_USER_AGENT_OPTION_PREFIX + agent);
        options.addArguments(ProxyUtil.CHROME_PROXY_OPTION_PREFIX + ProxyUtil.LUMINATI_PROXY_PORT_URL);
        Browser browser = new Browser(new ChromeDriver(options));

        return browser;
    }
}
