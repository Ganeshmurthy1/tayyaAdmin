package com.admin.api.provider.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;

/**
 * @author info name:Manish Kumar
 * @createdAt date:19-05-2017
 */
@Entity
@Table(name="api_provider_etravelsmart_config")
public class ApiProviderEtravelSmartConfig extends Timestampable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="companyId")
	private int companyId;
	@Column(name="title")
	private String  title;
	@Column(name="activeStatus")
	private Boolean  active;
	@Column(name="busUserName")
	private String busUserName;
	@Column(name="busPassword")
	private String  busPassword;
	@Column(name="url")
	private String  url;
	@Column(name="environment")
	private String  environment;
	@Column(name="currency")
	private String  apiCurrency;
	
	
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

	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getBusUserName() {
		return busUserName;
	}
	public void setBusUserName(String busUserName) {
		this.busUserName = busUserName;
	}
	public String getBusPassword() {
		return busPassword;
	}
	public void setBusPassword(String busPassword) {
		this.busPassword = busPassword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}