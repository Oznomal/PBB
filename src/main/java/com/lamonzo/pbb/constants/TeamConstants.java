package com.lamonzo.pbb.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public final class TeamConstants {

    //== FIELDS ==
    //ABBREVIATIONS
    public static final String ARI = "ARI";
    public static final String ATL = "ATL";
    public static final String BAL = "BAL";
    public static final String BUF = "BUF";
    public static final String CAR = "CAR";
    public static final String CHI = "CHI";
    public static final String CIN = "CIN";
    public static final String CLE = "CLE";
    public static final String DAL = "DAL";
    public static final String DEN = "DEN";
    public static final String DET = "DET";
    public static final String GB = "GB";
    public static final String HOU = "HOU";
    public static final String IND = "IND";
    public static final String JAX = "JAX";
    public static final String KC = "KC";
    public static final String LAC = "LAC";
    public static final String LAR = "LA";
    public static final String MIA = "MIA";
    public static final String MIN = "MIN";
    public static final String NE = "NE";
    public static final String NO = "NO";
    public static final String NYG = "NYG";
    public static final String NYJ = "NYJ";
    public static final String OAK = "OAK";
    public static final String PHI = "PHI";
    public static final String PIT = "PIT";
    public static final String SEA = "SEA";
    public static final String SF = "SF";
    public static final String TB = "TB";
    public static final String TEN = "TEN";
    public static final String WAS = "WAS";

    //FULL NAMES
    public static final String CARDINALS = "Arizona Cardinals";
    public static final String FALCONS = "Atlanta Falcons";
    public static final String RAVENS = "Baltimore Ravens";
    public static final String BILLS = "Buffalo Bills";
    public static final String PANTHERS = "Carolina Panthers";
    public static final String BEARS = "Chicago Bears";
    public static final String BENGALS = "Cincinnati Bengals";
    public static final String BROWNS = "Cleveland Browns";
    public static final String COWBOYS = "Dallas Cowboys";
    public static final String BRONCOS = "Denver Broncos";
    public static final String LIONS = "Detroit Lions";
    public static final String PACKERS = "Green Bay Packers";
    public static final String TEXANS = "Houston Texans";
    public static final String COLTS = "Indianapolis Colts";
    public static final String JAGUARS = "Jacksonville Jaguars";
    public static final String CHIEFS = "Kansas City Chiefs";
    public static final String CHARGERS = "Los Angeles Chargers";
    public static final String RAMS = "Los Angeles Rams";
    public static final String DOLPHINS = "Miami Dolphins";
    public static final String VIKINGS = "Minnesota Vikings";
    public static final String PATRIOTS = "New England Patriots";
    public static final String SAINTS = "New Orleans Saints";
    public static final String GIANTS = "New York Giants";
    public static final String JETS = "New York Jets";
    public static final String RAIDERS = "Oakland Raiders";
    public static final String EAGLES = "Philadelphia Eagles";
    public static final String STEELERS = "Pittsburgh Steelers";
    public static final String SEAHAWKS = "Seattle Seahawks";
    public static final String SF49ERS = "San Francisco 49ers";
    public static final String BUCS = "Tampa Bay Buccaneers";
    public static final String TITANS = "Tennessee Titans";
    public static final String REDSKINS = "Washington Redskins";

    //MAP
    public static final Map<String, String> TEAM_MAP = Map.ofEntries(
            Map.entry(ARI, CARDINALS),
            Map.entry(ATL, FALCONS),
            Map.entry(BAL, RAVENS),
            Map.entry(BUF, BILLS),
            Map.entry(CAR, PANTHERS),
            Map.entry(CHI, BEARS),
            Map.entry(CIN, BENGALS),
            Map.entry(CLE, BROWNS),
            Map.entry(DAL, COWBOYS),
            Map.entry(DEN, BRONCOS),
            Map.entry(DET, LIONS),
            Map.entry(GB, PACKERS),
            Map.entry(HOU, TEXANS),
            Map.entry(IND, COLTS),
            Map.entry(JAX, JAGUARS),
            Map.entry(KC, CHIEFS),
            Map.entry(LAC, CHARGERS),
            Map.entry(LAR, RAMS),
            Map.entry(MIA, DOLPHINS),
            Map.entry(MIN, VIKINGS),
            Map.entry(NE, PATRIOTS),
            Map.entry(NO, SAINTS),
            Map.entry(NYG, GIANTS),
            Map.entry(NYJ, JETS),
            Map.entry(OAK, RAIDERS),
            Map.entry(PHI, EAGLES),
            Map.entry(PIT, STEELERS),
            Map.entry(SEA, SEAHAWKS),
            Map.entry(SF, SF49ERS),
            Map.entry(TB, BUCS),
            Map.entry(TEN, TITANS),
            Map.entry(WAS, REDSKINS)
    );

    //== CONSTRUCTORS ==
    private TeamConstants(){}
}