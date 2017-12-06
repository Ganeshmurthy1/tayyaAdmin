package com.isl.admin.commission.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity

@JsonInclude(JsonInclude.Include.NON_NULL)
@Access(AccessType.FIELD)
@Table(name = "airline_commission_sheet")
@Proxy(lazy = false)
public class AirlineCommissionSheet implements Serializable{

	@Override
	public String toString() {
		return "AirlineCommissionSheetRow [id=" + id + ", iataCode=" + iataCode + ", sheetId=" + sheetId
				+ ", isPlbFixed=" + isPlbFixed + ", plbCommission=" + plbCommission + ", plbRemark=" + plbRemark
				+ ", isIataFixed=" + isIataFixed + ", iataCommission=" + iataCommission + ", iataRemark=" + iataRemark
				+ ", severityLevel=" + severityLevel + ", apiSupplierId=" + apiSupplierId + ", lastModifiedAt="
				+ lastModifiedAt + ", dtValidFrom=" + dtValidFrom + ", dtValidTill=" + dtValidTill
				+ ", updatedByUserId=" + updatedByUserId + ", dealSheetVersion=" + dealSheetVersion + ", createdAt="
				+ createdAt + ", createdByUserId=" + createdByUserId + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id") 
	private Long id;
	@Column(name="iata_code")
	@JsonProperty("icd") 
	private String iataCode;	
	@Column(name = "sheet_id")
	@JsonProperty("si") 
	private Long sheetId;	
	@Column(name = "is_plb_fixed")
	@JsonProperty("isp") 
	private Boolean isPlbFixed;
	@Column(name = "plb_commission", columnDefinition = "decimal(8,5) default '0.0'")
	@JsonProperty("pc")
	private BigDecimal plbCommission;


	@Basic(fetch = FetchType.LAZY)
	@Column(name="plb_remark" , columnDefinition="TEXT" )
	@JsonProperty("pr")
	private String plbRemark;
	@Column(name = "is_iata_fixed")
	@JsonProperty("isi") 
	private Boolean isIataFixed;
	@Column(name = "iata_commission", columnDefinition = "decimal(8,5) default '0.0'")
	@JsonProperty("ic") 
	private BigDecimal iataCommission;


	@Basic(fetch = FetchType.LAZY)
	@Column(name="iata_remark" , columnDefinition="TEXT" )
	@JsonProperty("ir") 
	private String iataRemark;
	@Column(name = "severity_level")
	@JsonProperty("sl") 
	private Integer severityLevel;
	@Column(name = "api_supplier_id")
	@JsonProperty("asi") 
	private Long apiSupplierId;


	@Column(name = "last_modified_at")
	@JsonProperty("mdt") 
	Timestamp lastModifiedAt;


	@Column(name = "dt_valid_from")
	@JsonProperty("fdt") 
	Timestamp dtValidFrom;


	@Column(name = "dt_valid_till")
	@JsonProperty("edt") 
	Timestamp dtValidTill;
	@Column(name = "updated_by_user_id")
	@JsonProperty("ubu") 
	private Long updatedByUserId;
	@Column(name="deal_sheet_version")
	@JsonProperty("sv") 
	private String dealSheetVersion;


	@Column(name = "created_at")
	@JsonProperty("cdt") 
	Timestamp createdAt;
	@Column(name = "created_by_user_id")
	@JsonProperty("cbu") 
	private Long createdByUserId;

	@Column(name="airline")
	@JsonProperty("air") 
	private String airline;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public Long getSheetId() {
		return sheetId;
	}
	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}
	public Boolean getIsPlbFixed() {
		return isPlbFixed;
	}
	public void setIsPlbFixed(Boolean isPlbFixed) {
		this.isPlbFixed = isPlbFixed;
	}
	public BigDecimal getPlbCommission() {
		return plbCommission;
	}
	public void setPlbCommission(BigDecimal plbCommission) {
		this.plbCommission = plbCommission;
	}
	public String getPlbRemark() {
		return plbRemark;
	}
	public void setPlbRemark(String plbRemark) {
		this.plbRemark = plbRemark;
	}
	public Boolean getIsIataFixed() {
		return isIataFixed;
	}
	public void setIsIataFixed(Boolean isIataFixed) {
		this.isIataFixed = isIataFixed;
	}
	public BigDecimal getIataCommission() {
		return iataCommission;
	}
	public void setIataCommission(BigDecimal iataCommission) {
		this.iataCommission = iataCommission;
	}
	public String getIataRemark() {
		return iataRemark;
	}
	public void setIataRemark(String iataRemark) {
		this.iataRemark = iataRemark;
	}
	public Integer getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(Integer severityLevel) {
		this.severityLevel = severityLevel;
	}
	public Long getApiSupplierId() {
		return apiSupplierId;
	}
	public void setApiSupplierId(Long apiSupplierId) {
		this.apiSupplierId = apiSupplierId;
	}
	public Timestamp getLastModifiedAt() {
		return lastModifiedAt;
	}
	public void setLastModifiedAt(Timestamp lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	public Timestamp getDtValidFrom() {
		return dtValidFrom;
	}
	public void setDtValidFrom(Timestamp dtValidFrom) {
		this.dtValidFrom = dtValidFrom;
	}
	public Timestamp getDtValidTill() {
		return dtValidTill;
	}
	public void setDtValidTill(Timestamp dtValidTill) {
		this.dtValidTill = dtValidTill;
	}
	public Long getUpdatedByUserId() {
		return updatedByUserId;
	}
	public void setUpdatedByUserId(Long updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
	}
	public String getDealSheetVersion() {
		return dealSheetVersion;
	}
	public void setDealSheetVersion(String dealSheetVersion) {
		this.dealSheetVersion = dealSheetVersion;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Long getCreatedByUserId() {
		return createdByUserId;
	}
	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
}


