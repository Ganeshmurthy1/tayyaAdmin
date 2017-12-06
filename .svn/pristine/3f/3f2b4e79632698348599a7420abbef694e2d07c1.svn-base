package com.isl.admin.commission.action;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.admin.commission.dao.AirlineCommissionSheetDaoImp;
import com.isl.admin.commission.model.AirlineCommissionSheet;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AirlineCommissionSheetCommonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	AirlineCommissionSheet airlinecommsheetrow = new AirlineCommissionSheet();
	AirlineCommissionSheetDaoImp sheetdao = new AirlineCommissionSheetDaoImp();
	List<AirlineCommissionSheet> airlineSheetitemlist;
	Map<String,Object> commisionSheetMap = new HashMap<String,Object>();
	String RemarkItem;
	String Sheetid;

	public String GetSheetCommissionCommon() throws JsonProcessingException,IOException
	{
       ObjectMapper mapper = new ObjectMapper();	
 		
 		JSONObject Sheetitemmap =  mapper.readValue(Sheetid, JSONObject.class);
		String Sheetid = (String) Sheetitemmap.get("Sheetid");
		
		long sheetidlong =  Long.parseLong(Sheetid);
		airlinecommsheetrow = sheetdao.getAirlineCommissionSheetCommons(sheetidlong);
		commisionSheetMap.put("ValidFrom", airlinecommsheetrow.getDtValidFrom()); 
		commisionSheetMap.put("ValidTill", airlinecommsheetrow.getDtValidTill()); 			
		commisionSheetMap.put("SheetId", airlinecommsheetrow.getSheetId()); 
		commisionSheetMap.put("CreatedByUserId", airlinecommsheetrow.getCreatedByUserId()); 
		commisionSheetMap.put("CreatedAt", airlinecommsheetrow.getCreatedAt()); 
		commisionSheetMap.put("SheetVersion", airlinecommsheetrow.getDealSheetVersion()); 

		return SUCCESS;
	} 	
	
	public String GetSheetRemark() throws JsonProcessingException,IOException
	{
		
		
 		ObjectMapper mapper = new ObjectMapper(); 	
 		JSONObject Sheetitemmap =  mapper.readValue(RemarkItem, JSONObject.class);
 		String iata_code = (String) Sheetitemmap.get("iata_code");
 		Integer sheetid = (Integer) Sheetitemmap.get("sheetid");
 		boolean is_plb = (boolean) Sheetitemmap.get("is_plb");
		String ssid = String.valueOf(sheetid); 		
 		Long sheet_id = new Long(ssid);	
		airlinecommsheetrow = sheetdao.getAirlineCommissionSheetRemark(sheet_id,iata_code,is_plb);
		
		if(is_plb)
		   commisionSheetMap.put("plb_remark", airlinecommsheetrow.getPlbRemark());
		else
			 commisionSheetMap.put("iata_remark", airlinecommsheetrow.getIataRemark());

		return SUCCESS;
	} 
	
	


	/**
	 * @return the jsonResult
	 */
	public Map getJsonResult() {
		return commisionSheetMap;
	}

	/**
	 * @param jsonResult the jsonResult to set
	 */
	public void setJsonResult(List commisionSheetJson) {
		this.commisionSheetMap = commisionSheetMap;
	}

	public String getRemarkItem() {
		return RemarkItem;
	}

	public void setRemarkItem(String remarkItem) {
		RemarkItem = remarkItem;
	}

	 
	public String getSheetid() {
		return Sheetid;
	}



	public void setSheetid(String sheetid) {
		Sheetid = sheetid;
	}

}
