package com.admin.hotel.fin.sheet.action;

import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.util.CommonUtilSession;
import com.admin.hotel.fin.sheet.Dao.HotelQuotaionShemaDao;
import com.admin.hotel.fin.sheet.Dao.HotelQuotationHistoryDao;
import com.admin.hotel.fin.sheet.Dao.HotelTravelRequestDao;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.HotelQuotationHistory;
import com.admin.hotel.fin.sheet.model.HotelQuotationSchema;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.HotelTravelRequest;
import com.admin.hotel.fin.sheet.model.PaymentOptions;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.User;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

public class HotelTravelRequestQuotationAction extends ActionSupport implements ModelDriven<List<HotelTravelRequestQuotation>>,SessionAware,ServletRequestAware{
	HotelTravelRequestDao hotelTravelRequestDao=new HotelTravelRequestDao();
	HotelQuotationHistoryDao hotelQuotationHistoryDao=new HotelQuotationHistoryDao();
	SessionMap<String, Object> sessionmap;
	HotelQuotationHistory  hotelQuotationHistory= new HotelQuotationHistory();
	HttpServletRequest request;
	private static final long serialVersionUID = 1L;
	List<HotelTravelRequestQuotation> hotelTravelRequestQuotation=new ArrayList<HotelTravelRequestQuotation>();
	HotelTravelRequestQuotation hotelQuotation= new HotelTravelRequestQuotation();
	private Long hotelQuotationRequestId;
	private List<HotelTravelRequestQuotation> hotelRequestQuotationList=null;
	private List<Country> countryList=null;
	HotelOrderRow hotelOrderRow=new HotelOrderRow();
	HotelQuotaionShemaDao hotelQuotaionShemaDao=null;
	private List<HotelQuotationSchema> hotelQuotationSchemas = new ArrayList<HotelQuotationSchema>();	
	private HotelTravelRequest hotelTravelRequest=new  HotelTravelRequest();
	private int hotelRequestTravelQuotationSize=0;


	public String goHotelRequestTravelQuotation(){
		List<Country> list=new CountryDao().getCountryList();
		if(list!=null && list.size()>0){
			setCountryList(new CountryDao().getCountryList());
		}
		HotelTravelRequest hotelQuotationRequestNew=hotelTravelRequestDao.getHotelQuotationRequestDetails(hotelQuotationRequestId);
		if(hotelQuotationRequestNew!=null){
			hotelTravelRequest=hotelQuotationRequestNew;
		}
		if(hotelTravelRequest!=null)
		{
			List<HotelTravelRequestQuotation> hotelQuotationList=  hotelTravelRequestDao.getHotelRequestTravelQuotationList(hotelQuotationRequestId);
			if(hotelQuotationList!=null && hotelQuotationList.size()>0)
				hotelRequestTravelQuotationSize =hotelQuotationList.size(); 	
		}

		return SUCCESS; 
	}

