package com.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement emailField = $("#Email"),
            passwordField = $("#Password"),
            loginButton = $(".login-button");


    public LoginPage openPage() {

        open("/login");
        return this;
    }

    public LoginPage checkLoginForm() {

        emailField.shouldBe(visible);
        passwordField.shouldBe(visible);
        loginButton.shouldBe(visible);
        return this;
    }

    public MainPage loginByPassword(String login, String password) {

        emailField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
        return new MainPage();
    }
}
