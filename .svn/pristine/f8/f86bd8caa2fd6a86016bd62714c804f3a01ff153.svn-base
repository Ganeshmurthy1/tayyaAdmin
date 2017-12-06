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
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;

public class EmployeeFilterDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmployeeFilterDao.class);
	//looking for child companies list based on parent_company_user_id
	public List<Company> getCompanyList(CompanyFilterPage companyFilterPage)
	{
		List<Company> companyList= new ArrayList<>();
		Session session = null;
		Company loginCompany=companyFilterPage.getCompanyFilter().getLoginCompany();

		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			//Disjunction only for OR logical operator
			Disjunction disjunction= Restrictions.disjunction();
			if(!loginCompany.getCompanyRole().isDistributor() && !loginCompany.getCompanyRole().isAgent() && !loginCompany.getCompanyRole().isCorporate()) 
				criteria.add(Restrictions.eq("super_company_userid", loginCompany.getCompany_userid()));

			else if(loginCompany.getCompanyRole().isDistributor()){ 
				disjunction.add(Restrictions.eq("parent_company_userid", loginCompany.getCompany_userid()));
				disjunction.add(Restrictions.eq("company_userid", loginCompany.getCompany_userid()));
				criteria.add(disjunction);
			}
			else if(loginCompany.getCompanyRole().isAgent() || loginCompany.getCompanyRole().isCorporate()){

				criteria.add(Restrictions.eq("company_userid", loginCompany.getCompany_userid()));
			}

			criteria.addOrder(Order.desc("companyid"));
			List<Company> companiesList=criteria.list();

			if(companiesList!=null && companiesList.size()>0){
				for(Company company:companiesList){
					companyList.add(company);
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
		return companyList;
	}

	/*get all agents by passing company ids*/
	public CompanyFilterPage getAllAgentsByCompanyId(CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			Conjunction conjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			CompanyFilter companyFilter =null;
			List<Company> companiesList=null;
			CompanyFilterPage companyFilterPageNew=new CompanyFilterPage();
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				Company company=null;
				if(companyFilter.getCompanyId()>0) 
					company=new CompanyDAO().getCompanyProfile(companyFilter.getCompanyId());
				else 
					company=companyFilter.getLoginCompany();
				companyFilter.setLoginCompany(company);
				companyFilterPageNew.setCompanyFilter(companyFilter);
			 companiesList =getCompanyList(companyFilterPage);
				if(companiesList!=null && companiesList.size()>0){
					for(Company companyNew:companiesList){
						companyIds.add(company.getCompanyid());
					}
				}
				conjunction.add(Restrictions.in("Companyid", companyIds));

				if(companyFilter.getUserName() != null && !companyFilter.getUserName().equals(""))
				{
					conjunction.add(Restrictions.eq("Username", companyFilter.getUserName()));
					logger.info("##########getUserName  added----"+companyFilter.getUserName());
				}
				if(companyFilter.getEmail() != null && !companyFilter.getEmail().equals(""))
				{
					conjunction.add(Restrictions.eq("Email", companyFilter.getEmail()));
					logger.info("##########Email  added----"+companyFilter.getEmail());
				}

				if(companyFilter.getPhone() != null && !companyFilter.getPhone().equals(""))
				{
					conjunction.add(Restrictions.eq("Mobile", companyFilter.getPhone()));
					logger.info("##########getPhone  added----"+companyFilter.getPhone());
				}

				criteria.add(conjunction);
				if(companyFilter.getCompanyRoleType()!=null && !companyFilter.getCompanyRoleType().equalsIgnoreCase("ALL")){
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("ADMIN")){
						disjunction.add(Restrictions.eq("isAdmin", true));
					}
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("ORDER")){
						disjunction.add(Restrictions.eq("isOrder", true));
					}
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("CMS")){
						disjunction.add(Restrictions.eq("isCms", true));
					}
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("REPORT")){
						disjunction.add(Restrictions.eq("isReports", true));
					}
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("TECH_HEAD")){
						disjunction.add(Restrictions.eq("isTechHead", true));
					}
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("TECH_SUPPORT")){
						disjunction.add(Restrictions.eq("isTechSupport", true));
					}
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("TRAVEL_DESK")){
						disjunction.add(Restrictions.eq("isTravelDesk", true));
					}
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("CORPORATE_EMPLOYEE")){
						disjunction.add(Restrictions.eq("isCorporateemployee", true));
					}
				}

				else{
					disjunction.add(Restrictions.eq("isAdmin", true));
					disjunction.add(Restrictions.eq("isCms", true));
					disjunction.add(Restrictions.eq("isOrder", true));
					disjunction.add(Restrictions.eq("isReports", true));
					disjunction.add(Restrictions.eq("isTechHead", true));
					disjunction.add(Restrictions.eq("isTechSupport", true));
					disjunction.add(Restrictions.eq("isTravelDesk", true));
					disjunction.add(Restrictions.eq("isCorporateemployee", true));
					/*	disjunction.add(Restrictions.eq("isTechHead", true));*/
				}
				criteria.createCriteria("userrole_id").add(disjunction);
				criteria.add(Restrictions.ne("Email", "directuser@intellicommsolutions.com"));
				criteria.addOrder(Order.desc("id"));
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
				List<User> filterUsersList=null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(User.class);
					criteria.add(conjunction);
					if(companyFilter.getCompanyRoleType()!=null && !companyFilter.getCompanyRoleType().equalsIgnoreCase("ALL")){
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("ADMIN")){
							disjunction.add(Restrictions.eq("isAdmin", true));
						}
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("ORDER")){
							disjunction.add(Restrictions.eq("isOrder", true));
						}
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("CMS")){
							disjunction.add(Restrictions.eq("isCms", true));
						}
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("REPORT")){
							disjunction.add(Restrictions.eq("isReports", true));
						}
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("TECH_HEAD")){
							disjunction.add(Restrictions.eq("isTechHead", true));
						}
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("TECH_SUPPORT")){
							disjunction.add(Restrictions.eq("isTechSupport", true));
						}
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("TRAVEL_DESK")){
							disjunction.add(Restrictions.eq("isTravelDesk", true));
						}
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("CORPORATE_EMPLOYEE")){
							disjunction.add(Restrictions.eq("isCorporateemployee", true));
						}
					}

					else{
						disjunction.add(Restrictions.eq("isAdmin", true));
						disjunction.add(Restrictions.eq("isCms", true));
						disjunction.add(Restrictions.eq("isOrder", true));
						disjunction.add(Restrictions.eq("isReports", true));
						disjunction.add(Restrictions.eq("isTravelDesk", true));
						disjunction.add(Restrictions.eq("isCorporateemployee", true));
						/*disjunction.add(Restrictions.eq("isTechHead", true));*/
					}

					criteria.createCriteria("userrole_id").add(disjunction);
					criteria.add(Restrictions.ne("Email", "directuser@intellicommsolutions.com"));
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					filterUsersList = criteria.list();
					logger.info("filterUsersList size------"+filterUsersList.size());					
				}

				List<User> newUsersList=new ArrayList<>();
				if(filterUsersList!=null && filterUsersList.size()>0){
					if(companiesList!=null &&  companiesList.size()>0){
						for(User user:filterUsersList){
							for(Company company:companiesList){
								if(user.getCompanyid()==company.getCompanyid()){
									user.setCompanyName(company.getCompanyname());
									if(company.getCompanyRole().isAgent()){
										user.setCompanyType("Agency");
									}
									else if(company.getCompanyRole().isDistributor()){
										user.setCompanyType(companyFilter.getCompanyType());
									}
									else if(company.getCompanyRole().isCorporate()){
										user.setCompanyType("Corporate");
									}
									else{
										user.setCompanyType("Super Agency");
									}
									newUsersList.add(user);
								}
							}
						} 
					}
					companyFilterPage.setUserList(filterUsersList);
				}
				else
				{
					companyFilterPage.setAvailableItems(0);
					companyFilterPage.setUserList(new ArrayList<User>());
				}
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setUserList(new ArrayList<User>());
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

		/*try{
				if(companyIds!=null && companyIds.size()>0){
					session = HibernateUtil.getSessionFactory().openSession();
					Criteria criteria=session.createCriteria(User.class);
					criteria.add(Restrictions.in("Companyid", companyIds));
					usersList = criteria.list();
				}
			}
			catch(Exception e){
				logger.error("--------Exception-------"+ e.getMessage());
			}
			finally{
				if(session!=null && session.isOpen())
					session.close();
			}
			return usersList;*/
	}


	/*get all agents by passing company ids*/
	public  List<User> getAllTechnicalSupportList(Company company){
		Session session = null;
		List<User> filterUsersList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			Conjunction conjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			conjunction.add(Restrictions.eq("Companyid",1));
			disjunction.add(Restrictions.eq("isTechSupport", true));
			criteria.createCriteria("userrole_id").add(disjunction); 
			criteria.addOrder(Order.desc("id"));
			filterUsersList=criteria.list();


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
		return filterUsersList;	

	}

}
