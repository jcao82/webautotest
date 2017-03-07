package com.jcao.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;

public class Log {

	private static Logger log;

	public static void initLog() {

		try {

			PropertyConfigurator.configure("./resources/log4j.properties");

		} catch (Exception e) {
			e.printStackTrace();
		}

			log = Logger.getLogger("tracelog");
			TestCaseLog.setLogger(log);
	}

	public static void info(String msg) {
		TestCaseLog.writeInfo(msg);

	}

	public static void error(String msg) {
		TestCaseLog.writeErrorInfo(msg);
		Assert.assertTrue(false);

	}

	public static void main(String[] args) {
		Log.initLog();
		Log.info("dddd");
		Log.error("sss");
	}
}
