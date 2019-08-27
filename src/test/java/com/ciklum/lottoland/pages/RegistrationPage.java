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
    private List<WebElementFacade> gender;

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

    public void selectGender(int value) {
        gender.get(value).click();
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
    public List<WebElement> getAllLanguagesElements() {
        languagesContainer.click();
        return getDriver().findElements((By.cssSelector("a.ui-corner-all")));
    }

    public List<String> getAllLanguages() {
        return getAllLanguagesElements().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * Based on array filled with 1 and 0 select the languages,
     *
     * @param languagesInput int[], int[i] = 1 - add language  to selected,
     *                       int[i] = 0 - don't add language  to selected
     */
    public void selectLanguages(List<?> languagesInput) {
        getAllLanguagesElements().stream().filter(language ->
                languagesInput.contains(language.getText())).forEach(language ->
                    getJavascriptExecutorFacade().executeScript("arguments[0].click();", language));
    }

    public void selectSkills(int skillIndex) {
        skills.selectByIndex(skillIndex);
    }

    public void selectCountries(int countryIndex) {
        countries.selectByIndex(countryIndex);
    }


    public void selectCountry(int countryIndex) {
        country.click(); // first click on the the field to make the options visible
        List<WebElement> countries = getDriver().findElements((By.cssSelector("li.select2-results__option")));

        //scroll to the option element if it's needed
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView()", countries.get(countryIndex));
        countries.get(countryIndex).click();
    }

    public List<String> getAllYears(){
        return year.thenFindAll(By.tagName("option")).stream()
                .map(WebElementFacade::getValue).collect(Collectors.toList());
    }

    public List<String> getAllMonths(){
        return month.thenFindAll(By.tagName("option")).stream()
                .map(WebElementFacade::getValue).collect(Collectors.toList());
    }

    public List<String> getAllDays(){
        return day.thenFindAll(By.tagName("option")).stream()
                .map(WebElementFacade::getValue).collect(Collectors.toList());
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

    public void uploadPhoto(int filename) {
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView()", photo);
        File photoFile = new File("src/test/resources/photos/" + filename + ".jpg");
        photo.sendKeys(photoFile.getAbsolutePath());
    }

    public void pressSubmit() {
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView()", submitButton);
        submitButton.click();
    }

    /**
     * After submit button is pressed, scroll to top of the screen, to check if some vissible errors appeard.
     * If yes return Optional<String> value of this error/errors, if no return Optional.empty()
     *
     * @return Optional<String>
     */
    public Optional<String> getRegistrationError() {

        //Scroll to top of the screen if we are still on registration page
        if (this.isOnPage()) {
            getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView()", waitFor(getDriver().findElement(By.id("header"))));

            //if there are some visible errors, collect errors text
            if (registrationError.isCurrentlyVisible()) {
                return Optional.of(registrationError.findElements(By.cssSelector("div div.ng-scope")).stream().map(WebElement::getText).collect(Collectors.toList()).toString());
            }
        }
        return Optional.empty();
    }

}
