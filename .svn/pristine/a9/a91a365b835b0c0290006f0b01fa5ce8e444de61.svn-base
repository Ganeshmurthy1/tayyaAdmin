<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- <html data-ng-app="app"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script> --%>
<%-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>
<%-- <script
	src="https//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script> --%>
<style type="text/css">
#level1 .btn {
	margin-top: 10px;
	padding: 2px 18px;
}

p {
	margin: 15px 0 10px;
}

#content {
	/*   width: 500px;
   height: 108px;
  overflow:auto */
	position: fixed;
	top: 10px;
	bottom: 10px;
	width: 100%;
	overflow-y: auto;
}

element.style {
	align-content: center;
}

textarea {
	width: 718px;
	height: 69px;
}

.p-label {
	float: left;
	width: 40%;
	text-align: left;
	font-weight: bold;
}
.button {
    color: green;
}
.tc{ 
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
}
</style>


<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
	$(function() {

		$('#success').click(function() {
			window.location.assign(document.referrer);
			$('#success-alert').hide();
		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			<i class="fa fa-list"></i>Bug Test Cases
		</h1>
		<div class="breadcrumb-wrapper">
			<%-- <span class="label">You are here:</span> --%>
			<ol class="breadcrumb">
				<li><a href="goBugTrackerList">Bug List</a></li>
				<li><a href="goTestCases?id=${id}">Create Test Case</a></li>
				<li class="active">View Test Cases</li>
			</ol>
		</div>
		</section>
		<!-- Main content -->
		<section class="content clearfix">
		
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
			<div class="form-group">
				<label class="col-sm-2 control-label"> </label>
				<div class="col-sm-12">
					<div class="support">
						<div class="level1">
							<div id="level1">

								<s:iterator value="bugTracker" status="rowCount">
									<!-- bugTestCases -->
									<input type="hidden" name="id" value="<s:property value="id"/>">
									<div id="clonedInput1" class="clonedInput">
										<div class="qotationlist clearfix">
											<div class="row">
												<div class="col-sm-6 ">
													<div class="p-info clearfix">
														<div class="p-label">
															<p>Title</p>
														</div>
														<div class="p-inp">
															<p>${title}</p>
														</div>
													</div>
												</div>
												<div class="col-sm-6 ">
													<div class="p-info clearfix">
														<div class="p-label">
															<p>description</p>
														</div>
														<div class="p-inp">
															<p>${description}</p>
														</div>
													</div>
												</div>
											</div>
											 
											</div>
											<div class="col-sm-12">
				<h4>
					<a href="goTestCases?id=${bugTracker.id}"
												class="btn btn-success btn-xs">Create</a>			
				</h4>
			</div>	
											

											<s:iterator value="bugTracker.bugTestCases" status="count">
												<div class="col-sm-12 tc">


													<div class="row">
														<div class="col-sm-2 ">
															<div class="p-info clearfix">
																 
																	<p align="left">Test Case:${count.count}</p>
																 
															</div>
														</div>
														<div class="col-sm-7 ">
															<div class="p-info clearfix"> 
																	<textarea rows="3" cols="3"
																		class="form-control input-sm" name="testCase"
																		placeholder="Enter Test Case" readonly>${testCase} </textarea> 
															</div>
														</div>
														<s:if test="verify!=true"> 
															<div class="col-sm-3 pull-top"> 
																		<a href="bugTestCaseVerify?id=${id}"
																			class=" btn btn-primary" id="verify">verify</a> 
							<a href="editBugTestCase?id=${id}" class="btn btn-success btn-xs"><i class="fa fa-edit"></i> Edit</a>
							<a href="deleteBugTestCase?id=${id}" class="btn btn-success btn-xs"><i class="fa fa-edit"></i> Delete</a>
																			
															</div>
														</s:if>
														<s:else>
														<div  class="col-sm-3 pull-top">
										<a href="#" class="btn btn-success btn-xs" id="verify" style="cursor:pointer !important;"><i class="fa fa-check " >Verified</i>
										</a>
															</div>
														</s:else>
														</div>
														<div class="row">
															<div class="col-sm-12"></div>
														</div> 
													</div>
											</s:iterator> 
										</div> 
								</s:iterator>
								</div>
								


							</div>
						</div>

					</div>
					<!--  support -->
				</div>
			
			
			
			<div class="modal fade" id="emailModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Alert !</h4>
				</div>
				<div class="modal-body">
					<p id="desc"></p>

				</div>

			</div>
		</div>
	</div>

		</section>
	</div>
	
	<%@ include file="DashboardFooter.jsp"%>
</body>

</html>