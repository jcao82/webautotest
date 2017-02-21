package com.jcao.util.data;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.jcao.logger.Log;

public class Property {

	public Property() {
		Log.initLog();
	}

	static String profilepath = "./resources/config.properties";

	private static Properties props = new Properties();

	static {

		try {

			InputStream in = new BufferedInputStream(new FileInputStream(profilepath));

			props.load(in);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

			System.exit(-1);

		} catch (IOException e) {

			System.exit(-1);
		}
	}

	public String readProperty(String key) {

		String str = null;
		try {
			str = new String((props.getProperty(key)).getBytes("ISO8859-1"), "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Log.writeErrorInfo("没有对应的key, 数据获取失败.");
		}
		return str;
	}

	/***
	 * 
	 * @param key
	 * @param value
	 * 
	 *            public void setProperty(String key, String value) { try {
	 * 
	 *            OutputStream fos = new FileOutputStream(profilepath);
	 *            props.setProperty(key, value); // props.store(fos, "Update '"
	 *            + key + "' value");
	 * 
	 *            Log.writeInfo(key + " = " + value + " 成功写入."); } catch
	 *            (IOException e) { Log.writeErrorInfo(key + " = " + value + "
	 *            写入失败."); } }
	 * 
	 *            public void updateProperty(String key, String value) { try {
	 *            props.load(new FileInputStream(profilepath)); OutputStream fos
	 *            = new FileOutputStream(profilepath); props.setProperty(key,
	 *            value); Log.writeInfo(key + " = " + value + " 成功更新.");
	 * 
	 *            } catch (IOException e) { Log.writeErrorInfo(key + " = " +
	 *            value + " 更新失败."); } }
	 * 
	 *            public static void main(String[] args) { Property data = new
	 *            Property();
	 * 
	 *            String s = data.readProperty("a"); System.out.println(s); }
	 */
}
