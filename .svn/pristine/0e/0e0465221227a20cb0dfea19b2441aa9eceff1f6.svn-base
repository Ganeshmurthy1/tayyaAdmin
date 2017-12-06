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

import com.admin.common.quotation.model.TrainTravelRequest;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.PaymentOptions;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.config.HibernateUtil;
import com.tayyarah.bus.model.BusOrderCustomerDetail;
import com.tayyarah.train.model.TrainOrderCustomer;
public class TrainTravelRequestDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainTravelRequestDao.class);


	public TrainTravelRequest inserttrainTravelRequestnew(TrainTravelRequest trainTravelRequest){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(trainTravelRequest);
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

		return trainTravelRequest;

	}


	public List<TrainTravelRequest>  loadTrainTravelRequestList(User user){
		// TODO Auto-generated method stub
		Session session= null;
		List<TrainTravelRequest>  newList= new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(TrainTravelRequest.class);
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

	public TrainTravelRequest getTrainQuotationRequestDetails(Long id){
		TrainTravelRequest trainTravelRequest=null;

		Session session= null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(TrainTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			trainTravelRequest= (TrainTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return trainTravelRequest;

	}

	public TrainTravelRequestQuotation getTrainTravelRequestQuotationDetails(Long id){
		TrainTravelRequestQuotation newQuotationObj=new TrainTravelRequestQuotation();
		Session session= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(TrainTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("id", id));
			newQuotationObj= (TrainTravelRequestQuotation) criteria.uniqueResult();
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

	public List<WalletAmountTranferHistory> getTrainOrderPaymentInfo(String orderId,int userId) {
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


	public boolean insertTrainQuotationList(List<TrainTravelRequestQuotation> trainTravelRequestQuotations,TrainTravelRequest trainTravelRequest){
		// TODO Auto-generated method stub
		boolean isInserted=false;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(trainTravelRequestQuotations!=null && trainTravelRequestQuotations.size()>0){
				for(TrainTravelRequestQuotation trainTravelRequestQuotation:trainTravelRequestQuotations){
					trainTravelRequestQuotation.setCreatedAt(new Timestamp(new Date().getTime()));
					trainTravelRequestQuotation.setTrainTravelRequest(trainTravelRequest);
					session.save(trainTravelRequestQuotation);
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


	public List<TrainTravelRequestQuotation> getTrainRequestTravelQuotationList(Long trainQuotationRequestId) {
		// TODO Auto-generated method stub
		List<TrainTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainTravelRequestQuotation itr where itr.trainTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", trainQuotationRequestId);
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


	public List<TrainTravelRequestQuotation> getTrainRequestTravelQuotationBookedList(Long trainQuotationRequestId) {
		// TODO Auto-generated method stub
		List<TrainTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(TrainTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("isBooked", true));
			criteria.createCriteria("trainTravelRequest").add(Restrictions.eq("id", trainQuotationRequestId));
			Newlist = criteria.list();

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






	public long  getTrainOrderRowIdFormTrainTravelRequestQuotation(Long trainQuotationRequestId) {
		// TODO Auto-generated method stub
		TrainTravelRequestQuotation  trainTravelRequestQuotation=  null;
		long id = 0;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainTravelRequestQuotation itq where itq.trainTravelRequest.id=:id and isBooked=:isBooked";
			Query query = session.createQuery(sql);
			query.setParameter("id", trainQuotationRequestId);
			query.setParameter("isBooked", true);
			trainTravelRequestQuotation= (TrainTravelRequestQuotation) query.uniqueResult();
			if(trainTravelRequestQuotation!=null){
				id=trainTravelRequestQuotation.getOrderRowId();	
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


	public TrainTravelRequestQuotation trainRequestQuotationUpdate(TrainTravelRequestQuotation trainTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		TrainTravelRequestQuotation  carTravelRequestQuotationnew=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carTravelRequestQuotationnew=(TrainTravelRequestQuotation) session.get(TrainTravelRequestQuotation.class,trainTravelRequestQuotation.getId());
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

	public TrainTravelRequestQuotation updateTrainTravelRequestQuotationWithOrderId(TrainOrderRow trainOrderRow, Long trainQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		TrainTravelRequestQuotation  trainTravelRequestQuotation=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			trainTravelRequestQuotation=(TrainTravelRequestQuotation) session.get(TrainTravelRequestQuotation.class,trainQuotationRequestId);
			trainTravelRequestQuotation.setBooked(true);
			trainTravelRequestQuotation.setOrderRowId(trainOrderRow.getId());
			trainTravelRequestQuotation.setHide(true);
			trainTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.BOOKED.getValue());
			session.saveOrUpdate(trainTravelRequestQuotation);
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
		return trainTravelRequestQuotation;

	}


	public TrainTravelRequest getTrainTravelRequestDetails(Long id) {
		Session session= null;
		TrainTravelRequest  trainTravelRequest=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(TrainTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			trainTravelRequest = (TrainTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return trainTravelRequest;

	}


	public TrainTravelRequest updateTrainTravelRequestDetails(TrainTravelRequest trainTravelRequest) {
		Session session= null;
		Transaction transaction=null;
		TrainTravelRequest trainTravelRequestNew=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			trainTravelRequestNew=(TrainTravelRequest) session.get(TrainTravelRequest.class,trainTravelRequest.getId());
			trainTravelRequestNew.setTitle(trainTravelRequest.getTitle());
			trainTravelRequestNew.setFirstName(trainTravelRequest.getFirstName());
			trainTravelRequestNew.setLastName(trainTravelRequest.getLastName());
			
			trainTravelRequestNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(trainTravelRequestNew);
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
		return trainTravelRequestNew;


	}


	public List<TrainTravelRequestQuotation> getTrainTravelRequestQuotationId(Long trainQuotationRequestId) {
		Session session= null;
		List<TrainTravelRequestQuotation> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainTravelRequestQuotation  where TrainTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", trainQuotationRequestId);
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

	public   boolean updateTrainTravelRequestQuotationHide(Long trainQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		boolean isHide=false;
		List<TrainTravelRequestQuotation> list=getTrainTravelRequestQuotationId(trainQuotationRequestId);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(list!=null){
				for(TrainTravelRequestQuotation trainTravelRequestQuotation:list){
					TrainTravelRequestQuotation  trainTravelRequestQuotationNew=(TrainTravelRequestQuotation) session.get(TrainTravelRequestQuotation.class,trainTravelRequestQuotation.getId());
					trainTravelRequestQuotationNew.setHide(true);
					session.saveOrUpdate(trainTravelRequestQuotationNew);
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

	public TrainTravelRequestQuotation  updateTrainQuotationList(List<TrainTravelRequestQuotation> trainTravelRequestQuotations) {
		boolean isUpdated=false;
		Session session= null;
		Transaction transaction=null;
		TrainTravelRequestQuotation trainTravelRequestQuotationOuter=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(trainTravelRequestQuotations!=null && trainTravelRequestQuotations.size()>0){
				for(TrainTravelRequestQuotation trainTravelRequestQuotationData:trainTravelRequestQuotations){
					TrainTravelRequestQuotation trainTravelRequestQuotation =(TrainTravelRequestQuotation)session.get(TrainTravelRequestQuotation.class, trainTravelRequestQuotationData.getId()); 
					// write code for Insurrance
					session.update(trainTravelRequestQuotation);
					trainTravelRequestQuotationOuter=trainTravelRequestQuotation;
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
		return trainTravelRequestQuotationOuter;
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

	public TrainTravelRequestQuotation getTrainQuotationDetails(Long trainOrderRowId) {
		Session  session=null;
		TrainTravelRequestQuotation trainTravelRequestQuotation=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(TrainTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("orderRowId",trainOrderRowId));
			trainTravelRequestQuotation=(TrainTravelRequestQuotation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return trainTravelRequestQuotation;

	}

	
	public TrainOrderRow insertTrainOrderRow(TrainOrderRow trainOrderRow) {
		Session  session=null;
		Transaction  tx=null;
		 
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(trainOrderRow);
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
		return trainOrderRow;

	}


	public TrainOrderRow getTrainOrderRowDetailsById(Long id) {
		// TODO Auto-generated method stub
		 Session session= null;
		 TrainOrderRow  trainOrderRow=null;
		  try {
		   session = HibernateUtil.getSessionFactory().openSession();
		   Criteria criteria=session.createCriteria(TrainOrderRow.class);
		   criteria.add(Restrictions.eq("id", id));
		   trainOrderRow=(TrainOrderRow) criteria.uniqueResult();
		  } catch (Exception e) {
		   logger.error("Exception---------"+e.getMessage());
		  }
		  finally{
		   if(session!=null && session.isOpen()){
		    session.close();
		   }
		  }
		  return trainOrderRow;
		 }	
	
	public TrainOrderRow updateTrainOrderRow(TrainOrderRow  trainOrderRow) {
		Session session = null;
		Transaction transaction = null;
		TrainOrderRow trainOrderRowFromDb = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			trainOrderRowFromDb = (TrainOrderRow) session.get(TrainOrderRow.class,trainOrderRow.getId());
			trainOrderRowFromDb.setConfirmationNumber(trainOrderRow.getConfirmationNumber());
			trainOrderRowFromDb.setRemarks(trainOrderRow.getRemarks());
			trainOrderRowFromDb.setEmpNmae(trainOrderRow.getEmpNmae());
			trainOrderRowFromDb.setSupplierPrice(trainOrderRow.getSupplierPrice());
			trainOrderRowFromDb.setInvoiceNo(trainOrderRow.getInvoiceNo());
			trainOrderRowFromDb.setApiComments(trainOrderRow.getApiComments());
			trainOrderRowFromDb.setTravelDate(trainOrderRow.getTravelDate());
			trainOrderRowFromDb.setManagementFee(trainOrderRow.getManagementFee());
			trainOrderRowFromDb.setConvenienceFee(trainOrderRow.getConvenienceFee());
			trainOrderRowFromDb.setMarkUp(trainOrderRow.getMarkUp());
			trainOrderRowFromDb.setServiceTax(trainOrderRow.getServiceTax());
			trainOrderRowFromDb.setOtherTaxes(trainOrderRow.getOtherTaxes());
			trainOrderRowFromDb.setBasePrice(trainOrderRow.getBasePrice());
			trainOrderRowFromDb.setTotalAmount(trainOrderRow.getTotalAmount());
			trainOrderRowFromDb.setApiToBaseExchangeRate(trainOrderRow.getApiToBaseExchangeRate());
			trainOrderRowFromDb.setConfirmationNumber(trainOrderRow.getConfirmationNumber());
			trainOrderRowFromDb.setMarkUp(trainOrderRow.getMarkUp());
			trainOrderRowFromDb.setTaxes(trainOrderRow.getTaxes());
			trainOrderRowFromDb.setProcessingFees(trainOrderRow.getProcessingFees());
			trainOrderRowFromDb.setBookingCurrency(trainOrderRow.getBookingCurrency());
			trainOrderRowFromDb.setPaidBy(trainOrderRow.getPaidBy());
			trainOrderRowFromDb.setPaymentStatus(trainOrderRow.getPaymentStatus());
			trainOrderRowFromDb.setStatusAction(trainOrderRow.getStatusAction());
			session.update(trainOrderRowFromDb);
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
		return trainOrderRow;

	}
	public TrainTravelRequestQuotation updateTrainTravelRequestQuotation(TrainTravelRequestQuotation trainTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		TrainTravelRequestQuotation  trainTravelRequestQuotationFromDb=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			trainTravelRequestQuotationFromDb=(TrainTravelRequestQuotation) session.get(TrainTravelRequestQuotation.class,trainTravelRequestQuotation.getId());
			trainTravelRequestQuotationFromDb.setUpdatedAt(new Timestamp(new Date().getTime()));
			trainTravelRequestQuotationFromDb.setTrainNumber(trainTravelRequestQuotation.getTrainNumber());
			trainTravelRequestQuotationFromDb.setFromlocation(trainTravelRequestQuotation.getFromlocation());
			trainTravelRequestQuotationFromDb.setTolocation(trainTravelRequestQuotation.getTolocation());
			trainTravelRequestQuotationFromDb.setTravelDate(trainTravelRequestQuotation.getTravelDate());
			trainTravelRequestQuotationFromDb.setRemarks(trainTravelRequestQuotation.getRemarks());
			trainTravelRequestQuotationFromDb.setCurrency(trainTravelRequestQuotation.getCurrency());
			session.update(trainTravelRequestQuotationFromDb);
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
		return trainTravelRequestQuotation;
	}
	
	public TrainTravelRequestQuotation getTrainTravelRequestQuotationDetailsByTrainOrderRowId(Long id){
		TrainTravelRequestQuotation newQuotationObj=new TrainTravelRequestQuotation();
		Session session= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(TrainTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("orderRowId", id));
			newQuotationObj= (TrainTravelRequestQuotation) criteria.uniqueResult();
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
	
	public void insertTrainOrderCustomer(TrainOrderCustomer trainOrderCustomer) {
		Session  session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(trainOrderCustomer);
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
