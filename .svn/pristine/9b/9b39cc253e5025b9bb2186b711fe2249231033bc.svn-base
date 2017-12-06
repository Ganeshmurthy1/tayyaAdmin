package com.admin.hotel.fin.sheet.model;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.admin.api.provider.model.ApiProviderPaymentTransaction;
import com.admin.hotel.fin.sheet.Dao.HotelOfflineBookingDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.utility.DateConversion;



@Entity
@Table(name="hotel_travel_request_quotation")
public class HotelTravelRequestQuotation  extends Timestampable{

	 
	private static final long serialVersionUID = 1L;

	@Transient
	private HotelOrderRow hotelOrderRow;
	@Transient
	private PaymentTransaction paymentTransaction;
	@Transient
	private ApiProviderPaymentTransaction apiProviderPaymentTransaction;
	@Transient
	private String checkIn;
	@Transient
	private BigDecimal basePrice;
	@Transient
	private String checkOut; 
	@Transient
	private String onlineUrl; 
	@Transient
	private List<String> availablePaymentOptionList;

	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "hotel_travel_req_id", referencedColumnName = "id")
	private HotelTravelRequest hotelTravelRequest; 
	
	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "hotel_flight_quotation_status_id", referencedColumnName = "id")
	private HotelFlightBookingStatus hotetFlightBookingStatus; 
	@Column(name = "currency")
	private String currency;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_option_id", referencedColumnName = "id")
	private PaymentOptions  paymentOptions;	
	
	@Column(name = "hotel_code")
	private String hotelCode;
	@Column(name = "city_code")
	private int cityCode;
	@Column(name = "status_id")
	private int statusId;
	@Column(name="room_schema",columnDefinition="text")
	private String roomSchema;

	@Column(name="room_id",columnDefinition="text")
	private String roomId;
	@Column(name = "sell_rate", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal sellRate;

	@Column(name = "is_booked")
	private boolean  isBooked;

	@Column(name = "is_prefer_property")
	private boolean  preferProperty;

	@Column(name = "order_row_Id")
	private Long  orderRowId;
	@Column(name="additional_data",columnDefinition="text")
	private String additionalData;
	@Column(name = "check_in_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date  	checkInDate ;
	@Column(name = "check_in_time")
	private String 	checkInTime ;
	@Column(name = "check_out_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date  checkOutDate ;
	@Column(name = "check_out_time")
	private String 	checkOutTime ;

	@Column(name = "adult_count")
	private int 	adultCount ;
	@Column(name = "child_count")
	private int 	childCount ;
	@Column(name = "night_count")
	private int 	nightCount ;
	@Column(name = "roomc_count")
	private int roomCount ;

	@Column(name = "available_payment_option")
	private String 	availablePaymentOption;

	@Column(name="hotel_name")
	private String hotelName;
	@Column(name = "hotel_category")
	private String 	hotelCategory ;
	@Column(name = "hotel_address")
	private String 	hotelAddress ;	
	@Column(name = "hotel_city")
	private String 	hotelCity ;
	@Column(name = "hotel_country")
	private String 	hotelCountry ;	
	@Column(name = "project_address")
	private String 	projectAddress ;
	@Column(name = "distance_from_work")
	private String 	distanceFromWork ;
	@Column(name = "room_type")
	private String 	roomType ;
	@Column(name = "room_rate_per_night")
	private String 	roomRatePerNight ;
	@Column(name = "taxes")
	private String 	taxes ;
	@Column(name = "breakfast")
	private String 	breakfast ;
	@Column(name = "internet")
	private String 	internet ;
	@Column(name = "cancellation_policy",columnDefinition="longtext")
	private String 	cancellationPolicy ;
	@Column(name="booking_mode")
	private String bookingMode;
	@Column(name = "is_hide" ,columnDefinition="BIT(1) default 0")//new column added by raham
	private boolean  isHide;
	@Column(name = "company_id" ,columnDefinition="INT(11) default 0")
	private int companyId;
	//--------------------11/15/1016 evening  created by raham-----------------------//
	
	public PaymentOptions getPaymentOptions() {
		return paymentOptions;
	}

	public void setPaymentOptions(PaymentOptions paymentOptions) {
		this.paymentOptions = paymentOptions;
	} 

	//------------------------------END--------------------//




	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "user_id",columnDefinition="INT(11) default 0")
	private int userId;


	public boolean isHide() {
		return isHide;
	}

	public void setHide(boolean isHide) {
		this.isHide = isHide;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public BigDecimal getSellRate() {
		return sellRate;
	}

	public void setSellRate(BigDecimal sellRate) {
		this.sellRate = sellRate;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public HotelTravelRequest getHotelTravelRequest() {
		return hotelTravelRequest;
	}

	public void setHotelTravelRequest(HotelTravelRequest hotelTravelRequest) {
		this.hotelTravelRequest = hotelTravelRequest;
	}

	public Long getOrderRowId() {
		return orderRowId;
	}

	public void setOrderRowId(Long orderRowId) {
		this.orderRowId = orderRowId;
	}

	public String getCheckIn() {
		if(getCheckInDate()!=null){
			checkIn=DateConversion.convertDateToStringToDate(getCheckInDate());
		}
		return checkIn;
	}

	public String getCheckOut() {
		if(getCheckOutDate()!=null){
			checkOut=DateConversion.convertDateToStringToDate(getCheckOutDate());
		}
		return checkOut;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public int getAdultCount() {
		return adultCount;
	}

	public int getChildCount() {
		return childCount;
	}

	public int getNightCount() {
		return nightCount;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public String getAvailablePaymentOption() {
		return availablePaymentOption;
	}

	public String getHotelCategory() {
		return hotelCategory;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}
	public String getHotelCity() {
		return hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelCountry() {
		return hotelCountry;
	}

	public void setHotelCountry(String hotelCountry) {
		this.hotelCountry = hotelCountry;
	}


	public String getProjectAddress() {
		return projectAddress;
	}

	public String getDistanceFromWork() {
		return distanceFromWork;
	}

	public String getRoomType() {
		return roomType;
	}

	public String getRoomRatePerNight() {
		return roomRatePerNight;
	}

	public String getTaxes() {
		return taxes;
	}

	public String getBreakfast() {
		return breakfast;
	}

	public String getInternet() {
		return internet;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public void setAdultCount(int adultCount) {
		this.adultCount = adultCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public void setNightCount(int nightCount) {
		this.nightCount = nightCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public void setAvailablePaymentOption(String availablePaymentOption) {
		this.availablePaymentOption = availablePaymentOption;
	}

	public void setHotelCategory(String hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public void setDistanceFromWork(String distanceFromWork) {
		this.distanceFromWork = distanceFromWork;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setRoomRatePerNight(String roomRatePerNight) {
		this.roomRatePerNight = roomRatePerNight;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	public void setInternet(String internet) {
		this.internet = internet;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}

	public boolean isPreferProperty() {
		return preferProperty;
	}

	public void setPreferProperty(boolean preferProperty) {
		this.preferProperty = preferProperty;
	}

	public List<String> getAvailablePaymentOptionList() {
		return availablePaymentOptionList;
	}

	public void setAvailablePaymentOptionList(List<String> availablePaymentOptionList) {
		this.availablePaymentOptionList = availablePaymentOptionList;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	public String getBookingMode() {
		return bookingMode;
	}

	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}



	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getOnlineUrl() {
		return onlineUrl;
	}

	public void setOnlineUrl(String onlineUrl) {
		this.onlineUrl = onlineUrl;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getRoomSchema() {
		return roomSchema;
	}

	public void setRoomSchema(String roomSchema) {
		this.roomSchema = roomSchema;
	}
	public HotelOrderRow getHotelOrderRow() {
		if(orderRowId!=null){
			hotelOrderRow =new HotelOrderDao().getHotelOrderRowInfo(orderRowId);
			hotelOrderRow.setFinalPrice(hotelOrderRow.getFinalPrice().setScale(2,BigDecimal.ROUND_UP));
		}
		return hotelOrderRow;
	}

	public void setHotelOrderRow(HotelOrderRow hotelOrderRow) {
		this.hotelOrderRow = hotelOrderRow;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public HotelFlightBookingStatus getHotetFlightBookingStatus() {
		return hotetFlightBookingStatus;
	}

	public void setHotetFlightBookingStatus(HotelFlightBookingStatus hotetFlightBookingStatus) {
		this.hotetFlightBookingStatus = hotetFlightBookingStatus;
	}

	public PaymentTransaction getPaymentTransaction() {
		if(getHotelOrderRow()!=null){
			paymentTransaction=new HotelOfflineBookingDao().getPaymentTransactionDetails(getHotelOrderRow().getOrderReference());
		}
		 return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public ApiProviderPaymentTransaction getApiProviderPaymentTransaction() {
		return apiProviderPaymentTransaction;
	}

	public void setApiProviderPaymentTransaction(ApiProviderPaymentTransaction apiProviderPaymentTransaction) {
		this.apiProviderPaymentTransaction = apiProviderPaymentTransaction;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	 

}
