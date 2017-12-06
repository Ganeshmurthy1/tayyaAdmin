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
<!-- <link href="css/jquery-uii.css" rel="stylesheet" type="text/css" /> -->
<link href="css/Tayyarahadmin-lintas.css" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
<link rel="stylesheet" type="text/css"
	href="print-pdf-excel/print-resource/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="print-pdf-excel/print-resource/buttons.dataTables.min.css">

<link rel="stylesheet" type="text/css"
	href="print-pdf-excel/print-resource/jquery.dataTables.min.css">


<script type="text/javascript" async=""
	src="print-pdf-excel/print-resource/ga.js.download"></script>
<%-- <script type="text/javascript" src="print-pdf-excel/print-resource/site.js.download">
	</script> --%>
<%-- <script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/jquery-1.12.3.js.download">
	</script> --%>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/jquery.dataTables.min.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/dataTables.buttons.min.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/buttons.flash.min.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/jszip.min.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/pdfmake.min.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/vfs_fonts.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/buttons.html5.min.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/buttons.print.min.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/demo.js.download">
	</script>
<script type="text/javascript" language="javascript"
	src="print-pdf-excel/print-resource/buttons.colVis.min.js">
	</script>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<script src="js/load_company_emp_names.js"></script>
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

<script type="text/javascript">
	$(function() {
		var reportType = document.getElementById('reportTypeHidden').value;

		document.getElementById('reportType').value = reportType;

	});
</script>
<script type="text/javascript">
  function  sendHotelVoucherToCustomerEmail(confirmNumber,invoiceMail){
	  var msg="";
    	  console.log("orderId..."+confirmNumber);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl = newUrl+"sendHotelVoucherToCustomerEmail";
  		if(invoiceMail=="invoice"){
  			 finalUrl = newUrl+"sendHotelInvoiceToCustomerEmail";
  			msg="Successfully sent hotel Invoice to email.";
  		}
  		else{
  			 //finalUrl = newUrl+"sendHotelVoucherToCustomerEmail";
  			//msg="Successfully sent hotel voucher to email.";
  			 finalUrl = newUrl+"sendHotelVoucherToCustomerEmail";
   			msg="Successfully sent hotel voucher to email.";
  		}
  	  console.log("finalUrl..."+finalUrl);
  		$('#h4').show();
  			$.ajax({
				    method: "POST",
				    url:finalUrl,
				    data: {orderId:confirmNumber},
				    success:function(data,status)
					{ 
				        $.each(data, function(index, element) {
				    		  console.log("data-------"+element.status);
						     	if(element.status=="success"){
						     		$('#h4').hide();
						    		  $('#success-alert').show();
						    		  $('#message').text(msg);
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					  //window.location.assign($(location).attr('href'));
						  					});					  				
						    	}
						    	else if(element.status=="fail"){
						    		$('#h4').hide();
						    	 $('#success-alert').show();
									  $('#message').text("Failed.Try again.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					 
						  					});  
					    	}
						    	 
				    	 });
				     },
					error: function(xhr, status, error)
					{
						$('#h4').hide();
					 $('#success-alert').show();
						 $('#message').text(error);
						  $('#success').click(function() {
		  					  $('#success-alert').hide();
		  					 });  
					   console.log("Error----------"+error);
					}
				}); 
    	   }
      
  </script>
