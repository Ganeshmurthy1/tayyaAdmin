<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
  <html > 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit MarkUp</title>
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<%-- <link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>  
   <script src=//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>  
   <script src=//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
  <link rel="stylesheet" href="css/alert.css">
 <!-- <link href="css/jquerydarkness-ui.min.css" rel="stylesheet" type="text/css"/> 
 <link href="css/slider.css" rel="stylesheet" type="text/css"/>  
   -->
   <%--  <link href="css/slider.css" rel="stylesheet" type="text/css"/>   
   <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script> 
  
   <script src=js/angular-slider.js> </script>
 <link href="css/RangeSlider.css" rel="stylesheet" type="text/css"/> 
  <link href="css/slider.css" rel="stylesheet" type="text/css"/>   --%>
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
   $(function() {
		var id=$("#uniqueId").val();
		var accumulative=$("#accumulative").val();
		var fixedAmount=$("#fixedAmount").val();
		var destinationType=$("#destType").val();
	/* 	var positionOfMarkup=$("#positionOfMarkup").val(); */
		//var airline=$("#airline").val();
		var country=$("#country").val();
		//var classOfService=$("#classOfService").val();
		//alert(id+"\n"+country_id);
		 document.getElementById('accumulative'+id).value =accumulative;
		 document.getElementById('fixedAmount'+id).value =fixedAmount;
	/* 	 document.getElementById('positionOfMarkup'+id).value =positionOfMarkup; */
		 //document.getElementById('airline'+id).value =airline;
		 document.getElementById('country'+id).value =country;
		 document.getElementById('destType'+id).value =destinationType;
		 //document.getElementById('classOfService'+id).value =classOfService;

		 
	}); 
   
   
   
   
   
 </script> 
 <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_arr").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate: 0
		/*  changeMonth: true,
		 changeYear: true */
	 }); 
	 });
	 
 </script> 
     <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_dep").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate: 0
	 });
	 });
 </script>
  <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_PromofareStart").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate: 0
	 });
	 }); 
 </script>
   <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_PromofareEnd").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate: 0
	 });
	 }); 
 </script>
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body  >
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper" >
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Edit Hotel Markup <%-- <small>Control panel</small> --%>
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
				 <form id="updateHotelMarkupForm" action="updateHotelMarkup" method="post" class="form-horizontal">
				 <input type="hidden" value="<s:property value="CurrentProfile.id"/>" name="id">
						
						 <div class="form-group">
						<label for="City" class="col-sm-2 control-label">Config Number
							 </label>
						<div class="col-sm-8">
						<input type="text" class="form-control input-sm" 
									name="configname" placeholder="configname" autocomplete="off"
										value="<s:property value="CurrentProfile.config_number"/>  - <s:property value="CurrentProfile.configname"/> (<s:property value="CurrentProfile.companyName"/>-<s:property value="CurrentProfile.companyUserId"/>)"
									 disabled="disabled">					
						</div>
					</div>
						
						<div class="form-group">
							<label for="Username" class="col-sm-2 control-label">MarkUp
								Name</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="name"
									name="name" placeholder="markup Name" autocomplete="off"
									required  value="<s:property value="CurrentProfile.name"/>">
							</div>
						</div>
						
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Group</label>
							<div class="col-sm-8">
							  <input type="text" class="form-control input-sm" id="hotelChain"
									name="hotelChain" placeholder="Hotel Group"  
									required  value="All" >
							 
							 </div>
						</div>
							<div class="form-group">
							<label for="Username" class="col-sm-2 control-label">Hotel
								Name</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="hotelName"
									name="hotelName" placeholder="hotel Name" autocomplete="off"
									required  value="All" >
							</div>
						</div>
						<div class="form-group">
							<label for="Username" class="col-sm-2 control-label">City</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="hotelCity"
									name="hotelCity" placeholder="markup Name" autocomplete="off"
									required value="All">
							</div>
						</div>
						<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Country </label>
							<input type="hidden" value="<s:property value="CurrentProfile.id"/>" id="uniqueId">
						<input type="hidden" value="<s:property value="CurrentProfile.hotelCountry"/>" id="country">
							
						<div class="col-sm-8">
							<select class="form-control input-sm" name="hotelCountry" id="country<s:property value="CurrentProfile.id"/>"
								autocomplete="off" required>
								<option selected value="ALL">ALL</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="c_name"/>"><s:property
											value="c_name"></s:property></option>
								</s:iterator>

							</select>
						</div>
					</div>
				   <div class="form-group">
							<label for="Country" class="col-sm-2 control-label">is
								Accumulative</label>
									<input type="hidden" value="<s:property value="CurrentProfile.isaccumulative"/>" id="accumulative">
							<div class="col-sm-8">
								<select class="form-control input-sm" name="isaccumulative" id="accumulative<s:property value="CurrentProfile.id"/>"
									  autocomplete="off" required>
 									<option value="1">YES</option>
									<option value="0" selected="selected">NO</option>
									 
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">is
								FixedAmount</label>
								<input type="hidden" value="<s:property value="CurrentProfile.isfixedAmount"/>" id="fixedAmount">
							<div class="col-sm-8">
								<select class="form-control input-sm" name="isfixedAmount"  id="fixedAmount<s:property value="CurrentProfile.id"/>"
									  autocomplete="off" required>
 									<option value="1">YES</option>
									<option value="0">NO</option>
								 </select>
							</div>
						</div>
						  
						  <div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Amount</label>
							<div class="col-sm-8">
							 
								 <input type="number" class="form-control input-sm" name="markupAmount"
									id="amount" autocomplete="off" required value="<s:property value="CurrentProfile.markupAmount"/>">
							  
							</div>
						</div>
						 
						 
						 
						 
						<div class="form-group">
						<input type="hidden" value="<s:property value="CurrentProfile.destinationType"/>" id="destType">
						<label for="City" class="col-sm-2 control-label">Destination Type</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="destinationType" id="destType<s:property value="CurrentProfile.id"/>"
								autocomplete="off" required>
								<option selected value="ALL">ALL</option>
								<option  value="Domestic">Domestic</option>
								<option  value="International">International</option>
								 

							</select>
						</div>
					</div>
						
						 
						
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Position of MarkUp</label>
							<div class="col-sm-8">
							 
								 <input type="number" class="form-control input-sm" name="positionMarkup"
									id="country" autocomplete="off" required value="<s:property value="CurrentProfile.positionMarkup"/>">
							  
							</div>
						</div>
						 
						<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">CheckIn
							Date</label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_arr"
								class="form-control input-sm" name="checkInDate" autocomplete="off"
								placeholder="ALL"  required="required" 
								value="<s:property value="CurrentProfile.checkIn"/>">
						</div>
					</div>
						
 	
					<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">CheckOut
							Date</label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_dep"
								class="form-control input-sm" name="checkOutDate" autocomplete="off"
								placeholder="ALL"  required="required" 
								value="<s:property value="CurrentProfile.checkOut"/>"
								 >
						</div>
					</div>  
						
						<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Promofare StartDate
							 </label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_PromofareStart"
								class="form-control input-sm" name="promofareStartDate"  value='<s:property value="CurrentProfile.promofareStartDate" />'  autocomplete="off"
								placeholder="ALL" >
						</div>
					</div>
						<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Promofare EndDate
							 </label>
						<div class="col-sm-8">
							<input type="text" id="datepicker_PromofareEnd"
								class="form-control input-sm" name="promofareEndDate"  value='<s:property value="CurrentProfile.promofareEndDate"/>'  autocomplete="off"
								placeholder="ALL" >
						</div>
					</div>	
					 
 					<div class="form-group text-center">
							<div class="col-xs-12 submitWrap text-center">
								<!-- <button type="submit" class="btn btn-primary btn-lg">Set
									Markup</button> -->
									<button id="hotelMarkupSubmit" type="button" class="btn btn-primary btn-lg">Set
									Markup</button>
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
	 <%--  <script src=js/hotelAutoComplete.js> </script>  --%>
	
		<script>
	$(document).ready(function(){
		  
		   $('#hotelMarkupSubmit').click(function(){
			   $("#updateHotelMarkupForm").valid();
			   if($("#updateHotelMarkupForm").valid()){
				   document.getElementById("updateHotelMarkupForm").submit();
			   }
		   });
		 		   
		   $("#updateHotelMarkupForm").validate({
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