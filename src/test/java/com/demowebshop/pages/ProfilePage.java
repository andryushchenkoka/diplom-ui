package com.demowebshop.pages;

import com.demowebshop.pages.components.HeaderComponent;
import com.demowebshop.helpers.Cookies;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

public class ProfilePage {

    HeaderComponent headerComponent = new HeaderComponent();

    public ProfilePage openPage() {
        open("/customer/info");
        return this;
    }

    public ProfilePage setCookie(String cookie) {

        Cookies.setCookie(cookie);
        return this;
    }

    public ProfilePage refreshPage() {

        refresh();
        return this;
    }

    public int getWishQuantity() {

        return headerComponent.getWishQuantity();
    }

    public String getProfileName() {

        return headerComponent.getUserName();
    }

    public void exitProfile() {

        headerComponent.exitProfile();
    }
}
