package com.tayyarah.bus.model;
/*Created By Vimal Susai Raj S 
 * Date : 25-5-2017*/
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="bus_markup")
public class BusMarkup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int markupId;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="modified_date")
	private Date modifiedDate;	
	@Column(name="markup_name")
	private String markupName;
	@Column(name="companyId")
	private int companyId;
	@Column(name="configId")
	private int configId;
	@Column(name="configname")
	private String configname;
	@Column(name="config_number")
	private String config_number;
	@Column(name="company_name")
	private String companyName;
	@Column(name="created_by_company_name")
	private String createdByCompanyName;
	@Column(name="createdby_company_id")
	private int createdbyCompanyId;
	@Column(name="cacheflag")
	private String cacheflag;
	@Column(name="origin")
	private String origin;
	@Column(name="destination")
	private String destination;
	@Column(name="arrDate")
	private String arrDate;
	@Column(name="depDate")
	private String depDate;
	@Column(name="accumulative")
	private boolean isAccumulative;  //If this is true we shud apply all the Markups
	@Column(name="fixedAmount")
	private boolean isFixedAmount;
	@Column(name="markupAmt")
	private BigDecimal markupAmount;
	@Column(name="positionOfMarkup")
	private int positionOfMarkup;
	@Column(name="busOperators")
	private String busOperators;
	@Column(name="busTypes")
	private String busTypes;		
	@Column(name="createdby_userId")
	private int createdbyUserId;
	@Column(name="modifiedby_userId")
	private int modifiedbyUserId;
	@Column(name="promofare_start_date")
	 private String promofareStartDate;
	@Column(name="promofare_end_date")
	 private String promofareEndDate; 
	@Column(name="markup_perpassenger")
	 private boolean isMarkUpPerPassenger;
	 @Column(name="markup_ontotal")
	 private boolean isMarkUpOnTotal; 
	@Transient
	@Column(name="company_user_id")
	private String  companyUserId; 
	
	public int getMarkupId() {
		return markupId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public String getMarkupName() {
		return markupName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public int getConfigId() {
		return configId;
	}
	public String getConfigname() {
		return configname;
	}
	public String getConfig_number() {
		return config_number;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getCreatedByCompanyName() {
		return createdByCompanyName;
	}
	public int getCreatedbyCompanyId() {
		return createdbyCompanyId;
	}
	public String getCacheflag() {
		return cacheflag;
	}
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	public String getArrDate() {
		return arrDate;
	}
	public String getDepDate() {
		return depDate;
	}
	public boolean isAccumulative() {
		return isAccumulative;
	}
	public boolean isFixedAmount() {
		return isFixedAmount;
	}
	public BigDecimal getMarkupAmount() {
		return markupAmount;
	}
	public int getPositionOfMarkup() {
		return positionOfMarkup;
	}
	public String getBusOperators() {
		return busOperators;
	}
	public String getBusTypes() {
		return busTypes;
	}
	public int getCreatedbyUserId() {
		return createdbyUserId;
	}
	public int getModifiedbyUserId() {
		return modifiedbyUserId;
	}
	public String getPromofareStartDate() {
		return promofareStartDate;
	}
	public String getPromofareEndDate() {
		return promofareEndDate;
	}
	public void setMarkupId(int markupId) {
		this.markupId = markupId;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public void setMarkupName(String markupName) {
		this.markupName = markupName;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	public void setConfigname(String configname) {
		this.configname = configname;
	}
	public void setConfig_number(String config_number) {
		this.config_number = config_number;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCreatedByCompanyName(String createdByCompanyName) {
		this.createdByCompanyName = createdByCompanyName;
	}
	public void setCreatedbyCompanyId(int createdbyCompanyId) {
		this.createdbyCompanyId = createdbyCompanyId;
	}
	public void setCacheflag(String cacheflag) {
		this.cacheflag = cacheflag;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public void setAccumulative(boolean isAccumulative) {
		this.isAccumulative = isAccumulative;
	}
	public void setFixedAmount(boolean isFixedAmount) {
		this.isFixedAmount = isFixedAmount;
	}
	public void setMarkupAmount(BigDecimal markupAmount) {
		this.markupAmount = markupAmount;
	}
	public void setPositionOfMarkup(int positionOfMarkup) {
		this.positionOfMarkup = positionOfMarkup;
	}
	public void setBusOperators(String busOperators) {
		this.busOperators = busOperators;
	}
	public void setBusTypes(String busTypes) {
		this.busTypes = busTypes;
	}
	public void setCreatedbyUserId(int createdbyUserId) {
		this.createdbyUserId = createdbyUserId;
	}
	public void setModifiedbyUserId(int modifiedbyUserId) {
		this.modifiedbyUserId = modifiedbyUserId;
	}
	public void setPromofareStartDate(String promofareStartDate) {
		this.promofareStartDate = promofareStartDate;
	}
	public void setPromofareEndDate(String promofareEndDate) {
		this.promofareEndDate = promofareEndDate;
	}
	
 
		 public boolean isMarkUpPerPassenger() {
		return isMarkUpPerPassenger;
	}
	public boolean isMarkUpOnTotal() {
		return isMarkUpOnTotal;
	}
		public void setMarkUpPerPassenger(boolean isMarkUpPerPassenger) {
		  this.isMarkUpPerPassenger = isMarkUpPerPassenger;
		 }
		 public void setMarkUpOnTotal(boolean isMarkUpOnTotal) {
		  this.isMarkUpOnTotal = isMarkUpOnTotal;
		 }
		 public String getCompanyUserId() {
				return companyUserId;
			}
			public void setCompanyUserId(String companyUserId) {
				this.companyUserId = companyUserId;
			}
}
