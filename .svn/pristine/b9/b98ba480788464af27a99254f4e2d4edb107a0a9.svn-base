package com.admin.common.quotation.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.admin.common.quotation.dao.CarTravelRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.hotel.model.HotelOrderRow;

@Entity
@Table(name = "car_travel_request_quotation")
public class CarTravelRequestQuotation extends Timestampable {

	private static final long serialVersionUID = 1L;

	

	@Column(name = "pickup")
	private String pickUp;
	@Transient
	private CarOrderRow carOrderRow;

	@Column(name = "dropoff")
	private String dropOff;

	@Column(name="remarks")
	private String remarks;

	
	@Column(name = "currency")
	private String currency;
	
	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "car_travel_req_id", referencedColumnName = "id")
	private CarTravelRequest carTravelRequest;
	
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

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	public String getDropOff() {
		return dropOff;
	}

	public void setDropOff(String dropOff) {
		this.dropOff = dropOff;
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

	
	public CarTravelRequest getCarTravelRequest() {
		return carTravelRequest;
	}

	public void setCarTravelRequest(CarTravelRequest carTravelRequest) {
		this.carTravelRequest = carTravelRequest;
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

	public CarOrderRow getCarOrderRow() {
		if(orderRowId!=null){
			carOrderRow =new CarTravelRequestDao().getCarOrderRowDetailsById(orderRowId);
			if(carOrderRow!=null && carOrderRow.getTotalAmount()!=null)
				carOrderRow.setTotalAmount(carOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		}
		return carOrderRow;
	}

	public void setCarOrderRow(CarOrderRow carOrderRow) {
		this.carOrderRow = carOrderRow;
	}

	
}
