<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Quick Book</title>
 
<link rel="stylesheet" href="css/alert.css">


<%--  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.1/css/bootstrapValidator.min.css">
 <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.1/js/bootstrapValidator.min.js"></script> --%>
 
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
 
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

		$('.supplierPayment').hide();
		$('.card-details').hide();
		$('.no-payment-mode').hide();
		$('#supplierPaymentType').change(function() {
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
			
		});
		
		
		$('.clientPayment').hide();
		$('.client-card-details').hide();
		$('.no-client-payment-mode').hide();
		$('#clientPaymentType').change(function() {
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
		/* if (clientPaymentType == 'Partial') {
			$('.clientPayment').show();
			$('.client-card-details').hide();
			document.getElementById('paymentStatus').value = "Paid";

		} else {
			$('.clientPayment').hide();
			$('.client-card-details').show();
		}
 */

		
	});
	
	
	 
</SCRIPT>
<style type="text/css">
.error {
    color:red;
}
.valid {
    color:green;
}



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
#mandatory {
	color: red;
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}
#passengerNested{ 
 padding: 1px 10px;
} 
.mopt{
display: none;

}

.pricebb{
width: 100%;
}
.price-details label{
font-size: 11px; 
}
#bookingConfirmed .form-group{ 
    padding-right: 5px;
}
 
 /* enable absolute positioning */
.inner-addon {
  position: relative;
}

/* style glyph */
.inner-addon .glyphicon {
  position: absolute;
  padding: 5px 3px 4px 2px;
  pointer-events: none;
}

/* align glyph */
.left-addon .glyphicon  { left:  0px;}
.right-addon .glyphicon { right: 0px;}

/* add padding  */
.left-addon input  { padding-left:  30px; }
.right-addon input { padding-right: 30px; }


 .form-control-feedback{
  display: none;
}

.form-horizontal .has-feedback .form-control-feedback {
    right: 12px !important;
}

