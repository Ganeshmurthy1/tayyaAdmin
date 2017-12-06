package com.lintas.admin.DAO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.hotel.model.HotelReport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;

public class HotelWeekandMonthOrderDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelWeekandMonthOrderDao.class);
	private SessionFactory sessionfactory;
	private Session session = null;
	private Transaction transaction = null;

	private CompanyDAO companyDAO = new CompanyDAO();

	public  List<HotelReport> getHotelWeekOrders(User userSessionObj,Company companySessionObj){
		List<HotelReport> reportlist = new ArrayList<HotelReport>();
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);			
					sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -6 day) and curdate()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from hotel_order_row where  company_id ="+companySessionObj.getCompanyid()+" and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -6 day) and curdate()";
					}else
					{
						StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);	
						sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -6 day) and curdate()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from hotel_order_row where  user_id="+userSessionObj.getCompanyid()+" and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -6 day) and curdate()";
					}
				}
				else{
					sql = "select * from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -6 day) and curdate()";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(HotelOrderRow.class);
			List<HotelOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				HotelOrderRow hor= (HotelOrderRow)iterator.next(); 
				HotelReport reportData=new HotelReport();
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
				sdf.applyPattern("dd/MM/yyyy");
				reportData.setCheckInDate(sdf.format(hor.getCheckInDate()));

				reportData.setCheckOutDate(sdf.format(hor.getCheckOutDate()));
				reportData.setCompanyId(hor.getCompanyId());
				reportData.setConfigId(hor.getConfigId());
				reportData.setCreatedBy(hor.getCreatedBy());
				reportData.setBooking_status(hor.getBookingStatus());
				reportData.setOrderRef(hor.getOrderReference());
				reportData.setTotal(hor.getFinalPrice());
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTax(hor.getTaxes());
				reportData.setFee_amount(hor.getFeeAmount());
				reportData.setDiscount(hor.getDiscountAmount());
				reportData.setAgentCom(hor.getMarkupAmount());
				reportData.setPaymentStatus(hor.getPaymentStatus());
				reportData.setStatusAction(hor.getStatusAction());
				reportData.setHotelName(hor.getHotelOrderHotelData().getName());
				reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
				reportData.setCurCode(hor.getBaseCurrency());
				reportData.setId(hor.getId());
				reportData.setHotel_loc(hor.getHotelOrderHotelData().getHotelLocation());
				reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
				reportData.setFirstname(hor.getOrderCustomer().getFirstName());
				reportData.setLastname(hor.getOrderCustomer().getLastName());
				reportData.setEmail(hor.getOrderCustomer().getEmail());
				reportData.setState(hor.getHotelOrderHotelData().getState());
				reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
				reportData.setApiComments(hor.getApiComments());
				reportData.setUserCommits(hor.getUserComments());
				reportData.setUserId(hor.getUserId());
				reportlist.add(reportData);
			}	



		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportlist;
	}

	public  List<Long> getHotelDayofWeekOrders(User userSessionObj,Company companySessionObj){

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
					sqlforday1 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
					sqlforday2 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
					sqlforday3 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
					sqlforday4 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
					sqlforday5 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
					sqlforday6 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
					sqlforday7 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sqlforday1 = "select count(*) from hotel_order_row where company_id ="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
						sqlforday2= "select count(*) from hotel_order_row where company_id ="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
						sqlforday3 = "select count(*) from hotel_order_row where company_id ="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
						sqlforday4 = "select count(*) from hotel_order_row where company_id ="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
						sqlforday5 = "select count(*) from hotel_order_row where company_id ="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
						sqlforday6 = "select count(*) from hotel_order_row where company_id ="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
						sqlforday7= "select count(*) from hotel_order_row where company_id ="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
					}else
					{
						StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						sqlforday1 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
						sqlforday2= "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
						sqlforday3 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
						sqlforday4 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
						sqlforday5 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
						sqlforday6 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
						sqlforday7= "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sqlforday1 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
						sqlforday2= "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
						sqlforday3 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
						sqlforday4 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
						sqlforday5 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
						sqlforday6 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
						sqlforday7= "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
					}
				}
				else{
					//sqlforday1 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
					sqlforday1 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
					sqlforday2= "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -5 DAY)";
					sqlforday3 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -4 DAY)";
					sqlforday4 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
					sqlforday5 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -2 DAY)";
					sqlforday6 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -1 DAY)";
					sqlforday7= "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -0 DAY)";
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
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return dailylist;
	}

	public  List<Long> getHotelWeekofMonthOrders(User userSessionObj,Company companySessionObj){

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
					sqlforweek1 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
					sqlforweek2 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
					sqlforweek3 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
					sqlforweek4 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+") and  date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sqlforweek1 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
						sqlforweek2= "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
						sqlforweek3 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
						sqlforweek4 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
					}
					else
					{
						StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						sqlforweek1 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+")and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
						sqlforweek2= "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+")and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
						sqlforweek3 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+")and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
						sqlforweek4 = "select count(*) from hotel_order_row where user_id in ("+userIdBuffer+")and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sqlforweek1 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
						sqlforweek2= "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
						sqlforweek3 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
						sqlforweek4 = "select count(*) from hotel_order_row where company_id="+companySessionObj.getCompanyid()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
					}
				}
				else{
					//sqlforweek1 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at)=DATE_ADD(CURDATE(), INTERVAL -6 DAY)";
					sqlforweek1 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -6 DAY) AND DATE_ADD(CURDATE(), INTERVAL 0 DAY)";
					sqlforweek2= "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -14 DAY) AND DATE_ADD(CURDATE(), INTERVAL -7 DAY)";
					sqlforweek3 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and  date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -22 DAY) AND DATE_ADD(CURDATE(), INTERVAL -15 DAY)";
					sqlforweek4 = "select count(*) from hotel_order_row where user_id="+userSessionObj.getId()+" and   date(created_at) BETWEEN DATE_ADD(CURDATE(), INTERVAL -29 DAY) AND DATE_ADD(CURDATE(), INTERVAL -23 DAY)";
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
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return dailylist;
	}

	public  List<HotelReport> getHotelMonthOrders(User userSessionObj,Company companySessionObj){
		List<HotelReport> reportlist = new ArrayList<HotelReport>();
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);		
					sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at) between DATE_ADD(curdate() ,INTERVAL -29 day) and curdate()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+" and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -29 day) and curdate()";
					}
					else
					{
						StringBuffer userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						sql = "select * from hotel_order_row where   user_id in ("+userIdBuffer+")  and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -29 day) and curdate()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+" and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -29 day) and curdate()";
					}
				}
				else{
					sql = "select * from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at) between  DATE_ADD(curdate() ,INTERVAL -29 day) and curdate()";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(HotelOrderRow.class);
			List<HotelOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				HotelOrderRow hor= (HotelOrderRow)iterator.next(); 
				HotelReport reportData=new HotelReport();
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
				sdf.applyPattern("dd/MM/yyyy");
				reportData.setCheckInDate(sdf.format(hor.getCheckInDate()));

				reportData.setCheckOutDate(sdf.format(hor.getCheckOutDate()));
				reportData.setCompanyId(hor.getCompanyId());
				reportData.setConfigId(hor.getConfigId());
				reportData.setCreatedBy(hor.getCreatedBy());
				reportData.setBooking_status(hor.getBookingStatus());
				reportData.setOrderRef(hor.getOrderReference());
				reportData.setTotal(hor.getFinalPrice());
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTax(hor.getTaxes());
				reportData.setFee_amount(hor.getFeeAmount());
				reportData.setDiscount(hor.getDiscountAmount());
				reportData.setAgentCom(hor.getMarkupAmount());
				reportData.setPaymentStatus(hor.getPaymentStatus());
				reportData.setStatusAction(hor.getStatusAction());
				reportData.setHotelName(hor.getHotelOrderHotelData().getName());
				reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
				reportData.setCurCode(hor.getBaseCurrency());
				reportData.setId(hor.getId());
				reportData.setHotel_loc(hor.getHotelOrderHotelData().getHotelLocation());
				reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
				reportData.setFirstname(hor.getOrderCustomer().getFirstName());
				reportData.setLastname(hor.getOrderCustomer().getLastName());
				reportData.setEmail(hor.getOrderCustomer().getEmail());
				reportData.setState(hor.getHotelOrderHotelData().getState());
				reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
				reportData.setApiComments(hor.getApiComments());
				reportData.setUserCommits(hor.getUserComments());
				reportData.setUserId(hor.getUserId());
				reportlist.add(reportData);
			}	


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}	 
		return reportlist;
	}

	public Long getHotelTodayOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		String sql = "";
		Long count= new Long(0);
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)		
					sql = "select count(*) from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+"  and date(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from hotel_order_row where  user_id="+userSessionObj.getCompanyid()+" and date(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select count(*) from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at)=CURDATE()";
				}
			}

			//	 logger.info("sql" +sql );
			logger.info("--------------getHotelTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return count;
	}

	public Long  getHotelTodayConfirmOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count= new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)		
					sql = "select count(*) from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and booking_status='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+"  and date(created_at)=CURDATE() and booking_status='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from hotel_order_row where  user_id in ("+userIdBuffer+")  and date(created_at)=CURDATE() and booking_status='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+" and date(created_at)=CURDATE() and booking_status='Confirmed'";
					}
				}
				else{
					sql = "select count(*) from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at)=CURDATE() and booking_status='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return count;
	}
	public  Long getHotelTodayPaymentOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count= new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)		
					sql = "select count(*) from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and (payment_status='wallet payment success' or payment_status='paid' or  payment_status='Paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+"  and date(created_at)=CURDATE() and (payment_status='wallet payment success' or payment_status='paid' or  payment_status='Paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from hotel_order_row where   user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and  (payment_status='wallet payment success' or payment_status='paid' or  payment_status='Paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+" and date(created_at)=CURDATE() and  (payment_status='wallet payment success' or payment_status='paid' or  payment_status='Paid')";
					}
				}
				else{
					sql = "select count(*) from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at)=CURDATE() and  (payment_status='wallet payment success' or payment_status='paid' or  payment_status='Paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return count;
	}

	public  Long getHotelTodayPaymentFailedOrdersCount(User userSessionObj,Company companySessionObj, StringBuffer userList){
		Long count= new Long(0);
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)		
					sql = "select count(*) from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and (payment_status='failed' or payment_status='Failed')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select count(*) from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+"  and date(created_at)=CURDATE() and (payment_status='failed' or payment_status='Failed')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select count(*) from hotel_order_row where   user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and (payment_status='failed' or payment_status='Failed')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select count(*) from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+" and date(created_at)=CURDATE() and (payment_status='failed' or payment_status='Failed')";
					}
				}
				else{
					sql = "select count(*) from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at)=CURDATE() and (payment_status='failed' or payment_status='Failed')";
				}
			}
			logger.info("PaymentFailedOrders-sql--::" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			count= ((Number) query.uniqueResult()).longValue();


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return count;
	}

	
	
	public  List<HotelReport> getHotelTodayOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<HotelReport> reportlist = new ArrayList<HotelReport>();
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)		
					sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE()";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+"  and date(created_at)=CURDATE()";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE()";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from hotel_order_row where  user_id="+userSessionObj.getCompanyid()+" and date(created_at)=CURDATE()";
					}
				}
				else{
					sql = "select * from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at)=CURDATE()";
				}
			}

			//	 logger.info("sql" +sql );
			logger.info("--------------getHotelTodayOrders-----------------" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(HotelOrderRow.class);
			List<HotelOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				HotelOrderRow hor= (HotelOrderRow)iterator.next(); 
				HotelReport reportData=new HotelReport();
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
				sdf.applyPattern("dd/MM/yyyy");
				reportData.setCheckInDate(sdf.format(hor.getCheckInDate()));

				reportData.setCheckOutDate(sdf.format(hor.getCheckOutDate()));

				BigDecimal basePrice= hor.getApiPrice().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= hor.getTaxes().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(hor.getMarkupAmount());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=hor.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);

				reportData.setCompanyId(hor.getCompanyId());
				reportData.setConfigId(hor.getConfigId());
				reportData.setCreatedBy(hor.getCreatedBy());
				reportData.setBooking_status(hor.getBookingStatus());
				reportData.setOrderRef(hor.getOrderReference());
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTax(taxesInBooking);
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTotal(totalPrice.setScale(2, BigDecimal.ROUND_UP));

				reportData.setFee_amount(hor.getFeeAmount());
				reportData.setDiscount(hor.getDiscountAmount());
				reportData.setAgentCom(hor.getMarkupAmount());
				reportData.setPaymentStatus(hor.getPaymentStatus());
				reportData.setStatusAction(hor.getStatusAction());
				reportData.setHotelName(hor.getHotelOrderHotelData().getName());
				reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
				reportData.setCurCode(hor.getBaseCurrency());
				reportData.setId(hor.getId());
				reportData.setHotel_loc(hor.getHotelOrderHotelData().getHotelLocation());
				reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
				reportData.setFirstname(hor.getOrderCustomer().getFirstName());
				reportData.setLastname(hor.getOrderCustomer().getLastName());
				reportData.setEmail(hor.getOrderCustomer().getEmail());
				reportData.setState(hor.getHotelOrderHotelData().getState());
				reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
				reportData.setApiComments(hor.getApiComments());
				reportData.setUserCommits(hor.getUserComments());
				reportData.setUserId(hor.getUserId());
				reportlist.add(reportData);
			}	

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportlist;
	}

	public  List<HotelReport> getHotelTodayConfirmOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<HotelReport> reportlist = new ArrayList<HotelReport>();
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)		
					sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and booking_status='Confirmed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+"  and date(created_at)=CURDATE() and booking_status='Confirmed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+")  and date(created_at)=CURDATE() and booking_status='Confirmed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+" and date(created_at)=CURDATE() and booking_status='Confirmed'";
					}
				}
				else{
					sql = "select * from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at)=CURDATE() and booking_status='Confirmed'";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(HotelOrderRow.class);
			List<HotelOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				HotelOrderRow hor= (HotelOrderRow)iterator.next(); 
				HotelReport reportData=new HotelReport();
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
				sdf.applyPattern("dd/MM/yyyy");
				reportData.setCheckInDate(sdf.format(hor.getCheckInDate()));

				reportData.setCheckOutDate(sdf.format(hor.getCheckOutDate()));
				BigDecimal basePrice= hor.getApiPrice().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= hor.getTaxes().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(hor.getMarkupAmount());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=hor.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);

				reportData.setCompanyId(hor.getCompanyId());
				reportData.setConfigId(hor.getConfigId());
				reportData.setCreatedBy(hor.getCreatedBy());
				reportData.setBooking_status(hor.getBookingStatus());
				reportData.setOrderRef(hor.getOrderReference());
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTax(taxesInBooking);
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTotal(totalPrice.setScale(2, BigDecimal.ROUND_UP));

				reportData.setFee_amount(hor.getFeeAmount());
				reportData.setDiscount(hor.getDiscountAmount());
				reportData.setAgentCom(hor.getMarkupAmount());
				reportData.setPaymentStatus(hor.getPaymentStatus());
				reportData.setStatusAction(hor.getStatusAction());
				reportData.setHotelName(hor.getHotelOrderHotelData().getName());
				reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
				reportData.setCurCode(hor.getBaseCurrency());
				reportData.setId(hor.getId());
				reportData.setHotel_loc(hor.getHotelOrderHotelData().getHotelLocation());
				reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
				reportData.setFirstname(hor.getOrderCustomer().getFirstName());
				reportData.setLastname(hor.getOrderCustomer().getLastName());
				reportData.setEmail(hor.getOrderCustomer().getEmail());
				reportData.setState(hor.getHotelOrderHotelData().getState());
				reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
				reportData.setApiComments(hor.getApiComments());
				reportData.setUserCommits(hor.getUserComments());
				reportData.setUserId(hor.getUserId());
				reportlist.add(reportData);
			}	


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportlist;
	}
	public  List<HotelReport> getHotelTodayPaymentOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<HotelReport> reportlist = new ArrayList<HotelReport>();
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)		
					sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and (payment_status='wallet payment success' or payment_status='paid')";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+"  and date(created_at)=CURDATE() and (payment_status='wallet payment success' or payment_status='paid')";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from hotel_order_row where   user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and(payment_status='wallet payment success' or payment_status='paid')";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+" and date(created_at)=CURDATE() and (payment_status='wallet payment success' or payment_status='paid')";
					}
				}
				else{
					sql = "select * from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at)=CURDATE() and (payment_status='wallet payment success' or payment_status='paid')";
				}
			}

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(HotelOrderRow.class);
			List<HotelOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				HotelOrderRow hor= (HotelOrderRow)iterator.next(); 
				HotelReport reportData=new HotelReport();
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
				sdf.applyPattern("dd/MM/yyyy");
				reportData.setCheckInDate(sdf.format(hor.getCheckInDate()));

				reportData.setCheckOutDate(sdf.format(hor.getCheckOutDate()));
				BigDecimal basePrice= hor.getApiPrice().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= hor.getTaxes().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(hor.getMarkupAmount());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=hor.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);

				reportData.setCompanyId(hor.getCompanyId());
				reportData.setConfigId(hor.getConfigId());
				reportData.setCreatedBy(hor.getCreatedBy());
				reportData.setBooking_status(hor.getBookingStatus());
				reportData.setOrderRef(hor.getOrderReference());
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTax(taxesInBooking);
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTotal(totalPrice.setScale(2, BigDecimal.ROUND_UP));

				reportData.setFee_amount(hor.getFeeAmount());
				reportData.setDiscount(hor.getDiscountAmount());
				reportData.setAgentCom(hor.getMarkupAmount());
				reportData.setPaymentStatus(hor.getPaymentStatus());
				reportData.setStatusAction(hor.getStatusAction());
				reportData.setHotelName(hor.getHotelOrderHotelData().getName());
				reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
				reportData.setCurCode(hor.getBaseCurrency());
				reportData.setId(hor.getId());
				reportData.setHotel_loc(hor.getHotelOrderHotelData().getHotelLocation());
				reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
				reportData.setFirstname(hor.getOrderCustomer().getFirstName());
				reportData.setLastname(hor.getOrderCustomer().getLastName());
				reportData.setEmail(hor.getOrderCustomer().getEmail());
				reportData.setState(hor.getHotelOrderHotelData().getState());
				reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
				reportData.setApiComments(hor.getApiComments());
				reportData.setUserCommits(hor.getUserComments());
				reportData.setUserId(hor.getUserId());
				reportlist.add(reportData);
			}	


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportlist;
	}

	public  List<HotelReport> getHotelTodayPaymentFailedOrders(User userSessionObj,Company companySessionObj, StringBuffer userList){
		List<HotelReport> reportlist = new ArrayList<HotelReport>();
		String sql = "";
		try{
			session = HibernateUtil.getSessionFactory().openSession();


			if(userSessionObj!=null && companySessionObj!=null){
				if(userSessionObj.getUserrole_id().isSuperuser()){
					StringBuffer userIdBuffer = userList;
					if(userIdBuffer==null)
						userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
					if(userIdBuffer!=null)		
					sql = "select * from hotel_order_row where  user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and payment_status='failed'";
				}
				else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
					if(companySessionObj.getCompanyRole().isCorporate())
					{
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+"  and date(created_at)=CURDATE() and payment_status='failed'";
					}
					else
					{
						StringBuffer userIdBuffer = userList;
						if(userIdBuffer==null)
							userIdBuffer= companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
						if(userIdBuffer!=null)
						sql = "select * from hotel_order_row where   user_id in ("+userIdBuffer+") and date(created_at)=CURDATE() and payment_status='failed'";
					}
				}
				else if((userSessionObj.getUserrole_id().isOrder() || userSessionObj.getUserrole_id().isReports()) && !userSessionObj.getUserrole_id().isAdmin()){
					if(userSessionObj.getCompanyid()==companySessionObj.getCompanyid()){
						sql = "select * from hotel_order_row where  company_id="+userSessionObj.getCompanyid()+" and date(created_at)=CURDATE() and payment_status='failed'";
					}
				}
				else{
					sql = "select * from hotel_order_row where  user_id="+userSessionObj.getId()+" and date(created_at)=CURDATE() and payment_status!='wallet payment success' ";
				}
			}
			logger.info("PaymentFailedOrders-sql--::" +sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(HotelOrderRow.class);
			List<HotelOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				HotelOrderRow hor= (HotelOrderRow)iterator.next(); 
				HotelReport reportData=new HotelReport();
				SimpleDateFormat sdf = new SimpleDateFormat();
				sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
				sdf.applyPattern("dd/MM/yyyy");
				reportData.setCheckInDate(sdf.format(hor.getCheckInDate()));

				reportData.setCheckOutDate(sdf.format(hor.getCheckOutDate()));
				BigDecimal basePrice= hor.getApiPrice().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= hor.getTaxes().multiply(hor.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(hor.getMarkupAmount());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(hor.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=hor.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);

				reportData.setCompanyId(hor.getCompanyId());
				reportData.setConfigId(hor.getConfigId());
				reportData.setCreatedBy(hor.getCreatedBy());
				reportData.setBooking_status(hor.getBookingStatus());
				reportData.setOrderRef(hor.getOrderReference());
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTax(taxesInBooking);
				reportData.setGuests(hor.getTotalGuest());
				reportData.setTotal(totalPrice.setScale(2, BigDecimal.ROUND_UP));

				reportData.setFee_amount(hor.getFeeAmount());
				reportData.setDiscount(hor.getDiscountAmount());
				reportData.setAgentCom(hor.getMarkupAmount());
				reportData.setPaymentStatus(hor.getPaymentStatus());
				reportData.setStatusAction(hor.getStatusAction());
				reportData.setHotelName(hor.getHotelOrderHotelData().getName());
				reportData.setCountry(hor.getHotelOrderHotelData().getCountry());
				reportData.setCurCode(hor.getBaseCurrency());
				reportData.setId(hor.getId());
				reportData.setHotel_loc(hor.getHotelOrderHotelData().getHotelLocation());
				reportData.setPhone(hor.getHotelOrderHotelData().getTelephone());
				reportData.setFirstname(hor.getOrderCustomer().getFirstName());
				reportData.setLastname(hor.getOrderCustomer().getLastName());
				reportData.setEmail(hor.getOrderCustomer().getEmail());
				reportData.setState(hor.getHotelOrderHotelData().getState());
				reportData.setHotel_cat(hor.getHotelOrderHotelData().getHotelCategory());
				reportData.setApiComments(hor.getApiComments());
				reportData.setUserCommits(hor.getUserComments());
				reportData.setUserId(hor.getUserId());
				reportlist.add(reportData);
			}	


		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		} finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return reportlist;
	}

}
