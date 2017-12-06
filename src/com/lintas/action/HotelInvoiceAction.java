package com.lintas.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.Filter;
import com.isl.admin.filter.FlightInvoiceFilter;
import com.isl.admin.page.FlightInvoicePage;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.HotelInvoiceDao;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.HotelInvoiceData;
import com.lintas.admin.model.InvoiceData;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.utility.InvoiceFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class HotelInvoiceAction extends ActionSupport implements ModelDriven<FlightInvoicePage>, SessionAware{
	/**
	 * @author info raham
	 * created date:17/10/2015
	 */ 
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	HotelInvoiceDao hotelInvoiceDao =new HotelInvoiceDao();
	HotelOrderRow hotelOrderRow=new HotelOrderRow();
	HotelInvoiceData hotelInvObj = new HotelInvoiceData();
	List<HotelOrderRow> hotelCustomerInvoiceList=null;
	FlightInvoicePage flightInvoicePage=new FlightInvoicePage();
	InvoiceFilter invoiceFilter=new InvoiceFilter();
	CompanyDAO companyDao = new CompanyDAO();
	private String fromDate;
	private String toDate;
	private String period;
	private String filterCompanyType;
	private String userId;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private List<HotelOrderRow> HotelCustomerInvoiceList;
	private InvoiceData agentTotalCommObj = new InvoiceData();
	private List<WalletAmountTranferHistory> agentWalletTxDetails;
	int invoiceType = InvoiceData.MY_INVOICE;

	public int getInvoiceType() {
		return invoiceType;
	}


	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelInvoiceAction.class);
	/*	get All End user Booking list  for generate invoice */
	public String getCustomerHotelOrderInvoiceList(){
		Company companySessionObj=(Company)sessionMap.get("Company");
		User userSessionObj = (User)sessionMap.get("User");
		setInvoiceType(InvoiceData.CUSTOMER_INVOICE);
		FlightInvoiceFilter flightInvoiceFilter = flightInvoicePage.getFlightInvoiceFilter();
		flightInvoiceFilter.setLoginCompany(companySessionObj);
		flightInvoiceFilter.setLoginUser(userSessionObj);
		flightInvoicePage.setFlightInvoiceFilter(flightInvoiceFilter);
		FlightInvoicePage newFlightInvoicePage =hotelInvoiceDao.getCompanyHotelCustomerOrderListTest(flightInvoicePage, getInvoiceType());
		if(newFlightInvoicePage!=null && newFlightInvoicePage.getItems()!=null && newFlightInvoicePage.getItems().size()>0){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.HOTEL_ORDER_INVOICE_LIST.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
			logger.info("------FlightCustomerInvoiceList---------"+ newFlightInvoicePage.getItems().size());
			flightInvoicePage=newFlightInvoicePage;
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.HOTEL_BOOKINGS_CUSTOMER_INVOICE, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;
	}

	public String getAffiliateHotelOrderInvoiceList(){
		Company companySessionObj=(Company)sessionMap.get("Company");
		User userSessionObj = (User)sessionMap.get("User");
		setInvoiceType(InvoiceData.AFFILIATE_INVOICE);
		FlightInvoiceFilter flightInvoiceFilter = flightInvoicePage.getFlightInvoiceFilter();
		flightInvoiceFilter.setLoginCompany(companySessionObj);
		flightInvoiceFilter.setLoginUser(userSessionObj);
		if(flightInvoiceFilter.getReportType()==Filter.REPORTS_MINE){
			flightInvoiceFilter.setReportType(Filter.REPORTS_ALL);
		}
		flightInvoicePage.setFlightInvoiceFilter(flightInvoiceFilter);

		FlightInvoicePage newFlightInvoicePage =hotelInvoiceDao.getCompanyHotelCustomerOrderListTest(flightInvoicePage, getInvoiceType());
		if(newFlightInvoicePage!=null && newFlightInvoicePage.getItems()!=null && newFlightInvoicePage.getItems().size()>0){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.AFFILIATE_HOTEL_ORDER_INVOICE_LIST.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();	
			logger.info("------FlightCustomerInvoiceList---------"+ newFlightInvoicePage.getItems().size());
			flightInvoicePage=newFlightInvoicePage;
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.HOTEL_BOOKINGS_AGENT_COMMISSION_INVOICE, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;
	}


	public String getMyHotelInvoiceList(){

		Company companySessionObj=(Company)sessionMap.get("Company");
		User userSessionObj = (User)sessionMap.get("User");
		setInvoiceType(InvoiceData.MY_INVOICE);
		FlightInvoiceFilter flightInvoiceFilter = flightInvoicePage.getFlightInvoiceFilter();
		flightInvoiceFilter.setLoginCompany(companySessionObj);
		flightInvoiceFilter.setLoginUser(userSessionObj);
		flightInvoicePage.setFlightInvoiceFilter(flightInvoiceFilter);
		FlightInvoicePage newFlightInvoicePage =hotelInvoiceDao.getCompanyHotelCustomerOrderListTest(flightInvoicePage, getInvoiceType());
		if(newFlightInvoicePage!=null && newFlightInvoicePage.getItems()!=null && newFlightInvoicePage.getItems().size()>0){
			logger.info("------FlightCustomerInvoiceList---------"+ newFlightInvoicePage.getItems().size());
			flightInvoicePage=newFlightInvoicePage;
		}
		return SUCCESS;

	}



	/*generate invoice based on order id */
	public String generateHotelCustomerInvoice(){
		logger.info("------GET ID---------"+flightInvoicePage.getId());
		logger.info("------ORDER ID---------"+flightInvoicePage.getOrderReference());
		logger.info("------ORDER ID---------"+flightInvoicePage.getCompanyId());
		hotelInvObj = hotelInvoiceDao.getHoteltOrderCustomerDetailsForInvoice(flightInvoicePage);
		logger.info("------getFirstName---------"+hotelInvObj.getFirstName());

		if(hotelInvObj!=null){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.HOTEL_CUSTOMER_INVOICE.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
			
			hotelInvObj.setHotelOrderGuest(hotelInvoiceDao.getHotelGuestInfo());
		}
		User user = (User)sessionMap.get("User");
		HistoryInfo	historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.HOTEL_BOOKINGS_CUSTOMER_INVOICE.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);

		return SUCCESS;
	}

	/*this method for filter agent Commission Invoice based on Agent and date fields */
	public String searchSuperHotelAgentCommInvoiceList(){
		logger.info("-----------------selected company type---------------"+getFilterCompanyType());
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		invoiceFilter.setFilterCompanyType(getFilterCompanyType());
		invoiceFilter.setFromDate(getFromDate());
		invoiceFilter.setToDate(getToDate());
		invoiceFilter.setPeriod(getPeriod());
		BigDecimal totComm =new BigDecimal("0") ;
		BigDecimal totSpentAmount =new BigDecimal("0");

		HotelCustomerInvoiceList = hotelInvoiceDao.getCompanyInvoiceByComapnyType(userSessionObj, companySessionObj, invoiceFilter);
		logger.info("-----------------companyInvoiceSearchList size---------------"+HotelCustomerInvoiceList.size());
		if(HotelCustomerInvoiceList!=null && HotelCustomerInvoiceList.size()>0){
			for(HotelOrderRow orderRow:HotelCustomerInvoiceList){
				totComm=totComm.add(orderRow.getMarkupAmount());
				totSpentAmount=totSpentAmount.add(orderRow.getFinalPrice());
			}
			logger.info("-----totAgentComm------"+totComm);
			logger.info("-----totAgentSpent------"+totSpentAmount);
			BigDecimal totAmount=totSpentAmount.add(totComm);
			logger.info("-----totAgentComm + totAgentSpent------"+totAmount);
			agentTotalCommObj.setTotAmountSpent(totSpentAmount);
			agentTotalCommObj.setTotAgentComm(totComm);
			agentTotalCommObj.setTotAmount(totAmount);

		}

		return SUCCESS;

	}

	/*generate invoice based on order id  for Agent*/
	public String generateHotelAgentCommissionInvoice(){		
		logger.info("------invoice type------"+getInvoiceType());		
		logger.info("------GET ID---------"+flightInvoicePage.getId());
		/*logger.info("------getOrderReference---------"+flightInvoicePage.getOrderReference());
		logger.info("------Created By Agent ID---------"+flightInvoicePage.getUserId());*/
		Company loginompany = (Company)sessionMap.get("Company");
		User loginUser = (User)sessionMap.get("User");
		hotelOrderRow=hotelInvoiceDao.getHotelOrderRowDetails(flightInvoicePage.getId());
		if(hotelOrderRow != null && loginompany!= null && loginUser != null)
		{			
			//invObj.setInvoiceType(InvoiceData.AFFILIATE_INVOICE);			
			logger.info("------ORDER user id---------"+flightInvoicePage.getUserId());
			Integer bookingUserId = Integer.valueOf(hotelOrderRow.getUserId());
			User bookingUser = companyDao.getUserByUserId(hotelOrderRow.getUserId());		
			Company invoiceCompany = loginompany;
			Company parentCompany = companyDao.getParentCompany(invoiceCompany);			
			if(parentCompany == null)
				return SUCCESS;			
			User invoiceUser = loginUser;	
			if(getInvoiceType() == InvoiceData.AFFILIATE_INVOICE)
			{
				invoiceCompany = companyDao.getImmediateChildCompanyBooked(loginompany, bookingUserId);
				invoiceUser = companyDao.getCompanyWalletUser(invoiceCompany);
				parentCompany = loginompany;
			}			
			logger.info("------parent company--------"+parentCompany.toString());
			logger.info("------invoicing company--------"+invoiceCompany.toString());
			logger.info("------invoicing user--------"+invoiceUser.toString());
			HashMap<String, BigDecimal> companyMarkUpCommissionsMap = hotelInvoiceDao.getHotelOrderInvoiceDetails(hotelOrderRow.getOrderReference(), invoiceUser, bookingUser);
			for (Map.Entry<String, BigDecimal> entry : companyMarkUpCommissionsMap.entrySet()) {
				logger.info(entry.getKey()+" : "+entry.getValue());
				logger.info(entry.getKey()+" : "+entry.getValue());
			}			
			hotelInvObj = hotelInvoiceDao.getHoteltOrderCustomerDetailsForInvoice(hotelOrderRow, getInvoiceType(), companyMarkUpCommissionsMap);
			hotelInvObj.setInvoiceCompanies(invoiceCompany, parentCompany, getInvoiceType());	

			logger.info("size.........."+hotelInvObj.getAgentWalletTxDetails().size());
			logger.info("------getFirstName---------"+hotelInvObj.getFirstName());
			hotelInvObj.setHotelOrderGuest(hotelInvoiceDao.getHotelGuestInfo());
			if(hotelInvObj.getAgentWalletTxDetails()!=null){
				statusCode = ActionStatusEnum.SUCCESS.getCode();
				actionId=BrowsingOptionActionEnum.HOTEL_AGENT_COMMISION_INVOICE.getId();
				detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();		
				agentWalletTxDetails=hotelInvObj.getAgentWalletTxDetails();
			}			
		}
		User user = (User)sessionMap.get("User");
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.HOTEL_BOOKINGS_AGENT_COMMISSION_INVOICE.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
		return SUCCESS;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public FlightInvoicePage getModel() {
		// TODO Auto-generated method stub
		return flightInvoicePage;
	}


	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}


	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}


	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}


	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getFilterCompanyType() {
		return filterCompanyType;
	}
	public void setFilterCompanyType(String filterCompanyType) {
		this.filterCompanyType = filterCompanyType;
	}
	public List<HotelOrderRow> getHotelCustomerInvoiceList() {
		return HotelCustomerInvoiceList;
	}
	public void setHotelCustomerInvoiceList(
			List<HotelOrderRow> hotelCustomerInvoiceList) {
		HotelCustomerInvoiceList = hotelCustomerInvoiceList;
	}
	public HotelInvoiceData getHotelInvObj() {
		return hotelInvObj;
	}
	public void setHotelInvObj(HotelInvoiceData hotelInvObj) {
		this.hotelInvObj = hotelInvObj;
	}
	public InvoiceData getAgentTotalCommObj() {
		return agentTotalCommObj;
	}
	public void setAgentTotalCommObj(InvoiceData agentTotalCommObj) {
		this.agentTotalCommObj = agentTotalCommObj;
	}
	public List<WalletAmountTranferHistory> getAgentWalletTxDetails() {
		return agentWalletTxDetails;
	}
	public void setAgentWalletTxDetails(List<WalletAmountTranferHistory> agentWalletTxDetails) {
		this.agentWalletTxDetails = agentWalletTxDetails;
	}
	public FlightInvoicePage getFlightInvoicePage() {
	 
		return flightInvoicePage;
	}

	public void setFlightInvoicePage(FlightInvoicePage flightInvoicePage) {
		this.flightInvoicePage = flightInvoicePage;
	}


}
