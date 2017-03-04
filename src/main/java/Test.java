import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test {

	@SuppressWarnings("deprecation")
	public ArrayList<String> getElementPathByName(String name) {
		int numberOfSheet = 0;
		int rows = 0;
		int col = 0;
		int currentRow = 0;
		ArrayList<String> list = new ArrayList<String>();

		try {
			Workbook book = Workbook.getWorkbook(new File("./resources/ElementData.xls"));
			Sheet sheet = book.getSheet(numberOfSheet);
			rows = sheet.getRows();
			col = sheet.getColumns();

			for (int i = 0; i < rows; i++) {
				String elementName = sheet.getCell(0, i).getContents();
				if (elementName.equals(name)) {
					currentRow = i;
					break;
				}

			}
			
			for (int i = currentRow + 1; i < rows; i++) {
				String keyName = sheet.getCell(0, i).getContents();
				if (keyName.equals(name)) {
					Assert.assertFalse(name + " 是重复的,取值不准确", true);
		
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
		return list;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws BiffException, IOException {
		// Test t = new Test();
		// t.file("./resources/message.properties");
		ArrayList<String> list = new ArrayList<String>();
		String name = "s";
		int currentRow = 0;
		Workbook book = Workbook.getWorkbook(new File("./resources/ElementData.xls"));
		Sheet sheet = book.getSheet(0);
		int rows = sheet.getRows();
		int col = sheet.getColumns();
		// System.out.println(sheet.getCell(0, 1).getContents());
		for (int i = 0; i < rows; i++) {
			String keyName = sheet.getCell(0, i).getContents();
			if (keyName.equals(name)) {
				currentRow = i;
				break;
			}
		}

		for (int i = currentRow + 1; i < rows; i++) {
			String keyName = sheet.getCell(0, i).getContents();
			if (keyName.equals(name)) {
				Assert.assertFalse(name + " 是重复的,取值不准确", true);
				break;
			}
		}

		for (int i = 0; i < col; i++) {
			String value = sheet.getCell(i, currentRow).getContents();
			if (value != "") {
				list.add(value);
			}

		}

		System.out.println(list);
		Test t = new Test();
		System.out.println(t.getElementPathByName("s"));
	}
}
