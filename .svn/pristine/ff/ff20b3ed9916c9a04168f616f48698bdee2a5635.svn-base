/**
 * 
 */
package com.admin.lookbook.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.FlightLoookBook.Filter.LookBookFilterPage;
import com.FlightLoookBook.Filter.LookBookReportFilter;
import com.admin.lookbook.dao.LookBookDao;
import com.admin.lookbook.model.BusBook;
import com.admin.lookbook.model.BusLook;
import com.admin.lookbook.model.FlightBook;
import com.admin.lookbook.model.FlightLook;
import com.admin.lookbook.model.HotelBook;
import com.admin.lookbook.model.HotelLook;
import com.admin.lookbook.model.LookBookCustomerIPStatus;
import com.admin.lookbook.model.LookBookRequest;
import com.admin.lookbook.model.LookBookResponse;
import com.admin.lookbook.model.LookOrBookResponse;
import com.customerIp.filter.CustomerIpFilterPage;
import com.customerIp.filter.CustomerIpReportFilter;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.FlightReportPage;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;

/**
 * @author : created by Manish Samrat and modified by basha 20-07-2017
 * @createdAt : 17/07/2017
 * @version
 */
public class LookBookAction extends ActionSupport implements SessionAware, ModelDriven<LookBookRequest> {

	private static final long serialVersionUID = 1L;
	LookBookRequest lookBookRequest = new LookBookRequest();
	SessionMap<String, Object> sessionMap;
	LookBookDao lookBookDao = new LookBookDao();
	LookBookResponse lookBookResponse = new LookBookResponse();
	LookOrBookResponse lookOrBookResponse = new LookOrBookResponse();
	List<LookBookResponse> lookBookResponses = new ArrayList<LookBookResponse>();
	LookBookFilterPage lookBookFilterPage = new   LookBookFilterPage();
	int configIds;
	int companyId;
	String searchType;
	List<FlightLook> flightLookresponse=new ArrayList<>();
	List<HotelLook> hotelLookresponse=new ArrayList<>();
	List<BusLook> busLookresponse=new ArrayList<>();
	List<FlightBook> flightBookresponse=new ArrayList<>();
	List<HotelBook> hotelBookresponse=new ArrayList<>();
	List<BusBook> busBookresponse=new ArrayList<>();
	CustomerIpFilterPage customerIpFilterPage = new   CustomerIpFilterPage();
	LookBookCustomerIPStatus customerIPStatus=new LookBookCustomerIPStatus();

	public String fetchFlightLookBookDetails() {
		Company companySessionObj=(Company)sessionMap.get("Company");
		lookBookFilterPage = getLookBookFilterPage();
		LookBookReportFilter lookBookReportFilter = lookBookFilterPage.getLookBookReportFilter();
		lookBookReportFilter.setLoginCompany(companySessionObj);
		lookBookReportFilter.setReportType(lookBookFilterPage.getLookBookReportFilter().getReportType());
		lookBookFilterPage.setLookBookReportFilter(lookBookReportFilter);
		lookBookFilterPage =lookBookDao.getCompanyFlightLookBookReports(lookBookFilterPage,companySessionObj);
		return SUCCESS;
	}

	public String fetchHotelLookBookDetails() {
		Company companySessionObj=(Company)sessionMap.get("Company");
		lookBookFilterPage = getLookBookFilterPage();
		LookBookReportFilter lookBookReportFilter = lookBookFilterPage.getLookBookReportFilter();
		lookBookReportFilter.setLoginCompany(companySessionObj);
		lookBookReportFilter.setReportType(lookBookFilterPage.getLookBookReportFilter().getReportType());
		lookBookFilterPage.setLookBookReportFilter(lookBookReportFilter);
		lookBookFilterPage =lookBookDao.getCompanyHotelLookBookReports(lookBookFilterPage,companySessionObj);
		return SUCCESS;
	}

