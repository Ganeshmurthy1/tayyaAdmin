package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="mail_config")
public class MailConfig implements Serializable {

	/**@author info name:raham
	 * created date:27-07-2015
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(name="company_id")
	private int companyId;
	@Column(name="company_name")
	private String companyName;
	@Column(name="company_user_id")
	private String companyUserId;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyUserId() {
		return companyUserId;
	}
	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}
	@Column(name="server_host")
	private String serverHost;
	@Column(name="server_user")
	private String  serverUser;
	@Column(name="server_password")
	private String serverPassword;
	@Column(name="server_port")
	private String serverPort;
	@Column(name="active")
	private boolean  active;
	@Column(name="mail_config_name")
	private String mailConfigName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	public String getServerUser() {
		return serverUser;
	}
	public void setServerUser(String serverUser) {
		this.serverUser = serverUser;
	}
	public String getServerPassword() {
		return serverPassword;
	}
	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getMailConfigName() {
		return mailConfigName;
	}
	public void setMailConfigName(String mailConfigName) {
		this.mailConfigName = mailConfigName;
	}
	
	 
	 
	 
}
