package com.lintas.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.dao.CompanyConfigFilterDao;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class CompanyConfigFilterAction extends ActionSupport implements ModelDriven<CompanyFilterPage>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyConfigFilterAction.class);
	SessionMap<String, Object> sessionMap;
	private  CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	CompanyConfigFilterDao  companyConfigFilterDao=new  CompanyConfigFilterDao();
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";

	/* fetching all company Config List*/
	public String getCompanyConfigList(){
		Company companySessionObj=(Company)sessionMap.get("Company");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage = companyConfigFilterDao.getCompanyConfigList(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getCompanyConfigList()!=null && newCompanyFilterPage.getCompanyConfigList().size()>0){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_FILTER.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
			companyFilterPage= newCompanyFilterPage;
		}

		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ALL_COMPANY_CONFIG_ALL, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  

		User user = (User)sessionMap.get("User");
		historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.ALL_COMPANY_CONFIG_ALL.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);



		return SUCCESS;
	}


	@Override
	public void setSession(Map<String, Object>  map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public CompanyFilterPage getModel() {
		// TODO Auto-generated method stub
		return companyFilterPage;
	}

	public CompanyFilterPage getCompanyFilterPage() {
		return companyFilterPage;
	}

	public void setCompanyFilterPage(CompanyFilterPage companyFilterPage) {
		this.companyFilterPage = companyFilterPage;
	}

}
