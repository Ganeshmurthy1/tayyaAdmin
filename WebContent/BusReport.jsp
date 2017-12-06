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
	rel="stylesheet" type="text/css" />
 -->

<!-- <link href="css/jquery-uii.css" rel="stylesheet" type="text/css" /> -->
<!-- <link href="css/Tayyarahadmin-lintas.css" rel="stylesheet"
	type="text/css" /> -->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
<style type="text/css">
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	/* add padding to account for vertical scrollbar */
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}

.items label {
	margin-top: 5px;
}
</style>
<style>
#pagtable {
	border-collapse: collapse;
	width: auto;
	float: right;
}

#th, #td {
	text-align: left;
	padding: 8px;
}
</style>
<script type="text/javascript">
	$(function() {
		var reportType = document.getElementById('reportTypeHidden').value;
		document.getElementById('reportType').value = reportType;
	});
</script>


<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var company_list = [];
						var agents_list = [];
						var user_list = [];

						$.ajax({
							//Action Name
							url : "CompanyListUnderSuperUser",
							dataType : "json",
							data : {
								parent_company_user_id : $("#companyUserId")
										.val(),
								email : $("#email").val()
							},

							success : function(data, textStatus, jqXHR) {

								var items = data.records;
								for (var i = 0; i < data.records.length; i++) {
									//company_list.push(data.records[i].companyname +"("+data.records[i].company_userid+")"+","
									//		+ data.records[i].companyid);
									company_list
											.push(data.records[i].companyname);
								}
								console.log(company_list);
								//response(items);
							},
							error : function(jqXHR, textStatus, errorThrown) {
								console.log(textStatus);
							}
						});

						$("#companyName")
								.autocomplete(
										{

											source : function(request, response) {
												var matcher = new RegExp(
														'^'
																+ $.ui.autocomplete
																		.escapeRegex(request.term),
														"i");
												response($
														.grep(
																company_list,
																function(item) {
																	return matcher
																			.test(item);

																}));
											},
											select : function(event, ui) {
												var label = ui.item.label;
												var company_userid = ui.item.value;
												console.log(company_userid);
												if (company_userid != null) {
													$
															.ajax({
																//Action Name
																url : "UserListUnderCompany",
																dataType : "json",
																data : {
																	company_user_id : company_userid
																},

																success : function(
																		data,
																		textStatus,
																		jqXHR) {

																	user_list = [];
																	console
																			.log("--data---------"
																					+ data);

																	var items = data.user_records;

																	for (var i = 0; i < data.user_records.length; i++) {
																		//user_list.push(data.user_records[i].username + "("+data.user_records[i].company_userid+")"  + ","
																		//		+ data.user_records[i].id);
																		user_list
																				.push(data.user_records[i].username);
																	}
																	console
																			.log(user_list);
																	userlist(user_list);
																},
																error : function(
																		jqXHR,
																		textStatus,
																		errorThrown) {
																	console
																			.log(textStatus);
																}
															});
												}
											}
										});

						$
								.ajax({
									//Action Name
									url : "AgentsListUnderSuperUser",
									dataType : "json",
									data : {
										parent_company_user_id : $(
												"#companyUserId").val(),
										email : $("#email").val()
									},

									success : function(data, textStatus, jqXHR) {
										for (var i = 0; i < data.agentList.length; i++) {
											agents_list
													.push(data.agentList[i].username);
											/* agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")"  + ","
													+ data.agentList[i].id); */
										}
										console.log("------agents_list------"
												+ agents_list);
										user_list = agents_list;
										console.log("------user_list------"
												+ user_list);
										userlist(user_list);
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										console.log(textStatus);
									}
								});

					});

	function userlist(userlist) {
		if (userlist.length > 0) {
			$("#agentName").autocomplete(
					{
						source : function(request, response) {
							var matcher = new RegExp('^'
									+ $.ui.autocomplete
											.escapeRegex(request.term), "i");
							response($.grep(userlist, function(item) {
								return matcher.test(item);

							}));
						}
					});
		}

	}
</script>

