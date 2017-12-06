package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="walletamount_tranfer_history")
public class WalletAmountTranferHistory implements Serializable{

	/**@author info raham
	 * created at:03-10-2015
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Transient
	private BigDecimal gstOrServiceTax ;
	@Transient
	private BigDecimal BRVorCRVSpentAmount;
	@Transient
	private BigDecimal  restAmount;
	@Transient
	private BigDecimal BRVorCRVAmount;
	@Transient
	private BigDecimal outStandingBalance;
	@Transient
	private String voucherType;
	@Transient
	@Column(name="convert_date")
	private String convertDate;
	@Transient
	@Column(name="agency_name")
	private String agencyName;
	@Transient
	@Column(name="company_userid")
	private String company_userid;
	@Transient
	@Column(name="parentcompany_userid")
	private String parentcompany_userid;
	@Column(name = "wallet_id")
	private int walletId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "action_id")
	private String actionId;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "currency",columnDefinition = "varchar(4)")
	private String currency; 
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "amount",columnDefinition="decimal(20,10) default '0.0'",nullable=false)
	private BigDecimal amount;
	@Column(name = "opening_balance",columnDefinition="decimal(20,10) default '0.0'",nullable=false)
	private BigDecimal openingBalance;
	@Column(name = "closing_balance",columnDefinition="decimal(20,10) default '0.0'",nullable=false)
	private BigDecimal closingBalance;
	@Column(name = "parent_opening_balance",columnDefinition="decimal(20,10) default '0.0'",nullable=false)
	private BigDecimal parentOpeningBalance;
	@Column(name = "parent_closing_balance",columnDefinition="decimal(20,10) default '0.0'",nullable=false)
	private BigDecimal parentClosingBalance;
	@Column(name = "action",columnDefinition = "varchar(50)")
	private String action; 
	@Column(name = "parent_userid")
	private int parentUserId;
	@Column(name = "transaction_type")
	private String transactionType;
	@Column(name = "invoice_no",columnDefinition="varchar(255) default '0'")
	private String invoiceNo="0";
	@Column(name = "company_id",columnDefinition="int default 0")
	private int companyId;
	@Column(name = "is_failed",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean failed;
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}
	public BigDecimal getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(BigDecimal closingBalance) {
		this.closingBalance = closingBalance;
	}
	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the actionId
	 */
	public String getActionId() {
		return actionId;
	}
	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	public int getWalletId() {
		return walletId;
	}
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public BigDecimal getParentClosingBalance() {
		return parentClosingBalance;
	}
	public void setParentClosingBalance(BigDecimal parentClosingBalance) {
		this.parentClosingBalance = parentClosingBalance;
	}
	public BigDecimal getParentOpeningBalance() {
		return parentOpeningBalance;
	}
	public void setParentOpeningBalance(BigDecimal parentOpeningBalance) {
		this.parentOpeningBalance = parentOpeningBalance;
	}
	public int getParentUserId() {
		return parentUserId;
	}
	public void setParentUserId(int parentUserId) {
		this.parentUserId = parentUserId;
	}
	public String getConvertDate() {
		return convertDate;
	}
	public void setConvertDate(String convertDate) {
		this.convertDate = convertDate;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getCompany_userid() {
		return company_userid;
	}
	public void setCompany_userid(String company_userid) {
		this.company_userid = company_userid;
	}
	public String getParentcompany_userid() {
		return parentcompany_userid;
	}
	public void setParentcompany_userid(String parentcompany_userid) {
		this.parentcompany_userid = parentcompany_userid;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getOutStandingBalance() { 
		return outStandingBalance;
	}
	public void setOutStandingBalance(BigDecimal outStandingBalance) {
		this.outStandingBalance = outStandingBalance;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	} 
	public BigDecimal getGstOrServiceTax() {
		return gstOrServiceTax;
	}
	public void setGstOrServiceTax(BigDecimal gstOrServiceTax) {
		this.gstOrServiceTax = gstOrServiceTax;
	}
	public BigDecimal getBRVorCRVAmount() {
		return BRVorCRVAmount;
	}
	public void setBRVorCRVAmount(BigDecimal bRVorCRVAmount) {
		BRVorCRVAmount = bRVorCRVAmount;
	}
	public BigDecimal getBRVorCRVSpentAmount() {
		return BRVorCRVSpentAmount;
	}
	public void setBRVorCRVSpentAmount(BigDecimal bRVorCRVSpentAmount) {
		BRVorCRVSpentAmount = bRVorCRVSpentAmount;
	}
	public BigDecimal getRestAmount() {
		return restAmount;
	}
	public void setRestAmount(BigDecimal restAmount) {
		this.restAmount = restAmount;
	}
	public boolean isFailed() {
		return failed;
	}
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
}
