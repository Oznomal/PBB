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


    // == BALLOT NAVIGATION
    //OFFENSE
    public static final String QUARTERBACK_TAB_LI = "<li id=button-QB>";
    public static final String QUARTERBACK_TAB_LINK = "<a title=Quarterbacks>";

    public static final String RUNNING_BACK_TAB_LI = "<li id=button-RB>";
    public static final String RUNNING_BACK_TAB_LINK = "<a title=Running\\sBacks>";

    public static final String WIDE_OUT_TAB_LI = "<li id=button-WR>";
    public static final String WIDE_OUT_TAB_LINK = "<a title=Wide\\sReceivers>";

    public static final String FULLBACK_TAB_LI = "<li id=button-FB>";
    public static final String FULLBACK_TAB_LINK = "<a title=Fullbacks>";

    public static final String TIGHT_END_TAB_LI = "<li id=button-TE>";
    public static final String TIGHT_END_TAB_LINK = "<a title=Tight\\sEnds>";

    public static final String TACKLE_TAB_LI = "<li id=button-T>";
    public static final String TACKLE_TAB_LINK = "<a title=Tackles>";

    public static final String GUARD_TAB_LI = "<li id=button-G>";
    public static final String GUARD_TAB_LINK = "<a title=Guards>";

    public static final String CENTER_TAB_LI = "<li id=button-C>";
    public static final String CENTER_TAB_LINK = "<a title=Centers>";

    //DEFENSE
    public static final String DEFENSIVE_END_TAB_LI = "<li id=button-DE>";
    public static final String DEFENSIVE_END_TAB_LINK = "<a title=Defensive\\sEnds>";

    public static final String DEFENSIVE_TACKLE_TAB_LI = "<li id=button-DT>";
    public static final String DEFENSIVE_TACKLE_TAB_LINK = "<a title=Defensive\\sTackles>";

    public static final String INSIDE_LINEBACKER_TAB_LI = "<li id=button-ILB>";
    public static final String INSIDE_LINEBACKER_TAB_LINK = "<a title=Inside\\sLinebackers>";

    public static final String OUTSIDE_LINEBACKER_TAB_LI = "<li id=button-OLB>";
    public static final String OUTSIDE_LINEBACKER_TAB_LINK = "<a title=Outside\\sLinebackers>";

    public static final String CORNER_BACK_TAB_LI = "<li id=button-CB>";
    public static final String CORNER_BACK_TAB_LINK = "<a title=Cornerbacks>";

    public static final String STRONG_SAFETY_TAB_LI = "<li id=button-SS>";
    public static final String STRONG_SAFETY_TAB_LINK = "<a title=Strong\\sSafeties>";

    public static final String FREE_SAFETY_TAB_LI = "<li id=button-FS>";
    public static final String FREE_SAFETY_TAB_LINK = "<a title=Free\\sSafeties>";

    //SPECIAL TEAMS
    public static final String KICKERS_TAB_LI = "<li id=button-K>";
    public static final String KICKERS_TAB_LINK = "<a title=Kickers>";

    public static final String RETURN_SPECIALISTS_TAB_LI = "<li id=button-KR>";
    public static final String RETURN_SPECIALISTS_TAB_LINK = "<a title=Return\\sSpecialists>";

    public static final String PUNTER_TAB_LI = "<li id=button-P>";
    public static final String PUNTER_TAB_LINK = "<a title=Punters>";

    public static final String SPECIAL_TEAMERS_TAB_LI = "<li id=button-ST>";
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

    //================================================================================================================//

    //== CONSTRUCTOR ==
    private ScrapingConstants(){}
}
