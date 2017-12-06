<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>
<%-- <script src="js/angular.js" type="text/javascript"></script>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
 
<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"userorcompanyprofile";
	$('#success').click(function() {
	 //window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 });
 </script>
<%-- <script type="text/javascript">
  $(function(){
	   var txt = $('.success-alert').text();
	   var protocol=location.protocol;
   	   var host=location.host;
   	   var url=protocol+"//"+host+"/LintasTravelAdmin/userorcompanyprofile";
	 if(txt.length>0){
 	   alert(txt); 
		  window.location.assign(url);
 	   
	 }
 });  
   
 </script>
 --%>
 </head>
<body data-ng-controller="AppCtrl">
  <!-- EDIT USER PROFILE -->
  
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Edit User Profile 
					<!-- <small>Control panel</small> -->
				</h1>
				<!-- <ol class="breadcrumb">
					<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol> -->
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->
				<div class="row">
					<div class="col-sm-12">
						<h1 class="page-header">
							Edit Profile <a href="userorcompanyprofile"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Profile</span></a>
						</h1>
					</div>
					<!-- Small boxes (Stat box) -->
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
				<div class="row">
 	<h3>Change Info   <a href="resetUserProfilePassword?id=<s:property value="%{#session.User.id}"/>"
															class="btn btn-success btn-xs" data-toggle="modal">
														Reset password
														</a>  </h3>
										
					
					<div class=" col-xs-12 personal-info">
 
					 
							<form id="userProfileUpdateForm" class="form-horizontal" role="form" action="userProfileUpdate"
							method="post" enctype="multipart/form-data">
								<div class="row">
							<div class="col-sm-4">
							 <div class="form-group">
					<div class=" col-sm-12 col-xs-12" ng-cloak>
						<div class="text-center">

							<img
								src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>"
								class="avatar img-circle img-thumbnail" alt="profile image" />
							<h6>Upload a different photo...</h6>
							<div class="col-sm-6 ">
								<div id="fileinfo">

									<div id="fileError"></div>


								</div>              
							</div>
							<input type="file" id="uploadimage" accept="image/*"
								  ng-file-select="onFileSelect($files)"
								class="text-center center-block well well-sm"  name="Imagepath" >
						</div>
					</div>
					</div>
					
					</div>
							
					<div class="col-sm-8">		
							
							<div class="form-group">
								<label for="Company" class="col-sm-2 control-label">
									User ID </label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="username"
										name="Username"
										value="<s:property value="%{#session.User.Username}"/>"
										placeholder="User ID " autocomplete="off"  disabled="disabled"  required>
								 
								</div>
							</div>
							 
							

							<div class="form-group">
								<label for="Website" class="col-sm-2 control-label">First
									Name</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm"
										id="first-name" name="Firstname"
										value="<s:property value="%{#session.User.Firstname}"/>"
										placeholder="First Name" autocomplete="off" required>
								</div>
							</div>

							<div class="form-group">
								<label for="Website" class="col-sm-2 control-label">Last
									Name</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="last-name"
										name="Lastname"
										value="<s:property value="%{#session.User.Lastname}"/>"
										placeholder="Last Name" autocomplete="off" required>
								</div>
							</div>

						<%-- 	<div class="form-group">
								<label for="Country" class="col-sm-2 control-label">User
									Role</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" id="userroletype"
										name="userroletype" required>

										<option selected="selected"
											value="<s:property value="%{#session.User.userroletype}"/>"><s:property
												value="%{#session.User.userroletype}" /></option>
										<option value="admin">Admin</option>
										<option value="report">Reports</option>
										<option value="order">Order</option>

									</select>
								</div>
							</div> --%>

						<%-- <div class="form-group">
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
							</div> --%>

							<div class="form-group">
								<label for="Email" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-8">
									<input   type="email" class="form-control input-sm" name="Email"
										id="email"
										value="<s:property value="%{#session.User.Email}"/>"
										placeholder="Email" disabled="disabled" autocomplete="off" required>
								</div>
							</div>
							 

							<div class="form-group">
								<label for="Address" class="col-sm-2 control-label">Address</label>
								<div class="col-sm-8">
									<textarea class="form-control input-sm" id="address"
										name="Address" placeholder="Address" autocomplete="off"
										required><s:property
											value="%{#session.User.Address}" /></textarea>

								</div>
							</div>

							<div class="form-group">
								<label for="Country" class="col-sm-2 control-label">Country</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" name="Countryname"
										id="country" autocomplete="off" required>
										<option
											value="<s:property value="%{#session.User.Countryname}"/>"
											selected="selected"><s:property
												value="%{#session.User.Countryname}" /></option>

										<s:iterator value="#session.countryList">
											<option value="<s:property value="c_name"/>"><s:property
													value="c_name"></s:property></option>
										</s:iterator>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="City" class="col-sm-2 control-label">City</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" name="City"
										id="city" placeholder="City" autocomplete="off" required
										value="<s:property value="%{#session.User.City}"/>">
								</div>
							</div>

							<div class="form-group">
								<label for="telphone" class="col-sm-2 control-label">Mobile</label>
								<div class="col-sm-8">
									<input type="tel" class="form-control input-sm" name="Mobile"
										id="telphone"
										value="<s:property value="%{#session.User.Mobile}"/>"
										placeholder="8105979291" autocomplete="off" onkeypress="return isNumberKey(event,this);" required>
								</div>
							</div>

							<div class="form-group">
								<label for="Description" class="col-sm-2 control-label">Description</label>
								<div class="col-sm-8">
									<textarea class="form-control input-sm" id="Description"
										name="Description" placeholder="Description"
										autocomplete="off" required><s:property
											value="%{#session.User.Description}" /></textarea>
								</div>
							</div>

							<%-- <div class="form-group">
								<label for="Country" class="col-sm-2 control-label">
									Security Question</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" id="Question"
										name="Securityquestion" required>
										<option selected="selected"><s:property
												value="%{#session.User.Securityquestion}" /></option>
										<option>What was your childhood nickname?</option>
										<option>What is the name of your favorite childhood
											friend ?</option>
										<option>What street did you live on in third grade?</option>
										<option>What is your oldest sibling's birthday month
											and year?</option>
										<option>What is the middle name of your oldest child?</option>
										<option>What is your oldest sibling's middle name?</option>
										<option>What school did you attend for sixth grade?</option>
										<option>What was the name of your first stuffed
											animal?</option>
										<option>What is your maternal grandmother's maiden
											name?</option>
										<option>In what town was your first job?</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="Question" class="col-sm-2 control-label">Answer</label>

								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="Answer"
										name="Securityanswer"
										value="<s:property value="%{#session.User.Securityanswer}"/>"
										placeholder="Answer" autocomplete="off" required>
								</div>
							</div> --%>

							<div class="form-group">
								<label class="col-md-3 control-label"></label>
								<div class="col-md-8">
									<!-- <input class="btn btn-primary" value="Save Changes"
										type="submit"> -->
										<input id="userProfileSubmit" class="btn btn-primary" value="Save Changes"
										type="button">
								</div>
							</div>
							</div>
							</div>
						</form>
					</div>
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
 


	<!-- /.content-wrapper -->
	
	<%@ include file="DashboardFooter.jsp"%>
	<script src="js/app.js" type="text/javascript"></script>
	<%-- 	<%@ include file="DashFooter.jsp"%> --%>
	<script type="text/javascript">
	function checkPasswordMatch() {
	    var password = $("#password").val();
	    var confirmPassword = $("#confirmPwd").val();

	    if (password !=confirmPassword)
	        $("#error").html("Passwords are not match!");
	    
	    else{
	    	 
	    	 $("#error").html("");
	     
	     
	}
	}

	$(document).ready(function () {
	   $("#confirmPwd").keyup(checkPasswordMatch);
	});

	$(document).ready(function(){
		  
		   $('#userProfileSubmit').click(function(){
			   $("#userProfileUpdateForm").valid();
			   if($("#userProfileUpdateForm").valid()){
				   document.getElementById("userProfileUpdateForm").submit();
			   }
		   });
		 		   
		   $("#userProfileUpdateForm").validate({
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
	 function isNumberKey(evt,obj){
		    var charCode = (evt.which) ? evt.which : event.keyCode;
		    if (charCode > 31  && (charCode > 57 || charCode <46 ))  
		   	 return false;
		   
		} 
	</script>
</body>
</html>
