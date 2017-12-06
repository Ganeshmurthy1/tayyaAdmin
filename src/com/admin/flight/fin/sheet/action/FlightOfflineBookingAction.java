 package com.admin.flight.fin.sheet.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.api.provider.model.ApiProviderPaymentTransaction;
import com.admin.api.provider.model.ApiProviderPaymentTransactionDetail;
import com.admin.common.config.model.FlightDomesticServiceTaxConfig;
import com.admin.common.config.model.FlightInternationalServiceTaxConfig;
import com.admin.common.util.CommonUtilSession;
import com.admin.enums.utility.CommonBookingStatusEnum;
import com.admin.enums.utility.IndianUnionTerritories;
import com.admin.flight.fin.sheet.Dao.FlightOfflineBooingDao;
import com.admin.flight.fin.sheet.Dao.FlightTravelRequestDao;
import com.admin.flight.fin.sheet.model.FlightServiceTax;
import com.admin.flight.fin.sheet.model.FlightTravelRequest;
import com.admin.flight.fin.sheet.model.FlightTravelRequestQuotation;
import com.admin.hotel.fin.sheet.Dao.HotelOfflineBookingDao;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.admin.payment.card.details.PaymentCardDetailsConfig;
import com.admin.payment.card.details.PaymentCardDetailsDao;
import com.common.dsr.CommonDsrDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.InvoiceDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.common.model.PaymentTransactionDetail;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderRowGstTax;
import com.lintas.admin.flight.model.FlightOrderRowServiceTax;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CompanyConfigTax;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.model.InvoiceData;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.vo.CutandPayModel;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.lintas.utility.GetNewRmConfigDetail;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.admin.orderrow.rm.structure.FlightOrderRowRmConfigStruct;
import com.tayyarah.gst.model.FlightGstTax;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;