.has-feedback label ~.form-control-feedback {
    top: 21px !important;
}
</style>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header"> 
					<div class="row">
			<div class="col-sm-2 clearfix pull-left"> 
				<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-plane"></i> Flight Booking</h3>
			</div>
		<div class="col-sm-10 clearfix pull-right " data-placement="top">	
			<div class="row">
			<div class="col-sm-12 clearfix pull-right " data-placement="top">
				<div class="row">
					<div class="col-sm-8 clearfix " data-placement="top">
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
					<div class="col-sm-2 clearfix  " data-placement="top">
						<div class="myDropdown">
							<button class="btn btn-top-link btn-xs dropdown-toggle"
								type="button" data-toggle="dropdown">
								Create Quick Trip <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="goQuickLinkOfflibeHotelBookingPage">Hotel</a></li>
								<li><a href="goQuickLinkOfflibeFlightBookingPage">Flight</a></li>
								<!-- <li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>	
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>	 -->					
							</ul>
						</div>
					</div>
					<div class="col-sm-1 clearfix  " data-placement="top">
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
		</div>	
		</div>

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
			<!-- createFlightOfflineBooking -->

			<form action="test" method="post" id="bookingConfirmed" class="form-horizontal" name="myForm" >
				<input type="hidden" name="flightQuotationRequestId" value="${flightQuotation.id}" /> 
				<input type="hidden" name="tripId" value="${tripId}" /> 
				<input id="flightCityUrl" type="hidden" value="<s:text name="global.flightCitiesUrl" ></s:text>">
				<input id="flightairlineUrl" type="hidden" value="<s:text name="global.airlineUrl" ></s:text>">
				
				<input type="hidden" id="managementFeetax" value="<s:property value="managementFeeForBooking" />">
				<input type="hidden" id="managementFeetaxdom" value="<s:property value="managementFeeForBookingDomManagemtFee" />">
				<input type="hidden" id="managementFeetaxintl" value="<s:property value="managementFeeForBookingIntManagemtFee" />">
				<input type="hidden" id="companyRole"
												value="<s:property value="#session.Company.companyRole.isCorporate()" />">
										<input  name="companyId" type="hidden" id="companyId" value="<s:property value="#session.User.Companyid" />">		
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
								<%-- <s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.empCode || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							
						</s:if>
						<s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							
						</s:if>
						<s:if
							test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.costCenter || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							
						</s:if>
						 --%>
					
									<div class="col-sm-3">
							<div class="form-group has-feedback">
								<label for="tripType">Trip Type</label> <select name="flightTravelRequest.tripType"
									class="form-control input-sm " id="tripT" required>
									<!-- <option value="">Select Trip Type</option> -->
									<option value="O" selected="selected">One Way</option>
									<option value="R">Round Trip</option>
									 <option value="M">Multi Trip</option>
								</select>
								<span class="form-control-feedback glyphicon glyphicon-ok"></span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group has-feedback">
											<label for="flightOrderRow.managementFeesdummy">Product Type<span
														id="mandatory"> * </span></label> 
											<select class="form-control input-sm productTypeVal"
												id="managementFeesForSend" name="flightOrderRow.managementFeesdummy" required onchange="getmangmentfee(this)"> 
												<option value="00.00" selected="selected">Select Product Type</option>
												<option value="<s:property value="managementFeeForBookingDomManagemtFee"/>">Domestic</option>
												<option value="<s:property value="managementFeeForBookingIntManagemtFee"/>">International</option>
												<option value="0.000000">Domestic Reissue</option>
												<option value="0.000000">International Reissue</option>
											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>

											</div>
						
								 <input
									type="hidden" autocomplete="off" name="flightTravelRequest.passengerCount"
									class="form-control input-sm" required="required" id="PassengerCountNew"
									placeholder="Passenger Count" value="1">
							
								
										<div class="col-sm-3">
										<div class="form-group has-feedback">
											<label for="City" class="control-label">Booking Date</label> <input
												type="text" autocomplete="off" name="flightOrderRow.bookingDate"
												class="form-control input-sm" id="bookingDate"
												 readonly="readonly"    placeholder="Booking Date" required="required">
												 <span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
									<s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}"> 
									<div class="col-sm-3">
														<div class="form-group has-feedback">
															<label for="carTravelRequest.entity" class=" control-label">
																Company Entity </label> 
														<select class="form-control input-sm" required onchange="getEntity(this)" name="flightOrderRow.companyEntityId" id="entity" >
									<option value="" selected="selected">Select Entity</option>
										 <s:iterator value="%{#session.Company.companyEntities}">
											<option value="<s:property value="companyEntityId"/>"
												 >
												<s:property value="CompanyEntityName" /></option>
										</s:iterator>
									 
										</select> 	 	 
										<span class="form-control-feedback glyphicon glyphicon-ok"></span>
																
														</div>
													</div>
									</s:if>

									
									
								</div>
								<!--/.panel-body outer-->
							</div>
							<!-- /.panel-collapse -->

						</div>
						
						<!-- /.panel-default-outer -->

							<s:iterator begin="1" end="1" status="loop">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a  data-toggle="collapse"
											data-parent="#accordion" href="#collapsePassengerDetails">
											<b>Passenger Details   <span style="color:red;"> (Management Fee: </span><input type="text"  style="color: red;font-size: 16px;width: 102px;" 
														disabled="disabled" id="quoteamount" value="0.00">  <span>)</span> </b>
										</a>
									</h4>
								</div>

								<!--/.panel-heading -->
								<div id="collapsePassengerDetails"
									class="panel-collapse collapse in">
									
									<div class="panel-body" > 
                       		 <div class="panel-group" id="passengerNested">
								<div class="panel panel-default" style="border: 1px solid #ccc;" id="passlength">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a  data-toggle="collapse"
											data-parent="#passengerNested" href="#collapseinside">
											<b>Passenger Details ${loop.count} <%-- <span style="color:red;">(Quoted Amount :
											<input type="hidden" autocomplete="off" name="quoteamount" id="quoteamount" class="form-control input-sm" required="required" value="">
													  </span> --%> </b>
										</a>
									</h4>
								</div>
									<div id="collapseinside" class="panel-collapse collapse in">
									<div class="panel-body passval">
<div class="detailswithRM"> 	 
										<div class="col-sm-1">
											<div class="form-group has-feedback">
												<label for="currency" class="control-label">PassengerType
												</label> <select class="form-control input-sm"
													name="flightOrderCustomerList[${loop.count-1}].passengerTypeCode">
													<option value="ADT">Adult</option>
													<option value="INF">Infant</option>
													<option value="CHD">Child</option>
													<option value="MASTER">Master</option>

												</select>
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="col-sm-1">
											<div class="form-group has-feedback">
												<label for="hotelName" class=" control-label">Title
												</label> <select
													name="flightOrderCustomerList[${loop.count-1}].title"
													class="form-control input-sm" id="title1">
													<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>

												</select>
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>

										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="hotelName" class=" control-label">FirstName
												</label> <input type="text" autocomplete="off"
													name="flightOrderCustomerList[${loop.count-1}].firstName"
													class="form-control input-sm " required="required" id="firstName"
													placeholder="First Name"
													value="">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>

											</div>
										</div>
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="hotelChain" class="  control-label">LastName
												</label> <input type="text" autocomplete="off" required="required"
													name="flightOrderCustomerList[${loop.count-1}].lastName" id="lastName"
													class="form-control input-sm " 
													placeholder="Last Name"
													value=""><span class="form-control-feedback glyphicon glyphicon-ok"></span>

											</div>
										</div>
										<div class="col-sm-2">
															<div class="form-group has-feedback">
																<label for="City" class=" control-label">Email </label>

																<input type="email" autocomplete="off"
																	name="orderCustomer.email" id="email" required="required"
																	class="form-control input-sm" placeholder="Email">
																	<span class="form-control-feedback glyphicon glyphicon-ok"></span>

															</div>
														</div>
														 <div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="hotelName" class=" control-label">Booking Class
												</label> <select
													name="flightOrderCustomerList[${loop.count-1}].bookingClassPreffer"
													class="form-control input-sm" id="bookingClassPreffer">
													<option value="Economy">Economy</option>
									<option value="All">ALL</option>
									<option value="PremiumEconomy">Premium Economy</option>
									<option value="Business">Business</option>
									<option value="PremiumBusiness">Premium Business</option>

												</select><span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div> 
														
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="hotelChain" class="  control-label">Ticket No
												</label> <input type="text" autocomplete="off"
													name="flightOrderCustomerList[${loop.count-1}].eticketnumber"
													class="form-control input-sm cusValidationAlphaNum" required="required" value=""
													placeholder="Enter ticket no." id="tno${loop.count}"
													><span class="form-control-feedback glyphicon glyphicon-ok"></span>

											</div>
										</div>
										
										
							<c:if test="${fieldName[0]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode"> ${fieldName[0]}</label> <input type="text"
											autocomplete="off" 
											name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField1"
											class="form-control input-sm cusValidationAlphaNum" required="required" 
											placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.manualField1"/>" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
							<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.trNumber!=null || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							
									<div class="col-sm-2">
								<div class="form-group has-feedback">
									<label for="travelRequestNumber">Travel Request Number</label>
									<input type="number" autocomplete="off"
										name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.trNumber" class="form-control input-sm"
										required="required" placeholder="Travel Request Number"><span class="form-control-feedback glyphicon glyphicon-ok"></span>
								</div>
							</div>
							</s:if>
							<c:if test="${rmConfigModel.empCode}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="empCode"> Emp Code</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.empCode"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmempCode"
															placeholder="Enter emp Code Details" value="<s:property value="configTripDetailsModel.empCode"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.department}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="empCode"> Department</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.department"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmDepartment"
															placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.department"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.costCenter}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="empCode">Cost Center</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.costCenter"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmcostCenter"
															placeholder="Enter costCenter Details" value="<s:property value="configTripDetailsModel.costCenter"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.approverName}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="empCode">Approver Name</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.approverName"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmapproverName"
															placeholder="Enter approverName Details" value="<s:property value="configTripDetailsModel.approverName"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.location}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="empCode">location</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.location"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmlocation"
															placeholder="Enter location Details" value="<s:property value="configTripDetailsModel.location"/>">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.bussinessUnit}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="empCode">Bussiness Unit</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.bussinessUnit"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmbussinessUnit" 
															placeholder="Enter bussiness Unit Details" value="<s:property value="configTripDetailsModel.bussinessUnit"/>">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span></div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.projectCode}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="projectCode"> Project Code</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.projectCode"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmprojectCode" 
															placeholder="Enter projectCode Details" value="<s:property value="configTripDetailsModel.projectCode"/>">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span></div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.reasonForTravel}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="reasonForTravel">Reason For Travel</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.reasonForTravel"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmreasonForTravel"
															placeholder="Enter reasonForTravel Details" value="<s:property value="configTripDetailsModel.reasonForTravel"/>">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span></div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.billNonBill}">
											<div class="col-sm-2">
													<div class="form-group has-feedback">
														<label for="billNonBill">Billing/Non Billing</label> <input
															type="text" autocomplete="off"
															name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.billNonBill"
															class="form-control input-sm cusValidationAlphaNum" required="required" id="rmbillNonBill"
															placeholder="Enter billNonBill Details" value="<s:property value="configTripDetailsModel.billNonBill"/>">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span></div>
												</div>
											</c:if>
							<c:if test="${fieldName[1]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode"> ${fieldName[1]}</label> <input type="text"
											autocomplete="off" required
											name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField2"
											class="form-control input-sm cusValidationAlphaNum"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField2}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[2]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode"> ${fieldName[2]}</label> <input type="text"
											autocomplete="off" required
											name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField3"
											class="form-control input-sm cusValidationAlphaNum"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField3}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[3]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode"> ${fieldName[3]}</label> <input type="text"
											autocomplete="off" required
											name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField4"
											class="form-control input-sm cusValidationAlphaNum"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField4}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[4]!=null}">
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="empCode"> ${fieldName[4]}</label> <input type="text"
											autocomplete="off" required
											name="flightOrderCustomerList[${loop.count-1}].rmConfigTripDetailsModel.manualField5"
											class="form-control input-sm cusValidationAlphaNum" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField5}" >
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
									</div>
								</div>
							</c:if>
										<s:if test="!domesticOrInternationalCountryNameList[0].equalsIgnoreCase('India')  
										             || !domesticOrInternationalCountryNameList[1].equalsIgnoreCase('India')">
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="currency" class=" control-label">Date of
													Birth </label> <input type="text" autocomplete="off"
													class="form-control input-sm" id="dateofBirth${loop.count}"
													placeholder="Date of Birth " required="required"
													name="flightOrderCustomerList[${loop.count-1}].birthday">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										</s:if>
										<%-- 
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
 --%>

										

										<div class="col-sm-2 mopt">
											<div class="form-group has-feedback">
												<label for="currency" class=" control-label">Nationality
												</label> <input type="text" class="form-control input-sm "
													name="flightOrderCustomerList[${loop.count-1}].nationality"
													placeholder="Nationality">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="col-sm-2 mopt">
											<div class="form-group has-feedback">
												<label for="currency" class="  control-label">Passport
													 Date of Issue</label> <input type="text"
													class="form-control input-sm" id="issueddate${loop.count}"
													placeholder="Passport Date Of Issue "
													name="flightOrderCustomerList[${loop.count-1}].passport_issuedDate">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="col-sm-2 mopt">
											<div class="form-group has-feedback">
												<label for="currency" class="  control-label">Passport
													Expire Date </label> <input type="text"
													class="form-control input-sm" id="expiredate${loop.count}"
													placeholder="Passport Expire Date"
													name="flightOrderCustomerList[${loop.count-1}].passport_expiryDate">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="col-sm-2 mopt">
											<div class="form-group has-feedback">
												<label for="currency" class="control-label">Passport
													Number </label> <input type="text" class="form-control input-sm cusValidationAlphaNum"
													placeholder="Passport Number"
													name="flightOrderCustomerList[${loop.count-1}].passportNo">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="col-sm-2 mopt">
											<div class="form-group has-feedback">
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
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>

											</div>
										</div>
							   			<!-- <div class="col-sm-2 filmore" style="margin-top: 25px;">

											<a class="btn btn-primary btn-xs" role="button"
												data-toggle="collapse" href="javascript:void(0)"  onclick="more(this)"  id="fil" > More options </a>

										</div>  
  -->
