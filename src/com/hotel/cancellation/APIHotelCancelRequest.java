package com.hotel.cancellation;

import java.io.Serializable;
 public class APIHotelCancelRequest implements Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;	
	private String password;
	private String appKey;
	private String orderId;
	private String remarks;
	
	private String methodType;
	
	public static final String METHOD_INITIATE = "0";
	public static final String METHOD_GET_STATUS = "1";
	
	
	public APIHotelCancelRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
}
