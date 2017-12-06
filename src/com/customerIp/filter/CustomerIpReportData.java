/**
 * 
 */
package com.customerIp.filter;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @author      : Manish Samrat
 * @createdAt   : 24/07/2017
 * @version
 * @updateaBy   :  
 */
public class CustomerIpReportData implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;
	Timestamp startDate;
	Timestamp lastDate;
	private String ip;
	private boolean blockStatus;
	private boolean b2cFlag;
	private int totalSearchCount;
	private int totalBookedCount;
	private int companyId;
	private int configId;
	private String companyName;
	private String configName;
	private String configType;
	
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getLastDate() {
		return lastDate;
	}
	public void setLastDate(Timestamp lastDate) {
		this.lastDate = lastDate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public boolean isBlockStatus() {
		return blockStatus;
	}
	public void setBlockStatus(boolean blockStatus) {
		this.blockStatus = blockStatus;
	}
	public boolean isB2cFlag() {
		return b2cFlag;
	}
	public void setB2cFlag(boolean b2cFlag) {
		this.b2cFlag = b2cFlag;
	}
	public int getTotalSearchCount() {
		return totalSearchCount;
	}
	public void setTotalSearchCount(int totalSearchCount) {
		this.totalSearchCount = totalSearchCount;
	}
	public int getTotalBookedCount() {
		return totalBookedCount;
	}
	public void setTotalBookedCount(int totalBookedCount) {
		this.totalBookedCount = totalBookedCount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
}
