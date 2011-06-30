package com.gmail.hidekishima.jexcelapi;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * You can create an Excel file (.xls) from Java!
 * I like this API and use it for many projects.
 * 
 * @author Hideki Shima
 *
 */
public class CreatExcelSpreadSheet {

	public static void run() throws Exception {
		WorkbookSettings ws = new WorkbookSettings();
		
		// It worked at least for Japanese, 
		// Chinese (both traditional & simplified), and English of course
		ws.setLocale(new Locale("ja", "JP"));
		ws.setEncoding("Windows-31J");
		
		OutputStream os = new FileOutputStream("target/sample.xls");
		WritableWorkbook workbook = Workbook.createWorkbook(os, ws);
		
		WritableSheet sheet = workbook.createSheet("Sheet1", 0);
		
		sheet.addCell( new Label(0,2,"You can enter Japanese here.") ); //A3
		sheet.addCell( new Number(0,3,4.4) ); //A4
		
		workbook.write();
		workbook.close();
	}
	
	public static void main(String[] args) throws Exception {
		run();
	}
	
}
