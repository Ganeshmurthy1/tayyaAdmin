package com.isl.admin.commission.model;

import java.io.Serializable;
import java.util.List;

public class CommissionActionStatus implements Serializable {
	public CommissionActionStatus(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public CommissionActionStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AirlineCommissionMasterSheet getMasterSheet() {
		return masterSheet;
	}
	public void setMasterSheet(AirlineCommissionMasterSheet masterSheet) {
		this.masterSheet = masterSheet;
	}
	public AirlineCommissionCompanyBlock getCompanyBlock() {
		return companyBlock;
	}
	public void setCompanyBlock(AirlineCommissionCompanyBlock companyBlock) {
		this.companyBlock = companyBlock;
	}
	private int status;
	private String message;
	private AirlineCommissionMasterSheet masterSheet;
	private List<AirlineCommissionSheet> airlineCommissionSheetRows;
	private AirlineCommissionCompanyBlock companyBlock;


	public static final int CODE_DEFAULT = 0;	
	public static final int CODE_SUCCESS = 1;	
	public static final int CODE_SHEET_INSERTION_FALIED = 2;
	public static final int CODE_SHEET_DUPLICATION_FALIED = 3;
	public static final int CODE_SHEET_MODIFICATION_FALIED = 4;
	public static final int CODE_BLOCK_INSERTION_FALIED = 5;
	public static final int CODE_BLOCK_DUPLICATION_FALIED = 6;
	public static final int CODE_BLOCK_MODIFICATION_FALIED = 7;
	public static final int CODE_SHEET_NO_SHEETROW_FOUND  = 8;

	public static final String MESSAGE_DEFAULT = "";	
	public static final String MESSAGE_SUCCESS = "Successfully done";	
	public static final String MESSAGE_SHEET_INSERTION_FALIED = "Sheet insertion failed";
	public static final String MESSAGE_SHEET_DUPLICATION_FALIED = "Sheet duplication failed";
	public static final String MESSAGE_SHEET_MODIFICATION_FALIED = "Sheet modification failed";
	public static final String MESSAGE_BLOCK_INSERTION_FALIED = "Block insertion failed";
	public static final String MESSAGE_BLOCK_DUPLICATION_FALIED = "Block duplication failed";
	public static final String MESSAGE_BLOCK_MODIFICATION_FALIED = "Block modification failed";
	public static final String MESSAGE_SHEET_NO_SHEETROW_FOUND = "No Deal Sheet Found";



}
