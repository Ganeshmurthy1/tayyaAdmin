package com.lintas.admin.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.common.commonDsrReportConfg.CommonDsrReportConfg;
import com.admin.common.config.model.AdvertiseandOtherServiceTaxConfig;
import com.admin.common.config.model.BookingServiceConfig;
import com.admin.common.config.model.BusServiceTaxConfig;
import com.admin.common.config.model.CarServiceTaxConfig;
import com.admin.common.config.model.FlightBookingCutoffConfig;
import com.admin.common.config.model.FlightDomesticServiceTaxConfig;
import com.admin.common.config.model.FlightInternationalServiceTaxConfig;
import com.admin.common.config.model.FlightPaymentConfig;
import com.admin.common.config.model.HolidayServiceTaxConfig;
import com.admin.common.config.model.HotelBookingCutoffConfig;
import com.admin.common.config.model.HotelPaymentConfig;
import com.admin.common.config.model.HotelServiceTaxConfig;
import com.admin.common.config.model.RailServiceTaxConfig;
import com.admin.common.config.model.VisaServiceTaxConfig;
import com.admin.miscellaneous.model.MiscellaneousGstTaxConfig;
import com.lintas.admin.advertisement.model.AdvertisementGstTaxConfig;
import com.lintas.admin.bus.model.BusGstTaxConfig;
import com.lintas.admin.car.model.CarGstTaxConfig;
import com.lintas.admin.flight.model.FlightDomesticGstTaxConfig;
import com.lintas.admin.flight.model.FlightInternationalGstTaxConfig;
import com.lintas.admin.holiday.model.HolidayGstTaxConfig;
import com.lintas.admin.hotel.model.HotelGstTaxConfig;
import com.lintas.admin.insurance.model.InsuranceGstTaxConfig;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CompanyConfigTax;
import com.lintas.admin.model.CompanyConfigType;
import com.lintas.admin.model.FlightMarkup;
import com.lintas.admin.model.HotelMarkup;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainGstTaxConfig;
import com.lintas.admin.visa.model.VisaGstTaxConfig;
import com.lintas.config.HibernateUtil;


public class CompanyConfigDao {

