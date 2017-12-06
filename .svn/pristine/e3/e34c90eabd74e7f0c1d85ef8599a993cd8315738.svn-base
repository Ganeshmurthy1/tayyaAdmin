package com.admin.hotel.fin.sheet.Dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lintas.admin.common.model.FlightPaymentTxDetailHistory;
import com.lintas.admin.common.model.HotelPaymentTxDetailHistory;
import com.lintas.config.HibernateUtil;

public class HotelPaymentTxHistoryDao {
	public HotelPaymentTxDetailHistory insertHotelPaymentTx(HotelPaymentTxDetailHistory txDetailHistory) {
		Session  session=null;
		Transaction transaction=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			session.save(txDetailHistory);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return txDetailHistory;

	}
	public FlightPaymentTxDetailHistory insertFlightPaymentTxHistory(FlightPaymentTxDetailHistory flightPaymentTxDetailHistory) {
		Session  session=null;
		Transaction transaction=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			session.save(flightPaymentTxDetailHistory);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return flightPaymentTxDetailHistory;

	}
}
