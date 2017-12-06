package com.admin.api.provider.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "api_provider_tayyarah_Config")
public class ApiProviderTayyarahConfig extends Timestampable implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "company_id")
	private int companyId;

	@Column(name = "auth_url")
	private String authUrl;

	@Column(name = "endpoint_url")
	private String endPointUrl;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;
	
	@Column(name = "property_id")
	private String propertyId;

	@Column(name = "api_key")
	private String apiKey;

	@Column(name = "provider_id")
	private int providerId;

	@Column(name = "provider_name")
	private String providerName;

	@Column(name = "api_currency")
	private String apiCurrency;

	private String title;
	@Column(name = "is_active")
	private boolean active;
	private String environment; // test , production , staging
	
	@Column(name="vendorratetype")
	private String vendorRateType;
	@Column(name="vendorpercentage")
	private BigDecimal vendorPercentage;

	public String getVendorRateType() {
		return vendorRateType;
	}
	public void setVendorRateType(String vendorRateType) {
		this.vendorRateType = vendorRateType;
	}
	public BigDecimal getVendorPercentage() {
		return vendorPercentage;
	}
	public void setVendorPercentage(BigDecimal vendorPercentage) {
		this.vendorPercentage = vendorPercentage;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getEndPointUrl() {
		return endPointUrl;
	}

	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getApiCurrency() {
		return apiCurrency;
	}

	public void setApiCurrency(String apiCurrency) {
		this.apiCurrency = apiCurrency;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
}
