<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="css/alert.css">
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> --%>
 <div class="content-wrapper">
	<section class="content-header">
   
 <h1>My Deal Sheet</h1>
  
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
								<th>BlockName</th>
								<th>Sheet Name</th>
								<th>Status</th>
								 <th>Action</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="blockList" status="rowCount" var="myobj">
								<tr>
									<td><s:property value="%{#rowCount.count}" /></td>
									<td><s:property value="name" /></td>
									<td><s:property value="sheetInfo" /></td>
									<td><s:if test="Active == true">
  									 Activated
 	 							</s:if> <s:if test="Active == false">
 									  Deactivated
  										</s:if></td>
  									  <td>
										<p data-placement="top" title="view block sheet">
										 
										 
											<a
												href="airlinediscommission?blockId=<s:property value="id"/>&companyId=<s:property value="%{#session.Company.companyid}"/>
												&configId=<s:property value="companyConfigId"/>&iseditable=false"
												class="btn btn-success btn-xs" data-toggle="modal">View</span></a>
										 
										</p>

									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>

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
            "searching": true,
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










