/**
 * 
 */
package com.admin.insurance.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProviderEtravelSmartConfig;
import com.admin.insurance.model.ApiProviderTrawellTagConfig;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author info : Manish Samrat
 * @createdAt : 07/06/2017
 * @version : 2.0
 */
public class ApiProviderTrawellTagAction extends ActionSupport implements ModelDriven<ApiProviderTrawellTagConfig>, SessionAware{


	private static final long serialVersionUID = 1L;

	ApiProviderTrawellTagConfig apiProviderTrawellTagConfig=new ApiProviderTrawellTagConfig();
	SessionMap<String, Object> sessionMap=null;
	List<ApiProviderTrawellTagConfig> apiProviderTrawellTagConfigsList=new ArrayList<ApiProviderTrawellTagConfig>();
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	
	public String insertTrawellTagConfig(){
		Company  companySession = (Company)sessionMap.get("Company");
		apiProviderTrawellTagConfig.setCompanyId(companySession.getCompanyid());
		apiProviderTrawellTagConfig.setCreatedAt(new Timestamp(new Date().getTime()));
		
		boolean isInserted= apiProviderDao.insertApiProviderTrawellTag(apiProviderTrawellTagConfig); 
		if(isInserted)
			addActionMessage("Successfully Added.");
		else
			addActionError("We found some error.Please try again.");
		return SUCCESS;
	}
	
	public String addTrawellTagConfig(){
		return SUCCESS;
	}

	public String listTrawellTagConfig(){
		apiProviderTrawellTagConfigsList = apiProviderDao.fetchApiProviderListTrawellTag();
		return SUCCESS;
	}
	public String editTrawellTagConfig(){
		apiProviderTrawellTagConfig=apiProviderDao.getApiProviderTrawellTag(apiProviderTrawellTagConfig.getId());
		return SUCCESS;
	}
	public String updateTrawellTagStatus(){
		 apiProviderDao.updateTrawellTag(apiProviderTrawellTagConfig);
		return SUCCESS;
	}
	
	public String updateTrawellTagConfig(){
		ApiProviderTrawellTagConfig apiProviderTrawellTagConfigNew=apiProviderDao.updateApiProviderTrawellTag(apiProviderTrawellTagConfig);
		if(apiProviderTrawellTagConfigNew!=null) {
			addActionMessage("Successfully updated.");
		}
		else{
			addActionError("We found some error.Please try again.");
		}
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public ApiProviderTrawellTagConfig getModel() {
		return apiProviderTrawellTagConfig;
	}

	public ApiProviderTrawellTagConfig getApiProviderTrawellTagConfig() {
		return apiProviderTrawellTagConfig;
	}

	public List<ApiProviderTrawellTagConfig> getApiProviderTrawellTagConfigsList() {
		return apiProviderTrawellTagConfigsList;
	}

	public void setApiProviderTrawellTagConfig(ApiProviderTrawellTagConfig apiProviderTrawellTagConfig) {
		this.apiProviderTrawellTagConfig = apiProviderTrawellTagConfig;
	}

	public void setApiProviderTrawellTagConfigsList(List<ApiProviderTrawellTagConfig> apiProviderTrawellTagConfigsList) {
		this.apiProviderTrawellTagConfigsList = apiProviderTrawellTagConfigsList;
	}
	
}
