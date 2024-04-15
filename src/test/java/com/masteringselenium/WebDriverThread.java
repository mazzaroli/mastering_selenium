package com.masteringselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * This class represents a thread-local WebDriver instance.
 */
public class WebDriverThread {
    private WebDriver webdriver;

    private final String operativeStystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");

    /**
     * Gets the WebDriver instance, initializing it if necessary.
     *
     * @return The WebDriver instance.
     * @throws Exception If unable to get the WebDriver instance.
     */
    public WebDriver getDriver() throws Exception{
        if (webdriver == null) {
            System.out.println(" ");
            System.out.println("Current Operating System: " + operativeStystem);
            System.out.println("Current Architecture: " + systemArchitecture);
            System.out.println("Current Browser Selection: Chrome");
            System.out.println(" ");

            ChromeOptions options = new ChromeOptions();
            webdriver = new ChromeDriver(options);
        }
        return webdriver;
    }

    /**
     * Quits the WebDriver instance.
     */
    public void quitDriver() {
        if (webdriver != null) {
            webdriver.quit();
            webdriver = null;
        }
    }
}
