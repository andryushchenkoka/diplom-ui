package com.demowebshop.pages;

import com.demowebshop.pages.components.HeaderComponent;

import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    HeaderComponent headerComponent = new HeaderComponent();

    public ProfilePage openPage() {
        open("/customer/info");
        return this;
    }

    public void checkUsername(String username) {

        headerComponent.checkUsername(username);
    }

    public void exitProfile() {

        headerComponent.exitProfile();
    }
}
