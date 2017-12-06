<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<script
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
</style>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
				<div class="row">
			<div class="col-sm-2 clearfix pull-left">
				<h3>Bus Travel Request(s)</h3>
			</div>
		<div class="col-sm-10 clearfix pull-right " data-placement="top">	
			<div class="row">
			<div class="col-sm-12 clearfix pull-right " data-placement="top">
				<div class="row">
					<div class="col-sm-8 clearfix " data-placement="top">
						<a href="goTrips" class="btn btn-top-link btn-xs">
							All Trips </a>
						<a href="HotelTravelRequestList" class="btn btn-top-link btn-xs">
							Hotel Trips </a> <a href="FlightTravelRequestList"
							class="btn btn-top-link btn-xs"> Flight Trips </a> <a
							href="CarTravelRequestList" class="btn btn-top-link btn-xs">
							Car Trips </a> <a href="BusTravelRequestList"
							class="btn btn-top-link btn-xs"> Bus Trips </a> <a
							href="TrainTravelRequestList" class="btn btn-top-link btn-xs">
							Train Trips </a> <a href="VisaTravelRequestList"
							class="btn btn-top-link btn-xs"> Visa Trips </a> <a
							href="InsuranceTravelRequestList" class="btn btn-top-link btn-xs">
							Insurance Trips </a>
							<a href="MiscellaneousTravelRequestList"
									class="btn btn-top-link btn-xs" >
									 Miscellaneous Trips
								</a>
					</div>
					<div class="col-sm-2 clearfix  " data-placement="top">
						<div class="myDropdown">
							<button class="btn btn-top-link btn-xs dropdown-toggle"
								type="button" data-toggle="dropdown">
								Create Quick Trip <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="goQuickLinkOfflibeHotelBookingPage">Hotel</a></li>
								<li><a href="goQuickLinkOfflibeFlightBookingPage">Flight</a></li>
								<!-- <li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>	
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>	 -->					
							</ul>
						</div>
					</div>
					<div class="col-sm-1 clearfix  " data-placement="top">
						<div class="myDropdown">
							<button class="btn btn-top-link btn-xs dropdown-toggle"
								type="button" data-toggle="dropdown">
								Create Trip <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="goHotelTravelRequest">Hotel</a></li>
								<li><a href="goFlightTravelRequest">Flight</a></li>
								<li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>	
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>							
							</ul>
						</div>
					</div>
				</div>
			</div>	  
			</div>	
			 
		</div>	
		</div>
			</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->


		<div class="row">
			<form action="superUserCompanyList" method="post" id="filterform">
				<div class="table-responsive dash-table">
					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">


						<thead>
							<tr>
								<th>S.No</th>
								<th>Id</th>
								<th>CustomerName</th>
								<th>Trip No</th>
							<th>Company Id</th>
							<th>User Id</th>
							  <th>Action</th>
								</tr>
					</thead>
					<tbody>
						<s:iterator value="busTravelRequestlist" status="rowCount">
							<tr>
								<td><s:property value="%{#rowCount.count}" /></td>	
								<td><s:property value="id" /></td>
								<td><s:property value="title" /> <s:property value=" firstName" /> <s:property value=" lastName" /></td>
								 
								
									
									<td><s:property value="TRNo" /> </td> 
									<td><s:property value="companyId" /> </td> 
								<td><s:property value="userId" /></td>
							  
							  
								 <td>
									<p data-placement="top">
									<div class="dropdown">
									    <button class="btn btn-primary btn-xs dropdown-toggle" id="menu1" type="button" data-toggle="dropdown">Edit
									    <span class="caret"></span></button>
									    <ul class="dropdown-menu " role="menu" aria-labelledby="menu1">
									      <li role="presentation"><a role="menuitem" tabindex="-1" href="editBusTravelRequestQuotation?idtosend=<s:property value="id"/>">Edit Quatation</a></li>
									      <li role="presentation"><a role="menuitem" tabindex="-1" href="editBusOrderRow?idtosend=<s:property value="id"/>">Bus order</a></li>  
									      <li role="presentation"><a role="menuitem" tabindex="-1" href="editBusTravelRequest?idtosend=<s:property value="id"/>">Edit Travel Request</a></li>    
									    </ul>
									    <a href="busOrderQuotationView?id=<s:property value="id"/>"
												class="btn btn-success btn-xs">
											 View
										</a>
								</div>
										
										
									</p>           
							  	</td>
 								</tr>
						</s:iterator>
					</tbody>

				</table>
				
				
								</div>
				
				</form>
			 
			</div>
</section>
		</div>
		
 
 
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">
<script type="text/javascript">
	/* , 'pdf', 'print'  */
	$(document).ready(
			function() {
				var table = $('#example').DataTable({
					"paging" : false,
					"info" : false,
					"searching" : false,
					"ordering" : true,
					"search" : {
						"regex" : true,
					},
					/* lengthChange : true,
					"pagingType" : "full_numbers", */
					/*"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ], */
					buttons : [ 'excel' ]
				});

				table.buttons().container().appendTo(
						'#example_wrapper .col-sm-6:eq(0)');

			});
</script>
  





