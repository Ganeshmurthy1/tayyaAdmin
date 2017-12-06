package com.common.dsr;

import java.util.Map;

public class CommonDsrTravelReportData {
	private String pincode;
	private String clientCode;
	private String state;	
	private String country;
	private String phone;
	private String rating;
	private String address;
	private String city;
	private String bkgRef;
	private String systemInvoiceId;
	private String bookingType;
	private String amendmentType;
	private String invoicedate;
	private String bookingDate;
	private String corporateName;
	private String billingEntity;
	private String bookerName;
	private String bookersLoginId;
	private String supplierCode;
	private String supplierName;
	private String supplierCharge;
	private String productType;
	private String itineraryType;
	private String productName;
	private String productCode;
	private String description;
	private String description2;
	private String remarks;
	private String airlinePNRorProvBooking;
	private String GDSPNR;
	private String ticketNumorFinalBooking;
	private String fareType;
	private String bookingRefundType;
	private String fareBasis;
	private String cabinClass;
	private String bookingClass;
	private String paxType;
	private String traveller;
	private String totalNights;//for flight 0
	private String tripStartsDate;//for hotel checkin and flight departure date
	private String tripEndDate;//for hotel checkOut and flight  Arrival date
	private String journeyType;
	private String baseFare;
	private String YQTax;
	private String YRTax;
	private String PSFTax;
	private String UDFTax;
	private String JNTax;
	private String INTax;
	private String OBTax;
	private String OCTax;
	private String WOTax;
	private String K3Tax;
	private String OtherTaxes;
	private String extraKmCharge;
	private String driverAllowancedayCharge;
	private String driverAllowanceNightCharge;
	private String tollorParkingCharge;
	private String extraHourCharge;
	private String countryId;
	private String extraCharge;
	private String supplierAmendmentOrCancellationFee;
	private String grossFare;
	private String swachBharatCess;
	private String krishiKalyanCess;
	private String serviceTaxBase;
	private String convenienceCharge;
	private String discount;
	private String netFare;
	private String markup;
	private String modeOfPayment;
	private String travelStatus;
	private String serviceTax;
	private String totGstTax;
	private String taxType;
	private String personalBooking;
	private String corporateCurrency;
	private String tayyarahServiceCharges;
	private String courierCharges;
	private String vfsCharges;
	/*RM  Config Details*/
	private String approverName;
	private String billNonBill;
	private String businessUnit;
	private String costCenter;
	private String department;
	private String empCode;
	private String location;
	private String projectCode;
	private String reasonForTravel;
	private String travelRequestNumber;
	private String extraRmConfigDetails;
	private String travelerEmail;
	private String travelerPhone;
	private String segmentNumber;
	private String flightNumber;
	private Map<String,String> rmConfigMap;
	private String depTime;
	private String arrTime;
	private String netDiscount;
	private boolean  creditnoteIssued;
	private String bookingTime;
	private int  guestCount;
	private String  gstOnTravels;
	private String  CGST;
	private String  SGSTorUGSTorIGST;
	private  String route;
	private  String bookingReference;
	private  String hotelName;
	private  String noOfRooms;
	private  String guestName;
	private  String status;
	private  String totalInvoiceAmount;
	private  String basePrice;
	private  String checkInDate;
	private  String checkOutDate;
	private  String noOfNights;
	private  String orderId;
	private  String bandCode;
	private  String AirlinePnr;
	private  String mobile;
	private  String outstandingAmount;
	private boolean  knockOff;
	private int  companyId;
	public String getCGST() {
		return CGST;
	}
	public String getSGSTorUGSTorIGST() {
		return SGSTorUGSTorIGST;
	}
	public void setCGST(String cGST) {
		CGST = cGST;
	}
	public void setSGSTorUGSTorIGST(String sGSTorUGSTorIGST) {
		SGSTorUGSTorIGST = sGSTorUGSTorIGST;
	}
	public String getApproverName() {
		return approverName;
	}
	public String getBillNonBill() {
		return billNonBill;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public String getDepartment() {
		return department;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public void setBillNonBill(String billNonBill) {
		this.billNonBill = billNonBill;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getExtraKmCharge() {
		return extraKmCharge;
	}
	public String getDriverAllowancedayCharge() {
		return driverAllowancedayCharge;
	}
	public String getDriverAllowanceNightCharge() {
		return driverAllowanceNightCharge;
	}
	public String getTollorParkingCharge() {
		return tollorParkingCharge;
	}
	public void setExtraKmCharge(String extraKmCharge) {
		this.extraKmCharge = extraKmCharge;
	}
	public void setDriverAllowancedayCharge(String driverAllowancedayCharge) {
		this.driverAllowancedayCharge = driverAllowancedayCharge;
	}
	public void setDriverAllowanceNightCharge(String driverAllowanceNightCharge) {
		this.driverAllowanceNightCharge = driverAllowanceNightCharge;
	}
	public void setTollorParkingCharge(String tollorParkingCharge) {
		this.tollorParkingCharge = tollorParkingCharge;
	}
	public String getBkgRef() {
		return bkgRef;
	}
	public String getSystemInvoiceId() {
		return systemInvoiceId;
	}
	public String getBookingType() {
		return bookingType;
	}
	public String getAmendmentType() {
		return amendmentType;
	}
	public String getInvoicedate() {
		return invoicedate;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public String getBillingEntity() {
		return billingEntity;
	}
	public String getBookerName() {
		return bookerName;
	}
	public String getBookersLoginId() {
		return bookersLoginId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public String getSupplierCharge() {
		return supplierCharge;
	}
	public String getProductType() {
		return productType;
	}
	public String getItineraryType() {
		return itineraryType;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public String getDescription() {
		return description;
	}
	public String getAirlinePNRorProvBooking() {
		return airlinePNRorProvBooking;
	}
	public String getGDSPNR() {
		return GDSPNR;
	}
	public String getTicketNumorFinalBooking() {
		return ticketNumorFinalBooking;
	}
	public String getFareType() {
		return fareType;
	}
	public String getBookingRefundType() {
		return bookingRefundType;
	}
	public String getFareBasis() {
		return fareBasis;
	}
	public String getCabinClass() {
		return cabinClass;
	}
	public String getBookingClass() {
		return bookingClass;
	}
	public String getPaxType() {
		return paxType;
	}
	public String getTraveller() {
		return traveller;
	}
	public String getTotalNights() {
		return totalNights;
	}
	public String getTripStartsDate() {
		return tripStartsDate;
	}
	public String getTripEndDate() {
		return tripEndDate;
	}
	public String getJourneyType() {
		return journeyType;
	}
	public String getBaseFare() {
		return baseFare;
	}
	public String getYQTax() {
		return YQTax;
	}
	public String getYRTax() {
		return YRTax;
	}
	public String getPSFTax() {
		return PSFTax;
	}
	public String getUDFTax() {
		return UDFTax;
	}
	public String getJNTax() {
		return JNTax;
	}
	public String getOBTax() {
		return OBTax;
	}
	public String getOCTax() {
		return OCTax;
	}
	public String getOtherTaxes() {
		return OtherTaxes;
	}
	public String getExtraCharge() {
		return extraCharge;
	}
	public String getSupplierAmendmentOrCancellationFee() {
		return supplierAmendmentOrCancellationFee;
	}
	public String getGrossFare() {
		return grossFare;
	}
	public String getSwachBharatCess() {
		return swachBharatCess;
	}
	public String getKrishiKalyanCess() {
		return krishiKalyanCess;
	}
	public String getServiceTax() {
		return serviceTax;
	}
	public String getConvenienceCharge() {
		return convenienceCharge;
	}
	public String getDiscount() {
		return discount;
	}
	public String getNetFare() {
		return netFare;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public String getTravelStatus() {
		return travelStatus;
	}
	public String getPersonalBooking() {
		return personalBooking;
	}
	public String getCorporateCurrency() {
		return corporateCurrency;
	}
	 
	public String getEmpCode() {
		return empCode;
	}
	public void setBkgRef(String bkgRef) {
		this.bkgRef = bkgRef;
	}
	public void setSystemInvoiceId(String systemInvoiceId) {
		this.systemInvoiceId = systemInvoiceId;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public void setAmendmentType(String amendmentType) {
		this.amendmentType = amendmentType;
	}
	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public void setBillingEntity(String billingEntity) {
		this.billingEntity = billingEntity;
	}
	public void setBookerName(String bookerName) {
		this.bookerName = bookerName;
	}
	public void setBookersLoginId(String bookersLoginId) {
		this.bookersLoginId = bookersLoginId;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public void setSupplierCharge(String supplierCharge) {
		this.supplierCharge = supplierCharge;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public void setItineraryType(String itineraryType) {
		this.itineraryType = itineraryType;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAirlinePNRorProvBooking(String airlinePNRorProvBooking) {
		this.airlinePNRorProvBooking = airlinePNRorProvBooking;
	}
	public void setGDSPNR(String gDSPNR) {
		GDSPNR = gDSPNR;
	}
	public void setTicketNumorFinalBooking(String ticketNumorFinalBooking) {
		this.ticketNumorFinalBooking = ticketNumorFinalBooking;
	}
	public void setFareType(String fareType) {
		this.fareType = fareType;
	}
	public void setBookingRefundType(String bookingRefundType) {
		this.bookingRefundType = bookingRefundType;
	}
	public void setFareBasis(String fareBasis) {
		this.fareBasis = fareBasis;
	}
	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}
	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}
	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}
	public void setTraveller(String traveller) {
		this.traveller = traveller;
	}
	public void setTotalNights(String totalNights) {
		this.totalNights = totalNights;
	}
	public void setTripStartsDate(String tripStartsDate) {
		this.tripStartsDate = tripStartsDate;
	}
	public void setTripEndDate(String tripEndDate) {
		this.tripEndDate = tripEndDate;
	}
	public void setJourneyType(String journeyType) {
		this.journeyType = journeyType;
	}
	public void setBaseFare(String baseFare) {
		this.baseFare = baseFare;
	}
	public void setYQTax(String yQTax) {
		YQTax = yQTax;
	}
	public void setYRTax(String yRTax) {
		YRTax = yRTax;
	}
	public void setPSFTax(String pSFTax) {
		PSFTax = pSFTax;
	}
	public void setUDFTax(String uDFTax) {
		UDFTax = uDFTax;
	}
	public void setJNTax(String jNTax) {
		JNTax = jNTax;
	}
	public void setOBTax(String oBTax) {
		OBTax = oBTax;
	}
	public void setOCTax(String oCTax) {
		OCTax = oCTax;
	}
	public void setOtherTaxes(String otherTaxes) {
		OtherTaxes = otherTaxes;
	}
	public void setExtraCharge(String extraCharge) {
		this.extraCharge = extraCharge;
	}
	public void setSupplierAmendmentOrCancellationFee(String supplierAmendmentOrCancellationFee) {
		this.supplierAmendmentOrCancellationFee = supplierAmendmentOrCancellationFee;
	}
	public void setGrossFare(String grossFare) {
		this.grossFare = grossFare;
	}
	public void setSwachBharatCess(String swachBharatCess) {
		this.swachBharatCess = swachBharatCess;
	}
	public void setKrishiKalyanCess(String krishiKalyanCess) {
		this.krishiKalyanCess = krishiKalyanCess;
	}
	public void setServiceTax(String serviceTax) {
		this.serviceTax = serviceTax;
	}
	public void setConvenienceCharge(String convenienceCharge) {
		this.convenienceCharge = convenienceCharge;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public void setNetFare(String netFare) {
		this.netFare = netFare;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public void setTravelStatus(String travelStatus) {
		this.travelStatus = travelStatus;
	}
	public void setPersonalBooking(String personalBooking) {
		this.personalBooking = personalBooking;
	}
	public void setCorporateCurrency(String corporateCurrency) {
		this.corporateCurrency = corporateCurrency;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getServiceTaxBase() {
		return serviceTaxBase;
	}
	public void setServiceTaxBase(String serviceTaxBase) {
		this.serviceTaxBase = serviceTaxBase;
	}
	public String getTayyarahServiceCharges() {
		return tayyarahServiceCharges;
	}
	public void setTayyarahServiceCharges(String tayyarahServiceCharges) {
		this.tayyarahServiceCharges = tayyarahServiceCharges;
	}
	public String getMarkup() {
		return markup;
	}
	public void setMarkup(String markup) {
		this.markup = markup;
	}
	public String getExtraHourCharge() {
		return extraHourCharge;
	}
	public void setExtraHourCharge(String extraHourCharge) {
		this.extraHourCharge = extraHourCharge;
	}
	public String getCourierCharges() {
		return courierCharges;
	}
	public void setCourierCharges(String courierCharges) {
		this.courierCharges = courierCharges;
	}
	public String getVfsCharges() {
		return vfsCharges;
	}
	public void setVfsCharges(String vfsCharges) {
		this.vfsCharges = vfsCharges;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getINTax() {
		return INTax;
	}
	public void setINTax(String iNTax) {
		INTax = iNTax;
	}


	public String getLocation() {
		return location;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public String getReasonForTravel() {
		return reasonForTravel;
	}
	public String getTravelRequestNumber() {
		return travelRequestNumber;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public void setReasonForTravel(String reasonForTravel) {
		this.reasonForTravel = reasonForTravel;
	}
	public void setTravelRequestNumber(String travelRequestNumber) {
		this.travelRequestNumber = travelRequestNumber;
	}
	public String getExtraRmConfigDetails() {
		return extraRmConfigDetails;
	}
	public void setExtraRmConfigDetails(String extraRmConfigDetails) {
		this.extraRmConfigDetails = extraRmConfigDetails;
	}
	public boolean isCreditnoteIssued() {
		return creditnoteIssued;
	}
	public void setCreditnoteIssued(boolean creditnoteIssued) {
		this.creditnoteIssued = creditnoteIssued;
	}
	public Map<String,String> getRmConfigMap() {
		return rmConfigMap;
	}
	public void setRmConfigMap(Map<String,String> rmConfigMap) {
		this.rmConfigMap = rmConfigMap;
	}
	public String getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public String getTravelerEmail() {
		return travelerEmail;
	}
	public void setTravelerEmail(String travelerEmail) {
		this.travelerEmail = travelerEmail;
	}
	public String getTravelerPhone() {
		return travelerPhone;
	}
	public void setTravelerPhone(String travelerPhone) {
		this.travelerPhone = travelerPhone;
	}
	public String getSegmentNumber() {
		return segmentNumber;
	}
	public void setSegmentNumber(String segmentNumber) {
		this.segmentNumber = segmentNumber;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public String getWOTax() {
		return WOTax;
	}
	public void setWOTax(String wOTax) {
		WOTax = wOTax;
	}
	public String getNetDiscount() {
		return netDiscount;
	}
	public void setNetDiscount(String netDiscount) {
		this.netDiscount = netDiscount;
	}
	public int getGuestCount() {
		return guestCount;
	}
	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGstOnTravels() {
		return gstOnTravels;
	}
	public void setGstOnTravels(String gstOnTravels) {
		this.gstOnTravels = gstOnTravels;
	}
	
	public String getBookingReference() {
		return bookingReference;
	}
	public void setBookingReference(String bookingReference) {
		this.bookingReference = bookingReference;
	}
	public String getTotGstTax() {
		return totGstTax;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTotGstTax(String totGstTax) {
		this.totGstTax = totGstTax;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getK3Tax() {
		return K3Tax;
	}
	public void setK3Tax(String k3Tax) {
		K3Tax = k3Tax;
	}
	
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getNoOfRooms() {
		return noOfRooms;
	}
	public void setNoOfRooms(String noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	public String getGuestName() {
		return guestName;
	}
	public String getStatus() {
		return status;
	}
	public String getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}
	public String getBasePrice() {
		return basePrice;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public String getNoOfNights() {
		return noOfNights;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTotalInvoiceAmount(String totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public void setNoOfNights(String noOfNights) {
		this.noOfNights = noOfNights;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBandCode() {
		return bandCode;
	}
	public void setBandCode(String bandCode) {
		this.bandCode = bandCode;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAirlinePnr() {
		return AirlinePnr;
	}
	public void setAirlinePnr(String airlinePnr) {
		AirlinePnr = airlinePnr;
	}
	public String getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public boolean isKnockOff() {
		return knockOff;
	}
	public void setKnockOff(boolean knockOff) {
		this.knockOff = knockOff;
	}
	 
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
 

}
