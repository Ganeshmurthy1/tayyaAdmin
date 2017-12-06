package com.admin.common.quotation.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.admin.common.config.model.BusServiceTaxConfig;
import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequest;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.admin.common.util.CommonUtilSession;
import com.admin.enums.utility.CommonBookingStatusEnum;
import com.admin.enums.utility.IndianUnionTerritories;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.BusOrderDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.bus.model.BusGstTaxConfig;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.bus.model.BusOrderRowGstTax;
import com.lintas.admin.bus.model.BusOrderRowServiceTax;
import com.lintas.admin.common.model.OrderCustomer;
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
import com.tayyarah.admin.orderrow.rm.structure.BusOrderRowRmConfigStruct;
import com.tayyarah.bus.model.BusOrderCustomerDetail;
import com.tayyarah.gst.model.FlightGstTax;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

public class BusTravelRequestAction extends ActionSupport implements ModelDriven<BusTravelRequest>, SessionAware {
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BusTravelRequestQuotation.class);
	private static final long serialVersionUID = 1L;
	BusTravelRequest busTravelRequest = new BusTravelRequest();
	BusTravelRequestQuotation busTravelRequestQuotation = new BusTravelRequestQuotation();
	SessionMap<String, Object> sessionMap;
	BusTravelRequestDao busTravelRequestDao = new BusTravelRequestDao();
	private List<Country> countryList = null;
	BusOrderRow busOrderRow = new BusOrderRow();
	private List<BusTravelRequest> busTravelRequestlist = null;
	private List<BusTravelRequestQuotation> busTravelRequestQuotationlist = new ArrayList<>();
	private List<BusOrderCustomerDetail> busOrderCustomerDetailList= new ArrayList<>();
	private Long busQuotationRequestId;
	private Long tripId;
	BusOrderRow busOrderRowForInvoice = new BusOrderRow();
	private Long orderId;
	private Long idForDetails;
	private Long idtosend;
	private Long detailsid;
	private String taxType;
	BigDecimal totalGstAmount = new BigDecimal("0.00");
	private WalletAmountTranferHistory payTxInfo;
	private BusServiceTaxConfig busServiceTaxConfig = new BusServiceTaxConfig();
	BusGstTaxConfig busGstTaxConfig = new BusGstTaxConfig();
	TripRequestDao tripRequestDao = new TripRequestDao();
	UserDAO userDao = new UserDAO();
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	RmConfigModel rmConfigModel = new RmConfigModel();
	List<String> fieldName = new ArrayList<String>();
	RmConfigDao rmConfigDao = new RmConfigDao();
	RmConfigTripDetailsModel configTripDetailsModel = new RmConfigTripDetailsModel();
	private UserWallet userWallet = new UserWallet();
	BigDecimal totalGstTaxPer = new BigDecimal("0.00");


	public String editBusTravelRequestQuotation() {
		countryList = new CountryDao().getCountryList();
		// TripRequest tripRequest=tripRequestDao.getTripRequestById(idtosend);
		busTravelRequest = busTravelRequestDao.getBusQuotationRequestDetails(idtosend);
		if (busTravelRequest != null) {
			busTravelRequestQuotationlist = busTravelRequest.getBusTravelRequestQuotations();
		}
		// busTravelRequestQuotation=busTravelRequestDao.busRequestQuotationUpdate(busTravelRequestQuotation);
		return SUCCESS;
	}

	public String getBusOfflineInvoice() {
		BusOrderRow busOrderRow = busTravelRequestDao.getBusOrderRowDetailsById(orderId);
		if (busOrderRow != null)
			setBusOrderRow(busOrderRow);

		return SUCCESS;
	}

	public String updateBusTravelRequestQuotation() {
		busTravelRequestDao.updateBusTravelRequestQuotation(busTravelRequestQuotation);
		return SUCCESS;

	}

	public String editBusOrderRowDetails() {
		setBusOrderRow(busTravelRequestDao.getBusOrderRowDetailsById(idtosend));
		return SUCCESS;
	}

	public String editBusTravelRequest() {
		setBusTravelRequest(busTravelRequestDao.getBusTravelRequestDetails(idtosend));
		return SUCCESS;
	}

	public String updateBusOrderRow() {
		busTravelRequestDao.updateBusOrderRow(busOrderRow);
		return SUCCESS;

	}

	public String updateBusTravelRequest() {
		busTravelRequestDao.updateBusTravelRequestDetails(busTravelRequest);
		return SUCCESS;
	}

	public String goBusTravelRequest() {
		countryList = new CountryDao().getCountryList();
		List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
		setApiProviders(list);
		User sessionUser = (User) sessionMap.get("User");
		Company sessionCompany = (Company) sessionMap.get("Company");
		Company parentCompany = new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());
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

		BigDecimal CGST = new BigDecimal("0.00");
		BigDecimal SGST = new BigDecimal("0.00");
		BigDecimal UGST = new BigDecimal("0.00");
		BigDecimal IGST = new BigDecimal("0.00");
		BigDecimal managementFee = new BigDecimal("0.00");


		if (companyConfig != null) {
			if (companyConfig.getTaxtype() != null && companyConfig.getTaxtype().equalsIgnoreCase("GST")) {

				boolean territiryStatus = IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());

				managementFee = companyConfig.getBusGstTaxConfig().getManagementFee().setScale(0, RoundingMode.UP);

				CGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getBusGstTaxConfig().getCGST());
				totalGstTaxPer = totalGstTaxPer.add(companyConfig.getBusGstTaxConfig().getCGST());
				if (sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					SGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getBusGstTaxConfig().getSGST());
					totalGstTaxPer = totalGstTaxPer.add(companyConfig.getBusGstTaxConfig().getSGST());
				}
				if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !territiryStatus) {
					IGST = managementFee.divide(new BigDecimal("100.0")) .multiply(companyConfig.getBusGstTaxConfig().getIGST());
					CGST=new  BigDecimal(0);
					totalGstTaxPer =companyConfig.getBusGstTaxConfig().getIGST();
				}
				if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && territiryStatus) {
					UGST = managementFee.divide(new BigDecimal("100.0")) .multiply(companyConfig.getBusGstTaxConfig().getUGST());
					totalGstTaxPer= totalGstTaxPer.add(companyConfig.getBusGstTaxConfig().getUGST());
				}

				totalGstAmount = CGST.add(SGST).add(IGST).add(UGST);
				taxType = "GST";

				if (companyConfig.getBusGstTaxConfig() != null) {
					busGstTaxConfig.setConvenienceFee(
							companyConfig.getBusGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.UP));
					busGstTaxConfig.setManagementFee(
							companyConfig.getBusGstTaxConfig().getManagementFee().setScale(0, RoundingMode.UP));

				}
			} else {
				taxType = "Service";
				if (companyConfig.getBusServiceTaxConfig() != null) {
					busServiceTaxConfig.setBasicTax(
							companyConfig.getBusServiceTaxConfig().getBasicTax().setScale(2, RoundingMode.UP));
					busServiceTaxConfig.setKrishiKalyanCess(
							companyConfig.getBusServiceTaxConfig().getKrishiKalyanCess().setScale(2, RoundingMode.UP));
					busServiceTaxConfig.setSwatchBharathCess(
							companyConfig.getBusServiceTaxConfig().getSwatchBharathCess().setScale(2, RoundingMode.UP));
					busServiceTaxConfig.setTotalTax(
							companyConfig.getBusServiceTaxConfig().getTotalTax().setScale(2, RoundingMode.UP));
					busServiceTaxConfig.setConvenienceFee(
							companyConfig.getBusServiceTaxConfig().getConvenienceFee().setScale(0, RoundingMode.UP));
					busServiceTaxConfig.setManagementFee(
							companyConfig.getBusServiceTaxConfig().getManagementFee().setScale(0, RoundingMode.UP));

				}
			}
		}

		setTripId(tripId);
		userWallet = new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId());
		return SUCCESS;
	}

	public String createBusTravelRequest() {
		TripRequest tripRequest = new TripRequest();
		User sessionUser = (User) sessionMap.get("User");
		User userNew = userDao.getUserByUserId(sessionUser.getId());
		Company sessionCompany = (Company) sessionMap.get("Company");
		Company parentCompany = new CompanyDAO()
				.getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());
		String orderId = busOrderRow.getConfirmationNumber();
		busOrderRow.setTotalAmount(busTravelRequestQuotation.getTotalAmount());
		BigDecimal finalPrice = busOrderRow.getTotalAmount();
		BigDecimal serviceOrGstTax = new BigDecimal("0.00");
		CompanyConfig newCompanyConfig = new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());
		User walletUser = userNew;
		if (userNew != null) {
			if (sessionCompany.getCompanyRole() != null && sessionCompany.getCompanyRole().isCorporate()) {
				UserDAO userDAO = new UserDAO();
				walletUser = userDAO.getUserPasswordForLock(sessionCompany.getEmail());
			}
		}
		busTravelRequestQuotation.setBusType(busOrderRow.getBusType());
		busTravelRequestQuotation.setDropOff(busOrderRow.getDropOff());
		busTravelRequestQuotation.setPickUp(busOrderRow.getPickUp());
		busTravelRequestQuotation.setRemarks(busOrderRow.getRemarks());
		busTravelRequestQuotation.setLocation(busOrderRow.getLocation());

		busTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		busTravelRequestQuotationlist.add(busTravelRequestQuotation);
		BusTravelRequest busTravelRequest=new BusTravelRequest();
		busTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
		busTravelRequest.setCompanyId(sessionUser.getCompanyid());
		busTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
				? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
		busTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		//NEWLY WRITTEN BY RAHAM

		BusOrderCustomerDetail busOrderCustomerDetail=null;
		if(busOrderCustomerDetailList!=null && busOrderCustomerDetailList.size()>0) 
			busOrderCustomerDetail=busOrderCustomerDetailList.get(0);
		if(busOrderCustomerDetail!=null){
			busTravelRequest.setFirstName(busOrderCustomerDetail.getFirstName());
			busTravelRequest.setLastName(busOrderCustomerDetail.getLastName());
			busTravelRequest.setTitle(busOrderCustomerDetail.getTitle());
		}
		BusTravelRequest busTravelRequestnew = busTravelRequestDao.insertbusTravelRequestnew(busTravelRequest);
		if (busTravelRequestnew != null && busTravelRequestnew.getId() > 0) {
			boolean isInserted = busTravelRequestDao.insertBusQuotationList(busTravelRequestQuotationlist,
					busTravelRequestnew);
			if (isInserted) {
				if (busTravelRequestQuotationlist != null && busTravelRequestQuotationlist.size() > 0) {
					busOrderRow.setBookingCurrency(busTravelRequestQuotationlist.get(0).getCurrency());
					try {
						Date travelDate = new SimpleDateFormat("dd-MM-yyyy")
								.parse(busTravelRequestQuotationlist.get(0).getTravelDateTemp());
						busOrderRow.setTravelDate(travelDate);
					} catch (ParseException e) {
					}
				}
				OrderCustomer orderCustomer = new OrderCustomer();
				orderCustomer.setFirstName(busTravelRequest.getFirstName());
				orderCustomer.setLastName(busTravelRequest.getLastName());
				orderCustomer.setTitle(busTravelRequest.getTitle());
				orderCustomer.setEmail(sessionUser.getEmail());
				orderCustomer.setCompanyId(sessionCompany.getCompanyid()); 
				orderCustomer.setBookingType(CommonBookingStatusEnum.BUS.getMessage());
				orderCustomer.setConfigId(newCompanyConfig.getConfig_id());
				//ADDED BY BASHA
				orderCustomer.setCreatedByUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				FlightGstTax flightGstTax=null;
				BusOrderRowServiceTax busOrderRowServiceTax = null;
				BusOrderRowGstTax busOrderRowGstTax =  null;
				if(newCompanyConfig != null && newCompanyConfig.getCompanyConfigType().isB2E()) {
					if (newCompanyConfig.getTaxtype() != null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")) {
						busOrderRowGstTax = new BusOrderRowGstTax();
						busOrderRowGstTax.setConvenienceFee(busOrderRow.getConvenienceFee());
						busOrderRowGstTax.setCreatedAt(busOrderRow.getCreatedAt());
						busOrderRowGstTax.setManagementFee(busOrderRow.getManagementFee());
						busOrderRowGstTax .setApplicableFare(newCompanyConfig.getBusServiceTaxConfig().getApplicableFare());
						busOrderRowGstTax.setIGST(newCompanyConfig.getBusGstTaxConfig().getIGST().setScale(2, RoundingMode.UP));
						busOrderRowGstTax.setSGST(newCompanyConfig.getBusGstTaxConfig().getSGST().setScale(2, RoundingMode.UP));
						busOrderRowGstTax.setCGST(newCompanyConfig.getBusGstTaxConfig().getCGST().setScale(2, RoundingMode.UP));
						busOrderRowGstTax.setUGST(newCompanyConfig.getBusGstTaxConfig().getUGST().setScale(2, RoundingMode.UP));
						flightGstTax=getFlightGSTTax(busOrderRowGstTax, sessionCompany, parentCompany, busOrderRow);
						busOrderRowGstTax.setTotalGst(flightGstTax.getTotalTax());
						busOrderRow.setTotalGstTax(flightGstTax.getTotalGstAmount());
						busOrderRow.setBusOrderRowGstTax(busOrderRowGstTax);
						serviceOrGstTax=flightGstTax.getTotalGstAmount();
					} else {
						if (newCompanyConfig.getBusServiceTaxConfig() != null) {
							busOrderRowServiceTax = new BusOrderRowServiceTax();
							busOrderRowServiceTax.setConvenienceFee(busOrderRow.getConvenienceFee());
							busOrderRowServiceTax.setCreatedAt(busOrderRow.getCreatedAt());
							busOrderRowServiceTax.setManagementFee(busOrderRow.getManagementFee());
							busOrderRowServiceTax
							.setApplicableFare(newCompanyConfig.getBusServiceTaxConfig().getApplicableFare());
							busOrderRowServiceTax.setBasicTax(newCompanyConfig.getBusServiceTaxConfig().getBasicTax());
							busOrderRowServiceTax.setKrishiKalyanCess(
									newCompanyConfig.getBusServiceTaxConfig().getKrishiKalyanCess());
							busOrderRowServiceTax.setSwatchBharathCess(
									newCompanyConfig.getBusServiceTaxConfig().getSwatchBharathCess());
							busOrderRowServiceTax.setTotalTax(newCompanyConfig.getBusServiceTaxConfig().getTotalTax());
							busOrderRow.setBusOrderRowServiceTax(busOrderRowServiceTax);
							serviceOrGstTax= busOrderRow.getServiceTax();
						}
					}
				}
				if(flightGstTax==null) 
					busOrderRow.setTotalGstTax(new BigDecimal("0.00"));
				if(busOrderRow.getServiceTax()==null)
					busOrderRow.setServiceTax(new BigDecimal("0.00"));
				busOrderRow.setOrderCustomer(orderCustomer);
				busOrderRow.setTaxes(new BigDecimal("0.00"));
				busOrderRow.setTotalAmount(busTravelRequestQuotation.getTotalAmount());
				busOrderRow.setCreatedAt(new Timestamp(new Date().getTime()));
				busOrderRow.setApiComments("ok");
				busOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
				busOrderRow.setCompanyId(String.valueOf(sessionUser.getCompanyid()));
				busOrderRow.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)
						? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId()));
				busOrderRow.setConfigId(String.valueOf(newCompanyConfig.getConfig_id()));
				busOrderRow.setCreatedBy(sessionUser.getUsername());
				busOrderRow.setUpdatedBy(sessionUser.getUsername());
				busOrderRow.setProcessingFees(new BigDecimal("0.00"));
				busOrderRow.setOrderId(busOrderRow.getConfirmationNumber());
				busOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
				busOrderRow.setBaseToBookingExchangeRate(new BigDecimal(1));
				busOrderRow.setStatusAction("Confirmed");
				busOrderRow.setPaymentStatus("Success");
				busOrderRow.setEmpName(busTravelRequest.getTitle() + " " + busTravelRequest.getFirstName() + " "
						+ busTravelRequest.getLastName());
				busOrderRow.setBookingMode(CommonBookingStatusEnum.BOOKING_MODE_OFFLINE.getMessage());
				busOrderRow.setOrigin(busOrderRow.getPickUp());
				busOrderRow.setDestination(busOrderRow.getDropOff());
				busOrderRow.setCancellationPolicy(busOrderRow.getRemarks());
				busOrderRow.setBookingDate(DateConversion.StringToDate(busOrderRow.getBusBookingDate()));
				RmConfigModel  rmConfigModel=rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid());
				   if(rmConfigModel!=null){
				   BusOrderRowRmConfigStruct busOrderRowRmConfigStruct=new BusOrderRowRmConfigStruct();
				  busOrderRowRmConfigStruct.setRmDynamicData(rmConfigModel.getDynamicFieldsData());
				   busOrderRow.setBusOrderRowRmConfigStruct(busOrderRowRmConfigStruct);
				   }
				BusOrderRow busOrderRowNew = busTravelRequestDao.insertBusOrderRow(busOrderRow);
				if (busOrderRowNew != null) {
					busOrderRowNew .setInvoiceNo(RandomConfigurationNumber.generateBusInvoiceNumber(busOrderRowNew.getId()));
					Long orderIdTemp = busOrderRowNew.getId() + 1000;
					orderId = RandomConfigurationNumber.generateBusInvoiceNumber(orderIdTemp);
					busOrderRowNew.setOrderId(orderId);
					busOrderRowNew = new BusOrderDao().updateBusOrderRowDetail(busOrderRowNew);
				}

				CompanyDAO companyDAO = new CompanyDAO();
				Map<String, BigDecimal> markups = new LinkedHashMap<>();
				if (busOrderRowNew != null) {
					if (sessionCompany.getCompanyRole().isSuperUser()) {
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					} else {
						Company companyParent = companyDAO.getParentCompany(sessionCompany);
						markups.put(String.valueOf(companyParent.getCompanyid()), busOrderRowNew.getMarkUp());
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					}
				}
				List<Company> companyListBottomToTop = new LinkedList<>();
				List<User> userListBottomToTop = new LinkedList<>();
				Map<Integer, CutandPayModel> cutAndPayUserMap = new LinkedHashMap<>();
				FlightOrderDao flightOrderDao = new FlightOrderDao();
				BusOrderDao busOrderDao = new BusOrderDao();
				BigDecimal finalPriceAfterTax = finalPrice.add(serviceOrGstTax!= null ?serviceOrGstTax : new BigDecimal(0));
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
								finalPriceAfterTax, walletUser);

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
														CommonBookingStatusEnum.BUS_REMARKS.getMessage(), orderId,
														busOrderRowNew.getInvoiceNo());
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
																	CommonBookingStatusEnum.BUS_FAILEDREMARKS
																	.getMessage(),
																	orderId, busOrderRowNew.getInvoiceNo());
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
					busOrderRowNew.setPaymentStatus("Failed");
					busOrderRowNew = busOrderDao.updateBusOrderRowDetailPaymentStatus(busOrderRowNew);
					addActionMessage(CommonBookingStatusEnum.WALLET_ERROR.getMessage());
					return ERROR;
				} else {
					busOrderRowNew.setPaymentStatus("Success");
					busOrderRowNew = busOrderDao.updateBusOrderRowDetailPaymentStatus(busOrderRowNew);
				}
				if (busOrderRowNew != null) {
					BusTravelRequestQuotation busTravelRequestQuotationNew = busTravelRequestDao
							.updateBusTravelRequestQuotationWithOrderId(busOrderRowNew,
									busTravelRequestQuotation.getId());
					if (busTravelRequestQuotationNew != null) {
						HotelFlightBookingStatus hotelFlightBookingStatus = new HotelFlightBookingStatus();
						if (busTravelRequestQuotationNew.getHotetFlightBookingStatus() != null
								&& busTravelRequestQuotationNew.getHotetFlightBookingStatus().getId() != null)
							hotelFlightBookingStatus
							.setId(busTravelRequestQuotationNew.getHotetFlightBookingStatus().getId());
						hotelFlightBookingStatus.setBooked(TravelRequestStatusEnum.BOOKED.getValue());
						busTravelRequestDao.updateHotelOrFlightQuotationBookingStatus(hotelFlightBookingStatus);
						new BusTravelRequestDao().updateBusTravelRequestQuotationHide(
								busTravelRequestQuotationNew.getBusTravelRequest().getId());
						new CompanyDAO().insertEmail(busOrderRowNew.getOrderId(), 0, Email.EMAIL_TYPE_BUS_INVOICE);
						addActionMessage("Successfully Bus Booking Done.");
					} else {
						addActionMessage("We found somr error while booking.");
						return ERROR;
					}
				}

				if (tripId != null) {
					tripRequest.setId(tripId);
					tripRequest.setBusTravelRequest(busTravelRequestnew);
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
							? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
					tripRequest = tripRequestDao.updateTripRequestById(tripRequest);
				}
				else {
					tripRequest.setBusTravelRequest(busTravelRequestnew);
					tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
							? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
					tripRequest = tripRequestDao.insertTripRequest(tripRequest);
					if (tripRequest != null && tripRequest.getId() > 0) {
						tripId=RandomConfigurationNumber.generateTripId(tripRequest.getId());
						tripRequest.setTripId(tripId);
						if (tripRequestDao.updateTripRequestNumber(tripRequest))
							addActionMessage("Successfully created");
						else
							addActionMessage("Something went wrong.Please wait.");
					}
				}
				if(busOrderCustomerDetailList!=null && busOrderCustomerDetailList.size()>0){
					for(BusOrderCustomerDetail busOrderCustomer:busOrderCustomerDetailList){
						busOrderCustomer.setEmail(sessionUser.getEmail());
						busOrderCustomer.setAge("");
						busOrderCustomer.setCreatedAt(new Timestamp(new Date().getTime()));
						busOrderCustomer.setIsSleeper(false);
						busOrderCustomer.setGender(busOrderCustomer.getTitle().equalsIgnoreCase("Mr")
								|| busOrderCustomer.getTitle().equalsIgnoreCase("Master") ? "M" : "F");
						busOrderCustomer.setBusOrderRow(busOrderRowNew);
						BigDecimal seatPrice=busOrderCustomer.getBaseAmount().add(busOrderCustomer.getMarkUp()).add(busOrderCustomer.getTax());
						busOrderCustomer.setSeatPrice(seatPrice);
						RmConfigTripDetailsModel configTripDetailsModel=busOrderCustomer.getRmConfigTripDetailsModel();
						if(configTripDetailsModel!=null){
							configTripDetailsModel.setOrderId(busOrderRowNew.getOrderId());
							configTripDetailsModel.setOrdertype("Bus");
							busOrderCustomer.setRmConfigTripDetailsModel(configTripDetailsModel);
						}
						busOrderCustomer.setPaxId(RandomConfigurationNumber.generateRandomPaxID());
						busTravelRequestDao.insertBusOrderCustomerDetail(busOrderCustomer);
					}
					new NotificationAction().insertNotificationOneandAll(sessionUser,
							String.valueOf(busTravelRequestnew.getId()), "bus book Request created",
							"Bus book Request notification", NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),
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

	public String getBusTravelRequestList() {
		busTravelRequest = busTravelRequestDao.getBusTravelRequestDetails(busTravelRequest.getId());
		countryList = new CountryDao().getCountryList();
		busTravelRequestQuotationlist = busTravelRequestDao
				.getBusRequestTravelQuotationBookedList(busTravelRequest.getId());
		List<BusTravelRequestQuotation> updatedBusTravelRequestQuotation = new ArrayList<>();
		for (BusTravelRequestQuotation busTravelRequestQuotation : busTravelRequestQuotationlist) {
			busTravelRequestQuotation.getBusOrderRow().setBasePrice(
					busTravelRequestQuotation.getBusOrderRow().getBasePrice().setScale(0, RoundingMode.HALF_UP));
			busTravelRequestQuotation.getBusOrderRow().setOtherTaxes(
					busTravelRequestQuotation.getBusOrderRow().getOtherTaxes().setScale(0, RoundingMode.HALF_UP));
			busTravelRequestQuotation.getBusOrderRow().setProcessingFees(
					busTravelRequestQuotation.getBusOrderRow().getProcessingFees().setScale(0, RoundingMode.HALF_UP));
			busTravelRequestQuotation.getBusOrderRow().setTotalAmount(
					busTravelRequestQuotation.getBusOrderRow().getTotalAmount().setScale(0, RoundingMode.HALF_UP));
			busTravelRequestQuotation.getBusOrderRow()
			.setTaxes(busTravelRequestQuotation.getBusOrderRow().getTaxes().setScale(0, RoundingMode.HALF_UP));
			busTravelRequestQuotation.getBusOrderRow().setSupplierPrice(
					busTravelRequestQuotation.getBusOrderRow().getSupplierPrice().setScale(0, RoundingMode.HALF_UP));
			busTravelRequestQuotation.getBusOrderRow().setServiceTax(
					busTravelRequestQuotation.getBusOrderRow().getServiceTax().setScale(0, RoundingMode.HALF_UP));
			updatedBusTravelRequestQuotation.add(busTravelRequestQuotation);
		}
		busTravelRequestQuotationlist = updatedBusTravelRequestQuotation;
		return SUCCESS;
	}

	public String getAllBusTravelRequestList() {
		setBusTravelRequestlist(busTravelRequestDao.getAllBusTravelRequestList());
		return SUCCESS;
	}

	public String generateBusInvoice() {
		busOrderRow = busTravelRequestDao.getBusOrderRowDetailsById(orderId);
		return SUCCESS;
	}

	public String generatePassengerDetailsById() {
		busOrderRow = busTravelRequestDao.getBusOrderRowDetailsById(idForDetails);
		busOrderRow.setProcessingFees(busOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		busOrderRow.setManagementFee(busOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		busOrderRow.setServiceTax(busOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		busOrderRow.setConvenienceFee(busOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		busOrderRow.setOtherTaxes(busOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		busOrderRow.setTotalAmount(busOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
		return SUCCESS;
	}

	public BusTravelRequest getBusQuotationRow() {
		return busTravelRequest;
	}

	public void setBusQuotationRow(BusTravelRequest hotelQuotationRow) {
		this.busTravelRequest = hotelQuotationRow;
	}

	public String getBusTravelRequestDetails() {
		busTravelRequest = busTravelRequestDao.getBusTravelRequestDetails(busTravelRequest.getId());
		countryList = new CountryDao().getCountryList();
		busTravelRequestQuotationlist = busTravelRequestDao
				.getBusRequestTravelQuotationBookedList(busTravelRequest.getId());

		for (BusTravelRequestQuotation busTravelRequestQuotation : busTravelRequestQuotationlist) {
			busTravelRequestQuotation
			.setTotalAmount(busTravelRequestQuotation.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
		}

		return SUCCESS;
	}

	public String updateBusTravelRequestDetails() {
		User sessionUser = (User) sessionMap.get("User");
		// HotetTravelRequest
		// hotelTravelRequest=hotelOrderRowFineSheetDao.getBusTravelRequestDetails(hotelQuotationRow.getId());
		busTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
				? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
		busTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		BusTravelRequest hotetTravelRequestNew = busTravelRequestDao.updateBusTravelRequestDetails(busTravelRequest);
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

	public String generateInvoicemail() {
		/*
		 * BusOrderRow
		 * busOrderRow=busTravelRequestDao.getBusOrderRowDetailsById(orderId);
		 * setBusOrderRowForInvoice(busOrderRow); return SUCCESS;
		 */
		busOrderRow = busTravelRequestDao.getBusOrderRowDetailsById(orderId);
		setBusOrderRow(busOrderRow);
		return SUCCESS;
	}


	public static FlightGstTax getFlightGSTTax(BusOrderRowGstTax busOrderRowGstTax,Company childCompany,Company parentCompany, BusOrderRow busOrderRow){

		CompanyEntity  companyEntity=new CompanyEntity();
		String entityState=null;
		if(busOrderRow.getCompanyEntityId()!=null) 
			companyEntity = new CompanyDAO().companyEntityProfile(busOrderRow.getCompanyEntityId().intValue());
		if(companyEntity!=null && companyEntity.getState()!=null) 
			entityState=companyEntity.getState(); 

		BigDecimal managementFee  =busOrderRowGstTax.getManagementFee()!=null?busOrderRowGstTax.getManagementFee():new BigDecimal("0.0");
		BigDecimal CGSTAmount = new BigDecimal("0.0");
		BigDecimal CGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTAmount = new BigDecimal("0.0");
		//BigDecimal IGST = new BigDecimal("0.0");
		//BigDecimal UGST = new BigDecimal("0.0");
		BigDecimal totalGstAmount = new BigDecimal("0.0");
		BigDecimal totalGstPer = new BigDecimal("0.0");
		boolean isParentCompanyUT=IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());

		CGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getCGST());
		CGSTPer  = busOrderRowGstTax.getCGST();

		if(entityState!=null){ 
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(entityState.trim());
			if(isParentCompanyUT && isBillingCompanyUT){
				CommonGSTPer =busOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getUGST());
				busOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				busOrderRowGstTax.setSGST(new BigDecimal("0.0"));

			}
			if(!isParentCompanyUT && !isBillingCompanyUT){
				if(entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =busOrderRowGstTax.getSGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getSGST());    
					busOrderRowGstTax.setIGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				}
				if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =busOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getIGST());   
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					busOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(entityState.trim())){
				CommonGSTPer =busOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getUGST());
				busOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				busOrderRowGstTax.setSGST(new BigDecimal("0.0"));
			}

			if(isParentCompanyUT && !isBillingCompanyUT){
				if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(entityState.trim())){
					CommonGSTPer =busOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getIGST());    
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					busOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setCGST(CGSTPer);
				}
			}
		}else{
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim());
			if(isParentCompanyUT && isBillingCompanyUT){
				CommonGSTPer =busOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getUGST());
				busOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				busOrderRowGstTax.setSGST(new BigDecimal("0.0"));

			}
			if(!isParentCompanyUT && !isBillingCompanyUT){
				if(childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =busOrderRowGstTax.getSGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getSGST());    
					busOrderRowGstTax.setIGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				}
				if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =busOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getIGST());   
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					busOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
				CommonGSTPer =busOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getUGST());
				busOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				busOrderRowGstTax.setSGST(new BigDecimal("0.0"));
			}

			if(isParentCompanyUT && !isBillingCompanyUT){
				if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
					CommonGSTPer =busOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(busOrderRowGstTax.getIGST());   
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					busOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					busOrderRowGstTax.setCGST(CGSTPer);
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
	public BusTravelRequest getModel() {
		return busTravelRequest;
	}

	public BusTravelRequest getBusTravelRequest() {
		return busTravelRequest;
	}

	public void setBusTravelRequest(BusTravelRequest busTravelRequest) {
		this.busTravelRequest = busTravelRequest;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<BusTravelRequest> getBusTravelRequestlist() {
		return busTravelRequestlist;
	}

	public void setBusTravelRequestlist(List<BusTravelRequest> busTravelRequestlist) {
		this.busTravelRequestlist = busTravelRequestlist;
	}

	public List<BusTravelRequestQuotation> getBusTravelRequestQuotationlist() {
		return busTravelRequestQuotationlist;
	}

	public void setBusTravelRequestQuotationlist(List<BusTravelRequestQuotation> busTravelRequestQuotationlist) {
		this.busTravelRequestQuotationlist = busTravelRequestQuotationlist;
	}

	public Long getBusQuotationRequestId() {
		return busQuotationRequestId;
	}

	public void setBusQuotationRequestId(Long busQuotationRequestId) {
		this.busQuotationRequestId = busQuotationRequestId;
	}

	public BusTravelRequestQuotation getBusTravelRequestQuotation() {
		return busTravelRequestQuotation;
	}

	public void setBusTravelRequestQuotation(BusTravelRequestQuotation busTravelRequestQuotation) {
		this.busTravelRequestQuotation = busTravelRequestQuotation;
	}

	public BusOrderRow getBusOrderRow() {
		return busOrderRow;
	}

	public void setBusOrderRow(BusOrderRow busOrderRow) {
		this.busOrderRow = busOrderRow;
	}

	public BusOrderRow getBusOrderRowForInvoice() {
		return busOrderRowForInvoice;
	}

	public void setBusOrderRowForInvoice(BusOrderRow busOrderRowForInvoice) {
		this.busOrderRowForInvoice = busOrderRowForInvoice;
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

	public BusServiceTaxConfig getBusServiceTaxConfig() {
		return busServiceTaxConfig;
	}

	public void setBusServiceTaxConfig(BusServiceTaxConfig busServiceTaxConfig) {
		this.busServiceTaxConfig = busServiceTaxConfig;
	}

	public Long getIdForDetails() {
		return idForDetails;
	}

	public void setIdForDetails(Long idForDetails) {
		this.idForDetails = idForDetails;
	}

	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}

	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
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

	public UserWallet getUserWallet() {
		return userWallet;
	}

	public void setUserWallet(UserWallet userWallet) {
		this.userWallet = userWallet;
	}

	public BusGstTaxConfig getBusGstTaxConfig() {
		return busGstTaxConfig;
	}

	public void setBusGstTaxConfig(BusGstTaxConfig busGstTaxConfig) {
		this.busGstTaxConfig = busGstTaxConfig;
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
	/*public static void main (String args[]){
		int i=1;
		String j="a";
		String k="Basha";
		int m;
		int n=1;
		if(!j.trim().equalsIgnoreCase("") && !k.trim().equalsIgnoreCase("")){		
		for(m=0;m<=k.length();m++){
			int b=k.IndexOf(i);

			if(m==n){
				break;

			}
		}

	}
	}*/

	public BigDecimal getTotalGstTaxPer() {
		return totalGstTaxPer;
	}

	public void setTotalGstTaxPer(BigDecimal totalGstTaxPer) {
		this.totalGstTaxPer = totalGstTaxPer;
	}

	public List<BusOrderCustomerDetail> getBusOrderCustomerDetailList() {
		return busOrderCustomerDetailList;
	}

	public void setBusOrderCustomerDetailList(List<BusOrderCustomerDetail> busOrderCustomerDetailList) {
		this.busOrderCustomerDetailList = busOrderCustomerDetailList;
	}

}
