package com.admin.hotel.fin.sheet.Dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.admin.hotel.fin.sheet.model.TripRequest;
import com.isl.admin.filter.ApiProviderFilter;
import com.isl.admin.page.ApiProviderPage;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
public class TripRequestDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TripRequestDao.class);
	public ApiProviderPage fetchFilterTripsList(ApiProviderPage newApiProviderPage) {
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TripRequest frm where frm.companyId=:companyId";
			Query query = null;
			ApiProviderFilter  apiProviderFilter  =null;
			User loginUser=null;
			if(newApiProviderPage!=null && newApiProviderPage.isFilterEnabled())
			{
				apiProviderFilter = newApiProviderPage.getApiProviderFilter();
				loginUser=apiProviderFilter.getLoginUser();
				if(apiProviderFilter.getCompanyId()!=0 && !apiProviderFilter.getTripNum().equals("")){
					sql=sql+"and tripId=:tripNo";
					query = session.createQuery(sql);  
					query.setParameter("companyId",Integer.valueOf(apiProviderFilter.getCompanyId()));
					query.setParameter("tripNo", Long.valueOf(apiProviderFilter.getTripNum()));
				}
				else if(apiProviderFilter.getCompanyId()==0  && !apiProviderFilter.getTripNum().equals("")){
					sql =sql+"and tripId=:tripNo";
					query = session.createQuery(sql);  
					query.setParameter("companyId",loginUser.getCompanyid());
					query.setParameter("tripNo", Long.valueOf(apiProviderFilter.getTripNum()));
				}
				else if(apiProviderFilter.getCompanyId()!=0  && apiProviderFilter.getTripNum().equals("")){
					query = session.createQuery(sql);  
					query.setParameter("companyId",apiProviderFilter.getCompanyId());

				}
				else{
					query = session.createQuery(sql);  
					query.setParameter("companyId",loginUser.getCompanyid());
				}

			}

			int rowCount = query.list().size();//(Long)session.getNamedQuery("count(*) from TripRequest frm where frm.companyId=:companyId").setParameter("companyId", loginUser.getCompanyid()).uniqueResult();
			if(rowCount>0)
			{
				if(newApiProviderPage.isPagination())
				{
					availableItems = rowCount;
					availablePages = (availableItems % newApiProviderPage.getMaxItems() == 0)?(availableItems / newApiProviderPage.getMaxItems()):((availableItems / newApiProviderPage.getMaxItems()) + 1);
					newApiProviderPage.setAvailableItems(availableItems);
					newApiProviderPage.setAvailablePages(availablePages);
				} 
				int pageIndexDb = (newApiProviderPage.getCurrentPageIndex() > 1)?newApiProviderPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * newApiProviderPage.getMaxItems();
				List<TripRequest> filterTripRequestList =  null;
				if(itemIndex <= rowCount)
				{
					query.setFirstResult(itemIndex);
					query.setMaxResults(newApiProviderPage.getMaxItems());
					filterTripRequestList = query.list();
					newApiProviderPage.setTripRequestList(filterTripRequestList);
				}
				else
				{
					newApiProviderPage.setAvailableItems(0);
					newApiProviderPage.setTripRequestList(new ArrayList<TripRequest>());
				}
			}	
			else
			{
				newApiProviderPage.setAvailableItems(0);
				newApiProviderPage.setAvailablePages(0);
				newApiProviderPage.setTripRequestList(new ArrayList<TripRequest>());
			}
		}
		catch(HibernateException e){

			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return newApiProviderPage;

	} 

	public TripRequest insertTripRequest(TripRequest tripRequest) {
		Session  session=null;
		Transaction transaction=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			session.save(tripRequest);
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}

		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return tripRequest;

	}
	public TripRequest getTripRequestById(Long id) {
		Session  session=null;
		TripRequest tripRequest=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tripRequest= (TripRequest)session.get(TripRequest.class, id);			
		}
		catch (HibernateException e) {
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return tripRequest;
	}


	public TripRequest updateTripRequestById(TripRequest tripRequestNew) {
		Session  session=null;
		TripRequest tripRequest=null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			tripRequest=(TripRequest) session.get(TripRequest.class,tripRequestNew.getId());
			tripRequest.setTripId(tripRequestNew.getTripId());
			if(tripRequest!=null)
			{
				if(tripRequestNew.getHotelTravelRequest()!=null)
					tripRequest.setHotelTravelRequest(tripRequestNew.getHotelTravelRequest());
				if(tripRequestNew.getFlightTravelRequest()!=null)
					tripRequest.setFlightTravelRequest(tripRequestNew.getFlightTravelRequest());
				if(tripRequestNew.getTrainTravelRequest()!=null)
					tripRequest.setTrainTravelRequest(tripRequestNew.getTrainTravelRequest());
				if(tripRequestNew.getCarTravelRequest()!=null)
					tripRequest.setCarTravelRequest(tripRequestNew.getCarTravelRequest());
				if(tripRequestNew.getVisaTravelRequest()!=null)
					tripRequest.setVisaTravelRequest(tripRequestNew.getVisaTravelRequest());
				if(tripRequestNew.getInsuranceTravelRequest()!=null)
					tripRequest.setInsuranceTravelRequest(tripRequestNew.getInsuranceTravelRequest());
				if(tripRequestNew.getBusTravelRequest()!=null)
					tripRequest.setBusTravelRequest(tripRequestNew.getBusTravelRequest());
				if(tripRequestNew.getMiscellaneousTravelRequest()!=null)
					tripRequest.setMiscellaneousTravelRequest(tripRequestNew.getMiscellaneousTravelRequest());
				
				session.saveOrUpdate(tripRequest);
				transaction.commit();
			}
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return tripRequest;
	}


	public boolean updateTripRequestNumber(TripRequest tripRequestNew) {
		Session  session=null;
		TripRequest tripRequest=null;
		Transaction transaction = null;
		boolean isUpdated=false;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			tripRequest=(TripRequest) session.get(TripRequest.class,tripRequestNew.getId());
			tripRequest.setTripId(tripRequestNew.getTripId());
			session.saveOrUpdate(tripRequest);
			transaction.commit();
			isUpdated=true;
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}





	public static List<Long> getHotelQuotationIdList(String orderId) throws Exception
	{
		if(orderId != null && orderId.length()>6)
		{
			String quotationIdsTxt = orderId.substring(7, (orderId.length() - 1));				
			List<Long> idList = new ArrayList<Long>();
			for (String idText : Arrays.asList(quotationIdsTxt.split(","))) {
				idList.add(Long.valueOf(idText));
			}
			return idList;
			///return new ArrayList<String>(Arrays.asList(quotationIdsTxt.split(",")));			
		}
		return new ArrayList<Long>();
	}


	public  TripRequest getTripRequestTripNumber(Long id,String travelType) {
		Session session=null;
		TripRequest tripRequest=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="";
			if(travelType.equals("F")) 
				sql= "from TripRequest com where com.flightTravelRequest.id=:id";
			else if(travelType.equals("H")) 
				sql= "from TripRequest com where com.hotelTravelRequest.id=:id";
			else if(travelType.equals("C")) 
				sql= "from TripRequest com where com.carTravelRequest.id=:id";
			else if(travelType.equals("T")) 
				sql= "from TripRequest com where com.trainTravelRequest.id=:id";
			else if(travelType.equals("V")) 
				sql= "from TripRequest com where com.visaTravelRequest.id=:id";
			else if(travelType.equals("B")) 
				sql= "from TripRequest com where com.busTravelRequest.id=:id";
			else if(travelType.equals("I")) 
				sql= "from TripRequest com where com.insuranceTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			tripRequest= (TripRequest) query.uniqueResult();
		}
		catch (HibernateException e) {
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return tripRequest;
	}


}
