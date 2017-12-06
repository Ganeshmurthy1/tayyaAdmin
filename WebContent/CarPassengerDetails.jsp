<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html data-ng-app="BookApp">
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
 -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%-- <script src="js/angular.js"></script> --%>
<title> </title>

</head>
<body data-ng-controller="bookcontroller" >
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Car Passenger Details </h1>
		</section>
  <!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
			<a href="#"  class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-ng-click="showmodel('Confirm')" data-ng-show="bookingstatus == 'Hold'"> <i class="fa fa-file"></i>Confirm Ticket</a> 
			<a href="#" class="btn btn-danger" data-toggle="modal" data-target="#myModal" data-ng-click="showmodel('Release')" data-ng-show="bookingstatus == 'Hold' || bookingstatus == 'Failed' ">  <i class="fa fa-file"></i> Cancel/Release Ticket</a>
				<img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
				
						<h4  >
							  <a href="companyReportList"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
						<div id="myModal" class="modal fade" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<!--  <button type="button" class="close" data-dismiss="modal">&times;</button> -->
								<h4 class="modal-title">{{modeltitle}}</h4>
							</div>
							<div class="modal-body">
								<p>{{modelcontent}} <span style="color: red;">{{pnr}}</span></p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" data-ng-click="showmodel('Proceed')">Proceed</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="userid" value="<s:property value="%{#session.Company.companyid}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.Company.Username}"/>">
				<input type="hidden" id="appkey" value="<s:property value="CurrentReportdata.appkey"/>">
				<input type="hidden" id="transkey" value="<s:property value="CurrentReportdata.transactionKey"/>">
				<input type="hidden" id="pricekey" value="<s:property value="CurrentReportdata.pricekey"/>">
				<input type="hidden" id="status" value="<s:property value="CurrentReportdata.status"/>">
				<input type="hidden" id="orderid" value="<s:property value="CurrentReportdata.orderId"/>">
				<input type="hidden" id="pnr" value="<s:property value="CurrentReportdata.pnr"/>">
			
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
			<!-- 		<form class="form-inline" action="" method="post"> -->
						<div class="table-responsive dash-table">
							<!-- testing -->
							<div class="box clearfix">
				 <div class="flight-admin-details">
                     <h3><span>Car Details:-</span></h3>

								<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									  <thead>
									<tr>
										  <th>Confirmation No</th>
										  <th>Car Company Name </th>
											<th>Invoice no</th>
											<th>Currency</th>
											<th>Order id</th>
										  	<th>Status</th>
											<th>Paid By</th>
											<th>Remarks</th>
										</tr>
									</thead>  
									<tbody>
 										<tr> 
											 <td><s:property value="carOrderRow.confirmationNumber"/></td>
 												<td><s:property value="carOrderRow.carCompanyName" /></td>
												<td><s:property value="carOrderRow.invoiceNo" /></td>
												<td><s:property value="carOrderRow.bookingCurrency" /></td>
 												<td><s:property value="carOrderRow.orderId"/></td>
 												<td><s:property value="carOrderRow.statusAction" /></td>
												<td><s:property value="carOrderRow.paidBy" /></td>
												<td><s:property value="carOrderRow.remarks" /></td>

											 </tr>
										</tbody>
                          			</table>
                      			</div>
                      			 <div class="flight-admin-details">
                     			<h3><span>Passenger Details:-</span></h3>

								<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									  <thead>
									<tr>
										   <th>FirstName</th>  
										  <th>LastName</th>
											<th>Phone no</th>
											 <th>Email</th>  
											 
										</tr>
									</thead>  
									<tbody>
											  <s:iterator value="carOrderRow.orderCustomer">
											<tr>
												<td><s:property value="firstName" /></td>
												<td><s:property value="lastName" /></td>
												<td><s:property value="phone" /></td>
												<td><s:property value="email" /></td>
											</tr>
										</s:iterator>  
										</tbody>
                          			</table>
                      			</div>
                      			 
                <div class="flight-admin-details">
                     <h3><span>Payment Details :-</span></h3>
                      <table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									  <thead>
									<tr>
										   <th>TxId</th>
											<th>Processing fee</th>
										    <th>PaymentStatus</th>
											 <th>Management fee</th>
											 	<th>Service Tax </th>
										  		<th> Other tax</th>
										  		<th>Convinience fee</th>
										  		<th>Amount</th>
										</tr>
									</thead>  
									<tbody>
											 <s:iterator value="carOrderRow">  
										    <tr> 
										  <td><s:property value="transactionKey" /></td> 
										    <td><s:property value="processingFees" /></td> 
										      <td><s:property value="paymentStatus"/></td> 
										    <td><s:property value="managementFee" /></td>  
										      <td><s:property value="serviceTax" /></td> 
										       <td><s:property value="otherTaxes" /></td>
										       <td><s:property value="convenienceFee" /></td>
										     <td><s:property value="totalAmount" /></td>
											 </tr>
										 </s:iterator> 
											 
										</tbody>
                          			</table>
                      			</div>
               <div class="flight-admin-details">
                     <h3><span>Other Details:-</span></h3>

								<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									  <thead>
									<tr>
										   <th>OrderId</th>
											<th>CreatedAt</th>
											<th>Company Id</th>
											<th>Config Id</th>
										</tr>
									</thead>  
									<tbody>
									
									 <s:iterator value="carOrderRow">  
										    <tr> 
										  <td><s:property value="orderId"/></td> 
										    <td><s:property value="CreatedAt"/></td> 
										    <td><s:property value="companyId"/></td>  
										      <td><s:property value="configId"/></td> 
										    </tr>
										 </s:iterator>  
										</tbody>
                          			</table>
                      			</div>
                      			
                      			 
						</div>
						<!-- table-responsive -->
					<!-- </form> -->
				</div>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
		<script src="js/flightconfirm.js?ver=10.0"></script>
		
</body>
</html>