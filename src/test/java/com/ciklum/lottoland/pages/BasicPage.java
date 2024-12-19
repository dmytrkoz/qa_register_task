package com.ciklum.lottoland.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BasicPage extends PageObject {

    /**
     * Waits till the facebook span will be visible and checks that the url is as needed.
     * @return
     */
    public boolean pageIsReady() {
        waitFor(".facebook").shouldBeDisplayed();
        return isOnPage();
    }

    /**
     * If page ahs a DefaultUrl annotation, will return the value using reflection
     * @return
     */
    public Optional<String> getDefualtUrlValue() {
        return Optional.of(this.getClass().getAnnotation(DefaultUrl.class).value());
    }

    public Optional<String> getPageTitle(){
        return Optional.of(getDriver().getTitle());
    }

    /**
     * Checks if current url equals expected page url
     * @return
     */
    public boolean isOnPage() {
        Optional<String> pageDefaultUrl = getDefualtUrlValue();
        return getDriver().getCurrentUrl().equals(pageDefaultUrl.orElse(
                "Default url for class " + this.getClass().getName()+ "is not provided"));
    }

    /**
     * Returns all String values of the dropdown options
     * @param rootElement select element
     * @return List of dropdowm options values
     */
    protected List<String> getAllDropdownValues(WebElementFacade rootElement){
        return rootElement.thenFindAll(By.tagName("option")).stream()
                .map(WebElementFacade::getValue).collect(Collectors.toList());
    }

    /**
     * Use js click() function to click on element, useful if element is not currently in visible part of the screen
     * @param element - target element
     */
    protected void jsClick(WebElement element){
        getJavascriptExecutorFacade().executeScript("arguments[0].click();", element);
    }

    /**
     * Use js scrollIntoView() function to make element visible and then click on element
     * @param element - target element
     */
    protected void jsScrollAndClick(WebElement element){
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView();",element);
        element.click();
    }

    /**
     * For some dropdown elements options are hidden or not present until the click.
     * Clicks on element and then gets the options
     * @param rootElement element to click on
     * @param selector selector, how to find the options
     * @return
     */
    protected List<WebElement> getHiddenDropdownElements(WebElementFacade rootElement, By selector){
        jsScrollAndClick(rootElement); // first click on the the field to make the options visible
        return getDriver().findElements((selector));
    }
}
