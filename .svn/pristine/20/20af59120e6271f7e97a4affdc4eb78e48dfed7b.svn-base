/**
 * 
 */
package com.admin.miscellaneous.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.miscellaneous.model.MiscellaneousOrderRowCancellation;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.config.HibernateUtil;

/**
 * @author BASHA
 *
 */

public class MiscellaneousCreditNoteDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MiscellaneousCreditNoteDao.class);
	

	public MiscellaneousOrderRow getMiscellaneousOrderRowDataForCreditNote(Long id) {
		MiscellaneousOrderRow miscellaneousOrderRow=null;
		Session session = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from MiscellaneousOrderRow hr where hr.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			miscellaneousOrderRow = (MiscellaneousOrderRow) query.uniqueResult();
		}
		catch (HibernateException e) {
		}finally {
			session.close(); 
		}
		return miscellaneousOrderRow;
	}
	public List<MiscellaneousCreditNote> getCreditNoteListByOrderRowID(Long id) {
		// TODO Auto-generated method stub
		Session session = null;

		List<MiscellaneousCreditNote> creditNoteList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from MiscellaneousCreditNote hcn where hcn.rowId=:row_id order by hcn.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("row_id", id.intValue());
			creditNoteList = query.list();
		}
		catch (HibernateException e) {
		}finally {
			session.close(); 
		}
		return creditNoteList;
	}
	
	public MiscellaneousCreditNote getCreditNoteDetailsByComapnyId(int companyid, Long id) {
		MiscellaneousCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(MiscellaneousCreditNote.class);			
			crit.add(Restrictions.eq("companyId", String.valueOf(companyid)));
			crit.add(Restrictions.eq("rowId", id.intValue()));	
			crit.add(Restrictions.eq("afterStatus", "Cancelled"));	
			crit.add(Restrictions.eq("afterPayStatus","Refund"));	
			creditNoteObj =(MiscellaneousCreditNote) crit.uniqueResult();
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}
	
	
	public MiscellaneousCreditNote getCreditNoteDetailsByUserId(int userId, Long id) {
		MiscellaneousCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(MiscellaneousCreditNote.class);			
			crit.add(Restrictions.eq("userId", String.valueOf(userId)));
			crit.add(Restrictions.eq("rowId", id.intValue()));	
			crit.add(Restrictions.eq("afterStatus", "Cancelled"));	
			crit.add(Restrictions.eq("afterPayStatus","Refund"));	
			creditNoteObj =(MiscellaneousCreditNote) crit.uniqueResult();
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}
	public MiscellaneousCreditNote updateRefundingAmount(MiscellaneousCreditNote creditNote) {
		MiscellaneousCreditNote updateCreditNote   = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCreditNote =  (MiscellaneousCreditNote) session.get(MiscellaneousCreditNote.class,creditNote.getId());
			updateCreditNote.setRefundedAmount(creditNote.getRefundedAmount());
			updateCreditNote.setTotalBookingAmount(creditNote.getTotalBookingAmount());
			updateCreditNote.setCreditnoteIssued(creditNote.isCreditnoteIssued());
			updateCreditNote.setOrderUpdated(true);
			updateCreditNote.setIssuedAt(new Timestamp(new Date().getTime()));
			session.save(updateCreditNote);
			transaction.commit();
		}
		catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
		}finally{
			session.close();
		}
		return updateCreditNote;

	}
	
	public MiscellaneousOrderRow updateCreditNoteIssuedInMiscellaneousOrderRow(Long id,boolean setCreditNoteIssued) {
		Session session = null;
		Transaction transaction = null;
		MiscellaneousOrderRow updateMiscellaneousOrderRow   = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateMiscellaneousOrderRow =  (MiscellaneousOrderRow) session.get(MiscellaneousOrderRow.class,id);
			updateMiscellaneousOrderRow.setCreditNoteIssued(setCreditNoteIssued);
			session.save(updateMiscellaneousOrderRow);
			transaction.commit();

		}
		catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}

		}finally{
			session.close();
		}
		return updateMiscellaneousOrderRow;
	}
	public MiscellaneousOrderRowCancellation getMiscellaneousOrderRowCancellation(String orderId)  throws HibernateException, Exception {
		Session sess = null;		
		MiscellaneousOrderRowCancellation orderRowCancellation = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(MiscellaneousOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			orderRowCancellation = (MiscellaneousOrderRowCancellation) crit.uniqueResult();
			sess.close();
		}catch (HibernateException e) {			
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close(); 
		}
		return orderRowCancellation;
	}
	public MiscellaneousOrderRowCancellation getUpdateMiscellaneousOrderRowCancellation(String orderId) throws HibernateException, Exception {
		Session sess = null;		
		MiscellaneousOrderRowCancellation miscellaneousOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(MiscellaneousOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			miscellaneousOrderRowCancellationDb = (MiscellaneousOrderRowCancellation) crit.uniqueResult();
			sess.close();
		}catch (HibernateException e) {			
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close(); 
		 }
		return miscellaneousOrderRowCancellationDb;
	}
	public MiscellaneousOrderRowCancellation insertOrUpdateMiscellaneousOrderRowCancellation(MiscellaneousOrderRowCancellation miscellaneousOrderRowCancellation) throws HibernateException, Exception {
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(MiscellaneousOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", miscellaneousOrderRowCancellation.getOrderId()));			
			MiscellaneousOrderRowCancellation miscellaneousOrderRowCancellationDb = (MiscellaneousOrderRowCancellation) crit.uniqueResult();
			Timestamp updated_at = new Timestamp(new Date().getTime());				
			if(miscellaneousOrderRowCancellationDb != null)
			{
				miscellaneousOrderRowCancellationDb.setAPIStatusCode(miscellaneousOrderRowCancellation.getAPIStatusCode());
				miscellaneousOrderRowCancellationDb.setAPIStatusMessage(miscellaneousOrderRowCancellation.getAPIStatusMessage());
				miscellaneousOrderRowCancellationDb.setAPIConfirmationNumber(miscellaneousOrderRowCancellation.getAPIConfirmationNumber());
				miscellaneousOrderRowCancellationDb.setAPIChargeType(miscellaneousOrderRowCancellation.getAPIChargeType());
				miscellaneousOrderRowCancellationDb.setAPIChargeAmount(miscellaneousOrderRowCancellation.getAPIChargeAmount());
				miscellaneousOrderRowCancellationDb.setAPICurrency(miscellaneousOrderRowCancellation.getAPICurrency());
				miscellaneousOrderRowCancellationDb.setAPIPaymentType(miscellaneousOrderRowCancellation.getAPIPaymentType());
				miscellaneousOrderRowCancellationDb.setAPIReference(miscellaneousOrderRowCancellation.getAPIReference());
				miscellaneousOrderRowCancellationDb.setAPIRefundAmount(miscellaneousOrderRowCancellation.getAPIRefundAmount());
				miscellaneousOrderRowCancellationDb.setStatusCode(miscellaneousOrderRowCancellation.getStatusCode());
				miscellaneousOrderRowCancellationDb.setStatusMessage(miscellaneousOrderRowCancellation.getStatusMessage());				
				miscellaneousOrderRowCancellationDb.setUpdatedAt(updated_at);				
				tx = sess.beginTransaction();
				sess.update(miscellaneousOrderRowCancellationDb);
				tx.commit();
				miscellaneousOrderRowCancellation = miscellaneousOrderRowCancellationDb;
			}
			else
			{
				tx = sess.beginTransaction();
				miscellaneousOrderRowCancellation.setCreatedAt(updated_at);				
				sess.saveOrUpdate(miscellaneousOrderRowCancellation);
				tx.commit();
			}
			sess.close();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
		}
		finally
		{
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}		
		return miscellaneousOrderRowCancellation;
	}
	
	
	
}
