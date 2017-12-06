/**
 * 
 */
package com.admin.insurance.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author info : Manish Samrat
 * @createdAt : 07/06/2017
 * @version : 2.0
 */

@Entity
@Table(name="insurance_markup")
public class InsuranceMarkup {

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
	
	@Column(name="accumulative")
	private boolean isAccumulative;  //If this is true we shud apply all the Markups
	@Column(name="fixedAmount")
	private boolean isFixedAmount;
	@Column(name="markupAmt")
	private BigDecimal markupAmount;
	@Column(name="positionOfMarkup")
	private int positionOfMarkup;
	
	@Column(name="createdby_userId")
	private int createdbyUserId;
	@Column(name="modifiedby_userId")
	private int modifiedbyUserId;
	
	@Column(name="markup_ontotal")
	private boolean isMarkUpOnTotal;
	@Column(name="markup_perpassenger")
	private boolean isMarkUpPerPassenger;
	
	@Transient
	@Column(name="company_user_id")
	private String  companyUserId;
	@Column(name="promofare_start_date")
	private String promofareStartDate;
	@Column(name="promofare_end_date")
	private String promofareEndDate;
	@Column(name="type")
	private String Type;

	
	public String getPromofareStartDate() {
		return promofareStartDate;
	}
	public String getPromofareEndDate() {
		return promofareEndDate;
	}
	public void setPromofareStartDate(String promofareStartDate) {
		this.promofareStartDate = promofareStartDate;
	}
	public void setPromofareEndDate(String promofareEndDate) {
		this.promofareEndDate = promofareEndDate;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
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
	public int getCreatedbyUserId() {
		return createdbyUserId;
	}
	public int getModifiedbyUserId() {
		return modifiedbyUserId;
	}
	public boolean isMarkUpOnTotal() {
		return isMarkUpOnTotal;
	}
	public String getCompanyUserId() {
		return companyUserId;
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
	public void setCreatedbyUserId(int createdbyUserId) {
		this.createdbyUserId = createdbyUserId;
	}
	public void setModifiedbyUserId(int modifiedbyUserId) {
		this.modifiedbyUserId = modifiedbyUserId;
	}
	public void setMarkUpOnTotal(boolean isMarkUpOnTotal) {
		this.isMarkUpOnTotal = isMarkUpOnTotal;
	}
	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}
	public boolean isMarkUpPerPassenger() {
		return isMarkUpPerPassenger;
	}
	public void setMarkUpPerPassenger(boolean isMarkUpPerPassenger) {
		this.isMarkUpPerPassenger = isMarkUpPerPassenger;
	} 
}