</div>
 <div class="col-sm-2 filmore pull-right" style="margin-top: 25px;">

											<a class="btn btn-primary btn-xs pull-right" role="button"
												data-toggle="collapse" href="javascript:void(0)"  onclick="more(this)"  id="fil" > More options </a>

										</div> 
										 
 

						           <div class="col-sm-12  pricebreakup"> 
											<a class="btn btn-success createdquotation collapsed"
												role="button" data-toggle="collapse"
												href="#priceFilters" aria-expanded="true"
												aria-controls="priceFilters"> Price Breakup(s)${loop.count} </a>
									</div>  
												
												
									
 

										<div class="col-sm-12 clearfix pricebb price-details">
											<div class="row">
												<div class="collapse" id="priceFilters"
													aria-expanded="true">
													<div class="panel-body">
														<!-- Filter of main info -->
														<div class=" col-sm-12 clearfix">
														
														<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">Currency
																	</label> <input type="text" readonly="readonly"
																		autocomplete="off"
																		value="INR" 
																		class="form-control input-sm">
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>
																</div>

															</div>		
															
															<div class="col-sm-2">
																<div class="form-group has-feedback"><%-- ${flightQuotation.totalAmount} --%>
																	<label for="hotelName" class=" control-label">Base
																		Fare </label> <input type="text" autocomplete="off"
																		id="baseFare${loop.count}" required="required"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].baseFare"
																		class="form-control input-sm required baseFareprice cusValidationforprice" 
																		value="0" 
																		placeholder="Base Fare" >
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">YQ
																	</label> <input type="text" autocomplete="off"
																	 id="YQTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].YQTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="YQTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">YR Tax
																	</label> <input type="text" autocomplete="off"
																	 id="YRTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].YRTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="YRTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">WO Tax
																	</label> <input type="text" autocomplete="off"
																	 id="WOTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].WOTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="WOTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">PSF Tax
																	</label> <input type="text" autocomplete="off"
																	 id="PSFTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].PSFTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="PSFTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">UDF Tax
																	</label> <input type="text" autocomplete="off"
																	 id="UDFTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].UDFTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="UDFTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2"> 
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">K3 Tax
																	</label> <input type="text" autocomplete="off"
																	 id="K3Tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].K3Tax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="K3Tax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															                     
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">IN Tax
																	</label> <input type="text" autocomplete="off"
																	 id="INTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].INTax"
																		class="form-control input-sm cusValidationforprice" 
																		placeholder="INTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">Transaction Fee
																	</label> <input type="text" autocomplete="off"
																	 id="transactionFee${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].transactionFee"
																		class="form-control input-sm cusValidationforprice" 
																		placeholder="transactionFee" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">G1 Tax
																	</label> <input type="text" autocomplete="off"
																	 id="G1Tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].G1Tax"
																		class="form-control input-sm cusValidationforprice" 
																		placeholder="G1Tax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName " class=" control-label">F2 Tax
																	</label> <input type="text" autocomplete="off"
																	 id="F2Tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].F2Tax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="F2Tax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">F6 Tax
																	</label> <input type="text" autocomplete="off"
																	 id="F6Tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].F6Tax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="F6Tax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">ZR Tax
																	</label> <input type="text" autocomplete="off"
																	 id="ZRTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].ZRTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="ZRTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback" >
																	<label for="hotelName" class=" control-label">YC Tax
																	</label> <input type="text" autocomplete="off"
																	 id="YCTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].YCTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="YCTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">US Tax
																	</label> <input type="text" autocomplete="off"
																	 id="USTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].USTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="USTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">XA Tax
																	</label> <input type="text" autocomplete="off"
																	 id="XATax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].XATax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="XATax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">XY Tax
																	</label> <input type="text" autocomplete="off"
																	 id="XYTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].XYTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="XYTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback" >
																	<label for="hotelName" class=" control-label">AY Tax
																	</label> <input type="text" autocomplete="off"
																	 id="AYTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].AYTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="AYTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label">XF Tax
																	</label> <input type="text" autocomplete="off"
																	 id="XFTax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].XFTax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="XFTax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label notone">Total Tax
																	</label> <input type="text" autocomplete="off"
																	 id="tax${loop.count}" value="0"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].tax"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="Tax" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="hotelName" class=" control-label notone1">Markup
																	</label> <input type="text" autocomplete="off"
																		name="flightOrderCustomerPriceBreakupList[${loop.count-1}].markup"
																		id="markup${loop.count}" value="0"
																		class="form-control input-sm cusValidationforprice"
																		placeholder="Markup" required>
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

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
							</div>
							
							<div class="additionalpackage"></div>
										
												<div class="clearfix add-remove">
													<a class="btn btn-primary" role="button" id="addroom"
														onclick="add(this);"> <!-- onclick="add() onclick="remove_field()" -->
														Add More
													</a> <a class="btn btn-primary remove_field" id="removeroom"
														role="button" onclick="remove_field(this)" disabled>
														Remove </a>

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
									<a  data-toggle="collapse" data-parent="#accordion"
										href="#collapseTripDetails"> Trip Details </a>
								</h4>
								
							</div>
							<!--/.panel-heading -->
							<div id="collapseTripDetails" class="panel-collapse collapse in">
								<div class="panel-body">
									<div id="tripdetails" class="clearfix">
									
									<div class="ctrip">
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">Airline
												</label> <input type="text" autocomplete="on"
													name="flightOrderTripDetailList[0].operatedByName"
													class="form-control input-sm airline" required="required"
													value=""
													placeholder="Airline"  id="airline">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>	
											</div>
										</div>

										<div class="ctrip">
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">FlightNumber
												</label> <input type="text" autocomplete="off"
													name="flightOrderTripDetailList[0].flightNumber"
													class="form-control input-sm cusValidationAlphaNum" required="required"
													placeholder="Flight Number"  id="flightNumber">	
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
									
										
										
										
										
										<div class="ctrip">
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">Origin </label>

												<input type="text" autocomplete="on" id="origin"
													name="flightOrderTripDetailList[0].originName"
													class="form-control input-sm origin" required="required"
													placeholder="Origin Name" value="">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="ctrip">
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">Destination
												</label> <input type="text" autocomplete="on" id="destination"
													name="flightOrderTripDetailList[0].destinationName"
													class="form-control input-sm destination"
													required="required" placeholder="Destination Name"
													value="">
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<%-- <div class="ctrip">
											<div class="form-group">
												<label for="NetRate" class=" control-label">Destination
												</label>
												<select class="form-control input-sm"
													name="flightOrderTripDetailList[0].bookingClassPreffer">
													<option value="M">Male</option>
													<option value="F">Female</option>

												</select> <!-- <input type="text" autocomplete="on" id="bookingClassPreffer"
													name="flightOrderTripDetailList[0].bookingClassPreffer"
													class="form-control input-sm destination"
													required="required" placeholder="Destination Name"
													value=""> -->
											</div>
										</div> --%>
										<input type="hidden" id="totalNumberOfPassenger" value="1">
										<div class="ctrip">
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">Departure
													Date </label> <input type="text" autocomplete="off"
													id="departureDate"
													name="flightOrderTripDetailList[0].depDate"
													class="form-control input-sm dep datepicker_recurring_start"
													required="required" placeholder="Departure Date"
													value="" readonly>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>

										<div class="ctrip">
											<div class="form-group sm-4 has-feedback">
												<label for="NetRate" class=" control-label">Departure
													Time </label> <input type="text" autocomplete="off"
													id="departureTime" name="flightOrderTripDetailList[0].depTime" 
													class="form-control input-sm dtime" required="required"
													placeholder="HH:mm" readonly>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										
										<div class="ctrip">
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">Arrival
													Date </label> <input type="text" autocomplete="off"
													id="arrivalDate"
													name="flightOrderTripDetailList[0].arrDate"
													class="form-control input-sm arr datepicker_recurring_start"
													required="required" placeholder="Arrival Date"
													value="" readonly>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
											</div>
										</div>
										<div class="ctrip">
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">Arrival
													Time </label> <input type="text" autocomplete="off"
													 id="arrivalTime"
													name="flightOrderTripDetailList[0].arrTime"
													class="form-control input-sm atime" required="required"
													placeholder="HH:mm" readonly>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
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
							<input type="hidden"  id="passengercountmine" value="1" onchange="setUserID();">
							<!-- /.panel-collapse -->
						</div>
						<div class="panel panel-default" id="supplier">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse"
										data-parent="#accordion" href="#collapsesupplier">
										Supplier Amount Payable</a>
								</h4>
							</div>
							<!--/.panel-heading -->
							<div id="collapsesupplier" class="panel-collapse collapse in">
								<div class="panel-body">
 						
									<div class="col-sm-2">
													<div class="form-group has-feedback">
								<label for="Vendor" class=" control-label">Supplier </label>	
								 
								<select class="form-control input-sm" name="providerAPI" id="supplierName" required>
									<option value="" selected="selected">select Supplier</option>
								<s:iterator value="ApiProviders">
									<option value="<s:property value="vendorName"/>"><s:property value="vendorName" /></option>
								</s:iterator>
								 </select>
								 <span class="form-control-feedback glyphicon glyphicon-ok"></span>
							 
											
								</div>
								</div>

									
									
									<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="currency" class=" control-label">PNR
										  </label>
										 
											<input type="text" class="form-control input-sm" 
												required="required" placeholder="PNR"
												  name="pnr"   id='pnr'>
												  <span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
									<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="currency" class=" control-label">GDS-PNR
										  </label>
										 
											<input type="text" class="form-control input-sm"
												required="required" placeholder="GDS-PNR"
												  name="gdsPnr" id="gdsPnr">
												  <span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
									
										 
									<div class="col-sm-2">
									<input type="hidden" class="form-control input-sm"
												required="required" placeholder="Confirmation Number"
												  name="orderId" id="orderId" value="TAYA786">
									<div class="form-group has-feedback">
										<label for="currency" class="  control-label">Supplier
											Currency </label>
										 
											<select class="form-control input-sm" required="required"
												name="apiCurrency">
												<s:iterator value="countryList">
										<s:if test="cur_code=='INR'">
											<option selected="selected"
												value="<s:property value="cur_code"/>"><s:property
													value="cur_code" /></option>
										</s:if>
										<option value="<s:property value="cur_code"/>"><s:property
												value="cur_code" /></option>
									</s:iterator>
												

											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
									 
									<input type="hidden" class="form-control input-sm"
												required="required" placeholder="Conversion Rate"
												value="1.0" name="apiToBaseExchangeRate">
									<div class="col-sm-2">
										<div class="form-group">
											<label for="flightOrderRow.managementFee" class=" control-label">Management Fees
											</label> 
										 
											<input type="text" autocomplete="off"
													name="flightOrderRow.tempManagementFees"
													id="managementFees" value="<s:property value="managementFeeForBooking" />"
													class="form-control input-sm" required="required"
													placeholder="management Fees" readonly="readonly">
													</div>

											</div>
													
													
											<div class="col-sm-12 row">
													<div class="col-sm-2">
													<div class="form-group has-feedback ">
													<label for="currency" class="control-label">Supplier PaymentType </label>
													
														<select class="form-control input-sm"
															id="supplierPaymentType" name="supplierPaymentType">
															<option value="Full">Full Payment</option>
															<option value="Partial">Partial Payment</option>
															<option selected="selected" value="Zero">No Payment</option>
														</select>
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div> 
													</div>
									
											<div class="col-sm-2" style="margin-top: 25px;">
											  <a  class="btn btn-info btn-xs" data-toggle="modal" href="#suppliermyNotification"><i class="fa fa-bell-o fa-sm"></i> Reminder</a>
											</div>
											
											
											
											
											
											<div class="col-sm-2 supplierPayment" style="display: none">							
									
									<div class="form-group has-feedback "  >
										<label for="currency" class=" control-label">Amount</label>
										 
											<input type="text" class="form-control input-sm"
												placeholder="amount" id="supplierAmount"
												name="paymentTransaction.apiProviderAmount">
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>

									</div>
									<div class="col-sm-2 supplierPayment" style="display: none">
									<div class="form-group has-feedback" >
										<label for="currency" class="col-sm-2 control-label">Paid
											By</label> 
											<input type="text" class="form-control input-sm"
												placeholder="Paid By"
												name="paymentTransaction.paidBySupplier">
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>

									</div>

									<!-- <div class="form-group card-details">
										<label for="currency" class="col-sm-2 control-label">CARD
											DETAILS </label>
									</div>
 -->							<div class="col-sm-2 no-payment-mode">
									<div class="form-group has-feedback ">
										<label for="currency" class=" control-label">Payment
											Mode </label>
									 
											<select class="form-control input-sm"
												id="supplierPaymentMethod"
												name="apiProviderPaymentTransaction.payment_method" required>
												<option value="card">CARD</option>
												<option value="cash">CASH</option>
												<option value="online">ONLINE</option>

											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>

								<div class="col-sm-2 supplier-comments" style="display: none">
									<div class="form-group has-feedback" >
										<label for="currency" class="col-sm-2 control-label">Comments
										</label> 
											<textarea rows="1" class="form-control input-sm"
												placeholder="Type text"
												name="apiProviderPaymentTransaction.response_message"></textarea>
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>

