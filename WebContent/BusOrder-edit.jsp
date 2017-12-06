<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Designation</title>

<%--
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> --%>
<%-- <script src="js/angular.js" type="text/javascript"></script>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>

<%-- <link href="css/jquery-ui-timepicker-addon.min.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/jquery-ui-timepicker-addon.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="css/alert.css"> --%>
<!-- 
<link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> -->
<!--  <link href="css/jquery-uii.css" rel="stylesheet" type="text/css" /> 
  
 -->

<style type="text/css">
#rooms {
	margin-top: 5px;
}

.panel-group .panel-heading+.panel-collapse>.panel-body {
	border: 1px solid #ddd;
}

.panel-group, .panel-group .panel, .panel-group .panel-heading,
	.panel-group .panel-heading a, .panel-group .panel-title, .panel-group .panel-title a,
	.panel-group .panel-body, .panel-group .panel-group .panel-heading+.panel-collapse>.panel-body
	{
	border-radius: 2px;
	border: 0;
}

.panel-group .panel-heading {
	padding: 0;
}

.panel-group .panel-heading a {
	display: block;
	background: #668bb1;
	color: #ffffff;
	padding: 8px;
	text-decoration: none;
	position: relative;
}

.panel-group .panel-heading a.collapsed {
	background: #eeeeee;
	color: inherit;
}

.panel-group .panel-heading a:after {
	content: '-';
	position: absolute;
	right: 20px;
	top: 8px;
	font-size: 20px;
}

.panel-group .panel-heading a.collapsed:after {
	content: '+';
}

.more-details a {
	width: 60px;
	text-align: left;
	position: relative;
}

.more-details a:after {
	content: '-';
	position: absolute;
	right: 10%;
	top: 18%;
	font-size: 15px;
	color: #fff;
}

.more-details a.collapsed:after {
	content: '+';
	/*   position: absolute;
  left: 10%;
  top:0px;
  font-size:25px; */
	color: #fff;
}

.panel-group .panel-collapse {
	margin-top: 5px !important;
}

.panel-group .panel-body {
	background: #ffffff;
	padding: 15px;
}

.panel-group .panel {
	background-color: transparent;
}

.panel-group .panel-body p:last-child, .panel-group .panel-body ul:last-child,
	.panel-group .panel-body ol:last-child {
	margin-bottom: 0;
}

.add-remove {
	margin: 20px 0px 20px;
}
</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">
<script src="js/angular.js" type="text/javascript"></script>

 	<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<script type="text/javascript">
	function numbersonly(thisObject) {
		var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
		if (!floatRegex.test($(thisObject).val())) {
			$(thisObject).val('0');
			$(thisObject).focus();
		}
	}
	function validateDateFormat(thisObject) {
		var dtRegex = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
		if (!dtRegex.test($(thisObject).val())) {
			$(thisObject).val('');
			$(thisObject).focus();
		}
	}
</script>
</head>
<body data-ng-controller="AppCtrl">
	<div>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="row">
					<div class="col-sm-3 clearfix pull-left"> 
						<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-car" aria-hidden="true"></i>Bus Travel Request</h3>
					</div>
		 
										
	<div class="col-sm-9 clearfix pull-right " data-placement="top">
		<div class="row">
			<div class="col-sm-12 clearfix pull-right " data-placement="top">
				<div class="row">
					<div class="col-sm-10 clearfix " data-placement="top">
						<a href="goTrips" class="btn btn-top-link btn-xs">
							All Trips </a>
						<a href="HotelTravelRequestList" class="btn btn-top-link btn-xs">
							Hotel Trips </a> <a href="FlightTravelRequestList"
							class="btn btn-top-link btn-xs"> Flight Trips </a> <a
							href="CarTravelRequestList" class="btn btn-top-link btn-xs">
							Car Trips </a> <a href="BusTravelRequestList"
							class="btn btn-top-link btn-xs"> Bus Trips </a> <a
							href="TrainTravelRequestList" class="btn btn-top-link btn-xs">
							Train Trips </a> <a href="VisaTravelRequestList"
							class="btn btn-top-link btn-xs"> Visa Trips </a> <a
							href="InsuranceTravelRequestList" class="btn btn-top-link btn-xs">
							Insurance Trips </a>
					</div>
					<div class="col-sm-2 clearfix pull-right" data-placement="top">
						<div class="myDropdown">
							<button class="btn btn-top-link btn-xs dropdown-toggle"
								type="button" data-toggle="dropdown">
								Create Trip <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="goHotelTravelRequest">Hotel</a></li>
								<li><a href="goFlightTravelRequest">Flight</a></li>
								<li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>								
							</ul>
						</div>
					</div>
				</div>
			</div>	  
			</div>	
			<div class="row">
				<div class="col-sm-12">
				</div>
			</div>
		<div class="row">
		<div class="col-sm-12 clearfix pull-left " data-placement="top">
				
		</div>
			</div>			
			</div> 
					
