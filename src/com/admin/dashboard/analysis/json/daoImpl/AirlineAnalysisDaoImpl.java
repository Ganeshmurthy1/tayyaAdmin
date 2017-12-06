package com.admin.dashboard.analysis.json.daoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.admin.dashboard.analysis.json.dao.AirlineAnalysisDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class  AirlineAnalysisDaoImpl implements  AirlineAnalysisDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirlineAnalysisDaoImpl.class);
	@Override
	public List<String> getAirlineBookedCount(Company company) {
		// TODO Auto-generated method stub
				Session session=null;
				Criteria criteria=null;
				List<String> airlineList=null;
				try{
					if(company!=null && company.getCompanyRole()!=null){
						session = HibernateUtil.getSessionFactory().openSession();
						criteria= session.createCriteria(FlightOrderRow.class); 
						if(!company.getCompanyRole().isSuperUser()) 
							criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
						criteria.setProjection(Projections.property("airline"));  
						airlineList=criteria.list();
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

				return airlineList;
		 
	}
	@Override
	public List<String> getAirApiProviderBookingCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String> airlineList=null;
		try{
			if(company!=null && company.getCompanyRole()!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(FlightOrderRow.class); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("providerAPI"));  
				airlineList=criteria.list();
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

		return airlineList;
	}
	@Override
	public List<String> getAirDestinationBookingCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String>  destinationList=null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(FlightOrderRow.class).createAlias("flightOrderTripDetails", "tripList"); 
			 if(!company.getCompanyRole().isSuperUser()) 
				 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("tripList.destinationName"));
				/* criteria.setResultTransformer(new AliasToBeanResultTransformer(String.class));*/
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
	public List<FlightOrderRow>  getAirlineWeeklySales(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<FlightOrderRow>  flightOrderRowList= null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(FlightOrderRow.class); 
				Conjunction conjunction=Restrictions.conjunction();
				new HotelAnalysisDaoImpl().getWeeklyConjunction(conjunction);
				 if(!company.getCompanyRole().isSuperUser()) 
					 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.add(conjunction);
				flightOrderRowList =criteria.list();
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
		return flightOrderRowList;
		 
	}
	public Conjunction getDayConjunction(String presrentDate,String type){
		String startDate = "";
		String weekDate = "";
		Conjunction conjunction=Restrictions.conjunction();
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
			Date dateStart = df.parse(presrentDate);
			Calendar c = Calendar.getInstance();
			c.setTime(dateStart);
			Date start = c.getTime();
			if(type.equals("D") || type.equals("W")) 
				c.add(Calendar.DATE, -6);
			if(type.equals("M") || type.equals("Y")) 
				c.add(Calendar.DATE, -364);
			Date end = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			startDate = sdf.format(start);
			weekDate = sdf.format(end);
			DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			try{
				Date date = originalFormat.parse(startDate);
				String formattedDate = targetFormat.format(date); 
				date = targetFormat.parse(formattedDate);
				date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
				conjunction.add(Restrictions.le("createdAt", date));

			}catch(Exception ex)
			{
				logger.info("##########date min format exception---"+ex.getMessage());

			}
			try{
				Date date = originalFormat.parse(weekDate);
				String formattedDate = targetFormat.format(date); 
				date = targetFormat.parse(formattedDate);
				date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
				conjunction.add(Restrictions.ge("createdAt", date));

			}catch(Exception ex)
			{
				logger.info("##########date max format exception---"+ex.getMessage());
			}


		} catch (Exception e) {
			logger.info("Exception::--"+e.getMessage());
		}

		return conjunction;
	}
	@Override
	public List<FlightOrderRow> getDWMYBookings(Company company, String presentDate,String type) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<FlightOrderRow>  flightOrderRowList=new ArrayList<>();
		try{
		 
			 Conjunction conjunction=getDayConjunction(presentDate,type);
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(FlightOrderRow.class);
			 if(company!=null && company.getCompanyRole()!=null &&  !company.getCompanyRole().isSuperUser()) 
				 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
			 criteria.add(conjunction);
			flightOrderRowList=criteria.list();
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
		return flightOrderRowList;
	}
	
	 
}
