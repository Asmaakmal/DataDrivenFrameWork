package com.w2a.TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;





public class BankManagerLoginTest extends TestBase {
	
	@Test
	public void LoginAsTestManager() throws InterruptedException, IOException {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		VerifyEquals("ABC","XYZ");
		log.debug("Inside LoginTest");
		Click("bmlBtn");

		Thread.sleep(3000);
		 Assert.assertTrue(isElementPresent(By.cssSelector(oR.getProperty("AddCustBtn"))),"Login Not Successeful");
		log.debug("Login Successfully Executed");
		//Assert.fail("login not successful");
		
	}

}
