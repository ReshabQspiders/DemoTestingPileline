package com.DWS.login;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.generic_utilities.BaseClass;
import com.object_repository.LoginPage;
import com.object_repository.WelcomePage;

public class LoginTest extends BaseClass {
	@Test
	public void loginAsValidUser() {
		WelcomePage wp=new WelcomePage(driver);
		LoginPage lp=new LoginPage(driver);
		wp.clickOnLoginLink();
		lp.loginWithValidCredentials(email, password);
		Assert.assertEquals(wp.getTextOfRegisteredEmail(),email);
				
	}
	

}
