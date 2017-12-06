package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="company_config_type")
public class CompanyConfigType implements Serializable{
	/**@author info raham
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="b2c", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isB2C;

	@Column(name="b2b" ,columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isB2B;

	@Column(name="api", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isAPI;
	@Column(name="whitelable", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isWhitelable;
	@Column(name="b2e", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isB2E;
	
	
	public boolean isB2E() {
		return isB2E;
	}

	public void setB2E(boolean isB2E) {
		this.isB2E = isB2E;
	}

	public boolean isWhitelable() {
		return isWhitelable;
	}

	public void setWhitelable(boolean isWhitelable) {
		this.isWhitelable = isWhitelable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isB2C() {
		return isB2C;
	}

	public void setB2C(boolean isB2C) {
		this.isB2C = isB2C;
	}

	public boolean isB2B() {
		return isB2B;
	}

	public void setB2B(boolean isB2B) {
		this.isB2B = isB2B;
	}

	public boolean isAPI() {
		return isAPI;
	}

	public void setAPI(boolean isAPI) {
		this.isAPI = isAPI;
	}
 
}
