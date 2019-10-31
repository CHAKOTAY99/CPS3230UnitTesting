package system.google;

import cucumber.api.java.After;

import cucumber.api.java.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class GoogleTests {
    WebDriver browser;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/dev/tools/ChromeDriver/chromedriver.exe");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }

    @Test
    public void testSimpleSearch() {
        browser.get("http://www.google.com");
        browser.findElement(By.name("q")).sendKeys("Malta");
        browser.findElement(By.name("btnK")).click();
        assertEquals("Malta - Google Search", browser.getTitle());
    }

}
