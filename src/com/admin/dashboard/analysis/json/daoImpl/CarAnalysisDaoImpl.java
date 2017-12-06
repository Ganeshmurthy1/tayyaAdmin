package com.admin.dashboard.analysis.json.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.analysis.json.dao.CarAnalysisDao;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;

public class CarAnalysisDaoImpl implements CarAnalysisDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CarAnalysisDaoImpl.class);
	@Override
	public List<String> getCarApiProviderCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String> carProviderList=null;	
		try{
			if(company!=null && company.getCompanyRole()!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(CarOrderRow.class); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("supplierName"));  
				carProviderList=criteria.list();
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

		return carProviderList;
	}
	@Override
	public List<CarOrderRow> getCarWeeklySales(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<CarOrderRow>  carOrderRowList= null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(CarOrderRow.class); 
				Conjunction conjunction=Restrictions.conjunction();
				new HotelAnalysisDaoImpl().getWeeklyConjunction(conjunction);
				 if(!company.getCompanyRole().isSuperUser()) 
					 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				 	criteria.add(conjunction);
				 	carOrderRowList =criteria.list();
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
		return carOrderRowList;
		  
	}
	@Override
	public List<String> getCarDestinationBookingCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String>  destinationList=null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(CarOrderRow.class); 
			 if(!company.getCompanyRole().isSuperUser()) 
				 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("location"));
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
