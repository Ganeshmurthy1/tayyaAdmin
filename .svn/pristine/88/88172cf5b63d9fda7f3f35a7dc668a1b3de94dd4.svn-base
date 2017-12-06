package com.admin.common.expense.dao;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.admin.common.expense.model.BusExpense;
import com.admin.common.expense.model.CarExpense;
import com.admin.common.expense.model.TripExepense;
import com.admin.common.expense.model.FlightExpense;
import com.admin.common.expense.model.HotelExpense;
import com.admin.common.expense.model.InsuranceExpense;
import com.admin.common.expense.model.LaborExpense;
import com.admin.common.expense.model.MealExpense;
import com.admin.common.expense.model.MiscellaneousExpense;
import com.admin.common.expense.model.TrainExpense;
import com.admin.common.expense.model.VisaExpense;
import com.lintas.config.HibernateUtil;

public class TripExpenseDao {
	public static final Logger logger=Logger.getLogger(TripExpenseDao.class);
	public TripExepense insertExpense(TripExepense tripExepense) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			tripExepense.setCreatedAt(new Timestamp(new Date().getTime()));
			session.save(tripExepense);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return tripExepense;
	}
	public boolean deleteflightexpensebyid(Long Id) {
		Session session=null;
		Transaction transaction=null;
		boolean isDelete=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from FlightExpense fe where fe.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", Id);
			int result = query.executeUpdate();

			if (result> 0) {
				isDelete = true;
			}
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return isDelete;

		}finally {
			session.close(); 
		}
		return isDelete;

		// TODO Auto-generated method stub

	}


	public FlightExpense insertFlightExpense(FlightExpense flightExpense) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			flightExpense.setCreatedAt(new Timestamp(new Date().getTime()));
			session.save(flightExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return flightExpense;
	}


	public HotelExpense insertHotelExpense(HotelExpense hotelExpense) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(hotelExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return hotelExpense;
	}

	public CarExpense insertCarExpense(CarExpense carExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(carExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return carExpense;
	}


	public TrainExpense insertTrainExpense(TrainExpense trainExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(trainExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return trainExpense;
	}


	public BusExpense insertBusExpense(BusExpense busExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(busExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return busExpense;
	}

	public MiscellaneousExpense insertMiscellaneousExpense(MiscellaneousExpense miscellaneousExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(miscellaneousExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return miscellaneousExpense;
	}

	public MealExpense insertMealExpense(MealExpense mealExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(mealExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return mealExpense;
	}
	public LaborExpense insertLaborExpense(LaborExpense laborExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(laborExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return laborExpense;
	}


	public TripExepense getExpense(Long tripId) {
		Session session = null;
		TripExepense tripExepense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr =session.createCriteria(TripExepense.class);
			cr.add(Restrictions.eq("id", tripId));
			tripExepense=(TripExepense) cr.uniqueResult();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return tripExepense;
	}

	public List<TripExepense> feltchExpenseList() {
		Session session = null;
		List<TripExepense> expenseList=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr =session.createCriteria(TripExepense.class);
			expenseList=cr.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return expenseList;
	}

	/*public static void main(String[] args) {

		List<TripExepense> expenseList=new TripExpenseDao().feltchExpenseList();
		for(TripExepense ex:expenseList){
			logger.info(ex.getFlightExpenseList().size());

		}
	 */



	public void deleteFlightExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from FlightExpense flight  where flight.id=:id";
		//String sql = "";
		try {
			logger.info("id----"+id);
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}

	public void deleteHotelExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from HotelExpense hotel  where hotel.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}

	public void deleteCarExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from CarExpense car  where car.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}

	public void deleteTrainExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from TrainExpense train  where train.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	public void deleteBusExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from BusExpense bus  where bus.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	public void deleteMealExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from MealExpense meal  where meal.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}

	public void deleteLaborExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from LaborExpense labor  where labor.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}

	public void deleteMiscellaneousExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from MiscellaneousExpense misl  where misl.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}



	public void editFlightExpense(FlightExpense flightExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.update(flightExpense);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	public void editHotelExpense(HotelExpense hotelExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			//HotelExpense hotelExpenseToUpdate=(HotelExpense) session.get(HotelExpense.class, hotelExpense.getId());
			session.update(hotelExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	
	public void editCarExpense(CarExpense carExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			//CarExpense carExpenseToUpdate=(CarExpense) session.get(CarExpense.class, carExpense.getId());
			session.update(carExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}

	public void editTrainExpense(TrainExpense trainExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			//TrainExpense trainExpenseToUpdate=(TrainExpense) session.get(TrainExpense.class, trainExpense.getId());
			session.update(trainExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	public void editBusExpense(BusExpense busExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			//BusExpense busExpenseToUpdate=(BusExpense) session.get(BusExpense.class, busExpense.getId());
			session.update(busExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	public void editMealExpense(MealExpense mealExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			//MealExpense mealExpenseToUpdate=(MealExpense) session.get(MealExpense.class, mealExpense.getId());
			session.update(mealExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	public void editLaborExpense(LaborExpense laborExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			//LaborExpense laborExpenseToUpdate=(LaborExpense) session.get(LaborExpense.class, laborExpense.getId());
			session.update(laborExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	public void editMiscellaneousExpense(MiscellaneousExpense miscellaneousExpense ) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			//MiscellaneousExpense miscellaneousExpenseToUpdate=(MiscellaneousExpense) session.get(MiscellaneousExpense.class, miscellaneousExpense.getId());
			session.update(miscellaneousExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}
	

	public FlightExpense showFlightExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		FlightExpense flightExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			flightExpense=(FlightExpense) session.get(FlightExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return flightExpense;
	}
	
	
	public HotelExpense showHotelExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		HotelExpense hotelExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			hotelExpense=(HotelExpense) session.get(HotelExpense.class, id);
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return hotelExpense;
	}
	
	public CarExpense showCarExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		CarExpense carExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			carExpense=(CarExpense) session.get(CarExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return carExpense;
	}
	
	public TrainExpense showTrainExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		TrainExpense trainExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			trainExpense=(TrainExpense) session.get(TrainExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return trainExpense;
	}
	
	public BusExpense showBusExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		BusExpense busExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			busExpense=(BusExpense) session.get(BusExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return busExpense;
	}
	
	public MealExpense showMealExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		MealExpense mealExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			mealExpense=(MealExpense) session.get(MealExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return mealExpense;
	}
	
	public LaborExpense showLaborExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		LaborExpense laborExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			laborExpense=(LaborExpense) session.get(LaborExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return laborExpense;
	}
	
	public MiscellaneousExpense showMislaniousExpense(Long id ) {
		Session session = null;
		Transaction tx=null;
		MiscellaneousExpense mislaniousExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			mislaniousExpense=(MiscellaneousExpense) session.get(MiscellaneousExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return mislaniousExpense;
	}
	
	
	public VisaExpense insertVisaExpense(VisaExpense visaExpense) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(visaExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return visaExpense;
	}
	
	
	public InsuranceExpense insertInsuranceExpense(InsuranceExpense insuranceExpense) {
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(insuranceExpense);
			tx.commit();
			// tripRequest	=(TripRequest) session.get(TripRequest.class, tripid);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return insuranceExpense;
	}
	public void deleteVisaExpense(long id) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from VisaExpense visa  where visa.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		// TODO Auto-generated method stub
		
	}
	public void deleteInsuranceExpense(long id) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx=null;
		String hql="delete from InsuranceExpense insurense  where insurense.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();
			
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		
	}
	public InsuranceExpense showInsurenseExpense(long id) {
		Session session = null;
		Transaction tx=null;
		InsuranceExpense insuranceExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			insuranceExpense=(InsuranceExpense) session.get(InsuranceExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return insuranceExpense;
	}
	public VisaExpense showVisaExpense(long id) {
		Session session = null;
		Transaction tx=null;
		VisaExpense visaExpense=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			visaExpense=(VisaExpense) session.get(VisaExpense.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return visaExpense;
	}
	public void editVisaExpense(VisaExpense visaExpense) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.update(visaExpense);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		
	}
	
	public void editInsuranseExpense(InsuranceExpense insuranceExpense) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.update(insuranceExpense);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		
		
	}
}
