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
<%-- <script src="js/angular.js" type="text/javascript"></script>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> --%>
	
	<%-- <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.1/css/bootstrapValidator.min.css">
 <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.1/js/bootstrapValidator.min.js"></script> --%>
 <script
	src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
 
  <%--  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js"></script>
  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/additional-methods.js"></script>  --%>
  <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>

<style type="text/css">
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

.error {
    color:red;
}
.valid {
    color:green;
    }
    
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

 

<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
<script src="js/city_code.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
					<div class="row">
			<div class="col-sm-2 clearfix pull-left">
			<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-hotel"></i> Hotel Booking</h3> 
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
			<div id="suppliermyNotification" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Supplier Payment Notification Alert
								!</h4>
						</div>

						<div class="modal-body">
							<span style='color: red; font-size: 11px; display: block'>
								(* Please separate emails with semicolon) </span>
							<form action="" method="post" class="form-horizontal"
								name="myForm" id="supplierForm">
								<div class="row">
									<div class="col-sm-12">

										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="comment">To Mail(s):</label>
												<textarea class="form-control" rows="1"
													id="supplierToEmails"></textarea>
											</div>
										</div>

										<div class="col-sm-12">
											<div class="form-group">
												<label for="comment">CC Mail(s):</label>
												<textarea class="form-control" rows="1"
													id="supplierCcEmails"></textarea>
											</div>
										</div>

										<div class="col-sm-6 fd">
											<div class="form-group">
												<label for="currency" class=" control-label"> From
													Date </label> <input type="text" class="form-control input-sm"
													value="" required id="supplierFromDate"
													placeholder="FromDate">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label for="currency" class=" control-label"> To
													Date <font size="1" color="red">(Selected time is
														preferred)</font>
												</label> <input type="text" class="form-control input-sm" value=""
													required id="supplierToDate" name=""
													placeholder="date with preferable time">
											</div>
										</div>
										<div class="col-sm-6 fd">
											<div class="form-group">
												<label for="currency" class=" control-label"> Due
													Date </label> <input type="text" class="form-control input-sm"
													value="" required id="supplierDueDate" name=""
													placeholder="FromDate">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label for="currency" class=" control-label"> Paid
													Date </label> <input type="text" class="form-control input-sm"
													value="" required id="supplierPaidDate" name=""
													placeholder="FromDate">
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
								<button type="button" id="paymentNotificationAlert"
									onclick="sendSupplierAndClientNotification('supplier');"
									class="btn btn-primary btn-sm">Set Supplier Reminder</button>

								<button type="button" class="btn btn-danger btn-sm"
									data-dismiss="modal">Close</button>

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
							<span style='color: red; font-size: 11px'>(* Please
								separate mails with semicolon) </span>
							<form action="" method="post" class="form-horizontal"
								name="myForm" id="clientForm">
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
												<label for="currency" class=" control-label"> From
													Date </label> <input type="text" class="form-control input-sm"
													value="" required id="clientFromDate"
													placeholder="FromDate">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label for="currency" class=" control-label"> To
													Date <font size="1" color="red">(Selected time is
														preferred)</font>
												</label> <input type="text" class="form-control input-sm" value=""
													required id="clientToDate" name=""
													placeholder="date with preferable time">
											</div>
										</div>
										<div class="col-sm-6 fd">
											<div class="form-group">
												<label for="currency" class=" control-label"> Due
													Date </label> <input type="text" class="form-control input-sm"
													value="" required id="clientDueDate" name=""
													placeholder="FromDate">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label for="currency" class=" control-label"> Paid
													Date </label> <input type="text" class="form-control input-sm"
													value="" required id="clientPaidDate" name=""
													placeholder="FromDate">
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
								<button type="button" id="paymentNotificationAlert"
									onclick="sendSupplierAndClientNotification('client');"
									class="btn btn-primary btn-sm">Set Client Reminder</button>
								<button type="button" class="btn btn-danger btn-sm"
									data-dismiss="modal">Close</button>
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
			
			<div class="row">
				<!-- "createHotelOfflineBooking"  -->
				<form action="goHotelQuickBooking" method="post"
					class="form-horizontal" name="myForm"  id="bookingFormId">
					<div id="myfform">
						<input id="tripId" name="tripId" type="hidden" value="${tripId}">
							<%--  <input type="hidden" id="managementFeetax" value="<s:property value="managementFeeForBooking" />">  --%>
							<input id="cityUrl" type="hidden" value="<s:text name="global.hotelCitiesUrl" ></s:text>"> 
							<input type="hidden" name="hotelTravelRequest.cityCode" id="citycode1">
							<input type="hidden" id="managementFeetaxdom" value="<s:property value="managementFeeForBookingDomManagemtFee" />">
							<input type="hidden" id="managementFeetaxintl" value="<s:property value="managementFeeForBookingIntManagemtFee" />">

							<input type="hidden" id="companyRole" value="<s:property value="#session.Company.companyRole.isCorporate()" />">
							<input  name="companyId" type="hidden" id="companyId" value="<s:property value="#session.User.Companyid" />">		

						<!-- harsha added colapse -->
						<div class="col-sm-12">
							<div class="panel-group" id="accordion">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse"  
												href="#collapseOne"  > Customer Details </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseOne" class="panel-collapse collapse in">
										<div class="panel-body">
										
										  
											<div class="col-sm-1">
												<div class="form-group has-feedback">
													<label for="checkInDate" class="control-label">Title<span
														id="mandatory"> * </span>
													</label> <select name="orderCustomer.title" 
														class="form-control input-sm" required="required" id="titlefirst">
														<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
													</select>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													

												</div>
											</div>
												<div class="col-sm-1">
							<div class="form-group has-feedback">
								<label for="adults" class="control-label">Adult(s)</label> 
								<input type="number" class="form-control input-sm" name="hotelTravelRequestQuotation.adultCount" id="adultCount"
														placeholder="Adult Count" autocomplete="off" value="1" required="required">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
														
							 				</div>
							</div>

											<div class="col-sm-2">

												<div class="form-group has-feedback">
													<label for="City" class=" control-label">First Name
														<span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off"
														name="orderCustomer.firstName"
														value=""
														class="form-control input-sm" placeholder="First Name" id="firstname" required="required">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>

												</div>
											</div>
											<div class="col-sm-2">

												<div class="form-group has-feedback">
													<label for="City" class="control-label">Last Name
														<span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off"
														value=""
														name="orderCustomer.lastName"
														class="form-control input-sm " required="required"
														placeholder="Last Name" id="lastname">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>

												</div>
											</div>
											
											
											<div class="col-sm-2">
							     				<div class="form-group has-feedback">
													<label for="City" class="control-label">City </label> <input type="text"
														autocomplete="on" id="hotelCitySearch" name="orderCustomer.city"
													class="form-control input-sm" required="required"
													placeholder="City" >
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>

													</div>
												</div>
												 
												 
						<div class="col-sm-2">
							<div class="form-group has-feedback">
								<label for="checkInDate" class="control-label">CheckIn Date </label> <input
									type="text" autocomplete="off" id="checkInDate"
									onchange="validateDateFormat(this)" name="hotelTravelRequest.checkIn"
									class="form-control input-sm" required="required"
									placeholder="checkInDate" readonly>
									<span class="form-control-feedback glyphicon glyphicon-ok"></span>

							</div>
						</div>
						
						<div class="col-sm-2">
							<div class="form-group has-feedback">
								<label for="checkOutDate" class="control-label">CheckOut Date </label> <input
									type="text" autocomplete="off" id="checkOutDate"
									name="hotelTravelRequest.checkOut" class="form-control input-sm"
									onchange="validateDateFormat(this)" required="required"
									placeholder="checkOutDate" readonly>
									<span class="form-control-feedback glyphicon glyphicon-ok"></span>

							</div>
							</div>
							
							<div class="col-sm-2">
							<div class="form-group has-feedback">
							<label for="checkinTime" class="control-label">Check In Time</label>
									<input type="text" class="form-control input-sm"
											name="hotelTravelRequestQuotation.checkInTime" id="checkintime"
											placeholder="CheckIn Time" autocomplete="off"  required="required" value="12:00" readonly>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
												</div>
								<div class="col-sm-2">
									<div class="form-group has-feedback" >
									<label for="checkinTime" class="control-label">Check Out Time</label>
											<input type="text" class="form-control input-sm"
												name="hotelTravelRequestQuotation.checkOutTime" id="checkouttime"
												placeholder="CheckIn Time" autocomplete="off"  required="required" value="12:00" readonly>
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
							
							
						</div> 
							
							<div class="col-sm-2">
							<div class="form-group has-feedback">
								<label for="childs" class="control-label">Child(s)</label> 
								<input type="number" class="form-control input-sm"
														name="hotelTravelRequestQuotation.childCount" id="childCount"
														placeholder="Child Count" autocomplete="off" value="0" required="required">
							<span class="form-control-feedback glyphicon glyphicon-ok"></span>
							</div>
							</div>
							
							<div class="col-sm-2">
							<div class="form-group has-feedback">
							<label for="paymentOption" class="control-label">Payment Option(s)</label>
											<select class="form-control input-sm"
														name="hotelTravelRequestQuotation.availablePaymentOption"
														 required="required" >
														<option value="PrePaid" selected="selected">PrePaid</option>
														<option value="PayAtHotel">PayAtHotel</option>
													</select>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
												
							</div>
							   
										<div class="col-sm-2">
												<div class="form-group has-feedback">
													<label for="City" class="control-label">Booking
														Date</label> <input readonly="readonly" type="text"
														autocomplete="off" name="hotelOrderRow.bookingDate"
														class="form-control input-sm" id="bookingDate"
														placeholder="Booking Date" required="required">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											<div class="col-sm-2">
											<div class="form-group has-feedback">
											<label for="flightOrderRow.managementFeesdummy" class="control-label">Product Type<span
														id="mandatory"> * </span></label> 
											<select class="form-control input-sm required productTypeVal"
												id="managementFeesForSend" name="hotelOrderRow.managementFeesdummy" required onchange="getmangmentfee(this)"> 
												<option value="00.00" selected="selected">Select Product Type</option>
												<option value="<s:property value="managementFeeForBookingDomManagemtFee"/>">Domestic</option>
												<option value="<s:property value="managementFeeForBookingIntManagemtFee"/>">International</option>
											</select>
											<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>

											</div>
											
											
											
											 <s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}">
											<div class="col-sm-2">
														<div class="form-group has-feedback">
															<label for="carTravelRequest.entity" class=" control-label">
																Company Entity </label> 
											<select class="form-control input-sm" required onchange="getEntity(this)" name="hotelOrderRow.companyEntityId" id="entity" >
													<option value="" selected="selected">Select Entity</option>
													 <s:iterator value="%{#session.Company.companyEntities}">
														<option value="<s:property value="companyEntityId"/>"  >
															<s:property value="CompanyEntityName" /></option>
													 </s:iterator>
									 
												</select> 	 	 
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
														</div>
													</div>
													
											</s:if>
											
										
											
											 
											
											

											


											<!-- on click more options will open here -->
											

											<!-- <div class="col-sm-12  more-details">
												<div class="row">

													<a class="btn btn-primary collapsed" role="button"
														data-toggle="collapse" href="#filters"
														aria-expanded="true" aria-controls="filters"> <i class="fa fa-filter" aria-hidden="true"></i>
														More
													</a>
												</div>
											</div> -->
											<%-- <div class="col-sm-12 clearfix">
												<div class="row">


													<div class="collapse" id="filters" aria-expanded="true">

														<div class="panel-body">
															<!-- Filter of main info -->
															<div class="row">
																<div class="clearfix">
																	<div class="col-sm-2">
																		<div class="form-group">
																			<label for="City" class=" control-label">Age
																			</label> <input type="text" autocomplete="off"
																				name="orderCustomer.age"
																				class="form-control input-sm" required="required"
																				placeholder="Age">

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
																			<label for="City" class="  control-label">Address2
																			</label> <input type="text" autocomplete="off"
																				name="orderCustomer.address2"
																				class="form-control input-sm" placeholder="Address2">

																		</div>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label for="City" class="  control-label">Zip
																				Code </label> <input type="text" autocomplete="off"
																				name="orderCustomer.zip"
																				class="form-control input-sm" required="required"
																				placeholder="Zip Code">

																		</div>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label for="City" class=" control-label">Phone
																			</label> <input type="text" autocomplete="off"
																				name="orderCustomer.phone"
																				class="form-control input-sm" placeholder="Phone">

																		</div>
																	</div>
																	<div class="col-sm-3">

																		<div class="form-group">
																			<label for="City" class=" control-label">Mobile
																			</label> <input type="text" autocomplete="off"
																				name="orderCustomer.mobile"
																				class="form-control input-sm" placeholder="Mobile">

																		</div>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label for="City" class=" control-label">Email
																			</label> <input type="text" autocomplete="off"
																				name="orderCustomer.email"
																				value=""
																				class="form-control input-sm" placeholder="Email">

																		</div>
																	</div>
																	<div class="col-sm-3">

																		<div class="form-group">
																			<label for="City" class=" control-label">Gender
																			</label> <select class="form-control input-sm"
																				name="orderCustomer.gender">
																				<option value="">Select Gender</option>
																				<option value="M">Male</option>
																				<option value="F">Female</option>
																			</select>

																		</div>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label for="City" class="control-label">Country
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
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label for="City" class="  control-label">State
																			</label> <input type="text" autocomplete="off"
																				name="orderCustomer.state" value=""
																				class="form-control input-sm"
																				placeholder="Type State">

																		</div>
																	</div>

																</div>
															</div>

														</div>
													</div>
												</div>
											</div> --%>
											

										</div>
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>
								<%-- <div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" 
										href="#collapseRm"  > RM Details </a>
								</h4>
							</div>
							<!--/.panel-heading -->
							<div id="collapseRm" class="panel-collapse collapse in">
								<div class="panel-body">
								<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.empCode || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							
									 <input
										type="hidden" autocomplete="off" name="customerNo"
										class="form-control input-sm" required="required" id="customerNo1"
										placeholder="Customer No ">

								
						</s:if>
								<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.empCode || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							
									<div class="col-sm-2">
								<div class="form-group">
									<label for="travelRequestNumber">Travel Request Number</label>
									<input type="number" autocomplete="off"
										name="travelRequestNumber" class="form-control input-sm"
										required="required" placeholder="Travel Request Number">
								</div>
							</div>
							</s:if>
								<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null && rmConfigModel.empCode || !#session.Company.companyRole.isAgent() && !#session.Company.companyRole.isDistributor() && !#session.Company.companyRole.isCorporate()}">
							 <input type="hidden"
										autocomplete="off" name="costCenter" id="costCenter1"
										class="form-control input-sm" required="required"
										placeholder="Cost Center">
								
							</s:if>
							<c:if test="${fieldName[0]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[0]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField1"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.manualField1"/>" >
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.empCode}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode"> Emp Code</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.empCode" id="customerNo"
															class="form-control input-sm" required="required"
															placeholder="Enter emp Code Details" value="<s:property value="configTripDetailsModel.empCode"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.department}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode"> Department</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.department"
															class="form-control input-sm" required="required"
															placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.department"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.costCenter}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode">Cost Center</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.costCenter" id="costCenter"
															class="form-control input-sm" required="required"
															placeholder="Enter costCenter Details" value="<s:property value="configTripDetailsModel.costCenter"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.approverName}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode">Approver Name</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.approverName"
															class="form-control input-sm" required="required"
															placeholder="Enter approverName Details" value="<s:property value="configTripDetailsModel.approverName"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.location}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode">location</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.location"
															class="form-control input-sm" required="required"
															placeholder="Enter location Details" value="<s:property value="configTripDetailsModel.location"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.bussinessUnit}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="empCode">Bussiness Unit</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.bussinessUnit"
															class="form-control input-sm" required="required"
															placeholder="Enter bussiness Unit Details" value="<s:property value="configTripDetailsModel.bussinessUnit"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.projectCode}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="projectCode"> Project Code</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.projectCode"
															class="form-control input-sm" required="required"
															placeholder="Enter projectCode Details" value="<s:property value="configTripDetailsModel.projectCode"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.reasonForTravel}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="reasonForTravel">Reason For Travel</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.reasonForTravel"
															class="form-control input-sm" required="required"
															placeholder="Enter reasonForTravel Details" value="<s:property value="configTripDetailsModel.reasonForTravel"/>">
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.billNonBill}">
											<div class="col-sm-2">
													<div class="form-group">
														<label for="billNonBill">Billing/Non Billing</label> <input
															type="text" autocomplete="off"
															name="configTripDetailsModel.billNonBill"
															class="form-control input-sm" required="required"
															placeholder="Enter billNonBill Details" value="<s:property value="configTripDetailsModel.billNonBill"/>">
													</div>
												</div>
											</c:if>
							<c:if test="${fieldName[1]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[1]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField2"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField2}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[2]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[2]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField3"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField3}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[3]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[3]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField4"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField4}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[4]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode"> ${fieldName[4]}</label> <input type="text"
											autocomplete="off" name="configTripDetailsModel.manualField5"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField5}" >
									</div>
								</div>
							</c:if>
						
						<c:if test="${configTripDetailsModel.manualField1 == null }">		
						<c:forEach items="${fieldName}" var="item" varStatus="counter">
							<div class="col-sm-4">
								<div class="form-group">
									<label for="dynamic"> ${item}</label> <input type="text"
										autocomplete="off"
										name="configTripDetailsModel.manualField${counter.count}"
										class="form-control input-sm" required="required"
										placeholder="Enter field Details">
								</div>
							</div>
						</c:forEach>
						</c:if>
						
						
								</div>
								</div>
								</div> --%>
								<!-- /.panel -->

								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed  " data-toggle="collapse"
												data-parent="#accordion" href="#collapseTwo" > Hotel
												Details </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseTwo" class="panel-collapse ">
										<div class="panel-body">
											<div class="col-sm-2">
												<div class="form-group has-feedback">
													<label for="hotelName" class=" control-label">Hotel
														Name <span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off" required="required"
														name="hotelOrderHotelData.name" id="hotelname"
														class="form-control input-sm " placeholder="Hotel Name"
														value="">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>

												</div>
											</div>
											<%-- <div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Hotel
														City <span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="on"
														name="hotelOrderHotelData.city" id="hotelcity"
														class="form-control input-sm" placeholder="Hotel city"
														value="">

												</div>
											</div> --%>
											<div class="col-sm-2">
												<div class="form-group has-feedback">
													<label for="hotelCategory" class=" control-label">Hotel
														Category <span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off" required="required"
														name="hotelOrderHotelData.hotelCategory" id="hotelcategory"
														class="form-control input-sm" placeholder="Hotel Category"
														value="">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>

												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group has-feedback">
													<label for="hotel address1" class="control-label">Hotel
														Address1 <span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off" required="required"
														name="hotelOrderHotelData.address1" id="hoteladdress"
														class="form-control input-sm " placeholder="Hotel Address1"
														value="">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>

												</div>
											</div>
											<div class="col-sm-2">

												<div class="form-group has-feedback">
													<label for="hotel telephone" class=" control-label"><span
														id="mandatory"> * </span>Telephone </label> <input type="text"
														autocomplete="off" name="hotelOrderHotelData.telephone" required="required"
														class="form-control input-sm" placeholder="telephone" >
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>

												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group has-feedback">
													<label for="hotelLocation" class=" control-label" >Hotel
														Property Amenities <span id="mandatory"> * </span>
													</label>

													<textarea rows="1" cols="4" name="hotelOrderHotelData.propertyAmenities" id="propertyAmenities" required="required" class="form-control input-sm" placeholder="Hotel Property Amenities"></textarea>
												<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											<div class="col-sm-12 clearfix more-details">
												<div class="row">
													<a class="btn btn-primary btn-xs collapsed" role="button"
														data-toggle="collapse" href="#filters-hotel"
														aria-expanded="true" aria-controls="filters-hotel"> <!-- <i class="fa fa-filter" aria-hidden="true"></i> -->
														More
													</a>
												</div>
											</div>
											<div class="col-sm-12 clearfix">
												<div class="row">

													<div class=" collapse" id="filters-hotel"
														aria-expanded="true">

														<div class="panel-body">
															<!-- Filter of main info -->
															<div class="row clearfix">
																<div class="col-sm-2">

																	<div class="form-group ">
																		<label for="hotelChain" class=" control-label">Hotel
																			Chain </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.chain"
																			class="form-control input-sm" 
																			placeholder="Hotel Chain">

																	</div>
																</div>

																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotelName" class=" control-label">Hotel
																			Type </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.type"
																			class="form-control input-sm" 
																			placeholder="Hotel Type">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotelName" class=" control-label">Hotel
																			Stars </label> <input type="number" autocomplete="off"
																			name="hotelOrderHotelData.stars"
																			class="form-control input-sm" 
																			placeholder="Hotel Stars">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotelrank" class=" control-label">Rank
																		</label> <input type="number" autocomplete="off"
																			name="hotelOrderHotelData.rank"
																			class="form-control input-sm" 
																			placeholder="Hotel Rank">
																	</div>
																</div>

																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotelrank" class="  control-label">Region
																			Name </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.regionName"
																			class="form-control input-sm"
																			placeholder="Hotel Region Name">

																	</div>
																</div>
																<div class="col-sm-2">

																	<div class="form-group ">
																		<label for="hotelregionID" class=" control-label">RegionID
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.regionID"
																			class="form-control input-sm"
																			placeholder="Hotel RegionID">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotelregionCityID" class=" control-label">Region
																			CityID </label> <input type="number" autocomplete="off"
																			name="hotelOrderHotelData.regionCityID"
																			class="form-control input-sm"
																			placeholder="Hotel Region CityID">

																	</div>
																</div>

																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotel address2" class=" control-label">Hotel
																			Address2 </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.address2"
																			class="form-control input-sm"
																			placeholder="Hotel Address2">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotel address3" class=" control-label">Hotel
																			Address3 </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.address3"
																			class="form-control input-sm"
																			placeholder="Hotel Address3">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotel state" class=" control-label">Hotel
																			State </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.state"
																			class="form-control input-sm" 
																			placeholder="Hotel state">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotel Country" class="  control-label">Hotel
																			Country </label> <select class="form-control input-sm"
																			name="hotelOrderHotelData.country">
																			<option value="">Select Country</option>
																			<s:iterator value="countryList">
																				<option value="<s:property value="c_name"/>"><s:property
																						value="c_name" /></option>
																			</s:iterator>
																		</select>

																	</div>
																</div>

																<div class="col-sm-2">

																	<div class="form-group ">
																		<label for="hotel email" class=" control-label">Email
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.email"
																			class="form-control input-sm" 
																			placeholder="Email">

																	</div>
																</div>
																<div class="col-sm-2">

																	<div class="form-group ">
																		<label for="url" class=" control-label">Url </label> <input
																			type="text" autocomplete="off"
																			name="hotelOrderHotelData.url"
																			class="form-control input-sm" placeholder="url">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="url" class=" control-label">ZipCode
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.zip"
																			class="form-control input-sm" 
																			placeholder="zip code">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="latitude" class=" control-label">Latitude
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.latitude"
																			class="form-control input-sm" placeholder="Latitude">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="longitude" class=" control-label">Longitude
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.longitude"
																			class="form-control input-sm" placeholder="Longitude">

																	</div>
																</div>
																<div class="col-sm-2">
																	<div class="form-group ">
																		<label for="hotelLocation" class=" control-label">Hotel
																			Location </label>

																		<textarea rows="2" cols="2"
																			name="hotelOrderHotelData.hotelLocation"
																			class="form-control input-sm"
																			placeholder="Hotel Location"></textarea>
																	</div>
																</div>
																<div class="col-sm-2">



																	<div class="form-group ">
																		<label for="Hotel Room Amenities"
																			class=" control-label">Hotel Room Amenities </label>

																		<textarea rows="4" cols="4"
																			name="hotelOrderHotelData.roomAmenities"
																			class="form-control input-sm"
																			placeholder="Hotel Room Amenities"></textarea>
																	</div>
																</div>




															</div>
														</div>
													</div>
												</div>
											</div>
										


										</div>
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>


								<div class="panel panel-default" >
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseRoomDetails"> Room Details</a>
										</h4>
									</div>
									<!--/.panel-heading -->
									 <div id="collapseRoomDetails" class="panel-collapse ">
                    <div class="panel-body"> 
                        <div class="panel-group" id="roomnested">
                            <div class="panel panel-default" style="border: 1px solid #ccc;margin-bottom: 5px" id="roomslength">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="collapsed" data-toggle="collapse" data-parent="#roomnested" href="#nested-room"><b>Room 1</b>
                                        </a>
                                    </h4>
                                </div>
                                <div id="nested-room" class="panel-collapse">
                                    <div class="panel-body">
                                    	<div class="first-block-rooms clearfix">
												<div class="col-sm-4">

													<div class="form-group has-feedback">
														<label for="checkInDate" class=" control-label">Room
															Type <span id="mandatory"> * </span>
														</label> <input type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].roomType" id="roomtype"
															class="form-control input-sm"
															value="" required="required"
															placeholder="Room Type">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>

													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group has-feedback">
														<label for="checkInDate" class="  control-label">Meal
															Type <span id="mandatory"> * </span>
														</label> <input type="text" autocomplete="off" required="required"
															name="hotelOrderRoomInfoList[0].mealType" id="mealtype"
															class="form-control input-sm " placeholder="Meal Type" > 
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>

												<div class="col-sm-4">
													<div class="form-group has-feedback">
														<label for="checkInDate" class=" control-label">Inclusions
															<span id="mandatory"> * </span>
														</label> <input type="text" autocomplete="off" required="required"
															name="hotelOrderRoomInfoList[0].inclusions" id="inclusions"
															class="form-control input-sm " placeholder="Inclusions"
															value="">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>


													</div>
												</div>

											</div>
                                  
                                  
                                  											<!-- nested -->

											<div class="panel-group" id="nested">

												<div class="panel panel-default " style="margin-bottom: 5px;margin-top: 10px;" id="mainpanel">
												
													<div class="panel-heading">
														<h4 class="panel-title">
															<a class="collapsed" data-toggle="collapse" data-parent="#nested" href="#nestedPerson" > Lead Person Details </a>
														</h4>
													</div>
													<!--/.panel-heading -->
													<div id="nestedPerson"
														class="panel-collapse ">
														<div class="panel-body">
														
														<div class="detailswithRM">
															<div class="col-sm-1">
																<div class="form-group has-feedback">
																	<label for="checkInDate" class="control-label">Title
																		<span id="mandatory"> * </span>
																	</label> <select
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].title"
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
																	<label for="checkInDate" class=" control-label">First
																		Name <span id="mandatory"> * </span>
																	</label> <input type="text" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].firstName"
																		class="form-control input-sm " required="required"
																		value=""
																		placeholder="First Name" id="firstName"> <input type="hidden" 
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].leader"
																		value="true"  /> 
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>
																</div>


															</div>
															<input type="hidden" id="totalNumberOfPassenger" value="1">
															<div class="col-sm-2"> 
																<div class="form-group has-feedback">
																	<label for="checkInDate" class=" control-label">Last
																		Name <span id="mandatory"> * </span>
																	</label> <input type="text" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].lastName"
																		class="form-control input-sm " placeholder="Last Name" required="required"
																		value="" id="lastName">
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															<div class="col-sm-1"> 
																<div class="form-group has-feedback">
																	<label for="checkInDate" class=" control-label">GuestType
																		<span id="mandatory"> * </span>
																	</label> <select
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].paxType"
																		class="form-control input-sm">
																		<option selected="selected">Adult</option>
																		<option>Infant</option>
																		<option>Children</option>

																	</select>
																	<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>

															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="checkInDate" class=" control-label">Nationality
																		<span id="mandatory"> * </span>
																	</label> <select
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].nationality"
																		class="form-control input-sm" required>
																		<s:iterator value="countryList">
																			<s:if test="c_name=='India'">
																				<option selected="selected"
																					value="<s:property value="c_name"/>"><s:property
																						value="c_name" /></option>
																			</s:if>
																			<option value="<s:property value="c_name"/>"><s:property
																					value="c_name" /></option>
																		</s:iterator>

																	</select>
																	<span class="form-control-feedback glyphicon glyphicon-ok"></span>


																</div>
															</div>



															<div class="col-sm-2">
																<div class="form-group has-feedback">
																	<label for="checkInDate" class="col-sm-2 control-label">Age
																	</label> <input type="number" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].age" maxlength="3"
																		class="form-control input-sm cusValidationforprice" required="required"
																		placeholder="Age"  >
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>

															<div class="col-sm-2">

																<div class="form-group has-feedback">
																	<label for="checkInDate" class="col-sm-2 control-label">Email
																	</label> <input type="email" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].email"
																		value=""
																		class="form-control input-sm" placeholder="Email" required="required">
																		<span class="form-control-feedback glyphicon glyphicon-ok"></span>

																</div>
															</div>
															
															<c:if test="${fieldName[0]!=null}">
							<div class="col-sm-3">
								<div class="form-group has-feedback">
									<label for="empCode" class="control-label"> ${fieldName[0]}</label> <input
										type="text" autocomplete="off"
										name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.manualField1"
										class="form-control input-sm " required="required"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField1}">
										<span class="form-control-feedback glyphicon glyphicon-ok"></span>
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[1]!=null}">
							<div class="col-sm-3">
								<div class="form-group has-feedback">
									<label for="empCode" class="control-label"> ${fieldName[1]}</label> <input
										type="text" autocomplete="off"
										name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.manualField2"
										class="form-control input-sm " required="required"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField2}">
										<span class="form-control-feedback glyphicon glyphicon-ok"></span>
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[2]!=null}">
							<div class="col-sm-3">
								<div class="form-group has-feedback">
									<label for="empCode" class="control-label"> ${fieldName[2]}</label> <input
										type="text" autocomplete="off"
										name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.manualField3"
										class="form-control input-sm " required="required"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField3}">
										<span class="form-control-feedback glyphicon glyphicon-ok"></span>
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[3]!=null}">
							<div class="col-sm-3">
								<div class="form-group has-feedback">
									<label for="empCode" class="control-label"> ${fieldName[3]}</label> <input
										type="text" autocomplete="off"
										name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.manualField4"
										class="form-control input-sm " required="required"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField4}">
										<span class="form-control-feedback glyphicon glyphicon-ok"></span>
								</div>
							</div>
						</c:if>
						<c:if test="${fieldName[4]!=null}">
							<div class="col-sm-3">
								<div class="form-group has-feedback">
									<label for="empCode" class="control-label"> ${fieldName[4]}</label> <input
										type="text" autocomplete="off"
										name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.manualField5"
										class="form-control input-sm " required="required"
										placeholder="Enter department Details"
										value="${configTripDetailsModel.manualField5}">
										<span class="form-control-feedback glyphicon glyphicon-ok"></span>
								</div>
							</div>
						</c:if>

					
					<c:if test="${rmConfigModel.empCode}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="empCode">Emp Code</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.empCode"
															class="form-control input-sm " required="required" id="rmempCode"
															placeholder="Enter emp Code Details" value="${configTripDetailsModel.empCode}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.department}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="empCode"> Department</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.department"
															class="form-control input-sm " required="required" id="rmDepartment"
															placeholder="Enter department Details" value="${configTripDetailsModel.department}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.costCenter}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="empCode">Cost Center</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.costCenter"
															class="form-control input-sm " required="required" id="rmcostCenter"
															placeholder="Enter costCenter Details" value="${configTripDetailsModel.costCenter}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.approverName}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="empCode">Approver Name</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.approverName"
															class="form-control input-sm " required="required" id="rmapproverName"
															placeholder="Enter approverName Details" value="${configTripDetailsModel.approverName}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.location}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="empCode" >location</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.location"
															class="form-control input-sm " required="required" id="rmlocation"
															placeholder="Enter location Details" value="${configTripDetailsModel.location}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.bussinessUnit}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="empCode">Bussiness Unit</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.bussinessUnit"
															class="form-control input-sm " required="required" id="rmbussinessUnit" 
															placeholder="Enter bussiness Unit Details" value="${configTripDetailsModel.bussinessUnit}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.projectCode}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="projectCode"> Project Code</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.projectCode"
															class="form-control input-sm " required="required" id="rmprojectCode"
															placeholder="Enter projectCode Details" value="${configTripDetailsModel.projectCode}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.reasonForTravel}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="reasonForTravel">Reason For Travel</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.reasonForTravel"
															class="form-control input-sm " required="required" id="rmreasonForTravel"
															placeholder="Enter reasonForTravel Details" value="${configTripDetailsModel.reasonForTravel}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${rmConfigModel.billNonBill}">
											<div class="col-sm-3">
													<div class="form-group has-feedback">
														<label class=" control-label" for="billNonBill">Billing/Non Billing</label> <input
															type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].rmConfigTripDetailsModel.billNonBill"
															class="form-control input-sm " required="required" id="rmbillNonBill"
															placeholder="Enter billNonBill Details" value="${configTripDetailsModel.billNonBill}">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											</c:if> 
											
														</div>
														<!--/.panel-body -->
													</div>
													<!--/.panel-collapse -->
												</div>
												<!-- /.panel -->
													</div> 
											<!-- nested --> 
											
											
											
                                    </div>
                                    <!-- panel body ends here  -->
												<div class="additionalpackage"></div>
												
														<!-- /.panel-group -->
												<div class="clearfix add-remove text-right">
													<a class="btn btn-primary btn-xs" role="button" id="addroom"
														onclick="add(this);"> 
														Add Guest
													</a> <a class="btn btn-primary  btn-xs remove_field" id="removeroom"
														role="button" onclick="remove_field(this)" disabled>
														Remove Guest </a>

												</div>
												
                                  

                                    
                                </div>
                            </div> 
                             <!-- add rooms here -->
                        
				</div> 
                  
				 <div class="additionalrooms"></div> 
                 <!-- add remove room here -->
				<div class="clearfix add-remove">
					<a class="btn btn-primary  btn-xs" role="button" id="addrooms"
						onclick="addrooms(this);"> <!-- onclick="add() onclick="remove_field()" -->
						Add rooms
					</a> <a class="btn btn-primary  btn-xs remove_room" id="removerooms"
						role="button" onclick="remove_rooms(this)" disabled>
						Remove Room </a>

				</div>
				</div> 
                    
                </div> 
            </div> 
        
        <!-- checking rooms --> 
        
        </div>
        
        
        
								<div class="panel panel-default" id="supplier">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapsesupplier">
												Supplier Amount Payable <%-- <span>( Supplier Price  : <span id="totSupplierPrice"></span> )</span> --%>
											</a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapsesupplier" class="panel-collapse">
										<div class="panel-body">
										<div class="col-sm-2">
										<div class="form-group">
												<label for="NetRate" class=" control-label"
													style="color: red;"><b>Selected ManagementFee</b> </label> 
													<input type="text" style="color: red; font-size: 18px"
														disabled="disabled" id="quotedAmount"
														value=""
														class="form-control input-sm">
												</div>
											</div>
										
										
											

										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="Vendor" class=" control-label">Supplier
													Name <span id="mandatory"> * </span>
												</label>
												
													<select class="form-control input-sm" name="hotelOrderRow.apiProvoder"
														id="supplierName" required>
														<option value="" selected="selected">select
															Supplier</option>
														<s:iterator value="ApiProviders">
															<option value="<s:property value="vendorName"/>"><s:property
																	value="vendorName" /></option>
														</s:iterator>
													</select>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													
												</div>
											</div>
											
											<div class="col-sm-2"> 
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">Supplier
													Order Reference <span id="mandatory"> * </span>
												</label>
												 
													<input type="text" autocomplete="off" name="hotelOrderRow.orderReference"
														class="form-control input-sm "
														placeholder="Type Order Refernce" id="orderReference"
														required>
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											
										
										<div class="price-details col-sm-12 row">
										<div class="col-sm-2"> 
											<div class="form-group has-feedback ">
												<label for="NetRate" class=" control-label">Base
													Price <span id="mandatory"> * </span>
												</label>
												 
													<input type="text" autocomplete="off" name="hotelOrderRow.apiPrice"
														class="form-control input-sm cusValidationforprice required baseFareprice digitOnly" required="required"
														placeholder="netRate" id="basePrice" value="0" onchange="numbersonly(this);">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group has-feedback">
												<label for="TxnCharge" class=" control-label">Tax
													<span id="mandatory"> * </span>
												</label>
												 
													<input type="text" autocomplete="off" name="hotelOrderRow.taxes"
														class="form-control input-sm cusValidationforprice digitOnly" required="required"
														placeholder="Tax" id="tax" value="0" onchange="numbersonly(this);">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group has-feedback">
												<label for="NetRate" class="  control-label">Mark
													up <span id="mandatory"> * </span>
												</label>
												 
													<input type="text" autocomplete="off" value="0"
														name="hotelOrderRow.markupAmount" class="form-control input-sm  cusValidationforprice required digitOnly"
														id="markup" required="required" placeholder="mark up" onchange="numbersonly(this);">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="NetRate" class=" control-label">Management Fee <span id="mandatory"> * </span>
												</label>
												 
													<input type="text" autocomplete="off" value="0"
														name="hotelOrderRow.tempManagementFees" class="form-control input-sm"
														id="managementFee" required="required" placeholder="Management Fee" readonly="readonly">
												</div>
											</div>
				
 
												 
													<input type="hidden" autocomplete="off" name="hotelOrderRow.discountAmount"
														value="0" class="form-control input-sm" id="discountAmount"
														required="required" placeholder="Discount Amount">
													<input type="hidden" autocomplete="off" name="hotelOrderRow.feeAmount"
														class="form-control input-sm" value="0">
												</div>
											 
											
											
											 
											 
												<div class="col-sm-12 clearfix">
												<div class="form-group" style="color: red;display: none" id="balanceCheck">
												<h5 style="color: red">Total Booking Amount is more than Deposit Or Wallet Balances.Please Check.</h5>
											</div>
											</div>
									<div class="col-sm-2"> 
										<div class="form-group has-feedback">
												<label for="currency" class="  control-label">Supplier
													Currency <span id="mandatory"> * </span>
												</label>
												 
													<select class="form-control input-sm" required="required"
														name="hotelOrderRow.apiCurrency">
														<option selected="selected"
															value="${hotelQuotation.currency}">${hotelQuotation.currency}</option>
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
										
											<!-- <div class="form-group"> -->
												<%-- <label for="currency" class="col-sm-2 control-label">Supplier
													To INR <span id="mandatory"> * </span>
												</label> --%>
												<!-- <div class="col-sm-8"> -->
													<input type="hidden" class="form-control input-sm"
														required="required" placeholder="Conversion Rate"
														name="hotelOrderRow.apiToBaseExchangeRate" value="1">
												<!-- </div>
											</div> -->
