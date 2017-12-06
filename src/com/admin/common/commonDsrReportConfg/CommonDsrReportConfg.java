package com.admin.common.commonDsrReportConfg;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "common_dsr_report_confg")
public class CommonDsrReportConfg extends Timestampable implements Serializable {
	/**
	 * @author Edited by Basha
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "companyId")
	private int companyId;
	
	@Column(name = "is_Booking_Reference",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean bookingReference;
	@Column(name = "is_System_InvoiceId",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean systemInvoiceId;
	
	@Column(name = "is_Booking_Type",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean bookingType;
	@Column(name = "is_Amendment_Type",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean amendmentType;
	@Column(name = "is_Invoice_Date",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean invoiceDate;
	@Column(name = "is_Booking_Date",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean bookingDate;
	@Column(name = "is_Corporate_Name",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean corporateName;
	@Column(name = "is_billing_Entity",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean billingEntity;
	@Column(name = "is_Booker_Name",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean bookerName;
	@Column(name = "is_bookers_LoginId",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean bookersLoginId;
	@Column(name = "is_supplier_Code",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean supplierCode;
	@Column(name = "is_supplier_Name",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean supplierName;
	@Column(name = "is_supplier_Charge",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean supplierCharge;
	@Column(name = "is_product_Type",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean productType;
	@Column(name = "is_itinerary_Type",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean itineraryType;
	@Column(name = "is_product_Name",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean productName;
	@Column(name = "is_product_Code",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean productCode;
	@Column(name = "is_description",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean description;
	@Column(name = "is_description2",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean description2;
	@Column(name = "is_provBooking",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean provBooking;
	@Column(name = "is_gds_Pnr",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean gdsPnr;
	@Column(name = "ticketNum",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean ticketNum;
	@Column(name = "is_fare_Type",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean fareType;
	@Column(name = "is_booking_RefundType",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean bookingRefundType;
	@Column(name = "is_fare_Basis",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean fareBasis;
	@Column(name = "is_cabin_Class",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean cabinClass;
	@Column(name = "is_booking_Class",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean bookingClass;
	@Column(name = "is_pax_Type",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean paxType;
	@Column(name = "istraveller_Name",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean travellerName;
	@Column(name = "istotal_Nights",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean totalNights;
	@Column(name = "istrip_Start_Date",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean tripStartDate;
	@Column(name = "istrip_End_Date",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean tripEndDate;
	@Column(name = "isjourney_Type",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean journeyType;
	@Column(name = "isbase_Fare",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean baseFare;
	@Column(name = "isyq_Tax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean yqTax;
	@Column(name = "isyr_Tax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean yrTax;
	@Column(name = "isk3_Tax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean k3Tax;
	@Column(name = "is_PsfTax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean psfTax;
	@Column(name = "is_UDfTax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean uDfTax;
	@Column(name = "is_JnTax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean jnTax;
	@Column(name = "is_InTax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean inTax;
	@Column(name = "is_OcTax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean ocTax;
	@Column(name = "is_OtherTax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean otherTax;
	@Column(name = "is_VFSTax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean vFSTax;
	@Column(name = "is_CourierCharge",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean courierCharge;
	@Column(name = "is_ExtraKmCharge",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean extraKmCharge;
	@Column(name = "is_DriverAllowDay",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean driverAllowDay;
	@Column(name = "is_DriverAllowNight",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean driverAllowNight;
	@Column(name = "is_TollOrParkingCharge",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean tollOrParkingCharge;
	@Column(name = "is_ExtraHourCharge",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean extraHourCharge;
	@Column(name = "is_ExtraCharge",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean extraCharge;
	@Column(name = "is_supplierAmendment",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean supplierAmendment;
	@Column(name = "is_grossFare",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean grossFare;
	@Column(name = "is_Cgst",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean cgst;
	@Column(name = "is_SgstorIgstorUgst",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean sgstorIgstorUgst;
	@Column(name = "is_gstTax",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean gstTax;
	@Column(name = "is_TayyarahCharge",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean tayyarahCharge;
	@Column(name = "is_ConvienceFee",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean convienceFee;
	@Column(name = "is_Discount",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean discount;
	@Column(name = "is_NetFare",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean netFare;
	@Column(name = "is_Total_Markup",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean totalMarkup;
	@Column(name = "is_Mode_Of_Payment",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean modeOfPayment;
	@Column(name = "is_Travel_Status",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean travelStatus;
	@Column(name = "is_Personal_Booking",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean personalBooking;
	@Column(name = "is_Corporate_Currency",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean corporateCurrency;
	
	@Column(name="createdby_company_user_id ")
	private int createdbyCompanyUserId;
	@Column(name="modifiedby_company_user_id")
	private int modifiedbyCompanyUserId;
	
	
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public boolean isBookingReference() {
		return bookingReference;
	}
	public void setBookingReference(boolean bookingReference) {
		this.bookingReference = bookingReference;
	}
	public boolean isSystemInvoiceId() {
		return systemInvoiceId;
	}
	public void setSystemInvoiceId(boolean systemInvoiceId) {
		this.systemInvoiceId = systemInvoiceId;
	}
	public boolean isBookingType() {
		return bookingType;
	}
	public void setBookingType(boolean bookingType) {
		this.bookingType = bookingType;
	}
	public boolean isAmendmentType() {
		return amendmentType;
	}
	public void setAmendmentType(boolean amendmentType) {
		this.amendmentType = amendmentType;
	}
	public boolean isInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(boolean invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public boolean isBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(boolean bookingDate) {
		this.bookingDate = bookingDate;
	}
	public boolean isCorporateName() {
		return corporateName;
	}
	public void setCorporateName(boolean corporateName) {
		this.corporateName = corporateName;
	}
	public boolean isBillingEntity() {
		return billingEntity;
	}
	public void setBillingEntity(boolean billingEntity) {
		this.billingEntity = billingEntity;
	}
	public boolean isBookerName() {
		return bookerName;
	}
	public void setBookerName(boolean bookerName) {
		this.bookerName = bookerName;
	}
	public boolean isBookersLoginId() {
		return bookersLoginId;
	}
	public void setBookersLoginId(boolean bookersLoginId) {
		this.bookersLoginId = bookersLoginId;
	}
	public boolean isSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(boolean supplierCode) {
		this.supplierCode = supplierCode;
	}
	public boolean isSupplierName() {
		return supplierName;
	}
	public void setSupplierName(boolean supplierName) {
		this.supplierName = supplierName;
	}
	public boolean isSupplierCharge() {
		return supplierCharge;
	}
	public void setSupplierCharge(boolean supplierCharge) {
		this.supplierCharge = supplierCharge;
	}
	public boolean isProductType() {
		return productType;
	}
	public void setProductType(boolean productType) {
		this.productType = productType;
	}
	public boolean isItineraryType() {
		return itineraryType;
	}
	public void setItineraryType(boolean itineraryType) {
		this.itineraryType = itineraryType;
	}
	public boolean isProductName() {
		return productName;
	}
	public void setProductName(boolean productName) {
		this.productName = productName;
	}
	public boolean isProductCode() {
		return productCode;
	}
	public void setProductCode(boolean productCode) {
		this.productCode = productCode;
	}
	public boolean isDescription() {
		return description;
	}
	public void setDescription(boolean description) {
		this.description = description;
	}
	public boolean isDescription2() {
		return description2;
	}
	public void setDescription2(boolean description2) {
		this.description2 = description2;
	}
	public boolean isProvBooking() {
		return provBooking;
	}
	public void setProvBooking(boolean provBooking) {
		this.provBooking = provBooking;
	}
	public boolean isGdsPnr() {
		return gdsPnr;
	}
	public void setGdsPnr(boolean gdsPnr) {
		this.gdsPnr = gdsPnr;
	}
	public boolean isTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(boolean ticketNum) {
		this.ticketNum = ticketNum;
	}
	public boolean isFareType() {
		return fareType;
	}
	public void setFareType(boolean fareType) {
		this.fareType = fareType;
	}
	public boolean isBookingRefundType() {
		return bookingRefundType;
	}
	public void setBookingRefundType(boolean bookingRefundType) {
		this.bookingRefundType = bookingRefundType;
	}
	public boolean isFareBasis() {
		return fareBasis;
	}
	public void setFareBasis(boolean fareBasis) {
		this.fareBasis = fareBasis;
	}
	public boolean isCabinClass() {
		return cabinClass;
	}
	public void setCabinClass(boolean cabinClass) {
		this.cabinClass = cabinClass;
	}
	public boolean isBookingClass() {
		return bookingClass;
	}
	public void setBookingClass(boolean bookingClass) {
		this.bookingClass = bookingClass;
	}
	public boolean isPaxType() {
		return paxType;
	}
	public void setPaxType(boolean paxType) {
		this.paxType = paxType;
	}
	public boolean isTravellerName() {
		return travellerName;
	}
	public void setTravellerName(boolean travellerName) {
		this.travellerName = travellerName;
	}
	public boolean isTotalNights() {
		return totalNights;
	}
	public void setTotalNights(boolean totalNights) {
		this.totalNights = totalNights;
	}
	public boolean isTripStartDate() {
		return tripStartDate;
	}
	public void setTripStartDate(boolean tripStartDate) {
		this.tripStartDate = tripStartDate;
	}
	public boolean isTripEndDate() {
		return tripEndDate;
	}
	public void setTripEndDate(boolean tripEndDate) {
		this.tripEndDate = tripEndDate;
	}
	public boolean isJourneyType() {
		return journeyType;
	}
	public void setJourneyType(boolean journeyType) {
		this.journeyType = journeyType;
	}
	public boolean isBaseFare() {
		return baseFare;
	}
	public void setBaseFare(boolean baseFare) {
		this.baseFare = baseFare;
	}
	public boolean isYqTax() {
		return yqTax;
	}
	public void setYqTax(boolean yqTax) {
		this.yqTax = yqTax;
	}
	public boolean isYrTax() {
		return yrTax;
	}
	public void setYrTax(boolean yrTax) {
		this.yrTax = yrTax;
	}
	public boolean isK3Tax() {
		return k3Tax;
	}
	public void setK3Tax(boolean k3Tax) {
		this.k3Tax = k3Tax;
	}
	public boolean isPsfTax() {
		return psfTax;
	}
	public void setPsfTax(boolean psfTax) {
		this.psfTax = psfTax;
	}
	public boolean isuDfTax() {
		return uDfTax;
	}
	public void setuDfTax(boolean uDfTax) {
		this.uDfTax = uDfTax;
	}
	public boolean isJnTax() {
		return jnTax;
	}
	public void setJnTax(boolean jnTax) {
		this.jnTax = jnTax;
	}
	public boolean isInTax() {
		return inTax;
	}
	public void setInTax(boolean inTax) {
		this.inTax = inTax;
	}
	public boolean isOcTax() {
		return ocTax;
	}
	public void setOcTax(boolean ocTax) {
		this.ocTax = ocTax;
	}
	public boolean isOtherTax() {
		return otherTax;
	}
	public void setOtherTax(boolean otherTax) {
		this.otherTax = otherTax;
	}
	public boolean isvFSTax() {
		return vFSTax;
	}
	public void setvFSTax(boolean vFSTax) {
		this.vFSTax = vFSTax;
	}
	public boolean isCourierCharge() {
		return courierCharge;
	}
	public void setCourierCharge(boolean courierCharge) {
		this.courierCharge = courierCharge;
	}
	public boolean isExtraKmCharge() {
		return extraKmCharge;
	}
	public void setExtraKmCharge(boolean extraKmCharge) {
		this.extraKmCharge = extraKmCharge;
	}
	public boolean isDriverAllowDay() {
		return driverAllowDay;
	}
	public void setDriverAllowDay(boolean driverAllowDay) {
		this.driverAllowDay = driverAllowDay;
	}
	public boolean isDriverAllowNight() {
		return driverAllowNight;
	}
	public void setDriverAllowNight(boolean driverAllowNight) {
		this.driverAllowNight = driverAllowNight;
	}
	public boolean isTollOrParkingCharge() {
		return tollOrParkingCharge;
	}
	public void setTollOrParkingCharge(boolean tollOrParkingCharge) {
		this.tollOrParkingCharge = tollOrParkingCharge;
	}
	public boolean isExtraHourCharge() {
		return extraHourCharge;
	}
	public void setExtraHourCharge(boolean extraHourCharge) {
		this.extraHourCharge = extraHourCharge;
	}
	public boolean isExtraCharge() {
		return extraCharge;
	}
	public void setExtraCharge(boolean extraCharge) {
		this.extraCharge = extraCharge;
	}
	public boolean isSupplierAmendment() {
		return supplierAmendment;
	}
	public void setSupplierAmendment(boolean supplierAmendment) {
		this.supplierAmendment = supplierAmendment;
	}
	public boolean isGrossFare() {
		return grossFare;
	}
	public void setGrossFare(boolean grossFare) {
		this.grossFare = grossFare;
	}
	public boolean isCgst() {
		return cgst;
	}
	public void setCgst(boolean cgst) {
		this.cgst = cgst;
	}
	public boolean isSgstorIgstorUgst() {
		return sgstorIgstorUgst;
	}
	public void setSgstorIgstorUgst(boolean sgstorIgstorUgst) {
		this.sgstorIgstorUgst = sgstorIgstorUgst;
	}
	public boolean isGstTax() {
		return gstTax;
	}
	public void setGstTax(boolean gstTax) {
		this.gstTax = gstTax;
	}
	public boolean isTayyarahCharge() {
		return tayyarahCharge;
	}
	public void setTayyarahCharge(boolean tayyarahCharge) {
		this.tayyarahCharge = tayyarahCharge;
	}
	public boolean isConvienceFee() {
		return convienceFee;
	}
	public void setConvienceFee(boolean convienceFee) {
		this.convienceFee = convienceFee;
	}
	public boolean isDiscount() {
		return discount;
	}
	public void setDiscount(boolean discount) {
		this.discount = discount;
	}
	public boolean isNetFare() {
		return netFare;
	}
	public void setNetFare(boolean netFare) {
		this.netFare = netFare;
	}
	public boolean isTotalMarkup() {
		return totalMarkup;
	}
	public void setTotalMarkup(boolean totalMarkup) {
		this.totalMarkup = totalMarkup;
	}
	public boolean isModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(boolean modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public boolean isTravelStatus() {
		return travelStatus;
	}
	public void setTravelStatus(boolean travelStatus) {
		this.travelStatus = travelStatus;
	}
	public boolean isPersonalBooking() {
		return personalBooking;
	}
	public void setPersonalBooking(boolean personalBooking) {
		this.personalBooking = personalBooking;
	}
	public boolean isCorporateCurrency() {
		return corporateCurrency;
	}
	public void setCorporateCurrency(boolean corporateCurrency) {
		this.corporateCurrency = corporateCurrency;
	}
	public int getCreatedbyCompanyUserId() {
		return createdbyCompanyUserId;
	}
	public void setCreatedbyCompanyUserId(int createdbyCompanyUserId) {
		this.createdbyCompanyUserId = createdbyCompanyUserId;
	}
	public int getModifiedbyCompanyUserId() {
		return modifiedbyCompanyUserId;
	}
	public void setModifiedbyCompanyUserId(int modifiedbyCompanyUserId) {
		this.modifiedbyCompanyUserId = modifiedbyCompanyUserId;
	}
	

}
