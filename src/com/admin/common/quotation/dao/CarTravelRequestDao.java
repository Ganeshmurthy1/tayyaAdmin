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
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.PaymentOptions;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.tayyarah.car.model.CarOrderCustomer;
import com.tayyarah.train.model.TrainOrderCustomer;
public class CarTravelRequestDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CarTravelRequestDao.class);


	public CarTravelRequest insertcarTravelRequestnew(CarTravelRequest carTravelRequest){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(carTravelRequest);
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

		return carTravelRequest;

	}
	public List<WalletAmountTranferHistory> getCarOrderPaymentInfo(String orderId,int userId) {
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

	public List<CarTravelRequest>  loadCarTravelRequestList(User user){
		// TODO Auto-generated method stub
		Session session= null;
		List<CarTravelRequest>  newList= new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequest.class);
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

	public CarTravelRequest getCarQuotationRequestDetails(Long id){
		CarTravelRequest carTravelRequest=null;

		Session session= null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			carTravelRequest= (CarTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return carTravelRequest;

	}

	public CarTravelRequestQuotation getCarTravelRequestQuotationDetails(Long id){
		CarTravelRequestQuotation newQuotationObj=new CarTravelRequestQuotation();
		Session session= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("id", id));
			newQuotationObj= (CarTravelRequestQuotation) criteria.uniqueResult();
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



	public boolean insertCarQuotationList(List<CarTravelRequestQuotation> carTravelRequestQuotations,CarTravelRequest carTravelRequest){
		// TODO Auto-generated method stub
		boolean isInserted=false;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(carTravelRequestQuotations!=null && carTravelRequestQuotations.size()>0){
				for(CarTravelRequestQuotation carTravelRequestQuotation:carTravelRequestQuotations){
					carTravelRequestQuotation.setCreatedAt(new Timestamp(new Date().getTime()));
					carTravelRequestQuotation.setCarTravelRequest(carTravelRequest);
					session.save(carTravelRequestQuotation);
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


	public List<CarTravelRequestQuotation> getCarRequestTravelQuotationList(Long carQuotationRequestId) {
		// TODO Auto-generated method stub
		List<CarTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarTravelRequestQuotation itr where itr.carTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", carQuotationRequestId);
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


	public List<CarTravelRequestQuotation> getCarRequestTravelQuotationBookedList(Long carQuotationRequestId) {
		// TODO Auto-generated method stub
		List<CarTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("isBooked", true));
			criteria.createCriteria("carTravelRequest").add(Restrictions.eq("id", carQuotationRequestId));
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

	public List<CarTravelRequestQuotation> getCarRequestTravelQuotationOrderBookedList(Long carQuotationRequestId) {
		// TODO Auto-generated method stub
		List<CarTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("isBooked", true));
			criteria.add(Restrictions.eq("orderRowId", carQuotationRequestId));
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





	public long  getCarOrderRowIdFormCarTravelRequestQuotation(Long carQuotationRequestId) {
		// TODO Auto-generated method stub
		CarTravelRequestQuotation  carTravelRequestQuotation=  null;
		long id = 0;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarTravelRequestQuotation itq where itq.carTravelRequest.id=:id and isBooked=:isBooked";
			Query query = session.createQuery(sql);
			query.setParameter("id", carQuotationRequestId);
			query.setParameter("isBooked", true);
			carTravelRequestQuotation= (CarTravelRequestQuotation) query.uniqueResult();
			if(carTravelRequestQuotation!=null){
				id=carTravelRequestQuotation.getOrderRowId();	
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


	public CarTravelRequestQuotation carRequestQuotationUpdate(CarTravelRequestQuotation carTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		CarTravelRequestQuotation  carTravelRequestQuotationnew=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carTravelRequestQuotationnew=(CarTravelRequestQuotation) session.get(CarTravelRequestQuotation.class,carTravelRequestQuotation.getId());
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

	
	
	public CarTravelRequestQuotation updateCarTravelRequestQuotation(CarTravelRequestQuotation carTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		CarTravelRequestQuotation  carTravelRequestQuotationFromDb=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carTravelRequestQuotationFromDb=(CarTravelRequestQuotation) session.get(CarTravelRequestQuotation.class,carTravelRequestQuotation.getId());
			carTravelRequestQuotationFromDb.setUpdatedAt(new Timestamp(new Date().getTime()));
			carTravelRequestQuotationFromDb.setPickUp(carTravelRequestQuotation.getPickUp());
			carTravelRequestQuotationFromDb.setDropOff(carTravelRequestQuotation.getDropOff());
			carTravelRequestQuotationFromDb.setRemarks(carTravelRequestQuotation.getRemarks());
			carTravelRequestQuotationFromDb.setCurrency(carTravelRequestQuotation.getCurrency());
			session.update(carTravelRequestQuotationFromDb);
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
		return carTravelRequestQuotation;
	}
	public CarTravelRequestQuotation updateCarTravelRequestQuotationWithOrderId(CarOrderRow carOrderRow, Long carQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		CarTravelRequestQuotation  carTravelRequestQuotation=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carTravelRequestQuotation=(CarTravelRequestQuotation) session.get(CarTravelRequestQuotation.class,carQuotationRequestId);
			carTravelRequestQuotation.setBooked(true);
			carTravelRequestQuotation.setOrderRowId(carOrderRow.getId());
			carTravelRequestQuotation.setHide(true);
			carTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.BOOKED.getValue());
			session.saveOrUpdate(carTravelRequestQuotation);
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
		return carTravelRequestQuotation;

	}


	public CarTravelRequest getCarTravelRequestDetails(Long id) {
		Session session= null;
		CarTravelRequest  carTravelRequest=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			carTravelRequest=(CarTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return carTravelRequest;

	}


	public CarTravelRequest updateCarTravelRequestDetails(CarTravelRequest carTravelRequest) {
		Session session= null;
		Transaction transaction=null;
		CarTravelRequest carTravelRequestNew=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carTravelRequestNew=(CarTravelRequest) session.get(CarTravelRequest.class,carTravelRequest.getId());
			carTravelRequestNew.setTitle(carTravelRequest.getTitle());
			carTravelRequestNew.setFirstName(carTravelRequest.getFirstName());
			carTravelRequestNew.setLastName(carTravelRequest.getLastName());
			carTravelRequestNew.setTRNo(carTravelRequest.getTRNo());
			carTravelRequestNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(carTravelRequestNew);
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
		return carTravelRequestNew;


	}


	public List<CarTravelRequestQuotation> getCarTravelRequestQuotationId(Long carQuotationRequestId) {
		Session session= null;
		List<CarTravelRequestQuotation> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarTravelRequestQuotation  where CarTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", carQuotationRequestId);
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

	public   boolean updateCarTravelRequestQuotationHide(Long carQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		boolean isHide=false;
		List<CarTravelRequestQuotation> list=getCarTravelRequestQuotationId(carQuotationRequestId);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(list!=null){
				for(CarTravelRequestQuotation carTravelRequestQuotation:list){
					CarTravelRequestQuotation  carTravelRequestQuotationNew=(CarTravelRequestQuotation) session.get(CarTravelRequestQuotation.class,carTravelRequestQuotation.getId());
					carTravelRequestQuotationNew.setHide(true);
					session.saveOrUpdate(carTravelRequestQuotationNew);
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

	public CarTravelRequestQuotation  updateCarQuotationList(List<CarTravelRequestQuotation> carTravelRequestQuotations) {
		boolean isUpdated=false;
		Session session= null;
		Transaction transaction=null;
		CarTravelRequestQuotation carTravelRequestQuotationOuter=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(carTravelRequestQuotations!=null && carTravelRequestQuotations.size()>0){
				for(CarTravelRequestQuotation carTravelRequestQuotationData:carTravelRequestQuotations){
					CarTravelRequestQuotation carTravelRequestQuotation =(CarTravelRequestQuotation)session.get(CarTravelRequestQuotation.class, carTravelRequestQuotationData.getId()); 
					// write code for Insurrance
					session.update(carTravelRequestQuotation);
					carTravelRequestQuotationOuter=carTravelRequestQuotation;
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
		return carTravelRequestQuotationOuter;
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

	public CarTravelRequestQuotation getCarQuotationDetails(Long carOrderRowId) {
		Session  session=null;
		CarTravelRequestQuotation carTravelRequestQuotation=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("orderRowId",carOrderRowId));
			carTravelRequestQuotation=(CarTravelRequestQuotation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return carTravelRequestQuotation;

	}

	
	public CarOrderRow insertCarOrderRow(CarOrderRow carOrderRow) {
		Session  session=null;
		Transaction  tx=null;
		 
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(carOrderRow);
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
		return carOrderRow;

	}


	public CarOrderRow getCarOrderRowDetailsById(Long id) {
		 Session session= null;
		 CarOrderRow  carOrderRow=null;
		  try {
		   session = HibernateUtil.getSessionFactory().openSession();
		   Criteria criteria=session.createCriteria(CarOrderRow.class);
		   criteria.add(Restrictions.eq("id", id));
		   carOrderRow=(CarOrderRow) criteria.uniqueResult();
		  } catch (Exception e) {
		   logger.error("Exception---------"+e.getMessage());
		  }
		  finally{
		   if(session!=null && session.isOpen()){
		    session.close();
		   }
		  }
		  return carOrderRow;
		 }

	
	
	public CarOrderRow updateCarOrderRow(CarOrderRow  carOrderRow) {
		Session session = null;
		Transaction transaction = null;
		CarOrderRow carOrderRowFromDb = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			carOrderRowFromDb = (CarOrderRow) session.get(CarOrderRow.class,carOrderRow.getId());
			carOrderRowFromDb.setConfirmationNumber(carOrderRow.getConfirmationNumber());
			carOrderRowFromDb.setCarCompanyName(carOrderRow.getCarCompanyName());
			carOrderRowFromDb.setLocation(carOrderRow.getLocation());
			carOrderRowFromDb.setTravelDate(carOrderRow.getTravelDateTemp());
			carOrderRowFromDb.setTollOrParkingCharges(carOrderRow.getTollOrParkingCharges());
			carOrderRowFromDb.setDriverAllowanceDay(carOrderRow.getDriverAllowanceDay());
			carOrderRowFromDb.setDriverAllowanceNight(carOrderRow.getDriverAllowanceNight());
			carOrderRowFromDb.setManagementFee(carOrderRow.getManagementFee());
			carOrderRowFromDb.setConvenienceFee(carOrderRow.getConvenienceFee());
			carOrderRowFromDb.setMarkUp(carOrderRow.getMarkUp());
			carOrderRowFromDb.setServiceTax(carOrderRow.getServiceTax());
			carOrderRowFromDb.setExtraKM(carOrderRow.getExtraKM());
			carOrderRowFromDb.setExtraHours(carOrderRow.getExtraHours());
			carOrderRowFromDb.setOtherTaxes(carOrderRow.getOtherTaxes());
			carOrderRowFromDb.setBasePrice(carOrderRow.getBasePrice());
			carOrderRowFromDb.setTotalAmount(carOrderRow.getTotalAmount());
			carOrderRowFromDb.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(carOrderRowFromDb);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Exception---------" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return carOrderRow;
	

	}
	
	public void insertCarOrderCustomer(CarOrderCustomer carOrderCustomer) {
		Session  session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(carOrderCustomer);
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
