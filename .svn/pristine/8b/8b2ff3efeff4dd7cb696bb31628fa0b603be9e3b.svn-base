package com.isl.admin.page;

import java.io.Serializable;
import java.util.List;

import com.admin.insurance.model.InsuranceMarkup;
import com.admin.knockoff.vo.KnockOffVO;
import com.admin.payment.recievable.PaymentRecievable;
import com.isl.admin.filter.CompanyFilter;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.model.Airport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CrudOperationDeals;
import com.lintas.admin.model.FlightMarkup;
import com.lintas.admin.model.HotelMarkup;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.frontuser.FrontUserDetail;
import com.tayyarah.bus.model.BusMarkup;

public class CompanyFilterPage extends Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompanyFilter  companyFilter;
	private List<User> userList;
	private List<Company> companyList;
	private List<Company> approvalCompanyList;
	private List<CompanyConfig> companyConfigList;
	private List<WalletAmountTranferHistory>  walletTxList;
	private List<WalletAmountTranferHistory> outstandingReportList;
	private List<FlightMarkup> flightMarkupList;
	private List<HotelMarkup> hotelMarkupList;
	private List<BusMarkup> busMarkupList;
	private List<InsuranceMarkup> insuranceMarkupList;
	private List<FrontUserDetail> frontUserDetailList;
	private List<CrudOperationDeals> crudOperationDealsList;
	private List<Airport> airportList; 
	private List<PaymentRecievable> paymentReceivableList; 
	private List<OrderCustomer> orderCustomerList; 
	private List<KnockOffVO> allKnockOffVOReports;
	private WalletAmountTranferHistory walletAmountTranferHistory;
	private int selectedRowIndex;

	public CompanyFilterPage() {
		super();
		this.setCompanyFilter(new CompanyFilter());
		// TODO Auto-generated constructor stub
	}

	public CompanyFilterPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, CompanyFilter companyFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.setCompanyFilter(companyFilter);
		// TODO Auto-generated constructor stub
	}
	public CompanyFilterPage(boolean isPagination, boolean isFilterEnabled, int maxItems,  CompanyFilter companyFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.setCompanyFilter(companyFilter);
		// TODO Auto-generated constructor stub
	}

	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}

	public CompanyFilter getCompanyFilter() {
		return companyFilter;
	}
	public void setCompanyFilter(CompanyFilter companyFilter) {
		this.companyFilter = companyFilter;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Company> getApprovalCompanyList() {
		return approvalCompanyList;
	}

	public void setApprovalCompanyList(List<Company> approvalCompanyList) {
		this.approvalCompanyList = approvalCompanyList;
	}

	public List<CompanyConfig> getCompanyConfigList() {
		return companyConfigList;
	}

	public void setCompanyConfigList(List<CompanyConfig> companyConfigList) {
		this.companyConfigList = companyConfigList;
	}

	public List<WalletAmountTranferHistory> getWalletTxList() {
		return walletTxList;
	}

	public void setWalletTxList(List<WalletAmountTranferHistory> walletTxList) {
		this.walletTxList = walletTxList;
	}

	public List<FlightMarkup> getFlightMarkupList() {
		return flightMarkupList;
	}

	public void setFlightMarkupList(List<FlightMarkup> flightMarkupList) {
		this.flightMarkupList = flightMarkupList;
	}

	public List<HotelMarkup> getHotelMarkupList() {
		return hotelMarkupList;
	}

	public void setHotelMarkupList(List<HotelMarkup> hotelMarkupList) {
		this.hotelMarkupList = hotelMarkupList;
	}

	public List<FrontUserDetail> getFrontUserDetailList() {
		return frontUserDetailList;
	}

	public void setFrontUserDetailList(List<FrontUserDetail> frontUserDetailList) {
		this.frontUserDetailList = frontUserDetailList;
	}

	public List<CrudOperationDeals> getCrudOperationDealsList() {
		return crudOperationDealsList;
	}

	public void setCrudOperationDealsList(List<CrudOperationDeals> crudOperationDealsList) {
		this.crudOperationDealsList = crudOperationDealsList;
	}

	public List<Airport> getAirportList() {
		return airportList;
	}

	public void setAirportList(List<Airport> airportList) {
		this.airportList = airportList;
	}

	public List<WalletAmountTranferHistory> getOutstandingReportList() {
		return outstandingReportList;
	}

	public void setOutstandingReportList(List<WalletAmountTranferHistory> outstandingReportList) {
		this.outstandingReportList = outstandingReportList;
	}

	public List<PaymentRecievable> getPaymentReceivableList() {
		return paymentReceivableList;
	}

	public void setPaymentReceivableList(List<PaymentRecievable> paymentReceivableList) {
		this.paymentReceivableList = paymentReceivableList;
	}

	public List<BusMarkup> getBusMarkupList() {
		return busMarkupList;
	}

	public void setBusMarkupList(List<BusMarkup> busMarkupList) {
		this.busMarkupList = busMarkupList;
	}

	public List<InsuranceMarkup> getInsuranceMarkupList() {
		return insuranceMarkupList;
	}

	public void setInsuranceMarkupList(List<InsuranceMarkup> insuranceMarkupList) {
		this.insuranceMarkupList = insuranceMarkupList;
	}

	public List<OrderCustomer> getOrderCustomerList() {
		return orderCustomerList;
	}

	public void setOrderCustomerList(List<OrderCustomer> orderCustomerList) {
		this.orderCustomerList = orderCustomerList;
	}

	public List<KnockOffVO> getAllKnockOffVOReports() {
		return allKnockOffVOReports;
	}

	public void setAllKnockOffVOReports(List<KnockOffVO> allKnockOffVOReports) {
		this.allKnockOffVOReports = allKnockOffVOReports;
	}

	public WalletAmountTranferHistory getWalletAmountTranferHistory() {
		return walletAmountTranferHistory;
	}

	public void setWalletAmountTranferHistory(WalletAmountTranferHistory walletAmountTranferHistory) {
		this.walletAmountTranferHistory = walletAmountTranferHistory;
	}


}
