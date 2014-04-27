package com.github.radnotiz.justeat_test.page_objects;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {

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
    public SearchPage(WebDriver webDriver, @SearchPageUrl String url) {
        this.webDriver = webDriver;
        this.url = url;
        PageFactory.initElements(webDriver, this);
    }

    public void open() {
        webDriver.navigate().to(url);
    }

    public void hideCookieWarning() {
        if (hideCookieWarning.isDisplayed()) {
            hideCookieWarning.click();
        }
    }

    public void close() {
        webDriver.close();
    }

    public void typeArea(String area) {
        where.sendKeys(area);
    }

    public void search() {
        search.click();
    }

    public boolean someRestaurantsAreVisible() throws Throwable {
        return !searchResults.isEmpty();
    }

    public boolean postcodeErrorMessageIsDisplayed() {
        return errorForWhere.isDisplayed();
    }

    public String getPostcodeErrorMessage() {
        return errorForWhere.getText();
    }

    public void selectCuisine(String cuisine) {
        try {
            new Select(what).selectByVisibleText(cuisine);
        } catch (ElementNotVisibleException e) {
            what_chzn_a.click();
            String exprCuisine = "//ul[contains(@class,'chzn-results')]/li[contains(text(),'" + cuisine + "')]";
            new WebDriverWait(webDriver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath(exprCuisine))).click();
        }
    }

    public void onlyRestaurantsVisibleServing(String cuisine) {
        // TODO refactor to minimize nr. of browser queries
        for (WebElement result : searchResultsOpenOnes) {
            String exprRestaurantContainingCuisine = ".//p[@class[contains(.,'restaurantCuisines')] and text()[contains(.,'" + cuisine + "')]]";

            if (result.findElements(By.xpath(exprRestaurantContainingCuisine)).isEmpty()) {
                String restaurant = result.findElement(By.xpath(".//a")).getAttribute("href");
                String reason = "Restaurant @ " + restaurant + " expected to have " + cuisine + " cuisine, but it was not found.";
                throw new InappropriateRestaurantInSearchResultExpcetion(reason);
            }
        }
    }
}
