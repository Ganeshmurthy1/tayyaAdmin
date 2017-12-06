<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->

<link rel="stylesheet" href="css/alert.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>

</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Passenger Profile</h1>
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
				<div class="col-sm-12 clearfix report-search">
					<s:if
						test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.superuser==1}">

						<form class="form-inline" action="FilterdPassengerList"
							method="post">
							
							
							<div class="form-group">
								<label for="exampleInputAmount">FirstName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm"
										placeholder="FirstName" name="passfirstName" value='<s:property value="passfirstName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">LastName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" 
										placeholder="LastName" name="passlastName" value='<s:property value="passlastName"/>'>
									
								</div>
							</div>
								<div class="form-group">
								<label for="exampleInputAmount">Email Address</label>
								<div class="input-group">
									<input type="email" class="form-control input-sm" 
										placeholder="Email" name="passemail" value='<s:property value="passemail"/>' >
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Mobile</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" 
										placeholder="Mobile" name="passmobile" value='<s:property value="passmobile"/>' onkeypress="return isNumberKey(event,this);">
									
								</div>
							</div>
							
							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Search</button>
							</div>
							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">
									<!-- <div class="box-body"> -->
									<%-- 
								<label for="Country"><h4>
										<b><s:property value="user" /> Report List</b>
									</h4></label> --%>


									<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>

												<!-- <th>Order id</th> -->
												<th>FirstName</th>
												<th>LastName</th>
												<th>Gender</th>
												<th>Email Address</th>
												<th>Mobile</th>												
												<th>Country Id</th>
                                                 <th>Action</th>
											</tr>
										</thead>
										<tbody>


											<s:iterator value="reportData_list">

												<tr>
													<td><input class="input" id="firstName<s:property value="id"/>" type="text" name="firstName" value="<s:property value="firstName"/>"></td>
													<td><input class="input" id="lastName<s:property value="id"/>" type="text" name="lastName" value="<s:property value="lastName"/>"></td>
													<td><input class="input" id="gender<s:property value="id"/>" type="text" name="gender" value="<s:property value="gender"/>"></td>
													<td><input class="input" id="email<s:property value="id"/>" type="text" name="email" value="<s:property value="email"/>"></td>
													<td><input class="input" id="mobile<s:property value="id"/>" type="text" name="mobile" value="<s:property value="mobile"/>"></td>													
													<td><input class="input" id="countryId<s:property value="id"/>" type="text" name="countryId" value="<s:property value="countryId"/>"></td>
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

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>


					</s:if>

					<s:elseif
						test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.usermode==1 &&  #session.User.userrole_id.superuser==0}">
						<form class="form-inline" action="FilterdPassengerList"
							method="post">
							
							
							<div class="form-group">
								<label for="exampleInputAmount">FirstName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="FirstName" name="passfirstName" value='<s:property value="passfirstName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">LastName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="LastName" name="passlastName" value='<s:property value="passlastName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Email Address</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="FlyerNo" name="passemail" value='<s:property value="passemail"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Mobile</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="Airline" name="passmobile" value='<s:property value="passmobile"/>'>
									
								</div>
							</div>
							
							
							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Search</button>
							</div>
							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">
									<!-- <div class="box-body"> -->
									<%-- 
								<label for="Country"><h4>
										<b><s:property value="user" /> Report List</b>
									</h4></label> --%>


									<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>

												<!-- <th>Order id</th> -->
												<th>FirstName</th>
												<th>LastName</th>
												<th>Gender</th>
												<th>Email Address</th>
												<th>Mobile</th>
												<th>City</th>
												<th>Country Id</th>
												

											</tr>
										</thead>
										<tbody>


											<s:iterator value="reportData_list">

												<tr>
													<td><input class="input" id="firstName<s:property value="id"/>" type="text" name="firstName" value="<s:property value="firstName"/>"></td>
													<td><input class="input" id="lastName<s:property value="id"/>" type="text" name="lastName" value="<s:property value="lastName"/>"></td>
													<td><input class="input" id="gender<s:property value="id"/>" type="text" name="gender" value="<s:property value="gender"/>"></td>
													<td><input class="input" id="email<s:property value="id"/>" type="text" name="email" value="<s:property value="email"/>"></td>
													<td><input class="input" id="mobile<s:property value="id"/>" type="text" name="mobile" value="<s:property value="mobile"/>"></td>												
													<td><input class="input" id="countryId<s:property value="id"/>" type="text" name="countryId" value="<s:property value="countryId"/>"></td>
													
													

												</tr>

											</s:iterator>

										</tbody>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>
					</s:elseif>

					<s:elseif
						test="%{#session.User!=null && #session.User.userrole_id.usermode==0 &&  #session.User.userrole_id.superuser==0}">
						<form class="form-inline" action="FilterdPassengerList"
							method="post">
							
							
							<div class="form-group">
								<label for="exampleInputAmount">FirstName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="FirstName" name="passfirstName" value='<s:property value="passfirstName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">LastName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="LastName" name="passlastName" value='<s:property value="passlastName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Email Address</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="FlyerNo" name="passemail" value='<s:property value="passemail"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Mobile</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="Airline" name="passmobile" value='<s:property value="passmobile"/>'>
									
								</div>
							</div>
							
							
							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Search</button>
							</div>
							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">
									<%-- <label for="Country"><h4>
										<b><s:property value="agencyName"/> </b>
									</h4></label> --%>

									<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>

												<!-- <th>Order id</th> -->
												<th>FirstName</th>
												<th>LastName</th>
												<th>Gender</th>
												<th>Email Address</th>
												<th>Mobile</th>
											
												<th>Country Id</th>

											</tr>
										</thead>
										<tbody>


											<s:iterator value="reportData_list">

												<tr>
													<td><input class="input" id="firstName<s:property value="id"/>" type="text" name="firstName" value="<s:property value="firstName"/>"></td>
													<td><input class="input" id="lastName<s:property value="id"/>" type="text" name="lastName" value="<s:property value="lastName"/>"></td>
													<td><input class="input" id="gender<s:property value="id"/>" type="text" name="gender" value="<s:property value="gender"/>"></td>
													<td><input class="input" id="email<s:property value="id"/>" type="text" name="email" value="<s:property value="email"/>"></td>
													<td><input class="input" id="mobile<s:property value="id"/>" type="text" name="mobile" value="<s:property value="mobile"/>"></td>
													
													<td><input class="input" id="countryId<s:property value="id"/>" type="text" name="countryId" value="<s:property value="countryId"/>"></td>
													

												</tr>

											</s:iterator>

										</tbody>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>


					</s:elseif>

 </div>



			</div>
			<!-- /.row -->
			<!-- Main row -->


		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>

	<script type="text/javascript">
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						lengthChange : true,
						/* "bPaginate": false, */
						"pagingType" : "full_numbers",
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						buttons : [ 'excel', 'pdf', 'print' ]
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');
					
					
					$('#example').on( 'length.dt', function ( e, settings, len ) {
					    console.log( 'New page length: '+len );
					    
					    var totUrl = $(location).attr('href');
				  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
				  		var finalUrl = newUrl+"LimitedPassengerList";
							 $.ajax({
								    method: "get",
								    url:finalUrl ,
								    data: {},
								    success:function(data,status)
									{ 
								    	  console.log("data-------"+data.jsonResult.result);
								    	//$.each(data, function(index, element) { 
								    		  //console.log("data-------"+element.result);

										     	if(data.jsonResult.result=="success"){
										    		 $('#success-alert').show();
													  $('#message').text("Successfully updated.");
													    $('#success').click(function() {
										  					  $('#success-alert').hide();
										  					 window.location.assign($(location).attr('href'));
										  					});
										  				
										  				
										    	}
										    	else if(data.jsonResult.result=="failed"){
										    		$('#success-alert').show();
													  $('#message').text("Failed.Try again.");
													    $('#success').click(function() {
										  					  $('#success-alert').hide();
										  					 
										  					});
									    	}
										    	 
								    	// });
								     
								    	
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
					    
					} );

				});
		
		/* var table = $('#example').DataTable();
		var info = table.page.info();
		console.log(info); */
		

		/*  $(function () {
		 	$('#example').DataTable({
		    	 "paging": true,
		         "lengthChange": true,
		        "searching": true,
		        "ordering": true,  
		           "info": true,
		         "autoWidth": false,  
		        "search": {
		      	    "regex": true,
		      	  }, 
		      	 
		      "pagingType": "full_numbers",
		      "lengthMenu": [10, 25, 50, 75, 100, 500 ],
		     
		      
		     });  
		  
		   });   */
	</script>
		<script type="text/javascript">
		
		 function isNumberKey(evt,obj){
		     evt = (evt) ? evt : window.event;
		       var charCode = (evt.which) ? evt.which : evt.keyCode;
		       if (charCode > 31 && (charCode < 48 || charCode > 57))  
		       
		           return false;
		       
		     
		   }
		
		
$(function() {
   /*  $('#row_dim').hide();  */
    $('#user').change(function(){
    	//alert($('#user').val());
        if($('#user').val()== 'ALL') {
            $('#company_form-group').hide(); 
        } 
        else if($('#user').val() == '0') {
        	 $('#company_form-group').show(); 
          
       } 
        else {
            $('#company_form-group').hide(); 
        } 
    });
   
   
    $('#companyName').change(function(){
    	//alert($('#companyName').val());
        if($('#companyName').val() == 'ALL') {
            $('#user_form-group').hide(); 
        } else if($('#companyName').val() == '0') {
        	 $('#user_form-group').show(); 
           
        } 
        else{
        	 $('#user_form-group').hide(); 
        }
    });
   
   
   
   
});
 </script>

	<script type="text/javascript">
      function  orderCustomerUpdate(id){
    	  var firstName=$("#firstName"+id).val();
    	  var LastName=$("#lastName"+id).val();
    	  var gender=$("#gender"+id).val();
    	  var email=$("#email"+id).val();
    	  var mobile=$("#mobile"+id).val();    	
    	  var countryId=$("#countryId"+id).val();    	  
    	 
			
    	  
    	  console.log("firstName..."+firstName); 
    	  console.log("LastName......"+LastName);
    	  console.log("email------"+email);
    	
    	  console.log("gender-------"+gender);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl = newUrl+"orderCustomerUpdate";
			 $.ajax({
				    method: "get",
				    url:finalUrl ,
				    data: {id:id,firstName:firstName,lastName:LastName,email:email,mobile:mobile,gender:gender,countryId:countryId},
				    success:function(data,status)
					{ 
				    	  console.log("data-------"+data.jsonResult.result);
				    	//$.each(data, function(index, element) { 
				    		  //console.log("data-------"+element.result);

						     	if(data.jsonResult.result=="success"){
						    		 $('#success-alert').show();
									  $('#message').text("Successfully updated.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					 window.location.assign($(location).attr('href'));
						  					});
						  				
						  				
						    	}
						    	else if(data.jsonResult.result=="failed"){
						    		$('#success-alert').show();
									  $('#message').text("Failed.Try again.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					 
						  					});
					    	}
						    	 
				    	// });
				     
				    	
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

</body>
</html>