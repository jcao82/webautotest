package com.jcao.logger;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class d {

	private Logger log;

	public void a(Logger paramLogger) {
		this.log = paramLogger;
	}
	
	public void writeInfo(String paramString) {
		this.log.info(paramString);
		Reporter.log(paramString);
	}
	
	public void writeErrorInfo(String paramString) {
		this.log.error(paramString);
		Reporter.log(paramString);
	}
	
}
