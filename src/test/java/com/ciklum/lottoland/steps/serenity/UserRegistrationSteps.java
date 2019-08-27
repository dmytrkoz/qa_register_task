package com.ciklum.lottoland.steps.serenity;

import com.ciklum.lottoland.pages.RegistrationPage;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Here are the steps which specify the user interaction with the Registration Page, to enter some data or press the
 * button
 */
public class UserRegistrationSteps {

    private RegistrationPage registrationPage;

    @Step
    public void entersFullName(String name, String surname) {
        registrationPage.enterFirstName(name);
        registrationPage.enterLastName(surname);
    }

    @Step
    public void entersAddress(String address) {
        registrationPage.enterAddress(address);
    }

    @Step
    public void entersEmail(String email) {
        registrationPage.enterEmail(email);
    }

    @Step
    public void entersPhone(String phone) {
        registrationPage.enterPhone(phone);
    }

    @Step
    public void selectsGender(int gender) {
        registrationPage.selectGender(gender);
    }

    @Step
    public void selectsHobbies(List<?> hobbies) {
        registrationPage.selectHobbies(hobbies);
    }

    public List<String> getAllHobbies(){
        return registrationPage.getAllHobbies();
    }

    @Step
    public void selectsLenguages(int[] languages) {
        registrationPage.selectLanguages(languages);
    }

    @Step
    public void selectsSkills(int skillIndex) {
        registrationPage.selectSkills(skillIndex);
    }

    @Step
    public void selectsCountries(int countriesIndex) {
        registrationPage.selectCountries(countriesIndex);
    }

    @Step
    public void selectsCountry(int countriesIndex) {
        registrationPage.selectCountry(countriesIndex);
    }

    @Step
    public void selectsDateOfBirth(int[] dateOfBirth) {
        registrationPage.selectDateOfBirth(dateOfBirth);
    }

    @Step
    public void entersPassword(String password) {
        registrationPage.enterPassword(password);
    }

    @Step
    public void entersPasswordConfirmation(String passwordConfirmation) {
        registrationPage.enterPasswordConfirmation(passwordConfirmation);
    }

    @Step
    public void uploadsPhoto(int filename) {
        registrationPage.uploadPhoto(filename);
    }

    @Step
    public void pressesSubmitButton() {
        registrationPage.pressSubmit();
    }

    @Step
    public void isOnRegistrationPage() {
        registrationPage.open();
        assertThat("Page was not fully loaded in needed time", registrationPage.pageIsReady(), equalTo(true));
        assertThat("Current url is not the same which we followed", registrationPage.getDriver().getCurrentUrl(),
                equalTo(registrationPage.getDefualtUrlValue().orElse("DefaultUrl is not set for this class")));
    }

}
   