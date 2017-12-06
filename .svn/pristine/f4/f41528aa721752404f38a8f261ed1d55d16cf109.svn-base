package com.admin.flight.fin.sheet.Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.FlightTravelRequestTripDetail;
import com.lintas.config.HibernateUtil;

public class FlightOfflineBooingDao {
	public FlightOrderRow insertFlightOrderRowInfo(FlightOrderRow flightOrderRow){
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(flightOrderRow);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return flightOrderRow;

	}
	
	public boolean insertFlightOrderCustomerInfo(FlightOrderCustomer flightOrderCustomer){
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		boolean isInserted=false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(flightOrderCustomer);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
			isInserted=false;
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return isInserted;

	}

	public boolean insertFlightOrderTripDetailInfo(FlightOrderTripDetail flightOrderTripDetail) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		boolean isInserted=false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(flightOrderTripDetail);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
			 isInserted=false;
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return isInserted;
	}
	
	public boolean insertFlightTravelRequestTripDetailInfo(FlightTravelRequestTripDetail flightOrderTripDetail) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		boolean isInserted=false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(flightOrderTripDetail);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
			 isInserted=false;
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return isInserted;
	}

	public boolean insertFlightOrderCustomerPriceBreakupInfo(FlightOrderCustomerPriceBreakup priceBreakup) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		boolean isInserted=false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(priceBreakup);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
			 isInserted=false;
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return isInserted;
	}
	
}
