package com.admin.hotel.fin.sheet.Dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.admin.api.provider.model.ApiProviderPaymentTransaction;
import com.admin.api.provider.model.ApiProviderPaymentTransactionDetail;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.common.model.PaymentTransactionDetail;
import com.lintas.admin.hotel.model.HotelOrderCancellationPolicy;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.config.HibernateUtil;

public class HotelOfflineBookingDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelOfflineBookingDao.class);
	public HotelOrderRow insertHotelOrderRowInfo(HotelOrderRow hotelOrderRow){
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(hotelOrderRow);
			transaction.commit();

		} catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());

		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelOrderRow;

	}

	public PaymentTransactionDetail insertHotelCustomerPaymentTxDetail(PaymentTransactionDetail paymentTransactionDetail) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(paymentTransactionDetail);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return paymentTransactionDetail;

	}

	public ApiProviderPaymentTransactionDetail insertHotelApiProviderPaymentTransactionDetail(ApiProviderPaymentTransactionDetail paymentTransactionDetail) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(paymentTransactionDetail);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return paymentTransactionDetail;

	}




	public PaymentTransaction insertPaymentTransactionInfo(PaymentTransaction paymentTransaction){
		// TODO Auto-generated method stub

		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(paymentTransaction);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return paymentTransaction;

	}
	public List<HotelOrderRoomInfo> insertHotelOrderRoomInfoInfo(HotelOrderRoomInfo  hotelOrderRoomInfo) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		List<HotelOrderRoomInfo> hotelOrderRoomInfoList=new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(hotelOrderRoomInfo!=null){
				session.save(hotelOrderRoomInfo);
				hotelOrderRoomInfoList.add(hotelOrderRoomInfo);

			}
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelOrderRoomInfoList;
	}

	/*public List<HotelOrderRoomInfo> insertHotelOrderRoomInfoInfo(List<HotelOrderRoomInfo> hotelOrderRoomInfoListNew) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		List<HotelOrderRoomInfo> hotelOrderRoomInfoList=new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(hotelOrderRoomInfoListNew!=null && hotelOrderRoomInfoListNew.size()>0){
				for(HotelOrderRoomInfo hotelOrderRoomInfo:hotelOrderRoomInfoListNew){
					session.save(hotelOrderRoomInfo);
					hotelOrderRoomInfoList.add(hotelOrderRoomInfo);
				}
			}
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelOrderRoomInfoList;
	}
	 */

	public List<HotelOrderGuest> insertHotelOrderGuestnfo(HotelOrderGuest  hotelOrderGuest) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		List<HotelOrderGuest> hotelOrderGuestList=new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(hotelOrderGuest!=null ){
				session.save(hotelOrderGuest);
				hotelOrderGuestList.add(hotelOrderGuest);

			}
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelOrderGuestList;
	}

	/*public List<HotelOrderGuest> insertHotelOrderGuestnfo(List<HotelOrderGuest> hotelOrderGuestListNew) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		List<HotelOrderGuest> hotelOrderGuestList=new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(hotelOrderGuestListNew!=null && hotelOrderGuestListNew.size()>0){
				for(HotelOrderGuest hotelOrderRoomInfo:hotelOrderGuestListNew){
					session.save(hotelOrderRoomInfo);
					hotelOrderGuestList.add(hotelOrderRoomInfo);
				}
			}
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelOrderGuestList;
	}*/


	public List<HotelOrderCancellationPolicy> insertHotelOrderCancellationPolicy(List<HotelOrderCancellationPolicy> cancellationPolicyList) {
		Session session= null;
		Transaction transaction=null;
		List<HotelOrderCancellationPolicy> cancellationList=new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(cancellationPolicyList!=null && cancellationPolicyList.size()>0){
				for(HotelOrderCancellationPolicy cancellationPolicy:cancellationPolicyList){
					session.save(cancellationPolicy);
					cancellationList.add(cancellationPolicy);
				}
			}
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return cancellationList;
	}
	public HotelOrderRow getHotelOrderRowDetails(Long hotelOrderId) {
		Session session= null;
		HotelOrderRow hotelOrderRowObj=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(HotelOrderRow.class);
			criteria.add(Restrictions.eq("id", hotelOrderId));
			hotelOrderRowObj= (HotelOrderRow) criteria.uniqueResult();

		} catch (Exception e) {

			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return hotelOrderRowObj;


	}

	public ApiProviderPaymentTransaction insertSupplierPaymentTransactionInfo(ApiProviderPaymentTransaction apiProviderPaymentTransaction) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(apiProviderPaymentTransaction);
			transaction.commit();

		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return apiProviderPaymentTransaction;
	}
	 
	public List<PaymentTransactionDetail> getPaymentTransactionDetailList(String api_transaction_id) {
		// TODO Auto-generated method stub
		Session session= null;
		List<PaymentTransactionDetail> list=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentTransactionDetail.class);
			criteria.add(Restrictions.eq("apiTransactionId", api_transaction_id));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return list;
	}

	public List<ApiProviderPaymentTransactionDetail> getApiProviderPaymentTransactionDetailList(String api_transaction_id) {
		Session session= null;
		List<ApiProviderPaymentTransactionDetail> list=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(ApiProviderPaymentTransactionDetail.class);
			criteria.add(Restrictions.eq("apiTransactionId", api_transaction_id));
			list= criteria.list();
		} catch (Exception e) {
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return list;
	}

	public PaymentTransaction  getPaymentTransactionDetails(String api_transaction_id) {
		Session session= null;
		PaymentTransaction paymentTransaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentTransaction.class);
			criteria.add(Restrictions.eq("apiTransactionId", api_transaction_id));
			paymentTransaction= (PaymentTransaction) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return paymentTransaction;
	}
	
	public PaymentTransaction  updatePaymentTransaction(Long id) {
		Session session= null;
		PaymentTransaction paymentTransaction=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			paymentTransaction=(PaymentTransaction) session.get(PaymentTransaction.class, id);
			paymentTransaction.setIsPaymentSuccess(true);
			session.saveOrUpdate(paymentTransaction);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) 
				tx.rollback();
			 
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return paymentTransaction;
	}

	public ApiProviderPaymentTransaction updateApiProviderPaymentTransaction(Long id) {
		// TODO Auto-generated method stub
		Session session= null;
		ApiProviderPaymentTransaction paymentTransaction=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			paymentTransaction=(ApiProviderPaymentTransaction) session.get(ApiProviderPaymentTransaction.class, id);
			paymentTransaction.setIsPaymentSuccess(true);
			session.saveOrUpdate(paymentTransaction);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) 
				tx.rollback();
			 
			logger.error("HibernateException---------------"+e.getMessage());
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return paymentTransaction;
	}
	
	 
}
