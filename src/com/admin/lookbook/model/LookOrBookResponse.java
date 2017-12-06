/**
 * 
 */
package com.admin.lookbook.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author      : Manish Samrat
 * @createdAt   : 18/07/2017
 * @version
 */
public class LookOrBookResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	Timestamp searchOnDateTime;
	private String searchQueryString;
	private String searchKey;
	private String transactionKey;
	private String appkey;
	private String IP;
	private int companyId;
	private int configId;
	private String companyName;
	
	public int getConfigId() {
		return configId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	
	public long getId() {
		return id;
	}
	public Timestamp getSearchOnDateTime() {
		return searchOnDateTime;
	}
	public String getSearchQueryString() {
		return searchQueryString;
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
	public void setSearchQueryString(String searchQueryString) {
		this.searchQueryString = searchQueryString;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
