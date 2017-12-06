<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#th, #td {
	text-align: left;
	padding: 8px;
}
#pagtable {
	border-collapse: collapse;
	width: auto;
	float: right;
}
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	/* add padding to account for vertical scrollbar */
}
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}

.items label {
	margin-top: 5px;
}
</style>
<link rel="stylesheet" href="css/alert.css">
<script type="text/javascript">
		$(function() {
		  var  reportType=document.getElementById('reportTypeHidden').value;
			 document.getElementById('reportType').value = reportType;
		});
	</script>
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
		<h1>Look Book List</h1>
	</section>
	<!-- Main content -->
	<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
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
			<form action="BusBookAndSearchView" method="post" id="filterform">
			<input type="hidden" name="showType"
					value="<s:property value="showType"/>">
				<div class="clearfix">
					<div class="col-sm-12">
						<div class="form-group" id="user_form-group">
							
						</div>
					</div>
				</div>
				<div class="col-sm-12">
						<div class="row">
							<div class="col-xs-6">
								<a class="btn btn-primary" role="button" data-toggle="collapse"
									href="#filters" aria-expanded="false" aria-controls="filters">
									<i class="fa fa-filter" aria-hidden="true"></i> Filters
								</a>
							</div>

							<div class="col-xs-6 pull-right items">
								<div class="form-group clearfix">

									<div class="col-sm-6">
										<select class="form-control" name="lookBookFilterPage.maxItems"
											id="maxItems" required onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${lookBookFilterPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${lookBookFilterPage.maxItems != null && lookBookFilterPage.maxItems == maxItems}">
													 <c:choose>
													 <c:when
														test="${lookBookFilterPage.maxItems == -1}">
													 <option value="${maxItems}" selected="selected">ALL</option>
													</c:when>
													 
													<c:otherwise>
														<option value="${maxItems}" selected="selected">${maxItems}</option>
													</c:otherwise>
													</c:choose>
													</c:when>
													 
													<c:otherwise>
													 <c:choose>
													 <c:when
														test="${maxItems == -1}">
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
									<label class="col-sm-5 control-label text-left">Items
										Per Page </label>

								</div>
							</div>

						</div>


						<div class="collapse filter-box" id="filters">
							
								<div class="panel-body">



									<!-- Filter of main info -->
									<div class=" filter-text col-sm-12 clearfix">
										<div class="row">
										
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Report Type</label>
													<div class="col-sm-12">

														<input type="hidden" id="reportTypeHidden"
															value="<s:property value="lookBookFilterPage.lookBookReportFilter.reportType"/>">
														<select class="form-control"
															name="lookBookFilterPage.lookBookReportFilter.reportType"
															id="reportType" required>
															<option value="1">My LookBook</option>
															<option value="0">ALL LookBooks</option>
															
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
															name="lookBookFilterPage.lookBookReportFilter.companyName"
															value="<s:property value="lookBookFilterPage.lookBookReportFilter.companyName"/>">
															<input type="hidden"  id="companyId"  name="lookBookFilterPage.lookBookReportFilter.companyId"     value="<s:property value="lookBookFilterPage.lookBookReportFilter.companyId"/>">
															 
													</div>
												</div>
											</div>
										</s:if>		


											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Company Type</label>
													<div class="col-sm-12">
													<select name="lookBookFilterPage.lookBookReportFilter.companyType"
															value="<s:property value="lookBookFilterPage.lookBookReportFilter.companyType"/>">
														<s:if test="lookBookFilterPage.lookBookReportFilter.companyType!=null">
													<option value="<s:property value="lookBookFilterPage.lookBookReportFilter.companyType"/>" selected="selected"><s:property value="lookBookFilterPage.lookBookReportFilter.companyType"/></option>
													</s:if>
													<option value="All">All</option>
														<option value="B2B">B2B</option>
														<option value="B2C">B2C</option>
														<option value="B2E">B2E</option>
														<option value="WhiteLabel">WhiteLabel</option>
													</select>
															<input type="hidden"  id="configId"  name="lookBookFilterPage.lookBookReportFilter.configId"     value="<s:property value="lookBookFilterPage.lookBookReportFilter.configId"/>">
															 
													</div>
												</div>
										</div>


											

											
												

										<div class="col-sm-6 clearfix cc-all">
											<a href="" id="reset" class="text-right"><i
												class="fa fa-close"></i> Clear All</a>
												<button type="submit" 
												name="lookBookFilterPage.currentPageIndex"
												value="${lookBookFilterPage.currentPageIndex}">Submit</button>
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
								<th>Company Name</th>
								<th>AppKey</th>
								<th>Total Search Count</th>
								<th>Total Booking Count</th>
								<th>View</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="lookBookFilterPage.items" status="rowCount">
								<tr>
									<td><s:property value="%{#rowCount.count}" /></td>
									<td><s:property value="companyName" /></td>
									<td><s:property value="appkey" /></td>
									<td><s:property value="totalSearchCount" /></td>
									<td><s:property value="totalBookedCount" /></td>

									<td>
										<p data-placement="top">
											<a href="BusLookList?configIds=<s:property value="configId"/>&searchType=bus&companyId=<s:property value="companyId"/>"
												class="btn btn-success btn-xs" > Look
											</a> <a href="BusBookList?configIds=<s:property value="configId"/>&searchType=bus&companyId=<s:property value="companyId"/>"
												class="btn btn-success btn-xs"> Book
											</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					<table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((lookBookFilterPage.currentPageIndex - 1)*lookBookFilterPage.maxItems)+1}" />
											to <s:property
												value="%{((lookBookFilterPage.currentPageIndex*lookBookFilterPage.maxItems) <= lookBookFilterPage.availableItems)?(lookBookFilterPage.currentPageIndex*lookBookFilterPage.maxItems):lookBookFilterPage.availableItems}" />
											of <s:property value="%{lookBookFilterPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">

										<c:if test="${lookBookFilterPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="lookBookFilterPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="lookBookFilterPage.currentPageIndex"
													value="${lookBookFilterPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(lookBookFilterPage.currentPageIndex) > 1? (lookBookFilterPage.currentPageIndex) : 1}"
											end="${ (lookBookFilterPage.currentPageIndex + 4) <= lookBookFilterPage.availablePages ? (lookBookFilterPage.currentPageIndex + 4) :  lookBookFilterPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="lookBookFilterPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${lookBookFilterPage.currentPageIndex == i}">
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
											test="${(lookBookFilterPage.currentPageIndex + 4) < lookBookFilterPage.availablePages}">
											<td id="td"><button type="submit"
													name="lookBookFilterPage.currentPageIndex"
													value="${lookBookFilterPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="lookBookFilterPage.currentPageIndex"
													value="${lookBookFilterPage.availablePages}"
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
<script type="text/javascript"  src="js/reset-form.js"></script>
<%-- <script type="text/javascript">
	$(document).ready(function() {
		$("#twodt1").keyup(function() {
			$(this).next("#twodt2").focus();
		});

	});
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "superUserCompanyList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script> --%>

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
<script type="text/javascript">/* , 'pdf', 'print'  */
		$(document).ready(
				function() {
					var currentDate = "export-data_"+$.datepicker.formatDate('yyyy-mm-dd', new Date())+(new Date).getTime();
					$('#example').DataTable( {
						dom: 'Bfrtip',
						"paging" : false,
						"info" : false,
						"searching" : false,
						 
					buttons: [
					            {
					                extend: 'csv',
					                exportOptions: {
					                	columns: "thead th:not(.noExport)"
					                },
					                filename: currentDate
					            },
					            'colvis'
					        ]
						        
					} );

				});
 
	</script>
<script type="text/javascript">
		$(function() {
			/*  $('#row_dim').hide();  */
			

			$('#companyName').change(function() {
				//alert($('#companyName').val());
				if ($('#companyName').val() == 'ALL') {
					$('#user_form-group').hide();
				} else if ($('#companyName').val() == '0') {
					$('#user_form-group').show();

				} else {
					$('#user_form-group').hide();
				}
			});
			 
			 
		});
	</script>
	<script>
	 $('spna[data-toggle="tooltip"]').tooltip({
    animated: 'fade',
    placement: 'bottom',
});
	</script>
	 

