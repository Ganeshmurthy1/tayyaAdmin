package com.admin.hotel.fin.sheet.Dao;
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

import com.admin.hotel.fin.sheet.model.HotelFlightBookingStatus;
import com.admin.hotel.fin.sheet.model.HotelTravelRequest;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.PaymentOptions;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;
public class HotelTravelRequestDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelTravelRequestDao.class);
	public HotelTravelRequest insertHotelQuotationRowDetails(HotelTravelRequest hotelQuotationRow){
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(hotelQuotationRow);
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

		return hotelQuotationRow;

	}


	public List<HotelTravelRequest>  loadHotelQuotationRowList(User sessionUser){
		// TODO Auto-generated method stub
		Session session= null;
		List<HotelTravelRequest>  newList= new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelTravelRequest.class);
			criteria.add(Restrictions.eq("companyId", sessionUser.getCompanyid()));
			
			List<HotelTravelRequest> list= criteria.list();
			if(list!=null && list.size()>0){
				for(HotelTravelRequest hotetTravelRequest:list){
					hotetTravelRequest.setCheckIn(DateConversion.convertDateToStringDatewirhDDMonthYear(hotetTravelRequest.getCheckInDate()));
					hotetTravelRequest.setCheckOut(DateConversion.convertDateToStringDatewirhDDMonthYear(hotetTravelRequest.getCheckOutDate()));
					newList.add(hotetTravelRequest);
				}
			}

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

	public HotelTravelRequest getHotelQuotationRequestDetails(Long id){
		HotelTravelRequest hotetTravelRequest=null;

		Session session= null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			hotetTravelRequest= (HotelTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotetTravelRequest;

	}

	public HotelTravelRequestQuotation getHotelTravelRequestQuotationDetails(Long id){
		HotelTravelRequestQuotation newQuotationObj=new HotelTravelRequestQuotation();
		Session session= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("id", id));
			HotelTravelRequestQuotation quotationObj= (HotelTravelRequestQuotation) criteria.uniqueResult();
			if(quotationObj!=null){
				newQuotationObj.setId(quotationObj.getId());
				newQuotationObj.setAdditionalData(quotationObj.getAdditionalData());
				newQuotationObj.setAdultCount(quotationObj.getAdultCount());
				newQuotationObj.setAvailablePaymentOption(quotationObj.getAvailablePaymentOption());
				newQuotationObj.setBreakfast(quotationObj.getBreakfast());
				newQuotationObj.setCancellationPolicy(quotationObj.getCancellationPolicy());
				newQuotationObj.setCheckIn(DateConversion.convertDateToStringToDate(quotationObj.getCheckInDate()));
				newQuotationObj.setCheckInTime(quotationObj.getCheckInTime());
				newQuotationObj.setCheckOut(DateConversion.convertDateToStringToDate(quotationObj.getCheckOutDate()));
				newQuotationObj.setCheckOutTime(quotationObj.getCheckOutTime());
				newQuotationObj.setChildCount(quotationObj.getChildCount());
				newQuotationObj.setDistanceFromWork(quotationObj.getDistanceFromWork());
				newQuotationObj.setHotelAddress(quotationObj.getHotelAddress());
				newQuotationObj.setHotelCategory(quotationObj.getHotelCategory());
				newQuotationObj.setHotelCity(quotationObj.getHotelCity());
				newQuotationObj.setHotelCountry(quotationObj.getHotelCountry());
				newQuotationObj.setHotelName(quotationObj.getHotelName());
				newQuotationObj.setPreferProperty(quotationObj.isPreferProperty());
				newQuotationObj.setProjectAddress(quotationObj.getProjectAddress());
				newQuotationObj.setRoomCount(quotationObj.getRoomCount());
				newQuotationObj.setRoomRatePerNight(quotationObj.getRoomRatePerNight());
				newQuotationObj.setRoomType(quotationObj.getRoomType());
				newQuotationObj.setTaxes(quotationObj.getTaxes());
				newQuotationObj.setInternet(quotationObj.getInternet());
				newQuotationObj.setHotelTravelRequest(quotationObj.getHotelTravelRequest());
				newQuotationObj.setPaymentOptions(quotationObj.getPaymentOptions());
				newQuotationObj.setBookingMode(quotationObj.getBookingMode());
				newQuotationObj.setCityCode(quotationObj.getCityCode());
				newQuotationObj.setNightCount(quotationObj.getNightCount());
				newQuotationObj.setHotetFlightBookingStatus(quotationObj.getHotetFlightBookingStatus());
				newQuotationObj.setCurrency(quotationObj.getCurrency());


			}

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



	public boolean insertHotelQuotationList(List<HotelTravelRequestQuotation> hotelTravelRequestQuotation,HotelTravelRequest hotetTravelRequest){
		// TODO Auto-generated method stub
		boolean isInserted=false;
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(hotelTravelRequestQuotation!=null && hotelTravelRequestQuotation.size()>0){
				for(HotelTravelRequestQuotation hotelQuotation:hotelTravelRequestQuotation){
					hotelQuotation.setCreatedAt(new Timestamp(new Date().getTime()));
					hotelQuotation.setHotelTravelRequest(hotetTravelRequest);
					session.save(hotelQuotation);
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


	public List<HotelTravelRequestQuotation> getHotelRequestTravelQuotationList(Long hotelQuotationRequestId) {
		// TODO Auto-generated method stub
		List<HotelTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelTravelRequestQuotation com where com.hotelTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", hotelQuotationRequestId);
			Newlist= query.list();
			/*for(HotelTravelRequestQuotation quotation:list){
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


	public List<HotelTravelRequestQuotation> getHotelRequestTravelQuotationBookedList(Long hotelQuotationRequestId) {
		// TODO Auto-generated method stub
		List<HotelTravelRequestQuotation> Newlist=  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("isBooked", true));
			criteria.createCriteria("hotelTravelRequest").add(Restrictions.eq("id", hotelQuotationRequestId));
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






	public long  getHotelOrderRowIdFormHotelTravelRequestQuotation(Long hotelQuotationRequestId) {
		// TODO Auto-generated method stub
		HotelTravelRequestQuotation  Newlist=  null;
		long id = 0;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelTravelRequestQuotation com where com.hotelTravelRequest.id=:id and isBooked=:isBooked";
			Query query = session.createQuery(sql);
			query.setParameter("id", hotelQuotationRequestId);
			query.setParameter("isBooked", true);
			Newlist= (HotelTravelRequestQuotation) query.uniqueResult();
			if(Newlist!=null){
				id=Newlist.getOrderRowId();	
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


	public HotelTravelRequestQuotation hotelRequestQuotationUpdate(HotelTravelRequestQuotation hotelTravelRequestQuotation) {

		Session session= null;
		Transaction transaction=null;
		HotelTravelRequestQuotation  hotelTravelRequestQuotationUpdate=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			hotelTravelRequestQuotationUpdate=(HotelTravelRequestQuotation) session.get(HotelTravelRequestQuotation.class,hotelTravelRequestQuotation.getId());
			hotelTravelRequestQuotationUpdate.setHotelName(hotelTravelRequestQuotation.getHotelName());
			hotelTravelRequestQuotationUpdate.setSellRate(hotelTravelRequestQuotation.getSellRate());
			hotelTravelRequestQuotationUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.saveOrUpdate(hotelTravelRequestQuotationUpdate);
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
		return hotelTravelRequestQuotationUpdate;
	}

	public HotelTravelRequestQuotation updateHotelRequestQuotationWithOrderId(HotelOrderRow hotelOrderRow, Long hotelRequestQuotationId) {
		Session session= null;
		Transaction transaction=null;
		HotelTravelRequestQuotation  hotelTravelRequestQuotationUpdate=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			hotelTravelRequestQuotationUpdate=(HotelTravelRequestQuotation) session.get(HotelTravelRequestQuotation.class,hotelRequestQuotationId);
			hotelTravelRequestQuotationUpdate.setBooked(true);
			hotelTravelRequestQuotationUpdate.setOrderRowId(hotelOrderRow.getId());
			hotelTravelRequestQuotationUpdate.setHide(true);
			hotelTravelRequestQuotationUpdate.setStatusId(TravelRequestStatusEnum.BOOKED.getValue());
			session.saveOrUpdate(hotelTravelRequestQuotationUpdate);
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
		return hotelTravelRequestQuotationUpdate;

	}


	public HotelTravelRequest getHotelTravelRequestDetails(Long id) {

		Session session= null;

		HotelTravelRequest  hotetTravelRequest=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			hotetTravelRequest=(HotelTravelRequest) criteria.uniqueResult();
		} catch (Exception e) {

			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotetTravelRequest;

	}


	public HotelTravelRequest updateHotelTravelRequestDetails(HotelTravelRequest hotelQuotationRow) {
		Session session= null;
		Transaction transaction=null;
		HotelTravelRequest hotelTravelRequest=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			hotelTravelRequest=(HotelTravelRequest) session.get(HotelTravelRequest.class,hotelQuotationRow.getId());
			hotelTravelRequest.setTitle(hotelQuotationRow.getTitle());
			hotelTravelRequest.setFirstName(hotelQuotationRow.getFirstName());
			hotelTravelRequest.setLastName(hotelQuotationRow.getLastName());
			hotelTravelRequest.setAlternativeEmail(hotelQuotationRow.getAlternativeEmail());
			hotelTravelRequest.setCheckInDate(hotelQuotationRow.getCheckInDate());
			hotelTravelRequest.setCheckOutDate(hotelQuotationRow.getCheckOutDate());
			hotelTravelRequest.setCity(hotelQuotationRow.getCity());
			hotelTravelRequest.setCountry(hotelQuotationRow.getCountry());
			hotelTravelRequest.setCurrency(hotelQuotationRow.getCurrency());
			hotelTravelRequest.setEmpName(hotelQuotationRow.getEmpName());
			hotelTravelRequest.setEntity(hotelQuotationRow.getEntity());
			hotelTravelRequest.setTRNo(hotelQuotationRow.getTRNo());
			hotelTravelRequest.setNoOfNights(hotelQuotationRow.getNoOfNights());
			hotelTravelRequest.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.update(hotelTravelRequest);
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
		return hotelTravelRequest;


	}


	public List<HotelTravelRequestQuotation> getHotelTravelRequestQuotationId(Long hotelRequestQuotationId) {
		Session session= null;
		List<HotelTravelRequestQuotation> list=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelTravelRequestQuotation  where hotelTravelRequest.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", hotelRequestQuotationId);
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

	public   boolean updateHotelTravelRequestQuotationHide(Long hotelRequestQuotationId) {
		Session session= null;
		Transaction transaction=null;
		boolean isHide=false;
		List<HotelTravelRequestQuotation> list=getHotelTravelRequestQuotationId(hotelRequestQuotationId);
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(list!=null){
				for(HotelTravelRequestQuotation hotelTravelRequestQuotation:list){
					HotelTravelRequestQuotation  hotelTravelRequestQuotationUpdate=(HotelTravelRequestQuotation) session.get(HotelTravelRequestQuotation.class,hotelTravelRequestQuotation.getId());
					hotelTravelRequestQuotationUpdate.setHide(true);
					session.saveOrUpdate(hotelTravelRequestQuotationUpdate);
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

	public HotelTravelRequestQuotation  updateHotelQuotationList(List<HotelTravelRequestQuotation> hotelTravelRequestQuotationList) {
		boolean isUpdated=false;
		Session session= null;
		Transaction transaction=null;
		HotelTravelRequestQuotation hotelTravelRequestQuotation=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(hotelTravelRequestQuotationList!=null && hotelTravelRequestQuotationList.size()>0){
				for(HotelTravelRequestQuotation quotationObj:hotelTravelRequestQuotationList){
					HotelTravelRequestQuotation newQuotationObj =(HotelTravelRequestQuotation)session.get(HotelTravelRequestQuotation.class, quotationObj.getId()); 
					newQuotationObj.setAdditionalData(quotationObj.getAdditionalData());
					newQuotationObj.setAdultCount(quotationObj.getAdultCount());
					newQuotationObj.setAvailablePaymentOption(quotationObj.getAvailablePaymentOption());
					newQuotationObj.setBreakfast(quotationObj.getBreakfast());
					newQuotationObj.setCancellationPolicy(quotationObj.getCancellationPolicy());
					newQuotationObj.setCheckIn(DateConversion.convertDateToStringToDate(quotationObj.getCheckInDate()));
					newQuotationObj.setCheckInTime(quotationObj.getCheckInTime());
					newQuotationObj.setCheckOut(DateConversion.convertDateToStringToDate(quotationObj.getCheckOutDate()));
					newQuotationObj.setCheckOutTime(quotationObj.getCheckOutTime());
					newQuotationObj.setChildCount(quotationObj.getChildCount());
					newQuotationObj.setDistanceFromWork(quotationObj.getDistanceFromWork());
					newQuotationObj.setHotelAddress(quotationObj.getHotelAddress());
					newQuotationObj.setHotelCategory(quotationObj.getHotelCategory());
					newQuotationObj.setHotelCity(quotationObj.getHotelCity());
					newQuotationObj.setHotelCountry(quotationObj.getHotelCountry());
					newQuotationObj.setHotelName(quotationObj.getHotelName());
					newQuotationObj.setPreferProperty(quotationObj.isPreferProperty());
					newQuotationObj.setProjectAddress(quotationObj.getProjectAddress());
					newQuotationObj.setRoomCount(quotationObj.getRoomCount());
					newQuotationObj.setRoomRatePerNight(quotationObj.getRoomRatePerNight());
					newQuotationObj.setRoomType(quotationObj.getRoomType());
					newQuotationObj.setTaxes(quotationObj.getTaxes());
					newQuotationObj.setInternet(quotationObj.getInternet());
					newQuotationObj.setBookingMode(quotationObj.getBookingMode());
					newQuotationObj.setCityCode(quotationObj.getCityCode());
					//newQuotationObj.setHotelTravelRequest(quotationObj.getHotelTravelRequest());
					session.update(newQuotationObj);
					hotelTravelRequestQuotation=newQuotationObj;
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
		return hotelTravelRequestQuotation;
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

	public HotelTravelRequestQuotation getHotelQuotationDetails(Long hotelRowId) {
		Session  session=null;
		HotelTravelRequestQuotation hotelQuotation=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelTravelRequestQuotation.class);
			criteria.add(Restrictions.eq("orderRowId",hotelRowId));
			hotelQuotation=(HotelTravelRequestQuotation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return hotelQuotation;

	}
}
