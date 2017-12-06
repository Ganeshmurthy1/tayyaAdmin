package com.lintas.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.OrderCustomerDao;
import com.lintas.admin.common.model.OrderCustomer;
import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class OrderCustomerRelationAction extends ActionSupport implements SessionAware  {
	 /**
	 * @author info raham
	 * created date:17/10/2015
	 */ 
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(OrderCustomerRelationAction.class);
	SessionMap<String, Object>  sessionMap;
	OrderCustomerDao orderCustomerDao =new OrderCustomerDao();
	OrderCustomer orderCustomer=new OrderCustomer();
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String countryId;
	private String gender;
	private String result="";
	
	private Map<String,String> jsonobj  =  new HashMap<String, String>();
	 
	
	
/*	this method for fetching all order customer list */
	public String goOrderCustomerList(){
		sessionMap.put("OrderCustomerList", orderCustomerDao.getOrderCustomersList()); 
		return SUCCESS;
	 }
	
	/*	this method for update  order customers based on  customer id */
	public String orderCustomersUpdate(){
		
		logger.info("-------------Id---------------"+getId());
		logger.info("-------------Firstname---------------"+getFirstName());
		logger.info("-------------LastName---------------"+getLastName());
		logger.info("-------------Email---------------"+getEmail());
	
		logger.info("-------------Gender---------------"+getGender());
	 
		try{
		orderCustomer.setId(getId());
		orderCustomer.setFirstName(getFirstName());
		orderCustomer.setLastName(getLastName());
		orderCustomer.setEmail(getEmail());
		orderCustomer.setMobile(getMobile());
		orderCustomer.setCountryId(getCountryId());
		orderCustomer.setGender(getGender());
		
		if (orderCustomerDao.updateOrderCustomerDetails(orderCustomer)) {
			
			
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(orderCustomer.getId()),"CRM passenger profile update  click ");
				
			result="success";
			
		}
		 else
		 {
			 
			 result="fail";
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(orderCustomer.getId()),"CRM passenger profile update  click ");
				
		 }
		logger.info("-------------result---------------"+result);
		jsonobj.put("result",result);
		}catch(Exception e){
			logger.error("-------------Exception---------------"+e.getMessage());
		}

		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(orderCustomer.getId()),"CRM passenger profile update  click ");
			
		 return SUCCESS;
	 }
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	 
	 
	/**
	 * @return the jsonResult
	 */
	public Map<String,String> getJsonResult() {
		return jsonobj;
	}

	/**
	 * @param jsonResult the jsonResult to set
	 */
	public void setJsonResult(Map<String,String> jsonResult) {
		this.jsonobj = jsonResult;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

}
