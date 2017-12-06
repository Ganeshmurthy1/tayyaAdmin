package com.lintas.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.FlightInvoiceFilter;
import com.isl.admin.page.FlightInvoicePage;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.InvoiceDao;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.InvoiceData;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.utility.GstPropertiesFile;
import com.lintas.utility.InvoiceFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class FlightInvoiceAction extends ActionSupport implements ModelDriven<FlightInvoicePage>, SessionAware{
	/**
	 * @author info raham
	 * created date:17/10/2015
	 */ 
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	CompanyDAO companyDao = new CompanyDAO();
	FlightInvoicePage flightInvoicePage=new FlightInvoicePage();
	InvoiceDao invoiceDao =new InvoiceDao();
	FlightOrderRow flightOrderRow=new FlightOrderRow();
	InvoiceData invObj=new InvoiceData();
	List<FlightOrderRow> invoiceList=null;
	List<FlightOrderRow> superInvoiceFilterList=new ArrayList<FlightOrderRow>();
	List<FlightOrderRow> superAgentInvoiceFilterList=new ArrayList<FlightOrderRow>();
	InvoiceFilter invoiceFilter=new InvoiceFilter();
	FlightOrderDao  flightOrderDao=new FlightOrderDao();
	private String fromDate;
	private String toDate;
	private String period;
	private String filterCompanyType;
	private String userId;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	//private List<FlightOrderRow> companyCustomerInvoiceList;
	private List<InvoiceData> invoiceDetails;
	private InvoiceData intlTax;
	private String tdsType;

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightInvoiceAction.class);
	/*get All End user Booking list  for generate invoice */
	public String getCompanyCustomerOrderInvoiceList(){
		String type=new GstPropertiesFile().getTDSorGSTType();
		setTdsType(type);
		Company companySessionObj=(Company)sessionMap.get("Company");
		User userSessionObj=(User)sessionMap.get("User");

		FlightInvoiceFilter flightInvoiceFilter = flightInvoicePage.getFlightInvoiceFilter();
		flightInvoiceFilter.setLoginCompany(companySessionObj);
		flightInvoiceFilter.setLoginUser(userSessionObj);
		flightInvoicePage.setFlightInvoiceFilter(flightInvoiceFilter);
		FlightInvoicePage newFlightInvoicePage =invoiceDao.getFlightCustomerInvoiceList(flightInvoicePage);
		if(newFlightInvoicePage!=null && newFlightInvoicePage.getItems()!=null && newFlightInvoicePage.getItems().size()>0){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.FLIGHT_CUSTOMER_ORDER_INVOICE_LIST.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
			logger.info("------FlightCustomerInvoiceList---------"+ newFlightInvoicePage.getItems().size());
			flightInvoicePage=newFlightInvoicePage;
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.FLIGHT_BOOKINGS_CUSTOMER_INVOICE, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;
	}

	/*generate invoice based on order id */
	public String generateCustomerInvoice(){
		GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
		logger.info("------GET ID---------"+flightInvoicePage.getId());

		flightOrderRow = flightOrderDao.getFlightOrderRowDataById(flightInvoicePage.getId());

		if(flightOrderRow != null)
		{

			logger.info("------ORDER ID---------"+flightOrderRow.getOrderId());
			invoiceDetails = new ArrayList<InvoiceData>();

			Company loginompany = (Company)sessionMap.get("Company");
			User loginUser = (User)sessionMap.get("User");
			logger.info("------ORDER user id---------"+flightOrderRow.getUserId());
			Integer bookingUserId = Integer.valueOf(flightOrderRow.getUserId());
			User bookingUser = companyDao.getUserByUserId(flightOrderRow.getUserId());		
			Company invoiceCompany = loginompany;
			User invoiceUser = loginUser;		

			HashMap<String, BigDecimal> companyMarkUpCommissionsMap = invoiceDao.getFlightOrderInvoiceDetails(flightOrderRow.getOrderId(), invoiceUser, bookingUser);

			for (Map.Entry<String, BigDecimal> entry : companyMarkUpCommissionsMap.entrySet()) {
				logger.info(entry.getKey()+" : "+entry.getValue());
				logger.info(entry.getKey()+" : "+entry.getValue());
			}
			invObj=invoiceDao.getFlightOrderCustomerDetailsForInvoice(flightOrderRow, companyMarkUpCommissionsMap, InvoiceData.CUSTOMER_INVOICE);
			invObj.setInvoiceCompanies(invoiceCompany, invoiceCompany, InvoiceData.CUSTOMER_INVOICE);	


			logger.info("------getTripParticulars---------"+invObj.getTripParticulars().size());
			if(invObj!=null){
				statusCode = ActionStatusEnum.SUCCESS.getCode();
				actionId=BrowsingOptionActionEnum.FLIGHT_CUSTOMER_INVOICE.getId();
				detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();		
				
				if(invObj.getAgentWalletTxDetails()!=null && invObj.getTxDetails()!=null){
				}
				else if(invObj.getAgentWalletTxDetails()!=null && invObj.getTxDetails()==null){
				}
				else if(invObj.getAgentWalletTxDetails()==null && invObj.getTxDetails()!=null){
				}
				logger.info("------getAgentWalletTxDetails LIst---------"+invObj.getAgentWalletTxDetails().size());
				logger.info("------getTxDetails LIst---------"+invObj.getTxDetails().size());
			}
			FlightOrderRow 	orderRow =flightOrderDao.getFlightOrderRowDataById(flightOrderRow.getId());
			BigDecimal gstOnMarkUp = flightOrderRow.getGst_on_markup();			



			//logger.info("gstSwithOn Value---------------"+gstPropertiesFile.getGstSwitchonValue());
			if(new GstPropertiesFile().getGstSwitchonValue()!=null){
				if(gstPropertiesFile.getGstSwitchonValue().equals("lintas")){
					Map<String,Integer> passengerMap= getPassengerMap();
					Map<String,BigDecimal> myMap= new HashMap<String, BigDecimal>();
					List<String> passList=new ArrayList<String>();
					String customerNames="";
					InvoiceData newObj=null;
					String D8tax="0.00";
					String Mytax="0.00";
					BigDecimal myTax=new BigDecimal("0.00");
					BigDecimal d8tax=new BigDecimal("0.00");
					BigDecimal gstOnMarkUpBreakUp = new BigDecimal("0.00");
					try{
						gstOnMarkUpBreakUp = gstOnMarkUp.divide(new BigDecimal(invObj.getOrderCustomerParticulars().size()));							
						logger.info("gstOnMarkUpBreakUp  "+gstOnMarkUpBreakUp);	
					}
					catch(ArithmeticException ex)
					{
						gstOnMarkUpBreakUp = gstOnMarkUp.divide(new BigDecimal(invObj.getOrderCustomerParticulars().size()), 7, RoundingMode.HALF_UP);
						logger.info("##### ArithmeticException gstOnMarkUpBreakUp  "+gstOnMarkUpBreakUp);	
					}

					for(int i=0;i<invObj.getOrderCustomerParticulars().size();i++){
						FlightOrderCustomer  customer=invObj.getOrderCustomerParticulars().get(i);
						FlightOrderCustomerPriceBreakup  priceBreakup=invObj.getPriceBreakupParticulars().get(i);
						BigDecimal brakupMarkup=new BigDecimal(priceBreakup.getMarkup());
						newObj=new InvoiceData();
						//logger.info("33333333333333-----priceBreakup-"+priceBreakup.toString());							
						customerNames=customer.getFirstName();
						newObj.setFirstName(customerNames);
						BigDecimal basePrice= priceBreakup.getBaseFare().multiply(orderRow.getApiToBaseExchangeRate());
						//modified by ram
						//logger.info("33333333333333-----basePrice-"+basePrice);							
						//logger.info("basePriceMarkupInBooking---priceBreakup.getMarkup()------"+priceBreakup.getMarkup());							
						BigDecimal markupBreakup = new BigDecimal(priceBreakup.getMarkup()).multiply(orderRow.getBaseToBookingExchangeRate());						
						//logger.info("price break up---markupBreakup------"+markupBreakup);
						BigDecimal basePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate());
						BigDecimal totalBasePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate())/*.multiply(new BigDecimal(passengerMap.get(priceBreakup.getDescription())))*/;
						BigDecimal taxes= priceBreakup.getTax().multiply(orderRow.getApiToBaseExchangeRate());
						BigDecimal taxesInBooking=taxes.multiply(orderRow.getBaseToBookingExchangeRate());
						//F2:1.00;G1:1.00;JN:20.00;WO:16.00;
						//MY:65.00;F2:2.00;G1:2.00;IN:152.00;JN:51.00;WO:16.00;D8:3.90;YQ:328.00;
						String taxDescription=priceBreakup.getTax_description();/*"D8:6.00;MY:7.00;F2:2.00;IN:49.00;JN:48.00;WO:16.00;YQ:401.00;YR:9.00;"*/;
						String temp=taxDescription;
						if(temp!=null){
							while(temp.length()>2){
								String fullValue=temp.substring(0,temp.indexOf(";"));
								String taxCode=fullValue.substring(0,fullValue.indexOf(":"));
								temp=temp.substring(fullValue.length()+1);
								if(taxCode.equalsIgnoreCase("D8") ){
									D8tax=fullValue.substring(fullValue.indexOf(":")+1);
								}
								if(taxCode.equalsIgnoreCase("MY") ){
									Mytax=fullValue.substring(fullValue.indexOf(":")+1);
								}
							}
						}
						BigDecimal airportTax=new BigDecimal("0.00");
						if(!D8tax.equals("0.00")){
							airportTax=taxesInBooking.subtract(new BigDecimal(D8tax).add(new BigDecimal(Mytax)));
							d8tax=d8tax.add(new BigDecimal(D8tax));
							myMap.put(priceBreakup.getDescription(), new BigDecimal(Mytax));
							myTax=myTax.add(new BigDecimal(Mytax));
						}else{
							statusCode = ActionStatusEnum.FAILED.getCode();
							actionId=BrowsingOptionActionEnum.FLIGHT_CUSTOMER_INVOICE.getId();
							detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();	
							airportTax=taxesInBooking;
						}
						BigDecimal totalAirportTax=airportTax/*.multiply(new BigDecimal(passengerMap.get(priceBreakup.getDescription())))*/;
						//modified by ram
						BigDecimal totalPriceInBooking=basePriceInBooking.add(markupBreakup.add(gstOnMarkUpBreakUp));
						//BigDecimal totalPriceInBooking=basePriceInBooking.add(basePriceMarkupInBooking);
						newObj.setMobile(customer.getMobile());
						newObj.setQty(1/*passengerMap.get(priceBreakup.getDescription())*/);
						newObj.setPrice(totalPriceInBooking.setScale(2, BigDecimal.ROUND_UP));
						newObj.setTotPrice(totalPriceInBooking.setScale(2, BigDecimal.ROUND_UP));
						newObj.setTax(airportTax.setScale(2, BigDecimal.ROUND_UP));
						newObj.setTotalTax(totalAirportTax.setScale(2, BigDecimal.ROUND_UP));
						invoiceDetails.add(newObj);
						passList.add(priceBreakup.getDescription());
					}
					if(!D8tax.equals("0.00")){
						Set<String> passset=passengerMap.keySet();
						StringBuilder builder=new StringBuilder();
						int totPass=0;
						for(String key:passset){
							String passtype=key;
							//logger.info("passtype-----------"+passtype);						
							totPass=totPass+passengerMap.get(key);
							if(passtype.equalsIgnoreCase("ADT")){
								int noofpass=0;
								noofpass=noofpass+passengerMap.get(passtype);
								builder.append(noofpass+"("+passtype+")");
							}
							if(passtype.equalsIgnoreCase("CNN")){
								int noofpass=0;
								noofpass=noofpass+passengerMap.get(passtype);
								builder.append(noofpass+"("+passtype+")"+"+");
							}
							if(passtype.equalsIgnoreCase("INF")){
								int noofpass=0;
								noofpass=noofpass+passengerMap.get(passtype);
								builder.append(noofpass+"("+passtype+")");
							}						
							BigDecimal MYamount=myMap.get(key);
							if(MYamount!=null){
								MYamount=MYamount.add(new BigDecimal("0.00"));
							}
							else{
								MYamount=new BigDecimal("0.00");
							}						 
							BigDecimal TotalMYamount=MYamount.multiply(new BigDecimal(totPass));
							intlTax=new InvoiceData();
							intlTax.setPassengerType(builder.toString());
							intlTax.setQty(totPass);
							intlTax.setTotalMYamount(TotalMYamount);
							intlTax.setMYamount(MYamount);
						}
					}
				}
				if(gstPropertiesFile.getGstSwitchonValue().equals("tayyarah")){
					Map<String,Integer> passengerMap= getPassengerMap();
					Map<String,BigDecimal> myMap= new HashMap<String, BigDecimal>();
					List<String> passList=new ArrayList<String>();
					String customerNames="";
					InvoiceData newObj=null;
					for(int i=0;i<invObj.getOrderCustomerParticulars().size();i++){
						FlightOrderCustomer  customer=invObj.getOrderCustomerParticulars().get(i);
						FlightOrderCustomerPriceBreakup  priceBreakup=invObj.getPriceBreakupParticulars().get(i);
						newObj=new InvoiceData();
						customerNames=customer.getFirstName();
						newObj.setFirstName(customerNames);
						//	BigDecimal procesiingFeee=new BigDecimal("0.00");
						BigDecimal basePrice= priceBreakup.getBaseFare().multiply(orderRow.getApiToBaseExchangeRate());
						BigDecimal basePriceMarkupInBooking=new BigDecimal(priceBreakup.getMarkup()).multiply(orderRow.getBaseToBookingExchangeRate());
						BigDecimal basePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate());
						BigDecimal totalBasePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate())/*.multiply(new BigDecimal(passengerMap.get(priceBreakup.getDescription())))*/;
						BigDecimal taxes= priceBreakup.getTax().multiply(orderRow.getApiToBaseExchangeRate());
						BigDecimal taxesInBooking=taxes.multiply(orderRow.getBaseToBookingExchangeRate());
						BigDecimal airportTax=new BigDecimal("0.00");
						airportTax=taxesInBooking;
						BigDecimal totalAirportTax=airportTax/*.multiply(new BigDecimal(passengerMap.get(priceBreakup.getDescription())))*/;
						BigDecimal totalPriceInBooking=basePriceInBooking.add(basePriceMarkupInBooking);
						newObj.setMobile(customer.getMobile());
						newObj.setQty(1/*passengerMap.get(priceBreakup.getDescription())*/);
						newObj.setPrice(totalPriceInBooking.setScale(2, BigDecimal.ROUND_UP));
						newObj.setTotPrice(totalPriceInBooking.setScale(2, BigDecimal.ROUND_UP));
						newObj.setTax(airportTax.setScale(2, BigDecimal.ROUND_UP));
						newObj.setTotalTax(totalAirportTax.setScale(2, BigDecimal.ROUND_UP));
						invoiceDetails.add(newObj);
						passList.add(priceBreakup.getDescription());
					}
				}
			}
		}
		else
		{

		}
		User user = (User)sessionMap.get("User");
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.FLIGHT_BOOKINGS_CUSTOMER_INVOICE.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
		return SUCCESS;
	}
	/*generate invoice based on order id  for Agent*/
	public String generateAgentCommissionInvoice(){
		logger.info("------GET ID---------"+flightOrderRow.getId());
		logger.info("------ORDER ID---------"+flightOrderRow.getOrderId());
		logger.info("------Created By Agent ID---------"+flightOrderRow.getUserId());
		invObj=invoiceDao.getFlightOrderCustomerDetailsForInvoice(flightOrderRow);
		invoiceDetails=new ArrayList<InvoiceData>();
		BigDecimal totComm =new BigDecimal("0");

		FlightOrderRow 	orderRow =flightOrderDao.getFlightOrderRowDataById(flightOrderRow.getId());
		for(int i=0;i<invObj.getOrderCustomerParticulars().size();i++){
			FlightOrderCustomer  customer=invObj.getOrderCustomerParticulars().get(i);

			FlightOrderCustomerPriceBreakup  priceBreakup=invObj.getPriceBreakupParticulars().get(i);

			BigDecimal brakupMarkup=new BigDecimal(priceBreakup.getMarkup());
			BigDecimal basePriceMarkupInBooking=new BigDecimal(priceBreakup.getMarkup()).multiply(orderRow.getBaseToBookingExchangeRate());
			//	BigDecimal procesiingFeee=new BigDecimal("0.00");
			BigDecimal basePrice= priceBreakup.getBaseFare().multiply(orderRow.getApiToBaseExchangeRate());
			BigDecimal taxes= priceBreakup.getTax().multiply(orderRow.getApiToBaseExchangeRate()) ;
			BigDecimal totalBasePrice=basePrice.add(brakupMarkup);
			BigDecimal basePriceInBooking=totalBasePrice.multiply(orderRow.getBaseToBookingExchangeRate());
			BigDecimal taxesInBooking=taxes.multiply(orderRow.getBaseToBookingExchangeRate());
			BigDecimal totalPriceInBooking=basePriceInBooking.add(basePriceMarkupInBooking);
			InvoiceData newObj=new InvoiceData();
			newObj.setFirstName(customer.getFirstName());
			newObj.setLastName(customer.getLastName());
			newObj.setMobile(customer.getMobile());
			newObj.setQty(priceBreakup.getQuantity());
			newObj.setPrice(totalPriceInBooking.setScale(2, BigDecimal.ROUND_UP));
			newObj.setTotPrice(totalPriceInBooking.setScale(2, BigDecimal.ROUND_UP));
			newObj.setTax(taxesInBooking.setScale(2, BigDecimal.ROUND_UP));
			invoiceDetails.add(newObj);
		}
		logger.info("------AGENT TOTAL COMMISSION---------"+totComm);

		logger.info("------Invoice getTripParticulars---------"+invObj.getTripParticulars().size());
		logger.info("------Invoice getTxDetails---------"+invObj.getTxDetails().size());
		logger.info("------Invoice getAgentWalletTxDetails---------"+invObj.getAgentWalletTxDetails().size());
		Map<PaymentTransaction,WalletAmountTranferHistory> agentWalletTxHistory=new HashMap<PaymentTransaction, WalletAmountTranferHistory>();
		for(PaymentTransaction paymentTx:invObj.getTxDetails()){
			for(WalletAmountTranferHistory  walletPaymentTx:invObj.getAgentWalletTxDetails()){
				logger.info("------Payment Tx getApi_transaction_id --------"+paymentTx.getApi_transaction_id()+"-----walletPaymentTx REference Id------"+walletPaymentTx.getActionId());
				if(paymentTx.getApi_transaction_id().equalsIgnoreCase(walletPaymentTx.getActionId())){
					agentWalletTxHistory.put(paymentTx, walletPaymentTx);


				}
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

	public Map<String,Integer> getPassengerMap(){
		Map<String,Integer> passengerMap=new HashMap<String, Integer>();

		for(int i=0;i<invObj.getOrderCustomerParticulars().size();i++){

			FlightOrderCustomerPriceBreakup  priceBreakup=invObj.getPriceBreakupParticulars().get(i);


			if(passengerMap.get(priceBreakup.getDescription())!=null){
				passengerMap.put(priceBreakup.getDescription(),passengerMap.get(priceBreakup.getDescription())+1);
			}else{
				passengerMap.put(priceBreakup.getDescription(), 1);
			}

		}

		//logger.info("passengerMap :"+passengerMap);
		return passengerMap;

	}



	public InvoiceData getInvObj() {
		return invObj;
	}

	public void setInvObj(InvoiceData invObj) {
		this.invObj = invObj;
	}

	public List<InvoiceData> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceData> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public InvoiceData getIntlTax() {
		return intlTax;
	}

	public void setIntlTax(InvoiceData intlTax) {
		this.intlTax = intlTax;
	}

	public String getTdsType() {
		return tdsType;
	}

	public void setTdsType(String tdsType) {
		this.tdsType = tdsType;
	}
	public FlightInvoicePage getFlightInvoicePage() {
		if(flightInvoicePage==null){
			flightInvoicePage=new FlightInvoicePage();
		}

		return flightInvoicePage;
	}

	public void setFlightInvoicePage(FlightInvoicePage flightInvoicePage) {
		this.flightInvoicePage = flightInvoicePage;
	}
}
