package com.tayyarah.notification.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.tayyarah.notification.Notification;
import com.tayyarah.notification.NotificationDetail;
import com.tayyarah.notification.NotificationStatusEnum;

public class NotificationDao {
	/**
	 * @author Ramesh
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(NotificationDao.class);
	
	public Notification insert(Notification notification){
		Session session=null;
		Transaction tx=null;
		try{
			if(notification!=null){
				session=HibernateUtil.getSessionFactory().openSession();				
				if(notification.getDetails() != null)
				{
					tx = session.beginTransaction();		
					for (NotificationDetail notificationDetail : notification.getDetails()) {
						notificationDetail.setNotification(notification);
						notificationDetail.setCreatedAt(new Timestamp(new Date().getTime()));
						notificationDetail.setUpdatedAt(new Timestamp(new Date().getTime()));

						session.save(notificationDetail);
					}					
					logger.info("############# browsingHistoryDetail saved----------------------------");
					tx.commit();
				}//session.save(browsingHistory);
				tx = session.beginTransaction();
				notification.setStatusId(NotificationStatusEnum.STATUS_PENDING.getCode());
				notification.setCreatedAt(new Timestamp(new Date().getTime()));
				notification.setUpdatedAt(new Timestamp(new Date().getTime()));
				//pls add the below line without which api cannot call syatem notification
				//notification.setCustomFlag(false);


				session.save(notification); 
				logger.info("############# browsingHistory saved----------------------------");
				tx.commit();
			}
			logger.info("committed------------------------");
		}
		catch(HibernateException he){
			logger.info("HibernateException "+he);
			he.printStackTrace();
			if(tx!=null) 
				tx.rollback();
			//throw new RuntimeException("DB is not connecting ,please make connections properly.");
		}
		finally {
			if(session!=null)
				session.close();
		}
		return notification;		
	}
	public List<Notification> getNotifications(NotificationStatusEnum statusEnum, Integer companyId, Integer userId){
		List<Notification> list = new ArrayList<Notification>();		
		Session session=null;		
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Notification.class);		
			Conjunction filters = Restrictions.conjunction();
			filters.add(Restrictions.eq("companyId", companyId));
			filters.add(Restrictions.eq("userId", userId));			
			filters.add(Restrictions.eq("statusId", statusEnum.getCode()));			
			criteria.add(filters);
			criteria.addOrder( Order.desc("createdAt") );
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException "+he);
			he.printStackTrace();			
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	public List<Notification> getNotificationsList(long notifyId){
		List<Notification> list = new ArrayList<Notification>();		
		Session session=null;		
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Notification.class);		
			Conjunction filters = Restrictions.conjunction();
			filters.add(Restrictions.eq("id", notifyId));					
			criteria.add(filters);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			list=criteria.list();
		}
		catch(HibernateException he){
			logger.info("HibernateException "+he);
			he.printStackTrace();			
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}

	public Notification getNotifications(long notifyId){
		Notification notifyobj = new Notification();	
		Session session=null;		
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Notification.class);		
			Conjunction filters = Restrictions.conjunction();
			filters.add(Restrictions.eq("id", notifyId));					
			criteria.add(filters);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			notifyobj = (Notification) criteria.uniqueResult();
		}
		catch(HibernateException he){
			logger.info("HibernateException "+he);
			he.printStackTrace();			
		}
		finally {
			if(session!=null)
				session.close();
		}
		return notifyobj;
	}


	public boolean updateNotifications(List<Notification> list){			
		Session session=null;
		Transaction tx=null;
		boolean result = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			int count=0;
			for (Notification notification : list) {
				Criteria crit = session.createCriteria(Notification.class);			
				crit.add(Restrictions.eq("id", notification.getId()));			
				Notification notificationDb = (Notification) crit.uniqueResult();

				tx = session.beginTransaction();	
				for (NotificationDetail notificationDetail : notificationDb.getDetails()) {					
					notificationDetail.setUpdatedAt(new Timestamp(new Date().getTime()));					
					session.update(notificationDetail);
				}				
				notificationDb.setStatusId(NotificationStatusEnum.STATUS_VIEWED.getCode());				
				notificationDb.setUpdatedAt(new Timestamp(new Date().getTime()));					
				session.update(notificationDb);
				if ( ++count % 100 == 0 ) {
					session.flush();
					session.clear();
				}
				tx.commit();
				result = true;				
			}
		}
		catch(HibernateException he){
			result = false;
			logger.info("HibernateException "+he);
			he.printStackTrace();			
		}
		finally {
			if(session!=null)
				session.close();
		}
		return result;
	}



	public List<User> getParentCompanyList(List<Integer> userids) throws Exception
	{
		List<User> users = new ArrayList<User>();
		Session session = null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			Conjunction userConjunction = Restrictions.conjunction();		
			userConjunction.add(Restrictions.in("id",userids));
			criteria.add(userConjunction);
			users = criteria.list();
		}
		catch (HibernateException e) {
			logger.info("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			session.close(); 
		}		
		return users;
	}

	public int getParentUserIdLevel1(Integer integer) throws Exception
	{
		Session session = null;
		int parentUserid=0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", integer);
			User user = (User) query.uniqueResult();
			if (user!=null) {
				parentUserid=user.getCreatedbyCompanyUserId();
			}
		}
		catch (HibernateException e) {
			//logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			session.close(); 
		}
		return parentUserid;
	}

	public List<Integer> getParentUserIdLevel2(Integer integer) throws Exception
	{
		List<Integer> userIds=new LinkedList<>();
		Session session = null;
		try{
			int parentUseridLevel1=getParentUserIdLevel1(integer);
			if(integer!=parentUseridLevel1){
				userIds.add(integer);
				userIds.add(parentUseridLevel1);
			}
			else{
				userIds.add(integer);
			}
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",parentUseridLevel1);
			User user = (User) query.uniqueResult();
			if (user!=null) {
				int parentUseridLevel2=user.getCreatedbyCompanyUserId();
				if(parentUseridLevel2!=parentUseridLevel1){
					userIds.add(parentUseridLevel2);
				}
			}
		}
		catch (HibernateException e) {
			//logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			session.close(); 
		}
		Collections.reverse(userIds);
		return  userIds;
	}
	public NotificationDetail insertNotificationDetail(NotificationDetail notificationDetail){
		  Session session=null;
		  Transaction tx=null;
		  try{
		   if(notificationDetail!=null){
		    session=HibernateUtil.getSessionFactory().openSession();    
		    tx = session.beginTransaction();
		    session.save(notificationDetail); 
		    tx.commit();
		   }
		    
		  }
		  catch(HibernateException he){
		   he.printStackTrace();
		   if(tx!=null) 
		    tx.rollback();
		    
		  }
		  finally {
		   if(session!=null)
		    session.close();
		  }
		  return notificationDetail;  
		 }
	 
}
