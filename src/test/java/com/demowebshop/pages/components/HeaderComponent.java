package com.demowebshop.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.MainPage;
import com.demowebshop.pages.WishlistPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HeaderComponent {

    private final SelenideElement wishlistQuantity = $("span.wishlist-qty"),
            headerButtons = $(".header-links-wrapper");

    private final SelenideElement registerButton = headerButtons.$(".header-links-wrapper .ico-register"),
            loginButton = $(".header-links-wrapper .ico-login"),
            logoutButton = $(".header-links-wrapper .ico-logout"),
            usernameButton = $(".header-links-wrapper .account"),
            cartButton = $(".header-links-wrapper .ico-cart"),
            wishlistButton = $(".header-links-wrapper .ico-wishlist");

    public int getWishQuantity() {
        sleep(3000);
        String strQuantity = wishlistQuantity.getText().replaceAll("\\D", "");
        return Integer.parseInt(strQuantity);
    }

    public SelenideElement getWishesQuantity() {

        return wishlistQuantity;
    }

    public void checkUsername(String username) {

        usernameButton.shouldHave(Condition.text(username));
    }

    public LoginPage openLoginForm() {

        loginButton.click();
        return new LoginPage();
    }

    public MainPage exitProfile() {

        logoutButton.click();
        return new MainPage();
    }

    public WishlistPage openWishlist() {

        wishlistButton.click();
        return new WishlistPage();
    }

    public MainPage checkLoggedHeader() {

        loginButton.shouldNot(visible);

        usernameButton.shouldBe(visible);
        logoutButton.shouldBe(visible);
        cartButton.shouldBe(visible);
        wishlistButton.shouldBe(visible);
        return new MainPage();
    }

    public MainPage checkUnloggedHeader() {

        usernameButton.shouldNot(visible);
        logoutButton.shouldNot(visible);

        registerButton.shouldBe(visible);
        loginButton.shouldBe(visible);
        cartButton.shouldBe(visible);
        wishlistButton.shouldBe(visible);
        return new MainPage();
    }
}
