/**
 * 
 */
package com.lintas.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.admin.DAO.TrainOrderDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author  info : basha
 * @created date : 15/03/2017
 *
 */
public class TrainReportListAction extends ActionSupport implements SessionAware,ModelDriven<TrainOrderRow> {

	private static final long serialVersionUID = 1L;
	TrainOrderRow trainOrderRow = new TrainOrderRow();
	SessionMap<String , Object> sessionMap;
	TrainOrderDao trainOrderDao=new TrainOrderDao();
	private String showType;
	public List<TrainOrderRow> trainOrderRowList ;
//	CarReportPage carReportPage=new CarReportPage();
	FlightReportPage commonReportPage=new FlightReportPage();
	private ApiProviderDao apiProviderDao =new ApiProviderDao(); 
	
	private List<ApiProvider> apiProviders;
	private String showReportType; 
	private String filterUptoDate; // W,M,W
	
	public String getCompanyTrainReports(){
		
		try {
			User userSessionObj=(User)sessionMap.get("User");
			Company companySessionObj=(Company)sessionMap.get("Company");
			commonReportPage = getCommonReportPage();
			FlightReportFilter flightReportFilter = commonReportPage.getFlightReportFilter();
			flightReportFilter.setLoginCompany(companySessionObj);
			flightReportFilter.setLoginUser(userSessionObj);
			flightReportFilter.setShowtype(showType);
			flightReportFilter.setReportType(commonReportPage.getFlightReportFilter().getReportType());
			
			commonReportPage.setFlightReportFilter(flightReportFilter); 
			
//			CarReportPage carReportPageNew=new CarReportPage();
			commonReportPage=trainOrderDao.getTrainReports(commonReportPage,showType);
			/*setCarReportPage(carReportPageNew);*/
		} catch (Exception e) {
		}
		return SUCCESS;
	}
	
	
public String getCompanyTrainReportsall(){
		
		try {
			User userSessionObj=(User)sessionMap.get("User");
			Company companySessionObj=(Company)sessionMap.get("Company");
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
			commonReportPage = getCommonReportPage();
			FlightReportFilter flightReportFilter = commonReportPage.getFlightReportFilter();
			flightReportFilter.setLoginCompany(companySessionObj);
			flightReportFilter.setLoginUser(userSessionObj);
			flightReportFilter.setShowtype(showType);
			flightReportFilter.setReportType(commonReportPage.getFlightReportFilter().getReportType());
			
			commonReportPage.setFlightReportFilter(flightReportFilter); 
			
//			CarReportPage carReportPageNew=new CarReportPage();
			commonReportPage=trainOrderDao.getTrainReports(commonReportPage,showType);
			/*setCarReportPage(carReportPageNew);*/
		} catch (Exception e) {
		}
		return SUCCESS;
	}

public String getIndividualTrainReports(){
	FlightReportFilter flightReportFilter = commonReportPage.getFlightReportFilter();
	Company companyObj=(Company)sessionMap.get("Company");
	if(companyObj.getCompanyRole().isSuperUser())
		flightReportFilter.setReportType(0);
	else
		flightReportFilter.setReportType(1);
	
	flightReportFilter.setPaymentStatus("ALL");
	flightReportFilter.setSupplierName("");
	getDayWeekMonthBookingDates(filterUptoDate, flightReportFilter);
	if(showReportType.equalsIgnoreCase("BC"))
		flightReportFilter.setBookingStatus("Confirmed");
	if(showReportType.equalsIgnoreCase("PS"))
		flightReportFilter.setPaymentStatus("Success");
	if(showReportType.equalsIgnoreCase("PF"))
		flightReportFilter.setPaymentStatus("Failed");
	
	commonReportPage.setFlightReportFilter(flightReportFilter); 
	
	getCompanyTrainReportsall();
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
	public TrainOrderRow getModel() {
		return trainOrderRow;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public FlightReportPage getCommonReportPage() {
		return commonReportPage;
	}

	public void setCommonReportPage(FlightReportPage commonReportPage) {
		this.commonReportPage = commonReportPage;
	}

	public TrainOrderRow getTrainOrderRow() {
		return trainOrderRow;
	}

	public void setTrainOrderRow(TrainOrderRow trainOrderRow) {
		this.trainOrderRow = trainOrderRow;
	}

	public List<TrainOrderRow> getTrainOrderRowList() {
		return trainOrderRowList;
	}

	public void setTrainOrderRowList(List<TrainOrderRow> trainOrderRowList) {
		this.trainOrderRowList = trainOrderRowList;
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
