
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/chart.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css">
<link href="css/jquerydarkness-ui-modified.css" rel="stylesheet">
<link href="css/animate-custom.css" rel="stylesheet" type="text/css" />
<link href="css/dashboard-search.css" rel="stylesheet" type="text/css" />
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

.dropdown-menu li a.ng-click-active, .dropdown-menu li a:active {
	background-color: green;
}

.addActiveClass {
	background-color: red;
}

#chartbarCompareDay {
	
}
</style>
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
			Dash board <small>Control panel</small>


		</h1>


	</section>
	<!-- Main content -->
	<!-- Main content -->
	<section class="content overall">
		<div ng-controller="airDataCtrl">
			<div class="row">
				<div class="col-sm-7" ng-controller="destinationDataCtrl">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Weekly Sales</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href=""
									ng-click="refreshService('AllServicesWeeklySales')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="AllServicesWeeklySales"></span></a></li>
								<%-- <li><a href="#" class="panel-fullscreen"><span
									class="fa fa-expand"></span></a></li> --%>

							</ul>
						</div>

						<div class="cnt">
							<ul class="chart-dot-list">
								<li class="dot-flight" style="">Flight <span
									style="color: green; font-weight: 800; font-size: 12px;"></span>
								</li>
								<li class="dot-hotel">Hotel <span
									style="color: blue; font-weight: 800; font-size: 12px;"></span></li>
								<li class="dot-bus">Bus <span
									style="color: red; font-weight: 800; font-size: 12px;"></span></li>
								<li class="dot-car" style="">Car <span
									style="color: green; font-weight: 800; font-size: 12px;"></span>
								</li>
								<li class="dot-train">Train <span
									style="color: blue; font-weight: 800; font-size: 12px;"></span></li>
								<li class="dot-visa">Visa <span
									style="color: red; font-weight: 800; font-size: 12px;"></span></li>
								<li class="dot-ins" style="">Insurance : <span
									style="color: green; font-weight: 800; font-size: 12px;"></span>
								</li>
								<li class="dot-misc">Misc <span
									style="color: blue; font-weight: 800; font-size: 12px;"></span></li>
							</ul>
							<div class="chart">
								<canvas id="line-chart-dashBoardOverAll" height="369"
									width="867" style="width: 867px; height: 369px;"></canvas>
							</div>
						</div>


					</div>
				</div>
				<div class="col-sm-5">

					<div class="col-sm-12 livebookpanel"
						style="padding-left: 0px; padding-right: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading clearfix">
								<div class="panel-title-box">
									<h3>Live Bookings Reports</h3>
								</div>
								<ul class="panel-controls" style="margin-top: 2px;">

									<li id="flight-list" style="display: none; color: green">Refreshing...</li>
									<li><a href=""
										ng-click="refreshService('AllServicesCount')"
										class="panel-refresh"><span class="fa fa-refresh"
											id="AllServicesCount"></span></a></li>
									<li><a href="#" class="panel-fullscreen"><span
											class="fa fa-expand"></span></a></li>


								</ul>
							</div>
							<div class="panel-body panel-body-table LimitedHeight">

								<div class="table-responsive">
									<table
										class="table table-condensed table-bordered table-striped">
										<thead>
											<tr>
												<!-- <th>Service</th> -->
												<th>No.of Ticket</th>
												<th>Total Price</th>
												<th>%.of Booking</th>
												<th>Avg.of Ticket Price</th>


											</tr>
										</thead>
										<!-- ng-repeat="name in finallivedata" -->
										<tbody>
											<tr ng-repeat="name in finallivedata track by $index">
												<td><i ng-if="$index==0"
													class="fa fa fa-plane livepanelico" style="color: #8b6bd8;"></i>
													<i ng-if="$index==1" class="fa fa fa-hotel livepanelico"
													style="color: #00abed;"></i> <i ng-if="$index==2"
													class="fa fa fa-bus livepanelico" style="color: #ff4353;"></i>
													<i ng-if="$index==3" class="fa fa fa-car livepanelico"
													style="color: #8eca36;"></i> <i ng-if="$index==4"
													class="fa fa fa-train livepanelico" style="color: #002d56;"></i>
													<i ng-if="$index==5" class="fa fa fa-cc-visa livepanelico"
													style="color: #004d56;"></i> <i ng-if="$index==6"
													class="fa fa fa-medkit livepanelico"
													style="color: #1d4f1d;"></i> <i ng-if="$index==7"
													class="fa fa fa-cogs livepanelico" style="color: #a76900;"></i><strong>{{name.countD}}</strong></td>
												<td><strong>{{name.bookingAmountD}}</strong></td>
												<td>{{name.percent}}%</td>
												<td>{{name.avgPrice}}</td>


											</tr>

											<%-- <tr>
										<td><strong>Air</strong><i class="fa fa fa-cc-plane"></i></td>
										<td><i class="fa fa fa-plane livepanelico" style="color: #8b6bd8;"></i><strong>20</strong></td>
										<td><strong>80000.00</strong></td>
										<td>40%</td>
										<td>4000.00</td>
									    
										
									</tr>
									<tr>
										<td><strong>Hotel</strong><i class="fa fa fa-hotel fa-fw"></i></td>
										<td><i class="fa fa fa-hotel livepanelico fa-fw" style="color: #00abed;"></i><strong>5</strong></td>
										<td><strong>30000.00</strong></td>
										<td>10%</td>
										<td>6000.00</td>
										
									</tr>
									<tr>
										<td><strong>Bus</strong><i class="fa fa fa-bus fa-fw"></i></td>
										<td><i class="fa fa fa-bus livepanelico fa-fw" style="color: #ff4353;"></i><strong>10</strong></td>
										<td><strong>16000</strong></td>
										<td>20%</td>
										<td>1600.00</td>
										
									</tr>
									<tr>
										<td><strong>Car</strong><i class="fa fa fa-car fa-fw"></i></td>
										<td><i class="fa fa fa-car livepanelico fa-fw" style="color: #8eca36;"></i><strong>5</strong></td>
										<td><strong>2500.00</strong></td>
										<td>10%</td>
										<td>500.00</td>
										
									</tr>
									<tr>
										<td><strong>Train</strong><i class="fa fa fa-train fa-fw"></i></td>
										<td><i class="fa fa fa-train livepanelico fa-fw" style="color: #002d56;"></i><strong>1</strong></td>
										<td><strong>550.00</strong></td>
										<td>2%</td>
										<td>550.00</td>
									</tr>
									<tr>
										<td><strong>Visa</strong><i class="fa fa fa-cc-visa"></i></td>
										<td><i class="fa fa livepanelico fa-cc-visa" style="color: #004d56;;"></i><strong>3</strong></td>
										<td><strong>6300.00</strong></td>
										<td>6%</td>
										<td>2100.00</td>
										
									</tr>
									<tr>
										<td><strong>Insurance</strong><i class="fa fa fa-medkit"></i></td>
										<td><i class="fa fa livepanelico fa-medkit" style="color: #1d4f1d;"></i><strong>4</strong></td>
										<td><strong>1000.00</strong></td>
										<td>8%</td>
										<td>250.00</td>
										
									</tr>
									<tr>
										<td><strong>Misc</strong><i class="fa fa fa-cogs"></i></td>
										<td><i class="fa fa livepanelico fa-cogs" style="color: #a76900;"></i><strong>2</strong></td>
										<td><strong>1600.00</strong></td>
										<td>4%</td>
										<td>800.00</td>
										
									</tr> --%>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>


				</div>

				<%-- <div class="col-sm-5" ng-controller="airDataCtrl">
				<div class="panel panel-default ov-live-bookings">
					<div class="panel-heading clearfix">
						<div class="panel-title-box">
							<h3>Live Bookings</h3>
						</div>
						<ul class="panel-controls" style="margin-top: 2px;">

							<li><a href="" ng-click="refreshService('AllServicesCount')" class="panel-refresh"><span
									class="fa fa-refresh" id="AllServicesCount"></span></a></li>
							<li><a href="#" class="panel-fullscreen"><span
									class="fa fa-expand"></span></a></li>

						</ul>
					</div>
					<div class="panel-body panel-body-table row">
						<div class="col-sm-6">
							<!-- small box -->
							<div class="small-box bg-purple">
								<div class="inner">
									<h3 id="flightconfirmCount">
										<span ng-bind="flightCount.countD"></span>
									</h3>
									<p>Air Tickets</p>
								</div>
								<div class="icon icon-planebg">
									<i class="fa fa-plane"></i>
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<!-- small box -->
							<div class="small-box bg-blued">
								<div class="inner">
									<h3 id="flightconfirmCount">
										<span ng-bind="hotelCount.countD">
									</h3>
									<p>Hotel Bookings</p>
								</div>
								<div class="icon icon-hotelbg">
									<i class="fa fa-hotel"></i>
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<!-- small box -->
							<div class="small-box bg-reded">
								<div class="inner">
									<h3 id="flightconfirmCount">
										<span ng-bind="busCount.countD">
									</h3>
									<p>Bus Bookings</p>
								</div>
								<div class="icon icon-busbg">
									<i class="fa fa-bus"></i>
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<!-- small box -->
							<div class="small-box bg-greened">
								<div class="inner">
									<h3 id="flightconfirmCount">
										<span ng-bind="carCount.countD">
									</h3>
									<p>Car Bookings</p>
								</div>
								<div class="icon icon-carbg">
									<i class="fa fa-car"></i>
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<!-- small box -->
							<div class="small-box bg-darkeded">
								<div class="inner">
									<h3 id="flightconfirmCount">
										<span ng-bind="trainCount.countD">
									</h3>
									<p>Train Bookings</p>
								</div>
								<div class="icon icon-trainbg">
									<i class="fa fa-train"></i>
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<!-- small box -->
							<div class="small-box bg-visa">
								<div class="inner">
									<h3 id="flightconfirmCount">
										<span ng-bind="visaCount.countD">
									</h3>
									<p>Visa Bookings</p>
								</div>
								<div class="icon icon-visabg">
									<i class="fa fa fa-cc-visa"></i>
								</div>
							</div>
						</div>


						<div class="col-sm-6">
							<!-- small box -->
							<div class="small-box bg-ins">
								<div class="inner">
									<h3 id="flightconfirmCount">
										<span ng-bind="insuranceCount.countD">
									</h3>
									<p>Insurance Bookings</p>
								</div>
								<div class="icon icon-visabg">
									<i class="fa fa fa-medkit"></i>
								</div>
							</div>
						</div>


						<div class="col-sm-6">
							<!-- small box -->
							<div class="small-box bg-misc">
								<div class="inner">
									<h3 id="flightconfirmCount">
										<span ng-bind="miscCount.countD">
									</h3>
									<p>Miscellaneous Bookings</p>
								</div>
								<div class="icon icon-visabg">
									<i class="fa fa fa-cogs"></i>
								</div>
							</div>
						</div>


					</div>
				</div>
			</div> --%>

			</div>



			<div class="row">


				<div class="col-sm-5">

					<div class="col-sm-12 livebookpanel"
						style="padding-left: 0px; padding-right: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading clearfix">
								<div class="panel-title-box">
									<h3>Todays</h3>
								</div>
								<ul class="panel-controls" style="margin-top: 2px;">

									<li id="flight-list" style="display: none; color: green">Refreshing...</li>
									<li><a href=""
										ng-click="refreshService('todaySalesReport')"
										class="panel-refresh"><span class="fa fa-refresh"
											id="todaySalesReport"></span></a></li>
									<li><a href="#" class="panel-fullscreen"><span
											class="fa fa-expand"></span></a></li>


								</ul>
							</div>
							<div class="panel-body panel-body-table LimitedHeight">

								<div class="table-responsive">
									<table
										class="table table-condensed table-bordered table-striped">
										<thead>
											<tr>
												<!-- <th>Service</th> -->
												<th>No.of Booking</th>
												<th>Total Booking Amount</th>
												<th>Total Refunded Amount</th>
												<th>Total Loss Amount</th>
												<th>%.of Loss Amount</th>

											</tr>
										</thead>
										<!-- ng-repeat="name in finallivedata" -->
										<tbody>
											<tr ng-repeat="name in finaltodaydata">

												<td><i ng-if='name.serviceType==="Flight"'
													class="fa fa fa-plane livepanelico" style="color: #8b6bd8;"></i>
													<i ng-if='name.serviceType==="Hotel"'
													class="fa fa fa-hotel livepanelico" style="color: #00abed;"></i>
													<i ng-if='name.serviceType==="Bus"'
													class="fa fa fa-bus livepanelico" style="color: #ff4353;"></i>
													<i ng-if='name.serviceType==="Car"'
													class="fa fa fa-car livepanelico" style="color: #8eca36;"></i>
													<i ng-if='name.serviceType==="Train"'
													class="fa fa fa-train livepanelico" style="color: #002d56;"></i>
													<i ng-if='name.serviceType==="Visa"'
													class="fa fa fa-cc-visa livepanelico"
													style="color: #004d56;"></i> <i
													ng-if='name.serviceType==="Insurance"'
													class="fa fa fa-medkit livepanelico"
													style="color: #1d4f1d;"></i> <i
													ng-if='name.serviceType==="Misc"'
													class="fa fa fa-cogs livepanelico" style="color: #a76900;"></i><strong>{{name.count}}</strong></td>
												<td><strong>{{name.totalBookingAmount}}</strong></td>
												<td>{{name.totalRefundedAmount}}</td>
												<td>{{name.totalLossAmount}}</td>
												<td>{{name.percent}}%</td>


											</tr>

											<%-- <tr>
										<td><strong>Air</strong><i class="fa fa fa-cc-plane"></i></td>
										<td><i class="fa fa fa-plane livepanelico" style="color: #8b6bd8;"></i><strong>20</strong></td>
										<td><strong>80000.00</strong></td>
										<td>40%</td>
										<td>4000.00</td>
									    
										
									</tr>
									<tr>
										<td><strong>Hotel</strong><i class="fa fa fa-hotel fa-fw"></i></td>
										<td><i class="fa fa fa-hotel livepanelico fa-fw" style="color: #00abed;"></i><strong>5</strong></td>
										<td><strong>30000.00</strong></td>
										<td>10%</td>
										<td>6000.00</td>
										
									</tr>
									<tr>
										<td><strong>Bus</strong><i class="fa fa fa-bus fa-fw"></i></td>
										<td><i class="fa fa fa-bus livepanelico fa-fw" style="color: #ff4353;"></i><strong>10</strong></td>
										<td><strong>16000</strong></td>
										<td>20%</td>
										<td>1600.00</td>
										
									</tr>
									<tr>
										<td><strong>Car</strong><i class="fa fa fa-car fa-fw"></i></td>
										<td><i class="fa fa fa-car livepanelico fa-fw" style="color: #8eca36;"></i><strong>5</strong></td>
										<td><strong>2500.00</strong></td>
										<td>10%</td>
										<td>500.00</td>
										
									</tr>
									<tr>
										<td><strong>Train</strong><i class="fa fa fa-train fa-fw"></i></td>
										<td><i class="fa fa fa-train livepanelico fa-fw" style="color: #002d56;"></i><strong>1</strong></td>
										<td><strong>550.00</strong></td>
										<td>2%</td>
										<td>550.00</td>
									</tr>
									<tr>
										<td><strong>Visa</strong><i class="fa fa fa-cc-visa"></i></td>
										<td><i class="fa fa livepanelico fa-cc-visa" style="color: #004d56;;"></i><strong>3</strong></td>
										<td><strong>6300.00</strong></td>
										<td>6%</td>
										<td>2100.00</td>
										
									</tr>
									<tr>
										<td><strong>Insurance</strong><i class="fa fa fa-medkit"></i></td>
										<td><i class="fa fa livepanelico fa-medkit" style="color: #1d4f1d;"></i><strong>4</strong></td>
										<td><strong>1000.00</strong></td>
										<td>8%</td>
										<td>250.00</td>
										
									</tr>
									<tr>
										<td><strong>Misc</strong><i class="fa fa fa-cogs"></i></td>
										<td><i class="fa fa livepanelico fa-cogs" style="color: #a76900;"></i><strong>2</strong></td>
										<td><strong>1600.00</strong></td>
										<td>4%</td>
										<td>800.00</td>
										
									</tr> --%>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>


				</div>



				<%-- <div class="col-sm-4">
				<div ng-controller="OrderChartCtrl">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Todays</h3>
							</div>

							<ul class="panel-controls" style="margin-top: 2px;">

								<li><a  href="" ng-click="refreshService('todayChart')"  class="panel-refresh"><span
										class="fa fa-refresh" id="todayChart"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>

							</ul>
						</div>
						<div class="panel-body panel-body-table">


							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a href="#bookings"
									ng-click="getToday()" aria-controls="home" role="tab"
									data-toggle="tab">Bookings</a></li>
								<li role="presentation"><a href="#bookings2"
									ng-click="getTicketCancelled('BVC')" aria-controls="tiocket"
									role="tab" data-toggle="tab">Ticket vs Cancelled</a></li>
							</ul>
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="bookings">
								 <ul ng-if="bookingStatusCount.servicesData.totalCount !=0" class="chart-dot-list">
                                        <li class="dot-flight" style=" ">Flight : <span style="color:green;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.flight.count}}</span> </li>
                                        <li class="dot-hotel">Hotel : <span style="color:blue;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.hotel.count}}</span></li>
                                        <li class="dot-bus">Bus : <span style="color:red;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.bus.count}}</span></li>
                                        <li class="dot-car" style=" ">Car : <span style="color:green;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.car.count}}</span> </li>
                                        <li class="dot-train">Train : <span style="color:blue;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.train.count}}</span></li>
                                        <li class="dot-visa">Visa : <span style="color:red;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.visa.count}}</span></li>
                                        <li class="dot-ins" style=" ">Insurance : <span style="color:green;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.insurance.count}}</span> </li>
                                        <li class="dot-misc">Misc : <span style="color:blue;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.misc.count}}</span></li>
                                    </ul>
                                    <ul ng-if="bookingStatusCount.servicesData.totalCount ==0" class="chart-dot-list">
                                        <li class="dot-flight" style=" ">Flight : <span style="color:green;font-weight: 800;font-size:12px;">0</span> </li>
                                        <li class="dot-hotel">Hotel : <span style="color:blue;font-weight: 800;font-size:12px;">0</span></li>
                                        <li class="dot-bus">Bus : <span style="color:red;font-weight: 800;font-size:12px;">0</span></li>
                                        <li class="dot-car" style=" ">Car : <span style="color:green;font-weight: 800;font-size:12px;">0</span> </li>
                                        <li class="dot-train">Train : <span style="color:blue;font-weight: 800;font-size:12px;">0</span></li>
                                        <li class="dot-visa">Visa : <span style="color:red;font-weight: 800;font-size:12px;">0</span></li>
                                        <li class="dot-ins" style=" ">Insurance : <span style="color:green;font-weight: 800;font-size:12px;">0</span> </li>
                                        <li class="dot-misc">Misc : <span style="color:blue;font-weight: 800;font-size:12px;">0</span></li>
                                    </ul>								
									<div class="chart chart-doughnut">
										<canvas id="today" 	style="width: 446px !important; height: 186px;">
                                    </canvas>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane" id="bookings2">
								
								 <ul ng-if="bookingStatusCount.servicesData.totalCount !=0" class="chart-dot-list">
                                        <li class="dot-flight" style=" ">Flight : <span style="color:green;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.flight.count}}</span> </li>
                                        <li class="dot-hotel">Hotel : <span style="color:blue;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.hotel.count}}</span></li>
                                        <li class="dot-bus">Bus : <span style="color:red;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.bus.count}}</span></li>
                                        <li class="dot-car" style=" ">Car : <span style="color:green;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.car.count}}</span> </li>
                                        <li class="dot-train">Train : <span style="color:blue;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.train.count}}</span></li>
                                        <li class="dot-visa">Visa : <span style="color:red;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.visa.count}}</span></li>
                                        <li class="dot-ins" style=" ">Insurance : <span style="color:green;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.insurance.count}}</span> </li>
                                        <li class="dot-misc">Misc : <span style="color:blue;font-weight: 800;font-size:12px;">{{bookingStatusCount.servicesData.misc.count}}</span></li>
                                    </ul>
                                    <ul ng-if="bookingStatusCount.servicesData.totalCount ==0" class="chart-dot-list">
                                        <li class="dot-flight" style=" ">Flight : <span style="color:green;font-weight: 800;font-size:12px;">0</span> </li>
                                        <li class="dot-hotel">Hotel : <span style="color:blue;font-weight: 800;font-size:12px;">0</span></li>
                                        <li class="dot-bus">Bus : <span style="color:red;font-weight: 800;font-size:12px;">0</span></li>
                                        <li class="dot-car" style=" ">Car : <span style="color:green;font-weight: 800;font-size:12px;">0</span> </li>
                                        <li class="dot-train">Train : <span style="color:blue;font-weight: 800;font-size:12px;">0</span></li>
                                        <li class="dot-visa">Visa : <span style="color:red;font-weight: 800;font-size:12px;">0</span></li>
                                        <li class="dot-ins" style=" ">Insurance : <span style="color:green;font-weight: 800;font-size:12px;">0</span> </li>
                                        <li class="dot-misc">Misc : <span style="color:blue;font-weight: 800;font-size:12px;">0</span></li>
                                    </ul>
									<div class="chart chart-doughnut">
										<canvas id="today2" style="width: 446px !important; height: 186px;">
                                    </canvas>
									</div>

								</div>
 
							</div>
							 


						</div>
					</div>
				</div>
			</div> --%>


				<div class="col-sm-7" ng-controller="barDataCtl">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Weekly Sales</h3>
							</div>

							<ul class="nav nav-tabs" id="weeklySalesBar" role="tablist">
								<li role="presentation" class="active"><a href="#flights"
									ng-click="getFlightBar()" aria-controls="flights" role="tab"
									data-toggle="tab">Flight</a></li>
								<li role="presentation"><a href="#hotels"
									ng-click="getHotelBar()" aria-controls="hotel" role="tab"
									data-toggle="tab">Hotel</a></li>
								<li role="presentation"><a href="#cars"
									ng-click="getCarBar()" aria-controls="car" role="tab"
									data-toggle="tab">Car</a></li>
								<li role="presentation"><a href="#buses"
									ng-click="getBusBar()" aria-controls="bus" role="tab"
									data-toggle="tab">Bus</a></li>
								<li role="presentation"><a href="#trains"
									ng-click="getTrainBar()" aria-controls="bus" role="tab"
									data-toggle="tab">Train</a></li>
							</ul>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href=""
									ng-click="refreshService('AllWeeklySalesbar')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="AllWeeklySalesbar"></span></a></li>
								<%-- <li><a href="#" class="panel-fullscreen"><span
									class="fa fa-expand"></span></a></li> --%>
							</ul>
						</div>
						<div class="panel-body panel-body-table">


							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="flights">
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="chartbarmonth" height="385" width="867"
												style="width: 867px; height: 385px;"></canvas>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane " id="hotels">
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="hotelChartBarMonth" height="370" width="867"
												style="width: 867px; height: 370px;"></canvas>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane " id="cars">
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="carChartBarMonth" height="370" width="867"
												style="width: 867px; height: 370px;"></canvas>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane " id="buses">
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="busChartBarMonth" height="370" width="867"
												style="width: 867px; height: 370px;"></canvas>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane " id="trains">
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="trainChartBarMonth" height="370" width="867"
												style="width: 867px; height: 370px;"></canvas>
										</div>
									</div>

								</div>

							</div>
						</div>
					</div>
				</div>




			</div>

			<div class="row">
				<div class="col-sm-12" ng-controller="barcomDataCtl">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Sales Comparison</h3>
							</div>

							<ul class="nav nav-tabs" id="dayCompareSalesbar" role="tablist">
								<li role="presentation" class="active"><a href="#daySale"
									ng-click="getdayCompareBar()" aria-controls="flights"
									role="tab" data-toggle="tab">DAY</a></li>
								<li role="presentation"><a href="#weekSale"
									ng-click="getweekCompareBar()" aria-controls="hotel" role="tab"
									data-toggle="tab">WEEK</a></li>
								<li role="presentation"><a href="#monthSale"
									ng-click="getmonthCompareBar()" aria-controls="car" role="tab"
									data-toggle="tab">MONTH</a></li>
								<li role="presentation"><a href="#yearSalee"
									ng-click="getyearCompareBar()" aria-controls="car" role="tab"
									data-toggle="tab">YEAR</a></li>
								<!-- <li role="presentation"><a href="#YearSale"
								ng-click="getyearCompareBar()" aria-controls="bus" role="tab"
								data-toggle="tab">YEAR</a></li> -->
							</ul>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href=""
									ng-click="refreshcomparecall('AllCompareSalesbar')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="AllCompareSalesbar"></span></a></li>
								<%-- <li><a href="#" class="panel-fullscreen"><span
									class="fa fa-expand"></span></a></li> --%>
							</ul>
						</div>
						<div class="panel-body panel-body-table">


							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="daySale">
									<div>
										<input type="button" ng-click="previousDate()"
											data-ng-disabled="!isPre()" value="Previous Date"
											id="previousDAte" class="btn btn-primary  btn-xs"> <input
											type="button" ng-click="nextDate()"
											data-ng-disabled="!isNext()" value="Next Date" id="nextDAte"
											class=" btn btn-primary  btn-xs"
											style="float: right; width: 6%;">

									</div>
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="chartbarCompareDay"></canvas>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane " id="weekSale">
									<div>
										<input type="button" ng-click="previousWeek()"
											data-ng-disabled="!isPreWeek()" value="Previous Week"
											id="previousWeek" class="btn btn-primary  btn-xs"> <input
											type="button" ng-click="nextWeek()"
											data-ng-disabled="!isNextWeek()" value="Next Week"
											id="nextWeek" class=" btn btn-primary  btn-xs"
											style="float: right; width: 6%;">

									</div>
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="chartbarCompareWeek"></canvas>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane " id="monthSale">
									<div>
										<input type="button" ng-click="previousMonth()"
											data-ng-disabled="!isPreMonth()" value="Previous Month"
											id="previousMonth" class="btn btn-primary  btn-xs"> <input
											type="button" ng-click="nextMonth()"
											data-ng-disabled="!isNextMonth()" value="Next Month"
											id="nextMonth" class=" btn btn-primary  btn-xs"
											style="float: right; width: 6%;">

									</div>
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="chartbarCompareMonth"></canvas>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane " id="yearSalee">
									<div>
										<input type="button" ng-click="previousYear()"
											data-ng-disabled="!isPreYear()" value="Previous Year"
											id="previousYear" class="btn btn-primary  btn-xs"> <input
											type="button" ng-click="nextYear()"
											data-ng-disabled="!isNextYear()" value="Next Year"
											id="nextYear" class=" btn btn-primary  btn-xs"
											style="float: right; width: 6%;">

									</div>
									<div class="cnt">
										<div class="chart" style="padding-top: 1% !important;">
											<canvas id="chartbarCompareYear"></canvas>
										</div>
									</div>

								</div>

								<!-- <div role="tabpanel" class="tab-pane " id="yearSale">
								<div>
							<input type="button" ng-click="previousYear()" data-ng-disabled="!isPreYear()" value="Previous Year" id="previousYear" class="btn btn-primary  btn-xs">
							
							<input type="button" ng-click="nextYear()" data-ng-disabled="!isNextYear()" value="Next Year" id="nextYear" class=" btn btn-primary  btn-xs" style="float: right;width: 6%;">
							
							</div>
								<div class="cnt">
									<div class="chart" style="padding-top: 1% !important;">
										<canvas id="chartbarCompareYear"></canvas>
									</div>
								</div>

							</div> -->

							</div>
						</div>
					</div>
				</div>
			</div>



			<div class="row">
				<div class="col-sm-3" ng-controller="destinationDataCtrl">

					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Top 10 Destinations</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href="" class="panel-refresh"><span
										class="fa fa-refresh"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>

								<li class="dropdown "><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"><span class="fa fa-filter"></span></a>
									<ul class="dropdown-menu destination-controls">
										<li class="active"><a href=""
											ng-click="getAirlineDestinations()">Airline</a></li>
										<li><a href="" ng-click="getHotelDestinations()">Hotel</a></li>
										<li><a href="" ng-click="getBusDestinations()"> Bus</a></li>
										<li><a href="" ng-click="getCarDestinations()"> Car</a></li>
										<li><a href="" ng-click="getTrainDestinations()">Train</a></li>

									</ul></li>
							</ul>
						</div>
						<div class="panel-body panel-body-table LimitedHeight">
							<div class="list-group"
								ng-if="topDestData.destinationList != null && topDestData.destinationList.length>0"
								ng-repeat="provider in topDestData.destinationList">
								<a class="list-group-item" href="#"> {{provider.name}}<span>%</span>
									<span ng-bind="provider.percentage"></span>
								</a>

							</div>
							<div class="list-group"
								ng-if="topDestData.destinationList == null">-------- no
								data ------</div>

						</div>
					</div>
				</div>


				<div>
					<div class="col-sm-3">

						<div class="panel panel-default">
							<div class="panel-heading clearfix">
								<div class="panel-title-box">
									<h3>Top 10 Airlines</h3>
								</div>
								<ul class="panel-controls" style="margin-top: 2px;">

									<li><a href="" ng-click="refreshService('Airline')"
										class="panel-refresh"><span class="fa fa-refresh"
											id="Airline"></span></a></li>
									<li><a href="#" class="panel-fullscreen"><span
											class="fa fa-expand"></span></a></li>

								</ul>
							</div>
							<div class="panel-body panel-body-table LimitedHeight">
								<!--  <div class="list-group" ng-repeat="x in airData">  -->

								<div class="list-group"
									ng-if="airData.airlineList != null && airData.airlineList.length>0"
									ng-repeat="provider in airData.airlineList">
									<a class="list-group-item" href="#"><i
										class="fa fa-plane fa-fw" aria-hidden="true"></i>
										{{provider.name}} <span>%</span> <span
										ng-bind="provider.percentage"></span> </a>

								</div>
								<div class="list-group" ng-if="airData.airlineList == null">
									-------- no data ------</div>

							</div>
						</div>
					</div>

					<div class="col-sm-3">

						<div class="panel panel-default">
							<div class="panel-heading clearfix">
								<div class="panel-title-box">
									<h3>Top 10 Hotel Chain</h3>
								</div>
								<ul class="panel-controls" style="margin-top: 2px;">

									<li><a href="" ng-click="refreshService('HotelChain')"
										class="panel-refresh"><span class="fa fa-refresh"
											id="HotelChain"></span></a></li>
									<li><a href="#" class="panel-fullscreen"><span
											class="fa fa-expand"></span></a></li>

								</ul>
							</div>
							<div class="panel-body panel-body-table LimitedHeight">

								<div class="list-group"
									ng-if="hotelData.hotelList != null && hotelData.hotelList.length>0"
									ng-repeat="provider in hotelData.hotelList">
									<a class="list-group-item" href="#"><i
										class="fa fa-hotel fa-fw" aria-hidden="true"></i>
										{{provider.name}} <span>%</span><span
										ng-bind="provider.percentage"></span></a>

								</div>
								<div class="list-group" ng-if="hotelData.hotelList == null">
									-------- no data ------</div>

							</div>
						</div>
					</div>
					<s:if test="%{#session.Company.companyRole.isSuperUser()}">
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Flight Providers</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">

										<li><a href=""
											ng-click="refreshService('FlightProvider')"
											class="panel-refresh"><span id="FlightProvider"
												class="fa fa-refresh"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="airProviderData.apiProviderList != null && airProviderData.apiProviderList.length>0"
										ng-repeat="provider in airProviderData.apiProviderList">
										<a class="list-group-item" href="#"><i
											class="fa fa-plane fa-fw" aria-hidden="true"></i>
											{{provider.name}} <span>%</span><span
											ng-bind="provider.percentage"></span></a>

									</div>
									<div class="list-group"
										ng-if="airProviderData.apiProviderList == null">
										-------- no data ------</div>
								</div>
							</div>
						</div>
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Hotel Providers</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">
										<li><a href="" ng-click="refreshService('HotelProvider')"
											class="panel-refresh"><span id="HotelProvider"
												class="fa fa-refresh"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="hotelProviderData.apiProviderList != null && hotelProviderData.apiProviderList.length>0"
										ng-repeat="provider in hotelProviderData.apiProviderList">
										<a class="list-group-item" href="#"><i
											class="fa fa-hotel fa-fw" aria-hidden="true"></i>
											{{provider.name}} <span>%</span><span
											ng-bind="provider.percentage"></span></a>

									</div>
									<div class="list-group"
										ng-if="hotelProviderData.apiProviderList == null">
										-------- no data ------</div>
								</div>
							</div>
						</div>
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Car Providers</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">
										<li><a href="" ng-click="refreshService('CarProvider')"
											class="panel-refresh"><span id="CarProvider"
												class="fa fa-refresh"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="carData.carProviderList != null && carData.carProviderList.length>0"
										ng-repeat="provider in carData.carProviderList">
										<a class="list-group-item" href="#"><i
											class="fa fa-car fa-fw" aria-hidden="true"></i>
											{{provider.name}} <span>%</span><span
											ng-bind="provider.percentage"></span></a>

									</div>
									<div class="list-group" ng-if="carData.carProviderList == null">
										-------- no data ------</div>
								</div>
							</div>
						</div>

						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Bus Providers</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">
										<li><a href="" ng-click="refreshService('BusProvider')"
											class="panel-refresh"><span id="BusProvider"
												class="fa fa-refresh"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="busData.busCompanyNameList != null && busData.busCompanyNameList.length>0"
										ng-repeat="provider in busData.busCompanyNameList">
										<a class="list-group-item" href="#"><i
											class="fa fa-bus fa-fw" aria-hidden="true"></i>{{provider.name}}<span>
												<span ng-bind="provider.percentage " class="pull-left"></span>
												<span class="pull-right"> %</span>
										</span></a>
									</div>
									<div class="list-group"
										ng-if="busData.busCompanyNameList == null">-------- no
										data ------</div>
								</div>
							</div>
						</div>
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Train Providers</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">

										<li><a href="" ng-click="refreshService('TrainProvider')"
											class="panel-refresh"><span id="TrainProvider"
												class="fa fa-refresh"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="trainData.trainList != null && trainData.trainList.length > 0"
										ng-repeat="provider in trainData.trainList">
										<a class="list-group-item" href="#"><i
											class="fa fa-train fa-fw" aria-hidden="true"></i>{{provider.name}}<span>
												<span ng-bind="provider.percentage" class="pull-left"></span>
												<span class="pull-right"> %</span>
										</span></a>
									</div>
									<div class="list-group" ng-if="trainData.trainList == null">
										-------- no data ------</div>
								</div>
							</div>
						</div>
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Visa Providers</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">

										<li><a href="" ng-click="refreshService('VisaProvider')"
											class="panel-refresh"><span class="fa fa-refresh"
												id="VisaProvider"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="visaData.visaList != null && visaData.visaList.length > 0"
										ng-repeat="provider in visaData.visaList">
										<a class="list-group-item" href="#"><i
											class="fa fa fa-cc-visa" aria-hidden="true"></i>&nbsp
											&nbsp{{provider.name}}<span> <span
												ng-bind="provider.percentage" class="pull-left"></span> <span
												class="pull-right"> %</span>
										</span></a>
									</div>
									<div class="list-group" ng-if="visaData.visaList == null">
										-------- no data ------</div>
								</div>
							</div>
						</div>
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Insurance Providers</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">

										<li><a href=""
											ng-click="refreshService('InsuranceProvider')"
											class="panel-refresh"><span class="fa fa-refresh"
												id="InsuranceProvider"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="insuranceData.insuranceList != null && insuranceData.insuranceList.length > 0"
										ng-repeat="provider in insuranceData.insuranceList">
										<a class="list-group-item" href="#"><i
											class="fa fa-medkit" aria-hidden="true"></i>&nbsp
											&nbsp{{provider.name}}<span> <span
												ng-bind="provider.percentage" class="pull-left"></span> <span
												class="pull-right"> %</span>
										</span></a>
									</div>
									<div class="list-group"
										ng-if="insuranceData.insuranceList == null">-------- no
										data ------</div>
								</div>
							</div>
						</div>

						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Misc Providers</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">

										<li><a href="" ng-click="refreshService('MiscProvider')"
											class="panel-refresh"><span class="fa fa-refresh"
												id="MiscProvider"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="miscellaneousData.miscellaneousList != null && miscellaneousData.miscellaneousList.length > 0"
										ng-repeat="provider in miscellaneousData.miscellaneousList">
										<a class="list-group-item" href="#"><i class="fa fa-cogs"
											aria-hidden="true"></i>&nbsp &nbsp{{provider.name}}<span>
												<span ng-bind="provider.percentage" class="pull-left"></span>
												<span class="pull-right"> %</span>
										</span></a>
									</div>
									<div class="list-group"
										ng-if="miscellaneousData.miscellaneousList == null">
										-------- no data ------</div>
								</div>
							</div>
						</div>
					</s:if>

					<s:if test="%{#session.Company.companyRole.isSuperUser()}">
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Distributors</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">

										<li><a href="" ng-click="refreshService('Distributor')"
											class="panel-refresh"><span class="fa fa-refresh"
												id="Distributor"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">

									<div class="list-group"
										ng-if="distributors.distributorList != null && distributors.distributorList.length>0"
										ng-repeat="distributor in distributors.distributorList">
										<%-- <a class="list-group-item" href="#"><i class="fa fa-money fa-fw" aria-hidden="true"></i>{{distributor.name}}
									        <span ng-bind="distributor.totalAmount"><label> </label></span>  </a> --%>
										<a class="list-group-item" href="#"><i
											class="fa fa-building fa-fw" aria-hidden="true"></i>{{distributor.name}}<span>
												<span ng-bind="distributor.totalAmount " class="pull-left">
											</span> <span class="pull-left"><span>({{distributor.petcentage}})
												</span></span><span class="pull-right"> %</span>
										</span></a>
									</div>
									<div class="list-group"
										ng-if="distributors.distributorList == null">-------- no
										data ------</div>

								</div>
							</div>
						</div>
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Corporate(s)</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">

										<li><a href="" ng-click="refreshService('Corporate')"
											class="panel-refresh"><span class="fa fa-refresh"
												id="Corporate"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">
									<div class="list-group"
										ng-if="corporatesData.corporateList != null && corporatesData.corporateList.length > 0"
										ng-repeat="corporate in corporatesData.corporateList">
										<%-- <a class="list-group-item" href="#"><i
									class="fa fa-money fa-fw" aria-hidden="true"></i>
									{{corporate.name}} <span ng-bind="corporate.totalAmount"></span></a> --%>
										<a class="list-group-item" href="#"><i
											class="fa fa-building fa-fw" aria-hidden="true"></i>{{corporate.name}}<span>
												<span ng-bind="corporate.totalAmount " class="pull-left">
											</span> <span class="pull-left"><span>({{corporate.petcentage}})
												</span></span><span class="pull-right"> %</span>
										</span></a>
									</div>
									<div class="list-group"
										ng-if="corporatesData.corporateList == null">-------- no
										data ------</div>

								</div>
							</div>
						</div>
					</s:if>

					<s:if
						test="%{#session.Company.companyRole.isSuperUser() || #session.Company.companyRole.isDistributor()}">
						<div class="col-sm-3">

							<div class="panel panel-default">
								<div class="panel-heading clearfix">
									<div class="panel-title-box">
										<h3>Top 10 Agents</h3>
									</div>
									<ul class="panel-controls" style="margin-top: 2px;">

										<li><a href="" ng-click="refreshService('Agents')"
											class="panel-refresh"><span class="fa fa-refresh"
												id="Agents"></span></a></li>
										<li><a href="#" class="panel-fullscreen"><span
												class="fa fa-expand"></span></a></li>

									</ul>
								</div>
								<div class="panel-body panel-body-table LimitedHeight">

									<div class="list-group"
										ng-if="agencies.agencyList != null && agencies.agencyList.length>0"
										ng-repeat="agent in agencies.agencyList">
										<%-- <a class="list-group-item" href="#"><i
									class="fa fa-home fa-fw" aria-hidden="true"></i> {{agent.name}}
									<span ng-bind="agent.totalAmount"></span></a> --%>
										<a class="list-group-item" href="#"><i
											class="fa fa-building fa-fw" aria-hidden="true"></i>{{agent.name}}<span>
												<span ng-bind="agent.totalAmount " class="pull-left">
											</span> <span class="pull-left"><span>({{agent.petcentage}})
												</span></span><span class="pull-right"> %</span>
										</span></a>
									</div>
									<div class="list-group" ng-if="agencies.agencyList == null ">--------
										no data ------</div>

								</div>
							</div>
						</div>

					</s:if>
				</div>

			</div>



			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Flight Sales Reports</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<%--  <li>
                                            <div id="reportrange" class="dtrange dropdown-toggle" data-toggle="dropdown">                                            
                                                <span>July 12, 2017 - August 10, 2017</span><b class="caret"></b>
                                            </div>     
                                            <ul class="daterangepicker dropdown-menu">
                                                <li><a href="#"> Today </a></li>
                                                
                                                 <li><a href="#"> Yesterday </a></li>
                                                  <li><a href="#"> 30 Days Back </a></li>
                                                  <li><a href="#"> 3 Months Back </a></li>
                                                 
                                            </ul>                                 
                                        </li> --%>
								<li id="flight-list" style="display: none; color: green">Refreshing...</li>
								<li><a href=""
									ng-click="refreshService('FlightSalesReport')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="FlightSalesReport"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>

								<%--  <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="fa fa-cog"></span></a>                                        
                                            <ul class="dropdown-menu">
                                                <li><a href="#" class="panel-collapse"><span class="fa fa-angle-down"></span> Collapse</a></li>
                                                <li><a href="#" class="panel-remove"><span class="fa fa-times"></span> Remove</a></li>
                                            </ul>                                        
                                        </li>       --%>
							</ul>
						</div>
						<div class="panel-body panel-body-table LimitedHeight">

							<div class="table-responsive">
								<table
									class="table table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>PNR</th>
											<th>BK Source</th>
											<th>Agency</th>
											<th>Dept Date</th>
											<th>Pax Full Name</th>
											<th>Status</th>
											<!-- <th >Remarks</th>  -->
											<!--  <th >Associated Bookings</th> -->
											<th>Report List</th>
											<th>Order List</th>
										</tr>
									</thead>
									<tbody ng-repeat="name in commonTopFivePojoFlightList">
										<tr>
											<td><strong>{{name.pnr}}</strong></td>

											<td><strong>{{name.source}}</strong></td>

											<td><strong>{{name.agencyName}}</strong></td>
											<td>{{name.departureDate | date:' dd-MM-yyyy'}}</td>
											<td>{{name.name}}</td>
											<td><span class="label label-danger">{{name.status}}</span></td>
											<!--  <td> Need Special Assistance  </td> -->
											<%-- <td> 
                                                    <ul class="booking-controls">
	                                                    <li><a href="#" class="plane" ><span class="fa fa-plane"></span>
	                                                    </a></li>
	                                                     <li><a href="#" class="hotel "><span class="fa fa-hotel"></span>
	                                                    </a></li>
	                                                     <li><a href="#" class="bus "><span class="fa fa-bus"></span>
	                                                    </a></li>
	                                                    <li> <a href="#" class="car "><span class="fa fa-car"></span>
	                                                    </a></li>
	                                                     <li><a href="#" class="train "><span class="fa fa-train"></span>
	                                                    </a></li>
	                                                     <li><a href="#" class="visa "><span class="fa fa-cc-visa"></span>
	                                                    </a></li>
	                                                     <li><a href="#" class="medkit "><span class="fa fa-medkit"></span>
	                                                    </a></li> 
                                                    </ul>
                                                    
                                                    </td> --%>
											<td><a href="companyReportList"
												class="btn btn-default btn-blue">see all reports</a></td>
											<td><a href="companyFlightOrderList"
												class="btn btn-default btn-blue">see all Orders </a></td>
											<%--  <td>
                                                    <ul class="booking-controls">
                                                    <li><a href="#" class="edit"><span class="fa fa-pencil-square-o"></span></a></li>
                                                    <li><a href="#" class="close"><span class="fa fa-close"></span></a></li>
                                                    </ul>
 	                                                </td> --%>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>




				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Hotel Sales Reports</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li id="hotel-list" style="display: none; color: green">Refreshing...</li>
								<li><a href=""
									ng-click="refreshService('HotelSalesReport')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="HotelSalesReport"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>
							</ul>

						</div>
						<div class="panel-body panel-body-table LimitedHeight">

							<div class="table-responsive">
								<table
									class="table table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>OrderId</th>
											<th>City</th>
											<th>Agency</th>
											<th>Checkin Date</th>
											<th>Pax Name</th>
											<th>Status</th>
											<!-- <th >Remarks</th>  -->
											<!--  <th >Associated Bookings</th> -->
											<th>Report List</th>
											<th>Order List</th>
										</tr>
									</thead>
									<tbody ng-repeat="name in commonTopFivePojoHotelList">
										<tr>
											<td><strong>{{name.orderId}}</strong></td>
											<td><strong>{{name.city}}</strong></td>
											<td><strong>{{name.agencyName}}</strong></td>
											<td>{{name.hotelCheckInDate | date:' dd-MM-yyyy'}}</td>
											<td>{{name.name}}</td>
											<td><span class="label label-danger">{{name.status}}</span></td>
											<td><a href="companyHotelReports"
												class="btn btn-default btn-blue">see all reports</a></td>
											<td><a href="companyHotelOrders"
												class="btn btn-default btn-blue">see all Orders </a></td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>


				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Bus Sales Reports</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li id="bus-list" style="display: none; color: green">Refreshing...</li>
								<li><a href="" ng-click="refreshService('BusSalesReport')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="BusSalesReport"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>
							</ul>

						</div>
						<div class="panel-body panel-body-table LimitedHeight">

							<div class="table-responsive">
								<table
									class="table table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>OrderId</th>
											<th>City</th>
											<th>Agency</th>
											<th>Booking Date</th>
											<th>Pax Full Name</th>
											<th>Status</th>
											<!-- <th >Remarks</th>  -->
											<!--  <th >Associated Bookings</th> -->
											<th>Report List</th>
											<th>Order List</th>
										</tr>
									</thead>
									<tbody ng-repeat="name in commonTopFivePojoBusList">
										<tr>
											<td><strong>{{name.orderId}}</strong></td>

											<td><strong>{{name.city}}</strong></td>

											<td><strong>{{name.agencyName}}</strong></td>
											<td>{{name.bookingDate | date:' dd-MM-yyyy'}}</td>
											<td>{{name.name}}</td>
											<td><span class="label label-danger">{{name.status}}</span></td>
											<td><a href="companyBusReports"
												class="btn btn-default btn-blue">see all reports</a></td>
											<td><a href="companyBusOrders"
												class="btn btn-default btn-blue">see all Orders </a></td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>


				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Car Sales Reports</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href="" ng-click="refreshService('CarSalesReport')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="CarSalesReport"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>
							</ul>
						</div>
						<div class="panel-body panel-body-table LimitedHeight">

							<div class="table-responsive">
								<table
									class="table table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>Order Id</th>
											<th>City</th>
											<th>Agency</th>
											<th>Booking Date</th>
											<th>Pax Full Name</th>
											<th>Status</th>
											<!-- <th >Remarks</th>  -->
											<!--  <th >Associated Bookings</th> -->
											<th>Report List</th>
											<th>Order List</th>
										</tr>
									</thead>
									<tbody ng-repeat="name in commonTopFivePojoCarList">
										<tr>
											<td><strong>{{name.orderId}}</strong></td>

											<td><strong>{{name.city}}</strong></td>

											<td><strong>{{name.agencyName}}</strong></td>
											<td>{{name.bookingDate | date:' dd-MM-yyyy'}}</td>
											<td>{{name.name}}</td>
											<td><span class="label label-danger">{{name.status}}</span></td>
											<td><a href="companyCarReports"
												class="btn btn-default btn-blue">see all reports</a></td>
											<td><a href="companyCarOrders"
												class="btn btn-default btn-blue">see all Orders </a></td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Train Sales Reports</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href=""
									ng-click="refreshService('TrainSalesReport')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="TrainSalesReport"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>
							</ul>
						</div>
						<div class="panel-body panel-body-table LimitedHeight">

							<div class="table-responsive">
								<table
									class="table table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>OrderId</th>
											<th>From Location</th>
											<th>Agency</th>
											<th>Booking Date</th>
											<th>Pax Full Name</th>
											<th>Status</th>
											<!-- <th >Remarks</th>  -->
											<!--  <th >Associated Bookings</th> -->
											<th>Report List</th>
											<th>Order List</th>
										</tr>
									</thead>
									<tbody ng-repeat="name in commonTopFivePojoTrainList">
										<tr>
											<td><strong>{{name.orderId}}</strong></td>
											<td><strong>{{name.fromLocation}}</strong></td>

											<td><strong>{{name.agencyName}}</strong></td>
											<td>{{name.bookingDate | date:' dd-MM-yyyy'}}</td>
											<td>{{name.name}}</td>
											<td><span class="label label-danger">{{name.status}}</span></td>
											<td><a href="companyTrainReports"
												class="btn btn-default btn-blue">see all reports</a></td>
											<td><a href="companyTrainOrders"
												class="btn btn-default btn-blue">see all Orders </a></td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>visa Sales Reports</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href="" ng-click="refreshService('VisaSalesReport')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="VisaSalesReport"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>
							</ul>

						</div>
						<div class="panel-body panel-body-table LimitedHeight">

							<div class="table-responsive">
								<table
									class="table table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>OrderId</th>

											<th>Agency</th>
											<th>Booking Date</th>
											<th>Pax Full Name</th>
											<th>Status</th>
											<!-- <th >Remarks</th>  -->
											<!--  <th >Associated Bookings</th> -->
											<th>Report List</th>
											<th>Order List</th>
										</tr>
									</thead>
									<tbody ng-repeat="name in commonTopFivePojoVisaList">
										<tr>
											<td><strong>{{name.orderId}}</strong></td>
											<td><strong>{{name.agencyName}}</strong></td>
											<td>{{name.bookingDate | date:' dd-MM-yyyy'}}</td>
											<td>{{name.name}}</td>
											<td><span class="label label-danger">{{name.status}}</span></td>
											<td><a href="companyVisaReports"
												class="btn btn-default btn-blue">see all reports</a></td>
											<td><a href="companyVisaOrders"
												class="btn btn-default btn-blue">see all Orders </a></td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Insurance Sales Reports</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href=""
									ng-click="refreshService('InsuranceSalesReport')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="InsuranceSalesReport"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>
							</ul>
						</div>
						<div class="panel-body panel-body-table LimitedHeight">

							<div class="table-responsive">
								<table
									class="table table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>OrderId</th>
											<th>City</th>
											<th>Agency</th>
											<th>Booking Date</th>
											<th>Pax Full Name</th>
											<th>Status</th>
											<!-- <th >Remarks</th>  -->
											<!--  <th >Associated Bookings</th> -->
											<th>Report List</th>
											<th>Order List</th>
										</tr>
									</thead>
									<tbody ng-repeat="name in commonTopFivePojoInsuranceList">
										<tr>
											<td><strong>{{name.orderId}}</strong></td>

											<td><strong>{{name.city}}</strong></td>

											<td><strong>{{name.agencyName}}</strong></td>
											<td>{{name.bookingDate | date:' dd-MM-yyyy'}}</td>
											<td>{{name.name}}</td>
											<td><span class="label label-danger">{{name.status}}</span></td>
											<td><a href="companyInsuranceReports"
												class="btn btn-default btn-blue">see all reports</a></td>
											<td><a href="companyInsuranceOrders"
												class="btn btn-default btn-blue">see all Orders </a></td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<div class="panel-title-box">
								<h3>Miscellaneous Sales Reports</h3>
							</div>
							<ul class="panel-controls" style="margin-top: 2px;">
								<li><a href="" ng-click="refreshService('MiscSalesReport')"
									class="panel-refresh"><span class="fa fa-refresh"
										id="MiscSalesReport"></span></a></li>
								<li><a href="#" class="panel-fullscreen"><span
										class="fa fa-expand"></span></a></li>
							</ul>
						</div>
						<div class="panel-body panel-body-table LimitedHeight">

							<div class="table-responsive">
								<table
									class="table table-condensed table-bordered table-striped">
									<thead>
										<tr>
											<th>OrderId</th>
											<th>Agency</th>
											<th>Booking Date</th>
											<th>Pax Full Name</th>
											<th>Status</th>
											<!-- <th >Remarks</th>  -->
											<!--  <th >Associated Bookings</th> -->
											<th>Report List</th>
											<th>Order List</th>
										</tr>
									</thead>
									<tbody ng-repeat="name in commonTopFivePojoMiscList">
										<tr>
											<td><strong>{{name.orderId}}</strong></td>
											<td><strong>{{name.agencyName}}</strong></td>
											<td>{{name.bookingDate | date:' dd-MM-yyyy'}}</td>
											<td>{{name.name}}</td>
											<td><span class="label label-danger">{{name.status}}</span></td>
											<td><a href="companyMiscellaneousReports"
												class="btn btn-default btn-blue">see all reports</a></td>
											<td><a href="companyMiscellaneousOrders"
												class="btn btn-default btn-blue">see all Orders </a></td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->
	</section>
