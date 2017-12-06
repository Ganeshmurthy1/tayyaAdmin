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
						var cityMap = [];
						var userMap = [];

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
									var cityObj = {
										"key" : data.records[i].companyid,
										"value" : data.records[i].companyname
									}
									cityMap.push(cityObj);

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
												$
														.map(
																cityMap,
																function(value,
																		key) {
																	if (value.value == ui.item.label) {
																		console
																				.log("value---"
																						+ value.value
																						+ "--------key----------"
																						+ value.key);
																		$(
																				"#companyId")
																				.val(
																						value.key);
																	}

																});
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
											var userObj = {
												"key" : data.agentList[i].id,
												"value" : data.agentList[i].username
											}
											userMap.push(userObj);

										}
										console.log("------agents_list------"
												+ agents_list);
										user_list = agents_list;
										console.log("------user_list------"
												+ user_list);
										userlist(user_list, userMap);
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										console.log(textStatus);
									}
								});

					});

	function userlist(userlist, userMap) {
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
						},
						select : function(event, ui) {
							$.map(userMap, function(value, key) {
								if (value.value == ui.item.label) {
									console.log("value---" + value.value
											+ "--------key----------"
											+ value.key);
									$("#userId").val(value.key);
								}

							});
						}
					});
		}

	}
</script>


