package com.tayyarah.hotel.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;
import com.tayyarah.hotel.DAO.AddHotelDAO;
import com.tayyarah.hotel.model.HotelDetails;

public class HotelDetailAction  extends ActionSupport implements SessionAware {

	/**
	 * @author HARSHA
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelDetailAction.class);
	private String hotelDetailsJson;
	private HotelDetails hotelDetailsResponseJson;
	private String hotelRoomDetailsJson;
	private HotelDetails hotelDetails =new HotelDetails();
	AddHotelDAO addHotelDAO=new AddHotelDAO(); 
	SessionMap<String, Object> sessionMap=null;
	public String addHotelDetails(){	
		User user=(User)sessionMap.get("User");
		
	 logger.info("---------getHotelDetails-------------"+getHotelDetailsJson());
		try {
			JSONObject json=new JSONObject(getHotelDetailsJson());
			String name = json.getString("name");
			String hotelCode = json.getString("hotelCode");
			String hotelChain = json.getString("hotelChain");
			String rating = json.getString("rating");
			String city = json.getString("city");
			String latitude = json.getString("latitude");
			String longitude = json.getString("longitude");
			String hotelType = json.getString("hotelType");
			String companyUserId = json.getString("companyUserId");
			String  active  = json.getString("activeStatus");
			String country = json.getString("country");
			boolean isactive=false;
			 
			if(companyUserId==null || companyUserId.equals("")){
				companyUserId="ALL";
			 }
			if(active.equals("1")){
				isactive=true;
			}
			logger.info("---------companyUserId-------------"+companyUserId);
			hotelDetails.setCompanyUserId(companyUserId);
			hotelDetails.setName(name);
			hotelDetails.setCity(city);
			hotelDetails.setActiveStatus(isactive);
			hotelDetails.setCountry(country);
			hotelDetails.setCreatedAt(new Timestamp(new Date().getTime()));
			hotelDetails.setHotelChain(hotelChain);
			hotelDetails.setHotelCode(hotelCode);
			hotelDetails.setLatitude(latitude);
			hotelDetails.setLongitude(longitude);
			hotelDetails.setHotelType(hotelType);
			hotelDetails.setRating(rating);
			//hotelDetails.setRooms(new ArrayList<HotelRoomDetails>());
			logger.info(hotelDetails.toString());
			 HotelDetails insertHotelDetails=addHotelDAO.insert(hotelDetails);
			 logger.info("insertHotelDetails-------"+insertHotelDetails.toString());

			if(insertHotelDetails!= null && insertHotelDetails.getId()>0){
				setHotelDetailsJson(null);
				setHotelDetails(insertHotelDetails);				 
			} 
			//HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_ADD_HOTEL.getId(), BrowsingOptionActionEnum.SETTINGS_ADDHOTEL_GOHOTELS_CREATEHOTEL.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.GOHOTELS_CREATEHOTEL.getId(), String.valueOf(user.getCompanyid()),"Go hotel hotel  create search click ");
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_ADD_HOTEL.getId(), BrowsingOptionActionEnum.SETTINGS_ADDHOTEL_GOHOTELS_CREATEHOTEL.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.GOHOTELS_CREATEHOTEL.getId(), String.valueOf(user.getCompanyid()),"Go hotel hotel  create search click ");
		
		return SUCCESS;		
	}

	public String addHoteRoomDetails(){			
		return SUCCESS;		
	}

	public String getHotelDetailsJson() {
		return hotelDetailsJson;
	}

	public void setHotelDetailsJson(String hotelDetailsJson) {
		this.hotelDetailsJson = hotelDetailsJson;
	}

	public String getHotelRoomDetailsJson() {
		return hotelRoomDetailsJson;
	}

	public void setHotelRoomDetailsJson(String hotelRoomDetailsJson) {
		this.hotelRoomDetailsJson = hotelRoomDetailsJson;
	}

	public HotelDetails getHotelDetails() {
		/*if(hotelDetails==null){
			hotelDetails=new HotelDetails();
		}*/
		return hotelDetails;
	}

	public void setHotelDetails(HotelDetails hotelDetails) {
		this.hotelDetails = hotelDetails;
	}

	public HotelDetails getHotelDetailsResponseJson() {
		return hotelDetailsResponseJson;
	}

	public void setHotelDetailsResponseJson(HotelDetails hotelDetailsResponseJson) {
		this.hotelDetailsResponseJson = hotelDetailsResponseJson;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

}
