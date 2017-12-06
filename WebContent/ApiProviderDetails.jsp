<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
 <%--  
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script>
  --%>


</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Supplier Details</h1>
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
		 
			 
			<form   class="form-horizontal"  >
			
			<div class="apiporoviderdetails">
			<div class="vendor-travel">
			<div class="row">
				<div class="col-sm-2">
					<b>Vendor Name  </b>
				</div>
				<div class="col-sm-1">
					<b> : </b>
				</div>
				<div class="col-sm-9">
				<p>${apiProviderNew.vendorName}</p>
				</div>
				
			</div>
			<div class="row">
				<div class="col-sm-2">
					<b>Vendor Id </b>
				</div>
				<div class="col-sm-1">
					<b> : </b>
				</div>
				<div class="col-sm-9">
				<p>${apiProviderNew.apiProvider}</p>
				</div>
				
			</div>
			<div class="row">
				<div class="col-sm-2">
					<b>Api Mode </b>
				</div>
				<div class="col-sm-1">
					<b> : </b>
				</div>
				<div class="col-sm-9">
				<p>${apiProviderNew.apiMode}</p>
				</div>
				
			</div>
			<div class="row">
				<div class="col-sm-2">
					<b>Travel Type</b>
				</div>
				<div class="col-sm-1">
					<b> : </b>
				</div>
				<div class="col-sm-9">
				<p>${apiProviderNew.travelType}</p>
				</div>
				
			</div>
			</div>
			
			
			<div class="support-details">
			
			<h4><b>Support Details</b></h4>
			<div class="row">
				<div class="col-sm-12">
				<h5>Level:1</h5>
			<table  class="table table-striped table-bordered">
				<thead>
				<tr>
					<th>Email</th>
					<th>Mobile/Phone</th>
					<th>Name</th>
					<th>Location</th>
				</tr>				
				</thead>
				<tbody>
					<tr>
						<td>${apiProviderNew.apiProviderSupportDetails.email1}</td>
						<td>${apiProviderNew.apiProviderSupportDetails.mob1}</td>
						<td>${apiProviderNew.apiProviderSupportDetails.name1}</td>
						<td>${apiProviderNew.apiProviderSupportDetails.loc1}</td>
					</tr>
				</tbody>
											
			</table>
			</div>
			<div class="col-sm-12">
				<h5>Level:2</h5>
			<table  class="table table-striped table-bordered">
				<thead>
				<tr>
					<th>Email</th>
					<th>Mobile/Phone</th>
					<th>Name</th>
					<th>Location</th>
				</tr>				
				</thead>
				<tbody>
					<tr>
						<td>${apiProviderNew.apiProviderSupportDetails.email2}</td>
						<td>${apiProviderNew.apiProviderSupportDetails.mob2}</td>
						<td>${apiProviderNew.apiProviderSupportDetails.name2}</td>
						<td>${apiProviderNew.apiProviderSupportDetails.loc2}</td>
					</tr>
				</tbody>
											
			</table>
			</div>
			</div>
			
			
			</div>
			
			<!-- technical support details -->
			
						<div class="support-details">
			
			<h4><b>Technical Support Details</b></h4>
			<div class="row">
				<div class="col-sm-12">
				<h5>Level:1</h5>
			<table  class="table table-striped table-bordered">
				<thead>
				<tr>
					<th>Email</th>
					<th>Mobile/Phone</th>
					<th>Name</th>
					<th>Location</th>
				</tr>				
				</thead>
				<tbody>
					<tr>
						<td>${apiProviderNew.apiProviderTechSupportDetails.email1}</td>
						<td>${apiProviderNew.apiProviderTechSupportDetails.mob1}</td>
						<td>${apiProviderNew.apiProviderTechSupportDetails.name1}</td>
						<td>${apiProviderNew.apiProviderTechSupportDetails.loc1}</td>
					</tr>
				</tbody>
											
			</table>
			</div>
			<div class="col-sm-12">
				<h5>Level:2</h5>
			<table  class="table table-striped table-bordered">
				<thead>
				<tr>
					<th>Email</th>
					<th>Mobile/Phone</th>
					<th>Name</th>
					<th>Location</th>
				</tr>				
				</thead>
				<tbody>
					<tr>
						<tr>
						<td>${apiProviderNew.apiProviderTechSupportDetails.email2}</td>
						<td>${apiProviderNew.apiProviderTechSupportDetails.mob2}</td>
						<td>${apiProviderNew.apiProviderTechSupportDetails.name2}</td>
						<td>${apiProviderNew.apiProviderTechSupportDetails.loc2}</td>
					</tr>
					 
				</tbody>
											
			</table>
			</div>
			</div>
			</div>
			</div>
			 </form>
		</section>
	</div>
 
	<%@ include file="DashboardFooter.jsp"%>

</body>
 
</html>