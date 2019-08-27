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

    @FindBy(css="div.col-xs-12.myGrid.ui-grid")
    private WebElementFacade userTable;

    @Override
    public boolean pageIsReady(){
        waitFor(userTable).isCurrentlyVisible();
        return super.pageIsReady();
    }
}
