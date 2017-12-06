package com.lintas.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserDesignation;
import com.lintas.admin.model.UserDesignationRole;
import com.lintas.admin.model.UserRole;
import com.lintas.admin.model.UserWallet;
import com.opensymphony.xwork2.ActionContext;

public class EmployeeExceltoDBUtil {

	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EmployeeExceltoDBUtil.class);
	public static boolean getFileDataFromExcel(File file) {
		boolean isUploaded = false;
		InputStream inputStream = null;
		User user = new User();
		try {
			inputStream = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			log.info("File not found in the specified path.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("inputStream" +inputStream);
		try {
			// XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);
			org.apache.poi.ss.usermodel.Sheet mySheet = workbook.getSheetAt(0);
			// XSSFSheet mySheet = workBook.getSheetAt(0);
			int totalRows = mySheet.getPhysicalNumberOfRows();
			log.info("total no of rows >>>>"+totalRows);
			String headers = null;
			boolean isFirstRow = true;
			for(Row myRow : mySheet){
				user = new User();         
				if (isFirstRow) {
					headers = addHeaderRowCells(myRow);
					System.out.println("header" +headers);
					user.setHeaders(headers);
				} else {
					boolean isexists = addVoRowCells(user, myRow);
					if(!isexists)
					{
						//DAO.SaveandUpdateUserDesignation(userdesignation);
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
		log.info("ExcelToXMLUtil getXMLFileDataFromExcel end" );
		return isUploaded;
	}
	
	public static boolean addVoRowCells(User user, Row row) {
		log.info("ExcelToXMLUtil addVoRowCells start");
		int cellIndex = 0;
		boolean isuserexists = false;
		String email = "";
		String Username ;
		String Firstname  = "";
		String Lastname  = "";
		String Userdesignation  = "";	      
		String userroletype = "";
		String password = "";
		String Status ;
		String Address = "";
		String PassportNumber = "";
		int passport_expirydate ;
		int passport_issuedate ;
		String passport_issueplace = "";
		String dateofbirth ;
		String Countryname = "";
		String Language = "";
		String City = "";
		int Mobile ;
		String Description = "";
		boolean isstatus = false ;

		UserDAO DAO = new UserDAO();
		CompanyDAO cDAO = new CompanyDAO();
		Company currentcompany = (Company) ActionContext.getContext().getSession().get("Company");    	
		User userSessionObj = (User)ActionContext.getContext().getSession().get("User");
		Cell myCell = row.getCell(cellIndex++);
		email = myCell.getStringCellValue();       
		myCell = row.getCell(cellIndex++);
		Username = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		Firstname = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		Lastname = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		Userdesignation = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		userroletype = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		password = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		Status = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		Address = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		PassportNumber = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		passport_expirydate = (int) myCell.getNumericCellValue();
		myCell = row.getCell(cellIndex++);
		passport_issuedate = (int) myCell.getNumericCellValue();
		myCell = row.getCell(cellIndex++);
		passport_issueplace = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		dateofbirth =  myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		Countryname = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		Language = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		City = myCell.getStringCellValue();
		myCell = row.getCell(cellIndex++);
		Mobile = (int) myCell.getNumericCellValue();
		myCell = row.getCell(cellIndex++);
		Description = myCell.getStringCellValue();
		if(Status.equalsIgnoreCase("Active")){
			isstatus = true;	
		}


		try
		{

			if(!DAO.UserIdExists(email)){	
				user.setUsername(Username);
				user.setAddress(Address);
				user.setCity(City);
				user.setCountryname(Countryname);
				user.setStatus(isstatus);
				user.setDescription(Description);
				user.setDateofbirth(dateofbirth);
				user.setFirstname(Firstname);
				user.setLanguage(Language);
				user.setLastname(Lastname);
				user.setMobile(Integer.toString(Mobile));
				user.setPassport_expirydate(Integer.toString(passport_expirydate));
				user.setPassport_issuedate(Integer.toString(passport_issuedate));
				user.setPassport_issueplace(passport_issueplace);
				user.setPassportno(PassportNumber);
				user.setPassword(password);		    		
				user.setEmail(email);
				user.setCreateddate(new Date());
				user.setModifieddate(new Date());
				user.setCompanyid(currentcompany.getCompanyid());        	            
				if(userSessionObj.getUserrole_id().isUsermode()){
					user.setCompany_userid(currentcompany.getCompany_userid());
				}
				else if(userSessionObj.getUserrole_id().isSuperuser()){
					user.setCompany_userid(currentcompany.getCompany_userid());
				}   
				UserRole user_role =new UserRole(); 
				UserWallet agentWallet = new UserWallet();
				UserDesignationRole user_designationrole = new UserDesignationRole();

				if(userroletype.contains("admin")){
					user_role.setAdmin(true);

				}
				else if(userroletype.contains("Reports")){
					user_role.setReports(true);

				}
				else if(userroletype.contains("order")){
					user_role.setOrder(true);

				}
				else if(userroletype.contains("cms")){
					user_role.setCms(true);

				}

				user.setCompany_userid(currentcompany.getCompany_userid());
				user.setUserrole_id(user_role);

				user.setLocked(false);
				user.setStatus(true);
				user.setCurrencyCode(currentcompany.getCurrencyCode());
				agentWallet.setWalletBalanceRange(new BigDecimal("0.00"));

				agentWallet.setWalletType("prepaid");
				agentWallet.setWalletbalance(new BigDecimal("0.00"));
				agentWallet.setCurrencyCode(currentcompany.getCurrencyCode());
				agentWallet.setCreatedAt(new Timestamp(new Date().getTime()));
				agentWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
				user.setAgentWallet(agentWallet);
				try{
					User userObj = DAO.RegisterUser(user, userSessionObj);
					log.info("-----After user register--------USER ID------------"+userObj.getId());
					if(userObj!=null){
						//userObj.setImagepath(uploadImageFile(userObj.getId()));	
						user_designationrole.setRoleid(user_role.getRoleid());
						user_designationrole.setDesignation(Userdesignation);
						cDAO.InsertUserDesignationRole(user_designationrole);
						//	cDAO.updateUserImagePath(userObj);
						cDAO.insertEmail(String.valueOf(userObj.getId()), 0, Email.EMAIL_TYPE_USER_REGISTRATION);



					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			else
			{
				isuserexists = true;
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}



		log.info("ExcelToXMLUtil addVoRowCells end");
		return isuserexists;
	}
	public static String addHeaderRowCells(Row row) {
		log.info("ExcelToXMLUtil addHeaderRowCells start");

		String header = "";
		int cellcount =  row.getPhysicalNumberOfCells();
		System.out.println("cellcount" +cellcount);

		try{
			Cell myCell0 = row.getCell(0);
			header +=  myCell0.getStringCellValue();
			for(int i = 1; i< cellcount;i++)
			{
				Cell myCell = row.getCell(i);
				header += "," + myCell.getStringCellValue();
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("ExcelToXMLUtil addHeaderRowCells end");
		return header;
	}

}
