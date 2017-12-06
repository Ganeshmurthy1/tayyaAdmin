package com.lintas.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.filter.HotelReportFilter;
import com.isl.admin.page.HotelReportPage;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.hotel.model.HotelOrderCancellationPolicy;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.hotel.model.HotelReport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.utility.DateConversion;
import com.lintas.utility.GstPropertiesFile;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class HotelReportAction extends ActionSupport implements ModelDriven<HotelReportPage>,SessionAware{

	/**@author info Raham
	 * created date:15-04-2015
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelReportAction.class);

	HotelReportPage hotelReportPage = new HotelReportPage();
	//HotelReport hotelReport = new HotelReport();
	SessionMap<String, Object> sessionMap;
	List<HotelReport> reportData_list=null;
	List<HotelReport> reportFilter_list= new ArrayList<HotelReport>();
	private  HotelOrderDao hotelOrderDao = new HotelOrderDao();
	private List<HotelReport> HotelReportsList;
	private HotelReport AgentCommission;
	private HotelReport CurrentHotelReport;
	private List<HotelReport> roomInfo =new ArrayList<>();
	private List<HotelReport> roomGuestInfos =new ArrayList<>();
	private List<HotelReport> cancellationPoliciesInfo = new ArrayList<HotelReport>();
	private List<PaymentTransaction> payTxInfo;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private List<WalletAmountTranferHistory> txWalletHistory; 
	private String showType = null;
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	private String showReportType; 
	private String filterUptoDate; // W,M,W
	
	public String getCompanyHotelReports(){
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		hotelReportPage = getHotelReportPage();
		HotelReportFilter HFilter = hotelReportPage.getHotelReportFilter();
		HFilter.setLoginUser(userSessionObj);
		HFilter.setLoginCompany(companySessionObj);
		HFilter.setShowtype(showType);
		if(showType!=null)
			statusCode = ActionStatusEnum.SUCCESS.getCode();
		actionId=BrowsingOptionActionEnum.HOTEL_REPORTS.getId();
		detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
			//HFilter.setReportType(0);

		hotelReportPage.setHotelReportFilter(HFilter);
		hotelReportPage = hotelOrderDao.getCompanyHotelReports(hotelReportPage,showType,companySessionObj);
		
		try{
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
		}catch (Exception e) {
		}
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.HOTEL_BOOKINGS_REPORT_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;
	} 


	/*showing  hotel reports based on orderids */
	public String showReportDetails(){
		HotelReport hotelReport = hotelOrderDao.hotelRoomandGuestandHotelOrderRowInfo(hotelReportPage.getSelectedRowIndex());
		List<PaymentTransaction> payTxList = hotelOrderDao.getHotelOrderPaymentInfo(hotelReport.getHotelOrderRow().getOrderReference());
		List<HotelOrderCancellationPolicy> hotelOrderCancellationPolicies=hotelOrderDao.hotelOrderCancellationPolicyDetails(hotelReport.getId());
		if(hotelReport.getHotelOrderRow()!=null){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.HOTEL_REPORTS.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();	
			HotelOrderRow row=hotelReport.getHotelOrderRow();
			CurrentHotelReport = new HotelReport();
			BigDecimal basePrice= row.getApiPrice().multiply(row.getApiToBaseExchangeRate()) ;
			BigDecimal taxes= row.getTaxes().multiply(row.getApiToBaseExchangeRate()) ;
			BigDecimal totalBasePrice=basePrice.add(row.getMarkupAmount());
			BigDecimal basePriceInBooking=totalBasePrice.multiply(row.getBaseToBookingExchangeRate());
			BigDecimal taxesInBooking=taxes.multiply(row.getBaseToBookingExchangeRate());
			BigDecimal totalPrice=row.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);
			//logger.info("totalPrice----in booking--------------------"+totalPrice);
			BigDecimal disCountInBooking=row.getDiscountAmount().multiply(row.getApiToBaseExchangeRate()).multiply(row.getBaseToBookingExchangeRate());
			CurrentHotelReport.setOrderRef(row.getOrderReference());
			CurrentHotelReport.setState(row.getHotelOrderHotelData().getState());
			CurrentHotelReport.setHotel_cat(row.getHotelOrderHotelData().getHotelCategory());
			CurrentHotelReport.setHotel_loc(row.getHotelOrderHotelData().getHotelLocation());
			CurrentHotelReport.setHotelName(row.getHotelOrderHotelData().getName());
			CurrentHotelReport.setCountry(row.getHotelOrderHotelData().getCountry());
			CurrentHotelReport.setHotelType(row.getHotelOrderHotelData().getType());
			CurrentHotelReport.setGuests(row.getTotalGuest());
			CurrentHotelReport.setCurCode(row.getBookingCurrency());
			CurrentHotelReport.setTotal(row.getFinalPrice().setScale(2, BigDecimal.ROUND_UP));//totalPrice.setScale(2, BigDecimal.ROUND_UP)
			CurrentHotelReport.setTax(taxesInBooking.setScale(2, BigDecimal.ROUND_UP));
			CurrentHotelReport.setFee_amount(row.getFeeAmount().setScale(2, BigDecimal.ROUND_UP));
			CurrentHotelReport.setDiscount(disCountInBooking.setScale(2, BigDecimal.ROUND_UP));
			CurrentHotelReport.setPaymentStatus(row.getPaymentStatus());
			CurrentHotelReport.setStatus(row.getStatusAction());
			CurrentHotelReport.setReferenceCode(row.getReferenceCode());
			CurrentHotelReport.setCreatedBy(row.getCreatedBy()); 
			CurrentHotelReport.setConfirmNo(row.getConfirmationNo());
			ApiProvider apiProvider = null;
			try{
				
				apiProvider=new ApiProviderDao().getApiProviderDetails(row.getApiProvoder(), "Hotels");
			}
			catch (Exception e) {
				logger.error(e);
			}
			if(apiProvider!=null){
				CurrentHotelReport.setApiProvider(apiProvider);
			}
			else{
				statusCode = ActionStatusEnum.FAILED.getCode();
				actionId=BrowsingOptionActionEnum.HOTEL_REPORTS.getId();
				detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
				
				apiProvider = new ApiProvider();
				apiProvider.setVendorName(row.getApiProvoder());
			}
			GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
			CurrentHotelReport.setCompanysGstOn(gstPropertiesFile.getGstSwitchonValue());
		} 

		List<Long> roomIds =new ArrayList<Long>();
		if(hotelReport.getHotelOrderRoomInfo()!=null){
			for(HotelOrderRoomInfo hotelRoomInfo:hotelReport.getHotelOrderRoomInfo()){
				HotelReport hotelreport=new HotelReport();
				hotelreport.setCreatedDate(DateConversion.convertTimestampToStringwithoutseconds(hotelRoomInfo.getCreatedAt()));
				hotelreport.setMealType(hotelRoomInfo.getMealType());
				hotelreport.setHotelName(hotelRoomInfo.getName());
				hotelreport.setRef_code(hotelRoomInfo.getReferenceCode());
				hotelreport.setStatus(hotelRoomInfo.getStatus());
				roomIds.add(hotelRoomInfo.getId());
				hotelreport.setId(hotelRoomInfo.getId());
				roomInfo.add(hotelreport);
				//sessionMap.put("HotelRoomInfo",roomInfo);
			}
		} 
		if(roomIds!=null){
			for(Long id:roomIds){
				List<HotelOrderGuest> hotelGuestInfo =hotelOrderDao.hotelOrderGuestInfo(id);
				if(hotelGuestInfo!=null){
					for(HotelOrderGuest orderGuest:hotelGuestInfo){

						if(orderGuest.getRoomInfo().getId().longValue() == id.longValue()){

							logger.info("----------guest room Id...-------"+orderGuest.getRoomInfo().getId()+".....room ids......"+id);
							HotelReport hotelreport=new HotelReport();
							logger.info("----------orderGuest.getFirstName()...-------"+orderGuest.getFirstName());
							hotelreport.setFirstname(orderGuest.getFirstName());
							hotelreport.setLastname(orderGuest.getLastName());
							hotelreport.setEmail(orderGuest.getEmail());
							roomGuestInfos.add(hotelreport);
							//sessionMap.put("HotelGuestInfo",roomGuestInfos);
						}	
					}
				}
			}
		}

		if(hotelOrderCancellationPolicies!=null){
			logger.info("------------HotelOrderCancellationPolicy list........."+hotelOrderCancellationPolicies.size());
			for(HotelOrderCancellationPolicy cancellationPolicy:hotelOrderCancellationPolicies){
				HotelReport hotelreport=new HotelReport();
				hotelreport.setCancellationDay(cancellationPolicy.getCancellationDay());
				hotelreport.setCreatedAt(cancellationPolicy.getCreatedAt());
				hotelreport.setCurCode(cancellationPolicy.getCurrency());
				hotelreport.setFormattedFeeAmount(cancellationPolicy.getFormattedFeeAmount());
				hotelreport.setFeeType(cancellationPolicy.getFeeType());
				hotelreport.setFeeAmount(cancellationPolicy.getFeeAmount().setScale(2, BigDecimal.ROUND_UP));
				hotelreport.setRemarks(cancellationPolicy.getRemarks());
				hotelreport.setStartDate(DateConversion.convertDateToStringToDate(cancellationPolicy.getStartDate()));
				hotelreport.setEndDate(DateConversion.convertDateToStringToDate(cancellationPolicy.getEndDate()));
				hotelreport.setFromDate(cancellationPolicy.getFromDate());
				cancellationPoliciesInfo.add(hotelreport); 
				//sessionMap.put("HotelcancellationPoliciyInfo",cancellationPoliciesInfo);
			}	
		}

		if(payTxList!=null){
			payTxInfo=payTxList;
		}

		ReportData companyWalletHistory=new FlightOrderDao().getWalletAmountTxStatementHistoryByUserId(hotelReport.getHotelOrderRow().getUserId(),hotelReport.getHotelOrderRow().getOrderReference());
		List<WalletAmountTranferHistory> newWalletHistoryList=new ArrayList<WalletAmountTranferHistory>();
		if(companyWalletHistory!=null && companyWalletHistory.getWalletAmountTranferHistory()!=null){
			for(WalletAmountTranferHistory history:companyWalletHistory.getWalletAmountTranferHistory()){
					history.setOpeningBalance(history.getOpeningBalance().setScale(2,  BigDecimal.ROUND_UP));
					history.setClosingBalance(history.getClosingBalance().setScale(2,  BigDecimal.ROUND_UP));
					history.setAmount(history.getAmount().setScale(2,  BigDecimal.ROUND_UP));
					newWalletHistoryList.add(history);
				 
			}
		}
		if(newWalletHistoryList!=null) 
			txWalletHistory=newWalletHistoryList;
 
		User user = (User)sessionMap.get("User");
		HistoryInfo	historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.HOTEL_REPORT.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
		return SUCCESS;

	}

	public String getIndividualHotelReports(){
		HotelReportFilter hotelReportFilter = hotelReportPage.getHotelReportFilter();
		Company companyObj=(Company)sessionMap.get("Company");
		if(companyObj.getCompanyRole().isSuperUser())
			hotelReportFilter.setReportType(0);
		else
			hotelReportFilter.setReportType(1);
		
		hotelReportFilter.setPaymentStatus("ALL");
		hotelReportFilter.setSupplierName("ALL");
		getDayWeekMonthBookingDates(filterUptoDate, hotelReportFilter);
		if(showReportType.equalsIgnoreCase("BC"))
			hotelReportFilter.setBookingStatus("Confirmed");
		if(showReportType.equalsIgnoreCase("PS"))
			hotelReportFilter.setPaymentStatus("Success");
		if(showReportType.equalsIgnoreCase("PF"))
			hotelReportFilter.setPaymentStatus("Failed");
		
		hotelReportPage.setHotelReportFilter(hotelReportFilter);
		
		getCompanyHotelReports();
		return SUCCESS;
	}
	
	public HotelReportFilter getDayWeekMonthBookingDates(String type,HotelReportFilter hotelReportFilter){
		String startDate = "";
		String weekDate = "";
		String monthDate = "";
		try {
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Date start = c.getTime();
			c.add(Calendar.DATE, -6);
			Date end = c.getTime();
			c.add(Calendar.DATE, -24);
			Date monthend = c.getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			startDate = sdf.format(start);
			weekDate = sdf.format(end);
			monthDate = sdf.format(monthend);
		} catch (Exception e) {
		}
		if (type.equalsIgnoreCase("D")) {
			hotelReportFilter.getDateFilterBooking().setDtStart(startDate);
			hotelReportFilter.getDateFilterBooking().setDtEnd(startDate);
		} else if (type.equalsIgnoreCase("W")) {
			hotelReportFilter.getDateFilterBooking().setDtStart(weekDate);
			hotelReportFilter.getDateFilterBooking().setDtEnd(startDate);
		} else if (type.equalsIgnoreCase("M")) {
			hotelReportFilter.getDateFilterBooking().setDtStart(monthDate);
			hotelReportFilter.getDateFilterBooking().setDtEnd(startDate);
		} 
		return hotelReportFilter;
	}

	@Override
	public HotelReportPage getModel() {
		// TODO Auto-generated method stub
		return hotelReportPage;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	public List<HotelReport> getHotelReportsList() {
		return HotelReportsList;
	}

	public void setHotelReportsList(List<HotelReport> hotelReportsList) {
		HotelReportsList = hotelReportsList;
	}

	public HotelReport getAgentCommission() {
		return AgentCommission;
	}

	public void setAgentCommission(HotelReport agentCommission) {
		AgentCommission = agentCommission;
	}

	public HotelReport getCurrentHotelReport() {
		return CurrentHotelReport;
	}

	public void setCurrentHotelReport(HotelReport currentHotelReport) {
		CurrentHotelReport = currentHotelReport;
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

	public List<PaymentTransaction> getPayTxInfo() {
		return payTxInfo;
	}

	public void setPayTxInfo(List<PaymentTransaction> payTxInfo) {
		this.payTxInfo = payTxInfo;
	}

	public List<WalletAmountTranferHistory> getTxWalletHistory() {
		return txWalletHistory;
	}

	public void setTxWalletHistory(List<WalletAmountTranferHistory> txWalletHistory) {
		this.txWalletHistory = txWalletHistory;
	}

	public HotelReportPage getHotelReportPage() {
		if(hotelReportPage == null)
			hotelReportPage = new HotelReportPage();

		return hotelReportPage;
	}

	public void setHotelReportPage(HotelReportPage hotelReportPage) {
		this.hotelReportPage = hotelReportPage;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}


	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}


	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}


	public String getShowReportType() {
		return showReportType;
	}


	public void setShowReportType(String showReportType) {
		this.showReportType = showReportType;
	}


	public String getFilterUptoDate() {
		return filterUptoDate;
	}


	public void setFilterUptoDate(String filterUptoDate) {
		this.filterUptoDate = filterUptoDate;
	}

}
