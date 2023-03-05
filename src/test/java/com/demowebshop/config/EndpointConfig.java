package com.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/endpoint.properties"
})
public interface EndpointConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("login")
    String getLogin();

    @Key("addWishlistPath")
    String getAddWishlistPath();

    @Key("addWishlistOperation")
    String getAddWishlistOperation();
}

