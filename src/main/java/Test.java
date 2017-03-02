import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test {

	static String profilepath1 = "";  //"./resources/message.properties";
	
	public  void file(String file){
		Test.profilepath1 = file;
		System.out.println(profilepath1);
	}

	 public static void main(String[] args) throws BiffException, IOException {
//		Test t = new Test();
//		t.file("./resources/message.properties");
	
		 Workbook book = Workbook.getWorkbook(new File("./resources/TestData.xls"));
		 System.out.println(book.toString());
		 Sheet sheet = book.getSheet(1);
		 int rows = sheet.getRows();
		 int  col = sheet.getColumns();
		 
		 System.out.println("rows: "+rows+" "+"col: "+col);
		 
	}
}
