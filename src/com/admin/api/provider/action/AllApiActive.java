/**
 * 
 */
package com.admin.api.provider.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProviderBluestarConfig;
import com.admin.api.provider.model.ApiProviderCommonConfig;
import com.admin.api.provider.model.ApiProviderDesiyaConfig;
import com.admin.api.provider.model.ApiProviderEtravelSmartConfig;
import com.admin.api.provider.model.ApiProviderTboConfig;
import com.admin.insurance.model.ApiProviderTrawellTagConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author      : Manish Samrat
 * @createdAt   : 30/06/2017
 * @version
 */
public class AllApiActive extends ActionSupport implements ModelDriven<ApiProviderCommonConfig>,SessionAware{

	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap=null;
	ApiProviderCommonConfig apiProviderCommonConfig=new ApiProviderCommonConfig();
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	public String switchType;
	public Boolean status;
	public String apiName;
	
	public String activeAllApiActiveStatusByAPIName(){
		switch(apiName){
		case "tbo":
			activeAllTbo();
			break;
		case "bluestar":
			activeAllBluestar();
			break;
		case "desiya":
			activeAllDesia();
			break;
		case "etravel":
			activeAllEtravel();
			break;
		case "trawelltag":
			activeAllTrawelltag();
			break;
		default:return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String activeAllTbo(){
		List<ApiProviderTboConfig> apiProviderTboConfigList=apiProviderDao.fetchApiProviderListTbo();
		if(apiProviderTboConfigList.size()>0){
			for(ApiProviderTboConfig tboConfig:apiProviderTboConfigList){
				
				if(tboConfig.getEnvironment().equalsIgnoreCase("live") && tboConfig.isActive()){
					List<ApiProviderCommonConfig> apiProviderCommonConfigs = apiProviderDao.fetchApiProviderListCommonConfig();
					for(ApiProviderCommonConfig commonConfig:apiProviderCommonConfigs){
						apiProviderDao.updateCommonConfigStatus(commonConfig, switchType, status);
					}
				}
			}
		}
		return SUCCESS;
	}
	public String activeAllBluestar(){
		List<ApiProviderBluestarConfig> apiProviderBluestarConfigList=apiProviderDao.fetchApiProviderListBluestar();
		if(apiProviderBluestarConfigList.size()>0){
			for(ApiProviderBluestarConfig config:apiProviderBluestarConfigList){
				
				if(config.getEnvironment().equalsIgnoreCase("live") && config.isActive()){
					List<ApiProviderCommonConfig> apiProviderCommonConfigs = apiProviderDao.fetchApiProviderListCommonConfig();
					for(ApiProviderCommonConfig commonConfig:apiProviderCommonConfigs){
						apiProviderDao.updateCommonConfigStatus(commonConfig, switchType, status);
					}
				}
			}
		}
		return SUCCESS;
	}
	public String activeAllDesia(){
		List<ApiProviderDesiyaConfig> apiProviderDesiaConfigList=apiProviderDao.fetchApiProviderListDesiya();
		if(apiProviderDesiaConfigList.size()>0){
			for(ApiProviderDesiyaConfig config:apiProviderDesiaConfigList){
				
				if(config.getEnvironment().equalsIgnoreCase("live") && config.isActive()){
					List<ApiProviderCommonConfig> apiProviderCommonConfigs = apiProviderDao.fetchApiProviderListCommonConfig();
					for(ApiProviderCommonConfig commonConfig:apiProviderCommonConfigs){
						apiProviderDao.updateCommonConfigStatus(commonConfig, switchType, status);
					}
				}
			}
		}
		return SUCCESS;
	}
	public String activeAllEtravel(){
		List<ApiProviderEtravelSmartConfig> apiProviderEtravelSmartConfigs=apiProviderDao.fetchApiProviderListEtravelSmart();
		if(apiProviderEtravelSmartConfigs.size()>0){
			for(ApiProviderEtravelSmartConfig tboConfig:apiProviderEtravelSmartConfigs){
				
				if(tboConfig.getEnvironment().equalsIgnoreCase("live") && tboConfig.getActive()){
					List<ApiProviderCommonConfig> apiProviderCommonConfigs = apiProviderDao.fetchApiProviderListCommonConfig();
					for(ApiProviderCommonConfig commonConfig:apiProviderCommonConfigs){
						apiProviderDao.updateCommonConfigStatus(commonConfig, switchType, status);
					}
				}
			}
		}
		return SUCCESS;
	}
	public String activeAllTrawelltag(){
		List<ApiProviderTrawellTagConfig> apiProviderTrawellTagConfigs=apiProviderDao.fetchApiProviderListTrawellTag();
		if(apiProviderTrawellTagConfigs.size()>0){
			for(ApiProviderTrawellTagConfig config:apiProviderTrawellTagConfigs){
				
				if(config.getEnvironment().equalsIgnoreCase("live") && config.getActive()){
					List<ApiProviderCommonConfig> apiProviderCommonConfigs = apiProviderDao.fetchApiProviderListCommonConfig();
					for(ApiProviderCommonConfig commonConfig:apiProviderCommonConfigs){
						apiProviderDao.updateCommonConfigStatus(commonConfig, switchType, status);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public ApiProviderCommonConfig getModel() {
		// TODO Auto-generated method stub
		return apiProviderCommonConfig;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getSwitchType() {
		return switchType;
	}

	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public ApiProviderCommonConfig getApiProviderCommonConfig() {
		return apiProviderCommonConfig;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public void setApiProviderCommonConfig(ApiProviderCommonConfig apiProviderCommonConfig) {
		this.apiProviderCommonConfig = apiProviderCommonConfig;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
}
