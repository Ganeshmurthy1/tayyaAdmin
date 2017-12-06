package com.admin.hotel.fin.sheet.model;


public enum TravelRequestStatusEnum {
	DEFAULT(0,"Default"),CREATED(1,"Created"),QSENT(2,"QuotationSent"),APPROVED(3,"Approved"),SHORTLISTED(4,"ShortListed"),BOOKED(5,"Booked"),CANCELLED(6,"Cancelled") ;
	private int value; 
	private String status; 
	TravelRequestStatusEnum(int id, String status) {
		this.value = id;
		this.setStatus(status);

	}
	public static String getBrowsingHistoryDetailTypeEnum(int statusId) {
		String defaultStatus = "";
		 
		for (TravelRequestStatusEnum bd : TravelRequestStatusEnum.values())
		{
			if(bd.getValue() == statusId)
			{
				defaultStatus=bd.getStatus();
				break;
			}		
		}

		return defaultStatus; 

	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	/*public static void main(String[] args) {
		TravelRequestStatusEnum.getBrowsingHistoryDetailTypeEnum(0);
	}*/
}


