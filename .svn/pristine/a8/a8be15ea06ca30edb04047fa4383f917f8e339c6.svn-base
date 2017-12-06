package com.admin.common.expense.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.admin.common.expense.dao.TripExpenseDao;
import com.admin.common.expense.model.BusExpense;
import com.admin.common.expense.model.CarExpense;
import com.admin.common.expense.model.FlightExpense;
import com.admin.common.expense.model.HotelExpense;
import com.admin.common.expense.model.InsuranceExpense;
import com.admin.common.expense.model.LaborExpense;
import com.admin.common.expense.model.MealExpense;
import com.admin.common.expense.model.MiscellaneousExpense;
import com.admin.common.expense.model.TrainExpense;
import com.admin.common.expense.model.TripExepense;
import com.admin.common.expense.model.VisaExpense;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Country;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TripExpenseCommonCrudAction extends ActionSupport implements ModelDriven<FlightExpense>{
	private static final long serialVersionUID = 1L;

	private FlightExpense flightExpense = new FlightExpense();
	private HotelExpense hotelExpense=new HotelExpense();
	private CarExpense carExpense=new CarExpense();
	private TrainExpense trainExpense=new TrainExpense();
	private BusExpense busExpense=new BusExpense();
	private MiscellaneousExpense miscellaneousExpense=new MiscellaneousExpense();
	private MealExpense mealExpense=new MealExpense();
	private LaborExpense laborExpense=new LaborExpense();
	private VisaExpense visaExpense=new VisaExpense();
	private InsuranceExpense insuranceExpense =new InsuranceExpense();
	TripExpenseDao tripExpenseDao = new TripExpenseDao();
	TripExpenseCommonAction tripExpenseCommonAction=new TripExpenseCommonAction();
	private List<Country> CountryList;
	TripExepense tripExepense=new TripExepense();
	CountryDao cDAO = new CountryDao();
	/*TripExepense tripExepense = new TripExepense();*/
	List<TripRequest> tripRequests;
	TripRequestDao tripRequestDao=new TripRequestDao();
	FlightExpense expenseToView=new FlightExpense();
	private long id;
	private Long tripId;
	
//created by basha for delete the perticular tripExepense 
	public String deleteFlightExpense(){

		tripExpenseDao.deleteFlightExpense(id);


		return SUCCESS;
	}
	public String deleteHotelExpense(){

		tripExpenseDao.deleteHotelExpense(id);

		return SUCCESS;
	}
	public String deleteCarExpense(){

		tripExpenseDao.deleteCarExpense(id);

		return SUCCESS;
	}
	public String deleteBusExpense(){

		tripExpenseDao.deleteBusExpense(id);


		return SUCCESS;
	}
	public String deleteTrainExpense(){

		tripExpenseDao.deleteTrainExpense(id);

		return SUCCESS;
	}
	public String deleteMealExpense(){

		tripExpenseDao.deleteMealExpense(id);

		return SUCCESS;
	}
	public String deleteLaborExpense(){

		tripExpenseDao.deleteLaborExpense(id);

		return SUCCESS;
	}
	
	public String deleteVisaExpense(){

		tripExpenseDao.deleteVisaExpense(id);

		return SUCCESS;
	}
	
	public String deleteInsuranceExpense(){

		tripExpenseDao.deleteInsuranceExpense(id);

		return SUCCESS;
	}
	
	public String deleteMislaniousExpense(){

		tripExpenseDao.deleteMiscellaneousExpense(id);

		return SUCCESS;
	}
	public String showflightExpenseDetails(){
		//flightExpense.getId()
		flightExpense=tripExpenseDao.showFlightExpense(id);
		/*notificationNew.setTransFromDate(DateConversion.convertTimestampToString(notificationNew.getFromDate()));
		notificationNew.setTransToDate(DateConversion.convertTimestampToString(notificationNew.getToDate()));*/
		/*flightExpense.setDeparturalDate(DateConversion.convertTimestampToString(flightExpense.getDepartureDate()));
		flightExpense.setArrivalDate(DateConversion.convertTimestampToString(flightExpense.getArrivDate()));
		setFlightExpense(flightExpense);*/
		
		
		return SUCCESS;

	}
	public String showhotelExpenseDetails(){
		//flightExpense.getId()
		hotelExpense=tripExpenseDao.showHotelExpense(id);
		/*hotelExpense.setCheckInDateTemp(DateConversion.convertTimestampToString(hotelExpense.getCheckInDate()));
		hotelExpense.setCheckOutDateTemp(DateConversion.convertTimestampToString(hotelExpense.getCheckOutDate()));
		setHotelExpense(hotelExpense);*/
		
		return SUCCESS;

	}

	public String showcarExpenseDetails(){
		//flightExpense.getId()
		carExpense=tripExpenseDao.showCarExpense(id);
		/*carExpense.setTravelDateTemp(DateConversion.convertTimestampToString(carExpense.getTravelDate()));
		
		setCarExpense(carExpense);*/
		
		return SUCCESS;

	}

	public String showtrainExpenseDetails(){
		//flightExpense.getId()
		trainExpense=tripExpenseDao.showTrainExpense(id);
		/*trainExpense.setTravelDateTemp(DateConversion.convertTimestampToString(trainExpense.getTravelDate()));
		setTrainExpense(trainExpense);*/
		
		return SUCCESS;

	}

	public String showbusExpenseDetails(){
		//flightExpense.getId()
		busExpense=tripExpenseDao.showBusExpense(id);
		/*busExpense.setTravelDate(DateConversion.convertTimestampToString(busExpense.getTravelDate()));
		setBusExpense(busExpense);*/
		
		return SUCCESS;

	}

	public String showmealExpenseDetails(){
		//flightExpense.getId()
		mealExpense=tripExpenseDao.showMealExpense(id);
		/*mealExpense.setExpenseDateTemp(DateConversion.convertTimestampToString(mealExpense.getExpenseDate()));
		setMealExpense(mealExpense);*/
		
		return SUCCESS;

	}

	public String showlaborExpenseDetails(){
		//flightExpense.getId()
		laborExpense=tripExpenseDao.showLaborExpense(id);
		/*laborExpense.setExpenseDateTemp(DateConversion.convertTimestampToString(laborExpense.getExpenseDate()));
		setLaborExpense(laborExpense);*/
		
		return SUCCESS;

	}

	public String showmislaniousExpenseDetails(){
		//flightExpense.getId()
		miscellaneousExpense=tripExpenseDao.showMislaniousExpense(id);
		/*miscellaneousExpense.setExpenseDateTemp(DateConversion.convertTimestampToString(miscellaneousExpense.getExpenseDate()));
		setMiscellaneousExpense(miscellaneousExpense);*/
		
		return SUCCESS;

	}
	public String showVisaExpenseDetails(){
		//flightExpense.getId()
		visaExpense=tripExpenseDao.showVisaExpense(id);
		/*miscellaneousExpense.setExpenseDateTemp(DateConversion.convertTimestampToString(miscellaneousExpense.getExpenseDate()));
		setMiscellaneousExpense(miscellaneousExpense);*/
		
		return SUCCESS;

	}
	public String showInsurenseExpenseDetails(){
		//flightExpense.getId()
		insuranceExpense=tripExpenseDao.showInsurenseExpense(id);
		/*miscellaneousExpense.setExpenseDateTemp(DateConversion.convertTimestampToString(miscellaneousExpense.getExpenseDate()));
		setMiscellaneousExpense(miscellaneousExpense);*/
		
		return SUCCESS;

	}
	


	public String updateflightexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			FlightExpense flightExpenseToUpdate = tripExpenseDao.showFlightExpense(flightExpense.getId());
			if(flightExpenseToUpdate!=null)
			{
				flightExpenseToUpdate.setExpenseCurrency(flightExpense.getExpenseCurrency());
				
				flightExpenseToUpdate.setPnrNumber(flightExpense.getPnrNumber());
				flightExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				flightExpenseToUpdate.setCreatedAt(flightExpense.getCreatedAt());
				//logger.info("tripExepense.getTrip_id()--------"+tripExepense.getTrip_id());
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"flightExpense");
				//logger.info("tripExepense.getTrip_id()--------"+tripExepense.getTrip_id());
				flightExpenseToUpdate.setReceiptFilename(imageName);
				flightExpenseToUpdate.setFlightNumber(flightExpense.getFlightNumber());
				flightExpenseToUpdate.setFlightCarrier(flightExpense.getFlightCarrier());
				flightExpenseToUpdate.setArrivDate(flightExpense.getArrivalDate());
				flightExpenseToUpdate.setDepartureDate(flightExpense.getDeparturalDate());
				flightExpenseToUpdate.setDepartment(flightExpense.getDepartment());
				flightExpenseToUpdate.setBillable(flightExpense.isBillable());
				flightExpenseToUpdate.setReimbursable(flightExpense.getReimbursable());
				flightExpenseToUpdate.setExpenseReason(flightExpense.getExpenseReason());
				flightExpenseToUpdate.setTotalAmount(flightExpense.getTotalAmount());
				
				//flightExpenseToUpdate.setArrivalDate(arrivalDate);
				
				//flightExpense.setCreatedAt(flightExpenseToUpdate.getCreatedAt());
				
				tripExpenseDao.editFlightExpense(flightExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	public String updatehotelexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			HotelExpense hotelExpenseToUpdate = tripExpenseDao.showHotelExpense(hotelExpense.getId());
			if(hotelExpenseToUpdate!=null)
			{
				
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"hotelExpense");
				hotelExpenseToUpdate.setReceiptFilename(imageName);
				//hotelExpenseToUpdate.setVersion(hotelExpense.getVersion());
				hotelExpenseToUpdate.setCreatedAt(hotelExpense.getCreatedAt());
				hotelExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				hotelExpenseToUpdate.setHotelConfirmNumber(hotelExpense.getHotelConfirmNumber());
				hotelExpenseToUpdate.setHotelName(hotelExpense.getHotelName());
				hotelExpenseToUpdate.setLocation(hotelExpense.getLocation());
				hotelExpenseToUpdate.setRoomType(hotelExpense.getRoomType());
				hotelExpenseToUpdate.setDepartment(hotelExpense.getRoomType());
				hotelExpenseToUpdate.setCheckInDate(hotelExpense.getCheckInDateTemp());
				hotelExpenseToUpdate.setCheckOutDate(hotelExpense.getCheckOutDateTemp());
				hotelExpenseToUpdate.setExpenseCurrency(hotelExpense.getExpenseCurrency());
				hotelExpenseToUpdate.setTotalAmount(hotelExpense.getTotalAmount());
				hotelExpenseToUpdate.setExpenseReason(hotelExpense.getExpenseReason());
				hotelExpenseToUpdate.setReimbursable(hotelExpense.getReimbursable());
				hotelExpenseToUpdate.setBillable(hotelExpense.isBillable());
				tripExpenseDao.editHotelExpense(hotelExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	public String updatecarexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			CarExpense carExpenseToUpdate = tripExpenseDao.showCarExpense(carExpense.getId());
			if(carExpenseToUpdate!=null)
			{
				
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"carExpense");
				carExpenseToUpdate.setReceiptFilename(imageName);
				//carExpense.setVersion(carExpenseToUpdate.getVersion());
				carExpenseToUpdate.setCreatedAt(carExpense.getCreatedAt());
				carExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				carExpenseToUpdate.setLocation(carExpense.getLocation());
				carExpenseToUpdate.setCarCompanyName(carExpense.getCarCompanyName());
				carExpenseToUpdate.setPickUp(carExpense.getPickUp());
				carExpenseToUpdate.setDropOff(carExpense.getDropOff());
				carExpenseToUpdate.setTravelDate(carExpense.getTravelDateTemp());
				carExpenseToUpdate.setDepartment(carExpense.getDepartment());
				carExpenseToUpdate.setExpenseCurrency(carExpense.getExpenseCurrency());
				carExpenseToUpdate.setTotalAmount(carExpense.getTotalAmount());
				carExpenseToUpdate.setExpenseReason(carExpense.getExpenseReason());
				carExpenseToUpdate.setBillable(carExpense.isBillable());
				//carExpense.setExpense(carExpenseToUpdate.getExpense());
				carExpenseToUpdate.setReimbursable(carExpense.getReimbursable());
				carExpenseToUpdate.setBasePrice(carExpense.getBasePrice());
				carExpenseToUpdate.setConfirmationNumber(carExpense.getConfirmationNumber());
				carExpenseToUpdate.setSupplierPrice(carExpense.getSupplierPrice());
				carExpenseToUpdate.setOtherTaxes(carExpense.getOtherTaxes());
				carExpenseToUpdate.setTollOrParkingCharges(carExpense.getTollOrParkingCharges());
				carExpenseToUpdate.setDriverAllowanceDay(carExpense.getDriverAllowanceDay());
				carExpenseToUpdate.setDriverAllowanceNight(carExpense.getDriverAllowanceNight());
				carExpenseToUpdate.setManagementFee(carExpense.getManagementFee());
				carExpenseToUpdate.setConvenienceFee(carExpense.getConvenienceFee());
				carExpenseToUpdate.setRemarks(carExpense.getRemarks());
				carExpenseToUpdate.setServiceTax(carExpense.getServiceTax());
				carExpenseToUpdate.setExtraKM(carExpense.getExtraKM());
				carExpenseToUpdate.setExtraHours(carExpense.getExtraHours());
				carExpenseToUpdate.setPassengerName(carExpense.getPassengerName());
				tripExpenseDao.editCarExpense(carExpenseToUpdate);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String updatetrainexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			TrainExpense trainExpenseToUpdate = tripExpenseDao.showTrainExpense(trainExpense.getId());
			if(trainExpenseToUpdate!=null)
			{
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"trainExpense");
				trainExpenseToUpdate.setReceiptFilename(imageName);
				//trainExpense.setVersion(trainExpenseToUpdate.getVersion());
				trainExpenseToUpdate.setCreatedAt(trainExpense.getCreatedAt());
				trainExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				trainExpenseToUpdate.setPnrNumber(trainExpense.getPnrNumber());
				trainExpenseToUpdate.setTrainNumber(trainExpense.getTrainNumber());
				trainExpenseToUpdate.setTolocation(trainExpense.getTolocation());
				trainExpenseToUpdate.setFromlocation(trainExpense.getFromlocation());
				trainExpenseToUpdate.setTravelDate(trainExpense.getTravelDateTemp());
				trainExpenseToUpdate.setDepartment(trainExpense.getDepartment());
				trainExpenseToUpdate.setExpenseCurrency(trainExpense.getExpenseCurrency());
				trainExpenseToUpdate.setTotalAmount(trainExpense.getTotalAmount());
				trainExpenseToUpdate.setExpenseReason(trainExpense.getExpenseReason());
				trainExpenseToUpdate.setBillable(trainExpense.isBillable());
				trainExpenseToUpdate.setReimbursable(trainExpense.getReimbursable());
				trainExpenseToUpdate.setBasePrice(trainExpense.getBasePrice());
				trainExpenseToUpdate.setSupplierPrice(trainExpense.getSupplierPrice());
				trainExpenseToUpdate.setOtherTaxes(trainExpense.getOtherTaxes());
				trainExpenseToUpdate.setManagementFee(trainExpense.getManagementFee());
				trainExpenseToUpdate.setConvenienceFee(trainExpense.getConvenienceFee());
				trainExpenseToUpdate.setRemarks(trainExpense.getRemarks());
				trainExpenseToUpdate.setServiceTax(trainExpense.getServiceTax());
				trainExpenseToUpdate.setPassengerName(trainExpense.getPassengerName());
				tripExpenseDao.editTrainExpense(trainExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	public String updatebusexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			BusExpense busExpenseToUpdate = tripExpenseDao.showBusExpense(busExpense.getId());
			if(busExpenseToUpdate!=null)
			{
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"busExpense");
				busExpenseToUpdate.setReceiptFilename(imageName);
				//busExpense.setVersion(busExpenseToUpdate.getVersion());
				busExpenseToUpdate.setCreatedAt(busExpense.getCreatedAt());
				busExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				busExpenseToUpdate.setBusType(busExpense.getBusType());
				busExpenseToUpdate.setLocation(busExpense.getLocation());
				busExpenseToUpdate.setPickUp(busExpense.getPickUp());
				busExpenseToUpdate.setDropOff(busExpense.getDropOff());
				busExpenseToUpdate.setTravelDate(busExpense.getTravelDateTemp());
				busExpenseToUpdate.setDepartment(busExpense.getDepartment());
				busExpenseToUpdate.setExpenseCurrency(busExpense.getExpenseCurrency());
				busExpenseToUpdate.setTotalAmount(busExpense.getTotalAmount());
				busExpenseToUpdate.setExpenseReason(busExpense.getExpenseReason());
				busExpenseToUpdate.setBillable(busExpense.isBillable());
				busExpenseToUpdate.setReimbursable(busExpense.getReimbursable());
				busExpenseToUpdate.setBasePrice(busExpense.getBasePrice());
				busExpenseToUpdate.setSupplierPrice(busExpense.getSupplierPrice());
				busExpenseToUpdate.setOtherTaxes(busExpense.getOtherTaxes());
				busExpenseToUpdate.setManagementFee(busExpense.getManagementFee());
				busExpenseToUpdate.setConvenienceFee(busExpense.getConvenienceFee());
				busExpenseToUpdate.setRemarks(busExpense.getRemarks());
				busExpenseToUpdate.setServiceTax(busExpense.getServiceTax());
				busExpenseToUpdate.setPassengerName(busExpense.getPassengerName());
				busExpenseToUpdate.setConfirmationNumber(busExpense.getConfirmationNumber());
				
				
				tripExpenseDao.editBusExpense(busExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	

	public String updatemealexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			MealExpense mealExpenseToUpdate = tripExpenseDao.showMealExpense(mealExpense.getId());
			if(mealExpenseToUpdate!=null)
			{
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"mealExpense");
				mealExpenseToUpdate.setReceiptFilename(imageName);
				mealExpenseToUpdate.setCreatedAt(mealExpense.getCreatedAt());
				mealExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				mealExpenseToUpdate.setVendor(mealExpense.getVendor());
				mealExpenseToUpdate.setLocation(mealExpense.getLocation());
				mealExpenseToUpdate.setExpenseDate(mealExpense.getExpenseDateTemp());
				mealExpenseToUpdate.setDepartment(mealExpense.getDepartment());
				mealExpenseToUpdate.setExpenseCurrency(mealExpense.getExpenseCurrency());
				mealExpenseToUpdate.setTotalAmount(mealExpense.getTotalAmount());
				mealExpenseToUpdate.setExpenseReason(mealExpense.getExpenseReason());
				mealExpenseToUpdate.setBillable(mealExpense.isBillable());
				mealExpenseToUpdate.setReimbursable(mealExpense.getReimbursable());
				tripExpenseDao.editMealExpense(mealExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	public String updatelaborexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			LaborExpense laborExpenseToUpdate = tripExpenseDao.showLaborExpense(laborExpense.getId());
			if(laborExpenseToUpdate!=null)
			{
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"laborExpense");
				laborExpenseToUpdate.setReceiptFilename(imageName);
				laborExpenseToUpdate.setCreatedAt(laborExpense.getCreatedAt());
				laborExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				laborExpenseToUpdate.setWorkName(laborExpense.getWorkName());
				laborExpenseToUpdate.setDepartment(laborExpense.getDepartment());
				laborExpenseToUpdate.setLocation(laborExpense.getLocation());
				laborExpenseToUpdate.setHours(laborExpense.getHours());
				laborExpenseToUpdate.setTotalAmount(laborExpense.getTotalAmount());
				laborExpenseToUpdate.setExpenseCurrency(laborExpense.getExpenseCurrency());
				laborExpenseToUpdate.setExpenseDate(laborExpense.getExpenseDateTemp());
				laborExpenseToUpdate.setExpenseReason(laborExpense.getExpenseReason());
				laborExpenseToUpdate.setBillable(laborExpense.isBillable());
				laborExpenseToUpdate.setReimbursable(laborExpense.getReimbursable());
				tripExpenseDao.editLaborExpense(laborExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return SUCCESS;
	}
	
	

	public String updatemislaniousexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			MiscellaneousExpense mislaniousExpenseToUpdate = tripExpenseDao.showMislaniousExpense(miscellaneousExpense.getId());
			if(mislaniousExpenseToUpdate!=null)
			{
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"mislaniousExpense");
				mislaniousExpenseToUpdate.setReceiptFilename(imageName);
				mislaniousExpenseToUpdate.setCreatedAt(miscellaneousExpense.getCreatedAt());
				mislaniousExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				mislaniousExpenseToUpdate.setDepartment(miscellaneousExpense.getDepartment());
				mislaniousExpenseToUpdate.setLocation(miscellaneousExpense.getLocation());
				mislaniousExpenseToUpdate.setExpenseDate(miscellaneousExpense.getExpenseDateTemp());
				mislaniousExpenseToUpdate.setExpenseCurrency(miscellaneousExpense.getExpenseCurrency());
				mislaniousExpenseToUpdate.setTotalAmount(miscellaneousExpense.getTotalAmount());
				mislaniousExpenseToUpdate.setExpenseReason(miscellaneousExpense.getExpenseReason());
				mislaniousExpenseToUpdate.setBillable(miscellaneousExpense.isBillable());
				mislaniousExpenseToUpdate.setReimbursable(miscellaneousExpense.getReimbursable());
				mislaniousExpenseToUpdate.setBasePrice(miscellaneousExpense.getBasePrice());
				mislaniousExpenseToUpdate.setSupplierPrice(miscellaneousExpense.getSupplierPrice());
				mislaniousExpenseToUpdate.setOtherTaxes(miscellaneousExpense.getOtherTaxes());
				mislaniousExpenseToUpdate.setManagementFee(miscellaneousExpense.getManagementFee());
				mislaniousExpenseToUpdate.setConvenienceFee(miscellaneousExpense.getConvenienceFee());
				mislaniousExpenseToUpdate.setRemarks(miscellaneousExpense.getRemarks());
				mislaniousExpenseToUpdate.setServiceTax(miscellaneousExpense.getServiceTax());
				mislaniousExpenseToUpdate.setPassengerName(miscellaneousExpense.getPassengerName());
				mislaniousExpenseToUpdate.setConfirmationNumber(miscellaneousExpense.getConfirmationNumber());
				
				
				tripExpenseDao.editMiscellaneousExpense(mislaniousExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	public String updateVisaexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			VisaExpense visaExpenseToUpdate = tripExpenseDao.showVisaExpense(visaExpense.getId());
			if(visaExpenseToUpdate!=null)
			{
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"visaExpense");
				visaExpenseToUpdate.setReceiptFilename(imageName);
				visaExpenseToUpdate.setCreatedAt(visaExpense.getCreatedAt());
				visaExpenseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
				visaExpenseToUpdate.setBasePrice(visaExpense.getBasePrice());
				visaExpenseToUpdate.setConfirmationNumber(visaExpense.getConfirmationNumber());
				visaExpenseToUpdate.setConvenienceFee(visaExpense.getConvenienceFee());
				visaExpenseToUpdate.setCourierCharges(visaExpense.getCourierCharges());
				visaExpenseToUpdate.setDescription(visaExpense.getDescription());
				visaExpenseToUpdate.setManagementFee(visaExpense.getManagementFee());
				visaExpenseToUpdate.setOtherTaxes(visaExpense.getOtherTaxes());
				visaExpenseToUpdate.setPassengerName(visaExpense.getPassengerName());
				visaExpenseToUpdate.setRemarks(visaExpense.getRemarks());
				visaExpenseToUpdate.setServiceTax(visaExpense.getServiceTax());
				visaExpenseToUpdate.setSupplierPrice(visaExpense.getSupplierPrice());
				visaExpenseToUpdate.setTotalInvoiceAmount(visaExpense.getTotalInvoiceAmount());
				visaExpenseToUpdate.setTravelDate(visaExpense.getTravelDateTemp());
				visaExpenseToUpdate.setVfsCharges(visaExpense.getVfsCharges());
				tripExpenseDao.editVisaExpense(visaExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	public String updateInsuranseexpenseForm()
	{
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		try
		{
			InsuranceExpense insuranceExpenseToUpdate = tripExpenseDao.showInsurenseExpense(insuranceExpense.getId());
			if(insuranceExpenseToUpdate!=null)
			{
				String imageName=tripExpenseCommonAction.uploadFile(getExpFilePath(),"insuranceExpense");
				insuranceExpenseToUpdate.setReceiptFilename(imageName);
				insuranceExpenseToUpdate.setCreatedAt(insuranceExpense.getCreatedAt());
				insuranceExpenseToUpdate.setBasePrice(insuranceExpense.getBasePrice());
				insuranceExpenseToUpdate.setConfirmationNumber(insuranceExpense.getConfirmationNumber());
				insuranceExpenseToUpdate.setConvenienceFee(insuranceExpense.getConvenienceFee());
				insuranceExpenseToUpdate.setDescription(insuranceExpense.getDescription());
				insuranceExpenseToUpdate.setManagementFee(insuranceExpense.getManagementFee());
				insuranceExpenseToUpdate.setOtherTaxes(insuranceExpense.getOtherTaxes());
				insuranceExpenseToUpdate.setPassengerName(insuranceExpense.getPassengerName());
				insuranceExpenseToUpdate.setRemarks(insuranceExpense.getRemarks());
				insuranceExpenseToUpdate.setServiceTax(insuranceExpense.getServiceTax());
				insuranceExpenseToUpdate.setSupplierPrice(insuranceExpense.getSupplierPrice());
				insuranceExpenseToUpdate.setTotalInvoiceAmount(insuranceExpense.getTotalInvoiceAmount());
				insuranceExpenseToUpdate.setTravelDate(insuranceExpense.getTravelDateTemp());
				tripExpenseDao.editInsuranseExpense(insuranceExpenseToUpdate);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	
	
	public String redirectUpdateExpensePage(){
		List<Country> list = cDAO.getCountryList();
		setCountryList(list);
		flightExpense=tripExpenseDao.showFlightExpense(id);
		hotelExpense=tripExpenseDao.showHotelExpense(id);
		carExpense=tripExpenseDao.showCarExpense(id);
		trainExpense=tripExpenseDao.showTrainExpense(id);
		busExpense=tripExpenseDao.showBusExpense(id);
		mealExpense=tripExpenseDao.showMealExpense(id);
		laborExpense=tripExpenseDao.showLaborExpense(id);
		miscellaneousExpense=tripExpenseDao.showMislaniousExpense(id);
		visaExpense=tripExpenseDao.showVisaExpense(id);
		insuranceExpense=tripExpenseDao.showInsurenseExpense(id);


		return SUCCESS;

	}
	

	public FlightExpense getExpenseToView() {
		return expenseToView;
	}


	public void setExpenseToView(FlightExpense expenseToView) {
		this.expenseToView = expenseToView;
	}

	public List<TripRequest> getTripRequests() {
		return tripRequests;
	}
	public void setTripRequests(List<TripRequest> tripRequests) {
		this.tripRequests = tripRequests;
	}


	@Override
	public FlightExpense getModel() {
		// TODO Auto-generated method stub
		return null;
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
	public FlightExpense getFlightExpense() {
		return flightExpense;
	}


	public void setFlightExpense(FlightExpense flightExpense) {
		this.flightExpense = flightExpense;
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
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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
	public String getExpFilePath(){
		 return	getText("global.expense_upload_download_file_path");
		}

}
