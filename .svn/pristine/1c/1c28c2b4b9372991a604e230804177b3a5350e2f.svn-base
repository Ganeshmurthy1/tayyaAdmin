package com.lintas.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.model.Airlinelist;
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

public class FlightReportListAction extends ActionSupport implements SessionAware,ModelDriven<FlightReportPage>{
	/**
	 * @author info raham
	 * created date : 31st Aug 2015
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightReportAction.class);
	FlightReportPage flightReportPage = new   FlightReportPage();
	FlightOrderDao  flightOrderDao=new FlightOrderDao();
	SessionMap<String , Object> sessionMap;
	private List<Airlinelist> airlineList ;
	CountryDao cDAO = new CountryDao();
	private String type = "none";
	private String showType = null;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	private String showReportType; 
	private String filterUptoDate; // W,M,W

	public String getCompanyFlightReports(){
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		flightReportPage = getFlightReportPage();
		FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
		flightReportFilter.setLoginCompany(companySessionObj);
		flightReportFilter.setLoginUser(userSessionObj);
		flightReportFilter.setShowtype(showType);
		flightReportFilter.setReportType(flightReportPage.getFlightReportFilter().getReportType());
		if(showType != null){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			
		}
		
		actionId=BrowsingOptionActionEnum.FLIGHT_REPORTS.getId();
		detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();	
		/*flightReportFilter.setReportType(0);*/
		flightReportPage.setFlightReportFilter(flightReportFilter); 
		flightReportPage =flightOrderDao.getCompanyFlightReports(flightReportPage,showType,companySessionObj);
		airlineList = cDAO.getAirlineList();
		type = "none";
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		historyInfo.changeNature(BrowsingOptionPageEnum.FLIGHT_BOOKINGS_REPORT_LIST, BrowsingOptionActionEnum.FLIGHT_REPORTS, ActionStatusEnum.SUCCESS); 
		new HistoryManager().inSertHistory(historyInfo);
		try{
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
		}catch (Exception e) {
		}
		return SUCCESS;
	} 
	
	public String getIndividualFlightReports(){
		Company companyObj=(Company)sessionMap.get("Company");
			
		FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
		flightReportFilter.setAirlineName("ALL");
		flightReportFilter.setPaymentStatus("ALL");
		flightReportFilter.setSupplierName("ALL");
		
		if(companyObj.getCompanyRole().isSuperUser())
			flightReportFilter.setReportType(0);
		else
			flightReportFilter.setReportType(1);
		
		getDayWeekMonthBookingDates(filterUptoDate, flightReportFilter);
		if(showReportType.equalsIgnoreCase("BC"))
			flightReportFilter.setBookingStatus("Confirmed");
		if(showReportType.equalsIgnoreCase("PS"))
			flightReportFilter.setPaymentStatus("Success");
		if(showReportType.equalsIgnoreCase("PF"))
			flightReportFilter.setPaymentStatus("Failed");
		
		flightReportPage.setFlightReportFilter(flightReportFilter);
		
		getCompanyFlightReports();
		return SUCCESS;
	}
	
	public FlightReportFilter getDayWeekMonthBookingDates(String type,FlightReportFilter flightReportFilter){
		String startDate = "";
		String weekDate = "";
		String monthDate = "";
		try {
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Date start = c.getTime();
			c.add(Calendar.DATE, -6);
			Date end = c.getTime();
			c.add(Calendar.DATE, -24);
			Date monthend = c.getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			startDate = sdf.format(start);
			weekDate = sdf.format(end);
			monthDate = sdf.format(monthend);
		} catch (Exception e) {
		}
		if (type.equalsIgnoreCase("D")) {
			flightReportFilter.getDateFilterBooking().setDtStart(startDate);
			flightReportFilter.getDateFilterBooking().setDtEnd(startDate);
		} else if (type.equalsIgnoreCase("W")) {
			flightReportFilter.getDateFilterBooking().setDtStart(weekDate);
			flightReportFilter.getDateFilterBooking().setDtEnd(startDate);
		} else if (type.equalsIgnoreCase("M")) {
			flightReportFilter.getDateFilterBooking().setDtStart(monthDate);
			flightReportFilter.getDateFilterBooking().setDtEnd(startDate);
		} 
		return flightReportFilter;
	}

	@Override
	public FlightReportPage getModel() {
		// TODO Auto-generated method stub
		return flightReportPage;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public FlightReportPage getFlightReportPage() {

		return flightReportPage;
	}

	public void setFlightReportPage(FlightReportPage flightReportPage) {
		this.flightReportPage = flightReportPage;
	}
	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}
	public List<Airlinelist> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(List<Airlinelist> airlineList) {
		this.airlineList = airlineList;
	}

	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}

	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}

	public String getShowReportType() {
		return showReportType;
	}

	public void setShowReportType(String showReportType) {
		this.showReportType = showReportType;
	}

	public String getFilterUptoDate() {
		return filterUptoDate;
	}

	public void setFilterUptoDate(String filterUptoDate) {
		this.filterUptoDate = filterUptoDate;
	}

}
