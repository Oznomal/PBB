package com.lamonzo.pbb.tasks;

import com.jauntium.Browser;
import com.jauntium.Element;
import com.jauntium.NotFound;
import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.Position;
import com.lamonzo.pbb.domain.Stat;
import com.lamonzo.pbb.util.BrowserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Thread safe class to handle scraping the player data (stats, positions, pro-bowl nominees)
 * from the pro-bowl ballot page.
 *
 * This is used when the application starts up
 */
@Slf4j
@Component
@Scope("prototype")
public class UpdatePlayerData implements Runnable {

    //== FIELDS ==
    private String positionTabHtmlLink;

    //== CONSTRUCTOR ==
    public UpdatePlayerData(){}

    public UpdatePlayerData(String positionTabHtmlLink){
        this.positionTabHtmlLink = positionTabHtmlLink;
    }

    //== PUBLIC METHODS ==
    @Override
    public void run() {
        Browser browser = BrowserUtil.getBrowser();

        try {
            //visit the Pro-Bowl page
            browser.visit(ScrapingConstants.PRO_BOWL_VOTING_URL);

            //Click on the position tab
            Element tab = browser.doc.findFirst(positionTabHtmlLink);
            tab.click();

            //Gather the position information (position name, max votes, and applicable stat types)
            Position position = collectPositionData(browser);
            Map<String, String> statMap = collectPositionStatTypes(browser);

            //Gather the player information
            collectPlayerData(browser, position, statMap);
        }catch(NotFound e){
            //TODO: Handle Exception or use Callable if I want to handle exception in the calling class
            log.warn("Something went wrong " + e.getMessage() );
        }finally {
            browser.close();
        }
    }

    //== PRIVATE METHODS ==
    //Collects the position data (position title and max votes for that position)
    private Position collectPositionData(Browser browser) throws NotFound{

        List<Element> positionInfoList = browser.doc.findFirst(ScrapingConstants.POSITION_INFO_LIST).getChildElements();

        //Will split the voting info string which uses this format: '0 of 6 votes'
        String [] votingInfo = positionInfoList.get(2).getTextContent().split(" ");

        //Remove the 's' from the end of the position name except from 'safeties' which will be replaced with 'safety'
        String pos = positionInfoList.get(0).getTextContent().trim();
        if(pos!= null && !pos.isEmpty() && pos.substring(pos.length() - 8).equalsIgnoreCase("Safeties"))
            pos = pos.substring(0, pos.length() - 8) + "Safety";

        else if(pos != null && !pos.isEmpty() && pos.charAt(pos.length()-1) == 's')
            pos = pos.substring(0, pos.length()-1);

        Position position = new Position();
        position.setPositionName(pos);
        position.setMaxVotes(Integer.parseInt(votingInfo[2]));

        //TODO: Save the position to the DB

        return position;
    }

    //Creates a map of the statID (stat1, stat2, etc) and the stat type (yards, forced fumbles, etc)
    private Map<String, String> collectPositionStatTypes(Browser browser) throws NotFound {
        //TODO: Figure out way to map the stat type data effectively
        Map<String, String> statMap = new HashMap<>();
        Element statParent = browser.doc.findFirst(ScrapingConstants.POSITION_STAT_TYPES_LIST);

        for (int i = 1; i <= 4; i++) {
            String statId = "stat" + i;
            String statType = statParent.findFirst(ScrapingConstants.STAT_PREFIX + statId
                    + ScrapingConstants.STAT_SUFFIX).getTextContent().trim();
            if (!statType.isEmpty())
                statMap.put(statId, statType);
            else
                break;
        }

        return statMap;
    }

    private void collectPlayerData(Browser browser, Position playerPosition, Map<String, String> statMap)
            throws NotFound{
        List<Element> players = browser.doc.findEvery(ScrapingConstants.PLAYER_INFO_DIV).toList();

        for(Element playerElement : players){
            Element parent = playerElement.getParent();

            Player player = new Player();
            player.setName(scrapePlayerName(playerElement));
            player.setPosition(playerPosition);
            player.setHtmlIdentifier(parent.getParent().getAttribute(ScrapingConstants.PLAYER_HTML_ID_ATTRIBUTE));
            player.setTeam(parent.findFirst(ScrapingConstants.PLAYER_TEAM_LOGO)
                    .getAttribute(ScrapingConstants.PLAYER_TEAM_NAME_ATTRIBUTE));
            player.setStats(scrapePlayerStats(parent, statMap));

            //TODO: Save the player to the DB
            System.out.println(player);
        }
    }

    //Scrapes the players name from the attribute (has to reverse the order because name is transposed originally)
    private String scrapePlayerName(Element playerElement){
        String [] arr = playerElement.getAttribute(ScrapingConstants.PLAYER_NAME_ATTRIBUTE).trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = arr.length - 1; i >= 0; i--)
            sb.append((i != 0) ? arr[i] + " " : arr[i]);
        return sb.toString();
    }

    //Scrapes the players stats from the playerElement
    private List<Stat> scrapePlayerStats(Element playerParent, Map<String, String> statTypeMap) throws NotFound{
        List<Stat> playerStats = new ArrayList<>();
        if(!statTypeMap.isEmpty()){
            Element statsParent = playerParent.getFirst(ScrapingConstants.PLAYER_STATS_LIST);
            for (String statId : statTypeMap.keySet()) {
                String value = statsParent.getFirst(ScrapingConstants.STAT_PREFIX + statId
                        + ScrapingConstants.STAT_SUFFIX).getTextContent().trim();

                Stat stat = new Stat();
                stat.setType(statTypeMap.get(statId));
                stat.setValue(convertStatToNumericalValue(value));

                playerStats.add(stat);
            }
        }

        return playerStats;
    }

    //Converts the string version of the stat to a double ('--' stats will return min double value)
    private double convertStatToNumericalValue(String stat){
        //Stats that the player doesn't have but are valid for the position will be represented by min double val in db
        if(stat == null || stat.isEmpty() || stat.equals("--"))
            return Double.MIN_VALUE;

        //Replace comma in stats that are above 999
        stat = stat.replace(",", "");

        return Double.parseDouble(stat);
    }
}
