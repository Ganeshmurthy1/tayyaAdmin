package com.admin.api.provider.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;
@Entity
@Table(name="api_provider_tbo_Config")
public class ApiProviderTboConfig extends Timestampable implements Serializable{


	/**@author info name:Manish
	 * @created date:16-01-2017
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String tboFlightEnvironment;
	@Transient
	private String tboHotelEnvironment;
	@Transient
	private String companyName;

	@Column(name="company_id")
	private int companyId;
	
/*	private String URL_AUTHENTICATE = "SharedServices/SharedData.svc/rest/Authenticate";
	private String SEARCH_URL = "BookingEngineService_Air/AirService.svc/rest/Search/";
	private String FARERULE_URL = "BookingEngineService_Air/AirService.svc/rest/FareRule/";
	private String AIRPRICE_URL = "BookingEngineService_Air/AirService.svc/rest/FareQuote/";
	private String SSR_URL = "BookingEngineService_Air/AirService.svc/rest/SSR/";
	private String BOOK_URL = "BookingEngineService_Air/AirService.svc/rest/Book/";
	private String TICKET_URL = "BookingEngineService_Air/AirService.svc/rest/Ticket/";
	private String GETBOOKDETAIL_URL = "BookingEngineService_Air/AirService.svc/rest/GetBookingDetails/";
	private String RELEASEPNR_URL = "BookingEngineService_Air/AirService.svc/rest/ReleasePNRRequest/";
	private String CANCELLATION_URL = "BookingEngineService_Air/AirService.svc/rest/SendChangeRequest/";
	private String CANCELLATIONSTATUS_URL = "BookingEngineService_Air/AirService.svc/rest/GetChangeRequestStatus/";
	private String PRICERBD_URL = "BookingEngineService_Air/AirService.svc/rest/PriceRBD/";
	private String  AGENCY_BALANCE_URL = "SharedServices/SharedData.svc/rest/GetAgencyBalance";
	private String CALENDAR_SEARCH_URL = "BookingEngineService_Air/SharedData.svc/rest/GetCalendarFare/";
	*/
	
	@Column(name="generalUrl_agencyBalance")
	private String generalUrlAgencyBalance ;

	
	@Column(name="auth_url")
	private String authUrlFlight ;
	@Column(name="flightUrl_search")
	private String flightUrlSearch;
	@Column(name="flightUrl_farerule")
	private String flightUrlFarerule ;
	@Column(name="flightUrl_airprice")
	private String flightUrlAirprice ;
	@Column(name="flightUrl_ssr")
	private String flightUrlSsr ;
	@Column(name="flightUrl_sook")
	private String flightUrlBook ;
	@Column(name="flightUrl_ticket")
	private String flightUrlTicket ;
	@Column(name="flightUrl_getBookingDetail")
	private String flightUrlGetBookingDetail ;
	@Column(name="flightUrl_releasePnr")
	private String flightUrlReleasePnr ;
	@Column(name="flightUrl_cancellation")
	private String flightUrlCancellation ;
	@Column(name="flightUrl_cancellationStatus")
	private String flightUrlCancellationStatus ;
	@Column(name="flightUrl_priceRbd")
	private String flightUrlPriceRbd ;
	@Column(name="flightUrl_calendarSearch")
	private String flightUrlCalendarSearch ;

	@Column(name="user_name")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="property_id")
	private String propertyId;
	private String environment; // test , production , staging 
	@Column(name="api_currency")
	private String  apiCurrency;
	@Column(name="provider_id")
	private int  providerId = 1;
	@Column(name="provider_name")
	private String providerName;
	private String title;
	@Column(name = "is_active")
	private boolean active;
	@Column(name="client_id")
	private String  clientId ;
	@Column(name="enduser_ip")
	private String enduserIp ;

	/*for hotel*/
	
