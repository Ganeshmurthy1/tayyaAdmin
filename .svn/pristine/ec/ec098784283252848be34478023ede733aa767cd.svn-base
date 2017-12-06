<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->
<link rel="stylesheet" href="css/pagination_css.css">
<link rel="stylesheet" href="css/alert.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>
 <script src="js/alluser_list_by companyid.js"> </script>
<%-- <script src="js/wallet_userids_filter.js">
 </script> --%>
 
 
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
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Wallet History</h1>

		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="row">
			
			
			
			<s:if test="%{#session.Company!=null && #session.User!=null}">
				<input type="hidden"
							value="<s:property value="%{#session.Company.company_userid}"/>"
							id="companyUserId"> <input type="hidden"
							value="<s:property value="%{#session.Company.Email}"/>"
							id="email"> <input type="hidden"
							value="<s:property value="%{#session.User.company_userid}"/>"
							id="user_id">
				<form action="superMyTx" method="post" id="filterform">
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
														<%-- <c:forEach var="maxItems"
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
														</c:forEach> --%>
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
																<label class="col-sm-12 control-label">User Name</label>
																<div class="col-sm-12">
																	<input type="text" autocomplete="on"
																		class="form-control" id="CuserName"
																		placeholder="user Name"
																		name="companyFilterPage.companyFilter.userName"
																		value="<s:property value="companyFilterPage.companyFilter.userName"/>">
																<input type="hidden" id="userId" value="<s:property value="companyFilterPage.companyFilter.userId"/>"      name="companyFilterPage.companyFilter.userId">
																		
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
															name="flightReportPage.flightReportFilter.companyName"
															value="<s:property value="flightReportPage.flightReportFilter.companyName"/>">
															<input type="hidden"  id="companyId"  name="companyFilterPage.companyFilter.companyId"     value="<s:property value="companyFilterPage.companyFilter.companyId"/>">
															 
													</div>
												</div>
											</div>
										</s:if>		
										
											<%-- <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Order Id</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="companyName" placeholder="Order Id"
															name="commonReportPage.flightReportFilter.orderId"
															value="<s:property value="commonReportPage.flightReportFilter.orderId"/>">
													</div>
												</div>
											</div> --%>
														<div class="col-sm-2">
															<div class="form-group clearfix">
																<label class="col-sm-12 control-label">Transaction
																	Type </label>
																<div class="col-sm-12">
																	<select class="form-control"
																		name="companyFilterPage.companyFilter.companyRoleType"
																		id="companyRoleType">

																		<option value="ALL">ALL</option>
																		<c:forEach var="roleItem"
																			items="${companyFilterPage.companyFilter.transactionTypeQueue}">
																			<c:choose>
																				<c:when
																					test="${companyFilterPage.companyFilter.companyRoleType != null && companyFilterPage.companyFilter.companyRoleType == roleItem}">
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
																			<input type="text" class="form-control" id="twodpd12"
																				placeholder="From Date(dd-mm-yyyy)"
																				name="companyFilterPage.companyFilter.dateFilterCreated.dtStart"
																				value='<s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtStart"/>'>

																		</div>
																		<div class="col-sm-6">
																			<input type="text" class="form-control" id="twodpd22"
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


					<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table">
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>S.No</th>
											<th>UserID</th>
											<th>CompanyID</th>
											<th>Amount</th>
											<th>TransactionType</th>
											<th>Remarks</th>
											<th>OpeningBal</th>
											<th>ClosingBal</th>
											<th>AllotedAt</th>
											<th>AllotedBy</th>

										</tr>
									</thead>
									<tbody>


										<s:iterator value="companyFilterPage.walletTxList"
											status="rowCount">
											<tr>
												<td><s:property
														value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>
												<td><s:property value="agencyName" /></td>
												<td><s:property value="company_userid" /></td>
												<td><s:if test="transactionType=='Debit'">(-)</s:if> <s:property
														value="amount" /></td>
												<td><s:property value="transactionType" /></td>
												<td><s:property value="remarks"/></td>
												<td><s:property value="parentOpeningBalance" /></td>
												<td><s:property value="parentClosingBalance" /></td>
												<td><s:property value="convertDate" /></td>
												<td><s:property value="parentcompany_userid" /></td>

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
				</s:if>
			</div>
		</section>
	</div>


	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script>
		$(document).ready(function() {
			$("#twodpd12").keyup(function () {
				  $(this).next("#twodpd22").focus();
				});
				
				 
				 
				 $('#twodpd12').datepicker({
					 //showTimePicker: false,
					dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
					 onSelect: function( selectedDate ) {
					    	var date2 = $("#twodpd12").datepicker('getDate', '+1d'); 
					  	  date2.setDate(date2.getDate() +1 ); 
					  	  $( "#twodpd22" ).datepicker('setDate', date2);
					  	$( "#twodpd22" ).datepicker( "option", "minDate", date2 ); 
					    },
					  onClose: function() {
					      $("#twodpd22").focus();
					  }
					  
				  });
			          
			     
			 
			 $('#twodpd22').datepicker({
				 //timepicker: false,
				/*  changeMonth: true,   
		    	 changeYear:true, */
		    	 dateFormat : "dd-mm-yy",
					changeMonth : true,
					changeYear : true,
		       	 minDate:0,
		         onSelect: function(selectedDate,i) { 
		        	// $("#fromDate").datepicker("option", selected)
		              var date2 = $("#twodpd22").datepicker('getDate', '+1d'); 
		             
		         }
			 
			 });
	 
		});
	</script>
	
	<script>
		/* $(document).ready(function() {
			$("#twodpd2").datepicker({
				dateFormat : "dd-mm-yy"
			});
			$("#twodpd1").datepicker({
				dateFormat : "dd-mm-yy"
			
			});
		}); */
		/*  changeMonth: true,
		 changeYear: true */
	</script>
	<script type="text/javascript">
	
	$(document).ready(
			  function() {
			   var sales_person_list = [];
			   var cityMap =[];
			   $.ajax({
			    url :"getAllUserListByCompanyUserId",
			    dataType : "json",
			    success : function(data, textStatus, jqXHR) {
			     for (var i = 0; i < data.agentList.length; i++) {
			      //agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")");
			      sales_person_list.push(data.agentList[i].username);
			      var cityObj ={"key":data.agentList[i].id,"value":data.agentList[i].username+"("+data.agentList[i].email+")"}
			      cityMap.push(cityObj);
			     }
			     console.log("------sales_person_list------"+sales_person_list);
			     if(sales_person_list.length>0){
			      $("#CuserName").autocomplete(
			        {
			         source : function(request, response) {
			          var matcher = new RegExp(''
			            + $.ui.autocomplete
			            .escapeRegex(request.term),
			          "i");
			          response($.grep(sales_person_list, function(item) {
			           return matcher.test(item);

			          }));
			         },
			         select: function( event , ui ) {
			          $.map(cityMap, function(value, key) {
			           if(value.value==ui.item.label){
			            console.log("value---"+value.value+"--------key----------"+value.key);
			            $("#userId").val(value.key);
			           }

			          });  
			         }
			        });
			     }

			    },
			    error : function(jqXHR, textStatus, errorThrown) {
			     console.log(textStatus);
			    }
			   });


			  });
	
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						"paging" : false,
						"lengthChange" : false,
						"searching" : false,
						"ordering" : false,
						"info" : false,
						"autoWidth" : false,
						"search" : {
							"regex" : true,
						},
						buttons : [ 'excel' ]
					//, 'pdf', 'print' 
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
	</script>
</body>
</html>