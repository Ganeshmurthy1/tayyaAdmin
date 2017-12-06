<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/pagination_css.css">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<div class="sccuss-full-updated" id="success-alertdelete"
		style="display: none">
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="messagedelete"></div>
			<button type="button" id="successdelete" class="btn btn-primary">Ok</button>

		</div>
	</div>

	<section class="content-header">
		<h1>Airport List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>

	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->

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

		<div class="row">
			<form action="ShowAirportList" method="post" id="filterform">
				<div class="clearfix">
					<div class="col-sm-12">
						<div class="form-group" id="user_form-group">

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
													name="companyFilterPage.maxItems" id="maxItems"
													onchange="this.form.submit()">
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
											<a class="btn btn-primary but btn-clean" href="AddAirportPage">
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
															<label class="col-sm-12 control-label">Country
															</label>
															<div class="col-sm-12">
																<select class="form-control"
																	name="companyFilterPage.companyFilter.countryName"
																	id="country">

																	<option value="ALL">ALL</option>
																	 
																	  <c:forEach var="roleItem"
																		items="${airportsList}">
																		<c:choose>
																			<c:when
																				test="${companyFilterPage.companyFilter.countryName!= null && companyFilterPage.companyFilter.countryName == roleItem.country}">
																				<option value="${roleItem.country}" selected="selected">${roleItem.country}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${roleItem.country}">${roleItem.country}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>  
																</select>
															</div>
														</div>

													</div>
													
													
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Airport Name
															</label>
															<div class="col-sm-12">
																<select class="form-control"
																 	name="companyFilterPage.companyFilter.airportName"  
																	id="country">

																	<option value="ALL">ALL</option>
																	   <c:forEach var="roleItem"
																		items="${airportsList}">
																		<c:choose>
																			<c:when
																				test="${companyFilterPage.companyFilter.airportName!= null && companyFilterPage.companyFilter.airportName == roleItem.airport_name}">
																				<option value="${roleItem.airport_name}" selected="selected">${roleItem.airport_name}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${roleItem.airport_name}">${roleItem.airport_name}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach> 
																</select>
															</div>
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
					</div>

				</div>

				<div class="table-responsive dash-table">
					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>AirportCode</th>
								<th>AirportName</th>
								<th>City</th>
								<th>Country</th>
								<th>Edit</th>
								<th>Delete</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="companyFilterPage.airportList" status="rowCount">
								<tr>
									<td><s:property
											value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>
									<td><s:property value="airport_code" /></td>
									<td><s:property value="airport_name" /></td>
									<td><s:property value="city" /></td>
									<td><s:property value="country" /></td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="Airport_Edit?airport_code=<s:property value="airport_code" />"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit
											</a>
										</p>
									</td>

									<td>
										<p data-placement="top" title="Delete">
											<a href="javascript:void(0)"
												class="btn btn-danger btn-xs fa fa-trash"
												data-title="Delete" data-toggle="modal"
												data-target="#delete<s:property value="airport_code"/>"
												onclick="userDelete('<s:property value="airport_code"/>')">
											</a>
										</p>


										<div class="modal fade"
											id="delete<s:property value="airport_code"/>" tabindex="-1"
											role="dialog" aria-labelledby="edit" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">
															<span class="glyphicon glyphicon-remove"
																aria-hidden="true"></span>
														</button>
														<h4 class="modal-title custom_align" id="Heading">Delete
															this entry</h4>
													</div>
													<div class="modal-body">

														<div class="alert alert-danger">
															<span class="glyphicon glyphicon-warning-sign"></span>
															Are you sure you want to delete this Record?
														</div>

													</div>
													<div class="modal-footer ">
														<button type="button" class="btn btn-success"
															id="yes<s:property value="airport_code"/>">
															<span class="glyphicon glyphicon-ok-sign"></span> Yes
														</button>
														<button type="button" class="btn btn-default"
															data-dismiss="modal">
															<span class="glyphicon glyphicon-remove"></span> No
														</button>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div>


									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					<table id="pagtable">
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
								end="${(companyFilterPage.currentPageIndex + 4) <= companyFilterPage.availablePages ? (companyFilterPage.currentPageIndex + 4) :  companyFilterPage.availablePages }"
								var="i">
								<td>
									<button type="submit" name="companyFilterPage.currentPageIndex"
										value="${i}" class="btn btn-primary">
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
					</table>
				</div>
			</form>
		</div>
</div>

<!-- /.content -->

<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
	$(function() {
		/*   $("#mytable1").DataTable(); */
		$('#mytable').DataTable({
			"paging" :false,
			"lengthChange" :false,
			"searching" :false,
			"ordering" :false,
			"info" :false,
			"autoWidth" : false,
			"search" : {
				"regex" : true,
			},
			"pagingType" : "full_numbers",
			"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],

		});
	});
</script>

<script type="text/javascript">
		function userDelete(airport_code) {

			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "AirportDelete";
             console.log(airport_code);
			$("#yes" + airport_code)
					.click(
							function() {
								$("#delete" + airport_code).hide();
								$(".modal-backdrop").hide();
								$
										.ajax({
											method : "POST",
											url : finalUrl,
											data : {
												airport_code : airport_code
											},

											success : function(data, status) {
												 console.log(data);
												 console.log(status);
												if (data == "deleted") {
													$('#success-alertdelete')
															.show();
													$('#messagedelete')
															.text(
																	"Successfully deleted.");
													$('#successdelete')
															.click(
																	function() {
																		$(
																				'#success-alertdelete')
																				.hide();
																		window.location
																				.assign($(
																						location)
																						.attr(
																								'href'));
																	});

												} else if (data == "failed") {
													$('#error-alert').show();
													$('#message')
															.text(
																	"Failed.Try again.");
													$('#cancel')
															.click(
																	function() {
																		$(
																				'#error-alert')
																				.hide();

																	});
												}

											},
											error : function(data, status) {
												console.log(data);
												console.log(status);
											}
										});

							});

		}
		
		
	</script>

<script type="text/javascript">
		$(function() {
			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "ShowAirportList";
			$('#success').click(function() {
				window.location.assign(finalUrl);
				$('#success-alert').hide();

			});
			$('#cancel').click(function() {
				$('#error-alert').hide();

			});
		});
	</script>



