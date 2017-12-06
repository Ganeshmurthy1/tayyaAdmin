<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>

<%-- <link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
	
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>

<link rel="stylesheet" href="css/alert.css">
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />

<style>
.dsrform select.reportty,
.dsrform input.reportty{
width: 100%;
height: 20px; 
}

span.multiselect-native-select {
    position: relative
}

span.multiselect-native-select select {
    border: 0!important;
    clip: rect(0 0 0 0)!important;
    height: 1px!important;
    margin: -1px -1px -1px -3px!important;
    overflow: hidden!important;
    padding: 0!important;
    position: absolute!important;
    width: 1px!important;
    left: 50%;
    top: 30px
}

.multiselect-container {
    position: absolute;
    list-style-type: none;
    margin: 0;
    padding: 0
}

.multiselect-container .input-group {
    margin: 5px
}

.multiselect-container>li {
    padding: 0
}

.multiselect-container>li>a.multiselect-all label {
    font-weight: 700
}

.multiselect-container>li.multiselect-group label {
    margin: 0;
    padding: 3px 20px 3px 20px;
    height: 100%;
    font-weight: 700
}

.multiselect-container>li.multiselect-group-clickable label {
    cursor: pointer
}

.multiselect-container>li>a {
    padding: 0
}

.multiselect-container>li>a>label {
    margin: 0;
    height: 100%;
    cursor: pointer;
    font-weight: 400;
    padding: 3px 20px 3px 40px
}

.multiselect-container>li>a>label.radio,
.multiselect-container>li>a>label.checkbox {
    margin: 0
}

.multiselect-container>li>a>label>input[type=checkbox] {
    margin-bottom: 5px
}

.btn-group>.btn-group:nth-child(2)>.multiselect.btn {
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px
}

 .multiselect-container label.checkbox,
  .multiselect-container label.radio {
    padding: 3px 20px 3px 40px
}

 .multiselect-container li a label.checkbox input[type=checkbox],
  .multiselect-container li a label.radio input[type=radio] {
    margin-left: -20px;
    margin-right: 0
}



