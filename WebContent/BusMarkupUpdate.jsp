<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update MarkUp</title>
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<%-- <%-- <link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>  
   <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>  
   <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"> </script> --%> 
  <link rel="stylesheet" href="css/alert.css">
  <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
      <link rel="stylesheet" href="css/fastselect.min.css">

   <style>

            .fstElement { font-size: 0.9em; }
            .fstToggleBtn { min-width: 16.5em; }
            .submitBtn { display: none; }
            .fstMultipleMode { display: block; }
            .fstMultipleMode .fstControls { width: 100%; }

			.error {
			    color:red;
			}
			.valid {
			    color:green;
			}
        </style>
  <link rel="stylesheet" href="css/bootstrap-select.css">
  
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"busMarkupList";
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
   
   $(function(){
		var id=$("#uniqueId").val(); 
		var accumulative=$("#accumulative").val();
		
		var fixedAmount=$("#fixedAmount").val();
		console.log("fixedAmount",fixedAmount);
		var positionOfMarkup=$("#positionOfMarkup").val(); 
		var busType=$("#busType").val();  
	  	document.getElementById('accumulative'+id).value =accumulative;
	  //	$("#accumulative"+id).val(accumulative);
	  	//console.log("accumulative",$("#accumulative"+id).val(accumulative));
	  	//alert("sdfg"+document.getElementById('accumulative'+id).value);
		document.getElementById('fixedAmount'+id).value =fixedAmount;
 	 	document.getElementById('positionOfMarkup'+id).value =positionOfMarkup;   
		document.getElementById('busType'+id).value= busType;
	 }); 
 </script> 
 <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_arr").datepicker({
		 dateFormat: "dd-mm-yy"  
		/*  changeMonth: true,
		 changeYear: true */
	 }); 
	 });
	 
 </script> 
     <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_dep").datepicker({
		 dateFormat: "dd-mm-yy"
	 });
	 }); 
 </script>
  <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_PromofareStart").datepicker({
		 dateFormat: "dd-mm-yy"
	 });
	 }); 
 </script>
   <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_PromofareEnd").datepicker({
		 dateFormat: "dd-mm-yy"
	 });
	 }); 
 </script>

