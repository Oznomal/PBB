package com.lamonzo.pbb.constants;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ScrapingConstants {

    //================================================================================================================//
    //== FIELDS ==

    //== URLS ==
    public static final String PRO_BOWL_VOTING_URL = "http://www.nfl.com/probowl/ballot";
    public static final String GOOGLE_URL = "https://google.com";
    public static final String NFL_URL = "http://nfl.com";
    public static final String VOTING_THANK_YOU_PAGE_URL = "http://www.nfl.com/probowl/ballot/thank-you";

    //== GOOGLE SEARCH ==
    //SEARCH STRINGS
    public static final String GOOGLE_PRO_BOWL_VOTING_SEARCH_STRING ="NFL Pro Bowl Voting";
    public static final String GOOGLE_NFL_SEARCH_STRING = "NFL";

    //GOOGLE SEARCH PAGE ELEMENTS
    public static final String GOOGLE_SEARCH_BALLOT_LINK = "<a href=http://www.nfl.com/probowl/ballot>";
    public static final String GOOGLE_SEARCH_NFL_LINK = "<a href=https://www.nfl.com/>";


    //== NFL WEBSITE MAIN PAGE ==
    //PAGE ELEMENTS
    public static final String NFL_FEATURED_VOTE_LINK ="<a href=http://www.nfl.com/probowlvote>";
    public static final String NFL_MODAL_CLOSE_BUTTON = "<a class=bp-modal-takeover-close>";


    // == BALLOT NAVIGATION
    //OFFENSE
    public static final String QUARTERBACK_TAB_LINK = "<a title=Quarterbacks>";
    public static final String RUNNING_BACK_TAB_LINK = "<a title=Running\\sBacks>";
    public static final String WIDE_OUT_TAB_LINK = "<a title=Wide\\sReceivers>";
    public static final String FULLBACK_TAB_LINK = "<a title=Fullbacks>";
    public static final String TIGHT_END_TAB_LINK = "<a title=Tight\\sEnds>";
    public static final String TACKLE_TAB_LINK = "<a title=Tackles>";
    public static final String GUARD_TAB_LINK = "<a title=Guards>";
    public static final String CENTER_TAB_LINK = "<a title=Centers>";
    public static final String CENTER_TAB_XPATH = "//a[@id='yui_3_10_3_1_1544269403116_446']";

    //DEFENSE
    public static final String DEFENSIVE_END_TAB_LINK = "<a title=Defensive\\sEnds>";
    public static final String DEFENSIVE_TACKLE_TAB_LINK = "<a title=Defensive\\sTackles>";
    public static final String INSIDE_LINEBACKER_TAB_LINK = "<a title=Inside\\sLinebackers>";
    public static final String OUTSIDE_LINEBACKER_TAB_LINK = "<a title=Outside\\sLinebackers>";
    public static final String CORNER_BACK_TAB_LINK = "<a title=Cornerbacks>";
    public static final String STRONG_SAFETY_TAB_LINK = "<a title=Strong\\sSafeties>";
    public static final String FREE_SAFETY_TAB_LINK = "<a title=Free\\sSafeties>";

    //SPECIAL TEAMS
    public static final String KICKERS_TAB_LINK = "<a title=Kickers>";
    public static final String RETURN_SPECIALISTS_TAB_LINK = "<a title=Return\\sSpecialists>";
    public static final String PUNTER_TAB_LINK = "<a title=Punters>";
    public static final String SPECIAL_TEAMERS_TAB_LINK = "<a title=Special\\sTeamers>";

    //ALL POSITION LINKS
    public static final Set<String> ALL_POSITION_TAB_LINKS = Stream.of(
            QUARTERBACK_TAB_LINK,
            RUNNING_BACK_TAB_LINK,
            WIDE_OUT_TAB_LINK,
            FULLBACK_TAB_LINK,
            TIGHT_END_TAB_LINK,
            TACKLE_TAB_LINK,
            GUARD_TAB_LINK,
            CENTER_TAB_LINK,
            DEFENSIVE_END_TAB_LINK,
            DEFENSIVE_TACKLE_TAB_LINK,
            INSIDE_LINEBACKER_TAB_LINK,
            OUTSIDE_LINEBACKER_TAB_LINK,
            CORNER_BACK_TAB_LINK,
            STRONG_SAFETY_TAB_LINK,
            FREE_SAFETY_TAB_LINK,
            KICKERS_TAB_LINK,
            RETURN_SPECIALISTS_TAB_LINK,
            PUNTER_TAB_LINK,
            SPECIAL_TEAMERS_TAB_LINK
    ).collect(Collectors.toCollection(HashSet::new));


    //== POSITION INFORMATION (LOCATED AT THE TOP OF EACH TAB)
    public static final String POSITION_INFO_LIST = "<ul id=ballot-pos-nav-info-nfl>";
    public static final String POSITION_STAT_TYPES_LIST = "<ul id=ballot-sort-nav-nfc>";

    //PLAYER TABLE
    public static final String PLAYER_INFO_DIV = "<div class=name-pos>";
    public static final String PLAYER_NAME_ATTRIBUTE = "data-sort-name";
    public static final String PLAYER_TEAM_LOGO = "<img class=team>";
    public static final String PLAYER_TEAM_NAME_ATTRIBUTE = "data-sort-team";
    public static final String PLAYER_HTML_ID_ATTRIBUTE = "id";
    public static final String PLAYER_STATS_LIST = "<ul class=stats>";
    public static final String STAT_PREFIX = "<li class=";
    public static final String CLOSE_ANCHOR = ">";

    //SUBMITTING THE BALLOT
    public static final String PLAYER_DIV_XPATH_PREFIX = "//li[@id='";
    public static final String PLAYER_DIV_XPATH_SUFFIX = "']";
    public static final String PLAYER_VOTE_BTN_XPATH = "//a[@class='btn-vote']";
    public static final String SUBMIT_BUTTON = "<button id=ballot-submit>";
    public static final String VOTE_AGAIN_BTN_XPATH = "//a[@id='pb-btn-vote-again']";


    //================================================================================================================//

    //== CONSTRUCTOR ==
    private ScrapingConstants(){}
}
