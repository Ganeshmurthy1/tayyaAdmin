package com.lintas.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.insurance.daoImp.InsuranceMarkupDaoImp;
import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.DAO.BusMarkupDao;
import com.lintas.admin.DAO.MarkupDao;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class MarkupFilterAction extends ActionSupport implements ModelDriven<CompanyFilterPage>,SessionAware{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MarkupFilterAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String , Object> sessionMap;
 CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	MarkupDao mDao=new MarkupDao();
	InsuranceMarkupDaoImp insuranceMarkupDao=new InsuranceMarkupDaoImp();
	BusMarkupDao bmDao=new BusMarkupDao();
	/*load All Company Markups data from to DB */
	public String showFlightMarkupList(){ 
		Company sessionObj=(Company)sessionMap.get("Company");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(sessionObj);
		companyFilter.setCompanyId(sessionObj.getCompanyid());
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=mDao.getFilterFlightMarkupList(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getFlightMarkupList()!=null){
			companyFilterPage= newCompanyFilterPage;
		}
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.FLIGHT_MARKUP_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  
		return SUCCESS;
	}

	/*load All Company Markups data from to DB */
	public String showHotelMarkupList(){
		Company sessionObj=(Company)sessionMap.get("Company");
		
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(sessionObj);
		companyFilter.setCompanyId(sessionObj.getCompanyid());
		companyFilterPage.setCompanyFilter(companyFilter);
		
		CompanyFilterPage newCompanyFilterPage=mDao.getFilterHotelMarkupList(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getHotelMarkupList()!=null){
			companyFilterPage= newCompanyFilterPage;
		}
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.HOTEL_MARKUP_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  
		return SUCCESS;
	}
	
	
	/*load All Company Markups data from to DB */
	public String showBusMarkupList(){  
Company sessionObj=(Company)sessionMap.get("Company");
		
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(sessionObj);
		companyFilter.setCompanyId(sessionObj.getCompanyid());
		companyFilterPage.setCompanyFilter(companyFilter);
		
		CompanyFilterPage newCompanyFilterPage=bmDao.getFilterBusMarkupList(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getBusMarkupList()!=null){
			companyFilterPage= newCompanyFilterPage;
		} 
		/*HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.HOTEL_MARKUP_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  */
		return SUCCESS;
	}
	
	public String showInsuranceMarkupList(){
		Company sessionObj=(Company)sessionMap.get("Company");
				
				CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
				companyFilter.setLoginCompany(sessionObj);
				companyFilter.setCompanyId(sessionObj.getCompanyid());
				companyFilterPage.setCompanyFilter(companyFilter);
				
				CompanyFilterPage newCompanyFilterPage=insuranceMarkupDao.getFilterInsuranceMarkupList(companyFilterPage);
				if(newCompanyFilterPage!=null && newCompanyFilterPage.getInsuranceMarkupList()!=null){
					companyFilterPage= newCompanyFilterPage;
				} 
				return SUCCESS;
			}
	
	 
	@Override
	public void setSession(Map<String, Object> map) {
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
