package com.admin.hotel.fin.sheet.action;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.admin.common.util.CommonUtilSession;
import com.admin.enums.utility.CommonBookingStatusEnum;
import com.admin.enums.utility.IndianUnionTerritories;
import com.admin.hotel.fin.sheet.Dao.HotelOfflineBookingDao;
import com.admin.hotel.fin.sheet.Dao.HotelTravelRequestDao;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.HotelOffineVoucherUtility;
import com.admin.hotel.fin.sheet.model.HotelTravelRequest;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.admin.payment.card.details.PaymentCardDetailsConfig;
import com.admin.payment.card.details.PaymentCardDetailsDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.common.model.PaymentTransactionDetail;
import com.lintas.admin.hotel.model.HotelOrderCancellationPolicy;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderHotelData;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRowGstTax;
import com.lintas.admin.hotel.model.HotelOrderRowServiceTax;
import com.lintas.admin.hotel.model.HotelReport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CompanyConfigTax;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.HotelOrderRowMarkup;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.vo.CutandPayModel;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.ArithmeticUti;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.admin.orderrow.rm.structure.HotelOrderRowRmConfigStruct;
import com.tayyarah.gst.model.FlightGstTax;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;
public class HotelOfflineBookingAction  extends ActionSupport implements ModelDriven<HotelOrderRow> ,SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelOfflineBookingAction.class);
	SessionMap<String, Object> sessionMap=null;
	private HotelOrderRow hotelOrderRow=new HotelOrderRow();
	private UserWallet userWallet=new UserWallet();
	private HotelReport hotelReport=new HotelReport();
	private List<HotelOrderGuest> hotelOrderGuestList=new ArrayList<>();
	private List<HotelOrderCancellationPolicy> hotelOrderCancellationPolicyList= new ArrayList<>();
	private List<HotelOrderRoomInfo>  hotelOrderRoomInfoList= new ArrayList<>();
	Map <HotelOffineVoucherUtility, HotelOrderGuest>  hotelVoucherMap=new HashMap<>();
	private PaymentTransaction paymentTransaction=new PaymentTransaction();
	private ApiProviderPaymentTransaction apiProviderPaymentTransaction=new ApiProviderPaymentTransaction();
	private List<ApiProviderPaymentTransactionDetail> apiProviderpaymentTransactionDetailList=new  ArrayList<>();
	private List<PaymentTransactionDetail> paymentTransactionDetailList=new  ArrayList<>();
	private HotelOfflineBookingDao hotelOfflineBookingDao=new HotelOfflineBookingDao();
	private Long  hotelRequestQuotationId;
	private Long  hotelOrderId;
	private String supplierPaymentType=null;
	private String customerPaymentType=null;
	private String dateStart;
	private String dateEnd;
	private String alternativeEmail;
	private int  noOfnightsCount;
	private List<Country> countryList=null;
	private List<PaymentCardDetailsConfig> paymentCardList=null;
	private PaymentCardDetailsDao paymentCardDetailsDao=new PaymentCardDetailsDao();
	private UserDAO userDao=new UserDAO();
	private List<ApiProvider> apiProviders;
	private ApiProviderDao apiProviderDao =new ApiProviderDao(); 
	private BigDecimal managementFeeForBooking=new BigDecimal("0.00");
	private HotelTravelRequest hotelTravelRequest=new HotelTravelRequest();
	private HotelTravelRequestQuotation hotelTravelRequestQuotation=new HotelTravelRequestQuotation();
	private List<HotelTravelRequestQuotation> hotelTravelRequestQuotationsListnew=new ArrayList<HotelTravelRequestQuotation>();
	private HotelTravelRequestDao hotelOrderRowFineSheetDao = new HotelTravelRequestDao();
	private Long hotelQuotationRequestId;
	private Long tripId;
	private int noofrooms;
	Company parentCompany= new Company();
	private TripRequestDao tripRequestDao = new TripRequestDao();
	private HotelTravelRequestDao hotelTravelRequestDao=new HotelTravelRequestDao();
	private HotelTravelRequestQuotation hotelQuotation= new HotelTravelRequestQuotation();
	private BigDecimal managementFeeForBookingIntManagemtFee =new BigDecimal("0.00");
	private BigDecimal managementFeeForBookingDomManagemtFee =new BigDecimal("0.00");
	RmConfigModel rmConfigModel = new RmConfigModel();
	List<String> fieldName = new ArrayList<String>();
	RmConfigTripDetailsModel configTripDetailsModel = new RmConfigTripDetailsModel();
	RmConfigDao rmConfigDao = new RmConfigDao();
	
	public String  goHotelBookingPage(){
		Company sessionCompany=(Company)sessionMap.get("Company");
		User sessionUser=(User)sessionMap.get("User");
		if(hotelRequestQuotationId!=null && hotelRequestQuotationId>0){
			HotelTravelRequestQuotation hotelQuotation=new HotelTravelRequestDao().getHotelTravelRequestQuotationDetails(getHotelRequestQuotationId());
			if(hotelQuotation!=null){
				setHotelQuotation(hotelQuotation);
			}
			countryList=new CountryDao().getCountryList();
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
			paymentCardList=paymentCardDetailsDao.getAllPaymentCardDetailsList();
			//added by basha
			CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());
			String itenaryType=hotelQuotation.getHotelCity().contains("(IN)")?"Dom":"Int";

			if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null  && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")){
				if(newCompanyConfig.getHotelGstTaxConfig()!=null){
					if(itenaryType.equalsIgnoreCase("Int"))
						managementFeeForBooking=newCompanyConfig.getHotelGstTaxConfig().getIntlManagementFee();
					if(itenaryType.equalsIgnoreCase("Dom")) 
						managementFeeForBooking=newCompanyConfig.getHotelGstTaxConfig().getDomesticManagementFee();
					managementFeeForBooking=managementFeeForBooking!=null?managementFeeForBooking:new BigDecimal(0);
				}
			}
			else{
				if(newCompanyConfig!=null && newCompanyConfig.getHotelServiceTaxConfig()!=null){
					if(itenaryType.equalsIgnoreCase("Int"))
						managementFeeForBooking=newCompanyConfig.getHotelServiceTaxConfig().getManagementFee();
					if(itenaryType.equalsIgnoreCase("Dom")) 
						managementFeeForBooking=newCompanyConfig.getHotelServiceTaxConfig().getDomesticManagementFee();
					managementFeeForBooking=managementFeeForBooking!=null?managementFeeForBooking:new BigDecimal(0);
				}
			}
			userWallet=new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId());
			return SUCCESS;

		}else{
			CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());	
			if(newCompanyConfig.getTaxtype()!=null){
				CompanyConfigTax companyConfigForManagementFee=new CompanyConfigDao().getConfigByComnpanyIdForB2ETaxes(sessionCompany.getCompanyid());
				if(companyConfigForManagementFee!=null){
					if(companyConfigForManagementFee.getGstTaxObj().getTaxtype().equalsIgnoreCase("GST")){
						setManagementFeeForBookingDomManagemtFee(companyConfigForManagementFee.getGstTaxObj().getHotelGstTaxConfig().getDomesticManagementFee());
						setManagementFeeForBookingIntManagemtFee(companyConfigForManagementFee.getGstTaxObj().getHotelGstTaxConfig().getIntlManagementFee());
				}else if(companyConfigForManagementFee.getServiceTaxObj().getTaxtype().equalsIgnoreCase("servicetax")){
					setManagementFeeForBookingDomManagemtFee(companyConfigForManagementFee.getServiceTaxObj().getHotelServiceTaxConfig().getDomesticManagementFee());
					setManagementFeeForBookingIntManagemtFee(companyConfigForManagementFee.getServiceTaxObj().getHotelServiceTaxConfig().getManagementFee());
			
					
				}
				}
			
			}else{
				if(newCompanyConfig.getHotelServiceTaxConfig()!=null ){
					if(newCompanyConfig.getHotelServiceTaxConfig().getDomesticManagementFee()!=null)
						setManagementFeeForBookingDomManagemtFee(newCompanyConfig.getHotelServiceTaxConfig().getDomesticManagementFee());
					else
						setManagementFeeForBookingDomManagemtFee(new BigDecimal("0.00"));
					if(newCompanyConfig.getHotelServiceTaxConfig().getManagementFee()!=null)
						setManagementFeeForBookingIntManagemtFee(newCompanyConfig.getHotelServiceTaxConfig().getManagementFee());
					else
						setManagementFeeForBookingIntManagemtFee(new BigDecimal("0.00"));
							
				}
			}
			countryList=new CountryDao().getCountryList();
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
			paymentCardList=paymentCardDetailsDao.getAllPaymentCardDetailsList();
			RmConfigDao rmConfigDao=new RmConfigDao();
			try{
				setRmConfigModel(rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid()));
				String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("")?rmConfigModel.getDynamicFieldsData().split(","):null;
				if(manualStringFields!=null && manualStringFields.length>0){
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
			}
			userWallet=new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId());
			return SUCCESS;
		}

	}

	public String insertHotelOrderRowInfo(){
		User sessionUser=(User) sessionMap.get("User");
		Company sessionCompany=(Company)sessionMap.get("Company");
		setParentCompany(new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid()));
		User userNew=userDao.getUserByUserId(sessionUser.getId());
		String orderId = RandomConfigurationNumber.generateHotelOfflineBookingNumber();
		PaymentTransactionDetail paymentTransactionDetail=new PaymentTransactionDetail();
		ApiProviderPaymentTransactionDetail apiProviderPaymentTransactionDetail=new ApiProviderPaymentTransactionDetail();
		hotelOrderRow.setBookingMode("Offline");
		if(hotelOrderRow.getCheckIn()!=null){
			hotelOrderRow.setCheckInDate(DateConversion.StringToDate(hotelOrderRow.getCheckIn()));
		}else{
			hotelOrderRow.setCheckInDate(DateConversion.StringToDate(hotelTravelRequest.getCheckIn()));
		}
		if(hotelOrderRow.getCheckOut()!=null){
			hotelOrderRow.setCheckOutDate(DateConversion.StringToDate(hotelOrderRow.getCheckOut()));
		}else{
			hotelOrderRow.setCheckOutDate(DateConversion.StringToDate(hotelTravelRequest.getCheckOut()));
		}


		hotelOrderRow.setCreatedAt(new Timestamp(new Date().getTime()));
		hotelOrderRow.setAgencyUserName(sessionUser.getUsername());
		hotelOrderRow.setBookingDate(DateConversion.convertDDMMYYtoYYMMDD(hotelOrderRow.getBookingDate()));
		hotelOrderRow.setCreatedBy(sessionUser.getUsername());
		hotelOrderRow.setCompanyId(String.valueOf(sessionCompany.getCompanyid()));
		hotelOrderRow.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId()));
		hotelOrderRow.setBaseCurrency("INR");
		hotelOrderRow.setPaymentStatus("Paid");
	/*	int roomAndNightCount;
		if(hotelOrderRow.getNoOfRooms()>0) 
			roomAndNightCount=noOfnightsCount*hotelOrderRow.getNoOfRooms();	
		 else 
			roomAndNightCount=noOfnightsCount*hotelOrderRoomInfoList.size();*/

		BigDecimal  tax=hotelOrderRow.getTaxes();
		BigDecimal supplierPriceWithTax=(hotelOrderRow.getApiPrice().add(hotelOrderRow.getTaxes()));
		BigDecimal totalMarkup=hotelOrderRow.getMarkupAmount();
		BigDecimal totaCalPrice=supplierPriceWithTax.multiply(hotelOrderRow.getApiToBaseExchangeRate()).add(totalMarkup);
		BigDecimal finalPrice=ArithmeticUti.divideTo2Decimal(totaCalPrice,hotelOrderRow.getBaseToBookingExchangeRate());
		BigDecimal managementFee=hotelOrderRow.getTempManagementFees()!=null?hotelOrderRow.getTempManagementFees():new BigDecimal(0);
