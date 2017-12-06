<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script> --%>
	<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script> --%>
<script
	src="js/company_filter.js">
</script>

<!-- <link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css">
	<link href="css/jquery-uii.css" rel="stylesheet" type="text/css" /> -->
  <style>
 .ui-autocomplete{
 height:auto;
 }
</style>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Hotel Travel Trips</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		 
	 
		<div class="row">
			<form  action="superUserCompanyList" method="post" id="filterform">
<%-- 		<div class="clearfix">
		<div class="col-sm-12">
			<div class="form-group" id="user_form-group" >
			 
				 		<div class="col-sm-12">

						<div class="row">
							<div class="col-sm-4">
								<a class="btn btn-primary" role="button" data-toggle="collapse"
									href="#filters" aria-expanded="false" aria-controls="filters">
									<i class="fa fa-filter" aria-hidden="true"></i> Filters
								</a>
							</div>

							<div class="col-sm-5  items">
								<div class="form-group clearfix">

									<div class="col-sm-6">
										<select class="form-control" name="companyFilterPage.maxItems"
											id="maxItems"   onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${companyFilterPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${companyFilterPage.maxItems != null && companyFilterPage.maxItems == maxItems}">
														<option value="${maxItems}" selected="selected">${maxItems}</option>
													</c:when>
													<c:otherwise>
														<option value="${maxItems}">${maxItems}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-5 control-label text-left">Items
										Per Page </label>

								</div>
							</div>
							
							<div class="col-sm-3 clearfix pull-right">
					<div class="add-new">
						<a class="btn btn-primary but btn-clean" href="addCompany">
							Add New <i class="fa fa-angle-right"></i>
						</a>
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
													<label class="col-sm-12 control-label">Company Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="search" placeholder="company Name"
															name="companyFilterPage.companyFilter.companyName"
															value="<s:property value="companyFilterPage.companyFilter.companyName"/>">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking Type
														 </label>
													<div class="col-sm-12">
														<select class="form-control"
															name="companyFilterPage.companyFilter.companyRoleType"
															id="companyRoleType">

															<option value="ALL">ALL</option>
															<c:forEach var="roleItem"
																items="${companyFilterPage.companyFilter.bookingTypeQueue}">
																<c:choose>
																	<c:when
																		test="${companyFilterPage.companyFilter.companyRoleType != null && companyFilterPage.companyFilter.companyRoleType == roleItem}">
																		<option value="${roleItem}" selected="selected">${roleItem}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${roleItem}">${roleItem}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											
											 
											 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Email
														 </label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="off"
															id="companyEmail" placeholder="email"
															name="companyFilterPage.companyFilter.email"
															value="<s:property value="companyFilterPage.companyFilter.email"/>">
													</div>
												</div>
											</div>
											 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Phone Number
														 </label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="off"
															id="phone" placeholder="Phone Number"
															name="companyFilterPage.companyFilter.phone"
															value="<s:property value="companyFilterPage.companyFilter.phone"/>">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label"><s:text name="global.KPPPno" ></s:text>
														 </label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="off"
															id="pan_number" placeholder="<s:text name="global.KPPPno" ></s:text>"
															name="companyFilterPage.companyFilter.panNumber"
															value="<s:property value="companyFilterPage.companyFilter.panNumber"/>">
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
										<input name="pageId" type="text" value="7">
										<input name="actionId" type="text" value="22">
										<input name="statusCode" type="text" value="0">
										
										
										<div class="text-right filtr-btn col-sm-6 clearfix">
											<button type="submit" class="btn btn-primary"
												name="companyFilterPage.currentPageIndex"
												value="${companyFilterPage.currentPageIndex}">Submit</button>
										</div>
									</div>
 
								</div>
							</div>
						</div>
					</div>
				
				 
			</div>
			</div>
			 </div> --%>
			
			 <div class="table-responsive dash-table">
 			<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
 
					<thead>
 							<tr>
							<th>S.No</th>
							<th>EmpName</th>
							<th>TRNo</th>
						  	<th>CheckIn</th>
						 	 <th>CheckOut</th>
						 	  <th>Trips</th>
						 	   <th>Quotation</th>
						 	   <th>Request</th>
							<!-- <th>Create</th>
							 <th>List</th>
							 <th>Edit</th>
							 <th>View</th> -->
						  
						</tr>
					</thead>
					<tbody>
						<s:iterator value="hotelQuotationRowlist" status="rowCount" >

							<tr>
								   <td><s:property value="%{#rowCount.count}" /></td>	
								<td ><s:property value="empName" /></td>
								  <td><s:property value="TRNo" /></td>
								  <td><s:property value="checkIn" /></td>
								   <td><s:property value="checkOut" /></td>
								     <td></td>
								      <td>
								      <p data-placement="top" title="Create">
										<a
											href="goHotelRequestTravelQuotation?hotelQuotationRequestId=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-toggle="modal">
											 Create
										</a>
										<a
											href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-toggle="modal">
											 List
										</a>
									</p>      
								      </td>
								     <td>
									<p data-placement="top" title="Create">
										<a
											href="hotelTravelRequestEdit?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-toggle="modal">
											 Edit
										</a>
										<a
											href="hotelOrderQuotationView?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-toggle="modal">
											 View
										</a>
									</p>      
									 
									    
								 <td>
									 
 								</tr>
						</s:iterator>
					</tbody>

				</table>
				
				<%-- <table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+1}" />
											to <s:property
												value="%{((companyFilterPage.currentPageIndex*companyFilterPage.maxItems) <= companyFilterPage.availableItems)?(companyFilterPage.currentPageIndex*companyFilterPage.maxItems):companyFilterPage.availableItems}" />
											of <s:property value="%{companyFilterPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">

										<c:if test="${companyFilterPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="companyFilterPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="companyFilterPage.currentPageIndex"
													value="${companyFilterPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(companyFilterPage.currentPageIndex) > 1? (companyFilterPage.currentPageIndex) : 1}"
											end="${ (companyFilterPage.currentPageIndex + 4) <= companyFilterPage.availablePages ? (companyFilterPage.currentPageIndex + 4) :  companyFilterPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="companyFilterPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${companyFilterPage.currentPageIndex == i}">
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
											test="${(companyFilterPage.currentPageIndex + 4) < companyFilterPage.availablePages}">
											<td id="td"><button type="submit"
													name="companyFilterPage.currentPageIndex"
													value="${companyFilterPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="companyFilterPage.currentPageIndex"
													value="${companyFilterPage.availablePages}"
													class="btn btn-primary">Last</button></td>
										</c:if>

									</tr>
								</table> --%>
								</div>
				
				</form>
			 
			</div>
</section>
		</div>
		
 
 
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">
<script type="text/javascript">/* , 'pdf', 'print'  */
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
						buttons : [ 'excel']
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
		</script>
  





