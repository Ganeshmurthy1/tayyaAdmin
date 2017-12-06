package com.common.dsr;

import java.io.Serializable;
import java.util.List;

import com.admin.common.commonDsrReportConfg.CommonDsrReportConfg;
import com.admin.flight.fin.sheet.model.HotelandFlightDisReportProperty;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.isl.admin.page.Page;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
public class CommonDsrPage extends Page implements Serializable {	
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<ReportData> items;
		private CommonDsrFilters commonDsrFilters;
		private HotelandFlightDisReportProperty hotelandFlightDisReportProperty;
		private List<FlightOrderRow> flightOrderRowList;
		private List<HotelOrderRow> hotelOrderRowList;
		private List<CarOrderRow> carOrderRowList;
		private List<BusOrderRow> busOrderRowList;
		private List<TrainOrderRow> trainOrderRowList;
		private List<VisaOrderRow> visaOrderRowList;
		private List<InsuranceOrderRow> insuranceOrderRowList;
		private List<MiscellaneousOrderRow> miscellaneousOrderRowList;
		private List<CommonDsrTravelReportData> commonDsrTravelReportList;
		private CommonDsrReportConfg commonDsrReportConfg;
		public CommonDsrPage() {
			super();
			this.commonDsrFilters = new CommonDsrFilters();
			this.hotelandFlightDisReportProperty = new HotelandFlightDisReportProperty();
		}
		public CommonDsrPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
				int availablePages, int availableItems, CommonDsrFilters commonDsrFilters) {
			super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
			this.commonDsrFilters = commonDsrFilters;
			// TODO Auto-generated constructor stub
		}
		public CommonDsrPage(boolean isPagination, boolean isFilterEnabled, int maxItems, CommonDsrFilters commonDsrFilters) {
			super(isPagination, isFilterEnabled, maxItems);
			this.commonDsrFilters = commonDsrFilters;
			// TODO Auto-generated constructor stub
		}
		private int selectedRowIndex;
		public int getSelectedRowIndex() {
			return selectedRowIndex;
		}
		public void setSelectedRowIndex(int selectedRowIndex) {
			this.selectedRowIndex = selectedRowIndex;
		}
		 
		public List<ReportData> getItems() {
			return items;
		}
		public void setItems(List<ReportData> items) {
			this.items = items;
		}
		 
		public CommonDsrFilters getCommonDsrFilters() {
			return commonDsrFilters;
		}
		public void setCommonDsrFilters(CommonDsrFilters commonDsrFilters) {
			this.commonDsrFilters = commonDsrFilters;
		}
		public HotelandFlightDisReportProperty getHotelandFlightDisReportProperty() {
			return hotelandFlightDisReportProperty;
		}
		public void setHotelandFlightDisReportProperty(HotelandFlightDisReportProperty hotelandFlightDisReportProperty) {
			this.hotelandFlightDisReportProperty = hotelandFlightDisReportProperty;
		}

		
		public List<FlightOrderRow> getFlightOrderRowList() {
			return flightOrderRowList;
		}
		public List<HotelOrderRow> getHotelOrderRowList() {
			return hotelOrderRowList;
		}
		public List<CarOrderRow> getCarOrderRowList() {
			return carOrderRowList;
		}
		public List<BusOrderRow> getBusOrderRowList() {
			return busOrderRowList;
		}
		public List<TrainOrderRow> getTrainOrderRowList() {
			return trainOrderRowList;
		}
		public List<VisaOrderRow> getVisaOrderRowList() {
			return visaOrderRowList;
		}
		public List<InsuranceOrderRow> getInsuranceOrderRowList() {
			return insuranceOrderRowList;
		}
		public void setFlightOrderRowList(List<FlightOrderRow> flightOrderRowList) {
			this.flightOrderRowList = flightOrderRowList;
		}
		public void setHotelOrderRowList(List<HotelOrderRow> hotelOrderRowList) {
			this.hotelOrderRowList = hotelOrderRowList;
		}
		public void setCarOrderRowList(List<CarOrderRow> carOrderRowList) {
			this.carOrderRowList = carOrderRowList;
		}
		public void setBusOrderRowList(List<BusOrderRow> busOrderRowList) {
			this.busOrderRowList = busOrderRowList;
		}
		public void setTrainOrderRowList(List<TrainOrderRow> trainOrderRowList) {
			this.trainOrderRowList = trainOrderRowList;
		}
		public void setVisaOrderRowList(List<VisaOrderRow> visaOrderRowList) {
			this.visaOrderRowList = visaOrderRowList;
		}
		public void setInsuranceOrderRowList(List<InsuranceOrderRow> insuranceOrderRowList) {
			this.insuranceOrderRowList = insuranceOrderRowList;
		}
		public List<CommonDsrTravelReportData> getCommonDsrTravelReportList() {
			return commonDsrTravelReportList;
		}
		public void setCommonDsrTravelReportList(List<CommonDsrTravelReportData> commonDsrTravelReportList) {
			this.commonDsrTravelReportList = commonDsrTravelReportList;
		}
		public List<MiscellaneousOrderRow> getMiscellaneousOrderRowList() {
			return miscellaneousOrderRowList;
		}
		public void setMiscellaneousOrderRowList(List<MiscellaneousOrderRow> miscellaneousOrderRowList) {
			this.miscellaneousOrderRowList = miscellaneousOrderRowList;
		}
		public CommonDsrReportConfg getCommonDsrReportConfg() {
			return commonDsrReportConfg;
		}
		public void setCommonDsrReportConfg(CommonDsrReportConfg commonDsrReportConfg) {
			this.commonDsrReportConfg = commonDsrReportConfg;
		}
		 
 
}
