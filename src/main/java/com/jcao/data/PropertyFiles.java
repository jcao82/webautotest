package com.jcao.data;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.jcao.log.Log;

public class PropertyFiles {

	public PropertyFiles() {
		Log.initLog();
	}

	public static String getValue(String filePath, String key) {

		String value = "";
		Properties props = new Properties();
		InputStream in;

		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {

			e.printStackTrace();
			System.exit(-1);
		}

		try {
			value = new String((props.getProperty(key)).getBytes("ISO8859-1"), "utf8");
		} catch (UnsupportedEncodingException e) {
			Log.writeErrorInfo("没有对应的key, 数据获取失败.");
			e.printStackTrace();
			System.exit(-1);

		}

		return value;
	}

	public static void main(String[] args) {
		System.out.println(getValue("./resources/PropertyFiles/config.properties","key"));
	}

}
