package com.lamonzo.pbb.util.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SOURCE URL: https://developers.whatismybrowser.com/useragents/explore/software_name/chrome/1
 *
 * Directions:
 * Visit the source URL above and start from page 2 (I already went through 1 for the most part)
 * Scroll down the list
 * 1. Match the Operating System Column (OS) to the name in the comment
 * 2. Match the hardware type to the section (Computer or Mobile/Mobile-Tablet)
 * 3. Check the Popularity, Very Common is ideal but common will work too, nothing less than that though
 * 4. Highlight the UserAgent string and put it in the code between the quotation marks in the right section
 */
public class UserAgentConstants {

    //================================================================================================================//
    //== FIELDS ==

    //==CHROME USER AGENTS
    public static final String CHROME_DRIVER_PATH = "E:/Programming/WebDrivers/Chrome/chromedriver.exe";
    public static final String CHROME_DRIVER_SYSTEM_PROPERTY = "webdriver.chrome.driver";
    public static final String CHROME_USER_AGENT_OPTION_PREFIX = "--user-agent=";

    //=========================
    // COMPUTERS & LAPTOPS
    //=========================
    //==VERY COMMON COMPUTER USER AGENTS
    //WINDOWS
    public static final String VC_COMP_USER_AGENT_0 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_1 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_2 = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_3 = "Mozilla/5.0 (Windows NT 5.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_4 = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_5 = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_6 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_7 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_8 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_9 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_10 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_11 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_12 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_13 = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_14 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_15 = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_16 = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_17 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_18 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_19 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";

    //MAC OSX
    //https://developers.whatismybrowser.com/useragents/explore/operating_system_name/mac-os-x/
    public static final String VC_COMP_USER_AGENT_20 = "";
    public static final String VC_COMP_USER_AGENT_21 = "";
    public static final String VC_COMP_USER_AGENT_22 = "";
    public static final String VC_COMP_USER_AGENT_23 = "";
    public static final String VC_COMP_USER_AGENT_24 = "";
    public static final String VC_COMP_USER_AGENT_25 = "";
    public static final String VC_COMP_USER_AGENT_26 = "";
    public static final String VC_COMP_USER_AGENT_27 = "";
    public static final String VC_COMP_USER_AGENT_28 = "";
    public static final String VC_COMP_USER_AGENT_29 = "";
    public static final String VC_COMP_USER_AGENT_30 = "";
    public static final String VC_COMP_USER_AGENT_31 = "";
    public static final String VC_COMP_USER_AGENT_32 = "";
    public static final String VC_COMP_USER_AGENT_33 = "";
    public static final String VC_COMP_USER_AGENT_34 = "";
    public static final String VC_COMP_USER_AGENT_35 = "";
    public static final String VC_COMP_USER_AGENT_36 = "";
    public static final String VC_COMP_USER_AGENT_37 = "";
    public static final String VC_COMP_USER_AGENT_38 = "";
    public static final String VC_COMP_USER_AGENT_39 = "";

    //LINUX
    //https://developers.whatismybrowser.com/useragents/explore/operating_system_name/linux/
    public static final String VC_COMP_USER_AGENT_40 = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_41 = "";
    public static final String VC_COMP_USER_AGENT_42 = "";
    public static final String VC_COMP_USER_AGENT_43 = "";
    public static final String VC_COMP_USER_AGENT_44 = "";
    public static final String VC_COMP_USER_AGENT_45 = "";
    public static final String VC_COMP_USER_AGENT_46 = "";
    public static final String VC_COMP_USER_AGENT_47 = "";
    public static final String VC_COMP_USER_AGENT_48 = "";
    public static final String VC_COMP_USER_AGENT_49 = "";

    public static final List<String> VERY_COMMON_COMPUTER_USER_AGENTS = Stream.of(
            VC_COMP_USER_AGENT_0,
            VC_COMP_USER_AGENT_1,
            VC_COMP_USER_AGENT_2,
            VC_COMP_USER_AGENT_3,
            VC_COMP_USER_AGENT_4,
            VC_COMP_USER_AGENT_5,
            VC_COMP_USER_AGENT_6,
            VC_COMP_USER_AGENT_7,
            VC_COMP_USER_AGENT_8,
            VC_COMP_USER_AGENT_9,
            VC_COMP_USER_AGENT_10,
            VC_COMP_USER_AGENT_11,
            VC_COMP_USER_AGENT_12,
            VC_COMP_USER_AGENT_13,
            VC_COMP_USER_AGENT_14,
            VC_COMP_USER_AGENT_15,
            VC_COMP_USER_AGENT_16,
            VC_COMP_USER_AGENT_17,
            VC_COMP_USER_AGENT_18,
            VC_COMP_USER_AGENT_19
    ).collect(Collectors.toList());


