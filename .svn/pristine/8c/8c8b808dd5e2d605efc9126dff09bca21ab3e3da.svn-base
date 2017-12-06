package com.tayyarah.hotel.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.common.model.Hoteloverview;
import com.lintas.config.HibernateUtilHotel;

public class HotelNameFilterDAO {     

	public static List<String>  getHotelNameList(String city)
	{

		Session session=null;
		List<String> hotelNames = null;
		try
		{

			session = HibernateUtilHotel.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(Hoteloverview.class);
			criteria.add(Restrictions.eq("city", city));
			criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("vendorName"))));
			hotelNames = criteria.list();

		}
		catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return hotelNames;
	}
	public static List<String>  getHotelCitynameListByCountry(String country)
	{

		Session session=null;
		Transaction tx=null;
		List<String> hotelcitynamelist = null;
		try
		{

			session = HibernateUtilHotel.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(Hoteloverview.class);
			criteria.add(Restrictions.eq("country", country));
			criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("city"))));
			hotelcitynamelist = criteria.list();

		}
		catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();

		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return hotelcitynamelist;
	}


}

