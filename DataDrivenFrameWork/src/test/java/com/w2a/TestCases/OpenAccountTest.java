package com.w2a.TestCases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.Utilities.TestUtil;
import com.w2a.base.TestBase;










public class OpenAccountTest extends TestBase {
	
	
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String,String >data) {
		
		Click("Openaccoun_CSS");
		select("customer_CSS",data.get("customer"));
		select("currency_CSS",data.get("currency"));
		Click("process_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		
	}
	
	
	
	
	
	

}
