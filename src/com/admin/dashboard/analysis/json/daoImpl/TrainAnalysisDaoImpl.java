package com.admin.dashboard.analysis.json.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.dashboard.analysis.json.dao.TrainAnalysisDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.config.HibernateUtil;

public class TrainAnalysisDaoImpl implements TrainAnalysisDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainAnalysisDaoImpl.class);
	@Override
	public List<String> getTrainApiProviderCount(Company company) {
		Session session=null;
		Criteria criteria=null;
		List<String> trainProviderList=null;	
		try{
			if(company!=null && company.getCompanyRole()!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(TrainOrderRow.class); 
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
	public List<TrainOrderRow> getTrainWeeklySales(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<TrainOrderRow>  trainOrderRowList= null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(TrainOrderRow.class); 
				Conjunction conjunction=Restrictions.conjunction();
				new HotelAnalysisDaoImpl().getWeeklyConjunction(conjunction);
				 if(!company.getCompanyRole().isSuperUser()) 
					 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				 	criteria.add(conjunction);
				 	trainOrderRowList =criteria.list();
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
		return trainOrderRowList;
	}
	@Override
	public List<String> getTrainDestinationBookingCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String>  destinationList=null;
		List<Long>  trainOrderRowIdList=null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(TrainOrderRow.class); 
			 if(!company.getCompanyRole().isSuperUser()) 
				 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("id"));
				trainOrderRowIdList=criteria.list();
		} 
		 if(trainOrderRowIdList!=null && trainOrderRowIdList.size()>0){
			 criteria= session.createCriteria(TrainTravelRequestQuotation.class); 
			 criteria.add(Restrictions.in("id", trainOrderRowIdList));
			 criteria.setProjection(Projections.property("Tolocation"));
			 destinationList=criteria.list();
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

		return destinationList;
	}
}