<div class="col-sm-2"> 
											<div class="form-group ">
												<label for="currency" class="  control-label">Supplier
													PaymentType <span id="mandatory"> * </span>
												</label>
												 
													<select class="form-control input-sm"
														id="supplierPaymentType" name="supplierPaymentType">
														<option value="Full" selected="selected">Full Payment</option>
														<option value="Partial">Partial Payment</option>
														<option selected="selected" value="Zero">No Payment</option>
													</select>
												</div> 
											</div>
											
											<div class="col-sm-1" style="margin-top: 25px;">
													<a class="btn btn-info btn-xs" data-toggle="modal"
														href="#suppliermyNotification"><i
														class="fa fa-bell-o fa-sm"></i> Reminder</a>
												</div>
											<div class="col-sm-2 supplierPayment" style="display: none"> 
											<div class="form-group  " >
												<label for="currency" class="  control-label">Amount</label>
												 
													<input type="text" class="form-control input-sm"
														placeholder="amount"
														name="paymentTransaction.apiProviderAmount"
														id="supplierAmount">
												</div>

											</div>
											<div class="col-sm-2 supplierPayment" style="display: none"> 
											<div class="form-group " >
												<label for="currency" class=" control-label">Paid
													By</label>
												 
													<input type="text" class="form-control input-sm"
														placeholder="Paid By"
														name="paymentTransaction.paidBySupplier">
												</div>

											</div>
