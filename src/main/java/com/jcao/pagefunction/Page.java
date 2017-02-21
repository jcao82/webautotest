package com.jcao.pagefunction;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jcao.logger.Log;
import com.jcao.util.Utils;

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

	public void toURL(String url) {
		webdriver.get(url);
	}

	public WebElement findElementById(String elementName) {

		return null;
	}

	public boolean isWebElementExist(By selector) {
		try {
			webdriver.findElement(selector);
			return true;
		} catch (Exception e) {
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
		} catch (Exception e) {
			Log.writeInfo("JavaScript脚本点击按钮错误.");
		}

	}

	public static void main(String[] args) {

		Page page = new Page(new FirefoxDriver());
		page.toURL("http://www.baidu.com");
		System.out.println("@@@:" + page.isWebElementExist(By.xpath(".//*[@id='kw']")));
		System.out.println("@@@:" + page.waitElement(By.xpath(".//*[@id='kw']")).getAttribute("name"));
		Utils.sleepinMsec(5000);
		driver.quit();
	}
}
