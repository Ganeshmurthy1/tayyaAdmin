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
				<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Confirmation Number</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.confirmationNumber}</p>
							</div>
						</div>
					</div>
					<div class="col-sm-6 ">
				<div class="p-info clearfix">
							<div class="p-label">
								<p>Passenger Name </p>
							</div>
							<div class="p-inp">
								<p>${busExpense.passengerName}</p>
							</div>
						</div>
						</div>
						</div>
						<div class="row">
						
					<div class="col-sm-6 ">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Bus Type</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.busType}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Bus Location </p>
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
								<p>Travel Date</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.travelDate}</p>
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
								<p>Remarks</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.remarks}</p>
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
					
					
				

				
					<div class="col-sm-6 ">
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
								<p>Supplier Price</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.supplierPrice}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Base Price</p>
							</div>
							<div class="p-inp">
								<p>${busExpense.basePrice}</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Management Fee</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.managementFee} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Convenience Fee</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.convenienceFee} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Service Tax</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.serviceTax} </time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Other Taxes</p>
							</div>
							<div class="p-inp">
								<time> ${busExpense.otherTaxes} </time>
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

 
 
 
 
 

</div>

<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">

<!-- /.content-wrapper -->
