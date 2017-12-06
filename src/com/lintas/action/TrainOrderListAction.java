package com.lintas.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.TrainTravelRequest;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.TrainOrderDao;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.train.model.TrainOrderRow;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TrainOrderListAction extends ActionSupport implements SessionAware,ModelDriven<TrainOrderRow>{

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainOrderListAction.class);


	/**
	 *  @author BASHA
	 */
	private static final long serialVersionUID = 1L;

	TrainOrderRow trainOrderRow = new TrainOrderRow();
	SessionMap<String , Object> sessionMap;
	TrainOrderDao trainOrderDao=new TrainOrderDao();
	private Long id;
	private List<WalletAmountTranferHistory> payTxInfo=null;
	TrainTravelRequestDao trainTravelRequestDao=new TrainTravelRequestDao();
	private List<TrainTravelRequestQuotation> trainTravelRequestQuotationlist=null;
	private TrainTravelRequest trainTravelRequest =null;


	public List<TrainOrderRow> trainOrderRowList ;
	//	CarReportPage carReportPage=new CarReportPage();
	FlightReportPage commonReportPage=new FlightReportPage();

	private List<TrainCreditNote> creditNoteList;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private ReportData ReportData = new ReportData();

	private boolean isCreditIssued = false;
	private ApiProviderDao apiProviderDao =new ApiProviderDao(); 
	
	private List<ApiProvider> apiProviders;

	public String  getCompanyTrainOrders(){


		try {
			User userSessionObj=(User)sessionMap.get("User");
			Company companySessionObj=(Company)sessionMap.get("Company");
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
			commonReportPage = getCommonReportPage();
			FlightReportFilter flightReportFilter = commonReportPage.getFlightReportFilter();
			flightReportFilter.setLoginCompany(companySessionObj);
			flightReportFilter.setLoginUser(userSessionObj);
			flightReportFilter.setReportType(commonReportPage.getFlightReportFilter().getReportType());

			commonReportPage.setFlightReportFilter(flightReportFilter); 

			//			CarReportPage carReportPageNew=new CarReportPage();
			commonReportPage=trainOrderDao.getTrainOrders(commonReportPage);
			/*setCarReportPage(carReportPageNew);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;


	}

	public String showPassengerDetailsOfTrainOrders(){
		User userSessionObj = (User)sessionMap.get("User");
		TrainOrderRow trainOrderRowNew=trainTravelRequestDao.getTrainOrderRowDetailsById(trainOrderRow.getId());
		trainTravelRequestQuotationlist = trainTravelRequestDao.getTrainRequestTravelQuotationBookedList(trainOrderRowNew.getId());
		if(trainTravelRequestQuotationlist!=null && trainTravelRequestQuotationlist.size()>0){
			setTrainTravelRequestQuotationlist(trainTravelRequestQuotationlist);
		}
		if(trainOrderRowNew!=null){
			List<WalletAmountTranferHistory> payTxList = trainTravelRequestDao.getTrainOrderPaymentInfo(trainOrderRowNew.getOrderId(),userSessionObj.getId());
			if(payTxList!=null){
				setPayTxInfo(payTxList);
			}
			setTrainOrderRow(trainOrderRowNew);
			Company companySessionObj = (Company)sessionMap.get("Company");
			
			CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(companySessionObj.getCompanyid());

			TrainOrderDao  trainOrderDao=new TrainOrderDao();
			ReportData newReportData= trainOrderDao.getReportDetailsByRowId(trainOrderRow.getId());

			newReportData.setAppkey(companyConfig.getAppKey());
			setTrainOrderRow(trainOrderRowNew);
			setIsCreditIssued(false);
			// get credit note of current company 
			TrainCreditNote creditNoteObj=trainOrderDao.trainCreditNoteData(trainOrderRow.getId(),companySessionObj.getCompanyid());
			newReportData.setMyTrainCreditNote(creditNoteObj);
			TrainCreditNote creditNoteObjParent = null;
			setCreditNoteList(trainOrderDao.loadCreditNoteListById(trainOrderRow.getId()));
			try {
				List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
				if(parentUserIds!=null && parentUserIds.size()>0)
				{
					creditNoteObjParent=trainOrderDao.trainCreditNoteData(trainOrderRow.getId(),parentUserIds.get(0));
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
				ReportData = newReportData;
			} 
		} 
		return SUCCESS;
	}





	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}



	public FlightReportPage getCommonReportPage() {
		return commonReportPage;
	}

	public void setCommonReportPage(FlightReportPage commonReportPage) {
		this.commonReportPage = commonReportPage;
	}



	public List<TrainOrderRow> getTrainOrderRowList() {
		return trainOrderRowList;
	}

	public void setTrainOrderRowList(List<TrainOrderRow> trainOrderRowList) {
		this.trainOrderRowList = trainOrderRowList;
	}

	public List<WalletAmountTranferHistory> getPayTxInfo() {
		return payTxInfo;
	}

	public void setPayTxInfo(List<WalletAmountTranferHistory> payTxInfo) {
		this.payTxInfo = payTxInfo;
	}

	public List<TrainTravelRequestQuotation> getTrainTravelRequestQuotationlist() {
		return trainTravelRequestQuotationlist;
	}

	public void setTrainTravelRequestQuotationlist(List<TrainTravelRequestQuotation> trainTravelRequestQuotationlist) {
		this.trainTravelRequestQuotationlist = trainTravelRequestQuotationlist;
	}

	public TrainOrderRow getTrainOrderRow() {
		return trainOrderRow;
	}

	public void setTrainOrderRow(TrainOrderRow trainOrderRow) {
		this.trainOrderRow = trainOrderRow;
	}

	public TrainTravelRequest getTrainTravelRequest() {
		return trainTravelRequest;
	}

	public void setTrainTravelRequest(TrainTravelRequest trainTravelRequest) {
		this.trainTravelRequest = trainTravelRequest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public TrainOrderRow getModel() {
		// TODO Auto-generated method stub
		return trainOrderRow;
	}

	public List<TrainCreditNote> getCreditNoteList() {
		return creditNoteList;
	}

	public void setCreditNoteList(List<TrainCreditNote> creditNoteList) {
		this.creditNoteList = creditNoteList;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getDetailType() {
		return detailType;
	}

	public void setDetailType(int detailType) {
		this.detailType = detailType;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public ReportData getReportData() {
		return ReportData;
	}

	public void setReportData(ReportData reportData) {
		ReportData = reportData;
	}

	public boolean getIsCreditIssued() {
		return isCreditIssued;
	}

	public void setIsCreditIssued(boolean isCreditIssued) {
		this.isCreditIssued = isCreditIssued;
	}

	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}

	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}





}
