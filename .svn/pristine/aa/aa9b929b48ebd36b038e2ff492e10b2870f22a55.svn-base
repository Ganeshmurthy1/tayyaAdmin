package com.admin.payment.recievable.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.payment.recievable.PaymentRecievable;
import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.dao.CompanyFilterDao;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class PaymentRecievableDaoImpl implements PaymentRecievableDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PaymentRecievableDaoImpl.class);
	@Override
	public PaymentRecievable save(PaymentRecievable paymentRecievable) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(paymentRecievable);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return paymentRecievable;
	}

	@Override
	public List<PaymentRecievable> list() {
		// TODO Auto-generated method stub
		List<PaymentRecievable>  list=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentRecievable.class);
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

	@Override
	public Boolean update(PaymentRecievable paymentRecievable) {
		// TODO Auto-generated method stub
		Boolean isUpdated=false;
		Session session = null;
		Transaction tx = null;
		Company company=null;
		User user= null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			PaymentRecievable paymentRecievableNew=(PaymentRecievable) session.get(PaymentRecievable.class, paymentRecievable.getId());
			if(paymentRecievableNew.getCompanyId()!=paymentRecievable.getCompanyId()){
				company=new CompanyDAO().getCompanyProfile(paymentRecievable.getCompanyId());
				if(company!=null)
					user=new UserDAO().getUserPasswordForLock(company.getEmail());
				paymentRecievableNew.setUserId(user.getId());
			}
			paymentRecievableNew.setAmount(paymentRecievable.getAmount());
			paymentRecievableNew.setCompanyId(paymentRecievable.getCompanyId());
			paymentRecievableNew.setDescription(paymentRecievable.getDescription());
			paymentRecievableNew.setPaymentMode(paymentRecievable.getPaymentMode());
			paymentRecievableNew.setReceivedBy(paymentRecievable.getReceivedBy());
			paymentRecievableNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			paymentRecievableNew.setReceivedDate(DateConversion.StringToDate(paymentRecievable.getTransReceivedDate()));
			if(paymentRecievable.getPaymentMode().equals("Cash"))
				paymentRecievableNew.setReferenceNumber(null);
			else
				paymentRecievableNew.setReferenceNumber(paymentRecievable.getReferenceNumber());
			paymentRecievableNew.setFromDate(DateConversion.StringToDate(paymentRecievable.getTransFromDt()));
			paymentRecievableNew.setToDate(DateConversion.StringToDate(paymentRecievable.getTransToDt()));
			session.update(paymentRecievableNew);
			tx.commit();
			isUpdated=true;
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			isUpdated=false;
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();

		}
		return isUpdated;
	}

	@Override
	public PaymentRecievable getDetails(Long id) {
		PaymentRecievable paymentRecievable=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentRecievable.class);
			criteria.add(Restrictions.eq("id", id));
			paymentRecievable=(PaymentRecievable) criteria.uniqueResult();
			paymentRecievable.setTransFromDt(DateConversion.convertDateToStringDateddMMyyyy(paymentRecievable.getFromDate()));
			paymentRecievable.setTransToDt(DateConversion.convertDateToStringDateddMMyyyy(paymentRecievable.getToDate()));
		 
		
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return paymentRecievable;
	}

	@Override
	public CompanyFilterPage paymentReceivableList(CompanyFilterPage companyFilterPage) {
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(PaymentRecievable.class);
			Conjunction conjunction = Restrictions.conjunction();
			CompanyFilter companyFilter =null;
			CompanyFilterPage companyFilterPageNew=new CompanyFilterPage();
			if(companyFilterPage!=null && companyFilterPage.isFilterEnabled())
			{
				companyFilter = companyFilterPage.getCompanyFilter();
				Company company=null;
				if(companyFilter.getCompanyId()>0) 
					company=new CompanyDAO().getCompanyProfile(companyFilter.getCompanyId());
				else 
					company=companyFilter.getLoginCompany();

				companyFilter.setLoginCompany(company);
				companyFilterPageNew.setCompanyFilter(companyFilter);
				List<Integer> companyIds =new CompanyFilterDao().getCompanyIdList(companyFilterPageNew);
				conjunction.add(Restrictions.in("companyId", companyIds));
				conjunction.add(Restrictions.eq("knockOff", false));
				if(companyFilter.getOrderId() != null )
					conjunction.add(Restrictions.like("transactionId", companyFilter.getOrderId(), MatchMode.ANYWHERE));
				if(companyFilter.getUserName() != null )
					conjunction.add(Restrictions.like("receivedBy", companyFilter.getUserName(), MatchMode.ANYWHERE));

				if(companyFilter.getDateFilterCreated() != null && companyFilter.getDateFilterCreated().getDtEnd() != null && companyFilter.getDateFilterCreated().getDtStart() != null )
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtStart());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						//date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						conjunction.add(Restrictions.ge("receivedDate", date));
						logger.info("##########date min added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(companyFilter.getDateFilterCreated().getDtEnd());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						//date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						conjunction.add(Restrictions.le("receivedDate", date));
						logger.info("##########date max added to conjuction---"+date);

					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				criteria.add(conjunction);
				criteria.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(companyFilterPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % companyFilterPage.getMaxItems() == 0)?(availableItems / companyFilterPage.getMaxItems()):((availableItems / companyFilterPage.getMaxItems()) + 1);
					companyFilterPage.setAvailableItems(availableItems);
					companyFilterPage.setAvailablePages(availablePages);
				} 
				int pageIndexDb = (companyFilterPage.getCurrentPageIndex() > 1)?companyFilterPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * companyFilterPage.getMaxItems();
				logger.info("setFirstResult-------"+itemIndex);
				List<PaymentRecievable> paymentList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(PaymentRecievable.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(companyFilterPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					paymentList = criteria.list();
				}
				if(paymentList!=null && paymentList.size()>0){
					companyFilterPage.setPaymentReceivableList(paymentList);
				}
				else
				{
					companyFilterPage.setAvailableItems(0);
					companyFilterPage.setPaymentReceivableList(new ArrayList<PaymentRecievable>());
				}
			}	
			else
			{
				companyFilterPage.setAvailableItems(0);
				companyFilterPage.setAvailablePages(0);
				companyFilterPage.setPaymentReceivableList(new ArrayList<PaymentRecievable>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}	
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyFilterPage;
	}

	@Override
	public PaymentRecievable updatePaymentFile(PaymentRecievable paymentRecievable) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		PaymentRecievable paymentRecievableNew=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			paymentRecievableNew=(PaymentRecievable) session.get(PaymentRecievable.class, paymentRecievable.getId());
			paymentRecievableNew.setFilePath(paymentRecievable.getFilePath());
			session.update(paymentRecievableNew);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();

		}
		return paymentRecievableNew;
	}

	@Override
	public void updateAirKnockOff(Long orderRowId,boolean isKnockOff) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			FlightOrderRow flightOrderRow=(FlightOrderRow) session.get(FlightOrderRow.class,orderRowId);
			if(flightOrderRow!=null && flightOrderRow.getInvoiceNo()!=null){
				flightOrderRow.setKnockOff(isKnockOff);
				session.update(flightOrderRow);
				tx.commit();
			}
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();

		}
	}

	@Override
	public void updateHotelKnockOff(Long orderRowId,boolean isKnockOff) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			HotelOrderRow hotelOrderRow=(HotelOrderRow) session.get(HotelOrderRow.class,orderRowId);
			if(hotelOrderRow!=null && hotelOrderRow.getInvoiceNo()!=null){
				hotelOrderRow.setKnockOff(isKnockOff);
				session.update(hotelOrderRow);
				tx.commit();
			}
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}

	}

	@Override
	public void updateBusKnockOff(Long orderRowId,boolean isKnockOff) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			BusOrderRow busOrderRow=(BusOrderRow) session.get(BusOrderRow.class,orderRowId);
			if(busOrderRow!=null && busOrderRow.getInvoiceNo()!=null){
				busOrderRow.setKnockOff(isKnockOff);
				session.update(busOrderRow);
				tx.commit();
			}

		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}

	@Override
	public void updateCarKnockOff(Long orderRowId,boolean isKnockOff) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			CarOrderRow carOrderRow=(CarOrderRow) session.get(CarOrderRow.class,orderRowId);
			if(carOrderRow!=null && carOrderRow.getInvoiceNo()!=null){
				carOrderRow.setKnockOff(isKnockOff);
				session.update(carOrderRow);
				tx.commit();
			}
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}

	@Override
	public void updateTrainKnockOff(Long orderRowId,boolean isKnockOff) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			TrainOrderRow trainOrderRow=(TrainOrderRow) session.get(TrainOrderRow.class,orderRowId);
			if(trainOrderRow!=null && trainOrderRow.getInvoiceNo()!=null){
				trainOrderRow.setKnockOff(isKnockOff);
				session.update(trainOrderRow);
				tx.commit();
			}

		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}

	@Override
	public void updateInsuranceKnockOff(Long orderRowId,boolean isKnockOff) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			InsuranceOrderRow insuranceOrderRow=(InsuranceOrderRow) session.get(InsuranceOrderRow.class,orderRowId);
			if(insuranceOrderRow!=null && insuranceOrderRow.getInvoiceNo()!=null){
				insuranceOrderRow.setKnockOff(isKnockOff);
				session.update(insuranceOrderRow);
				tx.commit();
			}
			 
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}

	@Override
	public void updateVisaKnockOff(Long orderRowId,boolean isKnockOff) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			VisaOrderRow visaOrderRow=(VisaOrderRow) session.get(VisaOrderRow.class,orderRowId);
			if(visaOrderRow!=null && visaOrderRow.getInvoiceNo()!=null){
				visaOrderRow.setKnockOff(isKnockOff);
				session.update(visaOrderRow);
				tx.commit();
			}
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}
	@Override
	public void updateMiscellaneousKnockOff(Long orderRowId, boolean isKnockOff) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			MiscellaneousOrderRow miscellaneousOrderRow=(MiscellaneousOrderRow) session.get(MiscellaneousOrderRow.class,orderRowId);
			if(miscellaneousOrderRow!=null && miscellaneousOrderRow.getInvoiceNo()!=null){
				miscellaneousOrderRow.setKnockOff(isKnockOff);
				session.update(miscellaneousOrderRow);
				tx.commit();
			}
		}
		catch(HibernateException e){
			if(tx!=null)
				tx.commit();
			logger.info("---------HibernateException-------------"+e.getMessage());
		}

		finally{
			if(session != null && session.isOpen())
				session.close();
		}
	}
	@Override
	public PaymentRecievable updatePaymentRefNo(PaymentRecievable paymentRecievable) {
		// TODO Auto-generated method stub
				Session session = null;
				Transaction tx = null;
				PaymentRecievable paymentRecievableNew=null;
				try{
					session = HibernateUtil.getSessionFactory().openSession();
					tx=session.beginTransaction();
					paymentRecievableNew=(PaymentRecievable) session.get(PaymentRecievable.class,paymentRecievable.getId());
					if(paymentRecievableNew!=null){
						paymentRecievableNew.setReferenceNumber(paymentRecievable.getReferenceNumber());
						session.update(paymentRecievableNew);
						tx.commit();
					}
				}
				catch(HibernateException e){
					if(tx!=null)
						tx.commit();
					logger.info("---------HibernateException-------------"+e.getMessage());
				}
				finally{
					if(session != null && session.isOpen())
						session.close();
				}
				return paymentRecievableNew;
	}

	

}
