package com.admin.flight.fin.sheet.Dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.admin.flight.fin.sheet.model.FlightQuotationHistory;
import com.admin.flight.fin.sheet.model.FlightTravelRequest;
import com.admin.flight.fin.sheet.model.FlightTravelRequestQuotation;
import com.admin.hotel.fin.sheet.Dao.HotelTravelRequestDao;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class FlightTravelRequestDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightTravelRequestDao.class);
	public FlightTravelRequest insertFlightQuotationRowDetails(FlightTravelRequest flightTravelRequest){
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(flightTravelRequest);
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
		return flightTravelRequest;
	}

	public List<FlightTravelRequest>  loadFlightQuotationRowList(User user){
		// TODO Auto-generated method stub

		Session session= null;
		List<FlightTravelRequest>  list= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightTravelRequest.class);
			criteria.add(Restrictions.eq("companyId", user.getCompanyid()));
		  list= criteria.list();
		} catch (Exception e) {
			e.printStackTrace();

		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();

			}
		}
		return list;

	}

	public boolean insertFlightQuotationList(List<FlightTravelRequestQuotation> flightTravelRequestQuotation,FlightTravelRequest flightTravelRequest, int userId, int companyId){
		// TODO Auto-generated method stub
		boolean isInserted=false;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(flightTravelRequestQuotation!=null && flightTravelRequestQuotation.size()>0){
				for(FlightTravelRequestQuotation flightQuotation:flightTravelRequestQuotation){
					flightQuotation.setCreatedAt(new Timestamp(new Date().getTime()));
					//flightQuotation.setTravelRequestDate(DateConversion.StringToDate(flightQuotation.getTransTravelRequestDate()));
					 HotelFlightBookingStatus hotetFlightBookingStatus=new HotelFlightBookingStatus();
						hotetFlightBookingStatus.setCreated(TravelRequestStatusEnum.CREATED.getValue());
						flightQuotation.setHotetFlightBookingStatus(hotetFlightBookingStatus);
					flightQuotation.setDepartureDate(DateConversion.StringToDate(flightQuotation.getTransDepartureDate()));
					flightQuotation.setUserId(userId);
					flightQuotation.setCompanyId(companyId);
					if(flightQuotation.getTransArrivalDate()!=null){
						flightQuotation.setArrivalDate(DateConversion.StringToDate(flightQuotation.getTransArrivalDate()));
					}
					flightQuotation.setFlightTravelRequest(flightTravelRequest);
					session.save(flightQuotation);
				}
			}

			transaction.commit();
			isInserted=true;
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
				isInserted=false;
			}
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return isInserted;

	}

	public User getUserNameByUserId(int userId){
		User user=null;
		Session session= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(User.class);
			criteria.add(Restrictions.eq("id", userId));
			user= (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return user;
	}

	public List<FlightTravelRequestQuotation> getFlightRequestTravelQuotationList(Long flightQuotationRequestId) {
		List<FlightTravelRequestQuotation> Newlist= new ArrayList<>();
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightTravelRequestQuotation com where com.flightTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", flightQuotationRequestId);
			List<FlightTravelRequestQuotation> list= query.list();
			for(FlightTravelRequestQuotation quotation:list){
				quotation.setTotalAmount(quotation.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
				Newlist.add(quotation);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return Newlist;
	}
	public long  getFlightOrderRowIdFromFlightTravelRequestQuotation(Long hotelQuotationRequestId) {
		// TODO Auto-generated method stub
		FlightTravelRequestQuotation  flightQuotation=  null;
		long id = 0;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightTravelRequestQuotation com where com.flightTravelRequest.id=:id and com.isBooked=:isBooked";
			Query query = session.createQuery(sql);
			query.setParameter("id", hotelQuotationRequestId);
			query.setParameter("isBooked", true);
			flightQuotation= (FlightTravelRequestQuotation) query.list();
			if(flightQuotation!=null){
				id=flightQuotation.getOrderRowId();	
			}


		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return id;
	}

	public FlightTravelRequestQuotation flightRequestQuotationUpdate(FlightTravelRequestQuotation flightTravelRequestQuotation) {
		Session session= null;
		Transaction transaction=null;
		FlightTravelRequestQuotation  flightTravelRequestQuotationUpdate=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			flightTravelRequestQuotationUpdate=(FlightTravelRequestQuotation) session.get(FlightTravelRequestQuotation.class,flightTravelRequestQuotation.getId());
			flightTravelRequestQuotationUpdate.setAirline(flightTravelRequestQuotation.getAirline());
			flightTravelRequestQuotationUpdate.setTotalAmount(flightTravelRequestQuotation.getTotalAmount());
			flightTravelRequestQuotationUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(flightTravelRequestQuotationUpdate);
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
		return flightTravelRequestQuotationUpdate;
	}

	public FlightTravelRequestQuotation getFlightTravelRequestQuotationDetails(Long flightQuotationRequestId) {
		// TODO Auto-generated method stub
		FlightTravelRequestQuotation  flightQuotation=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("id", flightQuotationRequestId));
			flightQuotation= (FlightTravelRequestQuotation)criteria.uniqueResult();
		 
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return flightQuotation;

	}


	public boolean updateFlightQuotationList(List<FlightTravelRequestQuotation> flightTravelRequestQuotationList) {
		// TODO Auto-generated method stub
		boolean isUpdated=false;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(flightTravelRequestQuotationList!=null && flightTravelRequestQuotationList.size()>0){
				for(FlightTravelRequestQuotation quotationObj:flightTravelRequestQuotationList){
					FlightTravelRequestQuotation newQuotationObj =(FlightTravelRequestQuotation)session.get(FlightTravelRequestQuotation.class, quotationObj.getId()); 
					newQuotationObj.setAdditionalData(quotationObj.getAdditionalData());
					newQuotationObj.setPassengerCount(quotationObj.getPassengerCount());
					newQuotationObj.setAirline(quotationObj.getAirline());
					newQuotationObj.setTotalAmount(quotationObj.getTotalAmount());
					newQuotationObj.setBookingClassPrefer(quotationObj.getBookingClassPrefer());
					//newQuotationObj.setTravelRequestDate(quotationObj.getTravelRequestDate());
					newQuotationObj.setTripType(quotationObj.getTripType());
					session.update(newQuotationObj);
				}
			}
			transaction.commit();
			isUpdated=true;
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
				isUpdated=false;
			}
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return isUpdated;
	}

	public FlightTravelRequest getFlightTravelRequestDetails(Long id) {
		// TODO Auto-generated method stub
		FlightTravelRequest  flightTravelRequest=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			flightTravelRequest= (FlightTravelRequest)criteria.uniqueResult();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return flightTravelRequest;
	}

	public FlightTravelRequest updateFlightTravelRequest(FlightTravelRequest flightTravelRequest) {
		// TODO Auto-generated method stub

		FlightTravelRequest newFlightTravelRequest=null;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			newFlightTravelRequest =(FlightTravelRequest)session.get(FlightTravelRequest.class, flightTravelRequest.getId()); 
			newFlightTravelRequest.setAirlinePrefer(flightTravelRequest.getAirlinePrefer());
			newFlightTravelRequest.setArrivalDate(flightTravelRequest.getArrivalDate());
			newFlightTravelRequest.setBookingClassPrefer(flightTravelRequest.getBookingClassPrefer());
			newFlightTravelRequest.setCompanyEntity(flightTravelRequest.getCompanyEntity());
			newFlightTravelRequest.setCostCenter(flightTravelRequest.getCostCenter());
			newFlightTravelRequest.setCurrency(flightTravelRequest.getCurrency());
			newFlightTravelRequest.setCustomerComments(flightTravelRequest.getCustomerComments());
			newFlightTravelRequest.setCustomerNo(flightTravelRequest.getCustomerNo());
			newFlightTravelRequest.setDepartureDate(flightTravelRequest.getDepartureDate());
			newFlightTravelRequest.setDestination(flightTravelRequest.getDestination());
			newFlightTravelRequest.setFirstName(flightTravelRequest.getFirstName());
			newFlightTravelRequest.setLastName(flightTravelRequest.getLastName());
			newFlightTravelRequest.setOrigin(flightTravelRequest.getOrigin());
			newFlightTravelRequest.setPassengerCount(flightTravelRequest.getPassengerCount());
			newFlightTravelRequest.setProjectName(flightTravelRequest.getProjectName());
			newFlightTravelRequest.setProjectSubTaskDetails(flightTravelRequest.getProjectSubTaskDetails());
			newFlightTravelRequest.setSource(flightTravelRequest.getSource());
			newFlightTravelRequest.setTitle(flightTravelRequest.getTitle());
			newFlightTravelRequest.setTravelRequestNumber(flightTravelRequest.getTravelRequestNumber());
			newFlightTravelRequest.setTripType(flightTravelRequest.getTripType());
			newFlightTravelRequest.setUpdated_by_userId(flightTravelRequest.getUpdated_by_userId());
			newFlightTravelRequest.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(newFlightTravelRequest); 
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

		return newFlightTravelRequest;
	}
	public FlightTravelRequestQuotation updateFlightRequestQuotationWithOrderId(FlightOrderRow flightOrderRow , Long flightRequestQuotationId) {
		Session session= null;
		Transaction transaction=null;
		FlightTravelRequestQuotation  flightTravelRequestQuotationUpdate=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			flightTravelRequestQuotationUpdate=(FlightTravelRequestQuotation) session.get(FlightTravelRequestQuotation.class,flightRequestQuotationId);
			flightTravelRequestQuotationUpdate.setBooked(true);
			flightTravelRequestQuotationUpdate.setOrderRowId(flightOrderRow.getId());
			flightTravelRequestQuotationUpdate.setHide(true);
			flightTravelRequestQuotationUpdate.setTotalAmount(flightOrderRow.getPrice());
			session.saveOrUpdate(flightTravelRequestQuotationUpdate);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return flightTravelRequestQuotationUpdate;

	}

	public   boolean updateFlightTravelRequestQuotationHide(Long flightRequestQuotationId) {
		Session session= null;
		Transaction transaction=null;
		boolean isHide=false;
		 
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			FlightTravelRequestQuotation  flightTravelRequestQuotationUpdate=(FlightTravelRequestQuotation) session.get(FlightTravelRequestQuotation.class,flightRequestQuotationId);
			flightTravelRequestQuotationUpdate.setHide(true);
			session.saveOrUpdate(flightTravelRequestQuotationUpdate);
			transaction.commit();
			isHide=true;
		 

		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			 
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return isHide;

	}
	public List<FlightTravelRequestQuotation> getFlightTravelRequestQuotationBookedList(Long hotelQuotationRequestId) {
		// TODO Auto-generated method stub
		List<FlightTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("isBooked", true));
			criteria.createCriteria("flightTravelRequest").add(Restrictions.eq("id", hotelQuotationRequestId));
			Newlist= criteria.list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return Newlist;
	}
 
	public  FlightQuotationHistory getFlightQuotationHistory(Long id) {
		// TODO Auto-generated method stub
		Session session= null;
		FlightQuotationHistory flightQuotationHistory=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightQuotationHistory.class);
			criteria.add(Restrictions.eq("id", id));
			flightQuotationHistory=(FlightQuotationHistory) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return flightQuotationHistory;
		 
	}

	public FlightTravelRequestQuotation getFlightQuotationDetails(Long id) {
			Session  session=null;
			 FlightTravelRequestQuotation  flightQuotation=null;
			try
			{
				session = HibernateUtil.getSessionFactory().openSession();
				Criteria criteria=session.createCriteria( FlightTravelRequestQuotation.class);
				criteria.add(Restrictions.eq("orderRowId",id));
				flightQuotation=(FlightTravelRequestQuotation) criteria.uniqueResult();
			}
			catch (HibernateException e) {
				logger.error("HibernateException---------"+e.getMessage());
			}finally {
				if(session!=null && session.isOpen())
					session.close(); 
			}
			return flightQuotation;

		}
	 
}

