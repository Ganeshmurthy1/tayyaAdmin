package com.lintas.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRowCancellation;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Airlinelist;
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

public class FlightOrderAction extends ActionSupport implements SessionAware,ModelDriven<ReportData> {
	/**
	 * @author info Raham
	 * Date:7-09-2015
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightOrderAction.class);
	SessionMap<String , Object> sessionMap;
	ReportData orderData=new ReportData();
	List<ReportData> orderFilter_list= new ArrayList<ReportData>();
	FlightOrderDao  flightOrderDao=new FlightOrderDao();
	private List<ReportData> passList=new ArrayList<>();
	private List<ReportData> flightInfo=new ArrayList<>();
	private List<ReportData> paymentInfo=new ArrayList<>();
	private ReportData agentTotalOrderCommObj=new ReportData();
	private List<ReportData> companyFlightOrderList;
	private List<Airlinelist> airlineList;
	private List<CreditNote> creditNoteList;
	CreditNoteDao flightCreditNoteDao=new CreditNoteDao();
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private ReportData ReportData = new ReportData();

	private boolean isCreditIssued = false;
	private FlightOrderRowCancellation flightOrderRowCancellation=new FlightOrderRowCancellation(); 
	List<String> fieldName = new ArrayList<String>();



	public static final String reportDateFormat = "dd/MM/yyyy";


	public String getCompanyFlightOrders(){
		//List<ReportData> companyFlightOrderList=null;
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		BigDecimal totSpentAmount =new BigDecimal("0");
		//ReportData agentTotalOrderCommObj=new ReportData(); getCompanyFlightOrders
		companyFlightOrderList = flightOrderDao.getCompanyFlightOrders(userSessionObj,companySessionObj);
		logger.info("....companyFlightOrderList....."+companyFlightOrderList.size());
		airlineList=new CountryDao().getAirlineList();
		if(companyFlightOrderList!=null && companyFlightOrderList.size()>0){
			for(ReportData report:companyFlightOrderList){
				totSpentAmount=totSpentAmount.add(report.getFinalPrice());
			}
			agentTotalOrderCommObj.setTotAmountSpent(totSpentAmount);
		} 

		return SUCCESS;
	} 

	public String searchFlightOrdersByCompanyType(){
		logger.info("-----------------selected company type---------------"+orderData.getFilterCompanyType());
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		String[] companyorAgentId=null;
		BigDecimal totSpentAmount =new BigDecimal("0");
		if(orderData.getCompanyName().contains(",")){
			companyorAgentId = orderData.getCompanyName().split(",");
			orderData.setCompanyName(companyorAgentId[1]);
		}
		else if(orderData.getAgentName().contains(",")){
			companyorAgentId=orderData.getAgentName().split(",");
			orderData.setAgentName(companyorAgentId[1]);
		}
		List<ReportData> companyOrdersSearchList=flightOrderDao.getCompanyFlightReportsByComapnyType1(userSessionObj, companySessionObj, orderData);
		if(companyOrdersSearchList!=null && companyOrdersSearchList.size()>0){
			for(ReportData report:companyOrdersSearchList){
				totSpentAmount=totSpentAmount.add(report.getFinalPrice());
			}
			agentTotalOrderCommObj.setTotAmountSpent(totSpentAmount);
			companyFlightOrderList=companyOrdersSearchList;
			airlineList = new CountryDao().getAirlineList();
		}
		return SUCCESS;
	} 
	/* method for  show  PassengerOrderDetails based on orderid */
	public String showPassengerOrderDetails() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(reportDateFormat);
		Company companySessionObj = (Company)sessionMap.get("Company");
		User userSessionObj = (User)sessionMap.get("User");
		CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(companySessionObj.getCompanyid());

		RmConfigDao rmConfigDao=new RmConfigDao();
		
