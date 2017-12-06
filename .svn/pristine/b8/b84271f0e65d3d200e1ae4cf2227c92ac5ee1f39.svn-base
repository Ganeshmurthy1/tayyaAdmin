package com.admin.common.commonDsrReportConfg.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.admin.common.commonDsrReportConfg.CommonDsrReportConfg;
import com.lintas.config.HibernateUtil;

public class CommonDsrReportConfgDao implements Serializable{
	/**
	 * @author Created by Basha at 18-07-2017
	 */
	private static final long serialVersionUID = 1L;
	SessionFactory sessionfactory;
	Session session = null;
	Transaction transaction = null;
	
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonDsrReportConfgDao.class);
//added by basha for  gst config save

		
		

		public CommonDsrReportConfg insertCommonDsrReportConfg(CommonDsrReportConfg commonDsrReportConfg) throws Exception{
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				session.save(commonDsrReportConfg);
				transaction.commit();

			}catch (HibernateException e) {
				if (transaction!=null)
					transaction.rollback();
				logger.error("--------------HibernateException-----------------"+e.getMessage());
			}
			catch (Exception e) {
				if (transaction!=null)
					transaction.rollback();
				logger.error("--------------Exception-----------------"+e.getMessage());
			}
			finally {
				if(session != null && session.isOpen())
					session.close(); 
			}
			return commonDsrReportConfg;
		}
		
		
		public CommonDsrReportConfg updateCommonDsrReportConfg(CommonDsrReportConfg commonDsrReportConfg) {
			// TODO Auto-generated method stub
			CommonDsrReportConfg commonDsrReportConfgnew=null;
			try
			{
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				commonDsrReportConfgnew = (CommonDsrReportConfg) session.get(CommonDsrReportConfg.class, commonDsrReportConfg.getId());
				commonDsrReportConfgnew.setCompanyId(commonDsrReportConfg.getCompanyId());
				commonDsrReportConfgnew.setAmendmentType(commonDsrReportConfg.isAmendmentType());
				commonDsrReportConfgnew.setBookerName(commonDsrReportConfg.isBookerName());
				commonDsrReportConfgnew.setBookingDate(commonDsrReportConfg.isBookingDate());
				commonDsrReportConfgnew.setBookingReference(commonDsrReportConfg.isBookingReference());
				commonDsrReportConfgnew.setBaseFare(commonDsrReportConfg.isBaseFare());
				commonDsrReportConfgnew.setBillingEntity(commonDsrReportConfg.isBillingEntity());
				commonDsrReportConfgnew.setBookersLoginId(commonDsrReportConfg.isBookersLoginId());
				commonDsrReportConfgnew.setBookingClass(commonDsrReportConfg.isBookingClass());
				commonDsrReportConfgnew.setBookingRefundType(commonDsrReportConfg.isBookingRefundType());
				commonDsrReportConfgnew.setBookingType(commonDsrReportConfg.isBookingType());
				commonDsrReportConfgnew.setCabinClass(commonDsrReportConfg.isCabinClass());
				commonDsrReportConfgnew.setCgst(commonDsrReportConfg.isCgst());
				commonDsrReportConfgnew.setConvienceFee(commonDsrReportConfg.isConvienceFee());
				commonDsrReportConfgnew.setCorporateCurrency(commonDsrReportConfg.isCorporateCurrency());
				commonDsrReportConfgnew.setCorporateName(commonDsrReportConfg.isCorporateName());
				commonDsrReportConfgnew.setCourierCharge(commonDsrReportConfg.isCourierCharge());
				commonDsrReportConfgnew.setDescription(commonDsrReportConfg.isDescription());
				commonDsrReportConfgnew.setDescription2(commonDsrReportConfg.isDescription2());
				commonDsrReportConfgnew.setDiscount(commonDsrReportConfg.isDiscount());
				commonDsrReportConfgnew.setDriverAllowDay(commonDsrReportConfg.isDriverAllowDay());
				commonDsrReportConfgnew.setDriverAllowNight(commonDsrReportConfg.isDriverAllowNight());
				commonDsrReportConfgnew.setExtraCharge(commonDsrReportConfg.isExtraCharge());
				commonDsrReportConfgnew.setExtraHourCharge(commonDsrReportConfg.isExtraHourCharge());
				commonDsrReportConfgnew.setExtraKmCharge(commonDsrReportConfg.isExtraKmCharge());
				commonDsrReportConfgnew.setFareBasis(commonDsrReportConfg.isFareBasis());
				commonDsrReportConfgnew.setFareType(commonDsrReportConfg.isFareType());
				commonDsrReportConfgnew.setGdsPnr(commonDsrReportConfg.isGdsPnr());
				commonDsrReportConfgnew.setJnTax(commonDsrReportConfg.isJnTax());
				commonDsrReportConfgnew.setGrossFare(commonDsrReportConfg.isGrossFare());
				commonDsrReportConfgnew.setGstTax(commonDsrReportConfg.isGstTax());
				commonDsrReportConfgnew.setInTax(commonDsrReportConfg.isInTax());;
				commonDsrReportConfgnew.setInvoiceDate(commonDsrReportConfg.isInvoiceDate());
				commonDsrReportConfgnew.setItineraryType(commonDsrReportConfg.isItineraryType());
				commonDsrReportConfgnew.setJourneyType(commonDsrReportConfg.isJourneyType());
				commonDsrReportConfgnew.setK3Tax(commonDsrReportConfg.isK3Tax());
				commonDsrReportConfgnew.setModeOfPayment(commonDsrReportConfg.isModeOfPayment());
				commonDsrReportConfgnew.setNetFare(commonDsrReportConfg.isNetFare());
				commonDsrReportConfgnew.setOcTax(commonDsrReportConfg.isOcTax());
				commonDsrReportConfgnew.setOtherTax(commonDsrReportConfg.isOtherTax());
				commonDsrReportConfgnew.setPaxType(commonDsrReportConfg.isPaxType());
				commonDsrReportConfgnew.setPersonalBooking(commonDsrReportConfg.isPersonalBooking());
				commonDsrReportConfgnew.setProductCode(commonDsrReportConfg.isProductCode());
				commonDsrReportConfgnew.setProductName(commonDsrReportConfg.isProductName());
				commonDsrReportConfgnew.setProductType(commonDsrReportConfg.isProductType());
				commonDsrReportConfgnew.setProvBooking(commonDsrReportConfg.isProvBooking());
				commonDsrReportConfgnew.setPsfTax(commonDsrReportConfg.isPsfTax());
				commonDsrReportConfgnew.setSgstorIgstorUgst(commonDsrReportConfg.isSgstorIgstorUgst());
				commonDsrReportConfgnew.setSupplierAmendment(commonDsrReportConfg.isSupplierAmendment());
				commonDsrReportConfgnew.setSupplierCharge(commonDsrReportConfg.isSupplierCharge());
				commonDsrReportConfgnew.setSupplierCode(commonDsrReportConfg.isSupplierCode());
				commonDsrReportConfgnew.setSupplierName(commonDsrReportConfg.isSupplierName());
				commonDsrReportConfgnew.setTicketNum(commonDsrReportConfg.isTicketNum());
				commonDsrReportConfgnew.setTayyarahCharge(commonDsrReportConfg.isTayyarahCharge());
				commonDsrReportConfgnew.setSystemInvoiceId(commonDsrReportConfg.isSystemInvoiceId());
				commonDsrReportConfgnew.setTollOrParkingCharge(commonDsrReportConfg.isTollOrParkingCharge());
				commonDsrReportConfgnew.setTotalMarkup(commonDsrReportConfg.isTotalMarkup());
				commonDsrReportConfgnew.setTotalNights(commonDsrReportConfg.isTotalNights());
				commonDsrReportConfgnew.setTravellerName(commonDsrReportConfg.isTravellerName());
				commonDsrReportConfgnew.setTravelStatus(commonDsrReportConfg.isTravelStatus());
				commonDsrReportConfgnew.setTripEndDate(commonDsrReportConfg.isTripEndDate());
				commonDsrReportConfgnew.setTripStartDate(commonDsrReportConfg.isTripStartDate());
				commonDsrReportConfgnew.setuDfTax(commonDsrReportConfg.isuDfTax());
				commonDsrReportConfgnew.setvFSTax(commonDsrReportConfg.isvFSTax());
				commonDsrReportConfgnew.setYqTax(commonDsrReportConfg.isYqTax());
				commonDsrReportConfgnew.setYrTax(commonDsrReportConfg.isYrTax());
				commonDsrReportConfgnew.setCreatedbyCompanyUserId(commonDsrReportConfg.getCreatedbyCompanyUserId());
				session.saveOrUpdate(commonDsrReportConfgnew);
				transaction.commit();
			}
			catch (HibernateException e) {
		if(transaction!=null){
			transaction.rollback();
		}
		logger.error("--------------HibernateException-----------------"+e.getMessage());
			}
			catch (Exception e) {
		if(transaction!=null){
			transaction.rollback();
		}
		logger.error("-------------Exception-----------------"+e.getMessage());
			}

			finally {
		if(session != null && session.isOpen())
		session.close(); 
			}
			return commonDsrReportConfgnew;
		}

		
		
		
		public CommonDsrReportConfg  getCommonDsrReportConfgByCompanyId(int companyid)
		{
			Session session=null;
			CommonDsrReportConfg  commonDsrReportConfgnew=null;
			try{
				session = HibernateUtil.getSessionFactory().openSession(); 
				Criteria criteria=session.createCriteria(CommonDsrReportConfg.class);
				criteria.add(Restrictions.eq("companyId", companyid));
				commonDsrReportConfgnew = (CommonDsrReportConfg) criteria.uniqueResult();

			}catch (HibernateException e) {
				logger.error("--------------HibernateException-----------------"+e.getMessage());
				e.printStackTrace();

			}finally {
				if(session!=null && session.isOpen())
					session.close(); 
			}
			return commonDsrReportConfgnew;
		}
		
		//ended by basha for updating the gst components

		
	
	
}
