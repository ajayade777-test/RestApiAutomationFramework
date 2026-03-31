package api.utilities;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "AllData")
	public String [][] AllDataProvider() throws Exception {
	    String fName = System.getProperty("user.dir")+"//TestData//TestData.xlsx";

	    int ttlRowCnt = ReadExcelFile.getRowCount(fName, "Sheet2");
	    int ttlColCnt = ReadExcelFile.getColCount(fName, "Sheet2");

	    List<String[]> dataList = new ArrayList<>();

	    for (int rowNo = 1; rowNo < ttlRowCnt; rowNo++) {
	        String[] rowData = new String[ttlColCnt];
	        boolean skipRow = false;

	        for (int colNo = 0; colNo < ttlColCnt; colNo++) {
	            String value = ReadExcelFile.getCellValue(fName, "Sheet2", rowNo, colNo);
	            rowData[colNo] = value;
	            if (colNo == 0 && (value == null || value.trim().isEmpty())) {
	                skipRow = true; // skip if userId is blank
	            }
	        }

	        if (!skipRow) {
	            dataList.add(rowData);
	        }
	    }

	    return dataList.toArray(new String[0][0]);
	}
	
	@DataProvider(name = "userNamesData")
	public String [] UserNameDataProvider() throws Exception {
	    String fName = System.getProperty("user.dir")+"//TestData//TestData.xlsx";

	    int ttlRowCnt = ReadExcelFile.getRowCount(fName, "Sheet2");

	    List<String> userNameList = new ArrayList<>();

	    for (int rowNo = 1; rowNo < ttlRowCnt; rowNo++) {   // skip header row
	        String value = ReadExcelFile.getCellValue(fName, "Sheet2", rowNo, 1); // column 1 = UserName
	        if (value != null && !value.trim().isEmpty()) {
	            userNameList.add(value);
	        }
	    }

	    return userNameList.toArray(new String[0]);
	}
}