		ReportData flightOrderCustomer=flightOrderDao.getFlightOrderCustomerDetail(orderData.getId());
		ReportData paymentData=flightOrderDao.getPaymentTransactionDetail(orderData.getOrderId());
		FlightOrderRowCancellation cancelationData=new CreditNoteDao().getFlightOrderRowCancellationWithAPIStatus(orderData.getOrderId());
		setFlightOrderRowCancellation(cancelationData);
		//List<WalletAmountTranferHistory> walletHistoryList=flightCreditNoteDao.getWalletHistoryDetailsByOrderId(orderData.getOrderId());
		ReportData newReportData=flightOrderDao.getReportDetailsByRowId(orderData.getId());
		if(newReportData!=null){
			setIsCreditIssued(false);
			if(newReportData.getLastticketdate()!=null && newReportData.getLastticketdate()!="")
				newReportData.setLastticketdate(DateConversion.getBluestarDate(newReportData.getLastticketdate()));
			if(cancelationData!=null && cancelationData.getOrderId()!=null){
				newReportData.setCancellationFees(cancelationData.getAPIChargeAmount().setScale(3));
			}
			newReportData.setAppkey(companyConfig.getAppKey());
			// get credit note of current company 
			CreditNote creditNoteObj=flightOrderDao.flightCreditNoteData(orderData.getId(),companySessionObj.getCompanyid());
			newReportData.setMyCreditNote(creditNoteObj);
			CreditNote creditNoteObjParent = null;
			creditNoteList=flightOrderDao.loadCreditNoteListByFlightRowId(orderData.getId());
			try {
				List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
				if(parentUserIds!=null && parentUserIds.size()>0)
				{
					creditNoteObjParent=flightOrderDao.flightCreditNoteData(orderData.getId(),parentUserIds.get(0));
					if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent())
						setIsCreditIssued(true);
					else if(creditNoteObjParent != null && creditNoteObjParent.isOrderUpdated())
						setIsCreditIssued(true);

				}
			} catch (Exception e) {
				logger.error(e);
			}	
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
			ReportData = newReportData;
			if(flightOrderCustomer!=null && flightOrderCustomer.getFlightOrderTripDetail()!=null && flightOrderCustomer.getFlightOrderTripDetail().size()>0){
				for(FlightOrderTripDetail  orderTripDetail:flightOrderCustomer.getFlightOrderTripDetail()){
					ReportData data=new ReportData();
					data.setFlight_number(orderTripDetail.getFlightNumber());
					String route=orderTripDetail.getOriginCode()+"-"+orderTripDetail.getDestinationCode();
					data.setRoute(route);
					data.setTake_off(DateConversion.convertTimestampToString(orderTripDetail.getDepartureTime()));
					if(orderTripDetail.getArrivalTime()!=null)
						data.setLanding(DateConversion.convertTimestampToString(orderTripDetail.getArrivalTime()));
					data.set_class(orderTripDetail.getClassOfService());
					data.setTripType(orderTripDetail.getTripType());
					data.setTrips(orderTripDetail.getTrips());
					flightInfo.add(data);
				}
			}

			if(flightOrderCustomer!=null && flightOrderCustomer.getFlightOrderCustomerList()!=null && flightOrderCustomer.getFlightOrderCustomerList().size()>0 &&  flightOrderCustomer.getFlightOrderCustomerPriceBreakup()!=null && flightOrderCustomer.getFlightOrderCustomerPriceBreakup().size()>0){
				statusCode = ActionStatusEnum.SUCCESS.getCode();
				actionId=BrowsingOptionActionEnum.FLIGHT_ORDER_DETAILS.getId();
				detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
				//	for(FlightOrderCustomer  customer:flightOrderCustomer.getFlightOrderCustomerList()){
				for(int i=0;i<flightOrderCustomer.getFlightOrderCustomerList().size();i++){
					FlightOrderCustomer  customer=flightOrderCustomer.getFlightOrderCustomerList().get(i);
					//for(FlightOrderCustomerPriceBreakup  PriceBreakup:flightOrderCustomer.getFlightOrderCustomerPriceBreakup()){
					FlightOrderCustomerPriceBreakup  PriceBreakup=flightOrderCustomer.getFlightOrderCustomerPriceBreakup().get(i);
					ReportData data=new ReportData();
					data.setName(customer.getFirstName());
					data.setSurname(customer.getLastName());
					//data.setPaymentMethod(customer.getFlightOrderRow().getPaidBy());
					data.setGender(customer.getGender());
					data.setMobile(customer.getMobile());
					if(customer.getPassportExpiryDate()!=null && !customer.getPassportExpiryDate().equals(""))
						data.setPassportExpDate(sdf.format(customer.getPassportExpiryDate()));
					data.setPhone(customer.getPhone());
					data.setPrice(PriceBreakup.getBaseFare().setScale(2, BigDecimal.ROUND_UP));
					data.setAgentCom(PriceBreakup.getMarkup());
					data.setTotal(PriceBreakup.getTotal().setScale(2, BigDecimal.ROUND_UP));
					data.setTax(PriceBreakup.getTax().setScale(2, BigDecimal.ROUND_UP));
					if(customer.getBirthday()!=null && !customer.getBirthday().equalsIgnoreCase(""))
						data.setBirth(DateConversion.getBluestarDate(customer.getBirthday()));
					
					if(customer.getRmConfigTripDetailsModel()!=null && customer.getRmConfigTripDetailsModel().getId()>0){
						fetchRmConfigDetailsOfCustomer(data,customer.getRmConfigTripDetailsModel());
					}
					passList.add(data);
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
		}


		User user = (User)sessionMap.get("User");
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.FLIGHT_BOOKINGS_ORDER_LIST.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
		return SUCCESS;
	}
	public ReportData fetchRmConfigDetailsOfCustomer(ReportData data,RmConfigTripDetailsModel rmConfigModel){
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
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public ReportData getModel() {
		// TODO Auto-generated method stub
		return orderData;
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

	public FlightOrderRowCancellation getFlightOrderRowCancellation() {
		return flightOrderRowCancellation;
	}

	public void setFlightOrderRowCancellation(FlightOrderRowCancellation flightOrderRowCancellation) {
		this.flightOrderRowCancellation = flightOrderRowCancellation;
	}

	public List<String> getFieldName() {
		return fieldName;
	}

	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}
}
