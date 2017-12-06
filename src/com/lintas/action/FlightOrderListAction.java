package com.lintas.action;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Company;
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
public class FlightOrderListAction extends ActionSupport implements SessionAware,ModelDriven<FlightReportPage> {
	/**
	 * @author info Raham
	 * Date:7-09-2015
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightOrderAction.class);
	SessionMap<String , Object> sessionMap;
	FlightReportPage flightReportPage=new FlightReportPage();
	List<ReportData> orderFilter_list= new ArrayList<ReportData>();
	FlightOrderDao  flightOrderDao=new FlightOrderDao();
	private List<ReportData> passList=new ArrayList<>();
	private List<ReportData> flightInfo=new ArrayList<>();
	private List<ReportData> paymentInfo=new ArrayList<>();
	private ReportData agentTotalOrderCommObj=new ReportData();
	private List<ReportData> companyFlightOrderList;
	private List<Airlinelist> airlineList;
	private List<CreditNote> creditNoteList;
	private ReportData ReportData = new ReportData();
	private boolean isCreditIssued = false;
	private String showType = null;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	public String getCompanyFlightOrdersPaginationTest(){
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		BigDecimal totSpentAmount =new BigDecimal("0");
		airlineList=new CountryDao().getAirlineList();
		FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
		if(flightReportPage.getFlightReportFilter().getCompanyId()>0){
			companySessionObj=new CompanyDAO().getCompanyProfile(flightReportPage.getFlightReportFilter().getCompanyId());
		}
		flightReportFilter.setLoginCompany(companySessionObj);
		flightReportFilter.setLoginUser(userSessionObj);
		flightReportPage.setFlightReportFilter(flightReportFilter);
		flightReportPage=flightOrderDao.getCompanyFlightOrders1(flightReportPage,companySessionObj);

		if(flightReportPage!=null && flightReportPage.getItems()!=null && flightReportPage.getItems().size()>0){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.FLIGHT_REPORTS.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();	
			logger.info("....companyFlightOrderList........size...."+flightReportPage.getItems().size());
			for(ReportData report:flightReportPage.getItems()){
				totSpentAmount = totSpentAmount.add(report.getFinalPrice()!=null?report.getFinalPrice():new BigDecimal(0));
			}
			agentTotalOrderCommObj.setTotAmountSpent(totSpentAmount);
		}
		try{
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
		}catch (Exception e) {
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.FLIGHT_BOOKINGS_REPORT_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;
		
	} 



	/* method for  show  PassengerOrderDetails based on orderid */
	public String showPassengerOrderDetails() throws ParseException{
		Company companySessionObj=(Company)sessionMap.get("Company");
		User userSessionObj=(User)sessionMap.get("User");
		ReportData flightOrderCustomer=flightOrderDao.getFlightOrderCustomerDetail(flightReportPage.getId());
		ReportData paymentData=flightOrderDao.getPaymentTransactionDetail(flightReportPage.getOrderId());
		ReportData newReportData=flightOrderDao.getReportDetailsByRowId(flightReportPage.getId());
		setIsCreditIssued(false);
		newReportData.setLastticketdate(DateConversion.getBluestarDate(newReportData.getLastticketdate()));
		// get credit note of current company 
		CreditNote creditNoteObj=flightOrderDao.flightCreditNoteData(flightReportPage.getId(),companySessionObj.getCompanyid());
		CreditNote creditNoteObjParent = null;
		creditNoteList=flightOrderDao.loadCreditNoteListByFlightRowId(flightReportPage.getId());
		try {
			List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
			if(parentUserIds!=null && parentUserIds.size()>0)
			{
				creditNoteObjParent=flightOrderDao.flightCreditNoteData(flightReportPage.getId(),parentUserIds.get(0));
				if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent())
					setIsCreditIssued(true);
				else if(creditNoteObjParent != null && creditNoteObjParent.isOrderUpdated())
					setIsCreditIssued(true);

			}
		} catch (Exception e) {
			logger.error(e);
		}
		if(newReportData!=null){
			if(creditNoteObj!=null){
				newReportData.setCreditNoteActiontype(creditNoteObj.getActionType());
				if(creditNoteObjParent!=null && creditNoteObjParent.getCancellationFees()!=null){
					newReportData.setCancellationFees(creditNoteObjParent.getCancellationFees());
				}
				if(creditNoteObjParent!=null && creditNoteObjParent.getConvenienceFees()!=null){
					newReportData.setConvenienceFees(creditNoteObjParent.getConvenienceFees());
				}
				if(creditNoteObj.getManagementFees()!=null){
					newReportData.setManagementFee(creditNoteObj.getManagementFees());
				}
				if(Integer.parseInt(creditNoteObj.getCompanyId())==companySessionObj.getCompanyid()){
					newReportData.setSuperUserOrdered(true);
				}
			}
			ReportData=newReportData;
		}

