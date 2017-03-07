package com.jcao.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jcao.log.Log;

public class Utils {
	
	public Utils(){
		Log.initLog();
	}

	public static void waitSecond(int sec) {
		try {
			int msec = sec*1000;
			Thread.sleep(msec);
			Log.info("Wait "+ sec +" second");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public  String getOS(){
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		return os;
	}
	
	public static String getNowDate(){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
	    String date = sdf.format(new Date());
	    return date;
	    		
	}
	

	public static void main(String[] args) {
		Utils utl = new Utils();
		
		System.err.println(utl.getOS());
		System.err.println(getNowDate());
	}
	
	
}
