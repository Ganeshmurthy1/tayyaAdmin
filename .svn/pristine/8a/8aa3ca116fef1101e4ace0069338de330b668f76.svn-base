package com.tayyarah.visa.model;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.visa.model.VisaOrderRow;

@Entity
@Table(name = "visa_order_customer")
public class VisaOrderCustomer extends Timestampable implements Serializable {

	/**
	 * @author Raham
	 * 12-Oct-2017
	 */
	private static final long serialVersionUID = 1L;
	
	@Transient
	private BigDecimal supplierAmount;
	@Transient
	private BigDecimal baseAmount;
	@Transient
	private BigDecimal tax;
	@Transient
	private BigDecimal markUp;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private VisaOrderRow visaOrderRow;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "title")
	private String title;
	@Column(name = "gender")
	private String gender;
	@Column(name = "age")
	private String age;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "email")
	private String email;
	 
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rm_config_trip_id", referencedColumnName = "id")
	private RmConfigTripDetailsModel rmConfigTripDetailsModel;
	@Column(name = "pax_id")
	 private String paxId;
	public String getPaxId() {
		return paxId;
	}

	public void setPaxId(String paxId) {
		this.paxId = paxId;
	}
	public RmConfigTripDetailsModel getRmConfigTripDetailsModel() {
		return rmConfigTripDetailsModel;
	}
	public void setRmConfigTripDetailsModel(RmConfigTripDetailsModel rmConfigTripDetailsModel) {
		this.rmConfigTripDetailsModel = rmConfigTripDetailsModel;
	}
	 
	public VisaOrderRow getVisaOrderRow() {
		return visaOrderRow;
	}
	 
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getTitle() {
		return title;
	}
	public String getGender() {
		return gender;
	}
	public String getAge() {
		return age;
	}
	public String getMobile() {
		return mobile;
	}
	public String getEmail() {
		return email;
	}
	 
	public void setVisaOrderRow(VisaOrderRow visaOrderRow) {
		this.visaOrderRow = visaOrderRow;
	}
	 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 
	public BigDecimal getSupplierAmount() {
		return supplierAmount;
	}
	public BigDecimal getBaseAmount() {
		return baseAmount;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public BigDecimal getMarkUp() {
		return markUp;
	}
	public void setSupplierAmount(BigDecimal supplierPrice) {
		this.supplierAmount = supplierPrice;
	}
	public void setBaseAmount(BigDecimal basePrice) {
		this.baseAmount = basePrice;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}
}