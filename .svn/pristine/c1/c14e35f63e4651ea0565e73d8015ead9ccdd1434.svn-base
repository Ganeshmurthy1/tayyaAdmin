package com.admin.dashboard.analysis.json.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.analysis.json.dao.InsuranceAnalysisDao;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.config.HibernateUtil;

public class InsuranceAnalysisDaoImpl implements InsuranceAnalysisDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InsuranceAnalysisDaoImpl.class);
	@Override
	public List<String> getInsuranceApiProviderCount(Company company) {
		Session session=null;
		Criteria criteria=null;
		List<String> trainProviderList=null;	
		try{
			if(company!=null && company.getCompanyRole()!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(InsuranceOrderRow.class); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("supplierName"));  
				trainProviderList=criteria.list();
			}
		}
		catch(HibernateException he){
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return trainProviderList;
	}
	@Override
	public List<InsuranceOrderRow> getInsuranceWeeklySales(Company company) {
		Session session=null;
		Criteria criteria=null;
		List<InsuranceOrderRow>  insuranceOrderRowList= null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(InsuranceOrderRow.class); 
				Conjunction conjunction=Restrictions.conjunction();
				new HotelAnalysisDaoImpl().getWeeklyConjunction(conjunction);
				 if(!company.getCompanyRole().isSuperUser()) 
					 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				 	criteria.add(conjunction);
				 	insuranceOrderRowList =criteria.list();
			 } 
		}
		catch(HibernateException he){
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return insuranceOrderRowList;
	}
}
