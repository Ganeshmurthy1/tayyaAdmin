package com.isl.admin.commission.action;

import java.io.IOException;
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
import com.isl.admin.commission.dao.AirlineCommissionSheetDaoImp;
import com.isl.admin.commission.model.AirlineCommissionSheet;
import com.isl.admin.commission.service.CommissionService;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.opensymphony.xwork2.ActionSupport;

public class AirlineCommissionSheetItemUpdateAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirlineCommissionSheetItemUpdateAction.class);
	
	SessionMap<String, Object>  sessionMap;

	AirlineCommissionSheetDaoImp sheetdao = new AirlineCommissionSheetDaoImp();
	Map Responsemap = new HashMap();
	String SheetItem;
	List<Object> commissionlist ;
	Boolean Isupdated = false;
	EmailNotificationDaoImp emailNotificationDaoImp = new EmailNotificationDaoImp();

	public String GetItemForUpdate() throws ParseException, IOException{

		//logger.info("SheetItem " + SheetItem);
		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, String> Sheetitemmap =  mapper.readValue(SheetItem, HashMap.class);
		String iata_code = Sheetitemmap.get("iata_code");
		String row_item = Sheetitemmap.get("row_item");
		String plbremark = Sheetitemmap.get("pr");
		String iataremark = Sheetitemmap.get("ir");
		String Sheetid = Sheetitemmap.get("Sheetid");
		//logger.info("row_item " + row_item);

		//logger.info("row_itemmap plbRemark" + row_itemmap.get("plbRemark"));
		try{
			AirlineCommissionSheet airlineCommissionSheetRow =  mapper.readValue(row_item, AirlineCommissionSheet.class); 

			airlineCommissionSheetRow.setPlbRemark(plbremark);
			airlineCommissionSheetRow.setIataRemark(iataremark);
			//logger.info("###   airlineCommissionSheetRow to be updated" + airlineCommissionSheetRow.toString());
			//logger.info("IataCommission " + airlineCommissionSheetRow.getIataCommission());
			List<AirlineCommissionSheet> airlineSheetitemlistToBeUpdated = new ArrayList<AirlineCommissionSheet>();
			airlineSheetitemlistToBeUpdated.add(airlineCommissionSheetRow);
			List<AirlineCommissionSheet> Updatedlist = null;
			Updatedlist = sheetdao.updateAirlineCommissionSheet(airlineSheetitemlistToBeUpdated);
			if(Updatedlist.size() > 0){				
				Isupdated = true;
				Company sessionCompanyObj = (Company) sessionMap.get("Company");				
				if(sessionCompanyObj!=null)
				{
					CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(sessionCompanyObj);
					logger.info("###   companyConfig " + companyConfig);
					if(companyConfig!=null)
					{
						new CommissionService().sheetChangeNotifyChildren(companyConfig, Long.valueOf(Sheetid), EmailNotification.EMAIL_TYPE_COMMISSION_SHEET_MODIFICATION);
						emailNotificationDaoImp.insertEmailNotification(sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), -1, EmailNotification.ACTION_PARENT_HIMSELF, EmailNotification.EMAIL_TYPE_COMMISSION_SHEET_MODIFICATION, Sheetid);
					}
				}
			}
			Responsemap.put("Issuccess", Isupdated);
		}
		catch(Exception e)
		{

		}
		return SUCCESS;
	}

	/*	public String GetSheetItemForUpdate() throws ParseException, IOException{

		//logger.info("SheetItem " + SheetItem);
		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, String> Sheetitemmap =  mapper.readValue(SheetItem, HashMap.class);
		String iata_code = Sheetitemmap.get("iata_code");
		String row_item = Sheetitemmap.get("row_item");
		String plbremark = Sheetitemmap.get("pr");
		String iataremark = Sheetitemmap.get("ir");
		logger.info("row_item " + row_item);

		//logger.info("row_itemmap plbRemark" + row_itemmap.get("plbRemark"));
		try{
			AirlineCommissionSheetRow airlineCommissionSheetRow =  mapper.readValue(row_item, AirlineCommissionSheetRow.class); 
			airlineCommissionSheetRow.setPlbRemark(plbremark);
			airlineCommissionSheetRow.setIataRemark(iataremark);
			logger.info("IataCommission " + airlineCommissionSheetRow.getIataCommission());
			List<AirlineCommissionSheetRow> airlineSheetitemlistToBeUpdated = new ArrayList<AirlineCommissionSheetRow>();
			airlineSheetitemlistToBeUpdated.add(airlineCommissionSheetRow);
			List<AirlineCommissionSheetRow> Updatedlist = null;
			Updatedlist = sheetdao.updateAirlineCommissionSheet(airlineSheetitemlistToBeUpdated);
			if(Updatedlist!=null){
				Isupdated = true;
			}
			Responsemap.put("Issuccess", Isupdated);
		}
		catch(Exception e)
		{

		}
		return SUCCESS;
	}*/


	public String GetListForUpdate() throws ParseException, IOException{
		// logger.info("SheetItem " + SheetItem);
		ObjectMapper mapper = new ObjectMapper();
		JSONObject dateobj = mapper.readValue(SheetItem, JSONObject.class);   	

		String VaildFromdate = (String) dateobj.get("fromdate");
		String VaildTodate = (String) dateobj.get("tilldate");

		try{
			List<AirlineCommissionSheet> Updatedlist = null;
			Updatedlist = sheetdao.updateAirlineCommissionSheetDates(VaildFromdate, VaildTodate, new Long(1));  	 		
			if(Updatedlist!=null){
				Isupdated = true;
			}
			Responsemap.put("Issuccess", Isupdated);
		}
		catch(Exception e)
		{

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
}
