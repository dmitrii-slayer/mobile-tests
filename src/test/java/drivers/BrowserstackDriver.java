package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileDriverConfig;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.URL;
import java.util.HashMap;

public class BrowserstackDriver implements WebDriverProvider {

    static MobileDriverConfig mobileConfig = ConfigFactory.create(MobileDriverConfig.class, System.getProperties());

    @SneakyThrows
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", mobileConfig.getBrowserstackUser());
        mutableCapabilities.setCapability("browserstack.key", mobileConfig.getBrowserstackKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", mobileConfig.getApp());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", mobileConfig.getDeviceName());
        mutableCapabilities.setCapability("os_version", mobileConfig.getPlatformVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");
        HashMap<String, Boolean> networkLogsOptions = new HashMap<>();
        networkLogsOptions.put("captureContent", true);
        mutableCapabilities.setCapability("browserstack.networkLogs", true);
        mutableCapabilities.setCapability("browserstack.networkLogsOptions", networkLogsOptions);
        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        return new RemoteWebDriver(new URL(mobileConfig.getUrl()), mutableCapabilities);
    }
}
