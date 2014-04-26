package com.github.radnotiz.justeat_test.step_definitions;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bindConstant().annotatedWith(FrontPageUrl.class).to("http://www.just-eat.co.uk");
    }

    @Provides
    public WebDriver firefoxDriver() {
        final WebDriver firefoxDriver = new FirefoxDriver();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                firefoxDriver.close();
            }
        });
        return firefoxDriver;
    }

    @BindingAnnotation
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    public @interface FrontPageUrl {
    }
}