<script type="text/javascript">
	function sendInsuranceInvoiceToCustomerEmail(confirmNumber) {
		var msg = "";
		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendInsuranceInvoiceToCustomerEmail";
		msg = "Successfully sent Insurance Invoice to email.";
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
						$('#message').text(msg);
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
			<h1>Insurance Reports</h1>
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
				<s:if test="%{#session.Company!=null && #session.User!=null}">

					<input type="hidden"
						value="<s:property value="%{#session.Company.company_userid}"/>"
						id="companyUserId">
					<input type="hidden"
						value="<s:property value="%{#session.Company.Email}"/>" id="email">
					<input type="hidden"
						value="<s:property value="%{#session.User.company_userid}"/>"
						id="user_id">

					<form action="companyInsuranceReports" method="post"
						id="filterform">
						<input type="hidden"
							value="<s:property value="%{#session.Company.company_userid}"/>"
							id="companyUserId"> <input type="hidden"
							value="<s:property value="%{#session.Company.Email}"/>"
							id="email"> <input type="hidden"
							value="<s:property value="%{#session.User.company_userid}"/>"
							id="user_id"> <input type="hidden" name="showType"
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
																	test="%{#session.Company.companyRole.isSuperUser() || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
																	<s:if
																		test="%{#session.Company.companyRole.isDistributor()}">
																		<option value="4">My Agency(s)</option>
																	</s:if>
																	<s:if
																		test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
																		<option value="3">My
																			<s:text name="global.Wholesaler"></s:text>(s)
																		</option>
																		<option value="8">All Corporate(s)</option>
																		<option value="4">My Agency(s)</option>
																		<option value="6">All
																			<s:text name="global.Wholesaler"></s:text>(s)
																		</option>
																		<option value="7">All Agency(s)</option>
																	</s:if>
																</s:if>
															</select>
														</div>
													</div>
												</div>


												<%-- <div class="col-sm-2">
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
											</div> --%>

												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Passenger
															Name</label>
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
																id="confirmationNo" placeholder="Confirmation no"
																name="commonReportPage.flightReportFilter.confirmationNo"
																value="<s:property value="commonReportPage.flightReportFilter.confirmationNo"/>">
														</div>
													</div>
												</div>
												<s:if
													test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Company
																Name</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="on"
																	class="form-control" id="companyName"
																	placeholder="type company Name"
																	name="commonReportPage.flightReportFilter.companyName"
																	value="<s:property value="commonReportPage.flightReportFilter.companyName"/>">
																<input type="hidden" id="companyId"
																	name="commonReportPage.flightReportFilter.companyId"
																	value="<s:property value="commonReportPage.flightReportFilter.companyId"/>">

															</div>
														</div>
													</div>
												</s:if>

												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Employee
															Name</label>
														<div class="col-sm-12">
															<input type="text" class="form-control" autocomplete="on"
																id="agentName" placeholder="type emp user id"
																name="commonReportPage.flightReportFilter.userName"
																value="<s:property value="commonReportPage.flightReportFilter.userName"/>">
															<input type="hidden" id="userId"
																name="commonReportPage.flightReportFilter.userId"
																value="<s:property value="commonReportPage.flightReportFilter.userId"/>">

														</div>
													</div>
												</div>

												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Order Id</label>
														<div class="col-sm-12">
															<input type="text" autocomplete="on" class="form-control"
																id="companyName" placeholder="Order Id"
																name="commonReportPage.flightReportFilter.orderId"
																value="<s:property value="commonReportPage.flightReportFilter.orderId"/>">
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Email
															Address</label>
														<div class="col-sm-12">
															<input type="text" class="form-control" id="emailAddress"
																autocomplete="off" placeholder="email"
																name="commonReportPage.flightReportFilter.email"
																value="<s:property value="commonReportPage.flightReportFilter.email"/>">
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
												
												

											
										<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Invoice No</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="commonReportPage.flightReportFilter.invoiceNo"/>"
															placeholder="type invoiceNo" autocomplete="off"    name="commonReportPage.flightReportFilter.invoiceNo"  id="invoiceNo">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">First Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="commonReportPage.flightReportFilter.firstName"/>"
															placeholder="type firstName" autocomplete="off"    name="commonReportPage.flightReportFilter.firstName"  id="firstName">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Last Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="commonReportPage.flightReportFilter.lastName"/>"
															placeholder="type lastName" autocomplete="off"    name="commonReportPage.flightReportFilter.lastName"  id="lastName">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">location</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="commonReportPage.flightReportFilter.location"/>"
															placeholder="type location" autocomplete="off"    name="commonReportPage.flightReportFilter.location"  id="location">
													</div>
											</div>
									</div>
									<s:if test="%{#session.Company.companyRole.isSuperuser==true}">
									<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Supplier Name</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="commonReportPage.flightReportFilter.supplierName"
															id="supplierName" required>
															<option value="ALL">ALL</option>
															<s:iterator value="ApiProviders">
																<s:if
																	test="commonReportPage.flightReportFilter.supplierName != null && commonReportPage.flightReportFilter.supplierName == vendorName">
																	<option value="<s:property value="vendorName"/>"
																		selected="selected"><s:property value="vendorName"></s:property></option>
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

										<div class="date col-sm-12 clearfix">
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Booking
															Date</label>
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
												<div class="col-sm-4">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Travel Date</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="twoout2"
																placeholder="From Date(dd-mm-yyyy)"
																name="commonReportPage.flightReportFilter.dateFilterArrival.dtStart"
																value='<s:property value="commonReportPage.flightReportFilter.dateFilterArrival.dtStart"/>'>

														</div>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="twoout3"
																placeholder="To Date(dd-mm-yyyy)"
																name="commonReportPage.flightReportFilter.dateFilterArrival.dtEnd"
																value='<s:property value="commonReportPage.flightReportFilter.dateFilterArrival.dtEnd"/>'>
														</div>

													</div>
												</div>
												
												
												<div class="col-sm-4">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Invoice Date</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="twodt12"
															placeholder="From Date(dd-mm-yyyy)"
															name="commonReportPage.flightReportFilter.dateFilterInvoice.dtStart"
															value='<s:property value="commonReportPage.flightReportFilter.dateFilterInvoice.dtStart"/>'>

														</div>
														<div class="col-sm-6">
															<input type="text" class="form-control" id="twodt22"
															placeholder="To Date(dd-mm-yyyy)"
															name="commonReportPage.flightReportFilter.dateFilterInvoice.dtEnd"
															value='<s:property value="commonReportPage.flightReportFilter.dateFilterInvoice.dtEnd"/>'>
														</div>

													</div>
												</div>
												
												<%-- 
												<div class="col-sm-4">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Invoice From Date</label>
													 <div class="col-sm-6">
														<input type="text" class="form-control" id="twodt12"
															placeholder="From Date(dd-mm-yyyy)"
															name="commonReportPage.flightReportFilter.dateFilterInvoice.dtStart"
															value='<s:property value="commonReportPage.flightReportFilter.dateFilterInvoice.dtStart"/>'>
													 </div>
												</div>
												 
											
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Invoice To Date</label>
													 <div class="col-sm-6">
														<input type="text" class="form-control" id="twodt22"
															placeholder="To Date(dd-mm-yyyy)"
															name="commonReportPage.flightReportFilter.dateFilterInvoice.dtEnd"
															value='<s:property value="commonReportPage.flightReportFilter.dateFilterInvoice.dtEnd"/>'>

													 </div>
												</div>
												 
											</div> --%>

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
									<table id="example" class="table table-striped table-bordered"
										style="white-space: nowrap;">
										<%-- <thead>
											<tr>
												<th>S.No</th>
												<th>Passenger Name</th>
												<th>Confirmation Number</th>
												<s:if test="%{#session.Company.companyRole.isDistributor()==false || #session.User.userrole_id.isAgent() ==false}">
													<th>Invoice No</th>
													</s:if>
												<th>Booking Date</th>
												<th>Travel Date</th>
											
												<s:if
													test="%{#session.Company.companyRole.isCorporate()==false || #session.User.userrole_id.isSuperuser() }">
													<th>Supplier price</th>
													<th>Total Booked Amount</th>
													<th>ServiceTax</th>
													<th>MarkUp Amount</th>
												</s:if>
												<th>Booking Status</th>
												<th>Payment Status</th>
												<s:if
													test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
													<th>Agency</th>
												</s:if>
												<s:elseif
													test="%{#session.Company.companyRole.isCorporate()}">
													<th>Corporate</th>
												</s:elseif>
												<th>Remarks</th>
												<th>Action</th>
												<th class="noExport">Download Invoice</th>
												<th class="noExport">Invoice Email</th>
												<!-- <th>S.No</th>
											<th>Passenger Name</th>
											<th>Confirmation Number</th>
											<th>InvoiceNo</th>
											<th>Travel Date</th>
											<th>Booking Date</th>
											<th>Company Id</th>
											<th>User Id</th>
											<th>Supplier price</th>
											<th>Base Price</th>
											<th>Other Taxes</th>
											<th>Markup Amount</th>
											<th>Management Fee</th>
											<th>Convenience Fee</th>
											<th>Service Tax</th>
											<th>Total Amount</th>
											<th>Remarks</th>
											<th>Status</th>
											<th>Supplier Name</th>
											<th>Payment Status</th>
											<th>Agency</th>
											<th>OrderId</th>
											<th>Action</th> -->
												<!--  <th>ARR</th> -->
											</tr>
										</thead>
										 --%>
										 
										 <thead>
											<tr>
												<th>S.No</th>
												<th>Passenger Name</th>
												<th>Confirmation Number</th>
												<s:if test="%{#session.Company.companyRole.isDistributor()==false || #session.User.userrole_id.isAgent() ==false}">
													<th>Invoice No</th>
													<th>Invoice Date</th>
													<th>Invoice Amount</th>
												</s:if>
												<th>Booking Date</th>
												<th>Travel Date</th>
												
												<s:if
													test="%{#session.Company.companyRole.isCorporate()==false || #session.User.userrole_id.isSuperuser() }">
													<th>Supplier price</th>
													<th>Total Booked Amount</th>
													<th>ServiceTax</th>
													<th>MarkUp Amount</th>
												</s:if>
												<th>Booking Status</th>
												<th>Payment Status</th>
												<s:if
													test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
													<th>Agency<b>or</b>Company Name</th>
												</s:if>
												<s:elseif
													test="%{#session.Company.companyRole.isCorporate()}">
													<th>Corporate<b>or</b>Company Name</th>
												</s:elseif>
												<th>Remarks</th>
												<th>Action</th>
												<th class="noExport">Download Invoice</th>
												<th class="noExport">Invoice Email</th>
												<!-- ADDED BY BASHA -->
												<th class="noExport">Flight View</th>
												<!-- <th>S.No</th>
											<th>Passenger Name</th>
											<th>Confirmation Number</th>
											<th>InvoiceNo</th>
											<th>Travel Date</th>
											<th>Booking Date</th>
											<th>Company Id</th>
											<th>User Id</th>
											<th>Supplier price</th>
											<th>Base Price</th>
											<th>Other Taxes</th>
											<th>Markup Amount</th>
											<th>Management Fee</th>
											<th>Convenience Fee</th>
											<th>Service Tax</th>
											<th>Total Amount</th>
											<th>Remarks</th>
											<th>Status</th>
											<th>Supplier Name</th>
											<th>Payment Status</th>
											<th>Agency</th>
											<th>OrderId</th>
											<th>Action</th> -->
												<!--  <th>ARR</th> -->
											</tr>
										</thead>
										
										<tbody>
											<s:iterator value="commonReportPage.items" status="rowCount">
												<tr>
													<td><s:property
															value="%{((commonReportPage.currentPageIndex - 1)*commonReportPage.maxItems)+#rowCount.count}" /></td>
													<td><s:property value="title" />&nbsp;&nbsp;&nbsp;<s:property
															value="firstName" /> &nbsp;&nbsp;&nbsp; <s:property
															value="lastName" /></td>
													<td><s:property value="confirmationNumber" /></td>
													<s:if test="%{#session.Company.companyRole.isDistributor()==false || #session.User.userrole_id.isAgent() ==false}">
													<td><s:property value="invoiceNo" /></td>
													<td><s:property value="bookingDate" /></td>
													<td><s:property value="invoiceAmount" /></td>
													</s:if>
													<td><s:property value="bookingDate" /></td>
													<td><s:date name="travelDate1" format="dd-MM-yyyy" /></td>
													
													<s:if
														test="%{#session.Company.companyRole.isCorporate()==false || #session.User.userrole_id.isSuperuser() }">
														<td><s:property value="supplierPrice" /></td>
														<td><s:property value="total" /></td>
														<%-- <s:if
														test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														<td><s:property value="bookingPrice" /></td>
													</s:if>
													<s:else>
														<td><s:property value="finalPrice" /></td>
													</s:else> --%>
														<td><s:property value="serviceTax" /></td>
														<td><s:property value="markUp" /></td>
													</s:if>
													<td><s:property value="status" /></td>
													<td><s:property value="paymentStatus" /></td>
													<td><s:property value="createdBy" /></td>
													<td><s:property value="description" /></td>
													<%-- <td><s:property value="id" /></td> --%>
													<%-- <td><s:property value="title" />&nbsp;&nbsp;&nbsp;<s:property value="firstName" />
												&nbsp;&nbsp;&nbsp; <s:property value="lastName" /></td>
												<td><s:property value="confirmationNumber" /></td>
												<td><s:property value="invoiceNo" /></td>
												<td><s:date name="travelDate" format="dd/MMM/yyyy" /></td>
												<td><s:property value="insuranceBookingDate"  /></td>
												<td><s:property value="companyId" /></td>
												<td><s:property value="userId" /></td>
												<td><s:property value="supplierPrice" /></td>
												<td><s:property value="basePrice" /></td>
												<td><s:property value="otherTaxes" /></td>
												<td><s:property value="markUp" /></td>
												<td><s:property value="managementFee" /></td>
												<td><s:property value="convenienceFees" /></td>
												<td><s:property value="serviceTax" /></td>
												<td><s:property value="finalPrice" /></td>
												<td><s:property value="description" /></td>
												<td><s:property value="status" /></td>
												<td><s:property value="supplierName" /></td>
												<td><s:property value="paymentStatus" /></td>
												<td><s:property value="createdBy" /></td>
												<td><s:property value="orderId" /></td>
 --%>
													<%--  <td><p data-placement="top" >
													  <s:if test="status=='Confirmed'">
													 <a href='<s:text name="global.resourceApiUrl"></s:text>getpdf/flight?orderids=<s:property value="orderId"/>"
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
																href="showPassengerDetailsOfInsuranceReports?id=<s:property value="id"/>"
																class="btn btn-success btn-xs " data-toggle="modal">
																Details </a>

														</p>

													</td>
													<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>


															<p data-placement="top">

																<a href=getInsuranceOfflineInvoice?orderId=${id}
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Invoice </a>
															</p>

														</s:else></td>
													<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>
															<p data-placement="top">

																<a href="javascript:history.void(0);"
																	onclick="sendInsuranceInvoiceToCustomerEmail('${orderId}');"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Send Invoice To Email </a>


															</p>
														</s:else></td>
														
														<!-- ADDED BY BASHA -->
														<s:if test="insuredProductOrderRowId > 0 && insuredProductOrderRowId != null">
												<td>
													<p data-placement="top" class="FlightView">
														<a href="showPassengerDetails?id=<s:property value="insuredProductOrderRowId"/>&orderId=<s:property value="insuredProductRowOrderId"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Details </a>
													</p>
												</td>
												</s:if>
												<s:else>
												<td>NA</td></s:else>
														<!--ENDED BY BASHA -->

													<s:if
														test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
														<td><s:property value="apiProvider" /></td>
													</s:if>





													<%-- 	<td><s:property value="departureDate" /></td>
														<td><s:property value="arrivalDate" /></td> --%>

												</tr>
											</s:iterator>
										</tbody>
					<%-- 					<tbody>
											<s:iterator value="commonReportPage.items" status="rowCount">
												<tr>
													<td><s:property
															value="%{((commonReportPage.currentPageIndex - 1)*commonReportPage.maxItems)+#rowCount.count}" /></td>
													<td><s:property value="title" />&nbsp;&nbsp;&nbsp;<s:property
															value="firstName" /> &nbsp;&nbsp;&nbsp; <s:property
															value="lastName" /></td>
													<td><s:property value="confirmationNumber" /></td>
													<s:if test="%{#session.Company.companyRole.isDistributor()==false || #session.User.userrole_id.isAgent() ==false}">
													<td><s:property value="invoiceNo" /></td>
													</s:if>
													<td><s:property value="insuranceBookingDate" /></td>
													<td><s:date name="travelDate1" format="dd-MM-yyyy" /></td>
													
													<s:if
														test="%{#session.Company.companyRole.isCorporate()==false || #session.User.userrole_id.isSuperuser() }">
														<td><s:property value="supplierPrice" /></td>
														<td><s:property value="total" /></td>
														<s:if
														test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														<td><s:property value="bookingPrice" /></td>
													</s:if>
													<s:else>
														<td><s:property value="finalPrice" /></td>
													</s:else>
														<td><s:property value="serviceTax" /></td>
														<td><s:property value="markUp" /></td>
													</s:if>
													<td><s:property value="status" /></td>
													<td><s:property value="paymentStatus" /></td>
													<td><s:property value="createdBy" /></td>
													<td><s:property value="description" /></td>
													<td><s:property value="id" /></td>
													<td><s:property value="title" />&nbsp;&nbsp;&nbsp;<s:property value="firstName" />
												&nbsp;&nbsp;&nbsp; <s:property value="lastName" /></td>
												<td><s:property value="confirmationNumber" /></td>
												<td><s:property value="invoiceNo" /></td>
												<td><s:date name="travelDate" format="dd/MMM/yyyy" /></td>
												<td><s:property value="insuranceBookingDate"  /></td>
												<td><s:property value="companyId" /></td>
												<td><s:property value="userId" /></td>
												<td><s:property value="supplierPrice" /></td>
												<td><s:property value="basePrice" /></td>
												<td><s:property value="otherTaxes" /></td>
												<td><s:property value="markUp" /></td>
												<td><s:property value="managementFee" /></td>
												<td><s:property value="convenienceFees" /></td>
												<td><s:property value="serviceTax" /></td>
												<td><s:property value="finalPrice" /></td>
												<td><s:property value="description" /></td>
												<td><s:property value="status" /></td>
												<td><s:property value="supplierName" /></td>
												<td><s:property value="paymentStatus" /></td>
												<td><s:property value="createdBy" /></td>
												<td><s:property value="orderId" /></td>

													 <td><p data-placement="top" >
													  <s:if test="status=='Confirmed'">
													  <a href='<s:text name="global.resourceApiUrl"></s:text>getpdf/flight?orderids=<s:property value="orderId"/>"
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
														
													</p></td>

													<td>
														<p data-placement="top" class="details">
															<a
																href="showPassengerDetailsOfInsuranceReports?id=<s:property value="id"/>"
																class="btn btn-success btn-xs " data-toggle="modal">
																Details </a>

														</p>

													</td>
													<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>


															<p data-placement="top">

																<a href=getInsuranceOfflineInvoice?orderId=${id}
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Invoice </a>
															</p>

														</s:else></td>
													<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>
															<p data-placement="top">

																<a href="javascript:history.void(0);"
																	onclick="sendInsuranceInvoiceToCustomerEmail('${orderId}');"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Send Invoice To Email </a>


															</p>
														</s:else></td>

													<s:if
														test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
														<td><s:property value="apiProvider" /></td>
													</s:if>





														<td><s:property value="departureDate" /></td>
														<td><s:property value="arrivalDate" /></td>

												</tr>
											</s:iterator>
										</tbody> --%>
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
				</s:if>
			</div>
		</section>
	</div>


	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript" src="js/reset-form.js"></script>
	<script>
		$(document).ready(function() {

			$("#twoout2").keyup(function() {
				$(this).next("#twoout3").focus();
			});

			$("#twodt1").keyup(function() {
				$(this).next("#twodt2").focus();
			});

			$('#twoout2').datepicker({
				//showTimePicker: false,
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
				onSelect : function(selectedDate) {
					var date2 = $("#twoout2").datepicker('getDate', '+1d');
					date2.setDate(date2.getDate() + 1);
					$("#twoout3").datepicker('setDate', date2);
					$("#twoout3").datepicker("option", "minDate", date2);
				},
				onClose : function() {
					$("#twoout3").focus();
				}

			});

			$('#twoout3').datepicker({
				//timepicker: false,
				/*  changeMonth: true,   
				 changeYear:true, */
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
				minDate : 0,
				onSelect : function(selectedDate, i) {
					// $("#fromDate").datepicker("option", selected)
					var date2 = $("#twoout3").datepicker('getDate', '+1d');

				}

			});

			$('#twodt1').datepicker({
				//showTimePicker: false,
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
				onSelect : function(selectedDate) {
					var date2 = $("#twodt1").datepicker('getDate', '+1d');
					date2.setDate(date2.getDate() + 1);
					$("#twodt2").datepicker('setDate', date2);
					$("#twodt2").datepicker("option", "minDate", date2);
				},
				onClose : function() {
					$("#twodt2").focus();
				}

			});

			$('#twodt2').datepicker({
				//timepicker: false,
				/*  changeMonth: true,   
				 changeYear:true, */
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
				minDate : 0,
				onSelect : function(selectedDate, i) {
					// $("#fromDate").datepicker("option", selected)
					var date2 = $("#twodt2").datepicker('getDate', '+1d');

				}

			});
			$('#twodt12').datepicker({
				 //showTimePicker: false,
				dateFormat : "dd-mm-yy",
			changeMonth : true,
			changeYear : true,
				 onSelect: function( selectedDate ) {
				    	var date2 = $("#twodt12").datepicker('getDate', '+1d'); 
				  	  date2.setDate(date2.getDate() + 1); 
				  	  $( "#twodt22" ).datepicker('setDate', date2);
				  	$( "#twodt22" ).datepicker( "option", "minDate", date2 ); 
				    },
				  onClose: function() {
				      $("#twodt22").focus();
				  }
				  
			  });
		          
		     
		 
		 $('#twodt22').datepicker({
			 //timepicker: false,
			/*  changeMonth: true,   
	    	 changeYear:true, */
	    	 dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
	       	 minDate:0,
	         onSelect: function(selectedDate,i) { 
	        	// $("#fromDate").datepicker("option", selected)
	              var date2 = $("#twodt22").datepicker('getDate', '+1d'); 
	          
	            
	             
	         }
		 
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

		/* $(document).ready(function() {
			$('#twodt1').focus(function() {
				$(this).val('');
			});
			$('#twodt2').focus(function() {
				$(this).val('');
			});
			$('#twoout2').focus(function() {
				$(this).val('');
			});
			$('#twoout3').focus(function() {
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

		}); */
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

	<script>
		$('spna[data-toggle="tooltip"]').tooltip({
			animated : 'fade',
			placement : 'bottom',
		});
	</script>


</body>
</html>