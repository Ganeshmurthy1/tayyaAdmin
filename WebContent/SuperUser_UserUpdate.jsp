<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>
<script src="js/angular.js" type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>  
 
   <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>  
   <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"> </script> --%>
 
<link rel="stylesheet" href="css/alert.css">
 



<script type="text/javascript">
	  $(function() {
		var id=$("#uniqueId").val();
		var status=$("#status").val();
		var Locked=$("#lockstatus").val();
 
		 document.getElementById('Status'+id).value =status;
		 document.getElementById('Locked'+id).value =Locked;

	 }); 
	
	
</script>

					

</head>
<body data-ng-controller="AppCtrl">

	<s:if test="CurrentProfile!=null">
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Edit Employee Profile
					<!-- <small>Control panel</small> -->
				</h1>
				<!-- <ol class="breadcrumb">
					<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol> -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-sm-12">
					<h4>
						<a href="javascript:history.back();"><span class="pull-right"><i
								class="fa fa-angle-left"></i> Back</span></a>
					</h4>
				</div>
				<div class="row">

					<!-- left column -->
				
					 
					<%-- 	<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
						
		
 
		
		 <%-- <s:elseif test="#session.Company.companyRole.isCorporate()">
						<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
						<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl+"superUser_UserList";
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
 	 <s:else>
 	 <script type="text/javascript">
	 $(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl+"companyUserList";
		
		$('#successelse').click(function() {
			window.location.assign(finalUrl);
			$('#success-alertelse').hide();

		});
		$('#cancelelse').click(function() {
			$('#error-alertelse').hide();

		});

	});
</script>
 <s:if test="hasActionErrors()">

							<div class="succfully-updated clearfix" id="error-alertelse">

								<div class="col-sm-2">
									<i class="fa fa-check fa-3x"></i>
								</div>

								<div class="col-sm-10">

									<p>
										<s:actionerror />
									</p>

									<button type="button" id="cancelelse" class="btn btn-primary">Ok</button>

								</div>

							</div>

						</s:if>

						<s:if test="hasActionMessages()">
							<div class="sccuss-full-updated" id="success-alertelse">
								<div class="succfully-updated clearfix">

									<div class="col-sm-2">
										<i class="fa fa-check fa-3x"></i>
									</div>

									<div class="col-sm-10">
										<s:actionmessage />
										<button type="button" id="successelse" class="btn btn-primary">Ok</button>

									</div>

								</div>
							</div>
						</s:if>		
  </s:else> --%>
		
	
		
						 
						 
						 
						 
						 
<%-- 						 
					 <s:else>
					
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl+"companyUserList";
		
		$('#successelse').click(function() {
			window.location.assign(finalUrl);
			$('#success-alertelse').hide();

		});
		$('#cancelelse').click(function() {
			$('#error-alertelse').hide();

		});

	});
