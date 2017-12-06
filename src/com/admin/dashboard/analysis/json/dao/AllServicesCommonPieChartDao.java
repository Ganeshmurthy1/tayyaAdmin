package com.admin.dashboard.analysis.json.dao;

import java.util.List;

import com.admin.dashboard.Vo.DashBoardJsonVo;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;

public interface AllServicesCommonPieChartDao{
	/**
	 * @author Basha
	 * 28 Aug 2017
	 */
	public DashBoardJsonVo getAllServicesOrdersCount(User userSessionObj, Company companySessionObj, List<String> userIdList, String type,String serviceType);
}
