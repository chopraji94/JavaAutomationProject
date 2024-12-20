package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\Login_Data.xlsx";
		
		ExcelUtility utility = new ExcelUtility(path);
		
		int totalRows = utility.getRowCount("Sheet1");
		int totalCols = utility.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols];
		
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalCols;j++) {
				loginData[i-1][j] = utility.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	}
}
