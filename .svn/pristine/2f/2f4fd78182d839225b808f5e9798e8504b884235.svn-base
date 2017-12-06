package com.admin.insurance.model;

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

import com.lintas.admin.insurance.model.InsuranceOrderRow;


@Entity
@Table(name = "insurance_order_row_markup")
public class InsuranceOrderRowMarkup implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private InsuranceOrderRow insuranceOrderRow;
	@Column(name="company_id")
	private String CompanyId;
	@Column(name="mark_up")
	private BigDecimal markUp;
	public Long getId() {
		return id;
	}
	public InsuranceOrderRow getInsuranceOrderRow() {
		return insuranceOrderRow;
	}
	public String getCompanyId() {
		return CompanyId;
	}
	public BigDecimal getMarkUp() {
		return markUp;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setInsuranceOrderRow(InsuranceOrderRow insuranceOrderRow) {
		this.insuranceOrderRow = insuranceOrderRow;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}
	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}
}


