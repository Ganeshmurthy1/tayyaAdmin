package com.admin.knockoff.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import com.admin.payment.recievable.PaymentKnockOffRow;
import com.admin.payment.recievable.PaymentKnockOffRowTx;
import com.admin.payment.recievable.dao.PaymentRecievableDao;
import com.admin.payment.recievable.dao.PaymentRecievableDaoImpl;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.CommonUtil;

public class PaymentKnockDaoImpl implements PaymentKnockDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PaymentKnockDaoImpl.class);
PaymentRecievableDao paymentRecievableDao=new PaymentRecievableDaoImpl();
	@Override
	public PaymentKnockOffRow save(PaymentKnockOffRow paymentKnockOffRow) {
		// TODO Auto-generated method stub
		Session session=null;	
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			session.save(paymentKnockOffRow);
			tx.commit();
		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();

			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return paymentKnockOffRow;
	}
	@Override
	public PaymentKnockOffRowTx save(PaymentKnockOffRowTx paymentKnockOffRowTx) {
		// TODO Auto-generated method stub
		Session session=null;	
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx= session.beginTransaction();
			session.saveOrUpdate(paymentKnockOffRowTx);
			tx.commit();
		}
		catch(HibernateException he){
			if(tx!=null)
				tx.rollback();

			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return paymentKnockOffRowTx;
	}
	@Override
	public  PaymentKnockOffRow  fetchPaymentKnockOffRow(String orderId, Integer companyId) {
		// TODO Auto-generated method stub
		Session session=null;	
		PaymentKnockOffRow paymentKnockOffRow=null;
		try{
			Conjunction conjunction=Restrictions.conjunction();
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentKnockOffRow.class);
			conjunction.add(Restrictions.eq("companyId", companyId));
			conjunction.add(Restrictions.eq("billNo", orderId));
			paymentKnockOffRow=(PaymentKnockOffRow) criteria.add(conjunction).uniqueResult();

		}
		catch(HibernateException he){
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return paymentKnockOffRow;
	}
	@Override
	public List<PaymentKnockOffRow> fetchPaymentKnockOffRowList(Integer companyId) {
		// TODO Auto-generated method stub
		Session session=null;	
		List<PaymentKnockOffRow> paymentKnockOffRowList=null;
		try{
			Conjunction conjunction=Restrictions.conjunction();
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentKnockOffRow.class);
			conjunction.add(Restrictions.eq("companyId", companyId));
			paymentKnockOffRowList= criteria.add(conjunction).list();
		}
		catch(HibernateException he){
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return paymentKnockOffRowList;
	}
	 
	@Override
	public void updateOrderRows(String invoiceNo,boolean isKnockOff) {
		// TODO Auto-generated method stub
		switch(CommonUtil.findStringFromString(invoiceNo)){
		case "TYA":
			paymentRecievableDao.updateAirKnockOff(CommonUtil.findDigitsFromString(invoiceNo),isKnockOff);
			break;
		case "TYB":
			paymentRecievableDao.updateBusKnockOff(CommonUtil.findDigitsFromString(invoiceNo),isKnockOff);
			break;
		case "TYC":
			paymentRecievableDao.updateCarKnockOff(CommonUtil.findDigitsFromString(invoiceNo),isKnockOff);
			break;
		case "TYH":
			paymentRecievableDao.updateHotelKnockOff(CommonUtil.findDigitsFromString(invoiceNo),isKnockOff);
			break;
		case "TYI":
			paymentRecievableDao.updateInsuranceKnockOff(CommonUtil.findDigitsFromString(invoiceNo),isKnockOff);
			break;
		case "TYT":
			paymentRecievableDao.updateTrainKnockOff(CommonUtil.findDigitsFromString(invoiceNo),isKnockOff);
			break;
		case "TYV":
			paymentRecievableDao.updateVisaKnockOff(CommonUtil.findDigitsFromString(invoiceNo),isKnockOff);
			break;
		case "TYM":
			paymentRecievableDao.updateMiscellaneousKnockOff(CommonUtil.findDigitsFromString(invoiceNo),isKnockOff);
			break;
		default:
			break;
		}
		 
	}
	@Override
	public List<PaymentKnockOffRowTx> fetchPaymentKnockOffRowTxs(String BRV) {
		// TODO Auto-generated method stub
		Session session=null;	
		List<PaymentKnockOffRowTx> paymentKnockOffRowTxList=null;
		try{
			Conjunction conjunction=Restrictions.conjunction();
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentKnockOffRowTx.class);
			conjunction.add(Restrictions.eq("BRVorCRV", BRV));
			paymentKnockOffRowTxList= criteria.add(conjunction).list();
		}
		catch(HibernateException he){
			logger.error("--------------HibernateException-----------------"+he.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return paymentKnockOffRowTxList;
		 
	}
	 

}
