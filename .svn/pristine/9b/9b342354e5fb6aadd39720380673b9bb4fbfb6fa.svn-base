<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html >
<head>
 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title> </title>

</head>
<body  >
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight Passenger Details </h1>
		</section>
  <!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
			<!-- <a href="#"  class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-ng-click="showmodel('Confirm')" data-ng-show="bookingstatus == 'Hold'"> <i class="fa fa-file"></i>Confirm Ticket</a> 
		 -->	<!-- <a href="#" class="btn btn-danger" data-toggle="modal" data-target="#myModal" data-ng-click="showmodel('Release')" data-ng-show="bookingstatus == 'Hold' || bookingstatus == 'Confirmed' ">  <i class="fa fa-file"></i> Cancel/Release Ticket</a>
			 -->	
			 
			 
			<!--  <a href="#" class="btn btn-danger" data-toggle="modal" data-target="#myModal" data-ng-click="showmodel('Cancel')" data-ng-show=" bookingstatus == 'Confirmed' ">  <i class="fa fa-file"></i> Cancel/Release Ticket</a>
			 -->
			 
			 <img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
				
						<h4>
							  <a href="companyReportList"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
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
                     <h3><span>Flight Details:</span></h3>

								<table class="table table-bordered">
									  <thead>
									<tr>
										  <th>PNR</th>
											<th>Airline</th>
											<th>Status</th>
											<th>Currency</th>
											<th>Booked</th>
										  	<th>Agency</th>
											<th>DEP</th>
											<th>ARR</th>
										</tr>
									</thead>  
									<tbody>
 										<tr> 
											 <td><s:property value="CurrentReportdata.pnr"/></td>
 												<td><s:property value="CurrentReportdata.airline" /></td>
												<td><s:property value="CurrentReportdata.status" /></td>
												<td><s:property value="CurrentReportdata.curCode" /></td>
 												<td><s:property value="CurrentReportdata.bookingDate"/></td>
 												<td><s:property value="CurrentReportdata.agencyUsername" /></td>
												<td><s:property value="CurrentReportdata.departureDate" /></td>
												<td><s:property value="CurrentReportdata.arrivalDate" /></td>

											 </tr>
										</tbody>
                          			</table>
                      			</div>
                      			<%--  <div class="flight-admin-details">
                     			<h3><span>Passenger Details:</span></h3>

								<table class="table table-bordered"
									>
									  <thead>
									<tr>
										   <th>FirstName</th>  
										  <th>LastName</th>
											<th>Gender</th>
											 <th>BaseFare</th>  
										  <th>Tax</th>
											 <th>Total</th>  
											 
											<!-- <th>Agent Comm</th> -->
												<th>Agency</th>
										</tr>
									</thead>  
									<tbody>
											  <s:iterator value="passList">  
										    <tr> 
										  <td><s:property value="name"/></td> 
										    <td><s:property value="surname"/></td>  
										      <td><s:property value="gender"/></td> 
										      <td><s:property value="price"/></td> 
										    <td><s:property value="tax"/></td>  
										       <td><s:property value="total"/></td> 
										         
										      <td><s:property value="agentCom"/></td>  
										       <td><s:property value="CurrentReportdata.agencyUsername"/></td>  
										       <td></td> 
										   </tr>
										 </s:iterator>  
										</tbody>
                          			</table>
                      			</div> --%>
                       <%-- 	<s:if test="CurrentReportdata.companysGstOn=='lintas'">	
                      	<div class="flight-admin-details">
                       <h3><span>GST Details :</span></h3>
                      <table class="table table-bordered"
									>
									  <thead>
									<tr>
										   <th>MarkAmount</th>
										  	<th>GstonMarkup</th>
										  		<th>GstonFlights</th>
										  		<th></th>
										  		
										  	 <th></th>
											<th></th>
										    <th></th>
											 <th></th>
										</tr>
									</thead>  
									<tbody>
									   <tr> 
									    <td><s:property value="CurrentReportdata.totAgentSComm"/></td> 
									       <td><s:property value="CurrentReportdata.GstOnMarkup" /> </td>
								 	   <td> <s:property value="CurrentReportdata.GstOnFlights" /> </td>
									   <td> </td> 
										    <td> </td> 
										    <td> </td>  
										      <td> </td> 
										       <td> </td>
											 </tr>
										</tbody>
                          			</table>
                      			</div>
                      			</s:if>	 --%>
                      			  <div class="flight-admin-details">
                      			 <h3><span>SSR Details:</span></h3>

								<table class="table table-bordered" >
									  <thead>
									<tr>
										   <th>S.No</th>
										    <th>PaxName</th>
										   <th>PNR</th>
										   <th>OrderId</th>
										    <th>TripType</th>
										  <th>MealType</th>
										   <th>MealName</th>
										   <th>MealPrice</th>
										   <th>BaggageType</th>
										    <th>BaggagePrice</th>
										     <th>BaggageWeight</th>
										      <th>SeatType</th>
										        <th>ReturnMealType</th>
										   <th>ReturnMealName</th>
										   <th>ReturnMealPrice</th>
										   <th>ReturnBaggageType</th>
										    <th>ReturnBaggagePrice</th>
										     <th>ReturnBaggageWeight</th>
										      <th>ReturnSeatType</th>
										      
										</tr>
									</thead>  
									<tbody>
									<s:if test="SSRList!=null">
									<s:iterator value="SSRList" status="rowCount">
										<tr> 
											 <td><s:property value="%{#rowCount.count}"/></td>
											<td><s:property value="paxName"/></td>
 												<td><s:property value="flightOrderRow.pnr" /></td>
 												<td><s:property value="flightOrderRow.orderId" /></td>
 												 <td><s:property value="flightOrderRow.tripType" /></td>
												<td>
												  <s:property value="mealType" />
												 
												</td>
												
													<td><s:property value="mealname" /></td>
														<td><s:property value="mealPrice" /></td>
															<td><s:property value="baggageType" /></td>
																<td><s:property value="baggagePrice" /></td>
																	<td><s:property value="baggageweight" /></td>
																		<td><s:property value="seatType" /></td>
																		<td><s:property value="returnmealType" /></td>
													<td><s:property value="returnmealname" /></td>
														<td><s:property value="returnmealPrice" /></td>
															<td><s:property value="returnbaggageType" /></td>
																<td><s:property value="returnbaggagePrice"/></td>
																	<td><s:property value="returnbaggageweight" /></td>
																		<td><s:property value="returnseatType" /></td>
																		 	 	
												 
											 </tr>
									
									</s:iterator>
									</s:if>
									<s:else>
									<tr> 
								 <td colspan="4">  No Data.</td>
									</tr>
									
									
									</s:else>
									 </tbody>
                          			</table>
                      			</div>
                      			 
                <div class="flight-admin-details">
                     <h3><span>Payment Details :</span></h3>
                      <table class="table table-bordered"
									>
									  <thead>
									<tr>
										   <th>TxId</th>
										  	<th>Amount</th>
										  	 <th>Currency</th>
											<th>ResMessage</th>
										    <th>PaymentStatus</th>
											 <th>PaymentMethod</th>
											 	<th> </th>
										  		<th> </th>
										</tr>
									</thead>  
									<tbody>
									<s:if test="endUserTxHistory.size>0">
											 <s:iterator value="endUserTxHistory">  
										    <tr> 
										  <td><s:property value="transactionId" /></td> 
										    <td><s:property value="amount" /></td> 
										      <td><s:property value="currency"/></td> 
										    <td><s:property value="response_message" /></td>  
										      <td><s:property value="payment_status" /></td> 
										       <td><s:property value="payment_method" /></td>
										       <td></td> 
										     <td> </td> 
											 </tr>
										 </s:iterator> 
										 </s:if>
										 <s:else>
										   <tr>
									  	<td>
									    No available data.
									  </td>
									  </tr>
									  </s:else>
											 
										</tbody>
                          			</table>
                      			</div>
               <div class="flight-admin-details">
                     <h3><span>Wallet Details:</span></h3>

								<table class="table table-bordered"
									>
									  <thead>
									<tr>
										   <th>OrderId</th>
										  	<th>Action</th>
											<th>Created</th>
											<th>Currency</th>
											<th>OpeningBal</th>
											 <th>ClosingBal</th>
										  	<th>Amount</th>
										  	<th>Agent</th>
										</tr>
									</thead>  
									<tbody>
									<s:if test="txHistory.walletAmountTranferHistory.size>0">
									
									 <s:iterator value="txHistory.getWalletAmountTranferHistory()">  
										    <tr> 
										  <td><s:property value="actionId"/></td> 
										    <td><s:property value="action"/></td> 
										    <td><s:property value="convertDate"/></td>  
										      <td><s:property value="currency"/></td> 
										       <td><s:property value="openingBalance"/></td> 
										         <td><s:property value="closingBalance"/></td> 
										    <td><s:property value="amount"/></td>  
										   <td>  <s:property value="CurrentReportdata.agencyUsername"/></td>
										       
										       <td></td> 
										    </tr>
										 </s:iterator>  
										 </s:if>
										 <s:else>
										 
										 <tr>
									  	<td>
									    No available data.
									  </td>
									  </tr>
										  </s:else>
											 
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
	<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content"  >
    <input type="hidden" id="modelcontent" name="title" value="{{modelcontent}}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">{{modeltitle}}</h4>
      </div>
      <div class="modal-body">
      {{modelcontent}}
       
      </div>
      <div class="modal-footer">
       
     
      <form action="">
      <input type="hidden" id="userid" value="<s:property value="%{#session.Company.companyid}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.Company.Username}"/>">
				<input type="hidden" id="appkey" value="<s:property value="CurrentReportdata.appkey"/>">
				<input type="hidden" id="transkey" value="<s:property value="CurrentReportdata.transactionKey"/>">
				<input type="hidden" id="pricekey" value="<s:property value="CurrentReportdata.pricekey"/>">
				<input type="hidden" id="status" value="<s:property value="CurrentReportdata.status"/>">
				<input type="hidden" id="orderid" value="<s:property value="CurrentReportdata.orderId"/>">
				<input type="hidden" id="pnr" value="<s:property value="CurrentReportdata.pnr"/>">
				<input type="hidden" id="password" value="<s:property value="%{#session.Company.Password}"/>">
				<input type="hidden" id="paymode" value="<s:property value="CurrentReportdata.paidBy"/>">
				
      
      
       
       <button type="button" class="btn btn-default"   data-ng-click="showmodel('Proceed')"  data-dismiss="modal"   data-ng-show="bookingstatus == 'Hold' ">Release Ticket</button>
       <button type="button" class="btn btn-default"   data-ng-click="showmodel('Proceed')"  data-dismiss="modal"   data-ng-show="bookingstatus == 'Confirmed'">Cancel Ticket</button>
       <!-- <button type="button" class="btn btn-default"   data-ng-click="showmodel('Proceed')"  data-dismiss="modal">Cancel/Release Ticket</button> -->
      <!--  <a href="#" class="btn btn-danger" data-toggle="modal" data-target="#myModal" data-ng-click="showmodel('Release')" data-ng-show="bookingstatus == 'Hold' || bookingstatus == 'Failed' ">  <i class="fa fa-file"></i> Cancel/Release Ticket</a>
       -->
         <div>
         <span id="loading">
         <img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
			</span>	
       {{processcomments}}
        {{bookingComments}}
       </div>
       </form> </div>
    </div>

  </div>
</div>
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
		
		
</body>
</html>