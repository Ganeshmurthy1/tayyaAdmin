<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script> --%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />

<script src="js/company_filter.js">
	
</script>

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
			<div class="col-sm-3 clearfix pull-left">
				<h3>Trip Expenses</h3>
			</div>
		<div class="col-sm-2 clearfix pull-right" data-placement="top">
						<div class="myDropdown">
							<button class="btn btn-top-link btn-xs dropdown-toggle"
								type="button" data-toggle="dropdown">
								Create Expense 	 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="goHotelExpense">Hotel</a></li>
								<li><a href="goFlightExpense">Flight</a></li>
								<li><a href="goCarExpense">Car</a></li>
								<li><a href="goTrainExpense">Train</a></li>
								<li><a href="goBusExpense">Bus</a></li>
								<li><a href="goInsuranceExpense">Insurance</a></li>
								<li><a href="goVisaExpense">Visa</a></li>
								<li><a href="goMealExpense">meal</a></li>			
								<li><a href="goLaborExpense">labour</a></li>	
								<li><a href="goMiscellaneousExpense">Miscellaneous</a></li>	
								
													
							</ul>
						</div>
					</div>
		</div>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
				<div class="table-responsive dash-table">
					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<thead>
							<tr>
								<th>S.No</th>
								<th>Expense Id</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-plane"></i> </span>&nbsp; &nbsp;Flight </th>
								<th><span class="status-bookingdone"><i
										class="fa fa-hotel"></i></span>&nbsp; &nbsp; Hotel</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-car"></i></span>&nbsp; &nbsp; Car</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-train"></i></span>&nbsp; &nbsp; Train</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-bus"></i></span>&nbsp; &nbsp; Bus</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-medkit"></i></span>&nbsp; &nbsp; Insurance</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-cc-visa"></i></span>&nbsp; &nbsp;Visa</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-cutlery"></i></span>&nbsp; &nbsp;Meal</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-users"></i></span>&nbsp; &nbsp;labor</th>
								<th><span class="status-bookingdone"><i
										class="fa fa-file-o"></i></span>&nbsp; &nbsp;miscellaneous</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="tripExepenses" status="rowCount" var="trip" >
							<tr>
									 <td><s:property value="%{#rowCount.count}" /></td> 
									<td><s:property value="%{#trip.id}" /></td> 
									<td><a href="flightExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goFlightExpense?expid=${id}"
													class="status-createnew"> <i
													class="fa fa-plus"></i>
												</a>
									</td>
								 <td><a href="hotelExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goHotelExpense?expid=${id}"
													class="status-createnew" > <i
													class="fa fa-plus"></i>
												</a>
									</td>
									<td><a href="carExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goCarExpense?expid=${id}"
													class="status-createnew" > <i
													class="fa fa-plus"></i>
												</a>
									</td>
									<td><a href="trainExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goTrainExpense?expid=${id}"
													class="status-createnew" > <i
													class="fa fa-plus"></i>
												</a>
									</td>
									<td><a href="busExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goBusExpense?expid=${id}"
													class="status-createnew" > <i
													class="fa fa-plus"></i>
												</a>
									</td>
									<td><a href="insuranceExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goInsuranceExpense?expid=${id}"
													class="status-createnew" > <i
													class="fa fa-plus"></i>
												</a>
									</td>
									<td><a href="visaExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goVisaExpense?expid=${id}"
													class="status-createnew" > <i
													class="fa fa-plus"></i>
												</a>
									</td>
									<td><a href="mealExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goMealExpense?expid=${id}"
													class="status-createnew" > <i
													class="fa fa-plus"></i>
												</a>
									</td>
									<td><a href="labourExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goLaborExpense?expid=${id}"
													class="status-createnew" > <i
													class="fa fa-plus"></i>
												</a>
									</td>
									<td><a href="miscellaneousExpenseView?expid=${id}"> <span
											class="status-view"><i class="fa fa-eye"></i></span>
									</a>
									<a href="goMiscellaneousExpense?expid=${id}"
													class="status-createnew"> <i
													class="fa fa-plus"></i>
												</a>
									</td> 

								</tr>
							 </s:iterator> 
						</tbody>
					</table>

				
				</div>

			<!-- </form> -->

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
	

	function myTripFunction() {
		document.getElementById("mytripDropdown").classList.toggle("show");
	}

	// Close the dropdown if the user clicks outside of it
	window.onclick = function(event) {
		if (!event.target.matches('.tripdropbtn')) {

			var dropdowns = document
					.getElementsByClassName("tripdropdown-content");
			var i;
			for (i = 0; i < dropdowns.length; i++) {
				var openDropdown = dropdowns[i];
				if (openDropdown.classList.contains('show')) {
					openDropdown.classList.remove('show');
				}
			}
		}
	}
</script>


<style>
.btn-green {
	color: #455a64;
	border: 1px solid transparent;
	background-color: #fff;
	border-color: #00a65a;
	width: 70%;
}

.btn-green:hover {
	color: #455a64;
	border: 1px solid transparent;
	background-color: #fff;
	border-color: #146c44;
	width: 70%;
}

.btn-green i {
	color: #0082ca;
	font-size: 13px;
	font-weight: 500;
	text-align: left;
	float: left;
}

.blue-icon {
	color: white;
	border: 1px solid transparent;
	border-radius: 10px;
	background: #0082ca;
}

.btn-green.focus, .btn-green:focus, .btn-green:active {
	color: #1b1919;
	background-color: #949994;
	border-color: #255625;
}

.support #dropdown {
	width: 100%;
	background-color: #fff;
	border: 1px solid #ccc;
	border-radius: 0px;
	padding: 5px 2px 5px 10px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

.support #dropdown span {
	color: #555;
}

.support .dropdownwrap .form-group, .support .dropdownwrap .form-group .input-group
	{
	width: 100% !important;
}

.tripdropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

/*.tripdropbtn:hover, .tripdropbtn:focus {
    background-color: #3e8e41;
}*/
.tripdropdown {
	float: right;
	position: relative;
	display: inline-block;
}

.tripdropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	overflow: auto;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	right: 0;
}

.tripdropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.tripdropdown a:hover {
	background-color: #f1f1f1
}

.show {
	display: block;
}
</style>