<%-- 					<div class="col-sm-9 clearfix pull-right " data-placement="top">
						<div class="row">
							<div class="col-sm-6 clearfix pull-left " data-placement="top">

							</div>
							<div class="col-sm-4 clearfix " data-placement="top">
								<a href="goTrips" class="btn btn-top-link btn-xs"> All Trips
								</a> <a href="HotelTravelRequestList"
									class="btn btn-top-link btn-xs" title="Check Hotel Trips">
									Hotel Trips </a> <a href="FlightTravelRequestList"
									class="btn btn-top-link btn-xs" title="Check Flight Trips">
									Flight Trips </a>
							</div>
							<div class="col-sm-3 clearfix pull-right" data-placement="top">
								<div class="myDropdown">
									<button class="btn btn-top-link btn-xs dropdown-toggle"
										type="button" data-toggle="dropdown">
										Create Trip <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="goHotelTravelRequest">Hotel</a></li>
										<li><a href="goFlightTravelRequest">Flight</a></li>
										<li><a href="goCarTravelRequest">Car</a></li>
										<li><a href="goBusTravelRequest">Bus</a></li>
										<li><a href="goTrainTravelRequest">Train</a></li>
										<li><a href="goVisaTravelRequest">Visa</a></li>
										<li><a href="goInsuranceTravelRequest">Insurance</a></li>

										<!--  <li><a href="allexpenses">Expenses</a></li> -->
									</ul>
								</div>
							</div>
						</div>
					</div> --%>
				</div>
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

				<div class="row">
					<!-- harsha added colapse -->



					<div class="col-sm-12">
					
															<form action="updateBusOrder" method="post"
											class="form-horizontal" name="busexpensesFormId"
											id="busexpensesFormId" >
											
																								
											 <div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon">  
								 <b> Bus Quotations </b>  
						</div> 
					</div>
					<div class="inner-compreg clearfix">
					
																	<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.confirmationNumber"
															class=" control-label"> Confirmation Number </label> <input
															type="text" autocomplete="off"
															name="busOrderRow.confirmationNumber"
															class="form-control input-sm" id="confirmationNumber"
															value='<s:property value="busOrderRow.confirmationNumber"/>' required
															placeholder="Enter Confirmation Number ">
													</div>
												</div>
												<div class="col-sm-2">

													<div class="form-group">
														<label for="busOrderRow.carCompanyName"
															class=" control-label"> Bus Company Name </label> <input
															type="text" autocomplete="off"
															name="busOrderRow.busCompanyName"
															class="form-control input-sm" id="buscompanyName"
															value='<s:property value="busOrderRow.busCompanyName"/>' placeholder="Bus Company name" required>

													</div>
												</div>

												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.busType" class=" control-label">
															Bus Type</label> <input type="text" autocomplete="off"
															name=busOrderRow.busType class="form-control input-sm"
															id="busType" value='<s:property value="busOrderRow.busType"/>' required
															placeholder="Enter busType ">
													</div>
												</div>

												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.location" class=" control-label">location</label>
														<input type="text" autocomplete="off"
															name=busOrderRow.location class="form-control input-sm"
															id="location" value='<s:property value="busOrderRow.location"/>' required
															placeholder="Enter location ">
													</div>
												</div>

												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.pickUp" class=" control-label">
															pickUp</label> <input type="text" autocomplete="off"
															name=busOrderRow.pickUp class="form-control input-sm"
															id="pickUp" value='<s:property value="busOrderRow.pickUp"/>' required
															placeholder="Enter pickUp location ">
													</div>
												</div>

												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.dropOff" class=" control-label">
															dropOff </label> <input type="text" autocomplete="off"
															name=busOrderRow.dropOff class="form-control input-sm"
															id="dropOff" value='<s:property value="busOrderRow.dropOff"/>' required
															placeholder="Enter dropOff location ">
													</div>
												</div>

												

												<div class="col-sm-2">
													<div class="form-group">
														<label for="busTravelRequestQuotation.travelDateTemp"
															class=" control-label">travel Date</label> <input
															type="text" autocomplete="off"
															name=busTravelRequestQuotation.travelDateTemp
															class="form-control input-sm" id="bustraveldate1"
															required onchange="validateDateFormat(this)"
															placeholder="select the date ">
													</div>
												</div>

												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.supplierPrice"
															class=" control-label">Supplier Price</label> <input
															type="text" autocomplete="off"
															name=busOrderRow.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value='<s:property value="busOrderRow.supplierPrice"/>' required onchange="numbersonly(this)"
															placeholder="Enter supplier Price ">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.basePrice" class=" control-label">
															Base Price</label> <input type="text" autocomplete="off"
															name=busOrderRow.basePrice class="form-control input-sm"
															id="basePrice" value='<s:property value="busOrderRow.basePrice"/>' required
															onchange="numbersonly(this)"
															placeholder="Enter  base Price">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.otherTaxes" class=" control-label">Other
															Taxes</label> <input type="text" autocomplete="off"
															name=busOrderRow.otherTaxes class="form-control input-sm"
															id="otherTaxes" value='<s:property value="busOrderRow.otherTaxes"/>' required
															onchange="numbersonly(this)"
															placeholder="Enter otherTaxes ">
													</div>
												</div>

												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.managementFee"
															class=" control-label">Management Fee</label> <input
															type="text" autocomplete="off"
															name=busOrderRow.managementFee
															class="form-control input-sm" id="managementFee"
															 value='<s:property value="busOrderRow.managementFee"/>' required onchange="numbersonly(this)"
															placeholder="Enter management Fee ">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.convenienceFee"
															class=" control-label">Convenience Fee </label> <input
															type="text" autocomplete="off"
															name=busOrderRow.convenienceFee
															class="form-control input-sm" id="convenienceFee"
															value='<s:property value="busOrderRow.convenienceFee"/>' required onchange="numbersonly(this)"
															placeholder="Enter convenience Fee ">
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.serviceTax" class=" control-label">
															Service Tax</label> <input type="text" autocomplete="off"
															name=busOrderRow.serviceTax class="form-control input-sm"
															id="serviceTax" value='<s:property value="busOrderRow.serviceTax"/>'
															onchange="numbersonly(this)"
															placeholder="Enter service Tax " required>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label for="busOrderRow.totalAmount"
															class=" control-label"> Total Amount </label> <input
															type="text" autocomplete="off"
															name=busOrderRow.totalAmount
															class="form-control input-sm" id="totalAmount" value='<s:property value="busOrderRow.totalAmount"/>'
															required onchange="numbersonly(this)"
															placeholder="Enter total Amount  ">
															
													</div>
												</div>
					
					
					</div>
					</div> 	
												<div class="form-group text-center">
													<div class="col-xs-12 submitWrap text-center">
														<input type="hidden" name="busOrderRow.id" id="tripId"
															value="${busOrderRow.id}">
														<button type="button" name="busTravelRequest" value=""
															id="busTravelRequestsubmitnew"
															class="btn btn-primary btn-lg">Bus Order update</button>
													</div>
												</div>
									 
										</form> 
					</div>
				</div>

				<!-- harsha added colapse ended -->

			</section>

		</div>

		<!-- /.row -->
		<!-- Main row -->

		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<div class="modal fade" id="emailModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body clearfix">
					<p id="desc"></p>

				</div>

			</div>
		</div>
	</div>
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>


	<script type="text/javascript">
		$('#bustraveldate1').datepicker({
			dateFormat : 'dd-mm-yy',
		});
	</script>
	
		<script>
	
	
	
	
	$(document).ready(function(){ 
		 
	    $("#busexpensesFormId").validate({
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
	    
	    $('#busTravelRequestsubmitnew').click(function() { 
	  	if($("#busexpensesFormId").valid())  
	    	 document.getElementById("busexpensesFormId").submit();
	  /* 	else
	  		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
	    });    
	    
	});

	</script>

</body>
</html>