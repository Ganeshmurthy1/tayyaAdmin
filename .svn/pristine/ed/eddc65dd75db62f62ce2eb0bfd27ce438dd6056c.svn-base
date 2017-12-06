
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
</style>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-sm-2 clearfix pull-left">
				<h3 style="font-size: 20px;margin: 0px;"><i class="fa fa-magic" aria-hidden="true"></i> Trips</h3>
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
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">

			<form action="goTrips" method="post" id="filterform">
				<div class="clearfix">
					<div class="col-sm-12">
						<div  id="user_form-group">
						
						  <div class="col-sm-12">
								<div class="row">
									<div class="col-sm-4">
										<a class="btn btn-primary" role="button"
											data-toggle="collapse" href="#filters" aria-expanded="false"
											aria-controls="filters"> <i class="fa fa-filter"
											aria-hidden="true"></i> Filters
										</a>
									</div>

									<div class="col-sm-5  items">
										<div class="form-group clearfix">

											<div class="col-sm-6">
												<select class="form-control"
													name="apiProviderPage.maxItems" id="maxItems"
													onchange="this.form.submit()">
													 <c:forEach var="maxItems" items="${apiProviderPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${apiProviderPage.maxItems != null && apiProviderPage.maxItems == maxItems}">
													 <c:choose>
													 <c:when
														test="${apiProviderPage.maxItems == -1}">
													 <option value="${maxItems}" selected="selected">ALL</option>
													</c:when>
													 
													<c:otherwise>
														<option value="${maxItems}" selected="selected">${maxItems}</option>
													</c:otherwise>
													</c:choose>
													</c:when>
													 
													<c:otherwise>
													 <c:choose>
													 <c:when
														test="${maxItems == -1}">
														<option value="${maxItems}">ALL</option>
													</c:when>
													 
													<c:otherwise>
														<option value="${maxItems}">${maxItems}</option>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
												</c:choose>
											
											</c:forEach>
													
													
													
													
													
													
													
													
													
													
													
													
													
													
												</select>
											</div>
											<label class="col-sm-5 control-label text-left">Items
												Per Page </label>

										</div>
									</div>
 
								</div>
								<div class="collapse filter-box" id="filters">
									<div class="well">
										<div class="panel-body">
											<!-- Filter of main info -->
											<div class=" filter-text col-sm-12 clearfix">
												<div class="row">
													 <div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Corporate Name
																</label>
															<div class="col-sm-12">
															<select class="form-control" id="supplierName" name="apiProviderPage.apiProviderFilter.companyId">
																	<option value="0">My Trips</option>
																	<s:iterator value="corporateCompanyList">
																	<option value="${companyid}">${Companyname}</option>
																	</s:iterator>
																 </select>
															</div>
														</div>
													</div>
													 <div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Trip Number
																</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="off"
																	class="form-control" id="tripNo"
																	placeholder="trip Number"
																	name="apiProviderPage.apiProviderFilter.tripNum"
																	value="<s:property value="apiProviderPage.apiProviderFilter.tripNum"/>">
															</div>
														</div>
													</div>
													 
												</div>
											</div>
											<div class="col-sm-12">
												<div class="col-sm-6 clearfix cc-all">
													<a href=" " id="reset" class="text-right"><i
														class="fa fa-close"></i> Clear All</a>
												</div>
												<div class="text-right filtr-btn col-sm-6 clearfix">
													<button type="submit" class="btn btn-primary"
														name="apiProviderPage.currentPageIndex"
														value="${apiProviderPage.currentPageIndex}">Submit</button>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
			
				<div class="table-responsive dash-table">
					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<thead>
							<tr>
								<th>S.No</th>
								<th>TripNumber</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-plane"></i> </span>&nbsp; &nbsp;Flight</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-hotel"></i></span>&nbsp; &nbsp; Hotel</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-car"></i></span>&nbsp; &nbsp; Car</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-train"></i></span>&nbsp; &nbsp; Train</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-bus"></i></span>&nbsp; &nbsp; Bus</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-file"></i></span>&nbsp; &nbsp; Insurance</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-cc-visa"></i></span>&nbsp; &nbsp;Visa</th>
										<th><span class="status-bookingdone"><i
										class="fa fa-pie-chart"></i></span>&nbsp; &nbsp;Miscellaneous</th>
								<th>View</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="apiProviderPage.tripRequestList" status="rowCount" var="trip">
								<tr>
									<td><s:property value="%{#rowCount.count}" /></td>
									<td>${tripId}</td>
									<td><c:choose>
											<c:when test="${trip.flightTravelRequest!=null}">
												<a
													href="flightOrderQuotationView?id=<s:property value="flightTravelRequest.id"/>"
													class="btn btn-xs btn-green" data-toggle="modal"> <i
													class="fa fa-edit" style="color: #0082ca;"></i>
												<s:property value="flightTravelRequest.travelRequestNumber" />
												</a>
												<%-- <a
													href="goQuickLinkOfflibeFlightBookingPage?flightQuotationRequestId=<s:property value="flightTravelRequestQuotation.id"/>"
													class="btn btn-xs btn-green" data-toggle="modal"> <i
													class="fa fa-share" style="color: #0082ca;"></i>
												<s:property value="flightTravelRequest.travelRequestNumber" />
												</a> --%>
												
											</c:when>
											<c:otherwise>
												<a href="goFlightTravelRequest?tripId=${id}"
													class="status-createnew"> <i class="fa fa-plus"></i>
												</a>
												<a href="goQuickLinkOfflibeFlightBookingPage?tripId=${id}"
													class="status-createnew"> <i class="fa fa-share"></i>
												</a>
											</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${trip.hotelTravelRequest!=null}">
												<a
													href="hotelOrderQuotationView?id=<s:property value="hotelTravelRequest.id"/>"
													class="btn  btn-xs btn-green"> <span class=""><i
														class="fa fa-edit"></i></span> <s:property
														value="hotelTravelRequest.TRNo" />
												</a>
											</c:when>
											<c:otherwise>
												<a href="goHotelTravelRequest?tripId=${id}"
													class="status-createnew" data-toggle="modal"> <i
													class="fa fa-plus"></i>
												</a>
												<a href="goQuickLinkOfflibeHotelBookingPage?tripId=${id}"
													class="status-createnew"> <i class="fa fa-share"></i>
												</a>
											</c:otherwise>
										</c:choose></td>

									<td><span class="status-expensedone"></span>&nbsp; &nbsp;									
										<c:choose>
											<c:when test="${trip.carTravelRequest != null}">
												<a
													href="carOrderQuotationView?id=<s:property value="carTravelRequest.id"/>"
													class="btn  btn-xs btn-green"> <span class=""><i
														class="fa fa-edit"></i></span> <s:property
														value="carTravelRequest.TRNo" />
												</a>
											</c:when>
											<c:otherwise>
												<a href="goCarTravelRequest?tripId=${id}"
													class="status-createnew" data-toggle="modal"> <i
													class="fa fa-plus"></i>
												</a>
											</c:otherwise>
										</c:choose></td>

									<td><span class="status-expensedone"></span>&nbsp; &nbsp;
										<c:choose>
											<c:when test="${trip.trainTravelRequest!=null}">
												<a
													href="trainOrderQuotationView?id=<s:property value="trainTravelRequest.id"/>"
													class="btn  btn-xs btn-green"> <span class=""><i
														class="fa fa-edit"></i></span> <s:property
														value="trainTravelRequest.trNo" />
												</a>
											</c:when>
											<c:otherwise>
												<a href="goTrainTravelRequest?tripId=${id}"
													class="status-createnew" data-toggle="modal"> <i
													class="fa fa-plus"></i>
												</a>
											</c:otherwise>
										</c:choose></td>

									<td><span class="status-expensedone"></span>&nbsp; &nbsp;
										<c:choose>
											<c:when test="${trip.busTravelRequest!=null}">
												<a
													href="busOrderQuotationView?id=<s:property value="busTravelRequest.id"/>"
													class="btn  btn-xs btn-green"> <span class=""><i
														class="fa fa-edit"></i></span> <s:property
														value="busTravelRequest.TRNo" />
												</a>
											</c:when>
											<c:otherwise>
												<a href="goBusTravelRequest?tripId=${id}"
													class="status-createnew" data-toggle="modal"> <i
													class="fa fa-plus"></i>
												</a>
											</c:otherwise>
										</c:choose></td>

									<td><span class="status-expensedone"></span>&nbsp; &nbsp;
										<c:choose>
											<c:when test="${trip.insuranceTravelRequest!=null}">
												<a
													href="insuranceOrderQuotationView?id=<s:property value="insuranceTravelRequest.id"/>"
													class="btn  btn-xs btn-green"> <span class=""><i
														class="fa fa-edit"></i></span> <s:property
														value="insuranceTravelRequest.TRNo" />
												</a>
											</c:when>
											<c:otherwise>
												<a href="goInsuranceTravelRequest?tripId=${id}"
													class="status-createnew" data-toggle="modal"> <i
													class="fa fa-plus"></i>
												</a>
											</c:otherwise>
										</c:choose></td>

									<td><span class="status-expensedone"></span>&nbsp; &nbsp;
										<c:choose>
											<c:when test="${trip.visaTravelRequest!=null}">
												<a
													href="visaOrderQuotationView?id=<s:property value="visaTravelRequest.id"/>"
													class="btn  btn-xs btn-green"> <span class=""><i
														class="fa fa-edit"></i></span> <s:property
														value="visaTravelRequest.trNo" />
												</a>
											</c:when>
											<c:otherwise>
												<a href="goVisaTravelRequest?tripId=${id}"
													class="status-createnew" data-toggle="modal"> <i
													class="fa fa-plus"></i>
												</a>
											</c:otherwise>
										</c:choose></td>
										<td><span class="status-expensedone"></span>&nbsp; &nbsp;
										<c:choose>
											<c:when test="${trip.miscellaneousTravelRequest!=null}">
												<a
													href="miscellaneousOrderQuotationView?id=<s:property value="miscellaneousTravelRequest.id"/>"
													class="btn  btn-xs btn-green"> <span class=""><i
														class="fa fa-edit"></i></span> <s:property
														value="miscellaneousTravelRequest.travelRequestNumber" />
												</a>
											</c:when>
											<c:otherwise>
												<a href="goMiscellaneousRequest?tripId=${id}"
													class="status-createnew" data-toggle="modal"> <i
													class="fa fa-plus"></i>
												</a>
											</c:otherwise>
										</c:choose></td>
										
										
									<td><a href="tripView?id=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a></td>

								</tr>
							</s:iterator>
						</tbody>
					</table>

								<table id="pagtable" >
								 
				 <tr id="tr">
				 		 <span>Showing <s:property value="%{((apiProviderPage.currentPageIndex - 1)*apiProviderPage.maxItems)+1}" /> to <s:property value="%{((apiProviderPage.currentPageIndex*apiProviderPage.maxItems) <= apiProviderPage.availableItems)?(apiProviderPage.currentPageIndex*apiProviderPage.maxItems):flightReportPage.availableItems}" /> of <s:property value="%{apiProviderPage.availableItems}" /> items</span>
				 
				 </tr>
				 <tr id="tr">
			 
			 	<c:if test="${apiProviderPage.currentPageIndex>1}">
			 		<td id="td"><button type="submit" name="apiProviderPage.currentPageIndex"  value="1" class="btn btn-primary">First</button></td>			 
			 		<td id="td"><button type="submit" name="apiProviderPage.currentPageIndex"  value="${apiProviderPage.currentPageIndex - 1}" class="btn btn-primary">Prev</button></td>		
				</c:if>
					 
			 	<c:forEach begin="${(apiProviderPage.currentPageIndex) > 1? (apiProviderPage.currentPageIndex) : 1}" end="${ (apiProviderPage.currentPageIndex + 4) <= apiProviderPage.availablePages ? (apiProviderPage.currentPageIndex + 4) :  apiProviderPage.availablePages }" var="i">						
					<td>
					<button type="submit" name="apiProviderPage.currentPageIndex"  value="${i}" class="btn btn-primary" >
					<c:choose>
								<c:when test="${apiProviderPage.currentPageIndex == i}">
									 <u>${i}</u>
								</c:when>

								<c:otherwise>
									${i}								
								</c:otherwise>
					</c:choose>
					</button>
					</td>						
				</c:forEach>
				<c:if test="${(apiProviderPage.currentPageIndex + 4) < apiProviderPage.availablePages}">
			 		<td id="td"><button type="submit" name="apiProviderPage.currentPageIndex"  value="${apiProviderPage.currentPageIndex + 1}" class="btn btn-primary">Next</button></td>	
			 		<td id="td"><button type="submit" name="apiProviderPage.currentPageIndex"  value="${apiProviderPage.availablePages}" class="btn btn-primary">Last</button></td>
				</c:if>
			  	
					</tr> 
				</table>
				</div>

			</form>

		</div>
	</section>
