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


    public List<?> getAllGenderOptions(){
        return registrationPage.getAllGenderOptions();
    }

    @Step
    public void selectsGender(String gender) {
        registrationPage.selectGender(gender);
    }

    @Step
    public void selectsHobbies(List<?> hobbies) {
        registrationPage.selectHobbies(hobbies);
    }

    public List<?> getAllHobbies(){
        return registrationPage.getAllHobbies();
    }

    @Step
    public void selectsLenguages(List<?> languages) {
        registrationPage.selectLanguages(languages);
    }

    public List<?> getAllLanguages(){
        return registrationPage.getAllLanguages();
    }

    @Step
    public void selectsSkills(String skillValue) {
        registrationPage.selectSkills(skillValue);
    }

    public List<?> getAllSkills(){
        return registrationPage.getAllSkills();
    }

    @Step
    public void selectsCountries(String countriesValue) {
        registrationPage.selectCountries(countriesValue);
    }

    public List<?> getAllCountries(){
        return registrationPage.getAllCountries();
    }

    public List<?> getAllCountryValues(){
        return registrationPage.getAllCountryValues();
    }

    @Step
    public void selectsCountry(String countryValue) {
        registrationPage.selectCountry(countryValue);
    }


    @Step
    public void selectsYear(String year) {
        registrationPage.selectYear(year);
    }

    public List<?> getAllYears(){
        return registrationPage.getAllYears();
    }

    public List<?> getAllMonths(){
        return registrationPage.getAllMonths();
    }

    public List<?> getAllDays(){
        return registrationPage.getAllDays();
    }

    @Step
    public void selectsMonth(String month) {
        registrationPage.selectMonth(month);
    }

    @Step
    public void selectsDay(String day) {
        registrationPage.selectDay(day);
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
    public void uploadsPhoto(String filename) {
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
   