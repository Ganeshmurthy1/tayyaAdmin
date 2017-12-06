package com.lintas.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="flight_markup")
public class FlightMarkup implements Serializable {

	/**@author info name:raham
	 * created Date:3-08-2015
	 * 
	 */
	private static final long serialVersionUID = -5621406142052450418L;
	@Id
	@GeneratedValue
	private int markupId;
	@Column(name="name")
	private String name;
	@Column(name="companyId")
	private int companyId;
	@Column(name="configId")
	private int configId;
	@Column(name="company_name")
	private String companyName;
	@Column(name="created_by_company_name")
	private String  createdByCompanyName;
	
	@Column(name="fare_base_code")
	private String  fareBaseCode;
	
	@Transient
	@Column(name="company_user_id")
	private String  companyUserId;
	
	
	/*@Column(name="booking-class-code")
	private String bookingClassCode;*/
	/*@Column(name="fare_type")
	private String fareType;*/
	@Column(name="configname")
	private String  configname;
	private String origin;
	private String destination;
	private String arrvDate;
	private String deptDate;
 	private String country; 
	@Column(name="accumulative")
	private String accumulative;//If this is true we shud apply all the Markups
	@Column(name="fixedAmount")
	private String fixedAmount;
	@Column(name="markup")
	private String markup;
	@Column(name="positionOfMarkup")
	private int positionOfMarkup;//it can 1-10 like priority
	@Column(name="airline")
	private String airline;
	@Column(name="classOfService")
	private String classOfService;//class Economy and Business  
	@Column(name="createdby_company_id")
	private int createdbyCompanyId;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="modified_date")
	private Date modifiedDate;
	@Column(name="config_number")
	private String config_number;
	@Column(name="createdby_userId")
	private int createdbyUserId;
	@Column(name="modifiedby_userId")
	private int modifiedbyUserId;
	@Column(name="promofare_start_date")
	private String promofareStartDate;
	@Column(name="promofare_end_date")
	private String promofareEndDate;
	@Column(name="destination_type")
	private String destinationType;
	
	public String getCountry() {
		return country;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getArrvDate() {
		return arrvDate;
	}
	public void setArrvDate(String arrvDate) {
		this.arrvDate = arrvDate;
	}
	public String getDeptDate() {
		return deptDate;
	}
	public void setDeptDate(String deptDate) {
		this.deptDate = deptDate;
	}
 
	public String getConfigname() {
		return configname;
	}
	public void setConfigname(String configname) {
		this.configname = configname;
	}

	public int getMarkupId() {
		return markupId;
	}
	public void setMarkupId(int markupId) {
		this.markupId = markupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getAccumulative() {
		return accumulative;
	}
	public void setAccumulative(String accumulative) {
		this.accumulative = accumulative;
	}
	public String getFixedAmount() {
		return fixedAmount;
	}
	public void setFixedAmount(String fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	public String getMarkup() {
		return markup;
	}
	public void setMarkup(String markup) {
		this.markup = markup;
	}
	public int getPositionOfMarkup() {
		return positionOfMarkup;
	}
	public void setPositionOfMarkup(int positionOfMarkup) {
		this.positionOfMarkup = positionOfMarkup;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getClassOfService() {
		return classOfService;
	}
	public void setClassOfService(String classOfService) {
		this.classOfService = classOfService;
	}
	 
	 
	public String getConfig_number() {
		return config_number;
	}
	public void setConfig_number(String config_number) {
		this.config_number = config_number;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	 
	public int getModifiedbyUserId() {
		return modifiedbyUserId;
	}
	public void setModifiedbyUserId(int modifiedbyUserId) {
		this.modifiedbyUserId = modifiedbyUserId;
	}
	public int getCreatedbyUserId() {
		return createdbyUserId;
	}
	public void setCreatedbyUserId(int createdbyUserId) {
		this.createdbyUserId = createdbyUserId;
	}
	public int getCreatedbyCompanyId() {
		return createdbyCompanyId;
	}
	public void setCreatedbyCompanyId(int createdbyCompanyId) {
		this.createdbyCompanyId = createdbyCompanyId;
	}
	public String getPromofareStartDate() {
		return promofareStartDate;
	}
	public void setPromofareStartDate(String promofareStartDate) {
		this.promofareStartDate = promofareStartDate;
	}
	public String getPromofareEndDate() {
		return promofareEndDate;
	}
	public void setPromofareEndDate(String promofareEndDate) {
		this.promofareEndDate = promofareEndDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
/*	public String getBookingClassCode() {
		return bookingClassCode;
	}
	public void setBookingClassCode(String bookingClassCode) {
		this.bookingClassCode = bookingClassCode;
	}*/
	public String getFareBaseCode() {
		return fareBaseCode;
	}
	public void setFareBaseCode(String fareBaseCode) {
		this.fareBaseCode = fareBaseCode;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public String getCompanyUserId() {
		return companyUserId;
	}
	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}

	
	public String getCreatedByCompanyName() {
		return createdByCompanyName;
	}
	public void setCreatedByCompanyName(String createdByCompanyName) {
		this.createdByCompanyName = createdByCompanyName;
	}

}
