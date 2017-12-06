package com.lintas.admin.advertisement.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "advertisement_gst_tax_config")
public class AdvertisementOrderRowGstTax implements Serializable{

	/**
	 * @author Basha
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	private Long id;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name="applicable_fare")
	private String applicableFare;
	@Column(name="cgst", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal CGST;
	@Column(name="sgst", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal SGST;
	@Column(name="igst", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal IGST;
	@Column(name="ugst", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal UGST;
	@Column(name="management_fee", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal managementFee;
	@Column(name="convenience_fee", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal convenienceFee;
	@Column(name="total_tax", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal totalGst;
	public String getApplicableFare() {
		return applicableFare;
	}
	public void setApplicableFare(String applicableFare) {
		this.applicableFare = applicableFare;
	}
	public BigDecimal getCGST() {
		return CGST;
	}
	public void setCGST(BigDecimal cGST) {
		CGST = cGST;
	}
	public BigDecimal getSGST() {
		return SGST;
	}
	public void setSGST(BigDecimal sGST) {
		SGST = sGST;
	}
	public BigDecimal getIGST() {
		return IGST;
	}
	public void setIGST(BigDecimal iGST) {
		IGST = iGST;
	}
	public BigDecimal getUGST() {
		return UGST;
	}
	public void setUGST(BigDecimal uGST) {
		UGST = uGST;
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
	public BigDecimal getTotalGst() {
		return totalGst;
	}
	public void setTotalGst(BigDecimal totalGst) {
		this.totalGst = totalGst;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
