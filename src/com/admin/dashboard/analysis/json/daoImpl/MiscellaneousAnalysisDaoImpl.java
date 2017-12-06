package com.admin.dashboard.analysis.json.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.analysis.json.dao.MiscellaneousAnalysisDao;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.config.HibernateUtil;

public class MiscellaneousAnalysisDaoImpl implements MiscellaneousAnalysisDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MiscellaneousAnalysisDaoImpl.class);
	@Override
	public List<String> getMiscellaneousApiProviderCount(Company company) {
		Session session=null;
		Criteria criteria=null;
		List<String> trainProviderList=null;	
		try{
			if(company!=null && company.getCompanyRole()!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(MiscellaneousOrderRow.class); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", company.getCompanyid()));
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
	public List<MiscellaneousOrderRow> getMiscellaneousWeeklySales(Company company) {
		Session session=null;
		Criteria criteria=null;
		List<MiscellaneousOrderRow>  miscellaneousOrderRowList= null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(MiscellaneousOrderRow.class); 
				Conjunction conjunction=Restrictions.conjunction();
				new HotelAnalysisDaoImpl().getWeeklyConjunction(conjunction);
				 if(!company.getCompanyRole().isSuperUser()) 
					 criteria.add(Restrictions.eq("companyId",  company.getCompanyid()));
				 	criteria.add(conjunction);
				 	miscellaneousOrderRowList =criteria.list();
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
		return miscellaneousOrderRowList;
	}
 
}
