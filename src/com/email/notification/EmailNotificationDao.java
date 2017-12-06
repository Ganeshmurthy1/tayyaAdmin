package com.email.notification;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;


public interface EmailNotificationDao {
	public EmailNotification insertEmailNotification(EmailNotification emailNotification, Email email) throws HibernateException;
	public EmailNotification insertEmailNotification(int receipientCompany, int performingCompany,
			int receivingCompany, int performingUser, int actionType, int type, String orderId)
					throws HibernateException;
}