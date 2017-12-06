package com.lintas.admin.DAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.PassengerProfileReport;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;

public class CrmDetailsDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	List<Long> customeridlist = new ArrayList<Long>();
	int tottalrecordscount = 0;

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CrmDetailsDao.class);
	public List<Long> GetCustomerIdList(User user)
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRow hr where hr.userId=:userid  order by hr.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("userid", user.getId());
			List<HotelOrderRow> list = query.list();
			for (Iterator  iterator = list.iterator(); iterator.hasNext();){
				HotelOrderRow hor= (HotelOrderRow)iterator.next(); 	
				customeridlist.add(hor.getOrderCustomer().getId());
			}

			String sqlflight = "from FlightOrderRow fr where fr.userId=:userid  order by fr.id desc";
			Query queryflight = session.createQuery(sqlflight);
			queryflight.setParameter("userid", user.getId());
			List<FlightOrderRow> listflight = queryflight.list();
			for (Iterator  iterator = listflight.iterator(); iterator.hasNext();){
				FlightOrderRow hor= (FlightOrderRow)iterator.next(); 	
				logger.info("Customer id flight --- "+hor.getCustomer().getId());
				customeridlist.add(hor.getCustomer().getId());
			}

			logger.info("customeridlist size---"+customeridlist.size());
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return customeridlist;
	}

	public  List<Long> GetFlightFrequentFlyerCustomerId(User user)
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();		
			String sqlflight = "from FlightOrderRow fr  where fr.userId=:userid=";
			Query queryflight = session.createQuery(sqlflight);
			queryflight.setParameter("userid", user.getId());
			List<FlightOrderRow> listflight = queryflight.list();
			if(listflight!=null && listflight.size()>0){
				for (FlightOrderRow hor:listflight){
					logger.info("Customer id flight --- "+hor.getCustomer().getId());
					customeridlist.add(hor.getCustomer().getId());
				}
			}

			logger.info("customeridlist size---"+customeridlist.size());
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return customeridlist;
	}

	public  List<Long> GetSuperUserFlightFrequentFlyerCustomerId()
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();		
			String sqlflight = "from FlightOrderRow";
			Query queryflight = session.createQuery(sqlflight);

			List<FlightOrderRow> listflight = queryflight.list();
			if(listflight!=null && listflight.size()>0){
				for (FlightOrderRow hor:listflight){
					logger.info("Customer id flight --- "+hor.getCustomer().getId());
					customeridlist.add(hor.getCustomer().getId());
				}
			}
			logger.info("customeridlist size---"+customeridlist.size());
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return customeridlist;
	}


	public List<FlightOrderCustomer> GetSuperUserFlightFrequentFlyerList()
	{
		GetSuperUserFlightFrequentFlyerCustomerId();
		List<FlightOrderCustomer>  orderData_list =  new ArrayList<FlightOrderCustomer>();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				logger.info("customeridlist" +customeridlist.get(i));
				String sql = "from FlightOrderCustomer fc where fc.id=:id";			
				Query query = session.createQuery(sql);
				query.setParameter("id", customeridlist.get(i));
				List<FlightOrderCustomer> list = query.list();
				if(list!=null && list.size()>0){
					for (FlightOrderCustomer hor:list){
						orderData_list.add(hor);
					}
				}

			}
			logger.info("orderData_list size" +orderData_list.size());
			Set<FlightOrderCustomer> orderedlist = new HashSet<FlightOrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
			logger.info("orderData_list after size" +orderData_list.size());


		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return orderData_list;
	}

	public List<FlightOrderCustomer> GetSuperUserFilteredFlightFrequentFlyerList(String firstname,String lastname,String flyerno,String flyerairline)
	{
		GetSuperUserFlightFrequentFlyerCustomerId();
		List<FlightOrderCustomer>  orderData_list =  new ArrayList<FlightOrderCustomer>();
		Query query=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				String sql;
				if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.id=:id";
					query = session.createQuery(sql);
					query.setParameter("id", customeridlist.get(i));
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.firstName=:firstName";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.lastName=:lastname";
					query = session.createQuery(sql);
					query.setParameter("lastname", lastname);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.frequent_flyer_number=:flyer_number";
					query = session.createQuery(sql);
					query.setParameter("flyer_number", flyerno);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{

					sql = "from FlightOrderCustomer fc where fc.frequent_flyer_airline=:flyer_airline";
					query = session.createQuery(sql);
					query.setParameter("flyer_airline", flyerairline);
				}
				else if(!firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.firstName=:firstName and fc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("lastName", lastname);
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.firstName=:firstName and fc.frequent_flyer_number=:flyer_number";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("flyer_number", flyerno);
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{

					sql = "from FlightOrderCustomer fc where fc.firstName=:firstName and fc.frequent_flyer_airline=:flyer_airline";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("flyer_airline", flyerairline);


				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.lastName=:lastName and fc.frequent_flyer_number=:flyer_number";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
					query.setParameter("flyer_number", flyerno);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.lastName=:lastName and fc.frequent_flyer_airline=:flyer_airline";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
					query.setParameter("flyer_airline", flyerairline);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.frequent_flyer_number=:flyer_number and fc.frequent_flyer_airline=:flyer_airline";
					query = session.createQuery(sql);
					query.setParameter("flyer_number", flyerno);
					query.setParameter("flyer_airline", flyerairline);
				}
				else if(!firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.frequent_flyer_number=:flyer_number and fc.frequent_flyer_airline=:flyer_airline and fc.firstName=:firstName and fc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("flyer_number", flyerno);
					query.setParameter("flyer_airline", flyerairline);
					query.setParameter("firstName", firstname);
					query.setParameter("lastName", lastname);
				}
				else
				{
					sql = "from FlightOrderCustomer fc where fc.id=:id";
					query = session.createQuery(sql);
					query.setParameter("id", customeridlist.get(i));
				}
				List<FlightOrderCustomer> list = query.list();
				if(list!=null && list.size()>0){
					for (FlightOrderCustomer hor:list){
						orderData_list.add(hor);
					}
				}

			}
			logger.info("orderData_list size" +orderData_list.size());
			Set<FlightOrderCustomer> orderedlist = new HashSet<FlightOrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
			logger.info("orderData_list after size" +orderData_list.size());
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return orderData_list;
	}

	public List<FlightOrderCustomer> GetFilteredFrequentFlyerDetailsList(User user,String firstname,String lastname,String flyerno,String flyerairline)
	{
		GetFlightFrequentFlyerCustomerId(user);
		List<FlightOrderCustomer>  orderData_list =  new ArrayList<FlightOrderCustomer>();
		Query query=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				String sql;
				if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.id=:id";
					query = session.createQuery(sql);
					query.setParameter("id", customeridlist.get(i));
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.firstName=:firstName";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.lastName=:lastname";
					query = session.createQuery(sql);
					query.setParameter("lastname", lastname);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.frequent_flyer_number=:flyer_number";
					query = session.createQuery(sql);
					query.setParameter("flyer_number", flyerno);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{

					sql = "from FlightOrderCustomer fc where fc.frequent_flyer_airline=:flyer_airline";
					query = session.createQuery(sql);
					query.setParameter("flyer_airline", flyerairline);
				}
				else if(!firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.firstName=:firstName and fc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("lastName", lastname);
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.firstName=:firstName and fc.frequent_flyer_number=:flyer_number";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("flyer_number", flyerno);
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{

					sql = "from FlightOrderCustomer fc where fc.firstName=:firstName and fc.frequent_flyer_airline=:flyer_airline";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("flyer_airline", flyerairline);


				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.lastName=:lastName and fc.frequent_flyer_number=:flyer_number";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
					query.setParameter("flyer_number", flyerno);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.lastName=:lastName and fc.frequent_flyer_airline=:flyer_airline";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
					query.setParameter("flyer_airline", flyerairline);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.frequent_flyer_number=:flyer_number and fc.frequent_flyer_airline=:flyer_airline";
					query = session.createQuery(sql);
					query.setParameter("flyer_number", flyerno);
					query.setParameter("flyer_airline", flyerairline);
				}
				else if(!firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& !flyerno.equalsIgnoreCase("") && !flyerairline.equalsIgnoreCase(""))
				{
					sql = "from FlightOrderCustomer fc where fc.frequent_flyer_number=:flyer_number and fc.frequent_flyer_airline=:flyer_airline and fc.firstName=:firstName and fc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("flyer_number", flyerno);
					query.setParameter("flyer_airline", flyerairline);
					query.setParameter("firstName", firstname);
					query.setParameter("lastName", lastname);
				}
				else
				{
					sql = "from FlightOrderCustomer fc where fc.id=:id";
					query = session.createQuery(sql);
					query.setParameter("id", customeridlist.get(i));
				}
				List<FlightOrderCustomer> list = query.list();
				if(list!=null && list.size()>0){
					for (FlightOrderCustomer hor:list){
						orderData_list.add(hor);
					}
				}

			}
			logger.info("orderData_list size" +orderData_list.size());
			Set<FlightOrderCustomer> orderedlist = new HashSet<FlightOrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
			logger.info("orderData_list after size" +orderData_list.size());

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return orderData_list;
	}

	public List<FlightOrderCustomer> GetFrequentFlyerDetailsList(User user)
	{
		GetFlightFrequentFlyerCustomerId(user);
		List<FlightOrderCustomer>  orderData_list =  new ArrayList<FlightOrderCustomer>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				logger.info("customeridlist" +customeridlist.get(i));
				String sql = "from FlightOrderCustomer fc where fc.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id", customeridlist.get(i));
				List<FlightOrderCustomer> list = query.list();
				for (FlightOrderCustomer hor:list){
					orderData_list.add(hor);
				}
			}
			logger.info("orderData_list size" +orderData_list.size());
			Set<FlightOrderCustomer> orderedlist = new HashSet<FlightOrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
			logger.info("orderData_list after size" +orderData_list.size());

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return orderData_list;
	}

	public List<Long> GetCustomerIdListforSuperUser()
	{
		 try{
			 session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRow hr order by hr.id desc";
			Query query = session.createQuery(sql);
			List<HotelOrderRow> list = query.list();
			if(list!=null && list.size()>0){
				for(HotelOrderRow hor:list){
					customeridlist.add(hor.getOrderCustomer().getId());
				} 
			}


			String sqlflight = "from FlightOrderRow fr order by fr.id desc ";
			Query queryflight = session.createQuery(sqlflight);
			List<FlightOrderRow> listflight = queryflight.list();
			for (FlightOrderRow hor:listflight){
				customeridlist.add(hor.getCustomer().getId());
			}
			logger.info("tottalrecordscount---"+tottalrecordscount);

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return customeridlist;
	}

	public List<Long> GetLimitedCustomerIdListforSuperUser()
	{
		List<PassengerProfileReport>  orderData_list =  new ArrayList<PassengerProfileReport>();

		try{
			//order by customer_id desc LIMIT 10
			session = HibernateUtil.getSessionFactory().openSession();

			String sql = "from HotelOrderRow hr where hr.id between 10 and 15";
			Query query = session.createQuery(sql);
			List<HotelOrderRow> list = query.list();
			for (HotelOrderRow hor:list){
				customeridlist.add(hor.getOrderCustomer().getId());
			}
			logger.info("customeridlist hotel---"+customeridlist.size());
			String sqlflight = "from FlightOrderRow fr  where fr.id between 10 and 15";
			Query queryflight = session.createQuery(sqlflight);
			List<FlightOrderRow> listflight = queryflight.list();
			for (FlightOrderRow hor:listflight){
				customeridlist.add(hor.getCustomer().getId());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return customeridlist;
	}
	public List<OrderCustomer> GetLimitedAllPassengerDetails()
	{
		GetLimitedCustomerIdListforSuperUser();
		List<OrderCustomer>  orderData_list =  new ArrayList<OrderCustomer>();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			logger.info("customeridlist" +customeridlist.size());
			for(int i=0;i<customeridlist.size();i++)
			{
				String sql = "from OrderCustomer oc where oc.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id",customeridlist.get(i));
				List<OrderCustomer> list = query.list();
				if(list!=null && list.size()>0){
					for (OrderCustomer hor:list){
						orderData_list.add(hor);
					}
				}

			}

			Set<OrderCustomer> orderedlist = new HashSet<OrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return orderData_list;
	}



	public List<OrderCustomer> GetPassengerDetails(User user)
	{
		GetCustomerIdList(user);

		List<OrderCustomer>  orderData_list =  new ArrayList<OrderCustomer>();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				logger.info("customeridlist" +customeridlist.get(i));
				String sql = "from OrderCustomer oc where oc.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id",customeridlist.get(i));
				List<OrderCustomer> list = query.list();
				for (OrderCustomer hor:list ){
					orderData_list.add(hor);
				}
			}

			Set<OrderCustomer> orderedlist = new HashSet<OrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return orderData_list;
	}

	public List<OrderCustomer> GetAllPassengerDetails()
	{
		GetCustomerIdListforSuperUser();
		List<OrderCustomer>  orderData_list =  new ArrayList<OrderCustomer>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				logger.info("customeridlist" +customeridlist.get(i));
				String sql = "from OrderCustomer oc where oc.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id", customeridlist.get(i));
				List<OrderCustomer> list = query.list();
				for (OrderCustomer hor:list){
					orderData_list.add(hor);
				}
			}

			Set<OrderCustomer> orderedlist = new HashSet<OrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return orderData_list;
	}

	public List<Long> GetCustomerIdListforPassportSuperUser()
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRow hr order by  hr.id desc LIMIT 10";
			Query query = session.createQuery(sql);
			List<HotelOrderRow> list = query.list();
			for (HotelOrderRow hor:list){
				customeridlist.add(hor.getOrderCustomer().getId());
			}
			String sqlflight = "from FlightOrderRow fr order by fr.id desc LIMIT 10";
			Query queryflight = session.createQuery(sqlflight);
			List<FlightOrderRow> listflight = queryflight.list();
			for (FlightOrderRow hor:listflight){
				customeridlist.add(hor.getCustomer().getId());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return customeridlist;
	}

	public List<OrderCustomer> GetAllPassportDetailsList()
	{
		GetCustomerIdListforPassportSuperUser();
		List<OrderCustomer>  orderData_list =  new ArrayList<OrderCustomer>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				logger.info("customeridlist" +customeridlist.get(i));
				String sql = "from OrderCustomer oc where oc.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id", customeridlist.get(i));
				List<OrderCustomer> list = query.list();
				for (OrderCustomer hor:list){
					orderData_list.add(hor);
				}
			}
			Set<OrderCustomer> orderedlist = new HashSet<OrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return orderData_list;
	}

	public List<OrderCustomer> GetFilteredAllPassengerDetails(String firstname,String lastname,String email,String mobile)
	{
		GetCustomerIdListforSuperUser();
		List<OrderCustomer>  orderData_list =  new ArrayList<OrderCustomer>(); 
		Query query=null;


		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				String sql;
				if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.id=:id";
					query = session.createQuery(sql);
					query.setParameter("id", customeridlist.get(i));
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.firstfame=:firstName";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.email=:email";
					query = session.createQuery(sql);
					query.setParameter("email", email);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.mobile=:mobile";
					query = session.createQuery(sql);
					query.setParameter("mobile", mobile);
				}
				else if(!firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.firstName=:firstName and oc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("lastName", lastname);
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.firstName=:firstName and oc.email=:email";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("email", email);
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.firstName=:firstName and oc.mobile=:mobile";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("mobile", mobile);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.lastName=:lastName and oc.email=:email";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
					query.setParameter("email", email);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.lastName=:lastName and oc.mobile=:mobile";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
					query.setParameter("mobile", mobile);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.email=:email and oc.mobile=:mobile";
					query = session.createQuery(sql);
					query.setParameter("email", email);
					query.setParameter("mobile", mobile);
				}
				else if(!firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.email=:email and oc.mobile=:mobile and oc.firstName=:firstName and oc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("email", email);
					query.setParameter("mobile", mobile);
					query.setParameter("firstName", firstname);
					query.setParameter("lastName", lastname);
				}
				else
				{
					sql = "from OrderCustomer oc where oc.id=:id";
					query = session.createQuery(sql);
					query.setParameter("id", customeridlist.get(i));
				}

				logger.info("sql-----------" +sql);

				List<OrderCustomer> list = query.list();
				for (OrderCustomer hor:list){
					orderData_list.add(hor);
				}
			}

			Set<OrderCustomer> orderedlist = new HashSet<OrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return orderData_list;
	}
	public List<OrderCustomer> GettFilteredPassengerDetails(User user,String firstname,String lastname,String email,String mobile)
	{
		GetCustomerIdList(user);
		Query query=null;
		List<OrderCustomer>  orderData_list =  new ArrayList<OrderCustomer>();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			for(int i=0;i<customeridlist.size();i++)
			{
				String sql;

				if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.id=:id";
					query = session.createQuery(sql);
					query.setParameter("id", customeridlist.get(i));
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.firstfame=:firstName";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.email=:email";
					query = session.createQuery(sql);
					query.setParameter("email", email);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.mobile=:mobile";
					query = session.createQuery(sql);
					query.setParameter("mobile", mobile);
				}
				else if(!firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.firstName=:firstName and oc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("lastName", lastname);
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.firstName=:firstName and oc.email=:email";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("email", email);
				}
				else if(!firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.firstName=:firstName and oc.mobile=:mobile";
					query = session.createQuery(sql);
					query.setParameter("firstName", firstname);
					query.setParameter("mobile", mobile);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.lastName=:lastName and oc.email=:email";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
					query.setParameter("email", email);
				}
				else if(firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.lastName=:lastName and oc.mobile=:mobile";
					query = session.createQuery(sql);
					query.setParameter("lastName", lastname);
					query.setParameter("mobile", mobile);
				}
				else if(firstname.equalsIgnoreCase("")&& lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.email=:email and oc.mobile=:mobile";
					query = session.createQuery(sql);
					query.setParameter("email", email);
					query.setParameter("mobile", mobile);
				}
				else if(!firstname.equalsIgnoreCase("")&& !lastname.equalsIgnoreCase("")&& !email.equalsIgnoreCase("") && !mobile.equalsIgnoreCase(""))
				{
					sql = "from OrderCustomer oc where oc.email=:email and oc.mobile=:mobile and oc.firstName=:firstName and oc.lastName=:lastName";
					query = session.createQuery(sql);
					query.setParameter("email", email);
					query.setParameter("mobile", mobile);
					query.setParameter("firstName", firstname);
					query.setParameter("lastName", lastname);
				}
				else
				{
					sql = "from OrderCustomer oc where oc.id=:id";
					query = session.createQuery(sql);
					query.setParameter("id", customeridlist.get(i));
				}

				logger.info("sql-----------" +sql);

				List<OrderCustomer> list = query.list();
				for (OrderCustomer hor:list){
					orderData_list.add(hor);
				}
			}
			Set<OrderCustomer> orderedlist = new HashSet<OrderCustomer>();
			orderedlist.addAll(orderData_list);
			orderData_list.clear();
			orderData_list.addAll(orderedlist);
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return orderData_list;
	}
	
	
	
	public CompanyFilterPage GetAllOrderCustomerDetails(CompanyFilterPage companyFilterPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
	/*	List<Integer> companyIds =getCompanyIdList(companyFilterPage);*/
		/*logger.info("------------child--companyids-----------------"+companyIds);*/
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(OrderCustomer.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter(); 
				
				if(companyFilter.getCompanyRoleType()!=null && !companyFilter.getCompanyRoleType().equalsIgnoreCase("All")){
					try{
						List<Integer> configIdList  =new CompanyConfigDao().getCompanyConfigIdListByConfigType(companyFilter.getCompanyRoleType());
						conjunction.add(Restrictions.in("configId",configIdList));
					}catch (Exception e) {
					}
				}
				
				List<String> companyIdList  = new ArrayList<String>();
				List<Integer> companyIdListInt=new ArrayList<Integer>();
				companyIdList = getCompanyIdList(companyFilter.getLoginCompany(), companyFilter.getReportType(), companyFilter.getCompanyName());
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					companyFilterPage.setAvailableItems(0);					
//					companyFilterPage.setItems(new ArrayList<ReportData>());
					return companyFilterPage;
				}
				else{
					for(String id:companyIdList){
						companyIdListInt.add(Integer.valueOf(id));
					}
				}
				conjunction.add(Restrictions.in("companyId",companyIdListInt));
				
				if(companyFilter.getEmail() != null && !companyFilter.getEmail().equals(""))
				{
					conjunction.add(Restrictions.eq("email", companyFilter.getEmail()));
					logger.info("##########Email  added----"+companyFilter.getEmail());
				}
				 
				if(companyFilter.getPhone() != null && !companyFilter.getPhone().equals(""))
				{
					conjunction.add(Restrictions.eq("phone", companyFilter.getPhone()));
					logger.info("##########getMobile  added----"+companyFilter.getPhone());
				}
				
				if(companyFilter.getFirstName() != null && !companyFilter.getFirstName().equals(""))
				{
					conjunction.add(Restrictions.eq("firstName", companyFilter.getFirstName()));
					logger.info("##########getPhone  added----"+companyFilter.getFirstName());
				}
				
				if(companyFilter.getLastName() != null && !companyFilter.getLastName().equals(""))
				{
					conjunction.add(Restrictions.eq("lastName", companyFilter.getLastName()));
					logger.info("##########getLastName  added----"+companyFilter.getLastName());
				} 
				criteria.add(conjunction);
			 criteria.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(companyFilterPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
					companyFilterPage.setAvailableItems(availableItems);
					companyFilterPage.setAvailablePages(availablePages);
				} 
				int pageIndexDb = (companyFilterPage.getCurrentPageIndex() > 1)?companyFilterPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * companyFilterPage.getMaxItems();
				logger.info("setFirstResult-------"+itemIndex);
				List<OrderCustomer> filterComapnyList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(OrderCustomer.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					filterComapnyList = criteria.list();
					logger.info("filterComapnyList size------"+filterComapnyList.size());					
				}
			 
				 if(filterComapnyList!=null && filterComapnyList.size()>0){
					 companyFilterPage.setOrderCustomerList(filterComapnyList);
				 } 
				else
				{
					companyFilterPage.setAvailableItems(0);
					companyFilterPage.setOrderCustomerList(new ArrayList<OrderCustomer>());
				}
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setOrderCustomerList(new ArrayList<OrderCustomer>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			System.out.println("--------------HibernateException-----------------"+e.getMessage());

		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}	
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyFilterPage;

	}
	
	public OrderCustomer getCustomerDetailsByRowId(Long id) {

		// TODO Auto-generated method stub
		 Session session= null;
		 OrderCustomer  reportData=null; 
		try{ 
			 session = HibernateUtil.getSessionFactory().openSession();
			   Criteria criteria=session.createCriteria(OrderCustomer.class);
			   criteria.add(Restrictions.eq("id", id));
			   reportData=(OrderCustomer) criteria.uniqueResult();  
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return reportData;
	
	}	
	
	
public OrderCustomer updateCustomerDetailsByRowId(OrderCustomer orderCustomerOld) { 
	Transaction transaction = null;
	Session session= null;
	OrderCustomer orderCustomerNew=null;
	try
	{
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		orderCustomerNew = (OrderCustomer) session.get(OrderCustomer.class, orderCustomerOld.getId());
		orderCustomerNew.setTitle(orderCustomerOld.getTitle());
		orderCustomerNew.setFirstName(orderCustomerOld.getFirstName());
		orderCustomerNew.setLastName(orderCustomerOld.getLastName());
		orderCustomerNew.setBirthday(orderCustomerOld.getBirthday());
		orderCustomerNew.setAge(orderCustomerOld.getAge());
		orderCustomerNew.setAddress(orderCustomerOld.getAddress2());
		orderCustomerNew.setAddress(orderCustomerOld.getAddress());
		
		orderCustomerNew.setEmail(orderCustomerOld.getEmail());
		orderCustomerNew.setZip(orderCustomerOld.getZip());
		orderCustomerNew.setCity(orderCustomerOld.getCity());
		orderCustomerNew.setMobile(orderCustomerOld.getMobile());
		orderCustomerNew.setPhone(orderCustomerOld.getPhone());
		orderCustomerNew.setGender(orderCustomerOld.getGender());
		orderCustomerNew.setState(orderCustomerOld.getState());
		orderCustomerNew.setCountryId(orderCustomerOld.getCountryId());
		session.saveOrUpdate(orderCustomerNew);
		transaction.commit();
	}
	catch (Exception e) {
		if(transaction!=null){
			transaction.rollback();
		}
		logger.error("-------------Exception-----------------"+e.getMessage());
	}

		finally {
			if(session != null && session.isOpen())
				session.close(); 
	}
	return orderCustomerNew; 
	}
 
public  List<String> getCompanyIdList(Company company, int reportType, String companyPreferable)
{
	List<String> companyIdList = new ArrayList<String>();
	Session session = null;
	try{			
		session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Company.class);
		Conjunction reportConjunction = Restrictions.conjunction();
		Disjunction reportDisjunction = Restrictions.disjunction();
		// To get total row count.
		List<Company> list = null;
		switch (reportType){
		case FlightReportFilter.REPORTS_MINE:
			reportConjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{
				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}

			criteria.add(reportConjunction);
			list = criteria.list();
			break;			
		case FlightReportFilter.REPORTS_ALL:
			if(!company.getCompanyRole().isAgent() && !company.getCompanyRole().isDistributor()&& !company.getCompanyRole().isCorporate()){
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));	
			}
			else{
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
			}
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			list = criteria.list();
			break;	

		case FlightReportFilter.REPORTS_MY_AFFILIATES:
			reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			list = criteria.list();
			break;

		case FlightReportFilter.REPORTS_MY_AGENCIES:
			logger.info("reportType---------"+reportType);


			reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
			list = criteria.list();
			//reportConjunction.add(Restrictions.eq("companyRole.isAgent",true));
			break;
		case FlightReportFilter.REPORTS_MY_DISTRIBUTORS:
			//For direct distributors...
			reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
			list = criteria.list();
			//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
			break;
		case FlightReportFilter.REPORTS_MY_CORPORATES:
			//For direct distributors...
			reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
			//reportDisjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{
				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate",true));
			list = criteria.list();
			//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
			break;

		case FlightReportFilter.REPORTS_ALL_AFFILIATES:
			reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			list = criteria.list();
			break;

		case FlightReportFilter.REPORTS_ALL_AGENCIES:
			logger.info("reportType---------"+reportType);

			reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
			list = criteria.list();
			//reportConjunction.add(Restrictions.eq("companyRole.isAgent",true));
			break;
		case FlightReportFilter.REPORTS_ALL_DISTRIBUTORS:
			//For direct distributors...
			reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
			list = criteria.list();
			//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
			break;


		case FlightReportFilter.ORDERS_ALL:
			reportConjunction.add(Restrictions.eq("companyid",company.getCompanyid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			list = criteria.list();
			break;		


		default:	
			companyIdList.add(String.valueOf(company.getCompanyid()));
			reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
			if((companyPreferable != null && companyPreferable.length()  > 0 ))
			{

				reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
				reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
				reportConjunction.add(reportDisjunction);
			}
			criteria.add(reportConjunction);
			list = criteria.list();
			break;
		}				
		criteria.add(reportConjunction);
		//List<Company> list = criteria.list();
		logger.error("--------------probable Company list -----------------"+list);
		if(list!=null && list.size()>0)
		{
			logger.error("--------------probable Company list size-----------------"+list.size());
			for (Company companyChild :list)
			{
				companyIdList.add(String.valueOf(companyChild.getCompanyid()));
			}						
		}		
	}
	catch (HibernateException e) {
		logger.error("--------------HibernateException-----------------"+e.getMessage());
	}
	catch (Exception e) {
		logger.error("--------------Exception-----------------"+e.getMessage());
	}
	finally {
		if(session!=null && session.isOpen())
			session.close(); 
	}
	//logger.info("--------reportData_list size-------"+reportData_list.size());
	return companyIdList;
}
}
