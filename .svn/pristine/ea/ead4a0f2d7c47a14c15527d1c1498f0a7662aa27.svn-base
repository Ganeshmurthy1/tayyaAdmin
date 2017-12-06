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
import com.lintas.config.HibernateUtil;
import com.tayyarah.bus.model.BusMarkup;

public class BusMarkupDao {
	 
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MarkupDao.class);
	/*Insert the Markup details into the database*/
	public BusMarkup insertMarkupDetails(BusMarkup busMarkup) throws Exception
	{ 
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(busMarkup);
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			logger.info("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return busMarkup;
	}

	/*get CompanyMarkup List details*/
	public List<BusMarkup> getMarkupList(Company sessionObj)
	{
		List<Company> companyIds=new CompanyDAO().getAllComapnyIdsByCompanyUserId(sessionObj.getCompany_userid());
		logger.info("companyIds---------"+companyIds.size());
		List<BusMarkup> markupLi=null;
		List<Integer> newCompanyIds=new ArrayList<>();
		if(companyIds!=null && companyIds.size()>0){
			for(Company companyObj:companyIds){
				newCompanyIds.add(companyObj.getCompanyid());
			}
		}
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="from BusMarkup bm where bm.createdbyCompanyId in (:companyids) order by bm.markupId desc";
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
 

	/*get Bus markup list List details*/
	public  CompanyFilterPage getFilterBusMarkupList(CompanyFilterPage companyFilterPage)
	{
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		List<Integer> companyIds =new CompanyConfigFilterDao().getCompanyIdList(companyFilterPage);

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusMarkup.class);
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
				if(companyFilter.getCompanyId()>0 )
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
				 
				if(companyFilter.getDateFilterCreated() != null && companyFilter.getDateFilterCreated().getDtEnd() != null && companyFilter.getDateFilterCreated().getDtStart() != null )
				{
					//2017-06-28
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
				List<BusMarkup> filterBusMarkupList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(BusMarkup.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("markupId"));
					filterBusMarkupList = criteria.list();
					logger.info("filterBusMarkupList size------"+filterBusMarkupList.size());					
				}
				companyFilterPage.setBusMarkupList(filterBusMarkupList);
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setBusMarkupList(new ArrayList<BusMarkup>());
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
			String sql = "delete from BusMarkup fm where fm.markupId=:id";
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


	public BusMarkup getMarkupDetails(BusMarkup cm ){
		BusMarkup markup=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusMarkup fm where fm.markupId =:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",cm.getMarkupId());
			markup= (BusMarkup)query.uniqueResult();
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
	public boolean updateBusMarkup(BusMarkup newobj)
	{ 
		boolean updated = false;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			BusMarkup config =  (BusMarkup) session.get(BusMarkup.class,newobj.getMarkupId()); 
			config.setArrDate(newobj.getArrDate());
			//config.setCity(newobj.getCity());
			config.setDepDate(newobj.getDepDate());
			config.setDestination(newobj.getDestination());
			config.setOrigin(newobj.getOrigin());
			config.setModifiedDate(new Date());
			config.setMarkupName(newobj.getMarkupName());  
			config.setFixedAmount(newobj.isFixedAmount());
			config.setAccumulative(newobj.isAccumulative());
			config.setMarkupAmount(newobj.getMarkupAmount());
			config.setBusTypes(newobj.getBusTypes()); 
			config.setPromofareStartDate(newobj.getPromofareStartDate());
			config.setPromofareEndDate(newobj.getPromofareEndDate());
			config.setPositionOfMarkup(newobj.getPositionOfMarkup()); 
			config.setMarkUpOnTotal(newobj.isMarkUpOnTotal());
			config.setMarkUpPerPassenger(newobj.isMarkUpPerPassenger());
			config.setBusOperators(newobj.getBusOperators());
		
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


	/*fetch bus markup  list  by passing Company_user_id*/
	public List<BusMarkup> getFlightMarkupListByCompanyUserId(String companyUserId){
		List<BusMarkup> busMarkupList =null;
		List<Company> companyIds=new CompanyDAO().getAllComapnyIdsByCompanyUserId(companyUserId);
		List<Integer> newCompanyIds=new ArrayList<>();
		if(companyIds!=null && companyIds.size()>0){
			for(Company companyObj:companyIds){
				newCompanyIds.add(companyObj.getCompanyid());
			}
		}
		try{
			if(companyIds!=null && companyIds.size()>0){
				String sql="from BusMarkup fm where fm.createdbyCompanyId in (:companyids)";
				logger.info("-----Filter bus_markup- query---"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameterList("companyids", newCompanyIds);
				busMarkupList = query.list();
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
		return busMarkupList;
	}

	/*fetch company Config list by passing user id*/
	public List<BusMarkup> getBusMarkupListByUserId(String userId){
		String sql="";
		List<BusMarkup> busMarkupList=null;
		try{
			if(userId!=null){
				sql = "from FlightMarkup fm where fm.createdbyUserId =:createdby_userId";
				logger.info("---------get created by company config Query-------"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("createdby_userId", userId);
				busMarkupList = query.list();
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
		return busMarkupList;
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
