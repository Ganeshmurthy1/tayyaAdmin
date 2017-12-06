package com.admin.api.provider.model;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="api_providers"/*,uniqueConstraints=@UniqueConstraint(columnNames={"vendorName"})*/)
public class ApiProvider implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;
	@Column(name = "api_mode")
	private String apiMode;
	@Column(name = "api_provider_id")
	private String apiProvider;
	@Column(name="VendorName")
	private String vendorName;
	@Column(name = "Description")
	private String description;
	@Column(name="Travel_Type")
	private String travelType;
	@Column(name="Support_Email")
	private String supportEmail;
	@Column(name="Support_Name")
	private String supportName;
	@Column(name="Support_Address")
	private String supportAddress;
	@Column(name="Cred_UserId")
	private String credUserid;
	@Column(name="Cred_Password")
	private String credPassword;
	@Column(name="Cred_SystemId")
	private String credSystemid;
	@Column(name="Cred_MessageId")
	private String credMessageid;
	@Column(name="Cred_PropertyId")
	private String credPropertyid;
	@Column(name="Cash_Balance")
	private String cashBalance;
	 @Column(name="Credit_Balance")
	private String creditBalance;
 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "support_id", referencedColumnName = "id") 
	private ApiProviderSupportDetails apiProviderSupportDetails;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tech_support_id", referencedColumnName = "id") 
	private ApiProviderTechSupportDetails apiProviderTechSupportDetails;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "traveltype_id", referencedColumnName = "id")
	private ApiProviderTravelType apiTravelType;	


	public ApiProviderSupportDetails getApiProviderSupportDetails() {
		return apiProviderSupportDetails;
	}

	public void setApiProviderSupportDetails(ApiProviderSupportDetails apiProviderSupportDetails) {
		this.apiProviderSupportDetails = apiProviderSupportDetails;
	}

	public ApiProviderTechSupportDetails getApiProviderTechSupportDetails() {
		return apiProviderTechSupportDetails;
	}

	public void setApiProviderTechSupportDetails(ApiProviderTechSupportDetails apiProviderTechSupportDetails) {
		this.apiProviderTechSupportDetails = apiProviderTechSupportDetails;
	}


	public ApiProviderTravelType getApiTravelType() {
		return apiTravelType;
	}

	public void setApiTravelType(ApiProviderTravelType apiTravelType) {
		this.apiTravelType = apiTravelType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApiProvider() {
		return apiProvider;
	}

	public void setApiProvider(String apiProvider) {
		this.apiProvider = apiProvider;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getSupportEmail() {
		return supportEmail;
	}

	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}

	public String getSupportName() {
		return supportName;
	}

	public void setSupportName(String supportName) {
		this.supportName = supportName;
	}

	public String getSupportAddress() {
		return supportAddress;
	}

	public void setSupportAddress(String supportAddress) {
		this.supportAddress = supportAddress;
	}

	public String getCredUserid() {
		return credUserid;
	}

	public void setCredUserid(String credUserid) {
		this.credUserid = credUserid;
	}

	public String getCredPassword() {
		return credPassword;
	}

	public void setCredPassword(String credPassword) {
		this.credPassword = credPassword;
	}

	public String getCredSystemid() {
		return credSystemid;
	}

	public void setCredSystemid(String credSystemid) {
		this.credSystemid = credSystemid;
	}

	public String getCredMessageid() {
		return credMessageid;
	}

	public void setCredMessageid(String credMessageid) {
		this.credMessageid = credMessageid;
	}

	public String getCredPropertyid() {
		return credPropertyid;
	}

	public void setCredPropertyid(String credPropertyid) {
		this.credPropertyid = credPropertyid;
	}

	public String getApiMode() {
		return apiMode;
	}

	public void setApiMode(String apiMode) {
		this.apiMode = apiMode;
	}

	public String getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(String cashBalance) {
		this.cashBalance = cashBalance;
	}

	public String getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(String creditBalance) {
		this.creditBalance = creditBalance;
	}


}