package com.admin.common.expense.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;

import com.admin.common.expense.dao.TripExpenseDao;
import com.admin.common.expense.model.BusExpense;
import com.admin.common.expense.model.CarExpense;
import com.admin.common.expense.model.TripExepense;
import com.admin.common.expense.model.FlightExpense;
import com.admin.common.expense.model.HotelExpense;
import com.admin.common.expense.model.InsuranceExpense;
import com.admin.common.expense.model.LaborExpense;
import com.admin.common.expense.model.MealExpense;
import com.admin.common.expense.model.MiscellaneousExpense;
import com.admin.common.expense.model.TrainExpense;
import com.admin.common.expense.model.VisaExpense;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Country;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * author saumya
 */
public class TripExpenseCommonAction extends ActionSupport implements ModelDriven<TripExepense> {

	private static final long serialVersionUID = 1L;
	List<TripRequest> tripRequests;
	TripExepense tripExepense = new TripExepense();
	TripExepense expenseToView=new TripExepense();
	private FlightExpense flightExpense = new FlightExpense();
	private HotelExpense hotelExpense=new HotelExpense();
	private CarExpense carExpense=new CarExpense();
	private TrainExpense trainExpense=new TrainExpense();
	private BusExpense busExpense=new BusExpense();
	private MiscellaneousExpense miscellaneousExpense=new MiscellaneousExpense();
	private MealExpense mealExpense=new MealExpense();
	private LaborExpense laborExpense=new LaborExpense();
	private VisaExpense visaExpense =new VisaExpense();
	private InsuranceExpense insuranceExpense= new InsuranceExpense();
	TripExpenseDao tripExpenseDao = new TripExpenseDao();
	List<TripExepense> tripExepenses;
	private List<Country> CountryList;
	CountryDao cDAO = new CountryDao();
	private Long tripId;
	TripRequestDao tripRequestDao=new TripRequestDao();
	List<FlightExpense> flightExpenses=new ArrayList<>();
	List<HotelExpense> hotelExpenses=new ArrayList<>();
	List<CarExpense> carExpenses=new ArrayList<>();
	List<BusExpense> busExpenses=new ArrayList<>();
	List<TrainExpense> trainExpenses=new ArrayList<>();
	List<InsuranceExpense> insuranceExpenses=new ArrayList<>();
	List<VisaExpense> visaExpenses=new ArrayList<>();
	//private long id;
	private long expid;
	

	
	public String createFlightExpense(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			
			if (flightExpense != null && !flightExpense.getDeparturalDate().equalsIgnoreCase("") && !flightExpense.getArrivalDate().equalsIgnoreCase("")) {        
				flightExpense.setArrivDate(flightExpense.getArrivalDate());
				flightExpense.setDepartureDate(flightExpense.getDeparturalDate());
			}
			//logger.info("getExpFilePath()-------"+getExpFilePath());
			String imageName=uploadFile(getExpFilePath(),"flightExpense");
	
			flightExpense.setReceiptFilename(imageName);
			flightExpense.setExpense(expenseNew);
			tripExpenseDao.insertFlightExpense(flightExpense);	
			return SUCCESS;
		}
		
		
	
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (flightExpense != null && !flightExpense.getDeparturalDate().equalsIgnoreCase("") && !flightExpense.getArrivalDate().equalsIgnoreCase("")) {        
			flightExpense.setArrivDate(flightExpense.getArrivalDate());
			flightExpense.setDepartureDate(flightExpense.getDeparturalDate());
			flightExpense.setCreatedAt(new Timestamp(new Date().getTime()));
			/*if(tripExepense.getTrip_id()!=null){
				String imageName=uploadFile(new Date().getTime(),"/home/expenseFile","flightExpense");
				flightExpense.setReceiptFilename(imageName);
			}*/
			String imageName=uploadFile(getExpFilePath(),"flightExpense");
			flightExpense.setReceiptFilename(imageName);
		}
		

