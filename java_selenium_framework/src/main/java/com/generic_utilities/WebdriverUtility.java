package com.generic_utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

/**
 * Provides reusable browser-level operations used by the automation framework.
 *
 * @author Reshab Patel
 */
public class WebdriverUtility {
	private final WebDriver driver;
    private static final Path SCREENSHOT_DIRECTORY = Path.of("Screenshots");
    private static final DateTimeFormatter SCREENSHOT_TIMESTAMP =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    /**
     * Creates a utility instance for the supplied WebDriver session.
     *
     * @param driver active WebDriver instance
     */
    public WebdriverUtility(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver must not be null.");
        }
        this.driver = driver;
    }

    /** Maximizes the current browser window. */
    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    /** Closes the current WebDriver session. */
    public void terminateBrowserSession() {
        driver.quit();
    }

    /**
     * Configures the implicit wait for element location.
     *
     * @param seconds maximum wait duration in seconds
     */
    public void addGlobalWait(long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Navigates the browser to the supplied application URL.
     *
     * @param url application URL
     */
    public void openApplication(String url) {
        driver.get(url);
    }

    /**
     * Captures the current browser view and stores it in the project's
     * Screenshots directory. The directory is created automatically.
     *
     * @param fileNamePrefix meaningful prefix, such as the test method name
     * @return absolute path of the saved screenshot
     * @throws IOException if the screenshot cannot be saved
     */
    public String takeScreenShot(String fileNamePrefix) throws IOException {
        if (!(driver instanceof TakesScreenshot)) {
            throw new IllegalStateException("The current WebDriver does not support screenshots.");
        }

        Files.createDirectories(SCREENSHOT_DIRECTORY);
        String safePrefix = fileNamePrefix == null ? "screenshot"
                : fileNamePrefix.replaceAll("[^a-zA-Z0-9._-]", "_");
        String timestamp = LocalDateTime.now().format(SCREENSHOT_TIMESTAMP);
        Path targetPath = SCREENSHOT_DIRECTORY.resolve(safePrefix + "_" + timestamp + ".png");

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, targetPath.toFile());
        return targetPath.toAbsolutePath().toString();
    }
}
