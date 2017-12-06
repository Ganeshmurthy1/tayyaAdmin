package com.lintas.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.FlightWeekandMonthOrderDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FlightWeekandMonthOrderAction extends ActionSupport implements ModelDriven<FlightOrderRow>,SessionAware{
	/**
	 * @author info raham
	 * created date:30-0-2015
	 */
	private static final long serialVersionUID = 1L;
	FlightOrderRow 	flightOrderRow =new FlightOrderRow();
	FlightWeekandMonthOrderDao monthOrderDao =new  FlightWeekandMonthOrderDao();

	SessionMap<String, Object> sessionMap=null;
	private List<ReportData> FlightReportsList =new ArrayList<>(); 

	private String type;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightWeekandMonthOrderAction.class);
	/*this method for showing flight orders report today or weekly or monthly  */
	public String showFlightWeekOrder(){
		logger.info("-------------type----------"+type);

		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		FlightOrderDao flightOrderDao = new FlightOrderDao();
		setFlightReportsList(flightOrderDao.getCompanyFlightReports(userSessionObj, companySessionObj, type));
		return SUCCESS;
	}

	@Override
	public FlightOrderRow getModel() {
		// TODO Auto-generated method stub
		return flightOrderRow;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public List<ReportData> getFlightReportsList() {
		return FlightReportsList;
	}

	public void setFlightReportsList(List<ReportData> flightReportsList) {
		FlightReportsList = flightReportsList;
	}


}
