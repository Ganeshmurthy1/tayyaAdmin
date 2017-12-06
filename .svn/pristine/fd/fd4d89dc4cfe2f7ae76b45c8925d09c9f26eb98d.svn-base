<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Config</title>
<%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>

 <link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"mailConfigList";
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
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Update Mail Configuration <%-- <small>Control panel</small> --%>
			</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>

		<!-- Main content -->
		<section class="content">
			 <div class="col-sm-12">
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
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
				 
				 	<form action="updateMailConfigData" method="post" id="myform"
					class="form-horizontal">
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">MailConfig Name</label>
						<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
								id="mail_config_name" name="mail_config_name"
								placeholder="mail_config_name"    
								  value="<s:property value="%{#session.MailconfigProfile.mail_config_name}"/>"
								 autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">Server Host
							Branch</label>
						<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="host"
								placeholder="mail_server_host"  name="mail_server_host"
								
								  value="<s:property value="%{#session.MailconfigProfile.mail_server_host}"/>"
								autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">Server User</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="user" name="mail_server_user" 
							  value="<s:property value="%{#session.MailconfigProfile.mail_server_user}"/>"
							 placeholder="mail_server_user" autocomplete="off" required>
						</div>
					</div>

					<div class="form-group">
						<label for="Password" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-8">
							<input type="password" ng-model="password" class="form-control input-sm" name="mail_server_password"  
							 value="<s:property value="%{#session.MailconfigProfile.mail_server_password}"/>"
							 id="password" placeholder="********" autocomplete="off" required>
						</div>
					</div>


					<div class="form-group">
						<label for="telphone" class="col-sm-2 control-label">Port</label>
						<div class="col-sm-8">
							 <input type="number" class="form-control input-sm" id="port" name="mailServerPort"
						   value="<s:property value="%{#session.MailconfigProfile.mailServerPort}"/>"
							placeholder="mailServerPort" autocomplete="off" required>   
						</div>
					</div>
					 
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Update Changes</button>
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
	
   <%@ include file="DashboardFooter.jsp"%> 
   <script type="text/javascript" src="js/app.js"></script>
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


$("#myform").validate({
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

$('#buttonSubmit').click(function() {
	   if($("#myform").valid())
	    	document.getElementById("myform").submit();
	}); 
});
</script>  
</body>

</html>