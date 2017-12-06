package com.admin.common.expense.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.utility.DateConversion;
@Entity
@Table(name = "visa_expense")
public class VisaExpense extends Timestampable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Transient
	private String travelDateTemp;
	private String confirmationNumber;
	private String description;
	private String passengerName;
	@Temporal(TemporalType.DATE)
	private Date travelDate;
	private String remarks;
	private BigDecimal supplierPrice;
	private BigDecimal basePrice;
	private BigDecimal vfsCharges;
	private BigDecimal courierCharges;
	private BigDecimal otherTaxes;
	private BigDecimal managementFee;
	private BigDecimal convenienceFee;
	private BigDecimal serviceTax;
	private BigDecimal totalInvoiceAmount;
	private String receiptFilename;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expense_id", referencedColumnName = "id")
	private TripExepense tripExepense;

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getVfsCharges() {
		return vfsCharges;
	}

	public void setVfsCharges(BigDecimal vfsCharges) {
		this.vfsCharges = vfsCharges;
	}

	public BigDecimal getCourierCharges() {
		return courierCharges;
	}

	public void setCourierCharges(BigDecimal courierCharges) {
		this.courierCharges = courierCharges;
	}

	public BigDecimal getOtherTaxes() {
		return otherTaxes;
	}

	public void setOtherTaxes(BigDecimal otherTaxes) {
		this.otherTaxes = otherTaxes;
	}

	public BigDecimal getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee;
	}

	public BigDecimal getConvenienceFee() {
		return convenienceFee;
	}

	public void setConvenienceFee(BigDecimal convenienceFee) {
		this.convenienceFee = convenienceFee;
	}

	public BigDecimal getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}

	public BigDecimal getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}

	public void setTotalInvoiceAmount(BigDecimal totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTravelDateTemp() {
		return travelDateTemp;
	}

	public void setTravelDateTemp(String travelDateTemp) {
		this.travelDateTemp = travelDateTemp;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDateTemp) {
		this.travelDate = DateConversion.StringToDate(travelDateTemp);
	}

	public String getReceiptFilename() {
		return receiptFilename;
	}

	public void setReceiptFilename(String receiptFilename) {
		this.receiptFilename = receiptFilename;
	}

	public TripExepense getExpense() {
		return tripExepense;
	}

	public void setExpense(TripExepense tripExepense) {
		this.tripExepense = tripExepense;
	}
	
}
