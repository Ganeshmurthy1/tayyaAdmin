package com.admin.flight.fin.sheet.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.util.CommonUtilSession;
import com.admin.flight.fin.sheet.Dao.FlightTravelRequestDao;
import com.admin.flight.fin.sheet.model.FlightTravelRequest;
import com.admin.flight.fin.sheet.model.FlightTravelRequestQuotation;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.User;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

public class FlightTravelRequestAction extends ActionSupport implements ModelDriven<FlightTravelRequest>,SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FlightTravelRequest flightTravelRequest=new FlightTravelRequest();
	SessionMap<String, Object> sessionMap;
	FlightTravelRequestDao flightTravelRequestDao=new FlightTravelRequestDao();
	private List<FlightTravelRequest> flightQuotationRowlist=null;
	private List<FlightTravelRequestQuotation> flightTravelRequestQuotaionList=null;
	private List<Country> countryList=null;
	private List<Airlinelist> airlineList=null;
	private Long flightQuotationRequestId;
	private Long tripId;
	TripRequestDao tripRequestDao = new TripRequestDao();
	RmConfigModel rmConfigModel = new RmConfigModel();
	List<String> fieldName = new ArrayList<String>();
	RmConfigDao rmConfigDao = new RmConfigDao();
	RmConfigTripDetailsModel configTripDetailsModel = new RmConfigTripDetailsModel();

	public String goFlightTravelRequest(){

		Company sessionCompany=(Company) sessionMap.get("Company");		
		RmConfigDao rmConfigDao=new RmConfigDao();
		try{
			setRmConfigModel(rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid()));
			String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("") ?rmConfigModel.getDynamicFieldsData().split(","):null;
			if(manualStringFields!=null && manualStringFields.length>0){
			for(String oneField:manualStringFields){
				String fieldsName[]=oneField.split(":");
				fieldName.add(fieldsName[0]);
			}
			}
		}catch (Exception e) {
		}
		if(tripId != null){
			TripRequest tripRequest = tripRequestDao.getTripRequestById(tripId);
			configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
		}
		List<Country> countryList=new CountryDao().getCountryList();
		List<Airlinelist> airportlist=new  CountryDao().getAirlineList();
		if(countryList!=null && countryList.size()>0) 
			setCountryList(countryList);
		if(airportlist!=null && airportlist.size()>0) 
			setAirlineList(airportlist);
		setTripId(tripId);


		return SUCCESS;

	}

	public String  createFlightTravelRequest(){
		flightTravelRequest.setDepartureDate(DateConversion.StringToDate(flightTravelRequest.getTranDepartureDate()));
		flightTravelRequest.setArrivalDate(DateConversion.StringToDate(flightTravelRequest.getTranArrivalDate()));

		/*if(flightTravelRequest.getTripType().equals("R") || flightTravelRequest.getTripType().equals("M")){
			 flightTravelRequest.setArrivalDate(DateConversion.StringToDate(flightTravelRequest.getTranArrivalDate()));
		}*/
		flightTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
		User sessionUser=(User) sessionMap.get("User");
		Company sessionCompany=(Company) sessionMap.get("Company");
		flightTravelRequest.setCreated_by_userId(sessionUser.getId());
		flightTravelRequest.setCompanyId(sessionUser.getCompanyid());
		flightTravelRequest.setUpdated_by_userId(sessionUser.getId());
		FlightTravelRequest flightTravelRequestNew=flightTravelRequestDao.insertFlightQuotationRowDetails(flightTravelRequest);

		if(flightTravelRequestNew!=null){
			/*configTripDetailsModel.setTrNumber(flightTravelRequestNew.getTravelRequestNumber());
			configTripDetailsModel.setEmpCode(flightTravelRequestNew.getCustomerNo());
			configTripDetailsModel.setCostCenter(flightTravelRequestNew.getCostCenter());*/
			setFlightQuotationRequestId(flightTravelRequestNew.getId());
			/*boolean isCompanyRmConfig=rmConfigDao.isCompanyHavingRmConfig(sessionCompany);*/
			if(tripId!=null)
			{
				TripRequest tripRequest =new TripRequest();
				tripRequest.setId(tripId);
				tripRequest.setFlightTravelRequest(flightTravelRequestNew);
				tripRequest.setCompanyId(sessionUser.getCompanyid());
				tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
				tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
				tripRequestDao.updateTripRequestById(tripRequest);
				/*configTripDetailsModel.setTripId(tripRequest.getTripId());*/
				/*if(isCompanyRmConfig){
					RmConfigTripDetailsModel rmconfigTripDetailsModel = new GetNewRmConfigDetail().getRmConfigDetail(configTripDetailsModel,"Flight");
					rmConfigDao.insertTripConfigDetails(rmconfigTripDetailsModel);
				}*/
				if(tripRequest!=null && tripRequest.getId()>0){
					addActionMessage("Successfully created");
				}
			}
			else{
				TripRequest tripRequest =new TripRequest();
				tripRequest.setFlightTravelRequest(flightTravelRequestNew);
				tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
				//tripRequest.setTripId(RandomConfigurationNumber.generateTripId(flightTravelRequestNew.getId()));
				tripRequest.setCompanyId(sessionUser.getCompanyid());
				tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
				tripRequest=tripRequestDao.insertTripRequest(tripRequest);
				if(tripRequest!=null && tripRequest.getId()>0){
					tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
					/*configTripDetailsModel.setTripId(tripRequest.getTripId());*/
					/*if(isCompanyRmConfig){
						RmConfigTripDetailsModel rmconfigTripDetailsModel = new GetNewRmConfigDetail().getRmConfigDetail(configTripDetailsModel,"Flight");
						rmConfigDao.insertTripConfigDetails(rmconfigTripDetailsModel);
					}*/
					if(tripRequestDao.updateTripRequestNumber(tripRequest))
						addActionMessage("Successfully created");
					else
						addActionMessage("Something went wrong.Please wait.");	

				}
			}
			new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(flightTravelRequestNew.getId()),"Flight book Request created",String.valueOf(flightTravelRequest.getCustomerComments()),NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),false,false,false,true,false,false);
			return SUCCESS;
		}
		else{
			addActionError("We found some error.Please Try again.");
			return ERROR;
		} 
	}

	public String getFlightTravelRequestList(){
		User sessionUser=(User)sessionMap.get("User");
		List<FlightTravelRequest> list=flightTravelRequestDao.loadFlightQuotationRowList(sessionUser);
		if(list!=null && list.size()>0){
			setFlightQuotationRowlist(list);
		}
		return SUCCESS;
	}

	public String goFlightRequestEdit(){
		Company sessionCompany=(Company) sessionMap.get("Company");		
		FlightTravelRequest flightTravelRequestNew = flightTravelRequestDao.getFlightTravelRequestDetails(flightTravelRequest.getId());
		if(flightTravelRequestNew!=null){
			setFlightTravelRequest(flightTravelRequestNew);
			RmConfigDao rmConfigDao=new RmConfigDao();
			try{
				setRmConfigModel(rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid()));
				String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("") ?rmConfigModel.getDynamicFieldsData().split(","):null;
				if(manualStringFields.length>0){
				for(String oneField:manualStringFields){
					String fieldsName[]=oneField.split(":");
					fieldName.add(fieldsName[0]);
				}
				}
			}catch (Exception e) {
			}
			TripRequest tripRequest = tripRequestDao.getTripRequestTripNumber(flightTravelRequestNew.getId(),"F");
			configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
			tripId = tripRequest.getTripId();
		}


		List<Country> countryList=new CountryDao().getCountryList();
		List<Airlinelist> airportlist=new  CountryDao().getAirlineList();
		if(countryList!=null && countryList.size()>0) 
			setCountryList(countryList);
		if(airportlist!=null && airportlist.size()>0) 
			setAirlineList(airportlist);
		return SUCCESS;
	}


	public String updateFlightTravelRequest(){
		flightTravelRequest.setDepartureDate(DateConversion.StringToDate(flightTravelRequest.getTranDepartureDate()));
		if(flightTravelRequest.getTripType().equals("R") || flightTravelRequest.getTripType().equals("M")){
			flightTravelRequest.setArrivalDate(DateConversion.StringToDate(flightTravelRequest.getTranArrivalDate()));
		}
		User sessionUser=(User) sessionMap.get("User");
		flightTravelRequest.setUpdated_by_userId(sessionUser.getId());
		FlightTravelRequest flightTravelRequestNew = flightTravelRequestDao.updateFlightTravelRequest(flightTravelRequest);

		if(flightTravelRequestNew!=null){
			if(tripId!=null)
			{
				TripRequest tripRequest =new TripRequest();
				tripRequest.setId(tripId);
				tripRequest.setFlightTravelRequest(flightTravelRequestNew);
				tripRequest.setCompanyId(sessionUser.getCompanyid());
				tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
				tripRequestDao.updateTripRequestById(tripRequest);
				if(tripRequest!=null && tripRequest.getId()>0){
					addActionMessage("Successfully created");
				}
			}
			else{
				TripRequest tripRequest =new TripRequest();
				tripRequest.setFlightTravelRequest(flightTravelRequestNew);
				tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
				tripRequest.setTripId(RandomConfigurationNumber.generateTripId(flightTravelRequestNew.getId()));
				tripRequest.setCompanyId(sessionUser.getCompanyid());
				tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
				tripRequest=tripRequestDao.insertTripRequest(tripRequest);
				if(tripRequest!=null && tripRequest.getId()>0){
					addActionMessage("Successfully created");
				}
			}
			new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(flightTravelRequestNew.getId()),"Flight book Request created",String.valueOf(flightTravelRequest.getCustomerComments()),NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),false,false,false,true,false,false);
			return SUCCESS;
		}
		else{
			addActionError("We found some error.Please Try again.");
			return ERROR;
		} 
	}

	public String getFlightTravelRequestDetails(){
		flightTravelRequest=flightTravelRequestDao.getFlightTravelRequestDetails(flightTravelRequest.getId());
		List<Country> list=new CountryDao().getCountryList();
		if(list!=null && list.size()>0){
			countryList=new CountryDao().getCountryList();
		}
		flightTravelRequestQuotaionList=flightTravelRequestDao.getFlightTravelRequestQuotationBookedList(flightTravelRequest.getId());
		List<FlightTravelRequestQuotation>  flightTravelRequestQuotaionListNew=new ArrayList<>();
		for (FlightTravelRequestQuotation flightTravelRequestQuotation : flightTravelRequestQuotaionList) {
			FlightOrderRow flightOrderRowNew = flightTravelRequestQuotation.getFlightOrderRow();			
			FlightOrderRow returnflightOrderRow = null;
			if(flightTravelRequestQuotation.getTripType().equalsIgnoreCase("R")){
				returnflightOrderRow = flightTravelRequestQuotation.getFlightOrderRow();			
				if(returnflightOrderRow != null){
					flightOrderRowNew.setFinalPrice((flightTravelRequestQuotation.getFlightOrderRow().getFinalPrice().add(flightTravelRequestQuotation.getReturnflightOrderRow().getFinalPrice())).setScale(2,BigDecimal.ROUND_UP));
				}else{
					flightOrderRowNew.setFinalPrice(flightTravelRequestQuotation.getFlightOrderRow().getFinalPrice().setScale(2,BigDecimal.ROUND_UP));
				}
			}else{
				flightOrderRowNew.setFinalPrice(flightTravelRequestQuotation.getFlightOrderRow().getFinalPrice().setScale(2,BigDecimal.ROUND_UP));
			}


			flightTravelRequestQuotation.setFlightOrderRow(flightOrderRowNew);
			flightTravelRequestQuotation.setReturnflightOrderRow(returnflightOrderRow);
			flightTravelRequestQuotaionListNew.add(flightTravelRequestQuotation);

		}
		setFlightTravelRequestQuotaionList(flightTravelRequestQuotaionListNew);
		return SUCCESS;
	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public FlightTravelRequest getModel() {
		// TODO Auto-generated method stub
		return flightTravelRequest;
	}



	public FlightTravelRequest getFlightTravelRequest() {
		return flightTravelRequest;
	}

	public void setFlightTravelRequest(FlightTravelRequest flightTravelRequest) {
		this.flightTravelRequest = flightTravelRequest;
	}

	public List<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<FlightTravelRequest> getFlightQuotationRowlist() {
		return flightQuotationRowlist;
	}

	public void setFlightQuotationRowlist(List<FlightTravelRequest> flightQuotationRowlist) {
		this.flightQuotationRowlist = flightQuotationRowlist;
	}

	public List<Airlinelist> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(List<Airlinelist> airlineList) {
		this.airlineList = airlineList;
	}

	public Long getFlightQuotationRequestId() {
		return flightQuotationRequestId;
	}

	public void setFlightQuotationRequestId(Long flightQuotationRequestId) {
		this.flightQuotationRequestId = flightQuotationRequestId;
	}

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public List<FlightTravelRequestQuotation> getFlightTravelRequestQuotaionList() {
		return flightTravelRequestQuotaionList;
	}

	public void setFlightTravelRequestQuotaionList(List<FlightTravelRequestQuotation> flightTravelRequestQuotaionList) {
		this.flightTravelRequestQuotaionList = flightTravelRequestQuotaionList;
	}

	public RmConfigModel getRmConfigModel() {
		return rmConfigModel;
	}

	public void setRmConfigModel(RmConfigModel rmConfigModel) {
		this.rmConfigModel = rmConfigModel;
	}

	public List<String> getFieldName() {
		return fieldName;
	}

	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}

	public RmConfigTripDetailsModel getConfigTripDetailsModel() {
		return configTripDetailsModel;
	}

	public void setConfigTripDetailsModel(RmConfigTripDetailsModel configTripDetailsModel) {
		this.configTripDetailsModel = configTripDetailsModel;
	}


}