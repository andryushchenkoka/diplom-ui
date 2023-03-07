package com.demowebshop.config;

import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Configuration.*;

public class ProjectConfiguration {

    public void setupConfig(WebConfig config) {

        baseUrl = config.getBaseUrl();
        browser = config.getBrowserName();
        browserVersion = config.getBrowserVersion();
        browserSize = config.getBrowserSize();
        browserPosition = "0x0";
        timeout = config.getTimeout();
        if (System.getProperty("env").equals("remote")) {
            remote = config.getRemoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            browserCapabilities = capabilities;
        }
    }
}
