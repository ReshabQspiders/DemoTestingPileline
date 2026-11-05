package com.object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "Email")
	private WebElement emailTextfield;
	@FindBy(id="Password")
	private WebElement passwordTextfield;
	@FindBy(css="[value='Log in']")
	private WebElement loginButton;
	public WebElement getEmailTextfield() {
		return emailTextfield;
	}
	public WebElement getPasswordTextfield() {
		return passwordTextfield;
	}
	public WebElement getLoginButton() {
		return loginButton;
	}
	public void loginWithValidCredentials(String email,String password) {
		emailTextfield.sendKeys(email);
		passwordTextfield.sendKeys(password);
		loginButton.click();
	}

}
