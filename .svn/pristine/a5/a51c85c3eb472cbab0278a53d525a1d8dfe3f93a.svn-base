<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script> --%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>
<script src="js/company_filter.js">
	
</script>

<!-- <link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css">
<link href="css/jquery-uii.css" rel="stylesheet" type="text/css" /> -->
<style>
.ui-autocomplete {
	height: auto;
}

button.button {
	font-family: "Helvetica Neue", Helvetica, /*Tahoma,*/     Arial,
		sans-serif;
	border-radius: 20px;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	-khtml-border-radius: 20px;
	background: #60b82d;
	background: -moz-linear-gradient(top, #69ca3b 0%, #56a61e 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #69ca3b),
		color-stop(100%, #56a61e));
	background: linear-gradient(#69ca3b, #56a61e);
	-pie-background: linear-gradient(#69ca3b, #56A61E);
	text-shadow: 0 -1px 0px rgba(0, 0, 0, 0.15);
	box-shadow: inset 0px 1px 0px #81f54a;
	-moz-box-shadow: inset 0px 1px 0px #81f54a;
	-webkit-box-shadow: inset 0px 1px 0px #81f54a;
	position: relative;
	outline: none;
	font-size: 14px;
	height: 5.158em;
	line-height: 30px;
	font-weight: normal;
	color: #fff !important;
	float: left;
	text-align: center;
	padding-left: 15px;
	padding-right: 15px;
	text-decoration: none;
	margin: 5px;
	cursor: default;
}
/* .row
{
    left: 50%;
    position: absolute;
    top: 50%;
}
 */
/* .row div
{
    border: 1px solid black;
    margin-left: -50%;
    margin-top: -50%;
    height: 100px;
    width: 100px;
} */
</style>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
				<div class="row">
					<div class="col-sm-3 clearfix pull-left">
						<h3>Trip Details</h3>
					</div>
				  <div class="col-sm-9 clearfix pull-right " data-placement="top">
		<div class="row">
		<div class="col-sm-5 clearfix pull-left " data-placement="top">
		</div>
		<div class="col-sm-8 clearfix " data-placement="top">		
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  >
					 Hotel Trips
				</a>
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Flight Trips
				</a>
				<a href="CarTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Car Trips
				</a>
				<a href="BusTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Bus Trips
				</a>
				<a href="TrainTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Train Trips
				</a>
				<a href="VisaTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Visa Trips
				</a>
				<a href="InsuranceTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Insurance Trips
				</a>
				<a href="MiscellaneousTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Miscellaneous Trips
				</a>
			</div>		
			
			<div class="col-sm-2 clearfix pull-left"  >
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
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>		 -->					
							</ul>
						</div>
					</div> 	
			<div class="col-sm-2 clearfix pull-right" data-placement="top">
				<div class="myDropdown">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Create Trip
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				    <li><a href="goHotelTravelRequest">Hotel</a></li>
				    <li><a href="goFlightTravelRequest">Flight</a></li>
				    <li><a href="goCarTravelRequest">Car</a></li>
				    <li><a href="goBusTravelRequest">Bus</a></li>
				    <li><a href="goTrainTravelRequest">Train</a></li>
				    <li><a href="goVisaTravelRequest">Visa</a></li>
				      <li><a href="goInsuranceTravelRequest">Insurance</a></li>
				      <li><a href="goMiscellaneousRequest">Miscellaneous</a></li>
				
				   <!--  <li><a href="allexpenses">Expenses</a></li> -->
				  </ul>
				</div>
			</div>	  
			</div>			
			</div>
				  
				  
				</div>
			</section>
	<!-- Main content -->
	<div>
		<div id="mypdf">
			<c:if test="${tripRequest.hotelTravelRequest!=null}">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4>
								<b>Hotel Travel Request Details</b> <a
									href="hotelTravelRequestEdit?id=${tripRequest.hotelTravelRequest.id}"
									class="btn btn-success btn-xs" data-toggle="modal"> <i
									class="fa fa-edit"></i> Travel Request
								</a> <a
									href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${tripRequest.hotelTravelRequest.id}"
									class="btn btn-success btn-xs" data-toggle="modal"> <i
									class="fa fa-plus"></i> Quotation
								</a> <a
									href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${tripRequest.hotelTravelRequest.id}"
									class="btn btn-success btn-xs" data-toggle="modal"> <i
									class="fa fa-list"></i> Quotation
								</a>
							</h4>
						</div>
					</div>
					<section class="content">


						<div class="row">
							<!-- edit form column -->
							<div class="col-sm-12 personal-info">


								<div class="row o-list-view">
									<div class="col-sm-4 ">
										<div class="p-info clearfix">
											<div class="p-label">
												<p>Employee Name</p>
											</div>
											<div class="p-inp">
												<p>${tripRequest.hotelTravelRequest.empName}</p>
											</div>
										</div>
									</div>
									<div class="col-sm-4 ">
										<div class="p-info clearfix">
											<div class="p-label">
												<p>Travel No</p>
											</div>
											<div class="p-inp">
												<p>${tripRequest.hotelTravelRequest.TRNo}</p>
											</div>
										</div>
									</div>
									<div class="col-sm-4 ">
										<div class="p-info clearfix">
											<div class="p-label">
												<p>Entity</p>
											</div>
											<div class="p-inp">
												<p>${tripRequest.hotelTravelRequest.entity}</p>
											</div>

										</div>
									</div>
								</div>

								<div class="row ">
									<div class="col-sm-6 ">

										<div class="p-info clearfix">
											<div class="p-label">
												<p>CheckIn</p>
											</div>
											<div class="p-inp">
												<p>${tripRequest.hotelTravelRequest.checkIn}</p>
											</div>
										</div>
										<div class="p-info clearfix">
											<div class="p-label">
												<p>CheckOut</p>
											</div>
											<div class="p-inp">
												<p>${tripRequest.hotelTravelRequest.checkOut}</p>
											</div>
										</div>
										<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<time> ${hotelQuotationRow.country} </time>
							</div>
						</div> --%>
									</div>

									<div class="col-sm-6 ">
										<div class="p-info clearfix">
											<div class="p-label">
												<p>NoOfNights</p>
											</div>
											<div class="p-inp">
												${tripRequest.hotelTravelRequest.noOfNights}</div>
										</div>
										<div class="p-info clearfix">
											<div class="p-label">
												<p>City</p>
											</div>
											<div class="p-inp">
												${tripRequest.hotelTravelRequest.city}</div>
										</div>

										<div class="p-info clearfix">
											<div class="p-label">
												<p>Currency</p>
											</div>
											<div class="p-inp">
												${tripRequest.hotelTravelRequest.currency}</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
				</section>
			</c:if>
			<div class="row">
				<div class="col-sm-12">
					<hr />
				</div>
			</div>
			<c:if test="${tripRequest.flightTravelRequest!=null}">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4>
								<b>Flight Travel Request Details</b>&nbsp&nbsp&nbsp&nbsp <a
									href="flightTravelRequestEdit?id=${tripRequest.flightTravelRequest.id}"
									class="btn btn-success btn-xs" data-toggle="modal"> Edit </a> <a
									href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${tripRequest.flightTravelRequest.id}"
									class="btn btn-success btn-xs" data-toggle="modal"
									title="Create"> Create Quotation </a> <a title="All Quotations"
									href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${tripRequest.flightTravelRequest.id}"
									class="btn btn-success btn-xs" data-toggle="modal">
									Quotations </a>
							</h4>
						</div>

					</div>

					<div class="row">
						<!-- edit form column -->
						<div class="col-sm-12 personal-info">


							<div class="row o-list-view">

								<div class="col-sm-4 ">
									<div class="p-info clearfix">
										<div class="p-label">
											<p>Employee Name</p>
										</div>
										<div class="p-inp">
											<p>${tripRequest.flightTravelRequest.firstName}</p>
										</div>
									</div>
								</div>


								<div class="col-sm-4 ">
									<div class="p-info clearfix">
										<div class="p-label">
											<p>Travel No</p>
										</div>
										<div class="p-inp">
											<p>${tripRequest.flightTravelRequest.travelRequestNumber}</p>
										</div>
									</div>
								</div>
								<div class="col-sm-4 ">
									<div class="p-info clearfix">
										<div class="p-label">
											<p>Entity</p>
										</div>
										<div class="p-inp">
											<p>${tripRequest.flightTravelRequest.companyEntity}</p>
										</div>

									</div>
								</div>
							</div>


							<div class="row ">
								<div class="col-sm-6 ">
									<div class="p-info clearfix">
										<div class="p-label">
											<p>Origin</p>
										</div>
										<div class="p-inp">
											<p>${tripRequest.flightTravelRequest.origin}</p>
										</div>
									</div>
									<div class="p-info clearfix">
										<div class="p-label">
											<p>Destination</p>
										</div>
										<div class="p-inp">
											<p>${tripRequest.flightTravelRequest.destination}</p>
										</div>
									</div>
									<div class="p-info clearfix">
										<div class="p-label">
											<p>TripType</p>
										</div>
										<div class="p-inp">
											<c:if test="${tripRequest.flightTravelRequest.tripType=='R'}">
												<p>Round Trip</p>
											</c:if>
											<c:if test="${tripRequest.flightTravelRequest.tripType=='O'}">
												<p>One Way</p>
											</c:if>
										</div>
									</div>
									<div class="p-info clearfix">
										<div class="p-label">
											<p>DepartureDate</p>
										</div>
										<div class="p-inp">
											<p>${tripRequest.flightTravelRequest.departureDate}</p>
										</div>
									</div>

									<c:if test="${tripRequest.flightTravelRequest.tripType=='R'}">
										<div class="p-info clearfix">
											<div class="p-label">
												<p>Arrival Date</p>
											</div>
											<div class="p-inp">
												<p>${tripRequest.flightTravelRequest.tranArrivalDate}</p>
											</div>
										</div>

									</c:if>

								</div>

								<div class="col-sm-6 ">
									<div class="p-info clearfix">
										<div class="p-label">
											<p>PassengerCount</p>
										</div>
										<div class="p-inp">
											<time>
												${tripRequest.flightTravelRequest.passengerCount} </time>
										</div>
									</div>
									<div class="p-info clearfix">
										<div class="p-label">
											<p>City</p>
										</div>
										<div class="p-inp">
											<time> ${tripRequest.flightTravelRequest.destination}
											</time>
										</div>
									</div>

									<div class="p-info clearfix">
										<div class="p-label">
											<p>Currency</p>
										</div>
										<div class="p-inp">
											<time> ${tripRequest.flightTravelRequest.currency} </time>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</c:if>

			<c:if
				test="${tripRequest.carTravelRequest!=null && tripRequest.carTravelRequest!=' '}">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4>
								<b>Car Travel Request Details</b>
							</h4>
						</div>
					</div>
					<div class="row">
						<!-- edit form column -->
						<div class="col-sm-12 personal-info">
							<div class="carexprow">
								<c:set var="carTravelRequest" scope="session"
									value="${tripRequest.carTravelRequest}" />
								<div class="row ">
									<div class=" ">
										<div class="p-info clearfix">
											<div class="p-label">
												<p>Travel No:</p>
											</div>
											<div class="p-inp">
												<p>${carTravelRequest.TRNo}</p>
											</div>
										</div>
										<div class="p-info clearfix">
											<div class="p-label">
												<p>Employee Name:</p>
											</div>
											<div class="p-inp">
												<p>${carTravelRequest.title}
													${carTravelRequest.firstName} ${carTravelRequest.lastName}</p>
											</div>
										</div>
										<div class="clearfix">
											<div class="table-responsive">
												<table class="table">
													<thead class="thead-inverse">
														<tr>
															<th>SNo.</th>
															<th>OrderId</th>
															<th>Status</th>
															<th>Final Price</th>
															<th>Invoice</th>
														</tr>
													</thead>
													<tbody>

														<c:forEach items="${carTravelRequestQuotationlist}"
															var="carTravelRequestQuotation" varStatus="status">
															<tr>
																<th scope="row">${status.count}</th>
																<td>${carTravelRequestQuotation.carOrderRow.confirmationNumber}</td>
																<td>${carTravelRequestQuotation.carOrderRow.statusAction}</td>
																<td>${carTravelRequestQuotation.carOrderRow.totalAmount}</td>


																<td>
																<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if>
														<s:else>
																<a
																	href="goCarInvoice?orderId=${carTravelRequestQuotation.orderRowId}"
																	class="btn btn-success btn-xs"> <i
																		class="fa fa-files-o"></i> Invoice
																</a>
																</s:else></td>
															</tr>

														</c:forEach>

													</tbody>
												</table>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</section>
			</c:if>
			<%-- <section class="content"></section> --%>



			<c:if test="${tripRequest.trainTravelRequest!=null }">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4><b>Train Travel Request Details</b></h4>
						</div>
					</div>
					<div class="row">
						<!-- edit form column -->
						<div class="trainexprow">
							<div class="col-sm-12 personal-info">
								<div class="row o-list-view">
									<c:set var="trainTravelRequest" scope="session"
										value="${tripRequest.trainTravelRequest}" />

									<div class="p-info clearfix">
										<div class="p-label">
											<p>Travel No:</p>
										</div>
										<div class="p-inp">
											<p>${trainTravelRequest.trNo}</p>
										</div>
									</div>
									<div class="p-info clearfix">
										<div class="p-label">
											<p>Employee Name:</p>
										</div>
										<div class="p-inp">
											<p>${trainTravelRequest.title}
												${trainTravelRequest.firstName}
												${trainTravelRequest.lastName}</p>
										</div>
									</div>

									<div class="clearfix">
										<div class="table-responsive">
											<table class="table">
												<thead class="thead-inverse">
													<tr>
														<th>SNo.</th>
														<th>Confirmation Number</th>
														<th>Train Number</th>
														<th>From Location</th>
														<th>To Location</th>
														<th>Travel Date</th>
														<th>Currency</th>
														<th>OrderId</th>
														<th>Status</th>
														<th>Final Price</th>
														<th>Invoice</th>
														<!-- <th>Quttion</th>
											<th>letitbe</th> -->
													</tr>
												</thead>
												<tbody>

													<c:forEach items="${trainTravelRequestQuotationlist}"
														var="trainTravelRequestQuotation" varStatus="status">
														<tr>
															<th scope="row">${status.count}</th>
															<td>${trainTravelRequestQuotation.trainOrderRow.confirmationNumber}</td>
															<td>${trainTravelRequestQuotation.trainNumber}</td>
															<td>${trainTravelRequestQuotation.fromlocation}</td>
															<td>${trainTravelRequestQuotation.tolocation}</td>
															<td>${trainTravelRequestQuotation.travelDate}</td>
															<td>${trainTravelRequestQuotation.currency}</td>

															<td>${trainTravelRequestQuotation.trainOrderRow.orderId}</td>
															<td>${trainTravelRequestQuotation.trainOrderRow.statusAction}</td>
															<td>${trainTravelRequestQuotation.trainOrderRow.totalAmount}</td>
															<td>
															<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if>
														<s:else>
															<a
																href="goTrainInvoice?orderId=${trainTravelRequestQuotation.orderRowId}"
																class="btn btn-success btn-xs"> <i
																	class="fa fa-files-o"></i> Invoice
															</a>
															</s:else></td>

														</tr>

													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


				</section>
			</c:if>
			

			<c:if test="${tripRequest.busTravelRequest!=null }">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4><b>Bus Travel Request Details</b></h4>
						</div>
					</div>
					<div class="row">
						<!-- edit form column -->
						<div class="busexprow">
							<div class="col-sm-12 personal-info">
								<div class="row ">
									<c:set var="busTravelRequest" scope="session"
										value="${tripRequest.busTravelRequest}" />
									<div class="p-info clearfix">
										<div class="p-label">
											<p>Travel No:</p>
										</div>
										<div class="p-inp">
											<p>${busTravelRequest.TRNo}</p>
										</div>
									</div>
									<div class="p-info clearfix">
										<div class="p-label">
											<p>Employee Name:</p>
										</div>
										<div class="p-inp">
											<p>${busTravelRequest.title}
												${busTravelRequest.firstName} ${busTravelRequest.lastName}</p>
										</div>
									</div>

									<div class="clearfix">
										<div class="table-responsive">
											<table class="table">
												<thead class="thead-inverse">
													<tr>
														<th>SNo.</th>
														<th>Travel Request Number</th>
														<th>OrderId</th>
														<th>Status</th>
														<th>Final Price</th>
														<th>Invoice</th>
														<!-- <th>Quttion</th>
											<th>letitbe</th> -->
													</tr>
												</thead>
												<tbody>

													<c:forEach items="${busTravelRequestQuotationlist}"
														var="busTravelRequestQuotation" varStatus="status">
														<tr>
															<th scope="row">${status.count}</th>

															<td>${busTravelRequestQuotation.busOrderRow.confirmationNumber}</td>
															<td>${busTravelRequestQuotation.busOrderRow.orderId}</td>
															<td>${busTravelRequestQuotation.busOrderRow.statusAction}</td>
															<td>${busTravelRequestQuotation.totalAmount}</td>
															<td>
															<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if>
														<s:else>
														<a
																href="generateBusInvoice?orderId=${busTravelRequestQuotation.orderRowId}"
																class="btn btn-success btn-xs"> <i
																	class="fa fa-files-o"></i> Invoice
															</a>
															</s:else></td>


															<!-- <td><a href="#" class="btn btn-success btn-xs"> <i
														class="fa fa-check"></i> Quotation
												</a></td>
												<td><a href="#" class="btn btn-success btn-xs"> <i
														class="fa fa-check"></i> Quotation
												</a></td> -->
														</tr>

													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


				</section>
			</c:if>

			<div class="row">
				<div class="col-sm-12">
					<hr />
				</div>
			</div>
			<c:if test="${tripRequest.visaTravelRequest!=null}">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4><b>Visa Request Details</b></h4>
						</div>
					</div>
					<div class="row ">
						<div class="visaexprow">
							<c:set var="visaTravelRequest" scope="session"
								value="${tripRequest.visaTravelRequest}" />
							<div class="p-info clearfix">
								<div class="p-label">
									<p>Travel No:</p>
								</div>
								<div class="p-inp">
									<p>${visaTravelRequest.trNo}</p>
								</div>
							</div>
							<div class="p-info clearfix">
								<div class="p-label">
									<p>Employee Name:</p>
								</div>
								<div class="p-inp">
									<p>${visaTravelRequest.title}${visaTravelRequest.firstName}
										${visaTravelRequest.lastName}</p>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix">
						<div class="table-responsive">
							<table class="table">
								<thead class="thead-inverse">
									<tr>
										<th>SNo.</th>
										<th>TravelDate</th>
										<th>Confirmation Number</th>
										<th>OrderId</th>
										<th>Status</th>
										<th>Final Price</th>
										<th>Invoice</th>
										<!-- <th>Quttion</th>
											<th>letitbe</th> -->
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${visaTravelRequestQuotationlist}"
										var="visaTravelRequestQuotation" varStatus="status">
										<tr>
											<th scope="row">${status.count}</th>
											<td>${visaTravelRequestQuotation.travelDate}</td>
											<td>${visaTravelRequestQuotation.visaOrderRow.confirmationNumber}</td>
											<td>${visaTravelRequestQuotation.visaOrderRow.orderId}</td>
											<td>${visaTravelRequestQuotation.visaOrderRow.statusAction}</td>
											<td>${visaTravelRequestQuotation.visaOrderRow.totalAmount}</td>
											<td>
											<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if>
														<s:else>
											<a
												href="goVisaInvoice?orderId=${visaTravelRequestQuotation.orderRowId}"
												class="btn btn-success btn-xs"> <i class="fa fa-files-o"></i>
													Invoice
											</a>
											</s:else></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</section>
			</c:if>


		
			<div class="row">
				<div class="col-sm-12">
					<hr />
				</div>
			</div>
			<c:if test="${tripRequest.insuranceTravelRequest!=null}">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4><b>insurance Request Details</b></h4>
						</div>
					</div>
					<div class="mealexprow">
						<div class="row ">
							<c:set var="insuranceTravelRequest" scope="session"
								value="${tripRequest.insuranceTravelRequest}" />
							<div class="p-info clearfix">
								<div class="p-label">
									<p>Travel No:</p>
								</div>
								<div class="p-inp">
									<p>${insuranceTravelRequest.TRNo}</p>
								</div>
							</div>
							<div class="p-info clearfix">
								<div class="p-label">
									<p>Employee Name:</p>
								</div>
								<div class="p-inp">
									<p>${insuranceTravelRequest.title}
										${insuranceTravelRequest.firstName}
										${insuranceTravelRequest.lastName}</p>
								</div>
							</div>

							<!-- Filter of main info -->
							<div class="clearfix">
								<div class="table-responsive">
									<table class="table">
										<thead class="thead-inverse">
											<tr>
												<th>SNo.</th>
												<th>Passport Number</th>
												<th>Confirmation Number</th>
												<th>Currency</th>
												<th>OrderId</th>
												<th>Status</th>
												<th>Final Price</th>
												<th>Invoice</th>
												<!-- <th>Quttion</th>
											<th>letitbe</th> -->
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${insuranceTravelRequestQuotationlist}"
												var="insuranceTravelRequestQuotation" varStatus="status">
												<tr>
													<th scope="row">${status.count}</th>
													<td>${insuranceTravelRequestQuotation.passportNumber}</td>
													<td>${insuranceTravelRequestQuotation.insuranceOrderRow.confirmationNumber}</td>
													<td>${insuranceTravelRequestQuotation.currency}</td>
													<td>${insuranceTravelRequestQuotation.insuranceOrderRow.orderId}</td>
													<td>${insuranceTravelRequestQuotation.insuranceOrderRow.statusAction}</td>
													<td>${insuranceTravelRequestQuotation.insuranceOrderRow.totalAmount}</td>
													<td>
													<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if>
														<s:else>
													<a
														href="goInsuranceInvoice?orderId=${insuranceTravelRequestQuotation.orderRowId}"
														class="btn btn-success btn-xs"> <i
															class="fa fa-files-o"></i> Invoice
													</a>
													</s:else></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

						</div>
					</div>
				</section>
			</c:if>

			<div class="row">
				<div class="col-sm-12">
					<hr />
				</div>
			</div>
			<c:if test="${tripRequest.miscellaneousTravelRequest!=null}">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4><b>Miscellaneous Request Details</b></h4>
						</div>
					</div>
					<div class="row ">
						<div class="visaexprow">
							<c:set var="miscellaneousTravelRequest" scope="session"
								value="${tripRequest.miscellaneousTravelRequest}" />
							<div class="p-info clearfix">
								<div class="p-label">
									<p>Travel No:</p>
								</div>
								<div class="p-inp">
									<p>${miscellaneousTravelRequest.travelRequestNumber}</p>
								</div>
							</div>
							<div class="p-info clearfix">
								<div class="p-label">
									<p>Employee Name:</p>
								</div>
								<div class="p-inp">
									<p>${miscellaneousTravelRequest.title}${miscellaneousTravelRequest.firstName}
										${miscellaneousTravelRequest.lastName}</p>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix">
						<div class="table-responsive">
							<table class="table">
								<thead class="thead-inverse">
									<tr>
										<th>SNo.</th>
										<th>Confirmation Number</th>
										<th>OrderId</th>
										<th>Status</th>
										<th>Final Price</th>
										<th>Invoice</th>
										<!-- <th>Quttion</th>
											<th>letitbe</th> -->
									</tr>
								</thead>
								<tbody>

									<%-- <c:forEach items="${visaTravelRequestQuotationlist}"
										var="visaTravelRequestQuotation" varStatus="status"> --%>
										<tr>
											<th scope="row">1</th>
											<td>${miscellaneousTravelRequest.confirmationNumber}</td>
											<td>${miscellaneousOrderRow.orderId}</td>
											<td>${miscellaneousOrderRow.statusAction}</td>
											<td>${miscellaneousOrderRow.totalAmount}</td>
											<td><a
												href="goMiscellaneousInvoice?orderId=${miscellaneousOrderRow.id}"
												class="btn btn-success btn-xs"> <i class="fa fa-files-o"></i>
													Invoice
											</a></td>
										</tr>
								<%-- 	</c:forEach> --%>
								</tbody>
							</table>
						</div>
					</div>
				</section>
			</c:if>
			
			<div class="row">
				<div class="col-sm-12">
					<div id="total" class="col-md-offset-4 col-md-4">
						<%-- ${expenseToView.totalAmount} --%>
					</div>
				</div>
				<hr />
			</div>
		</div>

	</div>

	<p id="demo"></p>


</div>


<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">
<!-- <Script>
 
/* var flghttamt = $("#flighttotamt").text(); 
 var flighttotalamt = parseInt(flghttamt); */
 
/*  console.log("flighttotalamt-------"+ flighttotalamt); */

/*  
 var hoteltamt = $("#hoteltotamt").text(); 
 var hoteltotalamt = parseInt(hoteltamt);
 console.log("hoteltotalamt-------"+ hoteltotalamt);
                                                                                                                                                                                                            
 var cartamt = $("#cartotamt").text(); 
 var cartotalamt = parseInt(cartamt);
 
 var bustamt = $("#bustotamt").text(); 
 var bustotalamt = parseInt(bustamt);
 
 var traintamt = $("#traintotamt").text(); 
 var traintotalamt = parseInt(traintamt); */
 
/*  var grandTotal = flighttotalamt;
 
 alert(parseInt(grandTotal));
 console.log(parseInt(grandTotal)); */
 
 
 $( document ).ready(function() { 
	 var s = $('.flightrow').length;
	 var h = $('.hotelrow').length;
	 var c = $('.carexprow').length;
	 var t = $('.trainexprow').length;
	 var b = $('.busexprow').length;
	 var m = $('.mealexprow').length;
	 var l = $('.labexprow').length;
	 var mi = $('.miscexprow').length;
	 
	 /* alert(s);
	 alert(h);
	 alert(c);
	 alert(t);
	 alert(b);
	 alert(m);
	 alert(l);
	 alert(mi); */
	 
	 if(s>0){  
		 var i = 0; 
		 $( ".flightrow #flighttotamt" ).each(function( index ) {
			  //console.log( index + ": " + $( this ).text() ); 
			  var flghttamt =  $( this ).text(); 
			  var flighttotalamt = parseInt(flghttamt);  
			  i = i + flighttotalamt;  
			  //console.log( index + ": " + flighttotalamt ); 
			});
		 var flightgrandTotal = i;
		 //alert(grandTotal); 
	 }
	 
	 
	 if(h>0){  
		 var j = 0; 
		 $( ".hotelrow #hoteltotamt" ).each(function( index ) {
			  //console.log( index + ": " + $( this ).text() ); 
			  var hoteltotamt =  $( this ).text(); 
			  var hoteltotalamt = parseInt(hoteltotamt);  
			  j = j + hoteltotalamt;  
			  //console.log( index + ": " + hoteltotalamt ); 
			});
		 var hotelgrandtotal = j;
		 //alert(hotelgrandtotal); 
		/*  var totalGrandTotal = grandTotal+hotelgrandtotal;
		 alert(totalGrandTotal);  */
	 }
	 
	 if(c>0){  
		 var k = 0; 
		 $( ".carexprow #cartotamt" ).each(function( index ) {
			  //console.log( index + ": " + $( this ).text() ); 
			  var cartotamt =  $( this ).text(); 
			  var carTotalAmt = parseInt(cartotamt);  
			  k = k + carTotalAmt;  
			  //console.log( index + ": " + carTotalAmt ); 
			});
		 var carTotal = k;
	 }
	 
	 if(t>0){  
		 var l = 0; 
		 $( ".trainexprow #traintotamt" ).each(function( index ) {
			  //console.log( index + ": " + $( this ).text() ); 
			  var trainTamt =  $( this ).text(); 
			  var traintotalamt = parseInt(trainTamt);  
			  l = l + traintotalamt;  
			  //console.log( index + ": " + traintotalamt ); 
			});
		 var trainTotal = l;
	 }
	 
	 if(b>0){  
		 var m = 0; 
		 $( ".busexprow #bustotamt" ).each(function( index ) {
			  //console.log( index + ": " + $( this ).text() ); 
			  var bustotamt =  $( this ).text(); 
			  var bustotalamt = parseInt(bustotamt);  
			  m = m + bustotalamt;  
			  //console.log( index + ": " + flighttotalamt ); 
			});
		 var busTotal = i;
	 }
	 if(m>0){  
		 var n = 0; 
		 $( ".mealexprow #mealtotamt" ).each(function( index ) {
			  //console.log( index + ": " + $( this ).text() ); 
			  var mealtotamt =  $( this ).text(); 
			  var mealtotalamt = parseInt(mealtotamt);  
			  n = n + flighttotalamt;  
			  //console.log( index + ": " + mealtotalamt ); 
			});
		 var mealTotal = n;
	 }
	 if(l>0){  
		 var o = 0; 
		 $( ".labexprow #labortotamt" ).each(function( index ) {
			  //console.log( index + ": " + $( this ).text() ); 
			  var labortotamt =  $( this ).text(); 
			  var labortotalamt = parseInt(labortotamt);  
			  o = o + labortotalamt;  
			  //console.log( index + ": " + labortotalamt ); 
			});
		 var labTotal = o;
	 }
	 if(mi>0){  
		 var p = 0; 
		 $( ".miscexprow #misctotamt" ).each(function( index ) {
			  //console.log( index + ": " + $( this ).text() ); 
			  var misctotamt =  $( this ).text(); 
			  var misctotalamt = parseInt(misctotamt);  
			  p = p + misctotalamt;  
			  //console.log( index + ": " + misctotalamt ); 
			});
		 var miscTotal = p;
		 var TotalGrandExpAmount=0;
		 
		 
	  if(flightgrandTotal > 0 && labTotal>0 && miscTotal>0 ){  
			  TotalGrandExpAmount=flightgrandTotal+labTotal+miscTotal;
			  document.getElementById("demo").innerHTML = TotalGrandExpAmount;
			 //alert("inside if----"+TotalGrandExpAmount);
		 }else if(flightgrandTotal > 0  && hotelgrandtotal>0 && carTotal>0){
			  TotalGrandExpAmount=flightgrandTotal+labTotal+miscTotal;
			 //alert("  if----"+TotalGrandExpAmount);
		 }else{
			 var TotalGrandExpAmount=flightgrandTotal+hotelgrandtotal+carTotal+trainTotal+busTotal+mealTotal+labTotal+miscTotal;
			// alert(" -ooo---"+TotalGrandExpAmount);
		 }
		   
	 
	 }
	 
	
	 
	 
	});

  
 
</Script>

  -->





