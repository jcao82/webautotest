package com.jcao.pagefunction;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {

    protected boolean isWebElementExist(WebDriver driver, By selector) {   
        try {   
            driver.findElement(selector);  
            return true;   
        } catch(NoSuchElementException e) {   
            return false;   
        }   
     } 
}
