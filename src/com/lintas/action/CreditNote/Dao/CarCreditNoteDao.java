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

import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.admin.car.model.CarCommissionReport;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.CarOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class CarCreditNoteDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CarCreditNoteDao.class);
	/*load all FlightOrderRow data for Invoice*/
	public  List<CarOrderRow> getCarAgentCreditNoteList(Company sessionCompany){
		Session session = null;
		List<CarOrderRow> hotelOrderList=new ArrayList<CarOrderRow>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarOrderRow hr where hr.companyId=:company_id order by hr.id desc";
			logger.info("-----filterQuery------------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("company_id",String.valueOf(sessionCompany.getCompanyid()));
			List<CarOrderRow> list=query.list();
			for(CarOrderRow invoiceData:list){
				invoiceData.setTotalAmount(invoiceData.getTotalAmount().add(invoiceData.getServiceTax()).setScale(2, BigDecimal.ROUND_UP));
				invoiceData.setTravelDate(DateConversion.convertDateToStringToDateWithTIME(invoiceData.getTravelDate()));
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
	
	public CarOrderRow getCarOrderRowDataForCreditNote(Long id) {
		// TODO Auto-generated method stub
		CarOrderRow hotelOrderRow=null;
		Session session = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarOrderRow hr where hr.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			hotelOrderRow = (CarOrderRow) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRow;
	}

	public CarCreditNote updateRefundingAmount(CarCreditNote creditNote) {
		// TODO Auto-generated method stub
		CarCreditNote updateCreditNote   = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCreditNote =  (CarCreditNote) session.get(CarCreditNote.class,creditNote.getId());
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



	public CarOrderRow updateCreditNoteIssuedInCarOrderRow(Long id,boolean setCreditNoteIssued) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		CarOrderRow updateCarOrderRow   = null;
		/*logger.info("updateUserWallet getWalletbalance------------------"+userWallet.getWalletbalance());
		logger.info("updateUserWallet getWalletId------------------"+userWallet.getWalletId());*/
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCarOrderRow =  (CarOrderRow) session.get(CarOrderRow.class,id);
			updateCarOrderRow.setCreditNoteIssued(setCreditNoteIssued);
			session.save(updateCarOrderRow);
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
		return updateCarOrderRow;
	}



	public List<CarCreditNote> getCreditNoteListByOrderRowID(Long id) {
		// TODO Auto-generated method stub
		Session session = null;

		List<CarCreditNote> creditNoteList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarCreditNote hcn where hcn.rowId=:row_id order by hcn.id desc";
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



	public CarCreditNote getCreditNoteDetailsByComapnyId(int companyid, Long id) {
		CarCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarCreditNote cn where cn.companyId=:company_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(companyid));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(CarCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}
	
	public CarCreditNote getCreditNoteDetailsByUserId(int userId, Long id) {
		CarCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CarCreditNote cn where cn.userId=:user_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("user_id", String.valueOf(userId));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(CarCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}




	public CarOrderRowCancellation getUpdateCarOrderRowCancellation(String orderId) throws HibernateException, Exception {
		Session sess = null;		
		CarOrderRowCancellation hotelOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(CarOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			hotelOrderRowCancellationDb = (CarOrderRowCancellation) crit.uniqueResult();
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


	public CarOrderRowCancellation insertOrUpdateCarOrderRowCancellation(CarOrderRowCancellation hotelOrderRowCancellation) throws HibernateException, Exception {
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(CarOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", hotelOrderRowCancellation.getOrderId()));			
			CarOrderRowCancellation hotelOrderRowCancellationDb = (CarOrderRowCancellation) crit.uniqueResult();
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
	public CarOrderRowCancellation getCarOrderRowCancellation(String orderId)  throws HibernateException, Exception {
		Session sess = null;		
		CarOrderRowCancellation busOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(CarOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			busOrderRowCancellationDb = (CarOrderRowCancellation) crit.uniqueResult();
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


