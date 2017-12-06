package com.admin.lookbook.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : Manish Samrat
 * @createdAt : 12/07/2017
 * @version
 */
@Entity
@Table(name = "ip_status")
public class LookBookCustomerIPStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;

	@Column(name="start_date")
	Timestamp startDate;
	@Column(name="last_date")
	Timestamp lastDate;
	@Column(name="ip")
	private String ip;
	@Column(name="block_status",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean blockStatus;
	@Column(name="b2c_flag",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean b2cFlag;
	@Column(name="total_search_count")
	private int totalSearchCount;
	@Column(name="total_booked_count")
	private int totalBookedCount;
	
	@Column(name="company_id")
	private int companyId;
	@Column(name="config_id")
	private int configId;
	
	@Column(name="company_name")
	private String companyName;
	@Column(name="config_name")
	private String configName;
	@Column(name="config_type")
	private String configType;
	
	public long getId() {
		return id;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public Timestamp getLastDate() {
		return lastDate;
	}
	public String getIp() {
		return ip;
	}
	public boolean isBlockStatus() {
		return blockStatus;
	}
	public boolean isB2cFlag() {
		return b2cFlag;
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
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public void setLastDate(Timestamp lastDate) {
		this.lastDate = lastDate;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setBlockStatus(boolean blockStatus) {
		this.blockStatus = blockStatus;
	}
	public void setB2cFlag(boolean b2cFlag) {
		this.b2cFlag = b2cFlag;
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
