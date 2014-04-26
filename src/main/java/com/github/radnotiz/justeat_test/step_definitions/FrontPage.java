package com.github.radnotiz.justeat_test.step_definitions;

import com.google.inject.Inject;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.github.radnotiz.justeat_test.step_definitions.PageModule.FrontPageUrl;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class FrontPage {

    private String url;
    private WebDriver webDriver;
    @FindBy(id = "where")
    private WebElement where;
    @FindBy(id = "btnSearch")
    private WebElement search;
    @FindBy(xpath = "//div[@id='SearchResults']/descendant::article")
    private List<WebElement> searchResults;
    @FindBy(xpath = "//div[@id='SearchResults']/div[@id='OpenRestaurants']/descendant::article")
    private List<WebElement> searchResultsOpenOnes;
    @FindBy(xpath = "//label[@for='where' and contains(@class,'error')]")
    private WebElement errorForWhere;
    @FindBy(id = "what")
    private WebElement what;
    @FindBy(xpath = "//div[contains(@id,'what_chzn')]/a")
    private WebElement what_chzn_a;
    @FindBy(id = "cookieButton")
    private WebElement hideCookieWarning;

    @Inject
    public FrontPage(WebDriver webDriver, @FrontPageUrl String url) {
        this.webDriver = webDriver;
        this.url = url;
        PageFactory.initElements(webDriver, this);
    }

    @Before
    public void openPage() {
        webDriver.navigate().to(url);
        if (hideCookieWarning.isDisplayed()) {
            hideCookieWarning.click();
        }
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

    @Then("^I should see a message \"([^\"]*)\" below the postcode just entered$")
    public void I_should_see_a_message_below_the_postcode_just_entered(String message) throws Throwable {
        assertThat(errorForWhere.isDisplayed(), is(true));
        assertThat(errorForWhere.getText(), is(equalTo(message)));
    }

    @And("^I select cuisine \"([^\"]*)\"$")
    public void I_select_cuisine(String cuisine) throws Throwable {
        try {
            new Select(what).selectByVisibleText(cuisine);
        } catch (ElementNotVisibleException e) {
            what_chzn_a.click();
            String exprCuisine = "//ul[contains(@class,'chzn-results')]/li[contains(text(),'" + cuisine + "')]";
            new WebDriverWait(webDriver, 3).until(elementToBeClickable(By.xpath(exprCuisine))).click();
        }
    }

    @Then("^I should see only restaurants serving \"([^\"]*)\" cuisine$")
    public void I_should_see_only_restaurants_serving_cuisine(final String cuisine) throws Throwable {
        for (WebElement result : searchResultsOpenOnes) {
            String exprContainsCuisine = ".//p[@class[contains(.,'restaurantCuisines')] and text()[contains(.,'" + cuisine + "')]]";
            String restaurant = result.findElement(By.xpath(".//a")).getAttribute("href");
            String withReason = "Restaurant @ " + restaurant + " expected to have " + cuisine + " cuisine, but it was not found.";
            assertThat(withReason, result.findElements(By.xpath(exprContainsCuisine)), is(not(empty())));
        }
    }
}
