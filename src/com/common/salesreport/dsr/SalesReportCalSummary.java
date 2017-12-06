package com.common.salesreport.dsr;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.common.dsr.CommonDsrTravelReportData;
import com.common.dsr.HotelCityWiseTravelReportData;

public class SalesReportCalSummary {
	private int totalCount;
	private int cancelledCount;
	private  String airlineOrHotel;
	private String  status;
	private BigDecimal totalTktCost;
	private BigDecimal AvgTktCost;
	private BigDecimal totalFare;
	private BigDecimal avgTotalFare;
	private BigDecimal invoiceAmount;
	private BigDecimal cancelledTktAmount;
	private int noofBookedNights;
	private int noofancelledNights;
	//private int noofrooms;
	private String  checkInDate;
	private String checkOutDate;
	private String routing;
	private BigDecimal totalTax;
	private int noofDaysCount;
	private int noofSegmentsCount;
	private BigDecimal avgBaseRoomNightCost;
	private BigDecimal avgTotalRoomNightCost;
	private BigDecimal baseFare;
	private List<HotelCityWiseTravelReportData> hotelCityWiseTravelReportDatas;
	private List<CommonDsrTravelReportData> commonDsrTravelReports;
	private Map<String,SalesReportCalSummary> airAndHotelSummaryMap;
	private List<SalesReportCalSummary> airOrHotelSalesReportList;
	private int noofDaysInAdvance;
	private int noOfRooms;
	private BigDecimal taxes;
	private BigDecimal  refundAmount;
	private BigDecimal  netSale;
	public int getTotalCount() {
		return totalCount;
	}
	public int getCancelledCount() {
		return cancelledCount;
	}
	public BigDecimal getTotalTktCost() {
		return totalTktCost;
	}
	public BigDecimal getAvgTktCost() {
		return AvgTktCost;
	}
	public BigDecimal getTotalFare() {
		return totalFare;
	}
	public BigDecimal getAvgTotalFare() {
		return avgTotalFare;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public void setCancelledCount(int cancelledCount) {
		this.cancelledCount = cancelledCount;
	}
	public void setTotalTktCost(BigDecimal totalTktCost) {
		this.totalTktCost = totalTktCost;
	}
	public void setAvgTktCost(BigDecimal avgTktCost) {
		AvgTktCost = avgTktCost;
	}
	public void setTotalFare(BigDecimal totalFare) {
		this.totalFare = totalFare;
	}
	public void setAvgTotalFare(BigDecimal avgTotalFare) {
		this.avgTotalFare = avgTotalFare;
	}
	public List<CommonDsrTravelReportData> getCommonDsrTravelReports() {
		return commonDsrTravelReports;
	}
	public void setCommonDsrTravelReports(List<CommonDsrTravelReportData> commonDsrTravelReports) {
		this.commonDsrTravelReports = commonDsrTravelReports;
	}
	public Map<String,SalesReportCalSummary> getAirAndHotelSummaryMap() {
		return airAndHotelSummaryMap;
	}
	public void setAirAndHotelSummaryMap(Map<String,SalesReportCalSummary> airAndHotelSummaryMap) {
		this.airAndHotelSummaryMap = airAndHotelSummaryMap;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	 
	public BigDecimal getCancelledTktAmount() {
		return cancelledTktAmount;
	}
	public void setCancelledTktAmount(BigDecimal cancelledTktAmount) {
		this.cancelledTktAmount = cancelledTktAmount;
	}
	 
	public String getAirlineOrHotel() {
		return airlineOrHotel;
	}
	public void setAirlineOrHotel(String airlineOrHotel) {
		this.airlineOrHotel = airlineOrHotel;
	}
	public List<SalesReportCalSummary> getAirOrHotelSalesReportList() {
		return airOrHotelSalesReportList;
	}
	public void setAirOrHotelSalesReportList(List<SalesReportCalSummary> airOrHotelSalesReportList) {
		this.airOrHotelSalesReportList = airOrHotelSalesReportList;
	}
	public int getNoofBookedNights() {
		return noofBookedNights;
	}
	public void setNoofBookedNights(int noofBookedNights) {
		this.noofBookedNights = noofBookedNights;
	}
	public int getNoofancelledNights() {
		return noofancelledNights;
	}
	public void setNoofancelledNights(int noofancelledNights) {
		this.noofancelledNights = noofancelledNights;
	}
	public String getRouting() {
		return routing;
	}
	public void setRouting(String routing) {
		this.routing = routing;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	/*public int getNoofrooms() {
		return noofrooms;
	}*/
	/*public void setNoofrooms(int noofrooms) {
		this.noofrooms = noofrooms;
	}*/
	public BigDecimal getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}
	
	
	public int getNoofSegmentsCount() {
		return noofSegmentsCount;
	}
	public void setNoofSegmentsCount(int noofSegmentsCount) {
		this.noofSegmentsCount = noofSegmentsCount;
	}
	public int getNoofDaysCount() {
		return noofDaysCount;
	}
	public void setNoofDaysCount(int noofDaysCount) {
		this.noofDaysCount = noofDaysCount;
	}
	
	
	public List<HotelCityWiseTravelReportData> getHotelCityWiseTravelReportDatas() {
		return hotelCityWiseTravelReportDatas;
	}
	public void setHotelCityWiseTravelReportDatas(List<HotelCityWiseTravelReportData> hotelCityWiseTravelReportDatas) {
		this.hotelCityWiseTravelReportDatas = hotelCityWiseTravelReportDatas;
	}
	public BigDecimal getAvgBaseRoomNightCost() {
		return avgBaseRoomNightCost;
	}
	public BigDecimal getAvgTotalRoomNightCost() {
		return avgTotalRoomNightCost;
	}
	public void setAvgBaseRoomNightCost(BigDecimal avgBaseRoomNightCost) {
		this.avgBaseRoomNightCost = avgBaseRoomNightCost;
	}
	public void setAvgTotalRoomNightCost(BigDecimal avgTotalRoomNightCost) {
		this.avgTotalRoomNightCost = avgTotalRoomNightCost;
	}
	public BigDecimal getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(BigDecimal baseFare) {
		this.baseFare = baseFare;
	}
	public int getNoofDaysInAdvance() {
		return noofDaysInAdvance;
	}
	public void setNoofDaysInAdvance(int noofDaysInAdvance) {
		this.noofDaysInAdvance = noofDaysInAdvance;
	}
	public int getNoOfRooms() {
		return noOfRooms;
	}
	
	public BigDecimal getTaxes() {
		return taxes;
	}
	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}
	public void setNoOfRooms(int noOfRoomsBooked) {
		this.noOfRooms = noOfRoomsBooked;
		
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public BigDecimal getNetSale() {
		return netSale;
	}
	public void setNetSale(BigDecimal netSale) {
		this.netSale = netSale;
	}
	

}