	public String fetchBusLookBookDetails() {
		Company companySessionObj=(Company)sessionMap.get("Company");
		lookBookFilterPage = getLookBookFilterPage();
		LookBookReportFilter lookBookReportFilter = lookBookFilterPage.getLookBookReportFilter();
		lookBookReportFilter.setLoginCompany(companySessionObj);
		lookBookReportFilter.setReportType(lookBookFilterPage.getLookBookReportFilter().getReportType());
		lookBookFilterPage.setLookBookReportFilter(lookBookReportFilter);
		lookBookFilterPage =lookBookDao.getCompanyBusLookBookReports(lookBookFilterPage,companySessionObj);
		return SUCCESS;
	}

	public String fetchLookDetailsList() {
		if (searchType.equalsIgnoreCase("flight")){
			List<FlightLook> lookResponse = lookBookDao.fetchFlightLookDetailById(configIds, companyId);
			if(lookResponse!=null && lookResponse.size()>0){
				setFlightLookresponse(lookResponse);
			}
			}
		if (searchType.equalsIgnoreCase("hotel")){
			List<HotelLook> lookResponse = lookBookDao.fetchHotelLookDetailById(configIds, companyId);
			if(lookResponse!=null && lookResponse.size()>0){
				setHotelLookresponse(lookResponse);
			}
			
		}if (searchType.equalsIgnoreCase("bus")){
				List<BusLook> lookResponse = lookBookDao.fetchBusLookDetailById(configIds, companyId);
				if(lookResponse!=null && lookResponse.size()>0){
					setBusLookresponse(lookResponse);
				}
				
		}
			return SUCCESS;
	}

	public String fetchBookDetailsList() {
		if (searchType.equalsIgnoreCase("flight")){
			List<FlightBook> bookResponse = lookBookDao.fetchFlightBookDetailById(configIds, companyId);
			if(bookResponse!=null && bookResponse.size()>0){
				setFlightBookresponse(bookResponse);
			}
			}
		if (searchType.equalsIgnoreCase("hotel")){
			List<HotelBook> bookResponse = lookBookDao.fetchHotelBookDetailById(configIds, companyId);
			if(bookResponse!=null && bookResponse.size()>0){
				setHotelBookresponse(bookResponse);
			}
		}
		if (searchType.equalsIgnoreCase("bus")){
			List<BusBook> bookResponse = lookBookDao.fetchBusBookDetailById(configIds, companyId);
			if(bookResponse!=null && bookResponse.size()>0){
				setBusBookresponse(bookResponse);
			}
		 }
		return SUCCESS;
	}
	
	public String fetchAllIpList() {
		Company companySessionObj=(Company)sessionMap.get("Company");
		customerIpFilterPage = getCustomerIpFilterPage();
		CustomerIpReportFilter customerIpReportFilter = customerIpFilterPage.getCustomerIpReportFilter();
		customerIpReportFilter.setLoginCompany(companySessionObj);
		customerIpReportFilter.setReportType(customerIpFilterPage.getCustomerIpReportFilter().getReportType());
		customerIpFilterPage.setCustomerIpReportFilter(customerIpReportFilter);
		customerIpFilterPage =lookBookDao.fetchAllIpListFromIpStatus(customerIpFilterPage,companySessionObj);
		
		return SUCCESS;
	}
	
	public String fetchAllIpHistoryList() {
		Company companySessionObj=(Company)sessionMap.get("Company");
		customerIpFilterPage = getCustomerIpFilterPage();
		CustomerIpReportFilter customerIpReportFilter = customerIpFilterPage.getCustomerIpReportFilter();
		customerIpReportFilter.setLoginCompany(companySessionObj);
		customerIpReportFilter.setReportType(customerIpFilterPage.getCustomerIpReportFilter().getReportType());
		customerIpFilterPage.setCustomerIpReportFilter(customerIpReportFilter);
		customerIpFilterPage =lookBookDao.fetchAllIpListFromIpHistory(customerIpFilterPage,companySessionObj);
		
		return SUCCESS;
	}
	public String resetIpStatusIndividually() {
		lookBookDao.resetIpStatusById(lookBookRequest.getId());
		return SUCCESS;
	}
	public String resetIpHistoryIndividually() {
		lookBookDao.resetIpHistoryById(lookBookRequest.getId());
		return SUCCESS;
	}

