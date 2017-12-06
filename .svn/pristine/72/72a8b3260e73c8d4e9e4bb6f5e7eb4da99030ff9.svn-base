package com.admin.hotel.fin.sheet.Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.admin.flight.fin.sheet.model.FlightQuotationHistory;
import com.admin.hotel.fin.sheet.model.HotelQuotationHistory;
import com.lintas.config.HibernateUtil;

public class HotelQuotationHistoryDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelQuotationHistoryDao.class);
	public  HotelQuotationHistory insertHotelQuotationHistory(HotelQuotationHistory hotelQuotationHistory){
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(hotelQuotationHistory);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelQuotationHistory;

	}
	public FlightQuotationHistory insertFlightQuotationHistory(FlightQuotationHistory flightQuotationHistory) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(flightQuotationHistory);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return flightQuotationHistory;
	}
	
	 
	
 
}
