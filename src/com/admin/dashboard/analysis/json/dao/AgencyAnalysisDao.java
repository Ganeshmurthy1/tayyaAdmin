package com.admin.dashboard.analysis.json.dao;

import java.util.List;

import com.admin.dashboard.analysis.json.vo.AgencyAnalysisDataVO;

public interface AgencyAnalysisDao {
	public List<AgencyAnalysisDataVO> calculateBookingAmount(List<Integer> companyIds);
}
