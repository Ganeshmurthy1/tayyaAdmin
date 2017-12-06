<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Insurance MarkUp</title>
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<%-- <%-- <link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>  
   <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>  
   <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"> </script> --%>
--%>
<link rel="stylesheet" href="css/alert.css">
<link rel="stylesheet" href="css/fastselect.min.css">

<style>
.fstElement {
	font-size: 0.9em;
}

.fstToggleBtn {
	min-width: 16.5em;
}

.submitBtn {
	display: none;
}

.fstMultipleMode {
	display: block;
}

.fstMultipleMode .fstControls {
	width: 100%;
}
</style>
<link rel="stylesheet" href="css/bootstrap-select.css">
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "insuranceMarkupList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>

<script>
	$(function() {
		var id = $("#uniqueId").val();
		var accumulative = $("#accumulative").val();
		/* alert(accumulative); */
		var fixedAmount = $("#fixedAmount").val();
		var positionOfMarkup = $("#positionOfMarkup").val();
		var type = $("#type").val();
		document.getElementById('accumulative' + id).value = accumulative;
		alert("sdfg" + document.getElementById('accumulative' + id).value);
		document.getElementById('fixedAmount' + id).value = fixedAmount;
		document.getElementById('positionOfMarkup' + id).value = positionOfMarkup;
		document.getElementById('type' + id).value = type;
	});
</script>
<script>
	$(document).ready(function() {
		$("#datepicker_arr").datepicker({
			dateFormat : "dd-mm-yy"
		/*  changeMonth: true,
		 changeYear: true */
		});
	});
</script>
<script>
	$(document).ready(function() {
		$("#datepicker_dep").datepicker({
			dateFormat : "dd-mm-yy"
		});
	});
</script>
<script>
	$(document).ready(function() {
		$("#datepicker_PromofareStart").datepicker({
			dateFormat : "dd-mm-yy"
		});
	});
</script>
<script>
	$(document).ready(function() {
		$("#datepicker_PromofareEnd").datepicker({
			dateFormat : "dd-mm-yy"
		});
	});
