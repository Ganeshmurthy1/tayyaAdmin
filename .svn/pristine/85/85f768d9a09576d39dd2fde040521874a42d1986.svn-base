package com.tayyarah.browsingHistory.model;

public enum BrowsingOptionActionEnum {

	
	ACTION_DEFAULT (0, "default", "index.jsp"),
	ACTION_LOGIN (1, "login", "index.jsp"),
	ACTION_LOGOUT (2, "logout", "http://all_compdfdffdf/fdfd"),
	ACTION_FORGOT_PASSWORD (3, "forgot password", "http://all_compdfdffdf/fdfd"),
	ACTION_RESET (4, "Password reset", "dashboard.jsp"),
	ACTION_ALLCOMPANY (5, "all company", "allcompany.jsp"),
	ACTION_DASHBOARD_FLIGHT_TODAY (6, "Dashboard today", "http://dashboard"),
	ACTION_DASHBOARD_FLIGHT_WEEK (7, "Dashboard week", "http://dashboard"),
	ACTION_DASHBOARD_FLIGHT_MONTH (8, "Dashboard month", "http://dashboard"),
	ACTION_DASHBOARD_FLIGHT_CONFIRMED_MORE_INFO (9, "Dashboard flight confirmed more info", "http://dashboard"),
	ACTION_DASHBOARD_FLIGHT_PAYMENT_SUCESS_MORE_INFO (10, "Dashboard sucess", "http://dashboard"),
	ACTION_DASHBOARD_FLIGHT_PAYMENT_FAILURE_MORE_INFO (11, "Dashboard failure", "http://dashboard"),
	ACTION_DASHBOARD_HOTEL_TODAY (12, "Dashboard today", "http://dashboard"),
	ACTION_DASHBOARD_HOTEL_WEEK (13, "Dashboard week", "http://dashboard"),
	ACTION_DASHBOARD_HOTEL_MONTH (14, "Dashboard month", "http://dashboard"),
	ACTION_DASHBOARD_HOTEL_CONFIRMED_MORE_INFO (15, "Dashboard hotel confirmed more info", "http://dashboard"),
	ACTION_DASHBOARD_HOTEL_PAYMENT_SUCESS_MORE_INFO (16, "Dashboard hotel sucess", "http://dashboard"),
	ACTION_DASHBOARD_HOTEL_PAYMENT_FAILURE_MORE_INFO (17, "Dashboard hotel failure", "http://dashboard"),
	ACTION_DASHBOARD_DISTRIBUTER (18, "Dashboard distributer", "http://dashboard"),
	ACTION_DASHBOARD_AGENCIES (19, "Dashboard agencies", "http://dashboard"),
	ACTION_FILTER (20, "Filter", "http://dashboard"),
	ACTION_ADDNEW (21, "add new", "http://dashboard"),
	ACTION_FILTER_SUBMIT (22, "add filter submit", "http://dashboard"),
	ACTION_VIEW (23, "view", "http://dashboard"),
	ACTION_EDIT (24, "edit", "http://dashboard"),
	ACTION_LOCK (25, "lock", "http://dashboard"),
	ACTION_UNLOCK (26, "unlock", "http://dashboard"),
	ACTION_REGISTER (27, "register", "http://dashboard"),


	ACTION_APPROVED_COMPANIES (28, "approved companies", "http://dashboard"),
	ACTION_DETAILS (29, "details", "http://dashboard"),
	ACTION_APPROVE (30, "approve", "http://dashboard"),
	ACTION_NEW_COMPANIES (31, "new companies", "http://dashboard"),
	ACTION_DISAPPROVE (32, "disapprove", "http://dashboard"),
	ACTION_CREATE_SHEET (33, "create sheet", "http://dashboard"),
	ACTION_DUPLICATE (34, "duplicate", "http://dashboard"),
	ACTION_EDIT_COMMISIONS (35, "edit commisions", "http://dashboard"),
	ACTION_CREATE_BLOCK (36, "create block", "http://dashboard"),
	ACTION_ADD (37, "add", "http://dashboard"),
	ACTION_ADD_WALLET (38, "add wallet", "http://dashboard"),
	ACTION_FAILED_WALLET (39, "wallet failed", "http://dashboard"),


	ACTION_ADD_UPDATE (40, "update", "http://dashboard"),
	ACTION_DELETE (41, "delete", "http://dashboard"),
	ACTION_SEARCH (42, "search", "http://dashboard"),
	ACTION_ADD_MYWALLET (43, "addmywallet", "http://dashboard"),
	ACTION_TXT_HISTORY(44, "transaction history", "http://dashboard"),
	FLIGHT_REPORTS(45, "flight reports", "http://dashboard"),
	PASSENGER_DETAILS(46, "passenger details", "http://dashboard"),
	FLIGHT_ORDER_DETAILS(47, "flight order details", "http://dashboard"),
	FLIGHT_AGENT_COMMISSION_REPORT(48, "flight agent commision report","http://dashboard"),
	FLIGHT_CUSTOMER_ORDER_INVOICE_LIST(48, "flight customer order invoicelist", "http://dashboard"),
	FLIGHT_CUSTOMER_INVOICE(49,"flight customer invoice","http://dashbord"),
	FLIGHT_AGENT_COMMISION_INVOICE(50,"flight agent commisioninvoice","http://dashbord"),
	HOTEL_REPORTS(51, "hotel reports", "http://dashboard"),
	COMPANY_HOTEL_ORDERS(52, "hotel orders", "http://dashboard"),
	HOTEL_COMMISION_REPORT(53,"hotel commision report","http://dashboard"),
	HOTEL_ORDER_INVOICE_LIST(53,"hotel order invoice list","http://dashboard"),
	HOTEL_CUSTOMER_INVOICE(54,"hotel customer invoice","http://dashboard"),
	AFFILIATE_HOTEL_ORDER_INVOICE_LIST(55,"affiliate hotel order invoicelist","http://dashboard"),
	HOTEL_AGENT_COMMISION_INVOICE(56,"hotel agent commisioninvoice","http://dashboard"),
	ACTION_UPDATE (57, "update", "http://dashboard"),
	CMS_ADD_DEALS(59,"add deals","http://dashboard"),
	HOTEL_DETAILS(60,"hoteldetails","http://dashboard"),
    CMS_PUBLISH(61,"cms publish","http://dashboard"),
   
    ACTION_SUBMIT(63,"action submit","action submit"),
    SETTINGS_ADDHOTEL_GOHOTELS_CREATEROOM(64,"create room","http://dashboard"),
    SETTINGS_ADDHOTEL_GOHOTELS_CREATEHOTEL(65,"create HOTEL","http://dashboard"),
    ACTION_EMAIL (66, "email", "http://dashboard");
	
	
	private final Integer id;
	private final String title;  
	private final String link; 
	BrowsingOptionActionEnum(Integer id, String title, String link) {
		this.id = id;
		this.title = title;
		this.link = link;
	}
	public static BrowsingOptionActionEnum getActionEnum(Integer actionId) {

		BrowsingOptionActionEnum actionEnum = ACTION_DEFAULT;
		if(actionId == null)
			return ACTION_DEFAULT;
		for (BrowsingOptionActionEnum a : BrowsingOptionActionEnum.values())
		{
			if(a.getId() == actionId)
			{
				actionEnum = a;
			}		
		}
		return actionEnum; 
		
	}
	
	public Integer getId() { return id; }
	public String getTitle() { return title; }
	public String getLink() { return link; }
	
	
}