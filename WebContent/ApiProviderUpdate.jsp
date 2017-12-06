<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<link rel="stylesheet" href="css/alert.css">
<script src="js/jquery.js" type="text/javascript"></script>
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Update ApiProvider Details</h1>
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
			<form action="apiProviderUpdate" id="apiProvidrUpdate" method="post" class="form-horizontal" name="myForm">
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Vendor
						Name</label>
						<input type="hidden" 
							name="id"   value="${apiProviderNew.id}" >
							<input type="hidden" 
							name="apiTravelType.travelTypeId"   value="${apiProviderNew.apiTravelType.travelTypeId}" >
							<input type="hidden" 
							name="apiProviderSupportDetails.id"   value="${apiProviderNew.apiProviderSupportDetails.id}">
							<input type="hidden" 
							name="apiProviderTechSupportDetails.id"   value="${apiProviderNew.apiProviderTechSupportDetails.id}" >
						 	<input type="hidden" 
							name="apiProvider"   value="${apiProviderNew.apiProvider}" >
						 	
						<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="vendorName"
							name="vendorName"   value="${apiProviderNew.vendorName}" placeholder="Vendor Name" autocomplete="off"
							required min="10" maxlength="30" required>
					</div>
				</div>
				
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Api Mode </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="apiMode" required="required">
							<s:if test="apiProviderNew.apiMode=='live'">
								<option value="live" selected="selected">Live</option>
							<option value="test">Test</option>
							<option value="dev">Dev</option>
							</s:if>
							<s:elseif test="apiProviderNew.apiMode=='test'">
								<option value="live">Live</option>
							<option value="test" selected="selected">Test</option>
							<option value="dev">Dev</option>
							</s:elseif>
							<s:elseif test="apiProviderNew.apiMode=='dev'">
								<option value="live">Live</option>
							<option value="test" >Test</option>
							<option value="dev" selected="selected">Dev</option>
							</s:elseif>
							<s:else>
								<option value="live">Live</option>
							<option value="test" >Test</option>
							<option value="dev">Dev</option>
							 </s:else>
							
						 </select>
					</div>
 					</div>
				
				  <div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Travel
						Type </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="travelTypeList"   required >
							<option value="">Select Travel Type</option>
							<option value="flight" selected="selected">Flight</option>
							<option value="hotel">Hotel</option>
							<option value="car">Car</option>
							<option value="bus">Bus</option>
							<option value="train">Train</option>
							<option value="visa">Visa</option>
							<option value="insurance">Insurance</option>
							
							

						</select>
						
					</div>

				</div>
				
				<%-- <div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Travel
						Type </label>
					<div class="col-sm-8">
						<select  multiple="multiple"    class="form-control input-sm"  name="travelTypeList" required="required">
						<s:if test="apiProviderNew.apiTravelType.isFlight() && apiProviderNew.apiTravelType.isHotel() && apiProviderNew.apiTravelType.isBus() && apiProviderNew.apiTravelType.isCar()">
										<option value="flight" selected="selected">flight</option>
										<option value="hotel" selected="selected">hotel</option>
										<option value="bus" selected="selected">bus</option>
										<option value="car" selected="selected">car</option>
										 </s:if>
										  
										 <s:elseif test="apiProviderNew.apiTravelType.isFlight() && apiProviderNew.apiTravelType.isHotel() && apiProviderNew.apiTravelType.isBus()">
										  <option value="flight" selected="selected">flight</option>
										<option value="hotel" selected="selected">hotel</option>
										<option value="bus" selected="selected">bus</option>
										<option value="car">car</option>
										</s:elseif>
										 
										 <s:elseif test="apiProviderNew.apiTravelType.isFlight() && apiProviderNew.apiTravelType.isHotel()">
										  <option value="flight" selected="selected">flight</option>
										<option value="hotel" selected="selected">hotel</option>
										<option value="bus">bus</option>
										<option value="car">car</option>
										</s:elseif>
										 
										 <s:elseif test="apiProviderNew.apiTravelType.isFlight() && apiProviderNew.apiTravelType.isBus()">
										 <option value="flight" selected="selected">flight</option>
										<option value="hotel">hotel</option>
										<option value="bus" selected="selected">bus</option>
										<option value="car">car</option>
										 </s:elseif>
										  <s:elseif test="apiProviderNew.apiTravelType.isFlight() && apiProviderNew.apiTravelType.isCar()">
										<option value="flight" selected="selected">flight</option>
										<option value="hotel">hotel</option>
										<option value="bus">bus</option>
										<option value="car" selected="selected">car</option>
										 </s:elseif>
										 <s:elseif test="apiProviderNew.apiTravelType.isHotel() && apiProviderNew.apiTravelType.isBus() && apiProviderNew.apiTravelType.isCar()">
										<option value="flight">flight</option>
										<option value="hotel" selected="selected">hotel</option>
										<option value="bus" selected="selected">bus</option>
										<option value="car" selected="selected">car</option>
										 </s:elseif>
										 <s:elseif test="apiProviderNew.apiTravelType.isHotel() && apiProviderNew.apiTravelType.isCar()">
										<option value="flight">flight</option>
										<option value="hotel" selected="selected">hotel</option>
										<option value="bus">bus</option>
										<option value="car" selected="selected">car</option>
										 </s:elseif>
										 
										  <s:elseif test="apiProviderNew.apiTravelType.isBus() && apiProviderNew.apiTravelType.isCar()">
										<option value="flight">flight</option>
										<option value="hotel">hotel</option>
										<option value="bus" selected="selected">bus</option>
										<option value="car" selected="selected">car</option>
										 </s:elseif>
										  <s:elseif test="apiProviderNew.apiTravelType.isHotel() && apiProviderNew.apiTravelType.isBus()">
										<option value="flight">flight</option>
										<option value="hotel" selected="selected">hotel</option>
										<option value="bus" selected="selected">bus</option>
										<option value="car">car</option>
										</s:elseif> 
										 
										  <s:elseif test="apiProviderNew.apiTravelType.isFlight()">
										<option value="flight" selected="selected">flight</option>
										<option value="hotel">hotel</option>
										<option value="bus">bus</option>
										<option value="car">car</option>
										 </s:elseif>
										 <s:elseif test="apiProviderNew.apiTravelType.isHotel()">
										<option value="flight">flight</option>
										<option value="hotel" selected="selected">hotel</option>
										<option value="bus">bus</option>
										<option value="car">car</option>
										 </s:elseif>
										  <s:elseif test="apiProviderNew.apiTravelType.isBus()">
										<option value="flight">flight</option>
										<option value="hotel">hotel</option>
										<option value="bus"  selected="selected">bus</option>
										<option value="car">car</option>
										 </s:elseif>
										 <s:elseif test="apiProviderNew.apiTravelType.isCar()">
										<option value="flight">flight</option>
										<option value="hotel">hotel</option>
										<option value="bus">bus</option>
										<option value="car"  selected="selected">car</option>
										 </s:elseif>
						 </select>
					</div>
				</div>
 --%>
 				<%-- 	
				<div class="form-group">
					<label class="col-sm-2 control-label"> </label>
					<div class="col-sm-8">
						<div class="support">

							<!-- <a class="h5" role="button" data-toggle="collapse"
								href="#support" aria-expanded="true"> Support Details <i
								class="fa fa-plus btn btn-primary"></i>
							</a> -->

							<div  id="support">

								<h4>Support details</h4>
								<!-- level1 -->
								<div class="level1">


									<div id="level1">

										<div class="well">
											<p class="h4">Level:1</p>
											<div class="form-group">
												<label class="col-sm-2 control-label">Email</label>
												<div class="col-sm-8">
													<input type="email" class="form-control input-sm"
														name="apiProviderSupportDetails.email1"
														placeholder="Support Email"  value="${apiProviderNew.apiProviderSupportDetails.email1}"  autocomplete="off" required>
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label">Mobile/Phone</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm"
														name="apiProviderSupportDetails.mob1"   value="${apiProviderNew.apiProviderSupportDetails.mob1}"          placeholder="phone"
														autocomplete="off" required>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">Name</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="apiProviderSupportDetails.name1"    value="${apiProviderNew.apiProviderSupportDetails.name1}"              placeholder="Name"
														autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">Location</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="apiProviderSupportDetails.loc1"
														placeholder="Location"   value="${apiProviderNew.apiProviderSupportDetails.loc1}"               autocomplete="off">
												</div>
											</div>


										</div>
									</div>
								</div>


								<!-- level1 -->

								<!-- level1 -->
								<div class="level1">
									<div id="level1">
										<div class="well">
											<p class="h4">Level:2</p>
											<div class="form-group">
												<label class="col-sm-2 control-label">Email</label>
												<div class="col-sm-8">
													<input type="email" class="form-control input-sm"
														name="apiProviderSupportDetails.email2"  value="${apiProviderNew.apiProviderSupportDetails.email2}" 
														placeholder="Support Email" autocomplete="off">
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label">Mobile/Phone</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm"
														name="apiProviderSupportDetails.mob2"  value="${apiProviderNew.apiProviderSupportDetails.mob2}"    placeholder="phone"
														autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">Name</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="apiProviderSupportDetails.name2"  value="${apiProviderNew.apiProviderSupportDetails.name2}"   placeholder="Name"
														autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">Location</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="apiProviderSupportDetails.loc2"  value="${apiProviderNew.apiProviderSupportDetails.loc2}" 
														placeholder="Location" autocomplete="off">
												</div>
											</div>


										</div>
									</div>
								</div>


								<!-- level1 -->


							</div>
							<!-- support -->
							<!-- tech support -->
							<!-- <a class="h5" role="button" 
								href="#tecsupport" > Technical Support
								<i class="fa fa-plus btn btn-primary"></i>
							</a> -->


							<div  id="tecsupport">

								<h4>Technical Support details</h4>
								<!-- level1 -->
								<div class="level1">
									<div id="level1">
										<div class="well">
											<p class="h4">Level:1</p>
											<div class="form-group">
												<label class="col-sm-2 control-label">Email</label>
												<div class="col-sm-8">
													<input type="email" class="form-control input-sm"
														name="apiProviderTechSupportDetails.email1"
														placeholder="Email" value="${apiProviderNew.apiProviderTechSupportDetails.email1}"       autocomplete="off" required>
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label">Mobile/Phone</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm"
														name="apiProviderTechSupportDetails.mob1"
														placeholder="Mobile/Phone"  value="${apiProviderNew.apiProviderTechSupportDetails.mob1}"           autocomplete="off" required>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">Name</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="apiProviderTechSupportDetails.name1"
														placeholder="Name"    value="${apiProviderNew.apiProviderTechSupportDetails.name1}"             autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">Location</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="apiProviderTechSupportDetails.loc1"
														placeholder="Location"  value="${apiProviderNew.apiProviderTechSupportDetails.loc1}"            autocomplete="off" required>
												</div>
											</div>

										</div>
									</div>
								</div>


								<!-- level1 -->

								<!-- level1 -->
								<div class="level1">
									<div id="level1">
										<div class="well">
											<p class="h4">Level:2</p>
											<div class="form-group">
												<label class="col-sm-2 control-label">Email</label>
												<div class="col-sm-8">
													<input type="email" class="form-control input-sm"
														name="apiProviderTechSupportDetails.email2"
														placeholder="Email"  value="${apiProviderNew.apiProviderTechSupportDetails.email2}"             autocomplete="off">
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label">Mobile/Phone</label>
												<div class="col-sm-8">
													<input type="number" class="form-control input-sm"
														name="apiProviderTechSupportDetails.mob2"
														placeholder="Mobile/Phone"   value="${apiProviderNew.apiProviderTechSupportDetails.mob2}"               autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">Name</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="apiProviderTechSupportDetails.name2"
														placeholder="Name"  value="${apiProviderNew.apiProviderTechSupportDetails.name2}"                autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">Location</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														name="apiProviderTechSupportDetails.loc2"
														placeholder="Location"   value="${apiProviderNew.apiProviderTechSupportDetails.loc2}"           autocomplete="off">
												</div>
											</div>

										</div>
									</div>
								</div>


								<!-- level1 -->


							</div>
							<!-- tech support ends -->

						</div>

						<!--  support -->

					</div>
				</div> --%>

				<div class="form-group">
					<label for="Address" class="col-sm-2 control-label">Address</label>
					<div class="col-sm-8">
						<textarea class="form-control input-sm" id="address"
							name="supportAddress"  placeholder="Support Address" maxlength="300" required> ${apiProviderNew.supportAddress} </textarea>
					</div>
				</div>
				 
				<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button id="apiProvidrUpdateBtn" type="button" class="btn btn-primary btn-lg">Update</button>
					</div>
				</div>
			</form>
		</section>
	</div>

	<%@ include file="DashboardFooter.jsp"%>

</body>
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
	$(document).ready(function(){
		  
		   $('#apiProvidrUpdateBtn').click(function(){
			   $("#apiProvidrUpdate").valid();
			   if($("#apiProvidrUpdate").valid()){
				   document.getElementById("apiProvidrUpdate").submit();
			   }
		   });
		   $("#apiProvidrUpdate").validate({
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
</html>