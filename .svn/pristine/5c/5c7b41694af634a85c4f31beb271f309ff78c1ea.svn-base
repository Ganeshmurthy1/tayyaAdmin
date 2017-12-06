package com.admin.dashboard.analysis.json.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.analysis.json.dao.VisaAnalysisDao;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;

public class VisaAnalysisDaoImpl  implements VisaAnalysisDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(VisaAnalysisDaoImpl.class);
	@Override
	public List<String> getVisaApiProviderCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String> visaProviderList=null;	
		try{
			if(company!=null && company.getCompanyRole()!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(VisaOrderRow.class); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("supplierName"));  
				visaProviderList=criteria.list();
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
		return visaProviderList;
	}
	@Override
	public List<VisaOrderRow> getVisaWeeklySales(Company company) {
		Session session=null;
		Criteria criteria=null;
		List<VisaOrderRow> visaOrderRowList= null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(VisaOrderRow.class); 
				Conjunction conjunction=Restrictions.conjunction();
				new HotelAnalysisDaoImpl().getWeeklyConjunction(conjunction);
				 if(!company.getCompanyRole().isSuperUser()) 
					 criteria.add(Restrictions.eq("companyId",  String.valueOf(company.getCompanyid())));
				 	criteria.add(conjunction);
				 	visaOrderRowList =criteria.list();
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
		return visaOrderRowList;
	}
}
