package com.lintas.admin.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.HotelMarkup;
import com.lintas.config.HibernateUtil;

public class HotelMarkupDao {
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelMarkupDao.class);

	/*Insert the Hotel Markup details into the database*/
	public Integer insertMarkupDetails(HotelMarkup hm) throws Exception
	{
		int result = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			result=(Integer)session.save(hm);
			hm.setCheckIn("checkIn");
			hm.setCheckOut("checkOut");
			hm.setCompanyUserId("RA1");
			transaction.commit();
			/*session.close();*/
		} catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
			session.close(); 
		}

		return result;
	}

	/*get hotel Markup List details*/
	public List<HotelMarkup> getHotelMarkupList(Company companyObj)
	{
		List<HotelMarkup> hotelMarkupList=null;
		 List<Company> companyList=new CompanyDAO().getAllComapnyIdsByCompanyUserId(companyObj.getCompany_userid());
		 List<Integer> newCompanyIds=new ArrayList<>();
			if(companyList!=null && companyList.size()>0){
				for(Company  obj:companyList){
					newCompanyIds.add(obj.getCompanyid());
				 }
			}
		 try
		{
			if(companyList!=null && companyList.size()>0){
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="from HotelMarkup hm where hm.createdbyCompanyId in (:companyIds) order by hm.id desc";
			logger.info("--------hotel markup lisy query------------"+sql);
			 Query query = session.createQuery(sql);
			query.setParameterList("companyIds", newCompanyIds);
			hotelMarkupList = query.list();
			}
		 }
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
			session.close(); 
		}
		return hotelMarkupList;
	}


	public HotelMarkup getHotelMarkupDetails(HotelMarkup hm ){
		HotelMarkup markup=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelMarkup hm where hm.id =:id"; 
			 Query query = session.createQuery(sql);
			query.setParameter("id", hm.getId());
			markup = (HotelMarkup)query.uniqueResult();
			 
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
			session.close(); 
		}

		return markup;


	}
 /*Update markup using congig Id details*/
	public boolean updateHotelMarkup(HotelMarkup newobj)
	{ 
		boolean updated = false;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			HotelMarkup config =  (HotelMarkup) session.get(HotelMarkup.class,newobj.getId());
			config.setIsaccumulative(newobj.getIsaccumulative());
			config.setHotelChain(newobj.getHotelChain());
			config.setHotelCheckinDate(newobj.getHotelCheckinDate());
			config.setHotelCheckoutDate(newobj.getHotelCheckoutDate());
			config.setHotelCity(newobj.getHotelCity());
			config.setHotelCountry(newobj.getHotelCountry());
			config.setHotelName(newobj.getHotelName());
			config.setIsfixedAmount(newobj.getIsfixedAmount());
			config.setMarkupAmount(newobj.getMarkupAmount());
			config.setName(newobj.getName());
			config.setPositionMarkup(newobj.getPositionMarkup());
			config.setModifiedDate(new Date());
			config.setPromofareStartDate(newobj.getPromofareStartDate());
			config.setPromofareEndDate(newobj.getPromofareEndDate());
			config.setDestinationType(newobj.getDestinationType());
			session.update(config);
			transaction.commit();

			updated = true;
		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();;
				logger.error("--------------HibernateException-----------------"+e.getMessage());
				return updated;
		}finally {
			if(session!=null && session.isOpen())
			session.close(); 
		}

		return updated;

	}

	/*delete CompanyMarkup List details*/
	public boolean deleteHotelMarkupDetails(int id){
		boolean isDelete=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from HotelMarkup hm where hm.id =:id"; 
			 Query query = session.createQuery(sql);
			 query.setParameter("id", id);
			int result = query.executeUpdate();
			if (result> 0) {
				isDelete = true;
			}
			transaction.commit();

		}catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return isDelete;
		}finally {
			if(session!=null && session.isOpen())
			session.close(); 
		}
		return isDelete;

	}

	/*fetch hotel markup  list  by passing Company_user_id*/
	public List<HotelMarkup> getHotelMarkupListByCompanyUserId(String companyUserId){
		List<HotelMarkup> flightMarkupList =null;
		 List<Company> companyIdList=new CompanyDAO().getAllComapnyIdsByCompanyUserId(companyUserId);
		 List<Integer> newCompanyIds=new ArrayList<>();
			if(companyIdList!=null && companyIdList.size()>0){
				for(Company  obj:companyIdList){
					newCompanyIds.add(obj.getCompanyid());
				 }
			}
		try{
			if(newCompanyIds!=null && newCompanyIds.size()>0){
				String sql="from HotelMarkup hm where hm.createdbyCompanyId in (:companyIds)";
				logger.info("-----Filter hotel_markup- query---"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				 Query query = session.createQuery(sql);
				query.setParameterList("companyIds", newCompanyIds);
				flightMarkupList = query.list();
			}
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session!=null && session.isOpen())
			session.close();
		}
		return flightMarkupList;
	}
	

	/*fetch hotel markup list by passing user id*/
	public List<HotelMarkup> getHotelMarkupListByUserId(String userId){
		String sql="";
		List<HotelMarkup> hotelMarkupList=null;
		try{
			if(userId!=null){
				sql = "from HotelMarkup hm where hm.createdbyUserId=:createdby_userId";
				logger.info("----------Filter hotel_markup-------"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				 Query query = session.createQuery(sql);
				query.setParameter("createdby_userId", userId);
				hotelMarkupList = query.list();
			}
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		} 
		finally{
			if(session!=null && session.isOpen())
			session.close();
		}
		return hotelMarkupList;
	}
 }
