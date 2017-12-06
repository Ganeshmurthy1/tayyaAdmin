package com.admin.api.provider.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.api.provider.model.ApiProvider;
import com.admin.api.provider.model.ApiProviderBluestarConfig;
import com.admin.api.provider.model.ApiProviderCommonConfig;
import com.admin.api.provider.model.ApiProviderDesiyaConfig;
import com.admin.api.provider.model.ApiProviderEtravelSmartConfig;
import com.admin.api.provider.model.ApiProviderTboConfig;
import com.admin.insurance.model.ApiProviderTrawellTagConfig;
import com.isl.admin.filter.ApiProviderFilter;
import com.isl.admin.page.ApiProviderPage;
import com.lintas.config.HibernateUtil;

public class ApiProviderDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ApiProviderDao.class);
	public ApiProvider getApiProviderDetails(String providerId,String travelType){
		Session session = null;

		ApiProvider apiProvider=null;
		String sql = "from ApiProvider ap where ap.apiProvider=:apiProvider and ap.travelType=:travelType";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("apiProvider", providerId);
			query.setParameter("travelType", travelType);
			apiProvider=(ApiProvider) query.uniqueResult();

		}
		catch(HibernateException e){

			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		catch(Exception e){
			logger.info("---------Exception-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProvider;
	}

	public ApiProvider insert(ApiProvider apiProvider){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(apiProvider);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProvider;
	}



	public ApiProviderPage fetchApiProviderList(ApiProviderPage newApiProviderPage) {
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ApiProvider.class);
			Conjunction conjunction = Restrictions.conjunction();
			ApiProviderFilter  apiProviderFilter  =null;
			if(newApiProviderPage!=null && newApiProviderPage.isFilterEnabled())
			{
				apiProviderFilter = newApiProviderPage.getApiProviderFilter();
				if(apiProviderFilter.getSupplierName() != null && !apiProviderFilter.getSupplierName().equals(""))
				{
					conjunction.add(Restrictions.eq("vendorName",apiProviderFilter.getSupplierName()));
					logger.info("##########getSupplierName  added----"+apiProviderFilter.getSupplierName());
				}

				criteria.add(conjunction);

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(newApiProviderPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % newApiProviderPage.getMaxItems() == 0)?(availableItems / newApiProviderPage.getMaxItems()):((availableItems / newApiProviderPage.getMaxItems()) + 1);
					newApiProviderPage.setAvailableItems(availableItems);
					newApiProviderPage.setAvailablePages(availablePages);
				} 
				int pageIndexDb = (newApiProviderPage.getCurrentPageIndex() > 1)?newApiProviderPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * newApiProviderPage.getMaxItems();
				logger.info("setFirstResult-------"+itemIndex);
				List<ApiProvider> filterApiProviderList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(ApiProvider.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(newApiProviderPage.getMaxItems());
					filterApiProviderList = criteria.list();

					logger.info("filterApiProviderList size------"+filterApiProviderList.size());					
					newApiProviderPage.setApiProviderList(filterApiProviderList);
				}
				else
				{
					newApiProviderPage.setAvailableItems(0);
					newApiProviderPage.setApiProviderList(new ArrayList<ApiProvider>());
				}
			}	
			else
			{
				newApiProviderPage.setAvailableItems(0);
				newApiProviderPage.setAvailablePages(0);
				newApiProviderPage.setApiProviderList(new ArrayList<ApiProvider>());
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

	public ApiProvider getApiProviderDetails(Integer id) {
		Session session = null;

		Transaction tx = null;
		ApiProvider  apiProvider=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProvider.class);
			criteria.add(Restrictions.eq("id", id));
			apiProvider=(ApiProvider) criteria.uniqueResult();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProvider;

	}

	public ApiProvider updateApiProviderDetails(ApiProvider apiProvider) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.update(apiProvider);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProvider;
	}
	public boolean updateApiProviderDetails(int id,String apiProviderId) {
		Session session = null;
		Transaction tx = null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			ApiProvider apiProvider=(ApiProvider)session.get(ApiProvider.class, id);
			apiProvider.setApiProvider(apiProviderId);
			session.saveOrUpdate(apiProvider);
			tx.commit();
			isUpdated=true;
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return isUpdated;
	}

	public boolean insertApiProviderTbo(ApiProviderTboConfig apiProviderTboConfig){
		Session session = null;
		Transaction tx = null;
		boolean isInserted=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(apiProviderTboConfig);
			tx.commit();

			if(apiProviderTboConfig!=null && apiProviderTboConfig.getId()>0) 
				isInserted=true;
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			isInserted=false;
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return isInserted;
	}
	public boolean insertApiProviderDesiya(ApiProviderDesiyaConfig apiProviderDesiyaConfig){
		Session session = null;
		Transaction tx = null;
		boolean isInserted=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(apiProviderDesiyaConfig);
			tx.commit();

			if(apiProviderDesiyaConfig!=null && apiProviderDesiyaConfig.getId()>0) 
				isInserted=true;
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			isInserted=false;
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return isInserted;
	}
	public boolean insertApiProviderBluestar(ApiProviderBluestarConfig apiProviderBluestarConfig){
		Session session = null;
		Transaction tx = null;
		boolean isInserted=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(apiProviderBluestarConfig);
			tx.commit();

			if(apiProviderBluestarConfig!=null && apiProviderBluestarConfig.getId()>0) 
				isInserted=true;
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			isInserted=false;
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return isInserted;
	}
	public boolean insertApiProviderEtravelSmart(ApiProviderEtravelSmartConfig apiProviderEtravelSmartConfig){
		Session session = null;
		Transaction tx = null;
		boolean isInserted=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(apiProviderEtravelSmartConfig);
			tx.commit();

			if(apiProviderEtravelSmartConfig!=null && apiProviderEtravelSmartConfig.getId()>0) 
				isInserted=true;
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			isInserted=false;
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return isInserted;
	}

	public boolean insertApiProviderTrawellTag(ApiProviderTrawellTagConfig apiProviderTrawellTagConfig){
		Session session = null;
		Transaction tx = null;
		boolean isInserted=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(apiProviderTrawellTagConfig);
			tx.commit();

			if(apiProviderTrawellTagConfig!=null && apiProviderTrawellTagConfig.getId()>0) 
				isInserted=true;
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			isInserted=false;
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return isInserted;
	}
	public List<ApiProviderEtravelSmartConfig> fetchApiProviderListEtravelSmart() {
		Session session = null;
		Transaction tx = null;
		List<ApiProviderEtravelSmartConfig> list=new ArrayList<ApiProviderEtravelSmartConfig>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderEtravelSmartConfig.class);
			list=criteria.list();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}
	public List<ApiProviderTrawellTagConfig> fetchApiProviderListTrawellTag() {
		Session session = null;
		List<ApiProviderTrawellTagConfig> list=new ArrayList<ApiProviderTrawellTagConfig>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(ApiProviderTrawellTagConfig.class);
			list=criteria.list();
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}
	public ApiProviderEtravelSmartConfig getApiProviderEtravelSmart(Long id) {
		Session session = null;

		Transaction tx = null;
		ApiProviderEtravelSmartConfig  apiProviderEtravelSmartConfig=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderEtravelSmartConfig.class);
			criteria.add(Restrictions.eq("id", id));
			apiProviderEtravelSmartConfig=(ApiProviderEtravelSmartConfig) criteria.uniqueResult();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderEtravelSmartConfig;

	}

	public ApiProviderTrawellTagConfig getApiProviderTrawellTag(Long id) {
		Session session = null;
		ApiProviderTrawellTagConfig  apiProviderTrawellTagConfig=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(ApiProviderTrawellTagConfig.class);
			criteria.add(Restrictions.eq("id", id));
			apiProviderTrawellTagConfig=(ApiProviderTrawellTagConfig) criteria.uniqueResult();
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderTrawellTagConfig;
	}
	public ApiProviderEtravelSmartConfig updateApiProviderEtravelSmart(ApiProviderEtravelSmartConfig apiProviderEtravelSmartConfig) {
		Session session = null;
		Transaction tx = null;
		ApiProviderEtravelSmartConfig apiProviderEtravelSmartConfigNew=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			apiProviderEtravelSmartConfigNew=(ApiProviderEtravelSmartConfig) session.get(ApiProviderEtravelSmartConfig.class, apiProviderEtravelSmartConfig.getId());
			apiProviderEtravelSmartConfigNew.setApiCurrency(apiProviderEtravelSmartConfig.getApiCurrency());
			apiProviderEtravelSmartConfigNew.setActive(apiProviderEtravelSmartConfig.getActive());
			apiProviderEtravelSmartConfigNew.setUrl(apiProviderEtravelSmartConfig.getUrl());
			apiProviderEtravelSmartConfigNew.setEnvironment(apiProviderEtravelSmartConfig.getEnvironment());
			apiProviderEtravelSmartConfigNew.setBusUserName(apiProviderEtravelSmartConfig.getBusUserName());
			apiProviderEtravelSmartConfigNew.setBusPassword(apiProviderEtravelSmartConfig.getBusPassword());
			apiProviderEtravelSmartConfigNew.setTitle(apiProviderEtravelSmartConfig.getTitle());
			session.update(apiProviderEtravelSmartConfigNew);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderEtravelSmartConfigNew;
	}

	public ApiProviderTrawellTagConfig updateApiProviderTrawellTag(ApiProviderTrawellTagConfig apiProviderTrawellTagConfig) {
		Session session = null;
		Transaction tx = null;
		ApiProviderTrawellTagConfig apiProviderTrawellTagConfigNew=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			apiProviderTrawellTagConfigNew=(ApiProviderTrawellTagConfig) session.get(ApiProviderTrawellTagConfig.class, apiProviderTrawellTagConfig.getId());
			apiProviderTrawellTagConfigNew.setApiCurrency(apiProviderTrawellTagConfig.getApiCurrency());
			apiProviderTrawellTagConfigNew.setActive(apiProviderTrawellTagConfig.getActive());
			apiProviderTrawellTagConfigNew.setUrl(apiProviderTrawellTagConfig.getUrl());
			apiProviderTrawellTagConfigNew.setEnvironment(apiProviderTrawellTagConfig.getEnvironment());
			apiProviderTrawellTagConfigNew.setInsuranceUserName(apiProviderTrawellTagConfig.getInsuranceUserName());
			apiProviderTrawellTagConfigNew.setReference(apiProviderTrawellTagConfig.getReference());
			apiProviderTrawellTagConfigNew.setSign(apiProviderTrawellTagConfig.getSign());
			apiProviderTrawellTagConfigNew.setBranchSign(apiProviderTrawellTagConfig.getBranchSign());

			apiProviderTrawellTagConfigNew.setTitle(apiProviderTrawellTagConfig.getTitle());
			session.update(apiProviderTrawellTagConfigNew);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			apiProviderTrawellTagConfigNew=null;
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderTrawellTagConfigNew;
	}
	public boolean insertApiProviderCommonConfig(ApiProviderCommonConfig apiProviderCommonConfig){
		Session session = null;
		Transaction tx = null;
		boolean isInserted=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(apiProviderCommonConfig);
			tx.commit();
			if(apiProviderCommonConfig!=null && apiProviderCommonConfig.getId()>0) 
				isInserted=true;
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			isInserted=false;
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return isInserted;
	}

	public List<ApiProviderCommonConfig> fetchApiProviderListCommonConfig() {
		Session session = null;
		Transaction tx = null;
		List<ApiProviderCommonConfig> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderCommonConfig.class);
			criteria.addOrder(Order.desc("id"));

			list=criteria.list();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}



	public List<ApiProvider> fetchApiProviderList() {
		Session session = null;
		Transaction tx = null;
		List<ApiProvider> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProvider.class);
			criteria.addOrder(Order.desc("id"));			
			list=criteria.list();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}




	public ApiProviderCommonConfig updateApiProviderCommonConfig(ApiProviderCommonConfig apiProviderCommonConfig) {
		Session session = null;
		Transaction tx = null;
		ApiProviderCommonConfig commonConfigNew=null;
		try{

			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();

			logger.info("---------getId()-------------"+apiProviderCommonConfig.getId());
			logger.info("---------isTboActive()-------------"+apiProviderCommonConfig.isTboActive());

			logger.info("---------isTboHotelActive()-------------"+apiProviderCommonConfig.isTboHotelActive());
			logger.info("---------isTboFlightActive()-------------"+apiProviderCommonConfig.isTboFlightActive());
			logger.info("---------isTboFlightEnvironment()-------------"+apiProviderCommonConfig.isTboFlightEnvironment());

			logger.info("---------isTboHotelEnvironment()-------------"+apiProviderCommonConfig.isTboHotelEnvironment());
			logger.info("---------isDesiyaHotelEnvironment()-------------"+apiProviderCommonConfig.isDesiyaHotelEnvironment());
			logger.info("---------isBluestarFlightEnvironment()-------------"+apiProviderCommonConfig.isBluestarFlightEnvironment());

			logger.info("---------isDesiyaActive()-------------"+apiProviderCommonConfig.isDesiyaActive());
			logger.info("---------isDesiyaHotelActive()-------------"+apiProviderCommonConfig.isDesiyaHotelActive());
			logger.info("---------isBluestarActive()-------------"+apiProviderCommonConfig.isBluestarActive());
			logger.info("---------isBluestarFlightActive()-------------"+apiProviderCommonConfig.isBluestarFlightActive());
			logger.info("---------getApiProviderBluestarConfigFlight()-------------"+apiProviderCommonConfig.getApiProviderBluestarConfigFlight());
			logger.info("---------getApiProviderDesiyaConfigHotel()-------------"+apiProviderCommonConfig.getApiProviderDesiyaConfigHotel());
			logger.info("---------getApiProviderTboConfigHotel()-------------"+apiProviderCommonConfig.getApiProviderTboConfigHotel());

			commonConfigNew = (ApiProviderCommonConfig) session.get(ApiProviderCommonConfig.class,apiProviderCommonConfig.getId());
			//			commonConfigNew.setId(apiProviderCommonConfig.getId());
			commonConfigNew.setTitle(apiProviderCommonConfig.getTitle());
			commonConfigNew.setTboActive(apiProviderCommonConfig.isTboActive());
			commonConfigNew.setTboHotelActive(apiProviderCommonConfig.isTboHotelActive());
			commonConfigNew.setTboFlightEnvironment(apiProviderCommonConfig.isTboFlightEnvironment());
			commonConfigNew.setTboHotelEnvironment(apiProviderCommonConfig.isTboHotelEnvironment());
			commonConfigNew.setDesiyaHotelEnvironment(apiProviderCommonConfig.isDesiyaHotelEnvironment());
			commonConfigNew.setBluestarFlightEnvironment(apiProviderCommonConfig.isBluestarFlightEnvironment());
			commonConfigNew.setTboFlightActive(apiProviderCommonConfig.isTboFlightActive());
			commonConfigNew.setDesiyaActive(apiProviderCommonConfig.isDesiyaActive());
			commonConfigNew.setDesiyaHotelActive(apiProviderCommonConfig.isDesiyaHotelActive());
			commonConfigNew.setBluestarActive(apiProviderCommonConfig.isBluestarActive());
			commonConfigNew.setBluestarFlightActive(apiProviderCommonConfig.isBluestarFlightActive());
			//commonConfigNew.setApiProviderBluestarConfigFlight(apiProviderCommonConfig.getApiProviderBluestarConfigFlight());
			//commonConfigNew.setApiProviderDesiyaConfigHotel(apiProviderCommonConfig.getApiProviderDesiyaConfigHotel());
			//commonConfigNew.setApiProviderTboConfigFlight(apiProviderCommonConfig.getApiProviderTboConfigFlight());
			//commonConfigNew.setApiProviderTboConfigHotel(apiProviderCommonConfig.getApiProviderTboConfigHotel());
			commonConfigNew.setEtravelActive(apiProviderCommonConfig.isEtravelActive());
			commonConfigNew.setEtravelBusActive(apiProviderCommonConfig.isEtravelBusActive());
			commonConfigNew.setEtravelBusId(apiProviderCommonConfig.getEtravelBusId());

			commonConfigNew.setTrawellTagActive(apiProviderCommonConfig.isTrawellTagActive());
			commonConfigNew.setTrawellTagInsuranceActive(apiProviderCommonConfig.isTrawellTagInsuranceActive());
			commonConfigNew.setTrawellTagInsuranceId(apiProviderCommonConfig.getTrawellTagInsuranceId());

			/*if(commonConfigNew.getApiProviderTboConfigFlight()!=null && commonConfigNew.getApiProviderTboConfigFlight().getId()!=apiProviderCommonConfig.getApiProviderTboConfigFlight().getId())
				commonConfigNew.setApiProviderTboConfigFlight(apiProviderCommonConfig.getApiProviderTboConfigFlight());
			if(commonConfigNew.getApiProviderTboConfigHotel()!=null && commonConfigNew.getApiProviderTboConfigHotel().getId()!=apiProviderCommonConfig.getApiProviderTboConfigHotel().getId())
				commonConfigNew.setApiProviderTboConfigHotel(apiProviderCommonConfig.getApiProviderTboConfigHotel());*/

			if(commonConfigNew.getApiProviderBluestarConfigFlight()!=null && commonConfigNew.getApiProviderBluestarConfigFlight().getId()!=apiProviderCommonConfig.getApiProviderBluestarConfigFlight().getId())
				commonConfigNew.setApiProviderBluestarConfigFlight(apiProviderCommonConfig.getApiProviderBluestarConfigFlight());
			if(commonConfigNew.getApiProviderDesiyaConfigHotel()!=null && commonConfigNew.getApiProviderDesiyaConfigHotel().getId()!=apiProviderCommonConfig.getApiProviderDesiyaConfigHotel().getId())
				commonConfigNew.setApiProviderDesiyaConfigHotel(apiProviderCommonConfig.getApiProviderDesiyaConfigHotel());
			if(commonConfigNew.getApiProviderEtravelBusConfig()!=null && commonConfigNew.getApiProviderEtravelBusConfig().getId()!=apiProviderCommonConfig.getApiProviderEtravelBusConfig().getId())
				commonConfigNew.setApiProviderEtravelBusConfig(apiProviderCommonConfig.getApiProviderEtravelBusConfig());
			if(commonConfigNew.getApiProviderTrawellTagInsuranceConfig()!=null && commonConfigNew.getApiProviderTrawellTagInsuranceConfig().getId()!=apiProviderCommonConfig.getApiProviderTrawellTagInsuranceConfig().getId())
				commonConfigNew.setApiProviderTrawellTagInsuranceConfig(apiProviderCommonConfig.getApiProviderTrawellTagInsuranceConfig());

			commonConfigNew.setEtravelBusEnvironment(apiProviderCommonConfig.isEtravelBusEnvironment());
			commonConfigNew.setTrawellTagInsuranceEnvironment(apiProviderCommonConfig.isTrawellTagInsuranceEnvironment());

			session.update(commonConfigNew);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return commonConfigNew;
	}

	public ApiProviderCommonConfig updateCommonConfigStatus(ApiProviderCommonConfig apiProviderCommonConfig,String switchType, boolean status) {
		Session session = null;
		Transaction tx = null;
		ApiProviderCommonConfig commonConfigNew =null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();			
			commonConfigNew =  (ApiProviderCommonConfig) session.get(ApiProviderCommonConfig.class,apiProviderCommonConfig.getId());
			if(switchType.equalsIgnoreCase("active"))
				commonConfigNew.setActive(status);
			if(switchType.equalsIgnoreCase("tboActive")) 
				commonConfigNew.setTboActive(status);
			if(switchType.equalsIgnoreCase("bluestarActive")) 
				commonConfigNew.setBluestarActive(status);
			if(switchType.equalsIgnoreCase("desiyaActive")) 
				commonConfigNew.setDesiyaActive(status);
			if(switchType.equalsIgnoreCase("etravelActive")) 
				commonConfigNew.setEtravelActive(status);
			if(switchType.equalsIgnoreCase("trawellTagActive")) 
				commonConfigNew.setTrawellTagActive(status);
			session.saveOrUpdate(commonConfigNew);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return commonConfigNew;
	}

	public ApiProviderTboConfig updateTboStatus(ApiProviderTboConfig apiProviderTboConfig) {
		Session session = null;
		Transaction tx = null;
		ApiProviderTboConfig commonConfigNew =null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();			
			commonConfigNew =  (ApiProviderTboConfig) session.get(ApiProviderTboConfig.class,apiProviderTboConfig.getId());
			commonConfigNew.setActive(apiProviderTboConfig.isActive());
			session.saveOrUpdate(commonConfigNew);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return commonConfigNew;
	}

	public ApiProviderBluestarConfig updateBluestarStatus(ApiProviderBluestarConfig apiProviderBluestarConfig) {
		Session session = null;
		Transaction tx = null;
		ApiProviderBluestarConfig commonConfigNew =null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();			
			commonConfigNew =  (ApiProviderBluestarConfig) session.get(ApiProviderBluestarConfig.class,apiProviderBluestarConfig.getId());
			commonConfigNew.setActive(apiProviderBluestarConfig.isActive());
			session.saveOrUpdate(commonConfigNew);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return commonConfigNew;
	}
	public ApiProviderDesiyaConfig updateDesiyaStatus(ApiProviderDesiyaConfig apiProviderDesiyaConfig) {
		Session session = null;
		Transaction tx = null;
		ApiProviderDesiyaConfig commonConfigNew =null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();			
			commonConfigNew =  (ApiProviderDesiyaConfig) session.get(ApiProviderDesiyaConfig.class,apiProviderDesiyaConfig.getId());
			commonConfigNew.setActive(apiProviderDesiyaConfig.isActive());
			session.saveOrUpdate(commonConfigNew);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return commonConfigNew;
	}

	public ApiProviderEtravelSmartConfig updateEtravelStatus(ApiProviderEtravelSmartConfig apiProviderEtravelConfig) {
		Session session = null;
		Transaction tx = null;
		ApiProviderEtravelSmartConfig apiProviderEtravelNew =null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();			
			apiProviderEtravelNew =  (ApiProviderEtravelSmartConfig) session.get(ApiProviderEtravelSmartConfig.class,apiProviderEtravelConfig.getId());
			apiProviderEtravelNew.setActive(apiProviderEtravelConfig.getActive());
			session.saveOrUpdate(apiProviderEtravelNew);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderEtravelNew;
	}
	public ApiProviderTrawellTagConfig updateTrawellTag(ApiProviderTrawellTagConfig apiProviderTrawellTagConfig) {
		Session session = null;
		Transaction tx = null;
		ApiProviderTrawellTagConfig apiProviderTrawellTagNew =null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();			
			apiProviderTrawellTagNew =  (ApiProviderTrawellTagConfig) session.get(ApiProviderTrawellTagConfig.class,apiProviderTrawellTagConfig.getId());
			apiProviderTrawellTagNew.setActive(apiProviderTrawellTagConfig.getActive());
			session.saveOrUpdate(apiProviderTrawellTagNew);
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderTrawellTagNew;
	}


	public List<ApiProviderTboConfig> fetchApiProviderListTbo() {
		Session session = null;
		Transaction tx = null;
		List<ApiProviderTboConfig> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderTboConfig.class);
			list=criteria.list();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}
	public List<ApiProviderDesiyaConfig> fetchApiProviderListDesiya() {
		Session session = null;
		Transaction tx = null;
		List<ApiProviderDesiyaConfig> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderDesiyaConfig.class);
			list=criteria.list();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}
	public List<ApiProviderBluestarConfig> fetchApiProviderListBluestar() {
		Session session = null;
		Transaction tx = null;
		List<ApiProviderBluestarConfig> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderBluestarConfig.class);
			list=criteria.list();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}



	public ApiProviderBluestarConfig updateApiProviderBluestar(ApiProviderBluestarConfig apiProviderBluestarConfig) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			ApiProviderBluestarConfig ApiProviderBluestarConfigNew=(ApiProviderBluestarConfig) session.get(ApiProviderBluestarConfig.class, apiProviderBluestarConfig.getId());
			ApiProviderBluestarConfigNew.setApiCurrency(apiProviderBluestarConfig.getApiCurrency());
			ApiProviderBluestarConfigNew.setActive(apiProviderBluestarConfig.isActive());
			ApiProviderBluestarConfigNew.setAgentCode(apiProviderBluestarConfig.getAgentCode());
			ApiProviderBluestarConfigNew.setInterfaceAuthKey(apiProviderBluestarConfig.getInterfaceAuthKey());
			ApiProviderBluestarConfigNew.setInterfaceCode(apiProviderBluestarConfig.getInterfaceCode());
			ApiProviderBluestarConfigNew.setEndPointUrl(apiProviderBluestarConfig.getEndPointUrl());
			ApiProviderBluestarConfigNew.setEnvironment(apiProviderBluestarConfig.getEnvironment());
			ApiProviderBluestarConfigNew.setPassword(apiProviderBluestarConfig.getPassword());
			ApiProviderBluestarConfigNew.setTitle(apiProviderBluestarConfig.getTitle());
			ApiProviderBluestarConfigNew.setHostUrl(apiProviderBluestarConfig.getHostUrl());
			ApiProviderBluestarConfigNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(ApiProviderBluestarConfigNew);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderBluestarConfig;
	}
	public ApiProviderTboConfig updateApiProviderTBO(ApiProviderTboConfig apiProviderTboConfig) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			ApiProviderTboConfig apiProviderTboConfigNew=(ApiProviderTboConfig) session.get(ApiProviderTboConfig.class, apiProviderTboConfig.getId());
			apiProviderTboConfigNew.setApiCurrency(apiProviderTboConfig.getApiCurrency());
			apiProviderTboConfigNew.setActive(apiProviderTboConfig.isActive());
			apiProviderTboConfigNew.setAuthUrlFlight(apiProviderTboConfig.getAuthUrlFlight());
			apiProviderTboConfigNew.setFlightUrlAirprice(apiProviderTboConfig.getFlightUrlAirprice());
			apiProviderTboConfigNew.setFlightUrlBook(apiProviderTboConfig.getFlightUrlBook());
			apiProviderTboConfigNew.setFlightUrlCalendarSearch(apiProviderTboConfig.getFlightUrlCalendarSearch());
			apiProviderTboConfigNew.setFlightUrlCancellation(apiProviderTboConfig.getFlightUrlCancellation());
			apiProviderTboConfigNew.setFlightUrlCancellationStatus(apiProviderTboConfig.getFlightUrlCancellationStatus());
			apiProviderTboConfigNew.setFlightUrlFarerule(apiProviderTboConfig.getFlightUrlFarerule());
			apiProviderTboConfigNew.setFlightUrlGetBookingDetail(apiProviderTboConfig.getFlightUrlGetBookingDetail());
			apiProviderTboConfigNew.setFlightUrlPriceRbd( apiProviderTboConfig.getFlightUrlPriceRbd());
			apiProviderTboConfigNew.setFlightUrlReleasePnr(apiProviderTboConfig.getFlightUrlReleasePnr());
			apiProviderTboConfigNew.setFlightUrlSearch(apiProviderTboConfig.getFlightUrlSearch());
			apiProviderTboConfigNew.setFlightUrlSsr(apiProviderTboConfig.getFlightUrlSsr());
			apiProviderTboConfigNew.setFlightUrlTicket(apiProviderTboConfig.getFlightUrlTicket());
			apiProviderTboConfigNew.setClientId(apiProviderTboConfig.getClientId());
			apiProviderTboConfigNew.setEnduserIp(apiProviderTboConfig.getEnduserIp());
			apiProviderTboConfigNew.setEnvironment(apiProviderTboConfig.getEnvironment());
			apiProviderTboConfigNew.setUserName(apiProviderTboConfig.getUserName());
			apiProviderTboConfigNew.setPassword(apiProviderTboConfig.getPassword());
			apiProviderTboConfigNew.setPropertyId(apiProviderTboConfig.getPropertyId());
			apiProviderTboConfigNew.setProviderId(apiProviderTboConfig.getProviderId());
			apiProviderTboConfigNew.setTitle(apiProviderTboConfig.getTitle());
			apiProviderTboConfigNew.setGeneralUrlAgencyBalance(apiProviderTboConfig.getGeneralUrlAgencyBalance());
			apiProviderTboConfigNew.setAuthUrlHotel(apiProviderTboConfig.getAuthUrlHotel());
			apiProviderTboConfigNew.setHotelUrlBlockRooms(apiProviderTboConfig.getHotelUrlBlockRooms());
			apiProviderTboConfigNew.setHotelUrlBooking(apiProviderTboConfig.getHotelUrlBooking());
			apiProviderTboConfigNew.setHotelUrlBookingSummary(apiProviderTboConfig.getHotelUrlBookingSummary());
			apiProviderTboConfigNew.setHotelUrlCancel(apiProviderTboConfig.getHotelUrlCancel());
			apiProviderTboConfigNew.setHotelUrlCancelStatus(apiProviderTboConfig.getHotelUrlCancelStatus());
			apiProviderTboConfigNew.setHotelUrlSearchHotel(apiProviderTboConfig.getHotelUrlSearchHotel());
			apiProviderTboConfigNew.setHotelUrlSearchHotelInfo(apiProviderTboConfig.getHotelUrlSearchHotelInfo());
			apiProviderTboConfigNew.setHotelUrlSearchRooms(apiProviderTboConfig.getHotelUrlSearchRooms());

			apiProviderTboConfigNew.setPasswordHotel(apiProviderTboConfig.getPasswordHotel());
			apiProviderTboConfigNew.setUserNameHotel(apiProviderTboConfig.getUserNameHotel());
			apiProviderTboConfigNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(apiProviderTboConfigNew);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderTboConfig;
	}
	public ApiProviderDesiyaConfig updateApiProviderDesiya(ApiProviderDesiyaConfig apiProviderDesiyaConfig) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			ApiProviderDesiyaConfig ApiProviderDesiyaConfigNew=(ApiProviderDesiyaConfig) session.get(ApiProviderDesiyaConfig.class, apiProviderDesiyaConfig.getId());
			ApiProviderDesiyaConfigNew.setApiCurrency(apiProviderDesiyaConfig.getApiCurrency());
			ApiProviderDesiyaConfigNew.setActive(apiProviderDesiyaConfig.isActive());
			ApiProviderDesiyaConfigNew.setEndPointUrl(apiProviderDesiyaConfig.getEndPointUrl());
			ApiProviderDesiyaConfigNew.setEnvironment(apiProviderDesiyaConfig.getEnvironment());
			ApiProviderDesiyaConfigNew.setPassword(apiProviderDesiyaConfig.getPassword());
			ApiProviderDesiyaConfigNew.setTitle(apiProviderDesiyaConfig.getTitle());
			ApiProviderDesiyaConfigNew.setPropertyId(apiProviderDesiyaConfig.getPropertyId());
			ApiProviderDesiyaConfigNew.setProviderId(apiProviderDesiyaConfig.getProviderId());
			ApiProviderDesiyaConfigNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			ApiProviderDesiyaConfigNew.setUserName(apiProviderDesiyaConfig.getUserName());
			session.update(ApiProviderDesiyaConfigNew);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderDesiyaConfig;
	}

	public ApiProviderTboConfig getApiProviderTbo(Long id) {
		Session session = null;

		Transaction tx = null;
		ApiProviderTboConfig  apiProviderTboConfig=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderTboConfig.class);
			criteria.add(Restrictions.eq("id", id));
			apiProviderTboConfig=(ApiProviderTboConfig) criteria.uniqueResult();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderTboConfig;

	}
	public ApiProviderDesiyaConfig getApiProviderDesiya(Long id) {
		Session session = null;

		Transaction tx = null;
		ApiProviderDesiyaConfig  apiProviderDesiyaConfig=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderDesiyaConfig.class);
			criteria.add(Restrictions.eq("id", id));
			apiProviderDesiyaConfig=(ApiProviderDesiyaConfig) criteria.uniqueResult();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderDesiyaConfig;

	}
	public ApiProviderBluestarConfig getApiProviderBluestar(Long id) {
		Session session = null;

		Transaction tx = null;
		ApiProviderBluestarConfig  apiProviderBluestarConfig=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderBluestarConfig.class);
			criteria.add(Restrictions.eq("id", id));
			apiProviderBluestarConfig=(ApiProviderBluestarConfig) criteria.uniqueResult();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderBluestarConfig;

	}
	public ApiProviderCommonConfig getApiProviderCommonConfig(Long id) {
		Session session = null;

		Transaction tx = null;
		ApiProviderCommonConfig  apiProviderCommonConfig=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProviderCommonConfig.class);
			criteria.add(Restrictions.eq("id", id));
			apiProviderCommonConfig=(ApiProviderCommonConfig) criteria.uniqueResult();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProviderCommonConfig;

	}


	public List<ApiProvider> fetchApiProviderListTest() {
		Session session = null;
		Transaction tx = null;
		List<ApiProvider> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(ApiProvider.class);
			list=criteria.list();
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return list;
	}

	public void deleteTboConfig(Long id) {
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from ApiProviderTboConfig cd where cd.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			query.executeUpdate();
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());


		}finally {
			session.close(); 
		}

	}

	public void deleteDesiyaConfig(Long id) {
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from ApiProviderDesiyaConfig cd where cd.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			query.executeUpdate();
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());


		}finally {
			session.close(); 
		}

	}


	public void deleteBluestarConfig(Long id) {
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from ApiProviderBluestarConfig cd where cd.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			query.executeUpdate();
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());


		}finally {
			session.close(); 
		}

	}

	public void deletecommonConfig(Long id) {
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from ApiProviderCommonConfig cd where cd.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			query.executeUpdate();
			transaction.commit();

		}catch (HibernateException e) {
			if(transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());


		}finally {
			session.close(); 
		}

	}
	public List<Integer> fetchCommonConfigConfigIds() {
		Session session = null;
		List<Integer> configIds=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(ApiProviderCommonConfig.class);
			crit.setProjection(Projections.property("configId"));
			List list=crit.list();
			Iterator it=list.iterator();
			while(it.hasNext())
			{
				Integer configId = (Integer)it.next();
				configIds.add(configId);
			}

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return configIds;
	}

	public List<Integer> getApiProviderDetailsBy(String vendorName){
		Session session = null;
		List<Integer> apiIdList=new ArrayList<Integer>();
		try{ 
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(ApiProvider.class);
			/*Conjunction conjunction=Restrictions.conjunction();
				conjunction.add(Restrictions.eq("vendorName", vendorName));
				conjunction.add(Restrictions.eq("travelType", travelType));*/
			crit.add(Restrictions.eq("vendorName", vendorName));
			crit.setProjection(Projections.property("id"));
			apiIdList=crit.list();
		}
		catch(HibernateException e){

			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		catch(Exception e){
			logger.info("---------Exception-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiIdList;
	}

	public  static ApiProvider getApiProviderDetailsByVendorName(String vendorName){
		Session session = null;
		ApiProvider apiProvider=null;
		try{ 
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(ApiProvider.class);
			crit.add(Restrictions.eq("vendorName", vendorName));
			apiProvider=(ApiProvider) crit.uniqueResult();
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		catch(Exception e){
			logger.info("---------Exception-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return apiProvider;
	}

	public  ApiProvider getApiPrividerDetails(String providerId){
		Session session=null;
		ApiProvider apiProvider=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(ApiProvider.class);
			criteria.add(Restrictions.eq("id", Integer.parseInt(providerId)));
			apiProvider = (ApiProvider) criteria.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		finally {
			session.close();
		}
		return apiProvider;
	}

}
