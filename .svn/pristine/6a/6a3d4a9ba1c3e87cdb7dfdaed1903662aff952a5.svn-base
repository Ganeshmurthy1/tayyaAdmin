package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id; 
	@Column(name="c_name")
	private String  c_name;
	@Column(name="c_code")
	private String  c_code;

	@Column(name="cur_code")
	private String cur_code;
	@Column(name="c_code_iso3")
	private String c_code_iso3;
	@Column(name="isd_code")
	private String isd_code;
	@Column(name="cur_name")
	private String cur_name;
	@Column(name="continent")
	private String continent;
	
	
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}

	public String getCur_code() {
		return cur_code;
	}
	public void setCur_code(String cur_code) {
		this.cur_code = cur_code;
	}
	public String getC_code_iso3() {
		return c_code_iso3;
	}
	public void setC_code_iso3(String c_code_iso3) {
		this.c_code_iso3 = c_code_iso3;
	}
	public String getIsd_code() {
		return isd_code;
	}
	public void setIsd_code(String isd_code) {
		this.isd_code = isd_code;
	}
	public String getCur_name() {
		return cur_name;
	}
	public void setCur_name(String cur_name) {
		this.cur_name = cur_name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
