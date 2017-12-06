package com.isl.admin.filter.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;

public class CompanyApprovalFilterDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyApprovalFilterDao.class);
	/*-------------------------get all company Approval companies ----------------------*/
	public CompanyFilterPage getApprovalCompanyList(CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
	 
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			List<Company> newApprovalComapnyList = new ArrayList<>();
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				conjunction.add(Restrictions.eq("parent_company_userid",companyFilter.getLoginCompany().getCompany_userid()));
				conjunction.add(Restrictions.eq("isStatus", true));
				if(companyFilter.getCompanyName() != null && !companyFilter.getCompanyName().equals(""))
				{
					conjunction.add(Restrictions.eq("Companyname", companyFilter.getCompanyName()));
					logger.info("##########CompanyName  added----"+companyFilter.getCompanyName());
				}
				if(companyFilter.getEmail() != null && !companyFilter.getEmail().equals(""))
				{
					conjunction.add(Restrictions.eq("Email", companyFilter.getEmail()));
					logger.info("##########Email  added----"+companyFilter.getEmail());
				}
				if(companyFilter.getPanNumber() != null && !companyFilter.getPanNumber().equals(""))
				{
					conjunction.add(Restrictions.eq("registerNumber", companyFilter.getPanNumber()));
					logger.info("##########getPanNumber  added----"+companyFilter.getPanNumber());
				}
				if(companyFilter.getPhone() != null && !companyFilter.getPhone().equals(""))
				{
					conjunction.add(Restrictions.eq("Phone", companyFilter.getPhone()));
					logger.info("##########getPhone  added----"+companyFilter.getPhone());
				}
				criteria.add(conjunction);

			}	
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(companyFilterPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
					companyFilterPage.setAvailableItems(availableItems);
					companyFilterPage.setAvailablePages(availablePages);
				} 
				int pageIndexDb = (companyFilterPage.getCurrentPageIndex() > 1)?companyFilterPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * companyFilterPage.getMaxItems();
				
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(Company.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("companyid"));
					List<Company> approvalComapnyList = criteria.list();
					logger.info("approvalComapnyList size------"+approvalComapnyList.size());	
					if(approvalComapnyList!=null && approvalComapnyList.size()>0){ 
					 for(Company company:approvalComapnyList){
						 if(company.isStatus())
							{
								if(company.getCompanyRole().isAgent()){
									company.setCompType("Agency");
									newApprovalComapnyList.add(company);
								}
								if(company.getCompanyRole().isDistributor()){
									company.setCompType(companyFilter.getCompanyType());
									newApprovalComapnyList.add(company);
								}
								if(company.getCompanyRole().isCorporate()){
									company.setCompType("Corporate");
									newApprovalComapnyList.add(company);
								}
							} 
					 }
					}
					if(newApprovalComapnyList!=null && newApprovalComapnyList.size()>0){
						companyFilterPage.setApprovalCompanyList(newApprovalComapnyList); 
					}
				 }
			 }	
			else{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setCompanyList(new ArrayList<Company>());
			}
		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyFilterPage;
	}
 
}


