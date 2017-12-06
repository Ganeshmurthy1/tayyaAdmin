package com.lintas.admin.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_customer")
public class OrderCustomer extends Timestampable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "title")
	private String title;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "age")
	private String age;

	@Column(name = "address")
	private String address;

	@Column(name = "address2")
	private String address2;

	@Column(name = "zip")
	private String zip;

	@Column(name = "city")
	private String city;

	@Column(name = "phone")
	private String phone;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;


	@Column(name = "gender")
	private String gender;

	@Column(name = "countryid")
	private String countryId;

	@Column(name = "state")
	private String state;
	
	@Column(name = "company_id",columnDefinition="int default 0")
	private Integer companyId;
	
	@Column(name = "config_id",columnDefinition="int default 0")
	private Integer configId;
	
	@Column(name = "created_by_user_id",columnDefinition="int default 0")
	private Integer createdByUserId;
	
	@Column(name = "booking_type")
	private String bookingType;
	
	@Column(name = "order_id")
	private String orderId;
	
 /*	@Column(name = "middleName")
	 private String middleName;*/
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	
	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	

	public Integer getCompanyId() {
		return companyId;
	}

	public Integer getConfigId() {
		return configId;
	}

	public Integer getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public void setCreatedByUserId(Integer createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	/* public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	} */


}
