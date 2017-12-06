<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="css/alert.css">
 
 <style type="text/css">
.error {
    color:red;
}
.valid {
    color:green;
}
</style>
 
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
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
		<h1>Create Deal Sheet</h1>
	</section>
	<!-- Main content -->
	<section class="cont-head">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<div class="clearfix content-box">

				<div class="col-sm-12">
					<div class="form-group" id="user_form-group">
						<form action="createSuperUserDealSheet" method="post" id="myform">
						 <div class="form-group">
								<input type="text" name="name" class="form-control"
									id="dealSheetName" autocomplete="off"     placeholder="Deal Sheet Name"
									required="required">
							</div>
						
							<div class="form-group">
								<select  name="dealStatus" class="form-control" id="dealSheetName"  
									required="required">
									  <option value="0" >InActive</option>
									   </select>
							</div>
							 

						


							<div class="form-group">
								<textarea rows="" cols="" name="description"
									class="form-control" placeholder="Description" id="description"
									required="required"></textarea>
							</div>
							 <button type="button" class="btn btn-primary" id="buttonSubmit">Create
								Sheet</button>

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


$("#myform").validate({
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
         }
     },
    submitHandler: function (form) { 
        return false; 
    }
});

$('#buttonSubmit').click(function() {
	   if($("#myform").valid())
	    	document.getElementById("myform").submit();
	 	 
	}); 

});


</script>  