/*	private  String URL_AUTHENTICATE = "SharedServices/SharedData.svc/rest/Authenticate";	
	private  String URL_SEARCH_HOTELS = "BookingEngineService_Hotel/hotelservice.svc/rest/GetHotelResult/";
	private  String URL_SEARCH_HOTELINFO = "BookingEngineService_Hotel/hotelservice.svc/rest/GetHotelInfo/";
	private  String URL_SEARCH_ROOMS = "BookingEngineService_Hotel/hotelservice.svc/rest/GetHotelRoom/";
	private  String URL_BLOCK_ROOMS = "BookingEngineService_Hotel/hotelservice.svc/rest/BlockRoom/";	
	private  String URL_BOOKING = "BookingEngineService_Hotel/hotelservice.svc/rest/Book/";
	private  String URL_CANCEL = "BookingEngineService_Hotel/hotelservice.svc/rest/SendChangeRequest/";
	private  String URL_CANCEL_STATUS = "BookingEngineService_Hotel/hotelservice.svc/rest/GetChangeRequestStatus/";
	//public static final String URL_BOOKING_SUMMARY = "http://tboapi.travelboutiqueonline.com/HotelAPI_V10/HotelService.svc";
*/	

	
	@Column(name="auth_url_hotel")
	private String authUrlHotel ;
	@Column(name="hotelUrl_search_hotel")
	private String hotelUrlSearchHotel; //URL_SEARCH_HOTELS ;
	@Column(name="hotelUrl_search_hotelInfo")
	private String hotelUrlSearchHotelInfo;//URL_SEARCH_HOTELINFO ;
	@Column(name="hotelUrl_search_rooms")
	private String hotelUrlSearchRooms; //URL_SEARCH_ROOMS ;
	@Column(name="hotelUrl_block_rooms")
	private String hotelUrlBlockRooms; //URL_BLOCK_ROOMS ;
	@Column(name="hotelUrl_booking")
	private String hotelUrlBooking; //URL_BOOKING ;
	@Column(name="hotelUrl_cancel")
	private String hotelUrlCancel; //URL_CANCEL ;
	@Column(name="hotelUrl_cancel_status")
	private String hotelUrlCancelStatus; //URL_CANCEL_STATUS ;
	@Column(name="hotelUrl_booking_summary")
	private String hotelUrlBookingSummary; //URL_BOOKING_SUMMARY ;
	
	
	
	
	@Column(name="password_hotel")
	private String passwordHotel ;
	@Column(name="user_name_hotel")
	private String userNameHotel ;
	@Column(name="id_hotel")
	private int idHotel ;

	@Column(name="flight_vendorratetype")
	private String flightVendorRateType;

	@Column(name="flight_vendorpercentage")	
	private BigDecimal flightVendorPercentage;

	@Column(name="hotel_vendorratetype")
	private String hotelVendorRateType;

	@Column(name="hotel_vendorpercentage")	
	private BigDecimal hotelVendorPercentage;



	public String getFlightVendorRateType() {
		return flightVendorRateType;
	}
	public void setFlightVendorRateType(String flightVendorRateType) {
		this.flightVendorRateType = flightVendorRateType;
	}
	public BigDecimal getFlightVendorPercentage() {
		return flightVendorPercentage;
	}
	public void setFlightVendorPercentage(BigDecimal flightVendorPercentage) {
		this.flightVendorPercentage = flightVendorPercentage;
	}
	public String getHotelVendorRateType() {
		return hotelVendorRateType;
	}
	public void setHotelVendorRateType(String hotelVendorRateType) {
		this.hotelVendorRateType = hotelVendorRateType;
	}
	public BigDecimal getHotelVendorPercentage() {
		return hotelVendorPercentage;
	}
	public void setHotelVendorPercentage(BigDecimal hotelVendorPercentage) {
		this.hotelVendorPercentage = hotelVendorPercentage;
	}

	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getPasswordHotel() {
		return passwordHotel;
	}
	public void setPasswordHotel(String passwordHotel) {
		this.passwordHotel = passwordHotel;
	}
	public String getUserNameHotel() {
		return userNameHotel;
	}
	public void setUserNameHotel(String userNameHotel) {
		this.userNameHotel = userNameHotel;
	}
	public int getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getEnduserIp() {
		return enduserIp;
	}
	public void setEnduserIp(String enduserIp) {
		this.enduserIp = enduserIp;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/*public String getAuthUrl() {
		return authUrl;
	}
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}*/
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getApiCurrency() {
		return apiCurrency;
	}
	public void setApiCurrency(String apiCurrency) {
		this.apiCurrency = apiCurrency;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTboFlightEnvironment() {
		return tboFlightEnvironment;
	}
	public void setTboFlightEnvironment(String tboFlightEnvironment) {
		this.tboFlightEnvironment = tboFlightEnvironment;
	}
	public String getTboHotelEnvironment() {
		return tboHotelEnvironment;
	}
	public void setTboHotelEnvironment(String tboHotelEnvironment) {
		this.tboHotelEnvironment = tboHotelEnvironment;
	}
	public String getGeneralUrlAgencyBalance() {
		return generalUrlAgencyBalance;
	}
	public void setGeneralUrlAgencyBalance(String generalUrlAgencyBalance) {
		this.generalUrlAgencyBalance = generalUrlAgencyBalance;
	}
	public String getAuthUrlFlight() {
		return authUrlFlight;
	}
	public void setAuthUrlFlight(String authUrlFlight) {
		this.authUrlFlight = authUrlFlight;
	}
	public String getFlightUrlSearch() {
		return flightUrlSearch;
	}
	public void setFlightUrlSearch(String flightUrlSearch) {
		this.flightUrlSearch = flightUrlSearch;
	}
	public String getFlightUrlFarerule() {
		return flightUrlFarerule;
	}
	public void setFlightUrlFarerule(String flightUrlFarerule) {
		this.flightUrlFarerule = flightUrlFarerule;
	}
	public String getFlightUrlAirprice() {
		return flightUrlAirprice;
	}
	public void setFlightUrlAirprice(String flightUrlAirprice) {
		this.flightUrlAirprice = flightUrlAirprice;
	}
	public String getFlightUrlSsr() {
		return flightUrlSsr;
	}
	public void setFlightUrlSsr(String flightUrlSsr) {
		this.flightUrlSsr = flightUrlSsr;
	}
	public String getFlightUrlBook() {
		return flightUrlBook;
	}
	public void setFlightUrlBook(String flightUrlBook) {
		this.flightUrlBook = flightUrlBook;
	}
	public String getFlightUrlTicket() {
		return flightUrlTicket;
	}
	public void setFlightUrlTicket(String flightUrlTicket) {
		this.flightUrlTicket = flightUrlTicket;
	}
	public String getFlightUrlReleasePnr() {
		return flightUrlReleasePnr;
	}
	public void setFlightUrlReleasePnr(String flightUrlReleasePnr) {
		this.flightUrlReleasePnr = flightUrlReleasePnr;
	}

	public String getFlightUrlCancellationStatus() {
		return flightUrlCancellationStatus;
	}
	public void setFlightUrlCancellationStatus(String flightUrlCancellationStatus) {
		this.flightUrlCancellationStatus = flightUrlCancellationStatus;
	}
	public String getFlightUrlPriceRbd() {
		return flightUrlPriceRbd;
	}
	public void setFlightUrlPriceRbd(String flightUrlPriceRbd) {
		this.flightUrlPriceRbd = flightUrlPriceRbd;
	}
	public String getFlightUrlCalendarSearch() {
		return flightUrlCalendarSearch;
	}
	public void setFlightUrlCalendarSearch(String flightUrlCalendarSearch) {
		this.flightUrlCalendarSearch = flightUrlCalendarSearch;
	}
	public String getAuthUrlHotel() {
		return authUrlHotel;
	}
	public void setAuthUrlHotel(String authUrlHotel) {
		this.authUrlHotel = authUrlHotel;
	}
	public String getHotelUrlSearchHotel() {
		return hotelUrlSearchHotel;
	}
	public void setHotelUrlSearchHotel(String hotelUrlSearchHotel) {
		this.hotelUrlSearchHotel = hotelUrlSearchHotel;
	}
	public String getHotelUrlSearchHotelInfo() {
		return hotelUrlSearchHotelInfo;
	}
	public void setHotelUrlSearchHotelInfo(String hotelUrlSearchHotelInfo) {
		this.hotelUrlSearchHotelInfo = hotelUrlSearchHotelInfo;
	}
	public String getHotelUrlSearchRooms() {
		return hotelUrlSearchRooms;
	}
	public void setHotelUrlSearchRooms(String hotelUrlSearchRooms) {
		this.hotelUrlSearchRooms = hotelUrlSearchRooms;
	}
	public String getHotelUrlBlockRooms() {
		return hotelUrlBlockRooms;
	}
	public void setHotelUrlBlockRooms(String hotelUrlBlockRooms) {
		this.hotelUrlBlockRooms = hotelUrlBlockRooms;
	}
	public String getHotelUrlBooking() {
		return hotelUrlBooking;
	}
	public void setHotelUrlBooking(String hotelUrlBooking) {
		this.hotelUrlBooking = hotelUrlBooking;
	}
	public String getHotelUrlCancel() {
		return hotelUrlCancel;
	}
	public void setHotelUrlCancel(String hotelUrlCancel) {
		this.hotelUrlCancel = hotelUrlCancel;
	}
	public String getHotelUrlCancelStatus() {
		return hotelUrlCancelStatus;
	}
	public void setHotelUrlCancelStatus(String hotelUrlCancelStatus) {
		this.hotelUrlCancelStatus = hotelUrlCancelStatus;
	}
	public String getHotelUrlBookingSummary() {
		return hotelUrlBookingSummary;
	}
	public void setHotelUrlBookingSummary(String hotelUrlBookingSummary) {
		this.hotelUrlBookingSummary = hotelUrlBookingSummary;
	}
	public String getFlightUrlGetBookingDetail() {
		return flightUrlGetBookingDetail;
	}
	public void setFlightUrlGetBookingDetail(String flightUrlGetBookingDetail) {
		this.flightUrlGetBookingDetail = flightUrlGetBookingDetail;
	}
	public String getFlightUrlCancellation() {
		return flightUrlCancellation;
	}
	public void setFlightUrlCancellation(String flightUrlCancellation) {
		this.flightUrlCancellation = flightUrlCancellation;
	}
	
	public String getCompanyName() {
		if(companyId>0){
			Company comapany=new CompanyDAO().getNewCompanyId(companyId);
			if(comapany!=null)
				companyName=comapany.getCompanyname();
		}
		else{
			companyName="-";
		}
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
} 