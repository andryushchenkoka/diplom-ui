package com.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/attach.properties"
})
public interface AttachConfig extends Config {

    @Config.Key("videoUrl")
    String getVideoUrl();
}