	public String displayIpDetailsById() {
		customerIPStatus=lookBookDao.fetchIpDetailsById(lookBookRequest.getId());
		return SUCCESS;
	}
	
	@Override
	public LookBookRequest getModel() {
		return lookBookRequest;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}

	public LookBookRequest getLookBookRequest() {
		return lookBookRequest;
	}

	public LookBookResponse getLookBookResponse() {
		return lookBookResponse;
	}

	public void setLookBookRequest(LookBookRequest lookBookRequest) {
		this.lookBookRequest = lookBookRequest;
	}

	public void setLookBookResponse(LookBookResponse lookBookResponse) {
		this.lookBookResponse = lookBookResponse;
	}

	public List<LookBookResponse> getLookBookResponses() {
		return lookBookResponses;
	}

	public void setLookBookResponses(List<LookBookResponse> lookBookResponses) {
		this.lookBookResponses = lookBookResponses;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public int getConfigIds() {
		return configIds;
	}

	public void setConfigIds(int configIds) {
		this.configIds = configIds;
	}

	public LookOrBookResponse getLookOrBookResponse() {
		return lookOrBookResponse;
	}

	public void setLookOrBookResponse(LookOrBookResponse lookOrBookResponse) {
		this.lookOrBookResponse = lookOrBookResponse;
	}

	
	public LookBookFilterPage getLookBookFilterPage() {
		return lookBookFilterPage;
	}

	public void setLookBookFilterPage(LookBookFilterPage lookBookFilterPage) {
		this.lookBookFilterPage = lookBookFilterPage;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public List<FlightLook> getFlightLookresponse() {
		return flightLookresponse;
	}

	public void setFlightLookresponse(List<FlightLook> flightLookresponse) {
		this.flightLookresponse = flightLookresponse;
	}

	public List<HotelLook> getHotelLookresponse() {
		return hotelLookresponse;
	}

	public void setHotelLookresponse(List<HotelLook> hotelLookresponse) {
		this.hotelLookresponse = hotelLookresponse;
	}

	public List<BusLook> getBusLookresponse() {
		return busLookresponse;
	}

	public void setBusLookresponse(List<BusLook> busLookresponse) {
		this.busLookresponse = busLookresponse;
	}

	public List<FlightBook> getFlightBookresponse() {
		return flightBookresponse;
	}

	public void setFlightBookresponse(List<FlightBook> flightBookresponse) {
		this.flightBookresponse = flightBookresponse;
	}

	public List<HotelBook> getHotelBookresponse() {
		return hotelBookresponse;
	}

	public void setHotelBookresponse(List<HotelBook> hotelBookresponse) {
		this.hotelBookresponse = hotelBookresponse;
	}

	public List<BusBook> getBusBookresponse() {
		return busBookresponse;
	}

	public void setBusBookresponse(List<BusBook> busBookresponse) {
		this.busBookresponse = busBookresponse;
	}

	public CustomerIpFilterPage getCustomerIpFilterPage() {
		return customerIpFilterPage;
	}

	public void setCustomerIpFilterPage(CustomerIpFilterPage customerIpFilterPage) {
		this.customerIpFilterPage = customerIpFilterPage;
	}

	public LookBookCustomerIPStatus getCustomerIPStatus() {
		return customerIPStatus;
	}

	public void setCustomerIPStatus(LookBookCustomerIPStatus customerIPStatus) {
		this.customerIPStatus = customerIPStatus;
	}

}
