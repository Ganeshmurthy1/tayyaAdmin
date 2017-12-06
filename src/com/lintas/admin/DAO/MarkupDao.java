package com.lintas.admin.DAO;

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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.dao.CompanyConfigFilterDao;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.FlightMarkup;
import com.lintas.admin.model.HotelMarkup;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;
/**@author info name:raham
 * created Date:3-08-2015
 * 
 */
public class MarkupDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MarkupDao.class);
	/*Insert the Markup details into the database*/
	public Integer insertMarkupDetails(FlightMarkup cm) throws Exception
	{
		int result=0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			result=(Integer) session.save(cm);
			cm.setCompanyUserId("tayyarah");
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return result;
	}

	/*get CompanyMarkup List details*/
	public List<FlightMarkup> getMarkupList(Company sessionObj)
	{
		List<Company> companyIds=new CompanyDAO().getAllComapnyIdsByCompanyUserId(sessionObj.getCompany_userid());
		logger.info("companyIds---------"+companyIds.size());
		List<FlightMarkup> markupLi=null;
		List<Integer> newCompanyIds=new ArrayList<>();
		if(companyIds!=null && companyIds.size()>0){
			for(Company companyObj:companyIds){
				newCompanyIds.add(companyObj.getCompanyid());
			}
		}
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="from FlightMarkup fm where fm.createdbyCompanyId in (:companyids) order by fm.markupId desc";
			Query query = session.createQuery(sql);
			query.setParameterList("companyids",newCompanyIds);
			markupLi = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return markupLi;
	}

	/*get CompanyMarkup List details*/
	public  CompanyFilterPage getFilterFlightMarkupList(CompanyFilterPage companyFilterPage)
	{
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =new CompanyConfigFilterDao().getCompanyIdList(companyFilterPage);

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightMarkup.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				conjunction.add(Restrictions.in("createdbyCompanyId", companyIds));
				if(companyFilter.getCompanyName() != null && !companyFilter.getCompanyName().equals(""))
				{
					conjunction.add(Restrictions.eq("companyName", companyFilter.getCompanyName()));
					logger.info("##########companyName  added----"+companyFilter.getCompanyName());
				}
				if(companyFilter.getCompanyId()>0)
				{
					conjunction.add(Restrictions.eq("createdbyCompanyId", companyFilter.getCompanyId()));
					logger.info("##########companyId added----"+companyFilter.getCompanyId());
				}
				
				
				if(companyFilter.getCreatedByCompanyName() != null && !companyFilter.getCreatedByCompanyName().equals(""))
				{
					conjunction.add(Restrictions.eq("createdByCompanyName", companyFilter.getCreatedByCompanyName()));
					logger.info("##########companyName  added----"+companyFilter.getCreatedByCompanyName());
				}
				if(companyFilter.getConfigName() != null && !companyFilter.getConfigName().equals(""))
				{
					conjunction.add(Restrictions.eq("configname", companyFilter.getConfigName()));
					logger.info("##########getConfigName  added----"+companyFilter.getConfigName());
				}
				if(companyFilter.getDestinationType() != null && !companyFilter.getDestinationType().equals("ALL"))
				{
					conjunction.add(Restrictions.eq("destinationType", companyFilter.getDestinationType()));
					logger.info("########## destinationType  added----"+companyFilter.getDestinationType());
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
						conjunction.add(Restrictions.ge("createdDate", date));
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
						conjunction.add(Restrictions.lt("createdDate", date));
						logger.info("##########date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				criteria.add(conjunction);
				criteria.addOrder(Order.desc("markupId"));
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
				List<FlightMarkup> filterFlightMarkupList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(FlightMarkup.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("markupId"));
					filterFlightMarkupList = criteria.list();
					logger.info("filterFlightMarkupList size------"+filterFlightMarkupList.size());					
				}
				companyFilterPage.setFlightMarkupList(filterFlightMarkupList);
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setFlightMarkupList(new ArrayList<FlightMarkup>());
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


	/*get hotel markup list List details*/
	public  CompanyFilterPage getFilterHotelMarkupList(CompanyFilterPage companyFilterPage)
	{
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =new CompanyConfigFilterDao().getCompanyIdList(companyFilterPage);

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(HotelMarkup.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				conjunction.add(Restrictions.in("createdbyCompanyId", companyIds));
				if(companyFilter.getCompanyName() != null && !companyFilter.getCompanyName().equals(""))
				{
					conjunction.add(Restrictions.eq("companyName", companyFilter.getCompanyName()));
					logger.info("##########companyName  added----"+companyFilter.getCompanyName());
				}
				
				if(companyFilter.getCreatedByCompanyName() != null && !companyFilter.getCreatedByCompanyName().equals(""))
				{
					conjunction.add(Restrictions.eq("createdByCompanyName", companyFilter.getCreatedByCompanyName()));
					logger.info("##########getCreatedToCompanyName  added----"+companyFilter.getCreatedByCompanyName());
				}
				
				if(companyFilter.getConfigName() != null && !companyFilter.getConfigName().equals(""))
				{
					conjunction.add(Restrictions.eq("configname", companyFilter.getConfigName()));
					logger.info("##########getConfigName  added----"+companyFilter.getConfigName());
				}
				if(companyFilter.getDestinationType() != null && !companyFilter.getDestinationType().equals("ALL"))
				{
					conjunction.add(Restrictions.eq("destinationType", companyFilter.getDestinationType()));
					logger.info("########## destinationType  added----"+companyFilter.getDestinationType());
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
						conjunction.add(Restrictions.ge("createdDate", date));
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
						conjunction.add(Restrictions.lt("createdDate", date));
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
				List<HotelMarkup> filterHotelMarkupList = new ArrayList<>();
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(HotelMarkup.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					List<HotelMarkup> list = criteria.list();
					logger.info("filterHotelMarkupList size------"+filterHotelMarkupList.size());					
					for(HotelMarkup hotelMarkup:list){
						if(hotelMarkup.getHotelCheckinDate()==null){
							//if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()).equalsIgnoreCase("31-12-0002")){
							hotelMarkup.setCheckIn("ALL");
							//}
						}
						else{
							if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()).equalsIgnoreCase("31-12-0002")){
								hotelMarkup.setCheckIn("ALL");
							}
							else{
								hotelMarkup.setCheckIn(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()));	
							}
						}

						if(hotelMarkup.getHotelCheckoutDate()==null){
							hotelMarkup.setCheckOut("ALL");
						}
						else{
							if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckoutDate()).equalsIgnoreCase("31-12-0002")){
								hotelMarkup.setCheckOut("ALL");
							}
							else{
								hotelMarkup.setCheckOut(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckoutDate()));
							} 
						}

						filterHotelMarkupList.add(hotelMarkup);
					}
				}
				companyFilterPage.setHotelMarkupList(filterHotelMarkupList);
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setHotelMarkupList(new ArrayList<HotelMarkup>());
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


















	/*delete CompanyMarkup List details*/
	public boolean deleteMarkupDetails(int id){
		boolean isDelete=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from FlightMarkup fm where fm.markupId=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			if (result> 0) {
				isDelete = true;
			}
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return isDelete;

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isDelete;

	}


	public FlightMarkup getMarkupDetails(FlightMarkup cm ){
		FlightMarkup markup=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightMarkup fm where fm.markupId =:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",cm.getMarkupId());
			markup= (FlightMarkup)query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		} 
		return markup;
	}

	/*Update markup using congig Id details*/
	public boolean updateCompanyMarkup(FlightMarkup newobj)
	{ 
		boolean updated = false;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			FlightMarkup config =  (FlightMarkup) session.get(FlightMarkup.class,newobj.getMarkupId());
			config.setAccumulative(newobj.getAccumulative());
			config.setArrvDate(newobj.getArrvDate());
			config.setCountry(newobj.getCountry());
			config.setDeptDate(newobj.getDeptDate());
			config.setDestination(newobj.getDestination());
			config.setOrigin(newobj.getOrigin());
			config.setModifiedDate(new Date());
			config.setAirline(newobj.getAirline());
			config.setFixedAmount(newobj.getFixedAmount());
			config.setMarkup(newobj.getMarkup());
			config.setName(newobj.getName()); 
			config.setClassOfService(newobj.getClassOfService()); 
			config.setPromofareStartDate(newobj.getPromofareStartDate());
			config.setPromofareEndDate(newobj.getPromofareEndDate());
			config.setFareBaseCode(newobj.getFareBaseCode());
			config.setDestinationType(newobj.getDestinationType());
			session.update(config);
			transaction.commit();
			updated = true;
		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return updated;

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updated;

	}

	/* this method for fetch company ids  for set the  company markups  by passinng company_parent_user id  */
	public 	List<Company>  getCompanyIds(Company company){
		List<Company> companyIdsList=null;
		String sql = "from Company  com where com.parent_company_userid=:parentcompanyuserid";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("parentcompanyuserid", company.getCompany_userid());
			companyIdsList = query.list();

		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return companyIdsList;

	}
	/* passing  company ids fetch all company config numbers for set company markups */
	public  List<CompanyConfig> getCompanyConfigIds(List<Company> companyIds){
		List<CompanyConfig> companyConfigIds= new ArrayList<CompanyConfig>();
		try{
			for(Company companyObj:companyIds){
				session = HibernateUtil.getSessionFactory().openSession();
				String sql = "from CompanyConfig cc where company_id=:id and is_active=:active";
				Query query = session.createQuery(sql);
				query.setParameter("id", companyObj.getCompanyid());
				query.setParameter("active", true);
				List<CompanyConfig> list=query.list();
				for (CompanyConfig companyConfigObj:list){
					if(companyConfigObj!=null){
						companyConfigIds.add(companyConfigObj);
					}
				} 

			}
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return companyConfigIds;
	}
	/* passing  company id whoever loggedin  and fetch particular   company config numbers for set company markups */
	public  List<CompanyConfig> getAgencyCompanyConfigIds(Company sessionObj){
		List<CompanyConfig> companyConfigIds= new ArrayList<CompanyConfig>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CompanyConfig cc where cc.company_id=:company_id and cc.isActive is true";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", sessionObj.getCompanyid());
			//query.setParameter("active",true);
			companyConfigIds = query.list();
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return companyConfigIds;
	}


	/*fetch flight markup  list  by passing Company_user_id*/
	public List<FlightMarkup> getFlightMarkupListByCompanyUserId(String companyUserId){
		List<FlightMarkup> flightMarkupList =null;
		List<Company> companyIds=new CompanyDAO().getAllComapnyIdsByCompanyUserId(companyUserId);
		List<Integer> newCompanyIds=new ArrayList<>();
		if(companyIds!=null && companyIds.size()>0){
			for(Company companyObj:companyIds){
				newCompanyIds.add(companyObj.getCompanyid());
			}
		}
		try{
			if(companyIds!=null && companyIds.size()>0){
				String sql="from FlightMarkup fm where fm.createdbyCompanyId in (:companyids)";
				logger.info("-----Filter flight_markup- query---"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameterList("companyids", newCompanyIds);
				flightMarkupList = query.list();
			}
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return flightMarkupList;
	}

	/*fetch company Config list by passing user id*/
	public List<FlightMarkup> getFlightMarkupListByUserId(String userId){
		String sql="";
		List<FlightMarkup> flightMarkupList=null;
		try{
			if(userId!=null){
				sql = "from FlightMarkup fm where fm.createdbyUserId =:createdby_userId";
				logger.info("---------get created by company config Query-------"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("createdby_userId", userId);
				flightMarkupList = query.list();
			}
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		} 
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return flightMarkupList;
	}

	public CompanyConfig getCompanyUserIdByConfigId(int configId) {
		// TODO Auto-generated method stub

		CompanyConfig  companyConfig=null;
		try{
			String sql = "from CompanyConfig cc where cc.config_id=:config_id";
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("config_id", configId);
			companyConfig = (CompanyConfig) query.uniqueResult();
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		} 
		finally{
			if(session!=null && session.isOpen())
				session.close();
		}
		return companyConfig;
	}


}
