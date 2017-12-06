<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Designation</title>
<%--
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> --%>
<%-- <script src="js/angular.js" type="text/javascript"></script>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">	
</script>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">	
</script> --%>
<%-- <link href="css/jquery-ui-timepicker-addon.min.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/jquery-ui-timepicker-addon.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="css/alert.css"> --%>
<!-- 
<link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> -->
<!--  <link href="css/jquery-uii.css" rel="stylesheet" type="text/css" /> 
  
 -->
<style type="text/css">
#rooms {
	margin-top: 5px;
} 
.price-details .form-group {
	margin-bottom: 0px;
}

.submitWrap {
	margin-top: 10px;
}

.bg-c {
	background: #eee;
	padding: 5px;
}

.panel-group .panel-heading+.panel-collapse>.panel-body {
	border: 1px solid #ddd;
}

.panel-group, .panel-group .panel, .panel-group .panel-heading,
	.panel-group .panel-heading a, .panel-group .panel-title, .panel-group .panel-title a,
	.panel-group .panel-body, .panel-group .panel-group .panel-heading+.panel-collapse>.panel-body
	{
	border-radius: 2px;
	border: 0;
}

.panel-group .panel-heading {
	padding: 0;
}

.panel-group .panel-heading a {
	display: block;
	background: #668bb1;
	color: #ffffff;
	padding: 8px;
	text-decoration: none;
	position: relative;
}

.panel-group .panel-heading a.collapsed {
	background: #eeeeee;
	color: inherit;
}

.panel-group .panel-heading a:after {
	content: '-';
	position: absolute;
	right: 20px;
	top: 8px;
	font-size: 20px;
}

.panel-group .panel-heading a.collapsed:after {
	content: '+';
}

.more-details a {
	width: 60px;
	text-align: left;
	position: relative;
}

.more-details a:after {
	content: '-';
	position: absolute;
	right: 10%;
	top: 18%;
	font-size: 15px;
	color: #fff;
}

.more-details a.collapsed:after {
	content: '+';
	/*   position: absolute;
  left: 10%;
  top:0px;
  font-size:25px; */
	color: #fff;
}

.panel-group .panel-collapse {
	margin-top: 5px !important;
}

.panel-group .panel-body {
	background: #ffffff;
	padding: 15px;
}

.panel-group .panel {
	background-color: transparent;
}

.panel-group .panel-body p:last-child, .panel-group .panel-body ul:last-child,
	.panel-group .panel-body ol:last-child {
	margin-bottom: 0;
}

.add-remove {
	margin: 20px 0px 20px;
}
/* enable absolute positioning */
.inner-addon {
  position: relative;
}

/* style glyph */
.inner-addon .glyphicon {
  position: absolute;
  padding: 5px 3px 4px 2px;
  pointer-events: none;
}

/* align glyph */
.left-addon .glyphicon  { left:  0px;}
.right-addon .glyphicon { right: 0px;}

/* add padding  */
.left-addon input  { padding-left:  30px; }
.right-addon input { padding-right: 30px; }
</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">
<script src="js/angular.js" type="text/javascript"></script>
<script type="text/javascript">
	function numbersonly(thisObject) {
		var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
		if (!floatRegex.test($(thisObject).val())) {
			$(thisObject).val('0');
			$(thisObject).focus();
		}
	}
	function validateDateFormat(thisObject) {
		var dtRegex = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
		if (!dtRegex.test($(thisObject).val())) {
			$(thisObject).val('');
			$(thisObject).focus();
		}
	}
