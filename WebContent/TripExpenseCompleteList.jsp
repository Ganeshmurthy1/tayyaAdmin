<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="/struts-tags" prefix="s"%>

<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script> --%>
<link rel="stylesheet" href="css/pagination_css.css">



    
<div class="content-wrapper">

	<!-- Content Header (Page header) -->
<div class="sccuss-full-updated" id="success-alertdelete"
		style="display: none">
		
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="messagedelete"></div>
			<button type="button" id="successdelete" class="btn btn-primary">Ok</button>

		</div>
		
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
		
	</div>
	 <div class="row">
		<div class="col-sm-12">
		<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
				</div>
			
		<hr/>
		</div>
		</div>
	
	<c:if test="${expenseToView.flightExpenseList!=null && expenseToView.flightExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Flight Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
			
			<%-- <table style="border: 1px solid;">
  
   <c:forEach var="flight" items="${expenseToView.flightExpenseList}">
       
       <tr>
      
       <td>${flight.pnrNumber}</td>
       <td>${user.flightCarrier}</td>
      
       </tr>
      
   </c:forEach>
   
   </table> --%>
			
			
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>PNR Number</th>
								<th>Carrier</th>
								<th>Departure Date</th>
								<th>Arrival Date</th>
								<th>Currency</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.flightExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									<td><s:property value="pnrNumber" /></td>
									<td><s:property value="flightCarrier" /></td>
									<td> <s:date name="departureDate" format="dd/MM/yyyy"/></td>
									<td> <s:date name="arrivDate" format="dd/MM/yyyy"/></td>
									<%-- <td><s:property value="departureDate" /></td>
									<td><s:property value="arrivDate" /></td> --%>
									<td><s:property value="expenseCurrency" /></td>
									<td><s:property value="totalAmount" /></td>
									<td> 
							<p data-placement="top" title="view">
									 <a href="expensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>
									
									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteFlightExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>

									
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		<c:if test="${expenseToView.hotelExpenseList!=null && expenseToView.hotelExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Hotel Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Hotel confirm No</th>
								<th>Hotel Name</th>
								<th>CheckIn Date</th>
								<th>CheckOut Date</th>
								<th>Currency</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.hotelExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									<td><s:property value="hotelConfirmNumber" /></td>
									<td><s:property value="hotelName" /></td>
									<%-- <td><s:property value="checkInDate" /></td> --%>
									<td> <s:date name="checkInDate" format="dd/MM/yyyy"/></td>
									<td> <s:date name="checkOutDate" format="dd/MM/yyyy"/></td>
									<%-- <td><s:property value="checkOutDate" /></td> --%>
									<td><s:property value="expenseCurrency" /></td>
									<td><s:property value="totalAmount" /></td>
									
									<td> 
							<p data-placement="top" title="view">
									 <a href="hotelexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>

									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteHotelExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		<c:if test="${expenseToView.carExpenseList!=null && expenseToView.carExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Car Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Confirmation</th>
								<th>Passenger  Name</th>
								<th>Car Company  Name</th>
								<th>Travel Location</th>
								<th>PickUp Location</th>
								<th>DropOff Location</th>
								<th>Travel Date</th>
								<th>Base  Price</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.carExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									 <td><s:property value="confirmationNumber" /></td>
									<td><s:property value="passengerName" /></td>
									<td><s:property value="carCompanyName" /></td>
									<td><s:property value="location" /></td>
									<td><s:property value="pickUp" /></td>
									<td><s:property value="dropOff" /></td>
									<%-- <td><s:property value="travelDate" /></td> --%>
									<td> <s:date name="travelDate" format="dd/MM/yyyy"/></td>
									<td><s:property value="basePrice" /></td>
									<td><s:property value="totalAmount" /></td>
									<td> 
							<p data-placement="top" title="view">
									 <a href="carexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>

									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteCarExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		<c:if test="${expenseToView.trainExpenseList!=null && expenseToView.trainExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Train Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Confirmation No</th>
								<th>passengerName</th>
								<th>Train Number</th>
								<th>Departure Location</th>
								<th>Arrival Location</th>
								<th>Travel Date</th>
								<th>Base Price</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.trainExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									<td><s:property value="pnrNumber" /></td>
									<td><s:property value="passengerName" /></td>
									<td><s:property value="trainNumber" /></td>
									<td><s:property value="fromlocation" /></td>
									<td><s:property value="tolocation" /></td>
									<%-- <td><s:property value="travelDate" /></td> --%>
									<td> <s:date name="travelDate" format="dd/MM/yyyy"/></td>
									<td><s:property value="basePrice" /></td>
									<td><s:property value="totalAmount" /></td>
									
									<td> 
							<p data-placement="top" title="view">
									 <a href="trainexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>
									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteTrainExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		<c:if test="${expenseToView.busExpenseList!=null && expenseToView.busExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Bus Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Confirmation Number</th>
								<th>Passenger  Name </th>
								<th>Travel Location</th>
								<th>PickUp Location</th>
								<th>DropOff Location</th>
								<th>Travel Date</th>
								<th>Base Price</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.busExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									 <td><s:property value="confirmationNumber" /></td>
									<td><s:property value="passengerName" /></td>
									<td><s:property value="location" /></td>
									<td><s:property value="pickUp" /></td>
									<td><s:property value="dropOff" /></td>
									<%-- <td><s:property value="travelDate" /></td> --%>
									<td> <s:date name="travelDate" format="dd/MM/yyyy"/></td>
									<td><s:property value="basePrice" /></td>
									<td><s:property value="totalAmount" /></td>
									<td> 
							<p data-placement="top" title="view">
									 <a href="busexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>

									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteBusExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		<c:if test="${expenseToView.mealExpenseList!=null && expenseToView.mealExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Meal Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Vendor</th>
								<th>Meal Location</th>
								<th>Travel Date</th>
								<th>Currency</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.mealExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									<td><s:property value="vendor" /></td>
									<td><s:property value="location" /></td>
									<%-- <td><s:property value="expenseDate" /></td> --%>
									<td> <s:date name="expenseDate" format="dd/MM/yyyy"/></td>
									<td><s:property value="expenseCurrency" /></td>
									<td><s:property value="totalAmount" /></td>
									
									<td> 
							<p data-placement="top" title="view">
									 <a href="mealexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>

									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteMealExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		<c:if test="${expenseToView.laborExpenseList!=null && expenseToView.laborExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Labor Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Work Name</th>
								<th>Travel Location</th>
								<th>Travel Date</th>
								<th>Worked Hours</th>
								<th>Currency</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.laborExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									<td><s:property value="workName" /></td>
									<td><s:property value="location" /></td>
									<%-- <td><s:property value="expenseDate" /></td> --%>
									<td> <s:date name="expenseDate" format="dd/MM/yyyy"/></td>
									<td><s:property value="hours" /></td>
									<td><s:property value="expenseCurrency" /></td>
									<td><s:property value="totalAmount" /></td>
									<td> 
							<p data-placement="top" title="view">
									 <a href="laborexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>
									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteLaborExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		<c:if test="${expenseToView.visaExpenseList!=null && expenseToView.visaExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Visa Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Confirmation No</th>
								<th>Passenger  Name</th>
								<th>Travel Date</th>
								<th> Base  Price</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.visaExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									 <td><s:property value="confirmationNumber" /></td>
									 <td><s:property value="passengerName" /></td>
									<td> <s:date name="travelDate" format="dd/MM/yyyy"/></td>
									<td><s:property value="basePrice" /></td>
									 <td><s:property value="totalInvoiceAmount" /></td> 
									
									<%-- <td><s:property value="expenseCurrency" /></td>
									<td><s:property value="totalAmount" /></td> --%>
									<td> 
							<p data-placement="top" title="view">
									 <a href="visaexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>
									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteVisaExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		
		<c:if test="${expenseToView.insuranceExpenseList!=null && expenseToView.insuranceExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Insuranse Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Confirmation Number</th>
								<th>Passenger Name</th>
								<th>Travel Date</th>
								<th>Base Price</th>
								<th>Total Amount</th>
								<!-- <th>Currency</th>
								<th></th> -->
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.insuranceExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									 <td><s:property value="confirmationNumber" /></td>
									 <td><s:property value="passengerName" /></td>
									 <td> <s:date name="travelDate" format="dd/MM/yyyy"/></td>
									<td><s:property value="basePrice" /></td>
									<td><s:property value="totalInvoiceAmount" /></td>
									<%-- <td><s:property value="expenseDate" /></td> --%>
									
									<%-- <td><s:property value="expenseCurrency" /></td>
									<td><s:property value="totalAmount" /></td> --%>
									<td> 
							<p data-placement="top" title="view">
									 <a href="insuranceexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>
									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteInsuranceExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
		 <div class="row">
		<div class="col-sm-12">
		<hr/>
		</div>
		</div>
		
		<c:if test="${expenseToView.miscellaneousExpenseList!=null && expenseToView.miscellaneousExpenseList.size()>0}">
	 <section class="content">
	<section class="content-header">
		<h1>Miscellaneous Expense List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>
	<section class="content">
	
		<div class="row">
			
