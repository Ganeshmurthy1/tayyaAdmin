package com.lintas.action;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.CompanyEntity;
import com.opensymphony.xwork2.ActionSupport;

public class CompanyEntityCUDAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompanyEntity companyEntity = new CompanyEntity();
	private CompanyEntity companyEntityNew = new CompanyEntity();
	private Long companyEntityid = null;
	private int companyid;
	private Long companyentityid;
	CompanyDAO companyDAO = new CompanyDAO();
	/*CompanyEntityDao companyEntityDao = new CompanyEntityDao();

	public String createcompanyentityDetails() {
		Company company = null;
		company = companyDAO.getNewCompanyId(companyid);		
		companyEntity.setCompanyId(companyid);
		companyEntity.setCompanyname(company.getCompanyname());
		try {
			if (companyEntity.getCity() !=null && companyEntity.getCompanyEntityName() !=null) {
				companyDAO.saveCompanyEntity(companyEntity);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String updatecompanyentityDetails() {
		companyEntity = companyEntityDao.getcompanyEntityCompanyEntityId(companyentityid);
		if (companyEntityid != null && companyEntityid > 0) {
			companyEntityDao.updateCompanyEntityCompanyEntityId(companyEntityNew, companyEntityid);
		}
		return SUCCESS;
	}

	public String deletecompanyentityDetails() {
		companyEntityDao.deleteCompanyEntityCompanyEntityId(companyentityid);
		return SUCCESS;
	}*/

	public CompanyEntity getCompanyEntity() {
		return companyEntity;
	}

	public void setCompanyEntity(CompanyEntity companyEntity) {
		this.companyEntity = companyEntity;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public Long getCompanyentityid() {
		return companyentityid;
	}

	public void setCompanyentityid(Long companyentityid) {
		this.companyentityid = companyentityid;
	}

	public CompanyEntity getCompanyEntityNew() {
		return companyEntityNew;
	}

	public void setCompanyEntityNew(CompanyEntity companyEntityNew) {
		this.companyEntityNew = companyEntityNew;
	}

	public Long getCompanyEntityid() {
		return companyEntityid;
	}

	public void setCompanyEntityid(Long companyEntityid) {
		this.companyEntityid = companyEntityid;
	}

}
