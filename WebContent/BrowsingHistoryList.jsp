<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%-- <link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<%-- <link href="css/jquery-uii.css" rel="stylesheet" type="text/css" /> --%>
<%-- 

<script src="js/angular.js" type="text/javascript"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
	
<script  src="js/jquery-ui-timepicker-addon.min.js"> 
</script>

 <link href="css/jquery-ui-timepicker-addon.min.css"
 rel="stylesheet" type="text/css" />

 --%>

<link rel="stylesheet" href="css/alert.css">

<script src="js/BrowsingHistoryUserName_Autocom.js"
	type="text/javascript">
	
</script>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Browsing History List</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<form class="form-inline" action="browsinghistories" method="post">
				<input type="hidden" name="userId" id="userId">


				<div class="clearfix">
					<div class="col-sm-12">
						<div id="user_form-group">

							<div class="col-sm-12">
								<div class="row">
									<div class="col-sm-4">
										<a class="btn btn-primary" role="button"
											data-toggle="collapse" href="#filters" aria-expanded="false"
											aria-controls="filters"> <i class="fa fa-filter"
											aria-hidden="true"></i> Filters
										</a>
									</div>

									<div class="col-sm-5  items">
										<div class="form-group clearfix">

											<div class="col-sm-6">
												<select class="form-control"
													name="browsingHistoryPage.maxItems" id="maxItems"
													onchange="this.form.submit()">
													<c:forEach var="maxItems"
														items="${browsingHistoryPage.pageSizeQueue}">
														<c:choose>
															<c:when
																test="${browsingHistoryPage.maxItems != null && browsingHistoryPage.maxItems == maxItems}">
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
 
								</div>
								<div class="collapse filter-box" id="filters">
									<div class="well">
										<div class="panel-body">
											<!-- Filter of main info -->
											<div class=" filter-text col-sm-12 clearfix">
												<div class="row">
													<div class="col-sm-2">
														<div class="form-group clearfix">

															<label class="col-sm-12 control-label">User Name</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="on"
																	class="form-control" id="searchByCreatedUser"
																	placeholder="user Name"
																	name="browsingHistoryPage.browsingHistory.User.Username"
																	value="<s:property value="browsingHistoryPage.browsingHistory.User.Username"/>">

																<input type="hidden" id="searchByCreatedUserId"
																	name="browsingHistoryPage.browsingHistory.User.UserId"
																	value="<s:property value="browsingHistoryPage.browsingHistory.User.UserId"/>">
																<input type="hidden" id="username"
																	name="browsingHistoryPage.browsingHistoryFilter.userName"
																	value="<s:property value="browsingHistoryPage.browsingHistory.User.Username"/>">
															</div>

														</div>

													</div>
													<div class="col-sm-2">
														<div class="form-group clearfix">

															<label class="col-sm-12 control-label">Page Name</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="on"
																	class="form-control" id="searchByPageName"
																	placeholder="page Name"
																	name="browsingHistoryPage.browsingHistoryFilter.pageName"
																	value="<s:property value="browsingHistoryPage.browsingHistoryFilter.pageName"/>">
																<input type="hidden" id="searchByPageId"
																	name="browsingHistoryPage.browsingHistoryFilter.pageId"
																	value="<s:property value="browsingHistoryPage.browsingHistoryFilter.pageId"/>">
															</div>

														</div>
													</div>


												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Date</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="twodpd1"
															placeholder="From Date(dd-mm-yyyy)"
															name="browsingHistoryPage.browsingHistoryFilter.dateFilter.dtStart">

													</div>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="twodpd2"
															placeholder="To Date(dd-mm-yyyy)"
															name="browsingHistoryPage.browsingHistoryFilter.dateFilter.dtEnd"
															value='<s:property value="browsingHistoryPage.browsingHistoryFilter.dateFilter.dtEnd"/>'>
													</div>
												</div>
											</div>

											<div class="col-sm-12">
												<div class="col-sm-6 clearfix cc-all">
													<a href="" id="reset" class="text-right"><i
														class="fa fa-close"></i> Clear All</a>
												</div>
												<div class="text-right filtr-btn col-sm-6 clearfix">
													<button type="submit" class="btn btn-primary"
														name="browsingHistoryPage.currentPageIndex"
														value="${browsingHistoryPage.currentPageIndex}">Submit</button>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="table-responsive dash-table">

					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<thead>
							<tr>
								<th>Sno</th>
								<th>Page Name</th>
								<th>Action</th>
								<th>User Name</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="browsingHistoryPage.browsingHistoryList"
								status="rowCount">
								<tr>

									<td><s:property
											value="%{((browsingHistoryPage.currentPageIndex - 1)*browsingHistoryPage.maxItems)+#rowCount.count}" /></td>
									<td><s:property value="pageName" /></td>
									<td><s:property value="actionName" /></td>
									<!-- actionName -->
									<td><s:property value="user.Username" /></td>
									<td><s:property value="createdAt" /></td>

								</tr>
							</s:iterator>
						</tbody>
					</table>
					<table id="pagtable">
						<tr id="tr">
							<span>Showing <s:property
									value="%{((browsingHistoryPage.currentPageIndex - 1)*browsingHistoryPage.maxItems)+1}" />
								to <s:property
									value="%{((browsingHistoryPage.currentPageIndex*browsingHistoryPage.maxItems) <= browsingHistoryPage.availableItems)?(browsingHistoryPage.currentPageIndex*browsingHistoryPage.maxItems):browsingHistoryPage.availableItems}" />
								of <s:property value="%{browsingHistoryPage.availableItems}" />
								items
							</span>

						</tr>
						<tr id="tr">

							<c:if test="${browsingHistoryPage.currentPageIndex>1}">
								<td id="td"><button type="submit"
										name="browsingHistoryPage.currentPageIndex" value="1"
										class="btn btn-primary">First</button></td>
								<td id="td"><button type="submit"
										name="browsingHistoryPage.currentPageIndex"
										value="${browsingHistoryPage.currentPageIndex - 1}"
										class="btn btn-primary">Prev</button></td>
							</c:if>

							<c:forEach
								begin="${(browsingHistoryPage.currentPageIndex) > 1? (browsingHistoryPage.currentPageIndex) : 1}"
								end="${ (browsingHistoryPage.currentPageIndex + 4) <= browsingHistoryPage.availablePages ? (browsingHistoryPage.currentPageIndex + 4) :  browsingHistoryPage.availablePages }"
								var="i">
								<td>
									<button type="submit"
										name="browsingHistoryPage.currentPageIndex" value="${i}"
										class="btn btn-primary">
										<c:choose>
											<c:when test="${browsingHistoryPage.currentPageIndex == i}">
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
								test="${(browsingHistoryPage.currentPageIndex + 4) < browsingHistoryPage.availablePages}">
								<td id="td"><button type="submit"
										name="browsingHistoryPage.currentPageIndex"
										value="${browsingHistoryPage.currentPageIndex + 1}"
										class="btn btn-primary">Next</button></td>
								<td id="td"><button type="submit"
										name="browsingHistoryPage.currentPageIndex"
										value="${browsingHistoryPage.availablePages}"
										class="btn btn-primary">Last</button></td>
							</c:if>

						</tr>
					</table>

				</div>
			</form>
		</div>
	</section>