		if(flightOrderCustomer!=null && flightOrderCustomer.getFlightOrderTripDetail()!=null && flightOrderCustomer.getFlightOrderTripDetail().size()>0){
			for(FlightOrderTripDetail  orderTripDetail:flightOrderCustomer.getFlightOrderTripDetail()){
				ReportData data=new ReportData();
				data.setFlight_number(orderTripDetail.getFlightNumber());
				String route=orderTripDetail.getOriginCode()+"-"+orderTripDetail.getDestinationCode();
				data.setRoute(route);
				data.setTake_off(DateConversion.convertTimestampToString(orderTripDetail.getDepartureTime()));
				data.setLanding(DateConversion.convertTimestampToString(orderTripDetail.getArrivalTime()));
				data.set_class(orderTripDetail.getClassOfService());
				data.setTripType(orderTripDetail.getTripType());
				data.setTrips(orderTripDetail.getTrips());
				flightInfo.add(data);
			}
		}
		if(flightOrderCustomer!=null && flightOrderCustomer.getFlightOrderCustomerList()!=null && flightOrderCustomer.getFlightOrderCustomerList().size()>0 &&  flightOrderCustomer.getFlightOrderCustomerPriceBreakup()!=null && flightOrderCustomer.getFlightOrderCustomerPriceBreakup().size()>0){
			for(int i=0;i<flightOrderCustomer.getFlightOrderCustomerList().size();i++){
				FlightOrderCustomer  customer=flightOrderCustomer.getFlightOrderCustomerList().get(i);
				FlightOrderCustomerPriceBreakup  PriceBreakup=flightOrderCustomer.getFlightOrderCustomerPriceBreakup().get(i);
				ReportData data=new ReportData();
				data.setName(customer.getFirstName());
				data.setSurname(customer.getLastName());
				data.setGender(customer.getGender());
				data.setMobile(customer.getMobile());
				if(customer.getPassportExpiryDate()!=null && !customer.getPassportExpiryDate().equals(""))
					data.setPassportExpDate(DateConversion.convertDateToStringToDate(customer.getPassportExpiryDate()));
				data.setPhone(customer.getPhone());
				data.setPrice(PriceBreakup.getBaseFare().setScale(2, BigDecimal.ROUND_UP));
				data.setAgentCom(PriceBreakup.getMarkup());
				data.setTotal(PriceBreakup.getTotal().setScale(2, BigDecimal.ROUND_UP));
				data.setTax(PriceBreakup.getTax().setScale(2, BigDecimal.ROUND_UP));
				if(customer.getBirthday()!=null && !customer.getBirthday().equalsIgnoreCase(""))
					data.setBirth(DateConversion.getBluestarDate(customer.getBirthday()));
				passList.add(data);
			}
		}

		if(paymentData!=null && paymentData.getPaymentTransactionDetail()!=null && paymentData.getPaymentTransactionDetail().size()>0)
		{
			for(PaymentTransaction paymentTransaction:paymentData.getPaymentTransactionDetail()){
				ReportData payment=new ReportData();
				payment.setC_dAmnt(paymentTransaction.getAmount().setScale(2, BigDecimal.ROUND_UP));
				payment.setPaymentStatus(paymentTransaction.getPayment_status());
				payment.setPaymentMethod(paymentTransaction.getPayment_method());
				payment.setTxId(paymentTransaction.getTransactionId());
				payment.setResMsg(paymentTransaction.getResponse_message());
				paymentInfo.add(payment);
			}
		} 
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public FlightReportPage getModel() {
		// TODO Auto-generated method stub
		return flightReportPage;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public List<ReportData> getPassList() {
		return passList;
	}
	public void setPassList(List<ReportData> passList) {
		this.passList = passList;
	}
	public List<ReportData> getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(List<ReportData> paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public ReportData getAgentTotalOrderCommObj() {
		return agentTotalOrderCommObj;
	}
	public void setAgentTotalOrderCommObj(ReportData agentTotalOrderCommObj) {
		this.agentTotalOrderCommObj = agentTotalOrderCommObj;
	}
	public List<ReportData> getCompanyFlightOrderList() {
		return companyFlightOrderList;
	}
	public void setCompanyFlightOrderList(List<ReportData> companyFlightOrderList) {
		this.companyFlightOrderList = companyFlightOrderList;
	}
	public ReportData getReportData() {
		return ReportData;
	}
	public void setReportData(ReportData reportData) {
		ReportData = reportData;
	}
	public List<ReportData> getFlightInfo() {
		return flightInfo;
	}
	public void setFlightInfo(List<ReportData> flightInfo) {
		this.flightInfo = flightInfo;
	}
	public List<CreditNote> getCreditNoteList() {
		return creditNoteList;
	}
	public void setCreditNoteList(List<CreditNote> creditNoteList) {
		this.creditNoteList = creditNoteList;
	}
	public List<Airlinelist> getAirlineList() {
		return airlineList;
	}
	public void setAirlineList(List<Airlinelist> airlineList) {
		this.airlineList = airlineList;
	}
	public boolean getIsCreditIssued() {
		return isCreditIssued;
	}
	public void setIsCreditIssued(boolean isCreditIssued) {
		this.isCreditIssued = isCreditIssued;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public FlightReportPage getFlightReportPage() {
		if(flightReportPage == null)
		{
			flightReportPage = new FlightReportPage();
		}
		return flightReportPage;
	}

	public void setFlightReportPage(FlightReportPage flightReportPage) {
		this.flightReportPage = flightReportPage;
	}



	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}



	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}
}

