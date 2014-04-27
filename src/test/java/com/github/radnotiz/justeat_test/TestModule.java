package com.github.radnotiz.justeat_test;

import com.github.radnotiz.justeat_test.page_objects.SearchPageUrl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bindConstant().annotatedWith(SearchPageUrl.class).to("http://www.just-eat.co.uk");
    }

    @Provides
    public WebDriver firefoxDriver() {
        final WebDriver firefoxDriver = new FirefoxDriver();
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                firefoxDriver.close();
//            }
//        });
        return firefoxDriver;
    }

}
