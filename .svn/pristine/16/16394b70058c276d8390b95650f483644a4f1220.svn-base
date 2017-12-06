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
	<section class="content-header">
			<h1>Car Report View</h1>
		</section>
	<section class="content">
		<div class="col-sm-12">
				<h4>
					<a href="companyCarReports"><span class="pull-right"><i
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
									<th>Car Confirmation Number</th>
									<th>Reference Code </th>
									<th>Booking Status</th>
									</tr>
									<tr>
										<th><s:property value="carOrderRow.orderId" /></th>
										<th><s:property value="carOrderRow.confirmationNumber" /></th>
										<th><s:property value="carOrderRow.confirmationNumber" /> </th>
										<th><s:property value="carOrderRow.statusAction" /></th>
 
									</tr>
									</table>
									<table class="table  table-bordered" >
									<tr>
										<th colspan="6"><h5>
												<b>TRAVEL INFORMATION</b>
											</h5></th>


									</tr>
									<tr>
										<th>S.I No</th>
										<th>Travel City</th>
										<td>pickUp</td>
										<td>dropOff</td>
										<td>Travel Date</td>
										<td>Booked Date</td>
										 <s:if test="%{#session.Company.companyRole.isSuperuser==true}">
										<th>supplier Name </th>
										</s:if>
										
										<!-- <th>state</th>
										<th>Country</th>

										<th>Hotel_category</th> -->
										
									</tr>
									<s:if test="carTravelRequestQuotationlist!=null && carTravelRequestQuotationlist.size>0">
									<s:iterator value="carTravelRequestQuotationlist" status="serial">
										<tr>

											<td><s:property value="%{#serial.count}" /></td>
											<td><s:property value="carOrderRow.location" /></td>
											<td><s:property value="pickUp" /></td>
											<td><s:property value="dropOff" /></td>
											<%-- <td><s:property value="travelDate" format="dd/mm/yyyy" /></td> --%>
											
											<s:iterator value="carOrderRow" >
											<td><s:date name="travelDate" format="dd-MM-yyyy" /></td>
										<td><s:property value="carBookingDate"  /></td>
										 <s:if test="%{#session.Company.companyRole.isSuperuser==true}">
										<td><s:property value="supplierName" /></td>
										</s:if>
											
											
									
										</s:iterator>
												<%-- <td><s:property value="lastname" /></td>
											<td><s:property value="email" /></td>
											<td></td>
											<td></td> --%>
										</tr>
									</s:iterator>
									</s:if>
									<s:else>
									<tr>
										<td>No available data.</td>
											 
										</tr>
									
									
									</s:else>
									
									</table>
									
									<table class="table  table-bordered" >
									<tr>
										<th colspan="6"><h5>
												<b> GUEST INFORMATION </b>
											</h5></th>
									</tr>
									<tr>
										<th>S.No</th>
										<th>Guest Name</th>
										 <th>Email</th> 
										<th></th>
										<th></th>

									</tr>
									<s:if test="carOrderRow!=null"  >
									<s:iterator value="carOrderRow" status="serial">
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
										 
									<s:if test="carOrderRow!=null">
									<s:iterator value="carOrderRow" status="serial">
										<td><s:property value="orderId" /></td>
										<td><s:property value="bookingCurrency" /></td>
											<td><s:property value="getText('{0,number,#,##0.00}',{totalAmount})" /></td>
											<td><s:property value="remarks" /></td>
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





