package com.jcao.log;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;


public class Log {

	private static Logger log;

	public static void initLog() {
		String rootPath = null;
		File directory = new File("");
		try {
			rootPath = directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			
			PropertyConfigurator.configure(rootPath + "//resources//log4j.ini");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log = Logger.getLogger("tracelog");
		TestCaseLog.setLogger(log);
	}
	
	public static void writeInfo(String msg) {
		TestCaseLog.writeInfo(msg);

	}
	public static void writeErrorInfo(String msg) {
		TestCaseLog.writeErrorInfo(msg);
		Assert.assertTrue(false);

	}
	public static void main(String[] args) {
		Log.initLog();
		Log.writeInfo("dddd");
		Log.writeErrorInfo("sss");
	}
}