/*		managementFee=managementFee.multiply(new BigDecimal(hotelOrderRoomInfoList.size()));*/
		BigDecimal TotalServiceTax = new BigDecimal("0");
		BigDecimal convenienceFee = new BigDecimal("0");
		BigDecimal serviceOrGstTax=new BigDecimal("0.00");
		
		HotelOrderRowServiceTax hotelOrderRowServiceTax=new HotelOrderRowServiceTax();
		HotelOrderRowGstTax hotelOrderRowGstTax=new HotelOrderRowGstTax();
		FlightGstTax flightGstTax=null;
		CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());
		if(newCompanyConfig!=null &&  newCompanyConfig.getCompanyConfigType().isB2E()) {
			if(newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")){
				//managementFee=managementFee.multiply(new BigDecimal(hotelOrderRoomInfoList.size()));
				hotelOrderRowGstTax.setApplicableFare(newCompanyConfig.getHotelGstTaxConfig().getApplicableFare());
				hotelOrderRowGstTax.setSGST(newCompanyConfig.getHotelGstTaxConfig().getSGST().setScale(2,RoundingMode.HALF_UP));
				hotelOrderRowGstTax.setManagementFee(managementFee);
				hotelOrderRowGstTax.setConvenienceFee(newCompanyConfig.getHotelGstTaxConfig().getConvenienceFee());
				hotelOrderRowGstTax.setCreatedAt(new Timestamp(new Date().getTime()));
				hotelOrderRowGstTax.setCGST(newCompanyConfig.getHotelGstTaxConfig().getCGST().setScale(2,RoundingMode.HALF_UP));
				hotelOrderRowGstTax.setIGST(newCompanyConfig.getHotelGstTaxConfig().getIGST().setScale(2,RoundingMode.HALF_UP));
				hotelOrderRowGstTax.setUGST(newCompanyConfig.getHotelGstTaxConfig().getUGST().setScale(2,RoundingMode.HALF_UP));
				flightGstTax=getFlightGSTTax(hotelOrderRowGstTax, sessionCompany, parentCompany,hotelOrderRow);
				hotelOrderRowGstTax.setTotalGst(flightGstTax.getTotalTax());
				convenienceFee=newCompanyConfig.getHotelGstTaxConfig().getConvenienceFee();
				hotelOrderRow.setTotGst(flightGstTax.getTotalGstAmount());
				serviceOrGstTax=flightGstTax.getTotalGstAmount();
				hotelOrderRow.setHotelOrderRowGstTax(hotelOrderRowGstTax);
			}
			else{
				if(newCompanyConfig.getHotelServiceTaxConfig()!=null){
					BigDecimal BaseServiceTax= new BigDecimal(0);
					BigDecimal swachBharatCess = new BigDecimal("0");
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					hotelOrderRowServiceTax.setApplicableFare(newCompanyConfig.getHotelServiceTaxConfig().getApplicableFare());
					hotelOrderRowServiceTax.setBasicTax(newCompanyConfig.getHotelServiceTaxConfig().getBasicTax());
					hotelOrderRowServiceTax.setManagementFee(managementFee);
					hotelOrderRowServiceTax.setConvenienceFee(newCompanyConfig.getHotelServiceTaxConfig().getConvenienceFee());
					hotelOrderRowServiceTax.setCreatedAt(newCompanyConfig.getHotelServiceTaxConfig().getCreatedAt());
					hotelOrderRowServiceTax.setKrishiKalyanCess(newCompanyConfig.getHotelServiceTaxConfig().getKrishiKalyanCess());
					hotelOrderRowServiceTax.setSwatchBharathCess(newCompanyConfig.getHotelServiceTaxConfig().getSwatchBharathCess());
					hotelOrderRowServiceTax.setTotalTax(newCompanyConfig.getHotelServiceTaxConfig().getTotalTax());
					hotelOrderRowServiceTax.setUpdatedAt(newCompanyConfig.getHotelServiceTaxConfig().getUpdatedAt());
					hotelOrderRow.setHotelOrderRowServiceTax(hotelOrderRowServiceTax);
					BaseServiceTax = finalPrice.add(managementFee).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					swachBharatCess = finalPrice.add(managementFee).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					krishiKalyanCess = finalPrice.add(managementFee).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);
					serviceOrGstTax=TotalServiceTax;
					convenienceFee=newCompanyConfig.getHotelServiceTaxConfig().getConvenienceFee();
				}	
			}

		}

		hotelOrderRow.setConfigId(String.valueOf(newCompanyConfig.getConfig_id()));

		//hotelOrderRow.setInternationalDestination(isInternational);
		if(flightGstTax==null) 
			hotelOrderRow.setTotGst(new BigDecimal("0.00"));
		
		BigDecimal finalPriceAfterTax=finalPrice.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
		User walletUser= userNew;
		if(userNew != null){
			if(sessionCompany.getCompanyRole()!=null && sessionCompany.getCompanyRole().isCorporate())
			{
				UserDAO userDAO = new UserDAO();
				walletUser = userDAO.getUserPasswordForLock(sessionCompany.getEmail());
			}
		}
		//ADDED  BY BASHA
		hotelOrderRow.getOrderCustomer().setCompanyId(sessionCompany.getCompanyid());
		hotelOrderRow.getOrderCustomer().setConfigId(newCompanyConfig.getConfig_id());
		hotelOrderRow.getOrderCustomer().setEmail(hotelOrderRoomInfoList.get(0).getHotelOrderGuests().get(0).getEmail());
		hotelOrderRow.getOrderCustomer().setCreatedByUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		hotelOrderRow.getOrderCustomer().setBookingType(CommonBookingStatusEnum.HOTEL.getMessage());
		//ENDED  BY BASHA
		hotelOrderRow.setFinalPrice(finalPrice);
		hotelOrderRow.setApiPrice(supplierPriceWithTax);
		hotelOrderRow.setMarkupAmount(totalMarkup);
		hotelOrderRow.setServiceTax(TotalServiceTax);
		hotelOrderRow.setTaxes(tax);
		hotelOrderRow.setConfirmationNo(hotelOrderRow.getOrderReference());
		hotelOrderRow.setApiConfirmationNo(hotelOrderRow.getConfirmationNo());
		hotelOrderRow.setApiBookingId(hotelOrderRow.getConfirmationNo());
		hotelOrderRow.setReferenceCode(orderId);
		hotelOrderRow.setOrderReference(orderId);
		hotelOrderRow.setNoOfRooms(hotelOrderRoomInfoList.size());
		hotelOrderRow.setStatusAction("Confirmed");
		hotelOrderRow.setBookingStatus("Confirmed");
		String narray[]=null;
		HotelOrderHotelData hotelOrderHotelData=hotelOrderRow.getHotelOrderHotelData();
		String city=hotelOrderHotelData.getCity();
	 
		if(city!=null && city.contains(",")){
			narray=city.split(",");
			city=narray[0];
		}
		hotelOrderHotelData.setCity(city);
		hotelOrderHotelData.setCreatedAt(hotelOrderRow.getCreatedAt());
		hotelOrderRow.setHotelOrderHotelData(hotelOrderHotelData);
		if(hotelOrderRoomInfoList!=null && hotelOrderRoomInfoList.size()>0){
			int leaderCount=0;
			for(HotelOrderRoomInfo hotelOrderRoomInfo:hotelOrderRoomInfoList){
				if(hotelOrderRoomInfo.getHotelOrderGuests()!=null && hotelOrderRoomInfo.getHotelOrderGuests().size()>0)
				{
					TripRequest tripRequestnew=null;
					try{
						tripRequestnew = tripRequestDao.getTripRequestTripNumber(hotelTravelRequestQuotation.getHotelTravelRequest().getId(),"H");
						
					}catch (Exception e) {
					}
					for(HotelOrderGuest hotelOrderGuest:hotelOrderRoomInfo.getHotelOrderGuests()){
						RmConfigTripDetailsModel rmConfigTripDetailsModel =hotelOrderGuest.getRmConfigTripDetailsModel();
						if(rmConfigTripDetailsModel!=null){
							rmConfigTripDetailsModel.setOrderId(orderId);
							hotelOrderGuest.setRoomInfo(hotelOrderRoomInfo);
							hotelOrderGuest.setRmConfigTripDetailsModel(rmConfigTripDetailsModel);
						}
						if(leaderCount==0)
							hotelOrderGuest.setLeader(true);
						else
							hotelOrderGuest.setLeader(false);
						hotelOrderGuestList.add(hotelOrderGuest);
						leaderCount++;
					}
				}
				leaderCount=0;
			}
		}
		hotelOrderRow.setTotalGuest(hotelOrderGuestList.size());
		if(flightGstTax==null) 
			hotelOrderRow.setTotGst(new BigDecimal("0.00"));
		RmConfigModel  rmConfigModel=rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid());
		   if(rmConfigModel!=null){
		   HotelOrderRowRmConfigStruct hotelOrderRowRmConfigStruct=new HotelOrderRowRmConfigStruct();
		   hotelOrderRowRmConfigStruct.setRmDynamicData(rmConfigModel.getDynamicFieldsData());
		   hotelOrderRow.setHotelOrderRowRmConfigStruct(hotelOrderRowRmConfigStruct);
		   }
		 
		HotelOrderRow hotelOrderRowNew= hotelOfflineBookingDao.insertHotelOrderRowInfo(hotelOrderRow);
		if(hotelOrderRowNew!=null){
			hotelOrderRowNew.setInvoiceNo(RandomConfigurationNumber.generateHotelOfflineInvoiceNumber(hotelOrderRowNew.getId()));
			try {
				hotelOrderRowNew.setHotelOrderRowCommissionList(HotelOrderDao.getHotelOrderRowCommissions(sessionCompany, hotelOrderRowNew));
				hotelOrderRowNew.setHotelOrderRowMarkupList(HotelOrderDao.getHotelOrderRowMarkups(sessionCompany,hotelOrderRowNew.getMarkupAmount(),hotelOrderRowNew));
			} catch (Exception e) {
				logger.error("Hotel markup & commission error "+e);			
			}
			Long orderIdTemp = hotelOrderRowNew.getId()+1000;
			orderId =RandomConfigurationNumber.generateHotelOfflineInvoiceNumber(orderIdTemp);
			hotelOrderRowNew.setOrderReference(orderId);
			hotelOrderRowNew=new HotelOrderDao().updateHotelOrderRowDetail(hotelOrderRowNew);
		}

		Map<String, BigDecimal> markups =  new LinkedHashMap<>();
		if(hotelOrderRowNew.getHotelOrderRowMarkupList()!=null && hotelOrderRowNew.getHotelOrderRowMarkupList().size()>0)
		{
			if(sessionCompany.getCompanyRole().isSuperUser())
			{
				//markups.put(String.valueOf(sessionCompany.getCompanyid()), insuranceOrderRowNew.getMarkUpamount());
				markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
			}
			else
			{
				for(HotelOrderRowMarkup hotelOrderRowMarkup :hotelOrderRowNew.getHotelOrderRowMarkupList())
				{
					markups.put(hotelOrderRowMarkup.getCompanyId(),hotelOrderRowMarkup.getMarkUp());
				}
			}

		}

		List<Company> companyListBottomToTop= new LinkedList<>();
		List<User> userListBottomToTop= new LinkedList<>();
		Map<Integer, CutandPayModel> cutAndPayUserMap = new LinkedHashMap<>();
		FlightOrderDao  flightOrderDao = new FlightOrderDao();
		HotelOrderDao  hotelOrderDao = new HotelOrderDao();
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
				cutAndPayUserMap = CommonUtil.getCutandPayModelUsersHotel(userListBottomToTop,markups,finalPriceAfterTax,walletUser);

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
										FlightOrderDao.deductUserWallet(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.HOTEL_REMARKS.getMessage(),orderId,hotelOrderRowNew.getInvoiceNo());
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
													flightOrderDao.creditUserWalletAmountForBookingFailed(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.HOTEL_FAILEDREMARKS.getMessage(),orderId,hotelOrderRowNew.getInvoiceNo());
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
			hotelOrderRowNew.setPaymentStatus("Failed");
			hotelOrderRowNew=hotelOrderDao.updateHotelOrderRowDetailPaymentStatus(hotelOrderRowNew);
			addActionMessage(CommonBookingStatusEnum.WALLET_ERROR.getMessage());
			return ERROR;
		}else{
			hotelOrderRowNew.setPaymentStatus("Success");
			hotelOrderRowNew=hotelOrderDao.updateHotelOrderRowDetailPaymentStatus(hotelOrderRowNew);
		}

		/*	
			if(walletUser!=null && FlightOrderDao.deductUserWallet(finalPriceAfterTax,walletUser,userDao,walletRemarks,orderId,hotelOrderRowNew.getInvoiceNo()))
			{
				logger.debug("Wallet Updated");
			}
			else{
				addActionMessage(CommonBookingStatusEnum.WALLET_ERROR.getMessage());
				return ERROR;
			}*/

		if(hotelOrderRoomInfoList!=null && hotelOrderRoomInfoList.size()>0){
			for(HotelOrderRoomInfo hotelOrderRoomInfo:hotelOrderRoomInfoList){
				hotelOrderRoomInfo.setCreatedAt(hotelOrderRowNew.getCreatedAt());
				hotelOrderRoomInfo.setName(hotelOrderRoomInfo.getRoomType());//hotelOrderRowNew.getHotelOrderHotelData().getName());
				hotelOrderRoomInfo.setReferenceCode(hotelOrderRowNew.getOrderReference());
				hotelOrderRoomInfo.setStatus(hotelOrderRowNew.getStatusAction());
				hotelOrderRoomInfo.setNoofguests(hotelOrderRoomInfo.getHotelOrderGuests().size());
				hotelOrderRoomInfo.setHotelOrderRow(hotelOrderRowNew);
				hotelOrderRoomInfo.setRoomType(hotelOrderRoomInfo.getName());
				//hotelOrderRoomInfoListNew.add(hotelOrderRoomInfo);
				hotelOfflineBookingDao.insertHotelOrderRoomInfoInfo(hotelOrderRoomInfo);
				if(hotelOrderRoomInfo.getHotelOrderGuests()!=null && hotelOrderRoomInfo.getHotelOrderGuests().size()>0)
				{
					for(HotelOrderGuest hotelOrderGuest:hotelOrderRoomInfo.getHotelOrderGuests()){
						//hotelOrderGuestListNew.add(hotelOrderGuest);
						hotelOrderGuest.setPaxId(RandomConfigurationNumber.generateRandomPaxID());
						hotelOrderGuest.setCreatedAt(hotelOrderRowNew.getCreatedAt());
						RmConfigTripDetailsModel rmConfigTripDetailsModel=hotelOrderGuest.getRmConfigTripDetailsModel();
						if(rmConfigTripDetailsModel!=null){
							rmConfigTripDetailsModel.setOrderId(orderId);
							hotelOrderGuest.setRmConfigTripDetailsModel(rmConfigTripDetailsModel);
						}
						hotelOrderGuest.setRoomInfo(hotelOrderRoomInfo);
						hotelOfflineBookingDao.insertHotelOrderGuestnfo(hotelOrderGuest);

					} 
				}

			}
		}

		PaymentCardDetailsConfig paymentCardInfo=null;
		if(hotelOrderRowNew!=null ){
			paymentTransaction.setCreatedAt(hotelOrderRowNew.getCreatedAt());
			paymentTransaction.setCurrency(hotelOrderRowNew.getBookingCurrency());
			paymentTransaction.setPayment_system(customerPaymentType);
			paymentTransaction.setPayment_status(hotelOrderRowNew.getPaymentStatus());
			paymentTransaction.setAmount(hotelOrderRowNew.getFinalPrice().add(hotelOrderRowNew.getServiceTax()).add(convenienceFee).add(managementFee));
			paymentTransaction.setApi_transaction_id(hotelOrderRowNew.getOrderReference());
			try
			{
				paymentCardInfo =paymentCardDetailsDao.getPaymentCardInfo(new PaymentCardDetailsConfig(paymentTransaction.getClientCardHolderId()));
			}
			catch(Exception e)
			{
				logger.error("paymentCardInfo error "+e);
			}
			if(paymentCardInfo!=null){
				paymentTransaction.setPaymentCardDetailsConfig(paymentCardInfo);
			}

			if(customerPaymentType.equalsIgnoreCase("Full")){
				paymentTransaction.setIsPaymentSuccess(true);
				paymentTransaction.setPayment_status("Paid");
			}
			else if(customerPaymentType.equalsIgnoreCase("Partial")){
				if(paymentTransaction.getClientAmount()!=null){
					paymentTransaction.setIsPaymentSuccess(false);
					paymentTransaction.setPayment_status("Paid");
				}
				else{
					paymentTransaction.setIsPaymentSuccess(false);
					paymentTransaction.setPayment_status("Pending"); 
				}
			}
			else{
				paymentTransaction.setIsPaymentSuccess(false);
				paymentTransaction.setPayment_status("Pending"); 
			} 
			paymentTransaction.setTransactionId(RandomConfigurationNumber.generateHotelandFlightPaymentTxKey());
			PaymentTransaction paymentTransactionNew= hotelOfflineBookingDao.insertPaymentTransactionInfo(paymentTransaction);
			if(paymentTransactionNew!=null)
			{ 
				if(paymentTransaction.getClientAmount()!=null)
				{
					if(hotelOrderRowNew.getFinalPrice().setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getClientAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
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
					paymentTransactionDetail.setCompanyId(sessionCompany.getCompanyid());
					paymentTransactionDetail.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					new HotelOfflineBookingDao().insertHotelCustomerPaymentTxDetail(paymentTransactionDetail);

				}


				ApiProviderPaymentTransaction apiProviderPaymentTransaction  = new ApiProviderPaymentTransaction();
				apiProviderPaymentTransaction.setAmount(hotelOrderRowNew.getApiPrice());
				apiProviderPaymentTransaction.setCurrency(hotelOrderRowNew.getApiCurrency());
				apiProviderPaymentTransaction.setApi_transaction_id(hotelOrderRowNew.getOrderReference());
				apiProviderPaymentTransaction.setCreatedAt(hotelOrderRowNew.getCreatedAt());
				apiProviderPaymentTransaction.setPayment_system(supplierPaymentType);
				apiProviderPaymentTransaction.setPayment_method(apiProviderPaymentTransaction.getPayment_method());
				paymentCardInfo =paymentCardDetailsDao.getPaymentCardInfo(new PaymentCardDetailsConfig(paymentTransaction.getSupplierCardHolderId()));
				if(paymentCardInfo!=null){
					apiProviderPaymentTransaction.setPaymentCardDetailsConfig(paymentCardInfo);
				}
				if(supplierPaymentType.equalsIgnoreCase("Full")){
					apiProviderPaymentTransaction.setIsPaymentSuccess(true);
					apiProviderPaymentTransaction.setPayment_status("Paid");
				}
				else if(supplierPaymentType.equalsIgnoreCase("Partial")){
					if(paymentTransaction.getApiProviderAmount()!=null){
						apiProviderPaymentTransaction.setIsPaymentSuccess(false);
						apiProviderPaymentTransaction.setPayment_status("Paid");
					}
					else{
						apiProviderPaymentTransaction.setIsPaymentSuccess(false);
						apiProviderPaymentTransaction.setPayment_status("Pending"); 
					}
				}
				else{
					apiProviderPaymentTransaction.setIsPaymentSuccess(false);
					apiProviderPaymentTransaction.setPayment_status("Pending"); 
				} 
				apiProviderPaymentTransaction.setCompanyId(sessionCompany.getCompanyid());
				apiProviderPaymentTransaction.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());

				apiProviderPaymentTransaction= hotelOfflineBookingDao.insertSupplierPaymentTransactionInfo(apiProviderPaymentTransaction);
				if(paymentTransaction.getApiProviderAmount()!=null && apiProviderPaymentTransaction!=null)
				{
					if(hotelOrderRowNew.getFinalPrice().setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getApiProviderAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
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
					apiProviderPaymentTransactionDetail.setPaymentPaidBy(paymentTransaction.getPaidBySupplier());
					apiProviderPaymentTransactionDetail.setApiProviderPaymentTransaction(apiProviderPaymentTransaction);
					apiProviderPaymentTransactionDetail.setCreatedAt(new Timestamp(new Date().getTime()));
					apiProviderPaymentTransactionDetail.setCompanyId(sessionCompany.getCompanyid());
					apiProviderPaymentTransactionDetail.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					new HotelOfflineBookingDao().insertHotelApiProviderPaymentTransactionDetail(apiProviderPaymentTransactionDetail);

				}
			}
			HotelTravelRequestQuotation hotelTravelRequestQuotation=new HotelTravelRequestDao().updateHotelRequestQuotationWithOrderId(hotelOrderRowNew,hotelRequestQuotationId);
			if(hotelTravelRequestQuotation!=null){
				HotelFlightBookingStatus hotelFlightBookingStatus=new HotelFlightBookingStatus();
				if(hotelTravelRequestQuotation.getHotetFlightBookingStatus()!=null && hotelTravelRequestQuotation.getHotetFlightBookingStatus().getId()!=null)
					hotelFlightBookingStatus.setId(hotelTravelRequestQuotation.getHotetFlightBookingStatus().getId());
				hotelFlightBookingStatus.setBooked(TravelRequestStatusEnum.BOOKED.getValue());
				TripRequestDao tripRequestDao = new TripRequestDao();
				TripRequest tripRequest = tripRequestDao.getTripRequestTripNumber(hotelTravelRequestQuotation.getHotelTravelRequest().getId(),"H");
				RmConfigDao rmConfigDao = new RmConfigDao();
				RmConfigTripDetailsModel rmConfigTripDetailsModel = rmConfigDao.getTripConfigDetailsByOrdertype(tripRequest.getTripId(),"Hotel");
				if(rmConfigTripDetailsModel!=null){
					rmConfigTripDetailsModel.setOrderId(hotelOrderRowNew.getOrderReference());
					rmConfigDao.updateTripConfigDetails(rmConfigTripDetailsModel);
				}

				new HotelTravelRequestDao().updateHotelOrFlightQuotationBookingStatus(hotelFlightBookingStatus);
				setHotelRequestQuotationId(hotelTravelRequestQuotation.getHotelTravelRequest().getId());
				//new CarTravelRequestDao().updateHotelTravelRequestQuotationHide(hotelTravelRequestQuotation.getHotelTravelRequest().getId());
				new CompanyDAO().insertEmail(hotelOrderRowNew.getOrderReference(), 0, Email.EMAIL_TYPE_HOTEL_OFFLINE_ONLINE_INVOICE_SEND);
				setHotelQuotationRequestId(hotelTravelRequestQuotation.getHotelTravelRequest().getId());


			}

			addActionMessage("Successfully Hotel Booking Done.");
			return SUCCESS;
		}
		else{
			addActionMessage("We found some error while booking.");
			return ERROR;
		}

	}

	public String insertHotelOrderForQuickBooking(){
		User sessionUser=(User) sessionMap.get("User");
		Company sessionCompany=(Company)sessionMap.get("Company");
		//TravelRequest Started
		if((hotelTravelRequest!=null  && tripId!=null) || (hotelTravelRequest!=null  && tripId==null)){
		if(hotelTravelRequest!=null  && tripId!=null){
			try{
				hotelTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				hotelTravelRequest.setCompanyId(sessionCompany.getCompanyid());
				hotelTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
				if(hotelTravelRequest.getCheckIn()!=null){
					hotelTravelRequest.setCheckInDate(DateConversion.StringToDate(hotelTravelRequest.getCheckIn()));
				}else{

				}

				if(hotelTravelRequest.getCheckOut()!=null){
					hotelTravelRequest.setCheckOutDate(DateConversion.StringToDate(hotelTravelRequest.getCheckOut()));
				}else{

				}



				hotelTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
				int noOfNights = CommonUtil.getNoofStayDays(hotelTravelRequest.getCheckIn(),
						hotelTravelRequest.getCheckOut());
				hotelTravelRequest.setNoOfNights(noOfNights);
				hotelTravelRequest.setTitle(hotelOrderRow.getOrderCustomer().getTitle());
				hotelTravelRequest.setFirstName(hotelOrderRow.getOrderCustomer().getFirstName());
				hotelTravelRequest.setLastName(hotelOrderRow.getOrderCustomer().getLastName());
				hotelTravelRequest.setCity(hotelOrderRow.getOrderCustomer().getCity());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(hotelTravelRequest!=null  && tripId==null){
			
			try{
				hotelTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				hotelTravelRequest.setCompanyId(sessionCompany.getCompanyid());
				hotelTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
				if(hotelTravelRequest.getCheckIn()!=null){
					hotelTravelRequest.setCheckInDate(DateConversion.StringToDate(hotelTravelRequest.getCheckIn()));
				}else{

				}

				if(hotelTravelRequest.getCheckOut()!=null){
					hotelTravelRequest.setCheckOutDate(DateConversion.StringToDate(hotelTravelRequest.getCheckOut()));
				}else{

				}



				hotelTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
				int noOfNights = CommonUtil.getNoofStayDays(hotelTravelRequest.getCheckIn(),
						hotelTravelRequest.getCheckOut());
				hotelTravelRequest.setNoOfNights(noOfNights);
				hotelTravelRequest.setTitle(hotelOrderRow.getOrderCustomer().getTitle());
				hotelTravelRequest.setFirstName(hotelOrderRow.getOrderCustomer().getFirstName());
				hotelTravelRequest.setLastName(hotelOrderRow.getOrderCustomer().getLastName());
				hotelTravelRequest.setCity(hotelOrderRow.getOrderCustomer().getCity());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			HotelTravelRequest newHoteltravelRequest = hotelOrderRowFineSheetDao.insertHotelQuotationRowDetails(hotelTravelRequest);
			if(newHoteltravelRequest!=null){
				//setHotelQuotationRequestId(newHoteltravelRequest.getId());
//				boolean isCompanyRmConfig=rmConfigDao.isCompanyHavingRmConfig(sessionCompany);
				if (tripId != null) {
					TripRequest tripRequest = new TripRequest();
					tripRequest.setId(tripId);
					tripRequest.setHotelTravelRequest(newHoteltravelRequest);
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
					tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
					tripRequest = tripRequestDao.updateTripRequestById(tripRequest);
				/*if(isCompanyRmConfig){
					RmConfigTripDetailsModel rmconfigTripDetailsModel = new GetNewRmConfigDetail().getRmConfigDetail(configTripDetailsModel,"Hotel");
					rmConfigDao.insertTripConfigDetails(rmconfigTripDetailsModel);
				}*/

					if (tripRequest != null && tripRequest.getId() > 0) {
						addActionMessage("Successfully created");
					}else{
						addActionMessage("Something went wrong.Please wait.");
					}
				}else {
					TripRequest tripRequest = new TripRequest();
					tripRequest.setHotelTravelRequest(newHoteltravelRequest);
					tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
					//tripRequest.setTripId(RandomConfigurationNumber.generateTripId(newHoteltravelRequest.getId()));
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
					tripRequest = tripRequestDao.insertTripRequest(tripRequest);
					if (tripRequest != null && tripRequest.getId() > 0) {
						if (tripRequest != null && tripRequest.getId() > 0) {
							tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
							/*if(isCompanyRmConfig){
								RmConfigTripDetailsModel rmconfigTripDetailsModel = new GetNewRmConfigDetail().getRmConfigDetail(configTripDetailsModel,"Hotel");
								rmConfigDao.insertTripConfigDetails(rmconfigTripDetailsModel);
							}*/
							if (tripRequestDao.updateTripRequestNumber(tripRequest))
								addActionMessage("Successfully created");
							else
								addActionMessage("Something went wrong.Please wait.");
						}

					}
				}
				new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(newHoteltravelRequest.getId()), "Hotal book Request created","Hotal book Request notification", NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(), false,false, false, true, false, false);

				//TravelRequest Ended

				//quotation is started
				if(newHoteltravelRequest.getId()!=null){


					HotelTravelRequestQuotation QuotationObj=new HotelTravelRequestQuotation();

					QuotationObj.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
					HotelFlightBookingStatus hotetFlightBookingStatus=new HotelFlightBookingStatus();
					hotetFlightBookingStatus.setCreated(TravelRequestStatusEnum.CREATED.getValue());
					QuotationObj.setHotetFlightBookingStatus(hotetFlightBookingStatus);
					QuotationObj.setCheckInDate(newHoteltravelRequest.getCheckInDate());
					QuotationObj.setCheckOutDate(newHoteltravelRequest.getCheckOutDate());
					QuotationObj.setHotelName(hotelOrderRow.getHotelOrderHotelData().getName());
					QuotationObj.setCheckInTime(hotelTravelRequestQuotation.getCheckInTime());
					QuotationObj.setCheckOutTime(hotelTravelRequestQuotation.getCheckOutTime());
					QuotationObj.setAdultCount(hotelTravelRequestQuotation.getAdultCount());
					QuotationObj.setRoomCount(hotelOrderRoomInfoList.size());
					QuotationObj.setChildCount(hotelTravelRequestQuotation.getChildCount());
					QuotationObj.setBookingMode("Offline");
					QuotationObj.setBreakfast("YES");
					QuotationObj.setTaxes("YES");
					QuotationObj.setInternet("YES");
					QuotationObj.setPreferProperty(true);
					QuotationObj.setRoomRatePerNight(hotelOrderRow.getApiPrice().toString());
					QuotationObj.setRoomType(hotelOrderRoomInfoList.get(0).getRoomType());
					QuotationObj.setHotelCity(hotelOrderRow.getOrderCustomer().getCity());
					QuotationObj.setHotelAddress(hotelOrderRow.getHotelOrderHotelData().getAddress1());
					QuotationObj.setCurrency(hotelOrderRow.getApiCurrency());
					QuotationObj.setCompanyId(newHoteltravelRequest.getCompanyId());
					QuotationObj.setUserId(newHoteltravelRequest.getUserId());
					QuotationObj.setNightCount(newHoteltravelRequest.getNoOfNights());
					QuotationObj.setCityCode(newHoteltravelRequest.getCityCode());
					QuotationObj.setAvailablePaymentOption(hotelTravelRequestQuotation.getAvailablePaymentOption());
					hotelTravelRequestQuotationsListnew.add(QuotationObj);
					boolean isInserted= hotelTravelRequestDao.insertHotelQuotationList(hotelTravelRequestQuotationsListnew, newHoteltravelRequest);
					if(isInserted){
						for(HotelTravelRequestQuotation hotelTravelRequestQuote: hotelTravelRequestQuotationsListnew){
							//setHotelQuotationRequestId(hotelTravelRequestQuote.getId());
							setHotelRequestQuotationId(hotelTravelRequestQuote.getId());
							setNoOfnightsCount(hotelTravelRequestQuote.getNightCount());
							setNoofrooms(hotelTravelRequestQuote.getRoomCount());
						}
						new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(newHoteltravelRequest.getId()),"Hotal quotation created","Hotal qutation notification",NotificationInventoryTypeEnum.HOTEL_QUOATATION.getId(),false,false,false,true,false,false);

					}
					else{
						addActionError("We found some error.Please Try again.");
						return ERROR;
					} 

				}
				if(hotelOrderRow.getOrderCustomer().getCity()!=null){
					String itenaryType=hotelOrderRow.getOrderCustomer().getCity().contains("(IN)")?"India":"";
					String CityNew=hotelOrderRow.getOrderCustomer().getCity();
					hotelOrderRow.getHotelOrderHotelData().setCity(CityNew);
					hotelOrderRow.getHotelOrderHotelData().setCountry(itenaryType);
					//HotelOrderHotelData hotelOrderHotelDatainsert=new HotelOrderDao().insertHotelData(hotelOrderRow.getHotelOrderHotelData());
				}


			}
			else {
				addActionError("We found some error.Please Try again.");
				return ERROR;
			}
			
		}
	return insertHotelOrderRowInfo();
}
	
	public String getHotelOfflineVoucher(){
		hotelReport= new HotelOrderDao().hotelRoomandGuestandHotelOrderRowInfo(getHotelOrderId());
		HotelTravelRequestQuotation hotelQuotation=new HotelTravelRequestDao().getHotelQuotationDetails(getHotelOrderId());
		if(hotelReport!=null && hotelQuotation!=null && hotelReport.getHotelOrderRow()!=null)
		{

			hotelReport.setCheckInDate(DateConversion.convertDateToStringDatewirhDDMonthYear(hotelReport.getHotelOrderRow().getCheckInDate()));
			hotelReport.setCheckOutDate(DateConversion.convertDateToStringDatewirhDDMonthYear(hotelReport.getHotelOrderRow().getCheckOutDate()));
			if(hotelReport!=null && hotelReport.getHotelOrderRoomInfo()!=null && hotelReport.getHotelOrderRoomInfo().size()>0)
			{
				for(HotelOrderRoomInfo hotelOrderRoomInfo:hotelReport.getHotelOrderRoomInfo()){
					List<HotelOrderGuest> hotelOrderGuestList= new HotelOrderDao().hotelOrderGuestInfo(hotelOrderRoomInfo.getId());
					if(hotelOrderGuestList!=null && hotelOrderGuestList.size()>0)
					{
						for(HotelOrderGuest hotelOrderGuest:hotelOrderGuestList){
							if(hotelOrderGuest.getLeader()!=null && hotelOrderGuest.getLeader()==true){
								HotelOffineVoucherUtility hotelOffineVoucherUtility= new HotelOffineVoucherUtility(hotelOrderGuestList.size(), hotelOrderRoomInfo.getRoomType(), hotelOrderRoomInfo.getInclusions());
								hotelVoucherMap.put(hotelOffineVoucherUtility, hotelOrderGuest);
							}
						}
					}
				}
			} 

			if(hotelQuotation!=null){
				setHotelQuotation(hotelQuotation);
			}
		}
		return SUCCESS;
	}


	public String getHotelOfflineInvoice(){
		hotelReport= new HotelOrderDao().hotelRoomandGuestandHotelOrderRowInfo(getHotelOrderId());
		HotelTravelRequestQuotation hotelQuotation=new HotelTravelRequestDao().getHotelQuotationDetails(getHotelOrderId());
		if(hotelReport!=null && hotelQuotation!=null)
		{

			hotelReport.setCheckInDate(DateConversion.convertDateToStringDatewirhDDMonthYear(hotelReport.getHotelOrderRow().getCheckInDate()));
			hotelReport.setCheckOutDate(DateConversion.convertDateToStringDatewirhDDMonthYear(hotelReport.getHotelOrderRow().getCheckOutDate()));
			if(hotelReport!=null && hotelReport.getHotelOrderRoomInfo()!=null && hotelReport.getHotelOrderRoomInfo().size()>0)
			{
				for(HotelOrderRoomInfo hotelOrderRoomInfo:hotelReport.getHotelOrderRoomInfo()){
					List<HotelOrderGuest> hotelOrderGuestList= new HotelOrderDao().hotelOrderGuestInfo(hotelOrderRoomInfo.getId());
					if(hotelOrderGuestList!=null && hotelOrderGuestList.size()>0)
					{
						for(HotelOrderGuest hotelOrderGuest:hotelOrderGuestList){
							if(hotelOrderGuest.getLeader()!=null && hotelOrderGuest.getLeader()==true){
								HotelOffineVoucherUtility hotelOffineVoucherUtility= new HotelOffineVoucherUtility(hotelOrderGuestList.size(), hotelOrderRoomInfo.getRoomType(), hotelOrderRoomInfo.getInclusions());
								hotelVoucherMap.put(hotelOffineVoucherUtility, hotelOrderGuest);
							}
						}
					}
				}
			} 

			if(hotelQuotation!=null){
				setHotelQuotation(hotelQuotation);
			}
		}
		return SUCCESS;
	}

	public String getCustomerPaymentTransactionInfo(){
		BigDecimal partialAmtCount= new BigDecimal("0.00");
		BigDecimal restAmtCount= new BigDecimal("0.00");
		paymentCardList=paymentCardDetailsDao.getPaymentCardDetailsList(false);
		HotelOrderDao hotelOrderDao=new HotelOrderDao();
		HotelOrderRow HotelOrderRow=hotelOrderDao.getHotelOrderRowInfo(getHotelOrderId());
		//logger.info("getHotelOrderId------------"+HotelOrderRow.getOrderReference());
		PaymentTransaction paymentTransactionNew= hotelOrderDao.getPaymentTransactionInfo(HotelOrderRow.getOrderReference()) ;
		if(paymentTransactionNew!=null && paymentTransactionNew.getPayment_system()!=null &&  ( paymentTransactionNew.getPayment_system().equalsIgnoreCase("Partial") ||  paymentTransactionNew.getPayment_system().equalsIgnoreCase("Zero"))){
			List<PaymentTransactionDetail> newPaymentTransactionDetailList=hotelOfflineBookingDao.getPaymentTransactionDetailList(paymentTransactionNew.getApi_transaction_id());
			logger.info("newPaymentTransactionDetailList------------"+newPaymentTransactionDetailList);
			if(newPaymentTransactionDetailList!=null && newPaymentTransactionDetailList.size()>0){
				for(PaymentTransactionDetail paymentTransactionDetail: newPaymentTransactionDetailList){
					paymentTransactionDetail.setCreatedDate(DateConversion.convertTimestampToString(paymentTransactionDetail.getCreatedAt()));
					if(paymentTransactionDetail.getAmount()!=null)
					{
						paymentTransactionDetail.setAmount(paymentTransactionDetail.getAmount().setScale(2, BigDecimal.ROUND_UP));
						restAmtCount =restAmtCount.add(paymentTransactionDetail.getAmount());
						BigDecimal balance=paymentTransactionNew.getAmount().subtract(restAmtCount);
						paymentTransactionDetail.setBalance(balance);
					}
					paymentTransactionDetailList.add(paymentTransactionDetail);
					partialAmtCount=partialAmtCount.add(paymentTransactionDetail.getAmount());


				}
			}
			BigDecimal balance=	paymentTransactionNew.getAmount().subtract(partialAmtCount);
			paymentTransactionNew.setBalance(balance);
		}


		HotelTravelRequestQuotation hotelQuotation=new HotelTravelRequestDao().getHotelQuotationDetails(HotelOrderRow.getId());
		hotelQuotation.setHotelOrderRow(HotelOrderRow);
		setHotelQuotation(hotelQuotation);
		setPaymentTransaction(paymentTransactionNew);
		//logger.info("paymentTransactionDetailList------------"+paymentTransactionDetailList);
		return SUCCESS;
	}

	//pending
	public String getApiProviderPaymentTransactionInfo(){
		//logger.info("getHotelOrderId()------------"+getHotelOrderId());
		BigDecimal partialAmtCount= new BigDecimal("0.00");
		BigDecimal restAmtCount= new BigDecimal("0.00");
		HotelOrderDao hotelOrderDao=new HotelOrderDao();
		paymentCardList=paymentCardDetailsDao.getPaymentCardDetailsList(true);
		HotelOrderRow HotelOrderRow=hotelOrderDao.getHotelOrderRowInfo(getHotelOrderId());
		ApiProviderPaymentTransaction paymentTransactionNew= hotelOrderDao.getApiProviderPaymentTransactionInfo(HotelOrderRow.getOrderReference()) ;
		if(paymentTransactionNew.getAmount()!=null)
		{
			paymentTransactionNew.setAmount(paymentTransactionNew.getAmount().setScale(2, BigDecimal.ROUND_UP));
			if(paymentTransactionNew!=null &&  paymentTransactionNew.getPayment_system()!=null && ( paymentTransactionNew.getPayment_system().equalsIgnoreCase("Partial") ||  paymentTransactionNew.getPayment_system().equalsIgnoreCase("Zero"))){
				List<ApiProviderPaymentTransactionDetail> newPaymentTransactionDetailList=hotelOfflineBookingDao.getApiProviderPaymentTransactionDetailList(paymentTransactionNew.getApi_transaction_id());
				if(newPaymentTransactionDetailList!=null && newPaymentTransactionDetailList.size()>0){
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
		setApiProviderPaymentTransaction(paymentTransactionNew);
		HotelTravelRequestQuotation hotelQuotation=new HotelTravelRequestDao().getHotelQuotationDetails(HotelOrderRow.getId());
		hotelQuotation.setHotelOrderRow(HotelOrderRow);
		setHotelQuotation(hotelQuotation);
		return SUCCESS;
	}

	public static FlightGstTax getFlightGSTTax(HotelOrderRowGstTax  hotelOrderRowGstTax,Company childCompany,Company parentCompany,HotelOrderRow hotelOrderRow){
		
		CompanyEntity  companyEntity=new CompanyEntity();
		String entityState=null;
		if(hotelOrderRow.getCompanyEntityId()!=null) 
		     companyEntity = new CompanyDAO().companyEntityProfile(hotelOrderRow.getCompanyEntityId().intValue());
		    if(companyEntity!=null && companyEntity.getState()!=null) 
		    	entityState=companyEntity.getState(); 
		    
		BigDecimal managementFee  =hotelOrderRowGstTax.getManagementFee()!=null?hotelOrderRowGstTax.getManagementFee():new BigDecimal("0.0");
		BigDecimal CGSTAmount = new BigDecimal("0.0");
		BigDecimal CGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTAmount = new BigDecimal("0.0");
		//BigDecimal IGST = new BigDecimal("0.0");
		//BigDecimal UGST = new BigDecimal("0.0");
		BigDecimal totalGstAmount = new BigDecimal("0.0");
		BigDecimal totalGstPer = new BigDecimal("0.0");
		boolean isParentCompanyUT=IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());
		
		CGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getCGST());
		CGSTPer  = hotelOrderRowGstTax.getCGST();
		
		
		if(entityState!=null){ 
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(entityState.trim());
		
		if(isParentCompanyUT && isBillingCompanyUT){
			CommonGSTPer =hotelOrderRowGstTax.getUGST();
			CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getUGST());
			hotelOrderRowGstTax.setIGST(new BigDecimal("0.0"));
			hotelOrderRowGstTax.setSGST(new BigDecimal("0.0"));

		}
		if(!isParentCompanyUT && !isBillingCompanyUT){
			if(entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
				CommonGSTPer =hotelOrderRowGstTax.getSGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getSGST());    
				hotelOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setUGST(new BigDecimal("0.0"));
			}
			if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
				CommonGSTPer =hotelOrderRowGstTax.getIGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getIGST());   
				 CGSTPer = new BigDecimal(0);
				 CGSTAmount = new BigDecimal(0);
				hotelOrderRowGstTax.setSGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setCGST(CGSTPer);
			}
		}

		if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(entityState.trim())){
			CommonGSTPer =hotelOrderRowGstTax.getUGST();
			CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getUGST());
			hotelOrderRowGstTax.setIGST(new BigDecimal("0.0"));
			hotelOrderRowGstTax.setSGST(new BigDecimal("0.0"));
		}

		if(isParentCompanyUT && !isBillingCompanyUT){
			if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(entityState.trim())){
				CommonGSTPer =hotelOrderRowGstTax.getIGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getIGST()); 
				 CGSTPer = new BigDecimal(0);
				 CGSTAmount = new BigDecimal(0);
				hotelOrderRowGstTax.setSGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setCGST(CGSTPer);
			}
		}
		
		}else{
		boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim()); 
		if(isParentCompanyUT && isBillingCompanyUT){
			CommonGSTPer =hotelOrderRowGstTax.getUGST();
			CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getUGST());
			hotelOrderRowGstTax.setIGST(new BigDecimal("0.0"));
			hotelOrderRowGstTax.setSGST(new BigDecimal("0.0"));

		}
		if(!isParentCompanyUT && !isBillingCompanyUT){
			if(childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
				CommonGSTPer =hotelOrderRowGstTax.getSGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getSGST());    
				hotelOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setUGST(new BigDecimal("0.0"));
			}
			if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
				CommonGSTPer =hotelOrderRowGstTax.getIGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getIGST());    
				 CGSTPer = new BigDecimal(0);
				 CGSTAmount = new BigDecimal(0);
				hotelOrderRowGstTax.setSGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setCGST(CGSTPer);
			}
		}

		if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
			CommonGSTPer =hotelOrderRowGstTax.getUGST();
			CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getUGST());
			hotelOrderRowGstTax.setIGST(new BigDecimal("0.0"));
			hotelOrderRowGstTax.setSGST(new BigDecimal("0.0"));
		}

		if(isParentCompanyUT && !isBillingCompanyUT){
			if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
				CommonGSTPer =hotelOrderRowGstTax.getIGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(hotelOrderRowGstTax.getIGST());    
				 CGSTPer = new BigDecimal(0);
				 CGSTAmount = new BigDecimal(0);
				hotelOrderRowGstTax.setSGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				hotelOrderRowGstTax.setCGST(CGSTPer);
			}
		}
			
		}

		totalGstPer=CGSTPer.add(CommonGSTPer);
		totalGstAmount = CGSTAmount.add(CommonGSTAmount); 
		FlightGstTax flightServiceTax = new FlightGstTax();
		flightServiceTax.setTotalTax(totalGstPer);
		flightServiceTax.setTotalGstAmount(totalGstAmount);
		return flightServiceTax;
	}
	 
	public HotelReport getHotelReport() {
		return hotelReport;
	}

	public void setHotelReport(HotelReport hotelReport) {
		this.hotelReport = hotelReport;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public HotelOrderRow getModel() {
		// TODO Auto-generated method stub
		return hotelOrderRow;
	}

	public HotelOrderRow getHotelOrderRow() {
		return hotelOrderRow;
	}

	public void setHotelOrderRow(HotelOrderRow hotelOrderRow) {
		this.hotelOrderRow = hotelOrderRow;
	}

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public Long getHotelRequestQuotationId() {
		return hotelRequestQuotationId;
	}

	public void setHotelRequestQuotationId(Long hotelRequestQuotationId) {
		this.hotelRequestQuotationId = hotelRequestQuotationId;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<HotelOrderGuest> getHotelOrderGuestList() {
		return hotelOrderGuestList;
	}

	public void setHotelOrderGuestList(List<HotelOrderGuest> hotelOrderGuestList) {
		this.hotelOrderGuestList = hotelOrderGuestList;
	}

	public List<HotelOrderRoomInfo> getHotelOrderRoomInfoList() {
		return hotelOrderRoomInfoList;
	}

	public void setHotelOrderRoomInfoList(List<HotelOrderRoomInfo> hotelOrderRoomInfoList) {
		this.hotelOrderRoomInfoList = hotelOrderRoomInfoList;
	}



	public Long getHotelOrderId() {
		return hotelOrderId;
	}

	public void setHotelOrderId(Long hotelOrderId) {
		this.hotelOrderId = hotelOrderId;
	}
	public List<HotelOrderCancellationPolicy> getHotelOrderCancellationPolicyList() {
		return hotelOrderCancellationPolicyList;
	}

	public void setHotelOrderCancellationPolicyList(List<HotelOrderCancellationPolicy> hotelOrderCancellationPolicyList) {
		this.hotelOrderCancellationPolicyList = hotelOrderCancellationPolicyList;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Map<HotelOffineVoucherUtility, HotelOrderGuest> getHotelVoucherMap() {
		return hotelVoucherMap;
	}

	public void setHotelVoucherMap(Map<HotelOffineVoucherUtility, HotelOrderGuest> hotelVoucherMap) {
		this.hotelVoucherMap = hotelVoucherMap;
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
	public List<PaymentTransactionDetail> getPaymentTransactionDetailList() {
		return paymentTransactionDetailList;
	}

	public void setPaymentTransactionDetailList(List<PaymentTransactionDetail> paymentTransactionDetailList) {
		this.paymentTransactionDetailList = paymentTransactionDetailList;
	}  

	public List<ApiProviderPaymentTransactionDetail> getApiProviderpaymentTransactionDetailList() {
		return apiProviderpaymentTransactionDetailList;
	}

	public void setApiProviderpaymentTransactionDetailList(
			List<ApiProviderPaymentTransactionDetail> apiProviderpaymentTransactionDetailList) {
		this.apiProviderpaymentTransactionDetailList = apiProviderpaymentTransactionDetailList;
	}
	public ApiProviderPaymentTransaction getApiProviderPaymentTransaction() {
		return apiProviderPaymentTransaction;
	}

	public void setApiProviderPaymentTransaction(ApiProviderPaymentTransaction apiProviderPaymentTransaction) {
		this.apiProviderPaymentTransaction = apiProviderPaymentTransaction;
	}

	public HotelTravelRequestQuotation getHotelQuotation() {
		return hotelQuotation;
	}

	public void setHotelQuotation(HotelTravelRequestQuotation hotelQuotation) {
		this.hotelQuotation = hotelQuotation;
	}

	public String getAlternativeEmail() {
		return alternativeEmail;
	}

	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}

	public List<PaymentCardDetailsConfig> getPaymentCardList() {
		return paymentCardList;
	}

	public void setPaymentCardList(List<PaymentCardDetailsConfig> paymentCardList) {
		this.paymentCardList = paymentCardList;
	}

	public int getNoOfnightsCount() {
		return noOfnightsCount;
	}

	public void setNoOfnightsCount(int noOfnightsCount) {
		this.noOfnightsCount = noOfnightsCount;
	}

	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}

	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
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

	public HotelTravelRequest getHotelTravelRequest() {
		return hotelTravelRequest;
	}

	public void setHotelTravelRequest(HotelTravelRequest hotelTravelRequest) {
		this.hotelTravelRequest = hotelTravelRequest;
	}


	public HotelTravelRequestQuotation getHotelTravelRequestQuotation() {
		return hotelTravelRequestQuotation;
	}

	public void setHotelTravelRequestQuotation(HotelTravelRequestQuotation hotelTravelRequestQuotation) {
		this.hotelTravelRequestQuotation = hotelTravelRequestQuotation;
	}

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	
	public Long getHotelQuotationRequestId() {
		return hotelQuotationRequestId;
	}

	public void setHotelQuotationRequestId(Long hotelQuotationRequestId) {
		this.hotelQuotationRequestId = hotelQuotationRequestId;
	}

	
	public List<HotelTravelRequestQuotation> getHotelTravelRequestQuotationsListnew() {
		return hotelTravelRequestQuotationsListnew;
	}

	public void setHotelTravelRequestQuotationsListnew(
			List<HotelTravelRequestQuotation> hotelTravelRequestQuotationsListnew) {
		this.hotelTravelRequestQuotationsListnew = hotelTravelRequestQuotationsListnew;
	}

	public int getNoofrooms() {
		return noofrooms;
	}

	public void setNoofrooms(int noofrooms) {
		this.noofrooms = noofrooms;
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

	public RmConfigTripDetailsModel getConfigTripDetailsModel() {
		return configTripDetailsModel;
	}

	public void setConfigTripDetailsModel(RmConfigTripDetailsModel configTripDetailsModel) {
		this.configTripDetailsModel = configTripDetailsModel;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	



}