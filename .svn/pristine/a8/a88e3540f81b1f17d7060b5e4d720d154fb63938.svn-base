package com.lintas.admin.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.FlightWeekandMonthOrderDao;
import com.lintas.admin.DAO.HotelWeekandMonthOrderDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelReport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class GraphDataJson  extends ActionSupport implements SessionAware{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FlightWeekandMonthOrderDao flightWeekandMonthOrderDao =new  FlightWeekandMonthOrderDao();
	HotelWeekandMonthOrderDao hotelWeekandMonthOrderDao = new HotelWeekandMonthOrderDao();
	CompanyDAO companydao = new CompanyDAO();
	UserDAO userdao = new UserDAO();
	Map jsonobj  =  new HashMap();
	SessionMap<String , Object> sessionMap;


	public String GetGraphDataJson(){	


		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		if(userSessionObj!=null && companySessionObj!=null )
		{
			int FlightWeeklyOrderscount = 0;
			List<FlightOrderRow> flightOrderRows  = flightWeekandMonthOrderDao.getFlightWeekOrders(userSessionObj,companySessionObj);
			if(flightOrderRows!=null && flightOrderRows.size()>0)
				FlightWeeklyOrderscount = flightOrderRows.size();

			int HotelWeeklyOrdercount =0; 
			List<HotelReport> hotelReports=  hotelWeekandMonthOrderDao.getHotelWeekOrders(userSessionObj,companySessionObj);
			if(hotelReports!=null && hotelReports.size()>0)
				HotelWeeklyOrdercount =hotelReports.size();
			int FlightMonthlyOrderscount =0;
			flightOrderRows = flightWeekandMonthOrderDao.getFlightMonthOrders(userSessionObj,companySessionObj);
			if(flightOrderRows!=null && flightOrderRows.size()>0)
				FlightMonthlyOrderscount = flightOrderRows.size();

			int HotelMonthlyOrdercount = 0;
			hotelReports=  hotelWeekandMonthOrderDao.getHotelMonthOrders(userSessionObj,companySessionObj);
			if(hotelReports!=null && hotelReports.size()>0)
				HotelMonthlyOrdercount =hotelReports.size();

			List<Long> FlightDayofweeklistcount = flightWeekandMonthOrderDao.getFlightDayofWeekOrders(userSessionObj,companySessionObj);
			List<Long> HotelDayofweeklistcount = hotelWeekandMonthOrderDao.getHotelDayofWeekOrders(userSessionObj,companySessionObj);
			List<Long> FlightWeekofmonthlistcount = flightWeekandMonthOrderDao.getFlightWeekofMonthOrders(userSessionObj,companySessionObj);
			List<Long> HotelWeekofmonthlistcount = hotelWeekandMonthOrderDao.getHotelWeekofMonthOrders(userSessionObj,companySessionObj);

			List<String> lastsevendays = new ArrayList<String>();
			GregorianCalendar cal = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM d");
			int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
			for(int i=day; i > (day-7); i--){
				cal.set(GregorianCalendar.DAY_OF_MONTH, i);
				Date date = cal.getTime();              
				lastsevendays.add(sdf.format(date));
			}
			Collections.reverse(lastsevendays);

			// logger.info("HotelDayofweeklistcount " +HotelDayofweeklistcount);

			jsonobj.put("FlightWeeklyOrderscount",FlightWeeklyOrderscount);
			jsonobj.put("FlightMonthlyOrderscount",FlightMonthlyOrderscount);
			jsonobj.put("HotelWeeklyOrdercount",HotelWeeklyOrdercount);
			jsonobj.put("HotelMonthlyOrdercount",HotelMonthlyOrdercount);
			jsonobj.put("FlightDayofweeklistcount",FlightDayofweeklistcount);
			jsonobj.put("HotelDayofweeklistcount",HotelDayofweeklistcount);
			jsonobj.put("FlightWeekofmonthlistcount",FlightWeekofmonthlistcount);
			jsonobj.put("HotelWeekofmonthlistcount",HotelWeekofmonthlistcount);
			jsonobj.put("lastsevendays",lastsevendays);
		}
		return SUCCESS;


	}
	public Map getJsonobj() {
		return jsonobj;
	}


	public void setJsonobj(Map jsonobj) {
		this.jsonobj = jsonobj;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;

	}

}
