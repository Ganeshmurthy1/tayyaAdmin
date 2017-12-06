<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Band</title>

 
<link rel="stylesheet" href="css/alert.css">
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Update Band</h1>
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

				<form id="UpdateBandInfoForm" action="UpdateBandInfo" method="post" class="form-horizontal"
					name="myForm" id="formSubmit">
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
					<input type="hidden" value='<s:property value="employeeBandModel.bandId"/>'  name="employeeBandModel.bandId" id="currentBandId">
					
					<div class="form-group">
						<label for="newBandName" class="col-sm-2 control-label">Band Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value='<s:property value="employeeBandModel.bandName"/>'
								required id="newBandName" name="employeeBandModel.bandName"
								placeholder="Enter new band name">
						</div>
					</div>
					<div class="form-group">
						<label for="newBandCode" class="col-sm-2 control-label">Band Code</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value='<s:property value="employeeBandModel.bandCode"/>'
								required id="newBandCode" name="employeeBandModel.bandCode"
								placeholder="Enter new band code">
						</div>
					</div>
					<div class="col-sm-12" id="hiddenDiv"></div>

				</form>


				<div class=" text-center">
					<div class="col-xs-12 submitWrap text-center">
						<!-- <button type="button" id="addBandButton"
							onclick="checkNameExistingOrSave(document.getElementById('newBandName').value,document.getElementById('newBandCode').value);"
							class="btn btn-primary btn-lg">Update Band</button> -->
							<button type="button" id="addBandButton"							
							class="btn btn-primary btn-lg">Update Band</button>
					</div>
				</div>
				<!-- </form> -->
			</div>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>

	<script>
	
	
	$(document).ready(function(){
		  
		   $('#addBandButton').click(function(){
			   $("#UpdateBandInfoForm").valid();
			   if($("#UpdateBandInfoForm").valid()){
				   var bandName=document.getElementById('newBandName').value;
				   var bandCode= document.getElementById('newBandCode').value;
				  
				   checkNameExistingOrSave(bandName,bandCode);
				  // document.getElementById("UpdateDealForm").submit();
			   }
		   });
		   $("#UpdateBandInfoForm").validate({
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
		   
		   
		   
		   function checkNameExistingOrSave(bandName, bandCode) {
			   
		  
			   console.log("bandName",bandName);
			   console.log("bandCode",bandCode);
	 
			var boolName = true;
			var boolCode = true;
			var currentBandId=document.getElementById('currentBandId').value;
			
			<c:forEach items="${bandModelList}" var="item" varStatus="status">
			if ("${item.bandName}".toLowerCase() === bandName.toLowerCase()  && !("${item.bandId}" == currentBandId)) {
					document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Band Name Already Existed !   Please try another ...</i></span>';
					boolName = false;
			}
			if ("${item.bandCode}".toLowerCase() === bandCode.toLowerCase()  && !("${item.bandId}" == currentBandId)) {
				document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Band Code Already Existed !   Please try another ...</i></span>';
				boolCode = false;
			}
			</c:forEach>
			if (boolName && boolCode) {
				document.getElementById("UpdateBandInfoForm").submit();
			}
		}
		   });
	
		
	</script>

</body>

</html>