package com.admin.hotel.fin.sheet.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.model.ApiProviderPaymentTransaction;
import com.admin.common.util.CommonUtilSession;
import com.admin.hotel.fin.sheet.Dao.HotelPaymentTxHistoryDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.common.model.HotelPaymentTxDetailHistory;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.notification.Notification;
import com.tayyarah.notification.NotificationDetail;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.NotificationStatusEnum;
import com.tayyarah.notification.dao.NotificationDao;

public class HotelPaymentNotificationAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HotelPaymentTxHistoryDao hotelPaymentTxHistoryDao=new HotelPaymentTxHistoryDao(); 
	NotificationDao notificationDao=new NotificationDao(); 
	private String transFromDate;
	private String transToDate;	
	private String transDueDate;
	private String transPaidDate;
	private String comments;
	private String toEmails;
	private String ccEmails;
	private String orderId;
	private String type;

	SessionMap<String, Object> sessionMap;
	private Map<String,String> statusMap=new HashMap<>();
	private Map<String,String> paymentMap=new HashMap<>();

	public String insertHotelPaymentTxHistory(){
		User sessionUser=(User)sessionMap.get("User");
		String tempTransTodate=getTransToDate();
		transToDate=DateConversion.convertDateToStringDateWithTimeDefault(DateConversion.StringToDate(getTransToDate()));
		setTransToDate(transToDate+" "+"23:59:00");
		HotelPaymentTxDetailHistory hotelPaymentTxDetailHistory=new HotelPaymentTxDetailHistory() ;
		Notification notification=new Notification() ;
		NotificationDetail notificationDetail=new NotificationDetail();
		PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(getOrderId());

		hotelPaymentTxDetailHistory.setFromDate(DateConversion.StringToDate(getTransFromDate()));
		hotelPaymentTxDetailHistory.setToDate(DateConversion.StringToDateTest(getTransToDate()));
		hotelPaymentTxDetailHistory.setPaidDate(DateConversion.StringToDate(getTransPaidDate()));
		hotelPaymentTxDetailHistory.setDueDate(DateConversion.StringToDate(getTransDueDate()));
		hotelPaymentTxDetailHistory.setComments(getComments());
		hotelPaymentTxDetailHistory.setPaymentTransaction(paymentTransaction);
		hotelPaymentTxDetailHistory=hotelPaymentTxHistoryDao.insertHotelPaymentTx(hotelPaymentTxDetailHistory);

		if(hotelPaymentTxDetailHistory!=null && hotelPaymentTxDetailHistory.getId()>0){
			notification.setCreatedAt(new Timestamp(new Date().getTime()));
			notification.setCompanyId(sessionUser.getCompanyid());
			notification.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
			notification.setCreatedby(sessionUser.getId());
			if(getType().equalsIgnoreCase("supplier")){
				notification.setDescription(NotificationInventoryTypeEnum.HOTEL_SUPPLIER_PAYMENT_NOTIFICATION_ALERT.description);
				notification.setStatusId(NotificationStatusEnum.STATUS_DEFAULT.getCode());
				notification.setType(NotificationInventoryTypeEnum.HOTEL_SUPPLIER_PAYMENT_NOTIFICATION_ALERT.getId());
				paymentMap.put("status",getType());
			}
			else if(getType().equalsIgnoreCase("client")){
				notification.setDescription(NotificationInventoryTypeEnum.HOTEL_CUSTOMER_PAYMENT_NOTIFICATION_ALERT.description);
				notification.setStatusId(NotificationStatusEnum.STATUS_DEFAULT.getCode());
				notification.setType(NotificationInventoryTypeEnum.HOTEL_CUSTOMER_PAYMENT_NOTIFICATION_ALERT.getId());
				paymentMap.put("status",getType());
			}
			else{
				notification.setDescription(NotificationInventoryTypeEnum.HOTEL_CUSTOMER_PAYMENT_NOTIFICATION_ALERT.description);
				notification.setStatusId(NotificationStatusEnum.STATUS_DEFAULT.getCode());
				notification.setType(NotificationInventoryTypeEnum.HOTEL_CUSTOMER_PAYMENT_NOTIFICATION_ALERT.getId()); 
			}

			notification.setFromDate(hotelPaymentTxDetailHistory.getFromDate());
			notification.setToDate(hotelPaymentTxDetailHistory.getToDate());
			notification.setIs_email(true);
			notification.setIs_admin(true);
			notification.setCustomFlag(true);
			notification.setCurrentNotificationView(false);
			notification.setIsExpired(false);
			notification.setTimeInterval(DateConversion.StringToDateHHMM(tempTransTodate));
			notification.setCcEmailAddress(getCcEmails());
			notification.setToEmailAddress(getToEmails());
			notification=notificationDao.insert(notification);
			if(notification!=null && notification.getId()>0){
				notificationDetail.setCreatedAt(notification.getCreatedAt());
				notificationDetail.setDescription(notification.getDescription());
				notificationDetail.setComments(hotelPaymentTxDetailHistory.getComments());
				notificationDetail.setInventoryId(String.valueOf(hotelPaymentTxDetailHistory.getId()));
				notificationDetail.setNotification(notification);
				notificationDetail.setType(notification.getType());
				notificationDetail=notificationDao.insertNotificationDetail(notificationDetail);
				if(notificationDetail!=null && notificationDetail.getId()>0){
					statusMap.put("status","1");
				}

			}
			else{
				statusMap.put("status","0");
			}
		}
		else{
			statusMap.put("status","0");
		}


		return SUCCESS;

	}


	public String insertHotelSupplierPaymentTxHistory(){
		User sessionUser=(User)sessionMap.get("User");
		String tempTransTodate=getTransToDate();
		transToDate=DateConversion.convertDateToStringDateWithTimeDefault(DateConversion.StringToDate(getTransToDate()));
		setTransToDate(transToDate+" "+"23:59:00");


		HotelPaymentTxDetailHistory hotelPaymentTxDetailHistory=new HotelPaymentTxDetailHistory() ;
		Notification notification=new Notification() ;
		NotificationDetail notificationDetail=new NotificationDetail();
		ApiProviderPaymentTransaction apiProviderPaymentTransaction=new HotelOrderDao().getApiProviderPaymentTransactionInfo(orderId);
		hotelPaymentTxDetailHistory.setFromDate(DateConversion.StringToDate(getTransFromDate()));
		hotelPaymentTxDetailHistory.setToDate(DateConversion.StringToDateTest(getTransToDate()));
		hotelPaymentTxDetailHistory.setPaidDate(DateConversion.StringToDate(getTransPaidDate()));
		hotelPaymentTxDetailHistory.setDueDate(DateConversion.StringToDate(getTransDueDate()));
		hotelPaymentTxDetailHistory.setComments(getComments());
		hotelPaymentTxDetailHistory.setApiProviderPaymentTransaction(apiProviderPaymentTransaction);
		hotelPaymentTxDetailHistory=hotelPaymentTxHistoryDao.insertHotelPaymentTx(hotelPaymentTxDetailHistory);
		if(hotelPaymentTxDetailHistory!=null && hotelPaymentTxDetailHistory.getId()>0){
			notification.setCreatedAt(new Timestamp(new Date().getTime()));
			notification.setCompanyId(sessionUser.getCompanyid());
			notification.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId());
			notification.setCreatedby(sessionUser.getId());
			notification.setDescription(NotificationInventoryTypeEnum.HOTEL_SUPPLIER_PAYMENT_NOTIFICATION_ALERT.description);
			notification.setStatusId(NotificationStatusEnum.STATUS_DEFAULT.getCode());
			notification.setType(NotificationInventoryTypeEnum.HOTEL_SUPPLIER_PAYMENT_NOTIFICATION_ALERT.getId());
			notification.setFromDate(hotelPaymentTxDetailHistory.getFromDate());
			notification.setToDate(hotelPaymentTxDetailHistory.getToDate());
			notification.setIs_email(true);
			notification.setIs_admin(true);
			notification.setCustomFlag(true);
			notification.setCurrentNotificationView(false);
			notification.setIsExpired(false);
			notification.setTimeInterval(DateConversion.StringToDateHHMM(tempTransTodate));
			notification.setCcEmailAddress(getCcEmails());
			notification.setToEmailAddress(getToEmails());
			notification=notificationDao.insert(notification);
			if(notification!=null && notification.getId()>0){
				notificationDetail.setCreatedAt(notification.getCreatedAt());
				notificationDetail.setDescription(notification.getDescription());
				notificationDetail.setComments(hotelPaymentTxDetailHistory.getComments());
				notificationDetail.setInventoryId(String.valueOf(hotelPaymentTxDetailHistory.getId()));
				notificationDetail.setNotification(notification);
				notificationDetail.setType(notification.getType());
				notificationDetail=notificationDao.insertNotificationDetail(notificationDetail);
				if(notificationDetail!=null && notificationDetail.getId()>0){
					statusMap.put("status","1");
				}
			}
			else{
				statusMap.put("status","0");
			}
		}
		else{
			statusMap.put("status","0");
		}
		return SUCCESS;

	}

	public String  sendOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Voucher"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_HOTEL_VOUCHER));
				else if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_HOTEL_OFFLINE_ONLINE_INVOICE_SEND));
				else if(getType().equalsIgnoreCase("CreditNote"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_HOTEL));

				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}

	public String getTransFromDate() {
		return transFromDate;
	}
	public void setTransFromDate(String transFromDate) {
		this.transFromDate = transFromDate;
	}
	public String getTransToDate() {
		return transToDate;
	}

	public void setTransToDate(String transToDate) {
		this.transToDate = transToDate;
	}

	public String getTransDueDate() {
		return transDueDate;
	}

	public void setTransDueDate(String transDueDate) {
		this.transDueDate = transDueDate;
	}

	public String getTransPaidDate() {
		return transPaidDate;
	}

	public void setTransPaidDate(String transPaidDate) {
		this.transPaidDate = transPaidDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) arg0;
	}

	public Map<String,String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String,String> statusMap) {
		this.statusMap = statusMap;
	}


	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getCcEmails() {
		return ccEmails;
	}


	public void setCcEmails(String ccEmails) {
		this.ccEmails = ccEmails;
	}


	public String getToEmails() {
		return toEmails;
	}


	public void setToEmails(String toEmails) {
		this.toEmails = toEmails;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Map<String,String> getPaymentMap() {
		return paymentMap;
	}


	public void setPaymentMap(Map<String,String> paymentMap) {
		this.paymentMap = paymentMap;
	}



	public String  sendCarOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_CAR_INVOICE));
				else if(getType().equalsIgnoreCase("CreditNote"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_CAR));

				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}

	public String  sendBusOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_BUS_INVOICE));
				else if(getType().equalsIgnoreCase("CreditNote"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_BUS));

				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}

	public String  sendVisaOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_VISA_INVOICE));
				else if(getType().equalsIgnoreCase("CreditNote"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_VISA));


				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}

	/*public String  sendMiscellaneousOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_MISC_INVOICE));
				else if(getType().equalsIgnoreCase("CreditNote"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_VISA));


				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}*/

	public String  sendTrainOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_TRAIN_INVOICE));
				else if(getType().equalsIgnoreCase("CreditNote"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_TRAIN));

				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}

	public String  sendInsurenceOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_INSURANCE_INVOICE));
				else if(getType().equalsIgnoreCase("CreditNote"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_INSURANCE));


				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}

	public String  sendMiscOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_MISC_INVOICE));

				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}

	public String  sendMealOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_MEAL_INVOICE));

				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS; 

	}

	public String  sendLabourOfflineVoucherOrInvoiceToMail(){
		CompanyDAO companyDAO=new CompanyDAO();
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				if(getType().equalsIgnoreCase("Invoice"))
					updateStatus= companyDAO.insertEmail(new Email(getOrderId(), 0, getToEmails(), getCcEmails(), Email.EMAIL_TYPE_LABOR_INVOICE));

				if(updateStatus!=null && updateStatus.getId()>0) 
					statusMap.put("status",getType());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS; 

	}


}
