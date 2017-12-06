<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
  
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
		User Wallet List
		</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>

	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<div class="table-responsive dash-table">
				<div class="col-sm-12 clearfix">
				<%-- 
				<form action="searchwalletbycompanytype" method="post">
					<div class="form-group" id="user_form-group">
									<label for="Country">Company Type</label>
									<div class="input-group">
										<select class="form-control input-sm" name="filterCompanyType"
											id="filterCompanyType" autocomplete="off" required>										
											<option value="all">ALL</option>
											<option value="dis"><s:text name="global.Wholesaler" ></s:text></option>
											<option value="agency">Agency</option>
										</select>
									</div>
									<div class="form-group rep-buto">
									<button type="submit" class="btn btn-primary">Show</button>
								</div>
								</div>
								
								</form> --%>
				
					<div class="add-new">
						<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">
					
					
					
				<%-- <form class="form-inline" action="filterAgentWalletList" method="post">
				<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="user_companyUserId" name="user_companyUserId">
					<div class="form-group">
						<!-- <label for="exampleInputAmount">Company Type</label> -->
						<div class="input-group">
							<input type="text" placeholder="Type Company Userid"
								class="form-control input-sm" id="search" value='<s:property value="company_user_id"/>'  name="company_user_id">
						</div>
						<div class="input-group">
							<input type="text" placeholder="Type User ID "
								class="form-control input-sm"  autocomplete="off"  value='<s:property value="user_id"/>'  id="userIdSearch" name="user_id">
					
						</div>
						
					</div>
					<div class="form-group rep-buto">
							<button type="submit" class="btn btn-primary">Search</button>
					</div>
				</form> --%>
					</div>
				</div>

				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">

					<thead>

						<!--  <th><input type="checkbox" id="checkall" /></th> -->
						<tr>
						<th>S.No</th>
							<th>Company Name </th>
							<th>Wallet Type</th>
						   <th>User ID</th>
						   <th>Email</th>
						   <th>Credit Balance</th>
						   <th>Deposit Balance</th>
						   <th>TransactionType</th>
						  <th>Update Wallet Balance</th>
						  <!--  <th>Update OutStanding Balance</th> -->
						  
						</tr>
					</thead>
					<tbody>
						<s:iterator value="walletusersList"  var="user" status="rowstatus">
						<s:if test="#user.Username != 'DirectUser'">
 							<tr>
							 <td><s:property value="#rowstatus.count"/></td>
							
									<td><s:property value="companyName"/></td>
										<td><s:property value="agentWallet.walletType" /> </td>
								<td><s:property value="Username" /></td>
									<td><s:property value="Email"/></td>
								<td><s:property value="agentWallet.walletbalance" /> <s:property value="agentWallet.currencyCode" /></td>
								<td><s:property value="agentWallet.depositBalance" /> <s:property value="agentWallet.currencyCode" /></td>
								 <td><s:property value="agentWallet.transactionType" /></td>
								<td> 
									 <a href="goWallet?id=<s:property value="id"/>&Username=<s:property value="Username"/>&agentWallet.walletId=<s:property value="agentWallet.walletId"/>&agentWallet.walletbalance=<s:property value="agentWallet.walletbalance"/>&agentWallet.currencyCode=<s:property value="agentWallet.currencyCode"/>" class="btn btn-success btn-xs" data-title="Add Wallet" > 
								<i class="fa fa-money-lg"></i>Add Wallet</a>
								  <%-- <a href="#"  onclick="javascript:userWalletVerifyByPassword('${agentWallet.walletId}','${agentWallet.walletbalance}','${agentWallet.currencyCode}','${username}','${id}')"  >Update </a> --%>
										 </td>
								<%--  <td> 
									 <a href="goOutStandingBalance?id=<s:property value="id"/>" class="btn btn-success btn-xs" data-title="Update" > 
								 OutStanding Balance</a>
								  <a href="#"  onclick="javascript:userWalletVerifyByPassword('${agentWallet.walletId}','${agentWallet.walletbalance}','${agentWallet.currencyCode}','${username}','${id}')"  >Update </a>
										 </td> --%>
							</tr>
							</s:if>
						</s:iterator>
					</tbody>

				</table>

				<s:if test="hasActionErrors()">
					<div class="alert" style="display: none">
						<span class="fa fa-thumbs-o-up fa-1x"></span>
						<s:actionerror />
					</div>
				</s:if>
				<s:if test="hasActionMessages()">
					<div class="alert" style="display: none">
						<span class="fa fa-thumbs-o-up fa-1x"></span>
						<s:actionmessage />
					</div>
				</s:if>
			</div>

		</div>
<!-- table-responsive -->


<!-- /.row -->
<!-- Main row -->


</section>
<!-- /.content -->
</div>


<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
<script type="text/javascript">
	$(function() {
		/*   $("#mytable1").DataTable(); */
		$('#mytable').DataTable({
			"paging" : true,
			"lengthChange" : true,
			"searching" : true,
			"ordering" : true,
			"info" : true,
			"autoWidth" : false,
			"search" : {
				"regex" : true,
			},
			"pagingType" : "full_numbers",
			"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],

		});
	});
</script>







