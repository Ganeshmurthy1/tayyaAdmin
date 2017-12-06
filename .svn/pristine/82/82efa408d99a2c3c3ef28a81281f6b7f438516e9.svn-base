<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>

 

<link rel="stylesheet" href="css/alert.css">
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
  <script
	src="js/load_company_emp_names.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script type="text/javascript">
	$(function() {
		var reportType = document.getElementById('reportTypeHidden').value;

		document.getElementById('reportType').value = reportType;

	});
</script>
  
</head>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight DSR Report</h1>
			 
		</section>
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
			
			
			<s:if test="hasActionErrors()">
				<div class="succfully-updated clearfix" id="error-alert">

					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>

					<div class="col-sm-10">

						<p>
							<s:actionerror />
						</p>

						<button type="button" id="cancel" class="btn btn-primary">Ok</button>

					</div>

				</div>


			</s:if>

			<s:if test="hasActionMessages()">
				<div class="sccuss-full-updated" id="success-alert">
					<div class="succfully-updated clearfix">

						<div class="col-sm-2">
							<i class="fa fa-check fa-3x"></i>
						</div>

						<div class="col-sm-10">
							<s:actionmessage />
							<button type="button" id="success" class="btn btn-primary">Ok</button>

						</div>

					</div>
				</div>
			</s:if>
					
			<div class="row" >
			
		<div class="collapse filter-box col-sm-12" id="documentDownload">
									<div class="well clearfix">	
 			<form class="form-horizontal" name="myForm"  action="downloadHotelDisReport">
					 <div id="divresult"></div>
					 	<div class="clearfix dsrform">				 
