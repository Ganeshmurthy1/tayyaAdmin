/**
 * 
 */
package com.admin.api.analytics.service;

import java.util.List;

import com.admin.api.analytics.pojo.FlightPojo;
import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.admin.DAO.TrainOrderDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.utility.DateConversion;

/**
 * @author MANISH
 *
 */
public class FlightAnalysisService {

	TrainTravelRequestDao trainTravelRequestDao=new TrainTravelRequestDao();
	public List<FlightPojo> setIntoFlightPojo(List<FlightPojo> flightTopFivePojoList,List<FlightOrderRow> flightOrderRowList){
		
		for(FlightOrderRow flightOrderRow:flightOrderRowList){
			FlightPojo flightTopFivePojo =new FlightPojo();
			flightTopFivePojo.setAgencyName(flightOrderRow.getCreatedBy()!=null?flightOrderRow.getCreatedBy():"-");
			flightTopFivePojo.setDepartureDate(flightOrderRow.getDepartureDate()!=null?flightOrderRow.getDepartureDate():"-");
			try{
				flightTopFivePojo.setName(flightOrderRow.getFlightCustomer().getTitle()+" "+flightOrderRow.getFlightCustomer().getFirstName()+" "+flightOrderRow.getFlightCustomer().getLastName());
			}catch (Exception e) {
				flightTopFivePojo.setName("-");
			}
			try{
				flightTopFivePojo.setSource(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
			}catch (Exception e) {
				flightTopFivePojo.setSource("-");
			}
			flightTopFivePojo.setPnr(flightOrderRow.getPnr()!=null?flightOrderRow.getPnr():"-");
			
			flightTopFivePojo.setStatus(flightOrderRow.getStatusAction()!=null?flightOrderRow.getStatusAction():"-");
			flightTopFivePojoList.add(flightTopFivePojo);
		}
		return flightTopFivePojoList;
	}
public List<FlightPojo> setIntoHotelPojo(List<FlightPojo> flightTopFivePojoList,List<HotelOrderRow> hotelOrderRowList){
		
		for(HotelOrderRow hotelOrderRow:hotelOrderRowList){
			FlightPojo flightTopFivePojo =new FlightPojo();
			flightTopFivePojo.setAgencyName(hotelOrderRow.getCreatedBy()!=null?hotelOrderRow.getCreatedBy():"-");
			try{
				flightTopFivePojo.setName(hotelOrderRow.getOrderCustomer().getTitle()+" "+hotelOrderRow.getOrderCustomer().getFirstName()+" "+hotelOrderRow.getOrderCustomer().getLastName());
			}catch (Exception e) {
				flightTopFivePojo.setName("-");
			}
			flightTopFivePojo.setHotelCheckInDate(DateConversion.convertDateToStringDate(hotelOrderRow.getCheckInDate()));
			flightTopFivePojo.setOrderId(hotelOrderRow.getOrderReference());
			flightTopFivePojo.setCity(hotelOrderRow.getOrderCustomer().getCity());
			
			/*flightTopFivePojo.setDepartureDate(flightOrderRow.getDepartureDate()!=null?flightOrderRow.getDepartureDate():"-");
			
			}
			try{
				flightTopFivePojo.setSource(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
			}catch (Exception e) {
				flightTopFivePojo.setSource("-");
			}
			flightTopFivePojo.setPnr(flightOrderRow.getPnr()!=null?flightOrderRow.getPnr():"-");
			*/
			flightTopFivePojo.setStatus(hotelOrderRow.getStatusAction()!=null?hotelOrderRow.getStatusAction():"-");
			flightTopFivePojoList.add(flightTopFivePojo);
		}
		return flightTopFivePojoList;
	}

public List<FlightPojo> setIntoBusPojo(List<FlightPojo> flightTopFivePojoList,List<BusOrderRow> flightOrderRowList){
	
	for(BusOrderRow busOrderRow:flightOrderRowList){
		FlightPojo flightTopFivePojo =new FlightPojo();
		flightTopFivePojo.setAgencyName(busOrderRow.getCreatedBy()!=null?busOrderRow.getCreatedBy():"-");
		try{
			flightTopFivePojo.setName(busOrderRow.getOrderCustomer().getTitle()+" "+busOrderRow.getOrderCustomer().getFirstName()+" "+busOrderRow.getOrderCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		flightTopFivePojo.setOrderId(busOrderRow.getOrderId());
		flightTopFivePojo.setBookingDate(DateConversion.convertDateToStringDate(busOrderRow.getBookingDate()));
		flightTopFivePojo.setCity(busOrderRow.getOrigin() );
		/*flightTopFivePojo.setDepartureDate(flightOrderRow.getDepartureDate()!=null?flightOrderRow.getDepartureDate():"-");
		
		try{
			flightTopFivePojo.setSource(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
		}catch (Exception e) {
			flightTopFivePojo.setSource("-");
		}
		flightTopFivePojo.setPnr(flightOrderRow.getPnr()!=null?flightOrderRow.getPnr():"-");
		*/
		flightTopFivePojo.setStatus(busOrderRow.getStatusAction()!=null?busOrderRow.getStatusAction():"-");
		flightTopFivePojoList.add(flightTopFivePojo);
	}
	return flightTopFivePojoList;
}
public List<FlightPojo> setIntoCarPojo(List<FlightPojo> flightTopFivePojoList,List<CarOrderRow> flightOrderRowList){
	
	for(CarOrderRow carOrderRow:flightOrderRowList){
		FlightPojo flightTopFivePojo =new FlightPojo();
		flightTopFivePojo.setAgencyName(carOrderRow.getCreatedBy()!=null?carOrderRow.getCreatedBy():"-");
		
		try{
			flightTopFivePojo.setName(carOrderRow.getOrderCustomer().getTitle()+" "+carOrderRow.getOrderCustomer().getFirstName()+" "+carOrderRow.getOrderCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		flightTopFivePojo.setOrderId(carOrderRow.getOrderId());
		flightTopFivePojo.setBookingDate(DateConversion.convertDateToStringDate(carOrderRow.getBookingDate()));
		flightTopFivePojo.setCity(carOrderRow.getLocation());
		/*flightTopFivePojo.setDepartureDate(flightOrderRow.getDepartureDate()!=null?flightOrderRow.getDepartureDate():"-");
		try{
			flightTopFivePojo.setName(flightOrderRow.getFlightCustomer().getTitle()+" "+flightOrderRow.getFlightCustomer().getFirstName()+" "+flightOrderRow.getFlightCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		try{
			flightTopFivePojo.setSource(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
		}catch (Exception e) {
			flightTopFivePojo.setSource("-");
		}
		flightTopFivePojo.setPnr(flightOrderRow.getPnr()!=null?flightOrderRow.getPnr():"-");
		*/
		flightTopFivePojo.setStatus(carOrderRow.getStatusAction()!=null?carOrderRow.getStatusAction():"-");
		flightTopFivePojoList.add(flightTopFivePojo);
	}
	return flightTopFivePojoList;
}

public List<FlightPojo> setIntoTrainPojo(List<FlightPojo> flightTopFivePojoList,List<TrainOrderRow> flightOrderRowList){
	
	for(TrainOrderRow trainOrderRow:flightOrderRowList){
		FlightPojo flightTopFivePojo =new FlightPojo();
		flightTopFivePojo.setAgencyName(trainOrderRow.getCreatedBy()!=null?trainOrderRow.getCreatedBy():"-");
		
		try{
			flightTopFivePojo.setName(trainOrderRow.getOrderCustomer().getTitle()+" "+trainOrderRow.getOrderCustomer().getFirstName()+" "+trainOrderRow.getOrderCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		flightTopFivePojo.setOrderId(trainOrderRow.getOrderId());
		flightTopFivePojo.setBookingDate(DateConversion.convertDateToStringDate(trainOrderRow.getBookingDate()));
		//flightTopFivePojo.setCity(trainOrderRow.getOrderCustomer().getCity());
		TrainTravelRequestQuotation trainTravelRequestQuotation =trainTravelRequestDao.getTrainTravelRequestQuotationDetailsByTrainOrderRowId(trainOrderRow.getId());
		flightTopFivePojo.setFromLocation(trainTravelRequestQuotation.getFromlocation());
		/*flightTopFivePojo.setDepartureDate(flightOrderRow.getDepartureDate()!=null?flightOrderRow.getDepartureDate():"-");
		try{
			flightTopFivePojo.setName(flightOrderRow.getFlightCustomer().getTitle()+" "+flightOrderRow.getFlightCustomer().getFirstName()+" "+flightOrderRow.getFlightCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		try{
			flightTopFivePojo.setSource(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
		}catch (Exception e) {
			flightTopFivePojo.setSource("-");
		}
		flightTopFivePojo.setPnr(flightOrderRow.getPnr()!=null?flightOrderRow.getPnr():"-");
		*/
		flightTopFivePojo.setStatus(trainOrderRow.getStatusAction()!=null?trainOrderRow.getStatusAction():"-");
		flightTopFivePojoList.add(flightTopFivePojo);
	}
	return flightTopFivePojoList;
}

public List<FlightPojo> setIntoVisaPojo(List<FlightPojo> flightTopFivePojoList,List<VisaOrderRow> flightOrderRowList){
	
	for(VisaOrderRow visaOrderRow:flightOrderRowList){
		FlightPojo flightTopFivePojo =new FlightPojo();
		flightTopFivePojo.setAgencyName(visaOrderRow.getCreatedBy()!=null?visaOrderRow.getCreatedBy():"-");
		try{
			flightTopFivePojo.setName(visaOrderRow.getOrderCustomer().getTitle()+" "+visaOrderRow.getOrderCustomer().getFirstName()+" "+visaOrderRow.getOrderCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		flightTopFivePojo.setOrderId(visaOrderRow.getOrderId());
		flightTopFivePojo.setBookingDate(DateConversion.convertDateToStringDate(visaOrderRow.getBookingDate()));
		flightTopFivePojo.setCity(visaOrderRow.getOrderCustomer().getCity());
		/*flightTopFivePojo.setDepartureDate(flightOrderRow.getDepartureDate()!=null?flightOrderRow.getDepartureDate():"-");
		try{
			flightTopFivePojo.setName(flightOrderRow.getFlightCustomer().getTitle()+" "+flightOrderRow.getFlightCustomer().getFirstName()+" "+flightOrderRow.getFlightCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		try{
			flightTopFivePojo.setSource(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
		}catch (Exception e) {
			flightTopFivePojo.setSource("-");
		}
		flightTopFivePojo.setPnr(flightOrderRow.getPnr()!=null?flightOrderRow.getPnr():"-");
		*/
		flightTopFivePojo.setStatus(visaOrderRow.getStatusAction()!=null?visaOrderRow.getStatusAction():"-");
		flightTopFivePojoList.add(flightTopFivePojo);
	}
	return flightTopFivePojoList;
}
public List<FlightPojo> setIntoInsurancePojo(List<FlightPojo> flightTopFivePojoList,List<InsuranceOrderRow> flightOrderRowList){
	
	for(InsuranceOrderRow insuranceOrderRow:flightOrderRowList){
		FlightPojo flightTopFivePojo =new FlightPojo();
		flightTopFivePojo.setAgencyName(insuranceOrderRow.getCreatedBy()!=null?insuranceOrderRow.getCreatedBy():"-");
		try{
			flightTopFivePojo.setName(insuranceOrderRow.getOrderCustomer().getTitle()+" "+insuranceOrderRow.getOrderCustomer().getFirstName()+" "+insuranceOrderRow.getOrderCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		flightTopFivePojo.setOrderId(insuranceOrderRow.getOrderId());
		flightTopFivePojo.setBookingDate(DateConversion.convertDateToStringDate(insuranceOrderRow.getBookingDate()));
		flightTopFivePojo.setCity(insuranceOrderRow.getOrderCustomer().getCity());
		/*flightTopFivePojo.setDepartureDate(flightOrderRow.getDepartureDate()!=null?flightOrderRow.getDepartureDate():"-");
		try{
			flightTopFivePojo.setName(flightOrderRow.getFlightCustomer().getTitle()+" "+flightOrderRow.getFlightCustomer().getFirstName()+" "+flightOrderRow.getFlightCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		try{
			flightTopFivePojo.setSource(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
		}catch (Exception e) {
			flightTopFivePojo.setSource("-");
		}
		flightTopFivePojo.setPnr(flightOrderRow.getPnr()!=null?flightOrderRow.getPnr():"-");
		*/
		flightTopFivePojo.setStatus(insuranceOrderRow.getStatusAction()!=null?insuranceOrderRow.getStatusAction():"-");
		flightTopFivePojoList.add(flightTopFivePojo);
	}
	return flightTopFivePojoList;
}
public List<FlightPojo> setIntoMiscellaneousPojo(List<FlightPojo> flightTopFivePojoList,List<MiscellaneousOrderRow> flightOrderRowList){
	
	for(MiscellaneousOrderRow miscOrderRow:flightOrderRowList){
		FlightPojo flightTopFivePojo =new FlightPojo();
		flightTopFivePojo.setAgencyName(miscOrderRow.getCreatedBy()!=null?miscOrderRow.getCreatedBy():"-");
		try{
			flightTopFivePojo.setName(miscOrderRow.getOrderCustomer().getTitle()+" "+miscOrderRow.getOrderCustomer().getFirstName()+" "+miscOrderRow.getOrderCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		flightTopFivePojo.setOrderId(miscOrderRow.getOrderId());
		flightTopFivePojo.setBookingDate(DateConversion.convertDateToStringDate(miscOrderRow.getBookingDate()));
		flightTopFivePojo.setCity(miscOrderRow.getOrderCustomer().getCity());
		/*flightTopFivePojo.setDepartureDate(flightOrderRow.getDepartureDate()!=null?flightOrderRow.getDepartureDate():"-");
		try{
			flightTopFivePojo.setName(flightOrderRow.getFlightCustomer().getTitle()+" "+flightOrderRow.getFlightCustomer().getFirstName()+" "+flightOrderRow.getFlightCustomer().getLastName());
		}catch (Exception e) {
			flightTopFivePojo.setName("-");
		}
		try{
			flightTopFivePojo.setSource(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());
		}catch (Exception e) {
			flightTopFivePojo.setSource("-");
		}
		flightTopFivePojo.setPnr(flightOrderRow.getPnr()!=null?flightOrderRow.getPnr():"-");
		*/
		flightTopFivePojo.setStatus(miscOrderRow.getStatusAction()!=null?miscOrderRow.getStatusAction():"-");
		flightTopFivePojoList.add(flightTopFivePojo);
	}
	return flightTopFivePojoList;
}

}
