<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>e-Travel Smart Config List</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<form class="form-inline" action="" method="post">
				
		 <div class="table-responsive dash-table">
				 
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
 							<tr>
							<th>S.No</th>
							<th>Title</th>
								<th>ApiMode</th>
							<th>Company</th>
							<th>Status</th>
							 <th>Action(s)</th>
							  
						</tr>
					</thead>
					<tbody>
						<s:iterator value="apiProviderEtravelSmartConfigsList" status="rowCount">
							<tr>
							<%-- <td><s:property value="%{((apiProviderTboConfigs.currentPageIndex - 1)*apiProviderTboConfigs.maxItems)+#rowCount.count}" /></td> --%>
								<td>${rowCount.count}</td>
								<td>${title}</td>
								<td>${environment}</td>
								<td>${companyName}</td>
								<td><span >
                 <label class="switch"> <input type="checkbox"  name="active"  data-id="${id}"
                  class="etravelActive" <c:if test="${active==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								  <td>
									<p data-placement="top">
										<a href="editEtravelSmartConfig?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" >Edit
											 </a>
									</p>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			</form>
		</div>
	</section>
</div>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
  <script src="js/common_config_status_update.js" type="text/javascript"></script>
  