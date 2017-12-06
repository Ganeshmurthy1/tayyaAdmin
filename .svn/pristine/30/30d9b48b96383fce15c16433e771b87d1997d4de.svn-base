package com.admin.whitelabel.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.admin.whitelabel.model.CompanyTheme;
import com.admin.whitelabel.model.ThemeAuthRequestModel;
import com.admin.whitelabel.model.ThemeInsertionRequestModel;
import com.admin.whitelabel.service.WhitelabelService;
import com.lintas.config.HibernateUtil;


public class WhiteLabelDao {

	WhitelabelService whitelabelService=new WhitelabelService();
	
	
	public Boolean insertIntoCompanyTheme(ThemeInsertionRequestModel insertionRequestModel){
		Session session=null;
		Transaction transaction=null;
		Boolean bool=false;
		CompanyTheme companyTheme=new CompanyTheme();
		try{
			companyTheme= whitelabelService.insertOrUpdateValuesIntoTable(insertionRequestModel,companyTheme);
				session=HibernateUtil.getSessionFactory().openSession();
				transaction=session.beginTransaction();
				session.save(companyTheme);
				transaction.commit();
				bool=true;
		}catch (Exception e) {
			transaction.rollback();
			bool=false;
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
			if(transaction.isActive())
				transaction.setTimeout(0);
		}
		return bool;
	}
	
	public CompanyTheme fetchCompanyThemeDetailsByAuthId(ThemeAuthRequestModel authRequestModel){
		
		Session session=null;
		Criteria criteria=null;
		CompanyTheme companyTheme=null;
		try{
			companyTheme=new CompanyTheme();
			session=HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(CompanyTheme.class);
			companyTheme=whitelabelService.fetchByAuthCriteria(companyTheme,criteria,authRequestModel);
		}catch (Exception e) {
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyTheme;
	}
	
	public CompanyTheme editCompanyThemeDetails(int configId){
		Session session=null;
		Criteria criteria=null;
		CompanyTheme companyTheme=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(CompanyTheme.class);
			criteria.add(Restrictions.eq("configId", configId));
			companyTheme=(CompanyTheme) criteria.uniqueResult();
			
		}catch (Exception e) {
			companyTheme=null;
		}	finally {
				if(session != null && session.isOpen())
					session.close(); 
			}
		return companyTheme;
	}
	
	public CompanyTheme updateCompanyThemeDetails(ThemeInsertionRequestModel requestModel){
		Session session=null;
		Transaction transaction=null;
		CompanyTheme companyTheme=new CompanyTheme();
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			companyTheme=(CompanyTheme) session.get(CompanyTheme.class, Long.valueOf(requestModel.getId()));
			companyTheme= whitelabelService.insertOrUpdateValuesIntoTable(requestModel,companyTheme);
			transaction=session.beginTransaction();
			session.save(companyTheme);
			transaction.commit();
			
		}catch (Exception e) {
			transaction.rollback();
			companyTheme=null;
		}	finally {
				if(session != null && session.isOpen())
					session.close(); 
				if(transaction.isActive())
					transaction.setTimeout(0);
			}
		return companyTheme;
	}
	
	/*---------*/
	public CompanyTheme verifyingUserWhitelabelExistence(ThemeAuthRequestModel authRequestModel){
		
		Session session=null;
		Criteria criteria=null;
		CompanyTheme companyTheme=null;
		try{
			companyTheme=new CompanyTheme();
			session=HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(CompanyTheme.class);
			companyTheme=whitelabelService.verifyingUserWhitelabelExistence(companyTheme,criteria,authRequestModel);
		}catch (Exception e) {
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyTheme;
	}
}
