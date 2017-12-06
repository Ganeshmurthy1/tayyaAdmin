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
import com.admin.common.config.model.VisaServiceTaxConfig;
import com.admin.common.quotation.dao.VisaTravelRequestDao;
import com.admin.common.quotation.model.VisaTravelRequest;
import com.admin.common.quotation.model.VisaTravelRequestQuotation;
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
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.DAO.VisaOrderDao;
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
import com.lintas.admin.visa.model.VisaGstTaxConfig;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.admin.visa.model.VisaOrderRowGstTax;
import com.lintas.admin.visa.model.VisaOrderRowServiceTax;
import com.lintas.admin.vo.CutandPayModel;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.admin.orderrow.rm.structure.VisaOrderRowRmConfigStruct;
import com.tayyarah.gst.model.FlightGstTax;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;
import com.tayyarah.visa.model.VisaOrderCustomer;

public class VisaTravelRequestAction extends ActionSupport implements ModelDriven<VisaTravelRequest>, SessionAware {
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(VisaTravelRequestAction.class);
	private static final long serialVersionUID = 1L;
	VisaTravelRequest visaTravelRequest=new VisaTravelRequest();
	VisaTravelRequestQuotation visaTravelRequestQuotation =new VisaTravelRequestQuotation();
	SessionMap<String, Object> sessionMap; 
	VisaTravelRequestDao visaTravelRequestDao=new VisaTravelRequestDao();
	private List<Country> countryList=null;
	VisaOrderRowServiceTax visaOrderRowServiceTax =new VisaOrderRowServiceTax();
	VisaOrderRow visaOrderRow=new VisaOrderRow();
	private List<VisaTravelRequest> visaTravelRequestlist=null;
	private List<VisaTravelRequestQuotation> visaTravelRequestQuotationlist=new ArrayList<>();
	private List<VisaOrderCustomer> visaOrderCustomerList= new ArrayList<>();
	private Long visaQuotationRequestId;
	private Long tripId;
	private Long orderId;
	private Long id;
	private Long idForDetails;
	private Long idtosend;
	private Long detailsid;
	private String taxType;
	BigDecimal totalGstAmount = new BigDecimal("0.00");
	BigDecimal totalGST = new BigDecimal("0.00");
	TripRequestDao tripRequestDao=new TripRequestDao();
	private WalletAmountTranferHistory payTxInfo; 
	UserDAO userDao=new UserDAO();
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	RmConfigModel rmConfigModel = new RmConfigModel();
	List<String> fieldName = new ArrayList<String>();
	RmConfigDao rmConfigDao=new RmConfigDao();
	RmConfigTripDetailsModel configTripDetailsModel=new RmConfigTripDetailsModel();
	private UserWallet userWallet=new UserWallet();
	VisaServiceTaxConfig visaServiceTaxConfig=new VisaServiceTaxConfig();
	VisaGstTaxConfig visaGstTaxConfig=new VisaGstTaxConfig();
	
	public String editVisaTravelRequestQuotation() {
		countryList = new CountryDao().getCountryList();
		//TripRequest tripRequest=tripRequestDao.getTripRequestById(idtosend);
		visaTravelRequest=visaTravelRequestDao.getVisaQuotationRequestDetails(idtosend);
		if(visaTravelRequest!=null){
			visaTravelRequestQuotationlist=visaTravelRequest.getVisaTravelRequestQuotations();
			for (VisaTravelRequestQuotation visaTravelRequestQuotation : visaTravelRequestQuotationlist) {
				logger.info("visaTravelRequestQuotation------"+visaTravelRequestQuotation.getId());
			}
		}
		//visaTravelRequestQuotation=visaTravelRequestDao.visaRequestQuotationUpdate(visaTravelRequestQuotation);
		return SUCCESS;
	}

	public String 	updateVisaTravelRequestQuotation(){
		visaTravelRequestDao.updateVisaTravelRequestQuotation(visaTravelRequestQuotation);
		return SUCCESS;

	}

