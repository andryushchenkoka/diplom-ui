package com.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/env.properties"
})
public interface WebConfig extends Config {

    @Key("urlBase")
    String getBaseUrl();

    @Key("browserName")
    String getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("urlRemote")
    String getRemoteUrl();

    @Key("timeout")
    Integer getTimeout();
}
