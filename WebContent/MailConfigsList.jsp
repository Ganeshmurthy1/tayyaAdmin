<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<link rel="stylesheet" href="css/alert.css">
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
 
	<div class="sccuss-full-updated" id="success-alert"
		style="display: none">
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="message"></div>
			<button type="button" id="success" class="btn btn-primary">Ok</button>

		</div>
	</div>

	<div class="sccuss-full-updated" id="error-alert" style="display: none">
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="message"></div>
			<button type="button" id="cancel" class="btn btn-primary">Ok</button>

		</div>
	</div>
	
	
	
	
	<section class="content-header">
		<h1>
		Mail Configuration List <%-- <small>Control panel</small> --%>
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
            <a class="btn btn-primary but btn-clean" href="addNewMailConfig"> Add New <i class="fa fa-angle-right"></i></a>
      </div>
    </div>
				
				 
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">

					<thead>

						<!--  <th><input type="checkbox" id="checkall" /></th> -->

						<!-- <th>User ID</th> -->
						<tr>
							<th>MailConfig Name</th>
							<th>Server Host</th>
							<th>Server User</th>
							<th>server Port</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>


					</thead>
					<tbody>
						<s:iterator value="#session.mailConfigList">
							<tr>
								<td><s:property value="mail_config_name" /></td>
								<td><s:property value="mail_server_host" /></td>
								<td><s:property value="mail_server_user" /></td>
								<td><s:property value="mailServerPort" /></td>
								 <td>
  						<p data-placement="top" title="update">
  					 
  						 <a href="mailConfigProfile?mail_config_id=<s:property value="mail_config_id" />" class="btn btn-success btn-xs"  
											data-toggle="modal" ><span
											class="fa fa-cloud-upload" ></span></a>  
									</p>
  						
  						 </td>
							 	<td><p data-placement="top" title="Delete">
										<a href="javascript:void(0)"
											class="btn btn-danger btn-xs fa fa-trash" data-title="Delete"
											data-toggle="modal" data-target="#delete<s:property value="mail_config_id"/>"
											onclick="mailConfigDelete('<s:property value="mail_config_id"/>')"></a>
									</p>
									
									
<div    class="modal fade" id="delete<s:property value="mail_config_id"/>" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
				<h4 class="modal-title custom_align" id="Heading">Delete this
					entry</h4>
			</div>
			<div class="modal-body">

				<div class="alert alert-danger">
					<span class="glyphicon glyphicon-warning-sign"></span> Are you sure
					you want to delete this Record?
				</div>

			</div>
			<div class="modal-footer ">
				<button type="button" class="btn btn-success" id="yes<s:property value="mail_config_id"/>">
					<span class="glyphicon glyphicon-ok-sign"></span> Yes
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span> No
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
									
 </td>
							</tr>
						</s:iterator>
					</tbody>

				</table>
 
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
<%-- <%@ include file="DashFooter.jsp"%> --%>
<script type="text/javascript">
      function mailConfigDelete(mail_config_id){
    	
    	 
    	  var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"deleteMailConfigData";
   
    			 $("#yes"+mail_config_id).click(function () {
    				  $("#delete"+mail_config_id).hide();
    				 $(".modal-backdrop").hide();
    				 $.ajax({
    					    method: "POST",
    					    url: finalUrl,
    					    data: {mail_config_id : mail_config_id },
    					    
    					    success:function(data,status)
    						{ 
    					    	if(data=="deleted"){
    					    		 $('#success-alert').show();
									  $('#message').text("Successfully deleted.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					 window.location.assign($(location).attr('href'));
						  					});
						  				
						  				
    					    	}
    					    	else if(data=="failed"){
    					    		$('#error-alert').show();
									  $('#message').text("Failed.Try again.");
									    $('#cancel').click(function() {
						  					  $('#error-alert').hide();
						  					 
						  					});
    					    	}
    					    	 
    					     },
    						error: function(e)
    						{
    						   alert(e.message);
    						}
    					});  
    			         
    			     });
    	 }
      
      
  </script>

<script type="text/javascript">
      $(function () {
      /*   $("#mytable1").DataTable(); */
        $('#mytable').DataTable({
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
      });
    </script>









