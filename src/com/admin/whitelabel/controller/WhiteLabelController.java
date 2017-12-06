/**
 * 
 */
package com.admin.whitelabel.controller;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.whitelabel.dao.WhiteLabelDao;
import com.admin.whitelabel.model.CompanyTheme;
import com.admin.whitelabel.model.ThemeAuthRequestModel;
import com.admin.whitelabel.model.ThemeInsertionRequestModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author      : Manish Samrat
 * @createdAt   : 28/06/2017
 * @version
 */
public class WhiteLabelController extends ActionSupport implements SessionAware,ModelDriven<ThemeInsertionRequestModel>{

	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	ThemeInsertionRequestModel themeRequestModel=new ThemeInsertionRequestModel();
	ThemeAuthRequestModel authRequestModel=new ThemeAuthRequestModel();
	Boolean isUserExisted=false;
	CompanyTheme companyTheme=new CompanyTheme();
	WhiteLabelDao whiteLabelDao=new WhiteLabelDao();
	
	public String fetchingUsersWhitelableExistence(){
		companyTheme=whiteLabelDao.verifyingUserWhitelabelExistence(authRequestModel);
		if(companyTheme!=null && companyTheme.getId()>0)
			isUserExisted=true;
		return SUCCESS;
	}
	
	public String insertIntoThemeCompanyByAdmin(){
		Boolean insertionStatus=whiteLabelDao.insertIntoCompanyTheme(themeRequestModel);
		if(insertionStatus)
			return SUCCESS;
		return ERROR;
	}
	
	public String editWhitelableByAdmin(){
		CompanyTheme companyTheme=whiteLabelDao.editCompanyThemeDetails(themeRequestModel.getConfigId());
		if(companyTheme.getId()!=null && companyTheme.getId()>0){
			setCompanyTheme(companyTheme);
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String updateWhitelableByAdmin(){
		CompanyTheme companyTheme=whiteLabelDao.updateCompanyThemeDetails(themeRequestModel);
		if(companyTheme.getId()!=null && companyTheme.getId()>0){
//			setCompanyTheme(companyTheme);
			return SUCCESS;
		}
		return ERROR;
	}
	
	@Override
	public ThemeInsertionRequestModel getModel() {
		return themeRequestModel;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;
	}
	
	public CompanyTheme getCompanyTheme() {
		return companyTheme;
	}

	public void setCompanyTheme(CompanyTheme companyTheme) {
		this.companyTheme = companyTheme;
	}

	public Boolean getIsUserExisted() {
		return isUserExisted;
	}

	public void setIsUserExisted(Boolean isUserExisted) {
		this.isUserExisted = isUserExisted;
	}

	public ThemeInsertionRequestModel getThemeRequestModel() {
		return themeRequestModel;
	}

	public void setThemeRequestModel(ThemeInsertionRequestModel themeRequestModel) {
		this.themeRequestModel = themeRequestModel;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public ThemeAuthRequestModel getAuthRequestModel() {
		return authRequestModel;
	}

	public void setAuthRequestModel(ThemeAuthRequestModel authRequestModel) {
		this.authRequestModel = authRequestModel;
	}
}
