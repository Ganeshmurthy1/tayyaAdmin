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

import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.admin.bus.model.BusCommissionReport;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.common.model.BusOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class BusCreditNoteDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BusCreditNoteDao.class);
	/*load all BusOrderRow data for Invoice*/
	public  List<BusCommissionReport> getAgentCreditNoteList(Company sessionCompany){
		List<BusCommissionReport> busCommissionReports=new ArrayList<BusCommissionReport>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusOrderRow fr where fr.companyId=:company_id order by fr.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(sessionCompany.getCompanyid()));
			List<BusOrderRow> list = query.list();
			if(list!=null && list.size()>0)
			{
				for (BusOrderRow busOrderRow:list){
					BusCommissionReport commissionReport=new BusCommissionReport();
					BigDecimal finalPriceInBaseCurrency=busOrderRow.getTotalAmount().divide(busOrderRow.getBaseToBookingExchangeRate());
					commissionReport.setFinalPrice(finalPriceInBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
					commissionReport.setOrderId(busOrderRow.getOrderId());
					commissionReport.setBookingDate(busOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDate(busOrderRow.getBookingDate()):"-");
					commissionReport.setOrderId(busOrderRow.getOrderId());
					commissionReport.setCurCode(busOrderRow.getBookingCurrency());
					commissionReport.setStatus(busOrderRow.getStatusAction());
					commissionReport.setPaymentStatus(busOrderRow.getPaymentStatus());
					commissionReport.setCreatedBy(busOrderRow.getCreatedBy());
					commissionReport.setUserId(busOrderRow.getUserId());
					commissionReport.setId(busOrderRow.getId());
					busCommissionReports.add(commissionReport);
				}
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return busCommissionReports;
	}
	public BusOrderRow getBusOrderRowDataForCreditNote(Long id) {
		// TODO Auto-generated method stub
		BusOrderRow hotelOrderRow=null;
		Session session = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusOrderRow hr where hr.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			hotelOrderRow = (BusOrderRow) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRow;
	}
	 
	public BusCreditNote updateRefundingAmount(BusCreditNote creditNote) {
		// TODO Auto-generated method stub
		BusCreditNote updateCreditNote   = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCreditNote =  (BusCreditNote) session.get(BusCreditNote.class,creditNote.getId());
			updateCreditNote.setRefundedAmount(creditNote.getRefundedAmount());
			updateCreditNote.setOrderUpdated(true);
			updateCreditNote.setCreditnoteIssued(creditNote.isCreditnoteIssued());
			updateCreditNote.setIssuedAt(new Timestamp(new Date().getTime()));
			updateCreditNote.setTotalBookingAmount(creditNote.getTotalBookingAmount());
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
	 
	 
	public BusOrderRow updateCreditNoteIssuedInBusOrderRow(Long id,boolean setCreditNoteIssued) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		BusOrderRow updateBusOrderRow   = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateBusOrderRow =  (BusOrderRow) session.get(BusOrderRow.class,id);
			updateBusOrderRow.setCreditNoteIssued(setCreditNoteIssued);
			session.save(updateBusOrderRow);
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
		return updateBusOrderRow;
	}

	public List<BusCreditNote> getCreditNoteListByOrderRowID(Long id) {
		// TODO Auto-generated method stub
		Session session = null;

		List<BusCreditNote> creditNoteList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from BusCreditNote hcn where hcn.rowId=:row_id order by hcn.id desc";
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
	 

	public BusCreditNote getCreditNoteDetailsByComapnyId(int companyid, Long id) {
		BusCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(BusCreditNote.class);			
			crit.add(Restrictions.eq("companyId", String.valueOf(companyid)));
			crit.add(Restrictions.eq("rowId", id.intValue()));	
			crit.add(Restrictions.eq("afterStatus", "Cancelled"));	
			crit.add(Restrictions.eq("afterPayStatus","Refund"));	
			creditNoteObj =(BusCreditNote) crit.uniqueResult();
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}

	public BusCreditNote getCreditNoteDetailsByUserId(int userId, Long id) {
		BusCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(BusCreditNote.class);			
			crit.add(Restrictions.eq("userId", String.valueOf(userId)));
			crit.add(Restrictions.eq("rowId", id.intValue()));	
			crit.add(Restrictions.eq("afterStatus", "Cancelled"));	
			crit.add(Restrictions.eq("afterPayStatus","Refund"));	
			creditNoteObj =(BusCreditNote) crit.uniqueResult();
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}


	public BusOrderRowCancellation getUpdateBusOrderRowCancellation(String orderId) throws HibernateException, Exception {
		Session sess = null;		
		BusOrderRowCancellation hotelOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(BusOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			hotelOrderRowCancellationDb = (BusOrderRowCancellation) crit.uniqueResult();
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


	public BusOrderRowCancellation insertOrUpdateBusOrderRowCancellation(BusOrderRowCancellation hotelOrderRowCancellation) throws HibernateException, Exception {
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(BusOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", hotelOrderRowCancellation.getOrderId()));			
			BusOrderRowCancellation hotelOrderRowCancellationDb = (BusOrderRowCancellation) crit.uniqueResult();
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

	public BusOrderRowCancellation getBusOrderRowCancellation(String orderId)  throws HibernateException, Exception {
		Session sess = null;		
		BusOrderRowCancellation busOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(BusOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			busOrderRowCancellationDb = (BusOrderRowCancellation) crit.uniqueResult();
			sess.close();
		}catch (HibernateException e) {			
			logger.info("Exceeption----HibernateException--"+e.getMessage());
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close(); 
		}
		return busOrderRowCancellationDb;
	}


}


