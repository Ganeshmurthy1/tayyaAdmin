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
		var finalUrl = newUrl + "c_ConfigList";
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
		  var  reportType=document.getElementById('reportTypeHidden').value;
		  if(reportType==null || reportType==""){
			  document.getElementById('reportType').value = "ALL";  
		  }
		  else{
			  document.getElementById('reportType').value = reportType;  
		  }
		 });
	</script>

</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">

		<section class="content-header">
			<h1>Company Configurations List</h1>

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
			<form  action="c_ConfigList" method="post" id="filterform">
	<div class="clearfix">
		<div class="col-sm-12">
			<div class="form-group" id="user_form-group" >
				<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId">
					 <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">
			
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
						<a class="btn btn-primary but btn-clean" href="addNewCompanyConfig">
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
															id="search" placeholder="company  Name"
															name="companyFilterPage.companyFilter.companyName"
															value="<s:property value="companyFilterPage.companyFilter.companyName"/>">
													</div>
												</div>
											</div>
											 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Config Name
														 </label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="off"
															id="configName" placeholder="Config Name"
															name="companyFilterPage.companyFilter.configName"
															value="<s:property value="companyFilterPage.companyFilter.configName"/>">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Company Type</label>
                   <div class="col-sm-12">
                     <input type="hidden" id="reportTypeHidden"  value="<s:property value="companyFilterPage.companyFilter.companyType"/>">
                     <select class="form-control" name="companyFilterPage.companyFilter.companyType"
											id="reportType">
											 <option value="ALL">ALL</option>
											<s:if test="%{#session.Company.companyRole.isDistributor()}">
											<option value="<s:text name="global.Wholesaler" ></s:text>"><s:text name="global.Wholesaler" ></s:text></option>
											<option value="agency">Agency</option>
											</s:if>
											<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
											<option value="Super Agency">Super Agency</option>
											<option value="<s:text name="global.Wholesaler" ></s:text>"><s:text name="global.Wholesaler" ></s:text></option>
											<option value="agency">Agency</option>
											<option value="<s:text name="global.Corporate" ></s:text>"><s:text name="global.Corporate" ></s:text></option>
											  
											 </s:if>											 
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
							<th>CompanyName</th>
							<th>CompanyType</th>
							<!-- <th>PaymntType</th> -->
							<th>ConfigName</th>
							<th>Update</th>
							<th>LockStatus</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="companyFilterPage.companyConfigList" status="rowCount" var="">
							<tr>
								 <td><s:property value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>	
								<td><s:property value="CompanyName" /></td>
								<td><s:property value="companyType" /></td>
								<%-- <td><s:property value="paymntType" /></td> --%>
								<td><s:property value="configname" /></td>
								<td>
									<p data-placement="top" >
									<%-- 	<c:if test="${companyConfigType.b2E==true}">
										yogesh
										</c:if>
										<c:if test="${companyConfigType.b2E==false}"> --%>
										<a
											href="companyConfigProfile?config_id=<s:property value="config_id"/>"
											class="btn btn-success btn-xs" data-toggle="modal"> Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${companyConfigType.whitelable}">
												<a href="editWhitelableConfigProfile?configId=<s:property value="config_id"/>"
													class="btn btn-success btn-xs" data-toggle="modal"> Edit WhiteLable</a>
											</c:if>
										<%-- </c:if> --%>
									</p>

								</td>
								<td>
							
								
								<s:if test="Active == true">
									<p data-placement="top" >
 									<a
											href="setCompanyConfigLock?config_id=<s:property value="config_id"/>&Active=<s:property value="Active"/>"
											class="btn btn-success btn-xs" data-toggle="modal"><i class="fa fa-unlock"></i> Lock
										</a>
									</p>
									 </s:if>
								<s:else>
								<p data-placement="top" >
 									<a
											href="setCompanyConfigLock?config_id=<s:property value="config_id"/>&Active=<s:property value="Active"/>"
											class="btn btn-success btn-xs" data-toggle="modal"><i class="fa fa-lock"></i> UnLock
										</a>
									</p>
								 </s:else>
								 <%-- <form action="setCompanyConfigLock" method="post">
										<input type="hidden" name="config_id"
											value="<s:property value="config_id"/>" id="config_id">
										<input type="hidden" name="Active"
											value="<s:property value="Active"/>" id="Active">
										<input type="hidden" name="isActive" value="<s:property value="Active"/>" id="Active">
										<s:if test="Active == true">
											<input type="submit" value="Lock"
												id="stat<s:property value="config_id"/>">
										</s:if>
										<s:else>
											<input type="submit" value="UnLock"
												id="stat<s:property value="config_id"/>">
										</s:else>
									</form> --%>
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







