package com.lintas.utility;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.UserDesignation;
import com.opensymphony.xwork2.ActionContext;


public class DesignationExcelToDBUtil {

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DesignationExcelToDBUtil.class);
	public static boolean getFileDataFromExcel1(File file) {
		logger.info("ExcelToXMLUtil getXMLFileDataFromExcel start");
		UserDesignation userdesignation = null;
		InputStream inputStream = null;

		boolean isUploaded = false;
		UserDAO DAO = new UserDAO();

		try {
			inputStream = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			logger.info("File not found in the specified path.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("inputStream" +inputStream);
		try {
			// XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);
			org.apache.poi.ss.usermodel.Sheet mySheet = workbook.getSheetAt(0);
			// XSSFSheet mySheet = workBook.getSheetAt(0);
			int totalRows = mySheet.getPhysicalNumberOfRows();
			logger.info("total no of rows >>>>"+totalRows);
			String headers = null;
			boolean isFirstRow = true;


			for(Row myRow : mySheet){
				userdesignation = new UserDesignation();   
				if (isFirstRow) {
					headers = addHeaderRowCells(myRow);
					userdesignation.setHeaders(headers);
				} else {
					boolean isexists = addVoRowCells1(userdesignation, myRow);

					if(!isexists)
					{
						DAO.SaveandUpdateUserDesignation(userdesignation);
						isUploaded = true;    
					}
					else
					{
						isUploaded = false;  
					}

				}

				if(isFirstRow) {
					isFirstRow = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//  loanData = generateXMLFile(userdesignationlist);
		logger.info("ExcelToXMLUtil getXMLFileDataFromExcel end" );
		return isUploaded;
	}

	public static boolean getFileDataFromExcel(File file) {
		logger.info("ExcelToXMLUtil getXMLFileDataFromExcel start");
		UserDesignation userdesignation = null;
		InputStream inputStream = null;

		boolean isUploaded = false;
		UserDAO DAO = new UserDAO();

		try {
			inputStream = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			logger.info("File not found in the specified path.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("inputStream" +inputStream);
		try {
			// XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);
			org.apache.poi.ss.usermodel.Sheet mySheet = workbook.getSheetAt(0);
			// XSSFSheet mySheet = workBook.getSheetAt(0);
			int totalRows = mySheet.getPhysicalNumberOfRows();
			logger.info("total no of rows >>>>"+totalRows);
			String headers = null;
			boolean isFirstRow = true;
			//List<UserDesignation>  newUserDesignationList= null;
			List<UserDesignation>  newUserDesignationUpdatedList= null;
			Company profile = (Company) ActionContext.getContext().getSession().get("Company");
			List<UserDesignation> existlist = DAO.GetUserDesignationList(profile);	 
			logger.info("existlist.size()-------------------"+existlist.size());
			for(Row myRow : mySheet){
				userdesignation = new UserDesignation();   
				if (isFirstRow) {
					headers = addHeaderRowCells(myRow);
					userdesignation.setHeaders(headers);
				} else {
					//boolean isexists = addVoRowCells(userdesignation, myRow);
					UserDesigtnationPojo newUserDesigtnationPojo=addVoRowCells(userdesignation, myRow,existlist,profile);
					logger.info("newUserDesigtnationPojo.getNewUserDesignationList()--------------"+newUserDesigtnationPojo.getNewUserDesignationList().size());
					if(newUserDesigtnationPojo!=null){
						  if(newUserDesigtnationPojo.getNewUserDesignationList()!=null && newUserDesigtnationPojo.getNewUserDesignationList().size()>0){
								for(UserDesignation userDesignationObj:newUserDesigtnationPojo.getNewUserDesignationList()){
									logger.info("id---------"+userDesignationObj.getId());
									logger.info("getCompanyid---------"+userDesignationObj.getCompanyid());
									logger.info("getDescription-------"+userDesignationObj.getDescription());
									logger.info("getDesignation---------"+userDesignationObj.getDesignation());
									newUserDesignationUpdatedList = DAO.SaveandUpdateUserDesignationExcel(userDesignationObj);
								}
							}
							if(newUserDesignationUpdatedList!=null && newUserDesignationUpdatedList.size()>0) 
								isUploaded = true;   

							else
								isUploaded = false;  

						}
					 

				}

				if(isFirstRow) {
					isFirstRow = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//  loanData = generateXMLFile(userdesignationlist);
		logger.info("ExcelToXMLUtil getXMLFileDataFromExcel end" );
		return isUploaded;
	}



	public static UserDesigtnationPojo addVoRowCells(UserDesignation vo, Row row,List<UserDesignation> existlist,Company loginCompany) {
		logger.info("ExcelToXMLUtil addVoRowCells start");
		List<UserDesignation> newUserDesignationList=new ArrayList<>();
		UserDesigtnationPojo  userDesigtnationPojo= new UserDesigtnationPojo();
		int cellIndex = 0;
		String cellDesignation = "";
		String cellDescription = "";
		UserDesignation userDesignationInnsertObj=new UserDesignation();
		Cell myCell = row.getCell(cellIndex++);
		cellDesignation = myCell.getStringCellValue();   
		 logger.info("cellDesignation--------------------"+cellDesignation);
		myCell = row.getCell(cellIndex++);
		cellDescription = myCell.getStringCellValue();
		 //logger.info("cellDescription--------------------"+cellDescription);
	 
		
		if(!new UserDAO().isUserDesignationExists(cellDesignation).contains(true)){
			userDesignationInnsertObj.setDesignation(cellDesignation);
			userDesignationInnsertObj.setDescription(cellDescription);
			userDesignationInnsertObj.setCompanyid(loginCompany.getCompanyid());
			newUserDesignationList.add(userDesignationInnsertObj);
			userDesigtnationPojo.setNewUserDesignationList(newUserDesignationList);
			
		}
		else{
			userDesigtnationPojo.setNewUserDesignationList(newUserDesignationList);
		}
		
		
		
		
		

		/////Code written By raham
		/*if(existlist!=null && existlist.size()>0){
			for(UserDesignation userdes : existlist){
				if(userdes!=null && userdes.getDesignation().equalsIgnoreCase(cellDesignation)){
					userDesigtnationPojo.setAlreadyExisted(true);
					userDesigtnationPojo.setNewUserDesignationList(newUserDesignationList);
				 }
			 }
		}
		else
		{
			userDesignationInnsertObj.setDesignation(cellDesignation);
			userDesignationInnsertObj.setDescription(cellDescription);
			userDesignationInnsertObj.setCompanyid(loginCompany.getCompanyid());
			newUserDesignationList.add(userDesignationInnsertObj);
			userDesigtnationPojo.setNewUserDesignationList(newUserDesignationList);

		} 
*/
		logger.info("ExcelToXMLUtil addVoRowCells end");
		return userDesigtnationPojo;
	}
 
	public static boolean addVoRowCells1(UserDesignation vo, Row row) {
		logger.info("ExcelToXMLUtil addVoRowCells start");
		int cellIndex = 0;
		boolean isDesignationexists = false;
		String cellDesignation = "";
		String cellDescription = "";
		UserDAO DAO = new UserDAO();
		Company profile = (Company) ActionContext.getContext().getSession().get("Company");
		logger.info("Companyid--------------------"+profile.getCompanyid());
		List<UserDesignation> existlist = DAO.GetUserDesignationList(profile);	    
		Cell myCell = row.getCell(cellIndex++);
		cellDesignation = myCell.getStringCellValue();   
		logger.info("cellDesignation--------------------"+cellDesignation);
		myCell = row.getCell(cellIndex++);
		cellDescription = myCell.getStringCellValue();
		logger.info("cellDescription--------------------"+cellDescription);
		logger.info("cellDescription--------------------"+cellDescription);
		logger.info("existlist.size()-------------------"+existlist.size());
		for(int i=0;i<existlist.size();i++){
			UserDesignation userdes = existlist.get(i);   
			logger.info("userdes.getCompanyid--------------------"+userdes.getCompanyid());
			logger.info("userdes.getDescription--------------------"+userdes.getDescription());
			logger.info("userdes.getDesignation--------------------"+userdes.getDesignation());

			if(!userdes.getDesignation().equalsIgnoreCase(cellDesignation)){
				try{            	
					vo.setDesignation(cellDesignation);
				}catch(Exception e){
					e.printStackTrace();
				}   	            

				try{                
					vo.setDescription(cellDescription);
				}catch(Exception e){
					e.printStackTrace();
				}
				vo.setCompanyid(profile.getCompanyid());
			}
			else
			{
				isDesignationexists = true;
			}


		}  
		logger.info("ExcelToXMLUtil addVoRowCells end");
		return isDesignationexists;
	}
	public static String addHeaderRowCells(Row row) {
		logger.info("ExcelToXMLUtil addHeaderRowCells start");
		int cellIndex = 0;
		String header = "";
		try{
			Cell myCell = row.getCell(cellIndex++);
			header = myCell.getStringCellValue();
			logger.info("header---------------"+header);
			myCell = row.getCell(cellIndex++);
			logger.info("myCell---------------"+myCell);

			header += "," + myCell.getStringCellValue();

		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("ExcelToXMLUtil addHeaderRowCells end");
		return header;
	}

}
class   UserDesigtnationPojo{
	private boolean isAlreadyExisted=false;
	private List<UserDesignation> newUserDesignationList=new ArrayList<>();
	public boolean isAlreadyExisted() {
		return isAlreadyExisted;
	}

	public void setAlreadyExisted(boolean isAlreadyExisted) {
		this.isAlreadyExisted = isAlreadyExisted;
	}

	public List<UserDesignation> getNewUserDesignationList() {
		return newUserDesignationList;
	}

	public void setNewUserDesignationList(List<UserDesignation> newUserDesignationList) {
		this.newUserDesignationList = newUserDesignationList;
	}

}
