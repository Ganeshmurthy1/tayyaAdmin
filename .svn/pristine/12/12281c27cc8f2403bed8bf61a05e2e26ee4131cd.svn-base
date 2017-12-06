package com.admin.miscellaneous.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.miscellaneous.model.MiscellaneousTravelRequest;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;
import com.tayyarah.miscellaneous.model.MiscellaneousOrderCustomer;
import com.tayyarah.visa.model.VisaOrderCustomer;

public class MiscellaneousOrderDao {

	
	public MiscellaneousOrderRow insertMiscellaneousOrderRow(MiscellaneousOrderRow miscellaneousOrderRow) {
		Session  session=null;
		Transaction  tx=null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(miscellaneousOrderRow);
			tx.commit();
		}
		catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
			
//			logger.error("HibernateException---------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return miscellaneousOrderRow;

	}
	
	public MiscellaneousOrderRow getMiscellaneousOrderRowByConfirmationNumber(String confirmationNumber) {
		Session  session=null;
		MiscellaneousOrderRow miscellaneousOrderRow=new MiscellaneousOrderRow();
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(MiscellaneousOrderRow.class);
			criteria.add(Restrictions.eq("confirmationNumber", confirmationNumber));
			miscellaneousOrderRow=(MiscellaneousOrderRow) criteria.uniqueResult();
		}
		catch (HibernateException e) {
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return miscellaneousOrderRow;
	}
	
	public MiscellaneousTravelRequest getMiscellaneousTravelRequestByConfirmationNumber(String confirmationNumber) {
		Session  session=null;
		MiscellaneousTravelRequest miscellaneousTravelRequest=new MiscellaneousTravelRequest();
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(MiscellaneousTravelRequest.class);
			criteria.add(Restrictions.eq("confirmationNumber", confirmationNumber));
			miscellaneousTravelRequest=(MiscellaneousTravelRequest) criteria.uniqueResult();
		}
		catch (HibernateException e) {
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return miscellaneousTravelRequest;
	}
	
	public List<WalletAmountTranferHistory> getMiscellaneousOrderPaymentInfo(String orderId,int userId) {
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
//			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return payTxList;
	}
	
	public MiscellaneousOrderRow getMiscellaneousOrderRowById(long id) {
		Session  session=null;
		MiscellaneousOrderRow miscellaneousOrderRow=new MiscellaneousOrderRow();
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(MiscellaneousOrderRow.class);
			criteria.add(Restrictions.eq("id", id));
			miscellaneousOrderRow=(MiscellaneousOrderRow) criteria.uniqueResult();
		}
		catch (HibernateException e) {
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return miscellaneousOrderRow;
	}
	public MiscellaneousOrderRow getMiscellaneousOrderRowDetail(String orderId) {
		/*this method for get  FlightOrderRow  using order id */
		MiscellaneousOrderRow miscellaneousOrderRow = null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(MiscellaneousOrderRow.class);
			criteria.add(Restrictions.eq("orderId", orderId));
			miscellaneousOrderRow = (MiscellaneousOrderRow) criteria.uniqueResult();
			}catch (HibernateException e) {
				  e.getMessage();
			}finally {
				if(session!=null && session.isOpen())
					session.close(); 
			}
			return miscellaneousOrderRow;
		}
	public MiscellaneousTravelRequest getMiscellaneousTravelRequestById(long id) {
		Session  session=null;
		MiscellaneousTravelRequest miscellaneousTravelRequest=new MiscellaneousTravelRequest();
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(MiscellaneousTravelRequest.class);
			criteria.add(Restrictions.eq("id", id));
			miscellaneousTravelRequest=(MiscellaneousTravelRequest) criteria.uniqueResult();
		}
		catch (HibernateException e) {
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return miscellaneousTravelRequest;
	}
	
	public MiscellaneousTravelRequest insertMiscellaneousTravelRequestnew(MiscellaneousTravelRequest miscellaneousTravelRequest){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(miscellaneousTravelRequest);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
//			logger.error("Exception---------"+e.getMessage());

		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return miscellaneousTravelRequest;

	}
	public MiscellaneousOrderRow updateMiscellaneousOrderRowDetail(MiscellaneousOrderRow orderRow){
		MiscellaneousOrderRow orderRowUpdate = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			orderRowUpdate=(MiscellaneousOrderRow) session.get(MiscellaneousOrderRow.class,orderRow.getId());
			orderRowUpdate.setInvoiceNo(orderRow.getInvoiceNo());
			orderRowUpdate.setOrderId(orderRow.getOrderId());
			orderRowUpdate.getOrderCustomer().setOrderId(orderRow.getOrderId());//added by basha
			session.update(orderRowUpdate);
			tx.commit();
		}catch (HibernateException e) {
//			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  orderRowUpdate;
	}
	
	public MiscellaneousOrderRow updateMiscellaneousOrderRowDetailPaymentStatus(MiscellaneousOrderRow miscellaneousOrderRow){
		MiscellaneousOrderRow orderRowUpdate = null;
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			orderRowUpdate=(MiscellaneousOrderRow) session.get(MiscellaneousOrderRow.class,miscellaneousOrderRow.getId());
			orderRowUpdate.setPaymentStatus(miscellaneousOrderRow.getPaymentStatus());
			session.update(orderRowUpdate);
			tx.commit();
		}catch (HibernateException e) {
//			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  orderRowUpdate;
	}
	
	public List<MiscellaneousTravelRequest>  loadMiscellaneousTravelRequestList(User user){
		Session session= null;
		List<MiscellaneousTravelRequest>  newList= new ArrayList<MiscellaneousTravelRequest>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(MiscellaneousTravelRequest.class);
			criteria.add(Restrictions.eq("companyId", user.getCompanyid()));
			newList= criteria.list();
		} catch (Exception e) {
//			logger.error("Exception---------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

		return newList;

	}
	public Boolean updateMiscellaneousOrderRowDetailFromEditPage(MiscellaneousOrderRow orderRow){
		MiscellaneousOrderRow orderRowUpdate = null;
		Session session=null;
		Transaction tx=null;
		Boolean status=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			orderRowUpdate=(MiscellaneousOrderRow) session.get(MiscellaneousOrderRow.class,orderRow.getId());
			orderRowUpdate.setConfirmationNumber(orderRow.getConfirmationNumber());
			orderRowUpdate.setSupplierName(orderRow.getSupplierName());
			orderRowUpdate.setStatusAction(orderRow.getStatusAction());
			orderRowUpdate.setPaymentStatus(orderRow.getPaymentStatus());
			orderRowUpdate.setBookingCurrency(orderRow.getBookingCurrency());
			orderRowUpdate.setBaseToBookingExchangeRate(orderRow.getBaseToBookingExchangeRate());
			orderRowUpdate.setApiToBaseExchangeRate(orderRow.getApiToBaseExchangeRate());
			orderRowUpdate.setTotalAmount(orderRow.getTotalAmount());
			session.update(orderRowUpdate);
			tx.commit();
			status=true;
		}catch (HibernateException e) {
			status=false;
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  status;
	}
	public Boolean updateMiscellaneousTravelDetailFromEditPage(MiscellaneousTravelRequest travelRequest){
		MiscellaneousTravelRequest travelRequestUpdate = null;
		Session session=null;
		Transaction tx=null;
		Boolean status=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			travelRequestUpdate=(MiscellaneousTravelRequest) session.get(MiscellaneousTravelRequest.class,travelRequest.getId());
			
			travelRequestUpdate.setConfirmationNumber(travelRequest.getConfirmationNumber());
			travelRequestUpdate.setDetails1(travelRequest.getDetails1());
			travelRequestUpdate.setDetails2(travelRequest.getDetails2());
			travelRequestUpdate.setTitle(travelRequest.getTitle());
			travelRequestUpdate.setEntity(travelRequest.getEntity());
			travelRequestUpdate.setFirstName(travelRequest.getFirstName());
			travelRequestUpdate.setLastName(travelRequest.getLastName());
			travelRequestUpdate.setSupplierName(travelRequest.getSupplierName());
			travelRequestUpdate.setTravelRequestNumber(travelRequest.getTravelRequestNumber());
			travelRequestUpdate.setTotalAmount(travelRequest.getTotalAmount());
			
			session.update(travelRequestUpdate);
			tx.commit();
			status=true;
		}catch (HibernateException e) {
			status=false;
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  status;
	}
	public MiscellaneousCreditNote miscellaneousCreditNoteData(Long id, int companyId) {
		int convertedId=id.intValue();
		String convertedCompanyId=String.valueOf(companyId);
		String sql="from MiscellaneousCreditNote cn where cn.rowId=:rowId and cn.companyId=:companyId";
		MiscellaneousCreditNote creditNote=null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("rowId",convertedId);
			query.setParameter("companyId",convertedCompanyId) ;
			creditNote=(MiscellaneousCreditNote)query.uniqueResult();
			if(creditNote!=null)
			{
				creditNote.setCancellationFees(creditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
				creditNote.setManagementFees(creditNote.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
				creditNote.setConvenienceFees(creditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
			}
		}
		catch (HibernateException e) {
//			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return creditNote;

	}
	
	public List<MiscellaneousCreditNote> loadCreditNoteListById(Long id) {
		Session  session=null;
		String sql="from MiscellaneousCreditNote cn where cn.rowId=:row_id";
		List<MiscellaneousCreditNote> list=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("row_id",id.intValue());
			List<MiscellaneousCreditNote> creditList=query.list();
			if(creditList!=null &&creditList.size()>0){
				for(MiscellaneousCreditNote note:creditList){
					note.setConvertDate(DateConversion.convertTimestampToStringWithoutAM(note.getOrderedAt()));
					note.setConvenienceFees(note.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
					note.setCancellationFees(note.getCancellationFees().setScale(2, BigDecimal.ROUND_UP));
					note.setManagementFees(note.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
					note.setGstAmount(note.getGstAmount().setScale(2, BigDecimal.ROUND_UP));
					list.add(note);
				}
			}
		}
		catch (HibernateException e) {
		}
		catch (Exception e) {
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;

	}
	public List<MiscellaneousOrderRow> getMiscellaneousBookingForIds(List<String> companyUserId ) {
		
		List<Integer> companyIds=new ArrayList<>();
		List<MiscellaneousOrderRow> miscellaneousOrderRows=null;
		BigDecimal finalPrice=new BigDecimal("0");
		Session  session=null;
		
		for (String companyIdtoConv : companyUserId) {
			companyIds.add(Integer.parseInt(companyIdtoConv));
		}
		 
 		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr=session.createCriteria(MiscellaneousOrderRow.class);
			cr.add(Restrictions.in("companyId", companyIds));
			miscellaneousOrderRows=cr.list();
			 
		}catch (HibernateException e) {
			
		}
		finally
		{
			if(session != null && session.isOpen())
			{				
				session.close(); 
			}
		}		
 		return miscellaneousOrderRows;
	}
	public void insertMiscellaneousOrderCustomer(MiscellaneousOrderCustomer miscellaneousOrderCustomer) {
		Session  session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(miscellaneousOrderCustomer);
			tx.commit();
		}
		catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
	
	}
}