</div>



<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">
<script type="text/javascript">
	/* , 'pdf', 'print'  */
	$(document).ready(
			function() {
				var table = $('#example').DataTable({
					"paging" : false,
					"info" : false,
					"searching" : false,
					"ordering" : true,
					"search" : {
						"regex" : true,
					},
					/* lengthChange : true,
					"pagingType" : "full_numbers", */
					/*"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ], */
					buttons : [ 'excel' ]
				});

				table.buttons().container().appendTo(
						'#example_wrapper .col-sm-6:eq(0)');

			});
	

	function myTripFunction() {
		document.getElementById("mytripDropdown").classList.toggle("show");
	}

	// Close the dropdown if the user clicks outside of it
	window.onclick = function(event) {
		if (!event.target.matches('.tripdropbtn')) {

			var dropdowns = document
					.getElementsByClassName("tripdropdown-content");
			var i;
			for (i = 0; i < dropdowns.length; i++) {
				var openDropdown = dropdowns[i];
				if (openDropdown.classList.contains('show')) {
					openDropdown.classList.remove('show');
				}
			}
		}
	}
	$('#tripNo').focus(function() {
		$(this).val('');
	});
</script>


<style>
.btn-green {
	color: #455a64;
	border: 1px solid transparent;
	background-color: #fff;
	border-color: #00a65a;
	/* width: 70%; */
}

