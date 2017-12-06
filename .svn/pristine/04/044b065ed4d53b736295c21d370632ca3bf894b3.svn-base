package com.admin.miscellaneous.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.miscellaneous.dao.MiscellaneousOrderDao;
import com.admin.miscellaneous.dao.MiscellaneousOrderReportDao;
import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.miscellaneous.model.MiscellaneousTravelRequest;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manish Kumar
 *
 */
public class MiscellaneousOrderListAction extends ActionSupport implements SessionAware,ModelDriven<MiscellaneousOrderRow>{

	private static final long serialVersionUID = 1L;
	MiscellaneousOrderRow miscellaneousOrderRow=new MiscellaneousOrderRow();
	MiscellaneousTravelRequest miscellaneousTravelRequest=new MiscellaneousTravelRequest();
	FlightReportPage commonReportPage=new FlightReportPage();
	private String showType;
	SessionMap<String , Object> sessionMap;
	MiscellaneousOrderReportDao orderReportDao=new MiscellaneousOrderReportDao();
	private List<WalletAmountTranferHistory> payTxInfo=null;
	private boolean isCreditIssued = false;
	private ReportData ReportData = new ReportData();
	private List<MiscellaneousCreditNote> creditNoteList;
	private List<ApiProvider> apiProviders;
	ApiProviderDao apiProviderDao = new ApiProviderDao();
	
	public String  getCompanyMiscellaneousOrders(){
		try {
			User userSessionObj=(User)sessionMap.get("User");
			Company companySessionObj=(Company)sessionMap.get("Company");
			commonReportPage = getCommonReportPage();
			FlightReportFilter flightReportFilter = commonReportPage.getFlightReportFilter();
			flightReportFilter.setLoginCompany(companySessionObj);
			flightReportFilter.setLoginUser(userSessionObj);
			flightReportFilter.setReportType(commonReportPage.getFlightReportFilter().getReportType());

			commonReportPage.setFlightReportFilter(flightReportFilter); 

			commonReportPage=orderReportDao.getMiscellaneousOrders(commonReportPage);
			try{
				List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
				setApiProviders(list);
			}catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}
	public String showPassengerDetailsOfMiscellaneousReport(){
		MiscellaneousOrderDao miscellaneousOrderDao=new MiscellaneousOrderDao();
		User userSessionObj = (User)sessionMap.get("User");
		MiscellaneousOrderRow miscellaneousOrderRowNew=miscellaneousOrderDao.getMiscellaneousOrderRowById(miscellaneousOrderRow.getId());
		MiscellaneousTravelRequest travelRequest=miscellaneousOrderDao.getMiscellaneousTravelRequestByConfirmationNumber(miscellaneousOrderRowNew.getConfirmationNumber());
//		List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotationlist = insuranceTravelRequestDao.getInsuranceRequestTravelQuotationBookedList(insuranceOrderRowNew.getId());
		if(travelRequest!=null && travelRequest.getId()>0){
			setMiscellaneousTravelRequest(travelRequest);
		}
		if(miscellaneousOrderRowNew!=null){
			List<WalletAmountTranferHistory> payTxList = miscellaneousOrderDao.getMiscellaneousOrderPaymentInfo(miscellaneousOrderRowNew.getOrderId(),userSessionObj.getId());
			if(payTxList!=null){
				setPayTxInfo(payTxList);
			}

//			setMiscellaneousOrderRow(miscellaneousOrderRowNew);
			Company companySessionObj = (Company)sessionMap.get("Company");
			
			CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(companySessionObj.getCompanyid());

//			InsuranceOrderDao  insuranceOrderDao=new InsuranceOrderDao();
			ReportData newReportData=new ReportData();// insuranceOrderDao.getReportDetailsByRowId(miscellaneousOrderRow.getId());
			orderReportDao.buildReportData(miscellaneousOrderRowNew, newReportData);

			newReportData.setAppkey(companyConfig.getAppKey());
			setMiscellaneousOrderRow(miscellaneousOrderRowNew);
			setCreditIssued(false);
			// get credit note of current company 
			MiscellaneousCreditNote creditNoteObj=miscellaneousOrderDao.miscellaneousCreditNoteData(miscellaneousOrderRow.getId(),companySessionObj.getCompanyid());
			newReportData.setMyMiscellaneousCreditNote(creditNoteObj);
			MiscellaneousCreditNote creditNoteObjParent = null;
			setCreditNoteList(miscellaneousOrderDao.loadCreditNoteListById(miscellaneousOrderRow.getId()));
			try {
				List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
				if(parentUserIds!=null && parentUserIds.size()>0)
				{
					creditNoteObjParent=miscellaneousOrderDao.miscellaneousCreditNoteData(miscellaneousOrderRow.getId(),parentUserIds.get(0));
					if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent())
						setCreditIssued(true);
					else if(creditNoteObjParent != null && creditNoteObjParent.isOrderUpdated())
						setCreditIssued(true);

				}
			} catch (Exception e) {
//				logger.error(e);
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
	public MiscellaneousOrderRow getModel() {
		return miscellaneousOrderRow;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;
	}

	public MiscellaneousOrderRow getMiscellaneousOrderRow() {
		return miscellaneousOrderRow;
	}

	public FlightReportPage getCommonReportPage() {
		return commonReportPage;
	}

	public String getShowType() {
		return showType;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setMiscellaneousOrderRow(MiscellaneousOrderRow miscellaneousOrderRow) {
		this.miscellaneousOrderRow = miscellaneousOrderRow;
	}

	public void setCommonReportPage(FlightReportPage commonReportPage) {
		this.commonReportPage = commonReportPage;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public MiscellaneousTravelRequest getMiscellaneousTravelRequest() {
		return miscellaneousTravelRequest;
	}
	public void setMiscellaneousTravelRequest(MiscellaneousTravelRequest miscellaneousTravelRequest) {
		this.miscellaneousTravelRequest = miscellaneousTravelRequest;
	}
	public List<WalletAmountTranferHistory> getPayTxInfo() {
		return payTxInfo;
	}
	public void setPayTxInfo(List<WalletAmountTranferHistory> payTxInfo) {
		this.payTxInfo = payTxInfo;
	}
	public boolean isCreditIssued() {
		return isCreditIssued;
	}
	public void setCreditIssued(boolean isCreditIssued) {
		this.isCreditIssued = isCreditIssued;
	}
	public ReportData getReportData() {
		return ReportData;
	}
	public void setReportData(ReportData reportData) {
		ReportData = reportData;
	}
	public List<MiscellaneousCreditNote> getCreditNoteList() {
		return creditNoteList;
	}
	public void setCreditNoteList(List<MiscellaneousCreditNote> creditNoteList) {
		this.creditNoteList = creditNoteList;
	}
	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}
	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}
	
	
}
