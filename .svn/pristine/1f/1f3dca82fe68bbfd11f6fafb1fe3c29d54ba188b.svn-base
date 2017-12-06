package com.lintas.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.DAO.AirportDao;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Airport;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class AirportFilterAction extends ActionSupport implements ModelDriven<CompanyFilterPage>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirportFilterAction.class);
	private List<Country> countryList;
	private List<Airport> airportsList;
	
	SessionMap<String, Object> sessionmap=null;
	CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	public String GetAirportList()
	{
		User user=(User) sessionmap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=new AirportDao().GetAllAirportList(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getAirportList()!=null) {
			airportsList=new AirportDao().GetAllAirportList();
			 countryList=new CountryDao().getCountryList();
			companyFilterPage= newCompanyFilterPage;
		}
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.SETTINGS_SHOW_ALL_AIRPORTS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		  
		   historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_SHOW_ALL_AIRPORTS.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"show  airport list filter submit click ");
			
		  
		 return SUCCESS;
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










	public List<Country> getCountryList() {
		return countryList;
	}










	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}










	public List<Airport> getAirportsList() {
		return airportsList;
	}










	public void setAirportsList(List<Airport> airportsList) {
		this.airportsList = airportsList;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}

}
