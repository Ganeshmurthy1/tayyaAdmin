<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Booking Page</title>

 
 
<link rel="stylesheet" href="css/alert.css">
<link href="css/offline_booking.css" rel="stylesheet" type="text/css" />
<script src="js/flight_city_url.js" type="text/javascript"></script>
 <script src="js/airlines.js"></script>
<SCRIPT TYPE="text/JavaScript">
	function validateHhMm(inputField) {
		var isValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/
				.test(inputField.value);

		if (isValid) {

			/* inputField.style.backgroundColor = '#bfa'; */
		} else {
			alert("format must be like this: HH:mm:ss");
			//inputField.style.backgroundColor = '#fba';
		}

		return isValid;
	}

	$(function() {
		var clientPaymentMethod = $('#clientPaymentMethod').val();
		var supplierPaymentMethod = $('#supplierPaymentMethod').val();
		if (clientPaymentMethod == 'cash' || clientPaymentMethod == 'online') {
			$('.client-comments').show();
		} else {
			$('.client-comments').hide();
		}
		if (supplierPaymentMethod == 'cash'
				|| supplierPaymentMethod == 'online') {
			$('.supplier-comments').show();
		} else {
			$('.supplier-comments').hide();
		}

		$('#clientPaymentMethod').on('change', function() {

			if (this.value == 'cash' || this.value == 'online') {
				$('.client-comments').show();
			} else {
				$('.client-comments').hide();
			}

		})

		$('#supplierPaymentMethod').on('change', function() {
			if (this.value == 'cash' || this.value == 'online') {
				$('.supplier-comments').show();
			} else {
				$('.supplier-comments').hide();
			}
		})

		$('#supplierPaymentType').on('change', function() {

			if (this.value == 'Partial') {
				$('.supplierPayment').show();
				$('.card-details').hide();
				$('.no-payment-mode').show();

			} 
			else if(this.value == 'Zero'){
				$('#supplierAmount').val("");
				$('.supplierPayment').hide();
				$('.card-details').hide();
				$('.no-payment-mode').hide();
			}
			else {
				$('.supplierPayment').hide();
				$('.card-details').show();
				$('.no-payment-mode').show();
				$('#supplierAmount').val("");
			}
		})

		$('#clientPaymentType').on('change', function() {
			if (this.value == 'Partial') {
				$('.clientPayment').show();
				$('.client-card-details').hide();
				$('.no-client-payment-mode').show();
				document.getElementById('paymentStatus').value = "Pending";
			}
			else if(this.value == 'Zero'){
				$('#clientAmount').val("");
				$('.clientPayment').hide();
				$('.client-card-details').hide();
				$('.no-client-payment-mode').hide();
			}
			
			else {
				$('.clientPayment').hide();
				$('.client-card-details').show();
				$('#clientAmount').val("");
				$('.no-client-payment-mode').show();
				document.getElementById('paymentStatus').value = "Paid";
			}
		})

		if (clientPaymentType == 'Partial') {
			$('.clientPayment').show();
			$('.client-card-details').hide();
			$('.no-client-payment-mode').show();
			document.getElementById('paymentStatus').value = "Pending";
		}if (clientPaymentType == 'Zero') {
			$('#clientAmount').val("");
			$('.clientPayment').hide();
			$('.client-card-details').hide();
			$('.no-client-payment-mode').hide();
		}
		else {
			$('.clientPayment').hide();
			$('.client-card-details').show();
			$('#clientAmount').val("");
			$('.no-client-payment-mode').show();
			document.getElementById('paymentStatus').value = "Paid";
		}
	});
</SCRIPT>
<style type="text/css">
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	/* add padding to account for vertical scrollbar */
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}
</style>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight Booking</h1>

		</section>
		<!-- Main content -->
		<section class="content clearfix">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			
			<div id="suppliermyNotification" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Supplier Payment Notification Alert !</h4>
      </div>
     
      <div class="modal-body">
       <span style='color:red;font-size:11px;display:block'> (* Please separate emails with semicolon) </span>
      <form action="" method="post" class="form-horizontal" name="myForm"  id="supplierForm">
     <div class="row">
     <div class="col-sm-12"> 
   
      <div class="col-sm-12  "> 
      <div class="form-group">
  <label for="comment">To Mail(s):</label> 
  <textarea class="form-control" rows="1" id="supplierToEmails"></textarea>
</div>
</div>
     
     <div class="col-sm-12"> 
      <div class="form-group">
  <label for="comment">CC Mail(s):</label>
  <textarea class="form-control" rows="1" id="supplierCcEmails"></textarea>
</div>
</div>
  
     <div class="col-sm-6 fd"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       From Date </label>
        <input type="text" class="form-control input-sm" value=""
         required id="supplierFromDate"   placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-6"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       To Date <font size="1" color="red">(Selected time is preferred)</font></label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="supplierToDate"   name="" placeholder="date with preferable time" >
       </div>
      </div>
      <div class="col-sm-6 fd"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       Due Date </label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="supplierDueDate"   name="" placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-6"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       Paid Date </label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="supplierPaidDate"   name="" placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-12"> 
      <div class="form-group">
  <label for="comment">Comment:</label>
  <textarea class="form-control" rows="1" id="supplierComment"></textarea>
</div>
</div>
      
     </div>
     </div>
     
     </form>
       
      </div>
      <div class="modal-footer">
  <div class="col-sm-12">
  <button type="button"  id="paymentNotificationAlert"    onclick="sendSupplierAndClientNotification('supplier');"     class="btn btn-primary btn-sm">Set Supplier Reminder</button>
       
        <button type="button"  class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
  
  </div>
       
      </div>
    </div>

  </div>
</div>
						<!-- Modal -->
<div id="clientmyNotification" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Client Payment Notification Alert !</h4>
      </div>
     
      <div class="modal-body">
       <span style='color:red;font-size:11px'>(* Please separate mails with semicolon) </span>
      <form action="" method="post" class="form-horizontal" name="myForm"  id="clientForm">
     <div class="row">
     <div class="col-sm-12"> 
   
      <div class="col-sm-12 "> 
      <div class="form-group">
  <label for="comment">To Mail(s):</label> 
  <textarea class="form-control" rows="1" id="clientToEmails"></textarea>
</div>
</div>
     
<div class="col-sm-12"> 
 <div class="form-group">
  <label for="comment">CC Mail(s):</label>
  <textarea class="form-control" rows="1" id="clientCcEmails"></textarea>
</div>
</div>
  
     <div class="col-sm-6 fd"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       From Date </label>
        <input type="text" class="form-control input-sm" value=""
         required id="clientFromDate"   placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-6"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       To Date <font size="1" color="red">(Selected time is preferred)</font></label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="clientToDate"   name="" placeholder="date with preferable time" >
       </div>
      </div>
      <div class="col-sm-6 fd"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       Due Date </label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="clientDueDate"   name="" placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-6"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       Paid Date </label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="clientPaidDate"   name="" placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-12"> 
      <div class="form-group">
  <label for="comment">Comment:</label>
  <textarea class="form-control" rows="1" id="clientComment"></textarea>
</div>
</div>
     </div>
     </div>
     </form>
      </div>
      <div class="modal-footer">
      <div class="col-sm-12">
       <button type="button"  id="paymentNotificationAlert"  onclick="sendSupplierAndClientNotification('client');"   class="btn btn-primary btn-sm">Set Client Reminder</button>
        <button type="button"  class="btn btn-danger btn-sm" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>

  </div>
