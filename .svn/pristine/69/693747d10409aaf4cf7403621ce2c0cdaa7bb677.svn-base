package com.admin.common.commonDsrReportConfg.Action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.commonDsrReportConfg.CommonDsrReportConfg;
import com.admin.common.commonDsrReportConfg.dao.CommonDsrReportConfgDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommonDsrReportConfgAction extends ActionSupport implements ModelDriven<CommonDsrReportConfg>,SessionAware{
	/**
	 * @author Created by Basha at 18-07-2017
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonDsrReportConfgAction.class);
	SessionMap<String, Object> sessionmap;
	CommonDsrReportConfg commonDsrReportConfg=new CommonDsrReportConfg();
	CommonDsrReportConfgDao commonDsrReportConfgDao=new CommonDsrReportConfgDao();
	Company Comreg = new Company();
	CompanyDAO companyDao=new CompanyDAO();
	private int companyid=0;

	//this method is used for  redirecting the edit/add button into jsp  and fetch the details of CommonDsrReportConfg to that person using companyid
	public String goCommonDsrReportConfg(){
		if(companyid>0)
		{
			Company  CompanyProfile=companyDao.getCompanyProfile(companyid);
			Company parentcompany=companyDao.getParentCompany(CompanyProfile);
			Company supercompanyprofile=companyDao.getCompanyProfile(1);

			CommonDsrReportConfg commonDsrReportConfgForSelf=commonDsrReportConfgDao.getCommonDsrReportConfgByCompanyId(companyid);
			if(commonDsrReportConfgForSelf!=null){
				commonDsrReportConfg = commonDsrReportConfgForSelf;
				return SUCCESS;

			}else{
				CommonDsrReportConfg commonDsrReportConfgForparent=commonDsrReportConfgDao.getCommonDsrReportConfgByCompanyId(parentcompany.getCompanyid());
				if(commonDsrReportConfgForparent!=null && commonDsrReportConfgForparent.getId()!=null){
					commonDsrReportConfg = commonDsrReportConfgForparent;
					return SUCCESS;
				}else{
					CommonDsrReportConfg commonDsrReportConfgForsuperuser=commonDsrReportConfgDao.getCommonDsrReportConfgByCompanyId(supercompanyprofile.getCompanyid());
					if(commonDsrReportConfgForsuperuser!=null && commonDsrReportConfgForsuperuser.getId()!=null){
						commonDsrReportConfg = commonDsrReportConfgForsuperuser;
						return SUCCESS;
					}
				}
			}
		}
		return SUCCESS;
	}

	//this method is used for  insert the commondsrreport config / update the commondsrreport config

	public String insertCommonDsrReportConfg() throws Exception{
		User sessionUser=(User)sessionmap.get("User");
		Company  sessionCom=(Company)sessionmap.get("Company");
		try{
			companyid = commonDsrReportConfg.getCompanyId();
			if(commonDsrReportConfg.getId()==null){
				commonDsrReportConfg.setCreatedbyCompanyUserId(sessionUser.getId());
				commonDsrReportConfg.setModifiedbyCompanyUserId(sessionUser.getId());
				CommonDsrReportConfg commonDsrReportConfgnew=commonDsrReportConfgDao.insertCommonDsrReportConfg(commonDsrReportConfg);
				

				addActionMessage("Sucessfully Created CommonDSR Config");
				return SUCCESS;
			}else{
				commonDsrReportConfg.setModifiedbyCompanyUserId(sessionUser.getId());
				commonDsrReportConfg.setCreatedbyCompanyUserId(sessionUser.getId());
				CommonDsrReportConfg commonDsrReportConfgupdate=commonDsrReportConfgDao.updateCommonDsrReportConfg(commonDsrReportConfg);
				
				addActionMessage("Sucessfully Updated CommonDSR Config");
				return SUCCESS;
			}
		}catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
			addActionError("Something went wrong please try again.........");
			return ERROR;
		}


	}




	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}

	@Override
	public CommonDsrReportConfg getModel() {
		// TODO Auto-generated method stub
		return commonDsrReportConfg;
	}

	public CommonDsrReportConfg getCommonDsrReportConfg() {
		return commonDsrReportConfg;
	}

	public void setCommonDsrReportConfg(CommonDsrReportConfg commonDsrReportConfg) {
		this.commonDsrReportConfg = commonDsrReportConfg;
	}

	public Company getComreg() {
		return Comreg;
	}

	public void setComreg(Company comreg) {
		Comreg = comreg;
	}




	public int getCompanyid() {
		return companyid;
	}




	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

}
