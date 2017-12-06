package com.admin.api.analytics.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.analytics.dao.CarWeeklyAndMonthlyDao;
import com.admin.api.analytics.pojo.CommonWeeklyMonthlyJsonData;
import com.admin.api.analytics.pojo.MonthlyAndWeeklyJsonCommonPojo;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class CarWeeklyMonthlySalesAction extends ActionSupport implements SessionAware {

	/**
	 * author:saumya date :22-aug-2017 for getting weekly and monthly sales
	 * report
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	CompanyDAO companyDAO = new CompanyDAO();
	public static final Logger logger = Logger.getLogger(CarWeeklyMonthlySalesAction.class);
	CarWeeklyAndMonthlyDao carWeeklyAndMonthlyDao = new CarWeeklyAndMonthlyDao();
	Map<String, Integer> weekMap = new HashMap<>();
	Map<String, Integer> monthMap = new HashMap<>();
	CommonWeeklyMonthlyJsonData carJsonData = new CommonWeeklyMonthlyJsonData();
	List<MonthlyAndWeeklyJsonCommonPojo> carMonthlyandWeeklyDataCountList = new ArrayList<>();

	public String fetchCarWeeklySalesJson() {
		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		String reportType = "W";
		int countTotal = 0;
		Long CancellationCount = new Long(0l);
		// Map<String, Integer> newMapToReturn=new HashMap<>();

		if (companySessionObj == null) {
			ErrorMsg error = new ErrorMsg();
			error.setMessage("Session is expired.");
			carJsonData.setError(error);
			return SUCCESS;
		} else {

			try {
				if (userSessionObj != null && companySessionObj != null) {
					List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
					List<String> userIdList = new ArrayList<>();
					if (userList != null && userList.size() > 0) {
						for (User user : userList) {
							userIdList.add(String.valueOf(user.getId()));
						}
					}
					weekMap = carWeeklyAndMonthlyDao.getCarWeeklyOrMonthlyReport(userSessionObj,
							companySessionObj, userIdList, reportType);
					CancellationCount = carWeeklyAndMonthlyDao.getCarWeeklyOrMonthlyCancellationCount(
							userSessionObj, companySessionObj, userIdList, reportType);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DAY_OF_YEAR, -7);
					for (int i = 0; i < 7; i++) {
						cal.add(Calendar.DAY_OF_YEAR, 1);
					}

					SimpleDateFormat sdfn = new SimpleDateFormat("yyyy-MM-dd ");
					Calendar caln = Calendar.getInstance();
					caln.add(Calendar.DAY_OF_YEAR, -7);
					for (int j = 0; j < 7; j++) {
						caln.add(Calendar.DAY_OF_YEAR, 1);
						MonthlyAndWeeklyJsonCommonPojo monthlyAndWeeklyJsonCommonPojo = new MonthlyAndWeeklyJsonCommonPojo();
						monthlyAndWeeklyJsonCommonPojo.setDate(sdf.format(caln.getTime()));
						monthlyAndWeeklyJsonCommonPojo.setCount(0);
						carMonthlyandWeeklyDataCountList.add(monthlyAndWeeklyJsonCommonPojo);
					}

					if (weekMap != null && weekMap.size() > 0) {
						Set<Entry<String, Integer>> set = weekMap.entrySet();
						for (Entry<String, Integer> entry : set) {
							for (MonthlyAndWeeklyJsonCommonPojo car : carMonthlyandWeeklyDataCountList) {
								if (car.getDate().trim().equalsIgnoreCase(entry.getKey().trim())) {
									car.setCount(entry.getValue());
									countTotal = countTotal + entry.getValue();
								}

							}
							carJsonData.setMonthlyWeeklyCommondata(carMonthlyandWeeklyDataCountList);
							carJsonData.setTotalCount(countTotal);
							carJsonData.setCancellationCount(CancellationCount.intValue());
						}
					}

				}
			} catch (Exception e) {
				logger.error(e);
				ErrorMsg error = new ErrorMsg();
				error.setMessage("some exception came");
				carJsonData.setError(error);
			}

			return SUCCESS;
		}
	}

	public String fetchCarMonthlySalesJson() {
		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		String reportType = "M";
		if (companySessionObj == null) {
			ErrorMsg error = new ErrorMsg();
			error.setMessage("Session is expired.");
			carJsonData.setError(error);
			return SUCCESS;
		} else {

			try {
				if (userSessionObj != null && companySessionObj != null) {
					List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
					List<String> userIdList = new ArrayList<>();
					if (userList != null && userList.size() > 0) {
						for (User user : userList) {
							userIdList.add(String.valueOf(user.getId()));
						}
					}
					monthMap = carWeeklyAndMonthlyDao.getCarWeeklyOrMonthlyReport(userSessionObj,
							companySessionObj, userIdList, reportType);
					if (monthMap != null && monthMap.size() > 0) {
						Set<Entry<String, Integer>> set = monthMap.entrySet();
						for (Entry<String, Integer> entry : set) {
							MonthlyAndWeeklyJsonCommonPojo monthlyAndWeeklyJsonCommonPojo = new MonthlyAndWeeklyJsonCommonPojo();
							monthlyAndWeeklyJsonCommonPojo.setDate(entry.getKey());
							monthlyAndWeeklyJsonCommonPojo.setCount(entry.getValue());
							carMonthlyandWeeklyDataCountList.add(monthlyAndWeeklyJsonCommonPojo);
							carJsonData.setMonthlyWeeklyCommondata(carMonthlyandWeeklyDataCountList);
						}
					}
				}
			} catch (Exception e) {
				logger.error(e);
				ErrorMsg error = new ErrorMsg();
				error.setMessage("some exception came");
				carJsonData.setError(error);
			}

			return SUCCESS;
		}
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;

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

	public CommonWeeklyMonthlyJsonData getCarJsonData() {
		return carJsonData;
	}

	public void setCarJsonData(CommonWeeklyMonthlyJsonData carJsonData) {
		this.carJsonData = carJsonData;
	}

}
