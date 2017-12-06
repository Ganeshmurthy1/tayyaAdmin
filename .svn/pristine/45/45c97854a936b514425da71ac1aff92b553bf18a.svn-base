package com.admin.flight.fin.sheet.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightTravelRequestTripDetail;
import com.lintas.utility.DateConversion;
@Entity
@Table(name="flight_travel_request_quotation")
public class FlightTravelRequestQuotation  extends Timestampable{

	private static final long serialVersionUID = 1L;
	@Transient
	private FlightOrderRow flightOrderRow;
	@Transient
	private FlightOrderRow returnflightOrderRow;
	@Transient
	private String transDepartureDate;
	@Transient
	private String transArrivalDate;
	@Transient
	private String onlineUrl; 
	@Column(name = "order_row_id")
	private long orderRowId;
	@Column(name = "return_order_row_id")
	private long returnOrderRowId;
	@Column(name="additional_data",columnDefinition="text")
	private String additionalData;
	@Column(name = "total_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totalAmount;
	
	@Column(name = "toatl_duration")
	 private String totalDuration;
	
	@Column(name = "base_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal baseAmount;

	@Column(name = "tax_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal taxAmount;
	@Column(name = "airline")
	private String airline;
	@Column(name = "is_hide")
	private boolean isHide;
	@Column(name = "is_booked")
	private boolean  isBooked;
	@Column(name = "customer_comments")
	private String customerComments; 
	@Column(name = "api_comments")
	private String apiComments; 
	@Column(name = "departure_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDate;

	@Column(name = "adult_count")
	private int adultCount;

	
	@Column(name = "child_count")
	private int childCount;
	
	@Column(name = "infant_count")
	private int infantCount;
	
	
	@Column(name = "arrival_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDate;
	
	@Column(name = "origin")
	private String origin; 
	@Column(name = "destination")
	private String destination; 
	
	@Column(name = "trip_type")
	private String tripType;

	@Column(name = "booking_class_prefer")
	private String bookingClassPrefer;

	@Column(name = "passenger_count")
	private int passengerCount;

	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "hotel_flight_quotation_status_id", referencedColumnName = "id")
	private HotelFlightBookingStatus hotetFlightBookingStatus; 
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "company_id")
	private int companyId;
	
	public String getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(String totalDuration) {
		this.totalDuration = totalDuration;
	}

	public HotelFlightBookingStatus getHotetFlightBookingStatus() {
		return hotetFlightBookingStatus;
	}

	public void setHotetFlightBookingStatus(HotelFlightBookingStatus hotetFlightBookingStatus) {
		this.hotetFlightBookingStatus = hotetFlightBookingStatus;
	}

	

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	public String getBookingClassPrefer() {
		return bookingClassPrefer;
	}

	public void setBookingClassPrefer(String bookingClassPrefer) {
		this.bookingClassPrefer = bookingClassPrefer;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public String getTransDepartureDate() {
		if(getDepartureDate()!=null){
			transDepartureDate=DateConversion.convertDateToStringToDate(getDepartureDate());
		}
	 
		return transDepartureDate;
	}

	public void setTransDepartureDate(String transDepartureDate) {
		this.transDepartureDate = transDepartureDate;
	}

	public String getTransArrivalDate() {
		if(getArrivalDate()!=null){
			transArrivalDate=DateConversion.convertDateToStringToDate(getArrivalDate());
		}
		return transArrivalDate;
	}

	public void setTransArrivalDate(String transArrivalDate) {
		this.transArrivalDate = transArrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
 
	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "flight_travel_request_id", referencedColumnName = "id")
	private FlightTravelRequest flightTravelRequest; 

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightTravelRequest")
	private List<FlightTravelRequestTripDetail> flightTravelRequestTripDetails;

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getCustomerComments() {
		return customerComments;
	}

	public void setCustomerComments(String customerComments) {
		this.customerComments = customerComments;
	}

	public String getApiComments() {
		return apiComments;
	}

	public void setApiComments(String apiComments) {
		this.apiComments = apiComments;
	}

	public FlightTravelRequest getFlightTravelRequest() {
		return flightTravelRequest;
	}

	public void setFlightTravelRequest(FlightTravelRequest flightTravelRequest) {
		this.flightTravelRequest = flightTravelRequest;
	}

	public List<FlightTravelRequestTripDetail> getFlightTravelRequestTripDetails() {
		return flightTravelRequestTripDetails;
	}

	public void setFlightTravelRequestTripDetails(List<FlightTravelRequestTripDetail> flightTravelRequestTripDetails) {
		this.flightTravelRequestTripDetails = flightTravelRequestTripDetails;
	}



	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public boolean isHide() {
		return isHide;
	}

	public void setHide(boolean isHide) {
		this.isHide = isHide;
	}

	public long getOrderRowId() {
		return orderRowId;
	}

	public void setOrderRowId(long orderRowId) {
		this.orderRowId = orderRowId;
	}
 
	public FlightOrderRow getReturnflightOrderRow() {
		if(returnOrderRowId!=0){
			returnflightOrderRow = new FlightOrderDao().getFlightOrderRowInfo(returnOrderRowId);		 
		}
		return returnflightOrderRow;
	}

	public void setReturnflightOrderRow(FlightOrderRow returnflightOrderRow) {
		this.returnflightOrderRow = returnflightOrderRow;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
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
	public int getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(int adultCount) {
		this.adultCount = adultCount;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public int getInfantCount() {
		return infantCount;
	}

	public void setInfantCount(int infantCount) {
		this.infantCount = infantCount;
	}

	public FlightOrderRow getFlightOrderRow() {
		if(orderRowId!=0){
			flightOrderRow =new FlightOrderDao().getFlightOrderRowInfo(orderRowId);
		 
		}
		return flightOrderRow;
	}

	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}

	public String getOnlineUrl() {
		return onlineUrl;
	}

	public void setOnlineUrl(String onlineUrl) {
		this.onlineUrl = onlineUrl;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public long getReturnOrderRowId() {		
		return returnOrderRowId;
	}

	public void setReturnOrderRowId(long returnOrderRowId) {
		this.returnOrderRowId = returnOrderRowId;
	}

}
