<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
 
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

.error {
    color:red;
}
.valid {
    color:green;
}
</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">
<script src="js/angular.js" type="text/javascript"></script>
<script type="text/javascript">
	function numbersonly(thisObject) {
		//var intRegex = /^\d+$/;
		var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
		if(!floatRegex.test($(thisObject).val())) {
			$(thisObject).val('0');
			$(thisObject).focus();
		}
	}
	function validateDateFormat(thisObject) {
		var dtRegex = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
	    if(!dtRegex.test($(thisObject).val())){
	    	$(thisObject).val('');
			$(thisObject).focus();
	    }
	}
</script>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<div>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="row">
					<div class="col-sm-3 clearfix pull-left">
					<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-file" aria-hidden="true"></i> Miscellaneous Travel Request</h3>  
						 
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
							<a href="MiscellaneousTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Miscellaneous Trips
				</a>
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
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>							
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
					<!-- colapse -->
					<div class="col-sm-12">
					 
					<form action="updateMiscellaneousOrder" method="post" class="form-horizontal"  id="miscellaneousOrderUpdate" > 
							<div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon">  
								 <b>Miscellaneous Travel Request</b>  
						</div> 
					</div>
					<div class="inner-compreg clearfix">  
												<div class="col-sm-2">
													<div class="form-group">
														<label for="miscellaneousOrderRow.confirmationNumber"
															class=" control-label"> Confirmation Number </label> <input
															type="text" autocomplete="off"
															name="miscellaneousOrderRow.confirmationNumber"
															class="form-control input-sm" id="confirmationNumber"
															value='<s:property value="miscellaneousOrderRow.confirmationNumber"/>' required
															placeholder="Enter Confirmation Number ">
													</div>
												</div>
												
											 
												<div class="col-sm-2">
													<div class="form-group">
														<label for="miscellaneousOrderRow.supplierName" class=" control-label">Supplier
															Name </label> <input type="text" autocomplete="off"
															name="miscellaneousOrderRow.supplierName" 
															class="form-control input-sm" id="suppliername" value='<s:property value="miscellaneousOrderRow.supplierName"/>'
															 required placeholder="Enter Supplier Name ">
													</div>
												</div>
												
											
												<div class="col-sm-2"> 
													<div class="form-group">
														<label for="miscellaneousOrderRow.statusAction" class=" control-label">
															Status Action </label> <input type="text" autocomplete="off"
															name="miscellaneousOrderRow.statusAction"
															class="form-control input-sm" value='<s:property value="miscellaneousOrderRow.statusAction"/>'
															required placeholder="Enter Other Taxes ">

													</div>
												</div>
												<div class="col-sm-2">

													<div class="form-group">
														<label for="miscellaneousOrderRow.paymentStatus" class=" control-label">
															Payment Status </label> <input type="text" autocomplete="off"
															name="miscellaneousOrderRow.paymentStatus" required
															class="form-control input-sm"  value='<s:property value="miscellaneousOrderRow.paymentStatus"/>'
															placeholder="Enter Payment Status ">

													</div>
												</div>
										
											 
					
					
					</div>
					</div> 
					
					
							<div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon">  
								 <b>Miscellaneous Pricing Details</b>  
						</div> 
					</div>
					<div class="inner-compreg clearfix"> 
																<div class="price-details">
												<div class="col-sm-2">
													<div class="form-group">
														<label for="miscellaneousOrderRow.bookingCurrency" class=" control-label">
															bookingCurrency</label> <input type="text" autocomplete="off"
															name="miscellaneousOrderRow.bookingCurrency" readonly="readonly"
															class="form-control input-sm" id="otherTaxes"value='<s:property value="miscellaneousOrderRow.bookingCurrency"/>'
															 placeholder="Enter Other Taxes ">
													</div> 
													</div>
														<div class="col-sm-2">

													<div class="form-group">
														<label for="miscellaneousOrderRow.baseToBookingExchangeRate" class=" control-label">
															baseToBookingExchangeRate </label> <input type="text" autocomplete="off"
															name="miscellaneousOrderRow.baseToBookingExchangeRate" onchange="numbersonly(this)"
															class="form-control input-sm" id="otherTaxes"value='<s:property value="miscellaneousOrderRow.baseToBookingExchangeRate"/>'
															required placeholder="Enter Other Taxes ">
													</div>
													</div>
												 	<div class="col-sm-2">

													<div class="form-group">
														<label for="miscellaneousOrderRow.apiToBaseExchangeRate" class=" control-label">
															apiToBaseExchangeRate </label> <input type="text" autocomplete="off"
															name="miscellaneousOrderRow.apiToBaseExchangeRate" onchange="numbersonly(this)"
															class="form-control input-sm" id="otherTaxes"value='<s:property value="miscellaneousOrderRow.apiToBaseExchangeRate"/>'
															required placeholder="Enter Other Taxes ">

													</div>
													</div>
														<div class="col-sm-2">
												 
													<div class="form-group">
														<label for="miscellaneousOrderRow.totalAmount"
															class=" control-label">Total Amount </label> <input
															type="text" autocomplete="off" onchange="numbersonly(this)"
															name="miscellaneousOrderRow.totalAmount" id="carexpenseamount"
															class="form-control input-sm" placeholder="Total Amount"
															value='<s:property value="miscellaneousOrderRow.totalAmount"/>' required>
													</div>
												 </div>
											</div>
					
					</div>
					</div>
					 
											<div class="form-group text-center">
												<div class="col-xs-12 submitWrap text-center">
												<input type="hidden" name="miscellaneousOrderRow.id"  value="${miscellaneousOrderRow.id}">
													<button type="submit" name="" value=""
														id="buttonSubmit"
														class="btn btn-primary btn-lg">Miscellaneous Order Row update
														</button>
												</div>
											</div>

										</form>
					 
					
				  
					</div>
					<!-- /.panel-group -->
				</div>
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
		$('#traveldateforcar').datepicker({
			dateFormat : 'dd-mm-yy',

		});
	</script>
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


$("#miscellaneousOrderUpdate").validate({
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
	   if($("#miscellaneousOrderUpdate").valid())
	    	document.getElementById("miscellaneousOrderUpdate").submit();
	}); 
});
</script>  
</body>
</html>