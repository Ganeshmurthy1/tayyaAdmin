<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>

<%-- <script src="js/angular.js" type="text/javascript"></script>
 <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"superUserCompanyList";
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
		  <h1>Reset Password</h1> 
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
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
			<div class="row" id="dash-us-register">
 				<form action="updateCompanyProfilePassword" method="post" class="form-horizontal"
					name="myForm" id="resetPasswordForm">
					   <div class="form-group">
					   <input type="hidden"  name="companyid"  value="<s:property  value="%{CurrentCompany.companyid}"/>">
					    <input type="hidden"  name="Email"  value="<s:property  value="%{CurrentCompany.Email}"/>">
					   <input type="hidden"  name="id"  value="<s:property  value="%{currentUser.id}"/>">
								<label for="Website" class="col-sm-2 control-label"> New
									password </label>
								<div class="col-sm-8">
									<input type="password" class="form-control input-sm"
										name="Password" id="password" placeholder="Password"
										autocomplete="off" required>
								</div>
							</div>
							<div class="form-group">
								<label for="Website" class="col-sm-2 control-label">Confirm
									password </label>
 									<div class="col-sm-8">
 									<span style="color: red" id="error"> </span> <input
										type="password" class="form-control input-sm"
										name="conformPassword" id="confirmPwd" placeholder="Password"
										autocomplete="off" required>
								</div>
							</div>  
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" onclick="checkPasswordMatch()" class="btn btn-primary btn-lg">Reset Password</button>
						</div>
					</div>
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<script src="js/app.js" type="text/javascript"></script>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript">
		function checkPasswordMatch() {
			 if($("#resetPasswordForm").valid()){
				 var password = $("#password").val();
					var confirmPassword = $("#confirmPwd").val();
		 			if(password != confirmPassword)
						 $("#error").html("Passwords do not match");
		 			else {
		 				document.getElementById("resetPasswordForm").submit();
		 				}
			 }
		}
	</script> 
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


$("#resetPasswordForm").validate({
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
});
</script>  
</body>

</html>