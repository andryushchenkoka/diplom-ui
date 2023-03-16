package com.demowebshop.helpers;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CookieHelper {

    public static void setCookieAndRefresh(Cookie cookie) {

        getWebDriver().manage().addCookie(cookie);
        refresh();
    }
}
