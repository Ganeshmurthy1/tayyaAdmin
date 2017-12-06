package com.admin.common.quotation.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.admin.common.quotation.dao.InsuranceTravelRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.utility.DateConversion;

@Entity
@Table(name = "insurance_travel_request_quotation")
public class InsuranceTravelRequestQuotation extends Timestampable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private String onwardTravelDateTemp;
	
	@Transient
	private InsuranceOrderRow insuranceOrderRow;
	
	@Transient
	private String returnTravelDatetemp;
	@Transient
	private String discription;
		
	
	@Column(name="onward_travelDate")
	@Temporal(TemporalType.DATE)
	private Date onwardTravelDate;
	
	
	@Column(name="return_travelDate")
	@Temporal(TemporalType.DATE)
	private Date ReturnTravelDate;
	
	@Column(name = "passport_number")
	private String passportNumber;
	
	
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "remarks")
	private String remarks;
	
	
	
	
	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "insurance_travel_req_id", referencedColumnName = "id")
	private InsuranceTravelRequest insuranceTravelRequest;
	
	@Column(name = "order_row_Id")
	private Long  orderRowId;
	@Column(name = "is_booked",columnDefinition = "BOOLEAN DEFAULT false")//new column added by raham
	private boolean  isBooked;
	@Column(name = "is_hide" ,columnDefinition = "BOOLEAN DEFAULT false")//new column added by raham
	private boolean  isHide;
	@Column(name = "status_id")
	private int statusId;


	
	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "hotel_flight_quotation_status_id", referencedColumnName = "id")
	private HotelFlightBookingStatus hotetFlightBookingStatus;
	public HotelFlightBookingStatus getHotetFlightBookingStatus() {
		return hotetFlightBookingStatus;
	}

	public void setHotetFlightBookingStatus(HotelFlightBookingStatus hotetFlightBookingStatus) {
		this.hotetFlightBookingStatus = hotetFlightBookingStatus;
	}

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	
	public InsuranceTravelRequest getInsuranceTravelRequest() {
		return insuranceTravelRequest;
	}

	public void setInsuranceTravelRequest(InsuranceTravelRequest insuranceTravelRequest) {
		this.insuranceTravelRequest = insuranceTravelRequest;
	}

	public Long getOrderRowId() {
		return orderRowId;
	}

	public void setOrderRowId(Long orderRowId) {
		this.orderRowId = orderRowId;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public boolean isHide() {
		return isHide;
	}

	public void setHide(boolean isHide) {
		this.isHide = isHide;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOnwardTravelDateTemp() {
		return onwardTravelDateTemp;
	}

	public void setOnwardTravelDateTemp(String onwardTravelDateTemp) {
		this.onwardTravelDateTemp = onwardTravelDateTemp;
	}

	public String getReturnTravelDatetemp() {
		return returnTravelDatetemp;
	}

	public void setReturnTravelDatetemp(String returnTravelDatetemp) {
		this.returnTravelDatetemp = returnTravelDatetemp;
	}

	public Date getOnwardTravelDate() {
		return onwardTravelDate;
	}

	public void setOnwardTravelDate(String onwardTravelDateTemp) {
		onwardTravelDate = DateConversion.StringToDate(onwardTravelDateTemp);
	}

	public Date getReturnTravelDate() {
		return ReturnTravelDate;
	}

	public void setReturnTravelDate(String returnTravelDatetemp) {
		ReturnTravelDate = DateConversion.StringToDate(returnTravelDatetemp);
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public InsuranceOrderRow getInsuranceOrderRow() {
		if(orderRowId!=null){
			insuranceOrderRow =new InsuranceTravelRequestDao().getInsuranceOrderRowDetailsById(orderRowId);
			if(insuranceOrderRow.getTotalAmount()!=null)
				insuranceOrderRow.setTotalAmount(insuranceOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		}
		return insuranceOrderRow;
	}

	public void setInsuranceOrderRow(InsuranceOrderRow insuranceOrderRow) {
		this.insuranceOrderRow = insuranceOrderRow;
	}

	
	
}
