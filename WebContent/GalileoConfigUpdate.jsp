<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Config</title>
 

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"g_ConfigList";
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
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Update Galileo Configuration <%-- <small>Control panel</small> --%>
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
				 
				<form action="updateGalileoConfigData" method="post"
					class="form-horizontal">
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Url</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="username"
								name="url"   value="<s:property value="%{#session.GalileoconfigProfile.url}"/>"      placeholder="url" autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">Target
							Branch</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="first-name"
								name="target_branch"   value="<s:property value="%{#session.GalileoconfigProfile.target_branch}"/>"       placeholder="Target Branch"
								autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">User
							Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="first-name"
								name="user_name"    value="<s:property value="%{#session.GalileoconfigProfile.user_name}"/>"         placeholder="User ID " autocomplete="off"
								required>
						</div>
					</div>

					<div class="form-group">
						<label for="Password" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-8">
							<input type="password" ng-model="password"
								class="form-control input-sm" name="password"
								id="Server Password"     value="<s:property value="%{#session.GalileoconfigProfile.password}"/>"                placeholder="Password" autocomplete="off"
								required>
						</div>
					</div>


					<div class="form-group">
						<label for="telphone" class="col-sm-2 control-label">Pcc</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" name="pcc"
								id="telphone"   value="<s:property value="%{#session.GalileoconfigProfile.pcc}"/>"       placeholder="Pcc" autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
						<label for="telphone" class="col-sm-2 control-label">Environment</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm"
								name="environment"   value="<s:property value="%{#session.GalileoconfigProfile.environment}"/>"           id="telphone" placeholder="Environment"
								autocomplete="off" required>
						</div>
					</div>

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="submit" class="btn btn-primary btn-lg">Update Changes</button>
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
	<script type="text/javascript" src="js/app.js"></script>
   <%@ include file="DashboardFooter.jsp"%> 
</body>

</html>