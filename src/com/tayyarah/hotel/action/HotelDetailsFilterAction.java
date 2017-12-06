package com.tayyarah.hotel.action;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Company;
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
import com.tayyarah.hotel.DAO.AddHotelDAO;
import com.tayyarah.hotel.model.HotelDetails;

public class HotelDetailsFilterAction  extends ActionSupport implements  ModelDriven<HotelDetails>,SessionAware{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelDetailsFilterAction.class);
	private List<Country> countyList;
	SessionMap<String, Object> sessionMap;
	private List<HotelDetails> hotelList;
	 
	 
	AddHotelDAO addHotelDAO=new AddHotelDAO(); 
	CompanyDAO ComregDAO = new CompanyDAO();
	HotelDetails hotelDetails=new HotelDetails();

	

	public String  goHotels(){

		Company sessionObj=(Company) sessionMap.get("Company");
		List<Company> companyList = ComregDAO.getDirectCompanyListUnderParent(sessionObj);
		logger.info(" companyList size----------------- "+companyList.size());
		JSONArray list = new JSONArray();
		Map<JSONObject,JSONObject> map =new HashMap<>();
		if(companyList!=null && companyList.size()>0){
			for(Company company:companyList){
				JSONObject text= new JSONObject();
				JSONObject value= new JSONObject();
				text.put("text", company.getCompany_userid());
				text.put("value", company.getCompany_userid());
				list.add(text);
			}
		}
	 String path="D:\\json\\directCompanyList.json";
		//String path="/home/json/directCompanyList.json";
		//String path = servletRequest.getSession().getServletContext().getRealPath("/directCompanyList.json" );
		logger.info("path---------"+path);
		try {
			logger.info("obj.toJSONString()---------"+list.toJSONString());
			FileWriter file = new FileWriter(path);
			file.write(list.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		setCountyList(new CountryDao().getCountryList());
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.SETTINGS_HOTELS_ADD_HOTEL, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		  
		return SUCCESS;

	}

	public String  hotelList(){
		hotelList=addHotelDAO.fetchHotelList();
		return SUCCESS;

	}

	public String  hotelDetails(){
		User user=(User)sessionMap.get("User");
		hotelDetails=addHotelDAO.getHotelDetails(hotelDetails.getId());
		setCountyList(new CountryDao().getCountryList());
		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_HOTEL_LIST.getId(), BrowsingOptionActionEnum.HOTEL_DETAILS.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.HOTEL_ROOMDETAILS.getId(), String.valueOf(user.getCompanyid()),"Hotel list room details(edit) click ");
		 return SUCCESS;

	}
	

	public String hotelUpdate(){
		User user=(User)sessionMap.get("User");
		HotelDetails hotelDetailsObj=addHotelDAO.getHotelDetails(hotelDetails.getId());
		hotelDetails.setCreatedAt(hotelDetailsObj.getCreatedAt());
		hotelDetails.setCompanyUserId(hotelDetailsObj.getCompanyUserId());
		HotelDetails hotelUpdateObj=addHotelDAO.hotelUpdate(hotelDetails);
		 if(hotelUpdateObj!=null && hotelUpdateObj.getId()==hotelDetails.getId()){
			 addActionMessage(getText("global.insertOrderModifiedInfocreditnote.getId"));
			 HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
			  historyInfo.changeNature(BrowsingOptionPageEnum.SETTINGS_HOTELS_HOTEL_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
			  new HistoryManager().inSertHistory(historyInfo);
			 historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_HOTEL_LIST.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"Hotel room  edit update click ");
				 
				 return SUCCESS;
		 }
		 else{
			 addActionError(getText("global.addMarkupDetailserror"));
           HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_HOTEL_LIST.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"Hotel room  edit update click ");
			 
			 return ERROR;
		 }

	}
	
	
	
	public List<Country> getCountyList() {
		return countyList;
	}

	public void setCountyList(List<Country> countyList) {
		this.countyList = countyList;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	public List<HotelDetails> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<HotelDetails> hotelList) {
		this.hotelList = hotelList;
	}

	@Override
	public HotelDetails getModel() {
		// TODO Auto-generated method stub
		return hotelDetails;
	}
	public HotelDetails getHotelDetails() {
		return hotelDetails;
	}

	public void setHotelDetails(HotelDetails hotelDetails) {
		this.hotelDetails = hotelDetails;
	}
}