		if(expenseNew!=null){
			flightExpense.setExpense(expenseNew);
			tripExpenseDao.insertFlightExpense(flightExpense);
		}
		else{
			//			TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			//			tripExepense.setTripRequest(tripRequest);
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			flightExpense.setExpense(expenseNew);
			tripExpenseDao.insertFlightExpense(flightExpense);
		}
		return SUCCESS;
	}
	
	
	

	public String createHotelExpenses(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			if (hotelExpense != null && !hotelExpense.getCheckInDateTemp().equalsIgnoreCase("") && !hotelExpense.getCheckOutDateTemp().equalsIgnoreCase("")) {        
				hotelExpense.setCheckInDate(hotelExpense.getCheckInDateTemp());
				hotelExpense.setCheckOutDate(hotelExpense.getCheckOutDateTemp());
			}
			
			String imageName=uploadFile(getExpFilePath(),"hotelExpense");
			hotelExpense.setReceiptFilename(imageName);
			hotelExpense.setExpense(expenseNew);
			tripExpenseDao.insertHotelExpense(hotelExpense);	
			return SUCCESS;
		}
 		if(tripExepense.getId()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getId()) ;
		if (hotelExpense != null && !hotelExpense.getCheckInDateTemp().equalsIgnoreCase("") && !hotelExpense.getCheckOutDateTemp().equalsIgnoreCase("")) {        
			hotelExpense.setCheckInDate(hotelExpense.getCheckInDateTemp());
			hotelExpense.setCheckOutDate(hotelExpense.getCheckOutDateTemp());
			String imageName=uploadFile(getExpFilePath(),"hotelExpense");
			hotelExpense.setReceiptFilename(imageName);
			hotelExpense.setCreatedAt(new Timestamp(new Date().getTime()));
		}

		if(expenseNew!=null ){
			hotelExpense.setExpense(expenseNew);
			tripExpenseDao.insertHotelExpense(hotelExpense);
		}
		else{
			//			TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			//			tripExepense.setTripRequest(tripRequest);
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			hotelExpense.setExpense(expenseNew);
			tripExpenseDao.insertHotelExpense(hotelExpense);
		}
		return SUCCESS;
	}
	public String createCarExpenses(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			if (carExpense != null && !carExpense.getTravelDateTemp().equalsIgnoreCase("")) {        
				carExpense.setTravelDate(carExpense.getTravelDateTemp());
			}
			String imageName=uploadFile(getExpFilePath(),"carExpense");
			carExpense.setReceiptFilename(imageName);
			carExpense.setExpense(expenseNew);
			tripExpenseDao.insertCarExpense(carExpense);
			return SUCCESS;
		}
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (carExpense != null && !carExpense.getTravelDateTemp().equalsIgnoreCase("")) {        
			carExpense.setTravelDate(carExpense.getTravelDateTemp());
			String imageName=uploadFile(getExpFilePath(),"carExpense");
			carExpense.setReceiptFilename(imageName);
			carExpense.setCreatedAt(new Timestamp(new Date().getTime()));
		}

		if(expenseNew!=null){
			carExpense.setExpense(expenseNew);
			tripExpenseDao.insertCarExpense(carExpense);
		}
		else{
			/*			TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			tripExepense.setTripRequest(tripRequest);*/
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			carExpense.setExpense(expenseNew);
			tripExpenseDao.insertCarExpense(carExpense);
		}
		return SUCCESS;
	}
	public String creatTrainExpenses(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			
			if (trainExpense != null && !trainExpense.getTravelDateTemp().equalsIgnoreCase("") ) {        
				trainExpense.setTravelDate(trainExpense.getTravelDateTemp());
			}
			String imageName=uploadFile(getExpFilePath(),"trainExpense");
			trainExpense.setReceiptFilename(imageName);
			trainExpense.setExpense(expenseNew);
			tripExpenseDao.insertTrainExpense(trainExpense);	
			return SUCCESS;
		}
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (trainExpense != null && !trainExpense.getTravelDateTemp().equalsIgnoreCase("") ) {        
			trainExpense.setTravelDate(trainExpense.getTravelDateTemp());
			//trainExpense.setDepartureDate(trainExpense.getDeparturalDate());
			String imageName=uploadFile(getExpFilePath(),"trainExpense");
			trainExpense.setReceiptFilename(imageName);
			trainExpense.setCreatedAt(new Timestamp(new Date().getTime()));

		}

		if(expenseNew!=null){
			trainExpense.setExpense(expenseNew);
			tripExpenseDao.insertTrainExpense(trainExpense);
		}
		else{
			/*			TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			tripExepense.setTripRequest(tripRequest);*/
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			trainExpense.setExpense(expenseNew);
			tripExpenseDao.insertTrainExpense(trainExpense);
		}
		return SUCCESS;
	}
	public String createBusExpenses(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			
			if (busExpense != null && !busExpense.getTravelDateTemp().equalsIgnoreCase("") ) {        
				busExpense.setTravelDate(busExpense.getTravelDateTemp());
			}
			String imageName=uploadFile(getExpFilePath(),"busExpense");
			busExpense.setReceiptFilename(imageName);
			busExpense.setExpense(expenseNew);
			tripExpenseDao.insertBusExpense(busExpense);	
			return SUCCESS;
		}
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (busExpense != null && !busExpense.getTravelDateTemp().equalsIgnoreCase("") ) {        
			busExpense.setTravelDate(busExpense.getTravelDateTemp());
			//busExpenses.setDepartureDate(busExpenses.getDeparturalDate());
			String imageName=uploadFile(getExpFilePath(),"busExpense");
			busExpense.setReceiptFilename(imageName);
			busExpense.setCreatedAt(new Timestamp(new Date().getTime()));
		}

		if(expenseNew!=null){
			busExpense.setExpense(expenseNew);
			tripExpenseDao.insertBusExpense(busExpense);
		}
		else{
			/*			TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			tripExepense.setTripRequest(tripRequest);*/
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			busExpense.setExpense(expenseNew);
			tripExpenseDao.insertBusExpense(busExpense);
		}
		return SUCCESS;
	}
	public String createMiscellaneousExpense(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			if (miscellaneousExpense != null && !miscellaneousExpense.getExpenseDateTemp().equalsIgnoreCase("") ) {        
				miscellaneousExpense.setExpenseDate(miscellaneousExpense.getExpenseDateTemp());
			}
			String imageName=uploadFile(getExpFilePath(),"miscellaneousExpense");
			miscellaneousExpense.setReceiptFilename(imageName);
			miscellaneousExpense.setExpense(expenseNew);
			tripExpenseDao.insertMiscellaneousExpense(miscellaneousExpense);	
			return SUCCESS;
		}
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (miscellaneousExpense != null && !miscellaneousExpense.getExpenseDateTemp().equalsIgnoreCase("") ) {        
			miscellaneousExpense.setExpenseDate(miscellaneousExpense.getExpenseDateTemp());
			//flightExpense.setDepartureDate(flightExpense.getDeparturalDate());
			String imageName=uploadFile(getExpFilePath(),"miscellaneousExpense");
			miscellaneousExpense.setReceiptFilename(imageName);
			miscellaneousExpense.setCreatedAt(new Timestamp(new Date().getTime()));
		}

		if(expenseNew!=null ){
			miscellaneousExpense.setExpense(expenseNew);
			tripExpenseDao.insertMiscellaneousExpense(miscellaneousExpense);
		}
		else{
			/*			TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			tripExepense.setTripRequest(tripRequest);*/
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			miscellaneousExpense.setExpense(expenseNew);
			tripExpenseDao.insertMiscellaneousExpense(miscellaneousExpense);
		}
		return SUCCESS;
	}
	public String createMealExpense(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			
			if (mealExpense != null && !mealExpense.getExpenseDateTemp().equalsIgnoreCase("") ) {        
				mealExpense.setExpenseDate(mealExpense.getExpenseDateTemp());
			}
			String imageName=uploadFile(getExpFilePath(),"mealExpense");
			mealExpense.setReceiptFilename(imageName);
			mealExpense.setExpense(expenseNew);
			tripExpenseDao.insertMealExpense(mealExpense);	
			return SUCCESS;
		}
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (mealExpense != null && !mealExpense.getExpenseDateTemp().equalsIgnoreCase("") ) {        
			mealExpense.setExpenseDate(mealExpense.getExpenseDateTemp());
			String imageName=uploadFile(getExpFilePath(),"mealExpense");
			mealExpense.setReceiptFilename(imageName);
			mealExpense.setCreatedAt(new Timestamp(new Date().getTime()));

		}

		if(expenseNew!=null ){
			mealExpense.setExpense(expenseNew);
			tripExpenseDao.insertMealExpense(mealExpense);
		}
		else{
			/*			TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			tripExepense.setTripRequest(tripRequest);*/
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			mealExpense.setExpense(expenseNew);
			tripExpenseDao.insertMealExpense(mealExpense);
		}
		return SUCCESS;
	}
	public String createLaborExpense(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			
			if (laborExpense != null && !laborExpense.getExpenseDateTemp().equalsIgnoreCase("") ) {        
				laborExpense.setExpenseDate(laborExpense.getExpenseDateTemp());
			}
			String imageName=uploadFile(getExpFilePath(),"laborExpense");
			laborExpense.setCreatedAt(new Timestamp(new Date().getTime()));
			laborExpense.setReceiptFilename(imageName);
			laborExpense.setExpense(expenseNew);
			tripExpenseDao.insertLaborExpense(laborExpense);	
			return SUCCESS;
		}
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (laborExpense != null && !laborExpense.getExpenseDateTemp().equalsIgnoreCase("") ) {        
			laborExpense.setExpenseDate(laborExpense.getExpenseDateTemp());
			String imageName=uploadFile(getExpFilePath(),"laborExpense");
			laborExpense.setCreatedAt(new Timestamp(new Date().getTime()));
			laborExpense.setReceiptFilename(imageName);
		}

		if(expenseNew!=null ){
			laborExpense.setExpense(expenseNew);
			tripExpenseDao.insertLaborExpense(laborExpense);
		}
		else{
			/*TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			tripExepense.setTripRequest(tripRequest);*/
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			laborExpense.setExpense(expenseNew);
			tripExpenseDao.insertLaborExpense(laborExpense);
		}
		return SUCCESS;
	}

	public String createVisaExpense(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			
			if (visaExpense != null && !visaExpense.getTravelDateTemp().equalsIgnoreCase("") ) {        
				visaExpense.setTravelDate(visaExpense.getTravelDateTemp());
			}
			String imageName=uploadFile(getExpFilePath(),"visaExpense");
			visaExpense.setReceiptFilename(imageName);
			visaExpense.setExpense(expenseNew);
			tripExpenseDao.insertVisaExpense(visaExpense);	
			return SUCCESS;
		}
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (visaExpense != null && !visaExpense.getTravelDateTemp().equalsIgnoreCase("") ) {        
			visaExpense.setTravelDate(visaExpense.getTravelDateTemp());
			String imageName=uploadFile(getExpFilePath(),"visaExpense");
			visaExpense.setReceiptFilename(imageName);
			visaExpense.setCreatedAt(new Timestamp(new Date().getTime()));
		}

		if(expenseNew!=null ){
			visaExpense.setExpense(expenseNew);
			tripExpenseDao.insertVisaExpense(visaExpense);
		}
		else{
			/*TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			tripExepense.setTripRequest(tripRequest);*/
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			visaExpense.setExpense(expenseNew);
			tripExpenseDao.insertVisaExpense(visaExpense);
		}
		return SUCCESS;
	}

	public String createInsurenseExpense(){
		TripExepense expenseNew=null;
		if(expid>0){
			expenseNew= tripExpenseDao.getExpense(expid) ;
			
			if (insuranceExpense != null && !insuranceExpense.getTravelDateTemp().equalsIgnoreCase("") ) {        
				insuranceExpense.setTravelDate(insuranceExpense.getTravelDateTemp());
			}
			String imageName=uploadFile(getExpFilePath(),"insuranceExpense");
			insuranceExpense.setReceiptFilename(imageName);
			insuranceExpense.setExpense(expenseNew);
			tripExpenseDao.insertInsuranceExpense(insuranceExpense);	
			return SUCCESS;
		}
		if(tripExepense.getTrip_id()!=null) 
			expenseNew= tripExpenseDao.getExpense(tripExepense.getTrip_id()) ;
		if (insuranceExpense != null && !insuranceExpense.getTravelDateTemp().equalsIgnoreCase("") ) {        
			insuranceExpense.setTravelDate(insuranceExpense.getTravelDateTemp());
			String imageName=uploadFile(getExpFilePath(),"insuranceExpense");
			insuranceExpense.setReceiptFilename(imageName);
			insuranceExpense.setCreatedAt(new Timestamp(new Date().getTime()));
		}

		if(expenseNew!=null ){
			insuranceExpense.setExpense(expenseNew);
			tripExpenseDao.insertInsuranceExpense(insuranceExpense);
		}
		else{
			/*TripRequest  tripRequest=tripRequestDao.getTripRequestById(tripExepense.getTrip_id());
			tripExepense.setTripRequest(tripRequest);*/
			expenseNew=tripExpenseDao.insertExpense(tripExepense);
			insuranceExpense.setExpense(expenseNew);
			tripExpenseDao.insertInsuranceExpense(insuranceExpense);
		}
		return SUCCESS;
	}

	public String showExpenseDetails(){
		TripExepense expensetoshow=new TripExepense();
		expensetoshow=tripExpenseDao.getExpense(tripExepense.getId());
		setExpenseToView(expensetoshow);
		return SUCCESS;

	}
	
	public String showFlightExpenseDetails(){
		TripExepense trpExpense=new TripExepense();
		trpExpense=tripExpenseDao.getExpense(expid);
		setExpenseToView(trpExpense);
		return SUCCESS;

	}
	
	
	public String redirectId(){
		CountryList = new CountryDao().getCountryList();
		setCountryList(CountryList);
		setExpid(expid);
		return SUCCESS;

	}
	public String uploadFile(String file_path,String expenseType){
		String fileNameInDir=null;
		String name=null;
		if(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)
		{
			MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();

			//String[] fileParameterNames = multiWrapper.getFileNames("Imagepath");
			Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();

			if(fileParameterNames.hasMoreElements()){
				String inputValue = (String) fileParameterNames.nextElement(); 
				String[] localFileNames = multiWrapper.getFileNames(inputValue);
				UploadedFile[] files = multiWrapper.getFiles(inputValue); 
				String fileName = "";
				String fileType = "";

				for (UploadedFile cf : files) {
					//logger.info("----------file length...-------"+cf.length());
					//logger.info("file length..."+cf.length());
					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
					//String file_path = servletRequest.getSession().getServletContext().getRealPath("/admin_profile_pics" );
					// String file_path = "/home/expenseFile";
					//file_path = "D:\\saumyaFile\\";
					//logger.info("----------Server path:...-------"+ file_path);
					//logger.info("Server path:" + file_path);
					File fileToCreate = new File(file_path, new Date().getTime()+"."+expenseType+"."+fileType);
					//logger.info("fileToCreate========="+fileToCreate);
					try {
						if(cf!=null && cf.getContent()!=null)
						{
							File fi = (File) cf.getContent();

							FileUtils.copyFile(fi, fileToCreate);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//logger.error("----------IOEXCEPTION-----------"+e.getMessage());
						//e.printStackTrace();
					}
					 name=fileToCreate.getName();
					//fileNameInDir = new Date().getTime()+"."+expenseType+"."+fileType;

					//logger.info("fileNameInDir==============="+fileNameInDir);

				}
			}
		}
		return name;

	}

	public long redirectTripExpense(){
		TripExepense tripExepense=new TripExepense();
		tripExepense.setTrip_id(1l);
		return tripExepense.getTrip_id();
		
	}
	
	public String getAllTripExpense(){
		 tripExepenses =tripExpenseDao.feltchExpenseList();
		 
		 if(tripExepenses !=null){
		for (TripExepense tripExepense : tripExepenses) {
			long id=tripExepense.getId();
			FlightExpense flightExpense=tripExpenseDao.showFlightExpense(id);
			HotelExpense hotelExpense=tripExpenseDao.showHotelExpense(id);
			CarExpense carExpense=tripExpenseDao.showCarExpense(id);
			BusExpense busExpense=tripExpenseDao.showBusExpense(id);
			TrainExpense trainExpense=tripExpenseDao.showTrainExpense(id);
			InsuranceExpense insuranceExpense=tripExpenseDao.showInsurenseExpense(id);
			VisaExpense visaExpense=tripExpenseDao.showVisaExpense(id);
			
			flightExpenses.add(flightExpense);
			hotelExpenses.add(hotelExpense);
			carExpenses.add(carExpense);
			busExpenses.add(busExpense);
			trainExpenses.add(trainExpense);
			insuranceExpenses.add(insuranceExpense);
			visaExpenses.add(visaExpense);
		}
		
		 }
		 
		return SUCCESS;
		
	}
	


	public String getCurrencyList() {
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		tripExepense.setTrip_id(getTripId());
		return SUCCESS;
	}

	@Override
	public TripExepense getModel() {
		return tripExepense;
	}

	public TripExepense getExpense() {
		return tripExepense;
	}

	public void setExpense(TripExepense tripExepense) {
		this.tripExepense = tripExepense;
	}

	public List<Country> getCountryList() {
		return CountryList;
	}

	public void setCountryList(List<Country> countryList) {
		CountryList = countryList;
	}

	public List<TripRequest> getTripRequests() {
		return tripRequests;
	}

	public void setTripRequests(List<TripRequest> tripRequests) {
		this.tripRequests = tripRequests;
	}

	/*public void setListOfTripId(List<Long> listOfTripId) {
		ListOfTripId = listOfTripId;
	}*/
	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}


	public FlightExpense getFlightExpense() {
		return flightExpense;
	}


	public void setFlightExpense(FlightExpense flightExpense) {
		this.flightExpense = flightExpense;
	}

	public HotelExpense getHotelExpense() {
		return hotelExpense;
	}

	public void setHotelExpense(HotelExpense hotelExpense) {
		this.hotelExpense = hotelExpense;
	}

	public CarExpense getCarExpense() {
		return carExpense;
	}

	public void setCarExpense(CarExpense carExpense) {
		this.carExpense = carExpense;
	}

	public TrainExpense getTrainExpense() {
		return trainExpense;
	}

	public void setTrainExpense(TrainExpense trainExpense) {
		this.trainExpense = trainExpense;
	}

	public BusExpense getBusExpense() {
		return busExpense;
	}

	public void setBusExpense(BusExpense busExpense) {
		this.busExpense = busExpense;
	}

	public MiscellaneousExpense getMiscellaneousExpense() {
		return miscellaneousExpense;
	}

	public void setMiscellaneousExpense(MiscellaneousExpense miscellaneousExpense) {
		this.miscellaneousExpense = miscellaneousExpense;
	}

	public MealExpense getMealExpense() {
		return mealExpense;
	}

	public void setMealExpense(MealExpense mealExpense) {
		this.mealExpense = mealExpense;
	}

	public LaborExpense getLaborExpense() {
		return laborExpense;
	}

	public void setLaborExpense(LaborExpense laborExpense) {
		this.laborExpense = laborExpense;
	}

	public TripExepense getExpenseToView() {
		return expenseToView;
	}

	public void setExpenseToView(TripExepense expenseToView) {
		this.expenseToView = expenseToView;
	}

	public VisaExpense getVisaExpense() {
		return visaExpense;
	}

	public void setVisaExpense(VisaExpense visaExpense) {
		this.visaExpense = visaExpense;
	}

	public InsuranceExpense getInsuranceExpense() {
		return insuranceExpense;
	}

	public void setInsuranceExpense(InsuranceExpense insuranceExpense) {
		this.insuranceExpense = insuranceExpense;
	}

	public List<FlightExpense> getFlightExpenses() {
		return flightExpenses;
	}

	public void setFlightExpenses(List<FlightExpense> flightExpenses) {
		this.flightExpenses = flightExpenses;
	}

	public List<HotelExpense> getHotelExpenses() {
		return hotelExpenses;
	}

	public void setHotelExpenses(List<HotelExpense> hotelExpenses) {
		this.hotelExpenses = hotelExpenses;
	}

	public List<CarExpense> getCarExpenses() {
		return carExpenses;
	}

	public void setCarExpenses(List<CarExpense> carExpenses) {
		this.carExpenses = carExpenses;
	}

	public List<BusExpense> getBusExpenses() {
		return busExpenses;
	}

	public void setBusExpenses(List<BusExpense> busExpenses) {
		this.busExpenses = busExpenses;
	}

	public List<TrainExpense> getTrainExpenses() {
		return trainExpenses;
	}

	public void setTrainExpenses(List<TrainExpense> trainExpenses) {
		this.trainExpenses = trainExpenses;
	}

	public List<InsuranceExpense> getInsuranceExpenses() {
		return insuranceExpenses;
	}

	public void setInsuranceExpenses(List<InsuranceExpense> insuranceExpenses) {
		this.insuranceExpenses = insuranceExpenses;
	}

	public List<VisaExpense> getVisaExpenses() {
		return visaExpenses;
	}

	public void setVisaExpenses(List<VisaExpense> visaExpenses) {
		this.visaExpenses = visaExpenses;
	}

	public List<TripExepense> getTripExepenses() {
		return tripExepenses;
	}

	public void setTripExepenses(List<TripExepense> tripExepenses) {
		this.tripExepenses = tripExepenses;
	}

	public long getExpid() {
		return expid;
	}

	public void setExpid(long expid) {
		this.expid = expid;
	}

	public String getExpFilePath(){
	 return	getText("global.expense_upload_download_file_path");
	}
	
	/*public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}*/
}
