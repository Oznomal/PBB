package com.lamonzo.pbb.constants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SOURCE URL: https://developers.whatismybrowser.com/useragents/explore/software_name/chrome/1
 */
public class UserAgentConstants {

    //================================================================================================================//
    //== FIELDS ==

    //==CHROME USER AGENTS
    public static final String CHROME_USER_AGENT_OPTION_PREFIX = "--user-agent=";

    //=========================
    // COMPUTERS & LAPTOPS
    //=========================
    //==VERY COMMON COMPUTER USER AGENTS
    //WINDOWS
    public static final String VC_COMP_USER_AGENT_0 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_1 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_2 = "Mozilla/5.0 (Windows NT 6.1) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_3 = "Mozilla/5.0 (Windows NT 5.1; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_4 = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_5 = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_6 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_7 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_8 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_9 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_10 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_11 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_12 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_13 = "Mozilla/5.0 (Windows NT 6.1; WOW64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_14 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_15 = "Mozilla/5.0 (Windows NT 6.1; WOW64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_16 = "Mozilla/5.0 (Windows NT 6.1; WOW64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_17 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_18 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_19 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";

    //MAC OSX
    //https://developers.whatismybrowser.com/useragents/explore/operating_system_name/mac-os-x/
    public static final String VC_COMP_USER_AGENT_20 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) " +
            "AppleWebKit/601.7.7 (KHTML, like Gecko) Version/9.1.2 Safari/601.7.7";
    public static final String VC_COMP_USER_AGENT_21 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) " +
            "AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.1.2 Safari/603.3.8";
    public static final String VC_COMP_USER_AGENT_22 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) " +
            "AppleWebKit/601.4.4 (KHTML, like Gecko) Version/9.0.3 Safari/601.4.4";
    public static final String VC_COMP_USER_AGENT_23 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) " +
            "AppleWebKit/601.5.17 (KHTML, like Gecko) Version/9.1 Safari/601.5.17";
    public static final String VC_COMP_USER_AGENT_24 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) " +
            "AppleWebKit/601.6.17 (KHTML, like Gecko) Version/9.1.1 Safari/601.6.17";
    public static final String VC_COMP_USER_AGENT_25 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) " +
            "AppleWebKit/601.7.8 (KHTML, like Gecko) Version/9.1.3 Safari/537.86.7";
    public static final String VC_COMP_USER_AGENT_26 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) " +
            "AppleWebKit/601.2.7 (KHTML, like Gecko) Version/9.0.1 Safari/601.2.7";
    public static final String VC_COMP_USER_AGENT_27 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) " +
            "AppleWebKit/600.8.9 (KHTML, like Gecko) Version/8.0.8 Safari/600.8.9";
    public static final String VC_COMP_USER_AGENT_28 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) " +
            "AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A";
    public static final String VC_COMP_USER_AGENT_29 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) " +
            "AppleWebKit/534.59.10 (KHTML, like Gecko) Version/5.1.9 Safari/534.59.10";
    public static final String VC_COMP_USER_AGENT_30 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) " +
            "AppleWebKit/537.78.2 (KHTML, like Gecko) Version/6.1.6 Safari/537.78.2";
    public static final String VC_COMP_USER_AGENT_31 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) " +
            "AppleWebKit/601.4.4 (KHTML, like Gecko) Version/9.0.3 Safari/601.4.4";
    public static final String VC_COMP_USER_AGENT_32 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) " +
            "AppleWebKit/601.5.17 (KHTML, like Gecko) Version/9.1 Safari/601.5.17";
    public static final String VC_COMP_USER_AGENT_33 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) " +
            "AppleWebKit/601.7.8 (KHTML, like Gecko) Version/9.1.3 Safari/601.7.8";
    public static final String VC_COMP_USER_AGENT_34 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) " +
            "AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Safari/602.1.50";
    public static final String VC_COMP_USER_AGENT_35 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11) " +
            "AppleWebKit/601.1.56 (KHTML, like Gecko) Version/9.0 Safari/601.1.56";
    public static final String VC_COMP_USER_AGENT_36 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) " +
            "AppleWebKit/600.8.9 (KHTML, like Gecko) Version/6.2.8 Safari/537.85.17";
    public static final String VC_COMP_USER_AGENT_37 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_5_8) " +
            "AppleWebKit/534.50.2 (KHTML, like Gecko) Version/5.0.6 Safari/533.22.3";
    public static final String VC_COMP_USER_AGENT_38 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) " +
            "AppleWebKit/601.7.7 (KHTML, like Gecko) Version/9.1.2 Safari/601.7.7";
    public static final String VC_COMP_USER_AGENT_39 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) " +
            "AppleWebKit/601.2.7 (KHTML, like Gecko) Version/9.0.1 Safari/601.2.7";

    //LINUX
    //https://developers.whatismybrowser.com/useragents/explore/operating_system_name/linux/
    public static final String VC_COMP_USER_AGENT_40 = "Mozilla/5.0 (X11; Linux x86_64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_41 = "Mozilla/5.0 (X11; Linux x86_64; rv:45.0) " +
            "Gecko/20100101 Thunderbird/45.3.0";
    public static final String VC_COMP_USER_AGENT_42 = "Mozilla/5.0 (X11; Linux x86_64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_43 = "Mozilla/5.0 (X11; Linux x86_64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_44 = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:15.0) " +
            "Gecko/20100101 Firefox/15.0";
    public static final String VC_COMP_USER_AGENT_45 = "Mozilla/5.0 (Unknown; Linux) " +
            "AppleWebKit/538.1 (KHTML, like Gecko) Chrome/v1.0.0 Safari/538.1";
    public static final String VC_COMP_USER_AGENT_46 = "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) " +
            "Gecko/20150101 Firefox/47.0 (Chrome)";
    public static final String VC_COMP_USER_AGENT_48 = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML";
    public static final String VC_COMP_USER_AGENT_49 = "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) " +
            "Gecko/20150101 Firefox/20.0 (Chrome)";

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
            VC_COMP_USER_AGENT_19,
            VC_COMP_USER_AGENT_20,
            VC_COMP_USER_AGENT_21,
            VC_COMP_USER_AGENT_22,
            VC_COMP_USER_AGENT_23,
            VC_COMP_USER_AGENT_24,
            VC_COMP_USER_AGENT_25,
            VC_COMP_USER_AGENT_26,
            VC_COMP_USER_AGENT_27,
            VC_COMP_USER_AGENT_28,
            VC_COMP_USER_AGENT_29,
            VC_COMP_USER_AGENT_30,
            VC_COMP_USER_AGENT_31,
            VC_COMP_USER_AGENT_32,
            VC_COMP_USER_AGENT_33,
            VC_COMP_USER_AGENT_34,
            VC_COMP_USER_AGENT_35,
            VC_COMP_USER_AGENT_36,
            VC_COMP_USER_AGENT_37,
            VC_COMP_USER_AGENT_38,
            VC_COMP_USER_AGENT_39,
            VC_COMP_USER_AGENT_40,
            VC_COMP_USER_AGENT_41,
            VC_COMP_USER_AGENT_42,
            VC_COMP_USER_AGENT_43,
            VC_COMP_USER_AGENT_44,
            VC_COMP_USER_AGENT_45,
            VC_COMP_USER_AGENT_46,
            VC_COMP_USER_AGENT_48,
            VC_COMP_USER_AGENT_49
    ).collect(Collectors.toList());


    //=========================
    // MOBILE DEVICES
    //=========================
    //==VERY COMMON MOBILE/TABLE USER AGENTS
    //ANDROID (10 Most Common)
    //https://developers.whatismybrowser.com/useragents/explore/operating_system_name/android/
    public static final String VC_MOBILE_USER_AGENT_0 = "Mozilla/5.0 (Linux; Android 4.2.1; en-us; " +
            "Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko; googleweblight) " +
            "Chrome/38.0.1025.166 Mobile Safari/535.19";
    public static final String VC_MOBILE_USER_AGENT_1 = "Mozilla/5.0 (Linux; Android 4.4.2; XMP-6250 Build/HAWK) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Safari/537.36 " +
            "ADAPI/2.0 (UUID:9e7df0ed-2a5c-4a19-bec7-2cc54800f99d) RK3188-ADAPI/1.2.84.533 (MODEL:XMP-6250)";
    public static final String VC_MOBILE_USER_AGENT_2 = "Mozilla/5.0 (Linux; Android 6.0.1; SM-G532G Build/MMB29T) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.83 Mobile Safari/537.36";
    public static final String VC_MOBILE_USER_AGENT_3 = "Mozilla/5.0 (Linux; Android 7.1; Mi A1 Build/N2G47H) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.83 Mobile Safari/537.36";
    public static final String VC_MOBILE_USER_AGENT_4 = "Mozilla/5.0 (Linux; Android 5.1; A37f Build/LMY47V) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.93 Mobile Safari/537.36";
    public static final String VC_MOBILE_USER_AGENT_5 = "Mozilla/5.0 (Linux; Android 6.0.1; CPH1607 " +
            "Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Version/4.0 Chrome/63.0.3239.111 Mobile Safari/537.36";
    public static final String VC_MOBILE_USER_AGENT_6 = "Mozilla/5.0 (Linux; Android 6.0.1; Redmi 4A " +
            "Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.116 " +
            "Mobile Safari/537.36";
    public static final String VC_MOBILE_USER_AGENT_7 = "Mozilla/5.0 (Linux; U; Android 6.0.1; zh-CN; " +
            "F5121 Build/34.0.A.1.247) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/40.0.2214.89 " +
            "UCBrowser/11.5.1.944 Mobile Safari/537.36";
    public static final String VC_MOBILE_USER_AGENT_8 = "Mozilla/5.0 (Linux; Android 6.0; MYA-L22 " +
            "Build/HUAWEIMYA-L22) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.84 Mobile Safari/537.36";
    public static final String VC_MOBILE_USER_AGENT_9 = "Mozilla/5.0 (Linux; Android 5.1; A1601 Build/LMY47I) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36";

    //APPLE

    //IPHONE (Visit the link below and just grab the first 20 most common ones)
    //https://developers.whatismybrowser.com/useragents/explore/operating_platform/iphone/
    public static final String VC_MOBILE_USER_AGENT_10 = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_11 = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) " +
            "AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";
    public static final String VC_MOBILE_USER_AGENT_12 = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) " +
            "AppleWebKit/602.4.6 (KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_13 = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13E188a Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_14 = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_3 like Mac OS X) " +
            "AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.0 Mobile/14G60 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_15 = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) " +
            "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";
    public static final String VC_MOBILE_USER_AGENT_16 = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) " +
            "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";
    public static final String VC_MOBILE_USER_AGENT_17 = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_1 like Mac OS X) " +
            "AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12B410 Safari/600.1.4";
    public static final String VC_MOBILE_USER_AGENT_18 = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_2 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13F69 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_19 = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2 like Mac OS X) " +
            "AppleWebKit/602.3.12 (KHTML, like Gecko) Version/10.0 Mobile/14C92 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_20 = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_1 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13E238 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_21 = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_2_1 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13D15 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_22 = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_1_1 like Mac OS X) " +
            "AppleWebKit/602.2.14 (KHTML, like Gecko) Version/10.0 Mobile/14B100 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_23 = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_2_1 like Mac OS X) " +
            "AppleWebKit/604.4.7 (KHTML, like Gecko) Version/11.0 Mobile/15C153 Safari/604.1";
    public static final String VC_MOBILE_USER_AGENT_24 = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) " +
            "AppleWebKit/603.2.4 (KHTML, like Gecko) Version/10.0 Mobile/14F89 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_25 = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_2_6 like Mac OS X) " +
            "AppleWebKit/604.5.6 (KHTML, like Gecko) Version/11.0 Mobile/15D100 Safari/604.1";
    public static final String VC_MOBILE_USER_AGENT_26 = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0_2 like Mac OS X) " +
            "AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/14A456 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_27 = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_4 like Mac OS X) " +
            "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";
    public static final String VC_MOBILE_USER_AGENT_28 = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_29 = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_2 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13C75 Safari/601.1";

    //IPAD (Visit the link below and just grab the first 15 most common ones)
    //https://developers.whatismybrowser.com/useragents/explore/operating_platform/ipad/
    public static final String VC_MOBILE_USER_AGENT_30 = "Mozilla/5.0 (iPad; CPU OS 9_3_2 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13F69 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_31 = "Mozilla/5.0 (iPad; CPU OS 9_3_5 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_32 = "Mozilla/5.0 (iPad; CPU OS 10_2_1 like Mac OS X) " +
            "AppleWebKit/602.4.6 (KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_33 = "Mozilla/5.0 (iPad; CPU OS 10_3_3 like Mac OS X) " +
            "AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.0 Mobile/14G60 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_34 = "Mozilla/5.0 (iPad; CPU OS 9_2_1 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13D15 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_35 = "Mozilla/5.0 (iPad; CPU OS 9_3_1 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13E238 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_36 = "Mozilla/5.0 (iPad; CPU OS 5_1_1 like Mac OS X) " +
            "AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3";
    public static final String VC_MOBILE_USER_AGENT_37 = "Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_38 = "Mozilla/5.0 (iPad; CPU OS 9_2 like Mac OS X) " +
            "AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13C75 Safari/601.1";
    public static final String VC_MOBILE_USER_AGENT_39 = "Mozilla/5.0 (iPad; CPU OS 8_4_1 like Mac OS X) " +
            "AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12H321 Safari/600.1.4";
    public static final String VC_MOBILE_USER_AGENT_40 = "Mozilla/5.0 (iPad; CPU OS 10_0_2 like Mac OS X) " +
            "AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/14A456 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_41 = "Mozilla/5.0 (iPad; CPU OS 10_2 like Mac OS X) " +
            "AppleWebKit/602.3.12 (KHTML, like Gecko) Version/10.0 Mobile/14C92 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_42 = "Mozilla/5.0 (iPad; CPU OS 11_4_1 like Mac OS X) " +
            "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";
    public static final String VC_MOBILE_USER_AGENT_43 = "Mozilla/5.0 (iPad; CPU OS 10_1_1 like Mac OS X) " +
            "AppleWebKit/602.2.14 (KHTML, like Gecko) Version/10.0 Mobile/14B100 Safari/602.1";
    public static final String VC_MOBILE_USER_AGENT_44 = "Mozilla/5.0 (iPad; CPU OS 11_3 like Mac OS X) " +
            "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";


    public static final List<String> VERY_COMMON_MOBILE_USER_AGENTS = Stream.of(
            VC_MOBILE_USER_AGENT_0,
            VC_MOBILE_USER_AGENT_1,
            VC_MOBILE_USER_AGENT_2,
            VC_MOBILE_USER_AGENT_3,
            VC_MOBILE_USER_AGENT_4,
            VC_MOBILE_USER_AGENT_5,
            VC_MOBILE_USER_AGENT_6,
            VC_MOBILE_USER_AGENT_7,
            VC_MOBILE_USER_AGENT_8,
            VC_MOBILE_USER_AGENT_9,
            VC_MOBILE_USER_AGENT_10,
            VC_MOBILE_USER_AGENT_11,
            VC_MOBILE_USER_AGENT_12,
            VC_MOBILE_USER_AGENT_13,
            VC_MOBILE_USER_AGENT_14,
            VC_MOBILE_USER_AGENT_15,
            VC_MOBILE_USER_AGENT_16,
            VC_MOBILE_USER_AGENT_17,
            VC_MOBILE_USER_AGENT_18,
            VC_MOBILE_USER_AGENT_19,
            VC_MOBILE_USER_AGENT_20,
            VC_MOBILE_USER_AGENT_21,
            VC_MOBILE_USER_AGENT_22,
            VC_MOBILE_USER_AGENT_23,
            VC_MOBILE_USER_AGENT_24,
            VC_MOBILE_USER_AGENT_25,
            VC_MOBILE_USER_AGENT_26,
            VC_MOBILE_USER_AGENT_27,
            VC_MOBILE_USER_AGENT_28,
            VC_MOBILE_USER_AGENT_29,
            VC_MOBILE_USER_AGENT_30,
            VC_MOBILE_USER_AGENT_31,
            VC_MOBILE_USER_AGENT_32,
            VC_MOBILE_USER_AGENT_33,
            VC_MOBILE_USER_AGENT_34,
            VC_MOBILE_USER_AGENT_35,
            VC_MOBILE_USER_AGENT_36,
            VC_MOBILE_USER_AGENT_37,
            VC_MOBILE_USER_AGENT_38,
            VC_MOBILE_USER_AGENT_39,
            VC_MOBILE_USER_AGENT_40,
            VC_MOBILE_USER_AGENT_41,
            VC_MOBILE_USER_AGENT_42,
            VC_MOBILE_USER_AGENT_43,
            VC_MOBILE_USER_AGENT_44
    ).collect(Collectors.toList());


    //================================================================================================================//
    //==CONSTRUCTOR==
    private UserAgentConstants(){}
}
