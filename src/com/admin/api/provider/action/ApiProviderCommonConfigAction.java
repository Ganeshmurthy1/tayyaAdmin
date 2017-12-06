package com.admin.api.provider.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProviderBluestarConfig;
import com.admin.api.provider.model.ApiProviderCommonConfig;
import com.admin.api.provider.model.ApiProviderDesiyaConfig;
import com.admin.api.provider.model.ApiProviderEtravelSmartConfig;
import com.admin.api.provider.model.ApiProviderTboConfig;
import com.admin.insurance.model.ApiProviderTrawellTagConfig;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ApiProviderCommonConfigAction  extends ActionSupport implements ModelDriven<ApiProviderCommonConfig>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ApiProviderCommonConfig apiProviderCommonConfig=new ApiProviderCommonConfig();
	List<ApiProviderCommonConfig> apiProviderCommonConfigs=new ArrayList<>();
	SessionMap<String, Object> sessionMap=null;
	private boolean tboAllActive=true;
	private boolean bluestarAllActive=true;
	private boolean desiyaAllActive=true;
	private boolean etravelAllActive=true;
	private boolean trawellTagAllActive=true;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	List<ApiProviderTboConfig> apiProviderTboConfigs=new ArrayList<>();
	List<ApiProviderBluestarConfig> apiProviderBluestarConfigs=new ArrayList<>();
	List<ApiProviderDesiyaConfig> apiProviderDesiyaConfigs=new ArrayList<>();
	List<ApiProviderEtravelSmartConfig> apiProviderEtravelSmartConfigList=new ArrayList<>();
	List<ApiProviderTrawellTagConfig> apiProviderTrawellTagConfigList=new ArrayList<ApiProviderTrawellTagConfig>();
	private List<CompanyConfig> companyConfigsList=null;
	private String companyConfigIds;
	private String switchType;
	private boolean status=false;
	
	public String insertConfig(){
		Company  companySession=(Company)sessionMap.get("Company");
		String idsArray[]=null;
		if(companyConfigIds!=null && companyConfigIds.contains("/")){
			idsArray=companyConfigIds.split("/");
			apiProviderCommonConfig.setCompanyId(Integer.valueOf(idsArray[0]));
			apiProviderCommonConfig.setConfigId(Integer.valueOf(idsArray[1]));

		}


		//apiProviderCommonConfig.setCompanyId(companySession.getCompanyid());
		apiProviderCommonConfig.setCreatedAt(new Timestamp(new Date().getTime()));
		apiProviderCommonConfig.setActive(true);

		if(apiProviderCommonConfig.getTboHotelId()>0 && apiProviderCommonConfig.getTboFlightId()>0 && apiProviderCommonConfig.getTboHotelId()== apiProviderCommonConfig.getTboFlightId())
		{
			ApiProviderTboConfig  apiProviderTboConfig = apiProviderDao.getApiProviderTbo(apiProviderCommonConfig.getTboHotelId());
			apiProviderCommonConfig.setApiProviderTboConfigHotel(apiProviderTboConfig);
			apiProviderCommonConfig.setApiProviderTboConfigFlight(apiProviderTboConfig);

		}
		else{
			if(apiProviderCommonConfig.getTboHotelId()>0 )
			{
				ApiProviderTboConfig  apiProviderTboConfig = apiProviderDao.getApiProviderTbo(apiProviderCommonConfig.getTboHotelId());
				apiProviderCommonConfig.setApiProviderTboConfigHotel(apiProviderTboConfig);
			}
			if(apiProviderCommonConfig.getTboFlightId()>0)
			{
				ApiProviderTboConfig apiProviderTboConfig1=apiProviderDao.getApiProviderTbo(apiProviderCommonConfig.getTboFlightId());	
				apiProviderCommonConfig.setApiProviderTboConfigFlight(apiProviderTboConfig1);
			}
		}

		if(apiProviderCommonConfig.getBluestarFlightId()>0)
		{
			ApiProviderBluestarConfig apiProviderBluestarConfig =apiProviderDao.getApiProviderBluestar(apiProviderCommonConfig.getBluestarFlightId());	
			apiProviderCommonConfig.setApiProviderBluestarConfigFlight(apiProviderBluestarConfig);
		}


		if(apiProviderCommonConfig.getDesiyaHotelId()>0)
		{
			ApiProviderDesiyaConfig  apiProviderDesiyaConfig =apiProviderDao.getApiProviderDesiya(apiProviderCommonConfig.getDesiyaHotelId());
			apiProviderCommonConfig.setApiProviderDesiyaConfigHotel(apiProviderDesiyaConfig);
		}

		if(apiProviderCommonConfig.getEtravelBusId()>0)
		{
			ApiProviderEtravelSmartConfig  apiProviderEtravelConfig =apiProviderDao.getApiProviderEtravelSmart(apiProviderCommonConfig.getEtravelBusId());
			apiProviderCommonConfig.setApiProviderEtravelBusConfig(apiProviderEtravelConfig);
		}



		/*apiProviderBluestarConfig=apiProviderDao.getApiProviderBluestar(apiProviderCommonConfig.getId());
		apiProviderBluestarConfig=apiProviderDao.getApiProviderBluestar(apiProviderCommonConfig.getId());
		 */
		boolean isInserted= apiProviderDao.insertApiProviderCommonConfig(apiProviderCommonConfig); 
		if(isInserted)
			addActionMessage("Successfully Added.");
		else
			addActionError("We found some error.Please try again.");
		return SUCCESS;
	}
	public String addConfig(){
		apiProviderTboConfigs=apiProviderDao.fetchApiProviderListTbo();
		apiProviderBluestarConfigs=apiProviderDao.fetchApiProviderListBluestar();
		apiProviderDesiyaConfigs=apiProviderDao.fetchApiProviderListDesiya();
		apiProviderEtravelSmartConfigList=apiProviderDao.fetchApiProviderListEtravelSmart();
		apiProviderTrawellTagConfigList=apiProviderDao.fetchApiProviderListTrawellTag();
		List<CompanyConfig> companyConfigs=null;
		List<Integer> apiProviderCommonConfigIds=apiProviderDao.fetchCommonConfigConfigIds();
		if(apiProviderCommonConfigIds!=null && apiProviderCommonConfigIds.size()>0){
			 companyConfigs=new CompanyConfigDao().fetchAllCompanyConfigsByConfigIds(apiProviderCommonConfigIds);
			setCompanyConfigsList(companyConfigs);
		}
		else{
			 companyConfigs=new CompanyConfigDao().fetchAllCompanyConfigs();
			setCompanyConfigsList(companyConfigs);
		}
		return SUCCESS;
	}


	public String editCommonConfig(){
		apiProviderCommonConfig=apiProviderDao.getApiProviderCommonConfig(apiProviderCommonConfig.getId());
		apiProviderTboConfigs=apiProviderDao.fetchApiProviderListTbo();
		apiProviderBluestarConfigs=apiProviderDao.fetchApiProviderListBluestar();
		apiProviderDesiyaConfigs=apiProviderDao.fetchApiProviderListDesiya();
		apiProviderEtravelSmartConfigList=apiProviderDao.fetchApiProviderListEtravelSmart();
		apiProviderTrawellTagConfigList=apiProviderDao.fetchApiProviderListTrawellTag();
		return SUCCESS;
	}



	public String updateCommonConfigStatus(){
		ApiProviderCommonConfig apiProviderBluestarConfigNew=apiProviderDao.updateCommonConfigStatus(apiProviderCommonConfig,switchType,status);
		if(apiProviderBluestarConfigNew!=null &&(apiProviderBluestarConfigNew.getId()==apiProviderBluestarConfigNew.getId())) {
			addActionMessage("Successfully updated.");
		}
		else{
			addActionError("We found some error.Please try again.");
		}  
		return SUCCESS;
	}


	public String updateCommonConfig(){
		if(apiProviderCommonConfig.getTboHotelId()>0 && apiProviderCommonConfig.getTboFlightId()>0 && apiProviderCommonConfig.getTboHotelId()== apiProviderCommonConfig.getTboFlightId())
		{
			ApiProviderTboConfig  apiProviderTboConfig = apiProviderDao.getApiProviderTbo(apiProviderCommonConfig.getTboHotelId());
			apiProviderCommonConfig.setApiProviderTboConfigHotel(apiProviderTboConfig);
			apiProviderCommonConfig.setApiProviderTboConfigFlight(apiProviderTboConfig);
		}
		else{
			if(apiProviderCommonConfig.getTboHotelId()>0)
			{
				ApiProviderTboConfig  apiProviderTboConfig = apiProviderDao.getApiProviderTbo(apiProviderCommonConfig.getTboHotelId());
				apiProviderCommonConfig.setApiProviderTboConfigHotel(apiProviderTboConfig);
			}
			if(apiProviderCommonConfig.getTboFlightId()>0)
			{
				ApiProviderTboConfig apiProviderTboConfig1=apiProviderDao.getApiProviderTbo(apiProviderCommonConfig.getTboFlightId());	
				apiProviderCommonConfig.setApiProviderTboConfigFlight(apiProviderTboConfig1);
			}
		}

		if(apiProviderCommonConfig.getBluestarFlightId()>0)
		{
			ApiProviderBluestarConfig apiProviderBluestarConfig =apiProviderDao.getApiProviderBluestar(apiProviderCommonConfig.getBluestarFlightId());	
			apiProviderCommonConfig.setApiProviderBluestarConfigFlight(apiProviderBluestarConfig);
		}


		if(apiProviderCommonConfig.getDesiyaHotelId()>0)
		{
			ApiProviderDesiyaConfig  apiProviderDesiyaConfig =apiProviderDao.getApiProviderDesiya(apiProviderCommonConfig.getDesiyaHotelId());
			apiProviderCommonConfig.setApiProviderDesiyaConfigHotel(apiProviderDesiyaConfig);
		}
		if(apiProviderCommonConfig.getEtravelBusId()>0)
		{
			ApiProviderEtravelSmartConfig  apiProviderEtravelSmartConfig =apiProviderDao.getApiProviderEtravelSmart(apiProviderCommonConfig.getEtravelBusId());
			apiProviderCommonConfig.setApiProviderEtravelBusConfig(apiProviderEtravelSmartConfig);
		}
		if(apiProviderCommonConfig.getTrawellTagInsuranceId()>0)
		{
			ApiProviderTrawellTagConfig  apiProviderTrawellTagConfig =apiProviderDao.getApiProviderTrawellTag(apiProviderCommonConfig.getTrawellTagInsuranceId());
			apiProviderCommonConfig.setApiProviderTrawellTagInsuranceConfig(apiProviderTrawellTagConfig);
		}
		ApiProviderCommonConfig apiProviderBluestarConfigNew=apiProviderDao.updateApiProviderCommonConfig(apiProviderCommonConfig);
		if(apiProviderBluestarConfigNew!=null &&(apiProviderBluestarConfigNew.getId()==apiProviderBluestarConfigNew.getId())) {
			addActionMessage("Successfully updated.");
		}
		else{
			addActionError("We found some error.Please try again.");
		}  
		return SUCCESS;
	}
	public String listConfig(){
		apiProviderCommonConfigs = apiProviderDao.fetchApiProviderListCommonConfig();
		if(apiProviderCommonConfigs!=null && apiProviderCommonConfigs.size()>0)
			for(ApiProviderCommonConfig commonConfig:apiProviderCommonConfigs){
				if(!commonConfig.isTboActive())
					setTboAllActive(false);
				if(!commonConfig.isBluestarActive())
					setBluestarAllActive(false);
				if(!commonConfig.isEtravelActive())
					setEtravelAllActive(false);
				if(!commonConfig.isTrawellTagActive())
					setTrawellTagAllActive(false);
				if(!commonConfig.isDesiyaActive())
					setDesiyaAllActive(false);
				
			}
		return SUCCESS;
	}

	public String deleteCommonConfig(){
		apiProviderDao.deletecommonConfig(apiProviderCommonConfig.getId());
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
	public ApiProviderCommonConfig getApiProviderCommonConfig() {
		return apiProviderCommonConfig;
	}
	public void setApiProviderCommonConfig(ApiProviderCommonConfig apiProviderCommonConfig) {
		this.apiProviderCommonConfig = apiProviderCommonConfig;
	}
	public List<ApiProviderCommonConfig> getApiProviderCommonConfigs() {
		return apiProviderCommonConfigs;
	}
	public void setApiProviderCommonConfigs(List<ApiProviderCommonConfig> apiProviderCommonConfigs) {
		this.apiProviderCommonConfigs = apiProviderCommonConfigs;
	}
	public List<ApiProviderTboConfig> getApiProviderTboConfigs() {
		return apiProviderTboConfigs;
	}
	public void setApiProviderTboConfigs(List<ApiProviderTboConfig> apiProviderTboConfigs) {
		this.apiProviderTboConfigs = apiProviderTboConfigs;
	}
	public List<ApiProviderBluestarConfig> getApiProviderBluestarConfigs() {
		return apiProviderBluestarConfigs;
	}
	public void setApiProviderBluestarConfigs(List<ApiProviderBluestarConfig> apiProviderBluestarConfigs) {
		this.apiProviderBluestarConfigs = apiProviderBluestarConfigs;
	}
	public List<ApiProviderDesiyaConfig> getApiProviderDesiyaConfigs() {
		return apiProviderDesiyaConfigs;
	}
	public void setApiProviderDesiyaConfigs(List<ApiProviderDesiyaConfig> apiProviderDesiyaConfigs) {
		this.apiProviderDesiyaConfigs = apiProviderDesiyaConfigs;
	}
	public String getSwitchType() {
		return switchType;
	}
	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<CompanyConfig> getCompanyConfigsList() {
		return companyConfigsList;
	}
	public void setCompanyConfigsList(List<CompanyConfig> companyConfigsList) {
		this.companyConfigsList = companyConfigsList;
	}
	public String getCompanyConfigIds() {
		return companyConfigIds;
	}
	public void setCompanyConfigIds(String companyConfigIds) {
		this.companyConfigIds = companyConfigIds;
	}
	public List<ApiProviderEtravelSmartConfig> getApiProviderEtravelSmartConfigList() {
		return apiProviderEtravelSmartConfigList;
	}
	public void setApiProviderEtravelSmartConfigList(
			List<ApiProviderEtravelSmartConfig> apiProviderEtravelSmartConfigList) {
		this.apiProviderEtravelSmartConfigList = apiProviderEtravelSmartConfigList;
	}
	public List<ApiProviderTrawellTagConfig> getApiProviderTrawellTagConfigList() {
		return apiProviderTrawellTagConfigList;
	}
	public void setApiProviderTrawellTagConfigList(List<ApiProviderTrawellTagConfig> apiProviderTrawellTagConfigList) {
		this.apiProviderTrawellTagConfigList = apiProviderTrawellTagConfigList;
	}
	public boolean isTboAllActive() {
		return tboAllActive;
	}
	public boolean isBluestarAllActive() {
		return bluestarAllActive;
	}
	public boolean isDesiyaAllActive() {
		return desiyaAllActive;
	}
	public boolean isEtravelAllActive() {
		return etravelAllActive;
	}
	public boolean isTrawellTagAllActive() {
		return trawellTagAllActive;
	}
	public void setTboAllActive(boolean tboAllActive) {
		this.tboAllActive = tboAllActive;
	}
	public void setBluestarAllActive(boolean bluestarAllActive) {
		this.bluestarAllActive = bluestarAllActive;
	}
	public void setDesiyaAllActive(boolean desiyaAllActive) {
		this.desiyaAllActive = desiyaAllActive;
	}
	public void setEtravelAllActive(boolean etravelAllActive) {
		this.etravelAllActive = etravelAllActive;
	}
	public void setTrawellTagAllActive(boolean trawellTagAllActive) {
		this.trawellTagAllActive = trawellTagAllActive;
	}


}
