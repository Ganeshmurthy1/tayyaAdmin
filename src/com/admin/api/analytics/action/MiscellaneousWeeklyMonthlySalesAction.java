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

import com.admin.api.analytics.dao.MiscellaneousWeeklyAndMonthlyDao;
import com.admin.api.analytics.pojo.CommonWeeklyMonthlyJsonData;
import com.admin.api.analytics.pojo.MonthlyAndWeeklyJsonCommonPojo;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class MiscellaneousWeeklyMonthlySalesAction extends ActionSupport implements SessionAware {

	/**
	 * author:saumya date :22-aug-2017 for getting weekly and monthly sales
	 * report
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	CompanyDAO companyDAO = new CompanyDAO();
	public static final Logger logger = Logger.getLogger(MiscellaneousWeeklyMonthlySalesAction.class);
	MiscellaneousWeeklyAndMonthlyDao miscellaneousWeeklyAndMonthlyDao = new MiscellaneousWeeklyAndMonthlyDao();
	Map<String, Integer> weekMap = new HashMap<>();
	Map<String, Integer> monthMap = new HashMap<>();
	CommonWeeklyMonthlyJsonData miscellaneousJsonData = new CommonWeeklyMonthlyJsonData();
	List<MonthlyAndWeeklyJsonCommonPojo> miscellaneousMonthlyandWeeklyDataCountList = new ArrayList<>();

	public String fetchMiscellaneousWeeklySalesJson() {
		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		String reportType = "W";
		int countTotal = 0;
		Long CancellationCount = new Long(0l);
		// Map<String, Integer> newMapToReturn=new HashMap<>();

		if (companySessionObj == null) {
			ErrorMsg error = new ErrorMsg();
			error.setMessage("Session is expired.");
			miscellaneousJsonData.setError(error);
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
					weekMap = miscellaneousWeeklyAndMonthlyDao.getMiscellaneousWeeklyOrMonthlyReport(userSessionObj,
							companySessionObj, userIdList, reportType);
					CancellationCount = miscellaneousWeeklyAndMonthlyDao.getMiscellaneousWeeklyOrMonthlyCancellationCount(
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
						miscellaneousMonthlyandWeeklyDataCountList.add(monthlyAndWeeklyJsonCommonPojo);
					}

					if (weekMap != null && weekMap.size() > 0) {
						Set<Entry<String, Integer>> set = weekMap.entrySet();
						for (Entry<String, Integer> entry : set) {
							for (MonthlyAndWeeklyJsonCommonPojo miscellaneous : miscellaneousMonthlyandWeeklyDataCountList) {
								if (miscellaneous.getDate().trim().equalsIgnoreCase(entry.getKey().trim())) {
									miscellaneous.setCount(entry.getValue());
									countTotal = countTotal + entry.getValue();
								}

							}
							miscellaneousJsonData.setMonthlyWeeklyCommondata(miscellaneousMonthlyandWeeklyDataCountList);
							miscellaneousJsonData.setTotalCount(countTotal);
							miscellaneousJsonData.setCancellationCount(CancellationCount.intValue());
						}
					}

				}
			} catch (Exception e) {
				logger.error(e);
				ErrorMsg error = new ErrorMsg();
				error.setMessage("some exception came");
				miscellaneousJsonData.setError(error);
			}

			return SUCCESS;
		}
	}

	public String fetchMiscellaneousMonthlySalesJson() {
		User userSessionObj = (User) sessionMap.get("User");
		Company companySessionObj = (Company) sessionMap.get("Company");
		String reportType = "M";
		if (companySessionObj == null) {
			ErrorMsg error = new ErrorMsg();
			error.setMessage("Session is expired.");
			miscellaneousJsonData.setError(error);
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
					monthMap = miscellaneousWeeklyAndMonthlyDao.getMiscellaneousWeeklyOrMonthlyReport(userSessionObj,
							companySessionObj, userIdList, reportType);
					if (monthMap != null && monthMap.size() > 0) {
						Set<Entry<String, Integer>> set = monthMap.entrySet();
						for (Entry<String, Integer> entry : set) {
							MonthlyAndWeeklyJsonCommonPojo monthlyAndWeeklyJsonCommonPojo = new MonthlyAndWeeklyJsonCommonPojo();
							monthlyAndWeeklyJsonCommonPojo.setDate(entry.getKey());
							monthlyAndWeeklyJsonCommonPojo.setCount(entry.getValue());
							miscellaneousMonthlyandWeeklyDataCountList.add(monthlyAndWeeklyJsonCommonPojo);
							miscellaneousJsonData.setMonthlyWeeklyCommondata(miscellaneousMonthlyandWeeklyDataCountList);
						}
					}
				}
			} catch (Exception e) {
				logger.error(e);
				ErrorMsg error = new ErrorMsg();
				error.setMessage("some exception came");
				miscellaneousJsonData.setError(error);
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

	public CommonWeeklyMonthlyJsonData getMiscellaneousJsonData() {
		return miscellaneousJsonData;
	}

	public void setMiscellaneousJsonData(CommonWeeklyMonthlyJsonData miscellaneousJsonData) {
		this.miscellaneousJsonData = miscellaneousJsonData;
	}

}