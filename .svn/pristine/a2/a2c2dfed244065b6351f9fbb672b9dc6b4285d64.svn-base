package com.lintas.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.isl.admin.page.FlightReportPage;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.admin.DAO.BusOrderDao;
import com.lintas.admin.DAO.CrmDetailsDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
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

public class CustomerOrderAction extends ActionSupport implements SessionAware,ModelDriven<OrderCustomer> {
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	private List<OrderCustomer> customerOrder_list; 
	private  OrderCustomer CurrentCustomerdata = new OrderCustomer();
	private  CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	CrmDetailsDao crmdao = new CrmDetailsDao();
	FlightReportPage commonReportPage=new FlightReportPage();
	private String showType;
	Long currentCustomerId;
	
	public String  getCustomerOrderList(){
		try { 
	 	Company companySessionObj=(Company)sessionMap.get("Company");
			User userSessionObj=(User)sessionMap.get("User");
			CompanyFilter companyFilter = companyFilterPage.getCompanyFilter(); 
			companyFilter.setLoginCompany(companySessionObj);
			companyFilter.setLoginUser(userSessionObj); 
			companyFilter.setReportType(companyFilterPage.getCompanyFilter().getReportType());
			companyFilterPage.setCompanyFilter(companyFilter);

			CompanyFilterPage commonReportPage=crmdao.GetAllOrderCustomerDetails(companyFilterPage);
			 if(commonReportPage!=null) 
				 setCompanyFilterPage(commonReportPage);
			 
			

			HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo()); 
			if(Integer.valueOf(userSessionObj.getCompany_userid())==BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId()) 
				historyInfo.changeNature(BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
			
			if(Integer.valueOf(userSessionObj.getCompany_userid())==BrowsingOptionPageEnum.CRM_EMERGENCY_CONTACT_DETAILS.getId())
				historyInfo.changeNature(BrowsingOptionPageEnum.CRM_EMERGENCY_CONTACT_DETAILS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
			new HistoryManager().inSertHistory(historyInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.error("---------Exception............."+e.getMessage());
		}
		return SUCCESS;

	}
	public String showCustomerDetailsOfCustomerOrders(){ 
		System.out.println("CurrentCustomerdata.getId()-------"+CurrentCustomerdata.getId());
		OrderCustomer selectedReportItem=crmdao.getCustomerDetailsByRowId(CurrentCustomerdata.getId());
		if(selectedReportItem!=null){ 
			CurrentCustomerdata = selectedReportItem;
		}
		return SUCCESS; 
	}
	
	
public String updateCustomerDetailsOfCustomerOrders(){
		
	Company companySessionObj=(Company)sessionMap.get("Company");
	User userSessionObj=(User)sessionMap.get("User");
		if(CurrentCustomerdata.getId()==null){
			addActionError("ID is not available for this please try again");
			return ERROR; 
		}else{
			OrderCustomer selectedReportItem=crmdao.updateCustomerDetailsByRowId(CurrentCustomerdata);
			if(selectedReportItem!=null){ 
				CurrentCustomerdata = selectedReportItem;
			}	
			addActionMessage("Sucessfully Updated");
			return SUCCESS; 
		}
		
	}

 
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;
		
	}



	public List<OrderCustomer> getCustomerOrder_list() {
		return customerOrder_list;
	}



	public void setCustomerOrder_list(List<OrderCustomer> customerOrder_list) {
		this.customerOrder_list = customerOrder_list;
	}
	
	public FlightReportPage getCommonReportPage() {
		return commonReportPage;
	}

	public void setCommonReportPage(FlightReportPage commonReportPage) {
		this.commonReportPage = commonReportPage;
	}
	public OrderCustomer getCurrentCustomerdata() {
		return CurrentCustomerdata;
	}
	public void setCurrentCustomerdata(OrderCustomer currentCustomerdata) {
		CurrentCustomerdata = currentCustomerdata;
	}
	@Override
	public OrderCustomer getModel() {
		// TODO Auto-generated method stub
		return CurrentCustomerdata;
	}
	public CompanyFilterPage getCompanyFilterPage() {
		return companyFilterPage;
	}
	public void setCompanyFilterPage(CompanyFilterPage companyFilterPage) {
		this.companyFilterPage = companyFilterPage;
	}
	public Long getCurrentCustomerId() {
		return currentCustomerId;
	}
	public void setCurrentCustomerId(Long currentCustomerId) {
		this.currentCustomerId = currentCustomerId;
	}
	 

}
