package com.lintas.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.InsuranceTravelRequestDao;
import com.admin.common.quotation.model.InsuranceTravelRequest;
import com.admin.common.quotation.model.InsuranceTravelRequestQuotation;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.InsuranceOrderDao;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class InsuranceOrderListAction extends ActionSupport implements SessionAware,ModelDriven<InsuranceOrderRow>{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InsuranceOrderListAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InsuranceOrderRow insuranceOrderRow = new InsuranceOrderRow();
	SessionMap<String , Object> sessionMap;
	InsuranceOrderDao insuranceOrderDao=new InsuranceOrderDao();
	private Long id;
	private List<WalletAmountTranferHistory> payTxInfo=null;
	InsuranceTravelRequestDao insuranceTravelRequestDao=new InsuranceTravelRequestDao();
	private List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotationlist=null;
	private InsuranceTravelRequest insuranceTravelRequest =null;

	public List<InsuranceOrderRow> insuranceOrderRowList ;

	private List<InsuranceCreditNote> creditNoteList;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private ReportData ReportData = new ReportData();

	private boolean isCreditIssued = false;
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	
	FlightReportPage commonReportPage=new FlightReportPage();
	public String  getCompanyInsuranceOrders(){
		try {
			User userSessionObj=(User)sessionMap.get("User");
			Company companySessionObj=(Company)sessionMap.get("Company");
			commonReportPage = getCommonReportPage();
			FlightReportFilter flightReportFilter = commonReportPage.getFlightReportFilter();
			flightReportFilter.setLoginCompany(companySessionObj);
			flightReportFilter.setLoginUser(userSessionObj);
			flightReportFilter.setReportType(commonReportPage.getFlightReportFilter().getReportType());

			commonReportPage.setFlightReportFilter(flightReportFilter); 

			//				InsuranceReportPage carReportPageNew=new InsuranceReportPage();
			commonReportPage=insuranceOrderDao.getInsuranceOrders(commonReportPage);
			/*setInsuranceReportPage(carReportPageNew);*/
			try{
				List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
				setApiProviders(list);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public String showPassengerDetailsOfInsuranceOrders(){
		User userSessionObj = (User)sessionMap.get("User");
		InsuranceOrderRow insuranceOrderRowNew=insuranceTravelRequestDao.getInsuranceOrderRowDetailsById(insuranceOrderRow.getId());
		List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotationlist = insuranceTravelRequestDao.getInsuranceRequestTravelQuotationBookedList(insuranceOrderRowNew.getId());
		if(insuranceTravelRequestQuotationlist!=null && insuranceTravelRequestQuotationlist.size()>0){
			setInsuranceTravelRequestQuotationlist(insuranceTravelRequestQuotationlist);
		}
		if(insuranceOrderRowNew!=null){
			List<WalletAmountTranferHistory> payTxList = insuranceTravelRequestDao.getInsuranceOrderPaymentInfo(insuranceOrderRowNew.getOrderId(),userSessionObj.getId());
			if(payTxList!=null){
				setPayTxInfo(payTxList);
			}

			setInsuranceOrderRow(insuranceOrderRowNew);
			Company companySessionObj = (Company)sessionMap.get("Company");
			
			CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(companySessionObj.getCompanyid());

			InsuranceOrderDao  insuranceOrderDao=new InsuranceOrderDao();
			ReportData newReportData= insuranceOrderDao.getReportDetailsByRowId(insuranceOrderRow.getId());

			newReportData.setAppkey(companyConfig.getAppKey());
			setInsuranceOrderRow(insuranceOrderRowNew);
			setIsCreditIssued(false);
			// get credit note of current company 
			InsuranceCreditNote creditNoteObj=insuranceOrderDao.insuranceCreditNoteData(insuranceOrderRow.getId(),companySessionObj.getCompanyid());
			newReportData.setMyInsuranceCreditNote(creditNoteObj);
			InsuranceCreditNote creditNoteObjParent = null;
			setCreditNoteList(insuranceOrderDao.loadCreditNoteListById(insuranceOrderRow.getId()));
			try {
				List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
				if(parentUserIds!=null && parentUserIds.size()>0)
				{
					creditNoteObjParent=insuranceOrderDao.insuranceCreditNoteData(insuranceOrderRow.getId(),parentUserIds.get(0));
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

		}
		return SUCCESS;
	}



	@Override
	public InsuranceOrderRow getModel() {
		return insuranceOrderRow;
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

	public InsuranceOrderRow getInsuranceOrderRow() {
		return insuranceOrderRow;
	}

	public void setInsuranceOrderRow(InsuranceOrderRow insuranceOrderRow) {
		this.insuranceOrderRow = insuranceOrderRow;
	}

	public List<InsuranceOrderRow> getInsuranceOrderRowList() {
		return insuranceOrderRowList;
	}

	public void setInsuranceOrderRowList(List<InsuranceOrderRow> insuranceOrderRowList) {
		this.insuranceOrderRowList = insuranceOrderRowList;
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

	public List<InsuranceTravelRequestQuotation> getInsuranceTravelRequestQuotationlist() {
		return insuranceTravelRequestQuotationlist;
	}

	public void setInsuranceTravelRequestQuotationlist(
			List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotationlist) {
		this.insuranceTravelRequestQuotationlist = insuranceTravelRequestQuotationlist;
	}

	public InsuranceTravelRequest getInsuranceTravelRequest() {
		return insuranceTravelRequest;
	}

	public void setInsuranceTravelRequest(InsuranceTravelRequest insuranceTravelRequest) {
		this.insuranceTravelRequest = insuranceTravelRequest;
	}

	public List<InsuranceCreditNote> getCreditNoteList() {
		return creditNoteList;
	}

	public void setCreditNoteList(List<InsuranceCreditNote> creditNoteList) {
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



