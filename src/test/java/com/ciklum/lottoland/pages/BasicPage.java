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

    @FindBy(tagName = "h1")
    protected WebElementFacade h1Header;

    public boolean pageIsReady() {
        waitFor(".facebook").shouldBeDisplayed();
        return true;
    }

    public Optional<String> getDefualtUrlValue(){
        return Optional.of(this.getClass().getAnnotation(DefaultUrl.class).value());
    }


}
