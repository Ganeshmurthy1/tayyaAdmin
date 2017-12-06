<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

<link rel="stylesheet" href="css/alert.css">
	<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
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

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Update e-Travel Smart Config</h1>
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
			<form action="updateEtravelSmartConfig" method="post"
				class="form-horizontal" name="myForm" id="apiProvder">
				<input type="hidden"   
							name="id"  
							 value="${apiProviderEtravelSmartConfig.id}" />
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Title</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="title"
							name="title" placeholder="Title" autocomplete="off"
							required min="10" maxlength="40"  value="${apiProviderEtravelSmartConfig.title}" />
					</div>
				</div>
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Status </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="active" required="required">
							<c:choose>
    							<c:when test="${apiProviderEtravelSmartConfig.active}">
       								<option value="true" selected="selected">active</option>
       								<option value="false" >inactive</option>
   								</c:when>    
							    <c:otherwise>
							    <option value="true" >active</option>
							    	<option value="false" selected="selected">inactive</option>
							    </c:otherwise>
							</c:choose>
							
						 </select>
					</div>
 					</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Bus UserName</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="userName"
							name="busUserName" placeholder="user Name" autocomplete="off"
							required min="10" maxlength="30" value="${apiProviderEtravelSmartConfig.busUserName}" />
					</div>
				</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Bus PassWord</label>
					<div class="col-sm-8">
						<input type="password" class="form-control input-sm" id="password"
							name="busPassword" placeholder="password" autocomplete="off"
							required min="10" maxlength="30" value="${apiProviderEtravelSmartConfig.busPassword}" />
					</div>
				</div>
				 
			<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Currency</label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="apiCurrency"
							name="apiCurrency" placeholder="api Currency" autocomplete="off"
							 value="${apiProviderEtravelSmartConfig.apiCurrency}" required />
					</div>
 					</div>
				
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Environment </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="environment" required="required">
							
     						 <c:choose>
    							<c:when test="${apiProviderEtravelSmartConfig.environment eq 'test'}">
       								<option value="test" selected="selected">Test</option>
       								<option value="live">Live</option>
   								</c:when>    
							    <c:otherwise>
							    <option value="test">Test</option>
							    	<option value="live" selected="selected">Live</option>
							    </c:otherwise>
							</c:choose>
								
						 </select>
					</div>
 					</div>
 						
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Url</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="url"
							name="url" placeholder="Enter Url" autocomplete="off"
							  value="${apiProviderEtravelSmartConfig.url}" required />
					</div>
				</div>

				 
				<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Update</button>
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