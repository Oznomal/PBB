package com.lamonzo.pbb.tasks;

import com.jauntium.JauntiumException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Single Thread Class used to submit multiple ballots
 * by re-using the same browser for each submission and
 * skipping Thread.sleep() calls used in the standard
 * implementation. This allows us to submit ballots at
 * a much faster rate, but increases the chances that
 * the bot could be detected.
 */
@Slf4j
@Component
@Scope("prototype")
public class SubmitLightningBallot extends SubmitBallotBase{

    //================================================================================================================//
    //== FIELDS ==
    private boolean firstAttempt = true;
    private boolean failedInnerAttempt = false;

    //================================================================================================================//
    //== PROTECTED METHODS ==
    @Override
    protected void submitBallot() throws JauntiumException{
        preSubmissionProcessing();

        //Process votes until stopped (unlimited) or until foals are reached
        while(!dataModel.getCancellingTask().get()
                && (unlimited || dataModel.getSuccessCount().get() < Integer.parseInt(voteSliderString))){
            try{
                if(failedInnerAttempt || firstAttempt) {
                    firstAttempt = false;

                    //Attempt to visit ballot page
                    browser = browserUtil.getBrowser();
                    visitBallotPage(browser, false);

                    //If we get here then we reached the ballot page and need to reset values
                    failedInnerAttempt = false;
                    attempts = 0;
                }

                submitBallotsByPosition();

                //Revisit the ballot page to keep using the same browser window
                visitBallotPage(browser, false);
                attempts = 0;
            }
            catch(Exception ex){
                log.warn("Error visiting page and submitting ballot | " + ex.getMessage());
                ex.printStackTrace();
                browser.quit();

                if(++attempts >= DEFAULT_MAX_ATTEMPTS)
                    throw new JauntiumException("Exceeded Max Amount of Successive Failures | " + ex.getMessage());

                failedInnerAttempt = true;
            }
        }
    }
}