</head>
<body>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>Hotel Reports</h1>
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
		<section class="content">
			<div class="row">
				<div class="col-sm-12 clearfix">
					<s:if test="%{#session.Company!=null && #session.User!=null}">

						<form action="companyHotelReports" method="post" id="filterform">
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
										<a class="btn btn-primary" role="button"
											data-toggle="collapse" href="#filters" aria-expanded="false"
											aria-controls="filters"> <i class="fa fa-filter"
											aria-hidden="true"></i> Filters
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
																	value="<s:property value="hotelReportPage.hotelReportFilter.reportType"/>">
																<select class="form-control"
																	name="hotelReportPage.hotelReportFilter.reportType"
																	id="reportType" required>
																	<option value="1">My Reports</option>
																	<s:if
																		test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
																	</s:if>
																	<s:else>
																		<s:if
																			test="%{#session.Company.companyRole.isDistributor()}">
																			<option value="0">ALL</option>
																			<option value="4">My Agency(s)</option>
																		</s:if>
																		<s:if
																			test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Corporate==false   && #session.Company.companyRole.Agent==false)}">
																			<%-- <option value="3">My
																				<s:text name="global.Wholesaler"></s:text>(s)
																			</option> --%>
																			<option value="0">ALL</option>
																			<option value="8">All Corporate(s)</option>
																			<option value="4">My Agency(s)</option>
																			<option value="6">All
																				<s:text name="global.Wholesaler"></s:text>(s)
																			</option>
																			<option value="7">All Agency(s)</option>
																		</s:if>
																	</s:else>


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
																	autocomplete="on" placeholder="type emp user id"
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
																<input type="text" class="form-control" id="hotelName"
																	autocomplete="off" placeholder="type hotel name"
																	name="hotelReportPage.hotelReportFilter.hotelName"
																	value="<s:property value="hotelReportPage.hotelReportFilter.hotelName"/>">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Customer
																Email </label>
															<div class="col-sm-12">
																<input type="text" class="form-control"
																	id="emailAddress" autocomplete="off"
																	placeholder="email"
																	name="hotelReportPage.hotelReportFilter.email"
																	value="<s:property value="hotelReportPage.hotelReportFilter.email"/>">
															</div>
														</div>
													</div>

													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Book
																Status</label>
															<div class="col-sm-12">
																<select class="form-control"
																	name="hotelReportPage.hotelReportFilter.bookingStatus"
																	id="bookingStatus" required>

																	<option value="ALL">ALL</option>
																	<c:forEach var="statusItem"
																		items="${hotelReportPage.hotelReportFilter.bookingStatusQueue}">
																		<c:choose>
																			<c:when
																				test="${hotelReportPage.hotelReportFilter.bookingStatus != null && hotelReportPage.hotelReportFilter.bookingStatus == statusItem}">
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
																	name="hotelReportPage.hotelReportFilter.paymentStatus"
																	id="paymentStatus" required>

																	<option value="ALL">ALL</option>
																	<c:forEach var="paymentStatusItem"
																		items="${hotelReportPage.hotelReportFilter.paymentStatusQueue}">
																		<c:choose>
																			<c:when
																				test="${hotelReportPage.hotelReportFilter.paymentStatus != null && hotelReportPage.hotelReportFilter.paymentStatus == paymentStatusItem}">
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
														<input type="text" class="form-control" value="<s:property value="hotelReportPage.hotelReportFilter.invoiceNo"/>"
															placeholder="type invoiceNo" autocomplete="off"    name="hotelReportPage.hotelReportFilter.invoiceNo"  id="invoiceNo">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Confirmation No</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="hotelReportPage.hotelReportFilter.confirmationNo"/>"
															placeholder="type orderId" autocomplete="off"    name="hotelReportPage.hotelReportFilter.confirmationNo"  id="confirmationNo">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">First Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="hotelReportPage.hotelReportFilter.firstName"/>"
															placeholder="type firstName" autocomplete="off"    name="hotelReportPage.hotelReportFilter.firstName"  id="firstName">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Last Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="hotelReportPage.hotelReportFilter.lastName"/>"
															placeholder="type lastName" autocomplete="off"    name="hotelReportPage.hotelReportFilter.lastName"  id="lastName">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Location</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="hotelReportPage.hotelReportFilter.location"/>"
															placeholder="type email" autocomplete="off"    name="hotelReportPage.hotelReportFilter.location"  id="location">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Mobile</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="hotelReportPage.hotelReportFilter.mobile"/>"
															placeholder="type mobile" autocomplete="off"    name="hotelReportPage.hotelReportFilter.mobile"  id="mobile">
													</div>
											</div>
									</div>
									<s:if test="%{#session.Company.companyRole.isSuperuser==true}">
									<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Supplier Name</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="hotelReportPage.hotelReportFilter.supplierName"
															id="supplierName" required>
															<option value="ALL">ALL</option>
															<s:iterator value="ApiProviders">
																<s:if
																	test="hotelReportPage.hotelReportFilter.supplierName != null && hotelReportPage.hotelReportFilter.supplierName == vendorName">
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


											<!-- Filter of additional info -->

											<div class="date col-sm-12 clearfix">
												<div class="row">
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Booking
																From Date</label> <input type="text" class="form-control"
																id="twodt1" placeholder="From Date(dd-mm-yyyy)"
																name="hotelReportPage.hotelReportFilter.dateFilterBooking.dtStart"
																value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterBooking.dtStart"/>'>

														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Booking To
																Date</label> <input type="text" class="form-control" id="twodt2"
																placeholder="To Date(dd-mm-yyyy)"
																name="hotelReportPage.hotelReportFilter.dateFilterBooking.dtEnd"
																value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterBooking.dtEnd"/>'>

														</div>

													</div>
													<div class="col-sm-4">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Check-In
																Date</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="twocin2"
																	placeholder="From Date(dd-mm-yyyy)"
																	name="hotelReportPage.hotelReportFilter.checkInFrom"
																	value='<s:property value="hotelReportPage.hotelReportFilter.checkInFrom"/>'>

															</div>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="twocin3"
																	placeholder="To Date(dd-mm-yyyy)"
																	name="hotelReportPage.hotelReportFilter.checkInTo"
																	value='<s:property value="hotelReportPage.hotelReportFilter.checkInTo"/>'>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Check-Out
																Date</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="twoout2"
																	placeholder="From Date(dd-mm-yyyy)"
																	name="hotelReportPage.hotelReportFilter.checkOutFrom"
																	value='<s:property value="hotelReportPage.hotelReportFilter.checkOutFrom"/>'>

															</div>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="twoout3"
																	placeholder="To Date(dd-mm-yyyy)"
																	name="hotelReportPage.hotelReportFilter.checkOutTo"
																	value='<s:property value="hotelReportPage.hotelReportFilter.checkOutTo"/>'>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Invoice From Date</label>
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


							<div class="col-sm-12 clearfix report-search">

								<div class="table-responsive dash-table">
									<div class="box clearfix">
										<table id="example" class="table table-striped table-bordered">
