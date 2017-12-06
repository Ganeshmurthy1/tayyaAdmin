<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Order</title>

<%-- <script src="js/angular.js" type="text/javascript"></script> --%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>

<link rel="stylesheet" href="css/alert.css">
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Order Customers List</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="sccuss-full-updated" id="success-alert" style="display:none">
				<div class="succfully-updated clearfix">

					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>

					<div id="message" class="col-sm-10">
 					</div>
 					 <button type="button" id="success" class="btn btn-primary">Ok</button>
 				 </div>
 				
			</div>

			<div class="row">

				<div class="table-responsive dash-table">

<s:if test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.superuser==1}">
 <table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<thead>
							<tr>
								<th>First_name</th>
								<th>Last_name</th>
								<th>Email</th>
								<th>Age</th>
								<th>Gender</th>
								<th>Action</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="#session.OrderCustomerList">

								<tr>
									<td><input class="input"
										id="firstName<s:property value="id"/>" type="text"
										name="firstName" value="<s:property value="firstName"/>"></td>
									<td><input class="input"
										id="LastName<s:property value="id"/>" type="text"
										name="LastName" value="<s:property value="LastName"/>"></td>
									<td><input class="input"
										id="email<s:property value="id"/>" type="text" name="email"
										value="<s:property value="email"/>"></td>
									<td><input class="input" id="age<s:property value="id"/>"
										type="text" name="age" value="<s:property value="age"/>"></td>
									<td><select class="input"
										id="gender<s:property value="id"/>" name="gender">
											<option selected="selected"><s:property
													value="gender" /></option>
											<option>Male</option>
											<option>Female</option>

									</select></td>
									<td>

										<p data-placement="top" title="update">

											<a href="#"
												onclick="orderCustomerUpdate('<s:property value="id"/>');"
												class="btn btn-success btn-xs" data-toggle="modal">Update
											</a>
										</p>

									</td>
								</tr>

							</s:iterator>
						</tbody>

					</table>
					</s:if>
					<s:elseif test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.usermode==1 &&  #session.User.userrole_id.superuser==0}">
					 <table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<thead>
							<tr>
								<th>First_name</th>
								<th>Last_name</th>
								<th>Email</th>
								<th>Age</th>
								<th>Gender</th>
								<th>Action</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="#session.OrderCustomerList">
 								<tr>
									<td><input class="input"
										id="firstName<s:property value="id"/>" type="text"
										name="firstName" value="<s:property value="firstName"/>"></td>
									<td><input class="input"
										id="LastName<s:property value="id"/>" type="text"
										name="LastName" value="<s:property value="LastName"/>"></td>
									<td><input class="input"
										id="email<s:property value="id"/>" type="text" name="email"
										value="<s:property value="email"/>"></td>
									<td><input class="input" id="age<s:property value="id"/>"
										type="text" name="age" value="<s:property value="age"/>"></td>
									<td><select class="input"
										id="gender<s:property value="id"/>" name="gender">
											<option selected="selected"><s:property
													value="gender" /></option>
											<option>Male</option>
											<option>Female</option>

									</select></td>
									<td>

										<p data-placement="top" title="update">

											<a href="#"
												onclick="orderCustomerUpdate('<s:property value="id"/>');"
												class="btn btn-success btn-xs" data-toggle="modal">Update
											</a>
										</p>

									</td>
								</tr>

							</s:iterator>
						</tbody>
 					</table>
				 </s:elseif>
					<s:elseif test="%{#session.User!=null && #session.User.userrole_id.usermode==0 &&  #session.User.userrole_id.superuser==0}">
					 <table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<thead>
							<tr>
								<th>First_name</th>
								<th>Last_name</th>
								<th>Email</th>
								<th>Age</th>
								<th>Gender</th>
								<th>Action</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="#session.OrderCustomerList">
 								<tr>
									<td><input class="input"
										id="firstName<s:property value="id"/>" type="text"
										name="firstName" value="<s:property value="firstName"/>"></td>
									<td><input class="input"
										id="LastName<s:property value="id"/>" type="text"
										name="LastName" value="<s:property value="LastName"/>"></td>
									<td><input class="input"
										id="email<s:property value="id"/>" type="text" name="email"
										value="<s:property value="email"/>"></td>
									<td><input class="input" id="age<s:property value="id"/>"
										type="text" name="age" value="<s:property value="age"/>"></td>
									<td><select class="input"
										id="gender<s:property value="id"/>" name="gender">
											<option selected="selected"><s:property
													value="gender" /></option>
											<option>Male</option>
											<option>Female</option>

									</select></td>
									<td>

										<p data-placement="top" title="update">

											<a href="#"
												onclick="orderCustomerUpdate('<s:property value="id"/>');"
												class="btn btn-success btn-xs" data-toggle="modal">Update
											</a>
										</p>

									</td>
								</tr>

							</s:iterator>
						</tbody>
 					</table>
					
					</s:elseif>
					
					
				 </div>

			</div>
		</section>
	</div>

</body>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>


<script type="text/javascript">
	$(function() {

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


<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "companyUserList";
		$('#success').click(function() {
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});

 </script>

<script type="text/javascript">
      function  orderCustomerUpdate(id){
    	  var firstName=$("#firstName"+id).val();
    	  var LastName=$("#LastName"+id).val();
    	  var email=$("#email"+id).val();
    	  var age=$("#age"+id).val();
    	  var gender=$("#gender"+id).val();
    	  console.log("firstName..."+firstName);
    	  console.log("LastName......"+LastName);
    	  console.log("email------"+email);
    	  console.log("age-----------"+age);
    	  console.log("gender-------"+gender);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl = newUrl+"orderCustomerUpdate";
			 $.ajax({
				    method: "POST",
				    url:finalUrl ,
				    data: {id:id,firstName:firstName,LastName:LastName,email:email,age:age,gender:gender},
				    success:function(data,status)
					{ 
				        $.each(data, function(index, element) {
				    		  console.log("data-------"+element.result);

						     	if(element.result=="success"){
						    		 $('#success-alert').show();
									  $('#message').text("Successfully updated.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					 window.location.assign($(location).attr('href'));
						  					});
						  				
						  				
						    	}
						    	else if(element.result=="failed"){
						    		$('#success-alert').show();
									  $('#message').text("Failed.Try again.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					 
						  					});
					    	}
						    	 
				    	 });
				     
				    	
				     },
					error: function(xhr, status, error)
					{
						$('#success-alert').show();
						 $('#message').text(error);
						  $('#success').click(function() {
		  					  $('#success-alert').hide();
		  					 });
					   console.log("Error----------"+error);
					}
				});  
    	  
    	  
    			    
    		
    	 }
      
     
 </script>