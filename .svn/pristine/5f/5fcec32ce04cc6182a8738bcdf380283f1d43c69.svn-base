<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html >
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
  
<link rel="stylesheet" href="css/alert.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="ReportData.agencyUsername" /></title>
 
<style>
#alter_table {
	border-collapse: collapse;
	width: 100%;
}
/* 
#th, td {
    text-align: left;
    padding: 8px;
} */
#td:nth-child(even) {
	background-color: #f2f2f2
}

#th {
	background-color: rgb(0, 130, 203);
	color: white;
}
element {
    width: 543px;
    height: 59px;
}
.error{
color:red !important;
}
</style>
<%-- <script src="js/angular.js" type="text/javascript"></script>   --%>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body >
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->

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
		<section class="content-header">
			<h1>Passenger Order Details</h1>
			 
		</section>

 	<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
			
			
			
			
			
			
			
			
			
			
			
			<div>
			<s:if test="%{#session.Company.companyRole.SuperUser==true}">
				
			  
			 
			 <s:if test="%{(ReportData.status =='Confirmed' || ReportData.status =='Cancelled') && ReportData.bookingMode == 'Online' && ReportData.cancelationMode == null}">
			 
			 <a href="#" id="showdiv" class="btn btn-danger" data-toggle="modal" data-target="#myModal" data-ng-click="showmodel('Cancel')"  >  <i class="fa fa-file"></i> Cancel Ticket</a>
			 <img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
			{{processcomments}}   {{bookingComments}}
        
			
			
			</s:if>
			 
			
			
			<s:elseif test="%{ReportData.status =='Hold'}">
			<a href="#"  class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-ng-click="showmodel('Release')" > <i class="fa fa-file"></i>Release Ticket</a> 
			<img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
			
			</s:elseif>
			 
				</s:if>
			
			 
			 <c:if test="${ReportData.cancelationMode != null}">
			 <c:choose>
			 <c:when  test="${flightOrderRowCancellation.APIStatusCode == 1 || ReportData.cancelationMode == 'Offline'}">
			 <div class="row">
			 <h4 style="color:green">Your Booking is Cancelled Sucessfully With PNR :  ${ReportData.pnr} .As a CancellationMode Is:  ${ReportData.cancelationMode}</h4>
			</div>
			 
			 </c:when >
			 <c:otherwise>
			 
			  <div class="row">
			 <h4 style="color:red">Your Cancellation For Flight Booking  With PNR :  ${ReportData.pnr} . Is In Process Please Wait.....</h4>
			</div>
			 </c:otherwise>
			 </c:choose>
			 
			 
			 </c:if>
			 
			 
			 
				</div>
						<h4>
							  <a href="companyFlightOrderList"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
					
				
				
				
				<input type="hidden" id="userid" value="<s:property value="%{#session.User.id}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.User.Username}"/>">
				<input type="hidden" id="appkey" value="<s:property value="ReportData.appkey"/>">
				<input type="hidden" id="transkey" value="<s:property value="ReportData.transactionKey"/>">
				<input type="hidden" id="pricekey" value="<s:property value="ReportData.pricekey"/>">
				<input type="hidden" id="status" value="<s:property value="ReportData.status"/>">
				<input type="hidden" id="orderid" value="<s:property value="ReportData.orderId"/>">
				<input type="hidden" id="pnr" value="<s:property value="ReportData.pnr"/>">
				<input type="hidden" id="bookingMode" value="<s:property value="ReportData.bookingMode"/>">
				<input type="hidden" id="password" value="<s:property value="%{#session.Company.Password}"/>">
				<input type="hidden" id="paymode" value="<s:property value="ReportData.paymentMethod"/>">
				<input type="hidden" id="cansmode" value="<s:property value="ReportData.cancelationMode"/>">
				<input type="hidden" id="flightOrderRowCanelationData" value="${flightOrderRowCancellation.statusMessage}">
				
				
				
				
				
			
	
				
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
					
					<!-- 		<form class="form-inline" action="" method="post"> -->

					<div class="table-responsive dash-table">

						<!-- testing -->

						<div class="box clearfix">
							<!-- <div class="box-body"> -->

							<label for="Country"><h4>
									<b><s:property value="user" /></b>
								</h4></label>

							<table  class="table table-bordered">
								<tr>
									<th>CreatedBy</th>
									<th>OrderId</th>
									<th>PNR</th>
									<s:if test="%{#session.Company.companyRole.isSuperuser==true}">
										<th>SupplierName</th>
									</s:if>
									 <th>Status</th>
									<th>Last Ticket Date</th>
									<th>Booking Mode</th>
									
								</tr>


								<tr>
									<td><s:property value="ReportData.createdBy" /></td>
									<td><s:property value="ReportData.orderId" /></td>
									<td><s:property value="ReportData.pnr" /></td>
									<s:if test="%{#session.Company.companyRole.isSuperuser==true}">
									 <td><s:property value="ReportData.apiProvider" /></td>
									</s:if>
									<td><s:property value="ReportData.status" /></td>
									<td><s:property value="ReportData.lastticketdate" /></td>
									<td><s:property value="ReportData.bookingMode" /></td>
									
								</tr>

								<tr>
									<th colspan="5"><h5>
											<b><u>FLIGHT INFORMATION</u></b>
										</h5></th>


								</tr>
								<tr>
									<th>S.No</th>
									<th>Airline</th>
									<th>FlightNo</th>
									<!-- <th>Airline_PNR</th> -->
									<th>Route</th>
									<!--  <th>Via</th> -->
									<th>TakeOff</th>
									<th>Landing</th>
									<th>Class</th>
									<th>TripType</th>
 									</tr>
								<s:iterator value="flightInfo" status="data">
									<tr>

										<td><s:property value="%{#data.count}" /></td>
										<td><s:property value="ReportData.airline" /></td>
										<td><s:property value="flight_number" /></td>
										<%--  <th> <s:property value=" airlinePNR}" />  </th>   --%>
										<td><s:property value="route" /></td>
										<!-- <th>_</th> -->
										<td><s:property value="take_off" /></td>
										<td><s:property value="landing" /></td>
										<td><s:property value="_class" /></td>
										<td><s:property value="tripType" /></td>


									</tr>
								</s:iterator>


								<tr>
									<th colspan="5"><h5>
											<b><u>PASSENGER INFORMATION</u></b>
										</h5></th>
								</tr>

								<tr>
									<!-- 	<th>Passengers</th> -->
									<th>S.No</th>
									<th>Name</th>
									<th>Surname</th>
									<th>Gender</th>
									<th>Birth</th>
									<th>PassportExpDate</th>
									<th>Tax</th>
									<th>BaseFare</th>
									<th>Total</th>
									<th>Currency</th>
								</tr>
								<s:iterator value="passList" status="data">
									<tr>
										<%-- 	<th><s:property value="Passengers" /></th> --%>
										<td><s:property value="%{#data.count}" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="surname" /></td>
										<td><s:property value="gender" /></td>
										<td><s:property value="birth" /></td>
										<%-- <th><s:property value="phone" /></th>
																			  <th><s:property value="mobile" /></th> --%>
										<td><s:property value="PassportExpDate" /></td>
										<td><s:property value="tax" /></td>
										<td><s:property value="price" /></td>
										<td><s:property value="total" /></td>
										 <td><s:property value="ReportData.curCode" /></td>
									</tr>
								</s:iterator>

								
								<tr>
									<th colspan="5"><h5>
											<b><u>RM Config</u></b>
										</h5></th>
								</tr>
								<tr>
									<th>RM Config For</th>
									<th>empCode</th>
									<th>department</th>
									<th>costCenter</th>
									<th>approverName</th>
									<th>location</th>
									<th>bussinessUnit</th>
									<th>projectCode</th>
									<th>reasonForTravel</th>
									<th>billNonBill</th>
									<s:if test="fieldName!=null && fieldName.size()>0">
											<s:if test="fieldName[0]!=null ">
												<td>${fieldName[0]}</td>
											</s:if>
											<s:if test="fieldName[1]!=null ">
												<td>${fieldName[1]}</td>
											</s:if>
											<s:if test="fieldName[2]!=null ">
												<td>${fieldName[2]}</td>
											</s:if>
											<s:if test="fieldName[3]!=null ">
												<td>${fieldName[3]}</td>
											</s:if>
											<s:if test="fieldName[4]!=null ">
												<td>${fieldName[4]}</td>
											</s:if>
									</s:if>
								</tr>
								<s:iterator value="passList" status="data">
									<tr>
									<td><s:property value="name" /></td>
										 <td><s:property value="empCode" /></td>
										<td><s:property value="department" /></td>
										<td><s:property value="costCenter" /></td>
										<td><s:property value="approverName" /></td>
										<td><s:property value="location" /></td>
										<td><s:property value="bussinessUnit" /></td>
										<td><s:property value="projectCode" /></td>
										<td><s:property value="reasonForTravel" /></td>
										 <td><s:property value="billNonBill" /></td>
										 
										 <s:if test="fieldName!=null && fieldName.size()>0">
											 <s:if test="fieldName[0]!=null ">
												<td><s:property value="manualField1" /></td>
											</s:if>
											<s:if test="fieldName[1]!=null ">
												<td><s:property value="manualField2" /></td>
											</s:if>
											<s:if test="fieldName[2]!=null ">
												<td><s:property value="manualField3" /></td>
											</s:if>
											<s:if test="fieldName[3]!=null ">
												<td><s:property value="manualField4" /></td>
											</s:if>
											<s:if test="fieldName[4]!=null ">
												<td><s:property value="manualField5" /></td>
											</s:if>
										</s:if>
										 
									</tr>
								</s:iterator>

									<%-- <s:if test="%{ReportData.companysGstOn=='lintas'}">
										<tr>
											<th><h5>
													<b><u>GST INFORMATION</u></b>
												</h5></th>
										</tr>
										<tr>
											<th>Markup Amount</th>
											<th>GST on Markup</th>
											<th>GST on Flights</th>
										</tr>
										<tr>
											<th><s:property value="ReportData.totAgentSComm" /></th>
											<th>
												6% of <s:property value="ReportData.GSTOnTotMarkup" />=
												<s:property value="ReportData.GstOnMarkup" />
											</th>
											<th><s:property value="ReportData.GstOnFlights" /></th>

										</tr>
									</s:if> --%>
								<tr>
									<th><h5>
											<b><u>PAYMENT INFORMATION</u></b>
										</h5></th>


								</tr>
								<tr>
									<th>TransactionId</th>
									<th>Amount</th>
									<th>PaymentType</th>
									<th>ResMessage</th>
									<th>PaymentStatus</th>
								</tr>
								<s:if test="paymentInfo.size()>0">
									<s:iterator value="paymentInfo" status="data">
										<tr>
											<td><s:property value="txId" /></td>
											<td><s:property value="c_dAmnt" /></td>
											<td><s:property value="paymentMethod" /></td>
											<td><s:property value="resMsg" /></td>
											<td><s:property value="paymentStatus" /></td>
										</tr>

									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td>No data.</td>
									</tr>
								</s:else>
								<tr>
									<th colspan="5"><h4>
											<b>Passenger(s):<s:property value="ReportData.Passengers" /></b>
										</h4></th>

									<th align="left" colspan="5"><h4>
											<b>Amount :<s:property value="ReportData.finalPrice" />
												<s:property value="ReportData.curCode" /></b>
										</h4></th>
								</tr>
</table>
									<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
								<tr>

									<%--  <s:if test="%{ReportData.CreditNoteIssued==false}">
										<th>
											<button type="button" id="orderNow" class="btn btn-primary">Alter
												Order Now</button>
										</th>
									 
									</s:if>ReportData.SuperUserOrdered==true &&
									 --%>
									<s:if
										test="%{ReportData.myCreditNote.CreditnoteIssued==false && ReportData.OrderUpdated==true}">
										<th><a
											href="generateCreditNote?id=<s:property value="ReportData.id"/>"
											id="orderNow1" class="btn btn-primary">Generate Credit Note</a>
											<Strong Style="color:red;font-size:12px;">Click here to generate your credit note</Strong>
										</th>
									</s:if>
									<s:elseif test="%{ (creditNoteList!=null && creditNoteList.size >0 && ReportData.CreditNoteIssued==false) && (#session.Company.companyRole.Corporate==true ||#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true)}">
									<Strong Style="color:red;font-size:15px;">Waiting for Admin Response
									to 
									<s:if test="%{ ReportData.creditNoteActiontype=='IssueCreditNote'}">
									 cancel your reservation, 
									</s:if>
									 please contact Admin for urgent quries</Strong>
									</s:elseif>
									<s:elseif test="%{(creditNoteList!=null && creditNoteList.size >0 && ReportData.CreditNoteIssued==false ) && #session.Company.companyRole.SuperUser==true}">
									<Strong Style="color:red;font-size:15px;">
									 You received request for  cancellation, Kindly process on time 
									 </Strong>
									</s:elseif>
									
									
									<s:if
										test="%{ReportData.CreditNoteIssued==true && ReportData.myCreditNote.CreditnoteIssued==true &&  ReportData.creditNoteActiontype=='IssueCreditNote'}">
										<th><a
											href="generateCreditNote?id=<s:property value="ReportData.id"/>"
											id="orderNow1" class="btn btn-primary">View Credit Note</a>
										<Strong Style="color:red;font-size:12px;">Click here to view your credit note</Strong>	
										</th>

									</s:if>

								</tr>
							</table>

						</div>
					</div>
					<!-- /.box -->
				</div>
				<!-- table-responsive -->
				<!-- </form> -->
			</div>
			<form id="insertOrderModifiedform" action="insertOrderModifiedInfo" method="post">
				<s:if test="hasActionErrors()">
					<div class="success-alert" style="display: none">
						<span class="fa fa-thumbs-o-up fa-1x"></span>
						<s:actionerror />
					</div>
				</s:if>
				<s:if test="hasActionMessages()">
					<div class="success-alert" style="display: none">
						<span class="fa fa-thumbs-o-up fa-1x"></span>
						<s:actionmessage />
					</div>
				</s:if>
				<table id="editOrder1" style="border: 2px solid #2283E1;"
					class="table table-bordered">

					<tr>
						<td colspan="6"><h4
								style="background-color: rgb(0, 130, 203); padding: 5px; color: white; font-weight: bold;">Edit
								or Cancel Order</h4></td>
					</tr>
					<tr>

						<td><h5>BookingStatus</h5> <input type="hidden"
							value="<s:property value="ReportData.status"/>"
							id="bookingStatus"> 
							 <s:if
							test="ReportData.isOrderRequested()">
							<select name="statusAction" id="statusAction" autocomplete="off"
								required>
								<option value="<s:property value="ReportData.status"/>"><s:property
										value="ReportData.status" /></option>
							</select>
				  	</s:if>  
						  <s:else>
							<select name="statusAction" id="statusAction" autocomplete="off"
								required>
								<!-- <option selected="selected" value="">Select booking
									status</option> -->
								<!-- <option value="Failed">Failed</option>
									 <option value="Issued">Issued</option>
										 <option value="Confirmed">Confirmed</option>
											<option value="Pending">Pending</option> -->
											<option value="Cancelled">Cancelled</option>

							</select>
						</s:else>  
							 <%-- </s:else> --%>
							  <input type="hidden" name="flightOrderRowId"
							value="<s:property value="ReportData.id"/>"> <input
							type="hidden" name="befPayStatus"
							value="<s:property value="ReportData.paymentStatus"/>"> <input
							type="hidden" name="beforeStatus"
							value="<s:property value="ReportData.status"/>"> <input
							type="hidden" name="userId"
							value="<s:property value="%{#session.User.id}"/>"> <input
							type="hidden" name="companyId"
							value="<s:property value="%{#session.Company.companyid}"/>">
							<input type="hidden" name="gstAmount"
							value="<s:property value="ReportData.gstOnMarkup"/>"> <input
							type="hidden" name="totalBookingAmount"
							value="<s:property value="ReportData.finalPrice"/>"> <input
							type="hidden" name="updatedBy"
							value="<s:property value="%{#session.User.Username}"/>">
						</td>

						<td><h5>PaymentStatus</h5> 
						<input type="hidden"
							value="<s:property value="ReportData.paymentStatus"/>"
							id="payStatus">
							<s:if test="ReportData.isOrderRequested()"> 
							<select name="paymentStatus" id="paymentStatus"
								autocomplete="off" required>
								<option value="<s:property value="ReportData.paymentStatus"/>"><s:property
										value="ReportData.paymentStatus" /></option>
									</select>
						  </s:if>  
						  <s:else>
								<select name="paymentStatus" id="paymentStatus"
									autocomplete="off" required>
									<!-- <option selected="selected" value="">Select payment
										status</option> -->
										<!-- <option value="Failed">Failed</option>
									<option value="Pending">Pending</option>
									<option value="Success">Success</option> -->
									 <option value="Refund">Refund</option>
								</select>
							 </s:else> 
						</td>
						<td><h5>Action Type</h5> 
						<input type="hidden"
							value="<s:property value="ReportData.creditNoteActiontype"/>"
							id="action_type"> 
							<s:if test="ReportData.creditNoteActiontype=='UpdateOrder'">
						 		<select name="actionType" id="actionType"
								autocomplete="off" required>
								 <option value="UpdateOrder">UpdateOrder</option>
							 </select>
						 	 </s:if>
						 	 <s:elseif test="ReportData.creditNoteActiontype=='IssueCreditNote'">
						 	 <select name="actionType" id="actionType"
								autocomplete="off" required>
									<option value="IssueCreditNote">IssueCreditNote</option>
							 </select>
						 	   </s:elseif>
						 	<s:else>
						 	<select name="actionType" id="actionType"
								autocomplete="off" required>
								<option  value="">Select Action Type
									 </option>
								<!-- <option value="UpdateOrder">UpdateOrder</option> -->
								<option selected="selected" value="IssueCreditNote">IssueCreditNote</option>
							</select>
						 	</s:else>
						 	</td>


					<s:if test="%{#session.Company.companyRole.SuperUser==true}">
						<td><h5>ManagementFees</h5>
							 <input type="text" name="managementFees" id="managementFees"
									autocomplete="off"   value="${ReportData.managementFee}" readonly>
							 <input
							type="hidden" id="creditNoteActiontype"
							value="<s:property value="ReportData.creditNoteActiontype"/>">
						</td>
					</s:if>	
					<s:elseif test="%{ReportData.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true)}">
						<td><h5>ManagementFees</h5>
							 <input type="text" name="managementFees" id="managementFees"
									autocomplete="off"   value="${ReportData.managementFee}" readonly>
						 </td>
					</s:elseif>	
					<s:elseif test="%{#session.Company.companyRole.Corporate==false}">	
								<td><h5>ManagementFees</h5>
									 <input type="text" name="managementFees" id="managementFees"
											autocomplete="off"   value="${ReportData.managementFee}" readonly>
								</td>	 	  
						 	  </s:elseif>
						 	  <%-- <s:elseif test="%{#session.Company.companyRole.Corporate==true}">	
								<td><h5>ManagementFees</h5>
									 <input type="text" name="managementFees" id="managementFees"
											autocomplete="off"   value="${ReportData.managementFee}">
								</td>	 	  
						 	  </s:elseif> --%>
						 	  <s:else>
						 	  <td></td>
						 	  </s:else>
						 	
					
					
					<s:if test="%{#session.Company.companyRole.SuperUser==true}">
						<td><h5>ConvenienceFees</h5>
						<input type="text" name="convenienceFees" id="convenienceFees"
									autocomplete="off"   value="${ReportData.convenienceFees}" required> 
							   </td>
					</s:if>
					<s:elseif test="%{ReportData.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true ||#session.Company.companyRole.Corporate==true)}">
						<td><h5>ConvenienceFees</h5>
						<input type="text"  name="convenienceFees" id="convenienceFees"
									autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  value="${ReportData.convenienceFees}" required> 
					 
							   </td>
					</s:elseif>
					<s:else>
					<td><h5>ConvenienceFees</h5>
						<input type="hidden"  name="convenienceFees" id="cancellationFees" autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  
						value="0"> 
					   <small>Convenience Fees automatically appears <br>once order is cancelled by admin</small>
							   </td>
					</s:else>
					
					<s:if test="%{#session.Company.companyRole.SuperUser==true}">
					 <s:if test="%{ReportData.cancelationMode == 'Online' }">
						<td><h5>CancellationFees</h5>
						<input type="text" name="cancellationFees" id="cancellationFees"
									autocomplete="off"   value="${ReportData.cancellationFees}" required> 
							   </td>
							   </s:if>
							   <s:else >
							   <td><h5>CancellationFees</h5>
						<input type="text" name="cancellationFees" id="cancellationFees"
									autocomplete="off"   value="${ReportData.cancellationFees}" required> 
							   </td>
							   
							   </s:else>
							   
					</s:if>
					<s:elseif test="%{ReportData.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true ||#session.Company.companyRole.Corporate==true)}">
						<td><h5>CancellationFees</h5>
						<input type="text"  name="cancellationFees" id="cancellationFees"
									autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  value="${ReportData.cancellationFees}" required> 
					 
							   </td>
					</s:elseif>
					<s:else>
					<td><h5>CancellationFees</h5>
						<input type="hidden"  name="cancellationFees" id="cancellationFees" autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  
						value="0" > 
					   <small>Cancellation Fees automatically appears <br>once order is cancelled by admin</small>
							   </td>
					</s:else>
					
					</tr>
					<tr>
						<td colspan="4">
							<h5>EmpComments</h5> <textarea name="employeeComments"
								style="width: 100%" required> </textarea>	

						</td>
						<td><h5>Action</h5>
						<!-- <button type="submit" class="btn btn-primary" style="width: 78%;">SUBMIT</button> -->
						<button id="cancelSubmit" type="button" class="btn btn-primary" style="width: 78%;">SUBMIT</button>
						<%-- <s:if test="%{#session.Company.Companyid==ReportData.companyId}">
    									  	<button type="submit" class="btn btn-primary" style="width: 78%;">SUBMIT</button>	
    					</s:if>
    					<s:else>
    					 		No Request For Cancellation
    					</s:else> --%>		
							</td>
					</tr>
					<tr>

					</tr>

				</table>
			</form>
				<s:if test="creditNoteList.size()>0">
			<table id="alter_table">
			
				<tr id="th">
					<th>S.no</th>
					<th>Alter By</th>
					<th>Convenience Fees</th>
					<th>Cancellation Fees</th>
					<th>Management Fees</th>
					<th>Before Status</th>
					<th>Before PayStatus</th>
					<th>After Status</th>
					<th>After PayStatus</th>
					<th>Action Type</th>
					<th>Date</th>

				</tr>
				
				 
					<s:iterator value="creditNoteList" status="serial">
						<tr id="td">
							<td><s:property value="%{#serial.count}" /></td>
							<td><s:property value="alterBy" /></td>
							<td><s:property value="convenienceFees" /></td>
							<td><s:property value="cancellationFees" /></td>
							<td><s:property value="managementFees" /></td>
							<td><s:property value="beforeStatus" /></td>
							<td><s:property value="befPayStatus" /></td>
							<td><s:property value="afterStatus" /></td>
							<td><s:property value="afterPayStatus" /></td>
							<td><s:property value="actionType" /></td>
							<td><s:property value="convertDate" /></td>

						</tr>

					</s:iterator>
				
				<%-- <s:else>
					<tr id="td">
						<td colspan="4">No data</td>
					</tr>
				</s:else> --%>
			 </table>
			 </s:if>
		</section>
		<!-- /.content -->

 	</div>
 	
 	
 	
 	
 	
 	<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content"  >
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">{{modeltitle}}</h4>
      </div>
      <div class="modal-body"><p>{{modelcontent}} </p>
      </div>
      <div class="modal-footer">
       
     
      <form action="" id="myForm" name="myForm">
       <div class="form-group">
      <div class="row" >
      <input type="text" class="form-control" data-ng-model='remarks' id="remarks" placeholder="Enter remarks Here" data-ng-minlength="5" data-ng-maxlength="40" data-ng-required="true"/>
                   </div>
                  </div>
    			 <input type="hidden" id="userid" value="<s:property value="%{#session.User.id}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.User.Username}"/>">
				<input type="hidden" id="appkey" value="<s:property value="ReportData.appkey"/>">
				<input type="hidden" id="transkey" value="<s:property value="ReportData.transactionKey"/>">
				<input type="hidden" id="pricekey" value="<s:property value="ReportData.pricekey"/>">
				<input type="hidden" id="status" value="<s:property value="ReportData.status"/>">
				<input type="hidden" id="orderid" value="<s:property value="ReportData.orderId"/>">
				<input type="hidden" id="pnr" value="<s:property value="ReportData.pnr"/>">
				<input type="hidden" id="bookingMode" value="<s:property value="ReportData.bookingMode"/>">
				<input type="hidden" id="password" value="<s:property value="%{#session.Company.Password}"/>">
				<input type="hidden" id="paymode" value="<s:property value="ReportData.paymentMethod"/>">
				
      
      
       
      <button type="button" class="btn btn-default"   data-ng-click="myForm.$valid && showmodel('Proceed')"  data-dismiss="modal"   data-ng-show="bookingstatus == 'Confirmed' || bookingstatus == 'Cancelled'">Cancel Ticket</button>
     		<button type="button" class="btn btn-default"	data-dismiss="modal">Close</button>
       
       </form> </div>
    </div>

  </div>
</div>
 	
 	
 	
 	
 	
 	
	<!-- /.content-wrapper -->
	<%--  <script src="js/flightconfirm.js?ver=12.0"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	 <script type="text/javascript">
		$(document).ready(function() {
			$("#editOrder").hide();

			$("#orderNow").click(function() {
				$("#editOrder").toggle("slow", function() {

				});
			});
		});
	</script>
	<script>
		$(document).ready(function() {
			cancelationValue = $("#cansmode").val();
			
			$("#twodpd2").datepicker({
				dateFormat : "yy-mm-dd"
			});
			$("#twodpd1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			 $('#success').click(function() {
	 				window.location.assign(document.referrer);
					$('#success-alert').hide();
	 				});
				$('#cancel').click(function() {
					$('#error-alert').hide();

				});
		});
		$(document).ready(function(){
			  
			   $('#cancelSubmit').click(function(){
				   $("#insertOrderModifiedform").valid();
				   if($("#insertOrderModifiedform").valid()){
					   document.getElementById("insertOrderModifiedform").submit();
				   }
			   });
			 		   
			   $("#insertOrderModifiedform").validate({
				   submitHandler: function (form) {  
			            
			            return false;
			        },
			        highlight: function(element, errorClass, validClass) { 
			            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
			            $(element).addClass(errorClass).removeClass(validClass);
			            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			          },
			          success: function(element) {
			            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
			         element.closest('.form-group').removeClass('has-error').addClass('has-success');
			            $(element).remove();
			          }
			   })
		});
	</script>
	
 

</body>
</html>