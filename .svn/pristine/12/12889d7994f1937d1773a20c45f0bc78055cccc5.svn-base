package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.admin.common.commonDsrReportConfg.CommonDsrReportConfg;
import com.admin.common.config.model.AdvertiseandOtherServiceTaxConfig;
import com.admin.common.config.model.BusServiceTaxConfig;
import com.admin.common.config.model.CarServiceTaxConfig;
import com.admin.common.config.model.FlightDomesticServiceTaxConfig;
import com.admin.common.config.model.FlightInternationalServiceTaxConfig;
import com.admin.common.config.model.HolidayServiceTaxConfig;
import com.admin.common.config.model.HotelServiceTaxConfig;
import com.admin.common.config.model.InsuranceServiceTaxConfig;
import com.admin.common.config.model.RailServiceTaxConfig;
import com.admin.common.config.model.VisaServiceTaxConfig;
import com.admin.miscellaneous.model.MiscellaneousGstTaxConfig;
import com.lintas.admin.advertisement.model.AdvertisementGstTaxConfig;
import com.lintas.admin.bus.model.BusGstTaxConfig;
import com.lintas.admin.car.model.CarGstTaxConfig;
import com.lintas.admin.flight.model.FlightDomesticGstTaxConfig;
import com.lintas.admin.flight.model.FlightInternationalGstTaxConfig;
import com.lintas.admin.holiday.model.HolidayGstTaxConfig;
import com.lintas.admin.hotel.model.HotelGstTaxConfig;
import com.lintas.admin.insurance.model.InsuranceGstTaxConfig;
import com.lintas.admin.train.model.TrainGstTaxConfig;
import com.lintas.admin.visa.model.VisaGstTaxConfig;
  
@Entity
@Table(name="companyconfig")
public class CompanyConfig implements Serializable{

	/** @author info name:raham
	 * created Date:27-07-2015
	 */
	
	@Transient
	private String configType;
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int config_id;

	private int company_id;
	private int parent_config_id;
	private int  galileo_id;
	private int mail_config_id;
	@Column(name="company_user_id")
	private String companyUserId;
	private String language_id;
	private String companyType;
	private String companyName;
	private String  notify_failed_booking;
	private String sales_country_id;
	private String currency_id;
	private String config_number;
	private String paymntType;
	private String fareRuleCategories;
	private int cache_flag;
	private int order_tracking;
	private int payment_pending_expire_time;
	private String configname;
	@Column(name="is_active")
	private boolean isActive;
	@Column(name="is_whitelable")
	private boolean isWhitelable;
	private String appKey;
	@Column(name="createdby_comapny_id")
	private int createdbyComapnyId;
	@Column(name="createdby_userId")
	private int createdbyUserId;
	@Column(name="modifiedby_userId")
	private int modifiedbyUserId;
	@Column(name="rate_type_hotel")
	private String rateTypeHotel;
	@Column(name="rate_type_flight")
	private String rateTypeFlight;
	
