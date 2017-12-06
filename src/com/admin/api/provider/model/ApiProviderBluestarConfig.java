package com.admin.api.provider.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;
@Entity
@Table(name="api_provider_bluestar_config")
public class ApiProviderBluestarConfig extends Timestampable implements Serializable{

	/**@author info name:raham
	 * created date:27-07-2015
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="company_id")
	private int companyId;
	@Column(name="endPoint_url")
	private String  endPointUrl;
	@Column(name="interface_code")
	private String interfaceCode;
	@Column(name="api_currency")
	private String  apiCurrency;
	@Column(name="interface_auth_key")
	private String interfaceAuthKey;
	@Column(name="agent_code")
	private String agentCode;
	@Column(name="password")
	private String	password;
	private String environment; // test , production , staging
	@Column(name="provider_id")
	private int  providerId;
	@Column(name="provider_name")
	private String providerName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "apiProvider_travel_type_id", referencedColumnName = "id")
	private ApiProviderTravelType apiProviderTravelType;
	private String title;
	@Column(name = "is_active")
	private boolean active;
	@Column(name="host_url")
	private String hostUrl;
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


	public ApiProviderTravelType getApiProviderTravelType() {
		return apiProviderTravelType;
	}
	public void setApiProviderTravelType(ApiProviderTravelType apiProviderTravelType) {
		this.apiProviderTravelType = apiProviderTravelType;
	}
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}
	public String getApiCurrency() {
		return apiCurrency;
	}
	public void setApiCurrency(String apiCurrency) {
		this.apiCurrency = apiCurrency;
	}
	public String getInterfaceAuthKey() {
		return interfaceAuthKey;
	}
	public void setInterfaceAuthKey(String interfaceAuthKey) {
		this.interfaceAuthKey = interfaceAuthKey;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEndPointUrl() {
		return endPointUrl;
	}
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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