</div>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
<link rel="stylesheet" href="css/alert.css">

<script src="js/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "BrowsingHistoryList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>
<script>
	$(document).ready(function() {
		$('#supplierName').focus(function() {
			$(this).val('');
		});

		$('#userName').focus(function() {
			$(this).val('');
		});

	});
</script>
<script type="text/javascript">
	$(function() {
		/*   $("#mytable1").DataTable(); */
		$('#mytable').DataTable({
			"paging" : false,
			"lengthChange" : false,
			"searching" : false,
			"ordering" : true,
			"info" : false,
			"autoWidth" : false,
			"search" : {
				"regex" : true,
			},
			"pagingType" : "full_numbers",
			"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],

		});
	});
</script>




 <script  src="js/jquery-ui-timepicker-addon.min.js"> 
</script>

 <link href="css/jquery-ui-timepicker-addon.min.css"
 rel="stylesheet" type="text/css" />




<script>
	/* $(document).ready(function() {
		$("#twodpd2").datepicker({
			dateFormat : "dd-mm-yy"
		});
		$("#twodpd1").datepicker({
			dateFormat : "dd-mm-yy"

		});

	});
	 */
	
	
	 $('#twodpd1').datepicker({
         dateFormat: 'dd-mm-yy',
         minDate:0,
         
      });
	 $('#twodpd2').datepicker({
         dateFormat: 'dd-mm-yy',
         minDate:0,
         onSelect: function(dateText, inst) {
             var todate = $(this).val();
            
            
         }
         
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
		$('#twodpd12').focus(function() {
			$(this).val('');
		});
		$('#twodpd22').focus(function() {
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

	});
</script>








