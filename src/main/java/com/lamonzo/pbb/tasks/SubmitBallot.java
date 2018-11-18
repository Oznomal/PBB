package com.lamonzo.pbb.tasks;

import com.jauntium.Browser;
import com.jauntium.Element;
import com.jauntium.Form;
import com.jauntium.JauntiumException;
import com.lamonzo.pbb.constants.ScrapingConstants;
import com.lamonzo.pbb.util.BrowserUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Thread Safe class to handle generating a new browser and submitting a pro bowl ballot
 */
@Slf4j
public class SubmitBallot implements Runnable{
    @Override
    public void run() {
        Browser browser = BrowserUtil.getBrowser();

        //TODO: Determine if we are going to directly go to the probowl ballot link or be redirected from google
        try {
            visitBallotPage(browser);
        }
        catch(JauntiumException | InterruptedException ex){
            log.warn("Error navigating to ballot page: " + ex.getMessage());
        }
    }


    //================================================================================================================//
    //== PRIVATE METHODS

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
        int option = random.nextInt(2); //TODO: Bump up to 4 if Google Bot Detection is able to be bypassed

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
}
