package com.lintas.admin.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;

public class FlightWeekandMonthOrderDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightWeekandMonthOrderDao.class);
	private CompanyDAO companyDAO = new CompanyDAO();

	public  List<FlightOrderRow> getFlightWeekOrders(User userSessionObj,Company companySessionObj){
		List<FlightOrderRow> list=null;
		String sql = "";

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					sql = "select * from flight_order_row where  bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
				}
				else {
					StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from flight_order_row where company_id ="+userSessionObj.getCompanyid()+" and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
					}
					else
					{
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -6 day) and CURDATE()";
					}
				}
			}

			//	logger.info("sql week" + sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FlightOrderRow.class);
			list = query.list();

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return list;
	}
	//select * from flight_order_row where bookingDate=DATE_ADD(CURDATE(), INTERVAL -6 DAY);


	public  List<Long> getFlightDayofWeekOrders(User userSessionObj,Company companySessionObj){

		ArrayList<Long> dailylist = new ArrayList<Long>();


		String sqlforday1 = "";
		String sqlforday2 = "";
		String sqlforday3 = "";
		String sqlforday4 = "";
		String sqlforday5 = "";
		String sqlforday6 = "";
		String sqlforday7 = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					sqlforday1 = "select count(count(*)) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
					sqlforday2 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
					sqlforday3 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
					sqlforday4 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
					sqlforday5 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
					sqlforday6 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
					sqlforday7 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sqlforday1 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
						sqlforday2= "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
						sqlforday3 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
						sqlforday4 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
						sqlforday5 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
						sqlforday6 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
						sqlforday7= "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";					}
					else
					{

						StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);	
						sqlforday1 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
						sqlforday2= "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
						sqlforday3 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
						sqlforday4 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
						sqlforday5 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
						sqlforday6 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
						sqlforday7= "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sqlforday1 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
						sqlforday2= "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
						sqlforday3 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
						sqlforday4 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
						sqlforday5 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
						sqlforday6 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
						sqlforday7= "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
					}
				}
				else{
					//sqlforday1 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
					sqlforday1 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
					sqlforday2= "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
					sqlforday3 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
					sqlforday4 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
					sqlforday5 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
					sqlforday6 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
					sqlforday7= "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
				}
			}

			SQLQuery queryforday1 = session.createSQLQuery(sqlforday1);
			queryforday1.addEntity(FlightOrderRow.class);
			Long  listforday1 =(Long)queryforday1.uniqueResult();

			SQLQuery queryforday2 = session.createSQLQuery(sqlforday2);
			queryforday2.addEntity(FlightOrderRow.class);
			Long  listforday2 =(Long)queryforday2.uniqueResult();
			

			SQLQuery queryforday3 = session.createSQLQuery(sqlforday3);
			queryforday3.addEntity(FlightOrderRow.class);
			Long  listforday3 =(Long)queryforday3.uniqueResult();

			SQLQuery queryforday4 = session.createSQLQuery(sqlforday4);
			queryforday4.addEntity(FlightOrderRow.class);
			Long  listforday4 =(Long)queryforday4.uniqueResult();

			SQLQuery queryforday5 = session.createSQLQuery(sqlforday5);
			queryforday5.addEntity(FlightOrderRow.class);
			Long  listforday5 =(Long)queryforday5.uniqueResult();

			SQLQuery queryforday6 = session.createSQLQuery(sqlforday6);
			queryforday6.addEntity(FlightOrderRow.class);
			Long  listforday6 =(Long)queryforday6.uniqueResult();

			SQLQuery queryforday7 = session.createSQLQuery(sqlforday7);
			queryforday7.addEntity(FlightOrderRow.class);
			Long  listforday7 =(Long)queryforday7.uniqueResult();

			dailylist.add(listforday1);
			dailylist.add(listforday2);
			dailylist.add(listforday3);
			dailylist.add(listforday4);
			dailylist.add(listforday5);
			dailylist.add(listforday6);
			dailylist.add(listforday7);

		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return dailylist;
	}

	public  List<Long> getFlightWeekofMonthOrders(User userSessionObj,Company companySessionObj){

		ArrayList<Long> dailylist = new ArrayList<Long>();


		String sqlforweek1 = "";
		String sqlforweek2 = "";
		String sqlforweek3 = "";
		String sqlforweek4 = "";

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					sqlforweek1 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
					sqlforweek2 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
					sqlforweek3 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
					sqlforweek4 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and  bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sqlforweek1 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
						sqlforweek2= "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
						sqlforweek3 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
						sqlforweek4 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
					}
					else
					{
						StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);	
						sqlforweek1 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
						sqlforweek2= "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
						sqlforweek3 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
						sqlforweek4 = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sqlforweek1 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
						sqlforweek2= "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
						sqlforweek3 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
						sqlforweek4 = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
					}
				}
				else{
					//sqlforweek1 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
					sqlforweek1 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
					sqlforweek2= "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
					sqlforweek3 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and  bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
					sqlforweek4 = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and   bookingDate BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
				}
			}

			SQLQuery queryforweek1 = session.createSQLQuery(sqlforweek1);
			queryforweek1.addEntity(FlightOrderRow.class);
			Long  listforweek1 =(Long)queryforweek1.uniqueResult();

			SQLQuery queryforweek2 = session.createSQLQuery(sqlforweek2);
			queryforweek2.addEntity(FlightOrderRow.class);
			Long  listforweek2 =(Long)queryforweek2.uniqueResult();
			

			SQLQuery queryforweek3 = session.createSQLQuery(sqlforweek3);
			queryforweek3.addEntity(FlightOrderRow.class);
			Long  listforweek3 =(Long)queryforweek3.uniqueResult();

			SQLQuery queryforweek4 = session.createSQLQuery(sqlforweek4);
			queryforweek4.addEntity(FlightOrderRow.class);
			Long  listforweek4 =(Long)queryforweek4.uniqueResult();


			dailylist.add(listforweek1);
			dailylist.add(listforweek2);
			dailylist.add(listforweek3);
			dailylist.add(listforweek4);

		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return dailylist;
	}


	public  List<FlightOrderRow> getFlightMonthOrders(User userSessionObj,Company companySessionObj){
		List<FlightOrderRow> list=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 

			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
					}
				}
				else{
					sql = "select * from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate between  DATE_ADD(CURDATE() ,INTERVAL -29 day) and CURDATE()";
				}
			}
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FlightOrderRow.class);
			list = query.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	
	public  Long getFlightTodayOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{

			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){

				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
						sql = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
							sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE()";
					}
				}
				else{
					sql = "select * from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate=CURDATE()";
				}
			}
			logger.info("--------------getFlightTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getFlightTodayConfirmOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
						sql = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getFlightTodayPaymentOrdersCount(User userSessionObj,Company companySessionObj,StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getFlightTodayPaymentFailedOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select count(*) from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}


	public  Long getTrainTodayOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select count(*) from train_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			logger.info("--------------getTrainTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}
	public  Long getTrainTodayConfirmOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select count(*) from train_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getTrainTodayPaymentOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select count(*) from train_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();
		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getTrainTodayPaymentFailedOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select count(*) from train_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getCarTodayOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}

				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select count(*) from car_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}
	public  Long getCarTodayConfirmOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select count(*) from car_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getCarTodayPaymentOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select count(*) from car_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getCarTodayPaymentFailedOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select count(*) from car_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}


	public  Long getBusTodayOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select count(*) from bus_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}
	public  Long getBusTodayConfirmOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select count(*) from bus_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getBusTodayPaymentOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select count(*) from bus_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getBusTodayPaymentFailedOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select count(*) from bus_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}


	public  Long getInsuranceTodayOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select count(*) from insurance_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			logger.info("--------------getBusTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}
	public  Long getInsuranceTodayConfirmOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select count(*) from insurance_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getInsuranceTodayPaymentOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select count(*) from insurance_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getInsuranceTodayPaymentFailedOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select count(*) from insurance_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}



	public  Long getVisaTodayOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select count(*) from visa_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			logger.info("--------------getBusTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}
	public Long  getVisaTodayConfirmOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select count(*) from visa_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getVisaTodayPaymentOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select count(*) from visa_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	public  Long getVisaTodayPaymentFailedOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count = new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select count(*) from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select count(*) from visa_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return count;
	}

	@SuppressWarnings("unchecked")
	public  List<FlightOrderRow> getFlightTodayConfirmOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<FlightOrderRow> Confirmlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select * from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FlightOrderRow.class);
			Confirmlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Confirmlist;
	}

	public  List<FlightOrderRow> getFlightTodayPaymentOrders(User userSessionObj,Company companySessionObj,StringBuffer userList){
		List<FlightOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select * from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FlightOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}

	public  List<FlightOrderRow> getFlightTodayPaymentFailedOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<FlightOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select * from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FlightOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}


	public  List<TrainOrderRow> getTrainTodayOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<TrainOrderRow> list=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select * from train_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			logger.info("--------------getTrainTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TrainOrderRow.class);
			list = query.list();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return list;
	}
	public  List<TrainOrderRow> getTrainTodayConfirmOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<TrainOrderRow> Confirmlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select * from train_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TrainOrderRow.class);
			Confirmlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Confirmlist;
	}

	public  List<TrainOrderRow> getTrainTodayPaymentOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<TrainOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select * from train_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TrainOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}

	public  List<TrainOrderRow> getTrainTodayPaymentFailedOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<TrainOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from train_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from train_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select * from train_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TrainOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}

	public  List<CarOrderRow> getCarTodayOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<CarOrderRow> list=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}

				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select * from car_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(CarOrderRow.class);
			list = query.list();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return list;
	}
	public  List<CarOrderRow> getCarTodayConfirmOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<CarOrderRow> Confirmlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select * from car_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(CarOrderRow.class);
			Confirmlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Confirmlist;
	}

	public  List<CarOrderRow> getCarTodayPaymentOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<CarOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select * from car_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(CarOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}

	public  List<CarOrderRow> getCarTodayPaymentFailedOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<CarOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from car_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from car_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select * from car_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(CarOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}


	public  List<BusOrderRow> getBusTodayOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<BusOrderRow> list=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select * from bus_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(BusOrderRow.class);
			list = query.list();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return list;
	}
	public  List<BusOrderRow> getBusTodayConfirmOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<BusOrderRow> Confirmlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select * from bus_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(BusOrderRow.class);
			Confirmlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Confirmlist;
	}

	public  List<BusOrderRow> getBusTodayPaymentOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<BusOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select * from bus_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(BusOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}

	public  List<BusOrderRow> getBusTodayPaymentFailedOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<BusOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from bus_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from bus_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select * from bus_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(BusOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}


	public  List<InsuranceOrderRow> getInsuranceTodayOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<InsuranceOrderRow> list=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select * from insurance_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			logger.info("--------------getBusTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(InsuranceOrderRow.class);
			list = query.list();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return list;
	}
	public  List<InsuranceOrderRow> getInsuranceTodayConfirmOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<InsuranceOrderRow> Confirmlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select * from insurance_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(InsuranceOrderRow.class);
			Confirmlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Confirmlist;
	}

	public  List<InsuranceOrderRow> getInsuranceTodayPaymentOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<InsuranceOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select * from insurance_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(InsuranceOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}

	public  List<InsuranceOrderRow> getInsuranceTodayPaymentFailedOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<InsuranceOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from insurance_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from insurance_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select * from insurance_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(InsuranceOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}



	public  List<VisaOrderRow> getVisaTodayOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<VisaOrderRow> list=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select * from visa_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE()";
				}
			}
			logger.info("--------------getBusTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(VisaOrderRow.class);
			list = query.list();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return list;
	}
	public  List<VisaOrderRow> getVisaTodayConfirmOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<VisaOrderRow> Confirmlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
					}
				}
				else{
					sql = "select * from visa_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and status_action='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(VisaOrderRow.class);
			Confirmlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Confirmlist;
	}

	public  List<VisaOrderRow> getVisaTodayPaymentOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<VisaOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
					}
				}
				else{
					sql = "select * from visa_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Success' or payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(VisaOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}

	public  List<VisaOrderRow> getVisaTodayPaymentFailedOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<VisaOrderRow> Paymentlist=null;
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
					sql = "select * from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from visa_order_row where user_id in ("+userIdBuffer+") and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from visa_order_row where company_id="+companySessionObj.getCompanyid()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
					}
				}
				else{
					sql = "select * from visa_order_row where user_id="+userSessionObj.getId()+" and DATE(created_at)=CURDATE() and (payment_status='Failed' or payment_status='Pending')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(VisaOrderRow.class);
			Paymentlist = query.list();


		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return Paymentlist;
	}
	public  List<FlightOrderRow> getFlightTodayOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<FlightOrderRow> list=null;
		String sql = "";
		try{

			session = HibernateUtil.getSessionFactory().openSession();		 
			if(userSessionObj!=null && companySessionObj!=null){

				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)
						sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
							sql = "select * from flight_order_row where user_id in ("+userIdBuffer+") and bookingDate=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from flight_order_row where company_id="+companySessionObj.getCompanyid()+" and bookingDate=CURDATE()";
					}
				}
				else{
					sql = "select * from flight_order_row where user_id="+userSessionObj.getId()+" and bookingDate=CURDATE()";
				}
			}
			logger.info("--------------getFlightTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FlightOrderRow.class);
			list = query.list();
			//logger.info("list" +list.size());

		} catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return list;
	}


}
