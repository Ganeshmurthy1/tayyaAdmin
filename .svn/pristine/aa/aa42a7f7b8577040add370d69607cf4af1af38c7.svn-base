package com.lintas.admin.DAO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;

public class WalletAmountTransferDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(WalletAmountTransferDao.class);
	
	public   WalletAmountTranferHistory getSuperUserBookingDetails(int userId, int companyid, String orderId) {
		WalletAmountTranferHistory walletAmountTranferHistory=new WalletAmountTranferHistory();
		// TODO Auto-generated method stub
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(WalletAmountTranferHistory.class);
			criteria.add(Restrictions.eq("actionId", orderId));
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.eq("companyId", companyid));
			walletAmountTranferHistory=(WalletAmountTranferHistory) criteria.uniqueResult();
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
		return walletAmountTranferHistory;
	}
	
	
	
	
	public synchronized WalletAmountTranferHistory insertWalletHistory(String message,String transactionMessage,WalletAmountTranferHistory walletAmountTranferHistory, BigDecimal openingBalance, BigDecimal closingBalance, BigDecimal amount ) {
		logger.info("Wallet history insertion method started---------------");
		WalletAmountTranferHistory	updatedWalletHistory= new WalletAmountTranferHistory();
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			updatedWalletHistory.setAction(message);
			updatedWalletHistory.setActionId(walletAmountTranferHistory.getActionId());
			updatedWalletHistory.setAmount(amount);
			updatedWalletHistory.setOpeningBalance(openingBalance);
			updatedWalletHistory.setClosingBalance(closingBalance);
			updatedWalletHistory.setCreatedAt(new Timestamp(new Date().getTime()));
			updatedWalletHistory.setCurrency(walletAmountTranferHistory.getCurrency());
			updatedWalletHistory.setParentClosingBalance(walletAmountTranferHistory.getParentClosingBalance());
			updatedWalletHistory.setParentOpeningBalance(walletAmountTranferHistory.getParentOpeningBalance());
			updatedWalletHistory.setParentUserId(walletAmountTranferHistory.getParentUserId());
			updatedWalletHistory.setUserId(walletAmountTranferHistory.getUserId());
			updatedWalletHistory.setWalletId(walletAmountTranferHistory.getWalletId());
			updatedWalletHistory.setRemarks(message);
			updatedWalletHistory.setInvoiceNo(walletAmountTranferHistory.getInvoiceNo());
			updatedWalletHistory.setTransactionType(transactionMessage);
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

	
	
	
	
	
	
	
}
