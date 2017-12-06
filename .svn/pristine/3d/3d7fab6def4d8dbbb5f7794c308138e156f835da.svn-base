package com.lintas.admin.DAO;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.MailConfig;
import com.lintas.config.HibernateUtil;

public class MailConfigDao {
	/**
	@author info name:raham
		created date:27-07-2015
	 */
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MailConfigDao.class);
	public List<Company> loadApiCompanies(Company  loginCompany)
	{
		List<Company> apiCompaniesList=  null;
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			if(!loginCompany.getCompanyRole().isDistributor() && !loginCompany.getCompanyRole().isAgent()) {
				criteria.add(Restrictions.eq("super_company_userid", loginCompany.getCompany_userid()));
				criteria.createCriteria("bookingSystemType").add(Restrictions.eq("isAPI",true));
			}
			criteria.addOrder(Order.desc("companyid"));
			apiCompaniesList=criteria.list();
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
		return apiCompaniesList;
	}
	/*Insert the MailConfig details in the database*/
	public void insertMailConfigData(MailConfig mcad) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(mcad);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
	}

	/*Check MailHos exists or not  in DB */
	public boolean isMailHostExists(MailConfig mcad) throws Exception
	{
		boolean exists = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			String sql = "from mail_config ma where ma.mail_server_host='"+mcad.getServerHost()+"' and  ma.mailServerPort='"+mcad.getServerPort()+"'"; 
			SQLQuery query = session.createSQLQuery(sql);
			List<MailConfig> list = query.list();
			if (list.size() > 0) {
				exists = true;
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			return exists;
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return exists;
	}

	/* delete MailConfig details from DB if existed...*/
	public boolean deleteMailConfigData(MailConfig mcad) throws Exception
	{
		boolean isDelete = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "DELETE FROM  mail_config   WHERE    mail_server_host ='"+mcad.getServerHost()+"' and  mailServerPort='"+mcad.getServerPort()+"'";
			SQLQuery query = session.createSQLQuery(sql);
			int result=query.executeUpdate();
			if (result> 0) {
				isDelete = true;
			}
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isDelete;
	}

	public MailConfig getMailConfigDetails(MailConfig  mc ){

		MailConfig mcad=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();

			String sql = "select * from mail_config where  id ='"+mc.getId()+"'" ; 
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(MailConfig.class);
			List<MailConfig> list = query.list();
			for (Iterator<MailConfig> iterator = list.iterator(); iterator.hasNext();){
				mcad = (MailConfig)iterator.next(); 
				mcad.setId(mcad.getId());
				mcad.setServerHost(mcad.getServerHost());
				mcad.setServerPassword(mcad.getServerPassword());
				mcad.setServerUser(mcad.getServerUser());
				mcad.setServerPort(mcad.getServerPort());
				mcad.setCompanyId(mcad.getCompanyId());
				mcad.setMailConfigName(mcad.getMailConfigName());
				 
			}		


		}catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
			
		}
		return mcad;


	}
	/*get MAil config List details*/
	public List<MailConfig> getMailConfigList()
	{
		List<MailConfig> configLi=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			configLi = session.createQuery("FROM mail_config").list(); 
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return configLi;

	}



}
