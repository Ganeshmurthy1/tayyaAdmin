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

import com.isl.admin.filter.Filter;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.InvoiceDao;
import com.lintas.admin.flight.model.FlightCommissionReport;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.InvoiceData;
import com.lintas.admin.model.User;
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

public class FlightAgentCommissionInvoiceAction extends ActionSupport implements ModelDriven<FlightReportPage>, SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	CompanyDAO companyDao = new CompanyDAO();
	InvoiceDao invoiceDao =new InvoiceDao();
	//FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
	FlightReportPage flightReportPage=new FlightReportPage();
	
	
	FlightOrderDao  flightOrderDao=new FlightOrderDao();
	private String fromDate;
	private String toDate;
	private String period;
	private String companyType;
	private String userId;
	private List<FlightCommissionReport> agentCommInvoiceList;
	private InvoiceData invObj;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private List<InvoiceData> invoiceDetails;
	private InvoiceData intlTax;
	private int invoiceType = InvoiceData.MY_INVOICE;
	
	private List<Airlinelist> airlineList ;
 public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightAgentCommissionInvoiceAction.class);
	public String getFlightAgentCommissionInvoiceList(){
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		invoiceType = InvoiceData.AFFILIATE_INVOICE;
		FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
		flightReportFilter.setLoginCompany(companySessionObj);
		flightReportFilter.setLoginUser(userSessionObj);
		if(flightReportFilter.getReportType()==Filter.REPORTS_MINE){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.FLIGHT_AGENT_COMMISSION_REPORT.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();	
			flightReportFilter.setReportType(Filter.REPORTS_ALL);
		}
		 
		flightReportPage.setFlightReportFilter(flightReportFilter);
		FlightReportPage newFlightReportPage=invoiceDao.getFlightAgentCommissionInvoiceList(flightReportPage,invoiceType);
		if(newFlightReportPage!=null) 
		flightReportPage=newFlightReportPage;
		airlineList =   new CountryDao().getAirlineList();
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.FLIGHT_BOOKINGS_AGENT_COMMISSION_INVOICE, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		
		return SUCCESS;
	}
	public String getFlightMyCommissionInvoiceList(){
		
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		invoiceType = InvoiceData.MY_INVOICE;
		FlightReportFilter flightReportFilter = flightReportPage.getFlightReportFilter();
		flightReportFilter.setLoginCompany(companySessionObj);
		flightReportFilter.setLoginUser(userSessionObj);
		flightReportPage.setFlightReportFilter(flightReportFilter);
		FlightReportPage newFlightReportPage=invoiceDao.getFlightAgentCommissionInvoiceList(flightReportPage,invoiceType);
		if(newFlightReportPage!=null) 
		flightReportPage=newFlightReportPage;
		setInvoiceType(invoiceType);
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.MY_DEAL_SHEET, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;
	}

	/*generate invoice based on order id  for Agent*/
	public String generateAgentCommissionInvoice(){
		logger.info("------GET ID---------"+flightReportPage.getId());
		logger.info("------ORDER ID---------"+flightReportPage.getOrderId());
		GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();

		FlightOrderRow 	flightOrderRow =flightOrderDao.getFlightOrderRowDataById(flightReportPage.getId());
		Company loginompany = (Company)sessionMap.get("Company");
		User loginUser = (User)sessionMap.get("User");
		if(flightOrderRow != null && loginompany!= null && loginUser != null)
		{			
			//invObj.setInvoiceType(InvoiceData.AFFILIATE_INVOICE);
			logger.info("------ORDER ID---------"+flightOrderRow.getOrderId());
			invoiceDetails = new ArrayList<InvoiceData>();

			logger.info("------ORDER user id---------"+flightOrderRow.getUserId());
			Integer bookingUserId = Integer.valueOf(flightOrderRow.getUserId());
			User bookingUser = companyDao.getUserByUserId(flightOrderRow.getUserId());		
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
			//Company immediateChildCompanyBooking = companyDao.getImmediateChildCompanyBooked(invoiceCompany, bookingUserId);
			HashMap<String, BigDecimal> companyMarkUpCommissionsMap = invoiceDao.getFlightOrderInvoiceDetails(flightOrderRow.getOrderId(), invoiceUser, bookingUser);
			invObj=invoiceDao.getFlightOrderCustomerDetailsForInvoice(flightOrderRow, companyMarkUpCommissionsMap, getInvoiceType());
			invObj.setInvoiceCompanies(invoiceCompany, parentCompany, getInvoiceType());	
			for (Map.Entry<String, BigDecimal> entry : companyMarkUpCommissionsMap.entrySet()) {
				logger.info(entry.getKey()+" : "+entry.getValue());
				logger.info(entry.getKey()+" : "+entry.getValue());
			}
			BigDecimal invoiceCompanyMarkup = (companyMarkUpCommissionsMap.get("urMarkup") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("urMarkup");		
			BigDecimal masterMarkup = (companyMarkUpCommissionsMap.get("masterMarkup") == null)?new BigDecimal("0.0"):companyMarkUpCommissionsMap.get("masterMarkup");
			//
			if(getInvoiceType() == InvoiceData.AFFILIATE_INVOICE)
			{
				masterMarkup = masterMarkup.add(invoiceCompanyMarkup);				
			}
			//BigDecimal invoiceMarkupTotal = invoiceCompanyMarkup.add(masterMarkup);
			BigDecimal masterMarkupBookingCurrency = masterMarkup.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			BigDecimal gstOnMasterMarkupBookingCurrency = new BigDecimal("0.00");
			try{
				gstOnMasterMarkupBookingCurrency = masterMarkupBookingCurrency.multiply(new BigDecimal(6)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP);
				logger.info("gstOnMasterMarkupBookingCurrency  "+gstOnMasterMarkupBookingCurrency);	
			}
			catch(ArithmeticException ex)
			{
				gstOnMasterMarkupBookingCurrency = masterMarkupBookingCurrency.multiply(new BigDecimal(6)).divide(new BigDecimal(100), 7, RoundingMode.HALF_UP).setScale(2, BigDecimal.ROUND_UP);
				logger.info("##### ArithmeticException gstOnMasterMarkupBookingCurrency  "+gstOnMasterMarkupBookingCurrency);	
			}
			BigDecimal invoiceMarkupTotal = masterMarkup.add(gstOnMasterMarkupBookingCurrency);
			logger.info("------getTripParticulars---------"+invObj.getTripParticulars().size());
			if(invObj!=null){
				statusCode = ActionStatusEnum.SUCCESS.getCode();
				actionId=BrowsingOptionActionEnum.FLIGHT_AGENT_COMMISION_INVOICE.getId();
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
			//removed by ram....
			//invObj = invoiceDao.getFlightOrderCustomerDetailsForAgentInvoice(flightCommissionReport);

			if(invObj!=null){
				if(invObj.getAgentWalletTxDetails()!=null && invObj.getTxDetails()!=null){
				}
				else if(invObj.getAgentWalletTxDetails()!=null && invObj.getTxDetails()==null){
				}
				else if(invObj.getAgentWalletTxDetails()==null && invObj.getTxDetails()!=null){
				}
				logger.info("------getAgentWalletTxDetails LIst---------"+invObj.getAgentWalletTxDetails().size());
				logger.info("------getTxDetails LIst---------"+invObj.getTxDetails().size());
				logger.info("------getTripParticulars---------"+invObj.getTripParticulars().size());

			}
			FlightOrderRow 	orderRow = flightOrderDao.getFlightOrderRowDataById(flightReportPage.getId());
			logger.info("gstSwithOn Value---------------"+gstPropertiesFile.getGstSwitchonValue());
			if(gstPropertiesFile.getGstSwitchonValue()!=null){
				if(gstPropertiesFile.getGstSwitchonValue().equals("lintas")){
					invoiceDetails = new ArrayList<InvoiceData>();
					Map<String,Integer> passengerMap= getPassengerMap(invObj);
					Map<String,BigDecimal> myMap= new HashMap<String, BigDecimal>();
					List<String> passList=new ArrayList<String>();
					String customerNames="";
					InvoiceData newObj=null;
					String D8tax="0.00";
					String Mytax="0.00";
					BigDecimal myTax=new BigDecimal("0.00");
					BigDecimal d8tax=new BigDecimal("0.00");
					if(invObj.getOrderCustomerParticulars()!=null){


						BigDecimal invoiceMarkupBreakUp = new BigDecimal("0.00");
						try{
							invoiceMarkupBreakUp = invoiceMarkupTotal.divide(new BigDecimal(invObj.getOrderCustomerParticulars().size()));							
							logger.info("invoiceMarkupBreakUp  "+invoiceMarkupBreakUp);	
						}
						catch(ArithmeticException ex)
						{
							invoiceMarkupBreakUp = invoiceMarkupTotal.divide(new BigDecimal(invObj.getOrderCustomerParticulars().size()), 7, RoundingMode.HALF_UP);
							logger.info("##### ArithmeticException invoiceMarkupBreakUp  "+invoiceMarkupBreakUp);	
						}

						for(int i=0;i<invObj.getOrderCustomerParticulars().size();i++){
							FlightOrderCustomer  customer=invObj.getOrderCustomerParticulars().get(i);
							FlightOrderCustomerPriceBreakup  priceBreakup=invObj.getPriceBreakupParticulars().get(i);
							newObj=new InvoiceData();
							BigDecimal brakupMarkup=new BigDecimal(priceBreakup.getMarkup());
							/*if(!passList.contains(priceBreakup.getDescription())){
							newObj=new InvoiceData();

						}
						if(passList.contains(priceBreakup.getDescription())){
							//logger.info(i+"customerNames b4 :"+customerNames);
							customerNames=customerNames+"/"+customer.getFirstName();
							// logger.info(i+"customerNames :"+customerNames);
							newObj.setFirstName(customerNames);
							 continue;
						}*/

							//modified by ram...
							//BigDecimal basePriceMarkupInBooking=invoiceMarkupBreakUp.multiply(orderRow.getBaseToBookingExchangeRate());
							BigDecimal invoiceMarkupBreakup = (priceBreakup.getMarkup() == null || priceBreakup.getMarkup().equals("0.00"))?new BigDecimal("0.00"):invoiceMarkupBreakUp.multiply(orderRow.getBaseToBookingExchangeRate());

							//BigDecimal basePriceMarkupInBooking=new BigDecimal(priceBreakup.getMarkup()).multiply(orderRow.getBaseToBookingExchangeRate());

							//logger.info("basePriceMarkupInBooking---invoice------"+invoiceMarkupBreakup);							
							//logger.info("basePriceMarkupInBooking---total------"+new BigDecimal(priceBreakup.getMarkup()).multiply(orderRow.getBaseToBookingExchangeRate()));


							BigDecimal basePrice= priceBreakup.getBaseFare().multiply(orderRow.getApiToBaseExchangeRate());

							BigDecimal basePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate());


							customerNames=customer.getFirstName()+"/"+customer.getLastName();
							newObj.setFirstName(customerNames);
							//	BigDecimal procesiingFeee=new BigDecimal("0.00");
							/*BigDecimal basePrice= priceBreakup.getBaseFare().multiply(orderRow.getApiToBaseExchangeRate());
						logger.info("basePrice---------"+basePrice);
						BigDecimal basePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate());
						logger.info("basePriceInBooking---------"+basePriceInBooking);*/
							BigDecimal totalBasePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate())/*.multiply(new BigDecimal(passengerMap.get(priceBreakup.getDescription())))*/;
							BigDecimal taxes= priceBreakup.getTax().multiply(orderRow.getApiToBaseExchangeRate());
							BigDecimal taxesInBooking=taxes.multiply(orderRow.getBaseToBookingExchangeRate());

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

								BigDecimal airportTax=new BigDecimal("0.00");
								if(D8tax!=null && !D8tax.equals("0.00")){
									airportTax=taxesInBooking.subtract(new BigDecimal(D8tax).add(new BigDecimal(Mytax)));

									d8tax=d8tax.add(new BigDecimal(D8tax));
									myMap.put(priceBreakup.getDescription(), new BigDecimal(Mytax));
									myTax=myTax.add(new BigDecimal(Mytax));
								}else{
									statusCode = ActionStatusEnum.FAILED.getCode();
									actionId=BrowsingOptionActionEnum.FLIGHT_AGENT_COMMISSION_REPORT.getId();
									detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();	
									airportTax=taxesInBooking;
								}
								BigDecimal totalAirportTax=airportTax;/*.multiply(new BigDecimal(passengerMap.get(priceBreakup.getDescription())));*/

								//modified by ram
								BigDecimal totalPriceInBooking=basePriceInBooking.add(invoiceMarkupBreakup);
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

							if(D8tax!=null &&!D8tax.equals("0.00")){

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
					}

				}
				if(gstPropertiesFile.getGstSwitchonValue().equals("tayyarah")){
					invoiceDetails=new ArrayList<InvoiceData>();
					Map<String,Integer> passengerMap= getPassengerMap(invObj);
					//Map<String,BigDecimal> myMap= new HashMap<String, BigDecimal>();
					List<String> passList=new ArrayList<String>();
					String customerNames="";
					InvoiceData newObj=null;
					for(int i=0;i<invObj.getOrderCustomerParticulars().size();i++){
						FlightOrderCustomer  customer=invObj.getOrderCustomerParticulars().get(i);
						FlightOrderCustomerPriceBreakup  priceBreakup=invObj.getPriceBreakupParticulars().get(i);
						newObj=new InvoiceData();
						//BigDecimal brakupMarkup=new BigDecimal(priceBreakup.getMarkup());
						/*if(!passList.contains(priceBreakup.getDescription())){
					newObj=new InvoiceData();

				}
				if(passList.contains(priceBreakup.getDescription())){
					//logger.info(i+"customerNames b4 :"+customerNames);
					customerNames=customerNames+"/"+customer.getFirstName();
					// logger.info(i+"customerNames :"+customerNames);
					newObj.setFirstName(customerNames);
					continue;
				}*/
						customerNames=customer.getFirstName()+"/"+customer.getLastName();
						newObj.setFirstName(customerNames);
						//	BigDecimal procesiingFeee=new BigDecimal("0.00");

						BigDecimal basePriceMarkupInBooking=new BigDecimal(priceBreakup.getMarkup()).multiply(orderRow.getBaseToBookingExchangeRate());
						BigDecimal basePrice= priceBreakup.getBaseFare().multiply(orderRow.getApiToBaseExchangeRate());
						BigDecimal basePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate());
						BigDecimal totalBasePriceInBooking=basePrice.multiply(orderRow.getBaseToBookingExchangeRate())/*.multiply(new BigDecimal(passengerMap.get(priceBreakup.getDescription())))*/;
						BigDecimal taxes= priceBreakup.getTax().multiply(orderRow.getApiToBaseExchangeRate());
						BigDecimal taxesInBooking=taxes.multiply(orderRow.getBaseToBookingExchangeRate());

						String taxDescription=priceBreakup.getTax_description();/*"D8:6.00;MY:7.00;F2:2.00;IN:49.00;JN:48.00;WO:16.00;YQ:401.00;YR:9.00;"*/;
						String temp=taxDescription;
						BigDecimal airportTax=new BigDecimal("0.00");
						airportTax=taxesInBooking;
						BigDecimal totalAirportTax=airportTax/*.multiply(new BigDecimal(passengerMap.get(priceBreakup.getDescription())))*/;
						BigDecimal totalPriceInBooking=basePriceInBooking.add(basePriceMarkupInBooking);
						newObj.setMobile(customer.getMobile());
						newObj.setQty(passengerMap.get(priceBreakup.getDescription()));
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
		HistoryInfo	historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.FLIGHT_BOOKINGS_AGENT_COMMISSION_INVOICE.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);

		return SUCCESS;
	}
	/*this method for filter agent Commission Invoice based on Agent and date fields */
	public String searchSuperAgentCommInvoiceList(){
		logger.info("-----------------selected company type---------------"+getCompanyType());
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		InvoiceFilter invoiceFilter=new InvoiceFilter();

		invoiceFilter.setFilterCompanyType(getCompanyType());
		invoiceFilter.setFromDate(getFromDate());
		invoiceFilter.setToDate(getToDate());
		invoiceFilter.setPeriod(getPeriod());

		List<FlightCommissionReport> companyInvoiceSearchList = invoiceDao.getAgentInvoiceByComapnyType(userSessionObj, companySessionObj, invoiceFilter);
		logger.info("-----------------companyInvoiceSearchList size---------------"+companyInvoiceSearchList.size());
		if(companyInvoiceSearchList!=null && companyInvoiceSearchList.size()>0){
			agentCommInvoiceList=companyInvoiceSearchList;
		}

		return SUCCESS;

	}
	public Map<String,Integer> getPassengerMap(InvoiceData invObj){
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public List<FlightCommissionReport> getAgentCommInvoiceList() {
		return agentCommInvoiceList;
	}

	public void setAgentCommInvoiceList(
			List<FlightCommissionReport> agentCommInvoiceList) {
		this.agentCommInvoiceList = agentCommInvoiceList;
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
	public int getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}
	public List<Airlinelist> getAirlineList() {
		return airlineList;
	}
	public void setAirlineList(List<Airlinelist> airlineList) {
		this.airlineList = airlineList;
	}
}
