/*var app = angular.module('NotifyAPP', ['ui-notification']);
app.config(function(NotificationProvider) {
    NotificationProvider.setOptions({ 
    	delay:290000, 
    	startTop: 20,
        startRight: 10,
        verticalSpacing: 10,
        horizontalSpacing: 10,
        positionX: 'right',
        positionY: 'bottom', 
          
    });
    

    
}); 


app.controller('headerController',function($scope,$http,$window,$filter,Notification,$timeout,$location) {
	$scope.init = function(){
		var DEFAULT = 0;
		var USER = 1;
		var USER_WALLET = 2;
		var COMPANY = 3;
		var COMPANY_CONFIG = 4;
		var PASSENGER = 5;
		var HOTEL_ORDER = 6;
		var HOTEL_ROOM_DETAILS = 7 ;	
		var HOTEL_QUOATATION = 8;
		var FLIGHT_ORDER = 9;
		var FLIGHT_QUOATATION = 10;
		var CMS = 11;
		var PAYMENT_PENDING = 12;
		var B2CUSER = 13;	
		var COMPANY_APPROVAL = 14;
		var FLIGHT_MARKUP = 15;
		var HOTEL_MARKUP = 16;
		var HOTEL_BOOKREQUEST = 17;
		var  NEW_NOTIFICATION = 18;


		var FLIGHT_MARKUPCREATE = 'Flight markup created';
		var FLIGHT_MARKUPUPDATE = 'Flight markup updated';
		var HOTEL_MARKUPCREATE = 'Hotel markup created';
		var HOTEL_MARKUPUPDATE = 'Hotel markup updated';
		var COMPANY_CREATE = 'Company Registered';
		var COMPANY_UPDATE = 'Company details updated';
		var COMPANY_CONFIG_CREATE = 'Company Configuration Created';
		var COMPANY_CONFIG_UPDATE = 'Company Configuration Updated';
		var USER_WALLET_CREDIT = 'Wallet balance Credited';
		var USER_WALLET_DEBIT = 'Wallet balance Debited';
		var USER_CREATE = 'Employee created';
		var USER_UPDATE = 'Employee updated';
		var FLIGHT_BOOK = 'Flight Ticket Booking';
		var FLIGHT_HOLD = 'Flight Ticket Hold';	
		var FLIGHT_CANCEL = 'Flight Ticket Cancel';
		var FLIGHT_RELEASE = 'Flight Ticket Release';
		var HOTEL_BOOK = 'Hotel Booking';
		var HOTEL_CANCEL = 'Hotel Cancel';
		var CREATE_NEW_NOTIFICATION = 'New Notification ';
		
		var currentUrl=$(location).attr('href');
		var baseUrl=currentUrl.substr(0,currentUrl.lastIndexOf('/')+1);
		var userId=$("#notiCreatedUserId").val();		 
		var companyId=$("#notiCreatedCompanyId").val();
	}
	
	
	
	$scope.init();
});*/