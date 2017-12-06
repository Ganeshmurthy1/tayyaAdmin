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
	font-family: "Helvetica Neue", Helvetica, /*Tahoma,*/  Arial, sans-serif;
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
								<a href="goTrips" class="btn btn-top-link btn-xs"> All Trips
								</a> <a href="FlightTravelRequestList"
									class="btn btn-top-link btn-xs"> Flight Trips </a> <a
									href="HotelTravelRequestList" class="btn btn-top-link btn-xs">
									Hotel Trips </a> <a href="CarTravelRequestList"
									class="btn btn-top-link btn-xs"> Car Trips </a> <a
									href="BusTravelRequestList" class="btn btn-top-link btn-xs">
									Bus Trips </a> <a href="TrainTravelRequestList"
									class="btn btn-top-link btn-xs"> Train Trips </a> <a
									href="VisaTravelRequestList" class="btn btn-top-link btn-xs">
									Visa Trips </a>
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
					</div>
		</div>
	</section>
	<!-- Main content -->
	<div>
		<div id="mypdf">
			<section class="content">
				<c:if test="${insuranceTravelRequest!=null}">
					<section class="content">
						<div class="row">
							<div class="col-sm-12">
								<h4>Insurance Request Details</h4>
							</div>
						</div>
						<div class="row">
							<div class="mealexprow">
								<div class="row ">
									<c:set var="insuranceTravelRequest" scope="session"
										value="${insuranceTravelRequest}" />
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
											<p>No. of Passengers</p>
										</div>
										<div class="p-inp">
											<p>${insuranceTravelRequest.noOfTravelers}</p>
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
									<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Confirmation Number</p>
							</div>
							<div class="p-inp">
								<time> ${insurance.confirmationNumber} </time>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Description </p>
							</div>
							<div class="p-inp">
								<p>${insurance.description}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${insurance.travelDate}</p>
							</div>
						</div>
						</div>
						
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>BasePrice</p>
							</div>
							<div class="p-inp">
								<time> ${insurance.basePrice} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Courier Charges</p>
							</div>
							<div class="p-inp" id="misctotamt">
								<time> ${insurance.courierCharges} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>total Invoice Amount</p>
							</div>
							<div class="p-inp">
								<time> ${insurance.totalInvoiceAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>ManagementFee</p>
							</div>
							<div class="p-inp">
								<time> ${insurance.managementFee} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Convenience Fee</p>
							</div>
							<div class="p-inp">
								<time> ${insurance.convenienceFee} </time>
							</div>
						</div> --%>
								</div>
							</div>
						</div>
						<s:if
							test="insuranceTravelRequestQuotationlist!=null && insuranceTravelRequestQuotationlist.size()>0">
							<div class="row">
								<div class=" col-sm-12 clearfix">
									<a class="btn btn-primary createdquotation collapsed"
										role="button" data-toggle="collapse" href="#createdquotation"
										aria-expanded="false" aria-controls="createdquotation">
										Confirmed Booking Quotations </a>
								</div>
								<div class="collapse" id="createdquotation" aria-expanded="true">
									<div class="panel-body">
										<!-- Filter of main info -->
										<div class="clearfix">
											<div class="table-responsive">
												<table class="table">
													<thead class="thead-inverse">
														<tr>
															<th>SNo.</th>
															<th>Passport Number</th>
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

														<c:forEach items="${insuranceTravelRequestQuotationlist}"
															var="insuranceTravelRequestQuotation" varStatus="status">
															<tr>
																<th scope="row">${status.count}</th>
																<td>${insuranceTravelRequestQuotation.passportNumber}</td>
																<td>${insuranceTravelRequestQuotation.insuranceOrderRow.confirmationNumber}</td>
																<td>${insuranceTravelRequestQuotation.insuranceOrderRow.orderId}</td>
																<td>${insuranceTravelRequestQuotation.insuranceOrderRow.statusAction}</td>
																<td>${insuranceTravelRequestQuotation.currency} ${insuranceTravelRequestQuotation.insuranceOrderRow.totalAmount}</td>
																<td>
																<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if>
														<s:else><a
																	href="goInsuranceInvoice?orderId=${insuranceTravelRequestQuotation.orderRowId}"
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

						</s:if>

					</section>
				</c:if>
			</section>
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






