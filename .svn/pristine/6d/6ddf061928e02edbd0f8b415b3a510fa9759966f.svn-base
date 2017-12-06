/**
 * 
 */
package com.admin.payment.card.details;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manish kumar
 *
 */

public class PaymentCardInfoAction extends ActionSupport implements ModelDriven<PaymentCardDetailsConfig>, SessionAware{

	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	PaymentCardDetailsConfig cardDetailsConfig=new PaymentCardDetailsConfig();
	PaymentCardDetailsDao cardDetailsDao=new PaymentCardDetailsDao();
	private List<PaymentCardDetailsConfig> paymentCardDetailsConfigList=null;
	private InputStream inputStream;
	private List<Integer> months=new LinkedList<>();
	
	
public String goPaymentCardInfo(){
 return SUCCESS;
	}
	
	
	public String  updatePaymentCardDetails(){
		cardDetailsConfig.setExpiryDate(cardDetailsConfig.getCardExpireMonth()+"/"+cardDetailsConfig.getCardExpireYear());
		int length = String.valueOf(cardDetailsConfig.getCardNumber()).length();
		 
		 if(length!=4){
			 addActionError("Please must be provide last 4 digits of card.");
				return ERROR;	 
		 }
		 else{
			 PaymentCardDetailsConfig cardDetailsConfigNew=	cardDetailsDao.insertPaymentCard(cardDetailsConfig);
			 if(cardDetailsConfigNew.getId()>0){
				addActionMessage("Successfully Added.");
				return SUCCESS;
			}
			else{
				addActionError("We Found some Error.Please Wait.");
				return ERROR;	
			} 
		 }
		
	}

	public String fetchPaymentCardDetailsList(){
		List<PaymentCardDetailsConfig> paymentCardDetailsConfigs=cardDetailsDao.getAllPaymentCardDetailsList();
		if(paymentCardDetailsConfigs!=null && paymentCardDetailsConfigs.size()>0){
			setPaymentCardDetailsConfigList(paymentCardDetailsConfigs);
		}
		return SUCCESS;
	}
	public String getPaymentCardInfo(){
		
		PaymentCardDetailsConfig cardDetailsConfigNew=	cardDetailsDao.getPaymentCardInfo(cardDetailsConfig);
		if(cardDetailsConfigNew!=null){
			String [] array=cardDetailsConfigNew.getExpiryDate().split("/");
			cardDetailsConfigNew.setCardExpireMonth(array[0]);
			cardDetailsConfigNew.setCardExpireYear(array[1]);
			cardDetailsConfig=cardDetailsConfigNew;
		}
		return SUCCESS;
	}
	
	

	public String updatePaymentCardInfo(){
		cardDetailsConfig.setExpiryDate(cardDetailsConfig.getCardExpireMonth()+"/"+cardDetailsConfig.getCardExpireYear());
		int length = String.valueOf(cardDetailsConfig.getCardNumber()).length();
		if(length!=4){
			 addActionError("Please must be provide last 4 digits of card.");
				return ERROR;	 
		 }
		else{
			PaymentCardDetailsConfig cardDetailsConfigNew=cardDetailsDao.updatePaymentCardInfo(cardDetailsConfig);
			if(cardDetailsConfigNew!=null) 
			return SUCCESS;
			else{
				addActionError("We Found some Error.Please Wait.");
				return ERROR;	
			}
		}
		
		
		
	}
	
	public String DeletePaymentCardInfo(){
		Boolean bool=cardDetailsDao.deletePaymentCardInfo(cardDetailsConfig);
		if(bool){
			showSuccessMessage("deleted");
			return SUCCESS;
		}
		 else{
			 showSuccessMessage("failed");
				return ERROR;	
			}
	}
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}

	@Override
	public PaymentCardDetailsConfig getModel() {
		return cardDetailsConfig;
	}

	public List<PaymentCardDetailsConfig> getPaymentCardDetailsConfigList() {
		return paymentCardDetailsConfigList;
	}

	public void setPaymentCardDetailsConfigList(List<PaymentCardDetailsConfig> paymentCardDetailsConfigList) {
		this.paymentCardDetailsConfigList = paymentCardDetailsConfigList;
	}
	public PaymentCardDetailsConfig getCardDetailsConfig() {
		return cardDetailsConfig;
	}

	public void setCardDetailsConfig(PaymentCardDetailsConfig cardDetailsConfig) {
		this.cardDetailsConfig = cardDetailsConfig;
	}
	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}
	public InputStream getInputStream() {
		return inputStream;
	}

	public List<Integer> getMonths() {
		return months;
	}

	public void setMonths(List<Integer> months) {
		this.months = months;
	}
}
