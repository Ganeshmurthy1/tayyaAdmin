<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Register</title>
 <%-- <script src="js/angular.js" type="text/javascript"></script>

<dj:head />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<script type="text/javascript">
 
$(function(){
	   var txt = $('.success-alert').text();
    if(txt.length>0){
    	  var protocol=location.protocol;
	   	   var host=location.host;
	   	   var url=protocol+"//"+host+"/LintasTravelAdmin/showLintasCompanyList"; 
    	 alert(txt); 
 		   window.location.assign(url);
  }
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
			<h1>Child_Parent Company Registration</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<s:if test="hasActionErrors()">
				<div class="success-alert" style="display: none">
					<span class="fa fa-thumbs-o-up fa-1x"></span>
					<s:actionerror />
				</div>
			</s:if>
			<s:if test="hasActionMessages()">
				<div class="success-alert" style="display: none">
					<span class="fa fa-thumbs-o-up fa-1x"></span>
					<s:actionmessage />
				</div>
			</s:if>
			<div class="row" id="dash-us-register">

				<form action="child_ParentCompanyReg" method="post" class="form-horizontal"
					name="myForm" id="childParentCompany">
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Company
							Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="company"
								name="Companyname" placeholder="Company Name" autocomplete="off"
								required>
								 
								
						</div>
					</div>
				 <div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Company Role</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="companyRole"
								name="companyType" required>
								<option selected="selected" value="parent">Parent Company</option>
								 <option value="child">Child Company</option>
								 
								 
 						</select>
						</div>
					</div>  
					<div class="form-group">
						<label for="Website" class="col-sm-2 control-label">Website</label>
						<div class="col-sm-8">
							<input class="form-control input-sm" type="url" name="Website"
								placeholder="http://www.example.com" />
						</div>
					</div>
<!-- 
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">
							User ID </label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="username"
								name="Username" placeholder="User ID " autocomplete="off"
								required>
						</div>
					</div>
 -->
					<div class="form-group">
						<label for="Password" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-8">
							<input type="password" ng-model="password"
								class="form-control input-sm" name="Password" id="password"
								placeholder="Password" autocomplete="off" required>
						</div>
					</div>

					<div class="form-group">
						<label for="Email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-8">
							<input type="email" class="form-control input-sm" name="Email"
								id="email"   placeholder="Email" autocomplete="off" required>
						</div>
					</div>

					<div class="form-group">
						<label for="Address" class="col-sm-2 control-label">Address</label>
						<div class="col-sm-8">
							<textarea class="form-control input-sm" id="address"
								name="Address" placeholder="Address" autocomplete="off" required></textarea>

						</div>
					</div>

					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Country</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="Countryname"
								id="country" autocomplete="off" required>
								<option selected>Select Country</option>
								<s:iterator value="#session.countryList">
									<option><s:property value="c_name"></s:property></option>
								</s:iterator>

							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">City</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" name="City"
								id="city" placeholder="city" autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
						<label for="telphone" class="col-sm-2 control-label">Phone</label>
						<div class="col-sm-8">
							<input type="tel" class="form-control input-sm" name="Phone"
								id="telphone" placeholder="8105979291" autocomplete="off"
								required>
						</div>
					</div>


		<div class="border-common clearfix">

						<p class="h4">
							<b>Status</b>
						</p>
 						</div>

						<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Status</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="Status"
								name="Status" required>
							  <option  value="active">Active</option>
								<option   selected="selected" value="inactive">InActive</option>
									 
							</select>
						</div>
					</div>


 					<div class="border-common">

						<p class="h4">
							<b>Logo</b>
						</p>

					</div>
					<div class="form-group">
						<label for="uploadimage" class="col-sm-2 control-label">Upload
							Image</label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100"
											alt="user image">
									</figure>
									<input type="file" id="uploadimage" accept="image/*"
										ng-file-select="onFileSelect($files)"> <input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

							 
							</div>


						</div>

					</div>


					<div class="border-common">

						<p class="h4">
							<b>Branch</b>
						</p>

					</div>


					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Location</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" name="location"
								id="location" placeholder="location" autocomplete="off" required>
						</div>
					</div>

					<div class="form-group">
						<label for="Company-type" class="col-sm-2 control-label">Company
							Service Type</label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
									<div class="radio">
										<label> <input type="radio" name="Service"
											id="optionsRadios1" value="Airways" checked> Airways
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="Service"
											id="optionsRadios2" value="Car"> Car
										</label>
									</div>
								</div>

								<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
									<div class="radio">
										<label> <input type="radio" name="Service"
											id="optionsRadios1" value="Hotel"> Hotel
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="Service"
											id="optionsRadios2" value="other"> other
										</label>
									</div>
								</div>
							</div>
						</div>


					</div>

					<div class="border-common">

						<p class="h4">
							<b>Description</b>
						</p>

					</div>

					<div class="form-group">
						<label for="Description" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-8">
							<textarea class="form-control input-sm" id="Description"
								name="Companydescription" placeholder="Description"
								autocomplete="off" required></textarea>
						</div>
					</div>



					<div class="border-common">

						<p class="h4">
							<b>Billing Address</b>
						</p>

					</div>

					<div class="form-group">
						<label for="Company" class="col-sm-2 control-label">Company</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="company"
								name="Billingcompany" placeholder="Company Name"
								autocomplete="off" required>
						</div>
					</div>

					<div class="form-group">
						<label for="Reference" class="col-sm-2 control-label">Reference</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="Reference"
								name="Billingreference" placeholder="Your Reference"
								autocomplete="off" required>
						</div>
					</div>

					<div class="form-group">
						<label for="Address" class="col-sm-2 control-label">Address</label>
						<div class="col-sm-8">
							<textarea class="form-control input-sm" id="address"
								name="Billingaddress" placeholder="Address" autocomplete="off"
								required></textarea>
						</div>
					</div>
					<div class="form-group ">
						<label for="Country" class="col-sm-2 control-label">Country</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="Billingcountry"
								id="country" autocomplete="off" required>
								 <option selected>Select Country</option>
								<s:iterator value="#session.countryList">
									<option><s:property value="c_name"></s:property></option>
								</s:iterator>
								 
							</select>
						</div>
					</div>






					<div class="border-common clearfix">

						<p class="h4">
							<b>Security</b>
						</p>


					</div>

					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Question</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="Question"
								name="Securityquestion" required>
								 
								<option selected="selected">What was your childhood nickname?</option>
								<option>What is the name of your favorite childhood friend ?</option>
								<option>What street did you live on in third grade?</option>
								<option>What is your oldest sibling's birthday month
									and year?</option>
								<option>What is the middle name of your oldest child?</option>
								<option>What is your oldest sibling's middle name?</option>
								<option>What school did you attend for sixth grade?</option>
								<option>What was the name of your first stuffed animal?</option>
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
								name="Securityanswer" placeholder="Answer" autocomplete="off"
								required>
						</div>
					</div>

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Register</button>
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
	
		
		<script>
	
	
	
	
	$(document).ready(function(){ 
		 
	    $("#childParentCompany").validate({
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
	  	if($("#childParentCompany").valid())  
	    	 document.getElementById("childParentCompany").submit();
	  /* 	else
	  		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
	    });    
	    
	});

	</script>
</body>

</html>