</script>
</head>
<body data-ng-controller="AppCtrl">
	<div>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="row">
			<div class="col-sm-2 clearfix pull-left">
				<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-bus"></i> Bus Travel Request</h3>
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
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-group" id="accordion">

							<div class="panel panel-default">
								<div class="panel-body">
									<form action="createBusTravelRequest" method="post" onsubmit="return validateForm()"
										class="form-horizontal" name="busexpensesFormId"
										id="busexpensesFormId" enctype="multipart/form-data">
										
										<%-- <c:if test="${taxType eq 'GST'}">
											<input type="hidden" id="CGST"
												value="<s:property value="busServiceTaxConfig.CGST" />">
											<input type="hidden" id="IGST"
												value="<s:property value="busServiceTaxConfig.IGST" />">
											<input type="hidden" id="SGST"
												value="<s:property value="busServiceTaxConfig.SGST" />">
											<input type="hidden" id="UGST"
												value="<s:property value="busServiceTaxConfig.UGST" />">
											<input type="hidden" id="total"
											value="<s:property value="busServiceTaxConfig.totalTax" />">
										</c:if> --%>
										
										<c:if test="${taxType eq 'Service'}">
											<input type="hidden" id="basictax"
												value="<s:property value="busServiceTaxConfig.basicTax" />">
											<input type="hidden" id="sbc"
												value="<s:property value="busServiceTaxConfig.swatchBharathCess" />">
											<input type="hidden" id="kkc"
												value="<s:property value="busServiceTaxConfig.krishiKalyanCess" />">
											<input type="hidden" id="total"
												value="<s:property value="busServiceTaxConfig.totalTax" />">
										</c:if>
										
										
										<div class="booking-details">
										
										
										<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
														 <i class="fa fa-book fa-2x"></i>  
														 <b>Booking Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix"> 				 
												<s:if
													test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
													<div class="col-sm-2">
														<div class="form-group">
															<label for="busTravelRequest.TRNo" class=" control-label">
																Travel Request Number </label> <input type="text"
																autocomplete="off" name=busTravelRequest.TRNo
																class="form-control input-sm" id="trNo" value=""
																required placeholder="Enter Travel Request Number ">
														</div>
													</div>
												</s:if>
												<s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}">
												<div class="col-sm-2">
														<div class="form-group">
															<label for="busTravelRequest.entity" class=" control-label">
																Company Entity </label> 
																
												<select class="form-control input-sm" onchange="getEntity(this)" name="busOrderRow.companyEntityId" id="entity" >
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
														<label for="busOrderRow.confirmationNumber"
															class=" control-label"> Confirmation Number </label> <input
															type="text" autocomplete="off"
															name="busOrderRow.confirmationNumber"
															class="form-control input-sm" id="confirmationNumber"
															value="" required
															placeholder="Enter Confirmation Number ">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.carCompanyName"
															class=" control-label"> Bus Company Name </label> <input
															type="text" autocomplete="off"
															name="busOrderRow.busCompanyName"
															class="form-control input-sm" id="buscompanyName"
															value="" placeholder="Bus Company name" required>
													</div>
												</div>
												
												
																						 
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busTravelRequest.title">Title</label> <select
															class="form-control input-sm" required="required"
															name="busTravelRequest.title">
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
														<label for="busTravelRequest.firstName"
															class=" control-label">First Name</label> <input
															type="text" autocomplete="off"
															name=busTravelRequest.firstName
															class="form-control input-sm" id="firstName" value=""
															required placeholder="Enter firstName ">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busTravelRequest.lastName"
															class=" control-label">Last Name </label> <input
															type="text" autocomplete="off"
															name=busTravelRequest.lastName
															class="form-control input-sm" id="lastName" value=""
															required placeholder="Enter lastName ">
													</div>
												</div>
											 
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.busType" class=" control-label">
															Bus Type</label> <input type="text" autocomplete="off"
															name=busOrderRow.busType class="form-control input-sm"
															id="busType" value="" required
															placeholder="Enter busType ">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busTravelRequestQuotation.seatNo" class=" control-label">
															Seat No</label> <input type="text" autocomplete="off"
															name=busTravelRequestQuotation.seatNo class="form-control input-sm"
															id="seatNo" value="" required
															placeholder="Enter Seat No">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.pickUp" class=" control-label">
															PickUp</label> <input type="text" autocomplete="off"
															name=busOrderRow.pickUp class="form-control input-sm"
															id="pickUp" value="" required
															placeholder="Enter pickUp location ">
													</div>
												</div>
											 
												<s:if
													test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.location || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
													<div class="col-sm-2">
														<div class="form-group">
															<label for="busOrderRow.location" class=" control-label">Location</label>
															<input type="text" autocomplete="off"
																name=busOrderRow.location class="form-control input-sm"
																id="location" value="" required
																placeholder="Enter location ">
														</div>
													</div>
												</s:if>
												
												
											 
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.dropOff" class=" control-label">
															DropOff </label> <input type="text" autocomplete="off"
															name=busOrderRow.dropOff class="form-control input-sm"
															id="dropOff" value="" required
															placeholder="Enter dropOff location ">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.supplierName"
															class=" control-label">Supplier name</label> <select
															class="form-control input-sm"
															name="busOrderRow.supplierName" id="supplierName"
															required>
															<option value="" selected="selected">select
																Supplier</option>
															<s:iterator value="ApiProviders">
																<option value="<s:property value="vendorName"/>"><s:property
																		value="vendorName" /></option>
															</s:iterator>
														</select>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.busBookingDate"
															class=" control-label"> Booking Date </label> <input
															type="text" autocomplete="off" readonly="readonly"
															name="busOrderRow.busBookingDate"
															onchange="validateDateFormat(this)"
															class="form-control input-sm" id="busBookingDate"
															value="" required placeholder="Enter Booking Date">
													</div>
												</div>
											 
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busTravelRequestQuotation.travelDateTemp"
															class=" control-label">Travel Date</label> <input
															type="text" autocomplete="off" readonly="readonly"
															name=busTravelRequestQuotation.travelDateTemp
															class="form-control input-sm" id="bustraveldate1"
															required onchange="validateDateFormat(this)"
															placeholder="select the date ">
													</div>
												</div>
												
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.depTime"
															class=" control-label">Departure Time</label> <input type="text" autocomplete="off"
													 		id="departureTime"
															name="busOrderRow.departureTime"
															class="form-control input-sm" required="required"
														placeholder="HH:mm">
												
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.arrTime"
															class=" control-label">Arrival Time</label> <input type="text" autocomplete="off"
													 		id="arrivalTime"
															name="busOrderRow.arrivalTime"
															class="form-control input-sm" required="required"
													placeholder="HH:mm">
												
													</div>
												</div>
												 
											
									<c:if test="${fieldName[0]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[0]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField1"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField1}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[1]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[1]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField2"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField2}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[2]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[2]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField3"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField3}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[3]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[3]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField4"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField4}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[4]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[4]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField5"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField5}" >
									</div>
								</div>
							</c:if>
										 
											
											<c:if test="${rmConfigModel.empCode}">
											<div class="col-sm-2" >
													<div class="form-group">
														<label for="empCode" class=" control-label"> Emp Code</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.empCode"
															class="form-control input-sm" required="required"
															placeholder="Enter emp Code Details" value="<s:property value="configTripDetailsModel.empCode"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.department}">
											<div class="col-sm-2"  >
													<div class="form-group">
														<label for="empCode" class=" control-label"> Department</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.department"
															class="form-control input-sm" required="required"
															placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.department"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.costCenter}">
											<div class="col-sm-2" >
													<div class="form-group">
														<label for="empCode" class=" control-label">Cost Center</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.costCenter"
															class="form-control input-sm" required="required"
															placeholder="Enter costCenter Details" value="<s:property value="configTripDetailsModel.costCenter"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.approverName}">
											<div class="col-sm-2" >
													<div class="form-group" >
														<label for="empCode" class=" control-label">Approver Name</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.approverName"
															class="form-control input-sm" required="required"
															placeholder="Enter approverName Details" value="<s:property value="configTripDetailsModel.approverName"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.location}">
											<div class="col-sm-2"  >
													<div class="form-group">
														<label for="empCode" class=" control-label">location</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.location"
															class="form-control input-sm" required="required"
															placeholder="Enter location Details" value="<s:property value="configTripDetailsModel.location"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.bussinessUnit}">
											<div class="col-sm-2" >
													<div class="form-group">
														<label for="empCode" class=" control-label">Bussiness Unit</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.bussinessUnit"
															class="form-control input-sm" required="required"
															placeholder="Enter bussiness Unit Details" value="<s:property value="configTripDetailsModel.bussinessUnit"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.projectCode}">
											<div class="col-sm-2"  >
													<div class="form-group">
														<label for="projectCode" class=" control-label"> Project Code</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.projectCode"
															class="form-control input-sm" required="required"
															placeholder="Enter projectCode Details" value="<s:property value="configTripDetailsModel.projectCode"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.reasonForTravel}">
											<div class="col-sm-2"  >
													<div class="form-group">
														<label for="reasonForTravel" class=" control-label">Reason For Travel</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.reasonForTravel"
															class="form-control input-sm" required="required"
															placeholder="Enter reasonForTravel Details" value="<s:property value="configTripDetailsModel.reasonForTravel"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.billNonBill}">
											<div class="col-sm-2"  >
													<div class="form-group" >
														<label for="billNonBill" class=" control-label">Billing/Non Billing</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.billNonBill"
															class="form-control input-sm" required="required"
															placeholder="Enter billNonBill Details" value="<s:property value="configTripDetailsModel.billNonBill"/>">
													</div>
												</div>
											</c:if> 
											</div>
										</div>
											  
										<div class="price-details"> 
										<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
														 <i class="fa fa-money fa-2x"></i>  
														 <b>Pricing Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix"> 
											<div class="col-sm-2"  >
											  <div class="form-group">
												<label for="busTravelRequestQuotation.currency" class=" control-label">Currency </label> 
												<select class="form-control input-sm" name="busTravelRequestQuotation.currency" id="Currency" required>
													<s:iterator value="CountryList">
														<c:choose>
															<c:when test="${cur_code=='INR'}">
																<option value="<s:property value="cur_code"/>" selected>
																	<s:property value="cur_code" /></option>
															</c:when>
															<c:otherwise>
																<option value="<s:property value="cur_code"/>">
																	<s:property value="cur_code" /></option>
															</c:otherwise>
														</c:choose>
													</s:iterator>
												</select>
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="busOrderRow.supplierPrice"
													class="control-label">Supplier Price</label> <input
													type="text" autocomplete="off"
													name=busOrderRow.supplierPrice
													class="form-control input-sm" id="supplierPrice" value="0"
													required onchange="numbersonly(this)"
													placeholder="Enter supplier Price ">
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="busOrderRow.basePrice" class=" control-label">
													Base Price</label> <input type="text" autocomplete="off"
													name=busOrderRow.basePrice class="form-control input-sm"
													id="basePrice" value="0" required
													onchange="numbersonly(this)"
													placeholder="Enter  base Price">
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="busOrderRow.otherTaxes" class=" control-label">Other
													Taxes</label> <input type="text" autocomplete="off"
													name=busOrderRow.otherTaxes class="form-control input-sm"
													id="otherTaxes" value="0" required
													onchange="numbersonly(this)"
													placeholder="Enter otherTaxes ">
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="busOrderRow.markUp" class=" control-label">Markup
													Amount</label> <input type="text" autocomplete="off"
													name=busOrderRow.markUp class="form-control input-sm"
													id="markupamount" value="0" required
													onchange="numbersonly(this)"
													placeholder="Enter Markup Price ">
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group" style="color: red;display: none" id="balanceCheck">
												<h5 style="color: red">Total Booking Amount is more than Deposit Or Wallet Balances.Please Check.</h5>
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="busOrderRow.managementFee"
													class=" control-label">Management Fee</label> <input
													type="text" autocomplete="off"
													name=busOrderRow.managementFee
													class="form-control input-sm" id="managementFee"
													 
													<c:if test="${taxType eq 'GST'}">value="<s:property value="busGstTaxConfig.managementFee" />"</c:if>
													<c:if test="${taxType eq 'Service'}">value="<s:property value="busServiceTaxConfig.managementFee" />"</c:if>
													required onchange="numbersonly(this)"
													placeholder="Enter management Fee " readonly>
											</div>
											</div>
											<div class="col-sm-2"> 
											<input type="hidden" id="taxType" value="${taxType}">
											
											<div class="form-group">
												<label for="busOrderRow.convenienceFee"
													class=" control-label">Convenience Fee </label> <input
													type="text" autocomplete="off"
													name=busOrderRow.convenienceFee
													class="form-control input-sm" id="convenienceFee"
													<c:if test="${taxType eq 'GST'}">value="<s:property value="busGstTaxConfig.convenienceFee" />"</c:if>
													<c:if test="${taxType eq 'Service'}">value="<s:property value="busServiceTaxConfig.convenienceFee" />"</c:if>
													
													required onchange="numbersonly(this)"
													placeholder="Enter convenience Fee " readonly>
											</div>
											</div>

											<c:if test="${taxType eq 'GST'}">
											<input type="hidden" autocomplete="off"
													name="busOrderRow.totalGstTax" class="form-control input-sm"
													id="gstTax" value="${totalGstTaxPer}" required 
													onchange="numbersonly(this)" >
											<div class="col-sm-2"> 
												<div class="form-group">
												<label for="totalGstTaxAmount" >
													Gst Tax </label> 
													<div class="inner-addon right-addon">
      												<span class="glyphicon">@ <span id="gstTaxPer" ></span>%</span>
													<input type="text" autocomplete="off"
													name="totalGstTaxAmount" onchange="numbersonly(this)"
													class="form-control input-sm" id="gstTaxAmount" value="${totalGstAmount}"
													required placeholder="Enter Gst Tax "
													readonly="readonly">
													</div>
											</div>
											</div>
											</c:if>
												
											<c:if test="${taxType eq 'Service'}">
											<<div class="col-sm-2"> 
												<div class="form-group">
												<label for="busOrderRow.serviceTax" class=" control-label">
													Service Tax</label> <input type="text" autocomplete="off"
													name=busOrderRow.serviceTax class="form-control input-sm"
													id="serviceTax" value="0" required
													onchange="numbersonly(this)" placeholder=" service Tax "
													readonly="readonly">
											</div>
											</div>
											</c:if>
											
