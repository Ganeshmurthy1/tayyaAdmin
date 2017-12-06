package com.admin.api.provider.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;
@Entity
@Table(name="api_provider_desiya_config")
public class ApiProviderDesiyaConfig extends Timestampable implements Serializable{

	/**@author info name:raham
	 * created date:27-07-2015
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="company_id")
	private int companyId;
	@Column(name="endPoint_url")
	private String  endPointUrl;
	@Column(name="user_name")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="property_id")
	private String propertyId;
	private String environment; // test , production , staging
	@Column(name="api_currency")
	private String  apiCurrency;
	@Column(name="provider_id")
	private int  providerId;
	@Column(name="provider_name")
	private String providerName;
	private String title;
	@Column(name = "is_active")
	private boolean active;
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

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	/**
	 * @return the company_id
	 */

	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}


	public String getApiCurrency() {
		return apiCurrency;
	}
	public void setApiCurrency(String apiCurrency) {
		this.apiCurrency = apiCurrency;
	}
	public String getEndPointUrl() {
		return endPointUrl;
	}
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	@Transient
	private String companyName;
	public String getCompanyName() {
		if(companyId>0){
			Company comapany=new CompanyDAO().getNewCompanyId(companyId);
			if(comapany!=null)
			companyName=comapany.getCompanyname();
		 }
		else{
			companyName="-";
		}
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

} 