<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script> --%>
<script
	src="js/company_filter.js">
</script>
<link rel="stylesheet" href="css/pagination_css.css">
<!-- <link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> -->
<link rel="stylesheet" href="css/alert.css">



 
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "commonConfigList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();
		});
		$('#cancel').click(function() {
			$('#error-alert').hide();
		});
	});
	 
</script>
 
</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">

		<section class="content-header">
			<h1>Common Config List</h1>

		</section>

		<!-- Main content -->
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
			<form  action="commonConfigList" method="post" id="filterform">
	<div class="clearfix">
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
										<select class="form-control" name="commonConfigPage.maxItems"
											id="maxItems"   onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${commonConfigPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${commonConfigPage.maxItems != null && commonConfigPage.maxItems == maxItems}">
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
						<a class="btn btn-primary but btn-clean" href="goCommonConfig">
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
                  <label class="col-sm-12 control-label">Server Mode <s:property value="commonConfigPage.commonConfigFilter.mode"/></label>
                   <div class="col-sm-12">
                     <select class="form-control" name="commonConfigPage.commonConfigFilter.mode" required>
									<option value="ALL">ALL</option>
									<c:forEach var="item"
												items="${commonConfigPage.commonConfigFilter.commonConfigStatusQueue}">
												<c:choose>
													<c:when
														test="${commonConfigPage.commonConfigFilter.mode != null && commonConfigPage.commonConfigFilter.mode == item}">
														<option value="${item}" selected="selected">${item}</option>
													</c:when>
													<c:otherwise>
														<option value="${item}">${item}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>		   
											   
										  </select>
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
												name="commonConfigPage.currentPageIndex"
												value="${commonConfigPage.currentPageIndex}">Submit</button>
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
							<th>ServerMode</th>
							<th>Email(s)</th>
							 <th>Action</th>
							 <th>Status</th>
				 
							 
						</tr>
					</thead>
					<tbody>
						<s:iterator value="commonConfigPage.commonConfigList" status="rowCount" var="obj">
							<tr>
								 <td><s:property value="%{((commonConfigPage.currentPageIndex - 1)*commonConfigPage.maxItems)+#rowCount.count}" /></td>	
								<td><s:property value="serverMode" /></td>
								<td>
									<p data-placement="top">

										<a
											href="CommonConfigEmails?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-toggle="modal">Emails</a>
									</p>

								</td>
								
								
								 <td>
									<p data-placement="top" >

										<a
											href="commonConfigProfile?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-toggle="modal">Edit </a>
											
									</p>

								</td>
								<td>
								<s:if test="Active == true">
									<p data-placement="top" >
 									<a
											href="setCommonConfigLock?id=<s:property value="id"/>&Active=<s:property value="Active"/>"
											class="btn btn-success btn-xs" data-toggle="modal"><i class="fa fa-unlock"></i> UnLock
										</a> 
									</p>
									 </s:if>
									 <s:if test="Active == false">
									<p data-placement="top" >
 									<a
											href="setCommonConfigLock?id=<s:property value="id"/>&Active=<s:property value="Active"/>"
											class="btn btn-success btn-xs" data-toggle="modal"><i class="fa fa-unlock"></i>Lock
										</a> 
									</p>
									 </s:if>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				
					<table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((commonConfigPage.currentPageIndex - 1)*commonConfigPage.maxItems)+1}" />
											to <s:property
												value="%{((commonConfigPage.currentPageIndex*commonConfigPage.maxItems) <= commonConfigPage.availableItems)?(commonConfigPage.currentPageIndex*commonConfigPage.maxItems):commonConfigPage.availableItems}" />
											of <s:property value="%{commonConfigPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">
										<c:if test="${commonConfigPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="commonConfigPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="commonConfigPage.currentPageIndex"
													value="${commonConfigPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(commonConfigPage.currentPageIndex) > 1? (commonConfigPage.currentPageIndex) : 1}"
											end="${ (commonConfigPage.currentPageIndex + 4) <= commonConfigPage.availablePages ? (commonConfigPage.currentPageIndex + 4) :  commonConfigPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="commonConfigPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${commonConfigPage.currentPageIndex == i}">
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
											test="${(commonConfigPage.currentPageIndex + 4) < commonConfigPage.availablePages}">
											<td id="td"><button type="submit"
													name="commonConfigPage.currentPageIndex"
													value="${commonConfigPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="commonConfigPage.currentPageIndex"
													value="${commonConfigPage.availablePages}"
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
					"regex" : true,
				},
				"pagingType" : "full_numbers",
				"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],

			});
		});
	</script>
</body>
</html>







