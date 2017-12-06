package com.lintas.action;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.isl.admin.filter.HotelReportFilter;
import com.isl.admin.page.HotelReportPage;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.common.model.HotelOrderRowCancellation;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.hotel.model.HotelOrderCancellationPolicy;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.hotel.model.HotelReport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.User;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class HotelOrderAction extends ActionSupport implements SessionAware,ModelDriven<HotelReportPage>{
	/**
	 * @author info raham
	 * created date:13-09-2015
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelOrderAction.class);
	SessionMap<String, Object> sessionMap;
	HotelReportPage hotelReportPage = new HotelReportPage();
	private  PaymentTransaction paymentTransaction;
	private  HotelOrderDao hotelOrderDao = new HotelOrderDao();
	//private HotelReport hotelReport = new HotelReport();
	private List<HotelReport> HotelOrderList;
	private HotelReport CurrentHotelReport=new HotelReport();
	private List<HotelReport> roomInfo = new ArrayList<HotelReport>();
	private List<HotelReport> roomGuestInfos = new ArrayList<HotelReport>();

	private List<HotelReport> cancellationPoliciesInfo=new ArrayList<HotelReport>();
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private List<HotelCreditNote> creditNoteList;
	private HotelOrderRowCancellation hotelOrderRowCancellation=new HotelOrderRowCancellation(); 
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	List<String> fieldName = new ArrayList<String>();
	public String getCompanyHotelOrders(){
		//List<HotelReport> companyHotelOrderList=null;
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		HotelReportPage hotelOrderPage =null;
		hotelReportPage = getHotelReportPage();
		HotelReportFilter HFilter = hotelReportPage.getHotelReportFilter();
		HFilter.setLoginUser(userSessionObj);
		HFilter.setLoginCompany(companySessionObj);
		hotelReportPage.setHotelReportFilter(HFilter);
		hotelOrderPage= hotelOrderDao.getCompanyHotelOrders(hotelReportPage);
		if(hotelOrderPage!=null ){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.COMPANY_HOTEL_ORDERS.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
			hotelReportPage=hotelOrderPage;
		}
		try{
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
		}catch (Exception e) {
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		historyInfo.changeNature(BrowsingOptionPageEnum.HOTEL_BOOKINGS_ORDER_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;
	} 

	/* this method for showing hotel guest details and  room information and cancellation policy info */
	public String showGuestDetails(){
		HotelReport hotelOrder=hotelOrderDao.hotelRoomandGuestandHotelOrderRowInfo(hotelReportPage.getId());
		PaymentTransaction paymentTransaction=hotelOrderDao.getPaymentTransactionInfo(hotelOrder.getHotelOrderRow().getOrderReference());
		setPaymentTransaction(paymentTransaction);
		Company companySessionObj=(Company)sessionMap.get("Company");
		User userSessionObj=(User)sessionMap.get("User");
		RmConfigDao rmConfigDao=new RmConfigDao();
//added  by basha		
		CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(companySessionObj.getCompanyid());
		HotelOrderRowCancellation hotelOrderRowCancellation=new CreditNoteDao().getHotelOrderRowCancellationWithAPIStatus(hotelOrder.getHotelOrderRow().getOrderReference());
		setHotelOrderRowCancellation(hotelOrderRowCancellation);
		if(hotelOrder!=null && hotelOrder.getHotelOrderRow()!=null){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.HOTEL_REPORTS.getId();
			detailType=BrowsingHistoryDetailTypeEnum.HOTEL_ORDERS_EDIT.getId();
			HotelOrderRow hotelOrderRow=hotelOrder.getHotelOrderRow();
			HotelReport currentHotelReport = new HotelReport();
			BigDecimal basePrice= hotelOrderRow.getApiPrice().multiply(hotelOrderRow.getApiToBaseExchangeRate()) ;
			BigDecimal taxes= hotelOrderRow.getTaxes().multiply(hotelOrderRow.getApiToBaseExchangeRate()) ;
			BigDecimal totalBasePrice=basePrice.add(hotelOrderRow.getMarkupAmount());
			BigDecimal taxesInBooking=taxes.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
			BigDecimal disCountInBooking=hotelOrderRow.getDiscountAmount().multiply(hotelOrderRow.getApiToBaseExchangeRate()).multiply(hotelOrderRow.getBaseToBookingExchangeRate());
			currentHotelReport.setOrderRef(hotelOrderRow.getOrderReference());
			currentHotelReport.setState(hotelOrderRow.getHotelOrderHotelData().getState());
			currentHotelReport.setHotel_cat(hotelOrderRow.getHotelOrderHotelData().getHotelCategory());
			currentHotelReport.setHotel_loc(hotelOrderRow.getHotelOrderHotelData().getHotelLocation());
			currentHotelReport.setHotelName(hotelOrderRow.getHotelOrderHotelData().getName());
			currentHotelReport.setCountry(hotelOrderRow.getHotelOrderHotelData().getCountry());
			currentHotelReport.setHotelType(hotelOrderRow.getHotelOrderHotelData().getType());
			currentHotelReport.setGuests(hotelOrderRow.getTotalGuest());
			currentHotelReport.setCurCode(hotelOrderRow.getBookingCurrency());
			currentHotelReport.setTotal(hotelOrderRow.getFinalPrice().setScale(2, BigDecimal.ROUND_UP));//totalPrice.setScale(2, BigDecimal.ROUND_UP)
			currentHotelReport.setTax(taxesInBooking.setScale(2, BigDecimal.ROUND_UP));
			currentHotelReport.setFee_amount(hotelOrderRow.getFeeAmount());
			currentHotelReport.setDiscount(disCountInBooking.setScale(2, BigDecimal.ROUND_UP));
			currentHotelReport.setPaymentStatus(hotelOrderRow.getPaymentStatus());
			currentHotelReport.setTotAmountSpent(totalBasePrice);
			currentHotelReport.setBookingMode(hotelOrderRow.getBookingMode());
			currentHotelReport.setStatus(hotelOrderRow.getStatusAction());
			currentHotelReport.setId(hotelOrderRow.getId());
			currentHotelReport.setUserId(hotelOrderRow.getUserId());
			currentHotelReport.setCompanyId(hotelOrderRow.getCompanyId());
			currentHotelReport.setCreditNoteIssued(hotelOrderRow.isCreditNoteIssued());
			currentHotelReport.setOrderUpdated(hotelOrderRow.isOrderUpdated());
			currentHotelReport.setReferenceCode(hotelOrderRow.getReferenceCode());
			currentHotelReport.setOrderRequested(hotelOrderRow.isOrderRequested());
			currentHotelReport.setStatus(hotelOrderRow.getStatusAction());
			currentHotelReport.setFinalPrice(hotelOrderRow.getFinalPrice());
			currentHotelReport.setConfirmNo(hotelOrderRow.getConfirmationNo());
			currentHotelReport.setCreatedBy(hotelOrderRow.getCreatedBy());
			currentHotelReport.setAppkey(companyConfig.getAppKey());
			currentHotelReport.setCancelMode(hotelOrderRow.getCancelMode());
			if(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()!=null)
				currentHotelReport.setManagementFee(hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee());
			else
				currentHotelReport.setManagementFee(new BigDecimal(0));
			if( hotelOrderRow.getHotelOrderRowGstTax()!=null && hotelOrderRow.getHotelOrderRowGstTax().getManagementFee()!=null)
				currentHotelReport.setManagementFee(hotelOrderRow.getHotelOrderRowGstTax().getManagementFee());
			else
				currentHotelReport.setManagementFee(new BigDecimal(0));
			
			if(hotelOrderRowCancellation!=null && hotelOrderRowCancellation.getOrderId()!=null){
				currentHotelReport.setCancellationFees(hotelOrderRowCancellation.getAPIChargeAmount().setScale(3));
				currentHotelReport.setAPIStatusCode(hotelOrderRowCancellation.getAPIStatusCode());
			}
			
			if(hotelOrderRow.getFeeAmount()!=null)
				currentHotelReport.setConvenienceFees(hotelOrderRow.getFeeAmount().setScale(2, BigDecimal.ROUND_UP));
			else
				currentHotelReport.setConvenienceFees(new BigDecimal(0));
			HotelCreditNote creditNoteObj=hotelOrderDao.hotelCreditNoteData(hotelReportPage.getId(),String.valueOf(companySessionObj.getCompanyid()));
			currentHotelReport.setMyCreditNote(creditNoteObj);
			HotelCreditNote childCreditNoteObj=hotelOrderDao.hotelCreditNoteData(hotelReportPage.getId(),hotelOrderRow.getCompanyId());
			if(childCreditNoteObj!=null){
				currentHotelReport.setCreditNoteActiontype(childCreditNoteObj.getActionType());
			}
			HotelCreditNote creditNoteObjParent = null;
			try {
				List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
				if(parentUserIds!=null && parentUserIds.size()>0)
				{
					creditNoteObjParent=hotelOrderDao.hotelCreditNoteData(hotelReportPage.getId(),String.valueOf(parentUserIds.get(0)));
				}
			} catch (Exception e) {
				logger.error(e);
			}
			if(creditNoteObj!=null){
				currentHotelReport.setCreditNoteActiontype(creditNoteObj.getActionType());
				HotelOrderRowCancellation horCancelobj=hotelOrderDao.getHotelOrderRowCancellation(hotelOrderRow.getOrderReference());
				if(horCancelobj!=null && horCancelobj.getAPIChargeAmount()!=null){
					currentHotelReport.setApiChargeAmount(horCancelobj.getAPIChargeAmount().setScale(2, BigDecimal.ROUND_UP));
					currentHotelReport.setCancelInitiated(true);
				}
				else
					currentHotelReport.setCancelInitiated(false);
				if(horCancelobj!=null) 
					currentHotelReport.setHotelOrderRowCancellation(horCancelobj);
				if(currentHotelReport.getApiChargeAmount()!=null)
				{
					currentHotelReport.setCancellationFees(currentHotelReport.getApiChargeAmount().setScale(2, BigDecimal.ROUND_UP));
				}
				else if(creditNoteObjParent!=null && creditNoteObjParent.getCancellationFees()!=null)
				{
					currentHotelReport.setCancellationFees(creditNoteObjParent.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
				}
				 
				if(creditNoteObj.getManagementFees()!=null){
					currentHotelReport.setManagementFee(creditNoteObj.getManagementFees());
				}
				if(Integer.parseInt(creditNoteObj.getCompanyId())==companySessionObj.getCompanyid()){
					currentHotelReport.setSuperUserOrdered(true);
				}
				 
			}
			CurrentHotelReport=currentHotelReport;

			List<HotelCreditNote>  creditList=hotelOrderDao.loadCreditNoteListByFlightRowId(hotelReportPage.getId());
			if(creditList!=null && creditList.size()>0){
				setCreditNoteList(creditList) ;
			}

		}
		List<Long> roomIds=new ArrayList<Long>();
		if(hotelOrder.getHotelOrderRoomInfo()!=null && hotelOrder.getHotelOrderRoomInfo().size()>0){
			for(HotelOrderRoomInfo hotelRoomInfo:hotelOrder.getHotelOrderRoomInfo()){
				HotelReport hotelReport=new HotelReport();
				hotelReport.setCreatedDate(DateConversion.convertTimestampToString(hotelRoomInfo.getCreatedAt()));
				hotelReport.setMealType(hotelRoomInfo.getMealType());
				hotelReport.setHotelName(hotelRoomInfo.getName());
				hotelReport.setRef_code(hotelRoomInfo.getReferenceCode());
				hotelReport.setStatus(hotelRoomInfo.getStatus());
				roomIds.add(hotelRoomInfo.getId());
				hotelReport.setId(hotelRoomInfo.getId());
				roomInfo.add(hotelReport);

			}
		} 
		if(roomIds!=null && roomIds.size()>0)
		{
			for(Long id:roomIds){
				List<HotelOrderGuest> hotelGuestInfo=hotelOrderDao.hotelOrderGuestInfo(id);
				if(hotelGuestInfo!=null && hotelGuestInfo.size()>0){
					for(HotelOrderGuest orderGuest:hotelGuestInfo){
						if(orderGuest.getRoomInfo().getId().longValue()==id.longValue()){
							HotelReport hotelReport=new HotelReport();
							hotelReport.setFirstname(orderGuest.getFirstName());
							hotelReport.setLastname(orderGuest.getLastName());
							hotelReport.setEmail(orderGuest.getEmail());
							if(orderGuest.getRmConfigTripDetailsModel()!=null && orderGuest.getRmConfigTripDetailsModel().getId()>0){
								fetchRmConfigDetailsOfCustomer(hotelReport,orderGuest.getRmConfigTripDetailsModel());
							}
							roomGuestInfos.add(hotelReport);
						}	
					}
					
				}

			}
			try{
				RmConfigModel rmConfigModel=rmConfigDao.getConfigDetailsByCompanyId(companySessionObj.getCompanyid());
				if(rmConfigModel!=null && rmConfigModel.getId()>0){
					String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("") ?rmConfigModel.getDynamicFieldsData().split(","):null;
					if(manualStringFields.length>0){
					for(String oneField:manualStringFields){
						String fieldsName[]=oneField.split(":");
						fieldName.add(fieldsName[0]);
					}
					}
				}
			}catch (Exception e) {
			}
		}

		List<HotelOrderCancellationPolicy> hotelOrderCancellationPolicies=hotelOrderDao.hotelOrderCancellationPolicyDetails(hotelReportPage.getId());
		if(hotelOrderCancellationPolicies!=null && hotelOrderCancellationPolicies.size()>0){
			//logger.info("HotelOrderCancellationPolicy........."+hotelOrderCancellationPolicies.size());
			for(HotelOrderCancellationPolicy cancellationPolicy:hotelOrderCancellationPolicies){
				HotelReport hotelReport=new HotelReport();
				hotelReport.setCancellationDay(cancellationPolicy.getCancellationDay());
				hotelReport.setCreatedAt(cancellationPolicy.getCreatedAt());
				hotelReport.setCurCode(cancellationPolicy.getCurrency());
				hotelReport.setFormattedFeeAmount(cancellationPolicy.getFormattedFeeAmount());
				hotelReport.setFeeType(cancellationPolicy.getFeeType());
				hotelReport.setFeeAmount(cancellationPolicy.getFeeAmount());
				hotelReport.setRemarks(cancellationPolicy.getRemarks());
				hotelReport.setStartDate(DateConversion.convertDateToStringToDate(cancellationPolicy.getStartDate()));
				hotelReport.setEndDate(DateConversion.convertDateToStringToDate(cancellationPolicy.getEndDate()));
				hotelReport.setFromDate(cancellationPolicy.getFromDate());
				cancellationPoliciesInfo.add(hotelReport); 
			}	
		}
		User user = (User)sessionMap.get("User");
		HistoryInfo	historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.HOTEL_ORDER.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);

		return SUCCESS;
	}

	public HotelReport fetchRmConfigDetailsOfCustomer(HotelReport data,RmConfigTripDetailsModel rmConfigModel){
		data.setApproverName(rmConfigModel.getApproverName()!=null?rmConfigModel.getApproverName():"-");
		
		data.setBillNonBill(rmConfigModel.getBillNonBill()!=null?rmConfigModel.getBillNonBill() :"-");
		data.setBussinessUnit(rmConfigModel.getBussinessUnit()!=null?rmConfigModel.getBussinessUnit() :"-");
		data.setCostCenter(rmConfigModel.getCostCenter()!=null?rmConfigModel.getCostCenter() :"-");
		data.setDepartment(rmConfigModel.getDepartment()!=null?rmConfigModel.getDepartment() :"-");
		data.setEmpCode(rmConfigModel.getEmpCode()!=null?rmConfigModel.getEmpCode() :"-"); 
		data.setProjectCode(rmConfigModel.getProjectCode()!=null?rmConfigModel.getProjectCode() :"-"); 
		data.setLocation(rmConfigModel.getLocation()!=null?rmConfigModel.getLocation() :"-");
		data.setManualField1(rmConfigModel.getManualField1()!=null?rmConfigModel.getManualField1() :"-");
		data.setManualField2 (rmConfigModel.getManualField2()!=null?rmConfigModel.getManualField2() :"-");
		data.setManualField3(rmConfigModel.getManualField3()!=null?rmConfigModel.getManualField3() :"-");
		data.setManualField4( rmConfigModel.getManualField4()!=null?rmConfigModel.getManualField4() :"-");
		data.setManualField5 (rmConfigModel.getManualField5()!=null?rmConfigModel.getManualField5() :"-");
		data.setReasonForTravel(rmConfigModel.getReasonForTravel()!=null?rmConfigModel.getReasonForTravel() :"-");
		data.setTrNumber( rmConfigModel.getTrNumber()!=null?rmConfigModel.getTrNumber():"-");
		return data;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap= (SessionMap<String, Object>) map;
	}


	@Override
	public HotelReportPage getModel() {
		// TODO Auto-generated method stub
		return hotelReportPage;
	}


	public List<HotelReport> getHotelOrderList() {
		return HotelOrderList;
	}


	public void setHotelOrderList(List<HotelReport> hotelOrderList) {
		HotelOrderList = hotelOrderList;
	}


	public List<HotelReport> getRoomInfo() {
		return roomInfo;
	}


	public void setRoomInfo(List<HotelReport> roomInfo) {
		this.roomInfo = roomInfo;
	}


	public List<HotelReport> getRoomGuestInfos() {
		return roomGuestInfos;
	}


	public void setRoomGuestInfos(List<HotelReport> roomGuestInfos) {
		this.roomGuestInfos = roomGuestInfos;
	}


	public List<HotelReport> getCancellationPoliciesInfo() {
		return cancellationPoliciesInfo;
	}


	public void setCancellationPoliciesInfo(
			List<HotelReport> cancellationPoliciesInfo) {
		this.cancellationPoliciesInfo = cancellationPoliciesInfo;
	}


	public HotelReport getCurrentHotelReport() {
		return CurrentHotelReport;
	}


	public void setCurrentHotelReport(HotelReport currentHotelReport) {
		CurrentHotelReport = currentHotelReport;
	}


	public List<HotelCreditNote> getCreditNoteList() {
		return creditNoteList;
	}


	public void setCreditNoteList(List<HotelCreditNote> creditNoteList) {
		this.creditNoteList = creditNoteList;
	}
	public HotelReportPage getHotelReportPage() {
		if(hotelReportPage == null)
			hotelReportPage = new HotelReportPage();

		return hotelReportPage;
	}

	public void setHotelReportPage(HotelReportPage hotelReportPage) {
		this.hotelReportPage = hotelReportPage;
	}

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public HotelOrderRowCancellation getHotelOrderRowCancellation() {
		return hotelOrderRowCancellation;
	}

	public void setHotelOrderRowCancellation(HotelOrderRowCancellation hotelOrderRowCancellation) {
		this.hotelOrderRowCancellation = hotelOrderRowCancellation;
	}

	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}

	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}

	public List<String> getFieldName() {
		return fieldName;
	}

	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}

}
