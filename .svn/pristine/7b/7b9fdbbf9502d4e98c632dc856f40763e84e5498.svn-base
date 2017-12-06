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
   <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"> </script> --%> --%>
  <link rel="stylesheet" href="css/alert.css">
      <link rel="stylesheet" href="css/fastselect.min.css">
 <script src="js/bootstrap-select.js"></script> 
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
   <style>

            .fstElement { font-size: 0.9em; }
            .fstToggleBtn { min-width: 16.5em; }
            .submitBtn { display: none; }
            .fstMultipleMode { display: block; }
            .fstMultipleMode .fstControls { width: 100%; }

        </style>
  <link rel="stylesheet" href="css/bootstrap-select.css">
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"markupList";
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
   <script type="text/javascript">
   
   $(function() {
		var id=$("#uniqueId").val();
		 
		var accumulative=$("#accumulative").val();
		var fixedAmount=$("#fixedAmount").val();
		var positionOfMarkup=$("#positionOfMarkup").val();
		var airline=$("#airline").val();
		var country=$("#country").val();
		var classOfService=$("#classOfService").val();
		var destType=$("#destType1").val();
		 document.getElementById('des'+id).value =destType;
	  	document.getElementById('accumulative'+id).value =accumulative;
		 document.getElementById('fixedAmount'+id).value =fixedAmount;
	/* 	 document.getElementById('positionOfMarkup'+id).value =positionOfMarkup; */
		 document.getElementById('airline'+id).value =airline;
		 document.getElementById('country'+id).value =country;
		 document.getElementById('classOfService'+id).value =classOfService;
	 }); 
 </script> 
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body >
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper" >
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Update Flight Markup <%-- <small>Control panel</small> --%>
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
				 
				<form action="updateMarkupData" method="post" class="form-horizontal" id="companyMarkup">
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
									name="name" placeholder="markup Name" autocomplete="off"
									  value="<s:property value="CurrentMarkupProfile.name"/>"
									 required>
							</div>
						</div>
						
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">is
								Accumulative</label>
							<div class="col-sm-8">
							<input type="hidden" value="<s:property value="CurrentMarkupProfile.markupId"/>" id="uniqueId">
						<input type="hidden" value="<s:property value="CurrentMarkupProfile.accumulative"/>" id="accumulative">
					 	<select class="form-control input-sm" name="accumulative" 
								 id="accumulative<s:property value="CurrentMarkupProfile.markupId"/>"
									  autocomplete="off" required>
									   <option value="true">YES</option>
									<option value="false" selected="selected">NO</option>
									 
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
							<label for="last-name" class="col-sm-2 control-label">MarkUp</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="last-name"
									name="markup" 
									value="<s:property value="CurrentMarkupProfile.markup"/>"
									
									placeholder="amount" autocomplete="off" required>
							</div>
						</div>
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Position of MarkUp</label>
							<div class="col-sm-8">
								<input type="hidden" value="<s:property value="CurrentMarkupProfile.positionOfMarkup"/>" id="positionOfMarkup">
								  <input type="number"   class="form-control input-sm" name="positionOfMarkup"
									value="<s:property value="CurrentMarkupProfile.positionOfMarkup"/>"  autocomplete="off" required>

									 
							</div>
						</div>
						
							<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Airline</label>
							<div class="col-sm-8">
							<input type="hidden" value="<s:property value="CurrentMarkupProfile.airline"/>" id="airline">
							
								<select class="form-control input-sm selectpicker"  name="airline" 
									id="airline<s:property value="CurrentMarkupProfile.markupId"/>"    required data-live-search="true">
 										<option value="ALL" selected="selected">ALL</option>
									<s:iterator value="allAirlineList">
									<option value="<s:property value="airlinecode"/>"><s:property
											value="airlinename"></s:property></option>
											 
								</s:iterator>
									 
								</select>
							</div>
						</div>
						<div class="form-group">
						<input type="hidden" value="<s:property value="CurrentMarkupProfile.destinationType"/>" id="destType1">
						<label for="City" class="col-sm-2 control-label">Destination Type</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="des<s:property value="CurrentMarkupProfile.markupId"/>" name="destinationType">
								<option   value="ALL">ALL</option>
								<option  value="Domestic">Domestic</option>
								<option  value="International">International</option>
								 

							</select>
						</div>
					</div>
						
						
						
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Fare Basis Code</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="last-name"
									name="fareBaseCode"   value='<s:property value="CurrentMarkupProfile.fareBaseCode"/>' placeholder="Fare Basis Code" autocomplete="off" > 
							</div>
						</div>
						<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Departure
							Date</label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_dep"
								class="form-control input-sm" name="deptDate" autocomplete="off"
								placeholder="ALL"   value="<s:property value="CurrentMarkupProfile.deptDate"/>">
						</div>
					</div>
						 <div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Arrival
							Date</label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_arr"
								class="form-control input-sm" name="arrvDate" autocomplete="off"
								placeholder="ALL"   value="<s:property value="CurrentMarkupProfile.arrvDate"/>"   >
						</div>
					</div>
					
				<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Promofare StartDate
							 </label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_PromofareStart"
								class="form-control input-sm" name="promofareStartDate"  value="<s:property value="CurrentMarkupProfile.promofareStartDate"/>"  autocomplete="off"
								placeholder="ALL" >
						</div>
					</div>
						<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Promofare EndDate
							 </label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_PromofareEnd"
								class="form-control input-sm" name="promofareEndDate" value="<s:property value="CurrentMarkupProfile.promofareEndDate"/>"   autocomplete="off"
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
							<label for="Country" class="col-sm-2 control-label">ClassOfService</label>
							<div class="col-sm-8">
							<input type="hidden" value="<s:property value="CurrentMarkupProfile.classOfService"/>" id="classOfService">
							
								<select class="form-control input-sm" name="classOfService"
									id="classOfService<s:property value="CurrentMarkupProfile.markupId"/>" autocomplete="off" required>
									<option selected="selected" value="ALL">ALL</option>
									<option value="Economy">Economy</option>
									<option value="Premium">Premium Economy</option>
									<option value="Business">Business</option>
										<option value="First">First</option>
									
								</select>
							</div>
						</div>
						<div class="form-group text-center">
							<div class="col-xs-12 submitWrap text-center">
								<button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Update Changes
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
	
	
	<script src ="js/fastselect.standalone.js"> </script>
    <script>
 	$('.multipleInputDynamic').fastselect();

            </script>
          <script>
	
	
	
	
	$(document).ready(function(){ 
		 
	    $("#companyMarkup").validate({
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
	            },
	        }, 
	        highlight: function(element, errorClass, validClass) { 
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) { 
	         element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          },
	        submitHandler: function (form) {   
	            return false;  
	        } 
	    });
	    
	    $('#buttonSubmit').click(function() { 
	  	if($("#companyMarkup").valid())  
	    	 document.getElementById("companyMarkup").submit();
	  /* 	else
	  		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
	    });    
	    
	});

	</script> 
 
</body>

</html>