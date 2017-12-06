package com.hotel.cancellation;

import java.io.Serializable;

public class APIStatus implements Serializable{

	public APIStatus() {
		super();
		this.code = STATUS_CODE_ERROR;
		this.message = STATUS_MESSAGE_ERROR;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "APIStatus [code=" + code + ", message=" + message + "]";
	}
	public static final String STATUS_CODE_SUCCESS = "1";
	public static final String STATUS_CODE_ERROR= "0";
	public static final String STATUS_MESSAGE_SUCCESS = "Success";
	public static final String STATUS_MESSAGE_ERROR= "Error:";
	
	
	
	/*NotSet = 0,
	Pending = 1,
	InProgress = 2,
	Processed = 3,
	Rejected = 4*/
	
	public static final String STATUS_CODE_CANCEL_NOT_SET = "0";
	public static final String STATUS_CODE_CANCEL_PENDING= "1";
	public static final String STATUS_CODE_CANCEL_IN_PROCESS = "2";
	public static final String STATUS_CODE_CANCEL_PROCESSED = "3";
	public static final String STATUS_CODE_CANCEL_REJECTED = "4";
	
	public static final String STATUS_MESSAGE_CANCEL_NOT_SET = "Cancellation Not set";
	public static final String STATUS_MESSAGE_CANCEL_PENDING= "Cancellation Pending";
	public static final String STATUS_MESSAGE_CANCEL_IN_PROGRESS = "Cancellation In Progress";
	public static final String STATUS_MESSAGE_CANCEL_PROCESSED = "Cancellation Processed";
	public static final String STATUS_MESSAGE_CANCEL_REJECTED = "Cancellation Rejected";
	
	
	public APIStatus(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String code;
	private String message;
}
