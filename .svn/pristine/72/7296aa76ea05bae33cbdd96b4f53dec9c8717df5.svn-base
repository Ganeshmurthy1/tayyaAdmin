package com.common.dsr;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommonDSRReportAction  extends ActionSupport implements ModelDriven<CommonDsrPage>,SessionAware{

	/**
	 * @raham
	 * Date:16-02-2017
	 */
	private static final long serialVersionUID = 1L;
	private CommonDsrPage commonDsrPage=new CommonDsrPage();
	SessionMap<String , Object> sessionMap;
	private List<Airlinelist> airlineList;
	private List<Country> countryList;
	CountryDao countryDao=new CountryDao();
	
	public String goCommonDsrFilterPage(){
		setCommonDsrPage(commonDsrPage);
		setAirlineList(countryDao.getAirlineList());
		 setCountryList(countryDao.getCountryList());
		return  SUCCESS;
	}

	public String goFilterDisViewPage(){
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		
		commonDsrPage = getCommonDsrPage();
		CommonDsrFilters commonDsrFilters = commonDsrPage.getCommonDsrFilters();
		commonDsrFilters.setLoginCompany(companySessionObj);
		commonDsrFilters.setLoginUser(userSessionObj);
		commonDsrFilters.setReportType(0);
		commonDsrPage.setCommonDsrFilters(commonDsrFilters);  
		if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("F")){
			commonDsrPage =new CommonDsrReportDao().getCompanyFlightReports(commonDsrPage);
			 
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("H")){
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("ALL")){
		} 
		return  SUCCESS;
	}
 
 



@Override
public CommonDsrPage getModel() {
	// TODO Auto-generated method stub
	return commonDsrPage;
}

public CommonDsrPage getCommonDsrPage() {
	return commonDsrPage;
}

public void setCommonDsrPage(CommonDsrPage commonDsrPage) {
	this.commonDsrPage = commonDsrPage;
}

@Override
public void setSession(Map<String, Object> map) {
	// TODO Auto-generated method stub
	sessionMap=(SessionMap<String, Object>) map;
}

public List<Airlinelist> getAirlineList() {
	return airlineList;
}

public void setAirlineList(List<Airlinelist> airlineList) {
	this.airlineList = airlineList;
}

public List<Country> getCountryList() {
	return countryList;
}

public void setCountryList(List<Country> countryList) {
	this.countryList = countryList;
}

 


}
