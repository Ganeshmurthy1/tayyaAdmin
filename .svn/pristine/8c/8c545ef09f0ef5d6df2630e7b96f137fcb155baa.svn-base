package com.lintas.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.MarkupDao;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.FlightMarkup;
import com.lintas.admin.model.User;
import com.lintas.utility.AirlineComparator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

public class MarkupAction  extends ActionSupport implements ModelDriven<FlightMarkup>,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MarkupAction.class);
	FlightMarkup cm=new FlightMarkup();
	MarkupDao mDao=new MarkupDao();
	 
	private Company c=null;
	private String configData; 
	private InputStream inputStream;
	private List<Airlinelist> allAirlineList;
	CountryDao countryDao=new CountryDao();
	SessionMap<String, Object> sessionmap ;
	private List<CompanyConfig> AgencyConfiglist;
	private List<CompanyConfig> companyConfigIdsList;
	private List<FlightMarkup> flightmarkupList;
	private FlightMarkup CurrentMarkupProfile;
	CountryDao cDAO = new CountryDao();
	private List<Country> CountryList ;
	/*inserting CompanyMarkup data in to DB */
	public String setCompanyMarkup(){
		try {
			logger.info("---------conifigData------------"+getConfigData());
			String[] parts=null;
			String companyId=null;
			String  config_Id=null;
			String  configName=null;
			String  configNumber=null;
			String  companyName=null;
			if(getConfigData().contains("/")){
				parts = getConfigData().split("/");
				companyId = parts[0];  
				logger.info("---------companyId------------"+companyId);
				config_Id = parts[1]; 
				logger.info("---------configId------------"+config_Id);
				configName = parts[2];  
				logger.info("---------configName------------"+configName);
				configNumber = parts[3]; 
				logger.info("---------configNumber------------"+configNumber);
				companyName=parts[4];
				logger.info("---------companyName------------"+companyName);

			}
			c=(Company)sessionmap.get("Company");
			cm.setCreatedByCompanyName(c.getCompanyname());
			User sessionUser=(User)sessionmap.get("User");
			cm.setCompanyId(Integer.parseInt(companyId));
			cm.setConfigId(Integer.parseInt(config_Id));
			cm.setConfig_number(configNumber);
			cm.setConfigname(configName);
			cm.setCompanyName(companyName);
			cm.setCreatedbyCompanyId(c.getCompanyid()); 
			cm.setCreatedbyUserId(sessionUser.getId());
			cm.setModifiedbyUserId(sessionUser.getId());
			cm.setCreatedDate(new Date());
			cm.setModifiedDate(cm.getCreatedDate());
			cm.getCountry(); 
			String dest=cm.getDestination();
			String ori=cm.getOrigin();
			String arrDate=cm.getArrvDate();
			String depDate=cm.getDeptDate();
			String promofireStartDate=cm.getPromofareStartDate();
			String promofireEndDate=cm.getPromofareEndDate();
			logger.info("ori------------"+ori);
			logger.info("dest------------"+dest);
			if(cm.getFareBaseCode()==null||cm.getFareBaseCode().equals("")){
				cm.setFareBaseCode("ALL");

			}
			if(promofireStartDate==null||promofireStartDate.equals("")){
				promofireStartDate="ALL";
				cm.setPromofareStartDate(promofireStartDate);
			}
			else{
				cm.setPromofareStartDate(promofireStartDate);
			}
			if(promofireEndDate==null||promofireEndDate.equals("")){
				promofireEndDate="ALL";
				cm.setPromofareEndDate(promofireEndDate);
			}
			else{
				cm.setPromofareEndDate(promofireEndDate);
			}
			if(arrDate==null||arrDate.equals("")){
				arrDate="ALL";
				cm.setArrvDate(arrDate);	
			}
			else{
				cm.setArrvDate(arrDate);	
			}
			if(depDate==null||depDate.equals("")){
				depDate="ALL";
				cm.setDeptDate(depDate);	
			}
			else{
				cm.setDeptDate(depDate);	
			}
			if(dest==null||dest.equals("")){
				dest="ALL";	
				cm.setDestination(dest);
			}
			else{
				cm.setDestination(splitDestandOriginAfterThreeCommas(dest).toString());
			}
			if(ori==null||ori.equals("")){
				ori="ALL";	
				cm.setOrigin(ori);
			}
			else{
				cm.setOrigin(splitDestandOriginAfterThreeCommas(ori).toString());

			}
			int result=mDao.insertMarkupDetails(cm);
			if(result>0){ 
				addActionMessage(getText("global.setCompanyMarkup"));
				new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(cm.getMarkupId()),"Flight markup created","Flight markup created",NotificationInventoryTypeEnum.FLIGHT_MARKUP.getId(),true,false,false,true,false,false);

			}
			else{
				addActionError(getText("global.setCompanyMarkupfailed"));

			}  

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("-----Exception-----"+e.getMessage());
			addActionError(getText("global.setCompanyMarkupfailederror"));
			//addActionError("Failed. Try again.");
			return ERROR;
		}
		return SUCCESS;
	}
	/*load All Company Markups data from to DB */
	public String showMarkupList(){
		Company sessionObj=(Company)sessionmap.get("Company");
		List<FlightMarkup> markupList=mDao.getMarkupList(sessionObj);
		logger.info("-----------markup list size1-------------"+markupList.size());
		if(markupList!=null && markupList.size()>0){
			logger.info("-----------markup list size2-------------"+markupList.size());
			flightmarkupList = markupList;
		}
		return SUCCESS;
	}
	/*delete CompanyMarkup data  using  MarkupId from  DB */
	public String deleteMarkupList(){
		logger.info("--------Delete getMarkupId....------"+cm.getMarkupId());
		// cm.setMarkupId(cm.getMarkupId());
		boolean isdelete= mDao.deleteMarkupDetails(cm.getMarkupId());
		if(isdelete){
			showSuccessMessage("deleted");
			return SUCCESS;
		}
		else{
			showSuccessMessage("failed");
			return ERROR;
		}
	}

	public String getCountryandlanguagelist()
	{
		CountryList  = cDAO.getCountryList();	
		List<Airlinelist> list=countryDao.getAirlineList();
		if(list!=null){
			Collections.sort(list, new AirlineComparator());
			setAllAirlineList(list);
		}
		return SUCCESS;
	}

	/*this method for passing   markupId fetching  compantyMarkup data*/
	public String getMarkupProfile(){
		logger.info("------getmarkup Id........"+cm.getMarkupId());
		CurrentMarkupProfile = mDao.getMarkupDetails(cm);
		if(CurrentMarkupProfile!=null){
			CompanyConfig compConfig=mDao.getCompanyUserIdByConfigId(CurrentMarkupProfile.getConfigId());
			if(compConfig!=null){
				CurrentMarkupProfile.setCompanyUserId(compConfig.getCompanyUserId());
			}
		}
		Company sessionObj=(Company)sessionmap.get("Company");
		List<FlightMarkup> markupList=mDao.getMarkupList(sessionObj);
		logger.info("-----------markup list size1-------------"+markupList.size());
		if(markupList!=null && markupList.size()>0){
			logger.info("-----------markup list size2-------------"+markupList.size());
			flightmarkupList = markupList;
		}
		getCountryandlanguagelist();
		return SUCCESS;
	}

	/*this method for passing   markupId update  compantyMarkup data*/
	public String updateCompanyMarkup() {
		User sessionUser=(User)sessionmap.get("User");
		CurrentMarkupProfile = mDao.getMarkupDetails(cm);		
		cm.setMarkupId(CurrentMarkupProfile.getMarkupId());
		cm.setCountry("ALL");
		 
		boolean updated = mDao.updateCompanyMarkup(cm);
		getCountryandlanguagelist();
		if(updated)
		{			
			addActionMessage(getText("globlal.updateHotelMarkupsuccess"));
			new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(cm.getMarkupId()),"Flight markup updated","Flight markup updated",NotificationInventoryTypeEnum.FLIGHT_MARKUP.getId(),true,false,false,true,false,false);
			return SUCCESS;
		}
		else
		{
			addActionError(getText("global.updateHotelmarkuperror"));
			return ERROR;
		} 
	}
	/*this method for  fetching  All parent company company config ids for set markup*/
	public String getCompanyConfigIds(){
		Company sessionObj=(Company)sessionmap.get("Company");
		List<Airlinelist> list=countryDao.getAirlineList();
		if(list!=null){
			Collections.sort(list, new AirlineComparator());
			setAllAirlineList(list);
		}
		List<Company> companyIds=mDao.getCompanyIds(sessionObj);
		if(companyIds!=null && companyIds.size()>0){
			companyConfigIdsList = mDao.getCompanyConfigIds(companyIds);
		}
		AgencyConfiglist = mDao.getAgencyCompanyConfigIds(sessionObj);
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ADD_FLIGHT_MARKUP, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  


		return SUCCESS;
	}
	@Override
	public FlightMarkup getModel() {
		// TODO Auto-generated method stub
		return cm;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}
	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}
	public String getConfigData() {
		return configData;
	}
	public void setConfigData(String configData) {
		this.configData = configData;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	public  StringBuilder splitDestandOriginAfterThreeCommas(String str) 
	{
		//logger.info("str--"+str);
		String[] parts = str.split(",");
		StringBuilder strList = new StringBuilder();
		if(parts.length>0){
			for(int x = 0; x < parts.length - 2; x = x+3) 
			{
				String tmpStr = parts[x] + "," + parts[x+1] + "," + parts[x+2];
				strList.append(tmpStr+";");
			}
		}

		return strList;
	}
	public List<Airlinelist> getAllAirlineList() {
		return allAirlineList;
	}
	public void setAllAirlineList(List<Airlinelist> allAirlineList) {
		this.allAirlineList = allAirlineList;
	}
	public List<CompanyConfig> getAgencyConfiglist() {
		return AgencyConfiglist;
	}


	public void setAgencyConfiglist(List<CompanyConfig> agencyConfiglist) {
		AgencyConfiglist = agencyConfiglist;
	}
	public List<CompanyConfig> getCompanyConfigIdsList() {
		return companyConfigIdsList;
	}


	public void setCompanyConfigIdsList(List<CompanyConfig> companyConfigIdsList) {
		this.companyConfigIdsList = companyConfigIdsList;
	}
	public List<FlightMarkup> getFlightmarkupList() {
		return flightmarkupList;
	}


	public void setFlightmarkupList(List<FlightMarkup> flightmarkupList) {
		this.flightmarkupList = flightmarkupList;
	}
	public FlightMarkup getCurrentMarkupProfile() {
		return CurrentMarkupProfile;
	}

	public void setCurrentMarkupProfile(FlightMarkup currentMarkupProfile) {
		CurrentMarkupProfile = currentMarkupProfile;
	}
	public List<Country> getCountryList() {

		return CountryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.CountryList = countryList;
	}	 

}
