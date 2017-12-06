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
	
</script>
 --%>
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
						<h3>Update Car Travel Request</h3>
					</div>
					<div class="col-sm-9 clearfix pull-right " data-placement="top">
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
							<div class="col-sm-2 clearfix pull-right" data-placement="top">
								<div class="myDropdown">
									<button class="btn btn-top-link btn-xs dropdown-toggle"
										type="button" data-toggle="dropdown">
										Create Trip <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="goHotelTravelRequest">Hotel</a></li>
										<li><a href="goFlightTravelRequest">Flight</a></li>
									</ul>
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
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a class="collapsed" data-toggle="collapse"
											data-parent="#accordion" href="#collapseFour"> Car
											Booking </a>
									</h4>
								</div>
								<!--/.panel-heading -->
								<div id="collapseFour" class="panel-collapse collapse">
									<div class="panel-body">
										<form action="createCarTravelRequest" method="post"
											class="form-horizontal" name="carexpensesFormId"
											id="carexpensesFormId" enctype="multipart/form-data">

											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label for="carTravelRequest.TRNo" class=" control-label">
															Travel Request Number </label> <input type="text"
															autocomplete="off" name=carTravelRequest.TRNo
															class="form-control input-sm" id="TRNo" value="" required
															placeholder="Enter Travel Request Number ">
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label for="carOrderRow.confirmationNumber"
															class=" control-label"> Confirmation Number </label> <input
															type="text" autocomplete="off"
															name="carOrderRow.confirmationNumber"
															class="form-control input-sm" id="confirmationNumber"
															value="" required
															placeholder="Enter Confirmation Number ">
													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="carOrderRow.carCompanyName"
															class=" control-label"> Car Company Name </label> 
															<select
														class="form-control input-sm" required="required" id="carcompanyName" 
														name="carOrderRow.carCompanyName">
														<option value="small">Small</option>
														<option value="medium">Medium</option>
														<option value="sedan">Sedan</option>
														<option value="suv">SUV</option>
													</select>

													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">

													<div class="form-group">
														<label for="carTravelRequest.title">Title</label> <select
															class="form-control input-sm" required="required"
															name="carTravelRequest.title">
													<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
														</select>
													</div>
												</div>

												<div class="col-sm-4">
													<div class="form-group">
														<label for="carTravelRequest.firstName">FirstName</label>
														<input type="text" autocomplete="off"
															name="carTravelRequest.firstName"
															class="form-control input-sm" required="required"
															placeholder="First Name">

													</div>
												</div>


												<div class="col-sm-4">
													<div class="form-group">
														<label for="carTravelRequest.lastName">LastName </label> <input
															type="text" autocomplete="off"
															name="carTravelRequest.lastName"
															class="form-control input-sm" required="required"
															placeholder="Last Name">

													</div>
												</div>
											</div>
											<div class="row">


												<div class="col-sm-4">

													<div class="form-group">
														<label for="carOrderRow.location" class=" control-label">Location
														</label> <input type="text" autocomplete="off"
															name="carOrderRow.location" class="form-control input-sm"
															id="carlocation" value="" placeholder="Location "
															required>

													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label for="carTravelRequestQuotation.pickUp">Pick
															Up </label> <input type="text" autocomplete="off"
															name="carTravelRequestQuotation.pickUp"
															class="form-control input-sm" required="required"
															placeholder="Enter Pickup Location">

													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label for="carTravelRequestQuotation.dropOff">Drop
															Off </label> <input type="text" autocomplete="off"
															name="carTravelRequestQuotation.dropOff"
															class="form-control input-sm" required="required"
															placeholder="Enter DtropOff Location">

													</div>
												</div>






											</div>
											<div class="row">



												<div class="col-sm-4">
													<div class="form-group">
														<label for="carTravelRequestQuotation.currency">Currency
														</label> <select class="form-control input-sm"
															name="carTravelRequestQuotation.currency" id="Currency"
															required>
															<s:iterator value="CountryList">
														<c:choose>
														<c:when test="${cur_code=='INR'}">
															<option value="<s:property value="cur_code"/>" selected  >
																<s:property value="cur_code" /></option>
														</c:when>	
														<c:otherwise>
															<option value="<s:property value="cur_code"/>" >
																<s:property value="cur_code" /></option>
														</c:otherwise>		
														</c:choose>
														</s:iterator>
														</select>
													</div>

												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label for="carOrderRow.travelDate" class=" control-label">Travel
															Date </label> <input type="text" autocomplete="off"
															name="carOrderRow.travelDateTemp" onchange="validateDateFormat(this)"
															class="form-control input-sm" id="traveldateforcar"
															value="" required placeholder="Enter Travel Date ">
													</div>
												</div>

												<div class="col-sm-4">

													<div class="form-group">
														<label for="carOrderRow.tollOrParkingCharges"
															class=" control-label"> Toll or Parking Charges </label>
														<input type="text" autocomplete="off" onchange="numbersonly(this)"
															name="carOrderRow.tollOrParkingCharges"
															class="form-control input-sm" id="tollOrParkingCharges"
															value="0" required placeholder="Enter toll/park charges">

													</div>
												</div>
											</div>
											<div class="row">



												<div class="col-sm-4">

													<div class="form-group">
														<label for="carOrderRow.driverAllowanceDay"
															class=" control-label"> Driver Allowance Day </label> <input
															type="text" autocomplete="off"
															name="carOrderRow.driverAllowanceDay"
															class="form-control input-sm" id="driverAllowanceDay"
															value="0" required onchange="numbersonly(this)"
															placeholder="Enter Driver Allowance Day">

													</div>
												</div>

												<div class="col-sm-4">

													<div class="form-group">
														<label for="carOrderRow.driverAllowanceNight"
															class=" control-label"> Driver Allowance Night </label> <input
															type="text" autocomplete="off"
															name="carOrderRow.driverAllowanceNight"
															class="form-control input-sm" id="driverAllowanceNight"
															value="0" required onchange="numbersonly(this)"
															placeholder="Enter Driver Allowance Night">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="carOrderRow.markUp" class=" control-label">Mark
															up Amount</label> <input type="text" autocomplete="off"
															name="carOrderRow.markUp" class="form-control input-sm"
															id="markUp" value="0" required onchange="numbersonly(this)"
															placeholder="Enter Management Fee">

													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">

													<div class="form-group">
														<label for="carOrderRow.managementFee"
															class=" control-label">Management Fee</label> <input
															type="text" autocomplete="off" onchange="numbersonly(this)"
															name="carOrderRow.managementFee"
															class="form-control input-sm" id="managementFee" value="0"
															required placeholder="Enter Management Fee">

													</div>
												</div>

												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label">
															Convenience Fee </label> <input type="text" autocomplete="off"
															name="carOrderRow.convenienceFee" onchange="numbersonly(this)"
															class="form-control input-sm" id="convenienceFee"
															value="0" required placeholder="Enter Convenience Fee">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label">
															Service Tax </label> <input type="text" autocomplete="off"
															name="carOrderRow.serviceTax" onchange="numbersonly(this)"
															class="form-control input-sm" id="serviceTax" value="0"
															required placeholder="Enter Service Tax ">

													</div>
												</div>
											</div>
											<div class="row">

												<div class="col-sm-4">

													<div class="form-group">
														<label for="carOrderRow.extraKM" class=" control-label">
															Extra KM </label> <input type="text" autocomplete="off"
															name="carOrderRow.extraKM" class="form-control input-sm"
															id="extraKM" value="0" required onchange="numbersonly(this)"
															placeholder="Enter extraKM">

													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label for="carOrderRow.extraHours" class=" control-label">
															Extra Hours </label> <input type="text" autocomplete="off"
															name="carOrderRow.extraHours" onchange="numbersonly(this)"
															class="form-control input-sm" id="extraHours" value="0"
															required placeholder="Enter Extra Hours">

													</div>
												</div>
												<div class="col-sm-3">

													<div class="form-group">
														<label for="carOrderRow.otherTaxes" class=" control-label">
															Other Taxes </label> <input type="text" autocomplete="off"
															name="carOrderRow.otherTaxes" onchange="numbersonly(this)"
															class="form-control input-sm" id="otherTaxes" value="0"
															required placeholder="Enter Other Taxes ">

													</div>
												</div>
												<div class="col-sm-3">

													<div class="form-group">
														<label for="carOrderRow.basePrice" class=" control-label">
															Base Price </label> <input type="text" autocomplete="off"
															name="carOrderRow.basePrice" onchange="numbersonly(this)"
															class="form-control input-sm" id="basePrice" value="0"
															required placeholder="Enter Base Price">

													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label for="carOrderRow.totalAmount"
															class=" control-label">Total Amount </label> <input
															type="text" autocomplete="off" onchange="numbersonly(this)"
															name="carOrderRow.totalAmount" id="carexpenseamount"
															class="form-control input-sm" placeholder="Total Amount"
															value="0" required>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label for="carTravelRequestQuotation.remarks"
															class=" control-label"> Remarks </label>
														<textarea rows="1" cols="1" class="form-control input-sm"
															name="carTravelRequestQuotation.remarks"
															placeholder="remarks" required></textarea>
													</div>
												</div>
											</div>

											<div class="form-group text-center">
												<div class="col-xs-12 submitWrap text-center">
												<input type="hidden" name="tripId" id="tripId" value="${tripId}">
													<button type="submit" name="carTravelRequest" value=""
														id="carTravelRequestsubmitnew"
														class="btn btn-primary btn-lg">Car Travel Request
														Save</button>
												</div>
											</div>

										</form>
									</div>
									<!--/.panel-body -->
								</div>
								<!--/.panel-collapse -->
							</div>
						</div>


						<div id="roomscount"></div>
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

</body>
</html>