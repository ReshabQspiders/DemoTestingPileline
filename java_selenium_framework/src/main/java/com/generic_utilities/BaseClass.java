package com.generic_utilities;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	public WebDriver driver;
	public String browser;
	public String url;
	public String email;
	public String password;
	WebdriverUtility wdu;
	@BeforeClass
	public void configBeforeClass() throws FileNotFoundException, IOException {
		FileUtility fu=new FileUtility();
		browser = fu.readPropertyData("browser");
		url =fu.readPropertyData("applicationUrl");
		email =fu.readPropertyData("validEmail");
		password =fu.readPropertyData("validPassword");

	}
	@BeforeMethod
	public void configBeforeMethod() {
		if (browser.contains("chrome")) {
			driver=new ChromeDriver();
		} else if(browser.contains("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new FirefoxDriver();
		}
		wdu=new WebdriverUtility(driver);
		wdu.maximizeBrowser();
		wdu.addGlobalWait(10);
		wdu.openApplication(url);
		
	}
	@AfterMethod
	public void configAfterMethod() {
		wdu.terminateBrowserSession();
	}

}
