package com.demowebshop.helpers;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Cookies {

    public static void setCookie(String name, String value) {

        getWebDriver().manage().addCookie(new Cookie(name, value));
    }

    public static void setCookie(String cookie) {

        String[] cookies = cookie.split(",");
        getWebDriver().manage().addCookie(new Cookie(cookies[0], cookies[1]));
    }

    public static String[] getCookieParts(String cookie) {

        return cookie.split(",");
    }

    public static String getCookieName(String cookie) {

        return cookie.split(",")[0];
    }

    public static String getCookieValue(String cookie) {

        return cookie.split(",")[1];
    }
}
