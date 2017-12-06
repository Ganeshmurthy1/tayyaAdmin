package com.common.dsr;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.flight.model.FlightFareAlertDetail;
import com.lintas.config.HibernateUtil;

public class FareAlertDao {
	public List<FlightFareAlertDetail> getFlightFareAlertDetail(String orderId) {
		Session  session=null;
		List<FlightFareAlertDetail> alertDetails=new ArrayList<FlightFareAlertDetail>();
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightFareAlertDetail.class);
			criteria.add(Restrictions.eq("orderid", orderId));
			alertDetails= criteria.list();
		}
		catch (HibernateException e) {
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return alertDetails;
	}
}
