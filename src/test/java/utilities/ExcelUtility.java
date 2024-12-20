package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileInputStream fs;
	public FileOutputStream fo;
	public XSSFCellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException  {
		fs = new FileInputStream(path);
		workBook = new XSSFWorkbook(fs);
		sheet = workBook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workBook.close();
		fs.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName,int rowNum) throws IOException {
		fs = new FileInputStream(path);
		workBook = new XSSFWorkbook(fs);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fs.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName,int rowNum,int colNum) throws IOException {
		fs = new FileInputStream(path);
		workBook = new XSSFWorkbook(fs);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		}
		catch(Exception ex) {
			data="";
		}
		workBook.close();
		fs.close();
		return data;
	}
	
	public void setCellData(String sheetName,int rowNum,int cellNum,String data)throws IOException{
		File xlFile = new File(path);
		if(!xlFile.exists()) {
			workBook= new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workBook.write(fo);
		}
		
		fs = new FileInputStream(path);
		workBook  = new XSSFWorkbook(fs);
		
		if(workBook.getSheetIndex(sheetName)==-1) {
			workBook.createSheet(sheetName);
		}
		
		sheet = workBook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum) == null) {
			sheet.createRow(rowNum);
		}
		
		row = sheet.getRow(rowNum);
		
		cell = row.createCell(cellNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fs.close();
		fo.close();
	}
	
	
	public void fillGreenColor(String sheetName,int rowNum,int cellNum) throws IOException {
		fs = new FileInputStream(path);
		workBook = new XSSFWorkbook(fs);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		style = workBook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workBook.write(fo);
		workBook.close();
		fs.close();
	}
}