</div>
			
			<div class="row" id="dash-us-register">
				<form class="form-horizontal" name="myForm">
					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<tr>
							<th>Title</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>Trip Type</th>
							<th>Departure Date</th>
							<th>Arrival Date</th>
							<%-- <s:if test="flightQuotation.tripType=='Round' || flightQuotation.tripType=='Multi'">
								<th>Arrival Date</th>
								</s:if> --%>
							 
						</tr>
						<tr>
							<th>${flightQuotation.flightTravelRequest.title}</th>
							<th>${flightQuotation.flightTravelRequest.firstName}</th>
							<th>${flightQuotation.flightTravelRequest.lastName}</th>
							<s:if
								test="flightQuotation.tripType=='Round' || flightQuotation.tripType=='Multi'">
								<th>Round Trip</th>
							</s:if>
							<s:else>
								<th>One Trip</th>
							</s:else>
							<th>${flightQuotation.transDepartureDate}</th>
							<th>${flightQuotation.transArrivalDate}</th>
							<%-- <s:if
								test="flightQuotation.tripType=='Round' || flightQuotation.tripType=='Multi'">
							<th>${flightQuotation.transArrivalDate}</th>
							</s:if> --%>
						</tr>
					</table>
				</form>
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



			<!-- Added details div in collapse -->

			<form action="createFlightOfflineBooking" method="post"
				class="form-horizontal" name="myForm" id="bookingConfirmed">
				<input type="hidden" name="flightQuotationRequestId" value="${flightQuotation.id}" /> 
				<input id="flightCityUrl" type="hidden" value="<s:text name="global.flightCitiesUrl" ></s:text>">
				<input id="flightairlineUrl" type="hidden" value="<s:text name="global.airlineUrl" ></s:text>">
				
				<input type="hidden" id="managementFeetax" value="<s:property value="managementFeeForBooking" />">
				<!-- harsha added colapse -->
				<div class="col-sm-12">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne"> Customer Details </a>
								</h4>
							</div>
							<!--/.panel-heading -->
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">

									<div class="col-sm-4">
										<div class="form-group">
											<label for="checkInDate" class=" control-label">Title
											</label> <select name="orderCustomer.title"
												class="form-control input-sm" required="required">
												<s:if test="flightQuotation.flightTravelRequest.title=='Mr'">
													<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
												</s:if>
												<s:if
													test="flightQuotation.flightTravelRequest.title=='Mrs'">
													<option value="Mr" >Mr</option>
													<option value="Mrs" selected="selected">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
												</s:if>
												<s:if
													test="flightQuotation.flightTravelRequest.title=='Miss'">
													<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss" selected="selected">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
												</s:if>
												<s:if
													test="flightQuotation.flightTravelRequest.title=='Ms'">
													<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss" >Miss</option>
													<option value="Ms" selected="selected">Ms</option>
													<option value="Master">Master</option>
												</s:if>
												<s:if
													test="flightQuotation.flightTravelRequest.title=='Master'">
													<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss" >Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master" selected="selected">Master</option>
												</s:if>


											</select>

										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<label for="City" class=" control-label">First Name </label>

											<input type="text" autocomplete="off"
												name="orderCustomer.firstName" class="form-control input-sm"
												required="required" placeholder="First Name"
												value="${flightQuotation.flightTravelRequest.firstName}">
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<label for="City" class=" control-label">Last Name </label> <input
												type="text" autocomplete="off" name="orderCustomer.lastName"
												class="form-control input-sm" required="required"
												placeholder="Last Name"
												value="${flightQuotation.flightTravelRequest.lastName}">
										</div>
									</div>
										<div class="col-sm-4">
										<div class="form-group">
											<label for="City" class="control-label">Booking Date</label> <input
												type="text" autocomplete="off" name="flightOrderRow.bookingDate"
												class="form-control input-sm" id="bookingDate"
												 readonly="readonly"    placeholder="Booking Date">
										</div>
									</div>
 								<s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}">
											<div class="col-sm-3">
							<div class="form-group">
								<label for="hotelName">Company Entity </label> 
								<select class="form-control input-sm" name="flightOrderRow.companyEntityId" id="entity" >
										 <s:iterator value="%{#session.Company.companyEntities}">
											<option value="<s:property value="companyEntityId"/>"
												selected>
												<s:property value="CompanyEntityName" /></option>
										</s:iterator>
									
										</select> 
							</div>
						</div>
  							</s:if>
									<!-- on click more options will open here -->


									<div class="row clearfix">
										<div class="collapse" id="filters" aria-expanded="true">

											<div class="panel-body">
												<div class="row">
													<!-- Filter of main info -->
													<div class=" col-sm-12 clearfix">

														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class=" control-label">Age </label> <input
																	type="text" autocomplete="off" name="orderCustomer.age"
																	class="form-control input-sm" placeholder="Age">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class="  control-label">Gender
																</label> <select class="form-control input-sm"
																	name="orderCustomer.gender">
																	<option value="M">Male</option>
																	<option value="F">Female</option>
																</select>

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class=" control-label">Phone </label>

																<input type="text" autocomplete="off"
																	name="orderCustomer.phone"
																	class="form-control input-sm" placeholder="Phone">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class="  control-label">Mobile
																</label> <input type="text" autocomplete="off"
																	name="orderCustomer.mobile"
																	class="form-control input-sm" placeholder="Mobile">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class=" control-label">Email </label>

																<input type="text" autocomplete="off"
																	name="orderCustomer.email"
																	class="form-control input-sm" placeholder="Email">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class="  control-label">Address
																</label> <input type="text" autocomplete="off"
																	name="orderCustomer.address"
																	class="form-control input-sm" placeholder="Address">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class=" control-label">Address2
																</label> <input type="text" autocomplete="off"
																	name="orderCustomer.address2"
																	class="form-control input-sm" placeholder="Address2">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class="control-label">City </label> <input
																	type="text" autocomplete="off"
																	name="orderCustomer.city" class="form-control input-sm"
																	placeholder="City">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class="  control-label">Zip
																	Code </label> <input type="text" autocomplete="off"
																	name="orderCustomer.zip" class="form-control input-sm"
																	placeholder="Zip Code">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class=" control-label">State </label>

																<input type="text" autocomplete="off"
																	name="orderCustomer.state"
																	class="form-control input-sm" placeholder="Type State">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class=" control-label">Country
																</label> <select class="form-control input-sm"
																	name="orderCustomer.countryId">
																	<option value="">Select Country</option>
																	<s:iterator value="countryList">
																		<option value="<s:property value="c_code"/>"><s:property
																				value="c_name" /></option>
																	</s:iterator>
																</select>

															</div>
														</div>
													</div>

												</div>
												<!--/.panel-body inner-->
											</div>
											<!--/.panel-collapse -->
										</div>
									</div>
									<!--/.col-12 -->
									<div class="clearfix more-details">
										<a class="btn btn-primary" role="button"
											data-toggle="collapse" href="#filters" aria-expanded="true"
											aria-controls="filters"> More options </a>
									</div>
								</div>
								<!--/.panel-body outer-->
							</div>
							<!-- /.panel-collapse -->

						</div>
						<!-- /.panel-default-outer -->




						<s:iterator begin="1" end="flightQuotation.passengerCount"
							status="loop">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a  data-toggle="collapse"
											data-parent="#accordion" href="#collapseTwo${loop.count}">
											<b>Passenger Details ${loop.count} <span style="color:red;">  (Quoted Amount : ${flightQuotation.totalAmount})</span> </b>
										</a>
									</h4>
								</div>

								<!--/.panel-heading -->
								<div id="collapseTwo${loop.count}"
									class="panel-collapse collapse in">
									<div class="panel-body">

										<div class="col-sm-3">
											<div class="form-group">
												<label for="currency" class="control-label">PassengerType
												</label> <select class="form-control input-sm"
													name="flightOrderCustomerList[${loop.count-1}].passengerTypeCode">
													<option value="ADT">Adult</option>
													<option value="INF">Infant</option>
													<option value="CHD">Child</option>
													
												</select>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<label for="hotelName" class=" control-label">Title
												</label> <select
													name="flightOrderCustomerList[${loop.count-1}].title"
													class="form-control input-sm">
													<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>

												</select>
											</div>
										</div>

										<div class="col-sm-3">
											<div class="form-group">
												<label for="hotelName" class=" control-label">FirstName
												</label> <input type="text" autocomplete="off"
													name="flightOrderCustomerList[${loop.count-1}].firstName"
													class="form-control input-sm" required="required"
													placeholder="First Name"
													value="${flightQuotation.flightTravelRequest.firstName}">

											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<label for="hotelChain" class="  control-label">LastName
												</label> <input type="text" autocomplete="off"
													name="flightOrderCustomerList[${loop.count-1}].lastName"
													class="form-control input-sm" required="required"
													placeholder="Last Name"
													value="${flightQuotation.flightTravelRequest.lastName}">

											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<label for="hotelChain" class="  control-label">Ticket No
												</label> <input type="text" autocomplete="off"
													name="flightOrderCustomerList[${loop.count-1}].eticketnumber"
													class="form-control input-sm" required="required"
													placeholder="Enter ticket no." id="tno${loop.count}"
													>

											</div>
										</div>
										
										<s:if test="!domesticOrInternationalCountryNameList[0].equalsIgnoreCase('India')  
										             || !domesticOrInternationalCountryNameList[1].equalsIgnoreCase('India')">
										<div class="col-sm-3">
											<div class="form-group">
												<label for="currency" class=" control-label">Date of
													Birth </label> <input type="text" autocomplete="off"
													class="form-control input-sm" id="dateofBirth${loop.count}"
													placeholder="Date of Birth " required="required"
													name="flightOrderCustomerList[${loop.count-1}].birthday">
											</div>
										</div>
										</s:if>
										
										<div class="col-sm-3">
											<div class="form-group">
												<label for="currency" class=" control-label">Gender
												</label> <select class="form-control input-sm"
													name="flightOrderCustomerList[${loop.count-1}].gender">
													<option value="M">Male</option>
													<option value="F">Female</option>

												</select>
											</div>
										</div>


										<%-- <div class="col-sm-12 clearfix more-details">
										<a class="btn btn-primary" role="button${loop.count}"
											data-toggle="collapse" href="#filters${loop.count}" aria-expanded="true"
											aria-controls="filters">
											Price Breakup(s)${loop.count}
										</a>
									</div> --%>
									
									<s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="travelRequestNumber" class="control-label">Travel Request Number</label>
									<input type="number" autocomplete="off"
										<%-- name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.trNumber" --%>
										name="travelRequestNoList[${loop.count-1}]"
										 class="form-control input-sm" required="required" placeholder="Travel Request Number">
								</div>
							</div>
						</s:if>
						<s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.costCenter || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="costCenter" class="control-label">Cost Center</label> <input type="text"
										autocomplete="off" name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.costCenter"
										class="form-control input-sm" required="required"
										placeholder="Cost Center">
								</div>
							</div>
						</s:if>
						 <%-- <s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}">
						<div class="col-sm-3">
							<div class="form-group">
								<label for="companyEntity" class="control-label">Company Entity </label>
								<select class="form-control input-sm" name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.costCenter" id="entity" >
								<option value="" selected="selected">Select Entity</option>
										 <s:iterator value="%{#session.Company.companyEntities}">
											<option value="<s:property value="companyEntityId"/>"
												 >
												<s:property value="CompanyEntityName" /></option>
										</s:iterator>
									 
										</select> 


							</div>
						</div>
						</s:if> --%>
							
							<c:if test="${fieldName[0]!=null}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[0]}</label> <input
										type="text" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField1"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField1}">
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[1]!=null}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[1]}</label> <input
										type="text" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField2"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField2}">
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[2]!=null}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[2]}</label> <input
										type="text" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField3"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField3}">
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[3]!=null}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[3]}</label> <input
										type="text" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField4"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField4}">
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[4]!=null}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="empCode" class="control-label"> ${fieldName[4]}</label> <input
										type="text" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField5"
										class="form-control input-sm"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField5}">
								</div>
							</div>
						</c:if>

 
						<c:if test="${rmConfigModel.department}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="empCode" class="control-label"> Department</label> <input type="text"
										autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.department" 
										class="form-control input-sm" required="required"
										placeholder="Enter department Details"
										value="<s:property value="configTripDetailsModel.department"/>">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.approverName}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="empCode" class="control-label">Approver Name</label> <input type="text"
										autocomplete="off" 
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.approverName" 
										class="form-control input-sm" required="required"
										placeholder="Enter approverName Details"
										value="${configTripDetailsModel.approverName}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.location}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="empCode" class="control-label">location</label> <input type="text"
										autocomplete="off" 
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.location" 
										class="form-control input-sm" required="required"
										placeholder="Enter location Details"
										value="${configTripDetailsModel.location}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.bussinessUnit}">
							<div class="col-sm-3">
								<div class="form-group" class="control-label">
									<label for="empCode">Bussiness Unit</label> <input type="text"
										autocomplete="off" 
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.bussinessUnit" 
										class="form-control input-sm" required="required"
										placeholder="Enter bussiness Unit Details"
										value="${configTripDetailsModel.bussinessUnit}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.projectCode}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="projectCode" class="control-label"> Project Code</label> <input
										type="text" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.projectCode" 
										class="form-control input-sm" required="required"
										placeholder="Enter projectCode Details"
										value="${configTripDetailsModel.projectCode}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.reasonForTravel}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="reasonForTravel" class="control-label">Reason For Travel</label> <input
										type="text" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.reasonForTravel" 
										class="form-control input-sm" required="required"
										placeholder="Enter reasonForTravel Details"
										value="${configTripDetailsModel.reasonForTravel}">
								</div>
							</div>
						</c:if>
						<c:if test="${rmConfigModel.billNonBill}">
							<div class="col-sm-3">
								<div class="form-group">
									<label for="billNonBill" class="control-label">Billing/Non Billing</label> <input
										type="text" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.billNonBill" 
										class="form-control input-sm" required="required"
										placeholder="Enter billNonBill Details"
										value="${configTripDetailsModel.billNonBill}">
								</div>
							</div>
						</c:if>

									

										<div class="col-sm-3 mopt">
											<div class="form-group">
												<label for="currency" class=" control-label">Nationality
												</label> <input type="text" class="form-control input-sm"
													name="flightOrderCustomerList[${loop.count-1}].nationality"
													placeholder="Nationality">
											</div>
										</div>
										<div class="col-sm-3 mopt">
											<div class="form-group">
												<label for="currency" class="  control-label">Passport
													Expire Date </label> <input type="text"
													class="form-control input-sm" id="expiredate${loop.count}"
													placeholder="Passport Expire Date"
													name="flightOrderCustomerList[${loop.count-1}].passport_expiryDate">
											</div>
										</div>
										<div class="col-sm-3 mopt">
											<div class="form-group">
												<label for="currency" class="control-label">Passport
													Number </label> <input type="text" class="form-control input-sm"
													placeholder="Passport Number"
													name="flightOrderCustomerList[${loop.count-1}].passportNo">
											</div>
										</div>
										<div class="col-sm-3 mopt">
											<div class="form-group">
												<label for="hotel Country" class=" control-label">Passport
													Issuing Country </label> <select class="form-control input-sm"
													name="flightOrderCustomerList[${loop.count-1}].passportIssuingCountry">
													<s:iterator value="countryList">
														<s:if test="c_name=='India'">
															<option value="<s:property value="c_name"/>"
																selected="selected"><s:property value="c_name" /></option>
														</s:if>
														<option value="<s:property value="c_name"/>"><s:property
																value="c_name" /></option>
													</s:iterator>
												</select>

											</div>
										</div>
										<div class="col-sm-3" style="margin-top: 25px;">

											<a class="btn btn-primary btn-xs" role="button"
												data-toggle="collapse" href="#" id="fil${loop.count}"
												aria-expanded="true" aria-controls="filters"> More
												options </a>

										</div>



										<div class="col-sm-12 clearfix pricebreakup">


											<a class="btn btn-success createdquotation collapsed"
												role="button${loop.count}" data-toggle="collapse"
												href="#filters${loop.count}" aria-expanded="true"
												aria-controls="filters"> Price Breakup(s)${loop.count} </a>
										</div>


										<div class="col-sm-12 clearfix pricebb price-details">
											<div class="row">
												<div class="collapse" id="filters${loop.count}"
													aria-expanded="true">
													<div class="panel-body">
														<!-- Filter of main info -->
														<div class=" col-sm-12 clearfix">
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">Currency
																	</label> <input type="text" readonly="readonly"
																		autocomplete="off"
																		value="	${flightQuotation.flightTravelRequest.currency}"
																		class="form-control input-sm" placeholder="Base Fare">
																</div>

															</div>
															<div class="col-sm-3">
																<div class="form-group"><%-- ${flightQuotation.totalAmount} --%>
																	<label for="hotelName" class=" control-label">Base
																		Fare </label> <input type="text" autocomplete="off"
																		id="baseFare${loop.count}"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].baseFare"
																		class="form-control input-sm" required="required"
																		value="0"
																		placeholder="Base Fare">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">YQ
																	</label> <input type="text" autocomplete="off"
																	 id="YQTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].YQTax"
																		class="form-control input-sm" required="required"
																		placeholder="YQTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">YR Tax
																	</label> <input type="text" autocomplete="off"
																	 id="YRTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].YRTax"
																		class="form-control input-sm" required="required"
																		placeholder="YRTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">WO Tax
																	</label> <input type="text" autocomplete="off"
																	 id="WOTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].WOTax"
																		class="form-control input-sm" required="required"
																		placeholder="WOTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">PSF Tax
																	</label> <input type="text" autocomplete="off"
																	 id="PSFTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].PSFTax"
																		class="form-control input-sm" required="required"
																		placeholder="PSFTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">UDF Tax
																	</label> <input type="text" autocomplete="off"
																	 id="UDFTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].UDFTax"
																		class="form-control input-sm" required="required"
																		placeholder="UDFTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">K3 Tax
																	</label> <input type="text" autocomplete="off"
																	 id="JNTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].K3Tax"
																		class="form-control input-sm" required="required"
																		placeholder="K3Tax">

																</div>
															</div>
															  
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">IN Tax
																	</label> <input type="text" autocomplete="off"
																	 id="INTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].INTax"
																		class="form-control input-sm" required="required"
																		placeholder="INTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">Transaction Fee
																	</label> <input type="text" autocomplete="off"
																	 id="transactionFee${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].transactionFee"
																		class="form-control input-sm" required="required"
																		placeholder="transactionFee">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">G1 Tax
																	</label> <input type="text" autocomplete="off"
																	 id="G1Tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].G1Tax"
																		class="form-control input-sm" required="required"
																		placeholder="G1Tax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">F2 Tax
																	</label> <input type="text" autocomplete="off"
																	 id="F2Tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].F2Tax"
																		class="form-control input-sm" required="required"
																		placeholder="F2Tax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">F6 Tax
																	</label> <input type="text" autocomplete="off"
																	 id="F6Tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].F6Tax"
																		class="form-control input-sm" required="required"
																		placeholder="F6Tax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">ZR Tax
																	</label> <input type="text" autocomplete="off"
																	 id="ZRTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].ZRTax"
																		class="form-control input-sm" required="required"
																		placeholder="ZRTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">YC Tax
																	</label> <input type="text" autocomplete="off"
																	 id="YCTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].YCTax"
																		class="form-control input-sm" required="required"
																		placeholder="YCTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">US Tax
																	</label> <input type="text" autocomplete="off"
																	 id="USTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].USTax"
																		class="form-control input-sm" required="required"
																		placeholder="USTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">XA Tax
																	</label> <input type="text" autocomplete="off"
																	 id="XATax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].XATax"
																		class="form-control input-sm" required="required"
																		placeholder="XATax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">XY Tax
																	</label> <input type="text" autocomplete="off"
																	 id="XYTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].XYTax"
																		class="form-control input-sm" required="required"
																		placeholder="XYTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">AY Tax
																	</label> <input type="text" autocomplete="off"
																	 id="AYTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].AYTax"
																		class="form-control input-sm" required="required"
																		placeholder="AYTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">XF Tax
																	</label> <input type="text" autocomplete="off"
																	 id="XFTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].XFTax"
																		class="form-control input-sm" required="required"
																		placeholder="XFTax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">Total Tax
																	</label> <input type="text" autocomplete="off"
																	 id="tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].tax"
																		class="form-control input-sm" required="required"
																		placeholder="Tax">

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="hotelName" class=" control-label">Markup
																	</label> <input type="text" autocomplete="off"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].markup"
																		id="markup${loop.count}" value="0"
																		class="form-control input-sm" required="required"
																		placeholder="Markup">

																</div>
															</div>
															
														</div>
													</div>
												</div>
											</div>
										</div>

									</div>

								</div>
							</div>
 
						</s:iterator>
											 
						<div class="panel panel-default">
							<div class="panel-heading">
							<div   style="color: red;display: none" id="balanceCheck">
												<h5 style="color: red">Total Booking Amount is more than Deposit Or Wallet Balances.Please Check.</h5>
											</div>
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapsetwo"> Trip Details </a>
								</h4>
								
							</div>
							<!--/.panel-heading -->
							<div id="collapsetwo" class="panel-collapse collapse">
								<div class="panel-body">
									<div id="tripdetails" class="clearfix">
									
									<div class="ctrip">
											<div class="form-group">
												<label for="NetRate" class=" control-label">Airline
												</label> <input type="text" autocomplete="on"
													name="flightOrderTripDetailList[0].operatedByName"
													class="form-control input-sm airline" required="required"
													value="${flightQuotation.flightTravelRequest.airlinePrefer}"
													placeholder="Flight Number"  id="airline">	
											</div>
										</div>

										<div class="ctrip">
											<div class="form-group">
												<label for="NetRate" class=" control-label">FlightNumber
												</label> <input type="text" autocomplete="off"
													name="flightOrderTripDetailList[0].flightNumber"
													class="form-control input-sm " required="required"
													placeholder="Flight Number"  id="flightNumber">	
											</div>
										</div>
									
										
										
										
										
										<div class="ctrip">
											<div class="form-group">
												<label for="NetRate" class=" control-label">Origin </label>

												<input type="text" autocomplete="on" id="origin"
													name="flightOrderTripDetailList[0].originName"
													class="form-control input-sm origin" required="required"
													placeholder="Origin Name" value="${flightQuotation.origin}">
											</div>
										</div>
										<div class="ctrip">
											<div class="form-group">
												<label for="NetRate" class=" control-label">Destination
												</label> <input type="text" autocomplete="on" id="destination"
													name="flightOrderTripDetailList[0].destinationName"
													class="form-control input-sm destination"
													required="required" placeholder="Destination Name"
													value="${flightQuotation.destination}">
											</div>
										</div>
										<div class="ctrip">
											<div class="form-group">
												<label for="NetRate" class=" control-label">Departure
													Date </label> <input type="text" autocomplete="off"
													id="departureDate"
													name="flightOrderTripDetailList[0].depDate"
													class="form-control input-sm dep datepicker_recurring_start"
													required="required" placeholder="Departure Date"
													value="${flightQuotation.transDepartureDate}">
											</div>
										</div>

										<div class="ctrip">
											<div class="form-group sm-4">
												<label for="NetRate" class=" control-label">Departure
													Time </label> <input type="text" autocomplete="off"
													id="departureTime" name="flightOrderTripDetailList[0].depTime" 
													class="form-control input-sm dtime" required="required"
													placeholder="HH:mm">
											</div>
										</div>
										
										<div class="ctrip">
											<div class="form-group">
												<label for="NetRate" class=" control-label">Arrival
													Date </label> <input type="text" autocomplete="off"
													id="arrivalDate"
													name="flightOrderTripDetailList[0].arrDate"
													class="form-control input-sm arr datepicker_recurring_start"
													required="required" placeholder="Arrival Date"
													value="${flightQuotation.transArrivalDate}">
											</div>
										</div>
										<div class="ctrip">
											<div class="form-group">
												<label for="NetRate" class=" control-label">Arrival
													Time </label> <input type="text" autocomplete="off"
													 id="arrivalTime"
													name="flightOrderTripDetailList[0].arrTime"
													class="form-control input-sm atime" required="required"
													placeholder="HH:mm">
											</div>
										</div>

										<%-- <s:if test="flightQuotation.tripType=='Round' || flightQuotation.tripType=='Multi'">
											
										
										</s:if> --%>

										<%-- <c:set var="salary" scope="session" value="${2000*2}"/>
										<c:if test="${salary > 2000}">
										   <p>My salary is: <c:out value="${salary}"/><p>
										</c:if>


										
										 --%>
										

									</div>
									<div id="add-trips"></div>
									<div class="col-sm-12 " style="margin-top: 20px;">
										<div class="row">

											<a class="btn btn-primary btn-sm" id="add_trip">Add Trip</a>
											<a class="btn btn-primary btn-sm" id="remove_trip">Remove
												Trip</a>
										</div>

									</div>
								</div>
								<!--/.panel-body outer-->
							</div>
							<input type="hidden"  id='tripDetailCount'>
							<!-- /.panel-collapse -->
						</div>
						<div class="panel panel-default" id="supplier">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a  data-toggle="collapse"
										data-parent="#accordion" href="#collapsesupplier">
										Supplier Amount Payable</a>
								</h4>
							</div>
							<!--/.panel-heading -->
							<div id="collapsesupplier" class="panel-collapse collapse in">
								<div class="panel-body">
 						
									
													<div class="form-group">
								<label for="Vendor" class="col-sm-2 control-label">Supplier </label>	
								<div class="col-sm-8">
								<select class="form-control input-sm" name="providerAPI" id="trainexpenseCurrency" required>
									<option value="" selected="selected">select Supplier</option>
								<s:iterator value="ApiProviders">
									<option value="<s:property value="vendorName"/>"><s:property value="vendorName" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>

									<%-- <div class="form-group">
										<label for="Vendor" class="col-sm-2 control-label">Supplier
										</label>
										<div class="col-sm-8">
											<select class="form-control input-sm" required="required"
												name="providerAPI">
												<option value="Tayyarah">Tayyarah</option>
												<option value="Expedia">Expedia</option>
												<option value="Desiya">Desiya</option>
												<option value="Expedia/Eem">Expedia/Eem</option>
												<option value="Offline Akbar">Offline Akbar</option>
												<option value="Akbar">Akbar</option>
												<option value="TBO">TBO</option>
												<option value="Booking.Com">Booking.Com</option>
											</select>
										</div>
									</div> --%>
									
									
									<div class="form-group">
										<label for="currency" class="col-sm-2 control-label">PNR
										  </label>
										<div class="col-sm-8">
											<input type="text" class="form-control input-sm"
												required="required" placeholder="PNR"
												  name="pnr"   id='pnr'>
										</div>
									</div>
									
									<div class="form-group">
										<label for="currency" class="col-sm-2 control-label">Confirmation Number
										  </label>
										<div class="col-sm-8">
											<input type="text" class="form-control input-sm"
												required="required" placeholder="Confirmation Number"
												  name="orderId" id='orderId'>
										</div>
									</div>
									
									<div class="form-group">
										<label for="currency" class="col-sm-2 control-label">Supplier
											Currency </label>
										<div class="col-sm-8">
											<select class="form-control input-sm" required="required"
												name="apiCurrency">
												<option selected="selected"
													value="${flightQuotation.flightTravelRequest.currency}">${flightQuotation.flightTravelRequest.currency}</option>
												<s:iterator value="countryList">
													<option value="<s:property value="cur_code"/>"><s:property
															value="cur_code" /></option>
												</s:iterator>

											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="currency" class="col-sm-2 control-label">Supplier
											To INR </label>
										<div class="col-sm-8">
											<input type="text" class="form-control input-sm"
												required="required" placeholder="Conversion Rate"
												value="1.0" name="apiToBaseExchangeRate">
										</div>
									</div>
									
										<div class="form-group">
											<label for="flightOrderRow.managementFee" class="col-sm-2 control-label">Management Fees
											</label> 
											<div class="col-sm-8">
											<input type="text" autocomplete="off"
													name="flightOrderRow.tempManagementFees"
													id="managementFees" value="<s:property value="managementFeeForBooking" />"
													class="form-control input-sm" required="required"
													placeholder="management Fees">
													</div>

											</div>
															

									<div class="form-group">
										<label for="currency" class="col-sm-2 control-label">Supplier
											PaymentType </label>
										<div class="col-sm-8">
											<select class="form-control input-sm"
												id="supplierPaymentType" name="supplierPaymentType">
												<option value="Full">Full Payment</option>
												<option value="Partial">Partial Payment</option>
												<option value="Zero">No Payment</option>
											</select>
										</div>
										<div class="col-sm-2 pull-right" >
											  <a  class="btn btn-info btn-xs" data-toggle="modal" href="#suppliermyNotification"><i class="fa fa-bell-o fa-sm"></i> Reminder</a>
										</div>
									</div>
									<div class="form-group supplierPayment" style="display: none" >
										<label for="currency" class="col-sm-2 control-label">Amount</label>
										<div class="col-sm-8">
											<input type="text" class="form-control input-sm"
												placeholder="amount" id="supplierAmount"
												name="paymentTransaction.apiProviderAmount">
										</div>

									</div>
									<div class="form-group supplierPayment" style="display: none">
										<label for="currency" class="col-sm-2 control-label">Paid
											By</label>
										<div class="col-sm-8">
											<input type="text" class="form-control input-sm"
												placeholder="Paid By"
												name="paymentTransaction.paidBySupplier">
										</div>

									</div>

									<div class="form-group card-details">
										<label for="currency" class="col-sm-2 control-label">CARD
											DETAILS </label>
									</div>

									<div class="form-group no-payment-mode">
										<label for="currency" class="col-sm-2 control-label">Payment
											Mode </label>
										<div class="col-sm-8">
											<select class="form-control input-sm"
												id="supplierPaymentMethod"
												name="apiProviderPaymentTransaction.payment_method" required>
												<option value="card">CARD</option>
												<option value="cash">CASH</option>
												<option value="online">ONLINE</option>

											</select>
										</div>
									</div>


									<div class="form-group supplier-comments" style="display: none">
										<label for="currency" class="col-sm-2 control-label">Comments
										</label>
										<div class="col-sm-8">
											<textarea class="form-control input-sm"
												placeholder="Type text"
												name="apiProviderPaymentTransaction.response_message"></textarea>
										</div>
									</div>



									<div class="form-group card-details">
										<label for="currency" class="col-sm-2 control-label">CardHolderName
										</label>
										<div class="col-sm-8">
											<select class="form-control input-sm"
												name="paymentTransaction.supplierCardHolderId">
												<option value="">Select card holder name</option>
												<s:iterator value="paymentCardList">
													<s:if test="paymentType==1">
														<option value="${id}">${userName}(${cardNumber})</option>
													</s:if>

												</s:iterator>



											</select>
										</div>
									</div>
									<!-- nested colapse happens here -->
									<!--/.panel-body -->
								</div>
								<!--/.panel-collapse -->
							</div>
						</div>
						<div class="panel panel-default" id="supplier">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a  data-toggle="collapse"
										data-parent="#accordion" href="#collapseClient"> Client Payment Receivable
										 </a>
								</h4>
							</div>
							<!--/.panel-heading -->
							<div id="collapseClient" class="panel-collapse collapse in">
								<div class="panel-body">

									<div class="form-group">
										<label for="currency" class="col-sm-2 control-label">Booking
											Currency </label>
										<div class="col-sm-8">
											<select class="form-control input-sm" required="required"
												name="bookingCurrency">
												<option selected="selected"
													value="${flightQuotation.flightTravelRequest.currency}">${flightQuotation.flightTravelRequest.currency}</option>
												<s:iterator value="countryList">
													<option value="<s:property value="cur_code"/>"><s:property
															value="cur_code" /></option>
												</s:iterator>
											</select>
										</div>
									</div>
									
											<%-- <div class="form-group">
												<label for="Convenience" class="col-sm-2 control-label">Convenience Fee
												 </label> <input type="text" autocomplete="off"
													id="convenienceFee"
													name="flightOrderRowServiceTax.convenienceFee"
													class="form-control input-sm" required="required"
													value="${flightOrderRowServiceTax.convenienceFee}"
													placeholder="Convenience Fee" readonly>

											</div>
											<div class="form-group">
												<label for="Management Fee" class="col-sm-2 control-label">Management Fee
												 </label> <input type="text" autocomplete="off"
													id="managementFee"
													name="flightOrderRowServiceTax.managementFee"
													class="form-control input-sm" required="required"
													value="${flightOrderRowServiceTax.managementFee}"
													placeholder="Management Fee"  readonly>

											</div>
											<div class="form-group">
												<label for="TotalAmount" class="col-sm-2 control-label">Total Payable
												 </label> <input type="text" autocomplete="off"
													id="totalAmount"
													name="totalAmount"
													class="form-control input-sm" required="required"
													value="${flightQuotation.totalAmount}"
													placeholder="Management Fee" readonly>

											</div> --%>

									<div class="form-group">
										<label for="currency" class="col-sm-2 control-label">INR
											To Booking </label>
										<div class="col-sm-8">
											<input type="text" class="form-control input-sm" value="1.0"
												required="required" placeholder="Conversion Rate"
												name="baseToBookingExchangeRate">
										</div>
									</div>
								 
					<div class="form-group">
										<label for="NetRate" class="col-sm-2 control-label">Payment
											Status </label>
										<div class="col-sm-8">
											<select class="form-control input-sm" required="required"
												name="paymentStatus" id="paymentStatus">
												<option value="Paid">Success</option>
												<option value="Pending">Pending</option>
											</select>
										</div>
									</div>


									<div class="form-group">
										<label for="NetRate" class="col-sm-2 control-label">Booking
											Status </label>
										<div class="col-sm-8">
											<select class="form-control input-sm" required="required"
												name="statusAction">
												<option value="Confirmed">Confirmed</option>
												<option value="Pending">Pending</option>
												<option value="Reserved">Reserved</option>
												<option value="Failed">Failed</option>
											</select>
										</div>
									</div>

									 
									<div class="form-group">
										<label for="currency" class="col-sm-2 control-label">Customer
											PaymentType </label>
										<div class="col-sm-8">
											<select class="form-control input-sm"
												name="customerPaymentType" id="clientPaymentType">
												<option value="Full">Full Payment</option>
												<option value="Partial">Partial Payment</option>
												<option value="Zero">No Payment</option>
											</select>
										</div>
										<div class="col-sm-2 pull-right" >
													 <a  class="btn btn-info btn-xs" data-toggle="modal" href="#clientmyNotification"><i class="fa fa-bell-o fa-sm"></i>Reminder</a>
													</div>
									</div>
									<div class="form-group clientPayment">
										<label for="currency" class="col-sm-2 control-label">Amount</label>
										<div class="col-sm-8">
											<input type="text" class="form-control input-sm"
												placeholder="amount" name="paymentTransaction.clientAmount">
										</div>
									</div>

									<div class="form-group clientPayment">
										<label for="currency" class="col-sm-2 control-label">Paid
											By</label>
										<div class="col-sm-8">
											<input type="text" class="form-control input-sm"
												placeholder="Paid By" name="paymentTransaction.paidByClient">
										</div>
									</div>

									
									<div class="form-group client-card-details">
										<label for="currency" class="col-sm-2 control-label">CARD
											DETAILS </label>

									</div>
									<div class="form-group no-client-payment-mode">
										<label for="currency" class="col-sm-2 control-label">Payment
											Mode </label>
										<div class="col-sm-8">
											<select class="form-control input-sm"
												id="clientPaymentMethod"
												name="paymentTransaction.payment_method" required>
												<option value="card">CARD</option>
												<option value="cash">CASH</option>
												<option value="online">ONLINE</option>

											</select>
										</div>
									</div>
									<div class="form-group client-comments" style="display: none">
										<label for="currency" class="col-sm-2 control-label">Comments
										</label>
										<div class="col-sm-8">
											<textarea class="form-control input-sm"
												placeholder="Type text"
												name="paymentTransaction.response_message"></textarea>
										</div>
									</div>


									<div class="form-group client-card-details">
										<label for="currency" class="col-sm-2 control-label">CardHolderName
										</label>
										<div class="col-sm-8">
											<select class="form-control input-sm"
												name="paymentTransaction.clientCardHolderId">
												<option value="">Select card holder name</option>
												<s:iterator value="paymentCardList">
													<s:if test="paymentType==0">
														<option value="${id}">${userName}(${cardNumber})</option>
													</s:if>

												</s:iterator>


 
											</select>
										</div>
									</div>
									<!-- nested colapse happens here -->
									<!--/.panel-body -->
								</div>
								<!--/.panel-collapse -->
							</div>
						</div>

						<!-- /.panel-default-outer -->

						<div class="form-group text-center" style="margin-top: 20px;">
							<div class="col-xs-12 submitWrap text-center">
								<button type="button" id="buttonSubmit"  class="btn btn-primary btn-lg">Book Now
									 </button>
							</div>
						</div>



					</div>
					<!--/.panel-group-->

				</div>
				<!--/.col-12 outer-panel-->
			</form>

			<!-- addeded details collapse div end -->


		</section>

	</div>

	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	
	<script>
	var totalAmount= ${flightQuotation.totalAmount};
	$('#supplierFromDate').datepicker({
		//showTimePicker: false,
		minDate : 0,
		dateFormat : 'dd-mm-yy',
		onSelect : function(selectedDate) {
			var date2 = $("#supplierFromDate").datepicker('getDate', '+1d');
			date2.setDate(date2.getDate());
			$("#supplierToDate").datepicker('setDate', date2);
			$("#supplierToDate").datepicker("option", "minDate", date2);
		},
		onClose : function() {
			$("#supplierToDate").focus();
		}

	}).datepicker("setDate", new Date());

	$('#supplierToDate').datetimepicker({
		dateFormat : 'dd-mm-yy',
		minDate : 0,
		onSelect : function(selectedDate, i) {
			// $("#fromDate").datepicker("option", selected)
			var todate = $(this).val();

		}

	}).datepicker("setDate", new Date());

	$("#supplierDueDate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate : 0,
	/*  onClose: function() {
	     $("#paidDate").focus();
	 } */
	});

	$("#supplierPaidDate").datepicker({
		dateFormat : "dd-mm-yy",
	});
	$("#departureTime").timepicker({
		dateFormat : "HH:mm",
	});
	$("#arrivalTime").timepicker({
		dateFormat : "HH:mm",
	});
	

	
	$('#clientFromDate').datepicker({
		//showTimePicker: false,
		minDate : 0,
		dateFormat : 'dd-mm-yy',
		onSelect : function(selectedDate) {
			var date2 = $("#clientFromDate").datepicker('getDate', '+1d');
			date2.setDate(date2.getDate());
			$("#clientToDate").datepicker('setDate', date2);
			$("#clientToDate").datepicker("option", "minDate", date2);
		},
		onClose : function() {
			$("#clientToDate").focus();
		}

	}).datepicker("setDate", new Date());

	$('#clientToDate').datetimepicker({
		dateFormat : 'dd-mm-yy',
		minDate : 0,
		onSelect : function(selectedDate, i) {
			// $("#fromDate").datepicker("option", selected)
			var todate = $(this).val();

		}

	}).datepicker("setDate", new Date());

	$("#clientDueDate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate : 0,
	/*  onClose: function() {
	     $("#paidDate").focus();
	 } */
	});

	$("#clientPaidDate").datepicker({
		dateFormat : "dd-mm-yy",
	});
	 
	function sendSupplierAndClientNotification(type){
		console.log(type);
		 var transFromDate=null;
	      var transToDate= null;
	      var transPaidDate=null;
	      var transDueDate= null;
	      var comments= null;
	      var toEmails= null;
	      var ccEmails= null;
	 
	 var orderId= $('#orderReference').val();
    	if(type=='supplier'){
    	transFromDate= $('#supplierFromDate').val();
		     transToDate= $('#supplierToDate').val();
		      transPaidDate= $('#supplierPaidDate').val();
		      transDueDate= $('#supplierDueDate').val();
		      comments= $('#supplierComment').val();
		      toEmails= $('#supplierToEmails').val();
		      ccEmails= $('#supplierCcEmails').val();  
		  
	}
    	else if (type == 'client') {
		transFromDate= $('#clientFromDate').val();
		     transToDate= $('#clientToDate').val();
		      transPaidDate= $('#clientPaidDate').val();
		      transDueDate= $('#clientDueDate').val();
		      comments= $('#clientComment').val();
		      toEmails= $('#clientToEmails').val();
		      ccEmails= $('#clientCcEmails').val();  
	}

	var txt1 ="";
	 if(orderId==''){
			alert("Please Provide Confirmation Number");
		}
			else{
	 	$.ajax({	
	        url: 'insertFlightCustomerPaymentTxHistory',
	        type: 'post',
	        data:{type:type,transFromDate:transFromDate,transToDate:transToDate,transPaidDate:transPaidDate,transDueDate:transDueDate,comments:comments,orderId:orderId,toEmails:toEmails,ccEmails:ccEmails},
	        success:function(data){
	         	  if(data.paymentMap.status=='supplier')
	            	{ 
	            		$("#supplierForm").hide();
	                	$(".modal-footer").hide(); 
	            	 txt1 = $("<p class='text-center clearfix tt1' style='color:green'></p>").text(" Successfully supplier reminder have been set.");
	            	  $("#suppliermyNotification .modal-body").append(txt1);  
	            	  $("#supplierForm")[0].reset(); 
	            	 setTimeout(function() {
	            		 $('#suppliermyNotification').modal('hide'); 
	            	 },1000);  
	            	 $('#suppliermyNotification').on('hidden.bs.modal', function () { 
	            		 $("#supplierForm").show();
		                	$(".modal-footer").show();
		                	$(".tt1").hide(); 
		                	$('#supplierFromDate').datepicker("setDate", new Date());
		                	$('#supplierToDate').datepicker("setDate", new Date());  
	            	});
	            	} 
	            
	            else if(data.paymentMap.status=='client'){ 
	        	$("#clientForm").hide();
	            	$(".modal-footer").hide();
	                txt1 = $("<p class='text-center clearfix tt1' style='color:green'></p>").text(" Successfully client reminder have been set");
	        	   //txt1 = "Successfully client reminder have been set.";
	        	 
	        	$("#clientmyNotification .modal-body").append( txt1);  
	        	 $("#clientForm")[0].reset(); 
	        	 setTimeout(function() {
	        		 $('#clientmyNotification').modal('hide'); 
	        	 },1000); 
	        	 $('#clientmyNotification').on('hidden.bs.modal', function () { 
            		   $("#clientForm").show();
            		   $(".modal-footer").show();
            		   $(".tt1").hide(); 
	                	$('#clientFromDate').datepicker("setDate", new Date());
	                	$('#clientToDate').datepicker("setDate", new Date());
	                	 
	                	 
	                	  
            	});
	        	 
	        	}
	        else{
	          txt1 = "We found some error......";
	          if(type==client){
		       		$("#clientmyNotification .modal-body").append( txt1);
		        	setTimeout(function() {$('#clientmyNotification').modal('hide');}, 1000);
		          
		        }else{
	          
	        	$("#suppliermyNotification .modal-body").append(txt1);
	        	setTimeout(function() {$('#clientmyNotification').modal('hide');}, 1000);
	        	
		        }
	        	
	        	
	        }
	       
	            }
	        
	    });
	}  

}

	 $('.price-details').on('keyup input', function () {
		   addtax(); 
		  });
	function addtax(){
		 var passengerCount= ${flightQuotation.passengerCount};
		 var taxvar=parseInt(0);
		 var markupvar=parseInt(0);
		 var baseFairvar=parseInt(0);
		 
		 var  yqTax=parseInt(0);
		 var yrTax=parseInt(0);
		 var  psfTax=parseInt(0);
		 var udfTax=parseInt(0);
		 var woTax=parseInt(0);
		 var jnTax=parseInt(0);
		 var inTax=parseInt(0);
		 var g1Tax=parseInt(0);
		 var f2Tax=parseInt(0);
		 var f6Tax=parseInt(0);
		 var zrTax=parseInt(0);
		 var ycTax=parseInt(0);
		 var usTax=parseInt(0);
		 var xaTax=parseInt(0);
		 var xyTax=parseInt(0);
		 var ayTax=parseInt(0);
		 var xfTax=parseInt(0);
		 var ayTax=parseInt(0);
		 for(var i=1;i<=passengerCount;i++){
				 taxvar=taxvar+Math.round(parseFloat($('#tax'+i).val())*100)/100;
				 markupvar=markupvar+Math.round(parseFloat($('#markup'+i).val())*100)/100;
				 baseFairvar=baseFairvar+Math.round(parseFloat($('#baseFare'+i).val())*100)/100;
				 yqTax=yqTax+Math.round(parseFloat($('#YQTax'+i).val())*100)/100;
				 yrTax=yrTax+Math.round(parseFloat($('#YRTax'+i).val())*100)/100;
				 woTax=woTax+Math.round(parseFloat($('#WOTax'+i).val())*100)/100;
				 psfTax=psfTax+Math.round(parseFloat($('#PSFTax'+i).val())*100)/100;
				 udfTax=udfTax+Math.round(parseFloat($('#UDFTax'+i).val())*100)/100;
				 jnTax=jnTax+Math.round(parseFloat($('#JNTax'+i).val())*100)/100;
				 inTax=inTax+Math.round(parseFloat($('#INTax'+i).val())*100)/100;
				 g1Tax=g1Tax+Math.round(parseFloat($('#G1Tax'+i).val())*100)/100;
				 f2Tax=f2Tax+Math.round(parseFloat($('#F2Tax'+i).val())*100)/100;
				 f6Tax=f6Tax+Math.round(parseFloat($('#F6Tax'+i).val())*100)/100;
				 zrTax=zrTax+Math.round(parseFloat($('#ZRTax'+i).val())*100)/100;
				 ycTax=ycTax+Math.round(parseFloat($('#YCTax'+i).val())*100)/100;
				 usTax=usTax+Math.round(parseFloat($('#USTax'+i).val())*100)/100;
				xaTax=xaTax+Math.round(parseFloat($('#XATax'+i).val())*100)/100;
				 xyTax=xyTax+Math.round(parseFloat($('#XYTax'+i).val())*100)/100;
				xfTax=xfTax+Math.round(parseFloat($('#XFTax'+i).val())*100)/100;
				ayTax=ayTax+Math.round(parseFloat($('#AYTax'+i).val())*100)/100;
				 
			} 
		  
		var totalBookingAmount1=taxvar+markupvar+baseFairvar+yqTax+yrTax+psfTax+udfTax+woTax+jnTax+inTax+g1Tax+f2Tax+f6Tax+zrTax+ycTax+usTax+xaTax+xyTax+ayTax+xfTax+ayTax;
		var depositBalance =${userWallet.depositBalance};
		var walletBalance =${userWallet.walletbalance};
		if (depositBalance >=totalBookingAmount1) {
			$('.submitWrap').show();
			$('#balanceCheck').hide();
		}
		else if (walletBalance>=totalBookingAmount1) {
			$('.submitWrap').show();
			$('#balanceCheck').hide();
		}
		else{
			$('.submitWrap').hide();
			$('#balanceCheck').show();
		}
	}
	 
	$('#buttonSubmit').click(function() {
		  var passengerCount= ${flightQuotation.passengerCount};
	   var pnr =$('#pnr').val();
	   var orderId =$('#orderId').val();
	   var flightNumber =$('#flightNumber').val();
	   var airline =$('#airline').val();
	   var departureTime =$('#departureTime').val();
	   var  tripDetailCount=$ ('#tripDetailCount').val();
	   var bookingDate =$('#bookingDate').val();
	   var arrivalTime=$('#arrivalTime').val();
		  var arrivalDate=$('#arrivalDate').val();
	 /*  if(passengerCount>0){ */
		   for(var i=1;i<=passengerCount;i++){
				  var tno=$('#tno'+i).val();
					var taxvar=Math.round(parseFloat($('#tax'+i).val())*100)/100;
					var markupvar=Math.round(parseFloat($('#markup'+i).val())*100)/100;
					var baseFairvar=Math.round(parseFloat($('#baseFare'+i).val())*100)/100;
					var totalCalculation=taxvar+markupvar+baseFairvar;
					
					if(tno==''){
						 alert('please provide Ticket Number  for passenger '+i+'');
						 
					}
					/*  if(totalCalculation >totalAmount){
						 alert('Entered (base fare + tax + markup) for passenger '+i+' is greater than Quoted Amount');
						 break;
					 } */
					  
				} 
	   /* } */
		 if(flightNumber==''){
		  alert("Please provide flightNumber");
	  }
		 if(airline==''){
			  alert("Please provide Airline");
		  }
	  else if(departureTime==''){
		  alert("Please enter the departureTime");
	  }
	 
	  else if(arrivalDate==''){
			 alert('please type arrivalDate');
		}
	   
	  else if(arrivalTime==''){
			 alert('please type arrivalTime');
		}
	   else if(bookingDate==''){
				 alert("Please provide bookingDate");
			  }
	  else{ 
		  var count=0;
		   for(var i=1;i<=tripDetailCount;i++){
			   count++;
				  var flightNumber=$('#flightNumber'+i).val();
				  var airline=$('#airline'+i).val();
				  var origin=$('#origin'+i).val();
				  var destination=$('#destination'+i).val();
				  var departureDate=$('#departureDate'+i).val();
				  var departureTime=$('#departureTime'+i).val();
				  var arrivalTime=$('#arrivalTime'+i).val();
				  var arrivalDate=$('#arrivalDate'+i).val();
					if(flightNumber==''){
						 alert('please provide flightNumber  for trip '+i+'');
						 break;
					}
					if(airline==''){
						 alert('please provide airline  for trip '+i+'');
						 break;
					}
					 if(origin==''){
						 alert('please type origin  for trip '+i+'');
						 break;
					}
					 if(destination==''){
						 alert('please type destination  for trip '+i+'');
						 break;
					}
					 if(departureDate==''){
						 alert('please type departureDate  for trip '+i+'');
						 break;
						 
					}
				 	if(departureTime==''){
						 alert('please type departureTime  for trip '+i+'');
						 break;
						 
					}
					 if(arrivalTime==''){
						 alert('please type arrivalTime  for trip '+i+'');
						 break;
						 
					}
				  if(arrivalDate==''){
						 alert('please type arrivalDate  for trip '+i+'');
						 break;
					}
				  count=0;
		  }
		   
		  if(count==0){
			 if(pnr=='' || orderId==''){
					 alert("Please provide PNR and Confirmation Number");
				  }
			 else{
				 $("#bookingConfirmed").submit();
			 }
			 
		  } 
	  }
	   
	})
	</script>
	
	<script type="text/javascript">
		$(function() {
			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			//var finalUrl = newUrl+"getFlightQuotationList?flightQuotationRequestId="+${flightQuotation.flightTravelRequest.id};
			var finalUrl = newUrl + "getFlightQuotationList";
			$('#success').click(function() {
				window.location.assign(finalUrl);
				$('#success-alert').hide();

			});
			$('#cancel').click(function() {
				$('#error-alert').hide();

			});

		});

		$('#add_trip').on(
						'click',
						function() {
							var i;
							$('#tripdetails:first').clone().appendTo(
									'#add-trips');
							var j = $('#add-trips #tripdetails').length;
						$ ('#tripDetailCount').val(j)
							var cl = $('#add-trips #tripdetails:last');
							 
						  var airlinesList = [];
							 $.ajax({
									url :"airlinesJson",
									dataType : "json",
									success : function(data, textStatus, jqXHR) {
										for (var i = 0; i < data.airlist.length; i++){
											var airlineName=data.airlist[i].airlinename;
											if(airlineName!=null) 
												airlinesList.push(airlineName);
											
										}
										airlinesList = Array
										.from(new Set(
												airlinesList));
									 },error : function(jqXHR, textStatus, errorThrown) {
											
										}
							 });
							 
						 
						
						 	var flightCityList = [];
							$
									.ajax({
										url : $("#flightCityUrl").val(),
										type : 'GET',
										dataType : 'json',
										success : function(data) {
											console.log(data.length)
											for (var i = 0; i < data.length; i++) {
												flightCityList
														.push(data[i].city
																+ ","
																+ data[i].country
																+ ",("
																+ data[i].airport_code
																+ ")");
											}
											flightCityList = Array
													.from(new Set(
															flightCityList));
										},
										error : function(jqXhr, textStatus,
												errorThrown) {
											console.log("Error---"
													+ errorThrown
													+ "-------Status----------"
													+ textStatus);
										}
									});
							  
							cl.find("input").val("");
							cl.find("input.dep ").attr("id",
									"departureDate" + j).removeClass(
									'hasDatepicker').removeClass(
									'hasDatepicker').unbind().datepicker(
									{
										dateFormat : "dd-mm-yy",
										/* minDate : 0, */
										onSelect : function(selectedDate) {
											var date2 = $("#departureDate" + j)
													.datepicker('getDate',
															'+1d');
											date2.setDate(date2.getDate() + 1);
											$("#arrivalDate" + j).datepicker(
													'setDate', date2);
										},
										onClose : function() {
											$("#arrivalDate" + j).focus();
										}
									});
							cl.find("input.arr ").attr("id", "arrivalDate" + j)
									.removeClass('hasDatepicker').unbind()
									.datepicker(
											{
												dateFormat : "dd-mm-yy",
												/* minDate : 1, */
												onSelect : function(selected) {
													$("#departureDate" + j)
															.datepicker(
																	"option",
																	selected)
												}
											});
							cl.find("input.atime ").attr("id", "arrivalTime" + j)
							.removeClass('hasDatepicker').unbind()
							.timepicker(
									{
										dateFormat : "HH:mm",
										minDate : 1,
										 
									});
							cl.find("input.dtime ").attr("id", "departureTime" + j)
							.removeClass('hasDatepicker').unbind()
							.timepicker(
									{ 
										dateFormat : "HH:mm",
										
									});
							
							
							 cl.find("input.airline ")
							.attr("id", "airline" + j)
							.removeClass('ui-autocomplete-input')
							.unbind()
							.autocomplete(
									{
										source : function(request,
												response) {
											var matcher = new RegExp(
													$.ui.autocomplete
															.escapeRegex(request.term),
													"i");
											response($
													.grep(
															airlinesList,
															function(
																	item) {
																return matcher
																		.test(item);

															}));
										}
									}); 
							
							
							cl.find("input.origin ")
									.attr("id", "origin" + j)
									.removeClass('ui-autocomplete-input')
									.unbind()
									.autocomplete(
											{
												source : function(request,
														response) {
													var matcher = new RegExp(
															$.ui.autocomplete
																	.escapeRegex(request.term),
															"i");
													response($
															.grep(
																	flightCityList,
																	function(
																			item) {
																		return matcher
																				.test(item);

																	}));
												}
											});

							cl.find("input.destination ")
									.attr("id", "destination" + j)
									.removeClass('ui-autocomplete-input')
									.unbind()
									.autocomplete(
											{
												source : function(request,response) {
													var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term),"i");
													response($.grep(flightCityList,
																	function(item) {
																		return matcher.test(item);

																	}));
												}
											});
							/* .attr("id", "origin"+ j) */

							$( '#add-trips #tripdetails:last .ctrip .form-group input[name*=flightOrderTripDetailList]')
									.each(
											function() {
												var name = $(this).attr('name');
												var id = $(this).attr('id');
												var indexOfThirdBracket = name
														.indexOf('[') + 1;
												name = name.substring(0,
														indexOfThirdBracket)
														+ j
														+ name.substring(indexOfThirdBracket + 1);
												$(this).attr('name', name);
												
												if(id=='flightNumber'){
													$(this).attr('id', id+j);
												}
												if(id=='airline'){
													$(this).attr('id', id+j);
												}
												if(id=='departureTime'){
													$(this).attr('id', id+j);
												}
												if(id=='arrivalTime'){
													$(this).attr('id', id+j);
												}
												if(id!='flightNumber'){
													$(this).attr('value','');
												}
												if(id!='airline'){
													$(this).attr('value','');
												}
												 
												 
											});

						});

		$('#remove_trip').click(function() {
			$('#add-trips > #tripdetails:last').remove();
			 
			var count=	$ ('#tripDetailCount').val();
			if(count>0){
				count=count-1;
				$ ('#tripDetailCount').val(count);
			}
			else{
				$ ('#tripDetailCount').val(count);
			}
			 
		});

		var s = $('#accordion > .panel-default').length;

		for (j = 1; j < s - 1; j++) {
			$('.mopt').hide();
			$('#fil' + j + '').on('click', function() {
				$('.mopt').toggle();
			});
		}

		$("#departureDate ").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			//minDate : 0,
			onSelect : function(selectedDate) {
				var date2 = $("#departureDate").datepicker('getDate', '+1d');
				date2.setDate(date2.getDate() + 1);
				$("#arrivalDate").datepicker('setDate', date2);
			},

			onClose : function() {
				$("#arrivalDate").focus();
			}
		});
		$("#arrivalDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			//minDate : 1,
			onSelect : function(selected) {
				$("#departureDate").datepicker("option", selected)
			}

		});

		
		$("#bookingDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd"
		}).datepicker("setDate", new Date());
		
		
		
		$("#dateofBirth1").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#dateofBirth2").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#dateofBirth3").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#dateofBirth4").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#dateofBirth5").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#dateofBirth6").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#dateofBirth7").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#dateofBirth8").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#dateofBirth9").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#expiredate1").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate2").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#expiredate3").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate4").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate5").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#expiredate6").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#expiredate7").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate8").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate9").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#arrivalDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#departureDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	</script>
	<script type="text/javascript">
	$(document).ready(function() {
		var totalPassenger=${flightQuotation.passengerCount};
		var managementFees=${managementFeeForBooking};
	$("#managementFees").val(totalPassenger*managementFees);
	});
	</script>

</body>
</html>