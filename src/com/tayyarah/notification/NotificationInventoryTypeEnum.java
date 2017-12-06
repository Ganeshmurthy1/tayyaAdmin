package com.tayyarah.notification;

public enum NotificationInventoryTypeEnum {
	/**
	 * @author Ramesh
	 */

	DEFAULT (0, "default", "default type","default.jpg",""),
	USER (1, "user", "user item","employee.png","agentDetails"),
	USER_WALLET (2, "wallet", "user wallet item","wallet.png","TransactionDetail"),
	COMPANY (3, "company", "company item","newcompany.png","companyDetails"),
	COMPANY_CONFIG (4, "company config", "company config item","company_config.png","companyConfigProfile"),	
	PASSENGER(5, "passenger Details", "passenger details","passenger.jpg",""),
	HOTEL_ORDER (6, "hotel order", "hotel order info","hotelorder.png","showReportDetails"),
	HOTEL_ROOM_DETAILS(7, "hotel room detail", "hotel room detail info","hotelroom.jpg",""),	
	HOTEL_QUOATATION(8, "hotel quoatation", "hotel quoatation info","hotelquote.png","getHotelRequestTravelQuotationList"),	
	FLIGHT_ORDER(9,"flight order","flight order info","flightorder.png","showPassengerDetails"),
	FLIGHT_QUOATATION(10, "flight quoatation", "flight quoatation info","flightquote.jpg",""),	
	CMS(11,"cms","cms info","cms.png","CmsDetails"),
	PAYMENT_PENDING(12,"payment pending","payment pending alert","payalert.jpg",""),
	B2CUSER (13, "B2cuser", "user item","b2cuser.png","UserDetail"),
	COMPANY_APPROVAL (14, "company approval", "company item","company_approval.png","companyDetails"),
	FLIGHT_MARKUP (15, "mark up", "markup item","markup.png","markupProfile"),
	HOTEL_MARKUP (16, "mark up", "markup item","markup.png","hoteMarkupProfile"),
	HOTEL_BOOKREQUEST (17, "hotel request", "Hotel item","hotelbookrequest.png","hotelOrderQuotationView"),
	
	HOTEL_CUSTOMER_PAYMENT_NOTIFICATION_ALERT(50, "hotel customer payment notification", "hotel customer payment notification alert","walletalert.png","hotelCustomerPaymentNotificationView"),
	HOTEL_SUPPLIER_PAYMENT_NOTIFICATION_ALERT(51, "hotel supplier payment notification", "hotel supplier payment notification alert","walletalert.png","hotelSupplierPaymentNotificationView"),
	FLIGHT_CUSTOMER_PAYMENT_NOTIFICATION_ALERT(52, "flight customer payment notification", "flight customer payment notification alert","walletalert.png","flightCustomerPaymentNotificationView"),
	FLIGHT_SUPPLIER_PAYMENT_NOTIFICATION_ALERT(53, "flight supplier payment notification", "flight supplier payment notification alert","walletalert.png","flightSupplierPaymentNotificationView");
	
	
	
	//ADD_COMMON_CONFIG(16, "Common Config Reg", "Common Config Reg");
	private final Integer id;
	private final String title;  
	public final String description; 
	
	private final String imagename; 
	private final String actionname;
	NotificationInventoryTypeEnum(Integer id, String title, String description,String imagename,String actionname) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.imagename = imagename;
		this.actionname = actionname;
	}
	public static NotificationInventoryTypeEnum getBrowsingHistoryDetailTypeEnum(Integer detailId) {

		NotificationInventoryTypeEnum browsingHistoryDetailTypeEnum = DEFAULT;
		if(detailId == null)
			return DEFAULT;
		for (NotificationInventoryTypeEnum bd : NotificationInventoryTypeEnum.values())
		{
			if(bd.getId() == detailId)
			{
				browsingHistoryDetailTypeEnum = bd;
			}		
		}
		return browsingHistoryDetailTypeEnum; 
	}

	public Integer getId() { return id; }
	public String getTitle() { return title; }
	public String getImagename() {
		return imagename;
	}
	public String getLink() { 
		return description; 
		}
	public String getActionname() {
		return actionname;
	}
	 
}