<div class="col-sm-2"> 
											<div class="form-group">
												<label for="busTravelRequestQuotation.totalAmount"
													class=" control-label"><span style="color:red"> *</span>
													Total Amount Without <c:if test="${taxType eq 'GST'}">Gst</c:if>
													<c:if test="${taxType eq 'Service'}">Service</c:if> Tax 
												</label> 
													<input type="text" autocomplete="off"
													name=busTravelRequestQuotation.totalAmount
													class="form-control input-sm" id="totalamount" value="0"
													required onclick="numbersonly(this);addtax();"
													placeholder="total Amount ">
											</div>
											
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="carOrderRow.totInvoiceAmount" class=" control-label">Total Invoice Amount
													 </label>  <input type="text" autocomplete="off"
													onclick="numbersonly(this);addtax();"
													name="busOrderRow.totInvoiceAmount" id="invoiceamount"
													class="form-control input-sm" placeholder="Invoice Amount"
													value="0" required>
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="busOrderRow.remarks" class=" control-label">
													Remarks </label>
												<textarea rows="1" cols="1" class="form-control input-sm"
													name="busOrderRow.remarks" id="remarks"
													placeholder="remarks" required></textarea>
											</div>
 
										</div>
									
										
											
											</div>
										</div> 
										<div class="form-group text-center">
											<div class="col-xs-12 submitWrap text-center">
												<input type="hidden" name="tripId" id="tripId"
													value="${tripId}">
												<button type="submit" name="busTravelRequest" value=""
													id="busTravelRequestsubmitnew"
													class="btn btn-primary btn-lg">Create</button>
											</div>
										</div>
