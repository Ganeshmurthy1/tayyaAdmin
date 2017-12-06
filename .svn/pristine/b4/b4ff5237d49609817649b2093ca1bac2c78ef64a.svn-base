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
<title><s:property value="user" /></title>

</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>My Invoice List</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
				 
					<s:if test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.superuser==1}">
					  <!-- </form> -->
						<div class="table-responsive dash-table">

							<!-- testing -->

							<div class="box clearfix">
								<!-- <div class="box-body"> -->
								 
								<table id="example" class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<thead>
										<tr>
										  <th>Inv_date</th>
											<th>Agency</th>
											<th>Inv_name</th>
											 <th>Desc</th>
											 <th>Official_inv</th>
											 	<th>Tot_amount</th>
											<th>Currency</th>
											<th>Action</th>
											 
												 
										</tr>
									</thead>
									<tbody>
										 
										<s:if test="%{#session.superAgentInvoiceFilterList.size>0}"> 

										<s:iterator value="#session.superAgentInvoiceFilterList">

											<tr>
												<td><s:property value="bookingDate" /></td>
												<td><s:property value="companyId" /></td>

												<td><s:property value="flightCustomer.firstName" /> <s:property value="flightCustomer.LastName" /></td>
												<td><s:property value="information"/></td>
												<td></td>
												<td><s:property value="finalPrice" /></td>
												<td><s:property value="currencyCode" /></td>
 													<td>
													<p data-placement="top" title="Generate Invoice">

														<a
															href="generateCustomerInvoice?id=<s:property value="id"/>&orderId=<s:property value="orderId"/>"
															class="btn btn-success btn-xs" data-toggle="modal">
															<span
											class="fa fa-cloud-upload" ></span>Invoice
														</a>
													</p>

												</td>  
											</tr>

										</s:iterator>
										</s:if>
 								</tbody>
								</table>
								 

							</div>
							<!-- /.box -->

						</div>
						<!-- table-responsive -->
					 
 					 </s:if>

		<s:elseif test="%{#session.User!=null && #session.User.userrole_id.usermode==0 &&  #session.User.userrole_id.superuser==0}">
 
						<!-- </form> -->
						<div class="table-responsive dash-table">

							<!-- testing -->

							<div class="box clearfix">
								<!-- <div class="box-body"> -->

								<label for="Country"><h4>
										<b><s:property value="agencyName" /> reports</b>
									</h4></label>


								<table id="example" class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<thead>
										<tr>
											<th>Expand</th>

											<th>PNR</th>
											<th>Airline</th>
											<th>Status</th>
										  	<th>Curr</th>
										  	<th>Booked</th>
											<!-- <th>Route</th> -->
											<th>Agency</th>
											<th>DEP</th>
											<th>ARR</th>
										</tr>
									</thead>
									<tbody>
										<%-- <s:if test="%{#session.reportFilter_list.size>0}"> --%>

										<s:iterator value="#session.userFilghtReportFilter_list" >

											<tr>
 												<td>
													<p data-placement="top" title="update">

														<a href="showPassengerDetails?id=<s:property value="id"/>"
															class="btn btn-success btn-xs" data-toggle="modal"><span
															class="fa fa-cloud-upload"></span></a>
													</p>

												</td>
												<td><s:property value="pnr" /></td>

												<td><s:property value="airline" /></td>
												<td><s:property value="status" /></td>
												<td><s:property value="curCode" /></td>


												<td><s:property value="bookingDate" /></td>

												<td><s:property value="agencyUsername" /></td>
												<td><s:property value="departureDate" /></td>
												<td><s:property value="arrivalDate" /></td>

											</tr>

										</s:iterator>

									</tbody>
								</table>

							</div>
							<!-- /.box -->

						</div>
						<!-- table-responsive -->
					 

 				</s:elseif>

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
						lengthChange : true,
						"pagingType" : "full_numbers",
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						buttons : [ 'excel','print' ]
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
 
	</script>
	<script type="text/javascript">
$(function() {
   /*  $('#row_dim').hide();  */
    $('#user').change(function(){
    	//alert($('#user').val());
        if($('#user').val()== 'ALL') {
            $('#company_form-group').show(); 
        } else {
            $('#company_form-group').hide(); 
        } 
    });
   
   
    $('#companyName').change(function(){
    	//alert($('#companyName').val());
        if($('#companyName').val() == 'ALL') {
            $('#user_form-group').show(); 
        } else {
            $('#user_form-group').hide(); 
        } 
    });
   
   
   
   
});
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