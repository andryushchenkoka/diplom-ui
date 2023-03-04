package com.tricentis.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private final SelenideElement headerButtons = $(".header-links-wrapper"),
            loginButton = headerButtons.$(".ico-login"),
            logoutButton = headerButtons.$(".ico-logout"),
            usernameButton = headerButtons.$(".account"),
            cartButton = headerButtons.$(".ico-cart"),
            wishlistButton = headerButtons.$(".ico-wishlist");


    public MainPage openPage() {

        open("/");
        return this;
    }

    public LoginPage openLoginForm() {

        loginButton.click();
        return new LoginPage();
    }

    public String getUserName() {

        return usernameButton.getText();
    }

    public MainPage checkLoggedHeader() {

        loginButton.shouldNot(visible);
        usernameButton.shouldBe(visible);
        logoutButton.shouldBe(visible);
        cartButton.shouldBe(visible);
        wishlistButton.shouldBe(visible);
        return this;
    }
}
