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

import com.admin.common.quotation.dao.VisaTravelRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.utility.DateConversion;

@Entity
@Table(name = "visa_travel_request_quotation")
public class VisaTravelRequestQuotation extends Timestampable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String travelDateTemp; 

	@Transient
	private VisaOrderRow visaOrderRow;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "travel_date")
	private Date travelDate;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "currency")
	private String currency;
	
	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "visa_travel_req_id", referencedColumnName = "id")
	private VisaTravelRequest visaTravelRequest;

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


	public String getTravelDateTemp() {
		return travelDateTemp;
	}

	public void setTravelDateTemp(String travelDateTemp) {
		this.travelDateTemp = travelDateTemp;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDateTemp) {
		this.travelDate = DateConversion.StringToDate(travelDateTemp);
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public VisaTravelRequest getVisaTravelRequest() {
		return visaTravelRequest;
	}

	public void setVisaTravelRequest(VisaTravelRequest visaTravelRequest) {
		this.visaTravelRequest = visaTravelRequest;
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

	public VisaOrderRow getVisaOrderRow() {
		if(orderRowId!=null){
			visaOrderRow =new VisaTravelRequestDao().getVisaOrderRowDetailsById(orderRowId);
			if(visaOrderRow.getTotalAmount()!=null)
				visaOrderRow.setTotalAmount(visaOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		}
		return visaOrderRow;
	}

	public void setVisaOrderRow(VisaOrderRow visaOrderRow) {
		this.visaOrderRow = visaOrderRow;
	}
	
}
