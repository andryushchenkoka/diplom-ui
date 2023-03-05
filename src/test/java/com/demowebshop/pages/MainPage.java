package com.demowebshop.pages;

import com.demowebshop.pages.components.HeaderComponent;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    HeaderComponent headerComponent = new HeaderComponent();

    public MainPage openPage() {

        open("/");
        return this;
    }

    public String getUserName() {

        return headerComponent.getUserName();
    }

    public LoginPage openLoginForm() {

        return headerComponent.openLoginForm();
    }

    public MainPage checkLoggedHeader() {

        return headerComponent.checkLoggedHeader();
    }

    public MainPage checkUnloggedHeader() {

        return headerComponent.checkUnloggedHeader();
    }

    public List<String> getSearchDropResults(String request) {

        return headerComponent.getSearchDropResults(request);
    }

    public Boolean areAllItemsContains(String request, List<String> list) {

        return headerComponent.areAllItemsContains(request, list);
    }

    public SearchPage goSearch() {

        return headerComponent.goSearch();
    }
}