.btn-green:hover {
	color: #455a64;
	border: 1px solid transparent;
	background-color: #fff;
	border-color: #146c44;
	/* width: 70%; */
}

.btn-green i {
	color: #0082ca;
	font-size: 13px;
	font-weight: 500;
	text-align: left;
	float: left;
}

.blue-icon {
	color: white;
	border: 1px solid transparent;
	border-radius: 10px;
	background: #0082ca;
}

.btn-green.focus, .btn-green:focus, .btn-green:active {
	color: #1b1919;
	background-color: #949994;
	border-color: #255625;
}

.support #dropdown {
	width: 100%;
	background-color: #fff;
	border: 1px solid #ccc;
	border-radius: 0px;
	padding: 5px 2px 5px 10px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

.support #dropdown span {
	color: #555;
}

.support .dropdownwrap .form-group, .support .dropdownwrap .form-group .input-group
	{
	width: 100% !important;
}

.tripdropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

/*.tripdropbtn:hover, .tripdropbtn:focus {
    background-color: #3e8e41;
}*/
.tripdropdown {
	float: right;
	position: relative;
	display: inline-block;
}

.tripdropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	overflow: auto;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	right: 0;
}

.tripdropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.tripdropdown a:hover {
	background-color: #f1f1f1
}

.show {
	display: block;
}
</style>




