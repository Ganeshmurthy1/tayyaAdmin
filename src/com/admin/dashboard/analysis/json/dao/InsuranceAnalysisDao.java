package com.admin.dashboard.analysis.json.dao;

import java.util.List;

import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;

public interface InsuranceAnalysisDao {
	public  List<String> getInsuranceApiProviderCount(Company company);
	/*public  List<String> getInsuranceDestinationBookingCount(Company company);*/
	public 	List<InsuranceOrderRow> getInsuranceWeeklySales(Company company);
}
