package com.admin.insurance.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.admin.insurance.model.TrawellTagCategory;
import com.admin.insurance.model.TrawellTagCountries;
import com.admin.insurance.model.TrawellTagPlan;
import com.admin.insurance.model.TrawellTagPremiumChart;
import com.lintas.config.HibernateUtil;

public class TrawellTagInsuranceDao  {
	public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TrawellTagInsuranceDao.class);
	
	public List<TrawellTagCategory> insertTrawellTagCategory(List<TrawellTagCategory> trawellTagCategoryList){
		Session session = null;
		Transaction transaction = null;
		List<TrawellTagCategory> trawellTagCategoryListUpdated = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (TrawellTagCategory trawellTagCategory : trawellTagCategoryList) {
				session.save(trawellTagCategory);
				trawellTagCategoryListUpdated.add(trawellTagCategory);
			}			
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			log.error("--------------insertTrawellTagCategory HibernateException-----------------"+e.getMessage());
			
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return trawellTagCategoryListUpdated;
	}
	public List<TrawellTagCountries> insertTrawellTagCountries(List<TrawellTagCountries> trawellTagCountriesList){
		Session session = null;
		Transaction transaction = null;
		List<TrawellTagCountries> trawellTagCountriesListUpdated = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (TrawellTagCountries trawellTagCountries : trawellTagCountriesList) {
				session.save(trawellTagCountries);
				trawellTagCountriesListUpdated.add(trawellTagCountries);
			}			
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			log.error("--------------insertTrawellTagCountries HibernateException-----------------"+e.getMessage());
			
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return trawellTagCountriesListUpdated;
	}
	public List<TrawellTagPlan> insertTrawellTagPlan(List<TrawellTagPlan> trawellTagPlanList){
		Session session = null;
		Transaction transaction = null;
		List<TrawellTagPlan> trawellTagPlanListUpdated = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (TrawellTagPlan trawellTagPlan : trawellTagPlanList) {
				session.save(trawellTagPlan);
				trawellTagPlanListUpdated.add(trawellTagPlan);
			}			
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			log.error("--------------insertTrawellTagCountries HibernateException-----------------"+e.getMessage());
			
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return trawellTagPlanListUpdated;
	}
	public List<TrawellTagPremiumChart> insertTrawellTagPremiumChart(List<TrawellTagPremiumChart> trawellTagPremiumChartList){
		Session session = null;
		Transaction transaction = null;
		List<TrawellTagPremiumChart> trawellTagPremiumChartListUpdated = new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (TrawellTagPremiumChart trawellTagPremiumChart : trawellTagPremiumChartList) {
				session.save(trawellTagPremiumChart);
				trawellTagPremiumChartListUpdated.add(trawellTagPremiumChart);
			}			
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			log.error("--------------insertTrawellTagCountries HibernateException-----------------"+e.getMessage());
			
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return trawellTagPremiumChartListUpdated;
	}
}
