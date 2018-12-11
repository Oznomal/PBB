package com.lamonzo.pbb.tasks;

import com.jauntium.JauntiumException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Single Thread Class used to submit multiple ballots
 * by generating a new browser for each submission.
 * This implementation helps avoid bot detection by
 * preforming random sleeps throughout the process
 * to simulate user delays and creating new browser
 * with each submission which will allow you to get
 * a new proxy for each submission if rotating proxies.
 */
@Slf4j
@Component
@Scope("prototype")
public class SubmitBallot extends SubmitBallotBase {

    //================================================================================================================//
    //== PROTECTED METHODS
    @Override
    protected void submitBallot() throws JauntiumException {
        preSubmissionProcessing();

        //Process votes until stopped (unlimited) or until goals are reached
        while(!dataModel.getCancellingTask().get()
                && (unlimited || dataModel.getSuccessCount().get() < Integer.parseInt(voteSliderString))){
            try {
                browser = browserUtil.getBrowser();
                visitBallotPage(browser, true);

                submitBallotsByPosition();

                //Quit the browser because a new instance will be used for the next request
                browser.quit();
                attempts = 0;
            }
            catch(Exception ex){
                log.warn("Error visiting page and submitting ballot | " + ex.getMessage());
                ex.printStackTrace();
                browser.quit();

                if(++attempts >= DEFAULT_MAX_ATTEMPTS)
                    throw new JauntiumException("Exceeded Max Amount of Successive Failures | " + ex.getMessage());
            }
        }
    }
}
