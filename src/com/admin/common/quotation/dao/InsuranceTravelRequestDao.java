package com.admin.common.quotation.dao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.admin.common.quotation.model.InsuranceTravelRequest;
import com.admin.common.quotation.model.InsuranceTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.PaymentOptions;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.insurance.model.InsuranceOrderCustomerDetail;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;
public class InsuranceTravelRequestDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InsuranceTravelRequestDao.class);


	public InsuranceTravelRequest insertinsuranceTravelRequestnew(InsuranceTravelRequest insuranceTravelRequest){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(insuranceTravelRequest);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("Exception---------"+e.getMessage());

		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return insuranceTravelRequest;

	}


	public List<InsuranceTravelRequest>  loadInsuranceTravelRequestList(User user){
		// TODO Auto-generated method stub
		Session session= null;
		List<InsuranceTravelRequest>  newList= new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(InsuranceTravelRequest.class);
			criteria.add(Restrictions.eq("companyId", user.getCompanyid()));
			newList= criteria.list();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return newList;

	}

	public InsuranceTravelRequest getInsuranceQuotationRequestDetails(Long id){
		InsuranceTravelRequest insuranceTravelRequest=null;

		Session session= null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(InsuranceTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			insuranceTravelRequest= (InsuranceTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return insuranceTravelRequest;

	}

	public InsuranceTravelRequestQuotation getInsuranceTravelRequestQuotationDetails(Long id){
		InsuranceTravelRequestQuotation newQuotationObj=new InsuranceTravelRequestQuotation();
		Session session= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(InsuranceTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("id", id));
			newQuotationObj= (InsuranceTravelRequestQuotation) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return newQuotationObj;
	}



	public boolean insertInsuranceQuotationList(List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotations,InsuranceTravelRequest insuranceTravelRequest){
		// TODO Auto-generated method stub
		boolean isInserted=false;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(insuranceTravelRequestQuotations!=null && insuranceTravelRequestQuotations.size()>0){
				for(InsuranceTravelRequestQuotation insuranceTravelRequestQuotation:insuranceTravelRequestQuotations){
					insuranceTravelRequestQuotation.setCreatedAt(new Timestamp(new Date().getTime()));
					insuranceTravelRequestQuotation.setInsuranceTravelRequest(insuranceTravelRequest);
					session.save(insuranceTravelRequestQuotation);
				}
			}

			transaction.commit();
			isInserted=true;
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
				isInserted=false;
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return isInserted;

	}


	public List<InsuranceTravelRequestQuotation> getInsuranceRequestTravelQuotationList(Long insuranceQuotationRequestId) {
		// TODO Auto-generated method stub
		List<InsuranceTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceTravelRequestQuotation itr where itr.insuranceTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", insuranceQuotationRequestId);
			Newlist= query.list();
			/*for(CarTravelRequestQuotation quotation:list){
				quotation.setSellRate(quotation.getSellRate().setScale(2, BigDecimal.ROUND_UP));
				Newlist.add(quotation);

			}*/
		}
		catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return Newlist;
	}


	public List<InsuranceTravelRequestQuotation> getInsuranceRequestTravelQuotationBookedList(Long insuranceQuotationRequestId) {
		// TODO Auto-generated method stub
		List<InsuranceTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(InsuranceTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("isBooked", true));
			criteria.createCriteria("insuranceTravelRequest").add(Restrictions.eq("id", insuranceQuotationRequestId));
			Newlist= criteria.list();

		}
		catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return Newlist;
	}






	public long  getInsuranceOrderRowIdFormInsuranceTravelRequestQuotation(Long insuranceQuotationRequestId) {
		// TODO Auto-generated method stub
		InsuranceTravelRequestQuotation  insuranceTravelRequestQuotation=  null;
		long id = 0;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceTravelRequestQuotation itq where itq.insuranceTravelRequest.id=:id and isBooked=:isBooked";
			Query query = session.createQuery(sql);
			query.setParameter("id", insuranceQuotationRequestId);
			query.setParameter("isBooked", true);
			insuranceTravelRequestQuotation= (InsuranceTravelRequestQuotation) query.uniqueResult();
			if(insuranceTravelRequestQuotation!=null){
				id=insuranceTravelRequestQuotation.getOrderRowId();	
			}


		}
		catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return id;
	}


	public InsuranceTravelRequestQuotation insuranceRequestQuotationUpdate(InsuranceTravelRequestQuotation insuranceTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		InsuranceTravelRequestQuotation  carTravelRequestQuotationnew=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carTravelRequestQuotationnew=(InsuranceTravelRequestQuotation) session.get(InsuranceTravelRequestQuotation.class,insuranceTravelRequestQuotation.getId());
			carTravelRequestQuotationnew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(carTravelRequestQuotationnew);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return carTravelRequestQuotationnew;
	}

	public InsuranceTravelRequestQuotation updateInsuranceTravelRequestQuotationWithOrderId(InsuranceOrderRow insuranceOrderRow, Long insuranceQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		InsuranceTravelRequestQuotation  insuranceTravelRequestQuotation=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			insuranceTravelRequestQuotation=(InsuranceTravelRequestQuotation) session.get(InsuranceTravelRequestQuotation.class,insuranceQuotationRequestId);
			insuranceTravelRequestQuotation.setBooked(true);
			insuranceTravelRequestQuotation.setOrderRowId(insuranceOrderRow.getId());
			insuranceTravelRequestQuotation.setHide(true);
			insuranceTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.BOOKED.getValue());
			session.saveOrUpdate(insuranceTravelRequestQuotation);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return insuranceTravelRequestQuotation;

	}


	public InsuranceTravelRequest getInsuranceTravelRequestDetails(Long id) {
		Session session= null;
		InsuranceTravelRequest  insuranceTravelRequest=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(InsuranceTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			insuranceTravelRequest=(InsuranceTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return insuranceTravelRequest;

	}


	public InsuranceTravelRequest updateInsuranceTravelRequestDetails(InsuranceTravelRequest insuranceTravelRequest) {
		Session session= null;
		Transaction transaction=null;
		InsuranceTravelRequest insuranceTravelRequestNew=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			insuranceTravelRequestNew=(InsuranceTravelRequest) session.get(InsuranceTravelRequest.class,insuranceTravelRequest.getId());
			insuranceTravelRequestNew.setTitle(insuranceTravelRequest.getTitle());
			insuranceTravelRequestNew.setFirstName(insuranceTravelRequest.getFirstName());
			insuranceTravelRequestNew.setLastName(insuranceTravelRequest.getLastName());
			insuranceTravelRequestNew.setNoOfTravelers(insuranceTravelRequest.getNoOfTravelers());
			insuranceTravelRequestNew.setTRNo(insuranceTravelRequest.getTRNo());
			insuranceTravelRequestNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(insuranceTravelRequestNew);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return insuranceTravelRequestNew;


	}


	public List<InsuranceTravelRequestQuotation> getInsuranceTravelRequestQuotationId(Long insuranceQuotationRequestId) {
		Session session= null;
		List<InsuranceTravelRequestQuotation> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceTravelRequestQuotation  where InsuranceTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", insuranceQuotationRequestId);
			list=query.list();

		}
		catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return list;

	}

	public   boolean updateInsuranceTravelRequestQuotationHide(Long insuranceQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		boolean isHide=false;
		List<InsuranceTravelRequestQuotation> list=getInsuranceTravelRequestQuotationId(insuranceQuotationRequestId);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(list!=null){
				for(InsuranceTravelRequestQuotation insuranceTravelRequestQuotation:list){
					InsuranceTravelRequestQuotation  insuranceTravelRequestQuotationNew=(InsuranceTravelRequestQuotation) session.get(InsuranceTravelRequestQuotation.class,insuranceTravelRequestQuotation.getId());
					insuranceTravelRequestQuotationNew.setHide(true);
					session.saveOrUpdate(insuranceTravelRequestQuotationNew);
					session.flush();
				}
				transaction.commit();
				isHide=true;
			}

		}
		catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return isHide;

	}

	public InsuranceTravelRequestQuotation  updateInsuranceQuotationList(List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotations) {
		boolean isUpdated=false;
		Session session= null;
		Transaction transaction=null;
		InsuranceTravelRequestQuotation insuranceTravelRequestQuotationOuter=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(insuranceTravelRequestQuotations!=null && insuranceTravelRequestQuotations.size()>0){
				for(InsuranceTravelRequestQuotation insuranceTravelRequestQuotationData:insuranceTravelRequestQuotations){
					InsuranceTravelRequestQuotation insuranceTravelRequestQuotation =(InsuranceTravelRequestQuotation)session.get(InsuranceTravelRequestQuotation.class, insuranceTravelRequestQuotationData.getId()); 
					// write code for Insurrance
					session.update(insuranceTravelRequestQuotation);
					insuranceTravelRequestQuotationOuter=insuranceTravelRequestQuotation;
				}
			}
			transaction.commit();
			isUpdated=true;
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
				isUpdated=false;
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return insuranceTravelRequestQuotationOuter;
	}

	 
	public void updatePaymentOptions(PaymentOptions paymentOptions) {
		Session  session=null;
		Transaction transaction=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();	
			if(paymentOptions!=null){
				session.update(paymentOptions);
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

	}



	public void updateHotelOrFlightQuotationSendStatus(HotelFlightBookingStatus hotelFlightBookingStatus) {
		Session  session=null;
		Transaction transaction=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(hotelFlightBookingStatus.getId()!=null)
			{
				HotelFlightBookingStatus hotelFlightBookingStatusNew=(HotelFlightBookingStatus)session.get(HotelFlightBookingStatus.class, hotelFlightBookingStatus.getId());
				hotelFlightBookingStatusNew.setqSentToMail(hotelFlightBookingStatus.getqSentToMail());
				session.saveOrUpdate(hotelFlightBookingStatusNew);
			}
			else
			{
				session.save(hotelFlightBookingStatus);
			}
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

	}

	public void updateHotelOrFlightQuotationBookingStatus(HotelFlightBookingStatus hotelFlightBookingStatus) {
		Session  session=null;
		Transaction transaction=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(hotelFlightBookingStatus.getId()!=null)
			{
				HotelFlightBookingStatus hotelFlightBookingStatusNew=(HotelFlightBookingStatus)session.get(HotelFlightBookingStatus.class, hotelFlightBookingStatus.getId());
				hotelFlightBookingStatusNew.setBooked(hotelFlightBookingStatus.getBooked());
				session.saveOrUpdate(hotelFlightBookingStatusNew);
			}
			else
			{
				session.save(hotelFlightBookingStatus);
			}
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

	}

	public InsuranceTravelRequestQuotation getInsuranceQuotationDetails(Long insuranceOrderRowId) {
		Session  session=null;
		InsuranceTravelRequestQuotation insuranceTravelRequestQuotation=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(InsuranceTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("orderRowId",insuranceOrderRowId));
			insuranceTravelRequestQuotation=(InsuranceTravelRequestQuotation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return insuranceTravelRequestQuotation;

	}

	
	public InsuranceOrderRow insertInsuranceOrderRow(InsuranceOrderRow insuranceOrderRow) {
		Session  session=null;
		Transaction  tx=null;
		 
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(insuranceOrderRow);
			tx.commit();
		}
		catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
			
			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return insuranceOrderRow;

	}
	/*public InsuranceOrderRow getInsuranceOrderRowDetailsById(Long id) {
		// TODO Auto-generated method stub
				 Session session= null;
				 InsuranceOrderRow  insuranceOrderRow=null;
				  try {
				   session = HibernateUtil.getSessionFactory().openSession();
				   Criteria criteria=session.createCriteria(InsuranceOrderRow.class);
				   criteria.add(Restrictions.eq("id", id));
				   insuranceOrderRow=(InsuranceOrderRow) criteria.uniqueResult();
				  } catch (Exception e) {
				   logger.error("Exception---------"+e.getMessage());
				  }
				  finally{
				   if(session!=null && session.isOpen()){
				    session.close();
				   }
				  }
				  return insuranceOrderRow;
				 }
	
	*/
	public InsuranceOrderRow getInsuranceOrderRowDetailsById(Long id) {
		  Session session= null;
		  InsuranceOrderRow  insuranceOrderRow=null;
		  try {
		   session = HibernateUtil.getSessionFactory().openSession();
		   Criteria criteria=session.createCriteria(InsuranceOrderRow.class);
		   criteria.add(Restrictions.eq("id", id));
		   insuranceOrderRow=(InsuranceOrderRow) criteria.uniqueResult();
		  } catch (Exception e) {
		   logger.error("Exception---------"+e.getMessage());
		  }
		  finally{
		   if(session!=null && session.isOpen()){
		    session.close();
		   }
		  }
		  return insuranceOrderRow;
		 }
	public InsuranceTravelRequestQuotation updateInsuranceTravelRequestQuotation(InsuranceTravelRequestQuotation insuranceTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		InsuranceTravelRequestQuotation  insuranceTravelRequestQuotationFromDb=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			insuranceTravelRequestQuotationFromDb=(InsuranceTravelRequestQuotation) session.get(InsuranceTravelRequestQuotation.class,insuranceTravelRequestQuotation.getId());
			insuranceTravelRequestQuotationFromDb.setUpdatedAt(new Timestamp(new Date().getTime()));
			insuranceTravelRequestQuotationFromDb.setPassportNumber(insuranceTravelRequestQuotation.getPassportNumber());
			insuranceTravelRequestQuotationFromDb.setOnwardTravelDate(insuranceTravelRequestQuotation.getOnwardTravelDateTemp());
			insuranceTravelRequestQuotationFromDb.setReturnTravelDate(insuranceTravelRequestQuotation.getReturnTravelDatetemp());
			insuranceTravelRequestQuotationFromDb.setRemarks(insuranceTravelRequestQuotation.getRemarks());
			insuranceTravelRequestQuotationFromDb.setCurrency(insuranceTravelRequestQuotation.getCurrency());
			session.update(insuranceTravelRequestQuotationFromDb);
			transaction.commit();

		} catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return insuranceTravelRequestQuotation;
	}
	
	public InsuranceOrderRow updateInsuranceOrderRow(InsuranceOrderRow  insuranceOrderRow) {
		Session session = null;
		Transaction transaction = null;
		InsuranceOrderRow insuranceOrderRowFromDb = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			logger.info("insuranceOrderRow.getId()--"+insuranceOrderRow.getId()+"type is"+insuranceOrderRow.getId().getClass());
			insuranceOrderRowFromDb=getInsuranceOrderRowDetailsById(insuranceOrderRow.getId());
			insuranceOrderRowFromDb.setConfirmationNumber(insuranceOrderRow.getConfirmationNumber());
			insuranceOrderRowFromDb.setTravelDate(DateConversion.StringToDate(insuranceOrderRow.getTravelDateTemp()));
			insuranceOrderRowFromDb.setRemarks(insuranceOrderRow.getRemarks());
			insuranceOrderRowFromDb.setCountryOfTravel(insuranceOrderRow.getCountryOfTravel());
			insuranceOrderRowFromDb.setManagementFee(insuranceOrderRow.getManagementFee());
			insuranceOrderRowFromDb.setConvenienceFee(insuranceOrderRow.getConvenienceFee());
			insuranceOrderRowFromDb.setMarkUpamount(insuranceOrderRow.getMarkUpamount());
			insuranceOrderRowFromDb.setServiceTax(insuranceOrderRow.getServiceTax());
			insuranceOrderRowFromDb.setBaseToBookingExchangeRate(insuranceOrderRow.getBaseToBookingExchangeRate());
			insuranceOrderRowFromDb.setApiToBaseExchangeRate(insuranceOrderRow.getApiToBaseExchangeRate());
			insuranceOrderRowFromDb.setOtherTaxes(insuranceOrderRow.getOtherTaxes());
			insuranceOrderRowFromDb.setSupplierPrice(insuranceOrderRow.getSupplierPrice());
			insuranceOrderRowFromDb.setBasePrice(insuranceOrderRow.getBasePrice());
			insuranceOrderRowFromDb.setTotalAmount(insuranceOrderRow.getTotalAmount()); 
			insuranceOrderRowFromDb.setSupplierName(insuranceOrderRow.getSupplierName());
			insuranceOrderRowFromDb.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(insuranceOrderRowFromDb);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Exception---------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return insuranceOrderRow;

	}
	
	public List<WalletAmountTranferHistory> getInsuranceOrderPaymentInfo(String orderId,int userId) {
		//String sql="select * from payment_transaction_APG where api_transaction_id='"+orderRef+"'";

		List<WalletAmountTranferHistory> payTxList=null;
		Session session= null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(WalletAmountTranferHistory.class);
			cr.add(Restrictions.eq("actionId", orderId));
			cr.add(Restrictions.eq("userId", userId));
			payTxList= cr.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return payTxList;
	}
	public void insertInsuranceOrderCustomer(InsuranceOrderCustomerDetail insuranceOrderCustomer) {
		Session  session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(insuranceOrderCustomer);
			tx.commit();
		}
		catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
	
	}
	
	}
