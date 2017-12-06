package com.lintas.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.admin.ageing.report.dto.AgeingReportVO;
import com.common.dsr.CommonDsrPage;
import com.common.dsr.CommonDsrTravelReportData;
import com.lintas.admin.model.Company;

public class AgeingReportsExportToExcel {
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AgeingReportsExportToExcel.class);
	public static boolean generateAgeingReporExcel(AgeingReportVO ageingReportVO, String path,Company companyObj,CommonDsrPage commonDsrPage,String fileName) {
		 Set<String> monthHeadings=ageingReportVO.getMonthHeadings();
		
		
		boolean isGenerated = false;
		try {
			path = path + "excel" + File.separator +fileName;
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			logger.info("Exception " + e);
		}
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Ageing Report Info");
		// Create row object
		XSSFRow row;
		SortedMap<Integer, List<Object>> flightReportInfoMap = new TreeMap<>();
		int i = 1;
		List<Object> headerList = new LinkedList<>();
		headerList.add("Company Name");
		 if(monthHeadings!=null && monthHeadings.size()>0){
			 for(String heading : monthHeadings){
				 headerList.add(heading);
			 }
				 
		 }
		 headerList.add("Total Outstanding");
		flightReportInfoMap.put(i, headerList);
		 if (ageingReportVO != null && ageingReportVO.getCompanyTypeMonthList()!=null&& ageingReportVO.getCompanyTypeMonthList().size() > 0) {
			 if (ageingReportVO != null && ageingReportVO.getCompanyTypeMonthList()!=null&& ageingReportVO.getCompanyTypeMonthList().size() > 0) {
				 for (int j=0;j<ageingReportVO.getCompanyTypeMonthList().size();j++) {
					 AgeingReportVO ageingReportVONew =ageingReportVO.getCompanyTypeMonthList().get(j);
					 i++;
					 List<Object> dataList = new LinkedList<>();
						dataList.add(ageingReportVONew.getCompanyType());
						BigDecimal totalOutstanding =new BigDecimal(0);
						Set<String> keys=ageingReportVONew.getOutStandingCompanyTypeMap().keySet();
						 for(String key:keys){
							 dataList.add(ageingReportVONew.getOutStandingCompanyTypeMap().get(key).toString());
							 totalOutstanding=totalOutstanding.add(ageingReportVONew.getOutStandingCompanyTypeMap().get(key));
						} 
						 dataList.add(totalOutstanding.toString());
						 flightReportInfoMap.put(i, dataList);
						 
					}
				 
			}   
			 
		}
		 if (ageingReportVO != null && ageingReportVO.getCompanyMonthList()!=null&& ageingReportVO.getCompanyMonthList().size() > 0) {
			 if (ageingReportVO != null && ageingReportVO.getCompanyMonthList()!=null&& ageingReportVO.getCompanyMonthList().size() > 0) {
				 for (int j=0;j<ageingReportVO.getCompanyMonthList().size();j++) {
					 AgeingReportVO ageingReportVONew =ageingReportVO.getCompanyMonthList().get(j);
					 i++;
					 List<Object> dataList = new LinkedList<>();
						BigDecimal totalOutstanding =new BigDecimal(0);
						Set<String> keys=ageingReportVONew.getOutstandingCompanyMap().keySet();
						 for(String key:keys){
							 dataList.add(ageingReportVONew.getOutstandingCompanyMap().get(key).toString());
							 totalOutstanding=totalOutstanding.add(ageingReportVONew.getOutstandingCompanyMap().get(key));
							
						 } 
						 dataList.add(ageingReportVONew.getCompanyName());
						 dataList.add(totalOutstanding.toString());
						 flightReportInfoMap.put(i, dataList);
						 
					}
				 
			}   
			 
		}  
		 
		// Iterate over data and write to sheet
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				row = spreadsheet.createRow(rowid++);
				 workbook.getSheetAt(0).autoSizeColumn(rowid);
				if (row.getRowNum() == 0) {
					row.setHeight((short)400);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short)300);
				}

				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(0).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}
					if (cell.getRowIndex() == 1) {
						workbook.getSheetAt(0).autoSizeColumn(cellid);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.BLUE.index);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}
			isGenerated=true; 
		}

		// Write the workbook in file system
		FileOutputStream out;
		try {
			 
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated=true; 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated=false; 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated=false; 
		}
		return isGenerated;
	}
	 

	public static List<Object> getExcelHeadings(List<CommonDsrTravelReportData> list,List<Object> headerList,String pairType){
		if(list!=null && list.size()>0){
			CommonDsrTravelReportData commonDsrTravelReportData=list.get(0);
			if(commonDsrTravelReportData.getExtraRmConfigDetails()==null){
				Map<String,String> fieldsMap=commonDsrTravelReportData.getRmConfigMap();
				if(fieldsMap!=null){
					for(Map.Entry<String,String> entry:fieldsMap.entrySet()){ 
						if(pairType.equals("K"))
							headerList.add(entry.getKey());

					}
				}
			}

		}
		return headerList;
	}

	public static  List<Object> getExcelData(CommonDsrTravelReportData commonDsrTravelReportData,List<Object>  dataList){
		if(commonDsrTravelReportData!=null ){
			if(commonDsrTravelReportData.getExtraRmConfigDetails()==null){
				Map<String,String> fieldsMap=commonDsrTravelReportData.getRmConfigMap();
				if(fieldsMap!=null){
					for(Map.Entry<String,String> entry:fieldsMap.entrySet()){ 
						dataList.add(entry.getValue());

					}
				}
			}
		}

		return dataList;
	}

}
