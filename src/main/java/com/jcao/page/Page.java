package com.jcao.page;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.jcao.log.Log;

public class Page {

	private WebDriver webdriver = null; // 仅限类内使用的WebDriver

	public static WebDriver driver = null;

	public Page(WebDriver mydriver) {
		Log.initLog();
		this.webdriver = mydriver;
		driver = mydriver;
	}

	public void goToURL(String url) {
		Log.info("Goto" + ": " + url);
		webdriver.get(url);
	}

	
	
	public boolean isXpathAvailable(String xpath) {
		Boolean isExist = false;
		try {
			
			final By by = By.xpath(xpath);
			return webdriver.findElements(by).size()>0;
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return isExist;
		}
	}

	public WebElement waitElement(final By ByPath, long timeout) {

		WebDriverWait driverWait = new WebDriverWait(webdriver, timeout);

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
			takeScreenShot(webdriver);
			Assert.assertTrue(false);
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

	public void highlightElement(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("element = arguments[0];" + "original_style = element.getAttribute('style');"
				+ "element.setAttribute('style', original_style + \";"
				+ "background: yellow; border: 2px solid red;\");"
				+ "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", element);
	}

	public static void takeScreenShot(WebDriver driver) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		// 格式化当前时间，例如20120406-165210
		String time = sdf.format(new Date());
		String dir_name = "./resources/screenshot";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// 将截图存放到指定目录,并以当前时间戳作为文件名保存
			FileUtils.copyFile(scrFile, new File(dir_name + File.separator + time + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Page page = new Page(new HtmlUnitDriver());
		page.goToURL("http://www.baidu.com");
		System.out.println(By.);
		page.webdriver.close();
		
	}
}
