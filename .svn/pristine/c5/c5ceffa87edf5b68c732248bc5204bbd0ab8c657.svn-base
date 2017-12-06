<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script> --%>
<script src="js/employee_filter.js"> </script>
<script src="js/load_company_emp_names.js"></script>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" type="text/css" href="css/jquerydarkness-ui.min.css"> -->
<link rel="stylesheet" href="css/alert.css">
 <title>Employee List</title>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Employee List
		</h1>
		 
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
				<form  action="superUser_UserList" method="post" id="filterform">
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
											<c:forEach var="maxItems" items="${companyFilterPage.pageSizeQueue}">
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
							
							<div class="col-sm-3 clearfix pull-right">
							 <s:if test="#session.Company.companyRole.isCorporate()">
							<div class="add-new">
						<a class="btn btn-primary but btn-clean" href="addSuperUser_user">
							Add New <i class="fa fa-angle-right"></i>
						</a>
					</div>
							</s:if>
							<s:else>
							<div class="add-new">
						<a class="btn btn-primary but btn-clean" href="addSuperUser_user">
							Add New <i class="fa fa-angle-right"></i>
						</a>
					</div>
							</s:else>
					
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
													<label class="col-sm-12 control-label">Employee Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="userIdSearch" placeholder="user Name"
															name="companyFilterPage.companyFilter.userName"
															value="<s:property value="companyFilterPage.companyFilter.userName"/>">
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Role Type
														 </label>
													<div class="col-sm-12">
														<select class="form-control"
															name="companyFilterPage.companyFilter.companyRoleType"
															id="companyRoleType">

															<option value="ALL">ALL</option>
															  <c:forEach var="roleItem"
																items="${companyFilterPage.companyFilter.employeeRoleQueue}">
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
															id="Email" placeholder="email"
															name="companyFilterPage.companyFilter.email"
															value="<s:property value="companyFilterPage.companyFilter.email"/>">
													</div>
												</div>
											</div>
											 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Mobile Number
														 </label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="off"
															id="phone" placeholder="Mobile Number"
															name="companyFilterPage.companyFilter.phone"
															value="<s:property value="companyFilterPage.companyFilter.phone"/>">
													</div>
												</div>
											</div>
											
											 <s:if test="%{#session.Company.companyRole.isSuperUser() || #session.Company.companyRole.isDistributor()}">
											 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Company Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="off" class="form-control"
															id="companyName" placeholder="Company Name"
															name="companyFilterPage.companyFilter.companyName"
															value="<s:property value="companyFilterPage.companyFilter.companyName"/>">
															<input type="hidden" id="companyId"  name="companyFilterPage.companyFilter.companyId"
															value="<s:property value="companyFilterPage.companyFilter.companyId"/>">
													</div>
												</div>
											</div>
											 </s:if>
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

						<!--  <th><input type="checkbox" id="checkall" /></th> -->
						<tr>
					 <th>S.No</th> 
					
							<th>UserID</th>
							 
							 <th>Email</th>
							 	 <th>Company</th>
							 	  <th>CompanyType</th>
							<th>Mobile</th>
						 
							<th>Country</th>
							 
							  <th>Action</th>  
								<th>Expand</th>
						 
							 	<th>Mail_status</th>
							<th>Status</th>
							<th>Lock</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="companyFilterPage.userList"  var="user" status="rowCount">

							<tr>
						 <td><s:property value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td>	
						  <td><s:property value="Username" /></td>
								 
								<td><s:property value="Email" /></td>
								<td><s:property value="companyName"/></td>
							<td><s:property value="companyType" /></td>
								
								<td><s:property value="mobile" /></td>
								<%-- <td><s:property value="Createddate" /></td> --%>
								<td><s:property value="Countryname" /></td>
								<%-- <td><s:property value="City" /></td> --%>
								  <td>
								  
								   
									<p data-placement="top">
									 <s:if test="%{#session.User.id==id}">
									 <a href="superUser_UserUpdate?id=<s:property value="id"/>&userrole_id.roleid=<s:property value="userrole_id.roleid"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="pointer-events:none;cursor: default;border-color: #c2c5c3;background-color: #c2c5c3;"><span
											class="fa fa-cloud-upload"></span>Edit</a>
									 </s:if>
									 <s:else>
									  <a href="superUser_UserUpdate?id=<s:property value="id"/>&userrole_id.roleid=<s:property value="userrole_id.roleid"/>"
											class="btn btn-success btn-xs" data-toggle="modal" ></span>Edit</a>
									 
									 </s:else>
 									 </p>
								 </td>  
								 <td> 
							<p data-placement="top">
									 <a  href="agentDetails?id=<s:property value="id"/>" class="btn btn-success btn-xs" data-title="Update">
								  <span data-placement="top" ></span> View<span></span></a>
								  </p>
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
						 				<td>
						 				  <s:if test="Status == true">
											 <s:if test="%{#session.User.id==id}">
											  <p data-placement="top" >
											 <a href="setSuperUser_UserStatus?id=<s:property value="id"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="pointer-events:none;cursor: pointer;border-color: #c2c5c3;background-color: #c2c5c3;">Activated</a>
 									 		</p>
											  </s:if>
											  <s:else>
											   <p data-placement="top" >
											 <a href="setSuperUser_UserStatus?id=<s:property value="id"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal" > DeActive</a>
 									 		</p>
 									 		</s:else>
										 </s:if>
											    <s:else>
                                               <s:if test="%{#session.User.id==id}">
                                                <p data-placement="top" >
											 <a href="setSuperUser_UserStatus?id=<s:property value="id"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="pointer-events:none;cursor: pointer;border-color: #c2c5c3;background-color: #c2c5c3;">DeActivated</a>
 									 		</p>
                                              </s:if>
                                              <s:else>
                                              <p data-placement="top" >
									  
									 <a href="setSuperUser_UserStatus?id=<s:property value="id"/>&Status=<s:property value="Status"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="cursor: pointer;border-color: #d50000;background-color:#00a65a;"> Active</a>
 									 </p>
 									 </s:else>
                                               </s:else>
									</td>
									
									<td>
						 				  <s:if test="Locked == true">
											 <s:if test="%{#session.User.id==id}">
											  <p data-placement="top" >
											 <a href="setSuperUser_UserLock?id=<s:property value="id"/>&Locked=<s:property value="Locked"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="pointer-events:none;cursor: pointer;border-color: #c2c5c3;background-color: #00a65a;">Locked</a>
 									 		</p>
											  </s:if>
											  <s:else>
											   <p data-placement="top"  >
											 <a href="setSuperUser_UserLock?id=<s:property value="id"/>&Locked=<s:property value="Locked"/>"
											class="btn btn-success btn-xs" data-toggle="modal" > <i class="fa fa-lock"></i>UnLock</a>
 									 		</p>
 									 		</s:else>
										 </s:if>
											    <s:else>
                                               <s:if test="%{#session.User.id==id}">
                                                <p data-placement="top"  >
											 <a href="setSuperUser_UserLock?id=<s:property value="id"/>&Locked=<s:property value="Locked"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="pointer-events:none;cursor: pointer;border-color: #c2c5c3;background-color: #c2c5c3;">UnLocked</a>
 									 		</p>
                                              </s:if>
                                              <s:else>
                                              <p data-placement="top" >
									  
									 <a href="setSuperUser_UserLock?id=<s:property value="id"/>&Locked=<s:property value="Locked"/>"
											class="btn btn-success btn-xs" data-toggle="modal" style="cursor: pointer;border-color: #00a65a;background-color:#00a65a;"><i class="fa fa-unlock"></i>Lock</a>
 									 </p>
 									 </s:else>
                                               </s:else>
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
 
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
 <script type="text/javascript" src="js/reset-form.js"></script>
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"superUser_UserList";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 });
 </script>
 
 







