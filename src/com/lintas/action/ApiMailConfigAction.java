package com.lintas.action;


import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.MailConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.MailConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ApiMailConfigAction extends ActionSupport implements ModelDriven<MailConfig>,SessionAware{
	/**@author info name:raham
	 * created date:27-07-2015
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ApiMailConfigAction.class);
	MailConfig mcad = new MailConfig();
	MailConfigDao mcadDao=new MailConfigDao();
	CompanyDAO mcDao=new CompanyDAO();
	private List<Company> apiCompanies =new ArrayList<>();
	private InputStream inputStream;
	private String  apiCompany ;
	
	SessionMap<String, Object> sessionmap ;
	public String addMailConfigData(){
		 logger.info("ApiCompany---------"+getApiCompany());
		 
		 
		 
		/*try {
			if(!mcDao.isMailHostExists(mcad)){
			 mcad.setCompany_id(company.getCompanyid()); 
			mcDao.insertMailConfigData(mcad);
				addActionMessage("Successfully Added.");
				return SUCCESS;
			}
			else{
				addActionMessage("Already existed.");
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 logger.error("------exception------"+e.getMessage());

			addActionError("Failed.Try again");
			return ERROR;
	}*/
		return SUCCESS;
	}
	public String goMailConfigPage(){
		Company company=(Company)sessionmap.get("Company");
		apiCompanies=mcadDao.loadApiCompanies(company);
		return SUCCESS;
	}




	public String getMailConfigProfile(){
		MailConfig getProfile=mcadDao.getMailConfigDetails(mcad);
		sessionmap.put("MailconfigProfile",getProfile);
		return SUCCESS;

	}



	/*public String updateMailConfig() {
		MailConfigAccessData getProfile= (MailConfigAccessData)sessionmap.get("MailconfigProfile");
		mcad.setMail_config_id(getProfile.getMail_config_id());
		boolean updated = mcDao.updateMailconfig(mcad);
		if(updated)
		{  addActionMessage("Successfully Updated.");
			 //showSuccessMessage("updated");
			return SUCCESS;
		}
		else
		{
			addActionError("Failed.Try again!");
			 //showSuccessMessage("failed");
			 return ERROR;
		}
	}*/


	/*	public String deleteMailConfigData(){
		 logger.info("----------Mail_config_id...."+mcad.getMail_config_id());


		try {
			  boolean isDelete=mcDao.deleteMailConfigData(mcad);
				if(isDelete){
					 showSuccessMessage("deleted");
					return SUCCESS;
				}

			else{
				 showSuccessMessage("failed");
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 logger.error("----------Exception-------------"+e.getMessage());
			showSuccessMessage("failed");
			return ERROR;
		}


	}*/


	/*public String getMailConfigList(){
		 List<MailConfigAccessData>  config_li= mcDao.getMailConfigList();
		 sessionmap.put("mailConfigList", config_li);
		 return SUCCESS;

	  }*/


	@Override
	public MailConfig getModel() {
		// TODO Auto-generated method stub
		return mcad;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionmap = (SessionMap<String, Object>) arg0;

	}

	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}
	public List<Company> getApiCompanies() {
		return apiCompanies;
	}
	public void setApiCompanies(List<Company> apiCompanies) {
		this.apiCompanies = apiCompanies;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public String getApiCompany() {
		return apiCompany;
	}
	public void setApiCompany(String apiCompany) {
		this.apiCompany = apiCompany;
	}

}
