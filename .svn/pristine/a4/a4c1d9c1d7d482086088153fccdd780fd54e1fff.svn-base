package com.admin.api.provider.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProviderTboConfig;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ApiProviderTboAction  extends ActionSupport implements ModelDriven<ApiProviderTboConfig>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ApiProviderTboConfig apiProviderTboConfig=new ApiProviderTboConfig();
	List<ApiProviderTboConfig> apiProviderTboConfigs=new ArrayList<>();
	SessionMap<String, Object> sessionMap=null;
	ApiProviderDao apiProviderDao = new ApiProviderDao();

	public String insertTboConfig(){
		//			User sessionUser=(User)sessionMap.get("User");
		Company  companySession = (Company)sessionMap.get("Company");
		apiProviderTboConfig.setCompanyId(companySession.getCompanyid());
		apiProviderTboConfig.setCreatedAt(new Timestamp(new Date().getTime()));
		
		boolean isInserted= apiProviderDao.insertApiProviderTbo(apiProviderTboConfig); 
		if(isInserted)
			addActionMessage("Successfully Added.");
		else
			addActionError("We found some error.Please try again.");
		return SUCCESS;
	}
	
	
	 
	
	
	
	public String addTboConfig(){
		return SUCCESS;
	}
	public String editTboConfig(){
		apiProviderTboConfig=apiProviderDao.getApiProviderTbo(apiProviderTboConfig.getId());
		return SUCCESS;
	}
	public String updateTboConfig(){
		ApiProviderTboConfig apiProviderTboConfigNew=apiProviderDao.updateApiProviderTBO(apiProviderTboConfig);
		if(apiProviderTboConfigNew!=null) {
			addActionMessage("Successfully updated.");
		}
		else{
			addActionError("We found some error.Please try again.");
		}
		return SUCCESS;
	}
	public String listTboConfig(){
		apiProviderTboConfigs = apiProviderDao.fetchApiProviderListTbo();
		return SUCCESS;
	}
	public String deleteTboConfig(){
		 apiProviderDao.deleteTboConfig(apiProviderTboConfig.getId());
		return SUCCESS;
	}

	public String updateTboStatus(){
		 apiProviderDao.updateTboStatus(apiProviderTboConfig);
			return SUCCESS;
		}


	public ApiProviderTboConfig getApiProviderTboConfig() {
		return apiProviderTboConfig;
	}

	public void setApiProviderTboConfig(ApiProviderTboConfig apiProviderTboConfig) {
		this.apiProviderTboConfig = apiProviderTboConfig;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public ApiProviderTboConfig getModel() {
		// TODO Auto-generated method stub
		return apiProviderTboConfig;
	}
	public List<ApiProviderTboConfig> getApiProviderTboConfigs() {
		return apiProviderTboConfigs;
	}
	public void setApiProviderTboConfigs(List<ApiProviderTboConfig> apiProviderTboConfigs) {
		this.apiProviderTboConfigs = apiProviderTboConfigs;
	}

}
