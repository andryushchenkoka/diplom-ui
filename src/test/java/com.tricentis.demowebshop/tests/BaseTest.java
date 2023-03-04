package com.tricentis.demowebshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.tricentis.demowebshop.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    @BeforeAll
    public static void beforeAll() {

        Configuration.baseUrl = "https://demowebshop.tricentis.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 20000;
    }

    @BeforeEach
    public void BeforeEach() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void afterEach() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();

        Attach.screenshotAs("screenshot_" + dateFormat.format(date));
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        getWebDriver().quit();
    }
}
