package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user_role")
public class UserRole implements Serializable {
	/**@author info raham
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int roleid;
	@Column(name="admin", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isAdmin;
	@Column(name="orders" ,columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isOrder;
	@Column(name="reports", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isReports;
	@Column(name="superuser" ,columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isSuperuser;

	@Column(name="usermode", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isUsermode;
	
	@Column(name="cms", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isCms;
	
	@Column(name="tech_support", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isTechSupport;
	
	@Column(name="tech_head", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isTechHead;
	
	@Column(name="travel_desk", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isTravelDesk;
	
	@Column(name="demo_user", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isDemoUser;
	
	
	public boolean isTechSupport() {
		return isTechSupport;
	}
	public void setTechSupport(boolean isTechSupport) {
		this.isTechSupport = isTechSupport;
	}

	public boolean isTechHead() {
		return isTechHead;
	}

	public void setTechHead(boolean isTechHead) {
		this.isTechHead = isTechHead;
	}

	@Column(name="corporate", columnDefinition = "BOOLEAN DEFAULT false")
	 private boolean isCorporate;
	 
	 @Column(name="corporateemployee", columnDefinition = "BOOLEAN DEFAULT false")
	 private boolean isCorporateemployee;
	
	 
	public boolean isCorporate() {
		return isCorporate;
	}

	public void setCorporate(boolean isCorporate) {
		this.isCorporate = isCorporate;
	}

	public boolean isCorporateemployee() {
		return isCorporateemployee;
	}

	public void setCorporateemployee(boolean isCorporateemployee) {
		this.isCorporateemployee = isCorporateemployee;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isOrder() {
		return isOrder;
	}

	public void setOrder(boolean isOrder) {
		this.isOrder = isOrder;
	}

	public boolean isReports() {
		return isReports;
	}

	public void setReports(boolean isReports) {
		this.isReports = isReports;
	}

	public boolean isSuperuser() {
		return isSuperuser;
	}

	public void setSuperuser(boolean isSuperuser) {
		this.isSuperuser = isSuperuser;
	}

	public boolean isUsermode() {
		return isUsermode;
	}

	public void setUsermode(boolean isUsermode) {
		this.isUsermode = isUsermode;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public boolean isCms() {
		return isCms;
	}

	public void setCms(boolean isCms) {
		this.isCms = isCms;
	}
	public boolean isTravelDesk() {
		return isTravelDesk;
	}
	public void setTravelDesk(boolean isTravelDesk) {
		this.isTravelDesk = isTravelDesk;
	}
	public boolean isDemoUser() {
		return isDemoUser;
	}
	public void setDemoUser(boolean isDemoUser) {
		this.isDemoUser = isDemoUser;
	}
 
}
