package com.jcao.log;

import org.apache.log4j.Logger;

public class TestCaseLog {

	private static InitLog impl = new InitLog();

	public static void writeInfo(String paramString) {
		impl.writeInfo(paramString);
	}

	public static void writeErrorInfo(String paramString) {
		impl.writeErrorInfo(paramString);
	}

	public static void setLogger(Logger paramLogger) {
		impl.setLog(paramLogger);
	}

}
