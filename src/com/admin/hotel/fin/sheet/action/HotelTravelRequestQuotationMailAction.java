package com.admin.hotel.fin.sheet.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.flight.fin.sheet.Dao.FlightTravelRequestDao;
import com.admin.flight.fin.sheet.model.FlightQuotationHistory;
import com.admin.flight.fin.sheet.model.FlightTravelRequestQuotation;
import com.admin.hotel.fin.sheet.Dao.HotelQuotationHistoryDao;
import com.admin.hotel.fin.sheet.Dao.HotelTravelRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.HotelQuotationHistory;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class HotelTravelRequestQuotationMailAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String selectedQuotaionList ="";
	private String travelRequestId ="";
	private String toEmail=null;
	private String CCEmail=null;
  HotelTravelRequestDao hotelTravelRequestDao=new HotelTravelRequestDao();
 FlightTravelRequestDao flightTravelRequestDao=new FlightTravelRequestDao();
	SessionMap<String, Object> sessionmap ;
	private Map<String, String> dataResponseJson = new HashMap<String, String>();

	public String insertHotelQuotationMailData(){
		CompanyDAO ComregDAO = new CompanyDAO();
		User user=(User)sessionmap.get("User");
		String data = "<"+user.getId()+"><"+travelRequestId+">"+"<"+selectedQuotaionList+">";
		String QuotationIds[]=selectedQuotaionList.split(",");
		for(String quotationId:QuotationIds){
			HotelTravelRequestQuotation hotelTravelRequestQuotation=hotelTravelRequestDao.getHotelTravelRequestQuotationDetails(Long.parseLong(quotationId));
			if(hotelTravelRequestQuotation!=null && hotelTravelRequestQuotation.getHotetFlightBookingStatus()!=null){
				HotelFlightBookingStatus hotelFlightBookingStatus=new HotelFlightBookingStatus();
				hotelFlightBookingStatus.setId(hotelTravelRequestQuotation.getHotetFlightBookingStatus().getId());
				hotelFlightBookingStatus.setqSentToMail(TravelRequestStatusEnum.QSENT.getValue());
				hotelTravelRequestDao.updateHotelOrFlightQuotationSendStatus(hotelFlightBookingStatus);
			}
		}
 Email  email= ComregDAO.insertEmail(data, 0, Email.EMAIL_TYPE_HOTEL_OFFLINE_REQUEST_QUOTATION );
		if(email!=null)
			dataResponseJson.put("status", "1");
		else
			dataResponseJson.put("status", "0");
 
		return SUCCESS;
	}

	public String insertFlightQuotationMailData(){
		CompanyDAO ComregDAO = new CompanyDAO();
		User user=(User)sessionmap.get("User");
		String data = "<"+user.getId()+"><"+travelRequestId+">"+"<"+selectedQuotaionList+">";
		String QuotationIds[]=selectedQuotaionList.split(",");
		for(String quotationId:QuotationIds){
			FlightTravelRequestQuotation flightTravelRequestQuotation=flightTravelRequestDao.getFlightTravelRequestQuotationDetails(Long.parseLong(quotationId));
			if(flightTravelRequestQuotation!=null && flightTravelRequestQuotation.getHotetFlightBookingStatus()!=null){
				HotelFlightBookingStatus hotelFlightBookingStatus=new HotelFlightBookingStatus();
				hotelFlightBookingStatus.setId(flightTravelRequestQuotation.getHotetFlightBookingStatus().getId());
				hotelFlightBookingStatus.setqSentToMail(TravelRequestStatusEnum.QSENT.getValue());
				hotelTravelRequestDao.updateHotelOrFlightQuotationSendStatus(hotelFlightBookingStatus);
			}
		}
		
		Email  email= ComregDAO.insertEmail(data, 0, Email.EMAIL_TYPE_FLIGHT_OFFLINE_REQUEST_QUOTATION);
		if(email!=null)
			dataResponseJson.put("status", "1");
		else
			dataResponseJson.put("status", "0");
		return SUCCESS;
	}

	public String insertHotelQuotationAlternativeMailData(){
		User user=(User)sessionmap.get("User");
		String data = "<"+user.getId()+"><"+travelRequestId+">"+"<"+selectedQuotaionList+">";
		HotelQuotationHistory hotelQuotationHistory=new HotelQuotationHistory();
		hotelQuotationHistory.setCcMails(getCCEmail());
		hotelQuotationHistory.setToMails(getToEmail());
		hotelQuotationHistory.setHotelQuotationSchema(data);
		hotelQuotationHistory.setEmailStatusId(HotelQuotationHistory.INIT_STATUS_ID);
		hotelQuotationHistory=new  HotelQuotationHistoryDao().insertHotelQuotationHistory(hotelQuotationHistory);
		if(hotelQuotationHistory!=null && hotelQuotationHistory.getId()>0){
			new CompanyDAO().insertEmail(String.valueOf(hotelQuotationHistory.getId()), 0, Email.EMAIL_TYPE_HOTEL_QUOTATION_ALTERNATIVE_EMAIL_SEND);
			dataResponseJson.put("status", "1");
		}
		else
			dataResponseJson.put("status", "0");
		return SUCCESS;
	}
	public String insertFlightQuotationAlternativeMailData(){
		User user=(User)sessionmap.get("User");
		String data = "<"+user.getId()+"><"+travelRequestId+">"+"<"+selectedQuotaionList+">";
		FlightQuotationHistory flightQuotationHistory=new FlightQuotationHistory();
		flightQuotationHistory.setCcMails(getCCEmail());
		flightQuotationHistory.setToMails(getToEmail());
		flightQuotationHistory.setFlightQuotationSchema(data);
		flightQuotationHistory.setEmailStatusId(HotelQuotationHistory.INIT_STATUS_ID);
		flightQuotationHistory=new  HotelQuotationHistoryDao().insertFlightQuotationHistory(flightQuotationHistory);
		if(flightQuotationHistory!=null && flightQuotationHistory.getId()>0){
			new CompanyDAO().insertEmail(String.valueOf(flightQuotationHistory.getId()), 0, Email.EMAIL_TYPE_FLIGHT_QUOTATION_ALTERNATIVE_EMAIL_SEND);
			dataResponseJson.put("status", "1");
		}
		else
			dataResponseJson.put("status", "0");
		return SUCCESS;
	}










	public String getSelectedQuotaionList() {
		return selectedQuotaionList;
	}
	public void setSelectedQuotaionList(String selectedQuotaionList) {
		this.selectedQuotaionList = selectedQuotaionList;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionmap = (SessionMap<String, Object>) arg0;

	}
	public String getTravelRequestId() {
		return travelRequestId;
	}
	public void setTravelRequestId(String travelRequestId) {
		this.travelRequestId = travelRequestId;
	}
	public Map<String, String> getDataResponseJson() {
		return dataResponseJson;
	}
	public void setDataResponseJson(Map<String, String> dataResponseJson) {
		this.dataResponseJson = dataResponseJson;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getCCEmail() {
		return CCEmail;
	}

	public void setCCEmail(String cCEmail) {
		CCEmail = cCEmail;
	}
}
