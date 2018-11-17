package com.lamonzo.pbb.util.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserAgentConstants {

    //================================================================================================================//
    //== FIELDS ==

    //==CHROME USER AGENTS
    public static final String CHROME_USER_AGENT_OPTION_PREFIX = "--user-agent=";

    //==VERY COMMON COMPUTER USER AGENTS
    //WINDOWS
    public static final String VC_COMP_USER_AGENT_0 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_1 = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";
    public static final String VC_COMP_USER_AGENT_2 = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
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
    public static final String VC_COMP_USER_AGENT_20 = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36";

    public static final List<String> VERY_COMMON_USER_AGENTS = Stream.of(
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
            VC_COMP_USER_AGENT_20
    ).collect(Collectors.toCollection(ArrayList::new));


    //================================================================================================================//

    //==CONSTRUCTOR==
    private UserAgentConstants(){}
}
