package com.tayyarah.browsingHistory.model;

import java.util.ArrayList;
import java.util.List;

public enum BrowsingOptionPageEnum {
	DEFAULT (0, "No info", "index.jsp"),
	LOGIN (1, "login", "index.jsp"),
	LOGOUT (2, "logout", "http://all_compdfdffdf/fdfd"),
	HOME (3, "home", "dashboard.jsp"),
	FLIGHT_REPORT (4, "flight report", "http://all_compdfdffdf/fdfd"),	
	FLIGHT_ORDER (5, "flight order", "http://all_compdfdffdf/fdfd"),
	ADD_COMPANY (6, "add company", "http://all_compdfdffdf/fdfd"),
	ALL_COMPANY (7, "all company", "http://all_compdfdffdf/fdfd"),	
	NEW_COMPANY_APPROVALS  (8, "New Company Approval List", "http://getNonApprovalCompaniesList"),
	APPROVED_COMPANIES  (9, " Approved Companies", "http://getNonApprovalCompaniesList"),
	MY_DEAL_SHEET  (10, " My Deal Sheet", "http://My Deal Sheet"),
	ADD_AFFILIATE_DEAL_SHEET  (11, " Add Affiliate Deal Sheet", "http://My Deal Sheet"),
	AFFILIATE_DEAL_SHEET_LIST  (12, " Affiliate Deal Sheet List", "http://CommingSoon"),
	ALL_COMPANY_CONFIG_ALL  (13, " all Company Config", "http://CommingSoon"),

	ADD_NEWCOMPANY_CONFIG (14, " add new Company Config", "http://CommingSoon"),
	FLIGHT_MARKUP_LIST  (15, " flight markup list", "http://CommingSoon"),
	ADD_FLIGHT_MARKUP  (16, " add flight markup", "http://CommingSoon"),
	HOTEL_MARKUP_LIST  (17, " hotel markup list", "http://CommingSoon"),
	ADD_HOTEL_MARKUP  (18, " add hotel markup", "http://CommingSoon"),
	ALL_EMPLOYEES  (19, " all employess", "http://CommingSoon"),
	ADD_NEW_EMPLOYEE  (20, " add new employee", "http://CommingSoon"),
	ADD_MYWALLET  (21, " manage add my wallet", "http://CommingSoon"),
	ADD_USERWALLET  (22, " Add user wallet", "http://CommingSoon"),
	MY_TRANSACTIONS  (23, " My Transactions", "http://CommingSoon"),
	FLIGHT_BOOKINGS_REPORT_LIST  (24, " Flight Bookings Report List", "http://CommingSoon"),
	FLIGHT_BOOKINGS_ORDER_LIST  (25, " Flight Bookings Order List", "http://CommingSoon"),
	FLIGHT_BOOKINGS_COMMISSION_REPORT  (26, " Flight Bookings Commission Report", "http://CommingSoon"),
	FLIGHT_BOOKINGS_CUSTOMER_INVOICE  (27, " Flight Bookings Customer Invoice", "http://CommingSoon"),
	FLIGHT_BOOKINGS_AGENT_COMMISSION_INVOICE  (28, " Flight Bookings Agent Commission Invoice", "http://CommingSoon"),

	HOTEL_BOOKINGS_REPORT_LIST  (29, " Hotel Bookings Report List", "http://CommingSoon"),
	HOTEL_BOOKINGS_ORDER_LIST  (30, " Hotel Bookings Order List", "http://CommingSoon"),
	HOTEL_BOOKINGS_COMMISSION_REPORT  (31, " Hotel Bookings Commission Report", "http://CommingSoon"),
	HOTEL_BOOKINGS_CUSTOMER_INVOICE  (32, " Hotel Bookings Customer Invoice", "http://CommingSoon"),
	HOTEL_BOOKINGS_AGENT_COMMISSION_INVOICE  (33, " Hotel Bookings Agent Commission Invoice", "http://CommingSoon"),

