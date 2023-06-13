package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;

public class WebDriverProvider {
    private static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static void config() {
        Configuration.baseUrl = WebDriverProvider.config.getBaseUrl();
        Configuration.browserSize = WebDriverProvider.config.getBrowserSize();
        Configuration.browser = WebDriverProvider.config.getBrowserName();
        Configuration.browserVersion = WebDriverProvider.config.getBrowserVersion();

    }
}