<%-- 											<thead>
												<tr>
													<th>S.No</th>
													<th>Booking Created Date</th>
													<th>GuestName</th>
													<th>HotelName</th>
													<th>BookingDate</th>
													<th>CheckIn</th>
													<th>CheckOut</th>

													<s:if test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
													<th>MarkUp</th>
													</s:if>

													<s:if
														test="%{#session.Company.companyRole.isCorporate()==false}">
														<th>NetPayable</th>
														<th>MarkUp</th>
													</s:if>
													<th>FinalAmount</th>
													<s:elseif test="%{#session.Company.companyRole.isCorporate()==false}">
													<th>FinalAmount</th>
													</s:elseif>
													<s:else><th>MarkUp</th></s:else>
													<th>BookingStatus</th>
													<s:if
														test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														<th>Agency</th>
													</s:if>
													<s:elseif
														test="%{#session.Company.companyRole.isCorporate()}">
														<th>Corporate</th>
													</s:elseif>

													<s:if
														test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														<th>SalesPersonName</th>
													</s:if>
													<s:elseif
														test="%{#session.Company.companyRole.isCorporate()}">
														<th>BookerUserName</th>
													</s:elseif>
													<s:else>
														<th>SalesPersonName</th>
													</s:else>

													<th>OrderId</th>
													<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<th class="noExport">Download Voucher</th>
											<th class="noExport">Voucher Email</th>											
											</s:if>
											<s:elseif test="%{(#session.Company.companyRole.isSuperUser()==true) || #session.Company.companyRole.isCorporate()}">
											<th class="noExport">Download Voucher/Invoice</th>
											<th class="noExport">Voucher Email</th>
											<th class="noExport">Invoice Email</th>
											</s:elseif>
											
											 <s:else>
											 <th class="noExport">Download Voucher</th>
											<th class="noExport">Voucher Email</th>	
											 </s:else>
													<th class="noExport">Action</th>
													<s:if
														test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false && #session.Company.companyRole.Corporate==false)}">
														<th>SupplierName</th>
													</s:if>
												</tr>
											</thead> --%>
												<thead>
												<tr>
													<th>S.No</th>
													<th>Booking Created Date</th>
													<th>GuestName</th>
													<th>HotelName</th>
													<th>City</th>
													<th>BookingDate</th>
													<th>Booking Confirm No</th>
													<s:if
														test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														<th>Confirmation No </th>
													</s:if>
													<s:else>
													    <th>invoice no</th>
													</s:else>
													
													<s:if
														test="%{#session.Company.companyRole.isDistributor()==false || #session.Company.companyRole.isAgent()==false}">
														<th>invoice Date</th>
													</s:if>
													<s:if
														test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isCorporate() }">
														<th>Invoice Value</th>
													</s:if>
													<th>CheckIn</th>
													<th>CheckOut</th>
													<s:if
														test="%{#session.Company.companyRole.isCorporate()==false}">
														<th>NetPayable</th>
														<th>MarkUp</th>
													</s:if>
													
													<th>FinalAmount</th>
													<th>BookingStatus</th>
													<s:if
														test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														<th>Agency<br>or</br>CompanyName</th>
													</s:if>
													<s:elseif
														test="%{#session.Company.companyRole.isCorporate()}">
														<th>Corporate<br>or</br>CompanyName</th>
													</s:elseif>

													<s:if
														test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														<th>SalesPersonName</th>
													</s:if>
													<s:elseif
														test="%{#session.Company.companyRole.isCorporate()}">
														<th>BookerUserName</th>
													</s:elseif>
													<s:else>
														<th>SalesPersonName</th>
													</s:else>

													<th>OrderId</th>
													<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<th class="noExport">Download Voucher</th>
											<th class="noExport">Voucher Email</th>											
											</s:if>
											<s:elseif test="%{(#session.Company.companyRole.isSuperUser()==true) || #session.Company.companyRole.isCorporate()}">
											<th class="noExport">Download Voucher/Invoice</th>
											<th class="noExport">Voucher Email</th>
											<th class="noExport">Invoice Email</th>
											</s:elseif>
											
											 <s:else>
											 <th class="noExport">Download Voucher</th>
											<th class="noExport">Voucher Email</th>	
											 </s:else>
													<th class="noExport">Action</th>
													<%-- <s:if
														test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false && #session.Company.companyRole.Corporate==false)}">
														<th>SupplierName</th>
													</s:if> --%>
												</tr>
											</thead>
												<tbody>
												<s:iterator value="hotelReportPage.items" status="rowCount">
													<tr>
														<td><s:property
																value="%{((hotelReportPage.currentPageIndex - 1)*hotelReportPage.maxItems)+#rowCount.count}" /></td>
															<td><s:date name="createdAt" format="dd-MM-yyyy"></s:date></td>
														<td><s:property value="firstname" /> <s:property
																value="lastname" /></td>
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
														<s:if
														test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														<td><s:property value="orderId" /></td>
														</s:if>
														<s:else>
													    <td>
													    
													    <c:choose>
														<c:when test="${invoiceNo!=null}">
														<s:property value="invoiceNo" />
														</c:when>
														<c:otherwise>NA</c:otherwise>
														</c:choose>
													    </td>
														</s:else>
														<s:if
														test="%{#session.Company.companyRole.isDistributor()==false || #session.Company.companyRole.isAgent()==false}">
														<td><s:property value="bookingDate" /></td>
														</s:if>
														<s:if
														test="%{#session.User.userrole_id.isSuperuser() ||#session.Company.companyRole.isCorporate() }">
														<td><s:property value="invoiceAmount" /></td>
														</s:if>
														<td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td>
														<s:if
															test="%{#session.Company.companyRole.isCorporate()==false}">
															<td><s:property value="netAmnt" /></td>
															<td><s:property value="markUp" /></td>
														</s:if>
														
														<td><s:property value="total" /></td>
														<td><s:property value="statusAction" /></td>
														<s:if test="createdBy=='DirectUser'">
															<td>B2C</td>
														</s:if>
														<s:else>
															<td><s:property value="createdBy" /></td>
														</s:else>
														<th><s:property value="salesPersonName" /></th>
														<th><s:property value="orderRef" /></th>
														<td>
															<p data-placement="top">
																<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																	<a href=getHotelOfflineVoucher?hotelOrderId=${id}
																		class="btn btn-success btn-xs" data-toggle="modal">
																		Voucher </a>
																</s:if>
																<s:else>
																NA
																</s:else>
															<s:if test="%{(#session.Company.companyRole.isSuperUser()==true) || #session.Company.companyRole.isCorporate()}">
																	<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																		<a href=getHotelOfflineInvoice?hotelOrderId=${id}
																			class="btn btn-success btn-xs" data-toggle="modal">
																			Invoice </a>
																	</s:if>
																	<s:else>
 															NA
 															</s:else>
																
															</s:if></p>
														</td>
														<td>
															<p data-placement="top">
																<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																	<a href="javascript:history.void(0);"
																		onclick="sendHotelVoucherToCustomerEmail('${orderRef}','voucher');"
																		class="btn btn-success btn-xs" data-toggle="modal">
																		Send Voucher To Email </a>
																</s:if>
																<s:else>
 															NA
 															</s:else>
															</p>
														</td>
														<s:if test="%{(#session.Company.companyRole.isSuperUser()==true) || #session.Company.companyRole.isCorporate()}">
													<td><p data-placement="top" >
													  <s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
													  <a href="javascript:history.void(0);"
															class="btn btn-success btn-xs"  onclick="sendHotelVoucherToCustomerEmail('${orderRef}','invoice');"      data-toggle="modal">
															Send Invoice To Email</a>
													  </s:if>
													  <s:else>
													  NIL													  
													  </s:else>										  
														
													</p></td>
													 </s:if>

														<td>
															<p data-placement="top" class="details">
																<a
																	href="showReportDetails?selectedRowIndex=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">Details</a>
																<s:if
																	test="%{#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false}">
																	<s:if test="apiResponseMessage!=null">
																		<span class="detailstiptext"><s:property
																				value="apiResponseMessage" /></span>
																	</s:if>
																	<s:else>
																		<span class="detailstiptext">No Response</span>
																	</s:else>
																</s:if>
																<s:else>
																	<s:if
																		test="apiResponseMessage!=null && apiResponseMessage=='Confirmed'">
																		<span class="detailstiptext"><s:property
																				value="apiResponseMessage" /></span>
																	</s:if>
																	<s:else>
																		<span class="detailstiptext">Please Contact
																			Tayyarah</span>
																	</s:else>
																</s:else>
															
															</p>
														</td>
													<%-- 	<s:if
															test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false && #session.Company.companyRole.Corporate==false)}">
														<s:property value="apiProvider.vendorName"/>
														</s:if> --%>
													</tr>
												</s:iterator>
											</tbody>
											
											
