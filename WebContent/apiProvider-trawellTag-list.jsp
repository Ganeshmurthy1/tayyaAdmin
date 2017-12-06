<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Trawell Tag Config List</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<form class="form-inline" action="" method="post">
				
		 <div class="table-responsive dash-table">
				 
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
 							<tr>
							<th>S.No</th>
							<th>Title</th>
							<th>ApiMode</th>
							<th>Company</th>
							<th>Status</th>
							<th>Action(s)</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="apiProviderTrawellTagConfigsList" status="rowCount">
							<tr>
								<td>${rowCount.count}</td>
								<td>${title}</td>
								<td>${environment}</td>
								<td>${companyName}</td>
								<td><span >
                 <label class="switch"> <input type="checkbox"  name="active"  data-id="${id}"
                  class="trawellTagActive" <c:if test="${active==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								  <td>
									<p data-placement="top">
										<a href="editTrawellTagConfig?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" >Edit
											 </a>
									</p>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			</form>
		</div>
	</section>
</div>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
<script>
$('.trawellTagActive').change(function (){
		var status = false;
		var id = $(this).data("id");
		if($(this).is(":checked")==false)
		{
			status= false;
			$(this).attr('checked', false);
			// $(this).attr('value', false);
		}
		else{
			status= true;
			$(this).attr('checked', true);
			//$(this).attr('value', true);
		}
		var name = $(this).attr("name");

		$.ajax({
			url :"updateTrawellTagStatus",
			data : {
				id :id,
				active:status,
			}, 
			success : function(data, textStatus, jqXHR) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}

		});
});

 </script>
  


  