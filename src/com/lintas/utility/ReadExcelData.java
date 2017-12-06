package com.lintas.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ReadExcelData {
	public static List<Object> readPaymentFile(File excelFile)  {
		List<Object> list=new ArrayList<>();
		try {
			FileInputStream inputStream = new FileInputStream(excelFile);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			int i=0;
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				i++;
				if(i>1){
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					 switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						list.add(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						list.add(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						list.add(cell.getNumericCellValue());
						break;
					}
				}
			}
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
   
}
