package com.masteringselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class BasicTestWD extends DriverFactory {

    private void googleExampleThatSearchesFor (final String searchString) throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.get("http://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();

        searchField.sendKeys(searchString);
        System.out.println("Page title is: " + driver.getTitle());

        searchField.submit();

        (new WebDriverWait(driver, Duration.ofSeconds(10)))
        .until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getTitle()
                        .toLowerCase()
                        .startsWith(searchString.toLowerCase());
            }
        });

        System.out.println("Page title is:" + driver.getTitle());
    }

    @Test
    public void googleCheeseExample() throws Exception {
        googleExampleThatSearchesFor("Cheese!");
    }

    @Test
    public void googleMilkExample() throws Exception {
        googleExampleThatSearchesFor("Milk!");
    }
}
