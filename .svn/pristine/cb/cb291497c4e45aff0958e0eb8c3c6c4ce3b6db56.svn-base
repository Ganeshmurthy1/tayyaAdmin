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

import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.admin.common.model.TrainOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainCommissionReport;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class TrainCreditNoteDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainCreditNoteDao.class);
	/*load all FlightOrderRow data for Invoice*/
	public  List<TrainOrderRow> getTrainAgentCreditNoteList(Company sessionCompany){
		Session session = null;
		List<TrainOrderRow> trainOrderList=new ArrayList<TrainOrderRow>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainOrderRow hr where hr.companyId=:company_id order by hr.id desc";
			logger.info("-----filterQuery------------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("company_id",String.valueOf(sessionCompany.getCompanyid()));
			List<TrainOrderRow> list=query.list();
			for(TrainOrderRow invoiceData:list){
				invoiceData.setTotalAmount(invoiceData.getTotalAmount().add(invoiceData.getServiceTax()).setScale(2, BigDecimal.ROUND_UP));
				trainOrderList.add(invoiceData);
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
		return trainOrderList;
	}

	public  List<TrainCommissionReport> getAgentCreditNoteList(Company sessionCompany){
		List<TrainCommissionReport> trainCommissionReports=new ArrayList<TrainCommissionReport>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainOrderRow fr where fr.companyId=:company_id order by fr.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(sessionCompany.getCompanyid()));
			List<TrainOrderRow> list = query.list();
			if(list!=null && list.size()>0)
			{
				for (TrainOrderRow trainOrderRow:list){
					TrainCommissionReport commissionReport=new TrainCommissionReport();
					BigDecimal finalPriceInBaseCurrency=trainOrderRow.getTotalAmount().divide(trainOrderRow.getBaseToBookingExchangeRate());
					commissionReport.setFinalPrice(finalPriceInBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
					commissionReport.setOrderId(trainOrderRow.getOrderId());
					commissionReport.setOrderId(trainOrderRow.getOrderId());
					commissionReport.setCurCode(trainOrderRow.getBookingCurrency());
					commissionReport.setStatus(trainOrderRow.getStatusAction());
					commissionReport.setPaymentStatus(trainOrderRow.getPaymentStatus());
					commissionReport.setCreatedBy(trainOrderRow.getCreatedBy());
					commissionReport.setBookingDate(trainOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDate(trainOrderRow.getBookingDate()):"-");
					commissionReport.setUserId(trainOrderRow.getUserId());
					commissionReport.setId(trainOrderRow.getId());
					trainCommissionReports.add(commissionReport);
				}
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return trainCommissionReports;
	}
	public TrainOrderRow getTrainOrderRowDataForCreditNote(Long id) {
		// TODO Auto-generated method stub
		TrainOrderRow hotelOrderRow=null;
		Session session = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainOrderRow hr where hr.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			hotelOrderRow = (TrainOrderRow) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRow;
	}
	 
	public TrainCreditNote updateRefundingAmount(TrainCreditNote creditNote) {
		// TODO Auto-generated method stub
		TrainCreditNote updateCreditNote   = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCreditNote =  (TrainCreditNote) session.get(TrainCreditNote.class,creditNote.getId());
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
	 
	 
	public TrainOrderRow updateCreditNoteIssuedInTrainOrderRow(Long id,boolean setCreditNoteIssued) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		TrainOrderRow updateTrainOrderRow   = null;
		/*logger.info("updateUserWallet getWalletbalance------------------"+userWallet.getWalletbalance());
		logger.info("updateUserWallet getWalletId------------------"+userWallet.getWalletId());*/
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateTrainOrderRow =  (TrainOrderRow) session.get(TrainOrderRow.class,id);
			updateTrainOrderRow.setCreditNoteIssued(setCreditNoteIssued);
			session.save(updateTrainOrderRow);
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
		return updateTrainOrderRow;
	}

	 
	public List<TrainCreditNote> getCreditNoteListByOrderRowID(Long id) {
		// TODO Auto-generated method stub
		Session session = null;

		List<TrainCreditNote> creditNoteList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainCreditNote hcn where hcn.rowId=:row_id order by hcn.id desc";
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
	 
	 

	public TrainCreditNote getCreditNoteDetailsByComapnyId(int companyid, Long id) {
		TrainCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainCreditNote cn where cn.companyId=:company_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(companyid));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(TrainCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}

	public TrainCreditNote getCreditNoteDetailsByUserId(int userId, Long id) {
		TrainCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from TrainCreditNote cn where cn.userId=:user_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("user_id", String.valueOf(userId));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(TrainCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}

	
	public TrainOrderRowCancellation getUpdateTrainOrderRowCancellation(String orderId) throws HibernateException, Exception {
		Session sess = null;		
		TrainOrderRowCancellation trainOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(TrainOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			trainOrderRowCancellationDb = (TrainOrderRowCancellation) crit.uniqueResult();
			sess.close();
		}catch (HibernateException e) {			
			logger.info("Exceeption----HibernateException--"+e.getMessage());
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close(); 
		 }
		return trainOrderRowCancellationDb;
	}
	

	public TrainOrderRowCancellation insertOrUpdateTrainOrderRowCancellation(TrainOrderRowCancellation trainOrderRowCancellation) throws HibernateException, Exception {
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(TrainOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", trainOrderRowCancellation.getOrderId()));			
			TrainOrderRowCancellation trainOrderRowCancellationDb = (TrainOrderRowCancellation) crit.uniqueResult();
			Timestamp updated_at = new Timestamp(new Date().getTime());				
			if(trainOrderRowCancellationDb != null)
			{
				trainOrderRowCancellationDb.setAPIStatusCode(trainOrderRowCancellation.getAPIStatusCode());
				trainOrderRowCancellationDb.setAPIStatusMessage(trainOrderRowCancellation.getAPIStatusMessage());
				trainOrderRowCancellationDb.setAPIConfirmationNumber(trainOrderRowCancellation.getAPIConfirmationNumber());
				trainOrderRowCancellationDb.setAPIChargeType(trainOrderRowCancellation.getAPIChargeType());
				trainOrderRowCancellationDb.setAPIChargeAmount(trainOrderRowCancellation.getAPIChargeAmount());
				trainOrderRowCancellationDb.setAPICurrency(trainOrderRowCancellation.getAPICurrency());
				trainOrderRowCancellationDb.setAPIPaymentType(trainOrderRowCancellation.getAPIPaymentType());
				trainOrderRowCancellationDb.setAPIReference(trainOrderRowCancellation.getAPIReference());
				trainOrderRowCancellationDb.setAPIRefundAmount(trainOrderRowCancellation.getAPIRefundAmount());
				trainOrderRowCancellationDb.setStatusCode(trainOrderRowCancellation.getStatusCode());
				trainOrderRowCancellationDb.setStatusMessage(trainOrderRowCancellation.getStatusMessage());				
				trainOrderRowCancellationDb.setUpdatedAt(updated_at);				
				tx = sess.beginTransaction();
				sess.update(trainOrderRowCancellationDb);
				tx.commit();
				trainOrderRowCancellation = trainOrderRowCancellationDb;
				logger.info("hotelOrderRowCancellation updated successfully-");
			}
			else
			{
				tx = sess.beginTransaction();
				trainOrderRowCancellation.setCreatedAt(updated_at);				
				sess.saveOrUpdate(trainOrderRowCancellation);
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
		return trainOrderRowCancellation;
	}
	public TrainOrderRowCancellation getTrainOrderRowCancellation(String orderId)  throws HibernateException, Exception {
		Session sess = null;		
		TrainOrderRowCancellation trainOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(TrainOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			trainOrderRowCancellationDb = (TrainOrderRowCancellation) crit.uniqueResult();
			sess.close();
		}catch (HibernateException e) {			
			logger.info("Exceeption----HibernateException--"+e.getMessage());
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close(); 
		}
		return trainOrderRowCancellationDb;
	}
 
}


