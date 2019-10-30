package edu.uom;

import edu.uom.pageobjects.CalculatorPageObject;
import edu.uom.pageobjects.UoMSearchPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UomSearchTests {
    WebDriver driver;


    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/dev/tools/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void TestSearchForRector() {

        UoMSearchPage page = new UoMSearchPage(driver);
        page.get();
        page.searchForStaff("Alfred Vella");
        assertTrue(page.isRectorateComponentPresent());
    }

    @Test
    public void testSearchForMaltaInGoogle() {

        //Visit google
        driver.get("http://www.google.com");

        //Search for Malta
        driver.findElement(By.name("q")).sendKeys("Malta");
        driver.findElement(By.name("q")).submit();
        sleep(3);

        //Find stats component and assert number of links
        WebElement statsElement = driver.findElement(By.id("resultStats"));
        String statsText = statsElement.getText();

        assertTrue(statsText.indexOf("893,000,000") >= 0);
    }


    @Test
    public void testAddition() {

        //Setup
        CalculatorPageObject calc = new CalculatorPageObject(driver);
        calc.get();

        //Exercise
        calc.enterKeys("5+2=");

        //Verify
        assertEquals("7", calc.getDisplayText());

    }


    @Test
    public void testMultiplication() {
        //Setup
        CalculatorPageObject calc = new CalculatorPageObject(driver);
        calc.get();

        //Exercise
        calc.enterKeys("158x22=");

        //Verify
        assertEquals("3476", calc.getDisplayText());
    }

    @Test
    public void testDivideByZero() {
        //Setup
        CalculatorPageObject calc = new CalculatorPageObject(driver);
        calc.get();

        //Exercise
        calc.enterKeys("5/0=");

        //Verify
        assertEquals("Infinity", calc.getDisplayText());
    }
}