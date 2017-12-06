<style>
.LimitedHeight {
	height: 186px;
	overflow-y: scroll;
	overflow-x: hidden;
}

.btn-blue {
	background: #1896d4;
	border-radius: 5px;
	font-size: 10px;
	color: #fff;
}
.small-box-footer{
text-decoration: underline;
color: white;
}

.small-box-footer :HOVER{
color: maroon;
font-weight:bold;
font-size: medium;
}
</style>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>


<!-- Content Wrapper. Contains page content -->
<s:if test="actionMsg!=null && actionMsg!='' ">
	<div class="succfully-updated clearfix" id="error-alert-main">
		<div class="col-sm-2">
			<i class="fa fa-check fa-3x"></i>
		</div>
		<div class="col-sm-10">
			<p>
				<s:property value="actionMsg" />
			</p>
			<button type="button" id="cancel-main" class="btn btn-primary">Ok</button>
		</div>
	</div>
</s:if>
<div class="content-wrapper" ng-app="airDataApp">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Dashboard <small>Insurance Panel</small> <i class="fa fa-medkit"></i>
		</h1>

	</section>
	<!-- Main content -->
	<section class="content overall">
		<!-- Small boxes (Stat box) -->
		<!--  Number of agents and distributers -->
		<div class="row" >
			<div class="col-sm-12 col-md-12">
				<!--  flightorder row -->
				<div class="row">
				 
			
					<div class=" col-md-4" ng-controller="BookingConfirmCtrl">
						<!-- small box -->
						<div class="small-box bg-green">
							<div class="inner">
								<h3 id="flightconfirmCount">
									<sup style="font-size: 20px"></sup>
								</h3>
								<p>
									Insurance Confirmed Orders(s)
								</p>
							</div>
							<div class="icon">
								<i class="fa fa-check"></i>
							</div>
							
							
							<div class="small-week small-box-footer">
								 <label class="btn btn-transparent grey-salsa btn-sm "><span style="color: aqua;font-size: 15px" >{{confirmedCount.countD}}</span>&nbsp;&nbsp;&nbsp;
								  <a href="showIndividualInsuranceReportList?showReportType=BC&filterUptoDate=D"
									class="small-box-footer" >  Today <i
										class="fa fa-arrow-circle-right"></i></a>
								</label> <label class="btn btn-transparent grey-salsa  btn-sm "><span style="color: aqua;font-size: 15px" >{{confirmedCount.countW}}</span>&nbsp;&nbsp;&nbsp;
									<a href="showIndividualInsuranceReportList?showReportType=BC&filterUptoDate=W"
									class="small-box-footer" >Week<i
										class="fa fa-arrow-circle-right"></i></a>
								</label> <label class="btn btn-transparent grey-salsa btn-sm "><span style="color: aqua;font-size: 15px" >{{confirmedCount.countM}}</span>&nbsp;&nbsp;&nbsp;
									<a href="showIndividualInsuranceReportList?showReportType=BC&filterUptoDate=M"
									class="small-box-footer" >Month <i
										class="fa fa-arrow-circle-right"></i></a>
								</label>
							</div>
						</div>
					</div>
					
					<!-- ./col -->
					<div class=" col-md-4" ng-controller="OrderPaymentSuccessCtrl">
						<!-- small box -->
						<div class="small-box bg-blue">
							<div class="inner">
								<h3 id="flightpaymentcount"></h3>
								<p>Insurance Payment Success</p>
							</div>
							<div class="icon">
								<i class="fa fa-thumbs-up"></i>
							</div>
							<div class="small-week small-box-footer">
								 <label class="btn btn-transparent grey-salsa btn-sm "><span style="color: aqua;font-size: 15px" >{{successCount.countD}}</span>&nbsp;&nbsp;&nbsp;
								 <a
									href="showIndividualInsuranceReportList?showReportType=PS&filterUptoDate=D"
									class="small-box-footer" > Today <i
										class="fa fa-arrow-circle-right"></i></a>
								</label> <label class="btn btn-transparent grey-salsa  btn-sm "><span style="color: aqua;font-size: 15px" >{{successCount.countW}}</span>&nbsp;&nbsp;&nbsp;
									<a href="showIndividualInsuranceReportList?showReportType=PS&filterUptoDate=W"
									class="small-box-footer" >Week<i
										class="fa fa-arrow-circle-right"></i></a>
								</label> <label class="btn btn-transparent grey-salsa btn-sm "><span style="color: aqua;font-size: 15px" >{{successCount.countM}}</span>&nbsp;&nbsp;&nbsp;
									<a href="showIndividualInsuranceReportList?showReportType=PS&filterUptoDate=M"
									class="small-box-footer" >Month <i
										class="fa fa-arrow-circle-right"></i></a>
								</label>
							</div>
						</div>
					</div>

					<div class=" col-md-4" ng-controller="OrderPaymentFailedCtrl">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner">
								<h3 id="flightpaymentfailedcount"></h3>
								<p>Insurance Payment Failed</p>
							</div>
							<div class="icon">
								<i class="fa fa-thumbs-down"></i>
							</div>
							<div class="small-week small-box-footer">
								<label
									class="btn btn-transparent grey-salsa btn-sm "><span style="color: aqua;font-size: 15px" >{{failedCount.countD}}</span>&nbsp;&nbsp;&nbsp;
									 <a href="showIndividualInsuranceReportList?showReportType=PF&filterUptoDate=D"
									class="small-box-footer" > Today <i
										class="fa fa-arrow-circle-right"></i></a>
								</label> <label class="btn btn-transparent grey-salsa  btn-sm "> <span style="color: aqua;font-size: 15px" >{{failedCount.countW}}</span>&nbsp;&nbsp;&nbsp;
									<a href="showIndividualInsuranceReportList?showReportType=PF&filterUptoDate=W"
									class="small-box-footer" >Week<i
										class="fa fa-arrow-circle-right"></i></a>
								</label> <label class="btn btn-transparent grey-salsa btn-sm "> <span style="color: aqua;font-size: 15px" >{{failedCount.countM}}</span>&nbsp;&nbsp;&nbsp;
									<a href="showIndividualInsuranceReportList?showReportType=PF&filterUptoDate=M"
									class="small-box-footer" >Month <i
										class="fa fa-arrow-circle-right"></i></a>
								</label>
							</div>
						</div>
					</div>
					<!-- ./col -->
				</div>
				<!-- ./row -->
				<!--  flightorder row ends -->
			</div>
		</div>
		<br>
		



		<div class="row">
			<div class="col-sm-4">
			<div ng-controller="OrderChartCtrl">
				<div class="panel panel-default">
					<div class="panel-heading clearfix">
						<div class="panel-title-box">
							<h3>Booking Status</h3>
						</div>
						<ul class="nav  nav-tabs " role="tablist">
							<li role="presentation" class="active"><a href="#bookings" ng-click="getDailyConfirmed()"
								aria-controls="home" role="tab" data-toggle="tab">Daily</a></li>
							<li role="presentation"><a href="#bookings" ng-click="getWeeklyConfirmed()"
								aria-controls="home" role="tab" data-toggle="tab">Weekly</a></li>
							<li role="presentation"><a href="#bookings" ng-click="getMonthlyConfirmed()"
								aria-controls="home" role="tab" data-toggle="tab">Monthly</a></li>
						</ul>
					</div>
					<div class="panel-body panel-body-table">


						
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="bookings">
                                    <ul class="chart-dot-list">
                                        <li class="car" style=" ">Confirmed : <span style="color:green;font-weight: 800;font-size:large;">{{bookingStatusCount.countHK}}</span> </li>
                                        <li class="air">Cancelled : <span style="color:blue;font-weight: 800;font-size:large;">{{bookingStatusCount.countHC}}</span></li>
                                        <li class="hotel">Failed : <span style="color:red;font-weight: 800;font-size:large;">{{bookingStatusCount.countHF}}</span></li>
                                    </ul>
								 <div class="doughnutDefault">
                                    <div class="chart chart-doughnut">
									<canvas id="chart-area1" width="262" height="196"
										style="width: 262px; height: 181px;">
                                    </canvas>
								</div>
                                    </div>
                                     <div class="doughnutDay">
                                     <div class="chart chart-doughnut">
									<canvas id="chart-area2" width="262" height="196"
										style="width: 262px; height: 181px;">
                                    </canvas>
								</div>
                                    </div>
                                     <div class="doughnutWeek">
                                     <div class="chart chart-doughnut">
									<canvas id="chart-area3" width="262" height="196"
										style="width: 262px; height: 181px;">
                                    </canvas>
								</div>
                                    </div>
                                     <div class="doughnutMonth">
                                     <div class="chart chart-doughnut">
									<canvas id="chart-area4" width="262" height="196"
										style="width: 262px; height: 181px;">
                                    </canvas>
								</div>
                                    </div>
                                    
								
							</div>
							
						</div>


					</div>
				</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="panel panel-default">
					<div class="panel-heading clearfix">
						<div class="panel-title-box">
							<h3>Insurance Sales</h3>
						</div>

						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#flightsconfirmedmodel"
								aria-controls="flightsconfirmedmodel" role="tab" data-toggle="tab">Confirmed Booking</a></li>
								
							<!-- <li role="presentation"><a href="#flightssuccessmodel"
								aria-controls="flightssuccessmodel" role="tab" data-toggle="tab">Payment Success</a></li>
								
							<li role="presentation"><a href="#flightsfailedmodel" aria-controls="#flightsfailedmodel"
								role="tab" data-toggle="tab">Payment Failed</a></li>
								 -->
						</ul>
						<ul class="panel-controls" style="margin-top: 2px;">
							<li><a href="#" class="panel-refresh"><span
									class="fa fa-refresh"></span></a></li>
							<li><a href="#" class="panel-fullscreen"><span
									class="fa fa-expand"></span></a></li>
						</ul>
					</div>
					<div class="panel-body panel-body-table">


						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="flightsconfirmedmodel">
								<div class="cnt">
									<div class="chart">
										<canvas id="flightconfirmed" height="289" width="867"
											style="width: 867px; height: 289px;"></canvas>
									</div>
								</div>

							</div>
							<!-- <div role="tabpanel" class="tab-pane" id="flightssuccessmodel">
							<div class="cnt">
									<div class="chart">
										<canvas id="flightsuccess" height="289" width="867"
											style="width: 867px; height: 289px;"></canvas>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="flightsfailedmodel">
							<div class="cnt">
									<div class="chart">
										<canvas id="flightfailed" height="289" width="867"
											style="width: 867px; height: 289px;"></canvas>
									</div>
								</div>
							</div> -->
							
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
                            <div class="panel panel-default">
                                <div class="panel-heading clearfix">
                                    <div class="panel-title-box">
                                        <h3>Insurance Sales Reports</h3> 
                                    </div>                                    
                                    <ul class="panel-controls" style="margin-top: 2px;">
                                     <li><a href="#" ng-click="refreshInsurance()" class="panel-refresh"><span class="fa fa-refresh"></span></a></li>
                                      <li><a href="#" class="panel-fullscreen"><span class="fa fa-expand"></span></a></li>
                                    </ul>     
                                </div>
                                <div class="panel-body panel-body-table LimitedHeight" ng-controller="airDataCtrl">
                                    
                                    <div class="table-responsive">
                                        <table class="table table-condensed table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th >OrderId</th>
                                                    <th >City</th>
                                                    <th >Agency</th>
                                                    <th >Booking Date</th>
                                                    <th >Pax Full Name</th>
                                                    <th >Status</th>
                                                    <!-- <th >Remarks</th>  -->
                                                   <!--  <th >Associated Bookings</th> -->
                                                   <th >Report List</th>
                                                     <th >Order List</th>
                                                </tr>
                                            </thead>
                                            <tbody ng-repeat="name in commonTopFivePojoInsuranceList">
                                                <tr>
                                                    <td><strong>{{name.orderId}}</strong></td>
                                                    
                                                    <td><strong>{{name.city}}</strong></td>
                                                    
                                                    <td><strong>{{name.agencyName}}</strong></td>
                                                      <td>{{name.bookingDate | date : "dd-MM-yyyy"}}</td>
                                                         <td>{{name.name}}</td>
                                                    <td><span class="label label-danger">{{name.status}}</span></td>
                                                     <td><a href="companyInsuranceReports" class="btn btn-default btn-blue">see all reports</a></td>
                                                      <td><a href="companyInsuranceOrders" class="btn btn-default btn-blue">see all Orders </a></td>
                                                </tr> 
                                            </tbody>
                                        </table>
                                    </div>
                                    
                                </div>
                            </div> 
                        </div>  

		</div>

		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
  <script src="js/dashboardpanelaction.js"></script> 
