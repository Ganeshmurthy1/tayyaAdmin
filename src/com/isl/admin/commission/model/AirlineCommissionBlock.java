package com.isl.admin.commission.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Access(AccessType.FIELD)
@Table(name = "airline_commission_block")
@Proxy(lazy = false)
public class AirlineCommissionBlock implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@JsonProperty("id") 
	private Long id;
	@Column(name="iata_code")
	@JsonProperty("icd") 
	private String iataCode;	
	@Column(name="airline")
	@JsonProperty("air") 
	private String airline;
	
	
	@Column(name = "plb_commission_retain", columnDefinition = "decimal(8,5) default '0.0'")
	@JsonProperty("pcr") 
	private BigDecimal plbCommissionRetain;
	@Column(name = "iata_commission_retain", columnDefinition = "decimal(8,5) default '0.0'")
	@JsonProperty("icr") 
	private BigDecimal iataCommissionRetain;
		
	@Column(name = "last_modified_at")
	@JsonProperty("mdt") 
	Timestamp lastModifiedAt;
	
	/*	@Column(name="block_id")
	@JsonProperty("bi") 
	private Long blockId;*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "block_id", referencedColumnName = "id")
	@JsonProperty("bi") 
	private AirlineCommissionCompanyBlock airlineCommissionCompanyBlock;
	
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
	
	public BigDecimal getPlbCommissionRetain() {
		return plbCommissionRetain;
	}
	public void setPlbCommissionRetain(BigDecimal plbCommissionRetain) {
		this.plbCommissionRetain = plbCommissionRetain;
	}
	public BigDecimal getIataCommissionRetain() {
		return iataCommissionRetain;
	}
	public void setIataCommissionRetain(BigDecimal iataCommissionRetain) {
		this.iataCommissionRetain = iataCommissionRetain;
	}
	
	public Timestamp getLastModifiedAt() {
		return lastModifiedAt;
	}
	public void setLastModifiedAt(Timestamp lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	
	
	public AirlineCommissionCompanyBlock getAirlineCommissionCompanyBlock() {
		return airlineCommissionCompanyBlock;
	}
	public void setAirlineCommissionCompanyBlock(AirlineCommissionCompanyBlock airlineCommissionCompanyBlock) {
		this.airlineCommissionCompanyBlock = airlineCommissionCompanyBlock;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
}
