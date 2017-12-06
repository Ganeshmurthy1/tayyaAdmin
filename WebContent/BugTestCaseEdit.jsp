<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bug Tracker Test Cases</title>
<script src="js/angular.js" type="text/javascript"></script>
<!--< link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
 -->
<style type="text/css">
#rooms {
	margin-top: 5px;
}

.panel-group .panel-heading+.panel-collapse>.panel-body {
	border: 1px solid #ddd;
}

.panel-group, .panel-group .panel, .panel-group .panel-heading,
	.panel-group .panel-heading a, .panel-group .panel-title, .panel-group .panel-title a,
	.panel-group .panel-body, .panel-group .panel-group .panel-heading+.panel-collapse>.panel-body
	{
	border-radius: 2px;
	border: 0;
}

.panel-group .panel-heading {
	padding: 0;
}

.panel-group .panel-heading a {
	display: block;
	background: #668bb1;
	color: #ffffff;
	padding: 8px;
	text-decoration: none;
	position: relative;
}

.panel-group .panel-heading a.collapsed {
	background: #eeeeee;
	color: inherit;
}

.panel-group .panel-heading a:after {
	content: '-';
	position: absolute;
	right: 20px;
	top: 8px;
	font-size: 20px;
}

.panel-group .panel-heading a.collapsed:after {
	content: '+';
}

.more-details a {
	width: 60px;
	text-align: left;
	position: relative;
}

.more-details a:after {
	content: '-';
	position: absolute;
	right: 10%;
	top: 18%;
	font-size: 15px;
	color: #fff;
}

.more-details a.collapsed:after {
	content: '+';
	/*   position: absolute;
  left: 10%;
  top:0px;
  font-size:25px; */
	color: #fff;
}

.panel-group .panel-collapse {
	margin-top: 5px !important;
}

.panel-group .panel-body {
	background: #ffffff;
	padding: 15px;
}

.panel-group .panel {
	background-color: transparent;
}

.panel-group .panel-body p:last-child, .panel-group .panel-body ul:last-child,
	.panel-group .panel-body ol:last-child {
	margin-bottom: 0;
}

.add-remove {
	margin: 20px 0px 20px;
}

#mandatory{
color:red;
}

</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
						
						<!-- Modal -->

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
			
			<div class="row">
				<!-- createHoteOfflineBooking  -->
				<form action="updateBugTestCase" method="post"
					class="form-horizontal" name="myForm" id="bookingFormId">
					<div>
					<div id="myfform">
						 
						<!-- harsha added colapse -->
						<div class="col-sm-12">
							


								<div class="panel panel-default" id="rooms">
									
									<!--/.panel-heading -->
									<div>
										<div class="panel-body">
 
										

											<div >

												<div class="panel panel-default">
													<div class="panel-heading">
														<h4 class="panel-title">
															<a > Edit Bug Tracker Test Case </a>
														</h4>
													</div>
													<!--/.panel-heading -->
													<div>
														<div class="panel-body">
															
															<div class="col-sm-14">
															<div class="form-group">
													<label for="testCase"
														class=" control-label"> Test Case </label>
													<textarea rows="3" cols="3" class="form-control input-sm"
														name="bugTestCase.testCase" 
														placeholder="Enter Test Case" required><s:property value="bugTestCase.testCase" /></textarea>
												</div>
															
														</div>
															

															
														</div>
														<!--/.panel-body -->
													</div>
													<!--/.panel-collapse -->
												</div>
												<!-- /.panel -->

												
												<!-- <div class="clearfix add-remove">
													<a class="btn btn-primary" role="button" id="addroom"
														onclick="add(this);"> onclick="add() onclick="remove_field()"
														Add More
													</a> <a class="btn btn-primary remove_field" id="removeroom"
														role="button" onclick="remove_field(this)" disabled>
														Remove </a>

												</div> -->
											</div>
											<!-- /.panel-group -->
											<!-- nested -->
										</div>
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
									</div>

									
									
								<!-- /.panel -->

								<div id="roomscount"></div>
							</div>
							<!-- /.panel-group -->
						</div>
					</div>

					<!-- harsha added colapse ended -->

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="submit" name="bugTestCase.id" value="${id}"  id="bugtestcasesubmitnew" 
								class="btn btn-primary btn-lg">Update Test Case</button>
						</div>
					</div>

				</form>
			</div>

			<!-- /.row -->
			<!-- Main row -->
		</section>
		</div>
		<!-- /.content -->
	
	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	
	 
</body>
</html>