package com.lintas.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.ChildOrParentCompanyDao;
import com.lintas.admin.DAO.MailStatusDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyRole;
import com.lintas.admin.model.MailStatus;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ChildOrParentCompanyAction extends ActionSupport implements ModelDriven<Company>,SessionAware{
	/**@author info raham
	 * created date:17-09-2015
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ChildOrParentCompanyAction.class);
	Company parentorChildCompanyReg=new Company();
	SessionMap<String, Object> sessionMap ;
	ChildOrParentCompanyDao CPCompanyDao =new ChildOrParentCompanyDao();
	private String companyType;
	CompanyRole companyRole =new CompanyRole();
	MailStatus ms=new MailStatus();
	MailStatusDao mailDao=new MailStatusDao();
	 
	
	public String child_ParentCompanyReg(){
		 parentorChildCompanyReg.setCreateddate(new Date());
  if(!CPCompanyDao.isExisted(parentorChildCompanyReg)){
			boolean  isUpdated=CPCompanyDao.insertChildOrParentCompanyDetails(parentorChildCompanyReg);
		   if(isUpdated){
			  addActionMessage(getText("global.child_ParentCompanyRegsuccess"));
				//addActionMessage("Successsfully Registered.");
			}
			else{
				addActionError(getText("global.child_ParentCompanyRegOops!"));
				//addActionError("Oops! Try again.");
			}
		}
		else{
			addActionError(getText("global.child_ParentCompanyRegalready"));
			//addActionError("Already existed.");
		}

		return SUCCESS;
	}

 

	public String setComapanyApproval(){
		 return SUCCESS; 
	}
	 
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public  Company getModel() {
		// TODO Auto-generated method stub
		return parentorChildCompanyReg;
	}

 /**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}


 /**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}



}