	/**
	@author info name:raham
		created date:27-07-2015	
	 */
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyConfigDao.class);
	/*Insert the CompanyConfig details into the database*/
	public CompanyConfig insertCompanyConfigData(CompanyConfig cc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(cc.getTaxtype().trim().equalsIgnoreCase("")){
				cc.setTaxtype(null);
			}
			session.save(cc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return cc	;
	}

	/*get CompanyConfig  Details from DB */
	public CompanyConfig getCompanyConfigDetails (int config_id){

		CompanyConfig configDetailsObj=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CompanyConfig cf where cf.config_id=:config_id order by cf.config_id desc";
			Query query = session.createQuery(sql);
			query.setParameter("config_id", config_id);
			configDetailsObj = (CompanyConfig)query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return configDetailsObj;
	}


	/*get CompanyConfig  Details from DB */
	public CompanyConfig getCompanyConfigDetails (CompanyConfig c){

		CompanyConfig configDetailsObj=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CompanyConfig cf where cf.config_id=:config_id"; 
			Query query = session.createQuery(sql);
			query.setParameter("config_id", c.getConfig_id());
			configDetailsObj = (CompanyConfig) query.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}  
		return configDetailsObj;
	}
	/* delete CompanyConfig details from DB if existed...*/
	public boolean deleteCompanyConfigData(CompanyConfig mcad) throws Exception
	{
		boolean isDelete = false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String sql = "delete from  CompanyConfig cf  where cfconfig_id =:cfId";
			Query query = session.createQuery(sql);
			query.setParameter("cfId", mcad.getConfig_id());
			int result=query.executeUpdate();
			if (result>0) {
				isDelete = true;
			}
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return isDelete;
	}
	/*get CompanyConfig List details*/
	public List<CompanyConfig> getCompanyConfigList(User userSessionObj,Company companySessionObj,int offset,int maxResults)
	{
		List<CompanyConfig> configLi=null;
		List<Integer> companyIds=new ArrayList<>();
		List<Company> companiesList=new CompanyDAO().getAllCompaniesByCompanyUserId(userSessionObj, companySessionObj);

		if(companiesList!=null && companiesList.size()>0){
			for (int i = 0; i < companiesList.size(); i++) {
				Company companyNew= (Company)companiesList.get(i);
				companyIds.add(companyNew.getCompanyid());
			}
		}

		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql="from CompanyConfig cf where cf.createdbyComapnyId in (:companyids) order by cf.config_id desc";
			logger.info("-------sql-------"+sql);
			Query query = session.createQuery(sql);
			query.setParameterList("companyids", companyIds);
			query.setFirstResult(offset).setMaxResults(maxResults);
			configLi = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return configLi;
	}

	/*fetch company config list  by passing Company user id*/
	public List<CompanyConfig> getCompanyCongigListByCompanyUserId(String companyUserId){
		List<CompanyConfig> configList =null;
		List<Integer> companyIds=new CompanyDAO().getAllComapnyIdsByCompanyUserIdTest(companyUserId);
		try{
			if(companyIds!=null && companyIds.size()>0){
				String sql="from CompanyConfig cf where cf.createdbyComapnyId in (:companyids) order by cf.config_id desc";
				logger.info("-----Filter companyconfig list- query---"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameterList("companyids", companyIds);
				configList = query.list();
			}
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return configList;
	}
	/*fetch company Config list by passing user id*/
	public List<CompanyConfig> getCompanyConfigListByUserId(String userId){
		String sql="";
		List<CompanyConfig> userList=null;
		try{
			if(userId!=null){
				sql = "from CompanyConfig cf where cf.createdbyUserId=:createdby_userId";
				logger.info("---------get created by company config Query-------"+sql);
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("createdby_userId", userId);
				userList = query.list();
			}
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		} 
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return userList;
	}

	/*Update Companyconfig details*/
	public boolean updateCompanyconfig(CompanyConfig newobj)
	{
		//boolean updated = false;
		/*try

	{
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		FlightDomesticServiceTaxConfig flightDomesticServiceTaxConfigNew=(FlightDomesticServiceTaxConfig) session.get(FlightDomesticServiceTaxConfig.class, flightDomesticServiceTaxConfig.getId());
		flightDomesticServiceTaxConfigNew.setApplicableFare(flightDomesticServiceTaxConfig.getApplicableFare());
		flightDomesticServiceTaxConfigNew.setBasicTax(flightDomesticServiceTaxConfig.getBasicTax());
		flightDomesticServiceTaxConfigNew.setConvenienceFee(flightDomesticServiceTaxConfig.getConvenienceFee());
		flightDomesticServiceTaxConfigNew.setKrishiKalyanCess(flightDomesticServiceTaxConfig.getKrishiKalyanCess());
		flightDomesticServiceTaxConfigNew.setManagementFee(flightDomesticServiceTaxConfig.getManagementFee());
		flightDomesticServiceTaxConfigNew.setSwatchBharathCess(flightDomesticServiceTaxConfig.getSwatchBharathCess());
		flightDomesticServiceTaxConfigNew.setTotalTax(flightDomesticServiceTaxConfig.getTotalTax());
		flightDomesticServiceTaxConfigNew.setUpdatedAt(new Timestamp(new  Date().getTime()));
		session.saveOrUpdate(flightDomesticServiceTaxConfigNew);
		transaction.commit();
	}
	catch (HibernateException e) {
		if(transaction!=null){
			transaction.rollback();
		}
		logger.error("--------------HibernateException-----------------"+e.getMessage());
	}finally {
		if(session != null && session.isOpen())
			session.close(); 
	}*/
		boolean updated = false;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			session.saveOrUpdate(newobj);
			session.flush();
			transaction.commit();
			logger.info("Config_id"+newobj.getConfig_id()+"getConfigname-------------"+newobj.getConfigname());
			if(newobj.getConfig_id()==newobj.getConfig_id()) 
				updated = true;
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return updated;
		//return updated;

	}

	public FlightPaymentConfig getFlightPaymentConfig(long id)
	{

		Session session=null;
		FlightPaymentConfig flightPaymentConfig=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightPaymentConfig.class);
			criteria.add(Restrictions.eq("id", id));
			flightPaymentConfig=(FlightPaymentConfig) criteria.uniqueResult();
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return flightPaymentConfig;


	}

	public HotelPaymentConfig getHotelPaymentConfig(long id)
	{

		Session session=null;
		HotelPaymentConfig hotelPaymentConfig=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelPaymentConfig.class);
			criteria.add(Restrictions.eq("id", id));
			hotelPaymentConfig=(HotelPaymentConfig) criteria.uniqueResult();
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return hotelPaymentConfig;


	}
	public BookingServiceConfig getBookingServiceConfig(long id)
	{

		Session session=null;
		BookingServiceConfig bookingServiceConfig=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BookingServiceConfig.class);
			criteria.add(Restrictions.eq("id", id));
			bookingServiceConfig=(BookingServiceConfig) criteria.uniqueResult();
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return bookingServiceConfig;


	}



	public FlightDomesticServiceTaxConfig getFlightServiceTaxConfig(long id)
	{

		Session session=null;
		FlightDomesticServiceTaxConfig flightServiceTaxConfig=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightDomesticServiceTaxConfig.class);
			criteria.add(Restrictions.eq("id", id));
			flightServiceTaxConfig=(FlightDomesticServiceTaxConfig) criteria.uniqueResult();
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return flightServiceTaxConfig;


	}
	public HotelServiceTaxConfig getHotelServiceTaxConfig(long id)
	{

		Session session=null;
		HotelServiceTaxConfig hotelServiceTaxConfig=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelServiceTaxConfig.class);
			criteria.add(Restrictions.eq("id", id));
			hotelServiceTaxConfig=(HotelServiceTaxConfig) criteria.uniqueResult();
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return hotelServiceTaxConfig;


	}





	public FlightBookingCutoffConfig getFlightBookingCutoffConfig(long flightBookingCutoffConfigId) {
		// TODO Auto-generated method stub
		Session session=null;
		FlightBookingCutoffConfig flightBookingCutoffConfig=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightBookingCutoffConfig.class);
			criteria.add(Restrictions.eq("id", flightBookingCutoffConfigId));
			flightBookingCutoffConfig=(FlightBookingCutoffConfig) criteria.uniqueResult();
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return flightBookingCutoffConfig;

	}
	public HotelBookingCutoffConfig getHotelBookingCutoffConfig(long hotelBookingCutoffConfigId) {
		// TODO Auto-generated method stub
		Session session=null;
		HotelBookingCutoffConfig hotelBookingCutoffConfig=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelBookingCutoffConfig.class);
			criteria.add(Restrictions.eq("id", hotelBookingCutoffConfigId));
			hotelBookingCutoffConfig=(HotelBookingCutoffConfig) criteria.uniqueResult();
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return hotelBookingCutoffConfig;

	}






	/*get  Flightmarkup ids */
	public List<FlightMarkup> getFlightMarkupIdsByConfigId(CompanyConfig newobj)
	{
		List<FlightMarkup> markupIds=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightMarkup fm where fm.configId=:configid"; 
			logger.error("sql----------------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("configid", newobj.getConfig_id());
			List<FlightMarkup>  list = query.list();
			if(list!=null){
				for (FlightMarkup flightMarkup:list){
					flightMarkup.setConfigname(newobj.getConfigname());
					logger.error("flightMarkup.getConfigname()"+flightMarkup.getConfigname());
					markupIds.add(flightMarkup);
				}	
			}
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return markupIds;
	}

	/*get  Flightmarkup ids */
	public List<HotelMarkup> getHotelMarkupIdsByConfigId(CompanyConfig newobj)
	{
		List<HotelMarkup> markupIds=new ArrayList<>();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelMarkup hm where fm.configId=:configid"; 
			logger.error("sql----------------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("configid", newobj.getConfig_id());
			List<HotelMarkup>  list = query.list();
			if(list!=null){
				for (HotelMarkup hotelMarkup:list){
					hotelMarkup.setConfigname(newobj.getConfigname());
					markupIds.add(hotelMarkup);
				}	
			}
		}
		catch (HibernateException e) {

			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return markupIds;
	}

	/*Update Flightmarkup */
	public void updateFlightmarkup(List<FlightMarkup>   markupIds)
	{
		try
		{
			FlightMarkup markup=null;
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(markupIds!=null){
				for(FlightMarkup id:markupIds){
					markup =  (FlightMarkup) session.get(FlightMarkup.class,id.getMarkupId());
					markup.setConfigname(id.getConfigname());
					session.update(markup);
				}
			}

			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
	}

	/*Update Flightmarkup */
	public void updateHotelmarkup(List<HotelMarkup>   markupIds)
	{
		logger.error("=====markupIds============="+markupIds.size());
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(markupIds!=null){
				for(HotelMarkup id:markupIds){
					logger.error("getMarkupId----------"+id.getId());
					logger.error("getConfigname----------"+id.getConfigname());
					HotelMarkup markup =  (HotelMarkup) session.get(HotelMarkup.class,id.getId());
					markup.setConfigname(id.getConfigname());
					session.update(markup);
				}
			}
			transaction.commit();
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
	}


	/*passing Config_id update the encripted appkey in to db */
	public boolean updateEncriptedAppkye(CompanyConfig cc )
	{
		boolean updated = false;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			session.update(cc);
			transaction.commit();
			updated = true;
		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return updated;
	}
	/* this method for fetch company userids  for set the  company configurations  by passinng company parent user id  */
	public List<Company> getCompanyUserIds(Company company){
		List<Company> companyUserIds=null;
		String sql = "from Company com where com.parent_company_userid=:parentcompanyuserid";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("parentcompanyuserid", company.getCompany_userid());
			companyUserIds = query.list();

		}catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyUserIds;
	}

	//this method for fetching the config list by company name

	public List<CompanyConfig> getCompanyConfigsByName(CompanyConfig companyConfig,int offset,int maxResults){
		List<CompanyConfig> filterConfigList=null;
		String sql = "from CompanyConfig";
		logger.info("--companyName------------"+companyConfig.getCompanyName());
		logger.info("--configname------------"+companyConfig.getConfigname());
		logger.info("--companyType------------"+companyConfig.getCompanyType());


		if(companyConfig.getCompanyName()!=null|| companyConfig.getConfigname()!=null || !companyConfig.getCompanyType().equals("select")){
			sql = "from CompanyConfig cf where cf.companyName=:companyname or cf.configname=:configname or cf.companyType=:companyType";
			logger.info("-------filter  configlist query-----------"+sql);
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery(sql);
				query.setParameter("companyname", companyConfig.getCompanyName());
				query.setParameter("configname", companyConfig.getConfigname());
				query.setParameter("companyType",companyConfig.getCompanyType());
				query.setFirstResult(offset).setMaxResults(maxResults);
				filterConfigList = query.list();
				logger.info("-------filter  configlist s-----------"+filterConfigList.size());
			}
			catch (HibernateException e) {
				logger.error("-------HibernateException-------"+  e.getMessage());
			}
			finally {
				if(session != null && session.isOpen())
					session.close(); 
			}
		}

		return filterConfigList;
	}

	public Long count(Object o){
		Session session=null;
		Criteria criteria=null;
		Long count=null;
		CompanyConfig config=(CompanyConfig) o;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(CompanyConfig.class);
			if(config!=null){
				Disjunction or = Restrictions.disjunction();//this is for or conditions 
				or.add(Restrictions.eq("companyName",config.getCompanyName()));
				or.add(Restrictions.eq("configname",config.getConfigname()));
				or.add(Restrictions.eq("companyType",config.getCompanyType()));
				criteria.add(or);
				count=(Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
			else{
				count=(Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		}catch(HibernateException e){
			logger.error("HibernateException-------"+e);
		}
		finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return count;

	}

	public List<CompanyConfig> getParentConfigIdsByCompanyId(Company sessionObj) {
		List<CompanyConfig> parentCompanyConfigList=null;
		Criteria criteria=null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(CompanyConfig.class);
			Conjunction conjection=Restrictions.conjunction();
			//Query query = session.createQuery(sql);
			if(!sessionObj.getCompanyRole().isDistributor() && !sessionObj.getCompanyRole().isAgent())
				conjection.add(Restrictions.eq("company_id", sessionObj.getCompanyid()));
			//conjection.add(Restrictions.eq("isMyConfig",true));
			else if(sessionObj.getCompanyRole().isDistributor() || sessionObj.getCompanyRole().isAgent())
				conjection.add(Restrictions.eq("company_id", sessionObj.getCompanyid()));
			//conjection.add(Restrictions.eq("isMyConfig",false));
			criteria.add(conjection);
			parentCompanyConfigList = criteria.list();
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return parentCompanyConfigList;
	}
	public CompanyConfig updateCompanyConfigLock(CompanyConfig companyConfig)
	{
		CompanyConfig config= null;
		try{
			if(companyConfig!=null){
				//logger.error("------Active------"+companyConfig.isActive());
				//logger.error("------Config id------"+companyConfig.getConfig_id());
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();			
				config =  (CompanyConfig) session.get(CompanyConfig.class,companyConfig.getConfig_id());
				config.setActive(companyConfig.isActive());
				session.update(config);
				transaction.commit();
				logger.error("config--"+config.isActive());
			}
		}catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return  config;
	}
	public Long getCommissionBlockId(int companyId)
	{
		Session session=null;
		Long blockID=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(CompanyConfig.class);
			cr.add(Restrictions.eq("company_id", companyId));
			List<CompanyConfig> list=cr.list();
			if(list != null && list.size()>0)
			{
				blockID=list.get(0).getAppliedBlockId();
			}


			/*if(list!=null && list.size()>0){
				for(CompanyConfig config:list){
					if(config.getConfig_number().equalsIgnoreCase("GIFNOC2")){
						blockID=config.getAppliedBlockId();
					}

				}
			}*/
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return blockID;
	}
	public List<CompanyConfig> getCompanyConfigList(Long blockId)
	{
		Session session=null;
		List<CompanyConfig> list = new ArrayList<CompanyConfig>();
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(CompanyConfig.class);
			cr.add(Restrictions.eq("appliedBlockId", blockId));
			list = cr.list();			
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	public CompanyConfig getLastUpdatedCompanyConfigDetails(Company sessionObj)
	{
		List<CompanyConfig> companyConfigList=null;
		CompanyConfig  companyConfig=null;
		Session session=null;
		session= HibernateUtil.getSessionFactory().openSession();

		Criteria cr = session.createCriteria(CompanyConfig.class);
		try{
			if(sessionObj!=null){
				cr.add(Restrictions.eq("company_id", sessionObj.getCompanyid()));
				cr.addOrder(Order.desc("config_id"));
				companyConfigList=cr.list();
				if(companyConfigList != null && companyConfigList.size()>0)
				{
					companyConfig = companyConfigList.get(0);
				}

			}
			if(companyConfig != null)
				logger.info("------last updated config id-------------------"+companyConfig.getConfig_id());
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyConfig;

	}

	public CompanyConfig getCompanyConfigDetailsByCompanydetails(Company sessionObj)
	{
		List<CompanyConfig> companyConfigList=null;
		CompanyConfig  companyConfig=null;
		Session session=null;
		session= HibernateUtil.getSessionFactory().openSession();

		Criteria cr = session.createCriteria(CompanyConfig.class);
		try{
			if(sessionObj!=null){
				cr.add(Restrictions.eq("company_id", sessionObj.getCompanyid()));
				cr.addOrder(Order.desc("config_id"));
				companyConfigList=cr.list();
				if(companyConfigList != null && companyConfigList.size()>0)
				{
					for (int i = 0; i < companyConfigList.size(); i++) {
						CompanyConfig companyConfigs = companyConfigList.get(i);
						if(!companyConfigs.getCompanyConfigType().isB2C())
							companyConfig = companyConfigs;
					}

				}

			}
			if(companyConfig != null)
				logger.info("------last updated config id-------------------"+companyConfig.getConfig_id());
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyConfig;

	}

	public void setIsMyConfigDeActive(String configType,int companyId) {
		// TODO Auto-generated method stub
		List<CompanyConfig> companyConfigList=null;
		Session session=null;
		Transaction tx=null;
		session= HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria cr = session.createCriteria(CompanyConfig.class);
			Conjunction conjunction =Restrictions.conjunction();
			conjunction.add(Restrictions.eq("company_id",companyId));
			cr.add(conjunction);
			companyConfigList=cr.list();
			if(companyConfigList != null && companyConfigList.size()>0)
			{
				tx = session.beginTransaction();	
				for(CompanyConfig companyConfig:companyConfigList){
					CompanyConfigType companyConfigType=companyConfig.getCompanyConfigType();
					CompanyConfig config= (CompanyConfig) session.get(CompanyConfig.class,companyConfig.getConfig_id());
					if(configType.equalsIgnoreCase("B2C")&&companyConfig.getCompanyConfigType().isB2C()){
						//companyConfigType.setB2C(false);
						config.setMyConfig(false);
						//config.setActive(false);
					}
					if(configType.equalsIgnoreCase("B2B")&&companyConfig.getCompanyConfigType().isB2B()){
						//companyConfigType.setB2B(false);
						config.setMyConfig(false);
						//config.setActive(false);
					}
					if(configType.equalsIgnoreCase("API")&&companyConfig.getCompanyConfigType().isAPI()){
						//companyConfigType.setAPI(false);
						config.setMyConfig(false);
						//config.setActive(false);
					}
					if(configType.equalsIgnoreCase("WL")&&companyConfig.getCompanyConfigType().isWhitelable()){
						//companyConfigType.setWhitelable(false);
						config.setMyConfig(false);
						//config.setActive(false);
					}
					if(configType.equalsIgnoreCase("B2E")&&companyConfig.getCompanyConfigType().isWhitelable()){
						//companyConfigType.setWhitelable(false);
						config.setMyConfig(false);
						//config.setActive(false);
					}
					config.setCompanyConfigType(companyConfigType);
					session.update(config);
				}
				tx.commit();
			}
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
			if(tx!=null)
				tx.rollback();
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

	}


	/*method for fetch company ids  by passing company user id*/
	public List<Company> getAllComapnyIdsByCompanyUserId(String companyUserId){
		Session session = null;
		List<Company> companiesList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(Company.class);
			criteria.add(Restrictions.eq("parent_company_userid", companyUserId));
			companiesList = criteria.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companiesList;
	}

	public void traverseandupdateParentconfigIdtoChild(List<Integer> newCompanyList,int parentConfigId,String configType) {
		// TODO Auto-generated method stub
		List<CompanyConfig> companyConfigList=null;
		Session session=null;
		Transaction tx=null;
		session= HibernateUtil.getSessionFactory().openSession();
		try{
			String sql="from CompanyConfig  as c where c.company_id in (:companyids)";
			Query query = session.createQuery(sql);
			query.setParameterList("companyids", newCompanyList);
			companyConfigList=query.list();
			if(companyConfigList != null && companyConfigList.size()>0)
			{
				tx = session.beginTransaction();
				for(CompanyConfig companyConfig:companyConfigList){
					CompanyConfig config=null;
					if(configType.equals("B2C")&& companyConfig.getCompanyConfigType().isB2C()) 
						config= (CompanyConfig) session.get(CompanyConfig.class,companyConfig.getConfig_id());	
					if(configType.equals("B2B")&& companyConfig.getCompanyConfigType().isB2B()) 
						config= (CompanyConfig) session.get(CompanyConfig.class,companyConfig.getConfig_id());	
					if(configType.equals("API")&& companyConfig.getCompanyConfigType().isAPI()) 
						config= (CompanyConfig) session.get(CompanyConfig.class,companyConfig.getConfig_id());	
					if(configType.equals("WL")&& companyConfig.getCompanyConfigType().isWhitelable()) 
						config= (CompanyConfig) session.get(CompanyConfig.class,companyConfig.getConfig_id());
					config.setParent_config_id(parentConfigId);
					session.update(config);
				}
				tx.commit();
			}
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
			if(tx!=null)
				tx.rollback();
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

	}

	public 	Map<String,Integer> childCountforMyCompanyConfig(List<Integer> newCompanyList,int createdByCompanyID,String  type){
		// TODO Auto-generated method stub
		List<CompanyConfig> companyConfigList=null;
		Session session=null;

		Map<String,Integer> countMap=new HashMap<>();
		session= HibernateUtil.getSessionFactory().openSession();
		try{
			String sql="from CompanyConfig  as c where c.company_id in (:companyids)";
			Query query = session.createQuery(sql);
			query.setParameterList("companyids", newCompanyList);
			companyConfigList=query.list();

			if(companyConfigList != null && companyConfigList.size()>0)
			{
				for(CompanyConfig companyConfig:companyConfigList){
					if(companyConfig.getCreatedbyComapnyId()==createdByCompanyID){
						if(type.equals("B2C") && companyConfig.getCompanyConfigType().isB2C()){

							if (countMap.containsKey(type)) {
								countMap.put(type, countMap.get(type) + 1);
							} else {
								countMap.put(type, 1);
							}
						}
						if(type.equals("B2B") && companyConfig.getCompanyConfigType().isB2B()) {

							if (countMap.containsKey(type)) {
								countMap.put(type, countMap.get(type) + 1);
							} else {
								countMap.put(type, 1);
							}
						}

						if(type.equals("API")&&companyConfig.getCompanyConfigType().isAPI()){

							if (countMap.containsKey(type)) {
								countMap.put(type, countMap.get(type) + 1);
							} else {
								countMap.put(type, 1);
							}
						}
						if (type.equals("WL")&&companyConfig.getCompanyConfigType().isWhitelable()){
							if (countMap.containsKey(type)) {
								countMap.put(type, countMap.get(type) + 1);
							} else {
								countMap.put(type, 1);
							}
						}
					}
				}
			}
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return countMap;

	}

	public  CompanyConfig getAppKeyByCompanyId(int companyId){
		CompanyConfig  configObj =null;
		Session session=null;
		try{
			Conjunction  conjunction=Restrictions.conjunction();
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CompanyConfig.class);
			conjunction.add(Restrictions.eq("company_id", companyId));
			conjunction.add(Restrictions.eq("isActive", true));
			criteria.add(conjunction);
			criteria.createCriteria("companyConfigType").add(Restrictions.eq("isB2B", true));
			configObj=(CompanyConfig) criteria.list().get(0);

		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return configObj;
	}
	public  List<CompanyConfig> fetchAllCompanyConfigs(){
		List<CompanyConfig>  configList =null;
		Session session=null;
		try{
			Conjunction  conjunction=Restrictions.conjunction();
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CompanyConfig.class);
			conjunction.add(Restrictions.eq("isActive", true));
			criteria.add(conjunction);
			//criteria.createCriteria("companyConfigType").add(Restrictions.eq("isB2B", true));
			configList=criteria.list();

		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return configList;
	}
	public  List<CompanyConfig> fetchAllCompanyConfigsByConfigIds(List<Integer> configIds){
		List<CompanyConfig>  configList =null;
		Session session=null;
		try{
			Conjunction  conjunction=Restrictions.conjunction();
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CompanyConfig.class);
			conjunction.add(Restrictions.not(Restrictions.in("config_id", configIds)));
			conjunction.add(Restrictions.eq("isActive", true));
			criteria.add(conjunction);
			configList=criteria.list();
		}
		catch(HibernateException e){
			logger.error("---------HibernateException---------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return configList;
	}

	public FlightDomesticServiceTaxConfig insertFlightDomesticServiceTaxConfig(FlightDomesticServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public FlightInternationalServiceTaxConfig insertFlightInternationalServiceTaxConfig(FlightInternationalServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));			
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public HotelServiceTaxConfig insertHotelServiceTaxConfig(HotelServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public BusServiceTaxConfig insertBusServiceTaxConfig(BusServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public RailServiceTaxConfig insertRailServiceTaxConfig(RailServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public CarServiceTaxConfig insertCarServiceTaxConfig(CarServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public HolidayServiceTaxConfig insertHolidayServiceTaxConfig(HolidayServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public AdvertiseandOtherServiceTaxConfig insertAdvertiseandOtherServiceTaxConfig(AdvertiseandOtherServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public VisaServiceTaxConfig insertVisaServiceTaxConfig(VisaServiceTaxConfig fdstc) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}


	/////

	public FlightDomesticServiceTaxConfig updateFlightDomesticServiceTaxConfig(FlightDomesticServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public FlightInternationalServiceTaxConfig updateFlightInternationalServiceTaxConfig(FlightInternationalServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public HotelServiceTaxConfig updateHotelServiceTaxConfig(HotelServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public BusServiceTaxConfig updateBusServiceTaxConfig(BusServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public RailServiceTaxConfig updateRailServiceTaxConfig(RailServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public CarServiceTaxConfig updateCarServiceTaxConfig(CarServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public HolidayServiceTaxConfig updateHolidayServiceTaxConfig(HolidayServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public AdvertiseandOtherServiceTaxConfig updateAdvertiseandOtherServiceTaxConfig(AdvertiseandOtherServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}
	public VisaServiceTaxConfig updateVisaServiceTaxConfig(VisaServiceTaxConfig fdstc,long id) throws Exception
	{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			fdstc.setId(id);
			fdstc.setUpdatedAt(new Timestamp(new Date().getTime()));	
			session.saveOrUpdate(fdstc);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return fdstc	;
	}

	public FlightInternationalServiceTaxConfig updateFlightInternationalServiceTax(FlightInternationalServiceTaxConfig flightInternationalServiceTaxConfig) {
		// TODO Auto-generated method stub
		FlightInternationalServiceTaxConfig flightInternationalServiceTaxConfigNew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			flightInternationalServiceTaxConfigNew = (FlightInternationalServiceTaxConfig) session.get(FlightInternationalServiceTaxConfig.class, flightInternationalServiceTaxConfig.getId());
			flightInternationalServiceTaxConfigNew.setApplicableFare(flightInternationalServiceTaxConfig.getApplicableFare());
			flightInternationalServiceTaxConfigNew.setBasicTax(flightInternationalServiceTaxConfig.getBasicTax());
			flightInternationalServiceTaxConfigNew.setConvenienceFee(flightInternationalServiceTaxConfig.getConvenienceFee());
			flightInternationalServiceTaxConfigNew.setKrishiKalyanCess(flightInternationalServiceTaxConfig.getKrishiKalyanCess());
			flightInternationalServiceTaxConfigNew.setManagementFee(flightInternationalServiceTaxConfig.getManagementFee());
			flightInternationalServiceTaxConfigNew.setSwatchBharathCess(flightInternationalServiceTaxConfig.getSwatchBharathCess());
			flightInternationalServiceTaxConfigNew.setTotalTax(flightInternationalServiceTaxConfig.getTotalTax());
			flightInternationalServiceTaxConfigNew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(flightInternationalServiceTaxConfigNew);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return flightInternationalServiceTaxConfigNew;
	}

	public FlightDomesticServiceTaxConfig updateFlightDomesticServiceTax(FlightDomesticServiceTaxConfig flightDomesticServiceTaxConfig) {
		// TODO Auto-generated method stub
		FlightDomesticServiceTaxConfig flightDomesticServiceTaxConfigNew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			flightDomesticServiceTaxConfigNew=(FlightDomesticServiceTaxConfig) session.get(FlightDomesticServiceTaxConfig.class, flightDomesticServiceTaxConfig.getId());
			flightDomesticServiceTaxConfigNew.setApplicableFare(flightDomesticServiceTaxConfig.getApplicableFare());
			flightDomesticServiceTaxConfigNew.setBasicTax(flightDomesticServiceTaxConfig.getBasicTax());
			flightDomesticServiceTaxConfigNew.setConvenienceFee(flightDomesticServiceTaxConfig.getConvenienceFee());
			flightDomesticServiceTaxConfigNew.setKrishiKalyanCess(flightDomesticServiceTaxConfig.getKrishiKalyanCess());
			flightDomesticServiceTaxConfigNew.setManagementFee(flightDomesticServiceTaxConfig.getManagementFee());
			flightDomesticServiceTaxConfigNew.setSwatchBharathCess(flightDomesticServiceTaxConfig.getSwatchBharathCess());
			flightDomesticServiceTaxConfigNew.setTotalTax(flightDomesticServiceTaxConfig.getTotalTax());
			flightDomesticServiceTaxConfigNew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(flightDomesticServiceTaxConfigNew);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}
		return flightDomesticServiceTaxConfigNew;

	}

	public HotelServiceTaxConfig updateHotelServiceTaxConfig(HotelServiceTaxConfig hotelServiceTaxConfig) {
		// TODO Auto-generated method stub
		HotelServiceTaxConfig HotelServiceTaxConfignew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			HotelServiceTaxConfignew=(HotelServiceTaxConfig) session.get(HotelServiceTaxConfig.class, hotelServiceTaxConfig.getId());
			HotelServiceTaxConfignew.setApplicableFare(hotelServiceTaxConfig.getApplicableFare());
			HotelServiceTaxConfignew.setBasicTax(hotelServiceTaxConfig.getBasicTax());
			HotelServiceTaxConfignew.setConvenienceFee(hotelServiceTaxConfig.getConvenienceFee());
			HotelServiceTaxConfignew.setKrishiKalyanCess(hotelServiceTaxConfig.getKrishiKalyanCess());
			HotelServiceTaxConfignew.setManagementFee(hotelServiceTaxConfig.getManagementFee());
			HotelServiceTaxConfignew.setDomesticManagementFee(hotelServiceTaxConfig.getDomesticManagementFee());
			HotelServiceTaxConfignew.setSwatchBharathCess(hotelServiceTaxConfig.getSwatchBharathCess());
			HotelServiceTaxConfignew.setTotalTax(hotelServiceTaxConfig.getTotalTax());
			HotelServiceTaxConfignew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(HotelServiceTaxConfignew);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}
		return HotelServiceTaxConfignew;

	}


	public HolidayServiceTaxConfig updateHolidayServiceTaxConfig(HolidayServiceTaxConfig holidayServiceTaxConfig) {
		// TODO Auto-generated method stub
		HolidayServiceTaxConfig holidayServiceTaxConfignew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			holidayServiceTaxConfignew=(HolidayServiceTaxConfig) session.get(HolidayServiceTaxConfig.class, holidayServiceTaxConfig.getId());
			holidayServiceTaxConfignew.setApplicableFare(holidayServiceTaxConfig.getApplicableFare());
			holidayServiceTaxConfignew.setBasicTax(holidayServiceTaxConfig.getBasicTax());
			holidayServiceTaxConfignew.setConvenienceFee(holidayServiceTaxConfig.getConvenienceFee());
			holidayServiceTaxConfignew.setKrishiKalyanCess(holidayServiceTaxConfig.getKrishiKalyanCess());
			holidayServiceTaxConfignew.setManagementFee(holidayServiceTaxConfig.getManagementFee());
			holidayServiceTaxConfignew.setDomesticManagementFee(holidayServiceTaxConfig.getDomesticManagementFee());
			holidayServiceTaxConfignew.setSwatchBharathCess(holidayServiceTaxConfig.getSwatchBharathCess());
			holidayServiceTaxConfignew.setTotalTax(holidayServiceTaxConfig.getTotalTax());
			holidayServiceTaxConfignew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(holidayServiceTaxConfignew);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}
		return holidayServiceTaxConfignew;

	}

	public BusServiceTaxConfig updateBusServiceTaxConfig(BusServiceTaxConfig busServiceTaxConfig) {
		// TODO Auto-generated method stub
		BusServiceTaxConfig BusServiceTaxConfignew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			BusServiceTaxConfignew= (BusServiceTaxConfig) session.get(BusServiceTaxConfig.class, busServiceTaxConfig.getId());
			BusServiceTaxConfignew.setApplicableFare(busServiceTaxConfig.getApplicableFare());
			BusServiceTaxConfignew.setBasicTax(busServiceTaxConfig.getBasicTax());
			BusServiceTaxConfignew.setConvenienceFee(busServiceTaxConfig.getConvenienceFee());
			BusServiceTaxConfignew.setKrishiKalyanCess(busServiceTaxConfig.getKrishiKalyanCess());
			BusServiceTaxConfignew.setManagementFee(busServiceTaxConfig.getManagementFee());
			BusServiceTaxConfignew.setSwatchBharathCess(busServiceTaxConfig.getSwatchBharathCess());
			BusServiceTaxConfignew.setTotalTax(busServiceTaxConfig.getTotalTax());
			BusServiceTaxConfignew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(busServiceTaxConfig);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}
		return BusServiceTaxConfignew;

	}

	public CarServiceTaxConfig updateCarServiceTaxConfig(CarServiceTaxConfig carServiceTaxConfig) {
		// TODO Auto-generated method stub
		CarServiceTaxConfig CarServiceTaxConfignew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CarServiceTaxConfignew= (CarServiceTaxConfig) session.get(CarServiceTaxConfig.class, carServiceTaxConfig.getId());
			CarServiceTaxConfignew.setApplicableFare(carServiceTaxConfig.getApplicableFare());
			CarServiceTaxConfignew.setBasicTax(carServiceTaxConfig.getBasicTax());
			CarServiceTaxConfignew.setConvenienceFee(carServiceTaxConfig.getConvenienceFee());
			CarServiceTaxConfignew.setKrishiKalyanCess(carServiceTaxConfig.getKrishiKalyanCess());
			CarServiceTaxConfignew.setManagementFee(carServiceTaxConfig.getManagementFee());
			CarServiceTaxConfignew.setSwatchBharathCess(carServiceTaxConfig.getSwatchBharathCess());
			CarServiceTaxConfignew.setTotalTax(carServiceTaxConfig.getTotalTax());
			CarServiceTaxConfignew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(CarServiceTaxConfignew);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}
		return CarServiceTaxConfignew;

	}

	public RailServiceTaxConfig updateRailServiceTaxConfig(RailServiceTaxConfig railServiceTaxConfig) {
		// TODO Auto-generated method stub
		RailServiceTaxConfig RailServiceTaxConfignew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			RailServiceTaxConfignew= (RailServiceTaxConfig) session.get(RailServiceTaxConfig.class, railServiceTaxConfig.getId());
			RailServiceTaxConfignew.setApplicableFare(railServiceTaxConfig.getApplicableFare());
			RailServiceTaxConfignew.setBasicTax(railServiceTaxConfig.getBasicTax());
			RailServiceTaxConfignew.setConvenienceFee(railServiceTaxConfig.getConvenienceFee());
			RailServiceTaxConfignew.setKrishiKalyanCess(railServiceTaxConfig.getKrishiKalyanCess());
			RailServiceTaxConfignew.setManagementFee(railServiceTaxConfig.getManagementFee());
			RailServiceTaxConfignew.setSwatchBharathCess(railServiceTaxConfig.getSwatchBharathCess());
			RailServiceTaxConfignew.setTotalTax(railServiceTaxConfig.getTotalTax());
			RailServiceTaxConfignew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(RailServiceTaxConfignew);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}
		return RailServiceTaxConfignew;

	}
	public AdvertiseandOtherServiceTaxConfig updateAdvertiseandOtherServiceTaxConfig(AdvertiseandOtherServiceTaxConfig advertiseandOtherServiceTaxConfig) {
		// TODO Auto-generated method stub
		AdvertiseandOtherServiceTaxConfig AdvertiseandOtherServiceTaxConfignew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			AdvertiseandOtherServiceTaxConfignew= (AdvertiseandOtherServiceTaxConfig) session.get(AdvertiseandOtherServiceTaxConfig.class, advertiseandOtherServiceTaxConfig.getId());
			AdvertiseandOtherServiceTaxConfignew.setApplicableFare(advertiseandOtherServiceTaxConfig.getApplicableFare());
			AdvertiseandOtherServiceTaxConfignew.setBasicTax(advertiseandOtherServiceTaxConfig.getBasicTax());
			AdvertiseandOtherServiceTaxConfignew.setConvenienceFee(advertiseandOtherServiceTaxConfig.getConvenienceFee());
			AdvertiseandOtherServiceTaxConfignew.setKrishiKalyanCess(advertiseandOtherServiceTaxConfig.getKrishiKalyanCess());
			AdvertiseandOtherServiceTaxConfignew.setManagementFee(advertiseandOtherServiceTaxConfig.getManagementFee());
			AdvertiseandOtherServiceTaxConfignew.setSwatchBharathCess(advertiseandOtherServiceTaxConfig.getSwatchBharathCess());
			AdvertiseandOtherServiceTaxConfignew.setTotalTax(advertiseandOtherServiceTaxConfig.getTotalTax());
			AdvertiseandOtherServiceTaxConfignew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(AdvertiseandOtherServiceTaxConfignew);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}
		return AdvertiseandOtherServiceTaxConfignew;

	}

	public VisaServiceTaxConfig updateVisaServiceTaxConfig(VisaServiceTaxConfig visaServiceTaxConfig) {
		// TODO Auto-generated method stub
		VisaServiceTaxConfig VisaServiceTaxConfignew = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			VisaServiceTaxConfignew= (VisaServiceTaxConfig) session.get(VisaServiceTaxConfig.class, visaServiceTaxConfig.getId());
			VisaServiceTaxConfignew.setApplicableFare(visaServiceTaxConfig.getApplicableFare());
			VisaServiceTaxConfignew.setBasicTax(visaServiceTaxConfig.getBasicTax());
			VisaServiceTaxConfignew.setConvenienceFee(visaServiceTaxConfig.getConvenienceFee());
			VisaServiceTaxConfignew.setKrishiKalyanCess(visaServiceTaxConfig.getKrishiKalyanCess());
			VisaServiceTaxConfignew.setManagementFee(visaServiceTaxConfig.getManagementFee());
			VisaServiceTaxConfignew.setSwatchBharathCess(visaServiceTaxConfig.getSwatchBharathCess());
			VisaServiceTaxConfignew.setTotalTax(visaServiceTaxConfig.getTotalTax());
			VisaServiceTaxConfignew.setUpdatedAt(new Timestamp(new  Date().getTime()));
			session.saveOrUpdate(VisaServiceTaxConfignew);
			transaction.commit();

		}
		catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("-------------Exception-----------------"+e.getMessage());
		}
		return VisaServiceTaxConfignew;

	}
 

	/*get CompanyConfig  Details from DB */
	public CompanyConfig getConfigByComnpanyId(int companyId){
		Company company=new CompanyDAO().getCompanyProfile(companyId);
		CompanyConfig configDetailsObj=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CompanyConfig.class);
			Conjunction conjunction=Restrictions.conjunction();
			conjunction.add(Restrictions.eq("company_id",companyId));
			conjunction.add(Restrictions.eq("isActive",new Boolean(true)));
			criteria.add(conjunction);
			List<CompanyConfig> list = criteria.list();
			if(list!=null){
				for(CompanyConfig configDetails:list){
					if(configDetails.getCompanyConfigType()!=null){
						if(company.getCompanyRole().isCorporate()){
							if(configDetails.getCompanyConfigType().isB2E()){
								configDetailsObj=configDetails;
							}
						}
						else{
							if(configDetails.getCompanyConfigType().isB2B()){
								configDetailsObj=configDetails;
							}					

						}

					}

				}


			}
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return configDetailsObj;
	}
	
	
	public CompanyConfigTax getConfigByComnpanyIdForB2ETaxes(int companyId){
		CompanyConfigTax companyConfigTax=new CompanyConfigTax();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CompanyConfig.class);
			Conjunction conjunction=Restrictions.conjunction();
			conjunction.add(Restrictions.eq("company_id",companyId));
			conjunction.add(Restrictions.eq("isActive",new Boolean(true)));
			criteria.add(conjunction);
			List<CompanyConfig> list = criteria.list();
			if(list!=null){
				for(CompanyConfig configDetails:list){
					if(configDetails.getCompanyConfigType().isB2E()){
						if(configDetails.getTaxtype().equalsIgnoreCase("GST")){
							companyConfigTax.setGstTaxObj(configDetails);
						}
						if(configDetails.getTaxtype().equalsIgnoreCase("servicetax")){
							companyConfigTax.setServiceTaxObj(configDetails);
					 }
						/*if(configDetails.getTaxtype().equalsIgnoreCase("commonDSRReport")){
							companyConfigTax.setCommonDSRreportObj(configDetails);
					 
						
					}*/
					}
					else{
						if(configDetails.getCompanyConfigType().isB2B()){
							if(configDetails.getTaxtype()!=null){
							if(configDetails.getTaxtype().equalsIgnoreCase("GST")){
								companyConfigTax.setGstTaxObj(configDetails);
							}
							if(configDetails.getTaxtype().equalsIgnoreCase("servicetax")){
								companyConfigTax.setServiceTaxObj(configDetails);
						 }
							}	
						}
					}/*else if(configDetails.getCompanyConfigType().isB2B()){
						if(configDetails.getTaxtype().equalsIgnoreCase("commonDSRReport")){
							companyConfigTax.setCommonDSRreportObj(configDetails);
						
					 
				}
					}*/
					
						
					}


			}
			
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyConfigTax;
	}
	public FlightInternationalGstTaxConfig insertFlightInternationalGstTaxConfig(FlightInternationalGstTaxConfig flightInternationalGstTaxConfig) throws Exception {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			flightInternationalGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(flightInternationalGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return flightInternationalGstTaxConfig;
	}

	public FlightDomesticGstTaxConfig insertFlightDomesticGstTaxConfig(FlightDomesticGstTaxConfig flightDomesticGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			flightDomesticGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(flightDomesticGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return flightDomesticGstTaxConfig;
	}

	public HotelGstTaxConfig insertHotelGstTaxConfig(HotelGstTaxConfig hotelGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			hotelGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(hotelGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return hotelGstTaxConfig;
	}
	public CarGstTaxConfig insertCarGstTaxConfig(CarGstTaxConfig carGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(carGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return carGstTaxConfig;
	}

	public BusGstTaxConfig insertBusGstTaxConfig(BusGstTaxConfig busGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			busGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(busGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return busGstTaxConfig;
	}
	public TrainGstTaxConfig insertTrainGstTaxConfig(TrainGstTaxConfig trainGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			trainGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(trainGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return trainGstTaxConfig;
	}
	public VisaGstTaxConfig insertVisaGstTaxConfig(VisaGstTaxConfig visaGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			visaGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(visaGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return visaGstTaxConfig;
	}

	public InsuranceGstTaxConfig insertInsuranceGstTaxConfig(InsuranceGstTaxConfig insuranceGstTaxConfig) throws Exception {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			insuranceGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(insuranceGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return insuranceGstTaxConfig;
	}

	public HolidayGstTaxConfig insertHolidayGstTaxConfig(HolidayGstTaxConfig holidayGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			holidayGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(holidayGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return holidayGstTaxConfig;
	}

	public AdvertisementGstTaxConfig insertAdvertisementGstTaxConfig(AdvertisementGstTaxConfig advertisementGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			advertisementGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(advertisementGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return advertisementGstTaxConfig;
	}
	
	
	
	
	//added by basha for updating the gst components
	public FlightInternationalGstTaxConfig updateFlightInternationalGstTaxConfig(FlightInternationalGstTaxConfig flightInternationalGstTaxConfig) {
		FlightInternationalGstTaxConfig flightInternationalGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			flightInternationalGstTaxConfignew = (FlightInternationalGstTaxConfig) session.get(FlightInternationalGstTaxConfig.class, flightInternationalGstTaxConfig.getId());
			flightInternationalGstTaxConfignew.setApplicableFare(flightInternationalGstTaxConfig.getApplicableFare());
			flightInternationalGstTaxConfignew.setConvenienceFee(flightInternationalGstTaxConfig.getConvenienceFee());
			flightInternationalGstTaxConfignew.setManagementFee(flightInternationalGstTaxConfig.getManagementFee());
			flightInternationalGstTaxConfignew.setCGST(flightInternationalGstTaxConfig.getCGST());
			flightInternationalGstTaxConfignew.setIGST(flightInternationalGstTaxConfig.getIGST());
			flightInternationalGstTaxConfignew.setSGST(flightInternationalGstTaxConfig.getSGST());
			flightInternationalGstTaxConfignew.setUGST(flightInternationalGstTaxConfig.getUGST());
		
			flightInternationalGstTaxConfignew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(flightInternationalGstTaxConfignew);
		    transaction.commit();
	}
	catch (HibernateException e) {
		if(transaction!=null){
			transaction.rollback();
		}
		logger.error("--------------HibernateException-----------------"+e.getMessage());
	}
	catch (Exception e) {
		if(transaction!=null){
			transaction.rollback();
		}
		logger.error("-------------Exception-----------------"+e.getMessage());
	}

	finally {
		if(session != null && session.isOpen())
			session.close(); 
	}
		return flightInternationalGstTaxConfignew;
	}

	public FlightDomesticGstTaxConfig updateFlightDomesticGstTaxConfig(FlightDomesticGstTaxConfig flightDomesticGstTaxConfig) {
		FlightDomesticGstTaxConfig flightDomesticGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			flightDomesticGstTaxConfignew = (FlightDomesticGstTaxConfig) session.get(FlightDomesticGstTaxConfig.class, flightDomesticGstTaxConfig.getId());
			flightDomesticGstTaxConfignew.setApplicableFare(flightDomesticGstTaxConfig.getApplicableFare());
			flightDomesticGstTaxConfignew.setConvenienceFee(flightDomesticGstTaxConfig.getConvenienceFee());
			flightDomesticGstTaxConfignew.setManagementFee(flightDomesticGstTaxConfig.getManagementFee());
			flightDomesticGstTaxConfignew.setCGST(flightDomesticGstTaxConfig.getCGST());
			flightDomesticGstTaxConfignew.setIGST(flightDomesticGstTaxConfig.getIGST());
			flightDomesticGstTaxConfignew.setSGST(flightDomesticGstTaxConfig.getSGST());
			flightDomesticGstTaxConfignew.setUGST(flightDomesticGstTaxConfig.getUGST());
			
			flightDomesticGstTaxConfignew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(flightDomesticGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		// TODO Auto-generated method stub
		return flightDomesticGstTaxConfignew;
	}

	public HotelGstTaxConfig updateHotelGstTaxConfig(HotelGstTaxConfig hotelGstTaxConfig) {
		HotelGstTaxConfig hotelGstTaxConfignew=null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			hotelGstTaxConfignew = (HotelGstTaxConfig) session.get(HotelGstTaxConfig.class, hotelGstTaxConfig.getId());
			hotelGstTaxConfignew.setApplicableFare(hotelGstTaxConfig.getApplicableFare());
			hotelGstTaxConfignew.setConvenienceFee(hotelGstTaxConfig.getConvenienceFee());
			hotelGstTaxConfignew.setIntlManagementFee(hotelGstTaxConfig.getIntlManagementFee());
			hotelGstTaxConfignew.setDomesticManagementFee(hotelGstTaxConfig.getDomesticManagementFee());
			hotelGstTaxConfignew.setCGST(hotelGstTaxConfig.getCGST());
			hotelGstTaxConfignew.setIGST(hotelGstTaxConfig.getIGST());
			hotelGstTaxConfignew.setSGST(hotelGstTaxConfig.getSGST());
			hotelGstTaxConfignew.setUGST(hotelGstTaxConfig.getUGST());
			
			hotelGstTaxConfignew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(hotelGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		// TODO Auto-generated method stub
		return hotelGstTaxConfignew;
	}
	
	

	public CarGstTaxConfig updateCarGstTaxConfig(CarGstTaxConfig carGstTaxConfig) {
		CarGstTaxConfig carGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carGstTaxConfignew = (CarGstTaxConfig) session.get(CarGstTaxConfig.class, carGstTaxConfig.getId());
			carGstTaxConfignew.setApplicableFare(carGstTaxConfig.getApplicableFare());
			carGstTaxConfignew.setConvenienceFee(carGstTaxConfig.getConvenienceFee());
			carGstTaxConfignew.setManagementFee(carGstTaxConfig.getManagementFee());
			carGstTaxConfignew.setCGST(carGstTaxConfig.getCGST());
			carGstTaxConfignew.setIGST(carGstTaxConfig.getIGST());
			carGstTaxConfignew.setSGST(carGstTaxConfig.getSGST());
			carGstTaxConfignew.setUGST(carGstTaxConfig.getUGST());
			
			carGstTaxConfignew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(carGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		return carGstTaxConfignew;
	}
	
	

	public BusGstTaxConfig updateBusGstTaxConfig(BusGstTaxConfig busGstTaxConfig) {
		BusGstTaxConfig busGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			busGstTaxConfignew = (BusGstTaxConfig) session.get(BusGstTaxConfig.class, busGstTaxConfig.getId());
			busGstTaxConfignew.setApplicableFare(busGstTaxConfig.getApplicableFare());
			busGstTaxConfignew.setConvenienceFee(busGstTaxConfig.getConvenienceFee());
			busGstTaxConfignew.setManagementFee(busGstTaxConfig.getManagementFee());
			busGstTaxConfignew.setCGST(busGstTaxConfig.getCGST());
			busGstTaxConfignew.setIGST(busGstTaxConfig.getIGST());
			busGstTaxConfignew.setSGST(busGstTaxConfig.getSGST());
			busGstTaxConfignew.setUGST(busGstTaxConfig.getUGST());
			
			busGstTaxConfignew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(busGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		return busGstTaxConfignew;
	}
	
	

	public TrainGstTaxConfig updateTrainGstTaxConfig(TrainGstTaxConfig trainGstTaxConfig) {
		TrainGstTaxConfig trainGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			trainGstTaxConfignew = (TrainGstTaxConfig) session.get(TrainGstTaxConfig.class, trainGstTaxConfig.getId());
			trainGstTaxConfignew.setApplicableFare(trainGstTaxConfig.getApplicableFare());
			trainGstTaxConfignew.setConvenienceFee(trainGstTaxConfig.getConvenienceFee());
			trainGstTaxConfignew.setManagementFee(trainGstTaxConfig.getManagementFee());
			trainGstTaxConfignew.setManagementFeeTatkal(trainGstTaxConfig.getManagementFeeTatkal());
			trainGstTaxConfignew.setCGST(trainGstTaxConfig.getCGST());
			trainGstTaxConfignew.setIGST(trainGstTaxConfig.getIGST());
			trainGstTaxConfignew.setSGST(trainGstTaxConfig.getSGST());
			trainGstTaxConfignew.setUGST(trainGstTaxConfig.getUGST());
			
			trainGstTaxConfignew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(trainGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		return trainGstTaxConfignew;
	}
	
	

	public VisaGstTaxConfig updateVisaGstTaxConfig(VisaGstTaxConfig visaGstTaxConfig) {
		VisaGstTaxConfig visaGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			visaGstTaxConfignew = (VisaGstTaxConfig) session.get(VisaGstTaxConfig.class, visaGstTaxConfig.getId());
			visaGstTaxConfignew.setApplicableFare(visaGstTaxConfig.getApplicableFare());
			visaGstTaxConfignew.setConvenienceFee(visaGstTaxConfig.getConvenienceFee());
			visaGstTaxConfignew.setManagementFee(visaGstTaxConfig.getManagementFee());
			visaGstTaxConfignew.setCGST(visaGstTaxConfig.getCGST());
			visaGstTaxConfignew.setIGST(visaGstTaxConfig.getIGST());
			visaGstTaxConfignew.setSGST(visaGstTaxConfig.getSGST());
			visaGstTaxConfignew.setUGST(visaGstTaxConfig.getUGST());
			
			visaGstTaxConfignew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(visaGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		return visaGstTaxConfignew;
	}
	
	

	public InsuranceGstTaxConfig updateInsuranceGstTaxConfig(InsuranceGstTaxConfig insuranceGstTaxConfig) {
		InsuranceGstTaxConfig insuranceGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			insuranceGstTaxConfignew = (InsuranceGstTaxConfig) session.get(InsuranceGstTaxConfig.class, insuranceGstTaxConfig.getId());
			insuranceGstTaxConfignew.setApplicableFare(insuranceGstTaxConfig.getApplicableFare());
			insuranceGstTaxConfignew.setConvenienceFee(insuranceGstTaxConfig.getConvenienceFee());
			insuranceGstTaxConfignew.setManagementFee(insuranceGstTaxConfig.getManagementFee());
			insuranceGstTaxConfignew.setCGST(insuranceGstTaxConfig.getCGST());
			insuranceGstTaxConfignew.setIGST(insuranceGstTaxConfig.getIGST());
			insuranceGstTaxConfignew.setSGST(insuranceGstTaxConfig.getSGST());
			insuranceGstTaxConfignew.setUGST(insuranceGstTaxConfig.getUGST());
		
			insuranceGstTaxConfignew.setUpdatedAt(new Timestamp(new Date().getTime()));
		
		session.saveOrUpdate(insuranceGstTaxConfignew);
	    transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		return insuranceGstTaxConfignew;
	}
	
	

	public HolidayGstTaxConfig updateHolidayGstTaxConfig(HolidayGstTaxConfig holidayGstTaxConfig) {
		HolidayGstTaxConfig holidayGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			holidayGstTaxConfignew = (HolidayGstTaxConfig) session.get(HolidayGstTaxConfig.class, holidayGstTaxConfig.getId());
			holidayGstTaxConfignew.setApplicableFare(holidayGstTaxConfig.getApplicableFare());
			holidayGstTaxConfignew.setConvenienceFee(holidayGstTaxConfig.getConvenienceFee());
			holidayGstTaxConfignew.setManagementFee(holidayGstTaxConfig.getManagementFee());
			holidayGstTaxConfignew.setCGST(holidayGstTaxConfig.getCGST());
			holidayGstTaxConfignew.setIGST(holidayGstTaxConfig.getIGST());
			holidayGstTaxConfignew.setSGST(holidayGstTaxConfig.getSGST());
			holidayGstTaxConfignew.setUGST(holidayGstTaxConfig.getUGST());
			holidayGstTaxConfignew.setTotalGst(holidayGstTaxConfig.getTotalGst());
			session.saveOrUpdate(holidayGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		return holidayGstTaxConfignew;
	}

	
	
	public AdvertisementGstTaxConfig updateAdvertisementGstTaxConfig(AdvertisementGstTaxConfig advertisementGstTaxConfig) {
		AdvertisementGstTaxConfig advertisementGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			advertisementGstTaxConfignew = (AdvertisementGstTaxConfig) session.get(AdvertisementGstTaxConfig.class, advertisementGstTaxConfig.getId());
			advertisementGstTaxConfignew.setApplicableFare(advertisementGstTaxConfig.getApplicableFare());
			advertisementGstTaxConfignew.setConvenienceFee(advertisementGstTaxConfig.getConvenienceFee());
			advertisementGstTaxConfignew.setManagementFee(advertisementGstTaxConfig.getManagementFee());
			advertisementGstTaxConfignew.setCGST(advertisementGstTaxConfig.getCGST());
			advertisementGstTaxConfignew.setIGST(advertisementGstTaxConfig.getIGST());
			advertisementGstTaxConfignew.setSGST(advertisementGstTaxConfig.getSGST());
			advertisementGstTaxConfignew.setUGST(advertisementGstTaxConfig.getUGST());
			
			session.saveOrUpdate(advertisementGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		return advertisementGstTaxConfignew;
	}

	
	
	public MiscellaneousGstTaxConfig insertMiscellaneousGstTaxConfig(MiscellaneousGstTaxConfig miscellaneousGstTaxConfig) throws Exception{
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			miscellaneousGstTaxConfig.setCreatedAt(new Timestamp(new Date().getTime()));	
			session.save(miscellaneousGstTaxConfig);
			transaction.commit();

		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			if (transaction!=null)
				transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return miscellaneousGstTaxConfig;
	}

	public MiscellaneousGstTaxConfig updateMiscellaneousGstTaxConfig(MiscellaneousGstTaxConfig miscellaneousGstTaxConfig) {
		// TODO Auto-generated method stub
		MiscellaneousGstTaxConfig miscellaneousGstTaxConfignew=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			miscellaneousGstTaxConfignew = (MiscellaneousGstTaxConfig) session.get(MiscellaneousGstTaxConfig.class, miscellaneousGstTaxConfig.getId());
			miscellaneousGstTaxConfignew.setApplicableFare(miscellaneousGstTaxConfig.getApplicableFare());
			miscellaneousGstTaxConfignew.setConvenienceFee(miscellaneousGstTaxConfig.getConvenienceFee());
			miscellaneousGstTaxConfignew.setManagementFee(miscellaneousGstTaxConfig.getManagementFee());
			miscellaneousGstTaxConfignew.setCGST(miscellaneousGstTaxConfig.getCGST());
			miscellaneousGstTaxConfignew.setIGST(miscellaneousGstTaxConfig.getIGST());
			miscellaneousGstTaxConfignew.setSGST(miscellaneousGstTaxConfig.getSGST());
			miscellaneousGstTaxConfignew.setUGST(miscellaneousGstTaxConfig.getUGST());
			
			session.saveOrUpdate(miscellaneousGstTaxConfignew);
			transaction.commit();
		}
		catch (HibernateException e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
	if(transaction!=null){
		transaction.rollback();
	}
	logger.error("-------------Exception-----------------"+e.getMessage());
		}

		finally {
	if(session != null && session.isOpen())
	session.close(); 
		}
		return miscellaneousGstTaxConfignew;
	}
	
	public  List<Integer> getCompanyConfigIdListByConfigType(String configType){
			List<Integer> configIdList = new ArrayList<Integer>();
			Session session = null;
			try{		
				session = HibernateUtil.getSessionFactory().openSession();
				Criteria criteria = session.createCriteria(CompanyConfigType.class);
				Conjunction reportConjunction = Restrictions.conjunction();
				
				if(configType.equalsIgnoreCase("B2B"))
					reportConjunction.add(Restrictions.eq("isB2B",true));
				if(configType.equalsIgnoreCase("B2C"))
					reportConjunction.add(Restrictions.eq("isB2C",true));
				if(configType.equalsIgnoreCase("B2E"))
					reportConjunction.add(Restrictions.eq("isB2E",true));
				if(configType.equalsIgnoreCase("WhiteLabel"))
					reportConjunction.add(Restrictions.eq("isWhitelable",true));
			
				criteria.setProjection(Projections.property("id"));
				criteria.add(reportConjunction);
				configIdList=criteria.list();
			}catch (Exception e) {
			}
		return configIdList;
	}
}