<div class="col-sm-2 card-details">

									<div class="form-group has-feedback">
										<label for="currency" class=" control-label">CardHolderName
										</label> 
											<select class="form-control input-sm"
												name="paymentTransaction.supplierCardHolderId">
												<option value="">Select card holder name</option>
												<s:iterator value="paymentCardList">
													<s:if test="paymentType==1">
														<option value="${id}">${userName}(${cardNumber})</option>
													</s:if>

												</s:iterator>



											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
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
									<a data-toggle="collapse"
										data-parent="#accordion" href="#collapseClient"> Client Payment Receivable
										 </a>
								</h4>
							</div>
							<!--/.panel-heading -->
							<div id="collapseClient" class="panel-collapse collapse in">
								<div class="panel-body">
								<div class="col-sm-2">
								<div class="form-group has-feedback">
												<!-- <label for="NetRate" class="control-label"
													style="color: red; font-size: 12px">Ticket Price </label> -->
												 
												 <label for="totalGstTax" style="color: red;">Ticket Price </label> 
												 
													<input type="text" style="color: red; font-size: 18px"
														disabled="disabled" id="quotedAmount1"
														value="0.00"
														class="form-control input-sm">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											<div class="col-sm-2"> 
											<input type="hidden" id="taxType" value="GST">
											<input type="hidden" autocomplete="off"
													name="totalGstTax" onchange="numbersonly(this)"
													class="form-control input-sm" id="gstTax" value="0.0"
													 >
											<div class="form-group">
												<label for="totalGstTaxAmount" >
													Gst Tax Amount </label> 
													<div class="inner-addon right-addon">
      												<span class="glyphicon">@ <span id="gstTaxPer"></span>%</span>
      												<input type="text" autocomplete="off"
													name="totalGstTaxAmount" onchange="numbersonly(this)"
													class="form-control input-sm" id="gstTaxAmount" value="0.0"
													 placeholder="Enter Gst Tax "
													readonly="readonly"   > 
    												</div>
													
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="totInvoiceAmount" >Invoice
													Amount </label> <input type="text" autocomplete="off"
													onclick="numbersonly(this);addtax();"
													name="totInvoiceAmount" id="invoiceamount"
													class="form-control input-sm" placeholder="Invoice Amount"
													value="0" readonly="readonly">
											</div>
											</div>
								<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="currency" >Booking
											Currency </label>
										 
											<select class="form-control input-sm" required="required"
												name="bookingCurrency">
												
												<s:iterator value="countryList">
										<s:if test="cur_code=='INR'">
											<option selected="selected"
												value="<s:property value="cur_code"/>"><s:property
													value="cur_code" /></option>
										</s:if>
										<option value="<s:property value="cur_code"/>"><s:property
												value="cur_code" /></option>
									</s:iterator>
											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
									
									<div class="col-sm-2">	
									<div class="form-group has-feedback">
										<label for="currency" >INR
											To Booking </label>
										 
											<input type="text" class="form-control input-sm" value="1.0"
												required="required" placeholder="Conversion Rate"
												name="baseToBookingExchangeRate">
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
								 <div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="NetRate" >Payment
											Status </label>
										 
											<select class="form-control input-sm" required="required"
												name="paymentStatus" id="paymentStatus">
												<option value="Paid">Success</option>
												<option value="Pending">Pending</option>
											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>

									
									<div class="col-sm-12 row">
									
									<div class="col-sm-2">
									<div class="form-group has-feedback">
										<label for="NetRate" class=" control-label">Booking
											Status </label>
										 
											<select class="form-control input-sm" required="required"
												name="statusAction">
												<option value="Confirmed">Confirmed</option>
												<option value="Pending">Pending</option>
												<option value="Reserved">Reserved</option>
												<option value="Failed">Failed</option>
											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
									
									
									<div class="col-sm-2"> 
									<div class="form-group has-feedback">
										<label for="currency" class=" control-label">Customer
											PaymentType </label>
										  
											<select class="form-control input-sm"
												name="customerPaymentType" id="clientPaymentType">
												<option value="Full">Full Payment</option>
												<option value="Partial">Partial Payment</option>
												<option selected="selected" value="Zero">No Payment</option>
											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
										
									</div>
									
									<div class="col-sm-1 "  style="margin-top: 25px;">
													 <a  class="btn btn-info btn-xs" data-toggle="modal" href="#clientmyNotification"><i class="fa fa-bell-o fa-sm"></i>Reminder</a>
													</div>
													
										<div class="col-sm-2 clientPayment">  
							 	<div class="form-group has-feedback">
										<label for="currency" class="  control-label">Amount</label>
										 
											<input type="text" class="form-control input-sm"
												placeholder="amount" name="paymentTransaction.clientAmount">
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
<div class="col-sm-2  clientPayment"> 
									<div class="form-group has-feedback">
										<label for="currency" class=" control-label">Paid
											By</label>
										 
											<input type="text" class="form-control input-sm"
												placeholder="Paid By" name="paymentTransaction.paidByClient">
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div> 
									<div class="col-sm-2 no-client-payment-mode"> 
									<div class="form-group has-feedback">
										<label for="currency" class=" control-label">Payment
											Mode </label>
										 
											<select class="form-control input-sm"
												id="clientPaymentMethod"
												name="paymentTransaction.payment_method" required>
												<option value="card">CARD</option>
												<option value="cash">CASH</option>
												<option value="online">ONLINE</option>

											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>
									<div class="col-sm-2 client-comments" style="display: none"> 
									<div class="form-group has-feedback "  >
										<label for="currency" class=" control-label">Comments
										</label>
										 
											<textarea rows='1' class="form-control input-sm"
												placeholder="Type text"
												name="paymentTransaction.response_message"></textarea>
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
									</div>

								<div class="col-sm-2 client-card-details"> 
									<div class="form-group has-feedback">
										<label for="currency" class=" control-label">CardHolderName
										</label> 
											<select class="form-control input-sm"
												name="paymentTransaction.clientCardHolderId">
												<option value="">Select card holder name</option>
												<s:iterator value="paymentCardList">
													<s:if test="paymentType==0">
														<option value="${id}">${userName}(${cardNumber})</option>
													</s:if>

												</s:iterator>


 
											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
										</div>
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
								<!-- <button type="submit"  class="btn btn-primary btn-lg">Book Now
									 </button> -->
									 <button type="button" id="buttonSubmit" class="btn btn-primary btn-lg">Book Now
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
	<script src="js/rmfeild.js"> </script> 
	<script>
	//var totalAmount= ${flightQuotation.totalAmount};
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
			minDate : 0,
			onSelect : function(selectedDate) {
				 $(this).valid();
				var date2 = $("#departureDate").datepicker('getDate', '+1d');
				date2.setDate(date2.getDate() + 1);
				var d1 = $(this).datepicker("getDate");
    			$("#arrivalDate").datepicker("option", "minDate", d1);
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
			minDate : 0,
			onSelect : function(selected) {
				 $(this).valid();
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
		$("#arrivalTime, #departureTime").timepicker({
			
			dateFormat : "HH:mm"
		}).on('change', function() {
            $(this).valid();  // triggers the validation test
        });
		
		

		$("#expiredate1,#issueddate1").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate2,#issueddate2").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#expiredate3,#issueddate3").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate4,#issueddate4").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate5,#issueddate5").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#expiredate6,#issueddate6").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});

		$("#expiredate7,#issueddate7").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate8,#issueddate8").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		$("#expiredate9,#issueddate9").datepicker({
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
		
		$('#titlefirst').change(function(){
		    $('#title1').val($(this).val());
		});
		
		$('#firstname').change(function(){
		    $('#firstName').val($(this).val());
		});
		$('#lastname').change(function(){
		    $('#lastName').val($(this).val());
		});
		$('#costCenter').change(function(){
		    $('#costCenter1').val($(this).val());
		});
		$('#customerNo').change(function(){
		    $('#customerNo1').val($(this).val());
		});
		
		
		
	</script>
	<script type="text/javascript">
	function add(currentObj) { 
		var $rooms = $("#passengerNested .panel-default:first").clone(); 
		/* copy these two lines */
		$rooms.find(".detailswithRM input").val(''); 
		
		if ($rooms.find("#priceFilters input").attr("readonly") === false){$rooms.find("#priceFilters input").val(''); } 
		/* till here copy these two lines */
		$('.additionalpackage').append($rooms).insertAfter("#passlength:last"); 
		var noOfRooms = $(".additionalpackage").find('.panel-default').length;  
		$rooms.find(".price-details").find('input:text:not(:first)').val('0');
		
		$('#totalNumberOfPassenger').val(noOfRooms+1);
		var managementFeesToCalc=  $('#managementFeesForSend option:selected').val();;
		var totalPassenger = $('#totalNumberOfPassenger').val();
		totalManagementFee=managementFeesToCalc*totalPassenger;
		$('#managementFees').val(totalManagementFee);
		$('#quoteamount').val(totalManagementFee);
		
		 for (var i = noOfRooms; i <= noOfRooms; i++) {
				$rooms.find("h4.panel-title a:first").text("Passenger Details" + (i+1)); 
				$rooms.find("h4.panel-title a[href='#collapseinside']").attr("href", "#collapseinside" + (i));
				$rooms.find("div#collapseinside").attr("id", "collapseinside" + (i));  
				$rooms.find(".panel-body .form-group input[name^=flightOrderCustomerList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				});
				
				$rooms.find(".panel-body .form-group select[name^=flightOrderCustomerList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				});
				
				$rooms.find(".passval .detailswithRM .form-group input").each(function() {
					var id = $(this).attr('id'); 
					id = id+i;
					$(this).attr('id', id);
					 
				});
				
				$rooms.find(".pricebreakup a").text(" Price Breakup(s)" + (i+1)); 
				$rooms.find(".pricebreakup a[href='#priceFilters']").attr("href", "#priceFilters" + (i));
				$rooms.find("div#priceFilters").attr("id", "priceFilters" + (i)); 
				$rooms.find(".filmore a").attr("id", "fil" + (i+1));
				
				$rooms.find(".price-details .form-group input[name^=flightOrderCustomerPriceBreakupList]").each(function() {
					
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				});
				
				
				var corporateID = $('#companyRole').val();  
				 var iscorID=$("#companyId").val();  
				 
				 var url = ''; 
				 $.ajax({
					   url : 'resources/Apicredential.properties',
					   type : 'GET',
					   dataType : 'json',
					   success : function(response) {
						   url = response.ApiURL;
					    console.log("ApiURL",url);
					   }, 
					   error : function(xhr, status, error) {
					   },complete: function(){
					        }

					  });
				 if(corporateID=="true") {
				 $("#firstName"+i).autocomplete({
				     source: function (request, response) { 
				          $.ajax({
				        	  url: url+"employee/list?companyid="+iscorID,
				              dataType : "json",
				              data: request,
				              success: function (data) { 
				             	   response(data);
				             	   response($.map(data, function(item) {
				                        return {
				                            label: item.firstName,
				                            value: item.firstName,
				                            lastName: item.lastName, 
				                            id:item.id, 
				                            data:item
				                        } 
				             	   }));
				             	   
				              }
				          });
				     },
				     select: function (event, ui) {
				     	 console.dir(ui);
				          $('#firstName'+(i-1)).val(ui.item.firstName);  
				          $('#lastName'+(i-1)).val(ui.item.lastName); 
				          console.log("checking last name",$('#firstName'+(i-1)).val(ui.item.firstName));
				         console.log("checking last name",$('#lastName'+(i-1)).val(ui.item.lastName));
				         console.log("ui.item.lastName",ui.item.lastName);
				          
				     }, 
				      change: function (event, ui) { console.log("uidddddd",ui); 
				      $.ajax({ 
				    	  url: url+"employee/empdetailsById?id="+ui.item.id,
				          dataType: "json",  
				          success: function(datas) {
				             console.log(datas);  
				             if(datas.rmDataListDetails.department!=undefined || datas.rmDataListDetails.department==""){
				            	 $('#rmDepartment'+(i-1)).val(datas.rmDataListDetails.department); 
				            }
				             if(datas.rmDataListDetails.empCode!=undefined || datas.rmDataListDetails.empCode==""){
				            	 $('#rmempCode'+(i-1)).val(datas.rmDataListDetails.empCode); 
				            }
				             if(datas.rmDataListDetails.costCenter!=undefined || datas.rmDataListDetails.costCenter==""){
				             	 $('#rmcostCenter'+(i-1)).val(datas.rmDataListDetails.costCenter); 
				             }
				             
				             if(datas.rmDataListDetails.approverName!=undefined || datas.rmDataListDetails.approverName==""){
				            	 $('#rmapproverName'+(i-1)).val(datas.rmDataListDetails.approverName); 
				            }
				             if(datas.rmDataListDetails.location!=undefined || datas.rmDataListDetails.location==""){
				            	 $('#rmlocation'+(i-1)).val(datas.rmDataListDetails.location); 
				            }
				             if(datas.rmDataListDetails.trNumber!=undefined || datas.rmDataListDetails.trNumber==""){
				            	 $('#rmtrNumber'+(i-1)).val(datas.rmDataListDetails.trNumber); 
				            }
				             if(datas.rmDataListDetails.bussinessUnit!=undefined || datas.rmDataListDetails.bussinessUnit==""){
				            	 $('#rmbussinessUnit'+(i-1)).val(datas.rmDataListDetails.bussinessUnit); 
				            }
				             
				             if(datas.rmDataListDetails.projectCode!=undefined || datas.rmDataListDetails.projectCode==""){
				               	 $('#rmprojectCode'+(i-1)).val(datas.rmDataListDetails.projectCode); 
				               }
				             
				             if(datas.rmDataListDetails.reasonForTravel!=undefined || datas.rmDataListDetails.reasonForTravel==""){
				               	 $('#rmreasonForTravel'+(i-1)).val(datas.rmDataListDetails.reasonForTravel); 
				               }
				               if(datas.rmDataListDetails.billNonBill!=undefined || datas.rmDataListDetails.billNonBill==""){
				                	 $('#rmbillNonBill'+(i-1)).val(datas.rmDataListDetails.billNonBill); 
				                } 
				             
				          },
				          error: function(datas) { 
				            console.log(datas);
				          }
				        
				        }); 
				     } 
				 }); 
				 } 
				
				
				
				
		 }
		 
		
		 
			if ((noOfRooms) > 0) {
				$('.remove_field').removeAttr('disabled');
			} else {
				$('.remove_field').attr('disabled', 'disabled');
			}
			var count=$('#passengercountmine').val();
			if(count>0){
				count=noOfRooms+1;
				$ ('#passengercountmine').val(count);  
			}
			else{
				$ ('#passengercountmine').val(count);
			}
			
			 
			
		}
	

		function remove_field(currentObj) {
			var parentid = $("#" + currentObj.id).parent().prev().attr('id');
			  
			   var noOfRooms = $("#" + parentid).find('.additionalpackage >.panel-default').length;

			 $('#' + parentid).find('.additionalpackage .panel-default:last-child').remove();
			if (noOfRooms <= 1) {
				$('.remove_field').attr('disabled', 'disabled');
			} else {
				$('.remove_field').removeAttr('disabled');
				 
			}
			noOfRooms--; 
			calculateTax();
			
			var count=	$ ('#passengercountmine').val();
			if(count>1){
				count=count-1;
				$('#passengercountmine').val(count);
				
			}
			else{
				$ ('#passengercountmine').val(count);
			}
			
			var managementFeesToCalc=  $('#managementFeesForSend option:selected').val();;
			var totalPassenger = $('#totalNumberOfPassenger').val() -1;
			$('#totalNumberOfPassenger').val(totalPassenger);
			totalManagementFee=managementFeesToCalc*totalPassenger;
			$('#managementFees').val(totalManagementFee);
			$('#quoteamount').val(totalManagementFee);
			
		} 
		 
		function more(more){ 
			var pid =  $("#" + more.id).parent().parent().parent().attr('id');
				
				$("#" + pid).each(function(){  
					$(this).find(".mopt").slideToggle( "slow" );
				});
			}
		
		
	
	
	</script>
	<script type="text/javascript">
	function getmangmentfee(){
		var sal = $('#managementFeesForSend option:selected').val();
		var totalPassenger = $('#totalNumberOfPassenger').val();
		totalManagementFee=sal*totalPassenger;
		$('#managementFees').val(totalManagementFee);
		  $('#quoteamount').val(totalManagementFee);
		  try {
			  var entityId=$('#entity option:selected').val();
			  if (entityId == null){
				  entityId=0;
				}
			}
			catch(err) {
				var entityId=0;
			}
		  
		  var entityType = $('#entity option:selected').text();
		  var productType = $('#managementFeesForSend option:selected').text();
		  
			  var travelType='flight';
				if (productType === "Domestic" || productType === "International") {
					$.ajax({
					    url : "getEntityTaxes?entityId="+entityId+"&travelType="+travelType+"&productType="+productType,
					    type : "GET", 
					    dataType: "json",
					    success : function(data) {	
					    	$("#gstTax").val(data.commonGstTaxes.totalTax);
					    	$("#gstTaxPer").text(data.commonGstTaxes.totalTax);
					   		 $("#gstTaxAmount").val(data.commonGstTaxes.totalTax);
					   		calculateTax();
					    }
					});
				}
				else if(productType === "Domestic Reissue" || productType === "International Reissue"){
					$("#gstTax").val(0);
			    	$("#gstTaxAmount").val(0);
			    	$("#invoiceamount").val(0);
					/* $("#gstTaxPer").text(0);
			   		 $("#gstTaxAmount").val(0); */
				}
				else{
					alert('Please select product type');
					$("#gstTax").val("");
			    	$("#gstTaxAmount").val("");
			    	$("#invoiceamount").val("");
				}
		  
	}
	
 $("#passengerNested ").on("focusout" , ".price-details input.form-control" ,function(){  
	    	  if(this.value == " "){  
     			this.value = "0"; 
     	} 
     });  
	    $(document).on("keyup","#passengerNested .price-details input.form-control" ,function(){  
	    	calculateTax();
        });
	    
	   
	    
	    $(document).on("keyup","#managementFees" ,function(){  
	    	calculateTax();
        });
	    
	    
	    
   
	 function calculateTax() {
	        var sum = 0; 
	        $("#passengerNested .price-details .form-control").each(function() {
	            if (!isNaN(this.value) && this.value.length != 0) {
	                sum += parseFloat(this.value);
	            }
	        }); 
	        $('#quotedAmount1').val(sum.toFixed(2));
	        
	        
	        var depositBalance =${userWallet.depositBalance};
			var walletBalance =${userWallet.walletbalance};
			var totalBookingAmount1=$('#quotedAmount1').val();
			
			var gstAmount =$('#gstTax').val();
			var managementfees =$('#managementFees').val();
			var gstTaxAmount =$('#gstTax').val()*(managementfees/100);
			
			//alert("management fees "+managementfees);
	        var totalInvoiceAmount=parseFloat(totalBookingAmount1) +parseFloat(gstTaxAmount)+parseFloat(managementfees);
	    	//alert("Total Invoice Amount "+totalInvoiceAmount);
	        $('#invoiceamount').val(totalInvoiceAmount);
	        $('#gstTaxAmount').val(gstTaxAmount);
	        
			if (depositBalance >= totalBookingAmount1) {
				$('.submitWrap').show();
				$('#balanceCheck').hide();
			}
			else if (walletBalance >= totalBookingAmount1) {
				$('.submitWrap').show();
				$('#balanceCheck').hide();
			}
			else{
				$('.submitWrap').hide();
				$('#balanceCheck').show();
			} 
	    }
	
	 
	
	</script>
	<script type="text/javascript">
	
	$(document).ready(function(){


		  		jQuery.validator.addMethod("baseFareprice",function(value) {
	             var startprice = 1;
	             console.log($('#baseFare1').val());
	             return startprice < parseFloat($('#baseFare1').val());
	           }, "please add minimum base fare");
		 		
		 	jQuery.validator.addMethod("productTypeVal",function(value) {
	            var startprice =  $('#managementFeesForSend').val();
	            return (startprice !=  '00.00');
	          }, "please select product type");
	     
	             jQuery.validator.classRuleSettings.baseFareprice = { baseFareprice: true };
	  
	             $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
	                  return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
	              }, "This field cannot have symbols.");

	              $.validator.addMethod("cusValidationAlphaName",function(value,element){
	                  return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
	              },"This field cannot have numbers and symbols."); 
	              $.validator.addMethod("cusValidationforprice",function(value,element){
	                  return this.optional(element) || /^[0-9]+$/i.test(value);
	              },"This field cannot have Char and symbols.");
	

		    $("#bookingConfirmed").validate({
		    	ignore: false,
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
		        submitHandler: function (form) { // for demo
		           // alert('valid form submitted'); // for demo
		            return false; // for demo
		        },
		        invalidHandler: function(e,validator) {
		            for (var i=0;i<validator.errorList.length;i++){   
		                $(validator.errorList[i].element).parents('.panel-collapse.collapse').collapse('show');
		            }
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
		    
		     $('#buttonSubmit').click(function() {
		        $("#bookingConfirmed").valid();
		        if($('#baseFare1').val()<=0)
		        	alert("please enter the base fare");
		        else if($("#bookingConfirmed").valid())
		        	document.getElementById("bookingConfirmed").submit();
		     	 
		    }); 

		});
	
	/* $( "#buttonSubmit").click(function() {
			console.log($("#bookingConfirmed").valid());
			
		 	var pnr =$('#pnr').val().length;
		    var managementFeestest = $('#managementFeesForSend').val();
		  	var flightNumber =$('#flightNumber').val().length;
		    var airline =$('#airline').val().length;
		    var departureTime =$('#departureTime').val().length;
		    var departureDate =$('#departureDate').val().length;
		    var  tripDetailCount=$ ('#tripDetailCount').val();
		    var bookingDate =$('#bookingDate').val().length;
		    var arrivalTime=$('#arrivalTime').val().length;
		    var arrivalDate=$('#arrivalDate').val().length;
		    var managementFeesnew=$('#managementFees').val().length;
		    var supplier= $('#supplierName option:selected').val();
		    var gdsPnr=$('#gdsPnr').val().length;
		    var origin=$('#origin').val().length;
		    var destination=$('#destination').val().length;
		    var validamount = false;
		    var validnames = false;
		    var entity= $('#entity option:selected').val();
		    
		    $("#passengerNested .price-details input").each(function(){   
				 var id = $(this).attr("id"); 
				 if(id=="baseFare1"){
					var value = $(this).val(); 
					  if(parseInt($(this).val())==0){ 
						var presentId = $(this).parent().parent().parent().parent().parent().attr('id');  
						validamount = false;
						 return false;
				           
						
					 }else{
						 validamount = true;
						return true;
						 
					 }
				 }  
	    });
		    $("#passengerNested .passval input").each(function(){  
				 var id = $(this).attr("id"); 
				 if(id=="tno1"){
					var value = $(this).val(); 
					  if($(this).val().length == 0){ 
						var presentId = $(this).parent().parent().parent().parent().parent().attr('id');  
						alert("please enter ticket number");
						validnames =false;
						 return false;
					 } 
				 } 
				 else if(id=="firstName"){
						var value = $(this).val(); 
						  if($(this).val().length == 0){ 
							var presentId = $(this).parent().parent().parent().parent().parent().attr('id');  
							alert("please enter First Name "+ index);
							validnames =false;
							 return false;
						 } 
					 } 
				 else if(id=="lastName"){
						var value = $(this).val(); 
						  if($(this).val().length == 0){ 
							var presentId = $(this).parent().parent().parent().parent().parent().attr('id');  
							alert("please enter Last Name");
							validnames =false;
							 return false;
						 } 
					 } 
				 else if(id=="email"){
						var value = $(this).val(); 
						  if($(this).val().length == 0){ 
							var presentId = $(this).parent().parent().parent().parent().parent().attr('id');  
							alert("please enter Email");
							validnames = false;
							 return false;
						 } 
					 }
				 else{
						 validnames =true;
						 return true;
					 } 
				 
				 
	   });
		   
		 if(entity==''){
		    	alert("Please select company entity");
		    }
		 else if(pnr == 0){
			alert("Please Enter PNR Number");
		}
		else if(supplier==''){
	    	alert("Please select supplier name");
	    }
		else if(managementFeestest == '00.00'){
			alert("Please Select the product Type");
		}
		else if(flightNumber == 0){
			alert("Please Enter Flight Number");
		}
		else if(airline == 0){
			alert("Please Enter Airline");
		}
		else if(departureTime == 0){
			alert("Please Enter Departure Time");
		}
		else if(departureDate == 0){
			alert("Please Enter Departure Date");
		}
		else if(bookingDate == 0){
			alert("Please Enter Booking Date");
		}
		else if(arrivalTime == 0){
			alert("Please Enter Arrival Time");
		}
		else if(arrivalDate == 0){
			alert("Please Enter Arrival Date");
		}
		 
		else if(managementFeesnew == 0){
			alert("Please Enter Management Fee");
		}
		else if(gdsPnr == 0){
			alert("Please Enter GDS PNR");
		}
		else if(origin == 0){
			alert("Please Enter Origin");
		}
		else if(destination == 0){
			alert("Please Enter Destination");
		}
		else if(validamount == false) {
			alert("Price Breakup BaseFare is not added. Please Add price to continue");
		}
		else if(validnames == false) {
			
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
				 if(pnr== 0 ){
						 alert("Please provide PNR");
					  }
				 else{
					 document.getElementById("bookingConfirmed").submit();
				 }
				 
			  } 
		  }
		 
		}); */
	
		
	</script>
	<script type="text/javascript">
	
	function getEntity(s) {
		var entityId=s[s.selectedIndex].value;
		var productType = $('#managementFeesForSend option:selected').text();
		var travelType='flight';
		if (productType === "Domestic" || productType === "International") {
			$.ajax({
			    url : "getEntityTaxes?entityId="+entityId+"&travelType="+travelType+"&productType="+productType,
			    type : "GET", 
			    dataType: "json",
			    success : function(data) {	
			    	$("#gstTax").val(data.commonGstTaxes.totalTax);
			    	$("#gstTaxPer").text(data.commonGstTaxes.totalTax);
			    	$("#gstTaxAmount").val(data.commonGstTaxes.totalTax);
			    	calculateTax();
			    }
			});
		}
		else{
			alert('Please select product type');
			$("#gstTax").val("");
	    	$("#gstTaxAmount").val("");
	    	$("#invoiceamount").val("");
		}
	}
	$(document).on("keyup","#managementFees" ,function(){  
		  $('#quoteamount').val($('#managementFees').val());
    });
	</script>

</body>
</html>