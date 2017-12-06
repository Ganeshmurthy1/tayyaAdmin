package com.lintas.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.admin.DAO.BusOrderDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.bus.model.BusCustomerDetail;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.common.model.BusOrderRowCancellation;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.bus.model.BusBlockedSeatTemp;
import com.tayyarah.bus.model.BusOrderCustomerDetail;

public class BusOrderListAction extends ActionSupport implements SessionAware,ModelDriven<BusOrderRow> {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BusOrderListAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String , Object> sessionMap;
	BusOrderDao busOrderDao=new BusOrderDao();
	private List<WalletAmountTranferHistory> payTxInfo=null;
	public List<BusOrderRow> busOrderRowList ;
	//	BusReportPage busReportPage=new BusReportPage();
	FlightReportPage commonReportPage=new FlightReportPage();
	BusTravelRequestDao busTravelRequestDao=new BusTravelRequestDao();
	BusOrderRow busOrderRow=new BusOrderRow();
	private List<BusTravelRequestQuotation> busTravelRequestQuotationlist=new ArrayList<>();
	private String seatNos;

	private List<BusCreditNote> creditNoteList;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private ReportData ReportData = new ReportData();
	private boolean isCreditIssued = false;
	private BusOrderRowCancellation busOrderRowCancellation=new BusOrderRowCancellation();
	private ApiProviderDao apiProviderDao =new ApiProviderDao(); 
	
	private List<ApiProvider> apiProviders;

	public String  getCompanyBusOrders(){

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
			commonReportPage=busOrderDao.getBusOrders(commonReportPage);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;


	}

