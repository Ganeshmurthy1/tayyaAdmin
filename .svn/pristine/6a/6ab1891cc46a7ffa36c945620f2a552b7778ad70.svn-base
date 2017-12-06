<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
	<!-- <link
	href="css/jquery-uii.css"
	rel="stylesheet" type="text/css" /> -->
<script
	src="js/customer_invoice.js"></script>
	
<script type="text/javascript">
	$(function() {
		var reportType = document.getElementById('reportTypeHidden').value;

		document.getElementById('reportType').value = reportType;

	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>

</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
	  <h1>Hotel Customer Invoice List</h1>
		  
		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
					 			  <form action="hotelCustomerInvoiceList" method="post" id="filterform">
					 			  <input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">
					 			  
				  		<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-8">
									<a class="btn btn-primary" role="button" data-toggle="collapse"
										href="#filters" aria-expanded="false" aria-controls="filters">
										<i class="fa fa-filter" aria-hidden="true"></i> Filters
									</a>
								</div>
								<div class="col-sm-4 pull-right items">
									<div class="form-group clearfix">

										<div class="col-sm-6">
											<select class="form-control" name="flightInvoicePage.maxItems"
												id="maxItems" required onchange="this.form.submit()">
												<c:forEach var="maxItems"
												items="${flightInvoicePage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${flightInvoicePage.maxItems != null && flightInvoicePage.maxItems == maxItems}">
													 <c:choose>
													 <c:when
														test="${flightInvoicePage.maxItems == -1}">
													 <option value="${maxItems}" selected="selected">ALL</option>
													</c:when>
													 
													<c:otherwise>
														<option value="${maxItems}" selected="selected">${maxItems}</option>
													</c:otherwise>
													</c:choose>
													</c:when>
													 
													<c:otherwise>
													 <c:choose>
													 <c:when
														test="${maxItems == -1}">
														<option value="${maxItems}">ALL</option>
													</c:when>
													 
													<c:otherwise>
														<option value="${maxItems}">${maxItems}</option>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
												</c:choose>
											</c:forEach>		
												 
											</select>
										</div>
										<label class="col-sm-5 control-label text-left">Items
											Per Page </label>

									</div>
								</div>


							</div>

							<!-- filter box started -->

							<div class="collapse filter-box" id="filters">
								<div class="well">
									<div class="clearfix">


										<!-- Filter of main info -->
										<div class=" filter-text col-sm-12 clearfix">
											<div class="row">
											
															<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Report
																Type</label>
															<div class="col-sm-12">

																<input type="hidden" id="reportTypeHidden"
																	value="<s:property value="flightInvoicePage.flightInvoiceFilter.reportType"/>">
																<select class="form-control"
																	name="flightInvoicePage.flightInvoiceFilter.reportType"
																	id="reportType" required>
																	<option value="1">My Reports</option>
																	<s:if
																		test="%{#session.Company.companyRole.isDistributor()}">
																		<option value="4">My Agency(s)</option>
																	</s:if>
																	<s:if
																		test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
																		<option value="0">ALL</option>
																		<%-- <option value="3">My
																			<s:text name="global.Wholesaler"></s:text>(s)
																		</option> --%>
																		<option value="4">My Agency(s)</option>
																		<option value="6">All
																			<s:text name="global.Wholesaler"></s:text>(s)
																		</option>
																		<option value="8">All <s:text name="global.Corporate"></s:text>(s)
																		<option value="7">All Agency(s)</option>
																	</s:if>
																</select>
															</div>
														</div>
													</div>
											
											 
											
 												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Order Id</label>
														<div class="col-sm-12">
															<input type="text" autocomplete="off"
																class="form-control" id="orderId"
																placeholder="type orderId"
																name="flightInvoicePage.flightInvoiceFilter.confirmNo"
																 value="<s:property value="flightInvoicePage.flightInvoiceFilter.confirmNo"/>">
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Invoice Number</label>
														<div class="col-sm-12">
															<input type="text" autocomplete="off"
																class="form-control" id="invoiceNo"
																placeholder="type invoice no"
																name="flightInvoicePage.flightInvoiceFilter.invoiceNo"
																value="<s:property value="flightInvoicePage.flightInvoiceFilter.invoiceNo"/>">
																  
														</div>
													</div>
												</div>
												<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Company
																Name</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="on"
																	class="form-control" id="companyName"
																	placeholder="type company Name"
																	name="flightInvoicePage.flightInvoiceFilter.companyName"
																	value="<s:property value="flightInvoicePage.flightInvoiceFilter.companyName"/>">
															</div>
														</div>
													</div>

													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Employee
																Name</label>
															<div class="col-sm-12">
																<input type="text" class="form-control" id="agentName"
																	autocomplete="on" placeholder="type emp user id"
																	name="flightInvoicePage.flightInvoiceFilter.userName"
																	value="<s:property value="flightInvoicePage.flightInvoiceFilter.userName"/>">
															</div>
														</div>
													</div>
												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Customer Email</label>
														<div class="col-sm-12">
															<input type="text" autocomplete="off"
																class="form-control" id="email"
																placeholder="type email"
																name="flightInvoicePage.flightInvoiceFilter.email"
																value="<s:property value="flightInvoicePage.flightInvoiceFilter.email"/>" >
															 
														</div>
													</div>
												</div>
 													<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Book Status</label>
														<div class="col-sm-12">
															<select class="form-control"
																name="flightInvoicePage.flightInvoiceFilter.bookingStatus"
																id="bookingStatus" required>

																<option value="ALL">ALL</option>
																<c:forEach var="statusItem"
																	items="${flightInvoicePage.flightInvoiceFilter.bookingStatusQueue}">
																	<c:choose>
																		<c:when
																			test="${flightInvoicePage.flightInvoiceFilter.bookingStatus != null && flightInvoicePage.flightInvoiceFilter.bookingStatus == statusItem}">
																			<option value="${statusItem}" selected="selected">${statusItem}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${statusItem}">${statusItem}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>

												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Payment
															Status</label>
														<div class="col-sm-12">
															<select class="form-control"
																name="flightInvoicePage.flightInvoiceFilter.paymentStatus"
																id="paymentStatus" required>

																<option value="ALL">ALL</option>
																<c:forEach var="paymentStatusItem"
																	items="${flightInvoicePage.flightInvoiceFilter.paymentStatusQueue}">
																	<c:choose>
																		<c:when
																			test="${flightInvoicePage.flightInvoiceFilter.paymentStatus != null && flightInvoicePage.flightInvoiceFilter.paymentStatus == paymentStatusItem}">
																			<option value="${paymentStatusItem}"
																				selected="selected">${paymentStatusItem}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${paymentStatusItem}">${paymentStatusItem}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>


										<!-- Filter of additional info -->

										<div class="date col-sm-12 clearfix">
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Booking
															Date</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="twodt1"
																placeholder="From Date(dd-mm-yyyy)"
																name="flightInvoicePage.flightInvoiceFilter.dateFilterBooking.dtStart"
																value='<s:property value="flightInvoicePage.flightInvoiceFilter.dateFilterBooking.dtStart"/>'>

														</div>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="twodt2"
																placeholder="To Date(dd-mm-yyyy)"
																name="flightInvoicePage.flightInvoiceFilter.dateFilterBooking.dtEnd"
																value='<s:property value="flightInvoicePage.flightInvoiceFilter.dateFilterBooking.dtEnd"/>'>
														</div>
													</div>
												</div>

											 </div>
										</div>
										<%-- <div class="slider col-sm-12 clearfix">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Total Price
															 </label>
														<div class="col-sm-12">
															<div id="slider-1"></div>

															<div class="col-sm-6">
																<label class="col-sm-12 control-label">Min</label>
																<div class="col-sm-12">
																	<input type="hidden" id="minPrice"
																		name="flightInvoicePage.flightInvoiceFilter.amountRangeBooking.amountMin"
																		value='<s:property value="flightInvoicePage.flightInvoiceFilter.amountRangeBooking.amountMin"/>'
																		class="form-control"> <input type="hidden"
																		id="minPriceDefault"
																		value='<s:property value="flightInvoicePage.flightInvoiceFilter.amountRangeBooking.amountMinDefault"/>'
																		class="form-control"> <input type="text"
																		id="price1" class="form-control">
																</div>
															</div>
															<div class="col-sm-6 text-right">
																<label class="col-sm-12 control-label">Max</label>
																<div class="col-sm-12">
																	<input type="hidden" id="maxPrice"
																		name="flightInvoicePage.flightInvoiceFilter.amountRangeBooking.amountMax"
																		value='<s:property value="flightInvoicePage.flightInvoiceFilter.amountRangeBooking.amountMax"/>'
																		class="form-control"> <input type="hidden"
																		id="maxPriceDefault"
																		value='<s:property value="flightInvoicePage.flightInvoiceFilter.amountRangeBooking.amountMaxDefault"/>'
																		class="form-control"> <input type="text"
																		id="pric1" class="form-control">
																</div>
															</div>
														</div>

													</div>
												</div>
											</div>
										</div> --%>

										<div class="col-sm-12">

											<div class="col-sm-6 clearfix cc-all">
												<a href=" " id="reset" class="text-right"><i
													class="fa fa-close"></i> Clear All</a>
											</div>
											<div class="text-right filtr-btn col-sm-6 clearfix">
												<button type="submit" class="btn btn-primary"
													name="flightInvoicePage.currentPageIndex"
													value="${flightInvoicePage.currentPageIndex}">Submit</button>
											</div>
										</div>

									</div>
								</div>
							</div>

							<!-- filter box ending -->
						</div>
							<div class="table-responsive dash-table">

							<!-- testing -->

							<div class="box clearfix">
								<!-- <div class="box-body"> -->

								<table id="example" class="table table-striped table-bordered" >
									<thead>
										<tr>
										<th>SNo</th>
										 <th>Action</th>
											<th>OrderId</th>
												<th>InvoiceNumber</th>
											<th>InvoiceDate</th>
											<th>Agency</th>
											<th>InvoiceName</th>
											 <th>FinalPrice</th>
											  <th>Status</th>
											 
 								</tr>
									</thead>
									<tbody>
									 

											<s:iterator value="flightInvoicePage.hotelItems" status="rowCount">

												<tr>
										 		<td><s:property value="%{((flightInvoicePage.currentPageIndex - 1)*flightInvoicePage.maxItems)+#rowCount.count}" /></td>
											 	<td>
														<p data-placement="top" title="Generate Invoice">

															<a
																href="generateHotelCustomerInvoice?id=<s:property value="id"/>&orderReference=<s:property value="orderReference"/>&companyId=<s:property value="companyId"/>"
																class="btn btn-success btn-xs" data-toggle="modal">
																 Invoice
															</a>
														</p>

													</td>
											 	
											 	
											 	<td><s:property value="orderReference" /></td>
											 	<td><s:property value="invoiceNo" /></td>
											 	
													<td><s:property value="createdDate" /></td>
													<td><s:property value="createdBy" /> </td>

													<td><s:property value="orderCustomer.firstName" /> <s:property
															value="orderCustomer.LastName" /></td>
													
													<td><s:property value="finalPrice"/> <s:property value="baseCurrency" /></td>
													  <td><s:property value="statusAction"/></td>
													 
													
												</tr>

											</s:iterator>
										 
										</tbody>
										</table>
											<table id="pagtable"  >
										 
				 						<tr id="tr">
				 					 <span>Showing <s:property value="%{((flightInvoicePage.currentPageIndex - 1)*flightInvoicePage.maxItems)+1}" /> to <s:property value="%{((flightInvoicePage.currentPageIndex*flightInvoicePage.maxItems) <= flightInvoicePage.availableItems)?(flightInvoicePage.currentPageIndex*flightInvoicePage.maxItems):flightInvoicePage.availableItems}" /> of <s:property value="%{flightInvoicePage.availableItems}" /> items</span>
										 </tr>
											<tr id="tr">
			 
			 	<c:if test="${flightInvoicePage.currentPageIndex>1}">
			 		<td id="td"><button type="submit" name="flightInvoicePage.currentPageIndex"  value="1" class="btn btn-primary">First</button></td>			 
			 		<td id="td"><button type="submit" name="flightInvoicePage.currentPageIndex"  value="${flightInvoicePage.currentPageIndex - 1}" class="btn btn-primary">Prev</button></td>		
				</c:if>
					 
			 	<c:forEach begin="${(flightInvoicePage.currentPageIndex) > 1? (flightInvoicePage.currentPageIndex) : 1}" end="${ (flightInvoicePage.currentPageIndex + 4) <= flightInvoicePage.availablePages ? (flightInvoicePage.currentPageIndex + 4) :  flightInvoicePage.availablePages }" var="i">						
					<td>
					<button type="submit" name="flightInvoicePage.currentPageIndex"  value="${i}" class="btn btn-primary" >
					<c:choose>
								<c:when test="${flightInvoicePage.currentPageIndex == i}">
									 <u>${i}</u>
								</c:when>

								<c:otherwise>
									${i}								
								</c:otherwise>
					</c:choose>
					</button>
					</td>						
				</c:forEach>
				<c:if test="${(flightInvoicePage.currentPageIndex + 4) < flightInvoicePage.availablePages}">
			 		<td id="td"><button type="submit" name="flightInvoicePage.currentPageIndex"  value="${flightInvoicePage.currentPageIndex + 1}" class="btn btn-primary">Next</button></td>	
			 		<td id="td"><button type="submit" name="flightInvoicePage.currentPageIndex"  value="${flightInvoicePage.availablePages}" class="btn btn-primary">Last</button></td>
				</c:if>
			  	
					</tr> 
				</table>
				 </div>
				 </div>
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
	 $("#reset").click(function() {
			document.getElementById("filterform").reset()
			 	document.getElementById("filterform").submit();
		});
		$(document).ready(function() {
			$("#twodt1").datepicker({
				dateFormat : "dd-mm-yy",
				  changeMonth: true,
					 changeYear: true  
			});
			$("#twodt2").datepicker({
				dateFormat : "dd-mm-yy",
		  changeMonth: true,
			 changeYear: true  
			});
			$('#twodt1').focus(function() {
				$(this).val('');
			});
			$('#twodt2').focus(function() {
				$(this).val('');
			});	
		});
	</script>
	<script type="text/javascript"> /* 'pdf',  */
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						lengthChange : false,
						"searching": false,
						 "paging": false,
						 "info": false,
						"pagingType" : "full_numbers",
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						buttons : [ 'excel']
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
 
	</script>

	<script>
 $(function() {
	
	  	$( "#slider-1" ).slider({
               range:true,
               min: parseInt($('#minPriceDefault').val()),
               max: parseInt($('#maxPriceDefault').val()),
               values: [parseInt($('#minPrice').val()), parseInt($('#maxPrice').val())],
               slide: function( event, ui ) {            	   
                  $( "#price1" ).val("(" + ui.values[0] + ")" );
                  $( "#pric1" ).val("(" + ui.values[1]  + ")" );   
                  $( "#minPrice" ).val(ui.values[0]);
                  $( "#maxPrice" ).val(ui.values[1]);   
 				}
           });
         	$( "#price1").val("("+ $( "#slider-1" ).slider( 'values', 0 ) + ")" );
            $( "#pric1" ).val("(" + $("#slider-1" ).slider( 'values', 1 ) + ")" );
         });

      </script>

</body>
</html>