<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="css/alert.css">
<script src="js/angular.js" type="text/javascript"></script>
<script
	src="js/company_filter.js">
</script>
 <%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
		<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css">
	<style>
	.load-gif{
	width: 18%
	}
	</style>
 <!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		   <h1>
	New Company Approval List
		</h1>
	 </section>

	<!-- Main content -->
	<section class="content">
		 <div class="row">
		  
		 <form action="getNonApprovalCompaniesList" method="post"> 
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
						<a class="btn btn-primary but btn-clean" href="getApprovalCompaniesList">
							Approved Companies <i class="fa fa-angle-right"></i>
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
													<label class="col-sm-12 control-label">Booking Type
														 </label>
													<div class="col-sm-12">
														<select class="form-control"
															name="companyFilterPage.companyFilter.companyRoleType"
															id="companyRoleType">

															<option value="ALL">ALL</option>
															<c:forEach var="roleItem"
																items="${companyFilterPage.companyFilter.bookingTypeQueue}">
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
											
											 
											 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Email
														 </label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="off"
															id="email" placeholder="email"
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
											 
 						
 										</div>
									</div>
							  <div class="col-sm-12">

										<div class="col-sm-6 clearfix cc-all">
											<a href=" " id="reset" class="text-right"><i
												class="fa fa-close"></i> Clear All</a>
										</div>
										<input name="pageId" type="hidden" value="8">
										<input name="actionId" type="hidden" value="22">
										<input name="statusCode" type="hidden" value="0">
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
 							 <th>SNo</th>
							<th>Company</th>
							<th>Email</th>
							<th>Phone</th>
							<th>CompanyType</th>
							<th>Country</th>
							<th>City</th>
							 <th>Details</th>
						  	<th>Approval</th>
						  	<th>Mail_status</th>
						 
						</tr>
					</thead>
					<tbody>
						<s:iterator value="companyFilterPage.approvalCompanyList"  status="rowCount">
							<tr>
						 <td><s:property value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>	
								<td><s:property value="Companyname"/></td>
								<td><s:property value="Email" /></td>
								<td><s:if test="countryCode!=null">+</s:if><s:property value="countryCode" />
								<s:if test="countryCode!=null">-</s:if><s:property value="Phone" /></td>
							 <td><s:property value="compType" /></td>  
								<td><s:property value="Countryname" /></td>
								<td><s:property value="City"/></td>
								 <td>
									<p data-placement="top" title="Company Details">
										<a
											href="companyDetails?companyid=<s:property value="companyid"/>"
											class="btn btn-success btn-xs" data-title="Update"> <span
											data-placement="top" class="fa fa-plus-circle"></span> <span></span></a>
									</p>
								</td>
								<td>
									
								 <c:choose>
    										<c:when test="${mailStatus=='5'}">
										 	<s:if test="Status == true">
										 	 <p data-placement="top" >
											 <a href="compnayApproval?companyid=<s:property value="companyid"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal">Disapprove</a>
 									 		</p>
										 	
										 	<%--   <input type="button" class="btn-primary" value="Disapprove" onclick="activateOrDeactivateFormsubmit(<s:property value="companyid"/>)"	id="stat<s:property value="companyid"/>">  --%>
											</s:if>
											<s:else>
											 <p data-placement="top" >
											<!--  <input name="pageId" type="hidden" value="8">
										<input name="actionId" type="hidden" value="30">
										<input name="statusCode" type="hidden" value="0">
										<input name="detailType" type="hidden" value="3"> -->
											 <a href="compnayApproval?pageId=8&actionId=30&statusCode=0&detailType=3&companyid=<s:property value="companyid"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="cursor: pointer;border-color: #d50000;background-color:#d50000;">Approve</a>
 									 		</p>
											
										<%--   <input type="button" class="btn-primary" value="Approve" onclick="activateOrDeactivateFormsubmit(<s:property value="companyid"/>)"	id="stat<s:property value="companyid"/>">  --%>
											 </s:else>
											</c:when>
											<c:when test="${mailStatus!='5'}">
											<s:if test="Status == true">
											<p data-placement="top" >
											 <a href="compnayApproval?companyid=<s:property value="companyid"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="pointer-events:none;cursor: pointer;border-color: #c2c5c3;background-color: #c2c5c3;">Approve</a>
 									 		</p>
											
											 <%-- <input type="button" class="btn-primary  grey" value="Approve" disabled="disabled"  onclick="activateOrDeactivateFormsubmit(<s:property value="companyid"/>)"	id="stat<s:property value="companyid"/>">  --%>
											</s:if>
											<s:else>
											<p data-placement="top" >
											 <a href="compnayApproval?companyid=<s:property value="companyid"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="pointer-events:none;cursor: pointer;border-color: #c2c5c3;background-color: #c2c5c3;">Approve</a>
 									 		</p>
											
											<%--  <input type="button" class="btn-primary  grey" value="Approve" disabled="disabled"  onclick="activateOrDeactivateFormsubmit(<s:property value="companyid"/>)" 
												id="stat<s:property value="companyid"/>"> --%>
											</s:else>
											</c:when>
											<c:otherwise>
											  <s:if test="Status == false">
											  <p data-placement="top" >
											 <a href="compnayApproval?companyid=<s:property value="companyid"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="cursor: pointer;border-color: #d50000;background-color:#d50000;">Approve</a>
 									 		</p>
											  
											  <%--  <input type="button" class="btn-primary" value="Approve"   onclick="activateOrDeactivateFormsubmit(<s:property value="companyid"/>)"	id="stat<s:property value="companyid"/>">  --%>
											</s:if>
											  </c:otherwise>
											  </c:choose>
									 </td> 
						 			<td>
						 	  			<s:if test="mailStatus == 0">
										 	 <i class="fa fa-exclamation pending"> Pending</i>
										 	 </s:if>
										 	  <s:if test="mailStatus == -1">
										 	 <i  class="fa fa-spinner processing"> Processing</i>
										 	 </s:if>
										 	 <s:if test="mailStatus == 1">
										 	 <i class="fa fa-check-circle success"> Success  Not Verified</i>
										 	 </s:if>
										 	 <s:if test="mailStatus == 2">
										 	  <i   class="fa fa-times-circle red"> Not Verified</i>
										 	 </s:if>
										 	 <s:if test="mailStatus == 3">
										 	  <i   class="fa fa-times-circle red"> Not Verified</i>
										 	 </s:if>
										 	  <s:if test="mailStatus == 4">
										 	 <i class="fa fa-times-circle"> Blocked</i>
										 	 </s:if>
										 	 <s:if test="mailStatus == 5">
										 	 <i class="fa fa-check-circle success"> Verified</i>
										 	 </s:if>
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
				  <s:if test="hasActionErrors()">
				 <div class="sccuss-full-updated" id="success-alert">
							<div class="succfully-updated clearfix">

								<div class="col-sm-2">
									<i class="fa fa-times fa-3x red"></i>
								</div>

								<div class="col-sm-10">
									<s:actionerror />
									<button type="button" id="cancel" class="btn btn-primary">Ok</button>

								</div>

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
			</div>
 </form> 
		</div>
		</section>
</div>
 
<!-- /.row -->
<!-- Main row -->

<!-- /.content -->
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
 <script type="text/javascript">
	$(function() {
		var totUrl=$(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		  var finalUrl = newUrl+"getApprovalCompaniesList";
		  var nonapprovalUrl = newUrl+"getNonApprovalCompaniesList";
	  $('#success').click(function() {
		 window.location.assign(finalUrl); 
			$('#success-alert').hide();
			
		});  
		  $('#cancel').click(function() {
			   $('#error-alert').hide();
			   window.location.assign(nonapprovalUrl); 
			});  
	 });
	 </script>
	 <script type="text/javascript">
	 function activateOrDeactivateFormsubmit(id){
		console.log("-------------id--------------"+id);
		$('#stat'+id).hide();
		$('#load'+id).show();
		$('#statusForm').submit();
	 }
	 </script>
	 
  






