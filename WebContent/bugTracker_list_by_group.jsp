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
	href="css/jquery-uii.css"
	rel="stylesheet" type="text/css" />	  -->
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<script src="js/load_company_emp_names.js"></script>
<script src="js/techsupport_emplist.js"></script>
		<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>

<style type="text/css">
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
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>

</head>
<body>
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
				<li><a href="goBugTrackerList">Bug List</a></li>
				<li class="active">View Issue</li>
			</ol>
		</div>
	</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="row">
			
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
						<form action="listBugTracker" method="post" id="filterform">
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
								<div class="add-new">
						<a class="btn btn-primary but btn-clean" href="addBugTracker">
							Add BugTracker <i class="fa fa-angle-right"></i>
						</a>
						<a class="btn btn-primary but btn-clean" href="goBugTrackerList?showtype=ALL">
							BugTracker List
						</a>
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
								<th>Assigned By User</th>
								<th>Assigned To User</th>
								<th>Created Date</th>
								<th>Assigned Date</th>
								<!-- <th>Active Status</th> -->
								<th>Action</th>
								<!-- <th>Test Cases</th> -->
								<th>View Test Cases</th>
								<!-- <th>Edit BugTracker</th> -->
								
								
								
							</tr>
						</thead>
						<tbody>
						
						<tr  style=" background-color: #3c8dbc"><td colspan="12" style="color: white"><h5>Group of Created </h5></td></tr>
						<% int increment=1;%>
						<s:iterator value="bugReportPage.items" var="bugHistory" status="varStatus" >
						 <s:if test="status=='Created'" > 
						<tr>
						<td><%=increment++%></td>
						<td>${title}</td>
						
						
						<td>${assignedByName}</td>
						<td>${assignedToName}</td>
						<td>${createDate}</td>
						<td><s:date name="assignDate" format="dd-MM-YYYY"/></td>
						<%-- <td><s:if test="bugTracker.active==true">
							Active
						</s:if> <s:if test="bugTracker.active==false">
							DeActive
						</s:if></td> --%>
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
						 </s:if> 
						</s:iterator>
						 </tbody>
						  <tbody>
						 <% int increment1=1;%>
						 <tr  style="background-color: #3c8dbc"><td colspan="12" style="color:white"><h5>Group of Assigned</h5></td></tr>
						<s:iterator value="bugReportPage.items" var="bugHistory" status="varStatus" >
						<s:if test="status=='Assigned'" > 
						<tr>
						<td><%=increment1++%></td>
						<td>${title}</td>
						
						<td>${assignedByName}</td>
						<td>${assignedToName}</td>
						<td>${createDate}</td>
						<td><s:date name="assignDate" format="dd-MM-YYYY"/></td>
						<td>
										<p data-placement="top">
											<a href="viewBugTracker?id=${bugTracker.id}"
												class="btn btn-success btn-xs">View</a>
										</p>
									</td>
									<%-- <td>
										<p data-placement="top">
											<a href="goTestCases?id=${bugTracker.id}"
												class="btn btn-success btn-xs">Create</a>
										</p>
									</td> --%>
									<td>
										<p data-placement="top">
											<a href="viewTestCases?id=${bugTracker.id}"
												class="btn btn-success btn-xs">View</a>
										</p>
									</td>
									<%-- <td>
										<p data-placement="top">
											<a href="editBugTrackerById?id=${bugTracker.id}"
												class="btn btn-success btn-xs">Edit</a>
										</p>
									</td> --%>
						
						</tr>
						</s:if> 
						</s:iterator>
						 </tbody>
						  <tbody>
						 <tr style="background-color: #3c8dbc"><td colspan="12" style="color:white"><h5>Group of Closed</h5></td></tr>
						 <% int increment2=1;%>
						<s:iterator value="bugReportPage.items" var="bugHistory" status="varStatus" >
						<s:if test="status=='Closed'" > 
						<tr>
						<td><%=increment2++%></td>
						<td>${title}</td>
						<td>${assignedByName}</td>
						<td>${assignedToName}</td>
						<td>${createDate}</td>
						<td><s:date name="assignDate" format="dd-MM-YYYY"/></td>
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
									<%-- <td>
										<p data-placement="top">
											<a href="editBugTrackerById?id=${bugTracker.id}"
												class="btn btn-success btn-xs">Edit</a>
										</p>
									</td> --%>
						
						</tr>
						 </s:if>
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
							<!-- /.box -->

						</div>
						<!-- table-responsive -->
					
</section>
				</div>
			
			<!-- /.row -->
			<!-- Main row -->


		
		<!-- /.content -->
	
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript" src="js/reset-form.js"></script>
	<script>
		$(document).ready(function() {
			$("#twodt2").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#twodt1").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#bookingDate").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});
			$("#travelDate").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true
			});

		});
	</script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var table = $('#mytable3' ,'#mytable2 ','#mytable1').DataTable({
						"searching" : false,
						"lengthChange" : false,
						"ordering" : true,
						"paging" : false,
						"info" : false,
						"autoWidth" : false,
						"search" : {
							"regex" : true,
						},
						"pagingType" : "full_numbers",
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
		</script>
		
	<script type="text/javascript">
	
	
	/* 
		 $(function() {
		  var  reportType=document.getElementById('reportTypeHidden').value;
		 if(reportType==1){
			 reportType=10;
			 document.getElementById('reportType').value = reportType;
		 }
		 else 
			 document.getElementById('reportType').value = reportType;
		 	 
		});  */ 
		  $(document).ready(function(){  
		      $("#filterform").validate({
		      	 rules: { 
		              "email": {
		                  required: true,
		                  email: true
		              } 
		          },  
		          messages: { 
		              "email": {
		                  required: "Please, enter an email",
		                  email: "Email is invalid"
		              },
		          }, 
		          highlight: function(element, errorClass, validClass) { 
		              $(element).addClass(errorClass).removeClass(validClass);
		              $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		            },
		            success: function(element) { 
		           element.closest('.form-group').removeClass('has-error').addClass('has-success');
		              $(element).remove();
		            },
		          submitHandler: function (form) {   
		              return false;  
		          }
		   
		         
		      });
		      
		      $('#buttonSubmit').click(function() { 
		    	if($("#filterform").valid())  
		      	 document.getElementById("filterform").submit();
		    /* 	else
		    		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
		      });    
		      
		  });
		
		
		
		
	</script>



</body>
</html>