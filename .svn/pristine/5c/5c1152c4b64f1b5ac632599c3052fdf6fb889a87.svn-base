package com.admin.flight.fin.sheet.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.util.CommonUtilSession;
import com.admin.flight.fin.sheet.Dao.FlightTravelRequestDao;
import com.admin.flight.fin.sheet.model.FlightTravelRequest;
import com.admin.flight.fin.sheet.model.FlightTravelRequestQuotation;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelQuotationSchema;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FlightTravelRequestQuotationAction extends ActionSupport implements SessionAware,ModelDriven<List<FlightTravelRequestQuotation>>{
	FlightTravelRequestDao flightTravelRequestDao = new FlightTravelRequestDao();
	FlightOrderRow flightOrderRow=null;
	private List<HotelQuotationSchema> hotelQuotationSchemas = null;
	private static final long serialVersionUID = 1L;	
	List<FlightTravelRequestQuotation> flightRequestQuotationList=new ArrayList<FlightTravelRequestQuotation>();
	private List<Airlinelist> airlineList;
	private Long flightQuotationRequestId;
	private FlightTravelRequestQuotation flightQuotation=null;
	private FlightTravelRequest flightTravelRequest=null;
	SessionMap<String, Object> sessionMap;
	
	private int flightRequestTravelQuotationSize=0;
	
	public String goFlightRequestTravelQuotation(){
		airlineList=new CountryDao().getAirlineList();
		setFlightQuotationRequestId(flightQuotationRequestId);
		FlightTravelRequest flightTravelRequestNew=flightTravelRequestDao.getFlightTravelRequestDetails(flightQuotationRequestId);
		if(flightTravelRequestNew!=null){
			setFlightTravelRequest(flightTravelRequestNew);
		}
		if(flightTravelRequest!=null){
			List<FlightTravelRequestQuotation> flightQuotationList=  flightTravelRequestDao.getFlightRequestTravelQuotationList(flightQuotationRequestId);
			if(flightQuotationList!=null && flightQuotationList.size()>0)
				flightRequestTravelQuotationSize =flightQuotationList.size(); 
		}
		
		return SUCCESS;
	}

	public String createFlightRequestTravelQuotation(){
		User sessionUser=(User) sessionMap.get("User");
		FlightTravelRequest flightQuotationRequestNew=flightTravelRequestDao.getFlightTravelRequestDetails(flightQuotationRequestId);
		int userId = CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId();
		boolean isInserted=  flightTravelRequestDao.insertFlightQuotationList(flightRequestQuotationList, flightQuotationRequestNew,userId,sessionUser.getCompanyid());
		if(isInserted){
			setFlightQuotationRequestId(flightQuotationRequestId);
			addActionMessage("Successfully created");
			return SUCCESS;
		}
		else{
			addActionError("We found some error.Please Try again.");
			return ERROR;
		}
	}


	public String getFlightRequestTravelQuotationList(){
		List<FlightTravelRequestQuotation> flightQuotationList=  flightTravelRequestDao.getFlightRequestTravelQuotationList(flightQuotationRequestId);
		/*long flightOrderRowId=flightTravelRequestDao.getFlightOrderRowIdFromFlightTravelRequestQuotation(flightQuotationRequestId);
		if(flightOrderRowId!=0){
			flightOrderRow =new FlightOrderDao().getFlightOrderRowInfo(flightOrderRowId);
		}*/
		List<FlightTravelRequestQuotation>  updatedflightQuotationList = new ArrayList<>();
	 
		if(flightQuotationList!=null && flightQuotationList.size()>0){
			for (FlightTravelRequestQuotation flightTravelRequestQuotation : flightQuotationList) {
				TripRequest tripRequest=null;
				 String onlineBookingUrl = getText("global.ibeurl");  
				 String dammytext = (String) sessionMap.get("Encryptedid");
				 if(flightTravelRequestQuotation.getFlightTravelRequest()!=null)
					 tripRequest=new TripRequestDao().getTripRequestTripNumber(flightTravelRequestQuotation.getFlightTravelRequest().getId(),"F");
				   try {
					  long  tripId =tripRequest!=null && tripRequest.getTripId()>0?tripRequest.getTripId():0;
					onlineBookingUrl = onlineBookingUrl+"?myaction=FlightQuotePriceSearch&flightQuotationRequestId="+flightTravelRequestQuotation.getId()+"&dammytext="+URLEncoder.encode(dammytext,"UTF-8")+"&tripNumber="+tripId;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			     flightTravelRequestQuotation.setOnlineUrl(onlineBookingUrl);
			     updatedflightQuotationList.add(flightTravelRequestQuotation);
			}
			flightRequestQuotationList = updatedflightQuotationList;
		}
		airlineList=new CountryDao().getAirlineList();
		return SUCCESS;
	}

	public String  goFlightTravelQuotationEdit(){
		FlightTravelRequestQuotation quotationObj =flightTravelRequestDao.getFlightTravelRequestQuotationDetails(flightQuotationRequestId);

		getFlightQuotationSchema(quotationObj.getAdditionalData(),getHotelQuotationSchemas());
		if(quotationObj!=null){
			setFlightQuotation(quotationObj);
			airlineList=new CountryDao().getAirlineList();

		}
		if(quotationObj!=null){
			List<FlightTravelRequestQuotation> flightQuotationList=  flightTravelRequestDao.getFlightRequestTravelQuotationList(quotationObj.getId());
			if(flightQuotationList!=null && flightQuotationList.size()>0)
				flightRequestTravelQuotationSize =flightQuotationList.size(); 
		}
		return SUCCESS;
	}

	public  void  getFlightQuotationSchema(String additionalData, List<HotelQuotationSchema> hotelQuotationSchemas){
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


	public String  flightQuotationUpdate(){
		FlightTravelRequest flightQuotationRequestNew=flightTravelRequestDao.getFlightTravelRequestDetails(flightQuotationRequestId);
		List<FlightTravelRequestQuotation> flightTravelRequestQuotationList= new ArrayList<>();
		if(flightRequestQuotationList!=null && flightRequestQuotationList.size()>0){
			for(FlightTravelRequestQuotation flightQuotationObj:flightRequestQuotationList){
				//flightQuotationObj.setTravelRequestDate(DateConversion.StringToDate(flightQuotationObj.getTransTravelRequestDate()));
				flightQuotationObj.setFlightTravelRequest(flightQuotationRequestNew);
				flightTravelRequestQuotationList.add(flightQuotationObj);
			}
		}
		boolean isUpdated=  flightTravelRequestDao.updateFlightQuotationList(flightTravelRequestQuotationList);
		if(isUpdated){
			setFlightQuotationRequestId(flightQuotationRequestNew.getId());
			addActionMessage("Successfully Updated");
			return SUCCESS;
		}
		else{
			addActionError("We found some error.Please Try again.");
			return ERROR;
		} 


	}

	@Override
	public List<FlightTravelRequestQuotation> getModel() {
		// TODO Auto-generated method stub
		return flightRequestQuotationList;
	}


	public Long getFlightQuotationRequestId() {
		return flightQuotationRequestId;
	}


	public void setFlightQuotationRequestId(Long flightQuotationRequestId) {
		this.flightQuotationRequestId = flightQuotationRequestId;
	}


	public List<FlightTravelRequestQuotation> getFlightRequestQuotationList() {
		return flightRequestQuotationList;
	}


	public void setFlightRequestQuotationList(List<FlightTravelRequestQuotation> flightRequestQuotationList) {
		this.flightRequestQuotationList = flightRequestQuotationList;
	}

	public List<Airlinelist> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(List<Airlinelist> airlineList) {
		this.airlineList = airlineList;
	}

	public FlightOrderRow getFlightOrderRow() {
		return flightOrderRow;
	}

	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}
	public FlightTravelRequestQuotation getFlightQuotation() {
		return flightQuotation;
	}

	public void setFlightQuotation(FlightTravelRequestQuotation flightQuotation) {
		this.flightQuotation = flightQuotation;
	}
	public List<HotelQuotationSchema> getHotelQuotationSchemas() {
		if(hotelQuotationSchemas==null){
			hotelQuotationSchemas=new ArrayList<>();
		}
		return hotelQuotationSchemas;
	}

	public void setHotelQuotationSchemas(List<HotelQuotationSchema> hotelQuotationSchemas) {
		this.hotelQuotationSchemas = hotelQuotationSchemas;
	}
	public FlightTravelRequest getFlightTravelRequest() {
		return flightTravelRequest;
	}

	public void setFlightTravelRequest(FlightTravelRequest flightTravelRequest) {
		this.flightTravelRequest = flightTravelRequest;
	}

	public int getFlightRequestTravelQuotationSize() {
		return flightRequestTravelQuotationSize;
	}

	public void setFlightRequestTravelQuotationSize(int flightRequestTravelQuotationSize) {
		this.flightRequestTravelQuotationSize = flightRequestTravelQuotationSize;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap=(SessionMap<String, Object>) arg0;
	}
}
