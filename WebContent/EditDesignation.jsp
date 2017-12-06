<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Designation</title>





<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "adduserdesignation";
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
			<h1>Update Designation</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
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

				<!-- <form action="adddesignation" method="post" class="form-horizontal"
					name="myForm"> -->
				<form action="UpdateDesignationInfo" method="post"
					class="form-horizontal" name="myForm" id="formSubmitDesg">
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
					
					<input type="hidden" value='<s:property value="employeeDesignations.desgId"/>'  id="currentDesgId" name="employeeDesignations.desgId"  >
					<%-- <div class="form-group">
						<label for="newDesgId" class="col-sm-2 control-label">Designation
							Id</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value='<s:property value="employeeDesignations.desgId"/>'
								required id="newDesgId" name="newDesgId" readonly="readonly"
								placeholder="Enter new Designation name">
						</div>
					</div> --%>
					
					<div class="form-group">
						<label for="newDesgName" class="col-sm-2 control-label">Designation
							Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value='<s:property value="employeeDesignations.desgName"/>'
								required id="newDesgName" name="employeeDesignations.desgName"
								placeholder="Enter new Designation name">
						</div>
					</div>

					<div class="form-group">
						<label for="newDesgCode" class="col-sm-2 control-label">Designation
							Code</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value='<s:property value="employeeDesignations.desgCode"/>'
								required id="newDesgCode" name="employeeDesignations.desgCode"
								placeholder="Enter new Designation Code">
						</div>
					</div>

					<div class="col-sm-12" id="hiddenDivforError"></div>
					<div  id=addDesgBandList>
						<div class="form-group">
							<label for="designation" class="col-sm-2 control-label">Band
								List (Code)</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" name="newDesgBandid" 
									id="newDesgBandid">
									<c:forEach var="band" items="${bandModelList}">
										<c:choose>
											<c:when test="${band.bandName == employeeDesignations.employeeBandModel.bandName}">
												<option value="${band.bandId}" selected="selected">${band.bandName}(
													${band.bandCode} )</option>
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>

					</div>



					 </form>




					<div class="text-center">
						<div class="col-xs-12 submitWrap text-center">
						<button type="button" id="addDesgButton"
									onclick="checkNameExistingOrSaveForEmployee(document.getElementById('newDesgName').value,document.getElementById('newDesgCode').value);"
									class="btn btn-primary btn-lg">Update Designation</button>
							<!-- <button type="button" class="btn btn-primary btn-lg">Add
								Designation</button> -->
						</div>
					</div>
				<!-- </form> -->
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>

<script >

function checkNameExistingOrSaveForEmployee(empName,empCode){
	var boolName=true;
	var boolCode=true;
	var currentDesgId=document.getElementById('currentDesgId').value;
	
	<c:forEach items="${employeeDesignationsList}" var="item" varStatus="status"> 
		if("${item.desgName}".toLowerCase() === empName.toLowerCase() && !("${item.desgId}" == currentDesgId)){
			document.getElementById('hiddenDivforError').innerHTML = '<span style="color:red;font-size: small; "><i>Name Already Existed !   Please try another ...</i></span>';
			boolName=false;
		}
		if("${item.desgCode}".toLowerCase() === empCode.toLowerCase() && !("${item.desgId}" == currentDesgId)){
			document.getElementById('hiddenDivforError').innerHTML = '<span style="color:red;font-size: small; "><i>Code Already Existed !   Please try another ...</i></span>';
			boolCode=false;
		}
	</c:forEach>
	if(boolName && boolCode){
		$("#formSubmitDesg").submit(); 
	}
}
</script>

</body>

</html>