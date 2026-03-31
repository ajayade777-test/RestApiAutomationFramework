package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	
	public static FileInputStream inputStream;
	public static XSSFWorkbook workbook;
	public static XSSFSheet excelSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	
	public static String getCellValue(String fileName, String sheetName, int rowNo , int cellNo) throws Exception {
	    inputStream = new FileInputStream(fileName);
	    workbook = new XSSFWorkbook(inputStream);
	    excelSheet = workbook.getSheet(sheetName);

	    row = excelSheet.getRow(rowNo);
	    String value = "";

	    if (row != null) {
	        cell = row.getCell(cellNo);
	        if (cell != null) {
	            value = cell.toString();
	            if (value.endsWith(".0")) {
	                value = value.substring(0, value.length() - 2);
	            }
	        }
	    }

	    workbook.close();
	    return value;
	}
	
	
	
	
	public static int getRowCount (String fileName, String sheetName) throws IOException {
		inputStream = new FileInputStream(fileName);
		workbook = new XSSFWorkbook(inputStream);
		excelSheet = workbook.getSheet(sheetName);
		
		//get total no. of rows
		int ttlRows = excelSheet.getLastRowNum() + 1;
		workbook.close();
		return ttlRows;
		
	}

	public static int getColCount (String fileName, String sheetName) throws IOException {
		inputStream = new FileInputStream(fileName);
		workbook = new XSSFWorkbook(inputStream);
		excelSheet = workbook.getSheet(sheetName);
		
		//get total no. of columns
		int ttlCells = excelSheet.getRow(0).getLastCellNum();
		workbook.close();
		return ttlCells;
}
}
