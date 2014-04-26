package com.github.radnotiz.justeat_test.step_definitions;

import com.google.inject.Inject;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.github.radnotiz.justeat_test.step_definitions.PageModule.FrontPageUrl;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class FrontPage {

    private String url;
    private WebDriver webDriver;
    @FindBy(id = "where")
    private WebElement where;
    @FindBy(id = "btnSearch")
    private WebElement search;
    @FindBy(xpath = "//div[@id='SearchResults']/descendant::article")
    private List<WebElement> searchResults;

    @Inject
    public FrontPage(WebDriver webDriver, @FrontPageUrl String url) {
        this.webDriver = webDriver;
        this.url = url;
        PageFactory.initElements(webDriver, this);
    }

    @Before
    public void openPage() {
        webDriver.navigate().to(url);
    }

    @After
    public void closePage() {
        webDriver.close();
    }

    @Given("^I want food in \"([^\"]*)\"$")
    public void I_want_food_in(String area) throws Throwable {
        where.sendKeys(area);
    }

    @When("^I search for restaurants$")
    public void I_search_for_restaurants() throws Throwable {
        search.click();
    }

    @Then("^I should see some restaurants in \"([^\"]*)\"$")
    public void I_should_see_some_restaurants_in(String area) throws Throwable {
        assertThat(searchResults, is(not(empty())));
    }
}
