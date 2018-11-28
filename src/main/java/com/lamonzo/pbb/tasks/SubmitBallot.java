package com.lamonzo.pbb.tasks;

import com.jauntium.*;
import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.Position;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.util.BrowserUtil;
import jdk.jfr.Timespan;
import jdk.jshell.tool.JavaShellToolBuilder;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Thread Safe class to handle generating a new browser and submitting a pro bowl ballot
 */
@Slf4j
@Component
@Scope("prototype")
public class SubmitBallot implements Runnable{

    //================================================================================================================//
    //== FIELDS ==
    private final DataModel dataModel;

    //================================================================================================================//
    //== CONSTRUCTORS ==
    @Autowired
    public SubmitBallot(DataModel dataModel){
        this.dataModel = dataModel;
    }

    //================================================================================================================//
    //== PUBLIC METHODS
    @Override
    public void run() {
        Browser browser = BrowserUtil.getBrowser();

        //TODO: Determine if we are going to directly go to the probowl ballot link or be redirected from google
        try {
            visitBallotPage(browser);
            makeBallotSelections(browser);
        }
        catch(JauntiumException | InterruptedException ex){
            log.warn("Error navigating to ballot page: " + ex.getMessage());
        }
    }


    //================================================================================================================//
    //== PRIVATE METHODS

    //*****************************************************************************************************************
    //VISITING BALLOT PAGE SECTION

    /**
     * Visits the pro bowl ballot page, but first decides the path to get there
     * either by:
     * 1. Following a direct link
     * 2. Navigating there from NFL.com
     * 3. Getting redirected from Google
     *
     * This is to help avoid detection because I'm not sure how far they have gone to detect
     * and defend against bots.
     *
     * @param browser an instance of the Chrome browser
     */
    private void visitBallotPage(Browser browser) throws JauntiumException, InterruptedException{
        Random random = new Random();
        int option = random.nextInt(1); //TODO: Bump up to 4 if Google Bot Detection is able to be bypassed

        switch (option) {
            case (0):
                followDirectLink(browser);
                break;
            case (1):
                nflRedirect(browser);
                break;
            case (2):
                googleDirectRedirect(browser);
                break;
            case (3):
                googleNflRedirect(browser);
                break;
            default:
                followDirectLink(browser);
        }
    }

    //Navigate to ballot directly
    private void followDirectLink(Browser browser){
        browser.visit(ScrapingConstants.PRO_BOWL_VOTING_URL);
    }

    //Navigate to ballot from the NFL.com page
    private void nflRedirect(Browser browser) throws JauntiumException, InterruptedException{
        Random random = new Random();

        if(browser.getLocation().equals("data:,"))
            browser.visit(ScrapingConstants.NFL_URL);

        Element link = browser.doc.findFirst(ScrapingConstants.NFL_FEATURED_VOTE_LINK);
        if(link != null){
            Thread.sleep(1200 + random.nextInt(4000));
            link.click();
        }else{
            throw new JauntiumException("Featured Vote Link Not Located on Main NFL Page");
        }
    }

    //Navigate directly to the ballot page via google search
    private void googleDirectRedirect(Browser browser) throws JauntiumException, InterruptedException{
        browser.visit(ScrapingConstants.GOOGLE_URL);
        simulateUserQuery(browser, ScrapingConstants.GOOGLE_PRO_BOWL_VOTING_SEARCH_STRING);
        Element link = browser.doc.findFirst(ScrapingConstants.GOOGLE_SEARCH_BALLOT_LINK);

        if(link != null)
            link.click();
        else
            throw new JauntiumException("Pro Bowl Ballot Link Not Located in Search Results");
    }

    //Navigate to the NFL.com page via a google search
    private void googleNflRedirect(Browser browser) throws JauntiumException, InterruptedException{
        browser.visit(ScrapingConstants.GOOGLE_URL);
        simulateUserQuery(browser, ScrapingConstants.GOOGLE_NFL_SEARCH_STRING);
        Element link = browser.doc.findFirst(ScrapingConstants.GOOGLE_SEARCH_NFL_LINK);

        if(link != null) {
            link.click();
            nflRedirect(browser);
        }else
            throw new JauntiumException("NFL Link Not Located in Search Results");
    }

    //Simulates a user query search on google
    private void simulateUserQuery(Browser browser, String searchToken) throws JauntiumException, InterruptedException{
        Random random = new Random();

        //Wait a minimum 0f 0.5 secs and max of 1.5 secs before entering in search
        Thread.sleep(500 + random.nextInt(1000));
        Form query = browser.doc.apply(searchToken);

        //Wait a minimum 0.25 secs and max of 1 sec before clicking search
        Thread.sleep(250 + random.nextInt(750));
        query.submit();

        //Wait a minimum of 1.3 secs and max of 4 secs before clicking link
        Thread.sleep(1300 + random.nextInt(2700));
    }
    //END OF VISITING BALLOT PAGE SECTION
    //*****************************************************************************************************************

