package com.admin.dashboard.analysis.json.daoImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import com.admin.dashboard.analysis.json.dao.AllServicesGraphDao;
import com.admin.dashboard.analysis.json.vo.WeeklySalesDataVO;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;

public class AllServicesGraphDaoImpl  implements AllServicesGraphDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AllServicesGraphDaoImpl.class);



	@Override
	public List<WeeklySalesDataVO> getAllservicesWeeklySales(Company company) {
		// TODO Auto-generated method stub
		List<FlightOrderRow> flightList=new AirlineAnalysisDaoImpl().getAirlineWeeklySales(company);
		List<HotelOrderRow> hotelList=new HotelAnalysisDaoImpl().getHotelWeeklySales(company);
		List<CarOrderRow> carList=new CarAnalysisDaoImpl().getCarWeeklySales(company);
		List<BusOrderRow> busList=new BusAnalysisDaoImpl().getBusWeeklySales(company);
		List<TrainOrderRow> trainList=new TrainAnalysisDaoImpl().getTrainWeeklySales(company);
		List<VisaOrderRow> visaList=new VisaAnalysisDaoImpl().getVisaWeeklySales(company);
		List<InsuranceOrderRow> insuranceList=new InsuranceAnalysisDaoImpl().getInsuranceWeeklySales(company);
		List<MiscellaneousOrderRow> miscList=new MiscellaneousAnalysisDaoImpl().getMiscellaneousWeeklySales(company);
		List<WeeklySalesDataVO> weeklySalesDataVOList=getCommonWeeklySalesList(flightList, hotelList, carList, busList, trainList, visaList, insuranceList, miscList);
		return weeklySalesDataVOList;
	}

	public List<WeeklySalesDataVO> getCommonWeeklySalesList(List<FlightOrderRow> flightList,List<HotelOrderRow> hotelList,List<CarOrderRow> carList,List<BusOrderRow> busList,	List<TrainOrderRow> trainList,List<VisaOrderRow> visaList,List<InsuranceOrderRow> insuranceList,List<MiscellaneousOrderRow> miscList) {
		List<WeeklySalesDataVO> weeklySalesDataVOList=new LinkedList<>();
		if(flightList!=null && flightList.size()>0 ){
			for(FlightOrderRow flightOrderRow:flightList){
				WeeklySalesDataVO weeklySalesDataVO=new WeeklySalesDataVO();
				weeklySalesDataVO.setName("Air");
				weeklySalesDataVO.setCreatedAt(flightOrderRow.getCreatedAt());
				weeklySalesDataVO.setBookingAmount(flightOrderRow.getFinalPrice().setScale(2, RoundingMode.UP));//added by basha
				weeklySalesDataVOList.add(weeklySalesDataVO);

			}
		}
		if(hotelList!=null  && hotelList.size()>0){
			for(HotelOrderRow hotelOrderRow:hotelList){
				WeeklySalesDataVO weeklySalesDataVO=new WeeklySalesDataVO();
				weeklySalesDataVO.setName("Hotel");
				weeklySalesDataVO.setCreatedAt(hotelOrderRow.getCreatedAt());
				weeklySalesDataVO.setBookingAmount(hotelOrderRow.getFinalPrice().setScale(2, RoundingMode.UP));//added by basha
				weeklySalesDataVOList.add(weeklySalesDataVO);

			}
		}
		if(carList!=null && carList.size()>0){
			for(CarOrderRow carOrderRow:carList){
				WeeklySalesDataVO weeklySalesDataVO=new WeeklySalesDataVO();
				weeklySalesDataVO.setName("Car");
				weeklySalesDataVO.setCreatedAt(carOrderRow.getCreatedAt());
				weeklySalesDataVO.setBookingAmount(carOrderRow.getTotalAmount().setScale(2, RoundingMode.UP));//added by basha
				weeklySalesDataVOList.add(weeklySalesDataVO);

			}
		}
		if(busList!=null  && busList.size()>0){
			for(BusOrderRow busOrderRow:busList){
				WeeklySalesDataVO weeklySalesDataVO=new WeeklySalesDataVO();
				weeklySalesDataVO.setName("Bus");
				weeklySalesDataVO.setCreatedAt(busOrderRow.getCreatedAt());
				weeklySalesDataVO.setBookingAmount(busOrderRow.getTotalAmount().setScale(2, RoundingMode.UP));//added by basha
				weeklySalesDataVOList.add(weeklySalesDataVO);

			}
		}
		if(trainList!=null  && trainList.size()>0){
			for(TrainOrderRow trainOrderRow:trainList){
				WeeklySalesDataVO weeklySalesDataVO=new WeeklySalesDataVO();
				weeklySalesDataVO.setName("Train");
				weeklySalesDataVO.setCreatedAt(trainOrderRow.getCreatedAt());
				weeklySalesDataVO.setBookingAmount(trainOrderRow.getTotalAmount().setScale(2, RoundingMode.UP));//added by basha
				weeklySalesDataVOList.add(weeklySalesDataVO);

			}
		}
		if(visaList!=null  && visaList.size()>0){
			for(VisaOrderRow visaOrderRow:visaList){
				WeeklySalesDataVO weeklySalesDataVO=new WeeklySalesDataVO();
				weeklySalesDataVO.setName("Visa");
				weeklySalesDataVO.setCreatedAt(visaOrderRow.getCreatedAt());
				weeklySalesDataVO.setBookingAmount(visaOrderRow.getTotalAmount().setScale(2, RoundingMode.UP));//added by basha
				weeklySalesDataVOList.add(weeklySalesDataVO);

			}
		}

		if(insuranceList!=null  && insuranceList.size()>0){
			for(InsuranceOrderRow insuranceOrderRow:insuranceList){
				WeeklySalesDataVO weeklySalesDataVO=new WeeklySalesDataVO();
				weeklySalesDataVO.setName("Insurance");
				weeklySalesDataVO.setCreatedAt(insuranceOrderRow.getCreatedAt());
				weeklySalesDataVO.setBookingAmount(insuranceOrderRow.getTotalAmount().setScale(2, RoundingMode.UP));//added by basha
				weeklySalesDataVOList.add(weeklySalesDataVO);

			}
		}
		if(miscList!=null && miscList.size()>0){
			for(MiscellaneousOrderRow miscOrderRow:miscList){
				WeeklySalesDataVO weeklySalesDataVO=new WeeklySalesDataVO();
				weeklySalesDataVO.setName("Miscellaneous");
				weeklySalesDataVO.setCreatedAt(miscOrderRow.getCreatedAt());
				weeklySalesDataVO.setBookingAmount(miscOrderRow.getTotalAmount().setScale(2, RoundingMode.UP));//added by basha
				weeklySalesDataVOList.add(weeklySalesDataVO);

			}
		}
		return weeklySalesDataVOList;

	}

}