	public String createHotelRequestTravelQuotation(){
		User sessionUser=(User)sessionmap.get("User");
		HotelTravelRequest hotelQuotationRequestNew=hotelTravelRequestDao.getHotelQuotationRequestDetails(hotelQuotationRequestId);
		List<HotelTravelRequestQuotation> hotelTravelRequestQuotationList= new ArrayList<HotelTravelRequestQuotation>();
		if(hotelTravelRequestQuotation!=null && hotelTravelRequestQuotation.size()>0){
			StringBuilder stringBuilder=new StringBuilder();
			for(HotelTravelRequestQuotation  QuotationObj:hotelTravelRequestQuotation){
				if(QuotationObj.getAvailablePaymentOptionList()!=null && QuotationObj.getAvailablePaymentOptionList().size()>0){
					for (int i = 0; i < QuotationObj.getAvailablePaymentOptionList().size(); i++) {
						String  availablePaymentOption= (String)QuotationObj.getAvailablePaymentOptionList().get(i);
						if(i == QuotationObj.getAvailablePaymentOptionList().size()-1)
							stringBuilder.append(""+availablePaymentOption+"");
						else
							stringBuilder.append(""+availablePaymentOption+","); 
					} 
				}
				QuotationObj.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
				 HotelFlightBookingStatus hotetFlightBookingStatus=new HotelFlightBookingStatus();
				hotetFlightBookingStatus.setCreated(TravelRequestStatusEnum.CREATED.getValue());
				QuotationObj.setHotetFlightBookingStatus(hotetFlightBookingStatus);
				String date1= QuotationObj.getCheckIn();
				String date2= QuotationObj.getCheckOut();
				QuotationObj.setCheckInDate(DateConversion.StringToDate(QuotationObj.getCheckIn()));
				QuotationObj.setCheckOutDate(DateConversion.StringToDate(QuotationObj.getCheckOut()));
				QuotationObj.setCompanyId(sessionUser.getCompanyid());
				QuotationObj.setUserId(CommonUtilSession.checkEmulatedUser(sessionmap)?CommonUtilSession.getEmulatedUserIdInt(sessionmap):sessionUser.getId());
				try {
					int noOfNights=CommonUtil.getNoofStayDays(date1, date2);
					QuotationObj.setNightCount(noOfNights);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				QuotationObj.setAvailablePaymentOption(stringBuilder.toString());
				QuotationObj.setAdditionalData(QuotationObj.getAdditionalData());
				PaymentOptions  paymentOptions=new PaymentOptions();
				if(QuotationObj.getAvailablePaymentOptionList().contains("PrePaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PostPaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PayAtHotel")){
					paymentOptions.setPayAtHotel(true);
					paymentOptions.setPrePaid(true);
					paymentOptions.setPostPaid(true);
				}

				else if(QuotationObj.getAvailablePaymentOptionList().contains("PrePaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PostPaid")){
					paymentOptions.setPrePaid(true);
					paymentOptions.setPostPaid(true);
				}

				else if(QuotationObj.getAvailablePaymentOptionList().contains("PrePaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PayAtHotel")){
					paymentOptions.setPrePaid(true);
					paymentOptions.setPayAtHotel(true);
				}
				else if(QuotationObj.getAvailablePaymentOptionList().contains("PostPaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PayAtHotel")){
					paymentOptions.setPostPaid(true);
					paymentOptions.setPayAtHotel(true);
				}
				else if(QuotationObj.getAvailablePaymentOptionList().contains("PostPaid")){
					paymentOptions.setPostPaid(true);

				}
				else if(QuotationObj.getAvailablePaymentOptionList().contains("PayAtHotel")){
					paymentOptions.setPayAtHotel(true);
				}
				else if(QuotationObj.getAvailablePaymentOptionList().contains("PrePaid")){
					paymentOptions.setPrePaid(true);
				}
				QuotationObj.setPaymentOptions(paymentOptions);
				hotelTravelRequestQuotationList.add(QuotationObj);
			}
		}
		
		 
		boolean isInserted= hotelTravelRequestDao.insertHotelQuotationList(hotelTravelRequestQuotationList, hotelQuotationRequestNew);
		if(isInserted){
			addActionMessage("Successfully created");
			new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(hotelQuotationRequestNew.getId()),"Hotal quotation created","Hotal qutation notification",NotificationInventoryTypeEnum.HOTEL_QUOATATION.getId(),false,false,false,true,false,false);
			return SUCCESS;
		}
		else{
			addActionError("We found some error.Please Try again.");
			return ERROR;
		} 

	}
	public String getHotelRequestTravelQuotationList(){
		  User sessionUser=(User)sessionmap.get("User");
		  List<HotelTravelRequestQuotation> hotelQuotationList=  hotelTravelRequestDao.getHotelRequestTravelQuotationList(hotelQuotationRequestId);
		  List<HotelTravelRequestQuotation> newHotelQuotationList=new ArrayList<>();
		   //long hotelOrderRowId=hotelTravelRequestDao.getHotelOrderRowIdFormHotelTravelRequestQuotation(hotelQuotationRequestId);
		  //logger.info("hotelOrderRowId----------------"+hotelOrderRowId);
		  /*if(hotelOrderRowId!=0){
		   hotelOrderRow =new HotelOrderDao().getHotelOrderRowInfo(hotelOrderRowId);
		  } 
		  */
		  CompanyConfig companyConfig=new CompanyConfigDao().getAppKeyByCompanyId(sessionUser.getCompanyid());
		  if(hotelQuotationList!=null && hotelQuotationList.size()>0){
		    for(HotelTravelRequestQuotation  hotelQuotation: hotelQuotationList){
		    	TripRequest tripRequest=null;
		     String onlineBookingUrl = getText("global.ibeurl"); 
		     if(hotelQuotation.getHotelTravelRequest()!=null)
				 tripRequest=new TripRequestDao().getTripRequestTripNumber(hotelQuotation.getHotelTravelRequest().getId(),"H");
		     long  tripId =tripRequest!=null && tripRequest.getTripId()>0?tripRequest.getTripId():0;     
		     
		     onlineBookingUrl=onlineBookingUrl+"?myaction=HotelQuoteRoomSearch&hotelquotationid="+hotelQuotation.getId()+"&dammytext="+sessionmap.get("Encryptedid")+"&tripNumber="+tripId;    
		     hotelQuotation.setOnlineUrl(onlineBookingUrl);
		     newHotelQuotationList.add(hotelQuotation);
		    
		    }
		    hotelRequestQuotationList=newHotelQuotationList;
		  }
		  return SUCCESS;
		 }
	 

