/**
 * 
 */
package com.admin.lookbook.model;

/**
 * @author      : Manish Samrat
 * @createdAt   : 17/07/2017
 * @version
 */
public class LookBookResponse {

	private long id;
	private String appkey;
	private int totalSearchCount;
	private int totalBookedCount;
	private int companyId;
	private int configId;
	private String responseType;
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
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