<script type="text/javascript">
	function sendFlightVoucherToCustomerEmail(confirmNumber) {

		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendFlightVoucherToCustomerEmail";
		console.log("finalUrl..." + finalUrl);
		$('#h4').show();
		$.ajax({
			method : "POST",
			url : finalUrl,
			data : {
				orderId : confirmNumber
			},
			success : function(data, status) {
				$.each(data, function(index, element) {
					console.log("data-------" + element.status);
					if (element.status == "success") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text(
								"Successfully sent flight voucher to email.");
						$('#success').click(function() {
							$('#success-alert').hide();
							//window.location.assign($(location).attr('href'));
						});
					} else if (element.status == "fail") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text("Failed.Try again.");
						$('#success').click(function() {
							$('#success-alert').hide();

						});
					}

				});
			},
			error : function(xhr, status, error) {
				$('#h4').hide();
				$('#success-alert').show();
				$('#message').text(error);
				$('#success').click(function() {
					$('#success-alert').hide();
				});
				console.log("Error----------" + error);
			}
		});
	}
</script>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Bus Reports</h1>
			<div class="sccuss-full-updated" id="success-alert"
				style="display: none">
				<div class="succfully-updated clearfix">

					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>

					<div id="message" class="col-sm-10"></div>
					<button type="button" id="success" class="btn btn-primary">Ok</button>
				</div>

			</div>
		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<!-- searchCompanyReportsList -->
			<div class="row">
				<!-- <input type="button" id="downloadExcel" value="downloadExcel"> -->
				<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">

				<form action="showBusWeekOrderList" method="post" id="filterform">
					<input type="hidden" name="showType"
						value="<s:property value="showType"/>">
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
										<select class="form-control" name="commonReportPage.maxItems"
											id="maxItems" required onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${commonReportPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${commonReportPage.maxItems != null && commonReportPage.maxItems == maxItems}">
														<c:choose>
															<c:when test="${commonReportPage.maxItems == -1}">
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
										</select>
									</div>
									<label class="col-sm-5 control-label text-left">Items
										Per Page </label>

								</div>
							</div>

						</div>


						<div class="collapse filter-box" id="filters">
							<div class="well">
								<div class="panel-body">



									<!-- Filter of main info -->
									<div class=" filter-text col-sm-12 clearfix">
										<div class="row">

											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Report Type</label>
													<div class="col-sm-12">

														<input type="hidden" id="reportTypeHidden"
															value="<s:property value="commonReportPage.flightReportFilter.reportType"/>">
														<select class="form-control"
															name="commonReportPage.flightReportFilter.reportType"
															id="reportType" required>
															<option value="1">My Reports</option>
															<option value="0">ALL</option>
															<s:if
																test="%{#session.Company.companyRole.isDistributor()}">
																<option value="4">My Agency(s)</option>
															</s:if>
															<s:if
																test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
																<option value="3">My
																	<s:text name="global.Wholesaler"></s:text>(s)
																</option>
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
													<label class="col-sm-12 control-label">PaxName</label>
													<div class="col-sm-12">
														<input type="text" class="form-control"
															value="<s:property value="commonReportPage.flightReportFilter.paxName"/>"
															placeholder="paxName" autocomplete="off" id="paxName"
															name="commonReportPage.flightReportFilter.paxName">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Confirmation
														No</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="companyName" placeholder="Confirmation no"
															name="commonReportPage.flightReportFilter.confirmationNo"
															value="<s:property value="commonReportPage.flightReportFilter.confirmationNo"/>">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Bus Company
														Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="companyName" placeholder="type Bus company Name"
															name="commonReportPage.flightReportFilter.companyName"
															value="<s:property value="commonReportPage.flightReportFilter.companyName"/>">
													</div>
												</div>
											</div>


											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking
														Status</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="commonReportPage.flightReportFilter.bookingStatus"
															id="bookingStatus" required>

															<option value="ALL">ALL</option>
															<c:forEach var="statusItem"
																items="${commonReportPage.flightReportFilter.bookingStatusQueue}">
																<c:choose>
																	<c:when
																		test="${commonReportPage.flightReportFilter.bookingStatus != null && commonReportPage.flightReportFilter.bookingStatus == statusItem}">
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
															name="commonReportPage.flightReportFilter.paymentStatus"
															id="paymentStatus" required>

															<option value="ALL">ALL</option>
															<c:forEach var="paymentStatusItem"
																items="${commonReportPage.flightReportFilter.paymentStatusQueue}">
																<c:choose>
																	<c:when
																		test="${commonReportPage.flightReportFilter.paymentStatus != null && commonReportPage.flightReportFilter.paymentStatus == paymentStatusItem}">
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


									<div class="date col-sm-12 clearfix">
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking Date</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="twodt1"
															placeholder="From Date(dd-mm-yyyy)"
															name="commonReportPage.flightReportFilter.dateFilterBooking.dtStart"
															value='<s:property value="commonReportPage.flightReportFilter.dateFilterBooking.dtStart"/>'>

													</div>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="twodt2"
															placeholder="To Date(dd-mm-yyyy)"
															name="commonReportPage.flightReportFilter.dateFilterBooking.dtEnd"
															value='<s:property value="commonReportPage.flightReportFilter.dateFilterBooking.dtEnd"/>'>
													</div>

												</div>
											</div>

										</div>
									</div>

									<div class="col-sm-12">

										<div class="col-sm-6 clearfix cc-all">
											<button type="reset" class="btn btn-primary text-right">
												<i class="fa fa-close"></i> Clear All
											</button>
										</div>
										<div class="text-right filtr-btn col-sm-6 clearfix">
											<button type="submit" class="btn btn-primary"
												name="commonReportPage.currentPageIndex"
												value="${commonReportPage.currentPageIndex}">Submit</button>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!-- Filter ends here.... -->
					<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table">
							<!-- testing -->
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>S.No</th>
											<th>PaxName</th>
											<th>Confirmation Number</th>
											<th>InvoiceNo</th>
											<th>Booking Date</th>
											<!-- <th>Travel Date</th> -->
											<th>Invoice Date</th>
											<th>Booking Status</th>
											<th>Payment Status</th>
											<th>Created By</th>
											<th>Bus Company Name</th>
											<th>Location</th>
											<th>Pick up</th>
											<th>Drop Off</th>
											<!-- <th>supplier price</th> -->
											<!-- <th>base Price</th>
											<th>other Taxes</th>
											<th>management Fee</th>
											<th>serviceTax</th> -->
											<th>totalAmount</th>
											<th>Remarks</th>
											<th>Action</th>
											<!--  <th>ARR</th> -->
										</tr>
									</thead>
									<tbody>
										<s:iterator value="commonReportPage.items" status="rowCount">
											<tr>
												<td><s:property
														value="%{((commonReportPage.currentPageIndex - 1)*commonReportPage.maxItems)+#rowCount.count}" /></td>
												<%-- <td><s:property value="id" /></td> --%>
											<td><s:property value="firstName" /> <s:property value="lastName" /></td>
												<td><s:property value="confirmationNumber" /></td>
													<td><s:property value="invoiceNo" /></td>
														<td><s:property value="bookingDate" /></td>
														<%-- <td><s:property value="travelDate" /></td> --%>
														<td><s:property value="invoiceDate" /></td>
														<td><s:property value="status" /></td>
												<td><s:property value="paymentStatus" /></td>
												<td><s:property value="createdBy" /></td>
												<td><s:property value="vehicleCompanyName" /></td>
												<td><s:property value="origin" /></td>

												<td><s:property value="pickUp" /></td>
												<td><s:property value="dropOff" /></td>
												<%-- <td><s:property value="supplierPrice" /></td>
												<td><s:property value="basePrice" /></td>
												<td><s:property value="otherTaxes" /></td>
												<td><s:property value="managementFee" /></td>
												<td><s:property value="serviceTax" /></td> --%>
												<td><s:property value="finalPrice" /></td>
												<td><s:property value="description" /></td>

												<%--  <td><p data-placement="top" >
													  <s:if test="status=='Confirmed'">
													  <a
															href="<s:text name="global.resourceApiUrl"/>getpdf/flight?orderids=<s:property value="orderId"/>"
															class="btn btn-success btn-xs"   data-toggle="modal">
															Voucher </a>
													  </s:if>
													  <s:else>
													  NIL													  
													  </s:else>										  
														
													</p></td>
													  
													  <td><p data-placement="top" >
													  <s:if test="status=='Confirmed'">
													  <a href="javascript:history.void(0);"
															class="btn btn-success btn-xs"  onclick="sendFlightVoucherToCustomerEmail('${orderId}');"      data-toggle="modal">
															Send Voucher To Email</a>
													  </s:if>
													  <s:else>
													  NIL													  
													  </s:else>										  
														
													</p></td> --%>

												<td>
													<p data-placement="top" class="details">
														<a
															href="showPassengerDetailsOfBus?idForDetails=<s:property value="id"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Details </a>

													</p>

												</td>

												<s:if
													test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
													<td><s:property value="apiProvider" /></td>
												</s:if>





												<%-- 	<td><s:property value="departureDate" /></td>
														<td><s:property value="arrivalDate" /></td> --%>

											</tr>
										</s:iterator>
									</tbody>
								</table>
								<table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((commonReportPage.currentPageIndex - 1)*commonReportPage.maxItems)+1}" />
											to <s:property
												value="%{((commonReportPage.currentPageIndex*commonReportPage.maxItems) <= commonReportPage.availableItems)?(commonReportPage.currentPageIndex*commonReportPage.maxItems):commonReportPage.availableItems}" />
											of <s:property value="%{commonReportPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">

										<c:if test="${commonReportPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="commonReportPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="commonReportPage.currentPageIndex"
													value="${commonReportPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(commonReportPage.currentPageIndex) > 1? (commonReportPage.currentPageIndex) : 1}"
											end="${ (commonReportPage.currentPageIndex + 4) <= commonReportPage.availablePages ? (commonReportPage.currentPageIndex + 4) :  commonReportPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="commonReportPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${commonReportPage.currentPageIndex == i}">
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
											test="${(commonReportPage.currentPageIndex + 4) < commonReportPage.availablePages}">
											<td id="td"><button type="submit"
													name="commonReportPage.currentPageIndex"
													value="${commonReportPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="commonReportPage.currentPageIndex"
													value="${commonReportPage.availablePages}"
													class="btn btn-primary">Last</button></td>
										</c:if>

									</tr>
								</table>
							</div>
						</div>
					</div>
				</form>
			</div>
		</section>
	</div>


	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
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
			$("#arrivalDate1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			$("#arrivalDate2").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});

			$("#departureDate1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			$("#departureDate2").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});

			$("#reset").click(function() {

				$('#filterform')[0].reset();
			});

		});
	</script>
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

		$(document).ready(function() {
			$('#twodt1').focus(function() {
				$(this).val('');
			});
			$('#twodt2').focus(function() {
				$(this).val('');
			});
			$('#companyName').focus(function() {
				$(this).val('');
			});
			$('#agentName').focus(function() {
				$(this).val('');
			});
			$('#arrivalDate1').focus(function() {
				$(this).val('');
			});
			$('#arrivalDate2').focus(function() {
				$(this).val('');
			});

			$('#departureDate1').focus(function() {
				$(this).val('');
			});
			$('#departureDate2').focus(function() {
				$(this).val('');
			});
			$('#pnr').focus(function() {
				$(this).val('');
			});
			$('#paxName').focus(function() {
				$(this).val('');
			});

		});
	</script>
	<script type="text/javascript">
		$(function() {
			/*  $('#row_dim').hide();  */
			$('#user').change(function() {
				//alert($('#user').val());
				if ($('#user').val() == 'ALL') {
					$('#company_form-group').hide();
				} else if ($('#user').val() == '0') {
					$('#company_form-group').show();

				} else {
					$('#company_form-group').hide();
				}
			});

			$('#companyName').change(function() {
				//alert($('#companyName').val());
				if ($('#companyName').val() == 'ALL') {
					$('#user_form-group').hide();
				} else if ($('#companyName').val() == '0') {
					$('#user_form-group').show();

				} else {
					$('#user_form-group').hide();
				}
			});

		});
	</script>

	<script src="js/jquery-ui.js"></script>


	<script>
		$('spna[data-toggle="tooltip"]').tooltip({
			animated : 'fade',
			placement : 'bottom',
		});
	</script>

</body>
</html>