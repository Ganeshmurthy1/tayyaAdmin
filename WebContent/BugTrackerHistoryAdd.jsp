<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<%-- 
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script> --%>
 <link rel="stylesheet" href="css/alert.css">
 
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "listBugTracker";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();
		});
	});
</script>
<script src="js/techsupport_emplist.js"></script>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body data-ng-controller="AppCtrl">

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Create Bug Tracker History</h1>
		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
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
			<%!String bugTrackerId;%>
 			<%bugTrackerId= request.getParameter("id");%>
			<form action="createNewBugHistory" method="post" class="form-horizontal"
				name="myForm" id="myForm"  enctype="multipart/form-data">
				<input type="hidden" name="id" value="<%=bugTrackerId%>">
				<div class="form-group">
					<label class="col-sm-2 control-label"> </label>
					<div class="col-sm-8">
					<s:if  test="%{#session.User.userrole_id.isTechHead() || #session.User.userrole_id.isTechSupport()}">
					<div class="form-group">
							<label class="col-sm-2 control-label">Assigned To</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									  id="assignTo" placeholder="assigned to"
									autocomplete="off" required>
										<input type="hidden" name="bugTrackerHistory.assignTo"  id="techSupportId">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Assigned By</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									  placeholder="assigned by"
									autocomplete="off" id="assignby" required value='<s:property value="%{#session.User.Username}"/>(<s:property value="%{#session.User.Email}"/>) '>
										<input type="hidden" name="bugTrackerHistory.assignedBy" value="<s:property value="%{#session.User.id}"/>" id="techHeadId">
							</div>
						</div>
					</s:if>
					<div class="form-group">
							<label class="col-sm-2 control-label">Bug Type</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="level" name="bugTrackerHistory.bugType" required>
										<c:forEach var="statusItem" items="${bugTrackerUtility.bugType}">
											 <option value="${statusItem}">${statusItem}</option>
											 </c:forEach>
									 </select>
							</div>
						</div> 
						<div class="form-group">
							<label class="col-sm-2 control-label">Bug Priority</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="level" name="bugTrackerHistory.level" required>
									 
									<c:forEach var="statusItem" items="${bugTrackerUtility.bugPriority}">
											 <option value="${statusItem}">${statusItem}</option>
											 </c:forEach>
									 
								</select>
							</div>
						</div> 
						<div class="form-group">
							<label class="col-sm-2 control-label">status</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="status" name="bugTrackerHistory.status" required>
										 <c:forEach var="statusItem" items="${bugTrackerUtility.bugStatus}">
										 <c:choose>
											 <c:when
												  test="${statusItem=='Created'}">
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
							<label class="col-sm-2 control-label">AssignedDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.transAssignedDate" placeholder="Assigned Date"
									autocomplete="off" required id="asssignDate">
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">StartToWorkDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.transStartToWorkDate" required placeholder="Start To Work Date"
									autocomplete="off" id="startToWorkDate">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">WorkFinishDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.transWorkFinishDate" required placeholder="Work Finish Date"
									autocomplete="off" id="workFinishDate">
							</div>
						</div>
						 <div class="form-group">
							<label class="col-sm-2 control-label">EstimatedHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.estimatedHours" required placeholder="Estimated Hours"
									autocomplete="off">
							</div>
						</div> 
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Developer EstimatedHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.developerEstimatedHours" required placeholder="developer Estimated Hours"
									autocomplete="off">
							</div>
						</div> 
						 <div class="form-group">
							<label class="col-sm-2 control-label">WorkingHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.workingHours" required placeholder="working hours"
									autocomplete="off"  >
							</div>
						</div> 
						 <div class="form-group">
							<label class="col-sm-2 control-label">ExtraHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.extraHours" required placeholder="Extra Hours"
									autocomplete="off">
							</div>
						</div> 
						<!-- <div class="form-group">
							<label class="col-sm-2 control-label">EstimatedHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.estimatedHours" placeholder="Estimated Hours"
									autocomplete="off">
							</div>
						</div> -->
						
						<!-- <div class="form-group">
							<label class="col-sm-2 control-label">Developer EstimatedHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="bugTrackerHistory.developerEstimatedHours" placeholder="developer Estimated Hours"
									autocomplete="off">
							</div>
						</div> -->
						
						
						
					 <div class="form-group">
							<label class="col-sm-2 control-label">comments</label>
							<div class="col-sm-8">
							<textarea cols="3" rows="3" class="form-control input-sm"
									name="bugTrackerHistory.comments" required placeholder="comments"></textarea>
								 
							</div>
						</div>
							
					 
						 
						<div class="form-group">
							<label for="uploadimage" class="col-sm-2 control-label">Upload
								File </label>
							<div class="col-sm-8">

								<div class="row">
									<div class="col-sm-6 file-upload">
 
										<input type="file" id="filePath" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel,image/*" 
											ng-file-select="onFileSelect($files)" required name="bugTrackerHistory.filePath">
									</div>

									<!-- 	ng-file-select="onFileSelect($files)" -->
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
						<button type="button" id="save" class="btn btn-primary btn-lg" >Add</button>
					</div>
				</div>
			</form>
		</section>
	</div>


	<%@ include file="DashboardFooter.jsp"%>
<script type="text/javascript">
$(function(){
	  $('myForm').on('keypress', function(event){
	    if(event.which === 13 && $(event.target).is(':input')){
	        event.preventDefault();
	        $('#save').trigger('click');
	    }
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