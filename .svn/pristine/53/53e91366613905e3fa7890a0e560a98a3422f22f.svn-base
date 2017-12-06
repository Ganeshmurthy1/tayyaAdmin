package com.lintas.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.BusMarkupDao;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.bus.model.BusMarkup;

public class BusMarkupAction extends ActionSupport implements ModelDriven<BusMarkup>, SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BusMarkupAction.class);

	BusMarkup bm = new BusMarkup();
	BusMarkupDao bmDAO = new BusMarkupDao();
	SessionMap<String, Object> sessionmap;
	List<BusMarkup> li;
	private String configData;
	private CompanyConfig cc = null;
	private Company c = null;
	private List<CompanyConfig> AgencyConfiglist;
	private List<CompanyConfig> companyConfigIdsList;
	CountryDao cDAO = new CountryDao();
	private InputStream inputStream;
	private List<BusMarkup> busmarkupList;

	private BusMarkup CurrentMarkupProfile;
	private static DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

	public String setCompanyMarkup() {
		try {
			logger.info("---------conifigData------------" + getConfigData());
			String[] parts = null;
			String companyId = null;
			String config_Id = null;
			String configName = null;
			String configNumber = null;
			String companyName = null;
			if (getConfigData().contains("/")) {
				parts = getConfigData().split("/");
				companyId = parts[0];
				logger.info("---------companyId------------" + companyId);
				config_Id = parts[1];
				logger.info("---------configId------------" + config_Id);
				configName = parts[2];
				logger.info("---------configName------------" + configName);
				configNumber = parts[3];
				logger.info("---------configNumber------------" + configNumber);
				companyName = parts[4];
				logger.info("---------companyName------------" + companyName);

			}
			c = (Company) sessionmap.get("Company");
			bm.setCreatedByCompanyName(c.getCompanyname());
			User sessionUser = (User) sessionmap.get("User");
			bm.setCompanyId(Integer.parseInt(companyId));
			bm.setConfigId(Integer.parseInt(config_Id));
			bm.setConfig_number(configNumber);
			bm.setConfigname(configName);
			bm.setCompanyName(companyName);
			bm.setCreatedbyCompanyId(c.getCompanyid());
			bm.setCreatedbyUserId(sessionUser.getId());
			bm.setModifiedbyUserId(sessionUser.getId());
			bm.setCreatedDate(new Date());
			bm.setModifiedDate(bm.getCreatedDate());
			/*bm.getCity();*/
			String dest = bm.getDestination();
			String ori = bm.getOrigin();
			String arrDate = bm.getArrDate();
			String depDate = bm.getDepDate();

			String busOperators = bm.getBusOperators();

			String busTypes = bm.getBusTypes();
			String promofireStartDate = bm.getPromofareStartDate();
			String promofireEndDate = bm.getPromofareEndDate();


			logger.info("ori------------" + ori);
			logger.info("dest------------" + dest);

			if (promofireStartDate == null || promofireStartDate.equals("")) {
				promofireStartDate = "ALL";
				bm.setPromofareStartDate(promofireStartDate);
			} else {
				bm.setPromofareStartDate(promofireStartDate);
			}
			if (promofireEndDate == null || promofireEndDate.equals("")) {
				promofireEndDate = "ALL";
				bm.setPromofareEndDate(promofireEndDate);
			} else {
				bm.setPromofareEndDate(promofireEndDate);
			}
			if (arrDate == null || arrDate.equals("")) {
				arrDate = "ALL";
				bm.setArrDate(arrDate);
			} else {
				bm.setArrDate(arrDate);
			}
			if (depDate == null || depDate.equals("")) {
				depDate = "ALL";
				bm.setDepDate(depDate);
			} else {
				bm.setDepDate(depDate);
			}
			if (dest == null || dest.equals("")) {
				dest = "ALL";
				bm.setDestination(dest);
			} else {
				bm.setDestination(splitDestandOriginAfterThreeCommas(dest).toString());
			}
			if (ori == null || ori.equals("")) {
				ori = "ALL";
				bm.setOrigin(ori);
			} else {
				bm.setOrigin(splitDestandOriginAfterThreeCommas(ori).toString());

			} 

			if (busOperators == null || busOperators.equals("")) {
				busOperators = "ALL";
				bm.setBusOperators(busOperators);
			} else {
				bm.setBusOperators(busOperators);
			}

			if (busTypes == null || busTypes.equals("")) {
				busTypes = "ALL";
				bm.setBusTypes(busTypes);
			} else {
				bm.setBusTypes(busTypes);
			}

			BusMarkup   busMarkup = bmDAO.insertMarkupDetails(bm);
			if (busMarkup!=null && busMarkup.getMarkupId() > 0) 
				addActionMessage(getText("global.setCompanyMarkup"));
			else  
				addActionError(getText("global.setCompanyMarkupfailed"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("-----Exception-----" + e.getMessage());
			addActionError(getText("global.setCompanyMarkupfailederror"));
			// addActionError("Failed. Try again.");
			return ERROR;
		}
		return SUCCESS;
	}

	public StringBuilder splitDestandOriginAfterThreeCommas(String str) {
		// logger.info("str--"+str);
		String[] parts = str.split(",");
		StringBuilder strList = new StringBuilder();
		if (parts.length > 0) {
			for (int x = 0; x < parts.length - 2; x = x + 3) {
				String tmpStr = parts[x] + "," + parts[x + 1] + "," + parts[x + 2];
				strList.append(tmpStr + ";");
			}
		}

		return strList;
	}

	/*
	 * this method for fetching All parent company company config ids for set
	 * markup
	 */
	public String getCompanyConfigIds() {

		Company sessionObj = (Company) sessionmap.get("Company");
		BusMarkupDao markupDao = new BusMarkupDao();
		List<Company> companyIds = markupDao.getCompanyIds(sessionObj);
		if (companyIds != null && companyIds.size() > 0) {
			companyConfigIdsList = markupDao.getCompanyConfigIds(companyIds);
			logger.info("---------companyConfigIdsList-------------" + companyConfigIdsList.size());
			/*
			 * List<CompanyConfig> newcompanyConfigIdsList= new
			 * ArrayList<CompanyConfig>(); for(CompanyConfig
			 * companyConfig:companyConfigIdsList){
			 * if(!companyConfig.isWhitelable() &&
			 * companyConfig.getConfig_id()!=1){
			 * newcompanyConfigIdsList.add(companyConfig); } }
			 */
			// sessionmap.put("companyConfigIds",companyConfigIdsList);
		}

		List<CompanyConfig> agencycompanyConfigIdsList = markupDao.getAgencyCompanyConfigIds(sessionObj);
		List<CompanyConfig> newAgencycompanyConfigIdsList = new ArrayList<CompanyConfig>();
		if (agencycompanyConfigIdsList != null && agencycompanyConfigIdsList.size() > 0) {
			AgencyConfiglist = agencycompanyConfigIdsList;
		}

		/*
		 * HistoryInfo historyInfo = (HistoryInfo)
		 * ((sessionmap.get("history")!=null)?sessionmap.get("history"):new
		 * HistoryInfo());
		 * historyInfo.changeNature(BrowsingOptionPageEnum.ADD_HOTEL_MARKUP,
		 * BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);
		 * new HistoryManager().inSertHistory(historyInfo);
		 */

		return SUCCESS;
	}

	/* load All Company Markups data from to DB */
	public String showMarkupList() {
		Company sessionObj = (Company) sessionmap.get("Company");
		List<BusMarkup> markupList = bmDAO.getMarkupList(sessionObj);
		logger.info("-----------markup list size1-------------" + markupList.size());
		if (markupList != null && markupList.size() > 0) {
			logger.info("-----------markup list size2-------------" + markupList.size());
			busmarkupList = markupList;
		}
		return SUCCESS;
	}

	/* delete CompanyMarkup data using MarkupId from DB */
	public String deleteMarkupList() {
		logger.info("--------Delete getMarkupId....------" + bm.getMarkupId());
		// cm.setMarkupId(cm.getMarkupId());
		boolean isdelete = bmDAO.deleteMarkupDetails(bm.getMarkupId());
		if (isdelete) {
			showSuccessMessage("deleted");
			return SUCCESS;
		} else {
			showSuccessMessage("failed");
			return ERROR;
		}
	}

	/* this method for passing markupId fetching compantyMarkup data */
	public String getMarkupProfile() {
		logger.info("------getmarkup Id........" + bm.getMarkupId());
		CurrentMarkupProfile = bmDAO.getMarkupDetails(bm);
		if (CurrentMarkupProfile != null) {
			CompanyConfig compConfig = bmDAO.getCompanyUserIdByConfigId(CurrentMarkupProfile.getConfigId());
			if (compConfig != null) {
				CurrentMarkupProfile.setCompanyUserId(compConfig.getCompanyUserId());
			}
		}
		Company sessionObj = (Company) sessionmap.get("Company");
		List<BusMarkup> markupList = bmDAO.getMarkupList(sessionObj);
		logger.info("-----------markup list size1-------------" + markupList.size());
		if (markupList != null && markupList.size() > 0) {
			logger.info("-----------markup list size2-------------" + markupList.size());
			busmarkupList = markupList;
		}
		return SUCCESS;
	}

	/* this method for passing markupId update compantyMarkup data */
	public String updateBusCompanyMarkup() {
		User sessionUser = (User) sessionmap.get("User");
		CurrentMarkupProfile = bmDAO.getMarkupDetails(bm);
		bm.setMarkupId(CurrentMarkupProfile.getMarkupId());
		boolean updated = bmDAO.updateBusMarkup(bm);

		if (updated) {
			addActionMessage(getText("globlal.updateBusMarkupsuccess"));
			/*
			 * new NotificationAction().insertNotificationOneandAll(sessionUser,
			 * String.valueOf(bm.getMarkupId()),"Bus markup updated",
			 * "Bus markup updated"
			 * ,NotificationInventoryTypeEnum.BUS_MARKUP.getId(),true,false,
			 * false,true,false,false);
			 */
			return SUCCESS;
		} else {
			addActionError(getText("global.updateBusmarkuperror"));
			return ERROR;
		}
	}

	public BusMarkup getCurrentMarkupProfile() {
		return CurrentMarkupProfile;
	}

	public void setCurrentMarkupProfile(BusMarkup currentMarkupProfile) {
		CurrentMarkupProfile = currentMarkupProfile;
	}

	@SuppressWarnings("deprecation")
	public void showSuccessMessage(String mes) {
		inputStream = new StringBufferInputStream(mes);

	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap = (SessionMap<String, Object>) map;
	}

	@Override
	public BusMarkup getModel() {
		// TODO Auto-generated method stub
		return bm;
	}

	public String getConfigData() {
		return configData;
	}

	public void setConfigData(String configData) {
		this.configData = configData;
	}

	public List<CompanyConfig> getAgencyConfiglist() {
		return AgencyConfiglist;
	}

	public void setAgencyConfiglist(List<CompanyConfig> agencyConfiglist) {
		AgencyConfiglist = agencyConfiglist;
	}

	public List<CompanyConfig> getCompanyConfigIdsList() {
		return companyConfigIdsList;
	}

	public void setCompanyConfigIdsList(List<CompanyConfig> companyConfigIdsList) {
		this.companyConfigIdsList = companyConfigIdsList;
	}

	public List<BusMarkup> getBusmarkupList() {
		return busmarkupList;
	}

	public void setBusmarkupList(List<BusMarkup> busmarkupList) {
		this.busmarkupList = busmarkupList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
