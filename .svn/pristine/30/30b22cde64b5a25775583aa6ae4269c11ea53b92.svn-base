<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>

 
<script type="text/javascript">
$(function(){
	   var txt = $('.success-alert').text();
	   var protocol=location.protocol;
   	   var host=location.host;
   	   var url=protocol+"//"+host+"/TravelAdmin/companyUserList";
    if(txt.length>0){
 	  if(txt="updated") {
 		   alert("succesfully updated"); 
 		   window.location.assign(url);
 	  }
 	 else if(txt="failed") {
		  alert("failed. try again"); 
		   //window.location.assign("http://localhost:8080/LintasTravelAdmin/userList");
	  }
 	 }
 });


 
 </script>

 </head>
<body data-ng-controller="AppCtrl">
	 
	<s:if test="%{#session.User!=null}">
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
				 <div class="col-sm-12">
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
				<div class="row">

					<!-- left column -->
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="text-center">
							<img src="<s:property value="%{#session.User.Imagepath}"/>"
								class="avatar img-circle img-thumbnail" alt="profile image">
							<!-- <img ng-src="{{imageSrc}}"  
											class="avatar img-circle img-thumbnail" alt="profile image"> -->
							<h6>Upload a different photo...</h6>
							<input type="file" id="uploadimage" accept="image/*"
								required="required" ng-file-select="onFileSelect($files)"
								class="text-center center-block well well-sm"> <input
								type="hidden" name="Imagepath" ng-model="Imagepath"
								value="{{Imagepath}}" >
						</div>
					</div>
					<!-- edit form column -->
					<div class="col-md-8 col-sm-6 col-xs-12 personal-info">

						<s:if test="hasActionErrors()"  >
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
<!--  superUser_UserProfileUpdate -->
						<h3>Change Info</h3>
						<form class="form-horizontal" role="form" action=""
							method="post">
							<div class="form-group">
								<label for="Company" class="col-sm-2 control-label">
								User ID </label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="username"
										name="Username"
										value="<s:property value="%{#session.User.Username}"/>"
										placeholder="Emp Code" autocomplete="off" required>
										
										<input
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

							<div class="form-group">
								<label for="Country" class="col-sm-2 control-label">
								Emp Role
								</label>
								<div class="col-sm-8">
									<select class="form-control input-sm" id="userroletype"
										name="userroletype" required>

										<option selected="selected"
											value="<s:property value="%{#session.User.userroletype}"/>"><s:property
												value="%{#session.User.userroletype}" /></option>
										<option value="admin">Admin</option>
										<option value="report">Reports</option>
										<option value="order">Order</option>
										<option value="cms">CMS</option>

									</select>
								</div>
							</div>


							<div class="form-group">
								<label for="Website" class="col-sm-2 control-label">Password
								</label>
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
										id="email"
										value="<s:property value="%{#session.User.Email}"/>"
										placeholder="Email" autocomplete="off" required>
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
											<option value="<s:property value="c_code"/>"><s:property
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
										placeholder="8105979291" autocomplete="off" required>
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

							<div class="form-group">
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
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label"></label>
								<div class="col-md-8">
									<input class="btn btn-primary" value="Save Changes"
										type="submit">
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
	</s:if> 
	<!-- /.content-wrapper --> 
	<%@ include file="DashboardFooter.jsp"%>
		<script src="js/app.js" type="text/javascript"></script>
	<%-- 	<%@ include file="DashFooter.jsp"%> --%>
</body>
</html>
