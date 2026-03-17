package com.actitime.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest
{
	public static WebDriver driver;
	public static Properties p;
	
	public static void loadpropertiesFile() throws IOException
	{
		FileInputStream fis=new FileInputStream("./src/main/resources/config.properties");
		p=new Properties();
		p.load(fis);
	}
	
	@BeforeSuite
	public void testEnvironment() throws IOException
	{
		BaseTest.loadpropertiesFile();
		Reporter.log("test execution happening in : " +p.getProperty("environment"),true);
	}
	
	@BeforeClass
	public void setup() throws IOException
	{
		BaseTest.loadpropertiesFile();
		String browser=p.getProperty("browser");
		Reporter.log("test execution happening in : " +browser , true);
		String url=p.getProperty("url");
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@AfterClass
	public void teardown()
	{
		if(driver!=null)
        {
            driver.quit();
        }
	}
}