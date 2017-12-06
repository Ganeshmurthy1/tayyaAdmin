package com.lintas.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.HotelWeekandMonthOrderDao;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.hotel.model.HotelReport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HotelWeekandMonthOrderAction extends ActionSupport implements ModelDriven<HotelOrderRow>,SessionAware{

	/**@author info raham
	 * created date:04-10-2015
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelWeekandMonthOrderAction.class);
	HotelOrderRow hotelOrderRow =new HotelOrderRow();
	HotelWeekandMonthOrderDao monthOrderDao =new  HotelWeekandMonthOrderDao();
	SessionMap<String, Object> sessionMap=null;
	private String type;
	private List<HotelReport> HotelReportsList;
	/*this method for showing hotel weeek orders ,monthly orders and today oredres*/
	public String showHotelWeekOrder(){
		logger.info("type........."+type); 

		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		HotelOrderDao hotelOrderDao = new HotelOrderDao();
		HotelReportsList = hotelOrderDao.getCompanyHotelReports(userSessionObj, companySessionObj, type);
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public HotelOrderRow getModel() {
		// TODO Auto-generated method stub
		return hotelOrderRow;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public List<HotelReport> getHotelReportsList() {
		return HotelReportsList;
	}

	public void setHotelReportsList(List<HotelReport> hotelReportsList) {
		HotelReportsList = hotelReportsList;
	}
}
