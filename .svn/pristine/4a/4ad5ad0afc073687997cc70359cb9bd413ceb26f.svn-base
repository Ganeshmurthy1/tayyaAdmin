package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the hotel_markup database table.
 * 
 */
@Entity
@Table(name="hotel_markup")
 @NamedQuery(name="HotelMarkup.findAll", query="SELECT h FROM HotelMarkup h") 
public class HotelMarkup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	@Column(name="check_in_date")
	private String checkIn;
	@Transient
	@Column(name="check_out_date")
	private String checkOut;
	
	@Transient
	@Column(name="company_user_id")
	private String  companyUserId;
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO) 
	private int id;

	@Column(name="company_id")
	private int companyId;

	@Column(name="company_name")
	private String  companyName;
	@Column(name="created_by_company_name")
	private String  createdByCompanyName;
	 
	public String getCreatedByCompanyName() {
		return createdByCompanyName;
	}

	public void setCreatedByCompanyName(String createdByCompanyName) {
		this.createdByCompanyName = createdByCompanyName;
	}

	@Column(name="config_id")
	private int configId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="hotel_chain")
	private String hotelChain;

	 @Temporal(TemporalType.TIMESTAMP)
	@Column(name="hotel_checkin_date")
	private Date hotelCheckinDate; 
	 @Temporal(TemporalType.TIMESTAMP)
	@Column(name="hotel_checkout_date")
	private Date hotelCheckoutDate;
 
	@Column(name="hotel_city")
	private String hotelCity;

	@Column(name="hotel_country")
	private String hotelCountry;

	@Column(name="hotel_name")
	private String hotelName;

	private byte isaccumulative;

	@Column(name="isfixed_amount")
	private byte isfixedAmount;

	@Column(name="markup_amount")
	private BigDecimal markupAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;
	@Column(name="createdby_userId")
	private int createdbyUserId;
	@Column(name="modifiedby_userId")
	private int modifiedbyUserId;
	@Column(name="createdby_company_id")
	private int createdbyCompanyId;
	@Column(name="config_number")
	private String config_number;
	@Column(name="configname")
	private String  configname;
	@Column(name="position_markup")
	private int positionMarkup;
	@Column(name="promofare_start_date")
	private String promofareStartDate;
	@Column(name="promofare_end_date")
	private String promofareEndDate;
	@Column(name="destination_type")
	private String destinationType;
	 public HotelMarkup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getConfigId() {
		return this.configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public String getHotelChain() {
		return this.hotelChain;
	}

	public void setHotelChain(String hotelChain) {
		this.hotelChain = hotelChain;
	}

	public Date getHotelCheckinDate() {
		return this.hotelCheckinDate;
	}

	public void setHotelCheckinDate(Date hotelCheckinDate) {
		this.hotelCheckinDate = hotelCheckinDate;
	}

	public Date getHotelCheckoutDate() {
		return this.hotelCheckoutDate;
	}

	public void setHotelCheckoutDate(Date hotelCheckoutDate) {
		this.hotelCheckoutDate = hotelCheckoutDate;
	}

	public String getHotelCity() {
		return this.hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelCountry() {
		return this.hotelCountry;
	}

	public void setHotelCountry(String hotelCountry) {
		this.hotelCountry = hotelCountry;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public byte getIsaccumulative() {
		return this.isaccumulative;
	}

	public void setIsaccumulative(byte isaccumulative) {
		this.isaccumulative = isaccumulative;
	}

	public byte getIsfixedAmount() {
		return this.isfixedAmount;
	}

	public void setIsfixedAmount(byte isfixedAmount) {
		this.isfixedAmount = isfixedAmount;
	}

	public BigDecimal getMarkupAmount() {
		return this.markupAmount;
	}

	public void setMarkupAmount(BigDecimal markupAmount) {
		this.markupAmount = markupAmount;
	}

	 
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPositionMarkup() {
		return this.positionMarkup;
	}

	public void setPositionMarkup(int positionMarkup) {
		this.positionMarkup = positionMarkup;
	}

	public int getCreatedbyUserId() {
		return createdbyUserId;
	}

	public void setCreatedbyUserId(int createdbyUserId) {
		this.createdbyUserId = createdbyUserId;
	}

	public int getModifiedbyUserId() {
		return modifiedbyUserId;
	}

	public void setModifiedbyUserId(int modifiedbyUserId) {
		this.modifiedbyUserId = modifiedbyUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getCreatedbyCompanyId() {
		return createdbyCompanyId;
	}

	public void setCreatedbyCompanyId(int createdbyCompanyId) {
		this.createdbyCompanyId = createdbyCompanyId;
	}

	public String getConfig_number() {
		return config_number;
	}

	public void setConfig_number(String config_number) {
		this.config_number = config_number;
	}

	public String getConfigname() {
		return configname;
	}

	public void setConfigname(String configname) {
		this.configname = configname;
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

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 
}