<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Train Travel</title>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#rooms {
	margin-top: 5px;
}
.price-details .form-group{
    margin-bottom: 0px;
}
.submitWrap{
margin-top: 10px;
}

.bg-c{ 
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
.error {
    color:red;
}
.valid {
    color:green;
}
.form-control-feedback{
  display: none;
}
.form-horizontal .has-feedback .form-control-feedback {
    right: 12px !important;
}


.has-feedback label ~.form-control-feedback {
    top: 21px !important;
}
/* add padding  */
.left-addon input  { padding-left:  30px; }
.right-addon input { padding-right: 30px; }
</style>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">

<%-- <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.1/css/bootstrapValidator.min.css">
 <script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.1/js/bootstrapValidator.min.js"></script> --%> 
	 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>


<script src="js/angular.js" type="text/javascript"></script>
<script type="text/javascript">
	function numbersonly(thisObject) {
		//var intRegex = /^\d+$/;
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
				<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-train"></i> Train Travel Request</h3>
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
					<!-- harsha added colapse -->
					<div class="col-sm-12">
						<div class="panel-group" id="accordion">
							<!-- <div id="collapseTen" class="panel-collapse collapse"> -->
							<div class="panel-body">
								<form action="createTrainTravelRequest" method="post" 
									class="form-horizontal" name="trainexpensesFormId"
									id="trainexpensesFormId" enctype="multipart/form-data">
									<c:if test="${taxType eq 'Service'}">
									<input type="hidden" id="basictax"
										value="<s:property value="railServiceTaxConfig.basicTax" />">
									<input type="hidden" id="sbc"
										value="<s:property value="railServiceTaxConfig.swatchBharathCess" />">
									<input type="hidden" id="kkc"
										value="<s:property value="railServiceTaxConfig.krishiKalyanCess" />">
									<input type="hidden" id="total"
										value="<s:property value="railServiceTaxConfig.totalTax" />">
									</c:if>
									
									<input type="hidden" id="companyRole"
												value="<s:property value="#session.Company.companyRole.isCorporate()" />">
										<input  name="companyId" type="hidden" id="companyId" value="<s:property value="#session.User.Companyid" />">		
										<div class="booking-details">
										
										<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
														 <i class="fa fa-book fa-2x"></i>  
														 <b>Booking Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix"> 	
									
										<s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}">
												<div class="col-sm-2">
														<div class="form-group has-feedback">
															<label for="trainTravelRequest.entity" class=" control-label">
																Company Entity </label> 
																<select class="form-control input-sm" required onchange="getEntity()" name="trainOrderRow.companyEntityId" id="entity" required="required">
																	 <option value="" selected="selected">Select Entity</option>
																		 	<s:iterator value="%{#session.Company.companyEntities}">
																				<option value="<s:property value="companyEntityId"/>" >
																				<s:property value="CompanyEntityName" /></option>
																			</s:iterator>
																	</select> 
																	<span class="form-control-feedback glyphicon glyphicon-ok"></span> 					 
														</div>
													</div>
											</s:if>
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="travelDate" class=" control-label">Train
													Number</label> <input type="number" autocomplete="off"
													name="trainTravelRequestQuotation.trainNumber"
													class="form-control input-sm" id="traveltrainNumber" value=""
													required placeholder="Enter Train Number ">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="trainOrderRow.confirmationNumber"
													class=" control-label">Confirmation Number</label> <input
													type="text" autocomplete="off"
													name="trainOrderRow.confirmationNumber"
													class="form-control input-sm " id="trainConfirmation" value=""
													required placeholder="Enter Confirmation Number ">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
									  
									 
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="trainTravelRequestQuotation.fromlocation"
													class=" control-label"> From Location </label> <input
													type="text" autocomplete="off"
													name=trainTravelRequestQuotation.fromlocation
													class="form-control input-sm " id="travelfromLocation" value=""
													required placeholder="Enter From Location ">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="trainTravelRequestQuotation.tolocation"
													class=" control-label">To Location</label> <input
													type="text" autocomplete="off"
													name="trainTravelRequestQuotation.tolocation"
													class="form-control input-sm " id="traveltoLocation" value=""
													required placeholder="Enter To Location ">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="trainOrderRow.ticketType" class="control-label">Ticket Type</label> <select
													class="form-control input-sm" onchange="getEntity()" required="required"
													name="trainOrderRow.ticketType" id="ticketType">
													<option value="tatkal" >Tatkal</option>
													<option value="normal" selected="selected" >Normal</option>
												</select>
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										 
										<div class="col-sm-2">
												<div class="form-group has-feedback">
													<label for="trainOrderRow.supplierName" class="control-label">Supplier Name</label> 
													<select class="form-control input-sm" name="trainOrderRow.supplierName" id="supplierName" required>
									<option value="" selected="selected">select Supplier</option>
								 <s:iterator value="ApiProviders">
									<option value="<s:property value="vendorName"/>"><s:property value="vendorName" /></option>
								</s:iterator> 
								 </select>
								 <span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group has-feedback">
													<label for="trainOrderRow.trainBookingDate"
														class=" control-label"> Booking Date </label> <input
														type="text" autocomplete="off" readonly="readonly"
														name="trainOrderRow.trainBookingDate"
														onchange="validateDateFormat(this)"
														class="form-control input-sm" id="trainBookingDate" value=""
														required placeholder="Enter Booking Date">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="trainTravelRequestQuotation.traveldatetemp"
													class=" control-label">Travel Date</label> <input
													type="text" autocomplete="off" readonly="readonly"
													onchange="validateDateFormat(this)"
													name="trainTravelRequestQuotation.traveldatetemp"
													class="form-control input-sm" id="traveldateforvisa"
													value="" required placeholder="Enter Travel Date ">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
									 
											 </div>
											
											
												
											</div> 
											<div class="companysetup clearfix" id = "passegerDetails" > 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
														 <i class="fa fa-book fa-2x"></i>  
														 <b>Passenger Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix"> 
											<div class="detailswithRM"> 	 
												<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="busTravelRequest.title">Title</label> <select
															class="form-control input-sm" required="required"
															name="trainOrderCustomerList[0].title">
													<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
														</select>
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="busTravelRequest.firstName"
															class=" control-label">First Name</label> <input
															type="text" autocomplete="off"
															name=trainOrderCustomerList[0].firstName
															class="form-control input-sm " id="firstName" value=""
															required placeholder="Enter firstName ">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="busTravelRequest[0].lastName"
															class=" control-label">Last Name </label> <input
															type="text" autocomplete="off"
															name=trainOrderCustomerList[0].lastName
															class="form-control input-sm " id="lastName" value=""
															required placeholder="Enter lastName ">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div> 
												 
												 <s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber!=null || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="travelDate" class=" control-label">
													Travel Request Number </label> <input type="text" autocomplete="off"
													name="trainOrderCustomerList[0].rmConfigTripDetailsModel.trNumber"
													class="form-control input-sm " id="travelReqNo" value=""
													required placeholder="Enter Travel Number ">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										</s:if>
											
									<c:if test="${fieldName[0]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode" class=" control-label"> ${fieldName[0]}</label> <input type="text"
											autocomplete="off" name="trainOrderCustomerList[0].rmConfigTripDetailsModel.manualField1"
											class="form-control input-sm "  required
											placeholder="Enter ${fieldName[0]}" value="${configTripDetailsModel.manualField1}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[1]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode" class=" control-label"> ${fieldName[1]}</label> <input type="text"
											autocomplete="off" name="trainOrderCustomerList[0].rmConfigTripDetailsModel.manualField2"
											class="form-control input-sm " required
											placeholder="Enter ${fieldName[1]}" value="${configTripDetailsModel.manualField2}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[2]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode" class=" control-label"> ${fieldName[2]}</label> <input type="text"
											autocomplete="off" name="trainOrderCustomerList[0].rmConfigTripDetailsModel.manualField3"
											class="form-control input-sm " required
											placeholder="Enter ${fieldName[2]}" value="${configTripDetailsModel.manualField3}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[3]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode" class=" control-label"> ${fieldName[3]}</label> <input type="text"
											autocomplete="off" name="trainOrderCustomerList[0].rmConfigTripDetailsModel.manualField4"
											class="form-control input-sm " required
											placeholder="Enter ${fieldName[3]}" value="${configTripDetailsModel.manualField4}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[4]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode" class=" control-label"> ${fieldName[4]}</label> <input type="text"
											autocomplete="off" name="trainOrderCustomerList[0].rmConfigTripDetailsModel.manualField5"
											class="form-control input-sm " required 
											placeholder="Enter ${fieldName[4]}" value="${configTripDetailsModel.manualField5}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
										 
											
											<c:if test="${rmConfigModel.empCode}">
											<div class="col-sm-2" >
													<div class="form-group has-feedback">
														<label for="empCode" class=" control-label"> Emp Code</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.empCode"
															class="form-control input-sm " required="required" id="rmempCode"
															placeholder="Enter emp Code Details" value="<s:property value="configTripDetailsModel.empCode"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.department}">
											<div class="col-sm-2"  >
													<div class="form-group has-feedback">
														<label for="empCode" class=" control-label"> Department</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.department"
															class="form-control input-sm " required="required" id="rmDepartment"
															placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.department"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.costCenter}">
											<div class="col-sm-2" >
													<div class="form-group has-feedback">
														<label for="empCode" class=" control-label">Cost Center</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.costCenter"
															class="form-control input-sm " required="required" id="rmcostCenter"
															placeholder="Enter costCenter Details" value="<s:property value="configTripDetailsModel.costCenter"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.approverName}">
											<div class="col-sm-2" >
													<div class="form-group has-feedback" >
														<label for="empCode" class=" control-label">Approver Name</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.approverName"
															class="form-control input-sm " required="required" id="rmapproverName"
															placeholder="Enter approverName Details" value="<s:property value="configTripDetailsModel.approverName"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.location}">
											<div class="col-sm-2"  >
													<div class="form-group has-feedback">
														<label for="empCode" class=" control-label">location</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.location"
															class="form-control input-sm " required="required" id="rmlocation"
															placeholder="Enter location Details" value="<s:property value="configTripDetailsModel.location"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.bussinessUnit}">
											<div class="col-sm-2" >
													<div class="form-group has-feedback">
														<label for="empCode" class=" control-label">Bussiness Unit</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.bussinessUnit"
															class="form-control input-sm " required="required" id="rmbussinessUnit"
															placeholder="Enter bussiness Unit Details" value="<s:property value="configTripDetailsModel.bussinessUnit"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.projectCode}">
											<div class="col-sm-2"  >
													<div class="form-group has-feedback">
														<label for="projectCode" class=" control-label"> Project Code</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.projectCode"
															class="form-control input-sm " required="required" id="rmprojectCode" 
															placeholder="Enter projectCode Details" value="<s:property value="configTripDetailsModel.projectCode"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.reasonForTravel}">
											<div class="col-sm-2"  >
													<div class="form-group has-feedback">
														<label for="reasonForTravel" class=" control-label">Reason For Travel</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.reasonForTravel"
															class="form-control input-sm " required="required" id="rmreasonForTravel"
															placeholder="Enter reasonForTravel Details" value="<s:property value="configTripDetailsModel.reasonForTravel"/>">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.billNonBill}">
											<div class="col-sm-2"  >
													<div class="form-group has-feedback" >
														<label for="billNonBill" class=" control-label">Billing/Non Billing</label> <input
															type="text" autocomplete="off"
															name="trainOrderCustomerList[0].rmConfigTripDetailsModel.billNonBill"
															class="form-control input-sm " required="required" id="rmbillNonBill"
															placeholder="Enter billNonBill Details" value="<s:property value="configTripDetailsModel.billNonBill"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if> 
											
										</div>	
											
											
						           <div class="col-sm-12  pricebreakup"> 
											<a class="btn btn-success createdquotation collapsed"
												role="button" data-toggle="collapse"
												href="#priceFilters" aria-expanded="true"
												aria-controls="priceFilters"> Price Breakup(s)</a>
									</div>  
												
												
									
 

										<div class="col-sm-12 clearfix pricebb price-details">
											<div class="row">
												<div class="collapse" id="priceFilters"
													aria-expanded="true">
													<div class="panel-body">
														<!-- Filter of main info -->
														<div class=" col-sm-12 clearfix">
														
														<div class="col-sm-2"> 
																<div class="form-group has-feedback">
																	<label for="busOrderRow.supplierPrice"
																		class="control-label">Supplier Price</label> <input
																		type="text" autocomplete="off"
																		name=trainOrderCustomerList[0].supplierAmount
																		class="form-control input-sm cusValidationforprice" id="supplierFare" value="0"
																		required onchange="numbersonly(this)"
																		placeholder="Enter supplier Price ">
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>
																</div>
																</div>
															 
															<div class="col-sm-2">
																<div class="form-group has-feedback"><%-- ${flightQuotation.totalAmount} --%>
																	<label for="hotelName" class=" control-label">Base
																		Fare </label> <input type="text" autocomplete="off"
																		id="baseFare"
																		name="trainOrderCustomerList[0].baseAmount"
																		class="form-control input-sm required baseFareprice cusValidationforprice" required="required"
																		value="0" 
																		placeholder="Base Fare" >
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															
															
															 
															 
															 
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label notone">Tax
																	</label> <input type="text" autocomplete="off"
																	 id="taxFare" value="0"
																		name="trainOrderCustomerList[0].tax"
																		class="form-control input-sm cusValidationforprice" required="required"
																		placeholder="Tax">
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label notone1">Markup
																	</label> <input type="text" autocomplete="off"
																		name="trainOrderCustomerList[0].markUp"
																		id="markupFare" value="0"
																		class="form-control input-sm cusValidationforprice" required="required"
																		placeholder="Markup">
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															
														</div>
													</div>
												</div>
											</div>
										</div> 
											</div>
										</div>
										
										<div  id="passengerlist">
												<div class="additionalpackage "></div> 
											</div>	 
											
											<div class="row clearfix add-remove">
													<a class="btn btn-primary" role="button" id="addroom"
														onclick="add(this);"> <!-- onclick="add() onclick="remove_field()" -->
														Add More
													</a> <a class="btn btn-primary remove_field" id="removeroom"
														role="button" onclick="remove_field(this)" disabled>
														Remove </a>

												</div> 
										
										  

										
									 
										<div class="pricee-details">
										
										<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
														 <i class="fa fa-money fa-2x"></i>  
														 <b>Pricing Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix"> 	
											<div class="col-sm-2">
											<div class="form-group">
											<label for="trainTravelRequestQuotation.currency" class="control-label">Currency </label> 
												<select class="form-control input-sm"
													name="trainTravelRequestQuotation.currency" id="currency"
													required>
													<s:iterator value="CountryList">
														<c:choose>
														<c:when test="${cur_code=='INR'}">
															<option value="<s:property value="cur_code"/>" selected  >
																<s:property value="cur_code" /></option>
														</c:when>	
														<c:otherwise>
															<option value="<s:property value="cur_code"/>" >
																<s:property value="cur_code" /></option>
														</c:otherwise>		
														</c:choose>
														</s:iterator>
												</select>
											</div>
											</div>
											<div class="col-sm-2">
											<div class="form-group">
												<label for="trainOrderRow.supplierPrice"
													class="control-label">Total Supplier Price</label> <input
													type="text" autocomplete="off" onchange="numbersonly(this)"
													name="trainOrderRow.supplierPrice" id="supplierPrice"
													class="form-control input-sm"
													placeholder="Enter Supplier Price" value="0" readonly required>
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="hotel address1" class="control-label">Total Base
													Price</label> <input type="text" autocomplete="off"
													onchange="numbersonly(this)" name="trainOrderRow.basePrice"
													id="basePrice" class="form-control input-sm "
													placeholder="Enter Base Price" value="0" readonly required>
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="trainOrderRow.otherTaxes" class=" control-label">Total Other
													Taxes </label> <input type="text" autocomplete="off"
													name="trainOrderRow.otherTaxes" id="othertaxes"
													class="form-control input-sm" value="0"
													onchange="numbersonly(this)" placeholder="Other Taxes"
													readonly required>
											</div>
											</div>
											<div class="col-sm-2">																	 
											<div class="form-group">
												<label for="managementFee" class=" control-label">Total Mark
													Up</label> <input type="text" autocomplete="off"
													name="trainOrderRow.markUp" class="form-control input-sm"
													id="markUp" value="0" required onchange="numbersonly(this)"
													placeholder="Enter markup" readonly>
											</div>
											</div>
											<div class="col-sm-2" style=" display: none"  id="balanceCheck"> 
											<div class="form-group" style="color: red;" >
												<h5 style="color: red">Total Booking Amount is more than Deposit Or Wallet Balances.Please Check.</h5>
											</div>
											</div>
											<div class="col-sm-2">
											<div class="form-group mmtgen">
												<label for="checkInDate" class=" control-label">Management
													Fee (General)</label> <input type="text" autocomplete="off"
													name="trainOrderRow.managementFee" id="managementFee"
													class="form-control input-sm"
													<c:if test="${taxType eq 'GST'}">value="<s:property value="trainGstTaxConfig.managementFee" />"</c:if>
													<c:if test="${taxType eq 'Service'}">value="<s:property value="railServiceTaxConfig.managementFee" />"</c:if>
													value="<s:property value="railServiceTaxConfig.managementFee" />"
													onchange="numbersonly(this)"
													placeholder="Enter Management fee" required readonly>
											</div>
											</div>
											<div class="col-sm-2 mmtkal">
											<div class="form-group ">
												<label for="checkInDate" class=" control-label">Management
													Fee (Tatkal)</label> <input type="text" autocomplete="off"
													name="trainOrderRow.managementFeeTatkal" id="managementFeeTatkal"
													class="form-control input-sm" 
													<c:if test="${taxType eq 'GST'}">value="<s:property value="trainGstTaxConfig.managementFeeTatkal" />"</c:if>
													<c:if test="${taxType eq 'Service'}">value="<s:property value="railServiceTaxConfig.managementFeeTatkal" />"</c:if>
													onchange="numbersonly(this)"
													placeholder="Enter Management fee" readonly >
											</div>
											</div>
											<div class="col-sm-2">
										 
											<div class="form-group">
												<label for="trainOrderRow.convenienceFee"
													class=" control-label">Convenience Fee </label> <input 
													type="text" autocomplete="off"
													name="trainOrderRow.convenienceFee" id="convenienceFee"
													class="form-control input-sm"
													<c:if test="${taxType eq 'GST'}">value="<s:property value="trainGstTaxConfig.convenienceFee" />"</c:if>
													<c:if test="${taxType eq 'Service'}">value="<s:property value="railServiceTaxConfig.convenienceFee" />"</c:if>
													onchange="numbersonly(this)"
													placeholder="Train Convenience Fee" required readonly>
											</div>
										 </div>
										<input type="hidden" id="taxType" value="${taxType}">
									 		<c:if test="${taxType eq 'GST'}">
									 		 <input type="hidden" autocomplete="off"
													name="trainOrderRow.TotalGstTax"
													onchange="numbersonly(this)" class="form-control input-sm"
													id="gstTax" value="${totalGstTaxPer}">
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
										 	<div class="col-sm-2">
										 		<div class="form-group">
												<label for="trainOrderRow.serviceTax" class="control-label">
													Service Tax </label> <input type="text" autocomplete="off"
													name="trainOrderRow.serviceTax"
													onchange="numbersonly(this)" class="form-control input-sm"
													id="serviceTax" value="0" required
													placeholder="Enter Service Tax " readonly="readonly">
												</div>
												</div>
										 	</c:if>
										 	<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="trainOrderRow.totalAmount"
													class=" control-label"><span style="color:red"> *</span>
													Total Amount Without 
													<c:if test="${taxType eq 'GST'}">Gst</c:if>
													<c:if test="${taxType eq 'Service'}">Service</c:if>
													Tax</label> <input
													type="text" autocomplete="off"
													name="trainOrderRow.totalAmount" id="totalamount"
													onclick="numbersonly(this);addtax();"
													class="form-control input-sm cusValidationforprice" placeholder="Total Amount"
													value="0" required>
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													
											</div> 
											</div>
											<div class="col-sm-2"> 
											<div class="form-group has-feedback">
												<label for="carOrderRow.totInvoiceAmount" class=" control-label">Total Invoice Amount
													 </label>  <input type="text" autocomplete="off"
													onclick="numbersonly(this);addtax();"
													name="trainOrderRow.totInvoiceAmount" id="invoiceamount"
													class="form-control input-sm cusValidationforprice" placeholder="Invoice Amount"
													value="0" required>
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
											</div>
											
											<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="trainTravelRequestQuotation.remarks"
													class=" control-label"> Remarks </label>
												<textarea rows="1" cols="1" class="form-control input-sm"
													name="trainTravelRequestQuotation.remarks"
													placeholder="remarks" required></textarea>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
											</div>
											</div>
											</div>
														
 	
														
										</div>									
										
										</div>
										
						
									<div class="form-group text-center">
										<div class="col-xs-12 submitWrap text-center">
											<input type="hidden" name="tripId" id="tripId"
												value="${tripId}">
											<button type="submit" name="TrainTravelRequest" value=""
												id="trainexpensesubmitnew" class="btn btn-primary btn-lg">Create</button>
										</div>
									</div>
								</form>
							</div>
							<!-- </div> -->
						</div>
					</div>
					<!--/.panel-body -->
				</div>
				<!-- /.panel-group -->
		</div>
	</div>
	
	<!-- harsha added colapse ended -->
	</section>
	<!-- /.row -->
	<!-- Main row -->
	<!-- /.content -->
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
	  <script src="js/rmfeild.js"> </script> 
		<script type="text/javascript">
	
	
	function getEntity() {
		var entityId=$('#entity option:selected').val();
		var travelType='train';
		var ticketType = $('#ticketType option:selected').val();
		if(entityId== null){
			entityId=0;
		}
		$.ajax({
		    url : "getEntityTaxes?entityId="+entityId+"&travelType="+travelType,
		    type : "GET", 
		    dataType: "json",
		    success : function(data) {			    	
		    	$("#gstTax").val(data.commonGstTaxes.totalTax);
		    	$("#gstTaxPer").text(data.commonGstTaxes.totalTax);
		    	if(ticketType === 'normal'){
		    		$("#gstTaxAmount").val(data.commonGstTaxes.totalTax*(data.commonGstTaxes.managementFee/100));
		    	}
		    	else if(ticketType === 'tatkal'){
		    		$("#gstTaxAmount").val(data.commonGstTaxes.totalTax*($("#managementFeeTatkal").val()/100));
		    	}
		    	else{
		    		$("#gstTaxAmount").val(data.commonGstTaxes.totalTax*(data.commonGstTaxes.managementFee/100));
		    	}
		    		
		    }
		});
	}
	</script>
	<script type="text/javascript">
	$(document).ready(function() {
		var managementFee=${trainGstTaxConfig.managementFee};
		var totalGstAmount=${totalGstTaxPer};
		var total=parseFloat(totalGstAmount)*parseFloat(managementFee/100);
	$("#gstTaxAmount").val(total);
	$("#gstTaxPer").text(totalGstAmount);
	
	});
	</script>
	
		<script>
		
		
		$(document).ready(function () {
			
		      jQuery.validator.addMethod("baseFareprice",function(value) {
	            var startprice = 1;
	            return startprice < parseFloat($('#baseFare').val());
	          }, "please add minimum base fare");
	    
	            jQuery.validator.classRuleSettings.baseFareprice = { baseFareprice: true };
		
		           $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
		                return this.optional(element) || /^[a-zA-Z0-9_.]+$/i.test(value);
		            }, "This field cannot have symbols.");

		            $.validator.addMethod("cusValidationAlphaName",function(value,element){
		                return this.optional(element) || /^[a-zA-Z_ ]+$/i.test(value);
		            },"This field cannot have numbers and symbols."); 
		            $.validator.addMethod("cusValidationforprice",function(value,element){
		                return this.optional(element) || /^[0-9.]+$/i.test(value);
		            },"This field cannot have Char and symbols.");

		           
		    $("#trainexpensesFormId").validate({
		        rules: {
		            "name": {
		                required: true,
		                minlength: 5
		            },
		            "email": {
		                required: true,
		                email: true
		            }
		        },
		        messages: {
		            "name": {
		                required: "Please, enter a name"
		            },
		            "email": {
		                required: "Please, enter an email",
		                email: "Email is invalid"
		            }
		        },
		        submitHandler: function (form) { // for demo
		           // alert('valid form submitted'); // for demo
		             console.log("trying")
		            return false; // for demo
		        },highlight: function(element, errorClass, validClass) { 
		            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
		            $(element).addClass(errorClass).removeClass(validClass);
		            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		          },
		          success: function(element) {
		            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
		         element.closest('.form-group').removeClass('has-error').addClass('has-success');
		            $(element).remove();
		          }
		    });
		    
		     $('#trainexpensesubmitnew').click(function() {
		        $("#trainexpensesFormId").valid();
		        console.log($("#trainexpensesFormId").valid())
		         var baseFare = $('#baseFare').val();
		        console.log();
		        if($("#trainexpensesFormId").valid()){
		        	if(baseFare=='' || baseFare==0){
		   			 alert("Price Breakup BaseFare is not added. Please Add price to continue");
		   		  }else{
		   			document.getElementById("trainexpensesFormId").submit(); 
		   		  }
		        	
		        }
		    });

		});
		
		
		
	function add(currentObj) { 
		 
		var $rooms = $(".booking-details #passegerDetails:first").clone(); 
		$rooms.find("input").val('');
		$('.additionalpackage').append($rooms).insertAfter("#passegerDetails:last"); 
		var noOfRooms = $(".additionalpackage").find('#passegerDetails').length;  
		 
		var managementFeesToCalc=  $('#managementFeesForSend option:selected').val();;
	  var totalPassenger = $('#totalNumberOfPassenger').val(); 
		totalManagementFee=managementFeesToCalc*totalPassenger;
		$('#managementFees').val(totalManagementFee);
		$('#quoteamount').val(totalManagementFee);
		
		 for (var i = noOfRooms; i <= noOfRooms; i++) {
				$rooms.find(".companyset-heading b").text("Passenger Details" + (i+1)); 
				$rooms.find("#passegerDetails").attr("id", "passegerDetails" + (i)); 
			  $rooms.find("h4.panel-title a[href='#collapseinside']").attr("href", "#collapseinside" + (i));
				$rooms.find("div#collapseinside").attr("id", "collapseinside" + (i));    
				$rooms.find(".inner-compreg .form-group input[name^=trainOrderCustomerList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				});
				
				$rooms.find(".inner-compreg .form-group select[name^=trainOrderCustomerList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				});
				
				$rooms.find(".inner-compreg .detailswithRM .form-group input").each(function() {
					var id = $(this).attr('id'); 
					id = id+i;
					$(this).attr('id', id);
					 
				});
				$rooms.find(".pricebreakup a").text(" Price Breakup(s)" + (i+1)); 
				$rooms.find(".pricebreakup a[href='#priceFilters']").attr("href", "#priceFilters" + (i));
				$rooms.find("div#priceFilters").attr("id", "priceFilters" + (i)); 
				$rooms.find(".filmore a").attr("id", "fil" + (i+1));
				$rooms.find(".price-details .form-group input").val('0');
				$rooms.find(".price-details .form-group input[name^=trainOrderCustomerList]").each(function() {
					
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				});
				
				
				 var corporateID = $('#companyRole').val();  
 				 var iscorID=$("#companyId").val();  
 				 var url = ''; 
 				/* url : 'resources/Apicredential.properties', */

				 $.ajax({
					 url : 'resources/Apicredential.properties',
					   type : 'GET',
					   dataType : 'json',
					   success : function(response) {
						   url = response.ApiURL;
					    console.log("ApiURL",url);
					   }, 
					   error : function(xhr, status, error) {
					   },complete: function(){
					        }

					  });
				 if(corporateID=="true") {
				 $("#firstName"+i).autocomplete({
				     source: function (request, response) { 
				          $.ajax({
				        	  url: url+"employee/list?companyid="+iscorID,
				              dataType : "json",
				              data: request,
				              success: function (data) { 
				             	   response(data);
				             	   response($.map(data, function(item) {
				                        return {
				                            label: item.firstName,
				                            value: item.firstName,
				                            lastName: item.lastName, 
				                            id:item.id, 
				                            data:item
				                        } 
				             	   }));
				             	   
				              }
				          });
				     },
				     select: function (event, ui) {
				     	 console.dir(ui);
				          $('#firstName'+(i-1)).val(ui.item.firstName);  
				          $('#lastName'+(i-1)).val(ui.item.lastName); 
				          console.log("checking last name",$('#firstName'+(i-1)).val(ui.item.firstName));
				         console.log("checking last name",$('#lastName'+(i-1)).val(ui.item.lastName));
				         console.log("ui.item.lastName",ui.item.lastName);
				          
				     }, 
				      change: function (event, ui) { console.log("uidddddd",ui); 
				      $.ajax({ 
				    	  url: url+"employee/empdetailsById?id="+ui.item.id,
				          dataType: "json",  
				          success: function(datas) {
				             console.log(datas);  
				             if(datas.rmDataListDetails.department!=undefined || datas.rmDataListDetails.department==""){
				            	 $('#rmDepartment'+(i-1)).val(datas.rmDataListDetails.department); 
				            }
				             if(datas.rmDataListDetails.empCode!=undefined || datas.rmDataListDetails.empCode==""){
				            	 $('#rmempCode'+(i-1)).val(datas.rmDataListDetails.empCode); 
				            }
				             if(datas.rmDataListDetails.costCenter!=undefined || datas.rmDataListDetails.costCenter==""){
				             	 $('#rmcostCenter'+(i-1)).val(datas.rmDataListDetails.costCenter); 
				             }
				             
				             if(datas.rmDataListDetails.approverName!=undefined || datas.rmDataListDetails.approverName==""){
				            	 $('#rmapproverName'+(i-1)).val(datas.rmDataListDetails.approverName); 
				            }
				             if(datas.rmDataListDetails.location!=undefined || datas.rmDataListDetails.location==""){
				            	 $('#rmlocation'+(i-1)).val(datas.rmDataListDetails.location); 
				            }
				             if(datas.rmDataListDetails.trNumber!=undefined || datas.rmDataListDetails.trNumber==""){
				            	 $('#rmtrNumber'+(i-1)).val(datas.rmDataListDetails.trNumber); 
				            }
				             if(datas.rmDataListDetails.bussinessUnit!=undefined || datas.rmDataListDetails.bussinessUnit==""){
				            	 $('#rmbussinessUnit'+(i-1)).val(datas.rmDataListDetails.bussinessUnit); 
				            }
				             
				             if(datas.rmDataListDetails.projectCode!=undefined || datas.rmDataListDetails.projectCode==""){
				               	 $('#rmprojectCode'+(i-1)).val(datas.rmDataListDetails.projectCode); 
				               }
				             
				             if(datas.rmDataListDetails.reasonForTravel!=undefined || datas.rmDataListDetails.reasonForTravel==""){
				               	 $('#rmreasonForTravel'+(i-1)).val(datas.rmDataListDetails.reasonForTravel); 
				               }
				               if(datas.rmDataListDetails.billNonBill!=undefined || datas.rmDataListDetails.billNonBill==""){
				                	 $('#rmbillNonBill'+(i-1)).val(datas.rmDataListDetails.billNonBill); 
				                } 
				             
				          },
				          error: function(datas) { 
				            console.log(datas);
				          }
				        
				        }); 
				     } 
				 }); 
				 } 
				
				
				
		 } 
		 
			if ((noOfRooms) > 0) {
				$('.remove_field').removeAttr('disabled');
			} else {
				$('.remove_field').attr('disabled', 'disabled');
			}
			var count=$('#passengercountmine').val();
			if(count>0){
				count=noOfRooms+1;
				$ ('#passengercountmine').val(count);  
			}
			else{
				$ ('#passengercountmine').val(count);
			} 
		}
	

		function remove_field(currentObj) {
			var parentid = $("#" + currentObj.id).parent().parent().attr('class'); 
			 
			   var noOfRooms = $("." + parentid).find('.additionalpackage .companysetup ').length;
			   

			 $('.' + parentid).find('.additionalpackage .companysetup:last-child').remove();
			if (noOfRooms <= 1) {
				$('.remove_field').attr('disabled', 'disabled');
			} else {
				$('.remove_field').removeAttr('disabled');
				 
			}
				noOfRooms--; 
			 calculateServiceTax();
			 calculateBaseTax();
			 calculateMarkupTax();
			 calculateTaxTax()
			 addtax();
		} 
	
	
	
	
	 $(document).on("keyup",".price-details input#supplierFare" ,function(){  
	    	calculateServiceTax();
     });
	 $(document).on("keyup",".price-details input#baseFare" ,function(){  
	    	calculateBaseTax();
     });
	 $(document).on("keyup",".price-details input#taxFare" ,function(){  
	    	calculateTaxTax();
     });
	 $(document).on("keyup",".price-details input#markupFare" ,function(){  
	    	calculateMarkupTax();
     });
	 
	 
	 
	 function calculateServiceTax() {
	        var sum = 0;  
	        $(".price-details #supplierFare").each(function() {
	            if (!isNaN(this.value) && this.value.length != 0) {
	                sum += parseFloat(this.value);
	            }
	        }); 
	        $('#supplierPrice').val(sum.toFixed(2));
	        addtax();
	    }
	 
	 function calculateBaseTax() {
	        var sum = 0;  
	        $(" .price-details #baseFare").each(function() {
	            if (!isNaN(this.value) && this.value.length != 0) {
	                sum += parseFloat(this.value);
	            }
	        }); 
	        $('#basePrice').val(sum.toFixed(2));
	        addtax();
	    }
	 
	 function calculateTaxTax() {
	        var sum = 0;  
	        $(" .price-details #taxFare").each(function() {
	            if (!isNaN(this.value) && this.value.length != 0) {
	                sum += parseFloat(this.value);
	            }
	        }); 
	        $('#othertaxes').val(sum.toFixed(2));
	        addtax();
	    }
	 
	 function calculateMarkupTax() {
	        var sum = 0;  
	        $(" .price-details #markupFare").each(function() {
	            if (!isNaN(this.value) && this.value.length != 0) {
	                sum += parseFloat(this.value);
	            }
	        }); 
	        $('#markUp').val(sum.toFixed(2));
	        addtax();
	    }
	</script>
	<script type="text/javascript">
	
	var normalGstAmount=${totalGstAmount};
	var tatkalGstAmount=${totalGstAmountTatkal};
		$('#traveldateforvisa').datepicker({
			dateFormat : 'dd-mm-yy',
			changeMonth: true,  
		    changeYear:true,
		}).on('change', function() {
            $(this).valid();  // triggers the validation test
        });
		$('#trainBookingDate').datepicker({
			dateFormat : 'dd-mm-yy',
			changeMonth: true,  
		    changeYear:true,
		}).datepicker("setDate", new Date());
		
		
		$("#ticketType").change(function() {
			var val = $(this).val();
			if (val === "tatkal") {
				$(".mmtgen").hide();
				$(".mmtkal").show();
				$("#gstTax").val(tatkalGstAmount);
			} else {
				$(".mmtgen").show();
				$(".mmtkal").hide();
				$("#gstTax").val(normalGstAmount);
			}
		  });
		
		/* $('.price-details').on('keyup input', function () {
			   addtax(); 
			  }); */
		function addtax() {
			var baseamountinput = $("#basePrice").val();
			var markupamountinput = $("#markUp").val();
			var otherTaxesinput = $("#othertaxes").val();
			var managementfeeinput = 0;
			
			if ($("#ticketType").val() == "tatkal") {
				managementfeeinput=$("#managementFeeTatkal").val();
			} else {
				managementfeeinput=$("#managementFee").val();
			}
			console.log(managementfeeinput);
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
			//var totalamountinput = $("#totalamount").val();
			var totalamountfortax = totalamount - parseInt(conveniencefeeinput);
			var basicTax = $("#basictax").val();
			var sbcTax = $("#sbc").val();
			var kkcTax = $("#kkc").val();
			var totalTax = $("#total").val();
			var depositBalance =${userWallet.depositBalance};
			var walletBalance =${userWallet.walletbalance};
			var basetaxcal = (parseInt(managementfeeinput) / parseInt(100))
					* parseInt(basicTax);
			var sbc = (parseInt(managementfeeinput) / parseInt(100))
					* parseInt(sbcTax);
			var kkc = (parseInt(managementfeeinput) / parseInt(100))
					* parseInt(kkcTax);
			
			var totalTax = (parseInt(managementfeeinput) / parseInt(100))
					* parseInt(totalTax);

			$("#serviceTax").val(Math.round(totalTax));
			$("#totalamount").val(totalamount);
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
							$(".mmtkal").hide();	
							$(
									"#supplierPrice , #processingFees , #markUp , #basePrice , #totalamount , #managementFee , #convenienceFee , #serviceTax , #othertaxes ,#managementFeeTatkal")
									.keydown(
											function(e) {
												var isModifierkeyPressed = (e.metaKey
														|| e.ctrlKey || e.shiftKey);
												var isCursorMoveOrDeleteAction = ([
														46, 8, 9, 37, 38, 39, 40,
														110, 190 ]
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
	 
	

</body>
</html>