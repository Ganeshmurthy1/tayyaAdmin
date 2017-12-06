package com.admin.insurance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.admin.insurance.daoImp.TrawellTagInsuranceDao;
import com.admin.insurance.model.TrawellTagCategory;
import com.admin.insurance.model.TrawellTagCountries;
import com.admin.insurance.model.TrawellTagPlan;
import com.admin.insurance.model.TrawellTagPremiumChart;

import com.opensymphony.xwork2.ActionSupport;



public class InsuranceDataUploadAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InsuranceDataUploadAction.class);
	TrawellTagInsuranceDao trawellTagInsuranceDao = new TrawellTagInsuranceDao();

	private File excelFile;
	private String contentType;
	private String filename;
	public String doExcelUpload() {
		log.info("UploadAction doExcelUpload start");
		log.info("*** " + excelFile + "\t" + excelFile);
		log.info("*** " + excelFile + "\t" + excelFile.length());
		log.info("filenames:");
		log.info("*** " + filename);
		log.info("content types:");
		log.info("*** " + contentType);	            
		String uploadStatus = getFileInsuranceDataFromExcel(excelFile);	      
		if(uploadStatus.equalsIgnoreCase("Success"))
		{
			log.info("UploadAction doExcelUpload end");
			addActionMessage("File uploaded successfully.");
			return SUCCESS;
		}
		else
		{
			log.info("UploadAction doExcelUpload Failed");
			addActionError("File uploaded Failed.");			
			return ERROR;
		}
	}

	public  String getFileInsuranceDataFromExcel(File file) {	
		String uploadStatus = "";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.info("File not found in the specified path.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(inputStream != null){
			try{
				org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);
				// Get cagetory 
				org.apache.poi.ss.usermodel.Sheet categorySheet = workbook.getSheetAt(0);	
				List<TrawellTagCategory> trawellTagCategoryList = new ArrayList<>();
				for(Row myRow : categorySheet){	
					if(myRow.getRowNum() != 0){
						TrawellTagCategory trawellTagCategory = new TrawellTagCategory();
						Cell codeCell = myRow.getCell(0);	
						Cell descCell = myRow.getCell(1);
						codeCell.setCellType(Cell.CELL_TYPE_STRING);
						descCell.setCellType(Cell.CELL_TYPE_STRING);
						trawellTagCategory.setCode(codeCell.getStringCellValue());
						trawellTagCategory.setDescription(descCell.getStringCellValue());
						trawellTagCategory.setCreatedAt(new Timestamp(new Date().getTime()));
						trawellTagCategoryList.add(trawellTagCategory);
					}
				}				
				
				
				List<TrawellTagCategory> trawellTagCategoryListUpdated = trawellTagInsuranceDao.insertTrawellTagCategory(trawellTagCategoryList);

				// Get Country
				org.apache.poi.ss.usermodel.Sheet countrySheet = workbook.getSheetAt(1);	
				List<TrawellTagCountries> trawellTagCountriesList = new ArrayList<>();
				for(Row myRow : countrySheet){	
					if(myRow.getRowNum() != 0){
						TrawellTagCountries trawellTagCountries = new TrawellTagCountries();
						Cell codeCell = myRow.getCell(0);	
						Cell descCell = myRow.getCell(1);
						codeCell.setCellType(Cell.CELL_TYPE_STRING);
						descCell.setCellType(Cell.CELL_TYPE_STRING);
						trawellTagCountries.setCode(Integer.parseInt(codeCell.getStringCellValue()));
						trawellTagCountries.setDescription(descCell.getStringCellValue());
						trawellTagCountries.setCreatedAt(new Timestamp(new Date().getTime()));
						trawellTagCountriesList.add(trawellTagCountries);
					}
				}				
			
				List<TrawellTagCountries> trawellTagCountriesListUpdated = trawellTagInsuranceDao.insertTrawellTagCountries(trawellTagCountriesList);

				// Get Plan
				org.apache.poi.ss.usermodel.Sheet planSheet = workbook.getSheetAt(2);
				List<TrawellTagPlan> trawellTagPlanList = new ArrayList<>();
				for(Row myRow : planSheet){	
					if(myRow.getRowNum() != 0){	
						TrawellTagPlan trawellTagPlan = new TrawellTagPlan();
						Cell codeCell = myRow.getCell(0);
						codeCell.setCellType(Cell.CELL_TYPE_STRING);
						Cell nameCell = myRow.getCell(1);						
						nameCell.setCellType(Cell.CELL_TYPE_STRING);
						Cell  categoryCodeCell = myRow.getCell(2);						
						categoryCodeCell.setCellType(Cell.CELL_TYPE_STRING);
						Cell  dayplanCell = myRow.getCell(3);						
						dayplanCell.setCellType(Cell.CELL_TYPE_STRING);
						Cell  tToptionscell = myRow.getCell(4);						
						tToptionscell.setCellType(Cell.CELL_TYPE_STRING);
						Cell  annualplancell = myRow.getCell(5);						
						annualplancell.setCellType(Cell.CELL_TYPE_STRING);
						Cell  countryCodecell = myRow.getCell(6);						
						countryCodecell.setCellType(Cell.CELL_TYPE_STRING);
						
						trawellTagPlan.setPlanCode(codeCell.getStringCellValue());
						trawellTagPlan.setPlanName(nameCell.getStringCellValue());
						
						for (TrawellTagCategory trawellTagCategory : trawellTagCategoryListUpdated) {
							if(trawellTagCategory.getCode().equalsIgnoreCase(categoryCodeCell.getStringCellValue())){
								trawellTagPlan.setTrawellTagCategory(trawellTagCategory);
							}
						}
						trawellTagPlan.setIsDayPlan(Boolean.getBoolean(dayplanCell.getStringCellValue()));
						trawellTagPlan.setIsTrawellTagOption(Boolean.getBoolean(tToptionscell.getStringCellValue()));
						trawellTagPlan.setIsAnnualPlan(Boolean.getBoolean(annualplancell.getStringCellValue()));
						
						for (TrawellTagCountries trawellTagCountries : trawellTagCountriesListUpdated) {
							if(trawellTagCountries.getCode() == Integer.parseInt(countryCodecell.getStringCellValue())){
								trawellTagPlan.setTrawellTagCountries(trawellTagCountries);
							}
						}
						trawellTagPlan.setCreatedAt(new Timestamp(new Date().getTime()));
						
						trawellTagPlanList.add(trawellTagPlan);
					}
				}

			
				List<TrawellTagPlan> trawellTagPlanListUpdated = trawellTagInsuranceDao.insertTrawellTagPlan(trawellTagPlanList);
				
				// Get Premium Chart
				org.apache.poi.ss.usermodel.Sheet premiumSheet = workbook.getSheetAt(3);
				List<TrawellTagPremiumChart> trawellTagPremiumChartList = new ArrayList<>();
				for(Row myRow : premiumSheet){	
					if(myRow.getRowNum() != 0){			
						TrawellTagPremiumChart trawellTagPremiumChart = new TrawellTagPremiumChart();
						Cell plancodeCell = myRow.getCell(0);
						plancodeCell.setCellType(Cell.CELL_TYPE_STRING);
						Cell agelimitCell = myRow.getCell(1);						
						agelimitCell.setCellType(Cell.CELL_TYPE_STRING);
						Cell  daylimitCell = myRow.getCell(2);						
						daylimitCell.setCellType(Cell.CELL_TYPE_STRING);
						Cell  premiumCell = myRow.getCell(3);						
						premiumCell.setCellType(Cell.CELL_TYPE_STRING);
						
						for (TrawellTagPlan trawellTagPlan : trawellTagPlanListUpdated) {
							if(trawellTagPlan.getPlanCode().equalsIgnoreCase(plancodeCell.getStringCellValue())){
								trawellTagPremiumChart.setTrawellTagPlan(trawellTagPlan);
							}							
						}
						trawellTagPremiumChart.setAgeLimit(Integer.parseInt(agelimitCell.getStringCellValue()) );
						trawellTagPremiumChart.setDayLimit(Integer.parseInt(daylimitCell.getStringCellValue()) );
						trawellTagPremiumChart.setPremiumAmount(new BigDecimal(premiumCell.getStringCellValue()));
						trawellTagPremiumChart.setCreatedAt(new Timestamp(new Date().getTime()));
						trawellTagPremiumChartList.add(trawellTagPremiumChart);
					}
				}
			
				List<TrawellTagPremiumChart> trawellTagPremiumChartListUpdated = trawellTagInsuranceDao.insertTrawellTagPremiumChart(trawellTagPremiumChartList);

				uploadStatus = "Success";
			}catch (Exception e) {
				log.info("InsuranceDataUploadAction  Exception " +e );
			}
		}
		return uploadStatus;
	}
	public static HashMap<String, String> getMapedValues(Sheet sheet){
		HashMap<String, String> Map = new HashMap<>();
		try{
			for(Row myRow : sheet){			
				if(myRow.getRowNum() != 0){					
					Cell codeCell = myRow.getCell(0);	
					Cell descCell = myRow.getCell(1);
					codeCell.setCellType(Cell.CELL_TYPE_STRING);
					descCell.setCellType(Cell.CELL_TYPE_STRING);
					if(!codeCell.getStringCellValue().equalsIgnoreCase(""))
						Map.put(descCell.getStringCellValue(), codeCell.getStringCellValue());	
				}
			}

		}catch (Exception e) {
			log.info("getCellvaluesMap " +e);
		}
		return Map;
	}

	public File getExcelFile() {
		return excelFile;
	}

	public String getContentType() {
		return contentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
