package com.admin.hotel.fin.sheet.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.admin.api.provider.model.ApiProviderPaymentTransaction;
import com.admin.api.provider.model.ApiProviderPaymentTransactionDetail;
import com.admin.common.util.CommonUtilSession;
import com.admin.hotel.fin.sheet.Dao.HotelOfflineBookingDao;
import com.admin.payment.card.details.PaymentCardDetailsConfig;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.model.User;
import com.lintas.config.RandomConfigurationNumber;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ApiProviderPaymentTxAction extends ActionSupport implements SessionAware,ModelDriven<ApiProviderPaymentTransactionDetail>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ApiProviderPaymentTransactionDetail apiProviderPaymentTransactionDetail=new ApiProviderPaymentTransactionDetail();
	ApiProviderPaymentTransaction apiProviderPaymentTransaction=new ApiProviderPaymentTransaction();
	String paymentMethod ;
	PaymentCardDetailsConfig paymentCardInfo=null;
	SessionMap<String, Object> sessionMap;
	
	
	public String insertApiProviderPaymentTransaction(){
		User sessionUser=(User) sessionMap.get("User");
		BigDecimal partialTotalPaidAmount=new BigDecimal("0.00");
		ApiProviderPaymentTransaction paymentTransaction=	new HotelOrderDao().getApiProviderPaymentTransactionInfo(apiProviderPaymentTransactionDetail.getApiTransactionId());
		List<ApiProviderPaymentTransactionDetail> newPaymentTransactionDetailList=new HotelOfflineBookingDao().getApiProviderPaymentTransactionDetailList(apiProviderPaymentTransactionDetail.getApiTransactionId());
		if(newPaymentTransactionDetailList!=null && newPaymentTransactionDetailList.size()>0){
			for(ApiProviderPaymentTransactionDetail paymentTransactionDetail: newPaymentTransactionDetailList){
				partialTotalPaidAmount= partialTotalPaidAmount.add(paymentTransactionDetail.getAmount());
			}
		}
		BigDecimal totalPaidAmount=	partialTotalPaidAmount.add(apiProviderPaymentTransactionDetail.getAmount());
		if(totalPaidAmount.setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
			apiProviderPaymentTransactionDetail.setIsPaymentSuccess(new Boolean(true));
		}
		/*else if(partialTotalPaidAmount.setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
		}*/
		apiProviderPaymentTransactionDetail.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		apiProviderPaymentTransactionDetail.setCompanyId(sessionUser.getCompanyid());
		apiProviderPaymentTransactionDetail.setApiProviderPaymentTransaction(paymentTransaction);
		apiProviderPaymentTransactionDetail.setCreatedAt(new Timestamp(new Date().getTime()));
		apiProviderPaymentTransactionDetail.setTransactionId(RandomConfigurationNumber.generateHotelandFlightPaymentTxKey());
		//apiProviderPaymentTransactionDetail.setPaymentStatus(apiProviderPaymentTransactionDetail.getPaymentMethod());
		//paymentCardInfo =new PaymentCardDetailsDao().getPaymentCardInfo(new PaymentCardDetailsConfig(apiProviderPaymentTransactionDetail.getSupplierCardHolderId()));
		//apiProviderPaymentTransactionDetail.setApiProviderPaymentCardInfo(paymentCardInfo);
		ApiProviderPaymentTransactionDetail paymentTransactionDetailNew=new HotelOfflineBookingDao().insertHotelApiProviderPaymentTransactionDetail(apiProviderPaymentTransactionDetail);
		if(paymentTransactionDetailNew!=null){
			if(paymentTransactionDetailNew.getIsPaymentSuccess()!=null){
				new HotelOfflineBookingDao().updateApiProviderPaymentTransaction(paymentTransactionDetailNew.getApiProviderPaymentTransaction().getId());
			}
			addActionMessage("Payment updated.");
			return SUCCESS;
		}
		else{
			addActionMessage("We found some error.Please try again.");
			return ERROR;
		} 
	}

	public String insertFlightApiProviderPaymentTxDetail(){
		User sessionUser=(User) sessionMap.get("User");
		BigDecimal partialTotalPaidAmount=new BigDecimal("0.00");
		ApiProviderPaymentTransaction paymentTransaction=	new HotelOrderDao().getApiProviderPaymentTransactionInfo(apiProviderPaymentTransactionDetail.getApiTransactionId());
		List<ApiProviderPaymentTransactionDetail> newPaymentTransactionDetailList=new HotelOfflineBookingDao().getApiProviderPaymentTransactionDetailList(apiProviderPaymentTransactionDetail.getApiTransactionId());
		if(newPaymentTransactionDetailList!=null && newPaymentTransactionDetailList.size()>0){
			for(ApiProviderPaymentTransactionDetail paymentTransactionDetail: newPaymentTransactionDetailList){
				partialTotalPaidAmount= partialTotalPaidAmount.add(paymentTransactionDetail.getAmount());
			}
		}
		BigDecimal totalPaidAmount=	partialTotalPaidAmount.add(apiProviderPaymentTransactionDetail.getAmount());
		if(totalPaidAmount.setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
			apiProviderPaymentTransactionDetail.setIsPaymentSuccess(new Boolean(true));
		}
		/*else if(partialTotalPaidAmount.setScale(0, BigDecimal.ROUND_FLOOR).equals(paymentTransaction.getAmount().setScale(0, BigDecimal.ROUND_FLOOR))){
		}*/
		apiProviderPaymentTransactionDetail.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
		apiProviderPaymentTransactionDetail.setCompanyId(sessionUser.getCompanyid());
		apiProviderPaymentTransactionDetail.setApiProviderPaymentTransaction(paymentTransaction);
		apiProviderPaymentTransactionDetail.setCreatedAt(new Timestamp(new Date().getTime()));
		apiProviderPaymentTransactionDetail.setTransactionId(RandomConfigurationNumber.generateHotelandFlightPaymentTxKey());
		 
		ApiProviderPaymentTransactionDetail paymentTransactionDetailNew=new HotelOfflineBookingDao().insertHotelApiProviderPaymentTransactionDetail(apiProviderPaymentTransactionDetail);
		if(paymentTransactionDetailNew!=null){
			if(paymentTransactionDetailNew.getIsPaymentSuccess()!=null){
				new HotelOfflineBookingDao().updateApiProviderPaymentTransaction(paymentTransactionDetailNew.getApiProviderPaymentTransaction().getId());
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
	public ApiProviderPaymentTransactionDetail getModel() {
		// TODO Auto-generated method stub
		return apiProviderPaymentTransactionDetail;
	}

	public ApiProviderPaymentTransaction getApiProviderPaymentTransaction() {
		return apiProviderPaymentTransaction;
	}

	public void setApiProviderPaymentTransaction(ApiProviderPaymentTransaction apiProviderPaymentTransaction) {
		this.apiProviderPaymentTransaction = apiProviderPaymentTransaction;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

}
