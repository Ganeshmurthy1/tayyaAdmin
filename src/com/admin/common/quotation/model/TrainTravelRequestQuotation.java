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

import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.utility.DateConversion;
@Entity
@Table(name = "train_travel_request_quotation")
public class TrainTravelRequestQuotation extends Timestampable {

	private static final long serialVersionUID = 1L;
	@Transient
	private String traveldatetemp;
	@Transient
	private TrainOrderRow trainOrderRow;
	
	@Column(name = "remarks")
	private String remarks;
	 
	@Column(name="train_number")
	private String trainNumber; 
	
	@Column(name="from_location")
	private String Fromlocation;	
	
	@Column(name="to_location")
	private String Tolocation;	
	
	
	@Column(name="travel_date")
	@Temporal(TemporalType.DATE)
	private Date travelDate;
	
	
	
	@Column(name = "currency")
	private String currency;
	
	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "train_travel_req_id", referencedColumnName = "id")
	private TrainTravelRequest trainTravelRequest;
	
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

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getFromlocation() {
		return Fromlocation;
	}

	public void setFromlocation(String fromlocation) {
		Fromlocation = fromlocation;
	}

	public String getTolocation() {
		return Tolocation;
	}

	public void setTolocation(String tolocation) {
		Tolocation = tolocation;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String traveldatetemp) {
		this.travelDate = DateConversion.StringToDate(traveldatetemp);
	}

	

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public TrainTravelRequest getTrainTravelRequest() {
		return trainTravelRequest;
	}

	public void setTrainTravelRequest(TrainTravelRequest trainTravelRequest) {
		this.trainTravelRequest = trainTravelRequest;
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

	

	public String getTraveldatetemp() {
		return traveldatetemp;
	}

	public void setTraveldatetemp(String traveldatetemp) {
		this.traveldatetemp = traveldatetemp;
	}

	public TrainOrderRow getTrainOrderRow() {
		if(orderRowId!=null){
			trainOrderRow = new TrainTravelRequestDao().getTrainOrderRowDetailsById(orderRowId);
			if(trainOrderRow!=null && trainOrderRow.getTotalAmount()!=null)
				trainOrderRow.setTotalAmount(trainOrderRow.getTotalAmount().setScale(2,BigDecimal.ROUND_UP));
		}
		return trainOrderRow;
	}

	public void setTrainOrderRow(TrainOrderRow trainOrderRow) {
		this.trainOrderRow = trainOrderRow;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}


	

}

