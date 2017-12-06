package com.tayyarah.browsingHistory.model;

public enum BrowsingHistoryDetailTypeEnum {
	 DEFAULT (0, "default", "default type"),
	 USER (1, "user", "user item"),
	 USER_WALLET (2, "wallet", "user wallet item"),
	 COMPANY (3, "company", "company item"),
	 COMPANY_CONFIG (4, "company config", "company config item"),
	 COMPANY_VIEW (5, "company view", "company view item"),
	 COMPANY_EDIT (6, "company Edit", "company Edit item"),
	 COMPANY_LOCK (7, "company Lock", "company Lock item"),
	 COMPANY_UNLOCK (8, "company Unlock", "company unlock item"),
	 UPDATE (9, "update", "update item"),
	 SEARCH (10, "Search", "Search item"),
	 ADD_WALLET (11, "Add Wallet", "Add Wallet item"),
	 COMPANY_DETAILS(12, "Company Details", "Company details"),
	 FILTER_SUBMIT (13, "Filter submit", "filter submit item"),
	 ADD_NEW(14, "Add new", "Add new item"),
	 APPROVED_COMPANIES(15, "APPROVED Companies", "Approved companies item"),
	 COMPANYCONFIG_EDIT (16, "company configEdit", "companyconfig Edit item"),
	 FAILED (17, "failed", "update wallet failed"),
	 PASSENGER_DETAILS(18, "passenger Details", "passenger details"),
	 HOTEL_ORDERS_EDIT (19,"hotel orders edit","hotel orders edit"),
	 DELETE(20, "delete", "delete item"),
	 ADD(21,"add","add item"),
	 EDIT(22,"edit","edit item"),
	 ADDBUGHISTORY(23,"addbughistory","add bug history"),
	 ADDCMS(24,"addCMS","add CMS"),
	HOTEL_ROOMDETAILS(25,"room details","hotel room details "),
	UPDATECMS(26,"update cms","CMS deals update"),
	CMS_ACTIVE(27,"cms active ","list of deals pulish status"),
	CMS_INACTIVE(28,"cms inactive ","list of deals pulish status"),
	RESET_PASSWORD(29,"reset password","reset password"),
	 REGISTER(30,"register","register"),
	SUBMIT(31,"submit","submit"),
	GOHOTELS_CREATEROOM(32,"create room","create room in add hotels"),
	GOHOTELS_CREATEHOTEL(33,"create hotel","create hotel in add hotels"),
	DETAIL(34,"detail", "detail"),
	EMAIL(35,"email", "email details");
	
	//ADD_COMMON_CONFIG(16, "Common Config Reg", "Common Config Reg");
	
	
	
	
	private final Integer id;
	private final String title;  
	private final String description; 
	BrowsingHistoryDetailTypeEnum(Integer id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	public static BrowsingHistoryDetailTypeEnum getBrowsingHistoryDetailTypeEnum(Integer detailId) {

		BrowsingHistoryDetailTypeEnum browsingHistoryDetailTypeEnum = DEFAULT;
		if(detailId == null)
			return DEFAULT;
		for (BrowsingHistoryDetailTypeEnum bd : BrowsingHistoryDetailTypeEnum.values())
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
	public String getLink() { return description; }
	
	
}