package com.tayyarah.hotel.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.lintas.admin.model.User;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;
import com.tayyarah.hotel.DAO.AddRoomsDAO;
import com.tayyarah.hotel.model.HotelDetails;
import com.tayyarah.hotel.model.HotelRoomDetails;

public class HotelRoomDetailAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelRoomDetailAction.class);
	
/*	
	private String hotelDetailsJson;*/
	private String hotelRoomDetailsJson;
	SessionMap<String, Object> sessionMap=null;
	 
	
	AddRoomsDAO addRoomsDAO=new AddRoomsDAO(); 
	public String addRoomDetails(){	
		User user=(User)sessionMap.get("User");
		logger.info("---------getRoomDetails-------------"+getHotelRoomDetailsJson());
		try {
			JSONObject json=new JSONObject(getHotelRoomDetailsJson());
			String basePrice = json.getString("basePrice");
			String taxPrice = json.getString("taxPrice");
			String availability = json.getString("availability");
			String startDate = json.getString("startDate");
			String endDate = json.getString("endDate");
			String extraBedPrice = json.getString("extraBedPrice");
			String cancelBeforeDay = json.getString("cancelBeforeDay");
			String cancelAmount= json.getString("cancelAmount");
			String amountType = json.getString("amountType");
			String condition = json.getString("condition");
			String description = json.getString("description");
			String  roomName = json.getString("roomName");
			int beds = Integer.parseInt(json.getString("beds"));
			int adults = Integer.parseInt(json.getString("adults"));
			int infants = Integer.parseInt(json.getString("infants"));
			int childs = Integer.parseInt(json.getString("childs"));
			Long hotelId = Long.parseLong(json.getString("id"));
			HotelRoomDetails hotelRoomDetails =new HotelRoomDetails();
			hotelRoomDetails.setBasePrice(new BigDecimal(basePrice));
			hotelRoomDetails.setCancelAmount(new BigDecimal(cancelAmount));
			hotelRoomDetails.setBasePrice(new BigDecimal(basePrice));
			hotelRoomDetails.setTaxPrice(new BigDecimal(taxPrice));		
			hotelRoomDetails.setAvailability(Integer.parseInt(availability));
			hotelRoomDetails.setCreatedAt(new Timestamp(new Date().getTime()));
			hotelRoomDetails.setUpdatedAt(new Timestamp(new Date().getTime()));
			hotelRoomDetails.setStartDate(DateConversion.StringToDate(startDate));
			hotelRoomDetails.setEndDate(DateConversion.StringToDate(endDate)); 
			hotelRoomDetails.setExtraBedPrice(new BigDecimal(extraBedPrice));
			hotelRoomDetails.setCancelBeforeDay(Integer.valueOf(cancelBeforeDay));
			hotelRoomDetails.setAmountType(amountType);
			hotelRoomDetails.setCond(condition);
			hotelRoomDetails.setAdults(adults);
			hotelRoomDetails.setBeds(beds);
			hotelRoomDetails.setInfants(infants);
			hotelRoomDetails.setChilds(childs);
			hotelRoomDetails.setName(roomName);
			hotelRoomDetails.setDescription(description);
			HotelDetails hotelDetails=addRoomsDAO.getHotelDetailsById(hotelId);
			if(hotelDetails!=null){
				hotelRoomDetails.setHotelDetails(hotelDetails);
			}
			
			logger.info(hotelRoomDetails.toString());
			HotelRoomDetails insertHotelRoomDetails=addRoomsDAO.insert(hotelRoomDetails);
			 logger.info("insertHotelDetails----ID---"+insertHotelRoomDetails.getId());
			 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_ADD_HOTEL.getId(), BrowsingOptionActionEnum.SETTINGS_ADDHOTEL_GOHOTELS_CREATEROOM.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.GOHOTELS_CREATEROOM.getId(), String.valueOf(user.getCompanyid()),"Go hotel hotel room create room click ");
		
		return SUCCESS;		
	}

	public String addHoteRoomDetails(){			
		return SUCCESS;		
	}
 
	 

	public String getHotelRoomDetailsJson() {
		return hotelRoomDetailsJson;
	}

	public void setHotelRoomDetailsJson(String hotelRoomDetailsJson) {
		this.hotelRoomDetailsJson = hotelRoomDetailsJson;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
 
}
