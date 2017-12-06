package com.lintas.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightBookingKeysTemp;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderCustomerSSR;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class FlightReportAction  extends ActionSupport implements SessionAware,ModelDriven<ReportData>{
	/**
	 * @author info raham
	 * created date : 31st Aug 2015
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightReportAction.class);
	//ReportData reportData = new   ReportData();


	FlightOrderCustomer foc=new FlightOrderCustomer();
	OrderCustomer oc=new OrderCustomer();
	FlightOrderRow  forow=new FlightOrderRow();
	FlightOrderCustomerPriceBreakup focpb=new FlightOrderCustomerPriceBreakup();
	FlightOrderTripDetail fotd=new FlightOrderTripDetail();
	FlightOrderDao ocd=new FlightOrderDao();
	PaymentTransaction pt=new PaymentTransaction();

	FlightOrderDao  flightOrderDao=new FlightOrderDao();
	SessionMap<String , Object> sessionMap;
	List<FlightOrderRow> flightOrderRow_list=null;
	List<FlightOrderCustomerPriceBreakup> flightOrderCustomerPriceBreakups_list=null;
	List<FlightOrderTripDetail> flightOrderTrip_list=null;
	List<FlightOrderCustomer> flightOrderCustomers_list=null;
	List<ReportData> reportData_list=null;
	List<ReportData> reportFilter_list= new ArrayList<ReportData>();
	private List<FlightOrderCustomerSSR> SSRList= null;

	private List<ReportData> FlightReportsList ; 
	private List<ReportData> passList = new ArrayList<ReportData>();
	private List<PaymentTransaction> endUserTxHistory;
	private ReportData txHistory ;
	private ReportData CurrentReportdata = new ReportData() ;
	private List<Airlinelist> airlineList ;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	CountryDao cDAO = new CountryDao();
	/* private FlightReportPage flightReportPage=null;*/
	//private FlightReportFilter flightReportFilter=null;
	private String type = "none";



	/*public String searchFlightReportsByCompanyType(){
		logger.info("-----------------selected company type---------------"+flightReportPage.reportData.getFilterCompanyType());
		logger.info("-----------------selected company type---------------"+reportData.getOrderId());
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		FlightReportsList = flightOrderDao.getCompanyFlightReportsByComapnyType1(userSessionObj, companySessionObj, reportData);
		airlineList = cDAO.getAirlineList();
		return SUCCESS;
	} */

	/*this method for show all flight passengers   */
	public String showPassengerDetails(){
		ReportData selectedReportItem=flightOrderDao.getReportDetailsByRowId(CurrentReportdata.getId());
		if(selectedReportItem!=null){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.PASSENGER_DETAILS.getId();
			detailType=BrowsingHistoryDetailTypeEnum.PASSENGER_DETAILS.getId();	
			//XXXXXXXXXXXXXXXXXXXXXXXX
			if(selectedReportItem.getArrivalDate()!=null &&selectedReportItem.getArrivalDate().equals("")){
				selectedReportItem.setArrivalDate("NIL");
			}
			CurrentReportdata = selectedReportItem;
		}

		List<FlightOrderCustomerSSR> ssrList= flightOrderDao.getFlightOrderSSR(CurrentReportdata.getId());
		List<FlightOrderCustomerSSR> newSsrList=new ArrayList<>();
		if(ssrList!=null && ssrList.size()>0){
			for(FlightOrderCustomerSSR flightOrderCustomerSSR:ssrList){
				if(flightOrderCustomerSSR.getMealType()==null ){
					flightOrderCustomerSSR.setMealType("NIL");
				} 

				if(flightOrderCustomerSSR.getMealPrice()==null ){
					flightOrderCustomerSSR.setMealPrice("NIL");
				}
				if(flightOrderCustomerSSR.getMealname()==null ){
					flightOrderCustomerSSR.setMealname("NIL");
				} 	
				if(flightOrderCustomerSSR.getBaggageType()==null ){
					flightOrderCustomerSSR.setBaggageType("NIL");					
				}
				if(flightOrderCustomerSSR.getBaggagePrice()==null ){
					flightOrderCustomerSSR.setBaggagePrice("NIL");					
				}
				if(flightOrderCustomerSSR.getBaggageweight()==null ){
					flightOrderCustomerSSR.setBaggageweight("NIL");		
				}
					if(flightOrderCustomerSSR.getSeatType()==null ){
						flightOrderCustomerSSR.setSeatType("NIL");					
					}
					if(flightOrderCustomerSSR.getReturnmealType()==null ){
						flightOrderCustomerSSR.setReturnmealType("NIL");	
					}
					if(flightOrderCustomerSSR.getReturnmealname()==null ){
						flightOrderCustomerSSR.setReturnmealname("NIL");					
					}
					if(flightOrderCustomerSSR.getReturnmealPrice()==null ){
						flightOrderCustomerSSR.setReturnmealPrice("NIL");	
					}
					if(flightOrderCustomerSSR.getReturnbaggageType()==null ){
						flightOrderCustomerSSR.setReturnbaggageType("NIL");					
					}
					if(flightOrderCustomerSSR.getReturnbaggagePrice()==null ){
						flightOrderCustomerSSR.setReturnbaggagePrice("NIL");	
					}
						if(flightOrderCustomerSSR.getReturnbaggageweight()==null ){
							flightOrderCustomerSSR.setReturnbaggageweight("NIL");					
						}
						if(flightOrderCustomerSSR.getReturnseatType()==null){
							flightOrderCustomerSSR.setReturnseatType("NIL");	
						}
						newSsrList.add(flightOrderCustomerSSR);
						SSRList=newSsrList;	
					}
					}
	 
					Company company = (Company)sessionMap.get("Company");
					CompanyConfigDao companyconfigdao = new CompanyConfigDao();
					CompanyConfig companyconfig =  companyconfigdao.getCompanyConfigDetailsByCompanydetails(company);

					CurrentReportdata.setAppkey(companyconfig.getAppKey());
					ReportData flightOrderCustomer = flightOrderDao.getFlightOrderCustomerDetail(CurrentReportdata.getId());
					FlightOrderRow 	flightOrderRow = flightOrderDao.getFlightOrderRowDataById(CurrentReportdata.getId());
					FlightBookingKeysTemp flightBookingKeysTemp = flightOrderDao.getPricekeyofCurrentFlightOrder(flightOrderRow.getTransaction_key());
					if(flightBookingKeysTemp!=null && flightBookingKeysTemp.getPrice_key()!=null)
					CurrentReportdata.setPricekey(flightBookingKeysTemp.getPrice_key());
					else
						CurrentReportdata.setPricekey("");	
					
								if(flightOrderCustomer!=null){
						if(flightOrderCustomer.getFlightOrderCustomerList()!=null){
							for(int i=0;i<flightOrderCustomer.getFlightOrderCustomerList().size();i++){
								FlightOrderCustomer  customer=flightOrderCustomer.getFlightOrderCustomerList().get(i);
								//for(FlightOrderCustomerPriceBreakup  PriceBreakup:flightOrderCustomer.getFlightOrderCustomerPriceBreakup()){
								FlightOrderCustomerPriceBreakup  PriceBreakup=flightOrderCustomer.getFlightOrderCustomerPriceBreakup().get(i);
								//logger.info("customer.getId()-----"+customer.getId()+"---PriceBreakup.getId()-------"+PriceBreakup.getId());
								BigDecimal brakupMarkup=new BigDecimal(PriceBreakup.getMarkup());
								//	BigDecimal procesiingFeee=new BigDecimal("0.00");
								BigDecimal basePrice= PriceBreakup.getBaseFare().multiply(flightOrderRow.getApiToBaseExchangeRate());
								BigDecimal taxes= PriceBreakup.getTax().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
								BigDecimal totalBasePrice = basePrice.add(brakupMarkup);
								BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
								BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
								//BigDecimal totalPrice=procesiingFeee.add(basePriceInBooking).add(taxesInBooking);//should be added later

								BigDecimal totalPriceInBooking=basePriceInBooking.add(taxesInBooking);
								//logger.info("totalPrice----in booking--------------------"+totalPriceInBooking);
								ReportData data = new ReportData();
								data.setName(customer.getFirstName());
								data.setSurname(customer.getLastName());
								data.setGender(customer.getGender());
								data.setMobile(customer.getMobile());
								//data.setPassportExpDate(customer.getPassportExpiryDate());
								data.setPhone(customer.getPhone());
								data.setPrice(basePriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
								//data.setAgentCom(PriceBreakup.getMarkup());
								data.setTotal(totalPriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
								data.setTax(taxesInBooking.setScale(2,  BigDecimal.ROUND_UP));
								//data.setRoute(orderTripDetail.getOriginCode()+"-"+orderTripDetail.getDestinationCode());
								passList.add(data);
								//sessionMap.put("passengerList", passList);
							}
						}

					}
  
					ReportData companyWalletHistory=flightOrderDao.getWalletAmountTxStatementHistoryByUserId(selectedReportItem.getUserId(),selectedReportItem.getOrderId());
					List<WalletAmountTranferHistory> newWalletHistoryList=new ArrayList<WalletAmountTranferHistory>();
					if(companyWalletHistory!=null && companyWalletHistory.getWalletAmountTranferHistory()!=null){
						for(WalletAmountTranferHistory history:companyWalletHistory.getWalletAmountTranferHistory()){
								history.setOpeningBalance(history.getOpeningBalance().setScale(2,  BigDecimal.ROUND_UP));
								history.setClosingBalance(history.getClosingBalance().setScale(2,  BigDecimal.ROUND_UP));
								history.setAmount(history.getAmount().setScale(2,  BigDecimal.ROUND_UP));
								newWalletHistoryList.add(history);
								companyWalletHistory.setWalletAmountTranferHistory(newWalletHistoryList); 
							 
						}
					}
					if(companyWalletHistory!=null){
						txHistory=companyWalletHistory;
					}
					List<PaymentTransaction> newPaymentTransactionList =new ArrayList<PaymentTransaction>();
					List<PaymentTransaction> paymentTransactionList=flightOrderDao.getEndUserPaymentTransactions(selectedReportItem.getOrderId());

					if(paymentTransactionList!=null){
						for(PaymentTransaction p:paymentTransactionList){
							p.setAmount(p.getAmount().setScale(2,  BigDecimal.ROUND_UP));
							newPaymentTransactionList.add(p);
						}
						endUserTxHistory =newPaymentTransactionList;
					}
					User user = (User)sessionMap.get("User");
					HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.PASSENGER_DETAILS.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
					return SUCCESS;
					}

	/*this method for show all flight passengers   */
	public String showTodayOrderPassengerDetails(){
		ReportData selectedReportItem=flightOrderDao.getReportDetailsByRowId(CurrentReportdata.getId());
		//logger.info("showPassengerDetails--------------------flightReportPage.getId()="+CurrentReportdata.getId());
		//logger.info("showPassengerDetails-------------------selectedReportItem="+selectedReportItem);

		if(selectedReportItem!=null){
			CurrentReportdata = selectedReportItem;
		}


		/*ReportData newReportData=flightOrderDao.getReportDetailsByRowId(selectedReportItem.getId());
		if(newReportData!=null){
			CurrentReportdata=newReportData;
		}*/
		//agency data-----------------end--------------------------
		ReportData flightOrderCustomer = flightOrderDao.getFlightOrderCustomerDetail(selectedReportItem.getId());
		FlightOrderRow 	flightOrderRow = flightOrderDao.getFlightOrderRowDataById(selectedReportItem.getId());


		for(int i=0;i<flightOrderCustomer.getFlightOrderCustomerList().size();i++){
			FlightOrderCustomer  customer=flightOrderCustomer.getFlightOrderCustomerList().get(i);
			//for(FlightOrderCustomerPriceBreakup  PriceBreakup:flightOrderCustomer.getFlightOrderCustomerPriceBreakup()){
			FlightOrderCustomerPriceBreakup  PriceBreakup=flightOrderCustomer.getFlightOrderCustomerPriceBreakup().get(i);
			//logger.info("customer.getId()-----"+customer.getId()+"---PriceBreakup.getId()-------"+PriceBreakup.getId());


			BigDecimal brakupMarkup=new BigDecimal(PriceBreakup.getMarkup());
			//	BigDecimal procesiingFeee=new BigDecimal("0.00");


			BigDecimal basePrice= PriceBreakup.getBaseFare().multiply(flightOrderRow.getApiToBaseExchangeRate());
			BigDecimal taxes= PriceBreakup.getTax().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
			BigDecimal totalBasePrice = basePrice.add(brakupMarkup);
			BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			//BigDecimal totalPrice=procesiingFeee.add(basePriceInBooking).add(taxesInBooking);//should be added later

			BigDecimal totalPriceInBooking=basePriceInBooking.add(taxesInBooking);
			//logger.info("totalPrice----in booking--------------------"+totalPriceInBooking);

			ReportData data = new ReportData();
			data.setName(customer.getFirstName());
			data.setSurname(customer.getLastName());
			data.setGender(customer.getGender());
			data.setMobile(customer.getMobile());
			//data.setPassportExpDate(customer.getPassportExpiryDate());
			data.setPhone(customer.getPhone());
			data.setPrice(basePriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setAgentCom(PriceBreakup.getMarkup());
			data.setTotal(totalPriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			data.setTax(taxesInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setRoute(orderTripDetail.getOriginCode()+"-"+orderTripDetail.getDestinationCode());
			passList.add(data);
			//sessionMap.put("passengerList", passList);
		}
		try {
			ReportData companyWalletHistory=flightOrderDao.getWalletAmountTxStatementHistoryByUserId(selectedReportItem.getUserId(),selectedReportItem.getOrderId());
			List<WalletAmountTranferHistory> newWalletHistoryList=new ArrayList<WalletAmountTranferHistory>();
			if(companyWalletHistory!=null && companyWalletHistory.getWalletAmountTranferHistory()!=null){
				for(WalletAmountTranferHistory history:companyWalletHistory.getWalletAmountTranferHistory()){
					if(history.getUserId()==Integer.parseInt(selectedReportItem.getUserId())){
						newWalletHistoryList.add(history);
						companyWalletHistory.setWalletAmountTranferHistory(newWalletHistoryList); 
					}
				}
			}
			if(companyWalletHistory!=null){
				txHistory=companyWalletHistory;
			}
			if(flightOrderDao.getEndUserPaymentTransactions(selectedReportItem.getOrderId())!=null){
				endUserTxHistory =flightOrderDao.getEndUserPaymentTransactions(selectedReportItem.getOrderId());
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("(-----Exception----------)"+e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*this method for show all flight passengers   */
	public String showWeekOrderPassengerDetails(){
		ReportData selectedReportItem=flightOrderDao.getReportDetailsByRowId(CurrentReportdata.getId());
		logger.info("showPassengerDetails--------------------flightReportPage.getId()="+CurrentReportdata.getId());
		logger.info("showPassengerDetails-------------------selectedReportItem="+selectedReportItem);

		if(selectedReportItem!=null){
			CurrentReportdata = selectedReportItem;
		}
		//agency data-----------------end--------------------------
		ReportData flightOrderCustomer = flightOrderDao.getFlightOrderCustomerDetail(selectedReportItem.getId());
		FlightOrderRow 	flightOrderRow = flightOrderDao.getFlightOrderRowDataById(selectedReportItem.getId());


		for(int i=0;i<flightOrderCustomer.getFlightOrderCustomerList().size();i++){
			FlightOrderCustomer  customer=flightOrderCustomer.getFlightOrderCustomerList().get(i);
			//for(FlightOrderCustomerPriceBreakup  PriceBreakup:flightOrderCustomer.getFlightOrderCustomerPriceBreakup()){
			FlightOrderCustomerPriceBreakup  PriceBreakup=flightOrderCustomer.getFlightOrderCustomerPriceBreakup().get(i);
			//logger.info("customer.getId()-----"+customer.getId()+"---PriceBreakup.getId()-------"+PriceBreakup.getId());


			BigDecimal brakupMarkup=new BigDecimal(PriceBreakup.getMarkup());
			//	BigDecimal procesiingFeee=new BigDecimal("0.00");


			BigDecimal basePrice= PriceBreakup.getBaseFare().multiply(flightOrderRow.getApiToBaseExchangeRate());
			BigDecimal taxes= PriceBreakup.getTax().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
			BigDecimal totalBasePrice = basePrice.add(brakupMarkup);
			BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			//BigDecimal totalPrice=procesiingFeee.add(basePriceInBooking).add(taxesInBooking);//should be added later
			BigDecimal totalPriceInBooking=basePriceInBooking.add(taxesInBooking);
			//logger.info("totalPrice----in booking--------------------"+totalPriceInBooking);
			ReportData data = new ReportData();
			data.setName(customer.getFirstName());
			data.setSurname(customer.getLastName());
			data.setGender(customer.getGender());
			data.setMobile(customer.getMobile());
			//data.setPassportExpDate(customer.getPassportExpiryDate());
			data.setPhone(customer.getPhone());
			data.setPrice(basePriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setAgentCom(PriceBreakup.getMarkup());
			data.setTotal(totalPriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			data.setTax(taxesInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setRoute(orderTripDetail.getOriginCode()+"-"+orderTripDetail.getDestinationCode());
			passList.add(data);
			//sessionMap.put("passengerList", passList);

		}

		try {
			ReportData companyWalletHistory=flightOrderDao.getWalletAmountTxStatementHistoryByUserId(selectedReportItem.getUserId(),selectedReportItem.getOrderId());
			List<WalletAmountTranferHistory> newWalletHistoryList=new ArrayList<WalletAmountTranferHistory>();
			if(companyWalletHistory!=null && companyWalletHistory.getWalletAmountTranferHistory()!=null){
				for(WalletAmountTranferHistory history:companyWalletHistory.getWalletAmountTranferHistory()){
					if(history.getUserId()==Integer.parseInt(selectedReportItem.getUserId())){
						newWalletHistoryList.add(history);
						companyWalletHistory.setWalletAmountTranferHistory(newWalletHistoryList); 
					}
				}
			}
			if(companyWalletHistory!=null){
				txHistory=companyWalletHistory;
			}
			if(flightOrderDao.getEndUserPaymentTransactions(selectedReportItem.getOrderId())!=null){
				endUserTxHistory =flightOrderDao.getEndUserPaymentTransactions(selectedReportItem.getOrderId());
			}

			/* txHistory = flightOrderDao.getWalletAmountTxStatementHistory(reportData.getOrderId());
			 endUserTxHistory =flightOrderDao.getEndUserPaymentTransactions(reportData.getOrderId());*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("(-----Exception----------)"+e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}


	/*this method for show all flight passengers   */
	public String showMonthOrderPassengerDetails(){

		ReportData selectedReportItem=flightOrderDao.getReportDetailsByRowId(CurrentReportdata.getId());
		logger.info("showPassengerDetails--------------------flightReportPage.getId()="+CurrentReportdata.getId());
		logger.info("showPassengerDetails-------------------selectedReportItem="+selectedReportItem);

		if(selectedReportItem!=null){
			CurrentReportdata = selectedReportItem;
		}
		//agency data-----------------end--------------------------
		ReportData flightOrderCustomer = flightOrderDao.getFlightOrderCustomerDetail(selectedReportItem.getId());
		FlightOrderRow 	flightOrderRow = flightOrderDao.getFlightOrderRowDataById(CurrentReportdata.getId());
		for(int i=0;i<flightOrderCustomer.getFlightOrderCustomerList().size();i++){
			FlightOrderCustomer  customer=flightOrderCustomer.getFlightOrderCustomerList().get(i);
			//for(FlightOrderCustomerPriceBreakup  PriceBreakup:flightOrderCustomer.getFlightOrderCustomerPriceBreakup()){
			FlightOrderCustomerPriceBreakup  PriceBreakup=flightOrderCustomer.getFlightOrderCustomerPriceBreakup().get(i);
			//logger.info("customer.getId()-----"+customer.getId()+"---PriceBreakup.getId()-------"+PriceBreakup.getId());
			BigDecimal brakupMarkup=new BigDecimal(PriceBreakup.getMarkup());
			//	BigDecimal procesiingFeee=new BigDecimal("0.00");
			BigDecimal basePrice= PriceBreakup.getBaseFare().multiply(flightOrderRow.getApiToBaseExchangeRate());
			BigDecimal taxes= PriceBreakup.getTax().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
			BigDecimal totalBasePrice = basePrice.add(brakupMarkup);
			BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			//BigDecimal totalPrice=procesiingFeee.add(basePriceInBooking).add(taxesInBooking);//should be added later
			BigDecimal totalPriceInBooking=basePriceInBooking.add(taxesInBooking);
			//logger.info("totalPrice----in booking--------------------"+totalPriceInBooking);
			ReportData data = new ReportData();
			data.setName(customer.getFirstName());
			data.setSurname(customer.getLastName());
			data.setGender(customer.getGender());
			data.setMobile(customer.getMobile());
			//data.setPassportExpDate(customer.getPassportExpiryDate());
			data.setPhone(customer.getPhone());
			data.setPrice(basePriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setAgentCom(PriceBreakup.getMarkup());
			data.setTotal(totalPriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			data.setTax(taxesInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setRoute(orderTripDetail.getOriginCode()+"-"+orderTripDetail.getDestinationCode());
			passList.add(data);
			//sessionMap.put("passengerList", passList);
		}
		try {
			ReportData companyWalletHistory=flightOrderDao.getWalletAmountTxStatementHistoryByUserId(CurrentReportdata.getUserId(),CurrentReportdata.getOrderId());
			List<WalletAmountTranferHistory> newWalletHistoryList=new ArrayList<WalletAmountTranferHistory>();
			if(companyWalletHistory!=null && companyWalletHistory.getWalletAmountTranferHistory()!=null){
				for(WalletAmountTranferHistory history:companyWalletHistory.getWalletAmountTranferHistory()){
					if(history.getUserId()==Integer.parseInt(CurrentReportdata.getUserId())){
						newWalletHistoryList.add(history);
						companyWalletHistory.setWalletAmountTranferHistory(newWalletHistoryList); 
					}
				}
			}
			if(companyWalletHistory!=null){
				txHistory=companyWalletHistory;
			}
			if(flightOrderDao.getEndUserPaymentTransactions(CurrentReportdata.getOrderId())!=null){
				endUserTxHistory =flightOrderDao.getEndUserPaymentTransactions(CurrentReportdata.getOrderId());
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("(-----Exception----------)"+e.getMessage());
			e.printStackTrace();
		}


		return SUCCESS;

	}

	/*this method for show all flight passengers   */
	public String showConfirmOrderPassengerDetails(){


		ReportData selectedReportItem=flightOrderDao.getReportDetailsByRowId(CurrentReportdata.getId());
		logger.info("showPassengerDetails--------------------flightReportPage.getId()="+CurrentReportdata.getId());
		logger.info("showPassengerDetails-------------------selectedReportItem="+selectedReportItem);

		if(selectedReportItem!=null){
			CurrentReportdata = selectedReportItem;
		}

		//List<ReportData> allReportList=(List<ReportData>) sessionMap.get("userFlightReportData_list");
		//agency data-----------------Start--------------------------
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		//List<ReportData> companyReportsList = flightOrderDao.getCompanyFlightReports(userSessionObj,companySessionObj);\
		FlightReportsList	= flightOrderDao.getCompanyFlightReports(userSessionObj,companySessionObj, null);
		//List<ReportData> companyReportsList = (List<ReportData>) sessionMap.get("companyReportsList");
		//////logger.info("r.getId()---companyReportsList.size()  -----"+companyReportsList.size());
		for(ReportData r:FlightReportsList){
			//////logger.info("r.getId()---"+r.getId()+"=====reportData.getId()"+reportData.getId());
			if(r.getId().longValue() == CurrentReportdata.getId().longValue()){
				CurrentReportdata = r;
				//sessionMap.put("ReportData",r);
			}
		}


		//agency data-----------------end--------------------------
		ReportData flightOrderCustomer = flightOrderDao.getFlightOrderCustomerDetail(CurrentReportdata.getId());
		FlightOrderRow 	flightOrderRow = flightOrderDao.getFlightOrderRowDataById(CurrentReportdata.getId());


		for(int i=0;i<flightOrderCustomer.getFlightOrderCustomerList().size();i++){
			FlightOrderCustomer  customer=flightOrderCustomer.getFlightOrderCustomerList().get(i);
			//for(FlightOrderCustomerPriceBreakup  PriceBreakup:flightOrderCustomer.getFlightOrderCustomerPriceBreakup()){
			FlightOrderCustomerPriceBreakup  PriceBreakup=flightOrderCustomer.getFlightOrderCustomerPriceBreakup().get(i);
			//logger.info("customer.getId()-----"+customer.getId()+"---PriceBreakup.getId()-------"+PriceBreakup.getId());


			BigDecimal brakupMarkup=new BigDecimal(PriceBreakup.getMarkup());
			//	BigDecimal procesiingFeee=new BigDecimal("0.00");


			BigDecimal basePrice= PriceBreakup.getBaseFare().multiply(flightOrderRow.getApiToBaseExchangeRate());
			BigDecimal taxes= PriceBreakup.getTax().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
			BigDecimal totalBasePrice = basePrice.add(brakupMarkup);
			BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			//BigDecimal totalPrice=procesiingFeee.add(basePriceInBooking).add(taxesInBooking);//should be added later

			BigDecimal totalPriceInBooking=basePriceInBooking.add(taxesInBooking);
			//logger.info("totalPrice----in booking--------------------"+totalPriceInBooking);

			ReportData data = new ReportData();
			data.setName(customer.getFirstName());
			data.setSurname(customer.getLastName());
			data.setGender(customer.getGender());
			data.setMobile(customer.getMobile());
			//data.setPassportExpDate(customer.getPassportExpiryDate());
			data.setPhone(customer.getPhone());
			data.setPrice(basePriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setAgentCom(PriceBreakup.getMarkup());
			data.setTotal(totalPriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			data.setTax(taxesInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setRoute(orderTripDetail.getOriginCode()+"-"+orderTripDetail.getDestinationCode());
			passList.add(data);
			//sessionMap.put("passengerList", passList);

		}



		try {

			txHistory = flightOrderDao.getWalletAmountTxStatementHistory(CurrentReportdata.getOrderId());
			endUserTxHistory =flightOrderDao.getEndUserPaymentTransactions(CurrentReportdata.getOrderId());
			/* if(txHistory.getWalletAmountTranferHistory().size()>0 && txHistory.getWalletAmountTranferHistory()!=null){
				 //sessionMap.put("txHistory", txHistory.getWalletAmountTranferHistory()); 
			}
			 else{
				 //sessionMap.remove("txHistory");
			 }*/
			/* if(endUserTxHistory.size()>0 && endUserTxHistory!=null){
			  //sessionMap.put("directUserTxHistory", endUserTxHistory);
			}
		  else{
				// sessionMap.remove("directUserTxHistory");
			 }*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("(-----Exception----------)"+e.getMessage());
			e.printStackTrace();
		}


		return SUCCESS;

	}


	/*this method for show all flight passengers   */
	public String showPaymentOrderPassengerDetails(){

		ReportData selectedReportItem=flightOrderDao.getReportDetailsByRowId(CurrentReportdata.getId());
		logger.info("showPassengerDetails--------------------flightReportPage.getId()="+CurrentReportdata.getId());
		logger.info("showPassengerDetails-------------------selectedReportItem="+selectedReportItem);

		if(selectedReportItem!=null){
			CurrentReportdata = selectedReportItem;
		}

		//List<ReportData> allReportList=(List<ReportData>) sessionMap.get("userFlightReportData_list");
		//agency data-----------------Start--------------------------
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		//List<ReportData> companyReportsList = flightOrderDao.getCompanyFlightReports(userSessionObj,companySessionObj);\
		FlightReportsList	= flightOrderDao.getCompanyFlightReports(userSessionObj,companySessionObj,null);
		//List<ReportData> companyReportsList = (List<ReportData>) sessionMap.get("companyReportsList");
		//////logger.info("r.getId()---companyReportsList.size()  -----"+companyReportsList.size());
		for(ReportData r:FlightReportsList){
			//////logger.info("r.getId()---"+r.getId()+"=====reportData.getId()"+reportData.getId());
			if(r.getId().longValue() == CurrentReportdata.getId().longValue()){
				CurrentReportdata = r;
				//sessionMap.put("ReportData",r);
			}
		}


		//agency data-----------------end--------------------------
		ReportData flightOrderCustomer = flightOrderDao.getFlightOrderCustomerDetail(CurrentReportdata.getId());
		FlightOrderRow 	flightOrderRow = flightOrderDao.getFlightOrderRowDataById(CurrentReportdata.getId());


		for(int i=0;i<flightOrderCustomer.getFlightOrderCustomerList().size();i++){
			FlightOrderCustomer  customer=flightOrderCustomer.getFlightOrderCustomerList().get(i);
			//for(FlightOrderCustomerPriceBreakup  PriceBreakup:flightOrderCustomer.getFlightOrderCustomerPriceBreakup()){
			FlightOrderCustomerPriceBreakup  PriceBreakup=flightOrderCustomer.getFlightOrderCustomerPriceBreakup().get(i);
			//logger.info("customer.getId()-----"+customer.getId()+"---PriceBreakup.getId()-------"+PriceBreakup.getId());


			BigDecimal brakupMarkup=new BigDecimal(PriceBreakup.getMarkup());
			//	BigDecimal procesiingFeee=new BigDecimal("0.00");


			BigDecimal basePrice= PriceBreakup.getBaseFare().multiply(flightOrderRow.getApiToBaseExchangeRate());
			BigDecimal taxes= PriceBreakup.getTax().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
			BigDecimal totalBasePrice = basePrice.add(brakupMarkup);
			BigDecimal basePriceInBooking=totalBasePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			BigDecimal taxesInBooking=taxes.multiply(flightOrderRow.getBaseToBookingExchangeRate());
			//BigDecimal totalPrice=procesiingFeee.add(basePriceInBooking).add(taxesInBooking);//should be added later

			BigDecimal totalPriceInBooking=basePriceInBooking.add(taxesInBooking);
			//logger.info("totalPrice----in booking--------------------"+totalPriceInBooking);

			ReportData data = new ReportData();
			data.setName(customer.getFirstName());
			data.setSurname(customer.getLastName());
			data.setGender(customer.getGender());
			data.setMobile(customer.getMobile());
			//data.setPassportExpDate(customer.getPassportExpiryDate());
			data.setPhone(customer.getPhone());
			data.setPrice(basePriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setAgentCom(PriceBreakup.getMarkup());
			data.setTotal(totalPriceInBooking.setScale(2,  BigDecimal.ROUND_UP));
			data.setTax(taxesInBooking.setScale(2,  BigDecimal.ROUND_UP));
			//data.setRoute(orderTripDetail.getOriginCode()+"-"+orderTripDetail.getDestinationCode());
			passList.add(data);
			//sessionMap.put("passengerList", passList);

		}



		try {

			txHistory = flightOrderDao.getWalletAmountTxStatementHistory(CurrentReportdata.getOrderId());
			endUserTxHistory =flightOrderDao.getEndUserPaymentTransactions(CurrentReportdata.getOrderId());
			/* if(txHistory.getWalletAmountTranferHistory().size()>0 && txHistory.getWalletAmountTranferHistory()!=null){
				 //sessionMap.put("txHistory", txHistory.getWalletAmountTranferHistory()); 
			}
			 else{
				 //sessionMap.remove("txHistory");
			 }*/
			/* if(endUserTxHistory.size()>0 && endUserTxHistory!=null){
			  //sessionMap.put("directUserTxHistory", endUserTxHistory);
			}
		  else{
				// sessionMap.remove("directUserTxHistory");
			 }*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("(-----Exception----------)"+e.getMessage());
			e.printStackTrace();
		}


		return SUCCESS;

	}

	@Override
	public ReportData getModel() {
		// TODO Auto-generated method stub
		return CurrentReportdata;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	public List<ReportData> getFlightReportsList() {
		return FlightReportsList;
	}

	public void setFlightReportsList(List<ReportData> flightReportsList) {
		FlightReportsList = flightReportsList;
	}



	public List<ReportData> getPassList() {
		return passList;
	}

	public void setPassList(List<ReportData> passList) {
		this.passList = passList;
	}

	public ReportData getTxHistory() {
		return txHistory;
	}

	public void setTxHistory(ReportData txHistory) {
		this.txHistory = txHistory;
	}

	public List<PaymentTransaction> getEndUserTxHistory() {
		return endUserTxHistory;
	}

	public void setEndUserTxHistory(List<PaymentTransaction> endUserTxHistory) {
		this.endUserTxHistory = endUserTxHistory;
	}



	public List<Airlinelist> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(List<Airlinelist> airlineList) {
		this.airlineList = airlineList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public ReportData getCurrentReportdata() {
		if(CurrentReportdata == null)
		{
			CurrentReportdata = new ReportData();
		}
		return CurrentReportdata;
	}

	public void setCurrentReportdata(ReportData CurrentReportdata) {
		this.CurrentReportdata = CurrentReportdata;
	}
	public List<FlightOrderCustomerSSR> getSSRList() {
		return SSRList;
	}
	public void setSSRList(List<FlightOrderCustomerSSR> sSRList) {
		SSRList = sSRList;
	}
}
