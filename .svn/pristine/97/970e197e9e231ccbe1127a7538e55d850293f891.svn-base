package com.admin.aircity.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lintas.admin.model.Airport;
import com.lintas.config.HibernateUtil;

public class AirCityDaoImpl  implements AirCityDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirCityDaoImpl.class);

	@Override
	public List<Airport> getAirportCityList() {
		
		Session session=null;
		List<Airport> airportList   = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Airport.class);
			airportList=criteria.list();
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
		return airportList;
	}
 
	 
}
