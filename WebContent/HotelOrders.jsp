<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->
<!-- <link
	href="css/jquery-uii.css"
	rel="stylesheet" type="text/css" />	  -->
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>
<script src="js/load_company_emp_names.js"></script>
</head>
<body>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>Hotel Orders</h1>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
					<input type="hidden"
						value="<s:property value="%{#session.Company.company_userid}"/>"
						id="companyUserId"> <input type="hidden"
						value="<s:property value="%{#session.Company.Email}"/>" id="email">
					<input type="hidden"
						value="<s:property value="%{#session.User.company_userid}"/>"
						id="user_id">
					<form action="companyHotelOrders" method="post" id="filterform">
						<input type="hidden" id="companyType"
							value='<s:property value="filterCompanyType"/>'> <input
							type="hidden" id="airline" value='<s:property value="airline"/>'>
						<input type="hidden" id="status"
							value='<s:property value="status"/>'> <input
							type="hidden"
							value="<s:property value="%{#session.Company.company_userid}"/>"
							id="user_companyUserId" name="user_companyUserId">
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
											<select class="form-control" name="hotelReportPage.maxItems"
												id="maxItems" required onchange="this.form.submit()">
												<c:forEach var="maxItems"
													items="${hotelReportPage.pageSizeQueue}">
													<c:choose>
														<c:when
															test="${hotelReportPage.maxItems != null && hotelReportPage.maxItems == maxItems}">
															<c:choose>
																<c:when test="${hotelReportPage.maxItems == -1}">
																	<option value="${maxItems}" selected="selected">ALL</option>
																</c:when>

																<c:otherwise>
																	<option value="${maxItems}" selected="selected">${maxItems}</option>
																</c:otherwise>
															</c:choose>
														</c:when>

														<c:otherwise>
															<c:choose>
																<c:when test="${maxItems == -1}">
																	<option value="${maxItems}">ALL</option>
																</c:when>

																<c:otherwise>
																	<option value="${maxItems}">${maxItems}</option>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<%-- <c:forEach var="maxItems"
													items="${hotelReportPage.pageSizeQueue}">
													<c:choose>
														<c:when
															test="${hotelReportPage.maxItems != null && hotelReportPage.maxItems == maxItems}">
															<option value="${maxItems}" selected="selected">${maxItems}</option>
														</c:when>
														<c:otherwise>
															<option value="${maxItems}">${maxItems}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> --%>
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
														<label class="col-sm-12 control-label">Report Type</label>
														<div class="col-sm-12">

															<input type="hidden" id="reportTypeHidden"
																value="<s:property value="hotelReportPage.hotelReportFilter.reportType"/>">
															<select class="form-control"
																name="hotelReportPage.hotelReportFilter.reportType"
																id="reportType" required>
																<option value="10">ALL</option>
																<s:if
																	test="%{#session.Company.companyRole.isDistributor()}">
																	<option value="4">My Agency(s)</option>
																</s:if>
																<s:if
																	test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false && #session.Company.companyRole.Corporate==false)}">
																	<%-- <option value="3">My <s:text name="global.Wholesaler" ></s:text>(s)</option> --%>
																	<option value="4">My Agency(s)</option>
																	<option value="6">All
																		<s:text name="global.Wholesaler"></s:text>(s)
																	</option>
																	<option value="8">All
																		<s:text name="global.Corporate"></s:text>(s)
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
																name="hotelReportPage.hotelReportFilter.orderId"
																value="<s:property value="hotelReportPage.hotelReportFilter.orderId"/>">
														</div>
													</div>
												</div>

												<s:if
													test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Corporate==false   && #session.Company.companyRole.Agent==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Company
																Name</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="on"
																	class="form-control" id="companyName"
																	placeholder="type company Name"
																	name="hotelReportPage.hotelReportFilter.companyName"
																	value="<s:property value="hotelReportPage.hotelReportFilter.companyName"/>">
																<input type="hidden" id="companyId"
																	name="hotelReportPage.hotelReportFilter.companyId"
																	value="<s:property value="hotelReportPage.hotelReportFilter.companyId"/>">
															</div>
														</div>
													</div>
												</s:if>

												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Employee
															Name</label>
														<div class="col-sm-12">
															<input type="text" class="form-control" id="agentName"
																autocomplete="on" placeholder="type emp user name"
																name="hotelReportPage.hotelReportFilter.userName"
																value="<s:property value="hotelReportPage.hotelReportFilter.userName"/>">
															<input type="hidden" id="userId"
																name="hotelReportPage.hotelReportFilter.userId"
																value="<s:property value="hotelReportPage.hotelReportFilter.userId"/>">
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Hotel Name</label>
														<div class="col-sm-12">
															<input type="text" class="form-control"
																autocomplete="off" placeholder="type hotel name"
																id="hotelName"
																name="hotelReportPage.hotelReportFilter.hotelName"
																value="<s:property value="hotelReportPage.hotelReportFilter.hotelName"/>">
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Email
															Address</label>
														<div class="col-sm-12">
															<input type="text" class="form-control"
																autocomplete="off" placeholder="email" id="emailAddress"
																name="hotelReportPage.hotelReportFilter.email"
																value="<s:property value="hotelReportPage.hotelReportFilter.email"/>">
														</div>
													</div>
												</div>
												<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Invoice No</label>
													<div class="col-sm-12">
														<input type="text" class="form-control"
															value="<s:property value="hotelReportPage.hotelReportFilter.invoiceNo"/>"
															placeholder="type invoiceNo" autocomplete="off"
															name="hotelReportPage.hotelReportFilter.invoiceNo"
															id="invoiceNo">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Confirmation
														No</label>
													<div class="col-sm-12">
														<input type="text" class="form-control"
															value="<s:property value="hotelReportPage.hotelReportFilter.confirmationNo"/>"
															placeholder="type orderId" autocomplete="off"
															name="hotelReportPage.hotelReportFilter.confirmationNo"
															id="confirmationNo">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">First Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control"
															value="<s:property value="hotelReportPage.hotelReportFilter.firstName"/>"
															placeholder="type firstName" autocomplete="off"
															name="hotelReportPage.hotelReportFilter.firstName"
															id="firstName">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Last Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control"
															value="<s:property value="hotelReportPage.hotelReportFilter.lastName"/>"
															placeholder="type lastName" autocomplete="off"
															name="hotelReportPage.hotelReportFilter.lastName"
															id="lastName">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Location</label>
													<div class="col-sm-12">
														<input type="text" class="form-control"
															value="<s:property value="hotelReportPage.hotelReportFilter.location"/>"
															placeholder="type location" autocomplete="off"
															name="hotelReportPage.hotelReportFilter.location"
															id="location">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Mobile</label>
													<div class="col-sm-12">
														<input type="text" class="form-control"
															value="<s:property value="hotelReportPage.hotelReportFilter.mobile"/>"
															placeholder="type mobile" autocomplete="off"
															name="hotelReportPage.hotelReportFilter.mobile"
															id="mobile">
													</div>
												</div>
											</div>
											<s:if test="%{#session.Company.companyRole.isSuperuser==true}">
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Supplier
														Name</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="hotelReportPage.hotelReportFilter.supplierName"
															id="supplierName" required>
															<option value="ALL">ALL</option>
															<s:iterator value="ApiProviders">
																<s:if
																	test="hotelReportPage.hotelReportFilter.supplierName != null && hotelReportPage.hotelReportFilter.supplierName == vendorName">
																	<option value="<s:property value="vendorName"/>"
																		selected="selected"><s:property
																			value="vendorName"></s:property></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="vendorName"/>"><s:property
																			value="vendorName" /></option>
																</s:else>

															</s:iterator>
														</select>

													</div>
												</div>
											</div>
											</s:if>
										</div>
									</div>


									<!-- Filter of additional info -->

									<div class="date col-sm-12 clearfix">
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking From Date</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="twodt1"
															placeholder="From Date(dd-mm-yyyy)" autocomplete="off"
															name="hotelReportPage.hotelReportFilter.dateFilterBooking.dtStart"
															value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterBooking.dtStart"/>'>

													</div>
													<div class="col-sm-6 clearfix">
													<label class="col-sm-12 control-label">Booking To Date</label>
														<input type="text" class="form-control" id="twodt2"
															placeholder="To Date(dd-mm-yyyy)" autocomplete="off"
															name="hotelReportPage.hotelReportFilter.dateFilterBooking.dtEnd"
															value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterBooking.dtEnd"/>'>
													</div>
												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Invoice From
														Date</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="twodt12"
															placeholder="From Date(dd-mm-yyyy)"
															name="hotelReportPage.hotelReportFilter.dateFilterInvoice.dtStart"
															value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterInvoice.dtStart"/>'>

													</div>


													<div class="col-sm-6">
														<input type="text" class="form-control" id="twodt22"
															placeholder="To Date(dd-mm-yyyy)"
															name="hotelReportPage.hotelReportFilter.dateFilterInvoice.dtEnd"
															value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterInvoice.dtEnd"/>'>
													</div>

												</div>
											</div>

										</div>
									</div>
									<div class="col-sm-12">

										<div class="col-sm-6 clearfix cc-all">
											<a href=" " id="reset" class="text-right"><i
												class="fa fa-close"></i> Clear All</a>
										</div>
										<div class="text-right filtr-btn col-sm-6 clearfix">
											<button type="submit" class="btn btn-primary"
												name="hotelReportPage.currentPageIndex"
												value="${hotelReportPage.currentPageIndex}">Submit</button>
										</div>
									</div>

								</div>
							</div>
						</div>

						<!-- filter box ending -->
				</div>
				<div class="table-responsive dash-table">
					<div class="box clearfix">
						<table id="example" class="table table-striped table-bordered">
							<!-- //<thead>
								<tr>
									<th>S.no</th>
									<th>Booking Created Date</th>
									<th>OrderId</th>
									<th>Total</th>
									<th>BookingDate</th>
									<th>CheckIn</th>
									<th>CheckOut</th>
									<th>HotelName</th>
									<th>Agency</th>
									<th>Status</th>
									<th>AgencyComm</th>
									<th>Order</th>
								</tr>
							</thead> -->
							
							<thead>
								<tr>
									<th>S.no</th>
									<th>Booking Created Date</th>
									<th>Passenger Name</th>
									<th>HotelName</th>
									<th>City </th>
									<th>BookingDate</th>
									<th>Confirmation Number</th>
									
									<s:if test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isCorporate()}">
										<th>Invoice Date</th>
										<th>Invoice Value</th>
									</s:if>
									
									
									<th>CheckIn</th>
									<th>CheckOut</th>
									<th>Total</th>
									
									
									<th>Status</th>
									<th>Agency</th>
									<!--<th>AgencyComm</th>-->
									<th>OrderId</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="hotelReportPage.items" status="rowCount">
									<tr>
										<td><s:property
												value="%{((hotelReportPage.currentPageIndex - 1)*hotelReportPage.maxItems)+#rowCount.count}" /></td>
										<td><s:date name="createdAt" format="dd-MM-yyyy"></s:date></td>
										
										<td><s:property value="firstname" /> <s:property value="lastname" /></td>
										<td><s:property value="hotelName" /></td>
										<td><s:property value="hotel_loc" /></td>
										<td><s:property value="bookingDate" /></td>
										<td>
										<c:choose>
														<c:when test="${confirmNo!=null}">
															<s:property value="confirmNo" />
														</c:when>
														<c:otherwise>NA</c:otherwise>
										</c:choose>
										</td>
										
											<s:if test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isCorporate()}">
										<td><s:property value="bookingDate" /></td>
										<td><s:property value="invoiceAmount" /></td>
										</s:if>
										
										
										
										<td><s:property value="checkInDate" /></td>
										<td><s:property value="checkOutDate" /></td>
										<s:if
											test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<td><s:property value="base_price" /> <s:property
													value="curCode" /></td>
										</s:if>
										<s:else>
											<td><s:property value="total" /> <s:property
													value="curCode" /></td>
										</s:else>
										
										
										<td><s:property value="statusAction" /></td>
										<td><s:property value="createdBy" /></td>
										<%-- <td><s:property value="agentCom"/></td>   --%>
										<td><s:property value="orderRef" /></td>
										<td>
											<p data-placement="top" title="edit">
												<a
													href="showGuestDetails?id=<s:property value="id"/>&orderRef=<s:property value="orderRef"/>&companyId=<s:property value="companyId"/>"
													class="btn btn-success btn-xs" data-toggle="modal">edit</a>

												<s:if test="orderRequested==true && CreditNoteIssued==false">
													<i title="Request" class="fa fa-check-circle red">Order
														Request</i>
												</s:if>
												<s:if test="CreditNoteIssued==true && OrderUpdated==true">
													<i title="Request" class="fa fa-check-circle success">CreditNote
														Issued</i>
												</s:if>
											</p>
										</td>
									</tr>
								</s:iterator>
							</tbody>
							
							<%-- <tbody>
								<s:iterator value="hotelReportPage.items" status="rowCount">
									<tr>
										<td><s:property
												value="%{((hotelReportPage.currentPageIndex - 1)*hotelReportPage.maxItems)+#rowCount.count}" /></td>
										<td><s:date name="createdAt" format="dd-MM-yyyy"></s:date></td>
										<td><s:property value="orderRef" /></td>
										<s:if
											test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<td><s:property value="base_price" /> <s:property
													value="curCode" /></td>
										</s:if>
										<s:else>
											<td><s:property value="total" /> <s:property
													value="curCode" /></td>
										</s:else>
										<td><s:property value="bookingDate" /></td>
										<td><s:property value="checkInDate" /></td>
										<td><s:property value="checkOutDate" /></td>
										<td><s:property value="hotelName" /></td>

										<td><s:property value="createdBy" /></td>
										<td><s:property value="statusAction" /></td>
										<td><s:property value="agentCom"/></td>  
										<td>
											<p data-placement="top" title="edit">
												<a
													href="showGuestDetails?id=<s:property value="id"/>&orderRef=<s:property value="orderRef"/>&companyId=<s:property value="companyId"/>"
													class="btn btn-success btn-xs" data-toggle="modal">edit</a>

												<s:if test="orderRequested==true && CreditNoteIssued==false">
													<i title="Request" class="fa fa-check-circle red">Order
														Request</i>
												</s:if>
												<s:if test="CreditNoteIssued==true && OrderUpdated==true">
													<i title="Request" class="fa fa-check-circle success">CreditNote
														Issued</i>
												</s:if>
											</p>
										</td>
									</tr>
								</s:iterator>
							</tbody> --%>
							
						</table>
						<table id="pagtable">
							<tr id="tr">
								<span>Showing <s:property
										value="%{((hotelReportPage.currentPageIndex - 1)*hotelReportPage.maxItems)+1}" />
									to <s:property
										value="%{((hotelReportPage.currentPageIndex*hotelReportPage.maxItems) <= hotelReportPage.availableItems)?(hotelReportPage.currentPageIndex*hotelReportPage.maxItems):hotelReportPage.availableItems}" />
									of <s:property value="%{hotelReportPage.availableItems}" />
									items
								</span>
							</tr>
							<tr id="tr">

								<c:if test="${hotelReportPage.currentPageIndex>1}">
									<td id="td"><button type="submit"
											name="hotelReportPage.currentPageIndex" value="1"
											class="btn btn-primary">First</button></td>
									<td id="td"><button type="submit"
											name="hotelReportPage.currentPageIndex"
											value="${hotelReportPage.currentPageIndex - 1}"
											class="btn btn-primary">Prev</button></td>
								</c:if>

								<c:forEach
									begin="${(hotelReportPage.currentPageIndex) > 1? (hotelReportPage.currentPageIndex) : 1}"
									end="${ (hotelReportPage.currentPageIndex + 4) <= hotelReportPage.availablePages ? (hotelReportPage.currentPageIndex + 4) :  hotelReportPage.availablePages }"
									var="i">
									<td>
										<button type="submit" name="hotelReportPage.currentPageIndex"
											value="${i}" class="btn btn-primary">
											<c:choose>
												<c:when test="${hotelReportPage.currentPageIndex == i}">
													<u>${i}</u>
												</c:when>

												<c:otherwise>
									${i}								
								</c:otherwise>
											</c:choose>
										</button>
									</td>
								</c:forEach>
								<c:if
									test="${(hotelReportPage.currentPageIndex + 4) < hotelReportPage.availablePages}">
									<td id="td"><button type="submit"
											name="hotelReportPage.currentPageIndex"
											value="${hotelReportPage.currentPageIndex + 1}"
											class="btn btn-primary">Next</button></td>
									<td id="td"><button type="submit"
											name="hotelReportPage.currentPageIndex"
											value="${hotelReportPage.availablePages}"
											class="btn btn-primary">Last</button></td>
								</c:if>

							</tr>
						</table>
					</div>
				</div>
				</form>

			</div>
	</div>
	</section>
	</div>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript" src="js/reset-form.js"></script>
	<script type="text/javascript">
		$(function() {
			var reportType = document.getElementById('reportTypeHidden').value;
			if (reportType == 1) {
				reportType = 10;
				document.getElementById('reportType').value = reportType;
			} else
				document.getElementById('reportType').value = reportType;

		});
	</script>
	<script>
		$(document).ready(function() {
			$("#twodt2").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#twodt1").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#twodt12").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true

			});
			$("#twodt22").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
		});
	</script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						"searching" : false,
						lengthChange : false,
						"paging" : false,
						"info" : false,
						"pagingType" : "full_numbers",
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						buttons : [ 'excel' ]
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
	</script>

</body>
</html>