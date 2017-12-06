/**
 * 
 */
package com.admin.api.analytics.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.analytics.dao.FlightAnalysisDao;
import com.admin.api.analytics.pojo.FlightPojo;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author MANISH
 *
 */
public class OrderRowJson extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;

	public static final Logger logger = Logger.getLogger(OrderRowJson.class);
	CompanyDAO companyDAO = new CompanyDAO();
	SessionMap<String, Object> sessionMap;
	FlightAnalysisDao analysisDao=new FlightAnalysisDao();
	String type="topfive";
	String responseStatus;
	List<FlightPojo> commonTopFivePojoList=new ArrayList<FlightPojo>();
	
	
	public String fetchFlightBookingList(){

		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						commonTopFivePojoList = analysisDao.getFlightOrdersList(userSessionObj, companySessionObj, userIdList, type);	
						
					}
					
					setResponseStatus("success");
				} catch (Exception e) {
					logger.error(e);
					setResponseStatus("error-exception");
				}
			} else {
				setResponseStatus("user-not-login, session-invalid");
			}
		} catch (Exception e) {
			logger.error(e);
			setResponseStatus("error-exception");
		}
		return SUCCESS;
	
	}
	
	public List<FlightPojo> getCommonTopFivePojoList() {
		return commonTopFivePojoList;
	}

	public void setCommonTopFivePojoList(List<FlightPojo> commonTopFivePojoList) {
		this.commonTopFivePojoList = commonTopFivePojoList;
	}

	public String fetchHotelBookingList(){

		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						commonTopFivePojoList = analysisDao.getHotelOrdersList(userSessionObj, companySessionObj, userIdList, type);	
						
					}
					
					setResponseStatus("success");
				} catch (Exception e) {
					logger.error(e);
					setResponseStatus("error-exception");
				}
			} else {
				setResponseStatus("user-not-login, session-invalid");
			}
		} catch (Exception e) {
			logger.error(e);
			setResponseStatus("error-exception");
		}
		return SUCCESS;
	
	}
	
	public String fetchCarBookingList(){

		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						commonTopFivePojoList = analysisDao.getCarOrdersList(userSessionObj, companySessionObj, userIdList, type);	
						
					}
					
					setResponseStatus("success");
				} catch (Exception e) {
					logger.error(e);
					setResponseStatus("error-exception");
				}
			} else {
				setResponseStatus("user-not-login, session-invalid");
			}
		} catch (Exception e) {
			logger.error(e);
			setResponseStatus("error-exception");
		}
		return SUCCESS;
	
	}
	
	public String fetchVisaBookingList(){

		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						commonTopFivePojoList = analysisDao.getVisaOrdersList(userSessionObj, companySessionObj, userIdList, type);	
						
					}
					
					setResponseStatus("success");
				} catch (Exception e) {
					logger.error(e);
					setResponseStatus("error-exception");
				}
			} else {
				setResponseStatus("user-not-login, session-invalid");
			}
		} catch (Exception e) {
			logger.error(e);
			setResponseStatus("error-exception");
		}
		return SUCCESS;
	
	}
	
	public String fetchTrainBookingList(){

		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						commonTopFivePojoList = analysisDao.getTrainOrdersList(userSessionObj, companySessionObj, userIdList, type);	
						
					}
					
					setResponseStatus("success");
				} catch (Exception e) {
					logger.error(e);
					setResponseStatus("error-exception");
				}
			} else {
				setResponseStatus("user-not-login, session-invalid");
			}
		} catch (Exception e) {
			logger.error(e);
			setResponseStatus("error-exception");
		}
		return SUCCESS;
	
	}
	
	public String fetchBusBookingList(){

		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						commonTopFivePojoList = analysisDao.getBusOrdersList(userSessionObj, companySessionObj, userIdList, type);	
						
					}
					
					setResponseStatus("success");
				} catch (Exception e) {
					logger.error(e);
					setResponseStatus("error-exception");
				}
			} else {
				setResponseStatus("user-not-login, session-invalid");
			}
		} catch (Exception e) {
			logger.error(e);
			setResponseStatus("error-exception");
		}
		return SUCCESS;
	
	}
	
	public String fetchInsuranceBookingList(){

		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						commonTopFivePojoList = analysisDao.getInsuranceOrdersList(userSessionObj, companySessionObj, userIdList, type);	
						
					}
					
					setResponseStatus("success");
				} catch (Exception e) {
					logger.error(e);
					setResponseStatus("error-exception");
				}
			} else {
				setResponseStatus("user-not-login, session-invalid");
			}
		} catch (Exception e) {
			logger.error(e);
			setResponseStatus("error-exception");
		}
		return SUCCESS;
	
	}
	
	public String fetchMiscellaneousBookingList(){

		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		try {

			if (userSessionObj != null && companySessionObj != null) {
				List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
				List<String> userIdList= new ArrayList<>(); 
				if(userList!=null && userList.size()>0)
				{
					for(User user : userList)
					{
						userIdList.add(String.valueOf(user.getId()));
					}
				}

				try {
					if(type!=null && !type.equalsIgnoreCase(""))
					{
						commonTopFivePojoList = analysisDao.getMiscellaneousOrdersList(userSessionObj, companySessionObj, userIdList, type);	
						
					}
					
					setResponseStatus("success");
				} catch (Exception e) {
					logger.error(e);
					setResponseStatus("error-exception");
				}
			} else {
				setResponseStatus("user-not-login, session-invalid");
			}
		} catch (Exception e) {
			logger.error(e);
			setResponseStatus("error-exception");
		}
		return SUCCESS;
	
	}
	
	
	
	private List<User> getUserIdListForAllCompanyList(User userSessionObj, Company companySessionObj) {
		List<User> userIdBuffer = new ArrayList<>();
		if (userSessionObj != null && companySessionObj != null) {
			if (userSessionObj.getUserrole_id().isSuperuser()) {
				userIdBuffer = companyDAO.GetAllUserListUnderCompanyAsList(userSessionObj, companySessionObj);
			} else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
				if (!companySessionObj.getCompanyRole().isCorporate()) {
					userIdBuffer = companyDAO.GetAllUserListUnderCompanyAsList(userSessionObj, companySessionObj);
				}
			}
		}
		return userIdBuffer;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	 

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
}
