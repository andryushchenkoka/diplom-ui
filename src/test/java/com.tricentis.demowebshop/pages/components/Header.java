package com.tricentis.demowebshop.pages.components;

import com.codeborne.selenide.SelenideElement;
import com.tricentis.demowebshop.pages.LoginPage;
import com.tricentis.demowebshop.pages.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Header {

    private final SelenideElement wishlistQuantity = $("span.wishlist-qty"),
            headerButtons = $(".header-links-wrapper");

    private final SelenideElement loginButton = headerButtons.$(".ico-login"),
            logoutButton = headerButtons.$(".ico-logout"),
            usernameButton = headerButtons.$(".account"),
            cartButton = headerButtons.$(".ico-cart"),
            wishlistButton = headerButtons.$(".ico-wishlist");

    public int getWishQuantity() {
        String strQuantity = wishlistQuantity.getText().replaceAll("\\D", "");
        return Integer.parseInt(strQuantity);
    }

    public String getUserName() {

        return usernameButton.getText();
    }

    public LoginPage openLoginForm() {

        loginButton.click();
        return new LoginPage();
    }

    public MainPage checkLoggedHeader() {

        loginButton.shouldNot(visible);
        usernameButton.shouldBe(visible);
        logoutButton.shouldBe(visible);
        cartButton.shouldBe(visible);
        wishlistButton.shouldBe(visible);
        return new MainPage();
    }
}
