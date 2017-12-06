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
button.button 
{
    font-family: "Helvetica Neue", Helvetica, /*Tahoma,*/ Arial, sans-serif;
    border-radius: 20px;
    -moz-border-radius: 20px;
    -webkit-border-radius: 20px;
    -khtml-border-radius: 20px;
    background: #60b82d;
    background: -moz-linear-gradient(top, #69ca3b 0%, #56a61e 100%);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#69ca3b), color-stop(100%,#56a61e));
    background: linear-gradient(#69ca3b, #56a61e);
    -pie-background: linear-gradient(#69ca3b, #56A61E);
    text-shadow: 0 -1px 0px rgba(0,0,0,0.15);
    box-shadow: inset 0px 1px 0px #81f54a;
    -moz-box-shadow: inset 0px 1px 0px #81f54a;
    -webkit-box-shadow: inset 0px 1px 0px #81f54a;
    position: relative;
    outline: none;
    font-size: 14px;
    height: 5.158em;
    line-height: 30px;
    font-weight: normal;
    color: #fff!important;
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
		<div class="col-sm-4 clearfix pull-left">
		<h3>Trip Details</h3>
		</div>
		<div class="col-sm-8 clearfix pull-right " data-placement="top">
		<div class="row">
		<div class="col-sm-5 clearfix pull-left " data-placement="top">
		</div>
		<div class="col-sm-4 clearfix " data-placement="top">		
				<a href="goTrips"
					class="btn btn-top-link btn-xs" >
					 All Trips
				</a>
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  title="Check Hotel Trips">
					 Hotel Trips
				</a>
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs"  title="Check Flight Trips">
					 Flight Trips
				</a>
			</div>		
			<div class="col-sm-3 clearfix pull-right" data-placement="top">
				<div class="myDropdown">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Create Trip
				  <span class="caret"></span></button>
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
	<div>
	<div id="mypdf">
	<c:if test="${tripRequest.hotelTravelRequest!=null}">
	<section class="content">
		<div class="row">
		<div class="col-sm-12">
		<h4>Hotel Request Details</h4>
		</div>
		</div>
	<section class="content">
	
		<div class="row">
		<div class="col-sm-12 pull-right">
				<a href="hotelTravelRequestEdit?id=${tripRequest.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-edit"></i> Travel Request 
				</a>
			<%-- 	<a href="hotelOrderQuotationView?id=<s:property value="id"/>"
					class="btn btn-success btn-xs" data-toggle="modal">
					 Details
				</a> --%>
				
				<a href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${tripRequest.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					  <i class= "fa fa-plus"></i> Quotation
				</a>
				<a href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${tripRequest.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-list"></i> Quotation
				</a>
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
								  ${tripRequest.hotelTravelRequest.noOfNights}  
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								  ${tripRequest.hotelTravelRequest.city}  
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								  ${tripRequest.hotelTravelRequest.currency}  
							</div>
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
		<hr/>
		</div>
		</div>
		<c:if test="${tripRequest.flightTravelRequest!=null}">
<section class="content">
		<div class="row">
		<div class="col-sm-12">
		<h4>Flight Request Details</h4>
		</div>
		</div>		
		<div class="row">
			<div class="col-sm-12 pull-right">
										<a
											href="flightTravelRequestEdit?id=${tripRequest.flightTravelRequest.id}"
											class="btn btn-success btn-xs" data-toggle="modal">
											 Edit
										</a>
										<a
											href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${tripRequest.flightTravelRequest.id}"
											class="btn btn-success btn-xs" data-toggle="modal"  title="Create">
											 Create Quotation
										</a>
										 
										<a  title="All Quotations"
											href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${tripRequest.flightTravelRequest.id}"
											class="btn btn-success btn-xs" data-toggle="modal">
											 Quotations
										</a>
				  
										
				  
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
								<p>DepartureDate</p>
							</div>
							<div class="p-inp">
								<p>${tripRequest.flightTravelRequest.departureDate}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>ArrivalDate</p>
							</div>
							<div class="p-inp">
								<p>${tripRequest.flightTravelRequest.arrivalDate}</p>
							</div>
						</div>
						<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<time> ${tripRequest.flightTravelRequest.country} </time>
							</div>
						</div> --%>
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>PassengerCount</p>
							</div>
							<div class="p-inp">
								<time> ${tripRequest.flightTravelRequest.passengerCount} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								<time> ${tripRequest.flightTravelRequest.destination} </time>
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
	
	<div class="row">
		<div class="col-sm-12">
		</div>
		</div>
	
<c:if test="${expenseToView.flightExpenseList!=null && expenseToView.flightExpenseList.size()>0}">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>Flight Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">
			
			
		
			
			
<c:forEach items="${expenseToView.flightExpenseList}" var="flight">

 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
<div class="flightrow">
	<div class="col-sm-12 pull-right">
			<%-- 	<a href="hotelTravelRequestEdit?id=${flight.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-edit"></i>Edit 
				</a>
				<a href="hotelOrderQuotationView?id=<s:property value="id"/>"
					class="btn btn-success btn-xs" data-toggle="modal">
					 Details
				</a>
				
				<a href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${flight.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					  <i class= "fa fa-plus"></i> Quotation
				</a>
				<a href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${flight.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-list"></i> Quotation
				</a> --%>
		</div>
			

				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>OrderId</p>
							</div>
							<div class="p-inp">
								<p>${flight.orderId}</p>
							</div>
						</div>
					</div>
					<%-- <div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Employee Name</p>
							</div>
							<div class="p-inp">
								<p>${flight.empName}</p>
							</div>
						</div>
					</div> --%>



					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>PNR No</p>
							</div>
							<div class="p-inp">
								<p>${flight.pnrNumber}</p>
							</div>
						</div>
					</div>
					
				</div>

				<div class="row ">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Flight Carrier </p>
							</div>
							<div class="p-inp">
								<p>${flight.flightCarrier}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Departure Date</p>
							</div>
							<div class="p-inp">
								<p>${flight.departureDate}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Arrival Date</p>
							</div>
							<div class="p-inp">
								<p>${flight.arrivDate}</p>
							</div>
						</div>
						<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<time> ${flight.country} </time>
							</div>
						</div> --%>
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${flight.department} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${flight.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp" id="flighttotamt">
								  ${flight.totalAmount}  
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${flight.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${flight.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${flight.billable} </time>
							</div>
						</div>
						
						<c:choose>
    						<c:when test="${empty flight.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${flight.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button">Download Flight Expenses </button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
				<!--  console.log('total price is'+totalPrice); -->
				 
				
						
						
					</div>
				</div>
				</div>
				</c:forEach>
			</div>
		</div>
	 
	 
	 
	 
	</section>
	
	
	</c:if>
<!-- Main Content
 --><div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
 <c:if test="${expenseToView.hotelExpenseList!=null && expenseToView.hotelExpenseList.size()>0}">
	 <section class="content">
	 <div>
		<div class="row">
		<div class="col-sm-12">
		<h4>Hotel Expense Details</h4>
		</div>
		</div>
		</div>		

			<div class="row">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">
<c:forEach items="${expenseToView.hotelExpenseList}" var="hotel">

<div class="hotelrow">

				<div class="row o-list-view">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>OrderId</p>
							</div>
							<div class="p-inp">
								<p>${hotel.orderId}</p>
							</div>
						</div>
					</div>
				<%-- 	<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Employee Name</p>
							</div>
							<div class="p-inp">
								<p>${expense.tripRequest.hotelTravelRequest.empName}</p>
							</div>
						</div>
					</div> --%>


  

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>HotelConfirmNumber No</p>
							</div>
							<div class="p-inp">
								<p>${hotel.hotelConfirmNumber}</p>
							</div>
						</div>
					</div>
					
					
				
				</div>

				<div class="row ">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Hotel Name </p>
							</div>
							<div class="p-inp">
								<p>${hotel.hotelName}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Hotel Location </p>
							</div>
							<div class="p-inp">
								<p>${hotel.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Hotel RoomType </p>
							</div>
							<div class="p-inp">
								<p>${hotel.roomType}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>CheckIn Date</p>
							</div>
							<div class="p-inp">
								<p>${hotel.checkInDate}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>CheckOut Date</p>
							</div>
							<div class="p-inp">
								<p>${hotel.checkOutDate}</p>
							</div>
						</div>
						
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${hotel.department} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${hotel.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp" id="hoteltotamt">
								<time> ${hotel.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${hotel.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${hotel.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${hotel.billable} </time>
							</div>
						</div>
						<c:choose>
    						<c:when test="${empty hotel.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${hotel.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button"  >Download Hotel Expenses </button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
				</div>
					</c:forEach>
			</div>
		</div>
	</section>
	
	
	</c:if>
	<div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
 <c:if test="${expenseToView.carExpenseList!=null && expenseToView.carExpenseList.size()>0}">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>	Car Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">
<c:forEach items="${expenseToView.carExpenseList}" var="car">
<div class="carexprow">

				<div class="row ">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Car Company Name Name </p>
							</div>
							<div class="p-inp">
								<p>${car.carCompanyName}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Location </p>
							</div>
							<div class="p-inp">
								<p>${car.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>PickUp Location </p>
							</div>
							<div class="p-inp">
								<p>${car.pickUp}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>DropOff Location</p>
							</div>
							<div class="p-inp">
								<p>${car.dropOff}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${car.travelDate}</p>
							</div>
						</div>
						
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${car.department} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${car.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp" id="cartotamt">
								<time> ${car.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${car.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${car.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${car.billable} </time>
							</div>
						</div>
						<c:choose>
    						<c:when test="${empty car.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${car.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button">Download Car Expenses</button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
				</div>
				</c:forEach>
			</div>
		</div>
	 
	 
	 </section>
	 </c:if>
 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
 
 <c:if test="${expenseToView.trainExpenseList!=null && expenseToView.trainExpenseList.size()>0}">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>Train Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			<c:forEach items="${expenseToView.trainExpenseList}" var="train">
			<!-- edit form column -->
			<div class="trainexprow">
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>PNR Number</p>
							</div>
							<div class="p-inp">
								<p>${train.pnrNumber}</p>
							</div>
						</div>
					</div>
					



					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>train  Number</p>
							</div>
							<div class="p-inp">
								<p>${train.trainNumber}</p>
							</div>
						</div>
					</div>
					
				</div>

				<div class="row ">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>From  Location </p>
							</div>
							<div class="p-inp">
								<p>${train.fromlocation}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>To Location </p>
							</div>
							<div class="p-inp">
								<p>${train.tolocation}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Traveled Date</p>
							</div>
							<div class="p-inp">
								<p>${train.travelDate}</p>
							</div>
						</div>
						
						
						
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${train.department} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${train.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp" id="traintotamt">
								<time> ${train.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${train.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${train.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${train.billable} </time>
							</div>
						</div>
							<c:choose>
    						<c:when test="${empty train.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${train.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button">Download Train Expenses</button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
			</div>
			</div>
			</c:forEach>
		</div>
	 
	 
	 </section>
	 </c:if>
 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
 
 <c:if test="${expenseToView.busExpenseList!=null && expenseToView.busExpenseList.size()>0}">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>Bus Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			<c:forEach items="${expenseToView.busExpenseList}" var="bus">
			<!-- edit form column -->
			<div class="busexprow">
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Bus Type</p>
							</div>
							<div class="p-inp">
								<p>${bus.busType}</p>
							</div>
						</div>
					</div>
					
					
				</div>

				<div class="row ">
					<div class="col-sm-6 ">
						
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
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${bus.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${bus.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${bus.billable} </time>
							</div>
						</div>
						<c:choose>
    						<c:when test="${empty bus.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${bus.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button">Download Train Expenses</button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
						
					</div>
				</div>
			</div>
			</div>
			</c:forEach>
		</div>
	 
	 
	 </section>
	 </c:if>
 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
 <c:if test="${expenseToView.mealExpenseList!=null && expenseToView.mealExpenseList.size()>0}">
	 <section class="content">
	 
	 <div class="row">
		<div class="col-sm-12">
		<h4>Meal Expense Details</h4>
		</div>
		</div>		
		<c:forEach items="${expenseToView.mealExpenseList}" var="meal">
			<div class="row">
			<div class="mealexprow">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Vendor</p>
							</div>
							<div class="p-inp">
								<p>${meal.vendor}</p>
							</div>
						</div>
					</div>
					


					</div>
					
				</div>
				

				<div class="row ">
					<div class="col-sm-6 ">
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Location </p>
							</div>
							<div class="p-inp">
								<p>${meal.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${meal.expenseDate}</p>
							</div>
						</div>
						</div>
						</div>
						<div class="row ">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${meal.department} </time>
							</div>
						</div>
						

					



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${meal.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp" id="mealtotamt">
								<time> ${meal.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${meal.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${meal.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${meal.billable} </time>
							</div>
						</div>
						<c:choose>
    						<c:when test="${empty meal.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${meal.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button">Download Train Expenses</button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
			
			</div>
		</div>
		</c:forEach>
	 
	 </section>
	 </c:if>
 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
 
 <c:if test="${expenseToView.laborExpenseList!=null && expenseToView.laborExpenseList.size()>0}">
	 <section class="content">
	 
	 <div class="row">
		<div class="col-sm-12">
		<h4>Labor Expense Details</h4>
		</div>
		</div>		
		<c:forEach items="${expenseToView.laborExpenseList}" var="labor">
			<div class="row">
			<div class="labexprow">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Work Name</p>
							</div>
							<div class="p-inp">
								<p>${labor.workName}</p>
							</div>
						</div>
					</div>
					


					</div>
					
				</div>

				<div class="row ">
					<div class="col-sm-6 ">
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Location </p>
							</div>
							<div class="p-inp">
								<p>${labor.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${labor.expenseDate}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Worked Hours</p>
							</div>
							<div class="p-inp">
								<p>${labor.hours}</p>
							</div>
						</div>
						</div>
						<div class="row ">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${labor.department} </time>
							</div>
						</div>


						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${labor.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp" id="labortotamt">
								<time> ${labor.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${labor.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${labor.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${labor.billable} </time>
							</div>
						</div>
						<c:choose>
    						<c:when test="${empty labor.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${labor.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button">Download Labor Expenses</button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
			</div>
		</div>
		</div>
	 </c:forEach>
	 </section>
	 </c:if>
 
 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
 <c:if test="${expenseToView.miscellaneousExpenseList!=null && expenseToView.miscellaneousExpenseList.size()>0}">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>miscellaneous Expense Details</h4>
		</div>
		</div>		
		<c:forEach items="${expenseToView.miscellaneousExpenseList}" var="miscellaneous">
			<div class="row">
			<div class="miscexprow">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">

</div>
				

				<div class="row ">
					<div class="col-sm-6 ">
					<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneous.department} </time>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Location </p>
							</div>
							<div class="p-inp">
								<p>${miscellaneous.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${miscellaneous.expenseDate}</p>
							</div>
						</div>
						</div>
						
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneous.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp" id="misctotamt">
								<time> ${miscellaneous.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneous.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneous.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneous.billable} </time>
							</div>
						</div>
						<c:choose>
    						<c:when test="${empty miscellaneous.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${miscellaneous.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button">Download Miscellaneous Expenses</button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
		</div>
		</div>
	 </c:forEach>
	 
	 </section>
	 </c:if>
	 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		
		 <c:if test="${expenseToView.visaExpenseList!=null && expenseToView.visaExpenseList.size()>0}">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>miscellaneous Expense Details</h4>
		</div>
		</div>		
		<c:forEach items="${expenseToView.visaExpenseList}" var="visa">
			<div class="row">
			<div class="miscexprow">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">

</div>
				

				<div class="row ">
					<div class="col-sm-6 ">
					<div class="p-info clearfix">
							<div class="p-label">
								<p>Confirmation Number</p>
							</div>
							<div class="p-inp">
								<time> ${visa.confirmationNumber} </time>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Description </p>
							</div>
							<div class="p-inp">
								<p>${visa.description}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${visa.travelDate}</p>
							</div>
						</div>
						</div>
						
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>BasePrice</p>
							</div>
							<div class="p-inp">
								<time> ${visa.basePrice} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Courier Charges</p>
							</div>
							<div class="p-inp" id="misctotamt">
								<time> ${visa.courierCharges} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>total Invoice Amount</p>
							</div>
							<div class="p-inp">
								<time> ${visa.totalInvoiceAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>ManagementFee</p>
							</div>
							<div class="p-inp">
								<time> ${visa.managementFee} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Convenience Fee</p>
							</div>
							<div class="p-inp">
								<time> ${visa.convenienceFee} </time>
							</div>
						</div>
						<c:choose>
    						<c:when test="${empty visa.receiptFilename}">
    						</c:when>
  							<c:otherwise>
  							<form action="downloadExpenses">
  						    <input type="hidden" value="${visa.receiptFilename}" name="fileNameeee"> 
  							<button type="submit" class="button">Download Miscellaneous Expenses</button>
  							</form>
  							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
		</div>
		</div>
	 </c:forEach>
	 
	 </section>
	 </c:if>
		
	 
	 <div class="row" >
		<div class="col-sm-12">
		 <div id="total" class="col-md-offset-4 col-md-4"><%-- ${expenseToView.totalAmount} --%>
	</div>
<c:choose> 
  <c:when test="${expenseToView.totalAmount > 0 }">
   <button type="button" class="button" >Total Expense Amount    =   ${expenseToView.totalAmount}</button>
  </c:when>
  <c:otherwise>
    <button type="button" class="button" >Total Expense Amount    =  0.00</button>
  </c:otherwise>
</c:choose>
	
	
	 
	<%--  <button type="button" class="button" >Total Expense Amount    =   ${expenseToView.totalAmount}</button> --%>

	     </div>
		<hr/>
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





