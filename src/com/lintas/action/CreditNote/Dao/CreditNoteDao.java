package com.lintas.action.CreditNote.Dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.common.model.BusOrderRowCancellation;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.HotelOrderRowCancellation;
import com.lintas.admin.flight.model.FlightCommissionReport;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderRowCancellation;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class CreditNoteDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CreditNoteDao.class);
	/*load all FlightOrderRow data for Invoice*/
	public  List<FlightCommissionReport> getAgentCreditNoteList(Company sessionCompany){
		List<FlightCommissionReport> newFlightOrderRowList=new ArrayList<FlightCommissionReport>();
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow fr where fr.companyId=:company_id order by fr.id desc";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(sessionCompany.getCompanyid()));
			List<FlightOrderRow> list = query.list();
			for (FlightOrderRow flightOrderRow:list){
				FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
				BigDecimal finalPriceInBaseCurrency=flightOrderRow.getFinalPrice().divide(flightOrderRow.getBaseToBookingExchangeRate());
				flightCommissionReport.setFinalPrice(finalPriceInBaseCurrency.setScale(2,BigDecimal.ROUND_UP));
				flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
				flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()) );
				flightCommissionReport.setOrderId(flightOrderRow.getOrderId());
				flightCommissionReport.setCurCode(flightOrderRow.getBaseCurrency());
				flightCommissionReport.setStatus(flightOrderRow.getStatusAction());
				flightCommissionReport.setPaymentStatus(flightOrderRow.getPaymentStatus());
				flightCommissionReport.setCreatedBy(flightOrderRow.getCreatedBy());
				flightCommissionReport.setBookingDate(DateConversion.getBluestarDate(flightOrderRow.getBookingDate()));
				flightCommissionReport.setUserId(flightOrderRow.getUserId());
				flightCommissionReport.setId(flightOrderRow.getId());
				newFlightOrderRowList.add(flightCommissionReport);
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return newFlightOrderRowList;
	}
	public FlightOrderRow getFlightOrderRowDataForCreditNote(Long id) {
		// TODO Auto-generated method stub
		Session session = null;
		FlightOrderRow flightOrderRow=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRow fr where fr.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",id);
			flightOrderRow = (FlightOrderRow) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return flightOrderRow;
	}

	public List<CreditNote> getCreditNoteListByOrderRowID(Long id) {
		// TODO Auto-generated method stub
		Session session = null;
		List<CreditNote> creditNoteList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(CreditNote.class);
			criteria.add(Restrictions.eq("rowId", id.intValue()));
			criteria.addOrder(Order.desc("id"));
			creditNoteList = criteria.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return creditNoteList;
	}
	public List<WalletAmountTranferHistory> getWalletHistoryDetailsByOrderId(String orderId) {
		// TODO Auto-generated method stub
		Session session = null;
		List<WalletAmountTranferHistory> historyList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from WalletAmountTranferHistory wt where wt.actionId=:action_id";
			Query query = session.createQuery(sql);
			query.setParameter("action_id",orderId);
			historyList = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return historyList;
	}
	public synchronized UserWallet getWalletAmountByWalletId(WalletAmountTranferHistory walletAmountTranferHistory) {
		// TODO Auto-generated method stub
		Session session = null;
		UserWallet userWallet=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from UserWallet uw where uw.walletId=:wallet_id";
			Query query = session.createQuery(sql);
			query.setParameter("wallet_id", walletAmountTranferHistory.getWalletId());
			userWallet = (UserWallet) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return userWallet;
	}

	public synchronized UserWallet getWalletAmountByWalletId(int walletId) {
		// TODO Auto-generated method stub
		UserWallet userWallet=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from UserWallet uw where uw.walletId=:wallet_id";
			Query query = session.createQuery(sql);
			query.setParameter("wallet_id", walletId);
			userWallet = (UserWallet) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return userWallet;
	}
	public synchronized UserWallet updateUserWallet(UserWallet userWallet) {
		// TODO Auto-generated method stub
		UserWallet updateUserWallet = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateUserWallet =  (UserWallet) session.get(UserWallet.class,userWallet.getWalletId());
			if(userWallet.getTxType().equals("Withdrawal")) 
				updateUserWallet.setDepositBalance(userWallet.getDepositBalance());
			else 
			updateUserWallet.setWalletbalance(userWallet.getWalletbalance());
			updateUserWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.save(updateUserWallet);
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
		return updateUserWallet;
	}
	public synchronized UserWallet updateUserWalletForCancellation(UserWallet userWallet) {
		// TODO Auto-generated method stub
		UserWallet updateUserWallet = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateUserWallet =  (UserWallet) session.get(UserWallet.class,userWallet.getWalletId());
			updateUserWallet.setWalletbalance(userWallet.getWalletbalance());
			updateUserWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
			session.save(updateUserWallet);
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
		return updateUserWallet;
	}
	public synchronized WalletAmountTranferHistory updateWalletHistory(String message, BigDecimal walletbalance, BigDecimal toatAmount, BigDecimal amount, WalletAmountTranferHistory walletAmountTranferHistory) {
		logger.info("Wallet history insertion method started---------------");
		
		
		WalletAmountTranferHistory	updatedWalletHistory= new WalletAmountTranferHistory();
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updatedWalletHistory.setAction(message);
			updatedWalletHistory.setActionId(walletAmountTranferHistory.getActionId());
			updatedWalletHistory.setAmount(amount);
			updatedWalletHistory.setOpeningBalance(walletbalance);
			updatedWalletHistory.setClosingBalance(toatAmount);
			updatedWalletHistory.setCreatedAt(new Timestamp(new Date().getTime()));
			updatedWalletHistory.setCurrency(walletAmountTranferHistory.getCurrency());
			updatedWalletHistory.setParentClosingBalance(walletAmountTranferHistory.getParentClosingBalance());
			updatedWalletHistory.setParentOpeningBalance(walletAmountTranferHistory.getParentOpeningBalance());
			updatedWalletHistory.setParentUserId(walletAmountTranferHistory.getParentUserId());
			updatedWalletHistory.setUserId(walletAmountTranferHistory.getUserId());
			updatedWalletHistory.setWalletId(walletAmountTranferHistory.getWalletId());
			updatedWalletHistory.setRemarks(message);
			updatedWalletHistory.setInvoiceNo(walletAmountTranferHistory.getInvoiceNo());
			updatedWalletHistory.setTransactionType("Refunded");
			updatedWalletHistory.setCompanyId(walletAmountTranferHistory.getCompanyId());
			session.save(updatedWalletHistory);
			logger.info("Wallet history insertion method ended---------------"+updatedWalletHistory.getId());
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
		return updatedWalletHistory;
		// TODO Auto-generated method stub

	}
	
 
	public CreditNote updateRefundingAmount(CreditNote creditNote) {
		// TODO Auto-generated method stub
		CreditNote updateCreditNote   =  null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateCreditNote =  (CreditNote) session.get(CreditNote.class,creditNote.getId());
			updateCreditNote.setRefundedAmount(creditNote.getRefundedAmount());
			updateCreditNote.setTotalBookingAmount(creditNote.getTotalBookingAmount());
			updateCreditNote.setOrderUpdated(true);
			updateCreditNote.setCreditnoteIssued(true);
			updateCreditNote.setIssuedAt(new Timestamp(new Date().getTime()));
			session.update(updateCreditNote);
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
	public CreditNote getCreditNoteDetailsByComapnyId(int companyId,Long rowId) {
		// TODO Auto-generated method stub
		CreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CreditNote cn where cn.companyId=:company_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("company_id", String.valueOf(companyId));
			query.setParameter("row_id", rowId.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(CreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}
	
	public CreditNote getCreditNoteDetailsByUserId(int userId,Long rowId) {
		// TODO Auto-generated method stub
		CreditNote creditNoteObj   = null;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from CreditNote cn where cn.userId=:user_id and cn.rowId=:row_id and cn.afterStatus=:after_status and cn.afterPayStatus=:after_pay_status";
			Query query = session.createQuery(sql);
			query.setParameter("user_id", String.valueOf(userId));
			query.setParameter("row_id", rowId.intValue());
			query.setParameter("after_status","Cancelled");
			query.setParameter("after_pay_status","Refund");
			creditNoteObj =(CreditNote) query.uniqueResult(); 
		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());

		}finally{
			session.close();
		}
		return creditNoteObj;
	}

	public FlightOrderRow updateCreditNoteIssuedInFlightOrderRow(Long id,boolean setCreditNoteIssued) {
		// TODO Auto-generated method stub
		FlightOrderRow updateFlightOrderRow   = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			updateFlightOrderRow =  (FlightOrderRow) session.get(FlightOrderRow.class,id);
			updateFlightOrderRow.setCreditNoteIssued(setCreditNoteIssued);
			session.update(updateFlightOrderRow);
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
		return updateFlightOrderRow;
	}
	/* flight order customer Particulars   */
	public  List<FlightOrderCustomer> getFlightOrderCustomerDetails(Long id){
		List<FlightOrderCustomer> orderCustomerList=null;
		Session session = null;
		String sql = "from FlightOrderCustomer foc where foc.flightOrderRow.id=:order_row_id";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("order_row_id", id);
			orderCustomerList =query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return orderCustomerList;
	}

	/* FlightTrip Particulars for Invoice */
	public  List<FlightOrderTripDetail> getFlightTripParticulars(Long id){
		List<FlightOrderTripDetail>  flightOrderTripDetailList=new ArrayList<FlightOrderTripDetail>();
		Session session = null;
		String sql = "from FlightOrderTripDetail ft where ft.flightOrderRow.id=:id";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("id", id);
			List<FlightOrderTripDetail> list = query.list();
			for(FlightOrderTripDetail flightOrderTripDetail:list){
				flightOrderTripDetail.setConvertDate(DateConversion.convertDateToStringToDate(flightOrderTripDetail.getDepartureDate()));
				flightOrderTripDetailList.add(flightOrderTripDetail);
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return flightOrderTripDetailList;

	}
	public  List<User>  getAgentAddress(String userId){
		Session session = null;
		List<User> list=null;
		try{
			if(userId!=null){
				session = HibernateUtil.getSessionFactory().openSession();
				String sql = "from User u where u.id=:id";
				Query query = session.createQuery(sql);
				query.setParameter("id",Integer.parseInt(userId));
				list = query.list();
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return list;
	}
	public  User getParentAddress(int userId){
		Session session = null;
		User parentAgentAddress=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", userId);
			parentAgentAddress = (User) query.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return parentAgentAddress;
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
	public List<FlightOrderRowMarkup> getMarkupsforAllCompaniesByFlightRowID(Long id) {
		// TODO Auto-generated method stub
		List<FlightOrderRowMarkup>  flightOrderRowMarkupList=null;
		Session session = null;
		String sql = "from FlightOrderRowMarkup fm where fm.flightOrderRow.id=:order_row_id";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("order_row_id", id);
			flightOrderRowMarkupList = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return flightOrderRowMarkupList;
	}

	public int getParentUserIdLevel1(Integer integer) throws Exception
	{
		Session session = null;
		int parentUserid=0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id", integer);
			User user = (User) query.uniqueResult();
			if (user!=null) {
				parentUserid=user.getCreatedbyCompanyUserId();
			}
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			session.close(); 
		}
		return parentUserid;
	}

	public List<Integer> getParentUserIdLevel2(Integer integer) throws Exception
	{
		List<Integer> userIds=new LinkedList<>();
		Session session = null;
		try{
			int parentUseridLevel1=getParentUserIdLevel1(integer);
			if(integer!=parentUseridLevel1){
				userIds.add(integer);
				userIds.add(parentUseridLevel1);
			}
			else{
				userIds.add(integer);
			}
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User u where u.id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",parentUseridLevel1);
			User user = (User) query.uniqueResult();
			if (user!=null) {
				int parentUseridLevel2=user.getCreatedbyCompanyUserId();
				if(parentUseridLevel2!=parentUseridLevel1){
					userIds.add(parentUseridLevel2);
				}
			}
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			session.close(); 
		}
		Collections.reverse(userIds);
		return  userIds;
	}
	public  Company findParentCompany(Company company) throws Exception
	{
		Session session = null;
		Company parentCompany=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from Company where company_userid=:company_userid";
			Query query = session.createQuery(sql);
			query.setParameter("company_userid",company.getParent_company_userid());
			parentCompany = (Company) query.uniqueResult();

		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			session.close(); 
		}

		return  parentCompany;
	}
	public  User getCompanyIdObjByPassingUserId(int userId) throws Exception
	{
		Session session = null;
		User user=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from User where  id=:id";
			Query query = session.createQuery(sql);
			query.setParameter("id",userId);
			user = (User) query.uniqueResult();

		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			session.close(); 
		}

		return  user;
	}
	public FlightOrderRowCancellation getFlightOrderRowCancellationWithAPIStatus(String orderReference) {
		// TODO Auto-generated method stub
		FlightOrderRowCancellation cancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(FlightOrderRowCancellation.class);
			criteria.add(Restrictions.eq("orderId", orderReference));
			criteria.add(Restrictions.eq("APIStatusCode", "1"));
			criteria.add(Restrictions.eq("statusMessage", "Success"));
			cancellation=(FlightOrderRowCancellation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return cancellation;
	}
	
	
	public HotelOrderRowCancellation getHotelOrderRowCancellationWithAPIStatus(String orderReference) {
		// TODO Auto-generated method stub
		HotelOrderRowCancellation cancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderRowCancellation.class);
			criteria.add(Restrictions.eq("orderId", orderReference));
			criteria.add(Restrictions.eq("APIStatusCode", "3"));
			criteria.add(Restrictions.eq("APIStatusMessage", "Cancellation Processed"));
			criteria.add(Restrictions.eq("statusMessage", "Success"));
			cancellation=(HotelOrderRowCancellation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return cancellation;
	}
	
	
	public FlightOrderRowCancellation getUpdateFlightOrderRowCancellation(String orderId) throws HibernateException, Exception {
		Session sess = null;		
		FlightOrderRowCancellation flightOrderRowCancellation = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(FlightOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", orderId));			
			flightOrderRowCancellation = (FlightOrderRowCancellation) crit.uniqueResult();
			sess.close();
		}catch (HibernateException e) {			
			logger.info("Exceeption----HibernateException--"+e.getMessage());
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close(); 
		}
		return flightOrderRowCancellation;
	}

	public FlightOrderRowCancellation insertOrUpdateFlightOrderRowCancellation(FlightOrderRowCancellation flightOrderRowCancellation) throws HibernateException, Exception {
		Session sess = null;
		Transaction tx = null;		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(FlightOrderRowCancellation.class);			
			crit.add(Restrictions.eq("orderId", flightOrderRowCancellation.getOrderId()));			
			FlightOrderRowCancellation hotelOrderRowCancellationDb = (FlightOrderRowCancellation) crit.uniqueResult();
			Timestamp updated_at = new Timestamp(new Date().getTime());				
			if(hotelOrderRowCancellationDb != null)
			{
				hotelOrderRowCancellationDb.setAPIStatusCode(flightOrderRowCancellation.getAPIStatusCode());
				hotelOrderRowCancellationDb.setAPIStatusMessage(flightOrderRowCancellation.getAPIStatusMessage());
				hotelOrderRowCancellationDb.setAPIConfirmationNumber(flightOrderRowCancellation.getAPIConfirmationNumber());
				hotelOrderRowCancellationDb.setAPIChargeType(flightOrderRowCancellation.getAPIChargeType());
				hotelOrderRowCancellationDb.setAPIChargeAmount(flightOrderRowCancellation.getAPIChargeAmount());
				hotelOrderRowCancellationDb.setAPICurrency(flightOrderRowCancellation.getAPICurrency());
				hotelOrderRowCancellationDb.setAPIPaymentType(flightOrderRowCancellation.getAPIPaymentType());
				hotelOrderRowCancellationDb.setAPIReference(flightOrderRowCancellation.getAPIReference());
				hotelOrderRowCancellationDb.setAPIRefundAmount(flightOrderRowCancellation.getAPIRefundAmount());
				hotelOrderRowCancellationDb.setStatusCode(flightOrderRowCancellation.getStatusCode());
				hotelOrderRowCancellationDb.setStatusMessage(flightOrderRowCancellation.getStatusMessage());				
				hotelOrderRowCancellationDb.setUpdatedAt(updated_at);				
				tx = sess.beginTransaction();
				sess.update(hotelOrderRowCancellationDb);
				tx.commit();
				flightOrderRowCancellation = hotelOrderRowCancellationDb;
				logger.info("hotelOrderRowCancellation updated successfully-");
			}
			else
			{
				tx = sess.beginTransaction();
				flightOrderRowCancellation.setCreatedAt(updated_at);				
				sess.saveOrUpdate(flightOrderRowCancellation);
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
		return flightOrderRowCancellation;
	}
	public List<WalletAmountTranferHistory> getWalletHistoryDetailsByOrderId(String orderId, String actionMsg) {

		// TODO Auto-generated method stub
		Session session = null;
		List<WalletAmountTranferHistory> historyList=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from WalletAmountTranferHistory wt where wt.actionId=:action_id and wt.action=:actionMsg";
			Query query = session.createQuery(sql);
			query.setParameter("action_id",orderId);
			query.setParameter("actionMsg",actionMsg);
			historyList = query.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			session.close(); 
		}
		return historyList;
	
	}
	public BusOrderRowCancellation getBusOrderRowCancellationWithAPIStatus(String orderId) {
		BusOrderRowCancellation cancellation=null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(BusOrderRowCancellation.class);
			criteria.add(Restrictions.eq("orderId", orderId));
			criteria.add(Restrictions.eq("APIStatusCode", "1"));
			criteria.add(Restrictions.eq("statusMessage", "Success"));
			cancellation=(BusOrderRowCancellation) criteria.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return cancellation;
	}
  
}
