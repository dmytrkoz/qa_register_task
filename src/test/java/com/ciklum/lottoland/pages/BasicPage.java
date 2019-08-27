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

/**
 * Page with common functionality
 */
public class BasicPage extends PageObject {

    @FindBy(id = "header")
    protected WebElementFacade header;

    public boolean pageIsReady() {
        waitFor(".facebook").shouldBeDisplayed();
        return isOnPage();
    }

    public Optional<String> getDefualtUrlValue() {
        return Optional.of(this.getClass().getAnnotation(DefaultUrl.class).value());
    }

    public Optional<String> getPageTitle(){
        return Optional.of(getDriver().getTitle());
    }

    public boolean isOnPage() {
        Optional<String> pageDefaultUrl = getDefualtUrlValue();
        return getDriver().getCurrentUrl().equals(pageDefaultUrl.orElse(
                "Default url for class " + this.getClass().getName()+ "is not provided"));
    }

    protected List<String> getAllDropdownValues(WebElementFacade rootElement){
        return rootElement.thenFindAll(By.tagName("option")).stream()
                .map(WebElementFacade::getValue).collect(Collectors.toList());
    }

    protected void jsClick(WebElement element){
        getJavascriptExecutorFacade().executeScript("arguments[0].click();", element);
    }

    protected void jsScrollAndClick(WebElement element){
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView();",element);
        element.click();
    }

    protected List<WebElement> getHiddenDropdownElements(WebElementFacade rootElement, By selector){
        rootElement.click(); // first click on the the field to make the options visible
        return getDriver().findElements((selector));
    }
}
