package com.ciklum.lottoland.steps.serenity;


import com.ciklum.lottoland.data.AbstractDataGenerator;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Here are the steps which need to be executed before or after Story are stored.
 */
public class BeforeAndAfterSteps {

    private final Logger logger = LoggerFactory.getLogger(BeforeAndAfterSteps.class);

    @BeforeStories
    @AfterStories
    public void logAvailableUsers() {
        logUsers();
    }

    /**
     * Get List<String> of users which were not used before in current test run and log them.
     */
    private void logUsers(){
        logger.info("Available users for tests: "+ AbstractDataGenerator.getAvailableUsers());
        }
}
