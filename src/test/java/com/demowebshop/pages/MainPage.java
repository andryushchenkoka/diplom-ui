package com.demowebshop.pages;

import com.demowebshop.pages.components.HeaderComponent;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    HeaderComponent headerComponent = new HeaderComponent();

    public MainPage openPage() {

        open("/");
        return this;
    }

    public void checkUsername(String username) {

        headerComponent.checkUsername(username);
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
}
