<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="css/alert.css">
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>

<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> --%>

<script src="js/company_filter.js">
	
</script>
<link rel="stylesheet" href="css/pagination_css.css">


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="sccuss-full-updated" id="success-alert"
		style="display: none">
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="message"></div>
			<button type="button" id="success" class="btn btn-primary">Ok</button>

		</div>
	</div>

	<div class="sccuss-full-updated" id="error-alert" style="display: none">
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="message"></div>
			<button type="button" id="cancel" class="btn btn-primary">Ok</button>

		</div>
	</div>
	<section class="content-header">
		<h1>
			Hotel Markup List
		</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<form class="form-inline" action="hotelMarkupList" method="post">
				<div class="clearfix">
					<div class="col-sm-12">
						<div class="form-group" id="user_form-group">
							<input type="hidden"
								value="<s:property value="%{#session.Company.company_userid}"/>"
								id="companyUserId"> <input type="hidden"
								value="<s:property value="%{#session.Company.Email}"/>"
								id="email"> <input type="hidden"
								value="<s:property value="%{#session.User.company_userid}"/>"
								id="user_id">
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
											<a class="btn btn-primary but btn-clean"
												href="addHotelMarkup"> Add New <i
												class="fa fa-angle-right"></i>
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
															<label class="col-sm-12 control-label">Parent Company</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="on"
																	class="form-control" id="toCreate"
																	placeholder="Parent company"
																	name="companyFilterPage.companyFilter.createdByCompanyName"
																	value="<s:property value="companyFilterPage.companyFilter.createdByCompanyName"/>">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Child Company</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="on"
																	class="form-control" id="search"
																	placeholder="Child company"
																	name="companyFilterPage.companyFilter.companyName"
																	value="<s:property value="companyFilterPage.companyFilter.companyName"/>">
															</div>
														</div>
													</div>
													
													
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">ConfigName </label>
															<div class="col-sm-12">
																<input type="text" class="form-control"
																	autocomplete="on" id="configname"
																	placeholder="Config Name"
																	name="companyFilterPage.companyFilter.configName"
																	value="<s:property value="companyFilterPage.companyFilter.configName"/>">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Destination
																Type </label>
															<div class="col-sm-12">
																<select class="form-control"
																	name="companyFilterPage.companyFilter.destinationType"
																	id="destinationType">

																	<option value="ALL">ALL</option>
																	<c:forEach var="roleItem"
																		items="${companyFilterPage.companyFilter.destinationTypeQueue}">
																		<c:choose>
																			<c:when
																				test="${companyFilterPage.companyFilter.destinationType != null && companyFilterPage.companyFilter.destinationType == roleItem}">
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
													<div class="date col-sm-12 clearfix">
														<div class="row">
															<div class="col-sm-4">
																<div class="form-group clearfix">
																	<label class="col-sm-12 control-label">Created
																		Date</label>
																	<div class="col-sm-6">
																		<input type="text" class="form-control" id="twodpd1"
																			placeholder="From Date(dd-mm-yyyy)"
																			name="companyFilterPage.companyFilter.dateFilterCreated.dtStart"
																			value='<s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtStart"/>'>

																	</div>
																	<div class="col-sm-6">
																		<input type="text" class="form-control" id="twodpd2"
																			placeholder="To Date(dd-mm-yyyy)"
																			name="companyFilterPage.companyFilter.dateFilterCreated.dtEnd"
																			value='<s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtEnd"/>'>
																	</div>
																</div>
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

				<div class="table-responsive dash-table">

					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>

							<tr>
								<th>S.No</th>
								<th>ChildCompany</th>
								<th>ParentCompany</th>
								<th>MarkupName</th>
								<th>ConfigName</th>
								<th>DestinationType</th>
								<th>HotelChain</th>
								<th>CheckInDate</th>
								<th>CheckOutDate</th>
								<th>City</th>
							<!-- 	<th>Country</th> -->
								<th>Update</th>
								<th>Delete</th>
							
							</tr>
						</thead>
						<tbody>
							<s:iterator value="companyFilterPage.hotelMarkupList" status="rowCount">
								<tr>
									<td><s:property value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>	
									<td><s:property value="companyName" /></td>
									<s:if test="createdByCompanyName!=null">
									<td><s:property value="createdByCompanyName" /></td>
									</s:if>
									<s:else>
									<td>----</td>
									</s:else>
									<td><s:property value="name" /></td>
									<td><s:property value="configname" /></td>
									
											<td><s:property value="destinationType" /></td>
									<td><s:property value="hotelChain" /></td>

									<td><s:property value="checkIn" /></td>
									<td><s:property value="checkOut" /></td>

									<td><s:property value="hotelCity" /></td>
									<%-- <td><s:property value="hotelCountry" /></td> --%>

									<td>
										<p data-placement="top" title="edit">
											<a href="hoteMarkupProfile?id=<s:property value="id"/>"
												class="btn btn-success btn-xs" data-toggle="modal"> Edit</a>
										</p>

									</td>


									<td>
										<p data-placement="top" title="Delete">
											<a href="javascript:void(0)"
												class="btn btn-danger btn-xs fa fa-trash"
												data-title="Delete" data-toggle="modal"
												data-target="#delete<s:property value="id"/>"
												onclick="userDelete('<s:property value="id"/>')"> </a>
										</p>


										<div class="modal fade" id="delete<s:property value="id"/>"
											tabindex="-1" role="dialog" aria-labelledby="edit"
											aria-hidden="true">
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
													
													<div class="modal-footer">
														<button type="button" class="btn btn-success"
															id="yes<s:property value="id"/>">
															<span class="glyphicon glyphicon-ok-sign"></span>�Yes
														</button>
														<button type="button" class="btn btn-default"
															data-dismiss="modal">
															<span class="glyphicon glyphicon-remove"></span>�No
														</button>
													</div>
												</div>
												
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div>
										
									
                                    
									
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
								end="${ (companyFilterPage.currentPageIndex + 4) <= companyFilterPage.availablePages ? (companyFilterPage.currentPageIndex + 4) :  companyFilterPage.availablePages }"
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
	</section>
</div>

<%@ include file="DashboardFooter.jsp"%>
<script>
		$(document).ready(function() {
			$("#twodpd2").datepicker({
				dateFormat : "dd-mm-yy"
			});
			$("#twodpd1").datepicker({
				dateFormat : "dd-mm-yy"
			/*  changeMonth: true,
			 changeYear: true */
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
			"ordering" : false,
			"info" : false,
			"autoWidth" : false,
			"search" : {
				"regex" : false,
			},
			"pagingType" : "full_numbers",
			"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],

		});
	});
</script>

<script type="text/javascript">
	function userDelete(id) {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "deleteHotelMarkup";

		$("#yes" + id).click(function() {
			$("#delete" + id).hide();
			$(".modal-backdrop").hide();
			console.log("markup id--------" + id)
			$.ajax({
				method : "POST",
				url : finalUrl,
				data : {
					id : id
				},
				success : function(data, status) {
					if (data == "deleted") {
						/* alert("Successfully deleted.");
						 window.location.assign($(location).attr('href')); */
						$('#success-alert').show();
						$('#message').text("Successfully deleted.");
						$('#success').click(function() {
							$('#success-alert').hide();
							window.location.assign($(location).attr('href'));
						});

					} else if (data == "failed") {
						//alert("Failed.Try again.");
						$('#error-alert').show();
						$('#message').text("Failed.Try again.");
						$('#cancel').click(function() {
							$('#error-alert').hide();

						});
					}

				},
				error : function(xhr, status, error) {
					$('#error-alert').show();
					$('#message').text(error);
					$('#cancel').click(function() {
						$('#error-alert').hide();

					});
					console.log(error);
					// alert(e.message);
				}
			});

		});

	}
</script>
 