<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Confirmation Number</th>
								<th>Passenger Name</th>
								<th>Department</th>
								<th>Travel Location</th>
								<th>Travel Date</th>
								<th>Base Price</th>
								<th>Total Amount</th>
								<th>View</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="expenseToView.miscellaneousExpenseList" status="rowCount">
								<tr>
									 <td><s:property value="%{#rowCount.count}" /></td>	
									 <td><s:property value="confirmationNumber" /></td>
									 <td><s:property value="passengerName" /></td>
									<td><s:property value="department" /></td>
									<td><s:property value="location" /></td>
									<%-- <td><s:property value="expenseDate" /></td> --%>
									<td> <s:date name="expenseDate" format="dd/MM/yyyy"/></td>
									<td><s:property value="basePrice" /></td>
									<td><s:property value="totalAmount" /></td>
									<td> 
							<p data-placement="top" title="view">
									 <a href="mislaniousexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="expensesEdit?id=<s:property value="id" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>
									<td>
										<p data-placement="top" title="Delete">

											<a
												href="deleteMiscellaneousExpense?id=<s:property value="id" />"
												class="btn btn-danger btn-xs fa fa-trash" data-toggle="modal"> Delete
											</a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					
					
		</div>
		</section>
		</section>
		</c:if>
					</div>
					
					
					<%@ include file="DashboardFooter.jsp"%>
					<script type="text/javascript">
		function userDelete(id) {

			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "deleteFlightExpense";

			$("#yes" + id)
					.click(
							function() {
								$("#delete" + id).hide();
								$(".modal-backdrop").hide();
								$
										.ajax({
											method : "POST",
											url : finalUrl,
											data : {
												id : id
											},

											success : function(data, status) {
												if (data == "deleted") {
													$('#success-alertdelete')
															.show();
													$('#messagedelete')
															.text(
																	"Successfully deleted.");
													$('#successdelete')
															.click(
																	function() {
																		$(
																				'#success-alertdelete')
																				.hide();
																		window.location
																				.assign($(
																						location)
																						.attr(
																								'href'));
																	});

												} else if (data == "failed") {
													$('#error-alert').show();
													$('#message')
															.text(
																	"Failed.Try again.");
													$('#cancel')
															.click(
																	function() {
																		$(
																				'#error-alert')
																				.hide();

																	});
												}

											},
											error : function(e) {
												alert(e.message);
											}
										});

							});

		}
	</script>
	<script type="text/javascript">
		$(function() {
			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "tripexpenseListpage?id="+id;
			$('#success').click(function() {
				window.location.assign(finalUrl);
				$('#success-alert').hide();

			});
			$('#cancel').click(function() {
				$('#error-alert').hide();

			});
		});
	</script>
	
					