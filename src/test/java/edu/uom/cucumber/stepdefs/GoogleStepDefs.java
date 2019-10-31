package edu.uom.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class GoogleStepDefs {

    WebDriver browser;

    @Given("^I am using the Google website$")
    public void iAmUsingTheGoogleWebsite() throws Throwable {


        browser.get("http://www.google.com");
    }

    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String query) throws Exception {
        browser.findElement(By.name("q")).sendKeys(query);
        browser.findElement(By.name("q")).submit();
    }

    @Then("^The title should be \"([^\"]*)\"$")
    public void the_title_should_be(String expectedTitle) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(expectedTitle, browser.getTitle());
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/dev/tools/ChromeDriver/chromedriver.exe");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }
}
