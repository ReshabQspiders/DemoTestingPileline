package com.generic_utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Holds common setup and teardown operations shared by test classes.
 *
 * @author Reshab Patel
 */

public class BaseClass {
	 public WebDriver driver;
	    public String browser;
	    public String url;
	    public String email;
	    public String password;
	    protected WebdriverUtility wdu;

	    /** Reads the framework configuration before the test class executes. */
	    @BeforeClass
	    public void configBeforeClass() throws IOException {
	        FileUtility fileUtility = new FileUtility();
	        browser = fileUtility.readPropertyData("browser");
	        url = fileUtility.readPropertyData("applicationUrl");
	        email = fileUtility.readPropertyData("validEmail");
	        password = fileUtility.readPropertyData("validPassword");

	        if (browser == null || browser.isBlank()) {
	            throw new IllegalStateException("The 'browser' property is missing or empty.");
	        }
	        if (url == null || url.isBlank()) {
	            throw new IllegalStateException("The 'applicationUrl' property is missing or empty.");
	        }
	    }

	    /** Starts the configured browser and opens the application before each test. */
	    @BeforeMethod
	    public void configBeforeMethod() {
	        String selectedBrowser = browser.trim().toLowerCase();
	        if (selectedBrowser.equals("chrome")) {
	            driver = new ChromeDriver();
	        } else if (selectedBrowser.equals("edge")) {
	            driver = new EdgeDriver();
	        } else if (selectedBrowser.equals("firefox")) {
	            driver = new FirefoxDriver();
	        } else {
	            throw new IllegalArgumentException("Unsupported browser: " + browser);
	        }

	        wdu = new WebdriverUtility(driver);
	        wdu.maximizeBrowser();
	        wdu.addGlobalWait(10);
	        wdu.openApplication(url);
	    }

	    /**
	     * Captures a screenshot for a failed test before closing its browser session.
	     * The teardown is marked alwaysRun so cleanup also happens after failures.
	     *
	     * @param result TestNG result for the test method that just executed
	     */
	    @AfterMethod(alwaysRun = true)
	    public void configAfterMethod(ITestResult result) {
	        try {
	            if (driver != null && result.getStatus() == ITestResult.FAILURE) {
	                String screenshotName = result.getTestClass().getName() + "_" + result.getMethod().getMethodName();
	                try {
	                    String screenshotPath = new WebdriverUtility(driver).takeScreenShot(screenshotName);
	                    result.setAttribute("screenshotPath", screenshotPath);
	                    System.out.println("Failure screenshot saved: " + screenshotPath);
	                } catch (IOException | RuntimeException screenshotException) {
	                    System.err.println("Unable to capture failure screenshot: " + screenshotException.getMessage());
	                }
	            }
	        } finally {
	            if (driver != null) {
	                try {
	                    driver.quit();
	                } catch (Exception quitException) {
	                    System.err.println("Unable to close browser cleanly: " + quitException.getMessage());
	                } finally {
	                    driver = null;
	                    wdu = null;
	                }
	            }
	        }
	    }
	}
