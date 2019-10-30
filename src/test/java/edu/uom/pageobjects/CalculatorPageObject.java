package edu.uom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalculatorPageObject {

    WebDriver driver;

    public CalculatorPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void get(){
        driver.get("http://www.math.com/students/calculators/source/basic.htm");
    }

    public void enterKeys(String expression) {
        char[] charArray = expression.toCharArray();
        for(char c : charArray) {
            pressKey(c);
        }
    }

    public void pressKey(char key){
        String name = null;

        switch(key){
            case '1':
                name = "one";
                break;
            case '2':
                name = "two";
                break;
            case '3':
                name = "three";
                break;
            case '4':
                name = "four";
                break;
            case '5':
                name = "five";
                break;
            case '6':
                name = "six";
                break;
            case '7':
                name = "seven";
                break;
            case '8':
                name = "eight";
                break;
            case '9':
                name = "nine";
                break;
            case '0':
                name = "zero";
                break;
            case '+':
                name = "plus";
                break;
            case '-':
                name = "minus";
                break;
            case 'x':
                name = "times";
                break;
            case '/':
                name = "div";
                break;
            case '=':
                name = "DoIt";
                break;
        }
        driver.findElement(By.name(name)).click();
    }

    public  void clear(){
        driver.findElement(By.name("clear")).click();
    }

    public String getDisplayText(){
        return driver.findElement(By.name("Input")).getAttribute("value");
    }
}
