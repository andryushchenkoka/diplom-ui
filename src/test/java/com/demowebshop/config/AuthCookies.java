package com.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:cookies/cookies.properties"
})
public interface AuthCookies extends Config {

    @Key("nopcommerce-auth")
    String nopcommerceAuth();
}
