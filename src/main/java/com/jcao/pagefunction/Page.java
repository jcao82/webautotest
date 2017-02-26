package com.jcao.pagefunction;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jcao.logger.Log;
import com.jcao.util.Utils;
import com.jcao.util.data.MessageProperties;

public class Page {

	private WebDriver webdriver = null; // 仅限类内使用的WebDriver
	public static WebDriver driver = null;

	private static long timeOutInSeconds = 30;

	public Page(WebDriver mydriver) {
		Log.initLog();
		this.webdriver = mydriver;
		driver = mydriver;
	}

	public Page() {
		if (this.webdriver == null) {
			this.webdriver = driver;
		}
	}

	public void goToURL(String url) {
		Log.writeInfo(MessageProperties.msgReadProperty("url") + "  " + url);
		webdriver.get(url);
	}

	public WebElement findElementById(String elementName) {
		WebElement webElement = null;
		try {
			webElement = webdriver.findElement(By.id(elementName));
			highlightElement(webdriver, webElement);
			Log.writeInfo(MessageProperties.msgReadProperty("selectedElement"));
		} catch (Exception e) {
			Log.writeErrorInfo(MessageProperties.msgReadProperty("elementNotExist"));
			Assert.assertTrue(false);

		}
		return webElement;
	}

	public boolean isWebElementExist(By selector) {
		try {
			webdriver.findElement(selector);
			Log.writeInfo(MessageProperties.msgReadProperty("isWebElementExist"));

			return true;
		} catch (Exception e) {
			Log.writeErrorInfo(MessageProperties.msgReadProperty("elementNotExist"));
			webdriver.quit();
			Assert.assertTrue(false);
			return false;
		}
	}

	public WebElement waitElement(final By ByPath) {

		WebDriverWait driverWait = new WebDriverWait(webdriver, timeOutInSeconds);

		WebElement element = driverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {

				WebElement element = driver.findElement(ByPath);

				return element;
			}
		});

		return element;
	}

	public void clickButtonByJS(WebDriver webdriver, WebElement webElement) {
		try {

			((JavascriptExecutor) webdriver).executeScript("arguments[0].click();", webElement);
			Log.writeInfo(MessageProperties.msgReadProperty("clickButtonByJS"));

		} catch (Exception e) {
			Assert.assertTrue(false);
			Log.writeErrorInfo(MessageProperties.msgReadProperty("errorClickButtonByJS"));
		}

	}

	public WebElement waitElementInSec(long timeOutInSeconds, WebDriver driver, final By ByPath) {

		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);

		WebElement element = driverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {

				WebElement element = driver.findElement(ByPath);

				return element;
			}
		});

		return element;
	}

	public static void highlightElement(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("element = arguments[0];" + "original_style = element.getAttribute('style');"
				+ "element.setAttribute('style', original_style + \";"
				+ "background: yellow; border: 2px solid red;\");"
				+ "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", element);
	}

	public static void takeScreenShot(WebDriver driver) {
		String nowDate =Utils.getNowDate();
		
		
	}
	
	public static void main(String[] args) {

		Page page = new Page(new FirefoxDriver());
		page.goToURL("http://www.baidu.com");
		System.out.println("@@@:" + page.isWebElementExist(By.xpath(".//*[@id='kw']")));
		System.out.println("@@@:" + page.waitElement(By.xpath(".//*[@id='kw']")).getAttribute("name"));
		page.findElementById("kws").sendKeys("sss");
		;
		Utils.sleepinMsec(5000);
		driver.quit();
	}
}
