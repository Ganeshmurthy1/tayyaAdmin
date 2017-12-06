package com.isl.admin.filter.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.config.HibernateUtil;

public class CompanyConfigFilterDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyConfigFilterDao.class);
	//looking for child companies list based on parent_company_user_id
		public List<Integer> getCompanyIdList(CompanyFilterPage companyFilterPage)
		{
			List<Integer> companyIds= new ArrayList<>();
			Session session = null;
			Company loginCompany=companyFilterPage.getCompanyFilter().getLoginCompany();
		 
			try{			
				session = HibernateUtil.getSessionFactory().openSession();
				Criteria criteria = session.createCriteria(Company.class);
				//Disjunction only for OR logical operator
				Disjunction disjunction= Restrictions.disjunction();
				if(!loginCompany.getCompanyRole().isDistributor() && !loginCompany.getCompanyRole().isAgent()) 
					criteria.add(Restrictions.eq("super_company_userid", loginCompany.getCompany_userid()));

				else if(loginCompany.getCompanyRole().isDistributor()){ 
					disjunction.add(Restrictions.eq("parent_company_userid", loginCompany.getCompany_userid()));
					disjunction.add(Restrictions.eq("company_userid", loginCompany.getCompany_userid()));
					criteria.add(disjunction);
				}
				else if(loginCompany.getCompanyRole().isAgent()){
					criteria.add(Restrictions.eq("company_userid", loginCompany.getCompany_userid()));
				}
				criteria.addOrder(Order.desc("companyid"));
				List<Company> companiesList=criteria.list();
				if(companiesList!=null && companiesList.size()>0){
					for(Company company:companiesList){
						  companyIds.add(company.getCompanyid());
					 }
				}
			}
			catch (HibernateException e) {
				logger.error("--------------HibernateException-----------------"+e.getMessage());
			}
			catch (Exception e) {
				logger.error("--------------Exception-----------------"+e.getMessage());
			}
			finally {
				if(session!=null && session.isOpen())
					session.close(); 
			}
			return companyIds;
		}

	 
	/*get CompanyConfig List details*/
	public CompanyFilterPage getCompanyConfigList(CompanyFilterPage companyFilterPage)
	{
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =getCompanyIdList(companyFilterPage);
	 
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CompanyConfig.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				conjunction.add(Restrictions.in("createdbyComapnyId", companyIds));
				if(companyFilter.getCompanyName() != null && !companyFilter.getCompanyName().equals(""))
				{
					conjunction.add(Restrictions.eq("companyName", companyFilter.getCompanyName()));
					logger.info("##########companyName  added----"+companyFilter.getCompanyName());
				}
				if(companyFilter.getConfigName() != null && !companyFilter.getConfigName().equals(""))
				{
					conjunction.add(Restrictions.eq("configname", companyFilter.getConfigName()));
					logger.info("##########getConfigName  added----"+companyFilter.getConfigName());
				}
				if(companyFilter.getCompanyType() != null && !companyFilter.getCompanyType().equals("ALL"))
				{
					conjunction.add(Restrictions.eq("companyType", companyFilter.getCompanyType()));
					logger.info("########## Companyonfig Company  Type  added----"+companyFilter.getCompanyType());
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
				logger.info("setFirstResult-------"+itemIndex);
				List<CompanyConfig> filterComapnyConfigList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(CompanyConfig.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("config_id"));
					filterComapnyConfigList = criteria.list();
					logger.info("filterComapnyConfigList size------"+filterComapnyConfigList.size());					
				}
				companyFilterPage.setCompanyConfigList(filterComapnyConfigList);
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setCompanyConfigList(new ArrayList<CompanyConfig>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}	
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyFilterPage;

	}
 
}