</head>
<body >
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper" >
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Update Bus Markup <%-- <small>Control panel</small> --%>
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
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
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
				 
				<form action="updateBusMarkupData" method="post" class="form-horizontal" id="formSubmit">
				<input type="hidden" name="markupId" value="<s:property value="CurrentMarkupProfile.markupId"/>">
						
				  <div class="form-group">
						<label for="City" class="col-sm-2 control-label">Config Number
							 </label>
						<div class="col-sm-8">
						<input type="text" class="form-control input-sm" 
									name="config_number" placeholder="config number" autocomplete="off"
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
							 <input type="hidden" value="<s:property value="CurrentMarkupProfile.markupId"/>" id="uniqueId">
						<input type="hidden" value="<s:property value="CurrentMarkupProfile.isAccumulative"/>" id="accumulative">
					 	<select class="form-control input-sm" name="isAccumulative" 
								 id="accumulative<s:property value="CurrentMarkupProfile.markupId"/>"
									  autocomplete="off" required>
									   <option value="true">YES</option>
									<option value="false" >NO</option>
									 
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">is
								FixedAmount</label>
							<div class="col-sm-8">
							<input type="hidden" value="<s:property value="CurrentMarkupProfile.fixedAmount"/>" id="fixedAmount">
								<select class="form-control input-sm" name="fixedAmount" 
								id="fixedAmount<s:property value="CurrentMarkupProfile.markupId"/>"
									  autocomplete="off" required>
 									<option value="true">YES</option>
									<option value="false">NO</option> 
								</select>
							</div>
						</div>
						
												 
						<div class="form-group">
							<label for="isMarkUpPerPassenger" class="col-sm-2 control-label" >isMarkUpPerPassenger</label> 
							<div class="col-sm-8">
								<select class="form-control input-sm" name="isMarkUpPerPassenger"
									id="isMarkUpPerPassenger" autocomplete="off" required>
									<option selected="selected" value="No">No</option> 
									<option  value="Yes">Yes</option>
									 
								</select>
							</div>
							</div>
						 
						<div class="form-group">
							<label for="isMarkUpOnTotal"  class="col-sm-2 control-label" >isMarkUpOnTotal</label> 
							<div class="col-sm-8">
								<select class="form-control input-sm" name="isMarkUpOnTotal"
									id="isMarkUpOnTotal" autocomplete="off" required>
									<option selected="selected" value="No">No</option> 
									<option  value="Yes">Yes</option>
									
								</select>
								</div>
							</div>
						 
						<div class="form-group">
							<label for="last-name" class="col-sm-2 control-label">MarkUp Amount</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm required cusValidationforprice" 
									name="markupAmount" 
									value="<s:property value="CurrentMarkupProfile.markupAmount"/>"
									
									placeholder="Markup Amount" autocomplete="off" required>
							</div>
						</div>
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Position of MarkUp</label>
							<div class="col-sm-8">
								<input type="hidden" value="<s:property value="CurrentMarkupProfile.positionOfMarkup"/>" id="positionOfMarkup">
								  <input type="number"   class="form-control input-sm" name="positionOfMarkup" id="positionOfMarkup<s:property value="CurrentMarkupProfile.markupId"/>"
									value="<s:property value="CurrentMarkupProfile.positionOfMarkup"/>"  autocomplete="off" required>

									 
							</div>
						</div>  
						<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Departure
							Date</label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_dep" required
								class="form-control input-sm" name="depDate" 
								 value="<s:property value="CurrentMarkupProfile.depDate"/>" autocomplete="off">
						</div>
					</div>
						 <div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Arrival
							Date</label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_arr" required
								class="form-control input-sm" name="arrDate" autocomplete="off"
								   value="<s:property value="CurrentMarkupProfile.arrDate"/>"   >
						</div>
					</div>
					
				<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Promofare StartDate
							 </label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_PromofareStart" required
								class="form-control input-sm" name="promofareStartDate"  value="<s:property value="CurrentMarkupProfile.promofareStartDate"/>"  autocomplete="off"
								placeholder="ALL" >
						</div>
					</div>
						<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Promofare EndDate
							 </label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_PromofareEnd" 
								class="form-control input-sm" name="promofareEndDate" value="<s:property value="CurrentMarkupProfile.promofareEndDate"/>" autocomplete="off"
								placeholder="ALL" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="last-name"    class="col-sm-2 control-label">Origin</label>
						<div class="col-sm-8">
							<input type="text" id="origin" multiple class="form-control input-sm multipleInputDynamic"  
								name="origin" value="<s:property value="CurrentMarkupProfile.origin"/>"								
								autocomplete="off" placeholder="ALL"  data-url="markupairportlist.json">
						</div>
					</div>
					<div class="form-group" >
						<label for="last-name" class="col-sm-2 control-label">Destination</label>
						<div class="col-sm-8">
							<input type="text" multiple id="dest" class="form-control input-sm multipleInputDynamic" 
								name="destination" autocomplete="off"
								value="<s:property value="CurrentMarkupProfile.destination"/>"
								 placeholder="ALL"
							 data-url="markupairportlist.json" >
						</div>
					</div>
				<%-- 	<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Country </label>
						<div class="col-sm-8">
							<input type="hidden" value="<s:property value="CurrentMarkupProfile.country"/>" id="country">
							
							<select class="form-control input-sm" name="country" 
							id="country<s:property value="CurrentMarkupProfile.markupId"/>"
								autocomplete="off" required>
								<option selected value="ALL">ALL</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="c_name"/>"><s:property
											value="c_name"></s:property></option>
								</s:iterator>

							</select>
						</div>
					</div> --%>
					
				 
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">BusTypes</label>
							<div class="col-sm-8"> 
							<input type="hidden" value="<s:property value="CurrentMarkupProfile.busTypes"/>" id="busType">
							
								<select class="form-control input-sm" name="busTypes"
									id="busType<s:property value="CurrentMarkupProfile.markupId"/>" autocomplete="off" required>
									
									<option value="ALL">ALL</option>
									<option value="Ac-Sleeper">Ac Sleeper</option>
									<option value="Non-Ac Sleeper">Non-Ac Sleeper</option>
									<option value="Ac-Seater">Ac-Seater</option>
									<option value="Non-Ac Seater">Non-Ac Seater</option>
									<option value="Cab">Cab</option> 
								</select> 
								
							</div>
						</div>
						<div class="form-group" >
						<label for="last-name" class="col-sm-2 control-label">Bus Operators</label>
						<div class="col-sm-8">
							<input type="text" multiple id="busOperators" class="form-control input-sm  " 
								name="busOperators" autocomplete="off" required
								value="<s:property value="CurrentMarkupProfile.busOperators"/>"
								 placeholder="ALL" >
						</div>
					</div>
						
						<div class="form-group text-center">
							<div class="col-xs-12 submitWrap text-center">
								<button type="button" class="btn btn-primary btn-lg" id="updateButton">Update Changes
								 </button>
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


$("#formSubmit").validate({
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
});


$("#formSubmit").valid();
$('#updateButton').click(function() {
	$("#formSubmit").valid();
	   if($("#formSubmit").valid())
	    	document.getElementById("formSubmit").submit();
	 	 
	}); 

});


</script>   
           
	
	<script src ="js/fastselect.standalone.js"> </script>
 
    <script>
 	$('.multipleInputDynamic').fastselect();

            </script>
            
   
 
</body>

</html>