<!-- <div class="col-sm-2 card-details"> 
											<div class="form-group ">
												<label for="currency" class="  control-label">CARD
													DETAILS </label>
											</div>
											</div> -->
<div class="col-sm-2 no-payment-mode"> 
											<div class="form-group   ">
												<label for="currency" class="  control-label">Payment
													Mode <span id="mandatory"> * </span>
												</label>
												 
													<select class="form-control input-sm"
														id="supplierPaymentMethod"
														name="apiProviderPaymentTransaction.payment_method"
														>
														<option value="card">CARD</option>
														<option value="cash">CASH</option>
														<option value="online">ONLINE</option>

													</select>
												</div>
											</div>

							<div class="col-sm-2 supplier-comments" style="display: none"> 
											<div class="form-group "
												>
												<label for="currency" class="  control-label">Comments
													<span id="mandatory"> * </span>
												</label>
												 
													<textarea class="form-control input-sm"  placeholder="Type text" name="apiProviderPaymentTransaction.response_message"></textarea>
												
												</div>
											</div>


							<div class="col-sm-2"> 
											<div class="form-group has-feedback card-details">
												<label for="currency" class=" control-label">CardHolderName
													<span id="mandatory"> * </span>
												</label>
												 
													<select class="form-control input-sm"
														name="paymentTransaction.supplierCardHolderId" >
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
										<!--/.panel-collapse -->

									</div>


								</div>

							<div class="panel panel-default" id="supplier1">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												  href="#collapseClient"> Client
												Amount Receivables 
											</a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseClient" class="panel-collapse">
										<div class="panel-body">

						
										
										
					<div class="col-sm-2">				
										
						<div class="form-group">
												<label for="NetRate" class="  control-label"
													style="color: red; font-size: 12px"><b>Total 
														Amount</b> </label>
												 
													<input type="text" style="color: red; font-size: 18px"
														disabled="disabled" id="quotedAmount1"
														value=""
														class="form-control input-sm">
												</div>
											</div>
											<div class="col-sm-2"> 
											<input type="hidden" id="taxType" value="GST">
											<input type="hidden" autocomplete="off"
													name="totalGstTax" onchange="numbersonly(this)"
													class="form-control input-sm" id="gstTax" value="0.0"
													>
											<div class="form-group">
												<label for="totalGstTax" >
													Gst Tax Amount</label> 
													<div class="inner-addon right-addon">
      												<span class="glyphicon">@ <span id="gstTaxPer"></span>%</span>
													<input type="text" autocomplete="off"
													name="totalGstTaxAmount" onchange="numbersonly(this)"
													class="form-control input-sm" id="gstTaxAmount" value="0.0"
													required readonly="readonly">
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
													value="0" required readonly="readonly">
											</div>
											</div>
											<!-- <div class="col-sm-2"> 
											<div class="form-group">
												<label for="" >
													CGST  </label> <input type="text" autocomplete="off"
													name="" id="cgstamount"
													class="form-control input-sm" placeholder="CGST"
													value="0" required readonly="readonly">
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="" >
													IGST  </label> <input type="text" autocomplete="off"
													name="" id="igstamount"
													class="form-control input-sm" placeholder="CGST"
													value="0" required readonly="readonly">
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="" >
													SGST  </label> <input type="text" autocomplete="off"
													name="" id="sgstamount"
													class="form-control input-sm" placeholder="CGST"
													value="0" required readonly="readonly">
											</div>
											</div>
											<div class="col-sm-2"> 
											<div class="form-group">
												<label for="" >
													UGST  </label> <input type="text" autocomplete="off"
													name="" id="ugstamount"
													class="form-control input-sm" placeholder="UGST"
													value="0" required readonly="readonly">
											</div>
											</div> -->
											<div class="col-sm-2">
												<div class="form-group has-feedback">
												<label for="currency" class=" control-label">Booking
													Currency <span id="mandatory"> * </span>
												</label>
												 
													<select class="form-control input-sm" required="required"
														name="hotelOrderRow.bookingCurrency">
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
													<input type="hidden" value="1" class="form-control input-sm"
														required="required" placeholder="Conversion Rate"
														name="hotelOrderRow.baseToBookingExchangeRate">
											</div> 
											<%-- <div class="form-group">
												<label for="currency" class="col-sm-2 control-label">INR
													To Booking <span id="mandatory"> * </span>
												</label> --%>
												<!-- <div class="col-sm-8"> -->
													<!-- <input type="hidden" value="1" class="form-control input-sm"
														required="required" placeholder="Conversion Rate"
														name="hotelOrderRow.baseToBookingExchangeRate"> -->
												<!-- </div>
											</div> -->
										<div class="col-sm-2">
											<div class="form-group has-feedback">
												<label for="NetRate" class=" control-label">Booking
													Status <span id="mandatory"> * </span>
												</label>
												 
													<select class="form-control input-sm" required="required"
														name="hotelOrderRow.statusAction">
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
													PaymentType <span id="mandatory"> * </span>
												</label>
												 
													<select class="form-control input-sm"
														name="customerPaymentType" id="clientPaymentType">
														<option value="Full" selected="selected">Full Payment</option>
														<option value="Partial" >Partial Payment</option>
														<option selected="selected" value="Zero">No Payment</option>
													</select>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
												
											</div>
											<div class="col-sm-1" style="margin-top: 25px;">
													<a class="btn btn-info btn-xs" data-toggle="modal"
														href="#clientmyNotification"><i
														class="fa fa-bell-o fa-sm"></i>Reminder</a>
												</div>
											<div class="col-sm-2 clientPayment">
												<div class="form-group has-feedback ">
													<label for="currency" class=" control-label">Amount</label>
													 
														<input type="text" class="form-control input-sm"
															placeholder="amount"
															name="paymentTransaction.clientAmount" id="clientAmount">
															<span class="form-control-feedback glyphicon glyphicon-ok"></span>
													</div>
												</div>
											 
										<div class="col-sm-2 clientPayment">
											<div class="form-group has-feedback ">
												<label for="currency" class=" control-label">Paid
													By</label>
												 
													<input type="text" class="form-control input-sm"
														placeholder="Paid By"
														name="paymentTransaction.paidByClient">
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
										
											<!-- <div class="form-group client-card-details">
												<label for="currency" class="col-sm-2 control-label">CARD
													DETAILS </label>

											</div> -->
											<div class="col-sm-2 no-client-payment-mode">
												<div class="form-group has-feedback  ">
													<label for="currency" class=" control-label">Payment
														Mode <span id="mandatory"> * </span>
													</label> 
													<select class="form-control input-sm"
														id="clientPaymentMethod"
														name="paymentTransaction.payment_method">
														<option value="card">CARD</option>
														<option value="cash" selected="selected">CASH</option>
														<option value="online">ONLINE</option>

													</select>
													<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>
											<div class="col-sm-2 client-comments" style="display: none">
											<div class="form-group has-feedback" >
												<label for="currency" class=" control-label">Comments
													<span id="mandatory"> * </span>
												</label>
												 
													<textarea rows = "1" class="form-control input-sm" required="required" placeholder="Type text" id="comments"
														name="paymentTransaction.response_message"></textarea>
														<span class="form-control-feedback glyphicon glyphicon-ok"></span>
												</div>
											</div>

											<div class="col-sm-2 client-card-details">
												<div class="form-group has-feedback ">
												<label for="currency" class=" control-label">CardHolderName
													<span id="mandatory"> * </span>
												</label> 
													<select class="form-control input-sm"
														name="paymentTransaction.clientCardHolderId" >
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


											<!--/.panel-body -->
										</div>
										<!--/.panel-collapse -->

									</div>


								</div>


							
							<!-- /.panel-group -->
						</div>
					</div>

					<!-- harsha added colapse ended -->

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button   type="button"  id="buttonSubmit"
								class="btn btn-primary btn-lg">Book Now</button>
						</div>
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
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
		<script src="js/rmfeild.js"> </script> 
	<script src="js/city_code.js"></script>
	<script type="text/javascript">
	function numbersonly(thisObject) {
		 var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
		if (!floatRegex.test($(thisObject).val())) {
			if($(thisObject).val('0'))
			$(thisObject).focus();
		} 
	}
		$(".digitOnly")
		.keydown(
				function(e) {
					var isModifierkeyPressed = (e.metaKey
							|| e.ctrlKey || e.shiftKey);
					var isCursorMoveOrDeleteAction = ([
							46, 8, 9, 37, 38, 39,
							40, 110, 190 ]
							.indexOf(e.keyCode) != -1);
					var isNumKeyPressed = (e.keyCode >= 48 && e.keyCode <= 58)
							|| (e.keyCode >= 96 && e.keyCode <= 105);
					var vKey = 86, cKey = 67, aKey = 65;
					switch (true) {
					case isCursorMoveOrDeleteAction:
					case isModifierkeyPressed == false
							&& isNumKeyPressed:
					case (e.metaKey || e.ctrlKey)
							&& ([ vKey, cKey, aKey ]
									.indexOf(e.keyCode) != -1):
						break;
					default:
						e.preventDefault();
					}
				});
		
		
	
	
	</script>
	<script type="text/javascript">
	
		function sendSupplierAndClientNotification(type) {
			console.log(type);
			var transFromDate = null;
			var transToDate = null;
			var transPaidDate = null;
			var transDueDate = null;
			var comments = null;
			var toEmails = null;
			var ccEmails = null;

			var orderId = $('#orderReference').val();
			if (type == 'supplier') {
				transFromDate = $('#supplierFromDate').val();
				transToDate = $('#supplierToDate').val();
				transPaidDate = $('#supplierPaidDate').val();
				transDueDate = $('#supplierDueDate').val();
				comments = $('#supplierComment').val();
				toEmails = $('#supplierToEmails').val();
				ccEmails = $('#supplierCcEmails').val();

			} else if (type == 'client') {
				transFromDate = $('#clientFromDate').val();
				transToDate = $('#clientToDate').val();
				transPaidDate = $('#clientPaidDate').val();
				transDueDate = $('#clientDueDate').val();
				comments = $('#clientComment').val();
				toEmails = $('#clientToEmails').val();
				ccEmails = $('#clientCcEmails').val();
			}

			var txt1 = "";
			if (orderId == '') {
				alert("Please Provide supplier order reference Number");
			} else {
				$
						.ajax({
							url : 'insertHotelPaymentTxHistory',
							type : 'post',
							data : {
								type : type,
								transFromDate : transFromDate,
								transToDate : transToDate,
								transPaidDate : transPaidDate,
								transDueDate : transDueDate,
								comments : comments,
								orderId : orderId,
								toEmails : toEmails,
								ccEmails : ccEmails
							},
							success : function(data) {
								if (data.paymentMap.status == 'supplier') {
									$("#supplierForm").hide();
									$(".modal-footer").hide();
									txt1 = $(
											"<p class='text-center clearfix tt1' style='color:green'></p>")
											.text(
													" Successfully supplier reminder have been set.");
									$("#suppliermyNotification .modal-body")
											.append(txt1);
									$("#supplierForm")[0].reset();
									setTimeout(function() {
										$('#suppliermyNotification').modal(
												'hide');
									}, 1000);
									$('#suppliermyNotification').on(
											'hidden.bs.modal',
											function() {
												$("#supplierForm").show();
												$(".modal-footer").show();
												$(".tt1").hide();
												$('#supplierFromDate')
														.datepicker("setDate",
																new Date());
												$('#supplierToDate')
														.datepicker("setDate",
																new Date());
											});
								}

								else if (data.paymentMap.status == 'client') {
									$("#clientForm").hide();
									$(".modal-footer").hide();
									txt1 = $(
											"<p class='text-center clearfix tt1' style='color:green'></p>")
											.text(
													" Successfully client reminder have been set");
									//txt1 = "Successfully client reminder have been set.";

									$("#clientmyNotification .modal-body")
											.append(txt1);
									$("#clientForm")[0].reset();
									setTimeout(function() {
										$('#clientmyNotification')
												.modal('hide');
									}, 1000);
									$('#clientmyNotification').on(
											'hidden.bs.modal',
											function() {
												$("#clientForm").show();
												$(".modal-footer").show();
												$(".tt1").hide();
												$('#clientFromDate')
														.datepicker("setDate",
																new Date());
												$('#clientToDate').datepicker(
														"setDate", new Date());

											});

								} else {
									txt1 = "We found some error......";
									if (type == client) {
										$("#clientmyNotification .modal-body")
												.append(txt1);
										setTimeout(function() {
											$('#clientmyNotification').modal(
													'hide');
										}, 1000);

									} else {

										$("#suppliermyNotification .modal-body")
												.append(txt1);
										setTimeout(function() {
											$('#clientmyNotification').modal(
													'hide');
										}, 1000);

									}

								}

							}

						});
			}

		}

		$('#supplierFromDate').datepicker(
				{
					//showTimePicker: false,
					minDate : 0,
					dateFormat : 'dd-mm-yy',
					onSelect : function(selectedDate) {
						var date2 = $("#supplierFromDate").datepicker(
								'getDate', '+1d');
						date2.setDate(date2.getDate());
						$("#supplierToDate").datepicker('setDate', date2);
						$("#supplierToDate").datepicker("option", "minDate",
								date2);
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

		$(function() {
			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "HotelTravelRequestList";
			$('#success').click(function() {
				window.location.assign(finalUrl);
				$('#success-alert').hide();

			});
			$('#cancel').click(function() {
				$('#error-alert').hide();

			});
		});

		$(function() {
			var totSupplierPrice = Math.round(parseFloat($('#totSupplierPrice')
					.text()) * 100) / 100;
			$("#tax")
					.keyup(
							function() {
								var tax = Math
										.round(parseFloat($('#tax').val()) * 100) / 100;
								var basePrice = Math.round(parseFloat($(
										'#basePrice').val()) * 100) / 100;
								if ($('#tax').val() != '') {
									$('#totSupplierPrice')
											.text(tax + basePrice);
									$('#totClientPrice').text(tax + basePrice);

								} else {
									$('#totSupplierPrice').text(
											0.00 + basePrice);
									$('#totClientPrice').text(0.00 + basePrice);

								}
								totSupplierPrice = Math.round(parseFloat($(
										'#totSupplierPrice').text()) * 100) / 100;
								

							});
			$("#markup")
					.keyup(
							function() {
								var markup = Math.round(parseFloat($('#markup')
										.val()) * 100) / 100;
								console.log("markup------" + markup);
								if ($('#markup').val() != '') {
									$('#totClientPrice').text(
											markup + totSupplierPrice)

									console
											.log("totSupplierPrice afrer addition------"
													+ totSupplierPrice);
								} else {
									$('#totClientPrice').text(
											0.00 + totSupplierPrice)
								}

							});

			var clientPaymentMethod = $('#clientPaymentMethod').val();
			var supplierPaymentMethod = $('#supplierPaymentMethod').val();
			var clientPaymentType = $('#clientPaymentType').val();
			if (clientPaymentMethod == 'cash'
					|| clientPaymentMethod == 'online') {
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
	</script>
	
	<script type="text/javascript">      
         
	$(document).ready(function(){
		
	
		jQuery.validator.addMethod("baseFareprice",function(value) {
            var startprice = 1;
            return (startprice < parseFloat($('#basePrice').val()));
          }, "please add  base fare");

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
                 return this.optional(element) || /^[0-9.]+$/i.test(value);
             },"This field cannot have Char and symbols.");

            
	    $("#bookingFormId").validate({
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
	            // for demo
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
	    	  $("#bookingFormId").valid();
				
				var firstName=$('#firstname').val();
				var lastName=$('#lastname').val();
				var city=$('#hotelCitySearch').val();
				var checkInDate=$('#checkInDate').val();
				var checkOutDate=$('#checkOutDate').val();
				var checkInTime=$('#checkintime').val();
				var checkOutTime=$('#checkouttime').val();
				var roomscountforval=$('#roomcnt').val();
				var adultCountForVal=$('#adultCount').val();
				var hotelNmae=$('#hotelname').val();
				var hotelCity=$('#hotelcity').val();
				var hotelCategory=$('#hotelcategory').val();
				var hotelAddress=$('#hoteladdress').val();
				var hotelProperties=$('#propertyAmenities').val();
				var roomtypeRoom=$('#roomtype').val();
				var mealtypeRoom=$('#mealtype').val();
				var inclusionsRoom=$('#inclusions').val();
				var firstname1Room=$('#firstName').val();
				var lastname1Room=$('#lastName').val();
				 var managementFeestest = $('#managementFeesForSend').val();
				
				var basePrice = Math.round(parseFloat($('#basePrice').val()) * 100) / 100;
				var markup = Math.round(parseFloat($('#markup').val()) * 100) / 100;
				var tax = Math.round(parseFloat($('#tax').val()) * 100) / 100;
				var managementFeetax = Math.round(parseFloat($('#managementFee').val()) * 100) / 100;
				var discountAmount= Math.round(parseFloat($('#discountAmount').val()) * 100) / 100;
				var quotedAmount = Math
						.round(parseFloat($('#quotedAmount').val()) * 100) / 100;
				var orderId = $('#orderReference').val();
				var clientAmount = ((basePrice + markup + tax + managementFeetax)-discountAmount);
				var bookingDate = $('#bookingDate').val();
				var supplierName=$('#supplierName').val();
				  var entity= $('#entity option:selected').val();
				  
				  var comments=$('#comments').val();
				// $("#bookingFormId").submit(); 
				if(entity==''){
				    	alert("Please select company entity");
				    }
				  else  if (bookingDate == '') {
					alert("Please provide bookingDate");
				}
			    else if(supplierName==''){
			    	alert("Please select supplier name");
			    }
			    
			    else if (orderId == '') {
					alert("Please Provide orderReference");
				}  else if (managementFeestest == '00.00') {
					alert("Please Provide Product Type ");
				} else if (basePrice == 0) {
					alert("Please provide Base price.");
				} else if (firstName == '') {
					alert("Please Provide first Name");
				} else if (lastName == '') {
					alert("Please provide Last Name.");
				} else if (city == '') {
					alert("Please Provide City");
				} else if (checkInDate == '') {
					alert("Please provide CheckIn Date.");
				} else if (checkOutDate == '') {
					alert("Please Provide CheckOut Date");
				} else if (checkInTime == '') {
					alert("Please provide CheckIn Time.");
				}else if (checkOutTime == '') {
					alert("Please Provide CheckOut Time");
				} else if (roomscountforval == 0) {
					alert("Please provide Rooms Count.");
				} else if (hotelNmae == '') {
					alert("Please Provide Hotel Name");
				} else if (hotelCity == '') {
					alert("Please provide Hotel City.");
				} else if (hotelCategory == '') {
					alert("Please Provide Hotel Category");
				} else if (hotelAddress == '') {
					alert("Please provide Hotel Address.");
				} else if (hotelProperties == '') {
					alert("Please Provide Hotel Properties");
				} else if (roomtypeRoom == '') {
						alert("Please Provide RoomType inRoom .");
				} else if (mealtypeRoom == '') {
						alert("Please provide Meal Type In Room.");
				} else if (inclusionsRoom == '') {
						alert("Please Provide Room Inclusions");
				} else if (firstname1Room == '') {
						alert("Please provide firstName in Room.");
				} else if (lastname1Room == '') {
						alert("Please Provide LastName In room");
				}else if (comments == '') {
					alert("Please Provide comments In room");
				}

				  else {
					 
				        if($("#bookingFormId").valid()){
				        	if($('#basePrice').val()<=0){
					        	alert("please enter the base fare");
					        	return false;
					        }
				        	 document.getElementById("bookingFormId").submit();
				        }
				 /* $("#bookingFormId").submit();  */
				/*  document.getElementById("bookingFormId").submit(); */
				}    

	    	  
	    });  

   
	});
	</script>
	<script>
		
		 $('.price-details').on('keyup input', function () {
			   addtax(); 
			  });
		 
		function addtax(){
			var basePrice1 = Math.round(parseFloat($('#basePrice').val()) * 100) / 100;
			var markup1 = Math.round(parseFloat($('#markup').val()) * 100) / 100;
			var tax1 = Math.round(parseFloat($('#tax').val()) * 100) / 100;
			var managementFeetax1 = Math.round(parseFloat($('#managementFee').val()) * 100) / 100;
			var discountAmount1= Math.round(parseFloat($('#discountAmount').val()) * 100) / 100;
			var totalBookingAmount1 = ((basePrice1 + markup1 + tax1 + managementFeetax1)-discountAmount1);
			var depositBalance =${userWallet.depositBalance};
			var walletBalance =${userWallet.walletbalance};
			if (depositBalance > totalBookingAmount1) {
				$('.submitWrap').show();
				$('#balanceCheck').hide();
			}
			else if (walletBalance > totalBookingAmount1) {
				$('.submitWrap').show();
				$('#balanceCheck').hide();
			}
			else{
				$('.submitWrap').hide();
				$('#balanceCheck').show();
			}
		}
		 
		
		
		/* function bookingFormSubmit() {
			
		} */
	</script>

	<script>
	function addrooms(currentObj) { 
		var $rooms = $("#roomnested .panel-default:first").clone(); 
		$rooms.find("input").val(''); 
		$rooms.find("div.additionalpackage .panel-default").remove();
		$('.additionalrooms').append($rooms).insertAfter("#roomslength:last");
		 
		var noOfRooms = $(".additionalrooms").find('#roomslength').length; 
		
		$('#totalNumberOfPassenger').val(noOfRooms+1);
		var managementFeesToCalc=  $('#managementFeesForSend option:selected').val();
		var totalPassenger = $('#totalNumberOfPassenger').val();
		totalManagementFee=managementFeesToCalc*totalPassenger;
		$('#managementFee').val(totalManagementFee);
		$('#quotedAmount').val(totalManagementFee);
		
		//var roomlength = $("#roomscount #rooms").length;  
		 for (var i = noOfRooms; i <= noOfRooms; i++) {
			 $rooms.find('#mainpanel'+ (i)).remove();
		$rooms.find("h4.panel-title a:first").text("Room" + (i+1)); 
		$rooms.find("h4.panel-title a[href='#nested-room']").attr("href", "#nested-room" + (i));
		$rooms.find("div#nested-room").attr("id", "nested-room" + (i)); 
			$rooms.find(".first-block-rooms .form-group input[name^=hotelOrderRoomInfoList]").each(function() {
							var name = $(this).attr('name');
							var indexOfThirdBracket = name.indexOf('[') + 1;
							name = name.substring(0,
									indexOfThirdBracket)
									+ i
									+ name
											.substring(indexOfThirdBracket + 1);
							$(this).attr('name', name);
						});
			 //$rooms.find(".panel-group h4.panel-title a[href='#nestedPerson']").removeClass("in");
		  $rooms.find(".panel-group h4.panel-title a[href='#nestedPerson']").attr("href", "#nestedPerson" + (i)); 
		  $rooms.find("div#nestedPerson").attr("id", "nestedPerson" + (i)); 
		  
		$rooms.find(".panel-group h4.panel-title a").attr("id",
				"nested-collapseOne" + (i)); 
		
		$rooms.find("#nested").attr("id", "nested" + (i));
		
		$rooms.find(".add-remove a:first").attr("id",
				"addroom" + (i));
		$rooms.find(".add-remove a:last").attr("id",
				"removeroom" + (i));
	$rooms.find("div.additionalpackage").attr("class", "additionalpackage" + (i));  
		$(
				' #nested'
						+ i
						+ ' .panel-default .form-group select[name^=hotelOrderRoomInfoList]')
				.each(
						function() {
							var name = $(this).attr('name');
							var indexOfThirdBracket = name.indexOf('[') + 1;
							name = name.substring(0,
									indexOfThirdBracket)
									+ i
									+ name
											.substring(indexOfThirdBracket + 1);
							 
							$(this).attr('name', name);
						});

		$( '#nested'+ i + ' .panel-default .form-group input[name^=hotelOrderRoomInfoList]').each(
						function() {
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
		 $(  '#nested'+ i +' '+ "#firstName" ).autocomplete({
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
		     	
		           $(  '#nested'+ (i-1) +' '+ '#firstName').val(ui.item.firstName);  
		          $(  '#nested'+ (i-1) +' '+'#lastName').val(ui.item.lastName);
		       /*    alert(ui.item.lastName); */
		          console.log("i"+i);
		          console.log("checking last name",$('#nested'+ (i-1) +' '+ '#firstName').val(ui.item.firstName));
		         console.log("checking last name",$('#nested'+ (i-1) +' '+ '#lastName').val(ui.item.lastName));
		         /* console.log("ui.item.lastName",ui.item.lastName); */
		          
		     }, 
		      change: function (event, ui) { console.log("uidddddd",ui);
		      $.ajax({ 
		    	  url: url+"employee/empdetailsById?id="+ui.item.id,
		          dataType: "json",  
		          success: function(datas) {
		             console.log(datas);  
		             if(datas.rmDataListDetails.department!=undefined || datas.rmDataListDetails.department==""){
		            	 $('#nested'+ (i-1) +' '+'#rmDepartment').val(datas.rmDataListDetails.department); 
		            }
		             if(datas.rmDataListDetails.empCode!=undefined || datas.rmDataListDetails.empCode==""){
		            	 $('#nested'+ (i-1) +' '+'#rmempCode').val(datas.rmDataListDetails.empCode); 
		            }
		             if(datas.rmDataListDetails.costCenter!=undefined || datas.rmDataListDetails.costCenter==""){
		             	 $('#nested'+ (i-1) +' '+'#rmcostCenter').val(datas.rmDataListDetails.costCenter); 
		             }
		             
		             if(datas.rmDataListDetails.approverName!=undefined || datas.rmDataListDetails.approverName==""){
		            	 $('#nested'+ (i-1) +' '+'#rmapproverName').val(datas.rmDataListDetails.approverName); 
		            }
		             if(datas.rmDataListDetails.location!=undefined || datas.rmDataListDetails.location==""){
		            	 $('#nested'+ (i-1) +' '+'#rmlocation').val(datas.rmDataListDetails.location); 
		            }
		             if(datas.rmDataListDetails.trNumber!=undefined || datas.rmDataListDetails.trNumber==""){
		            	 $('#nested'+ (i-1) +' '+'#rmtrNumber').val(datas.rmDataListDetails.trNumber); 
		            }
		             if(datas.rmDataListDetails.bussinessUnit!=undefined || datas.rmDataListDetails.bussinessUnit==""){
		            	 $('#nested'+ (i-1) +' '+'#rmbussinessUnit').val(datas.rmDataListDetails.bussinessUnit); 
		            }
		             
		             if(datas.rmDataListDetails.projectCode!=undefined || datas.rmDataListDetails.projectCode==""){
		               	 $('#nested'+ (i-1) +' '+'#rmprojectCode').val(datas.rmDataListDetails.projectCode); 
		               }
		             
		             if(datas.rmDataListDetails.reasonForTravel!=undefined || datas.rmDataListDetails.reasonForTravel==""){
		               	 $('#nested'+ (i-1) +' '+'#rmreasonForTravel').val(datas.rmDataListDetails.reasonForTravel); 
		               }
		               if(datas.rmDataListDetails.billNonBill!=undefined || datas.rmDataListDetails.billNonBill==""){
		                	 $('#nested'+ (i-1) +' '+'#rmbillNonBill').val(datas.rmDataListDetails.billNonBill); 
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
		if ((noOfcust) > 0) {
			$('.remove_room').removeAttr('disabled');
		} else {
			$('.remove_room').attr('disabled', 'disabled');
		}
	}
		
		function remove_rooms(currentObj) { 
			  var parentid = $("#" + currentObj.id).parent().parent().attr('id');
			  //alert(parentid);
			var tobeappenedid = $("#" + currentObj.id).parent().prev().attr(
					'class');
			//alert(tobeappenedid);
			var noOfcust = $("." + tobeappenedid).find('.panel-default').length;

			$('.' + tobeappenedid).find('.panel-default:last-child').remove();
			if (noOfcust <= 0) {
				$('.remove_room').attr('disabled', 'disabled');
			} else {
				$('.remove_room').removeAttr('disabled');
			}
			noOfcust--;  
			
			
			var managementFeesToCalc=  $('#managementFeesForSend option:selected').val();;
			var totalPassenger = $('#totalNumberOfPassenger').val() -1;
			$('#totalNumberOfPassenger').val(totalPassenger);
			totalManagementFee=managementFeesToCalc*totalPassenger;
			$('#managementFee').val(totalManagementFee);
			$('#quotedAmount').val(totalManagementFee);
		} 



		function add1() {
			var noOfcust1 = $("#nested1").find('.panel-default').length;
			var addcustName1 = "";
			$('.additionalpackage1').html("");
			for (var i = 0; i < parseInt(noOfcust1); i++) {
				var index = i + 1;
				addcustName1 += " <div class='panel panel-default'  id='p1"+index+"'> <div class='panel-heading'> <h4 class='panel-title'> <a data-toggle='collapse' href='#nestedtwoo"+index+"'> Guest Details:"
						+ index
						+ " </a> </h4> </div> <div id='nestedtwoo"+index+"' class='panel-collapse collapse'> <div class='panel-body'> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Title </label> <div class='col-sm-8'> <select name='hotelOrderRoomInfoList[1].hotelOrderGuestList["+index+"].title' class='form-control input-sm'> <option value='Mr' selected='selected'>Mr</option> <option value='Mrs'>Mrs</option> <option value='Miss'>Miss</option> </select> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>First Name </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[1].hotelOrderGuestList["+index+"].firstName' class='form-control input-sm'' placeholder='First Name'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Last Name </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[1].hotelOrderGuestList["+index+"].lastName' class='form-control input-sm' placeholder='Last Name'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>GuestType</label> <div class='col-sm-8'> <select name='hotelOrderRoomInfoList[1].hotelOrderGuestList["+index+"].paxType' class='form-control input-sm''> <option selected='selected'>Adult</option> <option>Infant</option> <option>Children</option> </select> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Nationality </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[1].hotelOrderGuestList["+index+"].nationality' class='form-control input-sm' placeholder='Nationality'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Age </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[1].hotelOrderGuestList["+index+"].age' class='form-control input-sm' required='required' placeholder='Age'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Email </label> <div class='col-sm-8'> <input type='email' autocomplete='off' name='hotelOrderRoomInfoList[1].hotelOrderGuestList["+index+"].email' class='form-control input-sm'' placeholder='Email'> </div> </div> </div> </div> </div>";

			}
			$('.additionalpackage1').html(addcustName1);
			if ((noOfcust1) > 0) {
				$('.remove_field').removeAttr('disabled');
			} else {
				$('.remove_field').attr('disabled', 'disabled');
			}
		}

		function remove_field1() {
			var noOfcust1 = $(".additionalpackage1").find('.panel-default').length;

			$('.additionalpackage1 .panel-default:last-child').remove();
			if (noOfcust1 <= 1) {
				$('.remove_field').attr('disabled', 'disabled');
			} else {
				$('.remove_field').removeAttr('disabled');
			}
			noOfcust--;
		}

		function add2() {
			var noOfcust2 = $("#nested2").find('.panel-default').length;
			var addcustName2 = "";
			$('.additionalpackage2').html("");
			for (var i = 0; i < parseInt(noOfcust2); i++) {
				var index = i + 1;
				addcustName2 += " <div class='panel panel-default'  id='p2"+index+"'> <div class='panel-heading'> <h4 class='panel-title'> <a data-toggle='collapse' href='#nestedtwot"+index+"'> Guest Details:"
						+ index
						+ " </a> </h4> </div> <div id='nestedtwot"+index+"' class='panel-collapse collapse'> <div class='panel-body'> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Title </label> <div class='col-sm-8'> <select name='hotelOrderRoomInfoList[2].hotelOrderGuestList["+index+"].title' class='form-control input-sm'> <option value='Mr' selected='selected'>Mr</option> <option value='Mrs'>Mrs</option> <option value='Miss'>Miss</option> </select> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>First Name </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[2].hotelOrderGuestList["+index+"].firstName' class='form-control input-sm'' placeholder='First Name'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Last Name </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[2].hotelOrderGuestList["+index+"].lastName' class='form-control input-sm' placeholder='Last Name'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>GuestType</label> <div class='col-sm-8'> <select name='hotelOrderRoomInfoList[2].hotelOrderGuestList["+index+"].paxType' class='form-control input-sm''> <option selected='selected'>Adult</option> <option>Infant</option> <option>Children</option> </select> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Nationality </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[2].hotelOrderGuestList["+index+"].nationality' class='form-control input-sm' placeholder='Nationality'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Age </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[2].hotelOrderGuestList["+index+"].age' class='form-control input-sm' required='required' placeholder='Age'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Email </label> <div class='col-sm-8'> <input type='email' autocomplete='off' name='hotelOrderRoomInfoList[2].hotelOrderGuestList["+index+"].email' class='form-control input-sm'' placeholder='Email'> </div> </div> </div> </div> </div>";

			}
			$('.additionalpackage2').html(addcustName2);
			if ((noOfcust2) > 0) {
				$('.remove_field').removeAttr('disabled');
			} else {
				$('.remove_field').attr('disabled', 'disabled');
			}
		}

		function remove_field2() {
			var noOfcust2 = $(".additionalpackage2").find('.panel-default').length;

			$('.additionalpackage2 .panel-default:last-child').remove();
			if (noOfcust2 <= 1) {
				$('.remove_field').attr('disabled', 'disabled');
			} else {
				$('.remove_field').removeAttr('disabled');
			}
			noOfcust--;
		}

		function add3() {
			var noOfcust3 = $("#nested3").find('.panel-default').length;
			var addcustName3 = "";
			$('.additionalpackage3').html("");
			for (var i = 0; i < parseInt(noOfcust3); i++) {
				var index = i + 1;
				addcustName3 += " <div class='panel panel-default'  id='p3"+index+"'> <div class='panel-heading'> <h4 class='panel-title'> <a data-toggle='collapse' href='#nestedtwoth"+index+"'> Guest Details:"
						+ index
						+ " </a> </h4> </div> <div id='nestedtwoth"+index+"' class='panel-collapse collapse'> <div class='panel-body'> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Title </label> <div class='col-sm-8'> <select name='hotelOrderRoomInfoList[3].hotelOrderGuestList["+index+"].title' class='form-control input-sm'> <option value='Mr' selected='selected'>Mr</option> <option value='Mrs'>Mrs</option> <option value='Miss'>Miss</option> </select> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>First Name </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[3].hotelOrderGuestList["+index+"].firstName' class='form-control input-sm'' placeholder='First Name'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Last Name </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[3].hotelOrderGuestList["+index+"].lastName' class='form-control input-sm' placeholder='Last Name'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>GuestType</label> <div class='col-sm-8'> <select name='hotelOrderRoomInfoList[3].hotelOrderGuestList["+index+"].paxType' class='form-control input-sm''> <option selected='selected'>Adult</option> <option>Infant</option> <option>Children</option> </select> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Nationality </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[3].hotelOrderGuestList["+index+"].nationality' class='form-control input-sm' placeholder='Nationality'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Age </label> <div class='col-sm-8'> <input type='text' autocomplete='off' name='hotelOrderRoomInfoList[3].hotelOrderGuestList["+index+"].age' class='form-control input-sm' required='required' placeholder='Age'> </div> </div> <div class='form-group'> <label for='checkInDate' class='col-sm-2 control-label'>Email </label> <div class='col-sm-8'> <input type='email' autocomplete='off' name='hotelOrderRoomInfoList[3].hotelOrderGuestList["+index+"].email' class='form-control input-sm'' placeholder='Email'> </div> </div> </div> </div> </div>";

			}
			$('.additionalpackage3').html(addcustName3);
			if ((noOfcust3) > 0) {
				$('.remove_field').removeAttr('disabled');
			} else {
				$('.remove_field').attr('disabled', 'disabled');
			}
		}

		function remove_field3() {
			var noOfcust3 = $(".additionalpackage3").find('.panel-default').length;

			$('.additionalpackage3 .panel-default:last-child').remove();
			if (noOfcust3 <= 1) {
				$('.remove_field').attr('disabled', 'disabled');
			} else {
				$('.remove_field').removeAttr('disabled');
			}
			noOfcust--;
		}
	</script>
	

	<script type="text/javascript">
	$("#checkInDate ").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
		minDate : 0,
		onSelect : function(selectedDate) {
			var date2 = $("#checkInDate").datepicker('getDate', '+1d');
			date2.setDate(date2.getDate() + 1);
			$("#checkOutDate").datepicker('setDate', date2);
			var d1 = $(this).datepicker("getDate");
			$("#checkOutDate").datepicker("option", "minDate", d1);
		},

		onClose : function() {
			$("#checkOutDate").focus();
		}
	}).datepicker("setDate", new Date());
	$("#checkOutDate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
		//minDate : 1,
		onSelect : function(selected) {
			$("#checkInDate").datepicker("option", selected)
		}

	}).datepicker('setDate', '+1d');

		$("#checkintime").timepicker({
			dateFormat : "HH:mm",
		});
		$("#checkouttime").timepicker({
			dateFormat : "HH:mm",
		});
		$("#bookingDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		}).datepicker("setDate", new Date());
 
		
			
		$('#titlefirst').change(function(){
		    $('#title1').val($(this).val());
		});
		
		$('#firstname').change(function(){
		    $('#firstName').val($(this).val());
		});
		$('#lastname').change(function(){
		    $('#lastName').val($(this).val());
		});
		$('#firstname').change(function(){
		    $('#firstName').val($(this).val());
		});
		$('#costCenter').change(function(){
		    $('#costCenter1').val($(this).val());
		});
		$('#customerNo').change(function(){
		    $('#customerNo1').val($(this).val());
		});
		
		$(document).on('keyup', '#roomcnt', function () {
			 $('#roomCntnew').val($(this).val()); 
		
		});
		$(document).on('keyup', '#hotelCitySearch', function () {
			 $('#hotelcity').val($(this).val()); 
		
		});
		
		
		$(document).on('keyup', '#basePrice, #markup,#tax,#managementFee,#discountAmount', function () {
			calculateInvoiceAmount();
		
		});
		
		function calculateInvoiceAmount(){
			var basePrice2 = Math.round(parseFloat($('#basePrice').val()) * 100) / 100;
			var markup2 = Math.round(parseFloat($('#markup').val()) * 100) / 100;
			var tax2 = Math.round(parseFloat($('#tax').val()) * 100) / 100;
			//var managementFeetax2 = Math.round(parseFloat($('#managementFee').val()) * 100) / 100;
			var discountAmount2= Math.round(parseFloat($('#discountAmount').val()) * 100) / 100;
			//var totalBookingAmount2 = ((basePrice2 + markup2 + tax2 + managementFeetax2)-discountAmount2);
			var totalBookingAmount2 = ((basePrice2 + markup2 + tax2)-discountAmount2);
			if(isNaN(totalBookingAmount2))				
				$('#quotedAmount1').val(0);
			else				
				$('#quotedAmount1').val(totalBookingAmount2); 
			//$('#quotedAmount1').val(totalBookingAmount1);
			var managementFee =$('#managementFee').val();
			var gstAmount =$('#gstTax').val();
			var gstTaxAmount =$('#gstTax').val()*(managementFee/100);
			
	        var totalInvoiceAmountCalc=totalBookingAmount2 + parseFloat(gstTaxAmount)+ parseFloat(managementFee) ;
	        if(isNaN(totalInvoiceAmountCalc))				
				$('#invoiceamount').val(0);  
			else				
				$('#invoiceamount').val(totalInvoiceAmountCalc);  
	       		$('#gstTaxAmount').val(gstTaxAmount);
		}
		
		
		
		
			
	</script>
	<script type="text/javascript">
	function getmangmentfee(){
		var sal = $('#managementFeesForSend option:selected').val();
		var totalPassenger = $('#totalNumberOfPassenger').val();
		totalManagementFee=sal*totalPassenger;
		$('#managementFee').val(totalManagementFee);
		  $('#quotedAmount').val(totalManagementFee);
		  var entityId=0;
		  try {
			  entityId=$('#entity option:selected').val();
			  if (entityId == null){
				  entityId=0;
				}
			}
			catch(err) {
				entityId=0;
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
					    	calculateInvoiceAmount();
					    	
					    	
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
	</script>
	
	<script type="text/javascript">
	function getEntity(s) {
		var entityId=s[s.selectedIndex].value;
		var productType = $('#managementFeesForSend option:selected').text();
		var travelType='hotel';
		if (productType === "Domestic" || productType === "International") {
			$.ajax({
			    url : "getEntityTaxes?entityId="+entityId+"&travelType="+travelType+"&productType="+productType,
			    type : "GET", 
			    dataType: "json",
			    success : function(data) {	
			    	$("#gstTax").val(data.commonGstTaxes.totalTax);
			    	$("#gstTaxPer").text(data.commonGstTaxes.totalTax);
			    	$("#gstTaxAmount").val(data.commonGstTaxes.totalTax);
			    	calculateInvoiceAmount();
			    	
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
	
	$(document).on("keyup","#managementFee" ,function(){  
		  $('#quotedAmount').val($('#managementFee').val());
  	});
	</script>
	<script>
 
	function add(currentObj) {
		var parentid = $("#" + currentObj.id).parent().prev().prev().attr('id');  
		console.log(parentid); 
		var tobeappenedid = $("#" + currentObj.id).parent().prev().attr('class');
		console.log(tobeappenedid);  
		var $addrooms = $('#' + parentid).find("#mainpanel").clone();  
		 
		$('.' + tobeappenedid).append($addrooms);  
		var th = $('#' + parentid).next().attr('class');
		var noOfcust = $('.'+th).find('.panel-default').length; 
		   for (var i = 1; i <= parseInt(noOfcust); i++) {  
		  $('.' + tobeappenedid).find(".panel-default").attr("id","mainpanel" + (i));  
			$addrooms.find("h4.panel-title a:first").text(
					"Guest Details" + (i));
			
			var s = $addrooms.find("h4.panel-title a").text();
			var splited = s.split(" ").join("");
			console.log("ssss" + splited);
			var url = $('.' + tobeappenedid).find(
					".panel-default  div.panel-collapse").attr("id")
			console.log("url " + url);

			$addrooms.find("h4.panel-title a").attr("href",
					"#guest" + splited + parentid);
		 
			$('.' + tobeappenedid).find(
					".panel-default:last  div.panel-collapse").attr("id",
					"guest" + splited + parentid).removeClass("in");
			 

			$('.' + tobeappenedid)
					.find('.panel-default:last .form-group ')
					.each(
							function() {
								var prefix = "hotelOrderGuests[" + i + "]";
								var hidden = " ";
								console.log(prefix);
								$(this)
										.find("input")
										.each(
												function() {
													this.name = this.name
															.replace(
																	/hotelOrderGuests\[\d+\]/,
																	prefix);
													
												});
								$(this).find('input[type="hidden"]').attr(
										'name', hidden);
								
								
								
								
								 
								$(this)
										.find("select")
										.each(
												function() {
													this.name = this.name
															.replace(
																	/hotelOrderGuests\[\d+\]/,
																	prefix);
												});
								
							}); 
				 
				 $addrooms.find(".detailswithRM .form-group input ").each(function(){ 
					 var thfirst = $('#' + parentid).next().attr('class');
					   var noOfGust = $('.'+thfirst).find('.panel-default').length;  
					  var id = $(this).attr('id'); 
						if(id=="firstName" || id=="lastName" || id=="lastName" || id=="rmDepartment" || id=="rmempCode" || id=="rmapproverName" || id=="rmlocation" || id=="rmtrNumber" || id=="rmbussinessUnit" || id=="rmprojectCode" || id=="rmreasonForTravel" || id=="rmbillNonBill"){ 
							id = id+(noOfGust);
							$(this).attr('id', id); 
						}
						
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
						 $('.' + tobeappenedid + ' ' + "#firstName"+(noOfGust)).autocomplete({
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
						         $('.' + tobeappenedid + ' ' + "#firstName"+(noOfGust)).val(ui.item.firstName);  
						          $('.' + tobeappenedid + ' ' + "#lastName"+(noOfGust)).val(ui.item.lastName); 
						          console.log("checking last name",$('.' + tobeappenedid + ' ' + "#firstName"+(noOfGust)).val(ui.item.firstName));
						       console.log("checking last name",$('.' + tobeappenedid + ' ' + "#lastName"+(noOfGust)).val(ui.item.lastName));
						         console.log("ui.item.lastName",ui.item.lastName);  
						          
						     }, 
						      change: function (event, ui) { console.log("uidddddd",ui); 
						      $.ajax({ 
						    	  url: url+"employee/empdetailsById?id="+ui.item.id,
						          dataType: "json",  
						          success: function(datas) {
						             console.log(datas);  
						             if(datas.rmDataListDetails.department!=undefined || datas.rmDataListDetails.department==""){
						            	 $('.' + tobeappenedid + ' ' + '#rmDepartment'+(noOfGust)).val(datas.rmDataListDetails.department); 
						            }
						             if(datas.rmDataListDetails.empCode!=undefined || datas.rmDataListDetails.empCode==""){
						            	 $('.' + tobeappenedid + ' ' + '#rmempCode'+(noOfGust)).val(datas.rmDataListDetails.empCode); 
						            }
						             if(datas.rmDataListDetails.costCenter!=undefined || datas.rmDataListDetails.costCenter==""){
						             	 $('#rmcostCenter'+(noOfGust)).val(datas.rmDataListDetails.costCenter); 
						             }
						             
						             if(datas.rmDataListDetails.approverName!=undefined || datas.rmDataListDetails.approverName==""){
						            	 $('.' + tobeappenedid + ' ' + '#rmapproverName'+(noOfGust)).val(datas.rmDataListDetails.approverName); 
						            }
						             if(datas.rmDataListDetails.location!=undefined || datas.rmDataListDetails.location==""){
						            	 $('.' + tobeappenedid + ' ' + '#rmlocation'+(noOfGust)).val(datas.rmDataListDetails.location); 
						            }
						             if(datas.rmDataListDetails.trNumber!=undefined || datas.rmDataListDetails.trNumber==""){
						            	 $('.' + tobeappenedid + ' ' + '#rmtrNumber'+(noOfGust)).val(datas.rmDataListDetails.trNumber); 
						            }
						             if(datas.rmDataListDetails.bussinessUnit!=undefined || datas.rmDataListDetails.bussinessUnit==""){
						            	 $('.' + tobeappenedid + ' ' + '#rmbussinessUnit'+(noOfGust)).val(datas.rmDataListDetails.bussinessUnit); 
						            }
						             
						             if(datas.rmDataListDetails.projectCode!=undefined || datas.rmDataListDetails.projectCode==""){
						               	 $('.' + tobeappenedid + ' ' + '#rmprojectCode'+(noOfGust)).val(datas.rmDataListDetails.projectCode); 
						               }
						             
						             if(datas.rmDataListDetails.reasonForTravel!=undefined || datas.rmDataListDetails.reasonForTravel==""){
						               	 $('.' + tobeappenedid + ' ' + '#rmreasonForTravel'+(noOfGust)).val(datas.rmDataListDetails.reasonForTravel); 
						               }
						               if(datas.rmDataListDetails.billNonBill!=undefined || datas.rmDataListDetails.billNonBill==""){
						                	 $('.' + tobeappenedid + ' ' + '#rmbillNonBill'+(noOfGust)).val(datas.rmDataListDetails.billNonBill); 
						                }  
						          },
						          error: function(datas) { 
						            console.log(datas);
						          }
						        
						        }); 
						     } 
						 }); 
						 }  
				 });  
		   }   
		 
		if ((noOfcust) > 0) {
			$('.remove_field').removeAttr('disabled');
		} else {
			$('.remove_field').attr('disabled', 'disabled');
		}
	}

	function remove_field(currentObj) {
		var parentid = $("#" + currentObj.id).parent().prev().attr('id');
		var tobeappenedid = $("#" + currentObj.id).parent().prev().attr(
				'class');
		var noOfcust = $("#" + parentid).find('.panel-default').length;

		$('.' + tobeappenedid).find('.panel-default:last-child').remove();
		if (noOfcust <= 1) {
			$('.remove_field').attr('disabled', 'disabled');
		} else {
			$('.remove_field').removeAttr('disabled');
		}
		noOfcust--;
	}


	
	
	</script>
	
	 

</body>
</html>