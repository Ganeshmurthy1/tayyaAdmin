var app = angular.module('BookApp',[]);
app.config(['$httpProvider', function( $httpProvider,$stateProvider, $urlRouterProvider) {    
	$httpProvider.defaults.useXDomain = true;  
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
}
]); 

app.controller('bookcontroller',['$scope', '$window','$http','$element',function($scope,$window,$http,$element){
	console.log('bookcontroller');

	var status =  $element.find('#status').val();
	$scope.pnr =  $element.find('#pnr').val();
	$scope.ticketprocessloader = false;
	$scope.bookingstatus = status;	
	
	$scope.bookingcomments = "";
	$scope.showmodel = function(requesttype){
		console.log(requesttype);
		if(requesttype == 'Confirm'){
			$scope.modeltitle = "Confirm Ticket";
			$scope.modelcontent = "Do you want to confirm your PNR ";
		}
		if(requesttype == 'Release'){
			$scope.modeltitle = "Release Ticket";
			$scope.modelcontent = "Do you want to release your PNR ";
		}
		if(requesttype == 'Proceed'){
			if($scope.modeltitle == 'Confirm Ticket'){
				$scope.ticketprocessloader = true;
				$scope.processcomments = "Please wait while we are confirm your PNR " + $scope.pnr;
				$scope.confirmticket();
			}
			if($scope.modeltitle == 'Release Ticket'){
				$scope.ticketprocessloader = true;
				$scope.processcomments = "Please wait while we are release your PNR " + $scope.pnr;
				$scope.cancelorreleaseticket();
			}
		}
	}

	$scope.confirmticket = function(){
		$scope.ticketprocessloader = true;
		var userid =  $element.find('#userid').val();
		var username =  $element.find('#username').val();	
		var appkey =  $element.find('#appkey').val();
		var transkey =  $element.find('#transkey').val();
		var pricekey =  $element.find('#pricekey').val();
		var orderid =  $element.find('#orderid').val();

		$http.get('resources/ApiResourceURLcredential.properties').then(function (response) {
			$scope.ApiURL = response.data.ApiResourceURL;
			var confirmdata = {"userid":userid,"username":decodeURIComponent(username),"paymode":"cash","transactionkey":transkey,"price_key":pricekey,"app_key":decodeURIComponent(appkey),"orderid":orderid,"requesttype":0};
			console.log($scope.ApiURL); 
			$http({method:'POST',url:$scope.ApiURL+'confirmticket/details',data: confirmdata,headers:{'Content-Type': 'application/json'}}).
			//$http.get('confirmticket.json').
			success(function(data, status, headers, config){
				//console.log(data); 			
				if( data.bookingComments == 'Confirmed'){
					$scope.bookingComments = data.bookstatus;
					$window.location.reload();
				}else{
					$scope.bookingComments = "Your PNR Not Confirmed. Please Try Again";
				}
			}).error(function(data, status, headers, config){ 
				$scope.bookingComments = "Your PNR Not Confirmed. Please Try Again";			
				$scope.ticketprocessloader = false;
			});

		});

	}

	$scope.cancelorreleaseticket = function(){
		$scope.ticketprocessloader = true;
		var userid =  $element.find('#userid').val();
		var username =  $element.find('#username').val();	
		var appkey =  $element.find('#appkey').val();
		var transkey =  $element.find('#transkey').val();
		var pricekey =  $element.find('#pricekey').val();
		var orderid =  $element.find('#orderid').val();
		// Method tyep 0 is for release hold booking
		// Method tyep 1 is for cancel ticket
		var canceldata = {"userId":userid,"password":"some","appKey":decodeURIComponent(appkey),"orderId":orderid,"remarks":"Test","methodtype":"0","requesttype":"1","cancellationtype":"3"}
		$http({method:'POST',url:$scope.ApiURL+'cancelticket/response',data: canceldata,headers:{'Content-Type': 'application/json'}}).

		success(function(data, status, headers, config){
			//console.log(data);
			if(data.bookstatus == 'Released'){
				$scope.bookingComments = data.bookstatus;
				$window.location.reload();
			}else{
				$scope.bookingComments = "Your PNR Not Released. Please Try Again";
			}

			$scope.ticketprocessloader = false;

		}).error(function(data, status, headers, config){ 
			//console.log(data);
			$scope.ticketprocessloader = false;
			$scope.bookingComments = "Your PNR Not Released. Please Try Again";
		});
	}

}]);