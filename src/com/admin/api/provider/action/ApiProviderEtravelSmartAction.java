package com.admin.api.provider.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProviderEtravelSmartConfig;
import com.admin.api.provider.model.ApiProviderTboConfig;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author info name:Manish Kumar
 * @createdAt date:19-05-2017
 */
public class ApiProviderEtravelSmartAction extends ActionSupport implements ModelDriven<ApiProviderEtravelSmartConfig>, SessionAware {
	
	private static final long serialVersionUID = 1L;
	ApiProviderEtravelSmartConfig apiProviderEtravelSmartConfig=new ApiProviderEtravelSmartConfig();
	SessionMap<String, Object> sessionMap=null;
	List<ApiProviderEtravelSmartConfig> apiProviderEtravelSmartConfigsList=new ArrayList<>();
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	
	public String insertEtravelSmartConfig(){
		Company  companySession = (Company)sessionMap.get("Company");
		apiProviderEtravelSmartConfig.setCompanyId(companySession.getCompanyid());
		apiProviderEtravelSmartConfig.setCreatedAt(new Timestamp(new Date().getTime()));
		
		boolean isInserted= apiProviderDao.insertApiProviderEtravelSmart(apiProviderEtravelSmartConfig); 
		if(isInserted)
			addActionMessage("Successfully Added.");
		else
			addActionError("We found some error.Please try again.");
		return SUCCESS;
	}
	public String listEtravelSmartConfig(){
		apiProviderEtravelSmartConfigsList = apiProviderDao.fetchApiProviderListEtravelSmart();
		return SUCCESS;
	}
	
	public String addEtravelSmartConfig(){
		return SUCCESS;
	}
	public String editEtravelSmartConfig(){
		apiProviderEtravelSmartConfig=apiProviderDao.getApiProviderEtravelSmart(apiProviderEtravelSmartConfig.getId());
		return SUCCESS;
	}
	public String updateEtravelSmartConfig(){
		ApiProviderEtravelSmartConfig apiProviderEtravelSmartConfigNew=apiProviderDao.updateApiProviderEtravelSmart(apiProviderEtravelSmartConfig);
		if(apiProviderEtravelSmartConfigNew!=null) {
			addActionMessage("Successfully updated.");
		}
		else{
			addActionError("We found some error.Please try again.");
		}
		return SUCCESS;
	}
	
	public String updateEtravelStatus(){
		 apiProviderDao.updateEtravelStatus(apiProviderEtravelSmartConfig);
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;
	}
	
	@Override
	public ApiProviderEtravelSmartConfig getModel() {
		return apiProviderEtravelSmartConfig;
	}
	
	public ApiProviderEtravelSmartConfig getApiProviderEtravelSmartConfig() {
		return apiProviderEtravelSmartConfig;
	}
	
	public void setApiProviderEtravelSmartConfig(ApiProviderEtravelSmartConfig apiProviderEtravelSmartConfig) {
		this.apiProviderEtravelSmartConfig = apiProviderEtravelSmartConfig;
	}
	public List<ApiProviderEtravelSmartConfig> getApiProviderEtravelSmartConfigsList() {
		return apiProviderEtravelSmartConfigsList;
	}
	public void setApiProviderEtravelSmartConfigsList(
			List<ApiProviderEtravelSmartConfig> apiProviderEtravelSmartConfigsList) {
		this.apiProviderEtravelSmartConfigsList = apiProviderEtravelSmartConfigsList;
	}
	
}