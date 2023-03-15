package com.demowebshop.tests;

import com.codeborne.selenide.Selenide;
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

public class BaseTest {

    protected final static AuthCookies AUTH_COOKIES = ConfigFactory.create(AuthCookies.class);
    protected final static EndpointConfig ENDPOINT_CONFIG = ConfigFactory.create(EndpointConfig.class);
    protected final static WebConfig CONFIG = ConfigFactory.create(WebConfig.class, System.getProperties());
    protected final static ProjectConfiguration PROJECT_CONFIGURATION = new ProjectConfiguration();
    protected final static UserProfileConfig USER_PROFILE_CONFIG = ConfigFactory.create(UserProfileConfig.class);

    @BeforeAll
    public static void beforeAll() {

        PROJECT_CONFIGURATION.setupConfig(CONFIG);
    }

    @BeforeEach
    public void beforeEach() {

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

        Selenide.closeWebDriver();
    }
}
