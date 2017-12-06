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

import com.admin.common.quotation.model.BusTravelRequest;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.admin.common.quotation.model.CarTravelRequest;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.PaymentOptions;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.tayyarah.bus.model.BusOrderCustomerDetail;
public class BusTravelRequestDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BusTravelRequestDao.class);


	public BusTravelRequest insertbusTravelRequestnew(BusTravelRequest busTravelRequest){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(busTravelRequest);
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

		return busTravelRequest;

	}

	public List<WalletAmountTranferHistory> getBusOrderPaymentInfo(String orderId,int userId) {
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

	public List<BusTravelRequest>  loadBusTravelRequestList(User user){
		// TODO Auto-generated method stub
		Session session= null;
		List<BusTravelRequest>  newList= new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusTravelRequest.class);
			criteria.add(Restrictions.eq("companyId", user.getCompanyid()));
			List<BusTravelRequest> list= criteria.list();
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

	public BusTravelRequest getBusQuotationRequestDetails(Long id){
		BusTravelRequest busTravelRequest=null;

		Session session= null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			busTravelRequest= (BusTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return busTravelRequest;

	}

	public BusTravelRequestQuotation getBusTravelRequestQuotationDetails(Long id){
		BusTravelRequestQuotation newQuotationObj=new BusTravelRequestQuotation();
		Session session= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CarTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("id", id));
			newQuotationObj= (BusTravelRequestQuotation) criteria.uniqueResult();
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



	public boolean insertBusQuotationList(List<BusTravelRequestQuotation> busTravelRequestQuotations,BusTravelRequest busTravelRequest){
		// TODO Auto-generated method stub
		boolean isInserted=false;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(busTravelRequestQuotations!=null && busTravelRequestQuotations.size()>0){
				for(BusTravelRequestQuotation busTravelRequestQuotation:busTravelRequestQuotations){
					busTravelRequestQuotation.setCreatedAt(new Timestamp(new Date().getTime()));
					busTravelRequestQuotation.setBusTravelRequest(busTravelRequest);
					session.save(busTravelRequestQuotation);
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

	public boolean updateBusTravelRequestQuotationById(List<BusTravelRequestQuotation> busTravelRequestQuotationsList){
		Session session=null;
		Transaction transaction =null;
		boolean isInserted=false;
		BusTravelRequestQuotation busTravelRequestQuotation=null;
		try{
			for(BusTravelRequestQuotation requestQuotation:busTravelRequestQuotationsList){
				session=HibernateUtil.getSessionFactory().openSession();
				transaction=session.beginTransaction();
				busTravelRequestQuotation=(BusTravelRequestQuotation) session.get(BusTravelRequestQuotation.class, requestQuotation.getId());
				busTravelRequestQuotation.setOrderRowId(requestQuotation.getId());
				session.saveOrUpdate(busTravelRequestQuotation);
				transaction.commit();
				isInserted=true;
			}

		}catch (Exception e) {
			transaction.rollback();
			isInserted=false;
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return isInserted;
	}

	public List<BusTravelRequestQuotation> getBusRequestTravelQuotationList(Long busQuotationRequestId) {
		// TODO Auto-generated method stub
		List<BusTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusTravelRequestQuotation itr where itr.busTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", busQuotationRequestId);
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


	public List<BusTravelRequestQuotation> getBusRequestTravelQuotationBookedList(Long busQuotationRequestId) {
		// TODO Auto-generated method stub
		List<BusTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("isBooked", true));
			criteria.createCriteria("busTravelRequest").add(Restrictions.eq("id", busQuotationRequestId));
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






	public long  getBusOrderRowIdFormBusTravelRequestQuotation(Long busQuotationRequestId) {
		// TODO Auto-generated method stub
		BusTravelRequestQuotation  busTravelRequestQuotation=  null;
		long id = 0;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusTravelRequestQuotation itq where itq.busTravelRequest.id=:id and isBooked=:isBooked";
			Query query = session.createQuery(sql);
			query.setParameter("id", busQuotationRequestId);
			query.setParameter("isBooked", true);
			busTravelRequestQuotation= (BusTravelRequestQuotation) query.uniqueResult();
			if(busTravelRequestQuotation!=null){
				id=busTravelRequestQuotation.getOrderRowId();	
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


	public BusTravelRequestQuotation busRequestQuotationUpdate(BusTravelRequestQuotation busTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		BusTravelRequestQuotation  busTravelRequestQuotationInner=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			busTravelRequestQuotationInner=(BusTravelRequestQuotation) session.get(BusTravelRequestQuotation.class,busTravelRequestQuotation.getId());
			busTravelRequestQuotationInner.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(busTravelRequestQuotationInner);
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
		return busTravelRequestQuotationInner;
	}

	public BusTravelRequestQuotation updateBusTravelRequestQuotationWithOrderId(BusOrderRow busOrderRow, Long busQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		BusTravelRequestQuotation  busTravelRequestQuotation=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			busTravelRequestQuotation=(BusTravelRequestQuotation) session.get(BusTravelRequestQuotation.class,busQuotationRequestId);
			busTravelRequestQuotation.setBooked(true);
			busTravelRequestQuotation.setOrderRowId(busOrderRow.getId());
			busTravelRequestQuotation.setHide(true);
			busTravelRequestQuotation.setStatusId(TravelRequestStatusEnum.BOOKED.getValue());
			session.saveOrUpdate(busTravelRequestQuotation);
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
		return busTravelRequestQuotation;

	}


	public BusTravelRequest getBusTravelRequestDetails(Long id) {
		Session session= null;
		BusTravelRequest  busTravelRequest=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			busTravelRequest=(BusTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return busTravelRequest;

	}

	public BusOrderRow getBusOrderRowDetailsById(Long id) {
		Session session= null;
		BusOrderRow  busOrderRow=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusOrderRow.class);
			criteria.add(Restrictions.eq("id", id));
			busOrderRow=(BusOrderRow) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return busOrderRow;
	}


	public BusTravelRequest updateBusTravelRequestDetails(BusTravelRequest busTravelRequest) {
		Session session= null;
		Transaction transaction=null;
		BusTravelRequest busTravelRequestNew=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			busTravelRequestNew=(BusTravelRequest) session.get(BusTravelRequest.class,busTravelRequest.getId());
			busTravelRequestNew.setTitle(busTravelRequest.getTitle());
			busTravelRequestNew.setFirstName(busTravelRequest.getFirstName());
			busTravelRequestNew.setLastName(busTravelRequest.getLastName());
			busTravelRequestNew.setTRNo(busTravelRequest.getTRNo());
			busTravelRequestNew.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(busTravelRequestNew);
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
		return busTravelRequestNew;


	}

	public List<BusTravelRequest>  getAllBusTravelRequestList(){
		// TODO Auto-generated method stub
		Session session= null;
		List<BusTravelRequest>  newList= new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusTravelRequest.class);
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


	public List<BusTravelRequestQuotation> getBusTravelRequestQuotationId(Long busQuotationRequestId) {
		Session session= null;
		List<BusTravelRequestQuotation> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusTravelRequestQuotation  where BusTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", busQuotationRequestId);
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

	public   boolean updateBusTravelRequestQuotationHide(Long busQuotationRequestId) {
		Session session= null;
		Transaction transaction=null;
		boolean isHide=false;
		List<BusTravelRequestQuotation> list=getBusTravelRequestQuotationId(busQuotationRequestId);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(list!=null){
				for(BusTravelRequestQuotation busTravelRequestQuotation:list){
					BusTravelRequestQuotation  busTravelRequestQuotationNew=(BusTravelRequestQuotation) session.get(BusTravelRequestQuotation.class,busTravelRequestQuotation.getId());
					busTravelRequestQuotationNew.setHide(true);
					session.saveOrUpdate(busTravelRequestQuotationNew);
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

	public BusTravelRequestQuotation  updateBusQuotationList(List<BusTravelRequestQuotation> busTravelRequestQuotations) {
		boolean isUpdated=false;
		Session session= null;
		Transaction transaction=null;
		BusTravelRequestQuotation busTravelRequestQuotationOuter=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(busTravelRequestQuotations!=null && busTravelRequestQuotations.size()>0){
				for(BusTravelRequestQuotation busTravelRequestQuotationData:busTravelRequestQuotations){
					BusTravelRequestQuotation busTravelRequestQuotation =(BusTravelRequestQuotation)session.get(BusTravelRequestQuotation.class, busTravelRequestQuotationData.getId()); 
					// write code for Insurrance
					session.update(busTravelRequestQuotation);
					busTravelRequestQuotationOuter=busTravelRequestQuotation;
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
		return busTravelRequestQuotationOuter;
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

	public BusTravelRequestQuotation getBusQuotationDetails(Long busOrderRowId) {
		Session  session=null;
		BusTravelRequestQuotation busTravelRequestQuotation=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("orderRowId",busOrderRowId));
			busTravelRequestQuotation=(BusTravelRequestQuotation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return busTravelRequestQuotation;

	}


	public BusOrderRow insertBusOrderRow(BusOrderRow busOrderRow) {
		Session  session=null;
		Transaction  tx=null;

		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(busOrderRow);
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
		return busOrderRow;

	}
	public void insertBusOrderCustomerDetail(BusOrderCustomerDetail busOrderCustomerDetail) {
		Session  session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(busOrderCustomerDetail);
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


	/*public BusOrderRow updateBusOrderRow(BusOrderRow  busOrderRow) {
		Session session = null;
		Transaction transaction = null;
		BusOrderRow busOrderRowFromDb = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			busOrderRowFromDb = (BusOrderRow) session.get(BusTravelRequestQuotation.class,busOrderRow.getId());
			busOrderRowFromDb.setConfirmationNumber(busOrderRow.getConfirmationNumber());
			busOrderRowFromDb.setBusCompanyName(busOrderRow.getBusCompanyName());
			busOrderRowFromDb.setLocation(busOrderRow.getLocation());
			busOrderRowFromDb.setTravelDate(DateConversion.StringToDate(busOrderRow.getTravelDateTemp()));
			busOrderRowFromDb.setManagementFee(busOrderRow.getManagementFee());
			busOrderRowFromDb.setConvenienceFee(busOrderRow.getConvenienceFee());
			busOrderRowFromDb.setMarkUp(busOrderRow.getMarkUp());
			busOrderRowFromDb.setServiceTax(busOrderRow.getServiceTax());
			busOrderRowFromDb.setOtherTaxes(busOrderRow.getOtherTaxes());
			busOrderRowFromDb.setBasePrice(busOrderRow.getBasePrice());
			busOrderRowFromDb.setTotalAmount(busOrderRow.getTotalAmount());
			session.update(busOrderRowFromDb);
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
		return busOrderRow;

	}
	 */

	public BusTravelRequestQuotation updateBusTravelRequestQuotation(BusTravelRequestQuotation busTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		BusTravelRequestQuotation  busTravelRequestQuotationFromDb=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			busTravelRequestQuotationFromDb=(BusTravelRequestQuotation) session.get(BusTravelRequestQuotation.class,busTravelRequestQuotation.getId());
			busTravelRequestQuotationFromDb.setUpdatedAt(new Timestamp(new Date().getTime()));
			busTravelRequestQuotationFromDb.setPickUp(busTravelRequestQuotation.getPickUp());
			busTravelRequestQuotationFromDb.setDropOff(busTravelRequestQuotation.getDropOff());
			busTravelRequestQuotationFromDb.setRemarks(busTravelRequestQuotation.getRemarks());
			busTravelRequestQuotationFromDb.setCurrency(busTravelRequestQuotation.getCurrency());
			busTravelRequestQuotationFromDb.setTotalAmount(busTravelRequestQuotation.getTotalAmount());
			session.update(busTravelRequestQuotationFromDb);
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
		return busTravelRequestQuotation;
	}

	public BusOrderRow updateBusOrderRow(BusOrderRow  busOrderRow) {
		Session session = null;
		Transaction transaction = null;
		BusOrderRow busOrderRowFromDb = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			busOrderRowFromDb = (BusOrderRow) session.get(BusOrderRow.class,busOrderRow.getId());
			busOrderRowFromDb.setConfirmationNumber(busOrderRow.getConfirmationNumber());
			busOrderRowFromDb.setBusType(busOrderRow.getBusType());
			busOrderRowFromDb.setBusCompanyName(busOrderRow.getBusCompanyName());
			busOrderRowFromDb.setLocation(busOrderRow.getLocation());
			busOrderRowFromDb.setTravelDate(busOrderRow.getTravelDate());
			busOrderRowFromDb.setPickUp(busOrderRow.getPickUp());
			busOrderRowFromDb.setDropOff(busOrderRow.getDropOff());
			busOrderRowFromDb.setSupplierPrice(busOrderRow.getSupplierPrice());
			busOrderRowFromDb.setManagementFee(busOrderRow.getManagementFee());
			busOrderRowFromDb.setConvenienceFee(busOrderRow.getConvenienceFee());
			busOrderRowFromDb.setMarkUp(busOrderRow.getMarkUp());
			busOrderRowFromDb.setServiceTax(busOrderRow.getServiceTax());
			busOrderRowFromDb.setOtherTaxes(busOrderRow.getOtherTaxes());
			busOrderRowFromDb.setBasePrice(busOrderRow.getBasePrice());
			busOrderRowFromDb.setTotalAmount(busOrderRow.getTotalAmount());
			busOrderRowFromDb.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(busOrderRowFromDb);
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
		return busOrderRow;

	}

  public List<BusOrderCustomerDetail> getBusOrderCustomerDetailList(long orderrowId){
	  List<BusOrderCustomerDetail> busOrderCustomerDetailList = new ArrayList<>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusOrderCustomerDetail.class);
			criteria.add(Restrictions.eq("busOrderRow.id",orderrowId));
			busOrderCustomerDetailList = criteria.list();
			
		}catch (Exception e) {
			logger.error("getBusOrderCustomerDetailList Exception---------" + e.getMessage());
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return busOrderCustomerDetailList;
  }




}
