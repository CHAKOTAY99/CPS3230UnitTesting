package edu.uom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UoMSearchPage {

    WebDriver driver;

    public void sleep(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e){}
    }

    public UoMSearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void get(){
        driver.get("http://www.um.edu.mt/search");
    }

    public void searchForStaff(String name) {
        driver.findElement(By.name("searchtext")).sendKeys(name);
        driver.findElement(By.name("searchtext")).submit();
        sleep(2);
    }

    // Xpath issue - does not work
    public void searchForStudies(String term){
        driver.findElement(By.name("text")).sendKeys(term);
        driver.findElement(By.name("text")).submit();
        sleep(2);
    }

    public boolean isRectorateComponentPresent(){
        return driver.findElements(By.xpath("div[text()='Rectorate']")).size() > 0;
    }

    // XPath does not work
    public boolean isSoftwareDevelopmentComponentPresent(){
        return driver.findElements(By.xpath("div[text()='searchres']")).size() > 0;
    }
}
