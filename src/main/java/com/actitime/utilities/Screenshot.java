package com.actitime.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Screenshot extends BaseTest implements ITestListener
{
	public void onTestFailure(ITestResult result) 
	{
		//if(result.getStatus()==2)
		if(result.getStatus()==ITestResult.FAILURE)
		{
			Reporter.log("test case is failed");
		}
		String methodname = result.getName();
		Date d=new Date();
		String timestamp = d.toString().replaceAll(":", "-");
		TakesScreenshot ts = (TakesScreenshot)BaseTest.driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try 
		{
			BaseTest.loadpropertiesFile();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		File dest=new File(p.getProperty("screenshotPath") +methodname+timestamp+".png");
		try 
		{
			FileHandler.copy(src, dest);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}