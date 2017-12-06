package com.admin.knockoff.common.filter.page;

import java.io.Serializable;
import java.util.List;

import com.admin.knockoff.vo.KnockOffVO;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.isl.admin.page.Page;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;

public class KnockOffPage extends Page implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int selectedRowIndex;
	private Long id;
	private String orderId;
	private String invoiceNo;
	private List<FlightOrderRow> flightRows;
	private List<HotelOrderRow> hotelRows;
	private List<CarOrderRow> carRows;
	private List<BusOrderRow> busRows;
	private List<TrainOrderRow> trainRows;
	private List<VisaOrderRow> visaRows;
	private List<InsuranceOrderRow> insuranceRows;
	private List<MiscellaneousOrderRow> miscellaneousRows;
	private  List<KnockOffVO> allKnockOffVOReports;
	private  KnockOffFilter knockOffFilter;
	public KnockOffPage(KnockOffFilter knockOffFilter) {
		super();
		this.knockOffFilter = knockOffFilter;
		// TODO Auto-generated constructor stub
	}
	public KnockOffPage() {
		super();
		this.knockOffFilter = new KnockOffFilter();
		 // TODO Auto-generated constructor stub
	}

	public KnockOffPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, KnockOffFilter knockOffFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.setKnockOffFilter(knockOffFilter);
	}
	public KnockOffPage(boolean isPagination, boolean isFilterEnabled, int maxItems, KnockOffFilter knockOffFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.setKnockOffFilter(knockOffFilter);
		// TODO Auto-generated constructor stub
	}
	public List<HotelOrderRow> getHotelRows() {
		return hotelRows;
	}
	public List<CarOrderRow> getCarRows() {
		return carRows;
	}
	public List<BusOrderRow> getBusRows() {
		return busRows;
	}
	public List<TrainOrderRow> getTrainRows() {
		return trainRows;
	}
	public List<VisaOrderRow> getVisaRows() {
		return visaRows;
	}
	public List<InsuranceOrderRow> getInsuranceRows() {
		return insuranceRows;
	}
	public void setHotelRows(List<HotelOrderRow> hotelRows) {
		this.hotelRows = hotelRows;
	}
	public void setCarRows(List<CarOrderRow> carRows) {
		this.carRows = carRows;
	}
	public void setBusRows(List<BusOrderRow> busRows) {
		this.busRows = busRows;
	}
	public void setTrainRows(List<TrainOrderRow> trainRows) {
		this.trainRows = trainRows;
	}
	public void setVisaRows(List<VisaOrderRow> visaRows) {
		this.visaRows = visaRows;
	}
	public void setInsuranceRows(List<InsuranceOrderRow> insuranceRows) {
		this.insuranceRows = insuranceRows;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}

	public KnockOffFilter getKnockOffFilter() {
		return knockOffFilter;
	}

	public void setKnockOffFilter(KnockOffFilter knockOffFilter) {
		this.knockOffFilter = knockOffFilter;
	}

	public List<FlightOrderRow> getFlightRows() {
		return flightRows;
	}

	public void setFlightRows(List<FlightOrderRow> flightRows) {
		this.flightRows = flightRows;
	}
	public List<MiscellaneousOrderRow> getMiscellaneousRows() {
		return miscellaneousRows;
	}
	public void setMiscellaneousRows(List<MiscellaneousOrderRow> miscellaneousRows) {
		this.miscellaneousRows = miscellaneousRows;
	}
	public List<KnockOffVO> getAllKnockOffVOReports() {
		return allKnockOffVOReports;
	}
	public void setAllKnockOffVOReports(List<KnockOffVO> allKnockOffVOReports) {
		this.allKnockOffVOReports = allKnockOffVOReports;
	}
}
