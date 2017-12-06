package com.admin.aircity.json.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.aircity.dao.AirCityDaoImpl;
import com.admin.aircity.vo.AirportCityVO;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.lintas.admin.model.Airport;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class AirSearchByCityAction extends ActionSupport  implements SessionAware    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	AirportCityVO airportCityData=new AirportCityVO();
	
	public String  airPortCitySearch(){
		Company sessionCompany = (Company) sessionMap.get("Company");
	if (sessionCompany == null) {
		ErrorMsg error = new ErrorMsg();
		error.setMessage("Session is expired.");
		airportCityData.setError(error);
		return SUCCESS;
	}
	AirCityDaoImpl airCityDao=new AirCityDaoImpl();
	List<Airport> airCityList=airCityDao.getAirportCityList();
	if(airCityList!=null && airCityList.size()>0) 
		 airportCityData.setAirCityList(airCityList);
	else{
		ErrorMsg error = new ErrorMsg();
		error.setMessage("No data.");
		airportCityData.setError(error);
	}
	return SUCCESS;
	}
	public AirportCityVO getAirportCityData() {
		return airportCityData;
	}
	public void setAirportCityData(AirportCityVO airportCityData) {
		this.airportCityData = airportCityData;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	
	
	
	
	
	
	

}
