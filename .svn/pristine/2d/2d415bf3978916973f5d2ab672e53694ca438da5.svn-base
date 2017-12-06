<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="css/alert.css">
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>
<!-- <link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> -->

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"getSuperUserDealSheet";
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
	<s:if test="%{#parameters.type[0]=='duplicate'}">
		<h1>Duplicate Deal Sheet</h1>
		</s:if>
		 <s:else>
		 <h1>Update Deal Sheet</h1>
		 </s:else>
	</section>
	<!-- Main content -->
	<section class="cont-head">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<div class="clearfix content-box">

				<div class="col-sm-12">
					<div class="form-group" id="user_form-group">
						<s:if test="%{#parameters.type[0]=='duplicate'}">
							<form action="createSuperUserDealSheet" method="post">
							<input type="hidden" name="id" value="<s:property value="dealSheet.id"/>">
									 
							<div class="form-group">
								<input type="text" name="name" class="form-control"
									id="dealSheetName"  value="<s:property value="dealSheet.name"/>"  placeholder="Deal Sheet Name"
									required="required">
							</div>
							<input type="hidden"  id="isStatus" value="<s:property value="dealSheet.Active"/>">
							<div class="form-group">
							 	<select  name="dealStatus" class="form-control" id="dealStatus"  
									required="required">
									<!--  <option value="1" selected="selected">Active</option> -->
									  <option value="0" >InActive</option>
									 </select>
							</div>


							<div class="form-group">
								<textarea rows="" cols=""    name="description"
									class="form-control" placeholder="Description" id="description"
									required="required"><s:property value="dealSheet.description"/> </textarea>
							</div>
							 
							 <button type="submit" class="btn btn-primary">Duplicate
								 </button>
						 </form>
							</s:if>
							<s:else>
						 <form id="updateSuperUserDealSheetForm" action="updateSuperUserDealSheet" method="post">
							<input type="hidden" name="id" value="<s:property value="dealSheet.id"/>">
									 
							<div class="form-group">
								<input type="text" name="name" class="form-control"
									id="dealSheetName"  value="<s:property value="dealSheet.name"/>"  placeholder="Deal Sheet Name"
									required="required">
							</div>
							<input type="hidden"  id="isStatus" value="<s:property value="dealSheet.Active"/>">
							<div class="form-group">
							 	<select  name="dealStatus" class="form-control" id="dealStatus"  
									required="required">
									 <option value="1" selected="selected">Active</option>
									  <option value="0" >InActive</option>
									 </select>
							</div>


							<div class="form-group">
								<textarea rows="" cols=""    name="description"
									class="form-control" placeholder="Description" id="description"
									required="required"><s:property value="dealSheet.description"/> </textarea>
							</div>
							 
							<!--  <button type="submit" class="btn btn-primary">Update
								 </button> -->
								  <button type="button" id="DealSubmit" class="btn btn-primary">Update
								 </button>
						 </form>
						 <script type="text/javascript">
      $(function () {
        var status=$("#isStatus").val();
        
        if(status=='true'){
        	document.getElementById('dealStatus').value ="1";
        }
        else{
        	document.getElementById('dealStatus').value ="0";
        }
        
      });
      
  	$(document).ready(function(){
		  
		   $('#DealSubmit').click(function(){
			   $("#updateSuperUserDealSheetForm").valid();
			   if($("#updateSuperUserDealSheetForm").valid()){
				   document.getElementById("updateSuperUserDealSheetForm").submit();
			   }
		   });
		   $("#updateSuperUserDealSheetForm").validate({
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
							 </s:else>
					 	


					</div>
				</div>
 			</div>
		</div>
	</section>
 
	<!-- Main content -->
 </div>
<!-- content wrapper -->
 


<%@ include file="DashboardFooter.jsp"%>











