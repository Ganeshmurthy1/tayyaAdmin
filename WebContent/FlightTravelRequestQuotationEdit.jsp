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

 
	 
 

<link rel="stylesheet" href="css/alert.css">
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl +"FlightTravelRequestList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<div class="row">
		<div class="col-sm-4 clearfix pull-left">
			<h3>Edit Flight Quotation</h3>
			</div>
			 
		<div class="col-sm-8 clearfix pull-right " data-placement="top">
		<div class="row">
		<div class="col-sm-5 clearfix pull-left " data-placement="top">
				<a href="goFlightRequestEdit?id=${flightQuotation.flightTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-edit"></i> Travel Request 
				</a>
				 
				<a href="goFlightRequestTravelQuotation?flightQuotationRequestId=${flightQuotation.flightTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-plus"></i> Quotation
				</a>
				<a href="getFlightQuotationList?flightQuotationRequestId=${flightQuotation.flightTravelRequest.id}"
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
			<form action="flightQuotationUpdate" method="post"
				class="form-horizontal" name="myForm" id="editForm">
				<input type="hidden" name="flightQuotationRequestId"
					value="<s:property value="%{flightQuotation.flightTravelRequest.id}"/>">
					<input type="hidden" name="flightRequestQuotationList[0].id"
					value="<s:property value="%{flightQuotation.id}"/>">
						<input type="hidden" name="flightRequestQuotationList[0].additionalData" id="aditionalSchema">

				<div class="col-sm-12">
					<div class="support">
						<div class="expand" id="support">
							<!-- level1 -->
							<div class="level1">
								<div id="level1">
									<div class="well">
										<div class="col-sm-6">
											<p class="h4"><span id="label" class="pull-right"></span></p>
										</div>
										<div class="col-sm-6">
											<a href="#addSchema"
												class="btn btn-success btn-xs pull-right" onclick="mod();"
												data-toggle="modal"> <i class="fa fa-plus-circle"></i>
												Add More
											</a>
										</div>
										<div id="sortable" class="sort">
										<s:iterator value="hotelQuotationSchemas" status="rowCount">
										<s:if test="fieldName=='origin'">
										<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Origin</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" value="${dataValue}" 
														name="flightRequestQuotationList[0].origin"
														placeholder="origin" autocomplete="off"
														required  id='origin'>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
										
										</s:if>
										<s:if test="fieldName=='destination'">
										<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Destination</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" value="${dataValue}" 
														name="flightRequestQuotationList[0].destination"
														placeholder="destination" autocomplete="off"
														required  id='destination'>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
										
										</s:if>
										<s:if test="fieldName=='transDepartureDate'">
										<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Departure Date</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" value="${dataValue}" 
														name="flightRequestQuotationList[0].transDepartureDate"
														placeholder="Departure Date" autocomplete="off"
														required  id='departureDate'>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
										
										</s:if>
										<s:if test="fieldName=='transArrivalDate'">
										<div class="form-group ui-state-default arrdate" style="width: 100%"
												id="${positionNumber}" >
												<label class="col-sm-2 control-label">Arrival Date</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" value="${dataValue}" 
														name="flightRequestQuotationList[0].transArrivalDate"
														placeholder="Arrival Date" autocomplete="off"
														required  id='arrivalDate'>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
										
										</s:if>
										
											<s:if test="fieldName=='airline'">
											<div class="form-group ui-state-default" style="width: 100%;"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Airline</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" 
														name="flightRequestQuotationList[0].airline">
														<s:if test="dataValue!=null">
														<option value="${dataValue}" selected="selected">${dataValue}</option>
														</s:if>
														<s:else>
														<option value="" selected="selected">Select Airline</option>
														</s:else>
															<s:iterator value="airlineList">
															<option value="<s:property value="airlinename"/>"><s:property
																	value="airlinename" /></option>
														</s:iterator>
													</select>
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
														name="${fieldName}"
														placeholder="${displayName}" value="${dataValue}"   autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
										
										<s:if test="fieldName=='tripType'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Trip Type</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="flightRequestQuotationList[0].tripType" id="edittriptype">
														<c:if test="${dataValue=='O'}">
														<option value="R" >Round Trip</option>
														<option value="O" selected="selected">One Way</option>
														<option value="M">Multi Trip</option>
														</c:if>
													 <c:if test="${dataValue=='R'}">
														<option value="R" selected="selected">Round Trip</option>
														<option value="O" >One Way</option>
														<option value="M">Multi Trip</option>
														</c:if>
														<c:if test="${dataValue=='M'}">
														<option value="R">Round Trip</option>
														<option value="O" >One Way</option>
														<option value="M" selected="selected">Multi Trip</option>
														</c:if>
														 <%-- <s:else>
														<option value="R">Round Trip</option>
														<option value="O">One Way</option>
														<option value="M">Multi Trip</option>
														</s:else>   --%>
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
										<s:if test="fieldName=='bookingClassPrefer'">
											<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Booking Class</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="flightRequestQuotationList[0].bookingClassPrefer">
														 
														<s:if test="dataValue=='All'">
														<option value="All" selected="selected">ALL</option>
														<option value="Economy">Economy</option>
														<option value="PremiumEconomy">Premium Economy</option>
														<option value="Business">Business</option>
														<option value="PremiumBusiness">Premium Business</option>
														</s:if>
														<s:elseif test="dataValue=='Economy'">
														<option value="All" >ALL</option>
														<option value="Economy" selected="selected">Economy</option>
														<option value="PremiumEconomy">Premium Economy</option>
														<option value="Business">Business</option>
														<option value="PremiumBusiness">Premium Business</option>
														</s:elseif>
														<s:elseif test="dataValue=='PremiumEconomy'">
														<option value="All" >ALL</option>
														<option value="Economy">Economy</option>
														<option value="PremiumEconomy"  selected="selected">Premium Economy</option>
														<option value="Business">Business</option>
														<option value="PremiumBusiness">Premium Business</option>
														</s:elseif>
														<s:elseif test="dataValue=='Business'">
														<option value="All" >ALL</option>
														<option value="Economy">Economy</option>
														<option value="PremiumEconomy">Premium Economy</option>
														<option value="Business"  selected="selected">Business</option>
														<option value="PremiumBusiness">Premium Business</option>
														</s:elseif>
														<s:elseif test="dataValue=='PremiumBusiness'">
														<option value="All" >ALL</option>
														<option value="Economy">Economy</option>
														<option value="PremiumEconomy">Premium Economy</option>
														<option value="Business">Business</option>
														<option value="PremiumBusiness"  selected="selected">Premium Business</option>
														</s:elseif>
														  <s:else>
														<option value="All"  selected="selected">ALL</option>
														<option value="Economy">Economy</option>
														<option value="PremiumEconomy">Premium Economy</option>
														<option value="Business">Business</option>
														<option value="PremiumBusiness" >Premium Business</option>
														</s:else>
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
									<s:if test="fieldName=='passengerCount'">
									<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Passenger Count</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm"
														name="flightRequestQuotationList[0].passengerCount"
														placeholder="Passenger Count" autocomplete="off" value="${dataValue}"    required>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
										<s:if test="fieldName=='totalAmount'">
										<div class="form-group ui-state-default" style="width: 100%"
												id="${positionNumber}">
												<label class="col-sm-2 control-label">Total Amount/ Per Passenger (${flightQuotation.flightTravelRequest.currency})</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="flightRequestQuotationList[0].totalAmount"
														placeholder="Total Amount" autocomplete="off" value="${dataValue}" required>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											</s:if>
										
										</s:iterator>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--  support -->

				</div>
		 <div class="form-group text-center">
					 
					<div class="col-xs-6 submitWrap text-center">
					
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
											  name="dataType"  >
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
			<%-- <div class="hotel-create-online">
					<div class="submitWrap text-center">
					<form action="<s:text name="global.ibeurl"></s:text>" method="get" target="_blank" class="form-horizontal" name="onlineForm" id="onlineForm">
    <input type="hidden" name="mode" value="0">
     <input type="hidden" name="type" value="4">
      <input type="hidden" name="order" value="PRICE"> 
      <input type="hidden" name="filter" value="7"> 
      <input type="hidden" name="cachelevel" value="Live">
       <input type="hidden" name="version" value="1.0">  
       <input type="hidden" name="lang" value="en"> 
       <input type="hidden" name="countrycode" value="IN">
        <input type="hidden" name="country" value="india">
         <input type="hidden" name="myaction" value="HotelQuoteSearch">
      <input type="hidden" name="dammytext"  value="<s:property value="%{#session.Encryptedid}"/>">
     <input type="hidden" name="request_locale" id="hotelrequestlocale" value="">
     <input type="hidden" id="cityname"   name="cityname" value="Bangalore(Bengaluru),India(IN)"> 
     <input type="hidden"  id="citycode"  name="citycode" value="100812">
     <input type="hidden"  id="rooms"  name="rooms" value="1">
     <input type="hidden" id="datestart"    name="datestart" value="20/11/2016">
       <input type="hidden" id="companyId"    name="companyId" value="<s:property value="%{#session.User.Companyid}"/>">  
         <input type="hidden" id="userId"    name="userId" value="<s:property value="%{#session.User.id}"/>">       
     <input type="hidden"  id="dateend"    name="dateend" value="21/11/2016">    
     <input type="hidden"  id="adults1"   name="Adults1" value="1">
     <input type="hidden" id="childs1"  name="Childs1" value="0">
     <input type="hidden" name="Age1" value="0">   
    <input type="hidden" name="hotelquotationid"
     value="<s:property value="%{hotelQuotationRequestId}"/>">
  <input type="button" onclick="onlineSubmit()"  class="btn btn-primary btn-lg" value="Create Online Quotation">
    </form>
	 </div>
		 </div> --%>
		</section>
	</div>
	<%@ include file="DashboardFooter.jsp"%>
	<script>
	var enableQuotationMove = ${flightRequestTravelQuotationSize};
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
function  lload(){
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
//console.log(input);
//console.log(select);
//console.log(textArea);
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
$("#editForm").submit();
  
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
	var fieldName=$("#fieldName").val();
	var dataType=$("#dataType").val();
	var positionNumber=$("#positionNumber").val();
	var fixedPosition=$("#fixedPosition").val();
	var addSchema=null;
	var checkSchema=$("#setSchema").val();
	var x =1;
	j = z+1;
	 var max_fields=10; //maximum input boxes allowed
	    var wrapper= $("#sortable"); //Fields wrapper
	if(fieldName!=''){
				 if(x<max_fields){
				  x++;
				   $(wrapper).append('<div class="form-group ui-state-default" style="width: 100%" id="'+j+'"><label class="col-sm-2 control-label">'+fieldName+'</label><div class="col-sm-8"><input type="'+dataType+'" class="form-control input-sm"  name="'+fieldName+'"    placeholder="'+fieldName+'" autocomplete="off" required></div><div class="col-sm-2 remove_field">Remove  <i class="fa fa-arrows"></i></div></div>');   
		    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
		        e.preventDefault(); $(this).parent('div').remove(); x--;
		    })
	 }
	 }
	lload();	
}
</script>
	<script>
		 /* $(document).ready(function(){
			 
			 $(function() {
				$("#departureDate").datepicker({				    
				    dateFormat : "dd-mm-yy",
			    	minDate: 0,
				     
				});
				});
	 }); */
		 
		 
		 $( "#departureDate" ).datepicker({
			    changeMonth: true,  
			    changeYear:true,
			    dateFormat: "dd-mm-yy",
			    minDate:0,
			    onSelect: function( selectedDate ) {
			    	var date2 = $("#departureDate").datepicker('getDate', '+1d'); 
			  	  date2.setDate(date2.getDate()+1); 
			  	  $( "#arrivalDate" ).datepicker('setDate', date2);
			  	$( "#arrivalDate" ).datepicker( "option", "minDate", date2 ); 
			    },
			
			 onClose: function() {
		         $("#arrivalDate").focus();
		     }
			  });
			  $( "#arrivalDate" ).datepicker({      
			    changeMonth: true,   
			    changeYear:true,
			    dateFormat: "dd-mm-yy",
			    minDate:1,
			    onSelect: function(selected) { 
			    	           $("#departureDate").datepicker("option", selected)
			    	        }

			   
			  });  
		 
		 
		 
		 
		 
		 $(function (){
			 
			  if($("#edittriptype").val() === "R" || $("#edittriptype").val() === "M") {				 
			        $(".arrdate").show();
			    }else
			    	$(".arrdate").hide();
			   
			   $("#edittriptype").change(function() {
				   var val = $(this).val();
				   if(val === "R" || val === "M") {
				        $(".arrdate").show();
				    }
				    else{
				    	$(".arrdate").hide();				        
				    }
			   });
			  
			});
		 
	</script>
	
	<script>
	console.log(enableQuotationMove);
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
		$(".fa-arrows").hide();
	}
	</script>
</body>

</html>