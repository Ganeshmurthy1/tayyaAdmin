package com.isl.admin.commission.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.email.notification.EmailNotification;
import com.email.notification.EmailNotificationDaoImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.admin.commission.dao.AirlineCommissionBlockDaoImp;
import com.isl.admin.commission.dao.AirlineCommissionSheetDaoImp;
import com.isl.admin.commission.model.AirlineCommissionBlock;
import com.isl.admin.commission.model.AirlineCommissionSheet;
import com.isl.admin.commission.service.CommissionService;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.opensymphony.xwork2.ActionSupport;

public class AirlineCommissionBlockItemUpdateAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;	
	AirlineCommissionBlockDaoImp sheetdao = new AirlineCommissionBlockDaoImp();
	Map Responsemap = new HashMap();
	String SheetItem;
	String blockid;
	List<Object> commissionlist ;
	Boolean Isupdated = false;
	EmailNotificationDaoImp emailNotificationDaoImp = new EmailNotificationDaoImp();

	public String GetBlockItemForUpdate() throws ParseException, IOException{

		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> Sheetitemmap =  mapper.readValue(SheetItem, HashMap.class);
		Boolean isDefaultSetMode = (Boolean) Sheetitemmap.get("defaultmode");
		Long masterSheetId = (Long) Sheetitemmap.get("sheetid");
		Long companyBlockId = (Long) Sheetitemmap.get("blockid");	
		if(isDefaultSetMode == null || !isDefaultSetMode )
		{
			String iata_code = (String) Sheetitemmap.get("iata_code");
			String row_item = (String) Sheetitemmap.get("block_item");
			try{
				AirlineCommissionBlock airlineCommissionBlockRow =  mapper.readValue(row_item, AirlineCommissionBlock.class); 			
				//logger.info("IataCommission " + airlineCommissionSheetRow.getIataCommission());
				List<AirlineCommissionBlock> airlineBlockitemlistToBeUpdated = new ArrayList<AirlineCommissionBlock>();
				airlineBlockitemlistToBeUpdated.add(airlineCommissionBlockRow);
				List<AirlineCommissionBlock> Updatedlist = null;
				Updatedlist = sheetdao.updateAirlineCommissionBlock(airlineBlockitemlistToBeUpdated);
				if(Updatedlist!=null){
					Isupdated = true;  	 
					Company sessionCompanyObj = (Company) sessionMap.get("Company");
					CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(sessionCompanyObj);
					new CommissionService().traverseAndNotifyChildren(companyConfig, airlineCommissionBlockRow.getAirlineCommissionCompanyBlock().getId(), EmailNotification.EMAIL_TYPE_COMMISSION_BLOCK_MODIFICATION);
					emailNotificationDaoImp.insertEmailNotification(sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), -1, EmailNotification.ACTION_PARENT_HIMSELF, EmailNotification.EMAIL_TYPE_COMMISSION_BLOCK_MODIFICATION, blockid);

				}

				Responsemap.put("Issuccess", Isupdated);
			}
			catch(Exception e)
			{

			}
		}
		else
		{
			BigDecimal plbdefault = (BigDecimal) Sheetitemmap.get("plbdefault");
			BigDecimal iatadefault = (BigDecimal) Sheetitemmap.get("plbdefault");

		}
			
					return SUCCESS;
		}




		/**
		 * @return the jsonResult
		 */
		public Map getJsonResult() {
			return Responsemap;
		}

		/**
		 * @param jsonResult the jsonResult to set
		 */
		public void setJsonResult(Boolean Isupdated) {
			this.Responsemap = Responsemap;
		}

		public String getSheetItem() {
			return SheetItem;
		}

		public void setSheetItem(String sheetItem) {
			SheetItem = sheetItem;
		}


		public List<Object> getCommissionlist() {
			return commissionlist;
		}


		public void setCommissionlist(List<Object> commissionlist) {
			this.commissionlist = commissionlist;
		}




		@Override
		public void setSession(Map<String, Object> map) {
			sessionMap = (SessionMap<String, Object>) map;

		}




		public String getBlockid() {
			return blockid;
		}




		public void setBlockid(String blockid) {
			this.blockid = blockid;
		}
	}
