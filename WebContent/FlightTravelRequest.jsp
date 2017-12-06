
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Travel Request</title>

<script src="js/flight_city_url.js" type="text/javascript"></script>




<link rel="stylesheet" href="css/alert.css">


<style type="text/css">
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
<script type="text/javascript">
	function validateDateFormat(thisObject) {
		var dtRegex = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
		if (!dtRegex.test($(thisObject).val())) {
			$(thisObject).val('');
			$(thisObject).focus();
		}
	}
</script>
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>


<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
			<div class="col-sm-2 clearfix pull-left">
				<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-plane"></i> Flight Travel Request</h3>
			</div>
		<div class="col-sm-10 clearfix pull-right " data-placement="top">	
			<div class="row">
			<div class="col-sm-12 clearfix pull-right " data-placement="top">
				<div class="row">
					<div class="col-sm-8 clearfix " data-placement="top">
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
							<a href="MiscellaneousTravelRequestList"
									class="btn btn-top-link btn-xs" >
									 Miscellaneous Trips
								</a>
					</div>
					<div class="col-sm-2 clearfix  " data-placement="top">
						<div class="myDropdown">
							<button class="btn btn-top-link btn-xs dropdown-toggle"
								type="button" data-toggle="dropdown">
								Create Quick Trip <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="goQuickLinkOfflibeHotelBookingPage">Hotel</a></li>
								<li><a href="goQuickLinkOfflibeFlightBookingPage">Flight</a></li>
								<!-- <li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>	
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>	 -->					
							</ul>
						</div>
					</div>
					<div class="col-sm-1 clearfix  " data-placement="top">
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

				<form action="createFlightTravelRequest " method="post"
					class="form-horizontal clearfix" name="myForm" id="myfform">
					
					<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
												<i class="fa fa-money fa-2x" aria-hidden="true"></i> 
														 <b>Customer Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix"> 
																		 
						<input id="flightCityUrl" type="hidden"
							value="<s:text name="global.flightCitiesUrl" ></s:text>">
						<input id="tripId" name="tripId" type="hidden" value="${tripId}">
						<%-- <s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.empCode || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="customerNo" class="control-label">Employee Code</label> <input
										type="text" autocomplete="off" name="customerNo"
										class="form-control input-sm" required="required"
										placeholder="Customer No ">

								</div>
							</div>
						</s:if> --%>
					 
						<div class="col-sm-2">
							<input type="hidden" name="cityCode" id="citycode">
							<div class="form-group">
								<label for="hotelName" class="control-label">Title</label> <select
									class="form-control input-sm" required="required" name="title">
									<option value="Mr" selected="selected">Mr</option>
									<option value="Mrs">Mrs</option>
									<option value="Miss">Miss</option>
									<option value="Ms">Ms</option>
									<option value="Master">Master</option>
								</select>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="customerName" class="control-label">First Name </label> <input type="text"
									autocomplete="off" name="firstName"
									class="form-control input-sm" required="required"
									placeholder="First Name" maxlength="25">
							</div>
						</div>

						<div class="col-sm-2">

							<div class="form-group">
								<label for="customerName" class="control-label">Last Name </label> <input type="text"
									autocomplete="off" name="lastName"
									class="form-control input-sm" required="required"
									placeholder="Last Name" maxlength="25">
							</div>
						</div>
						<%-- <s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="travelRequestNumber" class="control-label">Travel Request Number</label>
									<input type="number" autocomplete="off"
										name="travelRequestNumber" class="form-control input-sm"
										required="required" placeholder="Travel Request Number">
								</div>
							</div>
						</s:if>
						<s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.costCenter || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="costCenter" class="control-label">Cost Center</label> <input type="text"
										autocomplete="off" name="costCenter"
										class="form-control input-sm" required="required"
										placeholder="Cost Center">
								</div>
							</div>
						</s:if> --%>
						 <s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}">
						<div class="col-sm-2">
							<div class="form-group">
								<label for="companyEntity" class="control-label">Company Entity </label>
								<select class="form-control input-sm" name="entity" id="entity" >
								<option value="" selected="selected">Select Entity</option>
										 <s:iterator value="%{#session.Company.companyEntities}">
											<option value="<s:property value="companyEntityId"/>"
												 >
												<s:property value="CompanyEntityName" /></option>
										</s:iterator>
									 
										</select> 


							</div>
						</div>
						</s:if>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="source" class="control-label">Source </label> <input type="text"
									autocomplete="off" name="source" class="form-control input-sm"
									required="required" placeholder="Source" maxlength="35">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="projectName" class="control-label">Project Name </label> <input
									type="text" autocomplete="off" name="projectName"
									class="form-control input-sm" required="required"
									placeholder="Project Name" maxlength="40">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="projectSubTaskDetails" class="control-label">Project SubTask
									Details </label> <input type="text" autocomplete="off"
									name="projectSubTaskDetails" class="form-control input-sm"
									required="required" placeholder="Project SubTask Details" maxlength="40">
							</div>
						</div>

						<%-- <c:if test="${fieldName[0]!=null}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[0]}</label> <input
										type="text" autocomplete="off"
										name="configTripDetailsModel.manualField1"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField1}">
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[1]!=null}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[1]}</label> <input
										type="text" autocomplete="off"
										name="configTripDetailsModel.manualField2"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField2}">
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[2]!=null}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[2]}</label> <input
										type="text" autocomplete="off"
										name="configTripDetailsModel.manualField3"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField3}">
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[3]!=null}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[3]}</label> <input
										type="text" autocomplete="off"
										name="configTripDetailsModel.manualField4"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField4}">
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[4]!=null}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[4]}</label> <input
										type="text" autocomplete="off"
										name="configTripDetailsModel.manualField5"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField5}">
								</div>
							</div>
						</c:if>

 
						<c:if test="${rmConfigModel.department}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="empCode" class="control-label"> Department</label> <input type="text"
										autocomplete="off" name="configTripDetailsModel.department"
										class="form-control input-sm" required="required"
										placeholder="Enter department Details"
										value="<s:property value="configTripDetailsModel.department"/>">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.approverName}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="empCode" class="control-label">Approver Name</label> <input type="text"
										autocomplete="off" name="configTripDetailsModel.approverName"
										class="form-control input-sm" required="required"
										placeholder="Enter approverName Details"
										value="${configTripDetailsModel.approverName}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.location}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="empCode" class="control-label">location</label> <input type="text"
										autocomplete="off" name="configTripDetailsModel.location"
										class="form-control input-sm" required="required"
										placeholder="Enter location Details"
										value="${configTripDetailsModel.location}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.bussinessUnit}">
							<div class="col-sm-2">
								<div class="form-group" class="control-label">
									<label for="empCode">Bussiness Unit</label> <input type="text"
										autocomplete="off" name="configTripDetailsModel.bussinessUnit"
										class="form-control input-sm" required="required"
										placeholder="Enter bussiness Unit Details"
										value="${configTripDetailsModel.bussinessUnit}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.projectCode}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="projectCode" class="control-label"> Project Code</label> <input
										type="text" autocomplete="off"
										name="configTripDetailsModel.projectCode"
										class="form-control input-sm" required="required"
										placeholder="Enter projectCode Details"
										value="${configTripDetailsModel.projectCode}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.reasonForTravel}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="reasonForTravel" class="control-label">Reason For Travel</label> <input
										type="text" autocomplete="off"
										name="configTripDetailsModel.reasonForTravel"
										class="form-control input-sm" required="required"
										placeholder="Enter reasonForTravel Details"
										value="${configTripDetailsModel.reasonForTravel}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.billNonBill}">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="billNonBill" class="control-label">Billing/Non Billing</label> <input
										type="text" autocomplete="off"
										name="configTripDetailsModel.billNonBill"
										class="form-control input-sm" required="required"
										placeholder="Enter billNonBill Details"
										value="${configTripDetailsModel.billNonBill}">
								</div>
							</div>
						</c:if> --%>



						<div class="col-sm-2">
							<div class="form-group">
								<label for="customerComments" class="control-label">Customer Comments</label>

								<textarea rows="2" cols="12" name="customerComments"
									class="form-control input-sm" placeholder="Customer Comments" required></textarea>

							</div>
						</div>

					 
											</div>
											</div>
					
					<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
												<i class="fa fa-money fa-2x" aria-hidden="true"></i> 
														 <b>Trip Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix">  
						<div class="col-sm-2">
							<div class="form-group">
								<label for="tripType" class="control-label">Trip Type</label> <select name="tripType"
									class="form-control input-sm" id="tripT" required>
									<!-- <option value="">Select Trip Type</option> -->
									<option value="O" selected="selected">One Way</option>
									<option value="R">Round Trip</option>
									<!-- <option value="M">Multi Trip</option> -->
								</select>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="country" class="control-label">Origin </label> <input type="text"
									autocomplete="on" id="origin" name="origin"
									class="form-control input-sm" required="required"
									placeholder="City">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="checkOutDate" class="control-label">Destination </label> <input
									type="text" autocomplete="on" id="destination"
									name="destination" class="form-control input-sm"
									required="required" placeholder="City">

							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="requestDate" class="control-label">Departure Date </label> <input
									type="text" autocomplete="off" name="tranDepartureDate"
									id="requestDate" class="form-control input-sm"
									onchange="validateDateFormat(this)" required="required"
									placeholder="Departure Date">
							</div>
						</div>
						<div class="col-sm-2" id="request_return">
							<div class="form-group" >
								<label for="requestDate" class="control-label">Arrival Date</label> <input type="text"
									autocomplete="off" name="tranArrivalDate" id="returnDate"
									class="form-control input-sm"
									onchange="validateDateFormat(this)" required="required"
									placeholder="Arrival Date">
							</div>
						</div>

						<div class="col-sm-2">
							<div class="form-group">
								<label for="bookingClassPrefer" class="control-label">Booking Class</label> <select
									name="bookingClassPrefer" class="form-control input-sm" required>
									<!-- <option value="Economy">Select Booking Class</option> -->

									<option value="Economy">Economy</option>
									<option value="All">ALL</option>
									<option value="PremiumEconomy">Premium Economy</option>
									<option value="Business">Business</option>
									<option value="PremiumBusiness">Premium Business</option>
								</select>

							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="currency" class="control-label"> Currency </label> <select
									class="form-control input-sm" required="required"
									name="currency">
									<option value="">Select Currency</option>
									<s:iterator value="countryList">
										<s:if test="cur_code=='INR'">
											<option selected="selected"
												value="<s:property value="cur_code"/>"><s:property
													value="cur_code" /></option>
										</s:if>
										<option value="<s:property value="cur_code"/>"><s:property
												value="cur_code" /></option>
									</s:iterator>

								</select>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="passengerCount" class="control-label">Passenger Count</label> <input
									type="number" autocomplete="off" name="passengerCount"
									class="form-control input-sm" required="required"
									placeholder="Passenger Count">
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="airlinePrefer" class="control-label">Airline</label> <select
									class="form-control input-sm" name="airlinePrefer" required>
									<option value="">Select Airline</option>
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
							<button id="GenerateSubmit" type="button" class="btn btn-primary btn-lg">Generate
								Request</button>
						</div>
					</div>
				</form>
			 
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->

	</div>
	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	<script>
		$("#requestDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			//minDate : 0,
			onSelect : function(selectedDate) {
				var date2 = $("#requestDate").datepicker('getDate', '+1d');
				date2.setDate(date2.getDate() + 1);
				$("#returnDate").datepicker('setDate', date2);
				//$("#returnDate").datepicker("option", "minDate", date2);
				$("#returnDate").datepicker("option", date2);
			},

			onClose : function() {
				$("#returnDate").focus();
			}
		});
		$("#returnDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			//minDate : 1,
			onSelect : function(selected) {
				$("#requestDate").datepicker("option", selected)
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
			  
			   $('#GenerateSubmit').click(function(){
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