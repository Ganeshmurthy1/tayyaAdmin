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
import com.admin.common.config.model.RailServiceTaxConfig;
import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.TrainTravelRequest;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.common.util.CommonUtilSession;
import com.admin.enums.utility.CommonBookingStatusEnum;
import com.admin.enums.utility.IndianUnionTerritories;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.DAO.TrainOrderDao;
import com.lintas.admin.DAO.UserDAO;
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
import com.lintas.admin.train.model.TrainGstTaxConfig;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.train.model.TrainOrderRowGstTax;
import com.lintas.admin.train.model.TrainOrderRowServiceTax;
import com.lintas.admin.vo.CutandPayModel;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.admin.orderrow.rm.structure.TrainOrderRowRmConfigStruct;
import com.tayyarah.gst.model.FlightGstTax;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;
import com.tayyarah.train.model.TrainOrderCustomer;

public class TrainTravelRequestAction extends ActionSupport implements ModelDriven<TrainTravelRequest>,SessionAware{
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainTravelRequestAction.class);
	private static final long serialVersionUID = 1L;
	TrainTravelRequest trainTravelRequest=new TrainTravelRequest();
	TrainTravelRequestQuotation trainTravelRequestQuotation =new TrainTravelRequestQuotation();
	SessionMap<String, Object> sessionMap; 

	TrainTravelRequestDao trainTravelRequestDao=new TrainTravelRequestDao();
	private List<Country> countryList=null;
	TrainOrderRow trainOrderRow=new TrainOrderRow();
	private List<TrainTravelRequest> trainTravelRequestlist=null;
	private List<TrainOrderCustomer> trainOrderCustomerList=new ArrayList<>();
	private List<TrainTravelRequestQuotation> trainTravelRequestQuotationlist=new ArrayList<>();
	private Long trainQuotationRequestId;
	private Long tripId;
	private Long orderId;
	private Long idtosend;
	private Long detailsid;
	private String taxType;
	BigDecimal totalGstAmount = new BigDecimal("0.00");
	BigDecimal totalGstAmountTatkal = new BigDecimal("0.00");
	private WalletAmountTranferHistory payTxInfo ;
	TripRequestDao tripRequestDao=new TripRequestDao();
	RailServiceTaxConfig railServiceTaxConfig=new RailServiceTaxConfig();
	TrainGstTaxConfig trainGstTaxConfig=new TrainGstTaxConfig();
	UserDAO userDao=new UserDAO();
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao =new ApiProviderDao();
	RmConfigModel rmConfigModel=new RmConfigModel();
	List<String> fieldName = new ArrayList<String>();
	RmConfigDao rmConfigDao=new RmConfigDao();
	RmConfigTripDetailsModel configTripDetailsModel=new RmConfigTripDetailsModel();
	private UserWallet userWallet=new UserWallet();
	BigDecimal totalGstTaxPer = new BigDecimal("0.00");

	public String  editTrainTravelRequestQuotation(){
		countryList = new CountryDao().getCountryList();
		//TripRequest tripRequest=tripRequestDao.getTripRequestById(idtosend);
		trainTravelRequest=trainTravelRequestDao.getTrainQuotationRequestDetails(idtosend);
		if(trainTravelRequest!=null){
			trainTravelRequestQuotationlist=trainTravelRequest.getTrainTravelRequestQuotations();
			for (TrainTravelRequestQuotation trainTravelRequestQuotation : trainTravelRequestQuotationlist) {
				logger.info("trainTravelRequestQuotation------"+trainTravelRequestQuotation.getId());
			}
		}
		//trainTravelRequestQuotation=trainTravelRequestDao.trainRequestQuotationUpdate(trainTravelRequestQuotation);
		return SUCCESS;
	}


	public String 	updateTrainTravelRequestQuotation(){
		trainTravelRequestDao.updateTrainTravelRequestQuotation(trainTravelRequestQuotation);
		return SUCCESS;

	}

	public String  editTrainOrderRowDetails(){
		countryList = new CountryDao().getCountryList();
		trainOrderRow=trainTravelRequestDao.getTrainOrderRowDetailsById(idtosend);
		//trainOrderRow.setVfsCharges(trainOrderRow.getVfsCharges().setScale(2, BigDecimal.ROUND_UP));
		//trainOrderRow.setCourierCharges(trainOrderRow.getCourierCharges().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setSupplierPrice(trainOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setBasePrice(trainOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setApiToBaseExchangeRate(trainOrderRow.getApiToBaseExchangeRate().setScale(2,BigDecimal.ROUND_UP));
		trainOrderRow.setMarkUp(trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setManagementFee(trainOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setConvenienceFee(trainOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setServiceTax(trainOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setOtherTaxes(trainOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setBasePrice(trainOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		trainOrderRow.setTotalAmount(trainOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
		setTrainOrderRow(trainOrderRow);
		return SUCCESS;
	}

	public String  editTrainTravelRequest(){
		setTrainTravelRequest(trainTravelRequestDao.getTrainTravelRequestDetails(idtosend));
		return SUCCESS;
	}

	public String  updateTrainOrderRow(){
		trainTravelRequestDao.updateTrainOrderRow(trainOrderRow);
		return SUCCESS;

	}

	public String  updateTrainTravelRequest(){
		logger.info("idtosend in update-------"+idtosend);
		trainTravelRequestDao.updateTrainTravelRequestDetails(trainTravelRequest);
		return SUCCESS;
	}


	public String goTrainTravelRequest(){
		countryList=new CountryDao().getCountryList();
		List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
		setApiProviders(list);
		User sessionUser = (User)sessionMap.get("User");
		Company sessionCompany = (Company) sessionMap.get("Company");
		RmConfigDao rmConfigDao=new RmConfigDao();
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
		Company parentCompany = new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());

		BigDecimal CGST = new BigDecimal("0.00");
		BigDecimal SGST = new BigDecimal("0.00");
		BigDecimal UGST = new BigDecimal("0.00");
		BigDecimal IGST = new BigDecimal("0.00");
		BigDecimal CGST1 = new BigDecimal("0.00");
		BigDecimal SGST1 = new BigDecimal("0.00");
		BigDecimal UGST1 = new BigDecimal("0.00");
		BigDecimal IGST1 = new BigDecimal("0.00");

		BigDecimal managementFee = new BigDecimal("0.00");
		BigDecimal managementFeeTatkal = new BigDecimal("0.00");
		if(companyConfig!=null ){
			if(companyConfig.getTaxtype()!=null && companyConfig.getTaxtype().equalsIgnoreCase("GST")){
				boolean territiryStatus = IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());
				managementFee = companyConfig.getTrainGstTaxConfig().getManagementFee().setScale(0, RoundingMode.UP);
				managementFeeTatkal= companyConfig.getTrainGstTaxConfig().getManagementFeeTatkal().setScale(0, RoundingMode.UP);

				CGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getTrainGstTaxConfig().getCGST());
				CGST1 = managementFeeTatkal.divide(new BigDecimal("100.0")).multiply(companyConfig.getTrainGstTaxConfig().getCGST());
				totalGstTaxPer = totalGstTaxPer.add(companyConfig.getTrainGstTaxConfig().getCGST());
				if (sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					SGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getTrainGstTaxConfig().getSGST());
					SGST1 = managementFeeTatkal.divide(new BigDecimal("100.0")).multiply(companyConfig.getTrainGstTaxConfig().getSGST());
					totalGstTaxPer = totalGstTaxPer.add(companyConfig.getTrainGstTaxConfig().getSGST());
				}
				if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !territiryStatus) {
					IGST = managementFee.divide(new BigDecimal("100.0")) .multiply(companyConfig.getTrainGstTaxConfig().getIGST());
					IGST1 = managementFeeTatkal.divide(new BigDecimal("100.0")).multiply(companyConfig.getTrainGstTaxConfig().getIGST());
					CGST = new BigDecimal("0.00");
					CGST1 = new BigDecimal("0.00");
					totalGstTaxPer =companyConfig.getTrainGstTaxConfig().getIGST();

				}
				if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && territiryStatus) {
					UGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getTrainGstTaxConfig().getUGST());
					UGST1 = managementFeeTatkal.divide(new BigDecimal("100.0")).multiply(companyConfig.getTrainGstTaxConfig().getUGST());
					totalGstTaxPer = totalGstTaxPer.add(companyConfig.getTrainGstTaxConfig().getUGST());
				}

				totalGstAmount = CGST.add(SGST).add(IGST).add(UGST);
				totalGstAmountTatkal = CGST1.add(SGST1).add(IGST1).add(UGST1);


				setTaxType("GST");


				if(companyConfig.getTrainGstTaxConfig()!=null){
					trainGstTaxConfig.setConvenienceFee(companyConfig.getTrainGstTaxConfig().getConvenienceFee().setScale(0,RoundingMode.HALF_UP));
					trainGstTaxConfig.setManagementFee(companyConfig.getTrainGstTaxConfig().getManagementFee().setScale(0,RoundingMode.HALF_UP));
					trainGstTaxConfig.setManagementFeeTatkal(companyConfig.getTrainGstTaxConfig().getManagementFeeTatkal().setScale(0,RoundingMode.HALF_UP));
				}
			}
			else{
				setTaxType("Service");
				if(companyConfig.getRailServiceTaxConfig()!=null){
					railServiceTaxConfig.setBasicTax(companyConfig.getRailServiceTaxConfig().getBasicTax().setScale(2,RoundingMode.HALF_UP)); 
					railServiceTaxConfig.setKrishiKalyanCess(companyConfig.getRailServiceTaxConfig().getKrishiKalyanCess().setScale(2,RoundingMode.HALF_UP));
					railServiceTaxConfig.setSwatchBharathCess(companyConfig.getRailServiceTaxConfig().getSwatchBharathCess().setScale(2,RoundingMode.HALF_UP));
					railServiceTaxConfig.setTotalTax(companyConfig.getRailServiceTaxConfig().getTotalTax().setScale(2,RoundingMode.HALF_UP));
					railServiceTaxConfig.setConvenienceFee(companyConfig.getRailServiceTaxConfig().getConvenienceFee().setScale(0,RoundingMode.HALF_UP));
					railServiceTaxConfig.setManagementFee(companyConfig.getRailServiceTaxConfig().getManagementFee().setScale(0,RoundingMode.HALF_UP));
					railServiceTaxConfig.setManagementFeeTatkal(companyConfig.getRailServiceTaxConfig().getManagementFeeTatkal()!=null?companyConfig.getRailServiceTaxConfig().getManagementFeeTatkal().setScale(0,RoundingMode.HALF_UP):new BigDecimal(0));
				}
			}
		}

		setTripId(tripId);
		userWallet=new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId());
		return SUCCESS;
	}

	public String  createTrainTravelRequest(){
		TripRequest tripRequest =new TripRequest();
		User sessionUser=(User)sessionMap.get("User");
		User userNew=userDao.getUserByUserId(sessionUser.getId());
		Company sessionCompany=(Company)sessionMap.get("Company");
		String orderId = trainOrderRow.getConfirmationNumber();

		BigDecimal finalPrice = trainOrderRow.getTotalAmount();
		//trainOrderRow.setTotalAmount(finalPriceAfterTax);
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

		trainTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		trainTravelRequestQuotation.setTravelDate(trainTravelRequestQuotation.getTraveldatetemp());
		trainTravelRequestQuotationlist.add(trainTravelRequestQuotation);
		TrainTravelRequest trainTravelRequest=new TrainTravelRequest();
		trainTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
		trainTravelRequest.setCompanyId(sessionUser.getCompanyid());
		trainTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
				? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
		trainTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		//NEWLY WRITTEN BY RAHAM

		TrainOrderCustomer trainOrderCustomer=null;
		if(trainOrderCustomerList!=null && trainOrderCustomerList.size()>0) 
			trainOrderCustomer=trainOrderCustomerList.get(0);
		if(trainOrderCustomer!=null){
			trainTravelRequest.setFirstName(trainOrderCustomer.getFirstName());
			trainTravelRequest.setLastName(trainOrderCustomer.getLastName());
			trainTravelRequest.setTitle(trainOrderCustomer.getTitle());
			if(trainOrderCustomer.getRmConfigTripDetailsModel()!=null && trainOrderCustomer.getRmConfigTripDetailsModel().getTrNumber()!=null){
				trainTravelRequest.setTrNo(trainOrderCustomer.getRmConfigTripDetailsModel().getTrNumber());
			}
			
			
		}

		TrainTravelRequest trainTravelRequestnew=trainTravelRequestDao.inserttrainTravelRequestnew(trainTravelRequest);
		if(trainTravelRequestnew!=null && trainTravelRequestnew.getId()>0){
			boolean isInserted=trainTravelRequestDao.insertTrainQuotationList(trainTravelRequestQuotationlist, trainTravelRequestnew);
			if(isInserted){
				if(trainTravelRequestQuotationlist!=null && trainTravelRequestQuotationlist.size()>0)
				{
					trainOrderRow.setBookingCurrency(trainTravelRequestQuotationlist.get(0).getCurrency());
				}
				OrderCustomer orderCustomer = new OrderCustomer();
				orderCustomer.setFirstName(trainTravelRequest.getFirstName());
				orderCustomer.setLastName(trainTravelRequest.getLastName());
				orderCustomer.setTitle(trainTravelRequest.getTitle());
				orderCustomer.setEmail(sessionUser.getEmail());
				orderCustomer.setCompanyId(sessionCompany.getCompanyid());
				orderCustomer.setBookingType(CommonBookingStatusEnum.TRAIN.getMessage());
				orderCustomer.setConfigId(newCompanyConfig.getConfig_id());
				//ADDED  BY BASHA
				orderCustomer.setCreatedByUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				trainOrderRow.setTravelDate(trainTravelRequestQuotation.getTravelDate());
				trainOrderRow.setRemarks(trainTravelRequestQuotation.getRemarks());
				trainOrderRow.setEmpNmae(trainTravelRequest.getTitle() + "  "+trainTravelRequest.getFirstName()+ "  "+ trainTravelRequest.getLastName());
				TrainOrderRowServiceTax trainOrderRowServiceTax=null;
				TrainOrderRowGstTax trainOrderRowGstTax=null;
				FlightGstTax flightGstTax=null;

				Company parentCompany = new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());

				if(newCompanyConfig!=null && newCompanyConfig.getCompanyConfigType().isB2E()){
					if(newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")){
						if(newCompanyConfig.getTrainGstTaxConfig()!=null){
							trainOrderRowGstTax = new TrainOrderRowGstTax();
							trainOrderRowGstTax.setConvenienceFee(trainOrderRow.getConvenienceFee());
							trainOrderRowGstTax.setCreatedAt(trainOrderRow.getCreatedAt());
							trainOrderRowGstTax.setManagementFee(trainOrderRow.getManagementFee());
							trainOrderRowGstTax.setManagementFeeTatkal(trainOrderRow.getManagementFeeTatkal());
							trainOrderRowGstTax.setApplicableFare(newCompanyConfig.getRailServiceTaxConfig().getApplicableFare());
							trainOrderRowGstTax.setIGST(newCompanyConfig.getTrainGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
							trainOrderRowGstTax.setSGST(newCompanyConfig.getTrainGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
							trainOrderRowGstTax.setCGST(newCompanyConfig.getTrainGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
							trainOrderRowGstTax.setUGST(newCompanyConfig.getTrainGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));

							flightGstTax=getFlightGSTTax(trainOrderRowGstTax, sessionCompany, parentCompany,trainOrderRow);
							trainOrderRowGstTax.setTotalGst(flightGstTax.getTotalTax());
							trainOrderRow.setTotalGstTax(flightGstTax.getTotalGstAmount());
							trainOrderRow.setTrainOrderRowGstTax(trainOrderRowGstTax);
							serviceOrGstTax=flightGstTax.getTotalGstAmount();
						}
					}
					else{
						if(newCompanyConfig.getRailServiceTaxConfig()!=null){
							trainOrderRowServiceTax = new TrainOrderRowServiceTax();
							trainOrderRowServiceTax.setConvenienceFee(trainOrderRow.getConvenienceFee());
							trainOrderRowServiceTax.setCreatedAt(trainOrderRow.getCreatedAt());
							trainOrderRowServiceTax.setManagementFee(trainOrderRow.getManagementFee());
							trainOrderRowServiceTax.setManagementFeeTatkal(trainOrderRow.getManagementFeeTatkal());
							trainOrderRowServiceTax.setApplicableFare(newCompanyConfig.getRailServiceTaxConfig().getApplicableFare());
							trainOrderRowServiceTax.setBasicTax(newCompanyConfig.getRailServiceTaxConfig().getBasicTax());
							trainOrderRowServiceTax.setKrishiKalyanCess(newCompanyConfig.getRailServiceTaxConfig().getKrishiKalyanCess());
							trainOrderRowServiceTax.setSwatchBharathCess(newCompanyConfig.getRailServiceTaxConfig().getSwatchBharathCess());
							trainOrderRowServiceTax.setTotalTax(newCompanyConfig.getRailServiceTaxConfig().getTotalTax());
							trainOrderRow.setTrainOrderRowServiceTax(trainOrderRowServiceTax);
							serviceOrGstTax= trainOrderRow.getServiceTax();
						}
					}
				}

				if(flightGstTax==null) 
					trainOrderRow.setTotalGstTax(new BigDecimal("0.00"));
				if(trainOrderRow.getServiceTax()==null)
					trainOrderRow.setServiceTax(new BigDecimal("0.00"));

				trainOrderRow.setOrderCustomer(orderCustomer);
				trainOrderRow.setTaxes(new BigDecimal("0.00"));
				trainOrderRow.setOrderId(trainOrderRow.getConfirmationNumber());
				trainOrderRow.setCreatedAt(new Timestamp(new Date().getTime()));
				trainOrderRow.setApiComments("ok");
				trainOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
				trainOrderRow.setCompanyId(String.valueOf(sessionUser.getCompanyid()));
				trainOrderRow.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId()));
				trainOrderRow.setConfigId(String.valueOf(newCompanyConfig.getConfig_id()));
				trainOrderRow.setCreatedBy(sessionUser.getUsername());
				trainOrderRow.setUpdatedBy(sessionUser.getUsername());
				trainOrderRow.setProcessingFees(new BigDecimal("0.00"));
				trainOrderRow.setStatusAction("Confirmed");
				trainOrderRow.setPaymentStatus("Success");
				trainOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
				trainOrderRow.setBaseToBookingExchangeRate(new BigDecimal(1));
				trainOrderRow.setTicketType(trainOrderRow.getTicketType());
				trainOrderRow.setBookingMode(CommonBookingStatusEnum.BOOKING_MODE_OFFLINE.getMessage());
				trainOrderRow.setBookingDate(DateConversion.StringToDate(trainOrderRow.getTrainBookingDate()));
				RmConfigModel  rmConfigModel=rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid());
				   if(rmConfigModel!=null){
				  TrainOrderRowRmConfigStruct trainOrderRowRmConfigStruct=new TrainOrderRowRmConfigStruct();
				   trainOrderRowRmConfigStruct.setRmDynamicData(rmConfigModel.getDynamicFieldsData());
				   trainOrderRow.setTrainOrderRowRmConfigStruct(trainOrderRowRmConfigStruct);
				   }
				TrainOrderRow trainOrderRowNew= trainTravelRequestDao.insertTrainOrderRow(trainOrderRow);
				if(trainOrderRowNew!=null){
					trainOrderRowNew.setInvoiceNo(RandomConfigurationNumber.generateTrainInvoiceNumber(trainOrderRowNew.getId()));
					Long orderIdTemp = trainOrderRowNew.getId()+1000;
					orderId =RandomConfigurationNumber.generateTrainInvoiceNumber(orderIdTemp);
					trainOrderRowNew.setOrderId(orderId);
					trainOrderRowNew=new TrainOrderDao().updateTrainOrderRowDetail(trainOrderRowNew);
				}

				CompanyDAO companyDAO = new CompanyDAO();
				Map<String, BigDecimal> markups =  new LinkedHashMap<>();
				if(trainOrderRowNew!=null)
				{
					if(sessionCompany.getCompanyRole().isSuperUser())
					{
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					}
					else
					{
						Company companyParent= companyDAO.getParentCompany(sessionCompany);
						markups.put(String.valueOf(companyParent.getCompanyid()), trainOrderRowNew.getMarkUp());
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					}
				}

				List<Company> companyListBottomToTop= new LinkedList<>();
				List<User> userListBottomToTop= new LinkedList<>();
				Map<Integer, CutandPayModel> cutAndPayUserMap = new LinkedHashMap<>();
				FlightOrderDao  flightOrderDao = new FlightOrderDao();
				TrainOrderDao  trainOrderDao = new TrainOrderDao();

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
												FlightOrderDao.deductUserWallet(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.TRAIN_REMARKS.getMessage(),orderId,trainOrderRowNew.getInvoiceNo());
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
															flightOrderDao.creditUserWalletAmountForBookingFailed(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.TRAIN_FAILEDREMARKS.getMessage(),orderId,trainOrderRowNew.getInvoiceNo());
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
					trainOrderRowNew.setPaymentStatus("Failed");
					trainOrderRowNew=trainOrderDao.updateTrainOrderRowDetailPaymentStatus(trainOrderRowNew);
					addActionMessage(CommonBookingStatusEnum.WALLET_ERROR.getMessage());
					return ERROR;
				}else{
					trainOrderRowNew.setPaymentStatus("Success");
					trainOrderRowNew=trainOrderDao.updateTrainOrderRowDetailPaymentStatus(trainOrderRowNew);
				}

				if(trainOrderRowNew!=null)
				{
					TrainTravelRequestQuotation trainTravelRequestQuotationNew=trainTravelRequestDao.updateTrainTravelRequestQuotationWithOrderId(trainOrderRowNew,trainTravelRequestQuotation.getId());
					if(trainTravelRequestQuotationNew!=null){
						HotelFlightBookingStatus hotelFlightBookingStatus=new HotelFlightBookingStatus();
						if(trainTravelRequestQuotationNew.getHotetFlightBookingStatus()!=null && trainTravelRequestQuotationNew.getHotetFlightBookingStatus().getId()!=null)
							hotelFlightBookingStatus.setId(trainTravelRequestQuotationNew.getHotetFlightBookingStatus().getId());
						hotelFlightBookingStatus.setBooked(TravelRequestStatusEnum.BOOKED.getValue());
						trainTravelRequestDao.updateHotelOrFlightQuotationBookingStatus(hotelFlightBookingStatus);
						new TrainTravelRequestDao().updateTrainTravelRequestQuotationHide(trainTravelRequestQuotationNew.getTrainTravelRequest().getId());
						new CompanyDAO().insertEmail(trainOrderRowNew.getOrderId(), 0, Email.EMAIL_TYPE_TRAIN_INVOICE);
						addActionMessage("Successfully Train Booking Done.");
					}
					else{
						addActionMessage("We found somr error while booking.");
						return ERROR;
					}
				}
				if(tripId!=null)
				{

					tripRequest.setId(tripId);
					tripRequest.setTrainTravelRequest(trainTravelRequestnew);
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
					tripRequest=tripRequestDao.updateTripRequestById(tripRequest);
					if(tripRequest!=null && tripRequest.getId()>0){
						addActionMessage("Successfully created");
					}
				}
				else{
					tripRequest.setTrainTravelRequest(trainTravelRequestnew);
					tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
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
				if(trainOrderCustomerList!=null && trainOrderCustomerList.size()>0){
					for(TrainOrderCustomer trainOrderCustomerNew:trainOrderCustomerList){
						trainOrderCustomerNew.setEmail(sessionUser.getEmail());
						trainOrderCustomerNew.setAge("");
						trainOrderCustomerNew.setCreatedAt(new Timestamp(new Date().getTime()));
						trainOrderCustomerNew.setGender(trainOrderCustomerNew.getTitle().equalsIgnoreCase("Mr")
								|| trainOrderCustomerNew.getTitle().equalsIgnoreCase("Master") ? "M" : "F");
						trainOrderCustomerNew.setTrainOrderRow(trainOrderRowNew);
						BigDecimal seatPrice=trainOrderCustomerNew.getBaseAmount().add(trainOrderCustomerNew.getMarkUp()).add(trainOrderCustomerNew.getTax());
						trainOrderCustomerNew.setSeatPrice(seatPrice);
						RmConfigTripDetailsModel configTripDetailsModel=trainOrderCustomerNew.getRmConfigTripDetailsModel();
						if(configTripDetailsModel!=null){
							configTripDetailsModel.setOrderId(trainOrderRowNew.getOrderId());
							configTripDetailsModel.setOrdertype("Train");
							trainOrderCustomerNew.setRmConfigTripDetailsModel(configTripDetailsModel);
						}
						trainOrderCustomerNew.setPaxId(RandomConfigurationNumber.generateRandomPaxID());
						trainTravelRequestDao.insertTrainOrderCustomer(trainOrderCustomerNew);
					}
					new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(trainTravelRequestnew.getId()),"train book Request created","Train book Request notification",NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),false,false,false,true,false,false);
				}

			} 
			else{
				addActionMessage("We found somr error while booking.");
				return ERROR;
			}
		}
		else
		{
			addActionMessage("Something went wrong.Please wait.");
			return ERROR;
		}
		return SUCCESS;
	}


	public String getTrainTravelRequestList(){
		User sessionUser=(User)sessionMap.get("User");
		List<TrainTravelRequest> list=trainTravelRequestDao.loadTrainTravelRequestList(sessionUser);
		if(list!=null && list.size()>0){
			trainTravelRequestlist=list;
		}
		return SUCCESS;
	}


	public String getTrainOfflineInvoice(){
		TrainOrderRow trainOrderRow=trainTravelRequestDao.getTrainOrderRowDetailsById(orderId);
		if(trainOrderRow!=null)
			setTrainOrderRow(trainOrderRow);

		return SUCCESS;
	}

	public static FlightGstTax getFlightGSTTax(TrainOrderRowGstTax trainOrderRowGstTax,Company childCompany,Company parentCompany,TrainOrderRow trainOrderRow){
		CompanyEntity  companyEntity=new CompanyEntity();
		String entityState=null;
		if(trainOrderRow.getCompanyEntityId()!=null) 
			companyEntity = new CompanyDAO().companyEntityProfile(trainOrderRow.getCompanyEntityId().intValue());
		if(companyEntity!=null && companyEntity.getState()!=null) 
			entityState=companyEntity.getState(); 

		BigDecimal managementFee =new BigDecimal("0.0");
		String bookingType= trainOrderRow.getTicketType();
		if(bookingType.equalsIgnoreCase("Tatkal")) 
			managementFee=trainOrderRowGstTax.getManagementFeeTatkal()!=null?trainOrderRowGstTax.getManagementFeeTatkal():new BigDecimal("0.0");
			if(bookingType.equalsIgnoreCase("Normal")) 
				managementFee=trainOrderRowGstTax.getManagementFee()!=null?trainOrderRowGstTax.getManagementFee():new BigDecimal("0.0");
				BigDecimal CGSTAmount = new BigDecimal("0.0");
				BigDecimal CGSTPer = new BigDecimal("0.0");
				BigDecimal CommonGSTPer = new BigDecimal("0.0");
				BigDecimal CommonGSTAmount = new BigDecimal("0.0");
				//BigDecimal IGST = new BigDecimal("0.0");
				//BigDecimal UGST = new BigDecimal("0.0");
				BigDecimal totalGstAmount = new BigDecimal("0.0");
				BigDecimal totalGstPer = new BigDecimal("0.0");
				boolean isParentCompanyUT=IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());

				CGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getCGST());
				CGSTPer  = trainOrderRowGstTax.getCGST();

				if(entityState!=null){ 
					boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(entityState.trim());
					if(isParentCompanyUT && isBillingCompanyUT){
						CommonGSTPer =trainOrderRowGstTax.getUGST();
						CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getUGST());
						trainOrderRowGstTax.setIGST(new BigDecimal("0.0"));
						trainOrderRowGstTax.setSGST(new BigDecimal("0.0"));

					}
					if(!isParentCompanyUT && !isBillingCompanyUT){
						if(entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
							CommonGSTPer =trainOrderRowGstTax.getSGST();
							CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getSGST());    
							trainOrderRowGstTax.setIGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setUGST(new BigDecimal("0.0"));
						}
						if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
							CommonGSTPer =trainOrderRowGstTax.getIGST();
							CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getIGST());  
							CGSTPer = new BigDecimal(0);
							CGSTAmount = new BigDecimal(0);
							trainOrderRowGstTax.setSGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setUGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setCGST(CGSTPer);
						}
					}

					if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(entityState.trim())){
						CommonGSTPer =trainOrderRowGstTax.getUGST();
						CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getUGST());
						trainOrderRowGstTax.setIGST(new BigDecimal("0.0"));
						trainOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					}

					if(isParentCompanyUT && !isBillingCompanyUT){
						if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(entityState.trim())){
							CommonGSTPer =trainOrderRowGstTax.getIGST();
							CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getIGST());   
							CGSTPer = new BigDecimal(0);
							CGSTAmount = new BigDecimal(0);
							trainOrderRowGstTax.setSGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setUGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setCGST(CGSTPer);
						}
					}
				}else{
					boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim());
					if(isParentCompanyUT && isBillingCompanyUT){
						CommonGSTPer =trainOrderRowGstTax.getUGST();
						CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getUGST());
						trainOrderRowGstTax.setIGST(new BigDecimal("0.0"));
						trainOrderRowGstTax.setSGST(new BigDecimal("0.0"));

					}
					if(!isParentCompanyUT && !isBillingCompanyUT){
						if(childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
							CommonGSTPer =trainOrderRowGstTax.getSGST();
							CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getSGST());    
							trainOrderRowGstTax.setIGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setUGST(new BigDecimal("0.0"));
						}
						if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
							CommonGSTPer =trainOrderRowGstTax.getIGST();
							CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getIGST());   
							CGSTPer = new BigDecimal(0);
							CGSTAmount = new BigDecimal(0);
							trainOrderRowGstTax.setSGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setUGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setCGST(CGSTPer);
						}
					}

					if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
						CommonGSTPer =trainOrderRowGstTax.getUGST();
						CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getUGST());
						trainOrderRowGstTax.setIGST(new BigDecimal("0.0"));
						trainOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					}

					if(isParentCompanyUT && !isBillingCompanyUT){
						if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
							CommonGSTPer =trainOrderRowGstTax.getIGST();
							CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(trainOrderRowGstTax.getIGST());  
							CGSTPer = new BigDecimal(0);
							CGSTAmount = new BigDecimal(0);
							trainOrderRowGstTax.setSGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setUGST(new BigDecimal("0.0"));
							trainOrderRowGstTax.setCGST(CGSTPer);
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

	public TrainTravelRequest getTrainQuotationRow() {
		return trainTravelRequest;
	}

	public void setTrainQuotationRow(TrainTravelRequest hotelQuotationRow) {
		this.trainTravelRequest = hotelQuotationRow;
	}

	public String getTrainTravelRequestDetails()
	{
		trainTravelRequest = trainTravelRequestDao.getTrainTravelRequestDetails(trainTravelRequest.getId());
		countryList=new CountryDao().getCountryList();
		trainTravelRequestQuotationlist = trainTravelRequestDao.getTrainRequestTravelQuotationBookedList(trainTravelRequest.getId());
		return SUCCESS;
	}

	public String updateTrainTravelRequestDetails(){
		User sessionUser=(User)sessionMap.get("User");
		//HotetTravelRequest hotelTravelRequest=hotelOrderRowFineSheetDao.getTrainTravelRequestDetails(hotelQuotationRow.getId());
		trainTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		trainTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		TrainTravelRequest hotetTravelRequestNew= trainTravelRequestDao.updateTrainTravelRequestDetails(trainTravelRequest);
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

	public String generateTrainInvoice(){
		/* long dummyId= 9;*/
		TrainOrderRow trainOrderRow=trainTravelRequestDao.getTrainOrderRowDetailsById(orderId);
		if(trainOrderRow!=null)
			setTrainOrderRow(trainOrderRow);

		return SUCCESS;
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
	public TrainTravelRequest getModel() {
		return trainTravelRequest;
	}

	public TrainTravelRequest getTrainTravelRequest() {
		return trainTravelRequest;
	}

	public void setTrainTravelRequest(TrainTravelRequest trainTravelRequest) {
		this.trainTravelRequest = trainTravelRequest;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<TrainTravelRequest> getTrainTravelRequestlist() {
		return trainTravelRequestlist;
	}

	public void setTrainTravelRequestlist(List<TrainTravelRequest> trainTravelRequestlist) {
		this.trainTravelRequestlist = trainTravelRequestlist;
	}

	public List<TrainTravelRequestQuotation> getTrainTravelRequestQuotationlist() {
		return trainTravelRequestQuotationlist;
	}

	public void setTrainTravelRequestQuotationlist(
			List<TrainTravelRequestQuotation> trainTravelRequestQuotationlist) {
		this.trainTravelRequestQuotationlist = trainTravelRequestQuotationlist;
	}

	public Long getTrainQuotationRequestId() {
		return trainQuotationRequestId;
	}

	public void setTrainQuotationRequestId(Long trainQuotationRequestId) {
		this.trainQuotationRequestId = trainQuotationRequestId;
	}

	public TrainTravelRequestQuotation getTrainTravelRequestQuotation() {
		return trainTravelRequestQuotation;
	}

	public void setTrainTravelRequestQuotation(TrainTravelRequestQuotation trainTravelRequestQuotation) {
		this.trainTravelRequestQuotation = trainTravelRequestQuotation;
	}

	public TrainOrderRow getTrainOrderRow() {
		return trainOrderRow;
	}

	public void setTrainOrderRow(TrainOrderRow trainOrderRow) {
		this.trainOrderRow = trainOrderRow;
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

	public RailServiceTaxConfig getRailServiceTaxConfig() {
		return railServiceTaxConfig;
	}

	public void setRailServiceTaxConfig(RailServiceTaxConfig railServiceTaxConfig) {
		this.railServiceTaxConfig = railServiceTaxConfig;
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


	public TrainGstTaxConfig getTrainGstTaxConfig() {
		return trainGstTaxConfig;
	}


	public void setTrainGstTaxConfig(TrainGstTaxConfig trainGstTaxConfig) {
		this.trainGstTaxConfig = trainGstTaxConfig;
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


	public BigDecimal getTotalGstAmountTatkal() {
		return totalGstAmountTatkal;
	}


	public void setTotalGstAmountTatkal(BigDecimal totalGstAmountTatkal) {
		this.totalGstAmountTatkal = totalGstAmountTatkal;
	}


	public BigDecimal getTotalGstTaxPer() {
		return totalGstTaxPer;
	}


	public void setTotalGstTaxPer(BigDecimal totalGstTaxPer) {
		this.totalGstTaxPer = totalGstTaxPer;
	}


	public List<TrainOrderCustomer> getTrainOrderCustomerList() {
		return trainOrderCustomerList;
	}


	public void setTrainOrderCustomerList(List<TrainOrderCustomer> trainOrderCustomerList) {
		this.trainOrderCustomerList = trainOrderCustomerList;
	}



}
