<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
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
	
</script> --%>
<style>
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}
</style>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Email List</h1>
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
			 
		 
			<form action="" method="post"
				class="form-horizontal" name="myForm">
				  <div class="form-group">
					<label class="col-sm-2 control-label"> </label>
					<div class="col-sm-8">
						<div class="support">

							 <div  id="support">
							<h4>To Email List</h4>
 								<div class="level1">
 								
									<div id="level1">
									
 								<div class="well">
 								 <table >
										<s:iterator value="toEmailList" var="item" status="rowCount">
									 
										<tr>
										<td>
										<s:property  value="#rowCount.count"/>
										</td>
										<td>
										<s:property  value="item"/>
										</td>
										</tr>
									
									  
									</s:iterator>	 
									  
										</table> 
											 
								  </div>
									</div>
								</div>
 								</div>
							 
 						<div   id="tecsupport">
 						<h4>CC Email List</h4>
								<div class="level1">
 							<div id="level1">
 										<div class="well">
										 
										<table >
										<s:iterator value="ccEmailList" var="item" status="rowCount">
									 
										<tr>
										<td>
										<s:property  value="#rowCount.count"/>
										</td>
										<td>
										<s:property  value="item"/>
										</td>
										</tr>
									
									  
									</s:iterator>	 
									  
										</table> 
									  
											 
									</div>
								</div>
 							</div>
							<!-- tech support ends -->

						</div>
 
 						<div   id="tecsupport">
 					<h4>BCC Email List</h4>
								<div class="level1">
 							<div id="level1">
 										<div class="well">
											 
										
										<table >
										<s:iterator value="bccEmailList" var="item" status="rowCount">
									 
										<tr >
										<td>
										<s:property  value="#rowCount.count"/>
										</td>
										<td>
										<s:property  value="item"/>
										</td>
										</tr>
									
									  
									</s:iterator>	 
									  
										</table>
										
										
											 
									</div>
								</div>
 							</div>
							<!-- tech support ends -->

						</div>
						<!--  support -->

					</div>
				</div>

  </div>
			</form>
		</section>
	</div>


	<%@ include file="DashboardFooter.jsp"%>

</body>

</html>