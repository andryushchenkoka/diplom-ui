package com.demowebshop.pages;

import com.demowebshop.pages.components.Header;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    Header header = new Header();

    public MainPage openPage() {

        open("/");
        return this;
    }

    public String getUserName() {

        return header.getUserName();
    }

    public LoginPage openLoginForm() {

        return header.openLoginForm();
    }

    public MainPage checkLoggedHeader() {

        return header.checkLoggedHeader();
    }

    public MainPage checkUnloggedHeader() {

        return header.checkUnloggedHeader();
    }

    public List<String> getSearchDropResults(String request) {

        return header.getSearchDropResults(request);
    }

    public Boolean areAllItemsContains(String request, List<String> list) {

        return header.areAllItemsContains(request, list);
    }

    public SearchPage goSearch() {

        return header.goSearch();
    }
}
