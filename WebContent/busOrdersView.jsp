<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

button.button {
	font-family: "Helvetica Neue", Helvetica, /*Tahoma,*/   Arial,
		sans-serif;
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


.sub-menu {
  width: auto;
  padding: 0px 0px;
  position: relative;
  top: 100%;
  left: 0px;
  z-index: 2;
  opacity: 1;
  transition: opacity linear 0.15s;
  box-shadow: 0px 2px 3px rgba(0,0,0,0.2);
  background: #425563;
  text-align: center;
  display: inline-block;
}

.sub-menu li {
  font-size: 15px;
  margin-top: 0px;
}

.sub-menu li a {
  padding: 10px 5px;
  display: block;
  text-decoration: none;
  z-index: 3;
  text-align: left;
}

.sub-menu li a:hover, .sub-menu .current-item a {
  background: #01a982;
  -moz-box-shadow: 0 0 5px 0px #888;
  -webkit-box-shadow: 0 0 5px 0px #888;
  box-shadow: 0 0 5px 0px #888;
  color: #000;
}

nav li:hover .sub-menu {
    z-index: 2;
    opacity: 1;
    min-width: 100px;
}

.sub-menu li a input {
  margin-right: 3px;
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
		<div class="row">
			<div class="col-sm-3 clearfix pull-left">
				<h3>Bus Order View</h3>
			</div>

		</div>
	</section>
	<section class="content">
		<div class="col-sm-12">
		
		
			<div>
			<s:if test="%{#session.Company.companyRole.SuperUser==true}">
			 <s:if test="%{(busOrderRow.statusAction =='Confirmed' || busOrderRow.statusAction =='Cancelled') && busOrderRow.bookingMode == 'Online' && busOrderRow.cancelMode == null}">
			 
			 <a href="#" id="showdiv" class="btn btn-danger" data-toggle="modal" data-target="#myModalnew" data-ng-click="showmodel('BusCancel')"  >  <i class="fa fa-file"></i> Cancel Ticket</a>
			 <img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
			{{processcomments}}   {{bookingComments}}
        
			</s:if>
			 
			
			
			<s:elseif test="%{busOrderRow.statusAction =='Pending'}">
			<a href="#"  class="btn btn-primary" data-toggle="modal" data-target="#myModalnew" data-ng-click="showmodel('BusRelease')" > <i class="fa fa-file"></i>Release Ticket</a> 
			<img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
			
			</s:elseif>
			 
				</s:if>
			
			 
			 <c:if test="${ReportData.cancelationMode != null}">
			 <c:choose>
			 <c:when  test="${busOrderRowCancellation.APIStatusCode == 1 || ReportData.cancelationMode == 'Offline'}">
			 <div class="row">
			 <h4 style="color:green">Your Booking is Cancelled Sucessfully With OrderId :  ${busOrderRow.orderId} .As a CancellationMode Is:  ${ReportData.cancelationMode}</h4>
			</div>
			 
			 </c:when >
			 <c:otherwise>
			 
			  <div class="row">
			 <h4 style="color:red">Your Cancellation For Bus Booking  With OrserId :  ${busOrderRow.orderId} . Is In Process Please Wait.....</h4>
			</div>
			 </c:otherwise>
			 </c:choose>
			 
			 
			 </c:if>
			 
			 
			 
				</div>
		
		
		
			<h4>
				<a href="companyBusOrders"><span class="pull-right"><i
						class="fa fa-angle-left"></i> Back</span></a>
			</h4>
		</div>
		        <input type="hidden" id="appkey" value="<s:property value="busOrderRow.appkey"/>">
				<input type="hidden" id="searchkey" value="<s:property value="busOrderRow.searchkey"/>">
				<input type="hidden" id="transkey" value="<s:property value="busOrderRow.transactionKey"/>">
				<input type="hidden" id="orderid" value="<s:property value="busOrderRow.orderId"/>">
				<input type="hidden" id="userid" value="<s:property value="%{#session.User.id}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.User.Username}"/>">
				<input type="hidden" id="status" value="<s:property value="busOrderRow.statusAction"/>">
				<input type="hidden" id="bookingMode" value="<s:property value="busOrderRow.bookingMode"/>">
				<input type="hidden" id="paymode" value="<s:property value="busOrderRow.paidBy"/>">
				<input type="hidden" id="cansmode" value="<s:property value="busOrderRow.cancelMode"/>">
				<input type="hidden" id="busticketNoList" value="${seatNoList}">

		<div class="row">
			<div class="col-sm-12 clearfix report-search">

				<!-- 		<form class="form-inline" action="" method="post"> -->

				<div class="table-responsive dash-table">

					<!-- testing -->

					<div class="box clearfix">
						<!-- <div class="box-body"> -->

						<label for="Country"> <b><s:property value="user" /></b>
						</label>

						<div class="modal-body">
							<table class="table  table-bordered">
								<tr>
									<th>Order Id</th>
									<th>Confirmation Number</th>
									<th>Operator Pnr</th>
									<th>Booking Status</th>

								</tr>
								<tr>
									<th><s:property value="busOrderRow.orderId" /></th>
									<th><s:property value="busOrderRow.confirmationNumber" /></th>
									<th><s:property value="busOrderRow.operatorPnr" /></th>
									<th><s:property value="busOrderRow.statusAction" /></th>
								</tr>
							</table>
							<table class="table  table-bordered">
								<tr>
									<th colspan="6"><h5>
											<b>TRAVEL INFORMATION</b>
										</h5></th>
								</tr>
								<tr>
									<th>Origin</th>
									<th>Destination</th>
									<th>Pick Up</th>
									<th>Travel Date</th>
									<th>Booked Date</th>
									<th>Travels Name</th>
									<th>Bus Type</th>
									<s:if test="%{#session.Company.companyRole.isSuperuser==true}">
									<th>supplier Name</th>
									</s:if>
								</tr>
								<s:if test="busOrderRow!=null">
									<tr>
										<td><s:property value="busOrderRow.origin" /></td>
										<td><s:property value="busOrderRow.destination" /></td>
										<td><s:property value="busOrderRow.pickUp" /></td>
										<td><s:property value="busOrderRow.travelDate" /></td>
										<td><s:property value="busOrderRow.busBookingDate" /></td>
										<td><s:property value="busOrderRow.busCompanyName" /></td>
										<td><s:property value="busOrderRow.busType" /></td>
										<s:if test="%{#session.Company.companyRole.isSuperuser==true}">
										<td><s:property value="busOrderRow.supplierName" /></td>
										</s:if>
									</tr>
								</s:if>
								<s:else>
									<tr>
										<td>No available data.</td>
									</tr>
								</s:else>
							</table>
							<table class="table  table-bordered">
								<tr>
									<th colspan="6"><h5>
											<b>GUEST INFORMATION</b>
										</h5></th>
								</tr>
								<tr>
									<th>S.No</th>
									<th>Guest Name</th>
									<th>Email</th>
									<th></th>
									<th></th>

								</tr>
								<s:if test="busOrderRow!=null">
									<s:iterator value="busOrderRow.BusOrderCustomerDetails" status="serial">
										<tr>

											<td><s:property value="%{#serial.count}" /></td>
											<td><s:property value="title" />&nbsp;&nbsp;&nbsp;<s:property
													value="firstName" /> &nbsp;&nbsp;&nbsp; <s:property
													value="lastName" /></td>
											<td><s:property value="email" />&nbsp;&nbsp;&nbsp;</td>
										</tr>
									</s:iterator>

								</s:if>
								<s:else>
									<tr>
										<td>No available data.</td>

									</tr>


								</s:else>
							</table>

							<table class="table  table-bordered">
								<tr>
									<th colspan="6"><h5>
											<b>PAYMENT INFORMATION</b>
										</h5></th>
								</tr>
								<tr>
									<th>OrderId</th>
									<th>Booking Currency</th>
									<th>Amount</th>
									<th>Remarks</th>
									<th>Payment_status</th>
									<th></th>
								</tr>
								<s:if test="busOrderRow!=null">
									<s:iterator value="busOrderRow" status="serial">
										<td><s:property value="orderId" /></td>
										<td><s:property value="bookingCurrency" /></td>
										<td><s:property
												value="getText('{0,number,#,##0.00}',{totalAmount})" /></td>
										<td><s:property value="remarks" /></td>
										<td><s:property value="paymentStatus" /></td>										
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td>No available data.</td>
									</tr>
								</s:else>
							</table>
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th colspan="6"><h5>
												<b>WALLET INFORMATION</b>
											</h5></th>
									</tr>
									<tr>
										<th>OrderId</th>
										<th>Action</th>
										<th>Created</th>
										<th>Currency</th>
										<th>OpeningBal</th>
										<th>ClosingBal</th>
										<th>Amount</th>
									</tr>
								</thead>
								<tbody>
									<s:if test="payTxInfo!=null">
										<s:iterator value="payTxInfo" status="serial">
											<tr>
												<td><s:property value="actionId" /></td>
												<td><s:property value="action" /></td>
												<td><s:date name="createdAt" /></td>
												<td><s:property value="currency" /></td>
												<td><s:property
														value="getText('{0,number,#,##0.00}',{openingBalance})" /></td>
												<td><s:property
														value="getText('{0,number,#,##0.00}',{closingBalance})" /></td>
												<td><s:property
														value="getText('{0,number,#,##0.00}',{amount})" /></td>

												<td></td>
											</tr>
										</s:iterator>
									</s:if>
									<s:else>
										<tr>
											<td>No available data.</td>
										</tr>
									</s:else>
								</tbody>
							</table>
							
								<table class="table table-bordered">
									<tr>
										<s:if
											test="%{ReportData.myBusCreditNote.CreditnoteIssued==false && ReportData.OrderUpdated==true}">
											<th><a
												href="generateBusCreditNote?id=<s:property value="ReportData.id"/>"
												id="orderNow1" class="btn btn-primary">Generate Credit
													Note</a> <Strong Style="color: red; font-size: 12px;">Click
													here to generate your credit note</Strong></th>
										</s:if>
										<s:elseif
											test="%{ (creditNoteList!=null && creditNoteList.size >0 && ReportData.CreditNoteIssued==false) && (#session.Company.companyRole.Corporate==true ||#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true)}">
											<Strong Style="color: red; font-size: 15px;">Waiting
												for Admin Response to <s:if
													test="%{ ReportData.creditNoteActiontype=='IssueCreditNote'}">
									 cancel your reservation, 
									</s:if> please contact Admin for urgent quries
											</Strong>
										</s:elseif>
										<s:elseif
											test="%{(creditNoteList!=null && creditNoteList.size >0 && ReportData.CreditNoteIssued==false ) && #session.Company.companyRole.SuperUser==true}">
											<Strong Style="color: red; font-size: 15px;"> You
												received request for cancellation, Kindly process on time </Strong>
										</s:elseif>


										<s:if
											test="%{ReportData.CreditNoteIssued==true && ReportData.myBusCreditNote.CreditnoteIssued==true &&  ReportData.creditNoteActiontype=='IssueCreditNote'}">
											<th><a
												href="generateBusCreditNote?id=<s:property value="ReportData.id"/>"
												id="orderNow1" class="btn btn-primary">View Credit Note</a>
												<Strong Style="color: red; font-size: 12px;">Click
													here to view your credit note</Strong></th>

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
								<form action="insertBusOrderModifiedInfo" method="post">
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
												id="bookingStatus"> <s:if
													test="ReportData.isOrderRequested()">
													<select name="statusAction" id="statusAction"
														autocomplete="off" required>
														<option selected="selected"
															value="<s:property value="ReportData.status"/>"><s:property
																value="ReportData.status" /></option>
													</select>
												</s:if> <s:else>
													<select name="statusAction" id="statusAction"
														autocomplete="off" required>
														<option value="">Select booking status</option>
														<!-- <option value="Failed">Failed</option>
									 <option value="Issued">Issued</option>
										 <option value="Confirmed">Confirmed</option>
											<option value="Pending">Pending</option> -->
														<option selected="selected" value="Cancelled">Cancelled</option>

													</select>
												</s:else> <%-- </s:else> --%> <input type="hidden"
												name="busOrderRowId"
												value="<s:property value="ReportData.id"/>"> <input
												type="hidden" name="befPayStatus"
												value="<s:property value="ReportData.paymentStatus"/>">
												<input type="hidden" name="beforeStatus"
												value="<s:property value="ReportData.status"/>"> <input
												type="hidden" name="userId"
												value="<s:property value="%{#session.User.id}"/>"> <input
												type="hidden" name="companyId"
												value="<s:property value="%{#session.Company.companyid}"/>">
												<input type="hidden" name="gstAmount"
												value="<s:property value="ReportData.gstOnMarkup"/>">
												<input type="hidden" name="totalBookingAmount"
												value="<s:property value="ReportData.finalPrice"/>">
												<input type="hidden" name="updatedBy"
												value="<s:property value="%{#session.User.Username}"/>">
											</td>

											<td><h5>PaymentStatus</h5> <input type="hidden"
												value="<s:property value="ReportData.paymentStatus"/>"
												id="payStatus"> <s:if
													test="ReportData.isOrderRequested()">
													<select name="paymentStatus" id="paymentStatus"
														autocomplete="off" required>
														<option selected="selected"
															value="<s:property value="ReportData.paymentStatus"/>"><s:property
																value="ReportData.paymentStatus" /></option>
													</select>
												</s:if> <s:else>
													<select name="paymentStatus" id="paymentStatus"
														autocomplete="off" required>
														<option selected="selected" value="">Select
															payment status</option>
														<!-- <option value="Failed">Failed</option>
									<option value="Pending">Pending</option>
									<option value="Success">Success</option> -->
														<option selected="selected" value="Refund">Refund</option>
													</select>
												</s:else></td>
											<td><h5>Action Type</h5> <input type="hidden"
												value="<s:property value="ReportData.creditNoteActiontype"/>"
												id="action_type"> <s:if
													test="ReportData.creditNoteActiontype=='UpdateOrder'">
													<select name="actionType" id="actionType"
														autocomplete="off" required>
														<option selected="selected" value="UpdateOrder">UpdateOrder</option>
													</select>
												</s:if> <s:elseif
													test="ReportData.creditNoteActiontype=='IssueCreditNote'">
													<select name="actionType" id="actionType"
														autocomplete="off" required>
														<option selected="selected" value="IssueCreditNote">IssueCreditNote</option>
													</select>
												</s:elseif> <s:else>
													<select name="actionType" id="actionType"
														autocomplete="off" required>
														<option value="">Select Action Type</option>
														<!-- <option value="UpdateOrder">UpdateOrder</option> -->
														<option selected="selected" value="IssueCreditNote">IssueCreditNote</option>
													</select>
												</s:else></td>

											<s:if test="%{#session.Company.companyRole.SuperUser==true}">
												<td><h5>ManagementFees</h5> <input type="text"
													name="managementFees" id="managementFees"
													autocomplete="off" value="${ReportData.managementFee}" readonly>
													<input type="hidden" id="creditNoteActiontype"
													value="<s:property value="ReportData.creditNoteActiontype"/>">
												</td>
											</s:if>
											<s:elseif
												test="%{ReportData.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true)}">
												<td><h5>ManagementFees</h5> <input type="text"
													name="managementFees" id="managementFees"
													autocomplete="off" value="${ReportData.managementFee}" readonly>
												</td>
											</s:elseif>
											<s:elseif
												test="%{#session.Company.companyRole.Corporate==false}">
												<td><h5>ManagementFees</h5> <input type="text"
													name="managementFees" id="managementFees"
													autocomplete="off" value="${ReportData.managementFee}" readonly>
												</td>
											</s:elseif>
 								<%--  <s:elseif test="%{#session.Company.companyRole.Corporate==true}">	
								<td><h5>ManagementFees</h5>
									 <input type="text" name="managementFees" id="managementFees"
											autocomplete="off"   value="${ReportData.managementFee}">
								</td>	 	  
						 	  	</s:elseif> --%>
											<s:if test="%{#session.Company.companyRole.SuperUser==true}">
												<td><h5>Convenience Fees</h5> <input type="text"
													name="convenienceFees" id="convenienceFees"
													autocomplete="off" value="${ReportData.convenienceFees}">
												</td>
											</s:if>
											<s:elseif
												test="%{ReportData.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true ||#session.Company.companyRole.Corporate==true)}">
												<td><h5>Convenience Fees</h5> <input type="text"
													name="convenienceFees" id="convenienceFees"
													autocomplete="off" readonly="readonly"
													style="background: rgb(170, 169, 176)"
													value="${ReportData.convenienceFees}"></td>
											</s:elseif>
											<s:else>
												<td><h5>Convenience Fees</h5> <input type="hidden"
													name="convenienceFees" id="convenienceFees"
													autocomplete="off" readonly="readonly"
													style="background: rgb(170, 169, 176)" value="0"> <small>Convenience
														Fees automatically appears <br>once order is
														cancelled by admin
												</small></td>
											</s:else>



											<%-- <s:if test="%{#session.Company.companyRole.SuperUser==true}">
												<td><h5>Cancellation Fees</h5> <input type="text"
													name="cancellationFees" id="cancellationFees"
													autocomplete="off" value="${ReportData.cancellationFees}">
												</td>
											</s:if> --%>
											
											
											
											
											<s:if test="%{#session.Company.companyRole.SuperUser==true}">
					 <s:if test="%{ReportData.cancelationMode == 'Online' }">
						<td><h5>CancellationFees</h5>
						<input type="text" name="cancellationFees" id="cancellationFees"
									autocomplete="off"   value="${ReportData.cancellationFees}"> 
							   </td>
							   </s:if>
							    <s:else >
							   <td><h5>CancellationFees</h5>
						<input type="text" name="cancellationFees" id="cancellationFees"
									autocomplete="off"   value="${ReportData.cancellationFees}"> 
							   </td>
							   
							   </s:else> 
							   
					</s:if>
					<s:elseif
												test="%{ReportData.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true ||#session.Company.companyRole.Corporate==true)}">
												<td><h5>Cancellation Fees</h5> <input type="text"
													name="cancellationFees" id="cancellationFees"
													autocomplete="off" readonly="readonly"
													style="background: rgb(170, 169, 176)"
													value="${ReportData.cancellationFees}"></td>
											</s:elseif>
											<s:else>
												<td><h5>Cancellation Fees</h5> <input type="hidden"
													name="cancellationFees" id="cancellationFees"
													autocomplete="off" readonly="readonly"
													style="background: rgb(170, 169, 176)" value="0"> <small>Cancellation
														Fees automatically appears <br>once order is
														cancelled by admin
												</small></td>
											</s:else>
										</tr>
										<tr>
											<td colspan="4">
												<h5>EmpComments</h5> <textarea name="employeeComments"
													style="width: 100%"> </textarea>

											</td>
											<td><h5>Action</h5>
												<button type="submit" class="btn btn-primary"
													style="width: 78%;">SUBMIT</button> <%-- <s:if test="%{#session.Company.Companyid==ReportData.companyId}">
    									  	<button type="submit" class="btn btn-primary" style="width: 78%;">SUBMIT</button>	
    					</s:if>
    					<s:else>
    					 		No Request For Cancellation
    					</s:else> --%></td>
										</tr>
										<tr>

										</tr>

									</table>
								</form>
								<s:if
									test="%{#session.Company.companyRole.isSuperUser==true || #session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true}">
									<s:if test="creditNoteList.size()>0">
										<table id="alter_table">

											<tr id="th">
												<th>S.no</th>
												<th>Alter By</th>
												<th>Convenience Fees</th>
												<th>Management Fees</th>
												<th>Cancellation Fees</th>
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
													<td><s:property value="managementFees" /></td>
													<td><s:property value="cancellationFees" /></td>
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
								</s:if>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>
</div>


<div id="myModalnew" class="modal fade" role="dialog">
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
     <%--  <nav>
  			<li>
    <a>Default <span class="arrow">&#9660</span></a>
      <ul class="sub-menu" id="assetNameMenu">
      </ul>
  			</li>
		</nav> --%>
      <!-- <input type="text" class="form-control" data-ng-model='remarks' id="remarks" placeholder="Enter remarks Here" data-ng-minlength="5" data-ng-maxlength="40" data-ng-required="true"/>
       -->             </div>
                  </div>
    			<input type="hidden" id="appkey" value="<s:property value="busOrderRow.appkey"/>">
				<input type="hidden" id="searchkey" value="<s:property value="busOrderRow.searchkey"/>">
				<input type="hidden" id="transkey" value="<s:property value="busOrderRow.transactionKey"/>">
				<input type="hidden" id="orderid" value="<s:property value="busOrderRow.orderId"/>">
				<input type="hidden" id="userid" value="<s:property value="%{#session.User.id}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.User.Username}"/>">
				<input type="hidden" id="status" value="<s:property value="busOrderRow.statusAction"/>">
				<input type="hidden" id="bookingMode" value="<s:property value="busOrderRow.bookingMode"/>">
				<input type="hidden" id="paymode" value="<s:property value="busOrderRow.paidBy"/>">
				<input type="hidden" id="cansmode" value="<s:property value="ReportData.cancelMode"/>">
				<input type="hidden" id="seatNoList" value="${seatNos}">
				
      
       <s:if test="%{(busOrderRow.statusAction =='Confirmed' || busOrderRow.statusAction =='Cancelled')}">
			 
			
       
      <button type="button" class="btn btn-default"   data-ng-click="myForm.$valid && showmodel('Proceed')"  data-dismiss="modal">Cancel Ticket</button>
     		<button type="button" class="btn btn-default"	data-dismiss="modal">Close</button>
       </s:if>
       </form> </div>
    </div>

  </div>
</div>
 	


<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">

var countries = ['United States', 'Canada', 'Argentina', 'Armenia'];
var assetList = $('#assetNameMenu')
$.each(countries, function(i)
{
    var li = $('<li/>')
        .addClass('ui-menu-item')
        .attr('role', 'menuitem')
        .appendTo(assetList);
    
    
  
   var aaa = $('<a>')
        .addClass('ui-all')
        .appendTo(li);
  
  var input = $('<input/>')
        .addClass('ui-all')
        .attr('type', 'checkbox')
        .appendTo(aaa);
         
  
  var aaaa = $('<span>')
        .text(countries[i])
        .appendTo(aaa);

});

</script>

<script type="text/javascript">
	 $(document).ready(function() {
 $('#success').click(function() {
		  window.location.assign(document.referrer); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		  window.location.assign(document.referrer); 
		   $('#error-alert').hide();
			
		});  
	  
	 });		
 </script>
 

