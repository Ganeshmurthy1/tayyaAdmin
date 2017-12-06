package com.admin.dashboard.analysis.json.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.dashboard.analysis.json.dao.AgencyAnalysisDao;
import com.admin.dashboard.analysis.json.dao.AirlineAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.AgencyAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.daoImpl.AirlineAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.AgencyAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.AgencyAnalysisVO;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class AgencyAnalysisAction extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 11 Aug 2017
	 */
	AirlineAnalysisDao airlineAnalysisDao=new  AirlineAnalysisDaoImpl();
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	AgencyAnalysisDaoImpl  agencyAnalysisDao = new AgencyAnalysisDaoImpl();
	CompanyDAO companyDAO=new CompanyDAO();
	AgencyAnalysisVO  companyData=new AgencyAnalysisVO();
	public String  corporateBookingCount(){
		Company sessionCompany = (Company) sessionMap.get("Company");
		List<Integer> companyIds = new ArrayList<>();
		List<AgencyAnalysisDataVO>  corporateAnalysisList=new ArrayList<>();

		if (sessionCompany == null) {
			ErrorMsg error = new ErrorMsg();
			error.setMessage("Session is expired.");
			companyData.setError(error);
			return SUCCESS;
		}
		List<Company> corporates=companyDAO.getCorpotatesUnderSuperuser();
		if(corporates !=null && corporates.size() >0){
			for (Company company : corporates) {
				companyIds.add(company.getCompanyid());
			}
		}
		corporateAnalysisList=agencyAnalysisDao.calculateBookingAmount(companyIds);
		// companyUserId=distributorAnalysisDao.getAllCompanyUnderDistributor(sessionCompany);
		if(corporateAnalysisList!=null && corporateAnalysisList.size()>0){
			companyData.setTotalAmount(agencyAnalysisDao.calTotalAmount(corporateAnalysisList));
			companyData.setCorporateList(corporateAnalysisList);
		}
		else{
			ErrorMsg error = new ErrorMsg();
			error.setMessage("No data.");
			companyData.setError(error);
		}
		return SUCCESS;
	}
	public String  distributorBookingCount(){
		Company sessionCompany = (Company) sessionMap.get("Company");
		List<Integer> companyIds = new ArrayList<>();
		List<AgencyAnalysisDataVO>  disAnalysisList=new ArrayList<>();

		if (sessionCompany == null) {
			ErrorMsg error = new ErrorMsg();
			error.setMessage("Session is expired.");
			companyData.setError(error);
			return SUCCESS;
		}
		List<Company> corporates=companyDAO.getDistributorUnderSuperuser();
		if(corporates !=null && corporates.size() >0){
			for (Company company : corporates) {
				companyIds.add(company.getCompanyid());
			}
		}
		disAnalysisList=agencyAnalysisDao.calculateBookingAmount(companyIds);
		if(disAnalysisList!=null && disAnalysisList.size()>0){
			companyData.setDistributorList(disAnalysisList);
			companyData.setTotalAmount(agencyAnalysisDao.calTotalAmount(disAnalysisList));
		}
		else{
			ErrorMsg error = new ErrorMsg();
			error.setMessage("No data.");
			companyData.setError(error);
		}
			
		return SUCCESS;
	}
	public String  agencyBookingCount(){
		Company sessionCompany = (Company) sessionMap.get("Company");
		List<Integer> companyIds = new ArrayList<>();
		List<AgencyAnalysisDataVO>  agencyAnalysisList=new ArrayList<>();

		if (sessionCompany == null) {
			ErrorMsg error = new ErrorMsg();
			error.setMessage("Session is expired.");
			companyData.setError(error);
			return SUCCESS;
		}
		List<Company> corporates=companyDAO.getAgencyUnderSuperuser();
		if(corporates !=null && corporates.size() >0){
			for (Company company : corporates) {
				companyIds.add(company.getCompanyid());
			}
		}
		agencyAnalysisList=agencyAnalysisDao.calculateBookingAmount(companyIds);
		if(agencyAnalysisList!=null && agencyAnalysisList.size()>0){
			companyData.setAgencyList(agencyAnalysisList);
			companyData.setTotalAmount(agencyAnalysisDao.calTotalAmount(agencyAnalysisList));
		}
		else{
			ErrorMsg error = new ErrorMsg();
			error.setMessage("No data.");
			companyData.setError(error);
		}
		return SUCCESS;
	}
	
 
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	public AgencyAnalysisVO getCompanyData() {
		return companyData;
	}

	public void setCompanyData(AgencyAnalysisVO companyData) {
		this.companyData = companyData;
	}



}
