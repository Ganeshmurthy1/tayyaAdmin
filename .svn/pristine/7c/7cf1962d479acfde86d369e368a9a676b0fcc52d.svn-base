/**
 * 
 */
package com.admin.insurance.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.insurance.dao.InsuranceMarkupDao;
import com.admin.insurance.daoImp.InsuranceMarkupDaoImp;
import com.admin.insurance.model.InsuranceMarkup;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author info : Manish Samrat
 * @createdAt : 07/06/2017
 * @version : 2.0
 */

public class InsuranceMarkupAction extends ActionSupport implements ModelDriven<InsuranceMarkup>, SessionAware,InsuranceMarkupDao{

	private String configData;
	private List<CompanyConfig> companyConfigIdsList;
	private Company c = null;
	SessionMap<String, Object> sessionmap;
	private List<CompanyConfig> AgencyConfiglist;
	InsuranceMarkup insuranceMarkup=new InsuranceMarkup();
	InsuranceMarkupDaoImp insuranceMarkupDaoImp=new InsuranceMarkupDaoImp();
	private InsuranceMarkup CurrentMarkupProfile;
	private List<InsuranceMarkup> insuranceMarkupList;
	private InputStream inputStream;
	
	Logger logger=Logger.getLogger(InsuranceMarkupAction.class);
	
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
			
			insuranceMarkup.setCreatedByCompanyName(c.getCompanyname());
			User sessionUser = (User) sessionmap.get("User");
			insuranceMarkup.setCompanyId(Integer.parseInt(companyId));
			insuranceMarkup.setConfigId(Integer.parseInt(config_Id));
			insuranceMarkup.setConfig_number(configNumber);
			insuranceMarkup.setConfigname(configName);
			insuranceMarkup.setCompanyName(companyName);
			insuranceMarkup.setCreatedbyCompanyId(c.getCompanyid());
			insuranceMarkup.setCreatedbyUserId(sessionUser.getId());
			insuranceMarkup.setModifiedbyUserId(sessionUser.getId());
			insuranceMarkup.setCreatedDate(new Date());
			insuranceMarkup.setModifiedDate(insuranceMarkup.getCreatedDate());
			
			String promofireStartDate = insuranceMarkup.getPromofareStartDate();
			String promofireEndDate = insuranceMarkup.getPromofareEndDate();

			if (promofireStartDate == null || promofireStartDate.equals("")) {
				promofireStartDate = "ALL";
				insuranceMarkup.setPromofareStartDate(promofireStartDate);
			} else {
				insuranceMarkup.setPromofareStartDate(promofireStartDate);
			}
			if (promofireEndDate == null || promofireEndDate.equals("")) {
				promofireEndDate = "ALL";
				insuranceMarkup.setPromofareEndDate(promofireEndDate);
			} else {
				insuranceMarkup.setPromofareEndDate(promofireEndDate);
			}
			
			InsuranceMarkup   markup = insuranceMarkupDaoImp.insertMarkupDetails(insuranceMarkup);
			if (markup!=null && markup.getMarkupId() > 0) 
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
	
	public String getCompanyConfigIds() {
		Company sessionObj = (Company) sessionmap.get("Company");
		InsuranceMarkupDaoImp markupDao = new InsuranceMarkupDaoImp();
		List<Company> companyIds = markupDao.getCompanyIds(sessionObj);
		if (companyIds != null && companyIds.size() > 0) {
			companyConfigIdsList = markupDao.getCompanyConfigIds(companyIds);
		}

		List<CompanyConfig> agencycompanyConfigIdsList = markupDao.getAgencyCompanyConfigIds(sessionObj);
		List<CompanyConfig> newAgencycompanyConfigIdsList = new ArrayList<CompanyConfig>();
		if (agencycompanyConfigIdsList != null && agencycompanyConfigIdsList.size() > 0) {
			AgencyConfiglist = agencycompanyConfigIdsList;
		}

		return SUCCESS;
	}
	
