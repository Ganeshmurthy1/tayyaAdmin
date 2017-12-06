<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="css/alert.css">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
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
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Galileo Configuration List <%-- <small>Control panel</small> --%>
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
            <a class="btn btn-primary but btn-clean" href="addNewGalileo"> Add New <i class="fa fa-angle-right"></i></a>
      </div>
    </div>
				 
				<table id="mytable1" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">

					<thead>

						<!--  <th><input type="checkbox" id="checkall" /></th> -->

						<!-- <th>User ID</th> -->
						<tr>
							<!-- <th>Galileo ConfigId</th>
								<th>Company Id</th> -->
							<th>Url</th>
							<th>target_branch</th>
							<th>UserName</th>

							<th>PCC</th>
							<th>Environment</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>


					</thead>
					<tbody>
						<s:iterator value="#session.GalileoConfigList">
							<tr>
								<%-- <td><s:property value="galileo_id" /></td>
									<td><s:property value="company_id" /></td> --%>
								<td><s:property value="url" /></td>
								<td><s:property value="target_branch" /></td>
								<td><s:property value="user_name" /></td>
								<td><s:property value="pcc" /></td>
								<td><s:property value="environment" /></td>
								
								 <td>
  						<p data-placement="top" title="update">
  					 
  						 <a href="galileoConfigProfile?galileo_id=<s:property value="galileo_id" />" class="btn btn-success btn-xs"  
											data-toggle="modal" ><span
											class="fa fa-cloud-upload" ></span></a>  
									</p>
  						
  						 </td>
								
								
								
						 	<td><p data-placement="top" title="Delete">
										<a href="javascript:void(0)"
											class="btn btn-danger btn-xs fa fa-trash" data-title="Delete"
											data-toggle="modal" data-target="#delete<s:property value="galileo_id"/>"
											onclick="galileoConfigDelete('<s:property value="galileo_id"/>')"></a>
									</p>
									
									

<div class="modal fade" id="delete<s:property value="galileo_id"/>" tabindex="-1" role="dialog"
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
				<button type="button" class="btn btn-success" id="yes<s:property value="galileo_id"/>">
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

			<%-- 	<div class="clearfix"></div>
				<ul class="pagination pull-right">
					<li class="disabled"><a href="#"><span
							class="fa fa-angle-left"></span></a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#"><span class="fa fa-angle-right"></span></a></li>
				</ul> --%>
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
<%--  <%@ include file="DashFooter.jsp"%> --%>
<script type="text/javascript">
  function galileoConfigDelete(galileo_id) {
		//alert(galileo_id);
	 var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"deleteGalileoConfigData";
   	   
			$("#yes"+galileo_id).click(function() {
				 $("#delete"+galileo_id).hide();
				 $(".modal-backdrop").hide();
						$.ajax({
							 method : "POST",
							 url :finalUrl,
							 data : { galileo_id : galileo_id },
							 success : function(data, status) {
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
								 error : function(e) {
										 alert(e.message);
										 }
								 });
							 });
						 

	}
</script>

<script type="text/javascript">
      $(function () {
      /*   $("#mytable1").DataTable(); */
        $('#mytable1').DataTable({
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