</div>
<!-- /.content-wrapper -->

<script src="js/jquery.min.js" type="text/javascript"></script>

<script src="js/jquerycalUI.js"></script>
<%-- <script src="js/angular.js"></script> --%>
<script src="js/ui-bootstrap-tpls-0.9.0.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/custom.js"></script>
<script src="js/listcomplete.js?ver=5.0" defer></script>
<%@ include file="DashMainContentFooter.jsp"%>

<%-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script> --%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.3/Chart.min.js"></script>
<%-- <script src="js/Chart.min.js"></script> --%>
<script src="js/chartDataSample.js"></script>

<script>
	$('document').ready(function() {
		$(function() {
			getgraphdata();

			/* getHotelBarData(); */
		});
	});

	var app = angular.module('airDataApp', []);
	app
			.controller(
					'airDataCtrl',
					function($scope, $http, $timeout) {
						$scope.init = function() {
							// $scope.liveBookcount();
							$scope.defaultCall();
						}
						$scope.flightCount = {};
						$scope.hotelCount = {};
						$scope.busCount = {};
						$scope.carCount = {};
						$scope.trainCount = {};
						$scope.insuranceCount = {};
						$scope.visaCount = {};
						$scope.miscCount = {};
						$scope.defaultCall = function() {
							var currentUrl = $(location).attr('href');
							var baseUrl = currentUrl.substr(0, currentUrl
									.lastIndexOf('/') + 1);
							$http.get(baseUrl + "fetchHotelWeeklySalesJson")
									.then(function(response) {
										$scope.hotelweeklyData = response.data;
									});

							$http.get(baseUrl + "getAirlineBookingCount").then(
									function(response) {
										$scope.airData = response.data.airData;
									});

							$http
									.get(baseUrl + "getHotelNameBookingCount")
									.then(
											function(response) {
												$scope.hotelData = response.data.hotelData;
											});

							$http
									.get(
											baseUrl
													+ "getAirApiProviderBookingCount")
									.then(
											function(response) {
												$scope.airProviderData = response.data.airData;
											});

							$http
									.get(
											baseUrl
													+ "getHotelApiProviderBookingCount")
									.then(
											function(response) {
												$scope.hotelProviderData = response.data.hotelData;
											});

							$http.get(baseUrl + "getCarApiProviderCount").then(
									function(response) {
										$scope.carData = response.data.carData;
									});
							$http.get(baseUrl + "getBusApiProviderCount").then(
									function(response) {
										$scope.busData = response.data.busData;
									});
							$http
									.get(baseUrl + "getTrainApiProviderCount")
									.then(
											function(response) {
												$scope.trainData = response.data.trainData;
											});
							$http
									.get(baseUrl + "getVisaApiProviderCount")
									.then(
											function(response) {
												$scope.visaData = response.data.visaData;
											});

							$http
									.get(
											baseUrl
													+ "getInsuranceApiProviderCount")
									.then(
											function(response) {
												$scope.insuranceData = response.data.insuranceData;
											});

							$http
									.get(
											baseUrl
													+ "getMiscellaneousApiProviderCount")
									.then(
											function(response) {
												$scope.miscellaneousData = response.data.miscellaneousData;
											});
							$http
									.get(baseUrl + "distributorBookingCount")
									.then(
											function(response) {
												$scope.distributors = response.data.companyData;
											});
							$http
									.get(baseUrl + "agencyBookingCount")
									.then(
											function(response) {
												$scope.agencies = response.data.companyData;
											});
							$http
									.get(baseUrl + "corporateBookingCount")
									.then(
											function(response) {
												$scope.corporatesData = response.data.companyData;
											});

							$http
									.get(baseUrl + "getFlightAnalysisJson")
									.then(
											function(response) {
												$scope.flightCount = response.data;
												/* $scope.flightCount={"jsonobj":{"bookingAmountW":2500,"countW":2,"countM":6,								
													"bookingAmountD":80000,"bookingAmountM":6500,"countD":20},"onlyFlightOrderJson":"success"}; */
												$http
														.get(
																baseUrl
																		+ "getOnlyHotelOrderJson")
														.then(
																function(
																		response) {
																	$scope.hotelCount = response.data;
																	/* $scope.hotelCount={"jsonobj":{"bookingAmountW":2500,"countW":2,"countM":6,								
																		"bookingAmountD":30000,"bookingAmountM":6500,"countD":5},"onlyFlightOrderJson":"success"}; */
																	$http
																			.get(
																					baseUrl
																							+ "getBusAnalysisJson")
																			.then(
																					function(
																							response) {
																						$scope.busCount = response.data;
																						/* $scope.busCount={"jsonobj":{"bookingAmountW":2500,"countW":2,"countM":6,								
																							"bookingAmountD":16000,"bookingAmountM":6500,"countD":10},"onlyFlightOrderJson":"success"}; */
																						$http
																								.get(
																										baseUrl
																												+ "getCarAnalysisJson")
																								.then(
																										function(
																												response) {
																											$scope.carCount = response.data;
																											/* $scope.carCount={"jsonobj":{"bookingAmountW":2500,"countW":2,"countM":6,								
																												"bookingAmountD":2500,"bookingAmountM":6500,"countD":5},"onlyFlightOrderJson":"success"}; */
																											$http
																													.get(
																															baseUrl
																																	+ "getTrainAnalysisJson")
																													.then(
																															function(
																																	response) {
																																$scope.trainCount = response.data;
																																/* $scope.trainCount={"jsonobj":{"bookingAmountW":2500,"countW":2,"countM":6,								
																																	"bookingAmountD":550,"bookingAmountM":6500,"countD":1},"onlyFlightOrderJson":"success"}; */
																																$http
																																		.get(
																																				baseUrl
																																						+ "getOnlyInsuranceOrderJson")
																																		.then(
																																				function(
																																						response) {
																																					$scope.insuranceCount = response.data;
																																					/* $scope.insuranceCount={"jsonobj":{"bookingAmountW":2500,"countW":2,"countM":6,					
																																						"bookingAmountD":1000,"bookingAmountM":6500,"countD":4},"onlyFlightOrderJson":"success"}; */
																																					$http
																																							.get(
																																									baseUrl
																																											+ "getOnlyVisaOrderJson")
																																							.then(
																																									function(
																																											response) {
																																										$scope.visaCount = response.data;
																																										/* $scope.visaCount={"jsonobj":{"bookingAmountW":2500,"countW":2,"countM":6,					
																																											"bookingAmountD":6300,"bookingAmountM":6500,"countD":3},"onlyFlightOrderJson":"success"}; */
																																										$http
																																												.get(
																																														baseUrl
																																																+ "getMiscellaneousAnalysisJson")
																																												.then(
																																														function(
																																																response) {
																																															console
																																																	.log(
																																																			'respssdsd',
																																																			response);
																																															$scope.miscCount = response.data;
																																															/* $scope.miscCount={"jsonobj":{"bookingAmountW":2500,"countW":2,"countM":6,								
																																																"bookingAmountD":1600,"bookingAmountM":6500,"countD":2},"onlyFlightOrderJson":"success"}; */
																																															$scope
																																																	.overlallBookcount();
																																														});
																																									});
																																				});
																															});
																										});
																					});
																});
											});

							$scope.finaltodaydata = [];
							$http
									.get(
											baseUrl
													+ "allServicesTodayBookingCount")
									.then(
											function(response) {
												$scope.todaybooking = response.data.servicesData;
												$scope.todaydata = [];
												if ($scope.todaybooking.totalCount != 0
														&& $scope.todaybooking.totalCount != undefined) {
													$scope.todaydata
															.push($scope.todaybooking.flight);
													$scope.todaydata
															.push($scope.todaybooking.hotel);
													$scope.todaydata
															.push($scope.todaybooking.bus);
													$scope.todaydata
															.push($scope.todaybooking.car);
													$scope.todaydata
															.push($scope.todaybooking.train);
													$scope.todaydata
															.push($scope.todaybooking.insurance);
													$scope.todaydata
															.push($scope.todaybooking.visa);
													$scope.todaydata
															.push($scope.todaybooking.misc);
													$scope.finaltodaydata = $scope
															.lostperc($scope.todaydata);
												} else {
													$scope.todaybooking.flight = {
														"cancelledCount" : 0,
														"count" : 0,
														"percentage" : 0.0,
														"serviceType" : "Flight",
														"totalBookingAmount" : 0,
														"totalLossAmount" : 0,
														"totalRefundedAmount" : 0
													};
													$scope.todaybooking.hotel = {
														"cancelledCount" : 0,
														"count" : 0,
														"percentage" : 0.0,
														"serviceType" : "Hotel",
														"totalBookingAmount" : 0,
														"totalLossAmount" : 0,
														"totalRefundedAmount" : 0
													};
													$scope.todaybooking.bus = {
														"cancelledCount" : 0,
														"count" : 0,
														"percentage" : 0.0,
														"serviceType" : "Bus",
														"totalBookingAmount" : 0,
														"totalLossAmount" : 0,
														"totalRefundedAmount" : 0
													};
													$scope.todaybooking.car = {
														"cancelledCount" : 0,
														"count" : 0,
														"percentage" : 0.0,
														"serviceType" : "Car",
														"totalBookingAmount" : 0,
														"totalLossAmount" : 0,
														"totalRefundedAmount" : 0
													};
													$scope.todaybooking.train = {
														"cancelledCount" : 0,
														"count" : 0,
														"percentage" : 0.0,
														"serviceType" : "Train",
														"totalBookingAmount" : 0,
														"totalLossAmount" : 0,
														"totalRefundedAmount" : 0
													};
													$scope.todaybooking.insurance = {
														"cancelledCount" : 0,
														"count" : 0,
														"percentage" : 0.0,
														"serviceType" : "Insurance",
														"totalBookingAmount" : 0,
														"totalLossAmount" : 0,
														"totalRefundedAmount" : 0
													};
													$scope.todaybooking.visa = {
														"cancelledCount" : 0,
														"count" : 0,
														"percentage" : 0.0,
														"serviceType" : "Visa",
														"totalBookingAmount" : 0,
														"totalLossAmount" : 0,
														"totalRefundedAmount" : 0
													};
													$scope.todaybooking.misc = {
														"cancelledCount" : 0,
														"count" : 0,
														"percentage" : 0.0,
														"serviceType" : "Misc",
														"totalBookingAmount" : 0,
														"totalLossAmount" : 0,
														"totalRefundedAmount" : 0
													};

													$scope.todaydata
															.push($scope.todaybooking.flight);
													$scope.todaydata
															.push($scope.todaybooking.hotel);
													$scope.todaydata
															.push($scope.todaybooking.bus);
													$scope.todaydata
															.push($scope.todaybooking.car);
													$scope.todaydata
															.push($scope.todaybooking.train);
													$scope.todaydata
															.push($scope.todaybooking.insurance);
													$scope.todaydata
															.push($scope.todaybooking.visa);
													$scope.todaydata
															.push($scope.todaybooking.misc);
													$scope.finaltodaydata = $scope
															.lostperc($scope.todaydata);
												}
											});

						}

						$scope.refreshService = function(type) {
							var actionName = '';

							if (type == 'AllServicesWeeklySales')
								actionName = "getAllServicesWeeklySales";

							if (type == 'Airline')
								actionName = "getAirlineBookingCount";

							if (type == 'HotelChain')
								actionName = "getHotelNameBookingCount";

							if (type == 'FlightProvider')
								actionName = "getAirApiProviderBookingCount";

							if (type == 'HotelProvider')
								actionName = "getHotelApiProviderBookingCount";

							if (type == 'CarProvider')
								actionName = "getCarApiProviderCount";

							if (type == 'BusProvider')
								actionName = "getBusApiProviderCount";

							if (type == 'TrainProvider')
								actionName = "getTrainApiProviderCount";

							if (type == 'VisaProvider')
								actionName = "getVisaApiProviderCount";

							if (type == 'InsuranceProvider')
								actionName = "getInsuranceApiProviderCount";

							if (type == 'MiscProvider')
								actionName = "getMiscellaneousApiProviderCount";

							if (type == 'Distributor')
								actionName = "distributorBookingCount";

							if (type == 'Corporate')
								actionName = "corporateBookingCount";

							if (type == 'Agents')
								actionName = "agencyBookingCount";

							if (type == 'FlightSalesReport')
								actionName = "getFlightBookingList";

							if (type == 'HotelSalesReport')
								actionName = "getHotelBookingList";

							if (type == 'BusSalesReport')
								actionName = "getBusBookingList";

							if (type == 'CarSalesReport')
								actionName = "getCarBookingList";

							if (type == 'TrainSalesReport')
								actionName = "getTrainBookingList";

							if (type == 'VisaSalesReport')
								actionName = "getVisaBookingList";

							if (type == 'InsuranceSalesReport')
								actionName = "getInsuranceBookingList";

							if (type == 'MiscSalesReport')
								actionName = "getMiscellaneousBookingList";

							if (type == 'todaySalesReport')
								actionName = "allServicesTodayBookingCount";

							$http
									.get(baseUrl + actionName)
									.then(
											function(response) {
												if (type == 'AllServicesCount') {
													$scope.refreshLoader(type);
													$http
															.get(
																	baseUrl
																			+ "getFlightAnalysisJson")
															.then(
																	function(
																			response) {
																		$scope.flightCount = response.data;
																	});
													$http
															.get(
																	baseUrl
																			+ "getBusAnalysisJson")
															.then(
																	function(
																			response) {
																		$scope.busCount = response.data;
																	});
													$http
															.get(
																	baseUrl
																			+ "getCarAnalysisJson")
															.then(
																	function(
																			response) {
																		$scope.carCount = response.data;
																	});
													$http
															.get(
																	baseUrl
																			+ "getTrainAnalysisJson")
															.then(
																	function(
																			response) {
																		$scope.trainCount = response.data;
																	});
													$http
															.get(
																	baseUrl
																			+ "getOnlyHotelOrderJson")
															.then(
																	function(
																			response) {
																		$scope.hotelCount = response.data;
																	});
													$http
															.get(
																	baseUrl
																			+ "getOnlyInsuranceOrderJson")
															.then(
																	function(
																			response) {
																		$scope.insuranceCount = response.data;
																	});
													$http
															.get(
																	baseUrl
																			+ "getOnlyVisaOrderJson")
															.then(
																	function(
																			response) {
																		$scope.visaCount = response.data;
																	});
													$http
															.get(
																	baseUrl
																			+ "getMiscellaneousAnalysisJson")
															.then(
																	function(
																			response) {
																		$scope.miscCount = response.data;
																		$scope
																				.overlallBookcount();
																	});

												}
												if (type == 'todaySalesReport') {
													$scope.refreshLoader(type);
													$scope.todaybooking = response.data.servicesData;
													$scope.todaydata = [];
													if ($scope.todaybooking.totalCount != 0) {
														$scope.todaydata
																.push($scope.todaybooking.flight);
														$scope.todaydata
																.push($scope.todaybooking.hotel);
														$scope.todaydata
																.push($scope.todaybooking.bus);
														$scope.todaydata
																.push($scope.todaybooking.car);
														$scope.todaydata
																.push($scope.todaybooking.train);
														$scope.todaydata
																.push($scope.todaybooking.insurance);
														$scope.todaydata
																.push($scope.todaybooking.visa);
														$scope.todaydata
																.push($scope.todaybooking.misc);
														$scope.finaltodaydata = $scope
																.lostperc($scope.todaydata);
													} else {
														$scope.todaybooking.flight = {
															"cancelledCount" : 0,
															"count" : 0,
															"percentage" : 0.0,
															"serviceType" : "Flight",
															"totalBookingAmount" : 0,
															"totalLossAmount" : 0,
															"totalRefundedAmount" : 0
														};
														$scope.todaybooking.hotel = {
															"cancelledCount" : 0,
															"count" : 0,
															"percentage" : 0.0,
															"serviceType" : "Hotel",
															"totalBookingAmount" : 0,
															"totalLossAmount" : 0,
															"totalRefundedAmount" : 0
														};
														$scope.todaybooking.bus = {
															"cancelledCount" : 0,
															"count" : 0,
															"percentage" : 0.0,
															"serviceType" : "Bus",
															"totalBookingAmount" : 0,
															"totalLossAmount" : 0,
															"totalRefundedAmount" : 0
														};
														$scope.todaybooking.car = {
															"cancelledCount" : 0,
															"count" : 0,
															"percentage" : 0.0,
															"serviceType" : "Car",
															"totalBookingAmount" : 0,
															"totalLossAmount" : 0,
															"totalRefundedAmount" : 0
														};
														$scope.todaybooking.train = {
															"cancelledCount" : 0,
															"count" : 0,
															"percentage" : 0.0,
															"serviceType" : "Train",
															"totalBookingAmount" : 0,
															"totalLossAmount" : 0,
															"totalRefundedAmount" : 0
														};
														$scope.todaybooking.insurance = {
															"cancelledCount" : 0,
															"count" : 0,
															"percentage" : 0.0,
															"serviceType" : "Insurance",
															"totalBookingAmount" : 0,
															"totalLossAmount" : 0,
															"totalRefundedAmount" : 0
														};
														$scope.todaybooking.visa = {
															"cancelledCount" : 0,
															"count" : 0,
															"percentage" : 0.0,
															"serviceType" : "Visa",
															"totalBookingAmount" : 0,
															"totalLossAmount" : 0,
															"totalRefundedAmount" : 0
														};
														$scope.todaybooking.misc = {
															"cancelledCount" : 0,
															"count" : 0,
															"percentage" : 0.0,
															"serviceType" : "Misc",
															"totalBookingAmount" : 0,
															"totalLossAmount" : 0,
															"totalRefundedAmount" : 0
														};

														$scope.todaydata
																.push($scope.todaybooking.flight);
														$scope.todaydata
																.push($scope.todaybooking.hotel);
														$scope.todaydata
																.push($scope.todaybooking.bus);
														$scope.todaydata
																.push($scope.todaybooking.car);
														$scope.todaydata
																.push($scope.todaybooking.train);
														$scope.todaydata
																.push($scope.todaybooking.insurance);
														$scope.todaydata
																.push($scope.todaybooking.visa);
														$scope.todaydata
																.push($scope.todaybooking.misc);
														$scope.finaltodaydata = $scope
																.lostperc($scope.todaydata);
													}

												}

												if (type == 'Airline') {
													$scope.airData = response.data.airData;
													$scope.refreshLoader(type)
												}
												if (type == 'HotelChain') {
													$scope.hotelData = response.data.hotelData;
													$scope.refreshLoader(type)
												}

												if (type == 'FlightProvider') {
													$scope.airProviderData = response.data.airData;
													$scope.refreshLoader(type)
												}

												if (type == 'HotelProvider') {
													$scope.hotelProviderData = response.data.hotelData;
													$scope.refreshLoader(type)
												}

												if (type == 'CarProvider') {
													$scope.carData = response.data.carData;
													$scope.refreshLoader(type)
												}

												if (type == 'BusProvider') {
													$scope.busData = response.data.busData;
													$scope.refreshLoader(type)
												}

												if (type == 'TrainProvider') {
													$scope.trainData = response.data.trainData;
													$scope.refreshLoader(type)
												}

												if (type == 'VisaProvider') {
													$scope.visaData = response.data.visaData;
													$scope.refreshLoader(type)
												}

												if (type == 'InsuranceProvider') {
													$scope.insuranceData = response.data.insuranceData;
													$scope.refreshLoader(type)
												}

												if (type == 'MiscProvider') {
													$scope.miscellaneousData = response.data.miscellaneousData;
													$scope.refreshLoader(type)
												}

												if (type == 'Distributor') {
													$scope.distributors = response.data.companyData;
													$scope.refreshLoader(type)
												}

												if (type == 'Corporate') {
													$scope.corporatesData = response.data.companyData;
													$scope.refreshLoader(type)
												}

												if (type == 'Agents') {
													$scope.agencies = response.data.companyData;
													$scope.refreshLoader(type)
												}

												if (type == 'FlightSalesReport') {
													$scope.commonTopFivePojoFlightList = response.data.commonTopFivePojoList;
													$scope.refreshLoader(type)
												}

												if (type == 'HotelSalesReport') {
													$scope.commonTopFivePojoHotelList = response.data.commonTopFivePojoList;
													$scope.refreshLoader(type)
												}

												if (type == 'BusSalesReport') {
													$scope.commonTopFivePojoBusList = response.data.commonTopFivePojoList;
													$scope.refreshLoader(type)
												}

												if (type == 'CarSalesReport') {
													$scope.commonTopFivePojoCarList = response.data.commonTopFivePojoList;
													$scope.refreshLoader(type)
												}

												if (type == 'TrainSalesReport') {
													$scope.commonTopFivePojoTrainList = response.data.commonTopFivePojoList;
													$scope.refreshLoader(type)
												}

												if (type == 'VisaSalesReport') {
													$scope.commonTopFivePojoVisaList = response.data.commonTopFivePojoList;
													$scope.refreshLoader(type)
												}

												if (type == 'InsuranceSalesReport') {
													$scope.commonTopFivePojoInsuranceList = response.data.commonTopFivePojoList;
													$scope.refreshLoader(type)
												}

												if (type == 'MiscSalesReport') {
													$scope.commonTopFivePojoMiscList = response.data.commonTopFivePojoList;
													$scope.refreshLoader(type)
												}

											});
						};

						$http
								.get(baseUrl + "getFlightBookingList")
								.then(
										function(response) {
											$scope.commonTopFivePojoFlightList = response.data.commonTopFivePojoList;
										});

						$http
								.get(baseUrl + "getHotelBookingList")
								.then(
										function(response) {
											$scope.commonTopFivePojoHotelList = response.data.commonTopFivePojoList;
										});

						$http
								.get(baseUrl + "getCarBookingList")
								.then(
										function(response) {
											$scope.commonTopFivePojoCarList = response.data.commonTopFivePojoList;
										});

						$http
								.get(baseUrl + "getBusBookingList")
								.then(
										function(response) {
											$scope.commonTopFivePojoBusList = response.data.commonTopFivePojoList;
										});

						$http
								.get(baseUrl + "getTrainBookingList")
								.then(
										function(response) {
											$scope.commonTopFivePojoTrainList = response.data.commonTopFivePojoList;
										});

						$http
								.get(baseUrl + "getVisaBookingList")
								.then(
										function(response) {
											$scope.commonTopFivePojoVisaList = response.data.commonTopFivePojoList;
										});

						$http
								.get(baseUrl + "getInsuranceBookingList")
								.then(
										function(response) {
											$scope.commonTopFivePojoInsuranceList = response.data.commonTopFivePojoList;
										});

						$http
								.get(baseUrl + "getMiscellaneousBookingList")
								.then(
										function(response) {
											$scope.commonTopFivePojoMiscList = response.data.commonTopFivePojoList;
										});

						$scope.refreshLoader = function(type) {
							var time = 0;

							if (type == 'AllServicesCount') {
								$(".panel-refresh #AllServicesCount").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'todaySalesReport') {
								$(".panel-refresh #todaySalesReport").addClass(
										'fa-spin');
								time = 0;
							}

							if (type == 'Airline') {
								$(".panel-refresh #Airline")
										.addClass('fa-spin');
								time = 0;
							}
							if (type == 'HotelChain') {
								$(".panel-refresh #HotelChain").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'FlightProvider') {
								$(".panel-refresh #FlightProvider").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'HotelProvider') {
								$(".panel-refresh #HotelProvider").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'CarProvider') {
								$(".panel-refresh #CarProvider").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'BusProvider') {
								$(".panel-refresh #BusProvider").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'TrainProvider') {
								$(".panel-refresh #TrainProvider").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'VisaProvider') {
								$(".panel-refresh #VisaProvider").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'InsuranceProvider') {
								$(".panel-refresh #InsuranceProvider")
										.addClass('fa-spin');
								time = 0;
							}
							if (type == 'MiscProvider') {
								$(".panel-refresh #MiscProvider").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'Distributor') {
								$(".panel-refresh #Distributor").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'Corporate') {
								$(".panel-refresh #Corporate").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'Agents') {
								$(".panel-refresh #Agents").addClass('fa-spin');
								time = 0;
							}
							if (type == 'FlightSalesReport') {
								$(".panel-refresh #FlightSalesReport")
										.addClass('fa-spin');
								time = 0;
							}
							if (type == 'HotelSalesReport') {
								$(".panel-refresh #HotelSalesReport").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'BusSalesReport') {
								$(".panel-refresh #BusSalesReport").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'CarSalesReport') {
								$(".panel-refresh #CarSalesReport").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'TrainSalesReport') {
								$(".panel-refresh #TrainSalesReport").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'VisaSalesReport') {
								$(".panel-refresh #VisaSalesReport").addClass(
										'fa-spin');
								time = 0;
							}
							if (type == 'InsuranceSalesReport') {
								$(".panel-refresh #InsuranceSalesReport")
										.addClass('fa-spin');
								time = 0;
							}
							if (type == 'MiscSalesReport') {
								$(".panel-refresh #MiscSalesReport").addClass(
										'fa-spin');
								time = 0;
							}

							$timeout(function() {
							}, 3000);

							var timer = function() {
								if (time < 3000) {
									time += 1000;
									$timeout(timer, 1000);
								}
								if (time == 3000) {
									if (type == 'AllServicesCount')
										$(".panel-refresh #AllServicesCount")
												.removeClass('fa-spin');
									if (type == 'todaySalesReport')
										$(".panel-refresh #todaySalesReport")
												.removeClass('fa-spin');
									if (type == 'Airline')
										$(".panel-refresh #Airline")
												.removeClass('fa-spin');
									if (type == 'HotelChain')
										$(".panel-refresh #HotelChain")
												.removeClass('fa-spin');
									if (type == 'FlightProvider')
										$(".panel-refresh #FlightProvider")
												.removeClass('fa-spin');
									if (type == 'HotelProvider')
										$(".panel-refresh #HotelProvider")
												.removeClass('fa-spin');
									if (type == 'CarProvider')
										$(".panel-refresh #CarProvider")
												.removeClass('fa-spin');
									if (type == 'BusProvider')
										$(".panel-refresh #BusProvider")
												.removeClass('fa-spin');
									if (type == 'TrainProvider')
										$(".panel-refresh #TrainProvider")
												.removeClass('fa-spin');
									if (type == 'VisaProvider')
										$(".panel-refresh #VisaProvider")
												.removeClass('fa-spin');
									if (type == 'InsuranceProvider')
										$(".panel-refresh #InsuranceProvider")
												.removeClass('fa-spin');
									if (type == 'MiscProvider')
										$(".panel-refresh #MiscProvider")
												.removeClass('fa-spin');
									if (type == 'Distributor')
										$(".panel-refresh #Distributor")
												.removeClass('fa-spin');
									if (type == 'Corporate')
										$(".panel-refresh #Corporate")
												.removeClass('fa-spin');
									if (type == 'Agents')
										$(".panel-refresh #Agents")
												.removeClass('fa-spin');
									if (type == 'FlightSalesReport')
										$(".panel-refresh #FlightSalesReport")
												.removeClass('fa-spin');
									if (type == 'HotelSalesReport')
										$(".panel-refresh #HotelSalesReport")
												.removeClass('fa-spin');
									if (type == 'BusSalesReport')
										$(".panel-refresh #BusSalesReport")
												.removeClass('fa-spin');
									if (type == 'CarSalesReport')
										$(".panel-refresh #CarSalesReport")
												.removeClass('fa-spin');
									if (type == 'TrainSalesReport')
										$(".panel-refresh #TrainSalesReport")
												.removeClass('fa-spin');
									if (type == 'VisaSalesReport')
										$(".panel-refresh #VisaSalesReport")
												.removeClass('fa-spin');
									if (type == 'InsuranceSalesReport')
										$(
												".panel-refresh #InsuranceSalesReport")
												.removeClass('fa-spin');
									if (type == 'MiscSalesReport')
										$(".panel-refresh #MiscSalesReport")
												.removeClass('fa-spin');

								}
							}
							$timeout(timer, 1000);

						}

						$scope.refreshFlight = function() {
							$http
									.get(baseUrl + "getFlightBookingList")
									.then(
											function(response) {
												$scope.commonTopFivePojoFlightList = response.data.commonTopFivePojoList;
											});
						};
						$scope.refreshHotel = function() {
							$http
									.get(baseUrl + "getHotelBookingList")
									.then(
											function(response) {
												$scope.commonTopFivePojoHotelList = response.data.commonTopFivePojoList;
											});
						};
						$scope.refreshBus = function() {
							$http
									.get(baseUrl + "getBusBookingList")
									.then(
											function(response) {
												$scope.commonTopFivePojoBusList = response.data.commonTopFivePojoList;
											});
						};
						$scope.refreshCar = function() {
							$http
									.get(baseUrl + "getCarBookingList")
									.then(
											function(response) {
												$scope.commonTopFivePojoCarList = response.data.commonTopFivePojoList;
											});
						};

						$scope.refreshTrain = function() {
							$http
									.get(baseUrl + "getTrainBookingList")
									.then(
											function(response) {
												$scope.commonTopFivePojoTrainList = response.data.commonTopFivePojoList;
											});
						};

						$scope.refreshVisa = function() {
							$http
									.get(baseUrl + "getVisaBookingList")
									.then(
											function(response) {
												$scope.commonTopFivePojoVisaList = response.data.commonTopFivePojoList;
											});
						};
						$scope.refreshInsurance = function() {
							$http
									.get(baseUrl + "getInsuranceBookingList")
									.then(
											function(response) {
												$scope.commonTopFivePojoInsuranceList = response.data.commonTopFivePojoList;
											});
						};
						$scope.refreshMisc = function() {
							$http
									.get(
											baseUrl
													+ "getMiscellaneousBookingList")
									.then(
											function(response) {
												$scope.commonTopFivePojoMiscList = response.data.commonTopFivePojoList;
											});
						};

						$scope.liveBookcount = function() {

						}

						$scope.lostperc = function(val) {
							var finalTodaydata = [];
							angular
									.forEach(
											val,
											function(value) {
												if (value.totalLossAmount == 0)
													value.percent = 0;
												else
													value.percent = ((value.totalLossAmount / value.totalRefundedAmount) * 100)
															.toFixed(2);
												finalTodaydata.push(value)
											});
							return finalTodaydata;
						}

						$scope.overlallBookcount = function() {
							console.log("index");
							console.log("$scope.flightCount.jsonobj",
									$scope.flightCount);
							console.log("$scope.hotelCount.jsonobj",
									$scope.hotelCount.jsonobj);
							console.log("$scope.busCount.jsonobj",
									$scope.busCount.jsonobj);
							console.log("$scope.carCount.jsonobj",
									$scope.carCount.jsonobj);
							console.log("$scope.trainCount.jsonobj",
									$scope.trainCount.jsonobj);
							console.log("$scope.insuranceCount.jsonobj",
									$scope.insuranceCount.jsonobj);
							console.log("$scope.visaCount.jsonobj",
									$scope.visaCount.jsonobj);
							console.log("$scope.miscCount.jsonobj",
									$scope.miscCount.jsonobj);
							var totalbooking = $scope.flightCount.jsonobj.countD
									+ $scope.hotelCount.jsonobj.countD
									+ $scope.busCount.jsonobj.countD
									+ $scope.carCount.jsonobj.countD
									+ $scope.trainCount.jsonobj.countD
									+ $scope.insuranceCount.jsonobj.countD
									+ $scope.visaCount.jsonobj.countD
									+ $scope.miscCount.jsonobj.countD;

							var livedata = [];
							livedata.push($scope.flightCount.jsonobj);
							livedata.push($scope.hotelCount.jsonobj);
							livedata.push($scope.busCount.jsonobj);
							livedata.push($scope.carCount.jsonobj);
							livedata.push($scope.trainCount.jsonobj);
							livedata.push($scope.insuranceCount.jsonobj);
							livedata.push($scope.visaCount.jsonobj);
							livedata.push($scope.miscCount.jsonobj);
							$scope.finallivedata = [];
							angular.forEach(livedata, function(val, idx) {
								var flightpriceandPercent = $scope.calliveBook(
										val, totalbooking);
								val.percent = flightpriceandPercent.percent;
								val.avgPrice = flightpriceandPercent.avgPrice;
								$scope.finallivedata.push(val);
							});
						}

						$scope.calliveBook = function(val, bookcount) {
							var bookingCalc = {};
							if (bookcount != 0) {
								bookingCalc.percent = ((val.countD / bookcount) * 100)
										.toFixed(2);
								if (val.bookingAmountD == 0)
									bookingCalc.avgPrice = 0;
								else
									bookingCalc.avgPrice = (val.bookingAmountD / val.countD)
											.toFixed(0);
							} else {
								bookingCalc.percent = 0;
								bookingCalc.avgPrice = 0;
							}
							return bookingCalc;
						}

						$scope.init();
					});

	app
			.controller(
					'destinationDataCtrl',
					function($scope, $http, $timeout) {

						$scope.refreshService = function(type) {
							var actionName = '';

							if (type == 'AllServicesWeeklySales')
								actionName = "getAllServicesWeeklySales";

							$http
									.get(baseUrl + actionName)
									.then(
											function(response) {
												if (type == 'AllServicesWeeklySales') {
													$scope.refreshLoader(type);
													$scope.overAllDashBoard = response.data.allServicesWeeklyData.weekDayMap;
													getOverAllDashBoardFunction(response.data.allServicesWeeklyData.weekDayMap);

												}
											});

						}

						$scope.refreshLoader = function(type) {
							var time = 0;

							if (type == 'AllServicesWeeklySales') {
								$(".panel-refresh #AllServicesWeeklySales")
										.addClass('fa-spin');
								time = 0;
							}

							$timeout(function() {
							}, 3000);

							var timer = function() {
								if (time < 3000) {
									time += 1000;
									$timeout(timer, 1000);
								}
								if (time == 3000) {
									if (type == 'AllServicesWeeklySales')
										$(
												".panel-refresh #AllServicesWeeklySales")
												.removeClass('fa-spin');

								}
							}
							$timeout(timer, 1000);
						}

						$http.get(baseUrl + "getAirDestinationBookingCount")
								.then(function(response) {
									$scope.topDestData = response.data.airData;
								});
						$('.destination-controls li').click(
								function() {
									$('.destination-controls li').removeClass(
											'active');
									$(this).addClass('active');
								});

						$scope.getAirlineDestinations = function() {

							$http
									.get(
											baseUrl
													+ "getDestinationBookingCount?serviceType=flight")
									.then(
											function(response) {
												$scope.topDestData = response.data.commonData;
											});

						};
						$scope.getHotelDestinations = function() {
							$http
									.get(
											baseUrl
													+ "getDestinationBookingCount?serviceType=hotel")
									.then(
											function(response) {
												$scope.topDestData = response.data.commonData;
											});
						};
						$scope.getBusDestinations = function() {
							$http
									.get(
											baseUrl
													+ "getDestinationBookingCount?serviceType=bus")
									.then(
											function(response) {
												$scope.topDestData = response.data.commonData;
											});
						};
						$scope.getCarDestinations = function() {
							$http
									.get(
											baseUrl
													+ "getDestinationBookingCount?serviceType=car")
									.then(
											function(response) {
												$scope.topDestData = response.data.commonData;
											});
						};
						$scope.getTrainDestinations = function() {
							$http
									.get(
											baseUrl
													+ "getDestinationBookingCount?serviceType=train")
									.then(
											function(response) {
												$scope.topDestData = response.data.commonData;
											});
						};
						$scope.getVisaDestinations = function() {
							$http
									.get(
											baseUrl
													+ "getAirDestinationBookingCount")
									.then(
											function(response) {
												$scope.topDestData = response.data.commonData;
											});
						};
						$scope.getInsuranceDestinations = function() {
							$http
									.get(
											baseUrl
													+ "getAirDestinationBookingCount")
									.then(
											function(response) {
												$scope.topDestData = response.data.commonData;
											});
						};
					});

	app
			.controller(
					'barcomDataCtl',
					function($scope, $http, $timeout) {
						var currentUrl = $(location).attr('href');
						var baseUrl = currentUrl.substr(0, currentUrl
								.lastIndexOf('/') + 1);

						var count = 0;
						var countWeek = 0;
						var countMonth = 0;
						var countYear = 0;
						var today = new Date();
						var todayDate = today.getDate() + '-'
								+ (today.getMonth() + 1) + '-'
								+ today.getFullYear();
						var currentDate = new Date();
						var curDate = currentDate.getDate() + '-'
								+ (currentDate.getMonth() + 1) + '-'
								+ currentDate.getFullYear();
						$scope.previousDate = function() {
							today = new Date(today.getFullYear(), today
									.getMonth(), today.getDate() - 1);
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=D")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												compareBarChart($scope.flightdaycomparisonData);
											});
							count++;
						}

						$scope.nextDate = function() {
							today = new Date(today.getFullYear(), today
									.getMonth(), today.getDate() + 1);
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=D")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												compareBarChart($scope.flightdaycomparisonData);
											});
							count--;
						}

						$scope.isNext = function() {
							if (curDate != todayDate)
								return true;
						};

						$scope.isPre = function() {
							if (count < 7)
								return true;
						};

						$scope.getweekCompareBar = function() {
							countWeek = 0;
							today = new Date();
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();

							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=W")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												weekcompareBarChart($scope.flightdaycomparisonData);
											});
						}
						$scope.previousWeek = function() {
							today = new Date(today.getFullYear(), today
									.getMonth(), today.getDate() - 7);
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=W")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												weekcompareBarChart($scope.flightdaycomparisonData);
											});
							countWeek++;
						}

						$scope.nextWeek = function() {
							today = new Date(today.getFullYear(), today
									.getMonth(), today.getDate() + 7);
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=W")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												weekcompareBarChart($scope.flightdaycomparisonData);
											});
							countWeek--;
						}

						$scope.isNextWeek = function() {
							if (curDate != todayDate)
								return true;
						};

						$scope.isPreWeek = function() {
							if (countWeek < 4)
								return true;
						};

						$scope.getmonthCompareBar = function() {
							countMonth = 0;
							today = new Date();
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();

							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=M")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												monthcompareBarChart($scope.flightdaycomparisonData);
											});
						}
						$scope.previousMonth = function() {
							today = new Date(today.getFullYear(), today
									.getMonth() - 1, today.getDate());
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=M")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												monthcompareBarChart($scope.flightdaycomparisonData);
											});
							countMonth++;
						}

						$scope.nextMonth = function() {
							today = new Date(today.getFullYear(), today
									.getMonth() + 1, today.getDate());
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=M")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												monthcompareBarChart($scope.flightdaycomparisonData);
											});
							countMonth--;
						}

						$scope.isNextMonth = function() {
							if (curDate != todayDate)
								return true;
						};

						$scope.isPreMonth = function() {
							if (countMonth < 11)
								return true;
						};

						$scope.getyearCompareBar = function() {
							countYear = 0;
							today = new Date();
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							//var res={"flightComaprisonData":{"comparisonMap":{"Jan":{"bookingAmount":0.00,"percentageChangeInBookingAmount":0.00,"percentageChangeInTotalCount":0.00,"totalCount":0},"Feb":{"bookingAmount":0.00,"percentageChangeInBookingAmount":0.00,"percentageChangeInTotalCount":0.00,"totalCount":0},"Mar":{"bookingAmount":0.00,"percentageChangeInBookingAmount":0.00,"percentageChangeInTotalCount":0.00,"totalCount":0},"Apr":{"bookingAmount":0.00,"percentageChangeInBookingAmount":0.00,"percentageChangeInTotalCount":0.00,"totalCount":0},"May":{"bookingAmount":0.00,"percentageChangeInBookingAmount":0.00,"percentageChangeInTotalCount":0.00,"totalCount":0},"Jun":{"bookingAmount":0.00,"percentageChangeInBookingAmount":0.00,"percentageChangeInTotalCount":0.00,"totalCount":0},"Jul":{"bookingAmount":0.00,"percentageChangeInBookingAmount":0.00,"percentageChangeInTotalCount":0.00,"totalCount":0},"Aug":{"bookingAmount":211653.00,"percentageChangeInBookingAmount":100.00,"percentageChangeInTotalCount":100.00,"totalCount":97},"Sep":{"bookingAmount":1253677.00,"percentageChangeInBookingAmount":492.33,"percentageChangeInTotalCount":32.99,"totalCount":129},"Oct":{"bookingAmount":301609.00,"percentageChangeInBookingAmount":-75.95,"percentageChangeInTotalCount":-55.82,"totalCount":57},"Nov":{"bookingAmount":23267.00,"percentageChangeInBookingAmount":-92.29,"percentageChangeInTotalCount":-77.20,"totalCount":13},"Dec":{"bookingAmount":0.00,"percentageChangeInBookingAmount":-100.00,"percentageChangeInTotalCount":-100.00,"totalCount":0}}}};
							//monthcompareBarChart(res.data.flightComaprisonData.comparisonMap); 
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=Y")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												yearcompareBarChart($scope.flightdaycomparisonData);
											});
						}
						$scope.previousYear = function() {
							today = new Date(today.getFullYear() - 1, today
									.getMonth(), today.getDate());
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=Y")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												yearcompareBarChart($scope.flightdaycomparisonData);
											});
							countYear++;
						}

						$scope.nextYear = function() {
							today = new Date(today.getFullYear() + 1, today
									.getMonth(), today.getDate());
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=Y")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												yearcompareBarChart($scope.flightdaycomparisonData);
											});
							countYear--;
						}

						$scope.isNextYear = function() {
							if (curDate != todayDate)
								return true;
						};

						$scope.isPreYear = function() {
							if (countYear < 3)
								return true;
						};

						// compareBarChart(today);
						$http
								.get(
										baseUrl
												+ "getAirBookingComparison?presentDate="
												+ todayDate + "&type=D")
								.then(
										function(response) {
											$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
											compareBarChart($scope.flightdaycomparisonData);
										});
						$scope.getdayCompareBar = function() {
							count = 0;
							today = new Date();
							todayDate = today.getDate() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getFullYear();
							$http
									.get(
											baseUrl
													+ "getAirBookingComparison?presentDate="
													+ todayDate + "&type=D")
									.then(
											function(response) {
												$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
												compareBarChart($scope.flightdaycomparisonData);
											});
						}

						$scope.refreshcomparecall = function(sType) {
							var salesType = $('ul#dayCompareSalesbar').find(
									'li.active a')[0].hash;
							$scope.refreshLoader(sType);
							$scope.refreshcall(salesType);
						}

						$scope.refreshcall = function(sType) {
							if (sType == '#daySale') {
								// compareBarChart(today);
								$http
										.get(
												baseUrl
														+ "getAirBookingComparison?presentDate="
														+ todayDate + "&type=D")
										.then(
												function(response) {
													$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
													compareBarChart($scope.flightdaycomparisonData);
												});
							}
							if (sType == '#weekSale') {
								// compareBarChart(today);
								$http
										.get(
												baseUrl
														+ "getAirBookingComparison?presentDate="
														+ todayDate + "&type=W")
										.then(
												function(response) {
													$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
													weekcompareBarChart($scope.flightdaycomparisonData);
												});
							}
							if (sType == '#monthSale') {
								// compareBarChart(today);
								$http
										.get(
												baseUrl
														+ "getAirBookingComparison?presentDate="
														+ todayDate + "&type=M")
										.then(
												function(response) {
													$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
													monthcompareBarChart($scope.flightdaycomparisonData);
												});
							}
							if (sType == '#yearSalee') {
								// compareBarChart(today);
								$http
										.get(
												baseUrl
														+ "getAirBookingComparison?presentDate="
														+ todayDate + "&type=Y")
										.then(
												function(response) {
													$scope.flightdaycomparisonData = response.data.flightComaprisonData.comparisonMap;
													yearcompareBarChart($scope.flightdaycomparisonData);
												});
							}
						}

						$scope.refreshLoader = function(type) {
							var time = 0;
							if (type == 'AllCompareSalesbar') {
								$(".panel-refresh #AllCompareSalesbar")
										.addClass('fa-spin');
								time = 0;
							}
							$timeout(function() {
							}, 3000);
							var timer = function() {
								if (time < 3000) {
									time += 1000;
									$timeout(timer, 1000);
								}
								if (time == 3000) {
									if (type == 'dayCompareSalesbar')
										$(".panel-refresh #AllCompareSalesbar")
												.removeClass('fa-spin');
								}
							}
							$timeout(timer, 1000);
						}
					});

	app
			.controller(
					'barDataCtl',
					function($scope, $http, $timeout) {

						var currentUrl = $(location).attr('href');
						var baseUrl = currentUrl.substr(0, currentUrl
								.lastIndexOf('/') + 1);

						$scope.refreshService = function(type) {
							var salesType = $('ul#weeklySalesBar').find(
									'li.active a')[0].hash;
							if (type == 'AllWeeklySalesbar') {

								$scope.refreshLoader(type);
								$scope.refreshcall(salesType);
							}

						}

						$scope.refreshcall = function(sType) {
							if (sType == '#flights') {

								$http
										.get(
												baseUrl
														+ "getWeeklySalesTop5Airlines")
										.then(
												function(response) {
													console.log("calling",
															response)
													$scope.flightWeeklyBarData = response.data.flightWeeklyData.weekDayMap;
													prepareBarChart($scope.flightWeeklyBarData);
												});
							}
							;
							if (sType == '#hotels') {
								$http
										.get(
												baseUrl
														+ "getWeeklySalesTop5Hotels")
										.then(
												function(response) {
													$scope.hotelWeeklyBarData = response.data.hotelWeeklyData.weekDayMap;
													prepareHotelBarChart($scope.hotelWeeklyBarData);
												});
							}
							;
							if (sType == '#cars') {
								$http
										.get(
												baseUrl
														+ "getWeeklySalesTop5CarLocations")
										.then(
												function(response) {
													$scope.carWeeklyBarData = response.data.carWeeklyData.weekDayMap;
													prepareCarBarChart(response.data.carWeeklyData.weekDayMap);
												});
							}
							;
							if (sType == '#buses') {
								$http
										.get(
												baseUrl
														+ "getWeeklySalesTop5BusLocations")
										.then(
												function(response) {
													$scope.busWeeklyBarData = response.data.busWeeklyData.weekDayMap;
													prepareBusBarChart(response.data.busWeeklyData.weekDayMap);
												});
							}
							;
							if (sType == '#trains') {
								$http
										.get(
												baseUrl
														+ "getWeeklySalesTop5TrainLocations")
										.then(
												function(response) {
													$scope.trainWeeklyBarData = response.data.trainWeeklyData.weekDayMap;
													prepareTrainBarChart(response.data.trainWeeklyData.weekDayMap);
												});
							}
							;
						}

						$scope.refreshLoader = function(type) {
							var time = 0;
							if (type == 'AllWeeklySalesbar') {
								$(".panel-refresh #AllWeeklySalesbar")
										.addClass('fa-spin');
								time = 0;
							}
							$timeout(function() {
							}, 3000);
							var timer = function() {
								if (time < 3000) {
									time += 1000;
									$timeout(timer, 1000);
								}
								if (time == 3000) {
									if (type == 'AllWeeklySalesbar')
										$(".panel-refresh #AllWeeklySalesbar")
												.removeClass('fa-spin');
								}
							}
							$timeout(timer, 1000);
						}

						$http
								.get(baseUrl + "getWeeklySalesTop5Airlines")
								.then(
										function(response) {
											$scope.flightWeeklyBarData = response.data.flightWeeklyData.weekDayMap;
											prepareBarChart($scope.flightWeeklyBarData);

										});
						$scope.getFlightBar = function() {
							$http
									.get(baseUrl + "getWeeklySalesTop5Airlines")
									.then(
											function(response) {
												$scope.flightWeeklyBarData = response.data.flightWeeklyData.weekDayMap;
												prepareBarChart($scope.flightWeeklyBarData);
											});
						};
						$scope.getHotelBar = function() {
							$http
									.get(baseUrl + "getWeeklySalesTop5Hotels")
									.then(
											function(response) {
												$scope.hotelWeeklyBarData = response.data.hotelWeeklyData.weekDayMap;
												prepareHotelBarChart($scope.hotelWeeklyBarData);
											});
						};
						$scope.getCarBar = function() {
							$http
									.get(
											baseUrl
													+ "getWeeklySalesTop5CarLocations")
									.then(
											function(response) {
												$scope.carWeeklyBarData = response.data.carWeeklyData.weekDayMap;
												prepareCarBarChart(response.data.carWeeklyData.weekDayMap);
											});
						};
						$scope.getBusBar = function() {
							$http
									.get(
											baseUrl
													+ "getWeeklySalesTop5BusLocations")
									.then(
											function(response) {
												$scope.busWeeklyBarData = response.data.busWeeklyData.weekDayMap;
												prepareBusBarChart(response.data.busWeeklyData.weekDayMap);
											});
						};
						$scope.getTrainBar = function() {
							$http
									.get(
											baseUrl
													+ "getWeeklySalesTop5TrainLocations")
									.then(
											function(response) {
												$scope.trainWeeklyBarData = response.data.trainWeeklyData.weekDayMap;
												prepareTrainBarChart(response.data.trainWeeklyData.weekDayMap);
											});
						};
						$scope.getVisaBar = function() {
							$http
									.get(
											baseUrl
													+ "getWeeklySalesTop5CarLocations")
									.then(
											function(response) {
												$scope.hotelWeeklyBarData = response.data.carWeeklyData.weekDayMap;
												prepareBarChart(response.data.carWeeklyData.weekDayMap);
											});
						};
						$scope.getInsuranceBar = function() {
							$http
									.get(
											baseUrl
													+ "getWeeklySalesTop5CarLocations")
									.then(
											function(response) {
												$scope.hotelWeeklyBarData = response.data.carWeeklyData.weekDayMap;
												prepareBarChart(response.data.carWeeklyData.weekDayMap);

												app
														.controller(
																'destinationDataCtrl',
																function(
																		$scope,
																		$http) {
																	$http
																			.get(
																					baseUrl
																							+ "getAirDestinationBookingCount")
																			.then(
																					function(
																							response) {
																						$scope.topDestData = response.data.airData;
																					});
																	$scope.getAirlineDestinations = function() {
																		$http
																				.get(
																						baseUrl
																								+ "getDestinationBookingCount?serviceType=flight")
																				.then(
																						function(
																								response) {
																							$scope.topDestData = response.data.commonData;
																						});
																	};
																	$scope.getHotelDestinations = function() {
																		$http
																				.get(
																						baseUrl
																								+ "getDestinationBookingCount?serviceType=hotel")
																				.then(
																						function(
																								response) {
																							$scope.topDestData = response.data.commonData;
																						});
																	};
																	$scope.getBusDestinations = function() {
																		$http
																				.get(
																						baseUrl
																								+ "getDestinationBookingCount?serviceType=bus")
																				.then(
																						function(
																								response) {
																							$scope.topDestData = response.data.commonData;
																						});
																	};
																	$scope.getCarDestinations = function() {
																		$http
																				.get(
																						baseUrl
																								+ "getDestinationBookingCount?serviceType=car")
																				.then(
																						function(
																								response) {
																							$scope.topDestData = response.data.commonData;
																						});
																	};
																	$scope.getTrainDestinations = function() {
																		$http
																				.get(
																						baseUrl
																								+ "getDestinationBookingCount?serviceType=train")
																				.then(
																						function(
																								response) {
																							$scope.topDestData = response.data.commonData;
																						});
																	};
																	$scope.getVisaDestinations = function() {
																		$http
																				.get(
																						baseUrl
																								+ "getAirDestinationBookingCount")
																				.then(
																						function(
																								response) {
																							$scope.topDestData = response.data.commonData;
																						});
																	};
																	$scope.getInsuranceDestinations = function() {
																		$http
																				.get(
																						baseUrl
																								+ "getAirDestinationBookingCount")
																				.then(
																						function(
																								response) {
																							$scope.topDestData = response.data.commonData;
																						});
																	};
																});
											});
						};
						$scope.getMiscBar = function() {
							$http
									.get(
											baseUrl
													+ "getWeeklySalesTop5CarLocations")
									.then(
											function(response) {
												$scope.hotelWeeklyBarData = response.data.carWeeklyData.weekDayMap;
												prepareBarChart(response.data.carWeeklyData.weekDayMap);
											});
						};

						$http
								.get(baseUrl + "getAllServicesWeeklySales")
								.then(
										function(response) {
											//console.log("dumresponse",response);
											//console.log("dumData",dumData);
											$scope.overAllDashBoard = response.data.allServicesWeeklyData.weekDayMap;
											getOverAllDashBoardFunction(response.data.allServicesWeeklyData.weekDayMap);
										});

						$scope.clearData = function() {
							$scope.flightWeeklyBarData = [];
							$scope.hotelWeeklyBarData = [];
						};

					});

	app.controller('OrderChartCtrl', function($scope, $http, $timeout) {
		var currentUrl = $(location).attr('href');
		var baseUrl = currentUrl.substr(0, currentUrl.lastIndexOf('/') + 1);

		$scope.refreshLoader = function(type) {
			var time = 0;

			if (type == 'todayChart') {
				$(".panel-refresh #todayChart").addClass('fa-spin');
				time = 0;
			}

			$timeout(function() {
			}, 3000);

			var timer = function() {
				if (time < 3000) {
					time += 1000;
					$timeout(timer, 1000);
				}
				if (time == 3000) {
					if (type == 'todayChart')
						$(".panel-refresh #todayChart").removeClass('fa-spin');

				}
			}
			$timeout(timer, 1000);
		}

		$http.get(baseUrl + "allServicesTodayBookingCount").then(
				function(response) {
					$scope.bookingStatusCount = response.data;
					console.log("response.data.servicesData"
							+ response.data.servicesData)
					prepareDonutChart(response);
				});

	});

	function getOverAllDashBoardFunction(response) {

		//console.log("totalcountoverall",response);

		var Monday = [];
		var Tuesday = [];
		var Wednesday = [];
		var Thursday = [];
		var Friday = [];
		var Saturday = [];
		var Sunday = [];
		var RedfinedData = {};

		var air = {
			name : "Air",
			totalCount : 0,
			price : 0.00
		};
		var Air = {};
		var hot = {
			name : "Hotel",
			totalCount : 0,
			price : 0.00
		};
		var Hot = {};
		var bus = {
			name : "Bus",
			totalCount : 0,
			price : 0.00
		};
		var Bus = {};
		var car = {
			name : "Car",
			totalCount : 0,
			price : 0.00
		};
		var Car = {};
		var train = {
			name : "Train",
			totalCount : 0,
			price : 0.00
		};
		var Train = {};
		var vis = {
			name : "Visa",
			totalCount : 0,
			price : 0.00
		};
		var Vis = {};
		var ins = {
			name : "Insurance",
			totalCount : 0,
			price : 0.00
		};
		var Ins = {};
		var misc = {
			name : "Miscellaneous",
			totalCount : 0,
			price : 0.00
		};
		var Misc = {};

		if (response.MON.length != 0) {
			var monAir = {};
			var monHot = {};
			var monBus = {};
			var monCar = {};
			var monTrain = {};
			var monVis = {};
			var monIns = {};
			var monMisc = {};
			angular.forEach(response.MON, function(val) {
				if (Object.getOwnPropertyNames(monAir).length == 0
						|| monAir.totalCount == 0) {
					if (val.name == "Air")
						monAir = val;
					else
						monAir = air;
				}
				if (Object.getOwnPropertyNames(monHot).length == 0
						|| monHot.totalCount == 0) {
					if (val.name == "Hotel")
						monHot = val;
					else
						monHot = hot;
				}
				if (Object.getOwnPropertyNames(monBus).length == 0
						|| monBus.totalCount == 0) {
					if (val.name == "Bus")
						monBus = val;
					else
						monBus = bus;
				}
				if (Object.getOwnPropertyNames(monCar).length == 0
						|| monCar.totalCount == 0) {
					if (val.name == "Car")
						monCar = val;
					else
						monCar = car;
				}
				if (Object.getOwnPropertyNames(monTrain).length == 0
						|| monTrain.totalCount == 0) {
					if (val.name == "Train")
						monTrain = val;
					else
						monTrain = train;
				}
				if (Object.getOwnPropertyNames(monVis).length == 0
						|| monVis.totalCount == 0) {
					if (val.name == "Visa")
						monVis = val;
					else
						monVis = vis;
				}
				if (Object.getOwnPropertyNames(monIns).length == 0
						|| monIns.totalCount == 0) {
					if (val.name == "Insurance")
						monIns = val;
					else
						monIns = ins;
				}
				if (Object.getOwnPropertyNames(monMisc).length == 0) {
					if (val.name == "Miscellaneous")
						monMisc = val;
					else
						monMisc = misc;
				}
			});
			Monday.push(monAir);
			Monday.push(monHot);
			Monday.push(monBus);
			Monday.push(monCar);
			Monday.push(monTrain);
			Monday.push(monVis);
			Monday.push(monIns);
			Monday.push(monMisc);
		} else {
			Monday.push(air);
			Monday.push(hot);
			Monday.push(bus);
			Monday.push(car);
			Monday.push(train);
			Monday.push(vis);
			Monday.push(ins);
			Monday.push(misc);
		}
		RedfinedData["MON"] = Monday;

		if (response.TUE.length != 0) {
			var tuAir = {};
			var tuHot = {};
			var tuBus = {};
			var tuCar = {};
			var tuTrain = {};
			var tuVis = {};
			var tuIns = {};
			var tuMisc = {};
			angular.forEach(response.TUE, function(val, key) {
				if (Object.getOwnPropertyNames(tuAir).length == 0
						|| tuAir.totalCount == 0) {
					if (val.name == "Air")
						tuAir = val;
					else
						tuAir = air;
				}
				if (Object.getOwnPropertyNames(tuHot).length == 0
						|| tuHot.totalCount == 0) {
					if (val.name == "Hotel")
						tuHot = val;
					else
						tuHot = hot;
				}
				if (Object.getOwnPropertyNames(tuBus).length == 0
						|| tuBus.totalCount == 0) {
					if (val.name == 'Bus')
						tuBus = val;
					else
						tuBus = bus;
				}
				if (Object.getOwnPropertyNames(tuCar).length == 0
						|| tuCar.totalCount == 0) {
					if (val.name == 'Car')
						tuCar = val;
					else
						tuCar = car;
				}
				if (Object.getOwnPropertyNames(tuTrain).length == 0
						|| tuTrain.totalCount == 0) {
					if (val.name == 'Train')
						tuTrain = val;
					else
						tuTrain = train;
				}
				if (Object.getOwnPropertyNames(tuVis).length == 0
						|| tuVis.totalCount == 0) {
					if (val.name == 'Visa')
						tuVis = val;
					else
						tuVis = vis;
				}
				if (Object.getOwnPropertyNames(tuIns).length == 0
						|| tuIns.totalCount == 0) {
					if (val.name == 'Insurance')
						tuIns = val;
					else
						tuIns = ins;
				}
				if (Object.getOwnPropertyNames(tuMisc).length == 0
						|| tuMisc.totalCount == 0) {
					if (val.name == 'Miscellaneous')
						tuMisc = val;
					else
						tuMisc = misc;
				}
			});
			Tuesday.push(tuAir);
			Tuesday.push(tuHot);
			Tuesday.push(tuBus);
			Tuesday.push(tuCar);
			Tuesday.push(tuTrain);
			Tuesday.push(tuVis);
			Tuesday.push(tuIns);
			Tuesday.push(tuMisc);
		} else {
			Tuesday.push(air);
			Tuesday.push(hot);
			Tuesday.push(bus);
			Tuesday.push(car);
			Tuesday.push(train);
			Tuesday.push(vis);
			Tuesday.push(ins);
			Tuesday.push(misc);
		}

		RedfinedData["TUE"] = Tuesday;

		if (response.WED.length != 0) {
			var weAir = {};
			var weHot = {};
			var weBus = {};
			var weCar = {};
			var weTrain = {};
			var weVis = {};
			var weIns = {};
			var weMisc = {};
			angular.forEach(response.WED, function(val, key) {

				if (Object.getOwnPropertyNames(weAir).length == 0
						|| weAir.totalCount == 0) {
					if (val.name == "Air")
						weAir = val;
					else
						weAir = air;
				}
				if (Object.getOwnPropertyNames(weHot).length == 0
						|| weHot.totalCount == 0) {
					if (val.name == "Hotel")
						weHot = val;
					else
						weHot = hot;
				}
				if (Object.getOwnPropertyNames(weBus).length == 0
						|| weBus.totalCount == 0) {
					if (val.name == 'Bus')
						weBus = val;
					else
						weBus = bus;
				}
				if (Object.getOwnPropertyNames(weCar).length == 0
						|| weCar.totalCount == 0) {
					if (val.name == 'Car')
						weCar = val;
					else
						weCar = car;
				}
				if (Object.getOwnPropertyNames(weTrain).length == 0
						|| weTrain.totalCount == 0) {
					if (val.name == 'Train')
						weTrain = val;
					else
						weTrain = train;
				}
				if (Object.getOwnPropertyNames(weVis).length == 0
						|| weVis.totalCount == 0) {
					if (val.name == 'Visa')
						weVis = val;
					else
						weVis = vis;
				}
				if (Object.getOwnPropertyNames(weIns).length == 0
						|| weIns.totalCount == 0) {
					if (val.name == 'Insurance')
						weIns = val;
					else
						weIns = ins;
				}
				if (Object.getOwnPropertyNames(weMisc).length == 0
						|| weMisc.totalCount == 0) {
					if (val.name == 'Miscellaneous')
						weMisc = val;
					else
						weMisc = misc;
				}
			});
			Wednesday.push(weAir);
			Wednesday.push(weHot);
			Wednesday.push(weBus);
			Wednesday.push(weCar);
			Wednesday.push(weTrain);
			Wednesday.push(weVis);
			Wednesday.push(weIns);
			Wednesday.push(weMisc);
		} else {
			Wednesday.push(air);
			Wednesday.push(hot);
			Wednesday.push(bus);
			Wednesday.push(car);
			Wednesday.push(train);
			Wednesday.push(vis);
			Wednesday.push(ins);
			Wednesday.push(misc);
		}
		RedfinedData["WED"] = Wednesday;

		if (response.THU.length != 0) {
			var thAir = {};
			var thHot = {};
			var thBus = {};
			var thCar = {};
			var thTrain = {};
			var thVis = {};
			var thIns = {};
			var thMisc = {};
			angular.forEach(response.THU, function(val, key) {
				if (Object.getOwnPropertyNames(thAir).length == 0
						|| thAir.totalCount == 0) {
					if (val.name == "Air")
						thAir = val;
					else
						thAir = air;
				}
				if (Object.getOwnPropertyNames(thHot).length == 0
						|| thHot.totalCount == 0) {
					if (val.name == "Hotel")
						thHot = val;
					else
						thHot = hot;
				}
				if (Object.getOwnPropertyNames(thBus).length == 0
						|| thBus.totalCount == 0) {
					if (val.name == 'Bus')
						thBus = val;
					else
						thBus = bus;
				}
				if (Object.getOwnPropertyNames(thCar).length == 0
						|| thCar.totalCount == 0) {
					if (val.name == 'Car')
						thCar = val;
					else
						thCar = car;
				}
				if (Object.getOwnPropertyNames(thTrain).length == 0
						|| thTrain.totalCount == 0) {
					if (val.name == 'Train')
						thTrain = val;
					else
						thTrain = train;
				}
				if (Object.getOwnPropertyNames(thVis).length == 0
						|| thVis.totalCount == 0) {
					if (val.name == 'Visa')
						thVis = val;
					else
						thVis = vis;
				}
				if (Object.getOwnPropertyNames(thIns).length == 0
						|| thIns.totalCount == 0) {
					if (val.name == 'Insurance')
						thIns = val;
					else
						thIns = ins;
				}
				if (Object.getOwnPropertyNames(thMisc).length == 0
						|| thMisc.totalCount == 0) {
					if (val.name == 'Miscellaneous')
						thMisc = val;
					else
						thMisc = misc;
				}
			});
			Thursday.push(thAir);
			Thursday.push(thHot);
			Thursday.push(thBus);
			Thursday.push(thCar);
			Thursday.push(thTrain);
			Thursday.push(thVis);
			Thursday.push(thIns);
			Thursday.push(thMisc);

		} else {
			Thursday.push(air);
			Thursday.push(hot);
			Thursday.push(bus);
			Thursday.push(car);
			Thursday.push(train);
			Thursday.push(vis);
			Thursday.push(ins);
			Thursday.push(misc);
		}
		RedfinedData["THU"] = Thursday;

		if (response.FRI.length != 0) {
			var frAir = {};
			var frHot = {};
			var frBus = {};
			var frCar = {};
			var frTrain = {};
			var frVis = {};
			var frIns = {};
			var frMisc = {};
			angular.forEach(response.FRI, function(val, key) {
				if (Object.getOwnPropertyNames(frAir).length == 0
						|| frAir.totalCount == 0) {
					if (val.name == "Air")
						frAir = val;
					else
						frAir = air;
				}
				if (Object.getOwnPropertyNames(frHot).length == 0
						|| frHot.totalCount == 0) {
					if (val.name == "Hotel")
						frHot = val;
					else
						frHot = hot;
				}
				if (Object.getOwnPropertyNames(frBus).length == 0
						|| frBus.totalCount == 0) {
					if (val.name == 'Bus')
						frBus = val;
					else
						frBus = bus;
				}
				if (Object.getOwnPropertyNames(frCar).length == 0
						|| frCar.totalCount == 0) {
					if (val.name == 'Car')
						frCar = val;
					else
						frCar = car;
				}
				if (Object.getOwnPropertyNames(frTrain).length == 0
						|| frTrain.totalCount == 0) {
					if (val.name == 'Train')
						frTrain = val;
					else
						frTrain = train;
				}
				if (Object.getOwnPropertyNames(frVis).length == 0
						|| frVis.totalCount == 0) {
					if (val.name == 'Visa')
						frVis = val;
					else
						frVis = vis;
				}
				if (Object.getOwnPropertyNames(frIns).length == 0
						|| frIns.totalCount == 0) {
					if (val.name == 'Insurance')
						frIns = val;
					else
						frIns = ins;
				}
				if (Object.getOwnPropertyNames(frMisc).length == 0
						|| frMisc.totalCount == 0) {
					if (val.name == 'Miscellaneous')
						frMisc = val;
					else
						frMisc = misc;
				}
			});
			Friday.push(frAir);
			Friday.push(frHot);
			Friday.push(frBus);
			Friday.push(frCar);
			Friday.push(frTrain);
			Friday.push(frVis);
			Friday.push(frIns);
			Friday.push(frMisc);
		} else {
			Friday.push(air);
			Friday.push(hot);
			Friday.push(bus);
			Friday.push(car);
			Friday.push(train);
			Friday.push(vis);
			Friday.push(ins);
			Friday.push(misc);
		}
		RedfinedData["FRI"] = Friday;

		if (response.SAT.length != 0) {
			var satAir = {};
			var satHot = {};
			var satBus = {};
			var satCar = {};
			var satTrain = {};
			var satVis = {};
			var satIns = {};
			var satMisc = {};
			angular.forEach(response.SAT, function(val, key) {
				if (Object.getOwnPropertyNames(satAir).length == 0
						|| satAir.totalCount == 0) {
					if (val.name == "Air")
						satAir = val;
					else
						satAir = air;
				}
				if (Object.getOwnPropertyNames(satHot).length == 0
						|| satHot.totalCount == 0) {
					if (val.name == "Hotel")
						satHot = val;
					else
						satHot = hot;
				}
				if (Object.getOwnPropertyNames(satBus).length == 0
						|| satBus.totalCount == 0) {
					if (val.name == 'Bus')
						satBus = val;
					else
						satBus = bus;
				}
				if (Object.getOwnPropertyNames(satCar).length == 0
						|| satCar.totalCount == 0) {
					if (val.name == 'Car')
						satCar = val;
					else
						satCar = car;
				}
				if (Object.getOwnPropertyNames(satTrain).length == 0
						|| satTrain.totalCount == 0) {
					if (val.name == 'Train')
						satTrain = val;
					else
						satTrain = train;
				}
				if (Object.getOwnPropertyNames(satVis).length == 0
						|| satVis.totalCount == 0) {
					if (val.name == 'Visa')
						satVis = val;
					else
						satVis = vis;
				}
				if (Object.getOwnPropertyNames(satIns).length == 0
						|| satIns.totalCount == 0) {
					if (val.name == 'Insurance')
						satIns = val;
					else
						satIns = ins;
				}
				if (Object.getOwnPropertyNames(satMisc).length == 0
						|| satMisc.totalCount == 0) {
					if (val.name == 'Miscellaneous')
						satMisc = val;
					else
						satMisc = misc;
				}
			});
			Saturday.push(satAir);
			Saturday.push(satHot);
			Saturday.push(satBus);
			Saturday.push(satCar);
			Saturday.push(satTrain);
			Saturday.push(satVis);
			Saturday.push(satIns);
			Saturday.push(satMisc);
		} else {
			Saturday.push(air);
			Saturday.push(hot);
			Saturday.push(bus);
			Saturday.push(car);
			Saturday.push(train);
			Saturday.push(vis);
			Saturday.push(ins);
			Saturday.push(misc);
		}
		RedfinedData["SAT"] = Saturday;
		if (response.SUN.length != 0) {
			var sunAir = {};
			var sunHot = {};
			var sunBus = {};
			var sunCar = {};
			var sunTrain = {};
			var sunVis = {};
			var sunIns = {};
			var sunMisc = {};
			angular.forEach(response.SUN, function(val, key) {
				if (Object.getOwnPropertyNames(sunAir).length == 0
						|| sunAir.totalCount == 0) {
					if (val.name == "Air")
						sunAir = val;
					else
						sunAir = air;
				}
				if (Object.getOwnPropertyNames(sunHot).length == 0
						|| sunHot.totalCount == 0) {
					if (val.name == "Hotel")
						sunHot = val;
					else
						sunHot = hot;
				}
				if (Object.getOwnPropertyNames(sunBus).length == 0
						|| sunBus.totalCount == 0) {
					if (val.name == 'Bus')
						sunBus = val;
					else
						sunBus = bus;
				}
				if (Object.getOwnPropertyNames(sunCar).length == 0
						|| sunCar.totalCount == 0) {
					if (val.name == 'Car')
						sunCar = val;
					else
						sunCar = car;
				}
				if (Object.getOwnPropertyNames(sunTrain).length == 0
						|| sunTrain.totalCount == 0) {
					if (val.name == 'Train')
						sunTrain = val;
					else
						sunTrain = train;
				}
				if (Object.getOwnPropertyNames(sunVis).length == 0
						|| sunVis.totalCount == 0) {
					if (val.name == 'Visa')
						sunVis = val;
					else
						sunVis = vis;
				}
				if (Object.getOwnPropertyNames(sunIns).length == 0
						|| sunIns.totalCount == 0) {
					if (val.name == 'Insurance')
						sunIns = val;
					else
						sunIns = ins;
				}
				if (Object.getOwnPropertyNames(sunMisc).length == 0
						|| sunMisc.totalCount == 0) {
					if (val.name == 'Miscellaneous')
						sunMisc = val;
					else
						sunMisc = misc;
				}
			});
			Sunday.push(sunAir);
			Sunday.push(sunHot);
			Sunday.push(sunBus);
			Sunday.push(sunCar);
			Sunday.push(sunTrain);
			Sunday.push(sunVis);
			Sunday.push(sunIns);
			Sunday.push(sunMisc);
		} else {
			Sunday.push(air);
			Sunday.push(hot);
			Sunday.push(bus);
			Sunday.push(car);
			Sunday.push(train);
			Sunday.push(vis);
			Sunday.push(ins);
			Sunday.push(misc);
		}
		RedfinedData["SUN"] = Sunday;
		var data1 = [
				(!RedfinedData.MON || RedfinedData.MON[0] != null) ? RedfinedData.MON[0].totalCount
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[0] != null) ? RedfinedData.TUE[0].totalCount
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[0] != null) ? RedfinedData.WED[0].totalCount
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[0] != null) ? RedfinedData.THU[0].totalCount
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[0] != null) ? RedfinedData.FRI[0].totalCount
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[0] != null) ? RedfinedData.SAT[0].totalCount
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[0] != null) ? RedfinedData.SUN[0].totalCount
						: 0 ];

		/* var data1=['2-3200rs','0-0.0rs','2-5200rs','3-5700rs','0-0.0rs','10-7200rs','1-1200rs']; */
		var data2 = [
				(!RedfinedData.MON || RedfinedData.MON[1] != null) ? RedfinedData.MON[1].totalCount
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[1] != null) ? RedfinedData.TUE[1].totalCount
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[1] != null) ? RedfinedData.WED[1].totalCount
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[1] != null) ? RedfinedData.THU[1].totalCount
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[1] != null) ? RedfinedData.FRI[1].totalCount
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[1] != null) ? RedfinedData.SAT[1].totalCount
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[1] != null) ? RedfinedData.SUN[1].totalCount
						: 0 ];
		var data3 = [
				(!RedfinedData.MON || RedfinedData.MON[2] != null) ? RedfinedData.MON[2].totalCount
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[2] != null) ? RedfinedData.TUE[2].totalCount
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[2] != null) ? RedfinedData.WED[2].totalCount
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[2] != null) ? RedfinedData.THU[2].totalCount
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[2] != null) ? RedfinedData.FRI[2].totalCount
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[2] != null) ? RedfinedData.SAT[2].totalCount
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[2] != null) ? RedfinedData.SUN[2].totalCount
						: 0 ];
		var data4 = [
				(!RedfinedData.MON || RedfinedData.MON[3] != null) ? RedfinedData.MON[3].totalCount
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[3] != null) ? RedfinedData.TUE[3].totalCount
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[3] != null) ? RedfinedData.WED[3].totalCount
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[3] != null) ? RedfinedData.THU[3].totalCount
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[3] != null) ? RedfinedData.FRI[3].totalCount
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[3] != null) ? RedfinedData.SAT[3].totalCount
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[3] != null) ? RedfinedData.SUN[3].totalCount
						: 0 ];
		var data5 = [
				(!RedfinedData.MON || RedfinedData.MON[4] != null) ? RedfinedData.MON[4].totalCount
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[4] != null) ? RedfinedData.TUE[4].totalCount
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[4] != null) ? RedfinedData.WED[4].totalCount
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[4] != null) ? RedfinedData.THU[4].totalCount
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[4] != null) ? RedfinedData.FRI[4].totalCount
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[4] != null) ? RedfinedData.SAT[4].totalCount
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[4] != null) ? RedfinedData.SUN[4].totalCount
						: 0 ];
		var data6 = [
				(!RedfinedData.MON || RedfinedData.MON[5] != null) ? RedfinedData.MON[5].totalCount
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[5] != null) ? RedfinedData.TUE[5].totalCount
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[5] != null) ? RedfinedData.WED[5].totalCount
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[5] != null) ? RedfinedData.THU[5].totalCount
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[5] != null) ? RedfinedData.FRI[5].totalCount
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[5] != null) ? RedfinedData.SAT[5].totalCount
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[5] != null) ? RedfinedData.SUN[5].totalCount
						: 0 ];
		var data7 = [
				(!RedfinedData.MON || RedfinedData.MON[6] != null) ? RedfinedData.MON[6].totalCount
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[6] != null) ? RedfinedData.TUE[6].totalCount
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[6] != null) ? RedfinedData.WED[6].totalCount
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[6] != null) ? RedfinedData.THU[6].totalCount
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[6] != null) ? RedfinedData.FRI[6].totalCount
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[6] != null) ? RedfinedData.SAT[6].totalCount
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[6] != null) ? RedfinedData.SUN[6].totalCount
						: 0 ];
		var data8 = [
				(!RedfinedData.MON || RedfinedData.MON[7] != null) ? RedfinedData.MON[7].totalCount
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[7] != null) ? RedfinedData.TUE[7].totalCount
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[7] != null) ? RedfinedData.WED[7].totalCount
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[7] != null) ? RedfinedData.THU[7].totalCount
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[7] != null) ? RedfinedData.FRI[7].totalCount
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[7] != null) ? RedfinedData.SAT[7].totalCount
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[7] != null) ? RedfinedData.SUN[7].totalCount
						: 0 ];
		var dataprice1 = [
				(!RedfinedData.MON || RedfinedData.MON[0] != null) ? RedfinedData.MON[0].price
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[0] != null) ? RedfinedData.TUE[0].price
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[0] != null) ? RedfinedData.WED[0].price
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[0] != null) ? RedfinedData.THU[0].price
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[0] != null) ? RedfinedData.FRI[0].price
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[0] != null) ? RedfinedData.SAT[0].price
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[0] != null) ? RedfinedData.SUN[0].price
						: 0 ];
		var dataprice2 = [
				(!RedfinedData.MON || RedfinedData.MON[1] != null) ? RedfinedData.MON[1].price
						: 0 + '"',
				(!RedfinedData.TUE || RedfinedData.TUE[1] != null) ? RedfinedData.TUE[1].price
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[1] != null) ? RedfinedData.WED[1].price
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[1] != null) ? RedfinedData.THU[1].price
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[1] != null) ? RedfinedData.FRI[1].price
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[1] != null) ? RedfinedData.SAT[1].price
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[1] != null) ? RedfinedData.SUN[1].price
						: 0 ];
		var dataprice3 = [
				(!RedfinedData.MON || RedfinedData.MON[2] != null) ? RedfinedData.MON[2].price
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[2] != null) ? RedfinedData.TUE[2].price
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[2] != null) ? RedfinedData.WED[2].price
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[2] != null) ? RedfinedData.THU[2].price
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[2] != null) ? RedfinedData.FRI[2].price
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[2] != null) ? RedfinedData.SAT[2].price
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[2] != null) ? RedfinedData.SUN[2].price
						: 0 ];
		var dataprice4 = [
				(!RedfinedData.MON || RedfinedData.MON[3] != null) ? RedfinedData.MON[3].price
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[3] != null) ? RedfinedData.TUE[3].price
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[3] != null) ? RedfinedData.WED[3].price
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[3] != null) ? RedfinedData.THU[3].price
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[3] != null) ? RedfinedData.FRI[3].price
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[3] != null) ? RedfinedData.SAT[3].price
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[3] != null) ? RedfinedData.SUN[3].price
						: 0 ];
		var dataprice5 = [
				(!RedfinedData.MON || RedfinedData.MON[4] != null) ? RedfinedData.MON[4].price
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[4] != null) ? RedfinedData.TUE[4].price
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[4] != null) ? RedfinedData.WED[4].price
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[4] != null) ? RedfinedData.THU[4].price
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[4] != null) ? RedfinedData.FRI[4].price
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[4] != null) ? RedfinedData.SAT[4].price
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[4] != null) ? RedfinedData.SUN[4].price
						: 0 ];
		var dataprice6 = [
				(!RedfinedData.MON || RedfinedData.MON[5] != null) ? RedfinedData.MON[5].price
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[5] != null) ? RedfinedData.TUE[5].price
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[5] != null) ? RedfinedData.WED[5].price
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[5] != null) ? RedfinedData.THU[5].price
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[5] != null) ? RedfinedData.FRI[5].price
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[5] != null) ? RedfinedData.SAT[5].price
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[5] != null) ? RedfinedData.SUN[5].price
						: 0 ];
		var dataprice7 = [
				(!RedfinedData.MON || RedfinedData.MON[6] != null) ? RedfinedData.MON[6].price
						: 0,
				(!RedfinedData.TUE || RedfinedData.TUE[6] != null) ? RedfinedData.TUE[6].price
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[6] != null) ? RedfinedData.WED[6].price
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[6] != null) ? RedfinedData.THU[6].price
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[6] != null) ? RedfinedData.FRI[6].price
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[6] != null) ? RedfinedData.SAT[6].price
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[6] != null) ? RedfinedData.SUN[6].price
						: 0 ];
		var dataprice8 = [
				(!RedfinedData.MON || RedfinedData.MON[7] != null) ? RedfinedData.MON[7].price
						: 0 + '"',
				(!RedfinedData.TUE || RedfinedData.TUE[7] != null) ? RedfinedData.TUE[7].price
						: 0,
				(!RedfinedData.WED || RedfinedData.WED[7] != null) ? RedfinedData.WED[7].price
						: 0,
				(!RedfinedData.THU || RedfinedData.THU[7] != null) ? RedfinedData.THU[7].price
						: 0,
				(!RedfinedData.FRI || RedfinedData.FRI[7] != null) ? RedfinedData.FRI[7].price
						: 0,
				(!RedfinedData.SAT || RedfinedData.SAT[7] != null) ? RedfinedData.SAT[7].price
						: 0,
				(!RedfinedData.SUN || RedfinedData.SUN[7] != null) ? RedfinedData.SUN[7].price
						: 0 ];

		var LabelArray = [];
		angular.forEach(response, function(valu, key) {
			LabelArray.push(key);
		});

		var lineChartDashBoardOverAll = {
			labels : [ "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" ],

			labels : LabelArray,
			datasets : [ {
				labels : LabelArray,
				fillColor : "rgb(0, 188, 7)",
				strokeColor : "rgb(0, 188, 7,1)",
				pointColor : "rgb(0, 188, 7,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgb(0, 188, 7,1)",
				data : data1,
				fill : false,
			}, {
				labels : LabelArray,
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : data2,
				fill : false,
			}, {
				labels : LabelArray,
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : data3,
				fill : false,
			}, {
				labels : LabelArray,
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : data4,
				fill : false,
			}, {
				labels : LabelArray,
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : data5,
				fill : false,
			}, {
				labels : LabelArray,
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : data6,
				fill : false,
			}, {
				labels : LabelArray,
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : data7,
				fill : false,
			}, {
				labels : LabelArray,
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : data8,
				fill : false,
			} ]

		}

		var canvas = document.getElementById("line-chart-dashBoardOverAll");
		var ctx = canvas.getContext('2d');

		// Global Options:
		Chart.defaults.global.defaultFontColor = 'black';
		Chart.defaults.global.defaultFontSize = 16;
		var data = {
			labels : [ "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" ],
			datasets : [ {
				label : "Flight",
				fill : true,
				lineTension : 0.1,
				// backgroundColor: "rgba(139,107,216,0.4)",
				borderColor : "rgb(139,107,216)", // The main line color
				borderCapStyle : 'butt',
				borderDash : [], // try [5, 15] for instance
				// borderDashOffset: 0.0,
				borderJoinStyle : 'round',
				pointBorderColor : "rgba(139,107,216,0.4)",
				pointBackgroundColor : "rgb(139,107,216)",
				pointBorderWidth : 2,
				pointHoverRadius : 4,
				pointHoverBackgroundColor : "rgb(139,107,216)",
				pointHoverBorderColor : "rgba(139,107,216,0.4)",
				pointHoverBorderWidth : 1,
				pointRadius : 2,
				pointHitRadius : 5,
				// notice the gap in the data and the spanGaps: true
				dataprice : data1,
				data : dataprice1,
				spanGaps : true,
			}, {
				label : "Hotel",
				fill : true,
				lineTension : 0.1,
				// backgroundColor: "rgba(0,171,237,0.4)",
				borderColor : "rgb(0,171,237)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'round',
				pointBorderColor : "rgb(0,171,237)",
				pointBackgroundColor : "rgb(0,171,237,0.4)",
				pointBorderWidth : 2,
				pointHoverRadius : 4,
				pointHoverBackgroundColor : "rgb(0,171,237)",
				pointHoverBorderColor : "rgb(0,171,237,0.4)",
				pointHoverBorderWidth : 1,
				pointRadius : 2,
				pointHitRadius : 5,
				// notice the gap in the data and the spanGaps: false
				dataprice : data2,
				data : dataprice2,
				spanGaps : false,
			}, {
				label : "Bus",
				fill : true,
				lineTension : 0.1,
				//  backgroundColor: "rgba(255,67,83,0.7)",
				borderColor : "rgb(255,67,83)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgb(255,67,83)",
				pointBackgroundColor : "rgb(255,67,83,0.4)",
				pointBorderWidth : 2,
				pointHoverRadius : 4,
				pointHoverBackgroundColor : "rgb(255,67,83,0.4)",
				pointHoverBorderColor : "rgb(255,67,83)",
				pointHoverBorderWidth : 1,
				pointRadius : 2,
				pointHitRadius : 5,
				// notice the gap in the data and the spanGaps: false
				dataprice : data3,
				data : dataprice3,
				spanGaps : false,
			}, {
				label : "Car",
				fill : true,
				lineTension : 0.1,
				//  backgroundColor: "rgba(142,202,54,0.4)",
				borderColor : "rgb(142,202,54)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgb(142,202,54)",
				pointBackgroundColor : "rgb(142,202,54,0.4)",
				pointBorderWidth : 2,
				pointHoverRadius : 4,
				pointHoverBackgroundColor : "rgb(142,202,54,0.4)",
				pointHoverBorderColor : "rgb(142,202,54)",
				pointHoverBorderWidth : 1,
				pointRadius : 2,
				pointHitRadius : 5,
				// notice the gap in the data and the spanGaps: false
				dataprice : data4,
				data : dataprice4,
				spanGaps : false,
			}, {
				label : "Train",
				fill : true,
				lineTension : 0.1,
				//   backgroundColor: "rgba(0,45,86,0.4)",
				borderColor : "rgb(0,45,86)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgb(0,45,86)",
				pointBackgroundColor : "rgb(0,45,86,0.4)",
				pointBorderWidth : 2,
				pointHoverRadius : 4,
				pointHoverBackgroundColor : "rgb(0,45,86,0.4)",
				pointHoverBorderColor : "rgb(0,45,86)",
				pointHoverBorderWidth : 1,
				pointRadius : 2,
				pointHitRadius : 5,
				// notice the gap in the data and the spanGaps: false
				dataprice : data5,
				data : dataprice5,
				spanGaps : false,
			}, {
				label : "Visa",
				fill : true,
				lineTension : 0.1,
				//     backgroundColor: "rgba(0,77,86,0.4)",
				borderColor : "rgb(0,77,86)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgb(0,77,86)",
				pointBackgroundColor : "rgb(0,77,86,0.4)",
				pointBorderWidth : 2,
				pointHoverRadius : 4,
				pointHoverBackgroundColor : "rgb(0,77,86,0.4)",
				pointHoverBorderColor : "rgb(0,77,86)",
				pointHoverBorderWidth : 1,
				pointRadius : 2,
				pointHitRadius : 5,
				// notice the gap in the data and the spanGaps: false
				dataprice : data6,
				data : dataprice6,
				spanGaps : false,
			}, {
				label : "Insurance",
				fill : true,
				lineTension : 0.1,
				//  backgroundColor: "rgba(0,255,0,0.4)",
				borderColor : "rgb(29, 79, 29)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgb(29, 79, 29)",
				pointBackgroundColor : "rgb(29,79,29,0.4)",
				pointBorderWidth : 1,
				pointHoverRadius : 4,
				pointHoverBackgroundColor : "rgb(29,79,29,0.4)",
				pointHoverBorderColor : "rgb(29,79,29)",
				pointHoverBorderWidth : 1,
				pointRadius : 1,
				pointHitRadius : 5,
				// notice the gap in the data and the spanGaps: false
				dataprice : data7,
				data : dataprice7,
				spanGaps : false,
			}, {
				label : "Misc",
				fill : true,
				lineTension : 0.1,
				//      backgroundColor: "rgba(75,192,192,0.4)",
				borderColor : "rgb(75,192,192)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgb(75,192,192)",
				pointBackgroundColor : "rgba(75,192,192,0.4)",
				pointBorderWidth : 1,
				pointHoverRadius : 4,
				pointHoverBackgroundColor : "rgba(75,192,192,0.4)",
				pointHoverBorderColor : "rgb(75,192,192)",
				pointHoverBorderWidth : 1,
				pointRadius : 1,
				pointHitRadius : 5,
				// notice the gap in the data and the spanGaps: false
				dataprice : data8,
				data : dataprice8,
				spanGaps : false,
			}

			]
		};

		// Notice the scaleLabel at the same level as Ticks
		var options = {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					},
					scaleLabel : {
						display : true,
						labelString : 'Tayyarah-Sale(s) Price',
						fontSize : 18
					}
				} ],
			},
			legend : {
				display : false
			},

			tooltips : {
				callbacks : {
					label : function(tooltipItems, data) {

						var i, label = [], l = data.datasets.length;
						for (i = 0; i < l; i += 1) {
							var labeldata = data.datasets[i].dataprice[tooltipItems.index]
									+ " - "
									+ data.datasets[i].data[tooltipItems.index]
									+ "(INR)";
							//console.log("labeldata",labeldata);
							//console.log("data.datasets[i].dataprice[tooltipItems.index]",data.datasets[i].dataprice[tooltipItems.index]);
							// console.log("data.datasets[i].data[tooltipItems.index]",data.datasets[i].data[tooltipItems.index]);
							label[i] = data.datasets[i].label + ' : '
									+ labeldata;
						}
						return label;
					}

				}
			}
		};

		// Chart declaration:
		var myBarChart = new Chart(ctx, {
			type : 'line',
			data : data,
			options : options
		});
	}

	function getHotelBarData() {
		var url = 'getWeeklySalesTop5Hotels';
		var chartjsHotelBarData = [];

		$
				.ajax({
					method : "GET",
					url : url,
					success : function(data, status) {
						var datalength = data.hotelJsonData.monthlyWeeklyCommondata;
						for (var i = 0; i < datalength.length; i++) {
							chartjsHotelBarData.push(datalength[i].count);
						}
						var barchrtHotelConfirmedData = {
							labels : [ "Mon", "Tue", "Wed", "Thu", "Fri",
									"Sat", "Sun" ],

							datasets : [ {
								borderColor : "#3e95cd",
								data : [ 86, 114, 221, 467, 547, 675, 897 ],
								fill : false,
							} ]

						}
						var ctxHotelBar = document.getElementById(
								"chart-hotel-bar-month").getContext("2d");
						window.myBar = new Chart(ctxHotelBar).Bar(
								barchrtHotelConfirmedData, {
									responsive : true
								});
					},
					error : function(xhr, status, error) {
						console.log(error)
					}
				});
	}

	function prepareBarChart(response) {
		//console.log("flightBarChart", response);
		var Monday = [];
		var Tuesday = [];
		var Wednesday = [];
		var Thursday = [];
		var Friday = [];
		var Saturday = [];
		var Sunday = [];
		var LabelArray = [];
		if (response != undefined) {
			angular.forEach(response, function(val, key) {
				if (key == 'MON')
					Monday = val;
				if (key == 'TUE')
					Tuesday = val;
				if (key == 'WED')
					Wednesday = val;
				if (key == 'THU')
					Thursday = val;
				if (key == 'FRI')
					Friday = val;
				if (key == 'SAT')
					Saturday = val;
				if (key == 'SUN')
					Sunday = val;
			});

			angular.forEach(response, function(valu, key) {
				LabelArray.push(key);
			});

		}

		if (response != undefined) {
			var data1 = [
					(!response.MON || response.MON[0] != null) ? response.MON[0].totalCount
							: 0,
					(!response.TUE || response.TUE[0] != null) ? response.TUE[0].totalCount
							: 0,
					(!response.WED || response.WED[0] != null) ? response.WED[0].totalCount
							: 0,
					(!response.THU || response.THU[0] != null) ? response.THU[0].totalCount
							: 0,
					(!response.FRI || response.FRI[0] != null) ? response.FRI[0].totalCount
							: 0,
					(!response.SAT || response.SAT[0] != null) ? response.SAT[0].totalCount
							: 0,
					(!response.SUN || response.SUN[0] != null) ? response.SUN[0].totalCount
							: 0 ];
			var data2 = [
					(!response.MON || response.MON[1] != null) ? response.MON[1].totalCount
							: 0,
					(!response.TUE || response.TUE[1] != null) ? response.TUE[1].totalCount
							: 0,
					(!response.WED || response.WED[1] != null) ? response.WED[1].totalCount
							: 0,
					(!response.THU || response.THU[1] != null) ? response.THU[1].totalCount
							: 0,
					(!response.FRI || response.FRI[1] != null) ? response.FRI[1].totalCount
							: 0,
					(!response.SAT || response.SAT[1] != null) ? response.SAT[1].totalCount
							: 0,
					(!response.SUN || response.SUN[1] != null) ? response.SUN[1].totalCount
							: 0 ];
			var data3 = [
					(!response.MON || response.MON[2] != null) ? response.MON[2].totalCount
							: 0,
					(!response.TUE || response.TUE[2] != null) ? response.TUE[2].totalCount
							: 0,
					(!response.WED || response.WED[2] != null) ? response.WED[2].totalCount
							: 0,
					(!response.THU || response.THU[2] != null) ? response.THU[2].totalCount
							: 0,
					(!response.FRI || response.FRI[2] != null) ? response.FRI[2].totalCount
							: 0,
					(!response.SAT || response.SAT[2] != null) ? response.SAT[2].totalCount
							: 0,
					(!response.SUN || response.SUN[2] != null) ? response.SUN[2].totalCount
							: 0 ];
			var data4 = [
					(!response.MON || response.MON[3] != null) ? response.MON[3].totalCount
							: 0,
					(!response.TUE || response.TUE[3] != null) ? response.TUE[3].totalCount
							: 0,
					(!response.WED || response.WED[3] != null) ? response.WED[3].totalCount
							: 0,
					(!response.THU || response.THU[3] != null) ? response.THU[3].totalCount
							: 0,
					(!response.FRI || response.FRI[3] != null) ? response.FRI[3].totalCount
							: 0,
					(!response.SAT || response.SAT[3] != null) ? response.SAT[3].totalCount
							: 0,
					(!response.SUN || response.SUN[3] != null) ? response.SUN[3].totalCount
							: 0 ];
			var data5 = [
					(!response.MON || response.MON[4] != null) ? response.MON[4].totalCount
							: 0,
					(!response.TUE || response.TUE[4] != null) ? response.TUE[4].totalCount
							: 0,
					(!response.WED || response.WED[4] != null) ? response.WED[4].totalCount
							: 0,
					(!response.THU || response.THU[4] != null) ? response.THU[4].totalCount
							: 0,
					(!response.FRI || response.FRI[4] != null) ? response.FRI[4].totalCount
							: 0,
					(!response.SAT || response.SAT[4] != null) ? response.SAT[4].totalCount
							: 0,
					(!response.SUN || response.SUN[4] != null) ? response.SUN[4].totalCount
							: 0 ];
		} else {
			var data1 = [ 0, 0, 0, 0, 1 ];
			var data2 = [ 0, 0, 0, 0, 0 ];
			var data3 = [ 0, 0, 0, 0, 0 ];
			var data4 = [ 0, 0, 0, 0, 0 ];
			var data5 = [ 0, 0, 0, 0, 0 ];
		}

		var ctx = document.getElementById("chartbarmonth");

		var barChartConfirmedData = {
			labels : LabelArray,
			datasets : [ {
				label : "flight1",
				fillColor : "rgb(139,107,216)",
				backgroundColor : "rgb(139,107,216)",
				pointColor : "rgb(0,255,255)",
				data : data1
			}, {
				label : "flight2",
				fillColor : "rgb(0,171,237)",
				backgroundColor : "rgb(0,171,237)",
				pointColor : "rgb(0,255,0)",
				data : data2
			}, {
				label : "flight3",
				fillColor : "rgb(255,67,83)",
				backgroundColor : "rgb(255,67,83)",
				pointColor : "rgb(255,0,0)",
				data : data3
			}, {
				label : "flight4",
				fillColor : "rgb(142,202,54)",
				backgroundColor : "rgb(142,202,54)",
				pointColor : "rgb(0,0,0)",
				data : data4
			}, {
				label : "flight5",
				fillColor : "rgb(0,45,86)",
				backgroundColor : "rgb(0,45,86)",
				pointColor : "rgb(255,255,0)",
				data : data5
			} ]
		}

		var options = {
			legend : {
				display : false
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : 1,
						fontSize : 15
					}
				} ],
			},
			tooltips : {
				callbacks : {
					label : function(tooltipItems, data) {
						var i, label = [], l = data.datasets.length;
						if (tooltipItems.xLabel == 'MON') {
							for (i = 0; i < l; i += 1) {
								if (Monday[i] != undefined)
									label[i] = Monday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'TUE') {
							for (i = 0; i < l; i += 1) {
								if (Tuesday[i] != undefined)
									label[i] = Tuesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'WED') {
							for (i = 0; i < l; i += 1) {
								if (Wednesday[i] != undefined)
									label[i] = Wednesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'THU') {
							for (i = 0; i < l; i += 1) {
								if (Thursday[i] != undefined)
									label[i] = Thursday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'FRI') {
							for (i = 0; i < l; i += 1) {
								if (Friday[i] != undefined)
									label[i] = Friday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SAT') {
							for (i = 0; i < l; i += 1) {
								if (Saturday[i] != undefined)
									label[i] = Saturday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SUN') {
							for (i = 0; i < l; i += 1) {
								if (Sunday[i] != undefined)
									label[i] = Sunday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						return label;
					}
				}
			}
		}

		var myLineChart = new Chart(ctx, {
			type : 'bar',
			data : barChartConfirmedData,
			options : options
		});
	};
	function prepareHotelBarChart(response) {
		console.log("HotelBarChart", response);
		var Monday = [];
		var Tuesday = [];
		var Wednesday = [];
		var Thursday = [];
		var Friday = [];
		var Saturday = [];
		var Sunday = [];
		var LabelArray = [];
		if (response != undefined) {
			angular.forEach(response, function(val, key) {
				if (key == 'MON')
					Monday = val;
				if (key == 'TUE')
					Tuesday = val;
				if (key == 'WED')
					Wednesday = val;
				if (key == 'THU')
					Thursday = val;
				if (key == 'FRI')
					Friday = val;
				if (key == 'SAT')
					Saturday = val;
				if (key == 'SUN')
					Sunday = val;
			});

			angular.forEach(response, function(valu, key) {
				LabelArray.push(key);
			});
		}
		if (response != undefined) {
			var data1 = [
					(!response.MON || response.MON[0] != null) ? response.MON[0].totalCount
							: 0,
					(!response.TUE || response.TUE[0] != null) ? response.TUE[0].totalCount
							: 0,
					(!response.WED || response.WED[0] != null) ? response.WED[0].totalCount
							: 0,
					(!response.THU || response.THU[0] != null) ? response.THU[0].totalCount
							: 0,
					(!response.FRI || response.FRI[0] != null) ? response.FRI[0].totalCount
							: 0,
					(!response.SAT || response.SAT[0] != null) ? response.SAT[0].totalCount
							: 0,
					(!response.SUN || response.SUN[0] != null) ? response.SUN[0].totalCount
							: 0 ];
			var data2 = [
					(!response.MON || response.MON[1] != null) ? response.MON[1].totalCount
							: 0,
					(!response.TUE || response.TUE[1] != null) ? response.TUE[1].totalCount
							: 0,
					(!response.WED || response.WED[1] != null) ? response.WED[1].totalCount
							: 0,
					(!response.THU || response.THU[1] != null) ? response.THU[1].totalCount
							: 0,
					(!response.FRI || response.FRI[1] != null) ? response.FRI[1].totalCount
							: 0,
					(!response.SAT || response.SAT[1] != null) ? response.SAT[1].totalCount
							: 0,
					(!response.SUN || response.SUN[1] != null) ? response.SUN[1].totalCount
							: 0 ];
			var data3 = [
					(!response.MON || response.MON[2] != null) ? response.MON[2].totalCount
							: 0,
					(!response.TUE || response.TUE[2] != null) ? response.TUE[2].totalCount
							: 0,
					(!response.WED || response.WED[2] != null) ? response.WED[2].totalCount
							: 0,
					(!response.THU || response.THU[2] != null) ? response.THU[2].totalCount
							: 0,
					(!response.FRI || response.FRI[2] != null) ? response.FRI[2].totalCount
							: 0,
					(!response.SAT || response.SAT[2] != null) ? response.SAT[2].totalCount
							: 0,
					(!response.SUN || response.SUN[2] != null) ? response.SUN[2].totalCount
							: 0 ];
			var data4 = [
					(!response.MON || response.MON[3] != null) ? response.MON[3].totalCount
							: 0,
					(!response.TUE || response.TUE[3] != null) ? response.TUE[3].totalCount
							: 0,
					(!response.WED || response.WED[3] != null) ? response.WED[3].totalCount
							: 0,
					(!response.THU || response.THU[3] != null) ? response.THU[3].totalCount
							: 0,
					(!response.FRI || response.FRI[3] != null) ? response.FRI[3].totalCount
							: 0,
					(!response.SAT || response.SAT[3] != null) ? response.SAT[3].totalCount
							: 0,
					(!response.SUN || response.SUN[3] != null) ? response.SUN[3].totalCount
							: 0 ];
			var data5 = [
					(!response.MON || response.MON[4] != null) ? response.MON[4].totalCount
							: 0,
					(!response.TUE || response.TUE[4] != null) ? response.TUE[4].totalCount
							: 0,
					(!response.WED || response.WED[4] != null) ? response.WED[4].totalCount
							: 0,
					(!response.THU || response.THU[4] != null) ? response.THU[4].totalCount
							: 0,
					(!response.FRI || response.FRI[4] != null) ? response.FRI[4].totalCount
							: 0,
					(!response.SAT || response.SAT[4] != null) ? response.SAT[4].totalCount
							: 0,
					(!response.SUN || response.SUN[4] != null) ? response.SUN[4].totalCount
							: 0 ];
		} else {
			var data1 = [ 0, 0, 0, 0, 1 ];
			var data2 = [ 0, 0, 0, 0, 0 ];
			var data3 = [ 0, 0, 0, 0, 0 ];
			var data4 = [ 0, 0, 0, 0, 0 ];
			var data5 = [ 0, 0, 0, 0, 0 ];
		}

		var ctx = document.getElementById("hotelChartBarMonth");

		var hotelBarChartConfirmedData = {
			labels : LabelArray,
			datasets : [ {
				label : "Economy",
				fillColor : "rgb(139,107,216)",
				backgroundColor : "rgb(139,107,216)",
				pointColor : "rgb(0,255,255)",
				data : data1
			}, {
				label : "Economy",
				fillColor : "rgb(0,171,237)",
				backgroundColor : "rgb(0,171,237)",
				pointColor : "rgb(0,255,0)",
				data : data2
			}, {
				label : "Economy",
				fillColor : "rgb(255,67,83)",
				backgroundColor : "rgb(255,67,83)",
				pointColor : "rgb(255,0,0)",
				data : data3
			}, {
				label : "Economy",
				fillColor : "rgb(142,202,54)",
				backgroundColor : "rgb(142,202,54)",
				pointColor : "rgb(0,0,0)",
				data : data4
			}, {
				label : "Economy",
				fillColor : "rgb(0,45,86)",
				backgroundColor : "rgb(0,45,86)",
				pointColor : "rgb(255,255,0)",
				data : data5
			} ]
		}

		var options = {
			legend : {
				display : false
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : 1,
						fontSize : 15
					}
				} ],
			},
			tooltips : {
				callbacks : {
					label : function(tooltipItems, data) {
						var i, label = [], l = data.datasets.length;
						if (tooltipItems.xLabel == 'MON') {
							for (i = 0; i < l; i += 1) {
								if (Monday[i] != undefined)
									label[i] = Monday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'TUE') {
							for (i = 0; i < l; i += 1) {
								if (Tuesday[i] != undefined)
									label[i] = Tuesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'WED') {
							for (i = 0; i < l; i += 1) {
								if (Wednesday[i] != undefined)
									label[i] = Wednesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'THU') {
							for (i = 0; i < l; i += 1) {
								if (Thursday[i] != undefined)
									label[i] = Thursday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'FRI') {
							for (i = 0; i < l; i += 1) {
								if (Friday[i] != undefined)
									label[i] = Friday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SAT') {
							for (i = 0; i < l; i += 1) {
								if (Saturday[i] != undefined)
									label[i] = Saturday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SUN') {
							for (i = 0; i < l; i += 1) {
								if (Sunday[i] != undefined)
									label[i] = Sunday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						return label;
					}
				}
			}
		}

		var myLineChart = new Chart(ctx, {
			type : 'bar',
			data : hotelBarChartConfirmedData,
			options : options,
		});
	}
	function prepareCarBarChart(response) {
		console.log("CarBarChart", response);
		var LabelArray = [];
		var Monday = [];
		var Tuesday = [];
		var Wednesday = [];
		var Thursday = [];
		var Friday = [];
		var Saturday = [];
		var Sunday = [];
		if (response != undefined) {

			angular.forEach(response, function(val, key) {
				if (key == 'MON')
					Monday = val;
				if (key == 'TUE')
					Tuesday = val;
				if (key == 'WED')
					Wednesday = val;
				if (key == 'THU')
					Thursday = val;
				if (key == 'FRI')
					Friday = val;
				if (key == 'SAT')
					Saturday = val;
				if (key == 'SUN')
					Sunday = val;
			});

			angular.forEach(response, function(valu, key) {
				LabelArray.push(key);
			});
		}
		if (response != undefined) {
			var data1 = [
					(!response.MON || response.MON[0] != null) ? response.MON[0].totalCount
							: 0,
					(!response.TUE || response.TUE[0] != null) ? response.TUE[0].totalCount
							: 0,
					(!response.WED || response.WED[0] != null) ? response.WED[0].totalCount
							: 0,
					(!response.THU || response.THU[0] != null) ? response.THU[0].totalCount
							: 0,
					(!response.FRI || response.FRI[0] != null) ? response.FRI[0].totalCount
							: 0,
					(!response.SAT || response.SAT[0] != null) ? response.SAT[0].totalCount
							: 0,
					(!response.SUN || response.SUN[0] != null) ? response.SUN[0].totalCount
							: 0 ];
			var data2 = [
					(!response.MON || response.MON[1] != null) ? response.MON[1].totalCount
							: 0,
					(!response.TUE || response.TUE[1] != null) ? response.TUE[1].totalCount
							: 0,
					(!response.WED || response.WED[1] != null) ? response.WED[1].totalCount
							: 0,
					(!response.THU || response.THU[1] != null) ? response.THU[1].totalCount
							: 0,
					(!response.FRI || response.FRI[1] != null) ? response.FRI[1].totalCount
							: 0,
					(!response.SAT || response.SAT[1] != null) ? response.SAT[1].totalCount
							: 0,
					(!response.SUN || response.SUN[1] != null) ? response.SUN[1].totalCount
							: 0 ];
			var data3 = [
					(!response.MON || response.MON[2] != null) ? response.MON[2].totalCount
							: 0,
					(!response.TUE || response.TUE[2] != null) ? response.TUE[2].totalCount
							: 0,
					(!response.WED || response.WED[2] != null) ? response.WED[2].totalCount
							: 0,
					(!response.THU || response.THU[2] != null) ? response.THU[2].totalCount
							: 0,
					(!response.FRI || response.FRI[2] != null) ? response.FRI[2].totalCount
							: 0,
					(!response.SAT || response.SAT[2] != null) ? response.SAT[2].totalCount
							: 0,
					(!response.SUN || response.SUN[2] != null) ? response.SUN[2].totalCount
							: 0 ];
			var data4 = [
					(!response.MON || response.MON[3] != null) ? response.MON[3].totalCount
							: 0,
					(!response.TUE || response.TUE[3] != null) ? response.TUE[3].totalCount
							: 0,
					(!response.WED || response.WED[3] != null) ? response.WED[3].totalCount
							: 0,
					(!response.THU || response.THU[3] != null) ? response.THU[3].totalCount
							: 0,
					(!response.FRI || response.FRI[3] != null) ? response.FRI[3].totalCount
							: 0,
					(!response.SAT || response.SAT[3] != null) ? response.SAT[3].totalCount
							: 0,
					(!response.SUN || response.SUN[3] != null) ? response.SUN[3].totalCount
							: 0 ];
			var data5 = [
					(!response.MON || response.MON[4] != null) ? response.MON[4].totalCount
							: 0,
					(!response.TUE || response.TUE[4] != null) ? response.TUE[4].totalCount
							: 0,
					(!response.WED || response.WED[4] != null) ? response.WED[4].totalCount
							: 0,
					(!response.THU || response.THU[4] != null) ? response.THU[4].totalCount
							: 0,
					(!response.FRI || response.FRI[4] != null) ? response.FRI[4].totalCount
							: 0,
					(!response.SAT || response.SAT[4] != null) ? response.SAT[4].totalCount
							: 0,
					(!response.SUN || response.SUN[4] != null) ? response.SUN[4].totalCount
							: 0 ];
		} else {
			var data1 = [ 0, 0, 0, 0, 1 ];
			var data2 = [ 0, 0, 0, 0, 0 ];
			var data3 = [ 0, 0, 0, 0, 0 ];
			var data4 = [ 0, 0, 0, 0, 0 ];
			var data5 = [ 0, 0, 0, 0, 0 ];
		}
		var ctx = document.getElementById("carChartBarMonth");

		var carBarChartConfirmedData = {
			labels : LabelArray,
			datasets : [ {
				label : "Economy",
				fillColor : "rgb(139,107,216)",
				backgroundColor : "rgb(139,107,216)",
				pointColor : "rgb(0,255,255)",
				data : data1
			}, {
				label : "Economy",
				fillColor : "rgb(0,171,237)",
				backgroundColor : "rgb(0,171,237)",
				pointColor : "rgb(0,255,0)",
				data : data2
			}, {
				label : "Economy",
				fillColor : "rgb(255,67,83)",
				backgroundColor : "rgb(255,67,83)",
				pointColor : "rgb(255,0,0)",
				data : data3
			}, {
				label : "Economy",
				fillColor : "rgb(142,202,54)",
				backgroundColor : "rgb(142,202,54)",
				pointColor : "rgb(0,0,0)",
				data : data4
			}, {
				label : "Economy",
				fillColor : "rgb(0,45,86)",
				backgroundColor : "rgb(0,45,86)",
				pointColor : "rgb(255,255,0)",
				data : data5
			} ]
		}

		var options = {
			legend : {
				display : false
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : 1,
						fontSize : 15
					}
				} ],
			},
			tooltips : {
				callbacks : {
					label : function(tooltipItems, data) {
						var i, label = [], l = data.datasets.length;
						if (tooltipItems.xLabel == 'MON') {
							for (i = 0; i < l; i += 1) {
								if (Monday[i] != undefined)
									label[i] = Monday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'TUE') {
							for (i = 0; i < l; i += 1) {
								if (Tuesday[i] != undefined)
									label[i] = Tuesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'WED') {
							for (i = 0; i < l; i += 1) {
								if (Wednesday[i] != undefined)
									label[i] = Wednesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'THU') {
							for (i = 0; i < l; i += 1) {
								if (Thursday[i] != undefined)
									label[i] = Thursday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'FRI') {
							for (i = 0; i < l; i += 1) {
								if (Friday[i] != undefined)
									label[i] = Friday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SAT') {
							for (i = 0; i < l; i += 1) {
								if (Saturday[i] != undefined)
									label[i] = Saturday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SUN') {
							for (i = 0; i < l; i += 1) {
								if (Sunday[i] != undefined)
									label[i] = Sunday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						return label;
					}
				}
			}
		}
		var myLineChart = new Chart(ctx, {
			type : 'bar',
			data : carBarChartConfirmedData,
			options : options,
		});
	}
	function prepareBusBarChart(response) {
		console.log("BusBarChart", response);
		var LabelArray = [];
		var Monday = [];
		var Tuesday = [];
		var Wednesday = [];
		var Thursday = [];
		var Friday = [];
		var Saturday = [];
		var Sunday = [];
		if (response != undefined) {
			angular.forEach(response, function(val, key) {
				if (key == 'MON')
					Monday = val;
				if (key == 'TUE')
					Tuesday = val;
				if (key == 'WED')
					Wednesday = val;
				if (key == 'THU')
					Thursday = val;
				if (key == 'FRI')
					Friday = val;
				if (key == 'SAT')
					Saturday = val;
				if (key == 'SUN')
					Sunday = val;
			});
			angular.forEach(response, function(valu, key) {
				LabelArray.push(key);
			});
		}
		if (response != undefined) {
			var data1 = [
					(!response.MON || response.MON[0] != null) ? response.MON[0].totalCount
							: 0,
					(!response.TUE || response.TUE[0] != null) ? response.TUE[0].totalCount
							: 0,
					(!response.WED || response.WED[0] != null) ? response.WED[0].totalCount
							: 0,
					(!response.THU || response.THU[0] != null) ? response.THU[0].totalCount
							: 0,
					(!response.FRI || response.FRI[0] != null) ? response.FRI[0].totalCount
							: 0,
					(!response.SAT || response.SAT[0] != null) ? response.SAT[0].totalCount
							: 0,
					(!response.SUN || response.SUN[0] != null) ? response.SUN[0].totalCount
							: 0 ];
			var data2 = [
					(!response.MON || response.MON[1] != null) ? response.MON[1].totalCount
							: 0,
					(!response.TUE || response.TUE[1] != null) ? response.TUE[1].totalCount
							: 0,
					(!response.WED || response.WED[1] != null) ? response.WED[1].totalCount
							: 0,
					(!response.THU || response.THU[1] != null) ? response.THU[1].totalCount
							: 0,
					(!response.FRI || response.FRI[1] != null) ? response.FRI[1].totalCount
							: 0,
					(!response.SAT || response.SAT[1] != null) ? response.SAT[1].totalCount
							: 0,
					(!response.SUN || response.SUN[1] != null) ? response.SUN[1].totalCount
							: 0 ];
			var data3 = [
					(!response.MON || response.MON[2] != null) ? response.MON[2].totalCount
							: 0,
					(!response.TUE || response.TUE[2] != null) ? response.TUE[2].totalCount
							: 0,
					(!response.WED || response.WED[2] != null) ? response.WED[2].totalCount
							: 0,
					(!response.THU || response.THU[2] != null) ? response.THU[2].totalCount
							: 0,
					(!response.FRI || response.FRI[2] != null) ? response.FRI[2].totalCount
							: 0,
					(!response.SAT || response.SAT[2] != null) ? response.SAT[2].totalCount
							: 0,
					(!response.SUN || response.SUN[2] != null) ? response.SUN[2].totalCount
							: 0 ];
			var data4 = [
					(!response.MON || response.MON[3] != null) ? response.MON[3].totalCount
							: 0,
					(!response.TUE || response.TUE[3] != null) ? response.TUE[3].totalCount
							: 0,
					(!response.WED || response.WED[3] != null) ? response.WED[3].totalCount
							: 0,
					(!response.THU || response.THU[3] != null) ? response.THU[3].totalCount
							: 0,
					(!response.FRI || response.FRI[3] != null) ? response.FRI[3].totalCount
							: 0,
					(!response.SAT || response.SAT[3] != null) ? response.SAT[3].totalCount
							: 0,
					(!response.SUN || response.SUN[3] != null) ? response.SUN[3].totalCount
							: 0 ];
			var data5 = [
					(!response.MON || response.MON[4] != null) ? response.MON[4].totalCount
							: 0,
					(!response.TUE || response.TUE[4] != null) ? response.TUE[4].totalCount
							: 0,
					(!response.WED || response.WED[4] != null) ? response.WED[4].totalCount
							: 0,
					(!response.THU || response.THU[4] != null) ? response.THU[4].totalCount
							: 0,
					(!response.FRI || response.FRI[4] != null) ? response.FRI[4].totalCount
							: 0,
					(!response.SAT || response.SAT[4] != null) ? response.SAT[4].totalCount
							: 0,
					(!response.SUN || response.SUN[4] != null) ? response.SUN[4].totalCount
							: 0 ];
		} else {
			var data1 = [ 0, 0, 0, 0, 1 ];
			var data2 = [ 0, 0, 0, 0, 0 ];
			var data3 = [ 0, 0, 0, 0, 0 ];
			var data4 = [ 0, 0, 0, 0, 0 ];
			var data5 = [ 0, 0, 0, 0, 0 ];
		}
		var ctx = document.getElementById("busChartBarMonth");

		var busBarChartConfirmedData = {
			labels : LabelArray,
			datasets : [ {
				label : "Economy",
				fillColor : "rgb(139,107,216)",
				backgroundColor : "rgb(139,107,216)",
				pointColor : "rgb(0,255,255)",
				data : data1
			}, {
				label : "Economy",
				fillColor : "rgb(0,171,237)",
				backgroundColor : "rgb(0,171,237)",
				pointColor : "rgb(0,255,0)",
				data : data2
			}, {
				label : "Economy",
				fillColor : "rgb(255,67,83)",
				backgroundColor : "rgb(255,67,83)",
				pointColor : "rgb(255,0,0)",
				data : data3
			}, {
				label : "Economy",
				fillColor : "rgb(142,202,54)",
				backgroundColor : "rgb(142,202,54)",
				pointColor : "rgb(0,0,0)",
				data : data4
			}, {
				label : "Economy",
				fillColor : "rgb(0,45,86)",
				backgroundColor : "rgb(0,45,86)",
				pointColor : "rgb(255,255,0)",
				data : data5
			} ]
		}

		var options = {
			legend : {
				display : false
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : 1,
						fontSize : 15
					}
				} ],
			},
			tooltips : {
				callbacks : {
					label : function(tooltipItems, data) {
						var i, label = [], l = data.datasets.length;
						if (tooltipItems.xLabel == 'MON') {
							for (i = 0; i < l; i += 1) {
								if (Monday[i] != undefined)
									label[i] = Monday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'TUE') {
							for (i = 0; i < l; i += 1) {
								if (Tuesday[i] != undefined)
									label[i] = Tuesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'WED') {
							for (i = 0; i < l; i += 1) {
								if (Wednesday[i] != undefined)
									label[i] = Wednesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'THU') {
							for (i = 0; i < l; i += 1) {
								if (Thursday[i] != undefined)
									label[i] = Thursday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'FRI') {
							for (i = 0; i < l; i += 1) {
								if (Friday[i] != undefined)
									label[i] = Friday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SAT') {
							for (i = 0; i < l; i += 1) {
								if (Saturday[i] != undefined)
									label[i] = Saturday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SUN') {
							for (i = 0; i < l; i += 1) {
								if (Sunday[i] != undefined)
									label[i] = Sunday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						return label;
					}
				}
			}
		}
		var myLineChart = new Chart(ctx, {
			type : 'bar',
			data : busBarChartConfirmedData,
			options : options,
		});

	}
	function prepareTrainBarChart(response) {
		console.log("TrainBarChart", response);
		var LabelArray = [];
		var Monday = [];
		var Tuesday = [];
		var Wednesday = [];
		var Thursday = [];
		var Friday = [];
		var Saturday = [];
		var Sunday = [];
		if (response != undefined) {
			angular.forEach(response, function(val, key) {
				if (key == 'MON')
					Monday = val;
				if (key == 'TUE')
					Tuesday = val;
				if (key == 'WED')
					Wednesday = val;
				if (key == 'THU')
					Thursday = val;
				if (key == 'FRI')
					Friday = val;
				if (key == 'SAT')
					Saturday = val;
				if (key == 'SUN')
					Sunday = val;
			});
			angular.forEach(response, function(valu, key) {
				LabelArray.push(key);
			});
		}
		if (response != undefined) {
			var data1 = [
					(!response.MON || response.MON[0] != null) ? response.MON[0].totalCount
							: 0,
					(!response.TUE || response.TUE[0] != null) ? response.TUE[0].totalCount
							: 0,
					(!response.WED || response.WED[0] != null) ? response.WED[0].totalCount
							: 0,
					(!response.THU || response.THU[0] != null) ? response.THU[0].totalCount
							: 0,
					(!response.FRI || response.FRI[0] != null) ? response.FRI[0].totalCount
							: 0,
					(!response.SAT || response.SAT[0] != null) ? response.SAT[0].totalCount
							: 0,
					(!response.SUN || response.SUN[0] != null) ? response.SUN[0].totalCount
							: 0 ];
			var data2 = [
					(!response.MON || response.MON[1] != null) ? response.MON[1].totalCount
							: 0,
					(!response.TUE || response.TUE[1] != null) ? response.TUE[1].totalCount
							: 0,
					(!response.WED || response.WED[1] != null) ? response.WED[1].totalCount
							: 0,
					(!response.THU || response.THU[1] != null) ? response.THU[1].totalCount
							: 0,
					(!response.FRI || response.FRI[1] != null) ? response.FRI[1].totalCount
							: 0,
					(!response.SAT || response.SAT[1] != null) ? response.SAT[1].totalCount
							: 0,
					(!response.SUN || response.SUN[1] != null) ? response.SUN[1].totalCount
							: 0 ];
			var data3 = [
					(!response.MON || response.MON[2] != null) ? response.MON[2].totalCount
							: 0,
					(!response.TUE || response.TUE[2] != null) ? response.TUE[2].totalCount
							: 0,
					(!response.WED || response.WED[2] != null) ? response.WED[2].totalCount
							: 0,
					(!response.THU || response.THU[2] != null) ? response.THU[2].totalCount
							: 0,
					(!response.FRI || response.FRI[2] != null) ? response.FRI[2].totalCount
							: 0,
					(!response.SAT || response.SAT[2] != null) ? response.SAT[2].totalCount
							: 0,
					(!response.SUN || response.SUN[2] != null) ? response.SUN[2].totalCount
							: 0 ];
			var data4 = [
					(!response.MON || response.MON[3] != null) ? response.MON[3].totalCount
							: 0,
					(!response.TUE || response.TUE[3] != null) ? response.TUE[3].totalCount
							: 0,
					(!response.WED || response.WED[3] != null) ? response.WED[3].totalCount
							: 0,
					(!response.THU || response.THU[3] != null) ? response.THU[3].totalCount
							: 0,
					(!response.FRI || response.FRI[3] != null) ? response.FRI[3].totalCount
							: 0,
					(!response.SAT || response.SAT[3] != null) ? response.SAT[3].totalCount
							: 0,
					(!response.SUN || response.SUN[3] != null) ? response.SUN[3].totalCount
							: 0 ];
			var data5 = [
					(!response.MON || response.MON[4] != null) ? response.MON[4].totalCount
							: 0,
					(!response.TUE || response.TUE[4] != null) ? response.TUE[4].totalCount
							: 0,
					(!response.WED || response.WED[4] != null) ? response.WED[4].totalCount
							: 0,
					(!response.THU || response.THU[4] != null) ? response.THU[4].totalCount
							: 0,
					(!response.FRI || response.FRI[4] != null) ? response.FRI[4].totalCount
							: 0,
					(!response.SAT || response.SAT[4] != null) ? response.SAT[4].totalCount
							: 0,
					(!response.SUN || response.SUN[4] != null) ? response.SUN[4].totalCount
							: 0 ];
		} else {
			var data1 = [ 0, 0, 0, 0, 1 ];
			var data2 = [ 0, 0, 0, 0, 0 ];
			var data3 = [ 0, 0, 0, 0, 0 ];
			var data4 = [ 0, 0, 0, 0, 0 ];
			var data5 = [ 0, 0, 0, 0, 0 ];
		}
		var ctx = document.getElementById("trainChartBarMonth");

		var trainBarChartConfirmedData = {
			labels : LabelArray,
			datasets : [ {
				label : "Economy",
				fillColor : "rgb(139,107,216)",
				backgroundColor : "rgb(139,107,216)",
				pointColor : "rgb(0,255,255)",
				data : data1
			}, {
				label : "Economy",
				fillColor : "rgb(0,171,237)",
				backgroundColor : "rgb(0,171,237)",
				pointColor : "rgb(0,255,0)",
				data : data2
			}, {
				label : "Economy",
				fillColor : "rgb(255,67,83)",
				backgroundColor : "rgb(255,67,83)",
				pointColor : "rgb(255,0,0)",
				data : data3
			}, {
				label : "Economy",
				fillColor : "rgb(142,202,54)",
				backgroundColor : "rgb(142,202,54)",
				pointColor : "rgb(0,0,0)",
				data : data4
			}, {
				label : "Economy",
				fillColor : "rgb(0,45,86)",
				backgroundColor : "rgb(0,45,86)",
				pointColor : "rgb(255,255,0)",
				data : data5
			} ]
		}
		var options = {
			legend : {
				display : false,
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : 1,
						fontSize : 15
					}
				} ],
			},
			tooltips : {
				callbacks : {
					label : function(tooltipItems, data) {
						var i, label = [], l = data.datasets.length;
						if (tooltipItems.xLabel == 'MON') {
							for (i = 0; i < l; i += 1) {
								if (Monday[i] != undefined)
									label[i] = Monday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'TUE') {
							for (i = 0; i < l; i += 1) {
								if (Tuesday[i] != undefined)
									label[i] = Tuesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'WED') {
							for (i = 0; i < l; i += 1) {
								if (Wednesday[i] != undefined)
									label[i] = Wednesday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'THU') {
							for (i = 0; i < l; i += 1) {
								if (Thursday[i] != undefined)
									label[i] = Thursday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'FRI') {
							for (i = 0; i < l; i += 1) {
								if (Friday[i] != undefined)
									label[i] = Friday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SAT') {
							for (i = 0; i < l; i += 1) {
								if (Saturday[i] != undefined)
									label[i] = Saturday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						if (tooltipItems.xLabel == 'SUN') {
							for (i = 0; i < l; i += 1) {
								if (Sunday[i] != undefined)
									label[i] = Sunday[i].name
											+ ' : '
											+ data.datasets[i].data[tooltipItems.index];
							}
						}
						return label;
					}
				}
			}
		}
		var myLineChart = new Chart(ctx, {
			type : 'bar',
			data : trainBarChartConfirmedData,
			options : options,
		});
	}

	$(".panel-fullscreen").on("click", function() {
		panel_fullscreen($(this).parents(".panel"));
		return false;
	});

	function panel_fullscreen(panel) {

		if (panel.hasClass("panel-fullscreened")) {
			panel.removeClass("panel-fullscreened").unwrap();
			panel.find(".panel-body").css("height", "");
			panel.find(".panel-fullscreen .fa").removeClass("fa-compress")
					.addClass("fa-expand");

			$(window).resize();
		} else {
			var head = panel.find(".panel-heading");
			var body = panel.find(".panel-body");
			var footer = panel.find(".panel-footer");
			var hplus = 30;

			if (body.hasClass("panel-body-table") || body.hasClass("padding-0")) {
				hplus = 0;
			}
			if (head.length > 0) {
				hplus += head.height() + 21;
			}
			if (footer.length > 0) {
				hplus += footer.height() + 21;
			}

			panel.find(".panel-body").height($(window).height() - hplus);

			panel.addClass("panel-fullscreened").wrap(
					'<div class="panel-fullscreen-wrap"></div>');
			panel.find(".panel-fullscreen .fa").removeClass("fa-expand")
					.addClass("fa-compress");

			$(window).resize();
		}
	}

	function panel_remove(panel, action, callback) {
		if (action && action === "before" && typeof callback === "function")
			callback();

		panel.animate({
			'opacity' : 0
		}, 200, function() {
			panel.parent(".panel-fullscreen-wrap").remove();
			$(this).remove();
			if (action && action === "after" && typeof callback === "function")
				callback();

			onload();
		});
	}
	var myLineChart = null;
	function compareBarChart(res) {
		var LabelArray = [];
		var dataArray = [];
		var diffamountArray = [];
		var dataamountArray = [];
		var diffTotalArray = [];
		//res={"MON":{"bookingAmount":1000,"percentageChangeInBookingAmount":100,"percentageChangeInTotalCount":100,"totalCount":2},"TUE":{"bookingAmount":1109,"percentageChangeInBookingAmount":100,"percentageChangeInTotalCount":100,"totalCount":1},"WED":{"bookingAmount":0,"percentageChangeInBookingAmount":-100,"percentageChangeInTotalCount":-100,"totalCount":0},"THU":{"bookingAmount":0,"percentageChangeInBookingAmount":0,"percentageChangeInTotalCount":0,"totalCount":0},"FRI":{"bookingAmount":0,"percentageChangeInBookingAmount":0,"percentageChangeInTotalCount":0,"totalCount":0},"SAT":{"bookingAmount":0,"percentageChangeInBookingAmount":0,"percentageChangeInTotalCount":0,"totalCount":0},"SUN":{"bookingAmount":0,"percentageChangeInBookingAmount":0,"percentageChangeInTotalCount":0,"totalCount":0}};  
		if (res != undefined) {

			angular.forEach(res, function(valu, key) {
				LabelArray.push(key);
				dataamountArray.push(valu.bookingAmount);
				diffamountArray.push(valu.percentageChangeInBookingAmount);
				dataArray.push(valu.totalCount);
				diffTotalArray.push(valu.percentageChangeInTotalCount);
			});
		} else {

			LabelArray = [ 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN' ];
			dataamountArray = [ 0, 0, 0, 0, 0, 0, 0 ];
			diffamountArray = [ 0, 0, 0, 0, 0, 0, 0 ];
			dataArray = [ 0, 0, 0, 0, 0, 0, 0 ];
			diffTotalArray = [ 0, 0, 0, 0, 0, 0, 0 ];
		}

		var maxam = 0;
		var maxlimit = 100;
		maxam = dataamountArray.max();
		if (maxam == 0)
			maxlimit = 500;
		else if (maxam <= 10000)
			maxlimit = 1000;
		else if (maxam <= 50000)
			maxlimit = 5000;
		else if (maxam <= 100000)
			maxlimit = 10000;
		else if (maxam <= 500000)
			maxlimit = 50000;
		else if (maxam <= 1000000)
			maxlimit = 100000;
		else if (maxam <= 5000000)
			maxlimit = 500000;
		else if (maxam <= 1000000)
			maxlimit = 1000000;
		var ctx = document.getElementById("chartbarCompareDay");
		ctx.height = 300;

		var data = {
			labels : LabelArray,
			datasets : [
					{
						label : 'Sales',
						data : dataamountArray,
						borderWidth : 2,
						hoverBackgroundColor : "rgba(55, 160, 225, 0.7)",
						hoverBorderWidth : 2,
						hoverBorderColor : 'lightgrey',
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)',
								'rgba(55, 160, 225, 0.7)',
								'rgba(153, 252, 255, 0.2)', ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)',
								'rgba(55, 160, 225,1)',
								'rgba(153, 252, 255,1)', ]
					},

			]
		};

		var options = {
			maintainAspectRatio : false,
			legend : {
				display : false
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : maxlimit,
						fontSize : 18,
						beginAtZero : true
					}
				} ],
			},
			tooltips : {
				bodyFontSize : 12,
				bodyFontStyle : "bold",
				footerFontSize : 12,
				mode : 'label',
				callbacks : {
					title : function(tooltipItems, data) {
						return data.labels[tooltipItems[0].index];
					},
					label : function(tooltipItem, data) {
						return "Total"
								+ data.datasets[tooltipItem.datasetIndex].label
								+ ": " + numberWithCommas(tooltipItem.yLabel)
								+ " INR";
					},
					footer : function(tooltipItems, data) {

						var dataArrayTooltip = "Segment Count :"
								+ dataArray[tooltipItems[0].index];
						var diffamountArrayTooltip = "Percentage Change in Sales:"
								+ diffamountArray[tooltipItems[0].index] + "%";
						var diffTotalArrayTooltip = "Percent change in segment :"
								+ diffTotalArray[tooltipItems[0].index] + "%";
						return [ diffamountArrayTooltip, dataArrayTooltip,
								diffTotalArrayTooltip ];
					}
				}
			}
		}

		if (myLineChart != null)
			myLineChart.destroy();

		myLineChart = new Chart(ctx, {
			type : 'bar',
			data : data,
			options : options
		});
	};

	Array.prototype.max = function() {
		return Math.max.apply(null, this);
	};

	function weekcompareBarChart(res) {
		var LabelArray = [];
		var dataArray = [];
		var diffamountArray = [];
		var dataamountArray = [];
		var diffTotalArray = [];
		if (res != undefined) {

			angular.forEach(res, function(valu, key) {
				LabelArray.push(key);
				dataamountArray.push(valu.bookingAmount);
				diffamountArray.push(valu.percentageChangeInBookingAmount);
				dataArray.push(valu.totalCount);
				diffTotalArray.push(valu.percentageChangeInTotalCount);
			});
		} else {
			LabelArray = [ 'WEEK1', 'WEEK2', 'WEEK3', 'WEEK4' ];
			dataamountArray = [ 0, 0, 0, 0 ];
			diffamountArray = [ 0, 0, 0, 0 ];
			dataArray = [ 0, 0, 0, 0 ];
			diffTotalArray = [ 0, 0, 0, 0 ];
		}
		var maxam = 0;
		var maxlimit = 100;
		maxam = dataamountArray.max();
		if (maxam == 0)
			maxlimit = 1000;
		else if (maxam <= 100000)
			maxlimit = 10000;
		else if (maxam <= 500000)
			maxlimit = 50000;
		else if (maxam <= 1000000)
			maxlimit = 100000;
		else if (maxam <= 5000000)
			maxlimit = 500000;
		else if (maxam <= 1000000)
			maxlimit = 1000000;

		var ctx = document.getElementById("chartbarCompareWeek");
		ctx.height = 300;

		var data = {
			labels : LabelArray,
			datasets : [
					{
						label : 'Sales',
						data : dataamountArray,
						borderWidth : 2,
						hoverBackgroundColor : "rgba(55, 160, 225, 0.7)",
						hoverBorderWidth : 2,
						hoverBorderColor : 'lightgrey',
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)' ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)' ]
					}, ]
		};

		var options = {
			maintainAspectRatio : false,
			legend : {
				display : false
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : maxlimit,
						fontSize : 18,
						beginAtZero : true
					}
				} ],
			},
			tooltips : {
				bodyFontSize : 12,
				bodyFontStyle : "bold",
				footerFontSize : 12,
				mode : 'label',
				callbacks : {
					title : function(tooltipItems, data) {
						return data.labels[tooltipItems[0].index];
					},
					label : function(tooltipItem, data) {
						return "Total"
								+ data.datasets[tooltipItem.datasetIndex].label
								+ ": " + numberWithCommas(tooltipItem.yLabel)
								+ " INR";
					},
					footer : function(tooltipItems, data) {
						var dataArrayTooltip = "Segment Count :"
								+ dataArray[tooltipItems[0].index];
						var diffamountArrayTooltip = "Percentage Change in Sales:"
								+ diffamountArray[tooltipItems[0].index] + "%";
						var diffTotalArrayTooltip = "Percent change in segment :"
								+ diffTotalArray[tooltipItems[0].index] + "%";
						return [ diffamountArrayTooltip, dataArrayTooltip,
								diffTotalArrayTooltip ];
					}
				}
			}
		}

		if (myLineChart != null)
			myLineChart.destroy();

		myLineChart = new Chart(ctx, {
			type : 'bar',
			data : data,
			options : options
		});
	};

	function monthcompareBarChart(res) {
		var LabelArray = [];
		var dataArray = [];
		var diffamountArray = [];
		var dataamountArray = [];
		var diffTotalArray = [];
		if (res != undefined) {
			angular.forEach(res, function(valu, key) {
				LabelArray.push(key);
				dataamountArray.push(valu.bookingAmount);
				diffamountArray.push(valu.percentageChangeInBookingAmount);
				dataArray.push(valu.totalCount);
				diffTotalArray.push(valu.percentageChangeInTotalCount);
			});
		} else {
			LabelArray = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
					'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
			dataamountArray = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
			diffamountArray = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
			dataArray = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
			diffTotalArray = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
		}
		var maxam = 0;
		var maxlimit = 100;
		maxam = dataamountArray.max();
		if (maxam == 0)
			maxlimit = 1000;
		else if (maxam <= 100000)
			maxlimit = 10000;
		else if (maxam <= 500000)
			maxlimit = 50000;
		else if (maxam <= 1000000)
			maxlimit = 100000;
		else if (maxam <= 5000000)
			maxlimit = 500000;
		else if (maxam <= 10000000)
			maxlimit = 1000000;
		else if (maxam <= 50000000)
			maxlimit = 5000000;
		else if (maxam <= 10000000)
			maxlimit = 10000000;
		var ctx = document.getElementById("chartbarCompareMonth");
		ctx.height = 300;

		var data = {
			labels : LabelArray,
			datasets : [
					{
						label : 'Sales',
						data : dataamountArray,
						borderWidth : 2,
						hoverBackgroundColor : "rgba(55, 160, 225, 0.7)",
						hoverBorderWidth : 2,
						hoverBorderColor : 'lightgrey',
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)',
								'rgba(55, 160, 225, 0.7)',
								'rgba(155, 206, 86, 0.2)',
								'rgba(225, 192, 192, 0.2)',
								'rgba(53, 252, 255, 0.2)',
								'rgba(55, 160, 225, 0.7)',
								'rgba(153, 252, 255, 0.2)' ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)',
								'rgba(55, 160, 225,1)',
								'rgba(155, 206, 86, 1)',
								'rgba(225, 192, 192, 1)',
								'rgba(53, 252, 255,1)', 'rgba(55, 50, 225,1)',
								'rgba(153, 52, 255,1)' ]
					}, ]
		};

		var options = {
			maintainAspectRatio : false,
			legend : {
				display : false
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : maxlimit,
						fontSize : 18,
						beginAtZero : true
					}
				} ],
			},
			tooltips : {
				bodyFontSize : 12,
				bodyFontStyle : "bold",
				footerFontSize : 12,
				mode : 'label',
				callbacks : {
					title : function(tooltipItems, data) {
						return data.labels[tooltipItems[0].index];
					},
					label : function(tooltipItem, data) {
						return "Total"
								+ data.datasets[tooltipItem.datasetIndex].label
								+ ": " + numberWithCommas(tooltipItem.yLabel)
								+ " INR";
					},
					footer : function(tooltipItems, data) {
						var dataArrayTooltip = "Segment Count :"
								+ dataArray[tooltipItems[0].index];
						var diffamountArrayTooltip = "Percentage Change in Sales:"
								+ diffamountArray[tooltipItems[0].index] + "%";
						var diffTotalArrayTooltip = "Percent change in segment :"
								+ diffTotalArray[tooltipItems[0].index] + "%";
						return [ diffamountArrayTooltip, dataArrayTooltip,
								diffTotalArrayTooltip ];
					}
				}
			}
		}

		if (myLineChart != null)
			myLineChart.destroy();

		myLineChart = new Chart(ctx, {
			type : 'bar',
			data : data,
			options : options
		});
	};

	function yearcompareBarChart(res) {
		var LabelArray = [];
		var dataArray = [];
		var diffamountArray = [];
		var dataamountArray = [];
		var diffTotalArray = [];
		if (res != undefined) {
			angular.forEach(res, function(valu, key) {
				LabelArray.push(key);
				dataamountArray.push(valu.bookingAmount);
				diffamountArray.push(valu.percentageChangeInBookingAmount);
				dataArray.push(valu.totalCount);
				diffTotalArray.push(valu.percentageChangeInTotalCount);
			});
		} else {
			LabelArray = [ 'Year1', 'Year2', 'Year3', 'Year4' ];
			dataamountArray = [ 0, 0, 0, 0 ];
			diffamountArray = [ 0, 0, 0, 0 ];
			dataArray = [ 0, 0, 0, 0 ];
			diffTotalArray = [ 0, 0, 0, 0 ];
		}
		var ctx = document.getElementById("chartbarCompareYear");
		ctx.height = 300;
		var data = {
			labels : LabelArray,
			datasets : [
					{
						label : 'Sales',
						data : dataamountArray,
						borderWidth : 2,
						hoverBackgroundColor : "rgba(55, 160, 225, 0.7)",
						hoverBorderWidth : 2,
						hoverBorderColor : 'lightgrey',
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)', ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)', ]
					}, ]
		};

		var options = {
			maintainAspectRatio : false,
			legend : {
				display : false
			},
			scales : {
				yAxes : [ {
					ticks : {
						fixedStepSize : 1000000,
						fontSize : 18,
						beginAtZero : true
					}
				} ],
			},
			tooltips : {
				bodyFontSize : 12,
				bodyFontStyle : "bold",
				footerFontSize : 12,
				mode : 'label',
				callbacks : {
					title : function(tooltipItems, data) {
						return data.labels[tooltipItems[0].index];
					},
					label : function(tooltipItem, data) {
						return "Total"
								+ data.datasets[tooltipItem.datasetIndex].label
								+ ": " + numberWithCommas(tooltipItem.yLabel)
								+ " INR";
					},
					footer : function(tooltipItems, data) {
						var dataArrayTooltip = "Segment Count :"
								+ dataArray[tooltipItems[0].index];
						var diffamountArrayTooltip = "Percentage Change in Sales:"
								+ diffamountArray[tooltipItems[0].index] + "%";
						var diffTotalArrayTooltip = "Percent change in segment :"
								+ diffTotalArray[tooltipItems[0].index] + "%";
						return [ diffamountArrayTooltip, dataArrayTooltip,
								diffTotalArrayTooltip ];
					}
				}
			}
		}

		if (myLineChart != null)
			myLineChart.destroy();

		myLineChart = new Chart(ctx, {
			type : 'bar',
			data : data,
			options : options
		});
	};
</script>

