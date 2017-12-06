
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			<i class="fa fa-bug"></i>Bug List
		</h1>
		<div class="breadcrumb-wrapper">
			<%-- <span class="label">You are here:</span> --%>
			<ol class="breadcrumb">
				<li><a href="addBugTracker">Add Bug</a></li>
				<li  class="active">Bug List</li>
			</ol>
		</div>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="col-sm-12 clearfix">
					<!--  report-search -->
					<input type="hidden"
						value="<s:property value="%{#session.Company.company_userid}"/>"
						id="companyUserId"> <input type="hidden"
						value="<s:property value="%{#session.Company.Email}"/>" id="email">
					<input type="hidden"
						value="<s:property value="%{#session.User.company_userid}"/>"
						id="user_id">
						<input type="hidden"
						value="${bugReportPage.items}"
						id="buglist">
						<form action="goBugTrackerList" method="post" id="filterform">
							<input type="hidden" name="showType"
					value="<s:property value="showType"/>">
						<input type="hidden" id="companyType"
							value='<s:property value="filterCompanyType"/>'> 
						<input type="hidden" id="status"
							value='<s:property value="status"/>'> <input
							type="hidden"
							value="<s:property value="%{#session.Company.company_userid}"/>"
							id="user_companyUserId" name="user_companyUserId">
						<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-6">
									<a class="btn btn-primary" role="button" data-toggle="collapse"
										href="#filters" aria-expanded="false" aria-controls="filters">
										<i class="fa fa-filter" aria-hidden="true"></i> Filters
									</a>
								</div>
								<div class="col-sm-3 pull-right items">
								<label class="col-sm-4 control-label ">Items
											Per Page </label>
									<div class="form-group clearfix">
									
									
										<div class="col-sm-6">
											<select class="form-control" name="bugReportPage.maxItems"
												id="maxItems" required onchange="this.form.submit()">
												<c:forEach var="maxItems"
													items="${bugReportPage.pageSizeQueue}">
													<c:choose>
														<c:when
															test="${bugReportPage.maxItems != null && bugReportPage.maxItems == maxItems}">
															<c:choose>
																<c:when test="${bugReportPage.maxItems == -1}">
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
														<label class="col-sm-12 control-label">BugTracker History</label>
														<div class="col-sm-12">

															<input type="hidden" id="reportTypeHidden"
																value="<s:property value="bugReportPage.flightReportFilter.reportType"/>">
															<select class="form-control" name="bugReportPage.flightReportFilter.reportType" id="reportType" required>
																<option value="1" selected="selected">My Bug History</option>
																<option value="0">All</option>
																
															</select>

														</div>
													</div>
												</div>
												
												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Assigned By</label>
														<div class="col-sm-12">
															<input type="text" class="form-control input-sm"
															 placeholder="assigned by" autocomplete="off" id="assignby" > 
							  								<input type="hidden" name="bugReportPage.assignedBy"    id="techHeadId" > 
								
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Assigned To</label>
														<div class="col-sm-12">
															<input type="text" class="form-control input-sm" id="assignTo"  placeholder="assigned to" autocomplete="off">
								  							<input type="hidden" name="bugReportPage.assignTo" id="techSupportId"> 
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
													name="bugReportPage.currentPageIndex"
													value="${bugReportPage.currentPageIndex}">Submit</button>
											</div>
										</div>

									</div>
								</div>
							</div>

						</div>
						

					<div class="table-responsive dash-table">
					<table id="mytable1" class="table table-striped table-bordered "style="white-space:nowrap;" 
						data-sort-name="name" data-sort-order="assc">
						<thead style="width:auto;padding: 0px; margin: 0px">
							<tr>
								<th>S.No</th>
								<th>Title</th>
								<th>Reported By</th>
								<th>Assigned By</th>
								<th>Assigned To</th>
								<th>Assigned Date</th>
								<th>Created Date</th>
								<th>Action</th>
								<th>View Test Cases</th>
							</tr>
						</thead>
						
						  <tbody>
						<s:iterator value="bugReportPage.items" var="bugHistory" status="varStatus" >
						<tr>
						<td>${varStatus.count}</td>
						<td>${title}</td>
						<td>${CreatedByName}</td>
						<td>${assignedByName}</td>
						<td>${assignedToName}</td>
						<td>
						<c:choose>
							<c:when test="assignDate!=null">
									<s:date name="assignDate" format="dd-MM-YYYY"/>
							</c:when>
							<c:otherwise>
							 NA
							</c:otherwise>
						</c:choose>
						</td>
						<td>${createDate}</td>
						<td>
										<p data-placement="top">
											<a href="viewBugTracker?id=${bugTracker.id}"
												class="btn btn-success btn-xs">View</a>
										</p>
									</td>
								
									<td>
										<p data-placement="top">
											<a href="viewTestCases?id=${bugTracker.id}"
												class="btn btn-success btn-xs">View</a>
										</p>
									</td>
						</tr>
						 </s:iterator>
						 </tbody>
						 
						 
						 
						 </table> 
						 </div>
						 
						

								<table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((bugReportPage.currentPageIndex - 1)*bugReportPage.maxItems)+1}" />
											to <s:property
												value="%{((bugReportPage.currentPageIndex*bugReportPage.maxItems) <= bugReportPage.availableItems)?(bugReportPage.currentPageIndex*bugReportPage.maxItems):bugReportPage.availableItems}" />
											of <s:property value="%{bugReportPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">

										<c:if test="${bugReportPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="bugReportPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="bugReportPage.currentPageIndex"
													value="${bugReportPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(bugReportPage.currentPageIndex) > 1? (bugReportPage.currentPageIndex) : 1}"
											end="${ (bugReportPage.currentPageIndex + 4) <= bugReportPage.availablePages ? (bugReportPage.currentPageIndex + 4) :  bugReportPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="bugReportPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${bugReportPage.currentPageIndex == i}">
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
											test="${(bugReportPage.currentPageIndex + 4) < bugReportPage.availablePages}">
											<td id="td"><button type="submit"
													name="bugReportPage.currentPageIndex"
													value="${bugReportPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="bugReportPage.currentPageIndex"
													value="${bugReportPage.availablePages}"
													class="btn btn-primary">Last</button></td>
										</c:if>

									</tr>
								</table>
</form>
							</div>
	</section>
</div>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
<script>
	$(document).ready(function() {
		$('#supplierName').focus(function() {
			$(this).val('');
		});

	});
</script>
