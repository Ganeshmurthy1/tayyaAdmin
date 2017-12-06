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

import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.admin.common.model.InsuranceOrderRowCancellation;
import com.lintas.admin.insurance.model.InsuranceCommissionReport;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class InsuranceCreditNoteDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InsuranceCreditNoteDao.class);
	/*load all FlightOrderRow data for Invoice*/
	public  List<InsuranceOrderRow> getInsuranceAgentCreditNoteList(Company sessionCompany){
		Session session = null;
		List<InsuranceOrderRow> hotelOrderList=new ArrayList<InsuranceOrderRow>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceOrderRow hr where hr.companyId=:company_id order by hr.id desc";
			logger.info("-----filterQuery------------------"+sql);
			Query query = session.createQuery(sql);
			query.setParameter("company_id",String.valueOf(sessionCompany.getCompanyid()));
			List<InsuranceOrderRow> list=query.list();
			for(InsuranceOrderRow invoiceData:list){
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
	
	public  List<InsuranceCommissionReport> getAgentCreditNoteList(Company sessionCompany){
		List<InsuranceCommissionReport> busCommissionReports=new ArrayList<InsuranceCommissionReport>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceOrderRow fr where fr.companyId=:company_id order by fr.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(sessionCompany.getCompanyid()));
			List<InsuranceOrderRow> list = query.list();
			if(list!=null && list.size()>0)
			{
				for (InsuranceOrderRow busOrderRow:list){
					InsuranceCommissionReport commissionReport=new InsuranceCommissionReport();
					BigDecimal finalPriceInBaseCurrency=busOrderRow.getTotalAmount().divide(busOrderRow.getBaseToBookingExchangeRate());
					commissionReport.setFinalPrice(finalPriceInBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
					commissionReport.setOrderId(busOrderRow.getOrderId());
					commissionReport.setBookingDate(DateConversion.convertDateToStringDate(busOrderRow.getBookingDate()) );
					commissionReport.setOrderId(busOrderRow.getOrderId());
					commissionReport.setCurCode(busOrderRow.getBookingCurrency());
					commissionReport.setStatus(busOrderRow.getStatusAction());
					commissionReport.setPaymentStatus(busOrderRow.getPaymentStatus());
					commissionReport.setCreatedBy(busOrderRow.getCreatedBy());
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

	public InsuranceOrderRow getInsuranceOrderRowDataForCreditNote(Long id) {
		// TODO Auto-generated method stub
		InsuranceOrderRow hotelOrderRow=null;
		Session session = null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceOrderRow hr where hr.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			hotelOrderRow = (InsuranceOrderRow) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return hotelOrderRow;
	}
	 
	public InsuranceCreditNote updateRefundingAmount(InsuranceCreditNote creditNote) {
		// TODO Auto-generated method stub
		InsuranceCreditNote updateCreditNote   = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCreditNote =  (InsuranceCreditNote) session.get(InsuranceCreditNote.class,creditNote.getId());
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
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			session.close();
		}
		return updateCreditNote;

	}
	 
	public InsuranceOrderRow updateCreditNoteIssuedInInsuranceOrderRow(Long id,boolean setCreditNoteIssued) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		InsuranceOrderRow updateInsuranceOrderRow   = null;
		/*logger.info("updateUserWallet getWalletbalance------------------"+userWallet.getWalletbalance());
		logger.info("updateUserWallet getWalletId------------------"+userWallet.getWalletId());*/
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateInsuranceOrderRow =  (InsuranceOrderRow) session.get(InsuranceOrderRow.class,id);
			updateInsuranceOrderRow.setCreditNoteIssued(setCreditNoteIssued);
			session.save(updateInsuranceOrderRow);
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
		return updateInsuranceOrderRow;
	}

	 

	public List<InsuranceCreditNote> getCreditNoteListByOrderRowID(Long id) {
		// TODO Auto-generated method stub
		Session session = null;

		List<InsuranceCreditNote> creditNoteList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceCreditNote hcn where hcn.rowId=:row_id order by hcn.id desc";
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
	 
	public InsuranceCreditNote getCreditNoteDetailsByComapnyId(int companyid, Long id) {
		InsuranceCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceCreditNote cn where cn.companyId=:company_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(companyid));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(InsuranceCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}
	
	public InsuranceCreditNote getCreditNoteDetailsByUserId(int userId, Long id) {
		InsuranceCreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from InsuranceCreditNote cn where cn.userId=:user_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("user_id", String.valueOf(userId));
			query.setParameter("row_id", id.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(InsuranceCreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}

	public Company getCompanyAddress(String companyId){
		Company company=null;
		Session session = null;
		try{
			if(companyId!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				String sql = "from Company c where c.companyid=:companyid";
				Query query = session.createQuery(sql);
				query.setParameter("companyid", Integer.parseInt(companyId));
				company =   (Company) query.uniqueResult();
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return company;
	}
	
	
	public InsuranceOrderRowCancellation getUpdateInsuranceOrderRowCancellation(String orderId) throws HibernateException, Exception {
		Session sess = null;		
		InsuranceOrderRowCancellation hotelOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(InsuranceOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			hotelOrderRowCancellationDb = (InsuranceOrderRowCancellation) crit.uniqueResult();
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
	

	public InsuranceOrderRowCancellation insertOrUpdateInsuranceOrderRowCancellation(InsuranceOrderRowCancellation hotelOrderRowCancellation) throws HibernateException, Exception {
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(InsuranceOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", hotelOrderRowCancellation.getOrderId()));			
			InsuranceOrderRowCancellation hotelOrderRowCancellationDb = (InsuranceOrderRowCancellation) crit.uniqueResult();
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
	public InsuranceOrderRowCancellation getInsuranceOrderRowCancellation(String orderId)  throws HibernateException, Exception {
		Session sess = null;		
		InsuranceOrderRowCancellation busOrderRowCancellationDb = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(InsuranceOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			busOrderRowCancellationDb = (InsuranceOrderRowCancellation) crit.uniqueResult();
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


