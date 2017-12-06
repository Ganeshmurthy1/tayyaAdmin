package com.admin.hotel.fin.sheet.model;


public enum OfflineTravelRequestTypeEnum {
	DEFAULT (0, "No info", "index.jsp"),
	HOTEL_TRAVEL_REQUEST (1, "login", "index.jsp"),
	HOTEL_TRAVEL_RESPONSE (2, "login", "index.jsp"),
	HOTEL_QUOATATION_RESPONSE (3, "login", "index.jsp"),
	HOTEL_VOUCHER_RESPONSE (4, "login", "index.jsp"),
	FLIGHT_TRAVEL_REQUEST (5, "login", "index.jsp"),
	FLIGHT_TRAVEL_RESPONSE (6, "login", "index.jsp"),
	FLIGHT_QUOATATION_RESPONSE (7, "login", "index.jsp"),
	FLIGHT_VOUCHER_RESPONSE (8, "login", "index.jsp");
	
	
	//url/getpdf?orderids=(ordered/requested/quotationid)&userid=(userid)&type=(3 for HOTEL_QUOATATION_RESPONSE)
	
 
	private final Integer id;
	private final String title;   // in kilograms
	private final String description; // in meters
	OfflineTravelRequestTypeEnum(Integer id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	public Integer getId() { return id; }
	public String getTitle() { return title; }
	public String getDescription() { return description; }

	public static OfflineTravelRequestTypeEnum getPageEnum(Integer id) {

		OfflineTravelRequestTypeEnum offlineTravelRequestTypeEnum = DEFAULT;
		if(id == null)
			return DEFAULT;
		for (OfflineTravelRequestTypeEnum offlineTravelRequestTypeEnumTemp : OfflineTravelRequestTypeEnum.values())
		{
			if(offlineTravelRequestTypeEnumTemp.getId() == id)
			{
				offlineTravelRequestTypeEnum = offlineTravelRequestTypeEnumTemp;
			}		
		}
		return offlineTravelRequestTypeEnum; 
	}	
}