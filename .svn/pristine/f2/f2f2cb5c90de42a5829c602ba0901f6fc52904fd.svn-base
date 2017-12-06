package com.lintas.admin.json;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.admin.knockoff.common.filter.page.KnockOffUtility;
import com.admin.knockoff.dao.KnockOffDao;
import com.admin.knockoff.dao.KnockOffDaoImpl;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.admin.model.Email;
import com.opensymphony.xwork2.ActionSupport;

public class SendInvoiceToMailAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SendInvoiceToMailAction.class);
	KnockOffDao knockOffDao=new KnockOffDaoImpl();
	private String customerMail;
	private String htmlMessage;
	private String orderId;
	private String companyType;
	private String status="fail";
	private long rowId;
	private String travelType;
	private BigDecimal totInvoiceAmount;
	private BigDecimal receiveAmount;
	private int companyEntityId;
	private String companyEntityGstIn;
	private String companyname;
	private String companyEntityName;
	private String email;
	private String countryname;
	private String state;
	private String city;
	private String phone;
	private String address1;
	private String address2;
	private Map<String,String> jsonobj  =  new HashMap<String, String>();
	public String updateCompanyEntity(){
		//CompanyEntity companyEntity=new CompanyDAO().companyEntityProfile(companyEntityId);
	//System.out.println("companyEntity----------"+companyEntity);
		CompanyEntity companyEntity = new CompanyEntity();
	companyEntity.setAddress1(address1);
	companyEntity.setAddress2(address2);
	companyEntity.setCompanyEntityId(companyEntityId);
	companyEntity.setCity(city);
	companyEntity.setCompanyEntityGstIn(companyEntityGstIn);
	companyEntity.setCompanyEntityName(companyEntityName);
	companyEntity.setCompanyname(companyname);
	companyEntity.setCountryname(countryname);
	companyEntity.setEmail(email);
	companyEntity.setPhoneNo(phone);
	String [] arr=state.split("-");
	companyEntity.setState(arr[0]);
	companyEntity.setStateCode(arr[1]);
	//CompanyEntity companyEntityNew=new  CompanyEntity(companyEntityId, companyEntityGstIn, companyname, companyEntityName, countryname, address1, address2, email, city, state, phone,companyEntity.getCompany()!=null?companyEntity.getCompany():new Company(),companyEntity.getStateCode());
		boolean isupdated=new CompanyDAO().updateCompanyEntity(companyEntity);
		System.out.println("isupdated----------"+isupdated);
		if(isupdated)
			status="success";
		jsonobj.put("status", status);
		return SUCCESS; 
	}
 
	public String deleteCompanyEntity(){
		boolean isDeleted=new CompanyDAO().deleteCompanyEntity(companyEntityId);
		System.out.println("isDeleted----------------"+isDeleted);
		if(isDeleted)
			status="success";
		jsonobj.put("status", status);
		return SUCCESS; 
	}
	public String sendCustomerInvoiceToMail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null && getCompanyType()!=null){
				if(getCompanyType().equals("super")){
					updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_SUPER_INVOICE_TO_OTHERS);  
				}
				else if(getCompanyType().equals("dis")){
					updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_DIS_INVOICE_TO_OTHERS);  
				}
				else if(getCompanyType().equals("others")){
					updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_CUSTOMER_INVOICE_FLIGHT);  
				}
				/*else if(getCompanyType().equals("others")){
					updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_FLIGHT_INVOICE );  
				}*/
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}

	public String sendHotelAgentInvoiceToMail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null && getCompanyType()!=null){
				if(getCompanyType().equals("super")){
					updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_HOTEL_INVOICE_SUPER_TO_OTHERS);  
				}
				else if(getCompanyType().equals("dis")){
					updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_HOTEL_INVOICE_DISTRIBUTOR_TO_OTHERS);  
				}
				/*else if(getCompanyType().equals("others")){
					updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_HOTEL_INVOICE_DISTRIBUTOR_TO_OTHERS);  
				}*/
				else if(getCompanyType().equals("others")){
					updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_CUSTOMER_INVOICE_HOTEL);  
				} 
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}

	public String sendFlightVoucherToCustomerEmail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_FLIGHT_VOUCHER);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}

				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}

	public String sendFlightInvoiceToCustomerEmail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_FLIGHT_OFFLINE_ONLINE_INVOICE_SEND);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}

				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}



	/*added by basha*/
	public String sendTrainInvoiceToCustomerEmail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null ){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_TRAIN_INVOICE);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}
	public String sendCarInvoiceToCustomerEmail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null ){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_CAR_INVOICE);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}

	public String sendBusInvoiceToCustomerEmail(){

		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null ){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_BUS_INVOICE);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}

	public String sendVisaInvoiceToCustomerEmail(){
		logger.info("----sendVisaInvoiceToCustomerEmail---------Order Id---------------"+getOrderId());

		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null ){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_VISA_INVOICE);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}

	public String sendInsuranceInvoiceToCustomerEmail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null ){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_INSURANCE_INVOICE);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}

	public String sendMiscellaneousInvoiceToCustomerEmail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null ){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_MISC_INVOICE);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}
	
	/*added by basha*/

	
	public String sendHotelVoucherToCustomerEmail(){
		logger.info("----sendHotelVoucherToCustomerEmail---------Order Id---------------"+getOrderId());

		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null ){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_HOTEL_VOUCHER);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}
	public String sendHotelInvoiceToCustomerEmail(){
		//To Do code for invoice emails need to send out 
		try {
			Email updateStatus = null;
			if(getOrderId()!=null ){
				updateStatus=new CompanyDAO().insertEmail(getOrderId(),0, Email.EMAIL_TYPE_HOTEL_OFFLINE_ONLINE_INVOICE_SEND);  
				if(updateStatus!=null && updateStatus.getId()>0){
					status="success";
				}
				jsonobj.put("status", status);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("--------MessagingException--------------"+e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS; 
	}


	public String updateKnockOff(){
		boolean isUpdated=false;
		KnockOffUtility knockOffUtility=new KnockOffUtility(rowId, travelType, totInvoiceAmount, receiveAmount);
		switch (travelType) {
		case "F":
			isUpdated=knockOffDao.flightKnockOffUpdate(knockOffUtility);
			if(isUpdated)
				status="success";
			break;
		case "H":
			isUpdated=knockOffDao.hotelKnockOffUpdate(knockOffUtility);
			if(isUpdated)
				status="success";
			break;
		case "C":
			isUpdated=knockOffDao.carKnockOffUpdate(knockOffUtility);
			if(isUpdated)
				status="success";
			break;
		case "B":
			isUpdated=knockOffDao.busKnockOffUpdate(knockOffUtility);
			if(isUpdated)
				status="success";
			break;
		case "T":
			isUpdated=knockOffDao.trainKnockOffUpdate(knockOffUtility);
			if(isUpdated)
				status="success";
			break;

		case "V":
			isUpdated=knockOffDao.visaKnockOffUpdate(knockOffUtility);
			if(isUpdated)
				status="success";
			break;

		case "I":
			isUpdated=knockOffDao.insuranceKnockOffUpdate(knockOffUtility);
			if(isUpdated)
				status="success";
			break;
		default:
			break;
		}
		jsonobj.put("status", status);
		return SUCCESS;

	}

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}


	public String getHtmlMessage() {
		return htmlMessage;
	}

	public void setHtmlMessage(String htmlMessage) {
		this.htmlMessage = htmlMessage;
	}

	/**
	 * @return the jsonobj
	 */
	public Map<String,String> getJsonobj() {
		return jsonobj;
	}

	/**
	 * @param jsonobj the jsonobj to set
	 */
	public void setJsonobj(Map<String,String> jsonobj) {
		this.jsonobj = jsonobj;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getStatus() {
		return status;
	}

	public long getRowId() {
		return rowId;
	}

	public String getTravelType() {
		return travelType;
	}

	public BigDecimal getTotInvoiceAmount() {
		return totInvoiceAmount;
	}

	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public void setTotInvoiceAmount(BigDecimal totInvoiceAmount) {
		this.totInvoiceAmount = totInvoiceAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public int getCompanyEntityId() {
		return companyEntityId;
	}

	public void setCompanyEntityId(int companyEntityId) {
		this.companyEntityId = companyEntityId;
	}

	public String getCompanyEntityGstIn() {
		return companyEntityGstIn;
	}

	public void setCompanyEntityGstIn(String companyEntityGstIn) {
		this.companyEntityGstIn = companyEntityGstIn;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanyEntityName() {
		return companyEntityName;
	}

	public void setCompanyEntityName(String companyEntityName) {
		this.companyEntityName = companyEntityName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	 

}
