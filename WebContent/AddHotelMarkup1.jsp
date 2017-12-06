<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Hotel MarkUp</title>
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->
<link rel="stylesheet" href="css/alert.css">
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<script type="text/javascript">
 	 $(function() {
		 loadstations();
	 });
 	function loadstations() {
		  $.getJSON("hotels.json",{
				format : "json"
			}).done(
					 function(jsonbusdata) {
					 
						  citylist = [];
						  names = [];
						 
						 $.each(jsonbusdata, function(i, station) {
							citylist.push(station.chain);
							 
						 });
						 $("#hotelChain").autocomplete({
					        source: citylist,
					         
					    });
						 
						 $('#hotelChain').on('autocompleteselect', function (e, ui) {
							    	 
			 			}); 
					    $.ui.autocomplete.filter = function (array, term) {
					        var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
					        return $.grep(array, function (value) {
					            return matcher.test(value.label || value.value || value);
					        });
					    };
					 });
 	}
 	
 	 
 	</script>


<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"hotelMarkupList";
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
		 dateFormat: "dd-mm-yy",
		 minDate: 0,
		/*  changeMonth: true,
		 changeYear: true */
		 onSelect : function(selectedDate) {
			 $(this).valid();
			 var d1 = $(this).datepicker("getDate");
			$("#datepicker_dep").datepicker("option", "minDate", d1);
			
		},
	 }); 
	 });
	 
 </script>
<script>
 $(document).ready(function() 
 { 
	 $("#datepicker_dep").datepicker({
		 dateFormat: "dd-mm-yy",
		 //minDate: 0
		 onSelect : function(selectedDate) {
			 $(this).valid();
		},
	 });
	 });
 </script>
<script>
 $(document).ready(function() 
 { 
	 $("#datepicker_PromofareStart").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate: 0,
		 onSelect : function(selectedDate) {
			 $(this).valid();
			 var d1 = $(this).datepicker("getDate");
			$("#datepicker_PromofareEnd").datepicker("option", "minDate", d1);
			
		},
	 });
	 }); 
 </script>
<script>
 $(document).ready(function() 
 { 
	 $("#datepicker_PromofareEnd").datepicker({
		 dateFormat: "dd-mm-yy",
		 //minDate: 0
		 onSelect : function(selectedDate) {
			 $(this).valid();
		},
		 
		 
	 });
	 }); 
 </script>
 <style type="text/css">
 .error {
    color:red;
}
.valid {
    color:green;
}
 
 </style>
</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Add Hotel Markup
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
			<div class="clearfix">
				<form action="markup" method="post" class="form-horizontal" id="myfform">
<div class="col-sm-3">	
					<div class="form-group">
						<label for="City"  >Config Number </label>
						 
							<select class="form-control input-sm" name="configData"
								id="configData" autocomplete="off" required>
								<option selected value="">Select Company ConfigNumber</option>
								<s:if test="%{#session.Company.companyRole.isDistributor()}">
									<s:iterator value="AgencyConfiglist">
										<option
											value="<s:property value="company_id"/>/<s:property value="config_id"/>/<s:property value="configname"/>/<s:property value="config_number"/>/<s:property value="companyName"/>"><s:property
												value="config_number" /> -
											<s:property value="configname" /> (
											<s:property value="companyName" />-
											<s:property value="companyUserId" />)
										</option>
									</s:iterator>
									<s:iterator value="companyConfigIdsList">
										<option
											value="<s:property value="company_id"/>/<s:property value="config_id"/>/<s:property value="configname"/>/<s:property value="config_number"/>/<s:property value="companyName"/>"><s:property
												value="config_number" /> -
											<s:property value="configname" /> (
											<s:property value="companyName" />-
											<s:property value="companyUserId" />)
										</option>
									</s:iterator>
								</s:if>
								<s:elseif test="%{#session.Company.companyRole.isAgent()}">
									<s:iterator value="AgencyConfiglist">
										<option
											value="<s:property value="company_id"/>/<s:property value="config_id"/>/<s:property value="configname"/>/<s:property value="config_number"/>/<s:property value="companyName"/>"><s:property
												value="config_number" /> -
											<s:property value="configname" /> (
											<s:property value="companyName" />-
											<s:property value="companyUserId" />)
										</option>
									</s:iterator>
								</s:elseif>

								<s:else>
									<s:iterator value="companyConfigIdsList">
										<option
											value="<s:property value="company_id"/>/<s:property value="config_id"/>/<s:property value="configname"/>/<s:property value="config_number"/>/<s:property value="companyName"/>"><s:property
												value="config_number" /> -
											<s:property value="configname" /> (
											<s:property value="companyName" />-
											<s:property value="companyUserId" />)
										</option>
									</s:iterator>
								</s:else>


							</select>
						</div>
					</div>

