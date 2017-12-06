package com.lintas.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.UserDesignation;
import com.lintas.utility.CompanyEmployeeBulkUploadUtil;
import com.lintas.utility.DesignationExcelToDBUtil;
import com.lintas.utility.EmployeeData;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CompanyEmployeeAction extends ActionSupport implements ModelDriven<UserDesignation>,SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyEmployeeAction.class);
	UserDesignation userdesignation = new UserDesignation();
	SessionMap<String, Object> sessionmap ;
	private File file;
	private File excelFile;
	private String contentType;
	private String filename;
	private List<UserDesignation> UserDesignationList;
	HashMap<String, EmployeeData> existedEmailMap = new HashMap<>();

	UserDAO DAO = new UserDAO();
	public String addcompanyempolyeesdesignation()
	{	
		Company profile = (Company)sessionmap.get("Company");
		userdesignation.setCompanyid(profile.getCompanyid());
		userdesignation = DAO.SaveandUpdateUserDesignation(userdesignation);
		if(userdesignation!=null){
			addActionMessage(getText("global.addcompanyempolyeesdesignationempsuccess"));
			return SUCCESS;	
		}		      
		else{
			addActionError(getText("global.addcompanyempolyeesdesignationerror"));
			//addActionError("Failed.");
			return ERROR;
		}

	}

	public String doExcelUpload() {
		logger.info("UploadAction doExcelUpload start");
		logger.info("*** " + file + "\t" + excelFile);
		logger.info("*** " + file + "\t" + excelFile.length());
		logger.info("filenames:");
		logger.info("*** " + filename);
		logger.info("content types:");
		logger.info("*** " + contentType);	            
		existedEmailMap = CompanyEmployeeBulkUploadUtil.getFileEmployeeDataFromExcel(excelFile);	
		
		String uploadStatus = "";
		if(existedEmailMap.size() == 0){
			uploadStatus = "Success";
		}else{
			uploadStatus = "We have Successfully updated your data, we got below errors, please rectify only below data and upload again."; 
		}
		if(uploadStatus.equalsIgnoreCase("Success"))
		{
			logger.info("UploadAction doExcelUpload end");
			addActionMessage("File uploaded and employee's are created successfully.");
			return SUCCESS;
		}
		else
		{
			logger.info("UploadAction doExcelUpload Failed");
			addActionError(uploadStatus);
			//addActionError(getText("global.addcompanyempolyeesdesignationuploaderror"));
			return ERROR;
		}
	}
	public String getDesignationList() {
		List<UserDesignation> list= DAO.getDesignationList();
		if(list!=null && list.size()>0){
			setUserDesignationList(list);
		}
		return SUCCESS;

	}


	public List<UserDesignation> getUserDesignationList() {
		return UserDesignationList;
	}
	public void setUserDesignationList(List<UserDesignation> userDesignationList) {
		UserDesignationList = userDesignationList;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionmap = (SessionMap<String, Object>) arg0;
	}
	@Override
	public UserDesignation getModel() {
		// TODO Auto-generated method stub
		return userdesignation;
	}
	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public HashMap<String, EmployeeData> getExistedEmailMap() {
		return existedEmailMap;
	}

	public void setExistedEmailMap(HashMap<String, EmployeeData> existedEmailMap) {
		this.existedEmailMap = existedEmailMap;
	}

	
}
