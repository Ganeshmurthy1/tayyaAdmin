package com.admin.insurance.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;


@Entity
@Table(name = "trawelltag_category")
public class TrawellTagCategory extends Timestampable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "code")
	private String code;
	@Column(name = "description")
	private String description;
	
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