<%-- 											<tbody>
												<s:iterator value="hotelReportPage.items" status="rowCount">
													<tr>
														<td><s:property
																value="%{((hotelReportPage.currentPageIndex - 1)*hotelReportPage.maxItems)+#rowCount.count}" /></td>
															<td><s:date name="createdAt" format="dd-MM-yyyy"></s:date></td>
														<td><s:property value="firstname" /> <s:property
																value="lastname" /></td>
														<td><s:property value="hotelName" /></td>
														<td><s:property value="bookingDate" /></td>
														<td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td>
														<s:if
															test="%{#session.Company.companyRole.isCorporate()==false}">
															<td><s:property value="netAmnt" /></td>
															<td><s:property value="markUp" /></td>
														</s:if>
														<td><s:property value="total" /></td>
														<td><s:property value="statusAction" /></td>
														<s:if test="createdBy=='DirectUser'">
															<td>B2C</td>
														</s:if>
														<s:else>
															<td><s:property value="createdBy" /></td>
														</s:else>
														<th><s:property value="salesPersonName" /></th>
														<th><s:property value="orderRef" /></th>
														<s:if
															test="%{#session.User.userrole_id.isSuperuser() || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent() || #session.Company.companyRole.isCorporate()}">
															<td><s:property value="salesPersonName" /></td>
														</s:if>
														<s:elseif
															test="%{#session.Company.companyRole.isCorporate()}">
															<th><s:property value="salesPersonName" /></th>
														</s:elseif>
														<s:else>
															<th><s:property value="createdBy" /></th>
														</s:else>


														<td>
															<p data-placement="top">
																<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																	<a href=getHotelOfflineVoucher?hotelOrderId=${id}
																		class="btn btn-success btn-xs" data-toggle="modal">
																		Voucher </a>
																</s:if>
																<s:else>
																NA
																</s:else>
															<s:if test="%{(#session.Company.companyRole.isSuperUser()==true) || #session.Company.companyRole.isCorporate()}">
																	<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																		<a href=getHotelOfflineInvoice?hotelOrderId=${id}
																			class="btn btn-success btn-xs" data-toggle="modal">
																			Invoice </a>
																	</s:if>
																	<s:else>
 															NA
 															</s:else>
																
															</s:if></p>
														</td>
															
															
														<td>
															<p data-placement="top">
																<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																	<a href="javascript:history.void(0);"
																		onclick="sendHotelVoucherToCustomerEmail('${orderRef}','voucher');"
																		class="btn btn-success btn-xs" data-toggle="modal">
																		Send Voucher To Email </a>
																</s:if>
																<s:else>
 															NA
 															</s:else>
															</p>
														</td>
														<s:if test="%{(#session.Company.companyRole.isSuperUser()==true) || #session.Company.companyRole.isCorporate()}">
													<td><p data-placement="top" >
													  <s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
													  <a href="javascript:history.void(0);"
															class="btn btn-success btn-xs"  onclick="sendHotelVoucherToCustomerEmail('${orderRef}','invoice');"      data-toggle="modal">
															Send Invoice To Email</a>
													  </s:if>
													  <s:else>
													  NIL													  
													  </s:else>										  
														
													</p></td>
													 </s:if>

														<td>
															<p data-placement="top" class="details">
																<a
																	href="showReportDetails?selectedRowIndex=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">Details</a>
																<s:if
																	test="%{#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false}">
																	<s:if test="apiResponseMessage!=null">
																		<span class="detailstiptext"><s:property
																				value="apiResponseMessage" /></span>
																	</s:if>
																	<s:else>
																		<span class="detailstiptext">No Response</span>
																	</s:else>
																</s:if>
																<s:else>
																	<s:if
																		test="apiResponseMessage!=null && apiResponseMessage=='Confirmed'">
																		<span class="detailstiptext"><s:property
																				value="apiResponseMessage" /></span>
																	</s:if>
																	<s:else>
																		<span class="detailstiptext">Please Contact
																			Tayyarah</span>
																	</s:else>
																</s:else>
																<s:if test="apiResponseMessage!=null}">
															<span class="detailstiptext" ><s:property value="apiResponseMessage" /></span>  
															</s:if>
															<s:else>
															<span class="detailstiptext">No Response</span>  
															</s:else>

															</p>
														</td>
														<s:if
															test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false && #session.Company.companyRole.Corporate==false)}">
														<s:property value="apiProvider.vendorName"/>
														</s:if>
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
														<button type="submit"
															name="hotelReportPage.currentPageIndex" value="${i}"
															class="btn btn-primary">
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
							</div>
						</form>

					</s:if>
				</div>
			</div>
		</section>
	</div>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript" src="js/reset-form.js"></script>
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
			
			$("#twoout2").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#twoout3").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#twocin2").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#twocin3").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#twodt12").datepicker({
				dateFormat : "dd-mm-yy",
					changeMonth:true,
					changeYear:true
			 
			});
			$("#twodt22").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth:true,
				changeYear:true
			});
			
		});
	</script>
	<script type="text/javascript">
		/* , 'pdf', 'print'  */
		$(document).ready(
				function() {
					var currentDate = "export-data_"+$.datepicker.formatDate('yyyy-mm-dd', new Date())+(new Date).getTime();
					$('#example').DataTable( {
						dom: 'Bfrtip',
						"paging" : false,
						"info" : false,
						"searching" : false,
						 
					buttons: [
					            {
					                extend: 'csv',
					                exportOptions: {
					                	columns: "thead th:not(.noExport)"
					                },
					                filename: currentDate
					            },
					            'colvis'
					        ]
						        
					} );


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


</body>
</html>