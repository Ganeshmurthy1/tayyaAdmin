<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Register</title>
<!-- 
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->
	<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> --%>

<link rel="stylesheet" href="css/alert.css">

<style>
.switch1 {
 width: 130px;
  height: 25px;/* 20px */
  background: #c1c1c1;
  margin: 20px auto;
  position: relative;
  border-radius: 20px;
  box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.5), 0px 1px 0px rgba(255, 255, 255, 0.2);
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
  background: #787878;/* #787878 */
  /* background: -webkit-linear-gradient(top, #fcfff4 0%, #dfe5d7 40%, #b3bead 100%);
  background: linear-gradient(to bottom, #fcfff4 0%, #dfe5d7 40%, #b3bead 100%);  */
  
  z-index: 999;
}

/* input:checked+.slider {
 background-color: #2196F3;
}

input:focus+.slider {
 box-shadow: 0 0 1px #2196F3;
} */

input:checked+.slider1:before {
 -webkit-transform: translateX(70px);
 -ms-transform: translateX(70px);
 transform: translateX(70px);
 
 background: green;
  /* background: -webkit-linear-gradient(top, #1595d3 0%, #1595d3 40%, #1595d3 100%);
  background: linear-gradient(to bottom, #1595d3 0%, #1595d3 40%, #1595d3 100%); */
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
.switch-text{
position: absolute;
    bottom: 22px;
    left: 23%;
    font-size: 15px;
}
.support #dropdown{
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
}
.error {
    color:red;
}
.valid {
    color:green;
}
</style>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
 
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
<script type="text/javascript">
	function numbersonly(thisObject) {
		var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
		if(!floatRegex.test($(thisObject).val())) {
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
			
		<section class="content-header">
		<div class="row">
		<div class="col-sm-3 clearfix pull-left">
		<h4><b>Create Hotel Quotation</b></h4>
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
				<a href="hotelTravelRequestEdit?id=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-edit"></i> Travel Request
				</a>
				<a href="hotelOrderQuotationView?id=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-eye"></i> Travel Request
				</a>				
				<a href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-list"></i> Quotations
				</a>
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
			<form action="createHotelRequestTravelQuotation" method="post" id="createHotelRequestTravelQuotation"
				class="form-horizontal" name="myForm">
					<input type="hidden"  id="cityCode1"  name="hotelQuotationList[0].cityCode" value="${hotelTravelRequest.cityCode}">
	 				<input id="cityUrl" type="hidden" value="<s:text name="global.hotelCitiesUrl" ></s:text>">
						<input type="hidden" name="hotelQuotationRequestId"	value="<s:property value="%{hotelQuotationRequestId}"/>">
						<input type="hidden" name="hotelQuotationList[0].additionalData" id="aditionalSchema">
						<input type="hidden" id="nightCount" value="${hotelTravelRequest.noOfNights}">
		                <input type="hidden" name="hotelQuotationList[0].bookingMode" id="bookingMode" value="offline">
             	 <div class="row">
	             	    <div class="col-sm-12 text-center">
	             	    <span class="switch-text" > Choose online or offline bookings</span>
	     			 <label class="switch1"><input type="checkbox"  name="active"  data-id="${id}"
	                  class="activeStatus"   <c:if test="${active==true}" >checked</c:if> >
	                  <div class="slider1"></div>
	                </label> 
	             	</div>
             	</div>
             	<div class="row">
             	<div class="col-sm-12">
					<div class="support">
						<div class="expand" id="support">
							<!-- level1 -->
							<div class="level1">
								<div id="level1">
									<div class="well">
										<div class="col-sm-6">
											<p class="h5"><strong><span style="margin-left: -15px;font-size: 13px;margin-top: 20px;"     id="label" class="pull-left"></span></strong></p>
										</div>
										<div class="col-sm-6">
											<a href="#addSchema"
												class="btn btn-success btn-xs pull-right " onclick="mod();"
												data-toggle="modal"> <i class="fa fa-plus-circle"></i>
												Add More
											</a>
										</div>

										<div id="sortable" class="sort">
 
									<div class="form-group ui-state-default" style="width: 100%"
												id="1">
												<label class="col-sm-2 control-label">Check In Date</label>
												<div class="col-sm-8">
													<input type="text" id="checkIn"
														class="form-control input-sm "
														name="hotelQuotationList[0].checkIn"
														placeholder="Check In Date"  readonly value="${hotelTravelRequest.checkIn}"  autocomplete="off" required >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											<div class="form-group ui-state-default" style="width: 100%"
												id="2">
												<label class="col-sm-2 control-label">Check Out Date</label>
												<div class="col-sm-8">
													<input type="text" id="checkOut"
														class="form-control input-sm "
														name="hotelQuotationList[0].checkOut"
														placeholder="Check Out Date" readonly value="${hotelTravelRequest.checkOut}"            autocomplete="off" required >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											 <div class="form-group ui-state-default" style="width: 100%"
												id="3">
												<label class="col-sm-2 control-label">Check In Time</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" required
														name="hotelQuotationList[0].checkInTime" id="checkintime"
														placeholder="CheckIn Time" autocomplete="off" value="12:00">
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
 
											<div class="form-group ui-state-default" style="width: 100%"
												id="4">
												<label class="col-sm-2 control-label">Check Out Time</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" required
														name="hotelQuotationList[0].checkOutTime" id="checkouttime"
														placeholder="CheckOut Time" autocomplete="off" value="12:00">
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											
											<div class="form-group ui-state-default" style="width: 100%"
												id="5">
												<label class="col-sm-2 control-label">No of Nights</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" 
														name="hotelQuotationList[0].nightCount" id="nightCount"
														placeholder="No of Nights" autocomplete="off" value="${hotelTravelRequest.noOfNights}" readonly="readonly">
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											
											 <div class="form-group ui-state-default" style="width: 100%"
												id="6">
												<label class="col-sm-2 control-label">City</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm" required
														name="hotelQuotationList[0].hotelCity" id="hotelCity3"
														placeholder="Hotel City" autocomplete="off"  value="${hotelTravelRequest.city}">
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
										 
											<div class="form-group ui-state-default"  style="width: 100%"
												id="7">
												<label class="col-sm-2 control-label">Room(s)</label>
												<div class="col-sm-8" id="room-view" >
													
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="8">
												<label class="col-sm-2 control-label">Adult(s)</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm" required
														name="hotelQuotationList[0].adultCount" id="adultCount"
														placeholder="Adult Count" autocomplete="off" value=1>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="9">
												<label class="col-sm-2 control-label">Child(s)</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm" required
														name="hotelQuotationList[0].childCount" id="childCount"
														placeholder="Child Count" autocomplete="off" value=0>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>



											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="10">
												<label class="col-sm-2 control-label">RoomRate/Night</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].roomRatePerNight" onchange="numbersonly(this)"
														placeholder="Room Rate Per Night" autocomplete="off" required="required"
														>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											  <div class="form-group ui-state-default" style="width: 100%;"
												id="11">
												<label class="col-sm-2 control-label">Currency</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" required
														name="hotelQuotationList[0].currency">
														 <s:iterator value="countryList">
														<option value="${cur_code}" <c:if test="${cur_code==session.Company.currencyCode}"> selected </c:if> > ${cur_code}</option>												 	
						 								</s:iterator>
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div> 


											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="12">
												<label class="col-sm-2 control-label">Hotel Name</label>
												<div class="col-sm-8">
													<!--  -->
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].hotelName" required
														placeholder="hotelName" autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="13">
												<label class="col-sm-2 control-label">Hotel Category</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].hotelCategory" required
														placeholder="Hotel Category" autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="14">
												<label class="col-sm-2 control-label">Hotel Address</label>
												<div class="col-sm-8">

													<textarea rows="2" cols="2" class="form-control input-sm"
														name="hotelQuotationList[0].hotelAddress" required
														placeholder="Hotel Address" ></textarea>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
 									

											<div class="form-group ui-state-default offline-content"  style="width: 100%;"
												id="15">
												<label class="col-sm-2 control-label">Project Address</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].projectAddress" required
														placeholder="Project Address" autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="16">
												<label class="col-sm-2 control-label">Distance from Work</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].distanceFromWork" required
														placeholder="Distance From Work" autocomplete="off"
														>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="17">
												<label class="col-sm-2 control-label">Room Type</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="hotelQuotationList[0].roomType" required
														placeholder="Room Type" autocomplete="off" >
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>

											

											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="18">
												<label class="col-sm-2 control-label">Payment Option(s) </label>
												<div class="col-sm-8">
													<select class="form-control input-sm" required
														name="hotelQuotationList[0].availablePaymentOptionList"
														multiple="multiple" >
														<option value="PrePaid" selected="selected">PrePaid</option>
														<!-- <option value="PostPaid">PostPaid</option> -->
														<option value="PayAtHotel">PayAtHotel</option>
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>


											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="19">
												<label class="col-sm-2 control-label">Taxes</label>
												<div class="col-sm-8">

													<select class="form-control input-sm"
														name="hotelQuotationList[0].taxes">
														<option selected="selected" value="yes">YES</option>
														<option value="no">NO</option>
													</select>

												</div>
													<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>

											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="20">
												<label class="col-sm-2 control-label">Breakfast</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="hotelQuotationList[0].breakfast">
														<option selected="selected" value="yes">YES</option>
														<option value="no">NO</option>
													</select>
												</div>
													<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>


											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="21">
												<label class="col-sm-2 control-label">Internet</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="hotelQuotationList[0].internet">
														<option selected="selected" value="yes">YES</option>
														<option value="no">NO</option>
													</select>
												</div>
													<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>


											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="22">
												<label class="col-sm-2 control-label">Cancellation Policy</label>
												<div class="col-sm-8">

													<textarea rows="2" cols="2" class="form-control input-sm"
														name="hotelQuotationList[0].cancellationPolicy" required
														placeholder="Cancellation Policy" ></textarea>

												</div>
													<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>


											<div class="form-group ui-state-default offline-content" style="width: 100%"
												id="23">
												<label class="col-sm-2 control-label">Prefer Property</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="hotelQuotationList[0].preferProperty">
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>
												</div>
												<div class="col-sm-1">
												<i class= "fa fa-arrows"></i>
												</div>
											</div>
										</div>
									</div>

									<div class="package"></div>
									 
								</div>
							</div>
						</div>
					</div>
					<!--  support -->
				</div>
				</div>
		 <div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" class="btn btn-primary btn-m offline-content" onclick="lload()">Create Quotation</button>
						<button type="button" class="btn btn-primary btn-m online-content" style="display:none" onclick="onlineSubmit()">Create Quotation</button>
					</div>
					<!-- <div class="col-xs-4 submitWrap text-center">
						<button type="submit" class="btn btn-primary btn-m offline-content" onclick="lload()">Save & Copy Quotation</button>
					</div> -->
					<!-- <div class="col-xs-6 submitWrap text-center">
						<button type="submit" class="btn btn-primary btn-m offline-content" onclick="lload()">Save & Create Another Quotation</button>
					</div> -->
		</div> 
		
		</form>
		
		<!-- Online submit form  below-->
			<div class="hotel-create-online">
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
     <input type="hidden" id="cityname"   name="cityname" value="${hotelTravelRequest.city}"> 
     <input type="hidden"  id="citycode"  name="citycode" value="${hotelTravelRequest.cityCode}">
     
     <input type="hidden" id="datestart"    name="datestart" value="20/11/2016">
       <input type="hidden" id="companyId"    name="companyId" value="<s:property value="%{#session.User.Companyid}"/>">  
         <input type="hidden" id="userId"    name="userId" value="<s:property value="%{#session.User.id}"/>">       
     <input type="hidden"  id="dateend"    name="dateend" value="21/11/2016">
     <input type="hidden"  id="onlinerooms"  name="rooms" value="">     
       <input type="hidden"  id="onlinenoofrooms"   name="noofrooms" value="">    
   <!--   <input type="hidden"  id="adults1"   name="Adults1" value="1">
     <input type="hidden" id="childs1"  name="Childs1" value="0">
     <input type="hidden" name="Age1" value="0">    -->
    <input type="hidden" name="hotelquotationid"
     value="<s:property value="%{hotelQuotationRequestId}"/>">
 	 
    </form>
	 </div>
		 </div>
		<!--  <div class="col-sm-12">
		 
		 <div class="col-sm-6">
		 <a href="" type="submit" class="btn btn-primary btn-lg" onclick="lload()">Create Offline Quotation</a>
		 </div>
		  <div class="col-sm-6">
		   <a href="" type="button" onclick="onlineSubmit()" class="btn btn-primary btn-lg">Create Online Quotation</a>
		  </div>
		  </div> -->
			
		</section>
	</div>
	
	
	<!-- dont care about popop please -->
	
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
				
				
				<!--  room view count -->
				
				<div class="offline-content" id="offline-room-view"  style="display:none">
														<input type="number" class="form-control input-sm"
															name="hotelQuotationList[0].roomCount" id="roomCount"
															placeholder="Room Count" autocomplete="off" value=1>
				</div>
				
				<div class="online-content" id="online-room-view" style="display:none">
												<div id="dropdown">
															<span><span id="totalpeopletext">1</span>, Adult(s) <span
																id="totalchildtext">0</span>, Child(s) in <span
																id="totalroomtext">1</span>, Room(s)</span> <i
																class="tayyarah-angle-down pull-right"></i>
				
											   </div>
														
														<!-- dropdown comes here -->
										<div class="dropdownwrap" style="display: none;" id="hotelRoomCount">
									<div class="col-sm-12 clearfix">
										<div class="form-group">
											<label for="inputGroupSuccess2">Room(s)</label>
											<div class="input-group">
												<select class="form-control" name="rooms" value ="1" id="totalrooms"
													onchange="AddRooms(this)"  required>
													<option>1</option>
													<option>2</option>
													<option>3</option>
													<option>4</option>

												</select>
											</div>
										</div>
									</div>
									<div class="hotel-repeat ">
										<!-- groweble div in hotel -->
										<div class="row" id="rowid1" index='1'>
											<div class="col-sm-2 clearfix rooms-multiple">
												<p class="ro-heading">Room 1:</p>
											</div>
											<!-- <p>Room1</p> -->
											<div class="col-sm-5 clearfix">
												<div class="form-group">
													<label for="inputGroupSuccess2">Adult(s)</label>
													<div class="input-group">

														<select class="form-control" name="Adults1"
															id="RoomAdult1" required 
															onchange="FirstRoomChangeChild()">
															<option >1</option>
															<option>2</option>
															<option>3</option>
															<option>4</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-5 clearfix">
												<div class="form-group">
													<label for="inputGroupSuccess2">Child(s)</label>
													<div class="input-group">
														<!-- <span > Room 1</span> -->
														<select class="form-control" id="Childs" name="Childs1"
															required onchange="FirstrowChildchange('1')">
															<option>0</option>
															<option>1</option>
															<option>2</option>
															<option>3</option>
															<option>4</option>
															<option>5</option>
														</select>
													</div>
												</div>
											</div>

											<div class="col-sm-12">
												<div class="col-sm-2"></div>
												<div class="col-sm-2 clearfix Room1Child1" id="c-age"
													style="display: none;">
													<div class="form-group">
														<label for="inputGroupSuccess2">Age 1</label>
														<div class="input-group">

															<select class="form-control" id="room1Age1" name="Age1" required>
																<option value="1">< 1</option>
																<option>1</option>
																<option>2</option>
																<option>3</option>
																<option>4</option>
																<option>5</option>
																<option>6</option>
																<option>7</option>
																<option>8</option>
																<option>9</option>
																<option>10</option>
																<option>11</option>
															</select>
														</div>
													</div>
												</div>

												<div class="col-sm-2 clearfix Room1Child2" id="c-age"
													style="display: none;">
													<div class="form-group">
														<label for="inputGroupSuccess2">Age 2</label>
														<div class="input-group">

															<select class="form-control" id="room1Age2" name="Age1" required>
																<option value="1">< 1</option>
																<option>1</option>
																<option>2</option>
																<option>3</option>
																<option>4</option>
																<option>5</option>
																<option>6</option>
																<option>7</option>
																<option>8</option>
																<option>9</option>
																<option>10</option>
																<option>11</option>
															</select>
														</div>
													</div>
												</div>

												<div class="col-sm-2 clearfix Room1Child3" id="c-age"
													style="display: none;">
													<div class="form-group">
														<label for="inputGroupSuccess2">Age 3</label>
														<div class="input-group">

															<select class="form-control" id="room1Age3" name="Age1" required>
																<option value="1">< 1</option>
																<option>1</option>
																<option>2</option>
																<option>3</option>
																<option>4</option>
																<option>5</option>
																<option>6</option>
																<option>7</option>
																<option>8</option>
																<option>9</option>
																<option>10</option>
																<option>11</option>
															</select>
														</div>
													</div>
												</div>

												<div class="col-sm-2 clearfix Room1Child4" id="c-age"
													style="display: none;">
													<div class="form-group">
														<label for="inputGroupSuccess2">Age 4</label>
														<div class="input-group">

															<select class="form-control" id="room1Age4" name="Age1" required>
																<option value="1">1</option>
																<option>1</option>
																<option>2</option>
																<option>3</option>
																<option>4</option>
																<option>5</option>
																<option>6</option>
																<option>7</option>
																<option>8</option>
																<option>9</option>
																<option>10</option>
																<option>11</option>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-2 clearfix Room1Child5" id="c-age"
													style="display: none;">
													<div class="form-group">
														<label for="inputGroupSuccess2">Age 5</label>
														<div class="input-group">
															<select class="form-control" id="room1Age5" name="Age1" required>
																<option value="1">< 1</option>
																<option>1</option>
																<option>2</option>
																<option>3</option>
																<option>4</option>
																<option>5</option>
																<option>6</option>
																<option>7</option>
																<option>8</option>
																<option>9</option>
																<option>10</option>
																<option>11</option>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="hotel-repeatadd"></div>
										<div class="clearfix">
											<button type="button" class="btn btn-info" id="but-up"
											>Done</button>
										</div>
									</div>
								</div>
								
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


$("#createHotelRequestTravelQuotation").validate({
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
	<script>
	var enableQuotationMove = ${hotelRequestTravelQuotationSize};
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
		$('#cityCode1').val(citycode);
		$('#cityname').val(ui.item.value);
		 $('#citycode').val(citycode);
		console.log("citycode-----------"+$('#cityCode1').val());
		 
	}

});
function  lload(){
	/* var inputs = $("#"+id).find("select, textarea, input");
	console.log(inputs); */
	//var z = $(this).children('label').text();
	//var schemaStruct="$$proertyName:type:value:posNum:pos:displayName$$"
	
	 if($("#createHotelRequestTravelQuotation").valid()){

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

		 document.getElementById("createHotelRequestTravelQuotation").submit();
	 }
}
$('#yes').click(function() {
	
	if(($('#fieldName').val() != '') && ($('#dataType').val() != '')){		
		 $('#').modal('hide');
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
		 /* $('body').on('focus',".datepicker_recurring_start", function(){
			$(this).datepicker({
		    	dateFormat : "dd-mm-yy",
		    	minDate: 0
		    });
		}); 
		 */
		 $(document).ready(function(){
			 if($("#nightCount").val()!=""){
				 $('#label').html("No of nights selected  : "+ $("#nightCount").val());
			 }
			/*  $(function() {
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
				         var diff = ($("#checkOut").datepicker("getDate") - $("#checkIn").datepicker("getDate"));
				         $('#label').html("No of nights selected : "+diff);
				    }
				}
	 });
	</script>
	<script>
 if(enableQuotationMove == 0 ){
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
	  <script>
   function onlineSubmit(){
	 var rooms =  roomDetails();	 
	    var checkIn=$("#checkIn").val();
	    var checkOut=$("#checkOut").val();
		 var hotelCity= $("#hotelCity3").val();
	    $("#cityname").val(hotelCity);
		 $("#datestart").val(checkIn.replace(/-/g,"/"));
		 $("#dateend").val(checkOut.replace(/-/g,"/"));		
		 $("#onlinerooms").val(rooms);
		 var totalrooms = $("#totalrooms").val();	
		 $("#onlinenoofrooms").val(totalrooms);
	 if(checkIn=="" || checkOut=="" || hotelCity==""){
	 alert("Check In Date ,Check Out Date ,Hotel City are *** required***");
	 }
	 else{
		 $("#onlineForm").submit(); 
	 }
	  }
	</script>  
	
	<script>
	$( document ).ready(function() {
		$("#offline-room-view").appendTo("#room-view");
		$("#offline-room-view").show(); 
		 /*  if($('.activeStatus').is(":checked")==false){ 
		   $(this).attr('checked', false);
		   $(this).attr('value', false);
		   $("#bookingMode").attr('value', "offline");
		   $(".online-content").hide();
		   $(".offline-content").show(); 
		   }else{
			   $(this).attr('checked', true);
			   $(this).attr('value', true);
			   $("#bookingMode").attr('value', "online");
			   $(".online-content").show();
			   $(".offline-content").hide();
			   
		   }  */
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
	   $("#offline-room-view").appendTo("#room-view");
	   }
	 else{
	   status= true;
	   $(this).attr('checked', true);
	   $(this).attr('value', true);
	   $("#bookingMode").attr('value', "online");
	   $(".online-content").show();
	   $(".offline-content").hide();
	   $("#online-room-view").appendTo("#room-view");
	  }
	 
	 
});

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

<%-- <script src="js/city.js"></script> --%>
  <script src="js/city_code.js"></script>
  <script src="js/hotelRoomDetails.js"></script>
</body>
</html>