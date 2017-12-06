package com.lintas.deals.DAO;

import java.util.ArrayList;
import java.util.List;

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
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.CrudOperationDeals;
import com.lintas.admin.model.ImageUrl;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.InvoiceFilter;


public class CrudOperationDealsDaoImp implements CrudOperationDealsDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CrudOperationDealsDaoImp.class);
	@Override
	public CrudOperationDeals saveOrUpdateDeals(CrudOperationDeals deals) {
		// TODO Auto-generated method stub
		boolean success = false; 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			//deals.setImageUrl(ImageUrl.DealImagepath+deals.getImageUrl());
			session.saveOrUpdate(deals);
			success = true;
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return deals;
	}


	//update Deal image path
	public CrudOperationDeals updatDealImagepath(CrudOperationDeals obj){
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(obj);
			transaction.commit();
		}catch(HibernateException e){
			logger.info(""+e.getMessage());
		}
		finally{
			session.close();
		}
		return obj;

	}

	@Override
	public List<CrudOperationDeals> listOfDeals() {
		List<CrudOperationDeals> listDeals = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CrudOperationDeals";
			Query query = session.createQuery(sql);
			listDeals = query.list();
		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		catch (Exception e) {
			logger.error("------Exception-------"+  e.getMessage());

		}
		finally {
			session.close(); 
		}

		return listDeals;
	}

	@Override
	public CrudOperationDeals DealsById(CrudOperationDeals dealId)
	{
		CrudOperationDeals deals = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CrudOperationDeals com where com.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", dealId.getId());
			deals = (CrudOperationDeals) query.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return deals;
	}
	@Override
	public boolean deleteDeal(Long dealId) {
		boolean isDelete=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from CrudOperationDeals cd where cd.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", dealId);
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
			session.close(); 
		}
		return isDelete;

		// TODO Auto-generated method stub

	}

	@Override
	public CrudOperationDeals UpdateDeals(CrudOperationDeals deals) {
		// TODO Auto-generated method stub
		logger.info("deal id--------"+deals.getId());
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			/*deals = (CrudOperationDeals) session.get(CrudOperationDeals.class, id.getId());
			 deals.setDealsType(id.getDealsType());*/
			transaction = session.beginTransaction();
			deals.setImageUrl(ImageUrl.DealImagepath+deals.getImageUrl());
			session.update(deals);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return deals; 
	}

	@Override
	public CrudOperationDeals GetCmsDetails(Long cmsid) {
		CrudOperationDeals cms = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "from CrudOperationDeals com where com.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",cmsid);
			cms =(CrudOperationDeals) query.uniqueResult();
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return cms;
	}

	@Override
	public CrudOperationDeals updateCmsActiveOrInactive(CrudOperationDeals cms) {
		// TODO Auto-generated method stub
		{
			CrudOperationDeals status= new CrudOperationDeals();
			try{
				if(cms!=null){
					session = HibernateUtil.getSessionFactory().openSession();
					transaction = session.beginTransaction();		
					CrudOperationDeals config =  (CrudOperationDeals) session.get(CrudOperationDeals.class,cms.getId());
					config.setStatus(cms.isStatus());
					status.setStatus(config.isStatus());
					session.update(config);
					transaction.commit();
				}
			} catch(Exception e){
				if(transaction!=null)
					transaction.rollback();
				logger.error("---------Exception---------"+e.getMessage());

			}finally{
				session.close();
			}
			return status;

		}

	}

	@Override
	public List<CrudOperationDeals> filterDelasByDealType(InvoiceFilter filterObj) {
		// TODO Auto-generated method stub
		List<CrudOperationDeals> cmsList = null;
		String filterQuery=null; 
		Query query = null;

		//logger.info("-----Deals---filterQuery---------"+filterQuery);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(filterObj!=null){
				if(filterObj.getFilterDealType().equalsIgnoreCase("all")){
					filterQuery = "from CrudOperationDeals";
					query = session.createQuery(filterQuery);

				}
				else{
					filterQuery = "from CrudOperationDeals com where com.catagory=:catagory";
					query = session.createQuery(filterQuery);
					query.setParameter("catagory", filterObj.getFilterDealType());
				}
			}
			logger.info("filterQuery" +filterQuery);

			cmsList = (List<CrudOperationDeals>)query.list();

		}catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return cmsList;

	}

	public  CompanyFilterPage listOfDeals(CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CrudOperationDeals.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				if(companyFilter.getDestinationType() != null && !companyFilter.getDestinationType().equals("") &&  !companyFilter.getDestinationType().equals("ALL"))
				{
					conjunction.add(Restrictions.eq("catagory", companyFilter.getDestinationType() ));
					logger.info("##########dealsType  added----"+companyFilter.getDestinationType());
				}
				if(companyFilter.getStartdate() != null && !companyFilter.getStartdate().equals(""))
				{
					 
					try{
						 
						conjunction.add(Restrictions.eq("Startdate", companyFilter.getStartdate()));
						logger.info("##########date min added to conjuction---"+companyFilter.getStartdate());

					}catch(Exception ex)
					{
						logger.info("##########companyFilter.getEnddate()---"+ex.getMessage());

					}
				} 
				if(companyFilter.getEnddate() != null && !companyFilter.getEnddate().equals(""))
				{ 
					 
					try{
						 
						conjunction.add(Restrictions.eq("Enddate", companyFilter.getEnddate()));
						logger.info("##########companyFilter.getEnddate()---"+companyFilter.getEnddate());

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				} 
				/*if(companyFilter.getAmountRangeBooking() != null && companyFilter.getAmountRangeBooking().getAmountMin() != null && companyFilter.getAmountRangeBooking().getAmountMax() != null )
				{
					conjunction.add(Restrictions.ge("price", companyFilter.getAmountRangeBooking().getAmountMin()));
					conjunction.add(Restrictions.lt("price", companyFilter.getAmountRangeBooking().getAmountMax()));	
					logger.info("##########booking amount added to conjuction---"+companyFilter.getAmountRangeBooking().getAmountMin()+"  to "+companyFilter.getAmountRangeBooking().getAmountMax());
				}*/
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
				List<CrudOperationDeals> crudOperationDealsList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(CrudOperationDeals.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					crudOperationDealsList = criteria.list();
					logger.info("crudOperationDealsList size------"+crudOperationDealsList.size());					
				}
				companyFilterPage.setCrudOperationDealsList(crudOperationDealsList);
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setCrudOperationDealsList(new ArrayList<CrudOperationDeals>());
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





