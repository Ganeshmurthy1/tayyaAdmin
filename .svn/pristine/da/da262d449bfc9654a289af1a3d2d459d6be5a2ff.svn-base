/**
 * 
 */
package com.admin.lookbook.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : Manish Samrat
 * @createdAt : 17/07/2017
 * @version
 */
@Entity
@Table(name = "hotel_look_book")
public class HotelLookBook implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;

	private String appkey;
	private int companyId;
	@Column(name = "company_name")
	private String companyName;
	private int totalSearchCount;
	private int totalBookedCount;
	private int configId;

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public long getId() {
		return id;
	}

	public String getAppkey() {
		return appkey;
	}

	public int getTotalSearchCount() {
		return totalSearchCount;
	}

	public int getTotalBookedCount() {
		return totalBookedCount;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public void setTotalSearchCount(int totalSearchCount) {
		this.totalSearchCount = totalSearchCount;
	}

	public void setTotalBookedCount(int totalBookedCount) {
		this.totalBookedCount = totalBookedCount;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
