package com.w2a.TestCases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.reflect.DataProviderMethodMatcher;

import com.w2a.Utilities.TestUtil;
import com.w2a.base.TestBase;


public class AddCustomerTest extends TestBase {
	
	
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	
	public void addCustomerTest(Hashtable<String,String >data) {
		Click("AddCustBtn");
		
		Type("firstname",data.get("firstname"));
		Type("lastname",data.get("lastname"));
		Type("postcode",data.get("postcode"));
		Click("AddBtn");
		Alert alert =wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));
		alert.accept();
		
	}
	
}
