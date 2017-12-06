package com.lintas.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.RmConfigModel;
import com.opensymphony.xwork2.ActionSupport;

public class GetRmDetails extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int companyUserId;
	RmConfigModel rmConfigModel = new RmConfigModel();
	CompanyDAO companyDAO = new CompanyDAO();
	RmConfigDao rmConfigDao = new RmConfigDao();
	List<Company> companyList = new ArrayList<Company>();
	List<String> fieldNames = new ArrayList<String>();
	private Map<String,Object> jsonobj = new HashMap<>();
	public String getCompanyDetailById() {
		
		try {			
			rmConfigModel = rmConfigDao.getConfigDetailsByCompanyId(companyUserId);		
			jsonobj.put("data", rmConfigModel);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public RmConfigModel getRmConfigModel() {
		return rmConfigModel;
	}

	
	public void setRmConfigModel(RmConfigModel rmConfigModel) {
		this.rmConfigModel = rmConfigModel;
	}
	

	public int getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(int companyUserId) {
		this.companyUserId = companyUserId;
	}
	/**
	 * @return the jsonobj
	 */
	public Map<String, Object> getJsonobj() {
		return jsonobj;
	}

	/**
	 * @param jsonobj the jsonobj to set
	 */
	public void setJsonobj(Map<String, Object> jsonobj) {
		this.jsonobj = jsonobj;
	}
}
