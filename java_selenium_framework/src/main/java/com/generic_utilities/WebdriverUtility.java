package com.generic_utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class WebdriverUtility {
	WebDriver driver;
	public WebdriverUtility(WebDriver driver ) {
		this.driver=driver;
	}
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}
	public void terminateBrowserSession() {
		driver.quit();
	}
	public void addGlobalWait(long Seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Seconds));
	}
	public void openApplication(String url) {
		driver.get(url);
	}
	public void takeScreenShot() throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyymmss"));
		File trg=new File("./java_selenium_framework/Screenshots/ss"+timeStamp+".png");
		FileHandler.copy(src, trg);
	}

}
