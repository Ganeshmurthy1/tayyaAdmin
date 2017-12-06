package com.lintas.admin.DAO;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderModifyInfo;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.action.order.modify.model.BusOrderModifyInfo;
import com.lintas.action.order.modify.model.CarOrderModifyInfo;
import com.lintas.action.order.modify.model.InsuranceOrderModifyInfo;
import com.lintas.action.order.modify.model.TrainOrderModifyInfo;
import com.lintas.action.order.modify.model.VisaOrderModifyInfo;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.BusOrderRowCancellation;
import com.lintas.admin.common.model.CarOrderRowCancellation;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.common.model.HotelOrderModifyInfo;
import com.lintas.admin.common.model.HotelOrderRowCancellation;
import com.lintas.admin.common.model.InsuranceOrderRowCancellation;
import com.lintas.admin.common.model.OrderModifiedInfo;
import com.lintas.admin.common.model.TrainOrderRowCancellation;
import com.lintas.admin.common.model.VisaOrderRowCancellation;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderRowCancellation;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.config.RandomConfigurationNumber;

public class OrderModifyDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(OrderModifyDao.class);
	public FlightOrderRow updateFlightOrderRowBookingStatusInfo(FlightOrderRow flightOrderRow) {
		Session session = null;
		Transaction transaction = null;
		Long rowId=null;
		FlightOrderRow updateObj = flightOrderRow;
		try{
			if(flightOrderRow!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				updateObj =  (FlightOrderRow) session.get(FlightOrderRow.class,flightOrderRow.getId());
				updateObj.setUpdatedAt(new Timestamp(new Date().getTime()));
				updateObj.setStatusAction(flightOrderRow.getStatusAction());
				updateObj.setPaymentStatus(flightOrderRow.getPaymentStatus());
				if(flightOrderRow.getCancelationMode()!=null){
					if(flightOrderRow.getCancelationMode().equals("Offline")){
						updateObj.setCancelationMode(flightOrderRow.getCancelationMode());
						
					}	
				}else{
					updateObj.setCancelationMode(null);
					
				}
				
				if(flightOrderRow.isCreditNoteIssued()) 
					updateObj.setCreditNoteIssued(true);
				updateObj.setUser_commit(flightOrderRow.getUser_commit());
				updateObj.setOrderUpdated(flightOrderRow.isOrderUpdated());
				updateObj.setOrderRequested(flightOrderRow.isOrderRequested());
				session.update(updateObj);
				rowId=updateObj.getId();
				transaction.commit();

			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;

	}

	public HotelOrderRow updateHotelOrderRowBookingStatusInfo(HotelOrderRow hotelOrderRow) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		HotelOrderRow updateObj=null;
		try{
			if(hotelOrderRow!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				updateObj =  (HotelOrderRow) session.get(HotelOrderRow.class,hotelOrderRow.getId());
				updateObj.setUpdatedAt(new Timestamp(new Date().getTime()));
				updateObj.setStatusAction(hotelOrderRow.getStatusAction());
				updateObj.setPaymentStatus(hotelOrderRow.getPaymentStatus());
				updateObj.setUserComments(hotelOrderRow.getUserComments());
				updateObj.setUpdatedBy(hotelOrderRow.getUpdatedBy());
				if(hotelOrderRow.getCancelMode()!=null){
					if(hotelOrderRow.getCancelMode().equals("Offline")){
						updateObj.setCancelMode(hotelOrderRow.getCancelMode());
						
					}	
				}else{
					updateObj.setCancelMode(null);
					
				}
				updateObj.setOrderRequested(true);
				updateObj.setOrderUpdated(hotelOrderRow.isOrderUpdated());
				//updateObj.setCreditNote(hotelOrderRow.getCreditNote());
				session.update(updateObj);
				transaction.commit();
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;

	}
	public CarOrderRow updateCarOrderRowBookingStatusInfo(CarOrderRow carOrderRow) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		CarOrderRow updateObj=null;
		try{
			if(carOrderRow!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				updateObj =  (CarOrderRow) session.get(CarOrderRow.class,carOrderRow.getId());
				updateObj.setUpdatedAt(new Timestamp(new Date().getTime()));
				updateObj.setStatusAction(carOrderRow.getStatusAction());
				updateObj.setPaymentStatus(carOrderRow.getPaymentStatus());
				updateObj.setUserComments(carOrderRow.getUserComments());
				updateObj.setUpdatedBy(carOrderRow.getUpdatedBy());
				updateObj.setOrderRequested(true);
				updateObj.setOrderUpdated(carOrderRow.isOrderUpdated());
				//updateObj.setCreditNote(hotelOrderRow.getCreditNote());
				session.update(updateObj);
				transaction.commit();
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;

	}
	public BusOrderRow updateBusOrderRowBookingStatusInfo(BusOrderRow busOrderRow) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		BusOrderRow updateObj=null;
		try{
			if(busOrderRow!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				updateObj =  (BusOrderRow) session.get(BusOrderRow.class,busOrderRow.getId());
				updateObj.setUpdatedAt(new Timestamp(new Date().getTime()));
				updateObj.setStatusAction(busOrderRow.getStatusAction());
				updateObj.setPaymentStatus(busOrderRow.getPaymentStatus());
				if(busOrderRow.getCancelMode()!=null){
					updateObj.setCancelMode(busOrderRow.getCancelMode());
				}
				if(busOrderRow.isCreditNoteIssued()) 
					updateObj.setCreditNoteIssued(true);
				updateObj.setUserComments(busOrderRow.getUserComments());
				updateObj.setUpdatedBy(busOrderRow.getUpdatedBy());
				updateObj.setOrderRequested(true);
				updateObj.setOrderUpdated(busOrderRow.isOrderUpdated());
				//updateObj.setCreditNote(hotelOrderRow.getCreditNote());
				session.update(updateObj);
				transaction.commit();
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;

	}
	public TrainOrderRow updateTrainOrderRowBookingStatusInfo(TrainOrderRow trainOrderRow) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		TrainOrderRow updateObj=null;
		try{
			if(trainOrderRow!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				updateObj =  (TrainOrderRow) session.get(TrainOrderRow.class,trainOrderRow.getId());
				updateObj.setUpdatedAt(new Timestamp(new Date().getTime()));
				updateObj.setStatusAction(trainOrderRow.getStatusAction());
				updateObj.setPaymentStatus(trainOrderRow.getPaymentStatus());
				updateObj.setUserComments(trainOrderRow.getUserComments());
				updateObj.setUpdatedBy(trainOrderRow.getUpdatedBy());
				updateObj.setOrderRequested(true);
				updateObj.setOrderUpdated(trainOrderRow.isOrderUpdated());
				//updateObj.setCreditNote(hotelOrderRow.getCreditNote());
				session.update(updateObj);
				transaction.commit();
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;

	}
	public VisaOrderRow updateVisaOrderRowBookingStatusInfo(VisaOrderRow visaOrderRow) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		VisaOrderRow updateObj=null;
		try{
			if(visaOrderRow!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				updateObj =  (VisaOrderRow) session.get(VisaOrderRow.class,visaOrderRow.getId());
				updateObj.setUpdatedAt(new Timestamp(new Date().getTime()));
				updateObj.setStatusAction(visaOrderRow.getStatusAction());
				updateObj.setPaymentStatus(visaOrderRow.getPaymentStatus());
				updateObj.setUserComments(visaOrderRow.getUserComments());
				updateObj.setUpdatedBy(visaOrderRow.getUpdatedBy());
				updateObj.setOrderRequested(true);
				updateObj.setOrderUpdated(visaOrderRow.isOrderUpdated());
				//updateObj.setCreditNote(hotelOrderRow.getCreditNote());
				session.update(updateObj);
				transaction.commit();
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;

	}
	public InsuranceOrderRow updateInsuranceOrderRowBookingStatusInfo(InsuranceOrderRow insuranceOrderRow) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		InsuranceOrderRow updateObj=null;
		try{
			if(insuranceOrderRow!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				updateObj =  (InsuranceOrderRow) session.get(InsuranceOrderRow.class,insuranceOrderRow.getId());
				updateObj.setUpdatedAt(new Timestamp(new Date().getTime()));
				updateObj.setStatusAction(insuranceOrderRow.getStatusAction());
				updateObj.setPaymentStatus(insuranceOrderRow.getPaymentStatus());
				updateObj.setUserComments(insuranceOrderRow.getUserComments());
				updateObj.setUpdatedBy(insuranceOrderRow.getUpdatedBy());
				updateObj.setOrderRequested(true);
				updateObj.setOrderUpdated(insuranceOrderRow.isOrderUpdated());
				//updateObj.setCreditNote(hotelOrderRow.getCreditNote());
				session.update(updateObj);
				transaction.commit();
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;

	}
	
	public MiscellaneousOrderRow updateMiscellaneousOrderRowBookingStatusInfo(MiscellaneousOrderRow miscellaneousOrderRow) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		MiscellaneousOrderRow updateObj=null;
		try{
			if(miscellaneousOrderRow!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				updateObj =  (MiscellaneousOrderRow) session.get(MiscellaneousOrderRow.class,miscellaneousOrderRow.getId());
				updateObj.setUpdatedAt(new Timestamp(new Date().getTime()));
				updateObj.setStatusAction(miscellaneousOrderRow.getStatusAction());
				updateObj.setPaymentStatus(miscellaneousOrderRow.getPaymentStatus());
//				updateObj.setUserComments(insuranceOrderRow.getUserComments());
				updateObj.setUpdatedBy(miscellaneousOrderRow.getUpdatedBy());
				updateObj.setOrderRequested(true);
				updateObj.setOrderUpdated(miscellaneousOrderRow.isOrderUpdated());
				//updateObj.setCreditNote(hotelOrderRow.getCreditNote());
				session.update(updateObj);
				transaction.commit();
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;

	}


	public long insertFlightOrderModifiedInfo(OrderModifiedInfo orderModifiedInfo){
		Session session = null;
		Transaction transaction = null;
		Long uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Long) session.save(orderModifiedInfo);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		//logger.info("insertOrderModifiedInfo-------"+uniqId);
		return uniqId;
	}


	public long insertHotelOrderModifiedInfo(HotelOrderModifyInfo orderModifiedInfo){
		Session session = null;
		Transaction transaction = null;
		Long uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Long) session.save(orderModifiedInfo);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}
	public long insertVisaOrderModifiedInfo(VisaOrderModifyInfo orderModifiedInfo){
		Session session = null;
		Transaction transaction = null;
		Long uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Long) session.save(orderModifiedInfo);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}

	public long insertCarOrderModifiedInfo(CarOrderModifyInfo orderModifiedInfo){
		Session session = null;
		Transaction transaction = null;
		Long uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Long) session.save(orderModifiedInfo);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}


	public long insertBusOrderModifiedInfo(BusOrderModifyInfo orderModifiedInfo){
		Session session = null;
		Transaction transaction = null;
		Long uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Long) session.save(orderModifiedInfo);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}

	public long insertTrainOrderModifiedInfo(TrainOrderModifyInfo orderModifiedInfo){
		Session session = null;
		Transaction transaction = null;
		Long uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Long) session.save(orderModifiedInfo);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}

	public long insertInsuranceOrderModifiedInfo(InsuranceOrderModifyInfo orderModifiedInfo){
		Session session = null;
		Transaction transaction = null;
		Long uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Long) session.save(orderModifiedInfo);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}

	public long insertMiscellaneousOrderModifiedInfo(MiscellaneousOrderModifyInfo orderModifiedInfo){
		Session session = null;
		Transaction transaction = null;
		Long uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Long) session.save(orderModifiedInfo);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}
	public int insertFlightCreditNoteInfo(CreditNote creditNote) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Integer uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Integer) session.save(creditNote);
			creditNote.setConvertDate("date");
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}

	public int insertHotelCreditNoteInfo(HotelCreditNote creditNote) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Integer uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Integer) session.save(creditNote);
			creditNote.setConvertDate("date");
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}

	public int insertCarCreditNoteInfo(CarCreditNote creditNote) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Integer uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Integer) session.save(creditNote);
			creditNote.setConvertDate("date");
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}
	public int insertBusCreditNoteInfo(BusCreditNote creditNote) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Integer uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Integer) session.save(creditNote);
			creditNote.setConvertDate("date");
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}
	public int insertTrainCreditNoteInfo(TrainCreditNote creditNote) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Integer uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Integer) session.save(creditNote);
			creditNote.setConvertDate("date");
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}
	public int insertVisaCreditNoteInfo(VisaCreditNote creditNote) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Integer uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Integer) session.save(creditNote);
			creditNote.setConvertDate("date");
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}


	public int insertInsuranceCreditNoteInfo(InsuranceCreditNote creditNote) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Integer uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Integer) session.save(creditNote);
			creditNote.setConvertDate("date");
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}
	
	public int insertMiscellaneousCreditNoteInfo(MiscellaneousCreditNote creditNote) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Integer uniqId=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			uniqId=(Integer) session.save(creditNote);
			creditNote.setConvertDate("date");
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return uniqId;
	}

	public CreditNote getCreditNoteId(Long flightOrderRowId, String companyId) {
		// TODO Auto-generated method stub
		Session session = null;
		CreditNote  creditNote=null;
		String sql = "from CreditNote cn where cn.rowId=:rowId and  cn.companyId=:companyid";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("rowId", flightOrderRowId.intValue());
			query.setParameter("companyid", companyId);
			creditNote = (CreditNote) query.uniqueResult(); 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;
	}
	public HotelCreditNote getHotelCreditNoteId(Long hotelOrderRowId, String companyId) {
		// TODO Auto-generated method stub
		Session session = null;
		HotelCreditNote  creditNote=null;
		String sql = "from HotelCreditNote hcn  where  hcn.rowId=:rowid and  hcn.companyId=:companyId";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  query = session.createQuery(sql);
			query.setParameter("rowid",hotelOrderRowId.intValue());
			query.setParameter("companyId",companyId);
			creditNote = (HotelCreditNote) query.uniqueResult(); 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;
	}
	public CarCreditNote getCarCreditNoteId(Long hotelOrderRowId, String companyId) {
		// TODO Auto-generated method stub
		Session session = null;
		CarCreditNote  creditNote=null;
		String sql = "from CarCreditNote hcn  where  hcn.rowId=:rowid and  hcn.companyId=:companyId";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  query = session.createQuery(sql);
			query.setParameter("rowid",hotelOrderRowId.intValue());
			query.setParameter("companyId",companyId);
			creditNote = (CarCreditNote) query.uniqueResult(); 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;
	}


	public BusCreditNote getBusCreditNoteId(Long hotelOrderRowId, String companyId) {
		// TODO Auto-generated method stub
		Session session = null;
		BusCreditNote  creditNote=null;
		String sql = "from BusCreditNote hcn  where  hcn.rowId=:rowid and  hcn.companyId=:companyId";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  query = session.createQuery(sql);
			query.setParameter("rowid",hotelOrderRowId.intValue());
			query.setParameter("companyId",companyId);
			creditNote = (BusCreditNote) query.uniqueResult(); 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;
	}

	public TrainCreditNote getTrainCreditNoteId(Long hotelOrderRowId, String companyId) {
		// TODO Auto-generated method stub
		Session session = null;
		TrainCreditNote  creditNote=null;
		String sql = "from TrainCreditNote hcn  where  hcn.rowId=:rowid and  hcn.companyId=:companyId";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  query = session.createQuery(sql);
			query.setParameter("rowid",hotelOrderRowId.intValue());
			query.setParameter("companyId",companyId);
			creditNote = (TrainCreditNote) query.uniqueResult(); 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;
	}

	public VisaCreditNote getVisaCreditNoteId(Long hotelOrderRowId, String companyId) {
		// TODO Auto-generated method stub
		Session session = null;
		VisaCreditNote  creditNote=null;
		String sql = "from VisaCreditNote hcn  where  hcn.rowId=:rowid and  hcn.companyId=:companyId";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  query = session.createQuery(sql);
			query.setParameter("rowid",hotelOrderRowId.intValue());
			query.setParameter("companyId",companyId);
			creditNote = (VisaCreditNote) query.uniqueResult(); 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;
	}

	public InsuranceCreditNote getInsuranceCreditNoteId(Long hotelOrderRowId, String companyId) {
		// TODO Auto-generated method stub
		Session session = null;
		InsuranceCreditNote  creditNote=null;
		String sql = "from InsuranceCreditNote hcn  where  hcn.rowId=:rowid and  hcn.companyId=:companyId";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  query = session.createQuery(sql);
			query.setParameter("rowid",hotelOrderRowId.intValue());
			query.setParameter("companyId",companyId);
			creditNote = (InsuranceCreditNote) query.uniqueResult(); 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;
	}
	public MiscellaneousCreditNote getMiscellaneousCreditNoteId(Long miscellaneousOrderRowId, String companyId) {
		Session session = null;
		MiscellaneousCreditNote  creditNote=null;
		String sql = "from MiscellaneousCreditNote hcn  where  hcn.rowId=:rowid and  hcn.companyId=:companyId";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query  query = session.createQuery(sql);
			query.setParameter("rowid",miscellaneousOrderRowId.intValue());
			query.setParameter("companyId",companyId);
			creditNote = (MiscellaneousCreditNote) query.uniqueResult(); 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;
	}
	public CreditNote updateCreditNoteData(CreditNote creditNote) {
		// TODO Auto-generated method stub
		CreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (CreditNote) session.get(CreditNote.class,creditNote.getId());
			updateObj.setCancellationFees(creditNote.getCancellationFees());
			updateObj.setManagementFees(creditNote.getManagementFees());
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}
	public HotelCreditNote updateHotelCreditNoteData(HotelCreditNote creditNote) {
		// TODO Auto-generated method stub
		HotelCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (HotelCreditNote) session.get(HotelCreditNote.class,creditNote.getId());
			updateObj.setCancellationFees(creditNote.getCancellationFees());
			updateObj.setManagementFees(creditNote.getManagementFees());
			updateObj.setConvenienceFees(creditNote.getConvenienceFees());
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public CarCreditNote updateCarCreditNoteData(CarCreditNote creditNote) {
		// TODO Auto-generated method stub
		CarCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (CarCreditNote) session.get(CarCreditNote.class,creditNote.getId());
			updateObj.setCancellationFees(creditNote.getCancellationFees());
			updateObj.setManagementFees(creditNote.getManagementFees());
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public BusCreditNote updateBusCreditNoteData(BusCreditNote creditNote) {
		// TODO Auto-generated method stub
		BusCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (BusCreditNote) session.get(BusCreditNote.class,creditNote.getId());
			updateObj.setCancellationFees(creditNote.getCancellationFees());
			updateObj.setConvenienceFees(creditNote.getConvenienceFees());
			updateObj.setManagementFees(creditNote.getManagementFees());
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public TrainCreditNote updateTrainCreditNoteData(TrainCreditNote creditNote) {
		// TODO Auto-generated method stub
		TrainCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (TrainCreditNote) session.get(TrainCreditNote.class,creditNote.getId());
			updateObj.setCancellationFees(creditNote.getCancellationFees());
			updateObj.setConvenienceFees(creditNote.getConvenienceFees());
			updateObj.setManagementFees(creditNote.getManagementFees());
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public VisaCreditNote updateVisaCreditNoteData(VisaCreditNote creditNote) {
		// TODO Auto-generated method stub
		VisaCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (VisaCreditNote) session.get(VisaCreditNote.class,creditNote.getId());
			updateObj.setCancellationFees(creditNote.getCancellationFees());
			updateObj.setConvenienceFees(creditNote.getConvenienceFees());
			updateObj.setManagementFees(creditNote.getManagementFees());
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public InsuranceCreditNote updateInsuranceCreditNoteData(InsuranceCreditNote creditNote) {
		// TODO Auto-generated method stub
		InsuranceCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (InsuranceCreditNote) session.get(InsuranceCreditNote.class,creditNote.getId());
			updateObj.setCancellationFees(creditNote.getCancellationFees());
			updateObj.setConvenienceFees(creditNote.getConvenienceFees());
			updateObj.setManagementFees(creditNote.getManagementFees());
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}
	
	public MiscellaneousCreditNote updateMiscellaneousCreditNoteData(MiscellaneousCreditNote creditNote) {
		// TODO Auto-generated method stub
		MiscellaneousCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (MiscellaneousCreditNote) session.get(MiscellaneousCreditNote.class,creditNote.getId());
			updateObj.setCancellationFees(creditNote.getCancellationFees());
			updateObj.setConvenienceFees(creditNote.getConvenienceFees());
			updateObj.setManagementFees(creditNote.getManagementFees());
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}


	public CreditNote updateCreditNoteInvoiceNumber(int id) {
		// TODO Auto-generated method stub
		CreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (CreditNote) session.get(CreditNote.class,id);
			updateObj.setCNINumber(RandomConfigurationNumber.generateCreditNoteInvoiceNumber(String.valueOf(id),"F"));
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public HotelCreditNote updateHotelCreditNoteInvoiceNumber(int id) {
		// TODO Auto-generated method stub
		HotelCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (HotelCreditNote) session.get(HotelCreditNote.class,id);
			updateObj.setCNINumber(RandomConfigurationNumber.generateCreditNoteInvoiceNumber(String.valueOf(id),"H"));
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public CarCreditNote updateCarCreditNoteInvoiceNumber(int id) {
		// TODO Auto-generated method stub
		CarCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (CarCreditNote) session.get(CarCreditNote.class,id);
			updateObj.setCNINumber(RandomConfigurationNumber.generateCreditNoteInvoiceNumber(String.valueOf(id),"C"));
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public BusCreditNote updateBusCreditNoteInvoiceNumber(int id) {
		// TODO Auto-generated method stub
		BusCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (BusCreditNote) session.get(BusCreditNote.class,id);
			updateObj.setCNINumber(RandomConfigurationNumber.generateCreditNoteInvoiceNumber(String.valueOf(id),"B"));
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public TrainCreditNote updateTrainCreditNoteInvoiceNumber(int id) {
		// TODO Auto-generated method stub
		TrainCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (TrainCreditNote) session.get(TrainCreditNote.class,id);
			updateObj.setCNINumber(RandomConfigurationNumber.generateCreditNoteInvoiceNumber(String.valueOf(id),"T"));
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public VisaCreditNote updateVisaCreditNoteInvoiceNumber(int id) {
		// TODO Auto-generated method stub
		VisaCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (VisaCreditNote) session.get(VisaCreditNote.class,id);
			updateObj.setCNINumber(RandomConfigurationNumber.generateCreditNoteInvoiceNumber(String.valueOf(id),"V"));
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public InsuranceCreditNote updateInsuranceCreditNoteInvoiceNumber(int id) {
		// TODO Auto-generated method stub
		InsuranceCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (InsuranceCreditNote) session.get(InsuranceCreditNote.class,id);
			updateObj.setCNINumber(RandomConfigurationNumber.generateCreditNoteInvoiceNumber(String.valueOf(id),"I"));
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public MiscellaneousCreditNote updateMiscellaneousCreditNoteInvoiceNumber(int id) {
		// TODO Auto-generated method stub
		MiscellaneousCreditNote updateObj=null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updateObj =  (MiscellaneousCreditNote) session.get(MiscellaneousCreditNote.class,id);
			updateObj.setCNINumber(RandomConfigurationNumber.generateCreditNoteInvoiceNumber(String.valueOf(id),"I"));
			session.saveOrUpdate(updateObj);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return updateObj;
	}

	public FlightOrderRowCancellation getFlightOrderRowCancellation(String orderId) {
		// TODO Auto-generated method stub
		FlightOrderRowCancellation hotelOrderRowCancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRowCancellation hrc where hrc.orderId=:orderId";
			Query query = session.createQuery(sql);
			query.setParameter("orderId", orderId);
			hotelOrderRowCancellation = (FlightOrderRowCancellation) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRowCancellation;
	}
	public HotelOrderRowCancellation getHotelOrderRowCancellation(String orderId) {
		// TODO Auto-generated method stub
		HotelOrderRowCancellation hotelOrderRowCancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRowCancellation hrc where hrc.orderId=:orderId";
			Query query = session.createQuery(sql);
			query.setParameter("orderId", orderId);
			hotelOrderRowCancellation = (HotelOrderRowCancellation) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRowCancellation;
	}
	public CarOrderRowCancellation getCarOrderRowCancellation(String orderId) {
		// TODO Auto-generated method stub
		CarOrderRowCancellation hotelOrderRowCancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarOrderRowCancellation hrc where hrc.orderId=:orderId";
			Query query = session.createQuery(sql);
			query.setParameter("orderId", orderId);
			hotelOrderRowCancellation = (CarOrderRowCancellation) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRowCancellation;
	}

	public BusOrderRowCancellation getBusOrderRowCancellation(String orderId) {
		// TODO Auto-generated method stub
		BusOrderRowCancellation hotelOrderRowCancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusOrderRowCancellation hrc where hrc.orderId=:orderId";
			Query query = session.createQuery(sql);
			query.setParameter("orderId", orderId);
			hotelOrderRowCancellation = (BusOrderRowCancellation) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRowCancellation;
	}

	public TrainOrderRowCancellation getTrainOrderRowCancellation(String orderId) {
		// TODO Auto-generated method stub
		TrainOrderRowCancellation hotelOrderRowCancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainOrderRowCancellation hrc where hrc.orderId=:orderId";
			Query query = session.createQuery(sql);
			query.setParameter("orderId", orderId);
			hotelOrderRowCancellation = (TrainOrderRowCancellation) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRowCancellation;
	}

	public VisaOrderRowCancellation getVisaOrderRowCancellation(String orderId) {
		// TODO Auto-generated method stub
		VisaOrderRowCancellation hotelOrderRowCancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaOrderRowCancellation hrc where hrc.orderId=:orderId";
			Query query = session.createQuery(sql);
			query.setParameter("orderId", orderId);
			hotelOrderRowCancellation = (VisaOrderRowCancellation) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRowCancellation;
	}

	public  InsuranceOrderRowCancellation getInsuranceOrderRowCancellation(String orderId) {
		// TODO Auto-generated method stub
		InsuranceOrderRowCancellation hotelOrderRowCancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceOrderRowCancellation hrc where hrc.orderId=:orderId";
			Query query = session.createQuery(sql);
			query.setParameter("orderId", orderId);
			hotelOrderRowCancellation = (InsuranceOrderRowCancellation) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRowCancellation;
	}




}
