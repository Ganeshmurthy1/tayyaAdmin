/**
 * 
 */
package com.hotel.analytics.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.Vo.DashBoardJsonVo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;

/**
 * @author Basha
 * created at: 10-08-2017
 *
 */
public class HotelAnalysisDao {

	public static final Logger logger = Logger.getLogger(HotelAnalysisDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;

	public DashBoardJsonVo getHotelOrdersCount(User userSessionObj, Company companySessionObj, List<String> userIdList, String type,
			String pstatus, String bstatus) {
		Long count = new Long(0);
		BigDecimal totalAmount=new BigDecimal(0);
		Criteria criteria = null;
		DashBoardJsonVo  dashBoardJsonVo=new DashBoardJsonVo();
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(HotelOrderRow.class);
			Conjunction conjunction = Restrictions.conjunction();
			Disjunction disjunction= Restrictions.disjunction();

			if (userSessionObj != null && companySessionObj != null) {
				if (userSessionObj.getUserrole_id().isSuperuser()) {

					if (userIdList != null) {
						conjunction.add(Restrictions.in("userId", userIdList));
					}
				}
				else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
					if (companySessionObj.getCompanyRole().isCorporate()) {
						conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
					} else {
						if (userIdList != null)
							conjunction.add(Restrictions.eq("companyId", String.valueOf(companySessionObj.getCompanyid())));
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

				if(type!=null)
				{
					getDayWeekMonthConjunction(type, conjunction);
				}

				if(pstatus!=null)
				{
					if (pstatus.equalsIgnoreCase("PF")) {
						disjunction.add(Restrictions.eq("paymentStatus", "Failed"));
						disjunction.add(Restrictions.eq("paymentStatus", "Booking Failed"));
						conjunction.add(disjunction);
					} 
					if (pstatus.equalsIgnoreCase("PS")) {
						disjunction.add(Restrictions.eq("paymentStatus", "Success"));
						disjunction.add(Restrictions.eq("paymentStatus", "Paid"));
						conjunction.add(disjunction);

					}
				}

				if(bstatus!=null)
				{
					if (bstatus.equalsIgnoreCase("HK")) {
						conjunction.add(Restrictions.eq("statusAction", "Confirmed"));
					} 
					if (bstatus.equalsIgnoreCase("HC")) {
						conjunction.add(Restrictions.eq("statusAction", "Cancelled"));
						conjunction.add(Restrictions.eq("isCreditNoteIssued", true));
					} 
					if (bstatus.equalsIgnoreCase("HF")) {
						disjunction.add(Restrictions.eq("statusAction", "Failed"));
						disjunction.add(Restrictions.eq("statusAction", "Booking Failed"));
						conjunction.add(disjunction);
					} 		
				}
			}

			criteria.add(conjunction);
			count= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			totalAmount=  (BigDecimal) criteria.setProjection(Projections.sum("finalPrice")).uniqueResult();//added by basha
			//System.out.println("count========================="+count);
			dashBoardJsonVo.setCount(count);//added by basha
			if(totalAmount!=null)
				dashBoardJsonVo.setTotalBookingAmount(totalAmount);//added by basha
			else
				dashBoardJsonVo.setTotalBookingAmount(new BigDecimal(0));//added by basha

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return dashBoardJsonVo;
	}
	public Conjunction getDayWeekMonthConjunction(String type,Conjunction conjunction){
		String startDate = "";
		String weekDate = "";
		String monthDate = "";
		try {
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Date start = c.getTime();
			c.add(Calendar.DATE, -6);
			Date end = c.getTime();
			c.add(Calendar.DATE, -30);
			Date monthend = c.getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			startDate = sdf.format(start);
			weekDate = sdf.format(end);
			monthDate = sdf.format(monthend);
		} catch (Exception e) {
		}
		if (type.equalsIgnoreCase("D")) {
			conjunction.add(Restrictions.eq("bookingDate", startDate));
		} else if (type.equalsIgnoreCase("W")) {
			conjunction.add(Restrictions.le("bookingDate", startDate));
			conjunction.add(Restrictions.ge("bookingDate", weekDate));
		} else if (type.equalsIgnoreCase("M")) {
			conjunction.add(Restrictions.le("bookingDate", startDate));
			conjunction.add(Restrictions.ge("bookingDate", monthDate));
		} 
		return conjunction;
	}
}