<%@ include file="DashboardFooter.jsp"%>
<script src="js/Chart.min.js"></script>



<script type="text/javascript">
	$(document).ready(function() {
		$('#cancel-main').click(function() {
			$('#error-alert-main').hide();
		});

	});
</script>

<script>
	var app = angular.module('airDataApp', []);
	app.controller('airDataCtrl',function($scope, $http) {
						var currentUrl = $(location).attr('href');
						var baseUrl = currentUrl.substr(0, currentUrl.lastIndexOf('/') + 1);

						$http.get(baseUrl+"getInsuranceBookingList").then(function(response) {
					    	$scope.commonTopFivePojoInsuranceList = response.data.commonTopFivePojoList;
					        });
						$scope.refreshInsurance=function(){
							 $http.get(baseUrl+"getInsuranceBookingList").then(function(response) {
							    	$scope.commonTopFivePojoInsuranceList = response.data.commonTopFivePojoList;
							        });
					    };

					});
	
	app.controller('BookingConfirmCtrl',function($scope, $http) {
		var currentUrl = $(location).attr('href');
		var baseUrl = currentUrl.substr(0, currentUrl.lastIndexOf('/') + 1);

		$http.get(baseUrl + "getOnlyInsuranceOrderJson?bstatus=HK").then(function(response) {
			
			$scope.confirmedCount = response.data.jsonobj;
			
			var barChartConfirmedData = {
		    	    labels : ["Daily","Weekly","Monthly"],
		    	    datasets : [
		    	        {
		    	        	label: "Economy",
		    	            fillColor : "rgb(0, 128, 0)",
		    	            strokeColor : "rgb(0, 128, 0)",
		    	            pointColor : "rgb(0, 128, 0)",
		    	            highlightFill: "rgba(0, 128, 0,0.75)",
		    	            highlightStroke: "rgba(0, 128, 0,1)",
		    	               data : [response.data.jsonobj.countD,response.data.jsonobj.countW, response.data.jsonobj.countM]
		    	        }
		    	    ]

		    	}
			
			  var ctx = document.getElementById("flightconfirmed").getContext("2d");
	        window.myBar = new Chart(ctx).Bar(barChartConfirmedData, {
	            responsive : true
	        });
		});
		
		

	});
	
	app.controller('OrderPaymentSuccessCtrl',function($scope, $http) {
		var currentUrl = $(location).attr('href');
		var baseUrl = currentUrl.substr(0, currentUrl.lastIndexOf('/') + 1);

		$http.get(baseUrl + "getOnlyInsuranceOrderJson?pstatus=PS").then(function(response) {
			$scope.successCount = response.data.jsonobj;
		});

	});
	
	app.controller('OrderPaymentFailedCtrl',function($scope, $http) {
		var currentUrl = $(location).attr('href');
		var baseUrl = currentUrl.substr(0, currentUrl.lastIndexOf('/') + 1);

		$http.get(baseUrl + "getOnlyInsuranceOrderJson?pstatus=PF").then(function(response) {
			$scope.failedCount = response.data.jsonobj;
		});

	});
	
	app.controller('OrderChartCtrl', function($scope, $http) {
		var currentUrl = $(location).attr('href');
		var baseUrl = currentUrl.substr(0, currentUrl.lastIndexOf('/') + 1);
		
		$http.get(baseUrl+"getOnlyInsuranceOrderJson?bstatus=ALL&type=D").then(function(response) {
			 $('.doughnutDefault').show();
				$('.doughnutDay').hide();
				$('.doughnutWeek').hide();
				$('.doughnutMonth').hide();
			prepareDonutChart(response);
			 $scope.bookingStatusCount = response.data.jsonobj;
				
		});
		
		 $scope.getDailyConfirmed=function(){
			 $http.get(baseUrl+"getOnlyInsuranceOrderJson?bstatus=ALL&type=D").then(function(response) {
				 $('.doughnutDefault').hide();
					$('.doughnutDay').show();
					$('.doughnutWeek').hide();
					$('.doughnutMonth').hide();
				 $scope.bookingStatusCount = response.data.jsonobj;
				 prepareDonutChartForDay(response);
				 	
			});
		};
		
		$scope.getWeeklyConfirmed=function(){
			$http.get(baseUrl+"getOnlyInsuranceOrderJson?bstatus=ALL&type=W").then(function(response) {
				$('.doughnutDefault').hide();
				$('.doughnutDay').hide();
				$('.doughnutWeek').show();
				$('.doughnutMonth').hide();
				$scope.bookingStatusCount = response.data.jsonobj;
				prepareDonutChartForWeek(response);
				
			});
	  };
	  
	  $scope.getMonthlyConfirmed=function(){
		   $http.get(baseUrl+"getOnlyInsuranceOrderJson?bstatus=ALL&type=M").then(function(response) {
			   $('.doughnutDefault').hide();
				$('.doughnutDay').hide();
				$('.doughnutWeek').hide();
				$('.doughnutMonth').show();
			   $scope.bookingStatusCount = response.data.jsonobj;
			   prepareDonutChartForMonth(response);
			   
		   });
	 };
		   
	});
		
		
	function prepareDonutChart(response){
		var doughnutData22 = [ {
			value : response.data.jsonobj.countHK,
			color : "rgb(0, 128, 0)",
			highlight : "rgba(0, 128, 0,0.75)",
			label : "Confirmed"
		}, {
			value : response.data.jsonobj.countHC,
			color : "rgb(0, 130, 188)",
			highlight : "rgb(7, 156, 222)",
			label : "Cancelled"
		}, {
			value : response.data.jsonobj.countHF,
			color : "rgb(188, 0, 7)",
			highlight : "#af1519",
			label : "Failed"
		}, ];
		var doctx = document.getElementById("chart-area1").getContext("2d");
		window.myDoughnut = new Chart(doctx).Doughnut(doughnutData22,{responsive : true }); 
	}
	
	function prepareDonutChartForDay(response){
		var doughnutData22 = [ {
			value : response.data.jsonobj.countHK,
			color : "rgb(0, 128, 0)",
			highlight : "rgba(0, 128, 0,0.75)",
			label : "Confirmed"
		}, {
			value : response.data.jsonobj.countHC,
			color : "rgb(0, 130, 188)",
			highlight : "rgb(7, 156, 222)",
			label : "Cancelled"
		}, {
			value : response.data.jsonobj.countHF,
			color : "rgb(188, 0, 7)",
			highlight : "#af1519",
			label : "Failed"
		}, ];
		var doctx = document.getElementById("chart-area2").getContext("2d");
		window.myDoughnut = new Chart(doctx).Doughnut(doughnutData22,{responsive : true }); 
	}
	
	function prepareDonutChartForMonth(response){
		var doughnutData22 = [ {
			value : response.data.jsonobj.countHK,
			color : "rgb(0, 128, 0)",
			highlight : "rgba(0, 128, 0,0.75)",
			label : "Confirmed"
		}, {
			value : response.data.jsonobj.countHC,
			color : "rgb(0, 130, 188)",
			highlight : "rgb(7, 156, 222)",
			label : "Cancelled"
		}, {
			value : response.data.jsonobj.countHF,
			color : "rgb(188, 0, 7)",
			highlight : "#af1519",
			label : "Failed"
		}, ];
		var doctx = document.getElementById("chart-area4").getContext("2d");
		window.myDoughnut = new Chart(doctx).Doughnut(doughnutData22,{responsive : true }); 
	}
 
	function prepareDonutChartForWeek(response){
		var doughnutData22 = [ {
			value : response.data.jsonobj.countHK,
			color : "rgb(0, 128, 0)",
			highlight : "rgba(0, 128, 0,0.75)",
			label : "Confirmed"
		}, {
			value : response.data.jsonobj.countHC,
			color : "rgb(0, 130, 188)",
			highlight : "rgb(7, 156, 222)",
			label : "Cancelled"
		}, {
			value : response.data.jsonobj.countHF,
			color : "rgb(188, 0, 7)",
			highlight : "#af1519",
			label : "Failed"
		}, ];
		var doctx = document.getElementById("chart-area3").getContext("2d");
		window.myDoughnut = new Chart(doctx).Doughnut(doughnutData22,{responsive : true }); 
	}
	
	
</script>
