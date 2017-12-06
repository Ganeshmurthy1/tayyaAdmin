package com.lintas.admin.json;

import java.util.HashMap;
import java.util.Map;

import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class CommonValidationJsonAction extends ActionSupport  {
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonValidationJsonAction.class);
	UserDAO userDAO = new UserDAO();
	private Map<String,String> statusMap=new HashMap<>();
	private String email=null;
	public String checkCompanyEmailExists(){
		User user=new User();
		user.setEmail(getEmail());
		Company company=userDAO.checkUserExistInCompany(user);
		if(company!=null) 
			statusMap.put("status", "success");
		else
			statusMap.put("status", "fail");
		  
		return SUCCESS;
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Map<String,String> getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(Map<String,String> statusMap) {
		this.statusMap = statusMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
