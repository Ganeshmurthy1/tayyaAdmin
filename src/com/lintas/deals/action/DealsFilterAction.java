package com.lintas.deals.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.User;
import com.lintas.deals.DAO.CrudOperationDealsDaoImp;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class DealsFilterAction extends ActionSupport  implements ModelDriven<CompanyFilterPage>,SessionAware{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DealsFilterAction.class);
	CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	SessionMap<String, Object> sessionMap;
	public String listOfDeal()
	{
		User user=(User)sessionMap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=new CrudOperationDealsDaoImp().listOfDeals(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getCrudOperationDealsList()!=null) 
			companyFilterPage= newCompanyFilterPage;

		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		historyInfo.changeNature(BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		new HistoryManager().inSertHistory(historyInfo);

		  historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"show list of deals filter submit click ");
		 
		 return SUCCESS;
	}


	public CompanyFilterPage getCompanyFilterPage() {
		return companyFilterPage;
	}


	public void setCompanyFilterPage(CompanyFilterPage companyFilterPage) {
		this.companyFilterPage = companyFilterPage;
	}


	@Override
	public CompanyFilterPage getModel() {
		// TODO Auto-generated method stub
		return companyFilterPage;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap<String, Object>) arg0;
	}


}
