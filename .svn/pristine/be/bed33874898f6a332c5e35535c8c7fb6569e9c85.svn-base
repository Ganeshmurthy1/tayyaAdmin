<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
 
<script src="js/company_filter.js">
	
</script> 
<style>
.ui-autocomplete {
	height: auto;
}
</style>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-sm-2 clearfix pull-left">
				<h4><b>Flight Travel Request Details</b></h4> 
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
				<a href="goFlightRequestEdit?id=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-edit"></i> Travel Request
						</a> <a
							href="goFlightRequestTravelQuotation?flightQuotationRequestId=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-plus"></i> Quotation
						</a> <a
							href="getFlightQuotationList?flightQuotationRequestId=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-list"></i> Quotation
						</a>
		</div>
			</div>			
			</div>
 
		</div>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">

			<!-- edit form column -->
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-3 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>customerName</p>
							</div>
							<div class="p-inp">
								<p>${flightTravelRequest.customerName}</p>
							</div>
						</div>
					</div>



					<div class="col-sm-3">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel No</p>
							</div>
							<div class="p-inp">
								<p>${flightTravelRequest.travelRequestNumber}</p>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Customer No</p>
							</div>
							<div class="p-inp">
								<p>${flightTravelRequest.customerNo}</p>
							</div>
						</div>
					</div>
					<div class="col-sm-3 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Entity</p>
							</div>
							<div class="p-inp">
								<p>${flightTravelRequest.companyEntity}</p>
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
								<p>${flightTravelRequest.origin}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Destination</p>
							</div>
							<div class="p-inp">
								<p>${flightTravelRequest.destination}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>TripType</p>
							</div>
							<div class="p-inp">
								<c:if test="${flightTravelRequest.tripType=='R'}">
									<p>Round Trip</p>
								</c:if>
								<c:if test="${flightTravelRequest.tripType=='O'}">
									<p>One Way</p>
								</c:if>

							</div>
						</div>


						<div class="p-info clearfix">
							<div class="p-label">
								<p>Departure Date</p>
							</div>
							<div class="p-inp">
								<p>${flightTravelRequest.tranDepartureDate}</p>
							</div>
						</div>



						<c:if test="${flightTravelRequest.tripType=='R'}">
							<div class="p-info clearfix">
								<div class="p-label">
									<p>Arrival Date</p>
								</div>
								<div class="p-inp">
									<p>${flightTravelRequest.tranArrivalDate}</p>
								</div>
							</div>

						</c:if>



						<%-- 	<div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<time> ${flightTravelRequest.country} </time>
							</div>
						</div>
						 --%>
					</div>

					<div class="col-sm-6 ">


						<div class="p-info clearfix">
							<div class="p-label">
								<p>Number of Passenger</p>
							</div>
							<div class="p-inp">
								<time> ${flightTravelRequest.passengerCount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${flightTravelRequest.currency} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>AirlinePrefer</p>
							</div>
							<div class="p-inp">
								<time> ${flightTravelRequest.airlinePrefer} </time>
							</div>
						</div>
					</div>
				</div>


				<s:if
					test="flightTravelRequestQuotaionList!=null && flightTravelRequestQuotaionList.size()>0">
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
													<!-- <th>Travel Request Number</th> -->
													<th>OrderId</th>
													<th>Status</th>
													<th>Final Price</th>
													<th>Voucher</th>
													<th>Invoice</th>
													<th>Customer</th>
													<th>Supplier</th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="flightTravelRequestQuotaionList"
													status="rowCount">
													<tr>
														<th scope="row">${rowCount.count}</th>
														<%-- <td>${hotelTravelRequest.TRNo}</td> --%>
														<s:if test="returnflightOrderRow.orderId != '' ">
														<td>${flightOrderRow.orderId} , ${returnflightOrderRow.orderId}	</td>
														</s:if>
														<s:else>
															<td>${flightOrderRow.orderId}</td>
														</s:else>
													
														<td>${flightOrderRow.statusAction}</td>
														<td>${flightOrderRow.bookingCurrency} ${flightOrderRow.finalPrice}</td>		
																								
														<s:if test="tripType == '0' ">
														<td><a
															href="goFlightOfflineVoucher?id=${flightOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Voucher
														</a></td>
														<td><a
															href="goFlightOfflineInvoice?id=${flightOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Invoice
														</a></td>
														</s:if>
														<s:elseif test="returnflightOrderRow.orderId != ''">
														<td><a
															href="goFlightOfflineVoucher?id=${flightOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Onward Voucher
														</a>
														<a
															href="goFlightOfflineVoucher?id=${returnflightOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Return Voucher
														</a>
														</td>
														<td><a
															href="goFlightOfflineInvoice?id=${flightOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Onward Invoice
														</a>
														<a
															href="goFlightOfflineInvoice?id=${returnflightOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Return Invoice
														</a></td>
														</s:elseif>
														<s:else>
														<td><a
															href="goFlightOfflineVoucher?id=${flightOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Voucher
														</a></td>
														<td>
														<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if>
														<s:else>
														<a
															href="goFlightOfflineInvoice?id=${flightOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Invoice
														</a>
														</s:else></td>
														</s:else>
														<!--  <td><a href="#" class="btn btn-success btn-xs"> <i
														class="fa fa-check"></i>Customer Invoice
												</a></td>
														 -->

														<td><a
															href="addFlightCustomerPaymentTransaction?flightOrderId=${orderRowId}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-check"></i> Customer Payment
														</a></td>
														<td><a
															href="addFlightApiProviderPaymentTransaction?flightOrderId=${orderRowId}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-check"></i> Supplier Payment
														</a></td>
													</tr>

												</s:iterator>

											</tbody>
										</table>
									</div>

								</div>
							</div>
						</div>

					</div>
				</s:if>


			</div>
		</div>
	</section>
</div>

<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">