</div>
</div>
									</form>
								</div>
								<!--/.panel-body -->
								<!-- </div -->
								
								<!--/.panel-collapse -->
							</div>
							<!--/.panel-body -->
						</div>
						<!-- /.panel-group -->
					</div>
				</div>
				<!-- harsha added colapse ended -->
			</section>
		</div>
		<!-- /.row -->
		<!-- Main row -->
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<div class="modal fade" id="emailModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body clearfix">
					<p id="desc"></p>
				</div>
			</div>
		</div>
	</div>
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript">
		$('#bustraveldate1').datepicker({
			dateFormat : 'dd-mm-yy',
			changeMonth : true,
			changeYear : true,
		});
		$('#busBookingDate').datepicker({
			dateFormat : 'dd-mm-yy',
			changeMonth : true,
			changeYear : true,
		}).datepicker("setDate", new Date());
		
		$("#departureTime").timepicker({
			dateFormat : "HH:mm",
		});
		$("#arrivalTime").timepicker({
			dateFormat : "HH:mm",
		});
		
		
		$('.price-details').on('keyup input', function () {
			   addtax(); 
			  });
		function addtax() {
			var baseamountinput = $("#basePrice").val();
			var markupamountinput = $("#markupamount").val();
			var otherTaxesinput = $("#otherTaxes").val();
			var managementfeeinput = $("#managementFee").val();
			var conveniencefeeinput = $("#convenienceFee").val();
			var taxType=$("#taxType").val();
			
			var gstTaxAmount = $("#gstTax").val()*(managementfeeinput/100);
			
			var serviceTaxdummy='';
			if(taxType=='GST') 
				serviceTaxdummy=gstTaxAmount;
			else 
			serviceTaxdummy=$("#serviceTax").val();
			 
			if(baseamountinput==''){
				baseamountinput=0;
			}
			if(markupamountinput==''){
				markupamountinput=0;
			}
			if(otherTaxesinput==''){
				otherTaxesinput=0;
			}
			if(managementfeeinput==''){
				managementfeeinput=0;
			}
			if(conveniencefeeinput==''){
				conveniencefeeinput=0;
			}
			if(serviceTaxdummy==''){
				serviceTaxdummy=0;
			}
			var totalamount = parseInt(baseamountinput)
					+ parseInt(markupamountinput) + parseInt(otherTaxesinput)
					+ parseInt(managementfeeinput)
					+ parseInt(conveniencefeeinput);
			
			var invoiceamount=totalamount + parseInt(serviceTaxdummy);
			$("#invoiceamount").val(invoiceamount);
			$("#gstTaxAmount").val(gstTaxAmount);

			var totalamountfortax = totalamount - parseInt(conveniencefeeinput);
			var basicTax = $("#basictax").val();
			var sbcTax = $("#sbc").val();
			var kkcTax = $("#kkc").val();
			var totalTax = $("#total").val();
			var depositBalance =${userWallet.depositBalance};
			var walletBalance =${userWallet.walletbalance};
			var basetaxcal = (parseInt(totalamountfortax) / parseInt(100))
					* parseInt(basicTax);
			var sbc = (parseInt(totalamountfortax) / parseInt(100))
					* parseInt(sbcTax);
			var kkc = (parseInt(totalamountfortax) / parseInt(100))
					* parseInt(kkcTax);
			var kkc = (parseInt(totalamountfortax) / parseInt(100))
					* parseInt(kkcTax);
			var totalTax = (parseInt(totalamountfortax) / parseInt(100))
					* parseInt(totalTax);

			$("#totalamount").val(totalamount);
			$("#serviceTax").val(Math.round(totalTax));
			var totalamountdummy=totalamount + parseInt(serviceTaxdummy);
			
			
			if (depositBalance > totalamountdummy) {
				$('.submitWrap').show();
				$('#balanceCheck').hide();
			}
			else if (walletBalance>totalamountdummy) {
				$('.submitWrap').show();
				$('#balanceCheck').hide();
			}
			else{
				$('.submitWrap').hide();
				$('#balanceCheck').show();
			}

		}

		$(document)
				.ready(
						function() {

							$(
									"#supplierPrice , #markupamount , #totalamount , #managementFee , #convenienceFee , #markUp , #otherTaxes , #basePrice")
									.keydown(
											function(e) {
												var isModifierkeyPressed = (e.metaKey
														|| e.ctrlKey || e.shiftKey);
												var isCursorMoveOrDeleteAction = ([
														46, 8, 9, 37, 38, 39,
														40, 110, 190 ]
														.indexOf(e.keyCode) != -1);
												var isNumKeyPressed = (e.keyCode >= 48 && e.keyCode <= 58)
														|| (e.keyCode >= 96 && e.keyCode <= 105);
												var vKey = 86, cKey = 67, aKey = 65;
												switch (true) {
												case isCursorMoveOrDeleteAction:
												case isModifierkeyPressed == false
														&& isNumKeyPressed:
												case (e.metaKey || e.ctrlKey)
														&& ([ vKey, cKey, aKey ]
																.indexOf(e.keyCode) != -1):
													break;
												default:
													e.preventDefault();
												}
											});
						});
	</script>
	
	
	<script type="text/javascript">
	 
			/*  function validateForm(){
	var totalBaseFare=Math.round(parseFloat($('#totalamount').val())*100)/100;
	var totalSupplierPrice=Math.round(parseFloat($('#supplierPrice').val())*100)/100;
	
	if(totalBaseFare<totalSupplierPrice){
		alert('Please check supplier price is more than total booking amount');
		return false;
	}
	else{
		$('#busexpensesFormId').submit(validateForm);
		
		
	}
		
	}
	
	 */
	
	</script>
	<script type="text/javascript">
	
	
	function getEntity(s) {
		var entityId=s[s.selectedIndex].value;
		var travelType='bus';
		$.ajax({
		    url : "getEntityTaxes?entityId="+entityId+"&travelType="+travelType,
		    type : "GET", 
		    dataType: "json",
		    success : function(data) {			    	
		    	$("#gstTax").val(data.commonGstTaxes.totalTax);
				$("#gstTaxPer").text(data.commonGstTaxes.totalTax);
		    	$("#gstTaxAmount").val(data.commonGstTaxes.totalTax*(data.commonGstTaxes.managementFee/100));
		    }
		});
	}
	</script>
	<script type="text/javascript">
	$(document).ready(function() {
		var managementFee=${busGstTaxConfig.managementFee};
		var totalGstAmount=${totalGstTaxPer};
		var total=parseFloat(totalGstAmount)*parseFloat(managementFee/100);
	$("#gstTaxAmount").val(total);
	$("#gstTaxPer").text(totalGstAmount);
	
	});
	</script>
	
	
</body>
</html>