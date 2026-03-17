package com.actitime.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	protected JavascriptExecutor js;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	// ================= WEBDRIVER METHODS =================

	public void openApplication(String url) {
		driver.get(url);
		Assert.assertTrue(driver.getCurrentUrl().contains(url));
		Reporter.log("Application opened : " + url, true);
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
		Reporter.log("Window maximized", true);
	}

	public void minimizeWindow() {
		driver.manage().window().minimize();
		Reporter.log("Window minimized", true);
	}

	public void fullscreenWindow() {
		driver.manage().window().fullscreen();
		Reporter.log("Window fullscreen", true);
	}

	public void setWindowSize(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
		Reporter.log("Window resized", true);
	}

	public void setWindowPosition(int x, int y) {
		driver.manage().window().setPosition(new Point(x, y));
		Reporter.log("Window repositioned", true);
	}

	public void refreshPage() {
		driver.navigate().refresh();
		Reporter.log("Page refreshed", true);
	}

	public void navigateBack() {
		driver.navigate().back();
		Reporter.log("Navigated back", true);
	}

	public void navigateForward() {
		driver.navigate().forward();
		Reporter.log("Navigated forward", true);
	}

	public void closeBrowser() {
		driver.close();
		Reporter.log("Browser closed", true);
	}

	public void quitBrowser() {
		driver.quit();
		Reporter.log("Browser quit", true);
	}
	
	// ================= OPTIONS METHODS =================
	
	public Set<Cookie> getCookies()
	{
	    Set<Cookie> cookies = driver.manage().getCookies();
	    Assert.assertNotNull(cookies,"Cookies set is null");
	    return cookies;
	}

	public Cookie getCookieNamed(String name)
	{
	    Cookie cookie = driver.manage().getCookieNamed(name);
	    Assert.assertNotNull(cookie,"Cookie not found");
	    return cookie;
	}

	public void deleteAllCookies()
	{
	    driver.manage().deleteAllCookies();
	    Set<Cookie> cookies = driver.manage().getCookies();
	    Assert.assertTrue(cookies.isEmpty(),"Cookies not deleted");
	}

	public void deleteCookieNamed(String name)
	{
	    driver.manage().deleteCookieNamed(name);
	    Cookie cookie = driver.manage().getCookieNamed(name);
	    Assert.assertNull(cookie,"Cookie still present");
	}

	public void addCookie(Cookie cookie)
	{
	    driver.manage().addCookie(cookie);
	    Cookie added = driver.manage().getCookieNamed(cookie.getName());
	    Assert.assertNotNull(added,"Cookie not added");
	}

	// ================= WEBELEMENT METHODS =================

	public void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		Assert.assertTrue(element.isDisplayed(), "Element not clickable");
		Reporter.log("Element clicked successfully", true);
	}

	public void sendKeys(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
		String value = element.getAttribute("value");
		Assert.assertEquals(value, text, "Text not entered properly");
		Reporter.log("Text entered : " + text, true);
	}

	public void clear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		String value = element.getAttribute("value");
		Assert.assertTrue(value.isEmpty(), "Field not cleared");
		Reporter.log("Text field cleared", true);
	}

	public String getText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String text = element.getText();
		Assert.assertNotNull(text, "Text is null");
		Reporter.log("Text captured : " + text, true);
		return text;
	}

	public String getAttribute(WebElement element, String attributeName) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String value = element.getAttribute(attributeName);
		Assert.assertNotNull(value, "Attribute value is null");
		Reporter.log(attributeName + " attribute value : " + value, true);
		return value;
	}

	public String getCssValue(WebElement element, String propertyName) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String cssValue = element.getCssValue(propertyName);
		Assert.assertNotNull(cssValue, "CSS value is null");
		Reporter.log(propertyName + " CSS value : " + cssValue, true);
		return cssValue;
	}

	public String getTagName(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String tagName = element.getTagName();
		Assert.assertNotNull(tagName, "Tag name is null");
		Reporter.log("Tag name : " + tagName, true);
		return tagName;
	}

	public Point getLocation(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Point location = element.getLocation();
		Assert.assertNotNull(location, "Location is null");
		Reporter.log("Element location : " + location, true);
		return location;
	}

	public Dimension getSize(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Dimension size = element.getSize();
		Assert.assertNotNull(size, "Size is null");
		Reporter.log("Element size : " + size, true);
		return size;
	}

	public boolean isDisplayed(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean status = element.isDisplayed();
		Assert.assertTrue(status, "Element not displayed");
		Reporter.log("Element is displayed", true);
		return status;
	}

	public boolean isEnabled(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean status = element.isEnabled();
		Assert.assertTrue(status, "Element is disabled");
		Reporter.log("Element is enabled", true);
		return status;
	}

	public boolean isSelected(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean status = element.isSelected();
		Reporter.log("Element selected status : " + status, true);
		return status;
	}

	public Rectangle getRect(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Rectangle rect = element.getRect();
		Assert.assertNotNull(rect, "Rectangle is null");
		Reporter.log("Element rectangle : " + rect, true);
		return rect;
	}

	public void submit(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.submit();
		Reporter.log("Form submitted successfully", true);
	}
	// ================= SELECT CLASS =================

	public void selectByVisibleText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.selectByVisibleText(text);
		String selected = s.getFirstSelectedOption().getText();
		Assert.assertEquals(selected, text, "Dropdown selection failed");
		Reporter.log("Selected by visible text : " + text, true);
	}

	public void selectByContainsVisibleText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.selectByContainsVisibleText(text);
		String selected = s.getFirstSelectedOption().getText();
		Assert.assertEquals(selected, text, "Dropdown selection failed");
		Reporter.log("Selected by visible text : " + text, true);
	}

	public void selectByValue(WebElement element, String value) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.selectByValue(value);
		String selected = s.getFirstSelectedOption().getAttribute("value");
		Assert.assertEquals(selected, value, "Dropdown selection failed");
		Reporter.log("Selected by value : " + value, true);
	}

	public void selectByIndex(WebElement element, int index) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.selectByIndex(index);
		WebElement selected = s.getFirstSelectedOption();
		Assert.assertNotNull(selected, "Dropdown selection failed");
		Reporter.log("Selected by index : " + index, true);
	}

	public void deselectAll(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.deselectAll();
		Assert.assertTrue(s.getAllSelectedOptions().isEmpty(), "Deselect all failed");
		Reporter.log("All options deselected", true);
	}

	public void deselectByVisibleText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.deselectByVisibleText(text);
		Reporter.log("Deselected by visible text : " + text, true);
	}

	public void deselectByContainsVisibleText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.deSelectByContainsVisibleText(text);
		String selected = s.getFirstSelectedOption().getText();
		Assert.assertEquals(selected, text, "Dropdown deselection failed");
		Reporter.log("Selected by visible text : " + text, true);
	}

	public void deselectByValue(WebElement element, String value) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.deselectByValue(value);
		Reporter.log("Deselected by value : " + value, true);
	}

	public void deselectByIndex(WebElement element, int index) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		s.deselectByIndex(index);
		Reporter.log("Deselected by index : " + index, true);
	}

	public boolean isMultiple(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		boolean status = s.isMultiple();
		Reporter.log("Dropdown multiple status : " + status, true);
		return status;
	}

	public List<WebElement> getOptions(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		Assert.assertTrue(options.size() > 0, "No options found");
		Reporter.log("Total options : " + options.size(), true);
		return options;
	}

	public List<WebElement> getAllSelectedOptions(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		List<WebElement> selected = s.getAllSelectedOptions();
		Reporter.log("Total selected options : " + selected.size(), true);
		return selected;
	}

	public WebElement getFirstSelectedOption(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Select s = new Select(element);
		WebElement first = s.getFirstSelectedOption();
		Assert.assertNotNull(first, "No option selected");
		Reporter.log("First selected option : " + first.getText(), true);
		return first;
	}

	// ================= ACTIONS CLASS =================

	public Actions clickonElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.click(element);
		Reporter.log("Click action created on element", true);
		return act;
	}

	public Actions doubleClick(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.doubleClick(element);
		Reporter.log("Double click action created on element", true);
		return act;
	}

	public Actions contextClick(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.contextClick(element);
		Reporter.log("Context click action created on element", true);
		return act;
	}

	public Actions moveToElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.moveToElement(element);
		Reporter.log("Move to element action created", true);
		return act;
	}

	public Actions scrolltoElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.scrollToElement(element);
		Reporter.log("Scroll to element action created", true);
		return act;
	}

	public Actions dragAndDrop(WebElement source, WebElement target) {
		wait.until(ExpectedConditions.visibilityOf(source));
		wait.until(ExpectedConditions.visibilityOf(target));
		Actions act = actions.dragAndDrop(source, target);
		Reporter.log("Drag and drop action created", true);
		return act;
	}

	public Actions pause(Duration duration) {
		Actions act = actions.pause(duration);
		Reporter.log("Pause action created", true);
		return act;
	}

	public Actions sendKeys(CharSequence keys) {
		Actions act = actions.sendKeys(keys);
		Reporter.log("Sendkeys action created", true);
		return act;
	}

	public Actions keyDown(CharSequence key) {
		Actions act = actions.keyDown(key);
		Reporter.log("Key down action created", true);
		return act;
	}

	public Actions keyDown(WebElement element, CharSequence key) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.keyDown(element, key);
		Reporter.log("Key down action created on element", true);
		return act;
	}

	public Actions keyUp(CharSequence key) {
		Actions act = actions.keyUp(key);
		Reporter.log("Key up action created", true);
		return act;
	}

	public Actions keyUp(WebElement element, CharSequence key) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.keyUp(element, key);
		Reporter.log("Key up action created on element", true);
		return act;
	}

	public Actions clickAndHold(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.clickAndHold(element);
		Reporter.log("Click and hold action created on element", true);
		return act;
	}

	public Actions release(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.release(element);
		Reporter.log("Release action created on element", true);
		return act;
	}

	public Actions dragAndDropBy(WebElement element, int x, int y) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Actions act = actions.dragAndDropBy(element, x, y);
		Reporter.log("Drag and drop by offset created", true);
		return act;
	}

	// ================= ROBOT =================

	public void robotEnter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Reporter.log("Robot Enter pressed", true);
	}

	// ================= JAVASCRIPT =================

	public void jsClick(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		js.executeScript("arguments[0].click();", element);
		Reporter.log("JS click executed", true);
	}

	public void jsSendKeys(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
		js.executeScript("arguments[0].value='" + text + "';", element);
		String value = element.getAttribute("value");
		Assert.assertEquals(value, text);
		Reporter.log("Javascript send keys executed", true);
	}

	public void highlightElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		js.executeScript("arguments[0].style.border='3px solid red'", element);
		Reporter.log("Element highlighted", true);
	}

	public void scrollToElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Reporter.log("Scrolled to element", true);
	}

	public void scrollToTop() {
		js.executeScript("window.scrollTo(0,0)");
		Reporter.log("Scrolled to top", true);
	}

	public void scrollToBottom() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Reporter.log("Scrolled to bottom", true);
	}

	public void scrollByPixel(int x, int y) {
		js.executeScript("window.scrollBy(" + x + "," + y + ")");
		Reporter.log("Scrolled by pixel", true);
	}

	public String getTitleByJS() {
		String title = js.executeScript("return document.title;").toString();
		Assert.assertNotNull(title);
		Reporter.log("Title captured using JS", true);
		return title;
	}

	public String getURLByJS() {
		String url = js.executeScript("return document.URL;").toString();
		Assert.assertNotNull(url);
		Reporter.log("URL captured using JS", true);
		return url;
	}

	public void refreshBrowserByJS() {
		js.executeScript("history.go(0)");
		Reporter.log("Browser refreshed using JS", true);
	}

	public void drawBorder(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		js.executeScript("arguments[0].style.border='5px solid red'", element);
		Reporter.log("Border drawn on element", true);
	}

	public void flashElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			js.executeScript("arguments[0].style.backgroundColor='yellow'", element);
			js.executeScript("arguments[0].style.backgroundColor='" + bgcolor + "'", element);
		}
		Reporter.log("Element flashed", true);
	}

	// ================= ALERT =================

	public void acceptAlert() {
		Alert a = wait.until(ExpectedConditions.alertIsPresent());
		String text = a.getText();
		a.accept();
		Assert.assertNotNull(text);
		Reporter.log("Alert accepted : " + text, true);
	}

	public void dismissAlert() {
		Alert a = wait.until(ExpectedConditions.alertIsPresent());
		a.dismiss();
		Reporter.log("Alert dismissed", true);
	}

	public String getAlertText() {
		Alert a = driver.switchTo().alert();
		String text = a.getText();
		Assert.assertNotNull(text);
		return text;
	}

	public void alertSendKeys(String text) {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.sendKeys(text);
		Assert.assertNotNull(text, "Alert text is null");
		Reporter.log("Text entered in alert : " + text, true);
	}

	// ================= WINDOWS =================

	public void switchWindow(String title) {
		Set<String> windows = driver.getWindowHandles();

		for (String win : windows) {
			driver.switchTo().window(win);
			if (driver.getTitle().contains(title)) {
				break;
			}
		}
		Assert.assertTrue(driver.getTitle().contains(title));
		Reporter.log("Switched to window : " + title, true);
	}

	// ================= SCREENSHOT =================

	public File takeScreenshot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		Assert.assertNotNull(src);
		return src;
	}

	// ================= WAIT =================

	public void waitForVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Assert.assertTrue(element.isDisplayed());
	}

	public void waitForClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Assert.assertTrue(element.isDisplayed());
	}

	// ================= CHROME OPTIONS =================

	public ChromeOptions headless() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return options;
	}

	public ChromeOptions incognito() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		return options;
	}

	public ChromeOptions disableNotifications() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		return options;
	}

	public ChromeOptions startMaximixed() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		return options;
	}
	
	public ChromeOptions addExtension(String path) {
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File(path));
		return options;
	}
}