	public String  goHoteltQuotationEdit(){
		
		HotelTravelRequestQuotation quotationObj =hotelTravelRequestDao.getHotelTravelRequestQuotationDetails(hotelQuotationRequestId);
		getHotelQuotationSchemaList(quotationObj.getAdditionalData(),hotelQuotationSchemas);
		if(quotationObj!=null){
			setHotelQuotation(quotationObj);
			setHotelQuotationRequestId(quotationObj.getHotelTravelRequest().getId());
		}
		List<Country> list=new CountryDao().getCountryList();
		if(list!=null && list.size()>0){
			setCountryList(new CountryDao().getCountryList());
		}
		  List<HotelTravelRequestQuotation> hotelQuotationList=  hotelTravelRequestDao.getHotelRequestTravelQuotationList(hotelQuotationRequestId);
			if(hotelQuotationList!=null && hotelQuotationList.size()>0)
				hotelRequestTravelQuotationSize =hotelQuotationList.size(); 	
	 
		
		
		return SUCCESS;
	}

	public  void  getHotelQuotationSchemaList(String additionalData, List<HotelQuotationSchema> hotelQuotationSchemas){
		try {
			if(additionalData!=null){
				StringTokenizer tok=new StringTokenizer(additionalData, "[<#\\#>]");
				List<String> addList=  new LinkedList<>();
				while(tok.hasMoreTokens()){
					String token=tok.nextToken();
					addList.add(token.trim());
				}
				Iterator<String> itr=addList.iterator();
				String[] partsTest=null;
				while (itr.hasNext()) {
					String s=itr.next();
					//logger.info("Tokens::::::  "+s);
					partsTest=s.split("\\(\\|\\)");
					hotelQuotationSchemas.add(new HotelQuotationSchema(partsTest[0], partsTest[1],Integer.parseInt(partsTest[3]), partsTest[4], partsTest[5],partsTest[2]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String  HoteltQuotationUpdate(){
		User sessionUser=(User)sessionmap.get("User");
		HotelTravelRequest hotelQuotationRequestNew=hotelTravelRequestDao.getHotelQuotationRequestDetails(hotelQuotationRequestId);
		List<HotelTravelRequestQuotation> hotelTravelRequestQuotationList= new ArrayList<HotelTravelRequestQuotation>();
		PaymentOptions  paymentOptions=new PaymentOptions();
		if(hotelTravelRequestQuotation!=null && hotelTravelRequestQuotation.size()>0){
			StringBuilder stringBuilder=new StringBuilder();
			for(HotelTravelRequestQuotation  QuotationObj:hotelTravelRequestQuotation){
				if(QuotationObj.getAvailablePaymentOptionList()!=null && QuotationObj.getAvailablePaymentOptionList().size()>0){
					for (int i = 0; i < QuotationObj.getAvailablePaymentOptionList().size(); i++) {
						String  availablePaymentOption= (String)QuotationObj.getAvailablePaymentOptionList().get(i);
						if(i == QuotationObj.getAvailablePaymentOptionList().size()-1)
							stringBuilder.append(""+availablePaymentOption+"");
						else
							stringBuilder.append(""+availablePaymentOption+","); 
					} 
				}
				String date1= QuotationObj.getCheckIn();
				String date2= QuotationObj.getCheckOut();
				QuotationObj.setCheckInDate(DateConversion.StringToDate(QuotationObj.getCheckIn()));
				QuotationObj.setCheckOutDate(DateConversion.StringToDate(QuotationObj.getCheckOut()));
				try {

					int noOfNights=CommonUtil.getNoofStayDays(date1, date2);
					QuotationObj.setNightCount(noOfNights);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				QuotationObj.setAvailablePaymentOption(stringBuilder.toString());
				QuotationObj.setAdditionalData(QuotationObj.getAdditionalData());
				QuotationObj.setHotelTravelRequest(hotelQuotationRequestNew);
				QuotationObj.setCompanyId(sessionUser.getCompanyid());
				QuotationObj.setUserId(CommonUtilSession.checkEmulatedUser(sessionmap)?CommonUtilSession.getEmulatedUserIdInt(sessionmap):sessionUser.getId());
				hotelTravelRequestQuotationList.add(QuotationObj);
				if(QuotationObj.getAvailablePaymentOptionList().contains("PrePaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PostPaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PayAtHotel")){
					paymentOptions.setPayAtHotel(true);
					paymentOptions.setPrePaid(true);
					paymentOptions.setPostPaid(true);
				}

				else if(QuotationObj.getAvailablePaymentOptionList().contains("PrePaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PostPaid")){
					paymentOptions.setPrePaid(true);
					paymentOptions.setPostPaid(true);
				}

				else if(QuotationObj.getAvailablePaymentOptionList().contains("PrePaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PayAtHotel")){
					paymentOptions.setPrePaid(true);
					paymentOptions.setPayAtHotel(true);
				}
				else if(QuotationObj.getAvailablePaymentOptionList().contains("PostPaid")&&QuotationObj.getAvailablePaymentOptionList().contains("PayAtHotel")){
					paymentOptions.setPostPaid(true);
					paymentOptions.setPayAtHotel(true);
				}
				else if(QuotationObj.getAvailablePaymentOptionList().contains("PostPaid")){
					paymentOptions.setPostPaid(true);

				}
				else if(QuotationObj.getAvailablePaymentOptionList().contains("PayAtHotel")){
					paymentOptions.setPayAtHotel(true);
				}
				else if(QuotationObj.getAvailablePaymentOptionList().contains("PrePaid")){
					paymentOptions.setPrePaid(true);
				}
				paymentOptions.setId(QuotationObj.getPaymentOptions().getId());

			}
		}
		HotelTravelRequestQuotation hotelTravelRequestQuotation=  hotelTravelRequestDao.updateHotelQuotationList(hotelTravelRequestQuotationList);
		if(hotelTravelRequestQuotation!=null){
			hotelTravelRequestDao.updatePaymentOptions(paymentOptions);
			hotelQuotationHistory.setHotelTravelRequestQuotation(hotelTravelRequestQuotation);
			hotelQuotationHistoryDao.insertHotelQuotationHistory(hotelQuotationHistory);
			 
			addActionMessage("Successfully Updated");
			return SUCCESS;
		}
		else{
			addActionError("We found some error.Please Try again.");
			return ERROR;
		} 


	}

	@Override
	public List<HotelTravelRequestQuotation> getModel() {
		// TODO Auto-generated method stub
		return hotelTravelRequestQuotation;
	}


	public Long getHotelQuotationRequestId() {
		return hotelQuotationRequestId;
	}

	public void setHotelQuotationRequestId(Long hotelQuotationRequestId) {
		this.hotelQuotationRequestId = hotelQuotationRequestId;
	}
	public List<HotelTravelRequestQuotation> getHotelQuotationList() {
		return hotelTravelRequestQuotation;
	}

	public void setHotelQuotationList(List<HotelTravelRequestQuotation> hotelTravelRequestQuotation) {
		this.hotelTravelRequestQuotation = hotelTravelRequestQuotation;
	}

	public List<HotelTravelRequestQuotation> getHotelRequestQuotationList() {
		if(hotelRequestQuotationList==null){
			hotelRequestQuotationList=new ArrayList<>();
		}
		return hotelRequestQuotationList;
	}

	public void setHotelRequestQuotationList(List<HotelTravelRequestQuotation> hotelRequestQuotationList) {
		this.hotelRequestQuotationList = hotelRequestQuotationList;
	}
	public HotelOrderRow getHotelOrderRow() {
		return hotelOrderRow;
	}

	public void setHotelOrderRow(HotelOrderRow hotelOrderRow) {
		this.hotelOrderRow = hotelOrderRow;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionmap = (SessionMap<String, Object>) arg0;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return this.request;
	}
	public HotelTravelRequestQuotation getHotelQuotation() {
		return hotelQuotation;
	}

	public List<HotelQuotationSchema> getHotelQuotationSchemas() {
		return hotelQuotationSchemas;
	}

	public void setHotelQuotationSchemas(List<HotelQuotationSchema> hotelQuotationSchemas) {
		this.hotelQuotationSchemas = hotelQuotationSchemas;
	}

	public void setHotelQuotation(HotelTravelRequestQuotation hotelQuotation) {
		this.hotelQuotation = hotelQuotation;
	}

	public HotelTravelRequest getHotelTravelRequest() {
		return hotelTravelRequest;
	}

	public void setHotelTravelRequest(HotelTravelRequest hotelTravelRequest) {
		this.hotelTravelRequest = hotelTravelRequest;
	}

	public int getHotelRequestTravelQuotationSize() {
		return hotelRequestTravelQuotationSize;
	}

	public void setHotelRequestTravelQuotationSize(int hotelRequestTravelQuotationSize) {
		this.hotelRequestTravelQuotationSize = hotelRequestTravelQuotationSize;
	}

	public HotelQuotationHistory getHotelQuotationHistory() {
		return hotelQuotationHistory;
	}

	public void setHotelQuotationHistory(HotelQuotationHistory hotelQuotationHistory) {
		this.hotelQuotationHistory = hotelQuotationHistory;
	}


}
