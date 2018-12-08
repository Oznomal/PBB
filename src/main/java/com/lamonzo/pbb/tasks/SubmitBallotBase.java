package com.lamonzo.pbb.tasks;

import com.jauntium.Browser;
import com.jauntium.Element;
import com.jauntium.Form;
import com.jauntium.JauntiumException;
import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.controller.dialog.SettingsController;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.Position;
import com.lamonzo.pbb.model.DataModel;
import com.lamonzo.pbb.util.BrowserUtil;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Slf4j
public abstract class SubmitBallotBase extends Task<Boolean> {

    //================================================================================================================//
    //== PRIVATE FIELDS ==
    private static final String EMPTY_BROWSER_LOCATION = "data:";
    private static final int DEFAULT_MIN_SLEEP = 250;
    private static final int DEFAULT_MAX_SLEEP = 1000;

    //== PROTECTED FIELDS ==
    protected static final int DEFAULT_MAX_ATTEMPTS = 5;
    protected static final int WEB_DRIVER_WAIT_TIME = 8;
    protected static final int SELECT_PLAYER_MAX_ATTEMPTS = 10;

    protected static final String JS_SCROLL_TO_TOP = "window.scrollTo(0, 0)";

    @Autowired
    protected DataModel dataModel;

    @Autowired
    protected BrowserUtil browserUtil;

    @Autowired
    protected SettingsController settingsController;


    //================================================================================================================//
    //== ABSTRACT METHODS ==
    protected abstract void submitBallot() throws JauntiumException;

    //================================================================================================================//
    //== PROTECTED METHODS ==

    /**
     * Visits the pro bowl ballot page, but first decides the path to get there
     * either by:
     * 1. Following a direct link
     * 2. Navigating there from NFL.com
     * 3. Getting redirected directly to the ballot from Google
     * 4. Getting redirected to NFL.com from google and then following #2
     *
     * This is to help avoid detection because I'm not sure how far they have gone to detect
     * and defend against bots.
     *
     * @param browser an instance of the Chrome browser
     * @param redirectsAllowed if false the page will always goi directly to the browser, if true then
     *                         the page may possibly redirect to the ballot page based on the options above
     */
    protected void visitBallotPage(Browser browser, boolean redirectsAllowed)
            throws JauntiumException, InterruptedException{

        int option = 0;

        if(redirectsAllowed){
            Random random = new Random();
            option = random.nextInt(1); //TODO: Bump up to 4 if Google Bot Detection is able to be bypassed
        }

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

        //Closes Modal if Present
        try{
            Element modalCloseButton = browser.doc.findFirst(ScrapingConstants.NFL_MODAL_CLOSE_BUTTON);
            if(modalCloseButton != null) {
                modalCloseButton.click();

                //Lightning Mode Skips preformSleep() so we need to ensure that the elements are available after close
                if(dataModel.getLightningMode().get()) {
                    WebDriverWait wait = new WebDriverWait(browser.driver, 5);
                    wait.until(d -> d.findElement(By.xpath(ScrapingConstants.CENTER_TAB_XPATH)));
                }
            }
        }catch(Exception e){
            //If there is an exception here then it most likely means that the modal is no longer
            //there and in that case we want to ignore the exception and continue ...
            log.trace("Error clicking ballot page modal, continuing: " + e.getMessage());
        }
    }


    /**
     * Builds the final ballot of positions and players based on their selection
     * if Auto-Fill is enabled than then the remaining positions can be randomly
     * populated with players.
     *
     * @return a map of positions and their respective players to vote for.
     */
    protected Map<Position, List<Player>> buildFinalBallot(){
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

        //1B. Only return the map with the selected players if Auto-Fill is off
        if(!dataModel.getIsAutoFill().get())
            return ppMap;

        ///////////////////////////////////////////

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

    //================================================================================================================//
    //== PRIVATE METHODS ==

    //***************************************************************************************************************//
    //NAVIGATING TO THE BALLOT PAGE SECTION

    //Navigate to ballot directly
    private void followDirectLink(Browser browser){
        browser.visit(ScrapingConstants.PRO_BOWL_VOTING_URL);
    }

    //Navigate to ballot from the NFL.com page
    private void nflRedirect(Browser browser) throws JauntiumException, InterruptedException{
        if(browser.getLocation().equals(EMPTY_BROWSER_LOCATION))
            browser.visit(ScrapingConstants.NFL_URL);

        Element link = browser.doc.findFirst(ScrapingConstants.NFL_FEATURED_VOTE_LINK);
        if(link != null){
            preformRandomSleep(750, 3000);
        link.click();
        }else{
            throw new JauntiumException("Featured Vote Link Not Located on Main NFL Page");
        }
    }

    //Navigate directly to the ballot page via google search
    private void googleDirectRedirect(Browser browser) throws JauntiumException, InterruptedException{
        simulateGoogleQuery(browser, ScrapingConstants.GOOGLE_PRO_BOWL_VOTING_SEARCH_STRING,
                ScrapingConstants.GOOGLE_SEARCH_BALLOT_LINK);
    }

    //Navigate to the NFL.com page via a google search
    private void googleNflRedirect(Browser browser) throws JauntiumException, InterruptedException{
        simulateGoogleQuery(browser, ScrapingConstants.GOOGLE_NFL_SEARCH_STRING,
                ScrapingConstants.GOOGLE_SEARCH_NFL_LINK);
        nflRedirect(browser);
    }

    //Simulates a user query search on google
    private void simulateGoogleQuery(Browser browser, String searchToken, String linkHtml)
            throws JauntiumException, InterruptedException{
        browser.visit(ScrapingConstants.GOOGLE_URL);

        //Wait a minimum 0f 0.5 secs and max of 1.5 secs before entering in search
        preformRandomSleep(500, 1500);
        Form query = browser.doc.apply(searchToken);

        //Wait a minimum 0.25 secs and max of 1 sec before clicking search
        preformRandomSleep(250, 1000);
        query.submit();

        //Wait a minimum of 1.3 secs and max of 4 secs before clicking link
        preformRandomSleep(1300, 4000);
        Element link = browser.doc.findFirst(linkHtml);

        if(link != null)
            link.click();
        else
            throw new JauntiumException("Google Query Error | Link could not be located in the results: "
                    + linkHtml);
    }

    //END OF NAVIGATING TO THE BALLOT PAGE SECTION
    //***************************************************************************************************************//

    //***************************************************************************************************************//
    //PRIVATE HELPER METHODS SECTION
    protected void preformRandomSleep() throws InterruptedException{
        preformRandomSleep(DEFAULT_MIN_SLEEP, DEFAULT_MAX_SLEEP);
    }

    protected void preformRandomSleep(int min, int max) throws InterruptedException{
        if(!dataModel.getLightningMode().get()){
            Random random = new Random();
            Thread.sleep(min + random.nextInt(max - min));
        }
    }
    //END OF PRIVATE HELPER METHODS SECTION
    //***************************************************************************************************************//
}