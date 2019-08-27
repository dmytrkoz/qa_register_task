package com.ciklum.lottoland.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import java.util.Optional;

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


}
