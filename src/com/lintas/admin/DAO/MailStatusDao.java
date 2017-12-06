package com.lintas.admin.DAO;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lintas.admin.model.MailStatus;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;

public class MailStatusDao {


	/**
	 * @author info
	 * name:raham
	 * date:24-07-2015
	 * time:8:15PM
	 * 
	 */

	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MailStatusDao.class);
	/*Insert the  MailStatus details in to database*/
	public int insertMailStatus(MailStatus  status) 
	{
		 
		int id=0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			id=(Integer) session.save(status);
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
			session.close(); 
		}
		return id;
	}
	//this method for fetching userId  from database for mailStatus 
	public int getUserDetailsForMailStatus(String email)
	{
		User user = null;
		int Userid = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();	
			 
			String sql = "select *  from user u where u.email='" +email+ "'";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			List list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
				user = (User)iterator.next(); 
				Userid = user.getId();

			}	
			 
		}
		catch (HibernateException e) {
			 logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
			session.close(); 
		}
		return Userid;
	}

}



