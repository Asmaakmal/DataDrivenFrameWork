package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.Utilities.ExcelReader;
import com.w2a.Utilities.ExtentManager;



public class TestBase {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties oR = new Properties();
	public static FileInputStream Fis ;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\Testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep= ExtentManager.getInstance();
	public static ExtentTest test;
	
	
	@BeforeSuite
	public void setUp() {
		if (driver==null) {
			
			try {
				Fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(Fis);
				log.debug("Config file loaded!!!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				oR.load(Fis);
				log.debug("OR file loaded!!!");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(config.getProperty("browser").equals("firefox")) {
			
			System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
			 driver = new FirefoxDriver();
		} else if(config.getProperty("browser").equals("chrome")) {
			
          System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe" );
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			 driver = new ChromeDriver(options);
			 log.debug("Chrome Launched!!");
		} else if(config.getProperty("browser").equals("ie")) {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe" );
			 driver = new InternetExplorerDriver();
		
			
		}
				
			driver.get(config.getProperty("testsiteurl") );
			log.debug("Navigated to :" + config.getProperty("testsiteurl") );
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,5);
			
		}
  public void Click(String locator)  {
	   driver.findElement(By.cssSelector(oR.getProperty(locator))).click();
	   test.log(LogStatus.INFO, "Clicking on :" + locator);
  } 
  
  public void Type(String locator,String value) {
	  driver.findElement(By.cssSelector(oR.getProperty(locator))).sendKeys(value);
	  test.log(LogStatus.INFO, "typing in :" +locator+"enter value as :" +value);
  }
  
  
  
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
			
		} catch(NoSuchElementException e) {
			return false;
		}
		
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
			log.debug("Test execution Completed");
		}
		
	}

}
