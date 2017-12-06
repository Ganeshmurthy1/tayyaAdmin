package com.lintas.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.CarTravelRequestDao;
import com.admin.common.quotation.model.CarTravelRequest;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.admin.DAO.CarOrderDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CarOrderListAction extends ActionSupport implements SessionAware,ModelDriven<CarOrderRow>{

	/**
	 *  @author BASHA
	 */

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CarOrderListAction.class);
	private static final long serialVersionUID = 1L;
	CarOrderRow carOrderRow = new CarOrderRow();
	SessionMap<String , Object> sessionMap;
	CarOrderDao carOrderDao=new CarOrderDao();
	public List<CarOrderRow> carOrderRowList ;
	FlightReportPage commonReportPage=new FlightReportPage();
	private Long id;
	private List<WalletAmountTranferHistory> payTxInfo=null;
	CarTravelRequestDao carTravelRequestDao=new CarTravelRequestDao();
	private List<CarTravelRequestQuotation> carTravelRequestQuotationlist=null;
	private CarTravelRequest carTravelRequest =null;
	private List<CarCreditNote> creditNoteList;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private ReportData ReportData = new ReportData();
	private ApiProviderDao apiProviderDao =new ApiProviderDao(); 
	private List<ApiProvider> apiProviders;

	private boolean isCreditIssued = false;


	public String  getCompanyCarOrders(){
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
			commonReportPage=carOrderDao.getCarOrders(commonReportPage);
			/*setCarReportPage(carReportPageNew);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;


	}

	public String showPassengerDetailsOfCarOrders(){
		User userSessionObj = (User)sessionMap.get("User");
		CarOrderRow carOrderRowNew=carTravelRequestDao.getCarOrderRowDetailsById(carOrderRow.getId());
		List<CarTravelRequestQuotation> carTravelRequestQuotationlist = carTravelRequestDao.getCarRequestTravelQuotationOrderBookedList(carOrderRowNew.getId());
		if(carTravelRequestQuotationlist!=null && carTravelRequestQuotationlist.size()>0){
			setCarTravelRequestQuotationlist(carTravelRequestQuotationlist);
		}
		if(carOrderRowNew!=null)
		{
			List<WalletAmountTranferHistory> payTxList = carTravelRequestDao.getCarOrderPaymentInfo(carOrderRowNew.getOrderId(),userSessionObj.getId());
			if(payTxList!=null){
				setPayTxInfo(payTxList);
			}
			Company companySessionObj = (Company)sessionMap.get("Company");
			CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(companySessionObj.getCompanyid());
			CarOrderDao  carOrderDao=new CarOrderDao();
			ReportData newReportData= carOrderDao.getReportDetailsByRowId(carOrderRow.getId());
			newReportData.setAppkey(companyConfig.getAppKey());
			setCarOrderRow(carOrderRowNew);
			setIsCreditIssued(false);
			// get credit note of current company 
			CarCreditNote creditNoteObj=carOrderDao.carCreditNoteData(carOrderRow.getId(),companySessionObj.getCompanyid());
			newReportData.setMyCarCreditNote(creditNoteObj);
			CarCreditNote creditNoteObjParent = null;
			setCreditNoteList(carOrderDao.loadCreditNoteListById(carOrderRow.getId()));
			try {
				List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
				if(parentUserIds!=null && parentUserIds.size()>0)
				{
					creditNoteObjParent=carOrderDao.carCreditNoteData(carOrderRow.getId(),parentUserIds.get(0));
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
	public CarOrderRow getModel() {
		return carOrderRow;
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



	public CarOrderRow getCarOrderRow() {
		return carOrderRow;
	}

	public void setCarOrderRow(CarOrderRow carOrderRow) {
		this.carOrderRow = carOrderRow;
	}

	public List<CarOrderRow> getCarOrderRowList() {
		return carOrderRowList;
	}

	public void setCarOrderRowList(List<CarOrderRow> carOrderRowList) {
		this.carOrderRowList = carOrderRowList;
	}

	public FlightReportPage getCommonReportPage() {
		return commonReportPage;
	}

	public void setCommonReportPage(FlightReportPage commonReportPage) {
		this.commonReportPage = commonReportPage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<WalletAmountTranferHistory> getPayTxInfo() {
		return payTxInfo;
	}
	public void setPayTxInfo(List<WalletAmountTranferHistory> payTxInfo) {
		this.payTxInfo = payTxInfo;
	}
	public List<CarTravelRequestQuotation> getCarTravelRequestQuotationlist() {
		return carTravelRequestQuotationlist;
	}
	public void setCarTravelRequestQuotationlist(List<CarTravelRequestQuotation> carTravelRequestQuotationlist) {
		this.carTravelRequestQuotationlist = carTravelRequestQuotationlist;
	}
	public CarTravelRequest getCarTravelRequest() {
		return carTravelRequest;
	}
	public void setCarTravelRequest(CarTravelRequest carTravelRequest) {
		this.carTravelRequest = carTravelRequest;
	}
	public List<CarCreditNote> getCreditNoteList() {
		return creditNoteList;
	}
	public void setCreditNoteList(List<CarCreditNote> creditNoteList) {
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
