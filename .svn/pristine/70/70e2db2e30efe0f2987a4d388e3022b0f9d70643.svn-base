package com.lintas.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.VisaTravelRequestDao;
import com.admin.common.quotation.model.VisaTravelRequest;
import com.admin.common.quotation.model.VisaTravelRequestQuotation;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.VisaOrderDao;
import com.lintas.admin.common.model.VisaOrderRowCancellation;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.hotel.model.HotelReport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class VisaOrderListAction extends ActionSupport implements SessionAware,ModelDriven<VisaOrderRow> {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(VisaOrderListAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VisaTravelRequest visaTravelRequest =null;
	VisaOrderRow visaOrderRow = new VisaOrderRow();
	private List<WalletAmountTranferHistory> payTxInfo=null;
	SessionMap<String , Object> sessionMap;
	VisaOrderDao visaOrderDao=new VisaOrderDao();
	private String showType;
	public List<VisaOrderRow> visaOrderRowList ;
	private List<VisaCreditNote> creditNoteList;
	private HotelReport CurrentHotelReport=null;
	FlightReportPage commonReportPage=new FlightReportPage();
	VisaTravelRequestDao visaTravelRequestDao=new VisaTravelRequestDao();
	private List<VisaTravelRequestQuotation> visaTravelRequestQuotationlist=null;

	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private ReportData ReportData = new ReportData();
	private boolean isCreditIssued = false;
	private ApiProviderDao apiProviderDao =new ApiProviderDao(); 
	
	private List<ApiProvider> apiProviders;


	public String  getCompanyVisaOrders(){
		try {
			User userSessionObj=(User)sessionMap.get("User");
			Company companySessionObj=(Company)sessionMap.get("Company");
			List<ApiProvider> list = apiProviderDao.fetchApiProviderList();
			setApiProviders(list);
			commonReportPage = getCommonReportPage();
			FlightReportFilter flightReportFilter = commonReportPage.getFlightReportFilter();
			flightReportFilter.setLoginCompany(companySessionObj);
			flightReportFilter.setLoginUser(userSessionObj);
			flightReportFilter.setShowtype(showType);
			flightReportFilter.setReportType(commonReportPage.getFlightReportFilter().getReportType());

			/*if (companyEntityList != null) {
			for (CompanyEntity companyEntity : companyEntityList) {
					System.out.println("companyEntity" + companyEntity.getCountryname());
					flightReportFilter.setLocation(companyEntity.getCountryname());
				}
			}*/
			commonReportPage.setFlightReportFilter(flightReportFilter); 

			//			CarReportPage carReportPageNew=new CarReportPage();
			commonReportPage=visaOrderDao.getVisaOrders(commonReportPage);
			/*setCarReportPage(carReportPageNew);*/
		} catch (Exception e) {
		}
		return SUCCESS;
	}



	public String showPassengerDetailsOfVisaOrders(){
		User userSessionObj=(User)sessionMap.get("User");
		VisaOrderRow visaOrderRowNew=visaTravelRequestDao.getVisaOrderRowDetailsById(visaOrderRow.getId());
		List<VisaTravelRequestQuotation>visaTravelRequestQuotationlist = visaTravelRequestDao.getVisaRequestTravelQuotationBookedList(visaOrderRowNew.getId());
		if(visaTravelRequestQuotationlist!=null && visaTravelRequestQuotationlist.size()>0){
			setVisaTravelRequestQuotationlist(visaTravelRequestQuotationlist);
		}
		if(visaOrderRowNew!=null){
			List<WalletAmountTranferHistory> payTxList = visaTravelRequestDao.getVisaOrderPaymentInfo(visaOrderRowNew.getOrderId(),userSessionObj.getId());
			if(payTxList!=null){
				setPayTxInfo(payTxList);
			}
			setVisaOrderRow(visaOrderRowNew);
		} 
		Company companySessionObj=(Company)sessionMap.get("Company");
		
		HotelReport currentHotelReport = new HotelReport();
		currentHotelReport.setOrderRef(visaOrderRowNew.getOrderId());
		currentHotelReport.setCurCode(visaOrderRowNew.getBookingCurrency());
		currentHotelReport.setTotal(visaOrderRowNew.getTotalAmount().add(visaOrderRowNew.getServiceTax()).setScale(2, BigDecimal.ROUND_UP));
		currentHotelReport.setTax(visaOrderRowNew.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP));
		currentHotelReport.setPaymentStatus(visaOrderRowNew.getPaymentStatus());
		currentHotelReport.setBookingMode(visaOrderRowNew.getBookingMode());
		currentHotelReport.setStatus(visaOrderRowNew.getStatusAction());
		currentHotelReport.setId(visaOrderRowNew.getId());
		currentHotelReport.setUserId(visaOrderRowNew.getUserId());
		currentHotelReport.setCompanyId(visaOrderRowNew.getCompanyId());
		currentHotelReport.setCreditNoteIssued(visaOrderRowNew.isCreditNoteIssued());
		currentHotelReport.setOrderUpdated(visaOrderRowNew.isOrderUpdated());
		currentHotelReport.setReferenceCode(visaOrderRowNew.getOrderId());
		currentHotelReport.setOrderRequested(visaOrderRowNew.isOrderRequested());
		currentHotelReport.setFinalPrice(currentHotelReport.getTotal());
		currentHotelReport.setConfirmNo(visaOrderRowNew.getConfirmationNumber());
		currentHotelReport.setCreatedBy(visaOrderRowNew.getCreatedBy());
		if(visaOrderRowNew.getProcessingFees()!=null)
			currentHotelReport.setConvenienceFees(visaOrderRowNew.getProcessingFees().setScale(2, BigDecimal.ROUND_UP));
		else
			currentHotelReport.setConvenienceFees(new BigDecimal(0));
		 
		VisaOrderRowCancellation horCancelobj=visaOrderDao.getVisaOrderRowCancellation(visaOrderRowNew.getOrderId());
		if(horCancelobj!=null && horCancelobj.getAPIChargeAmount()!=null){
			currentHotelReport.setApiChargeAmount(horCancelobj.getAPIChargeAmount().setScale(2, BigDecimal.ROUND_UP));
			currentHotelReport.setCancelInitiated(true);
		}
		else
			currentHotelReport.setCancelInitiated(false);


		if(horCancelobj!=null) 
			currentHotelReport.setVisaOrderRowCancellation(horCancelobj);



		// remove code from above 


		setVisaOrderRow(visaOrderRowNew);
		CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(companySessionObj.getCompanyid());

		VisaOrderDao  visaOrderDao=new VisaOrderDao();
		ReportData newReportData= visaOrderDao.getReportDetailsByRowId(visaOrderRow.getId());

		newReportData.setAppkey(companyConfig.getAppKey());
		
		setVisaOrderRow(visaOrderRowNew);
		setIsCreditIssued(false);
		// get credit note of current company 
		VisaCreditNote creditNoteObj=visaOrderDao.visaCreditNoteData(visaOrderRow.getId(),companySessionObj.getCompanyid());
		newReportData.setMyVisaCreditNote(creditNoteObj);
		VisaCreditNote creditNoteObjParent = null;
		setCreditNoteList(visaOrderDao.loadCreditNoteListByRowId(visaOrderRow.getId()));
		try {
			List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
			if(parentUserIds!=null && parentUserIds.size()>0)
			{
				creditNoteObjParent=visaOrderDao.visaCreditNoteData(visaOrderRow.getId(),parentUserIds.get(0));
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

		return SUCCESS;
	}

	@Override
	public VisaOrderRow getModel() {
		return visaOrderRow;
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

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public FlightReportPage getCommonReportPage() {
		return commonReportPage;
	}

	public void setCommonReportPage(FlightReportPage commonReportPage) {
		this.commonReportPage = commonReportPage;
	}

	public VisaOrderRow getVisaOrderRow() {
		return visaOrderRow;
	}

	public void setVisaOrderRow(VisaOrderRow visaOrderRow) {
		this.visaOrderRow = visaOrderRow;
	}

	public List<VisaOrderRow> getVisaOrderRowList() {
		return visaOrderRowList;
	}

	public void setVisaOrderRowList(List<VisaOrderRow> visaOrderRowList) {
		this.visaOrderRowList = visaOrderRowList;
	}


	public VisaTravelRequest getVisaTravelRequest() {
		return visaTravelRequest;
	}



	public void setVisaTravelRequest(VisaTravelRequest visaTravelRequest) {
		this.visaTravelRequest = visaTravelRequest;
	}



	public List<VisaTravelRequestQuotation> getVisaTravelRequestQuotationlist() {
		return visaTravelRequestQuotationlist;
	}



	public void setVisaTravelRequestQuotationlist(List<VisaTravelRequestQuotation> visaTravelRequestQuotationlist) {
		this.visaTravelRequestQuotationlist = visaTravelRequestQuotationlist;
	}



	public List<WalletAmountTranferHistory> getPayTxInfo() {
		return payTxInfo;
	}



	public void setPayTxInfo(List<WalletAmountTranferHistory> payTxInfo) {
		this.payTxInfo = payTxInfo;
	}

	public HotelReport getCurrentHotelReport() {
		return CurrentHotelReport;
	}



	public void setCurrentHotelReport(HotelReport currentHotelReport) {
		CurrentHotelReport = currentHotelReport;
	}



	public List<VisaCreditNote> getCreditNoteList() {
		return creditNoteList;
	}



	public void setCreditNoteList(List<VisaCreditNote> creditNoteList) {
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
