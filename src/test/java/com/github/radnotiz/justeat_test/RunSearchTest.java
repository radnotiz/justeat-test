package com.github.radnotiz.justeat_test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resource/com/github/radnotiz/justeat_test/features")
public class RunSearchTest {
}
