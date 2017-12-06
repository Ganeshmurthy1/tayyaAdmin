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
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyRole;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
public class CompanyFilterDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyFilterDao.class);
	//looking for child companies list based on parent_company_user_id
	public List<Integer> getCompanyIdList(CompanyFilterPage companyFilterPage)
	{
		List<Integer> companyIds= new ArrayList<>();
		Session session = null;
		Company loginCompany=companyFilterPage.getCompanyFilter().getLoginCompany();
		if(loginCompany==null){
			loginCompany=new Company(); 
			if(loginCompany.getCompany_userid()==null){
				loginCompany.setCompany_userid("");
			}

			if(loginCompany.getCompanyRole()==null){
				CompanyRole companyRole=new CompanyRole();
				loginCompany.setCompanyRole(companyRole);
			} 

		}

		CompanyFilter companyFilter =companyFilterPage.getCompanyFilter();
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
			else if(loginCompany.getCompanyRole().isAgent()){
				criteria.add(Restrictions.eq("company_userid", loginCompany.getCompany_userid()));
			}
			else if(loginCompany.getCompanyRole().isCorporate()){
				criteria.add(Restrictions.eq("company_userid", loginCompany.getCompany_userid()));
			}

			if(companyFilter.getCompanyRoleType()!= null && !companyFilter.getCompanyRoleType().equals("") && !companyFilter.getCompanyRoleType().equalsIgnoreCase("ALL"))
			{
				if(companyFilter.getCompanyRoleType().equalsIgnoreCase("IBE")){
					criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isIBE",true));

				}
				else if(companyFilter.getCompanyRoleType().equalsIgnoreCase("API")){
					criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isAPI",true));

				}
				logger.info("##########getCompanyRoleType  added----"+companyFilter.getCompanyRoleType());
			}


			criteria.addOrder(Order.desc("companyid"));
			List<Company> companiesList=criteria.list();
			if(companiesList!=null && companiesList.size()>0){
				for(Company company:companiesList){
					//if(company.getCompanyid()!=loginCompany.getCompanyid()){
					companyIds.add(company.getCompanyid());
					//}


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


	public List<Integer> getDisCompanyIdList(CompanyFilterPage companyFilterPage)
	{
		List<Integer> companyIds= new ArrayList<>();
		Session session = null;
		Company loginCompany=companyFilterPage.getCompanyFilter().getLoginCompany();
		CompanyFilter companyFilter =companyFilterPage.getCompanyFilter();
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);

			if(!loginCompany.getCompanyRole().isDistributor() && !loginCompany.getCompanyRole().isAgent() && loginCompany.getCompanyRole().isCorporate()) {
				criteria.add(Restrictions.eq("super_company_userid", loginCompany.getCompany_userid()));
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
			}

			if(companyFilter.getCompanyRoleType()!= null && !companyFilter.getCompanyRoleType().equals("") && !companyFilter.getCompanyRoleType().equalsIgnoreCase("ALL"))
			{
				if(companyFilter.getCompanyRoleType().equalsIgnoreCase("IBE")){
					criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isIBE",true));

				}
				else if(companyFilter.getCompanyRoleType().equalsIgnoreCase("API")){
					criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isAPI",true));

				}
				logger.info("##########getCompanyRoleType  added----"+companyFilter.getCompanyRoleType());
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


	public List<Integer> getCorCompanyIdList(CompanyFilterPage companyFilterPage)
	{
		List<Integer> companyIds= new ArrayList<>();
		Session session = null;
		Company loginCompany=companyFilterPage.getCompanyFilter().getLoginCompany();
		CompanyFilter companyFilter =companyFilterPage.getCompanyFilter();
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);


			criteria.add(Restrictions.eq("super_company_userid", loginCompany.getCompany_userid()));
			criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate",true));

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

	public  List<Integer> getAgencyCompanyIds(CompanyFilterPage companyFilterPage){
		// TODO Auto-generated method stub
		List<Integer> companyIds= new ArrayList<>();
		Session session = null;
		Company loginCompany=companyFilterPage.getCompanyFilter().getLoginCompany();

		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);

			if(!loginCompany.getCompanyRole().isDistributor() && !loginCompany.getCompanyRole().isAgent() && !loginCompany.getCompanyRole().isCorporate() ) {
				criteria.add(Restrictions.eq("parent_company_userid", loginCompany.getCompany_userid()));
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
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
	/*method for fetch companies list  by passing company user id*/
	public CompanyFilterPage getAllAgencyCompanyIds (CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =getAgencyCompanyIds(companyFilterPage);
		logger.info("------------ --getAgencyCompanyIds-----------------"+companyIds);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				conjunction.add(Restrictions.in("companyid", companyIds));
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
				criteria.addOrder(Order.desc("companyid"));
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
				List<Company> filterComapnyList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(Company.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("companyid"));
					filterComapnyList = criteria.list();
					logger.info("filterComapnyList size------"+filterComapnyList.size());					
				}

				companyFilterPage.setCompanyList(filterComapnyList);

			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setCompanyList(new ArrayList<Company>());
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

	/*method for fetch companies list  by passing company user id*/
	public CompanyFilterPage getAllDistributors(CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =getDisCompanyIdList(companyFilterPage);
		logger.info("------------ --DisCompanyIds-----------------"+companyIds);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				conjunction.add(Restrictions.in("companyid", companyIds));
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
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				criteria.addOrder(Order.desc("companyid"));
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
				List<Company> filterComapnyList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(Company.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
					criteria.addOrder(Order.desc("companyid"));
					filterComapnyList = criteria.list();
					logger.info("filterComapnyList size------"+filterComapnyList.size());					
				}

				companyFilterPage.setCompanyList(filterComapnyList);

			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setCompanyList(new ArrayList<Company>());
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


	public CompanyFilterPage getAllCorporate(CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =getCorCompanyIdList(companyFilterPage);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				conjunction.add(Restrictions.in("companyid", companyIds));
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
				criteria.addOrder(Order.desc("companyid"));
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
				List<Company> filterComapnyList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(Company.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("companyid"));
					filterComapnyList = criteria.list();
					logger.info("filterComapnyList size------"+filterComapnyList.size());					
				}

				companyFilterPage.setCompanyList(filterComapnyList);

			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setCompanyList(new ArrayList<Company>());
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

	/*method for fetch filter companies list  by passing company user id*/
	public CompanyFilterPage getCompanyListByCompanyUserId(CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =getCompanyIdList(companyFilterPage);
		logger.info("------------child--companyids-----------------"+companyIds);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				conjunction.add(Restrictions.in("companyid", companyIds));
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
				criteria.addOrder(Order.desc("companyid"));
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
				List<Company> filterComapnyList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(Company.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("companyid"));
					filterComapnyList = criteria.list();
					logger.info("filterComapnyList size------"+filterComapnyList.size());					
				}
				List<Company> newCompaniesList=new ArrayList<Company>();
				if(filterComapnyList!=null && filterComapnyList.size()>0){
					for(Company companyNew:filterComapnyList){
						if(companyNew.getCompanyRole().isAgent() || companyNew.getCompanyRole().isDistributor() || companyNew.getCompanyRole().isCorporate())
						{
							if(companyFilterPage.getCompanyFilter().getLoginCompany().getCompanyid()!=companyNew.getCompanyid()){
								String demoType="";
								User user= new UserDAO().getUserPasswordForLock(companyNew.getEmail());
								if(user!=null && user.getUserrole_id()!=null && user.getUserrole_id().isDemoUser()) 
									demoType=demoType.replace("", "(Demo Account)");
								if(companyNew.getCompanyRole().isAgent())
									companyNew.setCompType("Agency"+demoType);
								if(companyNew.getCompanyRole().isDistributor())
									companyNew.setCompType(companyFilterPage.getCompanyFilter().getCompanyType()+demoType);
								if(companyNew.getCompanyRole().isCorporate())
									companyNew.setCompType("Corporate"+demoType);
								newCompaniesList.add(companyNew);
							}
						}
					}
					companyFilterPage.setCompanyList(newCompaniesList);
					logger.info("(-----newCompaniesList-- size-----)"+newCompaniesList.size());
				}

				else
				{
					companyFilterPage.setAvailableItems(0);
					companyFilterPage.setCompanyList(new ArrayList<Company>());
				}
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setCompanyList(new ArrayList<Company>());
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

	/*-------------------------get all company Approval companies ----------------------*/
	public CompanyFilterPage getApprovalCompanyList(CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Company> approvalComapnyList=null;
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

				if(companyFilter.getApproveType().equalsIgnoreCase("yes")){
					conjunction.add(Restrictions.eq("isStatus", true));
				}
				if(companyFilter.getApproveType().equalsIgnoreCase("not")){
					conjunction.add(Restrictions.eq("isStatus", false));
				}
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
				if(companyFilter.getCompanyRoleType()!= null && !companyFilter.getCompanyRoleType().equals("") && !companyFilter.getCompanyRoleType().equalsIgnoreCase("ALL"))
				{
					if(companyFilter.getCompanyRoleType().equalsIgnoreCase("IBE")){
						criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isIBE",true));

					}
					else if(companyFilter.getCompanyRoleType().equalsIgnoreCase("API")){
						criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isAPI",true));

					}
					logger.info("##########getCompanyRoleType  added----"+companyFilter.getCompanyRoleType());
				}

				//criteria.createCriteria("companyRole").add(Restrictions.and(Restrictions.eq("isDistributor", false), Restrictions.eq("isAgent", false)));
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
					if(companyFilter.getCompanyRoleType()!= null && !companyFilter.getCompanyRoleType().equals("") && !companyFilter.getCompanyRoleType().equalsIgnoreCase("ALL"))
					{
						if(companyFilter.getCompanyRoleType().equalsIgnoreCase("IBE")){
							criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isIBE",true));

						}
						else if(companyFilter.getCompanyRoleType().equalsIgnoreCase("API")){
							criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isAPI",true));

						}
						logger.info("##########getCompanyRoleType  added----"+companyFilter.getCompanyRoleType());
					}

					//criteria.createCriteria("companyRole").add(Restrictions.and(Restrictions.eq("isDistributor", false), Restrictions.eq("isAgent", false)));
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("companyid"));
					approvalComapnyList = criteria.list();
					logger.info("approvalComapnyList size------"+approvalComapnyList.size());	

				}

				if(approvalComapnyList!=null && approvalComapnyList.size()>0){ 
					for(Company company:approvalComapnyList){
						if(company.getCompanyid()!=companyFilter.getLoginCompany().getCompanyid()){
							String demoType="";
							User user= new UserDAO().getUserPasswordForLock(company.getEmail());
							if(user!=null && user.getUserrole_id()!=null && user.getUserrole_id().isDemoUser()) 
								demoType=demoType.replace("", "(Demo Account)");
							if(company.getCompanyRole().isAgent()){
								company.setCompType("Agency"+demoType);
								newApprovalComapnyList.add(company);
							}
							if(company.getCompanyRole().isDistributor()){
								company.setCompType(companyFilter.getCompanyType()+demoType);
								newApprovalComapnyList.add(company);
							}
							if(company.getCompanyRole().isCorporate()){
								company.setCompType("Corporate"+demoType);
								newApprovalComapnyList.add(company);
							} 
						}
					}
				}
				companyFilterPage.setApprovalCompanyList(newApprovalComapnyList); 
				if(companyFilterPage.isPagination())
				{
					availableItems = companyFilterPage.getApprovalCompanyList().size();
					availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
					companyFilterPage.setAvailableItems(availableItems);
					companyFilterPage.setAvailablePages(availablePages);
				} 

			}	
			else{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setApprovalCompanyList(new ArrayList<Company>());
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
