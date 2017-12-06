<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Register</title>

<%-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
	<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "getHotelRequestTravelQuotationList?hotelQuotationRequestId=${hotelQuotationRequestId}";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>

<style type="text/css">
.error {
    color:red;
}
.valid {
    color:green;
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
		<div class="col-sm-4 clearfix pull-left">
		<h3>Hotel Quotation Edit</h3>
		</div>
		<div class="col-sm-8 clearfix pull-right " data-placement="top">
		<div class="row">
		<div class="col-sm-5 clearfix pull-left " data-placement="top">
				<a href="hotelTravelRequestEdit?id=${hotelQuotation.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-edit"></i> Travel Request 
				</a>
				<%-- <a href="hotelOrderQuotationView?id=${hotelQuotation.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-plus"></i> Travel Request 
				</a> --%>
				
				<a href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-plus"></i> Quotation
				</a>
				<a href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-list"></i> Quotations
				</a>
		</div>
		<div class="col-sm-4 clearfix " data-placement="top">		
				<a href="goTrips"
					class="btn btn-top-link btn-xs" >
					 All Trips
				</a>
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  title="Check Hotel Trips">
					 Hotel Trips
				</a>
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs"  title="Check Flight Trips">
					 Flight Trips
				</a>
			</div>		
			<div class="col-sm-3 clearfix pull-right" data-placement="top">
				<div class="myDropdown">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Create Trip
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				    <li><a href="goHotelTravelRequest">Hotel</a></li>
				    <li><a href="goFlightTravelRequest">Flight</a></li>
				  </ul>
				</div>
			</div>	  
			</div>			
			</div>
		</div>
		</section>	
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<%-- <div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div> --%>
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
			<form action="HoteltQuotationUpdate" method="post" id="myForm"
				class="form-horizontal" name="myForm">
					<input type="hidden"  id="cityCode1"  name="hotelQuotationList[0].cityCode" value="${hotelQuotation.cityCode}">
				<input type="hidden" name="hotelQuotationRequestId"
					value="<s:property value="%{hotelQuotationRequestId}"/>">
					<input type="hidden" name="hotelQuotationList[0].id"
					value="<s:property value="%{hotelQuotation.id}"/>">
					<input type="hidden" name="hotelQuotationList[0].bookingMode" id="bookingMode" value="${hotelQuotation.bookingMode}">
					<input type="hidden" name="hotelQuotationList[0].additionalData" id="aditionalSchema" value="${hotelQuotation.additionalData}">
					
				<div class="col-sm-12">
					<div class="support">
						<div class="expand" id="support">
							<!-- level1 -->
							<div class="level1">
								<div id="level1">
									<div class="well">
									  <div class="col-sm-6">
											<%-- <p class="h5"><strong><span style="margin-left: -15px;font-size: 13px;margin-top: 20px;"     id="label" class="pull-left"></span></strong></p> --%>
										</div>  
										<div class="col-sm-6">
											<a href="#addSchema"
												class="btn btn-success btn-xs pull-right " onclick="mod();"
												data-toggle="modal"> <i class="fa fa-plus-circle"></i>
												Add More
											</a>
										</div>
											 
												 <div class="form-group ui-state-default" style="width: 100%">
												<label class="col-sm-2 control-label">No of night(s) selected :</label>
												<div class="col-sm-8">
													<input type="text" id="label"  disabled="disabled"   class="form-control input-sm datepicker_recurring_start" >
												</div>
												</div> 
												 
										<div id="sortable" class="sort">
											<s:iterator value="hotelQuotationSchemas" status="rowCount">
											<s:if test="fieldName=='checkIn'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">CheckInDate</label>
												<div class="col-sm-8">
													<input type="text" id="checkIn"
														class="form-control input-sm datepicker_recurring_start"
														name="hotelQuotationList[0].checkIn"
														placeholder="Check In Date"  value="${dataValue}"  readonly="readonly"   autocomplete="off" required>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='checkInTime'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">CheckInTime</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" required
														name="hotelQuotationList[0].checkInTime" id="checkintime"
														placeholder="CheckIn Time" value="<s:property value="hotelQuotation.checkInTime"/>"     autocomplete="off"  >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fixedPosition=='dynamic'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">${displayName}</label>
												<div class="col-sm-8">
													<input type="${dataType}" class="form-control input-sm"
														name="hotelQuotationList[0].${fieldName}"
														placeholder="${displayName}" value="${dataValue}"   autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='checkOut'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">CheckOutDate</label>
												<div class="col-sm-8">
													<input type="text" id="checkOut"
														class="form-control input-sm datepicker_recurring_start"
														name="hotelQuotationList[0].checkOut"
														placeholder="Check Out Date" value="${dataValue}"  readonly="readonly"    autocomplete="off" required>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>


											<s:if test="fieldName=='checkOutTime'">	
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">CheckOutTime</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" id="checkouttime"
														name="hotelQuotationList[0].checkOutTime" required
														placeholder="CheckOut Time"  value="${dataValue}"     autocomplete="off"  >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>		
											 
											<s:if test="fieldName=='roomCount'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Room Count</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm" required
														name="hotelQuotationList[0].roomCount" id="roomCount"
														placeholder="Room Count" value="${dataValue}"      autocomplete="off" value=1>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='adultCount'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Adult Count</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm" required
														name="hotelQuotationList[0].adultCount" id="adultCount"
														placeholder="Adult Count"   value="${dataValue}"     autocomplete="off" value=1>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='childCount'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Child Count</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm" required
														name="hotelQuotationList[0].childCount" id="childCount"
														placeholder="Child Count" value="${dataValue}"    autocomplete="off" value=0>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>	
											<s:if test="fieldName=='roomRatePerNight'">

											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">RoomRate/Night(${hotelQuotation.hotelTravelRequest.currency})</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].roomRatePerNight" required
														placeholder="Room Rate Per Night"  value="${dataValue}"      autocomplete="off"
														>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='hotelName'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">HotelName</label>
												<div class="col-sm-8">
													<!--  -->
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].hotelName" required
														placeholder="hotelName" autocomplete="off" value="${dataValue}" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='hotelCategory'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Hotel Category</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].hotelCategory" required
														placeholder="Hotel Category"  value="${dataValue}"   autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='hotelAddress'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Hotel Address</label>
												<div class="col-sm-8">

													<textarea rows="2" cols="2" class="form-control input-sm"
														name="hotelQuotationList[0].hotelAddress" required
														placeholder="Hotel Address" >${dataValue}</textarea>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='hotelCity'">
											<div class="form-group ui-state-default" style="width: 100%;"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Hotel City</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" id="hotelCity3"
														name="hotelQuotationList[0].hotelCity" 
														placeholder="Hotel City"  value="${dataValue}"   autocomplete="off"  required>
														
														
														
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<%-- <s:if test="fieldName=='hotelCountry'">

											  <div class="form-group ui-state-default" style="width: 100%;"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Hotel Country</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" 
														name="hotelQuotationList[0].hotelCountry">
														<s:if test="dataValue!=null">
														<option value="${dataValue}" selected="selected">${dataValue}</option>
														</s:if>
														<s:else>
														<option value="" selected="selected">Select
															Country</option>
														</s:else>
														
														<s:iterator value="countryList">
															<option value="<s:property value="c_name"/>"><s:property
																	value="c_name" /></option>
														</s:iterator>
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>  
											</s:if> --%>
											<s:if test="fieldName=='projectAddress'">

											<div class="form-group ui-state-default" style="width: 100%;"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Project Address</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].projectAddress"  required
														placeholder="Project Address"  value="${dataValue}"   autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='distanceFromWork'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Distance from Work</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"  required
														name="hotelQuotationList[0].distanceFromWork"
														placeholder="Distance From Work" value="${dataValue}"   autocomplete="off"
														>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='roomType'">

											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Room Type</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].roomType" required
														placeholder="Room Type" value="${dataValue}"   autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='availablePaymentOptionList'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Payment Option(s) </label>
												<div class="col-sm-8">
												<input type="hidden" value="${hotelQuotation.paymentOptions.id}" name="hotelQuotationList[0].paymentOptions.id"  >
													<select class="form-control input-sm"
														name="hotelQuotationList[0].availablePaymentOptionList"
														multiple="multiple" >
														 <s:if test="hotelQuotation.paymentOptions.isPrePaid()&&hotelQuotation.paymentOptions.isPostPaid()&&hotelQuotation.paymentOptions.isPayAtHotel()">
														 <option value="PrePaid" selected="selected">PrePaid</option>
														<option value="PostPaid" selected="selected">PostPaid</option>
														<option value="PayAtHotel" selected="selected">PayAtHotel</option>
														 </s:if>
														<s:elseif test="hotelQuotation.paymentOptions.isPostPaid()&&hotelQuotation.paymentOptions.isPayAtHotel()">
														 <option value="PrePaid">PrePaid</option>
														<option value="PostPaid" selected="selected">PostPaid</option>
														<option value="PayAtHotel" selected="selected">PayAtHotel</option>
														 </s:elseif>
														 <s:elseif test="hotelQuotation.paymentOptions.isPrePaid()&&hotelQuotation.paymentOptions.isPayAtHotel()">
														 <option value="PrePaid"  selected="selected">PrePaid</option>
														<!-- <option value="PostPaid">PostPaid</option> -->
														<option value="PayAtHotel" selected="selected">PayAtHotel</option>
														 </s:elseif>
														  <s:elseif test="hotelQuotation.paymentOptions.isPrePaid()&&hotelQuotation.paymentOptions.isPostPaid()">
														 <option value="PrePaid" selected="selected">PrePaid</option>
														<option value="PostPaid" selected="selected">PostPaid</option>
														<option value="PayAtHotel">PayAtHotel</option>
														 </s:elseif>
														 <s:elseif test="hotelQuotation.paymentOptions.isPrePaid()">
														 <option value="PrePaid" selected="selected">PrePaid</option>
														<!-- <option value="PostPaid">PostPaid</option> -->
														<option value="PayAtHotel">PayAtHotel</option>
														 </s:elseif>
														 <s:elseif test="hotelQuotation.paymentOptions.isPostPaid()">
														 <option value="PrePaid">PrePaid</option>
														<option value="PostPaid" selected="selected">PostPaid</option>
														<option value="PayAtHotel">PayAtHotel</option>
														 </s:elseif>
														 <s:elseif test="hotelQuotation.paymentOptions.isPayAtHotel()">
														 <option value="PrePaid">PrePaid</option>
													<!-- 	<option value="PostPaid">PostPaid</option> -->
														<option value="PayAtHotel" selected="selected">PayAtHotel</option>
														 </s:elseif>
														 <s:else>
														 <option value="PrePaid"  selected="selected">PrePaid</option>
														<!-- <option value="PostPaid">PostPaid</option> -->
														<option value="PayAtHotel">PayAtHotel</option>
														 </s:else>
														 
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='taxes'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Taxes</label>
												<div class="col-sm-8">

													<select class="form-control input-sm"
														name="hotelQuotationList[0].taxes">
														<s:if test="dataValue=='yes'">
														 <option selected="selected"  value="yes">YES</option>
														 	<option  value="no">NO</option>
														
														</s:if>
														<s:elseif test="dataValue=='no'">
														<option value="no" selected="selected">NO</option>
														<option  value="yes">YES</option>
														</s:elseif>
														<s:else>
														 <option selected="selected"  value="yes">YES</option>
														<option value="no">NO</option>
														</s:else>
													</select>

												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='breakfast'">

											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Breakfast</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="hotelQuotationList[0].breakfast">
														<s:if test="dataValue=='yes'">
														 <option selected="selected"  value="yes">YES</option>
														 <option value="no">NO</option>
														</s:if>
														<s:elseif test="dataValue=='no'">
														<option value="no" selected="selected">NO</option>
														<option   value="yes">YES</option>
														</s:elseif>
														<s:else>
														 <option selected="selected"  value="yes">YES</option>
														<option value="no">NO</option>
														</s:else>
														 
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='internet'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Internet</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="hotelQuotationList[0].internet">
														<s:if test="dataValue=='yes'">
														 <option selected="selected"  value="yes">YES</option>
														  <option   value="no">NO</option>
														</s:if>
														<s:elseif test="dataValue=='no'">
														<option value="no" selected="selected">NO</option>
														  <option   value="yes">YES</option>
														</s:elseif>
														<s:else>
														 <option selected="selected"  value="yes">YES</option>
														<option value="no">NO</option>
														</s:else>
														  
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='cancellationPolicy'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Cancellation Policy</label>
												<div class="col-sm-8">

													<textarea rows="2" cols="2" class="form-control input-sm"
														name="hotelQuotationList[0].cancellationPolicy" required
														placeholder="Cancellation Policy">${dataValue}</textarea>

												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											<s:if test="fieldName=='preferProperty'">


											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Prefer Property</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="hotelQuotationList[0].preferProperty">
														<s:if test="dataValue=='true'">
														 <option selected="selected"  value="true">YES</option>
														 <option value="false">NO</option>
														</s:if>
														<s:elseif test="dataValue=='false'">
														<option value="false" selected="selected">NO</option>
														 <option value="true">YES</option>
														</s:elseif>
														<s:else>
														 <option selected="selected"  value="true">YES</option>
														<option value="false">NO</option>
														</s:else>
														   
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
											
											</s:iterator>
 
										</div>
										<div class="form-group ui-state-default" style="width: 100%">
												<label class="col-sm-2 control-label">Message</label>
												<div class="col-sm-8">
													<textarea rows="2" cols="2" class="form-control input-sm"
														name="hotelQuotationHistory.message" required
														placeholder="Message" ></textarea>
												</div>
												</div>
												<div class="form-group ui-state-default" style="width: 100%">
												<label class="col-sm-2 control-label">Comments</label>
												<div class="col-sm-8">
													<textarea rows="2" cols="2" class="form-control input-sm"
														name="hotelQuotationHistory.comments" required
														placeholder="Comments" ></textarea>
												</div>
												</div>
									</div>
 											
									 
								</div>
							</div>
						</div>
					</div>
					<!--  support -->

				</div>
				<div class="form-group text-center">
					 
					<div class="col-xs-12 submitWrap text-center">
					
						<button type="button" class="btn btn-primary btn-lg" onclick="lload()">UPDATE</button>
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
											autocomplete="off"   >
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
			
			 
		</section>
	</div>
	<%@ include file="DashboardFooter.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	
     $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
          return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
      }, "This field cannot have symbols.");

      $.validator.addMethod("cusValidationAlphaName",function(value,element){
          return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
      },"This field cannot have numbers and symbols."); 
      $.validator.addMethod("cusValidationforprice",function(value,element){
          return this.optional(element) || /^[0-9.]+$/i.test(value);
      },"This field cannot have Char and symbols.");


