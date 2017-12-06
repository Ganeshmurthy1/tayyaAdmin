package com.common.dsr.helper;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;

public class DsrCreditNoteDaoHelper {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DsrCreditNoteDaoHelper.class);
	public   List<CreditNote> getFlightCreditNoteDetails(FlightOrderRow flightOrderRow){
		Session session=null;
		Conjunction conj=Restrictions.conjunction();
		List<CreditNote>  creditNoteList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CreditNote.class);
			conj.add(Restrictions.eq("rowId", flightOrderRow.getId().intValue()));
			criteria.add(conj);
			creditNoteList =  criteria.list();
			Collections.reverse(creditNoteList);

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return creditNoteList;
	}
	public   List<HotelCreditNote> getHotelCreditNoteDetails(HotelOrderRow hotelOrderRow){
		Session session=null;
		Conjunction conj=Restrictions.conjunction();
		List<HotelCreditNote>  hotelCreditNoteList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelCreditNote.class);
			conj.add(Restrictions.eq("rowId", hotelOrderRow.getId().intValue()));
			criteria.add(conj);
			hotelCreditNoteList =  criteria.list();
			Collections.reverse(hotelCreditNoteList);

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return hotelCreditNoteList;
	}
	public   List<CarCreditNote> getCarCreditNoteDetails(CarOrderRow carOrderRow){
		Session session=null;
		Conjunction conj=Restrictions.conjunction();
		List<CarCreditNote>  creditNoteList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarCreditNote.class);
			conj.add(Restrictions.eq("rowId", carOrderRow.getId().intValue()));
			criteria.add(conj);
			creditNoteList =  criteria.list();
			Collections.reverse(creditNoteList);

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return creditNoteList;
	}
	public   List<BusCreditNote> getBusCreditNoteDetails(BusOrderRow busOrderRow){
		Session session=null;
		Conjunction conj=Restrictions.conjunction();
		List<BusCreditNote>  creditNoteList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusCreditNote.class);
			conj.add(Restrictions.eq("rowId", busOrderRow.getId().intValue()));
			criteria.add(conj);
			creditNoteList =  criteria.list();
			Collections.reverse(creditNoteList);

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return creditNoteList;
	}
	public   List<TrainCreditNote> getTrainCreditNoteDetails(TrainOrderRow trainOrderRow){
		Session session=null;
		Conjunction conj=Restrictions.conjunction();
		List<TrainCreditNote>  creditNoteList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(TrainCreditNote.class);
			conj.add(Restrictions.eq("rowId", trainOrderRow.getId().intValue()));
			criteria.add(conj);
			creditNoteList =  criteria.list();
			Collections.reverse(creditNoteList);

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return creditNoteList;
	}

	public   List<VisaCreditNote> getVisaCreditNoteDetails(VisaOrderRow visaOrderRow){
		Session session=null;
		Conjunction conj=Restrictions.conjunction();
		List<VisaCreditNote>  creditNoteList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(VisaCreditNote.class);
			conj.add(Restrictions.eq("rowId", visaOrderRow.getId().intValue()));
			criteria.add(conj);
			creditNoteList =  criteria.list();
			Collections.reverse(creditNoteList);

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return creditNoteList;
	}


	public   List<InsuranceCreditNote> getInsuranceCreditNoteDetails(InsuranceOrderRow insuranceOrderRow){
		Session session=null;
		Conjunction conj=Restrictions.conjunction();
		List<InsuranceCreditNote>  creditNoteList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(InsuranceCreditNote.class);
			conj.add(Restrictions.eq("rowId", insuranceOrderRow.getId().intValue()));
			criteria.add(conj);
			creditNoteList =  criteria.list();
			Collections.reverse(creditNoteList);

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return creditNoteList;
	}

	public   List<MiscellaneousCreditNote> getMiscellaneousCreditNoteDetails(MiscellaneousOrderRow miscellaneousOrderRow){
		Session session=null;
		Conjunction conj=Restrictions.conjunction();
		List<MiscellaneousCreditNote>  creditNoteList=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(MiscellaneousCreditNote.class);
			conj.add(Restrictions.eq("rowId",miscellaneousOrderRow.getId().intValue()));
			criteria.add(conj);
			creditNoteList =  criteria.list();
			Collections.reverse(creditNoteList);

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return creditNoteList;
	}
}
