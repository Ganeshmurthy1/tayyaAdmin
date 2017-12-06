package com.admin.dashboard.analysis.json.dao;

import java.util.List;

import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainOrderRow;

public interface TrainAnalysisDao {
	public  List<String> getTrainApiProviderCount(Company company);
	public  List<String> getTrainDestinationBookingCount(Company company);
	public 	List<TrainOrderRow> getTrainWeeklySales(Company company);
}
