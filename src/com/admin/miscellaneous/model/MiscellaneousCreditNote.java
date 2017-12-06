package com.admin.miscellaneous.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author :   Manish Samrat
 * @Date   :   20/06/2017
 */
@Entity
@Table(name = "miscellaneous_credit_note")
public class MiscellaneousCreditNote implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Transient
	@Column(name="convertDate")
	private String convertDate;
	@Transient
	private String  employeeComments;
	@Transient
	private String  statusAction;
	@Transient
	private String  paymentStatus;
	@Transient
	private String  updatedBy;
	@Transient
	private Long miscellaneousOrderRowId;
	@Transient
	private int cancelType;
	@Transient
	private String cancelMode;
	
	@Column(name = "total_booking_amount", columnDefinition="decimal(20,10) default '0.00'")
	private BigDecimal totalBookingAmount;
	@Column(name = "refunded_amount", columnDefinition="decimal(20,10) default '0.00'")
	private BigDecimal refundedAmount;
	@Column(name = "convenience_fees", columnDefinition="decimal(20,10) default '0.00'")
	private BigDecimal convenienceFees;
	@Column(name = "cancellation_fees", columnDefinition="decimal(20,10) default '0.00'")
	private BigDecimal cancellationFees;
	@Column(name = "management_fees", columnDefinition="decimal(20,10) default '0.00'")
	private BigDecimal managementFees;
	@Column(name = "gst_amount", columnDefinition="decimal(20,10) default '0.00'")
	private BigDecimal gstAmount;
	@Column(name = "company_id")
	private String companyId="0";
	@Column(name = "user_id")
	private String userId="0";
	@Column(name="ordered_At")
	private Timestamp orderedAt;
	@Column(name="row_id")
	private int rowId;
	@Column(name="before_status")
	private String beforeStatus;
	@Column(name="after_status")
	private String afterStatus;
	private String alterBy;
	@Column(name="action_type")
	private String actionType;
	@Column(name="before_pay_status")
	private String befPayStatus;
	@Column(name="after_pay_status")
	private String afterPayStatus;
	@Column(name="cn_invoice_no")
	private String CNINumber;
	@Column(name="issued_at")
	private Timestamp issuedAt;
	@Column(name="is_order_update",columnDefinition="BIT(1) default 0")
	private boolean isOrderUpdated;
	@Column(name="is_creditnote_issue",columnDefinition="BIT(1) default 0")
	private boolean isCreditnoteIssued;
	
	public int getId() {
		return id;
	}
	public String getConvertDate() {
		return convertDate;
	}
	public String getEmployeeComments() {
		return employeeComments;
	}
	public String getStatusAction() {
		return statusAction;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public Long getMiscellaneousOrderRowId() {
		return miscellaneousOrderRowId;
	}
	public int getCancelType() {
		return cancelType;
	}
	public String getCancelMode() {
		return cancelMode;
	}
	public BigDecimal getTotalBookingAmount() {
		return totalBookingAmount;
	}
	public BigDecimal getRefundedAmount() {
		return refundedAmount;
	}
	public BigDecimal getCancellationFees() {
		return cancellationFees;
	}
	public BigDecimal getGstAmount() {
		return gstAmount;
	}
	public String getCompanyId() {
		return companyId;
	}
	public String getUserId() {
		return userId;
	}
	public Timestamp getOrderedAt() {
		return orderedAt;
	}
	public int getRowId() {
		return rowId;
	}
	public String getBeforeStatus() {
		return beforeStatus;
	}
	public String getAfterStatus() {
		return afterStatus;
	}
	public String getAlterBy() {
		return alterBy;
	}
	public String getActionType() {
		return actionType;
	}
	public String getBefPayStatus() {
		return befPayStatus;
	}
	public String getAfterPayStatus() {
		return afterPayStatus;
	}
	public String getCNINumber() {
		return CNINumber;
	}
	public Timestamp getIssuedAt() {
		return issuedAt;
	}
	public boolean isOrderUpdated() {
		return isOrderUpdated;
	}
	public boolean isCreditnoteIssued() {
		return isCreditnoteIssued;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setConvertDate(String convertDate) {
		this.convertDate = convertDate;
	}
	public void setEmployeeComments(String employeeComments) {
		this.employeeComments = employeeComments;
	}
	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public void setMiscellaneousOrderRowId(Long miscellaneousOrderRowId) {
		this.miscellaneousOrderRowId = miscellaneousOrderRowId;
	}
	public void setCancelType(int cancelType) {
		this.cancelType = cancelType;
	}
	public void setCancelMode(String cancelMode) {
		this.cancelMode = cancelMode;
	}
	public void setTotalBookingAmount(BigDecimal totalBookingAmount) {
		this.totalBookingAmount = totalBookingAmount;
	}
	public void setRefundedAmount(BigDecimal refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	public void setCancellationFees(BigDecimal cancellationFees) {
		this.cancellationFees = cancellationFees;
	}
	public void setGstAmount(BigDecimal gstAmount) {
		this.gstAmount = gstAmount;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setOrderedAt(Timestamp orderedAt) {
		this.orderedAt = orderedAt;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public void setBeforeStatus(String beforeStatus) {
		this.beforeStatus = beforeStatus;
	}
	public void setAfterStatus(String afterStatus) {
		this.afterStatus = afterStatus;
	}
	public void setAlterBy(String alterBy) {
		this.alterBy = alterBy;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public void setBefPayStatus(String befPayStatus) {
		this.befPayStatus = befPayStatus;
	}
	public void setAfterPayStatus(String afterPayStatus) {
		this.afterPayStatus = afterPayStatus;
	}
	public void setCNINumber(String cNINumber) {
		CNINumber = cNINumber;
	}
	public void setIssuedAt(Timestamp issuedAt) {
		this.issuedAt = issuedAt;
	}
	public void setOrderUpdated(boolean isOrderUpdated) {
		this.isOrderUpdated = isOrderUpdated;
	}
	public void setCreditnoteIssued(boolean isCreditnoteIssued) {
		this.isCreditnoteIssued = isCreditnoteIssued;
	}
	public BigDecimal getConvenienceFees() {
		return convenienceFees;
	}
	public BigDecimal getManagementFees() {
		return managementFees;
	}
	public void setConvenienceFees(BigDecimal convenienceFees) {
		this.convenienceFees = convenienceFees;
	}
	public void setManagementFees(BigDecimal managementFees) {
		this.managementFees = managementFees;
	}
	
}