</script>
<script src="js/jquery.js"></script> 
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Update Insurance Markup
				<%-- <small>Control panel</small> --%>
			</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
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

				<form id="updateInsuranceMarkupForm" action="updateInsuranceMarkupData" method="post"
					class="form-horizontal">
					<input type="hidden" name="markupId"
						value="<s:property value="CurrentMarkupProfile.markupId"/>">

					<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Config
							Number </label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm"
								name="config_number" placeholder="config number"
								autocomplete="off"
								value="<s:property value="CurrentMarkupProfile.config_number"/>  - <s:property value="CurrentMarkupProfile.configname"/> (<s:property value="CurrentMarkupProfile.companyName"/>-<s:property value="CurrentMarkupProfile.companyUserId"/>)"
								disabled="disabled">
						</div>
					</div>


					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">MarkUp
							Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="username"
								name="markupName" placeholder="markup Name" autocomplete="off"
								value="<s:property value="CurrentMarkupProfile.markupName"/>"
								required>
						</div>
					</div>

					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">is
							Accumulative</label>
						<div class="col-sm-8">
							<%-- <input type="hidden" value="<s:property value="CurrentMarkupProfile.markupId"/>" id="uniqueId"> --%>
							<input type="hidden"
								value="<s:property value="CurrentMarkupProfile.isAccumulative"/>"
								id="accumulative"> 
								<select class="form-control input-sm" name="accumulative" required="required">
								<c:choose>
									<c:when test="${CurrentMarkupProfile.accumulative}">
										<option value="true" selected="selected">Yes</option>
										<option value="false">No</option>
									</c:when>
									<c:otherwise>
										<option value="true">YES</option>
										<option value="false" selected="selected">No</option>
									</c:otherwise>
								</c:choose>

							</select>
								
								
						</div>
					</div>
					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Type</label>
						<div class="col-sm-8">
							<%-- <input type="hidden" value="<s:property value="CurrentMarkupProfile.markupId"/>" id="uniqueId"> --%>
							<input type="hidden"
								value="<s:property value="CurrentMarkupProfile.type"/>"
								id="type" name="type"> 
							<select class="form-control input-sm" name="active" required="required">
								<c:choose>
									<c:when test="${CurrentMarkupProfile.type eq 'domestic'}">
										<option value="domestic" selected="selected">Domestic</option>
										<option value="international">International</option>
									</c:when>
									<c:otherwise>
										<option value="domestic">Domestic</option>
										<option value="international" selected="selected">International</option>
									</c:otherwise>
								</c:choose>

							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">is
							FixedAmount</label>
						<div class="col-sm-8">
							<input type="hidden" value="<s:property value="CurrentMarkupProfile.fixedAmount"/>"
								id="fixedAmount"> 
								<select class="form-control input-sm" name="fixedAmount" required="required">
								<c:choose>
									<c:when test="${CurrentMarkupProfile.fixedAmount}">
										<option value="true" selected="selected">Yes</option>
										<option value="false">No</option>
									</c:when>
									<c:otherwise>
										<option value="true">YES</option>
										<option value="false" selected="selected">No</option>
									</c:otherwise>
								</c:choose>

							</select>
								
								<%-- <select class="form-control input-sm" name="fixedAmount" id="fixedAmount value="<s:property value="CurrentMarkupProfile.fixedAmount"/>"
								autocomplete="off" required>
								<option value="true">YES</option>
								<option value="false">NO</option>
							</select> --%>
						</div>
					</div>


					<div class="form-group">
						<label for="markUpPerPassenger" class="col-sm-2 control-label">MarkUp Per Passenger</label>
						<div class="col-sm-8">
						<select class="form-control input-sm" name="markUpPerPassenger" required="required">
								<c:choose>
									<c:when test="${CurrentMarkupProfile.markUpPerPassenger}">
										<option value="true" selected="selected">Yes</option>
										<option value="false">No</option>
									</c:when>
									<c:otherwise>
										<option value="true">YES</option>
										<option value="false" selected="selected">No</option>
									</c:otherwise>
								</c:choose>

							</select>
						
						<%-- 
						
							<select class="form-control input-sm" name="markUpPerPassenger"
								id="markUpPerPassenger" autocomplete="off" required>
								<option selected="selected" value="false">No</option>
								<option value="true">Yes</option>

							</select> --%>
						</div>
					</div>

					<div class="form-group">
						<label for="markUpOnTotal" class="col-sm-2 control-label">MarkUp On Total</label>
						<div class="col-sm-8">
						
						<select class="form-control input-sm" name="markUpOnTotal" required="required">
								<c:choose>
									<c:when test="${CurrentMarkupProfile.markUpOnTotal}">
										<option value="true" selected="selected">Yes</option>
										<option value="false">No</option>
									</c:when>
									<c:otherwise>
										<option value="true">YES</option>
										<option value="false" selected="selected">No</option>
									</c:otherwise>
								</c:choose>

							</select>
							
							<%-- <select class="form-control input-sm" name="markUpOnTotal"
								id="markUpOnTotal" autocomplete="off" required>
								<option selected="selected" value="false">No</option>
								<option value="true">Yes</option>

							</select> --%>
						</div>
					</div>

					<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">MarkUp
							Amount</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="last-name"
								name="markupAmount"
								value="<s:property value="CurrentMarkupProfile.markupAmount"/>"
								placeholder="Markup Amount" autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Position
							of MarkUp</label>
						<div class="col-sm-8">
							<input type="hidden"
								value="<s:property value="CurrentMarkupProfile.positionOfMarkup"/>"
								id="positionOfMarkup"> <input type="number"
								class="form-control input-sm" name="positionOfMarkup"
								value="<s:property value="CurrentMarkupProfile.positionOfMarkup"/>"
								autocomplete="off" required>


						</div>
					</div>


					<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Promofare
							StartDate </label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_PromofareStart"
								class="form-control input-sm" name="promofareStartDate"
								value="<s:property value="CurrentMarkupProfile.promofareStartDate"/>"
								autocomplete="off" placeholder="ALL">
						</div>
					</div>
					<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Promofare
							EndDate </label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_PromofareEnd"
								class="form-control input-sm" name="promofareEndDate"
								value="<s:property value="CurrentMarkupProfile.promofareEndDate"/>"
								autocomplete="off" placeholder="ALL">
						</div>
					</div>


					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<!-- <button type="submit" class="btn btn-primary btn-lg">Update
								Changes</button> -->
								<button id="updateSubmit" type="button" class="btn btn-primary btn-lg">Update
								Changes</button>
						</div>
					</div>
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%-- <script type="text/javascript" src="js/app.js"></script>  --%>
	<%--   --%>
	<%@ include file="DashboardFooter.jsp"%>
	<%--  <%@ include file="DashFooter.jsp" %>  --%>
	<%--  <script src=js/listcomplete.js> </script> --%>


	<script src="js/fastselect.standalone.js">
		
	</script>

	<script>
		$('.multipleInputDynamic').fastselect();
		</script>
	<script>
		$(document).ready(function(){
			  
			   $('#updateSubmit').click(function(){
				   $("#updateInsuranceMarkupForm").valid();
				   if($("#updateInsuranceMarkupForm").valid()){
					   document.getElementById("updateInsuranceMarkupForm").submit();
				   }
			   });
			 		   
			   $("#updateInsuranceMarkupForm").validate({
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