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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.dashboard.analysis.json.dao.HotelAnalysisDao;
import com.admin.dashboard.analysis.json.vo.CommonAnalysisVO;
import com.common.dsr.CommonDsrDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;

public class HotelAnalysisDaoImpl  implements HotelAnalysisDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelAnalysisDaoImpl.class);
	@Override
	public List<String> getHotelNameBookingCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String>  hotelList=null;
		try{
			if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(HotelOrderRow.class).createAlias("hotelOrderHotelData", "h"); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("h.name"));
				/* criteria.setResultTransformer(new AliasToBeanResultTransformer(String.class));*/
				hotelList=criteria.list();
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

		return hotelList;
	}
	@Override
	public List<String> getHotelApiProviderBookingCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<CommonAnalysisVO>  apiProviderList= null;
		List<String>  apiProviderListNew= new ArrayList<>();
		try{
			if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(HotelOrderRow.class); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				ProjectionList projectionList= Projections.projectionList();
				projectionList.add(Projections.property("apiProvoder"),"apiProvoder");
				projectionList.add(Projections.property("bookingMode"),"bookingMode");
				criteria.setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(CommonAnalysisVO.class));
				apiProviderList =criteria.list();
				if(apiProviderList!=null && apiProviderList.size()>0){
					for(CommonAnalysisVO commonAnalysisVO:apiProviderList){
						String apiProviderName="NA";
						ApiProvider apiProvoder=null;
						if(commonAnalysisVO!=null && commonAnalysisVO.getBookingMode() !=null && commonAnalysisVO.getBookingMode().equalsIgnoreCase("Offline")) 
							apiProviderName=commonAnalysisVO.getApiProvoder();
						if(commonAnalysisVO!=null && commonAnalysisVO.getBookingMode() !=null && commonAnalysisVO.getBookingMode().equalsIgnoreCase("Online")) 
							apiProvoder=new ApiProviderDao().getApiPrividerDetails(commonAnalysisVO.getApiProvoder());
						if(apiProvoder!=null) 
							apiProviderName=apiProvoder.getVendorName();
						if(apiProviderName.equals(""))
							apiProviderName="NA";
						apiProviderListNew.add(apiProviderName);

					}
				}

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

		return apiProviderListNew;
	}
	@Override
	public 	List<HotelOrderRow> getHotelWeeklySales(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<HotelOrderRow>  apiProviderList= null;
		try{
			if(company!=null && company.getCompanyRole()!=null){
				Conjunction conjunction=Restrictions.conjunction();
				getWeeklyConjunction(conjunction);
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(HotelOrderRow.class); 
				if(!company.getCompanyRole().isSuperUser()) 
					criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.add(conjunction);
				apiProviderList=criteria.list();
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

		return apiProviderList;

	}
	public Conjunction getWeeklyConjunction(Conjunction conjunction){
		String startDate = "";
		String weekDate = "";
		try {
			Date dateStart = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(dateStart);
			Date start = c.getTime();
			c.add(Calendar.DATE, -6);
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
	public List<String> getHotelDestinationBookingCount(Company company) {
		// TODO Auto-generated method stub
		Session session=null;
		Criteria criteria=null;
		List<String>  destinationList=null;
		try{
		 if(company!=null && company.getCompanyRole()!=null){ 
				session = HibernateUtil.getSessionFactory().openSession();
				criteria= session.createCriteria(HotelOrderRow.class).createAlias("hotelOrderHotelData", "cityData"); 
			 if(!company.getCompanyRole().isSuperUser()) 
				 criteria.add(Restrictions.eq("companyId", String.valueOf(company.getCompanyid())));
				criteria.setProjection(Projections.property("cityData.city"));
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


}
