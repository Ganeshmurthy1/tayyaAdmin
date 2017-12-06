package com.admin.payment.recievable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;
import com.lintas.utility.DateConversion;

@Entity
@Table(name="payment_recievable")
public class PaymentRecievable extends Timestampable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private String transReceivedDate;
	@Transient
	private String transFilePath;
	@Transient	
	private String companyName;
	@Transient	
	private String  transFromDt;
	@Transient	
	private String  transToDt;
	
	@Column(name="user_id")
	private int userId;
	@Column(name="company_id")
	private int companyId;
	@Column(name="amount")
	private BigDecimal amount;
	@Column(name="refernce_number")
	private  String referenceNumber;
	@Column(name="payment_mode")
	private  String paymentMode;
	@Column(name="description",columnDefinition="LONGTEXT")
	private  String description;
	@Column(name="received_date")
	private  Date receivedDate;
	@Column(name="received_by")
	private  String receivedBy;
	@Column(name="from_date")
	private  Date fromDate;
	@Column(name="to_date")
	private Date toDate;
	@Column(name="file_path")
	private String  filePath;
	@Column(name="is_knock_off")
	private boolean  knockOff;
	@Column(name="payment_type")
	private String  paymentType;
	 
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public boolean isKnockOff() {
		return knockOff;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setKnockOff(boolean knockOff) {
		this.knockOff = knockOff;
	}
	public String getTransReceivedDate() {
		if(getReceivedDate()!=null)
			transReceivedDate=DateConversion.convertDateToStringToDate(getReceivedDate());
		return transReceivedDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setTransReceivedDate(String transReceivedDate) {
		this.transReceivedDate = transReceivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUserId() {
		return userId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	 
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Override
	public String toString() {
		return "PatmentRecievable [userId=" + userId + ", companyId=" + companyId + ", amount=" + amount
				+ ", refernceNumber=" + referenceNumber + ", transactionType=" + paymentMode + "]";
	}

	public String getTransFromDt() {
		return transFromDt;
	}
	public String getTransToDt() {
		return transToDt;
	}
	public void setTransFromDt(String transFromDt) {
		this.transFromDt = transFromDt;
	}
	public void setTransToDt(String transToDt) {
		this.transToDt = transToDt;
	}
	public String getCompanyName() {
		Company company=new CompanyDAO().getCompanyProfile(companyId);
		if(company!=null &&  company.getCompanyname()!=null)
			companyName= company.getCompanyname();
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTransFilePath() {
		return transFilePath;
	}
	public void setTransFilePath(String transFilePath) {
		this.transFilePath = transFilePath;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
 
}
