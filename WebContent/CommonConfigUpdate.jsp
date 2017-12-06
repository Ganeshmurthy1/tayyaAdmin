<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

<%-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>

 

<link rel="stylesheet" href="css/alert.css">

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
			<h1>Update CommonConfig</h1>
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
			<form action="updateCommonConfig" method="post"
				class="form-horizontal" name="myForm">
				 <div class="form-group">
				 <input type="hidden"name="id" value="<s:property value="commonConfig.id"/>" >
					<label for="last-name" class="col-sm-2 control-label">Server Mode </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="serverMode" required="required">
						  <s:if test="commonConfig.serverMode=='live'">
							<option value="live" selected="selected">Live</option>
							<option value="test">Test</option>
							
							</s:if>
							<s:if test="commonConfig.serverMode=='test'">
							<option value="live" >Live</option>
							<option value="test" selected="selected">Test</option>
							</s:if>
						 </select>
					</div>
 					</div>
 					
 					<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Status</label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="isActive" required="required">
							 <s:if test="commonConfig.Active==true">
							<option value="1" selected="selected">Active</option>
							<option value="0">In Active</option>
							
							</s:if>
							 <s:if test="commonConfig.Active==false">
							<option value="1" >Active</option>
							<option value="0" selected="selected">In Active</option>
							</s:if>
						 </select>
					</div>
 					</div>
				
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Server Log Path</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="serverLogLocation"
							name="serverLogLocation" placeholder="Server Log Path" 
							value="<s:property value="commonConfig.serverLogLocation"/>"  autocomplete="off"
							required>
					</div>
				</div>
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Invoice Hotel Prefix</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="invoiceHotelPrefix"
							name="invoiceHotelPrefix" placeholder="Invoice Hotel Prefix" autocomplete="off"
							required value="<s:property value="commonConfig.invoiceHotelPrefix"/>" >
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Invoice Flight Prefix</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="invoiceFlightPrefix"
							name="invoiceFlightPrefix" 
							value="<s:property value="commonConfig.invoiceFlightPrefix"/>"
							
							placeholder="Invoice  Flight Prefix " autocomplete="off"
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Admin Url</label>
					<div class="col-sm-8">
						<input type="url" class="form-control input-sm" id="adminUrl"
							name="adminUrl" placeholder="Admin Url" autocomplete="off"
							
							value="<s:property value="commonConfig.adminUrl"/>"
							
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">IBE Url</label>
					<div class="col-sm-8">
						<input type="url" class="form-control input-sm" id="ibeUrl"
							name="ibeUrl" placeholder="IBE Url" autocomplete="off"
							value="<s:property value="commonConfig.ibeUrl"/>"
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">API Url</label>
					<div class="col-sm-8">
						<input type="url" class="form-control input-sm" id="apiUrl"
							name="apiUrl" placeholder="api Url" autocomplete="off"
							value="<s:property value="commonConfig.apiUrl"/>"
							required>
					</div>
				</div>
				
				 <div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Image Url</label>
					<div class="col-sm-8">
						<input type="url" class="form-control input-sm" id="imageUrl"
							name="imagePath" placeholder="Image Url" autocomplete="off"
								value="<s:property value="commonConfig.imagePath"/>"
							required>
					</div>
				</div>
				 
				
				 <div class="form-group">
					<label class="col-sm-2 control-label"> </label>
					<div class="col-sm-8">
						<div class="support">
 							<h4>Email Service PaymentUrls</h4>
						<div id="support">
								
								<!-- level1 -->
								<div class="level1">
								
									<div id="level1">
										<div class="well">
											 <div class="form-group">
												
												<div class="col-sm-12">
												<b>Flight Pending PaymentUrl</b> 
													<input type="url" class="form-control input-sm"
														name="emailServiceFlightPendingPaymentUrl"
														placeholder="Flight Pending PaymentUrl" 
															value="<s:property value="commonConfig.emailServiceFlightPendingPaymentUrl"/>"
														 autocomplete="off" required>
												</div>
											</div>
											<div class="form-group">
											
												<div class="col-sm-12">
												<b>Hotel Pending PaymentUrl</b> 
													<input type="url" class="form-control input-sm"
														name="emailServiceHotelPendingPaymentUrl"
														value="<s:property value="commonConfig.emailServiceHotelPendingPaymentUrl"/>"
														placeholder="Hotel Pending PaymentUrl" autocomplete="off" required>
												</div>
											</div>
											
											
 										</div>
									</div>
								</div>
  

							</div>
							
							<div   id="support">
							<h4>Email ServiceUrls</h4>
							 
								 
								<!-- level1 -->
								<div class="level1">
								
									<div id="level1">
										<div class="well">
											 <div class="form-group">
												 <div class="col-sm-12">
												 <b>Email SendUrl</b> 
													<input type="url" class="form-control input-sm"
														name="emailServiceUrl"
														value="<s:property value="commonConfig.emailServiceUrl"/>"
														placeholder="Email Send Url" autocomplete="off" required>
												</div>
											</div>
											<div class="form-group">
												 
												<div class="col-sm-12">
												 <b>Email VerifyUrl</b> 
													<input type="url" class="form-control input-sm"
														name="emailVerifyUrl"
														value="<s:property value="commonConfig.emailVerifyUrl"/>"
														placeholder="Email Verify Url" autocomplete="off" required>
												</div>
											</div>
											
											
 										</div>
									</div>
								</div>
  

							</div>
							
							 <div  id="support">

								<h4>Email Details</h4>
								<!-- level1 -->
								<div class="level1">


									<div id="level1">

										<div class="well">
										 <div class="form-group">
												 
												<div class="col-sm-12">
												 <b>Max Email Attempts</b> 
													<input type="text" class="form-control input-sm"
														name="maxEmailAttempts"
														value="<s:property value="commonConfig.maxEmailAttempts"/>"
														placeholder="Max Email Attempts" autocomplete="off" required>
												</div>
											</div>
											 <div class="form-group">
											 
												<div class="col-sm-12">
												 <b>Max Email QueueSize</b> 
													<input type="text" class="form-control input-sm"
														name="maxEmailQueueSize"
														value="<s:property value="commonConfig.maxEmailQueueSize"/>"
														placeholder="Max Email Queue Size" autocomplete="off" required>
												</div>
											</div>
											
											 <div class="form-group">
												 
												<div class="col-sm-12">
												<b>To Email(s)</b> 
												<textarea rows="3" cols="4" class="form-control input-sm"
														name="toEmails"> <s:property value="commonConfig.toEmails"/> </textarea>
												
												
													<!-- <input type="email" class="form-control input-sm"
														name="toEmails"
														placeholder="Email To" autocomplete="off" required> -->
												</div>
											</div>
											
											 
											
											<div class="form-group">
												 
												<div class="col-sm-12">
												<b>CC Email(s)</b> 
												<textarea rows="3" cols="4" class="form-control input-sm"
														name="ccEmails"> <s:property value="commonConfig.ccEmails"/> </textarea>
												
												
												
													<!-- <input type="text" height="100px" class="form-control input-sm"
														name="ccEmails"
														placeholder="CC Emails" autocomplete="off" required> -->
												</div>
											</div>
											<div class="form-group">
												 		
												<div class="col-sm-12">
												<b>BCC Email(s)</b> 
													<textarea rows="3" cols="4" class="form-control input-sm"
														name="bccEmails"> <s:property value="commonConfig.bccEmails"/> </textarea>
												
												
												<!-- 	<input type="text" height="100px" class="form-control input-sm"
														name="bccEmails"
														placeholder="BCC Emails" autocomplete="off" required> -->
												</div>
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
						<button type="submit" class="btn btn-primary btn-lg">Update</button>
					</div>
				</div>
			</form>
		</section>
	</div>

	<%@ include file="DashboardFooter.jsp"%>

</body>

</html>