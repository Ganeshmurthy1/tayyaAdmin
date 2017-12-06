<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#th, #td {
	text-align: left;
	padding: 8px;
}
</style>
<link rel="stylesheet" href="css/alert.css">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Hotel Book Details</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
		<div class="row">
			<form action="#" method="post" id="filterform">
				<div class="clearfix">
					<div class="col-sm-12">
						<div class="form-group" id="user_form-group">
							<%-- <input type="hidden" value="<s:property value="%{#session.Company.company_userid}"/>" id="companyUserId">  --%>

							<div class="col-sm-12">
								
							</div>
						</div>
					</div>
				</div>

				<div class="table-responsive dash-table">
					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.no</th>
								<th>Company Name</th>
								<th>Date</th>
								<th>Search Key</th>
								<th>Query</th>
								<th>transactionKey</th>
								<th>appkey</th>
								<th>IP</th>
								<th>companyId</th>
								<th>configId</th>
							</tr>
						</thead>
						<tbody>
								<s:iterator value="hotelBookresponse" status="rowCount">
								<tr>
									<td><s:property value="%{#rowCount.count}" /></td>
									<td><s:property value="companyName" /></td>
									<td><s:property value="searchOnDateTime" /></td>
									<td><s:property value="searchKey" /></td>
									<td><s:property value="searchQueryString" /></td>
									<td><s:property value="transactionKey" /></td>
									<td><s:property value="appkey" /></td>
									<td><s:property value="IP" /></td>
									<td><s:property value="companyId" /></td>
									<td><s:property value="configId" /></td>
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

