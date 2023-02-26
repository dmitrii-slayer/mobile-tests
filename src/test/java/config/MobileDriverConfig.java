package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:android.properties"
})
public interface MobileDriverConfig extends Config{

    @Key("browserstack.user")
    String getBrowserstackUser();

    @Key("browserstack.key")
    String getBrowserstackKey();

    @Key("Url")
    String getUrl();

    @Key("App")
    String getApp();

    @Key("device.name")
    String getDeviceName();

    @Key("platform.version")
    String getPlatformVersion();
}