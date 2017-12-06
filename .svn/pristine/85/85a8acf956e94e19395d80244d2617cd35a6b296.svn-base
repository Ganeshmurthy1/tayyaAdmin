package com.lintas.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.dao.EmployeeFilterDao;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class EmployeeFilterAction extends ActionSupport implements ModelDriven<CompanyFilterPage>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmployeeFilterAction.class);
	SessionMap<String, Object> sessionmap;
	private  CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	EmployeeFilterDao employeeFilterDao=new EmployeeFilterDao();
	
	public String showAllUsersByCompanyId(){
		User userSessionObj=(User)sessionmap.get("User");
		Company companySessionObj=(Company)sessionmap.get("Company");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setLoginUser(userSessionObj);
		companyFilter.setCompanyType(getText("global.Wholesaler"));
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=employeeFilterDao.getAllAgentsByCompanyId(companyFilterPage);
		if(newCompanyFilterPage!=null){
			companyFilterPage=newCompanyFilterPage;
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ALL_EMPLOYEES, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
		
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
