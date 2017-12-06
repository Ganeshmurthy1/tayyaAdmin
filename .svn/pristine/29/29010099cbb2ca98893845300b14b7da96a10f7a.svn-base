package com.tayyarah.hotel.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;
import com.tayyarah.hotel.DAO.AddRoomsDAO;
import com.tayyarah.hotel.model.HotelRoomDetails;

public class HotelRoomDetailsFilterAction  extends ActionSupport implements  ModelDriven<HotelRoomDetails>,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HotelRoomDetails hotelRoomDetails = new HotelRoomDetails();
	SessionMap<String, Object> sessionMap;
	AddRoomsDAO addRoomsDAO=new AddRoomsDAO();
	public String hotelRoomDetails(){
		User user=(User)sessionMap.get("User");
		hotelRoomDetails=addRoomsDAO.getHotelRoomDetails(hotelRoomDetails.getId());
		
		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_HOTEL_LIST.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.EDIT.getId(), String.valueOf(user.getCompanyid()),"Hotel room list edit click ");
		 return SUCCESS;

	}
	
	public String hotelRoomUpdate(){
		User user=(User)sessionMap.get("User");
		HotelRoomDetails hotelRoomObj=addRoomsDAO.getHotelRoomDetails(hotelRoomDetails.getId());
		HotelRoomDetails hotelRoomUpdateObj=addRoomsDAO.getHotelRoomUpdate(hotelRoomDetails,hotelRoomObj);
		 if(hotelRoomUpdateObj!=null && hotelRoomUpdateObj.getId()==hotelRoomDetails.getId()){
			 addActionMessage(getText("global.insertOrderModifiedInfocreditnote.getId"));
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_HOTEL_LIST.getId(), BrowsingOptionActionEnum.ACTION_SEARCH.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"Hotel room  edit search click ");
			 
			 return SUCCESS;
		 }
		 else{
			 addActionError(getText("global.addMarkupDetailserror"));
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_HOTEL_LIST.getId(), BrowsingOptionActionEnum.ACTION_SEARCH.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"Hotel room  edit search click  ");
			 
			 return ERROR;
		 }
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public HotelRoomDetails getModel() {
		// TODO Auto-generated method stub
		return hotelRoomDetails;
	}
	public HotelRoomDetails getHotelRoomDetails() {
		return hotelRoomDetails;
	}

	public void setHotelRoomDetails(HotelRoomDetails hotelRoomDetails) {
		this.hotelRoomDetails = hotelRoomDetails;
	}
}
