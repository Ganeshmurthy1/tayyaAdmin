package com.lintas.admin.model;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lintas.admin.hotel.model.HotelOrderRow;

@Entity(name="hotel_order_row_commission")
public class HotelOrderRowCommission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	Long id;
	@Column(name="rate_type")
	private String rateType;
	@Column(name="company_id")
	private String CompanyId;
	@Column(name="commission" , columnDefinition="decimal(20,10) default '0.00'")
	private BigDecimal  commission;
	@Column(name="commission_type")
	private String commissionType;
	@Column(name = "commission_amount_value", columnDefinition="decimal(20,10) default '0.00'")
	private BigDecimal commissionAmountValue;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private HotelOrderRow hotelOrderRow;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}


	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public String getCommissionType() {
		return commissionType;
	}
	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}
	public HotelOrderRow getHotelOrderRow() {
		return hotelOrderRow;
	}
	public void setHotelOrderRow(HotelOrderRow hotelOrderRow) {
		this.hotelOrderRow = hotelOrderRow;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public BigDecimal getCommissionAmountValue() {
		return commissionAmountValue;
	}
	public void setCommissionAmountValue(BigDecimal commissionAmountValue) {
		this.commissionAmountValue = commissionAmountValue;
	}

}
