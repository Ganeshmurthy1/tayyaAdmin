<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
  
<div class="content-wrapper">
<section class="content-header">
		<div class="row">
			<div class="col-sm-4 clearfix pull-left">
				<h3>Trip Expense Details</h3>
			</div>
			
			<div class="col-sm-8 clearfix pull-right " data-placement="top">
				<div class="row">
					<%-- <div class="col-sm-5 clearfix pull-left " data-placement="top">
						<a href="hotelTravelRequestEdit?id=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-edit"></i> Travel Request
						</a> --%>
						<%-- 	<a href="hotelOrderQuotationView?id=<s:property value="id"/>"
					class="btn btn-success btn-xs" data-toggle="modal">
					 Details
				</a> --%>

						<%-- <a
							href="goHotelRequestTravelQuotation?hotelQuotationRequestId=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-plus"></i> Quotation
						</a> <a
							href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-list"></i> Quotation
						</a>
					</div> --%>
					<div class="col-sm-3 clearfix pull-right" data-placement="top">
				<div class="myDropdown">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Create Expense
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				    <li><a href="goHotelExpense">Hotel</a></li>
								<li><a href="goFlightExpense">Flight</a></li>
								<li><a href="goCarExpense">Car</a></li>
								<li><a href="goTrainExpense">Train</a></li>
								<li><a href="goBusExpense">Bus</a></li>
								<li><a href="goInsuranceExpense">Insurance</a></li>
								<li><a href="goVisaExpense">Visa</a></li>
								<li><a href="goMealExpense">meal</a></li>			
								<li><a href="goLaborExpense">labour</a></li>	
								<li><a href="goMiscellaneousExpense">Miscellaneous</a></li>	
				  </ul>
				</div>
			</div>	  
				</div>
			</div>
		</div>
	</section>
	<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
	
	 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
	
	
	
	<%-- <c:if test="${flightExpense !=null }">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>Flight Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">

				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>OrderId</p>
							</div>
							<div class="p-inp">
								<p>${flightExpense.department}</p>
							</div>
						</div>
					</div>
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Employee Name</p>
							</div>
							<div class="p-inp">
								<p>${flight.empName}</p>
							</div>
						</div>
					</div>



					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>PNR No</p>
							</div>
							<div class="p-inp">
								<p>${flightExpense.pnrNumber}</p>
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
								<p>${flightExpense.flightCarrier}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Departure Date</p>
							</div>
							<div class="p-inp">
								<p>${flightExpense.departureDate}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Arrival Date</p>
							</div>
							<div class="p-inp">
								<p>${flightExpense.arrivDate}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<time> ${flight.country} </time>
							</div>
						</div>
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${flightExpense.department} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${flightExpense.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp">
								<time> ${flightExpense.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${flightExpense.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${flightExpense.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${flightExpense.billable} </time>
							</div>
						</div>
						
						
					</div>
				</div>
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
 <c:if test="${hotelExpense !=null && hotelExpense !=''}">
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


				<div class="row o-list-view">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>OrderId</p>
							</div>
							<div class="p-inp">
								<p>${hotelExpense.orderId}</p>
							</div>
						</div>
					</div>
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Employee Name</p>
							</div>
							<div class="p-inp">
								<p></p>
							</div>
						</div>
					</div>


  

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>HotelConfirmNumber No</p>
							</div>
							<div class="p-inp">
								<p>${hotelExpense.hotelConfirmNumber}</p>
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
								<p>${hotelExpense.hotelName}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Hotel Location </p>
							</div>
							<div class="p-inp">
								<p>${hotelExpense.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Hotel RoomType </p>
							</div>
							<div class="p-inp">
								<p>${hotelExpense.roomType}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>CheckIn Date</p>
							</div>
							<div class="p-inp">
								<p>${hotelExpense.checkInDate}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>CheckOut Date</p>
							</div>
							<div class="p-inp">
								<p>${hotelExpense.checkOutDate}</p>
							</div>
						</div>
						
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${hotelExpense.department} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${hotelExpense.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp">
								<time> ${hotelExpense.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${hotelExpense.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${hotelExpense.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${hotelExpense.billable} </time>
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
		<hr/>
		</div>
		</div>
 <c:if test="${carExpense!=null }">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>	Car Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">



				<div class="row ">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Car Company  Name </p>
							</div>
							<div class="p-inp">
								<p>${carExpense.carCompanyName}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Location </p>
							</div>
							<div class="p-inp">
								<p>${carExpense.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>PickUp Location </p>
							</div>
							<div class="p-inp">
								<p>${carExpense.pickUp}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>DropOff Location</p>
							</div>
							<div class="p-inp">
								<p>${carExpense.dropOff}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${carExpense.travelDate}</p>
							</div>
						</div>
						
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${carExpense.department} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${carExpense.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp">
								<time> ${carExpense.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${carExpense.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${carExpense.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${carExpense.billable} </time>
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
		<hr/>
		</div>
		</div>
 
 <c:if test="${trainExpense!=null }">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>Train Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>PNR Number</p>
							</div>
							<div class="p-inp">
								<p>${trainExpense.pnrNumber}</p>
							</div>
						</div>
					</div>
					



					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>train  Number</p>
							</div>
							<div class="p-inp">
								<p>${trainExpense.trainNumber}</p>
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
								<p>${trainExpense.fromlocation}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>To Location </p>
							</div>
							<div class="p-inp">
								<p>${trainExpense.tolocation}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Traveled Date</p>
							</div>
							<div class="p-inp">
								<p>${trainExpense.travelDate}</p>
							</div>
						</div>
						
						
						
					</div>

					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${trainExpense.department} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${trainExpense.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp">
								<time> ${trainExpense.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${trainExpense.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${trainExpense.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${trainExpense.billable} </time>
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
		<hr/>
		</div>
		</div>
 
 <c:if test="${busExpense!=null }">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>Bus Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Bus Type</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.busType}</p>
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
								<p>${busExpense.location}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Pickup Location</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.pickUp}</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>DropOff Location</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.dropOff}</p>
							</div>
						</div>
						
					</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.travelDate}</p>
							</div>
						</div>
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Department</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.department} </time>
							</div>
						</div>
						



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.billable} </time>
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
		<hr/>
		</div>
		</div>
 <c:if test="${mealExpense!=null }">
	 <section class="content">
	 
	 <div class="row">
		<div class="col-sm-12">
		<h4>Meal Expense Details</h4>
		</div>
		</div>		
	
			<div class="row">
			
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-4 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Vendor</p>
							</div>
							<div class="p-inp">
								<p>${mealExpense.vendor}</p>
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
								<p>${mealExpense.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${mealExpense.expenseDate}</p>
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
								<time> ${mealExpense.department} </time>
							</div>
						</div>
						

					



						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${mealExpense.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp">
								<time> ${mealExpense.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${mealExpense.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${mealExpense.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${mealExpense.billable} </time>
							</div>
						</div>
						
						
					</div>
				</div>
			
			</div>
		
		
	 
	 </section>
	 </c:if>
 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
  --%>
 <c:if test="${laborExpense!=null }">
	 <section class="content">
	 
	 <div class="row">
		<div class="col-sm-12">
		<h4>Labor Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
			<!-- edit form column -->
			<div class="col-sm-12 personal-info">


				<div class="row o-list-view">
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Work Name</p>
							</div>
							<div class="p-inp">
								<p>${laborExpense.workName}</p>
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
								<p>${laborExpense.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${laborExpense.expenseDate}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Worked Hours</p>
							</div>
							<div class="p-inp">
								<p>${laborExpense.hours}</p>
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
								<time> ${laborExpense.department} </time>
							</div>
						</div>


						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${laborExpense.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp">
								<time> ${laborExpense.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${laborExpense.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${laborExpense.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${laborExpense.billable} </time>
							</div>
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
		<hr/>
		</div>
		</div>
<%--  <c:if test="${miscellaneousExpense!=null }">
	 <section class="content">
	 <div class="row">
		<div class="col-sm-12">
		<h4>miscellaneous Expense Details</h4>
		</div>
		</div>		
		
			<div class="row">
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
								<time> ${miscellaneousExpense.department} </time>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Location </p>
							</div>
							<div class="p-inp">
								<p>${miscellaneousExpense.location}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${miscellaneousExpense.expenseDate}</p>
							</div>
						</div>
						
						
					<div class="col-sm-6 ">
						

						


						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneousExpense.expenseCurrency} </time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Total Amount</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneousExpense.totalAmount} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reason</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneousExpense.expenseReason} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Reimbursable</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneousExpense.reimbursable} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Expense Billable</p>
							</div>
							<div class="p-inp">
								<time> ${miscellaneousExpense.billable} </time>
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
		<hr/>
		</div>
		</div>
 
 
 
 
 
  --%>
 
 
 
 
 
 
 
 
 

</div>

<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">

<!-- /.content-wrapper -->
