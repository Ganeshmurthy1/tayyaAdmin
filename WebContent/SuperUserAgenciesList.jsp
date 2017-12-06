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
		<h1>Agency List</h1>
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
			<form  action="AllAgenciesList" method="post" id="filterform">
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
													<label class="col-sm-12 control-label">Email
														 </label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="on"
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
											<%-- <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Employee
														Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="on"
															id="userIdSearch" placeholder="emp user name"
															name="companyFilterPage.companyFilter.userName"
															value="<s:property value="companyFilterPage.companyFilter.userName"/>">
													</div>
												</div>
											</div> --%>
 						
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
							<th>Company</th>
							 
							<th>Email</th>
						  	<th>Country</th>
						 	 <th>Expand</th>
							<th>Edit</th>
							<th>Status</th>
							<th>Lock</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="companyFilterPage.companyList" status="rowCount" >

							<tr>
								 <td><s:property value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>	
								<td class="shorten-text"><s:property value="Companyname" /></td>
								 
								  <td><s:property value="Email" /></td>
								 <td><s:property value="Countryname" /></td>
								 <td>
									<p data-placement="top" title="Company Details">
										<a
											href="companyDetails?companyid=<s:property value="companyid"/>"
											class="btn btn-success btn-xs" data-title="Update"> <span
											data-placement="top" ></span>View <span></span></a>
									</p>
								</td>
								<td>
									<p data-placement="top" title="Edit">

										<a
											href="superUserCompanyUpdate?companyid=<s:property value="companyid"/>&Email=<s:property value="Email"/>"
											class="btn btn-success btn-xs" data-toggle="modal">
											 Edit
										</a>
									</p>

								</td> 
								<td>
									<form action="setSuperUserComapanyStatus" method="post">
										<input type="hidden" name="companyid"
											value="<s:property value="companyid"/>" id="uniqueId">
										<input type="hidden" name="Status"
											value="<s:property value="Status"/>" id="Status"> 
											   
											    <s:if test="Status == true">
											  <!-- <div  class="locked text-danger" > -->
											   <i class="fa fa-check-circle success "></i>
											   Active
											  <%--  <input type="submit" value="Deactivate" id="stat<s:property value="Status"/>"> --%>
											 <!--  </div --> 
											   </s:if>
											  
											  <s:if test="Status == false">
											<!--   <div  class="unlocked" > -->
											
											 <i class="fa fa-times-circle red"></i> 
											 DeActive
											 <%-- <input type="submit" value="Activate" id="stat<s:property value="Status"/>"> --%>
											<!--  </div> -->
											 </s:if>
											
									</form>
								</td>
								<td>
									<form action="setAgencyLock" method="post">
										<input type="hidden" name="companyid"
											value="<s:property value="companyid"/>" id="companyid">
										<input type="hidden" name="Status"
											value="<s:property value="Status"/>" id="Status"> <input
											type="hidden" name="Locked"
											value="<s:property value="Locked"/>" id="Locked">
											 <s:if test="Locked == true">
											 <div  class="unlocked" >									 
												 <i class="fa fa-lock"></i>
											 
											 <input	type="submit" value="UnLock"	id="stat<s:property value="id"/>">
											 </div>
                                              </s:if>
                                              <s:if test="Locked == false">
                                              <div  class="locked" >
                                              <i class="fa fa-unlock"></i>
                                               <input type="submit"	value="Lock"	id="stat<s:property value="id" />"></div>
                                              </s:if>
 										</form>
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
		
</div>
 
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"AllAgenciesList";
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







