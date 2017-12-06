<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Employee List</h1>
		 
	</section>

	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">

			<div class="table-responsive dash-table">


				<div class="col-sm-12 clearfix">
					<div class="add-new">
						<a class="btn btn-primary but btn-clean" href="adduser"> Add
							New <i class="fa fa-angle-right"></i>
						</a>
					</div>
				</div>

				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">

					<thead>

						 
						<tr>
							<th>S.No</th>
							<th>UserID </th>
							<th>Email</th>
							<th>ContactNumber</th>
							 <th>Country</th>
						 	<th>Expand</th>
						 	<th>Mail_status</th>
							<th>Status</th>
							<th>Lock</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="userList"  status="rowCount">

							<tr>
							<td><s:property value="{#rowCount.count}"/></td>
								 
								<td><s:property value="Username" /></td>
								<td><s:property value="Email" /></td>
								<td><s:property value="Mobile" /></td>
							 
								<td><s:property value="Countryname" /></td>
								<%-- <td>
									<p data-placement="top" title="update">

										<a href="companyUserUpdate?id=<s:property value="id" />"
											class="btn btn-success btn-xs" data-toggle="modal"><span
											class="fa fa-cloud-upload"></span>Edit</a>
									</p>

								</td> --%>
						 <td> 
							<p data-placement="top" title="Agent Details">
									 <a  href="agentDetails?id=<s:property value="id"/>" class="btn btn-success btn-xs" data-title="Update" >
								  <%-- <span data-placement="top" class="fa fa-plus-circle"></span> --%>View <span></span></a>
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
									<form action="setCompanyUserStatus" method="post">
										<input type="hidden" name="id"
											value="<s:property value="id"/>" id="uniqueId"> <input
											type="hidden" name="Status"
											value="<s:property value="Status"/>" id="Status"> 
											 <s:if test="Status == true">
											 <input	type="submit" value="Active" id="stat<s:property value="id"/>">
                                              </s:if>
                                              <s:else>
                                                 <input	type="submit" value="InActive" id="stat<s:property value="id"/>">
                                              </s:else>
											 

									</form>
								</td> 
								
								  <td>
  						 <form action="setCompanyUserLock" method="post">
  				 		<input type="hidden" name="id"   value="<s:property value="id"/>" id="id">
  						  <input type="hidden" name="Status"   value="<s:property value="Status"/>" id="Status">
  						    <input type="hidden" name="Locked"   value="<s:property value="Locked"/>" id="Locked">
  						   	<s:if test="Locked == true">
											 <input	type="submit" value="Locked" id="stat<s:property value="id"/>">
                                              </s:if>
                                              <s:else>
                                                 <input	type="submit" value="UnLocked" id="stat<s:property value="id"/>">
                                              </s:else>
  						      <%-- 
  						   <input type="submit" value="<s:property value="Locked"/>"  id="stat<s:property value="id"/>">
  						  --%>
  						
  						  </form>
  						    
  						 </td>
								 
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
		</section>
</div>
 
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<script>
/* 	function userDelete(userid, roleid) {
		alert(roleid);
		$("#yes" + userid).click(function() {
			$.ajax({
				method : "POST",
				url : "http://localhost:8080/LintasTravelAdmin/delete",
				data : {
					userid : userid,
					role_id : roleid
				},
				success : function(data, status) {
					alert(data);
					if (data == "deleted") {
						$("#delete" + userid).hide();
						alert(data);
						window.location.assign(location.href);
					} else if (data == "failed") {
						$("#delete" + userid).hide();
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

	} */
 
	  $(function(){
	      var t  = $('.alert').text();
	   if(t.length>0){
	    	  var protocol=location.protocol;
		   	   var host=location.host;
		   	   var url=protocol+"//"+host+"/LintasTravelAdmin/companyUserList"; 
	    	  alert(t); 
	    	  
	  	  	window.location.assign(url)
	      
	     }
	  
		  	 	
	   });
	   

</script>
 

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







