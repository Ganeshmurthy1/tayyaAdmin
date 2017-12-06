package com.isl.admin.commission.action;

import java.io.IOException;
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

public class AirlineCommissionSheetAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	AirlineCommissionSheet airlinecommsheetrow = new AirlineCommissionSheet();
	AirlineCommissionSheetDaoImp sheetdao = new AirlineCommissionSheetDaoImp();
	List<AirlineCommissionSheet> airlineSheetitemlist;
	Map commisionSheetMap = new HashMap();
	String Sheetid;
	
 	public String GetFlightCommissionlist() throws JsonProcessingException,IOException
	{
 		
 		ObjectMapper mapper = new ObjectMapper();	
 		
 		JSONObject Sheetitemmap =  mapper.readValue(Sheetid, JSONObject.class);
		String Sheetid = (String) Sheetitemmap.get("Sheetid");
		
		long sheetidlong =  Long.parseLong(Sheetid);
		
 		airlineSheetitemlist = sheetdao.getAirlineCommissionSheet(sheetidlong);
 		//logger.info("airlineSheetitemlist size----"+airlineSheetitemlist.size());
 		
 		List<Object> commisionSheetJson = new ArrayList<Object>();
 		for (AirlineCommissionSheet airlineCommissionSheetRow : airlineSheetitemlist) {
 			Map commisionSheetItem = new HashMap();
 			String commissionRowJson = mapper.writeValueAsString(airlineCommissionSheetRow);
 			commisionSheetItem.put("airline", airlineCommissionSheetRow.getAirline());
 			commisionSheetItem.put("iata_code", airlineCommissionSheetRow.getIataCode());
 			commisionSheetItem.put("is_updatable", false);
 			commisionSheetItem.put("pr", airlineCommissionSheetRow.getPlbRemark());
 			commisionSheetItem.put("ir", airlineCommissionSheetRow.getIataRemark());
 			commisionSheetItem.put("Sheetid", Sheetid);
 			commisionSheetItem.put("row_item", commissionRowJson); 			
 			commisionSheetJson.add(commisionSheetItem); 		
		}
 		commisionSheetMap.put("sheet", commisionSheetJson);
 
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






	public String getSheetid() {
		return Sheetid;
	}



	public void setSheetid(String sheetid) {
		Sheetid = sheetid;
	}

	
}
