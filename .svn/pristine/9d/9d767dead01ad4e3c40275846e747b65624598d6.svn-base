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

import com.admin.common.quotation.model.CarTravelRequest;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.admin.common.quotation.model.VisaTravelRequest;
import com.admin.common.quotation.model.VisaTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.PaymentOptions;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.tayyarah.train.model.TrainOrderCustomer;
import com.tayyarah.visa.model.VisaOrderCustomer;
public class VisaTravelRequestDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(VisaTravelRequestDao.class);


	public VisaTravelRequest insertvisaTravelRequestnew(VisaTravelRequest visaTravelRequest){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(visaTravelRequest);
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

		return visaTravelRequest;

	}


	public List<VisaTravelRequest>  loadVisaTravelRequestList(User user){
		// TODO Auto-generated method stub
		Session session= null;
		List<VisaTravelRequest>  newList= new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(VisaTravelRequest.class);
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
	

	public VisaTravelRequest getVisaQuotationRequestDetails(Long id){
		VisaTravelRequest visaTravelRequest=null;

		Session session= null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			visaTravelRequest= (VisaTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return visaTravelRequest;

	}

	public VisaTravelRequestQuotation getVisaTravelRequestQuotationDetails(Long id){
		VisaTravelRequestQuotation newQuotationObj=new VisaTravelRequestQuotation();
		Session session= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("id", id));
			newQuotationObj= (VisaTravelRequestQuotation) criteria.uniqueResult();
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



	public boolean insertVisaQuotationList(List<VisaTravelRequestQuotation> visaTravelRequestQuotations,VisaTravelRequest visaTravelRequest){
		// TODO Auto-generated method stub
		boolean isInserted=false;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(visaTravelRequestQuotations!=null && visaTravelRequestQuotations.size()>0){
				for(VisaTravelRequestQuotation visaTravelRequestQuotation:visaTravelRequestQuotations){
					visaTravelRequestQuotation.setCreatedAt(new Timestamp(new Date().getTime()));
					visaTravelRequestQuotation.setVisaTravelRequest(visaTravelRequest);
					session.save(visaTravelRequestQuotation);
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


	public List<VisaTravelRequestQuotation> getVisaRequestTravelQuotationList(Long visaQuotationRequestId) {
		// TODO Auto-generated method stub
		List<VisaTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaTravelRequestQuotation itr where itr.visaTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", visaQuotationRequestId);
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


	public List<VisaTravelRequestQuotation> getVisaRequestTravelQuotationBookedList(Long visaQuotationRequestId) {
		// TODO Auto-generated method stub
		List<VisaTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(VisaTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("isBooked", true));
			criteria.createCriteria("visaTravelRequest").add(Restrictions.eq("id", visaQuotationRequestId));
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






	public long  getVisaOrderRowIdFormVisaTravelRequestQuotation(Long visaQuotationRequestId) {
		// TODO Auto-generated method stub
		VisaTravelRequestQuotation  visaTravelRequestQuotation=  null;
		long id = 0;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaTravelRequestQuotation itq where itq.visaTravelRequest.id=:id and isBooked=:isBooked";
			Query query = session.createQuery(sql);
			query.setParameter("id", visaQuotationRequestId);
			query.setParameter("isBooked", true);
			visaTravelRequestQuotation= (VisaTravelRequestQuotation) query.uniqueResult();
			if(visaTravelRequestQuotation!=null){
				id=visaTravelRequestQuotation.getOrderRowId();	
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


	public VisaTravelRequestQuotation visaRequestQuotationUpdate(VisaTravelRequestQuotation visaTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		VisaTravelRequestQuotation  visaTravelRequestQuotationInner=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			visaTravelRequestQuotationInner=(VisaTravelRequestQuotation) session.get(VisaTravelRequestQuotation.class,visaTravelRequestQuotation.getId());
			visaTravelRequestQuotationInner.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(visaTravelRequestQuotationInner);
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
		return visaTravelRequestQuotationInner;
	}

	public VisaTravelRequestQuotation updateVisaTravelRequestQuotationWithOrderId(VisaOrderRow visaOrderRow, Long visaQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		VisaTravelRequestQuotation  visaTravelRequestQuotation=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			visaTravelRequestQuotation=(VisaTravelRequestQuotation) session.get(VisaTravelRequestQuotation.class,visaQuotationRequestId);
			visaTravelRequestQuotation.setBooked(true);
			visaTravelRequestQuotation.setOrderRowId(visaOrderRow.getId());
			visaTravelRequestQuotation.setHide(true);
			visaTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.BOOKED.getValue());
			session.saveOrUpdate(visaTravelRequestQuotation);
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
		return visaTravelRequestQuotation;

	}


	public VisaTravelRequest getVisaTravelRequestDetails(Long id) {
		Session session= null;
		VisaTravelRequest  visaTravelRequest=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(VisaTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			visaTravelRequest=(VisaTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return visaTravelRequest;

	}


	public VisaTravelRequest updateVisaTravelRequestDetails(VisaTravelRequest visaTravelRequest) {
		Session session= null;
		Transaction transaction=null;
		VisaTravelRequest visaTravelRequestNew=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			visaTravelRequestNew=(VisaTravelRequest) session.get(VisaTravelRequest.class,visaTravelRequest.getId());
			visaTravelRequestNew.setTitle(visaTravelRequest.getTitle());
			visaTravelRequestNew.setFirstName(visaTravelRequest.getFirstName());
			visaTravelRequestNew.setLastName(visaTravelRequest.getLastName());
			//visaTravelRequestNew.setTRNo(visaTravelRequest.getTRNo());
			visaTravelRequestNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(visaTravelRequestNew);
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
		return visaTravelRequestNew;


	}


	public List<VisaTravelRequestQuotation> getVisaTravelRequestQuotationId(Long visaQuotationRequestId) {
		Session session= null;
		List<VisaTravelRequestQuotation> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaTravelRequestQuotation  where VisaTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", visaQuotationRequestId);
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

	public   boolean updateVisaTravelRequestQuotationHide(Long visaQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		boolean isHide=false;
		List<VisaTravelRequestQuotation> list=getVisaTravelRequestQuotationId(visaQuotationRequestId);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(list!=null){
				for(VisaTravelRequestQuotation visaTravelRequestQuotation:list){
					VisaTravelRequestQuotation  visaTravelRequestQuotationNew=(VisaTravelRequestQuotation) session.get(VisaTravelRequestQuotation.class,visaTravelRequestQuotation.getId());
					visaTravelRequestQuotationNew.setHide(true);
					session.saveOrUpdate(visaTravelRequestQuotationNew);
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

	public VisaTravelRequestQuotation  updateVisaQuotationList(List<VisaTravelRequestQuotation> visaTravelRequestQuotations) {
		boolean isUpdated=false;
		Session session= null;
		Transaction transaction=null;
		VisaTravelRequestQuotation visaTravelRequestQuotationOuter=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(visaTravelRequestQuotations!=null && visaTravelRequestQuotations.size()>0){
				for(VisaTravelRequestQuotation visaTravelRequestQuotationData:visaTravelRequestQuotations){
					VisaTravelRequestQuotation visaTravelRequestQuotation =(VisaTravelRequestQuotation)session.get(VisaTravelRequestQuotation.class, visaTravelRequestQuotationData.getId()); 
					// write code for Insurrance
					session.update(visaTravelRequestQuotation);
					visaTravelRequestQuotationOuter=visaTravelRequestQuotation;
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
		return visaTravelRequestQuotationOuter;
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

	public VisaTravelRequestQuotation getVisaQuotationDetails(Long visaOrderRowId) {
		Session  session=null;
		VisaTravelRequestQuotation visaTravelRequestQuotation=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(VisaTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("orderRowId",visaOrderRowId));
			visaTravelRequestQuotation=(VisaTravelRequestQuotation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return visaTravelRequestQuotation;

	}

	
	public VisaOrderRow insertVisaOrderRow(VisaOrderRow visaOrderRow) {
		Session  session=null;
		Transaction  tx=null;
		 
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(visaOrderRow);
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
		return visaOrderRow;

	}


	public VisaOrderRow getVisaOrderRowDetailsById(Long id) {
		// TODO Auto-generated method stub
				 Session session= null;
				 VisaOrderRow  visaOrderRow=null;
				  try {
				   session = HibernateUtil.getSessionFactory().openSession();
				   Criteria criteria=session.createCriteria(VisaOrderRow.class);
				   criteria.add(Restrictions.eq("id", id));
				   visaOrderRow=(VisaOrderRow) criteria.uniqueResult();
				  } catch (Exception e) {
				   logger.error("Exception---------"+e.getMessage());
				  }
				  finally{
				   if(session!=null && session.isOpen()){
				    session.close();
				   }
				  }
				  return visaOrderRow;
				 }
	
	
	
	public VisaTravelRequestQuotation updateVisaTravelRequestQuotation(VisaTravelRequestQuotation visaTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		VisaTravelRequestQuotation  visaTravelRequestQuotationFromDb=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			visaTravelRequestQuotationFromDb=(VisaTravelRequestQuotation) session.get(VisaTravelRequestQuotation.class,visaTravelRequestQuotation.getId());
			visaTravelRequestQuotationFromDb.setUpdatedAt(new Timestamp(new Date().getTime()));
			visaTravelRequestQuotationFromDb.setTravelDate(visaTravelRequestQuotation.getTravelDateTemp());
			visaTravelRequestQuotationFromDb.setRemarks(visaTravelRequestQuotation.getRemarks());
			visaTravelRequestQuotationFromDb.setCurrency(visaTravelRequestQuotation.getCurrency());
			session.update(visaTravelRequestQuotationFromDb);
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
		return visaTravelRequestQuotation;
	}
	
	public VisaOrderRow updateVisaOrderRow(VisaOrderRow  visaOrderRow) {
		Session session = null;
		Transaction transaction = null;
		VisaOrderRow visaOrderRowFromDb = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			visaOrderRowFromDb = (VisaOrderRow) session.get(VisaOrderRow.class,visaOrderRow.getId());
			visaOrderRowFromDb.setConfirmationNumber(visaOrderRow.getConfirmationNumber());
			visaOrderRowFromDb.setTravelDate(visaOrderRow.getTravelDate());
			visaOrderRowFromDb.setRemarks(visaOrderRow.getRemarks());
			visaOrderRowFromDb.setEmpNmae(visaOrderRow.getEmpNmae());
			visaOrderRowFromDb.setConfirmationNumber(visaOrderRow.getConfirmationNumber());
			visaOrderRowFromDb.setVfsCharges(visaOrderRow.getVfsCharges());
			visaOrderRowFromDb.setCourierCharges(visaOrderRow.getCourierCharges());
			visaOrderRowFromDb.setManagementFee(visaOrderRow.getManagementFee());
			visaOrderRowFromDb.setConvenienceFee(visaOrderRow.getConvenienceFee());
			visaOrderRowFromDb.setMarkUp(visaOrderRow.getMarkUp());
			visaOrderRowFromDb.setServiceTax(visaOrderRow.getServiceTax());
			visaOrderRowFromDb.setSupplierPrice(visaOrderRow.getSupplierPrice());
			visaOrderRowFromDb.setBasePrice(visaOrderRow.getBasePrice());
			visaOrderRowFromDb.setOtherTaxes(visaOrderRow.getOtherTaxes());
			visaOrderRowFromDb.setBasePrice(visaOrderRow.getBasePrice());
			visaOrderRowFromDb.setTotalAmount(visaOrderRow.getTotalAmount());
			session.update(visaOrderRowFromDb);
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
		return visaOrderRow;

	}


	public List<WalletAmountTranferHistory> getVisaOrderPaymentInfo(String orderId,int userId) {
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

	public void insertVisaOrderCustomer(VisaOrderCustomer visaOrderCustomer) {
		Session  session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(visaOrderCustomer);
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
