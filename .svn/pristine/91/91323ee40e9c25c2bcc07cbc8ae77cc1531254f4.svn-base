package com.lintas.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="common_config")
public class CommonConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	 private int id;
	@Column(name="created_at")
	private Date createdAt;	
	
	@Column(name="server_log_location")
	private String serverLogLocation;
	
	@Column(name="active")
	private boolean isActive;
	
	@Column(name="mode")
	private String serverMode;
	
	@Column(name="invoice_hotel_prefix")
	private String invoiceHotelPrefix;
	
	@Column(name="invoice_flight_prefix")
	private String invoiceFlightPrefix;
	
	@Column(name="admin_url")
	private String adminUrl;
	
	@Column(name="ibe_url")
	private String ibeUrl;
	
	@Column(name="api_url")
	private String apiUrl;
	
	@Column(name="image_path")
	private String imagePath;

	@Column(name="email_verify_url")
	private String emailVerifyUrl;
	
	@Column(name="max_email_attempts")
	private int maxEmailAttempts;
	
	@Column(name="max_email_queue_size")
	private int maxEmailQueueSize;
	
	@Column(name="email_service_url")
	private String emailServiceUrl;
	
	@Column(name="bcc")
	private String bccEmails;
	
	@Column(name="cc")
	private String ccEmails;
	
	@Column(name="toEmails")
	private String toEmails;
	
	@Column(name="flight_pending_payment_url")
	private String emailServiceFlightPendingPaymentUrl;
	
	@Column(name="hotel_pending_payment_url")
	private String emailServiceHotelPendingPaymentUrl;




	@Override
	public String toString() {
		return "CommonConfig [createdAt=" + createdAt + ", serverLogLocation=" + serverLogLocation + ", isAcive="
				+ isActive + ", serverMode=" + serverMode + ", invoiceHotelPrefix=" + invoiceHotelPrefix
				+ ", invoiceFlightPrefix=" + invoiceFlightPrefix + ", adminUrl=" + adminUrl + ", ibeUrl=" + ibeUrl
				+ ", apiUrl=" + apiUrl + ", imagePath=" + imagePath + ", emailVerifyUrl=" + emailVerifyUrl
				+ ", maxEmailAttempts=" + maxEmailAttempts + ", maxEmailQueueSize=" + maxEmailQueueSize
				+ ", emailServiceUrl=" + emailServiceUrl + ", bccEmails=" + bccEmails + ", ccEmails=" + ccEmails
				+ ", toEmails=" + toEmails + ", emailServiceFlightPendingPaymentUrl="
				+ emailServiceFlightPendingPaymentUrl + ", emailServiceHotelPendingPaymentUrl="
				+ emailServiceHotelPendingPaymentUrl + "]";
	} 
	public String getEmailServiceFlightPendingPaymentUrl() {
		return emailServiceFlightPendingPaymentUrl;
	}
	public void setEmailServiceFlightPendingPaymentUrl(String emailServiceFlightPendingPaymentUrl) {
		this.emailServiceFlightPendingPaymentUrl = emailServiceFlightPendingPaymentUrl;
	}
	public String getEmailServiceHotelPendingPaymentUrl() {
		return emailServiceHotelPendingPaymentUrl;
	}
	public void setEmailServiceHotelPendingPaymentUrl(String emailServiceHotelPendingPaymentUrl) {
		this.emailServiceHotelPendingPaymentUrl = emailServiceHotelPendingPaymentUrl;
	} 
	public String getServerLogLocation() {
		return serverLogLocation;
	}
	public void setServerLogLocation(String serverLogLocation) {
		this.serverLogLocation = serverLogLocation;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getServerMode() {
		return serverMode;
	}
	public void setServerMode(String serverMode) {
		this.serverMode = serverMode;
	}
	public String getInvoiceHotelPrefix() {
		return invoiceHotelPrefix;
	}
	public void setInvoiceHotelPrefix(String invoiceHotelPrefix) {
		this.invoiceHotelPrefix = invoiceHotelPrefix;
	}
	public String getInvoiceFlightPrefix() {
		return invoiceFlightPrefix;
	}
	public void setInvoiceFlightPrefix(String invoiceFlightPrefix) {
		this.invoiceFlightPrefix = invoiceFlightPrefix;
	}
	public String getAdminUrl() {
		return adminUrl;
	}
	public void setAdminUrl(String adminUrl) {
		this.adminUrl = adminUrl;
	}
	public String getIbeUrl() {
		return ibeUrl;
	}
	public void setIbeUrl(String ibeUrl) {
		this.ibeUrl = ibeUrl;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getEmailVerifyUrl() {
		return emailVerifyUrl;
	}
	public void setEmailVerifyUrl(String emailVerifyUrl) {
		this.emailVerifyUrl = emailVerifyUrl;
	}
	public int getMaxEmailAttempts() {
		return maxEmailAttempts;
	}
	public void setMaxEmailAttempts(int maxEmailAttempts) {
		this.maxEmailAttempts = maxEmailAttempts;
	}
	public int getMaxEmailQueueSize() {
		return maxEmailQueueSize;
	}
	public void setMaxEmailQueueSize(int maxEmailQueueSize) {
		this.maxEmailQueueSize = maxEmailQueueSize;
	}
	public String getEmailServiceUrl() {
		return emailServiceUrl;
	}
	public void setEmailServiceUrl(String emailServiceUrl) {
		this.emailServiceUrl = emailServiceUrl;
	}
	public String getBccEmails() {
		return bccEmails;
	}
	public void setBccEmails(String bccEmails) {
		this.bccEmails = bccEmails;
	}
	public String getCcEmails() {
		return ccEmails;
	}
	public void setCcEmails(String ccEmails) {
		this.ccEmails = ccEmails;
	}
	public String getToEmails() {
		return toEmails;
	}
	public void setToEmails(String toEmails) {
		this.toEmails = toEmails;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
