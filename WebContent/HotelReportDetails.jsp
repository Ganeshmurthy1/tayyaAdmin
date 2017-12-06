<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="%{#session.ReportData.createdBy}" /></title>

<link rel="stylesheet" href="css/alert.css">
<style>
#supDetails, td, th {
	border: 1px solid #ddd;
	text-align: left;
}

#supDetails {
	border-collapse: collapse;
	width: 100%;
}

#supDetails, th, td {
	padding: 5px;
	border-color: red;
}
</style>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Hotel Report</h1>
		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="companyHotelReports"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			<div class="row">
				<div class="col-sm-12 clearfix report-search">

					<!-- 		<form class="form-inline" action="" method="post"> -->

					<div class="table-responsive dash-table">

						<!-- testing -->

						<div class="box clearfix">
							<!-- <div class="box-body"> -->

							<label for="Country"><h4>
									<s:property value="user" /></b>
								</h4></label>

							<div class="modal-body">
								<table class="table  table-bordered">
								<tr>
								<th>Order Id</th>
									<th>Hotel Confirmation Number</th>
									<th>Reference Code </th>
									<th>Booking Status</th>
									 
									<th></th>
									<th></th>


									</tr>
									<tr>
										<th><s:property
													value="CurrentHotelReport.orderRef" /></th>
													<th><s:property
													value="CurrentHotelReport.confirmNo" /></th>
										<th><s:property
													value="CurrentHotelReport.referenceCode" /> </th>
										<th><s:property
													value="CurrentHotelReport.status" /></th>
 
										 
										<th></th>
										<th></th>
									</tr>
									</table>
									<table class="table  table-bordered" >
									<tr>
										<th colspan="6"><h5>
												<b>HOTEL INFORMATION</b>
											</h5></th>


									</tr>
									<tr>
										<th>Hotel_name</th>
										<th>Location</th>
										<th>state</th>
										<th>Country</th>

										<th>Hotel_category</th>
										<th>
											<!-- Hotel_type -->
										</th>

									</tr>
									<tr>
										<td><s:property value="CurrentHotelReport.hotelName" /></td>
										<td><s:property value="CurrentHotelReport.hotel_loc" /></td>
										<td><s:property value="CurrentHotelReport.state" /></td>
										<td><s:property value="CurrentHotelReport.country" /></td>
										<td><s:property value="CurrentHotelReport.hotel_cat" /></td>
										<td>
											<%-- <s:property value="CurrentHotelReport.hotelType" /> --%>
										</td>
									</tr>
									</table>
									<table class="table  table-bordered" >
									<tr>
										<th colspan="6"><h5>
												<b>GUEST INFORMATION</b>
											</h5></th>
									</tr>
									<tr>
										<th>S.No</th>
										<th>FirstName</th>
										<th>LastName</th>
										<th>Email</th>
										<th></th>
										<th></th>

									</tr>
									<s:if test="roomGuestInfos!=null && roomGuestInfos.size>0">
									<s:iterator value="roomGuestInfos" status="serial">
										<tr>

											<td><s:property value="%{#serial.count}" /></td>
											<td><s:property value="firstname" /></td>
											<td><s:property value="lastname" /></td>
											<td><s:property value="email" /></td>
											<td></td>
											<td></td>
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
												<b>ROOM INFORMATION</b>
											</h5></th>
									</tr>
									<tr>
										<th>S.No</th>
										<th>Created_at</th>
										<th>MealType</th>
										<th>Hotel_name</th>
										<th></th>

									</tr>
									<s:iterator value="roomInfo" status="serial">
										<tr>
											<td><s:property value="%{#serial.count}" /></td>
											<td><s:property value="createdDate" /></td>
											<td><s:property value="mealType" /></td>
											<td><s:property value="hotelName" /></td>
											<td></td>

										</tr>
									</s:iterator>
								 </table>
									<table class="table  table-bordered">
									<tr>
										<th colspan="6"><h5>
												<b>PAYMENT INFORMATION</b>
											</h5></th>
									</tr>
									<tr>
										<th>OrderId</th>
										<th>Amount</th>
										<th>ResMessage</th>
										<th>Payment_status</th>
										<th>Payment_Type</th>
										<th></th>

									</tr>
										 
									<s:if test="payTxInfo.size>0">
										<s:iterator value="payTxInfo">
											<tr>
												<td><s:property value="api_transaction_id" /></td>
												<td><s:property value="amount" /><s:property value="currency" /></td>
 												<td><s:property value="response_message" /></td>
												<td><s:property value="payment_status" /></td>
												<td><s:property value="payment_method" /></td>
												<td></td>
												 
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
										  		<th>Agent</th>
										</tr>
									</thead>  
									<tbody>
									<s:if test="txWalletHistory.size>0">
									 <s:iterator value="txWalletHistory" status="serial">  
										    <tr> 
										  <td><s:property value="actionId"/></td> 
										    <td><s:property value="action"/></td> 
										    <td><s:property value="convertDate"/></td>  
										      <td><s:property value="currency"/></td> 
										       <td><s:property value="openingBalance"/></td> 
										         <td><s:property value="closingBalance"/></td> 
										    <td><s:property value="amount"/></td>  
										   <td>  <s:property value="CurrentHotelReport.createdBy"/></td>
										       
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
			<!-- /.row -->
			<!-- Main row -->


		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script>
	$("#supDetails").mouseover(function() {
		$('#success-alert').show();
		$('#success').click(function() {
			  $('#success-alert').hide();
			 });
	});
	</script>
	<script>
		$(document).ready(function() {
			$("#twodpd2").datepicker({
				dateFormat : "yy-mm-dd"
			});
			$("#twodpd1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
		});
	</script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						lengthChange : false,
						   "searching": false,
						   "ordering": false,  
						   "paging": false,
						   "info": false,
						/* "pagingType" : "full_numbers",
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ], */
						/* buttons : [ 'excel', 'pdf', 'print' ] */
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});

		/*  $(function () {
		 	$('#example').DataTable({
		    	 "paging": true,
		         "lengthChange": true,
		        "searching": true,
		        "ordering": true,  
		           "info": true,
		         "autoWidth": false,  
		        "search": {
		      	    "regex": true,
		      	  }, 
		      	 
		      "pagingType": "full_numbers",
		      "lengthMenu": [10, 25, 50, 75, 100, 500 ],
		     
		      
		     });  
		  
		   });   */
	</script>

	<!-- 
 
  $(document).ready(function() 
    	 { 
    		 $("#twodpd1").datepicker({
    			 dateFormat: "yy-mm-dd"  
    			/*  changeMonth: true,
    			 changeYear: true */
    		 }); 
    		 }); -->

</body>
</html>