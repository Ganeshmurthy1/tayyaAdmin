<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="css/alert.css">
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>
<!-- <link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> -->
 <div class="content-wrapper">
	<section class="content-header">
		<h1>Deal Sheet List</h1><span style="color:red">*</span><span style="font-size:10px">(<s:text name="global.applicableSheetNote"/>)</span>
	</section>
	 <section class="content">
		 <div class="row">
			<div class="clearfix">
				<div class="table-responsive dash-table">
					<table id="mytable" class="table table-striped ddd"
						data-sort-name="name" data-sort-order="desc">
						<thead>
							<tr>
								<th>S.No</th>
								<th>SheetName</th>
								<th>Status</th>
								<th>Action</th>
								<th>Action</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="sheetList" status="rowCount" var="myobj">
								<tr>
									<td><s:property value="%{#rowCount.count}" /></td>
									<td><s:property value="name" /></td>
									<td>
									
								 <form action="dealSheetActiveorInActive" method="post" class="statusform"  id="statusForm<s:property value="id"/>">
										<input type="hidden" name="id" value="<s:property value="id"/>" id="uniqueId">
											 
											 <input type="hidden" name="Active"  value="<s:property value="Active"/>" id="Active">
											  <span id="load<s:property value="id"/>" style="display:none"><img src="images/loading.GIF" class="load-gif"> </span>
										 	 
										 	<s:if test="Active == true">
										 	
										 	<span class="text-success"><i class="fa fa-check-circle green"></i> Active Sheet</span>
										 	<%-- <input   value="Active Sheet" disabled="disabled" onclick="activateOrDeactivateFormsubmit(<s:property value="id"/>)"	id="stat<s:property value="id"/>">  --%>
											</s:if>
											<s:else>
												<span class="text-error red" title = "Inactive Sheet"><i class="fa fa-times-circle red"></i></span><input type="button" class="btn-primary" value="Activate" onclick="activateOrDeactivateFormsubmit(<s:property value="id"/>)"	id="stat<s:property value="id"/>"> 
											</s:else>
											 </form>  
									
									<%--   <s:if test="#myobj.Active == true">
								 
  									 Activated
 	 							</s:if> <s:if test="#myobj.Active == false">
 									  Deactivated
  										</s:if> --%></td>
  										
  											<td>
  											
  											<p data-placement="top" title="edit deal sheet">
											<a
												href="superUserDealSheetEdit?id=<s:property value="id"/>"
												class="btn btn-success btn-xs" data-toggle="modal">Edit</a>
										</p>
										

									</td>
									
									<td>
  										 <p data-placement="top" title="duplicate deal sheet">
											<a
												href="createDuplicateDealSheet?type=duplicate&id=<s:property value="id"/>"
												class="btn btn-success btn-xs" data-toggle="modal">Duplicate</a>
										</p>
									 </td>
									
									  <td>
										<p data-placement="top" title="edit commissions">
											<a
												href="airlinecommission?sheetId=<s:property value="id"/>"
												class="btn btn-success btn-xs" data-toggle="modal">Edit Commissions </a>
										</p>
									</td>
  										
									
								</tr>
							</s:iterator>
						</tbody>

					</table>
 <s:if test="hasActionErrors()">
				 <div class="sccuss-full-updated" id="success-alert">
							<div class="succfully-updated clearfix">

								<div class="col-sm-2">
									<i class="fa fa-times fa-3x red"></i>
								</div>

								<div class="col-sm-10">
									<s:actionerror />
									<button type="button" id="cancel" class="btn btn-primary">Ok</button>

								</div>

							</div>
						</div>
				 
				  </s:if>

					<s:if test="hasActionMessages()">
						<div class="sccuss-full-updated" id="success-alert">
							<div class="succfully-updated clearfix">

								<div class="col-sm-2">
									<i class="fa fa-check fa-3x"></i>
								</div>

								<div class="col-sm-10">
									<s:actionmessage />
									<button type="button" id="success" class="btn btn-primary">Ok</button>

								</div>

							</div>
						</div>
					</s:if>
				</div>

			</div>
		</div>
	</section>
</div>
<!-- content wrapper -->
<%@ include file="DashboardFooter.jsp"%>
<script type="text/javascript">
      $(function () {
      
        $('#mytable').DataTable({
            "paging": true,
            "lengthChange": true,
            "searching": false,
            "ordering": true,
             "info": true,
             "autoWidth": false,
             "search": {
          	    "regex": true,
          	  },
          "pagingType": "full_numbers",
          "lengthMenu": [50, 75, 100, 500 ],
          
            });
      });
    </script>

	<script type="text/javascript">
	$(function() {
		var totUrl=$(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		  var finalUrl = newUrl+"getSuperUserDealSheet";
		 $('#success').click(function() {
		 window.location.assign(finalUrl); 
			$('#success-alert').hide();
			
		});
		  $('#cancel').click(function() {
			   $('#error-alert').hide();
			   window.location.assign(finalUrl); 
			});  
	 });
	 </script>
	 <script type="text/javascript">
	 function activateOrDeactivateFormsubmit(id){
		console.log("-------------id--------------"+id);
		$('#stat'+id).hide();
		$('#load'+id).show();
		$('#statusForm'+id).submit();
		
		
	}
	 </script>
	 








