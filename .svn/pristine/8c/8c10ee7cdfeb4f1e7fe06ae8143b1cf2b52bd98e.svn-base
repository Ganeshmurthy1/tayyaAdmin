<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>flight expense view</title>
<script src="js/angular.js" type="text/javascript"></script>
	<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> 
	
	<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
	<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script>

</head>
<body>


<c:if test="${expenseToView.flightExpenseList!=null && expenseToView.flightExpenseList.size()>0}">
<div class="content-wrapper" style="min-height: 245px;">
	 <section class="content">
	<section class="content-header">
		<h1>Flight Expense List</h1>
		<!-- <!-- <ol class="breadcrumb">
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
								<th>PNR Number</th>
								<th>Carrier</th>
								<th>Departure Date</th>
								<th>Arrival Date</th>
								<th>Currency</th>
								<th>Total Amount</th>
								<th>exp docs</th>
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
									 <a href="expensesview?id=<s:property value="id" />" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top"></span> View<span></span></a>
								  </p>
										 </td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="flightExpensesEdit?id=<s:property value="id" />"
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
		</div>
		</c:if>

</body>
</html>