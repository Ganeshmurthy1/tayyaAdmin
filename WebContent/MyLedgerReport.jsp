<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<%-- <script src="js/wallet_userids_filter.js">
 </script> --%>
 <script src="js/alluser_list_by companyid.js">
 </script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title><s:property value="user" /></title>
<link rel="stylesheet" href="css/pagination_css.css">
<style type="text/css">
#example td
{
padding:0px;
}
</style>
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
			 
				
			  });
	 
 </script>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Ledger Report</h1>
		</section>
		<section class="content">
			<div class="row">
				 <form action="myLedgerReport" method="post" id="filterform">
				 <input type="hidden"
									value="<s:property value="%{#session.Company.company_userid}"/>"
									id="companyUserId"> <input type="hidden"
									value="<s:property value="%{#session.Company.Email}"/>"
									id="email"> <input type="hidden"
									value="<s:property value="%{#session.User.company_userid}"/>"
									id="user_id"> 
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
													 <c:choose>
													 <c:when
														test="${companyFilterPage.maxItems == -1}"> 
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
										<s:if test="%{#session.Company.companyRole.isSuperUser()}">
										<div class="col-sm-2 clearfix pull-right">
										<div class="add-new">
											<a class="btn btn-primary but btn-clean" href="goPayment">
												Add Payment<i class="fa fa-angle-right"></i>
											</a>
										</div>
									</div>
										</s:if>
									

									</div>
									<div class="collapse filter-box" id="filters">
										<div class="well">
											<div class="panel-body">
												<div class=" filter-text col-sm-12 clearfix">
													<div class="row">
														<div class="col-sm-2">
															<div class="form-group clearfix">
																<label class="col-sm-12 control-label">UserName</label>
																<div class="col-sm-12">
																	<input type="text" autocomplete="on"
																		class="form-control" id="userName"
																		placeholder="user name"
																		name="companyFilterPage.companyFilter.userName"
																		value="<s:property value="companyFilterPage.companyFilter.userName"/>">
																<input type="hidden" id="userId" value="<s:property value="companyFilterPage.companyFilter.userId"/>"      name="companyFilterPage.companyFilter.userId">
																</div>
															</div>
														</div>
														<div class="col-sm-2">
														  <div class="form-group clearfix">
																<label class="col-sm-12 control-label">Company Name</label>
																<div class="col-sm-12">
																	<input type="text" autocomplete="off" class="form-control"
															id="companyName" placeholder="type company Name"
															name="companyFilterPage.companyFilter.companyName"
															value="<s:property value="companyFilterPage.companyFilter.companyName"/>">
															<input type="hidden"  id="companyId"  name="companyFilterPage.companyFilter.companyId"   
															  value="<s:property value="companyFilterPage.companyFilter.companyId"/>">
																</div>
															</div>
															</div>
														
														
															<div class="col-sm-2">
														  <div class="form-group clearfix">
																<label class="col-sm-12 control-label">Order Id</label>
																<div class="col-sm-12">
																	<input type="text" autocomplete="on"
																		class="form-control" id="orderId"
																		placeholder="Order Id"
																		name="companyFilterPage.companyFilter.orderId"
																		value="<s:property value="companyFilterPage.companyFilter.orderId"/>">
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
																			<input type="text" class="form-control" id="twodpd11"
																				placeholder="From Date(dd-mm-yyyy)"
																				name="companyFilterPage.companyFilter.dateFilterCreated.dtStart"
																				value='<s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtStart"/>'>

																		</div>
																		<div class="col-sm-6">
																			<input type="text" class="form-control" id="twodpd21"
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
 					 
				<div class="col-sm-12 clearfix report-search">
					<div class="table-responsive dash-table">
						<div class="box clearfix">
							<table id="example" class="table table-striped table-bordered">
								<thead>
									<tr>
											<th>S.No</th>
											<th>UserID/Company Name</th>
											<th>Voucher Date</th>
											<th>Voucher Type</th>
											<th>Order ID</th>
											<th>Invoice No</th>
											<th>Opening</th>
											<th>Debit Amount</th>
											<th>GST/Service Tax</th>
											<th>Outstanding</th>
											<th>Closing</th>
											<th>Description</th>
										</tr>
								</thead>
								<tbody><!-- companyFilterPage.walletTxList -->
									<s:iterator value="companyFilterPage.outstandingReportList"
										status="rowCount">
										<tr>
											<td><s:property
													value="%{#rowCount.count}" /></td>
											<td><s:property value="agencyName" /></td>
										 	<td><s:date name="createdAt" format="dd-MMM-yyyy HH:mm" /></td>
											<td><s:property value="voucherType"/></td>
											<td><s:property value="actionId"/></td>
											<td><s:property value="invoiceNo" /></td>
											<td><s:property value="openingBalance" /></td>
											<td><s:property value="amount"/></td>
											<td><s:property value="gstOrServiceTax"/></td>
													<s:if test="action=='Deposited' || action=='taken' || action=='Taken'">
													<td>0.00</td>
													</s:if>
													<s:else>
													<td><s:property value="outStandingBalance"/></td>
													</s:else>
											
											<td><s:property value="closingBalance" /></td>
											<td><s:property value="remarks" /></td>
										 
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
					</div>
				</div>
			 </form> 
			</div>
		</section>
	</div>

	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript"  src="js/reset-form.js"></script>
	<script>
	
		$(document).ready(function() {
			$("#twodpd21").datepicker({
				dateFormat : "dd-mm-yy",
					 changeMonth: true,
					 changeYear: true  
			});
			$("#twodpd11").datepicker({
				dateFormat : "dd-mm-yy",
			 changeMonth: true,
			 changeYear: true  
			});
		});
	</script> 
	<script type="text/javascript">
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						lengthChange : false,
						"info" :false,
						"paging" :false,
						"pagingType" : "full_numbers",
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						buttons : [ 'excel' ]
					//, 'pdf', 'print'
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
	</script>
</body>
</html>