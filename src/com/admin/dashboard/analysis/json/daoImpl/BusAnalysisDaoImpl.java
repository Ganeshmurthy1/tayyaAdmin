package com.admin.dashboard.analysis.json.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.analysis.json.dao.BusAnalysisDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;

public class BusAnalysisDaoImpl implements  BusAnalysisDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BusAnalysisDaoImpl.class);
	@Override
	public List<String> getBusCompanyNameCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String> busCompanyNameList=null;	
		try{
			if(company!=null && company.getCompanyRole()!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(BusOrderRow.class); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("supplierName"));  
				busCompanyNameList=criteria.list();
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

		return busCompanyNameList;
	}
	@Override
	public List<String> getBusDestinationBookingCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String>  destinationList=null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(BusOrderRow.class); 
			 if(!company.getCompanyRole().isSuperUser()) 
				 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("destination"));
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
	@Override
	public List<BusOrderRow> getBusWeeklySales(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<BusOrderRow>  busOrderRowList= null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(BusOrderRow.class); 
				Conjunction conjunction=Restrictions.conjunction();
				new HotelAnalysisDaoImpl().getWeeklyConjunction(conjunction);
				 if(!company.getCompanyRole().isSuperUser()) 
					 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				 	criteria.add(conjunction);
				 	busOrderRowList =criteria.list();
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
		return busOrderRowList;
	}
}
