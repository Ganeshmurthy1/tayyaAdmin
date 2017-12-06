package com.lintas.admin.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;

public class ChildOrParentCompanyDao {
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ChildOrParentCompanyDao.class);
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	/*Check company name exists in DB */
	public boolean isExisted(Company companyReg)
	{
		
		boolean isExists = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			 
			String sql = "from Company com  where com.Email=:email" + companyReg.getEmail() + "'";
		 Query query = session.createQuery(sql);
		 query.setParameter("email", companyReg.getEmail());
			 Company company = (Company) query.uniqueResult();
			if (company!=null && company.getEmail()!=null) {
				isExists = true;
			}
			 
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			 session.close(); 
		 }
		return isExists;
	}

	public boolean insertChildOrParentCompanyDetails(Company  companyReg){
		boolean isUpdate=false;
		String company_userid ="";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			int companyId=(Integer)session.save(companyReg);
			logger.info("-------company ID--------...."+companyId);
			company_userid=companyReg.getCompanyname().substring(0,2).toUpperCase()+companyId;
			logger.info("------------company_userid........."+company_userid);

			isUpdate = updateCompanyUserId(company_userid, companyId, session);
			transaction.commit();
			/* session.close();*/
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			session.close(); 
		}
		return isUpdate;
	}

	public boolean updateCompanyUserId(String company_userid,int companyId,Session session)
	{
		boolean isUpdate=false;
		try{
			if(company_userid!=null || company_userid!="" ){	
				Company companyupdate = (Company) session.get(Company.class,companyId);
				companyupdate.setCompany_userid(company_userid);
				session.update(companyupdate);
				isUpdate=true;
			}
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
			isUpdate=false;
		}
		 return isUpdate;
 }


	public List<Company> getLintasCompanyList(){
		List<Company> companyLi=null;
		String sql = "from Company";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			 Query query = session.createQuery(sql);
			 companyLi = query.list();
		 }catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			session.close(); 
		}
		return companyLi;

	}

	/* method for approval the company  */
	public  Company  updateCompanyApproval(Company c)
	{
		Company  status= new  Company();
		try{
		 if(c!=null){
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			Company config =  (Company) session.get(Company.class,c.getCompanyid());
			config.setModifieddate(new Date());
			config.setStatus(c.isStatus());
			status.setStatus(config.isStatus());
			status.setEmail(config.getEmail());
			status.setWebsite(config.getWebsite());
			status.setPassword(config.getPassword());
			status.setCompanyid(config.getCompanyid());
			session.update(config);
			transaction.commit();
			/*session.close();*/
		}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			session.close(); 
		}
		return status;
	}



	/*Check Email and password for login*/
	public  Company  parentOrChildCompanyLogin(String email, String password,String companyId)
	{
		Company  companyLogin=null;
		try{
		 session = HibernateUtil.getSessionFactory().openSession();		
		String sql = "from Company com where com.Email=:email and com.Password=:password and com.company_userid= companyuserid";
		 Query query = session.createQuery(sql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		query.setParameter("companyuserid", companyId);
		companyLogin = (Company)query.uniqueResult();
	/*	session.close();*/
		}catch (HibernateException e) {
			 
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			session.close(); 
		}
		return 	companyLogin;
	}

 }
