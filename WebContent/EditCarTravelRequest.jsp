<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%--
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> --%>


 
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
</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">
<script src="js/angular.js" type="text/javascript"></script>
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
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
</head>
<body data-ng-controller="AppCtrl">
	<div>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="row">
					<div class="col-sm-3 clearfix pull-left">
					<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-car" aria-hidden="true"></i>Car Travel Request</h3>
						 
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
					<!-- harsha added colapse -->
					<div class="col-sm-12">		 
						<form action="updateCarTravelRequest" method="post"
											class="form-horizontal " name="carexpensesFormId"
											id="carexpensesFormId" enctype="multipart/form-data"> 
											
																							
											 <div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon">  
								 <b> Car Travel Booking </b>  
						</div> 
					</div>
					<div class="inner-compreg clearfix">
											 <div class="col-sm-2">
													<div class="form-group">
														<label for="carTravelRequest.TRNo" class=" control-label">
															Travel Request Number </label> <input type="text"
															autocomplete="off" name=carTravelRequest.TRNo
															class="form-control input-sm" id="TRNo" value='<s:property value="carTravelRequest.TRNo"/>' required
															placeholder="Enter Travel Request Number ">
													</div> 
													</div>
													 <div class="col-sm-2">
													<div class="form-group">
														<label for="carTravelRequest.title">Title</label> <select
															class="form-control input-sm" required="required" 
															name="carTravelRequest.title">
															<option value="Mr">Mr</option>
															<option value="Mrs">Mrs</option>
															<option value="Miss">Miss</option>
															<option value="Master">Master</option>
														</select>
													</div>
													</div>
												 <div class="col-sm-2">
													<div class="form-group">
														<label for="carTravelRequest.firstName">FirstName</label>
														<input type="text" autocomplete="off"
															name="carTravelRequest.firstName" value='<s:property value="carTravelRequest.firstName"/>'
															class="form-control input-sm" required="required"
															placeholder="First Name">

													</div>
													</div>
												 	<div class="col-sm-2">
													<div class="form-group">
														<label for="carTravelRequest.lastName">LastName </label> <input
															type="text" autocomplete="off" value='<s:property value="carTravelRequest.lastName"/>'
															name="carTravelRequest.lastName"
															class="form-control input-sm" required="required"
															placeholder="Last Name"> 
													</div> 
												</div>		
											
											</div>
											</div>
											

											 

											<div class="form-group text-center">
												<div class="col-xs-12 submitWrap text-center">
												<input type="hidden" name="carTravelRequest.id" id="tripId" value="${carTravelRequest.id}">
													<button type="button" name="carTravelRequest"  value=""
														id="carTravelRequestsubmitnew"
														class="btn btn-primary btn-lg">Car Travel Request
														update</button>
												</div>
											</div>

										</form>
									</div> 
							</div> 
						<div id="roomscount"></div> 
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
		$(document).ready(function(){
			  
			   $('#carTravelRequestsubmitnew').click(function(){
				   $("#carexpensesFormId").valid();
				   if($("#carexpensesFormId").valid()){
					   document.getElementById("carexpensesFormId").submit();
				   }
			   });
			   $("#carexpensesFormId").validate({
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


$("#carexpensesFormId").validate({
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

$('#carTravelRequestsubmitnew').click(function() {
	   if($("#carexpensesFormId").valid())
	    	document.getElementById("carexpensesFormId").submit();
	 	 
	}); 

});
</script>  

</body>
</html>