</script>
			
			 <s:if test="hasActionErrors()">

							<div class="succfully-updated clearfix" id="error-alertelse">

								<div class="col-sm-2">
									<i class="fa fa-check fa-3x"></i>
								</div>

								<div class="col-sm-10">

									<p>
										<s:actionerror />
									</p>

									<button type="button" id="cancelelse" class="btn btn-primary">Ok</button>

								</div>

							</div>

						</s:if>

						<s:if test="hasActionMessages()">
							<div class="sccuss-full-updated" id="success-alertelse">
								<div class="succfully-updated clearfix">

									<div class="col-sm-2">
										<i class="fa fa-check fa-3x"></i>
									</div>

									<div class="col-sm-10">
										<s:actionmessage />
										<button type="button" id="successelse" class="btn btn-primary">Ok</button>

									</div>

								</div>
							</div>
						</s:if>		
					
					
					 </s:else>
				 --%>

					<!-- edit form column -->
					<div class=" col-sm-12  personal-info">


						
						<h3>Change Info   <a href="resetCompanyUserPassword?id=<s:property value="CurrentProfile.id"/>"
															class="btn btn-success btn-xs" data-toggle="modal">
														Reset password
														</a>     </h3>
						<form class="form-horizontal" role="form" enctype="multipart/form-data"
							action="superUser_UserProfileUpdate" method="post">
							
							<input type="hidden" name="id" value="<s:property value="CurrentProfile.id"/>">
							<input type="hidden" name="userrole_id.roleid"	value="<s:property value="userrole_id.roleid"/>">
							
							 <div class="col-sm-4">
										<div class="col-sm-12 ">
						<div class="text-center">
						 <s:if test="%{CurrentProfile.Imagepath == null}"> 
                  <img  src="images/default.png" class="user-image avatar img-circle" alt="No Image" />
				  </s:if> 
						<s:else>
						
						 <img src="<s:url action='ImageAction?imageId=%{CurrentProfile.Imagepath}'/>" 	class="avatar img-circle img-thumbnail" alt="profile image"/>
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
									
									
					<!-- upload passport size  photo-->	
					
					<s:if test="CurrentProfile.userrole_id.isCorporateemployee()">
					 <div class="col-sm-4">
										<div class="col-sm-12 ">
						<div class="text-center">
						 <s:if test="%{CurrentProfile.passportSizeImage == null}"> 
                  <img  src="images/default.png" class="user-image avatar img-circle" alt="No Image" />
				  </s:if> 
						<s:else>
						
						<div class="form-group">
						<!-- <label for="City" class="col-sm-2 control-label">Description</label> -->
						<div class="col-xs-12 ">
						 <a href="downloadPassportSizePhotograph?fileName=<s:property value="CurrentProfile.passportSizeImage"/>">(Download)</a>
						</div>
					</div>
						
						
						
						 <img src="<s:url action='UploadPassportSizeImageAction?passportSizeImageId=%{CurrentProfile.passportSizeImage}'/>" 	class="avatar img-circle img-thumbnail" alt="passport size image"/>
				</s:else>
							 
							<h6>Upload a different passport size photo...</h6>
							<div class="col-sm-6 ">
								<div id="fileinfo">

									<div id="fileError"></div><!-- required="required" -->


								</div>              
							</div>
						<input type="file" id="passportSizeImage" accept="image/*"
								 ng-file-select="onFileSelect($files)"
								class="text-center center-block well well-sm"  name="passportSizeImage">
						</div>

					</div>
									</div>	
									
									
							<!-- upload passport  scan copy-->									
									
					 <div class="col-sm-4">
										<div class="col-sm-12 ">
						<div class="text-center">
						 <s:if test="%{CurrentProfile.passportScanCopy == null}"> 
                  <img  src="images/default.png" class="user-image avatar img-circle" alt="No Image" />
				  </s:if> 
						<s:else>
						<div class="form-group">
						<!-- <label for="City" class="col-sm-2 control-label">Description</label> -->
						<div class="col-xs-12 ">
						 <a href="downloadPassportScanCopy?fileName=<s:property value="CurrentProfile.passportScanCopy"/>">(Download)</a>
						</div>
					</div>
						
						 <img src="<s:url action='UploadPassportScanCopyAction?passportScanCopyId=%{CurrentProfile.passportScanCopy}'/>" 	class="avatar img-circle img-thumbnail" alt="passport scan copy "/>
				</s:else>
							 
							<h6>Upload a different passport scan copy ...</h6>
							<div class="col-sm-6 ">
								<div id="fileinfo">

									<div id="fileError"></div><!-- required="required" -->

								</div>              
							</div>
						<input type="file" id="uploadimage" accept="image/*"
								 ng-file-select="onFileSelect($files)"
								class="text-center center-block well well-sm"  name="passportScanCopy">
						</div>

					</div>
									</div>		
					
					</s:if>
					
				 <div class="col-sm-8">
									
							<div class="form-group">
								<label for="Company" class="col-sm-2 control-label">
									<s:if test="CurrentProfile.userrole_id.isCorporateemployee()">
								Employee ID 
								</s:if>
								<s:else>
								User ID 
								</s:else>
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="username"
										name="Username"
										value="<s:property value="CurrentProfile.Username"/>"
										placeholder="User ID " autocomplete="off" required> <input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>
							</div>

							<div class="form-group">
								<label for="Website" class="col-sm-2 control-label">First
									Name</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm"
										id="first-name" name="Firstname"
										value="<s:property value="CurrentProfile.Firstname"/>"
										placeholder="First Name" autocomplete="off" required>
								</div>
							</div>

							<div class="form-group">
								<label for="Website" class="col-sm-2 control-label">Last
									Name</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="last-name"
										name="Lastname"
										value="<s:property value="CurrentProfile.Lastname"/>"
										placeholder="Last Name" autocomplete="off" required>
								</div>
							</div>
  					<s:if test="CurrentProfile.userrole_id.isCorporateemployee()">
  					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Employee Band</label>
						<div class="col-sm-8" >
						<select class="form-control input-sm" name="bandName" 
								id="bandName" >
								<option value="">Select band (Code)</option>
								<c:forEach var="band" items="${bandModelList}">
									<c:choose>
										<c:when test="${band.bandName== CurrentProfile.bandName}">
											<option selected="selected" value="${band.bandName}">${band.bandName} ( ${band.bandCode} )</option>
										</c:when>
										<c:otherwise>
											<option value="${band.bandName}">${band.bandName} ( ${band.bandCode} )</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Employee Designation</label>
						<div class="col-sm-8">
						<select class="form-control input-sm" name="designation" 
								id="designation" >
								<option value="">Select Designation (Code)</option>
								<c:forEach var="designation" items="${employeeDesignationsList}">
									<c:choose>
										<c:when test="${designation.desgName == CurrentProfile.designation}">
											<option selected="selected" value="${designation.desgName}">${designation.desgName} ( ${designation.desgCode} )</option>
										</c:when>
										<c:otherwise>
											<option value="${designation.desgName}">${designation.desgName} ( ${designation.desgCode} </option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					
					
					</s:if>
 					<div class="form-group">
								<label for="Country" class="col-sm-2 control-label">Employee Role</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" id="userroletype"
										name="userroletype" multiple="multiple">
										<s:if  test="%{#session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isCorporate()==false}">
	 										 <s:if test="CurrentProfile.userrole_id.isTechSupport()">
												<option value="tech_support" selected="selected">Technical Support</option>
											 </s:if>
											 <s:else>
											 	<option value="tech_support">Technical Support</option>
											 </s:else>
											 
											 <s:if test="CurrentProfile.userrole_id.isTechHead()">
												<option value="tech_head" selected="selected">Technical Head</option>
											 </s:if>
											 <s:else>
											 	<option value="tech_head" >Technical Head</option>
											 </s:else>
											 
											  <s:if test="CurrentProfile.userrole_id.isTravelDesk()">
												<option value="traveldesk" selected="selected">TravelDesk</option>
											  </s:if>
											   <s:else>
											 	<option value="traveldesk">TravelDesk</option>
											  </s:else>
											  
											 <s:if test=" CurrentProfile.userrole_id.isCms()">
												<option value="cms" selected="selected">CMS</option>
											 </s:if>
											  <s:else>
											 	<option value="cms" >CMS</option>
											 </s:else>
											 <s:if test=" CurrentProfile.userrole_id.isDemoUser()">
												<option value="demo" selected="selected">Demo User</option>
											 </s:if>
											  <s:else>
											 <option value="demo">Demo User</option>
											 </s:else>
											 
										</s:if>
											 <s:if test="CurrentProfile.userrole_id.isAdmin()">
												<option value="admin" selected="selected">Admin</option>
											 </s:if>
											 <s:else>
											 	<option value="admin"  >Admin</option>
											 </s:else>
											 
											 <s:if test="CurrentProfile.userrole_id.isReports()">
												<option value="report" selected="selected">Accounts</option>
											</s:if>
											 <s:else>
											 	<option value="report" >Accounts</option>
											 </s:else>
											 
											<s:if test="CurrentProfile.userrole_id.isOrder()">
												<option value="order" selected="selected">Operations</option>
											</s:if>
											 <s:else>
											 	<option value="order" >Operations</option>
											 </s:else>
											 <s:if test="#session.Company.companyRole.isCorporate()">
											 	<option value="corporateemployee" selected="selected">Employee</option>
											 </s:if>
											 
											  <%-- <s:if test="CurrentProfile.userrole_id.isCorporateemployee()">
												<option value="corporateemployee" selected="selected">Employee</option>
											 </s:if>  
											  <s:else>
											 	<option value="corporateemployee" >Employee</option>
											 </s:else> --%>
									 </select>
								</div>
							</div>
							<div class="form-group">
								<label for="Email" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-8">
									<input type="email" class="form-control input-sm" name="Email"
										id="email"
										value="<s:property value="CurrentProfile.Email"/>"
										placeholder="Email" autocomplete="off" required>
								</div>
							</div>

							<div class="form-group">
								<label for="Address" class="col-sm-2 control-label">Address</label>
								<div class="col-sm-8">
									<textarea class="form-control input-sm" id="address"
										name="Address" placeholder="Address" autocomplete="off"
										required><s:property
											value="CurrentProfile.Address" /></textarea>
								</div>
							</div>

 					<s:if test="CurrentProfile.userrole_id.isCorporateemployee()">

					<div class="form-group">
						<label for="passportno" class="col-sm-2 control-label">Passport Number</label>
						<div class="col-sm-8">
							<input type="text" 	class="form-control input-sm" name="passportno"
								 placeholder="PassportNumber" value="<s:property
											value="CurrentProfile.passportno" /> "  autocomplete="off" required>
						</div>
					</div>
					
					<div class="form-group">
						<label for="passport_expirydate" class="col-sm-2 control-label">Passport ExpiryDate</label>
						<div class="col-sm-8">
							<input type="text" id="passport_expirydate"	class="form-control input-sm" name="passport_expirydate"
								 placeholder="passport_expirydate"
								 value="<s:property
											value="CurrentProfile.passport_expirydate" /> " 
								  autocomplete="off" required>
							
						</div>
					</div>
					<div class="form-group">
						<label for="passport_issuedate" class="col-sm-2 control-label">Passport IssueDate</label>
						<div class="col-sm-8">
							<input type="text" id="passport_issuedate"	class="form-control input-sm" name="passport_issuedate"
								 placeholder="passport_issuedate" autocomplete="off" 
								  value="<s:property
											value="CurrentProfile.passport_issuedate" /> " 
								 required>
							
						</div>
					</div>
					<div class="form-group">
						<label for="passport_issueplace" class="col-sm-2 control-label">Passport IssuePlace</label>
						<div class="col-sm-8">
							<input type="text" 	class="form-control input-sm" name="passport_issueplace"
								 placeholder="passport_issueplace" autocomplete="off" 
								  value="<s:property
											value="CurrentProfile.passport_issueplace" /> " 
								 required>
						</div>
					</div>
					<div class="form-group">
						<label for="dateofbirth" class="col-sm-2 control-label">Date Of Birth</label>
						<div class="col-sm-8">
							<input type="text" id="dateofbirth"	class="form-control input-sm" name="dateofbirth"
								 placeholder="dateofbirth" autocomplete="off" 
								  value="<s:property
											value="CurrentProfile.dateofbirth" /> " 
								 required>
							
						</div>
					</div>
					</s:if>
							<div class="form-group">
								<label for="Country" class="col-sm-2 control-label">Country</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" name="Countryname"
										id="country" autocomplete="off" required>
										<option
											value="<s:property value="CurrentProfile.Countryname"/>"
											selected="selected"><s:property
												value="CurrentProfile.Countryname" /></option>

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
											value="<s:property value="CurrentProfile.Language"/>"
											selected="selected"><s:property
												value="CurrentProfile.Language" /></option>

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
										id="city" placeholder="City" autocomplete="off" required
										value="<s:property value="CurrentProfile.City"/>">
								</div>
							</div>

							<div class="form-group">
								
								<label for="telphone" class="col-sm-2 control-label">
								<s:if test="#session.Company.companyRole.isCorporate()">
								Phone
								</s:if>
								<s:else>Mobile</s:else>
								
								
								
								</label>
								<div class="col-sm-8">
									<input type="tel" class="form-control input-sm" name="Mobile"
										id="telphone"
										value="<s:property value="CurrentProfile.Mobile"/>"
										placeholder="8105979291" autocomplete="off" required>
								</div>
							</div>

							<div class="form-group">
								<label for="Description" class="col-sm-2 control-label"><s:text name="global.Designation"></s:text></label>
								<div class="col-sm-8">
									<textarea class="form-control input-sm" id="Description"
										name="Description" placeholder="Description"
										autocomplete="off" required><s:property
										value="CurrentProfile.Description" /></textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label"></label>
								<div class="col-md-8">
									<input class="btn btn-primary" value="Save Changes"
										type="submit">
								</div>
							</div>
									
									</div>						
							</form>
							</div><!-- row -->



					 
					</div>
					</section>
					
				</div>
			 
	</s:if>


	<!-- /.content-wrapper -->
	<script src="js/app.js" type="text/javascript"></script>
	<%@ include file="DashboardFooter.jsp"%>

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

	 
	</script>
	 <script>
 $(document).ready(function() 
 { 
	 $("#passport_issuedate,#dateofbirth").datepicker({
		 dateFormat: "dd-mm-yy",
		 maxDate:0,
		 numberOfMonths: 2,
		 yearRange: "-100:+0",
			changeMonth: true,
			changeYear: true
	 });
	 $("#passport_expirydate").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate:0,
		 numberOfMonths: 2,
		 yearRange: "+0:+30",
			changeMonth: true,
			changeYear: true
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
								<p>
									<s:actionmessage />
								</p>
									<button type="button" id="success" class="btn btn-primary">Ok</button>
							</div>
						</div>
				</div>
				</s:if>	
						
	
	<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl+"superUser_UserList";
		
		$('#success').click(function() {
				window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});

	});
	
	$('#designation').on('change', function() {
		  
		  var desgName=this.value;
		  <c:forEach items="${employeeDesignationsList}" var="item" varStatus="status"> 
			if("${item.desgName}".toLowerCase() === desgName.toLowerCase()){
				$('#bandName').val("${item.employeeBandModel.bandName}");
				
		 }
			
		</c:forEach>
		  
	})
</script>
	<%-- 	<%@ include file="DashFooter.jsp"%> --%>
</body>
</html>
