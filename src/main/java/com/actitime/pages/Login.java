package com.actitime.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.utilities.BasePage;
import com.actitime.utilities.BaseTest;

public class Login extends BasePage
{
	public WebDriver driver;
	
	@FindBy(id = "username")
	private WebElement usernameTextfield;
	
	@FindBy(name = "pwd")
	private WebElement passwordTextfield;
	
	@FindBy(id = "loginButton")
	private WebElement loginButton;
	
	public Login(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void login() throws IOException 
	{
		BaseTest.loadpropertiesFile();
		highlightElement(usernameTextfield);
		usernameTextfield.sendKeys(BaseTest.p.getProperty("username"));
		getAttribute(usernameTextfield,"value");
		
		highlightElement(passwordTextfield);
		passwordTextfield.sendKeys(BaseTest.p.getProperty("password"));
		getAttribute(passwordTextfield,"value");
		
		highlightElement(loginButton);
		loginButton.click();
	}
}