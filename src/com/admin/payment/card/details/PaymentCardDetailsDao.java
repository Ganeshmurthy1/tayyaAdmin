/**
 * 
 */
package com.admin.payment.card.details;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.CommonConfigFilter;
import com.isl.admin.page.CommonConfigPage;
import com.lintas.admin.model.CommonConfig;
import com.lintas.config.HibernateUtil;

/**
 * @author Manish Kumar
 *
 */
public class PaymentCardDetailsDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PaymentCardDetailsDao.class);

	public PaymentCardDetailsConfig insertPaymentCard(PaymentCardDetailsConfig paymentCardDetailsConfig){
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(paymentCardDetailsConfig);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();

		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return paymentCardDetailsConfig;
	}

	public PaymentCardDetailsConfig getPaymentCardInfo(PaymentCardDetailsConfig paymentCardDetailsConfig){
		Session session= null;
		PaymentCardDetailsConfig paymentCardDetailsConfigNew= null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(PaymentCardDetailsConfig.class);
			criteria.add(Restrictions.eq("id",paymentCardDetailsConfig.getId()));
			paymentCardDetailsConfigNew=(PaymentCardDetailsConfig) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return paymentCardDetailsConfigNew;
	}

	@SuppressWarnings("unchecked")
	public List<PaymentCardDetailsConfig> getAllPaymentCardDetailsList(){
		Session session= null;
		Transaction transaction=null;
		List<PaymentCardDetailsConfig> paymentCardDetailsConfigList=null;
		try {
			paymentCardDetailsConfigList=new ArrayList<PaymentCardDetailsConfig>();
			session = HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			Criteria criteria=session.createCriteria(PaymentCardDetailsConfig.class);
			criteria.addOrder(Order.desc("id"));
			paymentCardDetailsConfigList = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();

		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return paymentCardDetailsConfigList;
	}

	public PaymentCardDetailsConfig updatePaymentCardInfo(PaymentCardDetailsConfig cardDetailsConfig) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(cardDetailsConfig);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();

		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return cardDetailsConfig;
	}

	public Boolean deletePaymentCardInfo(PaymentCardDetailsConfig cardDetailsConfig) {
		// TODO Auto-generated method stub
		Session session= null;
		Transaction transaction=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			PaymentCardDetailsConfig cardDetailsConfigNewtest =(PaymentCardDetailsConfig) session.get(PaymentCardDetailsConfig.class, cardDetailsConfig.getId());
			session.delete(cardDetailsConfigNewtest);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return true;
	}

 @SuppressWarnings("unchecked")
	public List<PaymentCardDetailsConfig> getPaymentCardDetailsList(boolean paymenType){
		Session session= null;
		Transaction transaction=null;
		List<PaymentCardDetailsConfig> paymentCardDetailsConfigList=null;
		try {
			paymentCardDetailsConfigList=new ArrayList<PaymentCardDetailsConfig>();
			session = HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			Criteria criteria=session.createCriteria(PaymentCardDetailsConfig.class);
			criteria.add(Restrictions.eq("paymentType", paymenType));
			criteria.addOrder(Order.desc("id"));
			paymentCardDetailsConfigList = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();

		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		} 
		 return paymentCardDetailsConfigList;
	} 
 @SuppressWarnings("unchecked")
	public CommonConfigPage getAllPaymentCardDetailsList(CommonConfigPage commonConfigPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(PaymentCardDetailsConfig.class);
			Conjunction conjunction = Restrictions.conjunction();
			CommonConfigFilter commonConfigFilter  =null;
			if(commonConfigPage!=null && commonConfigPage.isFilterEnabled())
			{
				commonConfigFilter = commonConfigPage.getCommonConfigFilter();
				if(commonConfigFilter.getId() != null && !commonConfigFilter.getId().equalsIgnoreCase("ALL"))
			    {
			     conjunction.add(Restrictions.eq("id",Long.valueOf(commonConfigFilter.getId())));
			      
			    }
			    
				if(commonConfigFilter.getBankName() != null && !commonConfigFilter.getBankName().equalsIgnoreCase("ALL"))
				{
					conjunction.add(Restrictions.eq("bankName",commonConfigFilter.getBankName()));
					 
				}
				
				if(commonConfigFilter.getPaymentType() != null && !commonConfigFilter.getPaymentType().equalsIgnoreCase("ALL"))
				{
					conjunction.add(Restrictions.eq("paymentType",commonConfigFilter.getPaymentType().equals("true")?true:false));
					 
				}
				
				if(commonConfigFilter.getMailId() != null && !commonConfigFilter.getMailId().equalsIgnoreCase("ALL"))
				{
					conjunction.add(Restrictions.eq("mailId",commonConfigFilter.getMailId()));
					 
				}

				criteria.add(conjunction);

			}
			Long rowCount= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();

			if(rowCount>0)
			{
				if(commonConfigPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % commonConfigPage.getMaxItems() == 0)?(availableItems / commonConfigPage.getMaxItems()):((availableItems / commonConfigPage.getMaxItems()) + 1);
					commonConfigPage.setAvailableItems(availableItems);
					commonConfigPage.setAvailablePages(availablePages);
				} 
				int pageIndexDb = (commonConfigPage.getCurrentPageIndex() > 1)?commonConfigPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * commonConfigPage.getMaxItems();

				List<PaymentCardDetailsConfig> filterCommonConfigList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(PaymentCardDetailsConfig.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(commonConfigPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					filterCommonConfigList = criteria.list();
					commonConfigPage.setPaymentCardDetailsConfigList(filterCommonConfigList);
				}
				else
				{
					commonConfigPage.setAvailableItems(0);
					commonConfigPage.setCommonConfigList(new ArrayList<CommonConfig>());
				}
			}	
			else
			{
				commonConfigPage.setAvailableItems(0);
				commonConfigPage.setAvailablePages(0);
				commonConfigPage.setPaymentCardDetailsConfigList(new ArrayList<PaymentCardDetailsConfig>());
			}
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return commonConfigPage;
	}

	
	
	

}
