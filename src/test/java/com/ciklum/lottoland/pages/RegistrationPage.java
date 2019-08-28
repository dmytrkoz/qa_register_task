package com.ciklum.lottoland.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Page contains all needed elements for user registration
 */
@DefaultUrl("http://demo.automationtesting.in/Register.html")
public class RegistrationPage extends BasicPage {

    @FindBy(css = "input[ng-model=FirstName]")
    private WebElementFacade firstName;

    @FindBy(css = "input[ng-model=LastName]")
    private WebElementFacade lastName;

    @FindBy(css = "textarea[ng-model=Adress]")
    private WebElementFacade address;

    @FindBy(css = "#eid > input")
    private WebElementFacade email;

    @FindBy(css = "input[ng-model=Phone]")
    private WebElementFacade phone;

    @FindBy(css = "input[type=radio]")
    private List<WebElementFacade> genders;

    @FindBy(css = "input[type=checkbox]")
    private List<WebElementFacade> hobbies;

    @FindBy(id = "msdd")
    private WebElementFacade languagesContainer;

    @FindBy(css = "#Skills")
    private WebElementFacade skills;

    @FindBy(id = "countries")
    private WebElementFacade countries;

    @FindBy(xpath = "//*[@id='country']//parent::div")
    private WebElementFacade country;

    @FindBy(id = "yearbox")
    private WebElementFacade year;

    @FindBy(css = "select[ng-model=monthbox]")
    private WebElementFacade month;

    @FindBy(id = "daybox")
    private WebElementFacade day;

    @FindBy(id = "firstpassword")
    private WebElementFacade password;

    @FindBy(id = "secondpassword")
    private WebElementFacade confirmPassword;

    @FindBy(id = "submitbtn")
    private WebElementFacade submitButton;

    @FindBy(id = "imagesrc")
    private WebElementFacade photo;

    @FindBy(css = "div.container-fluid.ng-scope")
    private WebElementFacade registrationError;

    public void enterFirstName(String value) {
        firstName.type(value);
    }

    public void enterLastName(String value) {
        lastName.type(value);
    }

    public void enterAddress(String value) {
        address.type(value);
    }

    public void enterEmail(String value) {
        email.type(value);
    }

    public void enterPhone(String value) {
        phone.type(value);
    }


    public List<String> getAllGenderOptions(){
        return genders.stream().map(WebElementFacade::getValue).collect(Collectors.toList());

    }
    public void selectGender(String genderValue) {
        genders.stream().filter(gender -> gender.getValue().equals(genderValue)).forEach(WebElementFacade::click);
    }


    /**
     * Get all the hobbies possible values
     *
     * @return List<WebElementFacade> with WebElementFacade getValue()
     */
    public List<String> getAllHobbies() {
        return hobbies.stream().map(WebElementFacade::getValue).collect(Collectors.toList());
    }

    /**
     * Based on array filled with 1 and 0 select the checkbox items
     *
     * @param hobbiesInputs int[], int[i] = 1 - select checkbox,
     *                      int[i] = 0 - don't select checkbox
     */
    public void selectHobbies(List<?> hobbiesInputs){
        hobbies.stream().filter(hobby ->
                hobbiesInputs.contains(hobby.getValue())).forEach(WebElementFacade::click);
    }

    /**
     * Get all the hobbies possible values
     *
     * @return List<WebElementFacade> with WebElementFacade getValue()
     */
    private List<WebElement> getAllLanguagesElements() {
        return getHiddenDropdownElements(languagesContainer,By.cssSelector("a.ui-corner-all"));
    }

    public List<String> getAllLanguages() {
        return getAllLanguagesElements().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * Based on available options select the languages,
     *
     * @param languagesInput - which languages to select
     */
    public void selectLanguages(List<?> languagesInput) {
        getAllLanguagesElements().stream().filter(language ->
                languagesInput.contains(language.getText())).forEach(language ->
                    jsClick(language));
    }

    public List<String> getAllSkills(){
        return getAllDropdownValues(skills);
    }

    public void selectSkills(String skillValue) {
        skills.selectByValue(skillValue);
    }

    public List<String> getAllCountries(){
        return getAllDropdownValues(country);
    }

    public void selectCountries(String  countryValue) {
        countries.selectByValue(countryValue);
    }

    public List<String> getAllCountryValues(){
        return getHiddenDropdownElements(country,By.cssSelector("li.select2-results__option"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectCountry(String countryValue) {
        List<WebElement> countries = getDriver().findElements(By.cssSelector("li.select2" + "-results__option"));
        jsScrollAndClick(countries.stream().filter(country -> country.getText().equals(countryValue)).findFirst().get());
    }

    public List<String> getAllYears(){
        return getAllDropdownValues(year);
    }

    public List<String> getAllMonths(){
        return getAllDropdownValues(month);
    }

    public List<String> getAllDays(){
        return getAllDropdownValues(day);
    }

    public void selectYear(String yearValue){
        year.selectByValue(yearValue);
    }

    public void selectMonth(String monthValue){
        month.selectByValue(monthValue);
    }

    public void selectDay(String dayValue){
        day.selectByValue(dayValue);
    }


    public void enterPassword(String newPassword) {
        password.type(newPassword);
    }

    public void enterPasswordConfirmation(String newPasswordConfirmation) {
        confirmPassword.type(newPasswordConfirmation);
    }

    public void uploadPhoto(String filename) {
        if (!filename.equals("without file")) {
            getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView();", photo);
            File photoFile = new File(filename);
            photo.sendKeys(photoFile.getAbsolutePath());
        }
    }

    public void pressSubmit() {
        jsClick(submitButton);
    }

    /**
     * After submit button is pressed, scroll to top of the screen, to check if some vissible errors appeard.
     * If yes return Optional<String> value of this error/errors, if no return Optional.empty()
     *
     * @return Optional<String>
     */
    public Optional<String> getRegistrationError() {

        //Scroll to top of the screen if we are still on registration page
        if (isOnPage()) {
            getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView();",
                    waitFor(getDriver().findElement(By.id("header"))));

            //if there are some visible errors, collect errors text
            if (registrationError.isCurrentlyVisible()) {
                return Optional.of(registrationError.findElements(By.cssSelector("div div.ng-scope")).stream().map(WebElement::getText).collect(Collectors.toList()).toString());
            }
        }
        return Optional.empty();
    }

}
