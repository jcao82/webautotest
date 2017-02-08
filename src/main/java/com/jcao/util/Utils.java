package com.jcao.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	public static void sleepinMsec(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

	public static WebElement waitElement(WebDriver driver, long timeOutInSeconds, final By ByPath) {

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
}
