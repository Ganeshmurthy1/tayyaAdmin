package com.lintas.admin.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.model.Airport;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.AirlineComparator;

public class AirportDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirportDao.class);
	public List<Airport> GetAllAirportList()
	{
		Session session=null;
		Transaction tx=null;
		List<Airport> airportlist = null;
		try
		{
			 
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(Airport.class);
			airportlist = criteria.list();
			Collections.sort(airportlist,new AirlineComparator(). new AirportComparator());
			 tx.commit();
		}
		catch (HibernateException e) {
			if(tx!=null)
				 tx.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return airportlist;
	}

	
	public CompanyFilterPage GetAllAirportList(CompanyFilterPage companyFilterPage)
	{
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Airport.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{

				companyFilter = companyFilterPage.getCompanyFilter();
				if(companyFilter.getCountryName() != null && !companyFilter.getCountryName().equals("") &&  !companyFilter.getCountryName().equals("ALL"))
				{
					conjunction.add(Restrictions.eq("country", companyFilter.getCountryName() ));
					logger.info("##########getCountryName  added----"+companyFilter.getCountryName());
				}
				if(companyFilter.getAirportName() != null && !companyFilter.getAirportName().equals("") &&  !companyFilter.getAirportName().equals("ALL"))
				{
					conjunction.add(Restrictions.eq("airport_name", companyFilter.getAirportName() ));
					logger.info("##########getAirportName  added----"+companyFilter.getAirportName());
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
				List<Airport> airportList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(Airport.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					airportList = criteria.list();
					logger.info("airportList size------"+airportList.size());					
				}
				companyFilterPage.setAirportList(airportList);
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setAirportList(new ArrayList<Airport>());
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

 
	public List<Airport> filterAirportList(Airport airport)
	{
		List<Airport> airportlist = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(Airport.class);
			if(!airport.getCountry().equals("") && !airport.getAirport_code().equals("")){
				cr.add(Restrictions.eq("country", airport.getCountry()));
				cr.add(Restrictions.eq("airport_code", airport.getAirport_code()));
			}
			if(airport.getCountry().equals("") && !airport.getAirport_code().equals("")){
				cr.add(Restrictions.eq("airport_code", airport.getAirport_code()));
			}
			if(airport.getAirport_code().equals("") && !airport.getCountry().equals("")){
				cr.add(Restrictions.eq("country", airport.getCountry()));
			}
			airportlist = cr.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return airportlist;
	}




	public Boolean AddAirport(Airport airport)
	{
		boolean isAdded = false;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			//deals.setImageUrl(ImageUrl.DealImagepath+deals.getImageUrl());
			session.saveOrUpdate(airport);
			isAdded = true;
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			isAdded = false;
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return isAdded;
	}
	public Airport EditAirport(Airport airport)
	{
		Airport cuurentairport = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from Airport com where com.airport_code=:airport_code";
			Query query = session.createQuery(sql);
			query.setParameter("airport_code",airport.getAirport_code());
			cuurentairport = (Airport) query.uniqueResult();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();

		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return cuurentairport;
	}
	public Airport UpdateAirport(Airport airport)
	{
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			transaction = session.beginTransaction();			
			session.update(airport);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();

		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return airport;
	}

	public Boolean DeleteAirport(Airport airport)
	{

		boolean isDelete=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from Airport ar  where ar.airport_code=:airport_code";
			Query query = session.createQuery(sql);
			query.setParameter("airport_code", airport.getAirport_code());
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
			if(session != null && session.isOpen())
				session.close(); 
		}
		return isDelete;

	}
	 
	 
 }
