package com.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import com.demowebshop.helpers.Cookies;

import static com.codeborne.selenide.Selenide.*;

public class ProductPage {

    private final SelenideElement addToWishlistButton = $("input[value='Add to wishlist']");

    public ProductPage openPage(String url) {

        open(url);
        return this;
    }

    public ProductPage refreshPage() {

        refresh();
        return this;
    }

    public ProductPage setCookie(String cookie) {

        Cookies.setCookie(cookie);
        return this;
    }

    public ProductPage addToWishlist() {

        addToWishlistButton.click();
        return this;
    }
}
