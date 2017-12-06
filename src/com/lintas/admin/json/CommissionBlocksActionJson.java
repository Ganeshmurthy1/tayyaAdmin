package com.lintas.admin.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.commission.dao.AirlineCommissionBlockDaoImp;
import com.isl.admin.commission.model.AirlineCommissionCompanyBlock;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class CommissionBlocksActionJson  extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommissionBlocksActionJson.class);
	
	SessionMap<String,Object> sessionMap;
	private int  companyId;
	private   List<AirlineCommissionCompanyBlock>  companyBlockList = new ArrayList<>();
	
	public String getCompanyCommissionBlocks(){
		Company companySessionObj=(Company) sessionMap.get("Company");
		AirlineCommissionBlockDaoImp blockDao =new AirlineCommissionBlockDaoImp();
		logger.info("companyId------"+companyId);
		boolean isMasterCompany= new CompanyDAO().checkCompanyRole(companyId);
		List<AirlineCommissionCompanyBlock> airlineCommissionCompanyBlockList = null;
		List<AirlineCommissionCompanyBlock> activeCompanyBlockList=new ArrayList<>();
		if(isMasterCompany){
			airlineCommissionCompanyBlockList = blockDao.getChildrenCompanyCommissionBlocks(true, companySessionObj.getCompanyid());
			
		}
		else{
			airlineCommissionCompanyBlockList  =blockDao.getChildrenCompanyCommissionBlocks(false, companySessionObj.getCompanyid());
		}
		if(airlineCommissionCompanyBlockList != null && airlineCommissionCompanyBlockList.size()>0){
			 for(AirlineCommissionCompanyBlock commissionCompanyBlock:airlineCommissionCompanyBlockList){
				 if(commissionCompanyBlock.isActive()){
					 logger.info("Active---------"+commissionCompanyBlock.isActive());
					 activeCompanyBlockList.add(commissionCompanyBlock);
				 }
			 }
			 companyBlockList=activeCompanyBlockList;
		 } 
		 return SUCCESS;
	}
	public List<AirlineCommissionCompanyBlock> getCompanyBlockList() {
		return companyBlockList;
	}
	public void setCompanyBlockList(List<AirlineCommissionCompanyBlock> companyBlockList) {
		this.companyBlockList = companyBlockList;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	 
	 
	
	public int  getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	 

}
