package com.tayyarah.bus.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lintas.admin.bus.model.BusOrderRow;

@Entity
@Table(name = "bus_order_row_commission")
public class BusOrderRowCommission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private BusOrderRow busOrderRow;
	@Column(name="commission_amount_value")
	private BigDecimal commissionAmountValue;
	@Column(name="company_id")
	private String CompanyId;
	@Column(name="commission")
	private BigDecimal commission;
	@Column(name="commission_type")
	private String commissionType;
	@Column(name="rate_type")
	private String rateType;
	public Long getId() {
		return id;
	}
	public BusOrderRow getBusOrderRow() {
		return busOrderRow;
	}
	public BigDecimal getCommissionAmountValue() {
		return commissionAmountValue;
	}
	public String getCompanyId() {
		return CompanyId;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public String getCommissionType() {
		return commissionType;
	}
	public String getRateType() {
		return rateType;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBusOrderRow(BusOrderRow busOrderRow) {
		this.busOrderRow = busOrderRow;
	}
	public void setCommissionAmountValue(BigDecimal commissionAmountValue) {
		this.commissionAmountValue = commissionAmountValue;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
}
