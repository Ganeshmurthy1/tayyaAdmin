package com.lintas.admin.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.lintas.cache.flight.model.FlightSearchCacheDestination;
import com.lintas.config.HibernateUtil;

/**
 * @author MANISH
 *
 */
public class FlightSearchCacheDao {

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FlightSearchCacheDao.class);

	public FlightSearchCacheDestination insertflightSearchCacheDetails(FlightSearchCacheDestination flightSearchCacheDestination) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(flightSearchCacheDestination);
			tx.commit();
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flightSearchCacheDestination;
	}

	public FlightSearchCacheDestination deleteCacheById(long id) {
		Session session = null;
		FlightSearchCacheDestination flightSearchCacheDestination =null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			flightSearchCacheDestination=(FlightSearchCacheDestination) session.get(FlightSearchCacheDestination.class, id);
			transaction=session.beginTransaction();
			session.delete(flightSearchCacheDestination);
			transaction.commit();
			
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flightSearchCacheDestination;
	}
	
	public List<FlightSearchCacheDestination> getFlightSearchCacheList() {
		Session session = null;
		List<FlightSearchCacheDestination> flightSearchCacheModelList =new ArrayList<FlightSearchCacheDestination>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightSearchCacheDestination.class);
			flightSearchCacheModelList =  criteria.list();
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flightSearchCacheModelList;
	}
	


	public  boolean getAndUpdateCacheDetails(FlightSearchCacheDestination flightSearchCacheDestination) {
		Session session = null;
		Transaction transaction = null;
		boolean updateFlag=false;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			FlightSearchCacheDestination cacheModel =  (FlightSearchCacheDestination) session.get(FlightSearchCacheDestination.class,flightSearchCacheDestination.getId());
			if(cacheModel!=null)
			{
				cacheModel.setDestination(flightSearchCacheDestination.getDestination());
				cacheModel.setOneway(flightSearchCacheDestination.isOneway());
				cacheModel.setOrigin(flightSearchCacheDestination.getOrigin());
				session.update(cacheModel);
				transaction.commit();
				updateFlag=true;
			}

		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}
		return updateFlag;
	}


	

}