	public String  editVisaOrderRowDetails(){
		visaOrderRow=visaTravelRequestDao.getVisaOrderRowDetailsById(idtosend);
		visaOrderRow.setVfsCharges(visaOrderRow.getVfsCharges().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setCourierCharges(visaOrderRow.getCourierCharges().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setSupplierPrice(visaOrderRow.getSupplierPrice().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setBasePrice(visaOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setApiToBaseExchangeRate(visaOrderRow.getApiToBaseExchangeRate().setScale(2,BigDecimal.ROUND_UP));
		visaOrderRow.setMarkUp(visaOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setManagementFee(visaOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setConvenienceFee(visaOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setServiceTax(visaOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setOtherTaxes(visaOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setBasePrice(visaOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setTotalAmount(visaOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
		setVisaOrderRow(visaOrderRow);
		return SUCCESS;
	}

	public String  editVisaTravelRequest(){
		setVisaTravelRequest(visaTravelRequestDao.getVisaTravelRequestDetails(idtosend));
		return SUCCESS;
	}
	 
	public String  updateVisaOrderRow(){
		visaTravelRequestDao.updateVisaOrderRow(visaOrderRow);
		return SUCCESS;

	}

	public String  updateVisaTravelRequest(){
		visaTravelRequestDao.updateVisaTravelRequestDetails(visaTravelRequest);
		return SUCCESS;
	}

	
	public String goVisaTravelRequest(){
		countryList=new CountryDao().getCountryList();
		List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
		setApiProviders(list);
		User sessionUser = (User) sessionMap.get("User");
		Company sessionCompany = (Company) sessionMap.get("Company");
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
		if(tripId != null){
			TripRequest tripRequest = tripRequestDao.getTripRequestById(tripId);
			configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
		}
		CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());
		Company parentCompany = new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());
		
		BigDecimal CGST = new BigDecimal("0.00");
		BigDecimal CGSTPer = new BigDecimal("0.00");
		BigDecimal SGSTorIGSTorUGST = new BigDecimal("0.00");
		BigDecimal SGST = new BigDecimal("0.00");
		BigDecimal UGST = new BigDecimal("0.00");
		BigDecimal IGST = new BigDecimal("0.00");
		BigDecimal managementFee = new BigDecimal("0.00");
		if (companyConfig != null ){
			if(companyConfig.getTaxtype()!=null && companyConfig.getTaxtype().equalsIgnoreCase("GST")){
				boolean territiryStatus = IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());
				managementFee = companyConfig.getVisaGstTaxConfig().getManagementFee().setScale(0, RoundingMode.UP);
				CGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getVisaGstTaxConfig().getCGST());
				CGSTPer=companyConfig.getVisaGstTaxConfig().getCGST();
				if (sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					SGST = managementFee.divide(new BigDecimal("100.0")).multiply(companyConfig.getVisaGstTaxConfig().getSGST());
					SGSTorIGSTorUGST=companyConfig.getVisaGstTaxConfig().getSGST();
				}
				if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !territiryStatus) {
					IGST = managementFee.divide(new BigDecimal("100.0")) .multiply(companyConfig.getVisaGstTaxConfig().getIGST());
					CGST = new BigDecimal("0.00");
					SGSTorIGSTorUGST=companyConfig.getVisaGstTaxConfig().getIGST();
					CGSTPer=new BigDecimal("0.00");
				}
				if (!sessionCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && territiryStatus) {
					UGST = managementFee.divide(new BigDecimal("100.0")) .multiply(companyConfig.getVisaGstTaxConfig().getUGST());
					SGSTorIGSTorUGST=companyConfig.getVisaGstTaxConfig().getUGST();
				}

				
				totalGstAmount = CGST.add(SGST).add(IGST).add(UGST);
				setTaxType("GST");
				totalGST=CGSTPer.add(SGSTorIGSTorUGST);
				if( companyConfig.getVisaGstTaxConfig() != null) {
					/*visaGstTaxConfig.setCGST( companyConfig.getVisaGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
					visaGstTaxConfig.setIGST( companyConfig.getVisaGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
					visaGstTaxConfig.setSGST( companyConfig.getVisaGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
					visaGstTaxConfig.setUGST( companyConfig.getVisaGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));*/
					visaGstTaxConfig.setConvenienceFee( companyConfig.getVisaGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
					visaGstTaxConfig.setManagementFee( companyConfig.getVisaGstTaxConfig().getManagementFee().setScale(0, RoundingMode.HALF_UP));

				}
			}
			else{
				setTaxType("Service");
				if( companyConfig.getVisaServiceTaxConfig() != null) {
					visaServiceTaxConfig.setBasicTax(
							companyConfig.getVisaServiceTaxConfig().getBasicTax().setScale(2, RoundingMode.HALF_UP));
					visaServiceTaxConfig.setKrishiKalyanCess(
							companyConfig.getVisaServiceTaxConfig().getKrishiKalyanCess().setScale(2, RoundingMode.HALF_UP));
					visaServiceTaxConfig.setSwatchBharathCess(
							companyConfig.getVisaServiceTaxConfig().getSwatchBharathCess().setScale(2, RoundingMode.HALF_UP));
					visaServiceTaxConfig.setTotalTax(
							companyConfig.getVisaServiceTaxConfig().getTotalTax().setScale(2, RoundingMode.HALF_UP));
					visaServiceTaxConfig.setConvenienceFee(
							companyConfig.getVisaServiceTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
					visaServiceTaxConfig.setManagementFee(
							companyConfig.getVisaServiceTaxConfig().getManagementFee().setScale(0, RoundingMode.HALF_UP));

				}
			}
		}
		
				
		setTripId(tripId);
		userWallet=new UserDAO().getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId());
		return SUCCESS;
	}

	public String  createVisaTravelRequest(){
		TripRequest tripRequest =new TripRequest();
		User sessionUser=(User)sessionMap.get("User");
		Company sessionCompany=(Company)sessionMap.get("Company");
		User userNew=userDao.getUserByUserId(sessionUser.getId());
		String orderId = visaOrderRow.getConfirmationNumber();

		BigDecimal finalPrice = visaOrderRow.getTotalAmount();
		//visaOrderRow.setTotalAmount(finalPriceAfterTax);
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


		visaTravelRequestQuotation.setTravelDate(visaTravelRequestQuotation.getTravelDateTemp());
		visaTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		visaTravelRequestQuotationlist.add(visaTravelRequestQuotation);
		VisaTravelRequest visaTravelRequest=new VisaTravelRequest();
		visaTravelRequest.setCreatedAt(new Timestamp(new Date().getTime()));
		visaTravelRequest.setCompanyId(sessionUser.getCompanyid());
		visaTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		visaTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		 
		VisaOrderCustomer visaOrderCustomer=null;
		if(visaOrderCustomerList!=null && visaOrderCustomerList.size()>0) 
			visaOrderCustomer=visaOrderCustomerList.get(0);
		if(visaOrderCustomer!=null){
			visaTravelRequest.setFirstName(visaOrderCustomer.getFirstName());
			visaTravelRequest.setLastName(visaOrderCustomer.getLastName());
			visaTravelRequest.setTitle(visaOrderCustomer.getTitle());
		}

		
		VisaTravelRequest visaTravelRequestnew=visaTravelRequestDao.insertvisaTravelRequestnew(visaTravelRequest);
		if(visaTravelRequestnew!=null && visaTravelRequestnew.getId()>0){
			boolean isInserted=visaTravelRequestDao.insertVisaQuotationList(visaTravelRequestQuotationlist, visaTravelRequestnew);
			if(isInserted){
				if(visaTravelRequestQuotationlist!=null && visaTravelRequestQuotationlist.size()>0)
				{
					visaOrderRow.setBookingCurrency(visaTravelRequestQuotationlist.get(0).getCurrency());
				}

				OrderCustomer orderCustomer = new OrderCustomer();
				orderCustomer.setFirstName(visaTravelRequest.getFirstName());
				orderCustomer.setLastName(visaTravelRequest.getLastName());
				orderCustomer.setTitle(visaTravelRequest.getTitle());
				orderCustomer.setEmail(sessionUser.getEmail());
				orderCustomer.setCompanyId(sessionCompany.getCompanyid());
				orderCustomer.setBookingType(CommonBookingStatusEnum.VISA.getMessage());
				orderCustomer.setConfigId(newCompanyConfig.getConfig_id());
				//ADDD BY BASHA
				orderCustomer.setCreatedByUserId(CommonUtilSession.checkEmulatedUser(sessionMap)? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				visaOrderRow.setTravelDate(visaTravelRequestQuotation.getTravelDate());
				visaOrderRow.setRemarks(visaTravelRequestQuotation.getRemarks());
				visaOrderRow.setEmpNmae(visaTravelRequest.getTitle() + "  "+visaTravelRequest.getFirstName()+ "  "+ visaTravelRequest.getLastName());
				
				Company parentCompany = new CompanyDAO().getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());
				
				VisaOrderRowServiceTax visaOrderRowServiceTax = null;
				VisaOrderRowGstTax visaOrderRowGstTax = null;
				FlightGstTax flightGstTax=null;
				
				if(newCompanyConfig!=null && newCompanyConfig.getCompanyConfigType().isB2E()){
					if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")){
						if( newCompanyConfig.getVisaGstTaxConfig()!=null){
							visaOrderRowGstTax = new VisaOrderRowGstTax();
							visaOrderRowGstTax.setConvenienceFee(visaOrderRow.getConvenienceFee());
							visaOrderRowGstTax.setCreatedAt(visaOrderRow.getCreatedAt());
							visaOrderRowGstTax.setManagementFee(visaOrderRow.getManagementFee());
							visaOrderRowGstTax.setApplicableFare(newCompanyConfig.getVisaGstTaxConfig().getApplicableFare());
							visaOrderRowGstTax.setIGST(newCompanyConfig.getVisaGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
							visaOrderRowGstTax.setCGST(newCompanyConfig.getVisaGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
							visaOrderRowGstTax.setSGST(newCompanyConfig.getVisaGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
							visaOrderRowGstTax.setUGST(newCompanyConfig.getVisaGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
							
							flightGstTax=getFlightGSTTax(visaOrderRowGstTax, sessionCompany, parentCompany,visaOrderRow);
							visaOrderRowGstTax.setTotalGst(flightGstTax.getTotalTax());
							visaOrderRow.setTotalGstTax(flightGstTax.getTotalGstAmount());
							visaOrderRow.setVisaOrderRowGstTax(visaOrderRowGstTax);
							serviceOrGstTax=flightGstTax.getTotalGstAmount();
						}
					}
					else{
						if( newCompanyConfig.getVisaServiceTaxConfig()!=null){
							visaOrderRowServiceTax = new VisaOrderRowServiceTax();
							visaOrderRowServiceTax.setConvenienceFee(visaOrderRow.getConvenienceFee());
							visaOrderRowServiceTax.setCreatedAt(visaOrderRow.getCreatedAt());
							visaOrderRowServiceTax.setManagementFee(visaOrderRow.getManagementFee());
							visaOrderRowServiceTax.setApplicableFare(newCompanyConfig.getVisaServiceTaxConfig().getApplicableFare());
							visaOrderRowServiceTax.setBasicTax(newCompanyConfig.getVisaServiceTaxConfig().getBasicTax());
							visaOrderRowServiceTax.setKrishiKalyanCess(newCompanyConfig.getVisaServiceTaxConfig().getKrishiKalyanCess());
							visaOrderRowServiceTax.setSwatchBharathCess(newCompanyConfig.getVisaServiceTaxConfig().getSwatchBharathCess());
							visaOrderRowServiceTax.setTotalTax(newCompanyConfig.getVisaServiceTaxConfig().getTotalTax());
							visaOrderRow.setVisaOrderRowServiceTax(visaOrderRowServiceTax);
							serviceOrGstTax= visaOrderRow.getServiceTax();
						}
					}
					
				}
				
				if(flightGstTax==null) 
					visaOrderRow.setTotalGstTax(new BigDecimal("0.00"));
				if(visaOrderRow.getServiceTax()==null)
					visaOrderRow.setServiceTax(new BigDecimal("0.00"));
				
				visaOrderRow.setOrderCustomer(orderCustomer);
				visaOrderRow.setTaxes(new BigDecimal("0.00"));
				visaOrderRow.setCreatedAt(new Timestamp(new Date().getTime()));
				visaOrderRow.setApiComments("ok");
				visaOrderRow.setApiToBaseExchangeRate(new BigDecimal(1));
				visaOrderRow.setBaseToBookingExchangeRate(new BigDecimal(1));
				visaOrderRow.setCompanyId(String.valueOf(sessionUser.getCompanyid()));
				//added by basha
				visaOrderRow.setBookingDate(DateConversion.StringToDate(visaOrderRow.getVisaBookingDate()));
				visaOrderRow.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId()));
				visaOrderRow.setConfigId(String.valueOf(newCompanyConfig.getConfig_id()));
				visaOrderRow.setCreatedBy(sessionUser.getUsername());
				visaOrderRow.setUpdatedBy(sessionUser.getUsername());
				visaOrderRow.setProcessingFees(new BigDecimal("0.00"));
 
				visaOrderRow.setOrderId(visaOrderRow.getConfirmationNumber());
				visaOrderRow.setStatusAction("Confirmed");
				visaOrderRow.setPaymentStatus("Success");
				visaOrderRow.setBookingMode(CommonBookingStatusEnum.BOOKING_MODE_OFFLINE.getMessage());
				//visaOrderRow=visaTravelRequestDao.insertVisaOrderRow(visaOrderRow);
				RmConfigModel  rmConfigModel=rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid());
				   if(rmConfigModel!=null){
				 VisaOrderRowRmConfigStruct visaOrderRowRmConfigStruct=new VisaOrderRowRmConfigStruct();
				   visaOrderRowRmConfigStruct.setRmDynamicData(rmConfigModel.getDynamicFieldsData());
				   visaOrderRow.setVisaOrderRowRmConfigStruct(visaOrderRowRmConfigStruct);
				   }
				VisaOrderRow visaOrderRowNew= visaTravelRequestDao.insertVisaOrderRow(visaOrderRow);
				if(visaOrderRowNew!=null){
					visaOrderRowNew.setInvoiceNo(RandomConfigurationNumber.generateVisaInvoiceNumber(visaOrderRowNew.getId()));
					Long orderIdTemp = visaOrderRowNew.getId()+1000;
					orderId =RandomConfigurationNumber.generateVisaInvoiceNumber(orderIdTemp);
					visaOrderRowNew.setOrderId(orderId);
					visaOrderRowNew=new VisaOrderDao().updateVisaOrderRowDetail(visaOrderRowNew);
				}


				CompanyDAO companyDAO = new CompanyDAO();
				Map<String, BigDecimal> markups =  new LinkedHashMap<>();
				if(visaOrderRowNew!=null)
				{
					if(sessionCompany.getCompanyRole().isSuperUser())
					{
						//						markups.put(String.valueOf(sessionCompany.getCompanyid()), visaOrderRowNew.getMarkUp());
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					}
					else
					{
						Company companyParent= companyDAO.getParentCompany(sessionCompany);
						markups.put(String.valueOf(companyParent.getCompanyid()), visaOrderRowNew.getMarkUp());
						markups.put(String.valueOf(sessionCompany.getCompanyid()), new BigDecimal(0));
					}
				}

				List<Company> companyListBottomToTop= new LinkedList<>();
				List<User> userListBottomToTop= new LinkedList<>();
				Map<Integer, CutandPayModel> cutAndPayUserMap = new LinkedHashMap<>();
				FlightOrderDao  flightOrderDao = new FlightOrderDao();
				VisaOrderDao  visaOrderDao = new VisaOrderDao();

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
												FlightOrderDao.deductUserWallet(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.VISA_REMARKS.getMessage(),orderId,visaOrderRowNew.getInvoiceNo());
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
															flightOrderDao.creditUserWalletAmountForBookingFailed(totalPayableAmount,userInner,userDao,CommonBookingStatusEnum.VISA_FAILEDREMARKS.getMessage(),orderId,visaOrderRowNew.getInvoiceNo());
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
					visaOrderRowNew.setPaymentStatus("Failed");
					visaOrderRowNew=visaOrderDao.updateVisaOrderRowDetailPaymentStatus(visaOrderRowNew);
					addActionMessage(CommonBookingStatusEnum.WALLET_ERROR.getMessage());
					return ERROR;
				}else{
					visaOrderRowNew.setPaymentStatus("Success");
					visaOrderRowNew=visaOrderDao.updateVisaOrderRowDetailPaymentStatus(visaOrderRowNew);
				}


				if(visaOrderRowNew!=null)
				{
					VisaTravelRequestQuotation visaTravelRequestQuotationNew=visaTravelRequestDao.updateVisaTravelRequestQuotationWithOrderId(visaOrderRowNew,visaTravelRequestQuotation.getId());
					if(visaTravelRequestQuotationNew!=null){
						HotelFlightBookingStatus hotelFlightBookingStatus=new HotelFlightBookingStatus();
						if(visaTravelRequestQuotationNew.getHotetFlightBookingStatus()!=null && visaTravelRequestQuotationNew.getHotetFlightBookingStatus().getId()!=null)
							hotelFlightBookingStatus.setId(visaTravelRequestQuotationNew.getHotetFlightBookingStatus().getId());
						hotelFlightBookingStatus.setBooked(TravelRequestStatusEnum.BOOKED.getValue());
						visaTravelRequestDao.updateHotelOrFlightQuotationBookingStatus(hotelFlightBookingStatus);
						new VisaTravelRequestDao().updateVisaTravelRequestQuotationHide(visaTravelRequestQuotationNew.getVisaTravelRequest().getId());
						new CompanyDAO().insertEmail(visaOrderRowNew.getOrderId(), 0, Email.EMAIL_TYPE_VISA_INVOICE);
						addActionMessage("Successfully Visa Booking Done.");
					}
					else{
						addActionMessage("We found somr error while booking.");
						return ERROR;
					}
				}
				if(tripId!=null)
				{
					tripRequest.setId(tripId);
					tripRequest.setVisaTravelRequest(visaTravelRequestnew);
					tripRequest.setCompanyId(sessionUser.getCompanyid());
					tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
					tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
					tripRequest = tripRequestDao.updateTripRequestById(tripRequest);
					if(tripRequest!=null && tripRequest.getId()>0){
						addActionMessage("Successfully created");
					}
				}
				else{
					tripRequest.setVisaTravelRequest(visaTravelRequestnew);
					tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
					//tripRequest.setTripId(RandomConfigurationNumber.generateTripId(visaTravelRequestnew.getId()));
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
				if(visaOrderCustomerList!=null && visaOrderCustomerList.size()>0){
				for(VisaOrderCustomer visaOrderCustomerNew:visaOrderCustomerList){
					visaOrderCustomerNew.setEmail(sessionUser.getEmail());
					visaOrderCustomerNew.setAge("");
					visaOrderCustomerNew.setCreatedAt(new Timestamp(new Date().getTime()));
					visaOrderCustomerNew.setGender(visaOrderCustomerNew.getTitle().equalsIgnoreCase("Mr")
							|| visaOrderCustomerNew.getTitle().equalsIgnoreCase("Master") ? "M" : "F");
					visaOrderCustomerNew.setVisaOrderRow(visaOrderRowNew);
					RmConfigTripDetailsModel configTripDetailsModel=visaOrderCustomerNew.getRmConfigTripDetailsModel();
					if(configTripDetailsModel!=null){
						configTripDetailsModel.setOrderId(visaOrderRowNew.getOrderId());
						configTripDetailsModel.setOrdertype("Visa");
						visaOrderCustomerNew.setRmConfigTripDetailsModel(configTripDetailsModel);
					}
					visaOrderCustomerNew.setPaxId(RandomConfigurationNumber.generateRandomPaxID());
					visaTravelRequestDao.insertVisaOrderCustomer(visaOrderCustomerNew);
				}
				 new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(visaTravelRequestnew.getId()),"visa book Request created","Visa book Request notification",NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(),false,false,false,true,false,false);
				}
			}
			else{
				addActionMessage("We found somr error while booking.");
				return ERROR;
			}
		}
		else{
			addActionMessage("We found somr error while booking.");
			return ERROR;
		}
		return SUCCESS;

	}


	public String getVisaTravelRequestList(){
		User sessionUser=(User)sessionMap.get("User");
		List<VisaTravelRequest> list=visaTravelRequestDao.loadVisaTravelRequestList(sessionUser);
		if(list!=null && list.size()>0){
			visaTravelRequestlist=list;
		}
		return SUCCESS;
	}

	public VisaTravelRequest getVisaQuotationRow() {
		return visaTravelRequest;
	}

	public void setVisaQuotationRow(VisaTravelRequest hotelQuotationRow) {
		this.visaTravelRequest = hotelQuotationRow;
	}

	public String getVisaTravelRequestDetails()
	{
		visaTravelRequest=visaTravelRequestDao.getVisaTravelRequestDetails(visaTravelRequest.getId());
		countryList=new CountryDao().getCountryList();
		visaTravelRequestQuotationlist=visaTravelRequestDao.getVisaRequestTravelQuotationBookedList(visaTravelRequest.getId());
		return SUCCESS;
	}

	public String updateVisaTravelRequestDetails(){
		User sessionUser=(User)sessionMap.get("User");
		//HotetTravelRequest hotelTravelRequest=hotelOrderRowFineSheetDao.getVisaTravelRequestDetails(hotelQuotationRow.getId());
		visaTravelRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		visaTravelRequest.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		VisaTravelRequest hotetTravelRequestNew= visaTravelRequestDao.updateVisaTravelRequestDetails(visaTravelRequest);
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

	public String generateVisaInvoice(){
		/* long dummyId= 9;*/
		VisaOrderRow visaOrderRow=visaTravelRequestDao.getVisaOrderRowDetailsById(orderId);
		if(visaOrderRow!=null)
			setVisaOrderRow(visaOrderRow);

		return SUCCESS;
	}

	public String generatePassengerDetailsById(){
		VisaOrderRow visaOrderRow=visaTravelRequestDao.getVisaOrderRowDetailsById(idForDetails);
		if(visaOrderRow!=null)
			visaOrderRow.setProcessingFees(visaOrderRow.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setManagementFee(visaOrderRow.getManagementFee().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setServiceTax(visaOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setConvenienceFee(visaOrderRow.getConvenienceFee().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setOtherTaxes(visaOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		visaOrderRow.setTotalAmount(visaOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
		setVisaOrderRow(visaOrderRow);
		return SUCCESS;
	}


	public String getVisaOfflineInvoice(){

		VisaOrderRow visaOrderRow=visaTravelRequestDao.getVisaOrderRowDetailsById(orderId);
		if(visaOrderRow!=null)
			setVisaOrderRow(visaOrderRow);

		return SUCCESS;
	}
	public static FlightGstTax getFlightGSTTax(VisaOrderRowGstTax visaOrderRowGstTax,Company childCompany,Company parentCompany,VisaOrderRow visaOrderRow){
		CompanyEntity  companyEntity=new CompanyEntity();
		String entityState=null;
		if(visaOrderRow.getCompanyEntityId()!=null) 
		     companyEntity = new CompanyDAO().companyEntityProfile(visaOrderRow.getCompanyEntityId().intValue());
		    if(companyEntity!=null && companyEntity.getState()!=null) 
		    	entityState=companyEntity.getState(); 
		    
		BigDecimal managementFee  =visaOrderRowGstTax.getManagementFee()!=null?visaOrderRowGstTax.getManagementFee():new BigDecimal("0.0");
		BigDecimal CGSTAmount = new BigDecimal("0.0");
		BigDecimal CGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTAmount = new BigDecimal("0.0");
		//BigDecimal IGST = new BigDecimal("0.0");
		//BigDecimal UGST = new BigDecimal("0.0");
		BigDecimal totalGstAmount = new BigDecimal("0.0");
		BigDecimal totalGstPer = new BigDecimal("0.0");
		boolean isParentCompanyUT=IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());
		
		CGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getCGST());
		CGSTPer  = visaOrderRowGstTax.getCGST();
		
		if(entityState!=null){ 
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(entityState.trim());
		if(isParentCompanyUT && isBillingCompanyUT){
			CommonGSTPer =visaOrderRowGstTax.getUGST();
			CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getUGST());
			visaOrderRowGstTax.setIGST(new BigDecimal("0.0"));
			visaOrderRowGstTax.setSGST(new BigDecimal("0.0"));

		}
		if(!isParentCompanyUT && !isBillingCompanyUT){
			if(entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
				CommonGSTPer =visaOrderRowGstTax.getSGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getSGST());    
				visaOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				visaOrderRowGstTax.setUGST(new BigDecimal("0.0"));
			}
			if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
				CommonGSTPer =visaOrderRowGstTax.getIGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getIGST());   
				 CGSTPer = new BigDecimal(0);
				 CGSTAmount = new BigDecimal(0);
				visaOrderRowGstTax.setSGST(new BigDecimal("0.0"));
				visaOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				visaOrderRowGstTax.setCGST(CGSTPer);
			}
		}

		if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(entityState.trim())){
			CommonGSTPer =visaOrderRowGstTax.getUGST();
			CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getUGST());
			visaOrderRowGstTax.setIGST(new BigDecimal("0.0"));
			visaOrderRowGstTax.setSGST(new BigDecimal("0.0"));
		}

		if(isParentCompanyUT && !isBillingCompanyUT){
			if(!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(entityState.trim())){
				CommonGSTPer =visaOrderRowGstTax.getIGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getIGST());    
				 CGSTPer = new BigDecimal(0);
				 CGSTAmount = new BigDecimal(0);
				visaOrderRowGstTax.setSGST(new BigDecimal("0.0"));
				visaOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				visaOrderRowGstTax.setCGST(CGSTPer);
			}
		}
		}else{
			boolean isBillingCompanyUT=IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim());
			if(isParentCompanyUT && isBillingCompanyUT){
				CommonGSTPer =visaOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getUGST());
				visaOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				visaOrderRowGstTax.setSGST(new BigDecimal("0.0"));

			}
			if(!isParentCompanyUT && !isBillingCompanyUT){
				if(childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =visaOrderRowGstTax.getSGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getSGST());    
					visaOrderRowGstTax.setIGST(new BigDecimal("0.0"));
					visaOrderRowGstTax.setUGST(new BigDecimal("0.0"));
				}
				if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())){
					CommonGSTPer =visaOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getIGST()); 
					 CGSTPer = new BigDecimal(0);
					 CGSTAmount = new BigDecimal(0);
					visaOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					visaOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					visaOrderRowGstTax.setCGST(CGSTPer);
				}
			}

