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

import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.admin.common.model.VisaOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.admin.visa.model.VisaCommissionReport;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class VisaCreditNoteDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(VisaCreditNoteDao.class);
	/*load all FlightOrderRow data for Invoice*/
	public  List<VisaOrderRow> getVisaAgentCreditNoteList(Company sessionCompany){
		Session session = null;
		List<VisaOrderRow> hotelOrderList=new ArrayList<VisaOrderRow>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaOrderRow hr where hr.companyId=:company_id order by hr.id desc";
			logger.info("-----filterQuery------------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("company_id",String.valueOf(sessionCompany.getCompanyid()));
			List<VisaOrderRow> list=query.list();
			for(VisaOrderRow invoiceData:list){
				invoiceData.setTotalAmount(invoiceData.getTotalAmount().add(invoiceData.getServiceTax()).setScale(2, BigDecimal.ROUND_UP));
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

	public  List<VisaCommissionReport> getAgentCreditNoteList(Company sessionCompany){
		List<VisaCommissionReport> busCommissionReports=new ArrayList<VisaCommissionReport>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaOrderRow fr where fr.companyId=:company_id order by fr.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(sessionCompany.getCompanyid()));
			List<VisaOrderRow> list = query.list();
			if(list!=null && list.size()>0)
			{
				for (VisaOrderRow orderRow:list){
					VisaCommissionReport commissionReport=new VisaCommissionReport();
					BigDecimal finalPriceInBaseCurrency=orderRow.getTotalAmount().divide(orderRow.getBaseToBookingExchangeRate());
					commissionReport.setFinalPrice(finalPriceInBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
					commissionReport.setOrderId(orderRow.getOrderId());
					commissionReport.setOrderId(orderRow.getOrderId());
					commissionReport.setCurCode(orderRow.getBookingCurrency());
					commissionReport.setStatus(orderRow.getStatusAction());
					commissionReport.setPaymentStatus(orderRow.getPaymentStatus());
					commissionReport.setCreatedBy(orderRow.getCreatedBy());
					commissionReport.setBookingDate(DateConversion.convertDateToStringDatewirhDDMonthYear(orderRow.getBookingDate()));
					commissionReport.setUserId(orderRow.getUserId());
					commissionReport.setId(orderRow.getId());
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
	public VisaOrderRow getVisaOrderRowDataForCreditNote(Long id) {
		// TODO Auto-generated method stub
		VisaOrderRow hotelOrderRow=null;
		Session session = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaOrderRow hr where hr.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			hotelOrderRow = (VisaOrderRow) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRow;
	}
	 
	public VisaCreditNote updateRefundingAmount(VisaCreditNote creditNote) {
		// TODO Auto-generated method stub
		VisaCreditNote updateCreditNote   = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCreditNote =  (VisaCreditNote) session.get(VisaCreditNote.class,creditNote.getId());
			updateCreditNote.setRefundedAmount(creditNote.getRefundedAmount());
			updateCreditNote.setTotalBookingAmount(creditNote.getTotalBookingAmount());
			updateCreditNote.setCreditnoteIssued(creditNote.isCreditnoteIssued());
			updateCreditNote.setOrderUpdated(true);
			updateCreditNote.setCreditnoteIssued(true);
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
	 
	public VisaOrderRow updateCreditNoteIssuedInVisaOrderRow(Long id,boolean setCreditNoteIssued) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		VisaOrderRow updateVisaOrderRow   = null;
		/*logger.info("updateUserWallet getWalletbalance------------------"+userWallet.getWalletbalance());
		logger.info("updateUserWallet getWalletId------------------"+userWallet.getWalletId());*/
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateVisaOrderRow =  (VisaOrderRow) session.get(VisaOrderRow.class,id);
			updateVisaOrderRow.setCreditNoteIssued(setCreditNoteIssued);
			session.save(updateVisaOrderRow);
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
		return updateVisaOrderRow;
	}

	public List<VisaCreditNote> getCreditNoteListByOrderRowID(Long id) {
		// TODO Auto-generated method stub
		Session session = null;

		List<VisaCreditNote> creditNoteList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaCreditNote hcn where hcn.rowId=:row_id order by hcn.id desc";
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

	public VisaCreditNote getCreditNoteDetailsByComapnyId(int companyid, Long id) {
		VisaCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaCreditNote cn where cn.companyId=:company_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(companyid));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(VisaCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}
 
	public VisaCreditNote getCreditNoteDetailsByUserId(int userId, Long id) {
		VisaCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from VisaCreditNote cn where cn.userId=:user_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("user_id", String.valueOf(userId));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(VisaCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}
	
	public VisaOrderRowCancellation getUpdateVisaOrderRowCancellation(String orderId) throws HibernateException, Exception {
		Session sess = null;		
		VisaOrderRowCancellation hotelOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(VisaOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			hotelOrderRowCancellationDb = (VisaOrderRowCancellation) crit.uniqueResult();
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
	

	public VisaOrderRowCancellation insertOrUpdateVisaOrderRowCancellation(VisaOrderRowCancellation hotelOrderRowCancellation) throws HibernateException, Exception {
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(VisaOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", hotelOrderRowCancellation.getOrderId()));			
			VisaOrderRowCancellation hotelOrderRowCancellationDb = (VisaOrderRowCancellation) crit.uniqueResult();
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
	
	public VisaOrderRowCancellation getVisaOrderRowCancellation(String orderId)  throws HibernateException, Exception {
		Session sess = null;		
		VisaOrderRowCancellation busOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(VisaOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			busOrderRowCancellationDb = (VisaOrderRowCancellation) crit.uniqueResult();
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


