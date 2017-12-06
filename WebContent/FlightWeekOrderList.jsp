<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<style type="text/css">
.default:HOVER{
text-decoration: underline;
}
.modal-dialog {
    width: 1200px;
    margin: 30px auto;
    border: 1px solid #2AC5F8;
   }

</style>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight <s:property value="%{#session.weekType}"/> Orders</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
 		<!-- Main content -->
	  <section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
			<div class="row">
				<div class="col-sm-12 clearfix report-search">

					<form class="form-inline" action="searchSuperUserOrderList" method="post">
					<%-- 	<div class="form-group">
							<label for="exampleInputAmount">From Date</label>
							<div class="input-group">
								<input type="text" class="form-control input-sm" id="twodpd1"
									placeholder="yyyy-mm-dd" name="yesterDayDate"
									value='<s:property value="yesterDayDate"/>'>
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="exampleInputAmount">End Date</label>
							<div class="input-group">
								<input type="text" class="form-control input-sm" id="twodpd2"
									placeholder="yyyy-mm-dd" name="todayDate"
									value='<s:property value="todayDate"/>'>
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
							</div>
						</div>

		 
							<div class="form-group" id="user_form-group">
							<label for="Country">User</label>
							<div class="input-group">
								<select class="form-control input-sm" name="user" id="user"
									autocomplete="off" required>
										<option value="0">select user</option>
									<option value="ALL">ALL</option>
									<s:iterator value="#session.superUser_UserList">
										<option value="<s:property value="Username"/>"><s:property
												value="Username"></s:property></option>

									</s:iterator>

								 
								</select>
							</div>
						</div>
					 <div class="form-group" id="company_form-group">
							<label for="Country">Company</label>
							<div class="input-group">
								<select class="form-control input-sm" name="companyName" id="companyName"
									autocomplete="off" required>
										<option value="0">select company </option>
									<option value="ALL">ALL</option>
									<s:iterator value="#session.superUserCompanyList">
										<option value="<s:property value="Username"/>"><s:property
												value="Username"></s:property></option>

									</s:iterator>

								 
								</select>
							</div>
						</div>  
					  

						<div class="form-group" >
							<label for="Country">Airline</label>
							<div class="input-group">
								<select class="form-control input-sm" name="airline"
									autocomplete="off" required>
									<option value="ALL" selected="selected">ALL</option>
									<s:iterator value="#session.airlineList">
										<option value="<s:property value="airlinename"/>"><s:property
												value="airlinename"></s:property></option>

									</s:iterator>

								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="Country">Status</label>
							<div class="input-group">
								<select class="form-control input-sm" name="status" id="status"
									autocomplete="off" required>
									<option value="ALL">ALL</option>
									<option value="booked">booked</option>
									<option value="ticketed">ticketed</option>
									<option value="cancelled">cancelled</option>
									<option value="expired">expired</option>
									<option value="reserved">reserved</option>
									<option value="pending">pending</option>

								</select>
							</div>
						</div>
						<div class="form-group rep-buto">
							<button type="submit" class="btn btn-primary">Show</button>
						</div> --%>
						<!-- </form> -->
						<div class="table-responsive dash-table">
 
							<!-- testing -->

							<div class="box clearfix " >
								<!-- <div class="box-body"> -->
  							 
								 <table id="example" class="table table-striped table-bordered" >
									<thead>
										<tr>
										<!-- 	<th>Expand</th> -->
										   <th>S.No</th>  
										  <th>Order_id</th>  
											<th>PNR</th>
												<th>Airline</th>
												<th>Booking</th>
													<th>Status</th>
													<th>BaseFare</th>
													<th>Fee</th>
													
													<th>Total</th>
													<th>Cur</th>
													<th>P_status</th>
											 <!--  <th>Name</th>
											    <th>Surname</th> -->
										   <!--  <th>Sold</th> -->
										    <th>Route</th>
										     <th>Trip_type</th>
										     <th>Agency_UN</th>
										       <th>DEP</th>
										      <th>ARR</th>
											  
										</tr>
									</thead>
									<tbody>
									 
									<s:if test="%{#session.weekType=='week'}">
								<s:iterator value="#session.FlightWeekOrders" status="orderCount">
 											<tr>
											  <td >
											  <s:property value="%{#orderCount.count}" /> 
												 </td>  
												 <td >
											  <s:property value="orderId"/> 
												 </td>
												 <td >
											  <s:property value="pnr" /> 
												 </td>
												 <td><s:property value="airline"/></td>
												 <td><s:property value="bookingDate" /></td>
												 	<td><s:property value="statusAction" /></td>
												 
												 <td><s:property value="price" /></td>
												 	<td><s:property value="processingFees" /></td>
												  <td><s:property value="finalPrice" /></td>
												  <td><s:property value="currencyCode" /></td>
												   <td><s:property value="paymentStatus" /></td>
												  
												  
													 <td><s:property value="origin" />-<s:property value="destination" /></td>
												   <td><s:property value="tripType"/></td>
												  <td><s:property value="createdBy"/></td>
												    <td><s:property value="departureDate"/></td>
												   <td><s:property value="arrivalDate"/></td>
												  
												 	
												 	  
											 

											</tr>

										</s:iterator>
										</s:if>
										<s:elseif test="%{#session.weekType=='month'}">
										<s:iterator value="#session.FlightMonthOrders" status="orderCount">
 											<tr>
											  <td >
											  <s:property value="%{#orderCount.count}" /> 
												 </td>  
												 <td >
											  <s:property value="orderId"/> 
												 </td>
												 <td >
											  <s:property value="pnr" /> 
												 </td>
												 <td><s:property value="airline"/></td>
												 <td><s:property value="bookingDate" /></td>
												 	<td><s:property value="statusAction" /></td>
												 
												 <td><s:property value="price" /></td>
												 	<td><s:property value="processingFees" /></td>
												  <td><s:property value="finalPrice" /></td>
												  <td><s:property value="currencyCode" /></td>
												   <td><s:property value="paymentStatus" /></td>
												  
												  
													 <td><s:property value="origin" />-<s:property value="destination" /></td>
												   <td><s:property value="tripType"/></td>
												  <td><s:property value="createdBy"/></td>
												    <td><s:property value="departureDate"/></td>
												   <td><s:property value="arrivalDate"/></td>
												  
											 </tr>

										</s:iterator>
										
										 </s:elseif>
										<s:elseif test="%{#session.weekType=='today'}">
										 
										<s:iterator value="#session.FlightTodayOrders" status="orderCount">
 											<tr>
											  <td >
											  <s:property value="%{#orderCount.count}" /> 
												 </td>  
												 <td >
											  <s:property value="orderId"/> 
												 </td>
												 <td >
											  <s:property value="pnr" /> 
												 </td>
												 <td><s:property value="airline"/></td>
												 <td><s:property value="bookingDate" /></td>
												 	<td><s:property value="statusAction" /></td>
												 
												 <td><s:property value="price" /></td>
												 	<td><s:property value="processingFees" /></td>
												  <td><s:property value="finalPrice" /></td>
												  <td><s:property value="currencyCode" /></td>
												   <td><s:property value="paymentStatus" /></td>
												  
												  
													 <td><s:property value="origin" />-<s:property value="destination" /></td>
												   <td><s:property value="tripType"/></td>
												  <td><s:property value="createdBy"/></td>
												    <td><s:property value="departureDate"/></td>
												   <td><s:property value="arrivalDate"/></td>
												  
											 </tr>

										</s:iterator>
									 </s:elseif>
										
										 
 								</tbody>
								</table>

							</div>
							<!-- /.box -->

						</div>
						<!-- table-responsive -->
					</form>
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
						  buttons : [ 'excel', 'pdf', 'print' ]  
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