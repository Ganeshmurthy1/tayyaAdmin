package com.tayyarah.hotel.DAO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.lintas.config.HibernateUtil;
import com.tayyarah.hotel.model.HotelDetails;

public class AddHotelDAO {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AddHotelDAO.class);
	

	public HotelDetails insert(HotelDetails hoteldetails){
		Session session=null;
		Transaction tx=null;
		try{
			if(hoteldetails!=null){
				session=HibernateUtil.getSessionFactory().openSession();
				logger.info("session---------created-------------------"+session);
				tx = session.beginTransaction();
				logger.info("transaction---------created-------------------");
				session.save(hoteldetails);
				logger.info("saved----------------------------");
				tx.commit();
			}
			logger.info("committed------------------------");
		}
		catch(HibernateException he){

			if(tx!=null) 
				tx.rollback();
			throw new RuntimeException("DB is not connecting ,please make connections properly.");
		}
		finally {
			if(session!=null)
				session.close();
		}
		return hoteldetails;		
	}


	public List<HotelDetails> fetchHotelList(){
		Session session=null;
		List<HotelDetails>  list=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelDetails.class);
			list=criteria.list();
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;		
	}


	public HotelDetails getHotelDetails(Long id) {
		Session session=null;
		HotelDetails  hotelDetails=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelDetails.class);
			criteria.add(Restrictions.eq("id", id));
			hotelDetails=(HotelDetails) criteria.uniqueResult();
			logger.info("------getRooms-------"+hotelDetails.getRooms().size());
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return hotelDetails;
	}


	public HotelDetails hotelUpdate(HotelDetails hotelDetails) {
		Session session = null;
		Transaction transaction = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			HotelDetails HotelDetails =  (HotelDetails) session.get(HotelDetails.class,hotelDetails.getId());
			HotelDetails.setActiveStatus(hotelDetails.isActiveStatus());
			HotelDetails.setCity(hotelDetails.getCity());
			HotelDetails.setCompanyUserId(hotelDetails.getCompanyUserId());
			HotelDetails.setCountry(hotelDetails.getCountry());
			HotelDetails.setCreatedAt(hotelDetails.getCreatedAt());
			HotelDetails.setUpdatedAt(new Timestamp(new Date().getTime()));
			HotelDetails.setHotelChain(hotelDetails.getHotelChain());
			HotelDetails.setHotelType(hotelDetails.getHotelType());
			HotelDetails.setHotelCode(hotelDetails.getHotelCode());
			HotelDetails.setLatitude(hotelDetails.getLatitude());
			HotelDetails.setLongitude(hotelDetails.getLongitude());
			HotelDetails.setName(hotelDetails.getName());
			HotelDetails.setRating(hotelDetails.getRating());
			session.update(HotelDetails);
			transaction.commit();
		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return hotelDetails;	

	}


}
