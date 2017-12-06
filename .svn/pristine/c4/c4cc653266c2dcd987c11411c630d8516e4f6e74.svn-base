package com.admin.insurance.model;



import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;



@Entity
@Table(name = "trawelltag_premium_chart")
public class TrawellTagPremiumChart  extends Timestampable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "trawelltag_plan_id", referencedColumnName = "id")
	private TrawellTagPlan trawellTagPlan;
	@Column(name = "agelimit")
	private Integer ageLimit;
	@Column(name = "daylimit")
	private Integer dayLimit;
	@Column(name = "premium_amount")
	private BigDecimal premiumAmount;
	
	public TrawellTagPlan getTrawellTagPlan() {
		return trawellTagPlan;
	}
	public Integer getAgeLimit() {
		return ageLimit;
	}
	public Integer getDayLimit() {
		return dayLimit;
	}
	public BigDecimal getPremiumAmount() {
		return premiumAmount;
	}
	public void setTrawellTagPlan(TrawellTagPlan trawellTagPlan) {
		this.trawellTagPlan = trawellTagPlan;
	}
	public void setAgeLimit(Integer ageLimit) {
		this.ageLimit = ageLimit;
	}
	public void setDayLimit(Integer dayLimit) {
		this.dayLimit = dayLimit;
	}
	public void setPremiumAmount(BigDecimal premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

}