public class FlightOfflineBookingAction extends ActionSupport implements ModelDriven<FlightOrderRow>,SessionAware{
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightOfflineBookingAction.class);
	private static final long serialVersionUID = 1L;
	FlightOrderRow flightOrderRow=new FlightOrderRow();
	OrderCustomer orderCustomer=new OrderCustomer();

	List<Country> countryList=null;
	private long flightQuotationRequestId;
	FlightOfflineBooingDao flightOfflineBooingDao=new FlightOfflineBooingDao();
	FlightTravelRequestDao flightTravelRequestDao=new FlightTravelRequestDao();
	FlightTravelRequestQuotation flightQuotation=new FlightTravelRequestQuotation();
	private PaymentTransaction paymentTransaction=new PaymentTransaction();
	private ApiProviderPaymentTransaction apiProviderPaymentTransaction=new ApiProviderPaymentTransaction();
	private List<ApiProviderPaymentTransactionDetail> apiProviderpaymentTransactionDetailList=new  ArrayList<>();
	private List<PaymentTransactionDetail> paymentTransactionDetailList=new  ArrayList<>();
	SessionMap<String, Object> sessionMap=null;
	private List<Airlinelist> airlineList;
	private Long  flightOrderId;
	private BigDecimal managementFeeForBookingIntManagemtFee =new BigDecimal("0.00");;
	private BigDecimal managementFeeForBookingDomManagemtFee =new BigDecimal("0.00");;
	PaymentCardDetailsDao paymentCardDetailsDao=new PaymentCardDetailsDao();
	private UserWallet userWallet=new UserWallet();
	private InvoiceData flightOfflineVoucherData=null;
	HotelOfflineBookingDao hotelOfflineBookingDao=new HotelOfflineBookingDao();
	private List<FlightOrderTripDetail> flightOrderTripDetailList;
	private List<FlightOrderCustomer> flightOrderCustomerList;
	private String supplierPaymentType=null;
	private String customerPaymentType=null;
	private List<PaymentCardDetailsConfig> paymentCardList=null;
	private List<FlightOrderCustomerPriceBreakup> flightOrderCustomerPriceBreakupList;
	UserDAO userDao=new UserDAO();
	private List<ApiProvider> apiProviders;
	private FlightOrderRowServiceTax flightOrderRowServiceTax;
	ApiProviderDao apiProviderDao =new ApiProviderDao(); 
	BigDecimal managementFeeForBooking=new BigDecimal("0.00");
	private FlightTravelRequest flightTravelRequest=new FlightTravelRequest();
	List<String> domesticOrInternationalCountryNameList=new ArrayList<String>();
	RmConfigDao rmConfigDao = new RmConfigDao();
	TripRequestDao tripRequestDao = new TripRequestDao();
	private Long tripId;
	private Long passengerCountDynamic;
	RmConfigModel rmConfigModel = new RmConfigModel();
	List<String> fieldName = new ArrayList<String>();
	RmConfigTripDetailsModel configTripDetailsModel = new RmConfigTripDetailsModel();
	List<FlightTravelRequestQuotation> flightRequestQuotationList=new ArrayList<FlightTravelRequestQuotation>();
	private Company parentCompany= new Company();
	List<String> travelRequestNoList=new ArrayList<String>();

	public String  goFlightBookingPage(){
		User sessionUser = (User)sessionMap.get("User");
		Company sessionCompany = (Company) sessionMap.get("Company");
		countryList=new CountryDao().getCountryList();
		setAirlineList(new CountryDao().getAirlineList());
		//apiProviders=apiProviderDao.fetchApiProviderList();
		List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
		setApiProviders(list);
		RmConfigDao rmConfigDao=new RmConfigDao();
		try{
			setRmConfigModel(rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid()));
			String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("") ?rmConfigModel.getDynamicFieldsData().split(","):null;
			if(manualStringFields.length>0){
			for(String oneField:manualStringFields){
				String fieldsName[]=oneField.split(":");
				fieldName.add(fieldsName[0]);
			}
			}
		}catch (Exception e) {
		}
		if(tripId != null){
			TripRequest tripRequest = tripRequestDao.getTripRequestById(tripId);
			configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
		}
		if(flightQuotationRequestId>0){
			flightQuotation=flightTravelRequestDao.getFlightTravelRequestQuotationDetails(flightQuotationRequestId);
			if(flightQuotation.getTripType().equals("R"))
				flightQuotation.setTripType("Round");
			else if(flightQuotation.getTripType().equals("M"))
				flightQuotation.setTripType("Multi");

			else
				flightQuotation.setTripType("One");
			try{
				List<String> domesticOrInternational=new ArrayList<String>();
				domesticOrInternational.add(flightQuotation.getOrigin().split(",")[1]);
				domesticOrInternational.add(flightQuotation.getDestination().split(",")[1]);
				setDomesticOrInternationalCountryNameList(domesticOrInternational);
			}catch (Exception e) {
				logger.info("error : Not verified international or domestic");
			}
			//added by basha
			boolean isIsInternational=CommonDsrDao.isDomesticOrInternational(flightQuotation.getDestination(),flightQuotation.getOrigin());
			CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());	
			if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null  && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")){
				if(isIsInternational)
				{
					if(newCompanyConfig!=null && newCompanyConfig.getFlightInternationalGstTaxConfig()!=null)
					{
						BigDecimal managementFee=new BigDecimal("0.00");
						managementFee=newCompanyConfig.getFlightInternationalGstTaxConfig().getManagementFee();
						if(managementFee!=null) 
							setManagementFeeForBooking(managementFee);

					}

				}
				else{
					if(newCompanyConfig!=null && newCompanyConfig.getFlightDomesticGstTaxConfig()!=null)
					{
						BigDecimal managementFee=new BigDecimal("0.00");
						managementFee=newCompanyConfig.getFlightDomesticGstTaxConfig().getManagementFee();
						if(managementFee!=null) 
							setManagementFeeForBooking(managementFee);

					}
				}


			}
			else{
				if(isIsInternational)
				{
					if(newCompanyConfig!=null && newCompanyConfig.getFlightInternationalServiceTaxConfig()!=null)
					{
						BigDecimal managementFee=new BigDecimal("0.00");
						managementFee=newCompanyConfig.getFlightInternationalServiceTaxConfig().getManagementFee();
						if(managementFee!=null) 
							setManagementFeeForBooking(managementFee);

					}

				}
				else{
					if(newCompanyConfig!=null && newCompanyConfig.getFlightDomesticServiceTaxConfig()!=null)
					{
						BigDecimal managementFee=new BigDecimal("0.00");
						managementFee=newCompanyConfig.getFlightDomesticServiceTaxConfig().getManagementFee();
						if(managementFee!=null) 
							setManagementFeeForBooking(managementFee);

					}
				}
			}
			//ended by basha
			if(flightQuotation.getTotalAmount()!=null) 
				flightQuotation.setTotalAmount(flightQuotation.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));

			paymentCardList=paymentCardDetailsDao.getAllPaymentCardDetailsList();
			setUserWallet(new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId()));
		}else{
			CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());	
			if(newCompanyConfig.getTaxtype()!=null){
				CompanyConfigTax companyConfigForManagementFee=new CompanyConfigDao().getConfigByComnpanyIdForB2ETaxes(sessionCompany.getCompanyid());
				if(companyConfigForManagementFee!=null){
					if(companyConfigForManagementFee.getGstTaxObj()!=null && companyConfigForManagementFee.getGstTaxObj().getTaxtype()!=null && !companyConfigForManagementFee.getGstTaxObj().getTaxtype().trim().equalsIgnoreCase("")){
						if(companyConfigForManagementFee.getGstTaxObj().getTaxtype().equalsIgnoreCase("GST")){
							setManagementFeeForBookingDomManagemtFee(companyConfigForManagementFee.getGstTaxObj().getFlightDomesticGstTaxConfig().getManagementFee());
							setManagementFeeForBookingIntManagemtFee(companyConfigForManagementFee.getGstTaxObj().getFlightInternationalGstTaxConfig().getManagementFee());
						}
						
					}
					else if(companyConfigForManagementFee.getServiceTaxObj()!=null && companyConfigForManagementFee.getServiceTaxObj().getTaxtype()!=null && !companyConfigForManagementFee.getServiceTaxObj().getTaxtype().trim().equalsIgnoreCase("")){
					 if(companyConfigForManagementFee.getServiceTaxObj().getTaxtype().equalsIgnoreCase("servicetax")){
						setManagementFeeForBookingDomManagemtFee(companyConfigForManagementFee.getServiceTaxObj().getFlightDomesticServiceTaxConfig().getManagementFee());
						setManagementFeeForBookingIntManagemtFee(companyConfigForManagementFee.getServiceTaxObj().getFlightInternationalServiceTaxConfig().getManagementFee());
					 }

					}/*else{
					setManagementFeeForBookingDomManagemtFee(companyConfigForManagementFee.getServiceTaxObj().getFlightDomesticServiceTaxConfig().getManagementFee());
					setManagementFeeForBookingIntManagemtFee(companyConfigForManagementFee.getServiceTaxObj().getFlightInternationalServiceTaxConfig().getManagementFee());

				}*/
				}

			}else{
				if(newCompanyConfig.getFlightDomesticServiceTaxConfig()!=null){
					if(newCompanyConfig.getFlightDomesticServiceTaxConfig().getManagementFee()!=null)
						setManagementFeeForBookingDomManagemtFee(newCompanyConfig.getFlightDomesticServiceTaxConfig().getManagementFee());
					else
						setManagementFeeForBookingDomManagemtFee(new BigDecimal("0.00"));
					if(newCompanyConfig.getFlightInternationalServiceTaxConfig().getManagementFee()!=null)
						setManagementFeeForBookingIntManagemtFee(newCompanyConfig.getFlightInternationalServiceTaxConfig().getManagementFee());
					else
						setManagementFeeForBookingIntManagemtFee(new BigDecimal("0.00"));
				}
				

			}
			/*RmConfigDao rmConfigDao=new RmConfigDao();
			try{
				setRmConfigModel(rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid()));
				String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("") ?rmConfigModel.getDynamicFieldsData().split(","):null;
				if(manualStringFields.length>0){
					for(String oneField:manualStringFields){
						if(!oneField.trim().equalsIgnoreCase("")){
							String fieldsName[]=oneField.split(":");
							fieldName.add(fieldsName[0]);
						}
						
					}
				}
			}catch (Exception e) {
			}
			if(tripId != null){
				TripRequest tripRequest = tripRequestDao.getTripRequestById(tripId);
				configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
			}*/
			setUserWallet(new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId()));
			return SUCCESS;
		}
		return SUCCESS;
	}
	public String insertFlightOrderBookingDetails() {
		User sessionUser=(User) sessionMap.get("User");
		Company sessionCompany=(Company) sessionMap.get("Company");
		TripRequest tripRequest = null; 
		setParentCompany(new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid()));
		PaymentTransactionDetail paymentTransactionDetail=new PaymentTransactionDetail();
		ApiProviderPaymentTransactionDetail apiProviderPaymentTransactionDetail=new ApiProviderPaymentTransactionDetail();
		User userNew=userDao.getUserByUserId(sessionUser.getId());
		String orderId = flightOrderRow.getOrderId();
		FlightTravelRequestQuotation flightTravelRequestQuotation=flightTravelRequestDao.getFlightTravelRequestQuotationDetails(flightQuotationRequestId);
		BigDecimal managementFee=flightOrderRow.getTempManagementFees()!=null?flightOrderRow.getTempManagementFees():new BigDecimal(0);
		/*managementFee=managementFee.multiply(new BigDecimal(flightOrderCustomerList.size()));*/
		BigDecimal convinenceFee=new BigDecimal("0.00");
		BigDecimal serviceOrGstTax=new BigDecimal("0.00");
		CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());


		/*if(flightTravelRequestQuotation.getOrderRowId()==null){
			flightTravelRequestQuotation.setReturnOrderRowId(flightOrderRow.getId());

		}*/
		String split[]=null;
		String split1[]=null;
		BigDecimal totBaseFare=new BigDecimal("0");
		BigDecimal totTax=new BigDecimal("0");
		BigDecimal totMarkup=new BigDecimal("0");
		flightOrderRow.setCreatedAt(new Timestamp(new Date().getTime()));
		orderCustomer.setCreatedAt(flightOrderRow.getCreatedAt());
		orderCustomer.setCompanyId(sessionCompany.getCompanyid()); 
		orderCustomer.setBookingType(CommonBookingStatusEnum.FLIGHT.getMessage());
		orderCustomer.setConfigId(newCompanyConfig.getConfig_id());
		//added  by basha
		orderCustomer.setCreatedByUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		//ENDED BY BASHA
		flightOrderRow.setFlightCustomer(orderCustomer);
		flightOrderRow.setCreatedBy(sessionUser.getUsername());
		flightOrderRow.setCompanyId(String.valueOf(sessionUser.getCompanyid()));
		flightOrderRow.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId()));
		flightOrderRow.setBaseCurrency("INR");
		if(flightTravelRequestQuotation.getFlightTravelRequest()!=null){
			flightOrderRow.setBookingCurrency(flightTravelRequestQuotation.getFlightTravelRequest().getCurrency());
			//flightOrderRow.setBookingClass(flightTravelRequestQuotation.getBookingClassPrefer());
		}
		/*flightOrderRow.setOrderId(RandomConfigurationNumber.generateFlightOfflineBookingNumber());*/
		//flightOrderRow.setInvoiceNo(RandomConfigurationNumber.generateFlightOfflineInvoiceNumber());
		flightOrderRow.setPassengerCount(getFlightOrderCustomerList().size());
		flightOrderRow.setAirline(flightTravelRequestQuotation.getAirline());
		//flightOrderRow.setBookingDate(DateConversion.convertDateToStringDate(flightOrderRow.getCreatedAt()));
		flightOrderRow.setTripType(flightTravelRequestQuotation.getTripType());
		//flightOrderRow.setStatusAction("Confirmed");
		flightOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
		flightOrderRow.setBaseToBookingExchangeRate(new BigDecimal(1));
		flightOrderRow.setDepartureDate(DateConversion.convertDDMMYYtoYYMMDD(flightTravelRequestQuotation.getTransDepartureDate()));
		flightOrderRow.setArrivalDate(DateConversion.convertDDMMYYtoYYMMDD(flightTravelRequestQuotation.getTransArrivalDate()));
		/*if(flightOrderRow.getTripType().equals("R") && StringUtils.isNotBlank(flightTravelRequestQuotation.getTransArrivalDate())){
			flightOrderRow.setArrivalDate(DateConversion.convertDDMMYYtoYYMMDD(flightTravelRequestQuotation.getTransArrivalDate()));
		}*/
		BigDecimal yQTax= new BigDecimal(0);
		BigDecimal JNTax= new BigDecimal(0);
		if(getFlightOrderCustomerPriceBreakupList()!=null && getFlightOrderCustomerPriceBreakupList().size()>0){
			for(FlightOrderCustomerPriceBreakup flightOrderCustomerPriceBreakup:getFlightOrderCustomerPriceBreakupList())
			{

				totMarkup=totMarkup.add(new BigDecimal(flightOrderCustomerPriceBreakup.getMarkup()));
				totBaseFare = flightOrderCustomerPriceBreakup.getBaseFare()!=null ?totBaseFare.add(flightOrderCustomerPriceBreakup.getBaseFare()):totBaseFare;
				yQTax = flightOrderCustomerPriceBreakup.getYQTax()!=null ?yQTax.add(flightOrderCustomerPriceBreakup.getYQTax()):yQTax;
				totTax=totTax.add(flightOrderCustomerPriceBreakup.getTax())
						.add(flightOrderCustomerPriceBreakup.getYQTax()).add(flightOrderCustomerPriceBreakup.getYRTax()).add(flightOrderCustomerPriceBreakup.getAYTax()).add(flightOrderCustomerPriceBreakup.getF2Tax())
						.add(flightOrderCustomerPriceBreakup.getF6Tax()).add(flightOrderCustomerPriceBreakup.getG1Tax()).add(flightOrderCustomerPriceBreakup.getINTax()).add(flightOrderCustomerPriceBreakup.getK3Tax())
						.add(flightOrderCustomerPriceBreakup.getPSFTax()).add(flightOrderCustomerPriceBreakup.getUDFTax()).add(flightOrderCustomerPriceBreakup.getUSTax()).add(flightOrderCustomerPriceBreakup.getWOTax())
						.add(flightOrderCustomerPriceBreakup.getXATax()).add(flightOrderCustomerPriceBreakup.getXFTax()).add(flightOrderCustomerPriceBreakup.getXYTax()).add(flightOrderCustomerPriceBreakup.getYCTax())
						.add(flightOrderCustomerPriceBreakup.getZRTax()).add(JNTax);
			}
		}
		flightOrderRow.setExtrabaggageprice(new BigDecimal("0.00"));
		flightOrderRow.setExtramealprice(new BigDecimal("0.00"));
		flightOrderRow.setPublishedDiscount(new BigDecimal("0.00"));
		flightOrderRow.setSystemDiscount(new BigDecimal("0.00"));
		flightOrderRow.setSupplierDiscount(new BigDecimal("0.00"));
		flightOrderRow.setPrice(totBaseFare);
		flightOrderRow.setTaxes(totTax);
		//flightOrderRow.setManagementFee(flightOrderRow.getTempManagementFees());
		flightOrderRow.setMarkUp(totMarkup);
		flightOrderRow.setFinalPrice(totBaseFare.add(totTax).add(totMarkup));
		flightOrderRow.setGst_on_markup(new BigDecimal("0.00"));
		flightOrderRow.setGstOnFlights(new BigDecimal("0.00"));
		flightOrderRow.setProcessingFees(new BigDecimal("0.00"));
		String origin=null;
		String destination=null;
		if(flightTravelRequestQuotation.getOrigin()!=null){
			split=flightTravelRequestQuotation.getOrigin().split(",");
			if(split.length >= 4) 
				origin=split[3].substring(1, 4);
			 else if(split.length >=3) 
				origin=split[2].substring(1, 4);
			else if(split.length >=2) 
				origin=split[1].substring(1, 4);
			else if(split.length >=1) 
				origin=split[0].substring(1, 4);
			 

		}
		if(flightTravelRequestQuotation.getDestination()!=null){
			split1=flightTravelRequestQuotation.getDestination().split(",");
			if(split1.length >= 4) 
				destination=split1[3].substring(1, 4);
			else if(split1.length >=3) 
				destination=split1[2].substring(1, 4);
			else if(split1.length >=2) 
				destination=split1[1].substring(1, 4);
			else if(split1.length >=1) 
				destination=split1[0].substring(1, 4);
		 
			 
		}
		boolean isIsInternational=CommonDsrDao.isDomesticOrInternational(destination,origin);

		FlightGstTax flightGstTax=null;
		FlightServiceTax flightServiceTax=null;


		if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")){
			FlightOrderRowGstTax flightOrderRowGstTax = new FlightOrderRowGstTax();
			if(isIsInternational)
			{
				if(newCompanyConfig!=null && newCompanyConfig.getCompanyConfigType().isB2E() && newCompanyConfig.getFlightInternationalGstTaxConfig()!=null)
				{
					flightOrderRowGstTax.setApplicableFare(newCompanyConfig.getFlightInternationalGstTaxConfig().getApplicableFare());
					flightOrderRowGstTax.setCGST(newCompanyConfig.getFlightInternationalGstTaxConfig().getCGST());
					flightOrderRowGstTax.setConvenienceFee(newCompanyConfig.getFlightInternationalGstTaxConfig().getConvenienceFee());
					flightOrderRowGstTax.setIGST(newCompanyConfig.getFlightInternationalGstTaxConfig().getIGST());					
					flightOrderRowGstTax.setSGST(newCompanyConfig.getFlightInternationalGstTaxConfig().getSGST());
					flightOrderRowGstTax.setUGST(newCompanyConfig.getFlightInternationalGstTaxConfig().getUGST());
					convinenceFee=newCompanyConfig.getFlightInternationalGstTaxConfig().getConvenienceFee();
				}
			}
			else{
				if(newCompanyConfig!=null  &&  newCompanyConfig.getCompanyConfigType().isB2E() && newCompanyConfig.getFlightDomesticGstTaxConfig()!=null)
				{

					flightOrderRowGstTax.setApplicableFare(newCompanyConfig.getFlightDomesticGstTaxConfig().getApplicableFare());
					flightOrderRowGstTax.setCGST(newCompanyConfig.getFlightDomesticGstTaxConfig().getCGST());
					flightOrderRowGstTax.setConvenienceFee(newCompanyConfig.getFlightDomesticGstTaxConfig().getConvenienceFee());
					flightOrderRowGstTax.setIGST(newCompanyConfig.getFlightDomesticGstTaxConfig().getIGST());					
					flightOrderRowGstTax.setSGST(newCompanyConfig.getFlightDomesticGstTaxConfig().getSGST());
					flightOrderRowGstTax.setUGST(newCompanyConfig.getFlightDomesticGstTaxConfig().getUGST());
					convinenceFee=newCompanyConfig.getFlightDomesticGstTaxConfig().getConvenienceFee();
				}

			}
			flightOrderRowGstTax.setCreatedAt(new Timestamp(new Date().getTime()));
			flightOrderRowGstTax.setManagementFee(managementFee);
			flightOrderRowGstTax.setConvenienceFee(convinenceFee);
			flightGstTax=getFlightGSTTax(flightOrderRowGstTax, sessionCompany, getParentCompany(),flightOrderRow);
			flightOrderRowGstTax.setTotalGst(flightGstTax.getTotalTax());
			serviceOrGstTax=flightGstTax.getTotalGstAmount();
			flightOrderRow.setFlightOrderRowGstTax(flightOrderRowGstTax);
			flightOrderRow.setGstOnFlights(serviceOrGstTax);
		}else{

			if(isIsInternational)
			{
				if(newCompanyConfig!=null && newCompanyConfig.getCompanyConfigType().isB2E() && newCompanyConfig.getFlightInternationalServiceTaxConfig()!=null)
				{
					FlightOrderRowServiceTax flightOrderRowServiceTax=new FlightOrderRowServiceTax();
					flightOrderRowServiceTax.setApplicableFare(newCompanyConfig.getFlightInternationalServiceTaxConfig().getApplicableFare());
					flightOrderRowServiceTax.setBasicTax(newCompanyConfig.getFlightInternationalServiceTaxConfig().getBasicTax());
					flightOrderRowServiceTax.setConvenienceFee(newCompanyConfig.getFlightInternationalServiceTaxConfig().getConvenienceFee());
					flightOrderRowServiceTax.setCreatedAt(newCompanyConfig.getFlightInternationalServiceTaxConfig().getCreatedAt());
					flightOrderRowServiceTax.setKrishiKalyanCess(newCompanyConfig.getFlightInternationalServiceTaxConfig().getKrishiKalyanCess());
					//flightOrderRowServiceTax.setManagementFee(newCompanyConfig.getFlightInternationalServiceTaxConfig().getManagementFee());
					flightOrderRowServiceTax.setManagementFee(managementFee);
					flightOrderRowServiceTax.setSwatchBharathCess(newCompanyConfig.getFlightInternationalServiceTaxConfig().getSwatchBharathCess());
					flightOrderRowServiceTax.setTotalTax(newCompanyConfig.getFlightInternationalServiceTaxConfig().getTotalTax());
					flightOrderRowServiceTax.setUpdatedAt(newCompanyConfig.getFlightInternationalServiceTaxConfig().getUpdatedAt());
					convinenceFee=newCompanyConfig.getFlightInternationalServiceTaxConfig().getConvenienceFee();
					flightOrderRow.setFlightOrderRowServiceTax(flightOrderRowServiceTax);
				}
				//flightOrderRow.setInternationalDestination(isIsInternational);

			}
			else{
				if(newCompanyConfig!=null  &&  newCompanyConfig.getCompanyConfigType().isB2E() && newCompanyConfig.getFlightDomesticServiceTaxConfig()!=null)
				{
					FlightOrderRowServiceTax flightOrderRowServiceTax=new FlightOrderRowServiceTax();
					flightOrderRowServiceTax.setApplicableFare(newCompanyConfig.getFlightDomesticServiceTaxConfig().getApplicableFare());
					flightOrderRowServiceTax.setBasicTax(newCompanyConfig.getFlightDomesticServiceTaxConfig().getBasicTax());
					flightOrderRowServiceTax.setConvenienceFee(newCompanyConfig.getFlightDomesticServiceTaxConfig().getConvenienceFee());
					flightOrderRowServiceTax.setCreatedAt(newCompanyConfig.getFlightDomesticServiceTaxConfig().getCreatedAt());
					flightOrderRowServiceTax.setKrishiKalyanCess(newCompanyConfig.getFlightDomesticServiceTaxConfig().getKrishiKalyanCess());
					flightOrderRowServiceTax.setManagementFee(managementFee);
					flightOrderRowServiceTax.setSwatchBharathCess(newCompanyConfig.getFlightDomesticServiceTaxConfig().getSwatchBharathCess());
					flightOrderRowServiceTax.setTotalTax(newCompanyConfig.getFlightDomesticServiceTaxConfig().getTotalTax());
					flightOrderRowServiceTax.setUpdatedAt(newCompanyConfig.getFlightDomesticServiceTaxConfig().getUpdatedAt());
					convinenceFee=newCompanyConfig.getFlightDomesticServiceTaxConfig().getConvenienceFee();
					flightOrderRow.setFlightOrderRowServiceTax(flightOrderRowServiceTax);
				}
				//flightOrderRow.setInternationalDestination(false);
			}
			flightServiceTax= getFlightServiceTax(totBaseFare, yQTax, newCompanyConfig, isIsInternational,managementFee);
			flightOrderRow.setServiceTax(flightServiceTax.getTotalServiceTax());
			serviceOrGstTax=flightServiceTax.getTotalServiceTax();
		}

		flightOrderRow.setConfigId(Integer.toString(newCompanyConfig.getConfig_id()));
		flightOrderRow.setBookingMode(CommonBookingStatusEnum.BOOKING_MODE_OFFLINE.getMessage());

		if(flightGstTax==null) 
			flightOrderRow.setGstOnFlights(new BigDecimal("0.00"));
		if(flightServiceTax==null) 
			flightOrderRow.setServiceTax(new BigDecimal("0.00"));

		BigDecimal finalPriceAfterTax = flightOrderRow.getFinalPrice();
		if(flightServiceTax!=null)
		{
			if(flightServiceTax.getTotalServiceTax()!=null)
				finalPriceAfterTax = finalPriceAfterTax.add(flightServiceTax.getBaseServicetax());
			if(flightServiceTax.getConvenienceFee()!=null)
				finalPriceAfterTax = finalPriceAfterTax.add(flightServiceTax.getConvenienceFee());
			finalPriceAfterTax = finalPriceAfterTax.add(flightServiceTax.getManagementFee());
			finalPriceAfterTax = finalPriceAfterTax.add(flightServiceTax.getKKC());
			if(flightServiceTax.getSBC()!=null)
				finalPriceAfterTax = finalPriceAfterTax.add(flightServiceTax.getSBC());
		}
		if(flightGstTax!=null)
		{
			if(flightGstTax.getTotalGstAmount()!=null)
				finalPriceAfterTax = finalPriceAfterTax.add(flightGstTax.getTotalGstAmount());
			if(convinenceFee!=null)
				finalPriceAfterTax = finalPriceAfterTax.add(convinenceFee);
			if(managementFee!=null)
				finalPriceAfterTax = finalPriceAfterTax.add(managementFee);

		}

		User walletUser= userNew;
		if(userNew != null){
			if(sessionCompany.getCompanyRole()!=null && sessionCompany.getCompanyRole().isCorporate())
			{
				UserDAO userDAO = new UserDAO();
				walletUser = userDAO.getUserPasswordForLock(sessionCompany.getEmail());
			}
		}
		//flightOrderRow.setFinalPrice(finalPriceAfterTax);
		//flightOrderRow.setTaxes(totTax);
		RmConfigModel  rmConfigModel=rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid());
		 if(rmConfigModel!=null){
		 FlightOrderRowRmConfigStruct flightOrderRowRmConfigStruct=new FlightOrderRowRmConfigStruct();
		 flightOrderRowRmConfigStruct.setRmDynamicData(rmConfigModel.getDynamicFieldsData());
		 flightOrderRow.setFlightOrderRowRmConfigStruct(flightOrderRowRmConfigStruct);
		 }
		 
		 flightOrderRow.setOrigin(origin);
		 flightOrderRow.setDestination(destination); 
		 
		FlightOrderRow flightOrderRowNew=flightOfflineBooingDao.insertFlightOrderRowInfo(flightOrderRow);
 
		if(flightOrderRowNew!=null){
			if(getFlightOrderCustomerList()!=null && getFlightOrderCustomerList().size()>0){
				for(int i=0;i<getFlightOrderCustomerList().size();i++){
					FlightOrderCustomer flightOrderCustomer=getFlightOrderCustomerList().get(i);
					flightOrderCustomer.setFlightCustomer(flightOrderRowNew.getFlightCustomer());
					flightOrderCustomer.setFlightOrderRow(flightOrderRowNew);
					flightOrderCustomer.setCreatedAt(new Timestamp(new Date().getTime()));
					if(flightOrderCustomer.getPassport_expiryDate()!= null && !flightOrderCustomer.getPassport_expiryDate().equals(""))
						flightOrderCustomer.setPassportExpiryDate(DateConversion.StringToDate(flightOrderCustomer.getPassport_expiryDate()));
					if(flightOrderCustomer.getPassport_issuedDate()!= null && !flightOrderCustomer.getPassport_issuedDate().equals(""))
						flightOrderCustomer.setPassportIssuedDate(DateConversion.StringToDate(flightOrderCustomer.getPassport_issuedDate()));
					RmConfigTripDetailsModel  rmTripDetailsModel=flightOrderCustomer.getRmConfigTripDetailsModel();
					 if(rmTripDetailsModel!=null){ 
						rmTripDetailsModel.setOrderId(orderId);
						rmTripDetailsModel.setOrdertype("Flight");
						flightOrderCustomer.setRmConfigTripDetailsModel(rmTripDetailsModel);
					 } 
					flightOrderCustomer.setPaxId(RandomConfigurationNumber.generateRandomPaxID());
					flightOfflineBooingDao.insertFlightOrderCustomerInfo(flightOrderCustomer);
					if(getFlightOrderCustomerPriceBreakupList()!=null && getFlightOrderCustomerPriceBreakupList().size()>0){
						FlightOrderCustomerPriceBreakup priceBreakup=getFlightOrderCustomerPriceBreakupList().get(i);
						priceBreakup.setFlightCustomer(flightOrderRowNew.getFlightCustomer());
						priceBreakup.setFlightOrderRow(flightOrderRowNew);
						priceBreakup.setDescription(flightOrderCustomer.getPassengerTypeCode());
						priceBreakup.setTax(priceBreakup.getTax().add(priceBreakup.getYQTax()).add(priceBreakup.getYRTax()).add(priceBreakup.getAYTax()).add(priceBreakup.getF2Tax())
								.add(priceBreakup.getF6Tax()).add(priceBreakup.getG1Tax()).add(priceBreakup.getINTax()).add(priceBreakup.getK3Tax())
								.add(priceBreakup.getPSFTax()).add(priceBreakup.getUDFTax()).add(priceBreakup.getUSTax()).add(priceBreakup.getWOTax())
								.add(priceBreakup.getXATax()).add(priceBreakup.getXFTax()).add(priceBreakup.getXYTax()).add(priceBreakup.getYCTax())
								.add(priceBreakup.getZRTax()).add(JNTax));
						priceBreakup.setTotal(priceBreakup.getTax().add(priceBreakup.getBaseFare()));
						priceBreakup.setJNTax(JNTax);
						flightOfflineBooingDao.insertFlightOrderCustomerPriceBreakupInfo(priceBreakup);
					}
				}
			}

			if(flightOrderRowNew!=null){
				flightOrderRowNew.setInvoiceNo(RandomConfigurationNumber.generateFlightOfflineInvoiceNumber(flightOrderRowNew.getId()));
				try {
					flightOrderRowNew.setFlightOrderRowCommissionList(FlightOrderDao.getCommissionDetails(sessionCompany, flightOrderRowNew));
					flightOrderRowNew.setFlightOrderRowMarkupList(FlightOrderDao.getMarkupDetail(sessionCompany,flightOrderRowNew.getMarkUp(),flightOrderRowNew));
				} catch (Exception e) {
					logger.error("Flight markup & commission error "+e);			
				}
				Long orderIdTemp = flightOrderRowNew.getId()+1000;
				orderId =RandomConfigurationNumber.generateFlightOfflineInvoiceNumber(orderIdTemp);
				flightOrderRowNew.setOrderId(orderId);
				flightOrderRowNew=new FlightOrderDao().updateFlightOrderRowDetail(flightOrderRowNew);
			}
			Map<String, BigDecimal> markups =  new LinkedHashMap<>();
			if(flightOrderRowNew.getFlightOrderRowMarkupList()!=null && flightOrderRowNew.getFlightOrderRowMarkupList().size()>0)
			{
				if(sessionCompany.getCompanyRole().isSuperUser())
				{
					//					markups.put(String.valueOf(sessionCompany.getCompanyid()), insuranceOrderRowNew.getMarkUpamount());
					markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
				}
				else
				{
					for(FlightOrderRowMarkup flightOrderRowMarkup :flightOrderRowNew.getFlightOrderRowMarkupList())
					{
						markups.put(flightOrderRowMarkup.getCompanyId(),flightOrderRowMarkup.getMarkUp());
					}
				}

			}

			List<Company> companyListBottomToTop= new LinkedList<>();
			List<User> userListBottomToTop= new LinkedList<>();
			Map<Integer, CutandPayModel> cutAndPayUserMap = new LinkedHashMap<>();
			FlightOrderDao  flightOrderDao = new FlightOrderDao();
			CompanyDAO companyDAO = new CompanyDAO();
			boolean bookTicket=false;
			if(true)
			{
				try {
					companyListBottomToTop = CommonUtil.getParentCompanyBottomToTop(sessionCompany.getCompanyid(),companyDAO);
					if(companyListBottomToTop!=null && companyListBottomToTop.size()>0)
					{
						userListBottomToTop = CommonUtil.getUsersAllWithUserModeBottomToTop(companyListBottomToTop,companyDAO,walletUser);
					}
					cutAndPayUserMap = CommonUtil.getCutandPayModelUsersFlight(userListBottomToTop,markups,finalPriceAfterTax,walletUser,flightOrderRowNew);

					boolean checkBookingAmountEligibility= false;
					if(userListBottomToTop!=null && userListBottomToTop.size()>0)
					{
						for(User userInner : userListBottomToTop)
						{
							if(userInner.getAgentWallet()!=null)
							{
								if(cutAndPayUserMap!=null && cutAndPayUserMap.get(userInner.getId())!=null)
								{
									BigDecimal totalPayableAmount = cutAndPayUserMap.get(userInner.getId()).getPayableAmount();
									if(!flightOrderDao.checkWalletAmount(userInner.getId(), totalPayableAmount,new BigDecimal(0), new BigDecimal(0))){
										bookTicket = false;
										checkBookingAmountEligibility = false;
										break;
									}else{
										checkBookingAmountEligibility = true;
									}
								}
							}
						}	
					}	

					if(checkBookingAmountEligibility)
					{
						Map<Integer,Boolean> userMapBottomToTop= new LinkedHashMap<>();
						if(userListBottomToTop!=null && userListBottomToTop.size()>0)
						{
							for(User userInner : userListBottomToTop)
							{
								if(userInner.getAgentWallet()!=null)
								{
									if(cutAndPayUserMap!=null && cutAndPayUserMap.get(userInner.getId())!=null)
									{
										BigDecimal totalPayableAmount = cutAndPayUserMap.get(userInner.getId()).getPayableAmount();
										if(flightOrderDao.checkWalletAmount(userInner.getId(), totalPayableAmount,new BigDecimal(0), new BigDecimal(0)))
										{		
											FlightOrderDao.deductUserWallet(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.FLIHT_REMARKS.getMessage(),orderId,flightOrderRowNew.getInvoiceNo());
											userMapBottomToTop.put(userInner.getId(),true);
										}
										else{
											if(userMapBottomToTop!=null && userMapBottomToTop.size()>0)
											{
												for(Entry<Integer,Boolean>  userMap :userMapBottomToTop.entrySet())
												{
													if(userMap.getValue())
													{
														totalPayableAmount = cutAndPayUserMap.get(userMap.getKey()).getPayableAmount();
														flightOrderDao.creditUserWalletAmountForBookingFailed(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.FLIGHT_FAILEDREMARKS.getMessage(),orderId,flightOrderRowNew.getInvoiceNo());
													}
												}
											}
											bookTicket = false;
											break;
										}
									}
									else{
										bookTicket = false;
									}
								}
							}
							bookTicket = true;
						}	
					}else{
						bookTicket = false;
					}
				}
				catch (Exception e) {
				}
			}

			if(!bookTicket){
				flightOrderRowNew.setPaymentStatus("Failed");
				flightOrderRowNew.setStatusAction("Failed");
				flightOrderRowNew.setPnr("0");
				flightOrderRowNew.setInvoiceNo(null);
				flightOrderRowNew=flightOrderDao.updateFlightOrderRowDetailPaymentStatus(flightOrderRowNew);
				addActionError(CommonBookingStatusEnum.WALLET_ERROR.getMessage());
				return ERROR;
			}else{
				flightOrderRowNew.setPaymentStatus("Success");
				flightOrderRowNew=flightOrderDao.updateFlightOrderRowDetailPaymentStatus(flightOrderRowNew);
			}

			if(getFlightOrderTripDetailList()!=null && getFlightOrderTripDetailList().size()>0){
				for(FlightOrderTripDetail flightOrderTripDetail:getFlightOrderTripDetailList()){

					flightOrderTripDetail.setTrips(getFlightOrderTripDetailList().size());
					if(flightOrderTripDetail.getClassOfService()==null){
						flightOrderTripDetail.setClassOfService(flightTravelRequestQuotation.getBookingClassPrefer());
					}
					flightOrderTripDetail.setCreatedAt(flightOrderRowNew.getCreatedAt());
					//flightOrderTripDetail.setOperatedByName(flightOrderRowNew.getAirline());
					flightOrderTripDetail.setTripType(flightOrderRowNew.getTripType());
					flightOrderTripDetail.setFlightOrderRow(flightOrderRowNew);
					if(flightOrderTripDetail.getOriginName()!=null){
						split=flightOrderTripDetail.getOriginName().split(",");
						if(split.length >= 4){
							flightOrderTripDetail.setOriginCode(split[3].substring(1, 4));
						}else{
							flightOrderTripDetail.setOriginCode(split[2].substring(1, 4));
						}
						flightOrderTripDetail.setOriginName(split[0].trim());


						//flightOrderTripDetail.setOriginCode(split[2].substring(1, 4));

					}
					if(flightOrderTripDetail.getDestinationName()!=null){
						split1=flightOrderTripDetail.getDestinationName().split(",");
						if(split1.length >= 4){
							flightOrderTripDetail.setDestinationCode(split1[3].substring(1, 4));
						}else{
							flightOrderTripDetail.setDestinationCode(split1[2].substring(1, 4));
						}
						//flightOrderTripDetail.setDestinationCode(split[2].substring(1, 4));
						flightOrderTripDetail.setDestinationName(split1[0].trim());
					}
					try{

						//flightOrderTripDetail.setArrivalTimestamp(DateConversion.StringDateTimeToDateTime(flightOrderTripDetail.getArrDate()+" "+flightOrderTripDetail.getArrTime()));
						String  arrivTime=flightOrderTripDetail.getArrTime();
						SimpleDateFormat format = new SimpleDateFormat("HH:mm");
						java.util.Date d1 =(java.util.Date)format.parse(arrivTime);

						java.sql.Time ppstime = new java.sql.Time(d1.getTime());
						flightOrderTripDetail.setArrivalTime(ppstime);
					}catch (ParseException e) {
						logger.info("parsing exception while parsing arrival time "+e);
						// TODO: handle exception
					}
					flightOrderTripDetail.setDepartureTime(DateConversion.StringDateTimeToDateTime(flightOrderTripDetail.getDepDate()+" "+flightOrderTripDetail.getDepTime()));
					flightOrderTripDetail.setDepartureDate(DateConversion.StringToDate(flightOrderTripDetail.getDepDate()));
					flightOrderTripDetail.setDepartureTimestamp(DateConversion.StringDateTimeToDateTime(flightOrderTripDetail.getDepDate()+" "+flightOrderTripDetail.getDepTime()));
					//if(flightOrderTripDetail.getDepartureDate()!=null && flightOrderRowNew.getTripType().equals("O") && StringUtils.isNotBlank(flightOrderTripDetail.getArrTime())){
					flightOrderTripDetail.setArrivalDate(DateConversion.StringToDate(flightOrderTripDetail.getArrDate()));
					//flightOrderTripDetail.setArrivalTimestamp(DateConversion.StringDateTimeToDateTime(flightOrderTripDetail.getArrDate()+" "+flightOrderTripDetail.getArrTime()));
					flightOrderTripDetail.setArrivalTimestamp(DateConversion.StringDateTimeToDateTime(flightOrderTripDetail.getArrDate()+" "+flightOrderTripDetail.getArrTime()));
					flightOrderTripDetail.setArrivalTime(DateConversion.StringDateTimeToDateTime(flightOrderTripDetail.getArrDate()+" "+flightOrderTripDetail.getArrTime()));
					//}
					if(flightOrderTripDetail.getFlightDuration()==null){

						flightOrderTripDetail.setFlightDuration("0");
					}
					//if(flightOrderTripDetail.getArrDate()!=null && flightOrderRowNew.getTripType().equals("R") && StringUtils.isNotBlank(flightOrderTripDetail.getArrDate()) && StringUtils.isNotBlank(flightOrderTripDetail.getArrTime())){
					//flightOrderTripDetail.setArrivalDate(DateConversion.StringToDate(flightOrderTripDetail.getArrDate()));
					//flightOrderTripDetail.setArrivalTimestamp(DateConversion.StringDateTimeToDateTime(flightOrderTripDetail.getArrDate()+" "+flightOrderTripDetail.getArrTime()));
					//flightOrderTripDetail.setArrivalTime(DateConversion.StringDateTimeToDateTime(flightOrderTripDetail.getArrDate()+" "+flightOrderTripDetail.getArrTime()));
					//}
					flightOfflineBooingDao.insertFlightOrderTripDetailInfo(flightOrderTripDetail);
				}
			} 
			PaymentCardDetailsConfig   paymentCardInfo=null;
			paymentTransaction.setCreatedAt(flightOrderRowNew.getCreatedAt());
			paymentTransaction.setCurrency(flightOrderRowNew.getBookingCurrency());
			paymentTransaction.setPayment_system(customerPaymentType);
			paymentTransaction.setPayment_status(flightOrderRowNew.getPaymentStatus());
			paymentTransaction.setAmount(flightOrderRowNew.getFinalPrice().add(serviceOrGstTax).add(convinenceFee).add(managementFee));
			paymentTransaction.setApi_transaction_id(flightOrderRowNew.getOrderId());
			paymentCardInfo =paymentCardDetailsDao.getPaymentCardInfo(new PaymentCardDetailsConfig(paymentTransaction.getClientCardHolderId()));
			if(paymentCardInfo!=null){
				paymentTransaction.setPaymentCardDetailsConfig(paymentCardInfo);
			}
			if(customerPaymentType.equalsIgnoreCase("Full")) 
				paymentTransaction.setIsPaymentSuccess(true);
			else 
				paymentTransaction.setIsPaymentSuccess(false);
			paymentTransaction.setTransactionId(RandomConfigurationNumber.generateHotelandFlightPaymentTxKey());
			PaymentTransaction paymentTransactionNew= hotelOfflineBookingDao.insertPaymentTransactionInfo(paymentTransaction);
			/*****/
			if(paymentTransaction.getClientAmount()!=null){
				if(flightOrderRow.getFinalPrice()!=null && flightOrderRow.getFinalPrice().setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getClientAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
					paymentTransactionDetail.setIsPaymentSuccess(true);
				}
				paymentTransactionDetail.setAmount(paymentTransaction.getClientAmount());
				paymentTransactionDetail.setApiTransactionId(paymentTransactionNew.getApi_transaction_id());
				paymentTransactionDetail.setCurrency(paymentTransactionNew.getCurrency());
				paymentTransactionDetail.setPaymentCollectionType(paymentTransactionNew.getPayment_system());
				paymentTransactionDetail.setPaymentInformation(paymentTransactionNew.getPayment_information());
				paymentTransactionDetail.setPaymentTransaction(paymentTransactionNew);
				paymentTransactionDetail.setCreatedAt(new Timestamp(new Date().getTime()));
				paymentTransactionDetail.setTransactionId(RandomConfigurationNumber.generateHotelandFlightPaymentTxKey());
				paymentTransactionDetail.setPaymentStatus(paymentTransactionNew.getPayment_status());
				paymentTransactionDetail.setPaymentMethod(paymentTransactionNew.getPayment_method());
				paymentTransactionDetail.setPaymentPaidBy(paymentTransaction.getPaidByClient());
				paymentTransactionDetail.setCompanyId(sessionUser.getCompanyid());
				paymentTransactionDetail.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
				new HotelOfflineBookingDao().insertHotelCustomerPaymentTxDetail(paymentTransactionDetail);

			}

			if(paymentTransactionNew!=null){ 
				ApiProviderPaymentTransaction apiProviderPaymentTransaction  = new ApiProviderPaymentTransaction();
				apiProviderPaymentTransaction.setAmount(flightOrderRowNew.getFinalPrice());
				apiProviderPaymentTransaction.setCurrency(flightOrderRowNew.getApiCurrency());
				apiProviderPaymentTransaction.setApi_transaction_id(flightOrderRowNew.getOrderId());
				apiProviderPaymentTransaction.setCreatedAt(flightOrderRowNew.getCreatedAt());
				apiProviderPaymentTransaction.setPayment_system(supplierPaymentType);
				apiProviderPaymentTransaction.setPayment_status(flightOrderRowNew.getPaymentStatus());
				apiProviderPaymentTransaction.setIsPaymentSuccess(supplierPaymentType.equalsIgnoreCase("Full"));
				apiProviderPaymentTransaction.setCompanyId(sessionUser.getCompanyid());
				apiProviderPaymentTransaction.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
				paymentCardInfo =paymentCardDetailsDao.getPaymentCardInfo(new PaymentCardDetailsConfig(paymentTransaction.getSupplierCardHolderId()));
				if(paymentCardInfo!=null){
					paymentTransaction.setPaymentCardDetailsConfig(paymentCardInfo);
				}

				if(supplierPaymentType.equalsIgnoreCase("Full")) 
					paymentTransaction.setIsPaymentSuccess(true);
				else 
					paymentTransaction.setIsPaymentSuccess(false);
				apiProviderPaymentTransaction= hotelOfflineBookingDao.insertSupplierPaymentTransactionInfo(apiProviderPaymentTransaction);

				if(paymentTransaction.getApiProviderAmount()!=null){
					if(flightOrderRowNew.getFinalPrice()!=null && flightOrderRowNew.getFinalPrice().setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getApiProviderAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
						apiProviderPaymentTransactionDetail.setIsPaymentSuccess(new Boolean(true));
					}
					apiProviderPaymentTransactionDetail.setAmount(paymentTransaction.getApiProviderAmount());
					apiProviderPaymentTransactionDetail.setApiTransactionId(apiProviderPaymentTransaction.getApi_transaction_id());
					apiProviderPaymentTransactionDetail.setCurrency(apiProviderPaymentTransaction.getCurrency());
					apiProviderPaymentTransactionDetail.setPaymentCollectionType(apiProviderPaymentTransaction.getPayment_system());
					apiProviderPaymentTransactionDetail.setPaymentInformation(apiProviderPaymentTransaction.getPayment_information());
					apiProviderPaymentTransactionDetail.setCreatedAt(new Timestamp(new Date().getTime()));
					apiProviderPaymentTransactionDetail.setTransactionId(RandomConfigurationNumber.generateHotelandFlightPaymentTxKey());
					apiProviderPaymentTransactionDetail.setPaymentStatus(apiProviderPaymentTransaction.getPayment_status());
					apiProviderPaymentTransactionDetail.setPaymentMethod(apiProviderPaymentTransaction.getPayment_method());
					apiProviderPaymentTransactionDetail.setPaymentPaidBy(paymentTransaction.getPaidByClient());
					apiProviderPaymentTransactionDetail.setApiProviderPaymentTransaction(apiProviderPaymentTransaction);
					apiProviderPaymentTransactionDetail.setCreatedAt(new Timestamp(new Date().getTime()));
					apiProviderPaymentTransactionDetail.setCompanyId(sessionUser.getCompanyid());
					apiProviderPaymentTransactionDetail.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					new HotelOfflineBookingDao().insertHotelApiProviderPaymentTransactionDetail(apiProviderPaymentTransactionDetail);
				}
			}
			FlightTravelRequestQuotation   flightTravelRequestQuotationNew=flightTravelRequestDao.updateFlightRequestQuotationWithOrderId(flightOrderRowNew,flightTravelRequestQuotation.getId());
			if(flightTravelRequestQuotationNew!=null){
				HotelFlightBookingStatus hotelFlightBookingStatus=new HotelFlightBookingStatus();
				hotelFlightBookingStatus.setId(flightTravelRequestQuotationNew.getHotetFlightBookingStatus()!=null?flightTravelRequestQuotationNew.getHotetFlightBookingStatus().getId():0);
				hotelFlightBookingStatus.setBooked(TravelRequestStatusEnum.BOOKED.getValue());
				TripRequestDao tripRequestDao = new TripRequestDao();
				tripRequest = tripRequestDao.getTripRequestTripNumber(flightTravelRequestQuotationNew.getFlightTravelRequest().getId(),"F");
				RmConfigDao rmConfigDao = new RmConfigDao();
				RmConfigTripDetailsModel rmConfigTripDetailsModel = rmConfigDao.getTripConfigDetailsByOrdertype(tripRequest.getTripId(),"Flight");
				if(rmConfigTripDetailsModel!=null){
					rmConfigTripDetailsModel.setOrderId(flightOrderRowNew.getOrderId());
					rmConfigDao.updateTripConfigDetails(rmConfigTripDetailsModel);
				}
				new CompanyDAO().insertEmail(flightOrderRowNew.getOrderId(), 0, Email.EMAIL_TYPE_FLIGHT_OFFLINE_ONLINE_INVOICE_SEND);
				setFlightQuotationRequestId(flightTravelRequestQuotationNew.getFlightTravelRequest().getId());

			}
			addActionMessage("Successfully Flight Booking Done.");
			return SUCCESS;
		}
		else{ 
			addActionMessage("We found some error while processing....");
			return ERROR;
		}
	}

	public String insertFlightOrderDetailsTest() throws ParseException{
		User sessionUser=(User) sessionMap.get("User");
		Company sessionCompany=(Company) sessionMap.get("Company");

		if((flightTravelRequest!=null && tripId!=null) || (flightTravelRequest!=null && tripId==null)){

			if(flightTravelRequest!=null && tripId!=null){

				flightTravelRequest.setCreated_by_userId(sessionUser.getId());
				flightTravelRequest.setCompanyId(sessionUser.getCompanyid());
				flightTravelRequest.setUpdated_by_userId(sessionUser.getId());
				flightTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				flightTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
				flightTravelRequest.setTitle(flightOrderCustomerList.get(0).getTitle());
				flightTravelRequest.setFirstName(flightOrderCustomerList.get(0).getFirstName());
				flightTravelRequest.setLastName(flightOrderCustomerList.get(0).getLastName());
				flightTravelRequest.setCurrency("INR");
				flightTravelRequest.setTravelRequestNumber(flightOrderCustomerList.get(0).getRmConfigTripDetailsModel().getTrNumber());
				FlightOrderTripDetail flightOrderTripDetail=new FlightOrderTripDetail();
				if(getFlightOrderTripDetailList()!=null && getFlightOrderTripDetailList().size()>0){
					flightOrderTripDetail=getFlightOrderTripDetailList().get(0);
					flightTravelRequest.setDepartureDate(DateConversion.StringToDate(flightOrderTripDetail.getDepDate()));
					flightTravelRequest.setArrivalDate(DateConversion.StringToDate(flightOrderTripDetail.getArrDate()));
					flightTravelRequest.setOrigin(flightOrderTripDetail.getOriginName());
					flightTravelRequest.setAirlinePrefer(flightOrderTripDetail.getOperatedByName());


				}
				if(getFlightOrderTripDetailList()!=null && getFlightOrderTripDetailList().size()>0){

					flightOrderTripDetail=getFlightOrderTripDetailList().get(getFlightOrderTripDetailList().size() -1);
					flightTravelRequest.setDestination(flightOrderTripDetail.getDestinationName());
				}



			}else if(flightTravelRequest!=null && tripId==null){

				flightTravelRequest.setCreated_by_userId(sessionUser.getId());
				flightTravelRequest.setCompanyId(sessionUser.getCompanyid());
				flightTravelRequest.setUpdated_by_userId(sessionUser.getId());
				flightTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				flightTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
				flightTravelRequest.setTitle(flightOrderCustomerList.get(0).getTitle());
				flightTravelRequest.setFirstName(flightOrderCustomerList.get(0).getFirstName());
				flightTravelRequest.setLastName(flightOrderCustomerList.get(0).getLastName());
				flightTravelRequest.setCurrency("INR");
				flightTravelRequest.setBookingClassPrefer(flightOrderCustomerList.get(0).getBookingClassPreffer());
				FlightOrderTripDetail flightOrderTripDetail=new FlightOrderTripDetail();
				if(getFlightOrderTripDetailList()!=null && getFlightOrderTripDetailList().size()>0){
					flightOrderTripDetail=getFlightOrderTripDetailList().get(0);
					flightTravelRequest.setDepartureDate(DateConversion.StringToDate(flightOrderTripDetail.getDepDate()));
					flightTravelRequest.setArrivalDate(DateConversion.StringToDate(flightOrderTripDetail.getArrDate()));
					flightTravelRequest.setOrigin(flightOrderTripDetail.getOriginName());
					flightTravelRequest.setAirlinePrefer(flightOrderTripDetail.getOperatedByName());


				}
				if(getFlightOrderTripDetailList()!=null && getFlightOrderTripDetailList().size()>0){

					flightOrderTripDetail=getFlightOrderTripDetailList().get(getFlightOrderTripDetailList().size() -1);
					flightTravelRequest.setDestination(flightOrderTripDetail.getDestinationName());
				}

			}

			FlightTravelRequest flightTravelRequestNew=flightTravelRequestDao.insertFlightQuotationRowDetails(flightTravelRequest);
			if(flightTravelRequestNew!=null){
				if(flightTravelRequestNew.getTravelRequestNumber()!=null){
					configTripDetailsModel.setTrNumber(flightTravelRequestNew.getTravelRequestNumber());

				}else{
					configTripDetailsModel.setTrNumber("TAYA97654432");

				}
				setFlightQuotationRequestId(flightTravelRequestNew.getId());
				boolean isCompanyRmConfig=rmConfigDao.isCompanyHavingRmConfig(sessionCompany);
				if(tripId!=null)
				{
					TripRequest tripRequest =new TripRequest();
					tripRequest.setId(tripId);
					tripRequest.setFlightTravelRequest(flightTravelRequestNew);
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
					tripRequestDao.updateTripRequestById(tripRequest);
					if(isCompanyRmConfig){
						RmConfigTripDetailsModel rmconfigTripDetailsModel = new GetNewRmConfigDetail().getRmConfigDetail(configTripDetailsModel,"Flight");
//						rmConfigDao.insertTripConfigDetails(rmconfigTripDetailsModel);
					}
					if(tripRequest!=null && tripRequest.getId()>0){
						//addActionMessage("Successfully created");
					}
				}else{

					TripRequest tripRequest =new TripRequest();
					tripRequest.setFlightTravelRequest(flightTravelRequestNew);
					tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
					//tripRequest.setTripId(RandomConfigurationNumber.generateTripId(flightTravelRequestNew.getId()));
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					tripRequest=tripRequestDao.insertTripRequest(tripRequest);
					if(tripRequest!=null && tripRequest.getId()>0){
						tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
						if(isCompanyRmConfig){
							RmConfigTripDetailsModel rmconfigTripDetailsModel = new GetNewRmConfigDetail().getRmConfigDetail(configTripDetailsModel,"Flight");
//							rmConfigDao.insertTripConfigDetails(rmconfigTripDetailsModel);
						}
						if(tripRequestDao.updateTripRequestNumber(tripRequest)){

						}else{
							addActionMessage("Something went wrong.Please wait.");	
						}


					}


				}
				if(flightTravelRequestNew.getId()!=null  && flightTravelRequestNew!=null){
					FlightTravelRequestQuotation flightQuotation=new FlightTravelRequestQuotation();
					int userId = CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId();
					flightQuotation.setAirline(flightTravelRequestNew.getAirlinePrefer());
					flightQuotation.setOrigin(flightTravelRequestNew.getOrigin());
					flightQuotation.setDestination(flightTravelRequestNew.getDestination());
					flightQuotation.setDepartureDate(flightTravelRequestNew.getDepartureDate());
					flightQuotation.setArrivalDate(flightTravelRequestNew.getArrivalDate());
					flightQuotation.setTripType(flightTravelRequestNew.getTripType());
					flightQuotation.setPassengerCount(flightOrderCustomerList.size());
					flightQuotation.setBookingClassPrefer(flightOrderCustomerList.get(0).getBookingClassPreffer());
					flightRequestQuotationList.add(flightQuotation);

					boolean isInserted=  flightTravelRequestDao.insertFlightQuotationList(flightRequestQuotationList, flightTravelRequestNew,userId,sessionUser.getCompanyid());
					if(isInserted){
						for(FlightTravelRequestQuotation flightTravelRequestQuote: flightRequestQuotationList){
							setFlightQuotationRequestId(flightTravelRequestQuote.getId());
						}



					}
					else{
						addActionError("We found some error.Please Try again.");

					}

				}
				if(getFlightOrderCustomerList()!=null){
					OrderCustomer orderCustomerForQuick= new OrderCustomer();
					orderCustomerForQuick.setFirstName(flightOrderCustomerList.get(0).getFirstName());
					orderCustomerForQuick.setLastName(flightOrderCustomerList.get(0).getLastName());
					orderCustomerForQuick.setTitle(flightOrderCustomerList.get(0).getTitle());
					orderCustomerForQuick.setEmail(orderCustomer.getEmail());
					setOrderCustomer(orderCustomerForQuick);
				}



				new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(flightTravelRequestNew.getId()),"Flight book Request created",String.valueOf(flightTravelRequest.getCustomerComments()),NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),false,false,false,true,false,false);


			}

			else{
				addActionError("We found some error.Please Try again.");

			}
		}

		return insertFlightOrderBookingDetails();
		//FlightTravelRequestQuotation   flightTravelRequestQuotationNew=flightTravelRequestDao.updateFlightRequestQuotationWithOrderId(flightOrderRowNew,flightTravelRequestQuotation.getId());




	}



	public String getFlightOfflineVocher(){
		InvoiceData offlineVoucherData=new InvoiceDao().getFlightOfflineVoucher(flightOrderRow.getId());
		if(offlineVoucherData!=null){
			flightOfflineVoucherData=offlineVoucherData;
		}

		FlightTravelRequestQuotation quotation=new FlightTravelRequestDao().getFlightQuotationDetails(flightOrderRow.getId());
		if(quotation!=null){
			flightQuotation=quotation;
		}

		return SUCCESS;
	}

	public String getFlightOfflineInvoice(){
		InvoiceData offlineVoucherData=new InvoiceDao().getFlightOfflineVoucher(flightOrderRow.getId());
		if(offlineVoucherData!=null){
			flightOfflineVoucherData=offlineVoucherData;
		}

		FlightTravelRequestQuotation quotation=new FlightTravelRequestDao().getFlightQuotationDetails(flightOrderRow.getId());
		if(quotation!=null){
			flightQuotation=quotation;
		}

		return SUCCESS;
	}




	public String getFlightCustomerPaymentTransactionInfo(){
		BigDecimal partialAmtCount= new BigDecimal("0.00");
		BigDecimal restAmtCount= new BigDecimal("0.00");
		FlightOrderDao flightOrderDao=new FlightOrderDao();
		HotelOrderDao hotelOrderDao=new HotelOrderDao();
		FlightOrderRow flightOrderRow=flightOrderDao.getFlightOrderRowInfo(getFlightOrderId());
		paymentCardList=paymentCardDetailsDao.getPaymentCardDetailsList(false);
		PaymentTransaction paymentTransactionNew= hotelOrderDao.getPaymentTransactionInfo(flightOrderRow.getOrderId()) ;
		//paymentTransactionNew.setAmount(paymentTransactionNew.getAmount().setScale(BigDecimal.ROUND_UP,2));
		if(paymentTransactionNew!=null  && paymentTransactionNew.getPayment_system()!=null && (paymentTransactionNew.getPayment_system().equalsIgnoreCase("Partial") || paymentTransactionNew.getPayment_system().equalsIgnoreCase("Zero") )){
			List<PaymentTransactionDetail> newPaymentTransactionDetailList=hotelOfflineBookingDao.getPaymentTransactionDetailList(paymentTransactionNew.getApi_transaction_id());
			if(paymentTransactionNew.getAmount()!=null)
			{
				if(newPaymentTransactionDetailList!=null && newPaymentTransactionDetailList.size()>0){
					for(PaymentTransactionDetail paymentTransactionDetail: newPaymentTransactionDetailList){
						paymentTransactionDetail.setCreatedDate(DateConversion.convertTimestampToString(paymentTransactionDetail.getCreatedAt()));
						if(paymentTransactionDetail.getAmount()!=null)
						{
							paymentTransactionDetail.setAmount(paymentTransactionDetail.getAmount().setScale(2, BigDecimal.ROUND_UP));
							restAmtCount =restAmtCount.add(paymentTransactionDetail.getAmount());
							BigDecimal balance=paymentTransactionNew.getAmount().subtract(restAmtCount);
							paymentTransactionDetail.setBalance(balance);
							paymentTransactionDetailList.add(paymentTransactionDetail);
							partialAmtCount=partialAmtCount.add(paymentTransactionDetail.getAmount());
						}
					}
				}
				BigDecimal balance=	paymentTransactionNew.getAmount().subtract(partialAmtCount).setScale(0, BigDecimal.ROUND_UP);
				paymentTransactionNew.setBalance(balance);
			}

		}


		FlightTravelRequestQuotation flightQuotation=new FlightTravelRequestDao().getFlightQuotationDetails(flightOrderRow.getId());
		flightQuotation.setFlightOrderRow(flightOrderRow);
		setFlightQuotation(flightQuotation);
		setPaymentTransaction(paymentTransactionNew);
		return SUCCESS;
	}
	public String getFlightApiProviderPaymentTransactionInfo(){
		logger.info("getFlightOrderId()------------"+getFlightOrderId());
		BigDecimal partialAmtCount= new BigDecimal("0.00");
		BigDecimal restAmtCount= new BigDecimal("0.00");
		FlightOrderDao flightOrderDao=new FlightOrderDao();
		HotelOrderDao hotelOrderDao=new HotelOrderDao();
		FlightOrderRow flightOrderRow=flightOrderDao.getFlightOrderRowInfo(getFlightOrderId());
		logger.info("flightOrderRow.getOrderId()------------"+flightOrderRow.getOrderId());
		paymentCardList=paymentCardDetailsDao.getPaymentCardDetailsList(true);
		ApiProviderPaymentTransaction paymentTransactionNew= hotelOrderDao.getApiProviderPaymentTransactionInfo(flightOrderRow.getOrderId()) ;
		if(paymentTransactionNew!=null && paymentTransactionNew.getPayment_system()!=null && (paymentTransactionNew.getPayment_system().equalsIgnoreCase("Partial")|| paymentTransactionNew.getPayment_system().equalsIgnoreCase("Zero") )){
			List<ApiProviderPaymentTransactionDetail> newPaymentTransactionDetailList=hotelOfflineBookingDao.getApiProviderPaymentTransactionDetailList(paymentTransactionNew.getApi_transaction_id());
			logger.info("newPaymentTransactionDetailList----------"+newPaymentTransactionDetailList);
			if(newPaymentTransactionDetailList!=null && newPaymentTransactionDetailList.size()>0){
				if(paymentTransactionNew.getAmount()!=null)
				{
					for(ApiProviderPaymentTransactionDetail paymentTransactionDetail: newPaymentTransactionDetailList){
						paymentTransactionDetail.setCreatedDate(DateConversion.convertTimestampToString(paymentTransactionDetail.getCreatedAt()));
						if(paymentTransactionDetail.getAmount()!=null)
						{
							paymentTransactionDetail.setAmount(paymentTransactionDetail.getAmount().setScale(2, BigDecimal.ROUND_UP));
							restAmtCount =restAmtCount.add(paymentTransactionDetail.getAmount());
							BigDecimal balance=paymentTransactionNew.getAmount().subtract(restAmtCount);
							paymentTransactionDetail.setBalance(balance);
							apiProviderpaymentTransactionDetailList.add(paymentTransactionDetail);
							partialAmtCount=partialAmtCount.add(paymentTransactionDetail.getAmount());
						}
					}
				}
				BigDecimal balance=	paymentTransactionNew.getAmount().subtract(partialAmtCount);
				paymentTransactionNew.setBalance(balance);
			}

		}
		logger.info("apiProviderpaymentTransactionDetailList---------------"+apiProviderpaymentTransactionDetailList.size());
		FlightTravelRequestQuotation flightQuotation=new FlightTravelRequestDao().getFlightQuotationDetails(flightOrderRow.getId());
		flightQuotation.setFlightOrderRow(flightOrderRow);
		setFlightQuotation(flightQuotation);

		setApiProviderPaymentTransaction(paymentTransactionNew);
		return SUCCESS;
	}

	public FlightTravelRequestQuotation getFlightQuotation() {
		return flightQuotation;
	}

	public void setFlightQuotation(FlightTravelRequestQuotation flightQuotation) {
		this.flightQuotation = flightQuotation;
	}

	@Override
	public FlightOrderRow getModel() {
		// TODO Auto-generated method stub
		return flightOrderRow;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	public FlightOrderRow getFlightOrderRow() {
		return flightOrderRow;
	}

	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}

	public List<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public PaymentTransaction getPaymentTransaction() {
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
	public List<ApiProviderPaymentTransactionDetail> getApiProviderpaymentTransactionDetailList() {
		return apiProviderpaymentTransactionDetailList;
	}
	public void setApiProviderpaymentTransactionDetailList(List<ApiProviderPaymentTransactionDetail> apiProviderpaymentTransactionDetailList) {
		this.apiProviderpaymentTransactionDetailList = apiProviderpaymentTransactionDetailList;
	}
	public List<PaymentTransactionDetail> getPaymentTransactionDetailList() {
		return paymentTransactionDetailList;
	}
	public void setPaymentTransactionDetailList(List<PaymentTransactionDetail> paymentTransactionDetailList) {
		this.paymentTransactionDetailList = paymentTransactionDetailList;
	}
	public long getFlightQuotationRequestId() {
		return flightQuotationRequestId;
	}
	public void setFlightQuotationRequestId(long flightQuotationRequestId) {
		this.flightQuotationRequestId = flightQuotationRequestId;
	}
	public List<Airlinelist> getAirlineList() {
		return airlineList;
	}
	public void setAirlineList(List<Airlinelist> airlineList) {
		this.airlineList = airlineList;
	}

	public List<FlightOrderCustomer> getFlightOrderCustomerList() {
		return flightOrderCustomerList;
	}
	public void setFlightOrderCustomerList(List<FlightOrderCustomer> flightOrderCustomerList) {
		this.flightOrderCustomerList = flightOrderCustomerList;
	}

	public List<FlightOrderTripDetail> getFlightOrderTripDetailList() {
		return flightOrderTripDetailList;
	}
	public void setFlightOrderTripDetailList(List<FlightOrderTripDetail> flightOrderTripDetailList) {
		this.flightOrderTripDetailList = flightOrderTripDetailList;
	}
	public List<FlightOrderCustomerPriceBreakup> getFlightOrderCustomerPriceBreakupList() {
		return flightOrderCustomerPriceBreakupList;
	}
	public void setFlightOrderCustomerPriceBreakupList(List<FlightOrderCustomerPriceBreakup> flightOrderCustomerPriceBreakupList) {
		this.flightOrderCustomerPriceBreakupList = flightOrderCustomerPriceBreakupList;
	}
	public OrderCustomer getOrderCustomer() {
		return orderCustomer;
	}
	public void setOrderCustomer(OrderCustomer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}
	public InvoiceData getFlightOfflineVoucherData() {
		return flightOfflineVoucherData;
	}
	public void setFlightOfflineVoucherData(InvoiceData flightOfflineVoucherData) {
		this.flightOfflineVoucherData = flightOfflineVoucherData;
	}
	public Long getFlightOrderId() {
		return flightOrderId;
	}
	public void setFlightOrderId(Long flightOrderId) {
		this.flightOrderId = flightOrderId;
	}
	public String getSupplierPaymentType() {
		return supplierPaymentType;
	}
	public void setSupplierPaymentType(String supplierPaymentType) {
		this.supplierPaymentType = supplierPaymentType;
	}
	public String getCustomerPaymentType() {
		return customerPaymentType;
	}
	public void setCustomerPaymentType(String customerPaymentType) {
		this.customerPaymentType = customerPaymentType;
	}
	public List<PaymentCardDetailsConfig> getPaymentCardList() {
		return paymentCardList;
	}
	public void setPaymentCardList(List<PaymentCardDetailsConfig> paymentCardList) {
		this.paymentCardList = paymentCardList;
	}
	public List<String> getDomesticOrInternationalCountryNameList() {
		return domesticOrInternationalCountryNameList;
	}
	public void setDomesticOrInternationalCountryNameList(List<String> domesticOrInternationalCountryNameList) {
		this.domesticOrInternationalCountryNameList = domesticOrInternationalCountryNameList;
	}
	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}
	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}
	public FlightOrderRowServiceTax getFlightOrderRowServiceTax() {
		return flightOrderRowServiceTax;
	}
	public void setFlightOrderRowServiceTax(FlightOrderRowServiceTax flightOrderRowServiceTax) {
		this.flightOrderRowServiceTax = flightOrderRowServiceTax;
	} 
	public static FlightServiceTax getFlightServiceTax(BigDecimal basefare,BigDecimal YqTax,CompanyConfig companyConfig,boolean isIsInternational,BigDecimal managementFees){

		BigDecimal baseprice =  basefare.add(YqTax);
		BigDecimal baseServiceTax = new BigDecimal("0.0");
		BigDecimal SBC = new BigDecimal("0.0");
		BigDecimal KKC = new BigDecimal("0.0");
		BigDecimal totalServiceTax = new BigDecimal("0.0");
		BigDecimal convenienceFee  = new BigDecimal("0.0");
		BigDecimal managementFee  =managementFees!=null?managementFees:new BigDecimal("0.0");

		if(!isIsInternational){
			FlightDomesticServiceTaxConfig flightDomesticServiceTaxConfig = companyConfig.getFlightDomesticServiceTaxConfig();
			if(flightDomesticServiceTaxConfig!=null &&  companyConfig.getCompanyConfigType().isB2E()){
				baseServiceTax = baseprice.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal("100.0")).multiply(flightDomesticServiceTaxConfig.getBasicTax()).setScale(2, BigDecimal.ROUND_UP);
				SBC =  baseprice.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal("100.0")).multiply(flightDomesticServiceTaxConfig.getSwatchBharathCess()).setScale(2, BigDecimal.ROUND_UP);
				KKC =  baseprice.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal("100.0")).multiply(flightDomesticServiceTaxConfig.getKrishiKalyanCess()).setScale(2, BigDecimal.ROUND_UP);
				totalServiceTax =  baseprice.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal("100.0")).multiply(flightDomesticServiceTaxConfig.getTotalTax()).setScale(2, BigDecimal.ROUND_UP);
				/*managementFee = flightDomesticServiceTaxConfig.getManagementFee();*/
				convenienceFee = flightDomesticServiceTaxConfig.getConvenienceFee();
			}
		}
		else{
			FlightInternationalServiceTaxConfig flightInternationalServiceTaxConfig = companyConfig.getFlightInternationalServiceTaxConfig();
			if(flightInternationalServiceTaxConfig!=null&&  companyConfig.getCompanyConfigType().isB2E()){
				baseServiceTax = baseprice.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal("100.0")).multiply(flightInternationalServiceTaxConfig.getBasicTax()).setScale(2, BigDecimal.ROUND_UP);
				SBC =  baseprice.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal("100.0")).multiply(flightInternationalServiceTaxConfig.getSwatchBharathCess()).setScale(2, BigDecimal.ROUND_UP);
				KKC =  baseprice.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal("100.0")).multiply(flightInternationalServiceTaxConfig.getKrishiKalyanCess()).setScale(2, BigDecimal.ROUND_UP);
				totalServiceTax =  baseprice.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal("100.0")).multiply(flightInternationalServiceTaxConfig.getTotalTax()).setScale(2, BigDecimal.ROUND_UP);
				//managementFee = flightInternationalServiceTaxConfig.getManagementFee();
				convenienceFee = flightInternationalServiceTaxConfig.getConvenienceFee();
			}
		}

		FlightServiceTax flightServiceTax = new FlightServiceTax();
		flightServiceTax.setBaseServicetax(baseServiceTax);
		flightServiceTax.setSBC(SBC);
		flightServiceTax.setKKC(KKC);
		flightServiceTax.setTotalServiceTax(totalServiceTax);
		flightServiceTax.setManagementFee(managementFee);
		flightServiceTax.setConvenienceFee(convenienceFee);
		return flightServiceTax;
	}
	/*if(isParentCompanyUT && isBillingCompanyUT){
    CGST = managementFee.divide(new BigDecimal("100.0")).multiply(flightInternationalGstTaxConfig.getCGST());
    UGST =  managementFee.divide(new BigDecimal("100.0")).multiply(flightInternationalGstTaxConfig.getUGST());
   }
   if(!company.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(company.getBillingstate().trim())){
    CGST = managementFee.divide(new BigDecimal("100.0")).multiply(flightInternationalGstTaxConfig.getCGST());
    UGST =  managementFee.divide(new BigDecimal("100.0")).multiply(flightInternationalGstTaxConfig.getUGST());
   }
   if(!isParentCompanyUT && !isBillingCompanyUT){
    if(company.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
     CGST = managementFee.divide(new BigDecimal("100.0")).multiply(flightInternationalGstTaxConfig.getCGST());
     SGST =  managementFee.divide(new BigDecimal("100.0")).multiply(flightInternationalGstTaxConfig.getSGST());    
    }
   }
   if(isParentCompanyUT && !isBillingCompanyUT){
    if(!company.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(company.getBillingstate().trim())){
     IGST =  managementFee.divide(new BigDecimal("100.0")).multiply(flightInternationalGstTaxConfig.getIGST());  
    }
   }*/



	public static FlightGstTax getFlightGSTTax(FlightOrderRowGstTax flightOrderRowGstTax,Company childCompany,Company parentCompany,FlightOrderRow flightOrderRow){
		CompanyEntity  companyEntity=new CompanyEntity();
		String entityState=null;
		if(flightOrderRow.getCompanyEntityId()!=null) 
			companyEntity = new CompanyDAO().companyEntityProfile(flightOrderRow.getCompanyEntityId().intValue());
		if(companyEntity!=null && companyEntity.getState()!=null) 
			entityState=companyEntity.getState(); 

		BigDecimal managementFee  =flightOrderRowGstTax.getManagementFee()!=null?flightOrderRowGstTax.getManagementFee():new BigDecimal("0.0");
		BigDecimal CGSTAmount = new BigDecimal("0.0");
		BigDecimal CGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTAmount = new BigDecimal("0.0");

		BigDecimal totalGstAmount = new BigDecimal("0.0");
		BigDecimal totalGstPer = new BigDecimal("0.0");
		boolean isParentCompanyUT=IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());
		CGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getCGST());
		CGSTPer  = flightOrderRowGstTax.getCGST();
		if(entityState!=null){ 
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(entityState.trim());

			if(isParentCompanyUT && isBillingCompanyUT){
				CommonGSTPer =flightOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getUGST());
				flightOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				flightOrderRowGstTax.setSGST(new BigDecimal("0.0"));

			}
			if(!isParentCompanyUT && !isBillingCompanyUT){
				if(entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =flightOrderRowGstTax.getSGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getSGST());    
					flightOrderRowGstTax.setIGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				}
				if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =flightOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getIGST());
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					flightOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(isParentCompanyUT && !isBillingCompanyUT){
				if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(entityState.trim())){
					CommonGSTPer =flightOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getIGST()); 
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					flightOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(entityState.trim())){
				CommonGSTPer =flightOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getUGST());
				flightOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				flightOrderRowGstTax.setSGST(new BigDecimal("0.0"));
			}


		}else{
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim());

			if(isParentCompanyUT && isBillingCompanyUT){
				CommonGSTPer =flightOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getUGST());
				flightOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				flightOrderRowGstTax.setSGST(new BigDecimal("0.0"));

			}
			if(!isParentCompanyUT && !isBillingCompanyUT){
				if(childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =flightOrderRowGstTax.getSGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getSGST());    
					flightOrderRowGstTax.setIGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				}
				if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =flightOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getIGST());   
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					flightOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(isParentCompanyUT && !isBillingCompanyUT){
				if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
					CommonGSTPer =flightOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getIGST());  
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					flightOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					flightOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
				CommonGSTPer =flightOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(flightOrderRowGstTax.getUGST());
				flightOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				flightOrderRowGstTax.setSGST(new BigDecimal("0.0"));
			}


		} 
		//boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim());


		totalGstPer=CGSTPer.add(CommonGSTPer);
		totalGstAmount = CGSTAmount.add(CommonGSTAmount); 
		FlightGstTax flightServiceTax = new FlightGstTax();
		flightServiceTax.setTotalTax(totalGstPer);
		flightServiceTax.setTotalGstAmount(totalGstAmount);
		return flightServiceTax;
	}



	public BigDecimal getManagementFeeForBooking() {
		return managementFeeForBooking;
	}
	public void setManagementFeeForBooking(BigDecimal managementFeeForBooking) {
		this.managementFeeForBooking = managementFeeForBooking;
	}
	public UserWallet getUserWallet() {
		return userWallet;
	}
	public void setUserWallet(UserWallet userWallet) {
		this.userWallet = userWallet;
	}
	public FlightTravelRequest getFlightTravelRequest() {
		return flightTravelRequest;
	}
	public void setFlightTravelRequest(FlightTravelRequest flightTravelRequest) {
		this.flightTravelRequest = flightTravelRequest;
	}
	public Long getTripId() {
		return tripId;
	}
	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}
	public RmConfigTripDetailsModel getConfigTripDetailsModel() {
		return configTripDetailsModel;
	}
	public void setConfigTripDetailsModel(RmConfigTripDetailsModel configTripDetailsModel) {
		this.configTripDetailsModel = configTripDetailsModel;
	}
	public Long getPassengerCountDynamic() {
		return passengerCountDynamic;
	}
	public void setPassengerCountDynamic(Long passengerCountDynamic) {
		this.passengerCountDynamic = passengerCountDynamic;
	}
	public Company getParentCompany() {
		return parentCompany;
	}
	public void setParentCompany(Company parentCompany) {
		this.parentCompany = parentCompany;
	}
	public BigDecimal getManagementFeeForBookingIntManagemtFee() {
		return managementFeeForBookingIntManagemtFee;
	}
	public void setManagementFeeForBookingIntManagemtFee(BigDecimal managementFeeForBookingIntManagemtFee) {
		this.managementFeeForBookingIntManagemtFee = managementFeeForBookingIntManagemtFee;
	}
	public BigDecimal getManagementFeeForBookingDomManagemtFee() {
		return managementFeeForBookingDomManagemtFee;
	}
	public void setManagementFeeForBookingDomManagemtFee(BigDecimal managementFeeForBookingDomManagemtFee) {
		this.managementFeeForBookingDomManagemtFee = managementFeeForBookingDomManagemtFee;
	}
	public RmConfigModel getRmConfigModel() {
		return rmConfigModel;
	}
	public void setRmConfigModel(RmConfigModel rmConfigModel) {
		this.rmConfigModel = rmConfigModel;
	}
	public List<String> getFieldName() {
		return fieldName;
	}
	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}
	public List<FlightTravelRequestQuotation> getFlightRequestQuotationList() {
		return flightRequestQuotationList;
	}
	public void setFlightRequestQuotationList(List<FlightTravelRequestQuotation> flightRequestQuotationList) {
		this.flightRequestQuotationList = flightRequestQuotationList;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public List<String> getTravelRequestNoList() {
		return travelRequestNoList;
	}
	public void setTravelRequestNoList(List<String> travelRequestNoList) {
		this.travelRequestNoList = travelRequestNoList;
	}

}
