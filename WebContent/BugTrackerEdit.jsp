<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/alert.css">
<script src="js/techsupport_emplist.js"></script>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl
				+ "editBugTracker?bugTrackerId=${param['bugtrackerId']}";
		console.log("finalUrl", finalUrl);
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
<body data-ng-controller="AppCtrl">

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			<i class="fa fa-edit"></i>Update Bug
		</h1>
		<div class="breadcrumb-wrapper">
			<%-- <span class="label">You are here:</span> --%>
			<ol class="breadcrumb">
				<li><a href="goBugTrackerList">Bug List</a></li>
				<li><a href="viewBugTracker?id=${id}">Bug View</a></li>
				<li class="active">Add Bug</li>
			</ol>
		</div>
	</section>
		<!-- Main content -->
		<section class="content">
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
			<form action="createNewBugHistory" method="post"
				class="form-horizontal" name="myForm" id="myForm"
				enctype="multipart/form-data">
				<input type="hidden" name="id"
					value="<s:property value="bugTracker.id"/>"> 

				<div class="form-group">
					<label class="col-sm-2 control-label"> </label>
					<div class="col-sm-8">
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
							<div class="form-group">
								<label class="col-sm-2 control-label">Assigned To</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" id="assignTo"
										placeholder="assigned to" autocomplete="off"
										value="<s:property value="bugTracker.assignedToName"/>" >
									<input type="hidden" id="techSupportId" name="assignTo"
										value="<s:property value="bugTracker.assignTo"/>">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Assigned By</label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm"
										placeholder="assigned by" autocomplete="off" id="assignby"
										value="<s:property value="bugTracker.assignedByName"/>">
									<input type="hidden" name="assignedBy" id="techHeadId"
										value="<s:property value="bugTracker.assignedBy"/>">
								</div>
							</div>
						</c:when>
						<c:otherwise>
						<input type="hidden" id="techSupportId" name="assignTo"
										value="<s:property value="bugTracker.assignTo"/>">
						<input type="hidden" name="assignedBy" id="techHeadId"
										value="<s:property value="bugTracker.assignedBy"/>">
						</c:otherwise>
						</c:choose>
							<div class="form-group">
							<label class="col-sm-2 control-label">Title</label>
							<div class="col-sm-8">
							<input type="text" name="title" id="title" class="form-control input-sm"
										placeholder="Title" autocomplete="off" 
										value="<s:property value="bugTracker.title"/>" required="required">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Description</label>
							<div class="col-sm-8">
								<textarea cols="3" rows="3" class="form-control input-sm" name="bugTracker.description"
									placeholder="description"><s:property value="bugTracker.description" required="required" /></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Bug Type</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="level"
									name="bugTracker.bugType" required="required">
									<%-- <option value="${bugTracker.bugType}" selected="selected">${bugTracker.bugType}</option> --%>
									<c:forEach var="statusItem"
										items="${bugTrackerUtility.bugType}">
										<c:choose>
											<c:when test="${statusItem==bugTracker.bugType}">
												<option value="${statusItem}" selected="selected">${statusItem}</option>
											</c:when>
											<c:otherwise>
												<option value="${statusItem}">${statusItem}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Bug Priority</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="level"
									name="bugTracker.level" required="required">
									<c:forEach var="statusItem"
										items="${bugTrackerUtility.bugPriority}">
										<c:choose>
											<c:when test="${statusItem==bugTracker.level}">
												<option value="${statusItem}" selected="selected">${statusItem}</option>
											</c:when>
											<c:otherwise>
												<option value="${statusItem}">${statusItem}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">status</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="status"
									name="bugTracker.status" required="required">
									<c:forEach var="statusItem"
										items="${bugTrackerUtility.bugStatus}">
										<c:choose>
											<c:when test="${statusItem==bugTracker.status}">
												<option value="${statusItem}" selected="selected">${statusItem}</option>
											</c:when>
											<c:otherwise>
												<option value="${statusItem}">${statusItem}</option>
											</c:otherwise>
										</c:choose>

									</c:forEach>

								</select>
							</div>
						</div>
						
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">AssignedDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.transAssignedDate" placeholder="Assigned Date"
									value="<s:property value="bugTracker.transAssignedDate"/>"
									autocomplete="off" id="asssignDateedit" required="required">
							</div>
						</div>
						</c:when>
						<c:when test="${session.User.userrole_id.techSupport==true }">
						<div class="form-group">
							<label class="col-sm-2 control-label">AssignedDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.transAssignedDate" placeholder="Assigned Date"
									value="<s:property value="bugTracker.transAssignedDate"/>"
									autocomplete="off" id="asssignDateedit" readonly>
							</div>
						</div>
						</c:when>
						<c:otherwise>
							<input type="hidden" class="form-control input-sm"
									name="bugTracker.transAssignedDate" placeholder="Assigned Date"
									value="<s:property value="bugTracker.transAssignedDate"/>"
									autocomplete="off" id="asssignDateedit" readonly>
						</c:otherwise>
						</c:choose>
						
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true || session.User.userrole_id.techSupport==true }">
						<div class="form-group">
							<label class="col-sm-2 control-label">StartToWorkDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.transStartToWorkDate"
									value="<s:property value="bugTracker.transStartToWorkDate"/>"
									placeholder="Start To Work Date" autocomplete="off"
									id="startToWorkDateedit" required="required">
							</div>
						</div>
						</c:when>
						</c:choose>
						<input type="hidden" class="form-control input-sm"
									name="bugTracker.transStartToWorkDate"
									value="<s:property value="bugTracker.transStartToWorkDate"/>"
									placeholder="Start To Work Date" autocomplete="off"
									id="startToWorkDateedit">
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true || session.User.userrole_id.techSupport==true }">
						<div class="form-group">
							<label class="col-sm-2 control-label">WorkFinishDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.transWorkFinishDate"
									placeholder="Work Finish Date" autocomplete="off"
									id="workFinishDateedit"
									value="<s:property value="bugTracker.transWorkFinishDate"/>" required="required">
							</div>
						</div>
						</c:when>
						<c:otherwise>
						<input type="hidden" class="form-control input-sm"
									name="bugTracker.transWorkFinishDate"
									placeholder="Work Finish Date" autocomplete="off"
									id="workFinishDateedit"
									value="<s:property value="bugTracker.transWorkFinishDate"/>">
						</c:otherwise>
						</c:choose>
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">EstimatedHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.totalEstimatedHours"
									placeholder="Estimated Hours" autocomplete="off"
									value="<s:property value="bugTracker.totalEstimatedHours"/>" required="required">
							</div>
						</div>
						</c:when>
						<c:when test="${session.User.userrole_id.techSupport==true }">
								<div class="form-group">
							<label class="col-sm-2 control-label">EstimatedHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.totalEstimatedHours"
									placeholder="Estimated Hours" autocomplete="off"
									value="<s:property value="bugTracker.totalEstimatedHours"/>" readonly>
							</div>
						</div>
						</c:when>
						<c:otherwise>
						<input type="hidden" class="form-control input-sm"
									name="bugTracker.totalEstimatedHours"
									placeholder="Estimated Hours" autocomplete="off"
									value="<s:property value="bugTracker.totalEstimatedHours"/>" readonly>
						</c:otherwise>
						</c:choose>
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true || session.User.userrole_id.techSupport==true }">
						<div class="form-group">
							<label class="col-sm-2 control-label">Developer
								Estimated Hours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.totalDeveloperEstimatedHours"
									placeholder="developer Estimated Hours"
									value="<s:property value="bugTracker.totalDeveloperEstimatedHours"/>"
									autocomplete="off" required="required">
							</div>
						</div>
						</c:when>
						<c:otherwise>
						<input type="hidden" class="form-control input-sm"
									name="bugTracker.totalDeveloperEstimatedHours"
									placeholder="developer Estimated Hours"
									value="<s:property value="bugTracker.totalDeveloperEstimatedHours"/>"
									autocomplete="off">
						</c:otherwise>
						</c:choose>
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true || session.User.userrole_id.techSupport==true }">
						<div class="form-group">
							<label class="col-sm-2 control-label">WorkingHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.totalWorkingHours" placeholder="working hours"
									autocomplete="off"
									value="<s:property value="bugTracker.totalWorkingHours"/>" required="required">
							</div>
						</div>
						</c:when>
						<c:otherwise>
						<input type="hidden" class="form-control input-sm"
									name="bugTracker.totalWorkingHours" placeholder="working hours"
									autocomplete="off"
									value="<s:property value="bugTracker.totalWorkingHours"/>">
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true }">
						<div class="form-group">
							<label class="col-sm-2 control-label">ExtraHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.totalExtraHours" placeholder="Extra Hours"
									autocomplete="off"
									value="<s:property value="bugTracker.totalExtraHours"/>" required="required">
							</div>
						</div>
						</c:when>
						<c:when test="${session.User.userrole_id.techSupport==true }">
						<div class="form-group">
							<label class="col-sm-2 control-label">ExtraHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTracker.totalExtraHours" placeholder="Extra Hours"
									autocomplete="off"
									value="<s:property value="bugTracker.totalExtraHours"/>" readonly>
							</div>
						</div>
						</c:when>
						<c:otherwise>
								<input type="hidden" class="form-control input-sm"
									name="bugTracker.totalExtraHours" placeholder="Extra Hours"
									autocomplete="off"
									value="<s:property value="bugTracker.totalExtraHours"/>" readonly>
						</c:otherwise>
						</c:choose>
						<div class="form-group">
							<label for="uploadimage" class="col-sm-2 control-label">Upload
								File </label>
							<div class="col-sm-8">
								<div class="row">
									<div class="col-sm-6 file-upload">

										<input type="file" id="filePath"
											accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel,image/*"
											ng-file-select="onFileSelect($files)" name="filePath">
									</div>
									<div class="col-sm-6 ">
										<div id="fileinfo">
											<div id="fileError"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" id="save" class="btn btn-primary btn-lg"  >Update</button>
					</div>
				</div>
			</form>
		</section>
	</div>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript">
		$(function() {
			$('myForm').on('keypress', function(event) {
				if (event.which === 13 && $(event.target).is(':input')) {
					event.preventDefault();
					$('#save').trigger('click');
				}
			});
		});
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$(
									"#asssignDateedit , #startToWorkDateedit ,#workFinishDateedit ")
									.datepicker({
										dateFormat : "dd-mm-yy"

									});

						});
	</script>
	
												<script> 
	$(document).ready(function(){  
	 
		 
	    $("#myForm").validate({
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
	    
	    $('#save').click(function() { 
	  	if($("#myForm").valid())  
	    	 document.getElementById("myForm").submit(); 
	    });    
	    
	});

	</script>

</body>


</html>
