<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html >
<html>
<head>

 
 
<script src="js/frontuser_focus.js"></script>
 
<link rel="stylesheet" href="css/pagination_css.css">
 
<link rel="stylesheet" href="css/alert.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/angular.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {

		var id = $("#uniqueId").val();
		var stat = $("#stat").val();
		console.log("value of id" + id + "value " + stat);
		document.getElementById('Status' + id).value = stat;
	});
</script>
<title><s:property value="user" /></title>

</head>
<body>
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
			<h1>Deals List</h1>
			 
		</section>
		<section class="content">
			 
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
 		<form action="ShowListOfDeals" method="post" id="filterform">
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
											<a class="btn btn-primary but btn-clean"
												href="addDeals"> Add New <i
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
																<label class="col-sm-12 control-label">Deal
																	Type </label>
																<div class="col-sm-12">
																	<select class="form-control"
																		name="companyFilterPage.companyFilter.destinationType"
																		id="destinationType">

																		<option value="ALL">ALL</option>
																		<c:forEach var="roleItem"
																			items="${companyFilterPage.companyFilter.dealTypeQueue}">
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
														 
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Start Date</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="off"
																	class="form-control" id="twodpd1"
																		placeholder="Start Date(dd-mm-yyyy)"
																	name="companyFilterPage.companyFilter.Startdate"
																	value='<s:property value="companyFilterPage.companyFilter.Startdate"/>'>
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">End Date</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="off"
																	class="form-control" id="twodpd2"
																		placeholder="End Date(dd-mm-yyyy)"
																	name="companyFilterPage.companyFilter.Enddate"
																	value='<s:property value="companyFilterPage.companyFilter.Enddate"/>'>
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

					</div>




					<div class="table-responsive dash-table">
						<table id="mytable" class="table table-striped text-center"
							data-sort-name="name" data-sort-order="desc">

							<thead>

								<!--  <th><input type="checkbox" id="checkall" /></th> -->
								<tr>
									<th>S.No</th>
									<th>Price</th>
									<th>currency</th>
									<th>City</th>
									<th>Deal</th>
									<!--  -->
									<th>Preview</th>
									<th>Edit</th>
									<th>Publish</th>
									<th>Delete</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="companyFilterPage.crudOperationDealsList"
									status="rowCount">
								<tr>
									<td><s:property
											value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>

										<td><s:property value="price" /></td>
										<td><s:property value="currency" /></td>
										<td><s:property value="City" /></td>
										<td><s:property value="catagory" /></td>

										<td data-placement="top" title="CMS Details"><a
											href="CmsDetails?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-title="Update"> <span
												data-placement="top" class="fa fa-plus-circle"></span> <span></span></a>
										</td>
										<td>
										 <p data-placement="top" title="edit">

												<a href="Deals_Update?id=<s:property value="id" />"
													class="btn btn-success btn-xs" data-toggle="modal"> Edit
												</a>
											</p>
										</td>

										 <td>
										 <p data-placement="top" >
												<s:if test="Status==true">
												<a href="setCmsStatus?id=<s:property value="id" />&Status=<s:property value="Status"/>"
													class="btn btn-success btn-xs" data-toggle="modal">   Inactive
												</a>
												
												</s:if>
												<s:if test="Status==false">
												<a href="setCmsStatus?id=<s:property value="id" />&Status=<s:property value="Status"/>"
													class="btn btn-success btn-xs" data-toggle="modal"  style="background:red" >  Active
												</a>
												 </s:if>
											 
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
														<div class="modal-footer ">
															<button type="button" class="btn btn-success"
																id="yes<s:property value="id"/>">
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
				dateFormat : "yy-mm-dd"
			});
			$("#twodpd1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
		});
	</script>
	<script type="text/javascript">
		function userDelete(id) {

			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "deleteDealList";

			$("#yes" + id)
					.click(
							function() {
								$("#delete" + id).hide();
								$(".modal-backdrop").hide();
								$
										.ajax({
											method : "POST",
											url : finalUrl,
											data : {
												id : id
											},

											success : function(data, status) {
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
											error : function(e) {
												alert(e.message);
											}
										});

							});

		}
	</script>


	<script type="text/javascript">
		$(function() {
			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "ShowListOfDeals";
			$('#success').click(function() {
				window.location.assign(finalUrl);
				$('#success-alert').hide();

			});
			$('#cancel').click(function() {
				$('#error-alert').hide();

			});
		});
	</script>


	<script type="text/javascript">
		$(function() {

			$('#mytable').DataTable({
				"paging" : false,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : false,
				"info" : false,
				"autoWidth" : false,
				"search" : {
					"regex" : true,
				},
				"pagingType" : "full_numbers",
				"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],

			});
		});
		
		$(function() {
			 $( "#slider-1" ).slider({
		               range:true,
		               min: parseInt($('#minPriceDefault').val()),
		               max: parseInt($('#maxPriceDefault').val()),
		               values: [parseInt($('#minPrice').val()), parseInt($('#maxPrice').val())],
		               slide: function( event, ui ) {            	   
		                  $( "#price1" ).val("(" + ui.values[0] + ")" );
		                  $( "#pric1" ).val("(" + ui.values[1]  + ")" );   
		                  $( "#minPrice" ).val(ui.values[0]);
		                  $( "#maxPrice" ).val(ui.values[1]);   
		 				}
		           });
		         	$( "#price1").val("("+ $( "#slider-1" ).slider( 'values', 0 ) + ")" );
		            $( "#pric1" ).val("(" + $("#slider-1" ).slider( 'values', 1 ) + ")" );
		         });
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	