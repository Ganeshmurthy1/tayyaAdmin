package com.lintas.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.knockoff.dao.PaymentKnockDao;
import com.admin.knockoff.dao.PaymentKnockDaoImpl;
import com.admin.knockoff.vo.KnockOffVO;
import com.admin.miscellaneous.dao.MiscellaneousOrderDao;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.payment.recievable.PaymentKnockOffRow;
import com.admin.payment.recievable.PaymentKnockOffRowTx;
import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.DAO.BusOrderDao;
import com.lintas.admin.DAO.CarOrderDao;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.InsuranceOrderDao;
import com.lintas.admin.DAO.MyTransactionsDao;
import com.lintas.admin.DAO.TrainOrderDao;
import com.lintas.admin.DAO.VisaOrderDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class MyTransactionsAction extends ActionSupport implements ModelDriven<CompanyFilterPage>,SessionAware{

	/**@author info raham
	 * craeted date:27-10-2015
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";

	private CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	SessionMap<String, Object> sessionMap;
	MyTransactionsDao myTransactionsDao=new MyTransactionsDao();
	WalletAmountTranferHistory myTransaction;
	private String transactionid;
	List<KnockOffVO> knockOffVOList=new ArrayList<>();
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MyTransactionsAction.class);
	/*	getting all super user transactions */
	public String getSuperTransactions(){
		User sessionUser=(User)sessionMap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginUser(sessionUser);
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage = myTransactionsDao.getMySelfTransferHistory(companyFilterPage);
		//logger.info("----------getWalletTxList-------------------------"+newCompanyFilterPage.getWalletTxList().size());
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getWalletTxList()!=null){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId();
			detailType=BrowsingHistoryDetailTypeEnum.SEARCH.getId();
			companyFilterPage= newCompanyFilterPage;
		}

		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.MY_TRANSACTIONS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  
		return SUCCESS;
	}
	public String getTransactionsHistory(){
		User sessionUser=(User)sessionMap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginUser(sessionUser);
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage  = myTransactionsDao.getTransactionsHistory(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getWalletTxList()!=null && newCompanyFilterPage.getWalletTxList().size()>0){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_TXT_HISTORY.getId();
			detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();					
			companyFilterPage=newCompanyFilterPage;
		} 
		User user = (User)sessionMap.get("User");
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.WALLET_HISTORY.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);

		return SUCCESS;
	}

	public String getLedgerReport(){
		User sessionUser=(User)sessionMap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginUser(sessionUser);
		companyFilterPage.setCompanyFilter(companyFilter);
		companyFilterPage  = myTransactionsDao.getLedgerReport(companyFilterPage);  
		return SUCCESS; 
	}

	public String getKnockOffReport(){
		User sessionUser=(User)sessionMap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginUser(sessionUser);
		PaymentKnockDao paymentKnockDao=new PaymentKnockDaoImpl();
		companyFilterPage.setCompanyFilter(companyFilter);
		if(companyFilter.getActionType()!=null && companyFilter.getActionType().equalsIgnoreCase("KnockOff")){
			if(getKnockOffVOList()!=null && getKnockOffVOList().size()>0){
				for(KnockOffVO knockOffVO:getKnockOffVOList()){
					if(knockOffVO.isKnockOffCheck()){
						knockOffVO.setBRVorCRV(companyFilter.getOrderId());
						knockOffVO.setCompanyId(companyFilter.getCompanyId());
						PaymentKnockOffRow paymentKnockOffRow=paymentKnockDao.fetchPaymentKnockOffRow(knockOffVO.getBillNo(), knockOffVO.getCompanyId());
						BigDecimal totAmount=new BigDecimal(0);
						if(paymentKnockOffRow!=null){ 
							paymentKnockDao.save(buildPaymentKnockOffRowTxData(knockOffVO, paymentKnockOffRow));
						}
						else{
							paymentKnockOffRow=paymentKnockDao.save(buildPaymentKnockOffRowData(knockOffVO));
							if(paymentKnockOffRow!=null && paymentKnockOffRow.getId()>0){
								paymentKnockDao.save(buildPaymentKnockOffRowTxData(knockOffVO, paymentKnockOffRow));
							}
						}
						PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(knockOffVO.getBillNo(), knockOffVO.getCompanyId());
						if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
							for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
								totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
							}
							boolean knockoffType=false;
							String billNo=paymentKnockOffRowNew.getBillNo();
							 if(knockOffVO.getInvoiceType().equals("CreditNote")){
								 
							 }
							if(totAmount.compareTo(paymentKnockOffRowNew.getBillAmount())==0) 
								knockoffType=true;
							 if(knockOffVO.getInvoiceType().equals("CreditNote")){
								 if(paymentKnockOffRowNew.getGDSorLCC().equals("Air")){
									 FlightOrderRow flightOrderRow = new FlightOrderDao().getFlightOrderRowDetail(knockOffVO.getBookingRef());
											 billNo=flightOrderRow.getInvoiceNo();
								 }
								 if(paymentKnockOffRowNew.getGDSorLCC().equals("Hotel")){
									HotelOrderRow hotelOrderRow = new HotelOrderDao().getHotelOrderRowDetail(knockOffVO.getBookingRef());
											 billNo=hotelOrderRow.getInvoiceNo();

								 }
								 if(paymentKnockOffRowNew.getGDSorLCC().equals("Car")){
									 CarOrderRow carOrderRow = new CarOrderDao().getCarOrderRowDetail(knockOffVO.getBookingRef());
									 billNo=carOrderRow.getInvoiceNo();

								 }
								 if(paymentKnockOffRowNew.getGDSorLCC().equals("Bus")){
									 BusOrderRow busOrderRow = new BusOrderDao().getBusOrderRowDetail(knockOffVO.getBookingRef());
									 billNo=busOrderRow.getInvoiceNo();
								 }
								 if(paymentKnockOffRowNew.getGDSorLCC().equals("Train")){
									 TrainOrderRow trainOrderRow = new TrainOrderDao().getTrainOrderRowDetail(knockOffVO.getBookingRef());
									 billNo=trainOrderRow.getInvoiceNo();
								 }
								 if(paymentKnockOffRowNew.getGDSorLCC().equals("Visa")){
									 VisaOrderRow visaOrderRow = new VisaOrderDao().getVisaOrderRowDetail(knockOffVO.getBookingRef());
									 billNo=visaOrderRow.getInvoiceNo();
								 }
								 if(paymentKnockOffRowNew.getGDSorLCC().equals("Insurance")){
									 InsuranceOrderRow insuranceOrderRow = new InsuranceOrderDao().getInsuranceOrderRowDetail(knockOffVO.getBookingRef());
									 billNo=insuranceOrderRow.getInvoiceNo();
								 }
								 if(paymentKnockOffRowNew.getGDSorLCC().equals("Miscellaneous")){
									 MiscellaneousOrderRow miscellaneousOrderRow = new MiscellaneousOrderDao().getMiscellaneousOrderRowDetail(knockOffVO.getBookingRef());
									 billNo=miscellaneousOrderRow.getInvoiceNo();
								 }	
							 }
							 paymentKnockDao.updateOrderRows(billNo, knockoffType);
							
							 
							 /*if(totAmount.compareTo(paymentKnockOffRowNew.getBillAmount())==0){ 
								 if(knockOffVO.getInvoiceType().equals("Invoice")) 
									
								 else{
									 if(paymentKnockOffRowNew.getGDSorLCC().equals("Air")){
										 
									 }
								 }
								
							}
							else{
								paymentKnockDao.updateOrderRows(paymentKnockOffRowNew.getBillNo(), false);
							}*/
						}

					}
				}	
			}

		}
		companyFilterPage  = myTransactionsDao.getKnockOffReports(companyFilterPage);
		return SUCCESS; 
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public CompanyFilterPage getModel() {
		// TODO Auto-generated method stub
		return companyFilterPage;
	}


	public CompanyFilterPage getCompanyFilterPage() {
		return companyFilterPage;
	}
	public void setCompanyFilterPage(CompanyFilterPage companyFilterPage) {
		this.companyFilterPage = companyFilterPage;
	}
	public WalletAmountTranferHistory getMyTransaction() {
		return myTransaction;
	}
	public void setMyTransaction(WalletAmountTranferHistory myTransaction) {
		this.myTransaction = myTransaction;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public List<KnockOffVO> getKnockOffVOList() {
		return knockOffVOList;
	}
	public void setKnockOffVOList(List<KnockOffVO> knockOffVOList) {
		this.knockOffVOList = knockOffVOList;
	}

	public  PaymentKnockOffRow  buildPaymentKnockOffRowData(KnockOffVO knockOffVO){
		PaymentKnockOffRow paymentKnockOffRow=new PaymentKnockOffRow();
		paymentKnockOffRow.setBillAmount(knockOffVO.getBillAmount());
		paymentKnockOffRow.setBillDate(knockOffVO.getBillDate());
		paymentKnockOffRow.setBillNo(knockOffVO.getBillNo());
		paymentKnockOffRow.setBookingDate(knockOffVO.getBookingDate());
		paymentKnockOffRow.setBookingRef(knockOffVO.getBookingRef());
		paymentKnockOffRow.setBookingType(knockOffVO.getBookingType());
		paymentKnockOffRow.setGDSorLCC(knockOffVO.getGDSorLCC());
		paymentKnockOffRow.setCompanyId(knockOffVO.getCompanyId());
		return paymentKnockOffRow;
	}

	public  PaymentKnockOffRowTx  buildPaymentKnockOffRowTxData(KnockOffVO knockOffVO,PaymentKnockOffRow paymentKnockOffRow){
		PaymentKnockOffRowTx paymentKnockOffRowTx=new PaymentKnockOffRowTx();
		paymentKnockOffRowTx.setAmount(knockOffVO.getAmount());
		paymentKnockOffRowTx.setBRVorCRV(knockOffVO.getBRVorCRV());
		paymentKnockOffRowTx.setPaymentKnockOffRow(paymentKnockOffRow);
		return paymentKnockOffRowTx;
	}


}
