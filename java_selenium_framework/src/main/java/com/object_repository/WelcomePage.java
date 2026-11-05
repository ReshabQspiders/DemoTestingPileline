package com.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
	WebDriver driver;
	public WelcomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText  = "Log in")
	private WebElement loginLink;
	@FindBy(css="[class='account']")
	private WebElement registeredEmail;
	public WebElement getLoginLink() {
		return loginLink;
	}
	public WebElement getRegisteredEmail() {
		return registeredEmail;
	}
	public void clickOnLoginLink() {
		loginLink.click();
	}
	public String getTextOfRegisteredEmail() {
		return registeredEmail.getText();
	}

}
