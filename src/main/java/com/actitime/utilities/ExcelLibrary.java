package com.actitime.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary extends BaseTest
{
	public static String getStringValueFromCell(String sheet , int row , int cell) throws IOException
	{
		FileInputStream fis=new FileInputStream(p.getProperty("excelPath"));
		Workbook wb = new XSSFWorkbook(fis);
		String cellvalue = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
		return cellvalue;
	}
	
	public static boolean getBooleanValueFromCell(String sheet , int row , int cell) throws IOException
	{
		FileInputStream fis=new FileInputStream(p.getProperty("excelPath"));
		Workbook wb = new XSSFWorkbook(fis);
		boolean cellvalue = wb.getSheet(sheet).getRow(row).getCell(cell).getBooleanCellValue();
		wb.close();
		return cellvalue;
	}
	
	public static Date getDateValueFromCell(String sheet , int row , int cell) throws IOException
	{
		FileInputStream fis=new FileInputStream(p.getProperty("excelPath"));
		Workbook wb = new XSSFWorkbook(fis);
		Date cellvalue = wb.getSheet(sheet).getRow(row).getCell(cell).getDateCellValue();
		wb.close();
		return cellvalue;
	}
	
	public static double getNumericValueFromCell(String sheet , int row , int cell) throws IOException
	{
		FileInputStream fis=new FileInputStream(p.getProperty("excelPath"));
		Workbook wb = new XSSFWorkbook(fis);
		double cellvalue = wb.getSheet(sheet).getRow(row).getCell(cell).getNumericCellValue();
		wb.close();
		return cellvalue;
	}
	
	public static int rowCount(String sheet) throws IOException
	{
		FileInputStream fis=new FileInputStream(p.getProperty("excelPath"));
		Workbook wb=new XSSFWorkbook(fis);
		int rowCount = wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return rowCount;
	}
	public static void cellCount(String sheet) throws IOException
	{
		FileInputStream fis=new FileInputStream(p.getProperty("excelPath"));
		Workbook wb=new XSSFWorkbook(fis);
		for(int i=0;i<=wb.getSheet(sheet).getLastRowNum();i++)
		{
			short cellCount = wb.getSheet(sheet).getRow(i).getLastCellNum(); 
			System.out.println("number of cells present in " +i+ "row " +"is :" +cellCount);
		}
		wb.close();
	}
}