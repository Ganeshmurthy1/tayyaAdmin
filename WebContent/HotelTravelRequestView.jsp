<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script> --%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<%--<script
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
</style>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header"> 
		<div class="row">
			<div class="col-sm-2 clearfix pull-left">
				<h4><b>Hotel Travel Request Details</b></h4>
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
				<a href="hotelTravelRequestEdit?id=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-edit"></i> Travel Request
						</a>						
						<a
							href="goHotelRequestTravelQuotation?hotelQuotationRequestId=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-plus"></i> Quotation
						</a> <a
							href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=<s:property value="id"/>"
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
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Employee Name:</p>
							</div>
							<div class="p-inp">
								<p>${hotelQuotationRow.empName}</p>
							</div>
						</div>
					</div>



					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel No:</p>
							</div>
							<div class="p-inp">
								<p>${hotelQuotationRow.TRNo}</p>
							</div>
						</div>
					</div>
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Entity:</p>
							</div>
							<div class="p-inp">
								<p>${hotelQuotationRow.entity}</p>
							</div>

						</div>
					</div>
				</div>

				<div class="row ">

					<%-- <div class="col-sm-12">
			        <div class="row">
			          <div class="col-sm-3">
			            <div class="p-info clearfix">
			              <div class="p-label">
			                <p>CheckIn</p>
			              </div>
			              <div class="p-inp">
			                <p>${hotelQuotationRow.checkIn}</p>
			              </div>
			            </div>
			          </div>
			          
			          
			           <div class="col-sm-3">
			            <div class="p-info clearfix">
							<div class="p-label">
								<p>CheckOut</p>
							</div>
							<div class="p-inp">
								<p>${hotelQuotationRow.checkOut}</p>
							</div>
						</div>
			          </div>
			          
			           <div class="col-sm-3">
			            <div class="p-info clearfix">
							<div class="p-label">
								<p>NoOfNights</p>
							</div>
							<div class="p-inp">
								<time> ${hotelQuotationRow.noOfNights} </time>
							</div>
						</div>
			          </div>
			          <div class="col-sm-3">
				           <div class="p-info clearfix">
								<div class="p-label">
									<p>Currency</p>
								</div>
								<div class="p-inp">
									<time> ${hotelQuotationRow.currency} </time>
								</div>
							</div>
			          </div>
			          
			           <div class="col-sm-3">
				           <div class="p-info clearfix">
								<div class="p-label">
									<p>City</p>
								</div>
								<div class="p-inp">
									<time> ${hotelQuotationRow.city} </time>
								</div>
							</div>
			          </div>
			          
			           
			        </div>
        		</div> --%>




					<div class="col-sm-6 ">

						<div class="p-info clearfix">
							<div class="p-label">
								<p>CheckIn</p>
							</div>
							<div class="p-inp">
								<p>${hotelQuotationRow.checkIn}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>CheckOut</p>
							</div>
							<div class="p-inp">
								<p>${hotelQuotationRow.checkOut}</p>
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
								<time> ${hotelQuotationRow.noOfNights} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								<time> ${hotelQuotationRow.city} </time>
							</div>
						</div>

						<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${hotelQuotationRow.currency} </time>
							</div>
						</div> --%>
					</div>
				</div>

				<s:if
					test="hotelTravelRequestQuotationlist!=null && hotelTravelRequestQuotationlist.size()>0">
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
											<!-- <th>letitbe</th> -->
												</tr>
											</thead>
											<tbody>
												<s:iterator value="hotelTravelRequestQuotationlist"
													status="rowCount">
													<tr>
														<th scope="row">${rowCount.count}</th>
														<%-- <td>${hotelTravelRequest.TRNo}</td> --%>
														<td>${hotelOrderRow.orderReference}</td>
														<td>${hotelOrderRow.statusAction}</td>
														<td>${hotelOrderRow.bookingCurrency} ${hotelOrderRow.finalPrice}</td>
														<td><a
															href="getHotelOfflineVoucher?hotelOrderId=${hotelOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Voucher
														</a></td>
														<td>
														<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if>
														<s:else><a
															href="getHotelOfflineInvoice?hotelOrderId=${hotelOrderRow.id}"
															class="btn btn-success btn-xs"> <i
																class="fa fa-files-o"></i> Invoice
														</a>
														</s:else>
														</td>
														<!-- <td><a href="#" class="btn btn-success btn-xs"> <i
														class="fa fa-check"></i> Quotation
												</a></td>
												<td><a href="#" class="btn btn-success btn-xs"> <i
														class="fa fa-check"></i> Quotation
												</a></td> -->
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







