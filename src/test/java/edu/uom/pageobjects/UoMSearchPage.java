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

    public void searchForStudies(String name){
        driver.findElement
    }

    public boolean isRectorateComponentPresent(){
        return driver.findElements(By.xpath("div[text()='Rectorate']")).size() > 0;
    }
}