</style>
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
			<h1>Hotel DSR Report</h1>
			 
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
 			<form class="form-horizontal " name="myForm"  action="downloadHotelDisReport" >
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
        <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.guestName" type="checkbox"   value="true"  checked="checked" > <span>GuestName</span></label></div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.checkIn" type="checkbox"   value="true"  checked="checked" > <span>CheckIn</span></label></div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.checkOut" type="checkbox"   value="true"  checked="checked"  >  <span>CheckOut</span></label></div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.finalPrice" type="checkbox"   value="true"  checked="checked"  > <span>FinalPrice</span></label> </div></li>
            <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.invoiceNo" type="checkbox"   value="true"  checked="checked"  > <span>InvoiceNumber</span></label> </div></li>
                  <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.orderRef" type="checkbox"   value="true"  checked="checked"  > <span>OrderRef</span></label> </div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.statusAction" type="checkbox"  value="true"  checked="checked" > <span> StatusAction</span></label></div></li>
         <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.paymentStatus" type="checkbox"  value="true"  checked="checked"  > <span>paymentStatus</span></label></div></li>
           <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.agency" type="checkbox"  value="true"  checked="checked"  > <span>Agency</span></label></div></li>
                <li class="listitems"> <div class="checkbox"><label><input name="hotelandFlightDisReportProperty.hotelName" type="checkbox"  value="true"  checked="checked"  > <span>HotelName</span></label></div></li>
              
     </ul>
     <div class="clearfix text-right">
     <button type="submit" class="btn btn-success " id="xlsdownload" ><i class="fa fa-file-excel-o"></i> download</button>
      <input type="hidden" name="hotelandFlightDisReportProperty.travelType" value="H">
     </div>
 </div>

				</form> 
				</div>
				</div>
			</div>
			 
			<%-- </section>
		 <section class="content"> --%>
			<div class="row">
				<div class="col-sm-12 clearfix">
					<s:if test="%{#session.Company!=null && #session.User!=null}">

						<form action="getHotelDisReports" method="post" id="filterform">
				<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">
					<input type="hidden" name="showType"
					value="<s:property value="showType"/>"> 
							<div  >
								<div class="row">
									<div class="col-sm-8">
										<a class="btn btn-primary" role="button"
											data-toggle="collapse" href="#filters" aria-expanded="false"
											aria-controls="filters"> <i class="fa fa-filter"
											aria-hidden="true"></i> Filters
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
																	<option value="0">ALL</option>
																	 
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
																	name="hotelReportPage.hotelReportFilter.userName"
																	value="<s:property value="hotelReportPage.hotelReportFilter.userName"/>">
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
															<label class="col-sm-12 control-label">Email
																Address</label>
															<div class="col-sm-12">
																<input type="text" class="form-control" id="emailAddress"
																	autocomplete="off" placeholder="email"
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
																	name="hotelReportPage.hotelReportFilter.dateFilterBooking.dtStart"
																	value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterBooking.dtStart"/>'>

															</div>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="twodt2"
																	placeholder="To Date(dd-mm-yyyy)"
																	name="hotelReportPage.hotelReportFilter.dateFilterBooking.dtEnd"
																	value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterBooking.dtEnd"/>'>
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
																	name="hotelReportPage.hotelReportFilter.dateFilterCheckIn.dtStart"
																	value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterCheckIn.dtStart"/>'>

															</div>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="twoout3"
																	placeholder="To Date(dd-mm-yyyy)"
																	name="hotelReportPage.hotelReportFilter.dateFilterCheckIn.dtEnd"
																	value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterCheckIn.dtEnd"/>'>
															</div>
														</div>
													</div>

													<div class="col-sm-4">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Check-In
																Date</label>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="twocin2"
																	placeholder="From Date(dd-mm-yyyy)"
																	name="hotelReportPage.hotelReportFilter.dateFilterCheckOut.dtStart"
																	value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterCheckOut.dtStart"/>'>

															</div>
															<div class="col-sm-6">
																<input type="text" class="form-control" id="twocin3"
																	placeholder="To Date(dd-mm-yyyy)"
																	name="hotelReportPage.hotelReportFilter.dateFilterCheckOut.dtEnd"
																	value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterCheckOut.dtEnd"/>'>
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
											<thead>
												<tr>
													<th>S.No</th>
													<th>GuestName</th>
													<th>HotelName</th>
													<th>BookingDate</th>
													<th>CheckIn</th>
													<th>CheckOut</th>
													<th>NetPayable</th>
													<th>MarkUp</th>
													<th>FinalAmount</th>
													<th>BookingStatus</th>
													<th>Agency</th>
													<!-- <th>SalesPersonName</th> -->
													<th>OrderId</th>
													<!-- <th>Download</th>
													<th>Email</th> -->
													<th>Action</th>
													<s:if
														test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
														<th>SupplierName</th>
													</s:if>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="hotelReportPage.items" status="rowCount">
													<tr>
														<td><s:property
																value="%{((hotelReportPage.currentPageIndex - 1)*hotelReportPage.maxItems)+#rowCount.count}" /></td>
														<td><s:property value="firstname" />
															<s:property value="lastname" /></td>
														<td><s:property value="hotelName" /></td>
														<td><s:property value="bookingDate" /></td>
														<td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td>
														<td><s:property value="netAmnt"/></td>
															<td><s:property value="markUp"/></td>
														<td><s:property value="total" /></td>
														<td><s:property value="statusAction" /></td>
														<td><s:property value="createdBy" /></td>
														
													<%--  <s:if test="createdBy=='DirectUser'">
														 <td>B2C</td>
														</s:if>
														<s:else>
														  <td><s:property value="salesPersonName"/></td>
														</s:else>
														 --%>
														
														<td><s:property value="orderRef" /></td>
														<%-- <td>
 															<p data-placement="top" >
 															<s:if test="statusAction=='Confirmed'">
 															<a
															href="<s:text name="global.resourceApiUrl"/>getpdf/hotel?orderids=<s:property value="orderRef"/>"
															class="btn btn-success btn-xs"   data-toggle="modal">
															Voucher </a>
 															</s:if>
 															<s:else>
 															NIL
 															</s:else>													
 													</p>														 
														 </td>	
														  --%>
														<%--  <td>
 															<p data-placement="top" >
 															<s:if test="statusAction=='Confirmed'">
 															<a
															href="javascript:history.void(0);" onclick="sendHotelVoucherToCustomerEmail('${orderRef}');"
															class="btn btn-success btn-xs"   data-toggle="modal">
															Send Voucher To Email </a>
 															</s:if>
 															<s:else>
 															NIL
 															</s:else>													
 													</p>														 
														 </td>	 --%>
														 									
														<td>
															<p data-placement="top" class="details">
																<a
																	href="showReportDetails?selectedRowIndex=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">Details</a>
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
																	<%-- <s:if test="apiResponseMessage!=null}">
															<span class="detailstiptext" ><s:property value="apiResponseMessage" /></span>  
															</s:if>
															<s:else>
															<span class="detailstiptext">No Response</span>  
															</s:else> --%>
																 
															</p>
														</td>
														<s:if
															test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
															<td><s:property value="apiProvider.vendorName" /></td>
														</s:if>
													</tr>
												</s:iterator>
											</tbody>
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

<script src="js/bootstrap-multiselect.js"> </script>
			<script>
		$(document).ready(function() {
			$("#exctwodt2").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#exctwodt1").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			
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
			
		});
		
		$(document).ready(function() {
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
			$('#twocin2').focus(function() {
				$(this).val('');
			});
			$('#twocin3').focus(function() {
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

<!-- Initialize the plugin: -->
<script type="text/javascript">
    $(document).ready(function() {
        $('#example-getting-started').multiselect({
        	includeSelectAllOption: true
        });
    });
</script>

</body>
</html>