	public String showPassengerDetailsOfBusOrders(){
		User userSessionObj = (User)sessionMap.get("User");
		BusOrderRow busOrderRowNew = busTravelRequestDao.getBusOrderRowDetailsById(busOrderRow.getId());		
		if(busOrderRowNew != null){
			List<BusCustomerDetail> BusCustomerDetailList = new ArrayList<>();
			List<BusTravelRequestQuotation> busTravelRequestQuotationlist = busTravelRequestDao.getBusRequestTravelQuotationBookedList(busOrderRowNew.getId());
			if(busTravelRequestQuotationlist!=null && busTravelRequestQuotationlist.size()>0){
				for (BusTravelRequestQuotation busTravelRequestQuotation : busTravelRequestQuotationlist) {
					BusCustomerDetail busCustomerDetail = new BusCustomerDetail();
					busCustomerDetail.setEmail(busTravelRequestQuotation.getBusOrderRow().getOrderCustomer().getEmail());
					busCustomerDetail.setAge(busTravelRequestQuotation.getBusOrderRow().getOrderCustomer().getAge());
					busCustomerDetail.setFirstName(busTravelRequestQuotation.getBusOrderRow().getOrderCustomer().getFirstName());
					busCustomerDetail.setLastName(busTravelRequestQuotation.getBusOrderRow().getOrderCustomer().getLastName());
					busCustomerDetail.setMobile(busTravelRequestQuotation.getBusOrderRow().getOrderCustomer().getMobile());
					busCustomerDetail.setTitle(busTravelRequestQuotation.getBusOrderRow().getOrderCustomer().getTitle());
					BusCustomerDetailList.add(busCustomerDetail);
				}
			}	

		}

		List<WalletAmountTranferHistory> payTxList = busTravelRequestDao.getBusOrderPaymentInfo(busOrderRowNew.getOrderId(),userSessionObj.getId());
		if(payTxList!=null){
			setPayTxInfo(payTxList);
		}


		Company companySessionObj = (Company)sessionMap.get("Company");			
		CompanyConfig companyConfig = new CompanyConfigDao().getConfigByComnpanyId(companySessionObj.getCompanyid());

		BusOrderDao  busOrderDao=new BusOrderDao();
		ReportData newReportData= busOrderDao.getReportDetailsByRowId(busOrderRow.getId());
		//added by  basha on 15-06-2016  for bus online cancellation
		BusOrderRowCancellation cancelationData=new CreditNoteDao().getBusOrderRowCancellationWithAPIStatus(busOrderRowNew.getOrderId());
		setBusOrderRowCancellation(cancelationData);
		
		
		newReportData.setAppkey(companyConfig.getAppKey());
		
		List<BusOrderCustomerDetail> buscustomerList = busTravelRequestDao.getBusOrderCustomerDetailList(busOrderRowNew.getId());
		if(buscustomerList!=null && buscustomerList.size()>0)
		{
			StringBuffer seatnos = new StringBuffer();
			int i = 0;	
			for(BusOrderCustomerDetail busOrderCustomerDetail:buscustomerList){
				seatnos.append(busOrderCustomerDetail.getSeatNo());
				if(i != buscustomerList.size()-1){
					seatnos.append(",");
				}
				i++;
			}
			seatNos = seatnos.toString();
		}
		
		busOrderRowNew.setAppkey(companyConfig.getAppKey());
		if(busOrderRowNew.getTransactionKey()!=null){
			busOrderRowNew.setSearchkey(getSearchkeyofCurrentBusOrder(busOrderRowNew.getTransactionKey()).getSearchKey());
		}
		setBusOrderRow(busOrderRowNew);
		setIsCreditIssued(false);
		// get credit note of current company 
		BusCreditNote creditNoteObj=busOrderDao.busCreditNoteData(busOrderRow.getId(),companySessionObj.getCompanyid());
		newReportData.setMyBusCreditNote(creditNoteObj);
		BusCreditNote creditNoteObjParent = null;
		setCreditNoteList(busOrderDao.loadCreditNoteListById(busOrderRow.getId()));
		try {
			List<Integer> parentUserIds=new CreditNoteDao().getParentUserIdLevel2(userSessionObj.getId());
			if(parentUserIds!=null && parentUserIds.size()>0)
			{
				creditNoteObjParent=busOrderDao.busCreditNoteData(busOrderRow.getId(),parentUserIds.get(0));
				if(!companySessionObj.getCompanyRole().isDistributor() && !companySessionObj.getCompanyRole().isAgent())
					setIsCreditIssued(true);
				else if(creditNoteObjParent != null && creditNoteObjParent.isOrderUpdated())
					setIsCreditIssued(true);

			}
		} catch (Exception e) {
			logger.error(e);
		}

		if(newReportData!=null){
			if(cancelationData!=null && cancelationData.getOrderId()!=null){
				newReportData.setCancellationFees(cancelationData.getAPIChargeAmount().setScale(3));
			}
			if(creditNoteObj!=null){
				newReportData.setCreditNoteActiontype(creditNoteObj.getActionType());
				if(creditNoteObjParent!=null && creditNoteObjParent.getCancellationFees()!=null){
					newReportData.setCancellationFees(creditNoteObjParent.getCancellationFees());
				}
				if(creditNoteObj.getConvenienceFees()!=null){
					newReportData.setConvenienceFees(creditNoteObj.getConvenienceFees());
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
		return SUCCESS;
	} 



	//added by  basha on 15-06-2016  for bus online cancellation

	public BusBlockedSeatTemp getSearchkeyofCurrentBusOrder(String transaction_key){
		BusBlockedSeatTemp  busBlockedSeatTemp= null;
		Session session=null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusBlockedSeatTemp.class);
			criteria.add(Restrictions.eq("transactionKey", transaction_key));
			busBlockedSeatTemp = (BusBlockedSeatTemp) criteria.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();
			logger.info("-----Exception in getSearchkeyofCurrentBusOrder----------"+e);
		}finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return busBlockedSeatTemp;
	}



	@Override
	public BusOrderRow getModel() {
		// TODO Auto-generated method stub
		return busOrderRow;
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


	public List<BusOrderRow> getBusOrderRowList() {
		return busOrderRowList;
	}

	public void setBusOrderRowList(List<BusOrderRow> busOrderRowList) {
		this.busOrderRowList = busOrderRowList;
	}

	public List<BusTravelRequestQuotation> getBusTravelRequestQuotationlist() {
		return busTravelRequestQuotationlist;
	}

	public void setBusTravelRequestQuotationlist(List<BusTravelRequestQuotation> busTravelRequestQuotationlist) {
		this.busTravelRequestQuotationlist = busTravelRequestQuotationlist;
	}

	public List<WalletAmountTranferHistory> getPayTxInfo() {
		return payTxInfo;
	}

	public void setPayTxInfo(List<WalletAmountTranferHistory> payTxInfo) {
		this.payTxInfo = payTxInfo;
	}

	public BusOrderRow getBusOrderRow() {
		return busOrderRow;
	}

	public void setBusOrderRow(BusOrderRow busOrderRow) {
		this.busOrderRow = busOrderRow;
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

	public List<BusCreditNote> getCreditNoteList() {
		return creditNoteList;
	}

	public void setCreditNoteList(List<BusCreditNote> creditNoteList) {
		this.creditNoteList = creditNoteList;
	}
	public boolean getIsCreditIssued() {
		return isCreditIssued;
	}

	public void setIsCreditIssued(boolean isCreditIssued) {
		this.isCreditIssued = isCreditIssued;
	}

	public String getSeatNos() {
		return seatNos;
	}

	public void setSeatNos(String seatNos) {
		this.seatNos = seatNos;
	}

	public BusOrderRowCancellation getBusOrderRowCancellation() {
		return busOrderRowCancellation;
	}

	public void setBusOrderRowCancellation(BusOrderRowCancellation busOrderRowCancellation) {
		this.busOrderRowCancellation = busOrderRowCancellation;
	}

	public List<ApiProvider> getApiProviders() {
		return apiProviders;
	}

	public void setApiProviders(List<ApiProvider> apiProviders) {
		this.apiProviders = apiProviders;
	}

	
}
