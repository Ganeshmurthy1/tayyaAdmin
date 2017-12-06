package com.admin.insurance.model;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.RmConfigTripDetailsModel;

@Entity
@Table(name = "insurance_order_customer")
public class InsuranceOrderCustomerDetail extends Timestampable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private InsuranceOrderRow insuranceOrderRow;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "title")
	private String title;
	@Column(name = "age")
	private String age;
	@Column(name = "dateofbirth")
	private String dateOfBirth;
	@Column(name = "nominee")
	private String nominee;
	@Column(name = "relationshipwith_nominee")
	private String relationShipWithNominee;
	@Column(name = "passport_number")
	private String passportNumber;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "email")
	private String email;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "district")
	private String district;
	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
	@Column(name = "pinCode")
	private String pinCode;
	@Column(name = "policy_number")
	private String policyNumber;
	@Column(name = "claim_code")
	private String claimCode;
	@Column(name = "document_refer_url")
	private String documentReferUrl;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rm_config_trip_id", referencedColumnName = "id")
	private RmConfigTripDetailsModel rmConfigTripDetailsModel;
	@Column(name = "pax_id")
	 private String paxId;
	public RmConfigTripDetailsModel getRmConfigTripDetailsModel() {
		return rmConfigTripDetailsModel;
	}
	public String getPaxId() {
		return paxId;
	}
	public void setRmConfigTripDetailsModel(RmConfigTripDetailsModel rmConfigTripDetailsModel) {
		this.rmConfigTripDetailsModel = rmConfigTripDetailsModel;
	}
	public void setPaxId(String paxId) {
		this.paxId = paxId;
	}
	public InsuranceOrderRow getInsuranceOrderRow() {
		return insuranceOrderRow;
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
	public String getAge() {
		return age;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getNominee() {
		return nominee;
	}
	public String getRelationShipWithNominee() {
		return relationShipWithNominee;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public String getMobile() {
		return mobile;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getDistrict() {
		return district;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setInsuranceOrderRow(InsuranceOrderRow insuranceOrderRow) {
		this.insuranceOrderRow = insuranceOrderRow;
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
	public void setAge(String age) {
		this.age = age;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setNominee(String nominee) {
		this.nominee = nominee;
	}
	public void setRelationShipWithNominee(String relationShipWithNominee) {
		this.relationShipWithNominee = relationShipWithNominee;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public String getClaimCode() {
		return claimCode;
	}
	public String getDocumentReferUrl() {
		return documentReferUrl;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public void setClaimCode(String claimCode) {
		this.claimCode = claimCode;
	}
	public void setDocumentReferUrl(String documentReferUrl) {
		this.documentReferUrl = documentReferUrl;
	}
}

 
