<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<%-- 
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>
<style type="text/css">
.error {
    color:red;
}
.valid {
    color:green;
    }
</style>
<link rel="stylesheet" href="css/alert.css">
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "commonConfigList";
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
			<h1>Add CommonConfig</h1>
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
			<form action="addCommonConfig" method="post"
				class="form-horizontal" name="myForm" id="addcommonConfig">
				 <div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Server Mode </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="serverMode" required="required">
							<option value="">Select Server Mode</option>
							<option value="live">Live</option>
							<option value="test">Test</option>
							 
						 </select>
					</div>
 					</div>
 					
 					<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Status </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="isActive" required="required">
							<option value="1">Active</option>
							<option value="0">In Active</option>
							 
						 </select>
					</div>
 					</div>
				
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Server Log Path</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="serverLogLocation"
							name="serverLogLocation" placeholder="Server Log Path" autocomplete="off"
							required>
					</div>
				</div>
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Invoice Hotel Prefix</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="invoiceHotelPrefix"
							name="invoiceHotelPrefix" placeholder="Invoice Hotel Prefix" autocomplete="off"
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Invoice Flight Prefix</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="invoiceFlightPrefix"
							name="invoiceFlightPrefix" placeholder="Invoice  Flight Prefix " autocomplete="off"
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Admin Url</label>
					<div class="col-sm-8">
						<input type="url" class="form-control input-sm" id="adminUrl"
							name="adminUrl" placeholder="Admin Url" autocomplete="off"
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">IBE Url</label>
					<div class="col-sm-8">
						<input type="url" class="form-control input-sm" id="ibeUrl"
							name="ibeUrl" placeholder="Admin Url" autocomplete="off"
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">API Url</label>
					<div class="col-sm-8">
						<input type="url" class="form-control input-sm" id="apiUrl"
							name="apiUrl" placeholder="Admin Url" autocomplete="off"
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Image Url</label>
					<div class="col-sm-8">
						<input type="url" class="form-control input-sm" id="imageUrl"
							name="imagePath" placeholder="Image Url" autocomplete="off"
							required>
					</div>
				</div>
				 
				
				 <div>
					
					<div class="col-sm-8 col-sm-offset-2">	
						<div class="support">
 							<h4>Email Service PaymentUrls</h4>
						<div id="support">
								
								<!-- level1 -->
								<div class="level1">
								
									 
										<div class="well">
											 <div class="form-group">
												
												<div class="col-sm-12">
													<input type="url" class="form-control input-sm"
														name="emailServiceFlightPendingPaymentUrl"
														placeholder="Flight Pending PaymentUrl" autocomplete="off" required>
												</div>
											</div>
											<div class="form-group">
											
												<div class="col-sm-12">
													<input type="url" class="form-control input-sm"
														name="emailServiceHotelPendingPaymentUrl"
														placeholder="Hotel Pending PaymentUrl" autocomplete="off" required>
												</div>
											</div>
											
											
 										</div>
									 
								</div>
  

							</div>
							
							<div   id="support">
							<h4>Email ServiceUrls</h4>
							 
								 
								<!-- level1 -->
								<div class="level1">
								
									 
										<div class="well">
											 <div class="form-group">
												 
												<div class="col-sm-12">
													<input type="url" class="form-control input-sm"
														name="emailServiceUrl"
														placeholder="Email Send Url" autocomplete="off" required>
												</div>
											</div>
											<div class="form-group">
												 
												<div class="col-sm-12">
													<input type="url" class="form-control input-sm"
														name="emailVerifyUrl"
														placeholder="Email Verify Url" autocomplete="off" required>
												</div>
											</div>
											
											
 										</div>
									 
								</div>
  

							</div>
							
							 <div  id="support">

								<h4>Email Details</h4>
								<!-- level1 -->
								<div class="level1">
										<div class="well">
										 <div class="form-group">
												 
												<div class="col-sm-12">
													<input type="text" class="form-control input-sm"
														name="maxEmailAttempts"
														placeholder="Max Email Attempts" autocomplete="off" required>
												</div>
											</div>
											 <div class="form-group">
											 
												<div class="col-sm-12">
													<input type="text" class="form-control input-sm"
														name="maxEmailQueueSize"
														placeholder="Max Email Queue Size" autocomplete="off" required>
												</div>
											</div>
											
											 <div class="form-group">
												 
												<div class="col-sm-12">
													<input type="email" class="form-control input-sm"
														name="toEmails"
														placeholder="Email To" autocomplete="off" required>
												</div>
											</div>
											
											 
											
											<div class="form-group">
												 
												<div class="col-sm-12">
													<input type="text" height="100px" class="form-control input-sm"
														name="ccEmails"
														placeholder="CC Emails" autocomplete="off" required>
												</div>
											</div>
											<div class="form-group">
												 
												<div class="col-sm-12">
													<input type="text" height="100px" class="form-control input-sm"
														name="bccEmails"
														placeholder="BCC Emails" autocomplete="off" required>
												</div>
											</div>
											
										  
									</div>
								</div>
							</div>
						</div>
						<!--  support -->
					</div>
				</div>
 			<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" id="addcommonconfig" class="btn btn-primary btn-lg">Add</button>
					</div>
				</div>
			</form>
		</section>
	</div>

	<%@ include file="DashboardFooter.jsp"%>

<script>
$(document).ready(function(){		
	
	 $("#formSubmit").validate({
   	 ignore: false,  
   	rules: {
           "email": {
               required: true,
               email: true
           },
       },
       
       messages: {
           "email": {
               required: "Please, enter an email",
               email: "Email is invalid"
           },
       },
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
   });
   
	
	
$('#addcommonconfig').click(function() {
	 $("#addcommonConfig").valid();
	 if($("#addcommonConfig").valid()){ 
    	document.getElementById("addcommonConfig").submit();
    }
})


})
</script>
</body>

</html>