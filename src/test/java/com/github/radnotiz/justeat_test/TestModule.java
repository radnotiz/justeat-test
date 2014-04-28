package com.github.radnotiz.justeat_test;

import com.github.radnotiz.justeat_test.page_objects.SearchPageUrl;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bindConstant().annotatedWith(SearchPageUrl.class).to("http://www.just-eat.co.uk");
        bind(BrowserDriver.class)
                .toInstance(BrowserDriver.valueOf(System.getProperty("browser.driver", "htmlunit").toUpperCase()));
        bind(WebDriver.class).toProvider(WebDriverProvider.class);
    }

    public enum BrowserDriver {
        HTMLUNIT, FIREFOX
    }

    public static class WebDriverProvider implements Provider<WebDriver> {

        BrowserDriver browserDriver;

        @Inject
        public WebDriverProvider(BrowserDriver browserDriver) {
            this.browserDriver = browserDriver;
        }

        @Override
        public WebDriver get() {
            WebDriver webDriver = null;
            switch (browserDriver) {
                case HTMLUNIT:
                    webDriver = new HtmlUnitDriver(true);
                    break;
                case FIREFOX:
                    webDriver = new FirefoxDriver();
                    break;
            }
            return webDriver;
        }
    }
}
