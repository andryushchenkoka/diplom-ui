package com.demowebshop.pages;

import com.demowebshop.pages.components.HeaderComponent;

import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    HeaderComponent headerComponent = new HeaderComponent();

    public ProfilePage openPage() {
        open("/customer/info");
        return this;
    }

    public String getProfileName() {

        return headerComponent.getUserName();
    }

    public void exitProfile() {

        headerComponent.exitProfile();
    }
}
