/**
 * 
 */
package com.Visa.analytics.dao;

import java.math.BigDecimal;
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
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;

/**
 * @author Basha
 * created at: 10-08-2017
 *
 */
public class VisaAnalysisDao {

	public static final Logger logger = Logger.getLogger(VisaAnalysisDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;

	public DashBoardJsonVo getVisaOrdersCount(User userSessionObj, Company companySessionObj, List<String> userIdList, String type,
			String pstatus, String bstatus) {
		Long count = new Long(0);
		BigDecimal totalAmount=new BigDecimal(0);
		Criteria criteria = null;
		DashBoardJsonVo  dashBoardJsonVo=new DashBoardJsonVo();
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(VisaOrderRow.class);
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
						conjunction.add(Restrictions.eq("paymentStatus", "Failed"));
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
						conjunction.add(Restrictions.eq("creditNoteIssued", true));
					} 
					if (bstatus.equalsIgnoreCase("HF")) {
						conjunction.add(Restrictions.eq("statusAction", "Failed"));
					} 		
				}
			}

			criteria.add(conjunction);
			count= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			totalAmount=  (BigDecimal) criteria.setProjection(Projections.sum("totalAmount")).uniqueResult();//added by basha
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
		Date date = new Date();
		Date start= null;
		Date week = null;
		Date monthend = null;
		try {

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			start = c.getTime();
			c.add(Calendar.DATE, -6);
			week = c.getTime();
			c.add(Calendar.DATE, -24);
			monthend = c.getTime();
		} catch (Exception e) {
		}
		if (type.equalsIgnoreCase("D")) {
			conjunction.add(Restrictions.eq("bookingDate", start));
		} else if (type.equalsIgnoreCase("W")) {
			conjunction.add(Restrictions.le("bookingDate", start));
			conjunction.add(Restrictions.ge("bookingDate", week));
		} else if (type.equalsIgnoreCase("M")) {
			conjunction.add(Restrictions.le("bookingDate", start));
			conjunction.add(Restrictions.ge("bookingDate", monthend));
		} 
		return conjunction;
	}
}