$("#myForm").validate({
	 rules: {
         
         "email": {
             required: true,
             email: true
         }
     },
     messages: {
         "email": {
             required: "Please, enter an email",
             email: "Email is invalid"
         }
     },
    submitHandler: function (form) { 
        return false; 
    }
});

/* $('#but-up').click(function() {
	   if($("#laborexpensesFormId").valid())
	    	document.getElementById("laborexpensesFormId").submit();
	});  */
});
</script>  		 
<script src="js/city_code.js"></script>
	<script>
	var enableQuotationMove = ${hotelRequestTravelQuotationSize};
	console.log(enableQuotationMove);
	function addlist(){  
		var noOfQuation = $( "#level1" ).find('.well').length;  
		  var addQuatation = "";    
		  $('.package').html("");  
		  for (var i = 0; i < parseInt(noOfQuation); i++) {  
			  var index = i+1;  
			  var qPageNo= i+2;      
			  addQuatation += "<div class='well' id='id"+index+"'><p class='h4'>Option:"+qPageNo+"</p><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>HotelName</label><div class='col-sm-8'><input type='text' class='form-control input-sm' name='hotelQuotationList["+index+"].hotelName' placeholder='hotelName' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Hotel Category</label><div class='col-sm-8'><input type='text' class='form-control input-sm' name='hotelQuotationList["+index+"].hotelCategory' placeholder='Hotel Category' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Hotel Address</label><div class='col-sm-8'><textarea rows='2' cols='2' class='form-control input-sm' name='hotelQuotationList["+index+"].hotelAddress' placeholder='Hotel Address' required></textarea></div></div><div class='form-group' style='width: 100%''><label class='col-sm-2 control-label'>Hotel City</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].hotelCity'placeholder='Hotel City' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Hotel Country</label><div class='col-sm-8'><select class='form-control input-sm' required='required' name='hotelQuotationList["+index+"].hotelCountry'><option value='' selected='selected'>Select Country</option> <s:iterator value='countryList'> <option value='<s:property value='c_name'/>'><s:property value='c_name'/></option> </s:iterator> </select> </div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>ProjectAddress</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].projectAddress'placeholder='Project Address' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Distance from Work</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].distanceFromWork'placeholder='Distance From Work' autocomplete='off'required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Room Type</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].roomType'placeholder='Room Type' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>CheckInDate</label><div class='col-sm-8'><input type='text' id='checkIn"+index+"' class='form-control input-sm datepicker_recurring_start' name='hotelQuotationList["+index+"].checkIn' placeholder='Check In Date' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>CheckInTime</label><div class='col-sm-8'><input type='text' class='form-control input-sm' name='hotelQuotationList["+index+"].checkInTime' placeholder='CheckIn Time' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>CheckOutDate</label><div class='col-sm-8'><input type='text' id='checkOut"+index+"'class='form-control input-sm datepicker_recurring_start' name='hotelQuotationList["+index+"].checkOut' placeholder='Check Out Date' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>CheckOutTime</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].checkOutTime'placeholder='CheckOut Time' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Room Count</label><div class='col-sm-8'><input type='number' class='form-control input-sm'name='hotelQuotationList["+index+"].roomCount'placeholder='Room Count' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Adult Count</label><div class='col-sm-8'><input type='number' class='form-control input-sm'name='hotelQuotationList["+index+"].adultCount'placeholder='Adult Count' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Child Count</label><div class='col-sm-8'><input type='number' class='form-control input-sm'name='hotelQuotationList["+index+"].childCount'placeholder='Child Count' autocomplete='off' required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>RoomRate/Night</label><div class='col-sm-8'><input type='text' class='form-control input-sm'name='hotelQuotationList["+index+"].roomRatePerNight'placeholder='Room Rate Per Night' autocomplete='off'required></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Payment Option(s)</label><div class='col-sm-8'><select class='form-control input-sm'name='hotelQuotationList["+index+"].availablePaymentOptionList'multiple='multiple' required><option value=''>Please Select PaymentOption(s)</option><option value='PrePaid'>PrePaid</option><option value='PostPaid'>PostPaid</option><option value='PayAtHotel'>PayAtHotel</option></select></div></div> <div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Taxes</label><div class='col-sm-8'> <select class='form-control input-sm' name='hotelQuotationList["+index+"].taxes'><option value='yes'>YES</option><option value='no'>NO</option></select></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Breakfast</label><div class='col-sm-8'><select class='form-control input-sm'name='hotelQuotationList["+index+"].breakfast'><option selected='selected' value='yes'>YES</option><option value='no'>NO</option></select></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Internet</label><div class='col-sm-8'><select class='form-control input-sm'name='hotelQuotationList["+index+"].internet'><option selected='selected' value='yes'>YES</option><option value='no'>NO</option></select></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>Cancellation Policy</label><div class='col-sm-8'><textarea rows='2' cols='2' class='form-control input-sm'name='hotelQuotationList["+index+"].cancellationPolicy'placeholder='Cancellation Policy' required></textarea></div></div><div class='form-group' style='width: 100%'><label class='col-sm-2 control-label'>PreferProperty</label><div class='col-sm-8'><select class='form-control input-sm'name='hotelQuotationList["+index+"].preferProperty'><option selected='selected' value='true'>YES</option><option value='false'>NO</option></select></div></div></div>"; 
			  }  
	$('.package').html(addQuatation); 
	if((noOfQuation) > 0){ 
		$('.remove_field').removeAttr('disabled');  
		}  else{ 
			$('.remove_field').attr('disabled', 'disabled');  
			} 
	}
	function remove_field(){ 
		var noOfQuation = $( "#level1" ).find('.well').length; 
		$('.well:last-child').remove();    
		if(noOfQuation <= 1){     
			$('.remove_field').attr('disabled', 'disabled'); 
			}  else{ 
				$('.remove_field').removeAttr('disabled');  
				}   
		noOfQuation--;  
				}
	function clearQuotationSchema(){
		 $("#fieldName").val("");
	 	$("#positionNumber").val("");
		 }
	
	
	
