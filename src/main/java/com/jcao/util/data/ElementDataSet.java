package com.jcao.util.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ElementDataSet {

	public static ArrayList<String> getElementAsList(String name) {

		int numberOfSheet = 0;
		int rows = 0;
		int col = 0;
		int currentRow = 0;

		ArrayList<String> list = new ArrayList<String>();
		Workbook book = null;
		try {

			book = Workbook.getWorkbook(new File("./resources/ElementData.xls"));
			Sheet sheet = book.getSheet(numberOfSheet);
			rows = sheet.getRows();
			col = sheet.getColumns();

			for (int i = 0; i < rows; i++) {
				String elementName = sheet.getCell(0, i).getContents();

				if (elementName.equals("")) {
					Assert.assertFalse("There is not element", true);

					break;
				}

				if (elementName.equals(name)) {
					currentRow = i;
					break;
				}

			}

			for (int i = currentRow + 1; i < rows; i++) {
				String keyName = sheet.getCell(0, i).getContents();
				if (keyName.equals(name)) {

					Assert.assertFalse("Element" + name + " is repeated", true);

					break;
				}
			}

			for (int i = 0; i < col; i++) {
				String value = sheet.getCell(i, currentRow).getContents();

				if (value != "") {
					list.add(value);
				}
			}

		} catch (BiffException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		book.close();
		return list;
	}

	public static String getElementPath(String element) {
		String str = "";
		ArrayList<String> list = null;
		
		
		try {

			list = ElementDataSet.getElementAsList(element);
			str = list.get(1);

		} catch (Exception e) {
			Assert.assertFalse("Element " + list.get(0) + " has no value", true);

			e.printStackTrace();
		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(getElementPath("百度"));
	}
}
