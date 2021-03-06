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
					<!-- harsha added colapse -->
					<div class="col-sm-12">
						<div class="panel-group" id="accordion">
							<!-- <div id="collapseTen" class="panel-collapse collapse"> -->
							<div class="panel-body">
								<form action="createTrainTravelRequest" method="post" onsubmit="return validateForm()"
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
										<div class="booking-details">
										
										<div class="companysetup clearfix"> 
											<div class="companyset-heading"> 
												<div class="companyset-icon"> 
														 <i class="fa fa-book fa-2x"></i>  
														 <b>Booking Details</b>  
												</div> 
											</div>
											<div class="inner-compreg clearfix"> 	
											
											
								 
									<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
										<div class="col-sm-2">
											<div class="form-group">
												<label for="travelDate" class=" control-label">
													Travel Request No </label> <input type="text" autocomplete="off"
													name="trainTravelRequest.trNo"
													class="form-control input-sm" id="travelvisaDate" value=""
													required placeholder="Enter Travel Number ">
											</div>
										</div>
										</s:if>
										<s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}">
												<div class="col-sm-2">
														<div class="form-group">
															<label for="trainTravelRequest.entity" class=" control-label">
																Company Entity </label> 
																<select class="form-control input-sm" onchange="getEntity()" name="trainOrderRow.companyEntityId" id="entity" >
																	 <option value="" selected="selected">Select Entity</option>
																		 	<s:iterator value="%{#session.Company.companyEntities}">
																				<option value="<s:property value="companyEntityId"/>" >
																				<s:property value="CompanyEntityName" /></option>
																			</s:iterator>
																	</select>  					 
														</div>
													</div>
													</s:if>
										<div class="col-sm-2">
											<div class="form-group">
												<label for="travelDate" class=" control-label">Train
													Number</label> <input type="number" autocomplete="off"
													name="trainTravelRequestQuotation.trainNumber"
													class="form-control input-sm" id="travelvisaDate" value=""
													required placeholder="Enter Train Number ">
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<label for="trainOrderRow.confirmationNumber"
													class=" control-label">Confirmation Number</label> <input
													type="text" autocomplete="off"
													name="trainOrderRow.confirmationNumber"
													class="form-control input-sm" id="travelvisaDate" value=""
													required placeholder="Enter Confirmation Number ">
											</div>
										</div>
									 
										<div class="col-sm-2">
											<div class="form-group">
												<label for="trainTravelRequest.title" class="control-label">Title</label> <select
													class="form-control input-sm" required="required"
													name="trainTravelRequest.title">
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
												<label for="trainTravelRequest.firstName" class="control-label">FirstName</label>
												<input type="text" autocomplete="off"
													name="trainTravelRequest.firstName"
													class="form-control input-sm" required="required"
													placeholder="First Name">
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<label for="trainTravelRequest.lastName" class="control-label">LastName </label> <input
													type="text" autocomplete="off"
													name="trainTravelRequest.lastName"
													class="form-control input-sm" required="required"
													placeholder="Last Name">
											</div>
										</div>
									 
										<div class="col-sm-2">
											<div class="form-group">
												<label for="trainTravelRequestQuotation.fromlocation"
													class=" control-label"> From Location </label> <input
													type="text" autocomplete="off"
													name=trainTravelRequestQuotation.fromlocation
													class="form-control input-sm" id="travelvisaDate" value=""
													required placeholder="Enter From Location ">
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<label for="trainTravelRequestQuotation.tolocation"
													class=" control-label">To Location</label> <input
													type="text" autocomplete="off"
													name="trainTravelRequestQuotation.tolocation"
													class="form-control input-sm" id="travelvisaDate" value=""
													required placeholder="Enter To Location ">
											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group">
												<label for="trainOrderRow.ticketType" class="control-label">Ticket Type</label> <select
													class="form-control input-sm" onchange="getEntity()" required="required"
													name="trainOrderRow.ticketType" id="ticketType">
													<option value="tatkal" >Tatkal</option>
													<option value="normal" selected="selected" >Normal</option>
												</select>
											</div>
										</div>
										 
										<div class="col-sm-2">
												<div class="form-group">
													<label for="trainOrderRow.supplierName">Supplier Name</label> 
													<select class="form-control input-sm" name="trainOrderRow.supplierName" id="supplierName" required>
									<option value="" selected="selected">select Supplier</option>
								<s:iterator value="ApiProviders">
									<option value="<s:property value="vendorName"/>"><s:property value="vendorName" /></option>
								</s:iterator>
								 </select>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group">
													<label for="trainOrderRow.trainBookingDate"
														class=" control-label"> Booking Date </label> <input
														type="text" autocomplete="off" readonly="readonly"
														name="trainOrderRow.trainBookingDate"
														onchange="validateDateFormat(this)"
														class="form-control input-sm" id="trainBookingDate" value=""
														required placeholder="Enter Booking Date">
												</div>
											</div>
										<div class="col-sm-2">
											<div class="form-group">
												<label for="trainTravelRequestQuotation.traveldatetemp"
													class=" control-label">Travel Date</label> <input
													type="text" autocomplete="off" readonly="readonly"
													onchange="validateDateFormat(this)"
													name="trainTravelRequestQuotation.traveldatetemp"
													class="form-control input-sm" id="traveldateforvisa"
													value="" required placeholder="Enter Travel Date ">
											</div>
										</div>
									
										
										 
													
					  <c:if test="${fieldName[0]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class="control-label"> ${fieldName[0]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField1"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField1}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[1]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class="control-label"> ${fieldName[1]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField2"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField2}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[2]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class="control-label"> ${fieldName[2]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField3"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField3}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[3]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class="control-label"> ${fieldName[3]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField4"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField4}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[4]!=null}">
								<div class="col-sm-2">
									<div class="form-group" >
										<label for="empCode" class="control-label"> ${fieldName[4]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField5"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField5}" >
									</div>
								</div>
							</c:if>
						 
											
											<c:if test="${rmConfigModel.empCode}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode" class="control-label"> Emp Code</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.empCode"
															class="form-control input-sm" required="required"
															placeholder="Enter emp Code Details" value="<s:property value="configTripDetailsModel.empCode"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.department}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode" class="control-label"> Department</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.department"
															class="form-control input-sm" required="required"
															placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.department"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.costCenter}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode" class="control-label">Cost Center</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.costCenter"
															class="form-control input-sm" required="required"
															placeholder="Enter costCenter Details" value="<s:property value="configTripDetailsModel.costCenter"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.approverName}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode" class="control-label">Approver Name</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.approverName"
															class="form-control input-sm" required="required"
															placeholder="Enter approverName Details" value="<s:property value="configTripDetailsModel.approverName"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.location}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode" class="control-label">location</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.location"
															class="form-control input-sm" required="required"
															placeholder="Enter location Details" value="<s:property value="configTripDetailsModel.location"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.bussinessUnit}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode" class="control-label">Bussiness Unit</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.bussinessUnit"
															class="form-control input-sm" required="required"
															placeholder="Enter bussiness Unit Details" value="<s:property value="configTripDetailsModel.bussinessUnit"/>">
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
															placeholder="Enter projectCode Details" value="<s:property value="configTripDetailsModel.projectCode"/>">
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
															placeholder="Enter reasonForTravel Details" value="<s:property value="configTripDetailsModel.reasonForTravel"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.billNonBill}">
											<div class="col-sm-2">
													<div class="form-group" >
														<label for="billNonBill" class="control-label">Billing/Non Billing</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.billNonBill"
															class="form-control input-sm" required="required"
															placeholder="Enter billNonBill Details" value="<s:property value="configTripDetailsModel.billNonBill"/>">
													</div>
												</div>
											</c:if>
											 
											 </div>
											
											
												
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
													class="control-label">Supplier Price</label> <input
													type="text" autocomplete="off" onchange="numbersonly(this)"
													name="trainOrderRow.supplierPrice" id="supplierPrice"
													class="form-control input-sm"
													placeholder="Enter Supplier Price" value="0" required>
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="hotel address1" class="control-label">Base
													Price</label> <input type="text" autocomplete="off"
													onchange="numbersonly(this)" name="trainOrderRow.basePrice"
													id="basePrice" class="form-control input-sm"
													placeholder="Enter Base Price" value="0" required>
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="trainOrderRow.otherTaxes" class=" control-label">Other
													Taxes </label> <input type="text" autocomplete="off"
													name="trainOrderRow.otherTaxes" id="othertaxes"
													class="form-control input-sm" value="0"
													onchange="numbersonly(this)" placeholder="Other Taxes"
													required>
											</div>
											</div>
											<div class="col-sm-2">																	 
											<div class="form-group">
												<label for="managementFee" class=" control-label">Mark
													Up</label> <input type="text" autocomplete="off"
													name="trainOrderRow.markUp" class="form-control input-sm"
													id="markUp" value="0" required onchange="numbersonly(this)"
													placeholder="Enter markup">
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
											<div class="form-group">
												<label for="trainOrderRow.totalAmount"
													class=" control-label"><span style="color:red"> *</span>
													Total Amount Without 
													<c:if test="${taxType eq 'GST'}">Gst</c:if>
													<c:if test="${taxType eq 'Service'}">Service</c:if>
													Tax</label> <input
													type="text" autocomplete="off"
													name="trainOrderRow.totalAmount" id="totalamount"
													onclick="numbersonly(this);addtax();"
													class="form-control input-sm" placeholder="Total Amount"
													value="0" required>
											</div> 
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="carOrderRow.totInvoiceAmount" class=" control-label">Total Invoice Amount
													 </label>  <input type="text" autocomplete="off"
													onclick="numbersonly(this);addtax();"
													name="trainOrderRow.totInvoiceAmount" id="invoiceamount"
													class="form-control input-sm" placeholder="Invoice Amount"
													value="0" required>
											</div>
											</div>
											
											<div class="col-sm-2">
											<div class="form-group">
												<label for="trainTravelRequestQuotation.remarks"
													class=" control-label"> Remarks </label>
												<textarea rows="1" cols="1" class="form-control input-sm"
													name="trainTravelRequestQuotation.remarks"
													placeholder="remarks" required></textarea>
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
	<script type="text/javascript">
	
	var normalGstAmount=${totalGstAmount};
	var tatkalGstAmount=${totalGstAmountTatkal};
		$('#traveldateforvisa').datepicker({
			dateFormat : 'dd-mm-yy',
			changeMonth: true,  
		    changeYear:true,
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
		
		$('.price-details').on('keyup input', function () {
			   addtax(); 
			  });
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
	<script type="text/javascript">
	
	
	/*  function validateForm(){
	var totalBaseFare=Math.round(parseFloat($('#totalamount').val())*100)/100;
	var totalSupplierPrice=Math.round(parseFloat($('#supplierPrice').val())*100)/100;
	
	if(totalBaseFare<totalSupplierPrice){
		alert('Please check supplier price is more than total booking amount');
		return false;
	}
	else{
		$('#trainexpensesFormId').submit(validateForm);
		
		
	}
		
	} */
	
	</script>
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
</body>
</html>