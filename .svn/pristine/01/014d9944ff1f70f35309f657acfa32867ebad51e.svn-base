package com.lintas.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.HotelMarkupDao;
import com.lintas.admin.DAO.MarkupDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.HotelMarkup;
import com.lintas.admin.model.User;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

@SuppressWarnings("deprecation")
public class HotelMarkupAction1 extends ActionSupport implements ModelDriven<HotelMarkup>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelMarkupAction1.class);
	HotelMarkup hm=new HotelMarkup();
	HotelMarkupDao hmDAO=new HotelMarkupDao();
	SessionMap<String, Object> sessionmap ;
	List<HotelMarkup> li;
	private String configData; 
	private String checkInDate;
	private String checkOutDate;
	private CompanyConfig cc=null;
	private Company c=null;
	private List<CompanyConfig> AgencyConfiglist;
	private List<CompanyConfig> companyConfigIdsList;
	CountryDao cDAO = new CountryDao();
	private List<Country> CountryList ;
	private InputStream inputStream;
	private List<HotelMarkup> AllHotelMatrkup;
	private HotelMarkup CurrentProfile;
	private static DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

	/*this method for inserting hotel markup data*/
	public String addMarkupDetails(){
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
			companyName= parts[4];
			logger.info("---------companyName------------"+companyName);
		}
		c=(Company)sessionmap.get("Company");
		User sessionUser=(User)sessionmap.get("User");
		hm.setCreatedByCompanyName(c.getCompanyname());
		hm.setCompanyId(Integer.parseInt(companyId));
		hm.setConfigId(Integer.parseInt(config_Id));
		hm.setConfig_number(configNumber);
		hm.setConfigname(configName); 
		hm.setCreatedbyCompanyId(c.getCompanyid()); 
		hm.setCreatedbyUserId(sessionUser.getId());
		hm.setModifiedbyUserId(sessionUser.getId());
		hm.setCreatedDate(new Date());
		hm.setModifiedDate(hm.getCreatedDate());
		hm.setCompanyName(companyName);
		Date hotelCheckInDate = null;
		Date hotelCheckOutDate = null;
		try {
			if(getCheckInDate()==null || getCheckInDate().equalsIgnoreCase(""))
			{
				hotelCheckInDate = dateFormat.parse("00-00-0000");
			}
			else{
				hotelCheckInDate = dateFormat.parse(getCheckInDate());
				
			}
			if(getCheckOutDate()==null  ||  getCheckOutDate().equalsIgnoreCase(""))
			{
				  hotelCheckOutDate = dateFormat.parse("00-00-0000");
			}
			else{
				hotelCheckOutDate = dateFormat.parse(getCheckOutDate());
				
			}
			 hm.setHotelCheckoutDate(hotelCheckOutDate);
			 hm.setHotelCheckinDate(hotelCheckInDate);
			if(hm.getPromofareStartDate()==null || hm.getPromofareStartDate().equals("")){
				hm.setPromofareStartDate("ALL"); 
			}
			if(hm.getPromofareEndDate()==null || hm.getPromofareEndDate().equals("")){
				hm.setPromofareEndDate("ALL"); 
			}
			
			int result = hmDAO.insertMarkupDetails(hm);
			if(result>0){
				addActionMessage(getText("global.addMarkupDetailsresult"));
				new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(hm.getId()),"Hotel markup created","Hotel markup created",NotificationInventoryTypeEnum.HOTEL_MARKUP.getId(),true,false,false,true,false,false);

				//addActionMessage("Successfully added.");
			}
			else{
				addActionError(getText("global.addMarkupDetailserror"));
				//addActionError("Failed.Try again.");
				return ERROR;
			}  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			addActionError(getText("global.addMarkupDetailparseexception"));
			//addActionError("Failed.Try again.");
			logger.error("-----ParseException------"+e.getMessage()); 
			return ERROR;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			addActionError(getText("global.addMarkupDetailExceptionexception"));
			//addActionError("Failed.Try again.");
			logger.error("-----Exception------"+e.getMessage()); 
			return ERROR;
		}

		return SUCCESS;

	}


	/* loading all HotelMarkups*/
	public String showHotelMarkupList() throws ParseException{
		Company companySessionObj=(Company)sessionmap.get("Company");
		List<HotelMarkup> hotelMatrkupLi=hmDAO.getHotelMarkupList(companySessionObj);
		List<HotelMarkup> newHotelMatrkupLi=new ArrayList<HotelMarkup>();
		if(hotelMatrkupLi!=null && hotelMatrkupLi.size()>0){
			for(HotelMarkup hotelMarkup:hotelMatrkupLi){
				if(hotelMarkup.getHotelCheckinDate()==null){
					 //if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()).equalsIgnoreCase("31-12-0002")){
							hotelMarkup.setCheckIn("ALL");
						//}
				}
				else{
					if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()).equalsIgnoreCase("31-12-0002")){
					hotelMarkup.setCheckIn("ALL");
				}
					else{
						hotelMarkup.setCheckIn(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()));	
					}
				}
				
				if(hotelMarkup.getHotelCheckoutDate()==null){
					hotelMarkup.setCheckOut("ALL");
				}
				else{
					if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckoutDate()).equalsIgnoreCase("31-12-0002")){
						hotelMarkup.setCheckOut("ALL");
					}
					else{
						hotelMarkup.setCheckOut(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckoutDate()));
					 } 
				}
				 
			 newHotelMatrkupLi.add(hotelMarkup);
			 }
			AllHotelMatrkup = newHotelMatrkupLi;
			// sessionmap.put("hotelMarkupList", newHotelMatrkupLi);
		}
		else{
			//sessionmap.remove("hotelMarkupList");
		}
		return SUCCESS;


	}
	/*this method for passing  markupId and get MarkupProfile*/
	public String getHotelMarkupProfile(){
		CurrentProfile = hmDAO.getHotelMarkupDetails(hm);
		if(CurrentProfile!=null){
			CompanyConfig compConfig=new MarkupDao().getCompanyUserIdByConfigId(CurrentProfile.getConfigId());
			if(compConfig!=null){
				CurrentProfile.setCompanyUserId(compConfig.getCompanyUserId());
			}
		}
		 if(CurrentProfile.getHotelCheckinDate()==null){
			 //if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()).equalsIgnoreCase("31-12-0002")){
			CurrentProfile.setCheckIn("ALL");
				//}
		}
		else{
			if(DateConversion.convertDateToStringToDate(CurrentProfile.getHotelCheckinDate()).equalsIgnoreCase("31-12-0002")){
				CurrentProfile.setCheckIn("ALL");
		}
			else{
				CurrentProfile.setCheckIn(DateConversion.convertDateToStringToDate(CurrentProfile.getHotelCheckinDate()));	
			}
		}
		
		if(CurrentProfile.getHotelCheckoutDate()==null){
			CurrentProfile.setCheckOut("ALL");
		}
		else{
			if(DateConversion.convertDateToStringToDate(CurrentProfile.getHotelCheckoutDate()).equalsIgnoreCase("31-12-0002")){
				CurrentProfile.setCheckOut("ALL");
			}
			else{
				CurrentProfile.setCheckOut(DateConversion.convertDateToStringToDate(CurrentProfile.getHotelCheckoutDate()));
			 } 
		}
		 
		
		getCountryandlanguagelist();
		//sessionmap.put("HotelMarkupProfile",CurrentProfile);
		return SUCCESS;
	}


	/*updating the hotel markup based on markupId */
	public String updateHotelMarkup() {
		User sessionUser=(User)sessionmap.get("User");
		CurrentProfile = hmDAO.getHotelMarkupDetails(hm);
		//HotelMarkup getProfile= (HotelMarkup)sessionmap.get("HotelMarkupProfile");
		hm.setId(CurrentProfile.getId());
		Date hotelCheckInDate = null;
		Date hotelCheckOutDate = null;
		 
		try {
		if(getCheckInDate().equalsIgnoreCase("ALL") ||  getCheckInDate().equals("") || getCheckInDate()==null){
			hotelCheckInDate = dateFormat.parse("00-00-0000");
		}
		else{
			 
			hotelCheckInDate = dateFormat.parse(getCheckInDate());
	 
		 }
		if(getCheckOutDate().equalsIgnoreCase("ALL") ||  getCheckOutDate().equals("") || getCheckOutDate()==null){
			hotelCheckOutDate = dateFormat.parse("00-00-0000");
		}
		else{
			hotelCheckOutDate = dateFormat.parse(getCheckOutDate());
			
		}
		 hm.setHotelCheckoutDate(hotelCheckOutDate);
		 hm.setHotelCheckinDate(hotelCheckInDate);
		if(hm.getPromofareStartDate()==null || hm.getPromofareStartDate().equals("")){
			hm.setPromofareStartDate("ALL"); 
		}
		if(hm.getPromofareEndDate()==null || hm.getPromofareEndDate().equals("")){
			hm.setPromofareEndDate("ALL"); 
		}
		boolean updated = hmDAO.updateHotelMarkup(hm);
		getCountryandlanguagelist();
		if(updated)
		{	
			addActionMessage(getText("globlal.updateHotelMarkupsuccess"));
			new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(hm.getId()),"Hotel markup updated","Hotel markup updated",NotificationInventoryTypeEnum.HOTEL_MARKUP.getId(),true,false,false,true,false,false);

			//addActionMessage("Successfully Updated.");
		}
		else
		{
			addActionError(getText("global.updateHotelmarkuperror"));
			//addActionError("Failed.Try again.");
			return ERROR;
		} 
		 } catch (ParseException e) {
			// TODO Auto-generated catch block
			 addActionError(getText("global.updateHotelMarkupparseexception"));
			//addActionError("Failed.Try again.");
			logger.error("-----Exception------"+e.getMessage()); 
			return ERROR;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			addActionError(getText("global.updateHotelMarkupexception"));
			//addActionError("Failed.Try again.");
			logger.error("-----Exception------"+e.getMessage()); 
			return ERROR;
		} 
		return SUCCESS;
	}

 /*deleting  the hotel markup based on markupId */
	public String deleteHotelMarkupList(){
		logger.info("-----------------markup id.............."+hm.getId());
		 
		boolean isdelete= hmDAO.deleteHotelMarkupDetails(hm.getId());
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
		
		//logger.info("CountryList" +CountryList.size());
		return SUCCESS;
	}

	/*this method for  fetching  All parent company company config ids for set markup*/
	public String getCompanyConfigIds(){
		Company sessionObj=(Company)sessionmap.get("Company");
		MarkupDao  markupDao =new MarkupDao();
		List<Company> companyIds=markupDao.getCompanyIds(sessionObj);
		if(companyIds!=null && companyIds.size()>0){
			companyConfigIdsList =markupDao.getCompanyConfigIds(companyIds);
			logger.info("---------companyConfigIdsList-------------"+companyConfigIdsList.size());
			/*List<CompanyConfig> newcompanyConfigIdsList= new ArrayList<CompanyConfig>();
			 for(CompanyConfig companyConfig:companyConfigIdsList){
				if(!companyConfig.isWhitelable() && companyConfig.getConfig_id()!=1){
					newcompanyConfigIdsList.add(companyConfig);
				}
			} */
			//sessionmap.put("companyConfigIds",companyConfigIdsList);
		}

		List<CompanyConfig> agencycompanyConfigIdsList=markupDao.getAgencyCompanyConfigIds(sessionObj);
		List<CompanyConfig> newAgencycompanyConfigIdsList= new ArrayList<CompanyConfig>();
		if(agencycompanyConfigIdsList!=null && agencycompanyConfigIdsList.size()>0){
			AgencyConfiglist = agencycompanyConfigIdsList;
			/*for(CompanyConfig companyConfig:agencycompanyConfigIdsList){
				if(!companyConfig.isWhitelable() && companyConfig.getConfig_id()!=1){
					newAgencycompanyConfigIdsList.add(companyConfig);
				}
			}*/
			//sessionmap.put("AgencyConfigIdsList",agencycompanyConfigIdsList);
		}
		getCountryandlanguagelist();
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ADD_HOTEL_MARKUP, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  
		
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}

	@Override
	public HotelMarkup getModel() {
		// TODO Auto-generated method stub
		return hm;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
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


	public String getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}


	public String getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
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
public List<Country> getCountryList() {
		
		return CountryList;
	}
	
	public void setCountryList(List<Country> countryList) {
		this.CountryList = countryList;
	}


	public List<HotelMarkup> getAllHotelMatrkup() {
		return AllHotelMatrkup;
	}


	public void setAllHotelMatrkup(List<HotelMarkup> allHotelMatrkup) {
		AllHotelMatrkup = allHotelMatrkup;
	}


	public HotelMarkup getCurrentProfile() {
		return CurrentProfile;
	}


	public void setCurrentProfile(HotelMarkup currentProfile) {
		CurrentProfile = currentProfile;
	}	 

}
