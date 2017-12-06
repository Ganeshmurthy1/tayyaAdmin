package com.tayyarah.notification;

public enum NotificationStatusEnum {
	/**
	 * @author Ramesh
	 */
	STATUS_DEFAULT (0, "Default"),
	STATUS_PENDING (1, "Pending"),
	STATUS_SENT (2, "Notification Sent"),
	STATUS_RECEIVED (3, "Notification Received"),
	STATUS_VIEWED (4, "Notification Viewed"),
	STATUS_VIEW_ERROR (5, "Notification View Error");
	
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	private final Integer code;
	private final String message; // in kilograms	
	NotificationStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;		
	}
	
	public static NotificationStatusEnum getStatusEnum(Integer statusCode) {

		NotificationStatusEnum statusEnum = STATUS_DEFAULT;
		if(statusCode == null)
			return STATUS_DEFAULT;
		for (NotificationStatusEnum s : NotificationStatusEnum.values())
		{
			if(s.getCode() == statusCode)
			{
				statusEnum = s;
			}		
		}
		return statusEnum; 
		
	}
	
}