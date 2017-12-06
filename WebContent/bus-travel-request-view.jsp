<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<script
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
		     <a href="goTrips"
					class="btn btn-top-link btn-xs"  >
					 All Trips
				</a>	
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Flight Trips
				</a>
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  >
					 Hotel Trips
				</a>
				<a href="CarTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Car Trips
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
			</div>		
			<div class="col-sm-3 clearfix pull-right" data-placement="top">
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
			<c:if test="${busTravelRequest!=null }">
				<section class="content">
					<div class="row">
						<div class="col-sm-12">
							<h4>Bus Travel Request Details</h4>
						</div>
					</div>
					<div class="row">
						<!-- edit form column -->
						<div class="busexprow">
							<div class="col-sm-12 personal-info">
								<div class="row ">
									<c:set var="busTravelRequest" scope="session"
										value="${busTravelRequest}" />
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


									<%-- <div class="col-sm-6 ">
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Hotel Location </p>
							</div>
							<div class="p-inp">
								<p>${bus.location}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Pickup Location</p>
							</div>
							<div class="p-inp">
								<p>${bus.pickUp}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>DropOff Location</p>
							</div>
							<div class="p-inp">
								<p>${bus.dropOff}</p>
							</div>
						</div>
						
					</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${bus.travelDate}</p>
							</div>
						</div>
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${bus.department} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${bus.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp" id="bustotamt">
								<time> ${bus.totalAmount} </time>
							</div>
						</div> --%>
								</div>
							</div>
						</div>
					</div>
					<s:if
						test="busTravelRequestQuotationlist!=null && busTravelRequestQuotationlist.size()>0">
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
														<th>Travel Request Number</th>
														<th>OrderId</th>
														<th>Status</th>
														<th>Final Price</th>
														<th>Invoice</th>														
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
															<td>${busTravelRequestQuotation.busOrderRow.bookingCurrency} ${busTravelRequestQuotation.totalAmount}</td>
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





