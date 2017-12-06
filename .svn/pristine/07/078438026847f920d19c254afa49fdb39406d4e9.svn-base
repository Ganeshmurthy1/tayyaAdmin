package com.lintas.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.AirportDao;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Airport;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class AirportlistAction  extends ActionSupport implements ModelDriven<Airport>,SessionAware{
	
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirportlistAction.class);
	private static final long serialVersionUID = 1L;
	private List<Airport> AirportList ;
	AirportDao airportdao = new AirportDao();
	private Airport airport = new Airport();
	private Airport currentairport;
	private List<Country> countryLi;
	private InputStream inputStream;
	
	
	SessionMap<String, Object> sessionmap=null;
	public String GetAirportList()
	{
		AirportList = airportdao.GetAllAirportList();
		
		return SUCCESS;
	}
	
	public String filerAirportList()
	{
		logger.info("--------airport------------"+airport.getCountry());
		logger.info("--------airport code------------"+airport.getAirport_code());
		AirportList = airportdao.filterAirportList(airport); 
		return SUCCESS;
	}
	 public String getCountryList()
	{
		 User user=(User) sessionmap.get("User");
		countryLi = new CountryDao().getCountryList();
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.SETTINGS_ADD_AIRPORT, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		  historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_SHOW_ALL_AIRPORTS.getId(), BrowsingOptionActionEnum.ACTION_ADDNEW.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.ADD_NEW.getId(), String.valueOf(user.getCompanyid()),"show airport list add new click ");
			
		return SUCCESS;
	}
	
	public String InsertAirport()
	{
		User user=(User) sessionmap.get("User");
		boolean IsAdded = airportdao.AddAirport(airport);
		if(IsAdded){
			addActionMessage(getText("global.insertAirportsuccess"));
			HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_ADD_AIRPORT.getId(), BrowsingOptionActionEnum.ACTION_ADD.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.ADD.getId(), String.valueOf(user.getCompanyid()),"add airport  add click ");
				
		  return SUCCESS;
		}
		else{
			addActionError(getText("global.insertAirporterror"));
			HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_ADD_AIRPORT.getId(), BrowsingOptionActionEnum.ACTION_ADD.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.ADD.getId(), String.valueOf(user.getCompanyid()),"add airport  add click ");
				
			return ERROR;
		}
	}
	public String GetAirport()
	
	{
		User user=(User) sessionmap.get("User");
		currentairport =  airportdao.EditAirport(airport);
		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_SHOW_ALL_AIRPORTS.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.EDIT.getId(), String.valueOf(user.getCompanyid()),"show edit airport list edit  click");
			
		 return SUCCESS;
		
	}
	public String UpdateAirport()
	{
		User user=(User) sessionmap.get("User");
		currentairport =  airportdao.UpdateAirport(airport);
		if(currentairport!=null){
			addActionMessage(getText("global.updateAirportsuccess"));
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_SHOW_ALL_AIRPORTS.getId(), BrowsingOptionActionEnum.ACTION_ADD_UPDATE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"show edit airport list update  click");
				
			   return SUCCESS;	
		}
		else{
			addActionError(getText("global.updateairporterror"));
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_SHOW_ALL_AIRPORTS.getId(), BrowsingOptionActionEnum.ACTION_ADD_UPDATE.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"show edit airport list update  click");
				
			 return ERROR;
		}
	}
	public String DeleteAirport()
	{
		User user=(User) sessionmap.get("User");
		boolean IsDeleted =  airportdao.DeleteAirport(airport);
		if(IsDeleted){
			showSuccessMessage("deleted");
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_SHOW_ALL_AIRPORTS.getId(), BrowsingOptionActionEnum.ACTION_DELETE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.DELETE.getId(), String.valueOf(user.getCompanyid()),"show edit airport list delete  click");
				
			   return SUCCESS;	
		}
		else{
			showSuccessMessage("failed");
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_SHOW_ALL_AIRPORTS.getId(), BrowsingOptionActionEnum.ACTION_DELETE.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.DELETE.getId(), String.valueOf(user.getCompanyid()),"show edit airport list delete  click");
				
			 return ERROR;
		}
	 }
	
	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public List<Airport> getAirportList() {
		return AirportList;
	}
	public void setAirportList(List<Airport> airportList) {
		AirportList = airportList;
	}

	@Override
	public Airport getModel() {
		// TODO Auto-generated method stub
		return airport;
	}

	public Airport getCurrentairport() {
		return currentairport;
	}

	public void setCurrentairport(Airport currentairport) {
		this.currentairport = currentairport;
	}

	public List<Country> getCountryLi() {
		return countryLi;
	}

	public void setCountryLi(List<Country> countryLi) {
		this.countryLi = countryLi;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}

}
