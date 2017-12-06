<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script>
<link rel="stylesheet" type="text/css" href="css/jquerydarkness-ui.min.css"> --%>
<script type="text/javascript">
	$(document).ready(
			function() {

				var company_list = [];
				/* var user_list = []; */
				//192.168.1.158:9080\

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
							company_list.push(data.records[i].companyname + ","
									+ data.records[i].company_userid);
						}
						console.log(company_list);
						//response(items);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});

				$("#search").autocomplete(
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
								var label = ui.item.label;
								var company_userid = ui.item.value;
								console.log(company_userid);
							if(company_userid!=null){ 
								$.ajax({
									//Action Name
									url : "UserListUnderCompany",
									dataType : "json",
									data : {
										 company_user_id : company_userid 
									},

									success : function(data, textStatus, jqXHR) {
										user_list=[];
										console.log("--data---------"+data);
										
										var items = data.user_records;
										
										for (var i = 0; i < data.user_records.length; i++) {
											user_list.push(data.user_records[i].username + "("+data.user_records[i].company_userid+")"  + ","
													+ data.user_records[i].id);
										}
										console.log(user_list);
										userlist(user_list);
									},
									error : function(jqXHR, textStatus, errorThrown) {
										console.log(textStatus);
									}
								});
							}
							 }
						});
			 });
	
	function userlist(user_list)
	{
		$("#userIdSearch").autocomplete(
				{
 				source : function(request, response) {
						var matcher = new RegExp('^'
								+ $.ui.autocomplete
										.escapeRegex(request.term),
								"i");
						response($.grep(user_list, function(item) {
							return matcher.test(item);

						}));
					}
				});	
	}
 </script>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
		Company Wallet List
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
					<div class="add-new">
						<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">
				<form class="form-inline" action="filterCompanyWalletList" method="post">
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
				</form>
					</div>
				</div>

				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">

					<thead>

						<!--  <th><input type="checkbox" id="checkall" /></th> -->
						<tr>
						<th>S.No</th>
							<th>User ID </th>
							<!-- <th>Company</th> -->
							<th >Wallet</th>
							<th>Email</th>
							<th>Phone</th>
						  	<th>Country</th>
						  <!-- 	<th>Action
							</th>
							 
							<th>Status</th>
							<th>Lock</th> -->
						</tr>
					</thead>
					<tbody>
						<s:iterator value="walletCompaniesList"  var="user" status="rowstatus">
 							<tr>
							 <td><s:property value="#rowstatus.count"/></td>
								<!--  <td><input type="checkbox" class="checkthis" /></td> -->
								<td><s:property value="Username"/></td>
								<!-- <td>TO DO</td> -->
								<td> 
									 <a href="goWallet?id=<s:property value="id"/>&Username=<s:property value="Username"/>&agentWallet.walletId=<s:property value="agentWallet.walletId"/>&agentWallet.walletbalance=<s:property value="agentWallet.walletbalance"/>&agentWallet.currencyCode=<s:property value="agentWallet.currencyCode"/>" class="btn btn-success btn-xs" data-title="Update" >
								  <span data-placement="top" class="fa fa-plus-circle"></span> <span><s:property value="agentWallet.walletbalance" /> <s:property value="agentWallet.currencyCode" /></span></a>
										 </td>
								 
								<td><s:property value="Email" /></td>
								<td><s:property value="mobile" /> <s:property value="Phone"/></td>
								<%-- <td><s:property value="Createddate" /></td> --%>
								<td><s:property value="Countryname" /></td>
			 
					 

							</tr>
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
</div>
</div>


</div>
<!-- table-responsive -->


</div>
<!-- /.row -->
<!-- Main row -->


</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
<script>
	  $(function(){
	      var t  = $('.alert').text();
	   if(t.length>0){
	    	  var protocol=location.protocol;
		   	   var host=location.host;
		   	   var url=protocol+"//"+host+"/LintasTravelAdmin/superUser_UserList"; 
	    	  alert(t); 
	    	 window.location.assign(url)
	      
	     }
	  
		  	 	
	   });
	   
 
</script>
<%-- 
<script>
	function userDelete(id, roleid) {
		 //alert(id);
		 var protocol=location.protocol;
    	   var host=location.host;
    	   var d_url=protocol+"//"+host+"/LintasTravelAdmin/delete";
    	 /*   "http://localhost:8080/LintasTravelAdmin/delete" */
		
		$("#yes"+id).click(function() {
			//alert(d_url);
			$.ajax({
				method : "POST",
				url :d_url ,
				data : {
					id : id,
					role_id : roleid
				},
				success : function(data, status) {
					//alert(data);
					if (data == "deleted") {
						$("#delete"+id).hide();
						alert(data);
						window.location.assign(location.href);
					} else if (data == "failed") {
						$("#delete"+id).hide();
						alert(data);
						window.location.assign(location.href);

					} else {
						window.location.assign(location.href);
					}
				},
				error : function(e) {
					alert(e.message);
				}
			});
		});

	}
</script> --%>

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