</script>

<script>
$("#hotelCity3").autocomplete({
	source: function( request, response ) {
		 console.log("TEsting-----------------------");
		var matcher = new RegExp( '^' + $.ui.autocomplete.escapeRegex( request.term ), "gi" );
		response( $.grep( citylist, function( item ){
			//console.log(item);					
			var itemavailavle = item;					
			//itemavailavle = "dfdf";
			//process the item to get only name
			//return itemavailavle;
			return matcher.test( item );
			//return "dsdsd";
		}) );
	},
	select: function (event, ui) {
		
		var value = ui.item.value;					
		var citycode = citymap[ui.item.value];
		$('#childCount').focus();
		 console.log("##############3 selected city name   "+ui.item.value);
		 console.log("##############3 selected city id  "+citycode);
		$('#hotelCity2').val(ui.item.value);
		$('#cityCode1').val(citycode);
		//$('#citycode1').val(citycode);
		console.log("citycode-----------"+$('#cityCode1').val());
		 
	}

});







function  lload(){
	/* var inputs = $("#"+id).find("select, textarea, input");
	console.log(inputs); */
	//var z = $(this).children('label').text();
	//var schemaStruct="$$proertyName:type:value:posNum:pos:displayName$$"
	
	 if($("#myForm").valid()){
			var schemaStruct="<#proertyName(|)type(|)value(|)posNum(|)pos(|)displayName#>"
				var aditionalSchema = ""; 
				$('#sortable div.form-group').each(function(){
					var id = this.id;
					// dynamic data field 
					var fieldName=$("#fieldName").val();
					var dataType=$("#dataType").val();
					var positionNumber=$("#positionNumber").val();
					var fixedPosition=$("#fixedPosition").val();
					var displayName = $('#'+id+" "+'label').text();
					var type = "";
					var pos = "fixed";
					var input = $('#'+id+" "+'input').attr('name');
					var  select =$('#'+id+" "+'select').attr('name');
					var  textArea =$('#'+id+" "+'textarea').attr('name');
					var part="";
					var value =""; 
			  if(input!=null && input!="undefined"){
				if(input.indexOf(".")>=1){
					var splitName=input.split(".");
					part=splitName[1];
					pos = "fixed";
				 }
				else{
					part=input;
					pos = "dynamic";
				 }
				value = $('#'+id+" "+'input').val();
				 type = $('#'+id+" "+'input').attr('type');
				
			}
			if(select!=null && select!="undefined"){
				if(select.indexOf(".")>=1){
					var splitName=select.split(".");
					part=splitName[1];
					pos = "fixed";
				 }
				else
				{
					pos = "dynamic";
					part=select;
				 }
				value = $('#'+id+" "+'select').val();
				 type = "select";
				 
			}
			if(textArea!=null && textArea!="undefined"){
				if(textArea.indexOf(".")){
					var splitName=textArea.split(".");
					part=splitName[1];
					pos = "fixed";
				 }
				else{
					pos = "dynamic";
					part=textArea;
				 }
				type = "textarea";
				value = $('#'+id+" "+'textarea').val();
				 
			}
			 
			var schemaStructTemp=schemaStruct;
			schemaStructTemp = schemaStructTemp.replace("proertyName",part);
			schemaStructTemp = schemaStructTemp.replace("type",type);
			schemaStructTemp = schemaStructTemp.replace("value",value);
			schemaStructTemp = schemaStructTemp.replace("posNum",id);
			schemaStructTemp = schemaStructTemp.replace("pos",pos);
			schemaStructTemp = schemaStructTemp.replace("displayName",displayName);
			aditionalSchema +=schemaStructTemp;
			 
			});
				 console.debug(aditionalSchema);
			 $("#aditionalSchema").val(aditionalSchema);
			 document.getElementById("myForm").submit();
	 }
	
}

