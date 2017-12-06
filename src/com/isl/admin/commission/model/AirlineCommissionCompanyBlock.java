package com.isl.admin.commission.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="airline_commission_company_block")
public class AirlineCommissionCompanyBlock implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id") 
	private Long id;
	@Column(name="name",columnDefinition="VARCHAR(100)") 
	private String  name;
	@Column(name="description") 
	private String  description;
	@Column(name="is_active",columnDefinition="BIT(1) default 0") 
	private boolean  isActive;

	@Column(name="is_master_block",columnDefinition="BIT(1) default 0") 
	private boolean  isMasterBlock;


	@Column(name="created_by_user_id") 
	private int  createdByUserID;	

	@Column(name="created_by_comp_id") 
	private int  createdByCompanyID;	

	@Column(name="applied_sheet_id") 
	private Long appliedSheetId;

	@Transient
	@Column(name="sheet_info") 
	private String sheetInfo;

	public String getSheetInfo() {
		return sheetInfo;
	}
	public void setSheetInfo(String sheetInfo) {
		this.sheetInfo = sheetInfo;
	}

	/*@OneToMany(fetch = FetchType.LAZY,mappedBy = "airlineCommissionCompanyBlock")
	private List<AirlineCommissionBlockRow> airlineCommissionBlockRowList;
	*/

	public boolean isMasterBlock() {
		return isMasterBlock;
	}
	public void setMasterBlock(boolean isMasterBlock) {
		this.isMasterBlock = isMasterBlock;
	}
	public int getCreatedByCompanyID() {
		return createdByCompanyID;
	}
	public void setCreatedByCompanyID(int createdByCompanyID) {
		this.createdByCompanyID = createdByCompanyID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getCreatedByUserID() {
		return createdByUserID;
	}
	public void setCreatedByUserID(int createdByUserID) {
		this.createdByUserID = createdByUserID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppliedSheetId() {
		return appliedSheetId;
	}
	public void setAppliedSheetId(Long appliedSheetId) {
		this.appliedSheetId = appliedSheetId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	/*public List<AirlineCommissionBlockRow> getAirlineCommissionBlockRowList() {
		return airlineCommissionBlockRowList;
	}
	public void setAirlineCommissionBlockRowList(List<AirlineCommissionBlockRow> airlineCommissionBlockRowList) {
		this.airlineCommissionBlockRowList = airlineCommissionBlockRowList;
	}*/


}
