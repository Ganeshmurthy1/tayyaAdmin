<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>

</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Frequent Flyer Details</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>





		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
					<s:if
						test="%{#session.Company!=null && #session.User!=null && #session.User.userrole_id.superuser==1}">

						<form class="form-inline" action="FilterdFrequentFlyerList"
							method="post">
							
							
							<div class="form-group">
								<label for="exampleInputAmount">FirstName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="firstName"
										placeholder="FirstName" name="firstName" value='<s:property value="firstName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">LastName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="lastName"
										placeholder="LastName" name="lastName" value='<s:property value="lastName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Frequent FlyerNo</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="frequentFlyerNo"
										placeholder="FlyerNo" name="frequent_flyer_number" value='<s:property value="frequent_flyer_number"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Frequent Flyer Airline</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="frequentFlyerNo"
										placeholder="Airline" name="frequent_flyer_airline" value='<s:property value="frequent_flyer_airline"/>'>
									
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
												<th>Passport Number</th>	
												<th>Passport Expiry Date</th>										
												<th>FrequentFlyer Number</th>
												<th>FrequentFlyer AirLine</th>
												
												

											</tr>
										</thead>
										<tbody>


											<s:iterator value="FlyerreportData_list">

												<tr>
													<td><s:property value="firstName" /></td>
													<td><s:property value="lastName" /></td>																
												   <td><s:property value="passportNo" /></td>
												   <td><s:property value="passportExpiryDate" /></td>
													<td><s:property value="frequent_flyer_number" /></td>
													<td><s:property value="frequent_flyer_airline" /></td>	
													
													

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
						<form class="form-inline" action="FilterdFrequentFlyerList"
							method="post">
							
							
							<div class="form-group">
								<label for="exampleInputAmount">FirstName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="firstName"
										placeholder="FirstName" name="firstName" value='<s:property value="firstName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">LastName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="lastName"
										placeholder="LastName" name="lastName" value='<s:property value="lastName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Frequent FlyerNo</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="frequentFlyerNo"
										placeholder="FlyerNo" name="frequent_flyer_number" value='<s:property value="frequent_flyer_number"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Frequent Flyer Airline</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="frequentFlyerAirline"
										placeholder="Airline" name="frequent_flyer_airline" value='<s:property value="frequent_flyer_airline"/>'>
									
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
												<th>Passport Number</th>	
												<th>Passport Expiry Date</th>														
												<th>FrequentFlyer Number</th>
												<th>FrequentFlyer AirLine</th>
												
											</tr>
										</thead>
										<tbody>


											<s:iterator value="FlyerreportData_list">

												<tr>
													<td><s:property value="firstName" /></td>
													<td><s:property value="lastName" /></td>													
														<td><s:property value="passportNo" /></td>	
														<td><s:property value="passportExpiryDate" /></td>												
													<td><s:property value="frequent_flyer_number" /></td>
													<td><s:property value="frequent_flyer_airline" /></td>	
													
													

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
						<form class="form-inline" action="FilterdFrequentFlyerList"
							method="post">
							
							
							<div class="form-group">
								<label for="exampleInputAmount">FirstName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="firstName"
										placeholder="FirstName" name="firstName" value='<s:property value="firstName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">LastName</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="lastName"
										placeholder="LastName" name="lastName" value='<s:property value="lastName"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Frequent FlyerNo</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="frequentFlyerNo"
										placeholder="FlyerNo" name="frequent_flyer_number" value='<s:property value="frequent_flyer_number"/>'>
									
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAmount">Frequent Flyer Airline</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="frequentFlyerAirline"
										placeholder="Airline" name="frequent_flyer_airline" value='<s:property value="frequent_flyer_airline"/>'>
									
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
												
												<th>Passport Number</th>												
												<th>Passport Expiry Date</th>			
												<th>FrequentFlyer Number</th>
												<th>FrequentFlyer AirLine</th>
												
												

											</tr>
										</thead>
										<tbody>


											<s:iterator value="FlyerreportData_list">

												<tr>
													<td><s:property value="firstName" /></td>
													<td><s:property value="lastName" /></td>
												
														<td><s:property value="passportNo" /></td>
														<td><s:property value="passportExpiryDate" /></td>
													
													<td><s:property value="frequent_flyer_number" /></td>
													<td><s:property value="frequent_flyer_airline" /></td>													
													
													

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
						"pagingType" : "full_numbers",
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						buttons : [ 'excel', 'pdf', 'print' ]
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});

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

	<!-- 
 
  $(document).ready(function() 
    	 { 
    		 $("#twodpd1").datepicker({
    			 dateFormat: "yy-mm-dd"  
    			/*  changeMonth: true,
    			 changeYear: true */
    		 }); 
    		 }); -->

</body>
</html>