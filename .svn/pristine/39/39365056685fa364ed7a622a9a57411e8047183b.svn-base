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

button.button {
	font-family: "Helvetica Neue", Helvetica, /*Tahoma,*/  Arial, sans-serif;
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
				<h3>Miscellaneous Order View</h3>
			</div>
		</div>
		 
	</section>
	<section class="content">
		<div class="col-sm-12">
				<h4>
					<a href="companyMiscellaneousOrders"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
	 
	<!-- Main content -->
	<div class="row">
				<div class="col-sm-12 clearfix report-search">

					<!-- 		<form class="form-inline" action="" method="post"> -->

					<div class="table-responsive dash-table">

						<!-- testing -->

						<div class="box clearfix">
							<!-- <div class="box-body"> -->

							<label for="Country"> 
									<b><s:property value="user" /></b>
								 </label>

							<div class="modal-body">
								<table class="table  table-bordered">
								<tr>
								<th>Order Id</th>
									<th>Confirmation Number</th>
									<th>Reference Code </th>
									<th>Booking Status</th>
									 
									<th></th>
									<th></th>


									</tr>
									<tr>
										<th><s:property
													value="miscellaneousOrderRow.orderId" /></th>
													<th><s:property
													value="miscellaneousOrderRow.confirmationNumber" /></th>
										<th><s:property
													value="miscellaneousOrderRow.confirmationNumber" /> </th>
										<th><s:property
													value="miscellaneousOrderRow.statusAction" /></th>
 
										 
										<th></th>
										<th></th>
									</tr>
									</table>
									<table class="table  table-bordered"
									>
									<tr>
										<th colspan="6"><h5>
												<b>TRAVEL INFORMATION</b>
											</h5></th>


									</tr>
									<tr>
										<th>S.I No</th>
										<th>Reference  No</th>
										<!-- <td>OnWard Travel Date</td>
										<td>Return Travel Date</td>
										<td>Passport Number </td> -->
										<td>Booked Date</td>
										<!-- <td>Supplier Name</td> -->
										
										<!-- <th>state</th>
										<th>Country</th>

										<th>Hotel_category</th> -->
										
									</tr>
									<s:if test="miscellaneousTravelRequest!=null && miscellaneousTravelRequest.id>0">
									<%-- <s:iterator value="miscellaneousTravelRequest" status="serial"> --%>
										<tr>

											<td>1<%-- <s:property value="%{#serial.count}" /> --%></td>
											<s:iterator value="miscellaneousOrderRow" >
										<td><s:property value="orderId"  /></td>
										</s:iterator>
											<%-- <td><s:date name="onwardTravelDate" format="dd-MMM-yyyy"  /></td>
											<td><s:date name="ReturnTravelDate" format="dd-MMM-yyyy"  /></td>
											<td><s:property value="passportNumber" /></td> --%>
											<%-- <td><s:property value="travelDate" format="dd/mm/yyyy" /></td> --%>
											
											<s:iterator value="miscellaneousTravelRequest" >
										<td><s:property value="bookingDate"  /></td>
										<%-- <td><s:property value="supplierName"  /></td> --%>
										
											
											
									
										</s:iterator>
												<%-- <td><s:property value="lastname" /></td>
											<td><s:property value="email" /></td>
											<td></td>
											<td></td> --%>
										</tr>
									<%-- </s:iterator> --%>
									</s:if>
									<s:else>
									<tr>
										<td>No available data.</td>
											 
										</tr>
									
									
									</s:else>
									
									</table>
									<table class="table  table-bordered"
									>
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
									<s:if test="miscellaneousOrderRow!=null" >
									<s:iterator value="miscellaneousOrderRow" status="serial">
										<tr>
											<td><s:property value="%{#serial.count}" /></td>
											<td><s:property value="orderCustomer.title" />&nbsp;&nbsp;&nbsp;<s:property value="orderCustomer.firstName" />
												&nbsp;&nbsp;&nbsp; <s:property value="orderCustomer.lastName" /></td>
											<td><s:property value="orderCustomer.email" />&nbsp;&nbsp;&nbsp;</td>
										</tr>
										</s:iterator>
									</s:if>
									<s:else>
									<tr>
										<td>No available data.</td>
										</tr>
									</s:else>
									</table>
									
									<table class="table  table-bordered"
									>
									<tr>
										<th colspan="6"><h5>
												<b>PAYMENT INFORMATION</b>
											</h5></th>
									</tr>
									<tr>
										<th>OrderId</th>
										<th>Booking Currency</th>
										<th>Amount</th>
										<!-- <th>Remarks</th> -->
										<th>Payment_status</th>
										
										<th></th>

									</tr>
										 
									<s:if test="miscellaneousOrderRow!=null">
									<s:iterator value="miscellaneousOrderRow" status="serial">
										<td><s:property value="orderId" /></td>
										<td><s:property value="bookingCurrency" /></td>
											<td><s:property value="getText('{0,number,#,##0.00}',{totalAmount})" /></td>
											<%-- <td><s:property value="remarks" /></td> --%>
											<td><s:property value="paymentStatus" /></td>
										<%-- <s:iterator value="payTxInfo">
											<tr>
												<td><s:property value="api_transaction_id" /></td>
												<td><s:property value="amount" /><s:property value="currency" /></td>
 												<td><s:property value="response_message" /></td>
												<td><s:property value="payment_status" /></td>
												<td><s:property value="payment_method" /></td>
												<td></td>
												 
											</tr>--%>
										</s:iterator> 
									</s:if>
									<s:else>
										<tr>
											<td>No available data.</td>
										</tr>
									</s:else>
									 </table>
								<table class="table  table-bordered"
									>
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
										  <td><s:property value="actionId"/></td> 
										    <td><s:property value="action"/></td> 
										    <td>  <s:date name="createdAt"  /></td>  
										      <td><s:property value="currency"/></td> 
										      <td><s:property value="getText('{0,number,#,##0.00}',{openingBalance})" /></td>
										      <td><s:property value="getText('{0,number,#,##0.00}',{closingBalance})" /></td>
										      <td><s:property value="getText('{0,number,#,##0.00}',{amount})"/></td>  
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
                          			
                          			<table class="table table-bordered" >
									<tr>
									<s:if
										test="%{ReportData.myMiscellaneousCreditNote.CreditnoteIssued==false && ReportData.OrderUpdated==true}">
										<th><a
											href="generateMiscellaneousCreditNote?id=<s:property value="ReportData.id"/>"
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
										test="%{ReportData.CreditNoteIssued==true && ReportData.myMiscellaneousCreditNote.CreditnoteIssued==true &&  ReportData.creditNoteActiontype=='IssueCreditNote'}">
										<th><a
											href="generateMiscellaneousCreditNote?id=<s:property value="ReportData.id"/>"
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
			<form action="insertMiscellaneousOrderModifiedInfo" method="post">
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
								<option  value="">Select booking
									status</option>
											<option selected="selected"  value="Cancelled">Cancelled</option>

							</select>
						</s:else>  
							 <%-- </s:else> --%>
							  <input type="hidden" name="MiscellaneousOrderRowId"
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
								<option selected="selected"  value="<s:property value="ReportData.paymentStatus"/>"><s:property
										value="ReportData.paymentStatus" /></option>
									</select>
						  </s:if>  
						  <s:else>
								<select name="paymentStatus" id="paymentStatus"
									autocomplete="off" required>
									<option  value="">Select payment
										status</option>
										<!-- <option value="Failed">Failed</option>
									<option value="Pending">Pending</option>
									<option value="Success">Success</option> -->
									 <option selected="selected"  value="Refund">Refund</option>
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
								 <option selected="selected"  value="UpdateOrder">UpdateOrder</option>
							 </select>
						 	 </s:if>
						 	 <s:elseif test="ReportData.creditNoteActiontype=='IssueCreditNote'">
						 	 <select name="actionType" id="actionType"
								autocomplete="off" required>
									<option selected="selected"  value="IssueCreditNote">IssueCreditNote</option>
							 </select>
						 	   </s:elseif>
						 	<s:else>
						 	<select name="actionType" id="actionType"
								autocomplete="off" required>
								<option  value="">Select Action Type
									 </option>
								<!-- <option value="UpdateOrder">UpdateOrder</option> -->
								<option selected="selected"  selected="selected" value="IssueCreditNote">IssueCreditNote</option>
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
					<s:if test="%{#session.Company.companyRole.SuperUser==true}">
						<td><h5>Convenience Fees</h5>
						<input type="text" name="convenienceFees" id="convenienceFees"
									autocomplete="off"   value="${ReportData.convenienceFees}"> 
							   </td>
					</s:if>
					<s:elseif test="%{ReportData.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true ||#session.Company.companyRole.Corporate==true)}">
						<td><h5>Convenience Fees</h5>
						<input type="text"  name="convenienceFees" id="convenienceFees"
									autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  value="${ReportData.convenienceFees}"> 
					 
							   </td>
					</s:elseif>
					<s:else>
					<td><h5>Convenience Fees</h5>
						<input type="hidden"  name="convenienceFees" id="convenienceFees" autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  
						value="0"> 
					   <small>Convenience Fees automatically appears <br>once order is cancelled by admin</small>
							   </td>
					</s:else>
					
					<s:if test="%{#session.Company.companyRole.SuperUser==true}">
						<td><h5>Cancellation Fees</h5>
						<input type="text" name="cancellationFees" id="cancellationFees"
									autocomplete="off"   value="${ReportData.cancellationFees}"> 
							   </td>
					</s:if>
					<s:elseif test="%{ReportData.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true ||#session.Company.companyRole.Corporate==true)}">
						<td><h5>Cancellation Fees</h5>
						<input type="text"  name="cancellationFees" id="cancellationFees"
									autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  value="${ReportData.cancellationFees}"> 
					 
							   </td>
					</s:elseif>
					<s:else>
					<td><h5>Cancellation Fees</h5>
						<input type="hidden"  name="cancellationFees" id="cancellationFees" autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  
						value="0"> 
					   <small>Cancellation Fees automatically appears <br>once order is cancelled by admin</small>
							   </td>
					</s:else>
					</tr>
					<tr>
						<td colspan="4">
							<h5>EmpComments</h5> <textarea name="employeeComments"
								style="width: 100%"> </textarea>	

						</td>
						<td><h5>Action</h5>
						<button type="submit" class="btn btn-primary" style="width: 78%;">SUBMIT</button>
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
			<s:if test="%{#session.Company.companyRole.isSuperUser==true || #session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true}">
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
						<!-- /.box -->

					</div>
					<!-- table-responsive -->
					<!-- </form> -->
				</div>
			</div>
	

	<p id="demo"></p>
</section>


</div>


<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">


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


