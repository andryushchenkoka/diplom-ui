package com.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/endpoint.properties"
})
public interface EndpointConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();
}

