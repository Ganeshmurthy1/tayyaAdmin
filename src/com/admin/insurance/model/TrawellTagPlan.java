package com.admin.insurance.model;



import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;



@Entity
@Table(name = "trawelltag_plan")
public class TrawellTagPlan  extends Timestampable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "code")
	private String planCode;
	@Column(name = "name")
	private String planName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "trawelltag_category_id", referencedColumnName = "id")
	private TrawellTagCategory trawellTagCategory;	
	@Column(name = "dayplan")
	private Boolean isDayPlan;
	@Column(name = "trawelltag_option")
	private Boolean isTrawellTagOption;
	@Column(name = "annual_plan")
	private Boolean isAnnualPlan;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "trawelltag_countries_id", referencedColumnName = "id")
	private TrawellTagCountries trawellTagCountries;
	
	public String getPlanCode() {
		return planCode;
	}
	public String getPlanName() {
		return planName;
	}
	public TrawellTagCategory getTrawellTagCategory() {
		return trawellTagCategory;
	}
	public Boolean getIsDayPlan() {
		return isDayPlan;
	}
	public Boolean getIsTrawellTagOption() {
		return isTrawellTagOption;
	}
	public Boolean getIsAnnualPlan() {
		return isAnnualPlan;
	}
	public TrawellTagCountries getTrawellTagCountries() {
		return trawellTagCountries;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public void setTrawellTagCategory(TrawellTagCategory trawellTagCategory) {
		this.trawellTagCategory = trawellTagCategory;
	}
	public void setIsDayPlan(Boolean isDayPlan) {
		this.isDayPlan = isDayPlan;
	}
	public void setIsTrawellTagOption(Boolean isTrawellTagOption) {
		this.isTrawellTagOption = isTrawellTagOption;
	}
	public void setIsAnnualPlan(Boolean isAnnualPlan) {
		this.isAnnualPlan = isAnnualPlan;
	}
	public void setTrawellTagCountries(TrawellTagCountries trawellTagCountries) {
		this.trawellTagCountries = trawellTagCountries;
	}
	
	
	
}
