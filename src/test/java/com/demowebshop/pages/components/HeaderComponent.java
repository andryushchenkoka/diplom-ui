package com.demowebshop.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.MainPage;
import com.demowebshop.pages.SearchPage;
import com.demowebshop.pages.WishlistPage;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HeaderComponent {

    private final SelenideElement wishlistQuantity = $("span.wishlist-qty"),
            headerButtons = $(".header-links-wrapper"),
            searchInput = $("input#small-searchterms"),
            searchButton = $("input[value='Search']");

    private final SelenideElement registerButton = headerButtons.$(".header-links-wrapper .ico-register"),
            loginButton = $(".header-links-wrapper .ico-login"),
            logoutButton = $(".header-links-wrapper .ico-logout"),
            usernameButton = $(".header-links-wrapper .account"),
            cartButton = $(".header-links-wrapper .ico-cart"),
            wishlistButton = $(".header-links-wrapper .ico-wishlist");

    private final ElementsCollection searchResults = $$("[role=presentation] a");

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

    public MainPage exitProfile() {

        logoutButton.click();
        return new MainPage();
    }

    public WishlistPage openWishlist() {

        wishlistButton.click();
        return new WishlistPage();
    }

    public List<String> getSearchDropResults(String request) {

        searchInput.setValue(request);
        sleep(3000);
        return searchResults.texts();
    }

    public Boolean areAllItemsContains(String request, List<String> list) {

        for (String item : list)
            if (!item.toLowerCase().contains(request))
                return false;
        return true;
    }

    public SearchPage goSearch() {

        searchButton.click();
        return new SearchPage();
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
