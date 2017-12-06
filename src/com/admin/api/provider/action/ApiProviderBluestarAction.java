package com.admin.api.provider.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProviderBluestarConfig;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ApiProviderBluestarAction  extends ActionSupport implements ModelDriven<ApiProviderBluestarConfig>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ApiProviderBluestarConfig apiProviderBluestarConfig=new ApiProviderBluestarConfig();
	List<ApiProviderBluestarConfig> apiProviderBluestarConfigs=new ArrayList<>();
	SessionMap<String, Object> sessionMap=null;
	ApiProviderDao apiProviderDao = new ApiProviderDao();

	public String insertBluestarConfig(){
		
		Company  companySession = (Company)sessionMap.get("Company");
		apiProviderBluestarConfig.setCompanyId(companySession.getCompanyid());
		apiProviderBluestarConfig.setCreatedAt(new Timestamp(new Date().getTime()));
		boolean isInserted= apiProviderDao.insertApiProviderBluestar(apiProviderBluestarConfig); 
		if(isInserted)
			addActionMessage("Successfully Added.");
		else
			addActionError("We found some error.Please try again.");
		return SUCCESS;
	}
	public String addBluestarConfig(){
		return SUCCESS;
	}

	public String editBluestarConfig(){
		apiProviderBluestarConfig=apiProviderDao.getApiProviderBluestar(apiProviderBluestarConfig.getId());
		return SUCCESS;
	}
	public String updateBluestarConfig(){
		ApiProviderBluestarConfig apiProviderBluestarConfigNew=apiProviderDao.updateApiProviderBluestar(apiProviderBluestarConfig);
		if(apiProviderBluestarConfigNew!=null &&(apiProviderBluestarConfigNew.getId()==apiProviderBluestarConfigNew.getId())) {
			addActionMessage("Successfully updated.");
		}
		else{
			addActionError("We found some error.Please try again.");
		}
		return SUCCESS;
	}
	public String listBluestarConfig(){
		apiProviderBluestarConfigs = apiProviderDao.fetchApiProviderListBluestar();
		return SUCCESS;
	}

	public String deleteBluestarConfig(){
	 apiProviderDao.deleteBluestarConfig(apiProviderBluestarConfig.getId());
		return SUCCESS;
	}
	
	public String updateBluestarStatus(){
		 apiProviderDao.updateBluestarStatus(apiProviderBluestarConfig);
			return SUCCESS;
		}


	public ApiProviderBluestarConfig getApiProviderBluestarConfig() {
		return apiProviderBluestarConfig;
	}

	public void setApiProviderBluestarConfig(ApiProviderBluestarConfig apiProviderBluestarConfig) {
		this.apiProviderBluestarConfig = apiProviderBluestarConfig;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public ApiProviderBluestarConfig getModel() {
		// TODO Auto-generated method stub
		return apiProviderBluestarConfig;
	}
	public List<ApiProviderBluestarConfig> getApiProviderBluestarConfigs() {
		return apiProviderBluestarConfigs;
	}
	public void setApiProviderBluestarConfigs(List<ApiProviderBluestarConfig> apiProviderBluestarConfigs) {
		this.apiProviderBluestarConfigs = apiProviderBluestarConfigs;
	}

}
