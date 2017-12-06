<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"commissionBlockList";
	$('#success').click(function() {
	  window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		  var finalUrl = newUrl+"addChildCommissionBlock";
		  window.location.assign(finalUrl);
		   $('#error-alert').hide();
			
		});  
 });
 
 
 </script>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
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
	<section class="content-header">
		<h1>Create Child Block</h1>
	</section>
	<!-- Main content -->
	<section class="cont-head">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<div class="clearfix content-box">

				<div class="col-sm-12">
					<div class="form-group" id="user_form-group">
						<form id="createChildCommissionBlockForm" action="createChildCommissionBlock" method="post">

							<div class="form-group">
								<input type="text" name="name" class="form-control"
									id="BlockName" placeholder="Block Name" required="required">
							</div>
							<%-- 
							<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
							<div class="form-group">Select Deal Sheet
							 	<select  name="appliedSheetId" class="form-control" >
							 	 <s:iterator value="sheetList">
							 	 <option value='<s:property value="id"/>'> <s:property value="name"/> </option>
							 	  </s:iterator>
							 	 </select>
							</div>
							 </s:if> --%>
							<div class="form-group">
								<select name="dealStatus" class="form-control"
									id="dealSheetName" required="required">
									<option value="1" selected="selected">Active</option>
									<option value="0">InActive</option>
								</select>
							</div>


							<div class="form-group">
								<textarea rows="" cols="" name="description"
									class="form-control" placeholder="Description" id="description"
									required="required"></textarea>
							</div>
							<button id="SubmitBlock" type="button" class="btn btn-primary">Create
								Block</button>

						</form>


					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Main content -->
</div>
<!-- content wrapper -->



<%@ include file="DashboardFooter.jsp"%>
<script src="js/jquery.js"></script> 
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script> 
<script>
$(document).ready(function(){
	  
	   $('#SubmitBlock').click(function(){
		   $("#createChildCommissionBlockForm").valid();
		   if($("#createChildCommissionBlockForm").valid()){
			   document.getElementById("createChildCommissionBlockForm").submit();
		   }
	   });
	   $("#createChildCommissionBlockForm").validate({
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


