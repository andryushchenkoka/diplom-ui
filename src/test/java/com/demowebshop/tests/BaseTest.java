package com.demowebshop.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.demowebshop.config.*;
import com.demowebshop.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    protected static AuthCookies authCookies;
    protected static EndpointConfig endpointConfig;
    protected static WebConfig config;
    protected static ProjectConfiguration projectConfig;
    protected static UserProfileConfig userProfileConfig;

    @BeforeAll
    public static void beforeAll() {

        authCookies = ConfigFactory.create(AuthCookies.class);
        endpointConfig = ConfigFactory.create(EndpointConfig.class);
        config = ConfigFactory.create(WebConfig.class, System.getProperties());
        userProfileConfig = ConfigFactory.create(UserProfileConfig.class);
        projectConfig = new ProjectConfiguration();
        projectConfig.setupConfig(config);
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
