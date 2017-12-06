package com.isl.admin.commission.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.admin.commission.dao.AirlineCommissionBlockDaoImp;
import com.isl.admin.commission.dao.AirlineCommissionSheetDaoImp;
import com.isl.admin.commission.model.AirlineCommissionBlock;
import com.isl.admin.commission.model.AirlineCommissionCompanyBlock;
import com.isl.admin.commission.model.AirlineCommissionSheet;
import com.isl.admin.commission.service.CommissionService;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.opensymphony.xwork2.ActionSupport;

public class AirlineCommissionBlockAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirlineCommissionBlockAction.class);
	
	SessionMap<String, Object>  sessionMap;

	AirlineCommissionSheet airlinecommsheetrow = new AirlineCommissionSheet();
	AirlineCommissionBlock airlinecommblockrowxx = new AirlineCommissionBlock();
	AirlineCommissionBlockDaoImp airlinecommblockimp = new AirlineCommissionBlockDaoImp();
	AirlineCommissionSheetDaoImp sheetdao = new AirlineCommissionSheetDaoImp();
	CommissionService remarkservice = new CommissionService();
	List<AirlineCommissionBlock> airlineBlockitemlist;
	List<AirlineCommissionSheet> airlineSheetitemlist;

	Map commisionBlockMap = new HashMap();
	String Blockid;
	String SheetItem;
	String remarkitem;

	public synchronized String GetCommissionBlocklist() throws JsonProcessingException,IOException,Exception{
		logger.info("Blockid" +Blockid);
		ObjectMapper mappercom = new ObjectMapper();

		JSONObject Sheetitemmap =  mappercom.readValue(Blockid, JSONObject.class);
		String id = (String) Sheetitemmap.get("blockid");

		logger.info("id" +id);
		Long blockid =  Long.parseLong(id);
		logger.info("blockid" +blockid);
		airlineBlockitemlist = airlinecommblockimp.getAirlineCommissionBlockRow(blockid);
		logger.info("airlineBlockitemlist" +airlineBlockitemlist.size());
		/*if(airlineBlockitemlist.size() == 0){
			airlineBlockitemlist = airlinecommblockimp.createAirlineCommissionBlock(companyparentid,companychildid,new Long(1));
			//logger.info("after insert airlineBlockitemlist" +airlineBlockitemlist.size());
		}*/
		if(airlineBlockitemlist.size() > 0){

			Company sessionCompanyObj = (Company) sessionMap.get("Company");
			CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(sessionCompanyObj);
			if(sessionCompanyObj.getCompanyRole().isAgent() || sessionCompanyObj.getCompanyRole().isDistributor())
				airlineSheetitemlist = remarkservice.getAirLineCommissionSheetCompany(sessionCompanyObj.getCompanyid(), companyConfig.getConfig_id());
			else	
			{
				AirlineCommissionCompanyBlock airlineCommissionCompanyBlock = airlinecommblockimp.getAirlineCommissionCompanyBlock(blockid);
				airlineSheetitemlist = sheetdao.getAirlineCommissionSheet(airlineCommissionCompanyBlock.getAppliedSheetId());
			}

			ObjectMapper mapper = new ObjectMapper();	
			List<Object> commisionBlockJson = new ArrayList<Object>();
			for(int i=0; i < airlineBlockitemlist.size();i++){
				AirlineCommissionBlock blockrow = airlineBlockitemlist.get(i);
				AirlineCommissionSheet sheetrow = airlineSheetitemlist.get(i);
				HashMap<String,Object> commisionBlockItem = new HashMap<String,Object>();
				String commissionBlockJson = mapper.writeValueAsString(blockrow); 
				String commissionRowJson = mapper.writeValueAsString(sheetrow); 
				commisionBlockItem.put("airline", sheetrow.getAirline());
				commisionBlockItem.put("iata_code", blockrow.getIataCode());
				commisionBlockItem.put("is_updatable", false); 	 		
				commisionBlockItem.put("block_item", commissionBlockJson); 	
				commisionBlockItem.put("row_item", commissionRowJson); 	
				commisionBlockJson.add(commisionBlockItem);
			}
			commisionBlockMap.put("sheet", commisionBlockJson);
		}
		return SUCCESS;		

	}

	public String GetDealSheetRemark(){

		logger.info("remarkitem" +remarkitem);
		try {
			ObjectMapper mappercom = new ObjectMapper();

			JSONObject Sheetitemmap =  mappercom.readValue(remarkitem, JSONObject.class);
			String companyid = (String) Sheetitemmap.get("companyid");
			String configid = (String) Sheetitemmap.get("configid");
			String iata_code = (String) Sheetitemmap.get("iata_code");
			Integer company_id = new  Integer(companyid);
			Integer config_id = new  Integer(configid);
			airlinecommsheetrow = remarkservice.getAirLineCommissionSheetCompany(iata_code,company_id, config_id);
			String commissionRowJson = mappercom.writeValueAsString(airlinecommsheetrow); 
			commisionBlockMap.put("remark", commissionRowJson);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}




	/**
	 * @return the jsonResult
	 */
	public Map getJsonResult() {
		return commisionBlockMap;
	}

	/**
	 * @param jsonResult the jsonResult to set
	 */
	public void setJsonResult(List commisionSheetJson) {
		this.commisionBlockMap = commisionBlockMap;
	}

	public String getSheetItem() {
		return SheetItem;
	}

	public void setSheetItem(String sheetItem) {
		SheetItem = sheetItem;
	}


	public String getRemarkitem() {
		return remarkitem;
	}

	public void setRemarkitem(String remarkitem) {
		this.remarkitem = remarkitem;
	}

	public String getBlockid() {
		return Blockid;
	}

	public void setBlockid(String blockid) {
		Blockid = blockid;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;

	}






}
