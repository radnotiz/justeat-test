package com.github.radnotiz.justeat_test.step_definitions;

import com.github.radnotiz.justeat_test.page_objects.SearchPage;
import com.google.inject.Inject;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class Search {

    private SearchPage searchPage;

    @Inject
    public Search(SearchPage searchPage) {
        this.searchPage = searchPage;
    }

    @Before
    public void openPage() {
        searchPage.open();
        searchPage.hideCookieWarning();
    }

    @After
    public void closePage() {
        searchPage.close();
    }

    @Given("^I want food in \"([^\"]*)\"$")
    public void I_want_food_in(String area) throws Throwable {
        searchPage.typeArea(area);
    }

    @When("^I search for restaurants$")
    public void I_search_for_restaurants() throws Throwable {
        searchPage.search();
    }

    @Then("^I should see some restaurants in \"([^\"]*)\"$")
    public void I_should_see_some_restaurants_in(String area) throws Throwable {
        assertThat(searchPage.someRestaurantsAreVisible(), is(true));
    }

    @Then("^I should see a message \"([^\"]*)\" below the postcode just entered$")
    public void I_should_see_a_message_below_the_postcode_just_entered(String message) throws Throwable {
        assertThat(searchPage.postcodeErrorMessageIsDisplayed(), is(true));
        assertThat(searchPage.getPostcodeErrorMessage(), is(equalTo(message)));
    }

    @And("^I select cuisine \"([^\"]*)\"$")
    public void I_select_cuisine(String cuisine) throws Throwable {
        searchPage.selectCuisine(cuisine);
    }

    @Then("^I should see only restaurants serving \"([^\"]*)\" cuisine$")
    public void I_should_see_only_restaurants_serving_cuisine(String cuisine) throws Throwable {
        searchPage.onlyRestaurantsVisibleServing(cuisine);
    }
}
