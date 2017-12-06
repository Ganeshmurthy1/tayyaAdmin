

package com.admin.hotel.fin.sheet.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.utility.DateConversion;
@Entity
@Table(name="hotel_travel_request")
public class HotelTravelRequest extends Timestampable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private String checkIn;
	@Transient
	private String checkOut;

	@OneToMany(targetEntity = HotelTravelRequestQuotation.class,   mappedBy = "hotelTravelRequest" , cascade = CascadeType.ALL)
	private List<HotelTravelRequestQuotation> hotelTravelRequestQuotation; // list of rooms


	@Column(name = "currency")
	private String currency;
	@Column(name = "company_id" ,columnDefinition="INT(11) default 0")
	private int companyId;
	@Column(name = "user_id",columnDefinition="INT(11) default 0")
	private int userId;
	@Column(name="emp_name")
	private String empName;
	@Column(name = "city_code")
	 private int cityCode;
	@Column(name = "status_id")
	 private int statusId;
	@Column(name="entity")
	private String entity;

	@Column(name="tr_no")
	private String TRNo;
	@Column(name = "check_in_date")
	@Temporal(TemporalType.DATE)
	private Date checkInDate;
	@Column(name = "check_out_date")
	@Temporal(TemporalType.DATE)
	private Date checkOutDate;
	@Column(name="no_nights")
	private int noOfNights;
	@Column(name="country")
	private String country;
	@Column(name="city")
	private String city;
	@Column(name="title")
	private String title;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="alternative_email")
	private String alternativeEmail;
	
	
	
	public String getCheckIn() {
		if(checkIn==null){
			checkIn=DateConversion.convertDateToStringToDate(getCheckInDate());
		}
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		if(checkOut==null){
			checkOut=DateConversion.convertDateToStringToDate(getCheckOutDate());
		}
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}



	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getEmpName() {
		 return  getFirstName()+" "+getLastName();
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	 

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNoOfNights() {
		return noOfNights;
	}

	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
	public List<HotelTravelRequestQuotation> getHotelTravelRequestQuotation() {
		return hotelTravelRequestQuotation;
	}

	public void setHotelTravelRequestQuotation(List<HotelTravelRequestQuotation> hotelTravelRequestQuotation) {
		this.hotelTravelRequestQuotation = hotelTravelRequestQuotation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getAlternativeEmail() {
		return alternativeEmail;
	}

	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}

	public String getTRNo() {
		return TRNo;
	}

	public void setTRNo(String tRNo) {
		TRNo = tRNo;
	}
}