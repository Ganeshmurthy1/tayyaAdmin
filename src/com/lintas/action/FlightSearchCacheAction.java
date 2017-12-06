/**
 * 
 */
package com.lintas.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.FlightSearchCacheDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.cache.flight.model.FlightSearchCacheDestination;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manish Samrat
 * @createdAt 05/04/2017
 */
public class FlightSearchCacheAction extends ActionSupport implements ModelDriven<FlightSearchCacheDestination>, SessionAware{

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FlightSearchCacheAction.class);
	private static final long serialVersionUID = 1L;

	private SessionMap<String, Object> sessionMap;
	int companyUserId;
	RmConfigModel rmConfigModel = new RmConfigModel();
	FlightSearchCacheDestination flightSearchCacheDestination=new FlightSearchCacheDestination();
	private List<RmConfigModel> rmConfigModelList = new ArrayList<>();
	CompanyDAO companyDAO = new CompanyDAO();

	RmConfigDao rmConfigDao = new RmConfigDao();
	List<Company> companyList = new ArrayList<Company>();
	List<String> fieldNames = new ArrayList<String>();
	RmConfigTripDetailsModel configTripDetailsModel=new RmConfigTripDetailsModel();

	FlightSearchCacheDao flightSearchCacheDao=new FlightSearchCacheDao();
	List<FlightSearchCacheDestination> flightSearchCasheList=new ArrayList<FlightSearchCacheDestination>();

	public String addFlightSearchCache() {
		if (flightSearchCacheDestination != null ) {

			try{
				flightSearchCacheDestination.setOneway(true);
				flightSearchCacheDao.insertflightSearchCacheDetails(flightSearchCacheDestination);
			}catch (Exception e) {
				System.out.println("Error");
			}


		}
		return SUCCESS;
	}



	public String fetchFlightSearchCache() {
		try {
			flightSearchCasheList=flightSearchCacheDao.getFlightSearchCacheList();
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public String fetchFlightSearchCacheUpdate() {
		flightSearchCacheDestination.setOneway(true);
		flightSearchCacheDao.getAndUpdateCacheDetails(flightSearchCacheDestination);

		return SUCCESS;
	}


	public String deleteFlightSearchCache() {
		flightSearchCacheDao.deleteCacheById(flightSearchCacheDestination.getId());
		return SUCCESS;
	}



	public String fetchRmConfigToUpdate() {
		List<Long> corporateIdList = rmConfigDao.getAllCompanyIdCorporateOnly();
		List<Integer> listOfIntId = new ArrayList<Integer>();
		for (Long longId : corporateIdList) {
			listOfIntId.add((int) (long) longId);
		}
		setCompanyList(rmConfigDao.getCompanyNameById(listOfIntId, true));
		return SUCCESS;
	}




	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}

	@Override
	public FlightSearchCacheDestination getModel() {
		return flightSearchCacheDestination;
	}

	public int getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(int companyUserId) {
		this.companyUserId = companyUserId;
	}



	public List<RmConfigModel> getRmConfigModelList() {
		return rmConfigModelList;
	}

	public void setRmConfigModelList(List<RmConfigModel> rmConfigModelList) {
		this.rmConfigModelList = rmConfigModelList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}


	public List<String> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public RmConfigTripDetailsModel getConfigTripDetailsModel() {
		return configTripDetailsModel;
	}

	public void setConfigTripDetailsModel(RmConfigTripDetailsModel configTripDetailsModel) {
		this.configTripDetailsModel = configTripDetailsModel;
	}

	public FlightSearchCacheDestination getFlightSearchCacheModel() {
		return flightSearchCacheDestination;
	}

	public void setFlightSearchCacheModel(FlightSearchCacheDestination flightSearchCacheDestination) {
		this.flightSearchCacheDestination = flightSearchCacheDestination;
	}



	public List<FlightSearchCacheDestination> getFlightSearchCasheList() {
		return flightSearchCasheList;
	}



	public void setFlightSearchCasheList(List<FlightSearchCacheDestination> flightSearchCasheList) {
		this.flightSearchCasheList = flightSearchCasheList;
	}



}