<div class="col-sm-3">	
					<div class="form-group">
						<label for="Username"  >MarkUp
							Name</label>
					 
							<input type="text" class="form-control input-sm" id="name"
								name="name" placeholder="markup Name" autocomplete="off"
								required>
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="City"  >Destination
							Type</label>
						 
							<select class="form-control input-sm" name="destinationType"
								id="destinationType" autocomplete="off" required>
								<option selected value="ALL">ALL</option>
								<option value="Domestic">Domestic</option>
								<option value="International">International</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="Country">Group</label>
						 
							<input type="text" class="form-control input-sm" id="hotelChain"
								name="hotelChain" placeholder="Hotel Group" required value="ALL">
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="Username"  >Hotel
							Name</label>
						 
							<input type="text" class="form-control input-sm" id="hotelName"
								name="hotelName" placeholder="hotel Name" autocomplete="off"
								required value="ALL">
						</div>
					</div>
<div class="col-sm-3">	
					<div class="form-group">
						<label for="Username"  >City</label>
						 
							<input type="text" class="form-control input-sm" id="hotelCity"
								name="hotelCity" placeholder="markup Name" autocomplete="off"
								required value="ALL">
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="City"  >Country </label>
						 
							<select class="form-control input-sm" name="hotelCountry"
								id="hotelCountry" autocomplete="off" required>
								<option selected value="ALL">ALL</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="c_name"/>"><s:property
											value="c_name"></s:property></option>
								</s:iterator>

							</select>
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="Country"  >Applicable on existing markups</label>
						 
							<select class="form-control input-sm" name="isaccumulative"
								autocomplete="off" required>
								<option value="1" selected="selected">YES</option>
								<option value="0">NO</option>

							</select>
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="Country" >is
							FixedAmount</label>
						 
							<select class="form-control input-sm" name="isfixedAmount"
								autocomplete="off" required>
								<option value="1">YES</option>
								<option value="0">NO</option>
							</select>
						</div>
					</div>
				<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name" >MarkUp</label>
						 
							<input type="text" class="form-control input-sm cusValidationforprice"
								id="markupAmount" name="markupAmount" placeholder="amount"
								autocomplete="off" required
								>
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="Country"  >Position
							of MarkUp</label>
					 

							<input type="text" class="form-control input-sm"
								name="positionMarkup" id="country" autocomplete="off" required
								onkeypress="return isNumberKey(event,this);">

						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name"  >CheckIn
							Date</label>
						 
							<input type="text" id="datepicker_arr" required
								class="form-control input-sm" name="checkInDate"
								autocomplete="off" placeholder="ALL">
						</div>
					</div>

<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name"  >CheckOut
							Date</label>
						 
							<input type="text" id="datepicker_dep" required
								class="form-control input-sm" name="checkOutDate"
								autocomplete="off" placeholder="ALL">
						</div>
					</div>
<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name"  >Promofare
							StartDate </label>
						 
							<input type="text" id="datepicker_PromofareStart" required
								class="form-control input-sm" name="promofareStartDate"
								value='<s:property value="promofareStartDate" />'
								autocomplete="off" placeholder="ALL">
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name"  >Promofare
							EndDate </label>
						 
							<input type="text" id="datepicker_PromofareEnd" required
								class="form-control input-sm" name="promofareEndDate"
								value='<s:property value="promofareEndDate" />'
								autocomplete="off" placeholder="ALL">
						</div>
					</div>

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" class="btn btn-primary btn-lg" id="setMarkup">Set Markup</button>
						</div>
					</div>
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>

<script type="text/javascript">
	$(document).ready(function(){
		
		jQuery.validator.addMethod("amountValid",function(value) {
            var startprice = 1;
            return startprice < parseFloat($('#payBalance').val());
          }, "please add Amount Value");
		
		jQuery.validator.classRuleSettings.baseFareprice = { amountValid: true };
		
		jQuery.validator.addMethod("productTypeVal",function(value) {
            var startprice =  $('#holderName').val();
            return (startprice !=  '0');
          }, "please select card holder name");
		
	     $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
	          return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
	      }, "This field cannot have symbols.");

	      $.validator.addMethod("cusValidationAlphaName",function(value,element){
	          return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
	      },"This field cannot have numbers and symbols."); 
	      $.validator.addMethod("cusValidationforprice",function(value,element){
	          return this.optional(element) || /^[0-9.]+$/i.test(value);
	      },"This field cannot have Char and symbols.");


	$("#myfform").validate({
		 rules: {
	         
	         "email": {
	             required: true,
	             email: true
	         },
	     },
	     
	     messages: {
	         
	         "email": {
	             required: "Please, enter an email",
	             email: "Email is invalid"
	         },
	     },

	    submitHandler: function (form) { 
	        return false; 
	    }
	});

	});
	
	$('#setMarkup').click(function() {
        if($("#myfform").valid())
        	document.getElementById("myfform").submit();
     	 
    }); 
	</script>
	<script>
  
 function isNumberKey(evt,obj){
	 
     evt = (evt) ? evt : window.event;
       var charCode = (evt.which) ? evt.which : evt.keyCode;
       if (charCode > 31 &&  (charCode < 48 || charCode > 57))  
       
           return false;
       
    
      
   }
 
 </script>
	<!-- /.content-wrapper -->
	<%-- <script type="text/javascript" src="js/app.js"></script>  --%>
	<%--   --%>
	<%@ include file="DashboardFooter.jsp"%>
	<%--  <%@ include file="DashFooter.jsp" %>  --%>
	<%--  <script src=js/hotelAutoComplete.js> </script>  --%>




</body>

</html>