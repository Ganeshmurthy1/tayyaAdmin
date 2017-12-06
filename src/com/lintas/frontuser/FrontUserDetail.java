package com.lintas.frontuser;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "front_user_detail")
public class FrontUserDetail  extends Timestampable
{
	private static final long serialVersionUID = 1L;
	@Column(name="userName")
	private String userName; 
	//email;
	@Column(name="password")
	private String password;
	
	@Column(name="title")
	 private String title;
	
	@Column(name="email")
	private String email;
	@Column(name="firstName")
	private String firstName;
	@Column(name="middleName")
	private String middleName;
	@Column(name="lastName")
	private String lastName;
	@Column(name="phone")
	private String phone;
	@Column(name="mobile")
	private String mobile;
	@Column(name="about")
	private String about;
	@Column(name="fax")
	private String fax;
	@Column(name = "status",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int status;
	@Column(name = "role",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int role;
	private String languageCode;
	@Column(name = "isLocked",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int isLocked;

	private Timestamp lockedDate;
	@Column(name="isdCode")
	private String isdCode;
	@Column(name="homeAirport")
	private String homeAirport;
	@Column(name="streetAddress")
	private String streetAddress;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="zipCode")
	private String zipCode;
	@Column(name="country")
	private String country;
	@Transient
	@Column(name="flag")
	private String flag="";
	@Transient
	@Column(name="newPW1")
	private String newPW1;
	@Transient
	@Column(name="newPW2")
	private String newPW2;
	@Column(name="serverUrl")
	private String serverUrl;

	@Column(name="dateofbirth")
	private String dateofbirth;

	@Column(name="imagepath")
	private String imagepath;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "config_id")
	private int configId;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	/*	public String getOauthid() {
		return oauthid;
	}
	public void setOauthid(String oauthid) {
		this.oauthid = oauthid;
	}*/
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public int getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}
	public Timestamp getLockedDate() {
		return lockedDate;
	}
	public void setLockedDate(Timestamp lockedDate) {
		this.lockedDate = lockedDate;
	}
	public String getIsdCode() {
		return isdCode;
	}
	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}
	public String getHomeAirport() {
		return homeAirport;
	}
	public void setHomeAirport(String homeAirport) {
		this.homeAirport = homeAirport;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNewPW1() {
		return newPW1;
	}
	public void setNewPW1(String newPW1) {
		this.newPW1 = newPW1;
	}
	public String getNewPW2() {
		return newPW2;
	}
	public void setNewPW2(String newPW2) {
		this.newPW2 = newPW2;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getConfigId() {
		return configId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}


}