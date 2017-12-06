<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> </title>
<%-- <script src="js/angular.js" type="text/javascript"></script>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> --%>

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

#mandatory {
	color: red;
}
</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
				<div class="col-sm-3 clearfix pull-left">
					<h3>Hotel Booking</h3>
				</div>
				<div class="col-sm-9 clearfix pull-right " data-placement="top">
					<div class="row">
						<div class="col-sm-6 clearfix pull-left " data-placement="top">
							<a
								href="hotelTravelRequestEdit?id=${hotelQuotation.hotelTravelRequest.id}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-edit"></i> Travel Request
							</a> <a
								href="hotelOrderQuotationView?id=${hotelQuotation.hotelTravelRequest.id}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-eye"></i> Travel Request
							</a> <a
								href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${hotelQuotation.hotelTravelRequest.id}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-plus"></i> Quotation
							</a> <a
								href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${hotelQuotation.hotelTravelRequest.id}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-list"></i> Quotations
							</a>
						</div>
						<div class="col-sm-4 clearfix " data-placement="top">
							<a href="goTrips" class="btn btn-top-link btn-xs"> All Trips
							</a> <a href="HotelTravelRequestList" class="btn btn-top-link btn-xs"
								title="Check Hotel Trips"> Hotel Trips </a> <a
								href="FlightTravelRequestList" class="btn btn-top-link btn-xs"
								title="Check Flight Trips"> Flight Trips </a>
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
			<div class="row" id="dash-us-register">
				<form class="form-horizontal" name="myForm">
					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<tr>
							<th>Title</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>CheckInDate</th>
							<th>CheckOutDate</th>
							<th>Nights</th>
						</tr>
						<tr>
							<th>${hotelQuotation.hotelTravelRequest.title}</th>
							<th>${hotelQuotation.hotelTravelRequest.firstName}</th>
							<th>${hotelQuotation.hotelTravelRequest.lastName}</th>
							<th>${hotelQuotation.hotelTravelRequest.checkIn}</th>
							<th>${hotelQuotation.hotelTravelRequest.checkOut}</th>
							<th>${hotelQuotation.hotelTravelRequest.noOfNights}</th>
						</tr>
					</table>
				</form>
			</div>
			<div class="row">
				<!-- createHoteOfflineBooking  -->
				<form action="createHotelOfflineBooking" method="post"
					class="form-horizontal" name="myForm" id="bookingFormId">
					<div id="myfform">
						<input type="hidden" name="hotelRequestQuotationId"
							value="<s:property value="hotelRequestQuotationId"/>"> <input
							type="hidden" name="checkIn"
							value="${hotelQuotation.hotelTravelRequest.checkIn}"> <input
							type="hidden" name="checkOut"
							value="${hotelQuotation.hotelTravelRequest.checkOut}"> <input
							type="hidden" name="noOfRooms" id="roomCount"
							value="${hotelQuotation.roomCount}"> <input type="hidden"
							name="noOfnightsCount" id="noOfnightsCount"
							value="${hotelQuotation.nightCount}">
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
											<div class="col-sm-3">
												<div class="form-group">
													<label for="checkInDate" class="  control-label">Title<span
														id="mandatory"> * </span>
													</label> <select name="orderCustomer.title"
														class="form-control input-sm">
														<c:if
															test="${hotelQuotation.hotelTravelRequest.title=='Mr'}">
															<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
														</c:if>
														<c:if
															test="${hotelQuotation.hotelTravelRequest.title=='Mrs'}">
															<option value="Mr" >Mr</option>
													<option value="Mrs" selected="selected">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
														</c:if>
														<c:if
															test="${hotelQuotation.hotelTravelRequest.title=='Miss'}">
															<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss" selected="selected">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
														</c:if>
														<c:if
															test="${hotelQuotation.hotelTravelRequest.title=='Master'}">
															<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master" selected="selected">Master</option>
														</c:if>
														<c:if
															test="${hotelQuotation.hotelTravelRequest.title=='Ms'}">
															<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms" selected="selected">Ms</option>
													<option value="Master">Master</option>
														</c:if>
														

													</select>

												</div>
											</div>

											<div class="col-sm-3">

												<div class="form-group">
													<label for="City" class=" control-label">First Name
														<span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off"
														name="orderCustomer.firstName"
														value="${hotelQuotation.hotelTravelRequest.firstName}"
														class="form-control input-sm" placeholder="First Name">

												</div>
											</div>
											<div class="col-sm-3">

												<div class="form-group">
													<label for="City" class="  control-label">Last Name
														<span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off"
														value="${hotelQuotation.hotelTravelRequest.lastName}"
														name="orderCustomer.lastName"
														class="form-control input-sm" required="required"
														placeholder="Last Name">

												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label for="City" class=" control-label">City <span
														id="mandatory"> * </span></label> <input type="text"
														value="${hotelQuotation.hotelCity}" autocomplete="off"
														name="orderCustomer.city" class="form-control input-sm"
														placeholder="City">

												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label for="City" class="control-label">Booking
														Date</label> <input readonly="readonly" type="text"
														autocomplete="off" name="hotelOrderRow.bookingDate"
														class="form-control input-sm" id="bookingDate"
														placeholder="Booking Date">
												</div>
											</div>
											  <s:if test="%{#session.Company.companyEntities!=null && #session.Company.companyEntities.size()>0}">
											<div class="col-sm-3">
							<div class="form-group">
								<label for="hotelName">Company Entity </label> 
								<select class="form-control input-sm" name="hotelOrderRow.companyEntityId" id="entity" >
										 <s:iterator value="%{#session.Company.companyEntities}">
											<option value="<s:property value="companyEntityId"/>"
												selected>
												<s:property value="CompanyEntityName" /></option>
										</s:iterator>
									
										</select> 
							</div>
						</div>
  							</s:if>
											<!-- +++++++++++ additoonal content comes according to guest -->

											<!-- <div class="room-customer-name">
											<div class="col-sm-6">
				               <div class="form-group" id="emp">
										<label for="hotelName">Employee Name </label>
										
											<input type="text" autocomplete="off" name="empName"
												class="form-control input-sm"
												placeholder="Customer Name">
				
										</div>
                				</div>
                				
                				<div class="additionalpackage">
                				
                				</div>
                				<div class="col-sm-12 clearfix">
                						<a class="btn btn-primary" role="button" onclick="add()">
									Add More
								</a>
								<a class="btn btn-primary remove_field" role="button" onclick="remove_field()" disabled>
									Remove
								</a>
								
                				</div>
                				
                				</div> -->


											<!-- additoonal content comes according to guest ends-->





											<!-- on click more options will open here -->

											<div class="col-sm-12  more-details">
												<div class="row">

													<a class="btn btn-primary collapsed" role="button"
														data-toggle="collapse" href="#filters"
														aria-expanded="true" aria-controls="filters"> <!-- <i class="fa fa-filter" aria-hidden="true"></i> -->
														More
													</a>
												</div>
											</div>
											<div class="col-sm-12 clearfix">
												<div class="row">


													<div class="collapse" id="filters" aria-expanded="true">

														<div class="panel-body">
															<!-- Filter of main info -->
															<div class="row">
																<div class="clearfix">
																	<div class="col-sm-3">
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
																				value="${hotelQuotation.hotelTravelRequest.alternativeEmail}"
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
																				<option value="ADT">Adult</option>
																				<option value="INF">Infant</option>
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
											</div>

										</div>
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>
								<!-- /.panel -->

								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseTwo"> Hotel
												Details </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseTwo" class="panel-collapse collapse">
										<div class="panel-body">
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelName" class=" control-label">Hotel
														Name <span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.name"
														class="form-control input-sm" placeholder="Hotel Name"
														value="${hotelQuotation.hotelName}">

												</div>
											</div>
											<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Hotel
														City <span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.city"
														class="form-control input-sm" placeholder="Hotel city"
														value="${hotelQuotation.hotelCity}">

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Hotel
														Category <span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.hotelCategory"
														class="form-control input-sm" placeholder="Hotel Category"
														value="${hotelQuotation.hotelCategory}">

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Hotel
														Address1 <span id="mandatory"> * </span>
													</label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.address1"
														class="form-control input-sm" placeholder="Hotel Address1"
														value="${hotelQuotation.hotelAddress}">

												</div>
											</div>
											<div class="col-sm-3">

												<div class="form-group">
													<label for="hotel telephone" class=" control-label"><span
														id="mandatory"> * </span>Telephone </label> <input type="text"
														autocomplete="off" name="hotelOrderHotelData.telephone"
														class="form-control input-sm" placeholder="telephone">

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelLocation" class=" control-label">Hotel
														Property Amenities <span id="mandatory"> * </span>
													</label>

													<textarea rows="1" cols="4"
														name="hotelOrderHotelData.propertyAmenities"
														class="form-control input-sm"
														placeholder="Hotel Property Amenities"><c:if
															test="${hotelQuotation.internet=='no'}"> No Internet,</c:if><c:if
															test="${hotelQuotation.internet=='yes'}">Internet,</c:if><c:if
															test="${hotelQuotation.breakfast=='yes'}">Breakfast</c:if><c:if
															test="${hotelQuotation.breakfast=='no'}"> No Breakfast</c:if>
															
															</textarea>
												</div>
											</div>
											<!-- on click more options will open here    <c:if test="${hotelQuotation.breakfast=='yes'}">Breakfast,</c:if>-->

											<div class="col-sm-12 clearfix more-details">
												<div class="row">
													<a class="btn btn-primary collapsed" role="button"
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
																<div class="col-sm-3">

																	<div class="form-group">
																		<label for="hotelChain" class=" control-label">Hotel
																			Chain </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.chain"
																			class="form-control input-sm" required="required"
																			placeholder="Hotel Chain">

																	</div>
																</div>

																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotelName" class=" control-label">Hotel
																			Type </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.type"
																			class="form-control input-sm" required="required"
																			placeholder="Hotel Type">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotelName" class=" control-label">Hotel
																			Stars </label> <input type="number" autocomplete="off"
																			name="hotelOrderHotelData.stars"
																			class="form-control input-sm" required="required"
																			placeholder="Hotel Stars">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotelrank" class=" control-label">Rank
																		</label> <input type="number" autocomplete="off"
																			name="hotelOrderHotelData.rank"
																			class="form-control input-sm" required="required"
																			placeholder="Hotel Rank">
																	</div>
																</div>

																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotelrank" class="  control-label">Region
																			Name </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.regionName"
																			class="form-control input-sm"
																			placeholder="Hotel Region Name">

																	</div>
																</div>
																<div class="col-sm-3">

																	<div class="form-group">
																		<label for="hotelregionID" class=" control-label">RegionID
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.regionID"
																			class="form-control input-sm"
																			placeholder="Hotel RegionID">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotelregionCityID" class=" control-label">Region
																			CityID </label> <input type="number" autocomplete="off"
																			name="hotelOrderHotelData.regionCityID"
																			class="form-control input-sm"
																			placeholder="Hotel Region CityID">

																	</div>
																</div>

																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotel address2" class=" control-label">Hotel
																			Address2 </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.address2"
																			class="form-control input-sm"
																			placeholder="Hotel Address2">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotel address3" class=" control-label">Hotel
																			Address3 </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.address3"
																			class="form-control input-sm"
																			placeholder="Hotel Address3">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotel state" class=" control-label">Hotel
																			State </label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.state"
																			class="form-control input-sm" required="required"
																			placeholder="Hotel state">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
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

																<div class="col-sm-3">

																	<div class="form-group">
																		<label for="hotel email" class=" control-label">Email
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.email"
																			class="form-control input-sm" required="required"
																			placeholder="Email">

																	</div>
																</div>
																<div class="col-sm-3">

																	<div class="form-group">
																		<label for="url" class=" control-label">Url </label> <input
																			type="text" autocomplete="off"
																			name="hotelOrderHotelData.url"
																			class="form-control input-sm" placeholder="url">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="url" class=" control-label">ZipCode
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.zip"
																			class="form-control input-sm" required="required"
																			placeholder="zip code">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="latitude" class=" control-label">Latitude
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.latitude"
																			class="form-control input-sm" placeholder="Latitude">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="longitude" class=" control-label">Longitude
																		</label> <input type="text" autocomplete="off"
																			name="hotelOrderHotelData.longitude"
																			class="form-control input-sm" placeholder="Longitude">

																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label for="hotelLocation" class=" control-label">Hotel
																			Location </label>

																		<textarea rows="2" cols="2"
																			name="hotelOrderHotelData.hotelLocation"
																			class="form-control input-sm"
																			placeholder="Hotel Location"></textarea>
																	</div>
																</div>
																<div class="col-sm-3">



																	<div class="form-group">
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


								<div class="panel panel-default" id="rooms">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseThree"> Room Details</a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<c:forEach  begin="0" end="2">
									
									<div id="collapseThree" class="panel-collapse collapse">
										<div class="panel-body">

											<p class="h4">
												<b>Room 1:</b>
											</p>


											<div class="first-block-rooms clearfix">
												<div class="col-sm-3">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Room
															Type <span id="mandatory"> * </span>
														</label> <input type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].roomType"
															class="form-control input-sm"
															value="${hotelQuotation.roomType}"
															placeholder="Meal Type">

													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label for="checkInDate" class="  control-label">Meal
															Type <span id="mandatory"> * </span>
														</label> <input type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].mealType"
															class="form-control input-sm" placeholder="Meal Type"
															<c:if test="${hotelQuotation.breakfast=='yes'}">value="Breakfast"</c:if>>

													</div>
												</div>

												<div class="col-sm-3">


													<div class="form-group">
														<label for="checkInDate" class=" control-label">Inclusions
															<span id="mandatory"> * </span>
														</label> <input type="text" autocomplete="off"
															name="hotelOrderRoomInfoList[0].inclusions"
															class="form-control input-sm" placeholder="Inclusions"
															value="<c:if test="${hotelQuotation.internet=='no'}"> No Internet,</c:if><c:if
															test="${hotelQuotation.internet=='yes'}">Internet,</c:if><c:if
															test="${hotelQuotation.breakfast=='yes'}">Breakfast,</c:if><c:if
															test="${hotelQuotation.breakfast=='no'}"> No Breakfast,</c:if><c:if
															test="${hotelQuotation.taxes=='yes'}">Taxes</c:if><c:if
															test="${hotelQuotation.taxes=='no'}">No Taxes</c:if>">


													</div>
												</div>

											</div>

											<!-- nested colapse happens here -->
											<!-- nested -->

											<div class="panel-group" id="nested">

												<div class="panel panel-default">
													<div class="panel-heading">
														<h4 class="panel-title">
															<a data-toggle="collapse" data-parent="#nested"
																href="#nested-collapseOne"> Lead Person Details </a>
														</h4>
													</div>
													<!--/.panel-heading -->
													<div id="nested-collapseOne"
														class="panel-collapse collapse in">
														<div class="panel-body">
															<div class="col-sm-3">
																<div class="form-group">
																	<label for="checkInDate" class=" control-label">Title
																		<span id="mandatory"> * </span>
																	</label> <select
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].title"
																		class="form-control input-sm">
																		<c:if
																			test="${hotelQuotation.hotelTravelRequest.title=='Mr'}">
																			<option value="Mr" selected="selected">Mr</option>
																			<option value="Mrs">Mrs</option>
																			<option value="Miss">Miss</option>
																			<option value="Master">Master</option>
																		</c:if>
																		<c:if
																			test="${hotelQuotation.hotelTravelRequest.title=='Mrs'}">
																			<option value="Mr">Mr</option>
																			<option value="Mrs" selected="selected">Mrs</option>
																			<option value="Miss">Miss</option>
																			<option value="Master">Master</option>
																		</c:if>
																		<c:if
																			test="${hotelQuotation.hotelTravelRequest.title=='Miss'}">
																			<option value="Mr">Mr</option>
																			<option value="Mrs">Mrs</option>
																			<option value="Miss" selected="selected">Miss</option>
																			<option value="Master">Master</option>
																		</c:if>
																		<c:if
																			test="${hotelQuotation.hotelTravelRequest.title=='Master'}">
																			<option value="Mr">Mr</option>
																			<option value="Mrs">Mrs</option>
																			<option value="Miss" >Miss</option>
																			<option value="Master" selected="selected">Master</option>
																		</c:if>

																	</select>

																</div>
															</div>
															<div class="col-sm-3">

																<div class="form-group">
																	<label for="checkInDate" class=" control-label">First
																		Name <span id="mandatory"> * </span>
																	</label> <input type="text" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].firstName"
																		class="form-control input-sm"
																		value="${hotelQuotation.hotelTravelRequest.firstName}"
																		placeholder="First Name"> <input type="hidden"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].leader"
																		value="true" />


																</div>


															</div>
															<div class="col-sm-3">

																<div class="form-group">
																	<label for="checkInDate" class=" control-label">Last
																		Name <span id="mandatory"> * </span>
																	</label> <input type="text" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].lastName"
																		class="form-control input-sm" placeholder="Last Name"
																		value="${hotelQuotation.hotelTravelRequest.lastName}">

																</div>
															</div>
															<div class="col-sm-3">

																<div class="form-group">
																	<label for="checkInDate" class=" control-label">GuestType
																		<span id="mandatory"> * </span>
																	</label> <select
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].paxType"
																		class="form-control input-sm">
																		<option selected="selected">Adult</option>
																		<option>Infant</option>
																		<option>Children</option>

																	</select>

																</div>
															</div>

															<div class="col-sm-3">
																<div class="form-group">
																	<label for="checkInDate" class=" control-label">Nationality
																		<span id="mandatory"> * </span>
																	</label> <select
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].nationality"
																		class="form-control input-sm"
																		placeholder="Nationality" required>
																		<s:iterator value="CountryList">
																			<s:if test="c_name=='India'">
																				<option selected="selected"><s:property
																						value="c_name"></s:property></option>
																			</s:if>
																			<s:if test="c_name !='India'">
																				<option><s:property value="c_name"></s:property></option>
																			</s:if>
																		</s:iterator>

																	</select>

																	<!-- <input type="text" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].nationality"
																		class="form-control input-sm"
																		placeholder="Nationality"> -->

																</div>
															</div>



															<%-- <div class="form-group">
							<label for="Country">Country</label> <select
								class="form-control input-sm" name="Countryname" id="country"
								required>
								<s:iterator    value="CountryList">
								<s:if test="c_name=='India'">
								<option selected="selected"><s:property value="c_name"></s:property></option>
								</s:if>
								<s:if test="c_name !='India'">
								<option  ><s:property value="c_name"></s:property></option>
								</s:if>	
								</s:iterator>

							</select>
						</div> --%>


															<div class="col-sm-3">
																<div class="form-group">
																	<label for="checkInDate" class="col-sm-2 control-label">Age
																	</label> <input type="text" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].age"
																		class="form-control input-sm" required="required"
																		placeholder="Age">

																</div>
															</div>

															<div class="col-sm-3">

																<div class="form-group">
																	<label for="checkInDate" class="col-sm-2 control-label">Email
																	</label> <input type="email" autocomplete="off"
																		name="hotelOrderRoomInfoList[0].hotelOrderGuests[0].email"
																		value="${hotelQuotation.hotelTravelRequest.alternativeEmail}"
																		class="form-control input-sm" placeholder="Email">

																</div>
															</div>
														</div>
														<!--/.panel-body -->
													</div>
													<!--/.panel-collapse -->
												</div>
												<!-- /.panel -->

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
											<!-- /.panel-group -->
											<!-- nested -->
										</div>
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
									
									</c:forEach>
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
									<div id="collapsesupplier" class="panel-collapse collapse">
										<div class="panel-body">
											<div class="form-group">
												<label for="NetRate" class="col-sm-2 control-label"
													style="color: red; font-size: 18px"><b>Quoted
														Price</b> </label>
												<div class="col-sm-8">
													<input type="text" style="color: red; font-size: 18px"
														disabled="disabled" id="quotedAmount"
														value="${hotelQuotation.roomRatePerNight}"
														class="form-control input-sm">
												</div>
											</div>


											<div class="form-group">
												<label for="Vendor" class="col-sm-2 control-label">Supplier
													Name <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" name="apiProvoder"
														id="trainexpenseCurrency" required>
														<option value="" selected="selected">select
															Supplier</option>
														<s:iterator value="ApiProviders">
															<option value="<s:property value="vendorName"/>"><s:property
																	value="vendorName" /></option>
														</s:iterator>
													</select>
													<%-- <select  class="form-control input-sm" required="required"  name="apiProvoder">
						 <option value="2">Tayyarah</option>
						<option value="1">Expedia</option>
							<option value="3">Desiya</option>
							<option value="4">Expedia/Eem</option>
							<option value="5">Offline Akbar</option>
							<option value="6">Akbar</option>
							<option value="7">TBO</option>
							<option value="8">Booking.Com</option>
						</select> --%>
												</div>
											</div>

											<div class="form-group">
												<label for="NetRate" class="col-sm-2 control-label">Supplier
													Order Reference <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<input type="text" autocomplete="off" name="orderReference"
														class="form-control input-sm"
														placeholder="Type Order Refernce" id="orderReference"
														required>
												</div>
											</div>
										<div class="price-details">
											<div class="form-group">
												<label for="NetRate" class="col-sm-2 control-label">Base
													Price <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<input type="text" autocomplete="off" name="apiPrice"
														class="form-control input-sm" required="required"
														placeholder="netRate" id="basePrice" value="0">
												</div>
											</div>
											<div class="form-group">
												<label for="TxnCharge" class="col-sm-2 control-label">Tax
													<span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<input type="text" autocomplete="off" name="taxes"
														class="form-control input-sm" required="required"
														placeholder="Tax" id="tax" value="0">
												</div>
											</div>
											<div class="form-group">
												<label for="NetRate" class="col-sm-2 control-label">Mark
													up <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<input type="text" autocomplete="off" value="0"
														name="markupAmount" class="form-control input-sm"
														id="markup" required="required" placeholder="mark up">
												</div>
											</div>
											
											<div class="form-group">
												<label for="NetRate" class="col-sm-2 control-label">Management Fee <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<input type="text" autocomplete="off" value="<s:property value="managementFeeForBooking" />"
														name="tempManagementFees" class="form-control input-sm"
														id="managementFee" required="required" placeholder="Management Fee">
												</div>
											</div>
				

											<div class="form-group">
												<label for="TxnCharge" class="col-sm-2 control-label">Discount
													Amount </label>
												<div class="col-sm-8">
													<input type="text" autocomplete="off" name="discountAmount"
														value="0" class="form-control input-sm" id="discountAmount"
														required="required" placeholder="Discount Amount">
													<input type="hidden" autocomplete="off" name="feeAmount"
														class="form-control input-sm" value="0">
												</div>
											</div>
											
											<div class="form-group" style="color: red;display: none" id="balanceCheck">
												<h5 style="color: red">Total Booking Amount is more than Deposit Or Wallet Balances.Please Check.</h5>
											</div>
											 
											</div>


											<div class="form-group">
												<label for="currency" class="col-sm-2 control-label">Supplier
													Currency <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" required="required"
														name="apiCurrency">
														<option selected="selected"
															value="${hotelQuotation.currency}">${hotelQuotation.currency}</option>
														<s:iterator value="countryList">
															<option value="<s:property value="cur_code"/>"><s:property
																	value="cur_code" /></option>
														</s:iterator>

													</select>
												</div>
											</div>
											<div class="form-group">
												<label for="currency" class="col-sm-2 control-label">Supplier
													To INR <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														required="required" placeholder="Conversion Rate"
														name="apiToBaseExchangeRate" value="1">
												</div>
											</div>

											<div class="form-group">
												<label for="currency" class="col-sm-2 control-label">Supplier
													PaymentType <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														id="supplierPaymentType" name="supplierPaymentType">
														<option value="Full" selected="selected">Full
															Payment</option>
														<option value="Partial">Partial Payment</option>
														<option value="Zero">No Payment</option>
													</select>
												</div>
												<div class="col-sm-2 pull-right">
													<a class="btn btn-info btn-xs" data-toggle="modal"
														href="#suppliermyNotification"><i
														class="fa fa-bell-o fa-sm"></i> Reminder</a>
												</div>
											</div>
											<div class="form-group supplierPayment" style="display: none">
												<label for="currency" class="col-sm-2 control-label">Amount</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														placeholder="amount"
														name="paymentTransaction.apiProviderAmount"
														id="supplierAmount">
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

											<div class="form-group  no-payment-mode">
												<label for="currency" class="col-sm-2 control-label">Payment
													Mode <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														id="supplierPaymentMethod"
														name="apiProviderPaymentTransaction.payment_method"
														required>
														<option value="card">CARD</option>
														<option value="cash">CASH</option>
														<option value="online">ONLINE</option>

													</select>
												</div>
											</div>


											<div class="form-group supplier-comments"
												style="display: none">
												<label for="currency" class="col-sm-2 control-label">Comments
													<span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<textarea class="form-control input-sm" required="required"
														placeholder="Type text"
														name="apiProviderPaymentTransaction.response_message"></textarea>
												</div>
											</div>



											<div class="form-group card-details">
												<label for="currency" class="col-sm-2 control-label">CardHolderName
													<span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="paymentTransaction.supplierCardHolderId" required>
														<option value="">Select card holder name</option>
														<s:iterator value="paymentCardList">
															<s:if test="paymentType==1">
																<option value="${id}">${userName}(${cardNumber})</option>
															</s:if>
														</s:iterator>
													</select>
												</div>
											</div>

										</div>
										<!--/.panel-collapse -->

									</div>


								</div>

								<div class="panel panel-default" id="supplier">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseClient"> Client
												Amount Receivables<%-- <span>( Client Price  : <span id="totClientPrice">${hotelQuotation.basePrice}</span> )</span> --%>
											</a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseClient" class="panel-collapse collapse">
										<div class="panel-body">

											<!--  <div class="form-group">
						<label for="Vendor" class="col-sm-2 control-label">Client Name
						</label>
						<div class="col-sm-8">
						<input type="text"  class="form-control input-sm"  required="required" placeholder="Client Name"  name="paymentTransaction.clientName">
						 </div>
						</div>  -->
											<div class="form-group">
												<label for="NetRate" class="col-sm-2 control-label"
													style="color: red; font-size: 18px"><b>Quoted
														Price</b> </label>
												<div class="col-sm-8">
													<input type="text" style="color: red; font-size: 18px"
														disabled="disabled" id="quotedAmount"
														value="${hotelQuotation.roomRatePerNight}"
														class="form-control input-sm">
												</div>
											</div>

											<div class="form-group">
												<label for="currency" class="col-sm-2 control-label">Booking
													Currency <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<select class="form-control input-sm" required="required"
														name="bookingCurrency">
														<option selected="selected"
															value="${hotelQuotation.currency}">${hotelQuotation.currency}</option>
														<s:iterator value="countryList">
															<option value="<s:property value="cur_code"/>"><s:property
																	value="cur_code" /></option>
														</s:iterator>
													</select>
												</div>
											</div>

											<div class="form-group">
												<label for="currency" class="col-sm-2 control-label">INR
													To Booking <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<input type="text" value="1" class="form-control input-sm"
														required="required" placeholder="Conversion Rate"
														name="baseToBookingExchangeRate">
												</div>
											</div>

											<div class="form-group">
												<label for="NetRate" class="col-sm-2 control-label">Booking
													Status <span id="mandatory"> * </span>
												</label>
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
													PaymentType <span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="customerPaymentType" id="clientPaymentType">
														<option value="Full">Full Payment</option>
														<option value="Partial" selected="selected">Partial
															Payment</option>
														<option value="Zero">No Payment</option>
													</select>
												</div>
												<div class="col-sm-2 pull-right">
													<a class="btn btn-info btn-xs" data-toggle="modal"
														href="#clientmyNotification"><i
														class="fa fa-bell-o fa-sm"></i>Reminder</a>
												</div>
											</div>
											<div class="form-group clientPayment">
												<label for="currency" class="col-sm-2 control-label">Amount</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														placeholder="amount"
														name="paymentTransaction.clientAmount" id="clientAmount">
												</div>
											</div>

											<div class="form-group clientPayment">
												<label for="currency" class="col-sm-2 control-label">Paid
													By</label>
												<div class="col-sm-8">
													<input type="text" class="form-control input-sm"
														placeholder="Paid By"
														name="paymentTransaction.paidByClient">
												</div>
											</div>
											<%-- 
					 
					  <div class="form-group">
						<label for="NetRate" class="col-sm-2 control-label">Payment Status
						</label>
						<div class="col-sm-8">
						<select  class="form-control input-sm" required="required"  id="paymentStatus"    name="paymentStatus">
						<option value="Paid">Paid</option>
						<option value="Pending" selected="selected">Pending</option>
						</select>
						 </div>
					</div>
					  --%>
											<div class="form-group client-card-details">
												<label for="currency" class="col-sm-2 control-label">CARD
													DETAILS </label>

											</div>
											<div class="form-group  no-client-payment-mode">
												<label for="currency" class="col-sm-2 control-label">Payment
													Mode <span id="mandatory"> * </span>
												</label>
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
													<span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<textarea class="form-control input-sm" required="required"
														placeholder="Type text"
														name="paymentTransaction.response_message"></textarea>
												</div>
											</div>


											<div class="form-group client-card-details">
												<label for="currency" class="col-sm-2 control-label">CardHolderName
													<span id="mandatory"> * </span>
												</label>
												<div class="col-sm-8">
													<select class="form-control input-sm"
														name="paymentTransaction.clientCardHolderId" required>
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

								<!-- /.panel -->

								<div id="roomscount"></div>
							</div>
							<!-- /.panel-group -->
						</div>
					</div>

					<!-- harsha added colapse ended -->

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button   type="button" onclick="bookingFormSubmit();"
								class="btn btn-primary btn-lg">Book Now</button>
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
				alert("Please Provide Confirmation Number");
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
								console.log("totSupplierPrice------"
										+ totSupplierPrice);

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

			$('#supplierPaymentType').on('change', function() {

				if (this.value == 'Partial') {
					$('.supplierPayment').show();
					$('.card-details').hide();
					$('.no-payment-mode').show();
				}

				else if (this.value == 'Zero') {
					$('#supplierAmount').val("");
					$('.supplierPayment').hide();
					$('.card-details').hide();
					$('.no-payment-mode').hide();

				}

				else {
					$('#supplierAmount').val("");
					$('.supplierPayment').hide();
					$('.card-details').show();
					$('.no-payment-mode').show();
				}
			})

			$('#clientPaymentType').on('change', function() {
				if (this.value == 'Partial') {
					$('.clientPayment').show();
					$('.client-card-details').hide();
					$('.no-client-payment-mode').show();

				}

				else if (this.value == 'Zero') {
					$('#clientAmount').val("");
					$('.clientPayment').hide();
					$('.client-card-details').hide();
					$('.no-client-payment-mode').hide();
				}

				else {
					$('#clientAmount').val("");
					$('.clientPayment').hide();
					$('.client-card-details').show();
					$('.no-client-payment-mode').show();
				}
			})

			if (clientPaymentType == 'Partial') {
				$('.clientPayment').show();
				$('.client-card-details').hide();
				document.getElementById('paymentStatus').value = "Paid";

			} else {
				$('.clientPayment').hide();
				$('.client-card-details').show();
			}

		});
	</script>
	<script>
		$("#bookingDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		}).datepicker("setDate", new Date());
 
		 $('.price-details').on('keyup input', function () {
			   addtax(); 
			  });
		 
		function addtax(){
			var basePrice1 = Math.round(parseFloat($('#basePrice').val()) * 100) / 100;
			var markup1 = Math.round(parseFloat($('#markup').val()) * 100) / 100;
			var tax1 = Math.round(parseFloat($('#tax').val()) * 100) / 100;
			var managementFeetax1 = Math.round(parseFloat($('#managementFeetax').val()) * 100) / 100;
			var discountAmount1= Math.round(parseFloat($('#discountAmount').val()) * 100) / 100;
			var totalBookingAmount1 = ((basePrice1 + markup1 + tax1 + managementFeetax1)-discountAmount1);
			var depositBalance =${userWallet.depositBalance};
			var walletBalance =${userWallet.walletbalance};
			if (depositBalance > totalBookingAmount1) {
				$('.submitWrap').show();
				$('#balanceCheck').hide();
			}
			else if (walletBalance>totalBookingAmount1) {
				$('.submitWrap').show();
				$('#balanceCheck').hide();
			}
			else{
				$('.submitWrap').hide();
				$('#balanceCheck').show();
			}
		}
		 
		
		function bookingFormSubmit() {
			var basePrice = Math.round(parseFloat($('#basePrice').val()) * 100) / 100;
			var markup = Math.round(parseFloat($('#markup').val()) * 100) / 100;
			var tax = Math.round(parseFloat($('#tax').val()) * 100) / 100;
			var managementFeetax = Math.round(parseFloat($('#managementFeetax').val()) * 100) / 100;
			var discountAmount= Math.round(parseFloat($('#discountAmount').val()) * 100) / 100;
			var quotedAmount = Math
					.round(parseFloat($('#quotedAmount').val()) * 100) / 100;
			var orderId = $('#orderReference').val();
			var clientAmount = ((basePrice + markup + tax + managementFeetax)-discountAmount);
			var bookingDate = $('#bookingDate').val();
			 
		   if (bookingDate == '') {
				alert("Please provide bookingDate");
			} else if (orderId == '') {
				alert("Please Provide Confirmation Number");
			} else if (basePrice == 0) {
				alert("Please provide Base price.");
			} /*  else if (clientAmount >=quotedAmount) {
				alert("(BasePrice+Tax+Markup) not more than Quoted Price.Please Check.");
			}   */
			else {
			 $("#bookingFormId").submit(); 
			}  

		}
	</script>

	<script>
	
		var roomCount = $
		{
			hotelQuotation.roomCount
		};
		if (roomCount > 1) {

			var cloneCount = 1;
			for (var i = 2; i <= roomCount; i++) {
				var $rooms = $("#rooms:first").clone();
				$('#roomscount').append($rooms).insertAfter("#rooms:last");
				var roomlength = $("#roomscount #rooms").length;

				$rooms.find("h4.panel-title a:first")
						.text("Room Details" + (i));

				$rooms.find(".panel-body p.h4 b:first").text("Room" + (i));
				$rooms.find("h4.panel-title a[href='#collapseThree']").attr(
						"href", "#collapseThree" + (i));
				$rooms.find("div#collapseThree").attr("id",
						"collapseThree" + (i));
				/* $rooms.find(".first-block-rooms .form-group input[name]").attr(
						"name",
						"hotelOrderRoomInfoList" + '[' + cloneCount + ']'); */
				$rooms
						.find(
								".first-block-rooms .form-group input[name^=hotelOrderRoomInfoList]")
						.each(
								function() {
									var name = $(this).attr('name');
									var indexOfThirdBracket = name.indexOf('[') + 1;
									name = name.substring(0,
											indexOfThirdBracket)
											+ cloneCount
											+ name
													.substring(indexOfThirdBracket + 1);
									$(this).attr('name', name);
								});
				$rooms
						.find(
								".panel-group h4.panel-title a[href='#nested-collapseOne']")
						.attr("href", "#nested-collapseOne" + (i));
				$rooms.find(".panel-group div#nested-collapseOne").attr("id",
						"nested-collapseOne" + (i));
				$rooms.find("#nested").attr("id", "nested" + (cloneCount));
				$rooms.find(".add-remove a:first").attr("id",
						"addroom" + (cloneCount));
				$rooms.find(".add-remove a:last").attr("id",
						"removeroom" + (cloneCount));
				$rooms.find("div.additionalpackage").attr("class",
						"additionalpackage" + (cloneCount));
				$(
						' #nested'
								+ cloneCount
								+ ' .panel-default .form-group select[name^=hotelOrderRoomInfoList]')
						.each(
								function() {
									var name = $(this).attr('name');
									var indexOfThirdBracket = name.indexOf('[') + 1;
									name = name.substring(0,
											indexOfThirdBracket)
											+ cloneCount
											+ name
													.substring(indexOfThirdBracket + 1);
									//alert("name is after" + name);
									$(this).attr('name', name);
								});

				$(
						'#nested'
								+ cloneCount
								+ ' .panel-default .form-group input[name^=hotelOrderRoomInfoList]')
						.each(
								function() {
									var name = $(this).attr('name');
									var indexOfThirdBracket = name.indexOf('[') + 1;
									name = name.substring(0,
											indexOfThirdBracket)
											+ cloneCount
											+ name
													.substring(indexOfThirdBracket + 1);
									//alert("name is after" + name);
									$(this).attr('name', name);
								});
				cloneCount++;
			}

		}

		function add(currentObj) {
			var parentid = $("#" + currentObj.id).parent().parent().attr('id');
			console.log(parentid);
			var tobeappenedid = $("#" + currentObj.id).parent().prev().attr(
					'class');
			console.log(tobeappenedid);
			var noOfcust = $("#" + parentid).find('.panel-default').length;

			var $addrooms = $('#' + parentid).find(".panel-default:first")
					.clone();
			$('.' + tobeappenedid).append($addrooms);
			for (var i = 1; i <= parseInt(noOfcust); i++) {
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

			}
			//$('.'+tobeappenedid).html($addrooms);
			if ((noOfcust) > 0) {
				$('.remove_field').removeAttr('disabled');
			} else {
				$('.remove_field').attr('disabled', 'disabled');
			}
		}

		function remove_field(currentObj) {
			var parentid = $("#" + currentObj.id).parent().parent().attr('id');
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
		$(window).load(function(){
			var totalPassenger=${hotelQuotation.roomCount};
			var managementFees=${managementFeeForBooking};
		$("#managementFee").val(totalPassenger*managementFees);
		
		});
	
	</script>
</body>
</html>