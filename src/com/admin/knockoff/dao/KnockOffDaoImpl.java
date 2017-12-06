package com.admin.knockoff.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.knockoff.common.filter.page.KnockOffFilter;
import com.admin.knockoff.common.filter.page.KnockOffPage;
import com.admin.knockoff.common.filter.page.KnockOffUtility;
import com.admin.knockoff.vo.KnockOffVO;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.CommonDsrDao;
import com.isl.admin.page.Page;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;
public class KnockOffDaoImpl implements KnockOffDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(KnockOffDaoImpl.class);
	FlightOrderDao flightOrderDao=new FlightOrderDao();
	@Override
	public KnockOffPage commonKnockOffBookings(KnockOffPage knockOffPage) {
		// TODO Auto-generated method stub
		List<KnockOffVO> allKnockOffVOReports=new ArrayList<>();
		List<FlightOrderRow> flightOrderRowList= knockOffPage!=null && knockOffPage.getFlightRows()!=null?knockOffPage.getFlightRows():null;
		List<HotelOrderRow> hotelOrderRowList= knockOffPage!=null && knockOffPage.getHotelRows()!=null?knockOffPage.getHotelRows():null;
		List<CarOrderRow> carOrderRowList= knockOffPage!=null && knockOffPage.getCarRows()!=null?knockOffPage.getCarRows():null;
		List<BusOrderRow> busOrderRowList= knockOffPage!=null && knockOffPage.getBusRows()!=null?knockOffPage.getBusRows():null;
		List<TrainOrderRow> trainOrderRowList= knockOffPage!=null && knockOffPage.getTrainRows()!=null?knockOffPage.getTrainRows():null;
		List<VisaOrderRow> visaOrderRowList= knockOffPage!=null && knockOffPage.getVisaRows()!=null?knockOffPage.getVisaRows():null;
		List<InsuranceOrderRow> insuranceOrderRowList=knockOffPage!=null && knockOffPage.getInsuranceRows()!=null?knockOffPage.getInsuranceRows():null;
		List<MiscellaneousOrderRow> miscellaneousOrderRowList=knockOffPage!=null && knockOffPage.getMiscellaneousRows()!=null?knockOffPage.getMiscellaneousRows():null;
		if(flightOrderRowList!=null) 
			flightKnockOffBookings(knockOffPage, allKnockOffVOReports);
		if(hotelOrderRowList!=null) 
			hotelKnockOffBookings(knockOffPage, allKnockOffVOReports);
		/*if(carOrderRowList!=null){

		}
		if(busOrderRowList!=null){

		}
		if(trainOrderRowList!=null){

		}
		if(visaOrderRowList!=null){

		}
		if(insuranceOrderRowList!=null){

		}
		if(miscellaneousOrderRowList!=null){

		}
		 */
		return knockOffPage;
	}
	@Override
	public KnockOffPage flightKnockOffBookings(KnockOffPage knockOffPage) {
		// TODO Auto-generated method stub
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			if(knockOffPage!=null && knockOffPage.isFilterEnabled())
			{
				KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(knockOffFilter.getLoginCompany(),-1,"");
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setFlightRows(new ArrayList<FlightOrderRow>());
					return knockOffPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				disjunction.add(Restrictions.eq("statusAction","Confirmed")).add(Restrictions.eq("statusAction","Cancelled"));
				/*if(knockOffFilter.getKnockOff() != null && !knockOffFilter.getKnockOff().equals("ALL"))
					reportConjunction.add(Restrictions.like("knockOff", Boolean.valueOf(knockOffFilter.getKnockOff())));*/
				criteria.add(reportConjunction).add(disjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<FlightOrderRow> list =null;
			if(rowCount>0)
			{
				if(knockOffPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(FlightOrderRow.class);
					criteria.add(reportConjunction).add(disjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					knockOffPage.setAvailableItems(list.size());
					knockOffPage.setAvailablePages(1);
				}
				else{
					if(knockOffPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % knockOffPage.getMaxItems() == 0)?(availableItems / knockOffPage.getMaxItems()):((availableItems / knockOffPage.getMaxItems()) + 1);
						knockOffPage.setAvailableItems(availableItems);
						knockOffPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (knockOffPage.getCurrentPageIndex() > 1)?knockOffPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * knockOffPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(FlightOrderRow.class);
						criteria.add(reportConjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(knockOffPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					knockOffPage.setFlightRows(list);
				}
				else
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setFlightRows(new ArrayList<FlightOrderRow>());
				}

			}
			else
			{
				knockOffPage.setAvailableItems(0);
				knockOffPage.setAvailablePages(0);
				knockOffPage.setFlightRows(new ArrayList<FlightOrderRow>());
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

		return knockOffPage;
	}

	@Override
	public KnockOffPage hotelKnockOffBookings(KnockOffPage knockOffPage) {
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(HotelOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			if(knockOffPage!=null && knockOffPage.isFilterEnabled())
			{
				KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(knockOffFilter.getLoginCompany(),-1,"");
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setFlightRows(new ArrayList<FlightOrderRow>());
					return knockOffPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				//reportConjunction.add(Restrictions.eq("knockOff", true));
				disjunction.add(Restrictions.eq("statusAction","Confirmed")).add(Restrictions.eq("statusAction","Cancelled"));
				/*	if(knockOffFilter.getKnockOff() != null && !knockOffFilter.getKnockOff().equals("ALL"))
					reportConjunction.add(Restrictions.like("knockOff", Boolean.valueOf(knockOffFilter.getKnockOff())));
				 */
				criteria.add(reportConjunction).add(disjunction);

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<HotelOrderRow> list =null;
			if(rowCount>0)
			{
				if(knockOffPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(HotelOrderRow.class);
					criteria.add(reportConjunction).add(disjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					knockOffPage.setAvailableItems(list.size());
					knockOffPage.setAvailablePages(1);
				}
				else{
					if(knockOffPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % knockOffPage.getMaxItems() == 0)?(availableItems / knockOffPage.getMaxItems()):((availableItems / knockOffPage.getMaxItems()) + 1);
						knockOffPage.setAvailableItems(availableItems);
						knockOffPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (knockOffPage.getCurrentPageIndex() > 1)?knockOffPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * knockOffPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(HotelOrderRow.class);
						criteria.add(reportConjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(knockOffPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					knockOffPage.setHotelRows(list);
				}
				else
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setHotelRows(new ArrayList<HotelOrderRow>());
				}

			}
			else
			{
				knockOffPage.setAvailableItems(0);
				knockOffPage.setAvailablePages(0);
				knockOffPage.setHotelRows(new ArrayList<HotelOrderRow>());
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

		return knockOffPage;
	}

	@Override
	public KnockOffPage carKnockOffBookings(KnockOffPage knockOffPage) {
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CarOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			if(knockOffPage!=null && knockOffPage.isFilterEnabled())
			{
				KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(knockOffFilter.getLoginCompany(),-1,"");
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setCarRows(new ArrayList<CarOrderRow>());
					return knockOffPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				//reportConjunction.add(Restrictions.eq("knockOff", true));
				disjunction.add(Restrictions.eq("statusAction","Confirmed")).add(Restrictions.eq("statusAction","Cancelled"));
				/*if(knockOffFilter.getKnockOff() != null && !knockOffFilter.getKnockOff().equals("ALL"))
					reportConjunction.add(Restrictions.like("knockOff", Boolean.valueOf(knockOffFilter.getKnockOff())));*/

				criteria.add(reportConjunction).add(disjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<CarOrderRow> list =null;
			if(rowCount>0)
			{
				if(knockOffPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(CarOrderRow.class);
					criteria.add(reportConjunction).add(disjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					knockOffPage.setAvailableItems(list.size());
					knockOffPage.setAvailablePages(1);
				}
				else{
					if(knockOffPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % knockOffPage.getMaxItems() == 0)?(availableItems / knockOffPage.getMaxItems()):((availableItems / knockOffPage.getMaxItems()) + 1);
						knockOffPage.setAvailableItems(availableItems);
						knockOffPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (knockOffPage.getCurrentPageIndex() > 1)?knockOffPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * knockOffPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(CarOrderRow.class);
						criteria.add(reportConjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(knockOffPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					knockOffPage.setCarRows(list);
				}
				else
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setCarRows(new ArrayList<CarOrderRow>());
				}

			}
			else
			{
				knockOffPage.setAvailableItems(0);
				knockOffPage.setAvailablePages(0);
				knockOffPage.setCarRows(new ArrayList<CarOrderRow>());
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

		return knockOffPage;
	}

	@Override
	public KnockOffPage busKnockOffBookings(KnockOffPage knockOffPage) {
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			if(knockOffPage!=null && knockOffPage.isFilterEnabled())
			{
				KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(knockOffFilter.getLoginCompany(),-1,"");
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setBusRows(new ArrayList<BusOrderRow>());
					return knockOffPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				//reportConjunction.add(Restrictions.eq("knockOff", true));
				disjunction.add(Restrictions.eq("statusAction","Confirmed")).add(Restrictions.eq("statusAction","Cancelled"));
				/*if(knockOffFilter.getKnockOff() != null && !knockOffFilter.getKnockOff().equals("ALL"))
					reportConjunction.add(Restrictions.like("knockOff", Boolean.valueOf(knockOffFilter.getKnockOff())));*/

				criteria.add(reportConjunction).add(disjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<BusOrderRow> list =null;
			if(rowCount>0)
			{
				if(knockOffPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(BusOrderRow.class);
					criteria.add(reportConjunction).add(disjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					knockOffPage.setAvailableItems(list.size());
					knockOffPage.setAvailablePages(1);
				}
				else{
					if(knockOffPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % knockOffPage.getMaxItems() == 0)?(availableItems / knockOffPage.getMaxItems()):((availableItems / knockOffPage.getMaxItems()) + 1);
						knockOffPage.setAvailableItems(availableItems);
						knockOffPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (knockOffPage.getCurrentPageIndex() > 1)?knockOffPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * knockOffPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(BusOrderRow.class);
						criteria.add(reportConjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(knockOffPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					knockOffPage.setBusRows(list);
				}
				else
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setBusRows(new ArrayList<BusOrderRow>());
				}

			}
			else
			{
				knockOffPage.setAvailableItems(0);
				knockOffPage.setAvailablePages(0);
				knockOffPage.setBusRows(new ArrayList<BusOrderRow>());
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

		return knockOffPage;
	}

	@Override
	public KnockOffPage trainKnockOffBookings(KnockOffPage knockOffPage) {
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(TrainOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			if(knockOffPage!=null && knockOffPage.isFilterEnabled())
			{
				KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(knockOffFilter.getLoginCompany(),-1,"");
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setTrainRows(new ArrayList<TrainOrderRow>());
					return knockOffPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				/*reportConjunction.add(Restrictions.eq("knockOff", true));*/
				disjunction.add(Restrictions.eq("statusAction","Confirmed")).add(Restrictions.eq("statusAction","Cancelled"));
				/*if(knockOffFilter.getKnockOff() != null && !knockOffFilter.getKnockOff().equals("ALL"))
					reportConjunction.add(Restrictions.like("knockOff", Boolean.valueOf(knockOffFilter.getKnockOff())));*/

				criteria.add(reportConjunction).add(disjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<TrainOrderRow> list =null;
			if(rowCount>0)
			{
				if(knockOffPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(TrainOrderRow.class);
					criteria.add(reportConjunction).add(disjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					knockOffPage.setAvailableItems(list.size());
					knockOffPage.setAvailablePages(1);
				}
				else{
					if(knockOffPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % knockOffPage.getMaxItems() == 0)?(availableItems / knockOffPage.getMaxItems()):((availableItems / knockOffPage.getMaxItems()) + 1);
						knockOffPage.setAvailableItems(availableItems);
						knockOffPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (knockOffPage.getCurrentPageIndex() > 1)?knockOffPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * knockOffPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(TrainOrderRow.class);
						criteria.add(reportConjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(knockOffPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					knockOffPage.setTrainRows(list);
				}
				else
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setTrainRows(new ArrayList<TrainOrderRow>());

				}
			}
			else
			{
				knockOffPage.setAvailableItems(0);
				knockOffPage.setAvailablePages(0);
				knockOffPage.setTrainRows(new ArrayList<TrainOrderRow>());
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

		return knockOffPage;
	}

	@Override
	public KnockOffPage visaKnockOffBookings(KnockOffPage knockOffPage) {
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(VisaOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			if(knockOffPage!=null && knockOffPage.isFilterEnabled())
			{
				KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(knockOffFilter.getLoginCompany(),-1,"");
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setVisaRows(new ArrayList<VisaOrderRow>());
					return knockOffPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				/*	reportConjunction.add(Restrictions.eq("knockOff", true));*/
				disjunction.add(Restrictions.eq("statusAction","Confirmed")).add(Restrictions.eq("statusAction","Cancelled"));
				/*if(knockOffFilter.getKnockOff() != null && !knockOffFilter.getKnockOff().equals("ALL"))
					reportConjunction.add(Restrictions.like("knockOff", Boolean.valueOf(knockOffFilter.getKnockOff())));*/

				criteria.add(reportConjunction).add(disjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<VisaOrderRow> list =null;
			if(rowCount>0)
			{
				if(knockOffPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(VisaOrderRow.class);
					criteria.add(reportConjunction).add(disjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					knockOffPage.setAvailableItems(list.size());
					knockOffPage.setAvailablePages(1);
				}
				else{
					if(knockOffPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % knockOffPage.getMaxItems() == 0)?(availableItems / knockOffPage.getMaxItems()):((availableItems / knockOffPage.getMaxItems()) + 1);
						knockOffPage.setAvailableItems(availableItems);
						knockOffPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (knockOffPage.getCurrentPageIndex() > 1)?knockOffPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * knockOffPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(VisaOrderRow.class);
						criteria.add(reportConjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(knockOffPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					knockOffPage.setVisaRows(list);
				}
				else
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setVisaRows(new ArrayList<VisaOrderRow>());
				}

			}
			else
			{
				knockOffPage.setAvailableItems(0);
				knockOffPage.setAvailablePages(0);
				knockOffPage.setVisaRows(new ArrayList<VisaOrderRow>());
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

		return knockOffPage;
	}

	@Override
	public KnockOffPage insuranceKnockOffBookings(KnockOffPage knockOffPage) {
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(InsuranceOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			if(knockOffPage!=null && knockOffPage.isFilterEnabled())
			{
				KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(knockOffFilter.getLoginCompany(),-1,"");
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setInsuranceRows(new ArrayList<InsuranceOrderRow>());
					return knockOffPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				/*reportConjunction.add(Restrictions.eq("knockOff", true));*/
				disjunction.add(Restrictions.eq("statusAction","Confirmed")).add(Restrictions.eq("statusAction","Cancelled"));
				/*if(knockOffFilter.getKnockOff() != null && !knockOffFilter.getKnockOff().equals("ALL"))
					reportConjunction.add(Restrictions.like("knockOff", Boolean.valueOf(knockOffFilter.getKnockOff())));*/

				criteria.add(reportConjunction).add(disjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<InsuranceOrderRow> list =null;
			if(rowCount>0)
			{
				if(knockOffPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(InsuranceOrderRow.class);
					criteria.add(reportConjunction).add(disjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					knockOffPage.setAvailableItems(list.size());
					knockOffPage.setAvailablePages(1);
				}
				else{
					if(knockOffPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % knockOffPage.getMaxItems() == 0)?(availableItems / knockOffPage.getMaxItems()):((availableItems / knockOffPage.getMaxItems()) + 1);
						knockOffPage.setAvailableItems(availableItems);
						knockOffPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (knockOffPage.getCurrentPageIndex() > 1)?knockOffPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * knockOffPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(InsuranceOrderRow.class);
						criteria.add(reportConjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(knockOffPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					knockOffPage.setInsuranceRows(list);
				}
				else
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setInsuranceRows(new ArrayList<InsuranceOrderRow>());
				}

			}
			else
			{
				knockOffPage.setAvailableItems(0);
				knockOffPage.setAvailablePages(0);
				knockOffPage.setInsuranceRows(new ArrayList<InsuranceOrderRow>());
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

		return knockOffPage;
	}

	@Override
	public KnockOffPage miscellaneousKnockOffBookings(KnockOffPage knockOffPage) {
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(MiscellaneousOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			Disjunction disjunction = Restrictions.disjunction();
			if(knockOffPage!=null && knockOffPage.isFilterEnabled())
			{
				KnockOffFilter knockOffFilter = knockOffPage.getKnockOffFilter();
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = flightOrderDao.getCompanyIdList(knockOffFilter.getLoginCompany(),-1,"");
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setMiscellaneousRows(new ArrayList<MiscellaneousOrderRow>());
					return knockOffPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				/*reportConjunction.add(Restrictions.eq("knockOff", true));*/
				disjunction.add(Restrictions.eq("statusAction","Confirmed")).add(Restrictions.eq("statusAction","Cancelled"));
				/*if(knockOffFilter.getKnockOff() != null && !knockOffFilter.getKnockOff().equals("ALL"))
					reportConjunction.add(Restrictions.like("knockOff", Boolean.valueOf(knockOffFilter.getKnockOff())));*/

				criteria.add(reportConjunction).add(disjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			List<MiscellaneousOrderRow> list =null;
			if(rowCount>0)
			{
				if(knockOffPage.getMaxItems()==Page.ALL_ITEMS){
					criteria = session.createCriteria(MiscellaneousOrderRow.class);
					criteria.add(reportConjunction).add(disjunction);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					knockOffPage.setAvailableItems(list.size());
					knockOffPage.setAvailablePages(1);
				}
				else{
					if(knockOffPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % knockOffPage.getMaxItems() == 0)?(availableItems / knockOffPage.getMaxItems()):((availableItems / knockOffPage.getMaxItems()) + 1);
						knockOffPage.setAvailableItems(availableItems);
						knockOffPage.setAvailablePages(availablePages);
					} 
					int pageIndexDb = (knockOffPage.getCurrentPageIndex() > 1)?knockOffPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * knockOffPage.getMaxItems();
					if(itemIndex <= rowCount)
					{
						criteria = session.createCriteria(MiscellaneousOrderRow.class);
						criteria.add(reportConjunction).add(disjunction);
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(knockOffPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
					}
				}
				if(list!=null && list.size()>0)
				{
					knockOffPage.setMiscellaneousRows(list);
				}
				else
				{
					knockOffPage.setAvailableItems(0);					
					knockOffPage.setMiscellaneousRows(new ArrayList<MiscellaneousOrderRow>());
				}

			}
			else
			{
				knockOffPage.setAvailableItems(0);
				knockOffPage.setAvailablePages(0);
				knockOffPage.setMiscellaneousRows(new ArrayList<MiscellaneousOrderRow>());
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

		return knockOffPage;
	}



	@Override
	public boolean flightKnockOffUpdate(KnockOffUtility knockOffUtility) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			FlightOrderRow flightOrderRow=(FlightOrderRow) session.get(FlightOrderRow.class, knockOffUtility.getRowId());
			flightOrderRow.setKnockOff(true);
			session.update(flightOrderRow);
			tx.commit();
			isUpdated=true;

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			isUpdated=false;
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}
	@Override
	public boolean hotelKnockOffUpdate(KnockOffUtility knockOffUtility) {
		Session session=null;
		Transaction tx=null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			HotelOrderRow hotelOrderRow=(HotelOrderRow) session.get(HotelOrderRow.class, knockOffUtility.getRowId());
			hotelOrderRow.setKnockOff(true);
			session.update(hotelOrderRow);
			tx.commit();
			isUpdated=true;

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			isUpdated=false;
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}
	@Override
	public boolean carKnockOffUpdate(KnockOffUtility knockOffUtility) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			CarOrderRow carOrderRow=(CarOrderRow) session.get(CarOrderRow.class, knockOffUtility.getRowId());
			carOrderRow.setKnockOff(true);
			session.update(carOrderRow);
			tx.commit();
			isUpdated=true;

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			isUpdated=false;
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}
	@Override
	public boolean busKnockOffUpdate(KnockOffUtility knockOffUtility) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			BusOrderRow busOrderRow=(BusOrderRow) session.get(BusOrderRow.class, knockOffUtility.getRowId());
			busOrderRow.setKnockOff(true);
			session.update(busOrderRow);
			tx.commit();
			isUpdated=true;

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			isUpdated=false;
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}
	@Override
	public boolean trainKnockOffUpdate(KnockOffUtility knockOffUtility) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			TrainOrderRow trainOrderRow=(TrainOrderRow) session.get(TrainOrderRow.class, knockOffUtility.getRowId());
			trainOrderRow.setKnockOff(true);
			session.update(trainOrderRow);
			tx.commit();
			isUpdated=true;

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			isUpdated=false;
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}
	@Override
	public boolean visaKnockOffUpdate(KnockOffUtility knockOffUtility) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			VisaOrderRow visaOrderRow=(VisaOrderRow) session.get(VisaOrderRow.class, knockOffUtility.getRowId());
			visaOrderRow.setKnockOff(true);
			session.update(visaOrderRow);
			tx.commit();
			isUpdated=true;

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			isUpdated=false;
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}
	@Override
	public boolean insuranceKnockOffUpdate(KnockOffUtility knockOffUtility) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			InsuranceOrderRow insuranceOrderRow=(InsuranceOrderRow) session.get(InsuranceOrderRow.class, knockOffUtility.getRowId());
			insuranceOrderRow.setKnockOff(true);
			session.update(insuranceOrderRow);
			tx.commit();
			isUpdated=true;

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			isUpdated=false;
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}
	@Override
	public boolean miscellaneousKnockOffUpdate(KnockOffUtility knockOffUtility) {
		Session session=null;
		Transaction tx=null;
		boolean isUpdated=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			MiscellaneousOrderRow miscellaneousOrderRow=(MiscellaneousOrderRow) session.get(MiscellaneousOrderRow.class, knockOffUtility.getRowId());
			miscellaneousOrderRow.setKnockOff(true);
			session.update(miscellaneousOrderRow);
			tx.commit();
			isUpdated=true;

		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();
			isUpdated=false;
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isUpdated;
	}

	public KnockOffPage flightKnockOffBookings(KnockOffPage knockOffPage,List<KnockOffVO> allKnockOffVOReports) {
		List<FlightOrderRow> flightOrderRowList=knockOffPage.getFlightRows();
		if(flightOrderRowList!=null && flightOrderRowList.size()>0){
			for(FlightOrderRow flightOrderRow:flightOrderRowList){
				KnockOffVO knockOffVO=new KnockOffVO();
				BigDecimal invoiceAmount=new BigDecimal(0);
				BigDecimal serviceOrGstTax=new BigDecimal(0);
				BigDecimal managementFee=new BigDecimal(0);
				BigDecimal convenienceFee=new BigDecimal(0);
				knockOffVO.setAmount(new BigDecimal(0));
				knockOffVO.setBillDate(DateConversion.convertTimestampToString(flightOrderRow.getCreatedAt()));
				knockOffVO.setBillNo(flightOrderRow.getInvoiceNo());
				knockOffVO.setBookingDate(flightOrderRow.getBookingDate());
				knockOffVO.setBookingRef(flightOrderRow.getOrderId());
				boolean isIsInternational=CommonDsrDao.isDomesticOrInternational(flightOrderRow.getDestination(),flightOrderRow.getOrigin());
				if(isIsInternational)
					knockOffVO.setBookingType("International Flight");
				else
					knockOffVO.setBookingType("Domestic Flight");

				knockOffVO.setBRVorCRV("-");
				knockOffVO.setGDSorLCC("Air");
				if(flightOrderRow.getFlightOrderRowServiceTax()!=null){ 
					serviceOrGstTax=flightOrderRow.getServiceTax()!=null?flightOrderRow.getServiceTax():new BigDecimal(0);
					managementFee=flightOrderRow.getFlightOrderRowServiceTax().getManagementFee();
					convenienceFee=flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee();
				}
				else if(flightOrderRow.getFlightOrderRowGstTax()!=null){ 
					serviceOrGstTax=flightOrderRow.getGstOnFlights()!=null?flightOrderRow.getGstOnFlights():new BigDecimal(0);
					managementFee=flightOrderRow.getFlightOrderRowGstTax().getManagementFee();
					convenienceFee=flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee();
				}

				invoiceAmount=flightOrderRow.getFinalPrice().add(serviceOrGstTax).add(managementFee).add(convenienceFee);
				knockOffVO.setBillAmount(invoiceAmount);
				knockOffVO.setBalanceAmount(invoiceAmount);
				knockOffVO.setKnockedOffAmount(new BigDecimal("0.00"));
				allKnockOffVOReports.add(knockOffVO);
			}
		}
		knockOffPage.setAllKnockOffVOReports(allKnockOffVOReports);
		return knockOffPage;

	}
	public KnockOffPage hotelKnockOffBookings(KnockOffPage knockOffPage,List<KnockOffVO> allKnockOffVOReports) {
		List<HotelOrderRow> hotelOrderRowList=knockOffPage.getHotelRows();
		if(hotelOrderRowList!=null && hotelOrderRowList.size()>0){
			for(HotelOrderRow hotelOrderRow:hotelOrderRowList){
				KnockOffVO knockOffVO=new KnockOffVO();
				BigDecimal invoiceAmount=new BigDecimal(0);
				BigDecimal serviceOrGstTax=new BigDecimal(0);
				BigDecimal managementFee=new BigDecimal(0);
				BigDecimal convenienceFee=new BigDecimal(0);
				knockOffVO.setAmount(new BigDecimal(0));
				knockOffVO.setBillDate(DateConversion.convertTimestampToString(hotelOrderRow.getCreatedAt()));
				knockOffVO.setBillNo(hotelOrderRow.getInvoiceNo());
				knockOffVO.setBookingDate(hotelOrderRow.getBookingDate());
				knockOffVO.setBookingRef(hotelOrderRow.getOrderReference());
				String country=hotelOrderRow.getHotelOrderHotelData().getCountry()!=null?hotelOrderRow.getHotelOrderHotelData().getCountry():"India";
				String itineraryType=country.equalsIgnoreCase("India") ?"Domestic Hotel":"International Hotel";
				knockOffVO.setBookingType(itineraryType);
				knockOffVO.setBRVorCRV("-");
				knockOffVO.setGDSorLCC("Hotel");
				if(hotelOrderRow.getHotelOrderRowServiceTax()!=null){ 
					serviceOrGstTax=hotelOrderRow.getServiceTax()!=null?hotelOrderRow.getServiceTax():new BigDecimal(0);
					managementFee=hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee();
					convenienceFee=hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee();
				}
				else if(hotelOrderRow.getHotelOrderRowGstTax()!=null){ 
					serviceOrGstTax=hotelOrderRow.getTotGst()!=null?hotelOrderRow.getTotGst():new BigDecimal(0);
					managementFee=hotelOrderRow.getHotelOrderRowGstTax().getManagementFee();
					convenienceFee=hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee();
				}

				invoiceAmount=hotelOrderRow.getFinalPrice().add(serviceOrGstTax).add(managementFee).add(convenienceFee);
				knockOffVO.setBillAmount(invoiceAmount);
				knockOffVO.setBalanceAmount(invoiceAmount);
				knockOffVO.setKnockedOffAmount(new BigDecimal("0.00"));
				allKnockOffVOReports.add(knockOffVO);
			}
		}
		knockOffPage.setAllKnockOffVOReports(allKnockOffVOReports);
		return knockOffPage;

	}
	
	
	 
}
