<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Register</title>


<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> --%>
<script src="js/flight_city_url.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/alert.css">
<style>
.switch1 {
	width: 130px;
	height: 25px;
	background: #c1c1c1;
	margin: 20px auto;
	position: relative;
	border-radius: 20px;
	box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5), 0px 1px 0px
		rgba(255, 255, 255, 0.2);
}

.switch1 input {
	display: none;
}

.slider1 {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	-webkit-transition: .4s;
	transition: .4s;
	border-bottom: 0px;
}

.slider1:before {
	position: absolute;
	content: "";
	height: 25px;
	width: 60px;
	left: 0px;
	bottom: 0px;
	-webkit-transition: all 0.4s ease;
	transition: all 0.4s ease;
	border-radius: 50px;
	box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.3);
	/* border-radius: 50px;   */
	background: #787878; /* #787878 */
	/* background: -webkit-linear-gradient(top, #fcfff4 0%, #dfe5d7 40%, #b3bead 100%);
  background: linear-gradient(to bottom, #fcfff4 0%, #dfe5d7 40%, #b3bead 100%);  */
	z-index: 999;
}

input:checked+.slider1:before {
	-webkit-transform: translateX(70px);
	-ms-transform: translateX(70px);
	transform: translateX(70px);
	background: green;
}

.switch1:after {
	content: 'OFFLINE';
	color: #000;
	position: absolute;
	right: 10px;
	z-index: 0;
	top: 5px;
	font-size: 13px;
	text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.15);
}

.switch1:before {
	content: 'ONLINE';
	color: green;
	position: absolute;
	left: 10px;
	z-index: 0;
	top: 5px;
	font-size: 13px;
	text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.15);
}

.switch-text {
	position: absolute;
	bottom: 22px;
	left: 23%;
	font-size: 15px;
}
/* .support #dropdown{
	width: 100%;
	background-color: #fff;
	border: 1px solid #ccc;
    border-radius: 0px;
    padding: 5px 2px 5px 10px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
.support #dropdown span{
	color: #555;
}
.support .dropdownwrap .form-group,
.support .dropdownwrap .form-group .input-group{
width: 100%!important;
} */
</style>
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "FlightTravelRequestList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>
<script type="text/javascript">
	function numbersonly(thisObject) {
		var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
		if(!floatRegex.test($(thisObject).val())) {
			$(thisObject).val('');
			$(thisObject).focus();
		}
	}
	function validateDateFormat(thisObject) {
		var dtRegex = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
	    if(!dtRegex.test($(thisObject).val())){
	    	$(thisObject).val('');
			$(thisObject).focus();
	    }
	}
