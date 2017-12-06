<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>
<script type="text/javascript">
$(function() {
	  var  reportType=document.getElementById('reportTypeHidden').value;
	   document.getElementById('reportType').value = reportType;
	  	 
});
</script>
<script src="js/load_company_emp_names.js"></script>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<s:if test="invoiceType==0">
				<h1>My Flight Invoice List</h1>
			</s:if>
			<s:else>
				<h1>Flight Agent Invoice List</h1>
			</s:else>

		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
					<input type="hidden"
						value="<s:property value="%{#session.Company.company_userid}"/>"
						id="companyUserId"> <input type="hidden"
						value="<s:property value="%{#session.Company.Email}"/>" id="email">
					<input type="hidden"
						value="<s:property value="%{#session.User.company_userid}"/>"
						id="user_id">
					<s:if test="invoiceType==0">
						<form action="getMyInvoiceList" method="post" id="filterform">
					</s:if>
					<s:else>
						<form action="getSuperAgentCommInvoiceList" method="post"
							id="filterform">
					</s:else>
					<input type="hidden" id="companyType"
						value='<s:property value="filterCompanyType"/>'> <input
						type="hidden" id="airline" value='<s:property value="airline"/>'>
					<input type="hidden" id="status"
						value='<s:property value="status"/>'> <input type="hidden"
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
										<select class="form-control" name="flightReportPage.maxItems"
											id="maxItems" required onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${flightReportPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${flightReportPage.maxItems != null && flightReportPage.maxItems == maxItems}">
														<c:choose>
															<c:when test="${flightReportPage.maxItems == -1}">
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
											<%-- <c:forEach var="maxItems" items="${flightReportPage.pageSizeQueue}" >
												<c:choose>
												<c:when test="${flightReportPage.maxItems != null && flightReportPage.maxItems == maxItems}">
													  <option value="${maxItems}"
													  selected = "selected" >${maxItems}</option>
												</c:when>				
												<c:otherwise>
													<option value="${maxItems}">${maxItems}</option>						
												</c:otherwise>
												</c:choose>
												 </c:forEach>	 --%>
										</select>
									</div>
									<label class="col-sm-5 control-label text-left">Items
										Per Page </label>

								</div>
							</div>

						</div>


						<div class="collapse filter-box" id="filters">
							<div class="well">
								<div class="clearfix">

									<!-- Filter of main info -->
									<div class=" filter-text col-sm-12 clearfix">
										<div class="row">

											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Order Type</label>
													<div class="col-sm-12">

														<input type="hidden" id="reportTypeHidden"
															value="<s:property value="flightReportPage.flightReportFilter.reportType"/>">

														<select class="form-control"
															name="flightReportPage.flightReportFilter.reportType"
															id="reportType" required>
															<s:if
																test="%{#session.Company.companyRole.isDistributor()}">
																<s:if test="invoiceType==0">
																	<option value="1">Mine</option>
																</s:if>
																<s:else>
																	<option value="0">ALL</option>
																	<option value="4">My Agency(s)</option>
																</s:else>

															</s:if>
															<s:if
																test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
																<option value="0">ALL</option>
																<%-- <option value="3">My <s:text name="global.Wholesaler" ></s:text>(s)</option> --%>
																<option value="8">All Corporate(s)</option>
																<option value="4">My Agency(s)</option>
																<option value="6">All
																	<s:text name="global.Wholesaler"></s:text>(s)
																</option>
																<option value="7">All Agency(s)</option>
															</s:if>
														</select>


													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Order ID</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="off" class="form-control"
															id="orderId" placeholder="type company Name"
															name="flightReportPage.orderId"
															value="<s:property value="flightReportPage.orderId"/>">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Invoice
														Number</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="off" class="form-control"
															id="invoiceNo" placeholder="type company Name"
															name="flightReportPage.invoiceNo"
															value="<s:property value="flightReportPage.invoiceNo"/>">
													</div>
												</div>
											</div>

											<s:if test="invoiceType==0">
											</s:if>
											<s:else>
												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Company
															Name</label>
														<div class="col-sm-12">
															<input type="text" autocomplete="on" class="form-control"
																id="companyName" placeholder="type company Name"
																name="flightReportPage.flightReportFilter.companyName"
																value="<s:property value="flightReportPage.flightReportFilter.companyName"/>">
														<input type="hidden" id="companyId"
																	name="flightReportPage.flightReportFilter.companyId"
																	value="<s:property value="flightReportPage.flightReportFilter.companyId"/>">
														
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
														<input type="hidden"  id="userId"  name="flightReportPage.flightReportFilter.userId"     value="<s:property value="flightReportPage.flightReportFilter.userId"/>">
															
														</div>
													</div>
												</div>
											</s:else>

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
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">From Date</label> <input
														type="text" class="form-control" id="twodt1"
														placeholder="From Date(dd-mm-yyyy)"
														name="flightReportPage.flightReportFilter.dateFilterBooking.dtStart"
														value='<s:property value="flightReportPage.flightReportFilter.dateFilterBooking.dtStart"/>'>

												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">To Date</label> <input
														type="text" class="form-control" id="twodt2"
														placeholder="To Date(dd-mm-yyyy)"
														name="flightReportPage.flightReportFilter.dateFilterBooking.dtEnd"
														value='<s:property value="flightReportPage.flightReportFilter.dateFilterBooking.dtEnd"/>'>


												</div>

											</div>

											<%--  <div class="col-sm-4">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Arrival Date</label>
                    <div class="col-sm-6">
                      <input type="text" class="form-control" id="arrivalDate1"
											placeholder="From Date" name="flightReportPage.flightReportFilter.dateFilterArrival.dtStart"
											value='<s:property value="flightReportPage.flightReportFilter.dateFilterArrival.dtStart"/>'>
                      </div>
                       <div class="col-sm-6">
                      <input type="text" class="form-control" id="arrivalDate2"
											placeholder="To Date" name="flightReportPage.flightReportFilter.dateFilterArrival.dtEnd"
											value='<s:property value="flightReportPage.flightReportFilter.dateFilterArrival.dtEnd"/>'>
                    </div> 
                  </div>
                </div> --%>
											<%-- 
                <div class="col-sm-4">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Departure Date</label>
                    <div class="col-sm-6">
                    <input type="text" class="form-control" id="departureDate1"
											placeholder="From Date" name="flightReportPage.flightReportFilter.dateFilterDeparture.dtStart"
											value='<s:property value="flightReportPage.flightReportFilter.dateFilterDeparture.dtStart"/>'>
                     
                    </div>
                     <div class="col-sm-6">
                    <input type="text" class="form-control" id="departureDate2"
											placeholder="To Date" name="flightReportPage.flightReportFilter.dateFilterDeparture.dtEnd"
											value='<s:property value="flightReportPage.flightReportFilter.dateFilterDeparture.dtEnd"/>'>
                      </div>  
                  </div>
                </div> --%>
										</div>
									</div>
									<%-- <div class="slider col-sm-12 clearfix">
            <div class="row">
 				 <div class="col-sm-3">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Total Price </label>
                    <div class="col-sm-12">
                     <div id="slider-1"></div>

                         <div class="col-sm-6">
                        <label class="col-sm-12 control-label">Min</label>
                          <div class="col-sm-12">
                           <input type="hidden" id="minPrice" name="flightReportPage.flightReportFilter.amountRangeBooking.amountMin" value='<s:property value="flightReportPage.flightReportFilter.amountRangeBooking.amountMin"/>'  class="form-control" >
                          
                           <input type="hidden" id="minPriceDefault" value='<s:property value="flightReportPage.flightReportFilter.amountRangeBooking.amountMinDefault"/>'  class="form-control" >
                          
                          
                              <input type="text"  id="price1" class="form-control" >
                          </div>
                      </div>
                       <div class="col-sm-6 text-right">
                          <label class="col-sm-12 control-label">Max</label>
                            <div class="col-sm-12">
                             <input type="hidden" id="maxPrice" name="flightReportPage.flightReportFilter.amountRangeBooking.amountMax" value='<s:property value="flightReportPage.flightReportFilter.amountRangeBooking.amountMax"/>'  class="form-control" >
                          
                             <input type="hidden" id="maxPriceDefault" value='<s:property value="flightReportPage.flightReportFilter.amountRangeBooking.amountMaxDefault"/>'  class="form-control" >
                             
                                 <input type="text" id="pric1" class="form-control"  >
                            </div>
                       </div>
                    </div>
                    
                  </div>
                </div>

               
                </div> 
           </div>
           
            --%>
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


					<%--  
				<form class="form-inline" action="searchSuperAgentCommInvoiceList"
							method="post">
							<div class="form-group" id="fromDateDiv">
								<label for="exampleInputAmount">From Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd1"
										placeholder="yyyy-mm-dd" name="fromDate"
										value='<s:property value="fromDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>

							<div class="form-group" id="endDateDiv">
								<label for="exampleInputAmount">End Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="yyyy-mm-dd" name="toDate"
										value='<s:property value="toDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
							<div class="form-group" id="user_form-group">
								<label for="Country">Company Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="companyType"
										id="filterCompanyType" autocomplete="off" required>
										<option value="mine">My Commission</option>
										<option value="all">ALL</option>
										<option value="dis"><s:text name="global.Wholesaler" ></s:text></option>
										<option value="agency">Agency</option>
									</select>
								</div>
							</div>

							<div class="form-group" id="user_form-group">
								<label for="Country">Report Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="period" id="period"
										autocomplete="off" required>
										<option value="0">select type</option>
										<option value="month">Month</option>
										<option value="week">Week</option>


									</select>
								</div>
							</div>

							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Show</button>
							</div>

					  --%>
					<div class="table-responsive dash-table">

						<!-- testing -->

						<div class="box clearfix">

							<table id="example" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>S.No</th>
										<th>OrderId</th>
										<th>Invoice Number</th>
										<th>TotalAmount</th>
										<th>BookedDate</th>
										<th>StatusAction</th>
										<th>PayStatus</th>
										<th>Agency</th>

									</tr>
								</thead>
								<tbody>

									<s:iterator value="flightReportPage.invoiceitems"
										status="rowCount">

										<tr>
											<td><s:property
													value="%{((flightReportPage.currentPageIndex - 1)*flightReportPage.maxItems)+#rowCount.count}" /></td>
											<td>
												<p data-placement="top" title="Generate Invoice">

													<a
														href="generateSuperAgentInvoice?invoiceType=${invoiceType}&id=<s:property value="id"/>&orderId=<s:property value="orderId"/>&userId=<s:property value="userId"/>"
														class="btn btn-success btn-xs" data-toggle="modal"> <s:property
															value="orderId" />
													</a>
												</p>

											</td>
											<td><s:property value="invoiceNo" /></td>
											<td><s:property value="finalPrice" /> <s:property
													value="curCode" /></td>

											<td><s:property value="bookingDate" /></td>
											<td><s:property value="status" /></td>
											<td><s:property value="paymentStatus" /></td>
											<td><s:property value="createdBy" /></td>

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
	<script type="text/javascript" src="js/reset-form.js"></script>
	<script>
		$(document).ready(function() {
			$("#twodt2").datepicker({
				dateFormat : "dd-mm-yy",
				 changeMonth: true,
				 changeYear: true 
			});
			$("#twodt1").datepicker({
				dateFormat : "dd-mm-yy",
					 changeMonth: true,
					 changeYear: true 
			 
			});
		});
		
		 $(document).ready(function() {
				$('#twodt1').focus(function() {
					$(this).val('');
				});
				$('#twodt2').focus(function() {
					$(this).val('');
				});	
		  });
	
		 
	</script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						lengthChange : false,
						"paging" : false,
						"searching" : false,
						 "info": false,
						/* "pagingType" : "full_numbers", */
						/* 	"lengthMenu" : [ 10, 25, 50, 75, 100, 500,100 ], */
						buttons : [ 'excel']
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});

		 
	</script>
	<script type="text/javascript">
		$(function() {
			/*  $('#row_dim').hide();  */
			$('#period').change(function() {
				//alert($('#user').val());
				if ($('#period').val() == '0') {
					$('#fromDateDiv').show();
					$('#endDateDiv').show();
				} else {
					$('#fromDateDiv').hide();
					$('#endDateDiv').hide();
					if($("#twodpd1").val()!=""){
						$("#twodpd1").val("");
					}
					if($("#twodpd2").val()!=""){
						$("#twodpd2").val("");
					}
					 
				}
			});
 
		});
	</script>
</body>
</html>