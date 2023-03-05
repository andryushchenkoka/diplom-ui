package com.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:data/profile.properties"
})
public interface UserProfileConfig extends Config {

    @Config.Key("login")
    String getUserLogin();

    @Config.Key("password")
    String getUserPassword();
}
