package com.tayyarah.browsingHistory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.User;


@Entity
@Table(name = "browsingHistory")
public class BrowsingHistory extends Timestampable implements Serializable {
	/**
	 * @author HARSHA
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "password")
	private String password;

	@Column(name = "company_id")
	private Integer companyId;

	@Column(name = "country")
	public String country;

	@Column(name = "ip_address")
	public String ip_address;
	@Column(name = "postal")
	public String postal;

	@Column(name = "Langitude")
	private String longitude;

	@Column(name = "Latitude")
	private String latitude;
	@Column(name = "cityName")
	private String cityName;

	@Column(name = "pageid")
	private Integer pageid;

	@Column(name = "actionid")
	private Integer actionid;

	@Column(name = "statusid")
	private Integer statusid;


	@Transient	
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient	
	private String pageName;

	@Transient	
	private String actionName;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "history",targetEntity = BrowsingHistoryDetail.class)
	@LazyCollection(LazyCollectionOption.TRUE)		
	private List<BrowsingHistoryDetail> details;



	public List<BrowsingHistoryDetail> getDetails() {
		return details;
	}

	public void setDetails(List<BrowsingHistoryDetail> details) {
		this.details = details;
	}


	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public void initPageName() {		
		BrowsingOptionPageEnum browsingOptionPageEnum = BrowsingOptionPageEnum.getPageEnum(pageid);
		this.pageName =  browsingOptionPageEnum.getTitle();
	}

	public String getPageName() {
		if(this.pageName != null)
		{
			BrowsingOptionPageEnum browsingOptionPageEnum = BrowsingOptionPageEnum.getPageEnum(pageid);
			this.pageName =  browsingOptionPageEnum.getTitle();
		}
		return this.pageName;

	}
	public void initActionName() {
		// TODO Auto-generated method stub
		BrowsingOptionActionEnum browsingOptionActionEnum = BrowsingOptionActionEnum.getActionEnum(actionid);
		this.actionName =  browsingOptionActionEnum.getTitle();
	}

	public String getActionName() {
		if(this.actionName != null)
		{
			BrowsingOptionActionEnum browsingOptionActionEnum = BrowsingOptionActionEnum.getActionEnum(actionid);
			this.actionName =  browsingOptionActionEnum.getTitle();
		}

		return this.actionName;
	}
	public Integer getPageid() {
		return pageid;
	}

	public void setPageid(Integer pageid) {
		this.pageid = pageid;
	}

	public Integer getActionid() {
		return actionid;
	}

	public void setActionid(Integer actionid) {
		this.actionid = actionid;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}



	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}



}
