<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Config</title>
<link rel="stylesheet" href="css/alert.css">


<script type="text/javascript">
	 $(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "editRmConfig";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body>

	<!-- Content Wrapper. Contains page content -->

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>RM Configuration Update</h1>
		</section>

		<!-- Main content -->
		<section class="content">


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
				<input type="hidden" id="parentid"
					value="<s:property value="%{#session.Company.companyid}"/>">
				<input type="hidden" id="selectedCompanyUserId"> <input
					type="hidden" id="selectchildid" value="">
					
				<form action="UpdateRmConfig" method="post" id="bookingConfirmed" class="form-horizontal">
					<input type="hidden" id="rmConfigId" name="id"> 
				<input type="hidden" id="dynamicFieldDataSend" name="dynamicFieldDataSend"  value="">
				<input type="hidden" id="dynamicFieldDropdownSend" name="dynamicFieldDropdownSend"  value="">
					<div class="form-group">
						<label class="col-sm-3 control-label">Product Type</label>
						<div class="col-sm-7">
							<label class="form-control input-sm">RM - Config Update</label>
						</div>
					</div>
					<hr>
					<div class="form-group table-responsive dash-table">
						<table id="mytable" class="table table-striped"
							data-sort-name="name" data-sort-order="desc">
							<thead>
								<tr>
									<td><h4>
											<b>Select Corporate Company </b>
										</h4> <select class="form-control input-sm" name="companyUserId"
										id="companyUserId" onchange="getRmConfig()"
										required="required">
											<option value="">Select company</option>
											<s:iterator value="companyList" status="rowCount">
												<option value="${companyid}">${Companyname}(${companyid})</option>
											</s:iterator>
									</select></td>

								</tr>
							</thead>
						</table>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label" for="empCode">Employee
							code</label>
						<div class="col-sm-7">
							<input type="checkbox" id="empCode" name="empCode" value="true"
								autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.empCodeType!=null && $rmTotalData.empCodeType=='text'">
							<select class="form-control input-sm" name="empCodeType" id="empCodeType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.empCodeType!=null && $rmTotalData.empCodeType=='select'">
							<select class="form-control input-sm" name="empCodeType" id="empCodeType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="empCodeType" id="empCodeType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="department">Department</label>
						<div class="col-sm-7">
							<input type="checkbox" id="department" name="department"
								value="true" autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.departmentType!=null && $rmTotalData.departmentType=='text'">
							<select class="form-control input-sm" name="departmentType" id="departmentType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.departmentType!=null && $rmTotalData.departmentType=='select'">
							<select class="form-control input-sm" name="departmentType" id="departmentType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="departmentType" id="departmentType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
							
						</div>

					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="costCenter">Cost
							center</label>
						<div class="col-sm-7">
							<input type="checkbox" id="costCenter" name="costCenter"
								value="true" autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.costCenterType!=null && $rmTotalData.costCenterType=='text'">
							<select class="form-control input-sm" name="costCenterType" id="costCenterType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.costCenterType!=null && $rmTotalData.costCenterType=='select'">
							<select class="form-control input-sm" name="costCenterType" id="costCenterType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="costCenterType" id="costCenterType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="approverName">Approver
							name</label>
						<div class="col-sm-7">
							<input type="checkbox" id="approverName" name="approverName"
								value="true" autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.approverNameType!=null && $rmTotalData.approverNameType=='text'">
							<select class="form-control input-sm" name="approverNameType" id="approverNameType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.approverNameType!=null && $rmTotalData.approverNameType=='select'">
							<select class="form-control input-sm" name="approverNameType" id="approverNameType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="approverNameType" id="approverNameType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="location">Location</label>
						<div class="col-sm-7">
							<input type="checkbox" id="locations" name="location"
								value="true" autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.locationType!=null && $rmTotalData.locationType=='text'">
							<select class="form-control input-sm" name="locationType" id="locationType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.locationType!=null && $rmTotalData.locationType=='select'">
							<select class="form-control input-sm" name="locationType" id="locationType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="locationType" id="locationType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="trNumber">TR
							Number</label>
						<div class="col-sm-7">
							<input type="checkbox" id="trNumber" name="trNumber" value="true"
								autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.trNumberType!=null && $rmTotalData.trNumberType=='text'">
							<select class="form-control input-sm" name="trNumberType" id="trNumberType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.trNumberType!=null && $rmTotalData.trNumberType=='select'">
							<select class="form-control input-sm" name="trNumberType" id="trNumberType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="trNumberType" id="trNumberType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="bussinessUnit">Business
							unit</label>
						<div class="col-sm-7">
							<input type="checkbox" id="bussinessUnit" name="bussinessUnit"
								value="true" autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.bussinessUnitType!=null && $rmTotalData.bussinessUnitType=='text'">
							<select class="form-control input-sm" name="bussinessUnitType" id="bussinessUnitType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.bussinessUnitType!=null && $rmTotalData.bussinessUnitType=='select'">
							<select class="form-control input-sm" name="bussinessUnitType" id="bussinessUnitType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="bussinessUnitType" id="bussinessUnitType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="projectCode">Project
							Code</label>
						<div class="col-sm-7">
							<input type="checkbox" id="projectCode" name="projectCode"
								value="true" autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.projectCodeType!=null && $rmTotalData.projectCodeType=='text'">
							<select class="form-control input-sm" name="projectCodeType" id="projectCodeType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.projectCodeType!=null && $rmTotalData.projectCodeType=='select'">
							<select class="form-control input-sm" name="projectCodeType" id="projectCodeType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="projectCodeType" id="projectCodeType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="reasonForTravel">Reason
							for travel</label>
						<div class="col-sm-7">
							<input type="checkbox" id="reasonForTravel"
								name="reasonForTravel" value="true" autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.reasonForTravelType!=null && $rmTotalData.reasonForTravelType=='text'">
							<select class="form-control input-sm" name="departmentType" id="reasonForTravelType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.reasonForTravelType!=null && $rmTotalData.reasonForTravelType=='select'">
							<select class="form-control input-sm" name="reasonForTravelType" id="reasonForTravelType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="reasonForTravelType" id="reasonForTravelType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="billNonBill">Billable/
							non billable</label>
						<div class="col-sm-7">
							<input type="checkbox" id="billNonBill" name="billNonBill"
								value="true" autocomplete="off">
						</div>
						<div class="col-sm-3">
						<s:if test="$rmTotalData.billNonBillType!=null && $rmTotalData.billNonBillType=='text'">
							<select class="form-control input-sm" name="billNonBillType" id="billNonBillType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
							</s:if>
							<s:elseif test="$rmTotalData.billNonBillType!=null && $rmTotalData.billNonBillType=='select'">
							<select class="form-control input-sm" name="billNonBillType" id="billNonBillType"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
							</s:elseif>
							<s:else>
							<select class="form-control input-sm" name="billNonBillType" id="billNonBillType"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
							</s:else>
						</div>
					</div>
					
					<div class="dynamicHideDiv">
					
					
					<div class="form-group">
						<input  style="font-weight: bold;"  type="text" autocomplete="off" name="dynamicLable1" class="col-sm-2 control-label" id="dynamicLable1" placeholder="Enter Dynamic Lable" >
						<div class="col-sm-7">
							<!-- <input type="checkbox" id="dynamicLableCheckBox1" name="dynamicLableCheckBox1"
								value="true" autocomplete="off"> -->
						</div>
						
						<div class="col-sm-3">
						<s:if test="$dropdownName0[0]=='text'">
						<select class="form-control input-sm" name="dynamicLableType1" id="dynamicLableType1"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
						</s:if>
						<s:else>
						<select class="form-control input-sm" name="dynamicLableType1" id="dynamicLableType1"
								required="required">
								<option value="text" >TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
						</s:else>
						<s:if test="$parlellDropDown!=null">
						<select class="form-control input-sm" name="dynamicLableType1" id="dynamicLableType1"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select" >Select</option>

							</select>
						</s:if>
							
						</div>
					</div>
					
					
					
					
					
					<div class="form-group">
						<input style="font-weight: bold;" type="text" autocomplete="off" name="dynamicLable2" class="col-sm-2 control-label" id="dynamicLable2" placeholder="Enter Dynamic Lable" >
						<div class="col-sm-7">
							<!-- <input type="checkbox" id="dynamicLableCheckBox2" name="dynamicLableCheckBox2"
								value="true" autocomplete="off"> -->
						</div>
						<div class="col-sm-3">
						<s:if test="$dropdownName1[0]=='text'">
						<select class="form-control input-sm" name="dynamicLableType2" id="dynamicLableType2"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
						</s:if>
						<s:else>
						<select class="form-control input-sm" name="dynamicLableType2" id="dynamicLableType2"
								required="required">
								<option value="text">TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
						</s:else>
						</div>
					</div>
					
					
					
					
					<div class="form-group">
						<input style="font-weight: bold;"  type="text" autocomplete="off" name="dynamicLable3" class="col-sm-2 control-label" id="dynamicLable3" placeholder="Enter Dynamic Lable" >
						<div class="col-sm-7">
							<!-- <input type="checkbox" id="dynamicLableCheckBox3" name="dynamicLableCheckBox3"
								value="true" autocomplete="off"> -->
						</div>
						<div class="col-sm-3">
						<s:if test="$dropdownName2[0]=='text'">
						<select class="form-control input-sm" name="dynamicLableType3" id="dynamicLableType3"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
						</s:if>
						<s:else>
						<select class="form-control input-sm" name="dynamicLableType3" id="dynamicLableType3"
								required="required">
								<option value="text">TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
						</s:else>
					</div>
					</div>
					
					
					
					
					
					<div class="form-group">
						<input style="font-weight: bold;"  type="text" autocomplete="off" name="dynamicLable4" class="col-sm-2 control-label" id="dynamicLable4" placeholder="Enter Dynamic Lable" >
						<div class="col-sm-7">
							<!-- <input type="checkbox" id="dynamicLableCheckBox4" name="dynamicLableCheckBox4"
								value="true" autocomplete="off"> -->
						</div>
						<div class="col-sm-3">
						
						<s:if test="$dropdownName3[0]=='text'">
						<select class="form-control input-sm" name="dynamicLableType4" id="dynamicLableType4"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
						</s:if>
						<s:else>
						<select class="form-control input-sm" name="dynamicLableType4" id="dynamicLableType4"
								required="required">
								<option value="text">TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
						</s:else>
						</div>
					</div>
					
					
					
					<div class="form-group">
						<input style="font-weight: bold;"  type="text" autocomplete="off" name="dynamicLable5" class="col-sm-2 control-label" id="dynamicLable5" placeholder="Enter Dynamic Lable" >
						<div class="col-sm-7">
							<!-- <input type="checkbox" id="dynamicLableCheckBox5" name="dynamicLableCheckBox5"
								value="true" autocomplete="off"> -->
						</div>
						<div class="col-sm-3">
						
						<s:if test="$dropdownName4[0]=='text'">
						<select class="form-control input-sm" name="dynamicLableType5" id="dynamicLableType5"
								required="required">
								<option value="text" selected="selected">TextBox</option>
								<option value="select">Select</option>

							</select>
						</s:if>
						<s:else>
						<select class="form-control input-sm" name="dynamicLableType5" id="dynamicLableType5"
								required="required">
								<option value="text">TextBox</option>
								<option value="select" selected="selected">Select</option>

							</select>
						</s:else>
						</div>
					</div>
					</div>
					
					
					<div class="form-group text-center">
						<div class="col-xs-11 submtext-center">
							<button type="button" id="buttonSubmit" class="btn btn-primary btn-lg">Update</button>
						</div>
					</div>
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>


	<script type="text/javascript" src="js/app.js"></script>
	<%@ include file="DashboardFooter.jsp"%>
	
	
	
<script type="text/javascript">

$(document).ready(function() {
	
});

</script>

<script type="text/javascript">

$('.dynamicHideDiv').hide();
function getRmConfig() {
	$parlellDropDownd = new Array("text", "text", "text", "text", "text");
	console.log($parlellDropDownd);
	var value=$('#companyUserId').val();
	if(value>0){
		$('.dynamicHideDiv').show();
	}else{
		$('.dynamicHideDiv').hide();
	}
	$('#dynamicLable1').val("");
	$('#dynamicLable2').val("");
	$('#dynamicLable3').val("");
	$('#dynamicLable4').val("");
	$('#dynamicLable5').val("");
	$('#dynamicLableType1').val("");
	$('#dynamicLableType2').val("");
	$('#dynamicLableType3').val("");
	$('#dynamicLableType4').val("");
	$('#dynamicLableType5').val("");
	
	
	$.ajax({
	    url : "getCompanyDetailById?companyUserId="+value,
	    type : "GET", 
	    dataType: "json",
	    success : function(data) {			    	
	     	//console.log(data);
	     	$rmTotalData=data.rmConfigModel;
	     	//console.log($rmTotalData);
	     	if(data.rmConfigModel.id!=null)
	     		{
	     		$("#rmConfigId").val(data.rmConfigModel.id);
	     		}
	    	if(data.rmConfigModel.empCode){			    	
	    		$("#empCode").prop('checked', true);
	    		$("#empCodeType").val($rmTotalData.empCodeType);
	    		
	    	}
	    	else {
	    		$("#empCode").prop('checked', false);
			}
    	   if(data.rmConfigModel.department){
	    		$("#department").prop('checked', true);
	    		$("#departmentType").val($rmTotalData.departmentType);
	    	}
	    	else {
	    		$("#department").prop('checked', false);
			}
    	   if(data.rmConfigModel.costCenter){
	    		$("#costCenter").prop('checked', true);
	    		$("#costCenterType").val($rmTotalData.costCenterType);
	    	}
	    	else {
	    		$("#costCenter").prop('checked', false);
			}
    	   if(data.rmConfigModel.approverName){
	    		$("#approverName").prop('checked', true);
	    		$("#approverNameType").val($rmTotalData.approverNameType);
	    	}
	    	else {
	    		$("#approverName").prop('checked', false);
			}
    	   if(data.rmConfigModel.location){
    		   $("#locations").prop('checked', true);
    		   $("#locationType").val($rmTotalData.locationType);
	    	}
	    	else {
	    		$("#location").prop('checked', false);
			}
    	   if(data.rmConfigModel.trNumber){
	    		$("#trNumber").prop('checked', true);
	    		 $("#trNumberType").val($rmTotalData.trNumberType);
	    	}
	    	else {
	    		$("#trNumber").prop('checked', false);
			}
    	   if(data.rmConfigModel.bussinessUnit){
	    		$("#bussinessUnit").prop('checked', true);
	    		 $("#bussinessUnitType").val($rmTotalData.bussinessUnitType);
	    	}
	    	else {
	    		$("#bussinessUnit").prop('checked', false);
			}
    	   if(data.rmConfigModel.projectCode){
	    		$("#projectCode").prop('checked', true);
	    		$("#projectCodeType").val($rmTotalData.projectCodeType);
	    	}
	    	else {
	    		$("#projectCode").prop('checked', false);
			}
    	   if(data.rmConfigModel.reasonForTravel){
	    		$("#reasonForTravel").prop('checked', true);
	    		$("#reasonForTravelType").val($rmTotalData.reasonForTravelType);
	    	}
	    	else {
	    		$("#reasonForTravel").prop('checked', false);
			}
    	   if(data.rmConfigModel.billNonBill){
	    		$("#billNonBill").prop('checked', true);
	    		$("#billNonBillType").val($rmTotalData.billNonBillType);
	    	}
	    	else {
	    		$("#billNonBill").prop('checked', false);
	    		
			}
    	   var fields = new Array();
        	var fieldsNew = new Array();
        	$dropDownNew = new Array();
  		  fields = data.rmConfigModel.dynamicFieldsData.split(",");	
  		for (var i in fields) {
  			if(!fields[i].trim().length==""){ 
 			var field = fields[i];			     			
	    		var fieldsName = field.split(":");	
	    		var refinedFieldName=fieldsName[0]
	    		var refinedDropdownName=fieldsName[1]
	    		fieldsNew.push(refinedFieldName);
	    		$dropDownNew.push(refinedDropdownName);
 			}
  		}
  		


  		if(fieldsNew!=null && fieldsNew && fieldsNew.length>0){

	if(fieldsNew[0]){
  		  if(!fieldsNew[0].trim().length==""){
 			var field0 = fieldsNew[0];
	    		var fieldsName0 = field0.split(":");
	    		
	    	var dropDown0 = $dropDownNew[0];
	    	$dropdownName0 = dropDown0.split(":");
	    		
  			$('#dynamicLable1').val(fieldsName0[0]);
  			$('#dynamicLableType1').val($dropdownName0[0]);
  			  		
  		}
  		}else{
  			var dropDown0 = $parlellDropDownd[0];
	    	$dropdownName0 = dropDown0.split(":");
	    	$('#dynamicLableType1').val($dropdownName0[0]);
  		}
	if(fieldsNew[1]){
  		 if(!fieldsNew[1].trim().length==""){
  			var field1 = fieldsNew[1];
 	    		var fieldsName1 = field1.split(":");
 	    		
 	    		var dropDown1 = $dropDownNew[1];
	    		$dropdownName1 = dropDown1.split(":");
	    		
   			$('#dynamicLable2').val(fieldsName1[0]);
   			$('#dynamicLableType2').val($dropdownName1[0]);		
   		}
	}else{
	    	var dropDown1 = $parlellDropDownd[1];
    		$dropdownName1 = dropDown1.split(":");
    		$('#dynamicLableType2').val($dropdownName1[0]);	
  		}
  		  
	if(fieldsNew[2]){ 
  		 if(!fieldsNew[2].trim().length==""){
  			var field2 = fieldsNew[2];
 	    		var fieldsName2 = field2.split(":");
 	    		
 	    		var dropDown2 = $dropDownNew[2];
	    		$dropdownName2 = dropDown2.split(":");
	    		
   			$('#dynamicLable3').val(fieldsName2[0]);
   			$('#dynamicLableType3').val($dropdownName2[0]);		
   		} 
	}
	else{
    	var dropDown2 = $parlellDropDownd[2];
    	$dropdownName2 = dropDown2.split(":");
    	$('#dynamicLableType3').val($dropdownName2[0]);
		}
	if(fieldsNew[3]){
  		 if(!fieldsNew[3].trim().length==""){
  			var field3 = fieldsNew[3];
 	    		var fieldsName3 = field3.split(":");
 	    		
 	    		var dropDown3 = $dropDownNew[3];
	    		$dropdownName3 = dropDown3.split(":");
	    		
   			$('#dynamicLable4').val(fieldsName3[0]);
   			$('#dynamicLableType4').val($dropdownName3[0]); 		
   		} 
	}else{
    	var dropDown3 = $parlellDropDownd[3];
		$dropdownName3 = dropDown3.split(":");
		$('#dynamicLableType4').val($dropdownName3[0]); 
		}
	if(fieldsNew[4]){
  		 if(!fieldsNew[4].trim().length==""){
  			var field4 = fieldsNew[4];
 	    		var fieldsName4 = field4.split(":");
 	    		
 	    		var dropDown4 = $dropDownNew[4];
	    		$dropdownName4 = dropDown4.split(":");
	    		
   			$('#dynamicLable5').val(fieldsName4[0]);
   			$('#dynamicLableType5').val($dropdownName4[0]);		
   		}
	}else{
    	var dropDown4 = $parlellDropDownd[4];
    	$dropdownName4 = dropDown4.split(":");
    	$('#dynamicLableType5').val($dropdownName4[0]);
		}
	
	
  		}else{
  			var dropDown0 = $parlellDropDownd[0];
	    	$dropdownName0 = dropDown0.split(":");
	    	var dropDown1 = $parlellDropDownd[1];
    		$dropdownName1 = dropDown1.split(":");
    		var dropDown2 = $parlellDropDownd[2];
        	$dropdownName2 = dropDown2.split(":");
        	var dropDown3 = $parlellDropDownd[3];
    		$dropdownName3 = dropDown3.split(":");
    		var dropDown4 = $parlellDropDownd[4];
        	$dropdownName4 = dropDown4.split(":");
	    	$('#dynamicLableType1').val($dropdownName0[0]);
	    	$('#dynamicLableType2').val($dropdownName1[0]);	
    		$('#dynamicLableType3').val($dropdownName2[0]);
        	$('#dynamicLableType4').val($dropdownName3[0]); 
    		$('#dynamicLableType5').val($dropdownName4[0]);
  		}	
	    }
	});
}


</script>

<script type="text/javascript">

$( "#buttonSubmit").click(function(){
	 
	var val1=$('#dynamicLable1').val();
	var val2=$('#dynamicLable2').val();
	var val3=$('#dynamicLable3').val();
	var val4=$('#dynamicLable4').val();
	var val5=$('#dynamicLable5').val();
	var dropVal1=$('#dynamicLableType1').val();
	var dropVal2=$('#dynamicLableType2').val();
	var dropVal3=$('#dynamicLableType3').val();
	var dropVal4=$('#dynamicLableType4').val();
	var dropVal5=$('#dynamicLableType5').val();
	var dynamicPush=new Array();
	var dynamicDropDownPush=new Array();
	if(!val1.trim().length==""){
		dynamicPush.push(val1);
		dynamicDropDownPush.push(dropVal1);
	}
	if(!val2.trim().length==""){
		dynamicPush.push(val2);
		dynamicDropDownPush.push(dropVal2);
	}
	if(!val3.trim().length==""){
		dynamicPush.push(val3);
		dynamicDropDownPush.push(dropVal3);
	}
	if(!val4.trim().length==""){
		dynamicPush.push(val4);
		dynamicDropDownPush.push(dropVal4);
	}
	if(!val5.trim().length==""){
		dynamicPush.push(val5);
		dynamicDropDownPush.push(dropVal5);
	}
	$('#dynamicFieldDataSend').val(dynamicPush);
	$('#dynamicFieldDropdownSend').val(dynamicDropDownPush);
	 //$("#bookingConfirmed").submit();
	  $("#bookingConfirmed").valid();
	   if($("#bookingConfirmed").valid()){
		   document.getElementById("bookingConfirmed").submit();
	   }
	
});
$(document).ready(function(){
	   $("#bookingConfirmed").validate({
		   submitHandler: function (form) {  
	            
	            return false;
	        },
	        highlight: function(element, errorClass, validClass) { 
	            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) {
	            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
	         element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          }
	   })
	   });

</script>


</body>

</html>
