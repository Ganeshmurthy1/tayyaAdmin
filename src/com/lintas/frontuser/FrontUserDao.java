package com.lintas.frontuser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;

public class FrontUserDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FrontUserDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	
	/*public List<FrontUserDetail> loadAllFrontUsers(){
		List<FrontUserDetail> frontUserList=null; 
		 String sql= "from FrontUserDetail fu order by fu.id desc";
		 try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			frontUserList = query.list();
		 }
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			session.close(); 
		}
		return frontUserList;
		
	}*/
	
	 
 public  CompanyFilterPage loadAllFrontUsers(CompanyFilterPage companyFilterPage)
	{
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FrontUserDetail.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				
				List<String> companyIdList  = new ArrayList<String>();
				List<Integer> companyIdListInt=new ArrayList<Integer>();
				companyIdList = getCompanyIdList(companyFilter.getLoginCompany(), companyFilter.getReportType(), companyFilter.getCompanyName());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					companyFilterPage.setAvailableItems(0);					
//					companyFilterPage.setItems(new ArrayList<ReportData>());
					return companyFilterPage;
				}
				else{
					for(String id:companyIdList){
						companyIdListInt.add(Integer.valueOf(id));
					}
				}
				conjunction.add(Restrictions.in("companyId",companyIdListInt));
				
				
			 if(companyFilter.getFirstName() != null && !companyFilter.getFirstName().equals(""))
				{
					conjunction.add(Restrictions.eq("firstName", companyFilter.getFirstName()));
					logger.info("##########getFirstName  added----"+companyFilter.getFirstName());
				}
				if(companyFilter.getLastName() != null && !companyFilter.getLastName().equals(""))
				{
					conjunction.add(Restrictions.eq("lastName", companyFilter.getLastName()));
					logger.info("##########getLastName  added----"+companyFilter.getLastName());
				}
				if(companyFilter.getEmail() != null && !companyFilter.getEmail().equals(""))
				{
					conjunction.add(Restrictions.eq("email", companyFilter.getEmail()));
					logger.info("##########getEmail  added----"+companyFilter.getEmail());
				}
				if(companyFilter.getMobile() != null && !companyFilter.getMobile().equals(""))
				{
					conjunction.add(Restrictions.eq("mobile", companyFilter.getMobile()));
					logger.info("##########getEmail  added----"+companyFilter.getMobile());
				}
				 
				if(companyFilter.getDateFilterCreated() != null && companyFilter.getDateFilterCreated().getDtEnd() != null && companyFilter.getDateFilterCreated().getDtStart() != null )
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						conjunction.add(Restrictions.ge("createdAt", date));
						logger.info("##########date min added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunction.add(Restrictions.lt("createdAt", date));
						logger.info("##########date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				} 
			 criteria.add(conjunction);
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
				List<FrontUserDetail> filterFrontUserDetailList =  new ArrayList<FrontUserDetail>();
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(FrontUserDetail.class);
					 criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					filterFrontUserDetailList = criteria.list();
					logger.info("filterFrontUserDetailList size------"+filterFrontUserDetailList.size());
					System.out.println("filterFrontUserDetailList size------"+filterFrontUserDetailList.size());	
				}
				companyFilterPage.setFrontUserDetailList(filterFrontUserDetailList);
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setFrontUserDetailList(new ArrayList<FrontUserDetail>());
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
	
	 
	
	public FrontUserDetail getFrontUserDetail(Long id) {

		// TODO Auto-generated method stub
		 Session session= null;
		 FrontUserDetail reportData = null; 
		try{ 
			 session = HibernateUtil.getSessionFactory().openSession();
			   Criteria criteria=session.createCriteria(FrontUserDetail.class);
			   criteria.add(Restrictions.eq("id", id));
			   reportData=(FrontUserDetail) criteria.uniqueResult();  
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return reportData;
	
	}	
	public  List<String> getCompanyIdList(Company company, int reportType, String companyPreferable)
	{
		List<String> companyIdList = new ArrayList<String>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction reportDisjunction = Restrictions.disjunction();
			// To get total row count.
			List<Company> list = null;
			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}

				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
			case FlightReportFilter.REPORTS_ALL:
				if(!company.getCompanyRole().isAgent() && !company.getCompanyRole().isDistributor()&& !company.getCompanyRole().isCorporate()){
					reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));	
				}
				else{
					reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				}
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;	

			case FlightReportFilter.REPORTS_MY_AFFILIATES:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;

			case FlightReportFilter.REPORTS_MY_AGENCIES:
				logger.info("reportType---------"+reportType);


				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isAgent",true));
				break;
			case FlightReportFilter.REPORTS_MY_DISTRIBUTORS:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;
			case FlightReportFilter.REPORTS_MY_CORPORATES:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				//reportDisjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;

			case FlightReportFilter.REPORTS_ALL_AFFILIATES:
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;

			case FlightReportFilter.REPORTS_ALL_AGENCIES:
				logger.info("reportType---------"+reportType);

				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isAgent",true));
				break;
			case FlightReportFilter.REPORTS_ALL_DISTRIBUTORS:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;


			case FlightReportFilter.ORDERS_ALL:
				reportConjunction.add(Restrictions.eq("companyid",company.getCompanyid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;		


			default:	
				companyIdList.add(String.valueOf(company.getCompanyid()));
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{

					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;
			}				
			criteria.add(reportConjunction);
			//List<Company> list = criteria.list();
			logger.error("--------------probable Company list -----------------"+list);
			if(list!=null && list.size()>0)
			{
				logger.error("--------------probable Company list size-----------------"+list.size());
				for (Company companyChild :list)
				{
					companyIdList.add(String.valueOf(companyChild.getCompanyid()));
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
		//logger.info("--------reportData_list size-------"+reportData_list.size());
		return companyIdList;
	}
	
	
 
 }
