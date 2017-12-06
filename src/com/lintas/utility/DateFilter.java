package com.lintas.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.hotel.model.HotelOrderCancellationPolicy;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
 
 

public class DateFilter extends Thread{

	/*this method for  dates between one date to another date  */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DateFilter.class);

	public static List<String> getDatelist(String fromdate,String todate){
		List<String> datelist=new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		try {

			if(fromdate!=null && todate!=null){
				startDate = (Date)formatter.parse(fromdate);
				Date  endDate = (Date)formatter.parse(todate);
				long interval = 24*1000 * 60 * 60; // 1 hour in millis
				long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
				long curTime = startDate.getTime();
				while (curTime <= endTime) {
					dates.add(new Date(curTime));
					curTime += interval;
				}
				for(int i=0;i<dates.size();i++){
					Date lDate =(Date)dates.get(i);
					String ds = formatter.format(lDate);    
					//logger.info("Date is ..." + ds);
					datelist.add(ds);
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return datelist;
	}


	public static List<String> getPrevious30Days(int preDay,int curDay){
		return getDatelist(getPreviousDate(preDay),getPreviousDate(curDay));
	}

	public static String getPreviousDate(int day) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);    
		return dateFormat.format(cal.getTime());
	}



	public static List<HotelOrderRow> getLastBookingDateForHotel(int userId){
		logger.info("flight. userid"+userId);
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(tomorrow);
		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date minDate = null;
		try {
			minDate = originalFormat.parse(format);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));
		Session sess = null;
		List<HotelOrderRow> lastdateRowList = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sess.createCriteria(HotelOrderRow.class);
			/* Create object of Conjunction */
			Conjunction objConjunction = Restrictions.conjunction();
			/* Add multiple condition separated by AND clause within brackets. */
			objConjunction.add(Restrictions.eq("statusAction", "confirmed"));
			objConjunction.add(Restrictions.eq("userId", String.valueOf(userId)));
			objConjunction.add(Restrictions.ge("checkInDate", minDate));
			objConjunction.add(Restrictions.lt("checkInDate", maxDate));
			criteria.add(objConjunction);
			lastdateRowList= criteria.list();
			logger.info("------------"+lastdateRowList.size());
		} catch (HibernateException e) {			
			logger.info("HibernateException"+e.getMessage());
		} catch (Exception e) {			
			logger.info("Exception"+ e);
		}
		finally {
			if(sess != null && sess.isOpen())
				sess.close();
		}
		return lastdateRowList;
	}

	public static  List<FlightOrderRow>  getLastBookingDateForFlight(int userid)
			throws HibernateException {
	logger.info("flight. userid"+userid);
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_YEAR, 1); // <--
		Date tomorrow = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = formatter.format(tomorrow);
		logger.info("lats date formate "+format);
		
		
		
		Session sess = null;
		List<FlightOrderRow> lastdateRowList = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sess.createCriteria(FlightOrderRow.class);
			/* Create object of Conjunction */
			Conjunction objConjunction = Restrictions.conjunction();
			/* Add multiple condition separated by AND clause within brackets. */
			objConjunction.add(Restrictions.eq("statusAction", "confirmed"));
			objConjunction.add(Restrictions.eq("userId", String.valueOf(userid)));
			objConjunction.add(Restrictions.like("lastTicketingDate", format));
			 
			criteria.add(objConjunction);
			lastdateRowList= criteria.list();
			logger.info("lastdateRowList-----"+lastdateRowList.size());
		} catch (HibernateException e) {			
			logger.info("HibernateException"+e);
		} catch (Exception e) {			
			logger.info("Exception"+e);
		}
		finally {
			if(sess != null && sess.isOpen())
				sess.close();
		}
		return lastdateRowList;
	}
	 
	public static void subquery(){
		//String sqlCompany = "select * from company where companyid in (select companyid from user where id in("+companyidquerysection+"))";
		Session sess = null;
		List<Integer> userIds=new ArrayList<>();
		try{
			userIds.add(2);
			userIds.add(3);
			userIds.add(4);
			sess = HibernateUtil.getSessionFactory().openSession();
			// passing list ids
			/*DetachedCriteria detachAssignmentPrd=DetachedCriteria.forClass(User.class, "assignment");
			detachAssignmentPrd.add(Restrictions.in("id", userIds)).setProjection(Projections.property("assignment.Companyid"));
		Criteria criteria=sess.createCriteria(Company.class,"position");
		criteria.add(Subqueries.propertyIn("position.companyid", detachAssignmentPrd));
		List list=criteria.list();*/
			DetachedCriteria detachAssignmentPrd=DetachedCriteria.forClass(User.class, "assignment");
			detachAssignmentPrd.add(Restrictions.eq("id",4)).setProjection(Projections.property("assignment.Companyid"));
		Criteria criteria=sess.createCriteria(Company.class,"position");
		criteria.add(Subqueries.propertyIn("position.companyid", detachAssignmentPrd));
		Company list=(Company) criteria.uniqueResult();
		logger.info("------companySize----------"+list.getCompany_userid());
		
		}
		catch (HibernateException e) {			
			logger.info("HibernateException"+e);
		} catch (Exception e) {			
			logger.info("Exception"+e);
		}
		finally {
			if(sess != null && sess.isOpen())
				sess.close();
		}
	}
	public static List<FlightOrderTripDetail> flightTrips(Long id)
			throws HibernateException {
		// TODO Auto-generated method stub
		List<FlightOrderTripDetail> list =null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightOrderTripDetail.class);
			criteria.add(Restrictions.eq("flightOrderRow.id",id));
			 list = criteria.list();
			 logger.info("FlightOrderTripDetail  size()---"+list.size());
			
		}catch (HibernateException e) {
			 throw e; 
		}finally {
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}
	public static List<HotelOrderCancellationPolicy> getCancelPolicies(Long id)
			throws HibernateException {
		List<HotelOrderCancellationPolicy> list = new ArrayList<HotelOrderCancellationPolicy>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			//tx = session.beginTransaction();
			//String sql = "select  * from hotel_order_cancellation_policy where order_id ='" + hotelOrderRow.getId() + "'";
			Criteria criteria=session.createCriteria(HotelOrderCancellationPolicy.class);
			criteria.add(Restrictions.eq("hotelOrderRow.id", id));
			 list = criteria.list();
			 logger.info("list---------------"+list.size());
			 
		}catch (HibernateException e) {
			list = new ArrayList<HotelOrderCancellationPolicy>();
			//
		}finally {
			session.close(); 
			
		}
		return list;
	}

	public static List<HotelOrderGuest> guesInformationDeatails(
			List<Long> roomIds) throws HibernateException {
		List<HotelOrderGuest> hotelOrderGuestlist = new ArrayList<HotelOrderGuest>();
		
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderGuest.class);
		 	 criteria.add(Restrictions.in("roomInfo.id", roomIds));
			 hotelOrderGuestlist= criteria.list();
			 logger.info("----hotelOrderGuestlist size"+hotelOrderGuestlist.size());
			
		}catch (HibernateException e) {

			throw e; 
		}finally {
			session.close(); 
		}
		return hotelOrderGuestlist;
	}
	 
	public static List<Email> getPendingMails() throws HibernateException {
		List<Email> list  = new ArrayList<Email>();
		List<Email> listEmails  = new ArrayList<Email>();
		Session sess = null;
		Transaction tx = null;
		List<Email> emailVerificationListUser = new ArrayList<Email>();	
		List<Email> emailVerificationListCompany = new ArrayList<Email>();	
	logger.info("########################## DB Email retriving call");
		try{
			List<Integer> statusList=new ArrayList<>();
			statusList.add(Email.STATUS_PENDING);
			statusList.add(Email.STATUS_SENT_ERROR_SERVER_ISSUE);
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=sess.createCriteria(Email.class);
			 Conjunction conjuction=Restrictions.conjunction();
			 conjuction.add(Restrictions.in("mailStatus", statusList));
			 conjuction.add(Restrictions.lt("attemptedCount",5));
			 criteria.add(conjuction);
			 criteria.setMaxResults(20);
			 list = criteria.list();
			 logger.info("list------------------"+list.size());
			 
		}catch (HibernateException e) {
			 
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}
			
		}
		return listEmails;
	}

	
	
	
	
	
	
	
	
	
	
	
		/*public static void main(String[] args) {
			String sample="Bastos,Brazil(BR)/22";
			String newUrl=sample.substring(0,sample.lastIndexOf('/'));
			logger.info(newUrl);
		 }
		*/
	 

}
