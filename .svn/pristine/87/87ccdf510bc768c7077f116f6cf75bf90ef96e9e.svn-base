package com.email.notification;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;


public class EmailNotificationDaoImp implements EmailNotificationDao {	
	public static final Logger logger = Logger.getLogger(EmailNotificationDaoImp.class);	



	@Override
	public EmailNotification insertEmailNotification(EmailNotification emailNotification, Email email) throws HibernateException{
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Timestamp updated_at = new Timestamp(new Date().getTime());	
			emailNotification.setAttemptedCount(0);

			if(email == null)
			{
				email = new Email();
				email.setAttemptedCount(1);
				email.setMailStatus(Email.STATUS_SENT_SUCCESS);
				email.setCreatedAt(updated_at);
				email.setOrderId(emailNotification.getOrderId());
				email.setStatusMsg("Internal Notification");
				email.setUpdatedAt(updated_at);
				email.setType(Email.EMAIL_TYPE_COMMISSION_NOTIFICATION);
			}
			emailNotification.setEmail(email);				
			emailNotification.setUpdatedAt(updated_at);
			emailNotification.setCreatedAt(updated_at);
			emailNotification.setStatus(Email.STATUS_PENDING);			
			tx = sess.beginTransaction();			
			sess.save(emailNotification);			
			tx.commit();
			sess.close();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.info("Exceeption---insertEmailNotification -HibernateException--"+e.getMessage());
		}
		finally
		{
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}
		}
		return emailNotification;

	}

	@Override
	public EmailNotification insertEmailNotification(int receipientCompany, int performingCompany,
			int receivingCompany, int performingUser, int actionType, int type, String orderId)
					throws HibernateException {
		EmailNotification emailNotification = new EmailNotification();	

		Integer receipientCompanyId = new Integer(receipientCompany);
		Integer performingCompanyId = new Integer(performingCompany);
		Integer receivingCompanyId = new Integer(receivingCompany);
		Integer performingUserId = new Integer(performingUser);
		emailNotification.setActionType(actionType);
		emailNotification.setRecipientCompanyId(receipientCompanyId.longValue());
		emailNotification.setReceiverCompanyId(receivingCompanyId.longValue());
		emailNotification.setReceiverUserId(performingUserId.longValue());
		emailNotification.setPerformerCompanyId(performingCompanyId.longValue());
		emailNotification.setPerformerUserId(performingUserId.longValue());
		emailNotification.setType(type);
		emailNotification.setOrderId(orderId);




		insertEmailNotification(emailNotification, null);

		return emailNotification;
	}		



}
