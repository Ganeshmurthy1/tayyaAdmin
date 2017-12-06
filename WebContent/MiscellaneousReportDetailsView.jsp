<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />

<script src="js/company_filter.js">
	
</script>

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

</style>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-sm-3 clearfix pull-left">
				<h3>Miscellaneous Report View</h3>
			</div>
			 
		</div>
	</section>
	<section class="content">
		<div class="col-sm-12">
				<h4>
					<a href="companyMiscellaneousReports"><span class="pull-right"><i
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
									<%-- <s:iterator value="insuranceTravelRequestQuotationlist" status="serial"> --%>
										<tr>

											<td>1<%-- <s:property value="%{#serial.count}" /> --%></td>
											<s:iterator value="miscellaneousOrderRow" >
												<td><s:property value="orderId"  /></td>
											</s:iterator> 
											<%-- <td><s:date name="onwardTravelDate" format="dd-MMM-yyyy"  /></td>
											<td><s:date name="ReturnTravelDate" format="dd-MMM-yyyy"  /></td>
											<td>11</td> --%>
											<%-- <td><s:property value="travelDate" format="dd/mm/yyyy" /></td> --%>
											
											<s:iterator value="miscellaneousTravelRequest" >
										<td><s:property value="bookingDate"  /></td>
										<%-- <td><s:property value="supplierName"  /></td> --%>
										
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
									<s:if test="miscellaneousOrderRow!=null"  >
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
										<th>Payment_status</th>
										
										<th></th>

									</tr>
										 
									<s:if test="miscellaneousOrderRow!=null">
									<s:iterator value="miscellaneousOrderRow" status="serial">
										<td><s:property value="orderId" /></td>
										<td><s:property value="bookingCurrency" /></td>
											<td><s:property value="getText('{0,number,#,##0.00}',{totalAmount})" /></td>
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
                      			  
							</div>
						</div>
						<!-- /.box -->

					</div>
					
				</div>
			</div>
	

	<p id="demo"></p>
</section>

</div>


<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">

