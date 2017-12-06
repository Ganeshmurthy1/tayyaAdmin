package com.admin.ageing.report.dao;

import java.util.List;

import com.admin.ageing.report.dto.OrderRowDTO;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.CommonDsrPage;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;

public interface AgeingReportDao {
	public List<OrderRowDTO> loadFlightUnKnockOffReports(CommonDsrPage commonDsrPage);
	public List<HotelOrderRow> loadHotelUnKnockOffReports(CommonDsrPage commonDsrPage);
	public List<CarOrderRow>  loadCarUnKnockOffReports(CommonDsrPage commonDsrPage);
	public List<BusOrderRow>  loadBusUnKnockOffReports(CommonDsrPage commonDsrPage);
	public List<TrainOrderRow> loadTrainUnKnockOffReports(CommonDsrPage commonDsrPage);
	public List<VisaOrderRow> loadVisaUnKnockOffReports(CommonDsrPage commonDsrPage);
	public List<InsuranceOrderRow> loadInsuranceUnKnockOffReports(CommonDsrPage commonDsrPage);
	public List<MiscellaneousOrderRow> loadMiscellaneousUnKnockOffReports(CommonDsrPage commonDsrPage);

}
