package com.lintas.admin.DAO;

import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Country;
import com.admin.common.util.StateInfo;
import com.lintas.admin.model.Language;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.AirlineComparator;

public class CountryDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	/*get Agent List details*/
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CountryDao.class);
	public List<Country> getCountryList()
	{
		List<Country> countryLi=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="from Country";
			Query query = session.createQuery(sql);
			countryLi = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return countryLi;
	}
	
	
	public List<StateInfo> getStateList()
	{
		List<StateInfo> stateLi=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="from StateInfo";
			Query query = session.createQuery(sql);
			stateLi = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return stateLi;
	}
	/*get Airlines List details*/
	public List<Airlinelist> getAirlineList()
	{
		List<Airlinelist> airlineLi=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="from Airlinelist as al order by al.airline desc";
			Query query = session.createQuery(sql);
			airlineLi = query.list();
			HashSet<Airlinelist> hs = new HashSet<Airlinelist>();
			hs.addAll(airlineLi);
			airlineLi.clear();
			airlineLi.addAll(hs);
			java.util.Collections.sort(airlineLi, new AirlineComparator());

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return airlineLi;
	}

	/*get Language List  */
	public List<Language> getLanguageList()
	{
		List<Language> languageLi=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="from Language";
			Query query = session.createQuery(sql);
			languageLi = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return languageLi;
	}
 
}
