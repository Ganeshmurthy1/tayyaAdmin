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
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>

</head>

<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
<style type="text/css">

.error {
    color:red;
}
.valid {
    color:green;
    }

</style>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Edit Company Profile
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


					<!-- <a href="profile.html"><i class="fa fa-angle-left"></i> Profile</a> -->
				</div>
				<!-- Small boxes (Stat box) -->
 			</div>

			<s:if test="%{#session.Company!=null}">
				<div class="row">

					<!-- left column -->
				 
					<!-- edit form column -->
					<s:if
						test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.isSuperuser()}">


						<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl+"editcompanyprofile";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});

	});
</script>
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
					</s:if>
 <s:elseif test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.isUsermode()}"> 
					 

						<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl+"userorcompanyprofile";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});

	});
</script>


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


					</s:elseif>

					<div class=" col-sm-12 col-xs-12 personal-info">
						<h3>
							Change Info <a
				 				href="resetCompanyPassword?companyid=<s:property value="%{#session.Company.companyid}"/>&Email=<s:property value="%{#session.Company.Email}"/>"
								class="btn btn-success btn-xs" data-toggle="modal"> Reset
								password </a>
						</h3>
						<form class="form-horizontal" role="form" action="companyupdate" id="companyUpdate"
							method="post" enctype="multipart/form-data">
							
							<div class="row">
							<div class="col-sm-4">
							
							
							<div class="form-group">
					<div class=" col-sm-12 col-xs-12" ng-cloak>
						<div class="text-center">

 					<s:if test="%{CurrentCompany.Imagepath == null}"> 
                  <img  src="images/default.png" class="user-image avatar img-circle" alt="No Image" />
				  </s:if> 
				  <s:else>
				  <img src="<s:url action='ImageAction?imageId=%{CurrentCompany.Imagepath}'/>"
								class="avatar img-circle img-thumbnail" alt="profile image" />
				  </s:else>



							
							<h6>Upload a different photo...</h6>
							<div class="col-sm-6 ">
								<div id="fileinfo">

									<div id="fileError"></div><!-- required="required" -->


								</div>              
							</div>
							<input type="file" id="uploadimage" accept="image/*"
								 ng-file-select="onFileSelect($files)"
								class="text-center center-block well well-sm"  name="Imagepath">
						</div>
					</div>
					</div>
					
					</div>
					<div class="col-sm-8">
					 <div class="form-group">
								<label for="Company" class="col-sm-2 control-label">Company</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="company"
										name="Companyname"
										value="<s:property value="CurrentCompany.Companyname"/>"
										placeholder="Company Name" autocomplete="off" required>
 
							</div>
							</div>
							  
						  <div class="form-group">
								<label for="Website" class="col-sm-2 control-label"><s:text name="global.KPPPno" ></s:text></label>
								<div class="col-sm-8">
									<input class="form-control input-sm" type="text" name="registerNumber" required
										value="<s:property value="CurrentCompany.registerNumber"/>"
										placeholder="<s:text name="global.KPPPno" ></s:text>" />
								</div>
							</div>
 
                          <div class="form-group">
								<label for="WalletType" class="col-sm-2 control-label">Wallet Type</label>
								<div class="col-sm-8">
								<s:if test="%{(#session.User.userrole_id.isSuperuser() || #session.Company.companyRole.isDistributor())}">
								<select class="form-control input-sm" name="typeOfWallet"
								id="typeOfWallet" autocomplete="off"   required>
								<s:if test="%{#session.User.agentWallet.walletType =='Prepaid'}">
								<option  value="Prepaid" selected>Prepaid</option>
								</s:if>
								<s:else>
									<option value="Postpaid">Postpaid</option>
								 </s:else>
								</select>
								</s:if>
								<s:else>
								<select class="form-control input-sm" name="typeOfWallet"
								id="typeOfWallet" autocomplete="off"   required>
								<s:if test="%{#session.User.agentWallet.walletType == 'Prepaid'}">
								<option  value="Prepaid" selected>Prepaid</option>
								</s:if>
								<s:else>
									<option value="Postpaid">Postpaid</option>
								 </s:else>
								 </select>
								 </s:else>
								 <%-- <input class="form-control input-sm" type="text" name="typeOfWallet"
										value="<s:property value="%{#session.User.agentWallet.walletType}"/>"
										placeholder="Wallet Type" /> --%>
								</div>
							</div>
							
 						<%-- <div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Kpp Number
							 </label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="kppNumber"
								name="kppNumber" value='<s:property value="kppNumber"/>'
								placeholder="kpp number" autocomplete="off" required="required">

						</div>
					</div> --%>
							<div class="form-group">
								<label for="Website" class="col-sm-2 control-label">Website</label>
								<div class="col-sm-8">
									<input class="form-control input-sm" type="text" name="Website"
										value="<s:property value="CurrentCompany.Website"/>"
										placeholder="http://www.example.com" />
								</div>
							</div>
 
							<div class="form-group">
								<label for="Email" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-8">
									<input type="email" class="form-control input-sm" name="Email"
										id="email" placeholder="Email"
										value="<s:property value="CurrentCompany.Email"/>"
										autocomplete="off" required>
								</div>
							</div>

							<div class="form-group">
								<label for="Address" class="col-sm-2 control-label">Address</label>
								<div class="col-sm-8">
									<textarea class="form-control input-sm" id="address"
										name="Address" placeholder="Address" autocomplete="off"
										required><s:property
											value="CurrentCompany.Address" /></textarea>

								</div>
							</div>

							<div class="form-group">
								<label for="Country" class="col-sm-2 control-label">Country</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" name="Countryname"
										id="country" autocomplete="off" required>
										<option
											value="<s:property value="CurrentCompany.Countryname"/>"><s:property
												value="CurrentCompany.Countryname" /></option>
										<s:iterator value="CountryList">
											<option value="<s:property value="c_name"/>"><s:property
													value="c_name"></s:property></option>
										</s:iterator>

									</select>
								</div>
							</div>


							<div class="form-group">
								<label for="City" class="col-sm-2 control-label">Language</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" name="Language"
										id="language_id" autocomplete="off" required>
										<option
											value="<s:property value="CurrentCompany.Language"/>"><s:property
												value="CurrentCompany.Language"></s:property></option>
										<s:iterator value="LanguageList">
											<option value="<s:property value="language"/>"><s:property
													value="language"></s:property></option>

										</s:iterator>

									</select>
								</div>
							</div>




							<div class="form-group">
								<label for="City" class="col-sm-2 control-label">City</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" name="City"
										id="city" autocomplete="off" required
										value="<s:property value="CurrentCompany.City"/>">

								</div>
							</div>

							<div class="form-group">
								<label for="telphone" class="col-sm-2 control-label">Phone</label>
								<div class="col-sm-8">
									<input type="tel" class="form-control input-sm" name="Phone"
										id="telphone"
										value="<s:property value="CurrentCompany.Phone"/>"
										placeholder="8105979291" autocomplete="off" required>
								</div>
							</div>
							<div class="border-common">

								<p class="h4">
									<b>State/City</b>
								</p>

							</div>


							<div class="form-group">
								<label for="Company-type" class="col-sm-2 control-label">Location
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm"
										name="location" id="company-type" autocomplete="off"
										value="<s:property value="CurrentCompany.location"/>"
										required>
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
										name="Billingcompany"
										value="<s:property value="CurrentCompany.Billingcompany"/>"
										placeholder="Company Name" autocomplete="off" required>
								</div>
							</div>

						<%-- 	<div class="form-group">
								<label for="Reference" class="col-sm-2 control-label">Reference</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="Reference"
										name="Billingreference"
										value="<s:property value="%{#session.Company.Billingreference}"/>"
										placeholder="Your Reference" autocomplete="off" required>
								</div>
							</div> --%>

							<div class="form-group">
								<label for="Address" class="col-sm-2 control-label">Address</label>
								<div class="col-sm-8">
									<textarea class="form-control input-sm" id="address"
										name="Billingaddress" placeholder="Address" autocomplete="off"
										required><s:property
											value="CurrentCompany.Billingaddress" /></textarea>
								</div>
							</div>
							<div class="form-group ">
								<label for="Country" class="col-sm-2 control-label">Country</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" name="Billingcountry"
										id="country" autocomplete="off" required>
										<option
											value="<s:property value="CurrentCompany.Billingcountry"/>"><s:property
												value="CurrentCompany.Billingcountry" /></option>
										<s:iterator value="CountryList">
											<option value="<s:property value="c_name"/>"><s:property
													value="c_name"></s:property></option>
										</s:iterator>

									</select>
								</div>
							</div>

							 

							<div class="form-group">
								<label class="col-md-3 control-label"></label>
								<div class="col-md-8">
									<input class="btn btn-primary" value="Save Changes" id="UpdateCompany"
										type="button">
									<%-- <span></span> <input class="btn btn-default" value="Cancel"
								type="reset"> --%>
								</div>
							</div>
							</div>
							</div>
						</form>
					</div>
				</div>
			</s:if>


			<!-- /.row -->
		</section>
		<!-- /.content -->
	</div>


	<!-- /.content-wrapper -->
	<script src="js/app.js" type="text/javascript"></script>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript">
		function checkPasswordMatch() {
			var password = $("#password").val();
			var confirmPassword = $("#confirmPwd").val();

			if (password != confirmPassword)
				$("#error").html("Passwords are not match!");

			else {

				$("#error").html("");

			}
		}

		$(document).ready(function() {
			$("#confirmPwd").keyup(checkPasswordMatch);
		});
		
		$(document).ready(function() {
		 $("#companyUpdate").validate({
	    	 ignore: false,  
	    	rules: {
	            "email": {
	                required: true,
	                email: true
	            },
	            "Companyname":{
	            	 required: true,
	            },
	            "registerNumber":{
	            	 required: true,
	            }
	        },
	        
	        messages: {
	            "email": {
	                required: "Please, enter an email",
	                email: "Email is invalid"
	            },
	            "Companyname":{
	            	 required: "Please, enter a company name",
	            },
	            "registerNumber":{
	            	 required: "Please, enter a PAN Number",
	            }
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
	    
		
		
	$('#UpdateCompany').click(function() {
		 $("#companyUpdate").valid();
		 if($("#companyUpdate").valid()){ 
			 	
	     	document.getElementById("companyUpdate").submit();
	     }
	})
		});
		
	</script>
	<%-- 	<%@ include file="DashFooter.jsp"%> --%>
</body>
</html>
