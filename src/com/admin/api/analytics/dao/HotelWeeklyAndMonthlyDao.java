package com.admin.api.analytics.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;

public class HotelWeeklyAndMonthlyDao {

	public static final Logger logger = Logger.getLogger(HotelWeeklyAndMonthlyDao.class);
	
	public Map<String, Integer> getHotelWeeklyOrMonthlyReport(User userSessionObj, Company companySessionObj, List<String> userIdList,String reportType) {
		List<HotelOrderRow> hotelOrderRows=new ArrayList<>();
		Map<String, Integer> weekMap=new HashMap<>();
		SessionFactory sessionfactory;
		Session session = null;
		Criteria criteria = null;
		Integer i=1;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(HotelOrderRow.class);
			Conjunction conjunction = Restrictions.conjunction();

			if (userSessionObj != null && companySessionObj != null) {
				if (userSessionObj.getUserrole_id().isSuperuser()) {

					if (userIdList != null) {
						conjunction.add(Restrictions.in("userId", userIdList));
					}
				} else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
					if (companySessionObj.getCompanyRole().isCorporate()) {
						conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
					} else {
						if (userIdList != null)
							// conjunction.add(Restrictions.in("userId",
							// userIdList));
							conjunction.add(
									Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
					}
				}

				else if ((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports())
						&& !userSessionObj.getUserrole_id().isAdmin()) {
					if (userSessionObj.getCompanyid() == companySessionObj.getCompanyid()) {
						conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
					}
				}

				else {
					conjunction.add(Restrictions.eq("userId", userSessionObj.getId()));
				}
				conjunction.add(Restrictions.eq("statusAction", "Confirmed"));
				getWeeklyOrMonthlyRport(reportType, conjunction);
				criteria.add(conjunction);
				criteria.addOrder(Order.desc("createdAt"));
				hotelOrderRows = criteria.list();
				for (HotelOrderRow hotelOrderRow : hotelOrderRows) {
					String[] datenTime =hotelOrderRow.getCreatedAt().toString().split(" ");
					String date=datenTime[0];
					
					if (weekMap.containsKey(date)) {
						weekMap.put(date, i++);
					} else {
						weekMap.put(date, i);
					}
				}

				
				
				
			}
		}
		catch (HibernateException he) {
			// TODO: handle exception
		}
	
		finally {
			if (session != null && session.isOpen())
				session.close();
		}
		
		return weekMap;
	}
	
	
	public  Conjunction getWeeklyOrMonthlyRport(String reportType,Conjunction conjunction){
		Date date = new Date();
		Date start= null;
		Date week = null;
		Date monthend = null;
		
		try{
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			start = c.getTime();
			c.add(Calendar.DATE, -6);
			week = c.getTime();
			c.add(Calendar.DATE, -24);
			monthend = c.getTime();
		}
		catch (Exception e) {
			logger.error("message"+e.getMessage());
			e.printStackTrace();
		}
		  if (reportType.equalsIgnoreCase("W")) {
			conjunction.add(Restrictions.le("createdAt", start));
			conjunction.add(Restrictions.ge("createdAt", week));
		} else if (reportType.equalsIgnoreCase("M")) {
			conjunction.add(Restrictions.le("createdAt", start));
			conjunction.add(Restrictions.ge("createdAt", monthend));
		} 
		
		/*try{
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(HotelOrderRow.class);
			criteria.add(Restrictions.le("createdAt", start));
			criteria.add(Restrictions.ge("createdAt", week));
		    criteria.addOrder(Order.desc("createdAt"));
		     list = criteria.list();
		    
		}
		catch (HibernateException he) {
			// TODO: handle exception
		}
	
		finally {
			if (session != null && session.isOpen())
				session.close();
		}*/
		return conjunction;
	}
	
	public Long getHotelWeeklyOrMonthlyCancellationCount(User userSessionObj, Company companySessionObj, List<String> userIdList,String reportType) {
		List<HotelOrderRow> hotelOrderRows=new ArrayList<>();
		Map<String, Integer> weekMap=new HashMap<>();
		SessionFactory sessionfactory;
		Session session = null;
		Criteria criteria = null;
		Integer i=1;
		Long rowCount=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(HotelOrderRow.class);
			Conjunction conjunction = Restrictions.conjunction();

			if (userSessionObj != null && companySessionObj != null) {
				if (userSessionObj.getUserrole_id().isSuperuser()) {

					if (userIdList != null) {
						conjunction.add(Restrictions.in("userId", userIdList));
					}
				} else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
					if (companySessionObj.getCompanyRole().isCorporate()) {
						conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
					} else {
						if (userIdList != null)
							// conjunction.add(Restrictions.in("userId",
							// userIdList));
							conjunction.add(
									Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
					}
				}

				else if ((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports())
						&& !userSessionObj.getUserrole_id().isAdmin()) {
					if (userSessionObj.getCompanyid() == companySessionObj.getCompanyid()) {
						conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
					}
				}

				else {
					conjunction.add(Restrictions.eq("userId", userSessionObj.getId()));
				}
				conjunction.add(Restrictions.eq("statusAction", "Cancelled"));
				getWeeklyOrMonthlyRport(reportType, conjunction);
				criteria.add(conjunction);
				criteria.addOrder(Order.desc("createdAt"));
				 rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		}
		catch (HibernateException he) {
			// TODO: handle exception
		}
	
		finally {
			if (session != null && session.isOpen())
				session.close();
		}
		
		return rowCount;
	}
	
	 
}
