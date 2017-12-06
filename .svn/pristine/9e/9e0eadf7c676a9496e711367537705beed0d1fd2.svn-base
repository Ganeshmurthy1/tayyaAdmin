<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>flight expense view</title>
</head>
<body>
		
		<c:if test="${expenseToView.carExpenseList!=null && expenseToView.carExpenseList.size()>0}">
		<div class="content-wrapper" style="min-height: 245px;">
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
								<th>Exp Docs</th>
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
									
									<s:if test="%{receiptFilename!=null}">
									<td> 
									<form action="downloadExpenses">
  						   				 <input type="hidden" value="${receiptFilename}" name="fileNameeee"> 
  										<button type="submit" class="button"  >Download  Expenses </button>
  									</form>
										 </td>
									</s:if>
									<s:else>
									<td>NO DATA</td>
									</s:else>
									
									
									<td> 
							<p data-placement="top" title="view">
									 <a href="carexpensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="carExpensesEdit?id=<s:property value="id" />"
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
		</div>
		</c:if>
</body>
</html>