</script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<%-- <section class="content-header">
			<div class="row">
				<div class="col-sm-3 clearfix pull-left">
					<h3>Create Flight Quotation</h3>
				</div>

				<div class="col-sm-9 clearfix pull-right " data-placement="top">
					

						<div class="col-sm-6 clearfix pull-left " data-placement="top">
							<a href="goFlightRequestEdit?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-edit"></i> Travel Request
							</a> <a
								href="flightOrderQuotationView?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-eye"></i> Travel Request
							</a>
							<a
								href="getFlightQuotationList?flightQuotationRequestId=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-list"></i> Quotations
							</a>
						</div>
					<div class="row">
		<div class="col-sm-6 clearfix " data-placement="top">	
		     <a href="goTrips"
					class="btn btn-top-link btn-xs"  >
					 All Trips
				</a>	
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs"  >
					 Flight Trips
				</a>
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  >
					 Hotel Trips
				</a>
				<a href="CarTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Car Trips
				</a>				
				<a href="BusTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Bus Trips
				</a>
				<a href="TrainTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Train Trips
				</a>
				<a href="VisaTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Visa Trips
				</a>
				<a href="InsuranceTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Insurance Trips
				</a>
			</div>		
			<div class="col-sm-3 clearfix pull-right" data-placement="top">
				<div class="myDropdown">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Create Trip
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				    <li><a href="goHotelTravelRequest">Hotel</a></li>
				    <li><a href="goFlightTravelRequest">Flight</a></li>
				    <li><a href="goCarTravelRequest">Car</a></li>
				    <li><a href="goBusTravelRequest">Bus</a></li>
				    <li><a href="goTrainTravelRequest">Train</a></li>
				    <li><a href="goVisaTravelRequest">Visa</a></li>
				      <li><a href="goInsuranceTravelRequest">Insurance</a></li>
				
				   <!--  <li><a href="allexpenses">Expenses</a></li> -->
				  </ul>
				</div>
			</div>
				</div> 
					</div>
				</div>
			</div>
		</section> --%>
		<section class="content-header">
				<div class="row">
					<div class="col-sm-3 clearfix pull-left">
						<h3>Create Flight Quotation</h3>
					</div>
					
											<div class="col-sm-9 clearfix pull-right " data-placement="top">
		<div class="row">
			<div class="col-sm-12 clearfix pull-right " data-placement="top">
				<div class="row">
					<div class="col-sm-10 clearfix " data-placement="top">
						<a href="goTrips" class="btn btn-top-link btn-xs">
							All Trips </a>
						<a href="HotelTravelRequestList" class="btn btn-top-link btn-xs">
							Hotel Trips </a> <a href="FlightTravelRequestList"
							class="btn btn-top-link btn-xs"> Flight Trips </a> <a
							href="CarTravelRequestList" class="btn btn-top-link btn-xs">
							Car Trips </a> <a href="BusTravelRequestList"
							class="btn btn-top-link btn-xs"> Bus Trips </a> <a
							href="TrainTravelRequestList" class="btn btn-top-link btn-xs">
							Train Trips </a> <a href="VisaTravelRequestList"
							class="btn btn-top-link btn-xs"> Visa Trips </a> <a
							href="InsuranceTravelRequestList" class="btn btn-top-link btn-xs">
							Insurance Trips </a>
					</div>
					<div class="col-sm-2 clearfix pull-right" data-placement="top">
						<div class="myDropdown">
							<button class="btn btn-top-link btn-xs dropdown-toggle"
								type="button" data-toggle="dropdown">
								Create Trip <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="goHotelTravelRequest">Hotel</a></li>
								<li><a href="goFlightTravelRequest">Flight</a></li>
								<li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>								
							</ul>
						</div>
					</div>
				</div>
			</div>	  
			</div>	
			<div class="row">
				<div class="col-sm-12">
				</div>
			</div>
		 <div class="row">
		<div class="col-sm-12 clearfix pull-left " data-placement="top">
							<a href="goFlightRequestEdit?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-edit"></i> Travel Request
							</a>
			 <a	href="flightOrderQuotationView?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-eye"></i> Travel Request
							</a>
							<a href="getFlightQuotationList?flightQuotationRequestId=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-list"></i> Quotations
							</a>
		</div>
			</div>		 
			</div> 	
					
					
					
					
					
					
