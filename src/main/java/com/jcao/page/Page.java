package com.jcao.page;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jcao.data.ExcelWebElement;
import com.jcao.data.PropertyFiles;
import com.jcao.log.Log;
import com.jcao.util.Utils;

public class Page {

	private WebDriver webdriver = null; // 仅限类内使用的WebDriver
	private static String msgProp = "./resources/PropertyFiles/message.properties";
	private static long timeOutInSeconds = 30;

	public static WebDriver driver = null;


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
		Log.writeInfo(PropertyFiles.getValue(msgProp,"url") + "  " + url);
		webdriver.get(url);
	}

	public WebElement findElementById(String elementName) {
		WebElement webElement = null;
		try {
			webElement = webdriver.findElement(By.id(ExcelWebElement.getElementPath(elementName)));
			highlightElement(webdriver, webElement);
			Log.writeInfo(PropertyFiles.getValue(msgProp,"selectedElement"));
		} catch (Exception e) {
			takeScreenShot(webdriver);
			Log.writeErrorInfo(PropertyFiles.getValue(msgProp,"elementNotExist"));
			Assert.assertTrue(false);

		}
		return webElement;
	}

	public boolean isWebElementExist(By selector) {
		try {
			webdriver.findElement(selector);
			Log.writeInfo(PropertyFiles.getValue(msgProp,"isWebElementExist"));

			return true;
		} catch (Exception e) {
			Log.writeErrorInfo(PropertyFiles.getValue(msgProp,"elementNotExist"));
			takeScreenShot(webdriver);
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
			Log.writeInfo(PropertyFiles.getValue(msgProp,"clickButtonByJS"));

		} catch (Exception e) {
			Log.writeErrorInfo(PropertyFiles.getValue(msgProp,"errorClickButtonByJS"));
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

	public static void highlightElement(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("element = arguments[0];" + "original_style = element.getAttribute('style');"
				+ "element.setAttribute('style', original_style + \";"
				+ "background: yellow; border: 2px solid red;\");"
				+ "setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", element);
	}

	public static void takeScreenShot(WebDriver driver) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		//格式化当前时间，例如20120406-165210
		String time = sdf.format(new Date());
		String dir_name = "./resources/screenshot";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// 将截图存放到指定目录,并以当前时间戳作为文件名保存
			FileUtils.copyFile(scrFile, new File(dir_name + File.separator + time + ".png"));
			Log.writeInfo(PropertyFiles.getValue(msgProp,"screenshot")+dir_name);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		
		Page page = new Page(new FirefoxDriver());
		page.goToURL("http://www.baidu.com");
		System.out.println(page.webdriver.getTitle());
		Utils.sleepinMsec(3000);
		
		page.webdriver.quit();
	}
}
