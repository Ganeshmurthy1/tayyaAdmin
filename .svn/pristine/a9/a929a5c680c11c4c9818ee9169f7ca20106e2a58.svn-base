package com.admin.flight.fin.sheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.utility.DateConversion;

@Entity
@Table(name = "flight_travel_request")
public class FlightTravelRequest extends Timestampable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Transient
	private String tranDepartureDate;
	@Transient
	private String tranArrivalDate;
	@Transient
	private String userName;
	@Transient
	private String empName;
	@Column(name = "company_id")
	private int companyId;
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "title")
	private String title;

	@Column(name = "created_by_userId")
	private int created_by_userId;
	
	@Column(name = "updated_by_userId")
	private int updated_by_userId;
	@Column(name = "customer_no")
	private String customerNo;

	@Column(name = "project_sub_task_details")
	private String projectSubTaskDetails;

	@Column(name = "currency")
	private String currency;

	@Column(name = "company_entity")
	private String companyEntity;

	@Column(name = "source")
	private String source;

	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "cost_center")
	private String costCenter;

	@Column(name = "travel_request_number")
	private String travelRequestNumber;

	@Column(name = "departure_date")
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	
	@Column(name = "arrival_date")
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;

	@Column(name = "trip_type")
	private String tripType;

	@Column(name = "country")
	private String country;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "origin")
	private String origin;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "passenger_count")
	private int passengerCount;

	@Column(name = "booking_class_prefer")
	private String bookingClassPrefer;

	@Column(name = "airline_prefer")
	private String airlinePrefer;

	@Column(name = "customer_comments")
	private String customerComments;

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
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	 
	public int getCreated_by_userId() {
		return created_by_userId;
	}

	public void setCreated_by_userId(int created_by_userId) {
		this.created_by_userId = created_by_userId;
	}

	public int getUpdated_by_userId() {
		return updated_by_userId;
	}

	public void setUpdated_by_userId(int updated_by_userId) {
		this.updated_by_userId = updated_by_userId;
	}
 
	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return getFirstName()+" "+getLastName();
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProjectSubTaskDetails() {
		return projectSubTaskDetails;
	}

	public void setProjectSubTaskDetails(String projectSubTaskDetails) {
		this.projectSubTaskDetails = projectSubTaskDetails;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCompanyEntity() {
		return companyEntity;
	}

	public void setCompanyEntity(String companyEntity) {
		this.companyEntity = companyEntity;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getTravelRequestNumber() {
		return travelRequestNumber;
	}

	public void setTravelRequestNumber(String travelRequestNumber) {
		this.travelRequestNumber = travelRequestNumber;
	}

	 
	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getAirlinePrefer() {
		return airlinePrefer;
	}

	public void setAirlinePrefer(String airlinePrefer) {
		this.airlinePrefer = airlinePrefer;
	}

	public String getCustomerComments() {
		return customerComments;
	}

	public void setCustomerComments(String customerComments) {
		this.customerComments = customerComments;
	}
 

	public String getUserName() {
		return userName;
	}

	public String getTranDepartureDate() {
		if(getDepartureDate()!=null) 
			tranDepartureDate= DateConversion.convertDateToStringToDate(getDepartureDate());
			return tranDepartureDate;
		 
	}

	public void setTranDepartureDate(String tranDepDate) {
		this.tranDepartureDate = tranDepDate;
	}

	public String getTranArrivalDate() {
		if(getArrivalDate()!=null) 
			tranArrivalDate= DateConversion.convertDateToStringToDate(getArrivalDate());
		 
			return tranArrivalDate;
		 
	}

	public void setTranArrivalDate(String tranArrivalDate) {
		this.tranArrivalDate = tranArrivalDate;
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

	public void setArrivalDate(Date arrivaDate) {
		this.arrivalDate = arrivaDate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmpName() {
		return firstName+" "+lastName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
