package com.github.radnotiz.justeat_test.step_definitions;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class PageModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WebDriver.class).to(FirefoxDriver.class);
        bindConstant().annotatedWith(FrontPageUrl.class).to("http://www.just-eat.co.uk");
    }

    @BindingAnnotation
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    public @interface FrontPageUrl {
    }
}
