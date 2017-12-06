package com.admin.flight.fin.sheet.model;

public class HotelandFlightDisReportProperty{
	private String travelType;
private String reportType;
private String fromDate;
private String toDate;
private boolean hotelName;
private boolean orderRef;
private boolean invoiceNo;
private boolean finalPrice;
private boolean checkIn;
private boolean checkOut;
private boolean statusAction;
private boolean paymentStatus;
private boolean guestName;
private boolean agency;
private boolean airline;
private boolean pnr;
 
public boolean isCheckIn() {
	return checkIn;
}

public void setCheckIn(boolean checkIn) {
	this.checkIn = checkIn;
}

public boolean isCheckOut() {
	return checkOut;
}

public void setCheckOut(boolean checkOut) {
	this.checkOut = checkOut;
}

public boolean isStatusAction() {
	return statusAction;
}

public void setStatusAction(boolean statusAction) {
	this.statusAction = statusAction;
}
 
public boolean isFinalPrice() {
	return finalPrice;
}

public void setFinalPrice(boolean finalPrice) {
	this.finalPrice = finalPrice;
}

public boolean isPaymentStatus() {
	return paymentStatus;
}

public void setPaymentStatus(boolean paymentStatus) {
	this.paymentStatus = paymentStatus;
}

public String getReportType() {
	return reportType;
}

public void setReportType(String reportType) {
	this.reportType = reportType;
}

public String getFromDate() {
	return fromDate;
}

public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}

public String getToDate() {
	return toDate;
}

public void setToDate(String toDate) {
	this.toDate = toDate;
} 

public boolean isInvoiceNo() {
	return invoiceNo;
}

public void setInvoiceNo(boolean invoiceNo) {
	this.invoiceNo = invoiceNo;
}

public boolean isOrderRef() {
	return orderRef;
}

public void setOrderRef(boolean orderRef) {
	this.orderRef = orderRef;
}

public boolean isGuestName() {
	return guestName;
}

public void setGuestName(boolean guestName) {
	this.guestName = guestName;
}

public boolean isAgency() {
	return agency;
}

public void setAgency(boolean agency) {
	this.agency = agency;
}

public boolean isHotelName() {
	return hotelName;
}

public void setHotelName(boolean hotelName) {
	this.hotelName = hotelName;
}

public String getTravelType() {
	return travelType;
}

public void setTravelType(String travelType) {
	this.travelType = travelType;
}

public boolean isAirline() {
	return airline;
}

public void setAirline(boolean airline) {
	this.airline = airline;
}

public boolean isPnr() {
	return pnr;
}

public void setPnr(boolean pnr) {
	this.pnr = pnr;
}
}
