package com.lintas.action.CreditNote.Dao;

import java.math.BigDecimal;
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

import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.common.model.HotelOrderRowCancellation;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class HotelCreditNoteDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelCreditNoteDao.class);
	/*load all FlightOrderRow data for Invoice*/
	public  List<HotelOrderRow> getHotelAgentCreditNoteList(Company sessionCompany){
		Session session = null;
		List<HotelOrderRow> hotelOrderList=new ArrayList<HotelOrderRow>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRow hr where hr.companyId=:company_id order by hr.id desc";
			logger.info("-----filterQuery------------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("company_id",String.valueOf(sessionCompany.getCompanyid()));
			List<HotelOrderRow> list=query.list();
			for(HotelOrderRow invoiceData:list){
				BigDecimal basePrice= invoiceData.getApiPrice().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal taxes= invoiceData.getTaxes().multiply(invoiceData.getApiToBaseExchangeRate()) ;
				BigDecimal totalBasePrice=basePrice.add(invoiceData.getMarkupAmount());
				BigDecimal basePriceInBooking=totalBasePrice.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal taxesInBooking=taxes.multiply(invoiceData.getBaseToBookingExchangeRate());
				BigDecimal totalPrice=invoiceData.getFeeAmount().add(basePriceInBooking).add(taxesInBooking);
				invoiceData.setFinalPrice(totalPrice.setScale(2, BigDecimal.ROUND_UP));
				invoiceData.setCheckIn(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getCheckInDate()));
				invoiceData.setCheckOut(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getCheckOutDate()));
				hotelOrderList.add(invoiceData);
			}

		}catch(HibernateException e){
			logger.error("-------------------HibernateException--------------------"+e.getMessage());
		}
		catch(Exception e){
			logger.error("-------------------Exception--------------------"+e.getMessage());
		}	
		finally{
			session.close();
		}
		return hotelOrderList;
	}

	public HotelOrderRow getHotelOrderRowDataForCreditNote(Long id) {
		// TODO Auto-generated method stub
		HotelOrderRow hotelOrderRow=null;
		Session session = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelOrderRow hr where hr.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			hotelOrderRow = (HotelOrderRow) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRow;
	}
	/*public List<WalletAmountTranferHistory> getWalletHistoryDetailsByHotelOrderId(String orderReference) {
		// TODO Auto-generated method stub
		Session session = null;

		List<WalletAmountTranferHistory> historyList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from WalletAmountTranferHistory  wt where wt.actionId=:action_id";
			Query query = session.createQuery(sql);
			query.setParameter("action_id", orderReference);
			historyList = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return historyList;

	}	*/
	public HotelCreditNote updateRefundingAmount(HotelCreditNote creditNote) {
		// TODO Auto-generated method stub
		HotelCreditNote updateCreditNote   = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCreditNote =  (HotelCreditNote) session.get(HotelCreditNote.class,creditNote.getId());
			updateCreditNote.setRefundedAmount(creditNote.getRefundedAmount());
			updateCreditNote.setOrderUpdated(true);
			updateCreditNote.setCreditnoteIssued(true);
			updateCreditNote.setTotalBookingAmount(creditNote.getTotalBookingAmount());
			updateCreditNote.setIssuedAt(new Timestamp(new Date().getTime()));
			session.save(updateCreditNote);
			transaction.commit();
		}
		catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			session.close();
		}
		return updateCreditNote;

	}
	  
	public HotelOrderRow updateCreditNoteIssuedInHotelOrderRow(Long id,boolean setCreditNoteIssued) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		HotelOrderRow updateHotelOrderRow   = null;
		/*logger.info("updateUserWallet getWalletbalance------------------"+userWallet.getWalletbalance());
		logger.info("updateUserWallet getWalletId------------------"+userWallet.getWalletId());*/
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateHotelOrderRow =  (HotelOrderRow) session.get(HotelOrderRow.class,id);
			updateHotelOrderRow.setCreditNoteIssued(setCreditNoteIssued);
			session.update(updateHotelOrderRow);
			transaction.commit();

		}
		catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return updateHotelOrderRow;
	}
 

	public List<HotelCreditNote> getCreditNoteListByOrderRowID(Long id) {
		// TODO Auto-generated method stub
		Session session = null;

		List<HotelCreditNote> creditNoteList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelCreditNote hcn where hcn.rowId=:row_id order by hcn.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("row_id", id.intValue());
			creditNoteList = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return creditNoteList;
	}
	 
  
	public HotelCreditNote getCreditNoteDetailsByComapnyId(int companyid, Long id) {
		HotelCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelCreditNote cn where cn.companyId=:company_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(companyid));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(HotelCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}

	public HotelCreditNote getCreditNoteDetailsByUserId(int userId, Long id) {
		HotelCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from HotelCreditNote cn where cn.userId=:user_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("user_id", String.valueOf(userId));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(HotelCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}
	 
	public HotelOrderRowCancellation getUpdateHotelOrderRowCancellation(String orderId) throws HibernateException, Exception {
		Session sess = null;		
		HotelOrderRowCancellation hotelOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(HotelOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			hotelOrderRowCancellationDb = (HotelOrderRowCancellation) crit.uniqueResult();
			sess.close();
		}catch (HibernateException e) {			
			logger.info("Exceeption----HibernateException--"+e.getMessage());
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close(); 
		}
		return hotelOrderRowCancellationDb;
	}


	public HotelOrderRowCancellation insertOrUpdateHotelOrderRowCancellation(HotelOrderRowCancellation hotelOrderRowCancellation) throws HibernateException, Exception {
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(HotelOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", hotelOrderRowCancellation.getOrderId()));			
			HotelOrderRowCancellation hotelOrderRowCancellationDb = (HotelOrderRowCancellation) crit.uniqueResult();
			Timestamp updated_at = new Timestamp(new Date().getTime());				
			if(hotelOrderRowCancellationDb != null)
			{
				hotelOrderRowCancellationDb.setAPIStatusCode(hotelOrderRowCancellation.getAPIStatusCode());
				hotelOrderRowCancellationDb.setAPIStatusMessage(hotelOrderRowCancellation.getAPIStatusMessage());
				hotelOrderRowCancellationDb.setAPIConfirmationNumber(hotelOrderRowCancellation.getAPIConfirmationNumber());
				hotelOrderRowCancellationDb.setAPIChargeType(hotelOrderRowCancellation.getAPIChargeType());
				hotelOrderRowCancellationDb.setAPIChargeAmount(hotelOrderRowCancellation.getAPIChargeAmount());
				hotelOrderRowCancellationDb.setAPICurrency(hotelOrderRowCancellation.getAPICurrency());
				hotelOrderRowCancellationDb.setAPIPaymentType(hotelOrderRowCancellation.getAPIPaymentType());
				hotelOrderRowCancellationDb.setAPIReference(hotelOrderRowCancellation.getAPIReference());
				hotelOrderRowCancellationDb.setAPIRefundAmount(hotelOrderRowCancellation.getAPIRefundAmount());
				hotelOrderRowCancellationDb.setStatusCode(hotelOrderRowCancellation.getStatusCode());
				hotelOrderRowCancellationDb.setStatusMessage(hotelOrderRowCancellation.getStatusMessage());				
				hotelOrderRowCancellationDb.setUpdatedAt(updated_at);				
				tx = sess.beginTransaction();
				sess.update(hotelOrderRowCancellationDb);
				tx.commit();
				hotelOrderRowCancellation = hotelOrderRowCancellationDb;
				logger.info("hotelOrderRowCancellation updated successfully-");
			}
			else
			{
				tx = sess.beginTransaction();
				hotelOrderRowCancellation.setCreatedAt(updated_at);				
				sess.saveOrUpdate(hotelOrderRowCancellation);
				tx.commit();
				logger.info("hotelOrderRowCancellation inserted successfully-");
			}
			sess.close();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			logger.info("Exceeption----HibernateException--"+e.getMessage());
		}
		finally
		{
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}		
		return hotelOrderRowCancellation;
	}

}


