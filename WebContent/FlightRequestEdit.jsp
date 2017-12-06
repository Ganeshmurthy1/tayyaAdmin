
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<script src="js/flight_city_url.js" type="text/javascript"></script>

<link rel="stylesheet" href="css/alert.css">


<style type="text/css">
.error option{
color: #000;
}
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	/* add padding to account for vertical scrollbar */
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}
</style>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>


<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
				<div class="col-sm-3 clearfix pull-left">
					<h4 style="margin: 0px;"><b><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Flight Request Edit</b></h4>
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
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>									
							</ul>
						</div>
					</div>
				</div>
			</div>	  
			</div>	 
			</div>
			</div>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>

				</h4>
			</div>

			<!-- Small boxes (Stat box) -->
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
			<div class="clearfix"></div>
			<div class="clearfix">
				<form action="updateFlightTravelRequest" method="post"
					class="form-horizontal" name="myForm" id="myfform">
					<input id="flightCityUrl" type="hidden"
							value="<s:text name="global.flightCitiesUrl" ></s:text>">
						<input id="tripId" type="hidden" name="tripId" value="${tripId}">
						<input id="id"  name="id" type="hidden" value="${flightTravelRequest.id}">
						<input id="tranArrivalDate" type="hidden" name="tranArrivalDate" value="${flightTravelRequest.tranArrivalDate}">
					
					<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
												<i class="fa fa-user fa-2x" aria-hidden="true"></i> 
														 <b>Customer Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix"> 
						 
						
						<div class="col-sm-2">
							<div class="form-group">
								<label for="customerNo">Employee Code</label> <input
									type="text" autocomplete="off" name="customerNo"
									class="form-control input-sm"
									value="${flightTravelRequest.customerNo}" required="required"
									placeholder="Customer No">

							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="hotelName">Title</label> <select
									class="form-control input-sm" required="required" name="title">
									<c:if test="${flightTravelRequest.title=='Mr'}">
													<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>

									</c:if>
									<c:if test="${flightTravelRequest.title=='Mrs'}">
										<option value="Mr" >Mr</option>
													<option value="Mrs" selected="selected">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>

									</c:if>
									<c:if test="${flightTravelRequest.title=='Miss'}">
										<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss" selected="selected">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
									</c:if>
									<c:if test="${flightTravelRequest.title=='Ms'}">
										<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms" selected="selected">Ms</option>
													<option value="Master">Master</option>
									</c:if>
									<c:if test="${flightTravelRequest.title=='Master'}">
													<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master" selected="selected">Master</option>
									</c:if>


								</select>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="customerName">First Name </label> <input
									type="text" autocomplete="off" name="firstName"
									class="form-control input-sm"
									value="${flightTravelRequest.firstName}" required="required"
									placeholder="First Name">
							</div>
						</div>

						<div class="col-sm-2">

							<div class="form-group">
								<label for="customerName">Last Name </label> <input type="text"
									autocomplete="off" name="lastName"
									class="form-control input-sm" required="required"
									placeholder="Last Name" value="${flightTravelRequest.lastName}">


							</div>
						</div>
						<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
						<div class="col-sm-2">
							<div class="form-group">
								<label for="travelRequestNumber">Travel Request Number</label>

								<input type="number" autocomplete="off"
									name="travelRequestNumber" class="form-control input-sm"
									required="required" placeholder="Travel Request Number"
									value="${flightTravelRequest.travelRequestNumber}">
							</div>
						</div>
						</s:if>
						<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
						
						<div class="col-sm-2">
							<div class="form-group">
								<label for="costCenter">Cost Center</label> <input type="text"
									autocomplete="off" name="costCenter"
									class="form-control input-sm" required="required"
									placeholder="Cost Center"
									value="${flightTravelRequest.costCenter}">
							</div>
						</div>
						</s:if>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="companyEntity">Company Entity </label> <input
									type="text" autocomplete="off" name="companyEntity"
									class="form-control input-sm" required="required"
									placeholder="Company Entity"
									value="${flightTravelRequest.companyEntity}">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="source">Source </label> <input type="text"
									autocomplete="off" name="source" class="form-control input-sm"
									required="required" placeholder="Source"
									value="${flightTravelRequest.source}">
							</div>
						</div>

						<div class="col-sm-2">
							<div class="form-group">
								<label for="projectName">Project Name </label> <input
									type="text" autocomplete="off" name="projectName"
									class="form-control input-sm" required="required"
									placeholder="Project Name"
									value="${flightTravelRequest.projectName}">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="projectSubTaskDetails">Project SubTask
									Details </label> <input type="text" autocomplete="off"
									name="projectSubTaskDetails" class="form-control input-sm"
									required="required" placeholder="Project SubTask Details"
									value="${flightTravelRequest.projectSubTaskDetails}">
							</div>
						</div>                    
                     
								<c:if test="${fieldName[0]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[0]}</label> <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField1}"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField1}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[1]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[1]}</label> <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField2}"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField2}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[2]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[2]}</label> <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField3}"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField3}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[3]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[3]}</label> <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField4}"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField4}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[4]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[4]}</label> <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField5}"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField5}" >
									</div>
								</div>
							</c:if>
						
							<c:if test="${rmConfigModel.department}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> Department</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.department"
											class="form-control input-sm" required="required"
											placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.department"/>" >
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.approverName}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode">Approver Name</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.approverName"
											class="form-control input-sm" required="required"
											placeholder="Enter approverName Details" value="${configTripDetailsModel.approverName}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.location}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode">location</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.location"
											class="form-control input-sm" required="required"
											placeholder="Enter location Details" value="${configTripDetailsModel.location}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.bussinessUnit}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode">Bussiness Unit</label> <input type="text"
											autocomplete="off"
											name="configTripDetailsModel.bussinessUnit"
											class="form-control input-sm" required="required"
											placeholder="Enter bussiness Unit Details" value="${configTripDetailsModel.bussinessUnit}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.projectCode}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="projectCode"> Project Code</label> <input
											type="text" autocomplete="off"
											name="configTripDetailsModel.projectCode"
											class="form-control input-sm" required="required"
											placeholder="Enter projectCode Details" value="${configTripDetailsModel.projectCode}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.reasonForTravel}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="reasonForTravel">Reason For Travel</label> <input
											type="text" autocomplete="off"
											name="configTripDetailsModel.reasonForTravel"
											class="form-control input-sm" required="required"
											placeholder="Enter reasonForTravel Details" value="${configTripDetailsModel.reasonForTravel}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.billNonBill}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="billNonBill">Billing/Non Billing</label> <input
											type="text" autocomplete="off"
											name="configTripDetailsModel.billNonBill"
											class="form-control input-sm" required="required"
											placeholder="Enter billNonBill Details" value="${configTripDetailsModel.billNonBill}">
									</div>
								</div>
							</c:if>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="customerComments">Customer Comments</label>

								<textarea rows="1" cols="12" name="customerComments"
									class="form-control input-sm" required="required"
									placeholder="Customer Comments">${flightTravelRequest.customerComments}</textarea>

							</div>
						</div> 		 
											</div>
											</div>
					


					<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon">
												<i class="fa fa-shopping-bag fa-2x" aria-hidden="true"></i> 
														 <b>Trip Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix">  
						<div class="col-sm-2">
							<div class="form-group">
								<label for="tripType">Trip Type</label> <select
									name="tripType" class="form-control input-sm" id="tripT" required>
									<c:if test="${flightTravelRequest.tripType=='O'}">
										<option value="O" selected="selected">One Way</option>
										<option value="R">Round Trip</option> 
									</c:if>
									<c:if test="${flightTravelRequest.tripType=='R'}">
										<option value="O">One Way</option>
										<option value="R" selected="selected">Round Trip</option>
									<!-- 	<option value="M">Multi Trip</option> -->

									</c:if>
									<c:if test="${flightTravelRequest.tripType=='M'}">
										<option value="O">One Way</option>
										<option value="R">Round Trip</option>
										<option value="M" selected="selected">Multi Trip</option>
									</c:if> 
								</select>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="country">Origin </label> <input type="text"
									autocomplete="on" id="origin" name="origin"
									class="form-control input-sm" required="required"
									placeholder="Origin" value="${flightTravelRequest.origin}">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="checkOutDate">Destination </label> <input
									type="text" autocomplete="on" id="destination"
									name="destination" class="form-control input-sm"
									required="required" placeholder="Destination"
									value="${flightTravelRequest.destination}">

							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="requestDate">Departure Date </label> <input
									type="text" autocomplete="off" name="tranDepartureDate"
									id="requestDate" class="form-control input-sm"
									required="required" placeholder="Departure Date"
									value="${flightTravelRequest.tranDepartureDate}">
							</div>
						</div>

						<div class="col-sm-4" id="request_return">
							<div class="form-group">
								<label for="requestDate">Arrival Date</label> <input
									type="text" autocomplete="off" name="tranArrivalDate"
									id="returnDate" class="form-control input-sm"
									 placeholder="Arrival Date"
									value="${flightTravelRequest.tranArrivalDate}">
							</div>
						</div>


						<div class="col-sm-2">
							<div class="form-group">
								<label for="bookingClassPrefer">Booking Class</label> <select
									name="bookingClassPrefer" class="form-control input-sm" required>
									<c:if test="${flightTravelRequest.bookingClassPrefer=='Economy'}">
										<option value="Economy" selected="selected">Economy</option>
										<option value="All">ALL</option>
										<option value="PremiumEconomy">Premium Economy</option>
										<option value="Business">Business</option>
										<option value="PremiumBusiness">Premium Business</option>

									</c:if>

									<c:if test="${flightTravelRequest.bookingClassPrefer=='All'}">
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
						</div>


						<div class="col-sm-2">
							<div class="form-group">
								<label for="currency">Currency </label> 
								<select
									class="form-control input-sm" required="required"
									name="currency">
									<option value="<s:property value="flightTravelRequest.currency"/>">
									<s:property value="flightTravelRequest.currency" /></option>
										<s:iterator value="countryList">
											<option value="<s:property value="cur_code"/>"><s:property
													value="cur_code"></s:property></option>
										</s:iterator>
							
								</select>
							</div>
						</div>





						<div class="col-sm-2">
							<div class="form-group">
								<label for="passengerCount">Passenger Count</label> <input
									type="text" autocomplete="off" name="passengerCount"
									class="form-control input-sm" required="required"
									placeholder="Passenger Count"
									value="${flightTravelRequest.passengerCount}">
							</div>
						</div>


						<div class="col-sm-2">
							<div class="form-group">
								<label for="airlinePrefer">Airline</label> 
								<select
									class="form-control input-sm" name="airlinePrefer" required>
									<option value="<s:property value="flightTravelRequest.airlinePrefer"/>">
									<s:property value="flightTravelRequest.airlinePrefer" /></option>
										<s:iterator value="airlineList">
											<option value="<s:property value="airlinename"/>"><s:property
												value="airlinename" /></option>
										</s:iterator>
							
								</select>
								 	 
							</div>
						</div> 
									
											</div>
											</div>




					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<!-- <button type="submit" class="btn btn-primary btn-lg">Update</button> -->
							<button id="reqSubmit" type="button" class="btn btn-primary btn-lg">Update</button>
						</div>
					</div>
				</form>



			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->

	</div>
	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	<script>
	
	$( "#requestDate" ).datepicker({
	    changeMonth: true,  
	    changeYear:true,
	    dateFormat: "dd-mm-yy",
	    minDate:0,
	    onSelect: function( selectedDate ) {
	    	var date2 = $("#requestDate").datepicker('getDate', '+1d'); 
	  	  date2.setDate(date2.getDate()+1); 
	  	  $( "#returnDate" ).datepicker('setDate', date2);
	  	$( "#returnDate" ).datepicker( "option", "minDate", date2 ); 
	  	 $(this).valid();
	    },
	
	 onClose: function() {
         $("#returnDate").focus();
     }
	  });
	  $( "#returnDate" ).datepicker({      
	    changeMonth: true,   
	    changeYear:true,
	    dateFormat: "dd-mm-yy",
	    minDate:1,
	    onSelect: function(selected) { 
	    	           $("#requestDate").datepicker("option", selected);
	    	           $(this).valid();
	    	        }

	   
	  });  
	
			
			$(function () {
				 $("#request_return").hide();
				  $("#tripT").change(function() {
				    var val = $(this).val();
				    var arrivalDate=$("#tranArrivalDate").val();
				   
				    if(val === "R" || val === "M") {
				        $("#request_return").show();
				        $("#returnDate").prop('disabled', false);
				        $("#returnDate").val(arrivalDate);
				       
				    }
				    else{ 
				    	$("#returnDate").prop('disabled', true);
				        $("#request_return").hide();
				        $("#returnDate").val("");
				    }
				   
				  });
				  
				  if($("#tripT").val() === "R" || $("#tripT").val() === "M") {
					  var arrivalDate=$("#tranArrivalDate").val();
				        $("#request_return").show();
				        $("#returnDate").val(arrivalDate);
				        $("#returnDate").prop('disabled', false);
				    }
				  
				});
			
		
  
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
		$(document).ready(function(){
			  
			   $('#reqSubmit').click(function(){
				   $("#myfform").valid();
				   if($("#myfform").valid()){
					   document.getElementById("myfform").submit();
				   }
			   });
			 		   
			   $("#myfform").validate({
				   submitHandler: function (form) {  
			            
			            return false;
			        },
			        highlight: function(element, errorClass, validClass) { 
			            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
			            $(element).addClass(errorClass).removeClass(validClass);
			            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			          },
			          success: function(element) {
			            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
			         element.closest('.form-group').removeClass('has-error').addClass('has-success');
			            $(element).remove();
			          }
			   })
		});
	</script>





</body>
</html>