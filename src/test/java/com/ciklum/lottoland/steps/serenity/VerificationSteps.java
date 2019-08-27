package com.ciklum.lottoland.steps.serenity;

import com.ciklum.lottoland.pages.RegistrationPage;
import com.ciklum.lottoland.pages.UsersTablePage;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Here are the steps which are needed to verify if user was successfully registered, if redirect to page with<br>
 * Users table happened, we consider that user was created successfully, if no redirect happened after timeout<br>
 * we scroll to the top of the page, to check if there are some visible errors and report them.
 *
 */
public class VerificationSteps {

    private final Logger logger = LoggerFactory.getLogger(VerificationSteps.class);

    private RegistrationPage registrationPage;
    private UsersTablePage usersTablePage;

    /**
     * Verify if registration passed as expected.
     * @param expectedResult - true, expects that registration was succesfull<br>
     *                       - false, expects that registration failed
     */
    @Step
    public void verifyUserRegistration(Boolean expectedResult) {

        if (expectedResult) {  //expect registration successfull
                assertThat("Registration should be successful but failed, possible issue: "+
                                getVisibleErrors().orElse("check the screenshots"),
                        userRegistered(), equalTo(expectedResult));
        } else {  //expect registration failed
            assertThat("Registration should fail but was successful, check screenshots for more info",
                    userRegistered(), equalTo(expectedResult));
        }
    }

    /**
     * @return Text of the error if it's present, if not returns Optional.empty()
     */
    private Optional<String> getVisibleErrors() {
        return registrationPage.getRegistrationError();
    }

    /**
     * Check if there was a redirection from registration page to user table page
     *
     * @return - true if was<br>
     *         - false if wasn't
     *
     */
    private boolean userRegistered() {
        boolean redirected = true;
        try {
            usersTablePage.pageIsReady();
        } catch (TimeoutException e) { // catch serenity timeout exception and finish the test properly
            redirected = false;
            logger.warn("User is not registered due to: "+getVisibleErrors().orElse("check the screenshots"));
        }
        return redirected;
    }
}