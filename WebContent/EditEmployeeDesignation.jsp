<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>


<link rel="stylesheet" href="css/alert.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><s:property value="user" /></title>



<%-- <script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "PaymentCardList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script> --%>
<%-- <script type="text/javascript">
	function numbersonly(e) {
		var unicode = e.charCode ? e.charCode : e.keyCode
		if (unicode != 8) { //if the key isn't the backspace key (which we should allow)
			if (unicode<48||unicode>57) //if not a number
				return false //disable key press
		}
	}

</script> --%>
</head>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Edit Employee Designation</h1>
		</section>
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
				<h4>
					<a href="ShowEmployeeBandList" class="btn btn-primary pull-left"
						style="border: 2px solid #a1a1a1; border-radius: 15px"
						role="button"> <i class="fa fa-list"> View List</i></a>
				</h4>

			</div>

			<div id="addBandPopup" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Employee Band</h4>
						</div>

						<div class="modal-body">
							<%-- <span style='color: red; font-size: 11px; display: block'>
								(* Please separate emails with semicolon) </span> --%>
							<form action="insertBandName" method="post" class="form-horizontal"
								name="myForm" id="formSubmit">
								<div class="row">
									<div class="col-sm-12">
										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="newBandName">Band Name</label>
												<input type="text" class="form-control input-sm" value=""
													required id="newBandName" name="newBandName"
													placeholder="Enter new band name">
											</div>
										</div>
										<div class="col-sm-12" id="hiddenDiv">
										</div>
									</div>
								</div>

							</form>

						</div>
						<div class="modal-footer">
							<div class="col-sm-12">
								<button type="button" id="addBandButton"
									onclick="checkNameExistingOrSave(document.getElementById('newBandName').value);"
									class="btn btn-primary btn-sm">Add</button>

								<button type="button" class="btn btn-danger btn-sm"
									data-dismiss="modal">Close</button>

							</div>

						</div>
					</div>

				</div>
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

			<div class="row" id="dash-us-register">
				<form action="UpdateEmployeeInfo" method="post" class="form-horizontal"
					name="myForm" id="empDesgForm">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Band id </label>
						<div class="col-sm-8">
							<input maxlength="50" type="text" class="form-control input-sm"
								name="employeeDesignationModel.bandId" id="bandId" value='<s:property value="employeeDesignationModel.bandId"/>'
								placeholder="Enter Band Id" autocomplete="off" readonly="readonly" >
						</div>
					</div>
					<input type="hidden" name="employeeDesignationModel.id" value='<s:property value="employeeDesignationModel.id"/>'>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Employee
							Name </label>
						<div class="col-sm-8">
							<input maxlength="50" type="text" class="form-control input-sm"
								name="employeeDesignationModel.userName" id="userName" value='<s:property value="employeeDesignationModel.userName"/>'
								placeholder="Enter User Name" autocomplete="off" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="designation" class="col-sm-2 control-label">Designation
						</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="employeeDesignationModel.designation"
								id="designation" >
								<option value="">Select Designation (Code)</option>
								<c:forEach var="designation" items="${employeeDesignationsList}">
									<c:choose>
										<c:when test="${designation.desgName!= null}">
											<option value="${designation.desgName}" selected="selected">${designation.desgName} ( ${designation.desgCode} )</option>
										</c:when>
										<c:otherwise>
											<option value="${designation.desgName}">${designation.desgName} ( ${designation.desgCode} </option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="designation" class="col-sm-2 control-label">Band
							List </label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="employeeDesignationModel.bandName"
								id="bandName" >
								<option value="">Select band (Code)</option>
								<c:forEach var="band" items="${bandModelList}">
									<c:choose>
										<c:when test="${band.bandName!= null}">
											<option value="${band.bandName}" selected="selected">${band.bandName} ( ${band.bandCode} )</option>
										</c:when>
										<c:otherwise>
											<option value="${band.bandName}">${band.bandName} ( ${band.bandCode} )</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-1">
							<h4>
								<a href="#addBandPopup" class="btn btn-info " data-toggle="modal" 
									style="border: 2px solid #a1a1a1; font-size: x-small; border-radius: 35px"
									role="button"> <i class="fa fa-plus"> Add Band</i></a>
							</h4>

						</div>
					</div>


					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" class="btn btn-primary btn-lg" onclick="checkExistingValidation();"
							  id="saveEmployeeDetailsButton">Update Details</button>
						</div>
						
					</div>
				</form>
			</div>




		</section>



	</div>


	<%@ include file="DashboardFooter.jsp"%>
<script >
function checkNameExistingOrSave(bandName){
	var bool=true;
	<c:forEach items="${bandList}" var="item" varStatus="status"> 
		if("${item}".toLowerCase() === bandName.toLowerCase()){
			document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Name Already Existed !   Please try another ...</i></span>';
			bool=false;
		}
	</c:forEach>
	if(bool){
		$("#formSubmit").submit();
	}
}

function checkExistingValidation(obj){
	var totUrl = $(location).attr('href');
	alert(totUrl);
	if(!document.getElementById('userName').value){
		alert("Please provide the Name");
	}
	else if(designation.value ==''){
		alert("Please select the Designation");
	}
	else if(bandName.value ==''){
		alert("Please select the Band name");
	}
	else{
		checkDesignationAssignedtoAnyBand();
	}
}

function checkDesignationAssignedtoAnyBand(){
	$("#empDesgForm").submit();
}

</script>


</body>
</html>