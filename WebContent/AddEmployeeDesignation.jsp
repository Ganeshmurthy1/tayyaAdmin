<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<%-- 
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>

<link rel="stylesheet" href="css/alert.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><s:property value="user" /></title>


</head>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<div class="sccuss-full-updated" id="success-alertdelete"
			style="display: none">
			<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="messagedelete"></div>
			<button type="button" id="successdelete" class="btn btn-primary">Ok</button>

			</div>
		</div>
		<div class="sccuss-full-updated" id="failed-alertdelete"
		style="display: none">
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-ban fa-3x" style="color: red;"></i>
			</div>

			<div class="col-sm-10" id="errormessagedelete"></div>
			<button type="button" id="faileddelete" class="btn btn-primary">Ok</button>

		</div>
	</div>
		<section class="content-header">
			<h1>Add Employee</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
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
										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="newBandCode">Band Code</label>
												<input type="text" class="form-control input-sm" value=""
													required id="newBandCode" name="newBandCode"
													placeholder="Enter new band code">
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
									onclick="checkNameExistingOrSave(document.getElementById('newBandName').value,document.getElementById('newBandCode').value);"
									class="btn btn-primary btn-sm">Add</button>

								<button type="button" class="btn btn-danger btn-sm"
									data-dismiss="modal">Close</button>

							</div>

						</div>
					</div>

				</div>
			</div>
			<div id="addDesignationPopup" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Employee Designation</h4>
						</div>

						<div class="modal-body">
							<form action="insertDesignationName" method="post" class="form-horizontal"
								name="myForm" id="formSubmitDesg">
								<div class="row">
									<div class="col-sm-12">

										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="newDesgName">Designation Name</label>
												<input type="text" class="form-control input-sm" value=""
													required id="newDesgName" name="newDesgName"
													placeholder="Enter new Designation name">
											</div>
										</div>
										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="newDesgCode">Designation Code</label>
												<input type="text" class="form-control input-sm" value=""
													required id="newDesgCode" name="newDesgCode"
													placeholder="Enter new Designation Code">
											</div>
										</div>
										<div class="col-sm-12" id="hiddenDivforError">
										</div>
										<div class="col-sm-12 " style="display: none;" id=addDesgBandList>
											<div class="form-group">
												<label for="designation" >Band List (Code)</label>
													<!-- <div class="col-sm-8"> -->
														<select class="form-control input-sm" name="newDesgBandid"
															id="newDesgBandid" >
															<option value="">Select band</option>
															<c:forEach var="band" items="${bandModelList}">
																<c:choose>
																	<c:when test="${band.bandName!= null}">
																		<option value="${band.bandId}">${band.bandName} ( ${band.bandCode} )</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${band.bandId}">${band.bandName} ( ${band.bandCode} )</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													<!-- </div> -->
													<!-- <div class="col-sm-1">
														<h4>
															<a href="#addBandPopup" class="btn btn-info " data-toggle="modal" 
																style="border: 2px solid #a1a1a1; font-size: x-small; border-radius: 35px"
																role="button"> <i class="fa fa-plus"> Add Band</i></a>
														</h4>
							
													</div> -->
											</div>

										</div>
									</div>
								</div>

							</form>

						</div>
						<div class="modal-footer">
							<div class="col-sm-12">
								<button type="button" id="addDesgButton"
									onclick="checkNameExistingOrSaveForEmployee(document.getElementById('newDesgName').value,document.getElementById('newDesgCode').value,document.getElementById('newDesgBandid').value);"
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
						<div class="col-sm-10" id="message"></div>
						<button type="button" id="cancelError" class="btn btn-primary">Ok</button>
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
				<form action="AddEmployeeDesignation" method="post" class="form-horizontal"
					name="myForm" id="empDesgForm">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Band id </label>
						<div class="col-sm-8">
							<input maxlength="50" type="text" class="form-control input-sm"
								name="employeeDesignationModel.bandId" id="bandId" value='<s:property value="employeeDesignationModel.bandId"/>'
								placeholder="Enter Band Id" autocomplete="off" readonly="readonly">
						</div>
					</div>
						
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
						<label for="designation" class="col-sm-2 control-label">Band
							List </label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="employeeDesignationModel.bandName"
								id="bandName" >
								<option value="">Select band (Code)</option>
								<c:forEach var="band" items="${bandModelList}">
									<c:choose>
										<c:when test="${band.bandName!= null}">
											<option value="${band.bandName}">${band.bandName} ( ${band.bandCode} )</option>
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
											<option value="${designation.desgName}">${designation.desgName} ( ${designation.desgCode} )</option>
										</c:when>
										<c:otherwise>
											<option value="${designation.desgName}">${designation.desgName} ( ${designation.desgCode} </option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-1">
							<h4>
								<a href="#addDesignationPopup" class="btn btn-info " data-toggle="modal" 
									style="border: 2px solid #a1a1a1; font-size: x-small; border-radius: 35px"
									role="button"> <i class="fa fa-plus"> Add Desg</i></a>
							</h4>

						</div>
					</div>
					
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" class="btn btn-primary btn-lg" onclick="checkExistingValidation();"
							  id="saveEmployeeDetailsButton">Save Details</button>
						</div>
						
					</div>
				</form>
			</div>




		</section>



	</div>


	<%@ include file="DashboardFooter.jsp"%>
<script >
function checkNameExistingOrSave(bandName,bandCode){
	var boolName=true;
	var boolCode=true;
	<c:forEach items="${bandModelList}" var="item" varStatus="status"> 
		if("${item.bandName}".toLowerCase() === bandName.toLowerCase()){
			document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Name Already Existed !   Please try another ...</i></span>';
			boolName=false;
		}
		if("${item.bandCode}".toLowerCase() === bandCode.toLowerCase()){
			document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Code Already Existed !   Please try another ...</i></span>';
			boolCode=false;
		}
	</c:forEach>
	if(boolName && boolCode){
		$("#formSubmit").submit();
	}
}
function checkNameExistingOrSaveForEmployee(empName,empCode,newDesgBandid){
	var boolName=true;
	var boolCode=true;
	<c:forEach items="${employeeDesignationsList}" var="item" varStatus="status"> 
		if("${item.desgName}".toLowerCase() === empName.toLowerCase()){
			document.getElementById('hiddenDivforError').innerHTML = '<span style="color:red;font-size: small; "><i>Name Already Existed !   Please try another ...</i></span>';
			boolName=false;
		}
		if("${item.desgCode}".toLowerCase() === empCode.toLowerCase()){
			document.getElementById('hiddenDivforError').innerHTML = '<span style="color:red;font-size: small; "><i>Code Already Existed !   Please try another ...</i></span>';
			boolCode=false;
		}
	</c:forEach>
	if(boolName && boolCode){
		alert(newDesgBandid);
		document.getElementById('hiddenDivforError').innerHTML = '<span style="color:green;font-size: small; "><i>Name is Available !  Please Associate it with Any Band</i></span>';
		$("#addDesgBandList").show();
		if(newDesgBandid>0){
			  $("#formSubmitDesg").submit(); 
		}
		else 
			document.getElementById('hiddenDivforError').innerHTML = '<span style="color:red;font-size: small; "><i>Please select any band</i></span>';
	}
}
function checkExistingValidation(obj){
	var totUrl = $(location).attr('href');
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
	/* $("#empDesgForm").submit(); */ 
	        var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "AddEmployeeDesignation";
			bandId = $("#bandId").val();
			userName = $("#userName").val();
	        designation = $("#designation").val();
	        bandName = $("#bandName").val(); 
	        $.ajax({
	            type : "POST",
	            url : finalUrl,
	            data : {
	            	bandId : bandId, userName : userName, designation : designation, bandName :bandName
				},
	            success : function(data, status) {
	            	alert(data.insertionStatusMsg);
	            	alert(data.insertionResponseMsg);
	            	console.log("data",data);
	            	
					if (data.insertionMsgMap.response == "inserted") {
						$('#success-alertdelete').show();
						$('#messagedelete').text(data.insertionMsgMap.msg);
						$('#successdelete').click(
										function() {
											$('#success-alertdelete').hide();
											window.location.assign($(location).attr('href'));
										});

					} else if (data.insertionMsgMap.response == "already" || data.insertionMsgMap.response == "warning") {
						$('#failed-alertdelete').show();
						$('#errormessagedelete') .text( data.insertionMsgMap.msg);
						$('#faileddelete')
								.click( function() {
											$( '#failed-alertdelete') .hide();
										});
					}
					else{
						alert('Data is null OR Data Insufficient in Database');
					}
				},
				error : function(data, status) {
					console.log(data);
					console.log(status);
				}
	        });
	   
}
$('#designation').on('change', function() {
	  
	  var desgName=this.value;
	  <c:forEach items="${employeeDesignationsList}" var="item" varStatus="status"> 
		if("${item.desgName}".toLowerCase() === desgName.toLowerCase()){
			alert( this.value );
			alert("${item.employeeBandModel.bandName}");
			$('#bandName').val("${item.employeeBandModel.bandName}");
			
	 }
		
	</c:forEach>
	  
})
</script>


</body>
</html>