package com.admin.lookbook.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author      : Manish Samrat
 * @createdAt   : 12/07/2017
 * @version
 */
@Entity
@Table(name="bus_book")
public class BusBook implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;

	@Column(name="searchDate")
	Timestamp searchOnDateTime;
	
	@Column(name = "company_name")
	private String companyName;
	private int configId;
	private int companyId;
	private String searchKey;
	private String transactionKey;
	private String appkey;
	private String IP;
	
	public long getId() {
		return id;
	}
	public Timestamp getSearchOnDateTime() {
		return searchOnDateTime;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public String getTransactionKey() {
		return transactionKey;
	}
	public String getAppkey() {
		return appkey;
	}
	public String getIP() {
		return IP;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setSearchOnDateTime(Timestamp searchOnDateTime) {
		this.searchOnDateTime = searchOnDateTime;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public void setIP(String iP) {
		IP = iP;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