	@Column(name="commission_type_hotel")
	private String commissionTypeHotel;	
	@Column(name="commission_type_flight")
	private String commissionTypeFlight;	

	
	@Column(name="is_sheet_mode",columnDefinition="BIT(1) default 0")
	private boolean isSheetMode;
	@Column(name="commission_amount_hotel" , columnDefinition="decimal(20,2) default '0.00'")
	private BigDecimal commissionAmountHotel;	
	@Column(name="commission_amount_flight" , columnDefinition="decimal(20,2) default '0.00'")
	private BigDecimal commissionAmountFlight;	
	@Column(name="is_myconfig")
	private boolean isMyConfig;
	@Column(name="applied_block_id")
	private Long appliedBlockId;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "config_type", referencedColumnName = "id")
	private CompanyConfigType companyConfigType;
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_payment_config_id", referencedColumnName = "id")
	private HotelPaymentConfig  hotelPaymentConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_payment_config_id", referencedColumnName = "id")
	private FlightPaymentConfig  flightPaymentConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_service_config_id", referencedColumnName = "id")
	private BookingServiceConfig  bookingServiceConfig;	*/
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_service_tax_config_id", referencedColumnName = "id")
	private HotelServiceTaxConfig  hotelServiceTaxConfig;	
	 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_dom_service_tax_config_id", referencedColumnName = "id")
	private FlightDomesticServiceTaxConfig  flightDomesticServiceTaxConfig;		

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_intl_service_tax_config_id", referencedColumnName = "id")
	private FlightInternationalServiceTaxConfig  flightInternationalServiceTaxConfig;		
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_booking_cutoff_config_id", referencedColumnName = "id")
	private HotelBookingCutoffConfig  hotelBookingCutoffConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_booking_cutoff_config_id", referencedColumnName = "id")
	private FlightBookingCutoffConfig  flightBookingCutoffConfig;	*/
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bus_service_tax_config_id", referencedColumnName = "id")
	private BusServiceTaxConfig  busServiceTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rail_service_tax_config_id", referencedColumnName = "id")
	private RailServiceTaxConfig  railServiceTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "car_service_tax_config_id", referencedColumnName = "id")
	private CarServiceTaxConfig  carServiceTaxConfig;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "holiday_service_tax_config_id", referencedColumnName = "id")
	private HolidayServiceTaxConfig  holidayServiceTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "advertise_service_tax_config_id", referencedColumnName = "id")
	private AdvertiseandOtherServiceTaxConfig  advertiseandOtherServiceTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "visa_service_tax_config_id", referencedColumnName = "id")
	private VisaServiceTaxConfig  visaServiceTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "insurance_service_tax_config_id", referencedColumnName = "id")
	private InsuranceServiceTaxConfig insuranceServiceTaxConfig;	
	
	@Column(name="tax_type")
	private String taxtype;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "insurance_gst_tax_config_id", referencedColumnName = "id")
	private InsuranceGstTaxConfig insuranceGstTaxConfig;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "visa_gst_tax_config_id", referencedColumnName = "id")
	private VisaGstTaxConfig  visaGstTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_gst_tax_config_id", referencedColumnName = "id")
	private HotelGstTaxConfig  hotelGstTaxConfig;	
	 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_dom_gst_tax_config_id", referencedColumnName = "id")
	private FlightDomesticGstTaxConfig  flightDomesticGstTaxConfig;		

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_intl_gst_tax_config_id", referencedColumnName = "id")
	private FlightInternationalGstTaxConfig  flightInternationalGstTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bus_gst_tax_config_id", referencedColumnName = "id")
	private BusGstTaxConfig  busGstTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "train_gst_tax_config_id", referencedColumnName = "id")
	private TrainGstTaxConfig  trainGstTaxConfig;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "car_gst_tax_config_id", referencedColumnName = "id")
	private CarGstTaxConfig  carGstTaxConfig;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "advertisement_gst_tax_config_id", referencedColumnName = "id")
	private AdvertisementGstTaxConfig  advertisementGstTaxConfig;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "holiday_gst_tax_config_id", referencedColumnName = "id")
	private HolidayGstTaxConfig  holidayGstTaxConfig;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "miscellaneous_gst_tax_config_id", referencedColumnName = "id")
	private MiscellaneousGstTaxConfig  miscellaneousGstTaxConfig;
	
	public String getTaxtype() {
		return taxtype;
	}
	public void setTaxtype(String taxtype) {
		this.taxtype = taxtype;
	}
	public InsuranceGstTaxConfig getInsuranceGstTaxConfig() {
		return insuranceGstTaxConfig;
	}
	public void setInsuranceGstTaxConfig(InsuranceGstTaxConfig insuranceGstTaxConfig) {
		this.insuranceGstTaxConfig = insuranceGstTaxConfig;
	}
	public VisaGstTaxConfig getVisaGstTaxConfig() {
		return visaGstTaxConfig;
	}
	public void setVisaGstTaxConfig(VisaGstTaxConfig visaGstTaxConfig) {
		this.visaGstTaxConfig = visaGstTaxConfig;
	}
	public HotelGstTaxConfig getHotelGstTaxConfig() {
		return hotelGstTaxConfig;
	}
	public void setHotelGstTaxConfig(HotelGstTaxConfig hotelGstTaxConfig) {
		this.hotelGstTaxConfig = hotelGstTaxConfig;
	}
	public FlightDomesticGstTaxConfig getFlightDomesticGstTaxConfig() {
		return flightDomesticGstTaxConfig;
	}
	public void setFlightDomesticGstTaxConfig(FlightDomesticGstTaxConfig flightDomesticGstTaxConfig) {
		this.flightDomesticGstTaxConfig = flightDomesticGstTaxConfig;
	}
	public FlightInternationalGstTaxConfig getFlightInternationalGstTaxConfig() {
		return flightInternationalGstTaxConfig;
	}
	public void setFlightInternationalGstTaxConfig(FlightInternationalGstTaxConfig flightInternationalGstTaxConfig) {
		this.flightInternationalGstTaxConfig = flightInternationalGstTaxConfig;
	}
	public BusGstTaxConfig getBusGstTaxConfig() {
		return busGstTaxConfig;
	}
	public void setBusGstTaxConfig(BusGstTaxConfig busGstTaxConfig) {
		this.busGstTaxConfig = busGstTaxConfig;
	}
	public TrainGstTaxConfig getTrainGstTaxConfig() {
		return trainGstTaxConfig;
	}
	public void setTrainGstTaxConfig(TrainGstTaxConfig trainGstTaxConfig) {
		this.trainGstTaxConfig = trainGstTaxConfig;
	}
	public CarGstTaxConfig getCarGstTaxConfig() {
		return carGstTaxConfig;
	}
	public void setCarGstTaxConfig(CarGstTaxConfig carGstTaxConfig) {
		this.carGstTaxConfig = carGstTaxConfig;
	}
	public InsuranceServiceTaxConfig getInsuranceServiceTaxConfig() {
		return insuranceServiceTaxConfig;
	}
	public void setInsuranceServiceTaxConfig(InsuranceServiceTaxConfig insuranceServiceTaxConfig) {
		this.insuranceServiceTaxConfig = insuranceServiceTaxConfig;
	}
	public boolean isMyConfig() {
		return isMyConfig;
	}
	public void setMyConfig(boolean isMyConfig) {
		this.isMyConfig = isMyConfig;
	}
	
	/*public HotelPaymentConfig getHotelPaymentConfig() {
		return hotelPaymentConfig;
	}
	public void setHotelPaymentConfig(HotelPaymentConfig hotelPaymentConfig) {
		this.hotelPaymentConfig = hotelPaymentConfig;
	}
	public FlightPaymentConfig getFlightPaymentConfig() {
		return flightPaymentConfig;
	}
	public void setFlightPaymentConfig(FlightPaymentConfig flightPaymentConfig) {
		this.flightPaymentConfig = flightPaymentConfig;
	}
	public BookingServiceConfig getBookingServiceConfig() {
		return bookingServiceConfig;
	}
	public void setBookingServiceConfig(BookingServiceConfig bookingServiceConfig) {
		this.bookingServiceConfig = bookingServiceConfig;
	}*/
	public HotelServiceTaxConfig getHotelServiceTaxConfig() {
		return hotelServiceTaxConfig;
	}
	public void setHotelServiceTaxConfig(HotelServiceTaxConfig hotelServiceTaxConfig) {
		this.hotelServiceTaxConfig = hotelServiceTaxConfig;
	}
	
	/*public HotelBookingCutoffConfig getHotelBookingCutoffConfig() {
		return hotelBookingCutoffConfig;
	}
	public void setHotelBookingCutoffConfig(HotelBookingCutoffConfig hotelBookingCutoffConfig) {
		this.hotelBookingCutoffConfig = hotelBookingCutoffConfig;
	}
	public FlightBookingCutoffConfig getFlightBookingCutoffConfig() {
		return flightBookingCutoffConfig;
	}
	public void setFlightBookingCutoffConfig(FlightBookingCutoffConfig flightBookingCutoffConfig) {
		this.flightBookingCutoffConfig = flightBookingCutoffConfig;
	}*/
	public CompanyConfigType getCompanyConfigType() {
		return companyConfigType;
	}
	public void setCompanyConfigType(CompanyConfigType companyConfigType) {
		this.companyConfigType = companyConfigType;
	}
	
	public String getRateTypeHotel() {
		return rateTypeHotel;
	}
	public void setRateTypeHotel(String rateTypeHotel) {
		this.rateTypeHotel = rateTypeHotel;
	}
	public String getRateTypeFlight() {
		return rateTypeFlight;
	}
	public void setRateTypeFlight(String rateTypeFlight) {
		this.rateTypeFlight = rateTypeFlight;
	}
	public String getCommissionTypeHotel() {
		return commissionTypeHotel;
	}
	public void setCommissionTypeHotel(String commissionTypeHotel) {
		this.commissionTypeHotel = commissionTypeHotel;
	}
	public String getCommissionTypeFlight() {
		return commissionTypeFlight;
	}
	public void setCommissionTypeFlight(String commissionTypeFlight) {
		this.commissionTypeFlight = commissionTypeFlight;
	}
	public BigDecimal getCommissionAmountHotel() {
		return commissionAmountHotel;
	}
	public void setCommissionAmountHotel(BigDecimal commissionAmountHotel) {
		this.commissionAmountHotel = commissionAmountHotel;
	}
	public BigDecimal getCommissionAmountFlight() {
		return commissionAmountFlight;
	}
	public void setCommissionAmountFlight(BigDecimal commissionAmountFlight) {
		this.commissionAmountFlight = commissionAmountFlight;
	}
	public int getConfig_id() {
		return config_id;
	}
	public void setConfig_id(int config_id) {
		this.config_id = config_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getGalileo_id() {
		return galileo_id;
	}
	public void setGalileo_id(int galileo_id) {
		this.galileo_id = galileo_id;
	}
	public int getMail_config_id() {
		return mail_config_id;
	}
	public void setMail_config_id(int mail_config_id) {
		this.mail_config_id = mail_config_id;
	}
	public String getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}
	public String getNotify_failed_booking() {
		return notify_failed_booking;
	}
	public void setNotify_failed_booking(String notify_failed_booking) {
		this.notify_failed_booking = notify_failed_booking;
	}
	public String getSales_country_id() {
		return sales_country_id;
	}
	public void setSales_country_id(String sales_country_id) {
		this.sales_country_id = sales_country_id;
	}
	public String getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}
	public String getConfig_number() {
		return config_number;
	}
	public void setConfig_number(String config_number) {
		this.config_number = config_number;
	}
	public String getPaymntType() {
		return paymntType;
	}
	public void setPaymntType(String paymntType) {
		this.paymntType = paymntType;
	}
	public String getFareRuleCategories() {
		return fareRuleCategories;
	}
	public void setFareRuleCategories(String fareRuleCategories) {
		this.fareRuleCategories = fareRuleCategories;
	}
	public int getCache_flag() {
		return cache_flag;
	}
	public void setCache_flag(int cache_flag) {
		this.cache_flag = cache_flag;
	}
	public int getOrder_tracking() {
		return order_tracking;
	}
	public void setOrder_tracking(int order_tracking) {
		this.order_tracking = order_tracking;
	}
	public int getPayment_pending_expire_time() {
		return payment_pending_expire_time;
	}
	public void setPayment_pending_expire_time(int payment_pending_expire_time) {
		this.payment_pending_expire_time = payment_pending_expire_time;
	}
	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}
	/**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	/**
	 * @return the configname
	 */
	public String getConfigname() {
		return configname;
	}
	/**
	 * @param configname the configname to set
	 */
	public void setConfigname(String configname) {
		this.configname = configname;
	}
	/**
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}
	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCreatedbyComapnyId() {
		return createdbyComapnyId;
	}
	public void setCreatedbyComapnyId(int createdbyComapnyId) {
		this.createdbyComapnyId = createdbyComapnyId;
	}
	public int getCreatedbyUserId() {
		return createdbyUserId;
	}
	public void setCreatedbyUserId(int createdbyUserId) {
		this.createdbyUserId = createdbyUserId;
	}
	public int getModifiedbyUserId() {
		return modifiedbyUserId;
	}
	public void setModifiedbyUserId(int modifiedbyUserId) {
		this.modifiedbyUserId = modifiedbyUserId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyUserId() {
		return companyUserId;
	}
	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}
	public int getParent_config_id() {
		return parent_config_id;
	}
	public void setParent_config_id(int parent_config_id) {
		this.parent_config_id = parent_config_id;
	}
	public boolean isWhitelable() {
		return isWhitelable;
	}
	public void setWhitelable(boolean isWhitelable) {
		this.isWhitelable = isWhitelable;
	}
	public Long getAppliedBlockId() {
		return appliedBlockId;
	}
	public void setAppliedBlockId(Long appliedBlockId) {
		this.appliedBlockId = appliedBlockId;
	}
	public boolean isSheetMode() {
		return isSheetMode;
	}
	public void setSheetMode(boolean isSheetMode) {
		this.isSheetMode = isSheetMode;
	}
	public String getConfigType() {
		if(getCompanyConfigType()!=null){
			if(getCompanyConfigType().isAPI()) 
				configType="API";
			if(getCompanyConfigType().isWhitelable()) 
				configType="WhiteLable";
			if(getCompanyConfigType().isB2B()) 
				configType="B2B";
			if(getCompanyConfigType().isB2C()) 
				configType="B2C";
			if(getCompanyConfigType().isB2E()) 
				configType="B2E";
		 }
		 
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public BusServiceTaxConfig getBusServiceTaxConfig() {
		return busServiceTaxConfig;
	}
	public RailServiceTaxConfig getRailServiceTaxConfig() {
		return railServiceTaxConfig;
	}
	public CarServiceTaxConfig getCarServiceTaxConfig() {
		return carServiceTaxConfig;
	}
	public HolidayServiceTaxConfig getHolidayServiceTaxConfig() {
		return holidayServiceTaxConfig;
	}
	public AdvertiseandOtherServiceTaxConfig getAdvertiseandOtherServiceTaxConfig() {
		return advertiseandOtherServiceTaxConfig;
	}
	public void setBusServiceTaxConfig(BusServiceTaxConfig busServiceTaxConfig) {
		this.busServiceTaxConfig = busServiceTaxConfig;
	}
	public void setRailServiceTaxConfig(RailServiceTaxConfig railServiceTaxConfig) {
		this.railServiceTaxConfig = railServiceTaxConfig;
	}
	public void setCarServiceTaxConfig(CarServiceTaxConfig carServiceTaxConfig) {
		this.carServiceTaxConfig = carServiceTaxConfig;
	}
	public void setHolidayServiceTaxConfig(HolidayServiceTaxConfig holidayServiceTaxConfig) {
		this.holidayServiceTaxConfig = holidayServiceTaxConfig;
	}
	public void setAdvertiseandOtherServiceTaxConfig(AdvertiseandOtherServiceTaxConfig advertiseandOtherServiceTaxConfig) {
		this.advertiseandOtherServiceTaxConfig = advertiseandOtherServiceTaxConfig;
	}
	public VisaServiceTaxConfig getVisaServiceTaxConfig() {
		return visaServiceTaxConfig;
	}
	public void setVisaServiceTaxConfig(VisaServiceTaxConfig visaServiceTaxConfig) {
		this.visaServiceTaxConfig = visaServiceTaxConfig;
	}
	public FlightDomesticServiceTaxConfig getFlightDomesticServiceTaxConfig() {
		return flightDomesticServiceTaxConfig;
	}
	public FlightInternationalServiceTaxConfig getFlightInternationalServiceTaxConfig() {
		return flightInternationalServiceTaxConfig;
	}
	public void setFlightDomesticServiceTaxConfig(FlightDomesticServiceTaxConfig flightDomesticServiceTaxConfig) {
		this.flightDomesticServiceTaxConfig = flightDomesticServiceTaxConfig;
	}
	public void setFlightInternationalServiceTaxConfig(
			FlightInternationalServiceTaxConfig flightInternationalServiceTaxConfig) {
		this.flightInternationalServiceTaxConfig = flightInternationalServiceTaxConfig;
	}
	public AdvertisementGstTaxConfig getAdvertisementGstTaxConfig() {
		return advertisementGstTaxConfig;
	}
	public void setAdvertisementGstTaxConfig(AdvertisementGstTaxConfig advertisementGstTaxConfig) {
		this.advertisementGstTaxConfig = advertisementGstTaxConfig;
	}
	public HolidayGstTaxConfig getHolidayGstTaxConfig() {
		return holidayGstTaxConfig;
	}
	public void setHolidayGstTaxConfig(HolidayGstTaxConfig holidayGstTaxConfig) {
		this.holidayGstTaxConfig = holidayGstTaxConfig;
	}
	public MiscellaneousGstTaxConfig getMiscellaneousGstTaxConfig() {
		return miscellaneousGstTaxConfig;
	}
	public void setMiscellaneousGstTaxConfig(MiscellaneousGstTaxConfig miscellaneousGstTaxConfig) {
		this.miscellaneousGstTaxConfig = miscellaneousGstTaxConfig;
	}
	

}