			if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
				CommonGSTPer =visaOrderRowGstTax.getUGST();
				CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getUGST());
				visaOrderRowGstTax.setIGST(new BigDecimal("0.0"));
				visaOrderRowGstTax.setSGST(new BigDecimal("0.0"));
			}

			if(isParentCompanyUT && !isBillingCompanyUT){
				if(!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim()) && !IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())){
					CommonGSTPer =visaOrderRowGstTax.getIGST();
					CommonGSTAmount =  managementFee.divide(new BigDecimal("100.0")).multiply(visaOrderRowGstTax.getIGST());   
					 CGSTPer = new BigDecimal(0);
					 CGSTAmount = new BigDecimal(0);
					visaOrderRowGstTax.setSGST(new BigDecimal("0.0"));
					visaOrderRowGstTax.setUGST(new BigDecimal("0.0"));
					visaOrderRowGstTax.setCGST(CGSTPer);
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
	public VisaTravelRequest getModel() {
		return visaTravelRequest;
	}

	public VisaTravelRequest getVisaTravelRequest() {
		return visaTravelRequest;
	}

	public void setVisaTravelRequest(VisaTravelRequest visaTravelRequest) {
		this.visaTravelRequest = visaTravelRequest;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<VisaTravelRequest> getVisaTravelRequestlist() {
		return visaTravelRequestlist;
	}

	public void setVisaTravelRequestlist(List<VisaTravelRequest> visaTravelRequestlist) {
		this.visaTravelRequestlist = visaTravelRequestlist;
	}

	public List<VisaTravelRequestQuotation> getVisaTravelRequestQuotationlist() {
		return visaTravelRequestQuotationlist;
	}

	public void setVisaTravelRequestQuotationlist(
			List<VisaTravelRequestQuotation> visaTravelRequestQuotationlist) {
		this.visaTravelRequestQuotationlist = visaTravelRequestQuotationlist;
	}

	public Long getVisaQuotationRequestId() {
		return visaQuotationRequestId;
	}

	public void setVisaQuotationRequestId(Long visaQuotationRequestId) {
		this.visaQuotationRequestId = visaQuotationRequestId;
	}

	public VisaTravelRequestQuotation getVisaTravelRequestQuotation() {
		return visaTravelRequestQuotation;
	}

	public void setVisaTravelRequestQuotation(VisaTravelRequestQuotation visaTravelRequestQuotation) {
		this.visaTravelRequestQuotation = visaTravelRequestQuotation;
	}

	public VisaOrderRow getVisaOrderRow() {
		return visaOrderRow;
	}

	public void setVisaOrderRow(VisaOrderRow visaOrderRow) {
		this.visaOrderRow = visaOrderRow;
	}

	public VisaOrderRowServiceTax getVisaOrderRowServiceTax() {
		return visaOrderRowServiceTax;
	}

	public void setVisaOrderRowServiceTax(VisaOrderRowServiceTax visaOrderRowServiceTax) {
		this.visaOrderRowServiceTax = visaOrderRowServiceTax;
	}



	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdtosend() {
		return idtosend;
	}

	public void setIdtosend(Long idtosend) {
		this.idtosend = idtosend;
	}

	public VisaServiceTaxConfig getVisaServiceTaxConfig() {
		return visaServiceTaxConfig;
	}

	public void setVisaServiceTaxConfig(VisaServiceTaxConfig visaServiceTaxConfig) {
		this.visaServiceTaxConfig = visaServiceTaxConfig;
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

	public VisaGstTaxConfig getVisaGstTaxConfig() {
		return visaGstTaxConfig;
	}

	public void setVisaGstTaxConfig(VisaGstTaxConfig visaGstTaxConfig) {
		this.visaGstTaxConfig = visaGstTaxConfig;
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

	public BigDecimal getTotalGST() {
		return totalGST;
	}

	public void setTotalGST(BigDecimal totalGST) {
		this.totalGST = totalGST;
	}

	public List<VisaOrderCustomer> getVisaOrderCustomerList() {
		return visaOrderCustomerList;
	}

	public void setVisaOrderCustomerList(List<VisaOrderCustomer> visaOrderCustomerList) {
		this.visaOrderCustomerList = visaOrderCustomerList;
	}

}
