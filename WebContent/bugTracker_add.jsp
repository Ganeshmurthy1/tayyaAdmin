<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <style>
 .hidden
 {
 	display:none;
 }
  .error {
    color:red;
}
.valid {
    color:green;
}
 </style>
 <link rel="stylesheet" href="css/alert.css">
<script src="js/techsupport_emplist.js"></script>

<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<section class="content-header">
		<h1>
			<i class="fa fa-plus"></i>Create Bug
		</h1>
		<div class="breadcrumb-wrapper">
			<%-- <span class="label">You are here:</span> --%>
			<ol class="breadcrumb">
				<li><a href="goBugTrackerList">Bug List</a></li>
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
			<form action="saveBugTracker" method="post" class="form-horizontal"
				name="myForm" id="myForm" enctype="multipart/form-data">
				<input type="hidden" id="notiCreatedUserId" name="userId"
								value="<s:property value="#session.User.id" />"> 
								<input  name="companyId"
								type="hidden" id="notiCreatedCompanyId"
								value="<s:property value="#session.User.Companyid" />">
				<div class="form-group">
					<label class="col-sm-2 control-label"> </label>
					<div class="col-sm-8">
						<div class="form-group">
							<label class="col-sm-2 control-label">Title</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" name="title" id="title"
									placeholder="bug title" autocomplete="off" required>
							</div>
						</div>

					<!-- 	<div class="form-group">
							<label class="col-sm-2 control-label">Bug Tag</label>
							<div class="col-sm-8">
							<textarea rows="2" cols="2"  class="form-control input-sm" 	name="tag" placeholder="bug tag"  
									required></textarea>
							 </div>
						</div> -->

 					<div class="form-group">
							<label class="col-sm-2 control-label">Description</label>
							<div class="col-sm-8">
							<textarea rows="4" cols="4"  class="form-control input-sm" 	name="description" placeholder="description"  
									required></textarea>
							 </div>
						</div>
						 
  						<div class="form-group">
							<label class="col-sm-2 control-label">Bug Type</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="level" name="bugType" required>
										<c:forEach var="statusItem" items="${bugTrackerUtility.bugType}">
											 <option value="${statusItem}">${statusItem}</option>
											 </c:forEach>
									 </select>
							</div>
						</div> 
						<div class="form-group">
							<label class="col-sm-2 control-label">Bug Priority</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="level" name="level" required>
									<c:forEach var="statusItem" items="${bugTrackerUtility.bugPriority}">
											 <option value="${statusItem}">${statusItem}</option>
											 </c:forEach>
									 
								</select>
							</div>
						</div> 
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">status</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="status" name="status" required>
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
						</c:when>
						<c:otherwise>
							<select class="form-control input-sm hidden" id="status" name="status" required="">
								<option value="Created" selected="selected">Created</option>
							</select>
						</c:otherwise>
						
						</c:choose>
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">Active</label>
							<div class="col-sm-8">
							<select  class="form-control input-sm" name="bugActiveStatus" >
							<option value="1">Active</option>
							<option value="0">In Active</option>
							</select>
							</div>
						</div>
						</c:when>
						<c:otherwise>
							<select  class="form-control input-sm hidden" name="bugActiveStatus" >
							<option value="1" selected="selected">Active</option>
							</select>
						</c:otherwise>
						</c:choose>
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">Assigned To</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									 id="assignTo"  placeholder="assigned to"
									autocomplete="off">
								  <input type="hidden" name="assignTo" id="techSupportId">  
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Assigned By</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
								 placeholder="assigned by" autocomplete="off" id="assignby" value='<s:property value="%{#session.User.Username}"/>(<s:property value="%{#session.User.Email}"/>) '> 
							  <input type="hidden" name="assignedBy" value="<s:property value="%{#session.User.id}"/>"    id="techHeadId" > 
							</div>
						</div>
						</c:when>
						</c:choose>
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">AssignedDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="transAssignedDate" placeholder="Assigned Date"
									autocomplete="off" id="asssignDate">
							</div>
						</div>
						</c:when>
						</c:choose>
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
							<div class="form-group">
							<label class="col-sm-2 control-label">StartToWorkDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="transStartToWorkDate" placeholder="Start To Work Date"
									autocomplete="off" id="startToWorkDate">
							</div>
						</div>
						</c:when>
						</c:choose>
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">WorkFinishDate</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="transWorkFinishDate" placeholder="Work Finish Date"
									autocomplete="off" id="workFinishDate">
							</div>
						</div>
						</c:when>
						</c:choose>
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">EstimatedHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="totalEstimatedHours" placeholder="Estimated Hours"
									autocomplete="off">
							</div>
						</div>
						</c:when>
						</c:choose>
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">Developer EstimatedHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="totalDeveloperEstimatedHours" placeholder="developer Estimated Hours"
									autocomplete="off">
							</div>
						</div>
						</c:when>
						</c:choose>
						
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">WorkingHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="totalWorkingHours" placeholder="working hours"
									autocomplete="off"  >
							</div>
						</div>
						</c:when>
						</c:choose>
						<c:choose>
						<c:when test="${session.User.userrole_id.techHead==true}">
						<div class="form-group">
							<label class="col-sm-2 control-label">ExtraHours</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm"
									name="totalExtraHours" placeholder="Extra Hours"
									autocomplete="off">
							</div>
						</div>
						</c:when>
						</c:choose>
						
						<div class="form-group">
							<label for="uploadimage" class="col-sm-2 control-label">Upload
								File </label>
							<div class="col-sm-8">
								<div class="row">
									<div class="col-sm-6 file-upload">
										<input type="file" id="filePath" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel,image/*" 
											ng-file-select="onFileSelect($files)" name="bugTrackerHistory.filePath">
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
						<button type="submit" id="save" class="btn btn-primary btn-lg">Create Bug</button>
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
<script type="text/javascript">
$(document).ready(function(){
	
     $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
          return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
      }, "This field cannot have symbols.");

      $.validator.addMethod("cusValidationAlphaName",function(value,element){
          return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
      },"This field cannot have numbers and symbols."); 
      $.validator.addMethod("cusValidationforprice",function(value,element){
          return this.optional(element) || /^[0-9.]+$/i.test(value);
      },"This field cannot have Char and symbols.");


$("#myForm").validate({
	 rules: {
         
         "email": {
             required: true,
             email: true
         },
     },
     
     messages: {
         
         "email": {
             required: "Please, enter an email",
             email: "Email is invalid"
         },
     },

    submitHandler: function (form) { 
        return false; 
    }
});

$('#save').click(function() {
    $("#myForm").valid();
   if($("#myForm").valid())
    	document.getElementById("myForm").submit();
 	 
}); 

});


</script>
</body>
	
 
</html> 