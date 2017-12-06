/**
 * 
 */
package com.admin.lookbook.model;

/**
 * @author      : Manish Samrat
 * @createdAt   : 17/07/2017
 * @version
 */
public class LookBookRequest {

	private long id;
	private String appKey;
	private String searchKey;
	private String transactionId;
	
	public long getId() {
		return id;
	}
	public String getAppKey() {
		return appKey;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
