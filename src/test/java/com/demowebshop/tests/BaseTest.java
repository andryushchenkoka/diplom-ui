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
import org.openqa.selenium.Cookie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected final static EndpointConfig ENDPOINT_CONFIG = ConfigFactory.create(EndpointConfig.class);
    protected final static WebConfig CONFIG = ConfigFactory.create(WebConfig.class, System.getProperties());
    protected final static ProjectConfiguration PROJECT_CONFIGURATION = new ProjectConfiguration();
    protected final static UserProfileConfig USER_PROFILE_CONFIG = ConfigFactory.create(UserProfileConfig.class);
    public static Cookie authCookie;

    @BeforeAll
    public static void beforeAll() {

        PROJECT_CONFIGURATION.setupConfig(CONFIG);

        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", USER_PROFILE_CONFIG.getUserLogin())
                .formParam("Password", USER_PROFILE_CONFIG.getUserPassword())
                .when()
                .post(ENDPOINT_CONFIG.getBaseUrl() + "/login")
                .then()
                .statusCode(302)
                .extract().cookie("NOPCOMMERCE.AUTH");

        authCookie = new Cookie("NOPCOMMERCE.AUTH", authCookieValue);
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
