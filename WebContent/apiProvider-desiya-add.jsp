<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<style type="text/css">
.error {
    color:red;
}
.valid {
    color:green;
}
</style>

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Add Desiya Config</h1>
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
			<form action="createDesiyaConfig" method="post"
				class="form-horizontal" name="myForm" id="addDesiya">
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Title</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="title"
							name="title" placeholder="title" autocomplete="off"
							required min="10" maxlength="30" required>
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
					<label for="first-name" class="col-sm-2 control-label">UseName</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="userName"
							name="userName" placeholder="user Name" autocomplete="off"
							required min="4" maxlength="50" required>
					</div>
				</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">PassWord</label>
					<div class="col-sm-8">
						<input type="password" class="form-control input-sm" id="password"
							name="password" placeholder="password" autocomplete="off"
							required min="6" maxlength="30" required>
					</div>
				</div>
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Currency</label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="apiCurrency"
							name="apiCurrency" placeholder="api Currency" autocomplete="off"
							  required>
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
					<label for="last-name" class="col-sm-2 control-label">Provider Id</label>
					<div class="col-sm-8">
						 <input type="number" class="form-control input-sm" id="propertyId"
							name="providerId" min="0"  placeholder="Provider Id" autocomplete="off"
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
					<label for="first-name" class="col-sm-2 control-label">EndPointUrl</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="endPointUrl"
							name="endPointUrl" placeholder="EndPoint Url" autocomplete="off"
							  required>
					</div>
				</div>

				 
				<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Add</button>
					</div>
				</div>
			</form>
		</section>
	</div>


	<%@ include file="DashboardFooter.jsp"%>
	
	<script type="text/javascript">
	
	$(document).ready(function(){

		 	jQuery.validator.addMethod("productTypeVal",function(value) {
	            var startprice =  $('#managementFeesForSend').val();
	            return (startprice !=  '00.00');
	          }, "please select product type");
	     
	  
	             $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
	                  return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
	              }, "This field cannot have symbols.");

	              $.validator.addMethod("cusValidationAlphaName",function(value,element){
	                  return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
	              },"This field cannot have numbers and symbols."); 
	              $.validator.addMethod("cusValidationforprice",function(value,element){
	                  return this.optional(element) || /^[0-9]+$/i.test(value);
	              },"This field cannot have Char and symbols.");
	

		    $("#addDesiya").validate({
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
		        submitHandler: function (form) { // for demo
		           // alert('valid form submitted'); // for demo
		            return false; // for demo
		        },
		        invalidHandler: function(e,validator) {
		            for (var i=0;i<validator.errorList.length;i++){   
		                $(validator.errorList[i].element).parents('.panel-collapse.collapse').collapse('show');
		            }
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
		    
		     $('#buttonSubmit').click(function() {
		       if($("#addDesiya").valid())
		        	document.getElementById("addDesiya").submit();
		     	 
		    }); 

		});
	</script>
</body>

</html>