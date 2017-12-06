package com.admin.hotel.fin.sheet.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.util.CommonUtilSession;
import com.admin.hotel.fin.sheet.Dao.HotelOfflineBookingDao;
import com.admin.payment.card.details.PaymentCardDetailsConfig;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.common.model.PaymentTransactionDetail;
import com.lintas.admin.model.User;
import com.lintas.config.RandomConfigurationNumber;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerHotelPaymentTxAction extends ActionSupport implements SessionAware, ModelDriven<PaymentTransactionDetail>{

	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CustomerHotelPaymentTxAction.class);
	private static final long serialVersionUID = 1L;
	PaymentTransactionDetail paymentTransactionDetail=new PaymentTransactionDetail();
	PaymentCardDetailsConfig clientPaymentCardInfo=null;
	SessionMap<String, Object> sessionMap;
	public String insertCustomerPaymentTxDetail(){
		User sessionUser=(User) sessionMap.get("User");
		BigDecimal partialTotalPaidAmount=new BigDecimal("0.00");
		PaymentTransaction paymentTransaction=	new HotelOrderDao().getPaymentTransactionInfo(paymentTransactionDetail.getApiTransactionId());
		List<PaymentTransactionDetail> newPaymentTransactionDetailList=new HotelOfflineBookingDao().getPaymentTransactionDetailList(paymentTransactionDetail.getApiTransactionId());
		logger.info("newPaymentTransactionDetailList size-------------------"+newPaymentTransactionDetailList.size());
		
		if(newPaymentTransactionDetailList!=null && newPaymentTransactionDetailList.size()>0){
			for(PaymentTransactionDetail paymentTransactionDetail: newPaymentTransactionDetailList){
				partialTotalPaidAmount= partialTotalPaidAmount.add(paymentTransactionDetail.getAmount());
			}
		}
		BigDecimal totalPaidAmount=	partialTotalPaidAmount.add(paymentTransactionDetail.getAmount());
		if(totalPaidAmount.setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
			paymentTransactionDetail.setIsPaymentSuccess(true);
		}
		/*else if(partialTotalPaidAmount.setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
		
		}*/
		paymentTransactionDetail.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		paymentTransactionDetail.setCompanyId(sessionUser.getCompanyid());
		paymentTransactionDetail.setPaymentTransaction(paymentTransaction);
		paymentTransactionDetail.setCreatedAt(new Timestamp(new Date().getTime()));
		paymentTransactionDetail.setTransactionId(RandomConfigurationNumber.generateHotelandFlightPaymentTxKey());
		//PaymentCardDetailsConfig clientPaymentCardInfo =new PaymentCardDetailsDao().getPaymentCardInfo(new PaymentCardDetailsConfig(paymentTransactionDetail.getClientCardHolderId()));
		//paymentTransactionDetail.setClientPaymentCardDetailsConfig(clientPaymentCardInfo);
		/*paymentTransactionDetail.setPaymentStatus(paymentTransactionDetail.getPaymentMethod());*/
		PaymentTransactionDetail paymentTransactionDetailNew=new HotelOfflineBookingDao().insertHotelCustomerPaymentTxDetail(paymentTransactionDetail);
		 if(paymentTransactionDetailNew!=null && paymentTransactionDetailNew.getId()>0){
			 if(paymentTransactionDetailNew.getIsPaymentSuccess()!=null){
				 new HotelOfflineBookingDao().updatePaymentTransaction(paymentTransactionDetailNew.getPaymentTransaction().getId());
				}
			addActionMessage("Payment updated.");
			return SUCCESS;
		}
		else{
			addActionMessage("We found some error.Please try again.");
			return ERROR;
		} 
	}
	public String insertFlightCustomerPaymentTxDetail(){
		User sessionUser=(User) sessionMap.get("User");
		BigDecimal partialTotalPaidAmount=new BigDecimal("0.00");
		PaymentTransaction paymentTransaction=	new HotelOrderDao().getPaymentTransactionInfo(paymentTransactionDetail.getApiTransactionId());
		List<PaymentTransactionDetail> newPaymentTransactionDetailList=new HotelOfflineBookingDao().getPaymentTransactionDetailList(paymentTransactionDetail.getApiTransactionId());
		if(newPaymentTransactionDetailList!=null && newPaymentTransactionDetailList.size()>0){
			for(PaymentTransactionDetail paymentTransactionDetail: newPaymentTransactionDetailList){
				partialTotalPaidAmount= partialTotalPaidAmount.add(paymentTransactionDetail.getAmount());
				
				
			}
		}
		BigDecimal totalPaidAmount=	partialTotalPaidAmount.add(paymentTransactionDetail.getAmount());
		if(totalPaidAmount.setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
			paymentTransactionDetail.setIsPaymentSuccess(true);
		}
		/*else if(partialTotalPaidAmount.setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
		
		}*/
		paymentTransactionDetail.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		paymentTransactionDetail.setCompanyId(sessionUser.getCompanyid());
		paymentTransactionDetail.setPaymentTransaction(paymentTransaction);
		paymentTransactionDetail.setCreatedAt(new Timestamp(new Date().getTime()));
		paymentTransactionDetail.setTransactionId(RandomConfigurationNumber.generateHotelandFlightPaymentTxKey());
		paymentTransactionDetail.setPaymentStatus(paymentTransactionDetail.getPaymentMethod());
		PaymentTransactionDetail paymentTransactionDetailNew=new HotelOfflineBookingDao().insertHotelCustomerPaymentTxDetail(paymentTransactionDetail);
		/*paymentCardInfo =new PaymentCardDetailsDao().getPaymentCardInfo(new PaymentCardDetailsConfig(paymentTransactionDetail.getClientCardHolderId()));
		paymentTransactionDetail.setPaymentCardInfo(paymentCardInfo);*/
		if(paymentTransactionDetailNew!=null && paymentTransactionDetailNew.getId()>0){
			 if(paymentTransactionDetailNew.getIsPaymentSuccess()!=null){
				 new HotelOfflineBookingDao().updatePaymentTransaction(paymentTransactionDetailNew.getPaymentTransaction().getId());
				}
			 addActionMessage("Payment updated.");
			return SUCCESS;
		}
		else{
			addActionMessage("We found some error.Please try again.");
			return ERROR;
		} 
	}
	 
	 
	@Override
	public PaymentTransactionDetail getModel() {
		// TODO Auto-generated method stub
		return paymentTransactionDetail;
	}
	public PaymentTransactionDetail getPaymentTransactionDetail() {
		return paymentTransactionDetail;
	}
	public void setPaymentTransactionDetail(PaymentTransactionDetail paymentTransactionDetail) {
		this.paymentTransactionDetail = paymentTransactionDetail;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	
}
