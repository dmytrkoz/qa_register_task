package com.ciklum.lottoland.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

/**
 * Page contains all needed elements for work with already registered users
 * In this framework used to check if registration passed successfully, because redirect
 * to this page happens
 */
@DefaultUrl("http://demo.automationtesting.in/WebTable.html")
public class UsersTablePage extends BasicPage {

    @FindBy(css="h4:nth-child(1)")
    private WebElementFacade header;

    @Override
    public boolean pageIsReady(){
        waitFor(header).isCurrentlyVisible();
        return super.pageIsReady();
    }
}
