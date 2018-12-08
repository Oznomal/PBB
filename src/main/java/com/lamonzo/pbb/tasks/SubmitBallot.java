package com.lamonzo.pbb.tasks;

import com.jauntium.Browser;
import com.jauntium.Element;
import com.jauntium.JauntiumException;
import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.Position;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Thread Safe class to handle generating a new browser and submitting a pro bowl ballot
 */
@Slf4j
@Component
@Scope("prototype")
public class SubmitBallot extends SubmitBallotBase {

    //================================================================================================================//
    //== PUBLIC METHODS
    @Override
    public Boolean call() {
        try {
            submitBallot();
            return true;
        }catch(JauntiumException e){
            log.error("Something went wrong, Shutting Down Thread |  " + e.getMessage() );
            return false;
        }
    }

    //================================================================================================================//
    //== PROTECTED METHODS
    @Override
    protected void submitBallot() throws JauntiumException {
        Map<Position, List<Player>> finalBallotMap = null;
        Browser browser = null;
        int attempts = 0;

        //IF AUTO-FILL IS TURNED OFF THEN WE ONLY NEED TO BUILD THE MAP THE FIRST TIME
        //IF AUTO FILL IS TURNED ON THEN WE NEED TO BUILD THE MAP WITH EVERY NEW REQUEST
        if(!dataModel.getIsAutoFill().get())
            finalBallotMap = buildFinalBallot();


        //GET THE STRING VALUE OF THE VOTE GOALS, DOING IT LIKE THIS DECOUPLES
        //THE VALUES OF THE SLIDER SO I WON'T NEED TO ALTER THIS CODE IF I CHANGE THE VALUES
        double votingGoalsValue = dataModel.getVotingGoals().get();
        String voteSliderString = settingsController.getVoteGoalSlider().getLabelFormatter()
                .toString(votingGoalsValue).trim();
        boolean unlimited = voteSliderString.equalsIgnoreCase(settingsController.getUNLIMITED());

        while(unlimited || dataModel.getSuccessCount().get() < Integer.parseInt(voteSliderString)){

            try {
                browser = browserUtil.getBrowser();
                JavascriptExecutor jse = (JavascriptExecutor) browser.driver;
                WebDriverWait wait = new WebDriverWait(browser.driver, WEB_DRIVER_WAIT_TIME);

                //BUILD A NEW LIST OF EXTRA VOTES IF AUTO-FILL IS SELECTED
                if(dataModel.getIsAutoFill().get())
                    finalBallotMap = buildFinalBallot();

                visitBallotPage(browser, true);

                for(Position pos : finalBallotMap.keySet()) {
                    jse.executeScript(JS_SCROLL_TO_TOP);

                    Element tab = browser.doc.findFirst(pos.getTabHtmlLink());
                    preformRandomSleep(500, 1000);
                    tab.click();
                    preformRandomSleep();

                    log.info("Processing Position Tab: " + pos.getPositionName());

                    for(Player player : finalBallotMap.get(pos)) {
                        //CONFIGURE XPATH STRINGS
                        String playerDivXPath = ScrapingConstants.PLAYER_DIV_XPATH_PREFIX + player.getHtmlIdentifier()
                                + ScrapingConstants.PLAYER_DIV_XPATH_SUFFIX;
                        String playerVoteXPath = playerDivXPath + ScrapingConstants.PLAYER_VOTE_BTN_XPATH;

                        int selectPlayerAttempt = 0;
                        String scroll = "0";

                        //ATTEMPT TO FIND PLAYERS ON THE PAGE FOR MAX OF N TIMES WHILE SCROLLING DOWN FURTHER WITH EACH ATTEMPT
                        while(selectPlayerAttempt < SELECT_PLAYER_MAX_ATTEMPTS) {
                            try{
                                jse.executeScript("window.scrollTo(0, " + scroll + ")"); //SCROLL TO POSITION ON PAGE

                                WebElement playerDiv = wait.until(d -> d.findElement(By.xpath(playerDivXPath)));
                                Actions actions = new Actions(browser.driver);
                                actions.moveToElement(playerDiv).perform(); //SIMULATE MOUSE HOVER EFFECT

                                preformRandomSleep();

                                WebElement voteBtn = wait.until(d -> d.findElement(By.xpath(playerVoteXPath)));
                                voteBtn.click(); //CLICK THE VOTE BUTTON THAT APPEARS ON HOVER
                                break;
                            }
                            catch(ElementNotSelectableException | ElementNotInteractableException |  TimeoutException e){
                                if(++selectPlayerAttempt <= SELECT_PLAYER_MAX_ATTEMPTS){
                                    log.warn("Unable to select player or vote button for: "
                                            + player.getName()+ " | Attempt " + selectPlayerAttempt);

                                    int scrollPosition = Integer.parseInt(scroll)
                                            + (browser.driver.manage().window().getSize().getHeight());
                                    scroll = Integer.toString(scrollPosition);
                                }
                                else {
                                    log.error("Exceeded attempts to find " + player.getName() + " | "
                                            + player.getPosition().getPositionName() + " | Moving to next player");
                                    //TODO: Implement way to provide feedback if player was part of true ballot
                                }
                            }
                        }
                    }
                }

                //SUBMIT THE BALLOT FOR THE FINAL POSITION
                Element submitButton = browser.doc.findFirst(ScrapingConstants.SUBMIT_BUTTON);
                submitButton.click();

                //CLICK THE VOTE AGAIN BUTTON & INCREMENT COUNT TO RESTART THE PROCESS
                wait.until(d -> d.findElement(By.xpath(ScrapingConstants.VOTE_AGAIN_BTN_XPATH)));

                if(browser.getLocation().equalsIgnoreCase(ScrapingConstants.VOTING_THANK_YOU_PAGE_URL))
                    dataModel.incrementSuccessCount();

                browser.quit();
                attempts = 0;
            }
            catch(InterruptedException ex){
                log.warn("Error visiting page and submitting ballot | " + ex.getMessage());
                ex.printStackTrace();
                browser.quit();

                if(++attempts >= DEFAULT_MAX_ATTEMPTS)
                    throw new JauntiumException("Exceeded Max Amount of Successive Failures | " + ex.getMessage());            }

        }
    }
}
