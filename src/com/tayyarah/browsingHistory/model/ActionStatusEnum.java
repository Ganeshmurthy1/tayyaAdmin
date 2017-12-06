package com.tayyarah.browsingHistory.model;

public enum ActionStatusEnum {
	
	DEFAULT (0, "Default"),
	SUCCESS (1, "success"),
	FAILED (2, "failed"),
	FAILED_WRONG_USER_ID (3, "invalid email id or password"),
	EXCEPTION (4, "Exception ");
	
	
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	private final Integer code;
	private final String message; // in kilograms	
	ActionStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;		
	}
	
	public static ActionStatusEnum getStatusEnum(Integer statusCode) {

		ActionStatusEnum statusEnum = DEFAULT;
		if(statusCode == null)
			return DEFAULT;
		for (ActionStatusEnum s : ActionStatusEnum.values())
		{
			if(s.getCode() == statusCode)
			{
				statusEnum = s;
			}		
		}
		return statusEnum; 
		
	}
	
	
		
	
}