package com.admin.dashboard.analysis.json.dao;

import java.util.List;

import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;

public interface HotelAnalysisDao {
	public  List<String> getHotelNameBookingCount(Company company);
	public  List<String> getHotelApiProviderBookingCount(Company company);
	public  List<String> getHotelDestinationBookingCount(Company company);
	public 	List<HotelOrderRow>  getHotelWeeklySales(Company company);
}
