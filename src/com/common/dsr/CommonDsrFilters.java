package com.common.dsr;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.isl.admin.filter.Filter;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;

public class CommonDsrFilters extends Filter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String travelType;
	private String travelReportType;
	private String companyName;
	private List<String> hotelReportsList;
	private List<String> flightReportsList;
	private String fromDate;
	private String toDate;
	private String companyUserId="";
	private String bookingStatus;
	private String airline;
	private String  bookingClass;
	private String pnr;
	private String origin;
	private String destination;
	private String orderReference;
	private String city;
	private String country;
	private String confirmationNumber;
	private String bookingMode;
	private String destinationType;
	private String  hotelDestinationType;
	private String bookingDate;
	private String travelDate;
	private String bookingDateForAdvancePurchase;
	private String travelDateForAdvancePurchase;
	private String hotelbookingDateForAdvancePurchase;
	private String hoteltravelDateForAdvancePurchase;
	private String airPlannedTripFromTravelDate;
	private String airPlannedTripToTravelDate;
	 
	
	public CommonDsrFilters(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super(loginCompanyConfig, loginCompany, loginUser, reportType);
		// TODO Auto-generated constructor stub
	}
	public CommonDsrFilters(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super(loginCompanyConfig, loginCompany, loginUser);
		// TODO Auto-generated constructor stub
	}
	public CommonDsrFilters() {
		super();
		this.hotelReportsList = getHotelReportsList();
		this.flightReportsList = getFlightReportsList();
	}
	public List<String> getHotelReportsList() {
		if(hotelReportsList==null){
			hotelReportsList=new LinkedList<>();
			hotelReportsList.add("HotelName");
			hotelReportsList.add("CheckIn");
			hotelReportsList.add("CheckOut");
			hotelReportsList.add("Country");
			hotelReportsList.add("ArrivalDate");
			hotelReportsList.add("Email");
			hotelReportsList.add("Mobile");
			hotelReportsList.add("Supplier");
			hotelReportsList.add("Wallet");
			hotelReportsList.add("Card");
			hotelReportsList.add("PaymentGateway");
			hotelReportsList.add("Rating");
		}

		return hotelReportsList;
	}
	public void setHotelReportsList(List<String> hotelReportsList) {
		this.hotelReportsList = hotelReportsList;
	}
	public List<String> getFlightReportsList() {
		if(flightReportsList==null){
			flightReportsList=new LinkedList<>();
			flightReportsList.add("Airline");
			flightReportsList.add("Origin");
			flightReportsList.add("Destination");
			flightReportsList.add("DepartureDate");
			flightReportsList.add("ArrivalDate");
			flightReportsList.add("OneWay");
			flightReportsList.add("RoundTrip");
			flightReportsList.add("Supplier");
			flightReportsList.add("Wallet");
			flightReportsList.add("Card");
			flightReportsList.add("PaymentGateway");
			flightReportsList.add("Country");
			flightReportsList.add("Email");
			flightReportsList.add("Mobile");
		}
		return flightReportsList;
	}
	public void setFlightReportsList(List<String> flightReportsList) {
		this.flightReportsList = flightReportsList;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getTravelReportType() {
		return travelReportType;
	}
	public void setTravelReportType(String travelReportType) {
		this.travelReportType = travelReportType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getCompanyUserId() {
		return companyUserId;
	}
	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	 
	public String getBookingClass() {
		return bookingClass;
	}
	public String getPnr() {
		return pnr;
	}
	 
	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getOrderReference() {
		return orderReference;
	}
	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	public String getBookingMode() {
		return bookingMode;
	}
	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public String getHotelDestinationType() {
		return hotelDestinationType;
	}
	public void setHotelDestinationType(String hotelDestinationType) {
		this.hotelDestinationType = hotelDestinationType;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public String getBookingDateForAdvancePurchase() {
		return bookingDateForAdvancePurchase;
	}
	public void setBookingDateForAdvancePurchase(String bookingDateForAdvancePurchase) {
		this.bookingDateForAdvancePurchase = bookingDateForAdvancePurchase;
	}
	public String getTravelDateForAdvancePurchase() {
		return travelDateForAdvancePurchase;
	}
	public void setTravelDateForAdvancePurchase(String travelDateForAdvancePurchase) {
		this.travelDateForAdvancePurchase = travelDateForAdvancePurchase;
	}
	public String getHotelbookingDateForAdvancePurchase() {
		return hotelbookingDateForAdvancePurchase;
	}
	public String getHoteltravelDateForAdvancePurchase() {
		return hoteltravelDateForAdvancePurchase;
	}
	public void setHotelbookingDateForAdvancePurchase(String hotelbookingDateForAdvancePurchase) {
		this.hotelbookingDateForAdvancePurchase = hotelbookingDateForAdvancePurchase;
	}
	public void setHoteltravelDateForAdvancePurchase(String hoteltravelDateForAdvancePurchase) {
		this.hoteltravelDateForAdvancePurchase = hoteltravelDateForAdvancePurchase;
	}
	public String getAirPlannedTripFromTravelDate() {
		return airPlannedTripFromTravelDate;
	}
	public String getAirPlannedTripToTravelDate() {
		return airPlannedTripToTravelDate;
	}
	public void setAirPlannedTripFromTravelDate(String airPlannedTripFromTravelDate) {
		this.airPlannedTripFromTravelDate = airPlannedTripFromTravelDate;
	}
	public void setAirPlannedTripToTravelDate(String airPlannedTripToTravelDate) {
		this.airPlannedTripToTravelDate = airPlannedTripToTravelDate;
	} 
	 
}
