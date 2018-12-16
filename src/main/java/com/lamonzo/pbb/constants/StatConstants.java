package com.lamonzo.pbb.constants;

import java.util.Map;

public final class StatConstants {

    //================================================================================================================//
    //== FIELDS ==
    //Abbreviations
    public static final String TCKL = "Tckl";
    public static final String SCK = "Sck";
    public static final String FF = "FF";
    public static final String YDS = "Yds";
    public static final String TD = "TD";
    public static final String INT = "Int";
    public static final String RTG = "Rtg";
    public static final String KO_RET = "KORet";
    public static final String KO_AVG = "KOAvg";
    public static final String PT_RET = "PtRet";
    public static final String PT_AVG = "PtAvg";
    public static final String PUNTS = "Punts";
    public static final String AVG = "Avg";
    public static final String LNG = "Lng";
    public static final String IN_20 = "In 20";
    public static final String REC = "Rec";
    public static final String FGA = "FGA";
    public static final String FGM = "FGM";
    public static final String BLK = "Blk";
    public static final String FUM = "Fum";

    //Stat Names
    public static final String TACKLES = "Tackle(s)";
    public static final String SACKS = "Sack(s)";
    public static final String FORCED_FUMBLES = "Forced Fumble(s)";
    public static final String YARDS = "Yard(s)";
    public static final String TOUCHDOWNS = "Touchdown(s)";
    public static final String INTERCEPTIONS = "Interception(s)";
    public static final String RATING = "Rating";
    public static final String KO_RETURNED = "Kickoffs Returned";
    public static final String KO_AVERAGE = "Kickoff Return Average";
    public static final String PT_RETURNED = "Punts Returned";
    public static final String PT_AVERAGE = "Punt Return Average";
    public static final String PUNTS_ATTEMPTED = "Punts";
    public static final String AVERAGE = "Average";
    public static final String LONG = "Long";
    public static final String INSIDE_20 = "Inside 20";
    public static final String RECEPTIONS = "Receptions";
    public static final String FG_ATTEMPTS = "Field Goal Attempts";
    public static final String FG_MADE = "Field Goals Made";
    public static final String BLOCKS = "Block(s)";
    public static final String FUMBLES = "Fumble(s)";

    //Maps
    //Abbreviations to Names Map
    public static final Map<String, String> STAT_NAME_MAP = Map.ofEntries(
            Map.entry(TCKL, TACKLES),
            Map.entry(SCK, SACKS),
            Map.entry(FF, FORCED_FUMBLES),
            Map.entry(YDS, YARDS),
            Map.entry(TD, TOUCHDOWNS),
            Map.entry(INT, INTERCEPTIONS),
            Map.entry(RTG, RATING),
            Map.entry(KO_RET, KO_RETURNED),
            Map.entry(KO_AVG, KO_AVERAGE),
            Map.entry(PT_RET, PT_RETURNED),
            Map.entry(PT_AVG, PT_AVERAGE),
            Map.entry(PUNTS, PUNTS_ATTEMPTED),
            Map.entry(AVG, AVERAGE),
            Map.entry(LNG, LONG),
            Map.entry(IN_20, INSIDE_20),
            Map.entry(REC, RECEPTIONS),
            Map.entry(FGA, FG_ATTEMPTS),
            Map.entry(FGM, FG_MADE),
            Map.entry(BLK, BLOCKS),
            Map.entry(FUM, FUMBLES)
    );

    //Abbreviations to Value Type (True = Double, False = Integer)
    public static final Map<String, Boolean> STAT_VALUE_TYPE_MAP = Map.ofEntries(
            Map.entry(TCKL, false),
            Map.entry(SCK, true),
            Map.entry(FF, false),
            Map.entry(YDS, false),
            Map.entry(TD, false),
            Map.entry(INT, false),
            Map.entry(RTG, true),
            Map.entry(KO_RET, false),
            Map.entry(KO_AVG, true),
            Map.entry(PT_RET, false),
            Map.entry(PT_AVG, true),
            Map.entry(PUNTS, false),
            Map.entry(AVG, true),
            Map.entry(LNG, false),
            Map.entry(IN_20, false),
            Map.entry(REC, false),
            Map.entry(FGA, false),
            Map.entry(FGM, false),
            Map.entry(BLK, false),
            Map.entry(FUM, false)
    );

    //================================================================================================================//
    //== CONSTRUCTORS ==
    private StatConstants(){}
}
