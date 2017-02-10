package com.jcao.pagefunction;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	
    public static boolean isWebElementExist(WebDriver driver, By selector) {   
        try {   
            driver.findElement(selector);  
            return true;   
        } catch(NoSuchElementException e) {   
            return false;   
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
}
