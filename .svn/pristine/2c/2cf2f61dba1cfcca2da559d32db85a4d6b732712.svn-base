package com.admin.hotel.fin.sheet.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.util.CommonUtilSession;
import com.admin.hotel.fin.sheet.Dao.HotelTravelRequestDao;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

public class HotelTravelRequestQuotationCrudAction  extends ActionSupport implements ModelDriven<HotelTravelRequestQuotation>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionmap ;
	HotelTravelRequestQuotation hotelTravelRequestQuotation=new HotelTravelRequestQuotation();
	HotelTravelRequestDao hotelTravelRequestDao=new HotelTravelRequestDao();
	public String hotelRequestQuotationUpdate(){
		User sessionUser=(User)sessionmap.get("User");
		hotelTravelRequestQuotation.setCompanyId(sessionUser.getCompanyid());
		hotelTravelRequestQuotation.setUserId(CommonUtilSession.checkEmulatedUser(sessionmap)?CommonUtilSession.getEmulatedUserIdInt(sessionmap):sessionUser.getId());
	  HotelTravelRequestQuotation updatedQuotationObj=hotelTravelRequestDao.hotelRequestQuotationUpdate(hotelTravelRequestQuotation);
		if(updatedQuotationObj!=null){
			addActionMessage("Successfully Updated."); 
			new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(updatedQuotationObj.getId()),"Hotal quotation updated","Hotal quotation updated",NotificationInventoryTypeEnum.HOTEL_QUOATATION.getId(),false,false,false,true,false,false);

		}
		else{
			addActionMessage("We found some error.Please try again."); 
		}
		return SUCCESS;	
	}
  
	 
	
	
	//https://devlintas.tayyarah.com/devtayyarahapi/getpdf?orderids=(ordered/requested/quotationid)&userid=(userid)&type=(3 for HOTEL_QUOATATION_RESPONSE)
	
	
	
	
	
	
	
	
	@Override
	public HotelTravelRequestQuotation getModel() {
		// TODO Auto-generated method stub
		return hotelTravelRequestQuotation;
	}
	public HotelTravelRequestQuotation getHotelTravelRequestQuotation() {
		return hotelTravelRequestQuotation;
	}
	public void setHotelTravelRequestQuotation(HotelTravelRequestQuotation hotelTravelRequestQuotation) {
		this.hotelTravelRequestQuotation = hotelTravelRequestQuotation;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sessionmap = (SessionMap<String, Object>) arg0;
	}

}
