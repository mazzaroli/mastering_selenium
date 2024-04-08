package com.masteringselenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class manages the WebDriver instances using ThreadLocal to ensure thread safety.
 */
public class DriverFactory {
    // List to hold WebDriverThread instances
    private static List<WebDriverThread> webDriverThreadPool =
            Collections.synchronizedList(new ArrayList<WebDriverThread>());

    private static ThreadLocal<WebDriverThread> driverThread;

    /**
     * Instantiates the WebDriver object for each thread.
     */
    @BeforeSuite
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue() {
                // Creating a new WebDriverThread instance
                WebDriverThread webDriverThread = new WebDriverThread();
                // Adding the instance to the thread pool list
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    /**
     * Retrieves the WebDriver instance associated with the current thread.
     *
     * @return The WebDriver instance.
     * @throws Exception If unable to get the WebDriver instance.
     */
    public static WebDriver getDriver() throws Exception {
        return driverThread.get().getDriver();
    }

    /**
     * Clears cookies after each test method.
     *
     * @throws Exception If unable to clear cookies.
     */
    @AfterMethod
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    /**
     * Quits all WebDriver instances associated with the current thread.
     */
    @AfterMethod
    public static void closeDriverObjects() {
        for (WebDriverThread webDriverThread : webDriverThreadPool) {
            webDriverThread.quitDriver();
        }
    }
}
