package com.lintas.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.FlightCommissionDao;
import com.lintas.admin.DAO.HotelCommissionDao;
import com.lintas.admin.flight.model.FlightCommissionReport;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.HotelCommissionReport;
import com.lintas.admin.model.User;
import com.lintas.utility.DateFilter;
import com.lintas.utility.InvoiceFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class AgentCommissionReportAction  extends ActionSupport implements SessionAware,ModelDriven<ReportData>{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = 6072343153889802265L;
	ReportData reportData=new ReportData();
	SessionMap<String , Object> sessionMap;
	FlightCommissionDao flightCommissionDao=new FlightCommissionDao();
	HotelCommissionDao hotelCommissionDao=new HotelCommissionDao();
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AgentCommissionReportAction.class);
	List<FlightCommissionReport> companyCommissionReportList;
	FlightCommissionReport agentTotalReportCommObj;
	List<HotelCommissionReport> hotelCommissionReportList;
	HotelCommissionReport hotelAgentagentTotalReportCommObj;
	// action method for flight agent commission report
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	public String getFlightAgentCommissionReport(){
		 
		 agentTotalReportCommObj=new FlightCommissionReport();
		BigDecimal totComm =new BigDecimal("0.00") ;
		BigDecimal totSpentAmount =new BigDecimal("0.00") ;
		BigDecimal totTicketPrice =new BigDecimal("0.00") ;
		BigDecimal totMyMarkup =new BigDecimal("0.00") ;
		BigDecimal totWholeSalerMarkup =new BigDecimal("0.00") ;
		BigDecimal totAgencyMarkup =new BigDecimal("0.00") ;
		BigDecimal totMyCommission =new BigDecimal("0.00") ;
		BigDecimal totSharedCommission =new BigDecimal("0.00") ;
		 
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		InvoiceFilter filterObj=new InvoiceFilter();
		companyCommissionReportList = flightCommissionDao.getFlightCommissionReport(userSessionObj, companySessionObj,filterObj);
		logger.info("....companyCommissionReportList....."+companyCommissionReportList.size());

		if(companyCommissionReportList!=null && companyCommissionReportList.size()>0){
			for(FlightCommissionReport report:companyCommissionReportList){
				//logger.info("report.getAgentCommByRate()------------"+report.getAgentCommByRate());
				totComm=totComm.add(report.getAgentCommByRate());
				totSpentAmount=totSpentAmount.add(report.getFinalPrice());
				totTicketPrice=totTicketPrice.add(report.getTicketPrice());
				totMyMarkup=totMyMarkup.add(report.getMarkup());
				totWholeSalerMarkup=totWholeSalerMarkup.add(report.getDistributorMarkup());
				totAgencyMarkup=totAgencyMarkup.add(report.getChildMarkup());
				totMyCommission=totMyCommission.add(report.getMyCommission());
				totSharedCommission=totSharedCommission.add(report.getSharedCommission());
				agentTotalReportCommObj.setTDS(report.getTDS());
				agentTotalReportCommObj.setTdsType(report.getTdsType());
			}
			agentTotalReportCommObj.setTotTicketPrice(totTicketPrice);
			agentTotalReportCommObj.setTotMyMarkup(totMyMarkup);
			agentTotalReportCommObj.setTotWholeSalerMarkup(totWholeSalerMarkup);
			agentTotalReportCommObj.setTotAgencyMarkup(totAgencyMarkup);
			agentTotalReportCommObj.setTotMyCommission(totMyCommission);
			agentTotalReportCommObj.setTotSharedCommission(totSharedCommission);
			agentTotalReportCommObj.setTotAgentSComm(totComm);
			agentTotalReportCommObj.setTotAmountSpent(totSpentAmount);
			agentTotalReportCommObj.setYesterDayDate(DateFilter.getPreviousDate(-29));
			agentTotalReportCommObj.setTodayDate(DateFilter.getPreviousDate(0));
			agentTotalReportCommObj.setStatus("30 Days Report");
		 }
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.FLIGHT_BOOKINGS_COMMISSION_REPORT, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		 
		return SUCCESS;
	} 

	public String getFilterFlightAgentCommissionReport(){
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		InvoiceFilter filterObj=new InvoiceFilter();
		filterObj.setFromDate(reportData.getYesterDayDate());
		filterObj.setToDate(reportData.getTodayDate());
		companyCommissionReportList = flightCommissionDao.getFlightCommissionReport(userSessionObj, companySessionObj,filterObj);
		agentTotalReportCommObj=new FlightCommissionReport();
		BigDecimal totComm =new BigDecimal("0.00") ;
		BigDecimal totSpentAmount =new BigDecimal("0.00") ;
		BigDecimal totTicketPrice =new BigDecimal("0.00") ;
		BigDecimal totMyMarkup =new BigDecimal("0.00") ;
		BigDecimal totWholeSalerMarkup =new BigDecimal("0.00") ;
		BigDecimal totAgencyMarkup =new BigDecimal("0.00") ;
		BigDecimal totMyCommission =new BigDecimal("0.00") ;
		BigDecimal totSharedCommission =new BigDecimal("0.00") ;
		 if(companyCommissionReportList!=null && companyCommissionReportList.size()>0){
			 statusCode = ActionStatusEnum.SUCCESS.getCode();
				actionId=BrowsingOptionActionEnum.FLIGHT_AGENT_COMMISSION_REPORT.getId();
				detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();
			for(FlightCommissionReport report:companyCommissionReportList){
				//logger.info("report.getAgentCommByRate()------------"+report.getAgentCommByRate());
				totComm=totComm.add(report.getAgentCommByRate());
				totSpentAmount=totSpentAmount.add(report.getFinalPrice());
				totTicketPrice=totTicketPrice.add(report.getTicketPrice());
				totMyMarkup=totMyMarkup.add(report.getMarkup());
				totWholeSalerMarkup=totWholeSalerMarkup.add(report.getDistributorMarkup());
				totAgencyMarkup=totAgencyMarkup.add(report.getChildMarkup());
				totMyCommission=totMyCommission.add(report.getMyCommission());
				totSharedCommission=totSharedCommission.add(report.getSharedCommission());
				agentTotalReportCommObj.setTDS(report.getTDS());
				agentTotalReportCommObj.setTdsType(report.getTdsType());
			}
			agentTotalReportCommObj.setTotTicketPrice(totTicketPrice);
			agentTotalReportCommObj.setTotMyMarkup(totMyMarkup);
			agentTotalReportCommObj.setTotWholeSalerMarkup(totWholeSalerMarkup);
			agentTotalReportCommObj.setTotAgencyMarkup(totAgencyMarkup);
			agentTotalReportCommObj.setTotMyCommission(totMyCommission);
			agentTotalReportCommObj.setTotSharedCommission(totSharedCommission);
			agentTotalReportCommObj.setTotAgentSComm(totComm);
			agentTotalReportCommObj.setTotAmountSpent(totSpentAmount);
			agentTotalReportCommObj.setYesterDayDate(reportData.getYesterDayDate());
			agentTotalReportCommObj.setTodayDate(reportData.getTodayDate());
			 
		}
		 User user = (User)sessionMap.get("User");
			HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.FLIGHT_BOOKINGS_COMMISSION_REPORT.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
		 
		return SUCCESS;
	} 



	// action method for Hotel agent commission report
	public String getHotelCommissionReport(){
		BigDecimal totComm =new BigDecimal("0.00") ;
		BigDecimal totSpentAmount =new BigDecimal("0.00") ;
		BigDecimal totTicketPrice =new BigDecimal("0.00") ;
		BigDecimal totMyMarkup =new BigDecimal("0.00") ;
		BigDecimal totWholeSalerMarkup =new BigDecimal("0.00") ;
		BigDecimal totAgencyMarkup =new BigDecimal("0.00") ;
		BigDecimal totMyCommission =new BigDecimal("0.00") ;
		BigDecimal totSharedCommission =new BigDecimal("0.00") ;
		hotelAgentagentTotalReportCommObj=new HotelCommissionReport();
		InvoiceFilter filterObj=new InvoiceFilter();
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");
		hotelCommissionReportList = hotelCommissionDao.getHotelCommissionReport(userSessionObj, companySessionObj,filterObj);
		logger.info("....hotelCommissionReportList....."+hotelCommissionReportList.size());

		if(hotelCommissionReportList!=null && hotelCommissionReportList.size()>0){
			for(HotelCommissionReport report:hotelCommissionReportList){
				totComm=totComm.add(report.getAgentCommByRate());
				totSpentAmount=totSpentAmount.add(report.getFinalPrice());
				totTicketPrice=totTicketPrice.add(report.getTicketPrice());
				totMyMarkup=totMyMarkup.add(report.getMarkup());
				totWholeSalerMarkup=totWholeSalerMarkup.add(report.getDistributorMarkup());
				totAgencyMarkup=totAgencyMarkup.add(report.getChildMarkup());
				totMyCommission=totMyCommission.add(report.getMyCommission());
				totSharedCommission=totSharedCommission.add(report.getSharedCommission());
				hotelAgentagentTotalReportCommObj.setTDS(report.getTDS());
				hotelAgentagentTotalReportCommObj.setTdsType(report.getTdsType());
			}
		 
			hotelAgentagentTotalReportCommObj.setTotTicketPrice(totTicketPrice);
			hotelAgentagentTotalReportCommObj.setTotMyMarkup(totMyMarkup);
			hotelAgentagentTotalReportCommObj.setTotWholeSalerMarkup(totWholeSalerMarkup);
			hotelAgentagentTotalReportCommObj.setTotAgencyMarkup(totAgencyMarkup);
			hotelAgentagentTotalReportCommObj.setTotMyCommission(totMyCommission);
			hotelAgentagentTotalReportCommObj.setTotSharedCommission(totSharedCommission);
			hotelAgentagentTotalReportCommObj.setTotAgentSComm(totComm);
			hotelAgentagentTotalReportCommObj.setTotAmountSpent(totSpentAmount);
			hotelAgentagentTotalReportCommObj.setYesterDayDate(DateFilter.getPreviousDate(-29));
			hotelAgentagentTotalReportCommObj.setTodayDate(DateFilter.getPreviousDate(0));
			hotelAgentagentTotalReportCommObj.setStatus("30 Days Report");
			
			 
			 }
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.HOTEL_BOOKINGS_COMMISSION_REPORT, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		 return SUCCESS;
	} 



	// action method for Hotel agent commission report
		public String getFilterHotelCommissionReport(){
			InvoiceFilter filterObj=new InvoiceFilter();
			filterObj.setFromDate(reportData.getYesterDayDate());
			filterObj.setToDate(reportData.getTodayDate());
			BigDecimal totComm =new BigDecimal("0.00") ;
			BigDecimal totSpentAmount =new BigDecimal("0.00") ;
			BigDecimal totTicketPrice =new BigDecimal("0.00") ;
			BigDecimal totMyMarkup =new BigDecimal("0.00") ;
			BigDecimal totWholeSalerMarkup =new BigDecimal("0.00") ;
			BigDecimal totAgencyMarkup =new BigDecimal("0.00") ;
			BigDecimal totMyCommission =new BigDecimal("0.00") ;
			BigDecimal totSharedCommission =new BigDecimal("0.00") ;
			hotelAgentagentTotalReportCommObj=new HotelCommissionReport();
			User userSessionObj=(User)sessionMap.get("User");
			Company companySessionObj=(Company)sessionMap.get("Company");
			hotelCommissionReportList = hotelCommissionDao.getHotelCommissionReport(userSessionObj, companySessionObj,filterObj);
			logger.info("....hotelCommissionReportList....."+hotelCommissionReportList.size());

			if(hotelCommissionReportList!=null && hotelCommissionReportList.size()>0){
				statusCode = ActionStatusEnum.SUCCESS.getCode();
				actionId=BrowsingOptionActionEnum.HOTEL_COMMISION_REPORT.getId();
				detailType=BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId();		
				for(HotelCommissionReport report:hotelCommissionReportList){
					totComm=totComm.add(report.getAgentCommByRate());
					totSpentAmount=totSpentAmount.add(report.getFinalPrice());
					totTicketPrice=totTicketPrice.add(report.getTicketPrice());
					totMyMarkup=totMyMarkup.add(report.getMarkup());
					totWholeSalerMarkup=totWholeSalerMarkup.add(report.getDistributorMarkup());
					totAgencyMarkup=totAgencyMarkup.add(report.getChildMarkup());
					totMyCommission=totMyCommission.add(report.getMyCommission());
					totSharedCommission=totSharedCommission.add(report.getSharedCommission());
					hotelAgentagentTotalReportCommObj.setTDS(report.getTDS());
					hotelAgentagentTotalReportCommObj.setTdsType(report.getTdsType());
				}

				hotelAgentagentTotalReportCommObj.setTotTicketPrice(totTicketPrice);
				hotelAgentagentTotalReportCommObj.setTotMyMarkup(totMyMarkup);
				hotelAgentagentTotalReportCommObj.setTotWholeSalerMarkup(totWholeSalerMarkup);
				hotelAgentagentTotalReportCommObj.setTotAgencyMarkup(totAgencyMarkup);
				hotelAgentagentTotalReportCommObj.setTotMyCommission(totMyCommission);
				hotelAgentagentTotalReportCommObj.setTotSharedCommission(totSharedCommission);
				hotelAgentagentTotalReportCommObj.setTotAgentSComm(totComm);
				hotelAgentagentTotalReportCommObj.setTotAmountSpent(totSpentAmount);
				hotelAgentagentTotalReportCommObj.setYesterDayDate(reportData.getYesterDayDate());
				hotelAgentagentTotalReportCommObj.setTodayDate(reportData.getTodayDate());
			}
			User user = (User)sessionMap.get("User");
			HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.HOTEL_BOOKINGS_COMMISSION_REPORT.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
			 return SUCCESS;
		} 





	@Override
	public ReportData getModel() {
		// TODO Auto-generated method stub
		return reportData;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;

	}



	public List<FlightCommissionReport> getCompanyCommissionReportList() {
		return companyCommissionReportList;
	}



	public void setCompanyCommissionReportList(
			List<FlightCommissionReport> companyCommissionReportList) {
		this.companyCommissionReportList = companyCommissionReportList;
	}



	public FlightCommissionReport getAgentTotalReportCommObj() {
		return agentTotalReportCommObj;
	}



	public void setAgentTotalReportCommObj(
			FlightCommissionReport agentTotalReportCommObj) {
		this.agentTotalReportCommObj = agentTotalReportCommObj;
	}



	public List<HotelCommissionReport> getHotelCommissionReportList() {
		return hotelCommissionReportList;
	}



	public void setHotelCommissionReportList(
			List<HotelCommissionReport> hotelCommissionReportList) {
		this.hotelCommissionReportList = hotelCommissionReportList;
	}



	public HotelCommissionReport getHotelAgentagentTotalReportCommObj() {
		return hotelAgentagentTotalReportCommObj;
	}



	public void setHotelAgentagentTotalReportCommObj(
			HotelCommissionReport hotelAgentagentTotalReportCommObj) {
		this.hotelAgentagentTotalReportCommObj = hotelAgentagentTotalReportCommObj;
	}
}
