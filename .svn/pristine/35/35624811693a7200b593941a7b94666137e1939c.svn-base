<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Desiya Config List</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<form class="form-inline" action="apiProviderList" method="post">
				<%-- <div class="clearfix">
					<div class="col-sm-12">
						<div  id="user_form-group">
						
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
													name="apiProviderDesiyaConfigs.maxItems" id="maxItems"
													onchange="this.form.submit()">
													<c:forEach var="maxItems"
														items="${apiProviderDesiyaConfigs.pageSizeQueue}">
														<c:choose>
															<c:when
																test="${apiProviderDesiyaConfigs.maxItems != null && apiProviderDesiyaConfigs.maxItems == maxItems}">
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
												href="addApiProvider"> Add New <i
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
															<label class="col-sm-12 control-label">Supplier Name
																</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="off"
																	class="form-control" id="supplierName"
																	placeholder="Supplier Name"
																	name="apiProviderDesiyaConfigs.apiProviderFilter.supplierName"
																	value="<s:property value="apiProviderDesiyaConfigs.apiProviderFilter.supplierName"/>">
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
														name="apiProviderDesiyaConfigs.currentPageIndex"
														value="${apiProviderDesiyaConfigs.currentPageIndex}">Submit</button>
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
							<th>Title</th>
								<th>ApiMode</th>
							<th>Company</th>
							<th>Status</th>
							 <th>Action(s)</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="apiProviderDesiyaConfigs" status="rowCount">
							<tr>
							<%-- <td><s:property value="%{((apiProviderDesiyaConfigs.currentPageIndex - 1)*apiProviderDesiyaConfigs.maxItems)+#rowCount.count}" /></td> --%>
								<td>${rowCount.count}</td>
								<td>${title}</td>
								<td>${environment}</td>
								<td>${companyName}</td>
								<td><span >
                 <label class="switch"> <%-- ${desiyaActive} --%><input type="checkbox"  name="active"  data-id="${id}"
                  class="desiyaActive" <c:if test="${active==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								
								  <td>
									<p data-placement="top">
										<a href="editDesiyaConfig?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" >Edit
											 </a>
											 <p data-placement="top">
										<%-- <a href="deleteDesiyaConfig?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" >Delete
											 </a> --%>
									</p>
									 
								</td>
								
								 <%-- <td>
									<p data-placement="top">
										<a href="deleteDesiyaConfig?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" >Delete
											 </a>
									</p>
								</td> --%>
								
								
							</tr>
						</s:iterator>
					</tbody>
				</table>
								<%-- <table id="pagtable"  >
				 <tr id="tr">
				 		 <span>Showing <s:property value="%{((apiProviderDesiyaConfigs.currentPageIndex - 1)*apiProviderDesiyaConfigs.maxItems)+1}" /> to <s:property value="%{((apiProviderDesiyaConfigs.currentPageIndex*apiProviderDesiyaConfigs.maxItems) <= apiProviderDesiyaConfigs.availableItems)?(apiProviderDesiyaConfigs.currentPageIndex*apiProviderDesiyaConfigs.maxItems):flightReportPage.availableItems}" /> of <s:property value="%{apiProviderDesiyaConfigs.availableItems}" /> items</span>
				 
				 </tr>
				 <tr id="tr">
			 
			 	<c:if test="${apiProviderDesiyaConfigs.currentPageIndex>1}">
			 		<td id="td"><button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="1" class="btn btn-primary">First</button></td>			 
			 		<td id="td"><button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="${apiProviderDesiyaConfigs.currentPageIndex - 1}" class="btn btn-primary">Prev</button></td>		
				</c:if>
					 
			 	<c:forEach begin="${(apiProviderDesiyaConfigs.currentPageIndex) > 1? (apiProviderDesiyaConfigs.currentPageIndex) : 1}" end="${ (apiProviderDesiyaConfigs.currentPageIndex + 4) <= apiProviderDesiyaConfigs.availablePages ? (apiProviderDesiyaConfigs.currentPageIndex + 4) :  apiProviderDesiyaConfigs.availablePages }" var="i">						
					<td>
					<button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="${i}" class="btn btn-primary" >
					<c:choose>
								<c:when test="${apiProviderDesiyaConfigs.currentPageIndex == i}">
									 <u>${i}</u>
								</c:when>

								<c:otherwise>
									${i}								
								</c:otherwise>
					</c:choose>
					</button>
					</td>						
				</c:forEach>
				<c:if test="${(apiProviderDesiyaConfigs.currentPageIndex + 4) < apiProviderDesiyaConfigs.availablePages}">
			 		<td id="td"><button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="${apiProviderDesiyaConfigs.currentPageIndex + 1}" class="btn btn-primary">Next</button></td>	
			 		<td id="td"><button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="${apiProviderDesiyaConfigs.availablePages}" class="btn btn-primary">Last</button></td>
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
<script src="js/common_config_status_update.js" type="text/javascript"></script>