	/* this method for passing markupId fetching compantyMarkup data */
	public String getMarkupProfile() {
		logger.info("------getmarkup Id........" + insuranceMarkup.getMarkupId());
		CurrentMarkupProfile = insuranceMarkupDaoImp.getMarkupDetails(insuranceMarkup);
		if (CurrentMarkupProfile != null) {
			CompanyConfig compConfig = insuranceMarkupDaoImp.getCompanyUserIdByConfigId(CurrentMarkupProfile.getConfigId());
			if (compConfig != null) {
				CurrentMarkupProfile.setCompanyUserId(compConfig.getCompanyUserId());
			}
		}
		Company sessionObj = (Company) sessionmap.get("Company");
		List<InsuranceMarkup> markupList = insuranceMarkupDaoImp.getMarkupList(sessionObj);
		logger.info("-----------markup list size1-------------" + markupList.size());
		if (markupList != null && markupList.size() > 0) {
			logger.info("-----------markup list size2-------------" + markupList.size());
			insuranceMarkupList = markupList;
		}
		return SUCCESS;
	}
	
	public String updateInsuranceCompanyMarkup() {
		User sessionUser = (User) sessionmap.get("User");
		CurrentMarkupProfile = insuranceMarkupDaoImp.getMarkupDetails(insuranceMarkup);
		insuranceMarkup.setMarkupId(CurrentMarkupProfile.getMarkupId());
		boolean updated = insuranceMarkupDaoImp.updateInsuranceMarkup(insuranceMarkup);

		if (updated) {
			addActionMessage(getText("globlal.updateInsuranceMarkupsuccess"));
			return SUCCESS;
		} else {
			addActionError(getText("global.updateinsurancemarkuperror"));
			return ERROR;
		}
	}
	
	/* delete CompanyMarkup data using MarkupId from DB */
	public String deleteMarkupList() {
		logger.info("--------Delete getMarkupId....------" + insuranceMarkup.getMarkupId());
		// cm.setMarkupId(cm.getMarkupId());
		boolean isdelete = insuranceMarkupDaoImp.deleteMarkupDetails(insuranceMarkup.getMarkupId());
		if (isdelete) {
			showSuccessMessage("deleted");
			return SUCCESS;
		} else {
			showSuccessMessage("failed");
			return ERROR;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void showSuccessMessage(String mes) {
		setInputStream(new StringBufferInputStream(mes));

	}

	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionmap = (SessionMap<String, Object>) map;
	}
	@Override
	public InsuranceMarkup getModel() {
		return insuranceMarkup;
	}

	public String getConfigData() {
		return configData;
	}

	public List<CompanyConfig> getCompanyConfigIdsList() {
		return companyConfigIdsList;
	}

	public SessionMap<String, Object> getSessionmap() {
		return sessionmap;
	}

	public List<CompanyConfig> getAgencyConfiglist() {
		return AgencyConfiglist;
	}

	public InsuranceMarkup getInsuranceMarkup() {
		return insuranceMarkup;
	}

	public void setConfigData(String configData) {
		this.configData = configData;
	}

	public void setCompanyConfigIdsList(List<CompanyConfig> companyConfigIdsList) {
		this.companyConfigIdsList = companyConfigIdsList;
	}

	public void setSessionmap(SessionMap<String, Object> sessionmap) {
		this.sessionmap = sessionmap;
	}

	public void setAgencyConfiglist(List<CompanyConfig> agencyConfiglist) {
		AgencyConfiglist = agencyConfiglist;
	}

	public void setInsuranceMarkup(InsuranceMarkup insuranceMarkup) {
		this.insuranceMarkup = insuranceMarkup;
	}

	public InsuranceMarkup getCurrentMarkupProfile() {
		return CurrentMarkupProfile;
	}

	public void setCurrentMarkupProfile(InsuranceMarkup currentMarkupProfile) {
		CurrentMarkupProfile = currentMarkupProfile;
	}

	public Company getC() {
		return c;
	}

	public List<InsuranceMarkup> getInsuranceMarkupList() {
		return insuranceMarkupList;
	}

	public void setC(Company c) {
		this.c = c;
	}

	public void setInsuranceMarkupList(List<InsuranceMarkup> insuranceMarkupList) {
		this.insuranceMarkupList = insuranceMarkupList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