	CRM_PASSENGER_PROFILE  (34, " Passenger Profile", "http://CommingSoon"),
	CRM_FREQUENT_FLYER  (35, " Hotel Bookings Order List", "http://CommingSoon"),
	CRM_PHOTO_PASSPORT_VISA_DETAILS  (36, " photo passport and visa details", "http://CommingSoon"),
	CRM_EMERGENCY_CONTACT_DETAILS  (37, " Emergency Contact Details", "http://CommingSoon"),
	/*Master Configurations*/
	MASTER_CONFIGURATIONS_ADD_APIPROVIDER  (38, " Add ApiProvider", "http://CommingSoon"),
	MASTER_CONFIGURATIONS_APIPROVIDER_LIST  (39, " ApiProvider List", "http://CommingSoon"),
	MASTER_CONFIGURATIONS_ADD_COMMON_CONFIG  (40, " Add Common Config", "http://CommingSoon"),
	MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST  (41, " Common Config List", "http://CommingSoon"),
	MARKETING_SALES_LEADS_COMPANY_LIST  (42, " Company List", "http://CommingSoon"),
	HOTEL_FINAL_SHEET_ADD_HOTEL_FINAL_SHEET  (43, " Add Hotel Final Sheet", "http://CommingSoon"),
	SETTINGS_CMS_ADD_DEALS  (44, "Add Deals", "http://CommingSoon"),
	SETTINGS_CMS_SHOW_ALL_DEALS  (45, "Show All Deals", "http://CommingSoon"),
	SETTINGS_HOTELS_ADD_HOTEL  (46, "Add hotels", "http://CommingSoon"),
	SETTINGS_HOTELS_HOTEL_LIST  (46, "Hotel List", "http://CommingSoon"),
	SETTINGS_ADD_BUGTRACKER  (47, "Add BugTracker", "http://CommingSoon"),
	SETTINGS_BUGTRACKER_LIST  (48, "bugtraker list", "http://CommingSoon"),
	SETTINGS_ADD_AIRPORT  (49, "add airports", "http://CommingSoon"),
	SETTINGS_SHOW_ALL_AIRPORTS  (50, "all airports", "http://CommingSoon"),
	B2C_USERS (51, "b2c users", "http://CommingSoon"),
	API_API_PROVIDER_LIST (51, "Hotel List", "http://CommingSoon"),
	COMPANYCONFIG_EDIT(52, "Companyconfig edit", "http://CommingSoon"),
	ACTION_LOCK(53,"employee lock","http://commingoon"),
	WALLET_HISTORY  (54, " wallet history", "http://CommingSoon"),
	PASSENGER_DETAILS  (55, " passenger details", "http://CommingSoon"),
	HOTEL_REPORT (56, "hotel report", "http://all_compdfdffdf/fdfd"),
	HOTEL_ORDER (57, "hotel order", "http://all_compdfdffdf/fdfd");



	/*Hotel List
	,*/




	/*CRM_PASSENGER_PROFILE  (33, " Hotel Bookings Agent Commission Invoice", "http://CommingSoon"),*/



	private final Integer id;
	private final String title;   // in kilograms
	private final String link; // in meters
	BrowsingOptionPageEnum(Integer id, String title, String link) {
		this.id = id;
		this.title = title;
		this.link = link;
	}
	public Integer getId() { return id; }
	public String getTitle() { return title; }
	public String getLink() { return link; }

	public static BrowsingOptionPageEnum getPageEnum(Integer pageId) {

		BrowsingOptionPageEnum pageEnum = DEFAULT;
		if(pageId == null)
			return DEFAULT;
		for (BrowsingOptionPageEnum p : BrowsingOptionPageEnum.values())
		{
			if(p.getId() == pageId)
			{
				pageEnum = p;
			}		
		}
		return pageEnum; 
	}
	public static BrowsingOptionPageEnum getPageEnum(String pagename) {

		BrowsingOptionPageEnum pageEnum = DEFAULT;
		if(pagename == null)
			return DEFAULT;
		for (BrowsingOptionPageEnum p : BrowsingOptionPageEnum.values())
		{
			if(p.getTitle() == pagename)
			{
				pageEnum = p;
			}		
		}
		return pageEnum; 
	}
	
	public static List<EnumUtility>  getBrowsingPageNameList(){
		List<EnumUtility> pageList=new ArrayList<>();
		for (BrowsingOptionPageEnum p : BrowsingOptionPageEnum.values())
		{
			if(p.getId()!=0){ 
				EnumUtility enumUtility=new EnumUtility();
				enumUtility.setId(p.getId());
				enumUtility.setPageName(p.getTitle());
				pageList.add(enumUtility);
				  
			}
			}
		return pageList;
			
	}
	
	
	

	/* public static void main(String[] args) {
	 
		if (args.length != 1) {
			System.err.println("Usage: java Planet <earth_weight>");
			System.exit(-1);
		}

		for (BrowsingOptionPageEnum p : BrowsingOptionPageEnum.values())
			System.out.printf("your browsing option traversel",
					p, p.getLink());
	}*/
}