package com.admin.common.quotation.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
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
import com.admin.common.config.model.InsuranceServiceTaxConfig;
import com.admin.common.quotation.dao.InsuranceTravelRequestDao;
import com.admin.common.quotation.model.InsuranceTravelRequest;
import com.admin.common.quotation.model.InsuranceTravelRequestQuotation;
import com.admin.common.util.CommonUtilSession;
import com.admin.enums.utility.CommonBookingStatusEnum;
import com.admin.enums.utility.IndianUnionTerritories;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.admin.insurance.model.InsuranceOrderCustomerDetail;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.InsuranceOrderDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.insurance.model.InsuranceGstTaxConfig;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRowGstTax;
import com.lintas.admin.insurance.model.InsuranceOrderRowServiceTax;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.vo.CutandPayModel;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.admin.orderrow.rm.structure.InsuranceOrderRowRmConfigStruct;
import com.tayyarah.gst.model.FlightGstTax;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

public class InsuranceTravelRequestAction extends ActionSupport
		implements ModelDriven<InsuranceTravelRequest>, SessionAware {
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InsuranceTravelRequestAction.class);
	private static final long serialVersionUID = 1L;
	InsuranceTravelRequest insuranceTravelRequest = new InsuranceTravelRequest();
	InsuranceTravelRequestQuotation insuranceTravelRequestQuotation = new InsuranceTravelRequestQuotation();
	SessionMap<String, Object> sessionMap;
	InsuranceTravelRequestDao insuranceTravelRequestDao = new InsuranceTravelRequestDao();
	private List<Country> countryList = null;
	InsuranceOrderRow insuranceOrderRow = new InsuranceOrderRow();
	InsuranceOrderRowServiceTax insuranceOrderRowServiceTax = new InsuranceOrderRowServiceTax();
	private List<InsuranceTravelRequest> insuranceTravelRequestlist = null;
	private List<InsuranceOrderCustomerDetail> insuranceOrderCustomerList= new ArrayList<>();
	private List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotationlist = new ArrayList<>();
	private Long insuranceQuotationRequestId;
	private Long orderId;
	private Long idtosend;
	private Long tripId;
	private Long detailsid;
	TripRequestDao tripRequestDao = new TripRequestDao();
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	UserDAO userDao = new UserDAO();
	private WalletAmountTranferHistory payTxInfo;
	RmConfigModel rmConfigModel = new RmConfigModel();
	List<String> fieldName = new ArrayList<String>();
	RmConfigDao rmConfigDao = new RmConfigDao();
	RmConfigTripDetailsModel configTripDetailsModel = new RmConfigTripDetailsModel();
	private UserWallet userWallet = new UserWallet();
	private String taxType;
	BigDecimal totalGstAmount = new BigDecimal("0.00");
	BigDecimal totalGST = new BigDecimal("0.00");
	
	InsuranceGstTaxConfig insuranceGstTaxConfig = new InsuranceGstTaxConfig();
	InsuranceServiceTaxConfig insuranceServiceTaxConfig=new InsuranceServiceTaxConfig();

	public String editInsuranceTravelRequestQuotation() {
		// TripRequest tripRequest=tripRequestDao.getTripRequestById(idtosend);
		insuranceTravelRequest = insuranceTravelRequestDao.getInsuranceQuotationRequestDetails(idtosend);
		if (insuranceTravelRequest != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			insuranceTravelRequestQuotationlist = insuranceTravelRequest.getInsuranceTravelRequestQuotations();
			
			try{
				insuranceTravelRequestQuotationlist.get(0).setOnwardTravelDateTemp(formatter.format(insuranceTravelRequestQuotation.getOnwardTravelDate()));
				insuranceTravelRequestQuotationlist.get(0).setReturnTravelDatetemp(formatter.format(insuranceTravelRequestQuotation.getReturnTravelDate()));
			}catch (Exception e) {
			}
			
			for (InsuranceTravelRequestQuotation insuranceTravelRequestQuotation : insuranceTravelRequestQuotationlist) {
				logger.info("insuranceTravelRequestQuotation------" + insuranceTravelRequestQuotation.getId());
			}
		}
		// insuranceTravelRequestQuotation=insuranceTravelRequestDao.insuranceRequestQuotationUpdate(insuranceTravelRequestQuotation);
		return SUCCESS;
	}

	public String updateInsuranceTravelRequestQuotation() {
		insuranceTravelRequestDao.updateInsuranceTravelRequestQuotation(insuranceTravelRequestQuotation);
		return SUCCESS;

	}

	public String editInsuranceOrderRowDetails() {
		insuranceOrderRow = insuranceTravelRequestDao.getInsuranceOrderRowDetailsById(idtosend);
		insuranceOrderRow.setSupplierPrice(insuranceOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
		insuranceOrderRow.setBasePrice(insuranceOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		insuranceOrderRow.setApiToBaseExchangeRate(
				insuranceOrderRow.getApiToBaseExchangeRate().setScale(2, BigDecimal.ROUND_UP));
		insuranceOrderRow.setManagementFee(insuranceOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		insuranceOrderRow.setConvenienceFee(insuranceOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		insuranceOrderRow.setServiceTax(insuranceOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		insuranceOrderRow.setOtherTaxes(insuranceOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		insuranceOrderRow.setBasePrice(insuranceOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		insuranceOrderRow.setTotalAmount(insuranceOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
		setInsuranceOrderRow(insuranceOrderRow);
		return SUCCESS;
	}

	public String editInsuranceTravelRequest() {
		setInsuranceTravelRequest(insuranceTravelRequestDao.getInsuranceTravelRequestDetails(idtosend));
		return SUCCESS;
	}

	public String updateInsuranceOrderRow() {
		insuranceTravelRequestDao.updateInsuranceOrderRow(insuranceOrderRow);
		return SUCCESS;

	}

	public String updateInsuranceTravelRequest() {
		insuranceTravelRequestDao.updateInsuranceTravelRequestDetails(insuranceTravelRequest);
		return SUCCESS;
	}

	public String goInsuranceTravelRequest() {
		Company sessionCompany = (Company) sessionMap.get("Company");
		User sessionUser = (User) sessionMap.get("User");
		RmConfigDao rmConfigDao = new RmConfigDao();
		try {
			setRmConfigModel(rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid()));
			String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("") ?rmConfigModel.getDynamicFieldsData().split(","):null;
			if(manualStringFields!=null && manualStringFields.length>0){
				for (String oneField : manualStringFields) {
					if(!oneField.trim().equalsIgnoreCase("")){
						String fieldsName[]=oneField.split(":");
						fieldName.add(fieldsName[0]);
					}
				}
			}
		} catch (Exception e) {
		}
		if (tripId != null) {
			TripRequest tripRequest = tripRequestDao.getTripRequestById(tripId);
			configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
		}
		CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());
		Company parentCompany = new CompanyDAO()
				.getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());

		BigDecimal CGST = new BigDecimal("0.00");
		BigDecimal CGSTPer = new BigDecimal("0.00");
		BigDecimal SGSTorIGSTorUGST = new BigDecimal("0.00");
		BigDecimal SGST = new BigDecimal("0.00");
		BigDecimal UGST = new BigDecimal("0.00");
		BigDecimal IGST = new BigDecimal("0.00");
		BigDecimal managementFee = new BigDecimal("0.00");
		if (companyConfig != null) {
			if (companyConfig.getTaxtype() != null && companyConfig.getTaxtype().equalsIgnoreCase("GST")) {
				boolean territiryStatus = IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());
				managementFee = companyConfig.getInsuranceGstTaxConfig().getManagementFee().setScale(0, RoundingMode.UP);
				CGST = managementFee.divide(new BigDecimal("100.0"))
						.multiply(companyConfig.getInsuranceGstTaxConfig().getCGST());
				CGSTPer=companyConfig.getInsuranceGstTaxConfig().getCGST();
				
				
				if (sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					SGST = managementFee.divide(new BigDecimal("100.0"))
							.multiply(companyConfig.getInsuranceGstTaxConfig().getSGST());
					SGSTorIGSTorUGST=companyConfig.getInsuranceGstTaxConfig().getSGST();
				}
				if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
						&& !territiryStatus) {
					IGST = managementFee.divide(new BigDecimal("100.0"))
							.multiply(companyConfig.getInsuranceGstTaxConfig().getIGST());
					CGST = new BigDecimal("0.00");
					SGSTorIGSTorUGST=companyConfig.getInsuranceGstTaxConfig().getIGST();
					CGSTPer= new BigDecimal("0.00");
				}
				if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
						&& territiryStatus) {
					UGST = managementFee.divide(new BigDecimal("100.0"))
							.multiply(companyConfig.getInsuranceGstTaxConfig().getUGST());
					SGSTorIGSTorUGST=companyConfig.getInsuranceGstTaxConfig().getUGST();
				}

				totalGstAmount = CGST.add(SGST).add(IGST).add(UGST);
				totalGST=CGSTPer.add(SGSTorIGSTorUGST);
				setTaxType("GST");

				if (companyConfig.getInsuranceGstTaxConfig() != null) {
					insuranceGstTaxConfig.setConvenienceFee(
							companyConfig.getInsuranceGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.UP));
					insuranceGstTaxConfig.setManagementFee(
							companyConfig.getInsuranceGstTaxConfig().getManagementFee().setScale(0, RoundingMode.UP));
				} else {
					setTaxType("Service");
					if (companyConfig.getInsuranceServiceTaxConfig() != null) {
						insuranceServiceTaxConfig.setBasicTax(
								companyConfig.getInsuranceServiceTaxConfig().getBasicTax().setScale(2, RoundingMode.UP));
						insuranceServiceTaxConfig.setKrishiKalyanCess(
								companyConfig.getInsuranceServiceTaxConfig().getKrishiKalyanCess().setScale(2, RoundingMode.UP));
						insuranceServiceTaxConfig.setSwatchBharathCess(
								companyConfig.getInsuranceServiceTaxConfig().getSwatchBharathCess().setScale(2, RoundingMode.UP));
						insuranceServiceTaxConfig.setTotalTax(
								companyConfig.getInsuranceServiceTaxConfig().getTotalTax().setScale(2, RoundingMode.UP));
						insuranceServiceTaxConfig.setConvenienceFee(
								companyConfig.getInsuranceServiceTaxConfig().getConvenienceFee().setScale(0, RoundingMode.UP));
						insuranceServiceTaxConfig.setManagementFee(
								companyConfig.getInsuranceServiceTaxConfig().getManagementFee().setScale(0, RoundingMode.UP));

					}
				}
			}
		}
		countryList = new CountryDao().getCountryList();
		List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
		setApiProviders(list);
		setTripId(tripId);
		userWallet = new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId());
		return SUCCESS;
	}

	public String createInsuranceTravelRequest() {
		TripRequest tripRequest = new TripRequest();
		User sessionUser = (User) sessionMap.get("User");
		Company sessionCompany = (Company) sessionMap.get("Company");
		User userNew = userDao.getUserByUserId(sessionUser.getId());
		String orderId = insuranceOrderRow.getConfirmationNumber();
		BigDecimal finalPrice = insuranceOrderRow.getTotalAmount();
		BigDecimal finalPriceAfterTaxIfInclude = finalPrice
				.add(insuranceOrderRow.getServiceTax() != null ? insuranceOrderRow.getServiceTax() : new BigDecimal(0));
		//BigDecimal finalPriceAfterTax = finalPriceAfterTaxIfInclude.subtract(insuranceOrderRow.getServiceTax() != null ? insuranceOrderRow.getServiceTax() : new BigDecimal(0));
		BigDecimal serviceOrGstTax = new BigDecimal("0.00");
		CompanyConfig newCompanyConfig = new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());
		User walletUser = userNew;
		if (userNew != null) {
			if (sessionCompany.getCompanyRole() != null && sessionCompany.getCompanyRole().isCorporate()) {
				UserDAO userDAO = new UserDAO();
				walletUser = userDAO.getUserPasswordForLock(sessionCompany.getEmail());
			}
		}

		insuranceTravelRequestQuotation.setOnwardTravelDate(insuranceTravelRequestQuotation.getOnwardTravelDateTemp());
		insuranceTravelRequestQuotation.setReturnTravelDate(insuranceTravelRequestQuotation.getReturnTravelDatetemp());
		insuranceTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		insuranceTravelRequestQuotationlist.add(insuranceTravelRequestQuotation);
		InsuranceTravelRequest insuranceTravelRequest=new InsuranceTravelRequest();
		insuranceTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
		insuranceTravelRequest.setCompanyId(sessionUser.getCompanyid());
		insuranceTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
				? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
		insuranceTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());

		InsuranceOrderCustomerDetail insuranceOrderCustomer=null;
		if(insuranceOrderCustomerList!=null &&insuranceOrderCustomerList.size()>0) 
			insuranceOrderCustomer=insuranceOrderCustomerList.get(0);
		if(insuranceOrderCustomer!=null){
			insuranceTravelRequest.setFirstName(insuranceOrderCustomer.getFirstName());
			insuranceTravelRequest.setLastName(insuranceOrderCustomer.getLastName());
			insuranceTravelRequest.setTitle(insuranceOrderCustomer.getTitle());
		}
		 
		InsuranceTravelRequest insuranceTravelRequestnew = insuranceTravelRequestDao.insertinsuranceTravelRequestnew(insuranceTravelRequest);
		if (insuranceTravelRequestnew != null && insuranceTravelRequestnew.getId() > 0) {
			boolean isInserted = insuranceTravelRequestDao
					.insertInsuranceQuotationList(insuranceTravelRequestQuotationlist, insuranceTravelRequestnew);
			if (isInserted) {
				if (insuranceTravelRequestQuotationlist != null && insuranceTravelRequestQuotationlist.size() > 0) {
					insuranceOrderRow.setBookingCurrency(insuranceTravelRequestQuotationlist.get(0).getCurrency());
				}

				OrderCustomer orderCustomer = new OrderCustomer();
				orderCustomer.setFirstName(insuranceTravelRequest.getFirstName());
				orderCustomer.setLastName(insuranceTravelRequest.getLastName());
				orderCustomer.setTitle(insuranceTravelRequest.getTitle());
				orderCustomer.setEmail(sessionUser.getEmail());
				orderCustomer.setCompanyId(sessionCompany.getCompanyid());
				orderCustomer.setBookingType(CommonBookingStatusEnum.INSURANCE.getMessage());
				orderCustomer.setConfigId(newCompanyConfig.getConfig_id());
				//added by basha
				orderCustomer.setCreatedByUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				boolean countryOfTravel = insuranceOrderRow.getCountryOfTravel();
				if (countryOfTravel == true) {
					insuranceOrderRow.setCountryOfTravel(countryOfTravel);
				} else {
					insuranceOrderRow.setCountryOfTravel(countryOfTravel);
				}
				insuranceOrderRow.setOrderCustomer(orderCustomer);
				insuranceOrderRow.setTravelDate(insuranceTravelRequestQuotation.getOnwardTravelDate());
				insuranceOrderRow.setRemarks(insuranceTravelRequestQuotation.getRemarks());
				insuranceOrderRow.setEmpName(insuranceTravelRequest.getTitle() + "  "
						+ insuranceTravelRequest.getFirstName() + "  " + insuranceTravelRequest.getLastName());
				
				Company parentCompany = new CompanyDAO()
						.getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());

				FlightGstTax flightGstTax = null;
				InsuranceOrderRowServiceTax insuranceOrderRowServiceTax = null;
				InsuranceOrderRowGstTax insuranceOrderRowGstTax = null;

				if (newCompanyConfig != null && newCompanyConfig.getCompanyConfigType().isB2E()) {
					if (newCompanyConfig != null && newCompanyConfig.getTaxtype() != null
							&& newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")) {

						if (newCompanyConfig.getInsuranceGstTaxConfig() != null) {
							insuranceOrderRowGstTax = new InsuranceOrderRowGstTax();
							insuranceOrderRowGstTax.setConvenienceFee(insuranceOrderRow.getConvenienceFee());
							insuranceOrderRowGstTax.setCreatedAt(insuranceOrderRow.getCreatedAt());
							insuranceOrderRowGstTax.setManagementFee(insuranceOrderRow.getManagementFee());
							insuranceOrderRowGstTax
									.setApplicableFare(newCompanyConfig.getInsuranceGstTaxConfig().getApplicableFare());
							insuranceOrderRowGstTax.setIGST(newCompanyConfig.getInsuranceGstTaxConfig().getIGST());
							insuranceOrderRowGstTax.setSGST(newCompanyConfig.getInsuranceGstTaxConfig().getSGST());
							insuranceOrderRowGstTax.setCGST(newCompanyConfig.getInsuranceGstTaxConfig().getCGST());
							insuranceOrderRowGstTax.setUGST(newCompanyConfig.getInsuranceGstTaxConfig().getUGST());

							flightGstTax = getFlightGSTTax(insuranceOrderRowGstTax, sessionCompany, parentCompany,insuranceOrderRow);
							insuranceOrderRowGstTax.setTotalGst(flightGstTax.getTotalTax());
							insuranceOrderRow.setInsuranceOrderRowGstTax(insuranceOrderRowGstTax);
							serviceOrGstTax = insuranceOrderRow.getTotalGstTax();
						}
					} else {
						if (newCompanyConfig.getInsuranceServiceTaxConfig() != null) {
							insuranceOrderRowServiceTax = new InsuranceOrderRowServiceTax();
							insuranceOrderRowServiceTax.setConvenienceFee(insuranceOrderRow.getConvenienceFee());
							insuranceOrderRowServiceTax.setCreatedAt(insuranceOrderRow.getCreatedAt());
							insuranceOrderRowServiceTax.setManagementFee(insuranceOrderRow.getManagementFee());
							insuranceOrderRowServiceTax.setApplicableFare(
									newCompanyConfig.getInsuranceServiceTaxConfig().getApplicableFare());
							insuranceOrderRowServiceTax
									.setBasicTax(newCompanyConfig.getInsuranceServiceTaxConfig().getBasicTax());
							insuranceOrderRowServiceTax.setKrishiKalyanCess(
									newCompanyConfig.getInsuranceServiceTaxConfig().getKrishiKalyanCess());
							insuranceOrderRowServiceTax.setSwatchBharathCess(
									newCompanyConfig.getInsuranceServiceTaxConfig().getSwatchBharathCess());
							insuranceOrderRowServiceTax
									.setTotalTax(newCompanyConfig.getInsuranceServiceTaxConfig().getTotalTax());
							insuranceOrderRow.setInsuranceOrderRowServiceTax(insuranceOrderRowServiceTax);
							serviceOrGstTax = insuranceOrderRow.getServiceTax();
						}
					}
				}
				if (insuranceOrderRow.getTotalGstTax() == null)
					insuranceOrderRow.setTotalGstTax(new BigDecimal("0.00"));
				if (insuranceOrderRow.getServiceTax() == null)
					insuranceOrderRow.setServiceTax(new BigDecimal("0.00"));

				insuranceOrderRow.setTaxes(new BigDecimal("0.00"));
				insuranceOrderRow.setCreatedAt(new Timestamp(new Date().getTime()));
				insuranceOrderRow.setApiComments("ok");
				insuranceOrderRow.setCompanyId(String.valueOf(sessionUser.getCompanyid()));
				insuranceOrderRow.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)
						? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId()));
				insuranceOrderRow.setConfigId(newCompanyConfig.getCurrency_id());
				insuranceOrderRow.setCreatedBy(sessionUser.getUsername());
				insuranceOrderRow.setUpdatedBy(sessionUser.getUsername());
				insuranceOrderRow.setProcessingFees(new BigDecimal("0.00"));
				insuranceOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
				insuranceOrderRow.setBaseToBookingExchangeRate(new BigDecimal(1));
				insuranceOrderRow.setBookingDate(DateConversion.StringToDate(insuranceOrderRow.getInsuranceBookingDate()));
				insuranceOrderRow.setOrderId(insuranceOrderRow.getConfirmationNumber());
				insuranceOrderRow.setStatusAction("Confirmed");
				insuranceOrderRow.setPaymentStatus("Success");
				insuranceOrderRow.setBookingMode(CommonBookingStatusEnum.BOOKING_MODE_OFFLINE.getMessage());
				RmConfigModel  rmConfigModel=rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid());
				   if(rmConfigModel!=null ){
				InsuranceOrderRowRmConfigStruct insuranceOrderRowRmConfigStruct=new InsuranceOrderRowRmConfigStruct();
				   insuranceOrderRowRmConfigStruct.setRmDynamicData(rmConfigModel.getDynamicFieldsData());
				   insuranceOrderRow.setInsuranceOrderRowRmConfigStruct(insuranceOrderRowRmConfigStruct);
				   }
				InsuranceOrderRow insuranceOrderRowNew = insuranceTravelRequestDao.insertInsuranceOrderRow(insuranceOrderRow);
				if (insuranceOrderRowNew != null && insuranceOrderRowNew.getId()!=null) {
					insuranceOrderRowNew.setInvoiceNo(RandomConfigurationNumber.generateInsurenceInvoiceNumber(insuranceOrderRowNew.getId()));
					Long orderIdTemp = insuranceOrderRowNew.getId() + 1000;
					orderId = RandomConfigurationNumber.generateInsurenceInvoiceNumber(orderIdTemp);
					insuranceOrderRowNew.setOrderId(orderId);
					insuranceOrderRowNew = new InsuranceOrderDao().updateInsuranceOrderRowDetail(insuranceOrderRowNew);
				}

				CompanyDAO companyDAO = new CompanyDAO();
				Map<String, BigDecimal> markups = new LinkedHashMap<>();
				if (insuranceOrderRowNew != null) {
					if (sessionCompany.getCompanyRole().isSuperUser()) {
						// markups.put(String.valueOf(sessionCompany.getCompanyid()),
						// insuranceOrderRowNew.getMarkUpamount());
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					} else {
						Company companyParent = companyDAO.getParentCompany(sessionCompany);
						markups.put(String.valueOf(companyParent.getCompanyid()),
								insuranceOrderRowNew.getMarkUpamount());
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					}
				}

				List<Company> companyListBottomToTop = new LinkedList<>();
				List<User> userListBottomToTop = new LinkedList<>();
				Map<Integer, CutandPayModel> cutAndPayUserMap = new LinkedHashMap<>();
				FlightOrderDao flightOrderDao = new FlightOrderDao();
				InsuranceOrderDao insuranceOrderDao = new InsuranceOrderDao();

				finalPriceAfterTaxIfInclude = finalPrice
						.add(serviceOrGstTax != null ? serviceOrGstTax : new BigDecimal(0));
				boolean bookTicket = false;
				if (true) {
					try {
						companyListBottomToTop = CommonUtil.getParentCompanyBottomToTop(sessionCompany.getCompanyid(),
								companyDAO);
						if (companyListBottomToTop != null && companyListBottomToTop.size() > 0) {
							userListBottomToTop = CommonUtil.getUsersAllWithUserModeBottomToTop(companyListBottomToTop,
									companyDAO, walletUser);
						}
						cutAndPayUserMap = CommonUtil.getCutandPayModelUsersHotel(userListBottomToTop, markups,
								finalPriceAfterTaxIfInclude, walletUser);

						boolean checkBookingAmountEligibility = false;
						if (userListBottomToTop != null && userListBottomToTop.size() > 0) {
							for (User userInner : userListBottomToTop) {
								if (userInner.getAgentWallet() != null) {
									if (cutAndPayUserMap != null && cutAndPayUserMap.get(userInner.getId()) != null) {
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

						if (checkBookingAmountEligibility) {
							Map<Integer, Boolean> userMapBottomToTop = new LinkedHashMap<>();
							if (userListBottomToTop != null && userListBottomToTop.size() > 0) {
								for (User userInner : userListBottomToTop) {
									if (userInner.getAgentWallet() != null) {
										if (cutAndPayUserMap != null
												&& cutAndPayUserMap.get(userInner.getId()) != null) {
											BigDecimal totalPayableAmount = cutAndPayUserMap.get(userInner.getId())
													.getPayableAmount();
											if (flightOrderDao.checkWalletAmount(userInner.getId(), totalPayableAmount,
													new BigDecimal(0), new BigDecimal(0))) {
												FlightOrderDao.deductUserWallet(totalPayableAmount, userInner, userDao,
														CommonBookingStatusEnum.INSURANCE_REMARKS.getMessage(), orderId,
														insuranceOrderRowNew.getInvoiceNo());
												userMapBottomToTop.put(userInner.getId(), true);
											} else {
												if (userMapBottomToTop != null && userMapBottomToTop.size() > 0) {
													for (Entry<Integer, Boolean> userMap : userMapBottomToTop
															.entrySet()) {
														if (userMap.getValue()) {
															totalPayableAmount = cutAndPayUserMap.get(userMap.getKey())
																	.getPayableAmount();
															flightOrderDao.creditUserWalletAmountForBookingFailed(
																	totalPayableAmount, userInner, userDao,
																	CommonBookingStatusEnum.INSURANCE_FAILEDREMARKS
																			.getMessage(),
																	orderId, insuranceOrderRowNew.getInvoiceNo());
														}
													}
												}
												bookTicket = false;
												break;
											}
										} else {
											bookTicket = false;
										}
									}
								}
								bookTicket = true;
							}
						} else {
							bookTicket = false;
						}
					} catch (Exception e) {
					}
				}

				if (!bookTicket) {
					insuranceOrderRowNew.setPaymentStatus("Failed");
					insuranceOrderRowNew = insuranceOrderDao
							.updateInsuranceOrderRowDetailPaymentStatus(insuranceOrderRowNew);
					addActionMessage(CommonBookingStatusEnum.WALLET_ERROR.getMessage());
					return ERROR;
				} else {
					insuranceOrderRowNew.setPaymentStatus("Success");
					insuranceOrderRowNew = insuranceOrderDao
							.updateInsuranceOrderRowDetailPaymentStatus(insuranceOrderRowNew);
				}

				if (insuranceOrderRowNew != null) {
					InsuranceTravelRequestQuotation insuranceTravelRequestQuotationNew = insuranceTravelRequestDao
							.updateInsuranceTravelRequestQuotationWithOrderId(insuranceOrderRowNew,
									insuranceTravelRequestQuotation.getId());
					if (insuranceTravelRequestQuotationNew != null) {
						HotelFlightBookingStatus hotelFlightBookingStatus = new HotelFlightBookingStatus();
						if (insuranceTravelRequestQuotationNew.getHotetFlightBookingStatus() != null
								&& insuranceTravelRequestQuotationNew.getHotetFlightBookingStatus().getId() != null)
							hotelFlightBookingStatus
									.setId(insuranceTravelRequestQuotationNew.getHotetFlightBookingStatus().getId());
						hotelFlightBookingStatus.setBooked(TravelRequestStatusEnum.BOOKED.getValue());
						insuranceTravelRequestDao.updateHotelOrFlightQuotationBookingStatus(hotelFlightBookingStatus);
						new InsuranceTravelRequestDao().updateInsuranceTravelRequestQuotationHide(
								insuranceTravelRequestQuotationNew.getInsuranceTravelRequest().getId());
						new CompanyDAO().insertEmail(insuranceOrderRowNew.getOrderId(), 0,
								Email.EMAIL_TYPE_INSURANCE_INVOICE);
						addActionMessage("Successfully Insurance Booking Done.");
					} else {
						addActionMessage("We found somr error while booking.");
						return ERROR;
					}
				}
				 
				if (tripId != null) {
					tripRequest.setId(tripId);
					tripRequest.setInsuranceTravelRequest(insuranceTravelRequestnew);
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
							? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
					tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
					tripRequest = tripRequestDao.updateTripRequestById(tripRequest);
					if (tripRequest != null && tripRequest.getId() > 0) {
						addActionMessage("Successfully created");
					}
				} else {
					tripRequest.setInsuranceTravelRequest(insuranceTravelRequestnew);
					tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
					// tripRequest.setTripId(RandomConfigurationNumber.generateTripId(insuranceTravelRequestnew.getId()));
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
							? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
					tripRequest = tripRequestDao.insertTripRequest(tripRequest);
					if (tripRequest != null && tripRequest.getId() > 0) {
						tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
						if (tripRequestDao.updateTripRequestNumber(tripRequest))
							addActionMessage("Successfully created");
						else
							addActionMessage("Something went wrong.Please wait.");
					}
				}
				if(insuranceOrderCustomerList!=null && insuranceOrderCustomerList.size()>0){
					for(InsuranceOrderCustomerDetail  insuranceOrderCustomerNew:insuranceOrderCustomerList){
						insuranceOrderCustomerNew.setEmail(sessionUser.getEmail());
						insuranceOrderCustomerNew.setAge("");
						insuranceOrderCustomerNew.setCreatedAt(new Timestamp(new Date().getTime()));
						insuranceOrderCustomerNew.setInsuranceOrderRow(insuranceOrderRowNew);
						RmConfigTripDetailsModel configTripDetailsModel=insuranceOrderCustomerNew.getRmConfigTripDetailsModel();
						if(configTripDetailsModel!=null){
							configTripDetailsModel.setOrderId(insuranceOrderRowNew.getOrderId());
							configTripDetailsModel.setOrdertype("Insurance");
							insuranceOrderCustomerNew.setRmConfigTripDetailsModel(configTripDetailsModel);
						}
						insuranceOrderCustomerNew.setPaxId(RandomConfigurationNumber.generateRandomPaxID());
						insuranceTravelRequestDao.insertInsuranceOrderCustomer(insuranceOrderCustomerNew);
					}
					new NotificationAction().insertNotificationOneandAll(sessionUser,
							String.valueOf(insuranceTravelRequestnew.getId()), "insurance book Request created",
							"Insurance book Request notification", NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),
							false, false, false, true, false, false);
				}
				
			} else {
				addActionMessage("We found somr error while booking.");
				return ERROR;
			}

		} else {
			addActionMessage("We found somr error while booking.");
			return ERROR;
		}

		return SUCCESS;
	}

	public InsuranceOrderRowServiceTax getInsuranceOrderRowServiceTax() {
		return insuranceOrderRowServiceTax;
	}

	public void setInsuranceOrderRowServiceTax(InsuranceOrderRowServiceTax insuranceOrderRowServiceTax) {
		this.insuranceOrderRowServiceTax = insuranceOrderRowServiceTax;
	}

	public String getInsuranceTravelRequestList() {
		User sessionUser = (User) sessionMap.get("User");
		List<InsuranceTravelRequest> list = insuranceTravelRequestDao.loadInsuranceTravelRequestList(sessionUser);
		if (list != null && list.size() > 0) {
			insuranceTravelRequestlist = list;
		}
		return SUCCESS;
	}

	public InsuranceTravelRequest getInsuranceQuotationRow() {
		return insuranceTravelRequest;
	}

	public void setInsuranceQuotationRow(InsuranceTravelRequest hotelQuotationRow) {
		this.insuranceTravelRequest = hotelQuotationRow;
	}

	public String getInsuranceTravelRequestDetails() {
		insuranceTravelRequest = insuranceTravelRequestDao
				.getInsuranceTravelRequestDetails(insuranceTravelRequest.getId());
		countryList = new CountryDao().getCountryList();
		insuranceTravelRequestQuotationlist = insuranceTravelRequestDao
				.getInsuranceRequestTravelQuotationBookedList(insuranceTravelRequest.getId());
		return SUCCESS;
	}

	public String updateInsuranceTravelRequestDetails() {
		User sessionUser = (User) sessionMap.get("User");
		// HotetTravelRequest
		// hotelTravelRequest=hotelOrderRowFineSheetDao.getInsuranceTravelRequestDetails(hotelQuotationRow.getId());
		insuranceTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
				? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
		insuranceTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		InsuranceTravelRequest hotetTravelRequestNew = insuranceTravelRequestDao
				.updateInsuranceTravelRequestDetails(insuranceTravelRequest);
		if (hotetTravelRequestNew != null) {
			addActionMessage("Successfully Updated.");
			new NotificationAction().insertNotificationOneandAll(sessionUser,
					String.valueOf(hotetTravelRequestNew.getId()), "Hotal book Request updated",
					"Hotal book Request notification", NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(), false,
					false, false, true, false, false);

			return SUCCESS;
		} else {
			addActionError("We found some error.Please try again.");
			return ERROR;
		}
	}

	public String generateInsuranceInvoice() {
		/* long dummyId= 9; */
		InsuranceOrderRow insuranceOrderRow = insuranceTravelRequestDao.getInsuranceOrderRowDetailsById(orderId);
		if (insuranceOrderRow != null)
			setInsuranceOrderRow(insuranceOrderRow);

		return SUCCESS;
	}

	public String getInsuranceOfflineInvoice() {
		InsuranceOrderRow insuranceOrderRow = insuranceTravelRequestDao.getInsuranceOrderRowDetailsById(orderId);
		if (insuranceOrderRow != null)
			setInsuranceOrderRow(insuranceOrderRow);

		return SUCCESS;
	}

	public static FlightGstTax getFlightGSTTax(InsuranceOrderRowGstTax insOrderRowGstTax, Company childCompany,
			Company parentCompany, InsuranceOrderRow insuranceOrderRow) {
		CompanyEntity  companyEntity=new CompanyEntity();
		String entityState=null;
		if(insuranceOrderRow.getCompanyEntityId()!=null) 
		     companyEntity = new CompanyDAO().companyEntityProfile(insuranceOrderRow.getCompanyEntityId().intValue());
		    if(companyEntity!=null && companyEntity.getState()!=null) 
		    	entityState=companyEntity.getState();  
		
		BigDecimal managementFee = insOrderRowGstTax.getManagementFee() != null ? insOrderRowGstTax.getManagementFee()
				: new BigDecimal("0.0");
		BigDecimal CGSTAmount = new BigDecimal("0.0");
		BigDecimal CGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTAmount = new BigDecimal("0.0");
		// BigDecimal IGST = new BigDecimal("0.0");
		// BigDecimal UGST = new BigDecimal("0.0");
		BigDecimal totalGstAmount = new BigDecimal("0.0");
		BigDecimal totalGstPer = new BigDecimal("0.0");
		boolean isParentCompanyUT = IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());
		
		CGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getCGST());
		CGSTPer = insOrderRowGstTax.getCGST();
		
		
		if(entityState!=null){ 
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(entityState.trim());
		if (isParentCompanyUT && isBillingCompanyUT) {
			CommonGSTPer = insOrderRowGstTax.getUGST();
			CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getUGST());
			insOrderRowGstTax.setIGST(new BigDecimal("0.0"));
			insOrderRowGstTax.setSGST(new BigDecimal("0.0"));

		}
		if (!isParentCompanyUT && !isBillingCompanyUT) {
			if (entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
				CommonGSTPer = insOrderRowGstTax.getSGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getSGST());
				insOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				insOrderRowGstTax.setUGST(new BigDecimal("0.0"));
			}
			if (!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
				CommonGSTPer = insOrderRowGstTax.getIGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getIGST());
				 CGSTPer = new BigDecimal(0);
				 CGSTAmount = new BigDecimal(0);
				insOrderRowGstTax.setSGST(new BigDecimal("0.0"));
				insOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				insOrderRowGstTax.setCGST(CGSTPer);
			}
		}

		if (!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
				&& IndianUnionTerritories.isUnionter(entityState.trim())) {
			CommonGSTPer = insOrderRowGstTax.getUGST();
			CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getUGST());
			insOrderRowGstTax.setIGST(new BigDecimal("0.0"));
			insOrderRowGstTax.setSGST(new BigDecimal("0.0"));
		}

		if (isParentCompanyUT && !isBillingCompanyUT) {
			if (!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
					&& !IndianUnionTerritories.isUnionter(entityState.trim())) {
				CommonGSTPer = insOrderRowGstTax.getIGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getIGST());
				 CGSTPer = new BigDecimal(0);
				 CGSTAmount = new BigDecimal(0);
				insOrderRowGstTax.setSGST(new BigDecimal("0.0"));
				insOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				insOrderRowGstTax.setCGST(CGSTPer);
			}
		}
		}else{
			boolean isBillingCompanyUT = IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim());
			if (isParentCompanyUT && isBillingCompanyUT) {
				CommonGSTPer = insOrderRowGstTax.getUGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getUGST());
				insOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				insOrderRowGstTax.setSGST(new BigDecimal("0.0"));

			}
			if (!isParentCompanyUT && !isBillingCompanyUT) {
				if (childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					CommonGSTPer = insOrderRowGstTax.getSGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getSGST());
					insOrderRowGstTax.setIGST(new BigDecimal("0.0"));
					insOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				}
				if (!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					CommonGSTPer = insOrderRowGstTax.getIGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getIGST());
					 CGSTPer = new BigDecimal(0);
					 CGSTAmount = new BigDecimal(0);
					insOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					insOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					insOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if (!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
					&& IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())) {
				CommonGSTPer = insOrderRowGstTax.getUGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getUGST());
				insOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				insOrderRowGstTax.setSGST(new BigDecimal("0.0"));
			}

			if (isParentCompanyUT && !isBillingCompanyUT) {
				if (!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
						&& !IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())) {
					CommonGSTPer = insOrderRowGstTax.getIGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(insOrderRowGstTax.getIGST());
					 CGSTPer = new BigDecimal(0);
					 CGSTAmount = new BigDecimal(0);
					insOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					insOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					insOrderRowGstTax.setCGST(CGSTPer);
				}
			}
		}
		totalGstPer = CGSTPer.add(CommonGSTPer);
		totalGstAmount = CGSTAmount.add(CommonGSTAmount);
		FlightGstTax flightServiceTax = new FlightGstTax();
		flightServiceTax.setTotalTax(totalGstPer);
		flightServiceTax.setTotalGstAmount(totalGstAmount);
		return flightServiceTax;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap<String, Object>) map;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	@Override
	public InsuranceTravelRequest getModel() {
		// TODO Auto-generated method stub
		return insuranceTravelRequest;
	}

	public InsuranceTravelRequest getInsuranceTravelRequest() {
		return insuranceTravelRequest;
	}

	public void setInsuranceTravelRequest(InsuranceTravelRequest insuranceTravelRequest) {
		this.insuranceTravelRequest = insuranceTravelRequest;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<InsuranceTravelRequest> getInsuranceTravelRequestlist() {
		return insuranceTravelRequestlist;
	}

	public void setInsuranceTravelRequestlist(List<InsuranceTravelRequest> insuranceTravelRequestlist) {
		this.insuranceTravelRequestlist = insuranceTravelRequestlist;
	}

	public List<InsuranceTravelRequestQuotation> getInsuranceTravelRequestQuotationlist() {
		return insuranceTravelRequestQuotationlist;
	}

	public void setInsuranceTravelRequestQuotationlist(
			List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotationlist) {
		this.insuranceTravelRequestQuotationlist = insuranceTravelRequestQuotationlist;
	}

	public Long getInsuranceQuotationRequestId() {
		return insuranceQuotationRequestId;
	}

	public void setInsuranceQuotationRequestId(Long insuranceQuotationRequestId) {
		this.insuranceQuotationRequestId = insuranceQuotationRequestId;
	}

	public InsuranceTravelRequestQuotation getInsuranceTravelRequestQuotation() {
		return insuranceTravelRequestQuotation;
	}

	public void setInsuranceTravelRequestQuotation(InsuranceTravelRequestQuotation insuranceTravelRequestQuotation) {
		this.insuranceTravelRequestQuotation = insuranceTravelRequestQuotation;
	}

	public InsuranceOrderRow getInsuranceOrderRow() {
		return insuranceOrderRow;
	}

	public void setInsuranceOrderRow(InsuranceOrderRow insuranceOrderRow) {
		this.insuranceOrderRow = insuranceOrderRow;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getIdtosend() {
		return idtosend;
	}

	public void setIdtosend(Long idtosend) {
		this.idtosend = idtosend;
	}

	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}

	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}

	public Long getDetailsid() {
		return detailsid;
	}

	public void setDetailsid(Long detailsid) {
		this.detailsid = detailsid;
	}

	public WalletAmountTranferHistory getPayTxInfo() {
		return payTxInfo;
	}

	public void setPayTxInfo(WalletAmountTranferHistory payTxInfo) {
		this.payTxInfo = payTxInfo;
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

	public UserWallet getUserWallet() {
		return userWallet;
	}

	public void setUserWallet(UserWallet userWallet) {
		this.userWallet = userWallet;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public BigDecimal getTotalGstAmount() {
		return totalGstAmount;
	}

	public void setTotalGstAmount(BigDecimal totalGstAmount) {
		this.totalGstAmount = totalGstAmount;
	}

	public InsuranceGstTaxConfig getInsuranceGstTaxConfig() {
		return insuranceGstTaxConfig;
	}

	public void setInsuranceGstTaxConfig(InsuranceGstTaxConfig insuranceGstTaxConfig) {
		this.insuranceGstTaxConfig = insuranceGstTaxConfig;
	}

	public InsuranceServiceTaxConfig getInsuranceServiceTaxConfig() {
		return insuranceServiceTaxConfig;
	}

	public void setInsuranceServiceTaxConfig(InsuranceServiceTaxConfig insuranceServiceTaxConfig) {
		this.insuranceServiceTaxConfig = insuranceServiceTaxConfig;
	}
	public BigDecimal getTotalGST() {
		return totalGST;
	}

	public void setTotalGST(BigDecimal totalGST) {
		this.totalGST = totalGST;
	}

	public List<InsuranceOrderCustomerDetail> getInsuranceOrderCustomerList() {
		return insuranceOrderCustomerList;
	}

	public void setInsuranceOrderCustomerList(List<InsuranceOrderCustomerDetail> insuranceOrderCustomerList) {
		this.insuranceOrderCustomerList = insuranceOrderCustomerList;
	}

}