$('#yes').click(function() {
	
	if(($('#fieldName').val() != '') && ($('#dataType').val() != '')){		
		 $('#addSchema').modal('hide');
	}
});
</script>
	<script>
function mod(){
	
	var y = $('#sortable div.form-group').length;	
	return y;
}
function generateQuotationSchema(){
	var z = mod();
	 
	console.log(z);
	var fieldName=$("#fieldName").val();
	var dataType=$("#dataType").val();
	var positionNumber=$("#positionNumber").val();
	var fixedPosition=$("#fixedPosition").val();
	var addSchema=null;
	var checkSchema=$("#setSchema").val();
	var x =1;
	j = z+1;
	console.log("j-------"+j);
	 var max_fields      = 10; //maximum input boxes allowed
	    var wrapper   = $("#sortable"); //Fields wrapper
	if(fieldName!=''){
				 if(x<max_fields){
				  x++;
				  
				 /*  if(dataType='longText'){
					  $(wrapper).append('<div class="form-group ui-state-default" style="width: 100%" id="'+j+' "><label class="col-sm-2 control-label">'+fieldName+'</label><div class="col-sm-8"><textarea class="form-control input-sm"   name="'+fieldName+'"  placeholder="'+fieldName+'" required></textarea> </div><div class="col-sm-2 remove_field">Remove</div></div>'); 
				  } 
				  else   */
				   $(wrapper).append('<div class="form-group ui-state-default" style="width: 100%" id="'+j+'"><label class="col-sm-2 control-label">'+fieldName+'</label><div class="col-sm-8"><input type="'+dataType+'" class="form-control input-sm"  name="'+fieldName+'"    placeholder="'+fieldName+'" autocomplete="off" required></div><div class="col-sm-2 remove_field">Remove</div></div>');   
					
		    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
		        e.preventDefault(); $(this).parent('div').remove(); x--;
		    })
	 }
					
	 }
	    
	lload();	
	
}
</script>
	<%-- <script>
		$('body').on('focus',".datepicker_recurring_start", function(){
		    $(this).datepicker({
		    dateFormat : "dd-mm-yy" 
		    }
		    );
		});
 
	</script> --%>
	<script>
		 $(document).ready(function(){
			 /* if($("#nightCount").val()!=""){
				 $('#label').html("Selected:" + $("#nightCount").val() +" nights");
			 } */
			 /* $(function() {
				 
				$("#checkIn").datepicker({				    
				    dateFormat : "dd-mm-yy",
			    	minDate: 0,
				    onSelect: function(dateText, inst) {
				        //dateText comes in as MM/DD/YY
				        var datePieces = dateText.split('/');
				        var month = datePieces[0];
				        var day = datePieces[1];
				        var year = datePieces[2];
				        dateDifference();
				    }
				});
				$("#checkOut").datepicker({				  
				    dateFormat : "dd-mm-yy",
			    	minDate: 0,
				    onSelect: function(dateText, inst) {
				        var datePieces = dateText.split('/');
				        var month = datePieces[0];
				        var day = datePieces[1];
				        var year = datePieces[2];
				        dateDifference();
				    }
				});
				}); */
			  
				function dateDifference() {
				    if($("#checkOut").val()!='' && $("#checkIn").val()!='') {
				    	var s = $("#checkOut").datepicker("getDate");
				         var diff = ($("#checkOut").datepicker("getDate") - $("#checkIn").datepicker("getDate") / 1000 / 60 / 60 / 24);
				         $('#label').html("No of nights selected  : "+ diff);
				          
				        
				    }
				}
				
	 });
		 if(enableQuotationMove === 0 ){
	$('#sortable').sortable({
		   start: function(e, ui) {
			   $("#sortable div.form-group").each(function(i, elm) {
		 	         $elm = $(elm); // cache the jquery object
		 	         $elm.attr("id", $elm.index("#sortable div.form-group")+1);
		 	       });
		    },
		    update: function(event, ui) {
		 	      $("#sortable div.form-group").each(function(i, elm) {
		 	         $elm = $(elm); // cache the jquery object
		 	         $elm.attr("id", $elm.index("#sortable div.form-group")+1);
		 	       });
		    }
		}); 
		 }
		 else{
			 $('.fa-arrows ').hide(); 
		 }
	</script>


<script>


function pad(n){return n<10 ? '0'+n : n}
var j = $("#checkIn").val();
var i = $("#checkOut").val();

var parts = j.split("-");
var dd = new Date(parts[2], parts[1] - 1, parts[0]);

var startDate = dd.getFullYear()+  "-" + pad((dd.getMonth() + 1)) + "-" + pad(dd.getDate()) ;
var parts1 = i.split("-");
var dd1 = new Date(parts1[2], parts1[1] - 1, parts1[0]);
var endDate = dd1.getFullYear()+  "-" + pad((dd1.getMonth() + 1)) + "-" + pad(dd1.getDate()) ;


var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
var firstDate = new Date(startDate);
var secondDate = new Date(endDate);

var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
$('#label').val(diffDays);   /*  $('#label').html("No of nights selected  : "+ diffDays); */
 
</script>
 <script>
$("#checkintime").timepicker({
		dateFormat : "HH:mm",
		onSelect : function(selectedDate) {
			$(this).valid();
		}
	});
	$("#checkouttime").timepicker({
		dateFormat : "HH:mm",
		onSelect : function(selectedDate) {
			$(this).valid();
		}
	});

</script>
</body>

</html>