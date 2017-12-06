package com.admin.api.provider.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProviderDesiyaConfig;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ApiProviderDesiyaAction  extends ActionSupport implements ModelDriven<ApiProviderDesiyaConfig>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ApiProviderDesiyaConfig apiProviderDesiyaConfig=new ApiProviderDesiyaConfig();
	List<ApiProviderDesiyaConfig> apiProviderDesiyaConfigs=new ArrayList<>();
	SessionMap<String, Object> sessionMap=null;
	ApiProviderDao apiProviderDao = new ApiProviderDao();

	public String insertDesiyaConfig(){
		//		User sessionUser=(User)sessionMap.get("User");
		Company  companySession=(Company)sessionMap.get("Company");
		apiProviderDesiyaConfig.setCompanyId(companySession.getCompanyid());
		apiProviderDesiyaConfig.setCreatedAt(new Timestamp(new Date().getTime()));
		boolean isInserted= apiProviderDao.insertApiProviderDesiya(apiProviderDesiyaConfig); 
		if(isInserted)
			addActionMessage("Successfully Added.");
		else
			addActionError("We found some error.Please try again.");
		return SUCCESS;
	}
	public String addDesiyaConfig(){
		return SUCCESS;
	}
	public String editDesiyaConfig(){
		apiProviderDesiyaConfig=apiProviderDao.getApiProviderDesiya(apiProviderDesiyaConfig.getId());
		return SUCCESS;
	}
	public String updateDesiyaConfig(){
		ApiProviderDesiyaConfig apiProviderDesiyaConfigNew=apiProviderDao.updateApiProviderDesiya(apiProviderDesiyaConfig);
		if(apiProviderDesiyaConfigNew!=null &&(apiProviderDesiyaConfigNew.getId()==apiProviderDesiyaConfigNew.getId())) {
			addActionMessage("Successfully updated.");
		}
		else{
			addActionError("We found some error.Please try again.");
		}
		return SUCCESS;
	}
	public String listDesiyaConfig(){
		apiProviderDesiyaConfigs = apiProviderDao.fetchApiProviderListDesiya();
		return SUCCESS;
	}

	public String deleteDesiyaConfig(){
		 apiProviderDao.deleteDesiyaConfig(apiProviderDesiyaConfig.getId());
		return SUCCESS;
	}
	
	public String updateDesiyaStatus(){
		 apiProviderDao.updateDesiyaStatus(apiProviderDesiyaConfig);
			return SUCCESS;
		}

	public ApiProviderDesiyaConfig getApiProviderDesiyaConfig() {
		return apiProviderDesiyaConfig;
	}

	public void setApiProviderDesiyaConfig(ApiProviderDesiyaConfig apiProviderDesiyaConfig) {
		this.apiProviderDesiyaConfig = apiProviderDesiyaConfig;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public ApiProviderDesiyaConfig getModel() {
		// TODO Auto-generated method stub
		return apiProviderDesiyaConfig;
	}
	public List<ApiProviderDesiyaConfig> getApiProviderDesiyaConfigs() {
		return apiProviderDesiyaConfigs;
	}
	public void setApiProviderDesiyaConfigs(List<ApiProviderDesiyaConfig> apiProviderDesiyaConfigs) {
		this.apiProviderDesiyaConfigs = apiProviderDesiyaConfigs;
	}

}
