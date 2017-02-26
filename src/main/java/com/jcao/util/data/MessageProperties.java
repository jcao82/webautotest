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

public class MessageProperties {

	public MessageProperties() {
		Log.initLog();
	}
	

	static String profilepath = "./resources/message.properties";  //"./resources/message.properties";
	
	public  void file(String file){
		MessageProperties.profilepath = file;
//		System.out.println(profilepath);
	}

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

	public static String msgReadProperty(String key) {

		String str = null;
		try {
			str = new String((props.getProperty(key)).getBytes("ISO8859-1"), "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Log.writeErrorInfo("Can not find the key.");
		}
		return str;
	}
 
	public static void main(String[] args) {
		MessageProperties mp = new MessageProperties();
//		mp.file("./resources/message.properties");
//		System.out.println(mp.msgReadProperty("url"));
		mp.file("./resources/message.properties");
	}
	
}
