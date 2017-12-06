<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<script src="js/frontuser_focus.js"></script>
 
<link rel="stylesheet" href="css/pagination_css.css">
 <script type="text/javascript">
	$(document).ready(
			function() {
				var company_list = [];
				var agents_list = [];
				  var user_list = []; 
				 var cityMap=[];
				 var userMap=[];

				$.ajax({
					//Action Name
					url :"CompanyListUnderSuperUser",
					dataType : "json",
					data : {
					 parent_company_user_id : $("#companyUserId").val(),
						email : $("#email").val()
					},
					success : function(data, textStatus, jqXHR) {
						var items = data.records;
						for (var i = 0; i < data.records.length; i++) {
							//company_list.push(data.records[i].companyname +"("+data.records[i].company_userid+")"+","
							//		+ data.records[i].companyid);
							company_list.push(data.records[i].companyname);
							 var cityObj ={"key":data.records[i].companyid,"value":data.records[i].companyname}
	        				 cityMap.push(cityObj);
							 
						}
						console.log(company_list);
						//response(items);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});

				$("#companyName").autocomplete(
						{
							source : function(request, response) {
								var matcher = new RegExp('^'
										+ $.ui.autocomplete
												.escapeRegex(request.term),
										"i");
								response($.grep(company_list, function(item) {
									return matcher.test(item);

								}));
							},
							select : function(event, ui) {
								 $.map(cityMap, function(value, key) {
			          				    if(value.value==ui.item.label){
			          						console.log("value---"+value.value+"--------key----------"+value.key);
			          						$("#companyId").val(value.key);
			          					  }
			          				  
			          				});  
							 }
						});
				
				
				$.ajax({
					//Action Name
					url :"AgentsListUnderSuperUser",
					dataType : "json",
					data : {
					 parent_company_user_id : $("#companyUserId").val(),
						email : $("#email").val()
					},

					success : function(data, textStatus, jqXHR) {
 					for (var i = 0; i < data.agentList.length; i++) {
 						agents_list.push(data.agentList[i].username);
							/* agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")"  + ","
									+ data.agentList[i].id); */
									 var userObj ={"key":data.agentList[i].id,"value":data.agentList[i].username}
									userMap.push(userObj);
 							 
						}
						console.log("------agents_list------"+agents_list);
						 user_list=agents_list;
							console.log("------user_list------"+user_list);
						 userlist(user_list,userMap);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
				
			  });
	
	 function userlist(userlist,userMap)
			{
		  if(userlist.length>0){
				$("#agentName").autocomplete(
						{
		 				source : function(request, response) {
								var matcher = new RegExp('^'
										+ $.ui.autocomplete
												.escapeRegex(request.term),
										"i");
								response($.grep(userlist, function(item) {
									return matcher.test(item);

								}));
							},
							select : function(event, ui) {
							 $.map(userMap, function(value, key) {
		          				    if(value.value==ui.item.label){
		          						console.log("value---"+value.value+"--------key----------"+value.key);
		          						$("#userId").val(value.key);
		          					  }
		          				  
		          				});  
						 }
						});	 
		 }
		  
	}
 </script>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Front Users</h1>
		 
	</section>

	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
		
					<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">
			<form action="" method="post" id="filterform">
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

									<div class="col-sm-4 pull-right items">
										<div class="form-group clearfix">
										<button type="button" class="btn btn-success" id="xlsdownload">
											<i class="fa fa-file-excel-o"></i> download
									</button>
									&nbsp;&nbsp;&nbsp;
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

									 
								</div>
								<div class="collapse filter-box" id="filters">
									<div class="well">
										<div class="panel-body">
											<!-- Filter of main info -->
											<div class=" filter-text col-sm-12 clearfix">
												<div class="row">
												
												<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Report Type</label>
													<div class="col-sm-12">

														<input type="hidden" id="reportTypeHidden"
															value="<s:property value="companyFilterPage.companyFilter.reportType"/>">
														<select class="form-control"
															name="companyFilterPage.companyFilter.reportType"
															id="reportType" required>
															<option value="1">My Reports</option>
															<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
															</s:if>
															<s:else>
															<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
															<option value="0">ALL</option>
															<s:if
																test="%{#session.Company.companyRole.isDistributor()}">
																<option value="4">My Agency(s)</option>
															</s:if>
															<s:if
																test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
																<%-- <option value="3">My
																	<s:text name="global.Wholesaler"></s:text>(s)
																</option> --%>
																<option value="8">All Corporate(s)</option>
																<option value="4">My Agency(s)</option>
																<option value="6">All
																	<s:text name="global.Wholesaler"></s:text>(s)
																</option>
																<option value="7">All Agency(s)</option>
															</s:if>
														   </s:if>	
														   </s:else>
														</select>
													</div>
												</div>
											</div>
											<s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Company Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="companyName" placeholder="type company Name"
															name="companyFilterPage.companyFilter.companyName"
															value="<s:property value="companyFilterPage.companyFilter.companyName"/>">
															<input type="hidden"  id="companyId"  name="companyFilterPage.companyFilter.companyId" value="<s:property value="companyFilterPage.companyFilter.companyId"/>">
															 
													</div>
												</div>
											</div>
										</s:if>		
											 
												
												
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">First
																Name</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="off"
																	class="form-control" id="firstName"
																	placeholder="First Name"
																	name="companyFilterPage.companyFilter.firstName"
																	value="<s:property value="companyFilterPage.companyFilter.firstName"/>">
															</div>
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Last
																Name</label>
															<div class="col-sm-12">
																<input type="text" class="form-control"
																	autocomplete="off" id="lastName"
																	placeholder="Last Name"
																	name="companyFilterPage.companyFilter.LastName"
																	value="<s:property value="companyFilterPage.companyFilter.LastName"/>">
															</div>
														</div>
													</div>
													 
													 <div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Mobile</label>
															<div class="col-sm-12">
																<input type="text" class="form-control"
																	autocomplete="off" id="mobile"
																	placeholder="Mobile no"
																	name="companyFilterPage.companyFilter.mobile"
																	value="<s:property value="companyFilterPage.companyFilter.mobile"/>">
															</div>
														</div>
													</div>
													 
													 <div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Email</label>
															<div class="col-sm-12">
																<input type="text" class="form-control"
																	autocomplete="off" id="email"
																	placeholder="Email"
																	name="companyFilterPage.companyFilter.email"
																	value="<s:property value="companyFilterPage.companyFilter.email"/>">
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
												<div class="col-sm-12">
													<div class="col-sm-6 clearfix cc-all">
														<a href="" id="reset" class="text-right"><i
															class="fa fa-close"></i> Clear All</a>
													</div>
													<div class="text-right filtr-btn col-sm-6 clearfix">
														<button type="button" class="btn btn-primary" id="filterSubmit"
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
								<th>FirstName</th>
								<th>LastName</th>
								<th>Email</th>
								<th>Country</th>
								<th>Mobile</th>
								 <th>View</th> 
							</tr>
						</thead>
						<tbody>
							<s:iterator value="companyFilterPage.frontUserDetailList"
								status="rowCount">
								<tr>
									<td><s:property
											value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>
									<td><s:property value="firstName" /></td>
									<td><s:property value="lastName" /></td>
									<td><s:property value="email" /></td>
									<td><s:property value="country" /></td>
									<td><s:property value="mobile" /></td>
									  <td> <a href="showFrontUserDetails?frontUserid=<s:property value="id"/>" class="btn btn-success btn-xs " >
															View Details </a>   </td>   

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
	</section>
</div>

<!-- /.content -->

<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">
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
	
	$('#xlsdownload')
	.click(function() {
		 
				$("#filterform").attr("action","FrontUserReportExcelDownload");
				$("#filterform").submit();

			});
	$('#filterSubmit').click(function () {
		$("#filterform").attr("action","showFrontUsers");
		$("#filterform").submit();
	});
	</script>




