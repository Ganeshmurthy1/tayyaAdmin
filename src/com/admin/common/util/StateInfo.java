package com.admin.common.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

/**
 * author:saumya
 * for storing all states and UnionTerritories  
 */
@Entity
@Table(name = "state_info")
public class StateInfo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@GeneratedValue
	private	Long id;

	@Column(name="state_name",updatable=false, insertable=false )
	private String stateName;
	@Column(name="state_code",updatable=false, insertable=false )
	private String stateCode;
	@Column(name="is_Union_Territory",updatable=false, insertable=false)
	private boolean isUnionTerritory;
	
	
	public boolean isUnionTerritory() {
		return isUnionTerritory;
	}
	public void setUnionTerritory(boolean isUnionTerritory) {
		this.isUnionTerritory = isUnionTerritory;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStateName() {
		return stateName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}
