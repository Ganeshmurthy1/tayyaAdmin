package com.admin.common.quotation.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
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
import com.admin.common.config.model.CarServiceTaxConfig;
import com.admin.common.quotation.dao.CarTravelRequestDao;
import com.admin.common.quotation.model.CarTravelRequest;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.admin.common.util.CommonUtilSession;
import com.admin.enums.utility.CommonBookingStatusEnum;
import com.admin.enums.utility.IndianUnionTerritories;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.CarOrderDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.car.model.CarGstTaxConfig;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.car.model.CarOrderRowGstTax;
import com.lintas.admin.car.model.CarOrderRowServiceTax;
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
import com.tayyarah.admin.orderrow.rm.structure.CarOrderRowRmConfigStruct;
import com.tayyarah.car.model.CarOrderCustomer;
import com.tayyarah.gst.model.FlightGstTax;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

public class CarTravelRequestAction extends ActionSupport implements ModelDriven<CarTravelRequest>,SessionAware{
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CarTravelRequestAction.class);
	private static final long serialVersionUID = 1L;
	CarTravelRequest carTravelRequest=new CarTravelRequest();
	SessionMap<String, Object> sessionMap; 
	CarTravelRequestQuotation carTravelRequestQuotation =new CarTravelRequestQuotation();
	CarTravelRequestDao carTravelRequestDao=new CarTravelRequestDao();
	private List<Country> countryList=null;
	CarOrderRow carOrderRow=new CarOrderRow();
	private List<CarTravelRequest> carTravelRequestlist=null;
	private List<CarOrderCustomer> carOrderCustomerList=new ArrayList<>();
	private List<CarTravelRequestQuotation> carTravelRequestQuotationlist=new ArrayList<>();
	private Long carQuotationRequestId;
	private Long tripId;
	TripRequestDao tripRequestDao=new TripRequestDao();
	private Long orderId;
	private CarServiceTaxConfig carServiceTaxConfig = new CarServiceTaxConfig();
	private CarGstTaxConfig carGstTaxConfig = new CarGstTaxConfig();
	private Long idtosend;
	private Long detailsid;
	private String taxType;
	BigDecimal totalGstAmount = new BigDecimal("0.00");
	private WalletAmountTranferHistory payTxInfo ;
	UserDAO userDao=new UserDAO();
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao =new ApiProviderDao();
	RmConfigModel rmConfigModel=new RmConfigModel();
	List<String> fieldName = new ArrayList<String>();
	RmConfigDao rmConfigDao=new RmConfigDao();
	RmConfigTripDetailsModel configTripDetailsModel=new RmConfigTripDetailsModel();
	private UserWallet userWallet=new UserWallet();
	BigDecimal totalGstTaxPer = new BigDecimal("0.00");
	
	public String  editCarTravelRequestQuotation(){
		countryList = new CountryDao().getCountryList();
		//TripRequest tripRequest=tripRequestDao.getTripRequestById(idtosend);
		carTravelRequest=carTravelRequestDao.getCarQuotationRequestDetails(idtosend);
		if(carTravelRequest!=null){
			carTravelRequestQuotationlist=carTravelRequest.getCarTravelRequestQuotations();
		}
		//carTravelRequestQuotation=carTravelRequestDao.carRequestQuotationUpdate(carTravelRequestQuotation);
		return SUCCESS;
	}


	public String getCarOfflineInvoice(){
		CarOrderRow carOrderRow=carTravelRequestDao.getCarOrderRowDetailsById(orderId);
		if(carOrderRow!=null)
			setCarOrderRow(carOrderRow);

		return SUCCESS;
	}

	public String 	updateCarTravelRequestQuotation(){
		carTravelRequestDao.updateCarTravelRequestQuotation(carTravelRequestQuotation);
		return SUCCESS;

	}

	public String  editCarOrderRowDetails(){
		carOrderRow=carTravelRequestDao.getCarOrderRowDetailsById(idtosend);
		carOrderRow.setTollOrParkingCharges(carOrderRow.getTollOrParkingCharges().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setDriverAllowanceDay(carOrderRow.getDriverAllowanceDay().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setDriverAllowanceNight(carOrderRow.getDriverAllowanceNight().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setMarkUp(carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setManagementFee(carOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setConvenienceFee(carOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setServiceTax(carOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setOtherTaxes(carOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setBasePrice(carOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		carOrderRow.setTotalAmount(carOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
		setCarOrderRow(carOrderRow);
		return SUCCESS;
	}

	public String  editCarTravelRequest(){
		setCarTravelRequest(carTravelRequestDao.getCarTravelRequestDetails(idtosend));
		return SUCCESS;
	}


	public String  updateCarOrderRow(){
		carTravelRequestDao.updateCarOrderRow(carOrderRow);
		return SUCCESS;

	}

	public String  updateCarTravelRequest(){
		carTravelRequestDao.updateCarTravelRequestDetails(carTravelRequest);
		return SUCCESS;
	}


	public String goCarTravelRequest(){
		countryList = new CountryDao().getCountryList();
		List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
		setApiProviders(list);
		User sessionUser = (User)sessionMap.get("User");
		Company sessionCompany = (Company) sessionMap.get("Company");
		Company parentCompany = new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());

		try{
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
		}catch (Exception e) {
		}
		if(tripId != null){
			TripRequest tripRequest = tripRequestDao.getTripRequestById(tripId);
			configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
		}
		CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());
		BigDecimal CGST = new BigDecimal("0.00");
		BigDecimal SGST = new BigDecimal("0.00");
		BigDecimal UGST = new BigDecimal("0.00");
		BigDecimal IGST = new BigDecimal("0.00");
		BigDecimal managementFee = new BigDecimal("0.00");
		if(companyConfig.getTaxtype()!=null && companyConfig.getTaxtype().equalsIgnoreCase("GST")){

			boolean territiryStatus = IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());

			managementFee = companyConfig.getCarGstTaxConfig().getManagementFee().setScale(0, RoundingMode.UP);

			CGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getCarGstTaxConfig().getCGST());
			totalGstTaxPer = totalGstTaxPer.add(companyConfig.getCarGstTaxConfig().getCGST());
			if (sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
				SGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getCarGstTaxConfig().getSGST());
				totalGstTaxPer = totalGstTaxPer.add(companyConfig.getCarGstTaxConfig().getSGST());
			}
			if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !territiryStatus) {
				IGST = managementFee.divide(new BigDecimal("100.0")) .multiply(companyConfig.getCarGstTaxConfig().getIGST());
				CGST=new  BigDecimal(0);
				totalGstTaxPer =companyConfig.getCarGstTaxConfig().getIGST();
			}
			if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && territiryStatus) {
				UGST = managementFee.divide(new BigDecimal("100.0")) .multiply(companyConfig.getCarGstTaxConfig().getUGST());
				totalGstTaxPer = totalGstTaxPer.add(companyConfig.getCarGstTaxConfig().getUGST());
			}

			totalGstAmount = CGST.add(SGST).add(IGST).add(UGST);
			setTaxType("GST");


			if(companyConfig!=null && companyConfig.getCarGstTaxConfig()!=null){
				carGstTaxConfig.setConvenienceFee(companyConfig.getCarGstTaxConfig().getConvenienceFee().setScale(0,RoundingMode.HALF_UP));
				carGstTaxConfig.setManagementFee(companyConfig.getCarGstTaxConfig().getManagementFee().setScale(0,RoundingMode.HALF_UP));

			}
		}
		else{
			if(companyConfig!=null && companyConfig.getCarServiceTaxConfig()!=null){
				taxType = "Service";
				carServiceTaxConfig.setBasicTax(companyConfig.getCarServiceTaxConfig().getBasicTax().setScale(2,RoundingMode.HALF_UP)); 
				carServiceTaxConfig.setKrishiKalyanCess(companyConfig.getCarServiceTaxConfig().getKrishiKalyanCess().setScale(2,RoundingMode.HALF_UP));
				carServiceTaxConfig.setSwatchBharathCess(companyConfig.getCarServiceTaxConfig().getSwatchBharathCess().setScale(2,RoundingMode.HALF_UP));
				carServiceTaxConfig.setTotalTax(companyConfig.getCarServiceTaxConfig().getTotalTax().setScale(2,RoundingMode.HALF_UP));
				carServiceTaxConfig.setConvenienceFee(companyConfig.getCarServiceTaxConfig().getConvenienceFee().setScale(0,RoundingMode.HALF_UP));
				carServiceTaxConfig.setManagementFee(companyConfig.getCarServiceTaxConfig().getManagementFee().setScale(0,RoundingMode.HALF_UP));

			}
		}
		setTripId(tripId);
		userWallet=new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId()) ;
		return SUCCESS;
	}

	public String  createCarTravelRequest(){
		TripRequest tripRequest =new TripRequest();
		User sessionUser=(User)sessionMap.get("User");
		User userNew=userDao.getUserByUserId(sessionUser.getId());
		Company sessionCompany=(Company)sessionMap.get("Company");
		Company parentCompany = new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());
		String orderId = carOrderRow.getConfirmationNumber();
		BigDecimal finalPrice = carOrderRow.getTotalAmount();
		carOrderRow.setTotalAmount(finalPrice);
		BigDecimal serviceOrGstTax = new BigDecimal("0.00");
		CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());
		User walletUser= userNew;
		if(userNew != null){
			if(sessionCompany.getCompanyRole()!=null && sessionCompany.getCompanyRole().isCorporate())
			{
				UserDAO userDAO = new UserDAO();
				walletUser = userDAO.getUserPasswordForLock(sessionCompany.getEmail());
			}
		}
		carTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		carTravelRequestQuotationlist.add(carTravelRequestQuotation);
		CarTravelRequest carTravelRequest=new CarTravelRequest();
		 
		carTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
		carTravelRequest.setCompanyId(sessionUser.getCompanyid());
		carTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		carTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		CarOrderCustomer carOrderCustomer=null;
		if(carOrderCustomerList!=null && carOrderCustomerList.size()>0) 
			carOrderCustomer=carOrderCustomerList.get(0);
		if(carOrderCustomer!=null){
			carTravelRequest.setFirstName(carOrderCustomer.getFirstName());
			carTravelRequest.setLastName(carOrderCustomer.getLastName());
			carTravelRequest.setTitle(carOrderCustomer.getTitle());
		}
 
		CarTravelRequest carTravelRequestnew=carTravelRequestDao.insertcarTravelRequestnew(carTravelRequest);
		if(carTravelRequestnew!=null && carTravelRequestnew.getId()>0){
			boolean isInserted=carTravelRequestDao.insertCarQuotationList(carTravelRequestQuotationlist, carTravelRequestnew);
			if(isInserted){
				if(carTravelRequestQuotationlist!=null && carTravelRequestQuotationlist.size()>0)
				{
					carOrderRow.setBookingCurrency(carTravelRequestQuotationlist.get(0).getCurrency());
				}

				OrderCustomer orderCustomer = new OrderCustomer();
				//Long servictaxId=carOrderRowServiceTax.getId();
				orderCustomer.setFirstName(carTravelRequest.getFirstName());
				orderCustomer.setLastName(carTravelRequest.getLastName());
				orderCustomer.setTitle(carTravelRequest.getTitle());
				orderCustomer.setEmail(sessionUser.getEmail());
				orderCustomer.setCompanyId(sessionCompany.getCompanyid());
				orderCustomer.setBookingType(CommonBookingStatusEnum.CAR.getMessage());
				orderCustomer.setConfigId(newCompanyConfig.getConfig_id());
				//ADDED BY BASHA
				orderCustomer.setCreatedByUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
				carOrderRow.setTravelDate(carOrderRow.getTravelDateTemp());
				carOrderRow.setRemarks(carTravelRequestQuotation.getRemarks());
				carOrderRow.setEmpNmae(carTravelRequest.getTitle() + "  "+carTravelRequest.getFirstName()+ "  "+ carTravelRequest.getLastName());
				carOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
				carOrderRow.setBaseToBookingExchangeRate(new BigDecimal(1));
				carOrderRow.setOrderCustomer(orderCustomer);
				//carOrderRow.setSupplierPrice(carOrderRow.getBasePrice().add(carOrderRow.getOtherTaxes()));
				//carOrderRow.setSupplierName(carOrderRow.getSupplierName());
				CarOrderRowServiceTax carOrderRowServiceTax=null;
				FlightGstTax flightGstTax=null;
				CarOrderRowGstTax carOrderRowGstTax=null;


				if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")){
					if(newCompanyConfig.getCarGstTaxConfig()!=null){
						carOrderRowGstTax=new CarOrderRowGstTax();
						carOrderRowGstTax.setIGST(newCompanyConfig.getCarGstTaxConfig().getIGST().setScale(2,RoundingMode.HALF_UP));
						carOrderRowGstTax.setSGST(newCompanyConfig.getCarGstTaxConfig().getSGST().setScale(2,RoundingMode.HALF_UP));
						carOrderRowGstTax.setUGST(newCompanyConfig.getCarGstTaxConfig().getUGST().setScale(2,RoundingMode.HALF_UP));
						carOrderRowGstTax.setCGST(newCompanyConfig.getCarGstTaxConfig().getCGST().setScale(2,RoundingMode.HALF_UP));
						carOrderRowGstTax.setApplicableFare(newCompanyConfig.getCarGstTaxConfig().getApplicableFare());

						carOrderRowGstTax.setConvenienceFee(newCompanyConfig.getCarGstTaxConfig().getConvenienceFee().setScale(0,RoundingMode.HALF_UP));
						carOrderRowGstTax.setManagementFee(newCompanyConfig.getCarGstTaxConfig().getManagementFee().setScale(0,RoundingMode.HALF_UP));
						flightGstTax=getFlightGSTTax(carOrderRowGstTax, sessionCompany, parentCompany,carOrderRow);
						carOrderRowGstTax.setTotalGst(flightGstTax.getTotalTax());
						carOrderRow.setTotalGstTax(flightGstTax.getTotalGstAmount());
						carOrderRow.setCarOrderRowGstTax(carOrderRowGstTax);
						serviceOrGstTax=flightGstTax.getTotalGstAmount();
					}

				}
				else{
					if(newCompanyConfig!=null &&newCompanyConfig.getCompanyConfigType().isB2E()&&  newCompanyConfig.getCarServiceTaxConfig()!=null){
						carOrderRowServiceTax = new CarOrderRowServiceTax();
						carOrderRowServiceTax.setConvenienceFee(carOrderRow.getConvenienceFee());
						carOrderRowServiceTax.setCreatedAt(carOrderRow.getCreatedAt());
						carOrderRowServiceTax.setManagementFee(carOrderRow.getManagementFee());
						carOrderRowServiceTax.setApplicableFare(newCompanyConfig.getCarServiceTaxConfig().getApplicableFare());
						carOrderRowServiceTax.setBasicTax(newCompanyConfig.getCarServiceTaxConfig().getBasicTax());
						carOrderRowServiceTax.setKrishiKalyanCess(newCompanyConfig.getCarServiceTaxConfig().getKrishiKalyanCess());
						carOrderRowServiceTax.setSwatchBharathCess(newCompanyConfig.getCarServiceTaxConfig().getSwatchBharathCess());
						carOrderRowServiceTax.setTotalTax(newCompanyConfig.getCarServiceTaxConfig().getTotalTax());
						carOrderRow.setCarOrderRowServiceTax(carOrderRowServiceTax);
						serviceOrGstTax= carOrderRow.getServiceTax();
					}

				}

				if(flightGstTax==null) 
					carOrderRow.setTotalGstTax(new BigDecimal("0.00"));
				if(carOrderRow.getServiceTax()==null)
					carOrderRow.setServiceTax(new BigDecimal("0.00"));

				carOrderRow.setTaxes(new BigDecimal("0.00"));
				carOrderRow.setCreatedAt(new Timestamp(new Date().getTime()));
				carOrderRow.setApiComments("ok");
				carOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
				carOrderRow.setCompanyId(String.valueOf(sessionUser.getCompanyid()));
				carOrderRow.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId()));
				carOrderRow.setConfigId(String.valueOf(newCompanyConfig.getConfig_id()));
				carOrderRow.setCreatedBy(sessionUser.getUsername());
				carOrderRow.setUpdatedBy(sessionUser.getUsername());
				carOrderRow.setProcessingFees(new BigDecimal("0.00"));
				carOrderRow.setOrderId(carOrderRow.getConfirmationNumber());
				carOrderRow.setStatusAction("Confirmed");
				carOrderRow.setPaymentStatus("Success");
				carOrderRow.setBookingMode(CommonBookingStatusEnum.BOOKING_MODE_OFFLINE.getMessage());
				carOrderRow.setBookingDate(DateConversion.StringToDate(carOrderRow.getCarBookingDate()));
				RmConfigModel  rmConfigModel=rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid());
				   if(rmConfigModel!=null){
				   CarOrderRowRmConfigStruct carOrderRowRmConfigStruct=new CarOrderRowRmConfigStruct();
				   carOrderRowRmConfigStruct.setRmDynamicData(rmConfigModel.getDynamicFieldsData());
				  carOrderRow.setCarOrderRowRmConfigStruct(carOrderRowRmConfigStruct);
				   }
				CarOrderRow carOrderRowNew= carTravelRequestDao.insertCarOrderRow(carOrderRow);
				if(carOrderRowNew!=null){
					carOrderRowNew.setInvoiceNo(RandomConfigurationNumber.generateCarInvoiceNumber(carOrderRowNew.getId()));
					Long orderIdTemp = carOrderRowNew.getId()+1000;
					orderId =RandomConfigurationNumber.generateCarInvoiceNumber(orderIdTemp);
					carOrderRowNew.setOrderId(orderId);
					carOrderRowNew=new CarOrderDao().updateCarOrderRowDetail(carOrderRowNew);
				}

				CompanyDAO companyDAO = new CompanyDAO();
				Map<String, BigDecimal> markups =  new LinkedHashMap<>();
				if(carOrderRowNew!=null)
				{
					if(sessionCompany.getCompanyRole().isSuperUser())
					{
						//						markups.put(String.valueOf(sessionCompany.getCompanyid()), carOrderRowNew.getMarkUp());
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					}
					else
					{
						Company companyParent= companyDAO.getParentCompany(sessionCompany);
						markups.put(String.valueOf(companyParent.getCompanyid()), carOrderRowNew.getMarkUp());
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					}
				}

				List<Company> companyListBottomToTop= new LinkedList<>();
				List<User> userListBottomToTop= new LinkedList<>();
				Map<Integer, CutandPayModel> cutAndPayUserMap = new LinkedHashMap<>();
				FlightOrderDao  flightOrderDao = new FlightOrderDao();
				CarOrderDao  carOrderDao = new CarOrderDao();
				BigDecimal finalPriceAfterTax = finalPrice.add(serviceOrGstTax!= null ?serviceOrGstTax : new BigDecimal(0));

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
												FlightOrderDao.deductUserWallet(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.CAR_REMARKS.getMessage(),orderId,carOrderRowNew.getInvoiceNo());
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
															flightOrderDao.creditUserWalletAmountForBookingFailed(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.CAR_FAILEDREMARKS.getMessage(),orderId,carOrderRowNew.getInvoiceNo());
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
					carOrderRowNew.setPaymentStatus("Failed");
					carOrderRowNew=carOrderDao.updateCarOrderRowDetailPaymentStatus(carOrderRowNew);
					addActionMessage(CommonBookingStatusEnum.WALLET_ERROR.getMessage());
					return ERROR;
				}else{
					carOrderRowNew.setPaymentStatus("Success");
					carOrderRowNew=carOrderDao.updateCarOrderRowDetailPaymentStatus(carOrderRowNew);
				}


				if(carOrderRowNew!=null)
				{
					CarTravelRequestQuotation carTravelRequestQuotationNew=carTravelRequestDao.updateCarTravelRequestQuotationWithOrderId(carOrderRowNew,carTravelRequestQuotation.getId());
					if(carTravelRequestQuotationNew!=null){
						HotelFlightBookingStatus hotelFlightBookingStatus=new HotelFlightBookingStatus();
						if(carTravelRequestQuotationNew.getHotetFlightBookingStatus()!=null && carTravelRequestQuotationNew.getHotetFlightBookingStatus().getId()!=null)
							hotelFlightBookingStatus.setId(carTravelRequestQuotationNew.getHotetFlightBookingStatus().getId());
						hotelFlightBookingStatus.setBooked(TravelRequestStatusEnum.BOOKED.getValue());
						carTravelRequestDao.updateHotelOrFlightQuotationBookingStatus(hotelFlightBookingStatus);
						new CarTravelRequestDao().updateCarTravelRequestQuotationHide(carTravelRequestQuotationNew.getCarTravelRequest().getId());
						new CompanyDAO().insertEmail(carOrderRowNew.getOrderId(), 0, Email.EMAIL_TYPE_CAR_INVOICE);
						addActionMessage("Successfully Car Booking Done.");
					}
					else{
						addActionMessage("We found somr error while booking.");
						return ERROR;
					}
				}
				 
				if(tripId!=null)
				{
					tripRequest.setId(tripId);
					tripRequest.setCarTravelRequest(carTravelRequestnew);
					tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					tripRequest=tripRequestDao.updateTripRequestById(tripRequest);
					if(tripRequest!=null && tripRequest.getId()>0){
						addActionMessage("Successfully created");
					}
				}
				else{
					tripRequest.setCarTravelRequest(carTravelRequestnew);
					tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
					//tripRequest.setTripId(RandomConfigurationNumber.generateTripId(carTravelRequestnew.getId()));
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					tripRequest=tripRequestDao.insertTripRequest(tripRequest);
					if(tripRequest!=null && tripRequest.getId()>0){
						tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
						if(tripRequestDao.updateTripRequestNumber(tripRequest))
							addActionMessage("Successfully created");
						else
							addActionMessage("Something went wrong.Please wait.");	
					}
				}
				if(carOrderCustomerList!=null && carOrderCustomerList.size()>0){
					for(CarOrderCustomer carOrderCustomerNew:carOrderCustomerList){
						carOrderCustomerNew.setEmail(sessionUser.getEmail());
						carOrderCustomerNew.setAge("");
						carOrderCustomerNew.setCreatedAt(new Timestamp(new Date().getTime()));
						carOrderCustomerNew.setGender(carOrderCustomerNew.getTitle().equalsIgnoreCase("Mr")
								|| carOrderCustomerNew.getTitle().equalsIgnoreCase("Master") ? "M" : "F");
						carOrderCustomerNew.setCarOrderRow(carOrderRowNew);
						RmConfigTripDetailsModel configTripDetailsModel=carOrderCustomerNew.getRmConfigTripDetailsModel();
						 if(configTripDetailsModel!=null){
							configTripDetailsModel.setOrderId(carOrderRowNew.getOrderId());
							configTripDetailsModel.setOrdertype("Car");
							carOrderCustomerNew.setRmConfigTripDetailsModel(configTripDetailsModel);
						 } 
						carOrderCustomerNew.setPaxId(RandomConfigurationNumber.generateRandomPaxID());
						carTravelRequestDao.insertCarOrderCustomer(carOrderCustomerNew);
					}
					new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(carTravelRequestnew.getId()),"car book Request created","Car book Request notification",NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),false,false,false,true,false,false);
				}
 
			} 
			else{
				addActionMessage("We found somr error while booking.");
				return ERROR;
			}
		}else{
			addActionMessage("Something went wrong.Please wait.");
			return ERROR;
		}
		return SUCCESS;
	}


	public String getCarTravelRequestList(){
		User sessionUser=(User)sessionMap.get("User");
		List<CarTravelRequest> list=carTravelRequestDao.loadCarTravelRequestList(sessionUser);
		if(list!=null && list.size()>0){
			carTravelRequestlist=list;
		}
		return SUCCESS;
	}

	public CarTravelRequest getCarQuotationRow() {
		return carTravelRequest;
	}

	public void setCarQuotationRow(CarTravelRequest hotelQuotationRow) {
		this.carTravelRequest = hotelQuotationRow;
	}

	public String getCarTravelRequestDetails()
	{
		carTravelRequest = carTravelRequestDao.getCarTravelRequestDetails(carTravelRequest.getId());
		countryList=new CountryDao().getCountryList();
		carTravelRequestQuotationlist = carTravelRequestDao.getCarRequestTravelQuotationBookedList(carTravelRequest.getId());
		return SUCCESS;
	}

	public String updateCarTravelRequestDetails(){
		User sessionUser=(User)sessionMap.get("User");
		//HotetTravelRequest hotelTravelRequest=hotelOrderRowFineSheetDao.getCarTravelRequestDetails(hotelQuotationRow.getId());
		carTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		carTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		CarTravelRequest hotetTravelRequestNew= carTravelRequestDao.updateCarTravelRequestDetails(carTravelRequest);
		if(hotetTravelRequestNew!=null){
			addActionMessage("Successfully Updated.");
			new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(hotetTravelRequestNew.getId()),"Hotal book Request updated","Hotal book Request notification",NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),false,false,false,true,false,false);

			return SUCCESS;
		}
		else{
			addActionError("We found some error.Please try again.");
			return ERROR; 
		}
	}

	public String generateCarInvoice(){
		carOrderRow=carTravelRequestDao.getCarOrderRowDetailsById(orderId);
		setCarOrderRow(carOrderRow);
		return SUCCESS;
	}

	public static FlightGstTax getFlightGSTTax(CarOrderRowGstTax carOrderRowGstTax,Company childCompany,Company parentCompany, CarOrderRow carOrderRow){

		CompanyEntity  companyEntity=new CompanyEntity();
		String entityState=null;
		if(carOrderRow.getCompanyEntityId()!=null) 
			companyEntity = new CompanyDAO().companyEntityProfile(carOrderRow.getCompanyEntityId().intValue());
		if(companyEntity!=null && companyEntity.getState()!=null) 
			entityState=companyEntity.getState(); 


		BigDecimal managementFee  =carOrderRowGstTax.getManagementFee()!=null?carOrderRowGstTax.getManagementFee():new BigDecimal("0.0");
		BigDecimal CGSTAmount = new BigDecimal("0.0");
		BigDecimal CGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTAmount = new BigDecimal("0.0");
		//BigDecimal IGST = new BigDecimal("0.0");
		//BigDecimal UGST = new BigDecimal("0.0");
		BigDecimal totalGstAmount = new BigDecimal("0.0");
		BigDecimal totalGstPer = new BigDecimal("0.0");
		boolean isParentCompanyUT=IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());

		CGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getCGST());
		CGSTPer  = carOrderRowGstTax.getCGST();


		if(entityState!=null){ 
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(entityState.trim());

			if(isParentCompanyUT && isBillingCompanyUT){
				CommonGSTPer =carOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getUGST());
				carOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				carOrderRowGstTax.setSGST(new BigDecimal("0.0"));

			}
			if(!isParentCompanyUT && !isBillingCompanyUT){
				if(entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =carOrderRowGstTax.getSGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getSGST());    
					carOrderRowGstTax.setIGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				}
				if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =carOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getIGST());    
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					carOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(entityState.trim())){
				CommonGSTPer =carOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getUGST());
				carOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				carOrderRowGstTax.setSGST(new BigDecimal("0.0"));
			}

			if(isParentCompanyUT && !isBillingCompanyUT){
				if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(entityState.trim())){
					CommonGSTPer =carOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getIGST());    
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					carOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setCGST(CGSTPer);
				}
			}
		}else{
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim());

			if(isParentCompanyUT && isBillingCompanyUT){
				CommonGSTPer =carOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getUGST());
				carOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				carOrderRowGstTax.setSGST(new BigDecimal("0.0"));

			}
			if(!isParentCompanyUT && !isBillingCompanyUT){
				if(childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =carOrderRowGstTax.getSGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getSGST());    
					carOrderRowGstTax.setIGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				}
				if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =carOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getIGST());    
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					carOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
				CommonGSTPer =carOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getUGST());
				carOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				carOrderRowGstTax.setSGST(new BigDecimal("0.0"));
			}

			if(isParentCompanyUT && !isBillingCompanyUT){
				if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
					CommonGSTPer =carOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(carOrderRowGstTax.getIGST());   
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					carOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					carOrderRowGstTax.setCGST(CGSTPer);
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
		sessionMap=(SessionMap<String, Object>) map;
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
	public CarTravelRequest getModel() {
		return carTravelRequest;
	}

	public CarTravelRequest getCarTravelRequest() {
		return carTravelRequest;
	}

	public void setCarTravelRequest(CarTravelRequest carTravelRequest) {
		this.carTravelRequest = carTravelRequest;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<CarTravelRequest> getCarTravelRequestlist() {
		return carTravelRequestlist;
	}

	public void setCarTravelRequestlist(List<CarTravelRequest> carTravelRequestlist) {
		this.carTravelRequestlist = carTravelRequestlist;
	}

	public List<CarTravelRequestQuotation> getCarTravelRequestQuotationlist() {
		return carTravelRequestQuotationlist;
	}

	public void setCarTravelRequestQuotationlist(
			List<CarTravelRequestQuotation> carTravelRequestQuotationlist) {
		this.carTravelRequestQuotationlist = carTravelRequestQuotationlist;
	}

	public Long getCarQuotationRequestId() {
		return carQuotationRequestId;
	}

	public void setCarQuotationRequestId(Long carQuotationRequestId) {
		this.carQuotationRequestId = carQuotationRequestId;
	}

	public CarTravelRequestQuotation getCarTravelRequestQuotation() {
		return carTravelRequestQuotation;
	}

	public void setCarTravelRequestQuotation(CarTravelRequestQuotation carTravelRequestQuotation) {
		this.carTravelRequestQuotation = carTravelRequestQuotation;
	}

	public CarOrderRow getCarOrderRow() {
		return carOrderRow;
	}

	public void setCarOrderRow(CarOrderRow carOrderRow) {
		this.carOrderRow = carOrderRow;
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
	public CarServiceTaxConfig getCarServiceTaxConfig() {
		return carServiceTaxConfig;
	}

	public void setCarServiceTaxConfig(CarServiceTaxConfig carServiceTaxConfig) {
		this.carServiceTaxConfig = carServiceTaxConfig;
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


	public CarGstTaxConfig getCarGstTaxConfig() {
		return carGstTaxConfig;
	}


	public void setCarGstTaxConfig(CarGstTaxConfig carGstTaxConfig) {
		this.carGstTaxConfig = carGstTaxConfig;
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


	public BigDecimal getTotalGstTaxPer() {
		return totalGstTaxPer;
	}


	public void setTotalGstTaxPer(BigDecimal totalGstTaxPer) {
		this.totalGstTaxPer = totalGstTaxPer;
	}


	public List<CarOrderCustomer> getCarOrderCustomerList() {
		return carOrderCustomerList;
	}


	public void setCarOrderCustomerList(List<CarOrderCustomer> carOrderCustomerList) {
		this.carOrderCustomerList = carOrderCustomerList;
	}


}
