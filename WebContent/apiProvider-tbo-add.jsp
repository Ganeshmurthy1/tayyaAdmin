<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/alert.css">
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "apiProviderList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>
	<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Add TBO Config</h1>
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
			<form action="createTboConfig" method="post"
				class="form-horizontal" name="myForm" id="apiProvder">
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Title</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="title"
							name="title" placeholder="Title" autocomplete="off"
							required min="10" maxlength="40" required>
					</div>
				</div>
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Status </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="active" required="required">
								<option value="true" selected="selected">active</option>
							<option value="false">inactive</option>
						 </select>
					</div>
 					</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Flight UserName</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="userName"
							name="userName" placeholder="user Name" autocomplete="off"
							required min="10" maxlength="30" required>
					</div>
				</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Flight PassWord</label>
					<div class="col-sm-8">
						<input type="password" class="form-control input-sm" id="password"
							name="password" placeholder="password" autocomplete="off"
							required min="10" maxlength="30" required>
					</div>
				</div>
				
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Hotel UserName</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="userName"
							name="userNameHotel" placeholder="user Name" autocomplete="off"
							required min="10" maxlength="30" required>
					</div>
				</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Hotel PassWord</label>
					<div class="col-sm-8">
						<input type="password" class="form-control input-sm" id="password"
							name="passwordHotel" placeholder="password" autocomplete="off"
							required min="10" maxlength="30" required>
					</div>
				</div>
				
				
				
				
				  <div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Currency</label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="apiCurrency"
							name="apiCurrency" placeholder="api Currency" autocomplete="off"
							 >
					</div>
 					</div>  
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Environment </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="environment" required="required">
								<option value="test" selected="selected">Test</option>
							<option value="live">Live</option>
						 </select>
					</div>
 					</div>
 					<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Client Id</label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="clientId"
							name="clientId" placeholder="client Id" autocomplete="off"
							  required>
					</div>
 					</div>
 					<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Property Id </label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="propertyId"
							name="propertyId" placeholder="property Id" autocomplete="off"
							  required>
					</div>
 					</div>
 				 
 				 <div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Provider Id </label>
					<div class="col-sm-8">
						 <input type="number" class="form-control input-sm" id="providerId"
							name="providerId" placeholder="property Id" autocomplete="off"
							  required>
					</div>
 					</div>
 					<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">EndUser IP</label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="enduserIp"
							name="enduserIp" placeholder="192.128.1.144" autocomplete="off"
							  required>
					</div>
 					</div>
 					 
				  <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">Flight AuthUrl</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="authUrlFlight"
								name="authUrlFlight" placeholder="Authorization Url" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlSearch</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlSearch"
								name="flightUrlSearch" placeholder="flightUrlSearch" autocomplete="off"
								  required>
						</div>
					</div>
					
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlFarerule</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlFarerule"
								name="flightUrlFarerule" placeholder="flightUrlFarerule" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlAirprice</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlAirprice"
								name="flightUrlAirprice" placeholder="flightUrlAirprice" autocomplete="off"
								  required>
						</div>
					</div>
					 
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlSsr</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlSsr"
								name="flightUrlSsr" placeholder="flightUrlSsr" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlBook</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlBook"
								name="flightUrlBook" placeholder="flightUrlBook" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlTicket</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlTicket"
								name="flightUrlTicket" placeholder="flightUrlTicket" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlGetBookingDetail</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlGetBookingDetail"
								name="flightUrlGetBookingDetail" placeholder="flightUrlGetBookingDetail" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlReleasePnr</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlReleasePnr"
								name="flightUrlReleasePnr" placeholder="flightUrlGetBookingDetail" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlCancellation</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlCancellation"
								name="flightUrlCancellation" placeholder="flightUrlCancellation" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlCancellationStatus</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlCancellationStatus"
								name="flightUrlCancellationStatus" placeholder="flightUrlCancellationStatus" autocomplete="off"
								  required>
						</div>
					</div>
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlPriceRbd</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlPriceRbd"
								name="flightUrlPriceRbd" placeholder="flightUrlPriceRbd" autocomplete="off"
								  required>
						</div>
					</div>	
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">flightUrlCalendarSearch</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="flightUrlCalendarSearch"
								name="flightUrlCalendarSearch" placeholder="flightUrlCalendarSearch" autocomplete="off"
								  required>
						</div>
					</div>	
					
					<div class="form-group">
						<label for="agencyBalance-name" class="col-sm-2 control-label">Agenecy Balance Url</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="generalUrlAgencyBalance"
								name="generalUrlAgencyBalance" placeholder="Agenecy Balance Url" autocomplete="off"
								  required>
						</div>
					</div>	
					
					
					
				<hr/>	
					 <div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">authUrlHotel</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="authUrlHotel"
								name="authUrlHotel" placeholder="authUrlHotel" autocomplete="off"
								  required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">hotelUrlSearchHotel</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="hotelUrlSearchHotel"
								name="hotelUrlSearchHotel" placeholder="hotelUrlSearchHotel" autocomplete="off"
								  required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">hotelUrlSearchHotelInfo</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="hotelUrlSearchHotelInfo"
								name="hotelUrlSearchHotelInfo" placeholder="hotelUrlSearchHotelInfo" autocomplete="off"
								  required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">hotelUrlSearchRooms</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="hotelUrlSearchRooms"
								name="hotelUrlSearchRooms" placeholder="hotelUrlSearchRooms" autocomplete="off"
								  required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">hotelUrlBlockRooms</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="hotelUrlBlockRooms"
								name="hotelUrlBlockRooms" placeholder="hotelUrlBlockRooms" autocomplete="off"
								  required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">hotelUrlBooking</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="hotelUrlBooking"
								name="hotelUrlBooking" placeholder="hotelUrlBooking" autocomplete="off"
								  required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">hotelUrlCancel</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="hotelUrlCancel"
								name="hotelUrlCancel" placeholder="hotelUrlCancel" autocomplete="off"
								  required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">hotelUrlCancelStatus</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="hotelUrlCancelStatus"
								name="hotelUrlCancelStatus" placeholder="hotelUrlCancelStatus" autocomplete="off"
								  required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">hotelUrlBookingSummary</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="hotelUrlBookingSummary"
								name="hotelUrlBookingSummary" placeholder="hotelUrlBookingSummary" autocomplete="off"
								  required>
						</div>
					</div>
				 
				<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Add TBOConfig</button>
					</div>
				</div>
			</form>
		</section>
	</div>


	<%@ include file="DashboardFooter.jsp"%> 
		<script> 
	$(document).ready(function(){ 
		 
	    $("#apiProvder").validate({
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
	            },
	        }, 
	        highlight: function(element, errorClass, validClass) { 
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) { 
	         element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          },
	        submitHandler: function (form) {   
	            return false;  
	        } 
	    });
	    
	    $('#buttonSubmit').click(function() { 
	  	if($("#apiProvder").valid())  
	    	 document.getElementById("apiProvder").submit();
	  /* 	else
	  		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
	    });    
	    
	});

	</script>

</body>

</html>