<%-- 					
				  <div class="col-sm-9 clearfix pull-left " data-placement="top">
		<div class="row">
		
							
						
		
		<div class="col-sm-8 clearfix " data-placement="top">
		<div class="col-sm-8 clearfix pull-left" data-placement="top" >
			
			<a href="goFlightRequestEdit?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-edit"></i> Travel Request
							</a>
			 <a	href="flightOrderQuotationView?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-eye"></i> Travel Request
							</a>
							<a href="getFlightQuotationList?flightQuotationRequestId=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-list"></i> Quotations
							</a>
			</div>		  
			<div class="row">
			<div class="col-sm-15 clearfix pull-down"  data-placement="down">
		
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  >
					 Hotel Trips
				</a>
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Flight Trips
				</a>
				<a href="CarTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Car Trips
				</a>
				<a href="BusTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Bus Trips
				</a>
				<a href="TrainTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Train Trips
				</a>
				<a href="VisaTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Visa Trips
				</a>
				<a href="InsuranceTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Insurance Trips
				</a>
				</div>
				</div>
				
			</div>
			
			<div class="col-sm-2 clearfix pull-right" data-placement="top">
				<div class="myDropdown">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Create Trip
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				    <li><a href="goHotelTravelRequest">Hotel</a></li>
				    <li><a href="goFlightTravelRequest">Flight</a></li>
				    <li><a href="goCarTravelRequest">Car</a></li>
				    <li><a href="goBusTravelRequest">Bus</a></li>
				    <li><a href="goTrainTravelRequest">Train</a></li>
				    <li><a href="goVisaTravelRequest">Visa</a></li>
				      <li><a href="goInsuranceTravelRequest">Insurance</a></li>
				
				   <!--  <li><a href="allexpenses">Expenses</a></li> -->
				  </ul>
				</div>
			</div>	
						</div>			
			</div> --%>
				  
				  
				</div>
			</section>
		<!-- Main content -->
		<section class="content">
			<s:if test="hasActionErrors()">
				<div class="succfully-updated clearfix" id="error-alert">
					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>
					<div class="col-sm-10">
						<p>
							<s:actionerror />
						</p>

						<button type="button" id="cancel" class="btn btn-primary">Ok</button>
					</div>
				</div>
			</s:if>
			<s:if test="hasActionMessages()">
				<div class="sccuss-full-updated" id="success-alert">
					<div class="succfully-updated clearfix">
						<div class="col-sm-2">
							<i class="fa fa-check fa-3x"></i>
						</div>
						<div class="col-sm-10">
							<s:actionmessage />
							<button type="button" id="success" class="btn btn-primary">Ok</button>
						</div>
					</div>
				</div>
			</s:if>
			<form action="createFlightRequestTravelQuotation" method="post"
				class="form-horizontal" name="myForm">
				<input id="flightCityUrl" type="hidden"
					value="<s:text name="global.flightCitiesUrl" ></s:text>"> <input
					type="hidden" name="flightQuotationRequestId"
					value="<s:property value="%{flightQuotationRequestId}"/>">
				<input id="tranArrivalDate" type="hidden"
					value="${flightTravelRequest.tranArrivalDate}"> <input
					type="hidden" name="flightRequestQuotationList[0].additionalData"
					id="aditionalSchema">

				<div class="row">
					<div class="col-sm-12 text-center">
						<span class="switch-text"> Choose online or offline
							bookings</span> <label class="switch1"><input type="checkbox"
							name="active" data-id="${id}" class="activeStatus" checked
							<c:if test="${active==true}"></c:if>>
							<div class="slider1"></div> </label>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="support">
						<div class="expand" id="support">
							<!-- level1 -->
							<div class="level1">
								<div id="level1">
									<div class="well">
										<div class="col-sm-6"></div>
										<div class="col-sm-6">
											<a href="#addSchema"
												class="btn btn-success btn-xs pull-right" onclick="mod();"
												data-toggle="modal"> <i class="fa fa-plus-circle"></i>
												Add More
											</a>
										</div>
										<div id="sortable" class="sort">
											<div class="form-group ui-state-default offline-content"
												style="width: 100%" id="1">
												<label class="col-sm-2 control-label">Origin</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="flightRequestQuotationList[0].origin"
														placeholder="Origin" autocomplete="off" required
														id='origin' value="${flightTravelRequest.origin}">
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>
											<div class="form-group ui-state-default offline-content"
												style="width: 100%" id="2">
												<label class="col-sm-2 control-label">Destination</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="flightRequestQuotationList[0].destination"
														placeholder="Destination" autocomplete="off" required
														id='destination'
														value="${flightTravelRequest.destination}">
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-content"
												style="width: 100%" id="3">
												<label class="col-sm-2 control-label">Trip Type</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" required="required"
														name="flightRequestQuotationList[0].tripType" id="tripT">
														<c:if test="${flightTravelRequest.tripType=='O'}">
															<option value="R">Round Trip</option>
															<option value="O" selected="selected">One Way</option>
															<option value="M">Multi Trip</option>
														</c:if>
														<c:if test="${flightTravelRequest.tripType=='R'}">
															<option value="R" selected="selected">Round Trip</option>
															<option value="O">One Way</option>
															<option value="M">Multi Trip</option>
														</c:if>
														<c:if test="${flightTravelRequest.tripType=='M'}">
															<option value="R">Round Trip</option>
															<option value="O">One Way</option>
															<option value="M" selected="selected">Multi Trip</option>
														</c:if>
													</select>
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>


											<div class="form-group ui-state-default offline-content"
												style="width: 100%" id="4">
												<label class="col-sm-2 control-label">Departure Date</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" 
														name="flightRequestQuotationList[0].transDepartureDate"
														placeholder="Departure Date" autocomplete="off" required
														id='departureDate' onchange="validateDateFormat(this)"
														value="${flightTravelRequest.tranDepartureDate}">
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>
											<div class="form-group ui-state-default offline-content"
												style="width: 100%" id="5">
												<label class="col-sm-2 control-label">Arrival Date</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="flightRequestQuotationList[0].transArrivalDate"
														placeholder="Arrival Date" autocomplete="off" required
														id='arrivalDate' onchange="validateDateFormat(this)"
														value="${flightTravelRequest.tranArrivalDate}">
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-content"
												style="width: 100%" id="6">
												<label class="col-sm-2 control-label">Airline</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" required="required"
														name="flightRequestQuotationList[0].airline" id="airline">

														<option value="${flightTravelRequest.airlinePrefer}">${flightTravelRequest.airlinePrefer}</option>
														<s:iterator value="airlineList">
															<option value="<s:property value="airlinename"/>"><s:property
																	value="airlinename" /></option>
														</s:iterator>
													</select>
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>


											<div class="form-group ui-state-default offline-content"
												style="width: 100%" id="7">
												<label class="col-sm-2 control-label">Booking Class</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" required="required"
														id="bookingClassPrefer"
														name="flightRequestQuotationList[0].bookingClassPrefer">
														<c:if
															test="${flightTravelRequest.bookingClassPrefer=='Economy'}">
															<option value="Economy" selected="selected">Economy</option>
															<option value="All">ALL</option>
															<option value="PremiumEconomy">Premium Economy</option>
															<option value="Business">Business</option>
															<option value="PremiumBusiness">Premium Business</option>

														</c:if>

														<c:if
															test="${flightTravelRequest.bookingClassPrefer=='All'}">
															<option value="Economy">Economy</option>
															<option value="All" selected="selected">ALL</option>
															<option value="PremiumEconomy">Premium Economy</option>
															<option value="Business">Business</option>
															<option value="PremiumBusiness">Premium Business</option>

														</c:if>
														<c:if
															test="${flightTravelRequest.bookingClassPrefer=='PremiumEconomy'}">
															<option value="Economy">Economy</option>
															<option value="All">ALL</option>
															<option value="PremiumEconomy" selected="selected">Premium
																Economy</option>
															<option value="Business">Business</option>
															<option value="PremiumBusiness">Premium Business</option>

														</c:if>
														<c:if
															test="${flightTravelRequest.bookingClassPrefer=='Business'}">
															<option value="Economy">Economy</option>
															<option value="All">ALL</option>
															<option value="PremiumEconomy">Premium Economy</option>
															<option value="Business" selected="selected">Business</option>
															<option value="PremiumBusiness">Premium Business</option>

														</c:if>
														<c:if
															test="${flightTravelRequest.bookingClassPrefer=='PremiumBusiness'}">
															<option value="Economy">Economy</option>
															<option value="All">ALL</option>
															<option value="PremiumEconomy">Premium Economy</option>
															<option value="Business">Business</option>
															<option value="PremiumBusiness" selected="selected">Premium
																Business</option>

														</c:if>
													</select>
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-show"
												style="width: 100%" id="8">
												<label class="col-sm-2 control-label">Passenger Count</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm"
														name="flightRequestQuotationList[0].passengerCount"
														placeholder="Passenger Count" 
														value="${flightTravelRequest.passengerCount}"
														autocomplete="off" required>
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-show"
												style="width: 100%" id="9">
												<label class="col-sm-2 control-label">TotalAmount / passenger(${flightTravelRequest.currency})</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" onchange="numbersonly(this)"
														name="flightRequestQuotationList[0].totalAmount"
														placeholder="TotalAmount / passenger" autocomplete="off" required>
												</div>
												<div class="col-sm-1">
													<i class="fa fa-arrows"></i>
												</div>
											</div>




											<!-- for online view for travellers count ends -->

										</div>

										<!-- for online view for travellers count  -->


										<div class="form-group ui-state-default online-content"
											style="display: none; width: 100%">


											<label class="col-sm-2 control-label">Travellers</label>
											<div class="col-sm-8">

												<span class="fa fa-h-iicons"><i
													class="tayyarah-group_add"></i></span> <span
													class="form-control input-sm dropdown"
													data-toggle="dropdown"><span id="totaltraveller">1</span>
													Traveller(s)</span>


												<ul class="dropdown-menu mega-dropdown-menu traveller-menu"
													id="flight-onlinecont">
													<span class="arrow-up"></span>
													<li><a href="#">
															<div class="form-group">
																<label>Adult(s)</label>
																<div class="input-group">
																	<span class="input-group-btn">
																		<button type="button"
																			class="btn btn-default btn-number" data-type="minus"
																			data-field="adultid">
																			<span class="fa fa-minus"></span>
																		</button>
																	</span> <span id="adultid" class="infid input-number" min="1"
																		max="9">1</span> <input type="hidden" name="$A*D"
																		id="hiddenadult" value="1"> <span
																		class="input-group-btn">
																		<button type="button"
																			class="btn btn-default btn-number" data-type="plus"
																			data-field="adultid">
																			<span class="fa fa-plus"></span>
																		</button>
																	</span>
																</div>
															</div>
													</a></li>

													<li><a href="#">
															<div class="form-group">
																<label>Child(s)</label>
																<div class="input-group">
																	<span class="input-group-btn">
																		<button type="button"
																			class="btn btn-default btn-number"
																			disabled="disabled" data-type="minus"
																			data-field="kidid">
																			<span class="fa fa-minus"></span>
																		</button>
																	</span> <span id="kidid" class="infid input-number" min="0"
																		max="9">0</span> <input type="hidden" name="$K*D"
																		id="hiddenkid" value="0"> <span
																		class="input-group-btn">
																		<button type="button"
																			class="btn btn-default btn-number" data-type="plus"
																			data-field="kidid">
																			<span class="fa fa-plus"></span>
																		</button>
																	</span>
																</div>
															</div>
													</a></li>

													<li><a href="#"><div class="form-group">
																<label>Infant(s)</label>
																<div class="input-group">
																	<span class="input-group-btn">
																		<button type="button"
																			class="btn btn-default btn-number"
																			disabled="disabled" data-type="minus"
																			data-field="infantid">
																			<span class="fa fa-minus"></span>
																		</button>
																	</span><span class="infid input-number" id="infantid" min="0"
																		max="1">0</span> <input type="hidden" name="$I*T"
																		id="hiddeninfant" value="0"> <span
																		class="input-group-btn">
																		<button type="button"
																			class="btn btn-default btn-number" data-type="plus"
																			data-field="infantid">
																			<span class="fa fa-plus"></span>
																		</button>
																	</span>
																</div>
															</div></a></li>

													<li class="dorp-done">
														<div class="form-group">
															<button type="button" class="btn btn-info" id="but-up1">Done</button>
														</div>

													</li>

												</ul>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="offline-content" id="offline-room-view"
							style="display: none"></div>

						<div class="online-content" id="online-room-view"
							style="display: none"></div>
					</div>
					<!--  support -->

				</div>
				<div class="form-group text-center">

					<div class="col-xs-12 submitWrap text-center">

						<!-- <button type="submit" class="btn btn-primary btn-lg"
							onclick="lload()">Create Offline Quotation</button> -->
						<button type="submit" class="btn btn-primary btn-m offline-show"
							onclick="lload()">Create Offline Quotation</button>
						<button type="button" class="btn btn-primary btn-m online-content"
							style="display: none" onclick="onlineSubmit()">Create
							Online Quotation</button>
					</div>
				</div>


				<div class="modal fade" id="addSchema" tabindex="-1" role="dialog"
					aria-labelledby="addSchema" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true" onclick="clearQuotationSchema()">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button>
								<h4 class="modal-title custom_align" id="Heading">Hotel
									Quotation Schema Creation</h4>
							</div>
							<div class="modal-body">
								<div class="form-group" style="width: 100%">
									<label class="col-sm-3 control-label">Field Name</label>
									<div class="col-sm-7">
										<input type="text" class="form-control input-sm"
											name="fieldName" id="fieldName" placeholder="Field Name"
											autocomplete="off">
									</div>
								</div>
								<div class="form-group" style="width: 100%">
									<label class="col-sm-3 control-label">Input Type</label>
									<div class="col-sm-7">
										<select class="form-control input-sm" id="dataType"
											name="dataType">
											<option value="text">Text</option>
											<option value="longText">LongText</option>
											<option value="number">Number</option>
											<option value="text">Decimal</option>
										</select>
									</div>
								</div>

								<div class="form-group" style="width: 100%">
									<!-- <label class="col-sm-3 control-label">Fixed Position</label> -->
									<div class="col-sm-7">
										<input type="hidden" data-type="dynamic" id="fixedPosition">

									</div>
								</div>
							</div>
							<div class="modal-footer ">
								<button type="button" class="btn btn-success" id="yes"
									onclick="generateQuotationSchema()">
									<span class="glyphicon glyphicon-ok-sign"></span> Create Schema
								</button>
								<button type="button" class="btn btn-default"
									onclick="clearQuotationSchema()" data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> Cancel
								</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
			</form>
			<div class="hotel-create-online">
				<div class="submitWrap text-center">					
					<form method="get" action="<s:text name="global.ibeurl"></s:text>" target="_blank" class="form-horizontal"
						name="onlineForm" id="onlineForm">
						<input type="hidden" name="ori" id="ori"> <input
							type="hidden" name="des" id="des"> <input
							type="hidden" name="triptype" id="trip"> <input
							type="hidden" name="depDate" id="dep">
						<c:if
							test="${flightTravelRequest.tripType=='R' || flightTravelRequest.tripType=='M' }">
							<input type="hidden" name="arvlDate" id="arr">
						</c:if>
						<input type="hidden" name="Airlinecode" id="air"> 
						<input type="hidden" name="cabinClass" id="class"> 
						<input type="hidden" id="totaladult" name="adult" value="1"> 
						<input type="hidden" id="totalkid" name="kid" value="0">
						 <input type="hidden" id="totalinfant" name="infant" value="0">
                       <input type="hidden" name="flightQuotationRequestId"  value="<s:property value="%{flightQuotationRequestId}"/>">
                      <input type="hidden" name="dammytext"  value="<s:property value="%{#session.Encryptedid}"/>">
                       <input type="hidden" id="companyId"    name="companyId" value="<s:property value="%{#session.User.Companyid}"/>">  
                     <input type="hidden" id="userId"    name="userId" value="<s:property value="%{#session.User.id}"/>"> 
                       <input type="hidden" name="myaction" value="onewayQuoteSearch">
                       <input type="hidden" name="request_locale" value="en"> 
                       <input type="hidden" name="isDymark" value="false"> 
                       <input type="hidden" name="marAt" value="0"> 
					</form>
				</div>
			</div>
		</section>
	</div>
	<%@ include file="DashboardFooter.jsp"%>
	<script>
	 function onlineSubmit(){
		  var  tripType= $("#tripT").val();
		  $("#ori").val($("#origin").val());
		  $("#des").val($("#destination").val());
		  $("#trip").val($("#tripT").val());
		  $("#dep").val($("#departureDate").val());
		  if(tripType=='R' || tripType=='M'){
			  var scntDiv = $('#p_scents');
			     createinput();
			     function createinput() {

			                 $('<input  id="arr" type="hidden" name="arvlDate"/>').appendTo(scntDiv);            
			                 return false;
			     }
			     
			     $("#arr").val($("#arrivalDate").val());
		  }
			  
		  
		  $("#class").val($("#bookingClassPrefer").val());
		  $("#air").val($("#airline").val());
		  $("#totaladult").val($("#hiddenadult").val());
		  $("#totalkid").val($("#hiddenkid").val());
		  $("#totalinfant").val($("#hiddeninfant").val());
		  
		  /* console.log("hiddenadult  count-----------",$("#hiddenadult").val());
		   console.log("hiddenkid  count-----------",$("#hiddenkid").val());
		   console.log("hiddeninfant  count-----------",$("#hiddeninfant").val());
		   console.log("Origin  -----------",$("#origin").val());
		   console.log("Destination  -----------",$("#destination").val());
		   console.log("tripType  -----------",$("#tripT").val());
		   console.log("Departure Date  -----------",$("#departureDate").val());
		   if($("#tripT").val()=='R' || $("#tripT").val()=='M')
			   console.log("ArrivalDate Date  -----------",$("#arrivalDate").val());
		   console.log("airline  -----------",$("#airline").val());
		   console.log("bookingClassPrefer class  -----------",$("#bookingClassPrefer").val());
		    */
			 $("#onlineForm").submit(); 
	 }
	
	
	 
	
		function addlist() {
			var noOfQuation = $("#level1").find('.well').length;
			var addQuatation = "";
			$('.package').html("");
			for (var i = 0; i < parseInt(noOfQuation); i++) {
				var index = i + 1;
				var qPageNo = i + 2;
				addQuatation += "<div class='well' id='id"+index+"'><p class='h4'>Option:"
						+ qPageNo
						+ "</p><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>HotelName</label><div class='col-sm-8'><input type='text' class='form-control input-sm' name='hotelQuotationList["+index+"].hotelName' placeholder='hotelName' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Hotel Category</label><div class='col-sm-8'><input type='text' class='form-control input-sm' name='hotelQuotationList["+index+"].hotelCategory' placeholder='Hotel Category' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Hotel Address</label><div class='col-sm-8'><textarea rows='2' cols='2' class='form-control input-sm' name='hotelQuotationList["+index+"].hotelAddress' placeholder='Hotel Address' required></textarea></div></div><div class='form-group' style='width: 100%''><label class='col-sm-2 control-label'>Hotel City</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].hotelCity'placeholder='Hotel City' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Hotel Country</label><div class='col-sm-8'><select class='form-control input-sm' required='required' name='hotelQuotationList["+index+"].hotelCountry'><option value='' selected='selected'>Select Country</option> <s:iterator value='countryList'> <option value='<s:property value='c_name'/>'><s:property value='c_name'/></option> </s:iterator> </select> </div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>ProjectAddress</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].projectAddress'placeholder='Project Address' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Distance from Work</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].distanceFromWork'placeholder='Distance From Work' autocomplete='off'required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Room Type</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].roomType'placeholder='Room Type' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>CheckInDate</label><div class='col-sm-8'><input type='text' id='checkIn"+index+"' class='form-control input-sm datepicker_recurring_start' name='hotelQuotationList["+index+"].checkIn' placeholder='Check In Date' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>CheckInTime</label><div class='col-sm-8'><input type='text' class='form-control input-sm' name='hotelQuotationList["+index+"].checkInTime' placeholder='CheckIn Time' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>CheckOutDate</label><div class='col-sm-8'><input type='text' id='checkOut"+index+"'class='form-control input-sm datepicker_recurring_start' name='hotelQuotationList["+index+"].checkOut' placeholder='Check Out Date' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>CheckOutTime</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].checkOutTime'placeholder='CheckOut Time' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Room Count</label><div class='col-sm-8'><input type='number' class='form-control input-sm'name='hotelQuotationList["+index+"].roomCount'placeholder='Room Count' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Adult Count</label><div class='col-sm-8'><input type='number' class='form-control input-sm'name='hotelQuotationList["+index+"].adultCount'placeholder='Adult Count' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Child Count</label><div class='col-sm-8'><input type='number' class='form-control input-sm'name='hotelQuotationList["+index+"].childCount'placeholder='Child Count' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>RoomRate/Night</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].roomRatePerNight'placeholder='Room Rate Per Night' autocomplete='off'required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Payment Option(s)</label><div class='col-sm-8'><select class='form-control input-sm'name='hotelQuotationList["+index+"].availablePaymentOptionList'multiple='multiple' required><option value=''>Please Select PaymentOption(s)</option><option value='PrePaid'>PrePaid</option><option value='PostPaid'>PostPaid</option><option value='PayAtHotel'>PayAtHotel</option></select></div></div> <div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Taxes</label><div class='col-sm-8'> <select class='form-control input-sm' name='hotelQuotationList["+index+"].taxes'><option value='yes'>YES</option><option value='no'>NO</option></select></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Breakfast</label><div class='col-sm-8'><select class='form-control input-sm'name='hotelQuotationList["+index+"].breakfast'><option selected='selected' value='yes'>YES</option><option value='no'>NO</option></select></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Internet</label><div class='col-sm-8'><select class='form-control input-sm'name='hotelQuotationList["+index+"].internet'><option selected='selected' value='yes'>YES</option><option value='no'>NO</option></select></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Cancellation Policy</label><div class='col-sm-8'><textarea rows='2' cols='2' class='form-control input-sm'name='hotelQuotationList["+index+"].cancellationPolicy'placeholder='Cancellation Policy' required></textarea></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>PreferProperty</label><div class='col-sm-8'><select class='form-control input-sm'name='hotelQuotationList["+index+"].preferProperty'><option selected='selected' value='true'>YES</option><option value='false'>NO</option></select></div></div></div>";
			}
			$('.package').html(addQuatation);
			if ((noOfQuation) > 0) {
				$('.remove_field').removeAttr('disabled');
			} else {
				$('.remove_field').attr('disabled', 'disabled');
			}
		}
		function remove_field() {
			var noOfQuation = $("#level1").find('.well').length;
			$('.well:last-child').remove();
			if (noOfQuation <= 1) {
				$('.remove_field').attr('disabled', 'disabled');
			} else {
				$('.remove_field').removeAttr('disabled');
			}
			noOfQuation--;
		}

		function clearQuotationSchema() {
			$("#fieldName").val("");
			$("#positionNumber").val("");
		}
	</script>

	<script>
		function lload() {
			var schemaStruct = "<#proertyName(|)type(|)value(|)posNum(|)pos(|)displayName#>"
			var aditionalSchema = "";
			$('#sortable div.form-group')
					.each(
							function() {
								var id = this.id;
								// dynamic data field 
								var fieldName = $("#fieldName").val();
								var dataType = $("#dataType").val();
								var positionNumber = $("#positionNumber").val();
								var fixedPosition = $("#fixedPosition").val();
								var displayName = $('#' + id + " " + 'label')
										.text();
								var type = "";
								var pos = "fixed";
								var input = $('#' + id + " " + 'input').attr(
										'name');
								var select = $('#' + id + " " + 'select').attr(
										'name');
								var textArea = $('#' + id + " " + 'textarea')
										.attr('name');
								var part = "";
								var value = "";
								//console.log(input);
								//console.log(select);
								//console.log(textArea);
								if (input != null && input != "undefined") {
									if (input.indexOf(".") >= 1) {
										var splitName = input.split(".");
										part = splitName[1];
										pos = "fixed";
									} else {
										part = input;
										pos = "dynamic";
									}
									value = $('#' + id + " " + 'input').val();
									type = $('#' + id + " " + 'input').attr(
											'type');

								}
								if (select != null && select != "undefined") {
									if (select.indexOf(".") >= 1) {
										var splitName = select.split(".");
										part = splitName[1];
										pos = "fixed";
									} else {
										pos = "dynamic";
										part = select;
									}
									value = $('#' + id + " " + 'select').val();
									type = "select";

								}
								if (textArea != null && textArea != "undefined") {
									if (textArea.indexOf(".")) {
										var splitName = textArea.split(".");
										part = splitName[1];
										pos = "fixed";
									} else {
										pos = "dynamic";
										part = textArea;
									}
									type = "textarea";
									value = $('#' + id + " " + 'textarea')
											.val();

								}
								 
								var schemaStructTemp = schemaStruct;
								schemaStructTemp = schemaStructTemp.replace(
										"proertyName", part);
								schemaStructTemp = schemaStructTemp.replace(
										"type", type);
								schemaStructTemp = schemaStructTemp.replace(
										"value", value);
								schemaStructTemp = schemaStructTemp.replace(
										"posNum", id);
								schemaStructTemp = schemaStructTemp.replace(
										"pos", pos);
								schemaStructTemp = schemaStructTemp.replace(
										"displayName", displayName);
								aditionalSchema += schemaStructTemp;
							});
			console.debug(aditionalSchema);
			$("#aditionalSchema").val(aditionalSchema);

		}

		$('#yes').click(function() {

			if (($('#fieldName').val() != '') && ($('#dataType').val() != '')) {
				$('#addSchema').modal('hide');

			}
		});
	</script>


	<script>
		function mod() {
			var y = $('#sortable div.form-group').length;
			return y;
		}
		function generateQuotationSchema() {
			var z = mod();
			var fieldName = $("#fieldName").val();
			var dataType = $("#dataType").val();
			var positionNumber = $("#positionNumber").val();
			var fixedPosition = $("#fixedPosition").val();
			var addSchema = null;
			var checkSchema = $("#setSchema").val();
			var x = 1;
			j = z + 1;
			var max_fields = 10; //maximum input boxes allowed
			var wrapper = $("#sortable"); //Fields wrapper
			if (fieldName != '') {
				if (x < max_fields) {
					x++;
					$(wrapper)
							.append(
									'<div class="form-group ui-state-default" style="width: 100%" id="'
											+ j
											+ '"><label class="col-sm-2 control-label">'
											+ fieldName
											+ '</label><div class="col-sm-8"><input type="'+dataType+'" class="form-control input-sm"  name="'+fieldName+'"    placeholder="'+fieldName+'" autocomplete="off" required></div><div class="col-sm-2 remove_field">Remove  <i class="fa fa-arrows"></i></div></div>');
					$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text
						e.preventDefault();
						$(this).parent('div').remove();
						x--;
					})
				}
			}
			lload();
		}
	</script>
	<script>
		$(document).ready(function() {
			$(function() {
				$("#departureDate").datepicker({
					dateFormat : "dd-mm-yy",
					//minDate : 0,

				});
				$("#arrivalDate").datepicker({
					dateFormat : "dd-mm-yy",
					//minDate : 0,

				});
				
			});
		});
		$(function () {
			  $("#tripT").change(function() {
			    var val = $(this).val();
			    var arrivalDate=$("#tranArrivalDate").val();
			   
			    if(val === "R" || val === "M") {
			        //$("#arrivalDate").prop('disabled', false);
			        $("#arrivalDate").val(arrivalDate);
			       
			    }
			    
			   
			  });
			  
			  if($("#tripT").val() === "R" || $("#tripT").val() === "M") {
				  var arrivalDate=$("#tranArrivalDate").val();
			        $("#arrivalDate").val(arrivalDate);
			       // $("#arrivalDate").prop('disabled', false);
			    }
			  
			   
			  
			});
	</script>

	<script>
		$('#sortable').sortable({
		start : function(e, ui) { 
		$("#sortable div.form-group").each(function(i, elm) {
		$elm = $(elm); // cache the jquery object
		$elm.attr("id",$elm.index("#sortable div.form-group") + 1);
		});},
		update : function(event, ui) {
			 $("#sortable div.form-group").each(function(i, elm) {
				 $elm = $(elm); // cache the jquery object
				 $elm .attr("id",$elm.index("#sortable div.form-group") + 1);
				 });
				 }
		 });
	</script>
	<script>
	$( document ).ready(function() {
		$("#offline-room-view").appendTo("#room-view");
		$("#offline-room-view").show();
		if($('.activeStatus').is(":checked")==false){ 
			 $(this).attr('checked', false);
			   $(this).attr('value', false);
			   $("#bookingMode").attr('value', "offline");
			   $(".online-content").hide();
			   $(".offline-content").show();
			   $(".offline-show").show();
			   }else{
				   $(this).attr('checked', true);
				   $(this).attr('value', true);
				   $("#bookingMode").attr('value', "online");
				   $(".online-content").show(); 
				   $(".offline-show").hide();
				   
			   }
	});
	
$('.activeStatus').change(function (){
	 var status = false;
	 var id = $(this).data("id");
	 if($(this).is(":checked")==false)
	  {
	   status= false;
	   $(this).attr('checked', false);
	   $(this).attr('value', false);
	   $("#bookingMode").attr('value', "offline");
	   $(".online-content").hide();
	   $(".offline-content").show();
	   $(".offline-show").show();
	   $("#offline-room-view").appendTo("#room-view");
	   }
	 else{
	   status= true;
	   $(this).attr('checked', true);
	   $(this).attr('value', true);
	   $("#bookingMode").attr('value', "online");
	   $(".online-content").show();
	   //$(".offline-content").hide();
	   $(".offline-show").hide();
	   $("#online-room-view").appendTo("#room-view");
	  }
	 
	 /* alert(status); */
});

</script>
</body>

</html>