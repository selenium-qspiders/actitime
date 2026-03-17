package com.actitime.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TC_002 
{
	@Test
	public void demo()
	{
		Reporter.log("this is a demo test case" , true);
		Reporter.log("helloooo",true);
	}
}