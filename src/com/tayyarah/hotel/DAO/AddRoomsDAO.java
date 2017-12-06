package com.tayyarah.hotel.DAO;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.lintas.config.HibernateUtil;
import com.tayyarah.hotel.model.HotelDetails;
import com.tayyarah.hotel.model.HotelRoomDetails;

public class AddRoomsDAO {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AddRoomsDAO.class);
	
	
	public HotelRoomDetails insert(HotelRoomDetails hotelRoomDetails){
         Session session=null;
		Transaction tx=null;
		try{
			if(hotelRoomDetails!=null){
			session=HibernateUtil.getSessionFactory().openSession();
			 logger.info("session---------created-------------------"+session);
            tx = session.beginTransaction();
			logger.info("transaction---------created-------------------");
			 session.save(hotelRoomDetails);
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
		return hotelRoomDetails;		
	}

	public HotelDetails getHotelDetailsById(Long hotelId) {
		// TODO Auto-generated method stub
		 Session session=null;
		 HotelDetails hotelDetails=null;
		 try{
			 session=HibernateUtil.getSessionFactory().openSession();
			 Criteria criteria=session.createCriteria(HotelDetails.class);
			 hotelDetails=(HotelDetails) criteria.add(Restrictions.eq("id", hotelId)).uniqueResult();
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

	public HotelRoomDetails getHotelRoomDetails(Long id) {
		 Session session=null;
		 HotelRoomDetails hotelRoomDetails=null;
		 try{
			 session=HibernateUtil.getSessionFactory().openSession();
			 Criteria criteria=session.createCriteria(HotelRoomDetails.class);
			 hotelRoomDetails=(HotelRoomDetails) criteria.add(Restrictions.eq("id", id)).uniqueResult();
		 }
		 catch(HibernateException he){
			 he.printStackTrace();
		}
		finally {
			if(session!=null)
			 session.close();
		 }
		return hotelRoomDetails;
	}

	public HotelRoomDetails getHotelRoomUpdate(HotelRoomDetails newHotelRoomDetails,HotelRoomDetails oldHotelRoomDetails) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		HotelRoomDetails hotelRoomDetails=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			hotelRoomDetails =  (HotelRoomDetails) session.get(HotelRoomDetails.class,oldHotelRoomDetails.getId());
			hotelRoomDetails.setAmountType(newHotelRoomDetails.getAmountType());
			hotelRoomDetails.setAvailability(newHotelRoomDetails.getAvailability());
			hotelRoomDetails.setBasePrice(newHotelRoomDetails.getBasePrice());
			hotelRoomDetails.setCancelAmount(newHotelRoomDetails.getCancelAmount());
			hotelRoomDetails.setCancelBeforeDay(newHotelRoomDetails.getCancelBeforeDay());
			hotelRoomDetails.setCond(newHotelRoomDetails.getCond());
			hotelRoomDetails.setCreatedAt(oldHotelRoomDetails.getCreatedAt());
			hotelRoomDetails.setEndDate(newHotelRoomDetails.getEndDate());
			hotelRoomDetails.setExtraBedPrice(newHotelRoomDetails.getExtraBedPrice());
			hotelRoomDetails.setHotelDetails(oldHotelRoomDetails.getHotelDetails());
			hotelRoomDetails.setStartDate(newHotelRoomDetails.getStartDate());
			hotelRoomDetails.setTaxPrice(newHotelRoomDetails.getTaxPrice());
			hotelRoomDetails.setUpdatedAt(new Timestamp(new Date().getTime()));
			hotelRoomDetails.setVersion(newHotelRoomDetails.getVersion());
			session.update(hotelRoomDetails);
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
		return hotelRoomDetails;	
 
	}



}