    //=========================
    // MOBILE DEVICES
    //=========================
    //==VERY COMMON MOBILE/TABLE USER AGENTS
    //ANDROID (10 Most Common)
    //https://developers.whatismybrowser.com/useragents/explore/operating_system_name/android/
    public static final String VC_MOBILE_USER_AGENT_0 = "";
    public static final String VC_MOBILE_USER_AGENT_1 = "";
    public static final String VC_MOBILE_USER_AGENT_2 = "";
    public static final String VC_MOBILE_USER_AGENT_3 = "";
    public static final String VC_MOBILE_USER_AGENT_4 = "";
    public static final String VC_MOBILE_USER_AGENT_5 = "";
    public static final String VC_MOBILE_USER_AGENT_6 = "";
    public static final String VC_MOBILE_USER_AGENT_7 = "";
    public static final String VC_MOBILE_USER_AGENT_8 = "";
    public static final String VC_MOBILE_USER_AGENT_9 = "";

    //APPLE

    //IPHONE (Visit the link below and just grab the first 20 most common ones)
    //https://developers.whatismybrowser.com/useragents/explore/operating_platform/iphone/
    public static final String VC_MOBILE_USER_AGENT_10 = "";
    public static final String VC_MOBILE_USER_AGENT_11 = "";
    public static final String VC_MOBILE_USER_AGENT_12 = "";
    public static final String VC_MOBILE_USER_AGENT_13 = "";
    public static final String VC_MOBILE_USER_AGENT_14 = "";
    public static final String VC_MOBILE_USER_AGENT_15 = "";
    public static final String VC_MOBILE_USER_AGENT_16 = "";
    public static final String VC_MOBILE_USER_AGENT_17 = "";
    public static final String VC_MOBILE_USER_AGENT_18 = "";
    public static final String VC_MOBILE_USER_AGENT_19 = "";
    public static final String VC_MOBILE_USER_AGENT_20 = "";
    public static final String VC_MOBILE_USER_AGENT_21 = "";
    public static final String VC_MOBILE_USER_AGENT_22 = "";
    public static final String VC_MOBILE_USER_AGENT_23 = "";
    public static final String VC_MOBILE_USER_AGENT_24 = "";
    public static final String VC_MOBILE_USER_AGENT_25 = "";
    public static final String VC_MOBILE_USER_AGENT_26 = "";
    public static final String VC_MOBILE_USER_AGENT_27 = "";
    public static final String VC_MOBILE_USER_AGENT_28 = "";
    public static final String VC_MOBILE_USER_AGENT_29 = "";

    //IPAD (Visit the link below and just grab the first 15 most common ones)
    //https://developers.whatismybrowser.com/useragents/explore/operating_platform/ipad/
    public static final String VC_MOBILE_USER_AGENT_30 = "";
    public static final String VC_MOBILE_USER_AGENT_31 = "";
    public static final String VC_MOBILE_USER_AGENT_32 = "";
    public static final String VC_MOBILE_USER_AGENT_33 = "";
    public static final String VC_MOBILE_USER_AGENT_34 = "";
    public static final String VC_MOBILE_USER_AGENT_35 = "";
    public static final String VC_MOBILE_USER_AGENT_36 = "";
    public static final String VC_MOBILE_USER_AGENT_37 = "";
    public static final String VC_MOBILE_USER_AGENT_38 = "";
    public static final String VC_MOBILE_USER_AGENT_39 = "";
    public static final String VC_MOBILE_USER_AGENT_40 = "";
    public static final String VC_MOBILE_USER_AGENT_41 = "";
    public static final String VC_MOBILE_USER_AGENT_42 = "";
    public static final String VC_MOBILE_USER_AGENT_43 = "";
    public static final String VC_MOBILE_USER_AGENT_44 = "";



    public static final List<String> VERY_COMMON_MOBILE_USER_AGENTS = Stream.of(
            VC_MOBILE_USER_AGENT_0,
            VC_MOBILE_USER_AGENT_1,
            VC_MOBILE_USER_AGENT_2,
            VC_MOBILE_USER_AGENT_3,
            VC_MOBILE_USER_AGENT_4
    ).collect(Collectors.toList());


    //================================================================================================================//

    //==CONSTRUCTOR==
    private UserAgentConstants(){}
}