    //*****************************************************************************************************************
    //BALLOT SELECTION SECTION

    /**
     * Selects random players for ballot
     * @param browser an instance of the Chrome browser
     */
    private void makeBallotSelections(Browser browser) throws NotFound, InterruptedException {
        Map<Position, List<Player>> finalBallotMap = buildFinalBallot();
        JavascriptExecutor jse = (JavascriptExecutor) browser.driver;

        //browser.driver.manage().window().maximize();
        for(Position pos : finalBallotMap.keySet()){
            jse.executeScript("window.scrollTo(0, 0)");
            Element tab = browser.doc.findFirst(pos.getTabHtmlLink());
            tab.click();

            preformRandomSleep(500);

            for(Player player : finalBallotMap.get(pos)){
                int attempts = 0;
                String scroll = "0";

                while(attempts < 10) {
                    try {
                        jse.executeScript("window.scrollTo(0, " + scroll + ")");


                        //Configure XPath Strings
                        String playerDivXPath = ScrapingConstants.PLAYER_DIV_XPATH_PREFIX + player.getHtmlIdentifier()
                                + ScrapingConstants.PLAYER_DIV_XPATH_SUFFIX;
                        String playerVoteXPath = playerDivXPath + ScrapingConstants.PLAYER_VOTE_BTN_XPATH;

                        WebDriverWait wait = new WebDriverWait(browser.driver, 10);

                        //preformRandomSleep(1000);

                        //Simulate Mouse Hover
                        System.out.println("Trying mouse hover for " + playerDivXPath);
                        WebElement playerDiv = wait.until(d -> d.findElement(By.xpath(playerDivXPath)));

//                        jse.executeScript("arguments[0].scrollIntoView(true);", playerDiv);
//                        Thread.sleep(2500);

                        Actions actions = new Actions(browser.driver);
                        actions.moveToElement(playerDiv).perform();

                        preformRandomSleep(1000);

                        //Click The Vote Button
                        System.out.println("Trying Player Vote: " + playerVoteXPath);
                        WebElement voteBtn = wait.until(d -> d.findElement(By.xpath(playerVoteXPath)));
                        voteBtn.click();
                        break;
                    } catch (ElementNotSelectableException | ElementNotInteractableException |  TimeoutException e) {
                        log.warn("Unable to select player or vote button for: " + player.getName()
                                + " | Attempt " + (++attempts));

                        int value = Integer.parseInt(scroll);
                        value += 500;
                        scroll = Integer.toString(value);
                    }
                }
            }
        }
    }

    //Builds the final ballot by taking the players that the user selected and mixing in a random number
    //of other selections for the remaining positions.
    private Map<Position, List<Player>> buildFinalBallot(){
        //1. Divide players up into hash map for positions
        Map<Position, List<Player>> ppMap = new HashMap<>();
        for(Player player : dataModel.getBallotList()){
            if(!ppMap.containsKey(player.getPosition())){
                List<Player> playerList = new ArrayList<>();
                playerList.add(player);
                ppMap.put(player.getPosition(), playerList);
            }else{
                ppMap.get(player.getPosition()).add(player);
            }
        }

        //2. Get the remaining positions
        List<Position> remainingPositions = dataModel.getAllPositions();
        for(Position pos : ppMap.keySet())
            remainingPositions.remove(pos);

        //3. Determine how many extra random positions we are going to vote for
        Random random = new Random();
        int extraVotes = random.nextInt(remainingPositions.size() + 1);

        //4. Determine how many times we will vote for each random position
        Map<Position, Integer> extraVotesPosMap = new HashMap<>();
        for(int i = 0; i < extraVotes; i++){
            int index = random.nextInt(remainingPositions.size());

            Position pos = remainingPositions.get(index);
            int posVotes = random.nextInt(pos.getMaxVotes()) + 1;

            extraVotesPosMap.put(pos, posVotes);

            remainingPositions.remove(index);
        }

        //5. Add random players from the random positions that we are going to vote for to the ppMap
        for(Position pos : extraVotesPosMap.keySet()){
            List<Player> players = new ArrayList<>(dataModel.getPlayersByPosition(pos.getPositionName()));
            List<Player> toAdd = new ArrayList<>();
            for(int i = 0; i < extraVotesPosMap.get(pos); i++){
                int index = random.nextInt(players.size());
                toAdd.add(players.get(index));
                players.remove(index);
            }
            ppMap.put(pos, toAdd);
        }

        return ppMap;
    }

    private void preformRandomSleep() throws InterruptedException{
        Random random = new Random();
        Thread.sleep(random.nextInt(1500));
    }

    private int preformRandomSleep(int minimum) throws InterruptedException{
        Random random = new Random();
        int randomSleep = random.nextInt(1500);
        Thread.sleep(randomSleep + minimum);
        return randomSleep + minimum;
    }

    //END OF BALLOT SELECTION
    //*****************************************************************************************************************
}
