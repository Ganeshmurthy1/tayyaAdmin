/**
 * 
 */
package com.admin.whitelabel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author      : Manish Samrat
 * @createdAt   : 28/06/2017
 * @version
 */
@Entity
@Table(name="theme_auth_request")
public class ThemeAuthRequestModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String appKey;
	private int companyId;
	private int configId;
	
	private String themeName;
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getConfigId() {
		return configId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

}