<div class="col-sm-2"> 
	<div class="form-group clearfix">
	<label class="col-sm-12 control-label"> 
	  <span>Select Report Type</span></label>
		 <div class="col-sm-12">
		 <select name="reportType" class="reportty"> <option value="0">Select Report Type</option>
		 <option value="T">Today</option>
		 <option value="W">Week</option>
		 <option value="M">Month</option>
		 </select> 
		 </div>
 </div>
 </div>
 <div class="col-sm-2">
  <div class="form-group clearfix">
	  <div class="col-sm-12">OR
	  </div>
  </div>
  </div>
 
 <div class="col-sm-2"> 
	<div class="form-group clearfix">
	<label class="col-sm-12 control-label"> 
	  <span>Booking From Date</span></label>
		 <div class="col-sm-12">
		 <input type="text" name="fromDate" id="exctwodt2" class="reportty" placeholder="fromDate">
		  
		 </div>
 </div>
 </div>
 
  <div class="col-sm-1 text-center" > 
  
  <div class="form-group clearfix">
	<label class="col-sm-12 control-label"> 
	  <span>   </span></label>
		 <div class="col-sm-12">
		  <i class="fa fa-long-arrow-right" style="margin-top: 20px;"></i>
		 								 
		 </div>
 </div>
	 
 </div>
 
 <div class="col-sm-2"> 
	<div class="form-group clearfix">
	<label class="col-sm-12 control-label"> 
	  <span>Booking To Date</span></label>
		 <div class="col-sm-12">
		  
		 <input type="text" name="toDate"  id="exctwodt1" class="reportty" placeholder="toDate"> 
		 </div>
 </div>
 </div>
  
 </div>
					 
  <div id="checkbox-detect clearfix" class="col-sm-12"> 
     <ul class="detectul clearfix">
        <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.guestName" type="checkbox"   value="true"  checked="checked" > <span>PaxName</span></label></div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.pnr" type="checkbox"   value="true"  checked="checked" > <span>PNR</span></label></div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.finalPrice" type="checkbox"   value="true"  checked="checked"  > <span>FinalPrice</span></label> </div></li>
            <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.invoiceNo" type="checkbox"   value="true"  checked="checked"  > <span>InvoiceNumber</span></label> </div></li>
                  <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.orderRef" type="checkbox"   value="true"  checked="checked"  > <span>OrderId</span></label> </div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.statusAction" type="checkbox"  value="true"  checked="checked" > <span> StatusAction</span></label></div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.paymentStatus" type="checkbox"  value="true"  checked="checked"  > <span>paymentStatus</span></label></div></li>
           <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.agency" type="checkbox"  value="true"  checked="checked"  > <span>Agency</span></label></div></li>
                <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.airline" type="checkbox"  value="true"  checked="checked" > <span>Airline</span></label></div></li>
              
     </ul>
     <div class="clearfix text-right">
     <button type="submit" class="btn btn-success " id="xlsdownload" ><i class="fa fa-file-excel-o"></i> download</button>
       <input type="hidden" name="hotelandFlightDisReportProperty.travelType" value="F">
     </div>
      
 </div>

				</form> 
				</div>
				</div>
			</div>
			 
			 
		 
			<div class="row">
				<div class="col-sm-12 clearfix">
					<s:if test="%{#session.Company!=null && #session.User!=null}">
						<form action="getFlightDisReports" method="post" id="filterform">
				<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">
					<input type="hidden" name="showType"
					value="<s:property value="showType"/>">


							<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-8">
								<a class="btn btn-primary" role="button" data-toggle="collapse"
									href="#filters" aria-expanded="false" aria-controls="filters">
									<i class="fa fa-filter" aria-hidden="true"></i> Filters
								</a>
								<a class="btn btn-primary" role="button"
											data-toggle="collapse" href="#documentDownload" aria-expanded="false"
											aria-controls="filters"> <i class="fa fa-download"
											aria-hidden="true"></i>Import Excel
										</a>
							</div>

							<div class="col-sm-4 pull-right items">
								<div class="form-group clearfix">

									<div class="col-sm-6">
										<select class="form-control" name="flightReportPage.maxItems"
											id="maxItems" required onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${flightReportPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${flightReportPage.maxItems != null && flightReportPage.maxItems == maxItems}">
													 <c:choose>
													 <c:when
														test="${flightReportPage.maxItems == -1}">
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
															value="<s:property value="flightReportPage.flightReportFilter.reportType"/>">
														<select class="form-control"
															name="flightReportPage.flightReportFilter.reportType"
															id="reportType" required>
															<option value="1">My Reports</option>
															<option value="0">ALL</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">PNR</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.pnr"/>"
															placeholder="type pnr" autocomplete="off"    name="flightReportPage.flightReportFilter.pnr"  id="pnr">
													</div>
												</div>
											</div>  

											  <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">PaxName</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.paxName"/>"
															placeholder="paxName"  autocomplete="off"     id="paxName" name="flightReportPage.flightReportFilter.paxName">
													</div>
												</div>
											</div>  
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Company Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="companyName" placeholder="type company Name"
															name="flightReportPage.flightReportFilter.companyName"
															value="<s:property value="flightReportPage.flightReportFilter.companyName"/>">
													</div>
												</div>
											</div>



											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Employee
														Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="on"
															id="agentName" placeholder="type emp user id"
															name="flightReportPage.flightReportFilter.userName"
															value="<s:property value="flightReportPage.flightReportFilter.userName"/>">
													</div>
												</div>
											</div>


											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Airline Name</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="flightReportPage.flightReportFilter.airlineName"
															id="airlinename" required>
															<option value="ALL">ALL</option>
															<s:iterator value="airlineList">

																<s:if
																	test="flightReportPage.flightReportFilter.airlineName != null && flightReportPage.flightReportFilter.airlineName == airlinename">
																	<option value="<s:property value="airlinename"/>"
																		selected="selected"><s:property
																			value="airlinename"></s:property></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="airlinename"/>"><s:property
																			value="airlinename"></s:property></option>
																</s:else>
															</s:iterator>
														</select>

													</div>
												</div>
											</div>

											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking
														Status</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="flightReportPage.flightReportFilter.bookingStatus"
															id="bookingStatus" required>

															<option value="ALL">ALL</option>
															<c:forEach var="statusItem"
																items="${flightReportPage.flightReportFilter.bookingStatusQueue}">
																<c:choose>
																	<c:when
																		test="${flightReportPage.flightReportFilter.bookingStatus != null && flightReportPage.flightReportFilter.bookingStatus == statusItem}">
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
															name="flightReportPage.flightReportFilter.paymentStatus"
															id="paymentStatus" required>

															<option value="ALL">ALL</option>
															<c:forEach var="paymentStatusItem"
																items="${flightReportPage.flightReportFilter.paymentStatusQueue}">
																<c:choose>
																	<c:when
																		test="${flightReportPage.flightReportFilter.paymentStatus != null && flightReportPage.flightReportFilter.paymentStatus == paymentStatusItem}">
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
															name="flightReportPage.flightReportFilter.dateFilterBooking.dtStart"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterBooking.dtStart"/>'>

													</div>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="twodt2"
															placeholder="To Date(dd-mm-yyyy)"
															name="flightReportPage.flightReportFilter.dateFilterBooking.dtEnd"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterBooking.dtEnd"/>'>
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
												name="flightReportPage.currentPageIndex"
												value="${flightReportPage.currentPageIndex}">Submit</button>
										</div>
									</div>
 
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table">
							<!-- testing -->
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>S.No</th>
											<th>PaxName</th>
											<th>PNR</th>
											<th>Airline</th>
											<th>Origin</th>
											<th>Destination</th>
											<th>BookingDate</th>
											<th>TravelDate</th>
											<th>NetPayable</th>
											<th>MarkUp</th>
											<th>FinalAmount</th>
											<th>BookingStatus</th>
											<th>PaymentStatus</th>
											<th>Agency</th>
											<!-- <th>SalesPersonName</th> -->
											<th>OrderId</th>
											<!-- <th>Download</th>
											<th>Email</th> -->
											  <th>Action</th>
											 <s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
													<th>SupplierName</th>
													</s:if>
											<!--  <th>ARR</th> -->
										</tr>
									</thead>
									<tbody>
										<s:iterator value="flightReportPage.items" status="rowCount">
											<tr>
												<td><s:property value="%{((flightReportPage.currentPageIndex - 1)*flightReportPage.maxItems)+#rowCount.count}"/></td>
												<td><s:property value="firstName" /> <s:property value="lastName" /></td>
												<td><s:property value="pnr" /></td>
												<td><s:property value="airline" /></td>
												<td><s:property value="origin" /></td>
													<td><s:property value="route" /></td>
												<td><s:property value="bookingDate" /></td>
													<td><s:property value="departureDate"/></td>
												<td><s:property value="netAmnt"/></td>
												<td><s:property value="markUp"/></td>
												<td><s:property value="finalPrice" /> 
												 </td>
													 <td><s:property value="status" /></td>
														<td><s:property value="paymentStatus"/></td>
														<td><s:property value="createdBy"/></td>
														  <%-- <s:if test="createdBy=='DirectUser'">
														 <td>B2C</td>
														</s:if>
														<s:else>
														  <td><s:property value="salesPersonName"/></td>
														</s:else> --%>
													 <td><s:property value="orderId"/></td>
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
														
													</p></td> --%>
													  
													 <%--  <td><p data-placement="top" >
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
														<a href="showPassengerDetails?id=<s:property value="id"/>&orderId=<s:property value="orderId"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Details </a>
															 <s:if test="%{#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false}">
									 						<s:if test="apiResponseMessage!=null">
															<span class="detailstiptext" ><s:property value="apiResponseMessage"/></span>  
															</s:if>
															<s:else>
															<span class="detailstiptext">No Response</span>  
															</s:else>
															</s:if>
															<s:else>
															<s:if test="apiResponseMessage!=null && apiResponseMessage=='Confirmed'">
															<span class="detailstiptext" ><s:property value="apiResponseMessage"/></span>  
															</s:if>
															<s:else>
															<span class="detailstiptext">Please Contact Tayyarah</span>  
															</s:else>
															</s:else>
											<%--   <span class="detailstiptext" ><s:property value="apiResponseMessage" /></span>   --%>
															 
													</p>

												</td>

												<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
													<td><s:property value="apiProvider"/></td>
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
												value="%{((flightReportPage.currentPageIndex - 1)*flightReportPage.maxItems)+1}" />
											to <s:property
												value="%{((flightReportPage.currentPageIndex*flightReportPage.maxItems) <= flightReportPage.availableItems)?(flightReportPage.currentPageIndex*flightReportPage.maxItems):flightReportPage.availableItems}" />
											of <s:property value="%{flightReportPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">

										<c:if test="${flightReportPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="flightReportPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="flightReportPage.currentPageIndex"
													value="${flightReportPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(flightReportPage.currentPageIndex) > 1? (flightReportPage.currentPageIndex) : 1}"
											end="${ (flightReportPage.currentPageIndex + 4) <= flightReportPage.availablePages ? (flightReportPage.currentPageIndex + 4) :  flightReportPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="flightReportPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${flightReportPage.currentPageIndex == i}">
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
											test="${(flightReportPage.currentPageIndex + 4) < flightReportPage.availablePages}">
											<td id="td"><button type="submit"
													name="flightReportPage.currentPageIndex"
													value="${flightReportPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="flightReportPage.currentPageIndex"
													value="${flightReportPage.availablePages}"
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
			<script>
		$(document).ready(function() {
			$("#twodt2").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth:true,
				changeYear:true
			});
			$("#twodt1").datepicker({
				dateFormat : "dd-mm-yy",
					changeMonth:true,
					changeYear:true
			 
			});
			
			
			$("#exctwodt1").datepicker({
				dateFormat : "dd-mm-yy",
					changeMonth:true,
					changeYear:true
			 
			});
			$("#exctwodt2").datepicker({
				dateFormat : "dd-mm-yy",
					changeMonth:true,
					changeYear:true
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
				/*  $("input[type=text], textarea").val(""); */
				 $('#filterform')[0].reset();
			});
 
		});
		  $(document).ready(function() {
				$('#twodt1').focus(function() {
					$(this).val('');
				});

				$('#twodt2').focus(function() {
					$(this).val('');
				});
				
				$('#exctwodt1').focus(function() {
					$(this).val('');
				});
				
				$('#exctwodt2').focus(function() {
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
	<script>
 $('#xlsdownload').click(function () { 
	 
	 var durga = [];
		 var result= $( 'label input[type="checkbox"]:checked' );
		  
		 if(result.length > 0){
			  durga = result.map(function(){
				 var resultStr = result;
				   resultStr= $(this).next('span').text(); 
				   var ds = result.prop( "checked" ); 
				   var a = {"text":resultStr, "checked":ds}; 
				   var resultobj = JSON.stringify(a);  
				   return resultobj;
			 }).get().join(', ');
			
		 }
 
		 else{
			 
			 $('#divresult').html("select any one checkbox to download");
		 } 
 
 }); 
</script>	

</body>
</html>