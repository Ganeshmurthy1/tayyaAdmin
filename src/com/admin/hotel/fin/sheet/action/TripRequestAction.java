package com.admin.hotel.fin.sheet.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.dao.CarTravelRequestDao;
import com.admin.common.quotation.dao.InsuranceTravelRequestDao;
import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.dao.VisaTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequest;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.admin.common.quotation.model.CarTravelRequest;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.admin.common.quotation.model.InsuranceTravelRequest;
import com.admin.common.quotation.model.InsuranceTravelRequestQuotation;
import com.admin.common.quotation.model.TrainTravelRequest;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.common.quotation.model.VisaTravelRequest;
import com.admin.common.quotation.model.VisaTravelRequestQuotation;
import com.admin.flight.fin.sheet.Dao.FlightTravelRequestDao;
import com.admin.flight.fin.sheet.model.FlightTravelRequestQuotation;
import com.admin.hotel.fin.sheet.Dao.HotelTravelRequestDao;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelTravelRequest;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.admin.miscellaneous.dao.MiscellaneousOrderDao;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.miscellaneous.model.MiscellaneousTravelRequest;
import com.isl.admin.filter.ApiProviderFilter;
import com.isl.admin.page.ApiProviderPage;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TripRequestAction extends ActionSupport implements ModelDriven<TripRequest>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TripRequestAction.class);
	SessionMap<String, Object> sessionMap;
	TripRequestDao tripRequestDao=new TripRequestDao();
	private TripRequest tripRequest = new TripRequest();
	private List<TripRequest> trips = new ArrayList<>();
	CarTravelRequestDao carTravelRequestDao=new CarTravelRequestDao();
	TrainTravelRequestDao trainTravelRequestDao=new TrainTravelRequestDao();
	BusTravelRequestDao busTravelRequestDao=new BusTravelRequestDao();
	InsuranceTravelRequestDao insuranceTravelRequestDao=new  InsuranceTravelRequestDao();
	VisaTravelRequestDao visaTravelRequestDao=new VisaTravelRequestDao();
	CarTravelRequestQuotation carTravelRequestQuotation=new CarTravelRequestQuotation();
	TrainTravelRequestQuotation trainTravelRequestQuotation=new TrainTravelRequestQuotation();
	BusTravelRequestQuotation busTravelRequestQuotation=new BusTravelRequestQuotation();
	InsuranceTravelRequestQuotation insuranceTravelRequestQuotation=new InsuranceTravelRequestQuotation();
	VisaTravelRequestQuotation visaTravelRequestQuotation=new VisaTravelRequestQuotation();
	private List<CarTravelRequestQuotation> carTravelRequestQuotationlist=new ArrayList<>();
	private List<TrainTravelRequestQuotation> trainTravelRequestQuotationlist=new ArrayList<>();
	private List<BusTravelRequestQuotation> busTravelRequestQuotationlist=new ArrayList<>();
	private List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotationlist=new ArrayList<>();
	private List<VisaTravelRequestQuotation> visaTravelRequestQuotationlist=new ArrayList<>();
	CarTravelRequest updatedcarTravelRequest = new CarTravelRequest();
	TrainTravelRequest trainTravelRequest = new  TrainTravelRequest(); 
	BusTravelRequest busTravelRequest = new BusTravelRequest(); 
	InsuranceTravelRequest insuranceTravelRequest = new  InsuranceTravelRequest(); 
	VisaTravelRequest visaTravelRequest = new VisaTravelRequest(); 
	private List<FlightTravelRequestQuotation> flightTravelRequestQuotaionList=null;
	FlightTravelRequestDao flightTravelRequestDao=new FlightTravelRequestDao();
	HotelTravelRequestDao hotelOrderRowFineSheetDao=new HotelTravelRequestDao();
	HotelTravelRequest hotelQuotationRow=new HotelTravelRequest();
	private List<HotelTravelRequestQuotation> hotelTravelRequestQuotationlist=null;
	private ApiProviderPage apiProviderPage=new ApiProviderPage();
	private List<Company> corporateCompanyList=new ArrayList<>();
	
	MiscellaneousOrderRow miscellaneousOrderRow=new MiscellaneousOrderRow();
	MiscellaneousTravelRequest miscellaneousTravelRequest=new MiscellaneousTravelRequest();
	MiscellaneousOrderDao miscellaneousOrderDao=new MiscellaneousOrderDao();
	
	public String goTrips(){
		User sessionUser = (User)sessionMap.get("User");
		User emulateByUser = (User)sessionMap.get("emulateByUser");	
		corporateCompanyList=new CompanyDAO().getCorporateCompanyList();
		ApiProviderFilter apiProviderFilter = apiProviderPage.getApiProviderFilter();
		apiProviderFilter.setLoginUser(sessionUser);
		apiProviderPage.setApiProviderFilter(apiProviderFilter);
		CompanyConfig currentuserCompanyConfig = new CompanyConfigDao().getConfigByComnpanyId(sessionUser.getCompanyid());	
		CompanyConfig emulateuserCompanyConfig = null;
		if(emulateByUser!=null && !emulateByUser.equals(""))
			 emulateuserCompanyConfig = new CompanyConfigDao().getConfigByComnpanyId(emulateByUser.getCompanyid());	

		if(sessionUser!=null && emulateByUser==null){	
			if(currentuserCompanyConfig!=null){
				ApiProviderPage newApiProviderPage=tripRequestDao.fetchFilterTripsList(apiProviderPage);
				if(newApiProviderPage!=null && newApiProviderPage.getTripRequestList()!=null){
					apiProviderPage=newApiProviderPage;
				}
				return SUCCESS;
			}
			else{
				//addActionError("We found some error.Please Create CompanyConfig");
				return ERROR;
			}
		}else if(emulateByUser!=null){
				if(currentuserCompanyConfig!=null && emulateuserCompanyConfig != null){
					ApiProviderPage newApiProviderPage=tripRequestDao.fetchFilterTripsList(apiProviderPage);
					if(newApiProviderPage!=null && newApiProviderPage.getTripRequestList()!=null){
						apiProviderPage=newApiProviderPage;
					}
					return SUCCESS;
					 
				}else{
					//addActionError("We found some error.Please Create CompanyConfig");
					return ERROR;
				}			
			
		}else{
			//addActionError("We found some error.Please Create CompanyConfig");
			return ERROR;
		}
	}
 
	public String showTripDetails() {
		if (tripRequest.getId() != null) {
			tripRequest = tripRequestDao.getTripRequestById(tripRequest.getId());
			if(tripRequest.getFlightTravelRequest()!=null){
			flightTravelRequestQuotaionList=flightTravelRequestDao.getFlightTravelRequestQuotationBookedList(tripRequest.getFlightTravelRequest().getId());
			List<FlightTravelRequestQuotation>  flightTravelRequestQuotaionListNew=new ArrayList<>();
			for (FlightTravelRequestQuotation flightTravelRequestQuotation : flightTravelRequestQuotaionList) {
				FlightOrderRow flightOrderRowNew=flightTravelRequestQuotation.getFlightOrderRow();
				flightOrderRowNew.setFinalPrice(flightTravelRequestQuotation.getFlightOrderRow().getFinalPrice().setScale(2,BigDecimal.ROUND_UP));
				flightTravelRequestQuotation.setFlightOrderRow(flightOrderRowNew);;
				flightTravelRequestQuotaionListNew.add(flightTravelRequestQuotation);
			
			}
			setFlightTravelRequestQuotaionList(flightTravelRequestQuotaionListNew);
			}
			if(tripRequest.getHotelTravelRequest()!=null){
			hotelTravelRequestQuotationlist=hotelOrderRowFineSheetDao.getHotelRequestTravelQuotationBookedList(tripRequest.getHotelTravelRequest().getId());
			}
			if(tripRequest != null){
				if(tripRequest.getCarTravelRequest()!=null)
					getCarTravelRequestDetails();
				if(tripRequest.getTrainTravelRequest()!=null)
					getTrainTravelRequestDetails();
				if(tripRequest.getBusTravelRequest()!=null)
					getBusTravelRequestDetails();
				if(tripRequest.getInsuranceTravelRequest()!=null)
					getInsuranceTravelRequestDetails();
				if(tripRequest.getVisaTravelRequest()!=null)
					getVisaTravelRequestDetails();
				if(tripRequest.getMiscellaneousTravelRequest()!=null)
					getMiscellaneousOrderRequestDetails();

			}
		}
		return SUCCESS;
	}


	public String getCarTravelRequestDetails()
	{
		updatedcarTravelRequest = carTravelRequestDao.getCarTravelRequestDetails(tripRequest.getCarTravelRequest().getId());
		carTravelRequestQuotationlist=carTravelRequestDao.getCarRequestTravelQuotationBookedList(updatedcarTravelRequest.getId());
		return SUCCESS;
	}

	public String getTrainTravelRequestDetails()
	{
		trainTravelRequest=trainTravelRequestDao.getTrainTravelRequestDetails(tripRequest.getTrainTravelRequest().getId());
		trainTravelRequestQuotationlist=trainTravelRequestDao.getTrainRequestTravelQuotationBookedList(trainTravelRequest.getId());
		return SUCCESS;
	}
	public String getBusTravelRequestDetails()
	{
		busTravelRequest=busTravelRequestDao.getBusTravelRequestDetails(tripRequest.getBusTravelRequest().getId());
		busTravelRequestQuotationlist=busTravelRequestDao.getBusRequestTravelQuotationBookedList(busTravelRequest.getId());
		return SUCCESS;
	}
	public String getInsuranceTravelRequestDetails()
	{
		insuranceTravelRequest=insuranceTravelRequestDao.getInsuranceTravelRequestDetails(tripRequest.getInsuranceTravelRequest().getId());
		insuranceTravelRequestQuotationlist=insuranceTravelRequestDao.getInsuranceRequestTravelQuotationBookedList(insuranceTravelRequest.getId());
		return SUCCESS;
	}
	public String getVisaTravelRequestDetails()
	{
		visaTravelRequest=visaTravelRequestDao.getVisaTravelRequestDetails(tripRequest.getVisaTravelRequest().getId());
		visaTravelRequestQuotationlist=visaTravelRequestDao.getVisaRequestTravelQuotationBookedList(visaTravelRequest.getId());
		return SUCCESS;
	}
	public String getMiscellaneousOrderRequestDetails()
	{
		miscellaneousTravelRequest=miscellaneousOrderDao.getMiscellaneousTravelRequestById(tripRequest.getMiscellaneousTravelRequest().getId());
		miscellaneousOrderRow=miscellaneousOrderDao.getMiscellaneousOrderRowByConfirmationNumber(miscellaneousTravelRequest.getConfirmationNumber());
		return SUCCESS;
	}



	public List<TripRequest> getTrips() {
		return trips;
	}
	public void setTrips(List<TripRequest> trips) {
		this.trips = trips;
	}

	@Override
	public TripRequest getModel() {
		return tripRequest;
	}
	public TripRequest getTripRequest() {
		return tripRequest;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	public void setTripRequest(TripRequest tripRequest) {
		this.tripRequest = tripRequest;
	}


	public CarTravelRequest getCarTravelRequest() {
		return updatedcarTravelRequest;
	}


	public void setCarTravelRequest(CarTravelRequest carTravelRequest) {
		this.updatedcarTravelRequest = carTravelRequest;
	}


	public List<CarTravelRequestQuotation> getCarTravelRequestQuotationlist() {
		return carTravelRequestQuotationlist;
	}


	public void setCarTravelRequestQuotationlist(List<CarTravelRequestQuotation> carTravelRequestQuotationlist) {
		this.carTravelRequestQuotationlist = carTravelRequestQuotationlist;
	}


	public CarTravelRequestQuotation getCarTravelRequestQuotation() {
		return carTravelRequestQuotation;
	}


	public void setCarTravelRequestQuotation(CarTravelRequestQuotation carTravelRequestQuotation) {
		this.carTravelRequestQuotation = carTravelRequestQuotation;
	}


	public TrainTravelRequestDao getTrainTravelRequestDao() {
		return trainTravelRequestDao;
	}


	public void setTrainTravelRequestDao(TrainTravelRequestDao trainTravelRequestDao) {
		this.trainTravelRequestDao = trainTravelRequestDao;
	}


	public TrainTravelRequestQuotation getTrainTravelRequestQuotation() {
		return trainTravelRequestQuotation;
	}


	public void setTrainTravelRequestQuotation(TrainTravelRequestQuotation trainTravelRequestQuotation) {
		this.trainTravelRequestQuotation = trainTravelRequestQuotation;
	}


	public BusTravelRequestQuotation getBusTravelRequestQuotation() {
		return busTravelRequestQuotation;
	}


	public void setBusTravelRequestQuotation(BusTravelRequestQuotation busTravelRequestQuotation) {
		this.busTravelRequestQuotation = busTravelRequestQuotation;
	}


	public InsuranceTravelRequestQuotation getInsuranceTravelRequestQuotation() {
		return insuranceTravelRequestQuotation;
	}


	public void setInsuranceTravelRequestQuotation(InsuranceTravelRequestQuotation insuranceTravelRequestQuotation) {
		this.insuranceTravelRequestQuotation = insuranceTravelRequestQuotation;
	}


	public VisaTravelRequestQuotation getVisaTravelRequestQuotation() {
		return visaTravelRequestQuotation;
	}


	public void setVisaTravelRequestQuotation(VisaTravelRequestQuotation visaTravelRequestQuotation) {
		this.visaTravelRequestQuotation = visaTravelRequestQuotation;
	}


	public List<TrainTravelRequestQuotation> getTrainTravelRequestQuotationlist() {
		return trainTravelRequestQuotationlist;
	}


	public void setTrainTravelRequestQuotationlist(List<TrainTravelRequestQuotation> trainTravelRequestQuotationlist) {
		this.trainTravelRequestQuotationlist = trainTravelRequestQuotationlist;
	}


	public List<BusTravelRequestQuotation> getBusTravelRequestQuotationlist() {
		return busTravelRequestQuotationlist;
	}


	public void setBusTravelRequestQuotationlist(List<BusTravelRequestQuotation> busTravelRequestQuotationlist) {
		this.busTravelRequestQuotationlist = busTravelRequestQuotationlist;
	}


	public List<InsuranceTravelRequestQuotation> getInsuranceTravelRequestQuotationlist() {
		return insuranceTravelRequestQuotationlist;
	}


	public void setInsuranceTravelRequestQuotationlist(
			List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotationlist) {
		this.insuranceTravelRequestQuotationlist = insuranceTravelRequestQuotationlist;
	}


	public List<VisaTravelRequestQuotation> getVisaTravelRequestQuotationlist() {
		return visaTravelRequestQuotationlist;
	}


	public void setVisaTravelRequestQuotationlist(List<VisaTravelRequestQuotation> visaTravelRequestQuotationlist) {
		this.visaTravelRequestQuotationlist = visaTravelRequestQuotationlist;
	}


	public TrainTravelRequest getTrainTravelRequest() {
		return trainTravelRequest;
	}


	public void setTrainTravelRequest(TrainTravelRequest trainTravelRequest) {
		this.trainTravelRequest = trainTravelRequest;
	}


	public BusTravelRequest getBusTravelRequest() {
		return busTravelRequest;
	}


	public void setBusTravelRequest(BusTravelRequest busTravelRequest) {
		this.busTravelRequest = busTravelRequest;
	}


	public InsuranceTravelRequest getInsuranceTravelRequest() {
		return insuranceTravelRequest;
	}


	public void setInsuranceTravelRequest(InsuranceTravelRequest insuranceTravelRequest) {
		this.insuranceTravelRequest = insuranceTravelRequest;
	}


	public VisaTravelRequest getVisaTravelRequest() {
		return visaTravelRequest;
	}


	public void setVisaTravelRequest(VisaTravelRequest visaTravelRequest) {
		this.visaTravelRequest = visaTravelRequest;
	}


	public List<FlightTravelRequestQuotation> getFlightTravelRequestQuotaionList() {
		return flightTravelRequestQuotaionList;
	}


	public void setFlightTravelRequestQuotaionList(List<FlightTravelRequestQuotation> flightTravelRequestQuotaionList) {
		this.flightTravelRequestQuotaionList = flightTravelRequestQuotaionList;
	}


	public List<HotelTravelRequestQuotation> getHotelTravelRequestQuotationlist() {
		return hotelTravelRequestQuotationlist;
	}


	public void setHotelTravelRequestQuotationlist(List<HotelTravelRequestQuotation> hotelTravelRequestQuotationlist) {
		this.hotelTravelRequestQuotationlist = hotelTravelRequestQuotationlist;
	}


	public ApiProviderPage getApiProviderPage() {
		return apiProviderPage;
	}


	public void setApiProviderPage(ApiProviderPage apiProviderPage) {
		this.apiProviderPage = apiProviderPage;
	}
	public List<Company> getCorporateCompanyList() {
		return corporateCompanyList;
	}
	public void setCorporateCompanyList(List<Company> corporateCompanyList) {
		this.corporateCompanyList = corporateCompanyList;
	}

	public MiscellaneousOrderRow getMiscellaneousOrderRow() {
		return miscellaneousOrderRow;
	}

	public MiscellaneousTravelRequest getMiscellaneousTravelRequest() {
		return miscellaneousTravelRequest;
	}

	public void setMiscellaneousOrderRow(MiscellaneousOrderRow miscellaneousOrderRow) {
		this.miscellaneousOrderRow = miscellaneousOrderRow;
	}

	public void setMiscellaneousTravelRequest(MiscellaneousTravelRequest miscellaneousTravelRequest) {
		this.miscellaneousTravelRequest = miscellaneousTravelRequest;
	}

}
