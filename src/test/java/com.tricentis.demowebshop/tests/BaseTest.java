package com.tricentis.demowebshop.tests;

import com.tricentis.demowebshop.helpers.Attach;
import org.junit.jupiter.api.AfterEach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

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
